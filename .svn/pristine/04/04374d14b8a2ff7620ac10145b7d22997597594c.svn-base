package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.ITermServiceExt;

public class CourseYearTag extends AbstractTagSupport{
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			ISchoolCourseExt schoolCourseExt = (ISchoolCourseExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"schoolCourseExt");
			List<String> items = schoolCourseExt.getSchoolYears();
			String year="";
			String yearName="";
			if (null != items && !items.isEmpty()) {
				for(String strs: items) {   
					year =strs.split("@")[0]; 
					yearName =strs.split("@")[1]; 
					if (selectNum.equals(year) ){
						matched = true;
					}else {
						matched = false;
					}
					addOption(sb,year,yearName, matched);  
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	private String selectNum;
	public String getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}
}
