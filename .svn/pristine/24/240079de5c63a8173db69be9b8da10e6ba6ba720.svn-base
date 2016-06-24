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


public class SeniorAppraisalExport extends ExportAll{
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
				dtos = appraiseMaps.get(dto.getAppraisaltypeid());
				if (dtos == null) {
					dtos = new ArrayList<AppraiseBaseDto>();
					appraiseMaps.put(dto.getAppraisaltypeid(), dtos);
				}
				dtos.add(dto);
			}
		}
		return null;
	}
	@DefaultAction
    public Object ApprasialExport(HttpServletRequest req, HttpServletResponse resp)
    {
		try {
			generateZip(req,resp,studentName,termidName);
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
			generateExcel(studentName,termidName);
			zos.close();
			os.close();
		} catch (Exception e) {
			logger.error(
					"generateZipFile(HttpServletRequest,HttpServletResponse,String,List<String>,String)",
					e);
		}
		return null;
    }
	
	/**
	 * 学期开始的我
	 */
	private void termBegin()
	{
		single_apprasial(Constant.TYPE_START_ZWPJ,Constant.TYPE_ZWPJ);
		list=appraiseMaps.get(Constant.TYPE_START_WDFZMB);
		if(list!=null){
		   AppraiseBaseDto app_me=null;  //我的发展目标
		   AppraiseBaseDto app_parent=null;  //家长的期望
		   for(AppraiseBaseDto app : list)
		   {
			  if(app.getAppraseridentifynum().equals("1"))
			  {
					app_me=app;	
			  }else{
				  app_parent=app;
			  }
	       }
		   if(app_me!=null)
		   {
			   termBegin_end_appraisal(app_me,Constant.TYPE_DEVELOP_TATGET);
		   }else{
			   not_appraisal(Constant.TYPE_DEVELOP_TATGET);
		   }
		   if(app_parent!=null)
		   {
			   termBegin_end_appraisal(app_parent,Constant.TYPE_BEGIN_EXPECT);
		   }else{
			   not_appraisal(Constant.TYPE_BEGIN_EXPECT);
		   }
		   list.clear();
		}else{
			not_appraisal(Constant.TYPE_DEVELOP_TATGET);
			not_appraisal(Constant.TYPE_BEGIN_EXPECT);
		}
		region_First(Constant.TYPT_NEW_ME);
	}
	/**
	 * 学期结束时的我
	 */
	private void termEnd()
	{
		single_apprasial(Constant.TYPE_END_ZWPJ,Constant.TYPE_ZWPJ);
		single_apprasial(Constant.TYPE_END_WDFZMB,Constant.TYPE_DEVELOP_TATGET);
		single_apprasial(Constant.TYPE_END_BZRPY,Constant.TYPE_BZRPY);
		single_apprasial(Constant.TYPE_END_JZPYQW,Constant.TYPE_END_EXPECT);
		region_First(Constant.TYPE_END_ME);
	}
	
	/**
	 * 思想道德
	 */
	private void morality()
	{
		mutiple_apprasial(Constant.TYPE_SX_ZWPJ,Constant.TYPE_ZWPJ);
		createRowCell_other(Constant.TYPE_SX_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
		record_package(Constant.TYPE_SXJLD,Constant.TYPE_SXDD_BAG);
		region_First(Constant.TYPE_SXDD);
	}
	
	/**
	 * 学业成就
	 */
    private void academic_Record()
    {
    	record_package(Constant.TYPE_XY_GCJL,Constant.TYPE_XYCJ_SHOW);
    	suject_Appraise(Constant.TYPE_KE_CHENG_PINGYU, Constant.TYPE_KCPY);
    	mutiple_apprasial(Constant.TYPE_XY_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_XY,Constant.TYPE_APPRAISAL_OTHER);
    	region_First(Constant.TYPE_XYCJ);
    }
    /**
     * 合作与交流
     */
    private void cooperation_Exchange()
    {
    	mutiple_apprasial(Constant.TYPE_HZ_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_HZ_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	record_package(Constant.TYPE_HZ_JLD,Constant.TYPE_HZYJL_BAG);
    	region_First(Constant.TYPE_HZYJL);
    }
   
    /**
     * 运动与健康
     */
    private void sports_Health()
    {
    	mutiple_apprasial(Constant.TYPE_YDJK_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_YDJK_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	physical_Health(levelCode);
    	region_First(Constant.TYPE_YDYJK);
    }
    /**
     * 审美与表现
     */
    private void esthetic_Express()
    {
    	mutiple_apprasial(Constant.TYPE_SMYBX_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_SMYBX_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	record_package(Constant.TYPE_SMYBX_JLD,Constant.TYPE_SMYBX_BAG);
    	region_First(Constant.TYPE_SMYBX);
    }
   
    /**
     * 个性发展
     */
    private void personality_Develop()
    {
    	develop_Record();
    	mutiple_apprasial(Constant.TYPE_GXFZ_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_GXFZ_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	record_package(Constant.TYPE_GXFZGC,Constant.TYPE_PROCESS);
    	record_package(Constant.TYPE_GXFZ_CGZS,Constant.TYPE_TCYCG_SHOW);
    	region_First(Constant.TYPE_GXYFZ);
    }
	
	/**
	 * 综合实践活动
	 */
	private void comprehensive_Practice()
	{
		study_Content(Constant.TYPE_YJXX,Constant.TYPE_STUDY);
		community_Service(Constant.TYPE_SQFU,Constant.TYPE_COMMIUNITY);
		social_Practice(Constant.TYPE_SHSJHD,Constant.TYPE_PRACTICE);
		region_First(Constant.TYPE_ZHSJ);
	}
	/**
	 * 他人评价(同学,教师,家长)
	 * @param sectionCode 栏目号
	 * @param sectionName 栏目名称
	 */
	private void createRowCell_other(String sectionCode,String sectionName)
	{
		list=appraiseMaps.get(sectionCode);
		List<AppraiseBaseDto> classmate_app=new ArrayList<AppraiseBaseDto>(); 
		List<AppraiseBaseDto> teacher_app=new ArrayList<AppraiseBaseDto>(); 
		List<AppraiseBaseDto> parent_app=new ArrayList<AppraiseBaseDto>(); 
		if(list!=null)
		{
			for(AppraiseBaseDto app : list)
			{
				if(app.getAppraseridentifynum().equals("2"))    //同学评价
				{
					classmate_app.add(app);
				}else if(app.getAppraseridentifynum().equals("3")||app.getAppraseridentifynum().equals("4"))  //老师评价
				{
					teacher_app.add(app);
				}else{    //家长评价
					parent_app.add(app);
				}
			}
			other_mutiple_appraisal(classmate_app,Constant.APPRASER_STUDENT);
			other_mutiple_appraisal(teacher_app,Constant.APPRASER_TEACHER);
			other_mutiple_appraisal(parent_app,Constant.APPRASER_PARENT);
			
		}else{
			other_mutiple_appraisal(classmate_app,Constant.APPRASER_STUDENT);
			other_mutiple_appraisal(teacher_app,Constant.APPRASER_TEACHER);
			other_mutiple_appraisal(parent_app,Constant.APPRASER_PARENT);
		}
		sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 1, 1));
		HSSFRow row=sheet.getRow(startrow2);
		row.getCell(1).setCellValue(Constant.TYPE_APPRAISAL_OTHER);
		row.getCell(1).setCellStyle(cellStyle);
	}
	/**
	 * 学业成就课程评语
	 * @param sectionCode  栏目号
	 * @param sectionName  栏目名称
	 */
	private void suject_Appraise(String sectionCode,String sectionName)
	{
		list = appraiseMaps.get(Constant.TYPE_KE_CHENG_PINGYU);
		if(list!=null)
		{
			int i = 0;
			for(AppraiseBaseDto app : list)
			{
				edit_recordPackage(app,sectionName,++i);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	/**
	 * 研究性学习
	 * @param SectionCode  研究性学习栏目号
	 * @param sectionName  研究性学习
	 */
	private void study_Content(String SectionCode,String sectionName)
	{
		list = appraiseMaps.get(SectionCode);
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
		       package_base("题目",app.getItem1());
		       package_base("合伙人",app.getItem2());
		       package_base("总学时数",app.getItem3());
		       package_base("实施路径",app.getItem4());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
				   CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,3, 6);
				   createRegionStyle(otherStyle, region1);
				   sheet.addMergedRegion(region1);
				   row2.getCell(3).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	/**
	 * 综合实践活动自我评价
	 * @param practicesSelfAppraiseDtos  综合实践活动自我评价集合
	 * @return true 没有自我评价   false 有自我评价
	 */
	private boolean activity_Self(List<AppraiseBaseDto> practicesSelfAppraiseDtos)
	{
		if (practicesSelfAppraiseDtos!=null&&practicesSelfAppraiseDtos.size()>0) {
			for (AppraiseBaseDto app : practicesSelfAppraiseDtos) {
				HSSFRow row1 = createRow();
				int num = getExcelCellAutoHeight(app.getMyselfapprasercontent(), 39);
				row1.setHeightInPoints(num*20);
				CellRangeAddress region = new CellRangeAddress(endrow, endrow,4, 6);
				createRegionStyle(package_contentStyle, region);
				sheet.addMergedRegion(region);
				row1.getCell(4).setCellValue(replaceEsc(app.getMyselfapprasercontent()));
			}
			CellRangeAddress region2 = new CellRangeAddress(endrow-practicesSelfAppraiseDtos.size() + 1, endrow, 3, 3);
			createRegionStyle(packageStyle, region2);
			sheet.addMergedRegion(region2);
			sheet.getRow(endrow-practicesSelfAppraiseDtos.size()+1).getCell(3).setCellValue(Constant.TYPE_ZWPJ);
			HSSFRow row2 = createRow();
			CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,3, 6);
			createRegionStyle(otherStyle, region1);
			sheet.addMergedRegion(region1);
			row2.getCell(3).setCellValue(getMessage(practicesSelfAppraiseDtos.get(0)));
			return false;
		}
		return true;
	}
	/**
	 * 社区服务
	 */
	private void community_Service(String SectionCode,String sectionName)
	{
		list = appraiseMaps.get(SectionCode);
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
		       package_base("选择次数",app.getItem1());
		       package_base("社区服务名称",app.getItem2());
		       package_base("社区服务电话",app.getItem3());
		       package_base("服务时数",app.getItem4());
		       package_base("服务时间",app.getItem5());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		//       activity_Self(practicesSelfAppraiseDtos);
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
				   CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,3, 6);
				   createRegionStyle(otherStyle, region1);
				   sheet.addMergedRegion(region1);
				   row2.getCell(3).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	/**
	 * 社会实践
	 */
	private void social_Practice(String Sectioncode,String sectionName)
	{
		list = appraiseMaps.get(Sectioncode);
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
		       package_base("选择活动种",app.getItem1());
		       package_base("成果形成",app.getItem2());
		       package_base("地点",app.getItem4());
		       package_base("完成学时",app.getItem5());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		     //  activity_Self(practicesSelfAppraiseDtos);
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
				   CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,3, 6);
				   createRegionStyle(otherStyle, region1);
				   sheet.addMergedRegion(region1);
				   row2.getCell(3).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
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
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
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
		if(app.getAppraisaltypeid().equals(Constant.TYPE_XY_GCJL)||app.getAppraisaltypeid().equals(Constant.TYPE_KE_CHENG_PINGYU))
		{
			HSSFRow row=createRow();
			row.getCell(3).setCellValue("学科");
			row.getCell(3).setCellStyle(packageStyle);
			CellRangeAddress region3 = new CellRangeAddress(endrow,endrow,4,6);
			createRegionStyle(package_contentStyle, region3);
			sheet.addMergedRegion(region3);
			row.getCell(4).setCellValue(app.getItem1());
		}
		HSSFRow row2=createRow();
		row2.getCell(3).setCellValue("内容描述");
		row2.getCell(3).setCellStyle(packageStyle);
		int num = getExcelCellAutoHeight(replaceEsc(app.getApprasial()), 39);
		row2.setHeightInPoints(num*20);
		CellRangeAddress region5 = new CellRangeAddress(endrow,endrow,4,6);
		createRegionStyle(package_contentStyle, region5);
		sheet.addMergedRegion(region5);
		row2.getCell(4).setCellValue(replaceEsc(app.getApprasial()));
		if (!app.getAppraisaltypeid().equals(Constant.TYPE_KE_CHENG_PINGYU)) {
			attach_Activity(name_True,app);
		}
		HSSFRow row5=createRow();
		CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,3,6);
		createRegionStyle(otherStyle,region2);
		sheet.addMergedRegion(region2);
		row5.getCell(3).setCellValue(getMessage(app));
	}
	/**
	 * 综合实践活动附件
	 */
	private void attach_Activity(String SectionName,AppraiseBaseDto app)
	{
		List<AttachFileDto> attachlist = app.getAttachFileDtos();
		if (attachlist!=null&&attachlist.size()>0) {
			int i = 0;
			for (AttachFileDto attach : attachlist) {
				++i;
				HSSFRow row3 = createRow();
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
	/**
   	 * 个性发展基本情况
   	 */
   	private void develop_Record()
   	{
   		list = appraiseMaps.get(Constant.TYPE_GXFZ_JBQK);
   		AppraiseBaseDto app_specialty = null;
   		AppraiseBaseDto app_create = null;
   		AppraiseBaseDto app_other = null;
   		if(list!=null)
   		{
   			develop_Single("二级指标","三级指标","个人发展记录");
   			for(AppraiseBaseDto app : list)
   			{
   				if(app.getAppraseridentifynum().equals("1"))
   				{
   					app_specialty = app;
   				}else if(app.getAppraseridentifynum().equals("2"))
   				{
   					app_create = app;
   				}else{
   					app_other = app;
   				}
   			}
   			if(app_specialty!=null){
   				develop_Single("特长","学科特长体育运动特长\n艺术特长",replaceEsc(app_specialty.getApprasial()));
   				HSSFRow row = sheet.getRow(endrow);
   				row.setHeightInPoints(2*20);
   			}else{
   				develop_Single("特长","学科特长体育运动特长艺术特长","");
   			}
   			if(app_create!=null)
   			{
   				develop_Single("有新意的成果","活动成果设计成果制作成果",replaceEsc(app_create.getApprasial()));
   			}else{
   				develop_Single("有新意的成果","活动成果设计成果制作成果","");
   			}
   			if(app_other!=null)
   			{
   				develop_Single("其他","自主选择",replaceEsc(app_other.getApprasial()));
   			}else{
   				develop_Single("其他","自主选择","");
   			}
   			HSSFRow row = createRow();
			CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,3, 6);
			createRegionStyle(otherStyle, region1);
			sheet.addMergedRegion(region1);
			row.getCell(3).setCellValue(getMessage(list.get(0)));
   			CellRangeAddress region = new CellRangeAddress(startrow,endrow,1,2);
   			sheet.addMergedRegion(region);
   			sheet.getRow(startrow).getCell(1).setCellValue(Constant.TYPE_BASE);
   			beginrow=endrow+1;
   		}else{
   			not_appraisal(Constant.TYPE_BASE);
   		}
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
		int num = getExcelCellAutoHeight(replaceEsc(app.getApprasial()), 50);
		row1.setHeightInPoints(num*20);
		row1.getCell(3).setCellValue(replaceEsc(app.getApprasial()));
	    HSSFRow row2=createRow();
	    CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,3,6);
	    createRegionStyle(otherStyle,region2);
	    sheet.addMergedRegion(region2);
	    row2.getCell(3).setCellValue(getMessage(app));
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
	 * 他人评价中的同学评价、老师评价、家长评价
	 * @param list    同学评价、老师评价、家长评价集合
	 * @param appraisalName   同学评价、老师评价、家长评价
	 */
	private void other_mutiple_appraisal(List<AppraiseBaseDto> list,String appraisalName)
	{
		if(list.size()>0)
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

}
