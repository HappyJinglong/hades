package com.flyrish.hades.webapp.action.selfappraise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;

import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.webapp.action.BaseAction;

public class ZWPJAtcion extends BaseAction{
public String levelcode;
public String commonFuncId;
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:String.valueOf(levelCode);
		request.setAttribute("commonFuncId", commonFuncId);
			return "frame_ziwopingjia.jsp";
	}
}
