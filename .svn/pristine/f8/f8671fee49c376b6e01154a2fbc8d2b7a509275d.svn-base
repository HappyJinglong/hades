package com.flyrish.hades.service.ext.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.ViewDto;
import com.flyrish.hades.service.ext.IScoreExt;
import com.flyrish.hades.util.DataSource;

public class ScoreExtImpl extends JdbcRootManager implements IScoreExt{
	/**
	 * 获取学年
	 * @return 
	 */
	@DataSource("read")
	public List<String> getYears(String manyYears) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("manyYears", manyYears);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getYears.query");
			return this.findList("ScoreExtImpl.getYears.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					String year = rs.getString("schoolyear");
					String yearName = rs.getString("syName");
					if(NestUtil.isEmpty(year) &&NestUtil.isEmpty(yearName) ){
						return "";
					}
					return year+"@"+yearName;
				}
			});
		} catch (Exception e) {
			logger.error("ScoreExtImpl.getSchoolYears(String)", e);
		}
		return null;
	}
	
	/**
	 * 根据学年获取当前班的历史班级id
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	@DataSource("read")
	public List<String> getClassIdBySchoolyearAndClassid(String schoolyear,
			String classid) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("schoolyear", schoolyear);
			params.put("classid", classid);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getClassIdBySchoolyearAndClassid.query");
			return this.findList("ScoreExtImpl.getClassIdBySchoolyearAndClassid.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					String classId = rs.getString("CLASSID");
					return classId;
				}
			});
		} catch (Exception e) {
			logger.error("ScoreExtImpl.getClassIdBySchoolyearAndClassid(String,String)", e);
		}
		return null;
	}
	/**
	 * 获取教务老师管辖班级
	 */
	@DataSource("read")
	public List<CampusDto> getJWLSClass(String levelCode, String cmis30id,String campusid) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("levelCode", levelCode);
			params.put("cmis30id", cmis30id);
			params.put("campusid", campusid);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getJWLSClass.query");
			return this.findList("ScoreExtImpl.getJWLSClass.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					CampusDto dto = new CampusDto();
					dto.setLevelId(rs.getString("levelid"));
					dto.setLevelName(rs.getString("levelname"));
					dto.setLevelNum(rs.getString("levelnum"));
					dto.setGradeId(rs.getString("gradeid"));
					dto.setGradeName(rs.getString("gradename"));
					dto.setGradeNum(rs.getString("gradenum"));
					dto.setClassId(rs.getString("classid"));
					dto.setClassName(rs.getString("classsname"));
					dto.setClassNum(rs.getString("classnum"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getJWLSClass(String,String)", e);
		}
		return null;
	}
	/*根据学段获取学科
	 */
	@DataSource("read")
	public List<KsysSubjectDto> getSubjectBySegmentId(String cmis30id,
			String segmentId,String courseType,String teacherid,String classid) {
		if(Constant.KG_COURSE_KIND.equals(courseType)){
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("segmentId", segmentId);
				params.put("cmis30id", cmis30id);
				params.put("courseKind", Constant.KG_COURSE_KIND);
				params.put("teacherid", teacherid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSubjectBySegmentIdAndXB.query");
				return this.findList("ScoreExtImpl.getSubjectBySegmentIdAndXB.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KsysSubjectDto dto = new KsysSubjectDto();
						dto.setSubject_id(rs.getString("subject_id"));
						dto.setSubject_name(rs.getString("subject_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSubjectBySegmentId(String,String)", e);
			}
			
			
		}else{
			List<String> courseKinds  = new ArrayList<String>();
			courseKinds.add("1230301");
			courseKinds.add("1230310");
			courseKinds.add("1230315");
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("segmentId", segmentId);
				params.put("cmis30id", cmis30id);
				params.put("courseKinds", courseKinds);
				params.put("teacherid", teacherid);
				if(NestUtil.isEmpty(classid)){
					classid="-1";
				params.put("classid", classid);
				}else{
					params.put("classid", classid);
				}
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSubjectBySegmentId.query");
				return this.findList("ScoreExtImpl.getSubjectBySegmentId.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KsysSubjectDto dto = new KsysSubjectDto();
						dto.setSubject_id(rs.getString("subject_id"));
						dto.setSubject_name(rs.getString("subject_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSubjectBySegmentId(String,String)", e);
			}
		}
		
		
		return null;
	}
	/*根据学段获取学科
	 */
	@DataSource("read")
	public List<KsysSubjectDto> getSubjectBySegmentId(String cmis30id,
			String segmentId,String courseType,String teacherid,String classid,String schoolyear) {
		if(Constant.KG_COURSE_KIND.equals(courseType)){
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("segmentId", segmentId);
				params.put("cmis30id", cmis30id);
				params.put("courseKind", Constant.KG_COURSE_KIND);
				params.put("teacherid", teacherid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSubjectBySegmentIdAndXB.query");
				return this.findList("ScoreExtImpl.getSubjectBySegmentIdAndXB.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KsysSubjectDto dto = new KsysSubjectDto();
						dto.setSubject_id(rs.getString("subject_id"));
						dto.setSubject_name(rs.getString("subject_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSubjectBySegmentId(String,String)", e);
			}
			
			
		}else{
			List<String> courseKinds  = new ArrayList<String>();
			courseKinds.add("1230301");
			courseKinds.add("1230310");
			courseKinds.add("1230315");
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("segmentId", segmentId);
				params.put("cmis30id", cmis30id);
				params.put("courseKinds", courseKinds);
				params.put("teacherid", teacherid);
				params.put("schoolyear",schoolyear);
				if(NestUtil.isEmpty(classid)){
					classid="-1";
				params.put("classid", classid);
				}else{
					params.put("classid", classid);
				}
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSubjectBySegmentId.query2");
				return this.findList("ScoreExtImpl.getSubjectBySegmentId.query2", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KsysSubjectDto dto = new KsysSubjectDto();
						dto.setSubject_id(rs.getString("subject_id"));
						dto.setSubject_name(rs.getString("subject_name"));
						dto.setIsflag("1");
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSubjectBySegmentId(String,String,String,String,String,String)", e);
			}
		}
		return null;
	}
	
	@Override
	public List<KsysSubjectDto> getSubjectBySegmentIdAndStudents(
			String cmis30id,String discode,String segmentId, String courseType,String classid) {
		List<String> courseKinds  = new ArrayList<String>();
		courseKinds.add("1230301");
		courseKinds.add("1230310");
		courseKinds.add("1230315");
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segmentId", segmentId);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("courseKinds", courseKinds);
			params.put("classid", classid);
			return this.findList("ScoreExtImpl.getSubjectBySegmentIdAndStudents.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KsysSubjectDto dto = new KsysSubjectDto();
					dto.setSubject_id(rs.getString("subject_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setIsflag("0");
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getSubjectBySegmentIdAndStudents(String,String,String,String,String,String,String)", e);
		}
		return null;
	}

	/**
	 * 根据学科和课程类型获取课程模块
	 */
	@DataSource("read")
	public List<KcourseDto> getCourseBySubjectIdAndCourseType(String cmis30id,
	    String courseKind, String subjectId,String segmentId,String teacherid,String classid) {
		List<String> courseKinds  = new ArrayList<String>();
		if(Constant.KG_COURSE_KIND.equals(courseKind)){
			courseKinds.add(Constant.KG_COURSE_KIND);
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("courseKinds", courseKinds);
				params.put("cmis30id", cmis30id);
				params.put("subjectId", subjectId);
				params.put("segmentId", segmentId);
				params.put("teacherid", teacherid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSchoolCourseBySubjectIdAndCourseType.query");
				return this.findList("ScoreExtImpl.getSchoolCourseBySubjectIdAndCourseType.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KcourseDto dto = new KcourseDto();
						dto.setCourse_id(rs.getString("course_id"));
						dto.setCourse_name(rs.getString("course_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSchoolCourseBySubjectIdAndCourseType(String,String,String)", e);
			}
		}else{
			courseKinds.add("1230301");
			courseKinds.add("1230310");
			courseKinds.add("1230315");
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("courseKinds", courseKinds);
				params.put("cmis30id", cmis30id);
				params.put("subjectId", subjectId);
				params.put("segmentId", segmentId);
				params.put("teacherid", teacherid);
				if(NestUtil.isEmpty(classid)){
					classid="-1";
				}
				params.put("classid", classid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getCourseBySubjectIdAndCourseType.query");
				return this.findList("ScoreExtImpl.getCourseBySubjectIdAndCourseType.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KcourseDto dto = new KcourseDto();
						dto.setCourse_id(rs.getString("course_id"));
						dto.setCourse_name(rs.getString("course_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getCourseBySubjectIdAndCourseType(String,String,String)", e);
			}
		}
		
		return null;
	}
	
	@Override
	public List<KcourseDto> getCourseBySubjectIdAndCourseTypeByStudents(
			String cmis30id,String discode, String subjectId,
			String segmentId, String classid) {
		List<String> courseKinds  = new ArrayList<String>();
		courseKinds.add("1230301");
		courseKinds.add("1230310");
		courseKinds.add("1230315");
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("courseKinds", courseKinds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			params.put("subjectId", subjectId);
			params.put("segmentId", segmentId);
			params.put("classid", classid);
			ISqlElement sqlDemo=this.processSql(params,"ScoreExtImpl.getCourseBySubjectIdAndCourseTypeByStudents.query");
			return this.findList("ScoreExtImpl.getCourseBySubjectIdAndCourseTypeByStudents.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KcourseDto dto = new KcourseDto();
					dto.setCourse_id(rs.getString("course_id"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setIsflag("0");
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getCourseBySubjectIdAndCourseTypeByStudents(String,String,String,String,String)", e);
		}
		return null;
	}

	@Override
	public List<KcourseDto> getCourseBySubjectIdAndCourseType(String cmis30id,
			String courseKind, String subjectId, String segmentId,
			String teacherid, String classid, String schoolyear) {
		List<String> courseKinds  = new ArrayList<String>();
		if(Constant.KG_COURSE_KIND.equals(courseKind)){
			courseKinds.add(Constant.KG_COURSE_KIND);
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("courseKinds", courseKinds);
				params.put("cmis30id", cmis30id);
				params.put("subjectId", subjectId);
				params.put("segmentId", segmentId);
				params.put("teacherid", teacherid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSchoolCourseBySubjectIdAndCourseType.query");
				return this.findList("ScoreExtImpl.getSchoolCourseBySubjectIdAndCourseType.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KcourseDto dto = new KcourseDto();
						dto.setCourse_id(rs.getString("course_id"));
						dto.setCourse_name(rs.getString("course_name"));
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getSchoolCourseBySubjectIdAndCourseType(String,String,String)", e);
			}
		}else{
			courseKinds.add("1230301");
			courseKinds.add("1230310");
			courseKinds.add("1230315");
			try {
				Map<String,Object>params = new HashMap<String, Object>();
				params.put("courseKinds", courseKinds);
				params.put("cmis30id", cmis30id);
				params.put("subjectId", subjectId);
				params.put("segmentId", segmentId);
				params.put("teacherid", teacherid);
				params.put("schoolyear", schoolyear);
				if(NestUtil.isEmpty(classid)){
					classid="-1";
				}
				params.put("classid", classid);
				ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getCourseBySubjectIdAndCourseType.query2");
				return this.findList("ScoreExtImpl.getCourseBySubjectIdAndCourseType.query2", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						KcourseDto dto = new KcourseDto();
						dto.setCourse_id(rs.getString("course_id"));
						dto.setCourse_name(rs.getString("course_name"));
						dto.setIsflag("1");
						return dto;
					}
				});
			} catch (Exception e) {
				logger.error("getCourseBySubjectIdAndCourseType(String,String,String)", e);
			}
		}
		
		return null;
	}

	/**
	 * 内置课程查询模块学分、学时、任课老师
	 * @param segmentId
	 * @param classid
	 * @param discode
	 * @param courseId
	 * @param subjectId
	 * @param cmis30id
	 * @param courseKinds
	 * @return
	 */
	@DataSource("read")
	public List<ViewDto> getNZXFAndXSAndTeacherName(String segmentId,
			String classid, String discode, String courseId, String subjectId,
			String cmis30id,String teacherid,String usertype,String userRealType) {
		
		List<String> courseKinds  = new ArrayList<String>();
		courseKinds.add("1230301");
		courseKinds.add("1230310");
		courseKinds.add("1230315");
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segmentId", segmentId);
			params.put("classid", classid);
			params.put("discode", discode);
			params.put("courseId", courseId);
			params.put("subjectId", subjectId);
			params.put("cmis30id", cmis30id);
			params.put("courseKinds", courseKinds);
			if(Constant.USER_KIND_SCHOOLTEACHER.equals(usertype) //任课老师
					&&Constant.USER_TYPE_COURSEMASTER.equals(userRealType)){
					params.put("teacherid", teacherid);
			}
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZXFAndXSAndTeacherName.query");
			return this.findList("ScoreExtImpl.getNZXFAndXSAndTeacherName.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ViewDto vd = new ViewDto();
					vd.setXf(rs.getInt("credit_hour"));
					vd.setXs(rs.getInt("period_count"));
					
					vd.setXfs(rs.getDouble("credit_hour"));
					vd.setXss(rs.getDouble("period_count"));
					
					vd.setTeacherName(rs.getString("name"));
					vd.setClass_model_id(rs.getString("class_model_id"));
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setCourse_name(rs.getString("course_name"));
					vd.setModel_credit(rs.getString("model_credit"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getNZXFAndXSAndTeacherName(String,String,String,String,String,String)", e);
		}
		return null;
	}
	
	@Override
	public List<ViewDto> getNZXFAndXSAndTeacherNameByStudents(String segmentId,
			String classid, String discode, String courseId, String subjectId,
			String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segmentId", segmentId);
			params.put("classid", classid);
			params.put("discode", discode);
			params.put("course_id", NestUtil.isEmpty(courseId)?null:courseId.split("_")[0]);
			params.put("subjectId", subjectId);
			params.put("cmis30id", cmis30id);
			return this.findList("ScoreExtImpl.getNZXFAndXSAndTeacherNameByStudents.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ViewDto vd = new ViewDto();
					vd.setXf(rs.getInt("credit_hour"));
					vd.setXs(rs.getInt("period_count"));
					
					vd.setXfs(rs.getDouble("credit_hour"));
					vd.setXss(rs.getDouble("period_count"));
					vd.setSpecail(rs.getString("special"));
					vd.setTeacherName(rs.getString("name"));
					vd.setClass_model_id(rs.getString("class_model_id"));
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setCourse_name(rs.getString("course_name"));
					vd.setModel_credit(rs.getString("model_credit"));
					return vd;
				}
			}); 
			
		} catch (Exception e) {
			logger.error("getNZXFAndXSAndTeacherNameByStudents(String,String,String,String,String,String)", e);
		}
		return null;
	}

	@Override
	public List<ViewDto> getNZXFAndXSAndTeacherName(String segmentId,
			String classid, String discode, String courseId, String subjectId,
			String cmis30id, String teacherid, String usertype,
			String userRealType, String schoolyear) {
		List<String> courseKinds  = new ArrayList<String>();
		courseKinds.add("1230301");
		courseKinds.add("1230310");
		courseKinds.add("1230315");
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segmentId", segmentId);
			params.put("classid", classid);
			params.put("discode", discode);
			params.put("courseId", courseId.split("_")[0]);
			params.put("subjectId", subjectId);
			params.put("cmis30id", cmis30id);
			params.put("courseKinds", courseKinds);
			params.put("schoolyear", schoolyear);
			if(Constant.USER_KIND_SCHOOLTEACHER.equals(usertype) //任课老师
					&&Constant.USER_TYPE_COURSEMASTER.equals(userRealType)){
					params.put("teacherid", teacherid);
			}
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZXFAndXSAndTeacherName.query");
			return this.findList("ScoreExtImpl.getNZXFAndXSAndTeacherName.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ViewDto vd = new ViewDto();
					vd.setXf(rs.getInt("credit_hour"));
					vd.setXs(rs.getInt("period_count"));
					
					vd.setXfs(rs.getDouble("credit_hour"));
					vd.setXss(rs.getDouble("period_count"));
					
					vd.setTeacherName(rs.getString("name"));
					vd.setClass_model_id(rs.getString("class_model_id"));
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setCourse_name(rs.getString("course_name"));
					vd.setModel_credit(rs.getString("model_credit"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getNZXFAndXSAndTeacherName(String,String,String,String,String,String)", e);
		}
		return null;
	}

	@Override
	public List<ModelScoreDto> getNZScore(String classid, String preclassid,
			String class_model_id, Double xs, String discode, String cmis30id,
			String subject_id, String schoolyear) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("preclassid", preclassid);
			params.put("class_model_id", class_model_id);
			params.put("xs", xs);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			params.put("subject_id", subject_id);
			params.put("schoolyear", schoolyear);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZScore.query");
			return this.findList("ScoreExtImpl.getNZScore.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto dto = new ModelScoreDto();
					dto.setXuhao((num+1)+"");
					dto.setCourse_kind(rs.getString("course_kind"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setModel_credit(rs.getString("model_credit"));
					dto.setPeriod_count(rs.getString("period_count"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentid(rs.getString("STUDENTID"));
					dto.setClassid(rs.getString("classid"));
					dto.setClass_model_id(rs.getString("class_model_id"));
					dto.setStudentName(rs.getString("studentName"));
					dto.setPeacetime1(rs.getString("peacetime1"));
					dto.setPeacetime2(rs.getString("peacetime2"));
					dto.setPeacetime3(rs.getString("peacetime3"));
					dto.setPeacetime4(rs.getString("peacetime4"));
					dto.setPeacetime5(rs.getString("peacetime5"));
					dto.setDaily_behave(rs.getString("daily_behave"));
					dto.setPeacetime16(rs.getString("peacetime16"));
					dto.setQqxs(rs.getString("qqxs"));
					if(null==dto.getQqxs() ||"".equals(dto.getQqxs())||"0".equals(dto.getQqxs())){
						dto.setCql("100%");
					}else{
						dto.setCql(rs.getString("cql")+"%");
					}
					if(NestUtil.isNotEmpty(rs.getString("examine_result"))){
						if(!rs.getString("examine_result").contains(".")){
							dto.setExamine_result(rs.getString("examine_result"));
						}else{
							dto.setExamine_result((float)(Math.round(Double.parseDouble(rs.getString("examine_result"))*10))/10+"");
						}
					}
					dto.setPeacetime18(rs.getString("peacetime18"));
					dto.setCredit_hour(rs.getString("credit_hour"));
					dto.setCredit_source(rs.getString("credit_source"));
					if(null!=dto.getCredit_source() && dto.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						dto.setCredit_source("是");
					}else{
						dto.setCredit_source("否");
					}
					dto.setIs_pass(rs.getString("is_pass"));
					if(null!=dto.getIs_pass() && dto.getIs_pass().equals("0")){
						dto.setIs_pass("是");
					}else{
						dto.setIs_pass("否");
						dto.setCredit_hour(dto.getModel_credit());
					}
					dto.setLevel_name(rs.getString("level_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getNZScore(String,String,Integer,String,String)", e);
		}
		return null;	
	}

	@Override
	public List<ModelScoreDto> getNZScore2(String classid,
			List<ViewDto> vds , String discode, String cmis30id, Double xs) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classid", classid);
			List<String>class_model_ids=new ArrayList<String>();
			for(ViewDto dto:vds)
				class_model_ids.add(dto.getClass_model_id());
			params.put("class_model_ids", class_model_ids);
			params.put("xs", xs);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZScore2.query");
			return this.findList("ScoreExtImpl.getNZScore2.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto dto = new ModelScoreDto();
					dto.setXuhao((num+1)+"");
					dto.setCourse_kind(rs.getString("course_kind"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setModel_credit(rs.getString("model_credit"));
					dto.setPeriod_count(rs.getString("period_count"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentid(rs.getString("STUDENTID"));
					dto.setClassid(rs.getString("classid"));
					dto.setClass_model_id(rs.getString("class_model_id"));
					dto.setStudentName(rs.getString("studentName"));
					dto.setPeacetime1(rs.getString("peacetime1"));
					dto.setPeacetime2(rs.getString("peacetime2"));
					dto.setPeacetime3(rs.getString("peacetime3"));
					dto.setPeacetime4(rs.getString("peacetime4"));
					dto.setPeacetime5(rs.getString("peacetime5"));
					dto.setDaily_behave(rs.getString("daily_behave"));
					dto.setPeacetime16(rs.getString("peacetime16"));
					dto.setQqxs(rs.getString("qqxs"));
					if(NestUtil.isNotEmpty(rs.getString("qqxs"))&&Integer.parseInt(rs.getString("qqxs"))<0){
						dto.setQqxs("0");
					}
					if(NestUtil.isEmpty(rs.getString("cql"))||"0".equals(dto.getQqxs())){
						dto.setCql("100%");
					}else{
						dto.setCql(rs.getString("cql")+"%");
					}
					if(NestUtil.isNotEmpty(rs.getString("examine_result"))){
						if(!rs.getString("examine_result").contains(".")){
							dto.setExamine_result(rs.getString("examine_result"));
						}else{
							dto.setExamine_result((float)(Math.round(Double.parseDouble(rs.getString("examine_result"))*10))/10+"");
						}
					}
					dto.setPeacetime18(rs.getString("peacetime18"));
					dto.setCredit_hour(rs.getString("credit_hour"));
					dto.setCredit_source(rs.getString("credit_source"));
					if(null!=dto.getCredit_source() && dto.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						dto.setCredit_source("是");
					}else{
						dto.setCredit_source("否");
					}
					dto.setIs_pass(rs.getString("is_pass"));
					if(null!=dto.getIs_pass() && dto.getIs_pass().equals("0")){
						dto.setIs_pass("是");
					}else{
						dto.setIs_pass("否");
						dto.setCredit_hour(dto.getModel_credit());
					}
					dto.setLevel_name(rs.getString("level_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getNZScore2(String,String,Integer,String,String)", e);
		}
		return null;	
	}

	/**
	 * 校本课程查询模块学分、学时、任课老师
	 * @param segmentId
	 * @param discode
	 * @param courseId
	 * @param subjectId
	 * @param cmis30id
	 * @param courseKind
	 * @return
	 */
	@DataSource("read")
	public List<ViewDto> getSchoolXFAndXSAndTeacherName(String segmentId,
			String discode, String courseId, String subjectId, String cmis30id,
			String courseKind,String teacherid,String usertype,String userRealType) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segmentId", segmentId);
			params.put("discode", discode);
			params.put("courseId", courseId);
			params.put("subjectId", subjectId);
			params.put("cmis30id", cmis30id);
			/*params.put("courseKind", courseKind);*/
			
			if(Constant.USER_KIND_SCHOOLTEACHER.equals(usertype) //任课老师
					&&Constant.USER_TYPE_COURSEMASTER.equals(userRealType)){
					params.put("teacherid", teacherid);
			}
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSchoolXFAndXSAndTeacherName.query");
			return this.findList("ScoreExtImpl.getSchoolXFAndXSAndTeacherName.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ViewDto vd = new ViewDto();
					vd.setXf(rs.getInt("credit_hour"));
					vd.setXs(rs.getInt("period_count"));
					
					vd.setXfs(rs.getDouble("credit_hour"));
					vd.setXss(rs.getDouble("period_count"));
					
					vd.setTeacherName(rs.getString("name"));
					vd.setSegment_course_id(rs.getString("segment_course_id"));
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setCourse_name(rs.getString("course_name"));
					vd.setModel_credit(rs.getString("model_credit"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getSchoolXFAndXSAndTeacherName(String,String,String,String,String,String)", e);
		}
		return null;	

	}
	/**
	 * 内置课程查询成绩 
	 */
	@DataSource("read")
	public List<ModelScoreDto> getNZScore(String classid,String preclassid,String class_model_id,Double xs,String discode,String cmis30id,String subject_id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("preclassid", preclassid);
			params.put("class_model_id", class_model_id);
			params.put("xs", xs);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			params.put("subject_id", subject_id);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZScore.query");
			return this.findList("ScoreExtImpl.getNZScore.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto dto = new ModelScoreDto();
					dto.setXuhao((num+1)+"");
					dto.setCourse_kind(rs.getString("course_kind"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setModel_credit(rs.getString("model_credit"));
					dto.setPeriod_count(rs.getString("period_count"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentid(rs.getString("STUDENTID"));
					dto.setClassid(rs.getString("classid"));
					dto.setClass_model_id(rs.getString("class_model_id"));
					dto.setStudentName(rs.getString("studentName"));
					dto.setPeacetime1(rs.getString("peacetime1"));
					dto.setPeacetime2(rs.getString("peacetime2"));
					dto.setPeacetime3(rs.getString("peacetime3"));
					dto.setPeacetime4(rs.getString("peacetime4"));
					dto.setPeacetime5(rs.getString("peacetime5"));
					dto.setDaily_behave(rs.getString("daily_behave"));
					dto.setPeacetime16(rs.getString("peacetime16"));
					dto.setQqxs(rs.getString("qqxs"));
					if(null==dto.getQqxs() ||"".equals(dto.getQqxs())||"0".equals(dto.getQqxs())){
						dto.setCql("100%");
					}else{
						dto.setCql(rs.getString("cql")+"%");
					}
					dto.setExamine_result(rs.getString("examine_result"));
					dto.setPeacetime18(rs.getString("peacetime18"));
					dto.setCredit_hour(rs.getString("credit_hour"));
					dto.setCredit_source(rs.getString("credit_source"));
					if(null!=dto.getCredit_source() && dto.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						dto.setCredit_source("是");
					}else{
						dto.setCredit_source("否");
					}
					dto.setIs_pass(rs.getString("is_pass"));
					if(null!=dto.getIs_pass() && dto.getIs_pass().equals("0")){
						dto.setIs_pass("是");
					}else{
						dto.setIs_pass("否");
						dto.setCredit_hour(dto.getModel_credit());
					}
					dto.setLevel_name(rs.getString("level_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getNZScore(String,String,Integer,String,String)", e);
		}
		return null;		
	}
	/**
	 * 校本课程查询成绩
	 * @param classid
	 * @param class_model_id
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> getSchoolScore(String segment_course_id,
			Double xs, String discode, String cmis30id,String subject_id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("segment_course_id", segment_course_id);
			params.put("xs", xs);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			params.put("subject_id", subject_id);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSchoolScore.query");
			return this.findList("ScoreExtImpl.getSchoolScore.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto dto = new ModelScoreDto();
					dto.setXuhao((num+1)+"");
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setSegment_course_id(rs.getString("segment_course_id"));
					dto.setClassid(rs.getString("classid")); 
					dto.setCourse_kind(rs.getString("course_kind"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setModel_credit(rs.getString("model_credit"));
					dto.setPeriod_count(rs.getString("period_count"));
					
					dto.setStudentName(rs.getString("studentName"));
					dto.setPeacetime1(rs.getString("peacetime1"));
					dto.setPeacetime2(rs.getString("peacetime2"));
					dto.setPeacetime3(rs.getString("peacetime3"));
					dto.setPeacetime4(rs.getString("peacetime4"));
					dto.setPeacetime5(rs.getString("peacetime5"));
					dto.setDaily_behave(rs.getString("daily_behave"));
					dto.setPeacetime16(rs.getString("peacetime16"));
					dto.setQqxs(rs.getString("qqxs"));
					if(null==dto.getQqxs() ||"".equals(dto.getQqxs())||"0".equals(dto.getQqxs())){
						dto.setCql("100%");
					}else{
						dto.setCql(rs.getString("cql")+"%");
					}
					dto.setExamine_result(rs.getString("examine_result"));
					dto.setPeacetime18(rs.getString("peacetime18"));
					dto.setCredit_hour(rs.getString("credit_hour"));
					if(null!=dto.getCredit_source() && dto.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						dto.setCredit_source("是");
					}else{
						dto.setCredit_source("否");
					}
					dto.setIs_pass(rs.getString("is_pass"));
					if(null!=dto.getIs_pass() && dto.getIs_pass().equals("0")){
						dto.setIs_pass("是");
					}else{
						dto.setIs_pass("否");
						dto.setCredit_hour(dto.getModel_credit());
					}
					dto.setLevel_name(rs.getString("level_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getSchoolScore(String,Integer,String,String)", e);
		}
		return null;		
	}
	
	/**
	 * 成绩插入
	 * @param modelScores
	 * @param courseType
	 */
	public void insertNZScore(List<ModelScoreDto> modelScores,String courseType,String enteringScore,String cmis30id,String disCode,String class_model_id){
		List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		for(ModelScoreDto dto:modelScores){
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("studentid",dto.getStudentid());
			data.put("classid",dto.getClassid());
			data.put("class_model_id",dto.getClass_model_id());
			data.put("segment_course_id", dto.getSegment_course_id());
			data.put("daily_behave",dto.getDaily_behave());
			data.put("examine_result", dto.getExamine_result());
			data.put("absence_ration",100.00-Double.parseDouble(dto.getCql().split("%")[0]));
			if("否".equals(dto.getIs_pass())){
				data.put("is_pass", "1");
			}else{
				data.put("is_pass", "0");
			}
			data.put("course_kind", dto.getCourse_kind());
			data.put("course_name", dto.getCourse_name());
			data.put("credit_hour", dto.getCredit_hour());
			if("是".equals(dto.getCredit_source())){
				data.put("credit_source",Constant.KG_CREDIT_SOURCE_EXEMPTION);
			}else{
				if(Constant.KG_CREDIT_SOURCE_STUDY.equals(enteringScore)){
					data.put("credit_source",Constant.KG_CREDIT_SOURCE_STUDY);
				}else{
				   data.put("credit_source",Constant.KG_CREDIT_SOURCE_EXPORT);
				}
				
			}
			data.put("peacetime1", dto.getPeacetime1());
			data.put("peacetime2", dto.getPeacetime2());
			data.put("peacetime3", dto.getPeacetime3());
			data.put("peacetime4", dto.getPeacetime4());
			data.put("peacetime5", dto.getPeacetime5());
			data.put("peacetime16",dto.getPeacetime16());
			if(null == dto.getQqxs() || "".equals(dto.getQqxs())){
				dto.setQqxs("0");
			}
			/*data.put("peacetime17",Integer.parseInt(dto.getPeriod_count())-Integer.parseInt(dto.getQqxs()));*/
			double  period  =   Double.parseDouble(dto.getPeriod_count())-Double.parseDouble(dto.getQqxs());  
			BigDecimal  b   =   new   BigDecimal(period);  
			period   =   b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
			data.put("peacetime17",period);
			data.put("peacetime18",dto.getPeacetime18());
			data.put("model_credit",dto.getModel_credit());
			data.put("cmis30id",cmis30id);
			data.put("DISCODE",disCode);
			datas.add(data);
		}
		String sql = "";
		if(Constant.KG_COURSE_KIND.equals(courseType)){  //校本课程
			
			sql  = "UPDATE k_student_matriculate k SET k.daily_behave=?,k.examine_result=?,k.absence_ration=?,k.is_pass=?,k.course_kind=?,k.course_name=?,k.credit_hour=?,k.credit_source=?,k.peacetime1=?,k.peacetime2=?,k.peacetime3=?,k.peacetime4=?,k.peacetime5=?,k.peacetime16=?,k.peacetime17=?,k.peacetime18=?,k.model_credit=? WHERE k.studentid =? and k.segment_course_id=? and k.cmis30id=? and k.DISCODE=? and k.partid=mod(?,20)";
			 String[]fields={"daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","studentid","segment_course_id","cmis30id","DISCODE","cmis30id"};
			this.batchUpdateObjects(datas,fields,sql);
			
			
		}else if(Constant.KG_COURSE_NEIZHI.equals(courseType)){  //内置课程
			
			
			try{
				List<ModelScoreDto> mds = getNZScoreByClassModelId(class_model_id, disCode, cmis30id);
				List<Map<String,Object>> datasupdate=new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> datasinsert=new ArrayList<Map<String,Object>>();
				if(null != mds && mds.size()>0){
					for(Map<String,Object> data:datas){
						boolean isexsit=false;
						for(ModelScoreDto dto:mds){
							if(NestUtil.isNotEmpty(dto.getStudentid())&&dto.getStudentid().equals(data.get("studentid"))){
								datasupdate.add(data);
								isexsit=true;
								break;
							}
						}
						if(!isexsit)
							datasinsert.add(data);
					}
				}
				if(null != datasupdate && datasupdate.size()>0){
					sql  = "UPDATE k_student_model k SET k.daily_behave=?,k.examine_result=?,k.absence_ration=?,k.is_pass=?,k.course_kind=?,k.course_name=?,k.credit_hour=?,k.credit_source=?,k.peacetime1=?,k.peacetime2=?,k.peacetime3=?,k.peacetime4=?,k.peacetime5=?,k.peacetime16=?,k.peacetime17=?,k.peacetime18=?,k.model_credit=?,k.class_model_id=? WHERE k.studentid =? and k.class_model_id=? and k.cmis30id=? and k.DISCODE=? and k.partid=mod(?,20)";
					 String[]fields={"daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","class_model_id","studentid","class_model_id","cmis30id","DISCODE","cmis30id"};
					this.batchUpdateObjects(datasupdate,fields,sql);
				}
				if(null != datasinsert && datasinsert.size()>0){
					 sql="insert into k_student_model(credit_id,studentid,classid,class_model_id,daily_behave,examine_result,absence_ration,is_pass,course_kind,course_name,credit_hour,credit_source,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,peacetime16,peacetime17,peacetime18,model_credit,cmis30id,DISCODE) values(S_K_STUDENT_MODEL.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 String[]fields={"studentid","classid","class_model_id","daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","cmis30id","DISCODE"};
					 this.batchUpdateObjects(datasinsert,fields,sql);
				}else if(mds==null||mds.isEmpty()){
					 sql="insert into k_student_model(credit_id,studentid,classid,class_model_id,daily_behave,examine_result,absence_ration,is_pass,course_kind,course_name,credit_hour,credit_source,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,peacetime16,peacetime17,peacetime18,model_credit,cmis30id,DISCODE) values(S_K_STUDENT_MODEL.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 String[]fields={"studentid","classid","class_model_id","daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","cmis30id","DISCODE"};
					 this.batchUpdateObjects(datas,fields,sql);
				}
				
			}catch(Exception e){
				logger.error("insertNZScore(List,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	
	@Override
	public void updateStudentScoreClassModel(List<String> classmodelids,String class_model_id) {
		if(classmodelids==null||classmodelids.size()<1)return;
		Map<String,Object> params=new HashMap<String,Object>();
		if(classmodelids==null||classmodelids.size()<1) classmodelids.add("-1");
		params.put("class_model_ids", classmodelids);
		params.put("class_model_id", class_model_id);
		try{
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.updateStudentScoreClassModel.update");
			this.getJdbcTemplate().update(sqlDemo.getSql(), sqlDemo.getParams());
		}catch(Exception e){
			logger.error("updateStudentScoreClassModel(List<String>,String)",e);
			throw new ManagerException(e);
		}
	}

	/**
	 * 内置课程根据ClassModelId查询成绩
	 */
	@DataSource("read")
	public List<ModelScoreDto> getNZScoreByClassModelId(String class_model_id,
			String discode, String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("class_model_id", class_model_id);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getNZScoreByClassModelId.query");
			return this.findList("ScoreExtImpl.getNZScoreByClassModelId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setStudentid(rs.getString("studentid"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getNZScoreByClassModelId(String,String,String)", e);
		}
		return null;	
		
	}

	/**
	 * 根据界别、班级号、姓名查询学生
	 * @param cmis30id
	 * @param disCode
	 * @param studentNames
	 * @param graduateyears
	 * @param classnums
	 * @return
	 */
	/*@DataSource("read")*/
	public List<ModelScoreDto> getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(
			String cmis30id, String discode, List<String> studentNames,
			List<String> graduateyears, List<String> classnums,List<String> schoolyears) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("studentNames", studentNames);
			params.put("graduateyears", graduateyears);
			params.put("classnums", classnums);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			params.put("schoolyears", schoolyears);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getStudentInfoByGraduateyearAndClassnumAndNameAndEduId.query");
			return this.findList("ScoreExtImpl.getStudentInfoByGraduateyearAndClassnumAndNameAndEduId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto dto = new ModelScoreDto();
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentName(rs.getString("studentName"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setCircle(rs.getString("circle"));
					dto.setClassNum(rs.getString("classnum"));
					dto.setClassid(rs.getString("classid"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(String,String, List<String>, List<String>, List<String>)", e);
		}
		return null;	
		
	}
	
	/**
	 * 根据学科名称和模块名称
	 * @param cmis30id
	 * @param courseNames
	 * @param subjectNames
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> getCourseByCourseNameAndSubjectName(
			String cmis30id, List<String> courseNames, List<String> subjectNames) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("courseNames", courseNames);
			params.put("subjectNames", subjectNames);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getCourseByCourseNameAndSubjectName.query");
			return this.findList("ScoreExtImpl.getCourseByCourseNameAndSubjectName.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeriod_count(rs.getString("period_count"));
					vd.setModel_credit(rs.getString("model_credit"));
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourse_id(rs.getString("course_id"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getCourseByCourseNameAndSubjectName(String,List<String>,List<String>)", e);
		}
		return null;	
	}
	/**
	 * 判断学生是否在校本课程中
	 * @param schoolyearNames
	 * @param segmentNames
	 * @param courseIds
	 * @param classids
	 * @param studentids
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	/*@DataSource("read")*/
	public List<ModelScoreDto> xbCourseIsStudent(List<String> schoolyearNames,
			List<String> segmentNames, List<String> courseIds,
			List<String> classids, List<String> studentids, String discode,
			String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("studentids", studentids);
			params.put("classids", classids);
			params.put("courseIds", courseIds);
			params.put("segmentNames", segmentNames);
			params.put("schoolyearNames", schoolyearNames);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.xbCourseIsStudent.query");
			return this.findList("ScoreExtImpl.xbCourseIsStudent.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setSchoolyearName(rs.getString("schoolyearName"));
					vd.setSegmentName(rs.getString("segmentName"));
					vd.setCourse_id(rs.getString("course_id"));
					vd.setClassid(rs.getString("classid"));
					vd.setStudentid(rs.getString("studentid"));
					vd.setSegment_course_id(rs.getString("segment_course_id"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("xbCourseIsStudent(List<String>,List<String>,List<String>,List<String>,List<String>,String,String)", e);
		}
		return null;	
		
	}
	/**
	 * 判断学生是否在内置课程中
	 * @param schoolyearNames
	 * @param segmentNames
	 * @param courseIds
	 * @param classids
	 * @param studentids
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	/*@DataSource("read")*/
	public List<ModelScoreDto> nZCourseIsStudent(List<String> schoolyearNames,
			List<String> segmentNames, List<String> courseIds,
			List<String> classids, List<String> studentids, String discode,
			String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("studentids", studentids);
			if(null==classids || classids.size()==0){
				classids = new ArrayList<String>();
				classids.add("-1");
			}
			params.put("classids", classids);
			params.put("courseIds", courseIds);
			params.put("segmentNames", segmentNames);
			params.put("schoolyearNames", schoolyearNames);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.nZCourseIsStudent.query2");
			return this.findList("ScoreExtImpl.nZCourseIsStudent.query2", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setSchoolyearName(rs.getString("schoolyearName"));
					vd.setSegmentName(rs.getString("segmentName"));
					vd.setCourse_id(rs.getString("course_id"));
					vd.setClassid(rs.getString("classid"));
					vd.setStudentid(rs.getString("studentid"));
					vd.setClass_model_id(rs.getString("class_model_id"));
					vd.setSchoolyear(rs.getString("schoolyear"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("nZCourseIsStudent(List<String>,List<String>,List<String>,List<String>,List<String>,String,String)", e);
		}
		return null;	
		
	}
	
	
	/**
	 * 导入成绩
	 * @param modelScores
	 * @param courseType
	 */
	public void exportScore(List<ModelScoreDto> modelScores,String courseType,String enteringScore,String cmis30id,String disCode,Boolean isInsert){
		List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		for(ModelScoreDto dto:modelScores){
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("studentid",dto.getStudentid());
			data.put("classid",dto.getClassid());
			data.put("class_model_id",dto.getClass_model_id());
			data.put("segment_course_id", dto.getSegment_course_id());
			data.put("daily_behave",dto.getDaily_behave());
			data.put("examine_result", dto.getExamine_result());
			if(!(null==dto.getCql() ||"".equals(dto.getCql()))){
				data.put("absence_ration",100.0000-Double.parseDouble(dto.getCql().split("%")[0]));
			}
			if("否".equals(dto.getIs_pass())){
				data.put("is_pass", "1");
			}else if("是".equals(dto.getIs_pass())){
				data.put("is_pass", "0");
			}
			data.put("course_kind", dto.getCourse_kind());
			data.put("course_name", dto.getCourse_name());
			data.put("credit_hour", dto.getCredit_hour());
			if("是".equals(dto.getCredit_source())){
				data.put("credit_source",Constant.KG_CREDIT_SOURCE_EXEMPTION);
			}else if("否".equals(dto.getCredit_source())){
				if(Constant.KG_CREDIT_SOURCE_STUDY.equals(enteringScore)){
					data.put("credit_source",Constant.KG_CREDIT_SOURCE_STUDY);
				}else{
				   data.put("credit_source",Constant.KG_CREDIT_SOURCE_EXPORT);
				}
				
			}
			data.put("peacetime1", dto.getPeacetime1());
			data.put("peacetime2", dto.getPeacetime2());
			data.put("peacetime3", dto.getPeacetime3());
			data.put("peacetime4", dto.getPeacetime4());
			data.put("peacetime5", dto.getPeacetime5());
			data.put("peacetime16",dto.getPeacetime16());
			if(null == dto.getQqxs() || "".equals(dto.getQqxs())){
				dto.setQqxs("0");
			}
			if(!(null==dto.getQqxs() ||"".equals(dto.getQqxs()))){
				double  period  =   Double.parseDouble(dto.getPeriod_count())-Double.parseDouble(dto.getQqxs());  
				BigDecimal  b   =   new   BigDecimal(period);  
				period   =   b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
				if(period<=0.00){
					period=0.00;
				}
				data.put("peacetime17",period);
			}
			data.put("peacetime18",dto.getPeacetime18());
			data.put("model_credit",dto.getModel_credit());
			data.put("cmis30id",cmis30id);
			data.put("DISCODE",disCode);
			datas.add(data);
		}
		String sql = "";
		if(Constant.KG_COURSE_KIND.equals(courseType)){  //校本课程
			
			sql  = "UPDATE k_student_matriculate k SET k.daily_behave=?,k.examine_result=?,k.absence_ration=?,k.is_pass=?,k.course_kind=?,k.course_name=?,k.credit_hour=?,k.credit_source=?,k.peacetime1=?,k.peacetime2=?,k.peacetime3=?,k.peacetime4=?,k.peacetime5=?,k.peacetime16=?,k.peacetime17=?,k.peacetime18=?,k.model_credit=? WHERE k.studentid =? and k.segment_course_id=? and k.cmis30id=? and k.DISCODE=? and k.partid=mod(?,20)";
			 String[]fields={"daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","studentid","segment_course_id","cmis30id","DISCODE","cmis30id"};
			this.batchUpdateObjects(datas,fields,sql);
			
			
		}else if(Constant.KG_COURSE_NEIZHI.equals(courseType)){  //内置课程
			try{
				if(!isInsert){
					sql  = "UPDATE k_student_model k SET k.daily_behave=?,k.examine_result=?,k.absence_ration=?,k.is_pass=?,k.course_kind=?,k.course_name=?,k.credit_hour=?,k.credit_source=?,k.peacetime1=?,k.peacetime2=?,k.peacetime3=?,k.peacetime4=?,k.peacetime5=?,k.peacetime16=?,k.peacetime17=?,k.peacetime18=?,k.model_credit=? WHERE k.studentid =? and k.class_model_id=? and k.cmis30id=? and k.DISCODE=? and k.partid=mod(?,20)";
					 String[]fields={"daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","studentid","class_model_id","cmis30id","DISCODE","cmis30id"};
					this.batchUpdateObjects(datas,fields,sql);
				}else{
					 sql="insert into k_student_model(credit_id,studentid,classid,class_model_id,daily_behave,examine_result,absence_ration,is_pass,course_kind,course_name,credit_hour,credit_source,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,peacetime16,peacetime17,peacetime18,model_credit,cmis30id,DISCODE) values(S_K_STUDENT_MODEL.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 String[]fields={"studentid","classid","class_model_id","daily_behave","examine_result","absence_ration","is_pass","course_kind","course_name","credit_hour","credit_source","peacetime1","peacetime2","peacetime3","peacetime4","peacetime5","peacetime16","peacetime17","peacetime18","model_credit","cmis30id","DISCODE"};
					 this.batchUpdateObjects(datas,fields,sql);
				}
				
			}catch(Exception e){
				logger.error("insertNZScore(List,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	/**
	 * 学生查询内置课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetNZScore(
			String segment_id, String classid, String edu_id,
			String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("classid", classid);
			params.put("edu_id", edu_id);
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.studentGetNZScore.query1");
			System.out.println(sqlDemo.getSql());
			return this.findList("ScoreExtImpl.studentGetNZScore.query1", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					
					vd.setLevel_name(rs.getString("level_name"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetNZScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 学生查询内置课程成绩 
	 * @param courseName
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetNZScore(String subjectName,
			String segment_id, String classid, List<String> edu_ids,
			String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("subjectName", subjectName);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("classid", classid);
			params.put("edu_ids", edu_ids);
			return this.findList("ScoreExtImpl.studentGetNZScore.query2", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setEdu_id(rs.getString("edu_id"));
					vd.setCredit_id(rs.getString("credit_id"));
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					
					vd.setLevel_name(rs.getString("level_name"));
					if(NestUtil.isEmpty(rs.getString("old_credit_id"))){
						vd.setSource("系统");
					}else{
						vd.setSource("历史库");
					}
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetNZScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 学生查询内置课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetNZScore(
			String segment_id, String classid, List<String> edu_ids,
			String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("classid", classid);
			params.put("edu_ids", edu_ids);
			return this.findList("ScoreExtImpl.studentGetNZScore.query2", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setEdu_id(rs.getString("edu_id"));
					vd.setCredit_id(rs.getString("credit_id"));
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					
					vd.setLevel_name(rs.getString("level_name"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetNZScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 学生查询校本课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetSchoolScore(String segment_id,
			String classid, String edu_id, String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("classid", classid);
			params.put("edu_id", edu_id);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.studentGetSchoolScore.query");
			return this.findList("ScoreExtImpl.studentGetSchoolScore.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					
					vd.setLevel_name(rs.getString("level_name"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetSchoolScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 学生查询校本课程成绩 
	 * @param subjectName
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetSchoolScore(String subjectName, String segment_id,
			String classid, List<String> edu_ids, String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("subjectName", subjectName);
			params.put("classid", classid);
			params.put("edu_ids", edu_ids);
			
			return this.findList("ScoreExtImpl.studentGetSchoolScore.query2", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setEdu_id(rs.getString("edu_id"));
					vd.setCredit_id(rs.getString("matriculate_id"));
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					if(NestUtil.isEmpty(rs.getString("old_matriculate_id"))){
						vd.setSource("系统");
					}else{
						vd.setSource("历史库");
					}
					vd.setLevel_name(rs.getString("level_name"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetSchoolScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 学生查询校本课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> studentGetSchoolScore(String segment_id,
			String classid, List<String> edu_ids, String cmis30id, String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("segment_id", segment_id);
			params.put("classid", classid);
			params.put("edu_ids", edu_ids);
			
			return this.findList("ScoreExtImpl.studentGetSchoolScore.query2", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setEdu_id(rs.getString("edu_id"));
					vd.setCredit_id(rs.getString("matriculate_id"));
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setCourseName(rs.getString("courseName"));
					vd.setPeacetime1(rs.getString("peacetime1"));
					vd.setPeacetime2(rs.getString("peacetime2"));
					vd.setPeacetime3(rs.getString("peacetime3"));
					vd.setPeacetime4(rs.getString("peacetime4"));
					vd.setPeacetime5(rs.getString("peacetime5"));
					vd.setDaily_behave(rs.getString("daily_behave"));
					vd.setPeacetime16(rs.getString("peacetime16"));
					vd.setQqxs(rs.getString("qqxs"));
					if(null==vd.getQqxs() ||"".equals(vd.getQqxs())||"0".equals(vd.getQqxs())){
						vd.setCql("100%");
					}else{
						vd.setCql(rs.getString("cql")+"%");
					}
					vd.setCourse_kind(rs.getString("course_kind"));
					vd.setExamine_result(rs.getString("examine_result"));
					vd.setPeacetime18(rs.getString("peacetime18"));
					vd.setCredit_hour(rs.getDouble("credit_hour")+"");
					vd.setCredit_source(rs.getString("credit_source"));
					vd.setStudentName(rs.getString("studentName"));
					if(null!=vd.getCredit_source() && vd.getCredit_source().equals(Constant.KG_CREDIT_SOURCE_EXEMPTION)){
						vd.setCredit_source("是");
					}else{
						vd.setCredit_source("否");
					}
					vd.setIs_pass(rs.getString("is_pass"));
					if(null!=vd.getIs_pass() && vd.getIs_pass().equals("0")){
						vd.setIs_pass("是");
					}else{
						vd.setIs_pass("否");
					}
					
					vd.setLevel_name(rs.getString("level_name"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("studentGetSchoolScore(String,String,String,String,String,String)", e);
		}
		return null;	
	}
	/**
	 * 根据eduid获取学生名称
	 * @param cmis30id
	 * @param discode
	 * @param eduId
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> GetStudentNameByEduId(String cmis30id,
			String discode, String eduId) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("edu_id", eduId);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.GetStudentNameByEduId.query");
			return this.findList("ScoreExtImpl.GetStudentNameByEduId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					
					vd.setStudentName(rs.getString("studentName"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("GetStudentNameByEduId(String,String,String)", e);
		}
		
		return null;
	}

	/**
	 * 根据学科名称集和学生id集获得系统学科和会考成绩
	 * @param subjectNames
	 * @param studentids
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	@DataSource("read")
	public List<ModelScoreDto> getSubjectName(
			List<String> subjectNames) {
		
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("subjectNames", subjectNames);
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getSubjectName.query");
			return this.findList("ScoreExtImpl.getSubjectName.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setSubjectName(rs.getString("subjectName"));
					vd.setSubjectId(rs.getString("subjectId"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getSubjectName(List<String>)", e);
		}
		
		return null;
		
		
	}
	/**
	 * 查询会考成绩
	 */
	public List<ModelScoreDto> getGENERAL_EXAMINATION_SCORE(
			List<String> studentids, List<String> subjectIds, String cmis30id,
			String discode) {

		try {
			Map<String,Object>params = new HashMap<String, Object>();
			Map<String,Object> outDoubleDatas=new HashMap<String,Object>();
			Map<String,Object> outSubDoubleDatas=new HashMap<String,Object>();
			List<String>studentids1=new ArrayList<String>();
			List<String>subjectIds1=new ArrayList<String>();
			if(studentids!=null&&studentids.size()>0){
				for(String studentid:studentids){
					outDoubleDatas.put(studentid,new Object());
				}
			}
			studentids1.addAll(outDoubleDatas.keySet());
			if(subjectIds!=null&&subjectIds.size()>0){
				for(String subjectId:subjectIds){
					outSubDoubleDatas.put(subjectId,new Object());
				}
			}
			subjectIds1.addAll(outSubDoubleDatas.keySet());
			params.put("studentids", studentids1);
			params.put("subjectIds", subjectIds1);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			
			
			ISqlElement sqlDemo=this.processSql(params, "ScoreExtImpl.getGENERAL_EXAMINATION_SCORE.query");
			return this.findList("ScoreExtImpl.getGENERAL_EXAMINATION_SCORE.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto vd = new ModelScoreDto();
					vd.setStudentid(rs.getString("studentid"));
					vd.setSubjectId(rs.getString("subjectId"));
					vd.setGeneralExaminationScoreId(rs.getString("generalExaminationScoreId"));
					return vd;
				}
			});
		} catch (Exception e) {
			logger.error("getGENERAL_EXAMINATION_SCORE(List<String>,List<String>,String,String)", e);
		}
		
		return null;
	
	}
	
	
	
	/**
	 * 会考成绩插入-更新
	 * @param modelScores
	 * @param courseType
	 */
	public void hKScoreInsertOrUpdate(List<ModelScoreDto> modelScores,String cmis30id,String disCode,Boolean isInsert){
		List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		for(ModelScoreDto dto:modelScores){
			if(NestUtil.isNotEmpty(dto.getLevel_name())){
				Map<String,Object> data=new HashMap<String,Object>();
				data.put("student_id",dto.getStudentid());
				data.put("subject_id",dto.getSubjectId());
				data.put("cmis30id",cmis30id);
				data.put("DISCODE",disCode);
				data.put("LEVEL_NAME", dto.getLevel_name());
				data.put("GENERAL_EXAMINATION_SCORE_ID", dto.getGeneralExaminationScoreId());
				datas.add(data);
			}
		}
		String sql = "";
	
			try{
				if(!isInsert){
					sql  = "UPDATE K_GENERAL_EXAMINATION_SCORE k SET k.LEVEL_NAME=?,MODIFY_TIME=sysdate WHERE k.GENERAL_EXAMINATION_SCORE_ID=?";
					 String[]fields={"LEVEL_NAME","GENERAL_EXAMINATION_SCORE_ID"};
					this.batchUpdateObjects(datas,fields,sql);
				}else{
					 sql="insert into K_GENERAL_EXAMINATION_SCORE(GENERAL_EXAMINATION_SCORE_ID,student_id,subject_id,LEVEL_NAME,MODIFY_TIME,cmis30id,DISCODE) values(S_K_K_GENERAL_SCORE.nextval,?,?,?,sysdate,?,?)";
					 String[]fields={"student_id","subject_id","LEVEL_NAME","cmis30id","DISCODE"};
					 this.batchUpdateObjects(datas,fields,sql);
				}
				
			}catch(Exception e){
				logger.error("hKScoreInsertOrUpdate(List,String,String,Boolean)", e);
				throw new ManagerException(e);
			}
		}

	@Override
	public List<String> queryUserRoleType(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "IScoreExt.queryUserRoleType.query");
			return this.findList("IScoreExt.queryUserRoleType.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("roletype");
				}
			});
		} catch (Exception e) {
			logger.error("queryUserRoleType(Map<String, Object>)", e);
		}
		return null;
	}

	@Override
	public List<ModelScoreDto> queryStudyProblems(List<String> schoolyearNames,
			List<String> segmentNames, List<String> studyTopic,
			String cmis30id, String discode) {
		
		return null;
	}


	@Override
	public void saveOrUpdateScores(Map<String, Object> params) {
		/*params.put("practices", practices);
		params.put("servers", servers);
		params.put("study", study);
		params.put("userDto", userDto);//当前登录用户信息
		*/
		Map<String,Object>queryConditions = new HashMap<String, Object>();
		queryConditions.put("cmis30Id", (String)params.get("cmis30id"));
		Map<String,List<ModelScoreDto>> specailSubjectsScores = this.querSpecailSubjectsScores(queryConditions);
		List<String> segmentInfos = this.querSegmentInfos(queryConditions);
		//插入成绩
		insertScores = new ArrayList<ModelScoreDto>();
		//更新成绩
		updateScores = new ArrayList<ModelScoreDto>();
		sementModelIds = new HashMap<String, String>();
		classModelIds = new HashMap<String, String>();
		insertSementModelIds = new HashMap<String, String>();
		insertClassModelIds = new HashMap<String, String>();
		this.dealWithStudyProblems(params,specailSubjectsScores.get("研究性学习活动"),segmentInfos);
		this.dealWithPractices(params,specailSubjectsScores.get("社会实践"),segmentInfos);
		this.dealWithServers(params,specailSubjectsScores.get("社区服务"),segmentInfos);
	    this.initDatas(params);
	    this.dealWithSpecailSubjectScore(params);
		this.dealWithNzOrXbScorse(params);
	}
	/**
	 * 处理社会实践
	 * @param params
	 * @param specailSubjectsScores
	 * @param segmentInfos
	 */
	private void dealWithServers(Map<String, Object> params,
			List<ModelScoreDto> specailSubjectsScores, List<String> segmentInfos) {
		@SuppressWarnings("unchecked")
		List<ModelScoreDto> servers = (List<ModelScoreDto>)params.get("servers");
		this.commonInitSpecailDatas(specailSubjectsScores, segmentInfos, servers);
	}

	/**
	 * 处理社会实践
	 * @param params
	 * @param specailSubjectsScores
	 * @param segmentInfos
	 */
	private void dealWithPractices(Map<String, Object> params,List<ModelScoreDto> specailSubjectsScores, List<String> segmentInfos) {
		@SuppressWarnings("unchecked")
		List<ModelScoreDto> practices = (List<ModelScoreDto>)params.get("practices");
		this.commonInitSpecailDatas(specailSubjectsScores, segmentInfos, practices);
	}

	private void dealWithSpecailSubjectScore(Map<String,Object>params) {
		this.exportScore(updateScores, Constant.KG_COURSE_NEIZHI, Constant.KG_CREDIT_SOURCE_EXPORT, (String)params.get("cmis30id"), (String)params.get("discode"),false);
		this.exportScore(insertScores, Constant.KG_COURSE_NEIZHI, Constant.KG_CREDIT_SOURCE_EXPORT, (String)params.get("cmis30id"), (String)params.get("discode"),true);
	}

	/**
	 * 初始化 学段模块授课表
	 */
	private void initDatas(final Map<String,Object>params) {
		//学段模块表初始化
		if(null!=insertSementModelIds && insertSementModelIds.size()>0){
			try {
				Set<String> keySet = insertSementModelIds.keySet();
				final List<String>smentModelIds = new ArrayList<String>();
				for(String key : keySet){
					smentModelIds.add(insertSementModelIds.get(key));
				}
				ISqlElement sqlElement=this.processSql(params,"IScoreExt.insertSegmentModel.insert");
				getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						String smentModelId = smentModelIds.get(i);
						String[] modelIds = smentModelId.split("@");
						if(null!=modelIds && modelIds.length==3){
							ps.setString(1, modelIds[0]);
							ps.setString(2, modelIds[1]);
							ps.setString(3, modelIds[2]);
						}
					}
					public int getBatchSize() {
						return smentModelIds.size();
					}
				});
			} catch (Exception e) {
				logger.error("initDatas(Map<String, Object>).IScoreExt.insertSegmentModel.insert",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
		if(null!=insertClassModelIds && insertClassModelIds.size()>0){
			try {
				Set<String> keySet = insertClassModelIds.keySet();
				final List<String>classModelIds = new ArrayList<String>();
				for(String key : keySet){
					classModelIds.add(insertClassModelIds.get(key));
				}
				ISqlElement sqlElement=this.processSql(params,"IScoreExt.insertClassModel.insert");
				getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						String classModelId = classModelIds.get(i);
						String[] modelIds = classModelId.split("@");
						if(null!=modelIds && modelIds.length==4){
							ps.setString(1, modelIds[0]);
							ps.setString(2, modelIds[1]);
							if("0".equals(modelIds[3])){
								ps.setString(3, null);
							}else{
								ps.setString(3, modelIds[3]);
							}
							ps.setString(4, modelIds[2]);
							ps.setString(5,(String)params.get("cmis30id"));
							ps.setString(6,(String)params.get("discode"));
						}
					}
					public int getBatchSize() {
						return classModelIds.size();
					}
				});
			} catch (Exception e) {
				logger.error("initDatas(Map<String, Object>).IScoreExt.insertClassModel.insert",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
	}
	private List<ModelScoreDto>insertScores = null;
	private List<ModelScoreDto>updateScores = null;
	//授课classModelId
	private Map<String,String>classModelIds = null;
	//需要插入的学段开设模块id
	private Map<String,String>sementModelIds = null;
	private Map<String,String>insertSementModelIds = null;
	private Map<String,String>insertClassModelIds = null;
	/**
	 * 处理研究性学习活动
	 * @param params
	 * @param specailSubjectsScores
	 */
	private void dealWithStudyProblems(Map<String, Object> params,List<ModelScoreDto> specailSubjectsScores,List<String>segmentInfos) {
		@SuppressWarnings("unchecked")
		List<ModelScoreDto> studies = (List<ModelScoreDto>)params.get("study");
		this.commonInitSpecailDatas(specailSubjectsScores, segmentInfos, studies);
	}
	/**
	 * 根据学年获取当前班的历史班级id
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	public String queryClassIdBySchoolyearAndClassid(String schoolyear,String classid){
		List<String> lists = this.getClassIdBySchoolyearAndClassid(schoolyear,classid);
		if(null!=lists && lists.size()>0){
			return lists.get(0);
		}
		return null;
	}
	private void commonInitSpecailDatas(
			List<ModelScoreDto> specailSubjectsScores,
			List<String> segmentInfos, List<ModelScoreDto> studies) {
		if(null!=studies && studies.size()>0){
			if(null!=specailSubjectsScores){
				//挑选出指定学段下没有的
				//挑选出没有授课的
				for(ModelScoreDto studyDto : studies){
					String segmentId = this.sgementOrderToSegmentId(studyDto,segmentInfos);
					boolean isExist = false;//该课段模块不存在
					boolean isInsert = false;//该学生成绩是否该插入
					boolean isModelClassIdExsit = false;//授课classmodelid是否存在
					String insertClassModelId = "";
					String insertSegmenModelId = "";
					for(ModelScoreDto conmmonDto : specailSubjectsScores){
						if(studyDto.getSchoolyearName().equals(conmmonDto.getSchoolyearName())//学年
								&&studyDto.getSegmentName().equals(conmmonDto.getSegmentName())//学段
								&&studyDto.getCourse_id().equals(conmmonDto.getCourse_id())&&NestUtil.isNotEmpty(conmmonDto.getStudentid())
								&&conmmonDto.getStudentid().equals(studyDto.getStudentid())){//模块id
						    //说明该学段下有该模块
							isExist = true;//该课段模块存在
							insertSegmenModelId = conmmonDto.getSement_model_id();
							if(NestUtil.isNotEmpty(conmmonDto.getClass_model_id())){
								if(NestUtil.isEmpty(conmmonDto.getSchoolyear())){
									//历史班级id存在
									String histrotyClassId = queryClassIdBySchoolyearAndClassid(studyDto.getSchoolyearName(),studyDto.getClassid());
									if(NestUtil.isNotEmpty(histrotyClassId)&&histrotyClassId.equals(conmmonDto.getClassid())){
										//说明授课模块这里有数据
										isModelClassIdExsit = true;
										insertClassModelId = conmmonDto.getClass_model_id();
										if(NestUtil.isNotEmpty(conmmonDto.getStudentid())
												&&conmmonDto.getStudentid().equals(studyDto.getStudentid())){
											//说明该学生成绩已经存在于此，执行更新（记录更新）
											studyDto.setClass_model_id(conmmonDto.getClass_model_id());
											updateScores.add(studyDto);
											isInsert = true;//该更新
											break;
										}
									}
								}else{
									//历史班级id不存在
									String nowClassid=conmmonDto.getClassid();
									if(NestUtil.isNotEmpty(nowClassid)&&nowClassid.equals(studyDto.getClassid())){
										//说明授课模块这里有数据
										isModelClassIdExsit = true;
										insertClassModelId = conmmonDto.getClass_model_id();
										if(NestUtil.isNotEmpty(conmmonDto.getStudentid())
												&&conmmonDto.getStudentid().equals(studyDto.getStudentid())){
											//说明该学生成绩已经存在于此，执行更新（记录更新）
											studyDto.setClass_model_id(conmmonDto.getClass_model_id());
											updateScores.add(studyDto);
											isInsert = true;//该更新
											break;
										}
									}
								}
							}
						}
					}
					
					if(!isExist){
						String courseId = studyDto.getCourse_id();
						String schoolYear = studyDto.getSchoolyearName();
						//1.没有该模块，需要插入
						String smentModelIdsKey = courseId+"_"+segmentId+"_"+schoolYear;
						String smentModelId = sementModelIds.get(smentModelIdsKey);
						if(NestUtil.isEmpty(smentModelId)){
							//获取UUID 并放入sementModelIds中
							smentModelId = this.getUUID();
							sementModelIds.put(smentModelIdsKey, smentModelId);
							insertSementModelIds.put(smentModelIdsKey, smentModelId+"@"+segmentId+"@"+courseId);
						}
						studyDto.setSement_model_id(smentModelId);
						//2.插入授课
						String historyYear = "0";
						String classId = studyDto.getClassid();
						String classModelIdsKey = smentModelId+"_"+classId;
						String classModelId = classModelIds.get(classModelIdsKey);
						if(NestUtil.isEmpty(classModelId)){
							classModelId = this.getUUID();
							classModelIds.put(classModelIdsKey, classModelId);
							String histrotyClassId = queryClassIdBySchoolyearAndClassid(studyDto.getSchoolyearName(), classId);
							if(NestUtil.isEmpty(histrotyClassId)){
								historyYear = studyDto.getSchoolyearName();
								histrotyClassId = classId;
							}
							insertClassModelIds.put(classModelIdsKey, classModelId+"@"+smentModelId+"@"+histrotyClassId+"@"+historyYear);
						}
						studyDto.setClass_model_id(classModelId);
						studyDto.setSegment_course_id(segmentId);
						//3.插入成绩
						insertScores.add(studyDto);
					}
					if(isExist && !isModelClassIdExsit){
						//1.需要插入授课模块
						String classId = studyDto.getClassid();
						String smentModelId = insertSegmenModelId;
						String classModelIdsKey = smentModelId+"_"+classId;
						String classModelId = classModelIds.get(classModelIdsKey);
						String historyYear = "0";
						if(NestUtil.isEmpty(classModelId)){
							classModelId = this.getUUID();
							classModelIds.put(classModelIdsKey, classModelId);
							String histrotyClassId = queryClassIdBySchoolyearAndClassid(studyDto.getSchoolyearName(), classId);
							if(NestUtil.isEmpty(histrotyClassId)){
								histrotyClassId = classId;
								historyYear = studyDto.getSchoolyearName();
							}
							insertClassModelIds.put(classModelIdsKey, classModelId+"@"+smentModelId+"@"+histrotyClassId+"@"+historyYear);
						}
						studyDto.setClass_model_id(classModelId);
						studyDto.setSegment_course_id(segmentId);
						//2.插入成绩
						insertScores.add(studyDto);
					}
					if(isExist && isModelClassIdExsit && !isInsert){
						//说明该条记录还没有数据记录，执行插入（记录插入）
						insertScores.add(studyDto);
						studyDto.setClass_model_id(insertClassModelId);
					}
				}
			}
		}
	}

	private String sgementOrderToSegmentId(ModelScoreDto studyDto,List<String> segmentInfos) {
		if(null!=segmentInfos && segmentInfos.size()>0){
			for(String segmentInfo : segmentInfos){
				String[] segments = segmentInfo.split("@");
				if(null!=segments && segments.length==3
						&&segments[0].equals(studyDto.getSchoolyearName())
						&&segments[1].equals(studyDto.getSegmentName())){
					return segments[2];
				}
			}
		}
		return null;
	}

	private String getUUID() {
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            UUID uuid = UUID.randomUUID(); 
            String guidStr = uuid.toString(); 
            md.update(guidStr.getBytes(), 0, guidStr.length()); 
            return new BigInteger(1, md.digest()).toString(16); 
        } catch (NoSuchAlgorithmException e) { 
            return null; 
        } 
	}

	/**
	 * 查询社会实践、社区服务、研究性学习活动授课情况及成绩录入情况
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unused")
	@DataSource("read")
    private Map<String,List<ModelScoreDto>>querSpecailSubjectsScores(Map<String,Object>params){
		final Map<String,List<ModelScoreDto>> data=new HashMap<String,List<ModelScoreDto>>();
		try {
			ISqlElement sqlDemo=this.processSql(params, "IScoreExt.querSpecailSubjectsScores.query");
			 this.findList("IScoreExt.querSpecailSubjectsScores.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					ModelScoreDto msDto = new ModelScoreDto();
					msDto.setSchoolyearName(rs.getString("schoolyear"));
					msDto.setCourse_name(rs.getString("course_name"));
					msDto.setCourse_id(rs.getString("course_id"));
					msDto.setSegment_course_id(rs.getString("segment_id"));
					msDto.setSegmentName(rs.getString("segment_order"));
					msDto.setSement_model_id(rs.getString("sement_model_id"));
					msDto.setClass_model_id(rs.getString("class_model_id"));
					msDto.setStudentid(rs.getString("studentid"));
					msDto.setSchoolyear(rs.getString("historySchoolYears"));
					msDto.setClassid(rs.getString("classid"));
					List<ModelScoreDto> dt=data.get(rs.getString("subject_name"));
					if(dt==null){
						dt=new ArrayList<ModelScoreDto>();
						data.put(rs.getString("subject_name"),dt);
					}
					dt.add(msDto);
					return msDto;
				}
			});
		} catch (Exception e) {
			logger.error("IScoreExt.querSpecailSubjectsScores(Map<String,Object>)", e);
		}
    	return data;
    }
	/**
	 * 插入或者更新校本、内置课程成绩
	 * @param params
	 */
	@SuppressWarnings("unchecked")
	private void dealWithNzOrXbScorse(Map<String, Object> params) throws ManagerException{
		this.exportScore((List<ModelScoreDto>)params.get("xBExcelCourseInfo"), Constant.KG_COURSE_KIND, Constant.KG_CREDIT_SOURCE_EXPORT, (String)params.get("cmis30id"), (String)params.get("discode"),false);
		this.exportScore((List<ModelScoreDto>)params.get("nZ1ExcelCourseInfo"), Constant.KG_COURSE_NEIZHI, Constant.KG_CREDIT_SOURCE_EXPORT, (String)params.get("cmis30id"), (String)params.get("discode"),true);
		this.exportScore((List<ModelScoreDto>)params.get("nZ2ExcelCourseInfo"), Constant.KG_COURSE_NEIZHI, Constant.KG_CREDIT_SOURCE_EXPORT, (String)params.get("cmis30id"), (String)params.get("discode"),false);
	}
	@SuppressWarnings("unused")
	@DataSource("read")
	private List<String>querSegmentInfos(Map<String,Object>params){
		try {
			ISqlElement sqlDemo=this.processSql(params, "IScoreExt.querSegmentInfos.query");
			return this.findList("IScoreExt.querSegmentInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("segmentInfos");
				}
			});
		} catch (Exception e) {
			logger.error("IScoreExt.querSegmentInfos(Map<String,Object>)", e);
		}
    	return null;
	}
	@DataSource("read")
	private void delnZCourseById(List<String> ids) {
		if(ids==null||ids.size()<1)
			ids.add("-1");
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("ids",ids);
		try{
			ISqlElement sqlDemo=this.processSql(params, "IScoreExt.delnZCourseById.query");
			this.getJdbcTemplate().update(sqlDemo.getSql(),sqlDemo.getParams());
		}catch(Exception e){
			logger.error("delnZCourseById(List<String>)",e);
			throw new ManagerException(e);
		}
	}
	@DataSource("read")
	private void delxBCourseById(List<String> ids) {
		if(ids==null||ids.size()<1)
			ids.add("-1");
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("ids",ids);
		try{
			ISqlElement sqlDemo=this.processSql(params, "IScoreExt.delxBCourseById.query");
			this.getJdbcTemplate().update(sqlDemo.getSql(),sqlDemo.getParams());
		}catch(Exception e){
			logger.error("delxBCourseById(List<String>)",e);
			throw new ManagerException(e);
		}
	}

	@Override
	public void delCourseById(List<String> nIds, List<String> xIds) {
		//删除内置课程
		delnZCourseById(nIds);
		//删除校本课程
		delxBCourseById(xIds);
	}

	@Override
	public void initClassModelAndSegmentModel(List<ModelScoreDto> dtos,String cmis30id) {
		if(dtos==null||dtos.size()<0) return;
		for(ModelScoreDto dto:dtos){
			//先查询sement_model_id是否存在
			String sement_model_id=querySementModelidIsExist(cmis30id,dto.getSegmentName(),dto.getSchoolyearName(),dto.getCourse_id());
			dto.setSement_model_id(sement_model_id);
			//然后在生成class_model表相关记录
			//1、查找该学生历史班级标识号
			String historyclassid = queryClassIdBySchoolyearAndClassid(dto.getSchoolyearName(),dto.getClassid());
			//2、查找对应的模块是否存在
			boolean isExist=isExistClassModel(historyclassid,dto);
			//3、如果不存在，则生成模块
			if(!isExist)
				generateClassModel(dto,historyclassid);
		}
	}
	
	@Override
	public String getClassModelAndSegmentModelId(ModelScoreDto dto,
			String cmis30id,String historyclassid) {
		//先查询sement_model_id是否存在
		String sement_model_id=querySementModelidIsExist(cmis30id,dto.getSegmentName(),dto.getSchoolyearName(),dto.getCourse_id());
		dto.setSement_model_id(sement_model_id);
		//然后在生成class_model表相关记录
		return generateClassModel(dto,historyclassid);
	}

	public String generateClassModel(ModelScoreDto dto,String historyclassid){
		if(dto==null) return null;
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("sement_model_id",dto.getSement_model_id());
		params.put("teacherid",null);
		if(NestUtil.isEmpty(historyclassid)){
			params.put("classid",dto.getClassid());
			params.put("schoolyear",dto.getSchoolyearName());
		}else{
			params.put("classid",historyclassid);
		}
		params.put("need_assign","1");
		params.put("is_assigned","1");
		params.put("assign_model","1");
		params.put("special","1");
		try{
			//先获取序列号
			String proKey=getProKey();
			params.put("class_model_id",proKey);
			dto.setClass_model_id(proKey);
			ISqlElement sqlDemo=this.processSql(params,"IScoreExt.generateClassModel.add");
			this.getJdbcTemplate().update(sqlDemo.getSql(),sqlDemo.getParams());
			return proKey;
		}catch(Exception e){
			logger.error("generateClassModel(ModelScoreDto,String)",e);
			throw new ManagerException(e);
		}
	}
	private String getProKey(){
		Map<String,Object>params=new HashMap<String,Object>();
		try{
			List<String> class_model_ids=this.findList("IScoreExt.getProKey.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)throws SQLException {
						return rs.getString("class_model_id");
					}
				}
			);
			if(class_model_ids!=null&&class_model_ids.size()>0)
				return class_model_ids.get(0);
		}catch(Exception e){
			logger.error("getProKey(String,ModelScoreDto)",e);
			throw new ManagerException(e);
		}
		return null;
	}
	public boolean isExistClassModel(String historyclassid,ModelScoreDto dto){
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("historyclassid",historyclassid);
		params.put("sement_model_id",dto.getSement_model_id());
		params.put("schoolyear",dto.getSchoolyearName());
		params.put("classid",dto.getClassid());
		try{
			List<String> class_model_ids=this.findList("IScoreExt.isExistClassModel.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)throws SQLException {
						return rs.getString("class_model_id");
					}
				}
			);
			if(class_model_ids==null
					||class_model_ids.size()<1)
					return false;
			else return true;
		}catch(Exception e){
			logger.error("isExistClassModel(String,ModelScoreDto)",e);
			throw new ManagerException(e);
		}
	}
	  public String getId() { 
	        try { 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	            UUID uuid = UUID.randomUUID(); 
	            String guidStr = uuid.toString(); 
	            md.update(guidStr.getBytes(), 0, guidStr.length()); 
	            return new BigInteger(1, md.digest()).toString(16); 
	        } catch (NoSuchAlgorithmException e) { 
	            return null; 
	        } 
	    }
	private String querySementModelidIsExist(String cmis30id,String segmentName,String schoolyearName,String course_id){
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("segmentName",segmentName);
		params.put("schoolyearName",schoolyearName);
		params.put("course_id",course_id);
		try{
			List<String> sement_model_ids=this.findList("IScoreExt.initClassModelAndSegmentModel.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)throws SQLException {
						return rs.getString("sement_model_id");
					}
				}
			);
			if(sement_model_ids==null||sement_model_ids.size()<1){
				String sement_model_id=getId();
				params.put("sement_model_id",sement_model_id);
				ISqlElement sqlElement=this.processSql(params,"IScoreExt.querySementModelidIsExist.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return sement_model_id;
			}else{
				return sement_model_ids.get(0);
			}
		}catch(Exception e){
			logger.error("querySementModelidIsExist(String,String,String,String)",e);
			throw new ManagerException(e);
		}
	}

	@Override
	public Map<String, String> queryClassModelAndCreditByCondition(
			ModelScoreDto data,String cmis30id,String discode,String historyclassid) {
		if(data==null)return null;
		final Map<String,String> dat=new HashMap<String,String>();
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("schoolyear",data.getSchoolyearName());
		params.put("segment_order",data.getSegmentName());
		params.put("course_id",data.getCourse_id());
		params.put("studentid",data.getStudentid());
		params.put("classid",data.getClassid());
		params.put("historyclassid",historyclassid);
		try{
			this.findList("IScoreExt.queryClassModelAndCreditByCondition.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)throws SQLException {
						dat.put("class_model_id",rs.getString("class_model_id"));
						dat.put("credit_id",rs.getString("credit_id"));
						return null;
					}
				}
			);
			return dat;
		}catch(Exception e){
			logger.error("queryClassModelAndCreditByCondition(ModelScoreDto,String,String,String)",e);
			throw new ManagerException(e);
		}
	}
	
}
