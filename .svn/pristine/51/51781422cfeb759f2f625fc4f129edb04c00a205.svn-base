package com.flyrish.hades.webapp.action.masterReport;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.DataCountDto;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class ReportAction extends BaseAction{
	@Spring
	public IMasterReportExt masterReportExt;
	public List<DataCountDto>viewDatas;
	public String flag;
	
	public Object toDetailsReportPage(){
		flag = "1";
		return "/reportBook/detailsReportPage.jsp";
	}
	@DefaultAction
	public Object queryDatas(HttpServletRequest request){
		flag = "2";
		this.getLoginInfo(request);
		String discode = userDto.getDiscode();
		String graduYear = userDto.getTermId().substring(0, 4);
		List<DataCountDto> zoneInfos = masterReportExt.queryReportZoneDatas(discode, graduYear);
		this.initDatas(zoneInfos);
		return "/reportBook/detailsReportPage.jsp";
	}
	public int totalSchoolCount;
	public int totalReportStudentCount;
	public int totalCheckStudentCount;
	public int totalErrorCheckStudentCount;
	public int totalNoReportSchoolCount;
	public int totalReportSchoolCount;
	private void initDatas(List<DataCountDto> dataCounts) {
		if(null==dataCounts || dataCounts.size()==0)return;
		viewDatas = new ArrayList<DataCountDto>();
		for(DataCountDto dto : dataCounts){
			DataCountDto viewDto = new DataCountDto();
			viewDto.setName(dto.getName());
			viewDto.setDiscode(dto.getDiscode());
			viewDto.setSchoolTotalCount(dto.getSchoolTotalCount());
			totalSchoolCount+=this.totalCount(dto.getSchoolTotalCount());
			viewDto.setReportSchoolCount(dto.getReportSchoolCount());
			totalReportSchoolCount+=this.totalCount(dto.getReportSchoolCount());
			viewDto.setNoReportSchoolCount(dto.getNoReportSchoolCount());
			totalNoReportSchoolCount+=this.totalCount(dto.getNoReportSchoolCount());
			viewDto.setReportStudentCount(dto.getReportStudentCount());
			totalReportStudentCount+=this.totalCount(dto.getReportStudentCount());
			viewDto.setCheckStudentCount(dto.getCheckStudentCount());
			totalCheckStudentCount+=this.totalCount(dto.getCheckStudentCount());
			viewDto.setErrorCheckStudentCount(dto.getErrorCheckStudentCount());
			totalErrorCheckStudentCount += this.totalCount(dto.getErrorCheckStudentCount());
			viewDatas.add(viewDto);
		}
	}
	private int totalCount(String str){
		return Integer.parseInt(NestUtil.isEmpty(str) ?"0":str);
	}
}
