package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Set;

import com.flyrish.hades.dto.CountryInfoDto;
import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.SchoolUploadInfoDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;

public interface IBaseInforManagerExt {
	/**
	 * 根据用户ID获取登录用户的角色信息
	 * @param userId 登录用户标识号
	 * @param campuseId 学期标识号
	 * @return 角色集合
	 */
	public List<UserRoleDto> queryUserRoleByUserId(String userId ,String campuseId);
	/**
	 * 根据登录用户标识号查询该用户所拥有的校区，并且过滤掉只含有小学的校区
	 * @param userId 登录用户标识号
	 * @return 校区集合，如果userId 为Null，则返回Null
	 */
	public List<UserSchoolDto> queryUserSchoolByUserId(String userId);
	
	/**
	 * 查询校区名称
	 * @param userId 登录用户标识号
	 * @return 校区集合，如果userId 为Null，则返回Null
	 */
	public List<UserSchoolDto> queryCampuseByCampuseid(Set<String> campuseId);
	/**
	 * 通过discode查询某区县的上传的统计信息
	 * @param discode 区县代码
	 * @return 统计信息集合
	 */
	public List<CountryInfoDto> queryCountryInfoDtoByDiscode(String discode);
	/**
	 * 通过discode,上传文件类型，是否已已上传成功
	 * @param db 文件类型
	 * @param discode 区县代码
	 * @param isupload null=》未上传  or =》已上传
	 * @return 文件信息集合
	 */
	public List<SchoolUploadInfoDto> querySchoolUploadInfoDtoByCondition(String db,String discode,String isupload);
	/**
	 * 返回学段集合
	 * @param unitlevel 用户级别
	 * @param userId 用户标识号
	 * @param roleType 角色类型
	 * @param campuseid 校区标识号
	 * @param roleId 角色标识号
	 * @return 学段集合
	 */
	public List<EdusysDto> queryEdusysDtoByInfo(String unitlevel,String userId,String roleType,String campuseid,String roleId);
	/**
	 * 通过表名获取主键标识号
	 * @param tablename 表名
	 * @return if tablename is null ,return null. if the table is invalid,return null
	 */
	public String queryProKey(String tablename);
}
