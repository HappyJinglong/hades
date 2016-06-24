package com.flyrish.hades.webapp.action.innercourse;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;

import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.service.ext.IInnerCourseExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.ISysSubjectServiceExt;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class UploadAction extends NginxUploadAction{
	
	/**
	 * 处理记录袋业务类
	 */
	@Spring
	IInnerCourseExt innerCourseExt;
	@Spring
	public ISchoolCourseExt schoolCourseExt;
	@Spring
	public ISysSubjectServiceExt sysSubjectServiceExt;
	
	List<String> insertclassList = new ArrayList<String>();
	
	List<String> insertcourseList = new ArrayList<String>();
	/**
	 * 查询数据参数
	 */
	public  Map<String,Object> params=new HashMap<String,Object>();
	
	public List<GeneralDto> classList;
	
	public List<String> exlClassList;//exl中班级列数据
	/**
	 * UUID
	 * @return
	 */
	  public String getId() { 
	        try { 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	            UUID uuid = UUID.randomUUID(); 
	            String guidStr = uuid.toString(); 
	            md.update(guidStr.getBytes(), 0, guidStr.length()); 
	            return new BigInteger(1, md.digest()).toString(16); 
	        } catch (NoSuchAlgorithmException e) { 
	            return null; 
	        } 
	    }
	  	/**
		 * 解析excel
		 * @param importFile2
		 */
		public List<KcourseDto> parseExcelToList(FileItem importFile,String str,List<GeneralDto> classList) {
			try {
				List<KcourseDto>coursList = new ArrayList<KcourseDto>();
				InputStream inputStream = importFile.getInputStream();
				Workbook workBook = WorkbookFactory.create(inputStream);
				Sheet courseSheet = workBook.getSheetAt(0);
				if(courseSheet!=null){
					if(this.parseExcelInfo(courseSheet,classList)!=null&&this.parseExcelInfo(courseSheet,classList).size()!=0){
						coursList.addAll(this.parseExcelInfo(courseSheet,classList));
					}
				}
				return coursList;
			} catch (Exception e) {
				logger.error("parseExcelToList(FileItem,String)", e);
			}
			return null;
		}
		
		/**
		 * 解析excel
		 * @param importFile2
		 */
		public boolean parseExcelTittle(FileItem importFile,String str,List<GeneralDto> classList,int yearcount) {
			try {
				InputStream inputStream = importFile.getInputStream();
				Workbook workBook = WorkbookFactory.create(inputStream);
				Sheet courseSheet = workBook.getSheetAt(0);
				if(courseSheet!=null){
				return checkExcelTittle(courseSheet,classList,yearcount);
				}
			} catch (Exception e) {
				logger.error("parseExcelTittle(FileItem,String)", e);
			}
			return false;
		}
		
		/**
		 * 解析内容
		 * @param courseSheet
		 * @return
		 */
		public List<KcourseDto> parseExcelInfo(Sheet courseSheet,List<GeneralDto> classList) {
			List<KcourseDto> courseDtoList = new ArrayList<KcourseDto>();
			KcourseDto kcourseDto = null;
			int rowsNum = courseSheet.getLastRowNum();
			/*classList = innerCourseExt.selectNowClass(queryMap);*/
			/*exlClassList = new ArrayList<String>();
			Row rowclass = courseSheet.getRow(2);
			for(int n=0;n<classList.size();n++){//得到exl中所有班级名
				exlClassList.add(this.safeStringValue(rowclass, n+2));
			}*/
			for(int i = 3; i <=rowsNum; i++){
				Row row = courseSheet.getRow(i);
				if(classList!=null&&classList.size()!=0){
					for(int j=0;j<classList.size();j++){
						kcourseDto = new KcourseDto();
						kcourseDto.setRownum(i+1);
						GeneralDto generalDto = new GeneralDto();
						generalDto = classList.get(j);
						kcourseDto.setClassid(generalDto.getClassid());
						kcourseDto.setClassnum(generalDto.getClassnum());
						kcourseDto.setGradenum(generalDto.getGradenum());
						if("1".equals(generalDto.getGradenum())){
							kcourseDto.setClassName("高一"+generalDto.getClassnum()+"班");
						}else if("2".equals(generalDto.getGradenum())){
							kcourseDto.setClassName("高二"+generalDto.getClassnum()+"班");
						}else if("3".equals(generalDto.getGradenum())){
							kcourseDto.setClassName("高三"+generalDto.getClassnum()+"班");
						}
						kcourseDto.setTeacherName(this.safeStringValue(row, j+2));
						kcourseDto.setCourse_name(this.safeStringValue(row, 1));
						kcourseDto.setSubject_name(this.safeStringValue(row, 0));
						courseDtoList.add(kcourseDto);
					}
				}
			}
			return courseDtoList;
		}
		
		/**
		 * 解析内容
		 * @param courseSheet
		 * @return
		 */
		public boolean checkExcelTittle(Sheet courseSheet,List<GeneralDto> classList,int yearcount) {
			String excelList = "";
			String dataList = "";
			String oneListStr="";
			String twoListStr="";
			String threeListStr="";
				Row row = courseSheet.getRow(2);
				for(int j=0;j<classList.size();j++){
					GeneralDto generalDto = new GeneralDto();
					generalDto = classList.get(j);
					if(yearcount==0){
						if("1".equals(generalDto.getGradenum())){
							oneListStr+=("高一"+generalDto.getClassnum()+"班")+"#!#!";
						}else if("2".equals(generalDto.getGradenum())){
							twoListStr+=("高二"+generalDto.getClassnum()+"班")+"#!#!";
						}else if("3".equals(generalDto.getGradenum())){
							threeListStr+=("高三"+generalDto.getClassnum()+"班")+"#!#!";
						}
						excelList+=this.safeStringValue(row, j+2)+"#!#!";
					}
					if(yearcount==1){
						if("1".equals(generalDto.getGradenum())){
							twoListStr+=("高二"+generalDto.getClassnum()+"班")+"#!#!";
						}else if("2".equals(generalDto.getGradenum())){
							threeListStr+=("高三"+generalDto.getClassnum()+"班")+"#!#!";
						}
						excelList+=this.safeStringValue(row, j+2)+"#!#!";
					}
					if(yearcount==2){
						if("1".equals(generalDto.getGradenum())){
							threeListStr+=("高三"+generalDto.getClassnum()+"班")+"#!#!";
						}
						excelList+=this.safeStringValue(row, j+2)+"#!#!";
					}
					
				}
				dataList=oneListStr.trim()+twoListStr.trim()+threeListStr.trim();
				if(dataList.equals(excelList)){
					return true;
				}else{
					return false;
				}
				
		}
		
		/**
		 * 数据检验
		 * @param rowData
		 * @param index
		 * @return
		 */
	    public String safeStringValue(Row rowData, int index) {
	        Cell cell = rowData.getCell(index);
	        if (cell == null)
	            return StringUtils.EMPTY;
	        return this.getStringCellValue(cell);
	    }
	    /**
	     * 获取单元格信息
	     * @param cell
	     * @return
	     */
		private String getStringCellValue(Cell cell) {
	        DecimalFormat fmt = new DecimalFormat("#.####");
	        if (null == cell) {
	            return null;
	        } else {
	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_NUMERIC :
	                    return fmt.format(cell.getNumericCellValue());
	                case Cell.CELL_TYPE_STRING :
	                    return cell.getStringCellValue();
	                case Cell.CELL_TYPE_FORMULA :
	                    if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
	                        return cell.getStringCellValue();
	                    } else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
	                        return String.valueOf(cell.getNumericCellValue());
	                    } else {
	                        return null;
	                    }
	                default:
	                    return null;
	            }
	        }
		}
}
