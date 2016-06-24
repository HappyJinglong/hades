package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 高综_评价
 * @author Administrator
 *
 */
public class AapprasialCacheDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1470721729072445859L;
	private String apprasialid;//评价标识号
	private String appraisaltypeid;//评价分类标识
	private String appraiserrid;//评价人标识号
	private String semesterid;//学年学期标识号
	private String studentid;//学生标识号
	private String appraseridentify;//评价人身份
	private String appraser;//评价人
	private String apprasial;//评价内容
	private String signdate;//签字日期
	private String edu_id;//教育ID
	private String discode;//discode
	private String cmis30id;//cmis30id
	private String partid;//partid
	private String edittime;//修改时间
    
	public String getApprasialid() {
		return apprasialid;
	}
	public void setApprasialid(String apprasialid) {
		this.apprasialid = apprasialid;
	}
	public String getAppraisaltypeid() {
		return appraisaltypeid;
	}
	public void setAppraisaltypeid(String appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}
	public String getAppraiserrid() {
		return appraiserrid;
	}
	public void setAppraiserrid(String appraiserrid) {
		this.appraiserrid = appraiserrid;
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
	public String getAppraseridentify() {
		return appraseridentify;
	}
	public void setAppraseridentify(String appraseridentify) {
		this.appraseridentify = appraseridentify;
	}
	public String getAppraser() {
		return appraser;
	}
	public void setAppraser(String appraser) {
		this.appraser = appraser;
	}
	public String getApprasial() {
		return apprasial;
	}
	public void setApprasial(String apprasial) {
		this.apprasial = apprasial;
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
	@Override
	public String toString() {
		return "AapprasialCacheDto [apprasialid=" + apprasialid
				+ ", appraisaltypeid=" + appraisaltypeid + ", appraiserrid="
				+ appraiserrid + ", semesterid=" + semesterid + ", studentid="
				+ studentid + ", appraseridentify=" + appraseridentify
				+ ", appraser=" + appraser + ", apprasial=" + apprasial
				+ ", signdate=" + signdate + ", edu_id=" + edu_id
				+ ", discode=" + discode + ", cmis30id=" + cmis30id
				+ ", partid=" + partid + ", edittime=" + edittime + "]";
	}
	
	
}
