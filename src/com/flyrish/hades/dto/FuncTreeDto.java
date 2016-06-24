package com.flyrish.hades.dto;

import java.io.Serializable;
import java.util.List;


public class FuncTreeDto implements Serializable{
	
	//功能标示号
	private String funcId;
	//功能名称
	private String funcName;
	//功能对应url == execfilename 执行文件名
	private String url;
	//功能层级 
	private String funcLevel;
	//功能顺序 == orderno
	private String funcOrder;
	//上层功能菜单标识号 == fatherid
	private String parFuncId;
	//功能图标
	private String funcImage;
	//下级功能菜单
	private List<FuncTreeDto> sysFuncList;
	//显示级别
	private Integer showType;
	//常用功能主键
	private String commonFuncId;
	//用户标识号
	private String userId;
	
	
	
	public String getCommonFuncId() {
		return commonFuncId;
	}
	public void setCommonFuncId(String commonFuncId) {
		this.commonFuncId = commonFuncId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFuncLevel() {
		return funcLevel;
	}
	public void setFuncLevel(String funcLevel) {
		this.funcLevel = funcLevel;
	}
	public String getFuncOrder() {
		return funcOrder;
	}
	public void setFuncOrder(String funcOrder) {
		this.funcOrder = funcOrder;
	}
	public String getParFuncId() {
		return parFuncId;
	}
	public void setParFuncId(String parFuncId) {
		this.parFuncId = parFuncId;
	}
	public String getFuncImage() {
		return funcImage;
	}
	public void setFuncImage(String funcImage) {
		this.funcImage = funcImage;
	}
	public List<FuncTreeDto> getSysFuncList() {
		return sysFuncList;
	}
	public void setSysFuncList(List<FuncTreeDto> sysFuncList) {
		this.sysFuncList = sysFuncList;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}

}
