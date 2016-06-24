package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonalityDto  implements Serializable{

	private String baseid;//基本情况标识号
	
	private String studentid;//学生标识号
	
	private String semesterid;//学年学期标识号

	private String appraisaltypeid;//评价分类标识
	
	private String indexid;//1,2,3每个个性发展基本三条记录；评价指标标识
	
	private String developmentrd;//个性发展记录
	
	private String edu_id;//教育id
	
	private String discode;//discode
	
	private String cmis30id;//cmis30id
	
	private Date signdate;

	
	public Date getSigndate() {
		return signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	public String getBaseid() {
		return baseid;
	}

	public void setBaseid(String baseid) {
		this.baseid = baseid;
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

	public String getIndexid() {
		return indexid;
	}

	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	public String getDevelopmentrd() {
		return developmentrd;
	}

	public void setDevelopmentrd(String developmentrd) {
		this.developmentrd = developmentrd;
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
		return "PersonalityDto [baseid=" + baseid + ", studentid=" + studentid
				+ ", semesterid=" + semesterid + ", appraisaltypeid="
				+ appraisaltypeid + ", indexid=" + indexid + ", developmentrd="
				+ developmentrd + ", edu_id=" + edu_id + ", discode=" + discode
				+ ", cmis30id=" + cmis30id + "]";
	}
	
	/**
	 * 排序
	 */
	public int compareTo(Object obj) {
		if(obj==null) return -1;
		
		String appId = ((PersonalityDto)obj).getBaseid()==null?"-1":((PersonalityDto)obj).getBaseid();
		String appId2 = this.getBaseid()==null?"-2":this.getBaseid();
		return -appId.compareTo(appId2);
	    }
	
}
