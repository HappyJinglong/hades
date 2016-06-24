package com.flyrish.hades.dto;

public class SchoolreportDto {
	private Integer cmis30id;  //学校id 
	private String schoolcode;  //学校代码
	private String schoolname;  //学校名字
	private String jiebie;      //界别
	private Integer reportedCount; //上报人数
	private Integer verficationSuccessCount;//校验通过人数
	private Integer verficationFailedCount;//校验失败人数
	
	
	
	
 
	public Integer getCmis30id() {
		return cmis30id;
	}
	public void setCmis30id(Integer cmis30id) {
		this.cmis30id = cmis30id;
	}
	public String getSchoolcode() {
		return schoolcode;
	}
	public void setSchoolcode(String schoolcode) {
		this.schoolcode = schoolcode;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getJiebie() {
		return jiebie;
	}
	public void setJiebie(String jiebie) {
		this.jiebie = jiebie;
	}
	public Integer getReportedCount() {
		return reportedCount;
	}
	public void setReportedCount(Integer reportedCount) {
		this.reportedCount = reportedCount;
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
