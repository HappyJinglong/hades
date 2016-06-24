package com.flyrish.hades.service.ext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.LearnprocessAppraisaDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.SubjectDto;

public interface IMasterAppriseExt {
	/*
	 * 从缓存中取数据
	 */
	/**
	 * 高中栏目号
	 * @return
	 */
	public List<String> queryGZSectionsFromDB();
	/**
	 * 初中栏目号
	 * @return
	 */
	public List<String> queryCZSectionsFromDB();
	/**
	 * 从缓存中获取教师评价学生信息
	 * @param studentInfos 学生基本信息
	 * @param termId 学期id
	 * @param sectionIds 栏目号
	 * @param appraseridentify 评价人类型标识号
	 * @param appraiserrid 评价人id
	 * @param flag 标识号 暂时没想好
	 * @param params 查询数据库参数
	 * @return 老师评价学生的所有信息
	 */
	public List<AppraisalDto> queryAppraiseFromRedis(List<SchoolTreeDto> studentInfos,String termId,List<String> sectionIds,String appraseridentify,String appraiserrid,String flag,Map<String,Object> params);
	/**
	 * 将数据保存至缓存中
	 * @param aDto
	 * @return
	 */
	public String insertAppraisalToCache(AppraisalDto aDto);
	/**
	 * 更新缓存中的数据
	 * @param aDto
	 */
	public void updateAppraiseToCache(AppraisalDto aDto);
	/**
	 * 删除缓存数据
	 * @param aDto
	 */
	public void deleteAppraiseCache(AppraisalDto aDto);
	/**
	 * 添加高中数据进缓存
	 * @param aDto
	 * @return
	 */
	public String insertAppraisalToCacheHigh(AppraisalDto aDto);
	/**
	 * 更新缓存中的数据高中
	 * @param aDto
	 */
	public void updateAppraiseToCacheHigh(AppraisalDto aDto);
	/**
	 * 删除缓存数据高中
	 * @param aDto
	 */
	public void deleteAppraiseCacheHigh(AppraisalDto aDto);
	/**
	 * 从缓存中获取课程评语
	 * @param studentInfos
	 * @param termId
	 * @param sectionIds
	 * @param appraseridentify
	 * @param appraiserrid
	 * @param flag
	 * @param params
	 * @return
	 */
	public List<AppraisalDto> queryProcessAppraiseFromRedis(List<SchoolTreeDto> studentInfos,String termId,String sectionId,String appraseridentify,String appraiserrid,String flag,Map<String,Object> params);
	/**
	 * 添加高中课程评语数据进缓存
	 * @param aDto
	 * @return
	 */
	public String insertProcessAppraisalToCacheHigh(AppraisalDto aDto);
	/**
	 * 更新缓存中的数据高中 课程评语
	 * @param aDto
	 */
	public void updateProcessAppraiseToCacheHigh(AppraisalDto aDto);
	/**
	 * 删除缓存数据高中 课程评语
	 * @param aDto
	 */
	public void deleteProcessAppraiseCacheHigh(AppraisalDto aDto);
	/*
	 *从数据库中查询数据 
	 */
	/**
	 * 根据校区id获取校区相关信息
	 * @param params 校区campusid cimis30id discode
	 * @return 校区信息
	 */
	public CampusDto getCampusByCampusId(Map<String,Object> params);
	/**
	 * 查询学段信息
	 * @param campusId 校区id
	 * @param classids 班级id
	 * @return
	 */
	/**
	 *  查询学段信息
	 * @param params ids:校区campusid、学校cimis30id、区县discode、班级classIds
	 * @return
	 */
	public List<SchoolTreeDto> getEduysForMenu(Map<String,Object> params);
	/**
	 * 查询年级信息
	 * @param params ids::学段levelid、学校cimis30id、区县discode、班级classIds
	 * @return
	 */
	public List<SchoolTreeDto> getGradesInfo(Map<String, Object> params);
	/**
	 * 查询班级信息
	 * @param params ids::年级gradeid、学校cimis30id、区县discode、班级classIds
	 * @return
	 */
	public List<SchoolTreeDto> getClasssInfo(Map<String, Object> params);
	/**
	 * 查询班级管辖下学生
	 * @param params ids::年级gradeid、学校cimis30id、区县discode、班级classIds
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params);
	/**
	 * 获取班主任管辖班级下所有学生的所有栏目评语（不包括班主任评语和课程评语）
	 * @param params
	 * @return
	 */
	public List<AppraisalDto> getAllCommonAppraisalInfos(Map<String, Object> params);
	/**
	 * 获取班主任评语
	 * @param params
	 * @return
	 */
	public List<AppraisalDto>getMasterAppraisalInfos(Map<String, Object> params);
	/**
	 * 获取班主任课程评语
	 * @param params
	 * @return
	 */
	public List<AppraisalDto>getLearnProcessAppraisalInfos(Map<String, Object> params);
	/**
	 * 获取班主任管辖班级信息
	 * @param params 班主任 teacherId、学校cimis30id、区县discode、学段 levelcode
	 * @return
	 */
	public List<CampusDto>getClassInfos(Map<String, Object> params);
	/**
	 * 获取任课老师管辖班级信息
	 * @param params 班主任 teacherId、学校cimis30id、区县discode、学段 levelcode
	 * @return
	 */
	public List<CampusDto>getTeacherClassInfos(Map<String, Object> params);
	
