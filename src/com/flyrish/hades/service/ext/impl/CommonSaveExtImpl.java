package com.flyrish.hades.service.ext.impl;

import java.util.HashMap;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.service.ext.ICommonSaveExt;
import com.flyrish.hades.service.ext.ICommonTwoLevelSaveExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;

public class CommonSaveExtImpl extends JdbcRootManager implements ICommonSaveExt {
	
	private ICommonTwoLevelSaveExt  commonTwoLevelSaveExt;
	
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}

	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}

	public ICommonTwoLevelSaveExt getCommonTwoLevelSaveExt() {
		return commonTwoLevelSaveExt;
	}

	public void setCommonTwoLevelSaveExt(
			ICommonTwoLevelSaveExt commonTwoLevelSaveExt) {
		this.commonTwoLevelSaveExt = commonTwoLevelSaveExt;
	}

	public void saveCommonAppraise(String apprasialid, String appraisaltypeid,
			String apprasial, String levelcode) {
		//进行根据学段及评价类型进行分发处理
		//commonTwoLevelSaveExt.saveCommonAppraise(apprasialid,appraisaltypeid,apprasial,levelcode);
		
//		if(NestUtil.isEmpty(levelcode) || NestUtil.isEmpty(apprasialid));
		//高中
		if(Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_GZ) ||
				Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("apprasial", apprasial);
			params.put("apprasialid", apprasialid);
			params.put("appraisaltypeid", appraisaltypeid);
			
			
			//更新高中评价表中的评价内容
			updateAppraise( apprasialid,appraisaltypeid,apprasial);
			//更新高中记录袋表中的评价内容
			updateRecordpackage( apprasialid,appraisaltypeid,apprasial);
			//更新高中个性与发展_基本情况表中的评价内容
			updatePersonality( apprasialid,appraisaltypeid,apprasial);
			//更新班主任评语
			updateAssess( apprasialid,appraisaltypeid,apprasial);
			//更新学业成就_学科学习过程记录
			updateLearnprocessWorks( apprasialid,appraisaltypeid,apprasial);
			//更新学业成就_课程评语
			updateLearnprocessAppraisal( apprasialid,appraisaltypeid,apprasial);
			//更新综合实践活动
			updatePractices( apprasialid,appraisaltypeid,apprasial);
			//更新综合实践活动--自我评价
			updatePracticeSelfAppraisal( apprasialid,appraisaltypeid,apprasial);
			
			
			
			
		}
		//初中
		else if(Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			
			
			//更新初中自我评价
			updateMiddleSchoolAppraiseInfo( apprasialid,appraisaltypeid,apprasial);
			
			
			if(appraisaltypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
				//更新班主任评语
				updateAssess( apprasialid,appraisaltypeid,apprasial);
				
			}
			
			//更新主题
			//更新关键词
			//更新合作人
			//更新地址
			updateOthereInfo( apprasialid,appraisaltypeid,apprasial);
			
			
			
			/*if(appraisaltypeid.equals(Constant.ACTIVITY_BASEINFO_1)){
				updateKeyword( apprasialid,appraisaltypeid,apprasial);
				updateCooperationMan( apprasialid,appraisaltypeid,apprasial);
			}
			if(appraisaltypeid.equals(Constant.ACTIVITY_BASEINFO_2)){
				updateAddress( apprasialid,appraisaltypeid,apprasial);
			}*/
			
		}
	}
	
	//更新高中评价表中的评价内容
	private void updateAppraise(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		params.put("appraisaltypeid", appraisaltypeid);
		
		if( appraisaltypeid.equals(Constant.TYPE_START_ZWPJ) || 
			appraisaltypeid.equals(Constant.TYPE_START_WDFZMB) ||
			appraisaltypeid.equals(Constant.TYPE_END_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_END_WDFZMB) ||
			appraisaltypeid.equals(Constant.TYPE_END_JZPYQW) ||
			appraisaltypeid.equals(Constant.TYPE_SX_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_SX_TRPJ) ||
			appraisaltypeid.equals(Constant.TYPE_XY_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_XY) ||
			appraisaltypeid.equals(Constant.TYPE_HZ_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_HZ_TRPJ) ||
			appraisaltypeid.equals(Constant.TYPE_YDJK_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_YDJK_TRPJ) ||
			appraisaltypeid.equals(Constant.TYPE_SMYBX_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_SMYBX_TRPJ) ||
			appraisaltypeid.equals(Constant.TYPE_GXFZ_ZWPJ) ||
			appraisaltypeid.equals(Constant.TYPE_GXFZ_TRPJ) ||
			appraisaltypeid.equals(Constant.TYPE_GXFZGC) ||
			appraisaltypeid.equals(Constant.TYPE_GXFZ_CGZS)){
				
				
				try {
					String sqlStr="saveCommonAppraise.updateApprasial.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				} catch (Exception e) {
					logger.error("updateAppraise(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
	
	//更新高中记录袋表中的评价内容
	private void updateRecordpackage(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		params.put("appraisaltypeid", appraisaltypeid);
		
		if( appraisaltypeid.equals(Constant.TYPE_SXJLD) ||
			appraisaltypeid.equals(Constant.TYPE_HZ_JLD) ||
			appraisaltypeid.equals(Constant.TYPE_SMYBX_JLD)){
			
			try {
				String sqlStr="saveCommonAppraise.updateRecordpackage.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateRecordpackage(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	
	
	//更新高中个性与发展_基本情况表中的评价内容
	private void updatePersonality(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		if( appraisaltypeid.equals(Constant.TYPE_GXFZ_JBQK)){
			
			try {
				String sqlStr="saveCommonAppraise.updatePersonality.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updatePersonality(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	
	//更新班主任评语
	private void updateAssess(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		if( appraisaltypeid.equals(Constant.TYPE_END_BZRPY) ||
			appraisaltypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
			
			try {
				String sqlStr="saveCommonAppraise.updateAssess.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateAssess(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	
	
	//更新学业成就_学科学习过程记录
	private void updateLearnprocessWorks(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		if( appraisaltypeid.equals(Constant.TYPE_XY_GCJL)){
			
			try {
				String sqlStr="saveCommonAppraise.updateLearnprocessWorks.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateLearnprocessWorks(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	
	
	//更新学业成就_课程评语
	private void updateLearnprocessAppraisal(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		if( appraisaltypeid.equals(Constant.TYPE_KE_CHENG_PINGYU)){
			
			try {
				String sqlStr="saveCommonAppraise.updateLearnprocessAppraisal.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateLearnprocessAppraisal(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	
	
	//更新综合实践活动
	private void updatePractices(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		if(appraisaltypeid.contains("apprasial")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesA.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			}catch (Exception e){
				logger.error("updatePracticesA(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		
		if(appraisaltypeid.contains("item1")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesB.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			}catch (Exception e){
				logger.error("updatePracticesB(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		
		if(appraisaltypeid.contains("item2")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesC.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			}catch (Exception e){
				logger.error("updatePracticesC(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		if(appraisaltypeid.contains("item3")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			
			if(appraisaltypeId.equals(Constant.TYPE_YJXX)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD1.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
			
			if(appraisaltypeId.equals(Constant.TYPE_SQFU)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD2.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
				
			}
			
		}
		
		
		if(appraisaltypeid.contains("item4")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			
			if(appraisaltypeId.equals(Constant.TYPE_YJXX)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesE.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				}catch (Exception e){
					logger.error("updatePracticesE(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
			
			if(appraisaltypeId.equals(Constant.TYPE_SQFU) ||
			   appraisaltypeId.equals(Constant.TYPE_SHSJHD)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD1.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		
		if(appraisaltypeid.contains("item5")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesE.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			}catch (Exception e){
				logger.error("updatePracticesE(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}
	
	
	//更新综合实践活动--自我评价
	private void updatePracticeSelfAppraisal(String apprasialid, String appraisaltypeid,String apprasial){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", apprasialid);
		
		try {
			String sqlStr="saveCommonAppraise.updatePracticeSelfAppraisal.update";
			ISqlElement sqlelement=this.processSql(params, sqlStr);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			
		} catch (Exception e) {
			logger.error("updatePracticeSelfAppraisal(String,String,String)", e);
			throw new ManagerException(e);
		}
		
	}
	
	
	//更新初中评价内容
	private void updateMiddleSchoolAppraiseInfo(String apprasialid,String appraisaltypeid, String apprasial) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("partInfo", apprasial);
		params.put("partId", apprasialid);
		params.put("twoPartId", appraisaltypeid);
		
		//更新自我评价
		if(appraisaltypeid.equals(Constant.TERMS_BEGIN_ME) ||
		   appraisaltypeid.equals(Constant.DEVELOP_TARGET_ME) ||
		   appraisaltypeid.equals(Constant.TERMS_END_ME) ||
		   appraisaltypeid.equals(Constant.MORALITY_SELF_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.MORALITY_RECORD_BAG) ||		//35
		   appraisaltypeid.equals(Constant.WORKS_SELF_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.WORKS_SUBJECT_SHOW) ||		//43
		   appraisaltypeid.equals(Constant.COOPERATION_SELF_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.COOPERATION_RECORD_BAG) ||		//55
	   	   appraisaltypeid.equals(Constant.PLAY_SELF_APPRAISAL) || 
		   appraisaltypeid.equals(Constant.AESTHETIC_SELF_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.AESTHETIC_RECORD_BAG) ||			//75
		   appraisaltypeid.equals(Constant.ACTIVITY_BASEINFO_1) ||
		   appraisaltypeid.equals(Constant.ACTIVITY_SELF_APPRAISAL_1) ||
		   appraisaltypeid.equals(Constant.ACTIVITY_BASEINFO_2) ||
		   appraisaltypeid.equals(Constant.ACTIVITY_SELF_APPRAISAL_2) ||
		   appraisaltypeid.equals(Constant.INDIVIDUALITY_SELF_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.INDIVIDUALITY_RECORD_BAG)){
			
			
			try {
				String sqlStr="saveCommonAppraise.updateMiddleSchoolAppraiseInfo.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateMiddleSchoolAppraiseInfo(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		//更新家长评价
		if(appraisaltypeid.equals(Constant.PRAENTS_APPRAISAL_EXPECT) ||
		   appraisaltypeid.equals(Constant.MORALITY_PARENT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.WORKS_PARENT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.COOPERATION_PARENT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.PLAY_PARENT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.AESTHETIC_PARENT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.INDIVIDUALITY_PARENT_APPRAISAL)){
			
			try {
				String sqlStr="saveCommonAppraise.updateMiddleSchoolAppraiseInfo.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateMiddleSchoolAppraiseInfo(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		//更新任课老师评价
		if(appraisaltypeid.equals(Constant.MORALITY_TEACHER_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.WORKS_SUBJECT_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.COOPERATION_TEACHER_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.PLAY_TEACHER_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.AESTHETIC_TEACHER_APPRAISAL) ||
		   appraisaltypeid.equals(Constant.INDIVIDUALITY_TEACHER_APPRASIAL)){
			
			try {
				String sqlStr="saveCommonAppraise.updateMiddleSchoolAppraiseInfo.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
				
			} catch (Exception e) {
				logger.error("updateMiddleSchoolAppraiseInfo(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		
					
	}
	
	
	
	private void updateOthereInfo(String apprasialid,String appraisaltypeid, String apprasial) {
		
		String appraisaltypeId=appraisaltypeid.substring(0, 2);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("partId", apprasialid);
		params.put("twoPartId", appraisaltypeId);
		
		//更新主题 
		if(appraisaltypeid.contains("topic")){
			
			if(appraisaltypeId.equals(Constant.MORALITY_RECORD_BAG) ||
			   appraisaltypeId.equals(Constant.WORKS_SUBJECT_SHOW) ||
			   appraisaltypeId.equals(Constant.COOPERATION_RECORD_BAG) ||
			   appraisaltypeId.equals(Constant.AESTHETIC_RECORD_BAG) ||
			   appraisaltypeId.equals(Constant.ACTIVITY_BASEINFO_1) ||
			   appraisaltypeId.equals(Constant.ACTIVITY_BASEINFO_2) ||
			   appraisaltypeId.equals(Constant.INDIVIDUALITY_RECORD_BAG)){
			
			   params.put("topic", apprasial);
				
				try {
					String sqlStr="saveCommonAppraise.updateTopic.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				} catch (Exception e) {
					logger.error("updateTopic(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		//更新关键词
		if(appraisaltypeid.contains("keyword")){
			
			if(appraisaltypeId.equals(Constant.ACTIVITY_BASEINFO_1)){
				params.put("keyword", apprasial);
				
				try {
					String sqlStr="saveCommonAppraise.updateKeyword.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				} catch (Exception e) {
					logger.error("updateKeyword(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		//更新合伙人
		if(appraisaltypeid.contains("cooperationMan")){
			
			if(appraisaltypeId.equals(Constant.ACTIVITY_BASEINFO_1)){
				params.put("cooperationMan", apprasial);
				
				try {
					String sqlStr="saveCommonAppraise.updateCooperationMan.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				} catch (Exception e) {
					logger.error("updateCooperationMan(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		//更新地点
		if(appraisaltypeid.contains("address")){
			
			if(appraisaltypeId.equals(Constant.ACTIVITY_BASEINFO_2)){
				params.put("address", apprasial);
				try {
					String sqlStr="saveCommonAppraise.updateAddress.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
					
				} catch (Exception e) {
					logger.error("updateAddress(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		
		
		
			
	}

	@Override
	public void saveCommonAppraiseToCache(String apprasial,String stuEduid, String termid,String columNum, String appraseridentify, String appraiserrid,String proKey, String levelcode,String appraisaltypeid) {
		if(Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_GZ) ||
				Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			this.updateHighSchoolAppraiseInfoToCache(apprasial,stuEduid,termid,columNum, appraseridentify, appraiserrid, proKey, levelcode, appraisaltypeid);
		}else if(Integer.parseInt(levelcode) == (Constant.DICT_TYPE_LEVELCODE_CZ)){
			this.updateMiddleSchoolAppraiseInfoToCache(apprasial,stuEduid,termid,columNum, appraseridentify, appraiserrid, proKey, levelcode, appraisaltypeid);
		}
	}

	private void updateHighSchoolAppraiseInfoToCache(String apprasial,String stuEduid, String termid, String columNum,String appraseridentify, String appraiserrid, String proKey,String levelcode, String appraisaltypeid) {
		this.updateAppraiseToCache(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updateRecordpackageToCache(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updatePersonalityToCache( apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updateAssess( proKey,appraisaltypeid,apprasial);
		this.updateLearnprocessWorksToCache(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updateLearnprocessAppraisalToCache(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updatePracticesToCahce(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
		this.updatePracticeSelfAppraisalToCache(apprasial,stuEduid,termid, columNum, appraseridentify,appraiserrid, proKey, levelcode, appraisaltypeid);
	}

	private void updatePracticeSelfAppraisalToCache(String apprasial,
			String stuEduid, String termid, String columNum,
			String appraseridentify, String appraiserrid, String proKey,
			String levelcode, String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(Constant.TYPE_YJXX.equals(appraisaltypeid)
				||Constant.TYPE_SQFU.equals(appraisaltypeid)
				||Constant.TYPE_SHSJHD.equals(appraisaltypeid)){
			try {
				String[] proKeys = proKey.split("_");
				if(null!=proKeys && proKeys.length==2){
					params.put("apprasial", apprasial);
					params.put("apprasialid", proKeys[0]);
					String sqlStr="saveCommonAppraise.updatePracticeSelfAppraisal.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					ApracticeappraisalCacheDto acDto = new ApracticeappraisalCacheDto();
					acDto.setAppraisalid(proKeys[0]);
					acDto.setContent(apprasial);
					acDto.setPracticeid(proKeys[1]);
					latestEvaluationRecordExt.updateChildrenObjectlInCache(proKeys[1], proKeys[0], acDto, "a_practiceappraisal", columNum, sqlelement,null);
				}
			} catch (Exception e) {
				logger.error("updatePracticeSelfAppraisalToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	private void updatePracticesToCahce(String apprasial, String stuEduid,
			String termid, String columNum, String appraseridentify,
			String appraiserrid, String proKey, String levelcode,
			String appraisaltypeid) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		ApracticesCacheDto acDto = new ApracticesCacheDto();
		acDto.setPracticeid(proKey);
		if(appraisaltypeid.contains("apprasial")){
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesA.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				acDto.setItem1(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
			}catch (Exception e){
				logger.error("updatePracticesA(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		
		if(appraisaltypeid.contains("item1")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesB.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				acDto.setItem6(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			}catch (Exception e){
				logger.error("updatePracticesB(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		
		if(appraisaltypeid.contains("item2")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesC.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				acDto.setItem7(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			}catch (Exception e){
				logger.error("updatePracticesC(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
		if(appraisaltypeid.contains("item3")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			
			if(appraisaltypeId.equals(Constant.TYPE_YJXX)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD1.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					acDto.setItem2(apprasial);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
			
			if(appraisaltypeId.equals(Constant.TYPE_SQFU)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD2.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					acDto.setItem8(apprasial);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
				
			}
			
		}
		
		
		if(appraisaltypeid.contains("item4")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			
			if(appraisaltypeId.equals(Constant.TYPE_YJXX)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesE.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					acDto.setItem9(apprasial);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
					
				}catch (Exception e){
					logger.error("updatePracticesE(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
			
			if(appraisaltypeId.equals(Constant.TYPE_SQFU) ||
			   appraisaltypeId.equals(Constant.TYPE_SHSJHD)){
				try {
					String sqlStr="saveCommonAppraise.updatePracticesD1.update";
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					acDto.setItem2(apprasial);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
					
				}catch (Exception e){
					logger.error("updatePracticesD1(String,String,String)", e);
					throw new ManagerException(e);
				}
			}
		}
		
		
		if(appraisaltypeid.contains("item5")){
			
			String appraisaltypeId=appraisaltypeid.substring(0, 4);
			params.put("appraisaltypeid", appraisaltypeId);
			try {
				String sqlStr="saveCommonAppraise.updatePracticesE.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				acDto.setItem9(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			}catch (Exception e){
				logger.error("updatePracticesE(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	private void updateLearnprocessAppraisalToCache(String apprasial,
			String stuEduid, String termid, String columNum,
			String appraseridentify, String appraiserrid, String proKey,
			String levelcode, String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		
		if( appraisaltypeid.equals(Constant.TYPE_KE_CHENG_PINGYU)){
			
			try {
				String sqlStr="saveCommonAppraise.updateLearnprocessAppraisal.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				AlearnprocessAppraisalCacheDto acDto = new AlearnprocessAppraisalCacheDto();
				acDto.setAppraisalid(proKey);
				acDto.setAppraisal(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			} catch (Exception e) {
				logger.error("updateLearnprocessAppraisalToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	private void updateLearnprocessWorksToCache(String apprasial,
			String stuEduid, String termid, String columNum,
			String appraseridentify, String appraiserrid, String proKey,
			String levelcode, String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		
		if( appraisaltypeid.equals(Constant.TYPE_XY_GCJL)){
			
			try {
				String sqlStr="saveCommonAppraise.updateLearnprocessWorks.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				AlearnprocessWorksCacheDto acDto = new AlearnprocessWorksCacheDto();
				acDto.setWorkid(proKey);
				acDto.setProcessdesc(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			} catch (Exception e) {
				logger.error("updateLearnprocessWorksToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	private void updatePersonalityToCache(String apprasial, String stuEduid,
			String termid, String columNum, String appraseridentify,
			String appraiserrid, String proKey, String levelcode,
			String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		
		if( appraisaltypeid.equals(Constant.TYPE_GXFZ_JBQK)){
			
			try {
				String sqlStr="saveCommonAppraise.updatePersonality.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				ApersonalityCacheDto acDto = new ApersonalityCacheDto();
				acDto.setBaseid(proKey);
				acDto.setDevelopmentrd(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			} catch (Exception e) {
				logger.error("updatePersonalityToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
	}

	private void updateRecordpackageToCache(String apprasial, String stuEduid,
			String termid, String columNum, String appraseridentify,
			String appraiserrid, String proKey, String levelcode,
			String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		params.put("appraisaltypeid", appraisaltypeid);
		
		if( appraisaltypeid.equals(Constant.TYPE_SXJLD) ||
			appraisaltypeid.equals(Constant.TYPE_HZ_JLD) ||
			appraisaltypeid.equals(Constant.TYPE_SMYBX_JLD)){
			
			try {
				String sqlStr="saveCommonAppraise.updateRecordpackage.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				ArecordpackageCacheDto acDto = new ArecordpackageCacheDto();
				acDto.setRecordid(proKey);
				acDto.setContent(apprasial);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
			} catch (Exception e) {
				logger.error("updateRecordpackageToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
		
	}

	private void updateAppraiseToCache(String apprasial, String stuEduid,
			String termid, String columNum, String appraseridentify,
			String appraiserrid, String proKey, String levelcode,
			String appraisaltypeid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("apprasial", apprasial);
		params.put("apprasialid", proKey);
		params.put("appraisaltypeid", appraisaltypeid);
		if( appraisaltypeid.equals(Constant.TYPE_START_ZWPJ) || 
				appraisaltypeid.equals(Constant.TYPE_START_WDFZMB) ||
				appraisaltypeid.equals(Constant.TYPE_END_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_END_WDFZMB) ||
				appraisaltypeid.equals(Constant.TYPE_END_JZPYQW) ||
				appraisaltypeid.equals(Constant.TYPE_SX_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_SX_TRPJ) ||
				appraisaltypeid.equals(Constant.TYPE_XY_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_XY) ||
				appraisaltypeid.equals(Constant.TYPE_HZ_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_HZ_TRPJ) ||
				appraisaltypeid.equals(Constant.TYPE_YDJK_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_YDJK_TRPJ) ||
				appraisaltypeid.equals(Constant.TYPE_SMYBX_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_SMYBX_TRPJ) ||
				appraisaltypeid.equals(Constant.TYPE_GXFZ_ZWPJ) ||
				appraisaltypeid.equals(Constant.TYPE_GXFZ_TRPJ) ||
				appraisaltypeid.equals(Constant.TYPE_GXFZGC) ||
				appraisaltypeid.equals(Constant.TYPE_GXFZ_CGZS)){
			try {
				String sqlStr="saveCommonAppraise.updateApprasial.update";
				ISqlElement sqlelement=this.processSql(params, sqlStr);
				AapprasialCacheDto acDto = new AapprasialCacheDto();
				acDto.setApprasial(apprasial);
				acDto.setApprasialid(proKey);
				latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, acDto, sqlelement,null);
				
			} catch (Exception e) {
				logger.error("updateAppraiseToCache(String,String,String)", e);
				throw new ManagerException(e);
			}
		}
	}

	private void updateMiddleSchoolAppraiseInfoToCache(String apprasial,String stuEduid,
			String termid, String columNum, String appraseridentify,
			String appraiserrid, String proKey, String levelcode, String appraisaltypeid) {
			try {
				PartInfoCacheDto picDto = new PartInfoCacheDto();
				picDto.setPart_id(proKey);
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("partId", proKey);
				params.put("twoPartId", columNum);
				String sqlStr="";
				if(appraisaltypeid.contains("topic")){
					sqlStr="saveCommonAppraise.updateTopic.update";
					params.put("topic", apprasial);
					picDto.setTopic(apprasial);
				}else if(appraisaltypeid.contains("keyword")){
					sqlStr="saveCommonAppraise.updateKeyword.update";
					picDto.setKeyword(apprasial);
					params.put("keyword", apprasial);
				}else if(appraisaltypeid.contains("cooperationMan")){
					sqlStr="saveCommonAppraise.updateCooperationMan.update";
					picDto.setCooperation_man(apprasial);
					params.put("cooperationMan", apprasial);
				}else if(appraisaltypeid.contains("address")){
					sqlStr="saveCommonAppraise.updateAddress.update";
					picDto.setAddress(apprasial);
					params.put("address", apprasial);
				}else if(appraisaltypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
					this.updateAssess( proKey,appraisaltypeid,apprasial);
				}else{
					params.put("partInfo", apprasial);
					sqlStr="saveCommonAppraise.updateMiddleSchoolAppraiseInfo.update";
					picDto.setPart_info(apprasial);
				}
				if(!appraisaltypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
					ISqlElement sqlelement=this.processSql(params, sqlStr);
					latestEvaluationRecordExt.updateRecodeInCacheByProKey(stuEduid, termid, columNum, appraseridentify, appraiserrid, proKey, picDto, sqlelement,null);
				}
			} catch (Exception e) {
				logger.error("updateMiddleSchoolAppraiseInfoToCache(String,String,String,String,String,String,String,String)", e);
				throw new ManagerException(e);
			}
	}
	
	
	
	
	
	/*private void updateKeyword(String apprasialid,
			String appraisaltypeid, String apprasial) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("keyword", apprasial);
		params.put("partId", apprasialid);
		params.put("twoPartId", appraisaltypeid);
		
		try {
			String sqlStr="saveCommonAppraise.updateKeyword.update";
			ISqlElement sqlelement=this.processSql(params, sqlStr);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			
		} catch (Exception e) {
			logger.error("updateKeyword(String,String,String)", e);
			throw new ManagerException(e);
		}
	}
	
	
	
	private void updateCooperationMan(String apprasialid,
			String appraisaltypeid, String apprasial) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cooperationMan", apprasial);
		params.put("partId", apprasialid);
		params.put("twoPartId", appraisaltypeid);
		
		try {
			String sqlStr="saveCommonAppraise.updateCooperationMan.update";
			ISqlElement sqlelement=this.processSql(params, sqlStr);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			
		} catch (Exception e) {
			logger.error("updateCooperationMan(String,String,String)", e);
			throw new ManagerException(e);
		}
	}
	
	
	
	private void updateAddress(String apprasialid,
			String appraisaltypeid, String apprasial) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("address", apprasial);
		params.put("partId", apprasialid);
		params.put("twoPartId", appraisaltypeid);
		
		try {
			String sqlStr="saveCommonAppraise.updateAddress.update";
			ISqlElement sqlelement=this.processSql(params, sqlStr);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
			
		} catch (Exception e) {
			logger.error("updateAddress(String,String,String)", e);
			throw new ManagerException(e);
		}
	}*/
	
	
	
}
