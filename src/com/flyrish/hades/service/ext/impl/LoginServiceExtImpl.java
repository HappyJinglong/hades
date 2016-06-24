package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.addons.spring.Spring;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.InValidInsertException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ILoginServiceExt;
import com.flyrish.hades.util.DataSource;

public class LoginServiceExtImpl extends JdbcRootManager implements ILoginServiceExt{

	public boolean addUser(String username,String password) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username",username);
		params.put("passwod",password);
		try{
			ISqlElement sqlElement=this.processSql(params,"LoginServiceExtImpl.addUser.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("addUser(String,String)",e);
			if(e.getMessage().contains(Constant.MISSING_ONLY_MSG)){
				throw new InValidInsertException();
			}else{
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
	}
	@DataSource("read")
	public UserDto queryUserByUserName(String username) {
		if(NestUtil.isEmpty(username))return null;
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("username",username);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"LoginServiceExtImpl.queryUserByUserName.query");
			logger.info("queryUserByUserName(String) "+sqlElement.getSql()+":::"+sqlElement.getParams());
			List<UserDto> userDtos=this.findList("LoginServiceExtImpl.queryUserByUserName.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto=new UserDto();
					dto.setUsername(rs.getString("username"));
					dto.setPassword(rs.getString("pwd"));
					return dto;
				}
			});
			if(userDtos!=null&&userDtos.size()>0)
				return userDtos.get(0);
		}catch(Exception e){
			logger.error("queryUserByUserName(String)",e);
			//查询方法不用抛出异常
		}
		return null;
	}

}
