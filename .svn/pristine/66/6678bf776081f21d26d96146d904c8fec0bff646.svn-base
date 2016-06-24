package com.flyrish.hades.webapp.action.inform;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IInformQueryExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class AddInformAction extends NginxUploadAction{
	
	UserDto userDto;
	//用户类别1503
	public String usertype;
	//登录用户真实的角色类型(校级)，其他级别为null
	public String userRealtype;
	public String discode;
	//接收对象
	public String receiverObj;
	//接收学段
	public String objlevel;
	//主题
	public String theme;
	//通告内容
	public String informContent;
	//开始时间
	public String startDate;
	//结束时间
	public String endDate;
	
	public String uuid;
	//是否上传附件
	public String attachflag;
	//保存还是更新
	public String flag;
	//通告id串
	public String informIds;
	public InformDto information;
	public String trueAddress;// 文件存储真实路径
	//接收对象
    public List<String> receiverObje;
	//接收学段
	public List<String> levelObj;
	//通告列表
	public List<InformDto> informlist;
	//通告id
	public String informid;
	//页号
	public String pageNu;
	//一页多少记录
	public String pageNum;
	String personid; 
	String campusid;
	String filepath;   //文件路径
	String filename;   //文件名称
	String userid;
	public List<String> levelString = new ArrayList<String>();
	@Spring
	public IInformQueryExt informQueryExt;
	
	public String isDelAttachPath;
	@Before
	public Object beforeAddInform(HttpServletRequest req)
	{
		userDto = this.getLoginInfo(req);
		usertype = userDto.getUsertype();
		userRealtype = userDto.getUserRealType();
		userid = userDto.getUserid();
		discode = userDto.getDiscode();
		campusid = userDto.getCampuseId();
		return null;
	}
	@DefaultAction
	public void insertInform(HttpServletResponse response)
	{
		try {
			if("1".equals(attachflag))
			{
				uploadFile();
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("theme", theme);
			params.put("informContent", informContent);
			params.put("filepath", filepath);
			params.put("filename", filename);
			params.put("endtime", StringToDate(endDate));
			params.put("starttime", StringToDate(startDate));
			params.put("issue", "0");
			params.put("userid", userid);
			params.put("receiverObj", receiverObj);
			params.put("objlevel", objlevel);
			if(Constant.USER_KIND_CITY.equals(usertype))     //市级用户类型
			{
				params.put("isAll", "1");
			}else if(Constant.USER_KIND_COUNTY.equals(usertype)){      //区级用户类型
				params.put("discode", discode);
			} else if (Constant.USER_TYPE_SCHOOLADMIN.equals(userRealtype)
					|| Constant.USER_TYPE_SPORTSEMASTER.equals(userRealtype)) {   //教务和德育老师
				params.put("campusid", campusid);
			}else if(Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)){    //班主任
				params.put("campusid", campusid);
			}
			informQueryExt.insertInform(params);
			response.getWriter().write("{\"fin\":true}");
		} catch (Exception e) {
			try {
				response.getWriter().write("{\"fin\":false}");
			} catch (IOException e1) {}
			logger.error("insertInform()", e);
		}
	}
    public void updateInformContent(HttpServletResponse response)
    {
    	try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("theme", theme);
			params.put("informContent", informContent);
			params.put("endtime", StringToDate(endDate));
			params.put("startDate", StringToDate(startDate));
			params.put("receiverObj", receiverObj);
			params.put("objlevel", objlevel);
			params.put("informid", informid);
			if("1".equals(attachflag))
			{
				uploadFile();
			}
			if("1".equals(isDelAttachPath)&&NestUtil.isEmpty(filepath)){
				params.put("filepath",null);
				params.put("filename",null);
			}else if(NestUtil.isNotEmpty(filepath)){
				params.put("filepath", filepath);
				params.put("filename", filename);
			}
			informQueryExt.updateInform(params);
			response.getWriter().write("{\"fin\":true}");
		} catch (Exception e) {
			try {
				response.getWriter().write("{\"fin\":false}");
			} catch (IOException e1) {}
			logger.error("updateInformContent(HttpServletResponse)", e);
		}
    }
	// 上传附件
	@SuppressWarnings("unchecked")
	public void uploadFile() {
		
		trueAddress = File.separator+"informFile";
		
		// 根盘符
		String rootPath = constantBean.get("mapping_root");
		//
		String realPath = constantBean.get("JLD_upload");
		// 替换为当前系统支持的分隔符
		realPath = realPath.replaceAll("\\\\\\\\", File.separator);
		// 获取上传附件信息
		Map<String, Object> map = (Map<String, Object>) redisServiceExt.readMap(uuid);
		// 上传的临时路径
		String filePath = (String) map.get("filePath");
		// 临时文件的文件名
		String fileName = String.valueOf(map.get("fileName"));
		// 目标文件
		String uuid = UUID.randomUUID().toString();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		try {
			// 临时文件
			File srcFile = new File(rootPath + File.separator, filePath);
			// 目标文件
			File desFile = new File(rootPath + trueAddress, uuid + fileType);
			if (!desFile.exists()) {
				new File(desFile.getParent()).mkdirs();
				desFile.createNewFile();
			}
			NoServiceUtil.copyFile(srcFile, desFile);// copyFile();
			filename = (String) map.get("fileName");
			filepath = NoServiceUtil.replaceFileSeparator(trueAddress.replace("/", "\\\\")+ File.separator + uuid+fileType);
			// 判断目录或文件是否存在
			if (!srcFile.exists()) {
				// 不存在返回 false
			} else {
				// 判断是否为文件
				if (srcFile.isFile()) {
					deleteFile(srcFile.getPath());
				}
			}
		} catch (Exception e) {
			logger.error(
					"uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
		} finally {
			try {
				redisServiceExt.delete(uuid);
			} catch (ForceException ex) {
				logger.error(
						"uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",
						ex);
			}
		}
	}

	/**
	 * 删除临时文件
	 * 
	 * @param request
	 *            ,response,session
	 * @return
	 */
	public void deleteFile(String sPath) {
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
	/**
	 * 字符串转日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return null;
		}
	}
}
