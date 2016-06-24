package com.flyrish.hades.webapp.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.action.DownloadItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AttachmentDto;
import com.flyrish.hades.service.ext.IPartInfoServiceExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.util.XMLProperties;

public class DownloadAttachmentAction extends BaseAction{

	@Spring
	ISelfAppManagerExt selfAppManagerExt;
	/**
	 * 处理初中数据
	 */
	@Spring
	IPartInfoServiceExt partInfoServiceExt;
	@Spring
	public ConstantBean constantBean;
	
	private String attachment_id;
	
	public List<AttachmentDto> attachDtoList;//������Ϣ

	public String foreignKey;
	
	public void setattachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}


	protected XMLProperties p;

	@DefaultAction
	public Object download(HttpServletRequest req, HttpServletResponse response) {
		this.getLoginInfo(req);
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("attachment_id", attachment_id);
		queryMap.put("discode", userDto.getDiscode());
		queryMap.put("cmis30id", userDto.getCmis30id());
		isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		if("1".equals(isStartAppraiseCache)){
			attachDtoList = partInfoServiceExt.selectAttachmentListFromCache(foreignKey,attachment_id);
		}else{
			attachDtoList = partInfoServiceExt.selectAttachmentList(queryMap);
		}
		AttachmentDto attach = attachDtoList.get(0);
		String rootPath = constantBean.get("mapping_root");
		if (!new File(rootPath+attach.getAttachment_path()).exists()) {
			try {
				PrintWriter out = response.getWriter();
				response.setHeader("ContentType", "text/html;charset=utf-8");
				out.write("<script>parent.missed(" + attach.getAttachment_id()
						+ ");</script>");
				out.close();
			} catch (Exception e) {
				return null;
			}
			return null;
		}
		String fileName = NoServiceUtil.fileNameForEveryBrower(req, attach.getAttachment_name());
		String filePath = NoServiceUtil.replaceFileSeparator(attach.getAttachment_path());
		return new DownloadItem(new File(rootPath+filePath))
				.setFilename(fileName);
	}
}
