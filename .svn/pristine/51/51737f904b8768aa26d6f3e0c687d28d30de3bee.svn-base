package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AppraisalTypeDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.service.ext.SeniorExt;

public class SeniorExtImpl extends JdbcRootManager implements SeniorExt{

	@Override
	public List<AppraisalTypeDto> queryAppraisalTypeDto() {
		try {
			Map<String, Object> params=new HashMap<String,Object>();
			//栏目信息
			return this.findList("SeniorExtImpl.queryAppraisalTypeDto.query",params,new RowMapper(){
				public Object mapRow(ResultSet rs, int num) throws SQLException {
					AppraisalTypeDto dto = new AppraisalTypeDto();
					dto.setAppraisaltype(rs.getString("appraisaltype"));
					dto.setAppraisaltypeid(Integer.parseInt(rs.getString("appraisaltypeid")));
					return dto;
				}
			});
//			//学段信息
//			return this.findList("MasterAppriseExt.getEduysForMenu.query", params, new RowMapper(){
//				public Object mapRow(ResultSet rs, int num)
//						throws SQLException {
//					SchoolTreeDto dto = new SchoolTreeDto();
//					dto.setId(rs.getString("levelid")+"_1");//代表1级ID，为1的话 会调用查年级的方法
//					dto.setText(rs.getString("levelname"));
//					dto.setCompusId(rs.getString("campusid"));//校区id
//					return dto;
//				}
//			});
		} catch (Exception e) {
			logger.error("getEduysForMenu(Map<String,Object>)", e);
		}
		return null;
	}

	@Override
	public List<AppraisalTypeDto> queryAppraisalList() {
		try {
			Map<String, Object> params=new HashMap<String,Object>();
			//栏目信息
			return this.findList("SeniorExtImpl.queryAppraisalList.query",params,new RowMapper(){
				public Object mapRow(ResultSet rs, int num) throws SQLException {
					AppraisalTypeDto dto = new AppraisalTypeDto();
					dto.setUpappraisaltypeid(Integer.parseInt(rs.getString("upappraisaltypeid")));
					dto.setAppraisaltype(rs.getString("appraisaltype"));
					dto.setAppraisaltypeid(Integer.parseInt(rs.getString("appraisaltypeid")));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getEduysForMenu(Map<String,Object>)", e);
		}
		return null;
	}
	
	
	
}
