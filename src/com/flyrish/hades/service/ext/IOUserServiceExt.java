package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.SchoolInfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;


public interface IOUserServiceExt {

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return
	 */
	public UserDto findLoginUserByUserName(String username,String password,String schoolUserId);

	/**
	 * 根据用户ID获取用户角色
	 * @param userid
	 * @param schoolid 校区ID
	 * @return 
	 */
	public List<UserRoleDto> queryUserRoleByUserId(String userid,String campuseId);

	/**
	 * 根据用户ID、角色ID
	 * 查询用户所拥有的菜单
	 * @param level
	 * @param userId
	 * @param roleId
	 * @param layer
	 * @return
	 */
	public List<FuncTreeDto> queryAllFuncTree(String userId,String roleId,Integer funcLevelType,Integer layer);
	/**
	 * 只查询成绩录入功能菜单
	 * @return
	 */
	public List<FuncTreeDto> queryScoreTree();
	
	/**
	 * 根据学生ID获取学段类型
	 * @param personid		学生标识号
	 * @return UserDto		学段类型
	 */
	public List<UserDto> queryLevelCodeByStudentId(Integer personid);
	
	/**
	 * 根据eduId查询家长levelCode
	 * @param eduId
	 * @return
	 */
	public List<UserDto> queryPatriarchLevelCodeByEduId(String eduId);
	
	/**
	 * 根据教师ID获取学段类型
	 * @param personid      教师ID
	 * @return UserDto		学段类型
	 */
	public List<UserDto> queryLevelCodeByTeacherId(Integer personid);

	/**
	 * 处理一个用户在多个学校的问题
	 * @param userName
	 * @return
	 */
	public List<SchoolInfoDto> querySchoolInfoDtoByUserName(List<String> cmis30ids);

	/**
	 * 根据用户ID、用户角色ID
	 * 获取用户的角色类型
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public List<UserRoleDto> queryUserRoleTypeByUserId(Integer userId,Integer roleId);
	/**
	 * 获取当前有效的学期
	 * @return
	 */
	public Map<String,String> queryCurrentTermid();
	/**
	 * 通过学校标识号查询该学校所在的区县代码以及学校名称
	 * @param cmis30id 学校标识号
	 * @return String
	 */
	public Map<String,String> queryDiscodeAndSchoolName(String cmis30id,String teacherid);
	/**
	 * 查询学生相关信息
	 * @param edu_id 学生教育id
	 * @param termid 当前学期标识号
	 * @return
	 */
	public Map<String,String>queryStudentInfo(String edu_id,String termid);
	/**
	 * 通过roleid查询roleRealType
	 * @param roleId
	 * @return
	 */
	public String queryRoleRealType(String roleId);
	
}
