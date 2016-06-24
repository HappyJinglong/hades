package com.flyrish.hades.webapp.action.student;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.data.Json;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class CzTreeNodeXieAction extends BaseAction{
	//学生
	public StudentxieDto student;
	//学生id
	public Integer studentid;
	public Integer cmis30id;
	public Integer discode;
	public String classid;
	public Integer level;
	public String levelCode;
	public List<StudentxieDto> list;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	//节点id
	public String nodeId;
	//节点信息对象
	private List<SchoolTreeDto> results = new ArrayList<SchoolTreeDto>();
	/**
	 * 异步获取节点信息
	 * @return 节点信息
	 */
	@Json
	public Object getTreeContent(HttpServletRequest req){
		return getSubNodes(req);
	}
	/**
	 * 获取子节点相关信息
	 * @param req session
	 * @return 子节点信息
	 */
	private Object getSubNodes(HttpServletRequest req) {
		if(StringUtils.isBlank(nodeId) || nodeId.indexOf("root")>-1){
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("studentid", studentid);
			student=playAndHealthExt.findstudentTreeNode(studentid,discode,cmis30id);
					SchoolTreeDto sk = new SchoolTreeDto();
					sk.setId(student.getClassid().toString());
					sk.setText(student.getGradename()+"-"+student.getClassname());
					sk.setLeaf(false);
					sk.setExpanded(true);
					results.add(sk);
		}else{
			//查询班级学生信息
			Map<String,Object>params = new HashMap<String, Object>();
			
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			String classid=nodeId;
			params.put("lid", classid);
			List<SchoolTreeDto> studentInfo = playAndHealthExt.getStudentInfoAll(classid,studentid,cmis30id,discode);
			if (studentInfo != null && studentInfo.size() > 0){
				for (SchoolTreeDto sk : studentInfo){
					sk.setLeaf(true);
					sk.setHref("javascript:parent.forwardStuInfoPage('"+sk.getId()+"','"+sk.getText()+"');");
					results.add(sk);
				}
			}
		}
		return results;
	}
	@Before
	public Object befor(HttpServletRequest req){
		  this.getLoginInfo(req);
		  String studentidString=userDto.getPersonid(); 
		  studentid=Integer.valueOf(studentidString);
		  String discodeString=userDto.getDiscode();
		  discode=Integer.valueOf(discodeString);
		  String cmis30idString=userDto.getCmis30id();
		  cmis30id=Integer.valueOf(cmis30idString);
		  return null;
	
	}
}
