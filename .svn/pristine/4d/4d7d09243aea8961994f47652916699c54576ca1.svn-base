package com.flyrish.hades.dto;

import java.io.Serializable;

import org.nestframework.utils.NestUtil;

public class AttachFileDto implements Serializable{
	
	//初中栏目标号
	private String partId;
	private String attachid;
	private String attachtypeid;
	private String attachtype;
	private String filename;
	private String filepath;
	private String attachpath;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getAttachid() {
		return attachid;
	}
	public void setAttachid(String attachid) {
		this.attachid = attachid;
	}
	public String getAttachtypeid() {
		return attachtypeid;
	}
	public void setAttachtypeid(String attachtypeid) {
		this.attachtypeid = attachtypeid;
	}
	public String getAttachtype() {
		return attachtype;
	}
	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}
	public String getAttachpath() {
		return attachpath;
	}
	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
    public int compareTo(Object obj) {
    	if(obj==null) return -1;
    	String appId = NestUtil.isEmpty(((AttachFileDto)obj).getAttachid())?"-1":((AttachFileDto)obj).getAttachid();
    	String appId2 = NestUtil.isEmpty(this.getAttachid())?"-2":this.getAttachid();
    	return -appId.compareTo(appId2);
    }
}
