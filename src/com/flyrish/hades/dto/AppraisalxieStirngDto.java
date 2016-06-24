package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Date;

public class AppraisalxieStirngDto implements Serializable{
          
	  
		private String eduid;
		// 评价人
		private String appraser;
		//被评价人名字
		private String evaluateName;
		// 标识
		private String  studentid;
		
		//评价人身份  
		private String  appraseridentify;
		private String  appraisaltypeid;
		private String   semesterid;
		// 评价时间
		private Date signDate;
		//评价id
		private  String  appraiserrid;
		
		//评价idstring类型
		private  String  appraiserridSting;

		/**
	     * 学生照片路径
	     */
	    private String photoUrl;
	    private Integer fakeId;

		/**
		 * 被评价人ID.
		 */
		private String evaluatedPersonID;
		
		// 评语
		private String apprasial;

		private String apprasialid;
		
		
		private String sumnaber;
		
		
		
		
		public String getSumnaber() {
			return sumnaber;
		}

		public void setSumnaber(String sumnaber) {
			this.sumnaber = sumnaber;
		}

		public String getApprasialid() {
			return apprasialid;
		}

		public void setApprasialid(String apprasialid) {
			this.apprasialid = apprasialid;
		}

		public String getEduid() {
			return eduid;
		}

		public void setEduid(String eduid) {
			this.eduid = eduid;
		}

		public String getAppraser() {
			return appraser;
		}

		public void setAppraser(String appraser) {
			this.appraser = appraser;
		}

		public String getEvaluateName() {
			return evaluateName;
		}

		public void setEvaluateName(String evaluateName) {
			this.evaluateName = evaluateName;
		}

		public String getStudentid() {
			return studentid;
		}

		public void setStudentid(String studentid) {
			this.studentid = studentid;
		}

		public String getAppraseridentify() {
			return appraseridentify;
		}

		public void setAppraseridentify(String appraseridentify) {
			this.appraseridentify = appraseridentify;
		}

		public String getAppraisaltypeid() {
			return appraisaltypeid;
		}

		public void setAppraisaltypeid(String appraisaltypeid) {
			this.appraisaltypeid = appraisaltypeid;
		}

		public String getSemesterid() {
			return semesterid;
		}

		public void setSemesterid(String semesterid) {
			this.semesterid = semesterid;
		}

		public Date getSignDate() {
			return signDate;
		}

		public void setSignDate(Date signDate) {
			this.signDate = signDate;
		}

		public String getAppraiserrid() {
			return appraiserrid;
		}

		public void setAppraiserrid(String appraiserrid) {
			this.appraiserrid = appraiserrid;
		}

		public String getAppraiserridSting() {
			return appraiserridSting;
		}

		public void setAppraiserridSting(String appraiserridSting) {
			this.appraiserridSting = appraiserridSting;
		}

		public String getPhotoUrl() {
			return photoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}

		public Integer getFakeId() {
			return fakeId;
		}

		public void setFakeId(Integer fakeId) {
			this.fakeId = fakeId;
		}

		public String getEvaluatedPersonID() {
			return evaluatedPersonID;
		}

		public void setEvaluatedPersonID(String evaluatedPersonID) {
			this.evaluatedPersonID = evaluatedPersonID;
		}

		public String getApprasial() {
			return apprasial;
		}

		public void setApprasial(String apprasial) {
			this.apprasial = apprasial;
		}

		
		
		
		
		  public int compareTo(Object obj) {
		    	if(obj==null) return -1;
		    	//appraiserrid
		    	 String appId = ((AppraisalxieStirngDto)obj).getApprasialid()==null?"-1":((AppraisalxieStirngDto)obj).getApprasialid();
		    	String  appId2 = this.getApprasialid()==null?"-2":this.getApprasialid();
		    	return -appId.compareTo(appId2);
		    }


	  
		
	
}
