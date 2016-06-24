package com.flyrish.hades.webapp.action.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.LearnprocessAppraisaDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class QueryCommentAction extends BaseAction{
	private String isStartAppraiseCache;
	public String date_content;
	public String cmis30id;
	public String discode;
	//单个学生属性
	public String studentid;
	public String key;
	public String name;
	//年级班级信息
	public String classId;
	public String gradeId;
	public String termId;
	public String zsTermId;
	//所属班级信息
	public String classInfo;
	public String ids;
	public String idName;
	public String levelNum;
	//是否查询历史数据
	public String isHistory;
	//栏目类型
	public String sectionCode;
	public String sectionName;
	//统计班主任评价每个学生评价信息
	public Map<String,List<String>>masterAppriseStudentInfo = new LinkedHashMap<String,List<String>>();
	//统计班主任评价班级学生评价信息
	public Map<String,List<String>>masterAppriseClassInfo = new LinkedHashMap<String,List<String>>();
	//班级学生数量
	List<SchoolTreeDto>stuIfos;
	//班主任id
	public String teacherId;
	@Spring
	public IMasterAppriseExt masterAppriseExt;
	
	@Spring
	public AppriseMasterAppriseExt  appriseMasterAppriseExt;
	//评价人
	public  String appraiser;
	//教育id
	public String eduid;
	
	public List<Object> Appraisal1 = null;  //班主任评价
	
	public List<Object> Appraisal2 = null;  //任课教师评价
	
	public List<Object> Appraisal3 = null;  //学生自评
	
	public List<Object> Appraisal4 = null;  //同学互评

	public List<Object> Appraisal5 = null;  //家长评价
	
	//当前管辖班级最高年级
	public String maxClassId;

	@DefaultAction
	public Object queryByColumn(HttpServletRequest req,HttpSession session){
			UserDto loginInfo = this.getLoginInfo(req);
			cmis30id=loginInfo.getCmis30id();//"230003";
			discode=loginInfo.getDiscode();//"110101";
			teacherId=loginInfo.getTeacherid();//"194858";
			appraiser = loginInfo.getTeacherName();
			this.getClassAppriseInfo(sectionCode,req);
			return "queryCountByColumn.jsp";
	}
	/**
	 * 填充页面展示数据模型
	 * @param sectionCode
	 * @param req
	 * @return
	 */
	private void getClassAppriseInfo(String sectionCode,HttpServletRequest req) {
		UserDto loginInfo = this.getLoginInfo(req);
		//页面栏目展示
		this.viewData(sectionCode);
		
		Map<String,Object>params = new HashMap<String, Object>();
		//查询参数
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("techerid", userDto.getTeacherid());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		//统计班级总人数  已经完成   未完成学生
		List<CampusDto> campus = new ArrayList<CampusDto>();
		if(Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())){
			//统计班级总人数  已经完成   未完成学生
			campus=masterAppriseExt.getClassInfos(params);
		}else if(Constant.USER_TYPE_COURSEMASTER.equals(userDto.getUserRealType())){
			campus=masterAppriseExt.getTeacherClassInfos(params);
		}
		
		if(null!=campus && campus.size()>0){
			//获取最后一个班级--年级号最大的
			CampusDto maxClass = campus.get(campus.size()-1);
			//获取最大年级号
			int maxGradeNum = Integer.parseInt(maxClass.getGradeNum());
			for (CampusDto dto : campus) {
				init();
				classInfo = dto.getLevelName() + "-"+ dto.getGradeName() + "-"	+ dto.getClassName();
				classId=dto.getClassId();
				gradeId=dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId();
				levelNum=dto.getGradeNum();
				
				
				if(!NestUtil.isNotEmpty(isHistory)){
					termId=loginInfo.getTermId();
					//高中综素 组装学期id
					zsTermId=levelNum+termId.substring(4, 5);
				}else{
					String gradeNum = zsTermId.substring(0,1);
					String num = zsTermId.substring(1,2);
					termId = masterAppriseExt.getHSHistoryTermId(classId, gradeNum, num);
					maxGradeNum = Integer.parseInt(gradeNum);
				}
				if(Integer.parseInt(dto.getGradeNum())>maxGradeNum ||(Integer.parseInt(dto.getGradeNum())==maxGradeNum  && Integer.parseInt(loginInfo.getTermId().substring(4, 5))>=Integer.parseInt(zsTermId.substring(1,2)))){
				//班级学生数量
				stuIfos = this.getStudentInfos(classId,gradeId,req);
				//某个班级总人数
				List<String>classCount = new ArrayList<String>();
				classCount.add(classInfo);
				int studentCount=0;
				if(null!=stuIfos && stuIfos.size()>0){
					studentCount=stuIfos.size();
				}
				classCount.add(String.valueOf(studentCount));
				
				getEvaluationCount(sectionCode,stuIfos,zsTermId,termId); //获取评价数
		
				classCount.add(String.valueOf(Appraisal1.size()));  //班主任评价数
				classCount.add(String.valueOf(Appraisal2.size()));  //任课教师评价数
				classCount.add(String.valueOf(Appraisal3.size()));  //学生自评
				classCount.add(String.valueOf(Appraisal4.size()));   //同学互评
				classCount.add(String.valueOf(Appraisal5.size()));   //家长评价
				masterAppriseClassInfo.put(gradeId, classCount);
				}
			}
		}
	}
	//获取评价数
	public void getEvaluationCount(String sectionCode,List<SchoolTreeDto> stuIfos,String zsTermId,String termId){
		isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		if("1".equals(isStartAppraiseCache)){//走缓存
			this.queryDataFromCache(sectionCode, stuIfos, zsTermId);
		}else{//走数据库
			this.queryDataFromDB(sectionCode, stuIfos, zsTermId);
		}
	}
	private void queryDataFromCache(String sectionCode,List<SchoolTreeDto> stuIfos, String zsTermId) {
		List<String> appraisalTypeIds =  masterAppriseExt.getAppraisalTypeByUpAppraisalTypeId(sectionCode==null?null:Integer.parseInt(sectionCode));
		List<String> eduIds = screeningEduId(stuIfos);
		if(Constant.BEGIN_OF_THE_NEW_TERM.equals(sectionCode)  && eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			screeningEvaluation(appraisals);
		}else if(Constant.AT_THE_END_OF_THE_TERM.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<AppraiseBaseDto> appraiseBaseDto = new ArrayList<AppraiseBaseDto>();
			appriseMasterAppriseExt.queryAssess(eduIds, zsTermId, cmis30id, discode, appraiseBaseDto);
			screeningEvaluation6(appraiseBaseDto);
			screeningEvaluation(appraisals);
		}else if(Constant.IDEOLOGICAL_MORALITY.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackageFromCache(Constant.TYPE_SXJLD, zsTermId, eduIds);
			screeningEvaluation(appraisals);
			screeningEvaluation1(recordPackages);
		}else if(Constant.COOPERATION_AND_EXCHANGE.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackageFromCache(Constant.TYPE_HZ_JLD, zsTermId, eduIds);
			screeningEvaluation(appraisals);
			screeningEvaluation1(recordPackages);
		}else if(Constant.SPORTS_AND_HEALTH.equals(sectionCode)&& eduIds.size()>0){//  体质健康不进行统计
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			screeningEvaluation(appraisals);  
		}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackageFromCache(Constant.TYPE_SMYBX_JLD, zsTermId, eduIds);
			screeningEvaluation(appraisals);  
			screeningEvaluation1(recordPackages);
		}else if(Constant.PERSONALITY_DEVELOPMENT.equals(sectionCode)&& eduIds.size()>0){  
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<PersonalityDto> personalitys = masterAppriseExt.getPersonalityFromCache(Constant.TYPE_GXFZ_JBQK,zsTermId, eduIds); //个性发展基本情况
			screeningEvaluation(appraisals);  
			screeningEvaluation4(personalitys);
		}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisalFromCache(appraisalTypeIds, zsTermId,eduIds);
			List<LearnprocessWorksDto> learnprocessWorks = masterAppriseExt.getLearnprocessWorksFromCache(Constant.TYPE_XY_GCJL,zsTermId, eduIds);//查询学科作品展示
			List<LearnprocessAppraisaDto> learnprocessAppraisas = masterAppriseExt.getLearnprocessAppraisaFromCache(Constant.TYPE_KE_CHENG_PINGYU,zsTermId, eduIds);//课程评语
			screeningEvaluation(appraisals);
			screeningEvaluation2(learnprocessWorks);
			screeningEvaluation3(learnprocessAppraisas);
		}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(sectionCode)&& eduIds.size()>0){ 
			List<PracticesDto> practicess = masterAppriseExt.getPracticesFromCache(appraisalTypeIds, zsTermId, eduIds);
			screeningEvaluation5(practicess);
		}
	}
	private void queryDataFromDB(String sectionCode,List<SchoolTreeDto> stuIfos, String zsTermId) {
		List<String> appraisalTypeIds =  masterAppriseExt.getAppraisalTypeByUpAppraisalTypeId(sectionCode==null?null:Integer.parseInt(sectionCode));
		List<String> eduIds = screeningEduId(stuIfos);
		if(Constant.BEGIN_OF_THE_NEW_TERM.equals(sectionCode)  && eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			screeningEvaluation(appraisals);
		}else if(Constant.AT_THE_END_OF_THE_TERM.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<AppraiseBaseDto> appraiseBaseDto = new ArrayList<AppraiseBaseDto>();
			appriseMasterAppriseExt.queryAssess(eduIds, zsTermId, cmis30id, discode, appraiseBaseDto);
			screeningEvaluation6(appraiseBaseDto);
			screeningEvaluation(appraisals);
		}else if(Constant.IDEOLOGICAL_MORALITY.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackage(Constant.TYPE_SXJLD, zsTermId, eduIds, discode, cmis30id);
			screeningEvaluation(appraisals);
			screeningEvaluation1(recordPackages);
		}else if(Constant.COOPERATION_AND_EXCHANGE.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackage(Constant.TYPE_HZ_JLD, zsTermId, eduIds, discode, cmis30id);
			screeningEvaluation(appraisals);
			screeningEvaluation1(recordPackages);
		}else if(Constant.SPORTS_AND_HEALTH.equals(sectionCode)&& eduIds.size()>0){//  体质健康不进行统计
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			screeningEvaluation(appraisals);  
		}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<RecordPackageDto> recordPackages = masterAppriseExt.getRecordpackage(Constant.TYPE_SMYBX_JLD, zsTermId, eduIds, discode, cmis30id);
			screeningEvaluation(appraisals);  
			screeningEvaluation1(recordPackages);
		}else if(Constant.PERSONALITY_DEVELOPMENT.equals(sectionCode)&& eduIds.size()>0){  
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<PersonalityDto> personalitys = masterAppriseExt.getPersonality(zsTermId, eduIds, discode, cmis30id); //个性发展基本情况
			screeningEvaluation(appraisals);  
			screeningEvaluation4(personalitys);
		}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(sectionCode)&& eduIds.size()>0){
			List<AppraisalDto> appraisals =  masterAppriseExt.getAppraisal(appraisalTypeIds, zsTermId,eduIds , discode, cmis30id);
			List<LearnprocessWorksDto> learnprocessWorks = masterAppriseExt.getLearnprocessWorks(zsTermId, eduIds, discode, cmis30id);//查询学科作品展示
			List<LearnprocessAppraisaDto> learnprocessAppraisas = masterAppriseExt.getLearnprocessAppraisa(zsTermId, eduIds, discode, cmis30id);//课程评语
			screeningEvaluation(appraisals);
			screeningEvaluation2(learnprocessWorks);
			screeningEvaluation3(learnprocessAppraisas);
		}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(sectionCode)&& eduIds.size()>0){ 
			List<PracticesDto> practicess = masterAppriseExt.getPractices(appraisalTypeIds, zsTermId, eduIds, discode, cmis30id);
			screeningEvaluation5(practicess);
		}
	}
	
	public void screeningEvaluation6(List<AppraiseBaseDto> appraiseBaseDto){
		
		if(null!=appraiseBaseDto){
			for(AppraiseBaseDto appraiseBase : appraiseBaseDto){
				Appraisal1.add(appraiseBase);  //班主任评价
			}
		}
	}
	public void screeningEvaluation5(List<PracticesDto> practicess){
		if(null!=practicess){
			for(PracticesDto practices : practicess){
				Appraisal3.add(practices);  //自评
			}
		}
		
	}
	public void screeningEvaluation4(List<PersonalityDto> personalitys){
		if(null!=personalitys){
			for(PersonalityDto personality : personalitys){
					Appraisal3.add(personality);  //自评
			}
		}
	}
	public void screeningEvaluation3(List<LearnprocessAppraisaDto> learnprocessAppraisas){
		if(null!=learnprocessAppraisas){
			for(LearnprocessAppraisaDto learnprocessAppraisaDto : learnprocessAppraisas){
					Appraisal2.add(learnprocessAppraisaDto);  //任课教师评价
			}
		}
	}
	
	public void screeningEvaluation2(List<LearnprocessWorksDto> learnprocessWorkss){
		if(null!=learnprocessWorkss){
			for(LearnprocessWorksDto learnprocessWorksDto : learnprocessWorkss){
					Appraisal3.add(learnprocessWorksDto);  //自评
			}
		}
	}
	
	public void screeningEvaluation(List<AppraisalDto> appraisals){
		if(null!=appraisals){
			for(AppraisalDto appraisalDto : appraisals){
				if(1==appraisalDto.getAppraseridentify()){
					Appraisal3.add(appraisalDto);  //自评
				}else if(2==appraisalDto.getAppraseridentify()){
					Appraisal4.add(appraisalDto);  //同学互评
				}else if(3==appraisalDto.getAppraseridentify()){
					Appraisal2.add(appraisalDto);  //任课教师评价
				}else if(4==appraisalDto.getAppraseridentify()){
					Appraisal1.add(appraisalDto);  //班主任评价
				}else if(5==appraisalDto.getAppraseridentify()){
					Appraisal5.add(appraisalDto);  //家长评价
				}
			}
		}
	}
	public void screeningEvaluation1(List<RecordPackageDto> recordPackages){
		if(null!=recordPackages){
			for(RecordPackageDto recordPackageDto : recordPackages){
				if(1==recordPackageDto.getAppraseridentify()){
					Appraisal3.add(recordPackageDto);  //自评
				}else if(2==recordPackageDto.getAppraseridentify()){
					Appraisal4.add(recordPackageDto);  //同学互评
				}else if(3==recordPackageDto.getAppraseridentify()){
					Appraisal2.add(recordPackageDto);  //任课教师评价
				}else if(4==recordPackageDto.getAppraseridentify()){
					Appraisal1.add(recordPackageDto);  //班主任评价
				}else if(5==recordPackageDto.getAppraseridentify()){
					Appraisal5.add(recordPackageDto);  //家长评价
				}
			}
		}
		
	}
	/**
	 * 获取班主任管辖下所有学生id
	 * @param classIds
	 * @param gradeId
	 * @param req
	 * @return
	 */
	private List<SchoolTreeDto> getStudentInfos(String classId, String gradeId,HttpServletRequest req) {
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("lid",classId );
		params.put("cmis30Id",cmis30id );
		params.put("discode",discode );
		return masterAppriseExt.getStudentInfo(params);
	}
	/**
	 * 页面展示栏目
	 * @param sectionCode
	 */
	private void viewData(String sectionCode) {
		if(null!=sectionCode && sectionCode.equals("10")){
			sectionName="新学期伊始的我";
		}else if(null!=sectionCode && sectionCode.equals("20")){
			sectionName="学期结束时的我";
		}else if(null!=sectionCode && sectionCode.equals("30")){
			sectionName="思想道德"; 
		}else if(null!=sectionCode && sectionCode.equals("40")){
			sectionName="合作与交流"; 
		}else if(null!=sectionCode && sectionCode.equals("50")){
			sectionName="运动与健康"; 
		}else if(null!=sectionCode && sectionCode.equals("60")){
			sectionName="审美与表现"; 
		}else if(null!=sectionCode && sectionCode.equals("70")){
			sectionName="个性发展"; 
		}else if(null!=sectionCode && sectionCode.equals("80")){
			sectionName="学业成就"; 
		}else if(null!=sectionCode && sectionCode.equals("90")){
			sectionName="综合实践活动"; 
		}
	}
	
	private void init(){
		Appraisal1 =  new ArrayList<Object>();
		Appraisal2 =  new ArrayList<Object>();
		Appraisal3 =  new ArrayList<Object>();
		Appraisal4 =  new ArrayList<Object>();
		Appraisal5 =  new ArrayList<Object>();
		
	}
	
	@SuppressWarnings("unused")
	private List<String> screeningEduId(List<SchoolTreeDto> stuIfos){
		List<String> eduIds = new ArrayList<String>();
		if(stuIfos==null){
			return eduIds;
		}
		for(SchoolTreeDto std : stuIfos){
			eduIds.add(std.getEdusyId());
		}
		return eduIds;
	}

}
