package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.LoginUserDto;
import com.flyrish.hades.dto.StudentInfoDto;

public interface IUserLoginServiceExt {
	/**
	 * 验证登录用户是否存在于数据库中
	 * @param username 登录用户名 
	 * @param password
	 * @return 如果存在，则返回LoginUserDto对象,不存在，则返回null
	 */
	LoginUserDto validUserIsExistInDb(String username,String password);
	
	List<LoginOUser> queryLoginOUserAll();
	
	List<StudentInfoDto> queryStudentInfosAll();
}
