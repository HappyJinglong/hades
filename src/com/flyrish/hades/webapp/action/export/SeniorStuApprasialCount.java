package com.flyrish.hades.webapp.action.export;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.StudentAppraiseInfoNumDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;

public class SeniorStuApprasialCount extends TemplateExport{
	public String gradeId;
	public String classid;
	public String className;
	public String gradeName;
	public String termName;
	public String termid;
	private String cmis30id;
	private String discode;
	@Spring
    public IStudentAppDetailExt studentAppDetailExt;   
	
	private Map<String,Object> example;
	private String filename;
	@Before
	public Object doBefore(HttpServletRequest req)
	{
		cmis30id = getLoginInfo(req).getCmis30id();
		discode = getLoginInfo(req).getDiscode();
		filename = gradeName+"届"+className+"("+termName+")学生评价统计明细.xls";
		return null;
	}
	@DefaultAction
	public Object SeniorApprasialCount(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			redisServiceExt.save(dStatus, "0");
			String filepath = "SeniorStuApprasialCount";
			InputExcel(req,filepath);
			getData();
			FillData();
			OutputExcel(resp,filename);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("SeniorApprasialCount(HttpServletRequest,HttpServletResponse)", e);
		}
		return null;
	}
	/**
	 * 获取填充数据
	 */
	private void getData()
	{
		example = studentAppDetailExt.queryStudentAppraiseInfoNumDtoByInfo(gradeId, classid, termid, cmis30id, discode);
		sheet.getRow(0).getCell(0).setCellValue(gradeName+"届"+className+"学生数："+example.get("totalPersonsNum")+"人");
		sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
	}
	/**
	 * 高中學生評價統計明細 模板數據填充
	 */
	private void FillData()
	{
		HSSFRow row ;
		StudentAppraiseInfoNumDto studentDetailInfo;
		List<StudentAppraiseInfoNumDto> stuDetail = (List<StudentAppraiseInfoNumDto>) example.get("data");
		if (stuDetail != null && stuDetail.size() > 0) {
			for (int i = 0; i < stuDetail.size(); i++) {
				row = CreateCell(i + 4, 46);
				studentDetailInfo = stuDetail.get(i);
				row.getCell(0).setCellValue(studentDetailInfo.getGradeYear());
				row.getCell(1).setCellValue(studentDetailInfo.getClassName());
				row.getCell(2).setCellValue(studentDetailInfo.getStudentName());
				row.getCell(3).setCellValue(
						studentDetailInfo.getStartEcoleNum());
				row.getCell(4).setCellValue(
						studentDetailInfo.getStartMyDlpmentTargetNum());
				row.getCell(5).setCellValue(
						studentDetailInfo.getStartparentsExpectNum());
				row.getCell(6).setCellValue(
						studentDetailInfo.getEndMyEcoleNum());
				row.getCell(7).setCellValue(
						studentDetailInfo.getEndMyDlpmentTargetNum());
				row.getCell(8).setCellValue(
						studentDetailInfo.getEndHeadMasterAppraiseNum());
				row.getCell(9).setCellValue(
						studentDetailInfo.getEndParentsExpectAndAppNum());
				row.getCell(10).setCellValue(
						studentDetailInfo.getSxddMySelfAppraiseNum());
				row.getCell(11).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseClassMateNum());
				row.getCell(12).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseTeacherNum());
				row.getCell(13).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseParentsNum());
				row.getCell(14).setCellValue(
						studentDetailInfo.getSxddRecodeBagNum());
				row.getCell(15).setCellValue(
						studentDetailInfo.getXycjMySelfAppraiseNum());
				row.getCell(16).setCellValue(
						studentDetailInfo.getXycjOtherAppraiseClassMateNum());
				row.getCell(17).setCellValue(
						studentDetailInfo.getXycjOtherAppraiseTeacherNum());
				row.getCell(18).setCellValue(
						studentDetailInfo.getXycjOtherAppraiseParentsNum());
				row.getCell(19).setCellValue(
						studentDetailInfo.getXycjSubJectWorkNum());
				row.getCell(20).setCellValue(
						studentDetailInfo.getXycjSubJectAppraiseNum());
				row.getCell(21).setCellValue(
						studentDetailInfo.getXzyjlMySelfAppraiseNum());
				row.getCell(22).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseClassMateNum());
				row.getCell(23).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseTeacherNum());
				row.getCell(24).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseParentsNum());
				row.getCell(25).setCellValue(
						studentDetailInfo.getXzyjlRecodeBagNum());
				row.getCell(26).setCellValue(
						studentDetailInfo.getYdhjkMySelfAppraiseNum());
				row.getCell(27).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseClassMateNum());
				row.getCell(28).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseTeacherNum());
				row.getCell(29).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseParentsNum());
				row.getCell(30).setCellValue(
						studentDetailInfo.getYdhjkPhysicalHealthNum());
				row.getCell(31).setCellValue(
						studentDetailInfo.getXmybxMySelfAppraiseNum());
				row.getCell(32).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseClassMateNum());
				row.getCell(33).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseTeacherNum());
				row.getCell(34).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseParentsNum());
				row.getCell(35).setCellValue(
						studentDetailInfo.getXmybxRecordBagNum());
				row.getCell(36).setCellValue(
						studentDetailInfo.getZhsjhdYjxxxMySelfNum());
				row.getCell(37).setCellValue(
						studentDetailInfo.getZhsjhdSqfwMySelfNum());
				row.getCell(38).setCellValue(
						studentDetailInfo.getZhsjhdShsjhdMySelfNum());
				row.getCell(39).setCellValue(
						studentDetailInfo.getGxfzMyBaseInfoNum());
				row.getCell(40).setCellValue(
						studentDetailInfo.getGxfzMySelfAppraiseNum());
				row.getCell(41).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseClassMateNum());
				row.getCell(42).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseTeacherNum());
				row.getCell(43).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseParentsNum());
				row.getCell(44).setCellValue(
						studentDetailInfo.getGxfzSpecialtyAndAchievementsNum());
				row.getCell(45).setCellValue(studentDetailInfo.getGxfzSpecialtyDelepNum());
			}
		}
	} 
	/**
	 * 初中學生評價統計明細  報表導出
	 * @param req
	 * @param resp
	 */
	public void juniorDetailExport(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			redisServiceExt.save(dStatus, "0");
			String filepath = "JuniorStuApprasialCount";
			InputExcel(req,filepath);
			getData();
			fillJuniorData();
			OutputExcel(resp,filename);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("juniorDetailExport(HttpServletRequest,HttpServletResponse)", e);
		}
	}
	/**
	 * 初中學生評價統計明細 報表數據填充
	 */
	private void fillJuniorData()
	{
		HSSFRow row ;
		StudentAppraiseInfoNumDto studentDetailInfo;
		List<StudentAppraiseInfoNumDto> stuDetail = (List<StudentAppraiseInfoNumDto>) example.get("data");
		if (stuDetail != null && stuDetail.size() > 0) {
			for (int i = 0; i < stuDetail.size(); i++) {
				row = CreateCell(i + 4, 43);
				studentDetailInfo = stuDetail.get(i);
				row.getCell(0).setCellValue(studentDetailInfo.getGradeYear());
				row.getCell(1).setCellValue(studentDetailInfo.getClassName());
				row.getCell(2).setCellValue(studentDetailInfo.getStudentName());
				row.getCell(3).setCellValue(
						studentDetailInfo.getStartEcoleNum());
				row.getCell(4).setCellValue(
						studentDetailInfo.getStartMyDlpmentTargetNum());
				row.getCell(5).setCellValue(
						studentDetailInfo.getEndMyEcoleNum());
				row.getCell(6).setCellValue(
						studentDetailInfo.getEndHeadMasterAppraiseNum());
				row.getCell(7).setCellValue(
						studentDetailInfo.getEndParentsExpectAndAppNum());
				row.getCell(8).setCellValue(
						studentDetailInfo.getSxddMySelfAppraiseNum());
				row.getCell(9).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseClassMateNum());
				row.getCell(10).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseTeacherNum());
				row.getCell(11).setCellValue(
						studentDetailInfo.getSxddOtherAppraiseParentsNum());
				row.getCell(12).setCellValue(
						studentDetailInfo.getSxddRecodeBagNum());
				row.getCell(13).setCellValue(
						studentDetailInfo.getXycjMySelfAppraiseNum());
				row.getCell(14).setCellValue(
						studentDetailInfo.getXycjOtherAppraiseClassMateNum());
				row.getCell(15).setCellValue(
						studentDetailInfo.getXycjSubJectAppraiseNum());
				row.getCell(16).setCellValue(
						studentDetailInfo.getXycjOtherAppraiseParentsNum());
				row.getCell(17).setCellValue(
						studentDetailInfo.getXycjSubJectWorkNum());
				row.getCell(18).setCellValue(
						studentDetailInfo.getXzyjlMySelfAppraiseNum());
				row.getCell(19).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseClassMateNum());
				row.getCell(20).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseTeacherNum());
				row.getCell(21).setCellValue(
						studentDetailInfo.getXzyjlOtherAppraiseParentsNum());
				row.getCell(22).setCellValue(
						studentDetailInfo.getXzyjlRecodeBagNum());
				row.getCell(23).setCellValue(
						studentDetailInfo.getYdhjkMySelfAppraiseNum());
				row.getCell(24).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseClassMateNum());
				row.getCell(25).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseTeacherNum());
				row.getCell(26).setCellValue(
						studentDetailInfo.getYdhjkOtherAppraiseParentsNum());
				row.getCell(27).setCellValue(
						studentDetailInfo.getYdhjkPhysicalHealthNum());
				row.getCell(28).setCellValue(
						studentDetailInfo.getXmybxMySelfAppraiseNum());
				row.getCell(29).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseClassMateNum());
				row.getCell(30).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseTeacherNum());
				row.getCell(31).setCellValue(
						studentDetailInfo.getXmybxOtherAppraiseParentsNum());
				row.getCell(32).setCellValue(
						studentDetailInfo.getXmybxRecordBagNum());
				row.getCell(33).setCellValue(
						studentDetailInfo.getZhsjhdYjxxxBaseInfoNum());
				row.getCell(34).setCellValue(
						studentDetailInfo.getZhsjhdYjxxxAchievementsNum());
				row.getCell(35).setCellValue(
						studentDetailInfo.getZhsjhdYjxxxMySelfAppraiseNum());
				row.getCell(36).setCellValue(
						studentDetailInfo.getZhsjhdSqfuYshsjBaseInfoNum());
				row.getCell(37)
						.setCellValue(
								studentDetailInfo
										.getZhsjhdSqfuYshsjMySelfAppraiseNum());
				row.getCell(38).setCellValue(
						studentDetailInfo.getGxfzMySelfAppraiseNum());
				row.getCell(39).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseClassMateNum());
				row.getCell(40).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseTeacherNum());
				row.getCell(41).setCellValue(
						studentDetailInfo.getGxfzOtherAppraiseParentsNum());
				row.getCell(42).setCellValue(
						studentDetailInfo.getGxfzSpecialtyAndAchievementsNum());
			}
		}
	}
}
