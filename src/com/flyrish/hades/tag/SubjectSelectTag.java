package com.flyrish.hades.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.ISubjectServiceExt;

public class SubjectSelectTag extends AbstractTagSupport{
	private final static Logger LOG = Logger.getLogger(SubjectSelectTag.class);

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			ISubjectServiceExt subjectServiceExt = (ISubjectServiceExt) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"subjectServiceExt");
			List<SubjectDto> subjectDtos = subjectServiceExt.querySubjectDtoByLevelCode(levelCode);
			if (null != subjectDtos && !subjectDtos.isEmpty()) {
				for(SubjectDto dto: subjectDtos)    
				{   //初中用科目标识号
					if(NestUtil.isNotEmpty(levelCode)&&String.valueOf(Constant.DICT_TYPE_LEVELCODE_CZ).equals(levelCode)){
						if(StringUtil.isNotEmpty(subjectId))
						{
							if (dto.getSubjectid().equals(subjectId)) 
							{
								matched = true;
							}else {
								matched = false;
							}
						}
						addOption(sb,dto.getSubjectid(),dto.getSubjectName(),matched);
					}
					//如果是高中，则用科目名称
					if(NestUtil.isNotEmpty(levelCode)&&(String.valueOf(Constant.DICT_TYPE_LEVELCODE_GZ).equals(levelCode)
							||String.valueOf(Constant.DICT_TYPE_LEVELCODE_GZYK).equals(levelCode))){
						if(StringUtil.isNotEmpty(subjectName))
						{
							if (dto.getSubjectName().equals(subjectName)) 
							{
								matched = true;
							}else {
								matched = false;
							}
						}
						addOption(sb,dto.getSubjectName(),dto.getSubjectName(),matched);
					}
				} 
			}
			out.println(sb.toString());
		} catch (IOException e) {
			LOG.error("doEndTag()", e);
		}
		return SKIP_BODY;
	}
	//学段代码（必填字段）
	private String levelCode;
	//科目标识号（非必填字段，初中专用）
	private String subjectId;
	//科目名称（非必填字段，高中专用）
	private String subjectName;
	
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
}
