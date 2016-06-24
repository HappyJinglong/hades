package com.flyrish.hades.dto;

import java.io.Serializable;

import org.nestframework.utils.NestUtil;

import com.flyrish.hades.util.NoServiceUtil;

public class UserDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2074807870520416658L;

	//所有角色共有的属性
	/**
	 * 当前有效学期标识号
	 */
	private String termId;
	
	private String termName;

	/**
	 * 当前登录用户所属系统类型
	 * 1----为cmis平台
	 * 2----为cmis、综素平台
	 * 3----为综素平台
	 */
	private String systemtype;
	
	//登录用户自身属性
	/**
	 * 登录用户名
	 */
	private String username;
	/**
	 * 登录用户密码
	 */
	private String password;
	/**
	 * 登录用户标识号
	 */
	private String userid;
	/**
	 * 用户类别 1503
	 */
	private String usertype;
	/**
	 * 登录用户真实的角色类型(校级)，其他级别为null
	 * 1502001/系统管理员
	 * 1502002/校长
	 * 1502003/教务老师
	 * 1502004/学籍老师
	 * 1502005/德育老师
	 * 1502006/班主任
	 * 1502007/任课教师
	 * 1502008/学段负责人
	 * 1502009/年级负责人
	 * 1502010/校医
	 * 1502031/家长
	 * 1502030/学生
	 */
	private String userRealType;
	/**
	 * 登录用户所属单位标识号
	 * 校级用户  取值为学校标识号(家长和学生不适用)
	 * 区级用户  取值为区县代码
	 * 市级用户 取值为 null
	 */
	private String unitid;				//单位ID
	/**
	 * 该账户是否启用
	 */
	private String used;				//是否启用
	/**
	 * 用户所对应的只是用户标识号
	 * 家长用户 =>表示孩子的学生标识号（注意）
	 * 学生用户  studentid
	 * 校级教师 teacherid
	 * 校级管理员 null
	 * 区级用户 null
	 * 市级用户 null
	 */
	private String personid;
	/**
	 * 用户角色标识号
	 */
	private String roleId;
	
	//区级用户
	/**
	 * 区县代码
	 */
	private String discode;
	
	//校级用户
	/**
	 * 学校标识号
	 */
	private String cmis30id;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 校区标识号
	 */
	private String campuseId;
	/**
	 * 学段标识号
	 */
	private String levelid;
	/**
	 * 学段代码  只包含初高中（2012002 2012004 2012003）
	 */
	private String levelcode;
	
	//教师用户
	/**
	 * 教师标识号
	 */
	private String teacherid;
	/**
	 * 教师教育Id=登录用户名
	 */
	private String teachereduId;
	/**
	 * 教师名称
	 */
	private String teacherName;
	
	//学生用户
	/**
	 * 学生教育ID=username
	 */
	private String eduId;
	/*
	 *学生班主任所对应的用户标识号 
	 */
	private String masterid;
	
	private String className;
	private String gradeName;
	@Deprecated
	/**
	 * 作废
	 */
	private String levelName;
	
	public String getMasterid() {
		return masterid;
	}
	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}
	public String getLevelid() {
		return levelid;
	}
	public void setLevelid(String levelid) {
		this.levelid = levelid;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public void setEduId(String eduId) {
		this.eduId = eduId;
	}

	/**
	 * 该学生所在的当前年级标识号
	 */
	private String gradeid;
	/**
	 * 该学生所在的班级标识号
	 */
	private String classid;
	/**
	 * 该学生姓名
	 */
	private String studentName;
	/**
	 * 年级号
	 */
	private String gradenum;
	
	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getGradenum() {
		return gradenum;
	}
	public void setGradenum(String gradenum) {
		this.gradenum = gradenum;
	}
	public String getTermtype() {
		if(NestUtil.isEmpty(termId))return null;
		return termId.substring(4,5);
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * 是否是缓存中的对象 true表示缓存中读取出来，false表示新生代对象
	 */
	private Boolean isCacheObj=false;
	
	public Boolean getIsCacheObj() {
		return isCacheObj;
	}
	public void setIsCacheObj(Boolean isCacheObj) {
		this.isCacheObj = isCacheObj;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getSystemtype() {
		return systemtype;
	}
	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserRealType() {
		return userRealType;
	}
	public void setUserRealType(String userRealType) {
		this.userRealType = userRealType;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDiscode() {
		return discode;
	}
	public void setDiscode(String discode) {
		this.discode = discode;
	}
	public String getCmis30id() {
		return cmis30id;
	}
	public void setCmis30id(String cmis30id) {
		this.cmis30id = cmis30id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getCampuseId() {
		return campuseId;
	}
	public void setCampuseId(String campuseId) {
		this.campuseId = campuseId;
	}
	public String getLevelcode() {
		return levelcode;
	}
	public void setLevelcode(String levelcode) {
		this.levelcode = levelcode;
	}
	public String getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
	public String getTeachereduId() {
		return username;
	}
	
	public String getEduId() {
		return eduId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	@Override
	public String toString() {
		return "UserDto [termId=" + termId + ", termName=" + termName
				+ ", systemtype=" + systemtype + ", username=" + username
				+ ", password=" + password + ", userid=" + userid
				+ ", usertype=" + usertype + ", userRealType=" + userRealType
				+ ", unitid=" + unitid + ", used=" + used + ", personid="
				+ personid + ", roleId=" + roleId + ", discode=" + discode
				+ ", cmis30id=" + cmis30id + ", schoolName=" + schoolName
				+ ", campuseId=" + campuseId + ", levelid=" + levelid
				+ ", levelcode=" + levelcode + ", teacherid=" + teacherid
				+ ", teachereduId=" + teachereduId + ", teacherName="
				+ teacherName + ", eduId=" + eduId + ", masterid=" + masterid
				+ ", className=" + className + ", gradeName=" + gradeName
				+ ", levelName=" + levelName + ", gradeid=" + gradeid
				+ ", classid=" + classid + ", studentName=" + studentName
				+ ", gradenum=" + gradenum + ", isCacheObj=" + isCacheObj + "]";
	}
	
	/*@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		return ((UserDto)obj).toString().equals(this.toString());
	}
	@Override
	public String toString() {
		StringBuffer stb=new StringBuffer();
		return NoServiceUtil.md5(stb.append(this.campuseId).append(this.classid).append(this.className).append(this.cmis30id).append(this.discode).append(this.eduId).append(this.gradeid)
				.append(this.gradeName).append(this.gradenum).append(this.levelcode).append(this.levelcode).append(this.levelid).append(this.levelName).append(this.masterid).append(this.password).append(this.personid).append(this.roleId).append(this.schoolName)
				.append(this.studentName).append(this.systemtype).append(this.teachereduId).append(this.teacherid).append(this.teacherName).append(this.termId).append(this.termName).append(this.unitid).append(this.used)
				.append(this.used).append(this.userid).append(this.username).append(this.usertype).toString());
	}*/
	
}
