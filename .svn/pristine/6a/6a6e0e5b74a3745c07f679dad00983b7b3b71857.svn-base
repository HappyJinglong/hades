package com.flyrish.hades.webapp.action.areaStat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolDto;
import com.flyrish.hades.dto.SchoolStatDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IareaStatExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class CzSchoolStat extends BaseAction{
	
/*	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	*/
	//模糊查询的学校名
	public String  likeSchoolName;
	//学期
	public String  termid;
	public String  levelCode="2012002";
	public String  discode ="110105";
	public String  cmis30idString;
	public Integer intyear=2016;
	
	//第一届
	public int firstClassCountSum=0;
	public int firstClassCountedSum=0;
	//第二届
	public int secondClassCountSum=0;
	public int secondClassCountedSum=0;
	//第三届
	public int thirdClassCountSum=0;
	public int thirdClassCountedSum=0;
	//第四届
	public int fourthClassCountSum=0;
	public int fourthClassCountedSum=0;
	//查询各校的的结果
	public List<PartInfoXieDto> partInfoDtoList;
	public List<SchoolDto> listSchoolDto=new  ArrayList();
	public  SchoolStatDto  schoolStatDto ;
	//
	@Before
	public Object befor(HttpServletRequest req){
		  /*this.getLoginInfo(req);
		  discode=userDto.getDiscode();
		  //cmis30idString=userDto.getCmis30id();
         */	     
		
		/*
		 Calendar ca = Calendar.getInstance();   
	     ca.setTime(new java.util.Date());   
	     String year = ""+ca.get(Calendar.YEAR); */
	     try {
	    	// intyear=Integer.valueOf(year);
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	     
		return null;

	   
	}
	
	
    @Spring
	public IareaStatExt areaStatExt;
	
	
	@DefaultAction
	public Object defaultAction(HttpSession session){
		try {
			likeSchoolName=likeSchoolName.trim();
			List listclass=areaStatExt.findAllSchoolid(likeSchoolName,levelCode,discode);
		    
				if(0<listclass.size()){
				for(int i=0;i<listclass.size();i++){
					Integer cim=(Integer) listclass.get(i);
					   SchoolDto scd=areaStatExt.czFindClassApper(cim,intyear,levelCode,discode,termid);
					   listSchoolDto.add(scd);
				}
			 for(int w=0;w<listSchoolDto.size();w++){
					//第一届
					 firstClassCountSum+=listSchoolDto.get(w).getFirstClassCount();
					 firstClassCountedSum+=listSchoolDto.get(w).getFirstClassCounted();
					//第二届
					 secondClassCountSum+=listSchoolDto.get(w).getSecondClassCount();
					 secondClassCountedSum+=listSchoolDto.get(w).getSecondClassCounted();
					//第三届
					thirdClassCountSum+=listSchoolDto.get(w).getThirdClassCount();
					thirdClassCountedSum+=listSchoolDto.get(w).getThirdClassCounted();
					//第四届
					fourthClassCountSum+=listSchoolDto.get(w).getFourthClassCount();
					fourthClassCountedSum+=listSchoolDto.get(w).getFourthClassCounted();
			 }
				return "czSchoolStat.jsp";
			}else{
				
				return "czSchoolStat.jsp";
			}
		
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	
}
