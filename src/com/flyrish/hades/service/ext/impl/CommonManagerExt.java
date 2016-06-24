package com.flyrish.hades.service.ext.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.nestframework.commons.utils.StringUtil;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.flyrish.hades.service.ext.ICommonManagerExt;

public class CommonManagerExt extends JdbcRootManager implements
		ICommonManagerExt {
	public boolean updateCommonFuncTable(final String userid,
			final String funcid) {
		try {
			if(StringUtil.isEmpty(funcid)){
				return false;
			}
			String sql = "select count(1) from h_o_commonfunc  where userid  = ? and funcid =?";
			int flag = this.getJdbcTemplate().queryForInt(sql,
					new Object[] { userid, funcid });
			if (flag == 0)// insert
			{
				sql = "insert into h_o_commonfunc(commonfuncid,userid,FUNCID,accesstime,clickcount) values(h_s_o_commonfunc.nextval,?,?,sysdate,1)";
				this.getJdbcTemplate().update(sql,
						new PreparedStatementSetter() {

							public void setValues(PreparedStatement ps)
									throws SQLException {
								ps.setString(1, userid);
								ps.setString(2, funcid);
							}

						});
			} else {// modify
				sql = "update h_o_commonfunc set accesstime = sysdate ,clickcount = clickcount+1 where USERID = ? and FUNCID = ? ";
				this.getJdbcTemplate().update(sql,
						new PreparedStatementSetter() {

							public void setValues(PreparedStatement ps)
									throws SQLException {
								ps.setString(1, userid);
								ps.setString(2, funcid);
							}

						});

			}
			return true;
		} catch (Exception e) {
			logger.error("updateCommonFuncTable(final String,final String)",e);
		}
		return false;
	}
}
