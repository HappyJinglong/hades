package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.ISubjectServiceExt;
import com.flyrish.hades.util.DataSource;

public class SubjectServiceExtImpl extends JdbcRootManager implements ISubjectServiceExt {
	private IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	
	public List<SubjectDto> querySubjectDtoByLevelCode(String levelcode) {
		if(NestUtil.isEmpty(levelcode)) return null;
		
		//先从缓存中读取所有学期
		List<SubjectDto> subjectDtos=null;
		String rediesKey=null;
		if(String.valueOf(Constant.DICT_TYPE_LEVELCODE_CZ).equals(levelcode)){
			//初中情况下
			rediesKey=Constant.MIDDLESCHOOL_SUBJECT_KEY;
		}else if(String.valueOf(Constant.DICT_TYPE_LEVELCODE_GZ).equals(levelcode)
			||String.valueOf(Constant.DICT_TYPE_LEVELCODE_GZYK).equals(levelcode)){
			//高中或者内高班情况下
			rediesKey=Constant.HIGHTSCHOOL_SUBJECT_KEY;
		}else{
			return null;
		}
		subjectDtos=redisServiceExt.readList(rediesKey);
		if(subjectDtos==null||subjectDtos.isEmpty()){
			subjectDtos=querySubjectDtoByLevelCodeDb(rediesKey);
			//放回缓存
			try {
				redisServiceExt.save(rediesKey,subjectDtos);
			} catch (ForceException e) {
				logger.error("querySubjectDtoByLevelCode(String)",e);
			}
		}
		return subjectDtos;
	}
	@DataSource("read")
	private List<SubjectDto> querySubjectDtoByLevelCodeDb(String rediesKey){
		if(NestUtil.isEmpty(rediesKey)) return null;
		try{
			return this.findList(rediesKey, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					SubjectDto dto=new SubjectDto();
					dto.setSubjectid(rs.getString("subjectid"));
					dto.setSubjectName(rs.getString("subjectname"));
					return dto;
				}
			});
		}catch(Exception ex){
			logger.error("querySubjectDtoByLevelCodeDb(String)",ex);
		}
		return null;
	}
}
