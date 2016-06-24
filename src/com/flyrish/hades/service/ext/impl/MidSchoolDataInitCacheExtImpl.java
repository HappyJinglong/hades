package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMidSchoolDataInitCacheExt;
import com.flyrish.hades.util.DataSource;

public class MidSchoolDataInitCacheExtImpl extends JdbcRootManager implements
		IMidSchoolDataInitCacheExt {
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}

	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}

	@Override
	@DataSource("read")
	public List<PartInfoCacheDto> queryPartInfoCacheDtosInDb(String discode) {
		Map<String,Object>queryMap=new HashMap<String,Object>();
		queryMap.put("discode",discode);
		try {
			return this.findList("MidSchoolDataInitCacheExtImpl.queryPartInfoCacheDtosInDb.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					partInfoCacheDto.setPart_id(rs.getString("part_id"));
					if(Constant.me_apprasialidentify.equals(rs.getString("write_man"))){
						partInfoCacheDto.setUserid(rs.getString("edu_id"));
					}else{
						partInfoCacheDto.setUserid(rs.getString("userid"));
					}
					partInfoCacheDto.setStudentid(rs.getString("studentid"));
					partInfoCacheDto.setTermid(rs.getString("termid"));
					partInfoCacheDto.setTopic(rs.getString("topic"));
					partInfoCacheDto.setPart_info(rs.getString("part_info"));
					partInfoCacheDto.setWrite_man(rs.getString("write_man"));
					partInfoCacheDto.setSigner_name(rs.getString("signer_name"));
					partInfoCacheDto.setTwo_part_id(rs.getString("two_part_id"));
					partInfoCacheDto.setSubject_id(rs.getString("subject_id"));
					partInfoCacheDto.setCreateDate(rs.getDate("CreateDate")==null?null:sdf.format(rs.getDate("CreateDate")));
					partInfoCacheDto.setIs_attachmen(rs.getString("is_attachmen"));
					partInfoCacheDto.setCmis30id(rs.getString("cmis30id"));
					partInfoCacheDto.setDiscode(rs.getString("discode"));
					partInfoCacheDto.setEdu_id(rs.getString("edu_id"));
					partInfoCacheDto.setKeyword(rs.getString("keyword"));
					partInfoCacheDto.setCooperation_man(rs.getString("cooperation_man"));
					partInfoCacheDto.setStartdate(rs.getDate("startdate")==null?null:sdf.format(rs.getDate("startdate")));
					partInfoCacheDto.setEnddate(rs.getDate("enddate")==null?null:sdf.format(rs.getDate("enddate")));
					partInfoCacheDto.setAddress(rs.getString("address"));
					partInfoCacheDto.setPartid(rs.getString("partid"));
					partInfoCacheDto.setEdittime(rs.getDate("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(partInfoCacheDto.getEdu_id(), partInfoCacheDto.getTermid(), partInfoCacheDto.getTwo_part_id(),partInfoCacheDto.getWrite_man(), partInfoCacheDto.getUserid(),partInfoCacheDto.getPart_id(),partInfoCacheDto);
						logger.error(partInfoCacheDto.getDiscode()+"正在导把初中数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("queryPartInfoCacheDtosInDb()",e);
		}
		return null;
	}

	@Override
	@DataSource("read")
	public List<AttachmentCacheDto> queryAttachmentCacheDtosInDb(final String discode) {
		Map<String,Object>queryMap=new HashMap<String,Object>();
		queryMap.put("discode",discode);
		try {
			return this.findList("MidSchoolDataInitCacheExtImpl.queryAttachmentCacheDtosInDb.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachmentCacheDto attachmentCacheDto = new AttachmentCacheDto();
					attachmentCacheDto.setAttachment_id(rs.getString("attachment_id"));
					attachmentCacheDto.setPart_id(rs.getString("part_id"));
					attachmentCacheDto.setAttachment_name(rs.getString("attachment_name"));
					attachmentCacheDto.setAttachment_path(rs.getString("attachment_path"));
					attachmentCacheDto.setCmis30id(rs.getString("cmis30id"));
					attachmentCacheDto.setDiscode(rs.getString("discode"));
					attachmentCacheDto.setPartid(rs.getString("partid"));
					try{
						logger.error(attachmentCacheDto.getDiscode()+"正在导把初中附件导入到缓存中...+第"+arg1+"条");
						latestEvaluationRecordExt.addAttachFileInCache(attachmentCacheDto.getPart_id(),attachmentCacheDto.getAttachment_id(),attachmentCacheDto,Constant.CZ_attach);
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("queryAttachmentCacheDtosInDb()",e);
		}
		return null;
	}

}
