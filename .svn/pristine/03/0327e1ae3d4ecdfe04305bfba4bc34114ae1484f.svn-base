package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.RecordPackageDto;



public interface IRecordPackageManagerExt {
	/**
	 * 获取记录袋数据
	 * @param Map查询参数
	 * @return List记录袋数据
	 */
	public List selectRecordpackage(Map<String, Object> queryMap);
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttach(Map<String, Object> queryMap);
	/**
	 * 增加记录袋
	 * @param recordPackageDto记录袋Dto,附件list
	 * @return String当前记录袋id
	 */
	public String insertRecordpackage(RecordPackageDto recordPackageDto,List<AttachDto> attachs);
	
	/**
	 * 增加记录袋(缓存)
	 * @param recordpackageCacheDto
	 * @return
	 */
	public String insertRecordpackageCache(ArecordpackageCacheDto recordpackageCacheDto);
	
	/**
	 * 修改记录袋
	 * @param recordPackageDto记录袋Dto
	 * @return boolean是否成功
	 */
	public boolean updateRecordpackage(RecordPackageDto recordPackageDto);
	/**
	 * 修改记录袋
	 * @param recordpackageCacheDto
	 * @return
	 */
	public boolean updateRecordpackageCache(ArecordpackageCacheDto recordpackageCacheDto);
	/**
	 * 增加附件
	 * @param 附件dto,id
	 * @return boolean是否成功
	 */
	public boolean insertAttach(AttachDto attachDto,String id);
	/**
	 * 增加附件(缓存)
	 * @param attachCacheDto
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
	 * 删除id
	 * @param 记录袋id
	 */
	public boolean deleteSelfPackage(String id);
	
	public boolean deleteSelfPackageCache(ArecordpackageCacheDto recordpackageCacheDto);
	/**
	 * 下载单个附件
	 * @param foreignKey
	 * @param attachid
	 * @return
	 */
	public List<AttachDto> selectAttachListFromCache(String foreignKey,	String attachid);
}
