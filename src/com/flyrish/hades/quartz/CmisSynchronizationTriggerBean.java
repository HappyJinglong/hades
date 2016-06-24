package com.flyrish.hades.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt1;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.IUserLoginServiceExt;

/**
 * Created by yangbo on 14/10/28.
 */
public class CmisSynchronizationTriggerBean {
    /**
     * Logger
     */
    private static final Log logger = LogFactory.getLog(CmisSynchronizationTriggerBean.class);
    /**
     * 执行定时任务，每天凌晨开始执行（0：00：00）
     * @throws Exception
     */
    public void synchronizationCmis() throws Exception {
    	String isStartImportUserInfoToCache=constantBean.get("isStartImportUserInfoToCache");
    	//如果开启同步机制，则执行，反之，则不执行
    	if("1".equals(isStartImportUserInfoToCache)){
    		Long startdate=new Date().getTime();
    		logger.error("开始执行定时任务！");
    		List<LoginOUser> loginOUsers=userLoginServiceExt.queryLoginOUserAll();
    		if(loginOUsers==null||loginOUsers.size()<1){
    			logger.error("定时任务执行完毕，未获取任何用户，未更新到任何数据！");
    			return;
    		}
    		//同步数据开始
    		logger.error("开始更新缓存用户数据，共获取"+loginOUsers.size()+"用户，开始清理缓存数据。");
    		//删除缓存相应的用户数据
    		for(LoginOUser loginOUser:loginOUsers){
    			int count=0;
    			while(count<3){
	    			try{
	    				redisServiceExt.delete(loginOUser.getUsername()+Constant.R_REDIS_OUSER);
	    				break;
	    			}catch(Exception e){
	    				logger.error("定时任务删除key："+loginOUser.getUsername()+Constant.R_REDIS_OUSER+"第"+count+"次失败",e);
	    				count++;
	    			}
    			}
    		}
    		logger.error("清空缓存数据完毕，共清空"+loginOUsers.size()+"用户，开始向缓存添加用户信息。");
    		//开始向缓存添加数据
    		for(LoginOUser loginOUser:loginOUsers){
    			try {
    				List<LoginOUser> oUsers=null;
    				if(Integer.parseInt(loginOUser.getCount())>1){
    					oUsers=redisServiceExt.readList(loginOUser.getUsername()+Constant.R_REDIS_OUSER);
    				}
    				if(oUsers==null){
    					oUsers=new ArrayList<LoginOUser>();
    				}
    				oUsers.add(loginOUser);
    				redisServiceExt.save(loginOUser.getUsername()+Constant.R_REDIS_OUSER,oUsers);
    			} catch (ForceException e) {
    				logger.error("synchronizationCmis()",e);
    			}
    		}
    		Long enddate=new Date().getTime();
    		logger.error("缓存用户数据更新完毕，共更新"+loginOUsers.size()+"用户，定时任务结束。共用"+(enddate-startdate)+"ms");
    		Long informStartTime = new Date().getTime();
    		informDoQueAndCacheExt1.refreshInformInCache();
    		Long informEndtTime = new Date().getTime();
    		logger.error("缓存通知公告数据更新完毕，共更新"+loginOUsers.size()+"条数据。共用"+(informEndtTime-informStartTime)+"ms");
    		//开始清理用户缓存数据
    		redisServiceExt.cleanAllAppraserCache(Constant.R_REDIS_CACHE_LOGINKEY,constantBean.get("loginContailerNum"));
    		logger.error("缓存登录用户数据已被清理，定时任务结束。");
    	}
    }

    private ConstantBean constantBean;
    
	private IUserLoginServiceExt userLoginServiceExt;
	
	public IRedisServiceExt redisServiceExt;
	
	public IInformDoQueAndCacheExt1 informDoQueAndCacheExt1;
	
	public IInformDoQueAndCacheExt1 getInformDoQueAndCacheExt1() {
		return informDoQueAndCacheExt1;
	}
	public void setInformDoQueAndCacheExt1(
			IInformDoQueAndCacheExt1 informDoQueAndCacheExt1) {
		this.informDoQueAndCacheExt1 = informDoQueAndCacheExt1;
	}
	public ConstantBean getConstantBean() {
		return constantBean;
	}
	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public IUserLoginServiceExt getUserLoginServiceExt() {
		return userLoginServiceExt;
	}
	public void setUserLoginServiceExt(IUserLoginServiceExt userLoginServiceExt) {
		this.userLoginServiceExt = userLoginServiceExt;
	}
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}
	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}
    
}
