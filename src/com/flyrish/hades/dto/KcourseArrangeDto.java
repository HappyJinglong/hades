package com.flyrish.hades.dto;

import java.io.Serializable;

public class KcourseArrangeDto implements Serializable{
	
	private String arrange_id;//学段课程安排标号
	
	private String course_id;//课程标号
	
	private String arrange_date;//安排时间
	
	private String segment_order;//学段
	
	private String apply_grade;//适用年级(无用)

	public String getArrange_id() {
		return arrange_id;
	}

	public void setArrange_id(String arrange_id) {
		this.arrange_id = arrange_id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getArrange_date() {
		return arrange_date;
	}

	public void setArrange_date(String arrange_date) {
		this.arrange_date = arrange_date;
	}

	public String getSegment_order() {
		return segment_order;
	}

	public void setSegment_order(String segment_order) {
		this.segment_order = segment_order;
	}

	public String getApply_grade() {
		return apply_grade;
	}

	public void setApply_grade(String apply_grade) {
		this.apply_grade = apply_grade;
	}

	@Override
	public String toString() {
		return "KcourseArrange [arrange_id=" + arrange_id + ", course_id="
				+ course_id + ", arrange_date=" + arrange_date
				+ ", segment_order=" + segment_order + ", apply_grade="
				+ apply_grade + "]";
	}
	
	


}
