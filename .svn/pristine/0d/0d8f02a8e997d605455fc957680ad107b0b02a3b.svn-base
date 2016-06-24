package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.Conditions;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.Report;
import com.flyrish.hades.dto.ReportItme;
import com.flyrish.hades.dto.Reportstatus;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.Sreportstatus;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.Xueduan;

/**
 * 高中报告册service
 * */
public interface IBookReportExt {
 
	/**
	 * 查询学段
	 * */
	public List<Xueduan> getXueduan(String campuseId);
	
	/**
	 * 查询界别
	 * */
	public List<Conditions> getJiebie(String campuseId,String code);
	
	/**
	 * 查询班级
	 * */
	public List<ClassDto> getBGCBJList(String campuseId,String gradeid,String levelcode);
	
	
	/**
	 * 查询一个班所有学生eduidps上报的学生
	 * */
	public List<String>  getStudenteduidByclassidSB(String classid,String campuseId);
	
	/**
	 * 查询所有毕业班级
	 * */
	public List<ClassDto>  getClasssById(Integer cmis30id);

	
	/**
	 * 查询一个班所有学生 -默认全部查询
	 * */
	public List<StudentDto>  getStudentByClasssId(String classid,String campuseId);
	
	
	/**
	 * 查询一个班所有学生eduid
	 * */
	public List<String>  getStudenteduidByclassid(String classid,String campuseId,String cmis30id,String discode);
	
	/**
	 * 查询指定班所有学生eduid
	 * */
	public List<String>  getStudenteduidByclassid(int[]classids,String campuseId,String cmis30id,String discode);
	/**
	 * 查询一个班所有学生 查询北京籍
	 * */
	public  List<StudentDto>  getStudentByClasssIdAndCode(String classid,String campuseId);

	
	/**
	 * 查询是否上报
	 * */
	public List<Report> getRetortById(String studentid);
	
	/**
	 * 查询状态住表中是否有数据存在
	 * */
	public List<Report> isRetortById(String studentid);

	
	/**
	 * 上报报告册
	 * */
	public void insertReportInfo(Report report);

	
	/**
	 * 查询单个学生
	 * */
	public List<StudentDto> getStudentByStuId(String campuseId,String stuId);

	/**
	 * 校验首页
	 * */
	public List<StudentDto> getStudentByid(Map<String, Object> params);

	//校验自我评价 取一条
	public List<AppraisalDto> getOutputselfByclassid(List<String> eduids);

	//成果展示信息 没人3条
	public List<AppraisalDto> getOutputstrongByclassid(List<String> eduids);
	
	
	//获取评语信息  必须要有3年的信息
	List<AppraisalDto> queryMasterAppral(Map<String, Object> params); 
	
	//研究性学习
	List<AppraisalDto> queryOutStudyAppral(Map<String, Object> params);
	
	//体质健康
	List<HealthDataDto> queryHealthDdatas(Map<String, Object> params);
	
	//体检
	public List<CheckItemInfoDto> queryCheckItems(Map<String, Object> params);
	
	
	
	//插入状态副本
	public void insertSreportstatusInfo(Sreportstatus reportstatus);

	public void updateSreportByid(String studentid, String zhuangtai);

	
	//创建校验
	public void insertReportjyInfo(Report report);
	
	//校验错误学生
	public List<StudentDto> getStudenterrorlist(String classid);

	//取出来具体项
	public List<Reportstatus> getStudenterrorItmeBystuId(String studentid);

	
	//删除项
	public void deleteReportItme(String waterid, String key);

	public void updateReportinof(String waterid, String string);

	//查找班主任
	public String getStudentBarByClasId(String classId);

	//查找成绩用作校验
	public List<ModelScoreDto> queryAllScore(Map<String, Object> params);
	
 
	
	//校验方法
	public void jiaoyao(String classid,UserDto user);
	
	
	/**
	 * 统一插入
	 * */
	public void addAll(List<Sreportstatus> sb);
	
	/**
	 * 统一删除
	 * */
	public void deleteAll(List<Sreportstatus> sc);

	//查询该学生是否有未通过的项
	public List<Sreportstatus> getSreportBystuId(String waterid);
	
	/**
	 * 校验
	 * */
	public Map<String,Report> jiaoyanlist(int classs [],UserDto user,IMasterReportExt masterReportExt);
	
	
	
	
	
	/****************************************************************************************/
	
	//以下是区级报告册
	
	/**
	 * 已上报学校首页数据
	 * */
	public List<SchoolreportDto> getSchoolReported(String discode,String schoolname);
	
	
	/**
	 * 区县错误人数列表
	 * */
	public List<StudentDto> getQuErrorRepor(Integer cmis30id);

	/**
	 * 区县学校列表
	 * */
	public List<SchoolreportDto> getSchoolSelectList(String discode);
	
	/**
	 * 条件查询
	 * */
	public List<SchoolreportDto> getSchoolOne(Integer cmis30id);
	
	public void checkReportStatus(Map<String, Report> reports);

	public List<SchoolreportDto> getAllSchoolByDiscode(String discode);
	
	/**
	 * 查询未上报学校
	 * */
	public  List<SchoolreportDto> getNoSchollreportList(String discode);
	
}
