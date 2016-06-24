package com.flyrish.hades.webapp.action.score;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.ViewDto;
import com.flyrish.hades.webapp.action.BaseAction;

public class ExportScore extends BaseAction {
	public HSSFWorkbook workbook = null;
	
	public HSSFSheet sheet=null;
	
	public  HSSFFont font1=null;
	
	public HSSFCellStyle cellStyle = null; 
	HSSFFont font =null;
	public void init(HttpServletRequest req, HttpServletResponse resp,List<ModelScoreDto>  modelScores,ViewDto vd,String schoolyearValue,String segmentValue,String subjectValue,String courseValue,String gradeName1,String className1,String exportType,String studentName){
		workbook = new HSSFWorkbook();
	    sheet = workbook.createSheet("成绩表"); 
	    
	    sheet.setDefaultRowHeightInPoints(20);  //单元格高度 
	    font = workbook.createFont();
	    columnWidth();                     // 列宽设置
	    
	    
	    title(vd,schoolyearValue,segmentValue,subjectValue,courseValue,gradeName1,className1,exportType);
	    columnName(exportType);
	    content(modelScores,exportType);
	    export_Down(req,resp,className1,courseValue,subjectValue,schoolyearValue,segmentValue,exportType,studentName);
	}
	
	
	
	public void style(HSSFCell cell1){
		   HSSFCellStyle cellStyle1 = workbook.createCellStyle();
	        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
			
			font.setFontHeightInPoints((short) 12);
			font.setFontName("宋体");
			cellStyle1.setFont(font);
			
			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			    
			cell1.setCellStyle(cellStyle1);
	}
	
