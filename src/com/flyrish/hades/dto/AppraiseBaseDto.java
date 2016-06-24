package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.nestframework.utils.NestUtil;

/**
 * 评价类基础dto
 * @author zengdeqiang
 *
 */
public class AppraiseBaseDto implements Serializable{
	//评价主键标识号
	private String apprasialid;
	//评价主内容
	private String apprasial;
	//评价人身份=>指内容 同学，班主任，本人等等，此外还可以当做二级菜单
	private String appraseridentify;
	//评价人身份码
	private String appraseridentifynum;
	//签名
	private String appraser;
	//评价人标识号
	private String appraserid;
	//被评价人的教育Id
	private String edu_id;
	//评价时间
	private String signdate;
	//学年学期标识号
	private String termid;
	//分类标识号
	private String appraisaltypeid;
	//评语
	private String appraisal;
	//过程记录
	private String processdesc;
	//所属栏目名称
	private String columNumberName;
	
	
	//体质健康标准ID
	private String healthstdid;
	//年份
	private String schoolyear;
	//年级号
	private String gradenum;
	//年级名称
	private String gradename;
	//学年总分
	private String yearScore;
	//等级评定
	private String yearGrade;
	//体质健康明细ID
	private String healthid;
	//指标名称
	private String itemName;
	//指标类型
	private String itemType;
	//指标成绩
	private String itemMark;
	//标准体重
	private String itemMarkStandardWeight;
	//体重
	private String itemMarkWeight;
	//指标得分
	private String itemScore;
	//指标等级
	private String itemGrade;
	//奖励得分
	private String rewardSscore;
	
	
	/**
	 * 初中评价表中的数据
	 */
	//一级栏目标号
	private String onePartId;
	//一级栏目名称
	private String partName;
	//二级栏目标号
	private String twoPartId;
	//二级栏目名称
	private String twoPartName;
	//栏目标号
	private String partId;
	//主题
	private String topic;
	//栏目内容
	private String partInfo;
	//评价人类型
	private String writeMan;
	//关键字
	private String keyword;
	//合作人
	private String cooperationMan;
	//地点
	private String address;
	//userid
	private String userid;
	
	//初中综合实践活动--起止时间
	private String startDate;
	private String endDate;
	
	//初中附件集合
	private List<AppraiseBaseDto> attachmentDtos=new ArrayList<AppraiseBaseDto>(0);
	
	
	//评价人签名
	private String signerName;
	//学科标号
	private String subjectId;
	//评价日期
	private String createDate;
	//学科名称
	private String subjectName;
	//附件标号
	private String attachmentId;
	//附件名称
	private String attachmentName;
	//附件路径
	private String attachmentPath;
	
	
	
	//综合实践活动的id
	private String practiceid;
	//综合实践活动(高中)--自我评价查询
	private List<AppraiseBaseDto> practicesSelfAppraiseDtos=new ArrayList<AppraiseBaseDto>(0);
	
	
	

	public List<AppraiseBaseDto> getPracticesSelfAppraiseDtos() {
		return practicesSelfAppraiseDtos;
	}

	public void setPracticesSelfAppraiseDtos(
			List<AppraiseBaseDto> practicesSelfAppraiseDtos) {
		this.practicesSelfAppraiseDtos = practicesSelfAppraiseDtos;
	}

	public String getColumNumberName() {
		return columNumberName;
	}

	public void setColumNumberName(String columNumberName) {
		this.columNumberName = columNumberName;
	}

	public String getAppraseridentifynum() {
		return appraseridentifynum;
	}

	public void setAppraseridentifynum(String appraseridentifynum) {
		this.appraseridentifynum = appraseridentifynum;
	}
	//是否为只读
	private Boolean isReadOnly=true;
	//研究性学习、社区服务、社会实践活动=》自我评价的标识号
	private String myselfappraserid;
	//研究性学习、社区服务、社会实践活动=》自我评价的内容
	private String myselfapprasercontent;
	
	public String getMyselfappraserid() {
		return myselfappraserid;
	}

	public void setMyselfappraserid(String myselfappraserid) {
		this.myselfappraserid = myselfappraserid;
	}

	public String getMyselfapprasercontent() {
		return myselfapprasercontent;
	}

	public void setMyselfapprasercontent(String myselfapprasercontent) {
		this.myselfapprasercontent = myselfapprasercontent;
	}

	public String getAppraserid() {
		return appraserid;
	}

