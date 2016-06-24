package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.IJdbcManager;

import com.flyrish.hades.dto.OFunc;


public interface IWelcomeManagerExt extends IJdbcManager{
	/**
	 * 常用功能查询
	 * @param string
	 * @param queryMap
	 * @return
	 */
	List<OFunc> findCommonFunc(String string, Map<String, Object> queryMap);
}
