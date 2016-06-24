package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;

public class NewGradeYearTag extends AbstractTagSupport {
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
			String levelCode = userDto.getLevelcode();
			String currentTermId=userDto.getTermId();
			if(NestUtil.isEmpty(currentTermId)) return SKIP_BODY;
			String currentTermYear=currentTermId.substring(0,4);
			Integer levelLength=3;
			if("2012002".equals(levelCode)){
				levelLength =4;
			}
			List<String> termInfos =new ArrayList<String>();
			for(int i=1;i<=levelLength;i++){
				termInfos.add((Integer.parseInt(currentTermYear)+i)+"");
			}
			if (null != termInfos && !termInfos.isEmpty()) {
				for(String termInfo: termInfos)    
				{   
					if(StringUtil.isNotEmpty(gradeYear))
					{
						if ((termInfo).equals(gradeYear)) 
						{
							matched = true;
						}else {
							matched = false;
						}
					}
					addOption(sb,termInfo,termInfo,matched);
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	public String gradeYear;
	public String getGradeYear() {
		return gradeYear;
	}
	public void setGradeYear(String gradeYear) {
		this.gradeYear = gradeYear;
	}
	
}
