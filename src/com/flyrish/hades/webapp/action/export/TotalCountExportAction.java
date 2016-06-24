package com.flyrish.hades.webapp.action.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.service.ext.ITotalStaticsExt;

public class TotalCountExportAction extends TemplateExport{

	public String levelcode;
	public String usertype;
	public String levelid;
	public String cmis30id;
	public String discode;
	public String termId;
	public String termName;
	public String schoolName;
	public String gradeid;
	public String gradeName;
	public String discodeName;
	String filename;
	String titlename;
	String filepath;
	UserDto userDto;
	int cellNum = 0;
	int cellStart =4;
	int cellEnd;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	@Spring
	public ITotalStaticsExt totalStaticsExt;
	public List<String> gradeYears=new ArrayList<String>();
	public Map<String,Map<String,Map<String,String>>> datas=new HashMap<String,Map<String,Map<String,String>>>();
	public Map<String,Map<String,String>> tongjiDatas=new HashMap<String,Map<String,String>>();
	@Before
	public Object doBefore(HttpServletRequest req){
		userDto = this.getLoginInfo(req);
		levelcode = userDto.getLevelcode();
		usertype = userDto.getUsertype();
		cmis30id = userDto.getCmis30id();
		if(NestUtil.isEmpty(discode))
		{
			discode = userDto.getDiscode();
		}
		levelid = userDto.getLevelid();
		return null;
	}
	@DefaultAction
	public Object TotalExport(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			redisServiceExt.save(dStatus, "0");
			filename = termName+ "数据填写总体情况.xls";
			titlename = termName+"数据填写总体情况";
			
			if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelcode)) {
				String[] sectionIds = { "11", "12", "21", "22", "23", "31", "32", "33",
						"34", "35", "41", "45", "46", "44", "43", "51", "52",
						"53", "54", "55", "61", "62", "63", "64", "65", "71", "72",
						"73", "74", "75", "81", "82", "83", "84", "85", "91", "92",
						"93", "94", "95" };
				if (Constant.USER_KIND_CITY.equals(usertype)
						|| Constant.USER_KIND_COUNTY.equals(usertype)) {
					cellNum = 168;
					filepath = "totalCountJunior";
					InputExcel(req, filepath);
					filename =gradeName+"届"+(NestUtil.isEmpty(discodeName)?"":discodeName)+schoolName+termName+ "数据填写总体情况.xls";
					titlename =gradeName+"届"+(NestUtil.isEmpty(discodeName)?"":discodeName)+schoolName+ termName+"数据填写总体情况";
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	datas=studentAppDetailExt.queryMapDataByConditionInDiscode1(termId,gradeid,discode,levelcode,schoolName,tongjiDatas);
			    	FillDataInAreaOrCityExcel(sectionIds);
				} else {
					gradeYears=studentAppDetailExt.queryGradeYearList(levelid,levelcode);
					//获取相应的数据
					datas=totalStaticsExt.queryMapDataByCondition(termId, cmis30id,discode, levelcode, levelid);
					cellEnd = gradeYears.size()+cellStart-1;
			        filepath = "JuniorJWTotalCount";
			        InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	FillDataInJB();
			    	FillDataInJWExcel(sectionIds);
				}
			} else {
				if (Constant.USER_KIND_CITY.equals(usertype)
						|| Constant.USER_KIND_COUNTY.equals(usertype)) {
					String[] sectionIds = { "1010", "1020_0", "1020_1", "2010", "2020", "2030",
							"2040", "3010", "3020_1", "3020_2", "3020_3", "3030",
							"8010", "9999", "8020", "8040_1", "8040_2", "8040_3",
							"4010", "4020_1", "4020_2", "4020_3", "4030", "5010",
							"5020_1", "5020_2", "5020_3", "5555", "6010", "6020_1",
							"6020_2", "6020_3", "6030", "9010", "9020", "9030", "7010",
							"7020", "7030_1", "7030_2", "7030_3", "7040", "7050" };
					cellNum = 182;
					filepath = "totalCountHigh";
					InputExcel(req, filepath);
					filename =gradeName+"届"+(NestUtil.isEmpty(discodeName)?"":discodeName)+schoolName+termName+ "数据填写总体情况.xls";
					titlename =gradeName+"届"+(NestUtil.isEmpty(discodeName)?"":discodeName)+schoolName+ termName+"数据填写总体情况";
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	datas=studentAppDetailExt.queryMapDataByConditionInDiscode1(termId,gradeid,discode,levelcode,schoolName,tongjiDatas);
			    	FillDataInAreaOrCityExcel(sectionIds);
				} else {
					String[] sectionIds = { "1010-1", "1020-1", "1020-5", "2010-1", "2020-1", "2030",
							"2040-5", "3010-1", "3020-2", "3020-3", "3020-5", "3030",
							"8010", "9999", "8020-1", "8040-2", "8040-3", "8040-5",
							"4010-1", "4020-2", "4020-3", "4020-5", "4030", "5010-1",
							"5020-2", "5020-3", "5020-5", "5050", "6010-1", "6020-2",
							"6020-3", "6020-5", "6030", "9010", "9020", "9030", "7010",
							"7020-1", "7030-2", "7030-3", "7030-5", "7040-1", "7050-1" };
					gradeYears=studentAppDetailExt.queryGradeYearList(levelid,levelcode);
					//获取相应的数据
					datas=totalStaticsExt.queryMapDataByCondition(termId, cmis30id,discode, levelcode, levelid);
					cellEnd = gradeYears.size()+cellStart-1;
					filepath = "SeniorJWTotalCount";
					InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	FillDataInJB();
			    	FillDataInJWExcel(sectionIds);
				}
			}
			OutputExcel(resp, filename);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("TotalExport(HttpServletRequest,HttpServletResponse)", e);
		}
		return null;
	}
	private void FillDataInJB(){
		HSSFRow row = createCell(1, cellStart, cellEnd);
		for(String gradeyear : gradeYears)
    	{
			row.getCell(cellStart++).setCellValue(gradeyear+"届");
    	}
	}
	private void FillDataInJWExcel(String[] exportSectionId){
		int rowStart = 2;
		String[] conditions = {"totalStudentNum","overStudentNum","percentOverStudentNum","finishedTotalCount","attacheCount"};
		if(null==datas||datas.isEmpty())
			return;
		for(String sectionId : exportSectionId)
		{
			Map<String,Map<String,String>> finishedCondition = datas.get(sectionId);
			for(String condition : conditions)
			{
				if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelcode)) {
					if (sectionId != "35" && sectionId != "43"
							&& sectionId != "55" && sectionId != "75"
							&& sectionId != "82" && sectionId != "84"&& sectionId != "95"
							&& condition == "attacheCount")
						continue;
				} else {
					if ((sectionId != "7040-1" && sectionId != "7050-1"
							&& sectionId != "9010" && sectionId != "9020"
							&& sectionId != "9030" && sectionId != "3030"
							&& sectionId != "4030" && sectionId != "6030" && sectionId != "8010")
							&& condition == "attacheCount")
						continue;
				}
				cellStart = 4;
	            	HSSFRow row = createCell(rowStart++, cellStart, cellEnd);
	            	Map<String,String> JBFCondition = finishedCondition.get(condition);	
	            	for(String gradeyear : gradeYears)
	            	{
	            		row.getCell(cellStart++).setCellValue((null == JBFCondition.get(gradeyear)|| JBFCondition.isEmpty())? "": JBFCondition.get(gradeyear));
	            	}
	            }
			}
		}
	private void FillDataInAreaOrCityExcel(String[] exportSectionId){
		int rowStart = 5;
		String[] conditions = {"totalStudentNum","overStudentNum","percentOverStudentNum","overAppriseNum","attchFileNum"};
		if(null==datas||datas.isEmpty())
			return;
		for(Map.Entry<String, Map<String,Map<String,String>>> schoolMap : datas.entrySet())
		{
			cellStart = 0;
			HSSFRow row = CreateCell(rowStart++, cellNum);
			row.getCell(cellStart++).setCellValue(schoolMap.getKey());
			Map<String,Map<String,String>> sectionsMap = schoolMap.getValue();
			for(String sectionId : exportSectionId)
			{
				Map<String,String> sectionMap = sectionsMap.get(sectionId);
				if(null==sectionMap||sectionMap.isEmpty())
					continue;
				FillDataInRow(conditions, row, sectionId, sectionMap);
			}
		}
		cellStart = 0;
		HSSFRow row = CreateCell(rowStart++, cellNum);
		row.getCell(cellStart++).setCellValue("合计");
		for(String sectionId : exportSectionId)
		{
			Map<String,String> totalMap = tongjiDatas.get(sectionId);
			FillDataInRow(conditions, row, sectionId, totalMap);
		}
	}
	private void FillDataInRow(String[] conditions, HSSFRow row,
			String sectionId, Map<String, String> sectionMap) {
		for(String condition : conditions)
		{
			if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelcode)) {
				if (sectionId != "35" && sectionId != "43"
						&& sectionId != "55" && sectionId != "75"
						&& sectionId != "82" && sectionId != "84"&& sectionId != "95"
						&& condition == "attchFileNum")
					continue;
			} else {
				if ((sectionId != "7040" && sectionId != "7050"
						&& sectionId != "9010" && sectionId != "9020"
						&& sectionId != "9030" && sectionId != "3030"
						&& sectionId != "4030" && sectionId != "6030" && sectionId != "8010")
						&& condition == "attchFileNum")
					continue;
			}
			row.getCell(cellStart++).setCellValue((null == sectionMap.get(condition)|| sectionMap.isEmpty())? "": sectionMap.get(condition));
		}
	}
	
}
