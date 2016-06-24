package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.ISysSubjectServiceExt;
import com.flyrish.hades.util.DataSource;

public class SysSubjectServiceExtImpl extends JdbcRootManager implements ISysSubjectServiceExt {
	
	private IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}

	
	public List<KsysSubjectDto> querySysSubject(){
		//先从缓存中读取所有学期
		List<KsysSubjectDto> termList=null;
			termList=redisServiceExt.readList(Constant.REDIS_SYS_SUBJECT);
		if(termList!=null&&termList.size()>0){
			return termList;
			}
		//如果缓存中没有对应的值，则从数据库中读取
		termList=querySysSubjectDb1();
		try{
			//再将其放入redis缓存中
			redisServiceExt.save(Constant.REDIS_SYS_SUBJECT,termList);
		}catch(ForceException e){
			logger.error("querySysSubject()",e);
		}
		return termList;
	}
	
	@DataSource("read")
	public List<KsysSubjectDto> querySysSubjectDb() {
		try{
			return this.findList("SysSubjectServiceExtImpl.querySysSubjectDb.query",new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KsysSubjectDto dto=new KsysSubjectDto();
					dto.setDomain_id(rs.getString("domain_id"));
					dto.setMust_choose_score(rs.getString("must_choose_score"));
					dto.setShort_name(rs.getString("short_name"));
					dto.setSubject_id(rs.getString("subject_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					return dto;
				}
			});
		}catch(Exception e){
			logger.error("querySysSubjectDb()",e);
		}
		return null;
	}

	@Override
	public List<KsysSubjectDto> querySysSubjectDb1() {
		try{
			return this.findList("SysSubjectServiceExtImpl.querySysSubjectDb1.query",new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KsysSubjectDto dto=new KsysSubjectDto();
					dto.setDomain_id(rs.getString("domain_id"));
					dto.setMust_choose_score(rs.getString("must_choose_score"));
					dto.setShort_name(rs.getString("short_name"));
					dto.setSubject_id(rs.getString("subject_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					return dto;
				}
			});
		}catch(Exception e){
			logger.error("querySysSubjectDb()",e);
		}
		return null;
	}
}
