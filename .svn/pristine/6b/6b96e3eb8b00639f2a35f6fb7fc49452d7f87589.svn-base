package com.flyrish.hades.webapp.action.innercourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nestframework.action.FileItem;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;

import com.flyrish.hades.dto.DownloadDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.TbaseinfoDto;

public class ExcelAction extends UploadAction{
	/**
	 * 接受文件属性
	 */
	public FileItem importFile;
	
	public String cmis30id;
	
	public String campusid;
	
	public String year;
	
	public String levelcode;
	
	public String segment;
	
	public String discode;
	
	public String noticeflag;
	
	public String yearid;
	
	public String gradenum;
	
	public List<GeneralDto> classList;
	
	public String errorexl;
	
	@Before
	public void doBefore(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		cmis30id = userDto.getCmis30id();
		/*cmis30id = "250014";*/
		campusid = userDto.getCampuseId();
		/*campusid = "12966";*/
		levelcode = userDto.getLevelcode();
		/*levelcode="2012003";*/
		discode = userDto.getDiscode();
		params.put("cmis30id", cmis30id);
		params.put("discode",discode);
	}
	/**
	 * 导入模板并解析数据
	 */
	@DefaultAction
	public Object importExcelAndPaser(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		try {
			if(null!=importFile && importFile.getSize()>0){
				//解析excel中的数据
				Map<String,Object> classModeMap = new HashMap<String,Object>();
				classModeMap.put("levelcode", levelcode);
				classModeMap.put("cmis30id", cmis30id);
				classModeMap.put("campusid", campusid);
				classModeMap.put("year", year);
				classList = innerCourseExt.selectNowClass(classModeMap);
				//查询出所有的模块信息
				params.put("innerFlag", "1");
				List<KcourseDto> courseInfos = innerCourseExt.selectInnertCourseCheckList(params);
				//获取老师信息
				List<TbaseinfoDto> teacherInfos = schoolCourseExt.getTeacherInfosByName(params);
				Map<String,Object> downMap = new HashMap<String,Object>();
				downMap.put("segmentid", segment.split("--")[0]);
				downMap.put("year",year);
				//数据库中历史数据
				List<DownloadDto> downInfos = innerCourseExt.selectDownload(downMap);
				int yearcount = Integer.parseInt(userDto.getTermId().substring(0,4))-Integer.parseInt(year);
				boolean flagerror = this.parseExcelTittle(importFile,"sheet1",classList,yearcount);
				if(flagerror){
					List<KcourseDto> excelCourseInfos = this.parseExcelToList(importFile,"sheet1",classList);
					List<KcourseDto> updateCourseInfos = new ArrayList<KcourseDto>();
					if(null!=excelCourseInfos && excelCourseInfos.size()>1){
						List<String> errorMessagesShow = new ArrayList<String>();
						List<String> errorMessagesOther = new ArrayList<String>();
						List<KcourseDto> errorMessages = this.checkImportDatas(excelCourseInfos,courseInfos,teacherInfos);
						if(null!=errorMessages && errorMessages.size()>0){
							//数据导入失败，返回失败信息
							for(int i=0;i<errorMessages.size();i++){
								KcourseDto dto = new KcourseDto();
								dto = errorMessages.get(i);
								if(!(dto.getErrorInfo().indexOf("该学校没有此老师")>-1||dto.getErrorInfo().indexOf("指导教师姓名重复,请录入教师职工号")>-1)){
									errorMessagesShow.add(dto.getRownum()+"#!#!"+dto.getErrorInfo());
								}
							}
							for(int i=0;i<errorMessages.size();i++){
								KcourseDto dto = new KcourseDto();
								dto = errorMessages.get(i);
								if(dto.getErrorInfo().indexOf("该学校没有此老师")>-1||dto.getErrorInfo().indexOf("指导教师姓名重复,请录入教师职工号")>-1){
									errorMessagesOther.add(dto.getRownum()+"#!#!"+dto.getClassName()+"#!#!"+dto.getErrorInfo());
								}
							}
							remove(errorMessagesOther);
							remove(errorMessagesShow);
							request.setAttribute("errorMessagesOther", errorMessagesOther);
							request.setAttribute("errorMessagesShow", errorMessagesShow);
							noticeflag = "1";
							classList.clear();
							return "/courseModular/modularExport.jsp";
						}else{
							for(int i=0; i<excelCourseInfos.size(); i++){
								KcourseDto courseDto = new KcourseDto();
								courseDto = excelCourseInfos.get(i);
								for(int n=0; n<courseInfos.size(); n++){
									KcourseDto kDto= new KcourseDto();
									kDto = courseInfos.get(n);
									String coursename = courseDto.getCourse_name();
										if(coursename.trim().equals(kDto.getCourse_name())){
											courseDto.setCourse_id(kDto.getCourse_id());
										}
								}
								for(int j=0; j<teacherInfos.size(); j++){
									TbaseinfoDto tDto= new TbaseinfoDto();
									tDto = teacherInfos.get(j);
									String teacherInfo = courseDto.getTeacherName();
									if(!StringUtils.isEmpty(teacherInfo)){
										if(teacherInfo.matches("^[0-9]*$")){
											if(teacherInfo.trim().equals(tDto.getEmployeeid())){
												courseDto.setTeacherId(tDto.getTeacherid());
											}
										}else{
											if(teacherInfo.trim().equals(tDto.getName())){
												courseDto.setTeacherId(tDto.getTeacherid());
											}
										}
									}
								}
								updateCourseInfos.add(courseDto);
							}
							List<String> idList = new ArrayList<String>();
							List<String> valueList = new ArrayList<String>();
							List<String> differentList = new ArrayList<String>();
							for(int i=0; i<updateCourseInfos.size(); i++){
								KcourseDto courseDto = new KcourseDto();
								courseDto = updateCourseInfos.get(i);
								int classcount = 0;
								for(int j=0; j<downInfos.size(); j++){
									DownloadDto downDto = new DownloadDto();
									downDto = downInfos.get(j);
									String classname = "";
									if("1".equals(downDto.getGradenum())){
										classname = "高一"+downDto.getClassnum()+"班";
									}else if("2".equals(downDto.getGradenum())){
										classname = "高二"+downDto.getClassnum()+"班";
									}else if("3".equals(downDto.getGradenum())){
										classname = "高三"+downDto.getClassnum()+"班";
									}
									if(classname.equals(courseDto.getClassName())&&downDto.getCourse_name().equals(courseDto.getCourse_name())){
										if(StringUtil.isNotEmpty(downDto.getClass_model_id())){
											idList.add(downDto.getClass_model_id());
											valueList.add(courseDto.getTeacherId());
											classcount++;
										}
									}
								}
								//该班级和导出班级都不相同
								if(classcount==0){
									String classnum = courseDto.getClassName().substring(2, courseDto.getClassName().length()-1);
									String gradenum1 = "";
									if("一".equals(courseDto.getClassName().substring(1,2))){
										gradenum1="1";
									}else if("二".equals(courseDto.getClassName().substring(1,2))){
										gradenum1="2";
									}else if("三".equals(courseDto.getClassName().substring(1,2))){
										gradenum1="3";
									}
									gradenum1 = Integer.parseInt(gradenum1)+yearcount+"";
									differentList.add(courseDto.getCourse_id()+"#!#!"+gradenum1+"#!#!"+classnum+"#!#!"+courseDto.getTeacherId());
								}
								this.remove(differentList);
								
							}
							noticeflag = "2";
							//innerCourseExt.updateClassModel(idList, valueList);
							innerCourseExt.updateClassModel(idList, valueList,userDto);
							if(null!=differentList&&differentList.size()>0){
								classModeMap.put("userDto", userDto);
								innerCourseExt.insertExlSegmentModel(segment, differentList, classModeMap,yearcount);
							}
							classList.clear();
						return "/courseModular/modularAward.jsp";
						}
					}else{
						//导入模板失败 空数据
						noticeflag = "1";
						classList.clear();
						request.setAttribute("noDatas", "您导入的模板没有信息！");
						return "/courseModular/modularExport.jsp";
					}
				}else{
					errorexl = "您导入的模板错误,请按导出模板填写信息！";
					request.setAttribute("errorexl", errorexl);
					noticeflag = "1";
					/*classList.clear();*/
					return "/courseModular/modularExport.jsp";
				}
			}
		}catch (Exception e) {
			logger.error("importExcelAndPaser(HttpServletRequest)", e);
			return "error.jsp";
		}
		return null;
	}
	
