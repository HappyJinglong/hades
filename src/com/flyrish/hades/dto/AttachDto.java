package com.flyrish.hades.dto;

import java.io.Serializable;

public class AttachDto implements Serializable{
	
	private Integer attachid;//附件标识号
	
	private Integer workid;//作品展示标识号
	
	private Integer processid;//学习过程记录标识号

	private Integer attachtype;//附件类型(1.记录袋,2.个人发展,3.综合实践,4.学科学习发展过程)
	
	private String filename;//附件名称
	
	private String filepath;//附件路径
	
	private Integer image;//附件是否为图片文件
	
	private Integer attachtypeid;//记录袋id
	
	private String cmis30id;
	
	private String discode;

	public String getCmis30id() {
		return cmis30id;
	}

	public void setCmis30id(String cmis30id) {
		this.cmis30id = cmis30id;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	public Integer getAttachid() {
		return attachid;
	}

	public void setAttachid(Integer attachid) {
		this.attachid = attachid;
	}

	public Integer getWorkid() {
		return workid;
	}

	public void setWorkid(Integer workid) {
		this.workid = workid;
	}

	public Integer getProcessid() {
		return processid;
	}

	public void setProcessid(Integer processid) {
		this.processid = processid;
	}

	public Integer getAttachtype() {
		return attachtype;
	}

	public void setAttachtype(Integer attachtype) {
		this.attachtype = attachtype;
	}

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

	public Integer getAttachtypeid() {
		return attachtypeid;
	}

	public void setAttachtypeid(Integer attachtypeid) {
		this.attachtypeid = attachtypeid;
	}

	public Integer getImage() {
		return image;
	}

	public void setImage(Integer image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "AttachDto [attachid=" + attachid + ", workid=" + workid
				+ ", processid=" + processid + ", attachtype=" + attachtype
				+ ", filename=" + filename + ", filepath=" + filepath
				+ ", image=" + image + ", attachtypeid=" + attachtypeid + "]";
	}
	
    public int compareTo(Object obj) {
		if(obj==null) return -1;
		
		Integer appId = ((AttachDto)obj).getAttachid()==null?-1:((AttachDto)obj).getAttachid();
		Integer appId2 = this.getAttachid()==null?-2:this.getAttachid();
		return -appId.compareTo(appId2);
   }
}
