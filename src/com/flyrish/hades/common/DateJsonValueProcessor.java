package com.flyrish.hades.common;

import org.nestframework.commons.utils.DateUtil;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor{
	public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
		
		return null;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonconfig) {
		if (value == null)  
            return "";  
        // 注意：在判断几个父子级类型时要先判断子类型再判断父类型  
        if (value instanceof java.sql.Date) {  
            String str = DateUtil.dateToStr((java.util.Date) value,  
                    "yyyy-MM-dd");//这里是我封装的工具,可以使用SimpleDateFormat代替，一样  
            return str;  
        } else if (value instanceof java.sql.Timestamp  
                || value instanceof java.util.Date) {  
            String str = DateUtil.dateToStr((java.util.Date) value,  
                    "yyyy-MM-dd");  
            return str;  
        }  
        return value.toString();  
	}
}
