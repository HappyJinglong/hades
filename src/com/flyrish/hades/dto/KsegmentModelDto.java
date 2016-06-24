package com.flyrish.hades.dto;

import java.io.Serializable;

public class KsegmentModelDto implements Serializable{
	
	private String sement_model_id;//学段模块标号
	
	private String segment_id;//学段标号
	
	private String course_id;//课程标号
	
	private String is_assign;//是否指定了模块

	public String getSement_model_id() {
		return sement_model_id;
	}

	public void setSement_model_id(String sement_model_id) {
		this.sement_model_id = sement_model_id;
	}

	public String getSegment_id() {
		return segment_id;
	}

	public void setSegment_id(String segment_id) {
		this.segment_id = segment_id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getIs_assign() {
		return is_assign;
	}

	public void setIs_assign(String is_assign) {
		this.is_assign = is_assign;
	}

	@Override
	public String toString() {
		return "KsegmentModelDto [sement_model_id=" + sement_model_id
				+ ", segment_id=" + segment_id + ", course_id=" + course_id
				+ ", is_assign=" + is_assign + "]";
	}

	
}
