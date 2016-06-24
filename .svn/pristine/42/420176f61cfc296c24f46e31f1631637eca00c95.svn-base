package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILearnprocessWorksServiceExt;


public class LearnprocessWorksServiceExtImpl extends JdbcRootManager implements ILearnprocessWorksServiceExt{
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	public IBaseInforManagerExt baseInforManagerExt;
	
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}

	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}

	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}

	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}

	public List selectLearnprocessWorks(final Map<String, Object> queryMap) {
		try {
			return this.findList("LearnprocessWorks.selectLearnprocessWork.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					LearnprocessWorksDto learnprocessWorksDto = new LearnprocessWorksDto();
					learnprocessWorksDto.setWorkid(rs.getString("workid"));
					learnprocessWorksDto.setSubject(rs.getString("subject"));
					learnprocessWorksDto.setStudentid(rs.getString("studentid"));
					learnprocessWorksDto.setSemesterid(rs.getString("semesterid"));
					learnprocessWorksDto.setProcessdesc(rs.getString("processdesc"));
					learnprocessWorksDto.setSigndate(rs.getDate("signdate"));
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("recordid", Integer.valueOf(rs.getString("workid")));
					queryMap1.put("cmis30id", queryMap.get("cmis30id"));
					queryMap1.put("discode", queryMap.get("discode"));
					queryMap1.put("attachtype", "4");
					List<AttachDto> lst= selectAttach(queryMap1);
					learnprocessWorksDto.setAttachListForFile(lst);	
					return learnprocessWorksDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectLearnprocessWorks(Map)",e);
		}
		return null;
	}
	
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
			logger.error("selectRecordpackage(Map)",e);
		}
		return null;
	}

	public String insertLearnprocessWorks(
			LearnprocessWorksDto learnprocessWorksDto, List<AttachDto> attachs) {
		if(learnprocessWorksDto!=null&&attachs!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("subject",learnprocessWorksDto.getSubject());
			params.put("semesterid",learnprocessWorksDto.getSemesterid());
			params.put("edu_id",learnprocessWorksDto.getEdu_id());
			params.put("discode",learnprocessWorksDto.getDiscode());
			params.put("cmis30id",learnprocessWorksDto.getCmis30id());
			params.put("studentid",learnprocessWorksDto.getStudentid());
			params.put("processdesc",learnprocessWorksDto.getProcessdesc());
			params.put("signdate",learnprocessWorksDto.getSigndate());
			try{
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.insertLearnprocessWork.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				Map<String, Object> queryMap = new HashMap<String, Object>();
				List lst = new ArrayList();
				lst = getJdbcTemplate().queryForList("select s_A_LEARNPROCESS_WORKS.CURRVAL from dual");
				String str = lst.get(0).toString().split("=")[1].split("}")[0];
				return str;
			}catch(Exception e){
				logger.error("insertLearnprocessWorks(LearnprocessWorksDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return null;
		}
	}

	public boolean updateLearnprocessWorks(
			LearnprocessWorksDto learnprocessWorksDto) {
		if(learnprocessWorksDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("processdesc",learnprocessWorksDto.getProcessdesc());
			params.put("subject",learnprocessWorksDto.getSubject());
			params.put("workid",learnprocessWorksDto.getWorkid());
			params.put("signdate",learnprocessWorksDto.getSigndate());
			try{
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.updateLearnprocessWork.update");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("updateLearnprocessWorks(LearnprocessWorksDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}

	public boolean insertAttach(AttachDto attachDto, String id) {
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
				logger.error("insertAttach(AttachDto，String)",e);
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

	public List selectAttachList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteLearnprocessWorks(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("recordid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.deletetLearnprocessWorks.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement1=this.processSql(params,"SelfAppManager.deletetSelfPackageAttach.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteLearnprocessWorks(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	
	public boolean deleteAttachCache(AattachCacheDto attachCacheDto,String type) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("attachid",attachCacheDto.getAttachid());
		try {
			if("1".equals(type)){
				type=Constant.TYPE_XY_GCJL;
			}
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deletetSelfAttach.deleteCache");
			latestEvaluationRecordExt.deleteAttachFileInCache(attachCacheDto.getAttachtypeid(), attachCacheDto.getAttachid(), "a_attach", type, sqlElement,AattachCacheDto.class);
			return true;
		} catch (Exception e) {
			logger.error("deleteAttachCache(AattachCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	@Override
	public boolean insertAttachCache(AattachCacheDto attachCacheDto,String type) {
		if(attachCacheDto!=null&&!StringUtil.isEmpty(attachCacheDto.getAttachtypeid())){
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
				latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(), newId, 
						attachCacheDto, "a_attach", type, sqlElement);
				return true;
			}catch(Exception e){
				logger.error("insertAttach(AttachDto，String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}

	@Override
	public String insertLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto,AattachCacheDto attachCacheDto,String type) {
		if(learnprocessWorksCacheDto!=null&&attachCacheDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("subject",learnprocessWorksCacheDto.getSubject());
			params.put("semesterid",learnprocessWorksCacheDto.getSemesterid());
			params.put("edu_id",learnprocessWorksCacheDto.getEdu_id());
			params.put("discode",learnprocessWorksCacheDto.getDiscode());
			params.put("cmis30id",learnprocessWorksCacheDto.getCmis30id());
			params.put("studentid",learnprocessWorksCacheDto.getStudentid());
			params.put("processdesc",learnprocessWorksCacheDto.getProcessdesc());
			try{
				//java.util.Date valueOf = java.sql.Date.valueOf(learnprocessWorksCacheDto.getSigndate());
				params.put("signdate",learnprocessWorksCacheDto.getSigndate());
				String newId = baseInforManagerExt.queryProKey("A_LEARNPROCESS_WORKS");
				params.put("workid", newId);
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.insertLearnprocessWork.addCache1");
				learnprocessWorksCacheDto.setWorkid(newId);
				latestEvaluationRecordExt.addRecodeInCacheByProKey(learnprocessWorksCacheDto.getEdu_id(), learnprocessWorksCacheDto.getSemesterid(), 
						type, learnprocessWorksCacheDto.getAppraseridentify(), learnprocessWorksCacheDto.getEdu_id(),
						newId, learnprocessWorksCacheDto, sqlElement,learnprocessWorksCacheDto.getSigndate());
				return newId;
			}catch(Exception e){
				logger.error("insertLearnprocessWorksCache(AlearnprocessWorksCacheDto,AattachCacheDto,String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return null;
		}
	}

	@Override
	public boolean updateLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto, String type) {
		if(learnprocessWorksCacheDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("processdesc",learnprocessWorksCacheDto.getProcessdesc());
			params.put("subject",learnprocessWorksCacheDto.getSubject());
			params.put("workid",learnprocessWorksCacheDto.getWorkid());
			try{
				//java.util.Date valueOf = java.sql.Date.valueOf(learnprocessWorksCacheDto.getSigndate());
				params.put("signdate",learnprocessWorksCacheDto.getSigndate());
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.updateLearnprocessWork.updateCache1");
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(learnprocessWorksCacheDto.getEdu_id(), learnprocessWorksCacheDto.getSemesterid(), 
						type, learnprocessWorksCacheDto.getAppraseridentify(), learnprocessWorksCacheDto.getEdu_id(),
						learnprocessWorksCacheDto.getWorkid(), learnprocessWorksCacheDto, sqlElement,learnprocessWorksCacheDto.getSigndate());
				return true;
			}catch(Exception e){
				logger.error("updateLearnprocessWorksCache(AlearnprocessWorksCacheDto,String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto,String type) {
		if(!StringUtil.isEmpty(learnprocessWorksCacheDto.getWorkid())){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("recordid",learnprocessWorksCacheDto.getWorkid());
			try{
				ISqlElement sqlElement=this.processSql(params,"LearnprocessWorks.deletetLearnprocessWorks.deleteCache");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(learnprocessWorksCacheDto.getEdu_id(), learnprocessWorksCacheDto.getSemesterid(),
						type, learnprocessWorksCacheDto.getAppraseridentify(), learnprocessWorksCacheDto.getEdu_id(), learnprocessWorksCacheDto.getWorkid(), sqlElement,AlearnprocessWorksCacheDto.class);
				ISqlElement sqlElement1=this.processSql(params,"SelfAppManager.deletetSelfPackageAttach.deleteCache");
				latestEvaluationRecordExt.deleteAttachFileAllByforeignKey(learnprocessWorksCacheDto.getWorkid(), "a_attach", type, sqlElement1,AattachCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteLearnprocessWorks(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
}
