package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.ILearnprocessStaticsExt;

public class LearnprocessStaticsExtImpl extends JdbcRootManager implements ILearnprocessStaticsExt{
	
	
	public Map<String,Map<String,String>>queryLearnprocessStaticsInfo(String gradesyear,String schoolName,String levelCode,String termid,String discode,List<SubjectDto>subjectDtos,String userType){
		Map<String,Map<String,String>> learnprocessCounts = new HashMap<String, Map<String,String>>();
		Map<String,String> schoolStudentNums = new TreeMap<String, String>();
		this.querySchoolStudetNums(schoolStudentNums, discode, gradesyear, schoolName, levelCode, termid);
		if(null==schoolStudentNums ||  schoolStudentNums.size()==0)return learnprocessCounts;
		return this.queryJuniorOrHighSchoolLearnprocessCounts(userType,schoolStudentNums, learnprocessCounts, gradesyear, schoolName, levelCode, termid, discode,subjectDtos);
	}
	
	
	private Map<String,Map<String,String>> queryJuniorOrHighSchoolLearnprocessCounts(final String userType,final Map<String,String> schoolStudentNums,final Map<String,Map<String,String>>learnprocessCounts,String gradesyear,String schoolName,String levelCode,String termid,String discode,final List<SubjectDto>subjectDtos){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("discode",discode);
		params.put("gradeyear",gradesyear);
		params.put("schoolname",schoolName);
		params.put("levelcode",levelCode);
		params.put("termid",termid);
		try{
			final Map<String,String>sunjectSingleCount = new HashMap<String, String>();
			this.findList("IAppraisalWritedStaticsExt.queryJuniorOrHighSchoolLearnprocessCounts.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String schoolname = rs.getString("schoolname");
					String subjectName=rs.getString("subjectName");
					String finishCount = rs.getString("finishCount");
					String totalCount = rs.getString("totalCount");
					Map<String, String> countInfos = learnprocessCounts.get(schoolname);
					String studentCount = schoolStudentNums.get(schoolname);
					countInfos = this.tempSubjectCountInfos(learnprocessCounts,subjectDtos, schoolname, countInfos, studentCount);
					double scale = Integer.parseInt(finishCount)*1000/Integer.parseInt(studentCount)/10.0;
					
					String countInfo = studentCount+"@"+finishCount+"@"+scale+"@"+totalCount;
					
					countInfos.put(subjectName, countInfo);
					
					//统计科目填写总数
//					if(Constant.USER_KIND_CITY.equals(userType)){//市级用户
						String finishAndCount = sunjectSingleCount.get(subjectName);
						if(null==finishAndCount){
							sunjectSingleCount.put(subjectName, finishCount+"@"+totalCount);
						}else{
							String[] finish_counts = finishAndCount.split("@");
							sunjectSingleCount.put(subjectName, (Integer.parseInt(finish_counts[0])+Integer.parseInt(finishCount))+"@"+(Integer.parseInt(finish_counts[1])+Integer.parseInt(totalCount)));
						}
//					}
					return null;
				}

				private Map<String, String> tempSubjectCountInfos(
						final Map<String, Map<String, String>> learnprocessCounts,
						final List<SubjectDto> subjectDtos, String schoolname,
						Map<String, String> countInfos, String studentCount) {
					if(null==countInfos){
						countInfos = new HashMap<String, String>();
						learnprocessCounts.put(schoolname, countInfos);
						for(SubjectDto sDto : subjectDtos){
							countInfos.put(sDto.getSubjectid(), studentCount+"@0@0@0");
						}
					}
					return countInfos;
				}
			});
			//组装没有评价的学校数据
			Set<String> schoolNames = schoolStudentNums.keySet();
			Map<String,Map<String,String>>tempLearnprocessCounts = new LinkedHashMap<String, Map<String,String>>();
			Integer totalStudentCounts = 0;
			for(String tempSchoolName : schoolNames){
				Map<String, String> tempLeanrprocessCountsInfo = learnprocessCounts.get(tempSchoolName);//统计科目评价数据
				String tempStudentCount = schoolStudentNums.get(tempSchoolName);
				if(null==tempLeanrprocessCountsInfo){//该学校没有相关统计数据
					tempLeanrprocessCountsInfo = new HashMap<String, String>();
					tempLearnprocessCounts.put(tempSchoolName, tempLeanrprocessCountsInfo);
					for(SubjectDto sDto : subjectDtos){
						tempLeanrprocessCountsInfo.put(sDto.getSubjectid(), tempStudentCount+"@0@0@0");
					}
				}
				//有相关数据
				tempLearnprocessCounts.put(tempSchoolName, tempLeanrprocessCountsInfo);
				//统计总人数
				totalStudentCounts+=Integer.parseInt(tempStudentCount);
			}
			//合计
//			if(Constant.USER_KIND_CITY.equals(userType)){//市级用户
				this.cityTotalCount(subjectDtos, sunjectSingleCount,
						tempLearnprocessCounts, totalStudentCounts);
//			}
			return tempLearnprocessCounts;
		}catch(Exception e){
			logger.error("queryJuniorSchoolLearnprocessCounts(final Map<String,String>,String,String,String,String)",e);
		}
		return null;
	}



	private void cityTotalCount(final List<SubjectDto> subjectDtos,
			final Map<String, String> sunjectSingleCount,
			Map<String, Map<String, String>> tempLearnprocessCounts,
			Integer totalStudentCounts) {
		Map<String, String> hj = new HashMap<String, String>();//统计科目评价数据
		for(SubjectDto sDto : subjectDtos){
			hj.put(sDto.getSubjectid(), totalStudentCounts+"@0@0@0");
		}
		
		Set<String> finishSubjectName = sunjectSingleCount.keySet();
		
		if(null!=finishSubjectName && finishSubjectName.size()>0){
			for(String subName : finishSubjectName){
				String tempSubjectCountInfo = sunjectSingleCount.get(subName);
				String[] finish_count = tempSubjectCountInfo.split("@");
				
				double scale = Integer.parseInt(finish_count[0])*1000/totalStudentCounts/10.0;
				
				String countInfo = totalStudentCounts+"@"+finish_count[0]+"@"+scale+"@"+finish_count[1];
				
				hj.put(subName, countInfo);
			}
		}
		tempLearnprocessCounts.put("合计", hj);
	}
	
	
	private void querySchoolStudetNums(final Map<String,String> schoolStudentNums,String discode,String gradeyear,String schoolname,String levelcode,String termid){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelcode);
		params.put("termid",termid);
		try{
			this.findList("StudentAppDetailExtImpl.querySchoolStudetNums.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String schoolname=rs.getString("schoolname");
					String couts=rs.getString("couts");
					schoolStudentNums.put(schoolname,couts);
					return null;
				}
			});
		}catch(Exception e){
			logger.error("querySchoolStudetNums(final Map<String,String>,String,String,String)",e);
		}
	}


	@Override
	public Map<String, Map<String, Map<String,String>>> queryJWLearnprocessStatics(String termId, String currentTermId, String discode,String cmis30Id, List<SubjectDto> subjectDtos,List<String> gradeYears,String levelId,String levelCode) {
		List<String>years = this.initGrades(termId,currentTermId,gradeYears);
		Map<String, Map<String, Map<String,String>>> learnprocessCounts = new HashMap<String, Map<String,Map<String,String>>>();
		Map<String,String>studentCount = new HashMap<String, String>();
		this.querySchoolStudetNums(studentCount, discode, cmis30Id, years, levelCode, levelId);
		if(null==studentCount || studentCount.size()==0)return learnprocessCounts;
		return this.initJWLearnprocessCount(studentCount,learnprocessCounts,termId,discode,cmis30Id,subjectDtos,gradeYears,levelCode,levelId,years);
	}
	
	private Map<String, Map<String, Map<String,String>>> initJWLearnprocessCount(
			final Map<String,String>studentCount,
			final Map<String, Map<String, Map<String,String>>> learnprocessCounts,
			String termId, String discode, String cmis30Id,
			List<SubjectDto> subjectDtos, List<String> gradeYears,
			String levelCode, String levelId, List<String> years) {
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("discode",discode);
		params.put("cmis30Id",cmis30Id);
		params.put("years",years);
		params.put("levelId", levelId);
		params.put("levelCode", levelCode);
		params.put("termid", termId);
		try{
			this.findList("ILearnprocessStaticsExt.initJWLearnprocessCount.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String year=rs.getString("year");
					String subject=rs.getString("subject");
					String finishedCount = rs.getString("finishedCount");
					String totalCount = rs.getString("totalCount");
					String sdCount = studentCount.get(year);
					Map<String, Map<String, String>> finishDetails = learnprocessCounts.get(subject);
					if(null==finishDetails){//科目对应还没有相关评价信息
						 finishDetails = new LinkedHashMap<String, Map<String,String>>();
						 Map<String,String>jbFinishedCount = new HashMap<String, String>();
						 Map<String,String>jbTotalStudentCount = new HashMap<String, String>();
						 Map<String,String>jbScaleCount = new HashMap<String, String>();
						 Map<String,String>jbTotalCount = new HashMap<String, String>();
						 //届别总人数
						 jbTotalStudentCount.put(year, sdCount);
						 //届别已经完成
						 jbFinishedCount.put(year, finishedCount);
						 //届别百分比统计
						 jbScaleCount.put(year, String.valueOf(Integer.parseInt(finishedCount)*1000/Integer.parseInt(sdCount)/10.0));
						 //总评价条数
						 jbTotalCount.put(year, totalCount);
						 
						 finishDetails.put("学生总人数", jbTotalStudentCount);
						 finishDetails.put("已完成学生数", jbFinishedCount);
						 finishDetails.put("完成百分比（%）", jbScaleCount);
						 finishDetails.put("已填写条目数", jbTotalCount);
					}else{//有相关评价信息了
						Map<String, String> jbTotalStudentCount = finishDetails.get("学生总人数");
						Map<String, String> jbFinishedCount = finishDetails.get("已完成学生数");
						Map<String, String> jbScaleCount = finishDetails.get("完成百分比（%）");
						Map<String, String> jbTotalCount = finishDetails.get("已填写条目数");
						 //届别总人数
						jbTotalStudentCount.put(year, sdCount);
						 //届别已经完成
						 jbFinishedCount.put(year, finishedCount);
						 //届别百分比统计
						 jbScaleCount.put(year, String.valueOf(Integer.parseInt(finishedCount)*1000/Integer.parseInt(sdCount)/10.0));
						 //总评价条数
						 jbTotalCount.put(year, totalCount);
					}
					learnprocessCounts.put(subject, finishDetails);
					return null;
				}
			});
			//重组数据
			Map<String, Map<String, Map<String,String>>>tempLearnprocessCounts = new LinkedHashMap<String, Map<String,Map<String,String>>>();
			for(SubjectDto sDto : subjectDtos){
				String subjectid = sDto.getSubjectid();
				Map<String, Map<String, String>> finishDetails = learnprocessCounts.get(subjectid);
				if(null==finishDetails){
					 finishDetails = new LinkedHashMap<String, Map<String,String>>();
					 Map<String,String>jbFinishedCount = new HashMap<String, String>();
					 Map<String,String>jbTotalStudentCount = new HashMap<String, String>();
					 Map<String,String>jbScaleCount = new HashMap<String, String>();
					 Map<String,String>jbTotalCount = new HashMap<String, String>();
					 for(String gradeYear : years){//获取该校已知学年
						 //学年总人数
						 String sdCount = studentCount.get(gradeYear);
						 if(null==sdCount)sdCount="0";
						 //届别总人数
						 jbTotalStudentCount.put(gradeYear, sdCount);
						 //届别已经完成
						 jbFinishedCount.put(gradeYear, "0");
						 //届别百分比统计
						 jbScaleCount.put(gradeYear, "0");
						 //总评价条数
						 jbTotalCount.put(gradeYear, "0");
					 }
					 finishDetails.put("学生总人数", jbTotalStudentCount);
					 finishDetails.put("已完成学生数", jbFinishedCount);
					 finishDetails.put("完成百分比（%）", jbScaleCount);
					 finishDetails.put("已填写条目数", jbTotalCount);
				}else{
					Map<String, String> jbTotalStudentCount = finishDetails.get("学生总人数");
					Map<String, String> jbFinishedCount = finishDetails.get("已完成学生数");
					Map<String, String> jbScaleCount = finishDetails.get("完成百分比（%）");
					Map<String, String> jbTotalCount = finishDetails.get("已填写条目数");
					 for(String gradeYear : years){//获取该校已知学年
						 //学年总人数
						 String sdCount = studentCount.get(gradeYear);
						 String isGradeYearInfoIn = jbTotalStudentCount.get(gradeYear);
						 if(null!=isGradeYearInfoIn)continue;
						 if(null==sdCount)sdCount="0";
						 jbTotalStudentCount.put(gradeYear, sdCount);
						 //届别已经完成
						 jbFinishedCount.put(gradeYear, "0");
						 //届别百分比统计
						 jbScaleCount.put(gradeYear, "0");
						 //总评价条数
						 jbTotalCount.put(gradeYear, "0");
					 }
				}
				tempLearnprocessCounts.put(subjectid, finishDetails);
			}
			return tempLearnprocessCounts;
		}catch(Exception e){
			logger.error("querySchoolStudetNums(final Map<String,String>,String,String,List<String>,String)",e);
		}
		return null;
	}


	private List<String> initGrades(String termId, String currentTermId,List<String> gradeYears) {
		int length = gradeYears.size();
		Integer oldTermId = Integer.parseInt(termId.substring(0, 4));
		Integer newTermId = Integer.parseInt(currentTermId.substring(0, 4));
		List<String>years = new ArrayList<String>();
		if(3==length){
			if(newTermId-oldTermId==0){
				this.initYears(newTermId, years,4);
			}else if(newTermId-oldTermId==1){
				this.initYears(newTermId, years,3);
			}else if(newTermId-oldTermId==2){
				this.initYears(newTermId, years,2);
			}
		}else{
			if(newTermId-oldTermId==0){
				this.initYears(newTermId, years,5);
			}else if(newTermId-oldTermId==1){
				this.initYears(newTermId, years,4);
			}else if(newTermId-oldTermId==2){
				this.initYears(newTermId, years,3);
			}else if(newTermId-oldTermId==3){
				this.initYears(newTermId, years,2);
			}
		}
		return years;
	}
	private void initYears(Integer newTermId, List<String> years,int j) {
		for(int i=1;i<j;i++){
			years.add(String.valueOf(newTermId+i));
		}
	}

	//获取对应届别下对应学生数量
	private void querySchoolStudetNums(final Map<String,String>studentCount,String discode,String cmis30Id,List<String>years,String levelCode,String levelId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("discode",discode);
		params.put("cmis30Id",cmis30Id);
		params.put("years",years);
		params.put("levelId", levelId);
		params.put("levelCode", levelCode);
		try{
			this.findList("ILearnprocessStaticsExt.querySchoolStudetNums.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String year=rs.getString("year");
					String count=rs.getString("studentCount");
					studentCount.put(year, count);
					return null;
				}
			});
		}catch(Exception e){
			logger.error("querySchoolStudetNums(final Map<String,String>,String,String,List<String>,String)",e);
		}
	}


	@Override
	public Map<String, Map<String, Map<String, String>>> queryJWLearnprocessStatics(String termId, String currentTermId, List<SubjectDto> subjectDtos,List<String> gradeYears) {
//		List<String> years = this.initGrades(termId, currentTermId, gradeYears);
		//重组数据
		Map<String, Map<String, Map<String,String>>>tempLearnprocessCounts = new LinkedHashMap<String, Map<String,Map<String,String>>>();
		for(SubjectDto sDto : subjectDtos){
			String subjectid = sDto.getSubjectid();
			Map<String, Map<String, String>> finishDetails =new LinkedHashMap<String, Map<String,String>>();
				 finishDetails = new LinkedHashMap<String, Map<String,String>>();
				 Map<String,String>jbFinishedCount = new HashMap<String, String>();
				 Map<String,String>jbTotalStudentCount = new HashMap<String, String>();
				 Map<String,String>jbScaleCount = new HashMap<String, String>();
				 Map<String,String>jbTotalCount = new HashMap<String, String>();
				 finishDetails.put("学生总人数", jbTotalStudentCount);
				 finishDetails.put("已完成学生数", jbFinishedCount);
				 finishDetails.put("完成百分比（%）", jbScaleCount);
				 finishDetails.put("已填写条目数", jbTotalCount);
				 for(String gradeYear : gradeYears){//获取该校已知学年
					 //学年总人数
					 jbTotalStudentCount.put(gradeYear, null);
					 //届别已经完成
					 jbFinishedCount.put(gradeYear, null);
					 //届别百分比统计
					 jbScaleCount.put(gradeYear, null);
					 //总评价条数
					 jbTotalCount.put(gradeYear, null);
				 }
				tempLearnprocessCounts.put(subjectid, finishDetails);
		}
		return tempLearnprocessCounts;
	}
}
