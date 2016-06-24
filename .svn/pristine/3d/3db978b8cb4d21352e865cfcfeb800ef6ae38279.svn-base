package com.flyrish.hades.webapp.action.dwr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.ICommonManagerExt;
import com.flyrish.hades.service.ext.IHomePageManagerExt;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.service.ext.IWelcomeManagerExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class ShowUserFuncDwr extends BaseAction {

	private IOUserServiceExt userServiceExt;
	
	private IHomePageManagerExt homePageManagerExt;
	
	private ILoginUserInfoServiceExt loginUserInfoServiceExt;
	
	private IWelcomeManagerExt welcomeManagerExt;
	
	public Integer levelId;
	
	@Json
	public List<OFunc> queryUseNumUserMenu(String userId,String roleId,HttpServletRequest req)
	{
		UserDto userDto = this.getLoginInfo(req);
		if(userDto==null)return null;
		Integer funcLevelType=null;
		if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
			funcLevelType = 1;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode())){
			funcLevelType = 3;
		}
		List<OFunc> commonFuncs = homePageManagerExt.queryCommonFuncFromRedis(userDto, funcLevelType);
		if(!(null!=commonFuncs && commonFuncs.size()>0)){
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("userId", userId);
			queryMap.put("roleId",roleId);
			queryMap.put("funcLevelType",funcLevelType);
			commonFuncs = welcomeManagerExt.findCommonFunc("ShowUserFuncDwr.queryUseNumUserMenu.query", queryMap);
			if(null!=commonFuncs && commonFuncs.size()>0){
				for(OFunc of : commonFuncs){
					of.setClickCount(1);
				}
			}
			//将数据放入缓存
			homePageManagerExt.saveCommonMenuToRedis(userDto, commonFuncs);
		}
		return commonFuncs;
	}
	public List<FuncTreeDto> refreshLevelCode(String levelcode,HttpServletRequest req){
		UserDto userDto = this.getLoginInfo(req);
		if(userDto==null)return null;
		userDto.setLevelid(levelcode);
		userDto.setLevelcode(levelcode);
		//解决session共享，未刷新session数据问题
		req.getSession().removeAttribute(Constant.KEY_LOGIN_USER);
		req.getSession().setAttribute(Constant.KEY_LOGIN_USER,userDto);
		
		Integer funcLevelType=null;
		//默认角色下的所有菜单
		if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelcode)){
			funcLevelType = 1;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelcode)
						||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelcode)){
			funcLevelType = 3;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZKGTR.equals(levelcode)){
			return userServiceExt.queryScoreTree();
		}else{
			return userServiceExt.queryAllFuncTree(userDto.getUserid(),userDto.getRoleId(),funcLevelType,2);
		}
	}
	public boolean saveUserFunc(String userId,String commonFuncId,String funcMessg,HttpServletRequest req)
	{
		UserDto userDto = this.getLoginInfo(req);
		if(userDto==null)return false;
		Integer funcLevelType=null;
		//默认角色下的所有菜单
		if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
			funcLevelType = 1;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
						||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode())){
			funcLevelType = 3;
		}
		commonManagerExt.updateCommonFuncTable(userId, commonFuncId);
		homePageManagerExt.saveOrUpdateCommonMenuToRedis(userDto, commonFuncId,funcLevelType, funcMessg);
		return true;
	}
	
	
	public List<FuncTreeDto> queryUseCommonFunc(String roleId,HttpServletRequest req){
		UserDto userDto = this.getLoginInfo(req);
		
		if(userDto==null)return null;
		Integer funcLevelType=null;
		if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
			funcLevelType = 1;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode())){
			funcLevelType = 3;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZKGTR.equals(userDto.getLevelcode())){
			return userServiceExt.queryScoreTree();
		}
		//从缓存中获取数据
		List<OFunc> commonFuncFromRedis = homePageManagerExt.queryCommonFuncFromRedis(userDto, funcLevelType);
		if(null!=commonFuncFromRedis && commonFuncFromRedis.size()>0){
			List<FuncTreeDto>ofs = new ArrayList<FuncTreeDto>();
			for(OFunc oFunc : commonFuncFromRedis){
				FuncTreeDto ftd = new FuncTreeDto();
				ftd.setFuncId(oFunc.getFuncid().toString());
				ftd.setFuncName(oFunc.getFuncname());
				ftd.setUrl(oFunc.getExecfilename());
				ofs.add(ftd);
			}
		/*if(Constant.USER_TYPE_SCHOOLADMIN.equals(userDto.getUserRealType())&&(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode()))){
			FuncTreeDto ftd = new FuncTreeDto();
			ftd.setFuncId("5130");
			ftd.setFuncName("高中报告册");
			ftd.setUrl("/bookreport/BookreportAction.a");
			ofs.add(ftd);
		}else if(Constant.USER_TYPE_CLASSMASTER.equals(userDto.getUserRealType())&&(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode()))){
			FuncTreeDto ftd = new FuncTreeDto();
			ftd.setFuncId("3060");
			ftd.setFuncName("高中报告册");
			ftd.setUrl("/reportBook/ReportBookAction.a");
			ofs.add(ftd);
		}*/
		
			return ofs;
		}else{
			List<FuncTreeDto> commonFuncs = homePageManagerExt.queryCommonFunc(userDto.getUserid(),roleId==null?userDto.getRoleId():roleId,funcLevelType);
			if(null!=commonFuncs && commonFuncs.size()>0){
				List<OFunc>newCommonFuncs = new ArrayList<OFunc>();
				for(FuncTreeDto ftd : commonFuncs){
					OFunc of = new OFunc();
					of.setFuncid(new BigDecimal(ftd.getFuncId()));
					of.setFuncname(ftd.getFuncName());
					of.setExecfilename(ftd.getUrl());
					of.setClickCount(1);
					newCommonFuncs.add(of);
				}
				homePageManagerExt.saveCommonMenuToRedis(userDto, newCommonFuncs);
			}
			return commonFuncs;
		}
	}
	
	
	/**
	 * 根据用户ID、角色ID
	 * 查询用户所拥有的菜单
	 * @param campuseId
	 * @param roleId
	 * @param termId
	 * @return
	 * @throws InterruptedException 
	 */
	public List<FuncTreeDto> queryUserFuncTree(String campuseId,String levelId,String roleId,HttpServletRequest req) throws InterruptedException{
		UserDto userDto = this.getLoginInfo(req);
		if(userDto==null||levelId==null||roleId==null)return null;
		//刷新UserDto
		Map<String,Map<UserRoleDto,Set<EdusysDto>>> dataMaps=(Map<String,Map<UserRoleDto,Set<EdusysDto>>>)req.getSession().getAttribute(Constant.TYPE_CAMPUSEID_ROLE_LEVELCODE);
		if(dataMaps==null)return null;
		Map<UserRoleDto,Set<EdusysDto>> roleListMap=dataMaps.get(campuseId);
		if(roleListMap==null)return null;
		UserRoleDto dto=new UserRoleDto();
		dto.setRoleId(roleId);
		Set<EdusysDto> edusysDtos=roleListMap.get(dto);
		if(edusysDtos==null||edusysDtos.size()<1)return null;
		for(EdusysDto eusysDto:edusysDtos){
			if(levelId.equals(eusysDto.getEdusysId())){
				userDto.setCampuseId(campuseId);
				userDto.setLevelcode(eusysDto.getLevelCode());
				userDto.setLevelid(eusysDto.getEdusysId());
				userDto.setRoleId(roleId);
				String roleRealType=userServiceExt.queryRoleRealType(roleId);
				userDto.setUserRealType(roleRealType);
				break;
			}
		}
		String levelCode=userDto.getLevelcode();
		
		Integer funcLevelType=null;
		
		//默认角色下的所有菜单
		if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelCode)){
			funcLevelType = 1;
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			funcLevelType = 3;
		}
		req.getSession().removeAttribute(Constant.KEY_LOGIN_USER);
		req.getSession().setAttribute(Constant.KEY_LOGIN_USER,userDto);
		if(Constant.DICT_TYPE_LEVELCODE_GZKGTR.equals(levelCode)){
			return userServiceExt.queryScoreTree();
		}else{
			return userServiceExt.queryAllFuncTree(userDto.getUserid(),roleId,funcLevelType,2);
		}
	}
	
	
	/**
	 * 根据用户ID、校区ID、角色ID
	 * @param campuseId
	 * @return
	 */
	public List<UserRoleDto> queryRoleNameAndLevelName(String campuseId,HttpServletRequest req){
		Map<String,Map<UserRoleDto,Set<EdusysDto>>> dataMaps=(Map<String,Map<UserRoleDto,Set<EdusysDto>>>)req.getSession().getAttribute(Constant.TYPE_CAMPUSEID_ROLE_LEVELCODE);
		if(dataMaps==null)return null;
		Map<UserRoleDto,Set<EdusysDto>> roleListMap=dataMaps.get(campuseId);
		if(roleListMap==null)return null;
		List<UserRoleDto> userRoleDtoes=new ArrayList<UserRoleDto>();
		userRoleDtoes.addAll(roleListMap.keySet());
		return userRoleDtoes;
	}
	public List<EdusysDto> queryEdusysDtoByCampuseIdAndRoleId(String campuseId,String roleId,HttpServletRequest req){
		if(NestUtil.isEmpty(campuseId)||NestUtil.isEmpty(roleId))return null;
		Map<String,Map<UserRoleDto,Set<EdusysDto>>> dataMaps=(Map<String,Map<UserRoleDto,Set<EdusysDto>>>)req.getSession().getAttribute(Constant.TYPE_CAMPUSEID_ROLE_LEVELCODE);
		UserDto user=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		if(dataMaps==null||user==null)return null;
		List<EdusysDto> edusys=new ArrayList<EdusysDto>();
		Map<UserRoleDto,Set<EdusysDto>> roleListMap=dataMaps.get(campuseId);
		if(roleListMap==null)return null;
		for(UserRoleDto dto:roleListMap.keySet()){
			if(roleId.equals(dto.getRoleId())){
				edusys.addAll(roleListMap.get(dto));
				break;
			}
		}
		return edusys;
	}
	public List<ClassDto> queryClassId(String gradeid,HttpServletRequest req){
		//获取登录用户信息
		userDto=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		String teacherid=null;
		//用户真实的类型
		String userRealType=userDto.getUserRealType();
		if(Constant.USER_TYPE_SCHOOLADMIN.equals(userRealType)){
			//教务老师
			teacherid=null;
		}else if(Constant.USER_TYPE_CLASSMASTER.equals(userRealType)){
			//班主任
			teacherid=userDto.getTeacherid();
		}
		List<String> classStrs=appraisalStaticsExt.queryClass(gradeid, teacherid);
		List<ClassDto> classDtos=new ArrayList<ClassDto>();
		if(classStrs!=null&&!classStrs.isEmpty()){
			for(String classStr:classStrs){
				if(NestUtil.isNotEmpty(classStr)){
					ClassDto dto=new ClassDto();
					dto.setClassId(classStr.split("_")[0]);
					dto.setClassName(classStr.split("_")[2]);
					classDtos.add(dto);
				}
			}
		}
		return classDtos;
	}
	public List<TermDto> queryTermDto(String selectClassid,HttpServletRequest req){
		//获取登录用户信息
		userDto=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		//学段代码
		String levelCode=userDto.getLevelcode();
		List<TermDto>terms=termServiceExt.queryHighSchoolTerms(selectClassid,levelCode);
		if(terms==null||terms.isEmpty())return null;
		Collections.reverse(terms);
		return terms;
	}
	public List<TermDto> queryTermDtoByGradeYear(String gradeyear,HttpServletRequest req){
		if(NestUtil.isEmpty(gradeyear))return null;
		//获取登录用户信息
		userDto=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		String levelcode=userDto.getLevelcode();
		Integer levelLength=3;
		if("2012002".equals(levelcode)){
			levelLength=4;
		}
		Integer gradeYearInt=Integer.parseInt(gradeyear)-levelLength;
		List<TermDto>terms=termServiceExt.queryHighSchoolTerms(gradeYearInt);
		if(terms==null||terms.isEmpty())return null;
		return terms;
	}
	public ITermServiceExt termServiceExt;
	
	public ITermServiceExt getTermServiceExt() {
		return termServiceExt;
	}
	public void setTermServiceExt(ITermServiceExt termServiceExt) {
		this.termServiceExt = termServiceExt;
	}
	public IAppraisalStaticsExt appraisalStaticsExt;
	
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public IAppraisalStaticsExt getAppraisalStaticsExt() {
		return appraisalStaticsExt;
	}
	public void setAppraisalStaticsExt(IAppraisalStaticsExt appraisalStaticsExt) {
		this.appraisalStaticsExt = appraisalStaticsExt;
	}
	public IOUserServiceExt getUserServiceExt() {
		return userServiceExt;
	}

	public void setUserServiceExt(IOUserServiceExt userServiceExt) {
		this.userServiceExt = userServiceExt;
	}

	public IHomePageManagerExt getHomePageManagerExt() {
		return homePageManagerExt;
	}

	public void setHomePageManagerExt(IHomePageManagerExt homePageManagerExt) {
		this.homePageManagerExt = homePageManagerExt;
	}

	public ILoginUserInfoServiceExt getLoginUserInfoServiceExt() {
		return loginUserInfoServiceExt;
	}

	public void setLoginUserInfoServiceExt(
			ILoginUserInfoServiceExt loginUserInfoServiceExt) {
		this.loginUserInfoServiceExt = loginUserInfoServiceExt;
	}
	private ICommonManagerExt commonManagerExt;
	
	public ICommonManagerExt getCommonManagerExt() {
		return commonManagerExt;
	}

	public void setCommonManagerExt(ICommonManagerExt commonManagerExt) {
		this.commonManagerExt = commonManagerExt;
	}
	public IWelcomeManagerExt getWelcomeManagerExt() {
		return welcomeManagerExt;
	}
	public void setWelcomeManagerExt(IWelcomeManagerExt welcomeManagerExt) {
		this.welcomeManagerExt = welcomeManagerExt;
	}
	
}