	//标题
	public void title(ViewDto vd,String schoolyearValue,String segmentValue,String subjectValue,String courseValue,String gradeName1,String className1,String exportType){
		
		sheet.addMergedRegion(new Region(0,(short)0,0,(short)16));   //合并单元格
		
	    HSSFRow row = sheet.createRow(0);   //在索引0的位置创建行
	    HSSFCell cell = row.createCell(0);   //在索引0的位置创建单元格（左上端）　
	    cell.setCellType(HSSFCell.CELL_TYPE_STRING);   //定义单元格为字符串类型，这个字符串类型也可在创建单元格里面设置。
	    
	    HSSFCellStyle cellStyle1 = workbook.createCellStyle();
      
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle1.setFont(font);
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		
        
        if(Constant.USER_KIND_SCHOOLTEACHER.equals(exportType)){
        	
	    	sheet.addMergedRegion(new Region(1,(short)0,1,(short)16));   //合并单元格	
        	
        	 HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        	 HSSFRow row1 = sheet.createRow(1);   //在索引0的位置创建行
     	    HSSFCell cell1 = row1.createCell(0);   //在索引0的位置创建单元格（左上端）　
     	    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);   //定义单元格为字符串类型，这个字符串类型也可在创建单元格里面设置。	
     	    cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
	   		HSSFFont font1 = workbook.createFont();
	   		font1.setFontHeightInPoints((short) 12);
	   		font1.setFontName("宋体");
	   		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	   		cellStyle2.setFont(font1);
	 		
        	cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            cell1.setCellStyle(cellStyle2);
            if(null==vd.getTeacherName()){
            	vd.setTeacherName("");
            }
        	 cell1.setCellValue("任课老师:"+vd.getTeacherName()
     	    		+"  学时:"+vd.getXs()+"  学分:"+vd.getXf()); //在单元格中输入一些内容　
	   		
	   		
	    		
        	cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cell.setCellStyle(cellStyle1);
        	 cell.setCellValue(schoolyearValue+"学年 "+segmentValue+" "+gradeName1+" "+className1+" "+subjectValue+" "+courseValue); //在单元格中输入一些内容　
        }else if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
        	cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        	  cell.setCellStyle(cellStyle1);
        	 cell.setCellValue(schoolyearValue+"学年 "+segmentValue+"成绩表"); //在单元格中输入一些内容　
        	
        }
	   
	    
	}
	
	
	//列名
	public void columnName(String exportType){
	    
		HSSFRow row = null;
	    if(Constant.USER_KIND_SCHOOLTEACHER.equals(exportType)){
	    	 row = sheet.createRow(2);  
	    	HSSFCell cell1 = row.createCell(0);  
	 	    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);  
	 	    cell1.setCellValue("教育ID");
	 	    style(cell1);
	 	    HSSFCell cell2 = row.createCell(1);  
	 	    cell2.setCellType(HSSFCell.CELL_TYPE_STRING);  
	 	    cell2.setCellValue("姓名");
	 	    style(cell2);
        }else if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
        	 row = sheet.createRow(1);  
        	HSSFCell cell1 = row.createCell(0);  
	 	    cell1.setCellType(HSSFCell.CELL_TYPE_STRING);  
	 	    cell1.setCellValue("学科");
	 	    style(cell1);
	 	    HSSFCell cell2 = row.createCell(1);  
	 	    cell2.setCellType(HSSFCell.CELL_TYPE_STRING);  
	 	    cell2.setCellValue("模块");
	 	    style(cell2);
        }
	    
	    
	    HSSFCell cell3 = row.createCell(2);  
	    cell3.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell3.setCellValue("平时成绩1");
	    style(cell3);
	    
	    HSSFCell cell4 = row.createCell(3);  
	    cell4.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell4.setCellValue("平时成绩2");
	    style(cell4);
	    
	    HSSFCell cell5 = row.createCell(4);  
	    cell5.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell5.setCellValue("平时成绩3");
	    style(cell5);
	    
	    HSSFCell cell6 = row.createCell(5);  
	    cell6.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell6.setCellValue("平时成绩4");
	    style(cell6);
	    
	    HSSFCell cell7 = row.createCell(6);  
	    cell7.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell7.setCellValue("平时成绩5");
	    style(cell7);
	    
	    HSSFCell cell8 = row.createCell(7);  
	    cell8.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell8.setCellValue("平时评定");
	    style(cell8);
	    
	    HSSFCell cell9 = row.createCell(8);  
	    cell9.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell9.setCellValue("平时表现");
	    style(cell9);
	    
	    HSSFCell cell10 = row.createCell(9);  
	    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell10.setCellValue("出勤率");
	    style(cell10);
	    
	    HSSFCell cell11 = row.createCell(10);  
	    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell11.setCellValue("缺勤学时");
	    style(cell11);
	    
	    HSSFCell cell12 = row.createCell(11);  
	    cell12.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell12.setCellValue("考试成绩");
	    style(cell12);
	    
	    HSSFCell cell13 = row.createCell(12);  
	    cell13.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell13.setCellValue("补考");
	    style(cell13);
	    
	    HSSFCell cell14 = row.createCell(13);  
	    cell14.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell14.setCellValue("学分");
	    style(cell14);
	    
	    HSSFCell cell15 = row.createCell(14);  
	    cell15.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell15.setCellValue("免修");
	    style(cell15);
	    
	    HSSFCell cell16 = row.createCell(15);  
	    cell16.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell16.setCellValue("学分不通过");
	    style(cell16);
	    
	    
	    HSSFCell cell17 = row.createCell(16);  
	    cell17.setCellType(HSSFCell.CELL_TYPE_STRING);  
	    cell17.setCellValue("会考成绩");
	    style(cell17);
	    
	    
	}
	
	
	public void content(List<ModelScoreDto>  modelScores,String exportType){
		
		String maxName = "";
		String maxCourseName="";
		
		for(int i=0;i<modelScores.size();i++){
			 ModelScoreDto dto = modelScores.get(i);
			if(i==0){
				maxName =dto.getStudentName()  ;
				maxCourseName = dto.getCourseName();
			}else{
			
				 if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
						if(maxCourseName.length()<dto.getCourseName().length()){
							maxCourseName=dto.getCourseName();
						}
				 }else{
						if(maxName.length()<dto.getStudentName().length()){
							maxName = dto.getStudentName();
							
						}
				 }
			
			}
			
			HSSFRow row = null; 


			 if(Constant.USER_KIND_SCHOOLTEACHER.equals(exportType)){
				 row = sheet.createRow(3+i);
				 
				  HSSFCell cell1 = row.createCell(0);           //教育id
				  cell1.setCellType(HSSFCell.CELL_TYPE_STRING);  
				  cell1.setCellValue(dto.getEdu_id());
				  style(cell1);
				 
				  
				  HSSFCell cell2 = row.createCell(1);           //姓名
				  cell2.setCellType(HSSFCell.CELL_TYPE_STRING);  
				  cell2.setCellValue(dto.getStudentName());
				  style(cell2);
				 
		        }else if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
		        	row = sheet.createRow(2+i);
		        	  HSSFCell cell1 = row.createCell(0);           //学科
					  cell1.setCellType(HSSFCell.CELL_TYPE_STRING);  
					  cell1.setCellValue(dto.getSubjectName());
					  style(cell1);
					 
					  HSSFCell cell2 = row.createCell(1);           //模块
					  cell2.setCellType(HSSFCell.CELL_TYPE_STRING);  
					  cell2.setCellValue(dto.getCourseName());
					  style(cell2);
		        }
			
			
			  
			  
			  HSSFCell cell3 = row.createCell(2);          //平时成绩1
			    cell3.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell3.setCellValue(dto.getPeacetime1());
			    style(cell3);

			    HSSFCell cell4 = row.createCell(3);        //平时成绩2
			    cell4.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell4.setCellValue(dto.getPeacetime2());
			    style(cell4);
			    
			    HSSFCell cell5 = row.createCell(4);        //平时成绩3
			    cell5.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell5.setCellValue(dto.getPeacetime3());
			    style(cell5);
			    
			    HSSFCell cell6 = row.createCell(5);        //平时成绩4
			    cell6.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell6.setCellValue(dto.getPeacetime4());
			    style(cell6);
			    
			    HSSFCell cell7 = row.createCell(6);        //平时成绩5
			    cell7.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell7.setCellValue(dto.getPeacetime5());
			    style(cell7);  
			    
			    HSSFCell cell8 = row.createCell(7);       //平时评定
			    cell8.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell8.setCellValue(dto.getDaily_behave());
			    style(cell8);
			    
			    HSSFCell cell9 = row.createCell(8);       //平时表现
			    cell9.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell9.setCellValue(dto.getPeacetime16());
			    style(cell9);
			    
			    HSSFCell cell10 = row.createCell(9);       //出勤率
			    cell10.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell10.setCellValue(dto.getCql());
			    style(cell10);
			    
			    HSSFCell cell11 = row.createCell(10);     //缺勤学时
			    cell11.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell11.setCellValue(dto.getQqxs());
			    style(cell11);
			    
			    HSSFCell cell12 = row.createCell(11);      //考试成绩
			    cell12.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell12.setCellValue(dto.getExamine_result());
			    style(cell12);
			     
			    HSSFCell cell13 = row.createCell(12);      //补考
			    cell13.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell13.setCellValue(dto.getPeacetime18());
			    style(cell13);
			    
			    HSSFCell cell14 = row.createCell(13);      //学分
			    cell14.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell14.setCellValue(dto.getCredit_hour());
			    style(cell14);
			     
			    HSSFCell cell15 = row.createCell(14);    //"免修"
			    cell15.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell15.setCellValue(dto.getCredit_source());
			    style(cell15);
			    
			    HSSFCell cell16 = row.createCell(15);     //学分不通过
			    cell16.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell16.setCellValue(dto.getIs_pass());
			    style(cell16);
			    
			    
			    HSSFCell cell17 = row.createCell(16);     //会考成绩
			    cell17.setCellType(HSSFCell.CELL_TYPE_STRING);  
			    cell17.setCellValue(dto.getLevel_name());
			    style(cell17);
			    style(cell17);
			    	
		}
		
		
		 if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
			 if(maxCourseName.length()<=0){
				 maxCourseName="     ";
			 }
			 sheet.setColumnWidth(1,maxCourseName.length()*1*300);  //设置列宽
		 }else{
			 if(maxName.length()<=0){
				 maxName="  ";
			 }
			 sheet.setColumnWidth(1,maxName.length()*3*300);  //设置列宽
		 }
		
		
	}
	 /**
	  * 设置列框
	  */
	public void columnWidth(){
		
	  for(int i=0;i<17;i++){
			sheet.setColumnWidth(i,3000);
	  }	
	  sheet.setColumnWidth(9,2000);
	  sheet.setColumnWidth(12,1500);
	  sheet.setColumnWidth(13,1500);
	  sheet.setColumnWidth(14,1500);
		
	}
	
	
	public void export_Down(HttpServletRequest req, HttpServletResponse resp,String className,String courseValue,String subjectValue,String schoolyearValue,String segmentValue,String exportType,String studentName) {
		try {
			
			String filename = "";
			 if(Constant.USER_KIND_SCHOOLTEACHER.equals(exportType)){
					 filename = className+courseValue+"("+subjectValue+")"+schoolyearValue+"学年"+segmentValue+"成绩表.xls";

		        }else if(Constant.USER_KIND_SCHOOLSTUDENT.equals(exportType)){
					 filename = studentName+schoolyearValue+"学年"+segmentValue+"成绩表.xls";

		        }
			
			resp.setContentType("application/vnd.ms-excel; charset=utf-8");
			resp.addHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes("gbk"), "iso8859-1"));
			OutputStream out = resp.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} catch (IOException e1) {
			logger.error("", e1);
		}
	}

}
