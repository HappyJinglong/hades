package com.flyrish.hades.webapp.action.highschoolteacher;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.hibernate.mapping.Array;
import org.jfree.util.Log;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class StudentApprisedAction extends BaseAction{
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
	//班主任老师普通评语
	List<AppraisalDto>commonInfos;
	//班主任评语
	List<AppraisalDto>masterInfos;
/*	//班主任课程评语
	List<AppraisalDto>learnProcessInfos;*/
	//班主任id
	public String teacherId;
	//班主任评语
	public List<AppraisalDto> appraiselist1;
	//思想道德
	public List<AppraisalDto> appraiselist2;
	//课程评语
	public List<AppraisalDto> appraiselist3;
	//合作与交流
	public List<AppraisalDto> appraiselist4;
	//运动与健康列表
	public List<AppraisalDto> appraiselist5;
	//审美与表现列表
	public List<AppraisalDto> appraiselist6;
	//个性发展列表
	public List<AppraisalDto> appraiselist7;
	//单个学生栏目信息
	public List<StudentDto>sections = new ArrayList<StudentDto>();
	@Spring
	public IMasterAppriseExt masterAppriseExt;
	//评价人
	public  String appraiser;
	//教育id
	public String eduid;
	//当前管辖班级最高年级
	public String maxClassId;
	public String levelCode;
	private String isStartAppraiseCache;
	private List<SchoolTreeDto> singleStudentInfo = new ArrayList<SchoolTreeDto>();
	private List<String>sectionIds;
	@Before
	public void initData(HttpServletRequest req,HttpSession session){
		isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		this.getLoginInfo(req);
		cmis30id=userDto.getCmis30id();
		discode=userDto.getDiscode();
		teacherId=userDto.getTeachereduId();
		appraiser = (String) session.getAttribute("teacherName");
		levelCode = userDto.getLevelcode();
	}
	private void getStudentId(HttpServletRequest req) {
		List<SchoolTreeDto> studentInfos = this.getStudentInfos(classId, "", req);
		if(studentInfos!=null && studentInfos.size()>0){
			studentid = studentInfos.get(0).getId();
			name = studentInfos.get(0).getText();
			eduid = studentInfos.get(0).getEdusyId();
			singleStudentInfo.add(studentInfos.get(0));
		}
	}
	//课程
	public List<SubjectDto>subjectDtos = new ArrayList<SubjectDto>();
	//json 课程
	public String subjson;
	@DefaultAction
	public Object showSingleStudentApprise(HttpServletRequest req,HttpSession session){
		try {
			Date date_now=new Date();
			SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			date_content=simple.format(date_now);
			List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
			if(null==campus){
				Map<String,Object>params = new HashMap<String, Object>();
				String cmis30id=userDto.getCmis30id();
				String discode=userDto.getDiscode();
				String teacherId=userDto.getTeacherid();
				String levelcode=userDto.getLevelcode();
				String campusid=userDto.getCampuseId();
				//查询参数
				params.put("cmis30id", cmis30id);
				params.put("discode", discode);
				params.put("techerid", teacherId);
				params.put("levelcode", levelcode);
				params.put("campusid", campusid);
				campus=masterAppriseExt.getTeacherClassInfos(params);
			}
			if(null!=campus && campus.size()>0){
				if(NestUtil.isEmpty(studentid)){
					if(NestUtil.isEmpty(ids)&&NestUtil.isEmpty(idName)){
						//获取第一个学生的id
						CampusDto cDto = campus.get(0);
						levelNum = cDto.getGradeNum();
						classId = cDto.getClassId();
						this.initTermId(req);
						this.getStudentId(req);
					}else{
						classId = ids.split("@")[2];
						initTerm(req, campus);
						this.getStudentId(req);
					}
				}else{
					initTerm(req, campus);
				}
				this.getSubjects();
			//班级学生数量
			//获取单个学生评语信息
			List<AppraisalDto> conmonAppriseInfo = new ArrayList<AppraisalDto>();
			List<AppraisalDto> learnProcessInfo = new ArrayList<AppraisalDto>();
			if("1".equals(isStartAppraiseCache)){
				if(null!=singleStudentInfo && singleStudentInfo.size()!=1){
					stuIfos = this.getStudentInfos(classId,gradeId,req);
					for(SchoolTreeDto stDto : stuIfos){
						if(eduid.equals(stDto.getEdusyId())){
							singleStudentInfo.add(stDto);
							break;
						}
					} 
				}
				this.initSectionCodes();
				conmonAppriseInfo =masterAppriseExt.queryAppraiseFromRedis(singleStudentInfo, zsTermId, sectionIds, Constant.TEACHER_DICT, teacherId, "high", null);
				learnProcessInfo = masterAppriseExt.queryProcessAppraiseFromRedis(singleStudentInfo, zsTermId, Constant.TYPE_KE_CHENG_PINGYU, Constant.TEACHER_DICT, teacherId, "", null);
			}else{
				conmonAppriseInfo = this.getAllMasterAppriseInfos(classId, zsTermId, 0, true);
				learnProcessInfo = this.getAllMasterAppriseInfos(classId, zsTermId, 2, true);
			}
			if(null != conmonAppriseInfo && conmonAppriseInfo.size()>0){
				this.tmpFillList(conmonAppriseInfo, learnProcessInfo);
				fillSectionInfos(Constant.TYPE_SX_TRPJ,Constant.TYPE_SXDD,appraiselist1);
				fillSectionInfos(Constant.TYPE_KE_CHENG_PINGYU,Constant.TYPE_KCPY,appraiselist2);
				fillSectionInfos(Constant.TYPE_XY,Constant.TYPE_XYCJ,appraiselist3);
				fillSectionInfos(Constant.TYPE_HZ_TRPJ,Constant.TYPE_HZYJL,appraiselist4);
				fillSectionInfos(Constant.TYPE_YDJK_TRPJ,Constant.TYPE_YDYJK,appraiselist5);
				fillSectionInfos(Constant.TYPE_SMYBX_TRPJ,Constant.TYPE_SMYBX,appraiselist6);
				fillSectionInfos(Constant.TYPE_GXFZ_TRPJ,Constant.TYPE_GXYFZ,appraiselist7);
			}
			}
			return "masterApprise.jsp";
		} catch (Exception e) {
			logger.error("showSingleStudentApprise(HttpServletRequest,HttpSession)", e);
			return "error.jsp";
		}
	}
	private void initSectionCodes() {
		sectionIds = new ArrayList<String>();
		sectionIds.add(Constant.TYPE_SX_TRPJ);
		sectionIds.add(Constant.TYPE_XY);
		sectionIds.add(Constant.TYPE_HZ_TRPJ);
		sectionIds.add(Constant.TYPE_YDJK_TRPJ);
		sectionIds.add(Constant.TYPE_SMYBX_TRPJ);
		sectionIds.add(Constant.TYPE_GXFZ_TRPJ);
	}
	private void getSubjects() {
		Map<String,Object>params = new HashMap<String, Object>();
		params.put("teacherid", userDto.getTeacherid());
		params.put("cmis30id", userDto.getCmis30id());
		params.put("classId", classId);
		subjectDtos = masterAppriseExt.getGZSubjectInfos(params);
		subjson=JSONArray.fromObject(subjectDtos).toString();
	}
	private void initTerm(HttpServletRequest req, List<CampusDto> campus) {
		for(CampusDto dto:campus){
			if(dto.getClassId().equals(classId)){
				levelNum = dto.getGradeNum();
				this.initTermId(req);
				break;
			}
		}
	}
	//填充数据
	private void tmpFillList(List<AppraisalDto> conmonAppriseInfo,List<AppraisalDto> learnProcessInfo) {
		appraiselist1=new ArrayList<AppraisalDto>();
		appraiselist2=new ArrayList<AppraisalDto>();
		appraiselist3=new ArrayList<AppraisalDto>();
		appraiselist4=new ArrayList<AppraisalDto>();
		appraiselist5=new ArrayList<AppraisalDto>();
		appraiselist6=new ArrayList<AppraisalDto>();
		appraiselist7=new ArrayList<AppraisalDto>();		
		if(learnProcessInfo!=null&&learnProcessInfo.size()>0){
			for(AppraisalDto dto:learnProcessInfo){
				if(dto.getApprasialid()!=null){
					appraiselist2.add(dto);
				}
			}
		}
		for (AppraisalDto dto : conmonAppriseInfo) {
			Integer tmpType=dto.getAppraisaltypeid();
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_SX_TRPJ)){
				appraiselist1.add(dto);
			}
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_XY)){
				appraiselist3.add(dto);
			}
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_HZ_TRPJ)){
				appraiselist4.add(dto);
			}
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_YDJK_TRPJ)){
				appraiselist5.add(dto);
			}
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_SMYBX_TRPJ)){
				appraiselist6.add(dto);
			}
			if(null!=tmpType && tmpType.toString().equals(Constant.TYPE_GXFZ_TRPJ)){
				appraiselist7.add(dto);
			}
		}
	}
	//获取单个学生各栏目信息
	private void fillSectionInfos(String sectionid,String sectionname,List<AppraisalDto> apList ) {
		StudentDto sd = new StudentDto();
		sd.setStudentid(studentid);
		sd.setName(name);
		sd.setEduid(eduid);
		sd.setSectionId(sectionid);
		sd.setSectionName(sectionname);
		sd.setaInfos(apList);
		sections.add(sd);
	}
	/**
	 * 统计班主任评价每个学生的情况
	 * @param req
	 * @return
	 */
	public Object showAppriseCount(HttpServletRequest req,HttpSession session){
		try {
			//初始化数据
			this.initDatas(req);
			if(null!=stuIfos && stuIfos.size()>0){
				for(SchoolTreeDto dto:stuIfos){
					//学生统计数量
					List<String>appriseCounts=new ArrayList<String>();
					//获取某个学生信息   遍历评价表  获取栏目评价次数
					String tmpId = dto.getId();
					String tmpName=dto.getText();
					int sxddCount=0;//班主任思想道德评价计数 3020
					int hzjlCount=0;//班主任合作交流评价计数 4020
					int ydyjkCount=0;	//运动与健康 5020
					int smybxCount=0;//审美与表现 6020
					int gxyfzCount=0;//个性与发展 7030
					//班主任学业成就8040
					int masterProcess=0;
					//班主任普通评语
					for(AppraisalDto aDto:commonInfos){
						if(tmpId.equals(aDto.getStudentid())){
							if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_SX_TRPJ)){//3020
								sxddCount++;
							}else if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_HZ_TRPJ)){//4020
								hzjlCount++;
							}else if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_YDJK_TRPJ)){//5020
								ydyjkCount++;
							}else if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_SMYBX_TRPJ)){//6020
								smybxCount++;
							}else if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_GXFZ_TRPJ)){//7030
								gxyfzCount++;
							}else if(null!=aDto.getAppraisaltypeid()&&aDto.getAppraisaltypeid().toString().equals(Constant.TYPE_XY)){//8040
								masterProcess++;
							}
						}
					}
					//班主任评语
					int masterCount=0;
					for(AppraisalDto info:masterInfos){
						if(tmpId.equals(info.getStudentid())&&null!=info.getApprasialid()){
							masterCount++;
						}
					}
					//将评价统计信息放入list集合中
					appriseCounts.add(tmpName);
					appriseCounts.add(String.valueOf(masterCount));
					appriseCounts.add(String.valueOf(sxddCount));
					appriseCounts.add(String.valueOf(masterProcess));
					appriseCounts.add(String.valueOf(hzjlCount));
					appriseCounts.add(String.valueOf(ydyjkCount));
					appriseCounts.add(String.valueOf(smybxCount));
					appriseCounts.add(String.valueOf(gxyfzCount));
					masterAppriseStudentInfo.put(tmpId, appriseCounts);
				}
			}
			//其它评语
			return "appriseCountN.jsp";
		} catch (Exception e) {
			logger.error("showAppriseCount(HttpServletRequest,HttpSession)", e);
			return "error.jsp";
		}
	}
	//任课老师管辖下班级信息
	public List<String>classInfos = new ArrayList<String>();
	/**
	 * 统计老师已经评价班级学生数量
	 * @param req
	 * @return
	 */
	public Object showStudentCount(HttpServletRequest req,HttpSession session){
		try {
			List<CampusDto> cdinfos = (List<CampusDto>) req.getSession().getAttribute("campus");
			//获取班主任管辖班级
			if(null!=cdinfos && cdinfos.size()>0){
				for(CampusDto info:cdinfos){
					classInfos.add(info.getLevelName()+"-"+info.getGradeName()+"-"+info.getClassName()+"@"+info.getClassId());
				}
			}
			if(Constant.TYPE_KE_CHENG_PINGYU.equals(sectionCode)){
				this.getClassCourseAppriseInfo(sectionCode,req);
				return "studentCourseCount.jsp";
			}else{
				this.getClassAppriseInfo(sectionCode,req);
				return "studentCount.jsp";
			}
		} catch (Exception e) {
			logger.error("showStudentCount(HttpServletRequest,HttpSession)", e);
			return "error.jsp";
		}
	}
	public String isChooseClass;
	//统计班级单个科目
	public List<List<String>>classSubjectInfo = new ArrayList<List<String>>();
	private void getClassCourseAppriseInfo(String sectionCode,HttpServletRequest req) {
		//页面栏目展示
		this.viewData(sectionCode);
		//统计班级总人数  已经完成   未完成学生
		@SuppressWarnings("unchecked")
		List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
		if(null==campus){
			Map<String,Object>params = new HashMap<String, Object>();
			String cmis30id=userDto.getCmis30id();
			String discode=userDto.getDiscode();
			String teacherId=userDto.getTeacherid();
			String levelcode=userDto.getLevelcode();
			String campusid=userDto.getCampuseId();
			//查询参数
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("techerid", teacherId);
			params.put("levelcode", levelcode);
			params.put("campusid", campusid);
			campus=masterAppriseExt.getTeacherClassInfos(params);
		}
		if(null!=campus && campus.size()>0){
			CampusDto dto = null;//campus.get(0);
			if(null!=isChooseClass){
				for (CampusDto d: campus) {
					if(d.getClassId().equals(classId)){
						dto=d;
						break;
					}
				}
			}else{
				dto = campus.get(0);
			}
				classInfo = dto.getLevelName() + "-"+ dto.getGradeName() + "-"	+ dto.getClassName();
				classId=dto.getClassId();
				gradeId=dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId();
				levelNum=dto.getGradeNum();
				initTermId(req);
				stuIfos = this.getStudentInfos(classId,gradeId,req);
				//某个班级总人数
				List<String>classCount = new ArrayList<String>();
				classCount.add(classInfo);
				int studentCount=0;
				if(null!=stuIfos && stuIfos.size()>0){
					studentCount=stuIfos.size();
					classCount.add(String.valueOf(studentCount));
					//班主任老师普通评语
					if("1".equals(isStartAppraiseCache)){
						commonInfos= masterAppriseExt.queryProcessAppraiseFromRedis(stuIfos, zsTermId, Constant.TYPE_KE_CHENG_PINGYU, Constant.TEACHER_DICT, teacherId, "", null);
					}else{
						commonInfos=this.getAllMasterAppriseInfos(classId, zsTermId, 2,false);
					}
					//获取所有科目
					this.getSubjects();
					if(null!=commonInfos && commonInfos.size()>0){
						Set<String>tmpCount = new HashSet<String>();
						for (AppraisalDto infos : commonInfos) {
							String apprasialid = infos.getApprasialid();
							String tempSubject = infos.getSubject();
							if(null!=subjectDtos && subjectDtos.size()>0){
								for(SubjectDto subDto:subjectDtos){
									if(null!=apprasialid && null!=tempSubject && tempSubject.equals(subDto.getSubjectName())){
										tmpCount.add(infos.getStudentid());
									}
								}
							}
						}
						classCount.add(String.valueOf(tmpCount.size()));
						classCount.add(String.valueOf(Math.abs(tmpCount.size()-studentCount)));
					}
					masterAppriseClassInfo.put(gradeId, classCount);
					if(null!=subjectDtos && subjectDtos.size()>0){
						//获取单个科目
						for(SubjectDto subDto:subjectDtos){
							//某个班级某个课程统计
							List<String>courseCount = new ArrayList<String>();
							Set<String>courseTempCount = new HashSet<String>();
							String subjectName = subDto.getSubjectName();
							//遍历获取与当前科目相同的科目评价信息
							for (AppraisalDto infos : commonInfos) {
								if(subjectName.equals(infos.getSubject())){
									courseTempCount.add(infos.getStudentid()+subDto.getSubjectName());
								}
							}
							//封装统计课程数据
							courseCount.add(subDto.getSubjectName()+"@"+subjectName);
							courseCount.add(String.valueOf(studentCount));
							courseCount.add(String.valueOf(courseTempCount.size()));
							courseCount.add(String.valueOf(Math.abs(courseTempCount.size()-studentCount)));
							/*courseCount.add(subDto.getSubjectid());*/
							//将单个统计数据封装
							classSubjectInfo.add(courseCount);
						}
					}
	     	}
		}
	}
	/**
	 * 填充页面展示数据模型
	 * @param sectionCode
	 * @param req
	 * @return
	 */
	private void getClassAppriseInfo(String sectionCode,HttpServletRequest req) {
		//页面栏目展示
		this.viewData(sectionCode);
		//统计班级总人数  已经完成   未完成学生
		@SuppressWarnings("unchecked")
		List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
		if(null==campus){
			Map<String,Object>params = new HashMap<String, Object>();
			String cmis30id=userDto.getCmis30id();
			String discode=userDto.getDiscode();
			String teacherId=userDto.getTeacherid();
			String levelcode=userDto.getLevelcode();
			String campusid=userDto.getCampuseId();
			//查询参数
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("techerid", teacherId);
			params.put("levelcode", levelcode);
			params.put("campusid", campusid);
			campus=masterAppriseExt.getTeacherClassInfos(params);
		}
		if(null!=campus && campus.size()>0){
			//获取最后一个班级--年级号最大的
			CampusDto maxClass = campus.get(campus.size()-1);
			//获取最大年级号
			String maxGradeNum = maxClass.getGradeNum();
			//获取最高年级班级id
			maxClassId= maxClass.getClassId();
			//获取当前学期ID
			String currentTermId = userDto.getTermId();
			//获取termID最后一位
			String lastNum = currentTermId.substring(4, 5);
			//如果不是历史学期查询 首先选择年级最高的
			if(!NestUtil.isNotEmpty(isHistory)){
				iterateClassInfo(sectionCode, req, campus, maxGradeNum+lastNum,lastNum);
			}else{
				String historyGradeNum = zsTermId;//.substring(0,1);
				iterateClassInfo(sectionCode, req, campus, historyGradeNum,lastNum);
			}
		}
	}
	//遍历班级信息
	private void iterateClassInfo(String sectionCode, HttpServletRequest req,List<CampusDto> campus, String maxGradeNum,String lastNum) {
		for (CampusDto dto : campus) {
			if(Integer.parseInt(dto.getGradeNum()+lastNum)>=Integer.parseInt(maxGradeNum)){
				this.classCount(sectionCode, req, dto);
			}
		}
	}
	private void classCount(String sectionCode, HttpServletRequest req,	CampusDto dto) {
		classInfo = dto.getLevelName() + "-"+ dto.getGradeName() + "-"	+ dto.getClassName();
		classId=dto.getClassId();
		gradeId=dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId();
		levelNum=dto.getGradeNum();
		this.initTermId(req);
		//班级学生数量
		stuIfos = this.getStudentInfos(classId,gradeId,req);
		//某个班级总人数
		List<String>classCount = new ArrayList<String>();
		classCount.add(classInfo+"@"+classId+"@"+zsTermId);
		int studentCount=0;
		if(null!=stuIfos && stuIfos.size()>0){
			studentCount=stuIfos.size();
			classCount.add(String.valueOf(studentCount));
			//班主任评语
			if(null!=sectionCode && sectionCode.equals(Constant.TYPE_KE_CHENG_PINGYU)){
				//课程评语
				if("1".equals(isStartAppraiseCache)){
					masterInfos = masterAppriseExt.queryProcessAppraiseFromRedis(stuIfos, zsTermId, Constant.TYPE_KE_CHENG_PINGYU, Constant.TEACHER_DICT, teacherId, "", null);
				}else{
					masterInfos=this.getAllMasterAppriseInfos(classId, zsTermId, 2,false);
				}
				this.fillListCount(classCount, studentCount,masterInfos);
			}else{
				//班主任老师普通评语
				if("1".equals(isStartAppraiseCache)){
					this.initSectionCodes();
					commonInfos = masterAppriseExt.queryAppraiseFromRedis(stuIfos, zsTermId, sectionIds, Constant.TEACHER_DICT, teacherId, "high", null);
				}else{
					commonInfos=this.getAllMasterAppriseInfos(classId, zsTermId, 0,false);
				}
				if(null!=commonInfos && commonInfos.size()>0){
					Set<String>tmpCount = new HashSet<String>();
					for (AppraisalDto infos : commonInfos) {
						Integer typeid = infos.getAppraisaltypeid();
						if(null!=typeid){
							if(null!=sectionCode && sectionCode.equals(String.valueOf(typeid))){
								tmpCount.add(infos.getStudentid());
							}
						}
					}
					classCount.add(String.valueOf(tmpCount.size()));
					classCount.add(String.valueOf(Math.abs(tmpCount.size()-studentCount)));
				}
			}
		}else{
			classCount.add("0");
			classCount.add("0");
			classCount.add("0");
		}
		masterAppriseClassInfo.put(gradeId, classCount);
	}
	/**
	 * 填充统计不同栏目评价数量
	 * @param classCount 装载数据list
	 * @param studentCount 班级学生数量
	 * @param infos 班主任评价信息
	 */
	private void fillListCount(List<String> classCount, int studentCount,List<AppraisalDto> infos) {
		if(null!=infos && infos.size()>0){
			Set<String>tmpCount = new HashSet<String>();
			for (AppraisalDto info : infos) {
				if(info.getApprasialid()!=null){
					tmpCount.add(info.getStudentid());
				}
			}
			classCount.add(String.valueOf(tmpCount.size()));
			classCount.add(String.valueOf(Math.abs(tmpCount.size()-studentCount)));
		}
	}
	/**
	 * 页面展示栏目
	 * @param sectionCode
	 */
	private void viewData(String sectionCode) {
		if(null!=sectionCode && sectionCode.equals(Constant.TYPE_KE_CHENG_PINGYU)){
			sectionName=Constant.TYPE_KCPY;
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_SX_TRPJ)){
			sectionName=Constant.TYPE_SXDD;
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_XY)){
			sectionName=Constant.TYPE_XYCJ; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_HZ_TRPJ)){
			sectionName=Constant.TYPE_HZYJL; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_YDJK_TRPJ)){
			sectionName=Constant.TYPE_YDYJK; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_SMYBX_TRPJ)){
			sectionName=Constant.TYPE_SMYBX; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.TYPE_GXFZ_TRPJ)){
			sectionName=Constant.TYPE_GXYFZ; 
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
	 * 获取班主任的各种评语
	 * @param classId
	 * @param termId
	 * @param type 0：普通评语  1：班主任评语  2：课程评语 
	 * @param flag 是否查询单个学生信息
	 * @return
	 */
	private List<AppraisalDto> getAllMasterAppriseInfos(String classId,String termId,int type,boolean flag){
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("classid",classId );
		params.put("cmis30id",cmis30id );
		params.put("discode",discode );
		if(type==0){//普通评价
			params.put("semesterid",termId );
			params.put("appraiserrid",teacherId);
			params.put("appraseridentify",Constant.TEACHER_DICT);
			params.put("appraser",appraiser);
			if(sectionCode!=null){
				params.put("appraisaltypeid",sectionCode);
			}
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getAllCommonAppraisalInfos(params);
		}else if(type==1){//班主任评语
			params.put("termId",termId );
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getMasterAppraisalInfos(params);
		}else if(type==2){//班主任课程评语
			params.put("semesterid",termId );
			params.put("appraiserrid",teacherId);
			/*params.put("sign",appraiser);*/
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getLearnProcessAppraisalInfos(params);
		}
		return null;
	}
	/**
	 * 获取数据
	 * @param req
	 */
	private void initDatas(HttpServletRequest req){
		@SuppressWarnings("unchecked")
		List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
		if(null==campus){
			Map<String,Object>params = new HashMap<String, Object>();
			String cmis30id=userDto.getCmis30id();
			String discode=userDto.getDiscode();
			String teacherId=userDto.getTeacherid();
			String levelcode=userDto.getLevelcode();
			String campusid=userDto.getCampuseId();
			//查询参数
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("techerid", teacherId);
			params.put("levelcode", levelcode);
			params.put("campusid", campusid);
			campus=masterAppriseExt.getTeacherClassInfos(params);
		}
		if(null!=campus && campus.size()>0){
			//点击查询具体某个班级数据
			if (StringUtils.isNotBlank(ids) && StringUtils.isNotBlank(idName)) {
				for(CampusDto dto : campus){
					classInfo = dto.getLevelName() + "-"+ dto.getGradeName() + "-"	+ dto.getClassName();
					if(classInfo.equals(idName) && (dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId()).equals(ids)){
						classId=dto.getClassId();
						gradeId=dto.getGradeId();
						levelNum=dto.getGradeNum();
						break;
					}
				}
			} else {
				//默认加载第一个班级数据
				CampusDto campusDto = campus.get(0);
				classInfo = campusDto.getLevelName() + "-"+ campusDto.getGradeName() + "-"	+ campusDto.getClassName();
				classId=campusDto.getClassId();
				gradeId=campusDto.getGradeId();
				levelNum=campusDto.getGradeNum();
			}
			this.initTermId(req);
			//班级学生数量
			stuIfos = this.getStudentInfos(classId,gradeId,req);
			//班主任老师普通评语
			commonInfos=this.getAllMasterAppriseInfos(classId, zsTermId, 0,false);
			//班主任评语
			masterInfos=this.getAllMasterAppriseInfos(classId, termId, 1,false);
		}
	}
	//转换termId
	private void initTermId(HttpServletRequest req) {
		if(!NestUtil.isNotEmpty(isHistory)){
			termId=userDto.getTermId();//从session里获取学期id
			//高中综素 组装学期id
			zsTermId=levelNum+termId.substring(4, 5);
		}else{
			/*String gradeNum = zsTermId.substring(0,1);
			String num = zsTermId.substring(1,2);*/
			termId = zsTermId;// masterAppriseExt.getHSHistoryTermId(classId, gradeNum, num);
		}
	}
	
	public void DefaultEduId(HttpServletRequest req,HttpSession session,HttpServletResponse rep){
			//初始化数据
			this.initDatas(req);
			if(stuIfos.size()>0 && null != stuIfos){
				try {
					String name = URLEncoder.encode( URLEncoder.encode(stuIfos.get(0).getText(),"UTF-8"),"UTF-8");
					req.getRequestDispatcher("/appraise/QueryAppraiseAction.a?edu_id="+stuIfos.get(0).getEdusyId()+"&&studentName="+name+"&&flag=1").forward(req, rep);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		
		}
}
