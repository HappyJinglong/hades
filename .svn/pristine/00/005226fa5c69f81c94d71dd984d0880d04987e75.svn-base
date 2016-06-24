package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;

public class NewTermIdsTag extends AbstractTagSupport{
	private final static Logger LOG = Logger.getLogger(SubjectSelectTag.class);
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpSession session = pageContext.getSession();
			if(null==session)return SKIP_BODY;
			UserDto userDto = (UserDto)session.getAttribute(Constant.KEY_LOGIN_USER);
			if(null==userDto)return SKIP_BODY;
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			IAppraisalStaticsExt appraisalStaticsExt = (IAppraisalStaticsExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"appraisalStaticsExt");
			String levelCode = userDto.getLevelcode();
			if(!"2012002".equals(levelCode)){
				levelCode = "";
			}
			List<String> termInfos = appraisalStaticsExt.queryTermIds2(levelCode, userDto.getLevelid(), userDto.getUserRealType());
			if (null != termInfos && !termInfos.isEmpty()) {
				for(String termInfo: termInfos)    
				{   //初中用科目标识号
						if(StringUtil.isNotEmpty(termId))
						{
							if ((null==termInfo?"":termInfo.split("@")[0]).equals(termId)) 
							{
								matched = true;
							}else {
								matched = false;
							}
						}
						addOption(sb,termInfo.split("@")[0],termInfo.split("@")[1],matched);
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	public String termId;
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
}
