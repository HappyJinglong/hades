package com.flyrish.hades.dto;

import java.io.Serializable;

public class EdusysDto implements Serializable,Comparable{
	private String edusysId;
	private String edusysName;
	private String campuseId;
	
	private String levelCode;
	
	
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getEdusysId() {
		return edusysId;
	}
	public void setEdusysId(String edusysId) {
		this.edusysId = edusysId;
	}
	public String getEdusysName() {
		return edusysName;
	}
	public void setEdusysName(String edusysName) {
		this.edusysName = edusysName;
	}
	public String getCampuseId() {
		return campuseId;
	}
	public void setCampuseId(String campuseId) {
		this.campuseId = campuseId;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		try{
			 if(levelCode.equals(((EdusysDto)obj).getLevelCode())){
				 return edusysId.equals(((EdusysDto)obj).getEdusysId());
			 }else{
				 return false;
			 }
		}catch(Exception e){
			return super.equals(obj);
		}
	}
	@Override
	public int hashCode() {
		try{
			return levelCode.hashCode();
		}catch(Exception e){
			return super.hashCode();
		}
	}
	public int compareTo(Object obj) {
		if(obj==null) return -1;
		if(levelCode.compareTo(((EdusysDto)obj).getLevelCode())==0){
			return edusysId.compareTo(((EdusysDto)obj).getEdusysId());
		}else{
			return levelCode.compareTo(((EdusysDto)obj).getLevelCode());
		}
	}
	
}
