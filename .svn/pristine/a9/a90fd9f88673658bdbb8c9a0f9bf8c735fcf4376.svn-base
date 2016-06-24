package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.ViewDto;

public interface IScoreExt {
	/**
	 * 获取学年
	 * @return 
	 */
	public List<String> getYears(String  manyYears);
	
	/**
	 * 根据学年获取当前班的历史班级id
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	public List<String> getClassIdBySchoolyearAndClassid(String schoolyear,String classid);
	/**
	 * 获取教务老师管辖班级
	 */
	public List<CampusDto> getJWLSClass(String levelCode,String cmis30id,String campusid);
	/*
	 * 根据学段获取学科
	 */
	public List<KsysSubjectDto> getSubjectBySegmentId(String cmis30id,String segmentId,String courseType,String teacherid,String classid);
	/*
	 * 根据学段获取学科
	 */
	public List<KsysSubjectDto> getSubjectBySegmentId(String cmis30id,String segmentId,String courseType,String teacherid,String classid,String schoolyear);
	
	/**
	 * 通过学生反推学科
	 * @param cmis30id 学校标识号
	 * @param segmentId 学段标识号
	 * @param courseType 科目类型
	 * @param classid 班级标识号
	 * @return 科目集合
	 */
	public List<KsysSubjectDto> getSubjectBySegmentIdAndStudents(String cmis30id,String discode,String segmentId,String courseType,String classid);
	/**
	 * 根据学科和课程类型获取课程模块
	 */
	public List<KcourseDto> getCourseBySubjectIdAndCourseType(String cmis30id,String courseKind,String subjectId,String segmentId,String teacherid,String classid);
	
	/**
	 * 根据学科和课程类型获取课程模块
	 */
	public List<KcourseDto> getCourseBySubjectIdAndCourseType(String cmis30id,String courseKind,String subjectId,String segmentId,String teacherid,String classid,String schoolyear);
	
	/**
	 * 根据学科和课程类型获取课程模块（反向推理）
	 */
	public List<KcourseDto> getCourseBySubjectIdAndCourseTypeByStudents(String cmis30id,String discode,String subjectId,String segmentId,String classid);
	/**
	 * 内置课程查询模块学分、学时、任课老师
	 * @param segmentId
	 * @param classid
	 * @param discode
	 * @param courseId
	 * @param subjectId
	 * @param cmis30id
	 * @param courseKinds
	 * @return
	 */
	public List<ViewDto> getNZXFAndXSAndTeacherName(String segmentId,String classid,String discode,String courseId,String subjectId,String cmis30id,String teacherid,String usertype,String userRealType);
	/**
	 * 内置课程查询模块学分、学时、任课老师
	 * @param segmentId
	 * @param classid
	 * @param discode
	 * @param courseId
	 * @param subjectId
	 * @param cmis30id
	 * @param courseKinds
	 * @return
	 */
	public List<ViewDto> getNZXFAndXSAndTeacherName(String segmentId,String classid,String discode,String courseId,String subjectId,String cmis30id,String teacherid,String usertype,String userRealType,String schoolyear);
	
	public List<ViewDto> getNZXFAndXSAndTeacherNameByStudents(String segmentId,String classid,String discode,String courseId,String subjectId,String cmis30id);
	/**
	 * 校本课程查询模块学分、学时、任课老师
	 * @param segmentId
	 * @param discode
	 * @param courseId
	 * @param subjectId
	 * @param cmis30id
	 * @param courseKind
	 * @return
	 */
	public List<ViewDto> getSchoolXFAndXSAndTeacherName(String segmentId,String discode,String courseId,String subjectId,String cmis30id,String courseKind,String teacherid,String usertype,String userRealType);

	/**
	 * 内置课程查询成绩 
	 * @param classid
	 * @param class_model_id
	 * @return
	 */
	public List<ModelScoreDto> getNZScore(String classid,String preclassid,String class_model_id,Double xs,String discode,String cmis30id,String subject_id);
	/**
	 * 内置课程查询成绩 
	 * @param classid
	 * @param class_model_id
	 * @return
	 */
	public List<ModelScoreDto> getNZScore(String classid,String preclassid,String class_model_id,Double xs,String discode,String cmis30id,String subject_id,String schoolyear);
	
	/**
	 * 内置课程查询成绩 
	 * @param classid
	 * @param class_model_id
	 * @return
	 */
	public List<ModelScoreDto> getNZScore2(String classid,List<ViewDto> dtos,String discode,String cmis30id,Double xs);
	
	
	public void updateStudentScoreClassModel(List<String> classmodelids,String class_model_id);
	
	/**
	 * 校本课程查询成绩
	 * @param classid
	 * @param class_model_id
	 * @return
	 */
	public List<ModelScoreDto> getSchoolScore(String segment_course_id,Double xs,String discode,String cmis30id,String subject_id);

	/**
	 * 内置课程根据ClassModelId查询成绩
	 */
	public List<ModelScoreDto> getNZScoreByClassModelId(String class_model_id,String discode,String cmis30id);
 	
	/**
	 * 内置 成绩插入
	 * @param modelScores
	 * @param courseType
	 */
	public void insertNZScore(List<ModelScoreDto> modelScores,String courseType,String enteringScore,String cmis30id,String disCode,String class_model_id);

