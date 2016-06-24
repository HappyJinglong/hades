package com.flyrish.hades.service.ext;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public interface IUserDetailsServiceExt {

	/**
	 * 获取用户信息
	 * @param username 登录用户名
	 * @param password 登录密码
	 * @param cmis30id 学校标识号
	 * @param usertype 用户类型
	 * @param userid 用户标识号
	 * @param systemtype 系统标识号 2表示公用，3表示综素用户
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws DataAccessException
	 */
	public UserDetails loadUserByUsername(String username ,String password, String cmis30id,String usertype,String userid,String systemtype)
			throws UsernameNotFoundException, DataAccessException;
}
