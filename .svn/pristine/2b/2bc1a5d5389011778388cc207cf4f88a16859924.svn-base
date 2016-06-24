package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nestframework.commons.hibernate.JdbcManagerSupport;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.service.ext.IWelcomeManagerExt;
import com.flyrish.hades.util.DataSource;

public class WelcomeManagerExt extends JdbcManagerSupport implements IWelcomeManagerExt {
	protected Logger logger=Logger.getLogger(WelcomeManagerExt.class);
	@DataSource("read")
	public List<OFunc> findCommonFunc(String sql,
			Map<String, Object> queryMap) {
		try{
			List<OFunc> dtos=this.findList(sql,queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					OFunc of = new OFunc();
					of.setFuncname(rs.getString("funcname"));
					of.setFuncid(rs.getBigDecimal("funcid"));
					of.setExecfilename(rs.getString("execfilename"));
					of.setCommonFuncId(rs.getString("commonfuncid"));
					of.setUserId(rs.getString("userid"));
					return of;
				}
			});
			return dtos;
		}catch (Exception e) {
			logger.error("findCommonFunc(String,Map<String, Object>)",e);
		}
		return null;
	}
}
