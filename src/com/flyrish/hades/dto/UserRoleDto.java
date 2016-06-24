package com.flyrish.hades.dto;

import java.io.Serializable;

import org.nestframework.utils.NestUtil;

public class UserRoleDto implements Comparable,Serializable{

	private String roleId;
	
	private String roleName;
	
	private String roleType;
	
	private String levelId;//学校学段标号
	
	private String levelName;//学段名称
	
	private String realRoletype;
	

	public String getRealRoletype() {
		return realRoletype;
	}

	public void setRealRoletype(String realRoletype) {
		this.realRoletype = realRoletype;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		try{
			return roleId.equals(((UserRoleDto)obj).getRoleId());
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public int hashCode() {
		try{
			return roleId.hashCode();
		}catch(Exception e){
			return super.hashCode();
		}
	}

	public int compareTo(Object obj) {
		if(obj==null) return -1;
		return roleId.compareTo(((UserRoleDto)obj).getRoleId());
	}
	
}
