package com.flyrish.hades.dto;

import java.io.Serializable;

public class AppraisalWritedStaticseDto implements Serializable{
	private String discode;//区县代码
	private String classNum;//班级号
	private String classInfo;//班级信息  学校名称 共用
	private Integer totalCount=0;//班级总人数
	private Integer bzrCount=0;//班主任评语
	private Integer sxddCount=0;//思想道德
	private Integer kcpyCount=0;//课程评语
	private Integer hzyjlCount=0;//合作与交流
	private Integer ysyjkCount=0;//运动与健康
	private Integer smybxCount=0;//审美与表现
	private Integer gxfzCount=0;//个性发展
	//区
	private Integer oneCount=0;
	private Integer twoCount=0;
	private Integer threeCount=0;
	private Integer fourCount=0;
	private Integer oneCountFinished=0;
	private Integer twoCountFinished=0;
	private Integer threeCountFinished=0;
	private Integer fourCountFinished=0;
	
	public String getDiscode() {
		return discode;
	}
	public void setDiscode(String discode) {
		this.discode = discode;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public Integer getOneCount() {
		return oneCount;
	}
	public void setOneCount(Integer oneCount) {
		this.oneCount = oneCount;
	}
	public Integer getTwoCount() {
		return twoCount;
	}
	public void setTwoCount(Integer twoCount) {
		this.twoCount = twoCount;
	}
	public Integer getThreeCount() {
		return threeCount;
	}
	public void setThreeCount(Integer threeCount) {
		this.threeCount = threeCount;
	}
	public Integer getFourCount() {
		return fourCount;
	}
	public void setFourCount(Integer fourCount) {
		this.fourCount = fourCount;
	}
	public Integer getOneCountFinished() {
		return oneCountFinished;
	}
	public void setOneCountFinished(Integer oneCountFinished) {
		this.oneCountFinished = oneCountFinished;
	}
	public Integer getTwoCountFinished() {
		return twoCountFinished;
	}
	public void setTwoCountFinished(Integer twoCountFinished) {
		this.twoCountFinished = twoCountFinished;
	}
	public Integer getThreeCountFinished() {
		return threeCountFinished;
	}
	public void setThreeCountFinished(Integer threeCountFinished) {
		this.threeCountFinished = threeCountFinished;
	}
	public Integer getFourCountFinished() {
		return fourCountFinished;
	}
	public void setFourCountFinished(Integer fourCountFinished) {
		this.fourCountFinished = fourCountFinished;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getBzrCount() {
		return bzrCount;
	}
	public void setBzrCount(Integer bzrCount) {
		this.bzrCount = bzrCount;
	}
	public Integer getSxddCount() {
		return sxddCount;
	}
	public void setSxddCount(Integer sxddCount) {
		this.sxddCount = sxddCount;
	}
	public Integer getKcpyCount() {
		return kcpyCount;
	}
	public void setKcpyCount(Integer kcpyCount) {
		this.kcpyCount = kcpyCount;
	}
	public Integer getHzyjlCount() {
		return hzyjlCount;
	}
	public void setHzyjlCount(Integer hzyjlCount) {
		this.hzyjlCount = hzyjlCount;
	}
	public Integer getYsyjkCount() {
		return ysyjkCount;
	}
	public void setYsyjkCount(Integer ysyjkCount) {
		this.ysyjkCount = ysyjkCount;
	}
	public Integer getSmybxCount() {
		return smybxCount;
	}
	public void setSmybxCount(Integer smybxCount) {
		this.smybxCount = smybxCount;
	}
	public Integer getGxfzCount() {
		return gxfzCount;
	}
	public void setGxfzCount(Integer gxfzCount) {
		this.gxfzCount = gxfzCount;
	}
}
