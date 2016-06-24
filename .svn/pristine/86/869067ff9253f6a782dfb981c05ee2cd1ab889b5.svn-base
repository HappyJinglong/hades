package com.flyrish.hades.webapp.action.totalAppraisalStatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.service.ext.ITotalStaticsExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class TotalAppraisalStaticsAction extends BaseAction{
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
	}
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	@Spring
	public ITotalStaticsExt totalStaticsExt;
	/**
	 * 组装的数据
	 * Map<二级栏目号,Map<完成情况,Map<届别,统计数据>>>
	 * 
	 * 学生总人数<=>totalStudentNum
	 * 已完成学生数<=>overStudentNum
	 * 完成百分比<=>percentOverStudentNum
	 * finishedTotalCount
	 * attacheCount
	 * 
	 */
	public Map<String,Map<String,Map<String,String>>> datas=new HashMap<String,Map<String,Map<String,String>>>();
	/**
	 * 届别容器（顺序由低向高）eg,[2016,2017,2018]
	 */
	public List<String> gradeYears=new ArrayList<String>();
	
	public String levelCode;
	
	public String levelid;
	
	public String termId;
	
	public String termName;
	
	@DefaultAction
	public Object toTotalAppraisalStatics(HttpServletRequest req){
		queryBaseData(req);
		gradeYears=studentAppDetailExt.queryGradeYearList(levelid,levelCode);
		//获取相应的数据
		datas=totalStaticsExt.queryMapDataByCondition(termId, userDto.getCmis30id(),userDto.getDiscode(), levelCode, levelid);
		String toPage ="";
		if("2012002".equals(levelCode)){
			toPage="juniorTotalAppraisalStatics.jsp";
		}else{
			toPage="highTotalAppraisalStatics.jsp";
		}
		
		return toPage;
	}
	private void queryBaseData(HttpServletRequest req) {
		levelCode=userDto.getLevelcode();
		levelid=userDto.getLevelid();
	}
}
