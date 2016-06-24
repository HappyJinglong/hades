package com.flyrish.hades.webapp.action.dwr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.data.Json;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.SchoolInfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.BaseAction;

public class AdminRoleSelect extends BaseAction{

	private IOUserServiceExt userServiceExt;
	private ILoginUserInfoServiceExt loginUserInfoServiceExt;
	private IRedisServiceExt redisServiceExt;
	
	
	
	public List<SchoolInfoDto> querySchoolInfoDtoByUserName(String userName,String passWord,HttpServletRequest req)
	{
		//List<SchoolInfoDto> schoolList=redisServiceExt.readSingle(userName+"schoolList");
		List<SchoolInfoDto> schoolList=(List<SchoolInfoDto>)req.getSession().getAttribute(userName+"schoolList");
		return schoolList;
	}
	public Boolean checkUserSchoolCount(String userName,String passWord,HttpServletRequest req){
		try{
			List<String> cmis30ids=loginUserInfoServiceExt.queryUserNameCount(userName);
			if(cmis30ids!=null&&cmis30ids.size()>1){
				List<SchoolInfoDto> schoolList = userServiceExt.querySchoolInfoDtoByUserName(cmis30ids);
				if(null != schoolList && schoolList.size() > 1){
					if(schoolList.size() > 1){
							//redisServiceExt.save(userName+"schoolList",schoolList);
						req.getSession().setAttribute(userName+"schoolList", schoolList);
						return false;
					}else{
						return true;
					}
				}
			}
			return true;
		}catch(Exception e){
			logger.error("checkUserSchoolCount(String,String,HttpServletRequest)",e);
			return null;
		}
	}
	
	public IOUserServiceExt getUserServiceExt() {
		return userServiceExt;
	}

	public void setUserServiceExt(IOUserServiceExt userServiceExt) {
		this.userServiceExt = userServiceExt;
	}
	

	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public ILoginUserInfoServiceExt getLoginUserInfoServiceExt() {
		return loginUserInfoServiceExt;
	}
	
	public void setLoginUserInfoServiceExt(
			ILoginUserInfoServiceExt loginUserInfoServiceExt) {
		this.loginUserInfoServiceExt = loginUserInfoServiceExt;
	}
	
}
