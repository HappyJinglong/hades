package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecordPackageDto implements Serializable{


	private Integer recordid;//记录袋标识
	
	private Integer studentid;//学生标识号
	
	private Integer semesterid;//学年学期标识号
	
	private Integer appraisaltypeid;//评价分类标识
	
	private String content;//内容
	
	private Integer appraseridentify;//评价人身份
	
	private String signer;//签名
	
	private Date signdate;//签字日期
	
	private Integer edu_id;//教育id
	
	private String discode;//discode
	
	private Integer cmis30id;//cmis30id
	
	private Integer partid;//分区id MOD("CMIS30ID",20)
	
	private String filenamerp;//文件名
	
	public List<AttachDto> attachListForFile = new ArrayList<AttachDto>();
	public List<AttachDto> attachListForImage = new ArrayList<AttachDto>();
	
	
	public List<AttachDto> getAttachListForFile() {
		return attachListForFile;
	}

	public void setAttachListForFile(List<AttachDto> attachListForFile) {
		this.attachListForFile = attachListForFile;
	}

	public List<AttachDto> getAttachListForImage() {
		return attachListForImage;
	}

	public void setAttachListForImage(List<AttachDto> attachListForImage) {
		this.attachListForImage = attachListForImage;
	}

	public String getFilenamerp() {
		return filenamerp;
	}

	public void setFilenamerp(String filenamerp) {
		this.filenamerp = filenamerp;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public Integer getSemesterid() {
		return semesterid;
	}

	public void setSemesterid(Integer semesterid) {
		this.semesterid = semesterid;
	}

	public Integer getAppraisaltypeid() {
		return appraisaltypeid;
	}

	public void setAppraisaltypeid(Integer appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAppraseridentify() {
		return appraseridentify;
	}

	public void setAppraseridentify(Integer appraseridentify) {
		this.appraseridentify = appraseridentify;
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

	public Integer getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(Integer edu_id) {
		this.edu_id = edu_id;
	}

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	public Integer getCmis30id() {
		return cmis30id;
	}

	public void setCmis30id(Integer cmis30id) {
		this.cmis30id = cmis30id;
	}

	public Integer getPartid() {
		return partid;
	}

	public void setPartid(Integer partid) {
		this.partid = partid;
	}

	public RecordPackageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RecordPackageDto [recordid=" + recordid + ", studentid="
				+ studentid + ", semesterid=" + semesterid
				+ ", appraisaltypeid=" + appraisaltypeid + ", content="
				+ content + ", appraseridentify=" + appraseridentify
				+ ", signer=" + signer + ", signdate=" + signdate + ", edu_id="
				+ edu_id + ", discode=" + discode + ", cmis30id=" + cmis30id
				+ ", partid=" + partid + "]";
	}
	
    public int compareTo(Object obj) {
    	if(obj==null) return -1;
		Integer appId = ((RecordPackageDto)obj).getRecordid()==null?-1:((RecordPackageDto)obj).getRecordid();
		Integer appId2 = this.getRecordid()==null?-2:this.getRecordid();
		return -appId.compareTo(appId2);
    }
}
