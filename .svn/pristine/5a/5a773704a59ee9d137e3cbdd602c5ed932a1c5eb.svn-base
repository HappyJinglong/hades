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
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolNameDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.util.DataSource;




public class CzPlayAndHealthExtImpl extends JdbcRootManager implements ICzPlayAndHealthExt{
	private ConstantBean constantBean;
	private  ILatestEvaluationRecordExt latestEvaluationRecordExt;
	 
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}
	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	
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
						dto.setClassid(rs.getInt("classid"));
						dto.setName(rs.getString("name"));
						dto.setTermid(rs.getInt("termid"));
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
	/**
	 * 查询全班某项的评价
	 */
	@DataSource("read")
	public List<PartInfoXieDto> selectSelfAppraiseXie(Integer studentid,
			Integer classid, Integer termid, String userid,
			String two_par_id, Integer discode, Integer cmis30id) {
		if(classid==null||termid==null||two_par_id==null){
			return null;
		}
		  Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("studentid",studentid);
			queryMap.put("classid",classid);
			queryMap.put("termid", termid);    
			queryMap.put("userid", userid);
			queryMap.put("two_par_id", two_par_id);
			queryMap.put("cmis30id", cmis30id);
			queryMap.put("discode", discode);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"CzPlayAndHealthExtImpl.selectSelfAppraiseXie.query");
				//param  为参数
				List<PartInfoXieDto> Appraisal=this.findList("CzPlayAndHealthExtImpl.selectSelfAppraiseXie.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
			            dto.setPart_id(rs.getString("part_id"));
						dto.setStudentid(rs.getString("studentid"));
						dto.setTermid(rs.getString("termid"));
						dto.setPart_info(rs.getString("part_info"));
						dto.setSigner_name(rs.getString("signer_name"));
						dto.setTwo_part_id(rs.getString("two_part_id"));
			            dto.setCreateDate(rs.getDate("createDate"));
			            dto.setPname(rs.getString("name"));
						dto.setWrite_man(rs.getString("write_man"));
						dto.setEduid(rs.getString("edu_id"));
						dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
			            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
			            return dto;
					}
				});
					return Appraisal;
			}catch(Exception e){
				logger.error("CzPlayAndHealthExtImpl(Integer,String,String)",e);
				throw new ManagerException(e);
			}
	}
	
	
	
	

	public void deleApprasial(String part_id,Integer discode, Integer cmis30id) {
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",part_id);
			params.put("discode",discode);
			params.put("cmis30id",cmis30id);
			ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.CZdeleApprasial.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
	}catch(Exception e){
		logger.error("deleApprasial(Integer)",e);
		throw new ManagerException(e);
	} 
	
	}

		/**
		 * 全班同学
		 */
		public List<StudentxieDto> findClassStudent(StudentxieDto student) {
			if(student==null){
				return null;
			}
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("classid",student.getClassid());
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
	
		/**
		 * 保存评价
		 */
		
		public String czinsertSelfAppraisal(PartInfoXieDto appr) {
			 if(appr==null){
				 return null;
			  }
		try {
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("studentid",appr.getStudentid());
					params.put("termid",appr.getTermid());
					params.put("part_info",appr.getPart_info());
					params.put("write_man",appr.getWrite_man());
					params.put("signer_name",appr.getSigner_name());
					params.put("two_part_id",appr.getTwo_part_id());
					params.put("CreateDate",appr.getCreateDate());
					params.put("cmis30id",appr.getCmis30id());
					params.put("discode",appr.getDiscode());
					params.put("userid",appr.getUserid());
					
					/*params.put("part_id",appr.getPart_id());*/
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czinsertSelfAppraisal.query");
				  
					
					//param  为参数
				   this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
				    List lst = new ArrayList();
					lst = getJdbcTemplate().queryForList("select s_partinfo.CURRVAL from dual");
					String str = lst.get(0).toString().split("=")[1].split("}")[0];
					return str;
		}catch(Exception e){
				logger.error("czinsertSelfAppraisal(Appraisal)",e);
				throw new ManagerException(e);
			
			}
			 
		}

		
       //修改评价
		public void czupdataAppraisal(PartInfoXieDto appr) {
			  if(appr==null){
					 return ;
				  }
				try {
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("part_id",appr.getPart_id());
					params.put("part_info",appr.getPart_info());
					params.put("CreateDate",appr.getCreateDate());
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czupdataAppraisal.update");
					 //param  为参数
					this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
			}catch(Exception e){
				logger.error("czupdataAppraisal(Appraisal)",e);
			}
			  
			
		}
		
		
		
		
		
		
		
		
		//查询某个同学的所有评价
		public List<PartInfoXieDto> czAllAppraise(String userid,
				Integer termid, String evaluatedPersonID, Integer cmis30id,
				Integer discode) {
			    Map<String,Object> queryMap = new HashMap<String,Object>();
			    queryMap.put("studentid",evaluatedPersonID);
				queryMap.put("termid", termid);    
				queryMap.put("userid", userid);
				queryMap.put("cmis30id", cmis30id);
				queryMap.put("discode", discode);
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.czAllAppraise.query");
					//param  为参数
					
					List<PartInfoXieDto> partInfoXieDto=this.findList("PlayAndHealthExtImpl.czAllAppraise.query",queryMap, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							PartInfoXieDto dto=new PartInfoXieDto();
				            dto.setPart_id(rs.getString("part_id"));
							dto.setStudentid(rs.getString("studentid"));
							dto.setTermid(rs.getString("termid"));
							dto.setPart_info(rs.getString("part_info"));
							dto.setSigner_name(rs.getString("signer_name"));
							dto.setTwo_part_id(rs.getString("two_part_id"));
				            dto.setCreateDate(rs.getDate("createDate"));
							dto.setWrite_man(rs.getString("write_man"));
							return dto;
						}
					});
						return partInfoXieDto;
				}catch(Exception e){
					logger.error("allAppraise(Integer,String,Integer,Integer)",e);
					throw new ManagerException(e);
				}
		}
		
		public List<PartInfoXieDto> czfindreturn(String signer_name,
				String termid, String evaluatedPersonID, Integer cmis30id,
				Integer discode, String two_part_id) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
		    queryMap.put("studentid",evaluatedPersonID);
			queryMap.put("termid", termid);    
			queryMap.put("signer_name", signer_name);
			queryMap.put("cmis30id", cmis30id);
			queryMap.put("discode", discode);
			queryMap.put("two_part_id", two_part_id);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.czfindreturn.query");
				//param  为参数
				List<PartInfoXieDto> partInfoXieDto=this.findList("PlayAndHealthExtImpl.czfindreturn.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
			            dto.setPart_id(rs.getString("part_id"));
						dto.setStudentid(rs.getString("studentid"));
						dto.setTermid(rs.getString("termid"));
						dto.setPart_info(rs.getString("part_info"));
						dto.setSigner_name(rs.getString("signer_name"));
						dto.setTwo_part_id(rs.getString("two_part_id"));
			            dto.setCreateDate(rs.getDate("createDate"));
						dto.setWrite_man(rs.getString("write_man"));
						return dto;
					}
				});
					return partInfoXieDto;
			}catch(Exception e){
				logger.error("czfindreturn(Integer,String,Integer,Integer)",e);
				throw new ManagerException(e);
			}
			
		}
		public void czupdataTime(String part_id, Date da,Integer discode,
				Integer cmis30id) {
			if(part_id==null||da==null){
				 return ;
			  }
			try {
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("part_id",part_id);
				params.put("CreateDate",da);
				params.put("discode",discode);
				params.put("cmis30id",cmis30id);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czupdataTime.update");
				 //param  为参数
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());	
			
		}catch(Exception e){
			logger.error("czupdataTime(Integer,Integer)",e);
			throw new ManagerException(e);
		}
	}
		public SpeekDto czfindSpeek(String part_id, Integer discode,
				Integer cmis30id) {
			
			Map<String,Object> queryMap = new HashMap<String,Object>();
		    queryMap.put("part_id",part_id);
			queryMap.put("discode", discode);    
			queryMap.put("cmis30id", cmis30id);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"PlayAndHealthExtImpl.czfindspeek.query");
				List<SpeekDto> student =this.findList("PlayAndHealthExtImpl.czfindspeek.query", queryMap, new RowMapper() {
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

		public void addCachApper(String edu_id, String termid,
				String two_part_id, String string, String apperEduId,
				String newId, PartInfoCacheDto cacheDto, Date da) {
			try {
				
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("studentid",cacheDto.getStudentid());
				params.put("termid",cacheDto.getTermid());
				params.put("part_info",cacheDto.getPart_info());
				params.put("write_man",cacheDto.getWrite_man());
				params.put("signer_name",cacheDto.getSigner_name());
				params.put("two_part_id",cacheDto.getTwo_part_id());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				params.put("CreateDate",da==null?null:sdf.format(da));
				params.put("cmis30id",cacheDto.getCmis30id());
				params.put("discode",cacheDto.getDiscode());
				params.put("userid",cacheDto.getUserid());
				params.put("part_id",newId);
				
				ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czinsertSelfAppraisalCade.query1");
				latestEvaluationRecordExt.addRecodeInCacheByProKey( edu_id,  termid,    two_part_id,  string,  apperEduId,  newId, cacheDto,sqlElement,da==null?null:sdf.format(da));
		
				
				
			} catch (Exception e) {
				logger.error("addCachApper(String edu_id, String termid,String two_part_id, String string, String apperEduId,String newId, PartInfoCacheDto cacheDto, Date da) ",e);
				throw new ManagerException(e);
			}
			
			
		}
		@Override
		public void deleApprasialCade(String edu_id, String termidString,
				String two_part_id, String string, String apperEduId,
				String part_id,Integer discode,
				Integer cmis30id) {
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",part_id);
			params.put("discode",discode);
			params.put("cmis30id",cmis30id);
			ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.CZdeleApprasial.update");
			latestEvaluationRecordExt.delRecodeInCacheByProKey( edu_id,termidString,two_part_id,string, apperEduId,part_id,sqlElement,PartInfoCacheDto.class);
		} catch (Exception e) {
			logger.error(" deleApprasialCade(String edu_id, String termidString,String two_part_id, String string, String apperEduId,String part_id,Integer discode,Integer cmis30id) ",e);
			throw new ManagerException(e);
		}
			
		}
		@Override
		public void czupdataTimeCade(String edu_id, String termidString,
				String two_part_id, String string, String apperEduId,
				String part_id, PartInfoCacheDto cacheDto, Date da,
				Integer discode, Integer cmis30id) {
			try {
				
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("part_id",part_id);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				params.put("CreateDate",da==null?null:sdf.format(da));
				params.put("discode",discode);
				params.put("cmis30id",cmis30id);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czupdataTime.update1");
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id, termidString, two_part_id,  string, apperEduId, part_id,  cacheDto,sqlElement,da==null?null:sdf.format(da));
			} catch (Exception e) {
				logger.error("czupdataTimeCade(String edu_id, String termidString,String two_part_id, String string, String apperEduId,String part_id, PartInfoCacheDto cacheDto, Date da,Integer discode, Integer cmis30id)",e);
				throw new ManagerException(e);
			}
			
		}
		@Override
		public void czupdataAppraisalCade(String edu_id, String termidString,
				String two_part_id, String string, String apperEduId,
				String part_id, PartInfoCacheDto cacheDto, PartInfoXieDto appr) {
			try {
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("part_id",appr.getPart_id());
					params.put("part_info",appr.getPart_info());
					params.put("CreateDate",appr.getCreateDate());
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(params,"CzPlayAndHealthExtImpl.czupdataAppraisal.update");
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(edu_id, termidString, two_part_id,  string, apperEduId, part_id,  cacheDto,sqlElement,null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		@Override
		public List<PartInfoXieDto> selectClassStudent(Integer studentid,
				Integer classid, Integer termId, String apperEduId,String two_part_id, Integer discode, Integer cmis30id) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("studentid",studentid);
			queryMap.put("classid",classid);
			queryMap.put("termid", termId);    
			queryMap.put("userid", apperEduId);
			queryMap.put("two_part_id", two_part_id);
			queryMap.put("discode", discode);
			queryMap.put("cmis30id", cmis30id);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"CzPlayAndHealthExtImpl.selectClassStuder.query");
				//param  为参数
				List<PartInfoXieDto> Appraisal=this.findList("CzPlayAndHealthExtImpl.selectClassStuder.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
						dto.setStudentid(rs.getString("studentid"));
			            dto.setPname(rs.getString("name"));
			            dto.setContnumber(rs.getString("shu"));
						dto.setEduid(rs.getString("edu_id"));
						//photo
						dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
			            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
			            return dto;
					}
				});
					return Appraisal;
			}catch(Exception e){
				logger.error("CzPlayAndHealthExtImpl(Integer,String,String)",e);
				throw new ManagerException(e);
			}
			
			
			
		}
		@Override
		public List<PartInfoXieDto> selectClassStudentCade(Integer studentid,
				Integer classid, Integer termId, String apperEduId,
				String two_part_id, Integer discode, Integer cmis30id) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("studentid",studentid);
			queryMap.put("classid",classid);
			queryMap.put("termid", termId);    
			queryMap.put("userid", apperEduId);
			queryMap.put("two_part_id", two_part_id);
			queryMap.put("discode", discode);
			queryMap.put("cmis30id", cmis30id);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"CzPlayAndHealthExtImpl.selectClassStuderCade.query");
				//param  为参数
				List<PartInfoXieDto> Appraisal=this.findList("CzPlayAndHealthExtImpl.selectClassStuderCade.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
						dto.setStudentid(rs.getString("studentid"));
			            dto.setPname(rs.getString("name"));
						dto.setEduid(rs.getString("edu_id"));
						//photo
						dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
			            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
			            return dto;
					}
				});
					return Appraisal;
			}catch(Exception e){
				logger.error("CzPlayAndHealthExtImpl(Integer,String,String)",e);
				throw new ManagerException(e);
			}
			
			
		}
		@Override
		public String findGreedLenth(String cmis30Id, String levelcode) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("campuseid", cmis30Id);
			queryMap.put("levelid", levelcode);
			String ss=null;
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"CzPlayAndHealthExtImpl.findGreedLenth.query");
				//param  为参数
				List<PartInfoXieDto> Appraisal=this.findList("CzPlayAndHealthExtImpl.findGreedLenth.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
						dto.setStudentid(rs.getString("length"));
			            return dto;
					}
				});
				
				if(null!=Appraisal){
					ss=Appraisal.get(0).getStudentid();
				}
					return ss;
			}catch(Exception e){
				logger.error("CzPlayAndHealthExtImpl(Integer,String,String)",e);
				throw new ManagerException(e);
			}
		
		}
		@Override
		public String findclassid(String cmis30Id, String discode, String string) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("discode", discode);
			queryMap.put("cmis30id", cmis30Id);
			queryMap.put("edu_id", string);
		
			String ss=null;
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(queryMap,"CzPlayAndHealthExtImpl.findclassLenth.query");
				//param  为参数
				List<PartInfoXieDto> Appraisal=this.findList("CzPlayAndHealthExtImpl.findclassLenth.query",queryMap, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						PartInfoXieDto dto=new PartInfoXieDto();
						dto.setStudentid(rs.getString("classid"));
			            return dto;
					}
				});
				
				if(null!=Appraisal){
					ss=Appraisal.get(0).getStudentid();
				}
					return ss;
			}catch(Exception e){
				logger.error("CzPlayAndHealthExtImpl(Integer,String,String)",e);
				throw new ManagerException(e);
			}
		}
		@Override
		public Map<String,String> findth(String cmis30Id, String discode, String levelid,
				String termId) {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("cmis30id",cmis30Id);
			params.put("discode",discode);
			params.put("levelid",levelid);
			params.put("termid",termId);
			try{
				final Map<String,String> datas=new HashMap<String,String>();
				this.findList("StudentAppDetailExtImpl.queryYDYJKBaseData.query", params,new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						datas.put(rs.getString("gradeyear"),rs.getString("couts"));
						return null;
					}
				});
			
				return datas;
			}catch(Exception e){
				logger.error("queryYDYJKBaseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,String)",e);
			}
			return null;
		}
		@Override
		public List<SchoolNameDto> findShool(String schoolName, String discode,String Levelcode) {
			Map<String,Object> params=new HashMap<String,Object>();
			
			params.put("discode",discode);
			params.put("schoolName",schoolName);
			params.put("levelCode",Levelcode);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				//param  为参数distinct bba.cmis30id,bba.schoolname
				try {
					ISqlElement sqlElement=this.processSql(params,"AreaStatExtImpl.findAllSchoolid.query");
					List<SchoolNameDto>  dd=this.findList("AreaStatExtImpl.findAllSchoolid.query",params, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							SchoolNameDto dto=new SchoolNameDto();
							dto.setCimi30(rs.getString("cmis30id"));
					        dto.setShoolname(rs.getString("schoolname"));
							return dto;
						}
					});
					return dd;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
		
		}

}
