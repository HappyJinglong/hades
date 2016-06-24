package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessMarksCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.service.ext.IHighSchoolCacheExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;

public class HighSchoolCacheExtImpl extends JdbcRootManager implements IHighSchoolCacheExt{
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}

	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	/**
	 * 高中自我评价查询
	 */
	public List<AapprasialCacheDto> getAapprasialCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAapprasialCache.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					apprasialCacheDto.setApprasialid(rs.getString("apprasialid"));
					apprasialCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					apprasialCacheDto.setAppraiserrid(rs.getString("appraiserrid"));
					apprasialCacheDto.setSemesterid(rs.getString("semesterid"));
					apprasialCacheDto.setStudentid(rs.getString("studentid"));
					apprasialCacheDto.setAppraseridentify(rs.getString("appraseridentify"));
					apprasialCacheDto.setAppraser(rs.getString("appraser"));
					apprasialCacheDto.setApprasial(rs.getString("apprasial"));
					apprasialCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					apprasialCacheDto.setEdu_id(rs.getString("edu_id"));
					apprasialCacheDto.setDiscode(rs.getString("discode"));
					apprasialCacheDto.setCmis30id(rs.getString("cmis30id"));
					apprasialCacheDto.setPartid(rs.getString("partid"));
					apprasialCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					if (rs.getString("appraseridentify").equals("1")) {
						apprasialCacheDto.setAppraiserrid(rs.getString("edu_id"));
					}
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(apprasialCacheDto.getEdu_id(), apprasialCacheDto.getSemesterid(),apprasialCacheDto.getAppraisaltypeid(), 
								apprasialCacheDto.getAppraseridentify(), apprasialCacheDto.getAppraiserrid(), apprasialCacheDto.getApprasialid(), apprasialCacheDto);
						logger.error(apprasialCacheDto.getDiscode()+"正在导把高中评价表数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getAapprasialCache()",e);
		}
		return null;
	}
	
	@Override
	public void cleanDoubleAapprasialCacheAndDb() {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		try {
			 this.findList("HighSchoolCacheExtImpl.cleanDoubleAapprasialCacheAndDb.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
					apprasialCacheDto.setApprasialid(rs.getString("apprasialid"));
					apprasialCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					apprasialCacheDto.setAppraiserrid(rs.getString("appraiserrid"));
					apprasialCacheDto.setSemesterid(rs.getString("semesterid"));
					apprasialCacheDto.setAppraseridentify(rs.getString("appraseridentify"));
					apprasialCacheDto.setEdu_id(rs.getString("edu_id"));
					if ("1".equals(rs.getString("appraseridentify"))) {
						apprasialCacheDto.setAppraiserrid(rs.getString("edu_id"));
					}
					try{
						Map<String,Object>params=new HashMap<String,Object>();
						params.put("apprasialid",apprasialCacheDto.getApprasialid());
						ISqlElement sqlDemo=processSql(params,"HighSchoolCacheExtImpl.cleanDoubleAapprasialCacheAndDb.delete");
						logger.error(apprasialCacheDto.toString());
						latestEvaluationRecordExt.delRecodeInCacheByProKey(apprasialCacheDto.getEdu_id(),apprasialCacheDto.getSemesterid(), apprasialCacheDto.getAppraisaltypeid(),apprasialCacheDto.getAppraseridentify(),apprasialCacheDto.getAppraiserrid(),apprasialCacheDto.getApprasialid(),sqlDemo,apprasialCacheDto.getClass());
						logger.error(apprasialCacheDto.getDiscode()+"正在删除高中评价表数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						logger.error("cleanDoubleAapprasialCacheAndDb()",e);
					}
					return apprasialCacheDto;
				}
				
			});
		} catch (Exception e) {
			logger.error("cleanDoubleAapprasialCacheAndDb()",e);
		}
	}
	
	@Override
	public void cleanArecordpackageCache() {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		try {
			this.findList("HighSchoolCacheExtImpl.getArecordpackageCache.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					ArecordpackageCacheDto arecordpackageCacheDto = new ArecordpackageCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					arecordpackageCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					arecordpackageCacheDto.setAppraseridentify(rs.getString("appraseridentify"));
					arecordpackageCacheDto.setCmis30id(rs.getString("cmis30id"));
					arecordpackageCacheDto.setContent(rs.getString("content"));
					arecordpackageCacheDto.setDiscode(rs.getString("discode"));
					arecordpackageCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					arecordpackageCacheDto.setEdu_id(rs.getString("edu_id"));
					arecordpackageCacheDto.setPartid(rs.getString("partid"));
					arecordpackageCacheDto.setRecordid(rs.getString("recordid"));
					arecordpackageCacheDto.setSemesterid(rs.getString("semesterid"));
					arecordpackageCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					arecordpackageCacheDto.setSigner(rs.getString("signer"));
					arecordpackageCacheDto.setStudentid(rs.getString("studentid"));
					if ("1".equals(rs.getString("appraseridentify"))) {
						arecordpackageCacheDto.setAppraiserrid(rs.getString("edu_id"));
					}
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(arecordpackageCacheDto.getEdu_id(), arecordpackageCacheDto.getSemesterid(),arecordpackageCacheDto.getAppraisaltypeid()
								,"1", arecordpackageCacheDto.getEdu_id(), arecordpackageCacheDto.getRecordid(), arecordpackageCacheDto);
						logger.error(arecordpackageCacheDto.getDiscode()+"正在导把高中记录袋数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getArecordpackageCache()",e);
		}
	}

	/**
	 * 高中记录袋查询
	 */
	public List<ArecordpackageCacheDto> getArecordpackageCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getArecordpackageCache.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					ArecordpackageCacheDto arecordpackageCacheDto = new ArecordpackageCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					arecordpackageCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					arecordpackageCacheDto.setAppraseridentify(rs.getString("appraseridentify"));
					arecordpackageCacheDto.setCmis30id(rs.getString("cmis30id"));
					arecordpackageCacheDto.setContent(rs.getString("content"));
					arecordpackageCacheDto.setDiscode(rs.getString("discode"));
					arecordpackageCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					arecordpackageCacheDto.setEdu_id(rs.getString("edu_id"));
					arecordpackageCacheDto.setPartid(rs.getString("partid"));
					arecordpackageCacheDto.setRecordid(rs.getString("recordid"));
					arecordpackageCacheDto.setSemesterid(rs.getString("semesterid"));
					arecordpackageCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					arecordpackageCacheDto.setSigner(rs.getString("signer"));
					arecordpackageCacheDto.setStudentid(rs.getString("studentid"));
					if ("1".equals(rs.getString("appraseridentify"))) {
						arecordpackageCacheDto.setAppraiserrid(rs.getString("edu_id"));
					}
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(arecordpackageCacheDto.getEdu_id(), arecordpackageCacheDto.getSemesterid(),arecordpackageCacheDto.getAppraisaltypeid()
								,"1", arecordpackageCacheDto.getEdu_id(), arecordpackageCacheDto.getRecordid(), arecordpackageCacheDto);
						logger.error(arecordpackageCacheDto.getDiscode()+"正在导把高中记录袋数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getArecordpackageCache()",e);
		}
		return null;
	}
	
	/**
	 * 高中个性与发展_基本情况查询
	 */
	public List<ApersonalityCacheDto> getApersonalityCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getApersonalityCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					ApersonalityCacheDto apersonalityCacheDto = new ApersonalityCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					apersonalityCacheDto.setAppraisaltypeid(Constant.TYPE_GXFZ_JBQK);
					apersonalityCacheDto.setBaseid(rs.getString("BASEID"));
					apersonalityCacheDto.setCmis30id(rs.getString("CMIS30ID"));
					apersonalityCacheDto.setDevelopmentrd(rs.getString("DEVELOPMENTRD"));
					apersonalityCacheDto.setDiscode(rs.getString("DISCODE"));
					apersonalityCacheDto.setEdittime(rs.getString("EDITTIME")==null?null:sdf.format(rs.getDate("EDITTIME")));
					apersonalityCacheDto.setEdu_id(rs.getString("EDU_ID"));
					apersonalityCacheDto.setIndexid(rs.getString("INDEXID"));
					apersonalityCacheDto.setPartid(rs.getString("PARTID"));
					apersonalityCacheDto.setSemesterid(rs.getString("SEMESTERID"));
					apersonalityCacheDto.setSigndate(rs.getString("SIGNDATE")==null?null:sdf.format(rs.getDate("SIGNDATE")));
					apersonalityCacheDto.setStudentid(rs.getString("STUDENTID"));
					apersonalityCacheDto.setSystemtype(rs.getString("SYSTEMTYPE"));
					apersonalityCacheDto.setAppraiserrid(rs.getString("EDU_ID"));
					apersonalityCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(apersonalityCacheDto.getEdu_id(), apersonalityCacheDto.getSemesterid(), apersonalityCacheDto.getAppraisaltypeid(), 
								apersonalityCacheDto.getAppraseridentify(), apersonalityCacheDto.getAppraiserrid(), apersonalityCacheDto.getBaseid(), apersonalityCacheDto);
						logger.error(apersonalityCacheDto.getDiscode()+"正在导把高中个人基本情况导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高中学业成就_学科学习过程记录_作品相关信息介绍查询
	 */
	public List<AlearnprocessWorksCacheDto> getAlearnprocessWorksCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAlearnprocessWorksCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AlearnprocessWorksCacheDto alearnprocessWorksCacheDto = new AlearnprocessWorksCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					alearnprocessWorksCacheDto.setCmis30id(rs.getString("cmis30id"));
					alearnprocessWorksCacheDto.setDiscode(rs.getString("discode"));
					alearnprocessWorksCacheDto.setEdittime(rs.getDate("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					alearnprocessWorksCacheDto.setEdu_id(rs.getString("edu_id"));
					alearnprocessWorksCacheDto.setPartid(rs.getString("partid"));
					alearnprocessWorksCacheDto.setProcessdesc(rs.getString("processdesc"));
					alearnprocessWorksCacheDto.setSemesterid(rs.getString("semesterid"));
					alearnprocessWorksCacheDto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					alearnprocessWorksCacheDto.setStudentid(rs.getString("studentid"));
					alearnprocessWorksCacheDto.setSubject(rs.getString("subject"));
					alearnprocessWorksCacheDto.setWorkid(rs.getString("workid"));
					alearnprocessWorksCacheDto.setAppraiserrid(rs.getString("edu_id"));
					alearnprocessWorksCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(alearnprocessWorksCacheDto.getEdu_id(), alearnprocessWorksCacheDto.getSemesterid(), Constant.TYPE_XY_GCJL,
								alearnprocessWorksCacheDto.getAppraseridentify(), alearnprocessWorksCacheDto.getAppraiserrid(), alearnprocessWorksCacheDto.getWorkid(), alearnprocessWorksCacheDto);
						logger.error(alearnprocessWorksCacheDto.getDiscode()+"正在导把高中学科学习过程记录导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高中_学业成就_学科学习过程记录_成绩查询
	 */
	public List<AlearnprocessMarksCacheDto> getAlearnprocessMarksCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAlearnprocessMarksCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AlearnprocessMarksCacheDto alearnprocessMarksCacheDto = new AlearnprocessMarksCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					alearnprocessMarksCacheDto.setCmis30id(rs.getString("cmis30id"));
					alearnprocessMarksCacheDto.setDiscode(rs.getString("discode"));
					alearnprocessMarksCacheDto.setEdu_id(rs.getString("edu_id"));
					alearnprocessMarksCacheDto.setMarks(rs.getString("marks"));
					alearnprocessMarksCacheDto.setPartid(rs.getString("partid"));
					alearnprocessMarksCacheDto.setScoreid(rs.getString("scoreid"));
					alearnprocessMarksCacheDto.setScoretype(rs.getString("scoretype"));
					alearnprocessMarksCacheDto.setSemesterid(rs.getString("semesterid"));
					alearnprocessMarksCacheDto.setStudentid(rs.getString("studentid"));
					alearnprocessMarksCacheDto.setSubject(rs.getString("subject"));
					alearnprocessMarksCacheDto.setAppraiserrid(rs.getString("edu_id"));
					alearnprocessMarksCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(alearnprocessMarksCacheDto.getEdu_id(), alearnprocessMarksCacheDto.getSemesterid(), 
								Constant.TYPE_XY_GCJL, alearnprocessMarksCacheDto.getAppraseridentify(), alearnprocessMarksCacheDto.getAppraiserrid(), alearnprocessMarksCacheDto.getScoreid(), alearnprocessMarksCacheDto);
						logger.error(alearnprocessMarksCacheDto.getDiscode()+"正在导把高中高中_学业成就_学科学习过程记录_成绩查询导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_学业成就_学科学习过程记录_评语查询
	 */
	public List<AlearnprocessAppraisalCacheDto> getAlearnprocessAppraisalCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAlearnprocessAppraisalCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AlearnprocessAppraisalCacheDto alearnprocessAppraisalCacheDto = new AlearnprocessAppraisalCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					alearnprocessAppraisalCacheDto.setAppraisal(rs.getString("appraisal"));
					alearnprocessAppraisalCacheDto.setAppraisalid(rs.getString("appraisalid"));
					alearnprocessAppraisalCacheDto.setAppraiserrid(rs.getString("appraiserrid"));
					alearnprocessAppraisalCacheDto.setCmis30id(rs.getString("cmis30id"));
					alearnprocessAppraisalCacheDto.setDiscode(rs.getString("discode"));
					alearnprocessAppraisalCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					alearnprocessAppraisalCacheDto.setEdu_id(rs.getString("edu_id"));
					alearnprocessAppraisalCacheDto.setPartid(rs.getString("partid"));
					alearnprocessAppraisalCacheDto.setSemesterid(rs.getString("semesterid"));
					alearnprocessAppraisalCacheDto.setSign(rs.getString("sign"));
					alearnprocessAppraisalCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					alearnprocessAppraisalCacheDto.setStudentid(rs.getString("studentid"));
					alearnprocessAppraisalCacheDto.setSubject(rs.getString("subject"));
					alearnprocessAppraisalCacheDto.setAppraseridentify("3");
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(alearnprocessAppraisalCacheDto.getEdu_id(), alearnprocessAppraisalCacheDto.getSemesterid(), 
								Constant.TYPE_KE_CHENG_PINGYU, alearnprocessAppraisalCacheDto.getAppraseridentify(), alearnprocessAppraisalCacheDto.getAppraiserrid(), alearnprocessAppraisalCacheDto.getAppraisalid(),alearnprocessAppraisalCacheDto);
						logger.error(alearnprocessAppraisalCacheDto.getDiscode()+"正在导把高综_学业成就_学科学习过程记录_评语数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_综合实践活动
	 */
	public List<ApracticesCacheDto> getApracticesCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getApracticesCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					ApracticesCacheDto apracticesCacheDto = new ApracticesCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					apracticesCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					apracticesCacheDto.setCmis30id(rs.getString("cmis30id"));
					apracticesCacheDto.setDiscode(rs.getString("discode"));
					apracticesCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					apracticesCacheDto.setEdu_id(rs.getString("edu_id"));
					apracticesCacheDto.setItem1(rs.getString("item1"));
					apracticesCacheDto.setItem2(rs.getString("item2"));
					apracticesCacheDto.setItem3(rs.getString("item3"));
					apracticesCacheDto.setItem4(rs.getString("item4"));
					apracticesCacheDto.setItem5(rs.getString("item5"));
					apracticesCacheDto.setItem6(rs.getString("item6"));
					apracticesCacheDto.setItem7(rs.getString("item7"));
					apracticesCacheDto.setItem8(rs.getString("item8"));
					apracticesCacheDto.setItem9(rs.getString("item9"));
					apracticesCacheDto.setItem10(rs.getString("item10"));
					apracticesCacheDto.setItem11(rs.getString("item11"));
					apracticesCacheDto.setItem12(rs.getString("item12"));
					apracticesCacheDto.setItem13(rs.getString("item13"));
					apracticesCacheDto.setItem14(rs.getString("item14"));
					apracticesCacheDto.setItem15(rs.getString("item15"));
					apracticesCacheDto.setItem16(rs.getString("item16"));
					apracticesCacheDto.setItem17(rs.getString("item17"));
					apracticesCacheDto.setItem18(rs.getString("item18"));
					apracticesCacheDto.setItem19(rs.getString("item19"));
					apracticesCacheDto.setItem20(rs.getString("item20"));
					apracticesCacheDto.setPartid(rs.getString("partid"));
					apracticesCacheDto.setPracticeid(rs.getString("practiceid"));
					apracticesCacheDto.setSemesterid(rs.getString("semesterid"));
					apracticesCacheDto.setStudentid(rs.getString("studentid"));
					apracticesCacheDto.setSystemtype(rs.getString("systemtype"));
					apracticesCacheDto.setAppraiserrid(rs.getString("edu_id"));
					apracticesCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addRecodeInCacheByProKey(apracticesCacheDto.getEdu_id(), apracticesCacheDto.getSemesterid(),
								apracticesCacheDto.getAppraisaltypeid(), apracticesCacheDto.getAppraseridentify(), apracticesCacheDto.getAppraiserrid(), apracticesCacheDto.getPracticeid(), apracticesCacheDto);
						logger.error(apracticesCacheDto.getDiscode()+"正在导把高综_综合实践活动数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_综合实践活动_评价表
	 */
	public List<ApracticeappraisalCacheDto> getApracticeappraisalCache(final String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getApracticeappraisalCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					ApracticeappraisalCacheDto apracticeappraisalCacheDto = new ApracticeappraisalCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					apracticeappraisalCacheDto.setAppraisalid(rs.getString("appraisalid"));
					apracticeappraisalCacheDto.setContent(rs.getString("content"));
					apracticeappraisalCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					apracticeappraisalCacheDto.setPracticeid(rs.getString("practiceid"));
					apracticeappraisalCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					apracticeappraisalCacheDto.setSigner(rs.getString("signer"));
					apracticeappraisalCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addChildrenObjectInCache(apracticeappraisalCacheDto.getPracticeid(),apracticeappraisalCacheDto.getAppraisalid(), apracticeappraisalCacheDto, Constant.Pra);
						logger.error(discode+"正在导把高综_综合实践活动_评价表导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_记录袋_附件
	 * @return
	 */
	@Override
	public List<AattachCacheDto> getAattachRecordPackageCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAattachRecordPackageCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachid(rs.getString("attachid"));
					attachCacheDto.setAttachname(rs.getString("attachname"));
					attachCacheDto.setAttachpath(rs.getString("attachpath"));
					attachCacheDto.setAttachtype(rs.getString("attachtype"));
					attachCacheDto.setAttachtypeid(rs.getString("attachtypeid"));
					attachCacheDto.setCmis30id(rs.getString("cmis30id"));
					attachCacheDto.setDiscode(rs.getString("discode"));
					attachCacheDto.setImage(rs.getString("image"));
					attachCacheDto.setPartid(rs.getString("partid"));
					attachCacheDto.setProcessid(rs.getString("processid"));
					attachCacheDto.setWorkid(rs.getString("workid"));
					attachCacheDto.setAppraseridentify("1");
					try{
						latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(),attachCacheDto.getAttachid(), attachCacheDto, Constant.GZ_attach);
						logger.error(attachCacheDto.getDiscode()+"正在导把高综_记录袋_附件数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_个人发展_附件
	 * @return
	 */
	@Override
	public List<AattachCacheDto> getAattachPersonalityCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAattachPersonalityCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAppraseridentify("1");
					attachCacheDto.setAttachid(rs.getString("attachid"));
					attachCacheDto.setAttachname(rs.getString("attachname"));
					attachCacheDto.setAttachpath(rs.getString("attachpath"));
					attachCacheDto.setAttachtype(rs.getString("attachtype"));
					attachCacheDto.setAttachtypeid(rs.getString("attachtypeid"));
					attachCacheDto.setCmis30id(rs.getString("cmis30id"));
					attachCacheDto.setDiscode(rs.getString("discode"));
					attachCacheDto.setImage(rs.getString("image"));
					attachCacheDto.setPartid(rs.getString("partid"));
					attachCacheDto.setProcessid(rs.getString("processid"));
					attachCacheDto.setWorkid(rs.getString("workid"));
					try{
						latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(),attachCacheDto.getAttachid(), attachCacheDto, Constant.GZ_attach);
						logger.error(attachCacheDto.getDiscode()+"正在导把 高综_个人发展_附件导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_综合实践_附件
	 * @return
	 */
	@Override
	public List<AattachCacheDto> getAattachPracticesCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAattachPracticesCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAppraseridentify("1");
					attachCacheDto.setAttachid(rs.getString("attachid"));
					attachCacheDto.setAttachname(rs.getString("attachname"));
					attachCacheDto.setAttachpath(rs.getString("attachpath"));
					attachCacheDto.setAttachtype(rs.getString("attachtype"));
					attachCacheDto.setAttachtypeid(rs.getString("attachtypeid"));
					attachCacheDto.setCmis30id(rs.getString("cmis30id"));
					attachCacheDto.setDiscode(rs.getString("discode"));
					attachCacheDto.setImage(rs.getString("image"));
					attachCacheDto.setPartid(rs.getString("partid"));
					attachCacheDto.setProcessid(rs.getString("processid"));
					attachCacheDto.setWorkid(rs.getString("workid"));
					try{
						latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(),attachCacheDto.getAttachid(), attachCacheDto, Constant.GZ_attach);
						logger.error(attachCacheDto.getDiscode()+"正在导把高综_综合实践_附件数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	/**
	 * 高综_学科学习发展过程_附件
	 * @return
	 */
	@Override
	public List<AattachCacheDto> getAattachLearnprocessWorksCache(String discode) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("discode", discode);
		try {
			return this.findList("HighSchoolCacheExtImpl.getAattachLearnprocessWorksCache.select",queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAppraseridentify("1");
					attachCacheDto.setAttachid(rs.getString("attachid"));
					attachCacheDto.setAttachname(rs.getString("attachname"));
					attachCacheDto.setAttachpath(rs.getString("attachpath"));
					attachCacheDto.setAttachtype(rs.getString("attachtype"));
					attachCacheDto.setAttachtypeid(rs.getString("attachtypeid"));
					attachCacheDto.setCmis30id(rs.getString("cmis30id"));
					attachCacheDto.setDiscode(rs.getString("discode"));
					attachCacheDto.setImage(rs.getString("image"));
					attachCacheDto.setPartid(rs.getString("partid"));
					attachCacheDto.setProcessid(rs.getString("processid"));
					attachCacheDto.setWorkid(rs.getString("workid"));
					try{
						latestEvaluationRecordExt.addAttachFileInCache(attachCacheDto.getAttachtypeid(),attachCacheDto.getAttachid(), attachCacheDto, Constant.GZ_attach);
						logger.error(attachCacheDto.getDiscode()+"正在导把高综_学科学习发展过程_附件数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
						
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("getApersonalityCache()",e);
		}
		return null;
	}

	@Override
	public void updateFamlityCacheData() {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		try {
			this.findList("HighSchoolCacheExtImpl.getAapprasialCache.select", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
					apprasialCacheDto.setApprasialid(rs.getString("apprasialid"));
					apprasialCacheDto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					apprasialCacheDto.setAppraiserrid(rs.getString("appraiserrid"));
					apprasialCacheDto.setSemesterid(rs.getString("semesterid"));
					apprasialCacheDto.setStudentid(rs.getString("studentid"));
					apprasialCacheDto.setAppraseridentify(rs.getString("appraseridentify"));
					apprasialCacheDto.setAppraser(rs.getString("appraser"));
					apprasialCacheDto.setApprasial(rs.getString("apprasial"));
					apprasialCacheDto.setSigndate(rs.getString("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					apprasialCacheDto.setEdu_id(rs.getString("edu_id"));
					apprasialCacheDto.setDiscode(rs.getString("discode"));
					apprasialCacheDto.setCmis30id(rs.getString("cmis30id"));
					apprasialCacheDto.setPartid(rs.getString("partid"));
					apprasialCacheDto.setEdittime(rs.getString("edittime")==null?null:sdf.format(rs.getDate("edittime")));
					if (rs.getString("appraseridentify").equals("1")) {
						apprasialCacheDto.setAppraiserrid(rs.getString("edu_id"));
					}
					try{
						//先清理，再增加
						latestEvaluationRecordExt.addRecodeInCacheByProKey(apprasialCacheDto.getEdu_id(), apprasialCacheDto.getSemesterid(),apprasialCacheDto.getAppraisaltypeid(), 
								apprasialCacheDto.getAppraseridentify(), apprasialCacheDto.getAppraiserrid(), apprasialCacheDto.getApprasialid(), apprasialCacheDto);
						logger.error(apprasialCacheDto.getDiscode()+"正在导把高中评价表数据导入到缓存中...+第"+arg1+"条");
					}catch(Exception e){
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("updateFamlityCacheData()",e);
		}
	}
	
}
