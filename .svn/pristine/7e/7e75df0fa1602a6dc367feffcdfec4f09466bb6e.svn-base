package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.StudentDto;


public interface ISelfAppManagerExt {

	/**
	 * 获取自我评价列表信息
	 * @param Map查询参数
	 * @return list自我评价数据
	 */
	public List selectSelfAppraise(Map<String,Object> queryMap);

	/**
	 * 增加自我评价
	 * @param appraisal自我评价
	 */
	public String insertSelfAppraisal(AppraisalDto appraisal);
	
	/**
	 * 增加自我评价(缓存)
	 * @param appraisalDto 自我评价缓存Dto
	 * @return 主键id
	 */
	public String insertSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto);
	
	/**
	 * 增加自我评价
	 * @param appraisal自我评价
	 */
	public String insertSelfAppraisalwith(AppraisalDto appraisal);
	
	/**
	 * 增加自我评价(缓存)
	 * @param appraisalCacheDto 自我评价缓存Dto
	 * @return
	 */
	public String insertSelfAppraisalwithCache(AapprasialCacheDto appraisalCacheDto);
	
	/**
	 * 修改自我评价
	 * @param appraisal自我评价
	 */
	public boolean updateSelfAppraisal(AppraisalDto appraisal);
	
	/**
	 * 修改自我评价(缓存)
	 * @param appraisalCacheDto 自我评价缓存Dto
	 * @return
	 */
	public boolean updateSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto);
	
	/**
	 * 获得学生信息
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public StudentDto selectStudent(Map<String, Object> queryMap);
	
	/**
	 * 删除自我评价
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public boolean deleteSelfAppraisal(String id,String cmis30id,String discode);
	
	/**
	 * 删除自我评价(缓存)
	 * @param appraisalCacheDto 自我评价缓存Dto
	 * @return
	 */
	public boolean deleteSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto);
	
	/**
	 * 删除自我评价有附件
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public boolean deleteSelfAppraisalWithatt(String id,String cmis30id,String discode);
	
	/**
	 * 删除自我评价有附件(缓存)
	 * @param appraisalCacheDto  自我评价缓存Dto
	 * @return
	 */
	public boolean deleteSelfAppraisalWithattCache(AapprasialCacheDto appraisalCacheDto);
	
	/**
	 * 查询学生照片
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public List selectPhoto(Map<String, Object> queryMap);
	
}
