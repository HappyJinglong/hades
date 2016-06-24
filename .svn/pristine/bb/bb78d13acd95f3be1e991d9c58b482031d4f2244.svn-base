package com.flyrish.hades.service.ext;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolNameDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.dto.UserDto;

public interface ICzPlayAndHealthExt {
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

	List<PartInfoXieDto> selectSelfAppraiseXie(Integer studentid, Integer classid,
			Integer termid, String name, String two_par_id, Integer discode,
			Integer cmis30id);
	/**
      * 保存评价
      * @param appraisal 评价内容
      * @param cmis30id 
      * @param discode 
      * @param termId 学段
      */
	String czinsertSelfAppraisal(PartInfoXieDto appr);
	/**
       * 更新评价
       * @param appraisal 评价内容
       */
	void czupdataAppraisal(PartInfoXieDto appr);
	/**
	 * 删除评价
	 * @param apprasialid  评价id
	 */
	/*void deleApprasial(Integer apprasialid);*/
	void deleApprasial(String part_id,Integer discode, Integer cmis30id);
	/**
	 * 查询全班班级
	 * @param student
	 * @return
	 */
	List<StudentxieDto> findClassStudent(StudentxieDto student);
	/**
	 * 查询我对每个同学的所有评价
	 * @param studentid 学社id
	 * @return
	 */
	List<PartInfoXieDto> czAllAppraise(String signer_name, Integer termid,
			String evaluatedPersonID, Integer cmis30id, Integer discode);
	List<PartInfoXieDto> czfindreturn(String signer_name, String termid,
			String evaluatedPersonID, Integer cmis30id, Integer discode,
			String two_part_id);
	/**
	 * 时间更新
	 * @param part_id 主键
	 * @param da   时间
	 * @param cmis30id 
	 * @param discode 
	 */
	void czupdataTime(String part_id, Date da, Integer discode, Integer cmis30id);
	/**
	 * 
	 * @param part_id  主键
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	SpeekDto czfindSpeek(String part_id, Integer discode, Integer cmis30id);
	/**
	 * 添加
	 * @param edu_id  教育id
	 * @param termid   学期
	 * @param two_part_id  类型
	 * @param string
	 * @param apperEduId  评价人的id
	 * @param newId   新主键
	 * @param cacheDto
	 * @param da
	 */
	void addCachApper(String edu_id, String termid, String two_part_id,
			String string, String apperEduId, String newId,
			PartInfoCacheDto cacheDto, Date da);
	/**
	 * 删除
	 * @param edu_id   教育
	 * @param termidString  学期
	 * @param two_part_id  类型
	 * @param string
	 * @param apperEduId  评价人id
	 * @param part_id
	 * @param discode
	 * @param cmis30id
	 */
	void deleApprasialCade(String edu_id, String termidString,
			String two_part_id, String string, String apperEduId, String part_id, Integer discode, Integer cmis30id);
	/**
	 * 更新
	 * @param edu_id  教育
	 * @param termidString 学期
	 * @param two_part_id 类型
	 * @param string
	 * @param apperEduId  评价人
	 * @param part_id   主键
	 * @param cacheDto
	 * @param da    时间
	 * @param discode
	 * @param cmis30id
	 */
	void czupdataTimeCade(String edu_id, String termidString,
			String two_part_id, String string, String apperEduId,
			String part_id, PartInfoCacheDto cacheDto, Date da,
			Integer discode, Integer cmis30id);
	/**
	 *   更新
	 * @param edu_id  教育
	 * @param termidString  学期
	 * @param two_part_id  类型
	 * @param string
	 * @param apperEduId  评价人
	 * @param part_id   主键
	 * @param cacheDto
	 * @param appr1
	 */
	void czupdataAppraisalCade(String edu_id, String termidString,
			String two_part_id, String string, String apperEduId,
			String part_id, PartInfoCacheDto cacheDto, PartInfoXieDto appr1);
	
	/**
	 * 查询学生
	 * @param studentid  学生id
	 * @param classid   班级id
	 * @param termId    学期
	 * @param apperEduId
	 * @param two_part_id  类型
	 * @param discode
	 * @param cmis30id
	 * @return
	 */
	List<PartInfoXieDto> selectClassStudent(Integer studentid, Integer classid,
			Integer termId, String apperEduId, String two_part_id, Integer discode, Integer cmis30id);
	/**
	 * 
	 * @param studentid  学生id
	 * @param classid  班级
	 * @param termId   学期
	 * @param apperEduId  被评价人
	 * @param two_part_id  类型
	 * @param discode      
	 * @param cmis30id
	 * @return
	 */
	List<PartInfoXieDto> selectClassStudentCade(Integer studentid,
			Integer classid, Integer termId, String apperEduId,
			String two_part_id, Integer discode, Integer cmis30id);
	String findGreedLenth(String cmis30Id, String levelcode);
	String findclassid(String cmis30Id, String discode, String string);
	Map<String,String>  findth(String cmis30Id, String discode, String levelid, String termId);
	List<SchoolNameDto> findShool(String schoolName, String discode, String string);

	
	
	
	
	
	

	
}
