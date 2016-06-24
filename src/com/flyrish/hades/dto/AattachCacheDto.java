package com.flyrish.hades.dto;

import java.io.Serializable;

public class AattachCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1121329789125615753L;
	private String attachid;//附件标识号
	private String workid;//作品展示标识号
	private String processid;//学习过程记录标识号
	private String attachtype;//附件类型
	private String attachname;//附件名称
	private String attachpath;//附件路径
	private String image;//附件是否为图片文件
	private String attachtypeid;//附件所属模块ID
	private String discode;//discode
	private String cmis30id;//cmis30id
	private String partid;//partid
	private String appraseridentify;//appraseridentify
	public String getAttachid() {
		return attachid;
	}
	public void setAttachid(String attachid) {
		this.attachid = attachid;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
	}
	public String getAttachtype() {
		return attachtype;
	}
	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}
	public String getAttachname() {
		return attachname;
	}
	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}
	public String getAttachpath() {
		return attachpath;
	}
	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAttachtypeid() {
		return attachtypeid;
	}
	public void setAttachtypeid(String attachtypeid) {
		this.attachtypeid = attachtypeid;
	}
	public String getDiscode() {
		return discode;
	}
	public void setDiscode(String discode) {
		this.discode = discode;
	}
	public String getCmis30id() {
		return cmis30id;
	}
	public void setCmis30id(String cmis30id) {
		this.cmis30id = cmis30id;
	}
	public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getAppraseridentify() {
		return appraseridentify;
	}
	public void setAppraseridentify(String appraseridentify) {
		this.appraseridentify = appraseridentify;
	}
	
	
}
