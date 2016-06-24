package com.flyrish.hades.dto;

public class ClassDto {

	public String getClassnum() {
		return classnum;
	}
	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
	private String classId;  //班级id
	private String className;//班级名字
	private String xueduan; //学段
	private String jiebie; //界别
	private Integer totalCount; //班级总人数
	private Integer reportedCount;//已上报人数
	private Integer noreportCount;//未上报人数
	private Integer verficationSuccessCount;//校验通过人数
	private Integer verficationFailedCount;//校验失败人数
	
	private String classnum;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getXueduan() {
		return xueduan;
	}
	public void setXueduan(String xueduan) {
		this.xueduan = xueduan;
	}
	public String getJiebie() {
		return jiebie;
	}
	public void setJiebie(String jiebie) {
		this.jiebie = jiebie;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getReportedCount() {
		return reportedCount;
	}
	public void setReportedCount(Integer reportedCount) {
		this.reportedCount = reportedCount;
	}
	public Integer getNoreportCount() {
		return noreportCount;
	}
	public void setNoreportCount(Integer noreportCount) {
		this.noreportCount = noreportCount;
	}
	public Integer getVerficationSuccessCount() {
		return verficationSuccessCount;
	}
	public void setVerficationSuccessCount(Integer verficationSuccessCount) {
		this.verficationSuccessCount = verficationSuccessCount;
	}
	public Integer getVerficationFailedCount() {
		return verficationFailedCount;
	}
	public void setVerficationFailedCount(Integer verficationFailedCount) {
		this.verficationFailedCount = verficationFailedCount;
	}
	 
	
}
