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
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.util.DataSource;

public class TermServiceExtImpl extends JdbcRootManager implements ITermServiceExt {
	
	private IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}

	public List<TermDto> queryEschoolYears(){
		//先从缓存中读取所有学期
		List<TermDto> termList=null;
			termList=redisServiceExt.readList(Constant.REDIS_TERM_ALLVALUE);
			
		if(termList!=null&&termList.size()>0){
			return termList;
			}
		//如果缓存中没有对应的值，则从数据库中读取
		termList=queryEschoolYearInDb();
		try{
			//再将其放入redis缓存中
			redisServiceExt.save(Constant.REDIS_TERM_ALLVALUE,termList);
		}catch(ForceException e){
			logger.error("queryEschoolYears()",e);
		}
		return termList;
	}
	@DataSource("read")
	public List<TermDto> queryEschoolYearInDb(){
		try{
			return this.findList("TermServiceExtImpl.queryEschoolYearInDb.query",new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					TermDto dto=new TermDto();
					dto.setTermid(rs.getInt("termid"));
					dto.setTermname(rs.getString("termname"));
					dto.setTermtype(rs.getInt("termtype"));
					return dto;
				}
			});
		}catch(Exception e){
			logger.error("queryEschoolYearInDb()",e);
		}
		return null;
	}
	@DataSource("read")
	public List<TermDto> queryHighSchoolTerms(String classid,String levelCode) {
		try{
			if(NestUtil.isEmpty(classid)||NestUtil.isEmpty(levelCode))return null;
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("classid",classid);
			if(Constant.DICT_TYPE_LEVELCODE_GZ==Integer.valueOf(levelCode)
					||Constant.DICT_TYPE_LEVELCODE_GZYK==Integer.valueOf(levelCode)){
				params.put("levelcode",levelCode);
			}
			return this.findList("TermServiceExtImpl.queryHighSchoolTerms.query",params,new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					TermDto dto=new TermDto();
					dto.setTermid(rs.getInt("semesterid"));
					dto.setTermname(rs.getString("semester"));
					return dto;
				}
			});
		}catch(Exception e){
			logger.error("queryHighSchoolTerms(String)",e);
		}
		return null;
	}

	@Override
	public List<TermDto> queryHighSchoolTerms(Integer gradeYearInt) {
		try{
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("gradeYearInt",gradeYearInt);
			return this.findList("TermServiceExtImpl.queryHighSchoolTerms.query2",params,new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					TermDto dto=new TermDto();
					dto.setTermid(rs.getInt("termid"));
					dto.setTermname(rs.getString("termname"));
					return dto;
				}
			});
		}catch(Exception e){
			logger.error("queryHighSchoolTerms(Integer)",e);
		}
		return null;
	}
	
}
