package com.flyrish.hades.service.ext.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;
import org.nestframework.utils.NestUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.SqlDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.ObjectsTranscoder;

public class LatestEvaluationRecordExtImpl implements ILatestEvaluationRecordExt {
	
	protected  Logger logger=Logger.getLogger(LatestEvaluationRecordExtImpl.class);
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private IRedisServiceExt redisServiceExt;
	
	public ConstantBean constantBean;
	
	public ConstantBean getConstantBean() {
		return constantBean;
	}

	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}

	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}

	public void setheadMasterRecordToCache(String campuseid,String levelcode,String username,
			 String columNum,String proKey, String columnName,
			String studentName, String stuEduid, Date date) {
		//放一部分到班主任的缓存中
		String masterKey=MessageFormat.format(Constant.M_MASTER_FLAG,campuseid+"_"+levelcode+"_"+username);
		commonMethodToCache(masterKey,columNum,proKey,Constant.APPRASER_MASTER,columnName,studentName,date);
		//同时也放到学生的缓存中
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		commonMethodToCache(stuEduidkey,columNum,proKey,Constant.APPRASER_MASTER,columnName,studentName,date);
	}

	public void setteacherRecordToCache(String campuseid,String levelcode,String username,String columNum,String proKey, String columnName,
			String studentName, String stuEduid, Date date) {
		//放一部分到任课老师的缓存中
		String teacherKey=MessageFormat.format(Constant.T_TEACHER_FLAG,campuseid+"_"+levelcode+"_"+username);
		commonMethodToCache(teacherKey,columNum,proKey,Constant.APPRASER_TEACHER_Z,columnName,studentName,date);
		//同时也放到学生的缓存中
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		commonMethodToCache(stuEduidkey,columNum,proKey,Constant.APPRASER_TEACHER_Z,columnName,studentName,date);
	}

	public void setstudentRecordToCache(String username,String columNum,String proKey,String columnName,
			String studentName, Date date) {
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,username);
		commonMethodToCache(stuEduidkey,columNum,proKey,Constant.me_apprasialidentify,columnName,studentName,date);
	}

	public void setclassMateRecordToCache(String username,String columNum,String proKey,String columnName,
			String studentName, String stuEduid, Date date) {
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		commonMethodToCache(stuEduidkey,columNum,proKey,Constant.APPRASER_STUDENT,columnName,studentName,date);
	}

	public void setparentRecordToCache(String columnName,String columNum,String proKey,String studentName,
			String stuEduid, Date date) {
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		commonMethodToCache(stuEduidkey,columNum,proKey,Constant.APPRASER_PARENT,columnName,studentName,date);
	}
	/**
	 * 共有的底层方法
	 * @param masterKey 放入缓存的key
	 * @param roleName 角色名称 如：班主任，任课老师
	 * @param columnName 栏目名称  如：学业成就
	 * @param studentName 学生名称
	 * @param dateStr 被评价时间
	 */
	private void commonMethodToCache(String masterKey,String columNum,String proKey,String roleName,String columnName,String studentName,Date date){
		String dateStr=sdf.format(date);
		String recordMsg=MessageFormat.format(Constant.R_RECORD_MSG,roleName,columnName,studentName,dateStr,columNum,proKey);
		//System.out.println(masterKey+"::"+recordMsg);
		try {
			//先读取缓存中相对应的集合
			LinkedList<String> queue=redisServiceExt.readLinkList(masterKey,1000*60*60*24);
			if(queue==null){
				queue = new LinkedList<String>();
			}
			/*if(queue.size()>25){
				queue.poll();
			}*/
			queue.offer(recordMsg);
			//然后再放入缓存中
			redisServiceExt.save(masterKey,queue,1000*60*60*24);
		} catch (ForceException e) {
			logger.error("commonMethodToCache(String,String,String,String,String)",e);
		}
	}
	
	@Override
	public void deleteStatisticsRecordDataInCache(String redisKey,
			String oneLevelNum) {
		try {
			//先读取缓存中相对应的集合
			LinkedList<String> queue=redisServiceExt.readLinkList(redisKey,1000*60*60*24);
			List<String> otherList=new LinkedList<String>();
			for(String record:queue){
				if(NestUtil.isEmpty(record))continue;
				String str=record.split("_")[2].split("@")[0];
				if(NestUtil.isEmpty(str)) continue;
				int length=str.length();
				if(length==4){
					if(oneLevelNum.equals(str.substring(0,2))){
						otherList.add(record);
					}
					if(Constant.ACADEMIC_ACHIEVEMENT.equals(oneLevelNum)&&Constant.TYPE_KE_CHENG_PINGYU.equals(str)){
						otherList.add(record);
					}
				}else if(length==2){
					if(oneLevelNum.equals(str.substring(0,1))){
						otherList.add(record);
					}
				}
			}
			queue.removeAll(otherList);
			redisServiceExt.save(redisKey,queue);
		} catch (Exception e) {
			logger.error("deleteStatisticsRecordDataInCache(String,String)",e);
		}
	}
	@Override
	public Integer queryStatisticsRecordDataInCache(String redisKey,
			String oneLevelNum) {
		Integer count=0;
		try {
			//先读取缓存中相对应的集合
			LinkedList<String> queue=redisServiceExt.readLinkList(redisKey,1000*60*60*24);
			if(queue==null||queue.isEmpty()||NestUtil.isEmpty(oneLevelNum)){
				return 0;
			}
			for(String record:queue){
				if(NestUtil.isEmpty(record))continue;
				String str=record.split("_")[2].split("@")[0];
				if(NestUtil.isEmpty(str)) continue;
				int length=str.length();
				if(length==4){
					if(oneLevelNum.equals(str.substring(0,2)))
						count++;
				}else if(length==2){
					if(oneLevelNum.equals(str.substring(0,1)))
						count++;
				}
			}
			return count;
		} catch (Exception e) {
			logger.error("queryStatisticsRecordDataInCache(String,String)",e);
		}
		return 0;
	}
	public void deleteheadMasterRecordInCache(String campuseid,
			String levelcode, String username,String stuEduid,String columNum, String proKey) {
		//删除班主任的缓存记录
		String masterKey=MessageFormat.format(Constant.M_MASTER_FLAG,campuseid+"_"+levelcode+"_"+username);
		deleteCommonMethod(masterKey,columNum,proKey);
		//删除学生的缓存记录
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		deleteCommonMethod(stuEduidkey,columNum,proKey);
	}

	public void deleteteacherRecordInCache(String campuseid, String levelcode,
			String username,String stuEduid,String columNum, String proKey) {
		//删除任课老师的缓存记录
		String teacherKey=MessageFormat.format(Constant.T_TEACHER_FLAG,campuseid+"_"+levelcode+"_"+username);
		deleteCommonMethod(teacherKey,columNum,proKey);
		//删除学生的缓存记录
		String stuEduidkey=MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid);
		deleteCommonMethod(stuEduidkey,columNum,proKey);
	}

	public void deletestudentRecordInCache(String username, String columNum,
			String proKey) {
		deleteCommonMethod(MessageFormat.format(Constant.S_STUDENT_FLAG,username),columNum,proKey);
	}

	public void deleteclassMateRecordInCache(String stuEduid, String columNum,
			String proKey) {
		deleteCommonMethod(MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid),columNum,proKey);
	}

	public void deleteparentRecordToCache(String stuEduid, String columNum,
			String proKey) {
		deleteCommonMethod(MessageFormat.format(Constant.S_STUDENT_FLAG,stuEduid),columNum,proKey);
	}
	
	private void deleteCommonMethod(String masterKey,String columNum,String proKey){
		try{
			LinkedList<String> queue=redisServiceExt.readLinkList(masterKey,1000*60*60*24);
			//如果缓存里为空，则直接返回
			if(queue==null||queue.isEmpty())return;
			
			String flagKey=columNum+"@"+proKey;
			
			LinkedList<String> newqueue=new LinkedList<String>();
			
			for(String str:queue){
				if(NestUtil.isEmpty(str)) continue;
				String newStr=str.substring(str.lastIndexOf("_")+1);
				if(!flagKey.equals(newStr))
					newqueue.offer(str);
			}
			redisServiceExt.save(masterKey,newqueue,1000*60*60*24);
		}catch (Exception e) {
			logger.error("deleteCommonMethod(String,String,String)",e);
		}
	}
	@Override
	public <T> T queryRecodeInCache(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			String prokey,Class clazz) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||NestUtil.isEmpty(prokey))
			return null;
		String redisKey1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey1,Map.class);
		if(valueMap==null||valueMap.isEmpty()) return null;
		Map<String,JSONObject> values=valueMap.get(prokey);
		if(values==null)return null;
		JSONObject jsonObj=values.get(Constant.R_REDIS_NEW_CACHEKEY);
		if(jsonObj==null)return null;
		return (T)jsonObj.parseObject(jsonObj.toJSONString(), clazz);
	}
	
	@Override
	public <T> void updateRecodeInCacheByProKey(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			String prokey, T t,ISqlElement sqlDemo,String signdate) {
		//如果更新的对象是Null，则直接返回
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||NestUtil.isEmpty(prokey)
				||sqlDemo==null
				||t==null)
			throw new ManagerException("edu_id、termid、two_part_id、appraseridentify、appraiserrid、prokey、sqlDemo,t任何一个不能为Null");
		try{
			if(NestUtil.isNotEmpty(signdate)){
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				sf.setLenient(false);
				sf.parse(signdate);
				String[] signdateStr=signdate.split("-");
				int yyyylength=signdateStr[0].length();
				int mmlength=signdateStr[1].length();
				int ddlength=signdateStr[2].length();
				if(yyyylength!=4||mmlength!=2||ddlength!=2){
					throw new Exception();
				}
			}
		}catch(Exception e){
			throw new ManagerException("时间格式不对");
		}
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("update"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入update类型的Sql语句");
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		T oldt=queryRecodeInCache(edu_id,termid,two_part_id,appraseridentify,appraiserrid,prokey,t.getClass());
		//如果不为空，则进行字段比较
		if(oldt!=null){
			byte[] objKey = ObjectsTranscoder.serialize(oldt);
			T oldpreV=ObjectsTranscoder.deserializeObj(objKey);
			//放入缓存中，处理缓存中Key1,Key2,Key3之间的关系
			doWith3KeyRelationship(edu_id, termid, two_part_id, appraseridentify,appraiserrid,t.getClass());
			//将更新变更的值
			compareObjAndSetValue(oldt,t);
			//将对象重新放入缓存中
			Map<String,T> mapObj=new HashMap<String,T>();
			mapObj.put(Constant.R_REDIS_OLD_CACHEKEY, oldpreV);
			mapObj.put(Constant.R_REDIS_NEW_CACHEKEY,oldt);
			String key1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
			Map<String,JSONObject> dtoMapValue=redisServiceExt.readObjMapValue(key1,prokey,Map.class);
			try{
				redisServiceExt.saveObjMap(key1,prokey,mapObj);
				putSqlInQueue(sqlDemo, key1,prokey,Constant.S_SQL_QUEUE+two_part_id,"1");
			}catch(Exception e){
				logger.error("updateRecodeInCacheByProKey(String,String,String,String,String,String,T,ISqlElement)",e);
				//回退到之前的值
				if(dtoMapValue!=null){
					redisServiceExt.saveObjMap(key1,prokey,dtoMapValue);
				}
				throw new ManagerException(e);
			}
		}
	}

	private void putSqlInQueue(ISqlElement sqlDemo, String key1,String proKey,String redisKey,String whichOne) {
		//将sql放入队列中
		SqlDto sqldto=installSqlDto(sqlDemo);
		sqldto.setSqlKey(key1);
		sqldto.setProKey(proKey);
		sqldto.setWhichOne(whichOne);
		//如果放入队列失败，则尝试放入redisKey
		redisServiceExt.insertQueueStr(redisKey,sqldto);
	}
	private  SqlDto installSqlDto(ISqlElement sqlDemo){
		SqlDto dto=new SqlDto();
		dto.setParams(sqlDemo.getParams());
		dto.setParamsMap(sqlDemo.getParamsMap());
		dto.setSqlStr(sqlDemo.getSql());
		return dto;
	}
	/**
	 * 处理key1,key2,key3关系
	 * 规则：如果Key3中不包含Key2，则向Key3中加入Key2,如果Key2中不包含Key1(appraseridentify,appraiserrid)，则向Key2中加入Key1
	 * @param edu_id 教育Id
	 * @param termid 学期标识号
	 * @param two_part_id 二级栏目标识
	 * @param appraseridentify 身份类型
	 * @param appraiserrid 评价人
	 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid任何一个为Null都将抛出异常
	 */
	private <T> void doWith3KeyRelationship(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,Class clazz) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid))
			throw new ManagerException("edu_id、termid、two_part_id、appraseridentify、appraiserrid任何一个不能为Null");
		
		redisServiceExt.saveObjMap(edu_id+"_"+termid+"_"+two_part_id,appraseridentify+"_"+appraiserrid,"k");
		recodeKeyInTotalKey(edu_id+"_"+termid+"_"+two_part_id,Constant.R_REDIS_CACHE_TOTALKEY);
		/*String redisKey1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey1,Map.class);
		if(valueMap==null){
			//recodeKeyInTotalKey(edu_id+"_"+termid+"_"+two_part_id);
			//查询Key2中是否在缓存中有edu_id_termid_two_part_id以及对应的值
			Map<String,String> key2Map=redisServiceExt.readObjMap(edu_id+"_"+termid+"_"+two_part_id,String.class);
			if(key2Map==null||!key2Map.containsKey(appraseridentify+"_"+appraiserrid)){
				redisServiceExt.saveObjMap(edu_id+"_"+termid+"_"+two_part_id,appraseridentify+"_"+appraiserrid,"k");
				recodeKeyInTotalKey(edu_id+"_"+termid+"_"+two_part_id,Constant.R_REDIS_CACHE_TOTALKEY);
			}
		}*/
	}
	
	@Override
	public <T> void addRecodeInCacheByProKey(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			String prokey, T t,ISqlElement sqlDemo,String signdate) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||NestUtil.isEmpty(prokey)
				||sqlDemo==null)
			throw new ManagerException("edu_id、termid、two_part_id、appraseridentify、appraiserrid、prokey、sqlDemo任何一个不能为Null");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("insert"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入insert类型的Sql语句");
		try{
			if(NestUtil.isNotEmpty(signdate)){
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				sf.setLenient(false);
				sf.parse(signdate);
				String[] signdateStr=signdate.split("-");
				int yyyylength=signdateStr[0].length();
				int mmlength=signdateStr[1].length();
				int ddlength=signdateStr[2].length();
				if(yyyylength!=4||mmlength!=2||ddlength!=2){
					throw new Exception();
				}
			}
		}catch(Exception e){
			throw new ManagerException("时间格式不对");
		}
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//放入缓存中，处理缓存中Key1,Key2,Key3之间的关系
		doWith3KeyRelationship(edu_id, termid, two_part_id, appraseridentify,appraiserrid,t.getClass());
		//将key放入缓存容器中
		String key1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(key1,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(key1,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		Map<String,T> mapObj=new HashMap<String,T>();
		mapObj.put(Constant.R_REDIS_NEW_CACHEKEY,t);
		mapObj.put(Constant.R_REDIS_OLD_CACHEKEY,t);
		try{
			redisServiceExt.saveObjMap(key1,prokey,mapObj);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,key1,prokey,Constant.S_SQL_QUEUE+two_part_id,"1");
		}catch(Exception e){
			logger.error("addRecodeInCacheByProKey(String,String,Stringd,String,String,String,T,ISqlElement)",e);
			redisServiceExt.delObjMap(key1,prokey);
			throw new ManagerException(e);
		}
	}
	@Override
	public <T> void addRecodeInCacheByProKey(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			String prokey, T t) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||NestUtil.isEmpty(prokey))
			throw new ManagerException("edu_id、termid、two_part_id、appraseridentify、appraiserrid、prokey任何一个不能为Null");
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//放入缓存中，处理缓存中Key1,Key2,Key3之间的关系
		doWith3KeyRelationship(edu_id, termid, two_part_id, appraseridentify,appraiserrid,t.getClass());
		//将key放入缓存容器中
		String key1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(key1,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(key1,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		Map<String,T> mapObj=new HashMap<String,T>();
		mapObj.put(Constant.R_REDIS_NEW_CACHEKEY,t);
		mapObj.put(Constant.R_REDIS_OLD_CACHEKEY,t);
		try{
			redisServiceExt.saveObjMap(key1,prokey,mapObj);
		}catch(Exception e){
			logger.error("addRecodeInCacheByProKey(String,String,Stringd,String,String,String,T)",e);
			redisServiceExt.delObjMap(key1,prokey);
			throw new ManagerException(e);
		}
	}
	@Override
	public <T> void delRecodeInCacheByProKey(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			String prokey,ISqlElement sqlDemo,Class clazz) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||NestUtil.isEmpty(prokey)
				||sqlDemo==null)
			throw new ManagerException("edu_id、termid、two_part_id、appraseridentify、appraiserrid、prokey、sqlDemo任何一个不能为Null");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("delete"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入delete类型的Sql语句");
		String redisKey1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey1,Map.class);
		//将执行sql放入队列中
		//putSqlInQueue(sqlDemo,redisKey1,prokey,Constant.S_SQL_QUEUE+two_part_id,"1");
		if(valueMap==null||valueMap.isEmpty()) return;
		Map<String,JSONObject> values=valueMap.get(prokey);
		if(values==null)return;
		//获取最新值
		JSONObject oldT=values.get(Constant.R_REDIS_NEW_CACHEKEY);
		JSONObject oldT2=values.get(Constant.R_REDIS_OLD_CACHEKEY);
		//将新值放入旧值中
		values.put(Constant.R_REDIS_OLD_CACHEKEY,oldT);
		//删除就值对应的key
		values.remove(Constant.R_REDIS_NEW_CACHEKEY);
		try{
			//放回缓存
			redisServiceExt.saveObjMap(redisKey1,prokey,values);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,redisKey1,prokey,Constant.S_SQL_QUEUE+two_part_id,"1");
		}catch(Exception e){
			logger.error("delRecodeInCacheByProKey(String,String,String,String,String,String,ISqlElement)",e);
			values.put(Constant.R_REDIS_NEW_CACHEKEY, oldT);
			values.put(Constant.R_REDIS_OLD_CACHEKEY, oldT2);
			redisServiceExt.saveObjMap(redisKey1,prokey,values);
			throw new ManagerException(e);
		}
	}
	/**
	 * 将新对象中不为空的字段的值复制给旧对象
	 * @param oldt 旧对象
	 * @param newt 新对象
	 */
	private <T> void compareObjAndSetValue(T oldt,T newt){
		Class oldtClazz = oldt.getClass();
		Class newtClazz = newt.getClass();
		List<String> ofieldList = getFieldName(oldtClazz);
		for(String fieldName:ofieldList){
			if("serialVersionUID".equals(fieldName))continue;
			Method method= null;
			try {
				Field nfield=newtClazz.getDeclaredField(fieldName);
				Field ofield=oldtClazz.getDeclaredField(fieldName);
				ofield.setAccessible(true);
				nfield.setAccessible(true);
				Object objValue=nfield.get(newt);
				if(objValue==null)continue;
				method = oldtClazz.getDeclaredMethod(setterName(fieldName),objValue.getClass());
				method.invoke(oldt,objValue);
			} catch (Exception e) {
				logger.error("compareObjAndSetValue(T,T)",e);
			}
		}
	}
	private List<String> getFieldName(Class classN){
		Field[] fields = classN.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for(Field field:fields){
			fieldList.add(field.getName());
		}
		return fieldList;
	}
	private String setterName(String fieldName){
		StringBuffer strB = new StringBuffer();
		if(StringUtils.isNotBlank(fieldName)){
			strB.append("set").
			append(fieldName.substring(0, 1).toUpperCase()).
			append(fieldName.substring(1));
		}
		return strB.toString();
	}
	/**
	 * 组装Key1字符串，模型：学生教育id_学期标识号_二级栏目号_评价人身份_评价人标识号
	 * @param edu_id 学生教育id
	 * @param termid 学期标识号
	 * @param two_part_id 二级栏目号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @return Key1
	 */
	private String installRedisKey1(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid){
		StringBuffer sbStr=new StringBuffer();
		String preKey1=installRedisKey2(edu_id,termid,two_part_id);
		sbStr.append(preKey1).append("_").append(appraseridentify).append("_").append(appraiserrid);
		return sbStr.toString();
	}
	/**
	 * 组装Key2，模型：学生教育Id_学期标识号_二级栏目号
	 * @param edu_id 学生教育Id
	 * @param termid 学期标识号
	 * @param two_part_id 二级栏目号
	 * @return Key2
	 */
	private String installRedisKey2(String edu_id, String termid,
			String two_part_id){
		StringBuffer sbStr=new StringBuffer();
		sbStr.append(edu_id).append("_").append(termid).append("_").append(two_part_id);
		return sbStr.toString();
	}

	@Override
	public <T> List<T> queryAttachFileInCache(String foreignKey, String tablename,Class clazz) {
		//附件表的key组成规则：totalAttachFileKey+tablename+attachid eg:totalAttachFileKey_tablename_2222=》map<String,Object>
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(tablename))
			return null;
		//附件表的key组成规则：totalAttachFileKey+tablename+foreignKey eg:totalAttachFileKey_tablename_2222=》map<String,Object>
		String redisKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		//查询出redisKey1对应的所有评价记录
		//Map<proKey,Map<key={new,old},value={评价记录}>>
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey,Map.class);
		if(valueMap==null||valueMap.isEmpty()) return null;
		List<T> attachfileList=new ArrayList<T>(0);
		for(Map<String,JSONObject> objMap:valueMap.values()){
			JSONObject jsonObj=objMap.get(Constant.R_REDIS_NEW_CACHEKEY);
			if(jsonObj==null)continue;
			T t=(T)JSON.parseObject(jsonObj.toJSONString(),clazz);
			if(t==null)continue;
			attachfileList.add(t);
		}
		return attachfileList;
	}
	@Override
	public <T> T queryAttachFileInCache(String foreignKey, String attachid,
			String tablename,Class clazz) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(attachid))
			return null;
		//附件表的key组成规则：totalAttachFileKey+tablename+foreignKey eg:totalAttachFileKey_tablename_2222=》map<String,Object>
		String redisKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		//查询出redisKey1对应的所有评价记录
		//Map<proKey,Map<key={new,old},value={评价记录}>>
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey,Map.class);
		if(valueMap==null||valueMap.isEmpty()) return null;
		Map<String,JSONObject> objMap=valueMap.get(attachid);
		if(objMap==null||valueMap.isEmpty())return null;
		JSONObject jsonObj=objMap.get(Constant.R_REDIS_NEW_CACHEKEY);
		if(jsonObj==null)return null;
		return (T)JSON.parseObject(jsonObj.toJSONString(),clazz);
	}

	@Override
	public <T> void addAttachFileInCache(String foreignKey,String attachid, T t,
			String tablename,String two_part_id, ISqlElement sqlDemo) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(attachid)
				||t==null
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("String,String,T,String,ISqlElement中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("insert"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入insert类型的Sql语句");
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//附件表的key组成规则：totalAttachFileKey+levelCode+attachid eg:totalAttachFileKey_2012001_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(attachKey,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(attachKey,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		try{
			Map<String,T> objMap=new HashMap<String,T>();
			objMap.put(Constant.R_REDIS_NEW_CACHEKEY,t);
			objMap.put(Constant.R_REDIS_OLD_CACHEKEY,t);
			redisServiceExt.saveObjMap(attachKey, attachid,objMap);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,attachKey,attachid,Constant.S_SQL_QUEUE+two_part_id,"2");
		}catch(Exception e){
			logger.error("addAttachFileInCache(String,String,T,String,ISqlElement)",e);
			redisServiceExt.delObjMap(attachKey,attachid);
			throw new ManagerException(e);
		}
	}
	@Override
	public <T> void addAttachFileInCache(String foreignKey,String attachid, T t,
			String tablename) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(attachid)
				||t==null
				||NestUtil.isEmpty(tablename))
			throw new ManagerException("String,String,T,String,ISqlElement中任何一个都不能为空");
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//附件表的key组成规则：totalAttachFileKey+tablename+foreignKey+attachid eg:totalAttachFileKey_attachfile_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(attachKey,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(attachKey,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		try{
			Map<String,T> objMap=new HashMap<String,T>();
			objMap.put(Constant.R_REDIS_NEW_CACHEKEY,t);
			objMap.put(Constant.R_REDIS_OLD_CACHEKEY,t);
			redisServiceExt.saveObjMap(attachKey, attachid,objMap);
		}catch(Exception e){
			logger.error("addAttachFileInCache(String,String,T,String)",e);
			redisServiceExt.delObjMap(attachKey,attachid);
			throw new ManagerException(e);
		}
	}
	@Override
	public <T> void deleteAttachFileInCache(String foreignKey,String attachid,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz) {
		if(NestUtil.isEmpty(attachid)
				||NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("foreignKey,attachid,tablename,sqlDemo中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("delete"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入delete类型的Sql语句");
		//附件表的key组成规则：totalAttachFileKey+tablename+attachid eg:totalAttachFileKey_tablename_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		JSONObject oldT2=null;
		JSONObject oldT=null;
		Map<String,JSONObject>mapValue=null;
		try{
			mapValue=redisServiceExt.readObjMapValue(attachKey,attachid,Map.class);
			if(mapValue==null)return;
			//获取最新值
			oldT=mapValue.get(Constant.R_REDIS_NEW_CACHEKEY);
			//获取就值
			oldT2=mapValue.get(Constant.R_REDIS_OLD_CACHEKEY);
			//将新值放入旧值中
			mapValue.put(Constant.R_REDIS_OLD_CACHEKEY,oldT);
			//删除就值对应的key
			mapValue.remove(Constant.R_REDIS_NEW_CACHEKEY);
			//重新放入缓存中
			redisServiceExt.saveObjMap(attachKey, attachid,mapValue);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,attachKey,attachid,Constant.S_SQL_QUEUE+two_part_id,"2");
		}catch(Exception e){
			logger.error("deleteAttachFileInCache(String,String,String,ISqlElement)",e);
			if(mapValue!=null){ 
				mapValue.put(Constant.R_REDIS_NEW_CACHEKEY, oldT);
				mapValue.put(Constant.R_REDIS_OLD_CACHEKEY, oldT2);
				redisServiceExt.saveObjMap(attachKey,attachid,mapValue);
			}
			throw new ManagerException(e);
		}
	}

	@Override
	public <T> void deleteAttachFileAllByforeignKey(String foreignKey,
			String tablename,String two_part_id, ISqlElement sqlDemo,Class clazz) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("foreignKey,tablename,sqlDemo中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("delete"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入delete类型的Sql语句");
		String attachKey=Constant.R_REDIS_CACHE_ATTACHFILE+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> objMap=redisServiceExt.readObjMap(attachKey,Map.class);
		if(objMap==null)return;
		for(String key:objMap.keySet()){
			deleteAttachFileInCache(foreignKey,key,tablename,two_part_id,sqlDemo,clazz);
		}
	}

	@Override
	public <T> T queryChildrenObjectInCache(String foreignKey,String prokey,String tablename,Class clazz) {
		//key组成规则：totalChildrenObjectKey_tablename_prokey
		String childrenKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		 Map<String,Map<String,JSONObject>> mapObj=redisServiceExt.readObjMap(childrenKey,Map.class);
		 if(mapObj==null)return null;
		 Map<String,JSONObject> obj=mapObj.get(prokey);
		 if(obj==null)return null;
		 JSONObject jsonObj=obj.get(Constant.R_REDIS_NEW_CACHEKEY);
		 if(jsonObj==null) return null;
		 return (T)JSON.parseObject(jsonObj.toJSONString(), clazz);
	}


	@Override
	public <T> List<T> queryChildrenObjectListInCache(String foreignKey,
			String tablename,Class clazz) {
		//附件表的key组成规则：totalChildrenObjectKey_tablename_prokey eg:totalChildrenObjectKey_tablename_2222=》map<String,Object>
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(tablename))
				return null;
		String redisKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		//查询出redisKey1对应的所有评价记录
		//Map<proKey,Map<key={new,old},value={评价记录}>>
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey,Map.class);
		if(valueMap==null||valueMap.isEmpty()) return null;
			List<T> childrenObjectList=new ArrayList<T>(0);
			for(Map<String,JSONObject> objMap:valueMap.values()){
				JSONObject jsonObj=objMap.get(Constant.R_REDIS_NEW_CACHEKEY);
				if(jsonObj==null)continue;
				T t=(T)JSON.parseObject(jsonObj.toJSONString(),clazz);
				if(t==null)continue;
				childrenObjectList.add(t);
			}
		return childrenObjectList;
	}

	@Override
	public <T> void updateChildrenObjectlInCache(String foreignKey,String prokey,T t,String tablename,String two_part_id,
			ISqlElement sqlDemo,String signdate) {
		//如果更新的对象是Null，则直接返回
				if(NestUtil.isEmpty(foreignKey)
						||NestUtil.isEmpty(prokey)
						||NestUtil.isEmpty(tablename)
						||sqlDemo==null
						||t==null)
					throw new ManagerException("foreignKey、prokey、t、tablename、sqlDemo任何一个不能为Null");
				try{
					if(NestUtil.isNotEmpty(signdate)){
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
						sf.setLenient(false);
						sf.parse(signdate);
						String[] signdateStr=signdate.split("-");
						int yyyylength=signdateStr[0].length();
						int mmlength=signdateStr[1].length();
						int ddlength=signdateStr[2].length();
						if(yyyylength!=4||mmlength!=2||ddlength!=2){
							throw new Exception();
						}
					}
				}catch(Exception e){
					throw new ManagerException("时间格式不对");
				}
				if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("update"))
					throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入update类型的Sql语句");
				String clazzName=t.getClass().getName();
				if(!clazzName.contains("Cache"))
					throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
				T oldT2=null;
				T oldpreV=null;
				//Map<String,JSONObject>mapValue=null;
				Map<String,T> mapV=null;
				String redisKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
				try{
					Map<String,JSONObject> mapValue=redisServiceExt.readObjMapValue(redisKey,prokey,Map.class);
					if(mapValue==null)return;
					JSONObject jsonObj=mapValue.get(Constant.R_REDIS_NEW_CACHEKEY);
					T oldT=null;
					if(jsonObj!=null)
						oldT=(T)JSON.parseObject(jsonObj.toJSONString(),t.getClass());
					
					byte[] objKey = ObjectsTranscoder.serialize(oldT);
					
					oldpreV=ObjectsTranscoder.deserializeObj(objKey);
					//获取最新值
					//获取就值
					JSONObject jsonObj2=mapValue.get(Constant.R_REDIS_OLD_CACHEKEY);
					if(jsonObj2!=null)
						oldT2=(T)JSON.parseObject(jsonObj2.toJSONString(),t.getClass());
					
					mapV=new HashMap<String,T>();
					//将新值放入旧值中
					mapV.put(Constant.R_REDIS_OLD_CACHEKEY,oldpreV);
					//将更新变更之前的值放入缓存中
					compareObjAndSetValue(oldT,t);
					//将变更之后的值放入缓存中
					mapV.put(Constant.R_REDIS_NEW_CACHEKEY,oldT);
					
					//重新放入缓存中
					redisServiceExt.saveObjMap(redisKey, prokey,mapV);
					//将执行sql放入队列中
					putSqlInQueue(sqlDemo,redisKey,prokey,Constant.S_SQL_QUEUE+two_part_id,"3");
				}catch(Exception e){
					logger.error("updateChildrenObjectlInCache(String,String,T t,String,ISqlElement)",e);
					if(mapV!=null){ 
						mapV.put(Constant.R_REDIS_NEW_CACHEKEY, oldpreV);
						mapV.put(Constant.R_REDIS_OLD_CACHEKEY, oldT2);
						redisServiceExt.saveObjMap(redisKey,prokey,mapV);
					}
					throw new ManagerException(e);
				}
	}

	@Override
	public <T> void deleteChildrenObjectInCache(String foreignKey,String prokey,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(prokey)
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("String,ISqlElement中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("delete"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入delete类型的Sql语句");
		//附件表的key组成规则：totalAttachFileKey+tablename+attachid eg:totalAttachFileKey_tablename_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		JSONObject oldT2=null;
		JSONObject oldT=null;
		Map<String,JSONObject>mapValue=null;
		try{
			mapValue=redisServiceExt.readObjMapValue(attachKey,prokey,Map.class);
			if(mapValue==null)return;
			//获取最新值
			oldT=mapValue.get(Constant.R_REDIS_NEW_CACHEKEY);
			//获取就值
			oldT2=mapValue.get(Constant.R_REDIS_OLD_CACHEKEY);
			//将新值放入旧值中
			mapValue.put(Constant.R_REDIS_OLD_CACHEKEY,oldT);
			//删除就值对应的key
			mapValue.remove(Constant.R_REDIS_NEW_CACHEKEY);
			//重新放入缓存中
			redisServiceExt.saveObjMap(attachKey,prokey,mapValue);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,attachKey,prokey,Constant.S_SQL_QUEUE+two_part_id,"3");
		}catch(Exception e){
			logger.error("deleteChildrenObjectInCache(String,String,String,ISqlElement)",e);
			if(mapValue!=null){ 
				mapValue.put(Constant.R_REDIS_NEW_CACHEKEY, oldT);
				mapValue.put(Constant.R_REDIS_OLD_CACHEKEY, oldT2);
				redisServiceExt.saveObjMap(attachKey,prokey,mapValue);
			}
			throw new ManagerException(e);
		}
	}

	@Override
	public <T> void deleteChildrenObjectInCache(String foreignKey,
			String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("foreignKey,tablename,sqlDemo中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("delete"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入delete类型的Sql语句");
		String attachKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> objMap=redisServiceExt.readObjMap(attachKey,Map.class);
		if(objMap==null)return;
		for(String key:objMap.keySet()){
			deleteChildrenObjectInCache(foreignKey,key,tablename,two_part_id,sqlDemo,clazz);
		}
	}

	@Override
	public <T> void addChildrenObjectInCache(String foreignKey,String prokey, T t,String tablename,String two_part_id,
			ISqlElement sqlDemo,String signdate) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(prokey)
				||t==null
				||NestUtil.isEmpty(tablename)
				||sqlDemo==null)
			throw new ManagerException("String,String,T,String,ISqlElement中任何一个都不能为空");
		if(NestUtil.isNotEmpty(sqlDemo.getSql())&&!sqlDemo.getSql().contains("insert"))
			throw new ManagerException("所传入的sql与实际调用的接口不一致，请传入insert类型的Sql语句");
		try{
			if(NestUtil.isNotEmpty(signdate)){
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				sf.setLenient(false);
				sf.parse(signdate);
				String[] signdateStr=signdate.split("-");
				int yyyylength=signdateStr[0].length();
				int mmlength=signdateStr[1].length();
				int ddlength=signdateStr[2].length();
				if(yyyylength!=4||mmlength!=2||ddlength!=2){
					throw new Exception();
				}
			}
		}catch(Exception e){
			throw new ManagerException("时间格式不对");
		}
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//附件表的key组成规则：totalAttachFileKey+levelCode+attachid eg:totalAttachFileKey_2012001_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(attachKey,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(attachKey,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		try{
			Map<String,T> objMap=new HashMap<String,T>();
			objMap.put(Constant.R_REDIS_NEW_CACHEKEY,t);
			objMap.put(Constant.R_REDIS_OLD_CACHEKEY,t);
			redisServiceExt.saveObjMap(attachKey,prokey,objMap);
			//将执行sql放入队列中
			putSqlInQueue(sqlDemo,attachKey,prokey,Constant.S_SQL_QUEUE+two_part_id,"3");
		}catch(Exception e){
			logger.error("addChildrenObjectInCache(String,String,T,String,ISqlElement)",e);
			redisServiceExt.delObjMap(attachKey,prokey);
			throw new ManagerException(e);
		}
	}
	@Override
	public <T> void addChildrenObjectInCache(String foreignKey,String prokey, T t,String tablename) {
		if(NestUtil.isEmpty(foreignKey)
				||NestUtil.isEmpty(prokey)
				||t==null
				||NestUtil.isEmpty(tablename))
			throw new ManagerException("String,String,T,String中任何一个都不能为空");
		String clazzName=t.getClass().getName();
		if(!clazzName.contains("Cache"))
			throw new ManagerException("所传入的对象为非法对象，请核对该对象！");
		//附件表的key组成规则：totalAttachFileKey+levelCode+attachid eg:totalAttachFileKey_2012001_2222=》map<String,Object>
		String attachKey=Constant.R_REDIS_CACHE_CHILDRENKEY+"_"+tablename.toLowerCase()+"_"+foreignKey;
		Map<String,Map<String,JSONObject>> tValues=redisServiceExt.readObjMap(attachKey,Map.class);
		if(tValues==null){
			recodeKeyInTotalKey(attachKey,Constant.R_REDIS_CACHE_TOTALKEY);
		}
		try{
			Map<String,T> objMap=new HashMap<String,T>();
			objMap.put(Constant.R_REDIS_NEW_CACHEKEY,t);
			objMap.put(Constant.R_REDIS_OLD_CACHEKEY,t);
			redisServiceExt.saveObjMap(attachKey,prokey,objMap);
		}catch(Exception e){
			logger.error("addChildrenObjectInCache(String,String,T,String)",e);
			redisServiceExt.delObjMap(attachKey,prokey);
			throw new ManagerException(e);
		}
	}
	public void recodeKeyInTotalKey(String redisKey,String proKeyName){
		//把redisKey分别放入十万个容器中
		if(NestUtil.isEmpty(redisKey))return;
		int totalCount=Integer.parseInt(constantBean.get("containerNum"));
		int containerNum=(int)Math.round(Math.abs(redisKey.hashCode())%totalCount);
		try{
			redisServiceExt.saveObjMap(proKeyName+containerNum,redisKey,"t");
		}catch (Exception e) {
			logger.error("recodeKeyInTotalKey(String,String)",e);
		}
	}
	public void recodeKeyInTotalKey(String redisKey,String proKeyName,String containerNums){
		//把redisKey分别放入十万个容器中
		if(NestUtil.isEmpty(redisKey))return;
		int totalCount=Integer.parseInt(containerNums);
		int containerNum=(int)Math.round(Math.abs(redisKey.hashCode())%totalCount);
		try{
			redisServiceExt.saveObjMap(proKeyName+containerNum,redisKey,"t");
		}catch (Exception e) {
			logger.error("recodeKeyInTotalKey(String,String,String)",e);
		}
	}
	@Override
	public <T> List<T> queryRecodeInCache(String edu_id, String termid,
			String two_part_id, Class clazz) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||clazz==null)
			return null;
		//组装一级目录key
		String redisKey=edu_id+"_"+termid+"_"+two_part_id;
		String className=clazz.getName();
		//查询出所对应的值
		Map<String,String> proKeyList=redisServiceExt.readObjMap(redisKey,String.class);
		//如果没有对应的值，则返回null
		if(proKeyList==null||proKeyList.size()==0)return null;
		List<T> sumObjList=new ArrayList<T>();
		for(String key:proKeyList.keySet()){
			//组装key，并获取相应的
			Map<String,Map<String,JSONObject>> mapData=redisServiceExt.readObjMap(redisKey+"_"+key,Map.class);
			if(mapData==null||mapData.isEmpty()||mapData.values()==null)continue;
			for(Map<String,JSONObject> t:mapData.values()){
				if(t==null
						||t.get(Constant.R_REDIS_NEW_CACHEKEY)==null) continue;
				JSONObject jsonObj=t.get(Constant.R_REDIS_NEW_CACHEKEY);
				if(jsonObj==null)continue;
				sumObjList.add((T)JSONObject.parseObject(jsonObj.toJSONString(), clazz));
			}
		}
		return sumObjList;
	}

	@Override
	public <T> List<T> queryRecodeInCache(String edu_id, String termid,
			String two_part_id, String appraseridentify, String appraiserrid,
			Class clazz) {
		if(NestUtil.isEmpty(edu_id)
				||NestUtil.isEmpty(termid)
				||NestUtil.isEmpty(two_part_id)
				||NestUtil.isEmpty(appraseridentify)
				||NestUtil.isEmpty(appraiserrid)
				||clazz==null
				)
			return null;
		String redisKey1=installRedisKey1(edu_id,termid,two_part_id,appraseridentify,appraiserrid);
		//查询出redisKey1对应的所有评价记录
		//Map<proKey,Map<key={new,old},value={评价记录}>>
		Map<String,Map<String,JSONObject>> valueMap=redisServiceExt.readObjMap(redisKey1,Map.class);
		if(valueMap==null||valueMap.isEmpty()) return null;
		List<T> recodeList=new ArrayList<T>(0);
		String className=clazz.getName();
		for(Map<String,JSONObject> objMap:valueMap.values()){
			JSONObject jsonObj=objMap.get(Constant.R_REDIS_NEW_CACHEKEY);
			if(jsonObj==null)continue;
			T t=(T)JSON.parseObject(jsonObj.toJSONString(),clazz);
			if(t==null||!className.equals(t.getClass().getName())) continue;
			recodeList.add(t);
		}
		return recodeList;
	}
	
}
