package com.flyrish.hades.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.ConstantBean;

public class DataSourceAspect {
	private Logger logger = Logger.getLogger(DataSourceAspect.class);
	private ConstantBean constantBean;
	
	public ConstantBean getConstantBean() {
		return constantBean;
	}

	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}

	public void before(JoinPoint point){
        Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if(m != null && m.isAnnotationPresent(Redis.class))
            	return;
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
            	DataSource data = m.getAnnotation(DataSource.class);
            	if(NestUtil.isNotEmpty(data.value())&&("read".equals(data.value().toLowerCase()))){
            		String[] slaves=constantBean.get("db.slavedatasources").split(",");
            		if(slaves.length>0){
            			//如果配置了从节点，则动态选择配置的从节点
	            		int index=(int)(Math.round(Math.random()*100)%slaves.length);
	            		DynamicDataSourceHolder.putDataSource(slaves[index]);
            		}else{
            			//如果只有主节点，则默认选择主节点
            			DynamicDataSourceHolder.putDataSource("master");
            		}
            	}else{
            		DynamicDataSourceHolder.putDataSource("master");
            	}
            }else if(m != null){
            	DynamicDataSourceHolder.putDataSource("master");
            }
        } catch (Exception e) {
        	logger.error("before(JoinPoint)",e);
        	//如果抛出任何异常，则将数据源赋予默认的主节点
        	DynamicDataSourceHolder.putDataSource("master");
        }
    }
}
