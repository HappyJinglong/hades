package com.flyrish.hades.dto;

import java.io.Serializable;

public class TermDto implements Serializable{
	
	private Integer termid;//学期标示号
	
	private String termname;//学期名称

	private Integer termtype;//学期类型
	
	private Integer isNow;//是否为当前学期
	
	private Integer used;
	
	
	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getTermid() {
		return termid;
	}

	public void setTermid(Integer termid) {
		this.termid = termid;
	}

	public String getTermname() {
		return termname;
	}

	public void setTermname(String termname) {
		this.termname = termname;
	}

	public Integer getIsNow() {
		return isNow;
	}

	public void setIsNow(Integer isNow) {
		this.isNow = isNow;
	}

	public Integer getTermtype() {
		return termtype;
	}

	public void setTermtype(Integer termtype) {
		this.termtype = termtype;
	}
	
	
	
}
