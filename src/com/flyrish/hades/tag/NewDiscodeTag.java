package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.service.ext.IAppraisalStaticsExt;

public class NewDiscodeTag extends AbstractTagSupport {
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();			
			boolean matched = false;
			
			StringBuffer sb = new StringBuffer();
			IAppraisalStaticsExt appraisalStaticsExt = (IAppraisalStaticsExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"appraisalStaticsExt");
			//discode_discodename
			List<String> discodes =appraisalStaticsExt.queryDiscode();
			if (null != discodes && !discodes.isEmpty()) {
				for(String dis: discodes)    
				{   
					if(StringUtil.isNotEmpty(dis))
					{
						if ((discode).equals(dis.split("_")[0])) 
						{
							matched = true;
						}else {
							matched = false;
						}
					}
					addOption(sb,dis.split("_")[0],dis.split("_")[1],matched);
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	public String discode;
	public String getDiscode() {
		return discode;
	}
	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
}
