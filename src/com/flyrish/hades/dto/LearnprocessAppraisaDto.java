package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Date;

public class LearnprocessAppraisaDto implements Serializable{

	private String appraisalid;//综合实践活动评价标识
	private String semesterid;//学年学期标识号
	private String studentid;//学生标识号
	private String subject;//课程名称
	private String appraisal;//评语
	private String sign;//签字
	private Date signdate;//日期
	private String edu_id;//教育id
	private String discode;//
	private String cmis30id;//
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
	public Date getSigndate() {
		return signdate;
	}
	public void setSigndate(Date signdate) {
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
	@Override
	public String toString() {
		return "LearnprocessAppraisaDto [appraisalid=" + appraisalid
				+ ", semesterid=" + semesterid + ", studentid=" + studentid
				+ ", subject=" + subject + ", appraisal=" + appraisal
				+ ", sign=" + sign + ", signdate=" + signdate + ", edu_id="
				+ edu_id + ", discode=" + discode + ", cmis30id=" + cmis30id
				+ "]";
	}
	
}
