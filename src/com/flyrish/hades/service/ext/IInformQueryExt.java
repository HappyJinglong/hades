package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.IPage;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.dto.SchoolTreeDto;

public interface IInformQueryExt {
    
	/**
	 * 当教务老师和德育老师登录时，获取可选的学段
	 * @param cmis30id   
	 * @param dicode
	 * @param cmapusid 
	 * @return
	 */
	public List<String> schoolQuerylevel(String cmis30id,String discode,String campusid);
	
	/**
	 * 新增通告
	 * @param params 参数
	 */
	public void insertInform(Map<String,Object> params);
	/**
	 * 班主任所在班级的学段
	 * @param personid
	 * @return
	 */
	public List<String> masterQuerylevel(String personid);
	/**
	 * 查询通告列表
	 * @param params  查询条件
	 * @param pageNo   第几页
	 * @param pageSize  每页多少条
	 * @return
	 */
	public IPage<InformDto> informQuery(Map<String,Object> params,Integer pageNo,Integer pageSize);
	/**
	 * 删除通告 
	 * @param params 删除条件
	 */
	public void deleteInform(Map<String,Object> params);
	/**
	 * 修改通告的发布状态
	 * @param params  informIds
	 */
	public void publicInform(Map<String, Object> params);
	/**
	 * 更新通告内容
	 * @param params  更新的内容
	 */
	public void updateInform(Map<String, Object> params);
	/**
	 * 查询list（不用于分页）
	 * @param params
	 * @return
	 */
	public List<InformDto> informlistQuery(Map<String,Object> params);
	/**
	 * 查询班主任的用户id
	 * @param params
	 * @return
	 */
	public String queryUserid(Map<String,Object> params);
	/**
	 * 插入已读记录
	 * @param params
	 */
	public void insertRead(Map<String,Object> params);
	
	/**
	 * 插入已读记录前验重
	 * @param params
	 */
	public int beforeInsertRead(Map<String,Object> params);
	/**
	 * 获取最新的20条数据
	 * @return
	 */
	public List<AppraisalDto> queryAppraisal(Map<String,Object>params,String levelcode,String usertype);
	/**
	 * 获取班主任所有学生的信息
	 * @param params
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params);
	/**
	 * 查询已读未读的通知公告
	 * @param informDtos
	 */
	public void setHoiRead(List<InformDto> informDtos);
}
