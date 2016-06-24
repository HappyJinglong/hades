package com.flyrish.hades.dto;
/**
 * 状态
 * */
public class Reportstatus {

 

	private String waterid;  //主键
	private String reportid;	//外检
	private String verfifykind;	//code
	private String verfifyresult; //描述
	
	public String getWaterid() {
		return waterid;
	}
	public void setWaterid(String waterid) {
		this.waterid = waterid;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getVerfifykind() {
		return verfifykind;
	}
	public void setVerfifykind(String verfifykind) {
		this.verfifykind = verfifykind;
	}
	public String getVerfifyresult() {
		return verfifyresult;
	}
	public void setVerfifyresult(String verfifyresult) {
		this.verfifyresult = verfifyresult;
	}
	
	 
}
