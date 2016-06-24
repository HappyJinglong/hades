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

public class HighSchoolTermTag extends AbstractTagSupport{
	private final static Logger LOG = Logger.getLogger(HighSchoolTermTag.class);

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
			List<TermDto> items = termServiceExt.queryHighSchoolTerms(selectClassid,levelCode);
			if (null != items && !items.isEmpty()) {
				int maxCount=items.size()-1;
				int count=0;
				for(TermDto dto: items)    
				{   
					if(StringUtil.isEmpty(selectNum))
					{
						if(count==maxCount){
							matched=true;
						}else{
							matched=false;
							count++;
						}
					}else
					{
						if (dto.getTermid()!=null&&selectNum.equals(dto.getTermid().toString())) 
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
	//需要匹配的班级标识号，必选
	private String selectClassid = "";
	//选中指定的学期，可选
	private String selectNum="";
	//学段代码，必选
	private String levelCode="";
	
	
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(String selectNum) {
		this.selectNum = selectNum;
	}
	public String getSelectClassid() {
		return selectClassid;
	}
	public void setSelectClassid(String selectClassid) {
		this.selectClassid = selectClassid;
	}
	
	
}
