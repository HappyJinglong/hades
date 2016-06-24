package com.flyrish.hades.webapp.action.appraisalWritedStatics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class AppraisalWritedJWAction extends BaseAction{
	@Spring
	private IAppraisalStaticsExt appraisalStaticsExt;
	@Spring
	private IAppraisalWritedStaticsExt appraisalWritedStaticsExt;
	public List<String> JBs;
	public List<String>termInfos;
	private String levelCode;
	private String campuseId;
	public String years;
	public String termId;
	//是否查询
	public String flag;
	public List<AppraisalWritedStaticseDto> appraiseStatics;
	@Before
	public void init(HttpServletRequest request){
		this.getLoginInfo(request);
		levelCode = userDto.getLevelcode();
		campuseId = userDto.getCampuseId();
	}
	
	@DefaultAction
	public Object toAppraisalWritedJWStaticsPage(){
		JBs = appraisalStaticsExt.queryJB(userDto.getLevelid(),levelCode, campuseId, "", "1");
		if(NestUtil.isEmpty(flag)){
			if(null!=JBs && JBs.size()>0){
				String year = JBs.get(0);
				if(NestUtil.isNotEmpty(year)&&year.split("_").length==2){
					if("2012002".equals(levelCode)){
						termInfos = appraisalWritedStaticsExt.queryTerm(userDto.getLevelid(), campuseId, year.split("_")[1], "1");
					}else{
						termInfos = appraisalWritedStaticsExt.queryTerm(userDto.getLevelid(), campuseId, year.split("_")[1], "");
					}
				}
			}
		}else{
			//查询数据咯
			if(NestUtil.isNotEmpty(userDto.getTermId())){
				if("2012002".equals(userDto.getLevelcode())){
					//初中数据
					String gradeNum = String.valueOf(9-(Integer.parseInt(years)-Integer.parseInt(userDto.getTermId().substring(0,4))-1));
					List<String>cmis30Ids = new ArrayList<String>();
					cmis30Ids.add(userDto.getCmis30id());
					appraiseStatics = appraisalWritedStaticsExt.queryAppraiseStatics(cmis30Ids, userDto.getLevelid(), gradeNum, termId, years,userDto.getUserRealType(),userDto.getCampuseId());
				}else{
					//高中数据
					String gradeNum = String.valueOf(3-(Integer.parseInt(years)-Integer.parseInt(userDto.getTermId().substring(0,4))-1));
					List<String>cmis30Ids = new ArrayList<String>();
					cmis30Ids.add(userDto.getCmis30id());
					appraiseStatics = appraisalWritedStaticsExt.queryAppraiseStatics(cmis30Ids, userDto.getLevelid(), gradeNum, termId, years,userDto.getUserRealType(),userDto.getCampuseId());
				}
			}
			this.dataSort(appraiseStatics);
		}
		return "appraisalWritedJWStatics.jsp";
	}
	private void dataSort(List<AppraisalWritedStaticseDto> list) {
		  Collections.sort(list, new Comparator<AppraisalWritedStaticseDto>(){  
			  
	            public int compare(AppraisalWritedStaticseDto o1, AppraisalWritedStaticseDto o2) {  
	            	Integer o1ClassNum = this.getClassNum(o1);
	            	Integer o2ClassNum = this.getClassNum(o2);
	                //按照学生的年龄进行升序排列  
	                if(o1ClassNum > o2ClassNum){  
	                    return 1;  
	                }  
	                if(o1ClassNum == o2ClassNum){  
	                    return 0;  
	                }  
	                return -1;  
	            }

				private Integer getClassNum(AppraisalWritedStaticseDto o1) {
					if(null==o1)return 0;
					String classInfo = o1.getClassNum();
					if(NestUtil.isNotEmpty(classInfo)){
//						String[] split = classInfo.split("-");
//						if(null!=split&&split.length==3){
							return Integer.parseInt(classInfo);
//						}
					}
					return 0;
				}  
	        }); 		
	}

	@Json 
	public Object queryTermInfos(){
		String levelFlag="";
		if("2012002".equals(levelCode)){
			levelFlag = "1";
		}
		termInfos = appraisalWritedStaticsExt.queryTerm(userDto.getLevelid(), campuseId, years, levelFlag);
		JSONObject fromObject = JSONObject.fromObject("{val:"+termInfos+"}");
		return fromObject;
	}
}
