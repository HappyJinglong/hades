package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.PartInfoDto;
import com.flyrish.hades.service.ext.IDataCheckExt;

public class DataCheckExtImpl extends JdbcRootManager implements IDataCheckExt{

	public List<PartInfoDto> queryPartInfo(Map<String, Object> params) {
		try {
			ISqlElement sqlelement=this.processSql(params, "DataCheckExtImpl.queryPartInfo.select");
			List<PartInfoDto> list = this.findList("DataCheckExtImpl.queryPartInfo.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							PartInfoDto dto = new PartInfoDto();
					        dto.setCheckCount(rs.getString("checkcount"));
							dto.setGradeNum(rs.getString("gradenum"));
							dto.setTwo_part_id(rs.getString("two_part_id"));
							dto.setTermType(rs.getString("termtype"));
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	public List<PartInfoDto> queryClassTeacher(Map<String, Object> params) {
		try {
			List<PartInfoDto> list = this.findList("DataCheckExtImpl.queryAssess.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							PartInfoDto dto = new PartInfoDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setGradeNum(rs.getString("gradenum"));
							dto.setTwo_part_id("22");
							dto.setTermType(rs.getString("termtype"));
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	
	public List<String> querygradelength(Map<String, Object> queryMap) {
		try {
			List<String> list = this.findList("DataCheckExtImpl.queryLength.select",queryMap,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							String gradeLength = rs.getString("length");
							return gradeLength;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("querygradelength(String)",e);
				}
				return null;
	}
	@Override
	public List<AppraisalDto> queryApprasial(Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryApprasial.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
							dto.setAppraseridentify(Integer.valueOf(rs.getString("appraseridentify")));
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	@Override
	public List<AppraisalDto> queryRecordpackage(Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryRecordpackage.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
							dto.setAppraseridentify(1);
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	public List<AppraisalDto> queryClassTeacherGZ(Map<String, Object> params) {
			try {
				List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryAssessGZ.select",params,new RowMapper() {
							public Object mapRow(ResultSet rs, int arg1) throws SQLException {
								AppraisalDto dto = new AppraisalDto();
								dto.setCheckCount(rs.getString("checkcount"));
								String ss = rs.getString("gradenum")+rs.getString("termtype");
								dto.setSemesterid(Integer.valueOf(ss));
								dto.setAppraisaltypeid(2030);
								dto.setAppraseridentify(4);
								return dto;
							}
						});
						return list;
					} catch (Exception e){
						logger.error("queryPartInfo(String)",e);
					}
					return null;
		}
	@Override
	public List<AppraisalDto> queryPersonality(Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryPersonality.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							if(NestUtil.isNotEmpty(rs.getString("checkcount"))){
								dto.setCheckCount("1");
							}
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraisaltypeid(7010);
							dto.setAppraseridentify(1);
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	
	@Override
	public List<AppraisalDto> queryLearnprocessWorks(
			Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryLearnprocessWorks.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraseridentify(1);
							dto.setAppraisaltypeid(8010);
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	@Override
	public List<AppraisalDto> queryPractices(Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryPractices.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
							dto.setAppraseridentify(1);
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}
	@Override
	public List<AppraisalDto> queryLearnprocessAppraisal(
			Map<String, Object> params) {
		try {
			List<AppraisalDto> list = this.findList("DataCheckExtImpl.queryLearnprocessAppraisal.select",params,new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setCheckCount(rs.getString("checkcount"));
							dto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
							dto.setAppraseridentify(1);
							dto.setAppraisaltypeid(890909);
							return dto;
						}
					});
					return list;
				} catch (Exception e){
					logger.error("queryPartInfo(String)",e);
				}
				return null;
	}

}
