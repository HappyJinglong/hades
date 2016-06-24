package com.flyrish.hades.webapp.action.learnprocessStatics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.service.ext.ILearnprocessStaticsExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class LearnprocessStaticsAction extends BaseAction{
	
	public String levelCode;
	public String schoolName;
	public String termId;
	public String discode;
	public String isQuery;
	public String gradeid;
	public String termName;
	public String subject;
	public List<String>queryQXInfos;
	//课程评语评价统计
	public Map<String, Map<String, String>> learnprocessStaticsInfo;
	//教务老师课程评语统计
	public Map<String, Map<String, Map<String,String>>>learnprocessJWStaticsInfo;
	//课程
	public List<SubjectDto>subjectDtos;
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	@Spring
	private ILearnprocessStaticsExt learnprocessStaticsExt;
	@Spring
	private IAppraisalWritedStaticsExt appraisalWritedStaticsExt;
	public String levelid;
	public String currentTermId;
	public String cmis30Id;
	@Before
	public void initDatas(HttpServletRequest req){
		this.getLoginInfo(req);
		discode = NestUtil.isEmpty(discode)?userDto.getDiscode():discode;
		levelCode = userDto.getLevelcode();
		levelid = userDto.getLevelid();
		currentTermId = userDto.getTermId();
		cmis30Id = userDto.getCmis30id();
		
		if("2012002".equals(levelCode)){
			subjectDtos = masterAppriseExt.getCZSubjectInfos();
		}else{
			subjectDtos = masterAppriseExt.getGZSubjectInfos();
		}
//		subjectDtos = subjectDtos.subList(0, 1);
	}

	@DefaultAction
	public Object toLearnprocessStaticsPage(){
		//学校教务老师
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(userDto.getUsertype())){
			return toJWLearnprocessStaticsPage();
		}
		//市区级用户
		if(Constant.USER_KIND_CITY.equals(userDto.getUsertype())){//市级用户
			queryQXInfos = appraisalWritedStaticsExt.queryQXInfos(levelCode);
		}
		if(NestUtil.isNotEmpty(isQuery)){
			learnprocessStaticsInfo = learnprocessStaticsExt.queryLearnprocessStaticsInfo(gradeid, schoolName, levelCode, termId, discode,subjectDtos,userDto.getUsertype());
		}
		return "learnprocessStatics.jsp";
	}
	public List<String>gradeYears;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	private String levelId;
	public Object toJWLearnprocessStaticsPage(){
		//获取届别
		gradeYears=studentAppDetailExt.queryGradeYearList(levelid,levelCode);
		if(NestUtil.isNotEmpty(isQuery)){
			levelId = userDto.getLevelid();
			learnprocessJWStaticsInfo = learnprocessStaticsExt.queryJWLearnprocessStatics(termId,currentTermId,discode,cmis30Id,subjectDtos,gradeYears,levelId,levelCode);
		}else{
			learnprocessJWStaticsInfo = learnprocessStaticsExt.queryJWLearnprocessStatics(termId,currentTermId,subjectDtos,gradeYears);
		}
		return "learnprocessJWStatics.jsp";
	}
}
