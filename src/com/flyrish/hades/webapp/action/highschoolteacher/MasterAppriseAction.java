package com.flyrish.hades.webapp.action.highschoolteacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

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
			Map<String,Object>params = new HashMap<String, Object>();
			levelCode = userDto.getLevelcode();
			session.setAttribute("levelCode", levelCode);
			//查询参数
			params.put("cmis30id", userDto.getCmis30id());
			params.put("discode", userDto.getDiscode());
			params.put("techerid", userDto.getTeacherid());
			params.put("levelcode", userDto.getLevelcode());
			params.put("campusid", userDto.getCampuseId());
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
		return "tree.jsp";
	}
	
	
}
