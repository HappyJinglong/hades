package com.flyrish.hades.service.ext.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nestframework.exporter.exception.ManagerException;
import org.nestframework.utils.NestUtil;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.jodis.JedisResourcePool;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.ObjectsTranscoder;
import com.flyrish.hades.util.Redis;

public class RedisServiceExtImpl extends AbstractBaseRedisDao implements
		IRedisServiceExt{
	private Logger logger = Logger.getLogger(RedisServiceExtImpl.class);
	
	private JedisResourcePool jedisPool;
	
	public ConstantBean constantBean;
	
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public JedisResourcePool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisResourcePool jedisPool) {
		this.jedisPool = jedisPool;
	}
	@SuppressWarnings("unchecked")
	@Redis
	public void delete(String key) throws ForceException {
		Jedis jedis = null;
		try{
			if(NestUtil.isEmpty(key))return;
			
			jedis = jedisPool.getResource();
			byte[] objKey = ObjectsTranscoder.serialize(key);
			int count=0;
			while(count<3){
				try{
					jedis.del(objKey);
					break;
				}catch(Exception e){
					logger.error("delete(final String)-第"+count+"次删除"+key+" key",e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("delete(final String)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Redis
	public void deleteStr(String key) throws ForceException {
		Jedis jedis = null;
		try{
			if(NestUtil.isEmpty(key))return;
			jedis = jedisPool.getResource();
			int count=0;
			while(count<3){
				try{
					jedis.del(key);
					break;
				}catch(Exception e){
					logger.error("delete(final String)-第"+count+"次删除"+key+" key",e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("delete(final String)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	@Redis
	public void delete(List<String> keys) throws ForceException {
		if(keys==null||keys.size()<1)return;
		for(String key:keys){
				delete(key);
		}
	}
	public <T> void saveObjList(String key,T obj){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(obj);
					jedis.lpush(objKey,objValue);
					break;
				}catch(Exception e){
					logger.error("saveObjList(String,T)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("saveObjList(final String,final T)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> void saveObjList(String key,T obj,int time){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(obj);
					jedis.lpush(objKey,objValue);
					break;
				}catch(Exception e){
					logger.error("saveObjList(String,T,int)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("saveObjList(final String,final T)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> void saveObjMap(String key,String mapKey,T value){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					String json=JSON.toJSONString(value);
					jedis.hset(key, mapKey,json);
					break;
				}catch(Exception e){
					logger.error("saveObjMap(final String,final String,final T)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("saveObjMap(final String,final String,final T)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> T readObjMapValue(String key,String mapKey,Class clazz){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					String jsonObjectString=jedis.hget(key,mapKey);
					return (T)JSON.parseObject(jsonObjectString,clazz);
				}catch(Exception e){
					logger.error("readObjMapValue(final String,final String,Class)",e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("readObjMapValue(final String,final String,Class)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	public Long readLength(String key){
		Jedis jedis = jedisPool.getResource();
		try{
			byte[] objKey = ObjectsTranscoder.serialize(key);
			return jedis.llen(objKey);
		}catch(Exception e){
			logger.error("readLength(final String)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	@Override
	public void delObjMap(String key,String mapKey){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					jedis.hdel(key,mapKey);
					break;
				}catch(Exception e){
					logger.error("delObjMap(final String,final String)-第"+key+"次删除key",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("delObjMap(final String,final String)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> Map<String,T> readObjMap(String key,Class clazz){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					Map<String,String> mapString=jedis.hgetAll(key);
					if(mapString==null||mapString.size()==0)
						return null;
					Map<String,T> mapValue=new HashMap<String,T>();
					for(Entry<String,String> entry:mapString.entrySet()){
						T t=(T)JSON.parseObject(entry.getValue(),clazz);
						mapValue.put(entry.getKey(),t);
					}
					return mapValue;
				}catch(Exception e){
					logger.error("readObjMap(final String,Class)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
			return null;
		}catch(Exception e){
			logger.error("readObjMap(final String,Class)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	public <T> List<T> readObjList(String key){
		Jedis jedis = jedisPool.getResource();
		try{
			
			byte[] objKey = ObjectsTranscoder.serialize(key);
			Long len=jedis.llen(objKey);
			List<byte[]> listByte=jedis.lrange(objKey,0,len-1);
			if(listByte==null) return null;
			List<T> list=new ArrayList<T>();
			for(byte[] value:listByte){
				T t = ObjectsTranscoder.deserializeObj(value);
				list.add(t);
			}
			return list;
		}catch(Exception e){
			logger.error("readObjList(final String)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	public <T> void insertQueue(String key,final T t){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(t);
					//右边插入
					jedis.rpush(objKey,objValue);
					break;
				}catch(Exception e){
					logger.error("insertQueue(final String,final T)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("insertQueue(final String,final T)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> void insertQueueStr(String key,final T t){
		Jedis jedis = jedisPool.getResource();
		try{
			int count=0;
			while(count<3){
				try{
					jedis.rpush(key,JSON.toJSONString(t));
					break;
				}catch(Exception e){
					logger.error("insertQueueStr(final String,final T)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("insertQueueStr(final String,final T)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> T outQueue(String key,Class clazz){
		Jedis jedis = jedisPool.getResource();
		try{
			byte[] objKey = ObjectsTranscoder.serialize(key);
			//左边出列
			byte[] objValue=jedis.lpop(objKey);
			T t = ObjectsTranscoder.deserializeObj(objValue);
			return t;
		}catch(Exception e){
			logger.error("outQueue(final String,Class)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	public <T> T outQueueStr(String key,Class clazz){
		Jedis jedis = jedisPool.getResource();
		try{
			String jsonObjectString=jedis.lpop(key);
			return (T)JSON.parseObject(jsonObjectString,clazz);
		}catch(Exception e){
			logger.error("outQueue(final String,Class)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	@Redis
	public <T> boolean save(final String key,final List<T> objList) throws ForceException {
		int time=(60*60*24);
		return save(key,objList,time);
	}
	@Redis
	public <T> boolean save(final String key,final T obj) throws ForceException {
		int time=(60*60*24);
		return save(key,obj,time);
	}
	@Redis
	public <T> T readSingle(final String key) {
		int time=(60*60*24);
		return readSingle(key,time);
	}
	@Redis
	public <T> List<T> readList(final String key) {
		int time=(60*60*24);
		return readList(key,time);
	}
	@Redis
	public <T> T readMap(final String key) {
		int time=(60*60*24);
		return readMap(key,time);
	}
	public <T> boolean save(String key,List<T> objList,int time)
			throws ForceException {
		Jedis jedis = null;
		try{
			int count=0;
			while(count<3){
				try{
					jedis = jedisPool.getResource();
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(objList);
					jedis.set(objKey, objValue);
					return true;
				}catch(Exception e){
					logger.error("save(String,List<T>,int)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("save(final String,final List<T>)",e);
			//throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	public <T> boolean save(String key,T obj,int time) throws ForceException {
		Jedis jedis = null;
		try{
			int count=0;
			while(count<3){
				try{
					jedis = jedisPool.getResource();
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(obj);
					jedis.set(objKey, objValue);
					return true;
				}catch(Exception e){
					logger.error("save(String,T,int)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
		}catch(Exception e){
			logger.error("save(final String,final T)",e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	public <T> T readSingle(String key,int time) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			byte[] objKey = ObjectsTranscoder.serialize(key);
			jedis.expire(key,time);
			byte[] value = jedis.get(objKey);
			T t = ObjectsTranscoder.deserializeObj(value);
			return t;
		}catch(Exception e){
			logger.error("readSingle(String)",e);
			return null;
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> List<T> readList(String key,int time) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			byte[] objKey = ObjectsTranscoder.serialize(key);
/*			jedis.expire(key,time);
*/			byte[] value = jedis.get(objKey);
			List<T> t = ObjectsTranscoder.deserialize(value);
			return t;
		}catch(Exception e){
			logger.error("readList(String)",e);
			return null;
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> T readMap(String key, int time) {
		return readSingle(key,time);
	}
	public <T> LinkedList<T> readLinkList(String key,int time) {
		Jedis jedis = null;
		try{	
			jedis = jedisPool.getResource();
			byte[] objKey = ObjectsTranscoder.serialize(key);
			byte[] value = jedis.get(objKey);
			LinkedList<T> t=ObjectsTranscoder.deserializeLink(value);
			return t;
		}catch(Exception e){
			logger.error("readList(String)",e);
			return null;
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	public <T> boolean save(String key,LinkedList<T> objList,int time)
			throws ForceException {
		Jedis jedis = null;
		try{
			int count=0;
			while(count<3){
				try{
					jedis = jedisPool.getResource();
					byte[] objKey = ObjectsTranscoder.serialize(key);
					byte[] objValue = ObjectsTranscoder.serialize(objList);
					jedis.expire(key,time);
					jedis.set(objKey, objValue);
					return true;
				}catch(Exception e){
					logger.error("save(String,LinkedList<T>,int)",e);
					if(count==2)
						throw new ManagerException(e);
					count++;
				}
			}
			return false;
		}catch(Exception e){
			logger.error("save(final String,final LinkedList<T>,final long)",e);
			throw new ManagerException(e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	@Override
	public void cleanAllAppraserCache(String projectName) {
		int totalCount=Integer.parseInt(constantBean.get("containerNum"));
		for(int i=0;i<totalCount;i++){
			Map<String,String> mapObj=this.readObjMap(projectName+i,String.class);
			if(mapObj==null||mapObj.isEmpty())continue;
			for(String redisKey:mapObj.keySet()){
				try{
					this.deleteStr(redisKey);
				}catch(Exception e){
					logger.error("deleteAppriseAllInfoInCache(String)-"+redisKey,e);
				}
			}
			try{
				this.deleteStr(projectName+i);
			}catch(Exception e){
				logger.error("deleteAppriseAllInfoInCache(String)",e);
			}
		}
	}
	@Override
	public void cleanAllAppraserCache(String projectName,String containerNums) {
		int totalCount=Integer.parseInt(containerNums);
		for(int i=0;i<totalCount;i++){
			Map<String,String> mapObj=this.readObjMap(projectName+i,String.class);
			if(mapObj==null||mapObj.isEmpty())continue;
			for(String redisKey:mapObj.keySet()){
				try{
					this.deleteStr(redisKey);
				}catch(Exception e){
					logger.error("deleteAppriseAllInfoInCache(String)-"+redisKey,e);
				}
			}
			try{
				this.deleteStr(projectName+i);
			}catch(Exception e){
				logger.error("deleteAppriseAllInfoInCache(String)",e);
			}
		}
	}
}
