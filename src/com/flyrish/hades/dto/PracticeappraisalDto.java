package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Date;

public class PracticeappraisalDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appraisalid;
	
	private String practiceid;
	
	private String signer;
	
	private Date signdate; 
	
	private String content;

	public String getAppraisalid() {
		return appraisalid;
	}

	public void setAppraisalid(String appraisalid) {
		this.appraisalid = appraisalid;
	}

	public String getPracticeid() {
		return practiceid;
	}

	public void setPracticeid(String practiceid) {
		this.practiceid = practiceid;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public Date getSigndate() {
		return signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PracticeappraisalDto [appraisalid=" + appraisalid
				+ ", practiceid=" + practiceid + ", signer=" + signer
				+ ", signdate=" + signdate + ", content=" + content + "]";
	}
	
	public int compareTo(Object obj) {
		if(obj==null) return -1;
		
		Integer appId = ((PracticeappraisalDto)obj).getAppraisalid()==null?-1:Integer.valueOf(((PracticeappraisalDto)obj).getAppraisalid());
		Integer appId2 = this.getAppraisalid()==null?-2:Integer.valueOf(this.getAppraisalid());
		return -appId.compareTo(appId2);
   }
	
	
}
