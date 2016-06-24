package com.flyrish.hades.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AuthInfo;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.BadRandomCodeException;
import com.flyrish.hades.exception.FamliyUserNotSupportException;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.LoginIsUserdException;
import com.flyrish.hades.exception.SchoolNotFoundException;
import com.flyrish.hades.exception.StudentInfoException;
import com.flyrish.hades.exception.UserNameException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class UserAuthenticationProcessingFilter extends AuthenticationProcessingFilter{
	
	
	private IOUserServiceExt userServiceExt;
	
	private IRedisServiceExt redisServiceExt;
	
	private ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	private ConstantBean constantBean;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request)
		throws org.acegisecurity.AuthenticationException {
		//获取登录用户相关信息
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		//学校标识号
		String schoolId = request.getParameter("userSchoolId");
		
		String isFamliyEnable=constantBean.get("isFamliyEnable");
		if("0".equals(isFamliyEnable)&&username.contains("p")){
			throw new FamliyUserNotSupportException("user is not support");
		}
		//拼接redis缓存key 规则：登录用户名_学校标识号，如果学校标识号为空，则取登录用户名
		String redisKey=Constant.R_REDIS_LOGINUSER+(NestUtil.isNotEmpty(schoolId)?username+"_"+schoolId:username);
		//不要注释此代码，如果有问题，联系我
		try {
			redisServiceExt.delete(redisKey);
		} catch (ForceException e1) {
			e1.printStackTrace();
		}
		//重缓存中获取登录用户信息
		UserDto userdto=redisServiceExt.readSingle(redisKey);
		//如果缓存中没有，则组装信息
		if(userdto==null){
			userdto=userServiceExt.findLoginUserByUserName(username,password,schoolId);
			if(userdto!=null&&!"1".equals(userdto.getUsed())){
				//用户被禁用
				throw new LoginIsUserdException("user is forbidden");
			}
		}	
		//acegi验证的dto
		AuthInfo auth = new AuthInfo();
		auth.setUsername(username);
		auth.setPassword(password);
		auth.setSchoolId(schoolId);
		auth.setSystemtype(userdto==null?null:userdto.getSystemtype());
		auth.setUserid(userdto==null?null:userdto.getUserid());
		auth.setUsertype(userdto==null?null:userdto.getUsertype());
		//组装
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
		authRequest.setDetails(auth);
		//验证用户登录信息和用户权限
		Authentication authentication=this.getAuthenticationManager().authenticate(authRequest);
		//如果是新增学生用户，则需要重新获取相应的用户信息
		if(userdto==null){
			userdto=userServiceExt.findLoginUserByUserName(username,password,schoolId);
			if(userdto!=null&&!"1".equals(userdto.getUsed())){
				//用户被禁用
				throw new LoginIsUserdException("user is forbidden");
			}
			if(userdto==null)
				throw new UserNameException("user is not exsit");
		}
		/*if(!Constant.USER_KIND_SCHOOLGROUP.equals(userdto.getUsertype())){
			throw new UserNameException("user is not exsit");
		}*/
		//对于新生代对象组装系统所需的信息
		if(!userdto.getIsCacheObj()){
			//查询当前有效的学期
			setCurrentTermid(userdto);
			//对于区级用户
			if(Constant.USER_KIND_COUNTY.equals(userdto.getUsertype())){
				userdto.setDiscode(userdto.getCmis30id());
			//对于校级用户
			}else if(Constant.USER_KIND_SCHOOLGROUP.equals(userdto.getUsertype())
					||Constant.USER_KIND_SCHOOLTEACHER.equals(userdto.getUsertype())){
				//设置校级用户的信息
				setSchoolUserInfo(userdto);
			}else if(Constant.USER_KIND_SCHOOLSTUDENT.equals(userdto.getUsertype())
					||Constant.USER_KIND_SCHOOLFAM.equals(userdto.getUsertype())){
				//设置学生或家长信息
				setStudentOrParentInfo(userdto);
			}
			userdto.setIsCacheObj(true);
			try {
				latestEvaluationRecordExt.recodeKeyInTotalKey(redisKey,Constant.R_REDIS_CACHE_LOGINKEY,constantBean.get("loginContailerNum"));
				redisServiceExt.save(redisKey,userdto);
			} catch (ForceException e) {
				logger.error("将数据对象保存到redis中异常",e);
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute(Constant.KEY_LOGIN_USER,userdto);
		return authentication;
	}
	private void setStudentOrParentInfo(UserDto userdto) {
		String edu_id=null;
		if(Constant.USER_KIND_SCHOOLSTUDENT.equals(userdto.getUsertype())){
			//如果角色为学生
			edu_id=userdto.getUsername();
		}else{
			//如果角色为家长
			try{
				edu_id=userdto.getUsername().toString().substring(0, userdto.getUsername().toString().lastIndexOf("p"));
			}catch(Exception ex){
				//如果用户名不符合要求，则该用户不存在
				throw new UserNameException("user is not exsit");
			}
		}
		Map<String,String> studentInfo=userServiceExt.queryStudentInfo(edu_id,userdto.getTermId());
		if(studentInfo==null||studentInfo.size()==0)
			//所查询的学生不存在
			throw new StudentInfoException("user is not exsit");
		
		userdto.setEduId(edu_id);
		userdto.setDiscode(studentInfo.get("discode"));
		userdto.setCmis30id(studentInfo.get("cmis30id"));
		userdto.setUnitid(studentInfo.get("cmis30id"));
		userdto.setSchoolName(studentInfo.get("schoolname"));
		userdto.setPersonid(studentInfo.get("studentid"));
		userdto.setStudentName(studentInfo.get("name"));
		userdto.setGradenum(studentInfo.get("gradenum"));
		userdto.setGradeid(studentInfo.get("gradeid"));
		userdto.setClassid(studentInfo.get("classid"));
		userdto.setCampuseId(studentInfo.get("campusid"));
		userdto.setLevelcode(studentInfo.get("levelcode"));
		userdto.setClassName(studentInfo.get("classname"));
		userdto.setGradeName(studentInfo.get("gradename"));
		userdto.setLevelName(studentInfo.get("levelname"));
		userdto.setLevelid(studentInfo.get("levelid"));
		userdto.setMasterid(studentInfo.get("masterid"));
	}
	private void setSchoolUserInfo(UserDto userdto) {
		//填充cmis30id和discode
		//查询区县代码discode、学校名称schoolName
		Map<String,String> mapdto=userServiceExt.queryDiscodeAndSchoolName(userdto.getCmis30id(),userdto.getPersonid());
		if(mapdto==null)
			throw new SchoolNotFoundException("user is not exsit");
		
		userdto.setDiscode(mapdto.get("discode"));
		
		userdto.setSchoolName(mapdto.get("schoolName"));
		
		userdto.setTeacherid(userdto.getPersonid());
		
		userdto.setTeacherName(mapdto.get("teachername"));
	}
	@DataSource("read")
	private void setCurrentTermid(UserDto userdto) {
		String currentTermid=redisServiceExt.readSingle(Constant.TYPE_CURRENT_TERMID);
		String currentTermName=redisServiceExt.readSingle(Constant.TYPE_CURRENT_TERMNAME);
		if(NestUtil.isEmpty(currentTermid)||NestUtil.isEmpty(currentTermName)){
			Map<String,String> termMap=userServiceExt.queryCurrentTermid();			
			try {
				if(NestUtil.isEmpty(currentTermid)){
					currentTermid=termMap==null?null:termMap.get("termid");
					redisServiceExt.save(Constant.TYPE_CURRENT_TERMID,currentTermid);
				}
				if(NestUtil.isEmpty(currentTermName)){
					currentTermName=termMap==null?null:termMap.get("termname");
					redisServiceExt.save(Constant.TYPE_CURRENT_TERMNAME,currentTermName);
				}
			}catch (ForceException e) {
				logger.error("setCurrentTermid(UserDto)",e);
			}
		}
		userdto.setTermId(currentTermid);
		userdto.setTermName(currentTermName);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException {
		super.onSuccessfulAuthentication(request, response, authResult);
	}
	/*
     *  认证通过后将用户对象从数据库中取出来设置到Session中去
     * (non-Javadoc)
     * @see org.acegisecurity.ui.AbstractProcessingFilter#onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.acegisecurity.Authentication)
     */
	@Override
	protected void onPreAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException {
		//验证码校验代码
		String checkCode = request.getParameter("j_captcha_response");
		if (NestUtil.isEmpty(checkCode)
				|| !checkCode.equalsIgnoreCase((String) request.getSession()
						.getAttribute(Constant.KEY_LOGIN_CHECKCODE))) {
			throw new BadRandomCodeException("checkCode error");
		}
	}

	
	public IOUserServiceExt getUserServiceExt() {
		return userServiceExt;
	}

	public void setUserServiceExt(IOUserServiceExt userServiceExt) {
		this.userServiceExt = userServiceExt;
	}
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}
	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	
}
