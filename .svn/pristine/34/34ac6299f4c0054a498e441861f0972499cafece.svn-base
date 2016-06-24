package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AapprasialCacheXieDto;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.AppraisalxieStirngDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.util.DataSource;



public class PlayAndHealthExtImpl extends JdbcRootManager implements IPlayAndHealthExt{
	
	private  ILatestEvaluationRecordExt latestEvaluationRecordExt;
	 
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}
	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	private ConstantBean constantBean;
	
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	@DataSource("read")
	public StudentxieDto findstudent(Integer studentid, Integer discode, Integer cmis30id) {
		 if(studentid==null){
			 return  null;
		  }
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("studentid",studentid);
		params.put("discode",discode);
		params.put("cmis30id",cmis30id);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.findstudent.query");
			List<StudentxieDto > student =this.findList("PlayAndHealthExtImpl.findstudent.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentxieDto dto=new StudentxieDto();
						dto.setStudentid(rs.getInt("studentid"));
						dto.setClassid(rs.getInt("classid"));
						dto.setName(rs.getString("name"));
						dto.setEduid(rs.getString("edu_id"));
						return dto;
				}
			});
			if(student!=null&&student.size()>0)
				return student.get(0);
		}catch(Exception e){
			logger.error("findstudent(Integer)",e);
			throw new ManagerException(e);
		}
		return null;
	}
	
	
	//树的节点
	public StudentxieDto findstudentTreeNode(Integer studentid,
			Integer discode, Integer cmis30id) {
		if(studentid==null){
			 return  null;
		  }
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("studentid",studentid);
		params.put("discode",discode);
		params.put("cmis30id",cmis30id);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.findstudentTreeNode.query");
			List<StudentxieDto > student =this.findList("PlayAndHealthExtImpl.findstudentTreeNode.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentxieDto dto=new StudentxieDto();
						dto.setClassid(rs.getInt("classid"));
						dto.setClassname(rs.getString("classsname"));
						dto.setGradename(rs.getString("gradename"));
						return dto;
				}
			});
			if(student!=null&&student.size()>0)
				return student.get(0);
		}catch(Exception e){
			logger.error("findstudent(Integer)",e);
			throw new ManagerException(e);
		}
		return null;
	}
	
	
	/**
	 * 查询全班某项的评价
	 */
	@DataSource("read")
	public List<AppraisalxieDto> selectSelfAppraiseXie(Integer classid,
			String evaluateType, String termId,String studentid,
			Integer discode, Integer cmis30id) {
		if(classid==null||evaluateType==null||termId==null){
			return null;
		}
		  Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("classid",new Integer(classid));
			queryMap.put("semesterid", termId);    
			queryMap.put("appraisaltypeid", Integer.parseInt(evaluateType));
			queryMap.put("cmis30id", cmis30id);
			queryMap.put("discode", discode);
			queryMap.put("appraiserrid",studentid);
			
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.selectSelfAppraiseXie.query");
				//param  为参数
				List<AppraisalxieDto> Appraisal=this.findList("PlayAndHealthExtImpl.selectSelfAppraiseXie.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraisalxieDto dto=new AppraisalxieDto();
			            dto.setStudentid(rs.getInt("studentid"));
			            dto.setAppraser(rs.getString("appraser"));
			            dto.setApprasial(rs.getString("apprasial"));  
			            dto.setApprasialid(rs.getInt("apprasialid"));
			            dto.setSignDate(rs.getDate("signdate"));
			            dto.setApprasialid(rs.getInt("apprasialid"));
			            dto.setAppraseridentify(rs.getInt("appraseridentify"));
			            dto.setEvaluateName(rs.getString("name"));
			            dto.setEduid(rs.getString("edu_id"));
			            dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
			            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
			            return dto;
					}
				});
					return Appraisal;
			}catch(Exception e){
				logger.error("selectSelfAppraiseXie(Integer,String,String)",e);
				throw new ManagerException(e);
			}
	}
	
	/**
	 * 保存评价
	 */
	
	public Integer insertSelfAppraisal(AppraisalxieDto appraisal,
			String termId, Integer discode, Integer cmis30id) {
		 if(appraisal==null){
			 return null;
		  }
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraseridentify",appraisal.getAppraseridentify());
			params.put("appraisaltypeid",appraisal.getAppraisaltypeid());
			params.put("apprasial",appraisal.getApprasial());
			params.put("studentid",appraisal.getStudentid());
			params.put("appraser",appraisal.getAppraser());
			params.put("appraiserrid",appraisal.getAppraiserridSting());
			params.put("signDate",appraisal.getSignDate());
			params.put("semesterid",termId);
			params.put("discode",discode);
			params.put("cmis30id",cmis30id);
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.insertSelfAppraisal.query");
			 //param  为参数
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_a_apprasial.CURRVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			Integer pid=Integer.valueOf(str);
			return pid;
		}catch(Exception e){
		logger.error("insertSelfAppraisal(Appraisal)",e);
		throw new ManagerException(e);
	
	}
		
	}
	
	   /**
	    * 修改评价
	    */
	public void updataAppraisal(AppraisalxieDto appraisal) {
		  if(appraisal==null){
			 return ;
		  }
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			
			params.put("apprasial",appraisal.getApprasial());
			params.put("apprasialid",appraisal.getApprasialid());
			params.put("signDate",appraisal.getSignDate());
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.updataAppraisal.update");
			 //param  为参数
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
	}catch(Exception e){
		logger.error("updataAppraisal(Appraisal)",e);
	}
	}
	
	public void deleApprasial(Integer apprasialid) {
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("apprasialid",apprasialid);
			ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.deleApprasial.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
	}catch(Exception e){
		logger.error("deleApprasial(Integer)",e);
		throw new ManagerException(e);
	} 
	
	}

		/**
		 * 全班同学
		 */
		public List<StudentxieDto> findClassStudent(String classid) {
			if(classid==null){
				return null;
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("classid",classid);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"findClassStudent(Student student)");
				List<StudentxieDto > classStudent =this.findList("findClassStudent(Student student)", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						StudentxieDto dto=new StudentxieDto();
						dto.setStudentid(rs.getInt("studentid"));
							dto.setClassid(rs.getInt("classid"));
							dto.setGradeid(rs.getInt("gradeid"));
							dto.setLevelid(rs.getInt("levelid"));
							dto.setCampusid(rs.getInt("campusid"));
							dto.setName(rs.getString("name"));
							dto.setClassname(rs.getString("classsname"));
							dto.setGradename(rs.getString("gradename"));
							dto.setLevelname(rs.getString("levelname"));
							dto.setCampusname(rs.getString("gname"));
							dto.setCmis30id(rs.getInt("cmis30id"));
							dto.setSchoolname(rs.getString("schoolname"));
							return dto;
					}
				});
				if(classStudent!=null&&classStudent.size()>0)
					return classStudent;
			}catch(Exception e){
				logger.error("queryUserByUserName(String)",e);
				throw new ManagerException(e);
			}
			return null;
		}
		
		
	    //查询某个同学的所有评价
		public List<AppraisalxieDto> allAppraise(String termId,String studentid,
				Integer evaluatedPersonID, Integer cmis30id, Integer discode) {
			if(studentid==null||evaluatedPersonID==null){
				return null;
			}
			  Map<String,Object> queryMap = new HashMap<String,Object>();
			    queryMap.put("semesterid", termId); 
			    queryMap.put("studentid",evaluatedPersonID);
				queryMap.put("appraiserrid",studentid);    
				queryMap.put("cmis30id",cmis30id );
				queryMap.put("discode",discode );
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.allAppraise.query");
					//param  为参数
					List<AppraisalxieDto> Appraisal=this.findList("PlayAndHealthExtImpl.allAppraise.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalxieDto dto=new AppraisalxieDto();
				            dto.setStudentid(rs.getInt("studentid"));
				            dto.setAppraser(rs.getString("appraser"));
				            dto.setApprasial(rs.getString("apprasial"));  
				            dto.setApprasialid(rs.getInt("apprasialid"));
				            dto.setSignDate(rs.getDate("signdate"));
				            dto.setEvaluateName(rs.getString("name"));
				            dto.setAppraisaltypeid(rs.getInt("appraisaltypeid"));
				            dto.setAppraseridentify(rs.getInt("appraseridentify"));
				            return dto;
						}
					});
						return Appraisal;
				}catch(Exception e){
					logger.error("allAppraise(Integer,String,Integer,Integer)",e);
					throw new ManagerException(e);
				}
			
		}
	
		public String findStundentTermId(Integer classid) {
			
			if(classid==null){
				return null;
			}
			  Map<String,Object> queryMap = new HashMap<String,Object>();
				queryMap.put("classid",classid);
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.findStundentTermId.query");
					//param  为参数
					List<AppraisalxieDto> Appraisal=this.findList("PlayAndHealthExtImpl.findStundentTermId.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalxieDto dto=new AppraisalxieDto();
				            dto.setSemesterid(rs.getInt("semesterid"));
				            return dto;
						}
					});
					if(Appraisal!=null&&Appraisal.size()>0){
					    String st=Appraisal.get(0).getSemesterid().toString();
						return st;
					}
				}catch(Exception e){
					logger.error("allAppraise(Integer,String,Integer,Integer)",e);
					throw new ManagerException(e);
				}
				return null;
		}
		public List<SchoolTreeDto> getStudentInfoAll(String classid,Integer studentid,Integer cmis30id, Integer discode) {
			if(classid==null){
				return null;
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("classid",classid);
			params.put("studentid",studentid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			 
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.getStudentInfoAll.query");
				List<SchoolTreeDto> classStudent =this.findList("PlayAndHealthExtImpl.getStudentInfoAll.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						SchoolTreeDto dto=new SchoolTreeDto();
							dto.setId(rs.getString("studentid"));
							dto.setText(rs.getString("name"));
							dto.setEdu_id(rs.getString("edu_id"));
							return dto;
					}
				});
				if(classStudent!=null&&classStudent.size()>0)
					return classStudent;
			}catch(Exception e){
				logger.error("queryUserByUserName(String)",e);
				throw new ManagerException(e);
			}
			return null;
		}
		
		public void updataTime(Integer apprasialid, Date da) {
			if(apprasialid==null||da==null){
				 return ;
			  }
			try {
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("apprasialid",apprasialid);
				params.put("signDate",da);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.updataTime.update");
				 //param  为参数
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
		}catch(Exception e){
			logger.error("updataTime(Appraisal)",e);
		}
			
		}
		public List<AppraisalxieDto> findGzReturnTree(String evaluateType,
				String termId, Integer studentid, Integer evaluatedPersonID,
				Integer cmis30id, Integer discode) {
			if(studentid==null||evaluatedPersonID==null){
				return null;
			}
			  Map<String,Object> queryMap = new HashMap<String,Object>();
			    queryMap.put("semesterid", termId); 
			    queryMap.put("studentid",evaluatedPersonID);
				queryMap.put("appraiserrid",studentid);    
				queryMap.put("cmis30id",cmis30id );
				queryMap.put("discode",discode );
				queryMap.put("appraisaltypeid",evaluateType);
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.findGzReturnTree.query");
					//param  为参数
					List<AppraisalxieDto> Appraisal=this.findList("PlayAndHealthExtImpl.findGzReturnTree.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalxieDto dto=new AppraisalxieDto();
				            dto.setStudentid(rs.getInt("studentid"));
				            dto.setAppraser(rs.getString("appraser"));
				            dto.setApprasial(rs.getString("apprasial"));  
				            dto.setApprasialid(rs.getInt("apprasialid"));
				            dto.setSignDate(rs.getDate("signdate"));
				            dto.setEvaluateName(rs.getString("name"));
				            dto.setAppraisaltypeid(rs.getInt("appraisaltypeid"));
				           dto.setAppraseridentify(rs.getInt("appraseridentify"));
				            return dto;
						}
					});
						return Appraisal;
				}catch(Exception e){
					logger.error("allAppraise(Integer,String,Integer,Integer)",e);
					throw new ManagerException(e);
				}
		}
		
		
		@DataSource("read")
		public SpeekDto findSpeek(Integer apprasialid, Integer discode,
				Integer cmis30id) {
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("apprasialid",apprasialid);
			params.put("discode",discode);
			params.put("cmis30id",cmis30id);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.findspeek.query");
				List<SpeekDto> student =this.findList("PlayAndHealthExtImpl.findspeek.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						SpeekDto dto=new SpeekDto();
						dto.setUsername(rs.getString("appraser"));
						dto.setStuEduid(rs.getString("edu_id"));
					    dto.setStudentName(rs.getString("name"));
					    dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
							return dto;
					}
				});
				if(student!=null&&student.size()>0)
					return student.get(0);
			}catch(Exception e){
				logger.error("findstudent(Integer)",e);
				throw new ManagerException(e);
			}
			return null;
		}
		
		
		@DataSource("read")
		public List<AapprasialCacheXieDto> findEVerApperasial(String termIdString, String apperEduId,
				Integer evaluatedPersonID, Integer cmis30id, Integer discode,
				String appraisaltypeid) {
			if(apperEduId==null||evaluatedPersonID==null){
				return null;
			}
			  Map<String,Object> queryMap = new HashMap<String,Object>();
			    queryMap.put("semesterid", termIdString); 
			    queryMap.put("studentid",evaluatedPersonID);
				queryMap.put("appraiserrid",apperEduId);    
				queryMap.put("cmis30id",cmis30id );
				queryMap.put("discode",discode );
				queryMap.put("appraisaltypeid",appraisaltypeid);
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.EereallAppraise.query");
					//param  为参数
					List<AapprasialCacheXieDto> Appraisal=this.findList("PlayAndHealthExtImpl.EereallAppraise.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AapprasialCacheXieDto dto=new AapprasialCacheXieDto();
				            dto.setApprasialid(rs.getString("apprasialid"));
							dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
							dto.setAppraiserrid(rs.getString("appraiserrid"));
							dto.setSemesterid(rs.getString("semesterid"));
							dto.setStudentid(rs.getString("studentid"));
							dto.setAppraseridentify(rs.getString("appraseridentify"));
							dto.setAppraser(rs.getString("appraser"));
							dto.setApprasial(rs.getString("apprasial"));
							dto.setSigndate(rs.getDate("signdate"));
							dto.setEdu_id(rs.getString("edu_id"));
							dto.setDiscode(rs.getString("discode"));
							dto.setCmis30id(rs.getString("cmis30id"));
							dto.setName(rs.getString("name"));
							dto.setEdittime(rs.getString("edittime"));
				            return dto;
						}
					});
						return Appraisal;
				}catch(Exception e){
					logger.error("allAppraise(Integer,String,Integer,Integer)",e);
					throw new ManagerException(e);
				}
			
		}

		public void addCacheApper(Map<String, Object> params, String edu_id,
				String termIdString, String evaluateType, String string,
				String apperEduId, String newId, AapprasialCacheDto acd) {
			 
			try {
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.findhuaninsertSelfAppraisal.query");
				  latestEvaluationRecordExt.addRecodeInCacheByProKey(edu_id, termIdString, evaluateType, "2", apperEduId, newId,acd, sqlElement,acd.getSigndate());
			
			} catch (Exception e) {
				logger.error("addCacheApper(Map params, edu_id,termIdString,  evaluateType,  string, apperEduId,  newId, Aacd)",e);
				throw new ManagerException(e);
			}
			
			
			
			
		}

		public void delectCacheGzApper(String stuEduid, String termIdString,
				String evaluateType, String string, String apperEduId,
				Integer apprasialid) {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("apprasialid",apprasialid);
			ISqlElement sqlElement;
			try {
				sqlElement = this.processSql(params,"PlayAndHealthExtImpl.deleApprasial.update");
				 String apprasialidString=String.valueOf(apprasialid); 
					latestEvaluationRecordExt.delRecodeInCacheByProKey(stuEduid,termIdString,
							 evaluateType,string, apperEduId,
							 apprasialidString,sqlElement,AapprasialCacheDto.class);
			
			
			} catch (Exception e) {
				logger.error("delectCacheGzApper(String stuEduid, String termIdString,String evaluateType, String string, String apperEduId,Integer apprasialid)",e);
				throw new ManagerException(e);
			}

		
			
		}
		//更新缓存数据
		public void updataCachTime(String edu_id, String termIdString,
				String evaluateType, String string, String apperEduId,
				Integer apprasialid, AapprasialCacheDto aapprasialCacheDto,
				Date signDate) {
		  try {
			
			    Map<String,Object> params=new HashMap<String,Object>();
				params.put("apprasialid",apprasialid);
			    params.put("signDate",signDate);
			  
			    //调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.updataTime.update1");
				String apid=string.valueOf(apprasialid);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id,  termIdString, evaluateType,string,  apperEduId,apid, aapprasialCacheDto,sqlElement,null);
			  
		} catch (Exception e) {
			logger.error("updataCachTime(String edu_id, String termIdStrin String evaluateType, String string, String apperEduId,Integer apprasialid, AapprasialCacheDto aapprasialCacheDto,String signDate)",e);
			throw new ManagerException(e);
		}
			
		}
		@Override
		public void updateCacheApper(String edu_id, String termIdString,String evaluateType, String string, String apperEduId,
				Integer apprasialid, AapprasialCacheDto aapprasialCacheDto,
				String apprasial, Date signDate) {
			
			 /**
				 * 更新指定学期，指定栏目，指定评价人，指定记录评价信息
				 * @param edu_id 被评价学生教育id
				 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
				 * @param two_part_id 二级栏目标识号   evaluateType
				 * @param appraseridentify 评价人身份   2
				 * @param appraiserrid 评价人标识号   apperEduId
				 * @param prokey 主键key   
				 * @param sqlDemo 含有Sql语句及Sql参数值得对象
				 * @param t 需要更新的数据
				 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid,prokey,t,sqlDemo任何一个为Null都将抛出异常
				 * 		        或者操作失败时，也会此抛异常
				 */
			//public <T> void updateRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t,ISqlElement sqlDemo);
				try {
					Map<String,Object> params=new HashMap<String,Object>();
					
					params.put("apprasial",apprasial);
					params.put("apprasialid",apprasialid);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					params.put("signDate",signDate==null?null:sdf.format(signDate));
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.updataAppraisal.update1");
					 String pid=String.valueOf(apprasialid);
		
					//public <T> void updateRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t,ISqlElement sqlDemo);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id, termIdString, evaluateType, "2", apperEduId, pid, aapprasialCacheDto, sqlElement,signDate==null?null:sdf.format(signDate));
				} catch (Exception e) {
					
					logger.error("(String edu_id, String termIdString,String evaluateType, String string, String apperEduId,Integer apprasialid, AapprasialCacheDto aapprasialCacheDto,String apprasial, Date signDate)",e);
					throw new ManagerException(e);
					
				}
			
		}
	
		public List<SchoolTreeDto> getStudentInfoAll(String classid,
				String studentid, String cmis30id, String discode) {
			if(classid==null){
				return null;
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("classid",classid);
			params.put("studentid",studentid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			 
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.getStudentInfoAll.query");
			
				List<SchoolTreeDto> classStudent =this.findList("PlayAndHealthExtImpl.getStudentInfoAll.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						SchoolTreeDto dto=new SchoolTreeDto();
							dto.setId(rs.getString("studentid"));
							dto.setText(rs.getString("name"));
							dto.setEdu_id(rs.getString("edu_id"));
							return dto;
					}
				});
				if(classStudent!=null&&classStudent.size()>0)
					return classStudent;
			}catch(Exception e){
				logger.error("queryUserByUserName(String)",e);
				throw new ManagerException(e);
			}
			return null;
		}

		
		
		public List<AppraisalxieStirngDto> selectSelfAppraiseXie(String classid,
				String evaluateType, String termIdString, String apperEduId,
				String discode, String cmis30id) {
			if(classid==null||evaluateType==null||termIdString==null){
				return null;
			}
			  Map<String,Object> queryMap = new HashMap<String,Object>();
				queryMap.put("classid",classid);
				queryMap.put("semesterid", termIdString);    
				queryMap.put("appraisaltypeid", evaluateType);
				queryMap.put("cmis30id", cmis30id);
				queryMap.put("discode", discode);
				queryMap.put("appraiserrid",apperEduId);
				
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.selectSelfAppraiseXieShu.query");
					//param  为参数
					List<AppraisalxieStirngDto> Appraisal=this.findList("PlayAndHealthExtImpl.selectSelfAppraiseXieShu.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							AppraisalxieStirngDto dto=new AppraisalxieStirngDto();
				            dto.setStudentid(rs.getString("studentid"));
				            dto.setEvaluateName(rs.getString("name"));
				            dto.setEduid(rs.getString("edu_id"));
				            dto.setSumnaber(rs.getString("shu"));
				            dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
				            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
				            return dto;
						}
					});
						return Appraisal;
				}catch(Exception e){
					logger.error("selectSelfAppraiseXie(Integer,String,String)",e);
					throw new ManagerException(e);
				}
		}

		
		
		//查询指定栏目指定评价人指定学期
		public List<AppraisalxieStirngDto> findApprasial(String evaluateType,
				String termId, String eduId, String apperEduId, String discode,
				String cmis30id) {
			
		  if(evaluateType==null||termId==null||eduId==null||apperEduId==null){
				return null;
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("appraisaltypeid",evaluateType);
			params.put("semesterid",termId);
			params.put("edu_id",eduId);
			params.put("appraiserrid",apperEduId);
			 
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.getStudentAppervere.query");
			
				List<AppraisalxieStirngDto> classStudent =this.findList("PlayAndHealthExtImpl.getStudentAppervere.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraisalxieStirngDto dto=new AppraisalxieStirngDto();
						 dto.setApprasialid(rs.getString("apprasialid"));
						 dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
						 dto.setAppraiserrid(rs.getString("appraiserrid"));
						 dto.setEduid(rs.getString("edu_id"));
						 dto.setSemesterid(rs.getString("semesterid"));
						 dto.setAppraseridentify(rs.getString("appraseridentify"));
						 dto.setStudentid(rs.getString("studentid"));
						 dto.setAppraser(rs.getString("appraser"));
						 dto.setApprasial(rs.getString("apprasial"));
						 dto.setSignDate(rs.getDate("signdate"));
							return dto;
					}
				});
				
					return classStudent;
			}catch(Exception e){
				logger.error("queryUserByUserName(String)",e);
				throw new ManagerException(e);
			}
		
		}


		
		
		public void updataNewAppraisal(String apprasialid, String apprasial,
				String signDate) {
			  
			try {
				Map<String,Object> params=new HashMap<String,Object>();
				
				params.put("apprasial",apprasial);
				params.put("apprasialid",apprasialid);
				params.put("signDate",signDate);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.updataNewAppraisal.update");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
				
		}catch (Exception e) {
			logger.error("updataNewAppraisal(String apprasialid, String apprasial,String signDate)",e);
			throw new ManagerException(e);
		}
		}

		
		
		public String insertSelfAppraisal(AppraisalxieStirngDto appraisal,
				String termId, String discode, String cmis30id,String eduId) {
			
			if(appraisal==null){
				 return null;
			  }
			try {
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("appraseridentify",appraisal.getAppraseridentify());
				params.put("appraisaltypeid",appraisal.getAppraisaltypeid());
				params.put("apprasial",appraisal.getApprasial());
				params.put("studentid",appraisal.getStudentid());
				params.put("appraser",appraisal.getAppraser());
				params.put("appraiserrid",appraisal.getAppraiserrid());
				params.put("signDate",appraisal.getSignDate());
				params.put("semesterid",termId);  
				params.put("discode",discode);
				params.put("cmis30id",cmis30id);
				params.put("edu_id",eduId);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"PlayAndHealthExtImpl.insertSelfAppraisalbian.query");
				//param  为参数
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				List lst = new ArrayList();
				lst = getJdbcTemplate().queryForList("select s_a_apprasial.CURRVAL from dual");
				String str = lst.get(0).toString().split("=")[1].split("}")[0];
				return str;
			}catch(Exception e){
			logger.error("insertSelfAppraisal(Appraisal)",e);
			throw new ManagerException(e);
		
		}
		}
	
}