	public void setAppraserid(String appraserid) {
		this.appraserid = appraserid;
	}
	/**
	 * 学科学习过程记录：科目
	 * 研究性学习：题目
	 * 社区服务:选择次数
	 * 社会实践活动:选择活动种
	 */
	private String item1;
	/**
	 * 研究性学习：合伙人
	 * 社区服务:服务社区名称
	 * 社会实践活动:成果形成
	 */
	private String item2;
	/**
	 * 研究性学习：总学时数
	 * 社区服务:服务社区联系电话
	 * 社会实践活动:地点
	 */
	private String item3;
	/**
	 * 研究性学习：实施路径
	 * 社区服务:服务时数
	 * 社会实践活动:完成学时
	 */
	private String item4;
	/**
	 * 社区服务:服务时间
	 */
	private String item5;
	/**
	 * 附件集合
	 */
	private List<AttachFileDto> attachFileDtos=new ArrayList<AttachFileDto>(0);
	
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

	public List<AttachFileDto> getAttachFileDtos() {
		if(null!=attachFileDtos && attachFileDtos.size()>0){
			Collections.sort(attachFileDtos, compareByAttachId_CZ);
		}
		return attachFileDtos;
	}

	public void setAttachFileDtos(List<AttachFileDto> attachFileDtos) {
		this.attachFileDtos = attachFileDtos;
	}

	public Boolean getIsReadOnly() {
		return isReadOnly;
	}

	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public String getApprasialid() {
		return apprasialid;
	}
	
	public String getAppraisaltypeid() {
		return appraisaltypeid;
	}

	public void setAppraisaltypeid(String appraisaltypeid) {
		this.appraisaltypeid = appraisaltypeid;
	}

	public void setApprasialid(String apprasialid) {
		this.apprasialid = apprasialid;
	}
	public String getApprasial() {
		return apprasial;
	}
	public void setApprasial(String apprasial) {
		this.apprasial = apprasial;
	}
	public String getAppraseridentify() {
		return appraseridentify;
	}
	public void setAppraseridentify(String appraseridentify) {
		this.appraseridentify = appraseridentify;
	}
	public String getAppraser() {
		return appraser;
	}
	public void setAppraser(String appraser) {
		this.appraser = appraser;
	}
	public String getEdu_id() {
		return edu_id;
	}
	public void setEdu_id(String edu_id) {
		this.edu_id = edu_id;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}

	public String getAppraisal() {
		return appraisal;
	}

	public void setAppraisal(String appraisal) {
		this.appraisal = appraisal;
	}

	public String getProcessdesc() {
		return processdesc;
	}

	public void setProcessdesc(String processdesc) {
		this.processdesc = processdesc;
	}

	public String getHealthstdid() {
		return healthstdid;
	}

	public void setHealthstdid(String healthstdid) {
		this.healthstdid = healthstdid;
	}

	public String getSchoolyear() {
		return schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public String getGradenum() {
		return gradenum;
	}

	public void setGradenum(String gradenum) {
		this.gradenum = gradenum;
	}

	public String getYearScore() {
		return yearScore;
	}

	public void setYearScore(String yearScore) {
		this.yearScore = yearScore;
	}

	public String getYearGrade() {
		return yearGrade;
	}

	public void setYearGrade(String yearGrade) {
		this.yearGrade = yearGrade;
	}

	public String getHealthid() {
		return healthid;
	}

	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemMark() {
		return itemMark;
	}

	public void setItemMark(String itemMark) {
		this.itemMark = itemMark;
	}

	public String getItemScore() {
		return itemScore;
	}

	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}

	public String getItemGrade() {
		return itemGrade;
	}

	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getRewardSscore() {
		return rewardSscore;
	}

	public void setRewardSscore(String rewardSscore) {
		this.rewardSscore = rewardSscore;
	}

	public String getItemMarkStandardWeight() {
		return itemMarkStandardWeight;
	}

	public void setItemMarkStandardWeight(String itemMarkStandardWeight) {
		this.itemMarkStandardWeight = itemMarkStandardWeight;
	}

	public String getItemMarkWeight() {
		return itemMarkWeight;
	}

	public void setItemMarkWeight(String itemMarkWeight) {
		this.itemMarkWeight = itemMarkWeight;
	}

	public String getOnePartId() {
		return onePartId;
	}

