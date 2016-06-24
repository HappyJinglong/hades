package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.service.ext.IInnerCourseExt;

public class GradeSelectTag extends AbstractTagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = Logger.getLogger(SubjectSelectTag.class);

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("campusid", campusId);
			queryMap.put("cmis30id", cmis30Id);
			queryMap.put("levelcode", levelCode);
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			IInnerCourseExt innerCourseExt = (IInnerCourseExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"innerCourseExt");
			List<GeneralDto> generalDto = innerCourseExt.selectGread(queryMap);
			if (null != generalDto && !generalDto.isEmpty()) {
				for(GeneralDto dto: generalDto)    
				{   //初中用科目标识号
						if(StringUtil.isNotEmpty(gradeId))
						{
							if ((dto.getGradeid()+"--"+dto.getGradenum()).equals(gradeId)) 
							{
								matched = true;
							}else {
								matched = false;
							}
						}
						addOption(sb,(dto.getGradeid()+"--"+dto.getGradenum()),dto.getGradename(),matched);
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}

	private String gradeId;
	
	private String campusId;
	
	private String cmis30Id;
	
	private String levelCode;

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getCmis30Id() {
		return cmis30Id;
	}

	public void setCmis30Id(String cmis30Id) {
		this.cmis30Id = cmis30Id;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
}
