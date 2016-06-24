package com.flyrish.hades.webapp.action.schoolCourse;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.ISysSubjectServiceExt;
import com.flyrish.hades.webapp.action.PageAction;

public class CourseAction extends PageAction{
	/**********************相应属性接口**********************************/
	/*
	 * 教本课程老师信息
	 */
	public String teacherIdInfo;
	@Spring
	public IRedisServiceExt redisServiceExt;
	/**
	 * 删除成功标识号
	 */
	public String successFlag;
	/**
	 * 学期id前四位
	 */
	public String termId;
	/**
	 * 区县代码
	 */
	public String discode;
	/**
	 * 学段课程标识号
	 */
	public String segCourseId;
	/**
	 * 是否更新数据
	 */
	public String isUpdate;
	/**
	 * 使用年级学段
	 */
	public List<KcourseArrangeDto> kads;
	/**
	 * 单个课程模块信息
	 */
	public KcourseDto singleCourseInfo;
	/**
	 * 选择学段
	 */
	public List<String>sns;
	/**
	 * 选择年级
	 */
	public List<String>gns;
	/**
	 * 判断是编号还是名称
	 */
	public String courseFlag;
	/**
	 * 装载课程编号和姓名的载体
	 */
	public String courseInfo;
	public String cmis30id;
	/**
	 * 保存失败提醒信息
	 */
	public String saveErroMeg;
	/**
	 * 学段
	 */
	public String[] segmentName;
	public String sName;
	/**
	 * 使用年级
	 */
	public String[] gradeName;
	public String gName;
	/**
	 * 教师id
	 */
	public String teacherId;
	/**
	 * 近三学年
	 */
	public String year;
	/**
	 * 学年学段
	 */
	public String segmentId;
	/**
	 * 学科标示号
	 */
	public String subjectId;
	/**
	 * 是否被录取
	 */
	public String isHired;
	/**
	 * 模块名称
	 */
	public String courseName;
	/**
	 * 指导老师姓名
	 */
	public String guidTeacherName;
	/**
	 * 查询数据参数
	 */
	public  Map<String,Object> params=new HashMap<String,Object>();
	/**
	 * 是否查询数据
	 */
	public String isSearch;
	/**
	 * 模块编号
	 */
	public String courseCode;
	/**
	 * 模块简称
	 */
	public String courseShortName;
	/**
	 * 学时数
	 */
	public String periodCount;
	/**
	 * 学分
	 */
	public String creditHour;
	/**
	 * 学习方向
	 */
	public String studentAspect;
	/**
	 * 系列
	 */
	public String seriesid;
	/**
	 * 内容简介
	 */
	public String contentIntroduction;
	/**
	 * 参加要求
	 */
	public String joinRequirement;
	/**
	 * 教学要求
	 */
	public String teachRequirement;
	/**
	 * 备注
	 */
	public String courseRemark;
	/**
	 * 课程模块id
	 */
	public String courseid;
	/**
	 * 是否改变了需要验重的两个数据
	 */
	public String isChange;
	/**
	 * 判断是否锁按钮
	 */
	public String isLockButton;
	public UserDto newRoleTeacher;
	public String oldTeacherIdInfo;
	public String oldTeacherId;
	@Spring
	public ISchoolCourseExt schoolCourseExt;
	@Spring
	public ISysSubjectServiceExt sysSubjectServiceExt;
	
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
		public List<KcourseDto> parseExcelToList(FileItem importFile,String str) {
			try {
				List<KcourseDto>coursList = new ArrayList<KcourseDto>();
				InputStream inputStream = importFile.getInputStream();
				Workbook workBook = WorkbookFactory.create(inputStream);
				Sheet courseSheet = workBook.getSheet(str);
				if(null!=courseSheet){
					coursList.add(this.getTittle(courseSheet));
					coursList.addAll(this.parseExcelInfo(courseSheet));
					return coursList;
				}
			} catch (Exception e) {
				logger.error("parseExcelToList(FileItem,String)", e);
			}
			return null;
		}
		/**
		 * 获取标题信息
		 * @param courseSheet
		 * @return
		 */
		private KcourseDto getTittle(Sheet courseSheet) {
			KcourseDto lietou = new KcourseDto();
			int rowsNum = courseSheet.getLastRowNum();
			Row row = courseSheet.getRow(0);
			KcourseDto lie = this.parseRow(row, lietou);
			return lie;
		}
		/**
		 * 解析内容
		 * @param courseSheet
		 * @return
		 */
		public List<KcourseDto> parseExcelInfo(Sheet courseSheet) {
			List<KcourseDto> courseDtoList = new ArrayList<KcourseDto>();
			KcourseDto kcourseDto = null;
			int rowsNum = courseSheet.getLastRowNum();
			for(int i = 1; i <=rowsNum; i++){
				Row row = courseSheet.getRow(i);
				if(row==null)continue;
				kcourseDto = new KcourseDto();
				kcourseDto.setRownum(i+1);
				kcourseDto=this.parseRow(row, kcourseDto);
				if(kcourseDto==null)continue;
				courseDtoList.add(kcourseDto);
			}
			return courseDtoList;
		}
		/**
		 * 解析行
		 * @param row
		 * @param lietou
		 * @return
		 */
		protected KcourseDto parseRow(Row rowData, KcourseDto courseInfo) {
			/*courseInfo.setSubject_name(this.safeStringValue(rowData, 0));
			courseInfo.setCourse_code(this.safeStringValue(rowData, 1));
			courseInfo.setCourse_name(this.safeStringValue(rowData, 2));
			courseInfo.setCredit_hour(this.safeStringValue(rowData, 3));
			courseInfo.setApply_grade(this.safeStringValue(rowData, 4));
			courseInfo.setTeacherName(this.safeStringValue(rowData, 5));
			courseInfo.setPeriod_count(this.safeStringValue(rowData, 6));
			courseInfo.setContent_introduction(this.safeStringValue(rowData, 7));
			courseInfo.setTeach_requirement(this.safeStringValue(rowData, 8));
			courseInfo.setJoin_requirement(this.safeStringValue(rowData, 9));
			courseInfo.setCourse_remark(this.safeStringValue(rowData, 10));*/
			return courseInfo;
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
		/**
		 * 下载文件
		 * @param response
		 * @param downloadfile
		 * @param downName
		 * @param loggerInfo
		 */
		public void downFile(HttpServletResponse response, String downloadfile,String downName, String loggerInfo) {
			response.reset();
			response.setContentType("application/x-download");
			try {
				// 设置下载文件的名称
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String(downName.getBytes("gbk"),"iso8859-1"));
			} catch (UnsupportedEncodingException e) {
				logger.error(loggerInfo, e);
			}
			java.io.OutputStream outp = null;
			java.io.FileInputStream in = null;
			try {
				outp = response.getOutputStream();
				in = new java.io.FileInputStream(downloadfile);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					outp.write(b, 0, i);
				}
				outp.flush();
			} catch (Exception e) {
				logger.error(loggerInfo, e);
			} finally {
				if (in != null) {
					try {
						in.close();
						in = null;
					} catch (IOException e) {
						logger.error(loggerInfo, e);
					}
				}
				if (outp != null) {
					try {
						outp.close();
						outp = null;
					} catch (IOException e) {
						logger.error(loggerInfo, e);
					}
				}
			}
		}
		/**
		 * 去除字符串中的空字符串
		 * @param str
		 * @return
		 */
		public String trim(String str){
			return StringUtils.isEmpty(str)?"":str.trim();
		}
		public void queryCoursePage() {
			if(!"1".equals(isSearch)){
				if(!NestUtil.isEmpty(year)){
					pageObj = schoolCourseExt.selectSchoolCourse(params, pageNo, pageSize);
				}else{
					params.put("flag", "all");
					pageObj = schoolCourseExt.selectSchoolCourse(params, pageNo, pageSize);
				}
			}
		}
		/**
		 * 初始化查询参数
		 * @param req
		 * @param session
		 */
		public void initParams(HttpServletRequest req, HttpSession session) {
			if(!NestUtil.isEmpty(isHired)){
				if("1".equals(isHired)){
					params.put("hired", isHired);
				}else if("0".equals(isHired)){
					params.put("notHired", isHired);
				}
			}
			if(!NestUtil.isEmpty(year)){
				params.put("schoolyear", year);
			}
			if(!NestUtil.isEmpty(segmentId)){
				params.put("segment_id", segmentId);
			}
			if(!NestUtil.isEmpty(subjectId)){
				params.put("course_id", subjectId);
			}
			if(!NestUtil.isEmpty(courseName)){
				params.put("course_name", courseName);
			}
			if(!NestUtil.isEmpty(guidTeacherName)){
				params.put("name", guidTeacherName);
			}
		}
}
