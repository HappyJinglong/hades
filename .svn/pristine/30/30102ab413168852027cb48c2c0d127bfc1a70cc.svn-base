package com.flyrish.hades.service.ext.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.loader.hql.QueryLoader;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.LearnprocessAppraisaDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.util.DataSource;
import com.flyrish.hades.util.NoServiceUtil;

public class MasterAppriseExt extends JdbcRootManager implements IMasterAppriseExt{
    private ConstantBean constantBean;
	private IBaseInforManagerExt baseInforManagerExt;
	
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}
	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public CampusDto getCampusByCampusId(Map<String,Object> params) {
		try {
			//校区信息
			List<CampusDto>dtos = this.findList("MasterAppriseExt.getCampusByCampusId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					CampusDto dto = new CampusDto();
					dto.setCampusId(rs.getString("campusid"));
					dto.setCampusName(rs.getString("name"));
					return dto;
				}
			});
			//返回查询校区信息
			if(null!=dtos && dtos.size()>0){
				return dtos.get(0);
			}
		} catch (Exception e) {
			logger.error("getCampusByCampusId(Map<String,Object>)", e);
		}
		return null;
	}

	public List<SchoolTreeDto> getEduysForMenu(Map<String,Object> params) {
		try {
			//学段信息
			return this.findList("MasterAppriseExt.getEduysForMenu.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setId(rs.getString("levelid")+"_1");//代表1级ID，为1的话 会调用查年级的方法
					dto.setText(rs.getString("levelname"));
					dto.setCompusId(rs.getString("campusid"));//校区id
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getEduysForMenu(Map<String,Object>)", e);
		}
		return null;
	}

	public List<SchoolTreeDto> getGradesInfo(Map<String, Object> params) {
		try {
			//年级信息
			return this.findList("MasterAppriseExt.getGradesInfo.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setId(rs.getString("gradeid")+"_2");//代表2级ID，为2的话 会调用查班级的方法
					dto.setText(rs.getString("gradename"));
					dto.setCompusId(rs.getString("campusid"));//校区id
					dto.setEdusyId(rs.getString("levelid"));//学段id
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getGradesInfo(Map<String,Object>)", e);
		}
		return null;
	}

	public List<SchoolTreeDto> getClasssInfo(Map<String, Object> params) {
		try {
			//年级信息
			return this.findList("MasterAppriseExt.getClasssInfo.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setId(rs.getString("classid")+"_3");//代表3级ID，为3的话 会调用查考生的方法
					dto.setText(rs.getString("classsname"));
					dto.setCompusId(rs.getString("campusid"));//校区id
					dto.setEdusyId(rs.getString("levelid"));//学段id
					dto.setGradId(rs.getString("gradeId"));//年级id
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getStudentInfo.query");
			return this.findList("MasterAppriseExt.getStudentInfo.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setId(rs.getString("studentid"));
					dto.setText(rs.getString("name"));
					dto.setKey(NoServiceUtil.md53(rs.getString("studentid")));
					dto.setEdusyId(rs.getString("edu_id"));
					dto.setName(rs.getString("photo"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<SchoolTreeDto> getStudentInfoXie(Map<String, Object> params) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getStudentInfoXie.query");
			return this.findList("MasterAppriseExt.getStudentInfoXie.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setText(rs.getString("name"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<AppraisalDto> getAllCommonAppraisalInfos(	Map<String, Object> params) {
		try {
			//班主任普通评价信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getAllCommonAppraisalInfos.query");
			return this.findList("MasterAppriseExt.getAllCommonAppraisalInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					AppraisalDto dto = new AppraisalDto();
					dto.setApprasialid(rs.getString("apprasialid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid")==null?null:Integer.parseInt(rs.getString("appraisaltypeid")));
					dto.setAppraiserrid(rs.getString("appraiserrid")==null?null:Integer.parseInt(rs.getString("appraiserrid")));
//					dto.setSemesterid(rs.getInt("semesterid"));
					//dto.setAppraseridentify(rs.getString("appraseridentify")==null?null:Integer.parseInt(rs.getString("appraseridentify")));
					dto.setAppraser(rs.getString("appraser"));
					dto.setApprasial(rs.getString("apprasial"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEduid(rs.getString("edu_id"));
					dto.setStudentName(rs.getString("name"));
					dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
		            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getAllCommonAppraisalInfos(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<AppraisalDto> getMasterAppraisalInfos(Map<String, Object> params) {
		try {
			//班主任评语信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getMasterAppraisalInfos.query");
			return this.findList("MasterAppriseExt.getMasterAppraisalInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					AppraisalDto dto = new AppraisalDto();
					dto.setApprasialid(rs.getString("assessid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setStudentName(rs.getString("name"));
					dto.setApprasial(rs.getString("assesscontent"));
				//	dto.setAppraseridentify(rs.getString("appraser")==null?null:Integer.parseInt(rs.getString("appraser")));
					dto.setAppraser(rs.getString("signname"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEduid(rs.getString("edu_id"));
					dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
		            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getMasterAppraisalInfos(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<AppraisalDto> getLearnProcessAppraisalInfos(Map<String, Object> params) {
		try {
			//班主任课程评价信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getLearnProcessAppraisalInfos.query");
			return this.findList("MasterAppriseExt.getLearnProcessAppraisalInfos.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					AppraisalDto dto = new AppraisalDto();
//					rs.getString("appraisalid")==null?null:Integer.parseInt(rs.getString("appraisalid"))
					dto.setApprasialid(rs.getString("appraisalid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setStudentName(rs.getString("name"));
					dto.setApprasial(rs.getString("appraisal"));
					dto.setSubject(rs.getString("subject"));
					dto.setAppraser(rs.getString("sign"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEduid(rs.getString("edu_id"));
					dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
		            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getLearnProcessAppraisalInfos(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<CampusDto> getClassInfos(Map<String, Object> params) {
		try {
			//班主任课程评价信息
			return this.findList("MasterAppriseExt.getClassInfos.query", params, new RowMapper(){
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
					dto.setTeacherName(rs.getString("name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClassInfos(Map<String,Object>)", e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<CampusDto> getTeacherClassInfos(Map<String, Object> params) {
		try {
			String levelCode = (String) params.get("levelcode");
			String processSql = "";
			if("2012002".equals(levelCode)){
				ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getTeacherClassInfos.query");
				processSql = "MasterAppriseExt.getTeacherClassInfos.query";
			}else if("2012003".equals(levelCode)||"2012004".equals(levelCode)){
				ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getGZTeacherClass.query");
				
				processSql = "SchoolCourseExt.getGZTeacherClass.query";
			}
			//班主任课程评价信息
			return this.findList(processSql, params, new RowMapper(){
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
					dto.setTeacherName(rs.getString("name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getTeacherClassInfos(Map<String,Object>)", e);
		}
		return null;
	}

	public String insertMasterApprise(AppraisalDto dto) {
		try {
			String str = "";
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("studentid", dto.getStudentid());
	        params.put("appraser", dto.getAppraseridentify()==null ? dto.getAppraseridentity() : dto.getAppraseridentify());
	        params.put("discode", dto.getDiscode());
	        params.put("cmis30id", dto.getCmis30id());
	        params.put("semesterid", dto.getSemesterid());
	        params.put("assesscontent", dto.getApprasial());
	        params.put("signname", dto.getAppraser()); 
	        params.put("signdate", dto.getSigndate()); 
	        str = baseInforManagerExt.queryProKey("s_assess");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertMasterApprise.insert");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			return str;
		} catch (Exception e) {
			logger.error("insertMasterApprise(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}

	public void updateMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("assesscontent", aDto.getApprasial());
		params.put("signdate", aDto.getSigndate());
		params.put("assessid", aDto.getApprasialid());
		params.put("signname", aDto.getAppraser());
		/*params.put("appraiserrid", aDto.getAppraiserrid());*/
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateMasterApprise.update");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("updateMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public String insertCommonMasterApprise(AppraisalDto dto) {
		try {
			String str = "";
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("studentid", dto.getStudentid());
	        params.put("appraisaltypeid", dto.getAppraisaltypeid());
	        params.put("appraiserrid", dto.getAppraiserrid());
	        params.put("semesterid", dto.getSemesterid());
	        params.put("appraseridentify", dto.getAppraseridentify()==null ? dto.getAppraseridentity() : dto.getAppraseridentify());
	        params.put("appraser", dto.getAppraser()); 
	        params.put("apprasial", dto.getApprasial());
	        params.put("signdate", dto.getSigndate()); 
	        params.put("discode", dto.getDiscode());
	        params.put("cmis30id", dto.getCmis30id());
	        params.put("edu_id", dto.getEduid());
	        str = baseInforManagerExt.queryProKey("a_apprasial");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertCommonMasterApprise.insert");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			return str;
		} catch (Exception e) {
			logger.error("insertCommonMasterApprise(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}

	public void updateCommonMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasial", aDto.getApprasial());
		params.put("signdate", aDto.getSigndate());
		params.put("apprasialid", aDto.getApprasialid());
		params.put("appraser", aDto.getAppraser());
		params.put("appraiserrid", aDto.getAppraiserrid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateCommonMasterApprise.update");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("updateCommonMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public void deleteMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("assessid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteMasterApprise.delete");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("deleteMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public void deleteCommonMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteCommonMasterApprise.delete");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("deleteCommonMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public void deleteMasterProcessApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisalid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteMasterProcessApprise.delete");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("deleteMasterProcessApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public void updateMasterProcessApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisal", aDto.getApprasial());
		params.put("signdate", aDto.getSigndate());
		params.put("appraisalid", aDto.getApprasialid());
		params.put("subject", aDto.getSubject());
		params.put("appraiserrid", aDto.getAppraiserrid());
		params.put("sign", aDto.getAppraser());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateMasterProcessApprise.update");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("updateMasterProcessApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public String insertMasterProcessApprise(AppraisalDto dto) {
		try {
			String str = "";
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("studentid", dto.getStudentid());
	        params.put("semesterid", dto.getSemesterid());
	        params.put("sign", dto.getAppraser()); 
	        params.put("appraisal", dto.getApprasial());
	        params.put("signdate", dto.getSigndate()); 
	        params.put("discode", dto.getDiscode());
	        params.put("cmis30id", dto.getCmis30id());
	        params.put("subject",dto.getSubject());
	        params.put("edu_id",dto.getEduid());
	        params.put("appraiserrid", dto.getAppraiserrid());
	        str = baseInforManagerExt.queryProKey("a_learnprocess_appraisal");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertMasterProcessApprise.insert");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			return str;
		}  catch (Exception e) {
			logger.error("insertMasterProcessApprise(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}

	@DataSource("read")
	public List<AppraisalDto> getCZMasterApprise(Map<String, Object> params) {
		try {
			//初中班主任普通评价信息
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getCZMasterApprise.query");
			return this.findList("MasterAppriseExt.getCZMasterApprise.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					AppraisalDto dto = new AppraisalDto();
					dto.setApprasialid(rs.getString("apprasialid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid")==null?null:Integer.parseInt(rs.getString("appraisaltypeid")));
					dto.setAppraiserrid(rs.getString("appraiserrid")==null?null:Integer.parseInt(rs.getString("appraiserrid")));
//					dto.setSemesterid(rs.getInt("semesterid"));
				//	dto.setAppraseridentify(rs.getString("appraseridentify")==null?null:Integer.parseInt(rs.getString("appraseridentify")));
					dto.setAppraser(rs.getString("appraser"));
					dto.setApprasial(rs.getString("apprasial"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEduid(rs.getString("edu_id"));
					dto.setStudentName(rs.getString("name"));
					dto.setSubject(rs.getString("subjectid"));
					dto.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl")
		            		:constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getCZMasterApprise(Map<String,Object>)", e);
		}
		return null;
	}

	public String insertCZMasterApprise(AppraisalDto dto) {
		try {
			String str ="";
			Map<String, Object> params = initParams(dto);
	        str = baseInforManagerExt.queryProKey("partInfo");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertCZMasterApprise.insert");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			return str;
		}  catch (Exception e) {
			logger.error("insertCZMasterApprise(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}

	public void updateCZMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisal", aDto.getApprasial());
		params.put("signdate", aDto.getSigndate());
		params.put("appraisalid", aDto.getApprasialid());
		params.put("subject", aDto.getSubject());
		params.put("signer_name", aDto.getAppraser());
		params.put("appraiserrid", aDto.getAppraiserrid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateCZMasterApprise.update");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("updateCZMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}

	public void deleteCZMasterApprise(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisalid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteCZMasterApprise.delete");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("deleteCZMasterApprise(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	
	/**
	 * 获取课程信息
	 * @return
	 */
	@DataSource("read")
	public List<SubjectDto> getCZSubjectInfos() {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getCZSubjectInfos.qurey");
			return this.findList("MasterAppriseExt.getCZSubjectInfos.qurey", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SubjectDto dto = new SubjectDto();
					dto.setSubjectid(rs.getString("subject_id"));
					dto.setSubjectName(rs.getString("subject_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getCZSubjectInfos()", e);
		}
		return null;
	}
	@DataSource("read")
	public List<SubjectDto> getGZSubjectInfos(Map<String,Object> params) {
		try {
			ISqlElement sqlDemo=this.processSql(params, "SchoolCourseExt.getGZTeacherSubject.query");
			return this.findList("SchoolCourseExt.getGZTeacherSubject.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SubjectDto dto = new SubjectDto();
					dto.setSubjectid(rs.getString("subject_id"));
					dto.setSubjectName(rs.getString("subject_name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getGZSubjectInfos(Map<String,Object>)", e);
		}
		return null;
	}
	/**
	 * 查询高中历史学期id
	 * @param classId
	 * @param gradeNum
	 * @param num
	 * @return
	 */
	@DataSource("read")
	public String getHSHistoryTermId(String classId, String gradeNum, String num) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classId", classId);
			params.put("gradenum", gradeNum);
			params.put("num", num);
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getHSHistoryTermId.qurey");
			List<String>strs = this.findList("MasterAppriseExt.getHSHistoryTermId.qurey", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("termid");
				}
			});
			if(strs!=null && strs.size()>0){
				return strs.get(0);
			}
		} catch (Exception e) {
			logger.error("getCZSubjectInfos(String,String,String)", e);
		}
		return null;
	}
	/**
	 * 根据父类id查询评价类型 
	 * @param upappraisaltypeid
	 * @return
	 */
	@DataSource("read")
	public List<String> getAppraisalTypeByUpAppraisalTypeId(Integer upappraisaltypeid) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("upappraisaltypeid", upappraisaltypeid);
			
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getAppraisalTypeByUpAppraisalTypeId.query");
			return this.findList("MasterAppriseExt.getAppraisalTypeByUpAppraisalTypeId.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("appraisaltypeid");
				}
			});
		} catch (Exception e) {
			logger.error("getAppraisalTypeByUpAppraisalTypeId(Integer)", e);
		}
		
		return null;
	}
	/**
	 * 高中评价查询
	 * @param appraisaltypeids
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<AppraisalDto> getAppraisal(List<String> appraisaltypeids,String semesterid,List<String> eduIds,String discode,String cmis30id){

		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("appraisaltypeids", appraisaltypeids);
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getAppraisal.query");
			return this.findList("MasterAppriseExt.getAppraisal.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					AppraisalDto dto = new AppraisalDto();
					dto.setApprasialid(rs.getString("apprasialid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid")==null?null:Integer.parseInt(rs.getString("appraisaltypeid")));
					dto.setSemesterid(rs.getInt("semesterid"));
					dto.setAppraseridentify(rs.getString("appraseridentify")==null?null:Integer.parseInt(rs.getString("appraseridentify")));
					dto.setAppraser(rs.getString("appraser"));
					dto.setApprasial(rs.getString("apprasial"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEduid(rs.getString("edu_id"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getAppraisal(List<String>,String,List<String>,String,String)", e);
		}
		return null;
	}
	/***
	 * 高综_记录袋查询
	 * @param appraisaltypeid
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<RecordPackageDto> getRecordpackage(
			String appraisaltypeid, String semesterid,
			List<String> eduIds, String discode, String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("appraisaltypeid",appraisaltypeid);
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			@SuppressWarnings("unused")
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getRecordpackage.query");
			return this.findList("MasterAppriseExt.getRecordpackage.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					RecordPackageDto dto = new RecordPackageDto();
					dto.setRecordid(rs.getInt("recordid"));
					dto.setStudentid(rs.getInt("studentid"));
					dto.setSemesterid(rs.getInt("semesterid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid")==null?null:Integer.parseInt(rs.getString("appraisaltypeid")));
					dto.setContent(rs.getString("content"));
					dto.setAppraseridentify(1);
					dto.setSigner(rs.getString("signer"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEdu_id(rs.getInt("edu_id"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getRecordpackage(String,String,List<String>,String,String)", e);
		}
		
		return null;
	}
	/**
	 * 高中学科学习过程记录
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<LearnprocessWorksDto> getLearnprocessWorks(String semesterid,
			List<String> eduIds, String discode, String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			@SuppressWarnings("unused")
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getLearnprocessWorks.query");
			return this.findList("MasterAppriseExt.getLearnprocessWorks.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					LearnprocessWorksDto dto = new LearnprocessWorksDto();
					dto.setWorkid(rs.getString("workid"));
					dto.setSubject(rs.getString("subject"));
					dto.setProcessdesc(rs.getString("processdesc"));
					dto.setSemesterid(rs.getString("semesterid"));					
					dto.setStudentid(rs.getString("studentid"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEdu_id(rs.getString("edu_id"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getLearnprocessWorks(String,List<String>,String,String)", e);
		}
		
		return null;
	}
	/**
	 * 高中_学业成就_学科学习过程记录_评语
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<LearnprocessAppraisaDto> getLearnprocessAppraisa(
			String semesterid, List<String> eduIds, String discode,
			String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			@SuppressWarnings("unused")
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getLearnprocessAppraisa.query");
			return this.findList("MasterAppriseExt.getLearnprocessAppraisa.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					LearnprocessAppraisaDto dto = new LearnprocessAppraisaDto();
					dto.setAppraisalid(rs.getString("appraisalid"));
					dto.setSubject(rs.getString("subject"));
					dto.setAppraisal(rs.getString("appraisal"));
					dto.setSemesterid(rs.getString("semesterid"));					
					dto.setStudentid(rs.getString("studentid"));
					dto.setSigndate(rs.getDate("signdate"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setSign(rs.getString("sign"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getLearnprocessAppraisa(String,List<String>,String,String)", e);
		}
		
		
		return null;
	}
	/**
	 * 高中_个性与发展_基本情况
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<PersonalityDto> getPersonality(String semesterid,
			List<String> eduIds, String discode, String cmis30id) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			@SuppressWarnings("unused")
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getPersonality.query");
			return this.findList("MasterAppriseExt.getPersonality.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					PersonalityDto dto = new PersonalityDto();
					dto.setBaseid(rs.getString("baseid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setSemesterid(rs.getString("semesterid"));	
					dto.setIndexid(rs.getString("indexid"));
					dto.setDevelopmentrd(rs.getString("developmentrd"));
					dto.setEdu_id(rs.getString("edu_id"));			
					dto.setSigndate(rs.getDate("signdate"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getPersonality(String,List<String>,String,String)", e);
		}
		
		return null;
	}
	/**
	 * 高中_综合实践活动
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	@DataSource("read")
	public List<PracticesDto> getPractices(List<String> appraisaltypeids,String semesterid,
			List<String> eduIds, String discode, String cmis30id) {
		
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("appraisaltypeids", appraisaltypeids);
			params.put("semesterid", semesterid);
			params.put("eduIds", eduIds);
			params.put("discode", discode);
			params.put("cmis30id", cmis30id);
			
			@SuppressWarnings("unused")
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getPractices.query");
			return this.findList("MasterAppriseExt.getPractices.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					PracticesDto dto = new PracticesDto();
					dto.setPracticeid(rs.getString("practiceid"));
					dto.setStudentid(rs.getString("studentid"));
					dto.setSemesterid(rs.getString("semesterid"));	
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					dto.setItem1(rs.getString("item1"));
					dto.setItem2(rs.getString("item2"));
					dto.setItem3(rs.getString("item3"));
					dto.setItem4(rs.getString("item4"));
					dto.setItem5(rs.getString("item5"));
					dto.setItem6(rs.getString("item6"));
					dto.setItem7(rs.getString("item7"));
					dto.setItem8(rs.getString("item8"));
					dto.setItem9(rs.getString("item9"));
					dto.setItem10(rs.getString("item10"));
					dto.setItem11(rs.getString("item11"));
					dto.setItem12(rs.getString("item12"));
					dto.setItem13(rs.getString("item13"));
					dto.setItem14(rs.getString("item14"));
					dto.setItem15(rs.getString("item15"));
					dto.setItem16(rs.getString("item16"));
					dto.setItem17(rs.getString("item17"));
					dto.setItem18(rs.getString("item18"));
					dto.setItem19(rs.getString("item19"));
					dto.setItem20(rs.getString("item20"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getPractices(List<String>,String,List<String>,String,String)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<CampusDto> getHighSchoolClassInfos(Map<String, Object> params) {
		try {
			//班主任课程评价信息
			return this.findList("SchoolCourseExt.getGZTeacherClass.query", params, new RowMapper(){
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
					dto.setTeacherName(rs.getString("name"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getHighSchoolClassInfos(Map<String,Object>)", e);
		}
		return null;
	}
	private Integer count;
	private List<AppraisalDto> aDtos;
	private Map<String,Integer>cacheNoEduIds;
	@Override
	public List<AppraisalDto> queryAppraiseFromRedis(List<SchoolTreeDto> studentInfos,String termId,List<String> sectionIds,String appraseridentify,String appraiserrid,String flag,Map<String,Object> params) {
		//根据学生基本信获取教育id
		aDtos = new ArrayList<AppraisalDto>();
		//记录缓存中没有数据的
		cacheNoEduIds = new HashMap<String,Integer>();
		if(null!=studentInfos&&studentInfos.size()>0){
			for(SchoolTreeDto sDto : studentInfos){//单个教育id
				count = 0;
				for(String sectionId : sectionIds){//单个栏目
					if("junior".equals(flag)){//初中
						this.cacaheToViewJunior(termId, appraseridentify, appraiserrid, cacheNoEduIds, sDto, sectionId, aDtos,params);
					}else{
						this.cacheToView(termId, appraseridentify, appraiserrid, cacheNoEduIds, sDto, sectionId, aDtos);
					}
				}
			}
			if(null!=cacheNoEduIds && cacheNoEduIds.size()>0){//缓存中没有的数据
				noChacheToViwe(studentInfos, sectionIds, aDtos, cacheNoEduIds);
			}
		}
		Collections.sort(aDtos,compareByAppraiseId);
		return aDtos;
	}
	/**
	 * 初中缓存数据存放
	 * @param termId
	 * @param appraseridentify
	 * @param appraiserrid
	 * @param cacheNoEduIds2
	 * @param sDto
	 * @param sectionId
	 * @param aDtos2
	 */
	private void cacaheToViewJunior(String termId, String appraseridentify,
			String appraiserrid, Map<String, Integer> cacheNoEduIds,
			SchoolTreeDto sDto, String sectionId, List<AppraisalDto> aDtos,Map<String,Object> params) {
		List<PartInfoCacheDto> queryRecodeInCaches = latestEvaluationRecordExt.queryRecodeInCache(sDto.getEdusyId(), termId, sectionId, appraseridentify, appraiserrid,PartInfoCacheDto.class);
		boolean isExist = false;
		if(null!=queryRecodeInCaches && queryRecodeInCaches.size()>0){
			for(PartInfoCacheDto pdiDto : queryRecodeInCaches){
				if(pdiDto==null){
					continue;
				}
				AppraisalDto dto = new AppraisalDto();
				if(params!=null&&params.size()>0){//说明有学科查询  只需要填充对应的学科
					String subject = (String) params.get("subject");
					if(NestUtil.isNotEmpty(subject)&&subject.equals(pdiDto.getSubject_id())){
						dto = fillAppraiseData(sDto, pdiDto);
						isExist = true;
					}else{
						continue;
					}
				}else{
					dto = fillAppraiseData(sDto, pdiDto);
				}
				aDtos.add(dto);
			}
		}else{
			cacheNoEduIds.put(sDto.getEdusyId(), ++count);
		}
		if(params!=null&&params.size()>0){
			if(!isExist){
				cacheNoEduIds.put(sDto.getEdusyId(), 1);
			}
		}
	}
	private AppraisalDto fillAppraiseData(SchoolTreeDto sDto,
			PartInfoCacheDto pdiDto) {
		AppraisalDto dto = new AppraisalDto();
		dto.setApprasialid(pdiDto.getPart_id());
		dto.setStudentid(sDto.getId());
		dto.setAppraisaltypeid(pdiDto.getTwo_part_id()==null?null:Integer.parseInt(pdiDto.getTwo_part_id()));
		dto.setAppraiserrid(pdiDto.getUserid()==null?null:Integer.parseInt(pdiDto.getUserid()));
		dto.setAppraser(pdiDto.getSigner_name());
		dto.setApprasial(pdiDto.getPart_info());
		dto.setSigndate(StringToDate(pdiDto.getCreateDate()));
		dto.setEduid(sDto.getEdusyId());
		dto.setStudentName(sDto.getText());
		dto.setSubject(pdiDto.getSubject_id());
		dto.setPhotoUrl(NestUtil.isEmpty(sDto.getName())?constantBean.get("defaultPhotosUrl")
				:constantBean.get("photo_nginx_server")+sDto.getName().replace("\\","/"));
		return dto;
	}
	private void noChacheToViwe(List<SchoolTreeDto> studentInfos,
			List<String> sectionIds, List<AppraisalDto> aDtos,
			Map<String, Integer> cacheNoEduIds) {
		//获取所有的key值
		Set<String> eduIdKeys = cacheNoEduIds.keySet();
		//将map里的eduid循环出来组装数据哦
		for(String eduIdKey : eduIdKeys){
			Integer eduIdCount = cacheNoEduIds.get(eduIdKey);
			if(eduIdCount==sectionIds.size()){
				//说明该娃娃就没有数据，需要对数据进行组装
				this.addCacheNoEduIdToView(studentInfos, aDtos, eduIdKey);
			}
		}
	}
	//将缓存中没有的学生 添加到页面dto中
	private void addCacheNoEduIdToView(List<SchoolTreeDto> studentInfos,
			List<AppraisalDto> aDtos, String eduIdKey) {
		for(SchoolTreeDto studentDto : studentInfos){
			if(eduIdKey.equals(studentDto.getEdusyId())){
				AppraisalDto aDto = new AppraisalDto();
				aDto.setStudentid(studentDto.getId());
				aDto.setEduid(studentDto.getEdusyId());
				aDto.setStudentName(studentDto.getText());
				aDto.setPhotoUrl(NestUtil.isEmpty(studentDto.getName())?constantBean.get("defaultPhotosUrl")
		        		:constantBean.get("photo_nginx_server")+studentDto.getName().replace("\\","/"));
				aDtos.add(aDto);
			}
		}
	}
	private void cacheToView(String termId, String appraseridentify, String appraiserrid, Map<String,Integer> cacheNoEduIds, SchoolTreeDto sDto, String sectionId, List<AppraisalDto> aDtos) {
		List<AapprasialCacheDto> queryRecodeInCaches = latestEvaluationRecordExt.queryRecodeInCache(sDto.getEdusyId(), termId, sectionId, appraseridentify, appraiserrid,AapprasialCacheDto.class);
		if(null!=queryRecodeInCaches && queryRecodeInCaches.size()>0){
			for(AapprasialCacheDto cacheDto : queryRecodeInCaches){
				//将数据封装到AppraisalDto中
				AppraisalDto aDto = new AppraisalDto();
				aDto.setApprasialid(cacheDto.getApprasialid());
				aDto.setStudentid(sDto.getId());
				aDto.setAppraisaltypeid(cacheDto.getAppraisaltypeid()==null?null:Integer.parseInt(cacheDto.getAppraisaltypeid()));
				aDto.setAppraiserrid(cacheDto.getAppraiserrid()==null?null:Integer.parseInt(cacheDto.getAppraiserrid()));
				aDto.setAppraser(cacheDto.getAppraser());
				aDto.setApprasial(cacheDto.getApprasial());
				aDto.setSigndate(StringToDate(cacheDto.getSigndate()));
				aDto.setEduid(cacheDto.getEdu_id());
				aDto.setStudentName(sDto.getText());
				aDto.setPhotoUrl(NestUtil.isEmpty(sDto.getName())?constantBean.get("defaultPhotosUrl")
		        		:constantBean.get("photo_nginx_server")+sDto.getName().replace("\\","/"));
				aDtos.add(aDto);
			}
		}else{
			cacheNoEduIds.put(sDto.getEdusyId(), ++count);
		}
	}
	
	private ILatestEvaluationRecordExt latestEvaluationRecordExt;

	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}
	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	@Override
	public String insertAppraisalToCache(AppraisalDto dto) {
		try {
			String str ="";
			Map<String, Object> params = this.initParams(dto);
	        str = baseInforManagerExt.queryProKey("partInfo");
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	params.put("signdate", dto.getSigndate()==null?null:sdf.format(dto.getSigndate())); 
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertCZMasterApprise.insert1");
			//将数据放入指定dto
			PartInfoCacheDto cacheDto = this.initCacheDto(dto, str);
			//数据入缓存
			latestEvaluationRecordExt.addRecodeInCacheByProKey(dto.getEduid(),String.valueOf(dto.getSemesterid()),String.valueOf(dto.getAppraisaltypeid()),dto.getAppraseridentity(),String.valueOf(dto.getAppraiserrid()),str,cacheDto,sqlelement
					,dto.getSigndate()==null?null:sdf.format(dto.getSigndate()));
			return str;
		}  catch (Exception e) {
			logger.error("insertAppraisalToCache(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}
	private PartInfoCacheDto initCacheDto(AppraisalDto dto, String str) {
		PartInfoCacheDto picDto = new PartInfoCacheDto();
		picDto.setStudentid(dto.getStudentid());
		picDto.setTwo_part_id(String.valueOf(dto.getAppraisaltypeid()));
		picDto.setUserid(String.valueOf(dto.getAppraiserrid()));
		picDto.setTermid(String.valueOf(dto.getSemesterid()));
		picDto.setWrite_man(dto.getAppraseridentity());
		picDto.setSigner_name(dto.getAppraser());
		picDto.setPart_info(dto.getApprasial());
		picDto.setCreateDate(dateToString(dto.getSigndate()));
		picDto.setDiscode(String.valueOf(dto.getDiscode()));
		picDto.setCmis30id(String.valueOf(dto.getCmis30id()));
		picDto.setSubject_id(dto.getSubject());
		picDto.setEdu_id(dto.getEduid());
		picDto.setPart_id(str);
		picDto.setEdittime(dateToString(new Date()));
		return picDto;
	}
	private Map<String, Object> initParams(AppraisalDto dto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("studentid", dto.getStudentid());
		params.put("appraisaltypeid", dto.getAppraisaltypeid());
		params.put("appraiserrid", dto.getAppraiserrid());
		params.put("semesterid", dto.getSemesterid());
		params.put("appraseridentify", dto.getAppraseridentify()==null ? dto.getAppraseridentity() : dto.getAppraseridentify());
		params.put("sign", dto.getAppraser()); 
		params.put("appraisal", dto.getApprasial());
		params.put("signdate", dto.getSigndate()); 
		params.put("discode", dto.getDiscode());
		params.put("cmis30id", dto.getCmis30id());
		params.put("subject", dto.getSubject());
		params.put("edu_id", dto.getEduid());
		return params;
	}
	@Override
	public void updateAppraiseToCache(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisal", aDto.getApprasial());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		params.put("signdate", aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		params.put("appraisalid", aDto.getApprasialid());
		params.put("subject", aDto.getSubject());
		params.put("signer_name", aDto.getAppraser());
		params.put("appraiserrid", aDto.getAppraiserrid());
		try {
			PartInfoCacheDto cacheDto = this.initCacheDto(aDto);
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateCZMasterApprise.update1");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),aDto.getAppraseridentity(),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),cacheDto,sqlelement
					,aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		} catch (Exception e) {
			logger.error("updateAppraiseToCache(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	private PartInfoCacheDto initCacheDto(AppraisalDto dto) {
		PartInfoCacheDto picDto = new PartInfoCacheDto();
		picDto.setUserid(String.valueOf(dto.getAppraiserrid()));
		picDto.setSigner_name(dto.getAppraser());
		picDto.setPart_info(dto.getApprasial());
		picDto.setCreateDate(dateToString(dto.getSigndate()));
		picDto.setSubject_id(dto.getSubject());
		picDto.setPart_id(dto.getApprasialid());
		picDto.setEdittime(dateToString(new Date()));
		return picDto;
	}
	@Override
	public void deleteAppraiseCache(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisalid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteCZMasterApprise.delete");
			latestEvaluationRecordExt.delRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),aDto.getAppraseridentity(),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),sqlelement,PartInfoCacheDto.class);
		} catch (Exception e) {
			logger.error("deleteAppraiseCache(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	/**
	 * 时间转换
	 * @param d
	 * @return
	 */
	private Date StringToDate(String d) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = this.dateToString(sdf.parse(d));
			return java.sql.Date.valueOf(date);
			} catch (Exception e) {
				String date = this.dateToString(new Date());
				java.util.Date valueOf = java.sql.Date.valueOf(date);
				return valueOf;
		}
	}
	private String dateToString(Date signDate){
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		String date =simple.format(signDate);
		return date;
	}
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AppraisalDto app11 = (AppraisalDto) app1;
            	AppraisalDto app22 = (AppraisalDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };

	@Override
	public String insertAppraisalToCacheHigh(AppraisalDto dto) {
		try {
			String str = "";
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("studentid", dto.getStudentid());
	        params.put("appraisaltypeid", dto.getAppraisaltypeid());
	        params.put("appraiserrid", dto.getAppraiserrid());
	        params.put("semesterid", dto.getSemesterid());
	        params.put("appraseridentify", dto.getAppraseridentify()==null ? dto.getAppraseridentity() : dto.getAppraseridentify());
	        params.put("appraser", dto.getAppraser()); 
	        params.put("apprasial", dto.getApprasial());
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        params.put("signdate", dto.getSigndate()==null?null:sdf.format(dto.getSigndate())); 
	        params.put("discode", dto.getDiscode());
	        params.put("cmis30id", dto.getCmis30id());
	        params.put("edu_id", dto.getEduid());
	        str = baseInforManagerExt.queryProKey("a_apprasial");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.insertCommonMasterApprise.insert1");
			AapprasialCacheDto cacheDto = this.initCacheDtoHigh(dto, str);
			//数据入缓存
			latestEvaluationRecordExt.addRecodeInCacheByProKey(dto.getEduid(),String.valueOf(dto.getSemesterid()),String.valueOf(dto.getAppraisaltypeid()),String.valueOf(dto.getAppraseridentify()),String.valueOf(dto.getAppraiserrid()),str,cacheDto,sqlelement
					,dto.getSigndate()==null?null:sdf.format(dto.getSigndate()));
			return str;
		} catch (Exception e) {
			logger.error("insertAppraisalToCacheHigh(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}
	private AapprasialCacheDto initCacheDtoHigh(AppraisalDto dto, String str) {
		AapprasialCacheDto acDto = new AapprasialCacheDto();
		acDto.setStudentid(dto.getStudentid());
		acDto.setAppraisaltypeid(String.valueOf(dto.getAppraisaltypeid()));
		acDto.setAppraiserrid(String.valueOf(dto.getAppraiserrid()));
		acDto.setSemesterid(String.valueOf(dto.getSemesterid()));
		acDto.setAppraseridentify(String.valueOf(dto.getAppraseridentify()));
		acDto.setAppraser(dto.getAppraser());
		acDto.setApprasial(dto.getApprasial());
		acDto.setSigndate(dateToString(dto.getSigndate()));
		acDto.setDiscode(String.valueOf(dto.getDiscode()));
		acDto.setCmis30id(String.valueOf(dto.getCmis30id()));
		acDto.setEdu_id(dto.getEduid());
		acDto.setApprasialid(str);
		acDto.setEdittime(dateToString(new Date()));
		return acDto;
	}
	@Override
	public void updateAppraiseToCacheHigh(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasial", aDto.getApprasial());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		params.put("signdate",aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		params.put("apprasialid", aDto.getApprasialid());
		params.put("appraser", aDto.getAppraser());
		params.put("appraiserrid", aDto.getAppraiserrid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateCommonMasterApprise.update1");
			AapprasialCacheDto cacheDto = this.initCacheDtoHigh(aDto);
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),String.valueOf(aDto.getAppraseridentify()),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),cacheDto,sqlelement
					,aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		} catch (Exception e) {
			logger.error("updateAppraiseToCacheHigh(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	private AapprasialCacheDto initCacheDtoHigh(AppraisalDto dto) {
		AapprasialCacheDto acDto = new AapprasialCacheDto();
		acDto.setAppraiserrid(String.valueOf(dto.getAppraiserrid()));
		acDto.setAppraser(dto.getAppraser());
		acDto.setApprasial(dto.getApprasial());
		acDto.setSigndate(dateToString(dto.getSigndate()));
		acDto.setApprasialid(dto.getApprasialid());
		acDto.setEdittime(dateToString(new Date()));
		return acDto;
	}
	@Override
	public void deleteAppraiseCacheHigh(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteCommonMasterApprise.delete");
			latestEvaluationRecordExt.delRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),String.valueOf(aDto.getAppraseridentify()),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),sqlelement,AapprasialCacheDto.class);
		} catch (Exception e) {
			logger.error("deleteAppraiseCacheHigh(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	@Override
	public List<AppraisalDto> queryProcessAppraiseFromRedis(
			List<SchoolTreeDto> studentInfos, String termId,
			String sectionId, String appraseridentify,
			String appraiserrid, String flag, Map<String, Object> params) {
		//根据学生基本信获取教育id
		aDtos = new ArrayList<AppraisalDto>();
		//记录缓存中没有数据的
		cacheNoEduIds = new HashMap<String,Integer>();
		if(null!=studentInfos&&studentInfos.size()>0){
			for(SchoolTreeDto sDto : studentInfos){//单个教育id
				this.cacheToViewHighProcess(termId, appraseridentify, appraiserrid, cacheNoEduIds, sDto, sectionId, aDtos,params);
			}
			if(null!=cacheNoEduIds && cacheNoEduIds.size()>0){//缓存中没有的数据
				this.noChacheToViewHighProcess(studentInfos, sectionId, aDtos, cacheNoEduIds);
			}
		}
		Collections.sort(aDtos,compareByAppraiseId);
		return aDtos;
	}
	private void noChacheToViewHighProcess(List<SchoolTreeDto> studentInfos,String sectionId, List<AppraisalDto> aDtos,	Map<String, Integer> cacheNoEduIds) {
		Set<String> cacheNoEduids = cacheNoEduIds.keySet();
		for(String cacheNoEduid : cacheNoEduids){
			this.addCacheNoEduIdToView(studentInfos, aDtos, cacheNoEduid);
		}
	}
	private void cacheToViewHighProcess(String termId, String appraseridentify,	String appraiserrid, Map<String, Integer> cacheNoEduIds,SchoolTreeDto sDto, String sectionId, List<AppraisalDto> aDtos, Map<String, Object> params) {
		List<AlearnprocessAppraisalCacheDto> queryRecodeInCaches = latestEvaluationRecordExt.queryRecodeInCache(sDto.getEdusyId(), termId, sectionId, appraseridentify, appraiserrid,AlearnprocessAppraisalCacheDto.class);
		boolean isExist = false;//是否已经存在了
		if(null!=queryRecodeInCaches && queryRecodeInCaches.size()>0){
			for(AlearnprocessAppraisalCacheDto aacDto : queryRecodeInCaches){
				if(sDto.getText().equals("李宇杰")){
					
				}
				AppraisalDto dto = new AppraisalDto();
				if(params!=null&&params.size()>0){//说明有学科查询  只需要填充对应的学科
					String subject = (String) params.get("subject");
					if(NestUtil.isNotEmpty(subject)&&subject.equals(aacDto.getSubject())){
						dto = fillHighProcessData(sDto, aacDto);
						isExist = true;
					}else{
						
						continue;
					}
				}else{
					dto = fillHighProcessData(sDto, aacDto);
				}
				aDtos.add(dto);
			}
		}else{
			cacheNoEduIds.put(sDto.getEdusyId(), 1);
		}
		if(params!=null&&params.size()>0){
			if(!isExist){
				cacheNoEduIds.put(sDto.getEdusyId(), 1);
			}
		}
	}
	private AppraisalDto fillHighProcessData(SchoolTreeDto sDto,
			AlearnprocessAppraisalCacheDto aacDto) {
		AppraisalDto dto = new AppraisalDto();
		dto.setApprasialid(aacDto.getAppraisalid());
		dto.setStudentid(sDto.getId());
		dto.setStudentName(sDto.getText());
		dto.setApprasial(aacDto.getAppraisal());
		dto.setSubject(aacDto.getSubject());
		dto.setAppraser(aacDto.getSign());
		dto.setSigndate(StringToDate(aacDto.getSigndate()));
		dto.setEduid(sDto.getEdusyId());
		dto.setPhotoUrl(NestUtil.isEmpty(sDto.getName())?constantBean.get("defaultPhotosUrl")
				:constantBean.get("photo_nginx_server")+sDto.getName().replace("\\","/"));
		return dto;
	}
	@Override
	public String insertProcessAppraisalToCacheHigh(AppraisalDto dto) {
		try {
			String str = "";
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("studentid", dto.getStudentid());
	        params.put("semesterid", dto.getSemesterid());
	        params.put("sign", dto.getAppraser()); 
	        params.put("appraisal", dto.getApprasial());
	        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	        params.put("signdate",sdf.format(dto.getSigndate())); 
	        params.put("discode", dto.getDiscode());
	        params.put("cmis30id", dto.getCmis30id());
	        params.put("subject",dto.getSubject());
	        params.put("edu_id",dto.getEduid());
	        params.put("appraiserrid", dto.getAppraiserrid());
	        str = baseInforManagerExt.queryProKey("a_learnprocess_appraisal");
	        params.put("proKey",str); 
			ISqlElement sqlelement=this.processSql(params,"MasterAppriseExt.insertMasterProcessApprise.insert1");
			AlearnprocessAppraisalCacheDto cacheDto = this.initCacheDtoHighProcess(dto, str);
			//数据入缓存
			latestEvaluationRecordExt.addRecodeInCacheByProKey(dto.getEduid(),String.valueOf(dto.getSemesterid()),String.valueOf(dto.getAppraisaltypeid()),String.valueOf(dto.getAppraseridentify()),String.valueOf(dto.getAppraiserrid()),str,cacheDto,sqlelement
					,sdf.format(dto.getSigndate()));
			return str;
		}  catch (Exception e) {
			logger.error("insertProcessAppraisalToCacheHigh(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		} 
	}
	private AlearnprocessAppraisalCacheDto initCacheDtoHighProcess(AppraisalDto dto, String str) {
		AlearnprocessAppraisalCacheDto aacDto = new AlearnprocessAppraisalCacheDto();
		aacDto.setAppraisalid(str);
		aacDto.setStudentid(dto.getStudentid());
		aacDto.setSemesterid(String.valueOf(dto.getSemesterid()));
		aacDto.setSign(dto.getAppraser());
		aacDto.setAppraisal(dto.getApprasial());
		aacDto.setSigndate(dateToString(dto.getSigndate()));
		aacDto.setDiscode(String.valueOf(dto.getDiscode()));
		aacDto.setCmis30id(String.valueOf(dto.getCmis30id()));
		aacDto.setSubject(dto.getSubject());
		aacDto.setEdu_id(dto.getEduid());
		aacDto.setAppraseridentify(String.valueOf(null==dto.getAppraseridentify()?"":dto.getAppraseridentify()));
		aacDto.setAppraiserrid(String.valueOf(dto.getAppraiserrid()));
		aacDto.setEdittime(dateToString(new Date()));
		return aacDto;
	}
	@Override
	public void updateProcessAppraiseToCacheHigh(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisal", aDto.getApprasial());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		params.put("signdate", aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		params.put("appraisalid", aDto.getApprasialid());
		params.put("subject", aDto.getSubject());
		params.put("appraiserrid", aDto.getAppraiserrid());
		params.put("sign", aDto.getAppraser());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.updateMasterProcessApprise.update1");
			AlearnprocessAppraisalCacheDto cacheDto = this.initCacheDtoHighProcessUpdate(aDto);
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),String.valueOf(aDto.getAppraseridentify()),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),cacheDto,sqlelement
					,aDto.getSigndate()==null?null:sdf.format(aDto.getSigndate()));
		} catch (Exception e) {
			logger.error("updateProcessAppraiseToCacheHigh(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	private AlearnprocessAppraisalCacheDto initCacheDtoHighProcessUpdate(AppraisalDto aDto) {
		AlearnprocessAppraisalCacheDto aacDto = new AlearnprocessAppraisalCacheDto();
		aacDto.setAppraisal(aDto.getApprasial());
		aacDto.setSigndate(dateToString(aDto.getSigndate()));
		aacDto.setAppraisalid(aDto.getApprasialid());
		aacDto.setSubject(aDto.getSubject());
		aacDto.setAppraiserrid(String.valueOf(aDto.getAppraiserrid()));
		aacDto.setSign(aDto.getAppraser());
		return aacDto;
	}
	@Override
	public void deleteProcessAppraiseCacheHigh(AppraisalDto aDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisalid", aDto.getApprasialid());
		try {
			ISqlElement sqlelement=this.processSql(params, "MasterAppriseExt.deleteMasterProcessApprise.delete");
			latestEvaluationRecordExt.delRecodeInCacheByProKey(aDto.getEduid(),String.valueOf(aDto.getSemesterid()),String.valueOf(aDto.getAppraisaltypeid()),String.valueOf(aDto.getAppraseridentify()),String.valueOf(aDto.getAppraiserrid()),aDto.getApprasialid(),sqlelement,AlearnprocessAppraisalCacheDto.class);
		} catch (Exception e) {
			logger.error("deleteProcessAppraiseCacheHigh(AppraisalDto)", e);
			throw new ManagerException(e);
		}
	}
	@Override
	public List<String> queryCZSectionsFromDB() {
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.queryCZSectionsFromDB.query");
			String processSql = "MasterAppriseExt.queryCZSectionsFromDB.query";
			//班主任课程评价信息
			return this.findList(processSql, params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("sectionInfos");
				}
			});
		} catch (Exception e) {
			logger.error("queryCZSectionsFromDB()", e);
		}
		return null;
	}
	@Override
	public List<String> queryGZSectionsFromDB() {
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.queryGZSectionsFromDB.query");
			String processSql = "MasterAppriseExt.queryGZSectionsFromDB.query";
			//班主任课程评价信息
			return this.findList(processSql, params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getString("sectionInfos");
				}
			});
		} catch (Exception e) {
			logger.error("queryGZSectionsFromDB()", e);
		}
		return null;
	}
	@Override
	public List<AppraisalDto> getAppraisalFromCache(List<String> appraisalTypeIds, String zsTermId, List<String> eduIds) {
		try {
			List<AppraisalDto> qureyList = new ArrayList<AppraisalDto>();
			if(null!=eduIds && eduIds.size()>0 && null!=appraisalTypeIds &&appraisalTypeIds.size()>0){
				for(String eduId : eduIds){
					for(String typeId : appraisalTypeIds){
						List<AapprasialCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeId,AapprasialCacheDto.class);
						if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
							for(AapprasialCacheDto acDto : queryRecodeInCache){
								AppraisalDto aDto = new AppraisalDto();
								aDto.setApprasialid(acDto.getApprasialid());
								aDto.setStudentid(acDto.getStudentid());
								aDto.setAppraisaltypeid(acDto.getAppraisaltypeid()==null?null:Integer.parseInt(acDto.getAppraisaltypeid()));
								aDto.setSemesterid(acDto.getSemesterid()==null?null:Integer.parseInt(acDto.getSemesterid()));
								aDto.setAppraseridentify(acDto.getAppraseridentify()==null?null:Integer.parseInt(acDto.getAppraseridentify()));
								aDto.setAppraser(acDto.getAppraser());
								aDto.setApprasial(acDto.getApprasial());
								aDto.setSigndate(StringToDate(acDto.getSigndate()));
								aDto.setEduid(acDto.getEdu_id());
								qureyList.add(aDto);
							}
						}
					}
				}
				return qureyList;
			}
		} catch (Exception e) {
			logger.error("getAppraisalFromCache(List<String>,String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<RecordPackageDto> getRecordpackageFromCache(String typeSxjld,String zsTermId, List<String> eduIds) {
		try {
			List<RecordPackageDto> rpDtos = new ArrayList<RecordPackageDto>();
			if(null!= eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<ArecordpackageCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeSxjld,ArecordpackageCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(ArecordpackageCacheDto acDto : queryRecodeInCache ){
							RecordPackageDto rpDto = new RecordPackageDto();
							rpDto.setRecordid(acDto.getRecordid()==null?null:Integer.parseInt(acDto.getRecordid()));
							rpDto.setStudentid(acDto.getStudentid()==null?null:Integer.parseInt(acDto.getStudentid()));
							rpDto.setSemesterid(acDto.getSemesterid()==null?null:Integer.parseInt(acDto.getSemesterid()));
							rpDto.setAppraisaltypeid(acDto.getAppraisaltypeid()==null?null:Integer.parseInt(acDto.getAppraisaltypeid()));
							rpDto.setContent(acDto.getContent());
							rpDto.setAppraseridentify(1);
							rpDto.setSigner(acDto.getSigner());
							rpDto.setSigndate(StringToDate(acDto.getSigndate()));
							rpDto.setEdu_id(acDto.getEdu_id()==null?null:Integer.parseInt(acDto.getEdu_id()));
							rpDtos.add(rpDto);
						}
					}
				}
				return rpDtos;
			}
		} catch (Exception e) {
			logger.error("getRecordpackageFromCache(String,String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<PersonalityDto> getPersonalityFromCache(String typeGxfzJbqk,String zsTermId, List<String> eduIds) {
		try {
			List<PersonalityDto> pDtos = new ArrayList<PersonalityDto>();
			if(null!=eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<ApersonalityCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeGxfzJbqk,ApersonalityCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(ApersonalityCacheDto acDto : queryRecodeInCache){
							PersonalityDto dto = new PersonalityDto();
							dto.setBaseid(acDto.getBaseid());
							dto.setStudentid(acDto.getStudentid());
							dto.setSemesterid(acDto.getSemesterid());	
							dto.setIndexid(acDto.getIndexid());
							dto.setDevelopmentrd(acDto.getDevelopmentrd());
							dto.setEdu_id(acDto.getEdu_id());			
							dto.setSigndate(StringToDate(acDto.getSigndate()));
							pDtos.add(dto);
						}
					}
				}
				return pDtos;
			}
		} catch (Exception e) {
			logger.error("getPersonalityFromCache(String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<LearnprocessWorksDto> getLearnprocessWorksFromCache(String typeXyGcjl, String zsTermId, List<String> eduIds) {
		try {
			List<LearnprocessWorksDto> lwDtos = new ArrayList<LearnprocessWorksDto>();
			if(null!=eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<AlearnprocessWorksCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeXyGcjl,AlearnprocessWorksCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(AlearnprocessWorksCacheDto acDto : queryRecodeInCache){
							LearnprocessWorksDto lwDto = new LearnprocessWorksDto();
							lwDto.setWorkid(acDto.getWorkid());
							lwDto.setSubject(acDto.getSubject());
							lwDto.setProcessdesc(acDto.getProcessdesc());
							lwDto.setSemesterid(acDto.getSemesterid());					
							lwDto.setStudentid(acDto.getStudentid());
							lwDto.setSigndate(StringToDate(acDto.getSigndate()));
							lwDto.setEdu_id(acDto.getEdu_id());
							lwDtos.add(lwDto);
						}
					}
				}
				return lwDtos;
			}
		} catch (Exception e) {
			logger.error("getLearnprocessWorksFromCache(String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<LearnprocessAppraisaDto> getLearnprocessAppraisaFromCache(String typeKeChengPingyu, String zsTermId, List<String> eduIds) {
		try {
			List<LearnprocessAppraisaDto> laDtos = new ArrayList<LearnprocessAppraisaDto>();
			if(null!=eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<AlearnprocessAppraisalCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeKeChengPingyu,AlearnprocessAppraisalCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(AlearnprocessAppraisalCacheDto aaCdto : queryRecodeInCache){
							LearnprocessAppraisaDto dto = new LearnprocessAppraisaDto();
							dto.setAppraisalid(aaCdto.getAppraisalid());
							dto.setSubject(aaCdto.getSubject());
							dto.setAppraisal(aaCdto.getAppraisal());
							dto.setSemesterid(aaCdto.getSemesterid());					
							dto.setStudentid(aaCdto.getStudentid());
							dto.setSigndate(StringToDate(aaCdto.getSigndate()));
							dto.setEdu_id(aaCdto.getEdu_id());
							dto.setSign(aaCdto.getSign());
							laDtos.add(dto);
						}
					}
				}
				return laDtos;
			}
		} catch (Exception e) {
			logger.error("getLearnprocessAppraisaFromCache(String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<PracticesDto> getPracticesFromCache(List<String> appraisalTypeIds, String zsTermId, List<String> eduIds) {
		try {
			List<PracticesDto> pDtos = new ArrayList<PracticesDto>();
			if(null!=appraisalTypeIds && appraisalTypeIds.size()>0 && null!= eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					for(String typeId : appraisalTypeIds){
						List<ApracticesCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, zsTermId, typeId,ApracticesCacheDto.class);
						if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
							for(ApracticesCacheDto acDto : queryRecodeInCache){
								PracticesDto dto = new PracticesDto();
								dto.setPracticeid(acDto.getPracticeid());
								dto.setStudentid(acDto.getStudentid());
								dto.setSemesterid(acDto.getSemesterid());	
								dto.setEdu_id(acDto.getEdu_id());
								dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
								dto.setItem1(acDto.getItem1());
								dto.setItem2(acDto.getItem2());
								dto.setItem3(acDto.getItem3());
								dto.setItem4(acDto.getItem4());
								dto.setItem5(acDto.getItem5());
								dto.setItem6(acDto.getItem6());
								dto.setItem7(acDto.getItem7());
								dto.setItem8(acDto.getItem8());
								dto.setItem9(acDto.getItem9());
								dto.setItem10(acDto.getItem10());
								dto.setItem11(acDto.getItem11());
								dto.setItem12(acDto.getItem12());
								dto.setItem13(acDto.getItem13());
								dto.setItem14(acDto.getItem14());
								dto.setItem15(acDto.getItem15());
								dto.setItem16(acDto.getItem16());
								dto.setItem17(acDto.getItem17());
								dto.setItem18(acDto.getItem18());
								dto.setItem19(acDto.getItem19());
								dto.setItem20(acDto.getItem20());
								pDtos.add(dto);
							}
						}
					}
				}
				return pDtos;
			}
		} catch (Exception e) {
			logger.error("getPracticesFromCache(List<String>,String,List<String>,String,String)", e);
		}
		return null;
	}
	@Override
	public List<SchoolTreeDto> getStudentInfoXieBook(Map<String, Object> params1) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params1, "MasterAppriseExt.getStudentInfoXieBook.query");
			return this.findList("MasterAppriseExt.getStudentInfoXieBook.query", params1, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setText(rs.getString("name"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@Override
	public List<SchoolTreeDto> getStudentInfoXieBookNull(
			Map<String, Object> params1) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params1, "MasterAppriseExt.getStudentInfoXieBookNull.query");
			return this.findList("MasterAppriseExt.getStudentInfoXieBookNull.query", params1, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setText(rs.getString("name"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@Override
	public List<SchoolTreeDto> getStudentInfoXieBookSueed(
			Map<String, Object> params1) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params1, "MasterAppriseExt.getStudentInfoXieBookSueed.query");
			return this.findList("MasterAppriseExt.getStudentInfoXieBookSueed.query", params1, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setText(rs.getString("name"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setStudentno(rs.getString("studentno"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public List<SubjectDto> getGZSubjectInfos() {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			ISqlElement sqlDemo=this.processSql(params, "MasterAppriseExt.getGZSubjectInfos.qurey");
			return this.findList("MasterAppriseExt.getGZSubjectInfos.qurey", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SubjectDto dto = new SubjectDto();
					dto.setSubjectid(rs.getString("subjectName"));
					dto.setSubjectName(rs.getString("subjectName"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getGZSubjectInfos()", e);
		}
		return null;
	}
}
