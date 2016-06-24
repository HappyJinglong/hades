package com.flyrish.hades.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelScoreDto implements Serializable{
	
	private String classModelStudentId;//对应classmodel里的classid
	
	private String matriculate_id;//校本课程标示号
	
	private String credit_id;//内置课程标识号
	
	private String  xuhao;  //序号
	
	private String edu_id;//教育id
	
	private String studentid;//学生id
	
	private String classid;  //班级id
	
	private String class_model_id; //班级模块id
	
	private String sement_model_id;  //模块学期id
	
	private String course_id;   //模块id
	
	private String segment_course_id;
	
	private String course_kind; //课程类别     
	
	private String course_name;  //模块名称     
	
	private String peacetime17; //考勤        
	
	private String period_count;  //学时
	
	private String studentName;//学生名字      
	
    private String peacetime1;//平时成绩1
	
	private String peacetime2;//平时成绩2
	
	private String peacetime3;//平时成绩3
	
	private String peacetime4;//平时成绩4
	
	private String peacetime5;//平时成绩5
	
	private String daily_behave;    //平时评定
	
	private String peacetime16; //平时表现
	
	private String cql;  //出勤率
	
	private String absence_ration;//缺勤率
	
	private String qqxs;  //缺勤学时
	
	private String examine_result;//考核成绩
	
	private String peacetime18;//补考
	
	private String credit_hour;//学分
	
	private String credit_source;  //免修     学分来源：1231701（学习）、1231710（免修）、1231720（导入）   
	 
	private String is_pass;  //学分是否通过    0为不通过  1为通过
	
	private String model_credit; //模块学分    
	
	
	private String level_name;  //会考成绩
	
	private String source;//来源
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 导入数据时所使用
	 */
	
	private String rownum;   //excel行数
	
	@Override
	public String toString() {
		return "ModelScoreDto [classModelStudentId=" + classModelStudentId
				+ ", matriculate_id=" + matriculate_id + ", credit_id="
				+ credit_id + ", xuhao=" + xuhao + ", edu_id=" + edu_id
				+ ", studentid=" + studentid + ", classid=" + classid
				+ ", class_model_id=" + class_model_id + ", sement_model_id="
				+ sement_model_id + ", course_id=" + course_id
				+ ", segment_course_id=" + segment_course_id + ", course_kind="
				+ course_kind + ", course_name=" + course_name
				+ ", peacetime17=" + peacetime17 + ", period_count="
				+ period_count + ", studentName=" + studentName
				+ ", peacetime1=" + peacetime1 + ", peacetime2=" + peacetime2
				+ ", peacetime3=" + peacetime3 + ", peacetime4=" + peacetime4
				+ ", peacetime5=" + peacetime5 + ", daily_behave="
				+ daily_behave + ", peacetime16=" + peacetime16 + ", cql="
				+ cql + ", absence_ration=" + absence_ration + ", qqxs=" + qqxs
				+ ", examine_result=" + examine_result + ", peacetime18="
				+ peacetime18 + ", credit_hour=" + credit_hour
				+ ", credit_source=" + credit_source + ", is_pass=" + is_pass
				+ ", model_credit=" + model_credit + ", level_name="
				+ level_name + ", source=" + source + ", rownum=" + rownum
				+ ", errorInfo=" + errorInfo + ", schoolyearName="
				+ schoolyearName + ", segmentName=" + segmentName + ", circle="
				+ circle + ", classNum=" + classNum + ", subjectName="
				+ subjectName + ", subjectId=" + subjectId
				+ ", generalExaminationScoreId=" + generalExaminationScoreId
				+ ", courseName=" + courseName + ", service_content="
				+ service_content + ", service_days=" + service_days
				+ ", termId=" + termId + ", xbXDname=" + xbXDname
				+ ", nzXDname=" + nzXDname + ", nzOrXbSub=" + nzOrXbSub
				+ ", hkSub=" + hkSub + ", nzScore=" + nzScore + ", xbScore="
				+ xbScore + ", hkScore=" + hkScore + ", xbList=" + xbList
				+ ", nzList=" + nzList + ", orderBy=" + orderBy
				+ ", schoolyear=" + schoolyear + "]";
	}
	private String errorInfo;   //错误信息
	
	private String schoolyearName ;  //学年
	
	private String segmentName ;  //学段
	
	private String circle ; //界别  
	
	private String  classNum;  //班级数字
	
	private String  subjectName;  //科目
	
	private String subjectId;  //科目id
	
	private String generalExaminationScoreId;  //会考成绩id
	
	private String  courseName;   //模块名称
	
	private String  service_content; //研究性学习课题/社区服务、社会实践内容
	
	private String service_days;  //研究性学习学时/社区服务、社会实践天数
	
	private String termId;
	
	private String xbXDname;
	
	private String nzXDname;
	
	private String nzOrXbSub;
	
	private String hkSub;
	
	private String nzScore;
	
	private String xbScore;
	
	private String hkScore;
	
	private List<ModelScoreDto>xbList = new ArrayList<ModelScoreDto>();
	
	private List<ModelScoreDto>nzList = new ArrayList<ModelScoreDto>();
	
	private Integer orderBy;
	
	
	private String schoolyear;
	
	
	
	public String getMatriculate_id() {
		return matriculate_id;
	}

	public void setMatriculate_id(String matriculate_id) {
		this.matriculate_id = matriculate_id;
	}

	public String getCredit_id() {
		return credit_id;
	}

	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}

	public String getSchoolyear() {
		return schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public List<ModelScoreDto> getXbList() {
		return xbList;
	}

	public String getClassModelStudentId() {
		return classModelStudentId;
	}

	public void setClassModelStudentId(String classModelStudentId) {
		this.classModelStudentId = classModelStudentId;
	}

	public void setXbList(List<ModelScoreDto> xbList) {
		this.xbList = xbList;
	}

	public List<ModelScoreDto> getNzList() {
		return nzList;
	}

	public void setNzList(List<ModelScoreDto> nzList) {
		this.nzList = nzList;
	}

	public String getNzScore() {
		return nzScore;
	}

	public void setNzScore(String nzScore) {
		this.nzScore = nzScore;
	}

	public String getXbScore() {
		return xbScore;
	}

	public void setXbScore(String xbScore) {
		this.xbScore = xbScore;
	}

	public String getHkScore() {
		return hkScore;
	}

	public void setHkScore(String hkScore) {
		this.hkScore = hkScore;
	}

	public String getXbXDname() {
		return xbXDname;
	}

	public void setXbXDname(String xbXDname) {
		this.xbXDname = xbXDname;
	}

	public String getNzXDname() {
		return nzXDname;
	}

	public void setNzXDname(String nzXDname) {
		this.nzXDname = nzXDname;
	}

	public String getNzOrXbSub() {
		return nzOrXbSub;
	}

	public void setNzOrXbSub(String nzOrXbSub) {
		this.nzOrXbSub = nzOrXbSub;
	}

	public String getHkSub() {
		return hkSub;
	}

	public void setHkSub(String hkSub) {
		this.hkSub = hkSub;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public String getSement_model_id() {
		return sement_model_id;
	}

	public void setSement_model_id(String sement_model_id) {
		this.sement_model_id = sement_model_id;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}


	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getPeriod_count() {
		return period_count;
	}

	public void setPeriod_count(String period_count) {
		this.period_count = period_count;
	}

	public String getSegment_course_id() {
		return segment_course_id;
	}

	public void setSegment_course_id(String segment_course_id) {
		this.segment_course_id = segment_course_id;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClass_model_id() {
		return class_model_id;
	}

	public void setClass_model_id(String class_model_id) {
		this.class_model_id = class_model_id;
	}

	public String getCourse_kind() {
		return course_kind;
	}

	public void setCourse_kind(String course_kind) {
		this.course_kind = course_kind;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getPeacetime17() {
		return peacetime17;
	}

	public void setPeacetime17(String peacetime17) {
		this.peacetime17 = peacetime17;
	}

	public String getAbsence_ration() {
		return absence_ration;
	}

	public void setAbsence_ration(String absence_ration) {
		this.absence_ration = absence_ration;
	}

	public String getModel_credit() {
		return model_credit;
	}

	public void setModel_credit(String model_credit) {
		this.model_credit = model_credit;
	}


	public String getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPeacetime1() {
		return peacetime1;
	}

	public void setPeacetime1(String peacetime1) {
		this.peacetime1 = peacetime1;
	}

	public String getPeacetime2() {
		return peacetime2;
	}

	public void setPeacetime2(String peacetime2) {
		this.peacetime2 = peacetime2;
	}

	public String getPeacetime3() {
		return peacetime3;
	}

	public void setPeacetime3(String peacetime3) {
		this.peacetime3 = peacetime3;
	}

	public String getPeacetime4() {
		return peacetime4;
	}

	public void setPeacetime4(String peacetime4) {
		this.peacetime4 = peacetime4;
	}

	public String getPeacetime5() {
		return peacetime5;
	}

	public void setPeacetime5(String peacetime5) {
		this.peacetime5 = peacetime5;
	}

	public String getDaily_behave() {
		return daily_behave;
	}

	public void setDaily_behave(String daily_behave) {
		this.daily_behave = daily_behave;
	}

	public String getPeacetime16() {
		return peacetime16;
	}

	public void setPeacetime16(String peacetime16) {
		this.peacetime16 = peacetime16;
	}

	public String getCql() {
		return cql;
	}

	public void setCql(String cql) {
		this.cql = cql;
	}

	public String getQqxs() {
		return qqxs;
	}

	public void setQqxs(String qqxs) {
		this.qqxs = qqxs;
	}

	public String getExamine_result() {
		return examine_result;
	}

	public void setExamine_result(String examine_result) {
		this.examine_result = examine_result;
	}

	public String getPeacetime18() {
		return peacetime18;
	}

	public void setPeacetime18(String peacetime18) {
		this.peacetime18 = peacetime18;
	}

	public String getCredit_hour() {
		return credit_hour;
	}

	public void setCredit_hour(String credit_hour) {
		this.credit_hour = credit_hour;
	}

	public String getCredit_source() {
		return credit_source;
	}

	public void setCredit_source(String credit_source) {
		this.credit_source = credit_source;
	}

	public String getIs_pass() {
		return is_pass;
	}

	public void setIs_pass(String is_pass) {
		this.is_pass = is_pass;
	}

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	public String getSchoolyearName() {
		return schoolyearName;
	}

	public void setSchoolyearName(String schoolyearName) {
		this.schoolyearName = schoolyearName;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getService_content() {
		return service_content;
	}

	public void setService_content(String service_content) {
		this.service_content = service_content;
	}

	public String getService_days() {
		return service_days;
	}

	public void setService_days(String service_days) {
		this.service_days = service_days;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getGeneralExaminationScoreId() {
		return generalExaminationScoreId;
	}

	public void setGeneralExaminationScoreId(String generalExaminationScoreId) {
		this.generalExaminationScoreId = generalExaminationScoreId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		ModelScoreDto other = (ModelScoreDto) obj;
		if (absence_ration == null) {
			if (other.absence_ration != null)
				return false;
		} else if (!absence_ration.equals(other.absence_ration))
			return false;
		if (circle == null) {
			if (other.circle != null)
				return false;
		} else if (!circle.equals(other.circle))
			return false;
		if (classModelStudentId == null) {
			if (other.classModelStudentId != null)
				return false;
		} else if (!classModelStudentId.equals(other.classModelStudentId))
			return false;
		if (classNum == null) {
			if (other.classNum != null)
				return false;
		} else if (!classNum.equals(other.classNum))
			return false;
		if (class_model_id == null) {
			if (other.class_model_id != null)
				return false;
		} else if (!class_model_id.equals(other.class_model_id))
			return false;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (course_id == null) {
			if (other.course_id != null)
				return false;
		} else if (!course_id.equals(other.course_id))
			return false;
		if (course_kind == null) {
			if (other.course_kind != null)
				return false;
		} else if (!course_kind.equals(other.course_kind))
			return false;
		if (course_name == null) {
			if (other.course_name != null)
				return false;
		} else if (!course_name.equals(other.course_name))
			return false;
		if (cql == null) {
			if (other.cql != null)
				return false;
		} else if (!cql.equals(other.cql))
			return false;
		if (credit_hour == null) {
			if (other.credit_hour != null)
				return false;
		} else if (!credit_hour.equals(other.credit_hour))
			return false;
		if (credit_source == null) {
			if (other.credit_source != null)
				return false;
		} else if (!credit_source.equals(other.credit_source))
			return false;
		if (daily_behave == null) {
			if (other.daily_behave != null)
				return false;
		} else if (!daily_behave.equals(other.daily_behave))
			return false;
		if (edu_id == null) {
			if (other.edu_id != null)
				return false;
		} else if (!edu_id.equals(other.edu_id))
			return false;
		if (errorInfo == null) {
			if (other.errorInfo != null)
				return false;
		} else if (!errorInfo.equals(other.errorInfo))
			return false;
		if (examine_result == null) {
			if (other.examine_result != null)
				return false;
		} else if (!examine_result.equals(other.examine_result))
			return false;
		if (generalExaminationScoreId == null) {
			if (other.generalExaminationScoreId != null)
				return false;
		} else if (!generalExaminationScoreId
				.equals(other.generalExaminationScoreId))
			return false;
		if (hkScore == null) {
			if (other.hkScore != null)
				return false;
		} else if (!hkScore.equals(other.hkScore))
			return false;
		if (hkSub == null) {
			if (other.hkSub != null)
				return false;
		} else if (!hkSub.equals(other.hkSub))
			return false;
		if (is_pass == null) {
			if (other.is_pass != null)
				return false;
		} else if (!is_pass.equals(other.is_pass))
			return false;
		if (level_name == null) {
			if (other.level_name != null)
				return false;
		} else if (!level_name.equals(other.level_name))
			return false;
		if (model_credit == null) {
			if (other.model_credit != null)
				return false;
		} else if (!model_credit.equals(other.model_credit))
			return false;
		if (nzOrXbSub == null) {
			if (other.nzOrXbSub != null)
				return false;
		} else if (!nzOrXbSub.equals(other.nzOrXbSub))
			return false;
		if (nzScore == null) {
			if (other.nzScore != null)
				return false;
		} else if (!nzScore.equals(other.nzScore))
			return false;
		if (nzXDname == null) {
			if (other.nzXDname != null)
				return false;
		} else if (!nzXDname.equals(other.nzXDname))
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (peacetime1 == null) {
			if (other.peacetime1 != null)
				return false;
		} else if (!peacetime1.equals(other.peacetime1))
			return false;
		if (peacetime16 == null) {
			if (other.peacetime16 != null)
				return false;
		} else if (!peacetime16.equals(other.peacetime16))
			return false;
		if (peacetime17 == null) {
			if (other.peacetime17 != null)
				return false;
		} else if (!peacetime17.equals(other.peacetime17))
			return false;
		if (peacetime18 == null) {
			if (other.peacetime18 != null)
				return false;
		} else if (!peacetime18.equals(other.peacetime18))
			return false;
		if (peacetime2 == null) {
			if (other.peacetime2 != null)
				return false;
		} else if (!peacetime2.equals(other.peacetime2))
			return false;
		if (peacetime3 == null) {
			if (other.peacetime3 != null)
				return false;
		} else if (!peacetime3.equals(other.peacetime3))
			return false;
		if (peacetime4 == null) {
			if (other.peacetime4 != null)
				return false;
		} else if (!peacetime4.equals(other.peacetime4))
			return false;
		if (peacetime5 == null) {
			if (other.peacetime5 != null)
				return false;
		} else if (!peacetime5.equals(other.peacetime5))
			return false;
		if (period_count == null) {
			if (other.period_count != null)
				return false;
		} else if (!period_count.equals(other.period_count))
			return false;
		if (qqxs == null) {
			if (other.qqxs != null)
				return false;
		} else if (!qqxs.equals(other.qqxs))
			return false;
		if (rownum == null) {
			if (other.rownum != null)
				return false;
		} else if (!rownum.equals(other.rownum))
			return false;
		if (schoolyear == null) {
			if (other.schoolyear != null)
				return false;
		} else if (!schoolyear.equals(other.schoolyear))
			return false;
		if (schoolyearName == null) {
			if (other.schoolyearName != null)
				return false;
		} else if (!schoolyearName.equals(other.schoolyearName))
			return false;
		if (segmentName == null) {
			if (other.segmentName != null)
				return false;
		} else if (!segmentName.equals(other.segmentName))
			return false;
		if (segment_course_id == null) {
			if (other.segment_course_id != null)
				return false;
		} else if (!segment_course_id.equals(other.segment_course_id))
			return false;
		if (sement_model_id == null) {
			if (other.sement_model_id != null)
				return false;
		} else if (!sement_model_id.equals(other.sement_model_id))
			return false;
		if (service_content == null) {
			if (other.service_content != null)
				return false;
		} else if (!service_content.equals(other.service_content))
			return false;
		if (service_days == null) {
			if (other.service_days != null)
				return false;
		} else if (!service_days.equals(other.service_days))
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		if (studentid == null) {
			if (other.studentid != null)
				return false;
		} else if (!studentid.equals(other.studentid))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (termId == null) {
			if (other.termId != null)
				return false;
		} else if (!termId.equals(other.termId))
			return false;
		if (xbScore == null) {
			if (other.xbScore != null)
				return false;
		} else if (!xbScore.equals(other.xbScore))
			return false;
		if (xbXDname == null) {
			if (other.xbXDname != null)
				return false;
		} else if (!xbXDname.equals(other.xbXDname))
			return false;
		if (xuhao == null) {
			if (other.xuhao != null)
				return false;
		} else if (!xuhao.equals(other.xuhao))
			return false;
		return true;
	}
	
	
	

}
