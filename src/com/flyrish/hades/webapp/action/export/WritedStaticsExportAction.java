package com.flyrish.hades.webapp.action.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;

public class WritedStaticsExportAction extends TemplateExport{

	@Spring
	private IAppraisalStaticsExt appraisalStaticsExt;
	@Spring
	private IAppraisalWritedStaticsExt appraisalWritedStaticsExt;
	public List<String> JBs;
	public List<String>termInfos;
	private String levelCode;
	private String campuseId;
	public String years;
	public String termId;
	public String termName;
	public List<AppraisalWritedStaticseDto> appraiseStatics;
	@Before
	private void initBaseData(HttpServletRequest request){
		this.getLoginInfo(request);
		levelCode = userDto.getLevelcode();
		campuseId = userDto.getCampuseId();
	}
	@DefaultAction
	public void exportExcel(HttpServletRequest req,HttpServletResponse resp){
		try {
			redisServiceExt.save(dStatus, "0");
			//查询数据咯
			this.queryDatas();
			//开始导出数据
			this.exportDatas(req,resp);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("exportExcel(HttpServletRequest,HttpServletResponse)", e);
		}
	}
	//导出数据代码
	private String filepath;
	private Integer rowNum;
	private Integer cellNum;
	private void exportDatas(HttpServletRequest req, HttpServletResponse resp) {
		if(null==appraiseStatics||appraiseStatics.size()==0)return;
		String tittle = years+"届"+termName+"评语填写统计";
		String fileName = years+"届"+termName+"评语填写统计.xls";
		cellNum = 16;
		filepath = "writedJWStatics03";
		InputExcel(req, filepath);
		sheet.getRow(0).getCell(0).setCellValue(tittle);
    	sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
		int rowStart =3;
		for(AppraisalWritedStaticseDto appraisal : appraiseStatics)
		{
			HSSFRow row = CreateCell(rowStart++, cellNum);
			row.getCell(0).setCellValue(appraisal.getClassInfo());
			row.getCell(1).setCellValue(appraisal.getTotalCount());
			row.getCell(2).setCellValue(appraisal.getBzrCount());
			row.getCell(3).setCellValue(appraisal.getTotalCount()-appraisal.getBzrCount());
			row.getCell(4).setCellValue(appraisal.getSxddCount());
			row.getCell(5).setCellValue(appraisal.getTotalCount()-appraisal.getSxddCount());
			row.getCell(6).setCellValue(appraisal.getKcpyCount());
			row.getCell(7).setCellValue(appraisal.getTotalCount()-appraisal.getKcpyCount());
			row.getCell(8).setCellValue(appraisal.getHzyjlCount());
			row.getCell(9).setCellValue(appraisal.getTotalCount()-appraisal.getHzyjlCount());
			row.getCell(10).setCellValue(appraisal.getYsyjkCount());
			row.getCell(11).setCellValue(appraisal.getTotalCount()-appraisal.getYsyjkCount());
			row.getCell(12).setCellValue(appraisal.getSmybxCount());
			row.getCell(13).setCellValue(appraisal.getTotalCount()-appraisal.getSmybxCount());
			row.getCell(14).setCellValue(appraisal.getGxfzCount());
			row.getCell(15).setCellValue(appraisal.getTotalCount()-appraisal.getGxfzCount());
			
		}
		
		OutputExcel(resp, fileName);
		
	}
	private void queryDatas() {
		if(NestUtil.isNotEmpty(userDto.getTermId())){
			if("2012002".equals(userDto.getLevelcode())){
				//初中数据
				String gradeNum = String.valueOf(9-(Integer.parseInt(years)-Integer.parseInt(userDto.getTermId().substring(0,4))-1));
				List<String>cmis30Ids = new ArrayList<String>();
				cmis30Ids.add(userDto.getCmis30id());
				appraiseStatics = appraisalWritedStaticsExt.queryAppraiseStatics(cmis30Ids, userDto.getLevelid(), gradeNum, termId, years,userDto.getUserRealType(),userDto.getCampuseId());
			}else{
				//高中数据
				String gradeNum = String.valueOf(3-(Integer.parseInt(years)-Integer.parseInt(userDto.getTermId().substring(0,4))-1));
				List<String>cmis30Ids = new ArrayList<String>();
				cmis30Ids.add(userDto.getCmis30id());
				appraiseStatics = appraisalWritedStaticsExt.queryAppraiseStatics(cmis30Ids, userDto.getLevelid(), gradeNum, termId, years,userDto.getUserRealType(),userDto.getCampuseId());
			}
		}
		this.dataSort(appraiseStatics);
	}
	private void dataSort(List<AppraisalWritedStaticseDto> list) {
		  Collections.sort(list, new Comparator<AppraisalWritedStaticseDto>(){  
			  
	            public int compare(AppraisalWritedStaticseDto o1, AppraisalWritedStaticseDto o2) {  
	            	Integer o1ClassNum = this.getClassNum(o1);
	            	Integer o2ClassNum = this.getClassNum(o2);
	                //按照学生的年龄进行升序排列  
	                if(o1ClassNum > o2ClassNum){  
	                    return 1;  
	                }  
	                if(o1ClassNum == o2ClassNum){  
	                    return 0;  
	                }  
	                return -1;  
	            }

				private Integer getClassNum(AppraisalWritedStaticseDto o1) {
					if(null==o1)return 0;
					String classInfo = o1.getClassNum();
					if(NestUtil.isNotEmpty(classInfo)){
//						String[] split = classInfo.split("-");
//						if(null!=split&&split.length==3){
							return Integer.parseInt(classInfo);
//						}
					}
					return 0;
				}  
	        }); 		
	}
}	
