package com.flyrish.hades.dto;

import java.io.Serializable;

public class KsegmentCourseDto implements Serializable{
	
	private String segment_course_id;//学段课程标号
	
	private String declare_teacher;//申报教师
	
	private String audit_teacher;//审核教师
	
	private String course_id;//课程标号
	
	private String segment_id;//当前学段标号
	
	private String declare_date;//申报时间
	
	private String audit_status;//申报审核状态
	
	private String audit_date;//审核时间
	
	private String need_assign;//是否需要认定
	
	private String is_assigned;//是否认定完成
	
	private String brother_id;//同一批标号

	public String getSegment_course_id() {
		return segment_course_id;
	}

	public void setSegment_course_id(String segment_course_id) {
		this.segment_course_id = segment_course_id;
	}

	public String getDeclare_teacher() {
		return declare_teacher;
	}

	public void setDeclare_teacher(String declare_teacher) {
		this.declare_teacher = declare_teacher;
	}

	public String getAudit_teacher() {
		return audit_teacher;
	}

	public void setAudit_teacher(String audit_teacher) {
		this.audit_teacher = audit_teacher;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getSegment_id() {
		return segment_id;
	}

	public void setSegment_id(String segment_id) {
		this.segment_id = segment_id;
	}

	public String getDeclare_date() {
		return declare_date;
	}

	public void setDeclare_date(String declare_date) {
		this.declare_date = declare_date;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}

	public String getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}

	public String getNeed_assign() {
		return need_assign;
	}

	public void setNeed_assign(String need_assign) {
		this.need_assign = need_assign;
	}

	public String getIs_assigned() {
		return is_assigned;
	}

	public void setIs_assigned(String is_assigned) {
		this.is_assigned = is_assigned;
	}

	public String getBrother_id() {
		return brother_id;
	}

	public void setBrother_id(String brother_id) {
		this.brother_id = brother_id;
	}

	@Override
	public String toString() {
		return "KsegmentCourse [segment_course_id=" + segment_course_id
				+ ", declare_teacher=" + declare_teacher + ", audit_teacher="
				+ audit_teacher + ", course_id=" + course_id + ", segment_id="
				+ segment_id + ", declare_date=" + declare_date
				+ ", audit_status=" + audit_status + ", audit_date="
				+ audit_date + ", need_assign=" + need_assign
				+ ", is_assigned=" + is_assigned + ", brother_id=" + brother_id
				+ "]";
	}

	
}