	/**
	 * 根据界别、班级号、姓名查询学生
	 * @param cmis30id
	 * @param disCode
	 * @param studentNames
	 * @param graduateyears
	 * @param classnums
	 * @return
	 */
	public List<ModelScoreDto> getStudentInfoByGraduateyearAndClassnumAndNameAndEduId(String cmis30id,String disCode,List<String> studentNames,List<String> graduateyears,List<String> classnums,List<String>schoolyears);
	
	
	
	/**
	 * 根据学科名称和模块名称
	 * @param cmis30id
	 * @param courseNames
	 * @param subjectNames
	 * @return
	 */
	public List<ModelScoreDto> getCourseByCourseNameAndSubjectName(String cmis30id,List<String> courseNames,List<String> subjectNames);
	/**
	 * 判断学生是否在校本课程中
	 * @param schoolyearNames
	 * @param segmentNames
	 * @param courseIds
	 * @param classids
	 * @param studentids
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<ModelScoreDto> xbCourseIsStudent(List<String> schoolyearNames,List<String> segmentNames,List<String> courseIds,List<String> classids,List<String> studentids,String discode,String cmis30id);

	/**
	 * 判断学生是否在内置课程中
	 * @param schoolyearNames
	 * @param segmentNames
	 * @param courseIds
	 * @param classids
	 * @param studentids
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	public List<ModelScoreDto> nZCourseIsStudent(List<String> schoolyearNames,List<String> segmentNames,List<String> courseIds,List<String> classids,List<String> studentids,String discode,String cmis30id);

	/**
	 * 导入成绩
	 * @param modelScores
	 * @param courseType
	 */
	public void exportScore(List<ModelScoreDto> modelScores,String courseType,String enteringScore,String cmis30id,String disCode,Boolean isInsert);
	
	/**
	 * 学生查询内置课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> studentGetNZScore(String segment_id,String classid,String edu_id,String cmis30id,String discode);
	/**
	 * 学生查询内置课程成绩 (多个学生)
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> studentGetNZScore(String segment_id,String classid,List<String> edu_id,String cmis30id,String discode);
	/**
	 * 学生查询校本课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> studentGetSchoolScore(String segment_id,String classid,String edu_id,String cmis30id,String discode);
	/**
	 * 学生查询校本课程成绩 
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_ids
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> studentGetSchoolScore(String segment_id,String classid,List<String> edu_ids,String cmis30id,String discode);
	/**
	 * 根据eduid获取学生名称
	 * @param cmis30id
	 * @param discode
	 * @param eduId
	 * @return
	 */
	public List<ModelScoreDto> GetStudentNameByEduId(String cmis30id,String discode,String eduId);
	
	/**
	 * 根据学科名称集获得系统学科
	 * @param subjectNames
	 * @param studentids
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> getSubjectName(List<String> subjectNames);
	
	/**
	 * 查询会考成绩
	 * @param studentids
	 * @param subjectIds
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> getGENERAL_EXAMINATION_SCORE(List<String> studentids,List<String> subjectIds,String cmis30id,String discode);

	/**
	 * 会考成绩插入-更新
	 * @param modelScores
	 * @param courseType
	 */
	public void hKScoreInsertOrUpdate(List<ModelScoreDto> modelScores,String cmis30id,String disCode,Boolean isInsert);
	/**
	 * 查询用户角色
	 * @param params
	 * @return
	 */
	public List<String>queryUserRoleType(Map<String,Object> params);

	/**
	 * 查询研究性学习相关信息
	 * @param schoolyearNames
	 * @param segmentNames
	 * @param studyTopic
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<ModelScoreDto> queryStudyProblems(List<String> schoolyearNames,
			List<String> segmentNames, List<String> studyTopic,
			String cmis30id, String discode);

	/**
	 * 保存或者更新成绩
	 * @param params
	 */
	public void saveOrUpdateScores(Map<String, Object> params);
	/**
	 * 删除成绩
	 * @param nIds 内置课程标识号集合
	 * @param xIds 校本课程标识号集合
	 */
	public void delCourseById(List<String> nIds,List<String> xIds);
	/**
	 * 初始化未存在的课程模块
	 */
	public void initClassModelAndSegmentModel(List<ModelScoreDto> dtos,String cmis30id);
	
	/**
	 * 学生查询内置课程成绩 
	 * @param subjectName
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	List<ModelScoreDto> studentGetNZScore(String subjectName, String segment_id,
			String classid, List<String> edu_ids, String cmis30id,
			String discode);
	/**
	 * 学生查询校本课程成绩 
	 * @param subjectName
	 * @param schoolyear
	 * @param segment_order
	 * @param classid
	 * @param edu_id
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	List<ModelScoreDto> studentGetSchoolScore(String subjectName,
			String segment_id, String classid, List<String> edu_ids,
			String cmis30id, String discode);
	/**
	 * 查询对应的考试是否存在相应的记录
	 * @param data 数据结构
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @return
	 */
	public Map<String,String> queryClassModelAndCreditByCondition(ModelScoreDto data,String cmis30id,String discode,String historyclassid);
	/**
	 * 获取class_model_id标识号
	 * @param dto 数据载体
	 * @param cmis30id 学校标识号
	 */
	public String getClassModelAndSegmentModelId(ModelScoreDto dto,String cmis30id,String historyclassid);
	
}
