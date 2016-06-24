package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.service.ext.IAattachFileExt;

public class AattachFileExtImpl extends JdbcRootManager implements IAattachFileExt{
	
	@Override
	public void refreshAattachFileExt() {
		List<Map<String, Object>> allDatas=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> allDatasR=queryRecodingPacking("AattachFileExtImpl.queryRecodingPacking.queryR");
//		System.out.println("-------已获取数据->查询记录袋 "+allDatasR.size()+"-----");
		List<Map<String, Object>> allDatasAP=queryRecodingPacking("AattachFileExtImpl.queryRecodingPacking.queryAP");
//		System.out.println("-------已获取数据->查询个人发展 "+allDatasAP.size()+"-----");
		List<Map<String, Object>> allDatasP=queryRecodingPacking("AattachFileExtImpl.queryRecodingPacking.queryP");
//		System.out.println("-------已获取数据->查询综合实践 "+allDatasP.size()+"-----");
		List<Map<String, Object>> allDatasAL=queryRecodingPacking("AattachFileExtImpl.queryRecodingPacking.queryAL");
//		System.out.println("-------已获取数据->查询学科学习发展过程"+allDatasAL.size()+" -----");
		allDatas.addAll(allDatasR);
		allDatas.addAll(allDatasAP);
		allDatas.addAll(allDatasP);
		allDatas.addAll(allDatasAL);
		String updateSql="update a_attach set attachtypeid=? where attachid=?";
		String[]fiedNames={"attachtypeid","attachid"};
//		System.out.println("-------开始更新数据 ，共需要更新"+allDatas.size()+"条-----");
		batchUpdateObjects(allDatas, fiedNames, updateSql);
//		System.out.println("-------更新数据结束 -----");
	}
	private List<Map<String, Object>> queryRecodingPacking(final String forkey){
		Map<String, Object> paras = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> dtos = this.findList(forkey,
					paras, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Map<String, Object> dto=new HashMap<String,Object>();
							dto.put("attachtypeid",rs.getString("attachtypeid"));
							dto.put("attachid", rs.getString("attachid"));
							/*dto.put("cmis30id", rs.getString("cmis30id"));
							dto.put("discode", rs.getString("discode"));
							dto.put("partid", rs.getString("partid"));*/
							return dto;
						}
					});
			 return dtos;
		} catch (Exception ex) {
			logger.error("queryRecodingPacking()",ex);
			return null;
		}
	}
	
}
