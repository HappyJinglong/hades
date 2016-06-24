package com.flyrish.hades.dto;

import java.io.Serializable;

public class SubjectDto implements Serializable{
	//科目标识号
	private String subjectid;
	//科目名称
	private String subjectName;
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
