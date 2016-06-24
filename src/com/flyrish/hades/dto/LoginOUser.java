package com.flyrish.hades.dto;

import java.io.Serializable;

public class LoginOUser implements Serializable{
//	用户标识号	userid	NUMBER(6)
	private String userid;
//	登录名	username	VARCHAR2(50)
	private String username;
//	密码	pwd	VARCHAR2(200)
	private String pwd;
//	用户类型	usertype	INTEGER
	private String usertype;
//	姓名	name	VARCHAR2(30)
	private String name;
//	证件类型	cardsort	INTEGER
	private String cardsort;
//	证件号码	cardid	VARCHAR2(20)
	private String cardid;
//	工作单位	unitname	VARCHAR2(60)
	private String unitname;
//	联系电话	telephone	VARCHAR2(30)
	private String telephone;
//	单位id	unitid	INTEGER
	private String unitid;
//	人员id	personid	INTEGER	
	private String personid;
//	小学	primaryschool	SMALLINT
	private String primaryschool;
//	初中	middleschool	SMALLINT
	private String middleschool;
//	高中	highschool	SMALLINT
	private String highschool;
//	是否启用	used	INTEGER
	private String used;
//	密码明文	pwd_show	VARCHAR2(200)
	private String pwd_show;
	
	private String count;
	
	private String systemtype;
	
	
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardsort() {
		return cardsort;
	}
	public void setCardsort(String cardsort) {
		this.cardsort = cardsort;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getPrimaryschool() {
		return primaryschool;
	}
	public void setPrimaryschool(String primaryschool) {
		this.primaryschool = primaryschool;
	}
	public String getMiddleschool() {
		return middleschool;
	}
	public void setMiddleschool(String middleschool) {
		this.middleschool = middleschool;
	}
	public String getHighschool() {
		return highschool;
	}
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getPwd_show() {
		return pwd_show;
	}
	public void setPwd_show(String pwd_show) {
		this.pwd_show = pwd_show;
	}
	
	
}
