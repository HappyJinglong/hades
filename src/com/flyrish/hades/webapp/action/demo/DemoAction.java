package com.flyrish.hades.webapp.action.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;

import com.flyrish.hades.exception.InValidInsertException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ILoginServiceExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class DemoAction extends BaseAction{
	
	public String username="admin";//用于接收Jsp页面传递过来的值
	public String pwd;//用户密码
	@Spring
	public ILoginServiceExt loginServiceExt;
	@DefaultAction
	public Object defaultAction(){
		//查询
		/*UserDto dto=loginServiceExt.queryUserByUserName(username);
		username=dto.getUsername();
		pwd=dto.getPassword();*/
		logger.info("this is test message");
		return "login.jsp";
	}
	public Object saveUserInfo(HttpServletRequest request,HttpServletResponse response){
		try{
			loginServiceExt.addUser("zhangsan","123");
		}catch (InValidInsertException e) {
			logger.error("saveUserInfo(HttpServletRequest,HttpServletResponse)",e);
		}catch(ManagerException ex){
			logger.error("saveUserInfo(HttpServletRequest,HttpServletResponse)",ex);
		}
		return "login.jsp";
	}
	@Json
	public Object queryData(){
		//把北京地区放在集合最前面
		List<String>listStr=new ArrayList<String>();
		for(int i=0;i<50;i++){
			Map<String,Object>maps=new HashMap<String,Object>();
			maps.put("id",i+"::"+i);
			maps.put("name","name"+i);
			listStr.add(Utility.createJsonStr(maps));
		}
		return JSONObject.fromObject("{val:"+Utility.createJsonStr(listStr)+"}");
	}
}
