package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

public interface ITotalStaticsExt {
	/**
	 * 获取指定年级班级学生在指定学期的评价信息情况
	 * @param gradeId 年级标识号
	 * @param classid 班级标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校代码
	 * @param discode 区县代码
	 * @return Map<String,Object>=>key='gradeName'时，取到届别，key='className'时，取得班级号，key='totalPersonsNum'时，取总人数。key='data'时，取数据集合
	 */
	public Map<String,Object> queryStudentAppraiseInfoNumDtoByInfo(String gradeId,String classid,String termid,String cmis30id,String discode);
	/**
	 * 查询出指定班级所有的学生相关信息
	 * @param classid 班级标识号
	 * @param gradeid 年级标示号
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @param edu_id 教育标识号
	 * @param studentName 学生名称
	 * @return List<学生姓名_教育id_学段代码_届别_班级>
	 */
	public List<String> queryStudentInfoByCondition(String classid,String gradeid,String cmis30id,String discode,String edu_id,String studentName);
	/**
	 * 查询出指定班级所有的学生相关信息
	 * @param classid 班级标识号
	 * @param gradeid 年级标示号
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @param edu_id 教育标识号
	 * @param studentName 学生名称
	 * @return List<教育id>
	 */
	public List<String> queryStudentInfoByCondition1(String classid,String gradeid,String cmis30id,String discode,String edu_id,String studentName);
	/**
	 * 通过学段标识号获取该学段所对应的学年集合
	 * @param levelid 学段标识号
	 * @return 学年集合
	 */
	public List<String> queryGradeYearList(String levelid,String levelCode);
	
	public Map<String,Map<String,Map<String,String>>> queryMapDataByCondition(String termid,String cmis30id,String discode,String levelCode,String levelid);
	/**
	 * 区级查询统计信息
	 * @param 选择termid 学期标识号
	 * @param gradeyear 学年
	 * @param discode 区县
	 * @param levelCode 学段
	 * @param schoolname 学校名称
	 * @param tongjiDatas 数据统计信息集合
	 * @return 数据集合
	 */
	public Map<String,Map<String,Map<String,String>>> queryMapDataByConditionInDiscode(String termid,String gradeyear,String discode,
			String levelCode,String schoolname, Map<String,Map<String,String>> tongjiDatas);

}
