package com.flyrish.hades.webapp.action.fileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.CountryInfoDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class CityAndCountryAction extends BaseAction {
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	
	public List<CountryInfoDto> countryInfoDtos;//统计信息集合
	
	public String discode;
	
	@DefaultAction
	public Object defaultAction(HttpServletRequest req){
		//市级用户或区级用户
		userDto=getLoginInfo(req);
		discode=userDto.getDiscode();
		countryInfoDtos=baseInforManagerExt.queryCountryInfoDtoByDiscode(discode);
		return "cityandcountry.jsp";
	}
	
}
