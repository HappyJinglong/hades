package com.flyrish.hades.webapp.action.schoolCourse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.nestframework.action.FileItem;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KstudentMatriculateDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.util.NoServiceUtil;

public class CouresHireStudentAction extends CourseAction{
	/**
	 * 接受文件属性
	 */
	public FileItem importFile;
	/**
	 * 删除录取学生id
	 */
	public String matriculateId;
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
		cmis30id=userDto.getCmis30id();//"250014";
		discode = userDto.getDiscode();
		params.put("cmis30id", cmis30id);
		params.put("discode",discode);
		params.put("campuseid", userDto.getCampuseId());
	}
	/*************************录取学生相关业务处理********************************/
	/**
	 * 展示学生信息
	 * @return
	 */
	@DefaultAction
	public Object toStudentDetails(){
		params.put("segCourseId", segCourseId);
		params.put("year", isLockButton);
		pageObj = schoolCourseExt.getHiredStudentInfosById(params, pageNo, pageSize);
		return "queryStudent.jsp";
	}
	/**
	 * 删除学生信息
	 * @param response
	 */
	public void toDeleteStudentInfo(HttpServletResponse response){
		try {
			if(!NestUtil.isEmpty(matriculateId)){
				List<String> matriculateIds = Arrays.asList( matriculateId.split(","));
				params.put("matriculateIds", matriculateIds);
				params.put("deleteStudentFlag", "1");
				if(schoolCourseExt.isWriteScore(params)){
					try {
						response.getWriter().write("exist");
					} catch (IOException e1) {}
				}else{
					schoolCourseExt.deleteHiredStudentInfo(params);
				}
			}
		} catch (ManagerException e) {
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {}
			logger.error("toDeleteStudentInfo(HttpServletResponse)", e);
		}
	}
	public Object toExportStudentPage(){
		return "studentExport.jsp";
	}
	/**
	 * 导出录取学生模板
	 */
	public void exportStudentTemplate(HttpServletResponse response,HttpServletRequest request){
		String downloadfile = NoServiceUtil.replaceFileSeparator(request.getRealPath("")+constantBean.get("studentfilePath"));
		String [] filePath = downloadfile.split(File.separator+File.separator);
		String downName = "";
		if(null!=filePath && filePath.length>1){
			downName = filePath[filePath.length-1];
		}
		String loggerInfo = "exportStudentTemplate(HttpServletResponse,HttpServletRequest)";
		this.downFile(response, downloadfile, downName, loggerInfo);
	}
	/**
	 * 导入模板并解析数据
	 */
	public Object importExcelAndPaser(HttpServletRequest request,HttpSession session){
		try {
			if(null!=importFile && importFile.getSize()>0){
				//解析excel中的数据
				List<KcourseDto> excelCourseInfos = this.parseExcelToList(importFile,"校本课程录取学生");
				if(null!=excelCourseInfos && excelCourseInfos.size()>1){
					List<KcourseDto> errorMessages = this.checkImportDatas(excelCourseInfos);
					if(null!=errorMessages && errorMessages.size()>0){
						if(1==errorMessages.get(0).getRownum()){
							KcourseDto kd = errorMessages.get(0);
							kd.setErrorInfo("您导入的模板错误，请按导出模板填写信息！");
						}
						//数据导入失败，返回失败信息
						request.setAttribute("errorMessages", errorMessages);
						return "studentExport.jsp";
					}else{
						//保存数据
						schoolCourseExt.insertHiredStudentInfo(excelCourseInfos);
						isHired="1";
						successFlag="OK";
						this.initParams(request, session);
						this.queryCoursePage();
						return "courseList.jsp";
					}
				}else{
					//导入模板失败 空数据
					List<KcourseDto> errorMessages = new ArrayList<KcourseDto>();
					KcourseDto kd = new KcourseDto();
					kd.setRownum(2);
					kd.setErrorInfo("您导入的文件没有信息或者使用模板错误，请使用导出模板按照要求填写信息！");
					errorMessages.add(kd);
					request.setAttribute("errorMessages", errorMessages);
					return "studentExport.jsp";
				}
			}
		} catch (Exception e) {
			logger.error("importExcelAndPaser(HttpServletRequest)", e);
			return "error.jsp";
		}
		return null;
	}
	private Set<String>classIds = new HashSet<String>();
	/**
	 * 检验导入数据
	 * @param courseInfos
	 * @return
	 */
	private List<KcourseDto> checkImportDatas(List<KcourseDto> excelCourseInfos) {
		//错误信息
		List<KcourseDto> errorCourseData = new ArrayList<KcourseDto>();
		if(null!=excelCourseInfos && excelCourseInfos.size()>0){
			//获取第一条数据
			KcourseDto importTittle = excelCourseInfos.get(0);
			if(!this.checkTittle(importTittle)){
				importTittle.setRownum(1);
				errorCourseData.add(importTittle);
				return errorCourseData;
			}
			//查询出所有的模块信息
			params.put("all", "current");
			List<KcourseDto> courseInfos = schoolCourseExt.getSingleCourseInfoById(params);
			//获取学生信息
			params.put("grade", "grade");
			List<KstudentMatriculateDto> garadeInfos = schoolCourseExt.getSchoolStudentInfos(params);
			for(int i=1;i<excelCourseInfos.size();i++){
				KcourseDto kcourseDto = excelCourseInfos.get(i);
				//放cmis30
				kcourseDto.setCmis30id(cmis30id);
				//放discode
				kcourseDto.setDiscode(discode);
				//放courseKind
				kcourseDto.setCourse_kind(Constant.KG_COURSE_KIND);
				if(!this.checkSingleData(kcourseDto,i,courseInfos,excelCourseInfos,garadeInfos)){
					errorCourseData.add(kcourseDto);
				}
			}
		}
		
		//继续校验学生信息是否存在和是否已经录取了这些学生
		if(null!=errorCourseData&&errorCourseData.size()==0){
			//根据导入班级信息查询学生
			params.put("classIds", classIds);
			List<KstudentMatriculateDto> studentInfos = schoolCourseExt.getSchoolStudentInfos(params);
			//已经录取学生
			List<KstudentMatriculateDto> hiredStudentInfos = schoolCourseExt.getSchoolHiredStudentInfos(params);
			for(int i=1;i<excelCourseInfos.size();i++){
				KcourseDto kcourseDto = excelCourseInfos.get(i);
				if(!this.checkStudentData(kcourseDto,studentInfos,hiredStudentInfos)){
					errorCourseData.add(kcourseDto);
				}
			}
		}
		return errorCourseData;
	}
	
	private boolean checkTittle(KcourseDto importTittle) {
		errorInfo = new StringBuffer("");
		if(null!=importTittle){
			String courseName = importTittle.getCourse_name();
			if(!(NestUtil.isNotEmpty(courseName)&&courseName.equals("模块名称"))){
				this.appendErrorInfo("模块名称");
			}
			String className = importTittle.getClassName();
			if(!(NestUtil.isNotEmpty(className)&&className.equals("学生所在行政班级"))){
				this.appendErrorInfo("学生所在行政班级");
			}
			String studentName = importTittle.getStudentName();
			if(!(NestUtil.isNotEmpty(studentName)&&studentName.equals("姓名"))){
				this.appendErrorInfo("姓名");
			}
			String eduId = importTittle.getEduId();
			if(!(NestUtil.isNotEmpty(eduId)&&eduId.equals("教育ID"))){
				this.appendErrorInfo("教育ID");
			}
		}
		if(errorInfo.toString().equals("")){
			return true;
		}
		return false;
	}
	private boolean checkStudentData(KcourseDto kcourseDto,List<KstudentMatriculateDto> studentInfos,List<KstudentMatriculateDto> hiredStudentInfos) {
		boolean retVal = true;
		errorInfo = new StringBuffer("");
		String eduId = kcourseDto.getEduId();
		String studentName = kcourseDto.getStudentName();
		String className = kcourseDto.getClassName();
		int count = 0;
		for(KstudentMatriculateDto kmDto : studentInfos){
			if(StringUtil.isEmpty(eduId)){
				if(studentName.equals(kmDto.getStudentName())
						&&className.equals(kmDto.getClassName())
							&& kcourseDto.getClassId().equals(kmDto.getClassid())){
					kcourseDto.setStudentId(kmDto.getStudentid());
					kcourseDto.setClassId(kmDto.getClassid());
					count++;
				}
			}else{
				if(studentName.equals(kmDto.getStudentName())
						&&className.equals(kmDto.getClassName())
							&&eduId.equals(kmDto.getEduId())
							 && kcourseDto.getClassId().equals(kmDto.getClassid())){
					kcourseDto.setStudentId(kmDto.getStudentid());
					kcourseDto.setClassId(kmDto.getClassid());
					count++;
				}
			}
		}
		if(count==0){
			this.appendErrorInfo(className+"没有"+studentName+"这位同学");
			retVal=false;
		}else if(count>1){
			this.appendErrorInfo(className+studentName+"存在同名的请输入该名学生的教育id号");
			retVal=false;
		}
		if(retVal){
			for(KstudentMatriculateDto kmd : hiredStudentInfos){
				if(!(kcourseDto.getStudentId().equals(kmd.getStudentid())
						&& kcourseDto.getClassId().equals(kmd.getClassid())
						 && kcourseDto.getSegmentCourseId().equals(kmd.getSegment_course_id()))){
					continue;
				}else{
					this.appendErrorInfo(studentName+"已经选择了"+kcourseDto.getCourse_name()+"请删除该条数据再导入");
					retVal=false;
					break;
				}
			}
		}
		if(!retVal){
			kcourseDto.setErrorInfo(errorInfo.toString());
		}
		return retVal;
	}
	@Override
   protected KcourseDto parseRow(Row rowData, KcourseDto courseInfo) {
		if(NestUtil.isEmpty(this.trim(this.safeStringValue(rowData, 0)))&&NestUtil.isEmpty(this.trim(this.safeStringValue(rowData, 1)))
				&&NestUtil.isEmpty(this.trim(this.safeStringValue(rowData, 2)))&&NestUtil.isEmpty(this.trim(this.safeStringValue(rowData, 3)))){
				return null;
		}
		courseInfo.setCourse_name(this.trim(this.safeStringValue(rowData, 0)));
		courseInfo.setClassName(this.trim(this.safeStringValue(rowData, 1)));
		courseInfo.setStudentName(this.trim(this.safeStringValue(rowData, 2)));
		courseInfo.setEduId(this.trim(this.safeStringValue(rowData, 3)));
		return courseInfo;
	}
	
	
	private StringBuffer errorInfo;
	private boolean checkSingleData(KcourseDto kcourseDto, int i,List<KcourseDto> courseInfos, List<KcourseDto> excelCourseInfos,List<KstudentMatriculateDto> garadeInfos) {
		boolean retVl = true;
		errorInfo = new StringBuffer("");
		String course_name = kcourseDto.getCourse_name();
		String className = kcourseDto.getClassName();
		String studentName = kcourseDto.getStudentName();
		String eduId = kcourseDto.getEduId();
		//1、校验模块名称是否填写
		if(StringUtil.isEmpty(course_name)){
			this.appendErrorInfo("模块信息未填写");
			retVl=false;
		}
		//2、校验班级是否填写
		if(StringUtil.isEmpty(className)){
			this.appendErrorInfo("班级信息未填写");
			retVl=false;
		}else{
			//检验班级填写是否符合规范
			if(!this.checkClassName(className)){
				this.appendErrorInfo("班级名称书写不规范");
				retVl=false;
			}
		}
		//3、校验学生姓名是否填写
		if(StringUtil.isEmpty(studentName)){
			this.appendErrorInfo("学生姓名未填写");
			retVl=false;
		}
		//4、检验必填三项是否有重复
		if(retVl){
			//三项数据书否重复
			String currentRecord = (course_name+className+studentName+eduId);
			for(int j=1;j<excelCourseInfos.size();j++){
				String cName = excelCourseInfos.get(j).getCourse_name();
				String clName = excelCourseInfos.get(j).getClassName();
				String stName = excelCourseInfos.get(j).getStudentName();
				String eId = excelCourseInfos.get(j).getEduId();
				String otherRecord = cName+clName+stName+(StringUtil.isEmpty(eId)?"":eId);
				if(currentRecord.equals(otherRecord)){
					if(i!=j){
						this.appendErrorInfo("该行记录与第"+excelCourseInfos.get(j).getRownum()+"行数据重复，请删除一条记录");
						retVl=false;
						break;
					}
				}
			}
		}
		//5、检验相关数据是否存在库中
		if(retVl){
			//A、检验模块信息
			boolean isExsit = false;
			for(KcourseDto kdt : courseInfos){
				if(course_name.equals(kdt.getCourse_name()) && Constant.KG_COURSE_KIND.equals(kdt.getCourse_kind())){
					isExsit = true;
					kcourseDto.setSegmentCourseId(kdt.getSegmentCourseId());
					break;
				}
			}
			if(!isExsit){
				this.appendErrorInfo("当前学年不存在此模块信息");
				retVl=false;
			}
			
			/************************截取*******************************/
			//B、校验行政班级是否存在
			int count = 0;
			for(KstudentMatriculateDto gInfo : garadeInfos){
				if(className.equals(gInfo.getClassName())){
					kcourseDto.setClassId(gInfo.getClassid());
					classIds.add(gInfo.getClassid());
					count++;
				}
			}
			if(count==0){
				this.appendErrorInfo("学校没有这个班级");
				retVl=false;
			}
		}
		if(!retVl){
			kcourseDto.setErrorInfo(errorInfo.toString());
		}
		return retVl;
	}
	/**
	 * 校验班级名填写是否符合规则
	 * @param className
	 * @return
	 */
	private boolean checkClassName(String className) {
		boolean isGoOn = true;
		//int startIndex = 0;
		int endIndex = 0;
//		if(!(className.startsWith("高")&&className.endsWith("班"))
//				&&!(className.startsWith("内")&&className.endsWith("班"))){
//			isGoOn = false;
//		}
		if(!className.endsWith("班")){
			isGoOn = false;
		}
		if(isGoOn){
			//startIndex = className.indexOf("高");
			endIndex = className.indexOf("班");
			String secondStr = className.substring(1, 2);
			//判断第二个字符是否是“一二三”
			if(className.startsWith("高") && !"一二三".contains(secondStr)){
				isGoOn = false;
			}
			if(isGoOn){
				String thirdStr = className.substring(2, endIndex);
				try {
					if(NestUtil.isNotEmpty(thirdStr)){
						if(thirdStr.contains("-")){
							String[] thirdStrs = thirdStr.split("-");
							for(String ts : thirdStrs){
								Integer.parseInt(ts);
							}
						}else{
							Integer.parseInt(thirdStr);
						}
					}else{
						isGoOn = false;
					}
				} catch (NumberFormatException e) {
					isGoOn = false;
				}
			}
		}
		return isGoOn;
	}
	private void appendErrorInfo(String errorTip){
		if(StringUtil.isEmpty(errorInfo.toString())){
			errorInfo.append(errorTip);
		}else{
			errorInfo.append(","+errorTip);
		}
	}
	
/*	public static void main(String[] args) {
		String className="高一2班";
		if(!(className.startsWith("高")&&className.endsWith("班"))
				&&!(className.startsWith("内")&&className.endsWith("班"))){
			System.out.println(11);
		}
		System.out.println(22);
	}*/
	
}
