package com.flyrish.hades.dto;

import java.io.Serializable;

public class HOinfromreadDto implements Serializable{
	/**
	 * 通知公告已读标识号
	 */
	private String informreadid;
	/**
	 * 通知公告ID
	 */
	private String inform_id;
	/**
	 * 用户标识号
	 */
	private String userid;
	public String getInformreadid() {
		return informreadid;
	}
	public void setInformreadid(String informreadid) {
		this.informreadid = informreadid;
	}
	public String getInform_id() {
		return inform_id;
	}
	public void setInform_id(String inform_id) {
		this.inform_id = inform_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
