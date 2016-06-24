/**
 * 
 */
package com.flyrish.hades.tag;
 
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * Tag抽象类.
 * @author spoon
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractTagSupport extends TagSupport
{
    /**
     * Logger.
     */
    protected final static Logger LOG = Logger.getLogger(AbstractTagSupport.class);
    
    /**
     * 
     * 页面生成下拉列表框脚本.
     * @param sb
     * @param value 下拉框值.
     * @param text 下拉框显示文本.
     * @param matched 是否选中.
     */
    protected void addOption(StringBuffer sb,String value,String text,boolean matched)
    {
        sb.append("<option value=\"");
        sb.append(value);
        sb.append("\"");
        if (matched)
        {
            sb.append(" selected=\"selected\"");
        }
        sb.append("title=\"");
        sb.append(text);
        sb.append("\"");
        sb.append(">");
        if(text.length()>20)
        {
        	sb.append(text.substring(0, 20));
        }
        else
        {
        	sb.append(text);
        }
        sb.append("</option>\r\n");
    }
}
