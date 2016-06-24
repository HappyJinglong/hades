package com.flyrish.hades.webapp.action.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.EdusysDto;
import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.UserRoleDto;
import com.flyrish.hades.dto.UserSchoolDto;
import com.flyrish.hades.service.ext.IHomePageManagerExt;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class IndexAction extends BaseAction{
	
	@Spring
	IOUserServiceExt userServiceExt;
	@Spring
	ILoginUserInfoServiceExt loginUserInfoServiceExt;
	@Spring
	IHomePageManagerExt homePageManagerExt;
	
	//默认用户功能菜单
	public List<FuncTreeDto> userFuncTreeDtoes;
	//用户当前学年学期
	public String schoolYearNameShow;
	//学校名称
	public String schoolName;
	//校区ID
	public String campuseId;
	//是否显示角色
	public boolean isShowRole = true;
	//是否显示校区
	public boolean isShowSchool = true;
	//是否显示学段
	public boolean isShowLevelCode = true;
	
	public boolean isShowPwd = true;
	
	public String levelId;
	
	public String nginxServer;
	
	@Spring
	public ConstantBean constantBean;
	
	//常用功能					
	public List<FuncTreeDto> userCommonFunc;
	
	public List<UserRoleDto> userRoleDtos=new ArrayList<UserRoleDto>();
	
	public List<EdusysDto> edusysDtos=new ArrayList<EdusysDto>();
	
	@DefaultAction
	public Object toDefaultPage(HttpServletRequest req){
		
		UserDto userDto = this.getLoginInfo(req);
		
		String roleId = null;	//用户角色
		String levelCode = null;//学段类型
		Integer funcLevelType = null;//功能等级类型
		String userid = userDto.getUserid();//用户ID
		String usertype = userDto.getUsertype();//用户类型
		
		if(Constant.USER_KIND_CITY.equals(usertype)
				|| Constant.USER_KIND_COUNTY.equals(usertype)){
			isShowPwd = false;
		}else{
			isShowPwd = true;
		}
		//获取当前学年学期
		schoolYearNameShow =userDto.getTermName();
		schoolName =userDto.getSchoolName();
		//如果用户类型为学生或家长
		if(Constant.USER_KIND_SCHOOLSTUDENT.equals(usertype)
				||Constant.USER_KIND_SCHOOLFAM.equals(usertype)){
				campuseId = userDto.getCampuseId();
				levelCode=userDto.getLevelcode();
				levelId=userDto.getLevelid();
				//获取角色标识号
				roleId=userDto.getRoleId();
		}
		//如果用户类型为教师
		else if(Constant.USER_KIND_SCHOOLTEACHER.equals(usertype)){
			Map<String,Map<UserRoleDto,Set<EdusysDto>>> dataMaps=new TreeMap<String,Map<UserRoleDto,Set<EdusysDto>>>();
			//获取所有角色以及所对应的校区
			List<Map<String,String>>roletypeMaps=loginUserInfoServiceExt.queryListRoleTypes(userDto.getUserid(),userDto.getUsername(),userDto.getCmis30id());
			//组装为Map<RoleDto,List<String>>格式，RoleDto包括所对应的角色，String标识所对应的校区
			Map<UserRoleDto,List<String>> roleDtoMap=installRCMap(roletypeMaps);
			//根据具体角色查找校区
			UserRoleDto userRoleDto=listMapContains(roleDtoMap,Constant.USER_TYPE_SCHOOLADMIN);
			//教务老师
			if(userRoleDto!=null){
				List<Map<String,String>>data=loginUserInfoServiceExt.queryCampuseIdBySchoolAdmin(userDto.getUsername(),userDto.getCmis30id(),roleDtoMap.get(userRoleDto));
				Map<String,Set<EdusysDto>> edusysDtoMap=installEdusysDto(data);
				if(edusysDtoMap!=null&&edusysDtoMap.size()>0){
					for(Map.Entry<String,Set<EdusysDto>> entry:edusysDtoMap.entrySet()){
						Map<UserRoleDto,Set<EdusysDto>> adminMap=new TreeMap<UserRoleDto,Set<EdusysDto>>();
						adminMap.put(userRoleDto,entry.getValue());
						dataMaps.put(entry.getKey(),adminMap);
					}
				}
			}
			//德育老师
			userRoleDto=listMapContains(roleDtoMap,Constant.USER_TYPE_SPORTSEMASTER);
			if(userRoleDto!=null){
				List<Map<String,String>>data=loginUserInfoServiceExt.queryCampuseIdBySchoolAdmin(userDto.getUsername(),userDto.getCmis30id(),roleDtoMap.get(userRoleDto));
				Map<String,Set<EdusysDto>> edusysDtoMap=installEdusysDto(data);
				if(edusysDtoMap!=null&&edusysDtoMap.size()>0){
					for(Map.Entry<String,Set<EdusysDto>> entry:edusysDtoMap.entrySet()){
						Map<UserRoleDto,Set<EdusysDto>> adminMap=dataMaps.get(entry.getKey());
						if(adminMap==null){
							adminMap=new TreeMap<UserRoleDto,Set<EdusysDto>>();
							dataMaps.put(entry.getKey(),adminMap);
						}
						adminMap.put(userRoleDto,entry.getValue());
					}
				}
			}
			//班主任老师（判断该班主任所教班级是否在初高中学段里）
			userRoleDto=listMapContains(roleDtoMap,Constant.USER_TYPE_CLASSMASTER);
			if(userRoleDto!=null){
				List<Map<String,String>>data=loginUserInfoServiceExt.queryCampuseIdBySchoolTeacher(userDto.getTeacherid(),userDto.getUsername(),userDto.getCmis30id(),"LoginUserInfoServiceExtImpl.queryCampuseIdBySchoolTeacher.queryMater",roleDtoMap.get(userRoleDto));
				Map<String,Set<EdusysDto>> edusysDtoMap=installEdusysDto(data);
				if(edusysDtoMap!=null&&edusysDtoMap.size()>0){
					for(Map.Entry<String,Set<EdusysDto>> entry:edusysDtoMap.entrySet()){
						Map<UserRoleDto,Set<EdusysDto>> adminMap=dataMaps.get(entry.getKey());
						if(adminMap==null){
							adminMap=new TreeMap<UserRoleDto,Set<EdusysDto>>();
							dataMaps.put(entry.getKey(),adminMap);
						}
						adminMap.put(userRoleDto,entry.getValue());
						}
					}
				}
			//任课老师（判断该任课老师是否在初高中学段里）
			userRoleDto=listMapContains(roleDtoMap,Constant.USER_TYPE_COURSEMASTER);
			if(userRoleDto!=null){
				List<Map<String,String>>data=loginUserInfoServiceExt.queryCampuseIdBySchoolTeacher(userDto.getTeacherid(),userDto.getUsername(),userDto.getCmis30id(),"LoginUserInfoServiceExtImpl.queryCampuseIdBySchoolTeacher.queryCourse",roleDtoMap.get(userRoleDto));
				List<Map<String,String>>data2=loginUserInfoServiceExt.queryCampuseIdBySchoolTeacher(userDto.getTeacherid(),userDto.getUsername(),userDto.getCmis30id(),"LoginUserInfoServiceExtImpl.queryCampuseIdBySchoolTeacher.queryKCourse",roleDtoMap.get(userRoleDto));
				if(data==null){
					data=data2;
				}else{
					data.addAll(data2);
				}
				Map<String,Set<EdusysDto>> edusysDtoMap=installEdusysDto(data);
				if(edusysDtoMap!=null&&edusysDtoMap.size()>0){
					for(Map.Entry<String,Set<EdusysDto>> entry:edusysDtoMap.entrySet()){
						Map<UserRoleDto,Set<EdusysDto>> adminMap=dataMaps.get(entry.getKey());
						if(adminMap==null){
							adminMap=new TreeMap<UserRoleDto,Set<EdusysDto>>();
							dataMaps.put(entry.getKey(),adminMap);
						}
						adminMap.put(userRoleDto,entry.getValue());
						}
					}
			}
			//判断该用户在某个校区是否含有高中任课教师角色，如果没有，则判断其是否含有校本任课教师
			userRoleDto=listMapContains(roleDtoMap,Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE);
			if(userRoleDto!=null){
				//获取该角色所拥有的校区集合
				List<String>campuseids=roleDtoMap.get(userRoleDto);
				//判断当前是否有
				boolean ispass=loginUserInfoServiceExt.queryCampuseIdBySchoolTeacher2(userDto.getUsername(),userDto.getCmis30id());
				if(campuseids!=null&&campuseids.size()>0&&ispass){
					Map<String,Set<EdusysDto>> edusysDtoMap=new TreeMap<String, Set<EdusysDto>>();
					for(Map.Entry<String, Map<UserRoleDto, Set<EdusysDto>>> dataMap:dataMaps.entrySet()){
						//判断当前循环的校区是否在校区集合里
						if(!campuseids.contains(dataMap.getKey()))continue;
						boolean isHasBookTeacherRole=true;
						if(dataMap.getValue()==null||dataMap.getValue().isEmpty())continue;
						for(Map.Entry<UserRoleDto, Set<EdusysDto>> childDataMap:dataMap.getValue().entrySet()){
							//如果该校区不存在高中任课老师角色
							if(childDataMap!=null&&childDataMap.getKey()!=null
									&&Constant.USER_TYPE_COURSEMASTER.equals(childDataMap.getKey().getRoleType())){
								Set<EdusysDto> edusysDtos=childDataMap.getValue();
								if(edusysDtos==null||edusysDtos.isEmpty())continue;
								for(EdusysDto edusysDto:edusysDtos){
									//如果在任课老师中含有高中学段，则不执行
									if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(edusysDto.getLevelCode())
											||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(edusysDto.getLevelCode())){
										isHasBookTeacherRole=false;
									}
								}
							}
						}
						if(isHasBookTeacherRole){
							EdusysDto edusysdto=new EdusysDto();
							edusysdto.setEdusysId("99999");
							edusysdto.setEdusysName("高中");
							edusysdto.setLevelCode("99999");
							Set<EdusysDto> edusysList=new TreeSet<EdusysDto>();
							edusysList.add(edusysdto);
							edusysDtoMap.put(dataMap.getKey(),edusysList);
						}
					}
					//if(dataMaps.isEmpty()){
						//如果当前没有任何数据，则添加
						for(String campuseid:campuseids){
							if(NestUtil.isNotEmpty(campuseid)&&!dataMaps.containsKey(campuseid)){
								EdusysDto edusysdto=new EdusysDto();
								edusysdto.setEdusysId("99999");
								edusysdto.setEdusysName("高中");
								edusysdto.setLevelCode("99999");
								Set<EdusysDto> edusysList=new TreeSet<EdusysDto>();
								edusysList.add(edusysdto);
								edusysDtoMap.put(campuseid,edusysList);
							}
						}
					//}
					if(edusysDtoMap!=null&&edusysDtoMap.size()>0){
						for(Map.Entry<String,Set<EdusysDto>> entry:edusysDtoMap.entrySet()){
							Map<UserRoleDto,Set<EdusysDto>> adminMap=dataMaps.get(entry.getKey());
							if(adminMap==null){
								adminMap=new TreeMap<UserRoleDto,Set<EdusysDto>>();
								dataMaps.put(entry.getKey(),adminMap);
							}
							adminMap.put(userRoleDto,entry.getValue());
							}
					}
				}
			}
			
			isShowSchool = true;
			isShowLevelCode = true;
			//填充数据
			for(Map.Entry<String,Map<UserRoleDto,Set<EdusysDto>>> entry:dataMaps.entrySet()){
				if(entry.getValue()==null||entry.getValue().size()<0)continue;
				userDto.setCampuseId(entry.getKey());
				userRoleDtos.addAll(entry.getValue().keySet());
				if(entry.getValue()!=null){
					for(Map.Entry<UserRoleDto, Set<EdusysDto>> smailentry:entry.getValue().entrySet()){
						if(smailentry.getValue()==null||smailentry.getValue().size()<0)continue;
						userDto.setRoleId(smailentry.getKey().getRoleId());
						edusysDtos.addAll(smailentry.getValue());
						for(EdusysDto dto:smailentry.getValue()){
							userDto.setLevelid(dto.getEdusysId());
							userDto.setLevelcode(dto.getLevelCode());
							userDto.setLevelName(dto.getEdusysName());
							break;
						}
						break;
					}
					break;
				}else{
					continue;
				}
			}
			String roleRealType=userServiceExt.queryRoleRealType(userDto.getRoleId());
			userDto.setUserRealType(roleRealType);
			req.getSession().setAttribute(Constant.TYPE_CAMPUSEID_ROLE_LEVELCODE,dataMaps);
		}
		//市区用户，设置默认学段
		if(Constant.USER_KIND_CITY.equals(userDto.getUsertype())||Constant.USER_KIND_COUNTY.equals(userDto.getUsertype())){
			userDto.setLevelcode(Constant.DICT_TYPE_LEVELCODE_CZSTR);
			userDto.setLevelid(Constant.DICT_TYPE_LEVELCODE_CZSTR);
		}
		if(!Constant.USER_KIND_SCHOOLGROUP.equals(userDto.getUsertype())
				&&!Constant.DICT_TYPE_LEVELCODE_GZKGTR.equals(userDto.getLevelcode())){
			//默认角色下的所有菜单
			if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
				funcLevelType = 1;
			}
			if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
					||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode())){
				funcLevelType = 3;
			}
			userFuncTreeDtoes = userServiceExt.queryAllFuncTree(userDto.getUserid(),userDto.getRoleId(),funcLevelType,2);
			//用户常用功能查询
			List<OFunc> commonFuncs = homePageManagerExt.queryCommonFuncFromRedis(userDto, funcLevelType);
			if(null!=commonFuncs && commonFuncs.size()>0){
				userCommonFunc = new ArrayList<FuncTreeDto>();
				for(OFunc oFunc : commonFuncs){
					FuncTreeDto ftd = new FuncTreeDto();
					ftd.setFuncId(oFunc.getFuncid().toString());
					ftd.setFuncName(oFunc.getFuncname());
					ftd.setUrl(oFunc.getExecfilename());
					ftd.setUserId(oFunc.getUserId());
					ftd.setCommonFuncId(oFunc.getCommonFuncId());
					userCommonFunc.add(ftd);
				}
			}else{
				userCommonFunc = homePageManagerExt.queryCommonFunc(userDto.getUserid(),userDto.getRoleId(),funcLevelType);
				//没有放进缓存
				if(null!=userCommonFunc && userCommonFunc.size()>0){
					List<OFunc>newCommonFuncs = new ArrayList<OFunc>();
					for(FuncTreeDto ftd : userCommonFunc){
						OFunc of = new OFunc();
						of.setFuncid(new BigDecimal(ftd.getFuncId()));
						of.setFuncname(ftd.getFuncName());
						of.setExecfilename(ftd.getUrl());
						of.setUserId(ftd.getUserId());
						of.setCommonFuncId(ftd.getCommonFuncId());
						of.setClickCount(1);
						of.setUpdateOrInsertFlag(1);
						newCommonFuncs.add(of);
					}
					homePageManagerExt.saveCommonMenuToRedis(userDto, newCommonFuncs);
				}
			}
				//req.getSession().setAttribute("userCommonFunc", userCommonFunc);
		}
		if(Constant.USER_KIND_SCHOOLGROUP.equals(userDto.getUsertype())){
			nginxServer=constantBean.get("nginx_server");
			req.getSession().setAttribute("nginxServer", nginxServer);
		}
		if(Constant.DICT_TYPE_LEVELCODE_GZKGTR.equals(userDto.getLevelcode())){
			//某个校区只有校本课程任课老师角色时（只查询成绩录入的功能）
			userFuncTreeDtoes = userServiceExt.queryScoreTree();
			FuncTreeDto func = new FuncTreeDto();
			func.setFuncId(userFuncTreeDtoes.get(0).getFuncId());
			func.setFuncName(userFuncTreeDtoes.get(0).getFuncName());
			func.setUrl(userFuncTreeDtoes.get(0).getUrl());
			func.setCommonFuncId(userFuncTreeDtoes.get(0).getCommonFuncId());
			func.setUserId(userid);
			userCommonFunc=new ArrayList<FuncTreeDto>();
			userCommonFunc.add(func);
		}
		return "/index.jsp";
	}
	private Map<String, Set<EdusysDto>> installEdusysDto(
			List<Map<String, String>> data) {
		if(data==null||data.size()<1)return null;
		Map<String, Set<EdusysDto>> datas=new HashMap<String,Set<EdusysDto>>();
		for(Map<String, String> dta:data){
			EdusysDto edusysdto=new EdusysDto();
			edusysdto.setEdusysId(dta.get("levelid"));
			edusysdto.setEdusysName(dta.get("levelname"));
			edusysdto.setLevelCode(dta.get("levelcode"));
			Set<EdusysDto> edusysList=datas.get(dta.get("campusid"));
			if(edusysList==null){
				edusysList=new TreeSet<EdusysDto>();
				datas.put(dta.get("campusid"),edusysList);
			}
			edusysList.add(edusysdto);
		}
		return datas;
	}
	private Map<UserRoleDto,List<String>> installRCMap(
			List<Map<String, String>> roletypeMaps) {
		if(roletypeMaps==null||roletypeMaps.size()<1)return null;
		Map<UserRoleDto,List<String>> roleMap=new HashMap<UserRoleDto,List<String>>();
		for(Map<String, String> dto:roletypeMaps){
			UserRoleDto roleDto=new UserRoleDto();
			roleDto.setRoleId(dto.get("roleid"));
			roleDto.setRoleName(dto.get("rolename"));
			roleDto.setRoleType(dto.get("roletype"));
			List<String>campuseids=roleMap.get(roleDto);
			if(campuseids==null){
				campuseids=new ArrayList<String>(0);
				roleMap.put(roleDto, campuseids);
			}
			campuseids.add(dto.get("campusid"));
		}
		return roleMap;
	}
	private UserRoleDto listMapContains(Map<UserRoleDto,List<String>>roletypeMaps,String roleType){
		if(roletypeMaps==null)return null;
		for(UserRoleDto dto:roletypeMaps.keySet()){
			if(roleType.equals(dto.getRoleType())){
				return dto;
			}
		}
		return null;
	}
}
