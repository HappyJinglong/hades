package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.utils.NestUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;

public class EdusysSelectTag extends AbstractTagSupport{
private final static Logger LOG = Logger.getLogger(EdusysSelectTag.class);
private String unitlevel;
private String userId;
private String roleType;
	
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			IBaseInforManagerExt baseInforManagerExt = (IBaseInforManagerExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"baseInforManagerExt");
			List<EdusysDto> edusysDtos = baseInforManagerExt.queryEdusysDtoByInfo(unitlevel,userId,roleType,campuseId,roleId);
			if (null != edusysDtos && !edusysDtos.isEmpty()) {
				for(EdusysDto edusysDto : edusysDtos)    
				{    
					if (NestUtil.isNotEmpty(selectValue)
							&& edusysDto.getEdusysId().toString().equals(selectValue)) {
						matched = true;
					} else {
						matched = false;
					}
					addOption(sb,edusysDto.getEdusysId().toString(),edusysDto.getEdusysName(),matched);             
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	private String campuseId;
	private String selectValue;
	private String roleId;
	
	
	
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getCampuseId() {
		return campuseId;
	}
	public void setCampuseId(String campuseId) {
		this.campuseId = campuseId;
	}
	public String getSelectValue() {
		return selectValue;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	public String getUnitlevel() {
		return unitlevel;
	}
	public void setUnitlevel(String unitlevel) {
		this.unitlevel = unitlevel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
}
