package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LearnprocessWorksDto implements Serializable{

	private String workid;//作品展示标识号
	private String subject;//课程名称
	private String processdesc;//过程记录
	private String semesterid;//学年学期标识号
	private String studentid;//学生标识号
	private String edu_id;//教育id
	private String discode;//
	private String cmis30id;//
	private Date signdate;
	
	public Date getSigndate() {
		return signdate;
	}
	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}
	public List<AttachDto> attachListForFile = new ArrayList<AttachDto>();
	public List<AttachDto> attachListForImage = new ArrayList<AttachDto>();
	
	
	public List<AttachDto> getAttachListForFile() {
		return attachListForFile;
	}
	public void setAttachListForFile(List<AttachDto> attachListForFile) {
		this.attachListForFile = attachListForFile;
	}
	public List<AttachDto> getAttachListForImage() {
		return attachListForImage;
	}
	public void setAttachListForImage(List<AttachDto> attachListForImage) {
		this.attachListForImage = attachListForImage;
	}
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
	@Override
	public String toString() {
		return "LearnprocessWorksDto [workid=" + workid + ", subject="
				+ subject + ", processdesc=" + processdesc + ", semesterid="
				+ semesterid + ", studentid=" + studentid + ", edu_id="
				+ edu_id + ", discode=" + discode + ", cmis30id=" + cmis30id
				+ "]";
	}
	
    public int compareTo(Object obj) {
		if(obj==null) return -1;
		
		Integer appId = ((LearnprocessWorksDto)obj).getWorkid()==null?-1:Integer.valueOf(((LearnprocessWorksDto)obj).getWorkid());
		Integer appId2 = this.getWorkid()==null?-2:Integer.valueOf(this.getWorkid());
		return -appId.compareTo(appId2);
   }
	
}
