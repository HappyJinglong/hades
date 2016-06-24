package com.flyrish.hades.webapp.action.schoolCourse;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.TbaseinfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.util.Utility;

public class SchoolCourseAction extends CourseAction{
	public String radio;
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
		cmis30id=userDto.getCmis30id();//"250014";
		discode = userDto.getDiscode();
		params.put("cmis30id",cmis30id );
		params.put("discode", discode);
		//isLockButton = "2014";
		isLockButton = userDto.getTermId().substring(0, 4);
		newRoleTeacher = new UserDto();
		newRoleTeacher.setCampuseId(userDto.getCampuseId());
		newRoleTeacher.setUnitid(userDto.getUnitid());
		newRoleTeacher.setUsertype(Constant.GZ_XIAOBENKECHENG_TEACHER);
	}
/*********************************校本课程填写相关处理*****************************************/
	/**
	 * 删除模块信息
	 * @param response
	 */
	public void toDeleteCourse(HttpServletResponse response){
		try {
			//删除前对数据进行校验
			if(!this.deleteMay()){
				params.put("course_id", courseid);
				newRoleTeacher.setTeacherid(teacherId);
				newRoleTeacher.setUserid(this.getUserId("@_"+oldTeacherIdInfo,teacherId));
				params.put("newRoleTeacher", newRoleTeacher);
				schoolCourseExt.deleteCourseInfo(params);
			}else{
				try {
					response.getWriter().write("exist");
				} catch (IOException e) {
					logger.error("toDeleteCourse(HttpServletResponse)", e);
				}
			}//  ManagerException
		} catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {}
			logger.error("toDeleteCourse(HttpServletResponse)", e);
		}
	}
	//校验数据能否删除
	private boolean deleteMay() {
		boolean isExist = true;
		params.put("segCourseId", segCourseId);
		isExist = schoolCourseExt.isWriteScore(params);
		return isExist;
	}
	/**
	 * 查询单个模块信息
	 * @return
	 */
	public Object toCourseDetails(){
		String segment_course_id="";
		if(NestUtil.isNotEmpty(radio)&&courseid.contains("_")){
			segment_course_id=courseid.split("_")[1];
			courseid=courseid.split("_")[0];
		}else if(NestUtil.isNotEmpty(radio)){
			String[] radios=radio.split("_");
			segment_course_id=radios[1];
		}else{
			segment_course_id=courseid.split("_")[1];
			courseid=courseid.split("_")[0];
		}
		params.put("courseid", courseid);
		List<KcourseDto> courseInfos = schoolCourseExt.getSingleCourseInfoById(params);
		if(null!=courseInfos && courseInfos.size()>0){
			if(NestUtil.isEmpty(segment_course_id)){
				singleCourseInfo = courseInfos.get(0);
			}else{
				for(KcourseDto dto:courseInfos){
					if(segment_course_id.equals(dto.getSegmentCourseId())){
						singleCourseInfo=dto;
					}
				}
			}
		}
		kads = schoolCourseExt.getAspectInfos(params);
		if(null!=kads && kads.size()>0){
			this.initAplayGradeInfo();
		}
		if("is".equals(isUpdate)){
			return "courseModularUpdate.jsp";
		}else{
			return "queryCourseDetails.jsp";
		}
	}
	private void initAplayGradeInfo() {
		gns = new ArrayList<String>();
		sns = new ArrayList<String>();
		for(KcourseArrangeDto kad:kads){
			String grade = kad.getApply_grade();
			String segment = kad.getSegment_order();
			if(Constant.KG_ARRANGE_123.equals(grade)){
				gns.add(Constant.KG_ARRANGE_1);
				gns.add(Constant.KG_ARRANGE_2);
				gns.add(Constant.KG_ARRANGE_3);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_12.equals(grade)){
				gns.add(Constant.KG_ARRANGE_1);
				gns.add(Constant.KG_ARRANGE_2);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_13.equals(grade)){
				gns.add(Constant.KG_ARRANGE_1);
				gns.add(Constant.KG_ARRANGE_3);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_23.equals(grade)){
				gns.add(Constant.KG_ARRANGE_2);
				gns.add(Constant.KG_ARRANGE_3);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_1.equals(grade)){
				gns.add(Constant.KG_ARRANGE_1);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_2.equals(grade)){
				gns.add(Constant.KG_ARRANGE_2);
				sns.add(segment);
			}else if(Constant.KG_ARRANGE_3.equals(grade)){
				gns.add(Constant.KG_ARRANGE_3);
				sns.add(segment);
			} 
		}
	}
	@DefaultAction
	public Object toCourseList(HttpServletRequest req,HttpSession session){
		this.initParams(req,session);
		this.queryCoursePage();
		return "courseList.jsp";
	}
	/**
	 * 跳转至添加数据页面
	 * @return
	 */
	public Object toAddPage(){
		return "courseModularAdd.jsp";
	}
	/**
	 * 添加模块数据
	 * @return
	 */
	public void addCourse(HttpServletResponse response){
		try {
			this.initInsertDatas();
			this.inintApplyGradeData();
			List<KcourseDto> courseInfos = schoolCourseExt.getSingleCourseInfoById(params);
			String repeatFlag = null;
			if("is".equals(isUpdate)){
				params.put("course_id", courseid);
				params.put("segment_course_id", segCourseId);
				//更新数据
				if("00".equals(isChange)){//没有改变直接修改数据
					//两个不一样   新旧的都要放
					if(NestUtil.isNotEmpty(teacherId) && !teacherId.equals(oldTeacherId)){
						newRoleTeacher.setUserid(this.getUserId(teacherIdInfo,teacherId));
						newRoleTeacher.setTeacherid(teacherId);
						params.put("newRoleTeacher", newRoleTeacher);
						UserDto oldRoleTeacher = new UserDto();
						oldRoleTeacher.setUserid(this.getUserId(oldTeacherIdInfo,oldTeacherId));
						oldRoleTeacher.setTeacherid(oldTeacherId);
						params.put("oldRoleTeacher", oldRoleTeacher);
					}else{
						params.put("isNotSame", "1");
					}
					schoolCourseExt.updateCourseInfo(params);
				}else{//验重
					repeatFlag = isUpdateRepeat(courseInfos,isChange);
					if("".equals(repeatFlag)){
						//两个不一样   新旧的都要放
						if(NestUtil.isEmpty(teacherId) && !teacherId.equals(oldTeacherId)){
							newRoleTeacher.setUsed(this.getUserId(teacherIdInfo,teacherId));
							newRoleTeacher.setTeacherid(teacherId);
							params.put("newRoleTeacher", newRoleTeacher);
							UserDto oldRoleTeacher = new UserDto();
							oldRoleTeacher.setUsed(this.getUserId(oldTeacherIdInfo,oldTeacherId));
							oldRoleTeacher.setTeacherid(oldTeacherId);
							params.put("oldRoleTeacher", oldRoleTeacher);
						}else{
							params.put("isNotSame", "1");
						}
						schoolCourseExt.updateCourseInfo(params);
					}else{
						try {
							response.getWriter().write(repeatFlag);
						} catch (IOException e) { }
					}
				}
			}else{
				//插入数据验重
				repeatFlag = isRepeat(courseInfos);
				if("".equals(repeatFlag)){
					newRoleTeacher.setUserid(this.getUserId(teacherIdInfo,teacherId));
					newRoleTeacher.setTeacherid(teacherId);
					params.put("newRoleTeacher", newRoleTeacher);
					schoolCourseExt.insertCourseInfos(params);
				}else{
					try {
						response.getWriter().write(repeatFlag);
					} catch (IOException e) { }
				}
			}
		}catch (ManagerException e) {
			try {
				response.getWriter().write("0");
			} catch (IOException ex) { }
			logger.error("addCourse(HttpServletResponse)", e);
		}
	}
	private String getUserId(String teacherinfo,String tID) {
		if(NestUtil.isNotEmpty(teacherinfo)){
			boolean notExsit = false;//不存在
			String[] teacherIdInfos = teacherinfo.split("_");
			String techaerEduId = teacherIdInfos[1];
			if(null!=teacherIdInfos && teacherIdInfos.length==2){
				List<LoginOUser> loginUsers = redisServiceExt.readList(techaerEduId+"_ouser");
				if(null!=loginUsers && loginUsers.size()>0){
					for(LoginOUser loginUser : loginUsers){
						if(NestUtil.isNotEmpty(tID) && tID.equals(loginUser.getPersonid())){
							return loginUser.getUserid();//存在就返回数据咯
						}
					}
				}
			}
			//不存在就走这里哦
			String userId = schoolCourseExt.queryUserIdFromDB(tID,userDto.getUnitid());
			if(NestUtil.isEmpty(userId)){
				throw new ManagerException();
			}
			return userId;
		}
		return "";
	}
	/**
	 * 跟新验重
	 * @param courseInfos
	 * @param isChange
	 * @return
	 */
	private String isUpdateRepeat(List<KcourseDto> courseInfos,String isChange) {
		//组合：00 都没改变  01 10 11 
		StringBuffer isRepeat = new StringBuffer("");
		if("01".equals(isChange)){
			for (KcourseDto kd : courseInfos) {
				String cName = kd.getCourse_name();
				if(null!=courseName && courseName.equals(cName)){
					isRepeat.append("1");
				}
			}
		}else if("10".equals(isChange)){
			for (KcourseDto kd : courseInfos) {
				String cCode = kd.getCourse_code();
				if(null!=courseCode && courseCode.equals(cCode)){
					isRepeat.append("2");
				}
			}
		}else if("11".equals(isChange)){
			isRepeat.append(this.isRepeat(courseInfos));
		}
		return isRepeat.toString();
	}
	/**
	 * 返回重复数据
	 * @param courseInfos
	 * @return
	 */
	private String isRepeat(List<KcourseDto> courseInfos) {
		StringBuffer isRepeat = new StringBuffer("");
		if(null!=courseInfos && courseInfos.size()>0){
			for (KcourseDto kd : courseInfos) {
				String cName = kd.getCourse_name();
				String cCode = kd.getCourse_code();
				if(null!=courseName && courseName.equals(cName)){
					isRepeat.append("1");
				}
				if(null!=courseCode && courseCode.equals(cCode)){
					isRepeat.append("2");
				}
			}
		}
		if(isRepeat.toString().equals("")){
			return isRepeat.toString();
		}else{
			return isRepeat.toString();
		}
	}
	/**
	 * 异步加载老师信息
	 * @param req
	 * @return
	 */
	@Json
	public Object queryTeacherInfo(HttpServletRequest req){
		params.put("name", URLDecoder.decode(guidTeacherName));
		List<TbaseinfoDto> teacherInfos = schoolCourseExt.getTeacherInfosByName(params);
		if(null!=teacherInfos && teacherInfos.size()>0){
			List<String>listStr=new ArrayList<String>();
			for (TbaseinfoDto teacher : teacherInfos) {
				Map<String,Object>maps=new HashMap<String,Object>();
				maps.put("id",teacher.getTeacherid());
				maps.put("name",teacher.getName()+"_"+teacher.getEmployeeid());
				listStr.add(Utility.createJsonStr(maps));
			}
			return JSONObject.fromObject("{val:"+Utility.createJsonStr(listStr)+"}");
		}
		return null;
	}
	private void initInsertDatas(){
		//模块
		params.put("subject_id", subjectId);
		params.put("series_id", seriesid);
		params.put("course_code", courseCode);
		params.put("course_name", courseName);
		params.put("course_short_name", courseShortName);
		params.put("course_kind", Constant.KG_COURSE_KIND);
		params.put("student_aspect", studentAspect);
		params.put("period_count", periodCount);
		params.put("credit_hour", creditHour);
		params.put("content_introduction", contentIntroduction);
		params.put("join_requirement", joinRequirement);
		params.put("teach_requirement", teachRequirement);
		params.put("course_remark", courseRemark);
		//指导教师
		params.put("declare_teacher", teacherId);
		params.put("segment_id", segmentId);
		params.put("audit_status", Constant.KG_AUDIT_STATUS);
	}
	/**
	 * 组装适用年级数据
	 */
	public void inintApplyGradeData(){
		StringBuffer sb = new StringBuffer();
		String grade="";
		if(!(NestUtil.isEmpty(gName)&&NestUtil.isEmpty(sName))){
			gradeName = gName.split("@");
			segmentName = sName.split("@");
		}
		if(null!=gradeName && gradeName.length>0){
			//将年级组装成字符串
			for (int i=0;i<gradeName.length;i++) {
				if(i==gradeName.length-1){
					sb.append(gradeName[i]);
				}else{
					sb.append(gradeName[i]+",");
				}
			}
			
			if(sb.toString().contains(Constant.KG_ARRANGE_1+","+Constant.KG_ARRANGE_2+","+Constant.KG_ARRANGE_3)){
				grade=Constant.KG_ARRANGE_123;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_1+","+Constant.KG_ARRANGE_2)){
				grade=Constant.KG_ARRANGE_12;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_1+","+Constant.KG_ARRANGE_3)){
				grade=Constant.KG_ARRANGE_13;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_2+","+Constant.KG_ARRANGE_3)){
				grade=Constant.KG_ARRANGE_23;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_1)){
				grade=Constant.KG_ARRANGE_1;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_2)){
				grade=Constant.KG_ARRANGE_2;
			}else if(sb.toString().contains(Constant.KG_ARRANGE_3)){
				grade=Constant.KG_ARRANGE_3;
			}
			List<KcourseArrangeDto>applyGradeAndSegs = new ArrayList<KcourseArrangeDto>();
			if(segmentName!=null && segmentName.length>0){
				for(String seg:segmentName){
					KcourseArrangeDto kad = new KcourseArrangeDto();
					kad.setApply_grade(grade);
					kad.setSegment_order(seg);
					applyGradeAndSegs.add(kad);
				}
			}else{
				KcourseArrangeDto kad = new KcourseArrangeDto();
				kad.setApply_grade(grade);
				applyGradeAndSegs.add(kad);
			}
			params.put("applyGradeAndSegs", applyGradeAndSegs);
		}
	}
}
