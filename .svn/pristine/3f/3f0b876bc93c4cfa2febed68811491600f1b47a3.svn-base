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

import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IRecordPackageManagerExt;
import com.flyrish.hades.util.DataSource;

public class RecordPackageManagerExtImpl  extends JdbcRootManager implements IRecordPackageManagerExt{
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
	/**
	 * 获取记录袋数据
	 * @param Map查询参数
	 * @return
	 */
	public List selectRecordpackage(final Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectReportPackage.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					RecordPackageDto recordPackageDto = new RecordPackageDto();
					recordPackageDto.setRecordid(Integer.valueOf(rs.getString("recordid")));
					recordPackageDto.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
					recordPackageDto.setAppraseridentify(Integer.valueOf(rs.getString("appraseridentify")));
					recordPackageDto.setContent(rs.getString("content"));
					recordPackageDto.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
					recordPackageDto.setSigndate((Date) rs.getDate("signdate"));
					recordPackageDto.setSigner(rs.getString("signer"));
					recordPackageDto.setStudentid(Integer.valueOf(rs.getString("studentid")));
					AttachDto attachDto = new AttachDto();
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("recordid", Integer.valueOf(rs.getString("recordid")));
					queryMap1.put("cmis30id", queryMap.get("cmis30id"));
					queryMap1.put("discode", queryMap.get("discode"));
					queryMap1.put("attachtype", "1");
					List<AttachDto> lst= selectAttach(queryMap1);
					recordPackageDto.setAttachListForFile(lst);	
					return recordPackageDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectRecordpackage(Map)",e);
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
			logger.error("selectRecordpackage(Map)",e);
		}
		return null;
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
			logger.error("selectRecordpackage(Map)",e);
		}
		return null;
	}
	/**
	 * 增加记录袋
	 * @param recordPackageDto记录袋Dto,附件list
	 * @return
	 */
	public String insertRecordpackage(RecordPackageDto recordPackageDto,List<AttachDto> attachs) {
		if(recordPackageDto!=null&&attachs!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraisaltypeid",recordPackageDto.getAppraisaltypeid());
			params.put("appraseridentify",recordPackageDto.getAppraseridentify());
			params.put("cmis30id",recordPackageDto.getCmis30id());
			params.put("content",recordPackageDto.getContent());
			params.put("discode",recordPackageDto.getDiscode());
			params.put("edu_id",recordPackageDto.getEdu_id());
			params.put("semesterid",recordPackageDto.getSemesterid());
			params.put("signdate",recordPackageDto.getSigndate());
			params.put("signer",recordPackageDto.getSigner());
			params.put("studentid",recordPackageDto.getStudentid());
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertReportPackage.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				Map<String, Object> queryMap = new HashMap<String, Object>();
				List lst = new ArrayList();
				lst = getJdbcTemplate().queryForList("select s_A_RECORDPACKAGE.CURRVAL from dual");
				String str = lst.get(0).toString().split("=")[1].split("}")[0];
				return str;
			}catch(Exception e){
				logger.error("insertRecordpackage(RecordPackageDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return null;
		}
	}
	/**
	 * 修改记录袋
	 * @param recordPackageDto记录袋Dto
	 * @return
	 */
	public boolean updateRecordpackage(RecordPackageDto recordPackageDto) {
		if(recordPackageDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("content",recordPackageDto.getContent());
			params.put("signdate",recordPackageDto.getSigndate());
			params.put("recordid",recordPackageDto.getRecordid());
			params.put("appraseridentify",recordPackageDto.getAppraseridentify());
			try{
				ISqlElement sqlElement=this.processSql(params,"RecordPackageManager.doUpdate.update");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("updateRecordpackage(recordPackageDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
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
	/**
	 * 删除附件
	 * @param 附件dto
	 * @return
	 */
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
	public boolean deleteSelfPackage(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("recordid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deletetSelfPackage.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement1=this.processSql(params,"SelfAppManager.deletetSelfPackageAttach.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPackage(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
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
						attachCacheDto, "A_ATTACH", type, sqlElement);
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
	public String insertRecordpackageCache(ArecordpackageCacheDto recordpackageCacheDto) {
		if(recordpackageCacheDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraisaltypeid",recordpackageCacheDto.getAppraisaltypeid());
			params.put("appraseridentify",recordpackageCacheDto.getAppraseridentify());
			params.put("cmis30id",recordpackageCacheDto.getCmis30id());
			params.put("content",recordpackageCacheDto.getContent());
			params.put("discode",recordpackageCacheDto.getDiscode());
			params.put("edu_id",recordpackageCacheDto.getEdu_id());
			params.put("semesterid",recordpackageCacheDto.getSemesterid());
			params.put("signer",recordpackageCacheDto.getSigner());
			params.put("studentid",recordpackageCacheDto.getStudentid());
			try{
				String personid = "1";
				//java.util.Date valueOf = java.sql.Date.valueOf(recordpackageCacheDto.getSigndate());
				params.put("signdate",recordpackageCacheDto.getSigndate());
				String newId = baseInforManagerExt.queryProKey("A_RECORDPACKAGE");
				params.put("recordid", newId);
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertReportPackage.addCache1");
				recordpackageCacheDto.setRecordid(newId);
				latestEvaluationRecordExt.addRecodeInCacheByProKey(recordpackageCacheDto.getEdu_id(), recordpackageCacheDto.getSemesterid(),
						recordpackageCacheDto.getAppraisaltypeid(), personid, 
						recordpackageCacheDto.getEdu_id(), newId, recordpackageCacheDto, sqlElement,recordpackageCacheDto.getSigndate());
				return newId;
			}catch(Exception e){
				logger.error("insertRecordpackageCache(ArecordpackageCacheDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return null;
		}
	}


	@Override
	public boolean updateRecordpackageCache(ArecordpackageCacheDto recordpackageCacheDto) {
		if(recordpackageCacheDto!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("content",recordpackageCacheDto.getContent());
			params.put("recordid",recordpackageCacheDto.getRecordid());
			params.put("appraseridentify",recordpackageCacheDto.getAppraseridentify());
			try{
				String personid = "1";
				//java.util.Date valueOf = java.sql.Date.valueOf(recordpackageCacheDto.getSigndate());
				params.put("signdate",recordpackageCacheDto.getSigndate());
				ISqlElement sqlElement=this.processSql(params,"RecordPackageManager.doUpdate.updateCache1");
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(recordpackageCacheDto.getEdu_id(),
						recordpackageCacheDto.getSemesterid(), recordpackageCacheDto.getAppraisaltypeid(),
						personid, recordpackageCacheDto.getEdu_id(), 
						recordpackageCacheDto.getRecordid(), recordpackageCacheDto, sqlElement,recordpackageCacheDto.getSigndate());
				return true;
			}catch(Exception e){
				logger.error("updateRecordpackageCache(ArecordpackageCacheDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}


	@Override
	public boolean deleteSelfPackageCache(ArecordpackageCacheDto recordpackageCacheDto) {
		if(!StringUtil.isEmpty(recordpackageCacheDto.getRecordid())){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("recordid",recordpackageCacheDto.getRecordid());
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deletetSelfPackage.deleteCache");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(recordpackageCacheDto.getEdu_id(), recordpackageCacheDto.getSemesterid(), 
						recordpackageCacheDto.getAppraisaltypeid(), recordpackageCacheDto.getAppraseridentify(),
						recordpackageCacheDto.getEdu_id(), recordpackageCacheDto.getRecordid(), sqlElement,ArecordpackageCacheDto.class);
				ISqlElement sqlElement1=this.processSql(params,"SelfAppManager.deletetSelfPackageAttach.deleteCache");
				latestEvaluationRecordExt.deleteAttachFileAllByforeignKey(recordpackageCacheDto.getRecordid(), "a_attach",recordpackageCacheDto.getAppraisaltypeid(), sqlElement1,AattachCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPackage(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}


	@Override
	public List<AttachDto> selectAttachListFromCache(String foreignKey,	String attachid) {
		try {
			AattachCacheDto attachFileInCaches = latestEvaluationRecordExt.queryAttachFileInCache(foreignKey, attachid, "A_ATTACH",AattachCacheDto.class);
			if(null!= attachFileInCaches){
				List<AttachDto>attachDtos = new ArrayList<AttachDto>();
				AttachDto attachDto = new AttachDto();
				attachDto.setFilename(attachFileInCaches.getAttachname());
				attachDto.setFilepath(attachFileInCaches.getAttachpath());
				attachDto.setAttachid(Integer.valueOf(attachFileInCaches.getAttachid()));
				attachDtos.add(attachDto);
				return attachDtos;
			}
		} catch (Exception e) {
			logger.error("selectAttachListFromCache(String,String)",e);
		}
		return null;
	}

}
