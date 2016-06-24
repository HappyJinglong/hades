package com.flyrish.hades.webapp.action.needs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class HighNeedAppraisesAction extends BaseAction {
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	/**
	 * 组装的数据
	 * Map<二级栏目号,Map<完成情况,Map<届别,统计数据>>>
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
	
	public String levelCode;
	
	public String levelid;
	
	public String termId;
	@DefaultAction
	public Object defaultAction(HttpServletRequest req){
		queryBaseData(req);
		return queryWhichOnePage();
	}
	public Object queryData(HttpServletRequest req){
		queryBaseData(req);
		//获取相应的数据
		datas=studentAppDetailExt.queryMapDataByCondition(termId, userDto.getCmis30id(),userDto.getDiscode(), levelCode, levelid);
		return queryWhichOnePage();
	}
	
	private void queryBaseData(HttpServletRequest req) {
		getLoginInfo(req);
		levelCode=userDto.getLevelcode();
		levelid=userDto.getLevelid();
	}
	private Object queryWhichOnePage() {
		//获取届别
		gradeYears=studentAppDetailExt.queryGradeYearList(levelid,levelCode);
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			return "highindex.jsp";
		}else{
			return "middleindex.jsp";
		}
	}
}
