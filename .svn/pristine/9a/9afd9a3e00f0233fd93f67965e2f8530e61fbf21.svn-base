package com.flyrish.hades.webapp.action.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nestframework.addons.spring.Spring;
import org.nestframework.data.Json;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class QueryCommentTreeNodeAction extends BaseAction{
	@Spring
	private IMasterAppriseExt masterAppriseExt;
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
		UserDto loginInfo = this.getLoginInfo(req);
		if(null==loginInfo){
			return results;
		}
		if(StringUtils.isBlank(nodeId) || nodeId.indexOf("root")>-1){
			Map<String,Object>params = new HashMap<String, Object>();
			//查询参数
			params.put("cmis30id", userDto.getCmis30id());
			params.put("discode", userDto.getDiscode());
			params.put("techerid", userDto.getTeacherid());
			params.put("levelcode", userDto.getLevelcode());
			params.put("campusid", userDto.getCampuseId());
			List<CampusDto> campus = new ArrayList<CampusDto>();
			if(Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())){
				//统计班级总人数  已经完成   未完成学生
				campus=masterAppriseExt.getClassInfos(params);
			}else if(Constant.USER_TYPE_COURSEMASTER.equals(userDto.getUserRealType())){
				campus=masterAppriseExt.getTeacherClassInfos(params);
			}
			
			if(null!=campus && campus.size()>0){
				for(int i = 0;i<campus.size();i++){
					CampusDto dto = campus.get(i);
					SchoolTreeDto sk = new SchoolTreeDto();
					sk.setId(dto.getLevelId()+"@"+dto.getGradeId()+"@"+dto.getClassId());
					sk.setText(dto.getLevelName()+"-"+dto.getGradeName()+"-"+dto.getClassName());
					sk.setLeaf(false);
					if(i==0){
						sk.setExpanded(true);
					}else{
						sk.setExpanded(false);
					}
					sk.setHref("javascript:parent.forwardStuInfoList('"+sk.getId()+"','"+sk.getText()+"');");
					results.add(sk);
				}
			}
		}else{
			//查询班级学生信息
			Map<String,Object>params = new HashMap<String, Object>();
			String cmis30id=loginInfo.getCmis30id();
			String discode=loginInfo.getDiscode();
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
