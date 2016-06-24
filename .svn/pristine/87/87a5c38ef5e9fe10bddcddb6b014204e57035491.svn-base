package com.flyrish.hades.webapp.action.masterReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi2.hssf.usermodel.HSSFCell;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;

import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.webapp.action.BaseAction;




public class ExportAction extends BaseAction{
	/**
	 * 导出Excel文件.
	 * 
	 * @param filename 文件名.
	 * @param res
	 * @param data 数据.
	 * @return
	 * @throws ManagerException
	 */
	protected Object exportExcel(String filename, HttpServletResponse res,
			List<Object[]> data){
		try {
			res.setContentType("APPLICATION/ms-excel");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ new String(filename.getBytes("gbk"), "iso8859-1"));
			ServletOutputStream os = res.getOutputStream();
			this.export(os, data);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 导出Excel文件.
	 * 
	 * @param filename 文件名.
	 * @param res
	 * @param data 数据.
	 * @return
	 * @throws ManagerException
	 */
	protected Object exportExcel(HttpServletResponse res,
			File file){
		try {
			int fileLength = Integer.valueOf(String.valueOf(file.length()));
			res.setContentLength(fileLength);
			res.setContentType("APPLICATION/ms-excel");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ new String(file.getName().getBytes("gbk"), "iso8859-1"));
			byte   b[]=new   byte[fileLength];
            FileInputStream   fi=new   FileInputStream(file);                      
            OutputStream   o=res.getOutputStream();
            int   n   =   0;
            while((n=fi.read(b))!=-1)   {
                    o.write(b,0,n);
            }
            fi.close();
            o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 导出Excel文件.
	 * 
	 * @param filename 文件名.
	 * @param res
	 * @param data 数据.
	 * @return
	 * @throws ManagerException
	 */
	protected Object exportExcel(HttpServletResponse res,
			File file,boolean ifDelete){
		try {
			int fileLength = Integer.valueOf(String.valueOf(file.length()));
			res.setContentLength(fileLength);
			res.setContentType("APPLICATION/ms-excel");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ new String(file.getName().getBytes("gbk"), "iso8859-1"));
			byte   b[]=new   byte[fileLength];
            FileInputStream   fi=new   FileInputStream(file);                      
            OutputStream   o=res.getOutputStream();
            int   n   =   0;
            while((n=fi.read(b))!=-1)   {
                    o.write(b,0,n);
            }
            fi.close();
            o.close();
            if(ifDelete&&file!=null){
            	if(file.exists()){
            		file.delete();
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private void export(OutputStream os, List<Object[]> data){
		try {
			// 创建新的Excel工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中创建工作表
			HSSFSheet sheet = workbook.createSheet("Sheet1");

			int rowNum = 0;
			for (Object[] rowData : data) {
				HSSFRow row = sheet.createRow(rowNum++);
				int cellNum = 0;
				for (Object cellData : rowData) {
					HSSFCell cell = row.createCell((short) (cellNum++));
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					if (cellData == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue("" + cellData);
					}
				}
			}

			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}