	/**
	 * 检验导入数据
	 * @param courseInfos
	 * @return
	 */
	private List<KcourseDto> checkImportDatas(List<KcourseDto> excelCourseInfos,List<KcourseDto> courseInfos,List<TbaseinfoDto> teacherInfos) {
		//获取学科信息
		List<KsysSubjectDto> sysSubject = sysSubjectServiceExt.querySysSubject();
		//错误信息
		List<KcourseDto> errorCourseData = new ArrayList<KcourseDto>();
		if(null!=excelCourseInfos && excelCourseInfos.size()>0){
			for(int i=0;i<excelCourseInfos.size();i++){
				KcourseDto kcourseDto = excelCourseInfos.get(i);
				if(!this.checkSingleData(kcourseDto,i,courseInfos,excelCourseInfos,teacherInfos,sysSubject)){
					errorCourseData.add(kcourseDto);
				}
			}
		}
		return errorCourseData;
	}
	private StringBuffer errorInfo;
	private void appendErrorInfo(String errorTip){
		if(StringUtil.isEmpty(errorInfo.toString())){
			errorInfo.append(errorTip);
		}else{
			errorInfo.append("; "+errorTip);
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
		}else{//如果学科信息填写了，转换成学科编号
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
		}
		//3、模块名称校验
		if(StringUtil.isEmpty(kcourseDto.getCourse_name())){
			this.appendErrorInfo("模块名称未填写");
			rtnVal = false;
		}else{//名称重复了
			boolean isgoOn = true;
			
			if(isgoOn){
				int countnum = 0;
				for(int j=0;j<courseInfos.size();j++){
					if(kcourseDto.getCourse_name().equals(courseInfos.get(j).getCourse_name())){
						countnum=1;//数据库中有该名称
					}
				}
				if(countnum==0){
					this.appendErrorInfo("该模块名称不存在");
					rtnVal = false;
					isgoOn = false;
				}
			}
			if(isgoOn){
				int countnum = 0;
				for(int j=0;j<courseInfos.size();j++){
					if((kcourseDto.getCourse_name()+"--"+kcourseDto.getSubject_name()).equals(courseInfos.get(j).getCourse_name()+"--"+courseInfos.get(j).getSubject_name())){
						countnum=1;
					}
				}
				if(countnum==0){
					this.appendErrorInfo("该模块名称和学科名称不匹配");
					rtnVal = false;
					isgoOn = false;
				}
			}
			if(isgoOn){
				List<String> lst = new ArrayList<String>();
				for(int j=0;j<excelCourseInfos.size();j++){
					KcourseDto dto = new KcourseDto();
					dto = excelCourseInfos.get(j);
					lst.add(dto.getClassName());
				}
				remove(lst);
				for(int j=0;j<excelCourseInfos.size();j++){
					if(kcourseDto.getCourse_name().equals(excelCourseInfos.get(j).getCourse_name())&&!kcourseDto.getRownum().equals(excelCourseInfos.get(j).getRownum())){//名称重复
							this.appendErrorInfo("模块名称与第"+excelCourseInfos.get(j).getRownum()+"行重复");
							rtnVal = false;
							break;
					}
				}
			}
		}
		
		//4、指导老师信息校验
		if(!StringUtil.isEmpty(kcourseDto.getTeacherName())){
			String teacherInfo = kcourseDto.getTeacherName();
			if(teacherInfo.matches("^[0-9]*$")){
				//数字则为教职工号  以教职工号匹配是否有该老师
				boolean isExistTecher = false;
				for(TbaseinfoDto tDto:teacherInfos){
					if(teacherInfo.trim().equals(tDto.getEmployeeid())){
						isExistTecher=true;
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
				for(TbaseinfoDto tDto:teacherInfos){
					if(teacherInfo.trim().equals(tDto.getName())){
						tId = tDto.getTeacherid();
						count++;
					}
				}
				//如果有 有两个及以上提示输入教职工号进行匹配
				if(count==0){
					this.appendErrorInfo("该学校没有此老师");
					rtnVal = false;
				}else if(count>1){
					this.appendErrorInfo("指导教师姓名重复,请录入教师职工号");
					rtnVal = false;
				}else if(count==1){
					kcourseDto.setTeacherId(tId);
				}
			}
		}
		if(!rtnVal){
			kcourseDto.setErrorInfo(errorInfo.toString());
		}
		return rtnVal;
	}

}
