package com.flyrish.hades.webapp.action.middlemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.data.Json;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class TreeNodeAction extends BaseAction{
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	//节点id
	public String nodeId;
	//节点信息对象
	private List<SchoolTreeDto> results = new ArrayList<SchoolTreeDto>();
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
	}
	/**
	 * 异步获取节点信息
	 * @return 节点信息
	 */
	@Json
	public Object getTreeContent(HttpServletRequest req){
		try {
			return getSubNodes(req);
		} catch (Exception e) {
			logger.error("getTreeContent(HttpServletRequest)", e);
			return "error.jsp";
		}
	}
	/**
	 * 获取子节点相关信息
	 * @param req session
	 * @return 子节点信息
	 */
	private Object getSubNodes(HttpServletRequest req) {
		if(StringUtils.isBlank(nodeId) || nodeId.indexOf("root")>-1){
			@SuppressWarnings("unchecked")
			List<CampusDto>campus=(List<CampusDto>) req.getSession().getAttribute("campus");
			if(null==campus){
				//查询班级校区信息数据条件
				Map<String,Object>params = new HashMap<String, Object>();
				//查询参数
				params.put("cmis30id", userDto.getCmis30id());
				params.put("discode", userDto.getDiscode());
				params.put("techerid", userDto.getTeacherid());
				params.put("levelcode", userDto.getLevelcode());
				params.put("campusid", userDto.getCampuseId());
				campus=masterAppriseExt.getClassInfos(params);
			}
			if(null!=campus && campus.size()>0){
				for(CampusDto dto:campus){
					SchoolTreeDto sk = new SchoolTreeDto();
					sk.setId(dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId());
					sk.setText(dto.getLevelName()+"-"+dto.getGradeName()+"-"+dto.getClassName());
					sk.setLeaf(false);
					sk.setExpanded(false);
					sk.setHref("javascript:parent.forwardStuInfoList('"+sk.getId()+"','"+sk.getText()+"');");
					results.add(sk);
				}
			}
		}else{
			//查询班级学生信息
			Map<String,Object>params = new HashMap<String, Object>();
			String cmis30id=userDto.getCmis30id();
			String discode=userDto.getDiscode();
			params.put("cmis30id", cmis30id);
			params.put("discode", discode); 
			String classid=nodeId.split("@")[2];
			params.put("lid", classid);
			List<SchoolTreeDto> studentInfo = masterAppriseExt.getStudentInfo(params);
			if (studentInfo != null && studentInfo.size() > 0){
				for (SchoolTreeDto sk : studentInfo){
					sk.setLeaf(true);
					sk.setHref("javascript:parent.forwardStuInfoPage('"+sk.getId()+"','"+sk.getKey()+"','"+sk.getText()+"','"+classid+"','"+sk.getEdusyId()+"');");
					results.add(sk);
				}
			}
		}
		return results;
	}
}
