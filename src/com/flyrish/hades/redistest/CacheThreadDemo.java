package com.flyrish.hades.redistest;

import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class CacheThreadDemo extends Thread {
	private IRedisServiceExt redisServiceExt;
	
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public CacheThreadDemo(IRedisServiceExt redisServiceExt,
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.redisServiceExt = redisServiceExt;
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	@Override
	public void run() {
		int  count=0;
		while(count<1000000){
			count++;
			ApracticesCacheDto dto=new ApracticesCacheDto();
			dto.setAppraisaltypeid("1111111");
			dto.setAppraiserrid(Math.random()+"");
			dto.setAppraseridentify("1111111");
			dto.setCmis30id("1111111");
			dto.setDiscode("1111111");
			dto.setEdittime("1111111");
			dto.setEdu_id("1111111");
			dto.setItem1("1111111");
			dto.setItem10("1111111");
			dto.setItem11("1111111");
			dto.setItem12("1111111");
			dto.setItem13("1111111");
			dto.setItem14("1111111");
			dto.setItem15("1111111");
			dto.setItem16("1111111");
			dto.setItem17("1111111");
			dto.setItem18("1111111");
			dto.setItem2("1111111");
			dto.setItem20("1111111");
			dto.setItem8("1111111");
			dto.setItem9("1111111");
			dto.setItem5("1111111");
			dto.setItem3("1111111");
			dto.setItem4("1111111");
			dto.setItem5("1111111");
			dto.setItem6("1111111");
			dto.setItem7("1111111");
			dto.setItem8("1111111");
			dto.setItem9("1111111");
			String key=Math.random()+""+Math.random();
			try{
			latestEvaluationRecordExt.addRecodeInCacheByProKey(key,"20121","11","2","333","55555",dto);
			redisServiceExt.saveObjMap(key,key,dto);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		RedisApiTest.count++;
	}
	
}
