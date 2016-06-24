package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.dto.UserDto;

public interface IHomePageManagerExt {

	/**
	 * 常用功能查询
	 * @param userid	用户ID
	 * @param roleId	角色ID
	 * @return
	 */
	List<FuncTreeDto> queryCommonFunc(String userId, String roleId,Integer funcLevelType);

	/**
	 * 从缓存中获取常用功能菜单
	 * @param userDto
	 * @param funcLevelType
	 * @return
	 */
	List<OFunc>queryCommonFuncFromRedis(UserDto userDto,Integer funcLevelType);
	/**
	 * 将常用功能菜单放入redis
	 * @param userDto
	 * @param commonFuncs
	 */
	void saveCommonMenuToRedis(UserDto userDto,List<OFunc> commonFuncs);
	/**
	 * 
	 * @param userDto
	 * @param commonFuncs
	 */
	boolean saveOrUpdateCommonMenuToRedis(UserDto userDto,String commonFuncId,Integer funcLevelType,String funcMessg);
	/**
	 * 将所有的常用功能查询放入redis
	 * @return
	 */
	List<String> queryAllCommonMenusToRedis();
}
