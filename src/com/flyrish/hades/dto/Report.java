package com.flyrish.hades.dto;

/**
 * 上报状态表
 * */
public class Report {
	
	
	//id
	public String waterid ;
	
	//学生id
	public String pid;
	
	//学期id
	public String  termid; 
	
	//上报状态
	public String  reportstatus;
	
	//是否可用
	public String used;
	
	//插入时间
	public String reporttime;
	
	//教育id
	public String eduid;
	
	//是否校验状态
	public String checkstate;

	public String getCheckstate() {
		return checkstate;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	public String getWaterid() {
		return waterid;
	}

	public void setWaterid(String waterid) {
		this.waterid = waterid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTermid() {
		return termid;
	}

	public void setTermid(String termid) {
		this.termid = termid;
	}

	public String getReportstatus() {
		return reportstatus;
	}

	public void setReportstatus(String reportstatus) {
		this.reportstatus = reportstatus;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getEduid() {
		return eduid;
	}

	public void setEduid(String eduid) {
		this.eduid = eduid;
	}
	
	

}
