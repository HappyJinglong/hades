package com.flyrish.hades.webapp.action.appraiseclaim;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.webapp.action.BaseAction;

public class AppraiseClaimAction extends BaseAction {
 
	String userType=null;
	String levelCode=null;
	
	@DefaultAction
	public Object toDefaultPage(HttpServletRequest req){
		
		UserDto userDto = this.getLoginInfo(req);
		if(null == userDto){
			return "/login.jsp";
		}
		
		HttpSession session = req.getSession(false);
		if(null != session){
			userDto=(UserDto) req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
			userType = userDto.getUsertype();
			levelCode = userDto.getLevelcode();
		}
		
		
		if(userType.equals(Constant.USER_KIND_SCHOOLTEACHER)||
				userType.equals(Constant.USER_KIND_SCHOOLSTUDENT)||
				userType.equals(Constant.USER_KIND_SCHOOLFAM)){
			
			if(Integer.parseInt(levelCode)==(Constant.DICT_TYPE_LEVELCODE_CZ)){
				
				return "tianxieyaoqiu_cz.jsp";
			}
			else if(Integer.parseInt(levelCode)==(Constant.DICT_TYPE_LEVELCODE_GZ) || Integer.parseInt(levelCode)==(Constant.DICT_TYPE_LEVELCODE_GZYK)){
				
				return "tianxieyaoqiu_gz.jsp";
			}
		}
			return null;
	}
}
