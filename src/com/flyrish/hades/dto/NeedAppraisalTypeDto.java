package com.flyrish.hades.dto;

import java.util.List;

public class NeedAppraisalTypeDto {
	private Integer appraisaltypeid;   //id
	private Integer upappraisaltypeid;   //父类id
	private String appraisaltype;        //名称
	
	//高中--有三届学生
	private List<needsTable> jb;

	public Integer getAppraisaltypeid() {
		return appraisaltypeid;
	}

	public void setAppraisaltypeid(Integer appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}

	public Integer getUpappraisaltypeid() {
		return upappraisaltypeid;
	}

	public void setUpappraisaltypeid(Integer upappraisaltypeid) {
		this.upappraisaltypeid = upappraisaltypeid;
	}

	public String getAppraisaltype() {
		return appraisaltype;
	}

	public void setAppraisaltype(String appraisaltype) {
		this.appraisaltype = appraisaltype;
	}

	public List<needsTable> getJb() {
		return jb;
	}

	public void setJb(List<needsTable> jb) {
		this.jb = jb;
	}
	
	
}
