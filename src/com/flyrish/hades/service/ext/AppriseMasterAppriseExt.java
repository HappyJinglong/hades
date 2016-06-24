package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.AppraiseBaseDto;

public interface AppriseMasterAppriseExt {
	/**
	 * 通过学生教育id和指定的学期查看指定学期的评价信息
	 * @param edu_id 学生教育标识号
	 * @param termid 学期标识号
	 * @param levelCode 学段代码
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @return 返回该学生所有评价信息，如果含有附件，请把附件路径拼接为全路径 即nginx_server//filepath，如果edu_id或者levelCode为空，则返回null
	 */
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String sectionCode,List<String> eduIds,String termid,String levelCode,String cmis30id,String discode);
	
	public void queryAssess(List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto);
	
	public void queryMiddleSchoolAppraisalInfo(String sectionCode,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto);

	public void queryMiddleSchoolAppraisalInfoFromCache(String sectionCode,	List<String> eduIds, String termId,	List<AppraiseBaseDto> appraiseBaseDtos);

	public List<AppraiseBaseDto> queryAppraiseBaseDtoByConditionFromCache(List<String>studentNames,String sectionCode, List<String> eduIds, String termid, String levelcode,String cmis30id,String discode);

	List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String sectionCode,
			String secondItem, List<String> eduIds, String termid,
			String levelCode, String cmis30id, String discode);

}
