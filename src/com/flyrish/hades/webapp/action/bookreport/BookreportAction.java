package com.flyrish.hades.webapp.action.bookreport;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.Conditions;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.Report;
import com.flyrish.hades.dto.ReportItme;
import com.flyrish.hades.dto.Reportstatus;
import com.flyrish.hades.dto.Sreportstatus;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.Xueduan;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBookReportExt;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.AntZip;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.masterReport.ExportAction;

public class BookreportAction extends ExportAction {

	@Before
	public void initData(HttpServletRequest req) {
		this.getLoginInfo(req);
		this.initParams();
	}

	public String error;

	public List<ClassDto> classes;// 首页的封装类

	/**
	 * service具体逻辑 -高中报告册
	 * */
	@Spring
	public IBookReportExt bookReportExt;

	/**
	 * session信息
	 * */

	/**
	 * 页面需要的参数
	 * */

	// 界别集合
	public List<Conditions> jiebielist;

	// 学段集合
	public List<Xueduan> xueduanlist;

	// 班级集合
	public List<ClassDto> classList;

	/**
	 * ------------------------------------------------------------------------
	 * ---------↑
	 * */

	/**
	 * 页面传过来的参数
	 * */
	// 学段id
	public String learningPeriodId;

	// 界别id
	public String sectorsId;

	/**
	 * 默认action
	 * */
	@DefaultAction
	public Object defaultQueryAction(HttpServletRequest request,
			HttpServletRequest req) {
		// 得到session学区信息
		UserDto udto = this.getLoginInfo(req);
		// 根据cmis30id查询界别和学段
		// 先查询学段信息 在根据学段信息查询对应的界别
		xueduanlist = bookReportExt.getXueduan(udto.getCampuseId());
		if (null != xueduanlist) {
			jiebielist = bookReportExt.getJiebie(udto.getCampuseId(),
					xueduanlist.get(0).getXueduanid());
		}

		HttpSession session = request.getSession();
		session.setAttribute("code", xueduanlist.get(0).getXueduanid());

		if (jiebielist.size() > 0 || null != jiebielist
				&& xueduanlist.size() > 0 || null != xueduanlist) {

			return "bookreport.jsp";
		}

		return null;
	}

	public String xueduan;

	/**
	 * ajax查询界别
	 * */
	@Json
	public Object getJBList(HttpServletRequest request, HttpServletRequest req) {
		// 得到session学区信息
		UserDto udto = this.getLoginInfo(req);
		// 根据cmis30id查询界别和学段
		// 先查询学段信息 在根据学段信息查询对应的界别

		jiebielist = bookReportExt.getJiebie(udto.getCampuseId(), xueduan);
		HttpSession session = request.getSession();
		session.setAttribute("code", xueduan);

		JSONArray json = new JSONArray();
		for (int i = 0; i < jiebielist.size(); i++) {
			json.add(i, jiebielist.get(i));
		}
		String ret = json.toString();

		return JSONObject.fromObject("{vals:" + ret + "}");
		// return jiebielist;
	}

	public String dqXue;

	public String dqJie;

	public String dqxueid;

	// 是否可上报
	public String isSB = "0";

	/**
	 * 班级list
	 * */
	public Object GetBjList(HttpServletRequest request, HttpServletRequest req) {
		UserDto udto = this.getLoginInfo(req);
		// 根据学区id cmis30id 界别id 查询班级信息
		classList = bookReportExt.getBGCBJList(udto.getCampuseId(), sectorsId,
				learningPeriodId);

		HttpSession session = request.getSession();

		session.removeAttribute("jb");
		session.removeAttribute("xuanduan");

		session.setAttribute("jb", sectorsId);
		session.setAttribute("xueduan", learningPeriodId);

		String code = (String) session.getAttribute("code");
		xueduanlist = bookReportExt.getXueduan(udto.getCampuseId());
		jiebielist = bookReportExt.getJiebie(udto.getCampuseId(),
				learningPeriodId);

		if (null != xueduanlist && null != jiebielist) {
			for (Xueduan x : xueduanlist) {
				if (x.getXueduanid().equals(learningPeriodId)) {
					dqXue = x.getXueduan();
					dqxueid = x.getXueduanid();
				}
			}

			for (Conditions c : jiebielist) {
				if (c.getJiebieid().equals(sectorsId)) {
					dqJie = c.getJiebie();
				}
			}
		}

		String terid = udto.getTermId().substring(0, 4);
		Integer ter = Integer.valueOf(terid);
		ter = ter + 1;
		ter.toString();
		if (ter.toString().equals(dqJie)) {
			// 当前学年20151
			isSB = "1";
		}

		if (null != classList && classList.size() > 0) {

			return "bookreport.jsp";
		} else {
			error = "暂无数据";
			return "bookreport.jsp";
		}
	}

	// 班级id数组
	public int classs[];

	// 判断类型
	public String panduan;

	// 非京籍一起上报 默认不选择
	public Integer feijingji;

	// 学生id
	public String xue;

	// 校验失败入库
	List<Sreportstatus> sb = new ArrayList<Sreportstatus>();

	// 校验成果删除之前记录
	List<Sreportstatus> sc = new ArrayList<Sreportstatus>();

