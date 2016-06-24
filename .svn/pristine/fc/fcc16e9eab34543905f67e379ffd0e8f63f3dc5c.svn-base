package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;

public interface ILoginUserInfoServiceExt {

	/**
	 * 获取当年学年学期
	 * @return termDtosn	学期列表
	 */
	public List<TermDto> queryTerm();

	/**
	 * 获取登录用户所属学校的校名
	 * @param userid
	 * @return userSchoolDtoes  学校名列表
	 */
	public List<UserSchoolDto> queryUserSchool(Integer userid);

	/**
	 * 获取登录用户所属校区
	 * @param userid
	 * @return userCampusDtoes 校区列表
	 */
	public List<UserSchoolDto> queryUserSchoolCampus(String userid);
	/**
	 * 根据具体角色查找角色
	 * @param username 用户名称
	 * @param cmis30id 学校标识号
	 * @param userid 用户标识号
	 * @return
	 */
	public List<Map<String,String>> queryListRoleTypes(String userid,String username,String cmis30id);
	
	/**
	 * 根据用户名或用户ID查询用户所在学段
	 * @param userId
	 * @param userName
	 * @return  userDto  学段列表
	 */
	public List<UserDto> queryUserLevelCode(String userId,String userName);
	
	/**
	 * 根据校区标识号或用户ID查询用户所在学段
	 * @param userId 用户标识号
	 * @param campusId 校区标识号
	 * @return  userDto  学段列表
	 */
	public List<UserRoleDto> queryUserRolesByUserIdAndCampusId(String userId,String campusId);
	/**
	 * 通过登录名称查询校区(针对教务老师)
	 * @param username 用户名
	 * @param cmis30id 校区标识号
	 * @param campuseids 校区标识号集合
	 * @return
	 */
	public List<Map<String,String>> queryCampuseIdBySchoolAdmin(String username,String cmis30id,List<String>campuseids);
	/**
	 * 通过登录名称查询校区（班主任及任课老师，德育老师）
	 * @param username 用户名
	 * @param cmis30id 校区标识号
	 * @return
	 */
	public List<Map<String,String>> queryCampuseIdBySchoolTeacher(String teacherid,String username,String cmis30id,String sql,List<String>campuseids);

	
	/**
	 * 根据用户ID修改密码
	 * @param userId
	 * @param newPwd
	 * @return boolean
	 */
	public boolean updatePwd(String userId, String newPwd);

	
	/**
	 * 根据用户ID、校区ID、角色ID
	 * 登录用户所属级别(市级/区县/校级)
	 * 获取校区所对应的角色ID、角色名、学段标号、学段名称
	 * @param unitlevel
	 * @param userId
	 * @param campuseId
	 * @return
	 */
	public List<UserRoleDto> queryRoleNameAndLevelName(
			Integer unitlevel, Integer userId, Integer campuseId,Integer roleId,Integer roleType,Integer levelCode);

	/**
	 * 根据登录用于的levelId
	 * 获取用户levelCode
	 * @param levelId
	 * @return
	 */
	public List<UserDto> queryUserLevelCodeByLevelId(Integer levelId);
	/**
	 * 根据校区标识号查询初高中学段
	 * @param campuseId 校区标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县代码
	 * @return
	 */
	public List<EdusysDto> queryEdusysByCampuseId(String campuseId,String cmis30id,String discode);
	/**
	 * 通过用户标识号查询所任教的班级所在的学段
	 * @param campuseId 校区标识号
	 * @param personid 教师标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @return
	 */
	public List<EdusysDto> queryEdusysByPersionIdForMaster(String campuseId,String personid,
			String cmis30id, String discode);
	/**
	 * 通过用户标识号查询所任教的班级所在的学段
	 * @param campuseId 校区标识号
	 * @param personid 教师标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @return
	 */
	public List<EdusysDto> queryEdusysByPersionIdForCourse(String campuseId,String personid,
			String cmis30id, String discode);
	/**
	 * 查询用户所在学校集合
	 * @param userName
	 * @return
	 */
	public List<String> queryUserNameCount(String userName);

	/**
	 * 根据学生eduid查询
	 * 登录教师的classid
	 * @param eduid
	 * @param cmis30id
	 * @param discode
	 * @return
	 */
	public List<UserDto> queryClassidByEduid(String eduid,String cmis30id,String discode);
	/**
	 * 通过登录名称查询校区（班主任及任课老师，德育老师）
	 * @param username 用户名
	 * @param cmis30id 校区标识号
	 * @return
	 */
	public boolean queryCampuseIdBySchoolTeacher2(String username, String cmis30id);
}
