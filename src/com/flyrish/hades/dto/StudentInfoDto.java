package com.flyrish.hades.dto;

import java.io.Serializable;

public class StudentInfoDto implements Serializable{
	private String edu_id;
	
	private String studentName;
	
	private String studentno;
	
	private String cardid;
	
	private String cmis30id;
	
	private String birthday;
	
	private String sex;

	public String getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCmis30id() {
		return cmis30id;
	}

	public void setCmis30id(String cmis30id) {
		this.cmis30id = cmis30id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
