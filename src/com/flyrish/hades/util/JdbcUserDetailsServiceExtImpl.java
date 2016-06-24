package com.flyrish.hades.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.flyrish.hades.service.ext.IUserDetailsServiceExt;


@SuppressWarnings("unchecked")
public class JdbcUserDetailsServiceExtImpl extends JdbcDaoSupport implements
IUserDetailsServiceExt{

	public UserDetails loadUserByUsername(String username, String password,
			String cmis30id, String usertype, String userid, String systemtype)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
