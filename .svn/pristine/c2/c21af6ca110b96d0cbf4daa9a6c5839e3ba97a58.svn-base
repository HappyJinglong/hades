package com.flyrish.hades.webapp.action.export;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.NoServiceUtil;
public class shoolFillCount extends TemplateExport{

	public String termName;
	public String schoolName;
	public String termId;
	private String usertype;
	private String filepath;
	private String levelCode;
	private String discode;
	private int ColumnNum;
	private String discodeName;
	private String campusId;
	private List<String>queryJB;
	@Spring
	public IAppraisalStaticsExt appraisalStaticsExt;
	@Spring
	public IAppraisalWritedStaticsExt appraisalWritedStaticsExt;
	@Before
	public Object doBefore(HttpServletRequest req)
	{
		userDto = getLoginInfo(req);
		usertype = userDto.getUsertype();
		levelCode = userDto.getLevelcode();
		if(NestUtil.isEmpty(discode)){
			discode = userDto.getDiscode();
		}
		campusId = userDto.getCampuseId();
		//界别
		this.initJB();
		return null;
	}
	private void initJB() {
		queryJB = appraisalStaticsExt.queryJB(levelCode, "", "", "");
	}
	public String toSchoolDetail;
	@DefaultAction
	public Object AreaOrCitySchoolCountExport(HttpServletRequest req,HttpServletResponse resp)
	{
		try {
			redisServiceExt.save(dStatus, "0");
			getAreaName();
			String fileName = "学校填写情况.xls";
			getFilePath();
			InputExcel(req,filepath);
			areaOrCityFillTermName();
			areaOrCityFillData();
			OutputExcel(resp,fileName);
			redisServiceExt.save(dStatus, "1");
		} catch (Exception e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("AreaOrCitySchoolCountExport(HttpServletRequest,HttpServletResponse)", e);
		}
		return null;
	}
	/**
	 * (区县和市级)学校填写情况统计  数据填充
	 */
	private void areaOrCityFillData()
	{
		HSSFRow row;
		int i = 0;
		int j = 2;
		List<AppraisalWritedStaticseDto> schoolCountlist =null;
		if (Constant.USER_KIND_COUNTY.equals(usertype)){
			schoolCountlist = appraisalWritedStaticsExt.queryQXAppraiseStatics(levelCode, queryJB, discode, schoolName,termId,userDto.getTermId());
		} else if (Constant.USER_KIND_CITY.equals(usertype)){
			if(NestUtil.isEmpty(toSchoolDetail)){
				schoolCountlist = appraisalWritedStaticsExt.querySJAppraiseStatics(levelCode, queryJB, discode, termId, userDto.getTermId());
			}else{
				schoolCountlist = appraisalWritedStaticsExt.queryQXAppraiseStatics(levelCode, queryJB, discode, schoolName,termId,userDto.getTermId());
			}
		}
		if(schoolCountlist!=null&&schoolCountlist.size()>0)
		{
			for(AppraisalWritedStaticseDto appWSDto : schoolCountlist)
			{
				row = CreateCell(++j,ColumnNum);
				row.getCell(0).setCellValue(++i);
				row.getCell(1).setCellValue(appWSDto.getClassInfo());
				row.getCell(2).setCellValue(IntegerToString(appWSDto.getOneCount()));
				row.getCell(3).setCellValue(IntegerToString(appWSDto.getOneCountFinished()));
				row.getCell(4).setCellValue(IntegerToString(appWSDto.getTwoCount()));
				row.getCell(5).setCellValue(IntegerToString(appWSDto.getTwoCountFinished()));
				row.getCell(6).setCellValue(IntegerToString(appWSDto.getThreeCount()));
				row.getCell(7).setCellValue(IntegerToString(appWSDto.getThreeCountFinished()));
				if(ColumnNum==10)
				{
					row.getCell(8).setCellValue(IntegerToString(appWSDto.getFourCount()));
					row.getCell(9).setCellValue(IntegerToString(appWSDto.getFourCountFinished()));
				}
			}
		}
	}
	/**
	 * integer转为String
	 * @param num
	 * @return
	 */
	private String IntegerToString(Integer num)
	{
		return null==num?"":String.valueOf(num);
	}
	/**
	 * 填充模板中的届别
	 */
	private void areaOrCityFillTermName()
	{
		HSSFCell titleCell = sheet.getRow(0).getCell(0);
		titleCell.setCellValue(termName+"学校填写情况");
		titleCell.setCellStyle(titleCellStyle);
		HSSFRow termRow = sheet.getRow(1);
		List<String> termNamelist = appraisalStaticsExt.queryJB(levelCode, "", "", "");
		if(termNamelist!=null&&termNamelist.size()>0)
		{
			for(int i=0;i<termNamelist.size();++i)
			{
				termRow.getCell((i+1)*2).setCellValue(termNamelist.get(i)+"届");
			}
		}
	}
	/**
	 * 根据条件获取设置filepath
	 */
	private void getFilePath() {
		if (Constant.USER_KIND_COUNTY.equals(usertype))// 区县
		{
			if (Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)) {
				ColumnNum = 8;
				filepath = "SeniorAreaSchoolFillCount";
			} else if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelCode)) {
				filepath = "areaSchoolFillCount";
				ColumnNum = 10;
			}
		} else if (Constant.USER_KIND_CITY.equals(usertype)) // 市级
		{
			if (Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)) {
				ColumnNum = 8;
				filepath = "SeniorCitySchoolFillCount";
			} else if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelCode)) {
				ColumnNum = 10;
				filepath = "citySchoolFillCount";
			}
		}
	}
	/**
	 * 获取区县的名称
	 */
	private void getAreaName()
	{
		List<String> discodeList = null;
		if (Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)) {
			discodeList = appraisalWritedStaticsExt.queryQXInfos(levelCode);
		} else if (Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(levelCode)) {
			discodeList = appraisalWritedStaticsExt.queryQXInfos("");
		}
		if(discodeList!=null&&discodeList.size()>0)
		{
			for(int i=0;i<discodeList.size();++i)
			{
				if(discodeList.get(i).contains(NestUtil.isEmpty(discode)?"":discode))
				{
					discodeName = discodeList.get(i).split("@")[1];
				}
			}
		}
	}
}
