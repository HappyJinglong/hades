package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.LoginUserDto;
import com.flyrish.hades.dto.StudentInfoDto;
import com.flyrish.hades.service.ext.IUserLoginServiceExt;
import com.flyrish.hades.util.DataSource;
import com.flyrish.hades.util.NoServiceUtil;

public class UserLoginServiceExt extends JdbcRootManager implements IUserLoginServiceExt {
	@DataSource("read")
	public LoginUserDto validUserIsExistInDb(String username, String password) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("username",NestUtil.isEmpty(username)?null:username.trim());
		params.put("password",NestUtil.isEmpty(password)?null:NoServiceUtil.md5(password.trim()));
		try{
			List<LoginUserDto> dtos=this.findList("UserLoginServiceExt.validUserIsExistInDb.query",params,new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					LoginUserDto dto=new LoginUserDto();
					dto.setSchoolname(rs.getString("schoolname"));
					dto.setUsertype(rs.getString("usertype"));
					dto.setDiscode(rs.getString("unitid"));
					return dto;
				}
			});
			if(dtos!=null&&dtos.size()>0){
				return dtos.get(0);
			}
		}catch(Exception e){
			logger.error("validUserIsExistInDb(String,String)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<LoginOUser> queryLoginOUserAll() {
		Map<String,Object>params=new HashMap<String,Object>();
		try{
			List<LoginOUser> dtos=this.findList("UserLoginServiceExt.queryLoginOUserAll.query",params,new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					LoginOUser dto=new LoginOUser();
					dto.setUserid(rs.getString("userid"));
					dto.setUsername(rs.getString("username"));
					dto.setPwd(rs.getString("pwd"));
					dto.setUsertype(rs.getString("usertype"));
					dto.setName(rs.getString("name"));
					dto.setCardsort(rs.getString("cardsort"));
					dto.setCardid(rs.getString("cardid"));
					dto.setUnitname(rs.getString("unitname"));
					dto.setTelephone(rs.getString("telephone"));
					dto.setUnitid(rs.getString("unitid"));
					dto.setPersonid(rs.getString("personid"));
					dto.setPrimaryschool(rs.getString("primaryschool"));
					dto.setMiddleschool(rs.getString("middleschool"));
					dto.setHighschool(rs.getString("highschool"));
					dto.setUsed(rs.getString("used"));
					dto.setPwd_show(rs.getString("pwd_show"));
					dto.setCount(rs.getString("cot"));
					dto.setSystemtype(rs.getString("systemtype"));
					return dto;
				}
			});
			return dtos;
		}catch(Exception e){
			logger.error("queryLoginOUserAll()",e);
		}
		return null;
	}
	public List<StudentInfoDto> queryStudentInfosAll() {
		Map<String,Object>params=new HashMap<String,Object>();
		try{
			List<StudentInfoDto> dtos=this.findList("UserLoginServiceExt.queryStudentInfosAll.query",params,new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentInfoDto dto=new StudentInfoDto();
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setBirthday(rs.getString("birthday"));
					dto.setCardid(rs.getString("cardid"));
					dto.setCmis30id(rs.getString("cmis30id"));
					dto.setSex(rs.getString("sex"));
					dto.setStudentName(rs.getString("name"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
			return dtos;
		}catch(Exception e){
			logger.error("queryStudentInfosAll()",e);
		}
		return null;
	}
	
}
