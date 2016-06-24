package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPersonalityServiceExt;
import com.flyrish.hades.util.DataSource;

public class PersonalityServiceExtImpl extends JdbcRootManager implements IPersonalityServiceExt{
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	public IBaseInforManagerExt baseInforManagerExt;
	
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}



	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}



	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}



	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}
	
	public List selectPersonality(Map<String, Object> queryMap) {
		try {
			return this.findList("Personality.selectPersonality.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PersonalityDto personalityDto=new PersonalityDto();
					personalityDto.setBaseid(rs.getString("baseid"));
					personalityDto.setDevelopmentrd(rs.getString("developmentrd"));
					personalityDto.setIndexid(rs.getString("indexid"));
					personalityDto.setStudentid(rs.getString("studentid"));
					personalityDto.setSemesterid(rs.getString("semesterid"));
					personalityDto.setSigndate(rs.getDate("signdate"));
					return personalityDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectPersonality(Map)",e);
		}
		return null;
	}

	public boolean insertPersonality(PersonalityDto personalityDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("indexid",personalityDto.getIndexid());
		params.put("developmentrd",personalityDto.getDevelopmentrd());
		params.put("studentid",personalityDto.getStudentid());
		params.put("edu_id",personalityDto.getEdu_id());
		params.put("semesterid",personalityDto.getSemesterid());
		params.put("cmis30id",personalityDto.getCmis30id());
		params.put("discode",personalityDto.getDiscode());
		params.put("signdate",personalityDto.getSigndate());
		try{
			ISqlElement sqlElement=this.processSql(params,"Personality.insertPersonality.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("insertPersonality(AppraisalDto)",e);
			throw new ManagerException(e);
		}
	}

	public boolean updatePersonality(PersonalityDto personalityDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("baseid",personalityDto.getBaseid());
		params.put("developmentrd",personalityDto.getDevelopmentrd());
		params.put("signdate",personalityDto.getSigndate());
		try{
			ISqlElement sqlElement=this.processSql(params,"Personality.updatePersonality.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updatePersonality(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	/**
	 * 增加附件
	 * @param 附件dto,id
	 * @return
	 */
	public boolean insertAttach(AttachDto attachDto,String id) {
		if(attachDto!=null&&!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachtype",attachDto.getAttachtype());
			params.put("attachname",attachDto.getFilename());
			params.put("attachpath",attachDto.getFilepath());
			params.put("image",attachDto.getImage());
			params.put("cmis30id",attachDto.getCmis30id());
			params.put("discode",attachDto.getDiscode());
			params.put("attachtypeid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertattach.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("insertRecordpackage(RecordPackageDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	
	}

	public boolean deleteAttach(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deletetSelfAttach.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteAttach(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}

	/**
	 * 查询AttachList
	 * @param Map查询参数
	 * @return
	 */
	public List selectAttachList(Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectattachList.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachDto attachDto = new AttachDto();
					attachDto.setFilename(rs.getString("attachname"));
					attachDto.setFilepath(rs.getString("attachpath"));
					attachDto.setAttachid(Integer.valueOf(rs.getString("attachid")));
					return attachDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectAttachList(Map)",e);
		}
		return null;
	}
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return
	 */
	public List selectAttach(Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectattach.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachDto attachDto = new AttachDto();
					attachDto.setFilename(rs.getString("attachname"));
					attachDto.setFilepath(rs.getString("attachpath"));
					attachDto.setAttachid(Integer.valueOf(rs.getString("attachid")));
					return attachDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectAttach(Map)",e);
		}
		return null;
	}

	public String insertSelfApp(AppraisalDto appraisal,
			List<AttachDto> attachs) {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraisaltypeid",appraisal.getAppraisaltypeid());
			params.put("appraiserrid",appraisal.getAppraiserrid());
			params.put("apprasial",appraisal.getApprasial());
			params.put("studentid",appraisal.getStudentid());
			params.put("appraseridentify",appraisal.getAppraseridentify());
			params.put("appraser",appraisal.getAppraser());
			params.put("signdate",appraisal.getSigndate());
			params.put("edu_id",appraisal.getEduid());
			params.put("semesterid",appraisal.getSemesterid());
			params.put("cmis30id",appraisal.getCmis30id());
			params.put("discode",appraisal.getDiscode());
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisal.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				Map<String, Object> queryMap = new HashMap<String, Object>();
				List lst = new ArrayList();
				lst = getJdbcTemplate().queryForList("select s_A_APPRASIAL.CURRVAL from dual");
				String str = lst.get(0).toString().split("=")[1].split("}")[0];
				return str;
			}catch(Exception e){
				logger.error("insertSelfApp(AppraisalDto,List)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
	}
	/**
	 * 获取自我评价列表信息
	 */
	public List selectSelfAppraise(final Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectSelfAppraise.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto appraisal=new AppraisalDto();
					appraisal.setApprasialid(rs.getString("apprasialid"));
					appraisal.setSigndate((Date) rs.getDate("signdate"));
					appraisal.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
					appraisal.setApprasial((String) rs.getString("apprasial"));
					appraisal.setAppraser((String) rs.getString("appraser"));
					appraisal.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
					appraisal.setStudentid(rs.getString("studentid"));
					appraisal.setAppraseridentify(Integer.valueOf(rs.getString("appraseridentify")));
					AttachDto attachDto = new AttachDto();
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("recordid", Integer.valueOf(rs.getString("apprasialid")));
					queryMap1.put("cmis30id", queryMap.get("cmis30id"));
					queryMap1.put("discode", queryMap.get("discode"));
					queryMap1.put("attachtype", "2");
					List<AttachDto> lst= selectAttach(queryMap1);
					appraisal.setAttachListForFile(lst);	
					return appraisal;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfAppraise(Map)",e);
		}
		return null;
	}

	@Override
	public boolean insertAttachCache(AattachCacheDto attachCacheDto,String type) {
		if(attachCacheDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachtype",attachCacheDto.getAttachtype());
			params.put("attachname",attachCacheDto.getAttachname());
			params.put("attachpath",attachCacheDto.getAttachpath());
			params.put("image",attachCacheDto.getImage());
			params.put("cmis30id",attachCacheDto.getCmis30id());
			params.put("discode",attachCacheDto.getDiscode());
			params.put("attachtypeid",attachCacheDto.getAttachtypeid());
			try{
				String newId = baseInforManagerExt.queryProKey("A_ATTACH");
				params.put("attachid", newId);
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertattach.addCache");
				attachCacheDto.setAttachid(newId);
				latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(), newId, attachCacheDto, "A_ATTACH", type, sqlElement);
				return true;
			}catch(Exception e){
				logger.error("insertAttachCache(AattachCacheDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}



	@Override
	public String insertSelfAppCache(AapprasialCacheDto apprasialCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisaltypeid",apprasialCacheDto.getAppraisaltypeid());
		params.put("appraiserrid",apprasialCacheDto.getAppraiserrid());
		params.put("apprasial",apprasialCacheDto.getApprasial());
		params.put("studentid",apprasialCacheDto.getStudentid());
		params.put("appraseridentify",apprasialCacheDto.getAppraseridentify());
		params.put("appraser",apprasialCacheDto.getAppraser());
		params.put("edu_id",apprasialCacheDto.getEdu_id());
		params.put("semesterid",apprasialCacheDto.getSemesterid());
		params.put("cmis30id",apprasialCacheDto.getCmis30id());
		params.put("discode",apprasialCacheDto.getDiscode());
		try{
			String personid = "1";
			//java.util.Date valueOf = java.sql.Date.valueOf(apprasialCacheDto.getSigndate());
			params.put("signdate",apprasialCacheDto.getSigndate());
			String newId = baseInforManagerExt.queryProKey("A_APPRASIAL");
			params.put("apprasialid", newId);
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisal.addCache1");
			apprasialCacheDto.setApprasialid(newId);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(apprasialCacheDto.getEdu_id(), apprasialCacheDto.getSemesterid(),
					apprasialCacheDto.getAppraisaltypeid(), personid,
					apprasialCacheDto.getEdu_id(), newId, apprasialCacheDto, sqlElement,apprasialCacheDto.getSigndate());
			return newId;
		}catch(Exception e){
			logger.error("insertSelfAppCache(AapprasialCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}



	@Override
	public boolean insertPersonalityCache(ApersonalityCacheDto personalityCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("indexid",personalityCacheDto.getIndexid());
		params.put("developmentrd",personalityCacheDto.getDevelopmentrd());
		params.put("studentid",personalityCacheDto.getStudentid());
		params.put("edu_id",personalityCacheDto.getEdu_id());
		params.put("semesterid",personalityCacheDto.getSemesterid());
		params.put("cmis30id",personalityCacheDto.getCmis30id());
		params.put("discode",personalityCacheDto.getDiscode());
		try{
			//java.util.Date valueOf = java.sql.Date.valueOf(personalityCacheDto.getSigndate());
			params.put("signdate",personalityCacheDto.getSigndate());
			String newId = baseInforManagerExt.queryProKey("A_PERSONALITY");
			params.put("baseid", newId);
			ISqlElement sqlElement=this.processSql(params,"Personality.insertPersonality.addCache1");
			personalityCacheDto.setBaseid(newId);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(personalityCacheDto.getEdu_id(), personalityCacheDto.getSemesterid(), 
					personalityCacheDto.getAppraisaltypeid(), personalityCacheDto.getAppraseridentify(), 
					personalityCacheDto.getEdu_id(), newId, personalityCacheDto, sqlElement,personalityCacheDto.getSigndate());
			return true;
		}catch(Exception e){
			logger.error("insertPersonalityCache(ApersonalityCacheDto)",e);
			throw new ManagerException(e);
		}
	}



	@Override
	public boolean updatePersonalityCache(ApersonalityCacheDto personalityCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("baseid",personalityCacheDto.getBaseid());
		params.put("developmentrd",personalityCacheDto.getDevelopmentrd());
		try{
			//java.util.Date valueOf = java.sql.Date.valueOf(personalityCacheDto.getSigndate());
			params.put("signdate",personalityCacheDto.getSigndate());
			ISqlElement sqlElement=this.processSql(params,"Personality.updatePersonality.updateCache1");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(personalityCacheDto.getEdu_id(), personalityCacheDto.getSemesterid(),
					personalityCacheDto.getAppraisaltypeid(), personalityCacheDto.getAppraseridentify(), 
					personalityCacheDto.getEdu_id(), personalityCacheDto.getBaseid(), personalityCacheDto, sqlElement,personalityCacheDto.getSigndate());
			return true;
		}catch(Exception e){
			logger.error("updatePersonalityCache(ApersonalityCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	
}
