package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.Conditions;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.Report;
import com.flyrish.hades.dto.Reportstatus;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.Sreportstatus;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.Xueduan;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBookReportExt;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.util.DataSource;

public class BookReportExtImpl extends JdbcRootManager implements
		IBookReportExt {

	/**
	 * 查询学段信息
	 * */
	@DataSource("read")
	public List<Xueduan> getXueduan(String campuseId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("campuseId", campuseId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getXueduan.query");
			return this.findList("BookReportExt.getXueduan.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Xueduan con = new Xueduan();
							con.setXueduan(rs.getString("levelname"));
							con.setXueduanid(rs.getString("levelcode"));
							return con;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getSchoolYears()", e);
		}
		return null;
	}

	/**
	 * 查询界别列表
	 * */
	@DataSource("read")
	public List<Conditions> getJiebie(String campuseId, String code) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("campuseId", campuseId);
			params.put("code", code);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getJiebie.query");
			return this.findList("BookReportExt.getJiebie.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Conditions con = new Conditions();
							con.setJiebie(rs.getString("years"));
							con.setJiebieid(rs.getString("gradeid"));
							return con;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getJiebie()", e);
		}
		return null;
	}

	/**
	 * 根据条件查询年级对应的班级
	 * */
	@DataSource("read")
	public List<ClassDto> getBGCBJList(String campuseId, String gradeid,
			String levelcode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("campuseId", campuseId);
			params.put("gradeid", gradeid);
			params.put("levelcode", levelcode);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getBGCBJList.query");
			return this.findList("BookReportExt.getBGCBJList.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							ClassDto clas = new ClassDto();
							clas.setClassId(rs.getString("CLASSID")); // 班级id
							clas.setClassName(rs.getString("CLASSSNAME"));// 班级名字
							clas.setXueduan(rs.getString("xueduan")); // 学段
							clas.setJiebie(rs.getString("levelname")); // 界别
							clas.setTotalCount(Integer.parseInt(rs
									.getString("total"))); // 班级总人数
							clas.setReportedCount(Integer.parseInt(rs
									.getString("reported"))); // 已上报人数
							clas.setNoreportCount(Integer.parseInt(rs
									.getString("unreported"))); // 未上报人数
							clas.setVerficationFailedCount(Integer.parseInt(rs
									.getString("failed"))); // 校验失败人数
							clas.setVerficationSuccessCount(Integer.parseInt(rs
									.getString("success"))); // 校验成功人数
							return clas;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getBGCBJList()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<String> getStudenteduidByclassidSB(String classid,
			String campuseId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("campuseId", campuseId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudenteduidByclassidSB.query");
			return this.findList(
					"BookReportExt.getStudenteduidByclassidSB.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							return rs.getString("edu_id");
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudentByClasssIdSB()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<ClassDto> getClasssById(Integer cmis30id) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getClasssById.query");
			return this.findList("BookReportExt.getClasssById.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							ClassDto cdto = new ClassDto();
							cdto.setClassId(rs.getString("classid"));
							cdto.setClassName(rs.getString("masterid"));
							cdto.setJiebie(rs.getString("campusid"));
							cdto.setTotalCount(rs.getInt("discode"));
							cdto.setXueduan(rs.getString("schoolname"));
							cdto.setClassnum(rs.getString("classnum"));

							return cdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getClasssById()", e);
		}
		return null;
	}

	/**
	 * 查询非京畿
	 * */
	@DataSource("read")
	public List<StudentDto> getStudentByClasssId(String classid,
			String campuseId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("campuseId", campuseId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudentByClassId.query");
			return this.findList("BookReportExt.getStudentByClassId.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto stu = new StudentDto();
							stu.setStudentid(rs.getString("studentid")); // 学生id
							stu.setLevelid(rs.getInt("levelid")); // 学区
							stu.setName(rs.getString("name"));
							stu.setStudentno(rs.getString("studentno"));
							stu.setEduid(rs.getString("edu_id"));
							return stu;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudentByClasssId()", e);
		}
		return null;
	}

	/**
	 * 查询北京籍
	 * */
	@DataSource("read")
	public List<StudentDto> getStudentByClasssIdAndCode(String classid,
			String campuseId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("campuseId", campuseId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudentByClasssIdAndCode.query");
			return this.findList(
					"BookReportExt.getStudentByClasssIdAndCode.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto stu = new StudentDto();
							stu.setStudentid(rs.getString("studentid")); // 学生id
							stu.setLevelid(rs.getInt("levelid")); // 学区
							stu.setName(rs.getString("name"));
							stu.setStudentno(rs.getString("studentno"));
							stu.setEduid(rs.getString("edu_id"));
							return stu;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudentByClasssId()", e);
		}
		return null;
	}

	/**
	 * 是否上报
	 * */
	@DataSource("read")
	public List<Report> getRetortById(String studentid) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("studentid", studentid);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getRetortById.query");
			return this.findList("BookReportExt.getRetortById.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Report r = new Report();
							r.setWaterid(rs.getString("waterid"));
							return r;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getRetortById()", e);
		}
		return null;
	}

	/**
	 * 是否有数据
	 * */
	@DataSource("read")
	public List<Report> isRetortById(String studentid) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("studentid", studentid);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.isRetortById.query");
			return this.findList("BookReportExt.isRetortById.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Report r = new Report();
							r.setReportstatus(rs.getString("REPORT_STATUS"));
							r.setCheckstate(rs.getString("CHECKSTATE"));
							r.setWaterid(rs.getString("waterid"));
							return r;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getRetortById()", e);
		}
		return null;
	}

	/**
	 * 是否有数据
	 * */
	@DataSource("read")
	public Map<String, Report> isRetortById(List<String> studentids) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("studentids", studentids);
			final Map<String, Report> dat = new HashMap<String, Report>();
			this.findList("BookReportExt.isRetortById.query1", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Report r = new Report();
							r.setReportstatus(rs.getString("REPORT_STATUS"));
							r.setCheckstate(rs.getString("CHECKSTATE"));
							r.setWaterid(rs.getString("waterid"));
							r.setPid(rs.getString("p_id"));
							dat.put(r.getPid(), r);
							return null;
						}
					});
			return dat;
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getRetortById(List<String>)", e);
		}
		return null;
	}

	// throw new ManagerException(e);
	/**
	 * 学生上报
	 * */
	public void insertReportInfo(Report report) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("waterid",report.getWaterid());
			params.put("pid", report.getPid());
			params.put("termid", report.getTermid());
			params.put("reportstatus", report.getReportstatus());
			params.put("used", report.getUsed());
			params.put("reporttime", report.getReporttime());
			params.put("eduid", report.getEduid());
			// report
			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.insertReportInfo.insert");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("insertCourseInfos(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	@DataSource("read")
	public List<StudentDto> getStudentByStuId(String campuseId, String stuId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("campuseId", campuseId);
			params.put("stuId", stuId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudentByStuId.query");
			return this.findList("BookReportExt.getStudentByStuId.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto stu = new StudentDto();
							stu.setStudentid(rs.getString("studentid")); // 学生id
							stu.setLevelid(rs.getInt("levelid")); // 学区
							stu.setName(rs.getString("name"));
							stu.setStudentno(rs.getString("studentno"));
							stu.setEduid(rs.getString("edu_id"));
							return stu;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudentByStuId()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<StudentDto> getStudentByid(Map<String, Object> params) {
		try {
			return this.findList("BookReportExt.getStudentByid.query1", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto sDto = new StudentDto();
							sDto.setStudentid(rs.getString("studentid"));
							sDto.setEduid(rs.getString("edu_id"));
							sDto.setStudentno(rs.getString("studentno"));
							sDto.setName(rs.getString("studentName"));
							sDto.setSexname(rs.getString("sex"));
							sDto.setAge(NestUtil.isNotEmpty(rs.getString("age")) ? Integer
									.parseInt(rs.getString("age")) : 0);
							sDto.setEasyName(rs.getString("polityName"));
							sDto.setGradenum(rs.getString("gradename"));
							sDto.setClassname(rs.getString("classsname"));
							sDto.setSchoolName(rs.getString("schoolname"));
							sDto.setInschoolid(rs.getString("nummber"));
							return sDto;
						}
					});
		} catch (Exception e) {
			logger.error("getStudentByid(Map<String,Object>)", e);
		}
		return null;
	}

	@DataSource("read")
	public List<AppraisalDto> getOutputselfByclassid(List<String> eduids) {
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("eduIds", eduids);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getOutputselfByclassid.query");
			return this.findList("BookReportExt.getOutputselfByclassid.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setApprasial(rs.getString("description"));
							dto.setStudentid(rs.getString("studentid"));
							return dto;
						}
					});

		} catch (Exception e) {
			logger.error("BookReportExtImpl.getOutputselfByclassid", e);
		}
		return null;
	}

	public List<AppraisalDto> getOutputstrongByclassid(List<String> eduids) {

		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("eduIds", eduids);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getOutputstrongByclassid.query");
			return this.findList(
					"BookReportExt.getOutputstrongByclassid.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setOrderby(Integer.valueOf(rs
									.getString("orderby")));
							dto.setApprasial(rs.getString("description"));
							dto.setStudentid(rs.getString("studentid"));
							dto.setEduid(rs.getString("eduid"));
							return dto;
						}
					});

		} catch (Exception e) {
			logger.error("BookReportExtImpl.getOutputselfByclassid", e);
		}
		return null;
	}

	public List<AppraisalDto> queryMasterAppral(Map<String, Object> params) {
		try {
			// 班主任评语信息
			return this.findList("BookReportExtImpl.queryMasterAppral.query1",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setApprasial(rs.getString("assesscontent"));
							dto.setEduid(rs.getString("edu_id"));
							dto.setGradeNum(rs.getString("gradenum"));
							dto.setTermType(rs.getString("term"));
							dto.setStudentid(rs.getString("studentid"));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(
					"IMasterReportExt.queryMasterAppral(Map<String,Object>)", e);
		}
		return null;
	}

	// 研究性学习
	public List<AppraisalDto> queryOutStudyAppral(Map<String, Object> params) {
		try {
			// 研究性学习(输出数据)
			return this.findList("BookReportExtImpl.queryOutStudyAppral.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							AppraisalDto dto = new AppraisalDto();
							dto.setEduid(rs.getString("eduid"));
							dto.setApprasial(rs.getString("description"));
							dto.setTopic(rs.getString("title"));
							dto.setStudentid(rs.getString("studentid"));
							dto.setOrderby(Integer.valueOf(rs
									.getString("orderby")));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(
					"IMasterReportExt.queryOutStudyAppral(Map<String, Object>)",
					e);
		}
		return null;
	}

	// 体质健康
	public List<HealthDataDto> queryHealthDdatas(Map<String, Object> params) {
		try {
			// 研究性学习(输出数据)
			return this.findList("BookReportExt.queryHealthDdatas.query1",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							HealthDataDto dto = new HealthDataDto();
							dto.setGradeNum(rs.getString("gradenum"));
							dto.setGradeName(rs.getString("gradename"));
							dto.setEduId(rs.getString("edu_id"));
							dto.setName(rs.getString("name"));
							dto.setRewardScore(rs.getString("reward_score"));
							dto.setYearScore(rs.getString("year_score"));
							dto.setYearGrade(rs.getString("year_grade"));
							dto.setAllContent(rs.getString("dataDetails"));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(
					"IMasterReportExt.queryHealthDdatas(Map<String, Object>)",
					e);
		}
		return null;
	}

	// 体检信息
	public List<CheckItemInfoDto> queryCheckItems(Map<String, Object> params) {
		try {
			// 研究性学习(输出数据)
			return this.findList("BookReportExt.queryCheckItems.query1",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							CheckItemInfoDto dto = new CheckItemInfoDto();
							dto.setEduId(rs.getString("edu_id"));
							dto.setTermId(rs.getString("termId"));
							dto.setItemResultJg(rs.getString("unit"));
							dto.setItemResult(rs.getString("itemresult"));
							dto.setResultStr(rs.getString("result"));
							dto.setSubItemSort(rs.getString("subitemsort"));
							dto.setItemResultBs(rs.getString("medicalrecord"));
							return dto;
						}
					});
		} catch (Exception e) {
			logger.error(
					"IMasterReportExt.queryCheckItems(Map<String, Object>)", e);
		}
		return null;
	}

	@Override
	public void insertSreportstatusInfo(Sreportstatus reportstatus) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("waterid",report.getWaterid());
			params.put("reportid", reportstatus.getReportid());
			params.put("verfify", reportstatus.getVerfify());
			params.put("verfifyresult", reportstatus.getVerfifyresult());
			// report
			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.insertSreportstatusInfo.insert");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("insertCourseInfos(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}

	}

	/**
	 * 更新状态表状态
	 * */
	public void updateSreportByid(String pk, String zhuangtai) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("waterid",report.getWaterid());
			params.put("pk", pk);
			params.put("zhuangtai", zhuangtai);
			// report
			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.updateSreportInfo.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("insertCourseInfos(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	// 创建报告册校验
	public void insertReportjyInfo(Report report) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("waterid",report.getWaterid());
			params.put("pid", report.getPid());
			params.put("termid", report.getTermid());
			params.put("reportstatus", report.getReportstatus());
			params.put("used", report.getUsed());
			params.put("reporttime", report.getReporttime());
			params.put("eduid", report.getEduid());
			params.put("checkstate", report.getCheckstate());
			// report
			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.insertReportjyInfo.insert");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("insertReportjyInfo(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	/**
	 * 校验错误人数
	 * */
	@DataSource("read")
	public List<StudentDto> getStudenterrorlist(String classid) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classid", classid);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.geterrorlist.query");
			return this.findList("BookReportExt.geterrorlist.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto stu = new StudentDto();
							stu.setStudentid(rs.getString("studentid"));
							stu.setStudentno(rs.getString("studentno"));
							stu.setName(rs.getString("name"));
							stu.setEduid(rs.getString("edu_id"));
							return stu;
						}
					});

		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudenterrorlist", e);
		}
		return null;
	}

	/**
	 * 取出具体项
	 * */
	@DataSource("read")
	public List<Reportstatus> getStudenterrorItmeBystuId(String studentid) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("studentid", studentid);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudenterrorItmeBystuId.query");
			return this.findList(
					"BookReportExt.getStudenterrorItmeBystuId.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Reportstatus rep = new Reportstatus();
							rep.setVerfifykind(rs.getString("abc"));
							rep.setVerfifyresult(rs.getString("verfify_result"));
							return rep;
						}
					});

		} catch (Exception e) {
			logger.error("BookReportExtImpl.getStudenterrorItmeBystuId", e);
		}

		return null;
	}

	@Override
	public void deleteReportItme(String waterid, String key) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("waterid", waterid);
			params.put("key", key);

			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.deleteReportItme.delete");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("deleteReportItme(Map<String, Object>)", e);
			throw new ManagerException(e);
		}

	}

	// 跟新已上报
	public void updateReportinof(String waterid, String zhuangtai) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// params.put("waterid",report.getWaterid());
			params.put("pk", waterid);
			params.put("zhuangtai", zhuangtai);
			// report
			ISqlElement sqlElement = this.processSql(params,
					"BookReportExt.updateReportinof.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),
					sqlElement.getParams());

		} catch (Exception e) {
			logger.error("updateReportinof(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	@DataSource("read")
	public String getStudentBarByClasId(String classId) {
		List<String> s = null;
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classId", classId);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getStudentBarByClasId.query");
			s = this.findList("BookReportExt.getStudentBarByClasId.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							return rs.getString("masterid");
						}
					});

		} catch (Exception e) {
			logger.error("BookReportExtImpl.getOutputselfByclassid", e);
		}
		return s.get(0);
	}

	@DataSource("read")
	public List<String> getStudenteduidByclassid(String classid,
			String campuseId, String cmis30id, String discode) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("campuseId", campuseId);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			return this.findList(
					"BookReportExt.getStudenteduidByclassid.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							return rs.getString("edu_id");
						}
					});
		} catch (Exception e) {
			logger.error(
					"BookReportExtImpl.getStudentByClasssId(String,String,String,String)",
					e);
		}
		return null;
	}

	public List<String> getStudenteduidByclassid(int[] classids,
			String campuseId, String cmis30id, String discode) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (classids == null || classids.length < 1) {
				classids = new int[1];
				classids[0] = -1;
			}
			List<String> classidStrs = new ArrayList<String>();
			for (int i : classids)
				classidStrs.add(i + "");
			params.put("classids", classidStrs);
			params.put("campuseId", campuseId);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			return this.findList(
					"BookReportExt.getStudenteduidByclassid.query1", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							return rs.getString("edu_id");
						}
					});
		} catch (Exception e) {
			logger.error(
					"BookReportExtImpl.getStudentByClasssId(String,String,String,String)",
					e);
		}
		return null;
	}

	/**
	 * 获取成绩
	 * */
	public List<ModelScoreDto> queryAllScore(Map<String, Object> params) {
		try {
			return this.findList("BookReportExt.queryAllScore.query1", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							ModelScoreDto msDto = new ModelScoreDto();
							msDto.setEdu_id(rs.getString("edu_id"));
							msDto.setTermId(rs.getString("gradenum"));
							msDto.setXbXDname(rs.getString("name"));
							msDto.setXuhao(rs.getString("code"));
							msDto.setNzOrXbSub(rs.getString("subject_name"));
							msDto.setCourse_name(rs.getString("course_name"));
							msDto.setCourse_kind(rs.getString("course_kind"));
							msDto.setNzScore(rs.getString("examine_result"));
							msDto.setHkScore(rs.getString("level_name"));
							msDto.setCredit_hour(rs.getString("credit_hour"));
							msDto.setStudentid(rs.getString("studentid"));
							return msDto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.queryAllScore(Map<String,Object>)",
					e);
		}
		return null;
	}

	/**
	 * 校验详细方法
	 * */
	public void jiaoyao(String classid, UserDto userDto) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		params.put("techerid", userDto.getTeacherid());

		// eduid集合
		List<String> eduids = new ArrayList<String>();
		// 根据班级id查询班级所有uuid
		eduids = this.getStudenteduidByclassid(classid, userDto.getCampuseId(),
				userDto.getCmis30id(), userDto.getDiscode());
		// 自我评价 一条
		List<AppraisalDto> ziwopingjia = this.getOutputselfByclassid(eduids);
		// 取成果展示信息
		List<AppraisalDto> chengg = this.getOutputstrongByclassid(eduids);
		// 班主任评语
		List<AppraisalDto> banpingyu = this.queryMasterAppral(params);
		// 研究性学习
		List<AppraisalDto> yanjxxx = this.queryOutStudyAppral(params);
		// 体质健康
		List<HealthDataDto> tzjk = this.queryHealthDdatas(params);
		// 体检信息
		List<CheckItemInfoDto> tjxx = this.queryCheckItems(params);
		// 成绩
		List<ModelScoreDto> chengji = this.queryAllScore(params);
	}

	public void addAll(List<Sreportstatus> sb) {
		try {
			if (null != sb || sb.size() > 0) {
				for (Sreportstatus sr : sb) {
					this.insertSreportstatusInfo(sr);
				}
			}

		} catch (Exception e) {
			logger.error("insertCourseInfos(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	public void deleteAll(List<Sreportstatus> sc) {
		try {
			if (null != sc || sc.size() > 0) {
				for (Sreportstatus sr : sc) {
					this.deleteReportItme(sr.getWaterid(), sr.getVerfify());
				}
			}

		} catch (Exception e) {
			logger.error("insertCourseInfos(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}

	// 校验该学生是否通过
	@DataSource("read")
	public List<Sreportstatus> getSreportBystuId(String waterid) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("waterid", waterid);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getSreportBystuId.query");
			return this.findList("BookReportExt.getSreportBystuId.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Sreportstatus s = new Sreportstatus();
							s.setWaterid(rs.getString("WATERID"));
							s.setVerfify(rs.getString("VERFIFY_KIND"));
							return s;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getSreportBystuId()", e);
		}
		return null;
	}

	// 校验该学生是否通过
	public Map<String, Sreportstatus> getSreportBystuId(
			Collection<Report> reports) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<String> waterids = new ArrayList<String>();
			if (reports == null || reports.size() < 1)
				waterids.add("-1");
			for (Report rp : reports) {
				waterids.add(rp.getWaterid());
			}
			params.put("waterids", waterids);
			final Map<String, Sreportstatus> dt = new HashMap<String, Sreportstatus>();
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getSreportBystuId.query1");
			System.out.println(sqlDemo.getSql());
			this.findList("BookReportExt.getSreportBystuId.query1", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							Sreportstatus s = new Sreportstatus();
							s.setWaterid(rs.getString("WATERID"));
							s.setReportid(rs.getString("report_id"));
							s.setVerfify(rs.getString("VERFIFY_KIND"));
							dt.put(rs.getString("report_id"), s);
							return null;
						}
					});
			return dt;
		} catch (Exception e) {
			logger.error("getSreportBystuId(List<String>)", e);
		}
		return null;
	}

	private Map<String, Object> generateInsertMapObj(Sreportstatus st) {
		if (st == null)
			return null;
		Map<String, Object> mapdat = new HashMap<String, Object>();
		mapdat.put("reportid", st.getReportid());
		mapdat.put("verfify", st.getVerfify());
		mapdat.put("verfifyresult", st.getVerfifyresult());
		return mapdat;
	}

	private Map<String, Object> generateDelMapObj(String waterid,
			String stateCode) {
		Map<String, Object> mapdat = new HashMap<String, Object>();
		mapdat.put("REPORT_ID", waterid);
		mapdat.put("VERFIFY_KIND", stateCode);

		return mapdat;
	}

	private void checkDataIsOkForEveryStudent(
			List<Map<String, Object>> insertdatas,
			List<Map<String, Object>> deletedatas, StudentDto dto,
			Report report, Sreportstatus st, List<AppraisalDto> ziwopingjia,
			List<AppraisalDto> chengg, List<AppraisalDto> banpingyu,
			List<AppraisalDto> yanjxxx, List<HealthDataDto> tzjk,
			List<CheckItemInfoDto> tjxx, List<ModelScoreDto> chengji,
			IMasterReportExt masterReportExt, Map<String, String> hkScore) {
		// 班级所有成果展示
		List<AppraisalDto> appraiscgList = new ArrayList<AppraisalDto>();

		// 班级所有研究性学习
		List<AppraisalDto> xuexiList = new ArrayList<AppraisalDto>();

		// 班级所有评语信息
		List<AppraisalDto> apppingyuList = new ArrayList<AppraisalDto>();
		// 体检信息
		List<CheckItemInfoDto> tijianxinx = new ArrayList<CheckItemInfoDto>();
		// 给报告册外键赋值
		String waterid = report.getWaterid();
		st.setReportid(waterid);
		// 验证学生名字
		if (NestUtil.isEmpty(dto.getName())) {
			st.setVerfify("1301398");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301398"));
		}
		// 学校名字
		if (NestUtil.isEmpty(dto.getSchoolName())) {
			st.setVerfify("1301399");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301399"));
		}
		// 年龄
		if (dto.getAge() == null || dto.getAge() == 0) {
			st.setVerfify("1301396");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301396"));
		}
		// 性别
		if (NestUtil.isEmpty(dto.getSexname())) {
			st.setVerfify("1301397");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301397"));
		}
		// 班级
		if (NestUtil.isEmpty(dto.getClassname())) {
			st.setVerfify("1301395");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301395"));
		}
		// 学籍号
		if (NestUtil.isEmpty(dto.getStudentno())) {
			st.setVerfify("1301394");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			deletedatas.add(generateDelMapObj(waterid, "1301394"));
		}
		AppraisalDto appraispj = masterReportExt
				.initOutSinglePersonalAppraiseSelf(ziwopingjia, dto);
		if (null == appraispj) {
			st.setVerfify("1301307");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			if (NestUtil.isEmpty(appraispj.getApprasial())) {
				st.setVerfify("1301307");
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(waterid, "1301307"));// 删除状态评价
			}
		}
		// 取成果展示信息
		appraiscgList = masterReportExt.initOutSinglePersonalAppraiseExtra(
				chengg, dto);
		if (null == appraiscgList || appraiscgList.size() == 0) {
			st.setVerfify("1301308");
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301309");
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301310");
			insertdatas.add(generateInsertMapObj(st));
		} else {
			for (AppraisalDto acg : appraiscgList) {
				if (acg.getOrderby() != null && acg.getOrderby() != 0) {
					if (acg.getOrderby() == 1) {
						if (NestUtil.isEmpty(acg.getApprasial())) {
							st.setVerfify("1301308");
							insertdatas.add(generateInsertMapObj(st));
						} else {
							deletedatas.add(generateDelMapObj(waterid,
									"1301308"));// 删除成果1
						}
					} else if (acg.getOrderby() == 2) {
						if (NestUtil.isEmpty(acg.getApprasial())) {
							st.setVerfify("1301309");
							insertdatas.add(generateInsertMapObj(st));
						} else {
							deletedatas.add(generateDelMapObj(waterid,
									"1301309"));// 删除成果2
						}
					} else if (acg.getOrderby() == 3) {
						if (NestUtil.isEmpty(acg.getApprasial())) {
							st.setVerfify("1301310");
							insertdatas.add(generateInsertMapObj(st));
						} else {
							deletedatas.add(generateDelMapObj(waterid,
									"1301310"));// 删除成果3
						}
					}
				}
			}
		}
		// 3.评语 //3检验 评语表 开始
		apppingyuList = masterReportExt
				.initSingleMasterAppraise(dto, banpingyu);
		if (null == apppingyuList || apppingyuList.size() == 0) {
			st.setVerfify("1301311"); // 高一
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301312"); // 高二
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301313"); // 高三
			insertdatas.add(generateInsertMapObj(st));
		} else {
			st.setVerfify("1301311"); // 高一
			insertdatas.add(generateInsertMapObj(st));
			st.setVerfify("1301312"); // 高二
			insertdatas.add(generateInsertMapObj(st));
			st.setVerfify("1301313"); // 高三
			insertdatas.add(generateInsertMapObj(st));

			for (AppraisalDto app : apppingyuList) {
				if (app.getGradeNum().equals("1")) { // 高一
					if (!(NestUtil.isEmpty(app.getApprasial()))) {
						deletedatas.add(generateDelMapObj(waterid, "1301311"));
					}
				} else if (app.getGradeNum().equals("2")) { // 高二
					if (!(NestUtil.isEmpty(app.getApprasial()))) {
						deletedatas.add(generateDelMapObj(waterid, "1301312"));
					}
				} else if (app.getGradeNum().equals("3")) { // 高三
					if (!(NestUtil.isEmpty(app.getApprasial()))) {
						deletedatas.add(generateDelMapObj(waterid, "1301313"));
					}
				}
			}
		}

		// 5.研究性学习
		xuexiList = masterReportExt.initOutSinglePersonalAppraiseExtra(yanjxxx,
				dto);

		if (null == xuexiList || xuexiList.size() == 0) {
			st.setVerfify("1301315"); // 内容1
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301314"); // 标题1
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301317"); // 内容2
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301316"); // 标题2
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301319"); // 内容3
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301318"); // 标题3
			insertdatas.add(generateInsertMapObj(st));
		} else {

			st.setVerfify("1301315"); // 内容1
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301314"); // 标题1
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301317"); // 内容2
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301316"); // 标题2
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301319"); // 内容3
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfify("1301318"); // 标题3
			insertdatas.add(generateInsertMapObj(st));
			if (xuexiList.size() == 1) {
				AppraisalDto app = xuexiList.get(0);
				if (app.getOrderby() == 1) {
					// 如果是1，则删除1，3
					if (NestUtil.isNotEmpty(app.getTopic())) {
						deletedatas.add(generateDelMapObj(waterid, "1301314"));
					}
					if (NestUtil.isNotEmpty(app.getApprasial())) {
						deletedatas.add(generateDelMapObj(waterid, "1301315"));
					}
					deletedatas.add(generateDelMapObj(waterid, "1301318"));
					deletedatas.add(generateDelMapObj(waterid, "1301319"));

				} else if (app.getOrderby() == 2) {
					// 如果是2，则删除3，2
					if (NestUtil.isNotEmpty(app.getTopic())) {
						deletedatas.add(generateDelMapObj(waterid, "1301316"));
					}
					if (NestUtil.isNotEmpty(app.getApprasial())) {
						deletedatas.add(generateDelMapObj(waterid, "1301317"));
					}
					deletedatas.add(generateDelMapObj(waterid, "1301318"));
					deletedatas.add(generateDelMapObj(waterid, "1301319"));
				} // 变更三（可以不要，变更）
				else if (app.getOrderby() == 3) {
					// 如果是3，则删除3，2
					if (NestUtil.isNotEmpty(app.getTopic())) {
						deletedatas.add(generateDelMapObj(waterid, "1301318"));
					}
					if (NestUtil.isNotEmpty(app.getApprasial())) {
						deletedatas.add(generateDelMapObj(waterid, "1301319"));
					}
					deletedatas.add(generateDelMapObj(waterid, "1301316"));
					deletedatas.add(generateDelMapObj(waterid, "1301317"));
				}
			} else {

				deletedatas.add(generateDelMapObj(waterid, "1301318"));
				deletedatas.add(generateDelMapObj(waterid, "1301319"));
				deletedatas.add(generateDelMapObj(waterid, "1301316"));
				deletedatas.add(generateDelMapObj(waterid, "1301317"));
				deletedatas.add(generateDelMapObj(waterid, "1301314"));
				deletedatas.add(generateDelMapObj(waterid, "1301315"));

			}
		}
		// 体质健康
		this.tizhijiankang(dto, tzjk, report, insertdatas, deletedatas);
		// 6体检 开始
		tijianxinx = masterReportExt.querySigleCheckItems(dto, tjxx);
		// 体检表校验
		this.tijianbiao(dto, tijianxinx, report, insertdatas, deletedatas);
		// 校验成绩
		this.initSingleScore(dto, chengji, report, insertdatas, deletedatas);
		// 校验会考成绩
		this.huikaochengji(hkScore, dto, report, insertdatas, deletedatas);
	}

	public Map<String, Report> jiaoyanlist(int[] classs, UserDto userDto,
			IMasterReportExt masterReportExt) {
		try {
			// 学生
			List<StudentDto> stulist = new ArrayList<StudentDto>();
			// 获取当前系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			if (classs.length > 0) {
				// ----------------修改开始-------------------
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("cmis30id", userDto.getCmis30id());
				params.put("discode", userDto.getDiscode());
				params.put("levelcode", userDto.getLevelcode());
				params.put("campusid", userDto.getCampuseId());
				params.put("techerid", userDto.getTeacherid());
				// （所有请求班级）eduid集合
				List<String> eduids = new ArrayList<String>();
				// 根据班级id查询班级所有uuid(已修改)
				eduids = this.getStudenteduidByclassid(classs,
						userDto.getCampuseId(), userDto.getCmis30id(),
						userDto.getDiscode());
				if (eduids == null || eduids.size() < 1)
					return null;
				params.put("eduIds", eduids);
				// 个性发展
				List<AppraisalDto> ziwopingjia = masterReportExt
						.queryOutPersonalAppral(params, "self");
				// 取成果展示信息
				List<AppraisalDto> chengg = this
						.getOutputstrongByclassid(eduids);
				// 班主任评语
				List<AppraisalDto> banpingyu = this.queryMasterAppral(params);
				// 研究性学习
				List<AppraisalDto> yanjxxx = this.queryOutStudyAppral(params);
				// 体质健康
				List<HealthDataDto> tzjk = this.queryHealthDdatas(params);
				// 体检信息
				List<CheckItemInfoDto> tjxx = this.queryCheckItems(params);
				// 成绩
				List<ModelScoreDto> chengji = this.queryAllScore(params);
				// 会考成绩
				params.put("flag", "1");
				Map<String, String> hkScore = masterReportExt
						.queryHKScore(params);
				// 1校验 报告册封面 --开始
				if (classs == null || classs.length < 1) {
					classs = new int[1];
					classs[0] = -1;
				}
				List<String> classidStrs = new ArrayList<String>();
				for (int i : classs)
					classidStrs.add(i + "");
				params.put("classid", classidStrs);
				stulist = this.getStudentByid(params);
				Sreportstatus st = new Sreportstatus();
				st.setVerfifyresult("缺");
				// 获取上传记录主表所有学生s_report信息，如果不存在则直接生成
				Map<String, Report> reports = this.generateReports(stulist,
						userDto.getTermId(), df);

				if (reports == null)
					return null;
				// 初始化插入数据容器
				List<Map<String, Object>> insertdatas = new ArrayList<Map<String, Object>>();
				// 初始化删除数据容器
				List<Map<String, Object>> deletedatas = new ArrayList<Map<String, Object>>();
				// 开始依次对每个学生进行检查
				for (StudentDto dto : stulist) {
					Report report = reports.get(dto.getStudentid());
					if (report == null)
						continue;
					// 开始依次检查
					checkDataIsOkForEveryStudent(insertdatas, deletedatas, dto,
							report, st, ziwopingjia, chengg, banpingyu,
							yanjxxx, tzjk, tjxx, chengji, masterReportExt,
							hkScore);
				}
				// 批量执行插入操作
				String[] insertfieldNames = { "reportid", "verfify",
						"verfifyresult" };
				String insertsql = "insert into S_REPORT_STATUS(WATERID,REPORT_ID,VERFIFY_KIND,VERFIFY_RESULT)values(s_test_stat.nextval,:reportid,:verfify,:verfifyresult)";
				this.batchUpdateObjects(insertdatas, insertfieldNames,
						insertsql);
				// 批量执行删除操作
				String[] delfieldNames = { "REPORT_ID", "VERFIFY_KIND" };
				String delsql = "delete from S_REPORT_STATUS where  REPORT_ID=? and VERFIFY_KIND=?";
				this.batchUpdateObjects(deletedatas, delfieldNames, delsql);
				return reports;
				// ----------------修改结束-------------------
			}
		} catch (Exception e) {
			logger.error("updateReportinof(Map<String, Object>)", e);
			// 当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
		return null;
	}

	public void checkReportStatus(Map<String, Report> reports) {
		if (reports == null)
			return;
		// 校验所有的报告册是否全部通过
		// 1、查询出对应的报告册验证状态
		Map<String, Sreportstatus> sreportstatusMap = getSreportBystuId(reports
				.values());
		// 做整体更新
		List<Map<String, Object>> updateReports = new ArrayList<Map<String, Object>>();
		for (String mapkey : reports.keySet()) {
			Report report = reports.get(mapkey);
			if (report == null)
				continue;
			Map<String, Object> dat = new HashMap<String, Object>();
			String warterid = report.getWaterid();
			Sreportstatus sreportstatus = sreportstatusMap.get(warterid);
			dat.put("WATERID", warterid);
			if (sreportstatus == null)
				dat.put("CHECKSTATE", "1800001");
			else
				dat.put("CHECKSTATE", "1800002");
			updateReports.add(dat);
		}
		String[] updatefieldNames = { "CHECKSTATE", "WATERID" };
		String updateSql = "update s_report set CHECKSTATE =? where WATERID =?";
		this.batchUpdateObjects(updateReports, updatefieldNames, updateSql);
	}

	private Map<String, Report> generateReports(List<StudentDto> studentDtos,
			String termid, SimpleDateFormat df) {
		if (studentDtos == null || studentDtos.size() < 1)
			return null;
		List<String> studentids = new ArrayList<String>(0);
		for (StudentDto dto : studentDtos)
			studentids.add(dto.getStudentid());
		Map<String, Report> allReports = new HashMap<String, Report>();
		List<Report> notExistReports = new ArrayList<Report>();
		// 查询出存在的报告册
		Map<String, Report> existReports = this.isRetortById(studentids);
		// 找出不存在的报告册
		for (StudentDto dto : studentDtos) {
			// 如果存在
			if (existReports != null
					&& existReports.containsKey(dto.getStudentid())) {
				Report report = existReports.get(dto.getStudentid());
				allReports.put(report.getPid(), report);
			} else {
				// 如果不存在，则直接生成
				Report report = generateReport(dto.getStudentid(),
						dto.getEduid(), termid, df);
				notExistReports.add(report);
				allReports.put(report.getPid(), report);
			}
		}
		// 批量生成未存在的报告册
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (Report report : notExistReports) {
			Map<String, Object> dt = new HashMap<String, Object>();
			dt.put("WATERID", report.getWaterid());
			dt.put("P_ID", report.getPid());
			dt.put("TERMID", report.getTermid());
			dt.put("REPORT_STATUS", report.getReportstatus());
			dt.put("USED", report.getUsed());
			dt.put("REPORT_TIME", report.getReporttime());
			dt.put("EDU_ID", report.getEduid());
			dt.put("CHECKSTATE", report.getCheckstate());
			datas.add(dt);
		}
		String[] fieldNames = { "WATERID", "P_ID", "TERMID", "REPORT_STATUS",
				"USED", "REPORT_TIME", "EDU_ID", "CHECKSTATE" };
		String insertsql = "insert into s_report(WATERID,P_ID,TERMID,REPORT_STATUS,USED,REPORT_TIME,EDU_ID,CHECKSTATE)values"
				+ "(?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		this.batchUpdateObjects(datas, fieldNames, insertsql);
		return allReports;
	}

	private Report generateReport(String studentid, String eduid,
			String termid, SimpleDateFormat df) {
		Report report = new Report();
		report.setPid(studentid); // 学生id
		report.setTermid(termid); // 学期id从session中获得
		report.setReporttime(df.format(new Date())); // 获取当前系统时间
		report.setReportstatus(null); // 设置状态为未上报
		report.setCheckstate("1800002"); // 默认失败
		report.setUsed("1"); // 设置生效
		report.setEduid(eduid); // session获取教育id
		// 生成主键
		report.setWaterid(generateReportProKey());
		return report;
	}

	private String generateReportProKey() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<String> proKeys = this.findList(
					"BookReportExt.generateReportProKey.generate", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							return rs.getString("prokey");
						}
					});
			if (proKeys == null || proKeys.size() < 1)
				return null;
			else
				return proKeys.get(0);
		} catch (Exception e) {
			logger.error("BookReportExt.generateReportProKey()", e);
		}
		return null;
	}

	/**
	 * 校验会考成绩
	 * */
	private void huikaochengji(Map<String, String> huikao, StudentDto sDto,
			Report rr, List<Map<String, Object>> insertdatas,
			List<Map<String, Object>> deletedatas) {
		// 包括会考成绩的bean
		Sreportstatus st = new Sreportstatus();
		st.setReportid(rr.getWaterid());
		String xueke = "语文,英语,历史,地理,数学,物理,化学,思想政治,生物";
		StringBuffer sb = new StringBuffer();
		StringBuffer tui = new StringBuffer();
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
			boolean isHKQue = true;// 会不考缺
			if (null != msDto && msDto.size() > 0) {
				for (ModelScoreDto model : msDto) {
					if (NestUtil.isNotEmpty(sDto.getEduid())
							&& sDto.getEduid().equals(model.getEdu_id())) {
						/**
						 * 校验开始
						 * */
						String subjectName = "语文,英语,历史,地理,数学,物理,化学,生物,思想政治,英语";
						if (NestUtil.isEmpty(model.getHkScore())
								&& subjectName.contains(model.getNzOrXbSub())) {
							st.setVerfifyresult("缺");
							st.setVerfify("1301221"); // 内容1
							insertdatas.add(generateInsertMapObj(st));
							isHKQue = false;// 会考缺
						}
						if (NestUtil.isNotEmpty(model.getNzOrXbSub())) {
							if (model.getNzOrXbSub().equals("语文")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("语文,", "");
									}
								}

							} else if (model.getNzOrXbSub().equals("英语")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("英语,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("历史")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("历史,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("地理")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("地理,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("数学")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("数学,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("物理")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("物理,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("化学")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("化学,", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("思想政治")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("思想政治", "");
									}
								}
							} else if (model.getNzOrXbSub().equals("生物")) {
								if (NestUtil.isNotEmpty(model.getHkScore())) {
									if ("A".equals(model.getHkScore().trim())
											|| "B".equals(model.getHkScore()
													.trim())
											|| "C".equals(model.getHkScore()
													.trim())
											|| "D".equals(model.getHkScore()
													.trim())) {
										num++;
										xueke = xueke.replace("生物", "");
									}
								}
							}
						}
					}
				}
				if (isHKQue) {
					deletedatas.add(generateDelMapObj(rr.getWaterid(),
							"1301221"));
				}
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301222"));
				if (!(xueke.equals("语文,英语,历史,地理,数学,物理,化学,思想政治,生物"))) {
					if (null != xueke && !(xueke.equals(""))) {
						if (xueke.indexOf("生物") == -1) {
							xueke = xueke.substring(0, xueke.length() - 1);
						}
					}
				}

				st.setVerfify("1301222"); // 会考成绩非ABCD 1301221
				st.setVerfifyresult(xueke);
				insertdatas.add(generateInsertMapObj(st));

				if (num == 8) {
					deletedatas.add(generateDelMapObj(rr.getWaterid(),
							"1301222"));
				}

			}
		}
	}

	// 校验成绩
	public void initSingleScore(StudentDto sDto, List<ModelScoreDto> allScore,
			Report rr, List<Map<String, Object>> insertdatas,
			List<Map<String, Object>> deletedatas) {
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
							}

							break;
						case 1230301: // 必修
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								bixiu = bixiu
										+ Double.valueOf(model.getCredit_hour());
							}
							break;
						case 1230315: // 选修
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								xuanxiu = xuanxiu
										+ Double.valueOf(model.getCredit_hour());
							}
							break;
						case 1230310: // 必选和必修累加
							if (NestUtil.isNotEmpty(model.getCredit_hour())) {
								bixiu = bixiu
										+ Double.valueOf(model.getCredit_hour());
							}
						}

					}

				}
			}

			zong = zong + bixiu + xuanxiu + xiaoben;
			if (zong == 0) {
				st.setVerfifyresult("缺");
				st.setVerfify("10004"); // 选修
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "10004"));
			}

			if (bixiu == 0) {
				st.setVerfifyresult("缺");
				st.setVerfify("10002"); // 必修为空
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "10002"));
			}

			if (xuanxiu == 0) {
				st.setVerfifyresult("缺");
				st.setVerfify("10003"); // 选修
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "10003"));
			}

			if (xiaoben == 0) {
				st.setVerfifyresult("缺");
				st.setVerfify("10001"); // 校本为空
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "10001"));
			}

			if (zong < 144) {
				st.setVerfifyresult("X");
				st.setVerfify("1301210"); // 总学分
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301210"));
			}

			if (bixiu < 116) {
				st.setVerfifyresult("X");
				st.setVerfify("1301207"); // 必修
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301207"));
			}

			if (xuanxiu < 22) {
				st.setVerfifyresult("X");
				st.setVerfify("1301208"); // 选修
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301208"));
			}

			if (xiaoben < 6) {
				st.setVerfifyresult("X");
				st.setVerfify("1301209"); // 校本
				insertdatas.add(generateInsertMapObj(st));
			} else {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301209"));
			}
		} else {
			st.setVerfifyresult("X");
			st.setVerfify("1301210"); // 总学分
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("X");
			st.setVerfify("1301207"); // 总学分
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("X");
			st.setVerfify("1301208"); // 总学分
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("X");
			st.setVerfify("1301209"); // 总学分
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("缺");
			st.setVerfify("10004"); // 选修
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("缺");
			st.setVerfify("10003"); // 选修
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("缺");
			st.setVerfify("10002"); // 必修为空
			insertdatas.add(generateInsertMapObj(st));

			st.setVerfifyresult("缺");
			st.setVerfify("10001"); // 校本为空
			insertdatas.add(generateInsertMapObj(st));
		}
	}

	/**
	 * 体质健康
	 * */
	public void tizhijiankang(StudentDto sDto, List<HealthDataDto> healthDatas,
			Report rr, List<Map<String, Object>> insertdatas,
			List<Map<String, Object>> deletedatas) {
		boolean yi = true;
		boolean er = true;
		boolean three = true;
		Sreportstatus st = new Sreportstatus();
		st.setReportid(rr.getWaterid());
		st.setVerfifyresult("缺");
		st.setVerfify("1301205"); // 体质健康
		insertdatas.add(generateInsertMapObj(st));
		if (null != healthDatas && healthDatas.size() > 0) {
			for (HealthDataDto app : healthDatas) {
				if (NestUtil.isNotEmpty(sDto.getEduid())
						&& sDto.getEduid().equals(app.getEduId())) {
					if (NestUtil.isNotEmpty(app.getAllContent())
							&& NestUtil.isNotEmpty(app.getGradeNum())) {

						if (app.getGradeNum().equals("一")) {
							if (!(app.getYearGrade().equals("免体"))) {
								String itmes[] = app.getAllContent().split(",");

								// 校验数据完整性
								if (itmes.length + 1 < 6) {
									yi = false;
								} else {
									for (int y = 0; y < itmes.length; y++) {
										int shu = itmes[y].indexOf("-1");
										if (shu == -1) {
											yi = false;
										}

									}
								}
							}

						} else if ("二".equals(app.getGradeNum())) {
							if (!("免体".equals(app.getYearGrade()))) {
								String itmes[] = app.getAllContent().split(",");

								// 校验数据完整性
								if (itmes.length + 1 < 7) {
									er = false;
								} else {
									for (int y = 0; y < itmes.length; y++) {
										int shu = itmes[y].indexOf("-1");
										if (shu != -1) {
											er = false;
										}

									}
								}
							}
						} else if ("三".equals(app.getGradeNum())) {
							if (!("免体".equals(app.getYearGrade()))) {
								String itmes[] = app.getAllContent().split(",");

								// 校验数据完整性
								if (itmes.length + 1 < 7) {
									three = false;
								} else {
									for (int y = 0; y < itmes.length; y++) {
										int shu = itmes[y].indexOf("-1");
										if (shu != -1) {
											three = false;
										}

									}
								}
							}
						}
					}
				}
			}

			if (yi || er || three) {
				deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301205"));
			}
		}

	}

	/**
	 * 体检表校验
	 * */
	public void tijianbiao(StudentDto sDto, List<CheckItemInfoDto> tijianxinx,
			Report rr, List<Map<String, Object>> insertdatas,
			List<Map<String, Object>> deletedatas) {

		boolean pan = true;
		int xue = 0;
		int shuliZ = 0;
		int shuliY = 0;
		int jinshiZ = 0;
		int jinshiY = 0;

		if (null != tijianxinx || tijianxinx.size() > 3) {
			for (CheckItemInfoDto ch : tijianxinx) {
				// 校验每一年
				// 身高
				if (NestUtil.isEmpty(ch.getItemResultCm())) {
					pan = false;
				}

				// 体重
				if (NestUtil.isEmpty(ch.getItemResultKg())) {
					pan = false;
				}
				// 心脏
				if (NestUtil.isEmpty(ch.getItemResultXz())) {
					pan = false;
				}

				// 肺
				if (NestUtil.isEmpty(ch.getItemResultF())) {
					pan = false;
				}

				// 肝
				if (NestUtil.isEmpty(ch.getItemResultGan())) {
					pan = false;
				}
				// 脾
				if (NestUtil.isEmpty(ch.getItemResultPi())) {
					pan = false;
				}
				// 颈、
				if (NestUtil.isEmpty(ch.getItemResultTj())) {
					pan = false;
				}
				// 胸部、
				if (NestUtil.isEmpty(ch.getItemResultXb())) {
					pan = false;
				}
				// 脊柱、
				if (NestUtil.isEmpty(ch.getItemResultJz())) {
					pan = false;
				}
				// 四肢关节、
				if (NestUtil.isEmpty(ch.getItemResultSz())) {
					pan = false;
				}
				// 皮肤淋巴
				if (NestUtil.isEmpty(ch.getItemResultPf())) {
					pan = false;
				}
				// 血色素
				if (NestUtil.isNotEmpty(ch.getItemResultXss())) {
					xue++;
				}

				// 裸眼视力左（上学期）
				if (NestUtil.isNotEmpty(ch.getItemResultLySlLeftUp())) {
					shuliZ++;
				}
				// 裸眼视力左（下学期）
				if (NestUtil.isNotEmpty(ch.getItemResultLySlLeftDown())) {
					shuliZ++;
				}

				// 裸眼视力右（上学期）
				if (NestUtil.isNotEmpty(ch.getItemResultLySlRightUp())) {
					shuliY++;
				}
				// 裸眼视力右（下学期）
				if (NestUtil.isNotEmpty(ch.getItemResultLySlRightDown())) {
					shuliY++;
				}

				// 近视力左（上学期）
				if (NestUtil.isNotEmpty(ch.getItemResultJSlLeftUp())) {
					jinshiZ++;
				}
				// 近视力左（下学期）
				if (NestUtil.isNotEmpty(ch.getItemResultJSlLeftDown())) {
					jinshiZ++;
				}
				// 近视力右（上学期）
				if (NestUtil.isNotEmpty(ch.getItemResultJSlRightUp())) {
					jinshiY++;
				}

				// 近视力右（下学期）
				if (NestUtil.isNotEmpty(ch.getItemResultJSlRightDown())) {
					jinshiY++;
				}

			}
		} else {
			pan = false;
		}

		if (pan && xue > 1
				&& (shuliZ > 1 && shuliY > 1 || jinshiZ > 1 && jinshiY > 1)) {
			// this.deleteReportItme(rr.getWaterid(),"1301206");
			deletedatas.add(generateDelMapObj(rr.getWaterid(), "1301206"));
		} else {
			Sreportstatus st = new Sreportstatus();
			st.setReportid(rr.getWaterid());
			st.setVerfifyresult("缺");
			st.setVerfify("1301206"); // 体质健康
			insertdatas.add(generateInsertMapObj(st));
		}
	}

	@DataSource("read")
	public List<SchoolreportDto> getSchoolReported(String discode,
			String schoolname) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("discode", discode);
			params.put("schoolname", schoolname);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getQuIndex.query");
			return this.findList("BookReportExt.getQuIndex.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							SchoolreportDto schdto = new SchoolreportDto();
							schdto.setCmis30id(rs.getInt("CMIS30ID")); // 学校id
							schdto.setJiebie(rs.getString("jiebie")); // 界别
							schdto.setReportedCount(rs.getInt("reported")); // 上报人数-学校
							schdto.setSchoolcode(rs.getString("schoolcode")); // 学校代码
							schdto.setSchoolname(rs.getString("schoolname")); // 学校名字
							schdto.setVerficationFailedCount(rs
									.getInt("failed")); // 校验错误人数
							schdto.setVerficationSuccessCount(rs
									.getInt("success")); // 校验正确人数
							return schdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getSchoolReported()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<StudentDto> getQuErrorRepor(Integer cmis30id) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getQuerror.query");
			return this.findList("BookReportExt.getQuerror.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							StudentDto stu = new StudentDto();
							stu.setStudentid(rs.getString("studentid"));
							stu.setStudentno(rs.getString("studentno"));
							stu.setName(rs.getString("name"));
							stu.setEduid(rs.getString("edu_id"));
							return stu;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getQuErrorRepor()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<SchoolreportDto> getSchoolSelectList(String discode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("discode", discode);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getQuIndex.query");
			return this.findList("BookReportExt.getQuIndex.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							SchoolreportDto schdto = new SchoolreportDto();
							schdto.setCmis30id(rs.getInt("CMIS30ID")); // 学校id
							schdto.setSchoolname(rs.getString("schoolname")); // 学校名字
							return schdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getSchoolSelectList()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<SchoolreportDto> getNoSchollreportList(String discode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("discode", discode);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getNoSBschool.query");
			return this.findList("BookReportExt.getNoSBschool.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							SchoolreportDto schdto = new SchoolreportDto();
							schdto.setSchoolcode(rs.getString("schoolcode")); // 学校代码
							schdto.setSchoolname(rs.getString("schoolname")); // 学校名字
							return schdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getNoSchollreportList()", e);
		}
		return null;
	}

	@DataSource("read")
	public List<SchoolreportDto> getSchoolOne(Integer cmis30id) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.getSchoolOne.query");
			return this.findList("BookReportExt.getSchoolOne.query", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							SchoolreportDto schdto = new SchoolreportDto();
							schdto.setCmis30id(rs.getInt("CMIS30ID")); // 学校id
							schdto.setJiebie(rs.getString("jiebie")); // 界别
							schdto.setReportedCount(rs.getInt("reported")); // 上报人数-学校
							schdto.setSchoolcode(rs.getString("schoolcode")); // 学校代码
							schdto.setSchoolname(rs.getString("schoolname")); // 学校名字
							schdto.setVerficationFailedCount(rs
									.getInt("failed")); // 校验错误人数
							schdto.setVerficationSuccessCount(rs
									.getInt("success")); // 校验正确人数
							return schdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getSchoolOne()", e);
		}
		return null;
	}

	@Override
	public List<SchoolreportDto> getAllSchoolByDiscode(String discode) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("discode", discode);
			ISqlElement sqlDemo = this.processSql(params,
					"BookReportExt.queryAllSchoolByDiscode.query");
			return this.findList("BookReportExt.queryAllSchoolByDiscode.query",
					params, new RowMapper() {
						public Object mapRow(ResultSet rs, int num)
								throws SQLException {
							SchoolreportDto schdto = new SchoolreportDto();
							schdto.setCmis30id(rs.getInt("CMIS30ID")); // 学校id
							schdto.setJiebie(rs.getString("jiebie")); // 界别
							schdto.setSchoolcode(rs.getString("schoolcode")); // 学校代码
							schdto.setSchoolname(rs.getString("schoolname")); // 学校名字
							return schdto;
						}
					});
		} catch (Exception e) {
			logger.error("BookReportExtImpl.getAllSchoolByDiscode()", e);
		}
		return null;
	}
}
