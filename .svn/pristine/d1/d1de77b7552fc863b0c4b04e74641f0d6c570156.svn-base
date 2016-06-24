package com.flyrish.hades.webapp.action.inform;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.nestframework.action.DownloadItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt1;
import com.flyrish.hades.service.ext.IInformQueryExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class InformAction extends NginxUploadAction{
	
	String userid;
	UserDto userDto;
	//返回
	public String isBack;
	//是否插入
	public String isInsert;
	//用户类别1503
	public String usertype;
	//登录用户真实的角色类型(校级)，其他级别为null
	public String userRealtype;
	//通告id串
	public String informIds;
	//主题
	public String theme;
	//发布状态
	public String publicState;
	//发布时间
	public String startDate;
	public String endDate;
	//页号
	public String pageNu;
	//一页多少记录
	public String pageNum;
	public int pageNu1;
	//一页多少记录
	public int pageNum1;
	//提示标识号
    public String noticeflag1;
	//接收对象
    public List<String> receiverObje;
	//接收学段
	public List<String> levelObj;
	public InformDto information;
	public List<String> levelString = new ArrayList<String>();
	//通告列表
	public List<InformDto> informlist;
	public String cmis30id;
	public String discode;
	public String flag;
	//文件名称
	public String filename;
	//文件路径
	public String filepath;
	//保存信息提示
	public String noticeflag;
	
	String personid; 
	String campusid;
	@Spring
	public IInformQueryExt informQueryExt;
	@Spring
	public IInformDoQueAndCacheExt1 informDoQueAndCacheExt1;
	
	@Spring
	public IInformDoQueAndCacheExt informDoQueAndCacheExt;
	@Before
	public Object beforeInform(HttpServletRequest req)
	{
		userDto = this.getLoginInfo(req);
		usertype = userDto.getUsertype();
		userid = userDto.getUserid();
		cmis30id = userDto.getCmis30id();
		discode = userDto.getDiscode();
		campusid = userDto.getCampuseId();
		userRealtype = userDto.getUserRealType();
		return null;
	}
	@DefaultAction
	public Object information()
	{
		return "tzgg.jsp";
	}
	public Object AddInform()
	{
		pageNum1 = pageSize;
		pageNu1 = pageNo;
		if(Constant.USER_KIND_CITY.equals(usertype))     //市级用户类型
		{
			levelString.add(Constant.DICT_TYPE_LEVELCODE_CZSTR);
			levelString.add(Constant.DICT_TYPE_LEVELCODE_GZSTR);
		}else if(Constant.USER_KIND_COUNTY.equals(usertype)){      //区级用户类型
			levelString.add(Constant.DICT_TYPE_LEVELCODE_CZSTR);
			levelString.add(Constant.DICT_TYPE_LEVELCODE_GZSTR);
		} else if (Constant.USER_TYPE_SCHOOLADMIN.equals(userRealtype)
				|| Constant.USER_TYPE_SPORTSEMASTER.equals(userRealtype)) {   //教务和德育老师
			levelString = informQueryExt.schoolQuerylevel(cmis30id, discode,campusid);
			if(levelString.contains(Constant.DICT_TYPE_LEVELCODE_GZSTR)&&levelString.contains(Constant.DICT_TYPE_LEVELCODE_GZYKSTR))
			{
				levelString.remove(Constant.DICT_TYPE_LEVELCODE_GZYKSTR);
			}
		}else if(Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)){    //班主任
			personid = userDto.getPersonid();
			levelString = informQueryExt.masterQuerylevel(personid);
			if(levelString.contains(Constant.DICT_TYPE_LEVELCODE_GZSTR)&&levelString.contains(Constant.DICT_TYPE_LEVELCODE_GZYKSTR))
			{
				levelString.remove(Constant.DICT_TYPE_LEVELCODE_GZYKSTR);
			}
		}
		return "tzgg_add.jsp";
	}
	
	public Object updateInform()
	{
		try {
			pageNum1 = pageSize;
			pageNu1 = pageNo;
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("informid", informIds);
			informlist = informQueryExt.informlistQuery(params);
			information = informlist.get(0);
			List<String> receiverCode =Arrays.asList(information.getReceiverObj().split(","));
			receiverObje = new ArrayList<String>(receiverCode);
			receiverObje.remove(Constant.USER_TYPE_SPORTSEMASTER);
			List<String> levelCode =Arrays.asList(information.getObjlevel().split(","));
			levelObj = new ArrayList<String>(levelCode);
			levelObj.remove(Constant.DICT_TYPE_LEVELCODE_GZSTR);
		} catch (Exception e) {
			logger.error("updateInform()", e);
		}
		return AddInform();
	}
	/**
	 * 将接收对象的码值对应到汉字
	 */
	@SuppressWarnings("unchecked")
	private void Convert() {
		if (pageObj != null && pageObj.getTotalCount() > 0) {
			List<InformDto> informlist=(List<InformDto>)pageObj.getPageElements();
			for (InformDto inform : informlist) {

				if (NestUtil.isNotEmpty(inform.getReceiverObj())) {

					String[] receiverCode = inform.getReceiverObj().split(",");
					List<String> receiverlist = Arrays.asList(receiverCode);
					List<String> arrayList = new ArrayList<String>(receiverlist);
					arrayList.remove(Constant.USER_TYPE_SPORTSEMASTER);
					List<String> list = new ArrayList<String>();
					for (String code : arrayList) {
						if (code.equals(Constant.USER_KIND_COUNTY)) {
							list.add(Constant.APPRASER_DISCODE);
						}
						if (code.equals(Constant.USER_TYPE_SCHOOLADMIN)) {
							list.add(Constant.APPRASER_SCHOOL);
						}
						if(Constant.USER_KIND_CITY.equals(usertype)||Constant.USER_KIND_COUNTY.equals(usertype))
						{
							if (code.equals(Constant.USER_TYPE_CLASSMASTER)) {
								list.add(Constant.APPRASER_TEACHER);
							}
						}else{
							if (code.equals(Constant.USER_TYPE_CLASSMASTER)) {
								list.add(Constant.MASTER_DICT_CZ);
							}
							if (code.equals(Constant.USER_TYPE_COURSEMASTER)) {
								list.add(Constant.TEACHER_DICT_CZ);
							}
						}
						if (code.equals(Constant.USER_TYPE_PARENT)) {
							list.add(Constant.APPRASER_PARENT);
						}
						if (code.equals(Constant.USER_TYPE_STUDENT)) {
							list.add(Constant.APPRASER_STUDENT1);
						}
					}
					String receiverName = StringUtils.join(list.toArray(), "、");
					inform.setReceiverName(receiverName);
				}
		}
		}
	}
	/**
	 * 删除通告
	 */
	public void deleteInform(HttpServletResponse response)
	{
		try {
			String[] Ids = informIds.split(",");
			List<String> idList = Arrays.asList(Ids);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("informIds", idList);
			informQueryExt.deleteInform(params);
			response.getWriter().write("{\"fin\":true}");
			} catch (Exception e) {
				try {
					response.getWriter().write("{\"fin\":false}");
				} catch (IOException e1) {}
				logger.error("deleteInform(HttpServletResponse)", e);
		}
	}
	/**
	 * 更新通告发布状态
	 * @param response
	 */
	public void publishInform(HttpServletResponse response)
	{
		try {
			String[] Ids = informIds.split(",");
			List<String> idList = Arrays.asList(Ids);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("informIds", idList);
			params.put("publishState", "1");
			informQueryExt.publicInform(params);
			response.getWriter().write("{\"fin\":true}");
			Map<String,Object> para = new HashMap<String,Object>();
			para.put("informIds", idList);
			para.put("publishState", 1);
			para.put("oderbyC", "1");
			informlist = informQueryExt.informlistQuery(para);
			
			if("1".equals(constantBean.get("isCache"))&&informlist!=null&&informlist.size()>0)
			{
				for(InformDto inform : informlist)
				{
					try{
						//如果添加到队列，因各种原因导致异常，可以不做任何处理
						informDoQueAndCacheExt.putInformToQueue(inform);
					}catch(Exception e){
						logger.error("publishInform(HttpServletResponse)",e);
					}
				}
			}
			} catch (Exception e) {
				try {
					response.getWriter().write("{\"fin\":false}");
				} catch (IOException e1) {}
				logger.error("deleteInform(HttpServletResponse)", e);
		}
	}
	/**
	 * 根据条件查询通告
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public Object queryInformByCon(HttpServletResponse response)
	{
		try {
			if(NestUtil.isNotEmpty(pageNu))
			{
				pageNo = Integer.parseInt(pageNu);
			}
			if(NestUtil.isNotEmpty(pageNum))
			{
				pageSize = Integer.parseInt(pageNum);
			}
			Map<String,Object> params = new HashMap<String,Object>();
			String endtime = "";
			String starttime = "";
			if(NestUtil.isNotEmpty(endDate))
			{
				endtime = endDate+" 23-59-59";
			}
			if(NestUtil.isNotEmpty(startDate))
			{
				starttime = startDate+" 00-00-00";
			}
			params.put("theme", theme);
			params.put("endDate", endtime);
			params.put("startDate",starttime);
			params.put("publishState", publicState);
			params.put("userid", userid);
			pageObj = informQueryExt.informQuery(params,pageNo,pageSize);
			Convert();
		} catch (Exception e) {
		}
		return "tzgg.jsp";
	}
	/**
	 * 查看通告详细信息并且添加一读记录
	 * @return
	 */
	public Object queryInformation()
	{
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("informid", informIds);
			informlist = informQueryExt.informlistQuery(params);
			if(informlist==null||informlist.isEmpty()) 
				return "information.jsp";
			information = informlist.get(0);
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("userid", userid);
			param.put("informid", informIds);
			int count = informQueryExt.beforeInsertRead(param);
			if(isInsert!=null && isInsert.equals("no")){
				isBack="back";
				return "information.jsp";
			}
			if(count==0)
			{
				informQueryExt.insertRead(param);
				if("1".equals(constantBean.get("isCache")))
				{
					try{
						informDoQueAndCacheExt.addReadedInformUserToCache(informIds, userid);
					}catch(Exception e){
						//如果添加到队列，因各种原因导致异常，可以不做任何处理
						logger.error("queryInformation()",e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryInformation", e);
		}
		return "information.jsp";
	}
	/**
	 * 下载通知公告内的附件
	 * @param req
	 * @param response
	 * @return
	 */
	public Object downAttach(HttpServletRequest req, HttpServletResponse response)
	{
		String rootPath = constantBean.get("mapping_root");
		String filetruename = NoServiceUtil.replaceFileSeparator(rootPath+filepath);
		if (!new File(filetruename).exists()) {
			try {
				PrintWriter out = response.getWriter();
				out.write("<script>parent.missed(" + filepath
						+ ");</script>");
				out.close();
			} catch (Exception e) {
				return null;
			}
			return null;
		}
		String fileName = NoServiceUtil.fileNameForEveryBrower(req,filename);
		return new DownloadItem(new File(filetruename))
				.setFilename(fileName);
	}
	/**
	 * 改变noticeFlag的值
	 */
	public void changeNoticeFlag(){
		
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
