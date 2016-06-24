package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.List;



public class SchoolStatDto implements Serializable{
	private List<SchoolDto> schoolList;

	public List<SchoolDto> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<SchoolDto> schoolList) {
		this.schoolList = schoolList;
	}
 
}
