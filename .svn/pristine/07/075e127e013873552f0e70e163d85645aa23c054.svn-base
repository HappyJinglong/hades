package com.flyrish.hades.service.ext;

import com.flyrish.hades.dto.UserDto;

public interface ILoginServiceExt {
	/**
	 * 添加用户信息
	 * @param user 用户实体类
	 * @return 如果添加成功则返回true,反之则抛出异常
	 */
	boolean addUser(String username,String password);
	/**
	 * 通过用户名查找相应的用户
	 * @param username 用户名
	 * @return UserDto 对应的用户实体类，如果没有查找到相应的用户，则返回NULL
	 */
	UserDto queryUserByUserName(String username);
}
