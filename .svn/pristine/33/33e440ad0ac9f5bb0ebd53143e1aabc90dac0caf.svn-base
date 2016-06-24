package com.flyrish.hades.webapp.action.export;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ExportAll;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.AttachFileDto;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;

public class JuniorAppraisalExport extends ExportAll{
   
	public String studentName;
	public String termId;
	public String levelCode;
	public String eduId;
	public String cmis30id;
	public String discode;
	public String termidName;
	
	@Spring
	IOperationAppraiseServiceExt operationAppraiseServiceExt;
	@Before
	public Object QueryBeforeExport()
	{
		List<AppraiseBaseDto> appraiseBaseDtos=operationAppraiseServiceExt.queryAppraiseBaseDtoByCondition(studentName,eduId,termId, levelCode,cmis30id,discode);
		if (appraiseBaseDtos != null && appraiseBaseDtos.size() > 0) {
			for (AppraiseBaseDto dto : appraiseBaseDtos) {
				List<AppraiseBaseDto> dtos = null;
				dtos = appraiseMaps.get(dto.getTwoPartId());
				if (dtos == null) {
					dtos = new ArrayList<AppraiseBaseDto>();
					appraiseMaps.put(dto.getTwoPartId(), dtos);
				}
				dtos.add(dto);
			}
		}
		return null;
	}
	@DefaultAction
	public Object AppraisalExport(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			generateZip(req,resp,studentName,termidName);
			generate();
			generateExcel(studentName,termidName);
			zos.close();
			os.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	private void generate()
	{
		initExcel();
		termBegin();
		termEnd();
		morality();
		academic_Record();
		cooperation_Exchange();
		sports_Health();
		esthetic_Express();
		comprehensive_Practice();
		personality_Develop();
	}
	/**
	 * 评论单条时调用的方法
	 * @param sectionCode  栏目号
	 * @param sectionName 栏目名字
	 */
	private void single_apprasial(String sectionCode,String sectionName)
	{
		list=appraiseMaps.get(sectionCode);
		if(list!=null)
		{
			for(AppraiseBaseDto app : list)
			{
				termBegin_end_appraisal(app,sectionName);
			}
			list.clear();
		}else
		{
			not_appraisal(sectionName);
		}
	}
	/**
	 * 新学期的我和学期结束的我有内容时
	 * @param app   评价信息
	 * @param sectionName  栏目名称
	 */
	private void termBegin_end_appraisal(AppraiseBaseDto app,String sectionName)
	{
		    createRowCell(app);
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
		    HSSFRow row1=sheet.getRow(beginrow);
		    row1.getCell(1).setCellValue(sectionName);
		    beginrow=endrow+1;
	}
	/**
	 * 当评价内容为评价内容、签名信息时调用(不是记录袋)
	 * @param app   评价信息
	 */
	private void createRowCell(AppraiseBaseDto app)
	{
		HSSFRow row1=createRow();
		CellRangeAddress region1 = new CellRangeAddress(endrow,endrow,3,6);
		createRegionStyle(contentStyle,region1);
		sheet.addMergedRegion(region1);
		int num = getExcelCellAutoHeight(app.getPartInfo(), 50);
		row1.setHeightInPoints(num*20);
		row1.getCell(3).setCellValue(replaceEsc(app.getPartInfo()));
	    HSSFRow row2=createRow();
	    CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,3,6);
	    createRegionStyle(otherStyle,region2);
	    sheet.addMergedRegion(region2);
	    row2.getCell(3).setCellValue(appraisalMessage(app));
	}
	private String appraisalMessage(AppraiseBaseDto appraisebasedto)
	{
		StringBuffer string=new StringBuffer("评价人：");
		string.append(appraisebasedto.getWriteMan());
		string.append(" 签名：");
		string.append(appraisebasedto.getSignerName());
		string.append(" 时间：");
		string.append(appraisebasedto.getCreateDate());
		return string.toString();
	}
	/**
	 * 新学期伊始的我
	 */
	private void termBegin()
	{
		single_apprasial(Constant.TERMS_BEGIN_ME, Constant.TYPE_ZWPJ);
		single_apprasial(Constant.DEVELOP_TARGET_ME,Constant.TYPE_DEVELOP_TATGET);
		region_First(Constant.TYPT_NEW_ME);
	}
	/**
	 * 学期结束时的我
	 */
	private void termEnd()
	{
		single_apprasial(Constant.TERMS_END_ME, Constant.TYPE_ZWPJ);
		single_apprasial(Constant.CHARGE_TEACHER_APPRAISAL, Constant.TYPE_BZRPY);
		single_apprasial(Constant.PRAENTS_APPRAISAL_EXPECT, Constant.TYPE_QWJY);
		region_First(Constant.TYPE_END_ME);
	}
	/**
	 * 他人评价时调用
	 */
	private void region_other()
	{
		sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 1, 1));
		HSSFRow row=sheet.getRow(startrow2);
		row.getCell(1).setCellValue(Constant.TYPE_APPRAISAL_OTHER);
		row.getCell(1).setCellStyle(cellStyle);
	}
	/**
	 * 他人评价中的同学评价、老师评价、家长评价
	 * @param list    同学评价、老师评价、家长评价集合
	 * @param appraisalName   同学评价、老师评价、家长评价
	 */
	private void other_mutiple_appraisal(List<AppraiseBaseDto> list,String appraisalName)
	{
		if(list!=null&&list.size()>0)
		{
			for(AppraiseBaseDto app : list)
			{
				createRowCell(app);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,2));
			sheet.getRow(beginrow).getCell(2).setCellValue(appraisalName);
			sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
			beginrow=endrow+1;
		}else{
			createRow();
			HSSFRow row=sheet.getRow(endrow);
			CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
			createRegionStyle(cellStyle,region);
			sheet.addMergedRegion(region);
			row.getCell(2).setCellValue(appraisalName);
			beginrow=endrow+1;
		}
	}

	/**
	 * 他人评价(学业成就他人评价除外)
	 * @param code1   同学评价栏目号
	 * @param code2   教师评价栏目号
	 * @param code3   家长评价栏目号
	 */
	private void Appraisal_other(String code1,String code2,String code3)
	{
		list = appraiseMaps.get(code1);   //同学评价
		other_mutiple_appraisal(list,Constant.APPRASER_STUDENT);
		list = appraiseMaps.get(code2);  //教师评价
		other_mutiple_appraisal(list, Constant.APPRASER_TEACHER);
		list = appraiseMaps.get(code3);    //家长评价
		other_mutiple_appraisal(list, Constant.APPRASER_PARENT);
		region_other();
	}
	/**
	 * 评论为多条时调用方法(记录袋、二级栏目细分的除外)
	 * @param sectionCode   栏目号
	 * @param sectionName   栏目名称
	 */
	private void mutiple_apprasial(String sectionCode,String sectionName)
	{
		list=appraiseMaps.get(sectionCode);
		if(list!=null)
		{
			for(AppraiseBaseDto app : list)
			{
				createRowCell(app);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			sheet.getRow(beginrow).getCell(1).setCellStyle(cellStyle);
		}else{
			not_appraisal(sectionName);
		}
		startrow2=endrow+1;
		beginrow=endrow+1;
	}
	/**
	 * 思想道德
	 */
	private void morality()
	{
		mutiple_apprasial(Constant.MORALITY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		Appraisal_other(Constant.MORALITY_CLASSMATES_APPRAISAL,Constant.MORALITY_TEACHER_APPRAISAL,Constant.MORALITY_PARENT_APPRAISAL);
		record_package(Constant.MORALITY_RECORD_BAG,Constant.TYPE_SXDD_BAG);
		region_First(Constant.TYPE_SXDD);
	}
	/**
	 * 学业成就的他人评价
	 */
	private void academic_other()
	{
		record_package(Constant.WORKS_SUBJECT_APPRAISAL, Constant.TYPE_KCPY);
		list = appraiseMaps.get(Constant.WORKS_CLASSMATES_APPRAISAL);  //同学评价
		list = appraiseMaps.get(Constant.WORKS_CLASSMATES_APPRAISAL);  //同学评价
		other_mutiple_appraisal(list, Constant.APPRASER_STUDENT);
		list = appraiseMaps.get(Constant.WORKS_PARENT_APPRAISAL);    //家长评价
		other_mutiple_appraisal(list, Constant.APPRASER_PARENT);
		region_other();
	}
	/**
	 * 学业成就
	 */
	private void academic_Record()
	{
		mutiple_apprasial(Constant.WORKS_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		academic_other();
		record_package(Constant.WORKS_SUBJECT_SHOW,Constant.TYPE_XYCJ_SHOW);
		region_First(Constant.TYPE_XYCJ);
	}
	/**
	 * 合作与交流
	 */
	private void cooperation_Exchange()
	{
		mutiple_apprasial(Constant.COOPERATION_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		Appraisal_other(Constant.COOPERATION_CLASSMATES_APPRAISAL,Constant.COOPERATION_TEACHER_APPRAISAL,Constant.COOPERATION_PARENT_APPRAISAL);
		record_package(Constant.COOPERATION_RECORD_BAG, Constant.TYPE_HZYJL_BAG);
		region_First(Constant.TYPE_HZYJL);
	}
	/**
	 * 运动与健康
	 */
	private void sports_Health()
	{
		mutiple_apprasial(Constant.PLAY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		Appraisal_other(Constant.PLAY_CLASSMATES_APPRAISAL,Constant.PLAY_TEACHER_APPRAISAL,Constant.PLAY_PARENT_APPRAISAL);
		physical_Health(levelCode);
		region_First(Constant.TYPE_YDYJK);
	}
	/**
	 * 审美与表现
	 */
	private void esthetic_Express()
	{
		mutiple_apprasial(Constant.AESTHETIC_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		Appraisal_other(Constant.AESTHETIC_CLASSMATES_APPRAISAL,Constant.AESTHETIC_TEACHER_APPRAISAL,Constant.AESTHETIC_PARENT_APPRAISAL);
		record_package(Constant.AESTHETIC_RECORD_BAG,Constant.TYPE_SMYBX_BAG);
		region_First(Constant.TYPE_SMYBX);
	}
	/**
	 * 个性发展
	 */
	private void personality_Develop()
	{
		mutiple_apprasial(Constant.INDIVIDUALITY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
		Appraisal_other(Constant.INDIVIDUALITY_CLASSMATES_APPRAISAL,Constant.INDIVIDUALITY_TEACHER_APPRASIAL,Constant.INDIVIDUALITY_PARENT_APPRAISAL);
		record_package(Constant.INDIVIDUALITY_RECORD_BAG,Constant.TYPE_TCYCG_SHOW);
		region_First(Constant.TYPE_GXYFZ);
	}
	/**
	 * 研究性学习基本情况
	 * @param SectionCode
	 * @param sectionName  栏目名称（基本情况）
	 */ 
	private void study_Appraisal(String sectionName)
	{
		list = appraiseMaps.get(Constant.ACTIVITY_BASEINFO_1);
		if(list!=null)
		{
			int i=0;
		    for(AppraiseBaseDto app : list)
		    {
		    	++i;
		       HSSFRow row = createRow();
		       CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
		       createRegionStyle(package_contentStyle, region);
		       sheet.addMergedRegion(region);
		       String name_True = sectionName+i;
		       row.getCell(3).setCellValue(name_True);
		       package_base("主题",app.getTopic());
		       package_base("关键字",app.getKeyword());
		       package_base("合作人",app.getCooperationMan());
		       package_base("起止时间",app.getStartDate()+"至"+app.getEndDate());
		       package_base("内容摘要",app.getPartInfo());
		       HSSFRow row5=createRow();
			   CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,3,6);
			   createRegionStyle(otherStyle,region2);
			   sheet.addMergedRegion(region2);
			   row5.getCell(3).setCellValue(appraisalMessage(app));
		    }
		    
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,2));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			HSSFRow row1 = createRow();
			row1.getCell(2).setCellValue(Constant.TYPE_BASE);
			CellRangeAddress region = new CellRangeAddress(endrow,
					endrow, 3, 6);
			createRegionStyle(cellStyle, region);
			sheet.addMergedRegion(region);
			beginrow = endrow + 1;
		}
	}
	/**
	 * 综合实践活动的自我评价
	 */
	private void activity_self(String sectionCode,String sectionName)
	{
		list=appraiseMaps.get(sectionCode);
		if(list!=null)
		{
			for(AppraiseBaseDto app : list)
			{
				createRowCell(app);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,2));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
		}else{
			createRow();
			HSSFRow row=sheet.getRow(endrow);
			CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
			createRegionStyle(cellStyle,region);
			sheet.addMergedRegion(region);
			row.getCell(2).setCellValue(sectionName);
		}
		beginrow=endrow+1;
	}
	/**
	 * 综合实践活动的附件调用
	 * @param sectionCode
	 * @param sectionName
	 */
	private void activity_attach(String sectionName)
	{
		list = appraiseMaps.get(Constant.ACTIVITY_RESEARCH_RESULT);
		if (list != null) {
			for (AppraiseBaseDto appraise : list) {
				List<AttachFileDto> attachlist = appraise.getAttachFileDtos();
				if (attachlist != null && attachlist.size() > 0) {
					int i = 0;
					for (AttachFileDto app : attachlist) {
						++i;
						HSSFRow row1 = createRow();
						CellRangeAddress region = new CellRangeAddress(endrow,
								endrow, 4, 6);
						row1.getCell(3).setCellStyle(packageStyle);
						row1.getCell(3).setCellValue(Constant.attach+i);
						createRegionStyle(package_contentStyle, region);
						sheet.addMergedRegion(region);
						row1.getCell(4).setCellValue(app.getFilename());
						generateAttach(sectionName,app.getFilename(),app.getAttachpath());
					}
					HSSFRow row2 = createRow();
					CellRangeAddress region1 = new CellRangeAddress(endrow,
							endrow, 3, 6);
					createRegionStyle(otherStyle, region1);
					sheet.addMergedRegion(region1);
					row2.getCell(3).setCellValue(appraisalMessage(appraise));
					sheet.addMergedRegion(new CellRangeAddress(beginrow,
							endrow, 2, 2));
					sheet.getRow(beginrow).getCell(2).setCellValue("研究成果");
					sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
					beginrow = endrow + 1;

				}else{
					HSSFRow row1 = createRow();
					row1.getCell(2).setCellValue("研究成果");
					CellRangeAddress region = new CellRangeAddress(endrow,
							endrow, 3, 6);
					createRegionStyle(cellStyle, region);
					sheet.addMergedRegion(region);
					beginrow = endrow + 1;
				}
			}
		}else {
			HSSFRow row1 = createRow();
			row1.getCell(2).setCellValue("研究成果");
			CellRangeAddress region = new CellRangeAddress(endrow,
					endrow, 3, 6);
			createRegionStyle(cellStyle, region);
			sheet.addMergedRegion(region);
			beginrow = endrow + 1;
		}
	}
	/**
	 * 社区服务与社会综合实践中的基本情况
	 */
	private void social_Service(String sectionName)
	{
		list = appraiseMaps.get(Constant.ACTIVITY_BASEINFO_2);
		if(list!=null)
		{
			int i=0;
			for(AppraiseBaseDto app : list)
			{
				edit_recordPackage(app,sectionName,++i);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,2));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			createRow();
			HSSFRow row=sheet.getRow(endrow);
			CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
			createRegionStyle(cellStyle,region);
			sheet.addMergedRegion(region);
			row.getCell(2).setCellValue(sectionName);
			beginrow=endrow+1;
		}
	}
	/**
	 * 研究性学习
	 */
	private void comprehensive_Practice1()
	{
		startrow2=beginrow;
		study_Appraisal(Constant.TYPE_BASE);
		activity_attach("研究成果");
		activity_self(Constant.ACTIVITY_SELF_APPRAISAL_1,Constant.TYPE_ZWPJ);
		sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 1, 1));
		HSSFRow row=sheet.getRow(startrow2);
		row.getCell(1).setCellValue("研究性学习");
		row.getCell(1).setCellStyle(cellStyle);
	}
	/**
	 * 社区服务和社会实践活动
	 */
	private void comprehensive_Practice2()
	{
		startrow2=beginrow;
		social_Service(Constant.TYPE_BASE);
		activity_self(Constant.ACTIVITY_SELF_APPRAISAL_2, Constant.TYPE_ZWPJ);
		sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 1, 1));
		HSSFRow row=sheet.getRow(startrow2);
		row.getCell(1).setCellValue(Constant.TYPE_SOCIAL);
		row.getCell(1).setCellStyle(cellStyle);
	}
	/**
	 * 综合实践活动
	 */
	private void comprehensive_Practice()
	{
		comprehensive_Practice1();
		comprehensive_Practice2();
		region_First(Constant.TYPE_ZHSJ);
	}
	
	/**
	 * 记录袋时调用
	 * @param sectionCode  栏目号
	 * @param sectionName  栏目名称
	 */
	private void record_package(String sectionCode,String sectionName)
	{
		list = appraiseMaps.get(sectionCode);
		if(list!=null)
		{
			int i=0;
			for(AppraiseBaseDto app : list)
			{
				edit_recordPackage(app,sectionName,++i);
			}
			if(Constant.WORKS_SUBJECT_APPRAISAL.equals(sectionCode))
			{
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,2));
				sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
				sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
			}else{
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
				sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			}
			beginrow=endrow+1;
		}else{
			if(Constant.WORKS_SUBJECT_APPRAISAL.equals(sectionCode))
			{
				createRow();
				HSSFRow row=sheet.getRow(endrow);
				CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
				createRegionStyle(cellStyle,region);
				sheet.addMergedRegion(region);
				row.getCell(2).setCellValue(sectionName);
				beginrow=endrow+1;
			}else{
				not_appraisal(sectionName);
			}
		}
	}
	/**
	 * 创建记录袋
	 * @param app  记录袋信息
	 * @param sectionName  栏目名称
	 * @param i  栏目名称后面的序号
	 */
	private void edit_recordPackage(AppraiseBaseDto app,String sectionName,int i)
	{
		HSSFRow row1=createRow();
		CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
		createRegionStyle(package_contentStyle, region);
		sheet.addMergedRegion(region);
		String name_True = sectionName+i;
		row1.getCell(3).setCellValue(name_True);
		if(Constant.WORKS_SUBJECT_SHOW.equals(app.getTwoPartId()))
		{
			package_base("主题", app.getTopic());
			package_base("学科", app.getSubjectName());
		}else if(Constant.WORKS_SUBJECT_APPRAISAL.equals(app.getTwoPartId()))
		{
			package_base("学科", app.getSubjectName());
		}else if(Constant.ACTIVITY_BASEINFO_2.equals(app.getTwoPartId())){
			package_base("主题", app.getTopic());
			package_base("地点",app.getAddress());
		    package_base("起止时间",app.getStartDate()+"至"+app.getEndDate());
		}else{
			package_base("主题", app.getTopic());
		}
		HSSFRow row2=createRow();
		int num = getExcelCellAutoHeight(app.getPartInfo(), 39);
		row2.setHeightInPoints(num*20);
		row2.getCell(3).setCellValue("内容描述");
		row2.getCell(3).setCellStyle(packageStyle);
		CellRangeAddress region5 = new CellRangeAddress(endrow,endrow,4,6);
		createRegionStyle(package_contentStyle, region5);
		sheet.addMergedRegion(region5);
		row2.getCell(4).setCellValue(replaceEsc(app.getPartInfo()));
		if(Constant.ACTIVITY_BASEINFO_2.equals(app.getTwoPartId())){
			name_True = Constant.TYPE_SOCIAL+sectionName+i;
		}
		if (!Constant.WORKS_SUBJECT_APPRAISAL.equals(app.getAppraisaltypeid())) {
			attach_Activity(name_True,app);
		}
		HSSFRow row5=createRow();
		CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,3,6);
		createRegionStyle(otherStyle,region2);
		sheet.addMergedRegion(region2);
		row5.getCell(3).setCellValue(appraisalMessage(app));
	}
	/**
	 * 记录袋附件
	 */
	private void attach_Activity(String SectionName,AppraiseBaseDto app)
	{
		List<AttachFileDto> attachlist = app.getAttachFileDtos();
		if (attachlist!=null&&attachlist.size()>0) {
			int i = 0;
			for (AttachFileDto attach : attachlist) {
				++i;
				HSSFRow row3 = createRow();
				row3.getCell(3).setCellStyle(packageStyle);
				CellRangeAddress region4 = new CellRangeAddress(endrow,
						endrow, 4, 6);
				createRegionStyle(package_contentStyle, region4);
				row3.getCell(3).setCellValue(Constant.attach+i);
				row3.getCell(3).setCellStyle(packageStyle);
				sheet.addMergedRegion(region4);
				row3.getCell(4).setCellValue(attach.getFilename());
				generateAttach(SectionName,attach.getFilename(),attach.getAttachpath());
			}
		}
	}
	
}
