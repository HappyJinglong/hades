package com.flyrish.hades.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi2.hssf.usermodel.HSSFCell;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;
import org.apache.poi2.poifs.filesystem.POIFSFileSystem;
import org.nestframework.commons.utils.StringUtil;

public class ExcelReader {
	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	private HSSFRow row = null;

	private int sheetNum = 0; // 第sheetnum个工作表

	private int rowNum = 0;

//	private FileInputStream fis = null;

	private File file = null;

	public ExcelReader() {
	}

	public ExcelReader(File file) {
		this.file = file;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 读取excel文件获得HSSFWorkbook对象
	 */
	public void open(InputStream input) throws IOException {
//		fis = new FileInputStream(file);
		wb = new HSSFWorkbook(new POIFSFileSystem(input));
//		fis.close();
	}

	/**
	 * 返回sheet表数目
	 * 
	 * @return int
	 */
	public int getSheetCount() {
		int sheetCount = -1;
		sheetCount = wb.getNumberOfSheets();
		return sheetCount;
	}

	/**
	 * sheetNum下的记录行数
	 * 
	 * @return int
	 */
	public int getRowCount() {
		if (wb == null){}
			
		HSSFSheet sheet = wb.getSheetAt(this.sheetNum);
		int rowCount = -1;
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	/**
	 * 读取指定sheetNum的rowCount
	 * 
	 * @param sheetNum
	 * @return int
	 */
	public int getRowCount(int sheetNum) {
		HSSFSheet sheet = wb.getSheetAt(sheetNum);
		int rowCount = -1;
		rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	/**
	 * 得到指定行的内容
	 * 
	 * @param lineNum
	 * @return String[]
	 */
	public String[] readExcelLine(int lineNum) {
		return readExcelLine(this.sheetNum, lineNum);
	}

	/**
	 * 指定工作表和行数的内容
	 * 
	 * @param sheetNum
	 * @param lineNum
	 * @return String[]
	 */
	public String[] readExcelLine(int sheetNum, int lineNum) {
		if (sheetNum < 0 || lineNum < 0)
			return null;
		String[] strExcelLine = null;
		try {
			sheet = wb.getSheetAt(sheetNum);
			row = sheet.getRow(lineNum);

			int cellCount = row.getLastCellNum();
			strExcelLine = new String[cellCount + 1];
			for (int i = 0; i <= cellCount; i++) {
				strExcelLine[i] = readStringExcelCell(lineNum, i);
			}
		} catch (Exception e) {
			logger.error("系统:virtual;" + e.getMessage() + ";"
					+ e.fillInStackTrace());
		}
		return strExcelLine;
	}

	/**
	 * 读取指定列的内容
	 * 
	 * @param cellNum
	 * @return String
	 */
	public String readStringExcelCell(int cellNum) {
		return readStringExcelCell(this.rowNum, cellNum);
	}

	/**
	 * 指定行和列编号的内容
	 * 
	 * @param rowNum
	 * @param cellNum
	 * @return String
	 */
	public String readStringExcelCell(int rowNum, int cellNum) {
		return readStringExcelCell(this.sheetNum, rowNum, cellNum);
	}

	/**
	 * 指定工作表、行、列下的内容
	 * 
	 * @param sheetNum
	 * @param rowNum
	 * @param cellNum
	 * @return String
	 */
	public String readStringExcelCell(int sheetNum, int rowNum, int cellNum) {
		if (sheetNum < 0 || rowNum < 0)
			return "";
		String strExcelCell = "";
		try {
			sheet = wb.getSheetAt(sheetNum);
			row = sheet.getRow(rowNum);

			if (row.getCell((short) cellNum) != null) { // add this condition
				// judge
				switch (row.getCell((short) cellNum).getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA:
					strExcelCell = "FORMULA ";
					break;
				case HSSFCell.CELL_TYPE_NUMERIC: {
					strExcelCell = String.valueOf(row.getCell((short) cellNum)
							.getNumericCellValue());
				}
				break;
				case HSSFCell.CELL_TYPE_STRING:
					strExcelCell = row.getCell((short) cellNum)
					.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					strExcelCell = "";
					break;
				default:
					strExcelCell = "";
				break;
				}
			}
		} catch (Exception e) {
			logger.error("系统:virtual;" + e.getMessage() + ";"
					+ e.fillInStackTrace());
		}
		return strExcelCell;
	}

	public static String readStringExcelCell(HSSFCell cell) {
		String strExcelCell = "";
		try {
			switch (cell.getCellType()) 
			{ 
			case HSSFCell.CELL_TYPE_FORMULA : 
				strExcelCell = ""; 
				break; 

			case HSSFCell.CELL_TYPE_NUMERIC : 
				strExcelCell = ""+ cell.getNumericCellValue(); 
				if(StringUtil.isNotEmpty(strExcelCell)){
					int pointLen = strExcelCell.lastIndexOf(".");
					if(pointLen != -1){
						String pointStr = strExcelCell.substring(pointLen+1, strExcelCell.length());
						if("0".equals(pointStr)){
							strExcelCell = strExcelCell.substring(0, pointLen);
						}
					}
				}
				break; 

			case HSSFCell.CELL_TYPE_STRING : 
				strExcelCell = cell.getStringCellValue(); 
				break; 

			default : strExcelCell = "";
			} 
		} catch (Exception e) {
			logger.error("获取excel表格值错误：" + e.getMessage() + ";"
					+ e.fillInStackTrace());
			return "";
		}
		return strExcelCell;
	}

	/**
	 * 获取所有行内容(读取索引为0的工作表)
	 * @param fileUrl 文件路径
	 * @return
	 */
	public static List<String[]> getRowList(InputStream input,int sheetNum,int forEachNum) {
		List<String[]> list = new ArrayList<String[]>();
		try {
			ExcelReader readExcel = new ExcelReader();
			readExcel.open(input);
			readExcel.setSheetNum(sheetNum); // 设置读取索引为0的工作表
			// 总行数
			int count = readExcel.getRowCount();
			if(count>0){
				for (int i = forEachNum; i <= count; i++) {
					//每行内容
					String[] rows = readExcel.readExcelLine(i);
					list.add(rows);
				}
			}
		} catch (IOException e) {
			logger.error("系统:virtual;" + e.getMessage() + ";"
					+ e.fillInStackTrace());
		}
		return list;
	}
	/**
	 * 读取excel
	 * @param input
	 * @param sheetNum
	 * @param forEachNum
	 * @return
	 */
	public static List<String[]> getRowExcelList(InputStream input,int sheetNum,int forEachNum,int toEachNum){
		List<String[]> list = new ArrayList<String[]>();
		try {
			ExcelReader readExcel = new ExcelReader();
			readExcel.open(input);
			readExcel.setSheetNum(sheetNum); // 设置读取索引为0的工作表
			for (int i = forEachNum; i <= toEachNum; i++) {
				//每行内容
				String[] rows = readExcel.readExcelLine(i);
				list.add(rows);
			}
		} catch (IOException e) {
			logger.error("系统:virtual;" + e.getMessage() + ";"
					+ e.fillInStackTrace());
		}
		return list;
	}
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(ExcelReader.class);
}


