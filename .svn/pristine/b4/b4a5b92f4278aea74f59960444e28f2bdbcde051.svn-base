package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.TermDto;

public interface ITermServiceExt {
	/**
	 * 查询所有的学期
	 * @return 返回查询的结果集
	 */
	List<TermDto> queryEschoolYears();
	/**
	 * 返回符合条件的高中学期
	 * @param classid 匹配的班级标识号
	 * @param levelCode 学段代码
	 * @return 学期集合
	 */
	List<TermDto> queryHighSchoolTerms(String classid,String levelCode);
	
	List<TermDto> queryHighSchoolTerms(Integer gradeYearInt);
}
