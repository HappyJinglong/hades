package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.PersonalityDto;

public interface IPersonalityServiceExt {
	/**
	 * 获取个性发展基本情况
	 * @param Map查询参数
	 * @return list个性发展基本情况
	 */
	public List selectPersonality(Map<String,Object> queryMap);

	/**
	 * 增加个性发展基本情况
	 * @param personalityDto个性发展基本情况
	 */
	public boolean insertPersonality(PersonalityDto personalityDto);
	
	/**
	 * 增加个性发展基本情况(缓存)
	 * @param personalityCacheDto
	 * @return
	 */
	public boolean insertPersonalityCache(ApersonalityCacheDto personalityCacheDto);
	
	/**
	 * 修改个性发展基本情况
	 * @param personalityDto个性发展基本情况
	 */
	public boolean updatePersonality(PersonalityDto personalityDto);
	
	/**
	 * 修改个性发展基本情况(缓存)
	 * @param personalityCacheDto
	 * @return
	 */
	public boolean updatePersonalityCache(ApersonalityCacheDto personalityCacheDto);
	
	/**
	 * 增加附件
	 * @param 附件dto,id
	 * @return boolean是否成功
	 */
	public boolean insertAttach(AttachDto attachDto,String id);
	
	/**
	 * 增加附件(缓存)
	 * @param attachCacheDto 附件缓存Dto
	 * @return
	 */
	public boolean insertAttachCache(AattachCacheDto attachCacheDto,String type);
	
	/**
	 * 删除附件
	 * @param 附件ID
	 * @return boolean是否成功
	 */
	public boolean deleteAttach(String id);
	
	/**
	 * 查询AttachList
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttachList(Map<String, Object> queryMap);
	
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttach(Map<String, Object> queryMap);
	
	/**
	 * 增加学科学习过程记录
	 * @param recordPackageDto记录袋Dto,附件list
	 * @return String当前记录袋id
	 */
	public String insertSelfApp(AppraisalDto AppraisalDto,List<AttachDto> attachs);
	
	/**
	 * 增加学科学习过程记录(缓存)
	 * @param apprasialCacheDto
	 * @return
	 */
	public String insertSelfAppCache(AapprasialCacheDto apprasialCacheDto);
	
	/**
	 * 获取个性发展过程与特长与成果展示
	 * @param Map查询参数
	 * @return List个性发展过程与特长与成果展示
	 */
	public List selectSelfAppraise(Map<String, Object> queryMap);
	
}
