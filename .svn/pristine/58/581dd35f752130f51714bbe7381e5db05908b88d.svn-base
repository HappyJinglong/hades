package com.flyrish.hades.webapp.action.selfappraise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class Jumptree extends BaseAction{
	/**
	 * 处理自我评价业务类
	 */
	@Spring
	private ISelfAppManagerExt selfAppManagerExt;
	
	public String levelcode;
	
	public StudentDto studentDto = new StudentDto();
	
	public String evaluatePersonName;
	
	public List<StudentDto> lstPhoto;
	
	public String commonFuncId;
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:String.valueOf(levelCode);
		Integer studentId=Integer.valueOf(userDto.getPersonid());
		String studentid = studentId==null?null:String.valueOf(studentId);
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("studentid", studentid);
		lstPhoto = selfAppManagerExt.selectPhoto(queryMap);
		evaluatePersonName = userDto.getStudentName();
		if("2012003".equals(levelcode)||"2012004".equals(levelcode)){
			return "gzself_tree.jsp";
		}else if("2012002".equals(levelcode)){
			return "czself_tree.jsp";
		}else{
			return "/login.jsp";
		}
	}
		
}
