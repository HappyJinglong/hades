package com.flyrish.hades.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FuncsCheckFilter implements Filter{

	

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain f) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (!doCheckFuncs(req, res)) {
			doFuncMissing(req, res, "error.html");
			return;
		}

		f.doFilter(request, response);
	}

	
	public void doFuncMissing(HttpServletRequest req, HttpServletResponse res,
			String filename) throws IOException {
		try {
			String l = req.getRequestURL().toString();
			String url = l.substring(0, l.indexOf("hades") + 5) + File.separator
					+ filename;
			res.sendRedirect(url);
		} catch (Exception e) {
			res.sendRedirect(req.getRequestURL().toString() + File.separator
					+ filename);
		}
	}

	/*public boolean checkHasFuncsByCurrentUser(LoginUserInfo login,
			String specialFuncid) {
		if (login == null || login.getFuncs() == null
				|| StringUtil.isEmpty(specialFuncid))
			return false;
		return login.getFuncs().contains(specialFuncid);
	}*/

	public boolean doCheckFuncs(HttpServletRequest req, HttpServletResponse res) {
		// 校验权限
//		try {
//			LoginUserInfo login = (LoginUserInfo) req.getSession()
//					.getAttribute(Constant.KEY_LOGININFO_USER);
//			String uri = req.getRequestURI();
//			if (uri.contains("UserManagementAction.a")) {
//				/*if (!checkHasFuncsByCurrentUser(login,
//						Constant.KEY_FUNCTIONID_1)) {
//					return false;
//				}*/
//			}
//			else if(uri.contains("InfosMaintanceAction.a")){
//				/*if (!checkHasFuncsByCurrentUser(login,
//				Constant.KEY_STUDENTINFO_EDIT)) {
//					return false;
//				}*/
//			}
//
//		} catch (Exception e) {
//			return false;
//		}
		return true;
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
