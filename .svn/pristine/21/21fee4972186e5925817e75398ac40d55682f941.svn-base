package com.flyrish.hades.service.ext.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.HOinfromreadDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class InformDoQueAndCacheExtImpl1 implements IInformDoQueAndCacheExt {
	private IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public void putInformToQueue(InformDto informDto) {
		if(informDto==null)return;
		redisServiceExt.insertQueue(Constant.R_READINFORM_KEYS,informDto);
	}
	public Map<String,InformDto> queryInformDtoInCache() {
		return redisServiceExt.readSingle(Constant.R_READINFORM_KEY);
	}
	public void addReadedInformUserToCache(String inform_id,
			String userid) {
		if(NestUtil.isEmpty(inform_id)||NestUtil.isEmpty(userid)) return;
		HOinfromreadDto hOinfromreadDto=new HOinfromreadDto();
		hOinfromreadDto.setInform_id(inform_id);
		hOinfromreadDto.setUserid(userid);
		redisServiceExt.insertQueue(Constant.R_READINFORM_KEYS,hOinfromreadDto);
	}
	public Integer size(String key) {
		return redisServiceExt.readLength(key)==null?null:redisServiceExt.readLength(key).intValue();
	}
}
