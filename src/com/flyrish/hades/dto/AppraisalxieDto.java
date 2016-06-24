package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.Date;

public class AppraisalxieDto implements Serializable{
          
	  
		private String eduid;
		public String getEduid() {
			return eduid;
		}

		public void setEduid(String eduid) {
			this.eduid = eduid;
		}


		// 评价人
		private String appraser;
		//被评价人名字
		private String evaluateName;
		// 标识
		private Integer studentid;
		
		//评价人身份  
		private Integer appraseridentify;
		private Integer appraisaltypeid;
		private Integer  semesterid;
		// 评价时间
		private Date signDate;
		//评价id
		private  Integer appraiserrid;
		
		//评价idstring类型
		private  String  appraiserridSting;
		
		public String getAppraiserridSting() {
			return appraiserridSting;
		}

		public void setAppraiserridSting(String appraiserridSting) {
			this.appraiserridSting = appraiserridSting;
		}


		/**
	     * 学生照片路径
	     */
	    private String photoUrl;
	    private Integer fakeId;
		public Integer getFakeId() {
			return fakeId;
		}

		public void setFakeId(Integer fakeId) {
			this.fakeId = fakeId;
		}

		public String getPhotoUrl() {
			return photoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}

		public Integer getAppraiserrid() {
			return appraiserrid;
		}

		public void setAppraiserrid(Integer appraiserrid) {
			this.appraiserrid = appraiserrid;
		}

		public Integer getAppraseridentify() {
			return appraseridentify;
		}

		public void setAppraseridentify(Integer appraseridentify) {
			this.appraseridentify = appraseridentify;
		}

		public Integer getAppraisaltypeid() {
			return appraisaltypeid;
		}

		public void setAppraisaltypeid(Integer appraisaltypeid) {
			this.appraisaltypeid = appraisaltypeid;
		}

		public Integer getSemesterid() {
			return semesterid;
		}

		public void setSemesterid(Integer semesterid) {
			this.semesterid = semesterid;
		}

		public Date getSignDate() {
			return signDate;
		}

		public void setSignDate(Date signDate) {
			this.signDate = signDate;
		}

		public Integer getStudentid() {
			return studentid;
		}

		public void setStudentid(Integer studentid) {
			this.studentid = studentid;
		}

		  
		/**
		 * 被评价人ID.
		 */
		private String evaluatedPersonID;
		
		// 评语
		private String apprasial;

		public String getAppraser() {
			return appraser;
		}

		public void setAppraser(String appraser) {
			this.appraser = appraser;
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
		
		private Integer apprasialid;
			   
		public Integer getApprasialid() {
			return apprasialid;
		}
	
		public void setApprasialid(Integer apprasialid) {
			this.apprasialid = apprasialid;
		}
		public String getEvaluateName() {
			return evaluateName;
		}

		public void setEvaluateName(String evaluateName) {
			this.evaluateName = evaluateName;
		}

		@Override
		public String toString() {
			return "AppraisalxieDto [appraser=" + appraser + ", evaluateName="
					+ evaluateName + ", studentid=" + studentid
					+ ", appraseridentify=" + appraseridentify
					+ ", appraisaltypeid=" + appraisaltypeid + ", semesterid="
					+ semesterid + ", signDate=" + signDate + ", appraiserrid="
					+ appraiserrid + ", evaluatedPersonID=" + evaluatedPersonID
					+ ", apprasial=" + apprasial + ", apprasialid="
					+ apprasialid + "]";
		}

		  public int compareTo(Object obj) {
		    	if(obj==null) return -1;
		    	
		    	Integer appId = ((AppraisalxieDto)obj).getApprasialid()==null?-1:((AppraisalxieDto)obj).getApprasialid();
		    	Integer appId2 = this.getApprasialid()==null?-2:this.getApprasialid();
		    	return -appId.compareTo(appId2);
		    }


	  
		
	
}
