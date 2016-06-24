package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.mapping.Array;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.util.DataSource;

public class AppraisalWritedStaticsExtImpl extends JdbcRootManager implements IAppraisalWritedStaticsExt{
	private IAppraisalStaticsExt appraisalStaticsExt;
	
	public IAppraisalStaticsExt getAppraisalStaticsExt() {
		return appraisalStaticsExt;
	}

	public void setAppraisalStaticsExt(IAppraisalStaticsExt appraisalStaticsExt) {
		this.appraisalStaticsExt = appraisalStaticsExt;
	}

	@DataSource("read")
	public List<String> queryTerm(String levelCode, String campuseId,String year,String flag) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelId", levelCode);
			params.put("campuseId", campuseId);
			params.put("year", year);
			params.put("flag", flag);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryTerm.query");
			return this.findList("IAppraisalStaticsExt.queryTerm.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("termid")+"@"+rs.getString("termInfos");
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryTerm",e);
		}
		return null;
	}

	@Override
	public List<AppraisalWritedStaticseDto> queryAppraiseStatics(List<String>cmis30Ids,String levelCode,String gradeNum,String termId,String jb,String userType,String campueId) {
		List<AppraisalWritedStaticseDto>classInfos =new ArrayList<AppraisalWritedStaticseDto>();
		//1、获取学生信息
		Map<String, List<String>> schoolInfos = appraisalStaticsExt.querySchoolInfos_new(cmis30Ids, userType,levelCode,campueId);
		String cmis30Id = cmis30Ids!=null&&cmis30Ids.size()>0?cmis30Ids.get(0):"";
		if(null!=schoolInfos && schoolInfos.size()>0){
			//班主任评语信息
			Map<String, Integer> assessStatics = appraisalStaticsExt.queryAssessStatics(cmis30Ids, userType, levelCode,campueId);
			Set<String> schoolInfo = schoolInfos.keySet();
			if(null!=schoolInfo && schoolInfo.size()>0){
				for(String info : schoolInfo){
					String paramInfo = cmis30Id+"-"+gradeNum;
					if(info.contains(paramInfo)){
						List<String> eduIds = schoolInfos.get(info);
						if(null!=eduIds&& eduIds.size()>0){
							if(NestUtil.isNotEmpty(termId)&&termId.length()>2){
								juniorStatics(termId, jb, classInfos,assessStatics, info, eduIds);
							}else{
								highStatics(gradeNum, termId, jb, classInfos,assessStatics, info, eduIds);
							}
						}
					}
				}
			}
		}
		return classInfos;
	}

	private void highStatics(String gradeNum, String termId, String jb,
			List<AppraisalWritedStaticseDto> classInfos,
			Map<String, Integer> assessStatics, String info, List<String> eduIds) {
		AppraisalWritedStaticseDto staticseDto = new AppraisalWritedStaticseDto();
		//班级信息
		staticseDto.setClassInfo("高中-"+jb+"届-"+info.split("-")[3]);
		staticseDto.setClassNum(info.split("-")[2]);
		//班级学生数量
		if("0000"==eduIds.get(0)){
			staticseDto.setTotalCount(0);
			classInfos.add(staticseDto);
			return;
		}else{
			staticseDto.setTotalCount(eduIds.size());
		}
		//统计相关栏目数据  获取初中栏目信息
		//思想道德
		Integer maxVal = 0;
		for(String two_part_id : Constant.SXDD_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setSxddCount(maxVal);
		//课程评语
		maxVal=0;
		for(String two_part_id : Constant.KCPY_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setKcpyCount(maxVal);
		//合作与交流
		maxVal=0;
		for(String two_part_id : Constant.HZYJL_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setHzyjlCount(maxVal);
		//运动与健康
		maxVal=0;
		for(String two_part_id : Constant.YDYJK_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setYsyjkCount(maxVal);
		//审美与表现
		maxVal=0;
		for(String two_part_id : Constant.SMYBX_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setSmybxCount(maxVal);
		//个性发展
		maxVal=0;
		for(String two_part_id : Constant.GXFZ_TWO_PART_ID_H){
			maxVal = queryMaxVal_h(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setGxfzCount(maxVal);
		//班主任评语
		if(null!=assessStatics && assessStatics.size()>0){
			String tempKey = info.split("-")[1]+"-"+info.split("-")[2]+"-"+String.valueOf(Integer.parseInt(jb)-(4-Integer.parseInt(termId.substring(0,1))))+termId.substring(1);
			staticseDto.setBzrCount(assessStatics.get(tempKey)==null?0:assessStatics.get(tempKey));
		}
		classInfos.add(staticseDto);
	}
	/*public static void main(String[] args) {
		System.out.println("21".substring(1));
	}*/

	private void juniorStatics(String termId, String jb,
			List<AppraisalWritedStaticseDto> classInfos,
			Map<String, Integer> assessStatics, String info, List<String> eduIds) {
		AppraisalWritedStaticseDto staticseDto = new AppraisalWritedStaticseDto();
		//班级信息
		staticseDto.setClassInfo("初中-"+jb+"届-"+info.split("-")[3]);
		staticseDto.setClassNum(info.split("-")[2]);
		//班级学生数量
		if("0000"==eduIds.get(0)){
			staticseDto.setTotalCount(0);
			classInfos.add(staticseDto);
			return;
		}else{
			staticseDto.setTotalCount(eduIds.size());
		}
		//统计相关栏目数据  获取初中栏目信息
		//思想道德
		Integer maxVal = 0;
		for(String two_part_id : Constant.SXDD_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setSxddCount(maxVal);
		//课程评语
		maxVal=0;
		for(String two_part_id : Constant.KCPY_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setKcpyCount(maxVal);
		//合作与交流
		maxVal=0;
		for(String two_part_id : Constant.HZYJL_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setHzyjlCount(maxVal);
		//运动与健康
		maxVal=0;
		for(String two_part_id : Constant.YDYJK_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setYsyjkCount(maxVal);
		//审美与表现
		maxVal=0;
		for(String two_part_id : Constant.SMYBX_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setSmybxCount(maxVal);
		//个性发展
		maxVal=0;
		for(String two_part_id : Constant.GXFZ_TWO_PART_ID){
			maxVal = queryMaxVal(termId, eduIds, maxVal,two_part_id);
		}
		staticseDto.setGxfzCount(maxVal);
		//班主任评语
		if(null!=assessStatics && assessStatics.size()>0){
			String tempKey = info.split("-")[1]+"-"+info.split("-")[2]+"-"+termId;
			staticseDto.setBzrCount(assessStatics.get(tempKey)==null?0:assessStatics.get(tempKey));
		}
		classInfos.add(staticseDto);
	}

	private Integer queryMaxVal(String termId, List<String> eduIds,
			Integer maxVal, String two_part_id) {
		Map<Object, Integer> sxdd = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, PartInfoCacheDto.class);
		if(null!=sxdd && sxdd.size()>0){
			Collection<Integer> counts = sxdd.values();
			for(Integer count : counts){
				if(count>maxVal){
					maxVal = count;
				}
			}
		}
		return maxVal;
	}
	private Integer queryMaxVal_h(String termId, List<String> eduIds,
			Integer maxVal, String two_part_id) {
		Map<Object, Integer> sxdd = null;
		if("9999".equals(two_part_id)){
			sxdd = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, AlearnprocessAppraisalCacheDto.class);
		}else if("7010".equals(two_part_id)){
			sxdd = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, ApersonalityCacheDto.class);
		}else{
			sxdd = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, AapprasialCacheDto.class);
		}
		if(null!=sxdd && sxdd.size()>0){
			Collection<Integer> counts = sxdd.values();
			for(Integer count : counts){
				if(count>maxVal){
					maxVal = count;
				}
			}
		}
		return maxVal;
	}

	@Override
	public List<AppraisalWritedStaticseDto> queryQXAppraiseStatics(String schoolName, String termId, String discode, String levelCode,String termName,String campusId,String userType) {
		List<AppraisalWritedStaticseDto>staticseDtos = new ArrayList<AppraisalWritedStaticseDto>();
		List<String> queryQXSchoolInfos = this.queryQXSchoolInfos(discode, schoolName);
		
		if(null!=queryQXSchoolInfos && queryQXSchoolInfos.size()>0){
			for(String info : queryQXSchoolInfos){
				String[] cmis30Id_schoolName = info.split("@");
				String tempCmis30Id = cmis30Id_schoolName[0];
				String tempSchoolName = cmis30Id_schoolName[1];
				List<String>cmis30Ids = new ArrayList<String>();
				cmis30Ids.add(tempCmis30Id);
				//查询出所有的年级班级所对应的EduId
				if("2012002".equals(levelCode)){//初中
					Map<String, List<String>> querySchoolInfos = appraisalStaticsExt.querySchoolInfos_new(cmis30Ids, userType, levelCode,campusId);
					if(null==querySchoolInfos || querySchoolInfos.size()==0)continue;
					Set<String> cmis30Id_gradeNum_classNums = querySchoolInfos.keySet();
					String gradeNums[] = {"9","8","7","6"}; 
					//班级总数量
					Map<String,Integer>classTotalCout = new HashMap<String, Integer>();
					Map<String,Integer>classFinishedCout = new HashMap<String, Integer>();
					for(String gradeNum : gradeNums){
						if("8".equals(gradeNum) && termName.contains("9")){
							break;
						}else if("7".equals(gradeNum)){
							if(termName.contains("9")||termName.contains("8")){
								break;
							}
						}else if("6".equals(gradeNum)){
							if(termName.contains("9")||termName.contains("8")||termName.contains("7")){
								break;
							}
						}
						String temp_cmis30Id_gradeNum = tempCmis30Id+"-"+gradeNum;
						for(String cmis30Id_gradeNum_classNum :cmis30Id_gradeNum_classNums){
							if(cmis30Id_gradeNum_classNum.contains(temp_cmis30Id_gradeNum)){//9年级  统计相应班级数量
								//取出eduIds
								List<String> eduIds = querySchoolInfos.get(cmis30Id_gradeNum_classNum);
								//test
//								if(null!=eduIds && eduIds.size()>3){
//									eduIds = eduIds.subList(0, 2);
//								}
								int tempAppraisalCout = 0;
								for(String two_part_id : Constant.TOTAL_TWO_PART_ID_CZ){
									//某个年级  某个班级 数据查询
									for(String eduId : eduIds){
										List<PartInfoCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, two_part_id, PartInfoCacheDto.class);
										if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
											tempAppraisalCout = 1;
											break;
										}
									}
//									Map<Object, Integer> queryRecordStaticsInChache = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, PartInfoCacheDto.class);
									if(tempAppraisalCout==1){
										//记录有评价的班级数量
										Integer finishedCount = classFinishedCout.get(gradeNum);
										if(null==finishedCount){
											classFinishedCout.put(gradeNum, 1);
										}else{
											classFinishedCout.put(gradeNum, ++finishedCount);
										}
										break;
									}
								}
								//记录班级数量
								Integer totalClassCout = classTotalCout.get(gradeNum);
								if(null==totalClassCout){
									classTotalCout.put(gradeNum, 1);
								}else{
									classTotalCout.put(gradeNum, ++totalClassCout);
								}
							}
						}
					}
					AppraisalWritedStaticseDto writedStaticseDto = new AppraisalWritedStaticseDto();
					writedStaticseDto.setClassInfo(tempSchoolName);
					writedStaticseDto.setOneCount(classTotalCout.get("9"));
					writedStaticseDto.setTwoCount(classTotalCout.get("8"));
					writedStaticseDto.setThreeCount(classTotalCout.get("7"));
					writedStaticseDto.setFourCount(classTotalCout.get("6"));
					writedStaticseDto.setOneCountFinished(classFinishedCout.get("9"));
					writedStaticseDto.setTwoCountFinished(classFinishedCout.get("8"));
					writedStaticseDto.setThreeCountFinished(classFinishedCout.get("7"));
					writedStaticseDto.setFourCountFinished(classFinishedCout.get("6"));
					writedStaticseDto.setDiscode(discode);
					staticseDtos.add(writedStaticseDto);
				}else{//高中
					Map<String, List<String>> querySchoolInfos = appraisalStaticsExt.querySchoolInfos_new(cmis30Ids, userType, null,campusId);
					if(null==querySchoolInfos || querySchoolInfos.size()==0)continue;
					Set<String> cmis30Id_gradeNum_classNums = querySchoolInfos.keySet();
					String gradeNums[] = {"3","2","1"}; 
					//班级总数量
					Map<String,Integer>classTotalCout = new HashMap<String, Integer>();
					Map<String,Integer>classFinishedCout = new HashMap<String, Integer>();
					for(String gradeNum : gradeNums){
//						termId = gradeNum+termId.substring(5, 5);
						if("2".equals(gradeNum) && termName.contains("三")){
							break;
						}else if("1".equals(gradeNum)){
							if(termName.contains("三")||termName.contains("二")){
								break;
							}
						}
						String temp_cmis30Id_gradeNum = tempCmis30Id+"-"+gradeNum;
						for(String cmis30Id_gradeNum_classNum :cmis30Id_gradeNum_classNums){
							if(cmis30Id_gradeNum_classNum.contains(temp_cmis30Id_gradeNum)){//3年级  统计相应班级数量
								//取出eduIds
								List<String> eduIds = querySchoolInfos.get(cmis30Id_gradeNum_classNum);
								//test
								/*if(null!=eduIds && eduIds.size()>3){
									eduIds = eduIds.subList(0, 2);
								}*/
								int tempAppraisalCout = 0;
								for(String two_part_id : Constant.TOTAL_TWO_PART_ID_GZ){
									//某个年级  某个班级 数据查询
									for(String eduId:eduIds){
										/*if("10017129".equals(eduId)){
											System.out.println(11);
										}*/
										List<PartInfoCacheDto> queryRecodeInCache = null;
										if("3030".equals(two_part_id)
												||"4030".equals(two_part_id)
												||"6030".equals(two_part_id)){//a_recordpackage
//											queryRecordStaticsInChache = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, A.class);
										}else if("8010".equals(two_part_id)){//a_learnprocess_work
											queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, two_part_id, AlearnprocessWorksCacheDto.class);//appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, AlearnprocessWorksCacheDto.class);
										}else if("9010".equals(two_part_id)
												||"9020".equals(two_part_id)
												||"9030".equals(two_part_id)){//a_practices
											queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, two_part_id, ApracticesCacheDto.class);//queryRecodeInCache = appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, ApracticesCacheDto.class);
										}else if("9999".equals(two_part_id)){//a_learnprocess_app
											queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, two_part_id, AlearnprocessAppraisalCacheDto.class);//appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, AlearnprocessAppraisalCacheDto.class);
										}else{
											queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, two_part_id, AapprasialCacheDto.class);//appraisalStaticsExt.queryRecordStaticsInChache(eduIds, termId, two_part_id, AapprasialCacheDto.class);
										}
										if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
											tempAppraisalCout=1;
											break;
										}
									}
									if(tempAppraisalCout==1){
										//记录有评价的班级数量
										Integer finishedCount = classFinishedCout.get(gradeNum);
										if(null==finishedCount){
											classFinishedCout.put(gradeNum, 1);
										}else{
											classFinishedCout.put(gradeNum, ++finishedCount);
										}
										break;
									}
								}
								//记录班级数量
								Integer totalClassCout = classTotalCout.get(gradeNum);
								if(null==totalClassCout){
									classTotalCout.put(gradeNum, 1);
								}else{
									classTotalCout.put(gradeNum, ++totalClassCout);
								}
							}
						}
					}
					AppraisalWritedStaticseDto writedStaticseDto = new AppraisalWritedStaticseDto();
					writedStaticseDto.setClassInfo(tempSchoolName);
					writedStaticseDto.setOneCount(classTotalCout.get("3"));
					writedStaticseDto.setTwoCount(classTotalCout.get("2"));
					writedStaticseDto.setThreeCount(classTotalCout.get("1"));
					writedStaticseDto.setOneCountFinished(classFinishedCout.get("3"));
					writedStaticseDto.setTwoCountFinished(classFinishedCout.get("2"));
					writedStaticseDto.setThreeCountFinished(classFinishedCout.get("1"));
					writedStaticseDto.setDiscode(discode);
					staticseDtos.add(writedStaticseDto);
				}
			}
			this.totalCount(levelCode, staticseDtos);
		}
		return staticseDtos;
	}

	private void totalCount(String levelCode,
			List<AppraisalWritedStaticseDto> staticseDtos) {
		if(null!=staticseDtos && staticseDtos.size()>0){
			int one = 0;
			int two =0 ;
			int three = 0;
			int four = 0;
			int oneFinished = 0;
			int twoFinished =0 ;
			int threeFinished = 0;
			int fourFinished = 0;
			for(AppraisalWritedStaticseDto awsd : staticseDtos){
				one+=null==awsd.getOneCount()?0:awsd.getOneCount();
				two+=null==awsd.getTwoCount()?0:awsd.getTwoCount();
				three+=null==awsd.getThreeCount()?0:awsd.getThreeCount();
				oneFinished+=null==awsd.getOneCountFinished()?0:awsd.getOneCountFinished();
				twoFinished+=null==awsd.getTwoCountFinished()?0:awsd.getTwoCountFinished();
				threeFinished+=null==awsd.getThreeCountFinished()?0:awsd.getThreeCountFinished();
				if("2012002".equals(levelCode)){
					four+=null==awsd.getFourCount()?0:awsd.getFourCount();
					fourFinished+=null==awsd.getFourCountFinished()?0:awsd.getFourCountFinished();
				}
			}
			AppraisalWritedStaticseDto writedStaticseDto = new AppraisalWritedStaticseDto();
			writedStaticseDto.setClassInfo("合计");
			writedStaticseDto.setOneCount(one);
			writedStaticseDto.setTwoCount(two);
			writedStaticseDto.setThreeCount(three);
			writedStaticseDto.setFourCount(four);
			writedStaticseDto.setOneCountFinished(oneFinished);
			writedStaticseDto.setTwoCountFinished(twoFinished);
			writedStaticseDto.setThreeCountFinished(threeFinished);
			writedStaticseDto.setFourCountFinished(fourFinished);
			staticseDtos.add(writedStaticseDto);
		}
	}

	@DataSource("read")
	public List<String> queryQXSchoolInfos(String discode, String schoolName) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("discode", discode);
			params.put("schoolName", schoolName);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryQXSchoolInfos.query");
			return this.findList("IAppraisalStaticsExt.queryQXSchoolInfos.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("schoolInfos");
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryQXSchoolInfos",e);
		}
		return null;
	}
	private ILatestEvaluationRecordExt latestEvaluationRecordExt;

	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}

	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}

	@DataSource("read")
	public List<String> queryQXInfos(String levelCode) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryQXInfos.query");
			return this.findList("IAppraisalStaticsExt.queryQXInfos.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String discode = rs.getString("discode");
					String zoneName = rs.getString("name");
					return discode+"@"+zoneName;
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryQXInfos",e);
		}
		return null;
	}
	public List<AppraisalWritedStaticseDto> querySJAppraiseStatics(
			String discode,List<String> queryQXInfos,String schoolName,String termId,String levelCode,
			String zoneName,String termName,UserDto userDto) {
		List<AppraisalWritedStaticseDto> staticseDtos = new ArrayList<AppraisalWritedStaticseDto>();
		if("all".equals(discode)){//查询全部
			if(null!=queryQXInfos && queryQXInfos.size()>0){
				for(String qxInfo : queryQXInfos){
					String tempDiscode = qxInfo.split("@")[0];
					String tempZoneName = qxInfo.split("@")[1];
					this.querySingleTotalCount(schoolName, termId, tempDiscode, levelCode, tempZoneName,termName,userDto,staticseDtos);
				}
			}
		}else{
			this.querySingleTotalCount(schoolName, termId, discode, levelCode, zoneName,termName,userDto,staticseDtos);
		}
		this.totalCount(levelCode, staticseDtos);
		return staticseDtos;
	}
	private void querySingleTotalCount(String schoolName,String currentTermId,String tempDiscode,String levelCode,String tempZoneName,
				String termName,UserDto userDto,List<AppraisalWritedStaticseDto> staticseDtos) {
		List<AppraisalWritedStaticseDto> tempViews = this.queryQXAppraiseStatics(schoolName, currentTermId, tempDiscode, levelCode, termName,userDto.getCampuseId(),userDto.getUserRealType());
		if(null!=tempViews && tempViews.size()>0){
//					tempViews.size()  //学校数量
			int one = 0;
			int two =0 ;
			int three = 0;
			int four = 0;
			int oneFinished = 0;
			int twoFinished =0 ;
			int threeFinished = 0;
			int fourFinished = 0;
			for(AppraisalWritedStaticseDto awsd : tempViews){
				//分别统计各届别的学校完成情况
				if(null!=awsd.getOneCount()&&0!=awsd.getOneCount()){
					one++;
				}
				if(null!=awsd.getTwoCount()&&0!=awsd.getTwoCount()){
					two++;
				}
				if(null!=awsd.getThreeCount()&&0!=awsd.getThreeCount()){
					three++;
				}
				if(null!=awsd.getFourCount()&&0!=awsd.getFourCount()){
					four++;
				}
				if(null!=awsd.getOneCountFinished()&&0!=awsd.getOneCountFinished()){
					oneFinished++;
				}
				if(null!=awsd.getTwoCountFinished()&&0!=awsd.getTwoCountFinished()){
					twoFinished++;
				}
				if(null!=awsd.getThreeCountFinished()&&0!=awsd.getThreeCountFinished()){
					threeFinished++;
				}
				if(null!=awsd.getFourCountFinished()&&0!=awsd.getFourCountFinished()){
					fourFinished++;
				}
			}
			AppraisalWritedStaticseDto writedStaticseDto = new AppraisalWritedStaticseDto();
			writedStaticseDto.setClassInfo(tempZoneName);
			writedStaticseDto.setOneCount(one);
			writedStaticseDto.setTwoCount(two);
			writedStaticseDto.setThreeCount(three);
			writedStaticseDto.setFourCount(four);
			writedStaticseDto.setOneCountFinished(oneFinished);
			writedStaticseDto.setTwoCountFinished(twoFinished);
			writedStaticseDto.setThreeCountFinished(threeFinished);
			writedStaticseDto.setFourCountFinished(fourFinished);
			writedStaticseDto.setDiscode(tempDiscode);
			staticseDtos.add(writedStaticseDto);
		}
	}

	@DataSource("read")
	private Map<String,AppraisalWritedStaticseDto> queryQXAppraiseInfosFromDB(String levelCode,List<String> years, String discode, String schoolName,String termId,String currentTermId,String levelFlag) {
		try {
			List<String>gradeNums = this.initGradeNums(levelFlag,currentTermId,termId);
			if(null==gradeNums || gradeNums.size()==0)return null;
//			Map<String,AppraisalWritedStaticseDto> appraiseInfos = new LinkedHashMap<String, AppraisalWritedStaticseDto>();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("years", years);
			params.put("discode", discode);
			params.put("schoolName", schoolName);
			params.put("termId", termId);
			params.put("gradeNums", gradeNums);
			params.put("levelFlag", levelFlag);
			//学校 学年 对应班级数量统计
			List<String> classCountInfos = this.totalClassCount(params);
			
			if(null==classCountInfos || classCountInfos.size()==0)return null;
			Map<String,AppraisalWritedStaticseDto>tempCountInfo = new HashMap<String, AppraisalWritedStaticseDto>();
//			String preSql = "IAppraisalWritedStaticsExt.queryQXCommonAppraisalInfoFromDB.query";
			params.put("flag", "");
			//班主任评语
			Map<String, String> assessAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB.query");
			//其他评语
			params.put("flag", "1");
			Map<String, String> conmmonAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB.query");
			for(String classCountInfo : classCountInfos){//学校 学年 对应班级数量
				String[] school_class = classCountInfo.split("_");
				if(null!=school_class && school_class.length==2){
					String schoolName_cmis30 = school_class[0];
					String year_classCount = school_class[1];
					if(NestUtil.isNotEmpty(year_classCount)){
						String[] year_classCounts = year_classCount.split(",");
						for(String yc : year_classCounts){
							String[] ycs = yc.split("@");
							if(ycs.length==2){
								String tempYear = ycs[0];//学年
								String totalClassCount = ycs[1];//学校学年班级总数量
								String schoolName_cmis30_year = schoolName_cmis30+"@"+tempYear;
								String[] schoolName_cmis30_years = schoolName_cmis30_year.split("@");
								Set<String>classFinished = new HashSet<String>();
								if(null!=schoolName_cmis30_years && schoolName_cmis30_years.length==3){
									String tempSchoolName = schoolName_cmis30_years[0];//学校名称
									//普通评价统计个数
									this.countFinishedClass(conmmonAppraisalCout, schoolName_cmis30_year,classFinished);
									//个性评价
//									this.countFinishedClass(personalAppraisalCout, schoolName_cmis30_year,classFinished);
									//班主任评价
									this.countFinishedClass(assessAppraisalCout, schoolName_cmis30_year,classFinished);
									//学业评级
//									this.countFinishedClass(learnprocessAppraisalCout, schoolName_cmis30_year,classFinished);
									//综合评级
//									this.countFinishedClass(practiceAppraisalCout, schoolName_cmis30_year,classFinished);
									//已经完成评价数据
									int finishedCount = classFinished.size();
									AppraisalWritedStaticseDto schoolInfo = tempCountInfo.get(tempSchoolName);
									if(null!=schoolInfo){
										this.fillHighSchoolCount(years, totalClassCount,
												tempYear, finishedCount, schoolInfo,levelCode);
									}else{
										schoolInfo = new AppraisalWritedStaticseDto();
										this.fillHighSchoolCount(years, totalClassCount,
												tempYear, finishedCount, schoolInfo,levelCode);
										schoolInfo.setClassInfo(tempSchoolName);
										tempCountInfo.put(tempSchoolName, schoolInfo);
									}
								}
							}
						}
					}
				}
			}
			return tempCountInfo;
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB",e);
		}
		return null;
		/*try {
			List<String>gradeNums = this.initGradeNums(levelCode,currentTermId,termId);
			Map<String,AppraisalWritedStaticseDto> appraiseInfos = new LinkedHashMap<String, AppraisalWritedStaticseDto>();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("years", years);
			params.put("discode", discode);
			params.put("schoolName", schoolName);
			params.put("termId", termId);
			params.put("gradeNums", gradeNums);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB.query");
			List<String> countInfos = this.findList("IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("countInfos");
				}
			});
			this.retSchoolAppraisal_junior(years, appraiseInfos, countInfos);
			return appraiseInfos;
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB",e);
		}
		return null;*/
	}
	@DataSource("read")
	private Map<String,AppraisalWritedStaticseDto> queryQXAppraisalHighFromDB(String levelCode,List<String> years, String discode, String schoolName,String termId,String currentTermId){
		try {
			List<String>gradeNums = this.initGradeNums("3",currentTermId,termId);
//			Map<String,AppraisalWritedStaticseDto> appraiseInfos = new LinkedHashMap<String, AppraisalWritedStaticseDto>();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("years", years);
			params.put("discode", discode);
			params.put("schoolName", schoolName);
			params.put("termId", termId);
			params.put("gradeNums", gradeNums);
			//学校 学年 对应班级数量统计
			List<String> classCountInfos = this.totalClassCount(params);
			
			if(null==classCountInfos || classCountInfos.size()==0)return null;
			Map<String,AppraisalWritedStaticseDto>tempCountInfo = new HashMap<String, AppraisalWritedStaticseDto>();
//			String preSql = "IAppraisalWritedStaticsExt.queryQXCommonAppraisalInfoFromDB.query";
			//大部分评语统计信息
			Map<String, String> conmmonAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXCommonAppraisalInfoFromDB.query");
			//个性发展
			Map<String, String> personalAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXPersonalityAppraisalInfoFromDB.query");
			//综合实践活动
			Map<String, String> practiceAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXPracticeAppraisalInfoFromDB.query");
			//课程评价
			Map<String, String> learnprocessAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXLearnprocessAppraisalInfoFromDB.query");
			//班主任评价
			Map<String, String> assessAppraisalCout = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.queryQXAssessInfoFromDB.query");
			for(String classCountInfo : classCountInfos){//学校 学年 对应班级数量
				String[] school_class = classCountInfo.split("_");
				if(null!=school_class && school_class.length==2){
					String schoolName_cmis30 = school_class[0];
					String year_classCount = school_class[1];
					if(NestUtil.isNotEmpty(year_classCount)){
						String[] year_classCounts = year_classCount.split(",");
						for(String yc : year_classCounts){
							String[] ycs = yc.split("@");
							if(ycs.length==2){
								String tempYear = ycs[0];//学年
								String totalClassCount = ycs[1];//学校学年班级总数量
								String schoolName_cmis30_year = schoolName_cmis30+"@"+tempYear;
								String[] schoolName_cmis30_years = schoolName_cmis30_year.split("@");
								Set<String>classFinished = new HashSet<String>();
								if(null!=schoolName_cmis30_years && schoolName_cmis30_years.length==3){
									String tempSchoolName = schoolName_cmis30_years[0];//学校名称
									//普通评价统计个数
									this.countFinishedClass(conmmonAppraisalCout, schoolName_cmis30_year,classFinished);
									//个性评价
									this.countFinishedClass(personalAppraisalCout, schoolName_cmis30_year,classFinished);
									//班主任评价
									this.countFinishedClass(assessAppraisalCout, schoolName_cmis30_year,classFinished);
									//学业评级
									this.countFinishedClass(learnprocessAppraisalCout, schoolName_cmis30_year,classFinished);
									//综合评级
									this.countFinishedClass(practiceAppraisalCout, schoolName_cmis30_year,classFinished);
									//已经完成评价数据
									int finishedCount = classFinished.size();
									AppraisalWritedStaticseDto schoolInfo = tempCountInfo.get(tempSchoolName);
									if(null!=schoolInfo){
										this.fillHighSchoolCount(years, totalClassCount,
												tempYear, finishedCount, schoolInfo,levelCode);
									}else{
										schoolInfo = new AppraisalWritedStaticseDto();
										this.fillHighSchoolCount(years, totalClassCount,
												tempYear, finishedCount, schoolInfo,levelCode);
										schoolInfo.setClassInfo(tempSchoolName);
										tempCountInfo.put(tempSchoolName, schoolInfo);
									}
								}
							}
						}
					}
				}
			}
			return tempCountInfo;
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.queryQXAppraisalHighFromDB",e);
		}
		return null;
	}
	@DataSource("read")
	private Map<String, String> appraisalFinishedCount(Map<String, Object> params, String preSql) throws Exception {
		final Map<String,String>temp = new HashMap<String, String>();
		ISqlElement sqlDemo1=this.processSql(params, preSql);
		this.findList(preSql, params, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				temp.put(rs.getString("schoolInfos"), rs.getString("classInfos"));
				return null;
			}
		});
		return temp;
	}

	private List<String> totalClassCount(Map<String, Object> params)
			throws Exception {
		ISqlElement sqlDemo=this.processSql(params, "IAppraisalWritedStaticsExt.queryQXClassCoutFromDB.query");
		List<String> classCountInfos = this.findList("IAppraisalWritedStaticsExt.queryQXClassCoutFromDB.query", params, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("schoolinfo")+"_"+rs.getString("jbInfos");
			}
		});
		return classCountInfos;
	}

	private void fillHighSchoolCount(List<String> years,
			String totalClassCount, String tempYear, int finishedCount,
			AppraisalWritedStaticseDto schoolInfo,String levelCode) {
		if(years.get(0).equals(tempYear)){
			schoolInfo.setOneCount(Integer.parseInt(totalClassCount));
			schoolInfo.setOneCountFinished(finishedCount);
		}else if(years.get(1).equals(tempYear)){
			schoolInfo.setTwoCount(Integer.parseInt(totalClassCount));
			schoolInfo.setTwoCountFinished(finishedCount);
		}else if(years.get(2).equals(tempYear)){
			schoolInfo.setThreeCount(Integer.parseInt(totalClassCount));
			schoolInfo.setThreeCountFinished(finishedCount);
		}else if("2012002".equals(levelCode)&&years.get(3).equals(tempYear)){
			schoolInfo.setFourCount(Integer.parseInt(totalClassCount));
			schoolInfo.setFourCountFinished(finishedCount);
		}
	}

	private void countFinishedClass(
			Map<String, String> conmmonAppraisalCout,
			String school_cmis30_year, Set<String> classFinished) {
		if(null!=conmmonAppraisalCout && conmmonAppraisalCout.size()>0){
			String classes = conmmonAppraisalCout.get(school_cmis30_year);
			if(NestUtil.isNotEmpty(classes)){
				for(String classId : classes.split(",")){
					classFinished.add(classId);
				}
			}
		}
	}
	
	private List<String> initGradeNums(String levelCode, String currentTermId,String termId) {
		Integer oldTermId = Integer.parseInt(termId.substring(0, 4));
		Integer newTermId = Integer.parseInt(currentTermId.substring(0, 4));
		List<String>years = new ArrayList<String>();
		if("3".equals(levelCode)){
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

	private void retSchoolAppraisal_junior(List<String> years,
			Map<String, AppraisalWritedStaticseDto> appraiseInfos,
			List<String> countInfos) {
		if(null!=countInfos && countInfos.size()>0){
			for(String contInfo : countInfos){
				String[] info = contInfo.split("@");
				if(null!=info && info.length==6){
					String tempSchoolName = info[0];
					String cmis30id = info[1];
					String year = info[2];
					String campusid = info[3];
					String classid = info[4];
					String count = info[5];
					
					AppraisalWritedStaticseDto schoolInfo = appraiseInfos.get(tempSchoolName+"_"+cmis30id);
					if(null!=schoolInfo){//如果这个学校的数据部位null 那么取出数据并累加已经评价的班级数据
						if(years.get(0).equals(year)){
							schoolInfo.setOneCount(schoolInfo.getOneCount()+1);
							if("0".equals(count)){
								schoolInfo.setOneCountFinished(schoolInfo.getOneCountFinished()+1);
							}
						}else if(years.get(1).equals(year)){
							schoolInfo.setTwoCount(schoolInfo.getTwoCount()+1);
							if("0".equals(count)){
								schoolInfo.setTwoCountFinished(schoolInfo.getTwoCountFinished()+1);
							}
						}else if(years.get(2).equals(year)){
							schoolInfo.setThreeCount(schoolInfo.getThreeCount()+1);
							if("0".equals(count)){
								schoolInfo.setThreeCountFinished(schoolInfo.getThreeCountFinished()+1);
							}
						}else if(years.get(3).equals(year)){
							schoolInfo.setFourCount(schoolInfo.getFourCount()+1);
							if("0".equals(count)){
								schoolInfo.setFourCountFinished(schoolInfo.getFourCountFinished()+1);
							}
						}
					}else{//新建一个对象  并将将数据依次放入
						schoolInfo = new AppraisalWritedStaticseDto();
						if(years.get(0).equals(year)){
							schoolInfo.setOneCount(1);
							schoolInfo.setOneCountFinished(1);
						}else if(years.get(1).equals(year)){
							schoolInfo.setTwoCount(1);
							schoolInfo.setTwoCountFinished(1);
						}else if(years.get(2).equals(year)){
							schoolInfo.setThreeCount(1);
							schoolInfo.setThreeCountFinished(1);
						}else if(years.get(3).equals(year)){
							schoolInfo.setFourCount(1);
							schoolInfo.setFourCountFinished(1);
						}
						schoolInfo.setClassInfo(tempSchoolName);
						appraiseInfos.put(tempSchoolName+"_"+cmis30id, schoolInfo);
					}
				}else{
					continue;
				}
			}
		}
	}

	@Override
	public List<AppraisalWritedStaticseDto> queryQXAppraiseStatics(String levelCode, List<String> years, String discode,String schoolName,String termId,String currentTermId) {
		Map<String, AppraisalWritedStaticseDto> commonAppraisalCoutInfo = null;
		if("2012002".equals(levelCode)){
			commonAppraisalCoutInfo = new HashMap<String, AppraisalWritedStaticseDto>();
			Map<String, AppraisalWritedStaticseDto> commonAppraisalCoutInfo_three = this.queryQXAppraiseInfosFromDB(levelCode, years, discode, schoolName,termId,currentTermId,"3");
			Map<String, AppraisalWritedStaticseDto> commonAppraisalCoutInfo_four = this.queryQXAppraiseInfosFromDB(levelCode, years, discode, schoolName,termId,currentTermId,"4");
			if(null!=commonAppraisalCoutInfo_four &&commonAppraisalCoutInfo_four.size()>0){
				if(null!=commonAppraisalCoutInfo_three&&commonAppraisalCoutInfo_three.size()>0){
					Set<String> school_cmis30Ids = commonAppraisalCoutInfo_four.keySet();
					for(String school_cmis30Id : school_cmis30Ids){
						AppraisalWritedStaticseDto awsd_three = commonAppraisalCoutInfo_three.get(school_cmis30Id);
						if(null!=awsd_three){
							AppraisalWritedStaticseDto awsd_Four = commonAppraisalCoutInfo_four.get(school_cmis30Id);
							awsd_three.setOneCount(awsd_Four.getOneCount()+awsd_three.getOneCount());
							awsd_three.setOneCountFinished(awsd_Four.getOneCountFinished()+awsd_three.getOneCountFinished());
							
							awsd_three.setTwoCount(awsd_Four.getTwoCount()+awsd_three.getTwoCount());
							awsd_three.setTwoCountFinished(awsd_Four.getTwoCountFinished()+awsd_three.getTwoCountFinished());
							
							
							awsd_three.setThreeCount(awsd_Four.getThreeCount()+awsd_three.getThreeCount());
							awsd_three.setThreeCountFinished(awsd_Four.getThreeCountFinished()+awsd_three.getThreeCountFinished());
							
							awsd_three.setFourCount(awsd_Four.getFourCount()+awsd_three.getFourCount());
							awsd_three.setFourCountFinished(awsd_Four.getFourCountFinished()+awsd_three.getFourCountFinished());
							
						}else{
							commonAppraisalCoutInfo_three.put(school_cmis30Id, commonAppraisalCoutInfo_four.get(school_cmis30Id));
						}
					}
					commonAppraisalCoutInfo.putAll(commonAppraisalCoutInfo_three);
				}else{
					commonAppraisalCoutInfo.putAll(commonAppraisalCoutInfo_four);
				}
			}else if(null!=commonAppraisalCoutInfo_three){
				commonAppraisalCoutInfo.putAll(commonAppraisalCoutInfo_three);
			}
		}else{
			commonAppraisalCoutInfo = this.queryQXAppraisalHighFromDB(levelCode, years, discode, schoolName,termId,currentTermId);
		}
		List<AppraisalWritedStaticseDto>staticsInfos = new ArrayList<AppraisalWritedStaticseDto>();
		if(null!=commonAppraisalCoutInfo && commonAppraisalCoutInfo.size()>0){
			Set<String> schoolInfos = commonAppraisalCoutInfo.keySet();
			for(String schoolInfo : schoolInfos){
				staticsInfos.add(commonAppraisalCoutInfo.get(schoolInfo));
			}
		}
		this.sortSchoolCodeAsc(levelCode,schoolName,discode,staticsInfos);
		this.totalCount(levelCode, staticsInfos);
		this.cleanZeros(staticsInfos);
		return staticsInfos;
	}


	private void sortSchoolCodeAsc(String levelCode, String schoolName,String discode, List<AppraisalWritedStaticseDto> staticsInfos) {
		List<String> schoolAsc = this.querySchoolAsc(levelCode,schoolName,discode);
		if(null!=schoolAsc && !schoolAsc.isEmpty()&&null!=staticsInfos && !staticsInfos.isEmpty()){
			List<AppraisalWritedStaticseDto> temp = new ArrayList<AppraisalWritedStaticseDto>();
			for(String tempSchoolName : schoolAsc){
				boolean flag = false;
				for(AppraisalWritedStaticseDto awsd : staticsInfos){
					if(tempSchoolName.equals(awsd.getClassInfo())){
						temp.add(awsd);
						flag = true;
						break;
					}
				}
				if(!flag){
					AppraisalWritedStaticseDto awsd = new AppraisalWritedStaticseDto();
					awsd.setClassInfo(tempSchoolName);
					temp.add(awsd);
				}
			}
			staticsInfos.clear();
			staticsInfos.addAll(temp);
		}else if(null!=schoolAsc && !schoolAsc.isEmpty()){
			for(String tempSchoolName : schoolAsc){
				AppraisalWritedStaticseDto awsd = new AppraisalWritedStaticseDto();
				awsd.setClassInfo(tempSchoolName);
				staticsInfos.add(awsd);
			}
		}
	}

	private List<String> querySchoolAsc(String levelCode, String schoolName,String discode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("discode", discode);
			params.put("levelCode", levelCode);
			params.put("schoolName", schoolName);
			ISqlElement sqlDemo1=this.processSql(params, "IAppraisalWritedStaticsExt.querySchoolAsc.query");
			return this.findList("IAppraisalWritedStaticsExt.querySchoolAsc.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("schoolname");
				}
			});
		} catch (Exception e) {
			logger.error("querySchoolAsc(String,String,String)", e);
		}
		return null;
	}

