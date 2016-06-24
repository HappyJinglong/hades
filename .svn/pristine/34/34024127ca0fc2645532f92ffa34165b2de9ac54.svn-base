package com.flyrish.hades.service.ext;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AapprasialCacheXieDto;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.AppraisalxieStirngDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.dto.UserDto;

public interface IPlayAndHealthExt {
	/**
	 * 查询学生的班级
	 * @param studentid  学生id
	 * @return 学生信息
	 */
	StudentxieDto findstudent(Integer studentid, Integer discode,
			Integer cmis30id);
      /**
       *  查询全班评价
       * @param classid 班级id
       * @param evaluateType 评价类型
       * @param termId  学期
       * @param cmis30id 
       * @param discode 
       * @param studentid 评价的id
       * @return 评价详情
       */
	List<AppraisalxieDto> selectSelfAppraiseXie(Integer classid, String evaluateType,
			String termId, String studentid, Integer discode, Integer cmis30id);
     /**
      * 保存评价
      * @param appraisal 评价内容
      * @param cmis30id 
      * @param discode 
      * @param termId 学段
     * @return 
      * @return 
      */
	 Integer insertSelfAppraisal(AppraisalxieDto appraisal, String termId, Integer discode, Integer cmis30id);
      /**
       * 更新评价
       * @param appraisal 评价内容
       */
	void updataAppraisal(AppraisalxieDto appraisal);
	/**
	 * 删除评价
	 * @param apprasialid  评价id
	 */
	void deleApprasial(Integer apprasialid);
	/**
	 * 查询全班班级
	 * @param student
	 * @return
	 */
	List<StudentxieDto> findClassStudent(String classid);
	/**
	 * 查询我对每个同学的所有评价
	 * @param studentid 学社id
	 * @return
	 */
	List<AppraisalxieDto> allAppraise(String termId, String studentid,
			Integer evaluatedPersonID, Integer cmis30id, Integer discode);
	/**
	 * 当前学期
	 * @param classid班级id
	 * @return
	 */
	String findStundentTermId(Integer classid);
	/**
	 * 修改时间
	 * @param apprasialid
	 * @param da  时间
	 */
	void updataTime(Integer apprasialid, Date da);
	/**
	 * 全部学生评价
	 * @param evaluateType 类型
	 * @param termId 学期
	 * @param studentid 学生id
	 * @param evaluatedPersonID
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	List<AppraisalxieDto> findGzReturnTree(String evaluateType, String termId,
			Integer studentid, Integer evaluatedPersonID, Integer cmis30id,
			Integer discode);
	/**
	 * 查全班同学
	 * @param classid 班级id
	 * @param studentid 学生id
	 * @param discode 
	 * @param cmis30id 
	 * @return
	 */
	List<SchoolTreeDto> getStudentInfoAll(String classid, Integer studentid, Integer cmis30id, Integer discode);
	/**
	 * 查全班同学
	 * @param classid 班级id
	 * @param studentid 学生id
	 * @param discode 
	 * @param cmis30id 
	 * @return
	 */
	List<SchoolTreeDto> getStudentInfoAll(String classid, String studentid, String cmis30id, String discode);
	/**
	 * 查全班同学
	 * @param classid 班级id
	 * @param studentid 学生id
	 * @param discode 
	 * @param cmis30id 
	 * @return
	 */
	StudentxieDto findstudentTreeNode(Integer studentid, Integer discode,
			Integer cmis30id);
	/**
	 * 查询评价
	 * @param apprasialid  评价id
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	SpeekDto findSpeek(Integer apprasialid, Integer discode, Integer cmis30id);
	/**
	 * 查询单个同学的评价
	 * @param termIdString 学期
	 * @param apperEduId   评价人的id
	 * @param evaluatedPersonID  类型
	 * @param cmis30id
	 * @param discode
	 * @param string
	 * @return 
	 */
	List<AapprasialCacheXieDto> findEVerApperasial(String termIdString, String apperEduId,
			Integer evaluatedPersonID, Integer cmis30id, Integer discode,
			String string);
	
	/**
	 * 缓存添加
	 * @param params  参数
	 * @param edu_id  教育id
	 * @param termIdString 学期
	 * @param evaluateType  类型
	 * @param string
	 * @param apperEduId  评价人id
	 * @param newId   新的id
	 * @param acd
	 */
	void addCacheApper(Map<String, Object> params, String edu_id,
			String termIdString, String evaluateType, String string,
			String apperEduId, String newId, AapprasialCacheDto acd);
	/**
	 * 删除 缓存
	 * @param stuEduid  教育id
	 * @param termIdString  学期
	 * @param evaluateType  类型
	 * @param string  主键
	 * @param apperEduId  被评价人id
	 * @param apprasialid  评价id
	 */
	void delectCacheGzApper(String stuEduid, String termIdString,
			String evaluateType, String string, String apperEduId,
			Integer apprasialid);
	/**
	 * 更改 缓存
	 * @param edu_id 教育id
	 * @param termIdString 学期
	 * @param evaluateType 类型
	 * @param string
	 * @param apperEduId 评价人id
	 * @param apprasialid  主键id
	 * @param aapprasialCacheDto 
	 * @param da
	 */
	void updataCachTime(String edu_id, String termIdString,
			String evaluateType, String string, String apperEduId,
			Integer apprasialid, AapprasialCacheDto aapprasialCacheDto, Date da);
	/**
	 * 更改 缓存中的
	 * @param edu_id 教育id
	 * @param termIdString 学期
	 * @param evaluateType 类型
	 * @param string
	 * @param apperEduId 评价人id
	 * @param apprasialid  主键id
	 * @param aapprasialCacheDto 
	 * @param da
	 */
	void updateCacheApper(String edu_id, String termIdString,
			String evaluateType, String string, String apperEduId,
			Integer apprasialid, AapprasialCacheDto aapprasialCacheDto,
			String apprasial, Date da);
	/**
	 * 查询
	 * @param classid  班级id
	 * @param evaluateType 类型
	 * @param termIdString 学期
	 * @param apperEduId 教育id
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	List<AppraisalxieStirngDto> selectSelfAppraiseXie(String classid,
			String evaluateType, String termIdString, String apperEduId,
			String discode, String cmis30id);
	/**
	 * 
	 * @param evaluateType  类型
	 * @param termId  学期
	 * @param eduId   教育
	 * @param apperEduId  评价人id
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	List<AppraisalxieStirngDto> findApprasial(String evaluateType,
			String termId, String eduId, String apperEduId, String discode,
			String cmis30id);
	/**
	 * 
	 * @param apprasialid  主键id
	 * @param apprasial  内容
	 * @param signDate  时间
	 */
	void updataNewAppraisal(String apprasialid, String apprasial,
			String signDate);
	
	
	
	 /**
	  * 变更后的保存
	  * @param appraisal
	  * @param termId
	  * @param discode
	  * @param cmis30id
	  * @return
	  */
	String insertSelfAppraisal(AppraisalxieStirngDto appraisal, String termId,
			String discode, String cmis30id,String eduId);

	

	
	
	
}
