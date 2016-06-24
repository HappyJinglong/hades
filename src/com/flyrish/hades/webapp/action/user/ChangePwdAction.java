package com.flyrish.hades.webapp.action.user;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.core.BeanContext;

import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.BaseAction;
import com.nazca.util.StringUtil;

public class ChangePwdAction extends BaseAction {

	@Spring
	ILoginUserInfoServiceExt loginUserInfoServiceExt;
	
	//旧密码
	public String oldPwd;
	//新密码
	public String newPwd;
	//确认新密码
	public String rNewPwd;
	
	
	@DefaultAction
	public Object toDefaultPage(HttpServletRequest req){
		
		UserDto userDto = this.getLoginInfo(req);
		if(null == userDto){
			return "/login.jsp";
		}
		return "/user/change_pwd.jsp";
	}
	
	@SuppressWarnings("unused")
	public Object doChangePwd(HttpServletRequest req,BeanContext bc){
		
		UserDto userDto = this.getLoginInfo(req);
		String userId = userDto.getUserid();
		if(userDto == null){
			return "/login.jsp";
		}
		
		if(StringUtil.isEmpty(oldPwd)){
			error = "原密码不能为空";
			return toDefaultPage(req);
		}else{
			if( !userDto.getPassword().equals(NoServiceUtil.md5(oldPwd))){
				error = "原密码不正确";
				return toDefaultPage(req);
			}
		}
		if(StringUtil.isEmpty(newPwd)){
			this.error = bc.getLocaleMessage("ChangePwdAction.doChangePwd.oldpwd.exist.error");
			return toDefaultPage(req);
		}
		if(StringUtil.isEmpty(newPwd)){
			this.error = bc.getLocaleMessage("ChangePwdAction.doChangePwd.newpwd.exist.error");
			return toDefaultPage(req);
		}
		if(!newPwd.equals(rNewPwd)){
			this.error = bc.getLocaleMessage("ChangePwdAction.doChangePwd.newpwd.input.error");
			return toDefaultPage(req);
		}
		
		userDto.setPassword(NoServiceUtil.md5(newPwd));
//		userDto.setPwdShow(newPwd);
		try {
			loginUserInfoServiceExt.updatePwd(userId,newPwd);
			this.message ="修改成功！";
		} catch (Exception e) {
			logger.error("doChangePwd(HttpServletRequest,BeanContext)");
			this.error = "原始密码错误  修改密码失败";
		}
		return toDefaultPage(req);
		
	}
}
