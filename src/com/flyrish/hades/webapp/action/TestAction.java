package com.flyrish.hades.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nestframework.addons.spring.Spring;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.LoginOUser;
import com.flyrish.hades.dto.StudentInfoDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IAattachFileExt;
import com.flyrish.hades.service.ext.IImportDataToRedisCacheExt;
import com.flyrish.hades.service.ext.IInformDoQueAndCacheExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILoginServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.IUserLoginServiceExt;

public class TestAction extends BaseAction {
	@Spring
	private IUserLoginServiceExt userLoginServiceExt;
	@Spring
	public IRedisServiceExt redisServiceExt;
	@Spring
	public ILoginServiceExt loginServiceExt;
	@Spring
	public IInformDoQueAndCacheExt informDoQueAndCacheExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	@Spring
	public IAattachFileExt aattachFileExt;
	@Spring
	public IImportDataToRedisCacheExt importDataToRedisCacheExt;
	public String flag;
	public Object defaultAction(){
		List<LoginOUser> loginOUsers=userLoginServiceExt.queryLoginOUserAll();
		//latestEvaluationRecordExt.setheadMasterRecordToCache("1111","2012003","222333","columnum","22222","研究和学习","张三","0022334",new Date());
		System.out.println("总数量："+loginOUsers.size());
		int count=0;
		for(LoginOUser loginOUser:loginOUsers){
			count++;
			try {
				List<LoginOUser> oUsers=null;
				if(Integer.parseInt(loginOUser.getCount())>1){
					oUsers=redisServiceExt.readList(loginOUser.getUsername()+Constant.R_REDIS_OUSER);
				}
				if(oUsers==null){
					oUsers=new ArrayList<LoginOUser>();
				}
				oUsers.add(loginOUser);
				System.out.println(count);
				redisServiceExt.save(loginOUser.getUsername()+Constant.R_REDIS_OUSER,oUsers);
			} catch (ForceException e) {
				e.printStackTrace();
			}
			System.out.println("总数量："+loginOUsers.size()+"::当前数据："+count);
		}
		System.out.println("aaaaa");
		return null;
	}
	public Object insertStudentInfo(){
		List<StudentInfoDto> studentInfoDtos=userLoginServiceExt.queryStudentInfosAll();
		System.out.println("总数量："+studentInfoDtos.size());
		int count=0;
		for(StudentInfoDto dto:studentInfoDtos){
			count++;
			try {
				if(NestUtil.isEmpty(dto.getEdu_id()))continue;
				redisServiceExt.saveObjList("stuinfos_"+dto.getStudentno()+"_"+dto.getCmis30id(),dto.getEdu_id(),60*60*24);
				redisServiceExt.saveObjList("stuinfos_"+dto.getStudentName()+"_"+dto.getCardid()+"_"+dto.getCmis30id(),dto.getEdu_id(),60*60*24);
				redisServiceExt.saveObjList("stuinfos_"+dto.getStudentName()+"_"+dto.getSex()+"_"+dto.getBirthday()+"_"+dto.getCmis30id(),dto.getEdu_id(),60*60*24);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("总数量："+studentInfoDtos.size()+"::当前数据："+count);
		}
		System.out.println("数据已完成");
		return null;
	}
	public Object testCache() throws InterruptedException{
		/*ThreadDemo t=new ThreadDemo(redisServiceExt);
		t.start();*/
		importDataToRedisCacheExt.doStart();
		return null;
	}
	public Object testCleanData(){
		importDataToRedisCacheExt.startCleanDoubleData();
		return null;
	}
/*	public Object testQueueNum(){
		while(true){
			System.out.println("总数为："+informDoQueAndCacheExt.size());
		}
	}*/
	public Object sheQueSer(){
		importDataToRedisCacheExt.doStart();
		return null;
	}
	public Object cleanInformCache(){
		return null;
	}
	//更新高中附件的外键
	public Object updateAttachForKey(){
		aattachFileExt.refreshAattachFileExt();
		return null;
	}
	public static void main(String[]args){
		UserDto dto=new UserDto();
		dto.setClassid("aaaa");
		UserDto dto1=new UserDto();
		dto1.setClassid("aaaa");//c41107fc1eb56131383e41a1a3494021,c41107fc1eb56131383e41a1a3494021
		System.out.println(dto.equals(dto1));
	}
	public Object updateFamliyCache(){
		importDataToRedisCacheExt.udpateParentFamliy();
		System.out.println("缓存数据已更新完毕");
		return null;
	}
	public Object testMoreAction() throws ForceException{
		/*redisServiceExt.delete("wwqueue");
		for(int i=0;i<100;i++){
			DemoThread thread=new DemoThread(redisServiceExt);
			thread.start();
		}
		while(DemoThread.threadcount<100){
		}
		System.out.println("已完成");*/
		
		/*DemoThread.threadcount=0;
		DemoThread.count=0;
		for(int i=0;i<100;i++){
			DemoThread thread=new DemoThread(redisServiceExt);
			thread.start();
		}
		while(DemoThread.threadcount<100){
		}
		System.out.println(DemoThread.array.size()+"已完成"+DemoThread.threadcount);
		DemoThread.threadcount=0;
		DemoThread.count=0;
		
		//redisServiceExt.delete("weixinxa");*/
		//List<Integer> lisize=redisServiceExt.readObjList("wwqueue");
		/*System.out.println(lisize.size());*/
	/*	DemoThread.count=0;*/
		/*List<String>lens=redisServiceExt.readObjList("weixinxa");
		System.out.println(lens.size());*/
		//redisServiceExt.saveObjMap("wwwzeng","wwwwwwww","ssss");
		/*redisServiceExt.delObjMap("wwwzeng","wwwwwwww");
		redisServiceExt.delObjMap("wwwzeng","qqqq");
		//System.out.println(redisServiceExt.readObjMapValue("wwwzeng","wwwwwwww"));
		Map<String,Object> map=redisServiceExt.readObjMap("wwwzeng");
		for(String key:map.keySet()){
			System.out.println(key+":::"+map.get(key));
		}*/
		/*redisServiceExt.delete("wwqueue");
		for(int i=0;i<100000;i++){
			System.out.println(i);
			redisServiceExt.insertQueue("wwqueue",i);
		}
		System.out.println("over");*/
		/*for(int i=0;i<100000;i++){
			System.out.println(i);
			redisServiceExt.insertQueue("wwqueue",i);
		}
		System.out.println("over");*/
		redisServiceExt.save("isok","1");
		/*Long startTime=new Date().getTime();
		for(int i=0;i<1000;i++){
			AppraiseBaseDto obj=new AppraiseBaseDto();
			System.out.println(i);
			//redisServiceExt.save("ddd", obj);
			redisServiceExt.saveObjList("www",obj);
		}
		Long endTime=new Date().getTime();
		System.out.println("插入时间:"+(endTime-startTime)+"ms");*/
		return null;
	}
}
