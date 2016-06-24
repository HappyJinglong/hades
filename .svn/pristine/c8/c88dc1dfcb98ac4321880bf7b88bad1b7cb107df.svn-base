package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.util.DataSource;
import com.flyrish.hades.util.NoServiceUtil;

public class LoginUserInfoServiceExtImpl extends JdbcRootManager implements ILoginUserInfoServiceExt {

	@DataSource("read")
	public List<TermDto> queryTerm() {
		
		try {
			List<TermDto> termDtos=this.findList("LoginUserInfoServiceExtImpl.queryTerm.query", new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					TermDto dto = new TermDto();
					dto.setUsed(rs.getInt("used"));
					dto.setTermid(rs.getInt("termid"));
					dto.setTermtype(rs.getInt("termtype"));
					dto.setTermname(rs.getString("termname"));
					return dto;
				}
			});
			return termDtos;
		} catch (Exception e){
			logger.error("queryTerm()",e);
		}
		return null;
	}

	@DataSource("read")
	public List<UserSchoolDto> queryUserSchool(Integer userid) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid", userid);
		
		try {
			List<UserSchoolDto> userSchoolDtoes = this.findList("LoginUserInfoServiceExtImpl.queryUserSchool.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserSchoolDto dto = new UserSchoolDto();
					dto.setCampusid(rs.getInt("campusid"));
					dto.setSchoolName(rs.getString("schoolName"));
					return dto;
				}
			});
			return userSchoolDtoes;
		}catch (Exception e) {
			logger.error("queryUserSchool(Integer)",e);
		}
		return null;
	}

	@DataSource("read")
	public List<UserSchoolDto> queryUserSchoolCampus(String userid) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid", userid);		
		try {
			List<UserSchoolDto> userCampusDtoes = this.findList("LoginUserInfoServiceExtImpl.queryUserSchoolCampus.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserSchoolDto dto = new UserSchoolDto();
					dto.setName(rs.getString("name"));
					dto.setCampusid(rs.getInt("campusid"));
					dto.setCampusno(rs.getString("campusno"));
					dto.setSchoolName(rs.getString("schoolName"));
					return dto;
				}
			});
			return userCampusDtoes;
		}catch (Exception e) {
			logger.error("queryUserSchoolCampus(Integer)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<Map<String,String>> queryListRoleTypes(String userid,String username, String cmis30id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username", username);
		params.put("cmis30id", cmis30id);
		params.put("userid",userid);
		try {
			List<Map<String,String>> roletypes = this.findList("LoginUserInfoServiceExtImpl.queryListRoleTypes.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> dto=new HashMap<String,String>();
					dto.put("roletype",rs.getString("roletype"));
					dto.put("roleid",rs.getString("roleid"));
					dto.put("campusid",rs.getString("campusid"));
					dto.put("rolename",rs.getString("rolename"));
					return dto;
				}
			});
			return roletypes;
		}catch (Exception e) {
			logger.error("queryListRoleTypes(String,String)",e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<UserDto> queryUserLevelCode(String userId, String userName) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		params.put("userName",userName);
		
		try {
			List<UserDto> userDto = this.findList("LoginUserInfoServiceExtImpl.queryUserLevelCode.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto = new UserDto();
					/*dto.setLevelcode(rs.getInt("levelcode"));
					dto.setUsertype(rs.getInt("usertype"));*/
					return dto;
				}
			}); 
			return userDto;
		} catch (Exception e) {
			logger.error("queryUserLevelCode(Integer)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<String> queryUserNameCount(String userName) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username",userName);
		try {
			List<String> cmis30ids = this.findList("LoginUserInfoServiceExtImpl.queryUserNameCount.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString(1);
				}
			});
			return cmis30ids;
		} catch (Exception e) {
			logger.error("queryUserNameCount(String)",e);
		}
		return null;
	}
	public boolean updatePwd(String userId, String newPwd) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("newPwd", NoServiceUtil.md5(newPwd));
		
		
		try {
			ISqlElement sqlElement=this.processSql(params,"LoginUserInfoServiceExtImpl.updatePwd.update");
			this.getJdbcTemplate().update(sqlElement.getSql(), sqlElement.getParams());
			return true;
		} catch (Exception e) {
			logger.error("updatePwd(Integer,String)", e);
			throw new ManagerException(e);
		}
		
	}

	@DataSource("read")
	public List<UserRoleDto> queryRoleNameAndLevelName(
			Integer unitlevel, Integer userId, Integer campuseId,final Integer roleId,Integer roleType,Integer levelCode) {
		
		/*Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitlevel",unitlevel );
		params.put("userId",userId );
		params.put("campuseId",campuseId );
		params.put("roleId", roleId);
		params.put("roleType", roleType);
		params.put("levelCode", levelCode);
		
		try {
			List<UserRoleDto> dto = this.findList("LoginUserInfoServiceExtImpl.queryRoleNameAndLevelName.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserRoleDto dto = new UserRoleDto();
					if(roleId==null){
						dto.setRoleId(rs.getInt("roleId"));
						dto.setRoleName(rs.getString("roleName"));
					}else{
						dto.setLevelId(rs.getInt("levelId"));
						dto.setLevelName(rs.getString("levelName"));
					}
					return dto;
				}
			});
			return dto;
		} catch (Exception e) {
			logger.error("queryRoleNameAndLevelName(Integer,Integer,Integer,Integer)",e);
		}*/
		return null;
	}
	
	@DataSource("read")
	public List<UserDto> queryUserLevelCodeByLevelId(Integer levelId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("levelId",levelId );
		
		try {
			List<UserDto> userDto = this.findList("LoginUserInfoServiceExtImpl.queryUserLevelCodeByLevelId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto = new UserDto();
					//dto.setLevelcode(rs.getInt("levelcode"));
					return dto;
				}
			});
			return userDto;
		} catch (Exception e) {
			logger.error("queryUserLevelCodeByLevelId(Integer)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<Map<String,String>> queryCampuseIdBySchoolAdmin(String username, String cmis30id,List<String>campuseids) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username",username);
		params.put("cmis30id",cmis30id);
		params.put("campuseids",campuseids);
		try {
			List<Map<String,String>> roleidcampuseids = this.findList("LoginUserInfoServiceExtImpl.queryCampuseIdBySchoolAdmin.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String>data=new HashMap<String,String>();
					data.put("campusid",rs.getString("campusid"));
					data.put("levelcode",rs.getString("levelcode"));
					data.put("levelid",rs.getString("levelid"));
					data.put("levelname", rs.getString("levelname"));
					return data;
				}
			});
				return roleidcampuseids;
		} catch (Exception e) {
			logger.error("queryCampuseIdBySchoolAdmin(String,String,List<String>)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<UserRoleDto> queryUserRolesByUserIdAndCampusId(String userId,
			String campusId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid",userId);
		params.put("campusid",campusId);
		try {
			List<UserRoleDto> userRoleDtos = this.findList("LoginUserInfoServiceExtImpl.queryUserRolesByUserIdAndCampusId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserRoleDto dto=new UserRoleDto();
					dto.setRoleId(rs.getString("roleid"));
					dto.setRoleName(rs.getString("rolename"));
					dto.setRoleType(rs.getString("roletype"));
					return dto;
				}
			});
			return userRoleDtos;
		} catch (Exception e) {
			logger.error("queryUserRolesByUserIdAndCampusId(String,String)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<EdusysDto> queryEdusysByCampuseId(String campuseId,String cmis30id,String discode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("campuseid",campuseId);
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		try {
			List<EdusysDto> edusysDtos = this.findList("LoginUserInfoServiceExtImpl.queryEdusysByCampuseId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					EdusysDto dto=new EdusysDto();
					dto.setLevelCode(rs.getString("levelcode"));
					dto.setEdusysId(rs.getString("levelid"));
					dto.setEdusysName(rs.getString("levelname"));
					return dto;
				}
			});
			return edusysDtos;
		} catch (Exception e) {
			logger.error("queryEdusysByCampuseId(String,String,String)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<EdusysDto> queryEdusysByPersionIdForMaster(String campuseId,String personid,
			String cmis30id, String discode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personid",personid);
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("campuseid",campuseId);
		try {
			List<EdusysDto> edusysDtos = this.findList("LoginUserInfoServiceExtImpl.queryEdusysByPersionIdForMaster.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					EdusysDto dto=new EdusysDto();
					dto.setLevelCode(rs.getString("levelcode"));
					dto.setEdusysId(rs.getString("levelid"));
					dto.setEdusysName(rs.getString("levelname"));
					return dto;
				}
			});
			return edusysDtos;
		} catch (Exception e) {
			logger.error("queryEdusysByPersionIdForMaster(String,String,String)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<EdusysDto> queryEdusysByPersionIdForCourse(String campuseId,String personid,
			String cmis30id, String discode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personid",personid);
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("campuseid",campuseId);
		try {
			List<EdusysDto> edusysDtos = this.findList("LoginUserInfoServiceExtImpl.queryEdusysByPersionIdForCourse.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					EdusysDto dto=new EdusysDto();
					dto.setLevelCode(rs.getString("levelcode"));
					dto.setEdusysId(rs.getString("levelid"));
					dto.setEdusysName(rs.getString("levelname"));
					return dto;
				}
			});
			return edusysDtos;
		} catch (Exception e) {
			logger.error("queryEdusysByPersionIdForCourse(String,String,String)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<Map<String,String>> queryCampuseIdBySchoolTeacher(String teacherid,String username,
			String cmis30id, String sql,List<String>campuseids) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username",username);
		params.put("cmis30id",cmis30id);
		params.put("campuseids",campuseids);
		params.put("teacherid",teacherid);
		try {
			ISqlElement sqlDemo=this.processSql(params, sql);
			List<Map<String,String>> campuseidList = this.findList(sql, params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String>data=new HashMap<String,String>();
					data.put("campusid",rs.getString("campusid"));
					data.put("levelcode",rs.getString("levelcode"));
					data.put("levelid",rs.getString("levelid"));
					data.put("levelname", rs.getString("levelname"));
					return data;
				}
			});
				return campuseidList;
		} catch (Exception e) {
			logger.error("queryCampuseIdBySchoolAdmin(String,String,List<String>)",e);
		}
		return null;
	}

	@DataSource("read")
	public List<UserDto> queryClassidByEduid(String eduid, String cmis30id,String discode) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("edu_id",eduid);
		params.put("discode",discode);
		params.put("cmis30id",cmis30id);
		
		try {
			List<UserDto> userDto = this.findList("LoginUserInfoServiceExtImpl.queryClassidByEduid.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto=new UserDto();
					
					dto.setClassid(rs.getString("classid"));
					return dto;
				}
			});
			return userDto;
		} catch (Exception e) {
			logger.error("queryClassidByEduid(String,String,String)",e);
		}
		return null;
		
	}

	@DataSource("read")
	public boolean queryCampuseIdBySchoolTeacher2(String username,
			String cmis30id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username",username);
		params.put("cmis30id",cmis30id);
		try {
			List<Integer> counts = this.findList("LoginUserInfoServiceExtImpl.queryCampuseIdBySchoolTeacher2.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getInt(1);
				}
			});
			if(counts!=null&&counts.get(0)>0)
				return true;
		} catch (Exception e) {
			logger.error("queryCampuseIdBySchoolTeacher2(String,String)",e);
		}
		return false;
	}


}
