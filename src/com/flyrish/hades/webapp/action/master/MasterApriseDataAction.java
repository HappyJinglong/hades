package com.flyrish.hades.webapp.action.master;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.mapping.Array;
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
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class MasterApriseDataAction extends BaseAction{
	//评价标识号id
	public String appraisalid;
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
	public String zsTermId;
	public String teacherId;
	public String classId;
	public String eduid;
	//json数据
	private JSONArray arraylist;
	//参数对象
	private AppraisalDto aDto = new AppraisalDto();
	public List<StudentDto>appriseInfos = new ArrayList<StudentDto>();
	
	//班主任管辖下班级信息
	public List<String>classInfos = new ArrayList<String>();
	public String date_content;
	public String levelNum;
	public String isHistory;
	public String proKey;
	private String isStartAppraiseCache;
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	/**
	 * 初始化数据
	 * @param req
	 */
	@Before
	public Object initData(HttpServletRequest req,HttpSession session){
		try {
			isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
			this.getLoginInfo(req);
			this.viewData(evaluatetypeid);
			@SuppressWarnings("unchecked")
			List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
			if(null!=campus && campus.size()>0){
				for(CampusDto dto : campus){
					if(dto.getClassId().equals(classId)){
						levelNum = dto.getGradeNum();
						break;
					}
				}
				cmis30id=userDto.getCmis30id();
				discode=userDto.getDiscode();
				teacherId=userDto.getTeachereduId();
			    Date date_now=new Date();
			    SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			    date_content=simple.format(date_now);
				 //初始化参数
				aDto.setStudentid(studentid);
				/*aDto.setSemesterid(NestUtil.isEmpty(termId)?null:Integer.parseInt(termId));*/
				aDto.setApprasial(apprasial);
				aDto.setCmis30id(NestUtil.isEmpty(cmis30id)?null:Integer.parseInt(cmis30id));
				aDto.setDiscode(NestUtil.isEmpty(discode)?null:Integer.parseInt(discode));
				aDto.setAppraseridentify(Integer.parseInt(Constant.MASTER_DICT));
				aDto.setAppraser((String)  req.getSession().getAttribute("teacherName"));
				aDto.setSigndate(StringToDate(signDate));
				aDto.setAppraisaltypeid(NestUtil.isEmpty(evaluatetypeid)?null:Integer.parseInt(evaluatetypeid));
				aDto.setAppraiserrid(NestUtil.isEmpty(teacherId)?null:Integer.parseInt(teacherId));
				aDto.setEduid(eduid);
				aDto.setApprasialid(proKey);
			}
		} catch (NumberFormatException e) {
			logger.error("initData(HttpServletRequest,HttpSession)",e);
			return "error.jsp";
		}
		return null;
	}
	//学期Id
	private void initTermId(HttpServletRequest req) {
		if(!NestUtil.isNotEmpty(isHistory)){
			termId=userDto.getTermId();//从session里获取学期id
			//高中综素 组装学期id
			zsTermId=levelNum+termId.substring(4, 5);
		}else{
			String gradeNum = zsTermId.substring(0,1);
			String num = zsTermId.substring(1,2);
			termId = masterAppriseExt.getHSHistoryTermId(classId, gradeNum, num);
		}
	}
	/**
	 * 异步加载数据
	 * @param req
	 * @return
	 */
	@Json
	public Object queryData(HttpServletRequest req){
		@SuppressWarnings("unchecked")
		List<StudentDto>stuIfos = (List<StudentDto>) req.getSession().getAttribute("appriseInfos");
		if(null!=stuIfos && stuIfos.size()>0){
			List<String>listStr=new ArrayList<String>();
			for (StudentDto stI : stuIfos) {
				Map<String,Object>maps=new HashMap<String,Object>();
				maps.put("id",stI.getEduid());
				maps.put("name",stI.getName()+"_"+stI.getEduid());
				maps.put("status", "y");
				listStr.add(Utility.createJsonStr(maps));
			}
			return JSONObject.fromObject(Utility.createJsonStr(listStr));
		}
		return null;
	}
	
	public Object getSingleStudentAppraiseInfo(){
		
		return null;
	}
	/**
	 * 跳转至单个栏目所有学生评价情况
	 * @param req
	 * @return
	 */
	public Object showAppriseList(HttpServletRequest req){
		try {
			this.initTermId(req);
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
			return "masterFinishAppriseList.jsp";
		} catch (Exception e) {
			logger.error("showAppriseList(HttpServletRequest)", e);
			return "error.jsp";
		}
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
	List<SchoolTreeDto> studentInfos;
	/**
	 * 获取相应评价信息
	 * @param flag
	 * @param req
	 * @return
	 */
	private List<StudentDto> getAppriseStudentInfos(String flag, HttpServletRequest req) {
		//班主任评语
		if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.TYPE_END_BZRPY)){
			List<AppraisalDto> masterAppriseInfos = this.getAllMasterAppriseInfos(classId, termId, 1, false);
			if(null!=masterAppriseInfos && masterAppriseInfos.size()>0){
				return this.initApprise(masterAppriseInfos, flag);
			}
		}else if(null!=evaluatetypeid ){//思想道德评价 && evaluatetypeid.equals(Constant.TYPE_SX_TRPJ)
			List<AppraisalDto>masterConmonAppriseInfos = new ArrayList<AppraisalDto>();
			if("1".equals(isStartAppraiseCache)){
				studentInfos = this.getStudentInfos(classId, "", req);
				List<String>sectionIds = new ArrayList<String>();
				sectionIds.add(evaluatetypeid);
				masterConmonAppriseInfos = masterAppriseExt.queryAppraiseFromRedis(studentInfos, zsTermId, sectionIds, Constant.MASTER_DICT, teacherId, "high", null);
				if(null!=masterConmonAppriseInfos && masterConmonAppriseInfos.size()>0){
					return this.listSort(this.initApprise(masterConmonAppriseInfos, flag));
				}
			}else{
				masterConmonAppriseInfos = this.getAllMasterAppriseInfos(classId, zsTermId, 0, false);
			}
			if(null!=masterConmonAppriseInfos && masterConmonAppriseInfos.size()>0){
				return this.initApprise(masterConmonAppriseInfos, flag);
			}
		}
		return null;
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
					studentDto.setStatus("y");
					studentDto.setStudentid(dto.getStudentid());
					studentDto.setName(dto.getStudentName());
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
//							finishApp.add(fDto);
							isAdd=true;
							break;
						}
					}
					if(!isAdd){
						studentDto.setStatus("y");
						studentDto.setStudentid(dto.getStudentid());
						studentDto.setName(dto.getStudentName());
						studentDto.setEduid(dto.getEduid());
						studentDto.setPhotoUrl(dto.getPhotoUrl());
						studentDto.getaInfos().add(dto);
						finishApp.add(studentDto);
					}
				}
			}else{//放入未完成的容器中
				studentDto.setStatus("n");
				studentDto.setStudentid(dto.getStudentid());
				studentDto.setName(dto.getStudentName());
				studentDto.setPhotoUrl(dto.getPhotoUrl());
				studentDto.setEduid(dto.getEduid());
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
	public void doDeleteMasterAppraisal(HttpServletResponse response,HttpServletRequest req) {
		try {
			List<AppraisalDto> appriseInfos = null;
			response.setContentType("text/html; charset=utf-8");
			if (null != evaluatetypeid&& evaluatetypeid.equals(Constant.TYPE_END_BZRPY)) {
				masterAppriseExt.deleteMasterApprise(aDto);
				latestEvaluationRecordExt.deleteheadMasterRecordInCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, eduid, evaluatetypeid, proKey);
			} else if (null != evaluatetypeid) {
				if("1".equals(isStartAppraiseCache)){
					this.initTermId(req);
					aDto.setSemesterid(Integer.parseInt(zsTermId));
					masterAppriseExt.deleteAppraiseCacheHigh(aDto);
				}else{
					masterAppriseExt.deleteCommonMasterApprise(aDto);	
				}
				latestEvaluationRecordExt.deleteheadMasterRecordInCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, eduid, evaluatetypeid, proKey);
			}
		} catch (ManagerException e) {
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
	public void doUpadateMasterAppraisal(HttpServletResponse response,HttpServletRequest req){
		try {
			List<AppraisalDto> appriseInfos =null;
			response.setContentType("text/html; charset=utf-8");
			if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.TYPE_END_BZRPY)){
				masterAppriseExt.updateMasterApprise(aDto);
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, proKey, sectionName, studentname, eduid, new Date());
			}else if(null!=evaluatetypeid){
				if("1".equals(isStartAppraiseCache)){
					this.initTermId(req);
					aDto.setSemesterid(Integer.parseInt(zsTermId));
					masterAppriseExt.updateAppraiseToCacheHigh(aDto);
				}else{
					masterAppriseExt.updateCommonMasterApprise(aDto);
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
	public void doInsertMasterAppraisal(HttpServletResponse response,HttpServletRequest req){
		try {
			this.initTermId(req);
			String insertId ="";
			response.setContentType("text/html; charset=utf-8");
			if(null!=evaluatetypeid && evaluatetypeid.equals(Constant.TYPE_END_BZRPY)){
				aDto.setSemesterid(NestUtil.isEmpty(termId)?null:Integer.parseInt(termId));
				insertId = masterAppriseExt.insertMasterApprise(aDto);
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, insertId, sectionName, studentname, eduid, new Date());
			}else if(null!=evaluatetypeid){
				aDto.setSemesterid(Integer.parseInt(zsTermId));
				if("1".equals(isStartAppraiseCache)){
					insertId=masterAppriseExt.insertAppraisalToCacheHigh(aDto);
				}else{
					insertId=masterAppriseExt.insertCommonMasterApprise(aDto);
				}
				latestEvaluationRecordExt.setheadMasterRecordToCache(userDto.getCampuseId(), userDto.getLevelcode(), teacherId, evaluatetypeid, insertId, sectionName, studentname, eduid, new Date());
			}
			response.getWriter().write(insertId);
		} catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException ex) {}
			logger.error("doInsertMasterAppraisal(HttpServletResponse)", e);
		}catch(IOException e){
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
			params.put("appraseridentify",Constant.MASTER_DICT);
			params.put("appraisaltypeid",evaluatetypeid);
			params.put("appraser",aDto.getAppraser());
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
			params.put("sign",aDto.getAppraser());
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
		if(null!=sectionCode && sectionCode.equals(Constant.TYPE_END_BZRPY)){
			sectionName=Constant.TYPE_BZRPY;
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
}
