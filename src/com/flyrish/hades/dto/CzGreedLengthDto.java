package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CzGreedLengthDto implements Serializable{
  private Integer schoolId;
 
  private  Integer number;

public Integer getSchoolId() {
	return schoolId;
}

public void setSchoolId(Integer schoolId) {
	this.schoolId = schoolId;
}

public Integer getNumber() {
	return number;
}

public void setNumber(Integer number) {
	this.number = number;
}

@Override
public String toString() {
	return "CzGreedLengthDto [schoolId=" + schoolId + ", number=" + number
			+ "]";
}



}
