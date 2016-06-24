package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;

@SuppressWarnings("serial")
public class UserRolesTag extends AbstractTagSupport{

	
	private final static Logger LOG = Logger.getLogger(UserRolesTag.class);
	
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
			List<UserRoleDto> userRoleDtoes = baseInforManagerExt.queryUserRoleByUserId(userId,campuseId);
			if (null != userRoleDtoes && !userRoleDtoes.isEmpty()) {
				for(UserRoleDto roleDto : userRoleDtoes)    
				{    
					if (StringUtil.isNotEmpty(selectRoleId)
							&& roleDto.getRoleId().toString().equals(selectRoleId)) {
						matched = true;
					} else {
						matched = false;
					}
					addOption(sb, roleDto.getRoleId().toString(), roleDto.getRoleName(), matched);             
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	//用户标识号（必填字段）
	private String userId;
	//校区标识号（非必填字段）
	private String campuseId;
	//角色标识号（非必填字段）
	private String selectRoleId = "";
	
	public String getSelectRoleId() {
		return selectRoleId;
	}

	public void setSelectRoleId(String selectRoleId) {
		this.selectRoleId = selectRoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCampuseId() {
		return campuseId;
	}

	public void setCampuseId(String campuseId) {
		this.campuseId = campuseId;
	}
	
}
