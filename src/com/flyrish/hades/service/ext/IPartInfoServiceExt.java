package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.AttachmentDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoDto;


public interface IPartInfoServiceExt {

	/**
	 * 获取初中栏目内容(不含附件)
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List selectSelfPartInfo(Map<String,Object> queryMap);
	
	/**
	 * 获取初中栏目内容(含附件)
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List selectSelfPartInfoWithatt(Map<String, Object> queryMap);
	
	/**
	 * 获取初中综合实践活动(含附件)
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List selectSelfPartInfoZonghe(Map<String, Object> queryMap);
	
	/**
	 * 增加初中栏目内容
	 * @param PartInfo栏目内容Dto
	 * @throws Exception 
	 */
	public String insertSelfPartInfo(PartInfoDto partInfo);
	
	/**
	 * 增加初中栏目内容(缓存)
	 * @param partInfoCacheDto 栏目内容缓存Dto
	 * @return
	 */
	public String insertSelfPartInfoCache(PartInfoCacheDto partInfoCacheDto);
	
	/**
	 * 更新初中栏目内容
	 * @param PartInfo栏目内容Dto
	 */
	public boolean updateSelfPartInfo(PartInfoDto partInfo);
	
	/**
	 * 更新初中栏目内容
	 * @param partInfoCacheDto
	 * @return
	 */
	public boolean updateSelfPartInfoCache(PartInfoCacheDto partInfoCacheDto);
	
	/**
	 * 更新初中栏目内容
	 * @param PartInfo栏目内容Dto
	 */
	public boolean updateSelfPartInfoZh(PartInfoDto partInfo);	
	
	/**
	 * 更新初中栏目内容
	 * @param partInfoCacheDto 
	 * @return
	 */
	public boolean updateSelfPartInfoZhCache(PartInfoCacheDto partInfoCacheDto);
	
	/**
	 * 增加初中附件
	 * @param 附件dto,id
	 * @return boolean是否成功
	 */
	public boolean insertAttachment(AttachmentDto attachment,String id);
	
	/**
	 * 增加初中附件(缓存)
	 * @param attachmentCacheDto 初中附件缓存 
	 * @return
	 */
	public boolean insertAttachmentCache(AttachmentCacheDto attachmentCacheDto,String type);
	
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttachment(Map<String, Object> queryMap);
	
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return List附件数据
	 */
	public List selectAttachmentList(Map<String, Object> queryMap);
	
	/**
	 * 删除自我评价
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public boolean deletePartinfo(String id,String cmis30id,String discode);
	
	/**
	 * 删除自我评价(缓存)
	 * @return
	 */
	public boolean deletePartinfoCache(PartInfoCacheDto partInfoCacheDto);
	
	/**
	 * 删除附件
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public boolean deleteAttachment(String id);
	
	/**
	 * 删除附件(缓存)
	 * @param id 附件表主键
	 * @param rid 附件表外键
	 * @return
	 */
	public boolean deleteAttachmentCache(String id,String rid,String type);
	
	/**
	 * 删除记录袋
	 * @param Map查询参数
	 * @return StudentDto学生信息
	 */
	public boolean deleteAttach(String id,String cid,String dcode);
	
	/**
	 * 删除记录袋(缓存)
	 * @param pratInfoCacheDto  栏目信息缓存Dto
	 * @return
	 */
	public boolean deleteAttachCache(PartInfoCacheDto pratInfoCacheDto);
	
	/**
	 * 增加栏目内容
	 * @param partInfo
	 * @return
	 */
	public String insertSelfPartInfoWith(PartInfoDto partInfo);
	
	/**
	 * 增加栏目内容(缓存)
	 * @param partInfoCacheDto
	 * @return
	 */
	public String insertSelfPartInfoWithCache(PartInfoCacheDto partInfoCacheDto);
	/**
	 * 获取栏目标号
	 * @param params
	 * @return
	 */
	public String getSectionCode(Map<String,Object> params);
	/**
	 * 初中附件
	 * @param foreignKey
	 * @param attachment_id
	 * @return
	 */
	public List<AttachmentDto> selectAttachmentListFromCache(String foreignKey, String attachment_id);
}
