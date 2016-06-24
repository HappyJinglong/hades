package com.flyrish.hades.webapp.action.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;

public class RequirdCountExport extends TemplateExport{
	public UserDto userDto;
	public String levelcode;
	public String termId;
	public String cmis30Id;
	public String discode;
	public String levelid;
	public String termName;
	public String usertype;
	public String gradeyear;
	public String schoolname;
	int gradeNum;
	int cellStart = 4;
	int cellEnd;
	int cellNum;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	Map<String, Map<String, Map<String, String>>> datas;
	Map<String,Map<String,String>> tongjiDatas=new HashMap<String,Map<String,String>>();
	List<String> gradeYears;
	String filepath;
	String fileName;
	String titleName;
    @Before
	public Object doBefore(HttpServletRequest req)
	{
    	userDto = getLoginInfo(req);
    	levelcode = userDto.getLevelcode();
    	cmis30Id = userDto.getCmis30id();
    	discode = null==discode?userDto.getDiscode():discode;
    	levelid = userDto.getLevelid();
    	usertype = userDto.getUsertype();
		return null;
	}
    @DefaultAction
    public Object ExportData(HttpServletRequest req,HttpServletResponse resp)
    {
    	try {
			redisServiceExt.save(dStatus, "0");
			gradeYears = studentAppDetailExt.queryGradeYearList(levelid, levelcode);
			gradeNum = gradeYears.size();
			cellEnd = gradeNum + cellStart - 1;
			fileName =termName+"必填数据填写情况.xls ";
			titleName =termName+"必填数据填写情况";
			if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelcode))
			{
				cellNum = 43;
				String[] exportSections = {"11", "12", "21", "22", "23", "31",
						"41", "44", "52", "65", "75", "81", "91", "95"};
				if(Constant.USER_KIND_CITY.equals(usertype)||Constant.USER_KIND_COUNTY.equals(usertype)){
					datas = studentAppDetailExt.queryMapDataByConditionInDiscode(
							termId, gradeyear, discode, levelcode, schoolname,
							tongjiDatas);
					filepath = "JuniorRequiredWriteAreaCity";
					InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titleName);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
					FillDataInAreaOrCity(exportSections);
				}else{
					datas = studentAppDetailExt
							.queryMapDataByCondition(termId, cmis30Id, discode, levelcode,
									levelid);
					filepath = "JuniorRequiredWrite";
					InputExcel(req, filepath);
					fillDatainTitleorJB();
					FillDataInExcel(exportSections);
				}
			}else{
				cellNum = 40;
				String[] exportSections = { "1010", "1020", "2010", "2030", "2040",
						"3010", "9999", "4020", "5555", "6030", "9010", "7010",
						"7050" };
			    if(Constant.USER_KIND_CITY.equals(usertype)||Constant.USER_KIND_COUNTY.equals(usertype)){
			    	filepath = "SeniorRequiredWriteAreaCity";
					datas = studentAppDetailExt.queryMapDataByConditionInDiscode(
							termId, gradeyear, discode, levelcode, schoolname,
							tongjiDatas);
			    	InputExcel(req, filepath);
			    	sheet.getRow(0).getCell(0).setCellValue(titleName);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	FillDataInAreaOrCity(exportSections);
				}else{
					datas = studentAppDetailExt
							.queryMapDataByCondition(termId, cmis30Id, discode, levelcode,
									levelid);
					filepath = "SeniorRequiredWrite";
					InputExcel(req, filepath);
					fillDatainTitleorJB();
					FillDataInExcel(exportSections);
				}
			}
			OutputExcel(resp, fileName);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("ExportData(HttpServletRequest,HttpServletResponse)", e);
		}
    	return null;
    }
    /**
     * 填写表头和届别
     */
    private void fillDatainTitleorJB()
    {
    	sheet.getRow(0).getCell(0).setCellValue(titleName);
    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
    	HSSFRow JBRow = createCell(1, cellStart, cellEnd);
    	for(String gradeyear : gradeYears)
    	{
    		JBRow.getCell(cellStart++).setCellValue(gradeyear+"届");
    	}
    }
    private void FillDataInExcel(String[] exportSections)
    {
    	int rowStart = 2;
    	String[] conditions = {"totalStudentNum","overStudentNum","percentOverStudentNum"};
    	if(null==datas||datas.isEmpty())
    	    return;
    	for(String sectionId : exportSections){
    		Map<String,Map<String,String>> finishedCondition = datas.get(sectionId);
    		for(String condition : conditions){
    			cellStart = 4;
    			HSSFRow row = createCell(rowStart++, cellStart, cellEnd);
    			Map<String,String> JBFCondition = finishedCondition.get(condition);
    			for(String gradeyear : gradeYears)
    			{
					row.getCell(cellStart++)
							.setCellValue(
									null == JBFCondition.get(gradeyear)
											|| !JBFCondition
													.containsKey(gradeyear) ? ""
											: JBFCondition.get(gradeyear));
    			}
    		}
    	}
    }
    private void FillDataInAreaOrCity(String[] exportSections)
    {
    	int rowStart = 5;
    	int cellStart;
    	String[] conditions = {"totalStudentNum","overStudentNum","percentOverStudentNum"};
    	if(null==datas||datas.isEmpty())
    		return;
    	for(Map.Entry<String, Map<String,Map<String,String>>> requiredMap : datas.entrySet())
    	{
    		cellStart = 0;
    		HSSFRow row = CreateCell(rowStart++, cellNum);
    		row.getCell(cellStart++).setCellValue(requiredMap.getKey());
    		Map<String,Map<String,String>> sectionInfos = requiredMap.getValue();
    		for(String sectionId : exportSections)
    		{
    			Map<String,String> infos = sectionInfos.get(sectionId);
                for(String condition : conditions)
                {
                	row.getCell(cellStart++).setCellValue(infos.get(condition));
                }
    		}
    	}
    	HSSFRow Row = CreateCell(rowStart,cellNum);
    	Row.getCell(0).setCellValue("合计");
    	if(null==tongjiDatas||tongjiDatas.isEmpty())
    		return;
    	cellStart = 1;
    	for(String sectionId : exportSections)
		{
			Map<String,String> infos = tongjiDatas.get(sectionId);
            for(String condition : conditions)
            {
            	Row.getCell(cellStart++).setCellValue(infos.get(condition));
            }
		}
    }
}
