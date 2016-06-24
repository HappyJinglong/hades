package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalWritedStaticseDto;
import com.flyrish.hades.dto.UserDto;

public interface IAppraisalWritedStaticsExt {
	/**
	 * 获取学期
	 * @param levelCode 学段代码
	 * @param campuseId 校区id
	 * @param year 界别
	 * @param flag 初中有值
	 * @return List<termId@学期名>
	 */
	public List<String>queryTerm(String levelCode,String campuseId,String year,String flag);
	/**
	 * 
	 * @param cmis30Ids
	 * @param levelCode
	 * @param gradeNum
	 * @param termId
	 * @param jb
	 * @param userType
	 * @return
	 */
	public List<AppraisalWritedStaticseDto>queryAppraiseStatics(List<String>cmis30Ids,String levelCode,String gradeNum,String termId,String jb,String userType,String campueId);

	/**
	 * 区县查询统计情况
	 * @param schoolName 学校名称
	 * @param termId 学期id
	 * @param discode 区县代码
	 * @param levelCode 学段代码
	 * @param termName 学期名称
	 * @return
	 */
	public List<AppraisalWritedStaticseDto>queryQXAppraiseStatics(String schoolName, String termId, String discode, String levelCode,String termName,String campusId,String userType);
	/**
	 * 获取区县学校信息
	 * @param discode  区县标识号
	 * @param schoolName 学校名称
	 * @return
	 */
	public List<String>queryQXSchoolInfos(String discode,String schoolName);
	/**
	 * 获取区县代码信息
	 * @param levelCode 高中传值   初中为null
	 * @return
	 */
	public List<String>queryQXInfos(String levelCode);
	
	public List<AppraisalWritedStaticseDto> querySJAppraiseStatics(String discode,
			List<String> queryQXInfos,
			String schoolName,
			String termId,
			String levelCode,
			String zoneName,
			String termName,
			UserDto userDto);
	/**
	 * 获取区县评价数据统计（初中 partInfo表中数据）
	 * @param levelCode 学段代码
	 * @param years 届别
	 * @param discode 区县代码
	 * @param cmis30Id 学校代码
	 * @return
	 */
//	public Map<String,AppraisalWritedStaticseDto> queryQXAppraiseInfosFromDB(String levelCode,List<String>years,String discode,String schoolName);
	
	/**
	 * 获取区县全部统计信息
	 * @param levelCode 学段代码
	 * @param years 界别
	 * @param discode 区县代码
	 * @param schoolName 学校名称
	 * @param termId 选中学期
	 * @param currentTermId 当前学期
	 * @return
	 */
	public List<AppraisalWritedStaticseDto>queryQXAppraiseStatics(String levelCode,List<String>years,String discode,String schoolName,String termId,String currentTermId);
	/**
	 * 获取区县全部统计信息
	 * @param levelCode 学段代码
	 * @param years 界别
	 * @param discode 区县代码
	 * @param termId 选中学期
	 * @param currentTermId 当前学期
	 * @return
	 */
	public List<AppraisalWritedStaticseDto>querySJAppraiseStatics(String levelCode,List<String>years,String discode,String termId,String currentTermId);
	public List<String> querytest(String username,String flag,List<String>test);
}
