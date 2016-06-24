package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.SubjectDto;

public interface ILearnprocessStaticsExt {

	/**
	 * 获取市区级课程评语填写统计
	 * @param gradesyear 界别
	 * @param schoolName 学校名称
	 * @param levelCode 学段代码
	 * @param termid 学期
	 * @param discode 区县代码
	 * @param subjectDtos 科目信息
	 * @param userType 用户类型
	 * @return Map<学校名称,Map<科目,学生总数@已填写人数@完成百分比@已评价条目数>>
	 */
	public Map<String,Map<String,String>> queryLearnprocessStaticsInfo(String gradesyear,String schoolName,String levelCode,String termid,String discode,List<SubjectDto>subjectDtos,String userType);
	/**
	 * 获取教务老师课程评语填写统计
	 * @param termId 历史学期
	 * @param currentTermId 当前学期
	 * @param discode
	 * @param cmis30Id
	 * @param subjectDtos
	 * @param gradeYears 学段下对应届别
	 * @param levelId 学段id
	 * @param levelCode 学段代码
	 * @return Map<科目, Map<完成情况, Map<界别,各情况计数>>>
	 */
	public Map<String, Map<String, Map<String,String>>> queryJWLearnprocessStatics(String termId, String currentTermId, String discode,String cmis30Id, List<SubjectDto> subjectDtos,List<String>gradeYears,String levelId,String levelCode);
	/**
	 * 获取模板空数据
	 * @param termId
	 * @param currentTermId
	 * @param subjectDtos
	 * @param gradeYears
	 * @return
	 */
	public Map<String, Map<String, Map<String, String>>> queryJWLearnprocessStatics(String termId, String currentTermId, List<SubjectDto> subjectDtos,List<String> gradeYears);
}
