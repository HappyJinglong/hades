package com.flyrish.hades.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utility {
	
	
	public static String createJsonStr(Map<String, Object> jsonMap) {
		if (jsonMap == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		json.putAll(jsonMap);
		String ret = json.toString();
		json.clear();
		json=null;
		return ret;
	}

	public static String createJsonStr(List<String> jsonList) {

		if (jsonList == null)
			return null;

		JSONArray json = new JSONArray();

		int i = 0;
		for (; i < jsonList.size(); i++)
			json.add(i, jsonList.get(i));
		String ret = json.toString();
		json.clear();
		json=null;
		return ret;
	}

	public static JSONObject parseJsonObj(String jsonStr) {
		if (jsonStr == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(jsonStr);
		return json;
	}
	public static String fillNullString(String str){
		return str==null?"":str;
	}
}
