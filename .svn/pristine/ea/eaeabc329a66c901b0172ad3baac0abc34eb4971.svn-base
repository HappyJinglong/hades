package com.flyrish.hades.webapp.action.jwReportBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;

import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class JwReportBookAction extends BaseAction{
	    //用户信息
		private UserDto loginInfo;
		//校区名称
		public String rootName;
		public String qdxueduan;
		public String levelCode;
		public String commonFuncId;
		public String txetname;
		public String classid;
		public String marker;
		public List<SchoolTreeDto> studentInfo;
		public List<CampusDto> campus;
		//是否可上报
		public String isSB="0";
		public JSONArray arraylist;
		@Spring
		public IMasterAppriseExt masterAppriseExt;
	
		@Before
		public void initData(HttpServletRequest req){
			
			this.getLoginInfo(req);
		}
	public String chanClass(HttpServletRequest req,HttpSession session,HttpServletResponse response){
		    String cmis30id=userDto.getCmis30id();
		    String discode=userDto.getDiscode();
			Map<String,Object>params1 = new HashMap<String, Object>();
			params1.put("cmis30Id", cmis30id);
			params1.put("discode", discode); 
			params1.put("lid", classid);
		
		if(null!=classid && marker !=null){
			if("0".equals(marker)){
				studentInfo = masterAppriseExt.getStudentInfoXie(params1);
				
			}else if("1".equals(marker)){
				params1.put("report_status", "1300001");
				studentInfo = masterAppriseExt.getStudentInfoXieBook(params1);
			}else if("2".equals(marker)){
				studentInfo = masterAppriseExt.getStudentInfoXieBookNull(params1);
				
			}else if("3".equals(marker)){
				studentInfo = masterAppriseExt.getStudentInfoXieBookSueed(params1);
			}
		}
		if(null!=qdxueduan&&""!=qdxueduan){
			 String terid=userDto.getTermId().substring(0,4);
			 Integer ter=Integer.valueOf(terid);
			 ter= ter+1;
			 ter.toString();
			 if( ter.toString().equals(qdxueduan)){
				 //当前学年20151
				 isSB="1";
			  }
		}
	
		return "jwReportBook.jsp";
		
	}

	
	
	
}
