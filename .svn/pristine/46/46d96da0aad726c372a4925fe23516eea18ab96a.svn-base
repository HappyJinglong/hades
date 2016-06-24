package com.flyrish.hades.webapp.action.middlemaster;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.data.Json;
import org.nestframework.exporter.exception.ManagerException;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class MasterApriseDataAction extends BaseAction{
	//评价标识号id
	public String appraisalid;
	//科目
	public String subject;
	//栏目id
	public String evaluatetypeid;
	public String sectionName;
	public String flag;
	//学生id
	public String studentid;
	public String studentname;
	//评价内容
	public String apprasial;
	//评价日期
	public String signDate;
	public String cmis30id;
	public String discode;
	public String partid;
	public String termId;
	public String teacherId;
	public String classId;
	//json数据
	private JSONArray arraylist;
	//参数对象
	private AppraisalDto aDto = new AppraisalDto();
	public List<StudentDto>appriseInfos = new ArrayList<StudentDto>();
	//班主任管辖下班级信息
	public List<String>classInfos = new ArrayList<String>();
	public String date_content;
	public String levelNum;
	public String eduid;
	//选择学期
	public String zsTermId;
	//是否历史记录
	public String isHistory;
	public String proKey;
	//课程
	public List<SubjectDto>subjectDtos = new ArrayList<SubjectDto>();
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	@Spring
	private IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	private String isStartAppraiseCache;
	/**
	 * 初始化数据
	 * @param req
	 */
	@Before
	public void initData(HttpServletRequest req){
		isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		this.getLoginInfo(req);
		this.viewData(evaluatetypeid);
		List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
		if(null!=campus && campus.size()>0){
			cmis30id=userDto.getCmis30id();
			discode=userDto.getDiscode();
			teacherId=userDto.getTeachereduId();
			if(!NestUtil.isNotEmpty(isHistory)){
				termId=userDto.getTermId();//从session里获取学期id
				zsTermId = termId;
			}else{
				termId = zsTermId;
			}
			Date date_now=new Date();
		    SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
		    date_content=simple.format(date_now);
			aDto.setStudentid(studentid);
			aDto.setSemesterid(NestUtil.isEmpty(termId)?null:Integer.parseInt(termId));
			aDto.setApprasial(apprasial);
			aDto.setCmis30id(NestUtil.isEmpty(cmis30id)?null:Integer.parseInt(cmis30id));
			aDto.setDiscode(NestUtil.isEmpty(discode)?null:Integer.parseInt(discode));
			if("22".equals(evaluatetypeid)){
				aDto.setAppraseridentify(Integer.parseInt(Constant.MASTER_DICT));
			}else{
				aDto.setAppraseridentity(Constant.MASTER_DICT_CZ);
			}
			aDto.setAppraser((String)  req.getSession().getAttribute("teacherName"));
			aDto.setSigndate(StringToDate(signDate));
			aDto.setAppraisaltypeid(NestUtil.isEmpty(evaluatetypeid)?null:Integer.parseInt(evaluatetypeid));
			aDto.setAppraiserrid(NestUtil.isEmpty(teacherId)?null:Integer.parseInt(teacherId));
			aDto.setSubject(subject);
			aDto.setEduid(eduid);
			aDto.setApprasialid(proKey);
		}
	}
	/**
	 * 跳转至单个栏目所有学生评价情况
	 * @param req
	 * @return
	 */
	public Object showAppriseList(HttpServletRequest req){
		try {
			subjectDtos = masterAppriseExt.getCZSubjectInfos();
			@SuppressWarnings("unchecked")
			List<CampusDto> cdinfos = (List<CampusDto>) req.getSession().getAttribute("campus");
			//获取班主任管辖班级
			if(null!=cdinfos && cdinfos.size()>0){
				for(CampusDto info:cdinfos){
					classInfos.add(info.getLevelName()+"-"+info.getGradeName()+"-"+info.getClassName()+"@"+info.getClassId());
				}
			}
			if(StringUtils.isNotBlank(flag) && flag.equals("1")){//已经完成评价
				appriseInfos = this.getAppriseStudentInfos(flag,req);
				return "masterFinishAppriseList.jsp";
			}else if(StringUtils.isNotBlank(flag) && flag.equals("0")){//未完成评价
				appriseInfos = this.getAppriseStudentInfos(flag,req);
				return "masterNotFinishAppriseList.jsp";
			}
			return null;
		} catch (Exception e) {
			logger.error("showAppriseList(HttpServletRequest)", e);
			return "error.jsp";
		}
	}
	List<SchoolTreeDto> studentInfos;
	/**
	 * 获取相应评价信息
	 * @param flag
	 * @param req
	 * @return
	 */
	private List<StudentDto> getAppriseStudentInfos(String flag, HttpServletRequest req) {
		//班主任评语
		if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
			List<AppraisalDto> masterAppriseInfos = this.getAllMasterAppriseInfos(classId, termId, 1, false);
			if(null!=masterAppriseInfos && masterAppriseInfos.size()>0){
				return this.initApprise(masterAppriseInfos, flag);
			}
		}else if(null!=evaluatetypeid ){//思想道德评价 && evaluatetypeid.equals(Constant.TYPE_SX_TRPJ)
			if(Constant.WORKS_SUBJECT_APPRAISAL.equals(evaluatetypeid) && NestUtil.isEmpty(subject)){
				subject = subjectDtos.get(0).getSubjectid();
			}
			List<AppraisalDto>masterConmonAppriseInfos = new ArrayList<AppraisalDto>();
			if("1".equals(isStartAppraiseCache)){
				Map<String,Object>params = new HashMap<String, Object>();
				if(NestUtil.isNotEmpty(subject) && !"null".equals(subject)){
					params.put("subject", subject);
				}
				studentInfos = this.getStudentInfos(classId, "", req);
				List<String>sectionIds = new ArrayList<String>();
				sectionIds.add(evaluatetypeid);
				masterConmonAppriseInfos = masterAppriseExt.queryAppraiseFromRedis(studentInfos, termId, sectionIds, Constant.MASTER_DICT_CZ, teacherId, "junior", params);
				if(null!=masterConmonAppriseInfos && masterConmonAppriseInfos.size()>0){
					return this.listSort(this.initApprise(masterConmonAppriseInfos, flag));
				}
			}else{
				masterConmonAppriseInfos = this.getAllMasterAppriseInfos(classId, termId, 0, false);
			}
			if(null!=masterConmonAppriseInfos && masterConmonAppriseInfos.size()>0){
				return this.initApprise(masterConmonAppriseInfos, flag);
			}
		}
		return null;
	}
	private List<StudentDto> listSort(List<StudentDto> initApprise) {
		List<StudentDto> appriseInfo = new ArrayList<StudentDto>();//页面展示
		if(null!=studentInfos && studentInfos.size()>0 && null!=initApprise && initApprise.size()>0){
			for(SchoolTreeDto sDto : studentInfos){
				for(StudentDto aInfo : initApprise){
					if(sDto.getEdusyId().equals(aInfo.getEduid())){
						appriseInfo.add(aInfo);
						break;
					}
				}
			}
		}
		return appriseInfo;
	}
	/**
	 * 组装老师评价学生信息
	 * @param masterAppriseInfos
	 * @param finishApp
	 * @param notFinishApp
	 */
	private List<StudentDto> initApprise(List<AppraisalDto> appriseInfos,String flag) {
		//已经完成评价的数据
		List<StudentDto> finishApp = new ArrayList<StudentDto>();
		//还没有完成的评价数据
		List<StudentDto> notFinishApp = new ArrayList<StudentDto>();
		for(AppraisalDto dto:appriseInfos){
			//学生评价信息模型
			StudentDto studentDto = new StudentDto();
			if(null!=dto.getApprasialid()){//放入已经完成的容器中
				//如果该list还没有数据 说明还没有填如数据  将查询到的数据放入该list中
				if(finishApp.size()==0){
					studentDto.setStudentid(dto.getStudentid());
					studentDto.setName(dto.getStudentName());
					studentDto.setStatus("y");
					studentDto.setEduid(dto.getEduid());
					studentDto.setPhotoUrl(dto.getPhotoUrl());
					studentDto.getaInfos().add(dto);
					finishApp.add(studentDto);
				}else{//如果 有数据了 遍历该list   将已有的数据和查询出来的数据进行比对  
							//如果已经存在了该条数据  直接添加评价信息
							//否则 将数据重新装入容器
					boolean isAdd = false;
					for(StudentDto fDto:finishApp){
						if(dto.getStudentid().equals(fDto.getStudentid())){
							fDto.getaInfos().add(dto);
							isAdd=true;
							break;
						}
					}
					if(!isAdd){
						studentDto.setStudentid(dto.getStudentid());
						studentDto.setName(dto.getStudentName());
						studentDto.setPhotoUrl(dto.getPhotoUrl());
						studentDto.setEduid(dto.getEduid());
						studentDto.getaInfos().add(dto);
						studentDto.setStatus("y");
						finishApp.add(studentDto);
					}
				}
			}else{//放入未完成的容器中
				studentDto.setStudentid(dto.getStudentid());
				studentDto.setName(dto.getStudentName());
				studentDto.setPhotoUrl(dto.getPhotoUrl());
				studentDto.setEduid(dto.getEduid());
				studentDto.setStatus("n");
				notFinishApp.add(studentDto);
			}
		}
		if(flag.equals("1")){
			return finishApp;
		}else{
			return notFinishApp;
		}
	}
	/**
	 * 删除数据
	 * @param response
	 */
	public void doDeleteMasterAppraisal(HttpServletResponse response){
		try {
			List<AppraisalDto> appriseInfos=null;
			response.setContentType("text/html; charset=utf-8");
			if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
				masterAppriseExt.deleteMasterApprise(aDto);
				latestEvaluationRecordExt.deleteheadMasterRecordInCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, eduid, evaluatetypeid, proKey);
			}else if(null!=evaluatetypeid){
				if("1".equals(isStartAppraiseCache)){
					masterAppriseExt.deleteAppraiseCache(aDto);
				}else{
					masterAppriseExt.deleteCZMasterApprise(aDto);
				}
				latestEvaluationRecordExt.deleteheadMasterRecordInCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, eduid, evaluatetypeid, proKey);
			}
		}catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException ex) {}
			logger.error("doDeleteMasterAppraisal(HttpServletResponse)", e);
		}
	}
	/**
	 * 更新数据
	 * @param response
	 */
	public void doUpadateMasterAppraisal(HttpServletResponse response){
		try {
			List<AppraisalDto> appriseInfos =null;
			response.setContentType("text/html; charset=utf-8");
			if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
				masterAppriseExt.updateMasterApprise(aDto);
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, proKey, sectionName, studentname, eduid, new Date());
			}else if(null!=evaluatetypeid){
				if("1".equals(isStartAppraiseCache)){
					masterAppriseExt.updateAppraiseToCache(aDto);
				}else{
					masterAppriseExt.updateCZMasterApprise(aDto);
				}
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, proKey, sectionName, studentname, eduid, new Date());
			}
		}catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException ex) {}
			logger.error("doUpadateMasterAppraisal(HttpServletResponse)", e);
		}
	}
	/**
	 * 插入数据
	 * @param response
	 */
	public void doInsertMasterAppraisal(HttpServletResponse response){
		try {
			String insertId = "";
			response.setContentType("text/html; charset=utf-8");
			if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
				insertId = masterAppriseExt.insertMasterApprise(aDto);
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, insertId, sectionName, studentname, eduid, new Date());
			}else if(null!=evaluatetypeid){
				if("1".equals(isStartAppraiseCache)){
					insertId = masterAppriseExt.insertAppraisalToCache(aDto);
				}else{
					insertId = masterAppriseExt.insertCZMasterApprise(aDto);
				}
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, insertId, sectionName, studentname, eduid, new Date());
			}
				response.getWriter().write(insertId);
		}catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException ex) {}
			logger.error("doInsertMasterAppraisal(HttpServletResponse)", e);
		}catch (IOException e) {
			logger.error("doInsertMasterAppraisal(HttpServletResponse)", e);
		}
	}
	/**
	 * 时间转换
	 * @param d
	 * @return
	 */
	private  Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return new Date();
		}
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
			params.put("appraiserrid",teacherId );
			params.put("appraseridentify",Constant.MASTER_DICT_CZ);
			params.put("appraisaltypeid",evaluatetypeid);
			params.put("appraser",aDto.getAppraser());
			if(subject!=null && !subject.equals("null")){
				if(subject.equals("all")){
					subject=null;
				}
				params.put("subject",subject);
			}
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getCZMasterApprise(params);
		}else if(type==1){//班主任评语
			params.put("termId",termId );
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getMasterAppraisalInfos(params);
		}else if(type==2){//班主任课程评语
			params.put("semesterid",termId );
			/*params.put("sign",aDto.getAppraser());*/
			if(flag){
				params.put("studentid",studentid );
			}
			return masterAppriseExt.getLearnProcessAppraisalInfos(params);
		}
		return null;
	}
	private List<SchoolTreeDto> getStudentInfos(String classId, String gradeId,HttpServletRequest req) {
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("lid",classId );
		params.put("cmis30Id",cmis30id );
		params.put("discode",discode );
		return masterAppriseExt.getStudentInfo(params);
	}
	private void viewData(String sectionCode) {
		if(null!=sectionCode && sectionCode.equals(Constant.CHARGE_TEACHER_APPRAISAL)){
			sectionName=Constant.TYPE_BZRPY;
		}else if(null!=sectionCode && sectionCode.equals(Constant.MORALITY_TEACHER_APPRAISAL)){
			sectionName=Constant.TYPE_SXDD;
		}else if(null!=sectionCode && sectionCode.equals(Constant.WORKS_SUBJECT_APPRAISAL)){
			sectionName=Constant.TYPE_KCPY; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.COOPERATION_TEACHER_APPRAISAL)){
			sectionName=Constant.TYPE_HZYJL; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.PLAY_TEACHER_APPRAISAL)){
			sectionName=Constant.TYPE_YDYJK; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.AESTHETIC_TEACHER_APPRAISAL)){
			sectionName=Constant.TYPE_SMYBX; 
		}else if(null!=sectionCode && sectionCode.equals(Constant.INDIVIDUALITY_TEACHER_APPRASIAL)){
			sectionName=Constant.TYPE_GXYFZ; 
		}
	}
}
