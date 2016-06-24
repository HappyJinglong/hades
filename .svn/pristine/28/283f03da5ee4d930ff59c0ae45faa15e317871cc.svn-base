package com.flyrish.hades.service.ext.impl;

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
import java.util.UUID;

import org.jfree.ui.KeyedComboBoxModel;
import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.hibernate.IRowHandler;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import sun.security.krb5.internal.KdcErrException;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.DownloadDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.KclassModelDto;
import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KcourseSeriesDto;
import com.flyrish.hades.dto.SysdictDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IInnerCourseExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;

public class InnerCourseExtImpl extends JdbcRootManager implements IInnerCourseExt{
	
	public IPage<KcourseDto> selectInnertCourse(Map<String, Object> queryMap, Integer pageNo,
			Integer pageSize) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectCourse.count");
			return this.findPage("Innercourse.selectCourse.query","Innercourse.selectCourse.count",queryMap,pageNo,pageSize,new IRowHandler<KcourseDto>(){
				public KcourseDto handleRow(ResultSet rs) {
					try {
						KcourseDto kcourse = new KcourseDto();
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
						Map<String, Object> queryMap1 = new HashMap<String, Object>();
						queryMap1.put("courseid", rs.getString("course_id"));
						List<KcourseArrangeDto> lst = selectArrange(queryMap1);
						kcourse.setKcourseArrangeList(lst);	
						return kcourse;
					} catch (Exception e) {
					}
					return null;
				}
				});
			
		} catch (Exception e) {
			logger.error("selectSelfPartInfo(Map)",e);
		}
		return null;
	}
	
	
	public IPage<KcourseDto> selectInnertCourse1(Map<String, Object> queryMap, Integer pageNo,
			Integer pageSize) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectCourse1.count");
			return this.findPage("Innercourse.selectCourse1.query","Innercourse.selectCourse1.count",queryMap,pageNo,pageSize,new IRowHandler<KcourseDto>(){
				public KcourseDto handleRow(ResultSet rs) {
					try {
						KcourseDto kcourse = new KcourseDto();
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
						Map<String, Object> queryMap1 = new HashMap<String, Object>();
						queryMap1.put("courseid", rs.getString("course_id"));
						List<KcourseArrangeDto> lst = selectArrange(queryMap1);
						kcourse.setKcourseArrangeList(lst);	
						return kcourse;
					} catch (Exception e) {
					}
					return null;
				}
				});
			
		} catch (Exception e) {
			logger.error("selectSelfPartInfo(Map)",e);
		}
		return null;
	}
	
	/**
	 * 获取学段课程安排
	 * @param Map查询参数
	 * @return
	 */
	public List<KcourseArrangeDto> selectArrange(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectArrange.query");
			return this.findList("Innercourse.selectArrange.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseArrangeDto courseArrangeDto = new KcourseArrangeDto();
					courseArrangeDto.setApply_grade(rs.getString("apply_grade"));
					courseArrangeDto.setArrange_date(rs.getString("arrange_date"));
					courseArrangeDto.setArrange_id(rs.getString("arrange_id"));
					courseArrangeDto.setCourse_id(rs.getString("course_id"));
					courseArrangeDto.setSegment_order(rs.getString("segment_order"));
					return courseArrangeDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectArrange(Map)",e);
		}
		return null;
	}
	public void insertKcourse(KcourseDto courseDto, String[] arr) {
		if(courseDto!=null){
			try{
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("subject_id",courseDto.getSubject_id());
				params.put("series_id",courseDto.getSeries_id());
				params.put("cmis30id",courseDto.getCmis30id());
				params.put("course_code",courseDto.getCourse_code());
				params.put("course_name",courseDto.getCourse_name());
				params.put("course_kind",courseDto.getCourse_kind());
				params.put("student_aspect",courseDto.getStudent_aspect());
				params.put("period_count",courseDto.getPeriod_count());
				params.put("credit_hour",courseDto.getCredit_hour());
				params.put("course_remark",courseDto.getCourse_remark());
				params.put("is_default",courseDto.getIs_default());
				params.put("course_short_name",courseDto.getCourse_short_name());
				final String uuid = getId();
				params.put("course_id",uuid);
				ISqlElement sqlElement=this.processSql(params,"Innercourse.insertCourse.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				if(arr!=null){
					final List<String> gradeList = new ArrayList<String>();
					final List<String> orderList = new ArrayList<String>();
					for(int i=0;i<arr.length;i++){
						String[] arrange = new String[2];
						arrange = arr[i].split("\\^");
						gradeList.add(arrange[0]);
						orderList.add(arrange[1]);
					}
					Map<String,Object> map=new HashMap<String,Object>();
					ISqlElement sqlElement1=this.processSql(map,"Innercourse.insertCourseArrange.add");
					this.getJdbcTemplate().batchUpdate(sqlElement1.getSql(),
			                new BatchPreparedStatementSetter() {
						    public int getBatchSize() {
		                        return gradeList.size();
		                    }
							public void setValues(PreparedStatement preparedStatement,
			                            int i) throws SQLException {
			                    	preparedStatement.setObject(1, uuid);
			                    	preparedStatement.setObject(2, orderList.get(i));
			                        preparedStatement.setObject(3, gradeList.get(i));
			                      
			                    }
			                   
			                });
				}
			}catch(Exception e){
				logger.error("insertKcourse(KcourseDto,String[])",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			
		}
	}
	
	public List<KcourseSeriesDto> selectSeries(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectSeries.query");
			return this.findList("Innercourse.selectSeries.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseSeriesDto courseSeriesDto = new KcourseSeriesDto();
					courseSeriesDto.setSeries_id(rs.getString("series_id"));
					courseSeriesDto.setSeries_name(rs.getString("series_name"));
					courseSeriesDto.setSubject_id(rs.getString("subject_id"));
					return courseSeriesDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectSeries(Map)",e);
		}
		return null;
	}
	
	public List<SysdictDto> selectAspect(Map<String, Object> queryMap) {
		try {
			return this.findList("Innercourse.selectAspect.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					SysdictDto sysdictDto = new SysdictDto();
					sysdictDto.setDict_id(rs.getString("dict_id"));
					sysdictDto.setDict_name(rs.getString("name"));
					sysdictDto.setDict_type(rs.getString("dict_type"));
					return sysdictDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectSeries(Map)",e);
		}
		return null;
	}
	
	public List<KcourseDto> selectInnertCourseList(Map<String, Object> queryMap) {
		try {
			return this.findList("Innercourse.selectCourse.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseDto kcourse = new KcourseDto();
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
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("courseid", rs.getString("course_id"));
					List<KcourseArrangeDto> lst = selectArrange(queryMap1);
					kcourse.setKcourseArrangeList(lst);	
					return kcourse;
				}
			});
		} catch (Exception e) {
			logger.error("selectInnertCourseList(Map)",e);
		}
		return null;
	}
	
	public List<KcourseDto> selectInnertCourseCheckList(Map<String, Object> queryMap) {
		try {
			String innerFlag = (String) queryMap.get("innerFlag");
			queryMap.remove("innerFlag");
			String preSql = "Innercourse.selectCourseCheck.query";
			if("1".equals(innerFlag)){
				preSql="Innercourse.selectCourseCheck1.query";
			}
			return this.findList(preSql, queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseDto kcourse = new KcourseDto();
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
					/*Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("courseid", rs.getString("course_id"));
					List<KcourseArrangeDto> lst = selectArrange(queryMap1);
					kcourse.setKcourseArrangeList(lst);	*/
					return kcourse;
				}
			});
		} catch (Exception e) {
			logger.error("selectInnertCourseList(Map)",e);
		}
		return null;
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
	  
	  public Integer checkDeleteCourse(Map<String, Object> params) {
			try {
			/*	ISqlElement sqlElement=this.processSql(params,"Innercourse.deleteArrange.count");*/
				List<String> counts = this.findList("Innercourse.deleteArrange.count", params,
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
				logger.error(" checkDeleteCourse(Map<String, Object>)",e);
			}
			return null;
		}   
	  
	  public Integer CheckDeleteClass(String courseid, String segmentid, String cmis30id, String year, String gradeid) {
			try {
			/*	ISqlElement sqlElement=this.processSql(params,"Innercourse.selectCheckdeleteClass.count");*/
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("course_id",courseid);
				params.put("segment_id",segmentid);
				params.put("cmis30id",cmis30id);
				params.put("year",year);
				params.put("gradeid",gradeid);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("sementmodelid", this.selectSegmentModelId(params));
				map.put("cmis30id",cmis30id);
				map.put("year",year);
				map.put("gradeid",gradeid);
				List<String> counts = this.findList("Innercourse.selectCheckdeleteClass.count", map,
						new RowMapper() {
							public Object mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return rs.getString("count");
							}
						});
				if(null!=counts&&counts.size()>0){
					Integer count=counts.get(0)==null?0:Integer.parseInt(counts.get(0));
					if(count==0){
						//删除成绩表里为空的成绩
						deleteStudentModel(map);
					}
				}
				if(null!=counts && counts.size()>0){
					return counts.get(0)==null?null:Integer.parseInt(counts.get(0));
				}
			} catch (Exception e){
				logger.error(" CheckDeleteClass(Map<String, Object>)",e);
			}
			return null;
		}   
	private void deleteStudentModel(Map<String,Object> map){
		try{
			ISqlElement sqlElment=this.processSql(map,"Innercourse.selectCheckdeleteClass.delete");
			this.getJdbcTemplate().update(sqlElment.getSql(),sqlElment.getParams());
		}catch(Exception e){
			logger.error("deleteStudentModel(Map<String,Object>)",e);
			throw new ManagerException(e);
		}
	}
	public void deleteKcourse(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("course_id",id);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("sementmodelid", this.selectSegmentModelId(params));
			try{
				ISqlElement sqlElement=this.processSql(params,"Innercourse.deleteArrange.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				if(this.selectSegmentModelId(params).size()>0){
					ISqlElement sqlElement1=this.processSql(map,"Innercourse.deleteClassModel.delete");
					this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				}
				ISqlElement sqlElement2=this.processSql(params,"Innercourse.deleteSegmentModelwherecourseid.delete");
				this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
				ISqlElement sqlElement3=this.processSql(params,"Innercourse.deleteCourse.delete");
				this.getJdbcTemplate().update(sqlElement3.getSql(),sqlElement3.getParams());
			}catch(Exception e){
				logger.error("deletePartinfo(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
	}
	public void updateKcourse(KcourseDto courseDto, String[] arr) {
		if(courseDto!=null){
			try{
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("subject_id",courseDto.getSubject_id());
				params.put("series_id",courseDto.getSeries_id());
				params.put("course_code",courseDto.getCourse_code());
				params.put("course_name",courseDto.getCourse_name());
				params.put("course_kind",courseDto.getCourse_kind());
				params.put("student_aspect",courseDto.getStudent_aspect());
				params.put("period_count",courseDto.getPeriod_count());
				params.put("credit_hour",courseDto.getCredit_hour());
				params.put("course_remark",courseDto.getCourse_remark());
				params.put("course_short_name",courseDto.getCourse_short_name());
				params.put("course_id",courseDto.getCourse_id());
				ISqlElement sqlElement=this.processSql(params,"Innercourse.updateCourse.update");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement2=this.processSql(params,"Innercourse.deleteArrange.delete");
				this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
				if(arr!=null){
					final String courseid = courseDto.getCourse_id();
					final List<String> gradeList = new ArrayList<String>();
					final List<String> orderList = new ArrayList<String>();
					for(int i=0;i<arr.length;i++){
						String[] arrange = new String[2];
						arrange = arr[i].split("\\^");
						gradeList.add(arrange[0]);
						orderList.add(arrange[1]);
					}
					Map<String,Object> map=new HashMap<String,Object>();
					ISqlElement sqlElement1=this.processSql(map,"Innercourse.insertCourseArrange.add");
					this.getJdbcTemplate().batchUpdate(sqlElement1.getSql(),
			                new BatchPreparedStatementSetter() {
						    public int getBatchSize() {
		                        return gradeList.size();
		                    }
							public void setValues(PreparedStatement preparedStatement,
			                            int i) throws SQLException {
			                    	preparedStatement.setObject(1, courseid);
			                    	preparedStatement.setObject(2, orderList.get(i));
			                        preparedStatement.setObject(3, gradeList.get(i));
			                      
			                    }
			                   
			                });
				}
			}catch(Exception e){
				logger.error("updateKcourse(KcourseDto,String[])",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			
		}
	}
	
	public List<GeneralDto> selectYear(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlElement=this.processSql(queryMap,"Innercourse.selectSchoolyear.select");
			return this.findList("Innercourse.selectSchoolyear.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					GeneralDto dto = new GeneralDto();
					dto.setSchoolyear(rs.getString("schoolyear"));
					dto.setSchoolyearname(rs.getString("schoolyearname"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectYear(Map)",e);
		}
		return null;
	}
	
	public List<GeneralDto> selectSegment(Map<String, Object> queryMap) {
		try {
			return this.findList("Innercourse.selectStudySegment.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					GeneralDto dto = new GeneralDto();
					dto.setSchoolyear(rs.getString("schoolyear"));
					dto.setSegmentid(rs.getString("segment_id")+"--"+rs.getString("name"));
					dto.setSegmentorder(rs.getString("segment_order"));
					dto.setSegmentName(rs.getString("name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectSegment(Map)",e);
		}
		return null;
	}
	
	public List<KcourseDto> selectTeacherCourse(Map<String, Object> queryMap) {
		try {
//			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectTeacherCourse.select");
			return this.findList("Innercourse.selectTeacherCourse.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseDto dto = new KcourseDto();
					dto.setCourse_id(rs.getString("course_id"));
					dto.setCourse_name(rs.getString("course_subject_name"));
					String str = rs.getString("course_subject_name");
					if(!StringUtil.isEmpty(str)){
						dto.setSubject_name(str.substring(str.lastIndexOf("(")+1, str.length()-1));
						dto.setCourse_code(str.substring(0,str.lastIndexOf("(")));
					}
					
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectTeacherCourse(Map)",e);
		}
		return null;
	}
	
	public List<GeneralDto> selectGread(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectGrade.select");
			return this.findList("Innercourse.selectGrade.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					GeneralDto dto = new GeneralDto();
					dto.setGradename(rs.getString("gradename"));
					dto.setGradeid(rs.getString("gradeid"));
					dto.setGradenum(rs.getString("gradenum"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectGread(Map)",e);
		}
		return null;
	}
	
	public List<GeneralDto> selectClass(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectClass.select");
			List<GeneralDto> dtos=this.findList("Innercourse.selectClass.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					GeneralDto dto = new GeneralDto();
					dto.setClassid(rs.getString("classid"));
					dto.setClassname(rs.getString("classsname"));
					dto.setClassnum(rs.getString("classnum"));
					return dto;
				}
			});
			if(dtos==null||dtos.isEmpty()){
				//查询当前学年
				dtos=this.findList("Innercourse.selectClass.select1", queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						GeneralDto dto = new GeneralDto();
						dto.setClassid(rs.getString("classid"));
						dto.setClassname(rs.getString("classsname"));
						dto.setClassnum(rs.getString("classnum"));
						return dto;
					}
				});
			}
			return dtos;
		} catch (Exception e) {
			logger.error("selectClass(Map)",e);
		}
		return null;
	}
	
	
	public List<GeneralDto> selectNowClass(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectNowClass.select");
			List<GeneralDto> dtos=this.findList("Innercourse.selectNowClass.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					GeneralDto dto = new GeneralDto();
					dto.setClassid(rs.getString("classid"));
					dto.setClassname(rs.getString("classsname"));
					dto.setClassnum(rs.getString("classnum"));
					dto.setGradenum(rs.getString("gradenum"));
					dto.setGradename(rs.getString("gradename"));
					return dto;
				}
			});
			if(dtos==null||dtos.isEmpty()){
				dtos=this.findList("Innercourse.selectNowClass.select2", queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						GeneralDto dto = new GeneralDto();
						dto.setClassid(rs.getString("classid"));
						dto.setClassname(rs.getString("classsname"));
						dto.setClassnum(rs.getString("classnum"));
						dto.setGradenum(rs.getString("gradenum"));
						dto.setGradename(rs.getString("gradename"));
						return dto;
					}
				});
			}
			return dtos;
		} catch (Exception e) {
			logger.error("selectClass(Map)",e);
		}
		return null;
	}
	public void insertSegmentModel(final String segmentid,final List<String> courseList,List<String> classList,Map<String, String> queryMap) {
		if(StringUtil.isNotEmpty(segmentid)&&courseList!=null){
			String unitId = queryMap.get("unitid");
			String campusId = queryMap.get("campusid");
			String cmis30Id = queryMap.get("cmis30Id");
			queryMap.remove("unitid");
			queryMap.remove("campusid");
			queryMap.remove("cmis30Id");
			try{
				final List<String> uuidList = new ArrayList<String>();
				for(int j=0;j<courseList.size();j++){
					String uuid = getId();
					uuidList.add(uuid);
				}
				Map<String,Object> map=new HashMap<String,Object>();
				ISqlElement sqlElement1=this.processSql(map,"Innercourse.insertSegmentModel.add");
				this.getJdbcTemplate().batchUpdate(sqlElement1.getSql(),
		                new BatchPreparedStatementSetter() {
					    public int getBatchSize() {
	                        return courseList.size();
	                    }
						public void setValues(PreparedStatement preparedStatement,
		                            int i) throws SQLException {
		                    	preparedStatement.setObject(1, uuidList.get(i));
		                    	preparedStatement.setObject(2, segmentid);
		                    	preparedStatement.setObject(3, courseList.get(i));
		                    	preparedStatement.setObject(4, "1");
		                    }
		                });
				final List<KclassModelDto>innerCourseTeacherInfos = new ArrayList<KclassModelDto>();
				List<String> innerCourseTeacherIds = new ArrayList<String>();
				List<KcourseDto> insertTeacherIds = new ArrayList<KcourseDto>();
				for(int j=0;j<classList.size();j++){
					for(int i=0;i<courseList.size();i++){
						for (String key : queryMap.keySet()) {
							  if(courseList.get(i).equals(key.split("_")[1])&&classList.get(j).equals(key.split("_")[0])){
								  KclassModelDto kmDto = new KclassModelDto();
								 // params.put("sement_model_id", uuidList.get(i));
								  kmDto.setSement_model_id(uuidList.get(i));
								  if(!"@@".equals(queryMap.get(key))){
									 // params.put("teacherid", queryMap.get(key));
									  KcourseDto insertId = new KcourseDto();
									  insertId.setTeacherId(queryMap.get(key));
									  insertTeacherIds.add(insertId);
									  kmDto.setTeacherid(queryMap.get(key));
									  innerCourseTeacherIds.add(queryMap.get(key));
								  }else{
//									  params.put("teacherid", "");
									  kmDto.setTeacherid("");
								  }
//								  params.put("classid", classList.get(j));
								  kmDto.setClassid(classList.get(j));
								  //查询是否应该有schoolyear
								  String schoolyear=querySchoolyear(segmentid,classList.get(j));
								  kmDto.setSchoolyear(schoolyear);
								  innerCourseTeacherInfos.add(kmDto);
								  break;
								}
						}
					}
				}
				
				//批量插入前检测老师角色是否存在
				Map<String,Object> params=new HashMap<String,Object>();
				if(null==innerCourseTeacherIds || innerCourseTeacherIds.size()==0){
					innerCourseTeacherIds = new ArrayList<String>();
					innerCourseTeacherIds.add("-1");
				}
				params.put("teacherIds", innerCourseTeacherIds);
				params.put("insertTeacherIds", insertTeacherIds);
				params.put("unitid", unitId);
				params.put("campusid", campusId);
				params.put("cmis30Id", cmis30Id);
				this.checkTeacherRoles(params);
				//批量插入数据  内置课程老师授课数据
				ISqlElement sqlElement=this.processSql(params,"Innercourse.insertClassModel.add");
				getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KclassModelDto kmDto = innerCourseTeacherInfos.get(i);
						ps.setString(1, kmDto.getSement_model_id());
						ps.setString(2, kmDto.getTeacherid());
						ps.setString(3, kmDto.getClassid());
						ps.setString(4,kmDto.getSchoolyear());
					}
					public int getBatchSize() {
						return innerCourseTeacherInfos.size();
					}
				});
			}catch(Exception e){
				logger.error("insertSegmentModel(KsegmentModelDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
	}
	
	private String querySchoolyear(String segmentid, String classid) {
		Map<String,Object>params = new HashMap<String, Object>();
		try {
			params.put("segmentid",segmentid);
			params.put("classid", classid);
			List<String>schoolyears=this.findList("SchoolCourseExt.querySchoolyear.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("schoolyear");
				}
			});
			if(schoolyears!=null&&schoolyears.size()>0)
				return schoolyears.get(0);
		} catch (Exception e) {
			logger.error("querySchoolyear(String,String)", e);
		}
		return null;
	}


	private void checkTeacherRoles(Map<String, Object> queryMap) {
		String roleId = this.queryRoleId(queryMap);
		queryMap.put("roleId", roleId);
		List<KcourseDto>kgTeacherInfos = this.queryTeacherInfos(queryMap,"roles");
		if(kgTeacherInfos==null || kgTeacherInfos.size()==0)return;
		List<KcourseDto>needInsertTeacherIds = new ArrayList<KcourseDto>();
		for(KcourseDto kDto : kgTeacherInfos){
			if(NestUtil.isEmpty(kDto.getRoleId())
					&&NestUtil.isEmpty(kDto.getUserRoleId())
					&&NestUtil.isEmpty(kDto.getCampusId())){
				kDto.setRoleId(roleId);
				kDto.setUserRoleId(baseInforManagerExt.queryProKey("o_userrole"));
				kDto.setCampusId((String)queryMap.get("campusid"));
				needInsertTeacherIds.add(kDto);
			}
		}
		if(needInsertTeacherIds==null || needInsertTeacherIds.size()==0)return;
		this.bachInsertRole(needInsertTeacherIds);
	}
	private void queryTeacherInfoFromCZ(Map<String, Object> queryMap,String roleId,List<KcourseDto> needGoOnTeacherInfo) {
		List<KcourseDto>kcTeacherInfos = this.queryTeacherInfos(queryMap,"kc");
		List<KcourseDto>needAddRoleTeacherInfo = new ArrayList<KcourseDto>();
		if(null!=kcTeacherInfos && kcTeacherInfos.size()>0){
			for(KcourseDto ndTeacherInfo : needGoOnTeacherInfo){
				if(!kcTeacherInfos.contains(ndTeacherInfo) && !needAddRoleTeacherInfo.contains(ndTeacherInfo)){
					ndTeacherInfo.setRoleId(roleId);
					ndTeacherInfo.setUserRoleId(baseInforManagerExt.queryProKey("o_userrole"));
					ndTeacherInfo.setCampusId((String)queryMap.get("campusid"));
					needAddRoleTeacherInfo.add(ndTeacherInfo);
				}
			}
		}else{
			this.bachInsertRole(needGoOnTeacherInfo);
		}
		if(needAddRoleTeacherInfo==null || needAddRoleTeacherInfo.size()==0)return;
		this.bachInsertRole(needAddRoleTeacherInfo);
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
	private List<KcourseDto> queryUserIds(List<String> insertTeacherIds) {
		Map<String,Object>params = new HashMap<String, Object>();
		try {
			params.put("teacherIds", insertTeacherIds);
			ISqlElement sqlDemo=this.processSql(params, "IInnerCourseExt.queryUserIdFromDB.query");
			return this.findList("IInnerCourseExt.queryUserIdFromDB.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KcourseDto kDto = new KcourseDto();
					kDto.setTeacherId(rs.getString("personid"));
					kDto.setUserId(rs.getString("personid"));
					return kDto;
				}
			});
		} catch (Exception e) {
			logger.error("queryUserIds(List<String>)", e);
		}
		return null;
	}
	private List<KcourseDto> queryTeacherInfos(Map<String, Object> queryMap,String flag) {
		try {
			String preSql = "";
			if("kg".equals(flag)){
				preSql = "IInnerCourseExt.queryKgTeacherInfo.query";
			}else if("kc".equals(flag)){
				preSql = "IInnerCourseExt.queryKcTeacherInfo.query";
			}else if("delete".equals(flag)){
				preSql = "IInnerCourseExt.queryBeforeDelete.query";
			}else if("roles".equals(flag)){
				preSql = "IInnerCourseExt.queryRoles.query";
			}else{
				preSql = "IInnerCourseExt.queryBeforeTeacherId.query";
			}
			ISqlElement sqlElement=this.processSql(queryMap,preSql);
			/*System.out.println(sqlElement);*/
			return this.findList(preSql, queryMap, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					KcourseDto kDto = new KcourseDto();
					if("null".equals(rs.getString("teacherId"))||NestUtil.isEmpty(rs.getString("teacherId"))){
						kDto.setTeacherId(null);
					}else{
						kDto.setTeacherId(rs.getString("teacherId"));
					}
					kDto.setUserId(rs.getString("userid"));
					kDto.setRoleId(rs.getString("roleid"));
					kDto.setUserRoleId(rs.getString("userroleid"));
					kDto.setCampusId(rs.getString("campusid"));
					kDto.setRownum(NestUtil.isNotEmpty(rs.getString("count"))?Integer.parseInt(rs.getString("count")):0);
					return kDto;
				}
			});
		} catch (Exception e) {
			logger.error("InnerCourseExtImpl.queryKgTeacherInfos(Map<String, Object>)", e);
		}
		return null;
	}
	public String queryRoleId(Map<String, Object> queryMap){
		try {
			//params.put("unitid", newRoleTeacher.getUnitid());
			queryMap.put("roletype", Constant.USER_TYPE_COURSEMASTER);
			List<String>roleIds = this.findList("SchoolCourseExt.queryTeacherRoleId.query", queryMap, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("roleId");
				}
			});
			if(null!=roleIds && roleIds.size()>0){
				return roleIds.get(0);
			}
		} catch (Exception e) {
			logger.error("InnerCourseExtImpl.queryRoleDtos(Map<String, Object>)", e);
		}
		return null;
	}
	public void insertExlSegmentModel(final String segmentid,List<String> lst,Map<String, Object> classMap,int yearcount) {
		if(StringUtil.isNotEmpty(segmentid)&&lst!=null){
			try{
				UserDto userDto = (UserDto) classMap.get("userDto");
				classMap.remove("userDto");
				List<String> allList = new ArrayList<String>();//导入的有授课教师的模块名称
				for(int i=0;i<lst.size();i++){
					allList.add(lst.get(i).split("#!#!")[0]+"#!#!"+lst.get(i).split("#!#!")[1]);
				}
				this.remove(allList);
				final List<KcourseDto>insertTeacherInfo = new ArrayList<KcourseDto>();
				List<String>insertIds = new ArrayList<String>();
				for(int j=0;j<allList.size();j++){
					String uuid = getId();
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("segment_id",segmentid.split("--")[0]);
					params.put("sement_model_id",uuid);
					params.put("course_id",allList.get(j).split("#!#!")[0]);
					params.put("is_assign","1");
					ISqlElement sqlElement=this.processSql(params,"Innercourse.insertSegmentModel.add");
					this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
					List<GeneralDto> classList = new ArrayList<GeneralDto>();
					List<String> gnumlist = new ArrayList<String>();
					gnumlist.add(allList.get(j).split("#!#!")[1]);
					classMap.put("gradenumlist", gnumlist);
					classList = this.selectNowClass(classMap);
					//excelImport
					for(int m=0;m<classList.size();m++){
						KcourseDto kDto = new KcourseDto();
						GeneralDto dto = new GeneralDto(); 
						dto = classList.get(m);
						 /* Map<String,Object> classmodel=new HashMap<String,Object>();*/
						//  classmodel.put("sement_model_id", uuid);
						  kDto.setSegment_id(uuid);
						 // classmodel.put("classid", dto.getClassid());
						  kDto.setClassid(dto.getClassid());
						  //c745430e849644bfaf336cc50410b21d--第1学段
						  String schoolyear=querySchoolyear(segmentid.split("--")[0],dto.getClassid());
						  kDto.setSchoolyear(schoolyear);
						  for(int n=0;n<lst.size();n++){
								if(dto.getClassnum().equals(lst.get(n).split("#!#!")[2])&&allList.get(j).split("#!#!")[0].equals(lst.get(n).split("#!#!")[0])&&dto.getGradenum().equals(Integer.parseInt(lst.get(n).split("#!#!")[1])-yearcount+"")){
									//classmodel.put("teacherid", lst.get(n).split77("#!#!")[3]);
									if(!"null".equals(lst.get(n).split("#!#!")[3])&&NestUtil.isNotEmpty(lst.get(n).split("#!#!")[3])){
										kDto.setTeacherId(lst.get(n).split("#!#!")[3]);
									}
									if(!"null".equals(lst.get(n).split("#!#!")[3])&&NestUtil.isNotEmpty(lst.get(n).split("#!#!")[3]))
										insertIds.add(lst.get(n).split("#!#!")[3]);
									break;
								}
							}
						  insertTeacherInfo.add(kDto);
//						  ISqlElement sqlElement1=this.processSql(classmodel,"Innercourse.insertClassModel.add");
//						  this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
					}
				}
				//批量插入前检测老师角色是否存在
				Map<String,Object> params=new HashMap<String,Object>();
				if(null==insertIds|| insertIds.size()==0){
					insertIds = new ArrayList<String>();
					insertIds.add("-1");
				}
				params.put("teacherIds", insertIds);
				params.put("insertTeacherIds", insertTeacherInfo);
				params.put("unitid", userDto.getUnitid());
				params.put("campusid", userDto.getCampuseId());
				params.put("cmis30Id", userDto.getCmis30id());
				this.checkTeacherRoles(params);
				//批量插入数据  内置课程老师授课数据
				ISqlElement sqlElement=this.processSql(params,"Innercourse.insertClassModel.add");
				getJdbcTemplate().batchUpdate(sqlElement.getSql(), new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						KcourseDto kmDto = insertTeacherInfo.get(i);
						ps.setString(1, kmDto.getSegment_id());
						ps.setString(2, kmDto.getTeacherId());
						ps.setString(3, kmDto.getClassid());
						ps.setString(4, kmDto.getSchoolyear());
					}
					public int getBatchSize() {
						return insertTeacherInfo.size();
					}
				});
			}catch(Exception e){
				logger.error("insertSegmentModel(KsegmentModelDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}
	}
	
	public List<KclassModelDto> selectClassModel(Map<String, Object> queryMap) {
		try {
			/*ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectClassModel.select");*/
			return this.findList("Innercourse.selectClassModel.select2", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KclassModelDto dto = new KclassModelDto();
					dto.setClassid(rs.getString("classid"));
					dto.setCourse_id(rs.getString("course_id"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setSegment_id(rs.getString("segment_id"));
					dto.setTeacherid(rs.getString("teacherid"));
					dto.setTeachercode(rs.getString("teachercode"));
					dto.setClass_model_id(rs.getString("class_model_id"));
					dto.setSubject_name(rs.getString("subject_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectClass(Map)",e);
		}
		return null;
	}
	
	
	public List<String> selectSegmentModelId(Map<String, Object> queryMap) {
		try {
			/*ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.selectSegmentModelId.select");*/
			return this.findList("Innercourse.selectSegmentModelId.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String id = rs.getString("sement_model_id");
					return id;
				}
			});
		} catch (Exception e) {
			logger.error("selectClass(Map)",e);
		}
		return null;
	}
	
	public void deleteTeacherCourse(String courseid, String segmentid, String cmis30id, String year, String gradeid){
		if(!StringUtil.isEmpty(courseid)&&!StringUtil.isEmpty(segmentid)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("course_id",courseid);
			params.put("segment_id",segmentid);
			params.put("cmis30id",cmis30id);
			params.put("year",year);
			params.put("gradeid",gradeid);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("sementmodelid", this.selectSegmentModelId(params));
			try{
				ISqlElement sqlElement=this.processSql(map,"Innercourse.deleteClassModel.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement1=this.processSql(map,"Innercourse.deleteSegmentModel.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
			}catch(Exception e){
				logger.error("deleteTeacherCourse(String)",e);
				throw new ManagerException(e);
			}
		}
	}
	public void deleteTeacherCourse(String courseid, String segmentid, String cmis30id, String year, String gradeid,UserDto userDto){
		if(!StringUtil.isEmpty(courseid)&&!StringUtil.isEmpty(segmentid)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("course_id",courseid);
			params.put("segment_id",segmentid);
			params.put("cmis30id",cmis30id);
			params.put("year",year);
			params.put("gradeid",gradeid);
			Map<String,Object> map=new HashMap<String,Object>();
			List<String> sements=this.selectSegmentModelId(params);
			if(null!=sements && sements.size()>0){
				map.put("sementmodelid",sements);
			}else{
				List<String>temp = new ArrayList<String>();
				temp.add("-1");
				map.put("sementmodelid", temp);
			}
			try{
				
				//删除之前校验
				this.checkBeforeDelete(map,userDto);
				map.put("cmis30id",cmis30id);
				map.put("year",year);
				map.put("gradeid",gradeid);
				//删除相关联学生的学生成绩
				deleStudentScore(map);
				ISqlElement sqlElement=this.processSql(map,"Innercourse.deleteClassModel.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				//判断该sementmodelid下是否有相应的模块
				Integer count=checkClassModelCount(map);
				if(count==0){
					ISqlElement sqlElement1=this.processSql(map,"Innercourse.deleteSegmentModel.delete");
					this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				}
			}catch(Exception e){
				logger.error("deleteTeacherCourse(String)",e);
				throw new ManagerException(e);
			}
		}
	}
	private void deleStudentScore(Map<String, Object> map){
		try{
			ISqlElement sqlDemo=this.processSql(map,"Innercourse.deleStudentScore.del");
			
			this.getJdbcTemplate().update(sqlDemo.getSql(),sqlDemo.getParams());
		}catch(Exception e){
			logger.error("deleStudentScore(Map<String, Object>)",e);
			throw new ManagerException(e);
		}
		
	}
	private Integer checkClassModelCount(Map<String, Object> map) {
		try{
			List<Integer> counts=this.findList("Innercourse.checkClassModelCount.count",map,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getInt("cot");
				}
			});
			if(counts!=null&&counts.size()>0){
				return counts.get(0);
			}
			return 0;
		}catch(Exception e){
			logger.error("checkClassModelCount(Map<String, Object>)",e);
			throw new ManagerException(e);
			
		}
	}


	private void checkBeforeDelete(Map<String, Object> params,UserDto userDto) {
		//1.查出删除前数据
		params.put("unitid", userDto.getUnitid());
		String roleId = this.queryRoleId(params);
		params.put("roleId", roleId);
		params.put("campusid", userDto.getCampuseId());
		params.put("cmis30Id", userDto.getCmis30id());
		List<KcourseDto> beforeDeleteInfos = this.queryTeacherInfos(params, "delete");
		if(null==beforeDeleteInfos || beforeDeleteInfos.size()==0)return;
		List<KcourseDto>mayBeDeleteInfo = new ArrayList<KcourseDto>();
		List<String>mayBeDeleteIds = new ArrayList<String>();
		for(KcourseDto kDto : beforeDeleteInfos){
			if(NestUtil.isNotEmpty(kDto.getTeacherId())&&kDto.getRownum()==1){
				mayBeDeleteInfo.add(kDto);
				mayBeDeleteIds.add(kDto.getTeacherId());
			}
		}
		if(null==mayBeDeleteIds||mayBeDeleteIds.size()==0)return;
		//2.存在可能删除的teacherId又走初中表
		params.put("teacherIds", mayBeDeleteIds);
		List<KcourseDto> beforeDeleteInfosCZ = this.queryTeacherInfos(params, "kc");
		if(null==beforeDeleteInfosCZ || beforeDeleteInfosCZ.size()==0){
			this.bachDelete(mayBeDeleteInfo);
			return;
		}
		List<KcourseDto>needDelete = new ArrayList<KcourseDto>();
		for(KcourseDto kDto : mayBeDeleteInfo){
			if(!beforeDeleteInfosCZ.contains(kDto)&&!needDelete.contains(kDto)){
				needDelete.add(kDto);
			}
		}
		if(null==needDelete||needDelete.size()==0)return;
		this.bachDelete(needDelete);
		
	}
	public void updateClassModel(final List<String> idList,final List<String> valueList) {
		Map<String,Object> map=new HashMap<String,Object>();
		ISqlElement sqlElement1;
		try {
			sqlElement1 = this.processSql(map,"Innercourse.updateClassModel.update");
			this.getJdbcTemplate().batchUpdate(sqlElement1.getSql(),
	                new BatchPreparedStatementSetter() {
				    public int getBatchSize() {
	                    return idList.size();
	                }
					public void setValues(PreparedStatement preparedStatement,
	                            int i) throws SQLException {
								preparedStatement.setObject(1, valueList.get(i));
								preparedStatement.setObject(2, idList.get(i));
	                    }
	                });
		} catch (Exception e) {
			logger.error("updateClassModel(final List<String>,final List<String>)",e);
			throw new ManagerException(e);
		}
	}
	public void updateClassModel(final List<String> idList,final List<String> valueList,UserDto userDto) {
		Map<String,Object> map=new HashMap<String,Object>();
		ISqlElement sqlElement1;
		try {
			//更新前校验
			map.put("updateTeacherIds", valueList);
			if(null!=idList && idList.size()>0){
				map.put("classModelIds", idList);
			}else{
				List<String> temp = new ArrayList<String>();
				temp.add("-1");
				map.put("classModelIds", temp);
			}
			if(null!=valueList && valueList.size()>0){
				this.checkUpdateTeacherInfo(map,userDto);
			}
			sqlElement1 = this.processSql(map,"Innercourse.updateClassModel.update");
			this.getJdbcTemplate().batchUpdate(sqlElement1.getSql(),
	                new BatchPreparedStatementSetter() {
				    public int getBatchSize() {
	                    return idList.size();
	                }
					public void setValues(PreparedStatement preparedStatement,
	                            int i) throws SQLException {
								preparedStatement.setObject(1, valueList.get(i));
								preparedStatement.setObject(2, idList.get(i));
	                    }
	                });
		} catch (Exception e) {
			logger.error("updateClassModel(final List<String>,final List<String>,UserDto)",e);
			throw new ManagerException(e);
		}
	}
	private void checkUpdateTeacherInfo(Map<String, Object> map, UserDto userDto) {
		map.put("cmis30Id", userDto.getCmis30id());
		map.put("campusid", userDto.getCampuseId());
		map.put("unitid", userDto.getUnitid());
		String roleId = this.queryRoleId(map);
		map.put("roleId", roleId);
		//1、获取更新前的teacherid
		List<KcourseDto> beforeTeacherIds = this.queryTeacherInfos(map,"");
		//更新数据
		List<String>teacherIds = (List<String>) map.get("updateTeacherIds");
		if(null==teacherIds || teacherIds.size()==0)throw new ManagerException();
		List<KcourseDto>updateTeacherIds = new ArrayList<KcourseDto>();
		for(String upId : teacherIds){
			if(NestUtil.isNotEmpty(upId)){
				KcourseDto kcd = new KcourseDto();
				kcd.setTeacherId(upId);
				updateTeacherIds.add(kcd);
			}
		}
		//2、更新前后做对比
		if(beforeTeacherIds!=null && beforeTeacherIds.size()>0){
			//2.1遍历更新数据
			List<String>mayBeDeleteRole = new ArrayList<String>();
			List<KcourseDto>mayBeDeleteTeacherInfo = new ArrayList<KcourseDto>();
			for(KcourseDto beforeTeacherId : beforeTeacherIds){
				if(!updateTeacherIds.contains(beforeTeacherId) && !mayBeDeleteTeacherInfo.contains(beforeTeacherId)&&beforeTeacherId.getRownum()==1){
					mayBeDeleteTeacherInfo.add(beforeTeacherId);
					mayBeDeleteRole.add(beforeTeacherId.getTeacherId());
				}
			}
			if(mayBeDeleteTeacherInfo!=null && mayBeDeleteTeacherInfo.size()>0){
				map.put("teacherIds", mayBeDeleteRole);
				List<KcourseDto>kcTeacherInfos = this.queryTeacherInfos(map,"kc");
				List<KcourseDto>needDeleteTeacherInfo = new ArrayList<KcourseDto>();
				if(null!=kcTeacherInfos && kcTeacherInfos.size()>0){
					for(KcourseDto ndTeacherInfo : mayBeDeleteTeacherInfo){
						if(!kcTeacherInfos.contains(ndTeacherInfo) && !needDeleteTeacherInfo.contains(ndTeacherInfo)){
							needDeleteTeacherInfo.add(ndTeacherInfo);
						}
					}
					if(null!=needDeleteTeacherInfo && needDeleteTeacherInfo.size()>0){
						//删除角色
						this.bachDelete(needDeleteTeacherInfo);
					}
				}else{
					//删除角色
					this.bachDelete(mayBeDeleteTeacherInfo);
				}
			}
			List<String>mayBeInsertRole = new ArrayList<String>();
			List<KcourseDto>mayBeInsertRoles = new ArrayList<KcourseDto>();
			//2.4有小部分数据是交集的（可能没有授予角色）
			for(KcourseDto beforeTeacherId : beforeTeacherIds){
				if(updateTeacherIds.contains(beforeTeacherId) && NestUtil.isEmpty(beforeTeacherId.getRoleId())){
					mayBeInsertRoles.add(beforeTeacherId);
					mayBeInsertRole.add(beforeTeacherId.getTeacherId());
				}
			}
			//2.3遍历更新前的数据
			for(KcourseDto updateTeacherId : updateTeacherIds){
				if(!beforeTeacherIds.contains(updateTeacherId) && !mayBeInsertRoles.contains(updateTeacherId)){
					mayBeInsertRoles.add(updateTeacherId);
					mayBeInsertRole.add(updateTeacherId.getTeacherId());
				}
			}
			if(mayBeInsertRoles!=null && mayBeInsertRoles.size()>0){
				List<KcourseDto> needInsertTeacherIds = this.initInsertDatas(map,userDto, roleId, mayBeInsertRole);
				if(needInsertTeacherIds==null || needInsertTeacherIds.size()==0)return;
				this.bachInsertRole(needInsertTeacherIds);
			}
		}else{
			List<String> temp = new ArrayList<String>();
			for(String tId : teacherIds){
				if(NestUtil.isNotEmpty(tId)){
					temp.add(tId);
				}
			}
			if(null!=temp && temp.size()>0){
				List<KcourseDto> needInsertTeacherIds = this.initInsertDatas(map,userDto, roleId, temp);
				if(needInsertTeacherIds==null || needInsertTeacherIds.size()==0)return;
				this.bachInsertRole(needInsertTeacherIds);
			}
		}
	}
	private List<KcourseDto> initInsertDatas(Map<String, Object> map,
			UserDto userDto, String roleId, List<String> mayBeInsertRole) {
		map.put("teacherIds", mayBeInsertRole);
		List<KcourseDto>kgTeacherInfos = this.queryTeacherInfos(map,"roles");
		if(kgTeacherInfos==null || kgTeacherInfos.size()==0)throw new ManagerException();
		List<KcourseDto>needInsertTeacherIds = new ArrayList<KcourseDto>();
		for(KcourseDto kDto : kgTeacherInfos){
			if(NestUtil.isEmpty(kDto.getRoleId())
					&&NestUtil.isEmpty(kDto.getUserRoleId())
					&&NestUtil.isEmpty(kDto.getCampusId())){
				kDto.setRoleId(roleId);
				kDto.setUserRoleId(baseInforManagerExt.queryProKey("o_userrole"));
				kDto.setCampusId(userDto.getCampuseId());
				needInsertTeacherIds.add(kDto);
			}
		}
		return needInsertTeacherIds;
	}
	private void bachDelete(final List<KcourseDto> deleteTeacherInfos) {
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
	private List<String> querryBeforeTeacherIds(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "IInnerCourseExt.queryBeforeTeacherId.query");
			return this.findList("IInnerCourseExt.queryBeforeTeacherId.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("teacherId");
				}
			});
		} catch (Exception e) {
			logger.error("querryBeforeTeacherIds(Map<String, Object>)",e);
		}
		return null;
	}
	public List<DownloadDto> selectDownload(Map<String, Object> queryMap) {
		try {
			ISqlElement sqlDemo=this.processSql(queryMap, "Innercourse.download.select1");
			return this.findList("Innercourse.download.select1", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					DownloadDto dto = new DownloadDto();
					dto.setClass_model_id(rs.getString("class_model_id"));
					dto.setClassnum(rs.getString("classnum"));
					dto.setClasssname(rs.getString("classsname"));
					dto.setCourse_name(rs.getString("course_name"));
					dto.setGradename(rs.getString("gradename"));
					dto.setGradenum(rs.getString("gradenum"));
					dto.setSubject_name(rs.getString("subject_name"));
					dto.setTeachername(rs.getString("teachername"));
					dto.setTeacherid(rs.getString("teacherid"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("selectClass(Map)",e);
		}
		return null;
	}
	private IBaseInforManagerExt baseInforManagerExt;
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}

	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}
	
	/**
	 * list去重
	 * @param lst
	 */
	public void remove(List<String> lst) {
		for(int i = 0;i<lst.size()-1;i++){ 
		    for (int j = lst.size()-1;j>i;j--){ 
		      if(lst.get(j).equals(lst.get(i))){ 
		    	  lst.remove(j); 
		      } 
		    } 
		} 
	}
	@Override
	public List<KcourseDto> selectInnertCourseCheckList1(
			Map<String, Object> params) {
		try {
			return this.findList("Innercourse.selectCourseCheck.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					KcourseDto kcourse = new KcourseDto();
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
					/*Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("courseid", rs.getString("course_id"));
					List<KcourseArrangeDto> lst = selectArrange(queryMap1);
					kcourse.setKcourseArrangeList(lst);	*/
					return kcourse;
				}
			});
		} catch (Exception e) {
			logger.error("selectInnertCourseList(Map)",e);
		}
		return null;
	}


	@Override
	public void insertQSDatas(List<String> insertData, UserDto userDto) {
		//获取sementModelId参数
		//courseId
		//segmentId
		//...分解参数
		Map<String,Object>params = new HashMap<String, Object>();
		List<String>queryParams = new ArrayList<String>();
		List<String>teacherIds = new ArrayList<String>();
		for(String teacherId_classId_courseId_smentId : insertData){
			String[] infos = teacherId_classId_courseId_smentId.split("--");
			queryParams.add(" (course_id='"+infos[2]+"' and segment_id='"+infos[3]+"') ");
			if(!teacherIds.contains(infos[0])){
				teacherIds.add(infos[0]);
			}
		}
		params.put("queryParams", queryParams);
		List<String>sementIds = this.querySmentIds(params);
		if(null==sementIds || sementIds.size()==0)return;
		//...组装插入数据
		List<String>insertDats = new ArrayList<String>();
		for(String teacherId_classId_courseId_smentId : insertData){
			for(String sementModleId_courseid_segmentId : sementIds){
				String[] tempInfos = sementModleId_courseid_segmentId.split("@");
				String sementModleId = 2==tempInfos.length?tempInfos[0]:"";
				String courseid_segmentId = 2==tempInfos.length?tempInfos[1]:"";
				if(teacherId_classId_courseId_smentId.contains(courseid_segmentId)){
					insertDats.add(teacherId_classId_courseId_smentId+"--"+sementModleId);
				}
			}
		}
		params.put("unitid", userDto.getUnitid());
		params.put("campusid", userDto.getCampuseId());
		params.put("teacherIds", teacherIds);
		this.checkTeacherRoles(params);
		this.bachInsertQSDatas(insertDats);
		//插入授课表参数
		//teacherId
		//classId
		//sementModelId
	}
	private void bachInsertQSDatas(final List<String> sementIds) {
		try {
			//插入
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlElement1=this.processSql(params,"Innercourse.bachInsertQSDatas.insert");
			getJdbcTemplate().batchUpdate(sqlElement1.getSql(), new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					String temp = sementIds.get(i);
					ps.setString(1, temp.split("--")[4]);
					ps.setString(2, temp.split("--")[0]);
					ps.setString(3, temp.split("--")[1]);
				}
				public int getBatchSize() {
					return sementIds.size();
				}
			});
		} catch(Exception e) {
			logger.error("Innercourse.bachInsertQSDatas(List<String>)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	/**
	 * 
	 * @param queryParams
	 * @return List<sementId@courseId--segmentId>
	 */
	private List<String> querySmentIds(Map<String,Object> params) {
		try {
			return this.findList("Innercourse.querySmentIds.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("infos");
				}
			});
		} catch (Exception e) {
			logger.error("Innercourse.querySmentIds(Map)",e);
		}
		return null;
	}
}
