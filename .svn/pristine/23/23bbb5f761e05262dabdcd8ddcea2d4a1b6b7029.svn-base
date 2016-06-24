package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.service.ext.ITermServiceExt;


@SuppressWarnings("serial")
public class SelectSchoolYear extends AbstractTagSupport{

	/**
	 * Logger.
	 */
	private final static Logger LOG = Logger.getLogger(SelectSchoolYear.class);
	
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			ITermServiceExt termServiceExt = (ITermServiceExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"termServiceExt");
			List<TermDto> items = termServiceExt.queryEschoolYears();
			if (null != items && !items.isEmpty()) {
				for(TermDto dto: items)    
				{    
					if(StringUtil.isEmpty(selectValue))
					{
						if (1== dto.getIsNow()) 
						{
							matched = true;
						} else {
							matched = false;
						}
					}else
					{
						if (dto.getTermid().toString().equals(selectValue)) 
						{
							matched = true;
						}else {
							matched = false;
						}
					}
					addOption(sb, dto.getTermid().toString(), dto.getTermname(), matched);             
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	
	private String selectValue = "";

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
}
