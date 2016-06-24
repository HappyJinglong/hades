package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.service.ext.IMonitorStaticticsExt;

public class MonitorStaticticsExtImpl extends JdbcRootManager implements
		IMonitorStaticticsExt {
	
	public Integer queryHeathData(Integer levelCode, String termid, String classid, String cmis30id, String discode, String gradeid) {
		String num = null;
		String gradeNum = null;
		Map<String, Object> params = new HashMap<String, Object>();
		// 学段为高中
		if (levelCode == (Constant.DICT_TYPE_LEVELCODE_GZ)
				|| levelCode == (Constant.DICT_TYPE_LEVELCODE_GZYK)) {

			if (termid != null) {
				num = termid.substring(1);
				gradeNum = termid.substring(0, 1);
			}

			if (classid != null) {
				// 转换classid为string类型
				classid = classid.toString();
			} else {
				classid = null;
			}

			/**
			 * 转换学期格式
			 */
			params.put("num", num);
			params.put("classId", classid);
			params.put("gradenum", gradeNum);

			try {
				// ISqlElement sqlDemo = this.processSql(params2,
				// "MasterAppriseExt.getHSHistoryTermId.qurey");
				List<String> strs = this.findList(
						"MasterAppriseExt.getHSHistoryTermId.qurey", params,
						new RowMapper() {
							public Object mapRow(ResultSet rs, int num)
									throws SQLException {
								return rs.getString("termid");
							}
						});
				if (strs != null && strs.size() > 0) {
					termid = strs.get(0);
				}
			} catch (Exception e) {
				logger.error("getCZSubjectInfos()", e);
			}
			
			params = new HashMap<String, Object>();
			params.put("termid", termid);
			params.put("classid", classid);
			params.put("gradeid", gradeid);
			params.put("schoolyear", termid.substring(0, 4));
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);

			try {
//				ISqlElement sqlElement=this.processSql(params,"IMonitorStaticticsExt.getHeathData.count");
//				System.out.println(sqlElement);
				List<Integer> count = this.findList(
						"IMonitorStaticticsExt.getHeathData.count", params,
						new RowMapper() {
							public Object mapRow(ResultSet rs, int num)
									throws SQLException {
								return rs.getInt("count");
							}
						});
				return count.get(0);
			} catch (Exception e) {
				logger.error("queryHeathData()", e);
			}
		}else{
			params.put("termid", termid);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("classid", classid);
			try {
				List<Integer> count = this.findList(
						"MonitorStaticticsExtImpl.queryHeathData.query1", params,
						new RowMapper() {
							public Object mapRow(ResultSet rs, int num)
									throws SQLException {
								return rs.getInt("count");
							}
						});
				return count.get(0);
			} catch (Exception e) {
				logger.error("queryHeathData()", e);
			}
		}
		return 0;
	}
}
