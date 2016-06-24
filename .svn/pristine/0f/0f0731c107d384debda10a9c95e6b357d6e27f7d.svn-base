package com.flyrish.hades.webapp.action.appraisalWritedStatics;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class AppraisalWritedQXAction extends BaseAction{
	@Spring
	private IAppraisalStaticsExt appraisalStaticsExt;
	@Spring
	private IAppraisalWritedStaticsExt appraisalWritedStaticsExt;
	public List<String>termInfos = new ArrayList<String>();
	public String levelCode;
	public String currentTermId;
	public String termId;
	public String schoolName;
	public String discode;
	public String isQuery;
	public String termName;
	public List<String> queryJB ;
	public List<String> queryQXInfos;
	public List<AppraisalWritedStaticseDto>staticseDtos;
	public String zoneName;
	public String toQuery;
	@Before
	public void init(HttpServletRequest request){
		this.getLoginInfo(request);
		levelCode = userDto.getLevelcode();
		currentTermId = userDto.getTermId();
		//界别
		this.initJB();
	}
	@DefaultAction
	public Object toAppraisalWritedQXStaticsPage(){
		
		if(Constant.USER_KIND_COUNTY.equals(userDto.getUsertype())){//区级
			if(NestUtil.isEmpty(isQuery)){//不查
				return "appraisalWritedQXStatics.jsp";
			}
			//区县学校
			staticseDtos = appraisalWritedStaticsExt.queryQXAppraiseStatics(levelCode, queryJB, userDto.getDiscode(), schoolName,termId,userDto.getTermId());
			if(staticseDtos!=null&&staticseDtos.size()>0){
				//给学校排序  最后一个为合计
				Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);
				String[] newArray= new String[staticseDtos.size()-1];
				for(int i = 0;i<staticseDtos.size();i++){
					String schoolName = staticseDtos.get(i).getClassInfo();
					if("合计".equals(schoolName)){
						break;
					}
					newArray[i] = schoolName+"_"+i;
				}
				Arrays.sort(newArray,com);
				List<AppraisalWritedStaticseDto> staticseDtos2 = new ArrayList<AppraisalWritedStaticseDto>();
				for(int i = 0;i<staticseDtos.size()-1;i++){
					String s = newArray[i];
					String[] arrStr= s.split("_");
					String s2 = arrStr[1];
					staticseDtos2.add(staticseDtos.get(Integer.parseInt(s2)));
				}
				for(int i = 0;i<staticseDtos2.size();i++){
					staticseDtos.set(i, staticseDtos2.get(i));
				}
			}
			//排序结束
			return "appraisalWritedQXStatics.jsp";
		}else if(Constant.USER_KIND_CITY.equals(userDto.getUsertype())){//市级用户
			this.queryQXInfo(levelCode);
			if(NestUtil.isEmpty(isQuery)){//不查
				return "appraisalWritedSJStatics.jsp";
			}
			staticseDtos = appraisalWritedStaticsExt.querySJAppraiseStatics(levelCode, queryJB, discode, termId, currentTermId);
			return "appraisalWritedSJStatics.jsp";
		}
		return "error.jsp";
	}
	
	public Object toQueryQXAppraiseStatics(){
		if(NestUtil.isEmpty(toQuery)){
			staticseDtos = appraisalWritedStaticsExt.queryQXAppraiseStatics(levelCode, queryJB, discode, schoolName,termId,userDto.getTermId());
		}
		return "appraisalWritedQXStaticsDetails.jsp";
	}
	
	private void queryQXInfo(String level) {
		queryQXInfos = appraisalWritedStaticsExt.queryQXInfos(level);
	}
	private void initJB() {
		queryJB = appraisalStaticsExt.queryJB(levelCode, "", "", "");
	}
	private void initTermInfos() {
		if(NestUtil.isNotEmpty(currentTermId)&&NestUtil.isNotEmpty(levelCode)){
			Integer currentYear = Integer.parseInt(currentTermId.substring(0, 4));
			if("2012002".equals(levelCode)){
				//初中 例如：20151
				for(int i=9;i>5;i--){
					for(int j=0;j<2;j++){
						String tempFlag = currentTermId.substring(4,5);
						if(i==9 && "1".equals(tempFlag)){
							String tempTermId = String.valueOf(currentYear)+(j+1);
							String tempTermInfo = i+"年级第"+(0==j?"一":"二")+"学期";
							termInfos.add(tempTermId+"@"+tempTermInfo);
							break;
						}
						String tempTermId = String.valueOf(currentYear)+(j+1);
						String tempTermInfo = i+"年级第"+(0==j?"一":"二")+"学期";
						termInfos.add(tempTermId+"@"+tempTermInfo);
					}
					--currentYear;
				}
			}else{
				for(int i= 3;i>0;i--){
					for(int j=0;j<2;j++){
						String tempFlag = currentTermId.substring(4,5);
						if(i==3 && "1".equals(tempFlag)){
							String tempTermId = String.valueOf(i)+(j+1);
							String tempTermInfo = "高"+(i==1?"一":(i==2?"二":"三"))+"第"+(0==j?"一":"二")+"学期";
							termInfos.add(tempTermId+"@"+tempTermInfo);
							break;
						}
						String tempTermId = String.valueOf(i)+(j+1);
						String tempTermInfo = "高"+(i==1?"一":(i==2?"二":"三"))+"第"+(0==j?"一":"二")+"学期";
						termInfos.add(tempTermId+"@"+tempTermInfo);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("20151".substring(4, 5));
	}	
}
