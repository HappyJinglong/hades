package com.flyrish.hades.webapp.action.needs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.webapp.action.BaseAction;

public class MiddleNeedAppraisesAction extends BaseAction {
	/**
	 * 组装的数据
	 * Map<二级栏目号，Map<完成情况,Map<届别，统计数据>>>
	 * 
	 * 学生总人数<=>totalStudentNum
	 * 已完成学生数<=>overStudentNum
	 * 完成百分比<=>percentOverStudentNum
	 * 
	 */
	public Map<String,Map<String,Map<String,String>>> datas=new HashMap<String,Map<String,Map<String,String>>>();
	/**
	 * 届别容器（顺序由低向高）eg,[2016,2017,2018]
	 */
	public List<String> gradeYears=new ArrayList<String>();
	@DefaultAction
	public Object defaultAction(){
		return "middleindex.jsp";
	}
}