	public void setOnePartId(String onePartId) {
		this.onePartId = onePartId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getTwoPartId() {
		return twoPartId;
	}

	public void setTwoPartId(String twoPartId) {
		this.twoPartId = twoPartId;
	}

	public String getTwoPartName() {
		return twoPartName;
	}

	public void setTwoPartName(String twoPartName) {
		this.twoPartName = twoPartName;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPartInfo() {
		return partInfo;
	}

	public void setPartInfo(String partInfo) {
		this.partInfo = partInfo;
	}

	public String getWriteMan() {
		return writeMan;
	}

	public void setWriteMan(String writeMan) {
		this.writeMan = writeMan;
	}

	public String getSignerName() {
		return signerName;
	}

	public void setSignerName(String signerName) {
		this.signerName = signerName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getPracticeid() {
		return practiceid;
	}

	public void setPracticeid(String practiceid) {
		this.practiceid = practiceid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCooperationMan() {
		return cooperationMan;
	}

	public void setCooperationMan(String cooperationMan) {
		this.cooperationMan = cooperationMan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<AppraiseBaseDto> getAttachmentDtos() {
		return attachmentDtos;
	}

	public void setAttachmentDtos(List<AppraiseBaseDto> attachmentDtos) {
		this.attachmentDtos = attachmentDtos;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public  final Comparator compareByAttachId_CZ = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AttachFileDto app11 = (AttachFileDto) app1;
            	AttachFileDto app22 = (AttachFileDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
    @Override
	public String toString() {
		return "AppraiseBaseDto [apprasialid=" + apprasialid + ", apprasial="
				+ apprasial + ", appraseridentify=" + appraseridentify
				+ ", appraseridentifynum=" + appraseridentifynum
				+ ", appraser=" + appraser + ", appraserid=" + appraserid
				+ ", edu_id=" + edu_id + ", signdate=" + signdate + ", termid="
				+ termid + ", appraisaltypeid=" + appraisaltypeid
				+ ", appraisal=" + appraisal + ", processdesc=" + processdesc
				+ ", columNumberName=" + columNumberName + ", healthstdid="
				+ healthstdid + ", schoolyear=" + schoolyear + ", gradenum="
				+ gradenum + ", gradename=" + gradename + ", yearScore="
				+ yearScore + ", yearGrade=" + yearGrade + ", healthid="
				+ healthid + ", itemName=" + itemName + ", itemType="
				+ itemType + ", itemMark=" + itemMark
				+ ", itemMarkStandardWeight=" + itemMarkStandardWeight
				+ ", itemMarkWeight=" + itemMarkWeight + ", itemScore="
				+ itemScore + ", itemGrade=" + itemGrade + ", rewardSscore="
				+ rewardSscore + ", onePartId=" + onePartId + ", partName="
				+ partName + ", twoPartId=" + twoPartId + ", twoPartName="
				+ twoPartName + ", partId=" + partId + ", topic=" + topic
				+ ", partInfo=" + partInfo + ", writeMan=" + writeMan
				+ ", keyword=" + keyword + ", cooperationMan=" + cooperationMan
				+ ", address=" + address + ", userid=" + userid
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", attachmentDtos=" + attachmentDtos + ", signerName="
				+ signerName + ", subjectId=" + subjectId + ", createDate="
				+ createDate + ", subjectName=" + subjectName
				+ ", attachmentId=" + attachmentId + ", attachmentName="
				+ attachmentName + ", attachmentPath=" + attachmentPath
				+ ", practiceid=" + practiceid + ", practicesSelfAppraiseDtos="
				+ practicesSelfAppraiseDtos + ", isReadOnly=" + isReadOnly
				+ ", myselfappraserid=" + myselfappraserid
				+ ", myselfapprasercontent=" + myselfapprasercontent
				+ ", item1=" + item1 + ", item2=" + item2 + ", item3=" + item3
				+ ", item4=" + item4 + ", item5=" + item5 + ", attachFileDtos="
				+ attachFileDtos + ", compareByAttachId_CZ="
				+ compareByAttachId_CZ + "]";
	}

	public int compareTo(Object obj) {
    	if(obj==null) return -1;
    	String appId = NestUtil.isEmpty(((AppraiseBaseDto)obj).getPartId())?"-1":((AppraiseBaseDto)obj).getPartId();
    	String appId2 = NestUtil.isEmpty(this.getPartId())?"-2":this.getPartId();
    	return -appId.compareTo(appId2);
    }
}
