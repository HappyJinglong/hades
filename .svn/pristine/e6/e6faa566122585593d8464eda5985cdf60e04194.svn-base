package com.flyrish.hades.dto;

import java.io.Serializable;

public class AttachmentDto implements Serializable{

	private String attachment_id;//附件标号
	
	private String part_id;//栏目标号
	
	private String attachment_name;//附件名称
	
	private String attachment_path;//路径
	
	private String cmis30id;//
	
	private String discode;//DISCODE

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

	public String getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(String attachment_id) {
		this.attachment_id = attachment_id;
	}

	public String getPart_id() {
		return part_id;
	}

	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public String getAttachment_path() {
		return attachment_path;
	}

	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}

	@Override
	public String toString() {
		return "AttachmentDto [attachment_id=" + attachment_id + ", part_id="
				+ part_id + ", attachment_name=" + attachment_name
				+ ", attachment_path=" + attachment_path + "]";
	}
	
	public int compareTo(Object obj) {
		if(obj==null) return -1;
		
		Integer appId = ((AttachmentDto)obj).getAttachment_id()==null?-1:Integer.valueOf(((AttachmentDto)obj).getAttachment_id());
		Integer appId2 = this.getAttachment_id()==null?-2:Integer.valueOf(this.getAttachment_id());
		return -appId.compareTo(appId2);
   }
	
}
