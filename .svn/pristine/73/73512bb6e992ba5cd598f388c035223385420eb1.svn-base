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
import org.nestframework.commons.utils.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.AttachmentDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoDto;
import com.flyrish.hades.exception.InValidInsertException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPartInfoServiceExt;
import com.flyrish.hades.util.DataSource;

public class PartInfoServiceExtImpl extends JdbcRootManager implements IPartInfoServiceExt{
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
	
	@DataSource("read")
	public List selectSelfPartInfo(Map<String, Object> queryMap) {
		try {
			return this.findList("PartInfo.selectPartInfo.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PartInfoDto partInfo=new PartInfoDto();
					partInfo.setCreateDate(rs.getDate("CreateDate"));
					partInfo.setIs_attachmen(rs.getString("is_attachmen"));
					partInfo.setPart_id(rs.getString("part_id"));
					partInfo.setPart_info(rs.getString("part_info"));
					partInfo.setSigner_name(rs.getString("signer_name"));
					partInfo.setStudentid(rs.getString("studentid"));
					partInfo.setSubject_id(rs.getString("subject_id"));
					partInfo.setTermid(rs.getString("termid"));
					partInfo.setTopic(rs.getString("topic"));
					partInfo.setTwo_part_id(rs.getString("two_part_id"));
					partInfo.setUserid(rs.getString("userid"));
					partInfo.setWrite_man(rs.getString("write_man"));
					return partInfo;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfPartInfo(Map)",e);
		}
		return null;
	}
	public List selectSelfPartInfoWithatt(Map<String, Object> queryMap) {
		try {
			return this.findList("PartInfo.selectPartInfo.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PartInfoDto partInfo=new PartInfoDto();
					partInfo.setCreateDate(rs.getDate("CreateDate"));
					partInfo.setIs_attachmen(rs.getString("is_attachmen"));
					partInfo.setPart_id(rs.getString("part_id"));
					partInfo.setPart_info(rs.getString("part_info"));
					partInfo.setSigner_name(rs.getString("signer_name"));
					partInfo.setStudentid(rs.getString("studentid"));
					partInfo.setSubject_id(rs.getString("subject_id"));
					partInfo.setTermid(rs.getString("termid"));
					partInfo.setTopic(rs.getString("topic"));
					partInfo.setTwo_part_id(rs.getString("two_part_id"));
					partInfo.setUserid(rs.getString("userid"));
					partInfo.setWrite_man(rs.getString("write_man"));
					AttachmentDto attachmentDto = new AttachmentDto();
					Map<String, Object> queryMap1 = new HashMap();
					queryMap1.put("part_id", Integer.valueOf(rs.getString("part_id")));
//					queryMap1.put("discode", Integer.valueOf(rs.getString("discode")));
//					queryMap1.put("cmis30id", Integer.valueOf(rs.getString("cmis30id")));
					List<AttachmentDto> lst = selectAttachment(queryMap1);
					partInfo.setAttachListForFile(lst);	
					return partInfo;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfPartInfo(Map)",e);
		}
		return null;
	}
	
	public String insertSelfPartInfoWith(PartInfoDto partInfo) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("createdate",partInfo.getCreateDate());
		params.put("is_attachmen",partInfo.getIs_attachmen());
		params.put("part_info",partInfo.getPart_info());
		params.put("signer_name",partInfo.getSigner_name());
		params.put("studentid",partInfo.getStudentid());
		params.put("subject_id",partInfo.getSubject_id());
		params.put("termid",partInfo.getTermid());
		params.put("topic",partInfo.getTopic());
		params.put("two_part_id",partInfo.getTwo_part_id());
		params.put("userid",partInfo.getUserid());
		params.put("write_man",partInfo.getWrite_man());
		params.put("cmis30id",partInfo.getCmis30id());
		params.put("discode",partInfo.getDiscode());
		params.put("edu_id",partInfo.getEdu_id());
		params.put("address",partInfo.getAddress());
		params.put("cooperation_man",partInfo.getCooperation_man());
		params.put("startdate",partInfo.getStartdate());
		params.put("enddate",partInfo.getEnddate());
		params.put("keyword",partInfo.getKeyword());
		try{
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_PARTINFO.NEXTVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			params.put("part_id",str);
			ISqlElement sqlElement=this.processSql(params,"PartInfo.insertPartInfo.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return str;
		}catch(Exception e){
			logger.error("PartInfoDto(PartInfoDto)",e);
		/*	if(e.getMessage().contains(Constant.MISSING_ONLY_MSG)){
				throw new InValidInsertException();
			}else{*/
				throw new ManagerException(e);
		/*	}*/
		}
	}
	
	
	public String insertSelfPartInfo(PartInfoDto partInfo) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("createdate",partInfo.getCreateDate());
		params.put("is_attachmen",partInfo.getIs_attachmen());
		params.put("part_info",partInfo.getPart_info());
		params.put("signer_name",partInfo.getSigner_name());
		params.put("studentid",partInfo.getStudentid());
		params.put("subject_id",partInfo.getSubject_id());
		params.put("termid",partInfo.getTermid());
		params.put("topic",partInfo.getTopic());
		params.put("two_part_id",partInfo.getTwo_part_id());
		params.put("userid",partInfo.getUserid());
		params.put("write_man",partInfo.getWrite_man());
		params.put("cmis30id",partInfo.getCmis30id());
		params.put("discode",partInfo.getDiscode());
		params.put("edu_id",partInfo.getEdu_id());
		try{
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_PARTINFO.NEXTVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			params.put("part_id",str);
			ISqlElement sqlElement=this.processSql(params,"PartInfo.insertPartInfoWith.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return str;
		}catch(Exception e){
			logger.error("insertSelfPartInfo(PartInfoDto)",e);
			throw new ManagerException(e);
		}
	}
	
	/**
	 * 修改初中评价内容
	 * @param list
	 * @return
	 */
	public boolean updateSelfPartInfo(PartInfoDto partInfo) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("part_info",partInfo.getPart_info());
		params.put("part_id",partInfo.getPart_id());
		params.put("createdate",partInfo.getCreateDate());
		if(!StringUtil.isEmpty(partInfo.getTopic())){
			params.put("topic",partInfo.getTopic());
		}
		if(!StringUtil.isEmpty(partInfo.getSubject_id())){
			params.put("subject_id",partInfo.getSubject_id());
		}
		try{
			ISqlElement sqlElement=this.processSql(params,"PartInfo.updatePartInfo.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPartInfo(PartInfoDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	public boolean insertAttachment(AttachmentDto attachment, String id) {
		if(attachment!=null&&!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachment_name",attachment.getAttachment_name());
			params.put("attachment_path",attachment.getAttachment_path());
			params.put("part_id",id);
			params.put("cmis30id",attachment.getCmis30id());
			params.put("discode",attachment.getDiscode());
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.insertAttachment.add");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("insertAttachment(RecordPackageDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	
	}

	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return
	 */
	public List selectAttachment(Map<String, Object> queryMap) {
		try {
			return this.findList("PartInfo.selectAttachment.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachmentDto attachmentDto = new AttachmentDto();
					attachmentDto.setAttachment_id(rs.getString("attachment_id"));
					attachmentDto.setAttachment_name(rs.getString("attachment_name"));
					attachmentDto.setAttachment_path(rs.getString("attachment_path"));
					attachmentDto.setPart_id(rs.getString("part_id"));
					return attachmentDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectAttachment(Map)",e);
		}
		return null;
	}

	public boolean deletePartinfo(String id,String cid,String dcode) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",id);
			params.put("cmis30id",cid);
			params.put("discode",dcode);
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deletePartInfo.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deletePartinfo(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	public boolean deleteAttachment(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachment_id",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deleteAttachment.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteAttachment(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	public List selectAttachmentList(Map<String, Object> queryMap) {
		try {
			return this.findList("PartInfo.selectAttachmentList.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachmentDto attachDto = new AttachmentDto();
					attachDto.setAttachment_name(rs.getString("attachment_name"));
					attachDto.setAttachment_path(rs.getString("attachment_path"));
					attachDto.setPart_id(rs.getString("part_id"));
					attachDto.setAttachment_id(rs.getString("attachment_id"));
					return attachDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectAttachmentList(Map)",e);
		}
		return null;
	}

	public boolean deleteAttach(String id,String cid,String dcode) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",id);
			params.put("cmis30id",cid);
			params.put("discode",dcode);
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deleteAttachmentwith.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement1=this.processSql(params,"PartInfo.deletePartInfo.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteAttachment(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	public List selectSelfPartInfoZonghe(Map<String, Object> queryMap) {
		try {
			return this.findList("PartInfo.selectPartInfoZh.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PartInfoDto partInfo=new PartInfoDto();
					partInfo.setCreateDate(rs.getDate("CreateDate"));
					partInfo.setIs_attachmen(rs.getString("is_attachmen"));
					partInfo.setPart_id(rs.getString("part_id"));
					partInfo.setPart_info(rs.getString("part_info"));
					partInfo.setSigner_name(rs.getString("signer_name"));
					partInfo.setStudentid(rs.getString("studentid"));
					partInfo.setSubject_id(rs.getString("subject_id"));
					partInfo.setTermid(rs.getString("termid"));
					partInfo.setTopic(rs.getString("topic"));
					partInfo.setTwo_part_id(rs.getString("two_part_id"));
					partInfo.setUserid(rs.getString("userid"));
					partInfo.setWrite_man(rs.getString("write_man"));
					partInfo.setAddress(rs.getString("address"));
					partInfo.setKeyword(rs.getString("keyword"));
					partInfo.setCooperation_man(rs.getString("cooperation_man"));
					partInfo.setStartdate(rs.getDate("startdate"));
					partInfo.setEnddate(rs.getDate("enddate"));
					AttachmentDto attachmentDto = new AttachmentDto();
					Map<String, Object> queryMap1 = new HashMap();
					queryMap1.put("part_id", Integer.valueOf(rs.getString("part_id")));
					queryMap1.put("discode", Integer.valueOf(rs.getString("discode")));
					queryMap1.put("cmis30id", Integer.valueOf(rs.getString("cmis30id")));
					List<AttachmentDto> lst = selectAttachment(queryMap1);
					partInfo.setAttachListForFile(lst);	
					return partInfo;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfPartInfo(Map)",e);
		}
		return null;
	}
	public boolean updateSelfPartInfoZh(PartInfoDto partInfo) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("part_info",partInfo.getPart_info());
		params.put("part_id",partInfo.getPart_id());
		params.put("createdate",partInfo.getCreateDate());
		params.put("address",partInfo.getAddress());
		params.put("cooperation_man",partInfo.getCooperation_man());
		params.put("startdate",partInfo.getStartdate());
		params.put("enddate",partInfo.getEnddate());
		params.put("keyword",partInfo.getKeyword());
		
		if(!StringUtil.isEmpty(partInfo.getTopic())){
			params.put("topic",partInfo.getTopic());
		}
		if(!StringUtil.isEmpty(partInfo.getSubject_id())){
			params.put("subject_id",partInfo.getSubject_id());
		}
		try{
			ISqlElement sqlElement=this.processSql(params,"PartInfo.updatePartInfoZh.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPartInfoZh(PartInfoDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	@DataSource("read")
	public String getSectionCode(Map<String, Object> params) {
		try {
			List<String> sectionCodes = this.findList("PartInfo.getSectionCode.select", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("sectionCode");
				}
			});
			if(null!=sectionCodes && sectionCodes.size()>0){
				return sectionCodes.get(0);
			}
		} catch (Exception e) {
			logger.error("getSectionCode(Map)",e);
		}
		return null;
	}
	@Override
	public String insertSelfPartInfoCache(PartInfoCacheDto partInfoCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("createdate",partInfoCacheDto.getCreateDate());
		params.put("is_attachmen",partInfoCacheDto.getIs_attachmen());
		params.put("part_info",partInfoCacheDto.getPart_info());
		params.put("signer_name",partInfoCacheDto.getSigner_name());
		params.put("studentid",partInfoCacheDto.getStudentid());
		params.put("subject_id",partInfoCacheDto.getSubject_id());
		params.put("termid",partInfoCacheDto.getTermid());
		params.put("topic",partInfoCacheDto.getTopic());
		params.put("two_part_id",partInfoCacheDto.getTwo_part_id());
		params.put("userid",partInfoCacheDto.getUserid());
		params.put("write_man",partInfoCacheDto.getWrite_man());
		params.put("cmis30id",partInfoCacheDto.getCmis30id());
		params.put("discode",partInfoCacheDto.getDiscode());
		params.put("edu_id",partInfoCacheDto.getEdu_id());
		try{
			//java.util.Date valueOf = java.sql.Date.valueOf(partInfoCacheDto.getCreateDate());
			params.put("createdate",partInfoCacheDto.getCreateDate());
			String newId = baseInforManagerExt.queryProKey("PARTINFO");
			params.put("part_id",newId);
			ISqlElement sqlElement=this.processSql(params,"PartInfo.insertPartInfoWith.addCache1");
			partInfoCacheDto.setPart_id(newId);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), 
					partInfoCacheDto.getTwo_part_id(), partInfoCacheDto.getWrite_man(), partInfoCacheDto.getEdu_id(),
					newId, partInfoCacheDto, sqlElement,partInfoCacheDto.getCreateDate());
			return newId;
		}catch(Exception e){
			logger.error("insertSelfPartInfoCache(PartInfoCacheDto)",e);
			throw new ManagerException(e);
		}
	}
	@Override
	public boolean updateSelfPartInfoCache(PartInfoCacheDto partInfoCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("part_info",partInfoCacheDto.getPart_info());
		params.put("part_id",partInfoCacheDto.getPart_id());
		if(!StringUtil.isEmpty(partInfoCacheDto.getTopic())){
			params.put("topic",partInfoCacheDto.getTopic());
		}
		if(!StringUtil.isEmpty(partInfoCacheDto.getSubject_id())){
			params.put("subject_id",partInfoCacheDto.getSubject_id());
		}
		try{
			//java.util.Date valueOf = java.sql.Date.valueOf(partInfoCacheDto.getCreateDate());
			params.put("createdate",partInfoCacheDto.getCreateDate());
			ISqlElement sqlElement=this.processSql(params,"PartInfo.updatePartInfo.updateCache1");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), 
					partInfoCacheDto.getTwo_part_id(), partInfoCacheDto.getWrite_man(), partInfoCacheDto.getEdu_id(), partInfoCacheDto.getPart_id()
					, partInfoCacheDto, sqlElement,partInfoCacheDto.getCreateDate());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPartInfoCache(PartInfoCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	@Override
	public boolean updateSelfPartInfoZhCache(PartInfoCacheDto partInfoCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("part_info",partInfoCacheDto.getPart_info());
		params.put("part_id",partInfoCacheDto.getPart_id());
		params.put("address",partInfoCacheDto.getAddress());
		params.put("cooperation_man",partInfoCacheDto.getCooperation_man());
		params.put("keyword",partInfoCacheDto.getKeyword());
		
		if(!StringUtil.isEmpty(partInfoCacheDto.getTopic())){
			params.put("topic",partInfoCacheDto.getTopic());
		}
		if(!StringUtil.isEmpty(partInfoCacheDto.getSubject_id())){
			params.put("subject_id",partInfoCacheDto.getSubject_id());
		}
		try{
			/*java.util.Date valueOf = java.sql.Date.valueOf(partInfoCacheDto.getCreateDate());
			java.util.Date valueOf1 = java.sql.Date.valueOf(partInfoCacheDto.getStartdate());
			java.util.Date valueOf2 = java.sql.Date.valueOf(partInfoCacheDto.getEnddate());*/
			params.put("createdate",partInfoCacheDto.getCreateDate());
			params.put("startdate",partInfoCacheDto.getStartdate());
			params.put("enddate",partInfoCacheDto.getEnddate());
			ISqlElement sqlElement=this.processSql(params,"PartInfo.updatePartInfoZh.updateCache1");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), 
					partInfoCacheDto.getTwo_part_id(), partInfoCacheDto.getWrite_man(), partInfoCacheDto.getEdu_id(), partInfoCacheDto.getPart_id()
					, partInfoCacheDto, sqlElement,partInfoCacheDto.getEnddate());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPartInfoZh(PartInfoDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	@Override
	public String insertSelfPartInfoWithCache(PartInfoCacheDto partInfoCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("is_attachmen",partInfoCacheDto.getIs_attachmen());
		params.put("part_info",partInfoCacheDto.getPart_info());
		params.put("signer_name",partInfoCacheDto.getSigner_name());
		params.put("studentid",partInfoCacheDto.getStudentid());
		params.put("subject_id",partInfoCacheDto.getSubject_id());
		params.put("termid",partInfoCacheDto.getTermid());
		params.put("topic",partInfoCacheDto.getTopic());
		params.put("two_part_id",partInfoCacheDto.getTwo_part_id());
		params.put("userid",partInfoCacheDto.getUserid());
		params.put("write_man",partInfoCacheDto.getWrite_man());
		params.put("cmis30id",partInfoCacheDto.getCmis30id());
		params.put("discode",partInfoCacheDto.getDiscode());
		params.put("edu_id",partInfoCacheDto.getEdu_id());
		params.put("address",partInfoCacheDto.getAddress());
		params.put("cooperation_man",partInfoCacheDto.getCooperation_man());
		params.put("keyword",partInfoCacheDto.getKeyword());
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			/*java.util.Date valueOf = java.sql.Date.valueOf(partInfoCacheDto.getCreateDate());
			java.util.Date valueOf1 = java.sql.Date.valueOf(partInfoCacheDto.getStartdate()==""?sdf.format(new Date()):partInfoCacheDto.getStartdate());
			java.util.Date valueOf2 = java.sql.Date.valueOf(partInfoCacheDto.getEnddate()==""?sdf.format(new Date()):partInfoCacheDto.getEnddate());*/
			params.put("createdate",partInfoCacheDto.getCreateDate());
			params.put("startdate",partInfoCacheDto.getStartdate());
			params.put("enddate",partInfoCacheDto.getEnddate());
			String newId = baseInforManagerExt.queryProKey("PARTINFO");
			params.put("part_id",newId);
			ISqlElement sqlElement=this.processSql(params,"PartInfo.insertPartInfo.addCache1");
			partInfoCacheDto.setPart_id(newId);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), 
					partInfoCacheDto.getTwo_part_id(), partInfoCacheDto.getWrite_man(), partInfoCacheDto.getEdu_id(), 
					partInfoCacheDto.getPart_id(), partInfoCacheDto, sqlElement,partInfoCacheDto.getCreateDate());
			return newId;
		}catch(Exception e){
			logger.error("insertSelfPartInfoWithCache(PartInfoCacheDto)",e);
				throw new ManagerException(e);
		}
	}
	@Override
	public boolean deletePartinfoCache(PartInfoCacheDto partInfoCacheDto) {
		if(!StringUtil.isEmpty(partInfoCacheDto.getPart_id())){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",partInfoCacheDto.getPart_id());
			params.put("cmis30id",partInfoCacheDto.getCmis30id());
			params.put("discode",partInfoCacheDto.getDiscode());
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deletePartInfo.delete");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), partInfoCacheDto.getTwo_part_id(),
						partInfoCacheDto.getWrite_man(), partInfoCacheDto.getEdu_id(), partInfoCacheDto.getPart_id(), sqlElement,PartInfoCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deletePartinfoCache(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	@Override
	public boolean deleteAttachCache(PartInfoCacheDto pratInfoCacheDto) {
		if(!StringUtil.isEmpty(pratInfoCacheDto.getPart_id())){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("part_id",pratInfoCacheDto.getPart_id());
			params.put("cmis30id",pratInfoCacheDto.getCmis30id());
			params.put("discode",pratInfoCacheDto.getDiscode());
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deleteAttachmentwith.deleteCache");
				latestEvaluationRecordExt.deleteAttachFileAllByforeignKey(pratInfoCacheDto.getPart_id(), "ATTACHMENT", pratInfoCacheDto.getTwo_part_id(), sqlElement,AttachmentCacheDto.class);
				ISqlElement sqlElement1=this.processSql(params,"PartInfo.deletePartInfo.deleteCache");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(pratInfoCacheDto.getEdu_id(), pratInfoCacheDto.getTermid(), pratInfoCacheDto.getTwo_part_id(),
						pratInfoCacheDto.getWrite_man(), pratInfoCacheDto.getEdu_id(), pratInfoCacheDto.getPart_id(), sqlElement1,PartInfoCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteAttach(PartInfoCacheDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	@Override
	public boolean deleteAttachmentCache(String id, String rid, String type) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachment_id",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"PartInfo.deleteAttachment.deleteCache");
				latestEvaluationRecordExt.deleteAttachFileInCache(rid, id, "ATTACHMENT", type, sqlElement,AttachmentCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteAttachment(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	@Override
	public boolean insertAttachmentCache(AttachmentCacheDto attachmentCacheDto,String type) {
		if(attachmentCacheDto!=null&&!StringUtil.isEmpty(attachmentCacheDto.getPart_id())){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("attachment_name",attachmentCacheDto.getAttachment_name());
			params.put("attachment_path",attachmentCacheDto.getAttachment_path());
			params.put("part_id",attachmentCacheDto.getPart_id());
			params.put("cmis30id",attachmentCacheDto.getCmis30id());
			params.put("discode",attachmentCacheDto.getDiscode());
			try{
				String newId = baseInforManagerExt.queryProKey("ATTACHMENT");
				params.put("attachment_id", newId);
				ISqlElement sqlElement=this.processSql(params,"PartInfo.insertAttachment.addCache");
				attachmentCacheDto.setAttachment_id(newId);
				latestEvaluationRecordExt.addAttachFileInCache(attachmentCacheDto.getPart_id(), newId, 
						attachmentCacheDto, "ATTACHMENT", type, sqlElement);
				return true;
			}catch(Exception e){
				logger.error("insertAttachment(RecordPackageDto)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}
	@Override
	public List<AttachmentDto> selectAttachmentListFromCache(String foreignKey,	String attachment_id) {
		try {
			AttachmentCacheDto attachFileInCache = latestEvaluationRecordExt.queryAttachFileInCache(foreignKey, attachment_id, "ATTACHMENT",AttachmentCacheDto.class);
			if(null!=attachFileInCache){
				List<AttachmentDto>attachDtos = new ArrayList<AttachmentDto>();
				AttachmentDto attachDto = new AttachmentDto();
				attachDto.setAttachment_name(attachFileInCache.getAttachment_name());
				attachDto.setAttachment_path(attachFileInCache.getAttachment_path());
				attachDto.setPart_id(attachFileInCache.getPart_id());
				attachDto.setAttachment_id(attachFileInCache.getAttachment_id());
				attachDtos.add(attachDto);
				return attachDtos;
			}
		} catch (Exception e) {
			logger.error("selectAttachmentListFromCache(String,String)",e);
		}
		return null;
	}
}