	/**
	 * 插入班主任评价学生信息（班主任评语）
	 * @param dto
	 */
	public String insertMasterApprise(AppraisalDto dto);
	/**
	 *  更新班主任评价学生信息（班主任评语）
	 * @param aDto
	 */
	public void updateMasterApprise(AppraisalDto aDto);
	/**
	 * 插入班主任评价学生信息（班主任其他评语）
	 * @param aDto
	 */
	public String insertCommonMasterApprise(AppraisalDto aDto);
	/**
	 * 更新班主任评价学生信息（班主任其他评语）
	 * @param aDto
	 */
	public void updateCommonMasterApprise(AppraisalDto aDto);
	/**
	 * 删除班主任评语
	 * @param aDto
	 */
	public void deleteMasterApprise(AppraisalDto aDto);
	/**
	 * 删除班主任其他评语
	 * @param aDto
	 */
	public void deleteCommonMasterApprise(AppraisalDto aDto);
	/**
	 *  删除班主任课程评语
	 * @param aDto
	 */
	public void deleteMasterProcessApprise(AppraisalDto aDto);
	/**
	 * 更新班主任评价学生信息（班主任课程评语）
	 * @param aDto
	 */
	public void updateMasterProcessApprise(AppraisalDto aDto);
	/**
	 * 插入班主任评价学生信息（班主任课程评语）
	 * @param aDto
	 */
	public String insertMasterProcessApprise(AppraisalDto aDto);
	/**
	 * 获取初中评价信息
	 * @param params
	 * @return
	 */
	public List<AppraisalDto>getCZMasterApprise(Map<String, Object> params);
	/**
	 * 插入初中评语
	 * @param aDto
	 */
	public String insertCZMasterApprise(AppraisalDto aDto);
	/**
	 * 更新初中评语
	 * @param aDto
	 */
	public void updateCZMasterApprise(AppraisalDto aDto);
	/**
	 * 删除评语信息
	 * @param aDto
	 */
	public void deleteCZMasterApprise(AppraisalDto aDto);
	/**
	 * 获取课程信息
	 * @return
	 */
	public List<SubjectDto>getCZSubjectInfos();
	/**
	 * 查询高中历史学期id
	 * @param classId
	 * @param gradeNum
	 * @param num
	 * @return
	 */
	public String getHSHistoryTermId(String classId,String gradeNum,String num);
	/**
	 * 根据父类id查询评价类型 
	 * @param upappraisaltypeid
	 * @return
	 */
	public List<String> getAppraisalTypeByUpAppraisalTypeId(Integer upappraisaltypeid);
	
	/**
	 * 高中评价查询
	 * @param appraisaltypeids
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<AppraisalDto> getAppraisal(List<String> appraisaltypeids,String semesterid,List<String> eduIds,String discode,String cmis30id);
	/***
	 * 高综_记录袋查询
	 * @param appraisaltypeid
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<RecordPackageDto> getRecordpackage(String appraisaltypeid,String semesterid,List<String> eduIds,String discode,String cmis30id);
	/**
	 * 高中学科学习过程记录
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<LearnprocessWorksDto> getLearnprocessWorks(String semesterid,List<String> eduIds,String discode,String cmis30id);

	/**
	 * 高中_学业成就_学科学习过程记录_评语
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<LearnprocessAppraisaDto> getLearnprocessAppraisa(String semesterid,List<String> eduIds,String discode,String cmis30id);

	/**
	 * 高中_个性与发展_基本情况
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<PersonalityDto> getPersonality(String semesterid,List<String> eduIds,String discode,String cmis30id);

	/**
	 * 高中_综合实践活动
	 * @param semesterid
	 * @param eduIds
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<PracticesDto> getPractices(List<String> appraisaltypeids,String semesterid,List<String> eduIds,String discode,String cmis30id);
	/**
	 * 获取高中任课老师管辖班级信息
	 * @param params
	 * @return
	 */
	public List<CampusDto> getHighSchoolClassInfos(Map<String, Object> params);
	/**
	 * 获取高中老师授课信息
	 * @param params
	 * @return
	 */
	public List<SubjectDto> getGZSubjectInfos(Map<String,Object> params);
	/**
	 * 高中评价查询  缓存
	 * @param appraisalTypeIds
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<AppraisalDto> getAppraisalFromCache(List<String> appraisalTypeIds, String zsTermId, List<String> eduIds);
	/**
	 * 查询记录袋
	 * @param typeSxjld
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<RecordPackageDto> getRecordpackageFromCache(String typeSxjld,String zsTermId, List<String> eduIds);
	/**
	 * 个性发展
	 * @param typeGxfzJbqk
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<PersonalityDto> getPersonalityFromCache(String typeGxfzJbqk,String zsTermId, List<String> eduIds);
	/**
	 * 学分课程记录
	 * @param typeXyGcjl
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<LearnprocessWorksDto> getLearnprocessWorksFromCache(String typeXyGcjl, String zsTermId, List<String> eduIds);
	/**
	 * 课程评语
	 * @param typeKeChengPingyu
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<LearnprocessAppraisaDto> getLearnprocessAppraisaFromCache(String typeKeChengPingyu, String zsTermId, List<String> eduIds);
	/**
	 * 查询社会实践活动
	 * @param appraisalTypeIds
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<PracticesDto> getPracticesFromCache(List<String> appraisalTypeIds, String zsTermId, List<String> eduIds);
	/**
	 * 查询社会实践活动
	 * @param appraisalTypeIds
	 * @param zsTermId
	 * @param eduIds
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfoXie(Map<String, Object> params1);
	/**
	 * 
	 * @param params1 参数
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfoXieBook(Map<String, Object> params1);
	/**
	 * 
	 * @param params1 参数
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfoXieBookNull(
			Map<String, Object> params1);
	
	
	/**
	 * 
	 * @param params1 参数
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfoXieBookSueed(
			Map<String, Object> params1);
	/**
	 * 获取高中所有学科
	 * @return
	 */
	public List<SubjectDto> getGZSubjectInfos();

}
