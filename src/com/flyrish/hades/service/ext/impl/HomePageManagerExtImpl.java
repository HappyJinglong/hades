package com.flyrish.hades.service.ext.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IHomePageManagerExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.DataSource;

public class HomePageManagerExtImpl extends JdbcRootManager implements IHomePageManagerExt{

	private IRedisServiceExt redisServiceExt;
	
	private IBaseInforManagerExt baseInforManagerExt;
	
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}

	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}

	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}

	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
	@DataSource("read")
	public List<FuncTreeDto> queryCommonFunc(String userId, String roleId,Integer funcLevelType) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		params.put("funcLevelType", funcLevelType);
		try {
			 List<FuncTreeDto> commonFunc = this.findList("ShowUserFuncDwr.queryUseNumUserMenu.query",params, new RowMapper(){
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							FuncTreeDto func = new FuncTreeDto();
							func.setFuncId(rs.getString("funcId"));
							func.setFuncName(rs.getString("funcName"));
							func.setUrl(rs.getString("execfilename"));
							func.setCommonFuncId(rs.getString("commonfuncid"));
							func.setUserId(rs.getString("userid"));
							return func;
						}
					});
			 return commonFunc;
		} catch (Exception e) {
			logger.error("queryCommonFunc(Integer,Integer,String)",e);
		}
		return null;
	}

	@Override
	public List<OFunc> queryCommonFuncFromRedis(UserDto userDto,Integer funcLevelType) {
		try {
			String key = "CM_"+userDto.getCampuseId()+"_"+userDto.getUserid()+"_"+userDto.getRoleId()+"_"+userDto.getLevelid();
			//redisServiceExt.delete(key);
			List<OFunc> conmmonFuncs =  redisServiceExt.readList(key);
			List<OFunc> conmmonFuncsSort = new ArrayList<OFunc>();
			if(null!=conmmonFuncs && conmmonFuncs.size()>0){
				
				conmmonFuncs = this.checkCommonMenus(conmmonFuncs);
				redisServiceExt.save(key, conmmonFuncs);
				
				//如果conmmonFuncs长度小于4
				if(conmmonFuncs.size()<4){
					Collections.sort(conmmonFuncs, compareByClickCount);
					return conmmonFuncs;
				}
				//从缓存取出数据 首先按照是否有url排序
				for(OFunc of : conmmonFuncs){
					if(NestUtil.isNotEmpty(of.getExecfilename())){
						conmmonFuncsSort.add(of);
					}
				}
				//如果conmmonFuncs长度大于4
				if(null!=conmmonFuncsSort){
					if(conmmonFuncsSort.size()>4){
						//按照点击次数排序
						return this.conmmonFuncsSortByClickCount(conmmonFuncsSort);
					}else if(conmmonFuncsSort.size()==4){
						//四个常用功能菜单直接返回
						Collections.sort(conmmonFuncsSort, compareByClickCount);
						return conmmonFuncsSort;
					}else if(conmmonFuncsSort.size()<4){
						List<OFunc> fourCommonMenus = this.getFourCommonMenus(conmmonFuncs, conmmonFuncsSort);
						Collections.sort(fourCommonMenus, compareByClickCount);
						return fourCommonMenus;
					}
				}
			}
		} catch (ForceException e) {
			logger.error("queryCommonFuncFromRedis(UserDto,Integer)",e);
		}
		return null;
	}
	private List<OFunc> checkCommonMenus (List<OFunc> conmmonFuncs){
		List<String>checkIds = new ArrayList<String>();
		List<OFunc>newConmmonFuncs = new ArrayList<OFunc>();
		for(OFunc of : conmmonFuncs){
			if(NestUtil.isNotEmpty(of.getFuncid().toString())){
				checkIds.add(of.getFuncid().toString());
			}
		}
		if(null!=checkIds && checkIds.size()>0){
			newConmmonFuncs = this.queryNewUrl(checkIds);
		}
		if(null!=newConmmonFuncs && newConmmonFuncs.size()>0){
			for(OFunc of : conmmonFuncs){
				for(OFunc newOf : newConmmonFuncs){
					if(newOf.getFuncid().equals(of.getFuncid())){
						of.setExecfilename(newOf.getExecfilename());
					}
				}
			}
		}
		return conmmonFuncs;
	}
	private List<OFunc> queryNewUrl(List<String> checkIds) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("checkIds", checkIds);
		try {
			 List<OFunc> commonFunc = this.findList("IHomePageManagerExt.queryNewUrl.query",params, new RowMapper(){
						public Object mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							OFunc of = new OFunc();
							of.setFuncid(rs.getBigDecimal("funcid"));
							of.setExecfilename(rs.getString("execfilename"));
							return of;
						}
					});
			 return commonFunc;
		} catch (Exception e) {
			logger.error("queryNewUrl(List<String>)",e);
		}
		return null;
	}

	private List<OFunc> getFourCommonMenus(List<OFunc> conmmonFuncs,List<OFunc> conmmonFuncsSort) {
		for(OFunc of : conmmonFuncs){
			boolean isExist = false;
			for(OFunc cfs : conmmonFuncsSort){
				//排序容器中是否已经存在  存在的话将标识符置为存在状态
				if(of.getFuncid().equals(cfs.getFuncid())){
					isExist = true;
					break;
				}
			}
			//如果没有在排序容器中存在  添加  如果等于4 跳出循环
			if(!isExist){
				conmmonFuncsSort.add(of);
				if(conmmonFuncsSort.size()==4){
					break;
				}
			}
		}
		return conmmonFuncsSort;
	}

	private List<OFunc> conmmonFuncsSortByClickCount(List<OFunc> conmmonFuncsSort) {
		Collections.sort(conmmonFuncsSort, compareByClickCount);
		return conmmonFuncsSort.subList(0, 4);
	}
	public  final Comparator compareByClickCount = new Comparator(){          
        public int compare(Object commonMenu1, Object commonMenu2) {  
            try{                                          
            	OFunc commonMenu11 = (OFunc) commonMenu1;
            	OFunc commonMenu22 = (OFunc) commonMenu2;
    			return commonMenu11.compareTo(commonMenu22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };

	@Override
	public void saveCommonMenuToRedis(UserDto userDto, List<OFunc> commonFuncs) {
		try {
			String key = "CM_"+userDto.getCampuseId()+"_"+userDto.getUserid()+"_"+userDto.getRoleId()+"_"+userDto.getLevelid();
			redisServiceExt.save(key, commonFuncs);
		} catch (Exception e) {
			logger.error("saveCommonMenuToRedis(UserDto,List<OFunc>)",e);
		}
	}

	@Override
	public boolean saveOrUpdateCommonMenuToRedis(UserDto userDto, String funcId,Integer funcLevelType,String funcMessg) {
		try {
			String key = "CM_"+userDto.getCampuseId()+"_"+userDto.getUserid()+"_"+userDto.getRoleId()+"_"+userDto.getLevelid();
			List<OFunc> singleCommonMenus = redisServiceExt.readList(key);
			this.saveMenusToRedis(userDto, funcId, funcMessg, key, singleCommonMenus);
			return true;
		} catch (Exception e) {
			logger.error("saveOrUpdateCommonMenuToRedis(UserDto, String,String)",e);
			return false;
		}
	}

	private void saveMenusToRedis(UserDto userDto, String funcId,
			String funcMessg, String key, List<OFunc> singleCommonMenus)
			throws ForceException {
		if(null!=singleCommonMenus && singleCommonMenus.size()>0){
			boolean isExist = false;
			for(OFunc of : singleCommonMenus){
				if(of.getFuncid().equals(new BigDecimal(funcId))){
					//更新数据
					of.setClickCount(of.getClickCount()+1);
					isExist = true;
					break;
				}
			}
			if(!isExist){
				String[] messgs = funcMessg.split("_@_");
				OFunc newCommonMenu = new OFunc();
				newCommonMenu.setFuncid(new BigDecimal(funcId));
				newCommonMenu.setFuncname(messgs[1]);
				newCommonMenu.setExecfilename(messgs[0]);
				newCommonMenu.setClickCount(1);
				singleCommonMenus.add(newCommonMenu);
			}
			redisServiceExt.save(key, singleCommonMenus);
		}
	}
	public static void main(String[] args) {
	}

	@DataSource("read")
	public List<String> queryAllCommonMenusToRedis() {
		try {
			 Map<String,Object>params = new HashMap<String, Object>();
			return this.findList("IHomePageManagerExt.queryAllCommonMenusToRedis.query",params, new RowMapper(){
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String key = rs.getString("key");
						String value = rs.getString("value");
						this.putCommonMenuToRedis(key,value);
						return key;
					}
					//将数据库的常用功能放入缓存中
					private void putCommonMenuToRedis(String key, String value) {
						try {
							List<OFunc> commonMenus= new ArrayList<OFunc>();
							String[] menus = value.split(",");
							if(null!=menus && menus.length>0){
								for(String menu : menus){
									OFunc of = new OFunc();
									String[] ofItems = menu.split("@");
									if(null!=ofItems && ofItems.length>0){
										of.setUserId(ofItems[0]);
										of.setFuncid(new BigDecimal(ofItems[1]));
										of.setFuncname(ofItems[2]);
										of.setCommonFuncId(ofItems[3]);
										if(ofItems.length>4){
											of.setExecfilename(ofItems[4]);
										}
										of.setClickCount(1);
									}
								commonMenus.add(of);
								}
							}
							redisServiceExt.save(key, commonMenus);
						} catch (ForceException e) {
							e.printStackTrace();
						}
					}
				});
		} catch (Exception e) {
			logger.error("queryAllCommonMenusToRedis()",e);
		}
		return null;
	}
}
