package com.flyrish.hades.dto;

import java.io.Serializable;

public class ViewDto implements Serializable{
	 
	private String teacherName;  //任课老师
	
	private Integer xs;   //学时
	
	private Integer xf;  //学分
	
	private Double xss;
	
	private Double xfs;
	
    private String course_kind;  //课程类别	
    
    private String course_name; //课程名称
    
    private String model_credit;  //模块学分
	
	private String class_model_id;
	
	private String segment_course_id;
	
	private String specail;
	
	
	
	public String getSpecail() {
		return specail;
	}

	public void setSpecail(String specail) {
		this.specail = specail;
	}

	public Double getXss() {
		return xss;
	}

	public void setXss(Double xss) {
		this.xss = xss;
	}

	public Double getXfs() {
		return xfs;
	}

	public void setXfs(Double xfs) {
		this.xfs = xfs;
	}

	public String getModel_credit() {
		return model_credit;
	}

	public void setModel_credit(String model_credit) {
		this.model_credit = model_credit;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_kind() {
		return course_kind;
	}

	public void setCourse_kind(String course_kind) {
		this.course_kind = course_kind;
	}

	public String getSegment_course_id() {
		return segment_course_id;
	}

	public void setSegment_course_id(String segment_course_id) {
		this.segment_course_id = segment_course_id;
	}

	public String getClass_model_id() {
		return class_model_id;
	}

	public void setClass_model_id(String class_model_id) {
		this.class_model_id = class_model_id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getXs() {
		return xs;
	}

	public void setXs(Integer xs) {
		this.xs = xs;
	}

	public Integer getXf() {
		return xf;
	}

	public void setXf(Integer xf) {
		this.xf = xf;
	}
	
	
	
}
