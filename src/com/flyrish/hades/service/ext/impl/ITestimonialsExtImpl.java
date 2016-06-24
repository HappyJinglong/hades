package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.ITestimonialsExt;
import com.flyrish.hades.util.DataSource;

public class ITestimonialsExtImpl extends JdbcRootManager implements ITestimonialsExt{
	
	
	//查询科目信息
	@DataSource("read")
	public List<SubjectDto> getSubjectByCode(String code) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("levelcode", code);
			ISqlElement sqlDemo=this.processSql(params, "ITestimonialsExt.getSubject.query");
			return this.findList("ITestimonialsExt.getSubject.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SubjectDto sub = new SubjectDto();
					sub.setSubjectid(rs.getString("SUBJECTCODE"));
					sub.setSubjectName(rs.getString("SUBJECTNAME"));
					return sub;
				}
			});
		} catch (Exception e) {
			logger.error("ITestimonialsExtImpl.getSubjectByCode()", e);
		}
		return null;
	}

}
