package com.flyrish.hades.webapp.action.score;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KstudySegmentDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.IScoreExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class SpecialImportScoreAction extends BaseAction {
	// 接收文件属性
	public FileItem importFile;

	// 区县代码
	public String discode;

	// 学校代码
	public String cmis30id;

	public List<String> studentNames = new ArrayList<String>(); // 学生姓名

	public List<String> graduateyears = new ArrayList<String>(); // 界别

	public List<String> classnums = new ArrayList<String>(); // 班级号

	public List<String> courseNames = new ArrayList<String>(); // 模块名称

	public List<String> subjectNames = new ArrayList<String>(); // 科目名称

	public List<String> courseIds = new ArrayList<String>(); // 模块id

	public List<String> schoolyearNames = new ArrayList<String>(); // 学年

	public List<String> segmentNames = new ArrayList<String>(); // 学段代码

	public List<String> classids = new ArrayList<String>(); // 班级id

	public List<String> studentids = new ArrayList<String>(); // 学生id

	public List<ModelScoreDto> xBExcelCourseInfo = new ArrayList<ModelScoreDto>(); // 导入校本成绩

	public List<ModelScoreDto> nZExcelCourseInfo = new ArrayList<ModelScoreDto>(); // 导入内置成绩

	public List<ModelScoreDto> nZ1ExcelCourseInfo = new ArrayList<ModelScoreDto>(); // 导入内置成绩-插入

	public List<ModelScoreDto> nZ2ExcelCourseInfo = new ArrayList<ModelScoreDto>(); // 导入内置成绩-更新

	public String courseType;

	public String gradeName1;

	public String className1;

	public String schoolyear;

	public String segmentId;

	public String subjectId;

	public String courseId;

	public String tableTitle2;

	@Spring
	private IScoreExt iScoreExt;

	@Spring
	private ISchoolCourseExt schoolCourseExt;

	public List<CampusDto> campus = new ArrayList<CampusDto>(); // 班级

	public Map<String, String> grades = new HashMap<String, String>(); // 年级

	public List<String> xueYears = new ArrayList<String>(); // 学年

	public List<KstudySegmentDto> segments = new ArrayList<KstudySegmentDto>();// 学段

	public List<KsysSubjectDto> subjectDtos = new ArrayList<KsysSubjectDto>();// 学科

	public List<KcourseDto> courseDtos = new ArrayList<KcourseDto>();// 课程模块

	public String manyYears;

	@Before
	public void initData(HttpServletRequest req) {
		this.getLoginInfo(req);
		cmis30id = userDto.getCmis30id();
		discode = userDto.getDiscode();
	}

	@DefaultAction
	public Object studentQueryScoreFirst(HttpServletRequest req,
			HttpSession session) {
		// 获取登录用户信息
		init(req);
		return "importbeadrollscore.jsp";
	}

	// 初始化数据
	private void init(HttpServletRequest req) {
		campus = queryCampusDtos(req);// 返回教师所管辖的班级和年级信息
		grades = queryGrade(campus);// 返回年级

		if (grades.size() > 0) {
			xueYears = iScoreExt.getYears(manyYears); // 获取学年
		}
		UserDto user = getLoginInfo(req);
		cmis30id = user.getCmis30id();
		if (xueYears.size() > 0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("schoolyear", xueYears.get(0).split("@")[0]);
			segments = schoolCourseExt.getSegmentInfos(params); // 学段
		}
	}

	private Map<String, String> queryGrade(List<CampusDto> campus) {
		Map<String, String> campus1 = new LinkedHashMap<String, String>();
		for (int i = 0; i < campus.size(); i++) {
			CampusDto cmd = campus.get(i);
			if (i == 0) {
				manyYears = cmd.getGradeNum();
			}

			if ("1".equals(cmd.getGradeNum())) {
				cmd.setGradeName("高一年级");
			} else if ("2".equals(cmd.getGradeNum())) {
				cmd.setGradeName("高二年级");
			} else if ("3".equals(cmd.getGradeNum())) {
				cmd.setGradeName("高三年级");
			}
			campus1.put(cmd.getGradeId() + "_" + cmd.getGradeNum(),
					cmd.getGradeName());
		}

		return campus1;
	}

	// 返回教师所管辖的班级信息
	private List<CampusDto> queryCampusDtos(HttpServletRequest req) {
		// 获取登录用户信息
		UserDto user = getLoginInfo(req);
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询参数
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("techerid", userDto.getTeacherid());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		List<CampusDto> campus = new ArrayList<CampusDto>();
		if (Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) // 教务老师
				&& Constant.USER_TYPE_SCHOOLADMIN
						.equals(user.getUserRealType())) {
			campus = iScoreExt.getJWLSClass(userDto.getLevelcode(),
					userDto.getCmis30id(), userDto.getCampuseId());
		}
		return campus;
	}

	/**
	 * 导入模板并解析数据
	 */
	public Object importExcelAndPaser(HttpServletRequest request) {
		try {
			if (null != importFile && importFile.getSize() > 0) {
				// 解析excel中的数据
				List<ModelScoreDto> excelCourseInfos = this.parseExcelToList(
						importFile, "模块成绩");

				if (null != excelCourseInfos && excelCourseInfos.size() > 0) {
					if ("请导入正确模板"
							.equals(excelCourseInfos.get(0).getErrorInfo())) {
						List<KcourseDto> errorMessages = new ArrayList<KcourseDto>();
						KcourseDto kd = new KcourseDto();
						kd.setRownum(1);
						kd.setErrorInfo("请导入正确模板");
						errorMessages.add(kd);
						request.setAttribute("errorMessages", errorMessages);
						return "importbeadrollscore.jsp";
					}
				}

				if (null != excelCourseInfos && excelCourseInfos.size() > 0) {
					List<ModelScoreDto> errorMessages = checkImportDatas(excelCourseInfos);
					if (null != errorMessages && errorMessages.size() > 0) {
						// 数据导入失败，返回失败信息
						request.setAttribute("errorMessages", errorMessages);
						return "importbeadrollscore.jsp";
					} else {
						Map<String, Object> params = new HashMap<String, Object>();
						// 校本
						params.put("xBExcelCourseInfo", xBExcelCourseInfo);
						params.put("xb", Constant.KG_COURSE_KIND);
						// 内置
						params.put("nZ1ExcelCourseInfo", nZ1ExcelCourseInfo);
						params.put("nZ2ExcelCourseInfo", nZ2ExcelCourseInfo);
						params.put("cmis30id", cmis30id);
						params.put("discode", discode);
						// 特殊三类：研究性学习活动、社区服务、社会实践
						params.put("practices", practices);
						params.put("servers", servers);
						params.put("study", study);
						params.put("userDto", userDto);
						iScoreExt.saveOrUpdateScores(params);
						request.setAttribute("successFlag", "OK");
						return "importbeadrollscore.jsp";
					}
				} else {
					// 导入模板失败 空数据
					List<KcourseDto> errorMessages = new ArrayList<KcourseDto>();
					KcourseDto kd = new KcourseDto();
					kd.setRownum(3);
					kd.setErrorInfo("您导入的模板没有信息！");
					errorMessages.add(kd);
					request.setAttribute("errorMessages", errorMessages);
					return "importbeadrollscore.jsp";
				}
			}
		} catch (Exception e) {
			logger.error("importExcelAndPaser(HttpServletRequest)", e);
			return "error.jsp";
		}
		return null;
	}

	/**
	 * 解析excel
	 * 
	 * @param importFile2
	 */
	public List<ModelScoreDto> parseExcelToList(FileItem importFile, String str) {
		try {
			List<ModelScoreDto> coursList = new ArrayList<ModelScoreDto>();
			InputStream inputStream = importFile.getInputStream();
			Workbook workBook = WorkbookFactory.create(inputStream);
			Sheet courseSheet = workBook.getSheet(str);
			coursList.addAll(this.parseExcelInfo(courseSheet));
			return coursList;
		} catch (Exception e) {
			logger.error("parseExcelToList(FileItem,String)", e);
		}
		return null;
	}

	/**
	 * 解析内容
	 * 
	 * @param courseSheet
	 * @return
	 */
	public List<ModelScoreDto> parseExcelInfo(Sheet courseSheet) {

		try {
			List<ModelScoreDto> courseDtoList = new ArrayList<ModelScoreDto>();
			ModelScoreDto kcourseDto = null;
			int rowsNum = courseSheet.getLastRowNum();
			for (int i = 2; i <= rowsNum; i++) {
				Row row = courseSheet.getRow(i);
				kcourseDto = new ModelScoreDto();
				kcourseDto.setRownum((i + 1) + "");
				this.parseRow(row, kcourseDto);
				courseDtoList.add(kcourseDto);
			}
			return courseDtoList;
		} catch (Exception e) {
			List<ModelScoreDto> errorMessages = new ArrayList<ModelScoreDto>();
			ModelScoreDto kd = new ModelScoreDto();
			kd.setRownum("");
			kd.setErrorInfo("请导入正确模板");
			errorMessages.add(kd);
			return errorMessages;
		}

	}

	/**
	 * 解析行
	 * 
	 * @param row
	 * @param lietou
	 * @return
	 */
	protected ModelScoreDto parseRow(Row rowData, ModelScoreDto modelScore) {

		modelScore.setSchoolyearName((this.trim(this
				.safeStringValue(rowData, 0)))); // 学年

		String segmentName = (this.trim(this.safeStringValue(rowData, 1)));
		modelScore.setSegmentName(segmentName); // 学段
		modelScore.setCircle((this.trim(this.safeStringValue(rowData, 2)))); // 界别
		modelScore.setClassNum((this.trim(this.safeStringValue(rowData, 3)))); // 班级
		modelScore
				.setStudentName((this.trim(this.safeStringValue(rowData, 4)))); // 姓名
		modelScore.setEdu_id((this.trim(this.safeStringValue(rowData, 5)))); // 教育id
		modelScore
				.setSubjectName((this.trim(this.safeStringValue(rowData, 6)))); // 科目
		modelScore.setCourseName((this.trim(this.safeStringValue(rowData, 7)))); // 模块名称
		modelScore.setExamine_result((this.trim(this
				.safeStringValue(rowData, 8)))); // 考试成绩
		modelScore
				.setPeacetime18((this.trim(this.safeStringValue(rowData, 9)))); // 补考
		modelScore.setCredit_source((this.trim(this
				.safeStringValue(rowData, 10)))); // 是否免修
		modelScore.setIs_pass((this.trim(this.safeStringValue(rowData, 11)))); // 学分是否通过
		modelScore.setService_content((this.trim(this.safeStringValue(rowData,
				12)))); // 研究性学习课题/社区服务、社会实践内容
		modelScore
				.setService_days((this.trim(this.safeStringValue(rowData, 13)))); // 研究性学习学时/社区服务、社会实践天数
		modelScore
				.setCredit_hour((this.trim(this.safeStringValue(rowData, 14)))); // 研究性学习/社区服务/社会实践学分
		modelScore
				.setPeacetime16((this.trim(this.safeStringValue(rowData, 15)))); // 日常表现
		modelScore
				.setPeacetime1((this.trim(this.safeStringValue(rowData, 16)))); // 平时成绩1
		modelScore
				.setPeacetime2((this.trim(this.safeStringValue(rowData, 17)))); // 平时成绩2
		modelScore
				.setPeacetime3((this.trim(this.safeStringValue(rowData, 18)))); // 平时成绩3
		modelScore
				.setPeacetime4((this.trim(this.safeStringValue(rowData, 19)))); // 平时成绩4
		modelScore
				.setPeacetime5((this.trim(this.safeStringValue(rowData, 20)))); // 平时成绩5
		modelScore
				.setDaily_behave((this.trim(this.safeStringValue(rowData, 21)))); // 平时评定
		modelScore.setQqxs((this.trim(this.safeStringValue(rowData, 22)))); // 缺勤学时

		return modelScore;
	}

	/**
	 * 数据检验
	 * 
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
	 * 
	 * @param cell
	 * @return
	 */
	private String getStringCellValue(Cell cell) {
		DecimalFormat fmt = new DecimalFormat("#.####");
		if (null == cell) {
			return null;
		} else {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return fmt.format(cell.getNumericCellValue());
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_FORMULA:
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
	 * 去除字符串中的空字符串
	 * 
	 * @param str
	 * @return
	 */
	public String trim(String str) {
		return StringUtils.isEmpty(str) ? "" : str.replace(" ", "");
	}

	/**
	 * 验证
	 * 
	 * @param excelCourseInfos
	 * @return
	 */
	public List<ModelScoreDto> checkImportDatas(
			List<ModelScoreDto> excelCourseInfos) {

		List<ModelScoreDto> errorMessages = new ArrayList<ModelScoreDto>();
		for (int i = 0; i < excelCourseInfos.size(); i++) {
			List<String> repeatRowNums = new ArrayList<String>(); // 模块重复行集合
			List<String> repeatRowNumsAndEduId = new ArrayList<String>(); // 模块重复行集合
			ModelScoreDto dto = excelCourseInfos.get(i);
			if ("".equals(dto.getSchoolyearName())
					|| "".equals(dto.getSegmentName())
					|| "".equals(dto.getCircle())
					|| "".equals(dto.getClassNum())
					|| "".equals(dto.getStudentName())
					|| "".equals(dto.getSubjectName())
					|| "".equals(dto.getCourseName())) {
				if ("社会实践".equals(dto.getSubjectName())) {
					dto.setCourseName("社会实践");
				} else if ("社区服务".equals(dto.getSubjectName())) {
					dto.setCourseName("社区服务");
				} else if ("研究性学习活动".equals(dto.getSubjectName())) {
					dto.setCourseName("研究性学习活动");
				} else {
					dto.setErrorInfo("必填项没有填写，不能导入");
					errorMessages.add(dto);
				}
			}
			if (!(dto.getSchoolyearName().matches("[0-9]{4}"))) {
				dto.setErrorInfo("学年填写起始年，例如2015-2016学年，只需填写2015");
				errorMessages.add(dto);
			}
			if (!(dto.getSegmentName().matches("[1-4]{1}"))) {
				dto.setErrorInfo("学段填写，只能填写数字1,2,3,4。例如第一学段，填写1即可");
				errorMessages.add(dto);
			}
			if (!(dto.getCircle().matches("[0-9]{4}"))) {
				dto.setErrorInfo("界别填写，文本格式，填写四位数字，如2016");
				errorMessages.add(dto);
			}
			if (!(dto.getClassNum().matches("^[0-9]*$"))) {
				dto.setErrorInfo("班级填写，文本格式，填写班号数字，如高三1班则直接填写1即可");
				errorMessages.add(dto);
			}
			if ("研究性学习活动".equals(dto.getSubjectName())
					|| "社区服务".equals(dto.getSubjectName())
					|| "社区服务".equals(dto.getSubjectName())) {
				if ("".equals(dto.getService_content())
						|| "".equals(dto.getService_days())
						|| "".equals(dto.getCredit_hour())) {
					dto.setErrorInfo("学科为研究性学习、社区服务、社会实践时必填项没有填，请填写后在导入");
					errorMessages.add(dto);
				} else if (!(dto.getService_days().matches("^[0-9]*$") && dto
						.getCredit_hour().matches("^[0-9]*$"))) {
					dto.setErrorInfo("天数和学分请填写整数，填写后在导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getQqxs()))) {
				if (!(dto.getQqxs().matches("[0-9]+.*[0-9]*"))) {
					dto.setErrorInfo("填写学生缺勤学时数，只能填写数字。导入后系统自动计算出勤率，如果不填写，则出勤率为100%");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getExamine_result()))) {
				if (dto.getExamine_result().length() > 4) {
					dto.setErrorInfo("考试成绩超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime18()))) {
				if (dto.getPeacetime18().length() > 4) {
					dto.setErrorInfo("补考成绩超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime16()))) {
				if (dto.getPeacetime16().length() > 4) {
					dto.setErrorInfo("日常表现超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime1()))) {
				if (dto.getPeacetime1().length() > 4) {
					dto.setErrorInfo("平时成绩1超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime2()))) {
				if (dto.getPeacetime2().length() > 4) {
					dto.setErrorInfo("平时成绩2超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime3()))) {
				if (dto.getPeacetime3().length() > 4) {
					dto.setErrorInfo("平时成绩3超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime4()))) {
				if (dto.getPeacetime4().length() > 4) {
					dto.setErrorInfo("平时成绩4超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getPeacetime5()))) {
				if (dto.getPeacetime5().length() > 4) {
					dto.setErrorInfo("平时成绩5超长，不导入");
					errorMessages.add(dto);
				}
			}
			if (!("".equals(dto.getDaily_behave()))) {
				if (dto.getDaily_behave().length() > 4) {
					dto.setErrorInfo("平时评定超长，不导入");
					errorMessages.add(dto);
				}
			}
			studentNames.add(dto.getStudentName());
			graduateyears.add(dto.getCircle());
			classnums.add(dto.getClassNum());
			subjectNames.add(dto.getSubjectName());
			courseNames.add(dto.getCourseName());

			if ("1".equals(dto.getSegmentName())) {
				dto.setSegmentName(Constant.KG_ARRANSEGMENT_1);
			} else if ("2".equals(dto.getSegmentName())) {
				dto.setSegmentName(Constant.KG_ARRANSEGMENT_2);
			} else if ("3".equals(dto.getSegmentName())) {
				dto.setSegmentName(Constant.KG_ARRANSEGMENT_3);
			} else if ("4".equals(dto.getSegmentName())) {
				dto.setSegmentName(Constant.KG_ARRANSEGMENT_4);
			}

			String repeatRowNum = "";
			if (repeatRowNums.size() > 0) { // 判断该行是否在重复行集合中
				for (String num : repeatRowNums) {
					if (num.equals(dto.getRownum())) {
						repeatRowNum = dto.getRownum();
					}
				}
			}
			ModelScoreDto md1 = new ModelScoreDto();
			ModelScoreDto md2 = new ModelScoreDto();

			// 判断有哪些是重复导入
			if ("".equals(repeatRowNum)) {
				for (int j = 0; j < excelCourseInfos.size(); j++) {
					ModelScoreDto dto2 = excelCourseInfos.get(j);
					if (dto.getSchoolyearName()
							.equals(dto2.getSchoolyearName())
							&& dto.getSegmentName().equals(
									dto2.getSegmentName())
							&& dto.getCircle().equals(dto2.getCircle())
							&& dto.getClassNum().equals(dto2.getClassNum())
							&& dto.getStudentName().equals(
									dto2.getStudentName())
							&& dto.getSubjectName().equals(
									dto2.getSubjectName())
							&& dto.getCourseName().equals(dto2.getCourseName())
							&& i != j) {
						if (null == dto.getEdu_id()
								|| "".equals(dto.getEdu_id())
								|| null == dto2.getEdu_id()
								|| "".equals(dto2.getEdu_id())) {
							repeatRowNums.add(dto.getRownum());
							repeatRowNums.add(dto2.getRownum());
							md1.setRownum(dto2.getRownum() + "、"
									+ dto.getRownum());
							md1.setErrorInfo("导入学生模块重复，请填写教育ID");
							errorMessages.add(md1);

						} else {
							if (dto.getEdu_id().equals(dto2.getEdu_id())) {
								repeatRowNums.add(dto.getRownum());
								repeatRowNums.add(dto2.getRownum());
								md2.setRownum(dto2.getRownum() + "、"
										+ dto.getRownum());
								md2.setErrorInfo("导入学生模块重复，不导入");
								errorMessages.add(md2);
							}
						}

					}
				}
			}

		}

		if (errorMessages.size() > 0) {
			List<ModelScoreDto> list = new ArrayList<ModelScoreDto>();
			for (int i = 0; i < errorMessages.size(); i++) { // 去重
				ModelScoreDto str = errorMessages.get(i); // 获取传入集合对象的每一个元素
				if (!list.contains(str)) { // 查看新集合中是否有指定的元素，如果没有则加入
					list.add(str);
				}
			}
			return list;
		}

		checkStudent(errorMessages, excelCourseInfos);
		if (errorMessages.size() > 0) {
			return errorMessages;
		}
		checkSubjectAndCourse(errorMessages, excelCourseInfos);
		if (errorMessages.size() > 0) {
			return errorMessages;
		}

		checkStudentCourse(errorMessages);
		if (errorMessages.size() > 0) {
			return errorMessages;
		}

		checkStudentCourse2(errorMessages);
		if (errorMessages.size() > 0) {
			return errorMessages;
		}
		return errorMessages;
	}

	/**
	 * 学生验证
	 * 
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkStudent(List<ModelScoreDto> errorMessages,
			List<ModelScoreDto> excelCourseInfos) {

		List<String> schoolYears = new ArrayList<String>();
		if (excelCourseInfos == null || excelCourseInfos.size() < 0)
			return;
		List<ModelScoreDto> datas = iScoreExt
				.getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(
						cmis30id, discode, studentNames, graduateyears,
						classnums, null);
		for (ModelScoreDto excelCourseInfo : excelCourseInfos) {

			if (datas.size() <= 0) {
				excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
				errorMessages.add(excelCourseInfo);
			} else {
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();

				// 数据对比
				for (ModelScoreDto data : datas) {
					if (data.getCircle().substring(0, 4)
							.equals(excelCourseInfo.getCircle())
							&& data.getClassNum().equals(
									excelCourseInfo.getClassNum())
							&& data.getStudentName().equals(
									excelCourseInfo.getStudentName())) {
						md.add(data);
					}
				}
				if (md.size() <= 0) {
					excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
					errorMessages.add(excelCourseInfo);
				} else if (md.size() > 1) {
					if (excelCourseInfo.getEdu_id() == null
							|| "".equals(excelCourseInfo.getEdu_id())) {
						excelCourseInfo.setErrorInfo("学生在系统中重复，请填写教育ID");
						errorMessages.add(excelCourseInfo);
					} else {
						List<ModelScoreDto> md2 = new ArrayList<ModelScoreDto>();
						for (ModelScoreDto data : datas) {
							if (data.getCircle().substring(0, 4)
									.equals(excelCourseInfo.getCircle())
									&& data.getClassNum().equals(
											excelCourseInfo.getClassNum())
									&& data.getStudentName().equals(
											excelCourseInfo.getStudentName())
									&& data.getEdu_id().equals(
											excelCourseInfo.getEdu_id())) {
								md2.add(data);
							}
						}
						if (md2.size() <= 0) {
							excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
							errorMessages.add(excelCourseInfo);
						} else {
							excelCourseInfo.setEdu_id(md2.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md2.get(0)
									.getStudentid());
							excelCourseInfo.setClassid(md2.get(0).getClassid());
						}
					}
				} else {

					if (null == excelCourseInfo.getEdu_id()
							|| "".equals(excelCourseInfo.getEdu_id())) {
						excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
						excelCourseInfo.setStudentid(md.get(0).getStudentid());
						excelCourseInfo.setClassid(md.get(0).getClassid());
					} else {
						if (excelCourseInfo.getEdu_id().equals(
								md.get(0).getEdu_id())) {
							excelCourseInfo.setEdu_id(md.get(0).getEdu_id());
							excelCourseInfo.setStudentid(md.get(0)
									.getStudentid());
							excelCourseInfo.setClassid(md.get(0).getClassid());
						} else {
							excelCourseInfo.setErrorInfo("学生在系统中不存在，不能导入");
							errorMessages.add(excelCourseInfo);
						}
					}
				}
			}
		}
	}

	/**
	 * 学科模块验证
	 * 
	 * @param errorMessages
	 * @param excelCourseInfos
	 */
	public void checkSubjectAndCourse(List<ModelScoreDto> errorMessages,
			List<ModelScoreDto> excelCourseInfos) {
		List<ModelScoreDto> datas = iScoreExt
				.getCourseByCourseNameAndSubjectName(cmis30id, courseNames,
						subjectNames);
		for (ModelScoreDto excelCourseInfo : excelCourseInfos) {
			if (datas.size() <= 0) {
				excelCourseInfo.setErrorInfo("学科模块在系统中不存在，不能导入");
				errorMessages.add(excelCourseInfo);
			} else {
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();
				// 学科模块数据对比
				for (ModelScoreDto data : datas) {
					if (excelCourseInfo.getSubjectName().equals(
							data.getSubjectName())
							&& excelCourseInfo.getCourseName().equals(
									data.getCourseName())) {
						md.add(data);
					}
				}
				if (md.size() <= 0) {
					excelCourseInfo.setErrorInfo("学科模块在系统中不存在，不能导入");
					errorMessages.add(excelCourseInfo);
				} else {
					if ("是".equals(excelCourseInfo.getCredit_source())
							&& "是".equals(excelCourseInfo.getIs_pass())) {
						excelCourseInfo.setErrorInfo("免修、学分不通过不能同时为是，请修改后再导入。");
						errorMessages.add(excelCourseInfo);
					} else if ("".equals(excelCourseInfo.getExamine_result())
							&& "".equals(excelCourseInfo.getPeacetime18())
							&& "".equals(excelCourseInfo.getCredit_source())
							&& "".equals(excelCourseInfo.getIs_pass())) {
						excelCourseInfo
								.setErrorInfo("考试成绩、补考成绩、免修、学分不通过，不能都为空，请填写后再导入。");
						errorMessages.add(excelCourseInfo);
					} else {

						excelCourseInfo.setCourse_kind(md.get(0)
								.getCourse_kind());
						excelCourseInfo.setCourse_name(md.get(0)
								.getCourseName());
						excelCourseInfo.setPeriod_count(md.get(0)
								.getPeriod_count());
						excelCourseInfo.setModel_credit(md.get(0)
								.getModel_credit());
						excelCourseInfo.setCourse_id(md.get(0).getCourse_id());
						courseIds.add(excelCourseInfo.getCourse_id());
						schoolyearNames
								.add(excelCourseInfo.getSchoolyearName());
						segmentNames.add(excelCourseInfo.getSegmentName());
						studentids.add(excelCourseInfo.getStudentid());
						classids.add(excelCourseInfo.getClassid());
						if ("是".equals(excelCourseInfo.getCredit_source())) {
							if (!("社会实践".equals(excelCourseInfo
									.getSubjectName())
									|| "社区服务".equals(excelCourseInfo
											.getSubjectName()) || "研究性学习活动"
										.equals(excelCourseInfo
												.getSubjectName()))) {
								excelCourseInfo.setCredit_hour(excelCourseInfo
										.getModel_credit());
							}
						} else {
							excelCourseInfo.setCredit_source("否");
						}

						if ("是".equals(excelCourseInfo.getIs_pass())) {
							excelCourseInfo.setCredit_hour("0");
						} else {
							excelCourseInfo.setIs_pass("否");
							if (!("社会实践".equals(excelCourseInfo
									.getSubjectName())
									|| "社区服务".equals(excelCourseInfo
											.getSubjectName()) || "研究性学习活动"
										.equals(excelCourseInfo
												.getSubjectName()))) {
								excelCourseInfo.setCredit_hour(excelCourseInfo
										.getModel_credit());
							}
						}
						if (!("".equals(excelCourseInfo.getQqxs()))) {
							/*
							 * Double importQqs =
							 * Double.parseDouble(excelCourseInfo.getQqxs());
							 * Double period =
							 * Double.parseDouble(excelCourseInfo
							 * .getPeriod_count());
							 */
							double period = Double.parseDouble(excelCourseInfo
									.getPeriod_count())
									- Double.parseDouble(excelCourseInfo
											.getQqxs());
							BigDecimal b = new BigDecimal(period);
							period = b.setScale(2, BigDecimal.ROUND_HALF_UP)
									.doubleValue();
							if (period <= 0.00) {
								period = 0.00;
							}

							String cql = ((period)
									/ Double.parseDouble(excelCourseInfo
											.getPeriod_count()) * 100)
									+ "%";
							excelCourseInfo.setCql(cql);
							excelCourseInfo.setPeacetime17(String
									.valueOf(period));

						} else {
							excelCourseInfo.setCql("100%");
							excelCourseInfo.setPeacetime17(excelCourseInfo
									.getPeriod_count());
						}
						if ("社会实践".equals(excelCourseInfo.getSubjectName())) {
							practices.add(excelCourseInfo);
						} else if ("社区服务".equals(excelCourseInfo
								.getSubjectName())) {
							servers.add(excelCourseInfo);
						} else if ("研究性学习活动".equals(excelCourseInfo
								.getSubjectName())) {
							study.add(excelCourseInfo);
						} else if (Constant.KG_COURSE_KIND
								.equals(excelCourseInfo.getCourse_kind())) { // 课程分类
																				// 校本和内置
							xBExcelCourseInfo.add(excelCourseInfo);
						} else {
							nZExcelCourseInfo.add(excelCourseInfo);
						}
					}

				}
			}
		}
	}

	// 社会实践
	private List<ModelScoreDto> practices = new ArrayList<ModelScoreDto>();

	// 社区服务
	private List<ModelScoreDto> servers = new ArrayList<ModelScoreDto>();

	// 社区服务
	private List<ModelScoreDto> study = new ArrayList<ModelScoreDto>();

	/**
	 * 校本课程验证
	 */
	public void checkStudentCourse(List<ModelScoreDto> errorMessages) {

		List<ModelScoreDto> datas = iScoreExt.xbCourseIsStudent(
				schoolyearNames, segmentNames, courseIds, classids, studentids,
				discode, cmis30id);
		for (ModelScoreDto excelCourseInfo : xBExcelCourseInfo) {
			if (datas.size() <= 0) {
				excelCourseInfo.setErrorInfo("该学生不在此学科模块中，不能导入成绩");
				errorMessages.add(excelCourseInfo);
			} else {
				List<ModelScoreDto> md = new ArrayList<ModelScoreDto>();
				for (ModelScoreDto data : datas) {
					// 数据对比 判断该模块是否可以录入该学生成绩
					if (excelCourseInfo.getSchoolyearName().equals(
							data.getSchoolyearName())
							&& excelCourseInfo.getSegmentName().equals(
									data.getSegmentName())
							&& excelCourseInfo.getCourse_id().equals(
									data.getCourse_id())
							&& excelCourseInfo.getStudentid().equals(
									data.getStudentid())) {
						excelCourseInfo.setSegment_course_id(data
								.getSegment_course_id());
						md.add(excelCourseInfo);
					}

				}
				if (md.size() <= 0) {
					excelCourseInfo.setErrorInfo("该学生不在此学科模块中，不能导入成绩");
					errorMessages.add(excelCourseInfo);
				}
			}

		}

	}

	/**
	 * 检查内置课程是否存在，如果不存在，则为其单独生成一条内置课程设置模块
	 */
	public void checkStudentCourse2(List<ModelScoreDto> errorMessages) {

		List<ModelScoreDto> generateClassModelDto = new ArrayList<ModelScoreDto>();
		for (ModelScoreDto excelCourseInfo : nZExcelCourseInfo) {
			//查询历史班级
			String historyclassid = queryClassIdBySchoolyearAndClassid(excelCourseInfo.getSchoolyearName(),excelCourseInfo.getClassid());
			//查询对应的数据是否存在
			Map<String,String> classModelAndCreditMap=iScoreExt.queryClassModelAndCreditByCondition(excelCourseInfo,cmis30id,discode, historyclassid);
			
			if(classModelAndCreditMap==null){
				continue;
			}else{
				String class_model_id=classModelAndCreditMap.get("class_model_id");
				String credit_id=classModelAndCreditMap.get("credit_id");
				if(NestUtil.isEmpty(class_model_id)){
					//class_model直接不存在
					//生成对应的记录
					String gen_class_model_id=iScoreExt.getClassModelAndSegmentModelId(excelCourseInfo,cmis30id,historyclassid);
					excelCourseInfo.setClass_model_id(gen_class_model_id);
					nZ1ExcelCourseInfo.add(excelCourseInfo);
				}else if(NestUtil.isEmpty(credit_id)){
					//class_model直接存在，但考试记录不存在
					excelCourseInfo.setClass_model_id(class_model_id);
					nZ1ExcelCourseInfo.add(excelCourseInfo);
				}else if(NestUtil.isNotEmpty(credit_id)){
					//class_model直接存在，但考试记录存在
					excelCourseInfo.setClass_model_id(class_model_id);
					nZ2ExcelCourseInfo.add(excelCourseInfo);
				}
			}
		}
	}

	// 去重
	public void quchong(List<String> strs) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.size(); i++) { // 去重
			String str = strs.get(i); // 获取传入集合对象的每一个元素
			if (!list.contains(str)) { // 查看新集合中是否有指定的元素，如果没有则加入
				list.add(str);
			}
		}
		strs.removeAll(strs);
		strs.addAll(list);
	}

	/**
	 * 根据学年获取当前班的历史班级id
	 * 
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	public String queryClassIdBySchoolyearAndClassid(String schoolyear,
			String classid) {
		List<String> lists = iScoreExt.getClassIdBySchoolyearAndClassid(
				schoolyear, classid);
		if (null != lists && lists.size() > 0) {
			return lists.get(0);
		}
		return null;
	}
}
