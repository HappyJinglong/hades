package com.flyrish.hades.webapp.action.selfappraise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;

import com.flyrish.hades.webapp.action.BaseAction;

public class Jump extends BaseAction{
	public String levelcode;
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:String.valueOf(levelCode);
		if("2012003".equals(levelcode)||"2012004".equals(levelcode)){
			return "/selfappraise/SelfAppAction.a?choicenum=1001";
		}else if("2012002".equals(levelcode)){
			return "/partinfo/PartinfoAction.a?choicenum=1001";
		}else{
			return "/login.jsp";
		}
	}
}
