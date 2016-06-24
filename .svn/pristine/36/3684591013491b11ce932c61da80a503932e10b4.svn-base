package com.flyrish.hades.webapp.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.UserDto;

public abstract class BaseAction {
	protected Logger logger=Logger.getLogger(this.getClass());
	//回显操作成功信息字段
	public String message;
	//回显错误信息字段
	public String error;
	
	public UserDto userDto;
	public String  isStartAppraiseCache;
	@Spring
	public ConstantBean constantBean;
	/**
	 * 获取登录用户的Session对象
	 * @param req
	 * @return
	 */
	public UserDto getLoginInfo(HttpServletRequest req)
	{
		userDto=(UserDto)req.getSession().getAttribute(Constant.KEY_LOGIN_USER);
		return userDto;
	}
	
}
