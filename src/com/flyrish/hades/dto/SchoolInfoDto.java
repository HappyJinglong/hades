package com.flyrish.hades.dto;

import java.io.Serializable;

public class SchoolInfoDto implements Serializable{

	public Integer schoolId;	//学校ID
	
	public String schoolName;	//学校名称

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
