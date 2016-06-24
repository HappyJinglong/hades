package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 初综_栏目内容
 * @author Administrator
 *
 */
public class PartInfoCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156715551650642567L;
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
	private String CreateDate;//评价日期
	private String is_attachmen;//是否含附件
	private String cmis30id;//cmis30id
	private String discode;//区县代码
	private String edu_id;//教育id
	private String keyword;//关键字
	private String cooperation_man;//合作者
	private String startdate;//起始时间
	private String enddate;//结束时间
	private String address;//地址
	private String partid;//partid
	private String edittime;//edittime
	private String o_part_id;//o_part_id
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
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
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
	public String getEdu_id() {
		return edu_id;
	}
	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCooperation_man() {
		return cooperation_man;
	}
	public void setCooperation_man(String cooperation_man) {
		this.cooperation_man = cooperation_man;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}
	public String getO_part_id() {
		return o_part_id;
	}
	public void setO_part_id(String o_part_id) {
		this.o_part_id = o_part_id;
	}
	  public int compareTo(Object obj) {
	    	if(obj==null) return -1;
	    	String appId = ((PartInfoCacheDto)obj).getPart_id()==null?"-1":((PartInfoCacheDto)obj).getPart_id();
	    	String appId2 = this.getPart_id()==null?"-2":this.getPart_id();
	    	return -appId.compareTo(appId2);
	    }
	
}
