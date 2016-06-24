package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.service.ext.ISysSubjectServiceExt;

public class NewSysSubjectSelectTag extends AbstractTagSupport{
	private final static Logger LOG = Logger.getLogger(SubjectSelectTag.class);

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			ISysSubjectServiceExt sysSubjectServiceExt = (ISysSubjectServiceExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"sysSubjectServiceExt");
			List<KsysSubjectDto> subjectDtos = sysSubjectServiceExt.querySysSubject();
			if (null != subjectDtos && !subjectDtos.isEmpty()) {
				for(KsysSubjectDto dto: subjectDtos)    
				{   //初中用科目标识号
						if(StringUtil.isNotEmpty(subjectId))
						{
							if (dto.getSubject_id().equals(subjectId)) 
							{
								matched = true;
							}else {
								matched = false;
							}
						}
						addOption(sb,dto.getSubject_id(),dto.getSubject_name(),matched);
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}

	private String subjectId;
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
}
