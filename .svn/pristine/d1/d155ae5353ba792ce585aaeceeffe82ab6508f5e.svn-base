package com.flyrish.hades.webapp.action.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class MasterAppriseAction extends BaseAction{
	//用户信息
	private UserDto loginInfo;
	//校区名称
	public String rootName;
	//学段
	public String levelCode;
	
	public String commonFuncId;
	@Spring
	public IMasterAppriseExt masterAppriseExt;
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
	}
	/**
	 * 	跳转至加载树结构页面
	 * @param req
	 * @return 菜单页面
	 */
	@DefaultAction
	public Object toTreeMenu(HttpServletRequest req,HttpSession session){
		//获取当前年级班级
		try {
			session.removeAttribute("campus");
			Map<String,Object>params = new HashMap<String, Object>();
			levelCode = userDto.getLevelcode();
			session.setAttribute("levelCode", levelCode);
			//查询参数
			params.put("cmis30id", userDto.getCmis30id());
			params.put("discode", userDto.getDiscode());
			params.put("techerid", userDto.getTeacherid());
			params.put("levelcode", userDto.getLevelcode());
			params.put("campusid", userDto.getCampuseId());
			req.setAttribute("commonFuncId", commonFuncId);
			List<CampusDto>campus=masterAppriseExt.getClassInfos(params);
			if(null!=campus && campus.size()>0){
				//老师管线年级班级信息
				session.setAttribute("campus", campus);
				//老师姓名
				session.setAttribute("teacherName", campus.get(0).getTeacherName());
			}
			return "treemenu.jsp";
		} catch (Exception e) {
			logger.error("toTreeMenu(HttpServletRequest,HttpSession)", e);
			return "error.jsp";
		}
	}
	/**
	 * 跳转至左侧菜单
	 * @return 左侧菜单栏
	 * @param req
	 */
	public Object toTreePage(HttpServletRequest req){
/*		if(null==loginInfo){
			return "/login.jsp";
		}*/
		return "tree.jsp";
	}
	
	public Object queryCommentTreeMenu(HttpServletRequest req,HttpSession session){
		
		//获取当前年级班级
		try {
			levelCode = userDto.getLevelcode();
			session.setAttribute("levelCode", levelCode);
			Map<String,Object>params = new HashMap<String, Object>();
			//查询参数
			params.put("cmis30id", userDto.getCmis30id());
			params.put("discode", userDto.getDiscode());
			params.put("techerid", userDto.getTeacherid());
			params.put("levelcode", userDto.getLevelcode());
			params.put("campusid", userDto.getCampuseId());
			List<CampusDto> campus = new ArrayList<CampusDto>();
			
			req.setAttribute("commonFuncId", commonFuncId);
			
			if(Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())){
				//统计班级总人数  已经完成   未完成学生
				campus=masterAppriseExt.getClassInfos(params);
			}else if(Constant.USER_TYPE_COURSEMASTER.equals(userDto.getUserRealType())){
				campus=masterAppriseExt.getTeacherClassInfos(params);
			}
			if(null!=campus && campus.size()>0){
				//老师管线年级班级信息
				session.setAttribute("campus", campus);
				//老师姓名
				session.setAttribute("teacherName", campus.get(0).getTeacherName());
			}
			return "queryCommentTreeMenu.jsp";
		} catch (Exception e) {
			return "error.jsp";
		}
	}
	
	public Object queryCommentTreePage(HttpServletRequest req){

		return "querycommentTree.jsp";
	}
	
	/**
	 * 访问action前执行动作
	 * @param req
	 */
/*	@Before
	public void beforAction(HttpServletRequest req){
		//获取用户信息
		loginInfo = this.getLoginInfo(req);
	}*/
}
