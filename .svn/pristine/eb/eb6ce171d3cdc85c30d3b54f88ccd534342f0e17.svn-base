package com.flyrish.hades.webapp.action.appraise;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IAppraisalChildExt;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class QueryAppraiseAction extends BaseAction{
	//教育标识号
	public String edu_id;
	//学期标识号
	public String termid;
	//学段代码
	public String levelcode;
	
	public String cmis30id;
	
	public String discode;
	
	public String classId;
	
	public String studentName;
	
	public String commonFuncId;
	
	public String flag;
	String userRealtype;
	public String checkFlag;
	
	@Spring
	public IOperationAppraiseServiceExt operationAppraiseServiceExt;
	@Spring
	public IAppraisalChildExt appraisalChildExt;
	@Spring
	public ILoginUserInfoServiceExt loginUserInfoServiceExt;
	
	public String styleChange;
	
	public Map<String,List<AppraiseBaseDto>> appraiseMaps=new LinkedHashMap<String,List<AppraiseBaseDto>>(0);
	@DefaultAction
	public Object defaultQueryAction(HttpServletRequest request, HttpServletRequest req){
		request.setAttribute("commonFuncId", commonFuncId);
		//判断角色类型，如果教育id为空或者家长角色登录，则教育标识号从session中获取
		initEdu_id(req);
		userDto=(UserDto) req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		//获取学段代码
		levelcode=userDto.getLevelcode();
		cmis30id=userDto.getCmis30id();
		discode=userDto.getDiscode();
		classId=userDto.getClassid();
		userRealtype = userDto.getUserRealType();
		if((Constant.USER_TYPE_STUDENT.equals(userRealtype)||Constant.USER_TYPE_PARENT.equals(userRealtype))&&NestUtil.isEmpty(checkFlag))   //学生和家长
		{
			return "/common/checkapp.jsp";
		}
		if("1".equals(flag))
		{
			try {
				String name = URLDecoder.decode(studentName, "UTF-8");
				studentName = name;
			} catch (UnsupportedEncodingException e) {
				logger.error("defaultQueryAction(HttpServletRequest)",e);
			}
		}else{
			if(null==studentName||"".equals(studentName)){
				studentName=userDto.getStudentName();
			}
		}
		if(null==classId){
			List<UserDto> list =loginUserInfoServiceExt.queryClassidByEduid(edu_id, cmis30id, discode);
			if(list !=null){
				classId=list.get(0).getClassid();
			}
		}
		
		
		
		//（高中）如果对应的学期标识号为空，则查询出对应的学期标识号(11,12,21,22,31,32)
		if(NestUtil.isNotEmpty(levelcode)&&NestUtil.isEmpty(termid)&&((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_GZ) 
				|| (Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))){
			termid=operationAppraiseServiceExt.calcuHeighTermidByEduId(edu_id, cmis30id, discode);
		}
		else if(NestUtil.isNotEmpty(levelcode)&&NestUtil.isEmpty(termid)
				&&((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_CZ))){
			/*termid=operationAppraiseServiceExt.calcuMiddleTermidByEduId(edu_id,cmis30id,discode);*/
			termid = userDto.getTermId();
		}
		
		//获取数据集合
		List<AppraiseBaseDto> appraiseBaseDtos=operationAppraiseServiceExt.queryAppraiseBaseDtoByCondition(studentName,edu_id,termid,levelcode,cmis30id,discode);
		
		//组装数据
		if(Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_CZ)){
			installDataMaps2(appraiseBaseDtos,req);
			
			return "checkappraiseMiddleSchool.jsp";
			
		}else{
			installDataMaps(appraiseBaseDtos,req);
			return "checkappraise.jsp";
		}
		
		
	}
	private void initEdu_id(HttpServletRequest req){
		
		userDto = (UserDto) req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		if(NestUtil.isNotEmpty(edu_id)||userDto==null)return;
		String userType = userDto.getUsertype();
		if(Constant.USER_KIND_SCHOOLSTUDENT.equals(userType)
				||Constant.USER_KIND_SCHOOLFAM.equals(userType)){
			styleChange = "yes";
			edu_id=userDto.getEduId();
		}
		
		
	}
	private void installDataMaps(List<AppraiseBaseDto> appraiseBaseDtos,HttpServletRequest req){
		if(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)return;
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(dto==null||NestUtil.isEmpty(dto.getAppraisaltypeid())) continue;
			//将当前用户可操作的信息只为可读
			Boolean isReadOnly=estimateIsReadOnlyByLoginUser(req,dto.getAppraserid(),dto.getAppraisaltypeid(),dto.getAppraser());
			dto.setIsReadOnly(isReadOnly);
			//组装数据集合
			List<AppraiseBaseDto> dtos=null;
			dtos=appraiseMaps.get(dto.getAppraisaltypeid());
			if(dtos==null){
				dtos=new ArrayList<AppraiseBaseDto>();
			    appraiseMaps.put(dto.getAppraisaltypeid(),dtos);
			}
			dtos.add(dto);
		}
	}
	
	private void installDataMaps2(List<AppraiseBaseDto> appraiseBaseDtos,HttpServletRequest req){
		if(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)return;
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(dto==null||NestUtil.isEmpty(dto.getTwoPartId())) continue;
			//将当前用户可操作的信息只为可读
			Boolean isReadOnly=estimateIsReadOnlyByLoginUser2(req,dto.getUserid(),dto.getTwoPartId(),dto.getSignerName());
			dto.setIsReadOnly(isReadOnly);
			//组装数据集合
			List<AppraiseBaseDto> dtos=null;
			dtos=appraiseMaps.get(dto.getTwoPartId());
			if(dtos==null){
				dtos=new ArrayList<AppraiseBaseDto>();
			    appraiseMaps.put(dto.getTwoPartId(),dtos);
			}
			dtos.add(dto);
		}
	}
	/**
	 * 高中
	 * 通过登录用户身份判断当前的评价信息是否只读
	 * @param req reqest对象
	 * @param appraserid 评价人标识号
	 * @param appraser 评价人姓名
	 * @return
	 */
	public Boolean estimateIsReadOnlyByLoginUser(HttpServletRequest req,String appraserid,String appraisaltypeid,String appraser){
		if(userDto==null)return true;
		String username=userDto.getUsername();
		//如果角色为班主任，并且该评语为班主任评语，则将其置为非只读
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(userDto.getUsertype())
				&&(Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())||(NestUtil.isNotEmpty(userDto.getTeacherName())&&userDto.getTeacherName().equals(appraser)))
				&&Constant.TYPE_END_BZRPY.equals(appraisaltypeid)){
			return false;
		}
		if(NestUtil.isNotEmpty(username)&&username.equals(appraserid)){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * 初中
	 * 通过登录用户身份判断当前的评价信息是否只读
	 * @param req reqest对象
	 * @param appraserid 评价人标识号
	 * @param appraser 评价人姓名
	 * @return
	 */
	public Boolean estimateIsReadOnlyByLoginUser2(HttpServletRequest req,String userid,String twoPartid,String appraser){
		if(userDto==null)return true;
		String username=userDto.getUsername();
		//如果角色为班主任，并且该评语为班主任评语，则将其置为非只读
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(userDto.getUsertype())
				&&(Constant.USER_MASTERROLE_TYPE.equals(userDto.getUserRealType())||Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())||(NestUtil.isNotEmpty(userDto.getTeacherName())&&userDto.getTeacherName().equals(appraser)))
				&&Constant.CHARGE_TEACHER_APPRAISAL.equals(twoPartid)){
			return false;
		}
	//	if(NestUtil.isNotEmpty(username)&&username.equals(userid)&&userid.equals(userDto.getEduId())){
		if(NestUtil.isNotEmpty(username)&&username.equals(userid)){
			return false;
		}else{
			return true;
		}
	}
}
