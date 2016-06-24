package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;

@SuppressWarnings("serial")
public class UserSchoolTag extends AbstractTagSupport{

	

	private final static Logger LOG = Logger.getLogger(UserRolesTag.class);
	
	private String userId;
	private String selectValue = "";
	public String getSelectValue() {
		return selectValue;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Map<String,Map<UserRoleDto,Set<EdusysDto>>> dataMaps=(Map<String,Map<UserRoleDto,Set<EdusysDto>>>)pageContext.getSession().getAttribute(Constant.TYPE_CAMPUSEID_ROLE_LEVELCODE);
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			IBaseInforManagerExt baseInforManagerExt = (IBaseInforManagerExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"baseInforManagerExt");
			if(dataMaps==null)return 0;
			List<UserSchoolDto> userSchoolDtoes = baseInforManagerExt.queryCampuseByCampuseid(dataMaps.keySet());
			
			if (null != userSchoolDtoes && !userSchoolDtoes.isEmpty()) {
				for(UserSchoolDto schoolDto : userSchoolDtoes)    
				{    
					if (StringUtil.isNotEmpty(selectValue)
							&& schoolDto.getSchoolId().toString().equals(selectValue)) {
						matched = true;
					} else {
						matched = false;
					}
					addOption(sb, schoolDto.getSchoolId().toString(), schoolDto.getSchoolName(), matched);             
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
}
