package com.flyrish.hades.webapp.action.innercourse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Cell;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.DownloadDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.KclassModelDto;
import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.SysdictDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IInnerCourseExt;
import com.flyrish.hades.service.ext.ISysSubjectServiceExt;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class InnerCourse extends NginxUploadAction{

	/**
	 * 处理记录袋业务类
	 */
	@Spring
	IInnerCourseExt innerCourseExt;
	/**
	 * 查询学科
	 */
	@Spring
	ISysSubjectServiceExt sysSubjectServiceExt;
	
	public String subjectid;//学科id
	
	public String subjectidUpdate;//更新显示学科id
	
	public String coursekind;//课程类别
	
	public String isdefault;//来源,1为系统,0为自编
	
	public String coursename;//模块名称
	
	public String courseid;//模块id
	
	public String coursecode;//模块编号
	
	public String cmis30id;
	
	public String courseshortname;//模块简称
	
	public String studentaspect;//学习方向
	
	public String seriesid;//系列
	
	public String courseremark;//备注
	
	public String year;
	
	public List<KcourseArrangeDto> kcourseArrangeList;//
	
	public List<SysdictDto> sysdictList;//学习方向
	
	public List<String> lst;//向前台传递list
	
	public List<KcourseDto> kcourseList;
	
	public List<KcourseDto> kcourseListAll;
	
	public String[] arrange;
	
	public String credithour;//学分
	
	public String periodcount;//学时数
	
	public String checkbox;//选定的学段
	
	public String isUpdate;
	
	public String yearid;
	
	public String campusid;
	
	public List<GeneralDto> gradeList;
	
	public List<GeneralDto> classList;
	
	public String updateSeriesid;
	
	public String levelcode;
	
	//--------------------------------
	//教师授课查询模块
	public String gradenum;
	
	public String selectGradenum;
	
	public String segment;
	
	public String[] CourseIds;
	
	public String selectClassid;
	
	public String selectCourseid;
	
	public String selectteacherid;
	
	public List<KcourseDto> selectCourseList;//教师授课选择课程模块回显
	
	public List<KclassModelDto> selectClassModelList;
	
	public List<String> couresModelList;
	
	public List<String> couresModelListnum;
	
	public String teacherCourseid;
	
	public String noticeflag;
	
	public String searchShow = "0";
	
	//-----
	@Before
	public void doBefore(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		cmis30id = userDto.getCmis30id();
		/*cmis30id = "250014";*/
		campusid = userDto.getCampuseId();
		/*campusid = "12966";*/
		if(StringUtil.isEmpty(gradenum)){
			selectGradenum = "1--1";
		}else{
			selectGradenum = gradenum;
		}
		if(StringUtil.isEmpty(yearid)){
			yearid = userDto.getTermId().substring(0,4);
		}
		levelcode = userDto.getLevelcode();
		/*levelcode="2012003";*/
	}
	/**
	 * 初始跳转到内置课程页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Object goList(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		return "/courseModular/courseModularList.jsp";
	}
	/**
	 * 初始授课教师页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Object goAward(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		return "/courseModular/modularAward.jsp";
	}
	/**
	 * 初始跳转到导入授课教师模块
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Object goModularExport(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		return "/courseModular/modularExport.jsp";
	}
	/**
	 * 查询授课教师
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Object searchGoAward(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Map<String,Object> queryMap = new HashMap<String,Object>();
		Map<String,Object> queryAll = new HashMap<String,Object>();
		String Gradenum = "";
		String Segment = "";
		if(NestUtil.isNotEmpty(gradenum)){
			if("1".equals(gradenum.split("--")[1])){
				Gradenum = Constant.KG_ARRANGE_1;
			}else if("2".equals(gradenum.split("--")[1])){
				Gradenum = Constant.KG_ARRANGE_2;
			}else if("3".equals(gradenum.split("--")[1])){
				Gradenum = Constant.KG_ARRANGE_3;
			}
		}
		if("null".equals(segment))
			segment=null;
		if(NestUtil.isNotEmpty(segment)){
			String[] arr = segment.split("--");
			String ss=arr[1];
			
			if("第1学段".equals(ss)){
				Segment = Constant.KG_ARRANSEGMENT_1;
			}else if("第2学段".equals(ss)){
				Segment = Constant.KG_ARRANSEGMENT_2;
			}else if("第3学段".equals(ss)){
				Segment = Constant.KG_ARRANSEGMENT_3;
			}else if("第4学段".equals(ss)){
				Segment = Constant.KG_ARRANSEGMENT_4;
			}
		}
		if(NestUtil.isNotEmpty(subjectid)&&!"00".equals(subjectid)){
			queryMap.put("subject_id", subjectid);
		}
			queryMap.put("cmis30id", cmis30id);
			queryAll.put("cmis30id", cmis30id);
		/*queryMap.put("apply_grade", Gradenum);
		queryMap.put("segment_order", Segment);*/
		kcourseList = innerCourseExt.selectTeacherCourse(queryMap);
		kcourseListAll = innerCourseExt.selectTeacherCourse(queryAll);
		if(NestUtil.isEmpty(subjectid)){
			selectCourseList = new ArrayList<KcourseDto>();
			if(CourseIds!=null){
				for(int i=0;i<CourseIds.length;i++){
					KcourseDto dto = new KcourseDto();
					dto.setCourse_id(CourseIds[i].split("--")[0]);
					dto.setCourse_name(CourseIds[i].split("--")[1]);
					selectCourseList.add(dto);
				}
			}
			Map<String,Object> classMap = new HashMap<String,Object>();
			classMap.put("cmis30id", cmis30id);
			classMap.put("gradeid", gradenum.split("--")[0]);
			classMap.put("year", yearid);
			classList = innerCourseExt.selectClass(classMap);
			Map<String,Object> classModeMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(segment)){
				classModeMap.put("segment_id", segment.split("--")[0]);
			}
			if(!StringUtil.isEmpty(gradenum)){
				classModeMap.put("gradeid", gradenum.split("--")[0]);
			}
			if(!StringUtil.isEmpty(yearid)){
				classModeMap.put("year", yearid);
			}
			selectClassModelList = innerCourseExt.selectClassModel(classModeMap);
			couresModelList = new ArrayList<String>();
			couresModelListnum = new ArrayList<String>();
			for(int i=0;i<selectClassModelList.size();i++){
				KclassModelDto dto = new KclassModelDto();
				dto = selectClassModelList.get(i);
				couresModelList.add(dto.getCourse_id()+"##"+dto.getCourse_name()+"##"+dto.getClass_model_id());
				couresModelListnum.add(dto.getCourse_id()+"##"+dto.getCourse_name()+"##"+dto.getSubject_name());
			}
			 for(int i = 0;i<couresModelListnum.size()-1;i++){ 
				    for (int j = couresModelListnum.size()-1;j>i;j--){ 
				      if(couresModelListnum.get(j).equals(couresModelListnum.get(i))){ 
				    	  couresModelListnum.remove(j); 
				      } 
				    } 
			 }
			 searchShow = "1";
			return "/courseModular/modularAward.jsp";
		}else{
			try {
				Map<String,Object> Map = new HashMap<String,Object>();
				response.setContentType("text/html;charset=UTF-8"); 
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				Map.put("list", kcourseListAll);
				Map.put("listnow", kcourseList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			} catch (IOException e) {
			}
			return null;
		}
	}
	/**
	 * 查询内置课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(subjectid)){
				queryMap.put("subjectid", subjectid);
			}
			if(!StringUtil.isEmpty(coursekind)){
				queryMap.put("coursekind", coursekind);
			}
			if(!StringUtil.isEmpty(isdefault)){
				queryMap.put("isdefault", isdefault);
			}
			if(StringUtil.isEmpty(courseid)){
				if(!StringUtil.isEmpty(coursename)){
					queryMap.put("coursename", coursename);
				}
			}
			if(!StringUtil.isEmpty(courseid)){
				queryMap.put("courseid", courseid);
			}
			queryMap.put("cmis30id", cmis30id);
			pageObj = innerCourseExt.selectInnertCourse1(queryMap, pageNo, pageSize);
			
			if(!StringUtil.isEmpty(courseid)){
				lst = new ArrayList<String>();
				Map<String,Object> queryMap1 = new HashMap<String,Object>();
				queryMap1.put("courseid", courseid);
				queryMap1.put("cmis30id", cmis30id);
				kcourseArrangeList = innerCourseExt.selectArrange(queryMap1);
				/*Map<String,Object> Map = new HashMap<String,Object>();*/
			    for (int i = 0; i < kcourseArrangeList.size(); i++) {
			    	KcourseArrangeDto dto = new KcourseArrangeDto();
			    	dto = kcourseArrangeList.get(i);
			    	if(!(Constant.KG_ARRANGE_12.equals(dto.getApply_grade())&&Constant.KG_ARRANGE_13.equals(dto.getApply_grade())&&Constant.KG_ARRANGE_23.equals(dto.getApply_grade())&&Constant.KG_ARRANGE_123.equals(dto.getApply_grade()))){
			    		lst.add(dto.getApply_grade()+"^"+dto.getSegment_order());
			    	}else{
			    		if(Constant.KG_ARRANGE_12.equals(dto.getApply_grade())){
			    			lst.add(Constant.KG_ARRANGE_1+"^"+dto.getSegment_order());
			    			lst.add(Constant.KG_ARRANGE_2+"^"+dto.getSegment_order());
			    		}else if(Constant.KG_ARRANGE_13.equals(dto.getApply_grade())){
			    			lst.add(Constant.KG_ARRANGE_1+"^"+dto.getSegment_order());
			    			lst.add(Constant.KG_ARRANGE_3+"^"+dto.getSegment_order());
			    		}else if(Constant.KG_ARRANGE_23.equals(dto.getApply_grade())){
			    			lst.add(Constant.KG_ARRANGE_2+"^"+dto.getSegment_order());
			    			lst.add(Constant.KG_ARRANGE_3+"^"+dto.getSegment_order());
			    		}else if(Constant.KG_ARRANGE_123.equals(dto.getApply_grade())){
			    			lst.add(Constant.KG_ARRANGE_1+"^"+dto.getSegment_order());
			    			lst.add(Constant.KG_ARRANGE_2+"^"+dto.getSegment_order());
			    			lst.add(Constant.KG_ARRANGE_3+"^"+dto.getSegment_order());
			    		}
			    	}
		        }
			    if(!StringUtil.isEmpty(isUpdate)){
			    	kcourseList = innerCourseExt.selectInnertCourseList(queryMap1);
			    	KcourseDto dto =new KcourseDto();
			    	dto = kcourseList.get(0);
			    	subjectidUpdate = dto.getSubject_id();
			    	updateSeriesid = dto.getSeries_id();
			    	return "/courseModular/courseModularUpdate.jsp";
			    }else{
			    	return "/courseModular/ModularQueryList.jsp";
			    }
			}else{
				return "/courseModular/courseModularList.jsp";
			}
	}
	/**
	 * 跳转到添加内置课程页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public Object doAdd() {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("dicttype", "12312");
		sysdictList = innerCourseExt.selectAspect(queryMap);
		return "/courseModular/courseModularAdd.jsp";
	}
	/**
	 * 删除授课教师
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public void deleteTeacherCourse(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			if(!StringUtil.isEmpty(teacherCourseid)){
				String cid = teacherCourseid.split("--")[0];
				String sid = teacherCourseid.split("--")[1];
				String cmid = cmis30id;
				String gid = gradenum.split("--")[0];
				String yid = year;
				//Integer count = innerCourseExt.CheckDeleteClass(cid, sid, cmid, yid, gid);
				/*if(count==0){*/
				innerCourseExt.deleteTeacherCourse(cid, sid, cmid, yid, gid,userDto);
				/*}else{
					try {
						response.getWriter().write("@@");
					} catch (IOException e) {}
				}*/
				
			}
		} catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("deleteTeacherCourse(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} 
	}
	/**
	 * 删除内置课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public void deleteCourse(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("coursename", coursename);
			queryMap.put("cmis30id", cmis30id);
			Integer count = innerCourseExt.checkDeleteCourse(queryMap);
			if(count==0){
				innerCourseExt.deleteKcourse(courseid);
			}else{
				try {
					response.getWriter().write("@@");
				} catch (IOException e) {}
			}
			
		} catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doInsert(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} 
	}
	/**
	 * 添加内置课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public void doInsert(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		int codetype = 0;
		int nametype = 0;
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("cmis30id", cmis30id);
		kcourseList = innerCourseExt.selectInnertCourseCheckList(queryMap);
		List<String> codeList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		for(int i=0;i<kcourseList.size();i++){
			KcourseDto dto = new KcourseDto();
			dto = kcourseList.get(i);
			codeList.add(dto.getCourse_code());
			nameList.add(dto.getCourse_name());
		}
		Iterator<String> itercode = codeList.iterator();  
		Iterator<String> itername = nameList.iterator();  
		 while(itercode.hasNext()){  
			 if(!StringUtil.isEmpty(coursecode)){
				 if(coursecode.equals(itercode.next())){
					 codetype = 1;
					 break;
				 }
			 }
		}  	 
		 while(itername.hasNext()){  
			 if(!StringUtil.isEmpty(coursename)){
				 if(coursename.equals(itername.next())){
					 nametype = 1;
					 break;
				 }
			 }
		} 
		 try { 
		 if(codetype==0&&nametype==0){
			 KcourseDto courseDto = new KcourseDto();
				courseDto.setSubject_id(subjectid);
				courseDto.setSeries_id(seriesid);
				courseDto.setCmis30id(cmis30id);
				courseDto.setCourse_code(coursecode);
				courseDto.setCourse_name(coursename);
				courseDto.setCourse_kind(coursekind);
				courseDto.setStudent_aspect(studentaspect);
				courseDto.setPeriod_count(periodcount);
				courseDto.setCredit_hour(credithour);
				courseDto.setCourse_remark(courseremark);
				courseDto.setIs_default("0");
				courseDto.setCourse_short_name(courseshortname);
				String[] order = null;
				if(!StringUtil.isEmpty(checkbox)){
					order = checkbox.split("-");
				}
				innerCourseExt.insertKcourse(courseDto,order);
		 }else if(codetype==1&&nametype!=1){
			 response.getWriter().write("1");//模块编号重复
		 }else if(codetype!=1&&nametype==1){
			 response.getWriter().write("2");//模块名重复
		 }else{
			 response.getWriter().write("3");//模块名和编号都重复
		 }
		} catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doInsert(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {}
		}
	}
	/**
	 * 添加授课教师
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public void doInsertSegmentModel(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 try { 
			 Map<String,String> queryMap = new HashMap<String,String>();
				List<String> classList = new ArrayList<String>();
				List<String> courseList = new ArrayList<String>();
				selectCourseid = selectCourseid.replaceAll("tr_","");
					if(!StringUtil.isEmpty(selectCourseid)){
						for(int i=0;i<selectCourseid.split("--").length;i++){
							courseList.add(selectCourseid.split("--")[i]);
						} 
					}
					if(!StringUtil.isEmpty(selectClassid)){
						for(int i=0;i<selectClassid.split("--").length;i++){
							classList.add(selectClassid.split("--")[i]);
						}
					}
					if(!StringUtil.isEmpty(selectteacherid)){
						selectteacherid = selectteacherid.replaceAll("teacher_","");
						selectteacherid = selectteacherid.replaceAll("_hidden","");
						for(int i=0;i<selectteacherid.split("##").length;i++){
							if(selectteacherid.split("##")[i].split("--").length>0){
								queryMap.put(selectteacherid.split("##")[i].split("--")[1],selectteacherid.split("##")[i].split("--")[0]);
							}
						}
					}
					queryMap.put("unitid", userDto.getUnitid());
					queryMap.put("campusid", userDto.getCampuseId());
					queryMap.put("cmis30Id", userDto.getCmis30id());
					innerCourseExt.insertSegmentModel(segment.split("--")[0], courseList, classList, queryMap);
		 }catch (Exception ex) {
				try {
					response.getWriter().write("##");
				} catch (IOException e) {}
			}
	}
	/**
	 * 更新授课教师
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	private List<String> insertData = new ArrayList<String>();
	public void updateSegmentModel(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			List<String> idList = new ArrayList<String>();
			List<String> valueList = new ArrayList<String>();
			for(int i=0;i<teacherCourseid.split("##").length;i++){
				if(teacherCourseid.split("##")[i].split("--")[0].indexOf("_")>-1){
				}else{
					if(teacherCourseid.split("##")[i].split("--").length==4){
						insertData.add(teacherCourseid.split("##")[i]);
						continue;
					}
					if("@@".equals(teacherCourseid.split("##")[i].split("--")[0])){
						idList.add(teacherCourseid.split("##")[i].split("--")[1]);
						valueList.add(null);
					}else{
						idList.add(teacherCourseid.split("##")[i].split("--")[1]);
						valueList.add(teacherCourseid.split("##")[i].split("--")[0]);
					}
				}
			}
			innerCourseExt.updateClassModel(idList, valueList,userDto);
			if(null!=insertData && insertData.size()>0){
				innerCourseExt.insertQSDatas(insertData,userDto);
			}
		}catch (Exception ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {}
		}
	}
	/**
	 * 更新内置课程
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	public void doUpdate(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		if("0".equals(isdefault)){
			int codetype = 0;
			int nametype = 0;
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("cmis30id", cmis30id);
			kcourseList = innerCourseExt.selectInnertCourseList(queryMap);
			List<String> codeList = new ArrayList<String>();
			List<String> nameList = new ArrayList<String>();
			List<String> idList = new ArrayList<String>();
			for(int i=0;i<kcourseList.size();i++){
				KcourseDto dto = new KcourseDto();
				dto = kcourseList.get(i);
				codeList.add(dto.getCourse_code());
				nameList.add(dto.getCourse_name());
				idList.add(dto.getCourse_id());
			}
			for(int i=0;i<idList.size();i++){
				if(coursecode.equals(codeList.get(i))){
					if(!courseid.equals(idList.get(i))){
						 codetype = 1;
						 break;
					}
				}
			}
			for(int i=0;i<idList.size();i++){
				if(coursename.equals(nameList.get(i))){
					if(!courseid.equals(idList.get(i))){
						 nametype = 1;
						 break;
					}
				}
			}
			 try { 
			 if(codetype==0&&nametype==0){
				 KcourseDto courseDto = new KcourseDto();
				 	courseDto.setCourse_id(courseid);
					courseDto.setSubject_id(subjectid);
					courseDto.setSeries_id(seriesid);
					courseDto.setCourse_code(coursecode);
					courseDto.setCourse_name(coursename);
					courseDto.setCourse_kind(coursekind);
					courseDto.setStudent_aspect(studentaspect);
					courseDto.setPeriod_count(periodcount);
					courseDto.setCredit_hour(credithour);
					courseDto.setCourse_remark(courseremark);
					courseDto.setCourse_short_name(courseshortname);
					String[] order = null;
					if(!StringUtil.isEmpty(checkbox)){
						order = checkbox.split("-");
					}
					innerCourseExt.updateKcourse(courseDto,order);
			 }else if(codetype==1&&nametype!=1){
				 response.getWriter().write("1");//模块编号重复
			 }else if(codetype!=1&&nametype==1){
				 response.getWriter().write("2");//模块名重复
			 }else{
				 response.getWriter().write("3");//模块名和编号都重复
			 }
			} catch(ManagerException ex){
				try {
					response.getWriter().write("##");
				} catch (IOException e) {
				}
				logger.error("doUpdate(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			} catch (IOException ex) {
				try {
					response.getWriter().write("##");
				} catch (IOException e) {}
			}
		}else{ 
			try {
				KcourseDto courseDto = new KcourseDto();
				courseDto.setCourse_id(courseid);
				courseDto.setSubject_id(subjectid);
				courseDto.setSeries_id(seriesid);
				courseDto.setCourse_code(coursecode);
				courseDto.setCourse_name(coursename);
				courseDto.setCourse_kind(coursekind);
				courseDto.setStudent_aspect(studentaspect);
				courseDto.setPeriod_count(periodcount);
				courseDto.setCredit_hour(credithour);
				courseDto.setCourse_remark(courseremark);
				courseDto.setCourse_short_name(courseshortname);
				String[] order = null;
				if(!StringUtil.isEmpty(checkbox)){
					order = checkbox.split("-");
				}
				innerCourseExt.updateKcourse(courseDto,order);
			}catch(ManagerException ex){
				try {
					response.getWriter().write("##");
				} catch (IOException e) {
				}
				logger.error("doUpdate(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			}
		}
	}
	HSSFSheet sheet;
	@SuppressWarnings("deprecation")
	public void download(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(segment)){
				queryMap.put("segmentid", segment.split("--")[0]);
			}
			List<DownloadDto> downloadList = new ArrayList<DownloadDto>();
			queryMap.put("year", yearid);
			downloadList = innerCourseExt.selectDownload(queryMap);
				response.reset();
				response.setContentType("application/vnd.ms-excel; charset=utf-8");
				// 设置下载文件的名称
				int yearidnum=Integer.valueOf(yearid).intValue()+1;
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String((yearid+"-"+yearidnum+"学年"+segment.split("--")[1]+"授课教师导入模板.xls").getBytes("gbk"),
								"iso8859-1"));
				OutputStream os = response.getOutputStream();// 打开文件
				HSSFWorkbook wb = new HSSFWorkbook();  
				sheet = wb.createSheet("sheet1");// 创建第一个sheet
				List<KsysSubjectDto> subjectList = new ArrayList<KsysSubjectDto>();
				subjectList = sysSubjectServiceExt.querySysSubject();
				String[] subject = null;
				String sss = "";
				for(int i=0;i<subjectList.size();i++){
					KsysSubjectDto dto = new KsysSubjectDto();
					dto = subjectList.get(i);
					sss+=dto.getSubject_name()+"#!#!";
				}
				subject = sss.split("#!#!");
				sheet = setHSSFValidation(sheet, subject, 1, 500, 0, 0);// 第一列的前501行都设置为选择列表形式.  
				/*sheet.protectSheet("1qaz2wsx3edc4rfv@becom");*/
				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setLocked(true);
				HSSFCellStyle cellStyle1 = wb.createCellStyle();
				cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle1.setLocked(true);
				HSSFCellStyle cellStyle2 = wb.createCellStyle();
				cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle2.setLocked(false);
				int countnum1 = 0;
				int countnum2 = 0;
				int countnum3 = 0;
				List<String> gradenum1list = new ArrayList<String>();
				List<String> gradenum2list = new ArrayList<String>();
				List<String> gradenum3list = new ArrayList<String>();
				List<String> courseall = new ArrayList<String>();

				Map<String,Object> classMap = new HashMap<String,Object>();
				classMap.put("cmis30id", cmis30id);
				classMap.put("year", yearid);
				classMap.put("levelcode", levelcode);
				classMap.put("campusid", campusid);
				int yearcount = Integer.parseInt(userDto.getTermId().substring(0,4))-Integer.parseInt(yearid);
				
				classList = innerCourseExt.selectNowClass(classMap);
				for(int i=0;i<classList.size();i++){
					GeneralDto dto = new GeneralDto();
					dto = classList.get(i);
					if("1".equals(dto.getGradenum())){
						gradenum1list.add(dto.getClassnum());
						countnum1++;
					}else if("2".equals(dto.getGradenum())){
						gradenum2list.add(dto.getClassnum());
						countnum2++;
					}else if("3".equals(dto.getGradenum())){
						gradenum3list.add(dto.getClassnum());
						countnum3++;
					}
				}
				int countall = 2;
				if(countnum1!=0){
					countall += countnum1;
				}
				if(countnum2!=0){
					countall += countnum2;
				}
				if(countnum3!=0){
					countall += countnum3;
				}
				HSSFRow row = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,2,0,0));
				sheet.addMergedRegion(new CellRangeAddress(0,2,1,1));
				if(countall>2){
					sheet.addMergedRegion(new CellRangeAddress(0,0,2,countall-1));
				}
				if(countnum1>0){
					sheet.addMergedRegion(new CellRangeAddress(1,1,2,2+countnum1-1));
				}
				if(countnum2>0){
					sheet.addMergedRegion(new CellRangeAddress(1,1,2+countnum1,2+countnum1+countnum2-1));
				}
				if(countnum3>0){
					sheet.addMergedRegion(new CellRangeAddress(1,1,2+countnum1+countnum2,2+countnum1+countnum2+countnum3-1));
				}
				row.createCell(0).setCellValue("学科");
				createRegionStyle(cellStyle1,new CellRangeAddress(0,2,0,0));
				row.createCell(1).setCellValue("模块名称");
				createRegionStyle(cellStyle1,new CellRangeAddress(0,2,1,1));
				row.createCell(2).setCellValue("模块任课教师姓名或教职工号");
				row.getCell(2).setCellStyle(cellStyle);
				row = sheet.createRow(1);
				if(yearcount==0){
					if(countnum1>0){
						row.createCell(2).setCellValue("高一年级");
						row.getCell(2).setCellStyle(cellStyle);
					}
					if(countnum2>0){
						row.createCell(2+countnum1).setCellValue("高二年级");
						row.getCell(2+countnum1).setCellStyle(cellStyle);
					}
					if(countnum3>0){
						row.createCell(2+countnum1+countnum2).setCellValue("高三年级");
						row.getCell(2+countnum1+countnum2).setCellStyle(cellStyle);
					}
					row = sheet.createRow(2);
					if(gradenum1list.size()>0){
						for (int i = 0; i < gradenum1list.size();i++){
							Cell cell = row.createCell(i+2);
							cell.setCellValue("高一"+(String) gradenum1list.get(i)+"班");
							row.getCell(i+2).setCellStyle(cellStyle);
						}
					}
					if(gradenum2list.size()>0){
						for (int i = 0; i < gradenum2list.size();i++){
							Cell cell = row.createCell(2+i+countnum1);
							cell.setCellValue("高二"+(String) gradenum2list.get(i)+"班");
							row.getCell(2+i+countnum1).setCellStyle(cellStyle);
						}
					}
					if(gradenum3list.size()>0){
						for (int i = 0; i < gradenum3list.size();i++){
							Cell cell = row.createCell(2+i+countnum1+countnum2);
							cell.setCellValue("高三"+(String) gradenum3list.get(i)+"班");
							row.getCell(2+i+countnum1+countnum2).setCellStyle(cellStyle);
						}
					}
				}
				if(yearcount==1){
					if(countnum1>0){
						row.createCell(2).setCellValue("高二年级");
						row.getCell(2).setCellStyle(cellStyle);
					}
					if(countnum2>0){
						row.createCell(2+countnum1).setCellValue("高三年级");
						row.getCell(2+countnum1).setCellStyle(cellStyle);
					}
					row = sheet.createRow(2);
					if(gradenum1list.size()>0){
						for (int i = 0; i < gradenum1list.size();i++){
							Cell cell = row.createCell(i+2);
							cell.setCellValue("高二"+(String) gradenum1list.get(i)+"班");
							row.getCell(i+2).setCellStyle(cellStyle);
						}
					}
					if(gradenum2list.size()>0){
						for (int i = 0; i < gradenum2list.size();i++){
							Cell cell = row.createCell(2+i+countnum1);
							cell.setCellValue("高三"+(String) gradenum2list.get(i)+"班");
							row.getCell(2+i+countnum1).setCellStyle(cellStyle);
						}
					}
				}
				if(yearcount==2){
					if(countnum1>0){
						row.createCell(2).setCellValue("高三年级");
						row.getCell(2).setCellStyle(cellStyle);
					}
					row = sheet.createRow(2);
					if(gradenum1list.size()>0){
						for (int i = 0; i < gradenum1list.size();i++){
							Cell cell = row.createCell(i+2);
							cell.setCellValue("高三"+(String) gradenum1list.get(i)+"班");
							row.getCell(i+2).setCellStyle(cellStyle);
						}
					}
				}
			//-------课改_班级开设的模块表有数据
			if(downloadList.size()>0){
					for(int i=0;i<downloadList.size();i++){
						DownloadDto dto = new DownloadDto();
						dto = downloadList.get(i);
						courseall.add(dto.getSubject_name()+"&&##!!"+dto.getCourse_name());
					}
					remove(courseall);
					int maxlength = -1;
					if(courseall.size()>0){
						for (int i = 0; i < courseall.size();i++){
							if(maxlength<courseall.get(i).split("&&##!!")[1].length()){
								maxlength = courseall.get(i).split("&&##!!")[1].length();
							}
						}
						for (int i = 0; i < courseall.size();i++){
							row = sheet.createRow(i+3);
							Cell cell = row.createCell(0);
							cell.setCellValue(courseall.get(i).split("&&##!!")[0]);
							row.getCell(0).setCellStyle(cellStyle2);
							Cell cell1 = row.createCell(1);
							cell1.setCellValue(courseall.get(i).split("&&##!!")[1]);
							sheet.setColumnWidth(1, maxlength*2*256);
							row.getCell(1).setCellStyle(cellStyle2);
						}
					}
					if(gradenum1list.size()>0){
						for(int i = 0; i < gradenum1list.size();i++){
							for (int j = 0; j < courseall.size();j++){
								for(int m=0;m<downloadList.size();m++){
									DownloadDto dto = new DownloadDto();
									dto = downloadList.get(m);
									if("1".equals(dto.getGradenum())&&gradenum1list.get(i).equals(dto.getClassnum())&&courseall.get(j).split("&&##!!")[1].equals(dto.getCourse_name())){
										if(!StringUtil.isEmpty(dto.getTeachername())){
											sheet.getRow(3+j).createCell(2+i).setCellValue(dto.getTeachername());
										}
									}
								}
							}
						}
					}
					if(gradenum2list.size()>0){
						for(int i = 0; i < gradenum2list.size();i++){
							for (int j = 0; j < courseall.size();j++){
								for(int m=0;m<downloadList.size();m++){
									DownloadDto dto = new DownloadDto();
									dto = downloadList.get(m);
									if("2".equals(dto.getGradenum())&&gradenum2list.get(i).equals(dto.getClassnum())&&courseall.get(j).split("&&##!!")[1].equals(dto.getCourse_name())){
										if(!StringUtil.isEmpty(dto.getTeachername())){
											sheet.getRow(3+j).createCell(2+i+countnum1).setCellValue(dto.getTeachername());
										}
									}
								}
							}
						}
					}
					if(gradenum3list.size()>0){
						for(int i = 0; i < gradenum3list.size();i++){
							for (int j = 0; j < courseall.size();j++){
								for(int m=0;m<downloadList.size();m++){
									DownloadDto dto = new DownloadDto();
									dto = downloadList.get(m);
									if("3".equals(dto.getGradenum())&&gradenum3list.get(i).equals(dto.getClassnum())&&courseall.get(j).split("&&##!!")[1].equals(dto.getCourse_name())){
										if(!StringUtil.isEmpty(dto.getTeachername())){
											sheet.getRow(3+j).createCell(2+i+countnum1+countnum2).setCellValue(dto.getTeachername());
										}
									}
								}
							}
						}
					}
				}
			wb.write(os);// 写文件
			os.close();// 关闭输出流
		}catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符串转日期
	 * 
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return new Date();
		}
	}
	
	/**
	 * list去重
	 * @param lst
	 */
	public void remove(List<String> lst) {
		for(int i = 0;i<lst.size()-1;i++){ 
		    for (int j = lst.size()-1;j>i;j--){ 
		      if(lst.get(j).equals(lst.get(i))){ 
		    	  lst.remove(j); 
		      } 
		    } 
		} 
	}
	/**
	 * 设置合并单元格的样式
	 */
	@SuppressWarnings("deprecation")
	public void createRegionStyle(HSSFCellStyle style,CellRangeAddress region)
	{
		for(int i=region.getFirstRow();i<=region.getLastRow();i++){  
            HSSFRow row=sheet.getRow(i);  
            if(row==null) row=sheet.createRow(i);  
            for(int j=region.getFirstColumn();j<=region.getLastColumn();j++){  
                HSSFCell cell=row.getCell(j);  
                if( cell==null){  
                    cell=row.createCell(j);  
                    cell.setCellValue("");  
                }  
                 cell.setCellStyle(style);  
            }  
        }  
	}
	//为了解决list转换为Json时日期异常所需的内部类
			class DateJsonValueProcessor implements JsonValueProcessor
			{
				public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
				
					return null;
				}
				public Object processObjectValue(String key, Object value,
						JsonConfig jsonconfig) {
					if (value == null)  
		              return "";  
		          // 注意：在判断几个父子级类型时要先判断子类型再判断父类型  
		          if (value instanceof java.sql.Date) {  
		              String str = DateUtil.dateToStr((java.util.Date) value,  
		                      "yyyy-MM-dd");//这里是我封装的工具,可以使用SimpleDateFormat代替，一样  
		              return str;  
		          } else if (value instanceof java.sql.Timestamp  
		                  || value instanceof java.util.Date) {  
		              String str = DateUtil.dateToStr((java.util.Date) value,  
		                      "yyyy-MM-dd");  
		              return str;  
		          }  
		          return value.toString();  
				}
				
			}
			 /** 
		     * 设置某些列的值只能输入预制的数据,显示下拉框. 
		     * @param sheet 要设置的sheet. 
		     * @param textlist 下拉框显示的内容 
		     * @param firstRow 开始行 
		     * @param endRow 结束行 
		     * @param firstCol   开始列 
		     * @param endCol  结束列 
		     * @return 设置好的sheet. 
		     */  
		    @SuppressWarnings("deprecation")
			public static HSSFSheet setHSSFValidation(HSSFSheet sheet,  
		            String[] textlist, int firstRow, int endRow, int firstCol,  
		            int endCol) {  
		        // 加载下拉列表内容  
		        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);  
		        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
		        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
		        // 数据有效性对象  
		        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
		        sheet.addValidationData(data_validation_list);  
		        return sheet;  
		    }  
}
