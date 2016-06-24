package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessMarksCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;

/**
 * 高中缓存接口
 * @author Administrator
 *
 */
public interface IHighSchoolCacheExt {
	/**
	 * 高综_自我评价
	 * @return list集合
	 */
	public List<AapprasialCacheDto> getAapprasialCache(String discode);
	/**
	 * 清理重复的数据（缓存和数据库同时有的）
	 * @param cmis30id
	 * @param discode
	 */
	public void cleanDoubleAapprasialCacheAndDb();
	
	/**
	 * 高综_记录袋
	 * @return
	 */
	public List<ArecordpackageCacheDto> getArecordpackageCache(String discode);
	
	/**
	 * 高综_记录袋
	 * @return
	 */
	public void cleanArecordpackageCache();
	/**
	 * 高综_个性与发展_基本情况
	 * @return
	 */
	public List<ApersonalityCacheDto> getApersonalityCache(String discode);
	
	/**
	 * 高综_学业成就_学科学习过程记录_作品相关信息介绍
	 * @return
	 */
	public List<AlearnprocessWorksCacheDto> getAlearnprocessWorksCache(String discode);
	
	/**
	 * 高综_学业成就_学科学习过程记录_成绩
	 * @return
	 */
	public List<AlearnprocessMarksCacheDto> getAlearnprocessMarksCache(String discode);
	
	/**
	 * 高综_学业成就_学科学习过程记录_评语
	 * @return
	 */
	public List<AlearnprocessAppraisalCacheDto> getAlearnprocessAppraisalCache(String discode);
	
	/**
	 * 高综_综合实践活动
	 * @return
	 */
	public List<ApracticesCacheDto> getApracticesCache(String discode);
	
	/**
	 * 高综_综合实践活动_评价表
	 * @return
	 */
	public List<ApracticeappraisalCacheDto> getApracticeappraisalCache(String discode);
	
	/**
	 * 高综_记录袋_附件
	 * @return
	 */
	public List<AattachCacheDto> getAattachRecordPackageCache(String discode);
	
	/**
	 * 高综_个人发展_附件
	 * @return
	 */
	public List<AattachCacheDto> getAattachPersonalityCache(String discode);
	
	/**
	 * 高综_综合实践_附件
	 * @return
	 */
	public List<AattachCacheDto> getAattachPracticesCache(String discode);
	
	/**
	 * 高综_学科学习发展过程_附件
	 * @return
	 */
	public List<AattachCacheDto> getAattachLearnprocessWorksCache(String discode);
	
	public void updateFamlityCacheData();
}
