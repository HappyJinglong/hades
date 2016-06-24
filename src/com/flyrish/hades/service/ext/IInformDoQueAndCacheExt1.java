package com.flyrish.hades.service.ext;

import java.util.Map;

import com.flyrish.hades.dto.HOinfromreadDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.exception.ManagerException;

public interface IInformDoQueAndCacheExt1 {
	/**
	 * 刷新缓存中所有的通知公告
	 */
	public void refreshInformInCache();
	
	public Map<String,InformDto> queryInformDtoInCache();
}
