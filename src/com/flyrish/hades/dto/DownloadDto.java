package com.flyrish.hades.dto;

import java.io.Serializable;

public class DownloadDto implements Serializable{

	private String class_model_id;//班级模块标号
	
	private String subject_name;//学科名
	
	private String gradenum;//年级编号
	
	private String gradename;//年级名
	
	private String classsname;//班级名
	
	private String classnum;//班级编号
	
	private String course_name;//模块名称
	
	private String teachername;//教师名称
	
	private String classid;
	
	private String teacherid;
	
	private Integer rownum;
	
	private String errorInfo;
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	public String getClass_model_id() {
		return class_model_id;
	}

	public void setClass_model_id(String class_model_id) {
		this.class_model_id = class_model_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getGradenum() {
		return gradenum;
	}

	public void setGradenum(String gradenum) {
		this.gradenum = gradenum;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getClasssname() {
		return classsname;
	}

	public void setClasssname(String classsname) {
		this.classsname = classsname;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	@Override
	public String toString() {
		return "DownloadDto [class_model_id=" + class_model_id
				+ ", subject_name=" + subject_name + ", gradenum=" + gradenum
				+ ", gradename=" + gradename + ", classsname=" + classsname
				+ ", classnum=" + classnum + ", course_name=" + course_name
				+ ", teachername=" + teachername + "]";
	}
	
	
}
