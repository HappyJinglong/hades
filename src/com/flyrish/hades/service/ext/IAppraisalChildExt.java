package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;

import com.flyrish.hades.dto.AppraisalDto;

public interface IAppraisalChildExt {
    
	/**
	 * 根据教育id和学期id,评价人获取评论链表
	 * @param edu_id 教育id
	 * @param semesterid 学期id
	 * @param appraser 签名
	 * @return
	 */
	public List<AppraisalDto> getAppraisalChildList(AppraisalDto appraisal,Integer levelcode);
	/**
	 * 增加家长评论
	 * @param appraisal 评价
	 * @param levelcode  高中和初中的学段
	 * @return 当前插入的id
	 */
	public String InsertAppraisalChild(AppraisalDto appraisal,Integer levelcode);
	/**
	 * 根据评价id删除评价
	 * @param appraisalid 评价id
	 * @return
	 */
	public int DeleteAppraisalChild(String appraisalid,Integer levelcode);
	/**
	 * 更新评价
	 * @param listappraisal 评价对象
	 * @return
	 */
	public int UpdateAppraisalChildList(AppraisalDto appraisal,Integer levelcode);
	/**
	 * 根据教育id获取学生信息
	 * @param edu_id 教育id
	 * @return
	 */
	public String getParentInfo(String edu_id,String cmis30id,String discode);
	/**
	 * 返回ISqlElement对象
	 * @param params  参数
	 * @param sqlString sql的别名
	 * @return ISqlElement对象
	 */
    public ISqlElement getIsqlElement(Map<String,Object> params,String sqlString);
}
