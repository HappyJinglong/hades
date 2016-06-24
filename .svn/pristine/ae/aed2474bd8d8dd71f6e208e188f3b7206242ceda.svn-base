package com.flyrish.hades.redistest;

import java.util.List;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.IUserLoginServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class TestAction extends BaseAction {
	@Spring
	private IUserLoginServiceExt userLoginServiceExt;
	@Spring
	public IRedisServiceExt redisServiceExt;
	
	@DefaultAction
	public Object defaultAction(){
		/*List<LoginOUser> loginOUsers=userLoginServiceExt.queryLoginOUserAll();
		for(LoginOUser loginOUser:loginOUsers){
			try {
				redisServiceExt.save(loginOUser.getUsername(), loginOUser);
			} catch (ForceException e) {
				e.printStackTrace();
			}
		}*/
		
		return null;
	}
}
