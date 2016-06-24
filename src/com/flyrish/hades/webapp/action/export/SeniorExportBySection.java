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
import com.flyrish.hades.dto.AttachFileDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;

public class SeniorExportBySection extends ExportSection{
	
	//班级名称
	public String className;
	//年纪名称
	public String gradeName;
	//班级号
	public String classId;
	//一级栏目号
	public String firstSectionCode;  
	//学期id
	public String termid;
	//学期名称
	public String termidName;
	String cmis30id;
	String discode;
	String levelcode;
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
						if(dto==null||NestUtil.isEmpty(dto.getAppraisaltypeid())) continue;
						//组装数据集合
						List<AppraiseBaseDto> dtos=null;
						dtos=appraiseMaps2.get(dto.getAppraisaltypeid());
						if(dtos==null){
							dtos=new ArrayList<AppraiseBaseDto>();
						    appraiseMaps2.put(dto.getAppraisaltypeid(),dtos);
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
    	if(Constant.BEGIN_OF_THE_NEW_TERM.equals(firstSectionCode))
		{
			ExportBytermBegin(req,resp);
		}else if(Constant.AT_THE_END_OF_THE_TERM.equals(firstSectionCode)){
			ExportBytermEnd(req, resp);
		}else if(Constant.IDEOLOGICAL_MORALITY.equals(firstSectionCode)){
			ExportByMorality(req,resp);
		}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(firstSectionCode)){
			ExportByAcademic(req, resp);
		}else if(Constant.COOPERATION_AND_EXCHANGE.equals(firstSectionCode)){
			ExportByCoExchange(req, resp);
		}else if(Constant.SPORTS_AND_HEALTH.equals(firstSectionCode)){
			ExportBySpHealth(req, resp);
		}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(firstSectionCode)){
			ExportByEsthetic(req, resp);
		}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(firstSectionCode)){
			ExportByPractice(req, resp);
		}else if(Constant.PERSONALITY_DEVELOPMENT.equals(firstSectionCode)){
			ExportByPeDevelop(req, resp);
		}
    	return null;
    }
    
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
	 * 学期开始的我
	 */
	private void termBegin(String studentName) {
		single_apprasial(Constant.TYPE_START_ZWPJ, Constant.TYPE_ZWPJ);
		list = appraiseMaps.get(Constant.TYPE_START_WDFZMB);
		if (list != null) {
			AppraiseBaseDto app_me = null; // 我的发展目标
			AppraiseBaseDto app_parent = null; // 家长的期望
			for (AppraiseBaseDto app : list) {
				if (app.getAppraseridentifynum().equals("1")) {
					app_me = app;
				} else {
					app_parent = app;
				}
			}
			if (app_me != null) {
				termBegin_end_appraisal(app_me, Constant.TYPE_DEVELOP_TATGET);
			} else {
				not_appraisal(Constant.TYPE_DEVELOP_TATGET);
			}
			if (app_parent != null) {
				termBegin_end_appraisal(app_parent, Constant.TYPE_BEGIN_EXPECT);
			} else {
				not_appraisal(Constant.TYPE_BEGIN_EXPECT);
			}
			list.clear();
		} else {
			not_appraisal(Constant.TYPE_DEVELOP_TATGET);
			not_appraisal(Constant.TYPE_BEGIN_EXPECT);
		}
		region_First(Constant.TYPT_NEW_ME, studentName);
	}
	
	/**
	 * 当评价内容为评价内容、签名信息时调用(不是记录袋)
	 * 
	 * @param app
	 *            评价信息
	 */
	private void createRowCell(AppraiseBaseDto app) {
		HSSFRow row1 = createRow();
		CellRangeAddress region1 = new CellRangeAddress(endrow, endrow, 4, 7);
		createRegionStyle(contentStyle, region1);
		sheet.addMergedRegion(region1);
		int num = getExcelCellAutoHeight(replaceEsc(app.getApprasial()), 50);
		row1.setHeightInPoints(num*20);
		row1.getCell(4).setCellValue(replaceEsc(app.getApprasial()));
		HSSFRow row2 = createRow();
		CellRangeAddress region2 = new CellRangeAddress(endrow, endrow, 4, 7);
		createRegionStyle(otherStyle, region2);
		sheet.addMergedRegion(region2);
		row2.getCell(4).setCellValue(getMessage(app));
	}
	
	/**
	 * 评论为多条时调用方法(记录袋、二级栏目细分的除外)
	 * 
	 * @param sectionCode
	 *            栏目号
	 * @param sectionName
	 *            栏目名称
	 */
	private void mutiple_apprasial(String sectionCode, String sectionName) {
		list = appraiseMaps.get(sectionCode);
		if (list != null) {
			for (AppraiseBaseDto app : list) {
				createRowCell(app);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow, endrow, 2, 3));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			sheet.getRow(beginrow).getCell(2).setCellStyle(cellStyle);
		} else {
			not_appraisal(sectionName);
		}
		startrow2 = endrow + 1;
		beginrow = endrow + 1;
	}
	/**
	 * 新学期的我和学期结束的我有内容时
	 * 
	 * @param app
	 *            评价信息
	 * @param sectionName
	 *            栏目名称
	 */
	private void termBegin_end_appraisal(AppraiseBaseDto app, String sectionName) {
		createRowCell(app);
		sheet.addMergedRegion(new CellRangeAddress(beginrow, endrow, 2, 3));
		HSSFRow row1 = sheet.getRow(beginrow);
		row1.getCell(2).setCellValue(sectionName);
		beginrow = endrow + 1;
	}

	/**
	 * 评论单条时调用的方法
	 * 
	 * @param sectionCode
	 *            栏目号
	 * @param sectionName
	 *            栏目名字
	 */
	private void single_apprasial(String sectionCode, String sectionName) {
		list = appraiseMaps.get(sectionCode);
		if (list != null) {
			for (AppraiseBaseDto app : list) {
				termBegin_end_appraisal(app, sectionName);
			}
			list.clear();
		} else {
			not_appraisal(sectionName);
		}
	}
	
	/**
	 * 学期结束时的我
	 */
	private void termEnd(String studentName) {
		single_apprasial(Constant.TYPE_END_ZWPJ, Constant.TYPE_ZWPJ);
		single_apprasial(Constant.TYPE_END_WDFZMB, Constant.TYPE_DEVELOP_TATGET);
		single_apprasial(Constant.TYPE_END_BZRPY, Constant.TYPE_BZRPY);
		single_apprasial(Constant.TYPE_END_JZPYQW, Constant.TYPE_END_EXPECT);
		region_First(Constant.TYPE_END_ME, studentName);
	}
	

	/**
	 * 他人评价中的同学评价、老师评价、家长评价
	 * 
	 * @param list
	 *            同学评价、老师评价、家长评价集合
	 * @param appraisalName
	 *            同学评价、老师评价、家长评价
	 */
	private void other_mutiple_appraisal(List<AppraiseBaseDto> list,
			String appraisalName) {
		if (list.size() > 0) {
			for (AppraiseBaseDto app : list) {
				createRowCell(app);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow, endrow, 3, 3));
			sheet.getRow(beginrow).getCell(3).setCellValue(appraisalName);
			beginrow = endrow + 1;
		} else {
			HSSFRow row = createRow();
			CellRangeAddress region = new CellRangeAddress(endrow, endrow, 4, 7);
			createRegionStyle(cellStyle, region);
			sheet.addMergedRegion(region);
			row.getCell(3).setCellValue(appraisalName);
			beginrow = endrow + 1;
		}
	}
	
	/**
	 * 他人评价(同学,教师,家长)
	 * 
	 * @param sectionCode
	 *            栏目号
	 * @param sectionName
	 *            栏目名称
	 */
	private void createRowCell_other(String sectionCode, String sectionName) {
		list = appraiseMaps.get(sectionCode);
		List<AppraiseBaseDto> classmate_app = new ArrayList<AppraiseBaseDto>();
		List<AppraiseBaseDto> teacher_app = new ArrayList<AppraiseBaseDto>();
		List<AppraiseBaseDto> parent_app = new ArrayList<AppraiseBaseDto>();
		if (list != null) {
			for (AppraiseBaseDto app : list) {
				if (app.getAppraseridentifynum().equals("2")) // 同学评价
				{
					classmate_app.add(app);
				} else if (app.getAppraseridentifynum().equals("3")
						|| app.getAppraseridentifynum().equals("4")) // 老师评价
				{
					teacher_app.add(app);
				} else { // 家长评价
					parent_app.add(app);
				}
			}
			other_mutiple_appraisal(classmate_app, Constant.APPRASER_STUDENT);
			other_mutiple_appraisal(teacher_app, Constant.APPRASER_TEACHER);
			other_mutiple_appraisal(parent_app, Constant.APPRASER_PARENT);

		} else {
			other_mutiple_appraisal(classmate_app, Constant.APPRASER_STUDENT);
			other_mutiple_appraisal(teacher_app, Constant.APPRASER_TEACHER);
			other_mutiple_appraisal(parent_app, Constant.APPRASER_PARENT);
		}
		sheet.addMergedRegion(new CellRangeAddress(startrow2, endrow, 2, 2));
		HSSFRow row = sheet.getRow(startrow2);
		row.getCell(2).setCellValue(Constant.TYPE_APPRAISAL_OTHER);
		row.getCell(2).setCellStyle(cellStyle);
	}
	
	/**
	 * 记录袋时调用
	 * 
	 * @param sectionCode
	 *            栏目号
	 * @param sectionName
	 *            栏目名称
	 */
	private void record_package(String studentName,String sectionCode, String sectionName) {
		list = appraiseMaps.get(sectionCode);
		if (list != null) {
			int i = 0;
			for (AppraiseBaseDto app : list) {
				edit_recordPackage(studentName,app, sectionName, ++i);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow, endrow, 2, 3));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			beginrow = endrow + 1;
		} else {
			not_appraisal(sectionName);
		}
	}

	/**
	 * 创建记录袋
	 * 
	 * @param app
	 *            记录袋信息
	 * @param sectionName
	 *            栏目名称
	 * @param i
	 *            栏目名称后面的序号
	 */
	private void edit_recordPackage(String studentName,AppraiseBaseDto app, String sectionName,
			int i) {
		HSSFRow row1 = createRow();
		CellRangeAddress region = new CellRangeAddress(endrow, endrow, 4, 7);
		createRegionStyle(package_contentStyle, region);
		sheet.addMergedRegion(region);
		String name_True = sectionName + i;
		row1.getCell(4).setCellValue(name_True);
		if (app.getAppraisaltypeid().equals(Constant.TYPE_XY_GCJL)
				|| app.getAppraisaltypeid().equals(
						Constant.TYPE_KE_CHENG_PINGYU)) {
			HSSFRow row = createRow();
			row.getCell(4).setCellValue("学科");
			row.getCell(4).setCellStyle(packageStyle);
			CellRangeAddress region3 = new CellRangeAddress(endrow, endrow, 5,
					7);
			createRegionStyle(package_contentStyle, region3);
			sheet.addMergedRegion(region3);
			row.getCell(5).setCellValue(app.getItem1());
		}
		HSSFRow row2 = createRow();
		int num = getExcelCellAutoHeight(app.getAppraisal(), 50);
		row2.setHeightInPoints(num*20);
		row2.getCell(4).setCellValue("内容描述");
		row2.getCell(4).setCellStyle(packageStyle);
		CellRangeAddress region5 = new CellRangeAddress(endrow, endrow, 5, 7);
		createRegionStyle(package_contentStyle, region5);
		sheet.addMergedRegion(region5);
		row2.getCell(5).setCellValue(replaceEsc(app.getAppraisal()));
		if (!app.getAppraisaltypeid().equals(Constant.TYPE_KE_CHENG_PINGYU)) {
			attach_Activity(studentName,name_True,app);
		}
		HSSFRow row5 = createRow();
		CellRangeAddress region2 = new CellRangeAddress(endrow, endrow, 4, 7);
		createRegionStyle(otherStyle, region2);
		sheet.addMergedRegion(region2);
		row5.getCell(4).setCellValue(getMessage(app));
	}
 
	/**
	 * 学业成就课程评语
	 * @param sectionCode  栏目号
	 * @param sectionName  栏目名称
	 */
	private void suject_Appraise(String studentName,String sectionCode,String sectionName)
	{
		list = appraiseMaps.get(Constant.TYPE_KE_CHENG_PINGYU);
		if(list!=null)
		{
			int i = 0;
			for(AppraiseBaseDto app : list)
			{
				edit_recordPackage(studentName,app,sectionName,++i);
			}
			sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	
	/**
	 * 个性发展
	 */
	private void personality_Develop(String studentName) {
		develop_Record();
		mutiple_apprasial(Constant.TYPE_GXFZ_ZWPJ, Constant.TYPE_ZWPJ);
		createRowCell_other(Constant.TYPE_GXFZ_TRPJ, Constant.TYPE_APPRAISAL_OTHER);
		record_package(studentName,Constant.TYPE_GXFZGC, Constant.TYPE_PROCESS);
		record_package(studentName,Constant.TYPE_GXFZ_CGZS, Constant.TYPE_TCYCG_SHOW);
		region_First(Constant.TYPE_GXYFZ, studentName);
	}
	private boolean activity_Self(List<AppraiseBaseDto> practicesSelfAppraiseDtos)
	{
		if (practicesSelfAppraiseDtos!=null&&practicesSelfAppraiseDtos.size()>0) {
			int number_Self = practicesSelfAppraiseDtos.size();
			for (AppraiseBaseDto app : practicesSelfAppraiseDtos) {
				HSSFRow row1 = createRow();
				int num = getExcelCellAutoHeight(app.getMyselfapprasercontent(), 39);
				row1.setHeightInPoints(num*20);
				CellRangeAddress region = new CellRangeAddress(endrow, endrow,5, 7);
				createRegionStyle(package_contentStyle, region);
				sheet.addMergedRegion(region);
				row1.getCell(5).setCellValue(replaceEsc(app.getMyselfapprasercontent()));
			}
			CellRangeAddress region2 = new CellRangeAddress(endrow-number_Self + 1, endrow, 4, 4);
			createRegionStyle(packageStyle, region2);
			sheet.addMergedRegion(region2);
			sheet.getRow(endrow-number_Self+1).getCell(4).setCellValue(Constant.TYPE_ZWPJ);
			HSSFRow row2 = createRow();
			CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,5, 7);
			createRegionStyle(otherStyle, region1);
			sheet.addMergedRegion(region1);
			row2.getCell(5).setCellValue(getMessage(practicesSelfAppraiseDtos.get(0)));
			return false;
		}
		return true;
	}
	
	/**
	 * 研究性学习
	 * @param SectionCode  研究性学习栏目号
	 * @param sectionName  研究性学习
	 */
	private void study_Content(String studentName,String SectionCode,String sectionName)
	{
		list = appraiseMaps.get(SectionCode);
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
		       package_base("题目",app.getItem1());
		       package_base("合伙人",app.getItem2());
		       package_base("总学时数",app.getItem3());
		       package_base("实施路径",app.getItem4());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(studentName,name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
					CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,5, 7);
					createRegionStyle(otherStyle, region1);
					sheet.addMergedRegion(region1);
					row2.getCell(5).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	
	/**
	 * 综合实践活动附件
	 */
	private void attach_Activity(String studentName,String SectionName,AppraiseBaseDto app)
	{
		List<AttachFileDto> attachlist = app.getAttachFileDtos();
		if (attachlist!=null&&attachlist.size()>0) {
			int i = 0;
			for (AttachFileDto attach : attachlist) {
				++i;
				HSSFRow row3 = createRow();
				CellRangeAddress region4 = new CellRangeAddress(endrow,
						endrow, 5, 7);
				createRegionStyle(package_contentStyle, region4);
				row3.getCell(4).setCellStyle(packageStyle);
				row3.getCell(4).setCellValue(Constant.attach+i);
				sheet.addMergedRegion(region4);
				row3.getCell(5).setCellValue(attach.getFilename());
				generateAttach(studentName,SectionName,attach.getFilename(),attach.getAttachpath());
			}
		}
	}
	/**
	 * 社区服务
	 */
	private void community_Service(String studentName,String SectionCode,String sectionName)
	{
		list = appraiseMaps.get(SectionCode);
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
		       package_base("选择次数",app.getItem1());
		       package_base("社区服务名称",app.getItem2());
		       package_base("社区服务电话",app.getItem3());
		       package_base("服务时数",app.getItem4());
		       package_base("服务时间",app.getItem5());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(studentName,name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
					CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,5, 7);
					createRegionStyle(otherStyle, region1);
					sheet.addMergedRegion(region1);
					row2.getCell(5).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
			sheet.getRow(beginrow).getCell(1).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
	/**
	 * 社会实践
	 */
	private void social_Practice(String studentName,String Sectioncode,String sectionName)
	{
		list = appraiseMaps.get(Sectioncode);
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
		       package_base("选择活动种",app.getItem1());
		       package_base("成果形成",app.getItem2());
		       package_base("地点",app.getItem4());
		       package_base("完成学时",app.getItem5());
		       package_base("内容摘要",app.getApprasial());
		       attach_Activity(studentName,name_True,app);
		       List<AppraiseBaseDto> practicesSelfAppraiseDtos = app.getPracticesSelfAppraiseDtos(); 
		       if(activity_Self(practicesSelfAppraiseDtos))
		       {
		    	   HSSFRow row2 = createRow();
					CellRangeAddress region1 = new CellRangeAddress(endrow, endrow,5, 7);
					createRegionStyle(otherStyle, region1);
					sheet.addMergedRegion(region1);
					row2.getCell(5).setCellValue(getMessage(list.get(0)));
		       }
		    }
		    sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,2,3));
			sheet.getRow(beginrow).getCell(2).setCellValue(sectionName);
			beginrow=endrow+1;
		}else{
			not_appraisal(sectionName);
		}
	}
    /**
     * 按学期结束时的我导出
     */
    public void ExportBytermEnd(HttpServletRequest req, HttpServletResponse resp)
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
	 * 思想道德
	 */
	private void morality(String studentName)
	{
		mutiple_apprasial(Constant.TYPE_SX_ZWPJ,Constant.TYPE_ZWPJ);
		createRowCell_other(Constant.TYPE_SX_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
		record_package(studentName,Constant.TYPE_SXJLD,Constant.TYPE_SXDD_BAG);
		region_First(Constant.TYPE_SXDD,studentName);
	}
    /**
     * 按思想道德栏目导出
     */
	public void ExportByMorality(HttpServletRequest req,
			HttpServletResponse resp) {
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
	 * 学业成就
	 */
    private void academic_Record(String studentName)
    {
    	record_package(studentName,Constant.TYPE_XY_GCJL,Constant.TYPE_XYCJ_SHOW);
    	suject_Appraise(studentName,Constant.TYPE_KE_CHENG_PINGYU, Constant.TYPE_KCPY);
    	mutiple_apprasial(Constant.TYPE_XY_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_XY,Constant.TYPE_APPRAISAL_OTHER);
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
    	createRowCell_other(Constant.TYPE_HZ_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	record_package(studentName,Constant.TYPE_HZ_JLD,Constant.TYPE_HZYJL_BAG);
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
	private void sports_Health(String studentName) {
		mutiple_apprasial(Constant.TYPE_YDJK_ZWPJ, Constant.TYPE_ZWPJ);
		createRowCell_other(Constant.TYPE_YDJK_TRPJ, Constant.TYPE_APPRAISAL_OTHER);
		physical_Health(levelcode);
		region_First(Constant.TYPE_YDYJK, studentName);
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
    	mutiple_apprasial(Constant.TYPE_SMYBX_ZWPJ,Constant.TYPE_ZWPJ);
    	createRowCell_other(Constant.TYPE_SMYBX_TRPJ,Constant.TYPE_APPRAISAL_OTHER);
    	record_package(studentName,Constant.TYPE_SMYBX_JLD,Constant.TYPE_SMYBX_BAG);
    	region_First(Constant.TYPE_SMYBX,studentName);
    }
    /**
     * 按审美与表现栏目导出
     */
    public void ExportByEsthetic(HttpServletRequest req, HttpServletResponse resp)
    {
    	try {
			generateZip(req, resp, className, Constant.TYPE_YDYJK,termidName);
			for(Map.Entry<String, Map<String,List<AppraiseBaseDto>>> entry : appraiseMaps1.entrySet())
	    	{
	    		String studentName = entry.getKey().split("_")[0];
	    		appraiseMaps = entry.getValue();
	    		esthetic_Express(studentName);
	    	}
			generateExcel(className, Constant.TYPE_YDYJK,termidName);
			zos.close();
			os.close();
		} catch (IOException e) {
			logger.error("ExportByEsthetic(HttpServletRequest,HttpServletResponse)", e);
		}
    }
    /**
	 * 综合实践活动
	 */
	private void comprehensive_Practice(String studentName)
	{
		study_Content(studentName,Constant.TYPE_YJXX,Constant.TYPE_STUDY);
		community_Service(studentName,Constant.TYPE_SQFU,Constant.TYPE_COMMIUNITY);
		social_Practice(studentName,Constant.TYPE_SHSJHD,Constant.TYPE_PRACTICE);
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
