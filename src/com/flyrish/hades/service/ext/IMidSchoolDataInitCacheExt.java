package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;

public interface IMidSchoolDataInitCacheExt {
	/**
	 * 查询当前所有在学得学生评价信息
	 * @return 评价信息集合
	 */
	public List<PartInfoCacheDto> queryPartInfoCacheDtosInDb(String discode);
	/**
	 * 查询当前有效的附件信息
	 * @return 附件信息集合
	 */
	public List<AttachmentCacheDto> queryAttachmentCacheDtosInDb(String discode);
}
