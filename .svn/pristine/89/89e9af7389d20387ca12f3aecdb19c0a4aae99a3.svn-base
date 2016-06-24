package com.flyrish.hades.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.BaseAction;

public class ExportMonitorStatictics extends BaseAction {

	public HSSFWorkbook workbook = null;

	public HSSFSheet sheet = null;

	public HSSFCellStyle headerstyle;

	public HSSFCellStyle cellStyle; // 栏目名称的样式

	public HSSFCellStyle contentStyle; // 栏目内容的样式

	public HSSFCellStyle package_contentStyle; // 记录袋中内容的样式

	HSSFFont font1;

	public List<HSSFRow> rows;

	public ServletOutputStream os;

	public ZipOutputStream zos;

	public void initExcel() {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("数据填写监控统");
		sheet.setDefaultRowHeightInPoints(20);

		font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 16);
		font1.setFontName("宋体");
		cellStyle = workbook.createCellStyle(); // 栏目名称的样式
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框
		cellStyle.setFont(font1);

		contentStyle = workbook.createCellStyle(); // 栏目内容的样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setWrapText(true);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		contentStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 上边框
		contentStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 上边框
		contentStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框
		contentStyle.setFont(font1);
		createHeader();
		createAllRows();
		createFirstColumn();
		createSecondColumn();
		createThirdColumn();
		createFourthColumn();
		createFifthColumn();
	}

	/**
	 * 创建excel表头
	 */
	public void createHeader() {
		headerstyle = workbook.createCellStyle(); // 表格第一行的样式
		headerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置水平居中
		headerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		headerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		headerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		headerstyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框
		headerstyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		headerstyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框

		headerstyle.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerstyle.setFont(font);
		HSSFRow row = sheet.createRow(0);
		CellUtil.createCell(row, 0, "一级栏目", headerstyle);
		CellUtil.createCell(row, 1, "二级栏目", headerstyle);
		CellUtil.createCell(row, 2, "", headerstyle);
		CellUtil.createCell(row, 3, "评价主体", headerstyle);
		CellUtil.createCell(row, 4, "评价任务", headerstyle);
		CellUtil.createCell(row, 5, "评价次数要求", headerstyle);
		CellUtil.createCell(row, 6, "总人数", headerstyle);
		CellUtil.createCell(row, 7, "完成人数", headerstyle);
		CellUtil.createCell(row, 8, "未完成人数", headerstyle);
		CellUtil.createCell(row, 9, "完成百分比(%)", headerstyle);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));

		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 3500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 4000);
	}

	public void createAllRows() {
		rows = new ArrayList<HSSFRow>();
		for (int i = 1; i <= 40; i++) {
			HSSFRow row = sheet.createRow(i);
			rows.add(row);
		}
	}

	public void createFirstColumn() {
		Object[][] columns = { { "新学期伊始的我", 2 }, { "学期结束时的我", 3 },
				{ "思想道德", 5 }, { "学业成就", 5 }, { "合作与交流", 5 }, { "运动与健康", 5 },
				{ "审美与表现", 5 }, { "综合实践活动", 5 }, { "个性发展", 5 } };
		createColumns(columns, 1);
	}

	public void createSecondColumn() {
		Object[][] columns = { { "自我评价", 1 }, { "我的发展目标", 1 }, { "自我评价", 1 },
				{ "班主任评语", 1 }, { "家长期望和寄语", 1 }, { "自我评价", 1 }, { "他人评价", 3 },
				{ "思想道德记录袋", 1 }, { "自我评价", 1 }, { "他人评价", 3 },
				{ "学科作品展示", 1 }, { "自我评价", 1 }, { "他人评价", 3 },
				{ "合作与交流行为记录袋", 1 }, { "自我评价", 1 }, { "他人评价", 3 },
				{ "体质健康", 1 }, { "自我评价", 1 }, { "他人评价", 3 }, { "审美与表现记录袋", 1 },
				{ "研究性学习", 3 }, { "社区服务社会实践活动", 2 }, { "自我评价", 1 },
				{ "他人评价", 3 }, { "特长与成果展示", 1 } };
		createColumns(columns, 2);
	}

	public void createFourthColumn() {
		Object[][] columns = { { "学生本人", 1 }, { "学生本人", 1 }, { "学生本人", 1 },
				{ "班主任", 1 }, { "家长", 1 }, { "学生本人", 1 }, { "同学", 1 },
				{ "教师", 1 }, { "家长", 1 }, { "学生本人", 1 }, { "学生本人", 1 },
				{ "课程评语", 1 }, { "同学", 1 }, { "家长", 1 }, { "学生本人", 1 },
				{ "学生本人", 1 }, { "同学", 1 }, { "教师", 1 }, { "家长", 1 },
				{ "学生本人", 1 }, { "学生本人", 1 }, { "同学", 1 }, { "教师", 1 },
				{ "家长", 1 }, { "云平台提取", 1 }, { "学生本人", 1 }, { "同学", 1 },
				{ "教师", 1 }, { "家长", 1 }, { "学生本人", 1 }, { "基本情况", 1 },
				{ "学生本人", 1 }, { "学生本人", 1 }, { "学生本人", 1 },
				{ "自我评价	学生本人", 1 }, { "学生本人", 1 }, { "同学", 1 }, { "教师", 1 },
				{ "家长", 1 }, { "学生本人", 1 } };
		createColumns(columns, 4);
	}

	public void createThirdColumn() {
		Object[][] columns = { { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "基本情况", 1 }, { "研究成果", 1 }, { "自我评价", 1 },
				{ "基本情况", 1 }, { "自我评价", 1 }, { "", 1 }, { "", 1 }, { "", 1 },
				{ "", 1 }, { "", 1 } };
		createColumns(columns, 3);
	}

	public void createFifthColumn() {
		Object[][] columns = { { "必填", 1 }, { "必填", 1 }, { "必填", 1 },
				{ "必填", 1 }, { "必填", 1 }, { "必填", 1 }, { "鼓励填写", 1 },
				{ "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 }, { "必填", 1 },
				{ "必填", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 }, { "必填", 1 },
				{ "鼓励填写", 1 }, { "必填", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 },
				{ "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 },
				{ "鼓励填写", 1 }, { "", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 },
				{ "鼓励填写", 1 }, { "鼓励填写", 1 }, { "必填", 1 }, { "必填", 1 },
				{ "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 },
				{ "必填", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 }, { "鼓励填写", 1 },
				{ "必填", 1 } };
		createColumns(columns, 5);
	}

	/**
	 * 创建excel列内容
	 * 
	 * @param columns
	 *            {{单元格的值, 需要合并几列}}
	 * @param columnIndex
	 *            第几列
	 * 
	 **/
	public void createColumns(Object[][] columns, Integer columnIndex) {
		for (int i = 1, j = 0; j < columns.length; j++) {
			Object[] cell = columns[j];
			if (cell != null && cell.length > 1) {
				int merge = Integer.parseInt(cell[1].toString());
				for (int k = 0; k < merge; k++) {
					CellUtil.createCell(rows.get(i + k - 1), columnIndex - 1,
							String.valueOf(cell[0]), headerstyle);
				}
				sheet.addMergedRegion(new CellRangeAddress(i, i + merge - 1,
						columnIndex - 1, columnIndex - 1));
				i += merge;
			}
		}
	}

	/**
	 * 设置合并单元格的样式
	 */
	public void createRegionStyle(HSSFCellStyle style, CellRangeAddress region) {
		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null)
				row = sheet.createRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
					cell.setCellValue("");
				}
				cell.setCellStyle(style);
			}
		}
	}

	@DefaultAction
	public void export_Down(HttpServletRequest req, HttpServletResponse resp,
			String grade, String clazz, String term) {

		initExcel();

		try {
			String filename = grade + "届" + clazz + "班" + term + "的监控填写情况.xls";
			resp.setContentType("application/vnd.ms-excel; charset=utf-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("gbk"), "iso8859-1"));
			OutputStream out = resp.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("export_Down()", e);
		} catch (IOException e1) {
			logger.error("export_Down()", e1);
		}
	}
}
