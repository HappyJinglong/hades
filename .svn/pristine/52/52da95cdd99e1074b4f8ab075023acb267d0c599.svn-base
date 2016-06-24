package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.nestframework.utils.NestUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.org.bjca.uams.SdkConsant;
import cn.org.bjca.uams.rest.spi.BjcaRestSdk;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.exception.CannotGetJdbcConnectionException;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.HasNotFoundHigOrMidException;
import com.flyrish.hades.exception.RemoteAuthenticationServerErrorException;
import com.flyrish.hades.exception.TeacherInfoException;
import com.flyrish.hades.exception.UserNameException;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.IUserDetailsServiceExt;
import com.flyrish.hades.util.DataSource;
import com.flyrish.hades.util.NoServiceUtil;


@SuppressWarnings("unchecked")
public class JdbcUserDetailsServiceExtImpl extends JdbcDaoSupport implements
IUserDetailsServiceExt{


	private String authoritiesByUsernameQuery;
	
	private String authoritiesByUsernameByUnitIdQuery;
	
	private String rolePrefix = "";

	private boolean usernameBasedPrimaryKey = true;
	
	private ConstantBean constantBean;
	
	private IRedisServiceExt redisServiceExt;

	private String levelCodeCountqueryUsername;

	private String queryRoleTypeByUsernameAndCmis30id;

	private String queryRoleTypeByUsername;

	private String queryLevelCodeCountsByTeacherEduid;

	private String queryLevelCodeCountsByCmis30id;

	private String queryLevelCodeCountsByTeacherEduidForClassMaster;

	private String queryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster;

	private String queryLevelCodeCountsByTeacherEduidForCouserMaster;

	private String queryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster;

	private String authoritiesByUsernameQueryNotOuser;
	
	private String queryStudentInfoByEduId;

	private String queryCountsByTeacherEduidForCouserMaster;

	private String queryTeacherEduidAndCmis30idForCouserMaster;

	private String querySchoolCourseByTeacherEduidForCouserMaster;

	private String querySchoolCourseByTeacherEduidForCmis30idCouserMaster;

	private String queryCountsByTeacherEduidForCouserTeacher;

	private String queryCountsByTeacherEduidForCouserTeacherForCmis30id;
	
	public String getQuerySchoolCourseByTeacherEduidForCouserMaster() {
		return querySchoolCourseByTeacherEduidForCouserMaster;
	}
	public void setQuerySchoolCourseByTeacherEduidForCouserMaster(
			String querySchoolCourseByTeacherEduidForCouserMaster) {
		this.querySchoolCourseByTeacherEduidForCouserMaster = querySchoolCourseByTeacherEduidForCouserMaster;
	}
	
	public String getQuerySchoolCourseByTeacherEduidForCmis30idCouserMaster() {
		return querySchoolCourseByTeacherEduidForCmis30idCouserMaster;
	}
	public void setQuerySchoolCourseByTeacherEduidForCmis30idCouserMaster(
			String querySchoolCourseByTeacherEduidForCmis30idCouserMaster) {
		this.querySchoolCourseByTeacherEduidForCmis30idCouserMaster = querySchoolCourseByTeacherEduidForCmis30idCouserMaster;
	}
	public UserDetails loadUserByUsername(String username, String password,
			String cmis30id,String usertype,String userid,String systemtype) throws UsernameNotFoundException,DataAccessException {
		
		//获取缓存中的User对象
		//1、组装redis key(加密前的key)
		String md5Password=NoServiceUtil.md5(password);
		
		UserDetails user=null;
			//市、区、校级管理员进行普通认证
			if(Constant.USER_KIND_CITY.equals(usertype)
					||Constant.USER_KIND_COUNTY.equals(usertype)
					||Constant.USER_KIND_SCHOOLGROUP.equals(usertype)){
				user=authenticationLocalUser(userid,systemtype,username,md5Password,password,usertype);
				//如果是校级管理员，还需要判断其所在学校是否含有初高中学段
				if(Constant.USER_KIND_SCHOOLGROUP.equals(usertype))
					hasLevelCodeInSchool(username);
			}
			//校级用户（教师、学生、家长）并且在系统开启统一认证时进行统一认证
			if(NestUtil.isEmpty(usertype)||Constant.USER_KIND_SCHOOLTEACHER.equals(usertype)
					||Constant.USER_KIND_SCHOOLSTUDENT.equals(usertype)
					||Constant.USER_KIND_SCHOOLFAM.equals(usertype)){
				user=authenticationRemoteUser(userid,systemtype,username,md5Password,cmis30id,usertype,password);
			}

		return user;
	}
	@DataSource("read")
	private UserDetails authenticationRemoteUser(String userid,String systemtype,String username,
			String password, String cmis30id, String usertype,String prepassword) {
		String isEnable=constantBean.get("isEnable");
		UserDetails user=null;
		//是否开启统一认知
		if("1".equals(isEnable)){
			//开启统一认证
			Map map = BjcaRestSdk.getInstance().setServerUrl(constantBean.get("remoteAuthenticationUrl"));
			 Map map1 = BjcaRestSdk.getInstance().loginByPWD(username,prepassword);
			 //{message=用户认证失败！, status=108}
			 if(map1!=null&&Constant.SERVER_RENZHEN_ERROR.equals(map1.get("message"))
					 &&map1.get(SdkConsant.STATUS).equals(Constant.SERVER_RENZHEN_ERROR_STATUS))
				 throw new RemoteAuthenticationServerErrorException("server error");
			 if(!map1.get(SdkConsant.STATUS).equals("0"))
				 throw new UserNameException("user not exist");
			 
			 String tokenId = (String) map1.get("tokenId");
			 Map map2 = BjcaRestSdk.getInstance().generateRandom(tokenId);
			 if(!map2.get(SdkConsant.STATUS).equals("0")){
				 throw new UserNameException("user not exist");
			  }
			 
			String random = (String) map2.get("random");
			Map map4=BjcaRestSdk.getInstance().getAllUserAttributes(tokenId,random);
			String roletype=(String)map4.get("uamsroles");
			//如果登录角色是学生并且为新增学生，则需要向数据库生成一条登录信息
			if(NestUtil.isEmpty(usertype)&&Constant.S_STUDENT_ROLETYPE.equals(roletype)
					&&!username.contains("p")){
				insertStudentUser(username,password,prepassword);
			}else if(NestUtil.isEmpty(usertype)&&!username.contains("p")){
				//如果是非学生，则抛出异常，登录失败（注意此时没有考虑家长用户）
				throw new UserNameException("user not exist");
			}
			//如果登录角色是家长并且为新增家长，则需要向数据库生成一条登录信息
			if(NestUtil.isEmpty(usertype)&&username.contains("p")
					&&Constant.S_STUDENT_ROLETYPE.equals(roletype)){
				insertParentUser(username,password,prepassword);
			}else if(NestUtil.isEmpty(usertype)&&username.contains("p")){
				//如果是非家长，则抛出异常，登录失败
				throw new UserNameException("user not exist");
			}
			 /*user=new User(username,prepassword,true,
						true, true, true,authoritys);*/
		}else{
			//未开启统一认证
			user=queryUserByUserNameAndPwd(userid,systemtype,username,password,cmis30id,prepassword);
		}
		List<String> roleTypes=new ArrayList<String>();
		//判断用户相关信息是否符合要求（除学生和家长以外）
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(usertype)){
			//查询出用户所有真实的角色
			roleTypes=queryListRoleTypes(userid,systemtype,username,cmis30id);
			Boolean isPass=false;
			if(roleTypes.contains(Constant.USER_TYPE_SCHOOLADMIN)||roleTypes.contains(Constant.USER_TYPE_SPORTSEMASTER)){
				//教务老师（判断该校是否含有初高中学段）
				isPass=ispassForSchoolAdmin(userid,username,cmis30id);
			}
			if(roleTypes.contains(Constant.USER_TYPE_CLASSMASTER)&&!isPass){
				//班主任老师（判断该班主任所教班级是否在初高中学段里）
				isPass=ispassForSchoolTeacher(username,cmis30id,queryLevelCodeCountsByTeacherEduidForClassMaster,queryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster);
			}
			if((roleTypes.contains(Constant.USER_TYPE_COURSEMASTER)&&!isPass)){
				//任课老师（判断该任课老师所教班级是否在初高中学段里）
				isPass=ispassForSchoolTeacher(username,cmis30id,queryLevelCodeCountsByTeacherEduidForCouserMaster,queryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster);
				//如果没找到，则到课改库寻找看是否有相应的任课班级（内置课程）
				if(!isPass){
					isPass=ispassForSchoolTeacher(username,cmis30id,queryCountsByTeacherEduidForCouserMaster,queryTeacherEduidAndCmis30idForCouserMaster);
				}
				if(!isPass){
					//如果没找到，判断是否只有校本课程老师（校本课程）
					isPass=ispassForSchoolTeacher2(username,cmis30id,queryCountsByTeacherEduidForCouserTeacher,queryCountsByTeacherEduidForCouserTeacherForCmis30id);
				}
			}
			if((roleTypes.contains(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE)&&!isPass)){
				if(!isPass){
					//如果没找到，判断是否只有校本课程老师（校本课程）
					isPass=ispassForSchoolTeacher2(username,cmis30id,queryCountsByTeacherEduidForCouserTeacher,queryCountsByTeacherEduidForCouserTeacherForCmis30id);
				}
			}
			//如果该用户所对应的学段不在初高中之中，则认证不通过
			if(!isPass)
				throw new TeacherInfoException("User not found");
		}else{
			//要么是学生，要么是家长
			if("1503006".equals(usertype)){
				roleTypes.add("1502030");
			}else if("1503007".equals(usertype)){
				roleTypes.add("1502031");
			}
		}
		//查询当前用户所拥有的权限
		roleTypes.add("103");
		GrantedAuthorityImpl[]authoritys=new GrantedAuthorityImpl[roleTypes.size()];
		for(int i=0;i<roleTypes.size();i++){
			GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
					"ROLE_"+roleTypes.get(i));
			authoritys[i]=authority;
		}
		user=new User(username,prepassword,true,
				true, true, true,authoritys);
		return user;
	}
	private void insertParentUser(String username,String password,String prepassword){
		//获取用户表主键标识号
		String proKey=genreateProKey();
				
		//String prarentKey=genreateProKey();
		
		LoginOUser ou=queryLoginOUser(username.substring(0, username.lastIndexOf("p")),proKey,password,prepassword);
		String cmis30id=ou.getUnitid();
		//获取该用户所对应的用户信息
		LoginOUser ouser=new LoginOUser();
		ouser.setUserid(proKey);
		ouser.setUsername(username);
		ouser.setPwd(password);
		ouser.setUsertype(Constant.USER_KIND_SCHOOLFAM);
		ouser.setName(username);
		ouser.setUnitid(null);
		ouser.setPersonid(null);
		ouser.setUsed("1");
		//插入用户表（学生及家长用户）
		insertFamliyOUser(ouser,proKey);
		//插入角色表（学生及家长角色）
		insertFamliyRole(ouser,proKey,cmis30id);
		//放回缓存中
		List<LoginOUser> ousers=new ArrayList<LoginOUser>();
		ousers.add(ouser);
		try {
			redisServiceExt.save(ouser.getUsername()+Constant.R_REDIS_OUSER,ousers);
		} catch (ForceException e) {
			logger.error("insertParentUser(String,String)",e);
		}
	}
	private void insertFamliyRole(LoginOUser ouser,String key,String cmis30id){
		try{
			String insertParentSql="insert into o_userrole(userroleid,userid,roleid) "+
					"select s_o_userrole.nextval,"+key+",roleid from o_role oro "+
					"where oro.roletype=1502031 and oro.unitid="+cmis30id;
			this.getJdbcTemplate().update(insertParentSql);
		}catch(Exception e){
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	private void insertFamliyOUser(LoginOUser ouser,String key){
		try{
			//家长sql
			String insertParentSql="Insert into o_user (USERID,USERNAME,PWD,USERTYPE,UNITID,PERSONID,PRIMARYSCHOOL," +
					"MIDDLESCHOOL,HIGHSCHOOL,USED,NAME,CARDSORT,CARDID,UNITNAME,TELEPHONE,PWD_SHOW,PWD_ENROLLPLAN," +
					"PWD_ENROLLPLAN_SHOW,SYSTEMTYPE) values ("+key+",'"+ouser.getUsername()+"'," +
					"'"+ouser.getPwd()+"',1503007,"+ouser.getUnitid()+",null,null,null,null,1,'"+ouser.getUsername()+"',null,null,null,null,null,null,null,3)";
			this.getJdbcTemplate().update(insertParentSql);
		}catch(Exception e){
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	private Boolean ispassForSchoolTeacher2(String userid, String cmis30id,
			String querySchoolCourseByTeacherEduidForCouserMaster,
			String querySchoolCourseByTeacherEduidForCmis30idCouserMaster) {
		List<Integer> counts=null;
		if(NestUtil.isEmpty(cmis30id)){
			counts = getJdbcTemplate().query(querySchoolCourseByTeacherEduidForCouserMaster,
					new Object[] {userid}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		}else{
			counts = getJdbcTemplate().query(querySchoolCourseByTeacherEduidForCmis30idCouserMaster,
					new Object[] {userid,cmis30id}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		}
		if(counts!=null&&counts.size()>0&&counts.get(0)>0)
			return true;
		return false;
	}
	private void insertStudentUser(String username,String password,String prepassword){
		//获取用户表主键标识号
		String proKey=genreateProKey();
		
		String prarentKey=genreateProKey();
		//获取该用户所对应的用户信息
		LoginOUser ouser=queryLoginOUser(username,proKey,password,prepassword);
		//插入用户表（学生及家长用户）
		insertLoginOUser(ouser,prarentKey);
		//插入角色表（学生及家长角色）
		inserOuserRole(ouser,prarentKey);
		//放回缓存中
		List<LoginOUser> ousers=new ArrayList<LoginOUser>();
		ousers.add(ouser);
		try {
			redisServiceExt.save(ouser.getUsername()+Constant.R_REDIS_OUSER,ousers);
		} catch (ForceException e) {
			logger.error("insertStudentUser(String,String)",e);
		}
	}
	private void inserOuserRole(LoginOUser ouser,String prarentKey) {
		try{
			String insertSql="insert into o_userrole(userroleid,userid,roleid) "+
								"select s_o_userrole.nextval,"+ouser.getUserid()+",roleid from o_role oro "+
								"where oro.roletype=1502030 and oro.unitid="+ouser.getUnitid();
			this.getJdbcTemplate().update(insertSql);
		}catch(Exception e){
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	private void insertLoginOUser(LoginOUser ouser,String prarentKey){
		try{
			//学生sql
			String insertSql="Insert into o_user (USERID,USERNAME,PWD,USERTYPE,UNITID,PERSONID,PRIMARYSCHOOL," +
					"MIDDLESCHOOL,HIGHSCHOOL,USED,NAME,CARDSORT,CARDID,UNITNAME,TELEPHONE,PWD_SHOW,PWD_ENROLLPLAN," +
					"PWD_ENROLLPLAN_SHOW,SYSTEMTYPE) values ("+ouser.getUserid()+",'"+ouser.getUsername()+"'," +
					"'"+ouser.getPwd()+"',1503006,"+ouser.getUnitid()+","+ouser.getPersonid()+",null,null,null,1,'"+ouser.getName()+"',null,null,null,null,null,null,null,3)";
			this.getJdbcTemplate().update(insertSql);
		}catch(Exception e){
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	
	private LoginOUser queryLoginOUser(final String username,final String userid,final String password,final String prpassword){
		try{
			List<LoginOUser>ousers = getJdbcTemplate().query(queryStudentInfoByEduId,
					new Object[] {username}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)throws SQLException {
					LoginOUser ouser=new LoginOUser();
					ouser.setUserid(userid);
					ouser.setUsername(username);
					ouser.setPwd(password);
					ouser.setPwd_show(prpassword);
					ouser.setUsertype(Constant.USER_KIND_SCHOOLSTUDENT);
					ouser.setName(rs.getString("name"));
					ouser.setUnitid(rs.getString("cmis30id"));
					ouser.setPersonid(rs.getString("studentid"));
					ouser.setUsed("1");
					return ouser;
				}
			});
			if(ousers!=null&&ousers.size()>0)
				return ousers.get(0);
			else throw new UserNameException("user not exist");
		}catch(UserNameException e){
			throw new UserNameException("user not exist");
		}catch(Exception e){
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	
	private String genreateProKey(){
		String genSql="select s_o_user.nextval from dual";
		List<String> proKeys=this.getJdbcTemplate().query(genSql,new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString(1);
			}
		});
		if(proKeys!=null&&proKeys.size()>0)
			return proKeys.get(0);
		return null;
	}
	@DataSource("read")
	private Boolean ispassForSchoolTeacher(String username,String cmis30id,String sqlUserName,String sqlCmis30idAndUsername){
		List<Integer> counts=null;
		if(NestUtil.isEmpty(cmis30id)){
			counts = getJdbcTemplate().query(sqlUserName,
					new Object[] {username}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		}else{
			counts = getJdbcTemplate().query(sqlCmis30idAndUsername,
					new Object[] {cmis30id,cmis30id,cmis30id,username,cmis30id}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		}
		if(counts!=null&&counts.size()>0&&counts.get(0)>0)
			return true;
		return false;
	}
	@DataSource("read")
	private Boolean ispassForSchoolAdmin(String userid,String username,String cmis30id){
		List<Integer> counts=null;
		/*if(NestUtil.isEmpty(cmis30id)){*/
			counts = getJdbcTemplate().query(queryLevelCodeCountsByTeacherEduid,
					new Object[] {userid}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		/*}else{
			counts = getJdbcTemplate().query(queryLevelCodeCountsByCmis30id,
					new Object[] {username,cmis30id}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getInt(1);
				}
			});
		}*/
		if(counts!=null&&counts.size()>0&&counts.get(0)>0)
			return true;
		return false;
	}
	@DataSource("read")
	private List<String> queryListRoleTypes(String userid,String systemtype,String username,String cmis30id){
		List<String> roleTypes=null;
		if(NestUtil.isNotEmpty(cmis30id)){
			roleTypes = getJdbcTemplate().query(queryRoleTypeByUsernameAndCmis30id,
					new Object[] {userid,systemtype,username,cmis30id}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getString(1);
				}
			});
		}else{
			roleTypes = getJdbcTemplate().query(queryRoleTypeByUsername,
					new Object[] {userid,systemtype,username}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					return rs.getString(1);
				}
			});
		}
		//如果没有对应的角色，则认证不通过
		if(roleTypes==null||roleTypes.size()<1)
			throw new UsernameNotFoundException("User not found");
		return roleTypes;
	}
	@DataSource("read")
	private UserDetails queryUserByUserNameAndPwd(String userid,String systemtype,String username,String password,String cmis30id,final String prepassword){
		UserDetails user=null;
		try{
			if(NestUtil.isEmpty(cmis30id)) 
				return authenticationLocalUser(userid,systemtype,username,password,prepassword);
			
			List<UserDetails> users = getJdbcTemplate().query(authoritiesByUsernameByUnitIdQuery,
					new Object[] {username,password,cmis30id}, new RowMapper() {
	
				public Object mapRow(ResultSet rs, int rownum)
				throws SQLException {
					String username = rs.getString(1);
					String password = rs.getString(2);
					boolean enabled = rs.getBoolean(3);
					String roleName = rolePrefix + rs.getString("rolename");
					GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
							roleName);
					GrantedAuthorityImpl[]authoritys=new GrantedAuthorityImpl[1];
					authoritys[0]=authority;
					return new User(username,prepassword,enabled,
							true, true, true, authoritys);
				}
			});
			if (users==null||users.size() == 0) {
				throw new UsernameNotFoundException("User not found");
			}
			user=users.get(0);
		}catch(UsernameNotFoundException e){
			throw e;
		}catch (Exception e) {
			logger.error("authenticationLocalUser(String,String,String,String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
		return user;
	}
	@DataSource("read")
	private void hasLevelCodeInSchool(String username) {
		try{
			List<Integer> counts = getJdbcTemplate().query(levelCodeCountqueryUsername,
					new Object[] {username}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rownum)throws SQLException {
					return rs.getInt(1);
				}
			});
			if (counts==null||counts.size() == 0||counts.get(0)<1) {
				throw new HasNotFoundHigOrMidException("User not found");
			}
		}catch(HasNotFoundHigOrMidException e){
			throw e;
		}catch (Exception e) {
			logger.error("hasLevelCodeInSchool(String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
	}
	@DataSource("read")
	private UserDetails authenticationLocalUser(String userid_u,String systemtype,String username,
			String password,final String prepassword,final String usertype) {
		UserDetails user=null;
		try{
			List<UserDetails> users=null;
			//先从缓存中读取用户信息,禁用缓存
			//List<LoginOUser> loginOUsers=redisServiceExt.readList(username+Constant.R_REDIS_OUSER);
			List<LoginOUser> loginOUsers=null;
			if(loginOUsers==null||loginOUsers.isEmpty()){
					users = getJdbcTemplate().query(authoritiesByUsernameQuery,
							new Object[] {userid_u,systemtype,username,password }, new RowMapper() {
			
						public Object mapRow(ResultSet rs, int rownum)
						throws SQLException {
							String username = rs.getString(1);
							boolean enabled = rs.getBoolean(3);
							String roleName = rolePrefix + rs.getString("rolename");
							int size=1;
							GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
									roleName);
							if(NestUtil.isNotEmpty(usertype)){
								size=2;
							}
							GrantedAuthorityImpl[]authoritys=new GrantedAuthorityImpl[size];
							authoritys[0]=authority;
							if(NestUtil.isNotEmpty(usertype)){
								GrantedAuthorityImpl authority1 = new GrantedAuthorityImpl(
										rolePrefix+usertype);
								authoritys[1]=authority1;
							}
							return new User(username,prepassword,enabled,
									true, true, true, authoritys);
						}
					});
					if (users==null||users.size() == 0) {
						throw new UsernameNotFoundException("User not found");
					}
					user=users.get(0);
			}else{
				final LoginOUser ouser=loginOUsers.get(0);
				if(ouser==null)
					throw new UsernameNotFoundException("User not found");
				List<Integer> counts=getJdbcTemplate().query(authoritiesByUsernameAndPwd,new Object[] {ouser.getUserid(),password},
						new RowMapper() {
							@Override
							public Object mapRow(ResultSet rs, int arg1)
									throws SQLException {
								return rs.getInt(1);
							}
						});
				if(counts==null||counts.isEmpty()||counts.get(0)<1){
					throw new UsernameNotFoundException("User not found");
				}
				String userid=ouser.getUserid();
				users = getJdbcTemplate().query(authoritiesByUsernameQueryNotOuser,
						new Object[] {userid}, new RowMapper() {
		
					public Object mapRow(ResultSet rs, int rownum)
					throws SQLException {
						String username =ouser.getUsername();
						boolean enabled =true;
						String roleName = rolePrefix + rs.getString("rolename");
						int size=1;
						GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
								roleName);
						if(NestUtil.isNotEmpty(usertype)){
							size=2;
						}
						GrantedAuthorityImpl[]authoritys=new GrantedAuthorityImpl[size];
						authoritys[0]=authority;
						if(NestUtil.isNotEmpty(usertype)){
							GrantedAuthorityImpl authority1 = new GrantedAuthorityImpl(
									rolePrefix+usertype);
							authoritys[1]=authority1;
						}
						return new User(username,prepassword,enabled,
								true, true, true, authoritys);
					}
				});
				if (users==null||users.size() == 0) {
					throw new UsernameNotFoundException("User not found");
				}
				user=users.get(0);
			}
		}catch(UsernameNotFoundException e){
			throw e;
		}catch (Exception e) {
			logger.error("authenticationLocalUser(String,String,String,String)",e);
			throw new CannotGetJdbcConnectionException("db error",e);
		}
		return user;
	}
	@DataSource("read")
	private UserDetails authenticationLocalUser(String userid_u,String systemtype,String username,
			String password,final String prepassword) {
		return authenticationLocalUser(userid_u,systemtype,username,password,prepassword,null);
	}


	protected void addCustomAuthorities(String username, List authorities) {
	}

	public void setAuthoritiesByUsernameQuery(String authoritiesByUsernameQuery) {
		this.authoritiesByUsernameQuery = authoritiesByUsernameQuery;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
		this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
	}

	private static final Logger logger = Logger
	.getLogger(JdbcUserDetailsServiceExtImpl.class);

	public void setAuthoritiesByUsernameByUnitIdQuery(
			String authoritiesByUsernameByUnitIdQuery) {
		this.authoritiesByUsernameByUnitIdQuery = authoritiesByUsernameByUnitIdQuery;
	}
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}


	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	public String getLevelCodeCountqueryUsername() {
		return levelCodeCountqueryUsername;
	}
	public void setLevelCodeCountqueryUsername(String levelCodeCountqueryUsername) {
		this.levelCodeCountqueryUsername = levelCodeCountqueryUsername;
	}
	public void setQueryLevelCodeCountsByTeacherEduid(
			String queryLevelCodeCountsByTeacherEduid) {
		this.queryLevelCodeCountsByTeacherEduid = queryLevelCodeCountsByTeacherEduid;
	}
	public void setQueryLevelCodeCountsByCmis30id(
			String queryLevelCodeCountsByCmis30id) {
		this.queryLevelCodeCountsByCmis30id = queryLevelCodeCountsByCmis30id;
	}
	public void setQueryRoleTypeByUsernameAndCmis30id(
			String queryRoleTypeByUsernameAndCmis30id) {
		this.queryRoleTypeByUsernameAndCmis30id = queryRoleTypeByUsernameAndCmis30id;
	}
	public void setQueryRoleTypeByUsername(String queryRoleTypeByUsername) {
		this.queryRoleTypeByUsername = queryRoleTypeByUsername;
	}
	public void setQueryLevelCodeCountsByTeacherEduidForClassMaster(
			String queryLevelCodeCountsByTeacherEduidForClassMaster) {
		this.queryLevelCodeCountsByTeacherEduidForClassMaster = queryLevelCodeCountsByTeacherEduidForClassMaster;
	}
	public void setQueryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster(
			String queryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster) {
		this.queryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster = queryLevelCodeCountsByTeacherEduidAndCmis30idForClassMaster;
	}
	public void setQueryLevelCodeCountsByTeacherEduidForCouserMaster(
			String queryLevelCodeCountsByTeacherEduidForCouserMaster) {
		this.queryLevelCodeCountsByTeacherEduidForCouserMaster = queryLevelCodeCountsByTeacherEduidForCouserMaster;
	}
	public void setQueryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster(
			String queryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster) {
		this.queryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster = queryLevelCodeCountsByTeacherEduidAndCmis30idForCouserMaster;
	}
	public String getAuthoritiesByUsernameQueryNotOuser() {
		return authoritiesByUsernameQueryNotOuser;
	}
	public void setAuthoritiesByUsernameQueryNotOuser(
			String authoritiesByUsernameQueryNotOuser) {
		this.authoritiesByUsernameQueryNotOuser = authoritiesByUsernameQueryNotOuser;
	}
	public String getQueryStudentInfoByEduId() {
		return queryStudentInfoByEduId;
	}
	public void setQueryStudentInfoByEduId(String queryStudentInfoByEduId) {
		this.queryStudentInfoByEduId = queryStudentInfoByEduId;
	}
	public String getQueryCountsByTeacherEduidForCouserMaster() {
		return queryCountsByTeacherEduidForCouserMaster;
	}
	public void setQueryCountsByTeacherEduidForCouserMaster(
			String queryCountsByTeacherEduidForCouserMaster) {
		this.queryCountsByTeacherEduidForCouserMaster = queryCountsByTeacherEduidForCouserMaster;
	}
	public String getQueryTeacherEduidAndCmis30idForCouserMaster() {
		return queryTeacherEduidAndCmis30idForCouserMaster;
	}
	public void setQueryTeacherEduidAndCmis30idForCouserMaster(
			String queryTeacherEduidAndCmis30idForCouserMaster) {
		this.queryTeacherEduidAndCmis30idForCouserMaster = queryTeacherEduidAndCmis30idForCouserMaster;
	}
	private String authoritiesByUsernameAndPwd;

	public String getAuthoritiesByUsernameAndPwd() {
		return authoritiesByUsernameAndPwd;
	}
	public void setAuthoritiesByUsernameAndPwd(String authoritiesByUsernameAndPwd) {
		this.authoritiesByUsernameAndPwd = authoritiesByUsernameAndPwd;
	}
	public String getQueryCountsByTeacherEduidForCouserTeacher() {
		return queryCountsByTeacherEduidForCouserTeacher;
	}
	public void setQueryCountsByTeacherEduidForCouserTeacher(
			String queryCountsByTeacherEduidForCouserTeacher) {
		this.queryCountsByTeacherEduidForCouserTeacher = queryCountsByTeacherEduidForCouserTeacher;
	}
	public String getQueryCountsByTeacherEduidForCouserTeacherForCmis30id() {
		return queryCountsByTeacherEduidForCouserTeacherForCmis30id;
	}
	public void setQueryCountsByTeacherEduidForCouserTeacherForCmis30id(
			String queryCountsByTeacherEduidForCouserTeacherForCmis30id) {
		this.queryCountsByTeacherEduidForCouserTeacherForCmis30id = queryCountsByTeacherEduidForCouserTeacherForCmis30id;
	}
	
}
