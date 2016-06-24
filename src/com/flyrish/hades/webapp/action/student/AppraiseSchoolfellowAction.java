package com.flyrish.hades.webapp.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.nestframework.annotation.DefaultAction;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.webapp.action.BaseAction;
public class AppraiseSchoolfellowAction extends BaseAction{

	//判断登录学生学段
	@DefaultAction
	public Object defaultAction(HttpServletRequest req){
		try { 
			this.getLoginInfo(req);
			String levelCode =userDto.getLevelcode();
			if(levelCode!=null){
				     if("2012002".equals(levelCode)){ 
						    return "czhit.jsp";	
				    }
				    if("2012003".equals(levelCode)||"2012004".equals(levelCode)){
				    	return "hit.jsp";
				    }
		         }else{
		        	 return "/longin.jsp";
		         }
			   return null;
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	

}
