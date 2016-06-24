package com.flyrish.hades.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.BaseAction;

public class ExportAll extends BaseAction{
	public HSSFWorkbook workbook=null;
	public HSSFSheet sheet=null;
	public POIFSFileSystem fs=null;
	public HSSFCellStyle cellStyle=null;   //栏目名称的样式
	public HSSFCellStyle otherStyle=null;  //签名等信息的样式
	public HSSFCellStyle contentStyle=null; //栏目内容的样式
	public HSSFCellStyle packageStyle=null;//记录袋的样式
	public HSSFCellStyle package_contentStyle=null;  //记录袋中内容的样式
	public List<AppraiseBaseDto> list=new ArrayList<AppraiseBaseDto>(); //栏目评价信息list
	public Map<String,List<AppraiseBaseDto>> appraiseMaps=new HashMap<String,List<AppraiseBaseDto>>(0);
	public int startrow = 0;         //起始行
	public int beginrow=1;         //开始行
	public int endrow=0;   //结束行
	public int startrow2=0; //在二级栏目细分时合并单元格使用
	public ServletOutputStream os;
	public ZipOutputStream zos;
	/**
	 * 初始化表格样式
	 */
	public void initExcel()
	{
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("评价情况");
		sheet.setDefaultRowHeightInPoints(20);
		
		
		HSSFCellStyle headerstyle = workbook.createCellStyle();   //表格第一行的样式
		headerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   //设置水平居中
		headerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
		headerstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		headerstyle.setBottomBorderColor(HSSFColor.BLACK.index);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerstyle.setFont(font);
		
		HSSFFont font1 = workbook.createFont();
		font1.setFontHeightInPoints((short)12);
		font1.setFontName("宋体");
		cellStyle = workbook.createCellStyle();   //栏目名称的样式
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
 
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);//右边框
		cellStyle.setFont(font1);
		
