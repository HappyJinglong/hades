package com.flyrish.hades.webapp.action.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.util.CellRangeAddress;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ExportSection;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;

public class JuniorExportBySection extends ExportSection{

	//班级名称
		public String className = "xx班";
		//年纪名称
		public String gradeName;
		//班级号
		public String classId;
		//一级栏目号
		public String edu_id;
		//学期id
		public String termid;
		//学期名称
		public String termidName;
		String cmis30id;
		String discode;
		String levelcode;
		public String firstSectionCode;  
		
		UserDto userdto;
		
		
		public Map<String, Map<String,List<AppraiseBaseDto>>> appraiseMaps1 = new LinkedHashMap<String, Map<String,List<AppraiseBaseDto>>>(0);
		public Map<String,List<AppraiseBaseDto>> appraiseMaps2;
		
		@Spring
		public IMasterAppriseExt masterAppriseExt;
		@Spring
		public AppriseMasterAppriseExt appriseMasterAppriseExt;
		@Spring
		public IOperationAppraiseServiceExt operationAppraiseServiceExt;
	    @Before
		public Object BeforeExport(HttpServletRequest req)
		{
	    	initExcel();
	    	userdto = this.getLoginInfo(req);
	    	discode = userdto.getDiscode();
	    	cmis30id = userdto.getCmis30id();
	    	levelcode = userdto.getLevelcode();
	    	List<SchoolTreeDto> stuIfos = this.getStudentInfos(classId,req);
	    	if(stuIfos!=null&&stuIfos.size()>0){
	    		List<String> eduIds = new ArrayList<String>();
				for(SchoolTreeDto slt : stuIfos){
					eduIds.add(slt.getEdusyId());
				}
				if(eduIds.size()>0){
					//获取数据集合
					List<AppraiseBaseDto> appraiseBaseDtos=appriseMasterAppriseExt.queryAppraiseBaseDtoByCondition(firstSectionCode,eduIds,termid,levelcode,cmis30id,discode);
					//组装数据
					installDataMaps(stuIfos,appraiseBaseDtos);
				}
				
	    	}
			return null;
		}
	    /**
		 * 获取班主任管辖下所有学生id
		 * @param classIds
		 * @param gradeId
		 * @param req
		 * @return
		 */
		private List<SchoolTreeDto> getStudentInfos(String classId,HttpServletRequest req) {
			Map<String,Object>params = new HashMap<String,Object>();
			params.put("lid",classId );
			params.put("cmis30Id",cmis30id );
			params.put("discode",discode );
			return masterAppriseExt.getStudentInfo(params);
		}
		private void installDataMaps(List<SchoolTreeDto> stuIfos,List<AppraiseBaseDto> appraiseBaseDtos){
			for(SchoolTreeDto st : stuIfos){
				appraiseMaps2 = new HashMap<String,List<AppraiseBaseDto>>(0);
				if(!(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)){
					for(AppraiseBaseDto dto:appraiseBaseDtos){
					  if(st.getEdusyId().equals(dto.getEdu_id())){
							if(dto==null||NestUtil.isEmpty(dto.getTwoPartId())) continue;
							//组装数据集合
							List<AppraiseBaseDto> dtos=null;
							dtos=appraiseMaps2.get(dto.getTwoPartId());
							if(dtos==null){
								dtos=new ArrayList<AppraiseBaseDto>();
							    appraiseMaps2.put(dto.getTwoPartId(),dtos);
							}
							dtos.add(dto);
					  }
					}
				}
				appraiseMaps1.put(st.getText()+"_"+st.getEdusyId(), appraiseMaps2);
			}
		}
		@DefaultAction
		public Object Export(HttpServletRequest req, HttpServletResponse resp)
		{
			if(Constant.JUNIOR_NEWTERM.equals(firstSectionCode))
			{
				ExportBytermBegin(req,resp);
			}else if(Constant.JUNIOR_TERN_END.equals(firstSectionCode)){
				ExportBytermEnd(req, resp);
			}else if(Constant.JUNIOR_MORALITY.equals(firstSectionCode)){
				ExportByMorality(req,resp);
			}else if(Constant.JUNIOR_STUDY.equals(firstSectionCode)){
				ExportByAcademic(req, resp);
			}else if(Constant.JUNIOR_COOPERATION.equals(firstSectionCode)){
				ExportByCoExchange(req, resp);
			}else if(Constant.JUNIOR_SPROT.equals(firstSectionCode)){
				ExportBySpHealth(req, resp);
			}else if(Constant.JUNIOR_AESTHETIC.equals(firstSectionCode)){
				ExportByEsthetic(req, resp);
			}else if(Constant.JUNIOR_PRACTICE.equals(firstSectionCode)){
				ExportByPractice(req, resp);
			}else if(Constant.JUNIOR_DEVELOP.equals(firstSectionCode)){
				ExportByPeDevelop(req, resp);
			}
			return null;
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
			    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
			    HSSFRow row1=sheet.getRow(beginrow);
			    row1.getCell(2).setCellValue(sectionName);
			    beginrow=endrow+1;
		}
		/**
		 * 当评价内容为评价内容、签名信息时调用(不是记录袋)
		 * @param app   评价信息
		 */
		private void createRowCell(AppraiseBaseDto app)
		{
			
			HSSFRow row1=createRow();
			CellRangeAddress region1 = new CellRangeAddress(endrow,endrow,4,7);
			createRegionStyle(contentStyle,region1);
			sheet.addMergedRegion(region1);
			int num = getExcelCellAutoHeight(replaceEsc(app.getPartInfo()), 50);
			row1.setHeightInPoints(num*20);
			row1.getCell(4).setCellValue(replaceEsc(app.getPartInfo()));
		    HSSFRow row2=createRow();
		    CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,4,7);
		    createRegionStyle(otherStyle,region2);
		    sheet.addMergedRegion(region2);
		    row2.getCell(4).setCellValue(appraisalMessage(app));
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
		private void termBegin(String studentName)
		{
			single_apprasial(Constant.TERMS_BEGIN_ME, Constant.TYPE_ZWPJ);
			single_apprasial(Constant.DEVELOP_TARGET_ME,Constant.TYPE_DEVELOP_TATGET);
			region_First(Constant.TYPT_NEW_ME,studentName);
		}
		/**
		 * 按新学期伊始的我
		 * @param req
		 * @param resp
		 */
		private void ExportBytermBegin(HttpServletRequest req, HttpServletResponse resp)
		{
			for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
	    	{
	    		String studentName = entry.getKey().split("_")[0];
	    		appraiseMaps = entry.getValue();
	    		termBegin(studentName);
	    	}
	    	export_Down(req,resp,className,Constant.TYPT_NEW_ME,termidName);
		}
		/**
		 * 学期结束时的我
		 */
		private void termEnd(String studentName)
		{
			single_apprasial(Constant.TERMS_END_ME, Constant.TYPE_ZWPJ);
			single_apprasial(Constant.CHARGE_TEACHER_APPRAISAL, Constant.TYPE_BZRPY);
			single_apprasial(Constant.PRAENTS_APPRAISAL_EXPECT, Constant.TYPE_QWJY);
			region_First(Constant.TYPE_END_ME,studentName);
		}
		/**
		 * 按学期结束时的我导出
		 * @param req
		 * @param resp
		 */
		private void ExportBytermEnd(HttpServletRequest req, HttpServletResponse resp)
		{

	    	for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
	    	{
	    		String studentName = entry.getKey().split("_")[0];
	    		appraiseMaps = entry.getValue();
	    		termEnd(studentName);
	    	}
	    	export_Down(req,resp,className,Constant.TYPE_END_ME,termidName);
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
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
				sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
				sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
			}else{
				not_appraisal(sectionName);
			}
			startrow2=endrow+1;
			beginrow=endrow+1;
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
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,3,3));
				sheet.getRow(beginrow).getCell(3).setCellValue(appraisalName);
				sheet.getRow(beginrow).getCell(3).setCellStyle(cellStyle);
				beginrow=endrow+1;
			}else{
				createRow();
				HSSFRow row=sheet.getRow(endrow);
				CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,7);
				createRegionStyle(cellStyle,region);
				sheet.addMergedRegion(region);
				row.getCell(3).setCellValue(appraisalName);
				beginrow=endrow+1;
			}
		}
		/**
		 * 他人评价时调用
		 */
		private void region_other()
		{
			sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 2, 2));
			HSSFRow row=sheet.getRow(startrow2);
			row.getCell(2).setCellValue(Constant.TYPE_APPRAISAL_OTHER);
			row.getCell(2).setCellStyle(cellStyle);
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
		 * 记录袋时调用
		 * @param sectionCode  栏目号
		 * @param sectionName  栏目名称
		 */
		private void record_package(String studentName,String sectionCode,String sectionName)
		{
			list = appraiseMaps.get(sectionCode);
			if(list!=null)
			{
				int i=0;
				for(AppraiseBaseDto app : list)
				{
					edit_recordPackage(studentName,app,sectionName,++i);
				}
				if (Constant.WORKS_SUBJECT_APPRAISAL.equals(sectionCode)) {
					sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,3,3));
					sheet.getRow(beginrow).getCell(3).setCellValue(sectionName);
					sheet.getRow(beginrow).getCell(3).setCellStyle(cellStyle);
				}else{
				    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
				    sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
				}
				beginrow=endrow+1;
			}else{
				if(Constant.WORKS_SUBJECT_APPRAISAL.equals(sectionCode))
				{
					createRow();
					HSSFRow row=sheet.getRow(endrow);
					CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,7);
					createRegionStyle(cellStyle,region);
					sheet.addMergedRegion(region);
					row.getCell(3).setCellValue(sectionName);
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
		private void edit_recordPackage(String studentName,AppraiseBaseDto app,String sectionName,int i)
		{
			HSSFRow row1=createRow();
			CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,7);
			createRegionStyle(package_contentStyle, region);
			sheet.addMergedRegion(region);
			String name_True = sectionName+i;
			row1.getCell(4).setCellValue(name_True);
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
			int num = getExcelCellAutoHeight(replaceEsc(app.getPartInfo()), 39);
			row2.setHeightInPoints(num*20);
			row2.getCell(4).setCellValue("内容描述");
			row2.getCell(4).setCellStyle(packageStyle);
			CellRangeAddress region5 = new CellRangeAddress(endrow,endrow,5,7);
			createRegionStyle(package_contentStyle, region5);
			sheet.addMergedRegion(region5);
			row2.getCell(5).setCellValue(replaceEsc(app.getPartInfo()));
			if (!Constant.WORKS_SUBJECT_APPRAISAL.equals(app.getTwoPartId())) {
				attach_Activity(studentName,name_True,app);
			}
			HSSFRow row5=createRow();
			CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,4,7);
			createRegionStyle(otherStyle,region2);
			sheet.addMergedRegion(region2);
			row5.getCell(4).setCellValue(appraisalMessage(app));
		}
		/**
		 * 综合实践活动附件
		 */
		private void attach_Activity(String studentName,String SectionName,AppraiseBaseDto app)
		{
			List<AppraiseBaseDto> attachlist = app.getAttachmentDtos();
			if (attachlist!=null&&attachlist.size()>0) {
				int i = 0;
				for (AppraiseBaseDto attach : attachlist) {
					++i;
					HSSFRow row3 = createRow();
					CellRangeAddress region4 = new CellRangeAddress(endrow,
							endrow, 5, 7);
					createRegionStyle(package_contentStyle, region4);
					row3.getCell(4).setCellStyle(packageStyle);
					row3.getCell(4).setCellValue(Constant.attach+i);
					sheet.addMergedRegion(region4);
					row3.getCell(5).setCellValue(attach.getAttachmentName());
					generateAttach(studentName,SectionName,attach.getAttachmentName(),attach.getAttachmentPath());
				}
			}
		}
		/**
		 * 思想道德
		 */
		private void morality(String studentName)
		{
			mutiple_apprasial(Constant.MORALITY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
			Appraisal_other(Constant.MORALITY_CLASSMATES_APPRAISAL,Constant.MORALITY_TEACHER_APPRAISAL,Constant.MORALITY_PARENT_APPRAISAL);
			record_package(studentName,Constant.MORALITY_RECORD_BAG,Constant.TYPE_SXDD_BAG);
			region_First(Constant.TYPE_SXDD,studentName);
		}
		/**
		 * 按思想道德栏目导出
		 * @param req
		 * @param resp
		 */
		private void ExportByMorality(HttpServletRequest req, HttpServletResponse resp)
		{
			try {
				generateZip(req, resp, className, Constant.TYPE_SXDD,termidName);
				for (Map.Entry<String, Map<String, List<AppraiseBaseDto>>> entry : appraiseMaps1
						.entrySet()) {
					String studentName = entry.getKey().split("_")[0];
					appraiseMaps = entry.getValue();
					morality(studentName);
				}
				generateExcel(className, Constant.TYPE_SXDD,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByMorality(HttpServletRequest,HttpServletResponse)", e);
			}
		}
		/**
		 * 学业成就的他人评价
		 */
		private void academic_other(String studentName)
		{
			//list = appraiseMaps.get(Constant.WORKS_SUBJECT_APPRAISAL);   //课程评语
			//other_mutiple_appraisal(list,Constant.TYPE_KCPY);
			record_package(studentName, Constant.WORKS_SUBJECT_APPRAISAL, Constant.TYPE_KCPY);
			list = appraiseMaps.get(Constant.WORKS_CLASSMATES_APPRAISAL);  //同学评价
			other_mutiple_appraisal(list, Constant.APPRASER_STUDENT);
			list = appraiseMaps.get(Constant.WORKS_PARENT_APPRAISAL);    //家长评价
			other_mutiple_appraisal(list, Constant.APPRASER_PARENT);
			region_other();
		}
	    /**
		 * 学业成就
		 */
		private void academic_Record(String studentName)
		{
			mutiple_apprasial(Constant.WORKS_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
			academic_other(studentName);
			record_package(studentName,Constant.WORKS_SUBJECT_SHOW,Constant.TYPE_XYCJ_SHOW);
			region_First(Constant.TYPE_XYCJ,studentName);
		}
	    /**
	     * 按学业成就栏目导出
	     */
	    public void ExportByAcademic(HttpServletRequest req, HttpServletResponse resp)
	    {
	    	try {
				generateZip(req, resp, className, Constant.TYPE_XYCJ,termidName);
				for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
		    	{
		    		String studentName = entry.getKey().split("_")[0];
		    		appraiseMaps = entry.getValue();
		    		academic_Record(studentName);
		    	}
				generateExcel(className, Constant.TYPE_XYCJ,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByAcademic(HttpServletRequest,HttpServletResponse)", e);
			}
	    }
	    /**
	     * 合作与交流
	     */
	    private void cooperation_Exchange(String studentName)
	    {
	    	mutiple_apprasial(Constant.TYPE_HZ_ZWPJ,Constant.TYPE_ZWPJ);
	    	Appraisal_other(Constant.COOPERATION_CLASSMATES_APPRAISAL,Constant.COOPERATION_TEACHER_APPRAISAL,Constant.COOPERATION_PARENT_APPRAISAL);
	    	record_package(studentName,Constant.COOPERATION_RECORD_BAG, Constant.TYPE_HZYJL_BAG);
	    	region_First(Constant.TYPE_HZYJL,studentName);
	    }
	    /**
	     * 按合作与交流栏目导出
	     */
	    public void ExportByCoExchange(HttpServletRequest req, HttpServletResponse resp)
	    {
	    	try {
				generateZip(req, resp, className, Constant.TYPE_HZYJL,termidName);
				for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
		    	{
		    		String studentName = entry.getKey().split("_")[0];
		    		appraiseMaps = entry.getValue();
		    		cooperation_Exchange(studentName);
		    	}
				generateExcel(className, Constant.TYPE_HZYJL,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByCoExchange(HttpServletRequest,HttpServletResponse)", e);
			}
	    }
	    /**
		 * 运动与健康
		 */
		private void sports_Health(String studentName)
		{
			mutiple_apprasial(Constant.PLAY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
			Appraisal_other(Constant.PLAY_CLASSMATES_APPRAISAL,Constant.PLAY_TEACHER_APPRAISAL,Constant.PLAY_PARENT_APPRAISAL);
			physical_Health(levelcode);
			region_First(Constant.TYPE_YDYJK,studentName);
		}
		/**
	     * 按运动与健康栏目导出
	     */
	    public void ExportBySpHealth(HttpServletRequest req, HttpServletResponse resp)
	    {
	    	try {
				generateZip(req, resp, className, Constant.TYPE_YDYJK,termidName);
				for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
		    	{
		    		String studentName = entry.getKey().split("_")[0];
		    		appraiseMaps = entry.getValue();
		    		sports_Health(studentName);
		    	}
				generateExcel(className, Constant.TYPE_YDYJK,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportBySpHealth(HttpServletRequest,HttpServletResponse)", e);
			}
	    }
	    /**
		 * 审美与表现
		 */
		private void esthetic_Express(String studentName)
		{
			mutiple_apprasial(Constant.AESTHETIC_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
			Appraisal_other(Constant.AESTHETIC_CLASSMATES_APPRAISAL,Constant.AESTHETIC_TEACHER_APPRAISAL,Constant.AESTHETIC_PARENT_APPRAISAL);
			record_package(studentName,Constant.AESTHETIC_RECORD_BAG,Constant.TYPE_SMYBX_BAG);
			region_First(Constant.TYPE_SMYBX,studentName);
		}
		/**
	     * 按审美与表现栏目导出
	     */
	    public void ExportByEsthetic(HttpServletRequest req, HttpServletResponse resp)
	    {
	    	try {
				generateZip(req, resp, className, Constant.TYPE_SMYBX,termidName);
				for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
		    	{
		    		String studentName = entry.getKey().split("_")[0];
		    		appraiseMaps = entry.getValue();
		    		esthetic_Express(studentName);
		    	}
				generateExcel(className, Constant.TYPE_SMYBX,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByEsthetic(HttpServletRequest,HttpServletResponse)", e);
			}
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
			       CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,7);
			       createRegionStyle(package_contentStyle, region);
			       sheet.addMergedRegion(region);
			       String name_True = sectionName+i;
			       row.getCell(4).setCellValue(name_True);
			       package_base("主题",app.getTopic());
			       package_base("关键字",app.getKeyword());
			       package_base("合作人",app.getCooperationMan());
			       package_base("起止时间",app.getStartDate()+"至"+app.getEndDate());
			       package_base("内容摘要",app.getPartInfo());
			       HSSFRow row5=createRow();
				   CellRangeAddress region2 = new CellRangeAddress(endrow,endrow,4,7);
				   createRegionStyle(otherStyle,region2);
				   sheet.addMergedRegion(region2);
				   row5.getCell(4).setCellValue(appraisalMessage(app));
			    }
			    
			    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,3,3));
				sheet.getRow(beginrow).getCell(3).setCellValue(sectionName);
				beginrow=endrow+1;
			}else{
				HSSFRow row1 = createRow();
				row1.getCell(3).setCellValue("基本情况");
				CellRangeAddress region = new CellRangeAddress(endrow,
						endrow, 4, 7);
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
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,3,3));
				sheet.getRow(beginrow).getCell(3).setCellValue(sectionName);
				sheet.getRow(beginrow).getCell(3).setCellStyle(cellStyle);
			}else{
				HSSFRow row = createRow();
				CellRangeAddress region = new CellRangeAddress(endrow, endrow, 4, 7);
				createRegionStyle(cellStyle, region);
				sheet.addMergedRegion(region);
				row.getCell(3).setCellValue(sectionName);
				beginrow = endrow + 1;
			}
			beginrow=endrow+1;
		}
		/**
		 * 综合实践活动的附件调用
		 * @param sectionCode
		 * @param sectionName
		 */
		private void activity_attach(String sectionName,String studentName)
		{
			list = appraiseMaps.get(Constant.ACTIVITY_RESEARCH_RESULT);
			if (list != null) {
				for (AppraiseBaseDto appraise : list) {
					List<AppraiseBaseDto> attachlist = appraise.getAttachmentDtos();
					if (attachlist != null && attachlist.size() > 0) {
						int i = 0;
						for (AppraiseBaseDto app : attachlist) {
							++i;
							HSSFRow row1 = createRow();
							CellRangeAddress region = new CellRangeAddress(endrow,
									endrow, 5, 7);
							createRegionStyle(package_contentStyle, region);
							row1.getCell(4).setCellStyle(packageStyle);
							row1.getCell(4).setCellValue(Constant.attach+i);
							sheet.addMergedRegion(region);
							row1.getCell(5).setCellValue(app.getAttachmentName());
							generateAttach(studentName,sectionName,app.getAttachmentName(),app.getAttachmentPath());
						}
						HSSFRow row2 = createRow();
						CellRangeAddress region1 = new CellRangeAddress(endrow,
								endrow, 4, 7);
						createRegionStyle(otherStyle, region1);
						sheet.addMergedRegion(region1);
						row2.getCell(4).setCellValue(appraisalMessage(appraise));
						sheet.addMergedRegion(new CellRangeAddress(beginrow,
								endrow, 3, 3));
						sheet.getRow(beginrow).getCell(3).setCellValue("研究成果");
						sheet.getRow(beginrow).getCell(3).setCellStyle(cellStyle);
						beginrow = endrow + 1;

					}else{
						HSSFRow row1 = createRow();
						row1.getCell(3).setCellValue("研究成果");
						CellRangeAddress region = new CellRangeAddress(endrow,
								endrow, 4, 7);
						createRegionStyle(cellStyle, region);
						sheet.addMergedRegion(region);
						beginrow = endrow + 1;
					} 
				}
			}else {
				HSSFRow row1 = createRow();
				row1.getCell(3).setCellValue("研究成果");
				CellRangeAddress region = new CellRangeAddress(endrow,
						endrow, 4, 7);
				createRegionStyle(cellStyle, region);
				sheet.addMergedRegion(region);
				beginrow = endrow + 1;
			}
		}
		/**
		 * 社区服务与社会综合实践中的基本情况
		 */
		private void social_Service(String sectionName,String studentName)
		{
			list = appraiseMaps.get(Constant.ACTIVITY_BASEINFO_2);
			if(list!=null)
			{
				int i=0;
				for(AppraiseBaseDto app : list)
				{
					edit_recordPackage(studentName,app,sectionName,++i);
				}
				sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,3,3));
				sheet.getRow(beginrow).getCell(3).setCellValue(sectionName);
				beginrow=endrow+1;
			}else{
				createRow();
				HSSFRow row=sheet.getRow(endrow);
				CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,7);
				createRegionStyle(cellStyle,region);
				sheet.addMergedRegion(region);
				row.getCell(3).setCellValue(sectionName);
				beginrow=endrow+1;
			}
		}
		private void comprehensive_Practice1(String studentName)
		{
			startrow2=beginrow;
			study_Appraisal("基本情况");
			activity_attach("研究成果",studentName);
			activity_self(Constant.ACTIVITY_SELF_APPRAISAL_1,Constant.TYPE_ZWPJ);
			sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 2, 2));
			HSSFRow row=sheet.getRow(startrow2);
			row.getCell(2).setCellValue("研究性学习");
			row.getCell(2).setCellStyle(cellStyle);
		}
		private void comprehensive_Practice2(String studentName)
		{
			startrow2=beginrow;
			social_Service("基本情况",studentName);
			activity_self(Constant.ACTIVITY_SELF_APPRAISAL_2, Constant.TYPE_ZWPJ);
			sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 2, 2));
			HSSFRow row=sheet.getRow(startrow2);
			row.getCell(2).setCellValue("社区服务与社会实践");
			row.getCell(2).setCellStyle(cellStyle);
		}
		/**
		 * 综合实践活动
		 */
		private void comprehensive_Practice(String studentName)
		{
			comprehensive_Practice1(studentName);
			comprehensive_Practice2(studentName);
			region_First(Constant.TYPE_ZHSJ,studentName);
		}
		 /**
	     * 按综合实践活动栏目导出
	     */
	    public void ExportByPractice(HttpServletRequest req, HttpServletResponse resp)
	    {
	    	try {
				generateZip(req, resp, className, Constant.TYPE_ZHSJ,termidName);
				for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
		    	{
		    		String studentName = entry.getKey().split("_")[0];
		    		appraiseMaps = entry.getValue();
		    		comprehensive_Practice(studentName);
		    	}
				generateExcel(className, Constant.TYPE_ZHSJ,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByPractice(HttpServletRequest,HttpServletResponse)", e);
			}
	    }
	    /**
		 * 个性发展
		 */
		private void personality_Develop(String studentName)
		{
			mutiple_apprasial(Constant.INDIVIDUALITY_SELF_APPRAISAL,Constant.TYPE_ZWPJ);
			Appraisal_other(Constant.INDIVIDUALITY_CLASSMATES_APPRAISAL,Constant.INDIVIDUALITY_TEACHER_APPRASIAL,Constant.INDIVIDUALITY_PARENT_APPRAISAL);
			record_package(studentName,Constant.INDIVIDUALITY_RECORD_BAG,Constant.TYPE_TCYCG_SHOW);
			region_First(Constant.TYPE_GXYFZ,studentName);
		}
		 /**
		 * 按个性发展栏目导出
		 */
		public void ExportByPeDevelop(HttpServletRequest req,
				HttpServletResponse resp) {
			try {
				generateZip(req, resp, className, Constant.TYPE_GXYFZ,termidName);
				for (Map.Entry<String, Map<String, List<AppraiseBaseDto>>> entry : appraiseMaps1
						.entrySet()) {
					String studentName = entry.getKey().split("_")[0];
					appraiseMaps = entry.getValue();
					personality_Develop(studentName);
				}
				generateExcel(className, Constant.TYPE_GXYFZ,termidName);
				zos.close();
				os.close();
			} catch (IOException e) {
				logger.error("ExportByPeDevelop(HttpServletRequest,HttpServletResponse)", e);
			}
		}
}
