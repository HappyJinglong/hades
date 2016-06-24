package com.flyrish.hades.service.ext.impl;

import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessMarksCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IHighSchoolCacheExt;
import com.flyrish.hades.service.ext.IImportDataToRedisCacheExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMidSchoolDataInitCacheExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class ImportDataToRedisCacheExtImpl extends JdbcRootManager implements
		IImportDataToRedisCacheExt {
	
	public IRedisServiceExt redisServiceExt;
	
	public IMidSchoolDataInitCacheExt midSchoolDataInitCacheExt;
	
	public IHighSchoolCacheExt highSchoolCacheExt;
	
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public ConstantBean constantBean;
	
	public String discode;
	@Override
	public void doStart() {
		//清理缓存中所有和评价类相关的数据
		/*1、清除评价类的缓存
		2、清除附件类的缓存
		3、清除附表的缓存*/
		//获取相关参数
		//1、获取是否清理缓存标识
		String isCleanAllDataNum=constantBean.get("isCleanAllDataNum");
		if("1".equals(isCleanAllDataNum)){
			deleteAppriseAllInfoInCache();
		}
		//获取所需导入的区县代码
		String discodeStr=constantBean.get("importDataByDiscode");
		if(NestUtil.isEmpty(discodeStr))return;
		String[]discodes=discodeStr.split(",");
		int count=0;
		for(String dicode:discodes){
			this.discode=dicode;
			count++;
			try{
				logger.info("正在导入第"+count+"区数据");
				//导入初中数据入缓存中
				importMidDataIntoCach();
				logger.info(count+"已导入初中数据，开始导入初中附件");
				//导入初中附件缓存信息到缓存中
				importAttachFile();
				logger.info(count+"已导入初中附件，开始导入高中评价数据");
				//导入高中数据入缓存中
				importApprasialCache();//高综_自我评价
				logger.info(count+"已导入高综_自我评价，开始导入高综_记录袋");
				importArecordpackage();//高综_记录袋
				logger.info(count+"已导入高综_记录袋，开始导入高综_个性与发展_基本情况");
				importApersonality();//高综_个性与发展_基本情况
				logger.info(count+"已导入高综_个性与发展_基本情况据，开始导入高综_学业成就_学科学习过程记录_作品相关信息介绍");
				importAlearnprocessWorks();//高综_学业成就_学科学习过程记录_作品相关信息介绍
				logger.info(count+"已导入高综_学业成就_学科学习过程记录_作品相关信息介绍，开始导入高综_学业成就_学科学习过程记录_成绩");
				importAlearnprocessMarks();//高综_学业成就_学科学习过程记录_成绩
				logger.info(count+"已导入高综_学业成就_学科学习过程记录_成绩，开始导入高综_学业成就_学科学习过程记录_评语");
				importAlearnprocessAppraisal();//高综_学业成就_学科学习过程记录_评语
				logger.info(count+"已导入高综_学业成就_学科学习过程记录_评语，开始导入高综_综合实践活动");
				importApractices();//高综_综合实践活动
				logger.info(count+"已导入高综_综合实践活动，开始导入高综_综合实践活动_评价表");
				//导入高中附表信息到缓存中
				importApracticeappraisal();//高综_综合实践活动_评价表
				logger.info(count+"已导入高综_综合实践活动_评价表，开始导入高综_记录袋_附件");
				//导入高中附件表
				importAattachRecordPackage();//高综_记录袋_附件
				logger.info(count+"已导入高综_记录袋_附件，开始导入高综_个人发展_附件");
				importAattachPersonality();//高综_个人发展_附件
				logger.info(count+"已导入高综_个人发展_附件，开始导入高综_综合实践_附件");
				importAattachPractices();//高综_综合实践_附件
				logger.info(count+"已导入高综_综合实践_附件，开始导入高综_学科学习发展过程_附件");
				importAattachLearnprocessWorks();//高综_学科学习发展过程_附件
				logger.info(count+"已导入高综_学科学习发展过程_附件");
				redisServiceExt.saveObjMap(constantBean.get("discodeSuccuessInCache"),dicode,dicode);
			}catch(Exception e){
				logger.error("导入数据失败的区县"+discode,e);
			}
		}
	}
	
	@Override
	public void udpateParentFamliy() {
		//开始更新高中数据
		highSchoolCacheExt.updateFamlityCacheData();
		//开始更新初中数据
		
	}

	public void startCleanDoubleData(){
		//删除高中评价类重复的数据（包括缓存和数据库）
		cleanApprasialCache();//高综_自我评价
		//删除a_personality
		cleanApersonality();//高综_个性发展
		//高综_学业成就_学科学习过程记录_作品相关信息介绍
		cleanAlearnprocessWorks();
		//删除
		cleanAlearnprocessAppraisal();//高综_学业成就_学科学习过程记录_评语
		//删除高综_综合实践活动
		cleanApractices();
	}
	private void cleanApractices() {
		
	}
	private void cleanAlearnprocessAppraisal() {
		
	}
	private void cleanAlearnprocessWorks() {
		
	}
	private void cleanApersonality() {
		
	}
	private void cleanApprasialCache() {
		highSchoolCacheExt.cleanDoubleAapprasialCacheAndDb();
	}
	//高中
	private void importApprasialCache() {
		List<AapprasialCacheDto> dtos = highSchoolCacheExt.getAapprasialCache(discode);
	}
	private void importArecordpackage() {
		List<ArecordpackageCacheDto> dtos = highSchoolCacheExt.getArecordpackageCache(discode);
	}
	private void importApersonality() {
		List<ApersonalityCacheDto> dtos = highSchoolCacheExt.getApersonalityCache(discode);
	}
	private void importAlearnprocessWorks() {
		List<AlearnprocessWorksCacheDto> dtos = highSchoolCacheExt.getAlearnprocessWorksCache(discode);
	}
	private void importAlearnprocessMarks() {
		List<AlearnprocessMarksCacheDto> dtos = highSchoolCacheExt.getAlearnprocessMarksCache(discode);
	}
	private void importAlearnprocessAppraisal() {
		List<AlearnprocessAppraisalCacheDto> dtos = highSchoolCacheExt.getAlearnprocessAppraisalCache(discode);
	}
	private void importApractices() {
		List<ApracticesCacheDto> dtos = highSchoolCacheExt.getApracticesCache(discode);
	}
	//附表
	private void importApracticeappraisal() {
		List<ApracticeappraisalCacheDto> dtos = highSchoolCacheExt.getApracticeappraisalCache(discode);
	}
	//附件表
	private void importAattachRecordPackage() {
		List<AattachCacheDto> dtos = highSchoolCacheExt.getAattachRecordPackageCache(discode);
	}
	private void importAattachPersonality() {
		List<AattachCacheDto> dtos = highSchoolCacheExt.getAattachPersonalityCache(discode);
	}
	private void importAattachPractices() {
		List<AattachCacheDto> dtos = highSchoolCacheExt.getAattachPracticesCache(discode);
	}
	private void importAattachLearnprocessWorks() {
		List<AattachCacheDto> dtos = highSchoolCacheExt.getAattachLearnprocessWorksCache(discode);
	}
	//初中
	private void importAttachFile(){
		List<AttachmentCacheDto> dtos=midSchoolDataInitCacheExt.queryAttachmentCacheDtosInDb(discode);
	}
	private void importMidDataIntoCach(){
		midSchoolDataInitCacheExt.queryPartInfoCacheDtosInDb(discode);
	}
	
	@Override
	public void initData() {
		//
	}
	/**
	 * 删除评价类所有key
	 * @throws ForceException 
	 */
	private void deleteAppriseAllInfoInCache(){
		redisServiceExt.cleanAllAppraserCache(Constant.R_REDIS_CACHE_TOTALKEY);
	}
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public IMidSchoolDataInitCacheExt getMidSchoolDataInitCacheExt() {
		return midSchoolDataInitCacheExt;
	}
	public void setMidSchoolDataInitCacheExt(
			IMidSchoolDataInitCacheExt midSchoolDataInitCacheExt) {
		this.midSchoolDataInitCacheExt = midSchoolDataInitCacheExt;
	}
	public IHighSchoolCacheExt getHighSchoolCacheExt() {
		return highSchoolCacheExt;
	}
	public void setHighSchoolCacheExt(IHighSchoolCacheExt highSchoolCacheExt) {
		this.highSchoolCacheExt = highSchoolCacheExt;
	}
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
}
