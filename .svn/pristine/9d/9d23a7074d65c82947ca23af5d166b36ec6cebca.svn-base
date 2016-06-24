package com.flyrish.hades.service.ext.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.hibernate.IRowHandler;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.hibernate.JdbcManagerSupport;
import org.nestframework.commons.hibernate.JdbcPage;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.exporter.exception.ManagerException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.service.ext.IJdbcRootManager;

public abstract class JdbcRootManager extends JdbcManagerSupport implements
		IJdbcRootManager {
	protected  Logger logger=Logger.getLogger(this.getClass());
	public JdbcRootManager() {
		super();
		isOldJdbc = true;
	}
	public void setValue(PreparedStatement ps, int order, Object obj, int type)
			throws SQLException {
		if (obj == null) {
			ps.setNull(order,Types.VARCHAR);
		}

		if (obj instanceof String)
			ps.setString(order, (String) obj);
		if (obj instanceof Date)
			ps.setDate(order, new java.sql.Date(((Date) obj).getTime()));
		if (obj instanceof Integer)
			ps.setInt(order, (Integer) obj);
		if (obj instanceof Double)
			ps.setDouble(order, (Double) obj);
		if (obj instanceof BigDecimal)
			ps.setBigDecimal(order, (BigDecimal) obj);
		if (obj instanceof Short)
			ps.setShort(order, (Short) obj);

	}
	public void setValue(PreparedStatement ps, int order, Object obj)throws SQLException{
		if(obj==null){
			//设置占位符
			ps.setNull(order,Types.VARCHAR);
		}
		if (obj instanceof String)
			setValue(ps,order,obj,Types.VARCHAR);
		if (obj instanceof Integer)
			setValue(ps,order,obj,Types.INTEGER);
		if (obj instanceof Date)
			setValue(ps,order,obj,Types.DATE);
		if (obj instanceof Double)
			setValue(ps,order,obj,Types.DOUBLE);
		if (obj instanceof BigDecimal)
			setValue(ps,order,obj,Types.BIGINT);
	}
	public void setDataValue(PreparedStatement stat, int order, String str,
			int type) throws Exception {
		if (StringUtil.isEmpty(str)) {
			stat.setNull(order, type);
			return;
		}
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");

		switch (type) {
		case Types.VARCHAR:
			stat.setString(order, str);
			break;
		case Types.INTEGER:
			stat.setInt(order, Integer.parseInt(str));
			break;
		case Types.TIMESTAMP:
			stat.setTimestamp(order, new Timestamp(sdft.parse(str).getTime()));
			break;
		case Types.DOUBLE:
			stat.setBigDecimal(order, new BigDecimal(str));
			break;
		case Types.SMALLINT:
			stat.setShort(order, Short.parseShort(str));
			break;
		default:
			break;
		}
	}
	public void batchUpdateObjects(final List<Map<String, Object>> datas,final String[]fieldNames,String sql){
		try{
			//批量更新，每次批量更新500条数据
			int cirCount=datas.size()/500;
			int modelValue=datas.size()%500;
			for(int i=0;i<cirCount;i++){
				List<Map<String, Object>> smaildatas=new ArrayList<Map<String,Object>>();
				for(int j=0;j<500;j++){
					smaildatas.add(datas.get(i*500+j));
				}
//				System.out.println("已更新"+(i*500)+"条");
				batchUpdate(smaildatas, fieldNames, sql);
			}
			List<Map<String, Object>> lastdatas=new ArrayList<Map<String,Object>>();
			for(int k=0;k<modelValue;k++){
				lastdatas.add(datas.get(cirCount*500+k));
			}
//			System.out.println("已更新"+(cirCount*500)+"条");
			batchUpdate(lastdatas, fieldNames, sql);
		}catch(Exception ex){
			logger.error("batchUpdateObjects(List<Map<String, Object>>,String[],String)",ex);
			throw new ManagerException(ex);
		}
	}
	private void batchUpdate(final List<Map<String,Object>> fields,final String[] fieldNames, String sql) {
		this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int idx) throws SQLException {
				Map<String,Object>data=fields.get(idx);
				int i=1;
				for(String field:fieldNames){
					ps.setObject(i++, data.get(field));
				}
			}
			public int getBatchSize() {
				return fields.size();
			}
		});
	}
	
}