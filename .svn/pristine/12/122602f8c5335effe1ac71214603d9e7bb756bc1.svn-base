package com.flyrish.hades.webapp.action.score;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.schoolCourse.CourseAction;

public class XZ extends CourseAction{

	public String studentfilePath;
	
	@DefaultAction
	public void exportStudentTemplate(HttpServletResponse response,HttpServletRequest request){
		String downloadfile = NoServiceUtil.replaceFileSeparator(request.getRealPath("")+constantBean.get(studentfilePath));
		String [] filePath = downloadfile.split(File.separator+File.separator);
		String downName = "";
		if(null!=filePath && filePath.length>1){
			downName = filePath[filePath.length-1];
		}
		String loggerInfo = "exportStudentTemplate(HttpServletResponse,HttpServletRequest)";
		this.downFile(response, downloadfile, downName, loggerInfo);
	}
}
