package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.SysdictDto;
import com.flyrish.hades.service.ext.IInnerCourseExt;

public class AspectSelectTag extends AbstractTagSupport{
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			IInnerCourseExt innerCourseExt = (IInnerCourseExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"innerCourseExt");
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("dicttype", "12312");
			List<SysdictDto> items = innerCourseExt.selectAspect(queryMap);
			if (null != items && !items.isEmpty()) {
				for(SysdictDto sd: items) {   
					if (selectNum.equals(sd.getDict_id()) ){
						matched = true;
					}else {
						matched = false;
					}
					addOption(sb,sd.getDict_id(),sd.getDict_name(), matched);  
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
