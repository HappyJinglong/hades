package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 高综_评价
 * @author Administrator
 *
 */
public class SumColumnsDto implements Serializable{
	  /*学生总人数*/
	 private  String sumStruder;
	 /*已完成学生数*/
	 private  String sumStrudered;
	 /*完成百分比*/
	 private  String sign;
	 /*已填写条目数*/
	 private  String sumcont;
	 
	 List<GradeDto> gradelist =new ArrayList<GradeDto>();
	 
	public List<GradeDto> getGradelist() {
		return gradelist;
	}
	public void setGradelist(List<GradeDto> gradelist) {
		this.gradelist = gradelist;
	}
	public String getSumStruder() {
		return sumStruder;
	}
	public void setSumStruder(String sumStruder) {
		this.sumStruder = sumStruder;
	}
	public String getSumStrudered() {
		return sumStrudered;
	}
	public void setSumStrudered(String sumStrudered) {
		this.sumStrudered = sumStrudered;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSumcont() {
		return sumcont;
	}
	public void setSumcont(String sumcont) {
		this.sumcont = sumcont;
	}
	 
		
	
}
