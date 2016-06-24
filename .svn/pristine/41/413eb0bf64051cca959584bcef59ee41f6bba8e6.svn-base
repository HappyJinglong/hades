package com.flyrish.hades.tag;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.flyrish.hades.common.ConstantBean;

public class AppraiserSelectTag extends AbstractTagSupport{
	/**
	 * Logger.
	 */
	private final static Logger LOG = Logger.getLogger(AppraiserSelectTag.class);

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			boolean matched = false;
			StringBuffer sb = new StringBuffer();
			ConstantBean constantBean = (ConstantBean) WebApplicationContextUtils
					.getWebApplicationContext(
							this.pageContext.getServletContext()).getBean(
							"constantBean");
			//获取配置文件里面的字典值
			String[]dicts=constantBean.get("appraiserdic").split(",");
			Map<String,String> mapDtos=new TreeMap<String,String>();
			if(dicts!=null&&dicts.length!=0){
				for(String dictname:dicts){
					String[]keyvalues=dictname.split("#");
					mapDtos.put(keyvalues[1],keyvalues[0]);
				}
			}
			for(Entry<String, String> dto:mapDtos.entrySet()){
				
				if(StringUtil.isNotEmpty(selectValue))
				{
					if (dto.getKey().toString().equals(selectValue)) 
					{
						matched = true;
					}else {
						matched = false;
					}
				}
				addOption(sb, dto.getKey().toString(), dto.getValue(), matched);             
			}
			out.println(sb.toString());
		}catch(Exception e){
			LOG.error("doEndTag()",e);
		}
		return SKIP_BODY;
	}
	//选择关系人码
	private String selectValue = "";

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
}
