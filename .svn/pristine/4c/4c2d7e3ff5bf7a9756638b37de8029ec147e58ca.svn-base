package com.flyrish.hades.webapp.action.export;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyrish.hades.dto.SubjectDto;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILearnprocessStaticsExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;

public class CourseCountExport extends TemplateExport{
	public UserDto userDto;
	public String levelCode;
	public List<SubjectDto>subjectDtos; 
	public String gradesyear;
	public String schoolName;
	public String termid;
	public String discode;
	public String usertype;
	public String filename;
	public String titlename;
	public String discodename="";
	public String gradename="";
	public String termname;
	public String filepath;
	public String currentTermId;
	String cmis30Id;
	String levelId;
	int cellNum=0;
	int cellStart;
	int cellEnd;
	List<String> gradeYears;
	Map<String,Map<String,String>>tempLearnprocessCounts = new TreeMap<String, Map<String,String>>();
	public Map<String, Map<String, Map<String,String>>>learnprocessJWStaticsInfo;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	@Spring
	private ILearnprocessStaticsExt learnprocessStaticsExt;
	@Before
    public Object doBefore(HttpServletRequest req){
		userDto = this.getLoginInfo(req);
		levelCode = userDto.getLevelcode();
		if(NestUtil.isEmpty(discode)){
		    discode = userDto.getDiscode();
		}
		usertype = userDto.getUsertype();
		currentTermId = userDto.getTermId();
		cmis30Id = userDto.getCmis30id();
		levelId = userDto.getLevelid();
    	return null;
    }
	@DefaultAction
	public Object ExportExcel(HttpServletRequest req,HttpServletResponse resp){
		try {
			redisServiceExt.save(dStatus, "0");
			filename = discodename+ gradename+"届" + termname +"课程评语填写情况.xls";
			titlename = discodename + gradename+"届" + termname +"课程评语填写情况";
			
			if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelCode)){
				subjectDtos = masterAppriseExt.getCZSubjectInfos();
				if(Constant.USER_KIND_CITY.equals(usertype)||Constant.USER_KIND_COUNTY.equals(usertype)){
					filepath = "JuniorCourseCountArea";
					tempLearnprocessCounts = learnprocessStaticsExt
							.queryLearnprocessStaticsInfo(gradesyear, schoolName,
									levelCode, termid, discode, subjectDtos, usertype);
					InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
					cellNum = 69;
					FillDataInExcel();
				}else{
					cellStart = 2;
					filepath="JuniorJWCourseCount";
					titlename =  termname +"课程评语填写情况";
					filename = termname +"课程评语填写情况.xls";
					gradeYears=studentAppDetailExt.queryGradeYearList(levelId,levelCode);
					cellEnd = cellStart + gradeYears.size()-1;
					learnprocessJWStaticsInfo = learnprocessStaticsExt
							.queryJWLearnprocessStatics(termid, currentTermId,
									discode, cmis30Id, subjectDtos, gradeYears,
									levelId, levelCode);
					InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	fillDatainTitleorJB();
			    	FillDataInJW();
				}
			} else {
				subjectDtos = masterAppriseExt.getGZSubjectInfos();
				if (Constant.USER_KIND_CITY.equals(usertype)
						|| Constant.USER_KIND_COUNTY.equals(usertype)) {
					filepath = "SuniorCourseCountArea";
					tempLearnprocessCounts = learnprocessStaticsExt
							.queryLearnprocessStaticsInfo(gradesyear, schoolName,
									levelCode, termid, discode, subjectDtos, usertype);
					InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	cellNum = 73;
			    	FillDataInExcel();
				} else {
					cellStart = 2;
			        filepath = "SeniorJWCourseCount";
			        titlename =  termname +"课程评语填写情况";
					filename = termname +"课程评语填写情况.xls";
			        gradeYears=studentAppDetailExt.queryGradeYearList(levelId,levelCode);
			        cellEnd = cellStart + gradeYears.size()-1;
			        learnprocessJWStaticsInfo = learnprocessStaticsExt
							.queryJWLearnprocessStatics(termid, currentTermId,
									discode, cmis30Id, subjectDtos, gradeYears,
									levelId, levelCode);
			        InputExcel(req, filepath);
					sheet.getRow(0).getCell(0).setCellValue(titlename);
			    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
			    	fillDatainTitleorJB();
			    	FillDataInJW();
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
			logger.error("ExportExcel(HttpServletRequest,HttpServletResponse)", e);
		}
		return null;
	}
	
	private void FillDataInExcel(){
			int rowStart = 3;
			int cellStart;
			if(null==tempLearnprocessCounts||tempLearnprocessCounts.isEmpty())
				return;
			for (Map.Entry<String, Map<String, String>> shoolMap : tempLearnprocessCounts
					.entrySet()) {
				cellStart = 1;
				HSSFRow row = CreateCell(rowStart++, cellNum);
				row.getCell(0).setCellValue(shoolMap.getKey());
				Map<String,String> appInfo = shoolMap.getValue();
				for(SubjectDto subject : subjectDtos)
				{
					String info = appInfo.get(subject.getSubjectid());
					if(NestUtil.isNotEmpty(info))
					{
						String [] informations = info.split("@");
						for(String information : informations)
						{
							if(row.getCell(cellStart++)!=null)
								row.getCell(cellStart++).setCellValue(information);
						}
					}
				}
			}
	}
	
	private void FillDataInJW()
	{
		int rowStart = 2;
		String[] conditions = {"学生总人数","已完成学生数","完成百分比（%）","已填写条目数"};
		if(null==learnprocessJWStaticsInfo||learnprocessJWStaticsInfo.isEmpty())
			return;
		for(SubjectDto subject : subjectDtos)
		{
			Map<String,Map<String,String>> sectionAppInfos = learnprocessJWStaticsInfo.get(subject.getSubjectid());
			for(String condition : conditions)
			{
				cellStart = 2;
				HSSFRow row = createCell(rowStart++, cellStart, cellEnd);
				Map<String,String> appInfos = sectionAppInfos.get(condition);
				for(String gradeyear : gradeYears)
				{
					row.getCell(cellStart++).setCellValue(appInfos.get(gradeyear));
				}
			}
		}
	}
	 /**
     * 填写届别
     */
    private void fillDatainTitleorJB()
    {
    	HSSFRow JBRow = createCell(1, cellStart, cellEnd);
    	for(String gradeyear : gradeYears)
    	{
    		JBRow.getCell(cellStart++).setCellValue(gradeyear+"届");
    	}
    }
}
