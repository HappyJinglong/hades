package com.flyrish.hades.webapp.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nestframework.action.DownloadItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.service.ext.IRecordPackageManagerExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.util.XMLProperties;

public class DownloadAttachAction extends BaseAction{

	@Spring
	IRecordPackageManagerExt recordPackageManagerExt;
	@Spring
	ISelfAppManagerExt selfAppManagerExt;
	@Spring
	public ConstantBean constantBean;
	
	private String attachid;
	
	public String foreignKey;
	
	public List<AttachDto> attachDtoList;//附件信息

	public void setAttachid(String attachid) {
		this.attachid = attachid;
	}

	protected XMLProperties p;

	@DefaultAction
	public Object download(HttpServletRequest req, HttpServletResponse response) {
		this.getLoginInfo(req);
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("attachid", attachid);
		queryMap.put("cmis30id", userDto.getCmis30id());
		queryMap.put("discode", userDto.getDiscode());
		isStartAppraiseCache = constantBean.get("isStartAppraiseCache"); 
		if("1".equals(isStartAppraiseCache)){
			attachDtoList = recordPackageManagerExt.selectAttachListFromCache(foreignKey,attachid);
		}else{
			attachDtoList = recordPackageManagerExt.selectAttachList(queryMap);
		}
		AttachDto attach = attachDtoList.get(0);
		String rootPath = constantBean.get("mapping_root");
		if (!new File(rootPath+attach.getFilepath()).exists()) {
			try {
				PrintWriter out = response.getWriter();
				out.write("<script>parent.missed(" + attach.getAttachid()
						+ ");</script>");
				out.close();
			} catch (Exception e) {
				return null;
			}
			return null;
		}
		String fileName = NoServiceUtil.fileNameForEveryBrower(req, attach.getFilename());
		String filePath = NoServiceUtil.replaceFileSeparator(attach.getFilepath());
		return new DownloadItem(new File(rootPath+filePath))
				.setFilename(fileName);
	}
}
