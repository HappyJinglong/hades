package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PracticesDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String practiceid;//社会实践活动标识号
	
	private String semesterid;//学年学期标识号
	
	private String discode;//
	
	private String cmis30id;//
	
	private String partid;//
	
	private String studentid;//学生标识号
	
	private String appraisaltypeid;//类型
	
	private String item1;//内容摘要
	
	private String item2;//
	
	private String item3;//
	
	private String item4;//
	
	private String item5;//
	
	private String item6;//
	
	private String item7;//
	
	private String item8;//
	
	private String item9;//
	
	private String item10;//
	
	private String item11;//
	
	private String item12;//
	
	private String item13;//
	
	private String item14;
	
	private String item15;
	
	private String item16;
	
	private String item17;
	
	private String item18;
	
	private String item19;

	private String item20;

	private String edu_id;//eud_id

	public List<AttachDto> attachListForFile = new ArrayList<AttachDto>();

	public List<PracticeappraisalDto> practiceappraisalList = new ArrayList<PracticeappraisalDto>();
	
	
	public List<AttachDto> getAttachListForFile() {
		return attachListForFile;
	}

	public void setAttachListForFile(List<AttachDto> attachListForFile) {
		this.attachListForFile = attachListForFile;
	}

	public List<PracticeappraisalDto> getPracticeappraisalList() {
		return practiceappraisalList;
	}

	public void setPracticeappraisalList(
			List<PracticeappraisalDto> practiceappraisalList) {
		this.practiceappraisalList = practiceappraisalList;
	}

	public String getPracticeid() {
		return practiceid;
	}

	public void setPracticeid(String practiceid) {
		this.practiceid = practiceid;
	}

	public String getSemesterid() {
		return semesterid;
	}

	public void setSemesterid(String semesterid) {
		this.semesterid = semesterid;
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

	public String getPartid() {
		return partid;
	}

	public void setPartid(String partid) {
		this.partid = partid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getAppraisaltypeid() {
		return appraisaltypeid;
	}

	public void setAppraisaltypeid(String appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getItem5() {
		return item5;
	}

	public void setItem5(String item5) {
		this.item5 = item5;
	}

	public String getItem6() {
		return item6;
	}

	public void setItem6(String item6) {
		this.item6 = item6;
	}

	public String getItem7() {
		return item7;
	}

	public void setItem7(String item7) {
		this.item7 = item7;
	}

	public String getItem8() {
		return item8;
	}

	public void setItem8(String item8) {
		this.item8 = item8;
	}

	public String getItem9() {
		return item9;
	}

	public void setItem9(String item9) {
		this.item9 = item9;
	}

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public String getItem11() {
		return item11;
	}

	public void setItem11(String item11) {
		this.item11 = item11;
	}

	public String getItem12() {
		return item12;
	}

	public void setItem12(String item12) {
		this.item12 = item12;
	}

	public String getItem13() {
		return item13;
	}

	public void setItem13(String item13) {
		this.item13 = item13;
	}

	public String getItem14() {
		return item14;
	}

	public void setItem14(String item14) {
		this.item14 = item14;
	}

	public String getItem15() {
		return item15;
	}

	public void setItem15(String item15) {
		this.item15 = item15;
	}

	public String getItem16() {
		return item16;
	}

	public void setItem16(String item16) {
		this.item16 = item16;
	}

	public String getItem17() {
		return item17;
	}

	public void setItem17(String item17) {
		this.item17 = item17;
	}

	public String getItem18() {
		return item18;
	}

	public void setItem18(String item18) {
		this.item18 = item18;
	}

	public String getItem19() {
		return item19;
	}

	public void setItem19(String item19) {
		this.item19 = item19;
	}

	public String getItem20() {
		return item20;
	}

	public void setItem20(String item20) {
		this.item20 = item20;
	}

	public String getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
	}

	@Override
	public String toString() {
		return "PracticesDto [practiceid=" + practiceid + ", semesterid="
				+ semesterid + ", discode=" + discode + ", cmis30id="
				+ cmis30id + ", partid=" + partid + ", studentid=" + studentid
				+ ", appraisaltypeid=" + appraisaltypeid + ", item1=" + item1
				+ ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4
				+ ", item5=" + item5 + ", item6=" + item6 + ", item7=" + item7
				+ ", item8=" + item8 + ", item9=" + item9 + ", item10="
				+ item10 + ", item11=" + item11 + ", item12=" + item12
				+ ", item13=" + item13 + ", item14=" + item14 + ", item15="
				+ item15 + ", item16=" + item16 + ", item17=" + item17
				+ ", item18=" + item18 + ", item19=" + item19 + ", item20="
				+ item20 + ", edu_id=" + edu_id + "]";
	}
	
    public int compareTo(Object obj) {
	if(obj==null) return -1;
	
	Integer appId = ((PracticesDto)obj).getPracticeid()==null?-1:Integer.valueOf(((PracticesDto)obj).getPracticeid());
	Integer appId2 = this.getPracticeid()==null?-2:Integer.valueOf(this.getPracticeid());
	return -appId.compareTo(appId2);
   }
	
	
}
