package com.flyrish.hades.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.nestframework.utils.NestUtil;

public class ConstantReBean {
	private String propertiesFile;
	private String propertiesFileDB;
	
	private static Map<String, String> propertiesMap =new HashMap<String, String>();
	private static Map<String, String> propertiesMapDB =new HashMap<String, String>();

	public String getPropertiesFile() {
		return propertiesFile;
	}

	public void setPropertiesFile(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}
	
	public String getPropertiesFileDB() {
		return propertiesFileDB;
	}

	public void setPropertiesFileDB(String propertiesFileDB) {
		this.propertiesFileDB = propertiesFileDB;
	}

	public String get(String propertyName){
		return propertiesMap.get(propertyName);
	}
	
	public void init(){
		// 读取配置文件
		Properties prop = new Properties();
		Properties propDB = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("/" + propertiesFile);

			NestUtil.load(prop, new InputStreamReader(in, "utf-8"));
			Iterator<Object> iterator = prop.keySet().iterator();
			while(iterator.hasNext()){				
				String pName =(String)iterator.next();
				String pValue = prop.getProperty(pName);
				propertiesMap.put(pName, pValue);
			}
			in.close();
			
			InputStream inDB = getClass().getResourceAsStream("/" + propertiesFileDB);

			NestUtil.load(propDB, new InputStreamReader(inDB, "utf-8"));
			Iterator<Object> iteratorDB = propDB.keySet().iterator();
			while(iteratorDB.hasNext()){				
				String pNameDB =(String)iteratorDB.next();
				String pValue = propDB.getProperty(pNameDB);
				propertiesMap.put(pNameDB, pValue);
			}
			inDB.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