		otherStyle = workbook.createCellStyle();   //栏目签名等信息的样式
		otherStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		otherStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		otherStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		otherStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		otherStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		otherStyle.setRightBorderColor(HSSFColor.BLACK.index);//右边框
		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short)9);
		font2.setColor(HSSFColor.GREY_50_PERCENT.index);
		font2.setFontName("宋体");
		otherStyle.setFont(font2);
		
		contentStyle = workbook.createCellStyle();   //栏目内容的样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setWrapText(true);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		contentStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		contentStyle.setRightBorderColor(HSSFColor.BLACK.index);//右边框
		contentStyle.setFont(font1);
		
		packageStyle=workbook.createCellStyle();    //记录袋的样式
		packageStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		packageStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		packageStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		packageStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		packageStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		packageStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		packageStyle.setWrapText(true);
		packageStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
		packageStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		packageStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框
		packageStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		packageStyle.setRightBorderColor(HSSFColor.BLACK.index);//右边框
		packageStyle.setFont(font1);
		
		package_contentStyle = workbook.createCellStyle();
		package_contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		package_contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		package_contentStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		package_contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		package_contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		package_contentStyle.setWrapText(true);
		package_contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直居中
		package_contentStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框
		package_contentStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框
		package_contentStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框
		package_contentStyle.setRightBorderColor(HSSFColor.BLACK.index);//右边框
		package_contentStyle.setFont(font1);
		
		HSSFRow row = sheet.createRow(0);
		CellUtil.createCell(row, 0, "一级栏目", headerstyle);
		CellUtil.createCell(row, 1, "", headerstyle);
		CellUtil.createCell(row, 2, "", headerstyle);
		CellUtil.createCell(row, 3, "", headerstyle);
		CellUtil.createCell(row, 4, "", headerstyle);
		CellUtil.createCell(row, 5, "", headerstyle);
		CellUtil.createCell(row, 6, "", headerstyle);
		sheet.addMergedRegion(new CellRangeAddress(startrow,startrow,1,2));
		row.getCell(1).setCellValue("二级栏目");
		row.getCell(1).setCellStyle(headerstyle);
		sheet.addMergedRegion(new CellRangeAddress(startrow,startrow,3,6));
		row.getCell(3).setCellValue("栏目内容");
		row.getCell(3).setCellStyle(headerstyle);
		sheet.setColumnWidth(0, 18*256);
		sheet.setColumnWidth(1, 23*256);
		sheet.setColumnWidth(2, 12*256);
		sheet.setColumnWidth(3, 30*256);
		sheet.setColumnWidth(4, 30*256);
		sheet.setColumnWidth(5, 30*256);
		sheet.setColumnWidth(6, 30*256);
		++startrow;
	}
	/**
	 * 设置合并单元格的样式
	 */
	public void createRegionStyle(HSSFCellStyle style,CellRangeAddress region)
	{
		for(int i=region.getFirstRow();i<=region.getLastRow();i++){  
            HSSFRow row=sheet.getRow(i);  
            if(row==null) row=sheet.createRow(i);  
            for(int j=region.getFirstColumn();j<=region.getLastColumn();j++){  
                HSSFCell cell=row.getCell(j);  
                if( cell==null){  
                    cell=row.createCell(j);  
                    cell.setCellValue("");  
                }  
                 cell.setCellStyle(style);  
            }  
        }  
	}
	
	/**
	 * 没有评价内容时调用(记录袋除外)
	 */
	public HSSFRow createRow()
	{
		HSSFRow row=sheet.createRow(++endrow);
		CellUtil.createCell(row, 0, "", cellStyle);
		CellUtil.createCell(row, 1, "", cellStyle);
		CellUtil.createCell(row, 2, "", cellStyle);
		row.createCell(3);
		row.createCell(4);
		row.createCell(5);
		row.createCell(6);
		return row;
	}
	/**
	 * 拼接获取到的评价人，签名，日期信息
	 * @param appraisebasedto  评价信息
	 * @return
	 */
	public String getMessage(AppraiseBaseDto appraisebasedto)
	{
		StringBuffer string=new StringBuffer("评价人：");
		string.append(appraisebasedto.getAppraseridentify());
		string.append(" 签名：");
		string.append(appraisebasedto.getAppraser());
		string.append(" 日期：");
		string.append(appraisebasedto.getSigndate());
		return string.toString();
	}
	
	/**
	 *二级栏目不细分，没有内容时 
	 * @param sectionName  栏目名称
	 */
	public void not_appraisal(String sectionName)
	{
		HSSFRow row=createRow();
		CellRangeAddress region = new CellRangeAddress(endrow,endrow,3,6);
		createRegionStyle(cellStyle,region);
		sheet.addMergedRegion(region);
	    sheet.addMergedRegion(new CellRangeAddress(endrow,endrow,1,2));
		row.getCell(1).setCellValue(sectionName);
		row.getCell(1).setCellStyle(cellStyle);
		beginrow=endrow+1;
	}
	
	/**
	 * 一级栏目合并
	 * @param firstSectionName 一级栏目名称
	 */
	public void region_First(String firstSectionName)
	{
		sheet.addMergedRegion(new CellRangeAddress(startrow,endrow,0,0));
		HSSFRow row=sheet.getRow(startrow);
		row.getCell(0).setCellValue(firstSectionName);
		startrow=beginrow;
	}
	 /**
     * 体质健康中体重详细情况是调用
     * @param num 列号
     * @param content  单元格的内容
     * @param style   单元格样式
     */
    public void physical_weight(int num,String content,HSSFCellStyle style)
    {
    	CellRangeAddress region = new CellRangeAddress(endrow-1,endrow,num,num);
    	createRegionStyle(style, region);
    	sheet.addMergedRegion(region);
    	sheet.getRow(endrow-1).getCell(num).setCellValue(content);
    }
    /**
     * 体质健康中体重
     * @param app
     */
    public void weight(AppraiseBaseDto app)
    {
    	
    	HSSFRow row1 = createRow();
    	HSSFRow row2 = createRow();
    	physical_weight(3,"身高标准体重\n体重",packageStyle);
    	//row1.getCell(4).setCellValue(app.getItemMarkStandardWeight());
    	if(NestUtil.isNotEmpty(app.getItemMark())){
    		row1.getCell(4).setCellValue(app.getItemMarkStandardWeight());
    		row1.getCell(4).setCellValue(app.getItemMark().split("/")[0]);
    	}
    	row1.getCell(4).setCellStyle(packageStyle);
    	if(NestUtil.isNotEmpty(app.getItemMark())){
    		row2.getCell(4).setCellValue(app.getItemMarkStandardWeight());
    		row2.getCell(4).setCellValue(app.getItemMark().split("/")[1]);
    	}
    	//row2.getCell(4).setCellValue(app.getItemMarkWeight());
    	row2.getCell(4).setCellStyle(packageStyle);
    	physical_weight(5,app.getItemScore(),packageStyle);
    	physical_weight(6,app.getItemGrade(),package_contentStyle);
    }
    /**
     * 体质健康中除体重外的项目
     * @param app
     */
    public void physcial_other(AppraiseBaseDto app)
    {
    	HSSFRow row = createRow();
    	row.getCell(3).setCellValue(app.getItemName());
    	row.getCell(3).setCellStyle(packageStyle);
    	row.getCell(4).setCellValue(app.getItemMark());
    	row.getCell(4).setCellStyle(packageStyle);
    	row.getCell(5).setCellValue(app.getItemScore());
    	row.getCell(5).setCellStyle(packageStyle);
    	row.getCell(6).setCellValue(app.getItemGrade());
    	row.getCell(6).setCellStyle(package_contentStyle);
    }
    /**
     * 体质健康中的统计
     * @param name 奖励得分、学年总分、等级评定
     * @param content  内容
     */
    public void estimate(String name,String content)
    {
    	HSSFRow row = createRow();
    	row.getCell(3).setCellStyle(packageStyle);
    	row.getCell(3).setCellValue(name);
    	row.getCell(4).setCellStyle(packageStyle);
    	row.getCell(5).setCellStyle(packageStyle);
    	row.getCell(6).setCellStyle(package_contentStyle);
    	sheet.addMergedRegion(new CellRangeAddress(endrow,endrow,4,6));
    	row.getCell(4).setCellValue(content);
    }
    /**
     * 体质健康
     */
    public void physical_Health(String levelcode)
    {
    	if((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))
    	{
    		list=appraiseMaps.get(Constant.TYPE_YDJKTZJK);
    	}else{
    		list=appraiseMaps.get(Constant.PLAY_PHYSCIAL_HEALTH);
    	}
    	if(list!=null)
    	{
    		HSSFRow row = createRow();
    		row.getCell(3).setCellValue("指标(项目)");
    		row.getCell(3).setCellStyle(packageStyle);
    		row.getCell(4).setCellValue("成绩");
    		row.getCell(4).setCellStyle(packageStyle);
    		row.getCell(5).setCellValue("得分");
    		row.getCell(5).setCellStyle(packageStyle);
    		row.getCell(6).setCellValue("等级");
    		row.getCell(6).setCellStyle(package_contentStyle);
    		int i = 0;
    		for(AppraiseBaseDto app : list)
    		{
    			++i;
    			if(app.getItemType().equals("1"))
    			{
    				weight(app);
    			}else{
    			    physcial_other(app);
    			}
    			if(list.size()==i)
    			{
    				estimate("奖励得分",app.getRewardSscore());
    				estimate("学年总分",app.getYearScore());
    				estimate("等级评定",app.getYearGrade());
    			}
    		}
    		sheet.addMergedRegion(new CellRangeAddress(beginrow,endrow,1,2));
			sheet.getRow(beginrow).getCell(1).setCellValue("体质健康");
			beginrow=endrow+1;
    	}else{
    		not_appraisal("体质健康");
    	}
    }
    
   	/**
   	 * 个性发展基本情况调用的方法
   	 * @param section1    二级指标
   	 * @param section2  三级指标
   	 * @param section3  个性发展记录
   	 */
   	public void develop_Single(String section1,String section2,String section3)
   	{
   		HSSFRow row1=createRow();
   		row1.getCell(3).setCellValue(section1);
   		row1.getCell(3).setCellStyle(packageStyle);
   		row1.getCell(4).setCellValue(section2);
   		row1.getCell(4).setCellStyle(packageStyle);
   		CellRangeAddress region = new CellRangeAddress(endrow,endrow,5,6);
   		createRegionStyle(package_contentStyle, region);
   		sheet.addMergedRegion(region);
   		row1.getCell(5).setCellValue(section3);
   	}
   	
	/**
	 * 记录袋和综合型实践活动调用
	 * @param littleSection  当为主题等时
	 * @param content   内容
	 */
	public HSSFRow package_base(String littleSection,String content)
	{
		HSSFRow row=createRow();
		int num = getExcelCellAutoHeight(content, 39);
		row.setHeightInPoints(num*20);
		row.getCell(3).setCellValue(littleSection);
		row.getCell(3).setCellStyle(packageStyle);
		CellRangeAddress region = new CellRangeAddress(endrow,endrow,4,6); 
		createRegionStyle(package_contentStyle, region);
		sheet.addMergedRegion(region);
		row.getCell(4).setCellValue(content);
		return row;
	}
	
	public void writeFile(String filePath,OutputStream os){
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(filePath));
			BufferedInputStream bis = new BufferedInputStream(fis);  
			int read = 0;  
			byte[] bufs = new byte[1024*10]; 
			while((read=bis.read(bufs, 0, 1024*10)) != -1) {  
				os.write(bufs, 0, read);  
			} 
			bis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.fillInStackTrace());
		}
	}
	// 删除空文件
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			logger.error("delFolder(String) folderPath:" + folderPath, e);
		}
	}
	// 删除文件
	public void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
			}
		}
		return;
	}
	/**
	 * 产生附件
	 * @param req
	 * @param rep
	 * @param uuid
	 * @param fileName  文件名称
	 * @param file_truepath 数据库上文件的地址 
	 */
	public void generateAttach(String sectionName,String fileName,String file_path)
	{
		String export_path = Constant.attach+File.separator+sectionName+File.separator;
		String file_truepath = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path")+file_path);
		try{
			if(!new File(file_truepath).exists())
			{
				return;
			}else{
				String pathinfile = export_path+fileName;
				zos.putNextEntry(new ZipEntry(pathinfile));
				writeFile(file_truepath,zos);
			}
		}catch(Exception ex){
			logger.error("generateExcel(HttpServletRequest,HttpServletResponse,"+
			"String,String)",ex);
		}
	}
	/**
	 * 产生excel
	 * @param req
	 * @param rep
	 * @param uuid  
	 * @param studentName  学生名称
	 * @return
	 */
	public void generateExcel(String studentName,String termidName)
	{
		String uuid = NoServiceUtil.getGuid();    //产生uuid
		BigInteger uuid1,div,result;
		int hashcode = uuid.hashCode();
		uuid1 = new BigInteger(hashcode+"");
		div = new BigInteger("10");
		result = uuid1.mod(div);
		String server_filename = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path"))+
			File.separator+"zipfile"+File.separator+result+File.separator+uuid+File.separator;
		File f = new File(server_filename);
		if(!f.exists())
		{
			f.setWritable(true, false);
			f.mkdirs();
		}
		String pathinfile = server_filename+studentName+termidName+"的评价情况.xls";
		String pathfile = studentName+termidName+"的评价情况.xls";
		String deleteFilepath = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path"))+File.separator+"zipfile"+File.separator;
		try{
			FileOutputStream fos = new FileOutputStream(pathinfile);
			workbook.write(fos);
			fos.close();
			zos.putNextEntry(new ZipEntry(pathfile));
			writeFile(pathinfile, zos);
			delAllFile(deleteFilepath);
		}catch(Exception ex){
			delAllFile(deleteFilepath);
			logger.error("generateExcel(HttpServletRequest,HttpServletResponse,"+
			"String,String)",ex);
		}
	}
	/**
	 * 产生压缩包
	 */
	public void generateZip(HttpServletRequest req, HttpServletResponse resp,String studentName,String termidName)
	{
		try {
			os = resp.getOutputStream();
			zos = new ZipOutputStream(os);
			zos.setEncoding("GBK");
			String filename = studentName +termidName+ "的评价情况.zip";
			resp.setContentType("APPLICATION/ms-excel");
			resp.setHeader("Content-Disposition", "attachment; filename="
					+ new String(filename.getBytes("gbk"), "iso8859-1"));
		} catch (IOException e) {
           logger.error("generateZip(HttpServletRequest,HttpServletResponse,String)", e);
		}
	}
    /**
     * 当内容过长时设置两个行高
     * @param str  单元格的内容
     * @param fontCountInline  一行内输入多少汉字(50个)
     * @return  当内容超过一行时显示两行,否则显示一行
     */
	public static int getExcelCellAutoHeight(String str, float fontCountInline) {
		float defaultCount = 0.00f;
		if (NestUtil.isNotEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				float ff = getregex(str.substring(i, i + 1));
				defaultCount = defaultCount + ff;
				if (defaultCount > 50 && i < str.length()) {
					return 2;
				}
			}
		}
		return 1;// 计算
	}
	public static float getregex(String charStr) {
		
		if (charStr == " ") {
			return 0.5f;
		}
		// 判断是否为字母或字符
		if (Pattern.compile("^[A-Za-z0-9]+$").matcher(charStr).matches()) {
			return 0.5f;
		}
		// 判断是否为全角

		if (Pattern.compile("[\u4e00-\u9fa5]+$").matcher(charStr).matches()) {
			return 1.00f;
		}
		// 全角符号 及中文
		if (Pattern.compile("[^x00-xff]").matcher(charStr).matches()) {
			return 1.00f;
		}
		return 0.5f;

	}
	/**
	 * 替换转义字符
	 * @param content  内容
	 * @return
	 */
	public String replaceEsc(String content)
	{
		if (NestUtil.isNotEmpty(content)) {
			content = content.replace("&lt;", "<").replace("&gt;", ">")
					.replace("&amp;", "&").replaceAll("&quot;", "\"")
					.replace("&times;", "×").replace("&divide;", "÷");
		}
	   return content;
	}
}