	/**
	 * 报告册校验 班级校验
	 * */
	public Object ClassBJY(HttpServletRequest request, HttpServletRequest req) {
		UserDto udto = this.getLoginInfo(req);
		Map<String,Report> reports=bookReportExt.jiaoyanlist(classs, udto, masterReportExt);
		
		bookReportExt.checkReportStatus(reports);

		HttpSession session = request.getSession();
		String jb = (String) session.getAttribute("jb");
		String xueduan = (String) session.getAttribute("xueduan");
		classList = bookReportExt
				.getBGCBJList(udto.getCampuseId(), jb, xueduan);
		String code = (String) session.getAttribute("code");
		if (null != classList && classList.size() > 0) {

			xueduanlist = bookReportExt.getXueduan(udto.getCampuseId());
			jiebielist = bookReportExt.getJiebie(udto.getCampuseId(), code);

			for (Xueduan x : xueduanlist) {
				if (x.getXueduanid().equals(xueduan)) {
					dqXue = x.getXueduan();
				}
			}

			for (Conditions c : jiebielist) {
				if (c.getJiebieid().equals(jb)) {
					dqJie = c.getJiebie();
				}
			}
			String terid = udto.getTermId().substring(0, 4);
			Integer ter = Integer.valueOf(terid);
			ter = ter + 1;
			ter.toString();
			if (ter.toString().equals(dqJie)) {
				// 当前学年20151
				isSB = "1";
			}
			return "bookreport.jsp";
		}

		return null;
	}

	/**
	 * 报告册上报 学生上报
	 * */
	public Object StuBSB(HttpServletRequest request, HttpServletRequest req) {
		String stus[];
		stus = xue.split(",");
		// 便利学生
		// 查询学生有否上报过
		// 1有就略过
		// 2上报 -这个不管是否京畿都上报
		UserDto udto = this.getLoginInfo(req);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

		if (null != stus) {
			if (stus.length > 0) {
				for (int i = 0; i < stus.length; i++) {
					// 得到学生信息
					List<StudentDto> stu = bookReportExt.getStudentByStuId(
							udto.getCampuseId(), stus[i]);
					/*
					 * if(null!=stu){ if(stu.size()>0){ List<Report>
					 * rr=bookReportExt
					 * .getRetortById(stu.get(0).getStudentid());
					 * if(rr.size()==0){ Report report = new Report();
					 * report.setPid(stu.get(0).getStudentid()); //学生id
					 * report.setTermid(udto.getTermId()); //学期id从session中获得
					 * report.setReporttime(df.format(new Date())); //获取当前系统时间
					 * report.setReportstatus("1300001"); //设置状态为已上报
					 * report.setUsed("1"); //设置生效
					 * report.setEduid(stu.get(0).getEduid()); //session获取教育id
					 * //批量插入 bookReportExt.insertReportInfo(report); }else{
					 * //更新操作
					 * bookReportExt.updateReportinof(rr.get(0).getWaterid
					 * (),"1300001"); }
					 * 
					 * } }
					 */
					if (null != stu) {
						for (StudentDto s : stu) {
							List<Report> rr = bookReportExt.getRetortById(s
									.getStudentid());

							// 如果没有插入 表示已上报
							if (rr.size() == 0) {
								Report report = new Report();

								// report.setWaterid(String.valueOf(pk));
								// //暂时用随机时间 用数据库序列
								report.setPid(s.getStudentid()); // 学生id
								report.setTermid(udto.getTermId()); // 学期id从session中获得
								report.setReporttime(df.format(new Date())); // 获取当前系统时间
								report.setReportstatus("1300001"); // 设置状态为已上报
								report.setUsed("1"); // 设置生效
								report.setEduid(s.getEduid()); // session获取教育id
								// 批量插入
								bookReportExt.insertReportInfo(report);
							} else {
								// 更新操作
								bookReportExt.updateReportinof(rr.get(0)
										.getWaterid(), "1300001");
							}
						}
					}

				}
			}
		}
		// 上报完成返回页面刷新
		HttpSession session = request.getSession();
		String jb = (String) session.getAttribute("jb");
		String xueduan = (String) session.getAttribute("xueduan");
		classList = bookReportExt
				.getBGCBJList(udto.getCampuseId(), jb, xueduan);
		String code = (String) session.getAttribute("code");
		if (null != classList && classList.size() > 0) {

			xueduanlist = bookReportExt.getXueduan(udto.getCampuseId());
			jiebielist = bookReportExt.getJiebie(udto.getCampuseId(), code);

			for (Xueduan x : xueduanlist) {
				if (x.getXueduanid().equals(xueduan)) {
					dqXue = x.getXueduan();
				}
			}

			for (Conditions c : jiebielist) {
				if (c.getJiebieid().equals(jb)) {
					dqJie = c.getJiebie();
				}
			}

			String terid = udto.getTermId().substring(0, 4);
			Integer ter = Integer.valueOf(terid);
			ter = ter + 1;
			ter.toString();
			if (ter.toString().equals(dqJie)) {
				// 当前学年20151
				isSB = "1";
			}

			return "bookreport.jsp";
		}
		return null;
	}

