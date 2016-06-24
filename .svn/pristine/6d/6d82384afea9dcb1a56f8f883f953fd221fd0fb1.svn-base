package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.IPage;

import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KstudentMatriculateDto;
import com.flyrish.hades.dto.KstudySegmentDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.TbaseinfoDto;
import com.flyrish.hades.dto.UserDto;

public interface ISchoolCourseExt {
	/**
	 * 获取最近三年数据
	 * @return String:学年ID@学年名称
	 */
	public List<String> getSchoolYears();
	/**
	 * 获取学段信息
	 * @param params cmis30id schoolyear
	 * @return 学段信息
	 */
	public List<KstudySegmentDto> getSegmentInfos(Map<String, Object> params);
	/**
	 * 获取校本课程信息
	 * @param queryMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IPage selectSchoolCourse(Map<String,Object> queryMap,Integer pageNo,Integer pageSize);
	/**
	 * 插入模块信息
	 * @param params
	 */
	public void insertCourseInfos(Map<String,Object> params);
	/**
	 * 获取教师信息
	 * @param params
	 * @return
	 */
	public List<TbaseinfoDto> getTeacherInfosByName(Map<String,Object> params);
	/**
	 * 根据cmis30Id segmentId查询对应学科
	 * @param params
	 * @return
	 */
	public List<KsysSubjectDto>getSubjectByCTS(Map<String,Object> params);
	/**
	 * 查询模块编号和名称是否有重复
	 * @param params
	 * @return
	 */
	public Integer checkCourseCount(Map<String, Object> params);
	/**
	 * 根据模块id查询适用年级
	 * @param params
	 * @return
	 */
	public List<KcourseArrangeDto>getAspectInfos(Map<String, Object> params);
	/**
	 * 根据模块id查询单个模块信息
	 * @param params
	 * @return
	 */
	public List<KcourseDto> getSingleCourseInfoById(Map<String, Object> params);
	/**
	 * 更新课程信息
	 * @param params
	 */
	public void updateCourseInfo(Map<String, Object> params);
	/**
	 * 删除模块信息
	 * @param params
	 */
	public void deleteCourseInfo(Map<String, Object> params);
	/**
	 * 批量保存数据
	 * @param courseInfos
	 */
	public void saveCourseList(List<KcourseDto> courseInfos,UserDto userDto);
	/**
	 * 根据学段课程标号查询录取学生信息
	 * @param params
	 * @return
	 */
	public boolean isWriteScore(Map<String, Object> params);
	/**
	 * 获取录取学生信息
	 * @param queryMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IPage getHiredStudentInfosById(Map<String, Object> queryMap,Integer pageNo, Integer pageSize);
	/**
	 * 删除已经录取学生信息
	 * @param params
	 */
	public void deleteHiredStudentInfo(Map<String, Object> params);
	/**
	 * 获取学校学生信息
	 * @param params
	 * @return
	 */
	public List<KstudentMatriculateDto> getSchoolStudentInfos(Map<String, Object> params);
	/**
	 * 获取当前学年已经被录取学生信息
	 * @param params
	 * @return
	 */
	public List<KstudentMatriculateDto> getSchoolHiredStudentInfos(Map<String, Object> params);
	/**
	 * 插入录取学生信息
	 * @param excelCourseInfos
	 */
	public void insertHiredStudentInfo(List<KcourseDto> excelCourseInfos);
	/**
	 * 导入数据 保存 或者 更新
	 * @param insertDtoList
	 * @param updateDtoList
	 */
	public void saveOrUpdateCourseList(List<KcourseDto> insertDtoList,List<KcourseDto> updateDtoList,UserDto userDto);
	/**
	 * 需要删除的数据是否已经录入成绩
	 * @param params
	 */
	public boolean isAnyWriteScore(Map<String, Object> params);
	/**
	 * 新增校本课程并对老师角色进行修改
	 * @param newRoleTeacher
	 * @param flag
	 */
	public void insertRole(UserDto newRoleTeacher,List<UserDto>roleDtos);
	/**
	 * 查询学校校本课程老师对应有哪些用户
	 * @param newRoleTeacher
	 * @return
	 */
	public String queryRoleId(UserDto newRoleTeacher);
	/**
	 * 从数据库获取userid
	 * @param tID
	 * @return
	 */
	public String queryUserIdFromDB(String tID,String unitId);
}
