package com.flyrish.hades.webapp.action.schoolCourse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.nestframework.action.FileItem;
import org.nestframework.annotation.Before;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.TbaseinfoDto;
import com.flyrish.hades.util.NoServiceUtil;

public class ImportExcelAction extends CourseAction{
	/**
	 * 接受文件属性
	 */
	public FileItem importFile;
	/**
	 * 错误信息记录
	 */
	private StringBuffer errorInfo;
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
		termId = StringUtils.isEmpty(userDto.getTermId())?"":userDto.getTermId().substring(0, 4);
		cmis30id=userDto.getCmis30id();//"250014";
		discode = userDto.getDiscode();
		params.put("cmis30id", cmis30id);
		params.put("discode",discode);
		//isLockButton = "2014";
		//isLockButton = userDto.getTermId().substring(0, 4);
	}
/*********************************校本课程导入业务处理*****************************************/	
	/**
	 * 保存数据
	 */
	private List<KcourseDto> insertDtoList = new ArrayList<KcourseDto>();
	/**
	 * 更新数据
	 */
	private List<KcourseDto> updateDtoList = new ArrayList<KcourseDto>();
	/**
	 * 跳转至导入文件页面
	 * @return
	 */
	public Object toExportPage(){
		try {
			isLockButton = termId+"-"+(Integer.parseInt(termId)+1);
		} catch (NumberFormatException e) {
			isLockButton = "";
			logger.error("toExportPage()", e);
		}
		return "courseExport.jsp";
	}
	/**
	 * 导入模板并解析数据
	 */
	public Object importExcelAndPaser(HttpServletRequest request,HttpSession session){
		try {
			if(null!=importFile && importFile.getSize()>0){
				//解析excel中的数据
				List<KcourseDto> excelCourseInfos = this.parseExcelToList(importFile,"校本课程");
				if(null!=excelCourseInfos && excelCourseInfos.size()>1){
					List<KcourseDto> errorMessages = this.checkImportDatas(excelCourseInfos);
					if(null!=errorMessages && errorMessages.size()>0){
						if(1==errorMessages.get(0).getRownum()){
							KcourseDto kd = errorMessages.get(0);
							kd.setErrorInfo("您导入的模板错误，请按导出模板填写信息！");
						}
						//数据导入失败，返回失败信息
						request.setAttribute("errorMessages", errorMessages);
						return "courseExport.jsp";
					}else{
						successFlag="OK";
						//保存或者更新数据
						schoolCourseExt.saveOrUpdateCourseList(insertDtoList,updateDtoList,userDto);
						year = termId;
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
					return "courseExport.jsp";
				}
			}
		} catch (Exception e) {
			logger.error("importExcelAndPaser(HttpServletRequest)", e);
			List<KcourseDto> errorMessages = new ArrayList<KcourseDto>();
			KcourseDto kd = new KcourseDto();
			kd.setRownum(2);
			kd.setErrorInfo("导入数据失败！");
			errorMessages.add(kd);
			request.setAttribute("errorMessages", errorMessages);
			return "courseExport.jsp";
		}
		return null;
	}
		@Override
	   protected KcourseDto parseRow(Row rowData, KcourseDto courseInfo) {
		courseInfo.setSubject_name(this.trim(this.safeStringValue(rowData, 0)));
		courseInfo.setCourse_code(this.trim(this.safeStringValue(rowData, 1)));
		courseInfo.setCourse_name(this.trim(this.safeStringValue(rowData, 2)));
		courseInfo.setCredit_hour(this.trim(this.safeStringValue(rowData, 3)));
		courseInfo.setApply_grade(this.trim(this.safeStringValue(rowData, 4)));
		courseInfo.setTeacherName(this.trim(this.safeStringValue(rowData, 5)));
		courseInfo.setPeriod_count(this.trim(this.safeStringValue(rowData, 6)));
		courseInfo.setContent_introduction(this.trim(this.safeStringValue(rowData, 7)));
		courseInfo.setTeach_requirement(this.trim(this.safeStringValue(rowData, 8)));
		courseInfo.setJoin_requirement(this.trim(this.safeStringValue(rowData, 9)));
		courseInfo.setCourse_remark(this.trim(this.safeStringValue(rowData, 10)));
		return courseInfo;
	}
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
			params.put("all", "all");
			List<KcourseDto> courseInfos = schoolCourseExt.getSingleCourseInfoById(params);
			//获取老师信息
			List<TbaseinfoDto> teacherInfos = schoolCourseExt.getTeacherInfosByName(params);
			//获取学科信息
			List<KsysSubjectDto> sysSubject = sysSubjectServiceExt.querySysSubject();
			for(int i=1;i<excelCourseInfos.size();i++){
				KcourseDto kcourseDto = excelCourseInfos.get(i);
				//放主键
				kcourseDto.setCourse_id(this.getId());
				//放学段
				kcourseDto.setSegment_id(segmentId);
				//放cmis30
				kcourseDto.setCmis30id(cmis30id);
				//放courseKind
				kcourseDto.setCourse_kind(Constant.KG_COURSE_KIND);
				if(!this.checkSingleData(kcourseDto,i,courseInfos,excelCourseInfos,teacherInfos,sysSubject)){
					errorCourseData.add(kcourseDto);
				}
			}
		}
		return errorCourseData;
	}
	private boolean checkTittle(KcourseDto importTittle) {
		errorInfo = new StringBuffer("");
		if(null!=importTittle){
			String subjectName = importTittle.getSubject_name();
			if(!(NestUtil.isNotEmpty(subjectName)&&subjectName.equals("学科"))){
				this.appendErrorInfo("学科");
			}
			String courseCode = importTittle.getCourse_code();
			if(!(NestUtil.isNotEmpty(courseCode)&&courseCode.equals("编号"))){
				this.appendErrorInfo("编号");
			}
			String courseName = importTittle.getCourse_name();
			if(!(NestUtil.isNotEmpty(courseName)&&courseName.equals("模块名称"))){
				this.appendErrorInfo("模块名称");
			}
			String creditHour = importTittle.getCredit_hour();
			if(!(NestUtil.isNotEmpty(creditHour)&&creditHour.equals("学分"))){
				this.appendErrorInfo("学分");
			}
			String applyGrade = importTittle.getApply_grade();
			if(!(NestUtil.isNotEmpty(applyGrade)&&applyGrade.equals("适用年级"))){
				this.appendErrorInfo("适用年级");
			}
			String teacherName = importTittle.getTeacherName();
			if(!(NestUtil.isNotEmpty(teacherName)&&teacherName.equals("指导老师姓名或教职工号"))){
				this.appendErrorInfo("指导老师姓名或教职工号");
			}
			String periodCount = importTittle.getPeriod_count();
			if(!(NestUtil.isNotEmpty(periodCount)&&periodCount.equals("学时数"))){
				this.appendErrorInfo("学时数");
			}
			String contentIntroduction = importTittle.getContent_introduction();
			if(!(NestUtil.isNotEmpty(contentIntroduction)&&contentIntroduction.equals("内容简介"))){
				this.appendErrorInfo("内容简介");
			}
			String teachRequirement = importTittle.getTeach_requirement();
			if(!(NestUtil.isNotEmpty(teachRequirement)&&teachRequirement.equals("教学要求"))){
				this.appendErrorInfo("教学要求");
			}
			String joinRequirement = importTittle.getJoin_requirement();
			if(!(NestUtil.isNotEmpty(joinRequirement)&&joinRequirement.equals("参加要求"))){
				this.appendErrorInfo("参加要求");
			}
			String courseRemark = importTittle.getCourse_remark();
			if(!(NestUtil.isNotEmpty(courseRemark)&&courseRemark.equals("备注"))){
				this.appendErrorInfo("备注");
			}
		}
		if(errorInfo.toString().equals("")){
			return true;
		}
		return false;
	}
	private void appendErrorInfo(String errorTip){
		if(StringUtil.isEmpty(errorInfo.toString())){
			errorInfo.append(errorTip);
		}else{
			errorInfo.append(","+errorTip);
		}
	}
	/**
	 * 校验单条数据
	 * @param kcourseDto
	 * @param i
	 * @param courseInfos
	 * @param excelCourseInfos
	 * @return
	 */
	private boolean checkSingleData(KcourseDto kcourseDto, int i,List<KcourseDto> courseInfos, List<KcourseDto> excelCourseInfos,List<TbaseinfoDto> teacherInfos,List<KsysSubjectDto> sysSubject) {
		boolean rtnVal = true;
		errorInfo = new StringBuffer("");
		//1、学科信息校验
		if(StringUtil.isEmpty(kcourseDto.getSubject_name())){
			errorInfo.append("学科信息未填写");
			rtnVal = false;
		}
		//2、编号信息校验
		if(StringUtil.isEmpty(kcourseDto.getCourse_code())){
			this.appendErrorInfo("编号信息未填写");
			rtnVal = false;
		}else{
			boolean isgoOn = true;
			if(kcourseDto.getCourse_code().length()>20){
				this.appendErrorInfo("模块编号字数不能超过20字");
				rtnVal = false;
				isgoOn = false;
			}
			if(isgoOn){
				for(int j=0;j<excelCourseInfos.size();j++){
					if(kcourseDto.getCourse_code().equals(excelCourseInfos.get(j).getCourse_code())){
						if(i!=j){
							this.appendErrorInfo("模块编号与第"+excelCourseInfos.get(j).getRownum()+"行重复");
							rtnVal = false;
							break;
						}
					}
				}
			}
		}
		//3、模块名称校验
		if(StringUtil.isEmpty(kcourseDto.getCourse_name())){
			this.appendErrorInfo("模块名称未填写");
			rtnVal = false;
		}else{
			boolean isgoOn = true;
			if(kcourseDto.getCourse_name().length()>20){
				this.appendErrorInfo("模块名称字数不能超过20字");
				rtnVal = false;
				isgoOn = false;
			}
			if(isgoOn){
				for(int j=0;j<excelCourseInfos.size();j++){
					if(kcourseDto.getCourse_name().equals(excelCourseInfos.get(j).getCourse_name())){
						if(i!=j){
							//名称重复
							this.appendErrorInfo("模块名称与第"+excelCourseInfos.get(j).getRownum()+"行重复");
							rtnVal = false;
							break;
						}
					}
				}
			}
		}
		//4、学分信息校验
		if(StringUtil.isEmpty(kcourseDto.getCredit_hour())){
			this.appendErrorInfo("学分信息未填写");
			rtnVal = false;
		}else{
			boolean isgoOn = true;
			if(!kcourseDto.getCredit_hour().matches("[0-9]+.*[0-9]*")){
				this.appendErrorInfo("学分应输入正数或带两位小数");
				rtnVal = false;
				isgoOn = false;
			}
			if(isgoOn){
				if(kcourseDto.getCredit_hour().matches("[0-9]+.*[0-9]*")&&Double.parseDouble(kcourseDto.getCredit_hour())>6){
					this.appendErrorInfo("学分应小于6");
					rtnVal = false;
				}
			}
		}
		//5、适用年级校验
		if(StringUtil.isEmpty(kcourseDto.getApply_grade())){
			this.appendErrorInfo("适用年级未填写");
			rtnVal = false;
		}else{
			String applayGrade = kcourseDto.getApply_grade();
			String ags = "";
			if(applayGrade.contains("高一年级") && applayGrade.contains("高二年级") && applayGrade.contains("高三年级")){
				ags=Constant.KG_ARRANGE_123;
			}else if(applayGrade.contains("高一年级") && applayGrade.contains("高二年级")){
				ags=Constant.KG_ARRANGE_12;
			}else if(applayGrade.contains("高二年级") && applayGrade.contains("高三年级")){
				ags=Constant.KG_ARRANGE_23;
			}else if(applayGrade.contains("高一年级") && applayGrade.contains("高三年级")){
				ags=Constant.KG_ARRANGE_13;
			}else if(applayGrade.contains("高一年级")){
				ags=Constant.KG_ARRANGE_1;
			}else if(applayGrade.contains("高二年级")){
				ags=Constant.KG_ARRANGE_2;
			}else if(applayGrade.contains("高三年级")){
				ags=Constant.KG_ARRANGE_3;
			}else{
				this.appendErrorInfo("请按照说明要求填写使用年级");
				rtnVal = false;
			}
			if(!ags.equals("")){
				kcourseDto.setApply_grade(ags);
			}
		}
		//6、知道老师信息校验
		if(StringUtil.isEmpty(kcourseDto.getTeacherName())){
			this.appendErrorInfo("指导老师姓名或教职工号未填写");
			rtnVal = false;
		}
		//7、学时数校验
		if(StringUtil.isEmpty(kcourseDto.getPeriod_count())){
			this.appendErrorInfo("学时数未填写");
			rtnVal = false;
		}else{
			boolean isgoOn = true;
			if(!kcourseDto.getPeriod_count().matches("[0-9]+.*[0-9]*")){
				this.appendErrorInfo("课时数应输入正数或带两位小数");
				rtnVal = false;
				isgoOn = false;
			}
			if(isgoOn){
				if(Double.parseDouble(kcourseDto.getPeriod_count())>1000.00){
					this.appendErrorInfo("课时数不能超过4位数");
					rtnVal = false;
				}
			}
		}
		//8、内容简介校验
		if(StringUtil.isNotEmpty(kcourseDto.getContent_introduction())&&kcourseDto.getContent_introduction().length()>122){
			this.appendErrorInfo("内容简介不能超过122个字");
			rtnVal = false;
		}
		//9、教学要求校验
		if(StringUtil.isNotEmpty(kcourseDto.getTeach_requirement())&&kcourseDto.getTeach_requirement().length()>100){
			this.appendErrorInfo("教学要求不能超过100个字");
			rtnVal = false;
		}
		//10、参加要求校验
		if(StringUtil.isNotEmpty(kcourseDto.getJoin_requirement())&&kcourseDto.getJoin_requirement().length()>100){
			this.appendErrorInfo("参加要求不能超过100个字");
			rtnVal = false;
		}
		//11、备注检验
		if(StringUtil.isNotEmpty(kcourseDto.getCourse_remark())&&kcourseDto.getCourse_remark().length()>1000){
			this.appendErrorInfo("参加要求不能超过1000个字");
			rtnVal = false;
		}
		if(rtnVal){
			//校验是否有该门学科
			boolean isExistSub = false;
			for(KsysSubjectDto subject : sysSubject){
				if(subject.getSubject_name().equals(kcourseDto.getSubject_name())){
					isExistSub = true;
					kcourseDto.setSubject_id(subject.getSubject_id());
					break;
				}
			}
			if(!isExistSub){
				this.appendErrorInfo("学校没有该门学科");
				rtnVal = false;
			}
			//校验模块编号和模块名称
			for(int j=0;j<courseInfos.size();j++){
				if(kcourseDto.getCourse_code().equals(courseInfos.get(j).getCourse_code())
						&& kcourseDto.getCourse_name().equals(courseInfos.get(j).getCourse_name())
							&& Constant.KG_COURSE_KIND.equals(courseInfos.get(j).getCourse_kind())){
					//标记成更新模块
					kcourseDto.setFlag("1");
					kcourseDto.setCourse_id(courseInfos.get(j).getCourse_id());
					kcourseDto.setSegmentCourseId(courseInfos.get(j).getSegmentCourseId());
					break;
				}else if(kcourseDto.getCourse_code().equals(courseInfos.get(j).getCourse_code())){
					//编号重复
					this.appendErrorInfo("模块编号已经存在");
					rtnVal = false;
				}else if(kcourseDto.getCourse_name().equals(courseInfos.get(j).getCourse_name())){
					//名称重复
					this.appendErrorInfo("模块名称已经存在");
					rtnVal = false;
				}
			}
			//指导教师校验
			String teacherInfo = kcourseDto.getTeacherName();
			if(teacherInfo.matches("^[0-9]*$")){
				//数字则为教职工号  以教职工号匹配是否有该老师
				boolean isExistTecher = false;
				for(TbaseinfoDto tDto:teacherInfos){
					if(teacherInfo.trim().equals(tDto.getEmployeeid())){
						isExistTecher=true;
						kcourseDto.setTeacherId(tDto.getTeacherid());
						kcourseDto.setUserId(tDto.getUserId());
						break;
					}
				}
				if(!isExistTecher){
					this.appendErrorInfo("该学校没有此老师");
					rtnVal = false;
				}
			}else{
				//填写名字或者是其他信息 以名字匹配是否有该老师
				int count=0;
				String tId = "";
				String userId = "";
				for(TbaseinfoDto tDto:teacherInfos){
					if(teacherInfo.trim().equals(tDto.getName())){
						tId = tDto.getTeacherid();
						userId = tDto.getUserId();
						count++;
					}
				}
				//如果有 有两个及以上提示输入教职工号进行匹配
				if(count==0){
					this.appendErrorInfo("该学校没有此老师");
					rtnVal = false;
				}else if(count>1){
					this.appendErrorInfo("指导教师姓名重复请录入教师职工号");
					rtnVal = false;
				}else if(count==1){
					kcourseDto.setTeacherId(tId);
					kcourseDto.setUserId(userId);
				}
			}
		}
		if(!rtnVal){
			kcourseDto.setErrorInfo(errorInfo.toString());
		}
		
		if("1".equals(kcourseDto.getFlag())){
			//更新
			updateDtoList.add(kcourseDto);
		}else{
			//插入
			insertDtoList.add(kcourseDto);
		}
		return rtnVal;
	}

	/**
	 * 导出校本课程模板
	 */
	public void exportTemplate(HttpServletResponse response,HttpServletRequest request){
		String downloadfile =  NoServiceUtil.replaceFileSeparator(request.getRealPath("")+constantBean.get("coursefilePath"));
		String [] filePath = downloadfile.split(File.separator+File.separator);
		String downName = "";
		if(null!=filePath && filePath.length>1){
			downName = filePath[filePath.length-1];
		}
		downName = isLockButton+"学年"+segmentId+downName;
		String loggerInfo = "exportTemplate(HttpServletResponse,HttpServletRequest)";
		this.downFile(response, downloadfile, downName, loggerInfo);
	}
	public static void main(String[] args) {
		//System.out.println("1.5".matches("[0-9]+.*[0-9]*"));
	}
}
