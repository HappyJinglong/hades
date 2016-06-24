package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Map;

public class SqlDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7874469403836659810L;
	/**
	 * sql参数
	 */
	private Object[] params;
	/**
	 * sql字符串
	 */
	private String sqlStr;
	/**
	 * sql参数map
	 */
	private Map<String, Object> paramsMap;
	/**
	 * 此sql对应更新的redis key
	 */
	private String sqlKey;
	/**
	 * 该记录所对应的主键值
	 */
	private String proKey;
	
	/**
	 * 处理失败时间
	 */
	private String genDate;
	
	/**
	 * 错误信息
	 */
	private String errorMsg;
	/**
	 * 1代表主表，2代表附件，3代表附表
	 */
	private String whichOne;
	
	
	public String getWhichOne() {
		return whichOne;
	}

	public void setWhichOne(String whichOne) {
		this.whichOne = whichOne;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	public String getGenDate() {
		return genDate;
	}

	public void setGenDate(String genDate) {
		this.genDate = genDate;
	}

	
	
	public String getProKey() {
		return proKey;
	}

	public void setProKey(String proKey) {
		this.proKey = proKey;
	}

	public String getSqlKey() {
		return sqlKey;
	}

	public void setSqlKey(String sqlKey) {
		this.sqlKey = sqlKey;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
	
}
