package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.AppraiseBaseDto;

public interface IOperationAppraiseServiceExt {
	/**
	 * 通过学生教育id和指定的学期查看指定学期的评价信息
	 * @param edu_id 学生教育标识号
	 * @param termid 学期标识号
	 * @param levelCode 学段代码
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @return 返回该学生所有评价信息，如果含有附件，请把附件路径拼接为全路径 即nginx_server//filepath，如果edu_id或者levelCode为空，则返回null
	 */
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String studentName,String edu_id,String termid,String levelCode,String cmis30id,String discode);
	/**
	 * 通过学生的教育标识号计算相应的高中学期标识号
	 * @param edu_id 教育标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @return 如果教育标识号为null，则返回null,反之，则返回相应的学期标识号
	 */
	public String calcuHeighTermidByEduId(String edu_id,String cmis30id,String discode);
	/**
	 * 通过学生的教育标识号
	 * 获取初中学期标识号
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public String calcuMiddleTermidByEduId(String edu_id, String cmis30id,String discode);
	
}
