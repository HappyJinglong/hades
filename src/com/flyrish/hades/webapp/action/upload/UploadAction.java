package com.flyrish.hades.webapp.action.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class UploadAction{
	private Logger logger = Logger.getLogger(UploadAction.class);
	// file类型name值upload_file
	public String uuid; //uuid
	public String upload_file_name; //上传的文件名 例如a.txt
	public String upload_file_path; //存放在nginx所在服务器的路径 例如/usr/local/zeus/upload_temp/5/0025248785
	
	public String upload_cmis30_name;//综素的文件名
	public String upload_cmis30_path;
	
	public String upload_attchfile_name;//综素附件
	public String upload_attchfile_path;//
	
	public String upload_course_name;
	public String upload_course_path;//课改文件名
	
	public String old_fileField_name;//有数据时附件
	public String old_fileField_path;
	
	public String new_fileField_name;//初始化时附件
	public String new_fileField_path;
	
	public String add_fileField_name;//新加数据时附件
	public String add_fileField_path;
	
	public String new_fileField3_name;//初始化特长与成果展示 时附件
	public String new_fileField3_path;
	
	public String add_fileField3_name;//新加数据特长与成果展示 时附件
	public String add_fileField3_path;
	
	
	@Spring
	private ConstantBean constantBean;
	@Spring
	private IRedisServiceExt redisServiceExt;
	@DefaultAction
	public Object defaultProcess(HttpServletRequest request) {
		//检测是否上传成功
		if(NestUtil.isNotEmpty(upload_file_name)){
			uploadFileDone(upload_file_name,
							upload_file_path);
		}else if(NestUtil.isNotEmpty(upload_attchfile_name)){
			uploadFileDone(upload_attchfile_name,
							upload_attchfile_path);
		}else if(NestUtil.isNotEmpty(upload_cmis30_name)){
			uploadFileDone(upload_cmis30_name,
							upload_cmis30_path);
		}else if(NestUtil.isNotEmpty(upload_course_name)){
			uploadFileDone(upload_course_name,
							upload_course_path);
		}
		else if(NestUtil.isNotEmpty(old_fileField_name)){
			uploadFileDone(old_fileField_name,
					old_fileField_path);
		}
		else if(NestUtil.isNotEmpty(new_fileField_name)){
			uploadFileDone(new_fileField_name,
					new_fileField_path);
		}
		else if(NestUtil.isNotEmpty(add_fileField_name)){
			uploadFileDone(add_fileField_name,
					add_fileField_path);
		}
		else if(NestUtil.isNotEmpty(new_fileField3_name)){
			uploadFileDone(new_fileField3_name,
					new_fileField3_path);
		}
		else if(NestUtil.isNotEmpty(add_fileField3_name)){
			uploadFileDone(add_fileField3_name,
					add_fileField3_path);
		}
		return null;
	}
	private void uploadFileDone(String file_name,String file_path) {
		String[] pathStr = file_path.split("/");
		//处理文件路径
		StringBuffer filePath = new	StringBuffer();
		int n=0;
		for(int i=0;i<pathStr.length;i++){
			if(pathStr[i].equals("upload_temp")){
				n=i;
			}
		}
		filePath.append(File.separator)
				.append(pathStr[n])
				.append(File.separator)
				.append(pathStr[n+1])
				.append(File.separator)
				.append(pathStr[n+2]);//形如：\\upload_temp\\5\\0025248785 后期在处理时，与共享磁盘拼接 例如z:\\upload_temp\\5\\0025248785
		//文件名 路径
		Map<String,String> fileMap = new HashMap<String, String>();
		fileMap.put("fileName", file_name);
		fileMap.put("filePath", filePath.toString());
		//存储到redies服务器 
		try{
			redisServiceExt.save(uuid,fileMap);
		}catch(Exception e){
			logger.error("defaultProcess(HttpServletRequest)",e);
		}
	}
	public Object getUploadFileProgress(HttpServletRequest request, HttpServletResponse response){
		try{
			String uuid = request.getParameter("X-Progress-ID");
			logger.info("获取文件上传进度的uuid: " + uuid);
			URL url = new URL(constantBean.get("in_nginx_server")+"/progress?X-Progress-ID=" + uuid);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			//get the response and send this to the client 
			StringBuffer buffer = new StringBuffer();
			while(((line=br.readLine())!=null)){
				if(line.length()>0) {
					response.getOutputStream().println(line);
					buffer.append(line);
				}
			}
			logger.info("nginx文件上传进度返回: " + buffer.toString());
		} catch (IOException e) {
			logger.error("getUploadFileProgress(HttpServletRequest,HttpServletResponse)",e);
		}
		return null;

	}
}
