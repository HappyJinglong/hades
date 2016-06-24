package com.flyrish.hades.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.nestframework.addons.spring.Spring;
import org.nestframework.data.Json;

import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.BaseAction;
import com.lowagie.text.Font;

public class TemplateExport extends BaseAction{
    
	public  HSSFWorkbook workbook;
	public FileInputStream is;
	public HSSFCellStyle titleCellStyle;
	public HSSFSheet sheet;
	@Spring
	public IRedisServiceExt redisServiceExt;
	public String dStatus;
	@Json
	public Object queryDownLoadStatus(){
		try {
			String status = redisServiceExt.readSingle(dStatus);
			if("1".equals(status) || "2".equals(status)){
				redisServiceExt.delete(dStatus);
			}
			return JSONObject.fromObject("{val:"+status+"}");
		} catch (ForceException e) {
			return JSONObject.fromObject("{val:"+1+"}");
		}
	}
	/**
	 * 读入模板，并且在模板中添加数据后导出
	 * @param filepath 模板路径，在配置文件中初始化
	 */
	public void InputExcel(HttpServletRequest req,String filepath)
	{
		@SuppressWarnings("deprecation")
		String url = NoServiceUtil.replaceFileSeparator(req.getRealPath("")+constantBean.get(filepath));
		try {
			File file = new File(url);
			is = new FileInputStream(file);
			workbook = new HSSFWorkbook(is);
			sheet = workbook.getSheetAt(0);
			titleCellStyle = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			font.setFontHeightInPoints((short)14);
			titleCellStyle.setFont(font);
		} catch (FileNotFoundException e) {
			logger.error("InputExcel(String)", e);
		} catch (IOException e) {
			logger.error("InputExcel(String)", e);
		}
	}
	/**
	 * 导出excel
	 * @param resp
	 */
	public void OutputExcel(HttpServletResponse resp,String filename)
	{
		try {
			resp.setContentType("application/vnd.ms-excel; charset=utf-8");
			resp.addHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes("gbk"), "iso8859-1"));
			OutputStream out = resp.getOutputStream();
			workbook.write(out);
			is.close();
			out.close();
		} catch (IOException e) {
		    logger.error("OutputExcel(HttpServletResponse)", e);
		}
	}
	/**
	 * 在sheet中增加新的一行
	 * @param i  行标
	 * @param cellNum  列数
	 * @return
	 */
	public HSSFRow CreateCell(int i,int cellNum)
	{
	    HSSFRow row = workbook.getSheetAt(0).createRow(i);
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);      //設置水平居中
	    for(int j=0;j<cellNum;++j)
	    {
	    	HSSFCell cell = row.createCell(j);
	    	cell.setCellStyle(cellStyle);
	    }
	    return row;
	}
	/**
	 * 在监控统计中某行的第cellStart到CellEnd列
	 * @param i 第i行
	 * @param cellStart   开始列号
	 * @param cellEnd  结束列号
	 * @return  row
	 */
	public HSSFRow createCell(int i,int cellStart,int cellEnd)
	{
		HSSFRow row = sheet.getRow(i);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 設置水平居中
		for (int j = cellStart; j < cellEnd+1; ++j) {
			HSSFCell cell = row.createCell(j);
			cell.setCellStyle(cellStyle);
		}
		return row;
	}
}
