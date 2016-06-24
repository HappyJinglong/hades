package com.flyrish.hades.webapp.action.middlemaster;

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
import com.flyrish.hades.service.ext.impl.MasterAppriseExtImpl;
import com.flyrish.hades.webapp.action.BaseAction;

public class QueryCommentAction extends BaseAction{
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
	
	public String gradeNum;
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
			int keyWordGradeNum = Integer.parseInt(maxClass.getGradeNum());
			for (CampusDto dto : campus) {
				init();
				classInfo = dto.getLevelName() + "-"+ dto.getGradeName() + "-"	+ dto.getClassName();
				classId=dto.getClassId();
			
				levelNum=dto.getGradeNum();
				
				
				if(!NestUtil.isNotEmpty(isHistory)){
					termId=loginInfo.getTermId();
					//高中综素 组装学期id
					zsTermId=termId;
				}else{
					termId = zsTermId;
					
					Integer cha = (maxGradeNum-Integer.parseInt(levelNum))*10;
					
					termId = String.valueOf(Integer.parseInt(termId)+cha);
					
					keyWordGradeNum = Integer.parseInt(gradeNum);
				}
				gradeId=dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId()+"@"+termId;
				
				if(Integer.parseInt(dto.getGradeNum())>keyWordGradeNum ||(Integer.parseInt(dto.getGradeNum())==keyWordGradeNum  && Integer.parseInt(loginInfo.getTermId().substring(4, 5))>=Integer.parseInt(termId.substring(4, 5)))){
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
		List<String> eduIds = screeningEduId(stuIfos);
		List<AppraiseBaseDto> appraiseBaseDtos=new ArrayList<AppraiseBaseDto>();
		//查询初中评价信息
		String isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		if("1".equals(isStartAppraiseCache)){
			appriseMasterAppriseExt.queryMiddleSchoolAppraisalInfoFromCache(sectionCode,eduIds,termId,appraiseBaseDtos);
		}else{
			appriseMasterAppriseExt.queryMiddleSchoolAppraisalInfo(sectionCode,eduIds,termId,cmis30id,discode,appraiseBaseDtos);
		}
		if(Constant.JUNIOR_TERN_END.equals(sectionCode)&& eduIds.size()>0){
			//初高中班主任评语查询
			appriseMasterAppriseExt.queryAssess(eduIds,termId,cmis30id,discode,appraiseBaseDtos);
		}
		screeningEvaluation(appraiseBaseDtos);
	}
	
	
	
	public void screeningEvaluation(List<AppraiseBaseDto> appraiseBaseDtos){
		if(null!=appraiseBaseDtos){
			for(AppraiseBaseDto appraiseBaseDto : appraiseBaseDtos){
				if("本人".equals(appraiseBaseDto.getWriteMan())){
					Appraisal3.add(appraiseBaseDto);  //自评
				}else if("同学".equals(appraiseBaseDto.getWriteMan())){
					Appraisal4.add(appraiseBaseDto);  //同学互评
				}else if("任课老师".equals(appraiseBaseDto.getWriteMan())
						||"老师".equals(appraiseBaseDto.getWriteMan())){
					Appraisal2.add(appraiseBaseDto);  //任课教师评价
				}else if("班主任".equals(appraiseBaseDto.getWriteMan())){
					Appraisal1.add(appraiseBaseDto);  //班主任评价
				}else if("家长".equals(appraiseBaseDto.getWriteMan())){
					Appraisal5.add(appraiseBaseDto);  //家长评价
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
		if(null!=sectionCode && sectionCode.equals("1")){
			sectionName="新学期伊始的我";
		}else if(null!=sectionCode && sectionCode.equals("2")){
			sectionName="学期结束时的我";
		}else if(null!=sectionCode && sectionCode.equals("3")){
			sectionName="思想道德"; 
		}else if(null!=sectionCode && sectionCode.equals("4")){
			sectionName="学业成就"; 
		}else if(null!=sectionCode && sectionCode.equals("5")){
			sectionName="合作与交流"; 
		}else if(null!=sectionCode && sectionCode.equals("6")){
			sectionName="运动与健康"; 
		}else if(null!=sectionCode && sectionCode.equals("7")){
			sectionName="审美与表现"; 
		}else if(null!=sectionCode && sectionCode.equals("8")){
			sectionName="综合实践活动"; 
		}else if(null!=sectionCode && sectionCode.equals("9")){
			sectionName="个性发展"; 
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
