package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 高综_学业成就_学科学习过程记录_作品相关信息介绍
 * @author Administrator
 *
 */
public class AlearnprocessWorksCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5442304540992931264L;
	private String workid;//作品展示标识号
	private String subject;//课程名称
	private String processdesc;//过程记录
	private String semesterid;//学年学期标识号
	private String studentid;//学生标识号
	private String edu_id;//教育ID
	private String discode;//discode
	private String cmis30id;//cmis30id
	private String signdate;//签字日期
	private String partid;//partid
	private String edittime;//edittime
	private String appraiserrid;//评价人标识号
	private String appraseridentify;//评价人身份
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getProcessdesc() {
		return processdesc;
	}
	public void setProcessdesc(String processdesc) {
		this.processdesc = processdesc;
	}
	public String getSemesterid() {
		return semesterid;
	}
	public void setSemesterid(String semesterid) {
		this.semesterid = semesterid;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getEdu_id() {
		return edu_id;
	}
	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
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
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
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
	public String getAppraiserrid() {
		return appraiserrid;
	}
	public void setAppraiserrid(String appraiserrid) {
		this.appraiserrid = appraiserrid;
	}
	public String getAppraseridentify() {
		return appraseridentify;
	}
	public void setAppraseridentify(String appraseridentify) {
		this.appraseridentify = appraseridentify;
	}
	
	
}
