package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.StudentDto;

/**
 * 高中报告册service
 * */
public interface IViewAppraiseExt {
	
	/**
	 * 查询班级
	 * @param levelCode 
	 * */
	public List<ClassDto> getClassList(String cmis30id, String graduateyear,
			String discode, String levelCode);


	StudentDto getStudentByPager(String classid, String cmis30id,
			String discode, String year, String termid, String levelCode, long index);


	Long getStudentTotal(String classid, String cmis30id, String discode,
			String year, String termid, String levelCode);


	public List<SchoolreportDto> querySchool(String discode, String levelcode);
}
