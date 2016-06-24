package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentxieDto implements Serializable{
	/**
	 * 教育id
	 */
		private String eduid;
	
		public String getEduid() {
			return eduid;
		}
	
		public void setEduid(String eduid) {
			this.eduid = eduid;
		}

	   /**
	    * 学生的id
	    */
		private Integer studentid;
		/**
		 * 学生的班级id
		 */
		private Integer classid;
		/**
		 * 学生的年级id
		 */
		private Integer gradeid;
		/**
		 * 学生的学期id
		 */
		private Integer termid;
		/**
		 * 学端id
		 */
		private Integer levelid;
		/**
		 * 区校区id
		 */
		private Integer campusid;
		/**
		 * 校区id
		 */
		private Integer cmis30id;
		/**
		 * 学生的名字
		 */
		private String name;
		/**
		 * 班级名字
		 */
		private String classname;
		
		/**
		 * 年级名字
		 */
		private String gradename;
		/**
		 * 学段名字
		 */
		private String levelname;
		
		/**
		 * 校类别名字  注意 在表中为name
		 */
		private String campusname;
		/**
		 * 校区名字  注意 在表中为name
		 */
		private String schoolname;
	     
		private boolean leaf = false;
		/**
	     * 菜单用
	     */
	    private String href;
	    /**
	     * 学生照片路径
	     */
	    private String photoUrl;
		
	    
	    private List<AppraisalxieDto> aInfos = new ArrayList<AppraisalxieDto>();
	    
	    
	    private List<PartInfoXieDto> czaInfos = new ArrayList<PartInfoXieDto>();
	    
	    public List<PartInfoXieDto> getCzaInfos() {
			return czaInfos;
		}

		public void setCzaInfos(List<PartInfoXieDto> czaInfos) {
			this.czaInfos = czaInfos;
		}

		public List<AppraisalxieDto> getaInfos() {
			return aInfos;
		}

		public void setaInfos(List<AppraisalxieDto> aInfos) {
			this.aInfos = aInfos;
		}

		public String getHref() {
			return href;
		}

		public String getPhotoUrl() {
			return photoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		public Integer getCmis30id() {
			return cmis30id;
		}

		public void setCmis30id(Integer cmis30id) {
			this.cmis30id = cmis30id;
		}

		public String getSchoolname() {
			return schoolname;
		}

		public void setSchoolname(String schoolname) {
			this.schoolname = schoolname;
		}

		public Integer getStudentid() {
			return studentid;
		}

		public void setStudentid(Integer studentid) {
			this.studentid = studentid;
		}

		public Integer getClassid() {
			return classid;
		}

		public void setClassid(Integer classid) {
			this.classid = classid;
		}

		public Integer getGradeid() {
			return gradeid;
		}

		public void setGradeid(Integer gradeid) {
			this.gradeid = gradeid;
		}

		public Integer getLevelid() {
			return levelid;
		}

		public void setLevelid(Integer levelid) {
			this.levelid = levelid;
		}

		public Integer getCampusid() {
			return campusid;
		}

		public void setCampusid(Integer campusid) {
			this.campusid = campusid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		public String getGradename() {
			return gradename;
		}

		public void setGradename(String gradename) {
			this.gradename = gradename;
		}

		public String getLevelname() {
			return levelname;
		}

		public void setLevelname(String levelname) {
			this.levelname = levelname;
		}

		public String getCampusname() {
			return campusname;
		}

		public void setCampusname(String campusname) {
			this.campusname = campusname;
		}
		public Integer getTermid() {
			return termid;
		}

		public void setTermid(Integer termid) {
			this.termid = termid;
		}

		@Override
		public String toString() {
			return "StudentxieDto [studentid=" + studentid + ", classid="
					+ classid + ", gradeid=" + gradeid + ", termid=" + termid
					+ ", levelid=" + levelid + ", campusid=" + campusid
					+ ", cmis30id=" + cmis30id + ", name=" + name
					+ ", classname=" + classname + ", gradename=" + gradename
					+ ", levelname=" + levelname + ", campusname=" + campusname
					+ ", schoolname=" + schoolname + "]";
		}
    
}
