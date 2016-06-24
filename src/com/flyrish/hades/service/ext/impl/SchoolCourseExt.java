package com.flyrish.hades.service.ext.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.hibernate.IRowHandler;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import sun.rmi.runtime.NewThreadAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KstudentMatriculateDto;
import com.flyrish.hades.dto.KstudySegmentDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.TbaseinfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.util.DataSource;

public class SchoolCourseExt extends JdbcRootManager implements ISchoolCourseExt{
	
	@DataSource("read")
	public List<String> getSchoolYears() {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getSchoolYears.query");
			return this.findList("SchoolCourseExt.getSchoolYears.query", params, new RowMapper(){
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
			logger.error("SchoolCourseExt.getSchoolYears()", e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<KstudySegmentDto> getSegmentInfos(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getSegmentInfos.query");
			
			List<KstudySegmentDto> dtos= this.findList("SchoolCourseExt.getSegmentInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KstudySegmentDto ksd = new KstudySegmentDto();
					ksd.setSegment_id(rs.getString("segmentId"));
					ksd.setSegment_name(rs.getString("name"));
					return ksd ;
				}
			});
			return dtos;
		} catch (Exception e) {
			logger.error("SchoolCourseExt.getSegmentInfos(Map<String,Object>)", e);
		}
		return null;
	}
	/*@DataSource("read")*/
	public IPage selectSchoolCourse(Map<String, Object> queryMap,Integer pageNo, Integer pageSize) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "SchoolCourseExt.getCourseInfos.query");
			/*System.out.println(sqlDemo);*/
			return this.findPage("SchoolCourseExt.getCourseInfos.query","SchoolCourseExt.getCourseInfos.count",queryMap,pageNo,pageSize,new IRowHandler<KcourseDto>(){
				public KcourseDto handleRow(ResultSet rs) {
					try {
						KcourseDto kcourse = new KcourseDto();
						kcourse.setSegmentCourseId(rs.getString("segment_course_id"));
						kcourse.setSchoolyearname(rs.getString("schoolyearname"));
						kcourse.setSchoolyear(rs.getString("schoolyear"));
						kcourse.setSegmentName(rs.getString("segmentName"));
						kcourse.setSegment_id(rs.getString("segment_id"));
						kcourse.setTeacherName(rs.getString("teacherName"));
						kcourse.setCourse_id(rs.getString("course_id"));
						kcourse.setSubject_name(rs.getString("subject_name"));
						kcourse.setSeries_name(rs.getString("series_name"));
						kcourse.setSubject_id(rs.getString("subject_id"));
						kcourse.setSeries_id(rs.getString("series_id"));
						kcourse.setCourse_code(rs.getString("course_code"));
						kcourse.setCourse_name(rs.getString("course_name"));
						kcourse.setCourse_kind(rs.getString("course_kind"));
						kcourse.setApply_grade(rs.getString("apply_grade"));
						kcourse.setStudent_aspect(rs.getString("student_aspect"));
						kcourse.setPeriod_count(rs.getString("period_count"));
						kcourse.setCredit_hour(rs.getString("credit_hour"));
						kcourse.setCourse_remark(rs.getString("course_remark"));
						kcourse.setCourse_short_name(rs.getString("course_short_name"));
						kcourse.setDefault_credit_hour(rs.getString("default_credit_hour"));
						kcourse.setDefault_period_count(rs.getString("default_period_count"));
						kcourse.setIs_default(rs.getString("is_default"));
						kcourse.setCourse_kindname(rs.getString("course_kindname"));
						kcourse.setStudent_aspectname(rs.getString("student_aspectname"));
						kcourse.setTeacherId(rs.getString("teacherId"));
						kcourse.setEduId(rs.getString("eduId"));
						return kcourse;
					} catch (Exception e) {
						logger.error("SchoolCourseExt.selectSchoolCourse(Map<String,Object>)", e);
					}
					return null;
				}
				});
			
		} catch (Exception e) {
			logger.error("selectSchoolCourse(Map<String, Object>,Integer, Integer)",e);
		}
		return null;
	}

	public void insertCourseInfos(Map<String, Object> params) {
		try{
			final String str = this.getId();
			params.put("course_id",str);
			//主表course
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.insertCourseInfo.insert");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			//插入指导老师前需要做的几个操作
			this.insertTeachersRole(params);
			//指导老师表
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.insertGidTeacherInfo.insert");
			this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
			//适用年级
			final List<KcourseArrangeDto>applyGradeAndSegs = (List<KcourseArrangeDto>) params.get("applyGradeAndSegs");
			if(null!=applyGradeAndSegs && applyGradeAndSegs.size()>0){
				ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertSegmentInfo.insert");
				getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseArrangeDto kad = applyGradeAndSegs.get(i);
						ps.setString(1, str);
						ps.setString(2, kad.getSegment_order());
						ps.setString(3, kad.getApply_grade());
					}
					public int getBatchSize() {
						return applyGradeAndSegs.size();
					}
				});
			}
		}catch(Exception e){
			logger.error("insertCourseInfos(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	private void insertTeachersRole(Map<String, Object> params) {
		try {
			//1.查询数据库中是否有这些老师的记录
			UserDto newRoleTeacher = (UserDto)params.get("newRoleTeacher");
			List<String>teacherIds = new ArrayList<String>();
			if(NestUtil.isNotEmpty(newRoleTeacher.getTeacherid())){
				teacherIds.add(newRoleTeacher.getTeacherid());
			}else{
				teacherIds.add("-1");
			}
			params.put("teacherIds", teacherIds);
			String roleId = this.queryRoleId(newRoleTeacher);
			List<String>userIds = new ArrayList<String>();
			if(NestUtil.isNotEmpty(newRoleTeacher.getUserid())){
				userIds.add(newRoleTeacher.getUserid());
			}else{
				userIds.add("-1");
			}
			params.put("userid", newRoleTeacher.getUserid());
			params.put("userIds", userIds);
			params.put("roleid", roleId);
			List<String> teachersCourseInfos = this.queryTeachersCourseInfos2(params);
			//2.如果有的话不操作角色表
			if(null!=teachersCourseInfos 
					&& teachersCourseInfos.size()>0 
					&& null!=teachersCourseInfos.get(0).split("@")
					&& teachersCourseInfos.get(0).split("@").length>1
					&& !"-1".equals(teachersCourseInfos.get(0).split("@")[1])){
				return;
			}
			//3.如果没有操作角色表
			String proKey = baseInforManagerExt.queryProKey("o_userrole");
			params.put("userroleid", proKey);
			//o_role
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.insertOuserRole.insert");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			//o_rolecampus
			params.put("campusid", newRoleTeacher.getCampuseId());
			ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertOroleCampus.insert");
			this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
		} catch (Exception e) {
			logger.error("insertTeachersRole(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	private List<String> queryTeachersCourseInfos(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.queryBeforeUpdateTeacherInfoIsNotExist.query");
			return this.findList("SchoolCourseExt.queryBeforeUpdateTeacherInfoIsNotExist.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("teacherIds");
						}
					});
		} catch (Exception e) {
			logger.error(" queryTeachersCourseInfos(Map<String, Object>)",e);
		}
		return null;
	}
	private List<String> queryTeachersCourseInfos2(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.queryBeforeUpdateTeacherInfoIsNotExist2.query");
			return this.findList("SchoolCourseExt.queryBeforeUpdateTeacherInfoIsNotExist2.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("teacherIds");
						}
					});
		} catch (Exception e) {
			logger.error(" queryTeachersCourseInfos2(Map<String, Object>)",e);
		}
		return null;
	}

	/*@DataSource("read")*/
	public List<TbaseinfoDto> getTeacherInfosByName(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.getTeacherInfosByName.query");
			return this.findList("SchoolCourseExt.getTeacherInfosByName.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							TbaseinfoDto dto = new TbaseinfoDto();
							dto.setTeacherid(rs.getString("teacherid"));
							dto.setName(rs.getString("name"));
							dto.setEduId(rs.getString("edu_Id"));
							dto.setSex(rs.getString("sex"));
							dto.setEmployeeid(rs.getString("employeeid"));
							dto.setUserId(rs.getString("userId"));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(" getTeacherInfosByName(Map<String, Object>)",e);
		}
		return null;
	}
	  private String getId() { 
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
	@DataSource("read")
	public List<KsysSubjectDto> getSubjectByCTS(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.getSubjectByCTS.query");
			return this.findList("SchoolCourseExt.getSubjectByCTS.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							KsysSubjectDto dto = new KsysSubjectDto();
							dto.setSubject_id(rs.getString("subject_id"));
							dto.setSubject_name(rs.getString("subject_name"));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(" getSubjectByCTS(Map<String, Object>)",e);
		}
		return null;
	}
	@DataSource("read")
	public Integer checkCourseCount(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.checkCourseCount.count");
			List<String> counts = this.findList("SchoolCourseExt.checkCourseCount.count", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("count");
						}
					});
			if(null!=counts && counts.size()>0){
				return counts.get(0)==null?null:Integer.parseInt(counts.get(0));
			}
		} catch (Exception e) {
			logger.error(" checkCourseCount(Map<String, Object>)",e);
		}
		return null;
	}
	@DataSource("read")
	public List<KcourseArrangeDto> getAspectInfos(Map<String, Object> params) {
		try {
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.getAspectInfos.qurey");
			return this.findList("SchoolCourseExt.getAspectInfos.qurey", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							KcourseArrangeDto kad = new KcourseArrangeDto();
							kad.setApply_grade(rs.getString("apply_grade"));
							kad.setSegment_order(rs.getString("segment_order"));
							return kad;
						}
					});
		} catch (Exception e) {
			logger.error("SchoolCourseExt.getAspectInfos(Map<String, Object>)",e);
		}
		return null;
	}
	/*@DataSource("read")*/
	public List<KcourseDto> getSingleCourseInfoById(Map<String, Object> params) {
		try {
			String flag = (String) params.get("all");
			if(NestUtil.isEmpty(flag)){
				ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.getSingleCourseInfoById.qurey");
				System.out.println(sqlElement.getSql());
				return this.findList("SchoolCourseExt.getSingleCourseInfoById.qurey", params,new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						KcourseDto kcourse = new KcourseDto();
						kcourse.setEmployyedid(rs.getString("employeeid"));
						kcourse.setSegmentCourseId(rs.getString("segment_course_id"));
						kcourse.setSchoolyearname(rs.getString("schoolyearname"));
						kcourse.setSchoolyear(rs.getString("schoolyear"));
						kcourse.setSegmentName(rs.getString("segmentName"));
						kcourse.setSegment_id(rs.getString("segment_id"));
						kcourse.setTeacherName(rs.getString("teacherName"));
						kcourse.setCourse_id(rs.getString("course_id"));
						kcourse.setSubject_name(rs.getString("subject_name"));
						kcourse.setSeries_name(rs.getString("series_name"));
						kcourse.setSubject_id(rs.getString("subject_id"));
						kcourse.setSeries_id(rs.getString("series_id"));
						kcourse.setCourse_code(rs.getString("course_code"));
						kcourse.setCourse_name(rs.getString("course_name"));
						kcourse.setCourse_kind(rs.getString("course_kind"));
						kcourse.setApply_grade(rs.getString("apply_grade"));
						kcourse.setStudent_aspect(rs.getString("student_aspect"));
						kcourse.setPeriod_count(rs.getString("period_count"));
						kcourse.setCredit_hour(rs.getString("credit_hour"));
						kcourse.setCourse_remark(rs.getString("course_remark"));
						kcourse.setCourse_short_name(rs.getString("course_short_name"));
						kcourse.setDefault_credit_hour(rs.getString("default_credit_hour"));
						kcourse.setDefault_period_count(rs.getString("default_period_count"));
						kcourse.setIs_default(rs.getString("is_default"));
						kcourse.setCourse_kindname(rs.getString("course_kindname"));
						kcourse.setStudent_aspectname(rs.getString("student_aspectname"));
						kcourse.setContent_introduction(rs.getString("content_introduction"));
						kcourse.setJoin_requirement(rs.getString("join_requirement"));
						kcourse.setTeach_requirement(rs.getString("teach_requirement"));
						kcourse.setTeacherId(rs.getString("teacherid"));
						return kcourse;
					}
				});
			}else if("all".equals(flag)){
				return this.getCourseInfos(params, "SchoolCourseExt.getAllCourseInfos.query");
			}else if("current".equals(flag)){
				return this.getCourseInfos(params,"SchoolCourseExt.getCurrentAllCourseInfos.query");
			}
			
		} catch (Exception e) {
			logger.error("SchoolCourseExt.getSingleCourseInfoById(Map<String, Object>)",e);
		}
		return null;
	}

	private List<KcourseDto> getCourseInfos(Map<String, Object> params,String sql)
			throws Exception {
		ISqlElement sqlElement=this.processSql(params,sql);
		return this.findList(sql, params,new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				KcourseDto kcourse = new KcourseDto();
				kcourse.setSegmentCourseId(rs.getString("segment_course_id"));
				kcourse.setCourse_id(rs.getString("course_id"));
				kcourse.setCourse_code(rs.getString("course_code"));
				kcourse.setCourse_name(rs.getString("course_name"));
				kcourse.setCourse_kind(rs.getString("course_kind"));
				return kcourse;
			}
		});
	}

	public void updateCourseInfo(Map<String, Object> params) {
		try{
			final String str = (String) params.get("course_id");
			//主表course
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.updateCourseInfo.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			//修改此表时   得判断修改前的老师是谁
			this.updateTeacherInfos(params);
			//指导老师表
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.updateTeacherInfo.update");
			this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
			//适用年级  先删除
			ISqlElement sqlElement3=this.processSql(params,"SchoolCourseExt.deleteApplyGradeInfo.delete");
			this.getJdbcTemplate().update(sqlElement3.getSql(),sqlElement3.getParams());
			//适用年级  再插入
			@SuppressWarnings("unchecked")
			final List<KcourseArrangeDto>applyGradeAndSegs = (List<KcourseArrangeDto>) params.get("applyGradeAndSegs");
			if(null!=applyGradeAndSegs && applyGradeAndSegs.size()>0){
				ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertSegmentInfo.insert");
				getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseArrangeDto kad = applyGradeAndSegs.get(i);
						ps.setString(1, str);
						ps.setString(2, kad.getSegment_order());
						ps.setString(3, kad.getApply_grade());
					}
					public int getBatchSize() {
						return applyGradeAndSegs.size();
					}
				});
			}
		}catch(Exception e){
			logger.error("insertCourseInfos(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	//TO LOOK
	private void updateTeacherInfos(Map<String, Object> params) {
		try {
			//修改老师  老师信息改变  类似于重新插入数据
			String isNotSame = (String) params.get("isNotSame");
			if(NestUtil.isNotEmpty(isNotSame) && isNotSame.equals("1")){
				return;
			}
			//1、新老师数据 类似插入
			UserDto newRoleTeacher = (UserDto) params.get("newRoleTeacher");
			if(null!=newRoleTeacher){
				this.insertTeachersRole(params);
			}
			//2、旧老师数据类似删除
			UserDto oldRoleTeacher = (UserDto) params.get("oldRoleTeacher");
			if(null!=oldRoleTeacher){
				this.deleteOldTeacher(params);
			}
		} catch (Exception e) {
			logger.error("updateTeacherInfos(Map<String, Object>)",e);
			throw new ManagerException(e);
		}
	}

	private void deleteOldTeacher(Map<String, Object> params) {
		try {
			//1.查询数据库中是否有这些老师的记录
			UserDto oldRoleTeacher = (UserDto)params.get("oldRoleTeacher");
			List<String>teacherIds = new ArrayList<String>();
			if(NestUtil.isNotEmpty(oldRoleTeacher.getTeacherid())){
				teacherIds.add(oldRoleTeacher.getTeacherid());
			}else{
				teacherIds.add("-1");
			}
			params.put("teacherIds", teacherIds);
			List<String> teachersCourseInfos = this.queryTeachersCourseInfos(params);
			//2.如果有的话不操作角色表
			if(null!=teachersCourseInfos && teachersCourseInfos.size()>1){
				return;
			}
			params.put("oldUserId", oldRoleTeacher.getUserid());
			params.put("oldRoleId", this.queryRoleId((UserDto)params.get("newRoleTeacher")));
			oldRoleTeacher.setCampuseId(((UserDto)params.get("newRoleTeacher")).getCampuseId());
			this.deleteUserRoleInfos(params, oldRoleTeacher);
		} catch (Exception e) {
			logger.error("deleteOldTeacher(Map<String, Object>)",e);
			throw new ManagerException(e);
		}
	}

	private void deleteUserRoleInfos(Map<String, Object> params,UserDto newRoleTeacher) throws Exception {
		List<String> oldUserRoleIds = this.findList("SchoolCourseExt.queryBeforeUpdateUserRoleidInfoIsNotExist.query", params,new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return rs.getString("oldUserRoleId");
			}
		});
		if(null!=oldUserRoleIds && oldUserRoleIds.size()>0){
			String oldUserRoleId = oldUserRoleIds.get(0);
			params.put("oldUserRoleId", oldUserRoleId);
			params.put("campusid", newRoleTeacher.getCampuseId());
			//删除 角色校区对应表	
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.deleteOroleCampusInfo.delete");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			//删除用户角色表
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.deleteOuserRoleInfo.delete");
			this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
		}
	}


	public void deleteCourseInfo(Map<String, Object> params) {
		try {
			//适用年级  删除
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.deleteApplyGradeInfo.delete");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			//删除学生信息
			ISqlElement sqlElement3=this.processSql(params,"SchoolCourseExt.deleteStudentMatriculateInfo.delete");
			this.getJdbcTemplate().update(sqlElement3.getSql(),sqlElement3.getParams());
			//注意：删除指导老师前  -->需要进行的操作
			this.deleteUserRole(params);
			//删除知道教师相关信息
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.deleteTeacherInfo.delete");
			this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
			//删除知道教师相关信息
			ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.deleteCourseInfo.delete");
			this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
		} catch(Exception e){
			logger.error("deleteCourseInfo(Map<String, Object>)",e);
			//当涉及到对数据进行删除操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	//eeeee
	private void deleteUserRole(Map<String, Object> params) {
		try {
			//1.查询数据库中是否有这些老师的记录
			UserDto oldRoleTeacher = (UserDto)params.get("newRoleTeacher");
			List<String>teacherIds = new ArrayList<String>();
			if(NestUtil.isNotEmpty(oldRoleTeacher.getTeacherid())){
				teacherIds.add(oldRoleTeacher.getTeacherid());
			}else{
				teacherIds.add("-1");
			}
			params.put("teacherIds", teacherIds);
			List<String> teachersCourseInfos = this.queryTeachersCourseInfos(params);
			//2.如果有的话不操作角色表
			if(null!=teachersCourseInfos && teachersCourseInfos.size()>1){
				return;
			}
			params.put("oldUserId", oldRoleTeacher.getUserid());
			params.put("oldRoleId", this.queryRoleId(oldRoleTeacher));
			this.deleteUserRoleInfos(params, oldRoleTeacher);
		} catch (Exception e) {
			logger.error("deleteUserRole(Map<String, Object>)",e);
			//当涉及到对数据进行删除操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	public void saveCourseList(final List<KcourseDto> courseInfos,UserDto userDto) {
		try{
			/*courseInfos.remove(0);*/
			Map<String,Object>params = new HashMap<String, Object>();
			//主表course
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.insertCourseInfo.insert");
			getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = courseInfos.get(i);
					ps.setString(1, kad.getCourse_id());
					ps.setString(2, kad.getSubject_id());
					ps.setString(3, kad.getSeries_id());
					ps.setString(4, kad.getCmis30id());
					ps.setString(5, kad.getCourse_code());
					ps.setString(6, kad.getCourse_name());
					ps.setString(7, kad.getCourse_short_name());
					ps.setString(8, kad.getCourse_kind());
					ps.setString(9, kad.getStudent_aspect());
					ps.setString(10, kad.getPeriod_count());
					ps.setString(11, kad.getCredit_hour());
					ps.setString(12, kad.getContent_introduction());
					ps.setString(13, kad.getJoin_requirement());
					ps.setString(14, kad.getTeach_requirement());
					ps.setString(15, kad.getCourse_remark());
				}
				public int getBatchSize() {
					return courseInfos.size();
				}
			});
			this.insertImportRoles(courseInfos,userDto);
			//指导老师表
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.insertGidTeacherInfo.insert");
			getJdbcTemplate().batchUpdate(sqlElement1.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = courseInfos.get(i);
					ps.setString(1, kad.getTeacherId());
					ps.setString(2, kad.getCourse_id());
					ps.setString(3, kad.getSegment_id());
					ps.setString(4, Constant.KG_AUDIT_STATUS);
				}
				public int getBatchSize() {
					return courseInfos.size();
				}
			});
			//适用年级
			ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertSegmentInfo.insert");
			getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = courseInfos.get(i);
					ps.setString(1, kad.getCourse_id());
					ps.setString(2, "");
					ps.setString(3, kad.getApply_grade());
				}
				public int getBatchSize() {
					return courseInfos.size();
				}
			});
		}catch(Exception e){
			logger.error("insertCourseInfos(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	private void insertImportRoles(List<KcourseDto> courseInfos,UserDto userDto) {
		//1.获取到所有的teacherId
		List<String>teacherIds = new ArrayList<String>();
		List<String>userIds = new ArrayList<String>();
		if(null!=courseInfos && courseInfos.size()>0){
			for(KcourseDto kDto : courseInfos){
				teacherIds.add(kDto.getTeacherId());
				userIds.add(kDto.getUserId());
			}
		}
		if(null==teacherIds || teacherIds.size()==0)throw new ManagerException();
		UserDto roleTeacher = new UserDto();
		roleTeacher.setUnitid(userDto.getUnitid());
		roleTeacher.setUsertype(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE);
		String roleId = this.queryRoleId(roleTeacher);
		//2.使用teacherId查询数据库
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("teacherIds", teacherIds);
		params.put("userIds", userIds);
		params.put("roleid", roleId);
		List<String> teachersCourseInfos = this.queryTeachersCourseInfos2(params);
		//存放去重后的  需要操作其角色的teacherId
		if(null!=teachersCourseInfos && teachersCourseInfos.size()>0){
			//3.遍历比对哪些teacherId有、哪些没有
			noRoleTeacherIds = new HashSet<String>();
			for(String tcInfo : teachersCourseInfos){
				String[] tcInfos = tcInfo.split("@");
				if(null==tcInfos && tcInfos.length==0)throw new ManagerException();
				String tId = tcInfos[0];
				String rId = tcInfos[1];
				if("-1".equals(rId)){
					noRoleTeacherIds.add(tId);
				}
			}
		}else{
			noRoleTeacherIds = new HashSet<String>(teacherIds);
		}
		if(null==noRoleTeacherIds || noRoleTeacherIds.size()==0)return;//4.都有角色啦  不用操作其角色
		//5.没有角色操作其角色表
		this.commonBachInsertRoles(courseInfos, userDto.getCampuseId(), roleId);
	}

	private void commonBachInsertRoles(List<KcourseDto> courseInfos,
			String campusId, String roleId) {
		List<KcourseDto>noRoleTeacherInfos = new ArrayList<KcourseDto>();
		for(KcourseDto kDto : courseInfos){
			if(noRoleTeacherIds.contains(kDto.getTeacherId()) && !noRoleTeacherInfos.contains(kDto)){//如果相等得到该对象
				kDto.setCampusId(campusId);
				kDto.setRoleId(roleId);
				kDto.setUserRoleId(baseInforManagerExt.queryProKey("o_userrole"));
				noRoleTeacherInfos.add(kDto);
			}
		}
		//6.批量查数据进角色表
		if(null==noRoleTeacherInfos && noRoleTeacherInfos.size()==0)throw new ManagerException();
		this.bachInsertRole(noRoleTeacherInfos);
	}
	private void bachInsertRole(final List<KcourseDto> noRoleTeacherInfos) {
		try {
			//插入用户角色信息
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.insertOuserRole.insert");
			getJdbcTemplate().batchUpdate(sqlElement1.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = noRoleTeacherInfos.get(i);
					ps.setString(1, kad.getUserRoleId());
					ps.setString(2, kad.getUserId());
					ps.setString(3, kad.getRoleId());
				}
				public int getBatchSize() {
					return noRoleTeacherInfos.size();
				}
			});
			//插入用户角色角色校区信息
			ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertOroleCampus.insert");
			getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = noRoleTeacherInfos.get(i);
					ps.setString(1, kad.getUserRoleId());
					ps.setString(2, kad.getCampusId());
				}
				public int getBatchSize() {
					return noRoleTeacherInfos.size();
				}
			});
		} catch(Exception e) {
			logger.error("bachInsertRole(List<KcourseDto>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
		
	}
	private Set<String>noRoleTeacherIds;
	/*@DataSource("read")*/
	public boolean isWriteScore(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo = null;
			String preSql = "";
			if("1".equals(params.get("deleteStudentFlag"))){
				sqlDemo=this.processSql(params, "SchoolCourseExt.isAnyWriteScore.query");
				preSql = "SchoolCourseExt.isAnyWriteScore.query"; 
			}else{
				sqlDemo=this.processSql(params, "SchoolCourseExt.isWriteScore.query");
				preSql = "SchoolCourseExt.isWriteScore.query";
			}
			List<String>scores = this.findList(preSql, params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("score");
				}
			});
			if(null!=scores && scores.size()>0){
				String score = scores.get(0);
				if(!NestUtil.isEmpty(score)){
					return true;
				}
			}
			
		} catch (Exception e) {
			logger.error("SchoolCourseExt.isWriteScore(Map<String, Object>)", e);
			throw new ManagerException();
		}
		return false;
	}
	@DataSource("read")
	public IPage getHiredStudentInfosById(Map<String, Object> queryMap,Integer pageNo, Integer pageSize) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "SchoolCourseExt.getHiredStudentInfosById.query");
			return this.findPage("SchoolCourseExt.getHiredStudentInfosById.query","SchoolCourseExt.getHiredStudentInfosById.queryPage",queryMap,pageNo,pageSize,new IRowHandler<KstudentMatriculateDto>(){
				public KstudentMatriculateDto handleRow(ResultSet rs) {
					try {
						KstudentMatriculateDto matriculateDto = new KstudentMatriculateDto();
						matriculateDto.setMatriculate_id(rs.getString("matriculate_id"));
						matriculateDto.setStudentid(rs.getString("studentid"));
						matriculateDto.setStudentName(rs.getString("name"));
						matriculateDto.setSex(rs.getString("sex"));
						matriculateDto.setSubjectName(rs.getString("subject_name"));
						matriculateDto.setEduId(rs.getString("edu_id"));
						matriculateDto.setClassName(rs.getString("classname"));
						return matriculateDto;
					} catch (Exception e) {
						logger.error("SchoolCourseExt.getHiredStudentInfosById(Map<String,Object>)", e);
					}
					return null;
				}
				});
		} catch (Exception e) {
			logger.error("getHiredStudentInfosById(Map<String, Object>,Integer, Integer)",e);
		}
		return null;
	}

	public void deleteHiredStudentInfo(Map<String, Object> params) {
		try {
			//删除录取学生信息
			ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.deleteHiredStudentInfo.delete");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
		} catch(Exception e){
			logger.error("deleteHiredStudentInfo(Map<String, Object>)",e);
			//当涉及到对数据进行删除操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	/*@DataSource("read")*/
	public List<KstudentMatriculateDto> getSchoolStudentInfos(Map<String, Object> params) {
		try {
			String flag = (String) params.get("grade");
			Set<String>classIds = (Set<String>) params.get("classIds");
			if(null!=classIds && classIds.size()>0 ){
				ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getSchoolStudentInfos.query");
			  
				return this.findList("SchoolCourseExt.getSchoolStudentInfos.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)	throws SQLException {
						KstudentMatriculateDto kmDto = new KstudentMatriculateDto();
						kmDto.setEduId(rs.getString("edu_id"));
						kmDto.setStudentName(rs.getString("name"));
						kmDto.setStudentid(rs.getString("studentid"));
						kmDto.setClassid(rs.getString("classid"));
						kmDto.setCmis30Id(rs.getString("cmis30id"));
						kmDto.setDiscode(rs.getString("discode"));
						kmDto.setClassName(rs.getString("classname"));
						return kmDto;
					}
				});
			}else if(!NestUtil.isEmpty(flag)){
				ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getCurrentAllGradeInfos.query");
				return this.findList("SchoolCourseExt.getCurrentAllGradeInfos.query", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)	throws SQLException {
						KstudentMatriculateDto kmDto = new KstudentMatriculateDto();
						kmDto.setClassid(rs.getString("classid"));
						kmDto.setClassName(rs.getString("classname"));
						return kmDto;
					}
				});
			}

		} catch (Exception e) {
			logger.error("SchoolCourseExt.getSchoolStudentInfos(Map<String, Object>)", e);
		}
		return null;
	}
	/*@DataSource("read")*/
	public List<KstudentMatriculateDto> getSchoolHiredStudentInfos(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getSchoolHiredStudentInfos.query");
			return this.findList("SchoolCourseExt.getSchoolHiredStudentInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)	throws SQLException {
					KstudentMatriculateDto kmDto = new KstudentMatriculateDto();
					kmDto.setStudentid(rs.getString("studentid"));
					kmDto.setClassid(rs.getString("classid"));
					kmDto.setSegment_course_id(rs.getString("segment_course_id"));
					return kmDto;
				}
			});
		} catch (Exception e) {
			logger.error("SchoolCourseExt.getSchoolHiredStudentInfos(Map<String, Object>)", e);
		}
		return null;
	}

	public void insertHiredStudentInfo(final List<KcourseDto> excelCourseInfos) {
		try{
			excelCourseInfos.remove(0);
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertHiredStudentInfo.insert");
			getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = excelCourseInfos.get(i);
					ps.setString(1, kad.getStudentId());
					ps.setString(2, kad.getSegmentCourseId());
					ps.setString(3, kad.getClassId());
					ps.setString(4, kad.getCmis30id());
					ps.setString(5, kad.getDiscode());
				}
				public int getBatchSize() {
					return excelCourseInfos.size();
				}
			});
		}catch(Exception e){
			logger.error("SchoolCourseExt.insertHiredStudentInfo(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	public void saveOrUpdateCourseList(List<KcourseDto> insertDtoList,final List<KcourseDto> updateDtoList,UserDto userDto) {
		try {
			if(null!=insertDtoList && insertDtoList.size()>0){
				//插入数据
				this.saveCourseList(insertDtoList,userDto);
			}
			if(null!=updateDtoList && updateDtoList.size()>0){
				//更新数据
				//1、更新主表
				//主表course
				Map<String,Object>params = new HashMap<String, Object>();
				ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.updateCourseInfo.update");
				getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseDto kad = updateDtoList.get(i);
						ps.setString(1, kad.getSubject_id());
						ps.setString(2, kad.getSeries_id());
						ps.setString(3, kad.getCmis30id());
						ps.setString(4, kad.getCourse_code());
						ps.setString(5, kad.getCourse_name());
						ps.setString(6, kad.getCourse_short_name());
						ps.setString(7, kad.getCourse_kind());
						ps.setString(8, kad.getStudent_aspect());
						ps.setString(9, kad.getPeriod_count());
						ps.setString(10, kad.getCredit_hour());
						ps.setString(11, kad.getContent_introduction());
						ps.setString(12, kad.getJoin_requirement());
						ps.setString(13, kad.getTeach_requirement());
						ps.setString(14, kad.getCourse_remark());
						ps.setString(15, kad.getCourse_id());
					}
					public int getBatchSize() {
						return updateDtoList.size();
					}
				});
				//指导老师表
				this.updateImportRole(updateDtoList,userDto);
				ISqlElement sqlElement1=this.processSql(params,"SchoolCourseExt.updateTeacherInfo.update");
				getJdbcTemplate().batchUpdate(sqlElement1.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseDto kad = updateDtoList.get(i);
						ps.setString(1, kad.getTeacherId());
						ps.setString(2, kad.getCourse_id());
						ps.setString(3, kad.getSegment_id());
						ps.setString(4, Constant.KG_AUDIT_STATUS);
						ps.setString(5, kad.getSegmentCourseId());
					}
					public int getBatchSize() {
						return updateDtoList.size();
					}
				});
				//批量删除适用年级表
				ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.deleteApplyGradeInfo.delete");
				getJdbcTemplate().batchUpdate(sqlElement2.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseDto kad = updateDtoList.get(i);
						ps.setString(1, kad.getCourse_id());
					}
					public int getBatchSize() {
						return updateDtoList.size();
					}
				});
				//适用年级
				ISqlElement sqlElement3=this.processSql(params,"SchoolCourseExt.insertSegmentInfo.insert");
				getJdbcTemplate().batchUpdate(sqlElement3.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseDto kad = updateDtoList.get(i);
						ps.setString(1, kad.getCourse_id());
						ps.setString(2, "");
						ps.setString(3, kad.getApply_grade());
					}
					public int getBatchSize() {
						return updateDtoList.size();
					}
				});
			}
		} catch (Exception e) {
			logger.error("SchoolCourseExt.insertHiredStudentInfo(Map<String, Object>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	private void updateImportRole(List<KcourseDto> updateDtoList,UserDto userDto) {
		List<String>updateSegMentIds = new ArrayList<String>();
		for(KcourseDto upDto : updateDtoList){
			updateSegMentIds.add(upDto.getSegmentCourseId());
		}
		if(null==updateSegMentIds || updateSegMentIds.size()==0)throw new ManagerException();
		//1.获取更新前的老师授课信息  并查询其还有其他的授课没有
		UserDto roleTeacher = new UserDto();
		roleTeacher.setUnitid(userDto.getUnitid());
		roleTeacher.setUsertype(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE);
		String roleId = this.queryRoleId(roleTeacher);
		
		Map<String,Object>params = new HashMap<String, Object>();
		if(null==updateSegMentIds || updateSegMentIds.size()==0){
			updateSegMentIds = new ArrayList<String>();
			updateSegMentIds.add("-1");
		}
		params.put("segMentIds", updateSegMentIds);
		params.put("campusid", userDto.getCampuseId());
		params.put("unitid", userDto.getUnitid());
		params.put("roleId", roleId);
		List<KcourseDto>importBeforedTeacherInfos =this.queryImportBeforeTeacher(params);
		if(null==importBeforedTeacherInfos && importBeforedTeacherInfos.size()==0)throw new ManagerException();
		final List<KcourseDto>deleteTeacherInfos = new ArrayList<KcourseDto>();
		for(KcourseDto importBeforeTeacher : importBeforedTeacherInfos){//更新前数据为主
			if(!updateDtoList.contains(importBeforeTeacher) && 1==importBeforeTeacher.getRownum()){
				//需要被删除的部分
				importBeforeTeacher.setCampusId(userDto.getCampuseId());
				deleteTeacherInfos.add(importBeforeTeacher);
			}
		}
		noRoleTeacherIds = new HashSet<String>();
		Set<String>userIds = new HashSet<String>();
		for(KcourseDto importBeforeTeacher : importBeforedTeacherInfos){//更新前数据为主
			if(updateDtoList.contains(importBeforeTeacher) && NestUtil.isEmpty(roleId)){
				//需要插入的部分
				noRoleTeacherIds.add(importBeforeTeacher.getTeacherId());
			}
		}
		for(KcourseDto upDto : updateDtoList){
			if(!importBeforedTeacherInfos.contains(upDto)){
				//需要插入的部分
				noRoleTeacherIds.add(upDto.getTeacherId());
				userIds.add(upDto.getUserId());
			}
		}
		List<String>tIds = new ArrayList<String>();
		List<String>uIds = new ArrayList<String>();
		for(String noo:noRoleTeacherIds){
			tIds.add(noo);
		}
		for(String uId:userIds){
			uIds.add(uId);
		}
		if(tIds.size()==0){
			tIds.add("-1");
			uIds.add("-1");
		}
		//2.使用teacherId查询数据库
		Map<String,Object>params1 = new HashMap<String,Object>();
		params1.put("teacherIds", tIds);
		params1.put("userIds", uIds);
		params1.put("roleid", roleId);
		Set<String>tempNoRoleTeacherIds = new HashSet<String>();
		List<String> teachersCourseInfos = this.queryTeachersCourseInfos2(params1);
		if(null!=teachersCourseInfos && teachersCourseInfos.size()>0){
			//3.遍历比对哪些teacherId有、哪些没有
			noRoleTeacherIds = new HashSet<String>();
			for(String tcInfo : teachersCourseInfos){
				String[] tcInfos = tcInfo.split("@");
				if(null==tcInfos && tcInfos.length==0)throw new ManagerException();
				String tId = tcInfos[0];
				String rId = tcInfos[1];
				if("-1".equals(rId)){
					tempNoRoleTeacherIds.add(tId);
				}
			}
			noRoleTeacherIds.addAll(tempNoRoleTeacherIds);
		}
		//删除对应角色信息
		if(null!=deleteTeacherInfos && deleteTeacherInfos.size()>0){
			this.importBachDelete(deleteTeacherInfos);
		}
		//插入对应角色信息
		if(null!=noRoleTeacherIds && noRoleTeacherIds.size()>0){
			this.commonBachInsertRoles(updateDtoList, userDto.getCampuseId(), roleId);
		}
	}

	private void importBachDelete(final List<KcourseDto> deleteTeacherInfos) {
		try {
			//批量删除 角色校区对应表
			Map<String,Object>params1 = new HashMap<String, Object>();
			ISqlElement sqlElement=this.processSql(params1,"SchoolCourseExt.deleteOroleCampusInfo.delete");
			getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = deleteTeacherInfos.get(i);
					ps.setString(1, kad.getUserRoleId());
					ps.setString(2, kad.getCampusId());
				}
				public int getBatchSize() {
					return deleteTeacherInfos.size();
				}
			});
			//批量删除用户角色表
			ISqlElement sqlElement1=this.processSql(params1,"SchoolCourseExt.deleteOuserRoleInfo.delete");
			getJdbcTemplate().batchUpdate(sqlElement1.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					KcourseDto kad = deleteTeacherInfos.get(i);
					ps.setString(1, kad.getUserRoleId());
				}
				public int getBatchSize() {
					return deleteTeacherInfos.size();
				}
			});
		} catch (Exception e) {
			logger.error("SchoolCourseExt.changeRole(UserDto，String)", e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	private List<KcourseDto> queryImportBeforeTeacher(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.queryImportBeforeUpdateTeacherInfoIsNotExist.query");
			return this.findList("SchoolCourseExt.queryImportBeforeUpdateTeacherInfoIsNotExist.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KcourseDto kDto = new KcourseDto();
					kDto.setTeacherId(rs.getString("declare_teacher"));
					kDto.setUserId(rs.getString("userid"));
					kDto.setRownum(NestUtil.isNotEmpty(rs.getString("count"))?Integer.parseInt(rs.getString("count")):0);
					kDto.setRoleId(rs.getString("roleid"));
					kDto.setUserRoleId(rs.getString("userroleid"));
					return kDto;
				}
			});
			}catch(Exception e){
				logger.error("SchoolCourseExt.queryImportBeforeTeacher(Map<String, Object>)", e);
			}
		return null;
	}

	public boolean isAnyWriteScore(Map<String, Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.isAnyWriteScore.query");
			List<String>scores = this.findList("SchoolCourseExt.isAnyWriteScore.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("score");
				}
			});
			if(null!=scores && scores.size()>0){
				String score = scores.get(0);
				if(!NestUtil.isEmpty(score)){
					return true;
				}
			}
		} catch (Exception e) {
			logger.error("SchoolCourseExt.isAnyWriteScore(Map<String, Object>)", e);
		}
		return false;
		
	}

	public String queryRoleId(UserDto newRoleTeacher){
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("unitid", newRoleTeacher.getUnitid());
			params.put("roletype", newRoleTeacher.getUsertype());
			List<String>roleIds = this.findList("SchoolCourseExt.queryTeacherRoleId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("roleId");
				}
			});
			if(null!=roleIds && roleIds.size()>0){
				return roleIds.get(0);
			}
		} catch (Exception e) {
			logger.error("SchoolCourseExt.queryRoleDtos(UserDto)", e);
		}
		return null;
	}
	
	@Override
	public void insertRole(UserDto newRoleTeacher,List<UserDto>roleDtos) {
		Map<String,Object>params = new HashMap<String, Object>();
		try {
			boolean isNotExist = false;
			if(null!=roleDtos && roleDtos.size()>0){
				for(UserDto roleDto : roleDtos ){
					if(newRoleTeacher.getUserid().equals(roleDto.getUserid())){
						isNotExist = true;//用户角色存在 跳出循环
						newRoleTeacher.setRoleId(roleDto.getRoleId());
						break;
					}
				}
				if(!isNotExist){//如果不存在向用户角色表添加信息
					params.put("userid", newRoleTeacher.getUserid());
					params.put("roleid", roleDtos.get(0).getRoleId());
					String proKey = baseInforManagerExt.queryProKey("o_userrole");
					params.put("userroleid", proKey);
					//o_role
					ISqlElement sqlElement=this.processSql(params,"SchoolCourseExt.insertOuserRole.insert");
					this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
					//o_rolecampus
					params.put("campusid", newRoleTeacher.getCampuseId());
					ISqlElement sqlElement2=this.processSql(params,"SchoolCourseExt.insertOroleCampus.insert");
					this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
				}
			}
		} catch (Exception e) {
			logger.error("SchoolCourseExt.changeRole(UserDto，String)", e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	private IBaseInforManagerExt baseInforManagerExt;
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}

	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}

	@Override
	public String queryUserIdFromDB(String tID,String unitId) {
		Map<String,Object>params = new HashMap<String, Object>();
		try {
			params.put("personId", tID);
			params.put("unitId", unitId);
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.queryUserIdFromDB.query");
			List<String>userIds = this.findList("SchoolCourseExt.queryUserIdFromDB.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("userIds");
				}
			});
			if(null!=userIds && userIds.size()>0){
				return userIds.get(0);
			}
		} catch (Exception e) {
			logger.error("SchoolCourseExt.queryUserIdFromDB(String)", e);
		}
		return "";
	}

}
