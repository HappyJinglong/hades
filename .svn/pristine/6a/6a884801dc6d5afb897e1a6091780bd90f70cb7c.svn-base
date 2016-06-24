package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.CountryInfoDto;
import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.SchoolUploadInfoDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.util.DataSource;

@SuppressWarnings("unchecked")
public class BaseInforManagerExt extends JdbcRootManager implements IBaseInforManagerExt {
	@DataSource("read")
	public List<UserRoleDto> queryUserRoleByUserId(String userId, String campuseId) {
		if(NestUtil.isEmpty(userId))return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("campuseId",campuseId);
		try {
			if(NestUtil.isEmpty(campuseId)){
				return this.findList("BaseInforManagerExt.queryUserRoleByUserId.query",
							params, new RowMapper() {
								public Object mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									UserRoleDto dto = new UserRoleDto();
									dto.setRoleId(rs.getString("roleid"));
									dto.setRoleName(rs.getString("rolename"));
									return dto;
								}
							});
			}else{
				List<UserRoleDto> dto = this.findList("BaseInforManagerExt.querySchoolUserRoleByUserId.query",
						params, new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								UserRoleDto dto = new UserRoleDto();
								dto.setRoleId(rs.getString("roleid"));
								dto.setRoleName(rs.getString("rolename"));
								return dto;
							}
						});
				 return dto;
			}
			} catch (Exception ex) {
				logger.error("queryUserRoleByUserId(String,String)",ex);
				return null;
			}
	}
	@DataSource("read")
	public List<UserSchoolDto> queryUserSchoolByUserId(String userId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("userId", userId);
		try {
			List<UserSchoolDto> dto = this.findList("BaseInforManagerExt.queryUserSchoolByUserId.query",
					paras, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserSchoolDto dto = new UserSchoolDto();
							dto.setSchoolId(rs.getInt("campusid"));
							dto.setSchoolName(rs.getString("name"));
							return dto;
						}
					});
			 return dto;
		} catch (Exception ex) {
			logger.error("queryUserSchoolByUserId(String)",ex);
			return null;
		}
	}
	
	@Override
	public List<UserSchoolDto> queryCampuseByCampuseid(Set<String> campuseId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		if(campuseId==null||campuseId.isEmpty()){
			campuseId=new HashSet<String>();
			campuseId.add("-1");
		}
		paras.put("campuseids", campuseId);
		try {
			List<UserSchoolDto> dto = this.findList("BaseInforManagerExt.queryCampuseByCampuseid.query",
					paras, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserSchoolDto dto = new UserSchoolDto();
							dto.setSchoolId(rs.getInt("campusid"));
							dto.setSchoolName(rs.getString("name"));
							return dto;
						}
					});
			 return dto;
		} catch (Exception ex) {
			logger.error("queryCampuseByCampuseid(String)",ex);
			return null;
		}
	}
	@DataSource("read")
	public List<CountryInfoDto> queryCountryInfoDtoByDiscode(String discode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("discode", discode);
		try {
			ISqlElement sqlDemo=this.processSql(params, "BaseInforManagerExt.queryCountryInfoDtoByDiscode.query");
			return this.findList("BaseInforManagerExt.queryCountryInfoDtoByDiscode.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							CountryInfoDto dto = new CountryInfoDto();
							dto.setAttchfileover(rs.getString("attchfileover"));
							dto.setAttchfileunover(rs.getString("attchfileunover"));
							dto.setCmisdbover(rs.getString("cmisdbover"));
							dto.setCmisdbunover(rs.getString("cmisdbunover"));
							dto.setCoursedbover(rs.getString("coursedbover"));
							dto.setCoursedbunover(rs.getString("coursedbunover"));
							dto.setName(rs.getString("name"));
							dto.setDiscode(rs.getString("discode"));
							return dto;
						}
					});
		} catch (Exception ex) {
			logger.error("queryCountryInfoDtoByDiscode(String)",ex);
			return null;
		}
	}
	@DataSource("read")
	public List<SchoolUploadInfoDto> querySchoolUploadInfoDtoByCondition(
			String db, String discode, String isupload) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("discode",discode);
		params.put("db",db);
		//如果不为空，则表示查询上传成功，反正查询不成功或者未上传的
		if(NestUtil.isNotEmpty(isupload)){
			params.put("isupload",isupload);
		}
		try {
			List<SchoolUploadInfoDto> dtos=this.findList("BaseInforManagerExt.querySchoolUploadInfoDtoByCondition.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							SchoolUploadInfoDto dto=new SchoolUploadInfoDto();
							dto.setSchoolname(rs.getString("schoolname"));
							dto.setState(rs.getString("state"));
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							dto.setUploadTime(NestUtil.isEmpty(rs.getString("uploadtime"))?null:sdf.format(rs.getTimestamp("uploadtime")));
							return dto;
						}
					});
			if(dtos==null||dtos.size()==0)return null;
			//去除重复的
			Map<String,SchoolUploadInfoDto> dtoMaps=new HashMap<String,SchoolUploadInfoDto>();
			for(SchoolUploadInfoDto dto:dtos){
				SchoolUploadInfoDto listDto=dtoMaps.get(dto.getSchoolname());
				if(listDto==null)
					dtoMaps.put(dto.getSchoolname(),dto);
				else{
					//比较上传时间大小，取最新有效值
					if(NestUtil.isNotEmpty(dto.getUploadTime())&&NestUtil.isNotEmpty(listDto.getUploadTime())
							&&dto.getUploadTime().compareToIgnoreCase(listDto.getUploadTime())>-1){
						dtoMaps.put(dto.getSchoolname(),dto);
					}
				}
			}
			dtos.clear();
			for(SchoolUploadInfoDto dto:dtoMaps.values()){
				if(NestUtil.isNotEmpty(dto.getUploadTime())){
					dto.setUploadTime(dto.getUploadTime()+"(最后一次上传时间)");
				}
				dtos.add(dto);
			}
			return dtos;
		} catch (Exception ex) {
			logger.error("querySchoolUploadInfoDtoByCondition(String,String,String)",ex);
			return null;
		}
	}
	@DataSource("read")
	public List<EdusysDto> queryEdusysDtoByInfo(String unitlevel,String userId,String roleType,String campuseid,String roleId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("unitlevel",unitlevel);
		params.put("userId",userId );
		params.put("campuseId",campuseid);
		params.put("roleId", roleId);
		params.put("roleType", roleType);
		try {
			ISqlElement sql=this.processSql(params,"BaseInforManagerExt.queryEdusysDtoByInfo.query");
			List<EdusysDto> dto = this.findList("BaseInforManagerExt.queryEdusysDtoByInfo.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							EdusysDto dto = new EdusysDto();
							dto.setCampuseId(rs.getString("campusid"));
							dto.setEdusysName(rs.getString("levelname"));
							dto.setEdusysId(rs.getString("levelid"));
							dto.setLevelCode(rs.getString("levelCode"));
							return dto;
						}
					});
			 return dto;
		} catch (Exception ex) {
			logger.error("queryEdusysDtoByInfo(String)",ex);
			return null;
		}
	}
	public String queryProKey(String tablename) {
		if(NestUtil.isEmpty(tablename)) return null;
		String sqlStr="select S_"+tablename+".nextval prokey from dual";
		try{
			List<String> prokeys=this.getJdbcTemplate().query(sqlStr, new RowMapper(){
	
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("prokey");
				}
			});
			if(prokeys!=null&&prokeys.size()>0)
				return prokeys.get(0);
		}catch(Exception e){
			logger.error("queryProKey(String)",e);
		}
		return null;
	}
	
}
