package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartInfoXieDto implements Serializable{

	private String part_id;//栏目标号
	
	private String userid;//用户标识号
	
	private String studentid;//学生标识号

	private String termid;//学期标识号

	private String topic;//主题

	private String part_info;//栏目内容

	private String write_man;//评价人类型

	private String signer_name;//评价人签名

	private String two_part_id;//二级栏目标号
	
	private String subject_id;//学科标号

	private Date createDate;//评价日期

	private String is_attachmen;//是否含附件

	private String cmis30id;//cmis30id
	
	private String discode;//区县代码
	
	private String pid;//评价人的id
	
	private String pname;//被评价人的名字
	private String eduid;
	
	
	private String fakeId;
	
	
	private String contnumber;
	
	
	public String getContnumber() {
		return contnumber;
	}

	public void setContnumber(String contnumber) {
		this.contnumber = contnumber;
	}

	public String getFakeId() {
		return fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}

	public String getEduid() {
		return eduid;
	}

	public void setEduid(String eduid) {
		this.eduid = eduid;
	}

	 /**
     * 学生照片路径
     */
    private String photoUrl;
	
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public List<AttachmentDto> attachListForFile = new ArrayList<AttachmentDto>();
	public List<AttachmentDto> attachListForImage = new ArrayList<AttachmentDto>();
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPart_id() {
		return part_id;
	}

	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getTermid() {
		return termid;
	}

	public void setTermid(String termid) {
		this.termid = termid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPart_info() {
		return part_info;
	}

	public void setPart_info(String part_info) {
		this.part_info = part_info;
	}

	public String getWrite_man() {
		return write_man;
	}

	public void setWrite_man(String write_man) {
		this.write_man = write_man;
	}

	public String getSigner_name() {
		return signer_name;
	}

	public void setSigner_name(String signer_name) {
		this.signer_name = signer_name;
	}

	public String getTwo_part_id() {
		return two_part_id;
	}

	public void setTwo_part_id(String two_part_id) {
		this.two_part_id = two_part_id;
	}

	public String getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}

	public String getIs_attachmen() {
		return is_attachmen;
	}

	public void setIs_attachmen(String is_attachmen) {
		this.is_attachmen = is_attachmen;
	}

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

	
	public List<AttachmentDto> getAttachListForFile() {
		return attachListForFile;
	}

	public void setAttachListForFile(List<AttachmentDto> attachListForFile) {
		this.attachListForFile = attachListForFile;
	}

	public List<AttachmentDto> getAttachListForImage() {
		return attachListForImage;
	}

	public void setAttachListForImage(List<AttachmentDto> attachListForImage) {
		this.attachListForImage = attachListForImage;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "PartInfoDto [part_id=" + part_id + ", userid=" + userid
				+ ", studentid=" + studentid + ", termid=" + termid
				+ ", topic=" + topic + ", part_info=" + part_info
				+ ", write_man=" + write_man + ", signer_name=" + signer_name
				+ ", two_part_id=" + two_part_id + ", subject_id=" + subject_id
				+ ", CreateDate=" + createDate + ", is_attachmen="
				+ is_attachmen + ", cmis30id=" + cmis30id + ", discode="
				+ discode + "]";
	}
	
	  public int compareTo(Object obj) {
	    	if(obj==null) return -1;
	    	String appId = ((PartInfoXieDto)obj).getPart_id()==null?"-1":((PartInfoXieDto)obj).getPart_id();
	    	String appId2 = this.getPart_id()==null?"-2":this.getPart_id();
	    	return -appId.compareTo(appId2);
	    }
	

	
}
