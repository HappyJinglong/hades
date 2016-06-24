package com.flyrish.hades.service.ext.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt1;
import com.flyrish.hades.service.ext.IInformQueryExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class InformDoQueAndCacheExtImpl extends JdbcRootManager implements IInformDoQueAndCacheExt1 {
	private IInformQueryExt informQueryExt;
	
	public IInformQueryExt getInformQueryExt() {
		return informQueryExt;
	}
	public void setInformQueryExt(IInformQueryExt informQueryExt) {
		this.informQueryExt = informQueryExt;
	}
	private IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public Map<String,InformDto> queryInformDtoInCache() {
		return redisServiceExt.readSingle(Constant.R_READINFORM_KEY);
	}
	public void refreshInformInCache() {
		try {
			//1、清空缓存数据
			redisServiceExt.delete(Constant.R_READINFORM_KEY);
			//2、查询通知通告
			Map<String,Object> params = new HashMap<String,Object>();
			Date date_now=new Date();
			SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			String date = simple.format(date_now);
			params.put("endtime",  date+"00-00-00");
			params.put("publishState", 1);
			params.put("oderbyC", 1);
			List<InformDto> informlistQuery = informQueryExt.informlistQuery(params);
			if(null!=informlistQuery &&informlistQuery.size()>0){
				informQueryExt.setHoiRead(informlistQuery);
				Map<String, InformDto> cHashMap = new ConcurrentHashMap<String, InformDto>();
				for(InformDto iDto : informlistQuery){
					cHashMap.put(iDto.getInformid(), iDto);
				}
				//3、将查询数据放进缓存
				if(null!=cHashMap && cHashMap.size()>0){
					redisServiceExt.save(Constant.R_READINFORM_KEY, cHashMap);
				}
			}
		} catch (ForceException e) {
			logger.error("refreshInformInCache()",e);
		}
	}
}
