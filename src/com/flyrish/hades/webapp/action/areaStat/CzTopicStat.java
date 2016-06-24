package com.flyrish.hades.webapp.action.areaStat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.CzGreedLengthDto;
import com.flyrish.hades.dto.FistGzTopicDto;
import com.flyrish.hades.dto.GreedDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolDto;
import com.flyrish.hades.dto.SchoolStatDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IareaStatExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class CzTopicStat extends BaseAction{
	
/*	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	*/
	//模糊查询的学校名
	public String  schoolName;
	//届别
	public Integer  graduateyear;
	//科目
	public String  topic;
	//学期
	public String  termid;
	//学年
	public String  greed;
	
	public String  levelCode="2012002";
	public String  discode ="110105";
	
	public String  cmis30idString;
	public String  topicmu;
	
	//当前学年
	public Integer intyear=2014;
	public Integer newyear=2015;
	public Integer topicxuanze;
	
	
	//查询各校的的结果
	public List<PartInfoXieDto> partInfoDtoList;
	public List<FistGzTopicDto> listTopic=new ArrayList<FistGzTopicDto>();
	
	
	
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
		/*<c:if test="${topicxuanze!=0}">
	      <td class="1_Class" colspan="4">${topicmu}</td>
	    </c:if> */
		
	 if("0".equals(topic)){
		 topicxuanze=0; 
	 }else{
		 Integer a=Integer.valueOf(topic); 
		 topicxuanze=a;
		 if(1==a){
			 topicmu="思想品德";
				   
		 }else if(2==a){
			 topicmu="语文";
		 }else if(3==a){
			 topicmu="数学";
		 }else if(4==a){
			 topicmu="外语";
		 }else if(5==a){
			 topicmu="历史";
		 }else if(6==a){
			 topicmu="地理";
		 }else if(7==a){
			 topicmu="物理";
		 }else if(8==a){
			 topicmu="化学";
		 }else if(9==a){
			 topicmu="生物";
		 }else if(10==a){
			 topicmu="体育与健康";
		 }else if(11==a){
			 topicmu="音乐";
		 }else if(12==a){
			 topicmu="美术";
		 }else if(13==a){
			 topicmu="劳动技术";
		 }else if(14==a){
			 topicmu="信息技术";
		 }else if(15==a){
			 topicmu="研究性学习";
		 }else if(16==a){
			 topicmu="社区服务与社会实践";
		 }else if(17==a){
			 topicmu="地方与校本课程";
		 }
	
	 }
		
	if(schoolName!=null){
       schoolName=schoolName.trim();
	}
		//查出来学校的值  
		List<CzGreedLengthDto> listclass=areaStatExt.findCzAllSchoolid(schoolName,levelCode,discode);
		for(int i=0;i<listclass.size();i++){
			//存放学校名和greed信息
			  FistGzTopicDto  fistGzTopicDto=new FistGzTopicDto();
			  Integer cim=(Integer) listclass.get(i).getSchoolId();
			  Integer greedLength=(Integer)listclass.get(i).getNumber(); 
			   fistGzTopicDto=areaStatExt.czFindTopicApper(cim,levelCode,discode,topic,greed,graduateyear,intyear,greedLength);
			   String schname=areaStatExt.findSchoolName(cim,discode);
			   if(null!=listTopic){ 
				    fistGzTopicDto.setSchoolName(schname);
			         listTopic.add(fistGzTopicDto);
				   }
		}
			return "czTopicStat.jsp";
		}
	
}






/*
List listclass=areaStatExt.findAllSchoolid(schoolName,levelCode,discode);

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

}*/
