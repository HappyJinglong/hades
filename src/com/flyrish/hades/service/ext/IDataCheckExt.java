package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.PartInfoDto;

public interface IDataCheckExt {

	/**
	 * 查询学生的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<PartInfoDto> queryPartInfo(Map<String,Object> queryMap);
	
	/**
	 * 初中是三年制还是四年制
	 * @param queryMap
	 * @return
	 */
	public List<String> querygradelength(Map<String,Object> queryMap);
	
	/**
	 * 查询初中班主任的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<PartInfoDto> queryClassTeacher(Map<String,Object> queryMap);
	
	/**
	 * 查询高中班主任的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryClassTeacherGZ(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryApprasial(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryRecordpackage(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryLearnprocessWorks(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryPersonality(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryPractices(Map<String,Object> queryMap);
	
	/**
	 * 查询高中的评价填写情况
	 * @param queryMap
	 * @return
	 */
	public List<AppraisalDto> queryLearnprocessAppraisal(Map<String,Object> queryMap);
	
	
}
