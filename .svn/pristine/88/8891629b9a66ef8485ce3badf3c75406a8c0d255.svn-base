package com.flyrish.hades.util;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.nestframework.utils.NestUtil;

public class DynamicDataSourceHolder {
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();
	private static final Logger logger=Logger.getLogger(DynamicDataSourceHolder.class);
    public static void putDataSource(String name) {
        holder.set(name);
    }
    public static String getDataSouce() {
    	logger.info("当前连接的数据库："+(NestUtil.isEmpty(holder.get())?"master":holder.get()));
        return holder.get();
    }
}
