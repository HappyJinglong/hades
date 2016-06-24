package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 高综_学业成就_学科学习过程记录_评语
 * @author Administrator
 *
 */
public class AlearnprocessAppraisalCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1825449288519386120L;
	private String appraisalid;//综合实践活动评价标识
	private String semesterid;//学年学期标识号
	private String studentid;//学生标识号
	private String subject;//课程名称
	private String appraisal;//评语
	private String sign;//签字
	private String signdate;//日期
	private String edu_id;//教育ID
	private String discode;//discode
	private String cmis30id;//cmis30id
	private String partid;//partid
	private String edittime;//edittime
	private String appraiserrid;//appraiserrid
	private String appraseridentify;//评价人身份
	public String getAppraisalid() {
		return appraisalid;
	}
	public void setAppraisalid(String appraisalid) {
		this.appraisalid = appraisalid;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAppraisal() {
		return appraisal;
	}
	public void setAppraisal(String appraisal) {
		this.appraisal = appraisal;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
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
