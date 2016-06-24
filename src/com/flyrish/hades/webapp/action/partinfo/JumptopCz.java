package com.flyrish.hades.webapp.action.partinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.annotation.DefaultAction;

public class JumptopCz {
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		return "/selfappraise/cz_toptree.jsp";
	}
}
