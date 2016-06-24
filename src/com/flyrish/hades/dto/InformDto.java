package com.flyrish.hades.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.nestframework.utils.NestUtil;


public class InformDto implements Serializable,Comparable{
    /**
     * 通告的主题
     */
    private String theme;
    /**
     * 公告内容
     */
    private String text;
    /**
     * 附件路径
     */
    private String filepath;
    /**
     * 附件名称
     */
    private String filename;
    /**
     * 发布日期
     */
    private String startDate;
    
    private String startMoreDate;
    /**
     * 截至时间
     */
    private String endDate;
    /**
     * 通知公告id
     */
    private String informid;
    /**
     * 发布状态
     */
    private String publishState;
    /**
     * 发布状态名称(已发布,未发布)
     */
    private String statename;
    /**
     * 发布者类型
     */
    private String roletype;
    /**
     * 发布者用户id
     */
    private String userid;
    /**
     * 发布区县代码
     */
    private String publishDiscode;
    /**
     * 接受对象串
     */
    private String roleCode;
    /**
     * 接收者串
     */
    private String receiverObj;
    /**
     * 接收学段串
     */
    private String objlevel;
    /**
     * 接收者对象名称
     */
    private String receiverName;
    /**
     * 校区id
     */
    private String campusid;
    /**
     * 已读未读标识
     */
    private String flag;
    /**
     * 是否全部范围
     */
    private String isall;
    
    
    
   /* private List<HOinfromreadDto> hOinfromreadDtos=Collections.synchronizedList(new ArrayList<HOinfromreadDto>(0));*/
    
    public String getStartMoreDate() {
		return startMoreDate;
	}
	public void setStartMoreDate(String startMoreDate) {
		this.startMoreDate = startMoreDate;
	}
	private ConcurrentHashMap<String, String> readMap = new ConcurrentHashMap<String, String>(0);
    /*public List<HOinfromreadDto> gethOinfromreadDtos() {
		return hOinfromreadDtos;
	}
	public void sethOinfromreadDtos(HOinfromreadDto hOinfromreadDto) {
		this.hOinfromreadDtos.add(hOinfromreadDto);
	}*/
  //  public ConcurrentHashMap<String, Object> get
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	public String getInformid() {
		return informid;
	}
	public void setInformid(String informid) {
		this.informid = informid;
	}
	public String getPublishState() {
		return publishState;
	}
	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	public String getRoletype() {
		return roletype;
	}
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPublishDiscode() {
		return publishDiscode;
	}
	public void setPublishDiscode(String publishDiscode) {
		this.publishDiscode = publishDiscode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getLevelcode() {
		return levelcode;
	}
	public void setLevelcode(String levelcode) {
		this.levelcode = levelcode;
	}
	public String getDownFilepath() {
		return downFilepath;
	}
	public void setDownFilepath(String downFilepath) {
		this.downFilepath = downFilepath;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getReceiverObj() {
		return receiverObj;
	}
	public void setReceiverObj(String receiverObj) {
		this.receiverObj = receiverObj;
	}
	public String getObjlevel() {
		return objlevel;
	}
	public void setObjlevel(String objlevel) {
		this.objlevel = objlevel;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCampusid() {
		return campusid;
	}
	public void setCampusid(String campusid) {
		this.campusid = campusid;
	}
	public String getIsall() {
		return isall;
	}
	public void setIsall(String isall) {
		this.isall = isall;
	}
	public ConcurrentHashMap<String, String> getReadMap() {
		return readMap;
	}
	public void setReadMap(ConcurrentHashMap<String, String> readMap) {
		this.readMap = readMap;
	}
	/**
     * 学段串
     */
    private String levelcode;
    /**
     * 下载路径
     */
    private String downFilepath;
	public int compareTo(Object obj) {
		if(obj==null) return -1;
		if(NestUtil.isEmpty(flag)) flag="-2";
		if(NestUtil.isEmpty(((InformDto)obj).getFlag()))((InformDto)obj).setFlag("-2");
		if(flag.equals(((InformDto)obj).getFlag())&&NestUtil.isNotEmpty(((InformDto)obj).getStartDate())&&NestUtil.isNotEmpty(startDate)){
			 if(((InformDto)obj).getStartDate().compareTo(startDate)==0){
				 return ((InformDto)obj).getInformid().compareTo(informid);
			 }else{
				return ((InformDto)obj).getStartDate().compareTo(startDate);
			 }
		}else if(!flag.equals(((InformDto)obj).getFlag())){
			return flag.compareTo(((InformDto)obj).getFlag());
		}else{
			return -1;
		}
	}
    
}
