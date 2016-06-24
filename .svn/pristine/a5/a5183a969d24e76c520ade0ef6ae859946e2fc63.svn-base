package com.flyrish.hades.service.ext;

import java.util.Map;

import com.flyrish.hades.dto.HOinfromreadDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.exception.ManagerException;

public interface IInformDoQueAndCacheExt {
	/**
	 * 把通知公告放入队列之中
	 * @param informDto 有效的通知公告
	 * @exception ManagerException 存放失败，则抛出运行时异常，保证事务回滚
	 */
	public void putInformToQueue(InformDto informDto);
	/**
	 * 把对通知公告已读的用户放入队列中
	 * @param inform_id 通知公告主键标识号
	 * @param userid 用户对象id
	 */
	public void addReadedInformUserToCache(String inform_id,String userid);
	/**
	 * 获取当前队列中，待处理的任务
	 * @return
	 */
	public Integer size(String key);
}
