package com.flyrish.hades.service.ext;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.exception.ForceException;

/**
 * redis对外提供的操作接口
 * @author zengdeqiang
 *
 */
public interface IRedisServiceExt{
	
    /** key value   二进制  
     * 新增或者更新 list集合
     * @param objList
     * @param key
     * @return 添加成功返回 true 反之则抛出强制性异常
     * @exception 如果添加过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
     */  
	 <T> boolean save(String key,List<T> objList) throws ForceException;
	 /** key value   二进制  
	     * 新增或者更新 list集合
	     * @param objList
	     * @param key
	     * @param time 指定存活时间
	     * @return 添加成功返回 true 反之则抛出强制性异常
	     * @exception 如果添加过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
	 */
	 <T> boolean save(String key,List<T> objList,int time) throws ForceException;
	 
	 <T> boolean save(String key,LinkedList<T> objList,int time) throws ForceException;
    /** 
     * 新增或者更新 单个对象
     * @param obj
     * @param key
     * @return 添加成功返回 true 反之则抛出强制性异常
     * @exception 如果添加过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
     */  
	 <T> boolean save(String key,T obj)throws ForceException;
	 /** 
	     * 新增或者更新 单个对象
	     * @param obj
	     * @param key
	     * @param time 指定存活时间
	     * @return 添加成功返回 true 反之则抛出强制性异常
	     * @exception 如果添加过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
	 */  
	 <T> boolean save(String key,T obj,int time)throws ForceException;
    /** 
     * 删除 
     * @param key 通过key删除对应的信息
     * @exception 如果删除过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
     */  
    void delete( String key) throws ForceException;
    /**
     * 删除缓存中以字符串存储的key
     * @param key
     * @throws ForceException
     */
    void deleteStr( String key) throws ForceException;
    /** 
     * 删除多个 
     * @param keys 
     * @exception 如果删除过程中，出现任何错误，则抛出编译异常，需要开发人员强制转换为运行时异常
     */  
    void delete(List<String> keys) throws ForceException;
    /** 
     * 通过key获取单个对象
     * @param keyId 
     * @return  Object
     */  
    <T> T readSingle(String key);
    /** 
     * 通过key获取单个对象
     * @param keyId
     * @param time 指定存活时间
     * @return  Object
     */  
    <T> T readSingle(String key,int time);
    /** 
     * 通过key获取list集合
     * @param key
     * @return  list
     */  
    <T> List<T> readList(String key);
    
    
    <T> LinkedList<T> readLinkList(String key,int time);
    /** 
     * 通过key获取list集合
     * @param key
     * @param time 指定存活时间
     * @return  list
     */  
    <T> List<T> readList(String key,int time);
    /** 
     * 通过key获取map集合
     * @param key
     * @return  map
     */  
    <T> T readMap(String key);
    /** 
     * 通过key获取map集合
     * @param key
     * @param time 指定存活时间
     * @return  map
     */  
    <T> T readMap(String key,int time);
    /**
     * 读取redis中的list
     * @param key
     * @return
     */
    <T> List<T> readObjList( String key);
    /**
     * 向redis相应key里面的list放入相应的值
     * @param key
     * @return
     */
    <T> void saveObjList( String key, T obj);
    
    /**
     * 向redis相应key里面的list放入相应的值(设置存活周期)
     * @param key
     * @return
     */
    <T> void saveObjList( String key, T obj,int time);
    /**
     * 通过key，将指定的Key,value放入redis对应的hash表中
     * @param key redis的key
     * @param mapKey hash表对应的key
     * @param value hash表对应key的值
     */
    <T> void saveObjMap( String key, String mapKey, T value);
    /**
     * 通过redis的key和hash表的mapKey查询对应的hash表值
     * @param key redis的key
     * @param mapKey redis key 对应hash表的key
     * @return 制定hash表中的value
     */
    <T> T readObjMapValue( String key, String mapKey,Class clazz);
    /**
     * 通过制定的redis key表所对应的hash表里所有的值都读出来
     * @param key  redis的key
     * @param clazz 指定转换的对象
     * @return hash表中所有的值
     */
    <T> Map<String,T> readObjMap( String key,Class clazz);
    /**
     * 向队列中插入元素（队列元素为二进制流）
     * @param key redis key
     * @param t 值
     */
    <T> void insertQueue( String key, T t);
    /**
     * 向队列中插入元素（队列元素为json字符串）
     * @param key redis key
     * @param t 值
     */
    <T> void insertQueueStr( String key, T t);
    /**
     * 删除Redis中key指定的map key
     * @param key redis key
     * @param mapKey mapKey
     */
    void delObjMap( String key, String mapKey);
    /**
     * 获取map或者list长度
     * @param key
     * @return
     */
    public Long readLength( String key);
    /**
     * 清理评价缓存接口(默认容器大小)
     * @param projectKeyName 指定缓存容器的Key
     */
    public void cleanAllAppraserCache(String projectKeyName);
    /**
     * 清理评价缓存接口(指定容器大小)
     * @param projectName 指定缓存容器的Key
     * @param containerNums 容器大小
     */
    public void cleanAllAppraserCache(String projectName,String containerNums);
}
