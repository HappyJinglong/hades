package com.flyrish.hades.webapp.action.reportBook;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;

import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class ReportBookAction extends BaseAction{
	
	
	//用户信息
		private UserDto loginInfo;
		//校区名称
		public String rootName;
		public String levelCode;
		public String commonFuncId;
		public String txetname;
		public String classid;
		public List<SchoolTreeDto> studentInfo;
		public List<CampusDto> campus;
		public JSONArray arraylist;
		public String falg;
		public String gradeId;
		public String classId;
		@Spring
		public IMasterAppriseExt masterAppriseExt;
	
		@Before
		public void initData(HttpServletRequest req){
			
			this.getLoginInfo(req);
		}

		
		
		
	@DefaultAction
	public Object toTreeMenu(HttpServletRequest req,HttpSession session,HttpServletResponse response){
		Map<String,Object>params = new HashMap<String, Object>();
		String cmis30id=userDto.getCmis30id();
		String discode=userDto.getDiscode();
		String teacherId=userDto.getTeacherid();
		String levelcode=userDto.getLevelcode();
		String campusid=userDto.getCampuseId();
		session.setAttribute("levelCode", levelcode);
		levelCode = levelcode;
		req.setAttribute("commonFuncId", commonFuncId);
		//查询参数
		params.put("cmis30id", cmis30id);
		params.put("discode", discode);
		params.put("techerid", teacherId);
		params.put("levelcode", levelcode);
		params.put("campusid", campusid);
		campus=masterAppriseExt.getClassInfos(params);
		session.setAttribute("campus", campus);
	  /*
		if(null!=campus){
			if(0<campus.size()){
				Map<String,Object>params1 = new HashMap<String, Object>();
				params1.put("cmis30id", cmis30id);
				params1.put("discode", discode); 
				String classid=campus.get(0).getClassId();
				txetname=campus.get(0).getLevelName()+campus.get(0).getGradeName()+campus.get(0).getClassName();
				params1.put("lid", classid);
				 studentInfo = masterAppriseExt.getStudentInfoXie(params1);
				 ListToJson(response,studentInfo);
			}else{
				 //return "reportBook.jsp";
			}
		}*/
	   return "/reportBook/reportBook.jsp";
		/*System.out.println("ddd");
		return null;*/
	}
	
	
	public void chanClass(HttpServletRequest req,HttpSession session,HttpServletResponse response){
		if(null!=classid){
			  String cmis30id=userDto.getCmis30id();
			    String discode=userDto.getDiscode();
				Map<String,Object>params1 = new HashMap<String, Object>();
				params1.put("cmis30id", cmis30id);
				params1.put("discode", discode); 
				params1.put("lid", classid);
				studentInfo = masterAppriseExt.getStudentInfoXie(params1);
				ListToJson(response,studentInfo);
		
		}
		
	}
	
	
	
	
	
	
	
	//将List转换为Json
	public void ListToJson(HttpServletResponse response,List list)
	{
		try {
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			arraylist=JSONArray.fromObject(list,jsonConfig);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(arraylist.toString());
		} catch (IOException e) {
			logger.error("ListToJson(HttpServletResponse)", e);
		}
	}
	class DateJsonValueProcessor implements JsonValueProcessor
	{

		public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
		
			return null;
		}
		public Object processObjectValue(String key, Object value,
				JsonConfig jsonconfig) {
			if (value == null)  
                return "";  
            // 注意：在判断几个父子级类型时要先判断子类型再判断父类型  
            if (value instanceof java.sql.Date) {  
                String str = DateUtil.dateToStr((java.util.Date) value,  
                        "yyyy-MM-dd");//这里是我封装的工具,可以使用SimpleDateFormat代替，一样  
                return str;  
            } else if (value instanceof java.sql.Timestamp  
                    || value instanceof java.util.Date) {  
                String str = DateUtil.dateToStr((java.util.Date) value,  
                        "yyyy-MM-dd");  
                return str;  
            }  
            return value.toString();  
		}
		
	}
	
	
}
