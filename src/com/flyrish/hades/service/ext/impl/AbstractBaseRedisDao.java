package com.flyrish.hades.service.ext.impl;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class AbstractBaseRedisDao {
	 protected RedisTemplate<Serializable, Serializable> redisTemplate; 
	 /** 
	    * 设置redisTemplate 
	    * @param redisTemplate the redisTemplate to set 
	    */  
	 public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {  
	       this.redisTemplate = redisTemplate;  
	   }
	 
	 public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	 }
	/** 
	     * 获取 RedisSerializer 
	     */  
	 protected RedisSerializer<String> getRedisSerializer(){  
	      return redisTemplate.getStringSerializer();  
	  } 
}
