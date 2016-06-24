package com.flyrish.hades.dto;

import java.io.Serializable;
/**
 * 高综_评价
 * @author Administrator
 *
 */
public class SchoolNameDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8095761651583494048L;

    private  String shoolname;
    
    private  String cimi30;

	public String getShoolname() {
		return shoolname;
	}

	public void setShoolname(String shoolname) {
		this.shoolname = shoolname;
	}

	public String getCimi30() {
		return cimi30;
	}

	public void setCimi30(String cimi30) {
		this.cimi30 = cimi30;
	}
    
	
}
