package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.SchoolInfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.exception.CannotGetJdbcConnectionException;
import com.flyrish.hades.exception.UserNameException;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.DataSource;

public class OUserServiceExtImpl extends JdbcRootManager implements IOUserServiceExt{

	private IRedisServiceExt redisServiceExt;
	
	@DataSource("read")
	public UserDto findLoginUserByUserName(String username, String password,String schoolUserId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username", username);
		params.put("unitid", schoolUserId);
		try {
			//先从缓存里读取用户信息
			//List<LoginOUser> loginOUsers=redisServiceExt.readList(username+Constant.R_REDIS_OUSER);
			List<LoginOUser> loginOUsers=null;
			if(loginOUsers==null||loginOUsers.isEmpty()){
				//如果缓存中没有相应的用户，则直接走数据库
				List<UserDto> userDtos=this.findList("OUserServiceExtImpl.findLoginUserByUserName.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						UserDto dto=new UserDto();
						dto.setUsername(rs.getString("username"));
						dto.setUsed(rs.getString("used"));
						dto.setCmis30id(rs.getString("unitid"));
						dto.setUnitid(rs.getString("unitid"));
						dto.setUserid(rs.getString("userid"));
						dto.setUsertype(rs.getString("usertype"));
						dto.setPersonid(rs.getString("personid"));
						dto.setUsed(rs.getString("used"));
						dto.setRoleId(rs.getString("roleid"));
						dto.setUserRealType(rs.getString("roletype"));
						dto.setSystemtype(rs.getString("systemtype"));
						return dto;
					}
				});
				if(userDtos!=null&&userDtos.size()>0)
					return userDtos.get(0);
			}else{
				//查找出对应学校的用户数据
				LoginOUser loginOUser=queryLoginOUserByConditon(schoolUserId,loginOUsers);
				if(loginOUser==null){
					//判断用户是否在数据库中存在
					List<UserDto> users = this.findList("OUserServiceExtImpl.findLoginUserByUserName.query2", params, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							UserDto dto=new UserDto();
							dto.setUsername(rs.getString("username"));
							dto.setUsed(rs.getString("used"));
							dto.setCmis30id(rs.getString("unitid"));
							dto.setUnitid(rs.getString("unitid"));
							dto.setUserid(rs.getString("userid"));
							dto.setUsertype(rs.getString("usertype"));
							dto.setPersonid(rs.getString("personid"));
							dto.setUsed(rs.getString("used"));
							dto.setSystemtype(rs.getString("systemtype"));
							dto.setUserid(rs.getString("userid"));
						return dto;
						}
					});
					if(users == null || users.size() <= 0){
							throw new UserNameException("user is not exsit");
					}else{
						params.put("userid",users.get(0).getUserid());
						//判断该用户是否存在角色
						List<UserRoleDto> serRoleDtos=this.findList("OUserServiceExtImpl.findLoginUserByUserName.queryrole", params, new RowMapper() {
							public Object mapRow(ResultSet rs, int arg1) throws SQLException {
								UserRoleDto dto=new UserRoleDto();
								dto.setRoleId(rs.getString("roleid"));
								dto.setRealRoletype(rs.getString("roletype"));
								return dto;
							}
						});
						if(serRoleDtos!=null&&serRoleDtos.size()>0){
							UserRoleDto roledto=serRoleDtos.get(0);
							UserDto userDto=new UserDto();
								userDto.setUsername(users.get(0).getUsername());
								userDto.setUsed(users.get(0).getUsed());
								userDto.setUserid(users.get(0).getUserid());
								userDto.setUsertype(users.get(0).getUsertype());
								userDto.setPersonid(users.get(0).getPersonid());
								userDto.setUsed(users.get(0).getUsed());
								userDto.setRoleId(roledto.getRoleId());
								userDto.setUserRealType(roledto.getRealRoletype());
								userDto.setCmis30id(users.get(0).getUnitid());
								userDto.setUnitid(users.get(0).getUnitid());
								userDto.setSystemtype(users.get(0).getSystemtype());
							return userDto;
						}
					}
				}else{
					params.put("userid",loginOUser.getUserid());
					List<UserRoleDto> serRoleDtos=this.findList("OUserServiceExtImpl.findLoginUserByUserName.queryrole", params, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							UserRoleDto dto=new UserRoleDto();
							dto.setRoleId(rs.getString("roleid"));
							dto.setRealRoletype(rs.getString("roletype"));
							return dto;
						}
					});
					if(serRoleDtos!=null&&serRoleDtos.size()>0){
						UserRoleDto roledto=serRoleDtos.get(0);
						UserDto userDto=new UserDto();
							userDto.setUsername(loginOUser.getUsername());
							userDto.setUsed(loginOUser.getUsed());
							userDto.setUserid(loginOUser.getUserid());
							userDto.setUsertype(loginOUser.getUsertype());
							userDto.setPersonid(loginOUser.getPersonid());
							userDto.setUsed(loginOUser.getUsed());
							userDto.setRoleId(roledto.getRoleId());
							userDto.setUserRealType(roledto.getRealRoletype());
							userDto.setCmis30id(loginOUser.getUnitid());
							userDto.setUnitid(loginOUser.getUnitid());
							userDto.setSystemtype(loginOUser.getSystemtype());
						return userDto;
					}
				}
			}
		}catch (UserNameException e) {
			throw e;
		} 
		catch (Exception e) {
			logger.error("findLoginUserByUserName(String,String,String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
		return null;
	}
	public LoginOUser queryLoginOUserByConditon(String schoolUserId,List<LoginOUser> loginOUsers){
		if(loginOUsers==null||loginOUsers.isEmpty())return null;
		if(NestUtil.isEmpty(schoolUserId)) return loginOUsers.get(0);
		for(LoginOUser loginOUser:loginOUsers){
			if(schoolUserId.equals(loginOUser.getUnitid()))
				return loginOUser;
		}
		return null;
	}
	@DataSource("read")
	public Map<String,String> queryCurrentTermid() {
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			List<Map<String,String>> termids=this.findList("OUserServiceExtImpl.queryCurrentTermid.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String>dataMap=new HashMap<String,String>();
					dataMap.put("termid",rs.getString("termid"));
					dataMap.put("termname",rs.getString("termname"));
					return dataMap;
				}
			});
			if(termids!=null&&termids.size()>0)
				return termids.get(0);
		} catch (Exception e) {
			logger.error("queryCurrentTermid()",e);
		}
		return null;
	}
	
	public String queryRoleRealType(String roleId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId",roleId);
		try {
			List<String> roleRealTypes=this.findList("OUserServiceExtImpl.queryRoleRealType.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString(1);
				}
			});
			if(roleRealTypes!=null&&roleRealTypes.size()>0)
				return roleRealTypes.get(0);
		} catch (Exception e) {
			logger.error("queryRoleRealType(String)",e);
		}
		return null;
	}

	@DataSource("read")
	public Map<String,String> queryDiscodeAndSchoolName(String cmis30id,final String teacherid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("teacherid",teacherid);
		try {
			List<Map<String,String>> mapdtos=this.findList("OUserServiceExtImpl.queryDiscodeAndSchoolName.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> dto=new HashMap<String,String>();
					dto.put("discode",rs.getString("discode"));
					dto.put("schoolName",rs.getString("schoolname"));
					if(NestUtil.isNotEmpty(teacherid))
						dto.put("teachername",rs.getString("teachername"));
					return dto;
				}
			});
			if(mapdtos!=null&&mapdtos.size()>0)
				return mapdtos.get(0);
		} catch (Exception e) {
			logger.error("queryDiscodeAndSchoolName(String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
		return null;
	}
	@DataSource("read")
	public Map<String, String> queryStudentInfo(String edu_id,String termid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("edu_id",edu_id);
		params.put("termid",termid);
		try {
			List<Map<String,String>> mapdtos=this.findList("OUserServiceExtImpl.queryStudentInfo.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> dto=new HashMap<String,String>();
					dto.put("schoolname",rs.getString("schoolname"));
					dto.put("cmis30id",rs.getString("cmis30id"));
					dto.put("discode",rs.getString("discode"));
					dto.put("studentid",rs.getString("studentid"));
					dto.put("name",rs.getString("name"));
					dto.put("gradenum",rs.getString("gradenum"));
					dto.put("gradeid",rs.getString("gradeid"));
					dto.put("classid",rs.getString("classid"));
					dto.put("levelcode",rs.getString("levelcode"));
					dto.put("campusid",rs.getString("campusid"));
					dto.put("gradename", rs.getString("gradename"));
					dto.put("classname", rs.getString("classsname"));
					dto.put("levelname",rs.getString("levelname"));
					dto.put("levelid", rs.getString("levelid"));
					dto.put("masterid",rs.getString("masterid"));
					return dto;
				}
			});
			if(mapdtos!=null&&mapdtos.size()>0)
				return mapdtos.get(0);
		} catch (Exception e) {
			logger.error("queryStudentInfo(String,String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
		return null;
	}

	//!!!!!!!
	@DataSource("read")
	public List<UserRoleDto> queryUserRoleByUserId(String userid,String campuseId) {
		
		/*Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid", userid);
		params.put("campuseId", campuseId);
		try {
			List<UserRoleDto> dto = this.findList("OUserServiceExtImpl.querySchoolUserRoleByUserId.query",
						params, new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								UserRoleDto dto = new UserRoleDto();
								dto.setRoleId(rs.getInt("roleId"));
								dto.setRoleName(rs.getString("roleName"));
								dto.setRoleType(rs.getInt("roleType"));
								return dto;
							}
						});
					return dto;
			} catch (Exception ex) {
				logger.error("queryUserRoleByUserId(String,String)",ex);
				return null;
			}*/
		return null;
	}
 
	

	@SuppressWarnings("unchecked")
	@DataSource("read")
	public List<FuncTreeDto> queryAllFuncTree(String userId,
			String roleId,Integer funcLevelType,Integer layer) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		params.put("layer", layer);
		params.put("funcLevelType", funcLevelType);
		
		try {
			ISqlElement sqlElement=this.processSql(params,"OUserServiceExtImpl.queryAllFuncTree.query");
			logger.info("queryAllFuncTree"+sqlElement.getSql()+":::"+sqlElement.getParams());
			List<FuncTreeDto> userFuncTreeDtoes =  getJdbcTemplate().query(sqlElement.getSql(), sqlElement.getParams(),
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rownum)
								throws SQLException {
							//功能标号
							String funcId = rs.getString("funcid");
							//功能名称
							String funcName = rs.getString("funcname");
							//功能对应url
							String url = rs.getString("execfilename");
							//功能层级
							String funcLevel = rs.getString("layer");
							//功能顺序
							String funcOrder = rs.getString("orderno");
							//上次功能菜单id
							String parFuncId = rs.getString("fatherid");
							//菜单图标
							String funcImage = rs.getString("funcimage");
							
							FuncTreeDto oneItem = new FuncTreeDto();
							oneItem.setShowType(rs.getInt("showtype"));
							oneItem.setFuncId(funcId);
							oneItem.setFuncName(funcName);
							oneItem.setUrl(url);
							oneItem.setFuncLevel(funcLevel);
							oneItem.setFuncOrder(funcOrder);
							oneItem.setParFuncId(parFuncId);
							oneItem.setFuncImage(funcImage);
							return oneItem;
						}
					});
			return userFuncTreeDtoes;
			
		} catch (Exception e) {
			logger.error("queryAllFuncTree(String,String,Integer,Integer)",e);
		}
		return null;
	}
	
	
	@Override
	@DataSource("read")
	public List<FuncTreeDto> queryScoreTree() {
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			ISqlElement sqlElement=this.processSql(params,"OUserServiceExtImpl.queryScoreTree.query");
			List<FuncTreeDto> userFuncTreeDtoes =  getJdbcTemplate().query(sqlElement.getSql(), sqlElement.getParams(),
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rownum)
								throws SQLException {
							//功能标号
							String funcId = rs.getString("funcid");
							//功能名称
							String funcName = rs.getString("funcname");
							//功能对应url
							String url = rs.getString("execfilename");
							//功能层级
							String funcLevel = rs.getString("layer");
							//功能顺序
							String funcOrder = rs.getString("orderno");
							//上次功能菜单id
							String parFuncId = rs.getString("fatherid");
							//菜单图标
							String funcImage = rs.getString("funcimage");
							
							FuncTreeDto oneItem = new FuncTreeDto();
							oneItem.setShowType(rs.getInt("showtype"));
							oneItem.setFuncId(funcId);
							oneItem.setFuncName(funcName);
							oneItem.setUrl(url);
							oneItem.setFuncLevel(funcLevel);
							oneItem.setFuncOrder(funcOrder);
							oneItem.setParFuncId(parFuncId);
							oneItem.setFuncImage(funcImage);
							return oneItem;
						}
					});
			return userFuncTreeDtoes;
			
		} catch (Exception e) {
			logger.error("queryScoreTree()",e);
		}
		return null;
	}
	@DataSource("read")
	public List<UserDto> queryLevelCodeByStudentId(Integer personid) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personid", personid);
		
		try {
			ISqlElement sqlElement=this.processSql(params,"OUserServiceExtImpl.queryLevelCodeByStudentId.query");
			logger.info("queryLevelCodeByStudentId(Integer) "+sqlElement.getSql()+":::"+sqlElement.getParams());
			
			List<UserDto> UserDtos =this.findList("OUserServiceExtImpl.queryLevelCodeByStudentId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto=new UserDto();
					/*dto.setLevelcode(rs.getInt("levelcode"));
					dto.setGradeid(rs.getInt("gradeid"));
					dto.setClassid(rs.getInt("classid"));
					dto.setDiscode(rs.getInt("discode"));
					dto.setEduId(rs.getString("edu_id"));*/
					return dto;
				}
			});
			return UserDtos;
		} catch (Exception e) {
			logger.error("queryLevelCodeByStudentId(Integer)",e);
		}
		return null;
	}


	@DataSource("read")
	public List<UserDto> queryPatriarchLevelCodeByEduId(String eduId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("eduId", eduId);
		
		try {
			ISqlElement sqlElement=this.processSql(params,"OUserServiceExtImpl.queryPatriarchLevelCodeByEduId.query");
			logger.info("queryPatriarchLevelCodeByEduId(String) "+sqlElement.getSql()+":::"+sqlElement.getParams());
			List<UserDto> userDtos = this.findList("OUserServiceExtImpl.queryPatriarchLevelCodeByEduId.query", params,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserDto dto = new UserDto();
					/*dto.setCmis30id(rs.getInt("cmis30id"));
					dto.setLevelcode(rs.getInt("levelcode"));
					dto.setDiscode(rs.getInt("discode"));
					dto.setTermId(rs.getInt("termId"));*/
					return dto;
				}
			});
			return userDtos;
		} catch (Exception e) {
			logger.error("queryPatriarchLevelCodeByEduId(String)",e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<UserDto> queryLevelCodeByTeacherId(Integer personid) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personid", personid);
		
		try {
			ISqlElement sqlElement=this.processSql(params,"OUserServiceExtImpl.queryLevelCodeByTeacherId.query");
			logger.info("queryLevelCodeByTeacherId(Integer) "+sqlElement.getSql()+":::"+sqlElement.getParams());
			
			List<UserDto> UserDtos =this.findList("OUserServiceExtImpl.queryLevelCodeByTeacherId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					UserDto dto=new UserDto();
					/*dto.setLevelcode(rs.getInt("levelcode"));
					dto.setEduId(rs.getString("edu_id"));
					dto.setDiscode(rs.getInt("discode"));*/
					
					return dto;
				}
			});
			return UserDtos;
		} catch (Exception e) {
			logger.error("queryLevelCodeByTeacherId(Integer)",e);
		}
		return null;
	}

	@DataSource("read")
	public List<SchoolInfoDto> querySchoolInfoDtoByUserName(List<String> cmis30ids) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cmis30ids",cmis30ids);
		try {
			List<SchoolInfoDto> schoolList = this.findList("OUserServiceExtImpl.querySchoolInfoDtoByUserName.query", params,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					SchoolInfoDto dto = new SchoolInfoDto();
					dto.setSchoolId(rs.getInt("cmis30id"));
					dto.setSchoolName(rs.getString("schoolname"));
					return dto;
				}
			});
			return schoolList;
		} catch (Exception e) {
			logger.error("querySchoolInfoDtoByUserName(String,String)",e);
		}
		return null;
	}


	public List<UserRoleDto> queryUserRoleTypeByUserId(Integer userId,Integer roleId) {
		
		/*Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid", userId);
		params.put("roleId", roleId);
		
		try {
			List<UserRoleDto> dto = this.findList("OUserServiceExtImpl.queryUserRoleTypeByUserId.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserRoleDto dto = new UserRoleDto();
							dto.setRoleId(rs.getInt("roleId"));
							dto.setRoleName(rs.getString("roleName"));
							dto.setRoleType(rs.getInt("roleType"));
							return dto;
						}
					});
			return dto;
		} catch (Exception ex) {
			logger.error("queryUserRoleTypeByUserId(Integer,Integer)",ex);
		}*/
		return null;
	}

	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}

	
	
}
