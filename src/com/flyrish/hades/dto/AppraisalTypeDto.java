package com.flyrish.hades.dto;

import java.io.Serializable;


/**
 * 评价类型
 * @author 
 *
 */
public class AppraisalTypeDto implements Serializable{
	
	private Integer appraisaltypeid;   //类型id
	
	private Integer upappraisaltypeid;   //父类id
	
	private String appraisaltype;        //类型名称

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
	
}