	@Json
	public void printReport(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UserDto user = getLoginInfo(request);
		String classids = request.getParameter("classids");
		String[] split = classids.split(",");
		StringBuffer result = new StringBuffer();
		int _size = split.length, _index = 0;
		for (String clazz : split) {
			// 查询学生
			List<StudentDto> students = bookReportExt.getStudentByClasssId(
					clazz, userDto.getCampuseId());
			int size = students.size(), index = 0;
			for (StudentDto student : students) {
				String id = student.getEduid();
				result.append("{reportlet:'all.cpt',edu_id:'" + id
						+ "',discode:'" + user.getDiscode() + "',cmis30id:'"
						+ user.getCmis30id() + "'}");
				if (index < size - 1) {
					result.append(",");
					index++;
				}
			}

			if (_index < _size - 1) {
				result.append(",");
				_index++;
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();
	}

	@Json
	public void printReportByPerson(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UserDto user = getLoginInfo(request);
		String eduids = request.getParameter("eduids");
		String[] split = eduids.split(",");
		StringBuffer result = new StringBuffer();
		int _size = split.length, _index = 0;
		for (String eduid : split) {
			result.append("{reportlet:'all.cpt','op': 'view',edu_id:'" + eduid
					+ "',discode:'" + user.getDiscode() + "',cmis30id:'"
					+ user.getCmis30id() + "'}");
			if (_index < _size - 1) {
				result.append(",");
				_index++;
			}

		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();
	}

	/**
	 * 报告册上报 班级上报
	 * */
	public Object BSB(HttpServletRequest request, HttpServletRequest req) {
		// 便利数组
		// 1个班级的情况
		// 得到班级内所有学生
		// 先查询该学生有否上报
		// 有 略过
		// 没有 插入s_report报告册状态表 ” 1300001“ 表示已经上报
		Report r = new Report();
		// session取用户信息
		UserDto udto = this.getLoginInfo(req);
		// 学生
		List<StudentDto> stulist = new ArrayList<StudentDto>();
		// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		if (null != classs) {
			if (classs.length > 0) {
				for (int i = 0; i < classs.length; i++) {
					// 获取学生列表
					if (null == feijingji) {
						// 1代表全部学生
						stulist = bookReportExt.getStudentByClasssIdAndCode(
								String.valueOf(classs[i]), udto.getCampuseId());
					} else if (feijingji == 1) {
						// 走北京籍
						stulist = bookReportExt.getStudentByClasssId(
								String.valueOf(classs[i]), udto.getCampuseId());
					}
					if (null != stulist) {
						for (StudentDto stu : stulist) {
							List<Report> rr = bookReportExt.getRetortById(stu
									.getStudentid());

							// 如果没有插入 表示已上报
							if (rr.size() == 0) {
								Report report = new Report();

								// report.setWaterid(String.valueOf(pk));
								// //暂时用随机时间 用数据库序列
								report.setPid(stu.getStudentid()); // 学生id
								report.setTermid(udto.getTermId()); // 学期id从session中获得
								report.setReporttime(df.format(new Date())); // 获取当前系统时间
								report.setReportstatus("1300001"); // 设置状态为已上报
								report.setUsed("1"); // 设置生效
								report.setEduid(stu.getEduid()); // session获取教育id
								// 批量插入
								bookReportExt.insertReportInfo(report);
							} else {
								// 更新操作
								bookReportExt.updateReportinof(rr.get(0)
										.getWaterid(), "1300001");
							}
						}
					}

				}
			}
		}

		// 上报完成返回页面刷新
		HttpSession session = request.getSession();
		String jb = (String) session.getAttribute("jb");
		String xueduan = (String) session.getAttribute("xueduan");
		classList = bookReportExt
				.getBGCBJList(udto.getCampuseId(), jb, xueduan);
		String code = (String) session.getAttribute("code");
		if (null != classList && classList.size() > 0) {

			xueduanlist = bookReportExt.getXueduan(udto.getCampuseId());
			jiebielist = bookReportExt.getJiebie(udto.getCampuseId(), code);

			for (Xueduan x : xueduanlist) {
				if (x.getXueduanid().equals(xueduan)) {
					dqXue = x.getXueduan();
				}
			}

			for (Conditions c : jiebielist) {
				if (c.getJiebieid().equals(jb)) {
					dqJie = c.getJiebie();
				}
			}

			String terid = udto.getTermId().substring(0, 4);
			Integer ter = Integer.valueOf(terid);
			ter = ter + 1;
			ter.toString();
			if (ter.toString().equals(dqJie)) {
				// 当前学年20151
				isSB = "1";
			}
			return "bookreport.jsp";
		}

		return null;
	}

	// 单个班级
	public String classid;

	// 返回页面的错误人列表
	public List<ReportItme> studentlist;

	/**
	 * 报告单错误人数
	 * */
	public Object getErrorstudentlist(HttpServletRequest request,
			HttpServletRequest req) {
		UserDto udto = this.getLoginInfo(req);
		studentlist = new ArrayList();
		// 班级下错误人数集合
		List<StudentDto> stulist;

		stulist = bookReportExt.getStudenterrorlist(classid);

		if (stulist.size() > 0) {
			for (StudentDto stu : stulist) {
				ReportItme resitm = new ReportItme();
				resitm.setStudentname(stu.getName()); // 名字
				resitm.setStudentno(stu.getStudentno()); // 学籍号
				resitm.setEduid(stu.getEduid()); // eduid
				// 取出每个学生”缺“
				List<Reportstatus> rstat = bookReportExt
						.getStudenterrorItmeBystuId(stu.getStudentid());

				if (rstat.size() > 0) {
					// 取出每一项
					for (Reportstatus r : rstat) {
						switch (Integer.valueOf(r.getVerfifykind())) {
						case 1301398: // 姓名
							resitm.setName(r.getVerfifyresult());
							break;
						case 1301397: // 性别
							resitm.setSex(r.getVerfifyresult());
							break;
						case 1301396: // 年龄
							resitm.setAge(r.getVerfifyresult());
							break;
						case 1301395: // 班级
							resitm.setGrade(r.getVerfifyresult());
							break;
						case 1301394: // 学籍号
							resitm.setStudentCode(r.getVerfifyresult());
							break;
						case 1301399: // 毕业学校
							resitm.setSchool(r.getVerfifyresult());
							break;
						case 1301307: // 自我评价
							resitm.setSelfappraisal(r.getVerfifyresult());
							break;
						case 1301308: // 成果展示1
							resitm.setShow1(r.getVerfifyresult());
							break;
						case 1301309: // 成果展示2
							resitm.setShow2(r.getVerfifyresult());
							break;
						case 1301310: // 成果展示3
							resitm.setShow3(r.getVerfifyresult());
							break;
						case 1301311: // 高一 senior1
							resitm.setSenior1(r.getVerfifyresult());
							break;
						case 1301312: // 高二
							resitm.setSenior2(r.getVerfifyresult());
							break;
						case 1301313: // 高三
							resitm.setSenior3(r.getVerfifyresult());
							break;
						case 1301315: // 内容摘要
							resitm.setDigest1(r.getVerfifyresult());
							break;
						case 1301317: // 内容摘要2
							resitm.setDigest2(r.getVerfifyresult());
							break;
						case 1301319: // 内容摘要3
							resitm.setDigest3(r.getVerfifyresult());
							break;
						case 1301314: // 标题1
							resitm.setHeadline1(r.getVerfifyresult());
							break;
						case 1301316: // 标题2
							resitm.setHeadline2(r.getVerfifyresult());
							break;
						case 1301318: // 标题3
							resitm.setHeadline3(r.getVerfifyresult());
							break;
						case 1301205: // 学生体质健康数据明细
							resitm.setConstitution(r.getVerfifyresult());
							break;
						case 1301206: // 体检
							resitm.setPhysical(r.getVerfifyresult());
							break;

						case 1301207: // 必修116
							resitm.setRequired(r.getVerfifyresult());
							break;

						case 1301208: // 选修22
							resitm.setElective(r.getVerfifyresult());
							break;

						case 1301209: // 校本
							resitm.setEdition(r.getVerfifyresult());
							break;

						case 1301210: // 总学分144
							resitm.setCredit(r.getVerfifyresult());
							break;
						case 10004: // 总学分144 为空
							resitm.setCreditnull(r.getVerfifyresult());
							break;
						case 10002: // 必修为空 requirednull
							resitm.setRequirednull(r.getVerfifyresult());
							break;
						case 10003: // 选修为空 electivenull
							resitm.setElectivenull(r.getVerfifyresult());
							break;
						case 10001: // 校本为空 editionnull
							resitm.setEditionnull(r.getVerfifyresult());
							break;
						case 1301221: // 会考缺成绩
							resitm.setHuikaochngji(r.getVerfifyresult());
							break;
						case 1301222: // 会考缺成绩
							resitm.setHuikaoadcd(r.getVerfifyresult());
							break;
						default:
							break;
						}
					}
				}
				studentlist.add(resitm);
			}
		}
		if (null != dqJie && "" != dqJie) {
			String terid = udto.getTermId().substring(0, 4);
			Integer ter = Integer.valueOf(terid);
			ter = ter + 1;
			ter.toString();
			if (ter.toString().equals(dqJie)) {
				// 当前学年20151
				isSB = "1";
			}
		}

		return "errorreportlist.jsp";
	}

	// -------------------------------------------------------------------------------------导出开始
	@Spring
	public IRedisServiceExt redisServiceExt;

	@Spring
	public IMasterReportExt masterReportExt;

	List<String> eduId = new ArrayList<String>();

	/**
	 * 班级id
	 */
	public String classId;

	/**
	 * 选择学生教育id
	 */
	public String eduIds;

	/**
	 * 标记是否导出打印模板
	 */
	public String flag;

	/**
	 * 下载状态码
	 */
	public String dStatus;

	/**
	 * 查询参数
	 */
	public Map<String, Object> params = new HashMap<String, Object>();

	// -------------------------------------------------------------------------------------导出

	/**
	 * 单个学生导出
	 * 
	 * @throws ForceException
	 * */
	public Object exportZip(HttpServletRequest req, HttpServletResponse res)
			throws ForceException {
		String zipName = "";
		// 班级classid数组
		String[] clsaaid = classId.split(",");
		try {
			if (null != clsaaid) {
				for (int i = 0; i < clsaaid.length; i++) {

					// 先查出来班主任id
					String bzrid = bookReportExt
							.getStudentBarByClasId(clsaaid[i]);
					params.put("techerid", bzrid);
					if (null != bzrid && "" != bzrid) {
						redisServiceExt.save(dStatus, "0");
						this.putTempParam(clsaaid[i]);
						params.put("levelcode", dqxueid);
						// 1.获取班主任老师班级信息
						List<CampusDto> teacherInfos = masterReportExt
								.queryTeacherInfos(params);
						if (null != teacherInfos && teacherInfos.size() > 0) {
							String templteUrl = NoServiceUtil
									.replaceFileSeparator(req.getRealPath("")
											+ constantBean
													.get("reportfilePath"));
							String url = templteUrl.substring(
									0,
									templteUrl.lastIndexOf(File.separator
											+ File.separator));
							zipName = this.getZipName(teacherInfos, url, flag);
							List<StudentDto> studentBaseInfos = masterReportExt
									.queryStudentBaseInfos(params);
							this.initExportExcelData(studentBaseInfos,
									templteUrl, url, zipName);
							redisServiceExt.save(dStatus, "1");
							return exportExcel(res, new File(zipName), true);
						}
					} else {
						return this.defaultQueryAction(req, req);
					}
				}
			}
		} catch (Exception e) {
			logger.error("单个学生导出出现问题", e);
			redisServiceExt.save(dStatus, "2");
		}
		return null;

	}

	/**
	 * 仅供测试使用
	 */
	private void putTempParam(String classid) {
		if (NestUtil.isNotEmpty(eduIds)) {
			String[] tempEduIds = eduIds.split(",");
			if (null != tempEduIds && tempEduIds.length > 0) {
				for (String eduid : tempEduIds) {
					eduId.add(eduid);
				}
			}
		}
		params.put("eduIds", eduId);
		params.put("classid", classid);
	}

	private void initParams() {
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		params.put("techerid", userDto.getTeacherid());
	}

	private String getZipName(List<CampusDto> teacherInfos, String url,
			String flag) {
		CampusDto teacher = teacherInfos.get(0);
		/*
		 * if("1".equals(flag)){ return
		 * url+File.separator+teacher.getTeacherName
		 * ()+"（高"+teacher.getGradeName()+"年级"+teacher.getClassNum()+"班）.zip";
		 * }else{
		 */
		return url + File.separator + "高" + teacher.getGradeName()
				+ "年级报告册.zip";
		// }
	}

	private void initExportExcelData(List<StudentDto> studentBaseInfos,
			String fUrl, String url, String zipName) throws ManagerException {
		List<File> zipList = new ArrayList<File>();
		if (null != studentBaseInfos && studentBaseInfos.size() > 0) {
			List<AppraisalDto> personalAppral = null;
			List<AppraisalDto> studyAppral = null;
			/*
			 * if("1".equals(flag)){ personalAppral =
			 * masterReportExt.queryPersonalAppral(params); studyAppral =
			 * masterReportExt.queryStudyAppral(params); }
			 */
			// 个性发展
			List<AppraisalDto> outPersonalAppralSelf = masterReportExt
					.queryOutPersonalAppral(params, "self");
			List<AppraisalDto> outPersonalAppralExtra = masterReportExt
					.queryOutPersonalAppral(params, "");
			// 研究性学习
			List<AppraisalDto> outStudyAppral = masterReportExt
					.queryOutStudyAppral(params);
			// 班主任评语
			List<AppraisalDto> masterAppraise = masterReportExt
					.queryMasterAppral(params);
			// 会考成绩
			Map<String, String> hkScore = masterReportExt.queryHKScore(params);
			// 成绩
			List<ModelScoreDto> allScore = masterReportExt
					.queryAllScore(params);
			// 体质健康数据
			List<HealthDataDto> healthDatas = masterReportExt
					.queryHealthDdatas(params);
			// 体检数据
			List<CheckItemInfoDto> checkItems = masterReportExt
					.queryCheckItems(params);

			// 全部学生
			List<File> fileList = new ArrayList<File>();

			// 全部班级
			List<File> clasList = new ArrayList<File>();

			for (StudentDto sDto : studentBaseInfos) {
				// 以学生为单位 生成zip文件名
				String zipFileName = "";
				try {
					// 文件名称
					String fileName1 = "";
					/*
					 * if("1".equals(flag)){ fileName1 =
					 * sDto.getName()+"（"+sDto.getStudentno()+"）评价数据.xls"; }
					 */
					String fileName2 = sDto.getName() + "（"
							+ sDto.getStudentno() + "）.xls";
					// 1.封面 OK
					List<AppraisalDto> personalAppralSelf = null;
					List<AppraisalDto> personalAppralExtra = null;
					List<AppraisalDto> singleStudyAppral = null;
					/*
					 * if("1".equals(flag)){ personalAppralSelf =
					 * masterReportExt
					 * .initSinglePersonalSelf(sDto,personalAppral,"selef");
					 * personalAppralExtra =
					 * masterReportExt.initSinglePersonalSelf
					 * (sDto,personalAppral,""); singleStudyAppral =
					 * masterReportExt.initsingleStudyAppral(sDto,studyAppral);
					 * }
					 */
					// 2.1个性发展 自我评价
					AppraisalDto outSinglePersonalAppraiseSelf = masterReportExt
							.initOutSinglePersonalAppraiseSelf(
									outPersonalAppralSelf, sDto);
					// 2.2个性发展 自我评价
					List<AppraisalDto> outSinglePersonalAppraiseExtra = masterReportExt
							.initOutSinglePersonalAppraiseExtra(
									outPersonalAppralExtra, sDto);
					// 3.评语
					List<AppraisalDto> masterAppral = masterReportExt
							.initSingleMasterAppraise(sDto, masterAppraise);
					// 4.成绩学分
					Map<String, Map<String, List<ModelScoreDto>>> singleScore = masterReportExt
							.initSingleScore(sDto, allScore);
					// 5.研究性学习
					List<AppraisalDto> outSingleStudyAppral = masterReportExt
							.initOutSinglePersonalAppraiseExtra(outStudyAppral,
									sDto);
					// 6.体质健康
					List<HealthDataDto> singleHealthData = masterReportExt
							.querySingleHealthData(sDto, healthDatas);
					// 7.健康体检
					List<CheckItemInfoDto> sigleCheckItems = masterReportExt
							.querySigleCheckItems(sDto, checkItems);
					/*
					 * //生成学生评价数据 if("1".equals(flag)){
					 * masterReportExt.writeAppraiseData
					 * (url,fileName1,personalAppralSelf
					 * ,personalAppralExtra,singleStudyAppral); }
					 */
					// 生成报告单数据
					AntZip.outPutStreamFileMethod(url, fileName2,
							new File(fUrl));
					File fileBGD = new File(url + File.separator + fileName2);
					// 文件是否存在
					if (fileBGD.exists()) {
						masterReportExt.writeReportExcel(fileBGD, sDto,
								masterAppral, outSinglePersonalAppraiseSelf,
								outSinglePersonalAppraiseExtra,
								outSingleStudyAppral, singleHealthData,
								sigleCheckItems, singleScore, "2", hkScore);
					}

					File file = null;
					/*
					 * if("1".equals(flag)){ fileList = new ArrayList<File>();
					 * file = new File(url+"班级"+File.separator+fileName1);
					 * fileList.add(file); }
					 */
					file = new File(url + File.separator + fileName2);
					fileList.add(file);

					/*
					 * if("1".equals(flag)){ //压缩评价，报告单 zipFileName =
					 * url+File.separator
					 * +sDto.getName()+"（"+sDto.getStudentno()+"）.zip";
					 * AntZip.createExcelZip(fileList, zipFileName);
					 * zipList.add(new File(zipFileName));
					 * AntZip.deleteFileList(fileList); }
					 */
				} catch (ManagerException me) {
					// 删除excel文件
					if (StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				} catch (Exception e) {
					// 删除excel文件
					if (StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				}
			}

			/*
			 * if("1".equals(flag)){ //压缩全部学生为完整文件
			 * AntZip.createExcelZip(zipList, zipName); //删除学生Zip文件
			 * AntZip.deleteFileList(zipList); }else{
			 */
			// 压缩全部学生为完整文件
			AntZip.createExcelZip(fileList, zipName);
			// 删除学生Zip文件
			AntZip.deleteFileList(fileList);
			// }
		}
	}

	/**
	 * 班级为单位导出
	 * 
	 * @throws ForceException
	 * */
	public Object exportZipByClass(HttpServletRequest req,
			HttpServletResponse res) throws ForceException {

		String zipName = "";
		String bigzipName = "";
		// Object [] obj=new Object[]{};
		// 班级classid数组
		redisServiceExt.save(dStatus, "0");
		try {
			List<File> zipList = new ArrayList<File>();
			if (null != classs) {
				for (int i = 0; i < classs.length; i++) {

					// 先查出来班主任id
					String bzrid = bookReportExt.getStudentBarByClasId(String
							.valueOf(classs[i]));
					params.put("techerid", bzrid);
					List<String> eduIdc = null;
					// 查询学生eduid 根据classid
					List<String> edus = bookReportExt.getStudenteduidByclassid(
							String.valueOf(classs[i]), userDto.getCampuseId(),userDto.getCmis30id(),userDto.getDiscode());
					this.putTempParam(String.valueOf(classs[i]));
					if (null != edus) {
						params.put("eduIds", edus);
					} else {
						params.put("eduIds", "-1");
					}
					params.put("levelcode", dqxueid);
					// 1.获取班主任老师班级信息
					List<CampusDto> teacherInfos = masterReportExt
							.queryTeacherInfos(params);
					if (null != teacherInfos && teacherInfos.size() > 0) {
						String templteUrl = NoServiceUtil
								.replaceFileSeparator(req.getRealPath("")
										+ constantBean.get("reportfilePath"));
						String url = templteUrl.substring(
								0,
								templteUrl.lastIndexOf(File.separator
										+ File.separator));
						bigzipName = this.getZipName(teacherInfos, url, flag);
						zipName = url + File.separator
								+ teacherInfos.get(0).getClassNum();// this.getZipName(teacherInfos,url,flag);
						List<StudentDto> studentBaseInfos = masterReportExt
								.queryStudentBaseInfos(params);
						List<File> fileList = this.initExportExcelData1(
								studentBaseInfos, templteUrl, url, zipName);

						zipName = url + File.separator
								+ teacherInfos.get(0).getClassNum() + "班"
								+ ".zip";
						AntZip.createExcelZip(fileList, zipName);
						zipList.add(new File(zipName));
						AntZip.deleteFileList(fileList);

					}

				}
			}
			// 压缩全部学生为完整文件
			AntZip.createExcelZip(zipList, bigzipName);
			// 删除学生Zip文件
			AntZip.deleteFileList(zipList);
			redisServiceExt.save(dStatus, "1");
			return exportExcel(res, new File(bigzipName), true);
		} catch (Exception e) {
			redisServiceExt.save(dStatus, "2");
			return exportExcel(res, new File(bigzipName), true);
		}
	}

	private List<File> initExportExcelData1(List<StudentDto> studentBaseInfos,
			String fUrl, String url, String zipName) throws ManagerException {
		List<File> zipList = new ArrayList<File>();
		// 全部学生
		List<File> fileList = new ArrayList<File>();
		if (null != studentBaseInfos && studentBaseInfos.size() > 0) {
			List<AppraisalDto> personalAppral = null;
			List<AppraisalDto> studyAppral = null;
			/*
			 * if("1".equals(flag)){ personalAppral =
			 * masterReportExt.queryPersonalAppral(params); studyAppral =
			 * masterReportExt.queryStudyAppral(params); }
			 */
			// 个性发展
			List<AppraisalDto> outPersonalAppralSelf = masterReportExt
					.queryOutPersonalAppral(params, "self");
			List<AppraisalDto> outPersonalAppralExtra = masterReportExt
					.queryOutPersonalAppral(params, "");
			// 研究性学习
			List<AppraisalDto> outStudyAppral = masterReportExt
					.queryOutStudyAppral(params);
			// 班主任评语
			List<AppraisalDto> masterAppraise = masterReportExt
					.queryMasterAppral(params);
			// 成绩
			List<ModelScoreDto> allScore = masterReportExt
					.queryAllScore(params);
			// 会考成绩
			Map<String, String> hkScore = masterReportExt.queryHKScore(params);
			// 体质健康数据
			List<HealthDataDto> healthDatas = masterReportExt
					.queryHealthDdatas(params);
			// 体检数据
			List<CheckItemInfoDto> checkItems = masterReportExt
					.queryCheckItems(params);

			for (StudentDto sDto : studentBaseInfos) {
				// 以学生为单位 生成zip文件名
				String zipFileName = "";
				try {
					// 文件名称
					String fileName1 = "";
					/*
					 * if("1".equals(flag)){ fileName1 =
					 * sDto.getName()+"（"+sDto.getStudentno()+"）评价数据.xls"; }
					 */
					String fileName2 = sDto.getName() + "（"
							+ sDto.getStudentno() + "）.xls";
					// 1.封面 OK
					List<AppraisalDto> personalAppralSelf = null;
					List<AppraisalDto> personalAppralExtra = null;
					List<AppraisalDto> singleStudyAppral = null;
					/*
					 * if("1".equals(flag)){ personalAppralSelf =
					 * masterReportExt
					 * .initSinglePersonalSelf(sDto,personalAppral,"selef");
					 * personalAppralExtra =
					 * masterReportExt.initSinglePersonalSelf
					 * (sDto,personalAppral,""); singleStudyAppral =
					 * masterReportExt.initsingleStudyAppral(sDto,studyAppral);
					 * }
					 */
					// 2.1个性发展 自我评价
					AppraisalDto outSinglePersonalAppraiseSelf = masterReportExt
							.initOutSinglePersonalAppraiseSelf(
									outPersonalAppralSelf, sDto);
					// 2.2个性发展 自我评价
					List<AppraisalDto> outSinglePersonalAppraiseExtra = masterReportExt
							.initOutSinglePersonalAppraiseExtra(
									outPersonalAppralExtra, sDto);
					// 3.评语
					List<AppraisalDto> masterAppral = masterReportExt
							.initSingleMasterAppraise(sDto, masterAppraise);
					// 4.成绩学分
					Map<String, Map<String, List<ModelScoreDto>>> singleScore = masterReportExt
							.initSingleScore(sDto, allScore);
					// 5.研究性学习
					List<AppraisalDto> outSingleStudyAppral = masterReportExt
							.initOutSinglePersonalAppraiseExtra(outStudyAppral,
									sDto);
					// 6.体质健康
					List<HealthDataDto> singleHealthData = masterReportExt
							.querySingleHealthData(sDto, healthDatas);
					// 7.健康体检
					List<CheckItemInfoDto> sigleCheckItems = masterReportExt
							.querySigleCheckItems(sDto, checkItems);
					/*
					 * //生成学生评价数据 if("1".equals(flag)){
					 * masterReportExt.writeAppraiseData
					 * (url,fileName1,personalAppralSelf
					 * ,personalAppralExtra,singleStudyAppral); }
					 */
					// 生成报告单数据
					AntZip.outPutStreamFileMethod(url, fileName2,
							new File(fUrl));
					File fileBGD = new File(url + File.separator + fileName2);
					// 文件是否存在
					if (fileBGD.exists()) {
						masterReportExt.writeReportExcel(fileBGD, sDto,
								masterAppral, outSinglePersonalAppraiseSelf,
								outSinglePersonalAppraiseExtra,
								outSingleStudyAppral, singleHealthData,
								sigleCheckItems, singleScore, flag, hkScore);
					}

					File file = null;
					/*
					 * if("1".equals(flag)){ fileList = new ArrayList<File>();
					 * file = new File(url+"班级"+File.separator+fileName1);
					 * fileList.add(file); }
					 */
					file = new File(url + File.separator + fileName2);
					fileList.add(file);

					/*
					 * if("1".equals(flag)){ //压缩评价，报告单 zipFileName =
					 * url+File.separator
					 * +sDto.getName()+"（"+sDto.getStudentno()+"）.zip";
					 * AntZip.createExcelZip(fileList, zipFileName);
					 * zipList.add(new File(zipFileName));
					 * AntZip.deleteFileList(fileList); }
					 */
				} catch (ManagerException me) {
					// 删除excel文件
					if (StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				} catch (Exception e) {
					// 删除excel文件
					if (StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				}
			}

		}
		return fileList;
	}

	// 校验成绩
	public void initSingleScore(StudentDto sDto, List<ModelScoreDto> allScore,
			Report rr) {
		Sreportstatus st = new Sreportstatus();
		st.setReportid(rr.getWaterid());
		st.setVerfifyresult("X");
		double zong = 0; // 总学分 144
		double bixiu = 0;// 必修 116
		double xuanxiu = 0; // 选修 22
		double xiaoben = 0; // 校本6
		if (null != allScore && allScore.size() > 0) {
			for (ModelScoreDto model : allScore) {
				if (NestUtil.isNotEmpty(sDto.getEduid())
						&& sDto.getEduid().equals(model.getEdu_id())) {
					if (null != model.getCourse_kind()
							&& null != model.getCredit_hour()) {
						switch (Integer.valueOf(model.getCourse_kind())) {
						case 1230320: // 校本课程
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								xiaoben = xiaoben
										+ Double.valueOf(model.getCredit_hour());
							} else {
								st.setVerfifyresult("缺");
								st.setVerfify("10001"); // 校本为空
								bookReportExt.insertSreportstatusInfo(st);
							}

							break;
						case 1230301: // 必修
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								bixiu = bixiu
										+ Double.valueOf(model.getCredit_hour());
							} else {
								st.setVerfifyresult("缺");
								st.setVerfify("10002"); // 必修为空
								bookReportExt.insertSreportstatusInfo(st);
							}
							break;
						case 1230315: // 选修
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								xuanxiu = xuanxiu
										+ Double.valueOf(model.getCredit_hour());
							} else {
								st.setVerfifyresult("缺");
								st.setVerfify("10003"); // 选修
								bookReportExt.insertSreportstatusInfo(st);
							}
							break;
						case 1230310: // 必选和必修累加
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								bixiu = bixiu
										+ Double.valueOf(model.getCredit_hour());
							} else {
								st.setVerfifyresult("缺");
								st.setVerfify("10002"); // 必修为空
								bookReportExt.insertSreportstatusInfo(st);
							}
						}

					}

				}
			}

			zong = zong + bixiu + xuanxiu + xiaoben;
			if (zong == 0) {
				st.setVerfifyresult("缺");
				st.setVerfify("10004"); // 选修
				bookReportExt.insertSreportstatusInfo(st);

				st.setVerfifyresult("缺");
				st.setVerfify("10003"); // 选修
				bookReportExt.insertSreportstatusInfo(st);

				st.setVerfifyresult("缺");
				st.setVerfify("10002"); // 必修为空
				bookReportExt.insertSreportstatusInfo(st);

				st.setVerfifyresult("缺");
				st.setVerfify("10001"); // 校本为空
				bookReportExt.insertSreportstatusInfo(st);
			}
			if (zong < 144) {
				st.setVerfify("1301210"); // 总学分
				bookReportExt.insertSreportstatusInfo(st);
			} else {
				bookReportExt.deleteReportItme(rr.getWaterid(), "1301210");
			}

			if (bixiu < 166) {
				st.setVerfify("1301207"); // 必修
				bookReportExt.insertSreportstatusInfo(st);
			} else {
				bookReportExt.deleteReportItme(rr.getWaterid(), "1301207");
			}

			if (xuanxiu < 22) {
				st.setVerfify("1301208"); // 选修
				bookReportExt.insertSreportstatusInfo(st);
			} else {
				bookReportExt.deleteReportItme(rr.getWaterid(), "1301208");
			}

			if (xiaoben < 6) {
				st.setVerfify("1301209"); // 校本
				bookReportExt.insertSreportstatusInfo(st);
			} else {
				bookReportExt.deleteReportItme(rr.getWaterid(), "1301209");
			}
		} else {
			st.setVerfifyresult("X");
			st.setVerfify("1301210"); // 总学分
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("X");
			st.setVerfify("1301207"); // 总学分
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("X");
			st.setVerfify("1301208"); // 总学分
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("X");
			st.setVerfify("1301209"); // 总学分
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("缺");
			st.setVerfify("10004"); // 选修
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("缺");
			st.setVerfify("10003"); // 选修
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("缺");
			st.setVerfify("10002"); // 必修为空
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("缺");
			st.setVerfify("10001"); // 校本为空
			bookReportExt.insertSreportstatusInfo(st);
		}
	}

	/**
	 * 校验会考成绩
	 * */
	private void huikaochengji(Map<String, String> huikao, StudentDto sDto,
			Report rr) {
		// 包括会考成绩的bean
		Sreportstatus st = new Sreportstatus();
		st.setReportid(rr.getWaterid());
		String xueke = "语文,英语,历史,地理,数学,物理,化学,生物,";
		StringBuffer sb = new StringBuffer();
		String newxueke = null;
		List<ModelScoreDto> msDto = new ArrayList<ModelScoreDto>();
		if (null != huikao && huikao.size() > 0) {
			Set<String> key = huikao.keySet();
			Iterator it = key.iterator();
			while (it.hasNext()) {
				ModelScoreDto m = new ModelScoreDto();
				String str = (String) it.next();
				String[] ss = str.split("_");
				String val = huikao.get(str);
				if (null != ss && ss.length == 2) {
					m.setEdu_id(ss[0]); // 用户id
					m.setNzOrXbSub(ss[1]); // 学科
					m.setHkScore(val);
					msDto.add(m);
				}

			}

			int num = 0;
			if (null != msDto && msDto.size() > 0) {
				for (ModelScoreDto model : msDto) {
					if (NestUtil.isNotEmpty(sDto.getEduid())
							&& sDto.getEduid().equals(model.getEdu_id())) {
						/**
						 * 校验开始
						 * */

						if (model.getNzOrXbSub().equals("语文")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									// newxueke=xueke.replace("语文,", "");
									sb.append("语文");
								}
							}

						} else if (model.getNzOrXbSub().equals("英语")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									newxueke = xueke.replace("英语,", "");
									sb.append("英语");
								}
							}
						} else if (model.getNzOrXbSub().equals("历史")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("历史");
								}
							}
						} else if (model.getNzOrXbSub().equals("地理")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("地理");
								}
							}
						} else if (model.getNzOrXbSub().equals("数学")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("数学");
								}
							}
						} else if (model.getNzOrXbSub().equals("物理")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("物理");
								}
							}
						} else if (model.getNzOrXbSub().equals("化学")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("化学");
								}
							}
						} else if (model.getNzOrXbSub().equals("生物")) {
							if (null != model.getHkScore()) {
								if (model.getHkScore().indexOf("A") != -1
										|| model.getHkScore().indexOf("B") != -1
										|| model.getHkScore().indexOf("C") != -1
										|| model.getHkScore().indexOf("D") != -1) {
									num++;
									sb.append("生物");
								}
							}
						}

					}
				}
				if (sb.toString().equals("")) {
					st.setVerfify("1301222"); // 会考成绩非ABCD 1301221
					st.setVerfifyresult(xueke);
					bookReportExt.insertSreportstatusInfo(st);

					st.setVerfifyresult("缺");
					st.setVerfify("1301221"); // 内容1
					bookReportExt.insertSreportstatusInfo(st);
					bookReportExt.deleteReportItme(rr.getWaterid(), "1301221");

				} else {
					st.setVerfify("1301222"); // 会考成绩非ABCD 1301221
					st.setVerfifyresult(sb.toString());
					bookReportExt.insertSreportstatusInfo(st);

					st.setVerfifyresult("缺");
					st.setVerfify("1301221"); // 内容1
					bookReportExt.insertSreportstatusInfo(st);
					bookReportExt.deleteReportItme(rr.getWaterid(), "1301221");
				}

				if (num == 8) {
					bookReportExt.deleteReportItme(rr.getWaterid(), "1301222");
					bookReportExt.deleteReportItme(rr.getWaterid(), "1301221");
				}

			}
		} else {
			st.setVerfify("1301222"); // 会考成绩非ABCD 1301221
			st.setVerfifyresult(xueke);
			bookReportExt.insertSreportstatusInfo(st);

			st.setVerfifyresult("缺");
			st.setVerfify("1301221"); // 内容1
			bookReportExt.insertSreportstatusInfo(st);
		}

	}

	@Json
	public Object queryDownLoadStatus() {
		try {
			String status = redisServiceExt.readSingle(dStatus);
			if ("1".equals(status) || "2".equals(status)) {
				redisServiceExt.delete(dStatus);
			}
			return JSONObject.fromObject("{val:" + status + "}");
		} catch (ForceException e) {
			return JSONObject.fromObject("{val:" + 1 + "}");
		}
	}
}
