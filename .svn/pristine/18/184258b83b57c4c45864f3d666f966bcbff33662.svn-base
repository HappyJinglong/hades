package com.flyrish.hades.webapp.action.fileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CountryInfoDto;
import com.flyrish.hades.dto.LoginUserDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IUserLoginServiceExt;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class LoginFileUploadAction extends NginxUploadAction{
	//用户名、密码
	public String username;
	public String password;
	
	public String discode;
	//学校名称
	public String schoolname;
	@Spring
	public IUserLoginServiceExt userLoginServiceExt;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	
	public List<CountryInfoDto> countryInfoDtos;//统计信息集合
	
	private String userType;//用户类型
	
	
	@DefaultAction
	public Object toLogin(){
		return "/login.jsp";
	}
	public Object onLogin(HttpServletRequest request){
		
		LoginUserDto dto=userLoginServiceExt.validUserIsExistInDb(username, password);
		schoolname=dto==null?null:dto.getSchoolname();
		userType=dto==null?null:dto.getUsertype();
		if(NestUtil.isEmpty(schoolname)
				&&Constant.USER_KIND_SCHOOLGROUP.equals(userType)){
			error="用户名或密码错误，登录失败";
			return "/login.jsp";
		}
		request.getSession().setAttribute("user",dto);
		//如果是校级用户
		if(Constant.USER_KIND_SCHOOLGROUP.equals(userType)){
			return "import.jsp";
		}else if(Constant.USER_KIND_CITY.equals(userType)||Constant.USER_KIND_COUNTY.equals(userType)){
			//市级用户或区级用户
			discode=dto.getDiscode();
			countryInfoDtos=baseInforManagerExt.queryCountryInfoDtoByDiscode(discode);
			return "cityandcountry.jsp";
		}else{
			error="用户名或密码错误，登录失败";
			return "/login.jsp";
		}
	}
	public Object loginOut(HttpServletRequest request){
		request.getSession().setAttribute("user",null);
		return "/login.jsp";
	}
}
