package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 高综_记录袋
 * @author Administrator
 *
 */
public class ArecordpackageCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2717306610207566148L;
	private String recordid;//记录单标识
	private String studentid;//学生标识号
	private String semesterid;//学年学期标识号
	private String appraisaltypeid;//评价分类标识
	private String appraiserrid;//评价人标识号
	private String content;//内容
	private String appraseridentify;//评价人身份
	private String signer;//签名
	private String signdate;//签字日期
	private String edu_id;//教育id
	private String discode;//discode
	private String cmis30id;//cmis30id
	private String partid;//partid
	private String edittime;//修改时间
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getSemesterid() {
		return semesterid;
	}
	public void setSemesterid(String semesterid) {
		this.semesterid = semesterid;
	}
	public String getAppraisaltypeid() {
		return appraisaltypeid;
	}
	public void setAppraisaltypeid(String appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAppraseridentify() {
		return appraseridentify;
	}
	public void setAppraseridentify(String appraseridentify) {
		this.appraseridentify = appraseridentify;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
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
	
}