/*	private void listSort(List<AppraisalWritedStaticseDto> staticsInfos) {
		if(null!=staticsInfos && staticsInfos.size()>0){
	        Collections.sort(staticsInfos, new Comparator<AppraisalWritedStaticseDto>(){  
	        	  
	              
	             * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	               
	            public int compare(AppraisalWritedStaticseDto o1, AppraisalWritedStaticseDto o2) {  
	              
	                //按照学生的年龄进行升序排列  
	                if(Integer.parseInt(o1.getDiscode()) > Integer.parseInt(o2.getDiscode())){  
	                    return 1;  
	                }  
	                if(Integer.parseInt(o1.getDiscode()) == Integer.parseInt(o2.getDiscode())){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        }); 
		}
	}*/
	

	private void cleanZeros(List<AppraisalWritedStaticseDto> staticseDtos2) {
		if(null!=staticseDtos2 && staticseDtos2.size()>0){
			for(AppraisalWritedStaticseDto awsd : staticseDtos2){
				if(0==awsd.getOneCount()){
					awsd.setOneCount(null);
					awsd.setOneCountFinished(null);
				}
				if(0==awsd.getTwoCount()){
					awsd.setTwoCount(null);
					awsd.setTwoCountFinished(null);
				}
				if(0==awsd.getThreeCount()){
					awsd.setThreeCount(null);
					awsd.setThreeCountFinished(null);
				}
				if(0==awsd.getFourCount()){
					awsd.setFourCount(null);
					awsd.setFourCountFinished(null);
				}
			}
		}
	}
	
	@Override
	public List<AppraisalWritedStaticseDto> querySJAppraiseStatics(
			String levelCode, List<String> years, String discode,
			String termId, String currentTermId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("levelCode", levelCode);
		params.put("years", years);
		params.put("discode", discode);
		params.put("termId", termId);
		List<String> quInfos = this.queryQXInfos(levelCode,discode);
		params.put("quInfos", quInfos);
		List<AppraisalWritedStaticseDto> tempAppraisalCounts = new ArrayList<AppraisalWritedStaticseDto>();
		if("2012002".equals(levelCode)){
			params.put("gradeNums_three", this.initGradeNums("3", currentTermId, termId));
			params.put("gradeNums_four", this.initGradeNums("4", currentTermId, termId));
			this.querySJAppraisalJuniorCountsInfo(params,tempAppraisalCounts);
		}else{
			List<String> gradeNums = this.initGradeNums("3", currentTermId, termId);
			params.put("gradeNums", gradeNums);
			this.querySJAppraisalHighCountsInfo(params,tempAppraisalCounts);
		}
		this.cleanZeros(tempAppraisalCounts);
		return tempAppraisalCounts;
	}
	@DataSource("read")
	private List<String> queryQXInfos(String levelCode, String discode) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("discode", discode);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryQXInfos.query");
			return this.findList("IAppraisalStaticsExt.queryQXInfos.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String discode = rs.getString("discode");
					String zoneName = rs.getString("name");
					return discode+"@"+zoneName;
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryQXInfos",e);
		}
		return null;
	}

	private void querySJAppraisalHighCountsInfo(Map<String, Object> params,List<AppraisalWritedStaticseDto> tempAppraisalCounts) {
		try {
			//获取相应的学校统计信息
//			params.remove("levelCode");
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalWritedStaticsExt.querySJSchoolCountInfoFromDB_high.query");
			List<String> discodeSchoolCountInfo = this.findList("IAppraisalWritedStaticsExt.querySJSchoolCountInfoFromDB_high.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("zoneInfos")+"_"+rs.getString("schoolCount");
				}
			});
			//获取已经完成相应学校统计信息
			params.put("flag", "1");
			Map<String, String> assessFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_high.query");
			params.put("flag", "2");
			Map<String, String> appraisalFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_high.query");
			params.put("flag", "3");
			Map<String, String> personalFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_high.query");
			params.put("flag", "4");
			Map<String, String> practiceFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_high.query");
			params.put("flag", "5");
			Map<String, String> learnprocessFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_high.query");
			//组装数据
			if(null!=discodeSchoolCountInfo && discodeSchoolCountInfo.size()>0){
				Map<String,AppraisalWritedStaticseDto>tempCountInfo = new HashMap<String, AppraisalWritedStaticseDto>();
				for(String name_discode_year_counts : discodeSchoolCountInfo){
					String[] name_discode_year_count = name_discode_year_counts.split("_");
					if(null!=name_discode_year_count && name_discode_year_count.length==2){
						String name_discode_year = name_discode_year_count[0];//可以做key查出相应已经完成评价的学校
						String totalSchoolCount = name_discode_year_count[1];//某一学年学校总数
						if(NestUtil.isNotEmpty(name_discode_year) && 3==name_discode_year.split("@").length){
							String zoneName = name_discode_year.split("@")[0];
							String discode = name_discode_year.split("@")[1];
							String year = name_discode_year.split("@")[2];
							Set<String>classFinished = new HashSet<String>();
							//取已经评价的学校数据
							this.countFinishedClass(assessFinishedCount, name_discode_year,classFinished);
							//班主任评语
							this.countFinishedClass(appraisalFinishedCount, name_discode_year,classFinished);
							//个性
							this.countFinishedClass(personalFinishedCount, name_discode_year,classFinished);
							//综合
							this.countFinishedClass(practiceFinishedCount, name_discode_year,classFinished);
							//课程评语
							this.countFinishedClass(learnprocessFinishedCount, name_discode_year,classFinished);
							int finishedCount = classFinished.size();
							AppraisalWritedStaticseDto schoolInfo = tempCountInfo.get(zoneName);
							if(null!=schoolInfo){
								this.fillHighSchoolCount((List<String>)params.get("years"), totalSchoolCount,
										year, finishedCount, schoolInfo,(String)params.get("levelCode"));
							}else{
								schoolInfo = new AppraisalWritedStaticseDto();
								this.fillHighSchoolCount((List<String>)params.get("years"), totalSchoolCount,
										year, finishedCount, schoolInfo,(String)params.get("levelCode"));
								schoolInfo.setClassInfo(zoneName);
								schoolInfo.setDiscode(discode);
								tempCountInfo.put(zoneName, schoolInfo);
							}
						}
					}
				}
				if(null!=tempCountInfo && tempCountInfo.size()>0){
					Set<String> schoolInfos = tempCountInfo.keySet();
					for(String schoolInfo : schoolInfos){
						tempAppraisalCounts.add(tempCountInfo.get(schoolInfo));
					}
					this.listSort(tempAppraisalCounts,(List<String>)params.get("quInfos"));
					this.totalCount((String)params.get("levelCode"), tempAppraisalCounts);
				}
			}
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.querySJAppraisalJuniorCountsInfo",e);
		}
	}

	@SuppressWarnings("unchecked")
	@DataSource("read")
	private void querySJAppraisalJuniorCountsInfo(Map<String, Object> params,List<AppraisalWritedStaticseDto> tempAppraisalCounts) {
		try {
			//获取相应的学校统计信息
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalWritedStaticsExt.querySJSchoolCountInfoFromDB.query");
			List<String> discodeSchoolCountInfo = this.findList("IAppraisalWritedStaticsExt.querySJSchoolCountInfoFromDB.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("zoneInfos")+"_"+rs.getString("schoolCount");
				}
			});
			//获取已经完成相应学校统计信息
			Map<String, String> commonFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_junior.query");
			params.put("flag", "1");
			Map<String, String> assessFinishedCount = this.appraisalFinishedCount(params, "IAppraisalWritedStaticsExt.querySJAppraisalCountInfoFromDB_junior.query");
			//组装数据
			if(null!=discodeSchoolCountInfo && discodeSchoolCountInfo.size()>0){
				Map<String,AppraisalWritedStaticseDto>tempCountInfo = new HashMap<String, AppraisalWritedStaticseDto>();
				for(String name_discode_year_counts : discodeSchoolCountInfo){
					String[] name_discode_year_count = name_discode_year_counts.split("_");
					if(null!=name_discode_year_count && name_discode_year_count.length==2){
						String name_discode_year = name_discode_year_count[0];//可以做key查出相应已经完成评价的学校
						String totalSchoolCount = name_discode_year_count[1];//某一学年学校总数
						if(NestUtil.isNotEmpty(name_discode_year) && 3==name_discode_year.split("@").length){
							String zoneName = name_discode_year.split("@")[0];
							String discode = name_discode_year.split("@")[1];
							String year = name_discode_year.split("@")[2];
							Set<String>classFinished = new HashSet<String>();
							//取已经评价的学校数据
							this.countFinishedClass(commonFinishedCount, name_discode_year,classFinished);
							//班主任评语
							this.countFinishedClass(assessFinishedCount, name_discode_year,classFinished);
							int finishedCount = classFinished.size();
							AppraisalWritedStaticseDto schoolInfo = tempCountInfo.get(zoneName);
							if(null!=schoolInfo){
								this.fillHighSchoolCount((List<String>)params.get("years"), totalSchoolCount,
										year, finishedCount, schoolInfo,(String)params.get("levelCode"));
							}else{
								schoolInfo = new AppraisalWritedStaticseDto();
								this.fillHighSchoolCount((List<String>)params.get("years"), totalSchoolCount,
										year, finishedCount, schoolInfo,(String)params.get("levelCode"));
								schoolInfo.setClassInfo(zoneName);
								schoolInfo.setDiscode(discode);
								tempCountInfo.put(zoneName, schoolInfo);
							}
						}
					}
				}
				if(null!=tempCountInfo && tempCountInfo.size()>0){
					Set<String> schoolInfos = tempCountInfo.keySet();
					for(String schoolInfo : schoolInfos){
						tempAppraisalCounts.add(tempCountInfo.get(schoolInfo));
					}
					this.listSort(tempAppraisalCounts,(List<String>)params.get("quInfos"));
					this.totalCount((String)params.get("levelCode"), tempAppraisalCounts);
				}
			}
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.querySJAppraisalJuniorCountsInfo",e);
		}
	}

	private void listSort(List<AppraisalWritedStaticseDto> tempAppraisalCounts,List<String> list) {
		List<AppraisalWritedStaticseDto>tem = new ArrayList<AppraisalWritedStaticseDto>();
		if(null!=list && list.size()>0 && null!=tempAppraisalCounts && tempAppraisalCounts.size()>0){
			for(String discode_zoneName : list){
				boolean flag = false;
				String discode = discode_zoneName.split("@")[0];
				String zoneName = discode_zoneName.split("@")[1];
				for(AppraisalWritedStaticseDto awsd : tempAppraisalCounts){
					if(zoneName.equals(awsd.getClassInfo())){
						tem.add(awsd);
						flag = true;
						break;
					}
				}
				if(!flag){
					AppraisalWritedStaticseDto awsd = new AppraisalWritedStaticseDto();
					awsd.setClassInfo(zoneName);
					awsd.setDiscode(discode);
					tem.add(awsd);
				}
			}
		}else if(null!=list && list.size()>0){
			for(String discode_zoneName : list){
				AppraisalWritedStaticseDto awsd = new AppraisalWritedStaticseDto();
				awsd.setClassInfo(discode_zoneName.split("@")[1]);
				awsd.setDiscode(discode_zoneName.split("@")[0]);
				tem.add(awsd);
			}
		}
		if(null!=tem && tempAppraisalCounts!=null){
			tempAppraisalCounts.clear();
			tempAppraisalCounts.addAll(tem);
		}
	}

	@Override
	public List<String> querytest(String username, String flag,List<String>test) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("username", username);
			params.put("flag", flag);
			params.put("test", "name = '张三' or name = 'lisi'");
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalWritedStaticsExt.querytest.query");
			return this.findList("IAppraisalWritedStaticsExt.querytest.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("countInfos");
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalWritedStaticsExt.queryQXAppraiseInfosFromDB",e);
		}
		return null;
	}
}
