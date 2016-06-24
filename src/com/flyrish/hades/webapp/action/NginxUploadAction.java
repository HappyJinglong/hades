package com.flyrish.hades.webapp.action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.NoServiceUtil;

public abstract class NginxUploadAction<E> extends PageAction<E> {
	public String uuid;    //前台生成的随机id
	public String fileName;//上传的文件名
	public String filePath;//上传的文件路径 nginx所在服务器 临时路径 相对 例如\\upload_temp\\0\\0129109(单个分隔符)
	public String nginxServer;//nginx请求服务器地址
	//业务处理后的存储路径
	public String dbPath; //入库的路径
	@Spring
	public IRedisServiceExt redisServiceExt;
	@Spring
	public ConstantBean constantBean;
	@Before
	public void init(){
		nginxServer=constantBean.get("nginx_server");
	}
	//
	/**
	 * 获取上传文件信息
	 */
	@SuppressWarnings("unchecked")
	public void uploadFileInfo(){
		//uuid不为空 且 通过uuid获取路径信息不为空
		if(StringUtils.isNotEmpty(uuid) && null !=redisServiceExt.readMap(uuid)){
			//获取memcached里的路径集合
			Map<String,String> map =redisServiceExt.readMap(uuid);
			fileName = String.valueOf(map.get("fileName"));
			filePath = String.valueOf(map.get("filePath"));
		}
	}
	/**
	 * copy文件到正式目录 
	 * @param rootPath
	 * @param savePath
	 * @return
	 */
	public boolean doCopyFile(String rootPath,String savePath){
		try{
			uploadFileInfo();
			//处理保存
			if(StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(filePath)){
				//判断路径为空
				if(StringUtils.isEmpty(rootPath) || StringUtils.isEmpty(savePath)){
					throw new NullPointerException("根目录或存储路径为空!");
				}
				//替换目录 为 当前支持分隔符
				rootPath = NoServiceUtil.replaceFileSeparator(rootPath);
				savePath = NoServiceUtil.replaceFileSeparator(savePath);
				String uuid=NoServiceUtil.getGuid();
				//临时文件的文件名
				String tempFileName = uuid+fileName.substring(fileName.lastIndexOf("."));
				//临时文件
				File srcFile = new File(rootPath+filePath);
				//目标文件
				File desFile = new File(rootPath+savePath,tempFileName);
				if(!desFile.exists()){
					new File(desFile.getParent()).mkdirs();
					desFile.createNewFile();
				}
				//执行copy操作
				NoServiceUtil.copyFile(srcFile, desFile);
				//
				this.dbPath = savePath+tempFileName;
			}
			return true;
		}catch(Exception e){
			logger.error("doCopyFile(String,String)",e);
			return false;
		}
	}
	
	public String fileName() {
		uploadFileInfo();
		return fileName;
	}

	public String filePath() {
		uploadFileInfo();
		return filePath;
	}

	public void  nginxServer(){
		nginxServer = constantBean.get("nginx_server");
	}
	public String getDbPath() {
		return dbPath;
	}
}
