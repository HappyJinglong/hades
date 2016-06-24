package com.flyrish.hades.webapp.action.testimonials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.Conditions;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.Xueduan;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IBookReportExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ITestimonialsExt;
import com.flyrish.hades.webapp.action.masterReport.ExportAction;

/**
 * 课程评语类
 * */
public class TestimonialsAction extends ExportAction{

	//----------------------------------------------------------------------------------
	/**
	 * 准备工作
	 * */
	//----------------------------------------------------------------------------------
	
	/**
	 * 数据接口
	 * */
	@Spring
	private IAppraisalStaticsExt appraisalStaticsExt;
	
	/**
	 * 业务
	 * */
	 @Spring
	private ITestimonialsExt itestimonialsExt;
	 @Spring
	private ILatestEvaluationRecordExt latestEvaluationRecordExt;
	 @Spring
    public IBookReportExt bookReportExt;
	
	//--------------------------------------校级开始--------------------------------------------------
	//初中
	
	 
	
    //高中
	 
    //页面传递参数
	public List<SubjectDto> sublist;
	 
	public Map<String,Map<String,List<Map<Object,Integer>>>> ret;
	 
	/**
	 * 初中课程评语填写情况 默认action
	 * */
	@DefaultAction
	public Object defaultQueryAction(HttpServletRequest request, HttpServletRequest req){
		/*//上学期     下学期
		//尾数  1 or 2    20152
			//20151
			//20141 20142
		//2016  - 9年级  当前 20151 or 20152  20151
			// 8年级 历史  2015-1   ==20141 20142
			// 7年级 历史 2015-2   ==20131 20132
		
		//1025 - 8年级 当前 20151 or 20152  20151
			// 7年级 历史 2015-1   ==20141 20142
		//循环学期得到数据  //按照顺序便利  16 17 18 届 16全 17 8-7 18 7
		//得到当前学期
		
		//得到session学区信息
		UserDto  udto=this.getLoginInfo(req);
		
		//取到界别信息  当前界别要+1
		Integer thisterm=Integer.parseInt(udto.getTermId().substring(0,udto.getTermId().length()-1));  //当前界别
		Integer erterm=thisterm+2; //2017届
		Integer santerm=thisterm+3; //2018届
		thisterm=thisterm+1;
		
		//界别年级学期信息
		Map<String,Map<String,Integer[]>> jiexueqi = new HashedMap();
		//界别集合
		Integer [] terms = new Integer[]{thisterm,erterm,santerm};
		//取到该届下所有数据
		for(int i=0;i<terms.length;i++){
			Integer dqterm =Integer.parseInt(udto.getTermId().substring(udto.getTermId().length()-1));
			Map<String,Integer[]> nianji = new HashMap();
			//判断是上学期 or 下学期
			if(1==dqterm){
				//上学期
					if(terms[i].equals(thisterm)){ //9年级
						//9年级
						Integer[] jn=new Integer[]{20161};
						//8年级
						Integer[] bn=new Integer[]{20151,20152};
						//7年级
						Integer[] qn=new Integer[]{20141,20142};
						
						nianji.put("16_9", jn);
						nianji.put("16_8", bn);
						nianji.put("16_7", qn);
						jiexueqi.put(terms[i].toString(), nianji);
					}else if(terms[i].equals(erterm)){
						//8年级
						Integer[] bn=new Integer[]{20171};
						//7年级
						Integer[] qn=new Integer[]{20161,20162};
						
						nianji.put("17_8", bn);
						nianji.put("17_7", qn);
						jiexueqi.put(terms[i].toString(), nianji);
					}else if(terms[i].equals(santerm)){
						//7年级
						Integer[] qn=new Integer[]{20181};
						nianji.put("18_7", qn);
						jiexueqi.put(terms[i].toString(), nianji);
					}
					
				 
			}else if(2==dqterm){
				//下学期
				if(terms[i].equals(thisterm)){ //9年级
					//9年级
					Integer[] jn=new Integer[]{20161,20162};
					//8年级
					Integer[] bn=new Integer[]{20151,20152};
					//7年级
					Integer[] qn=new Integer[]{20141,20142};
					
					nianji.put("16_9", jn);
					nianji.put("16_8", bn);
					nianji.put("16_7", qn);
					jiexueqi.put(terms[i].toString(), nianji);
				}else if(terms[i].equals(erterm)){
					//8年级
					Integer[] bn=new Integer[]{20171,20172};
					//7年级
					Integer[] qn=new Integer[]{20161,20162};
					
					nianji.put("17_8", bn);
					nianji.put("17_7", qn);
					jiexueqi.put(terms[i].toString(), nianji);
				}else if(terms[i].equals(santerm)){
					//7年级
					Integer[] qn=new Integer[]{20181,20182};
					nianji.put("18_7", qn);
					jiexueqi.put(terms[i].toString(), nianji);
				}
			}
			//组装
			
		}
		
		//需要科目   16 3 17 2 18 1
		sublist= itestimonialsExt.getSubjectByCode("2012002");
		List<String>cmis30Id = new ArrayList<String>();
		cmis30Id.add(udto.getCmis30id());
		Map<String,List<String>> uid=appraisalStaticsExt.querySchoolInfos(cmis30Id,udto.getUsertype(),"1");
		
		List<String> uidlist= new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		Iterator<Map.Entry<String, List<String>>> it = uid.entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry<String, List<String>> entry = it.next();
		   String key = (String)entry.getKey();
		   sb.append(entry.getValue().toString());
		  }
		String a= sb.toString().substring(1,sb.length()-1);
		   String[]  sourceStrArray = a.split(", ");
		
		 List<PartInfoCacheDto> queryRecodes = new ArrayList<PartInfoCacheDto>();
		 //44
		// udto.getTermId()		20151 21
		for(int i =0;i<sourceStrArray.length;i++){  
			List<PartInfoCacheDto>  fu = latestEvaluationRecordExt.queryRecodeInCache(sourceStrArray[i], udto.getTermId(),"44", PartInfoCacheDto.class);
			if(null!=fu){
				for(PartInfoCacheDto ale : fu){
					queryRecodes.add(ale);
				}
			}
			
		}
		
		for(PartInfoCacheDto partInfocache:queryRecodes){
			System.out.println(partInfocache.getStudentid());
			System.out.println(partInfocache.getTermid());
		}
		
		*/
		return "testimonialsJW.jsp";
	}
	
	/**
	 * 根据科目查询评语填写情况
	 * */
	public Object getTestimonialsByKm(HttpServletRequest request, HttpServletRequest req){
		
		
		
		return null;
	}
	//--------------------------------------校级结束--------------------------------------------------
	
	
	
	//---------------------------------------区县开始----------------------------------------------------
	/**
	 * 区县
	 * */
	//---------------------------------------区县结束----------------------------------------------------
	
	
	//---------------------------------------市区开始----------------------------------------------------
	/**
	 * 市区
	* */
	//---------------------------------------市区结束----------------------------------------------------
	
}
