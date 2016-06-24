package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;

public interface ILearnprocessWorksServiceExt {
	
	/**
	 * 获取学科学习过程记录
	 * @param Map查询参数
	 * @return List记录袋数据
	 */
	public List selectLearnprocessWorks(Map<String, Object> queryMap);
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
	public String insertLearnprocessWorks(LearnprocessWorksDto learnprocessWorksDto,List<AttachDto> attachs);
	
	/**
	 * 增加学科学习过程记录(缓存)
	 * @param learnprocessWorksCacheDto
	 * @return
	 */
	public String insertLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto,AattachCacheDto attachCacheDto,String type);
	
	/**
	 * 修改学科学习过程记录
	 * @param recordPackageDto记录袋Dto
	 * @return boolean是否成功
	 */
	public boolean updateLearnprocessWorks(LearnprocessWorksDto learnprocessWorksDto);
	
	/**
	 * 修改学科学习过程记录(缓存)
	 * @param learnprocessWorksCacheDto
	 * @param type
	 * @return
	 */
	public boolean updateLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto,String type);
	
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
	 * 删除附件
	 * @param attachCacheDto 附件缓存Dto
	 * @return boolean是否成功
	 */
	public boolean deleteAttachCache(AattachCacheDto attachCacheDto,String type);
	
	/**
	 * 查询AttachList
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttachList(Map<String, Object> queryMap);
	
	/**
	 * 删除id
	 * @param 记录袋id
	 */
	public boolean deleteLearnprocessWorks(String id);
	
	/**
	 * 删除记录袋
	 * @return
	 */
	public boolean deleteLearnprocessWorksCache(AlearnprocessWorksCacheDto learnprocessWorksCacheDto,String type);
}
