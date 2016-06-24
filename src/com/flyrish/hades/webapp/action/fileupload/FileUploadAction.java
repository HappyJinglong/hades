package com.flyrish.hades.webapp.action.fileupload;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.nestframework.action.DownloadItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CountryInfoDto;
import com.flyrish.hades.dto.HuploadOldfile;
import com.flyrish.hades.dto.SchoolUploadInfoDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IDoWithFileServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class FileUploadAction extends NginxUploadAction{
	//文件名称
	public String filename;
	//上传文件类型
	public String uploadfiletype;
	//学校代码
	public String schoolcode;
	//查询的文件类型
	public String db;
	//区县代码
	public String discode;
	//区县名称
	public String discodename;
	//登录用户所在区县的区县代码
	public String mydiscode;
	//查询的状态 1代码已上传，0代表未上传或者上传失败
	public String isupload;
	//密文
	public String md5;
	
	private String userType;//用户类型
	
	public List<CountryInfoDto> countryInfoDtos;//统计信息集合
	
	@Spring
	public IDoWithFileServiceExt doWithFileServiceExt;
	//学校上传信息集合
	public List<SchoolUploadInfoDto>schoolUploadInfoDtos;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Json
	public Object startUploadFileInfo(){
		boolean issuccess=doWithFileServiceExt.doWithStartUploadFile(uuid, filename, schoolcode, uploadfiletype);
		//缓存里获取上传文件的数量
		Integer uploadfilecount=redisServiceExt.readSingle("uploadfilecount");
		if(uploadfilecount==null){
			uploadfilecount=1;
		}else{
			uploadfilecount++;
		}
		try{
			redisServiceExt.save("uploadfilecount",uploadfilecount);
		}catch(Exception ex){
			logger.error("startUploadFileInfo()",ex);
		}
		return JSONObject.fromObject("{val:"+issuccess+"}");
	}
	@Json
	public Object queryUploadFileCounts(){
		//限制上传的次数
		Integer uploadfilelimitcount=Integer.valueOf(constantBean.get("uploadfilecount"));
		//当前上传文件个数
		Integer uploadfilenowCount=redisServiceExt.readSingle("uploadfilecount");
		logger.error("当前文件连接数量:"+uploadfilenowCount);
		if(uploadfilenowCount==null||uploadfilenowCount!=null&&uploadfilenowCount<uploadfilelimitcount){
			return JSONObject.fromObject("{val:"+true+"}");
		}else{
			return JSONObject.fromObject("{val:"+false+"}");
		}
	}
	@Json
	public Object lessOneUploadFileCounts(){
		//当前上传文件个数
		Integer uploadfilenowCount=redisServiceExt.readSingle("uploadfilecount");
		if(uploadfilenowCount==null||uploadfilenowCount<=0){
			uploadfilenowCount=0;
		}else{
			uploadfilenowCount--;
		}
		try {
			redisServiceExt.save("uploadfilecount",uploadfilenowCount);
		} catch (ForceException e) {
			logger.error("lessOneUploadFileCounts()",e);
		}
		return null;
	}
	@Json
	public Object endUploadFileInfo(){
		List<String>datalist=new ArrayList<String>();
		boolean issuccess=false;
		try{
			Integer uploadfilecount=redisServiceExt.readSingle("uploadfilecount");
			if(uploadfilecount==null||uploadfilecount<=0){
				uploadfilecount=0;
			}else{
				uploadfilecount--;
			}
			redisServiceExt.save("uploadfilecount",uploadfilecount);
			String filepath=uploadFile();
			if(NestUtil.isNotEmpty(filepath)){
				issuccess=doWithFileServiceExt.doWithEndUploadFileAll(schoolcode, uploadfiletype, uuid, filepath);
			}
		}catch(Exception ex){
			logger.error("endUploadFileInfo()",ex);
		}
		//不管更新成功或者失败，都应该回显页面信息
		List<HuploadOldfile> huploadOldfiles=doWithFileServiceExt.queryHuploadOldfileList(schoolcode);
		 if(huploadOldfiles!=null){
			 for(HuploadOldfile huploadOldfile:huploadOldfiles){
				 Map<String,Object> datamap=new HashMap<String,Object>();
				 datamap.put("fileName", huploadOldfile.getFileName());
				 datamap.put("state", huploadOldfile.getState());
				 datamap.put("uploadTime", huploadOldfile.getUploadTime());
				 datalist.add(Utility.createJsonStr(datamap));
			 }
		 }
		 return JSONObject.fromObject("{val:"+Utility.createJsonStr(datalist)+",issucess:"+issuccess+"}");
	}
	 @SuppressWarnings("unchecked")
	 private String uploadFile(){
		 //根盘符
		 /*String rootPath = constantBean.get("root_path");
		 String savePath=constantBean.get(uploadfiletype);
		 //替换目录 为 当前支持分隔符
		 rootPath = NoServiceUtil.replaceFileSeparator(rootPath);
		 savePath = NoServiceUtil.replaceFileSeparator(savePath);*/
	    try{	
			uploadFileInfo();
					//处理保存
			if(StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(filePath)){
					//判断路径为空
				/*if(StringUtils.isEmpty(rootPath) || StringUtils.isEmpty(savePath)){
							throw new NullPointerException("根目录或存储路径为空!");
					}*/
				//临时文件
				//File srcFile = new File(rootPath+filePath);
				//目标文件
				/*String uuid=UUID.randomUUID().toString();
				String fileType=fileName.substring(fileName.lastIndexOf("."));*/
				//File desFile = new File(rootPath+savePath,schoolcode+"_"+uuid+fileType);
				/*if(!desFile.exists()){
					new File(desFile.getParent()).mkdirs();
				}*/
				//执行copy操作
				//NoServiceUtil.copyFile(srcFile, desFile);
				//获取文件Md5加密后的密文
				//md5=NoServiceUtil.getMD5(desFile);
				return NoServiceUtil.replaceFileSeparator(filePath);
			}
			return null;
			} catch (Exception e) {
				throw new ManagerException(e);
			}finally{
				//删除临时文件
				//deleteFile(rootPath+filePath);
				try{
					redisServiceExt.delete(uuid);
				}catch(ForceException ex){
					logger.error("uploadFile()",ex);
				}
			}
		}
	 public void deleteFile(String sPath) {  
		 	File file = new File(sPath);  
		    // 路径为文件且不为空则进行删除  
		    if (file.isFile() && file.exists()) {  
		        file.delete();  
		    }  
		}
	 @Json
	 public Object queryHistoryUploadFile(){
		 List<HuploadOldfile> huploadOldfiles=doWithFileServiceExt.queryHuploadOldfileList(schoolcode);
		 if(huploadOldfiles==null) return null;
		 List<String>datalist=new ArrayList<String>();
		 for(HuploadOldfile huploadOldfile:huploadOldfiles){
			 Map<String,Object> datamap=new HashMap<String,Object>();
			 datamap.put("fileName", huploadOldfile.getFileName());
			 datamap.put("state", huploadOldfile.getState());
			 datamap.put("uploadTime", huploadOldfile.getUploadTime());
			 datalist.add(Utility.createJsonStr(datamap));
		 }
		 return JSONObject.fromObject("{val:"+Utility.createJsonStr(datalist)+"}");
	 }
	 public Object downloadFile(HttpServletRequest req){
		 	String filePath=req.getSession().getServletContext().getRealPath(Constant.DOWNLOAD_FILEPATH);
			DownloadItem downloadItem = null;
			if(StringUtils.isNotBlank(filePath)){
				String realPath  =  NoServiceUtil.replaceFileSeparator(filePath);
				File file = new File(realPath);
				if(file.exists()){
					downloadItem = new DownloadItem(file);
					String fileName = NoServiceUtil.fileNameForEveryBrower(req,Constant.DOWNLOAD_FILENAME);
					downloadItem.setFilename(fileName);
				}
			}
			return downloadItem;
	    }
	 public Object queryStudentContent(HttpServletRequest request){
			 schoolUploadInfoDtos=baseInforManagerExt.querySchoolUploadInfoDtoByCondition(db,discode,isupload);
			 return "schoolcontents.jsp";
	 }
	 public Object queryRecodeInfo(HttpServletRequest request){
			 countryInfoDtos=baseInforManagerExt.queryCountryInfoDtoByDiscode(discode);
			 return "cityandcountry.jsp";
	 }
}
