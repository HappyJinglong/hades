package com.flyrish.hades.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.flyrish.hades.dto.SqlDto;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class DemoThread extends Thread {
	protected Logger logger=Logger.getLogger(this.getClass());
	public IRedisServiceExt redisServiceExt;
	public static int count=0;
	public static int threadcount=0;
	public static List<Integer> array=Collections.synchronizedList(new ArrayList<Integer>());
	public DemoThread(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	@Override
	public void run() {
		Integer obj=0;
		while(obj!=null){
			//obj=redisServiceExt.outQueue("wwqueue");
			if(obj!=null)
				array.add(obj);
		}
		threadcount++;
		/*for(int i=0;i<1000;i++){
			System.out.println(i);
			redisServiceExt.insertQueue("wwqueue",i);
		}
		threadcount++;*/
	}
	public static void main(String[]args){
		/*SqlDto dto1=new SqlDto();
		dto1.setSqlKey("aaa");
		dto1.setSqlStr("wwww");
		Object[]objs=new Object[]{"333",222,"222"};
		dto1.setParams(objs);
		SqlDto dto2=new SqlDto();
		dto2.setParams(new Object[]{"aaa",222,"4444"});
		dto2.setSqlKey("bbbbb");
		//dto2.setSqlStr("wewwwe");
		SqlDto t=compareObjAndSetValue(dto1,dto2);
		System.out.println(t.getSqlKey());*/
		/*Map<String,SqlDto> map=new HashMap<String,SqlDto>();
		map.put("aaa",new SqlDto());
		map.put("bbb",new SqlDto());
		map.put("ccc",new SqlDto());
		SqlDto t=map.get("bbb");
		map.remove("bbb");
		for(String str:map.keySet())
			System.out.println(t);*/
		//11161001_20141_
		String sqlKey="11161001_11_3010_家长_1111_jz";
		String[]bb=sqlKey.split("_");
		sqlKey.indexOf("_");
		int count=1;
		String key2;
		String key2Value;
		/*for(String str:bb){
			if()
		}*/
		
	}
	private static <T> T compareObjAndSetValue(T oldt,T newt){
		Class oldtClazz = oldt.getClass();
		T t=null;
		byte[] objKey = ObjectsTranscoder.serialize(oldt);
		t=ObjectsTranscoder.deserializeObj(objKey);
		Class newtClazz = newt.getClass();
		List<String> ofieldList = getFieldName(oldtClazz);
		for(String fieldName:ofieldList){
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
				e.printStackTrace();
			}
		}
		return t;
	}
	public static List<String> getFieldName(Class classN){
		Field[] fields = classN.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for(Field field:fields){
			fieldList.add(field.getName());
		}
		return fieldList;
	}
	public static String getterName(String fieldName){
		StringBuffer strB = new StringBuffer();
		if(StringUtils.isNotBlank(fieldName)){
			strB.append("get").
			append(fieldName.substring(0, 1).toUpperCase()).
			append(fieldName.substring(1));
		}
		return strB.toString();
	}
	public static String setterName(String fieldName){
		StringBuffer strB = new StringBuffer();
		if(StringUtils.isNotBlank(fieldName)){
			strB.append("set").
			append(fieldName.substring(0, 1).toUpperCase()).
			append(fieldName.substring(1));
		}
		return strB.toString();
	}
}
