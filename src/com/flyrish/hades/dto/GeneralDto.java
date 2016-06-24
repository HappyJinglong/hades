package com.flyrish.hades.dto;

import java.io.Serializable;

public class GeneralDto implements Serializable{

	private String schoolyear;
	
	private String schoolyearname;
	
	private String segmentName;
	
	private String segmentid;
	
	private String segmentorder;
	
	private String classid;
	
	private String classname;
	
	private String classnum;
	
	private String gradenum;
	
	private String gradename;
	
	private String gradeid;
	
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

	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getSegmentid() {
		return segmentid;
	}

	public void setSegmentid(String segmentid) {
		this.segmentid = segmentid;
	}

	public String getSegmentorder() {
		return segmentorder;
	}

	public void setSegmentorder(String segmentorder) {
		this.segmentorder = segmentorder;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSchoolyear() {
		return schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public String getSchoolyearname() {
		return schoolyearname;
	}

	public void setSchoolyearname(String schoolyearname) {
		this.schoolyearname = schoolyearname;
	}

	@Override
	public String toString() {
		return "GeneralDto [schoolyear=" + schoolyear + ", schoolyearname="
				+ schoolyearname + ", segmentName=" + segmentName
				+ ", segmentid=" + segmentid + ", segmentorder=" + segmentorder
				+ ", classid=" + classid + ", classname=" + classname
				+ ", gradenum=" + gradenum + ", gradename=" + gradename
				+ ", gradeid=" + gradeid + "]";
	}

	
}
