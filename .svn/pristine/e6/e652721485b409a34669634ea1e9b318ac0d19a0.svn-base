package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.PracticeappraisalDto;
import com.flyrish.hades.dto.PracticesDto;

public interface IPracticesServiceExt {

	/**
	 * 获取高中综合实践活动
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<PracticesDto> selectSelfPractices(Map<String,Object> queryMap);
	/**
	 * 增加高中实践活动
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public String insertSelfPractices(PracticesDto practices);
	/**
	 * 增加高中实践活动(缓存)
	 * @param practicesCacheDto 高中实践活动缓存Dto
	 * @return
	 */
	public String insertSelfPracticesCache(ApracticesCacheDto practicesCacheDto);
	/**
	 * 增加高中实践活动
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public String insertSelfPracticeappraisal(PracticeappraisalDto practiceappraisal);
	/**
	 * 增加高中实践活动自我评价(缓存、附表)
	 * @param practiceappraisalCacheDto 高中实践活动自我评价缓存Dto
	 * @return
	 */
	public String insertSelfPracticeappraisalCache(ApracticeappraisalCacheDto practiceappraisalCacheDto, String type);
	/**
	 * 更新高中实践活动
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public boolean updateSelfPractices(PracticesDto practices);
	/**
	 * 更新高中实践活动(缓存)
	 * @param practicesCacheDto 高中实践活动缓存Dto
	 * @return
	 */
	public boolean updateSelfPracticesCache(ApracticesCacheDto practicesCacheDto);
	/**
	 * 更新高中实践活动自我评价
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public boolean updateSelfPracticeappraisal(PracticeappraisalDto practiceappraisal);
	/**
	 * 更新高中实践活动自我评价(缓存、附表)
	 * @param practiceappraisalCacheDto 高中实践活动自我评价缓存Dto
	 * @return
	 */
	public boolean updateSelfPracticeappraisalCache(ApracticeappraisalCacheDto practiceappraisalCacheDto,String type);
	/**
	 * 删除高中实践活动
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public boolean deleteSelfPractices(String id);
	/**
	 * 删除高中实践活动(缓存)
	 * @return
	 */
	public boolean deleteSelfPracticesCache(String username, String columNum,String proKey,ApracticesCacheDto practicesCacheDto);
	/**
	 * 删除高中实践活动自我评价
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public boolean deleteSelfPracticeappraisal(String id);
	/**
	 * 删除高中实践活动自我评价(缓存)
	 * @param id
	 * @return
	 */
	public boolean deleteSelfPracticeappraisalCache(String id,String fid,String type);
	
}
