package com.flyrish.hades.redistest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IHighSchoolCacheExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMidSchoolDataInitCacheExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;

public class RedisApiTest {
	public static ApplicationContext ctx =new ClassPathXmlApplicationContext("com/flyrish/hades/redistest/applicationContext-extservice.xml");
	public static IRedisServiceExt redisServiceExt;
	public static ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public static IMidSchoolDataInitCacheExt midSchoolDataInitCacheExt;
	public static IHighSchoolCacheExt highSchoolCacheExt;
	public static int count=0;
	static{
		redisServiceExt=(IRedisServiceExt)ctx.getBean("redisServiceExt");
		latestEvaluationRecordExt=(ILatestEvaluationRecordExt)ctx.getBean("latestEvaluationRecordExt");
		midSchoolDataInitCacheExt=(IMidSchoolDataInitCacheExt)ctx.getBean("midSchoolDataInitCacheExt");
		highSchoolCacheExt=(IHighSchoolCacheExt)ctx.getBean("highSchoolCacheExt");
	}
	//@Before
	public void initRedisTemplate() throws IOException{
		redisServiceExt=(IRedisServiceExt)ctx.getBean("redisServiceExt");
		latestEvaluationRecordExt=(ILatestEvaluationRecordExt)ctx.getBean("latestEvaluationRecordExt");
		midSchoolDataInitCacheExt=(IMidSchoolDataInitCacheExt)ctx.getBean("midSchoolDataInitCacheExt");
		highSchoolCacheExt=(IHighSchoolCacheExt)ctx.getBean("highSchoolCacheExt");
	}
	//@Test
	public void testCacheImpl(){
		ApracticesCacheDto dto =new ApracticesCacheDto();
		dto.setEdu_id("01084017");
		dto.setAppraisaltypeid("2222");
		dto.setAppraseridentify("5");
		dto.setSemesterid("20122");
		dto.setStudentid("qqqqqqqqqqq");
		dto.setCmis30id("bbbbbbbbbbb");
		dto.setAppraiserrid("2222222222222222222");
		dto.setPracticeid("6666666666");
		dto.setItem1("weiooweiroiueroierworwewero");
		dto.setItem11("ioptyuyopiuyuipkldfg");
		ISqlElement sqlDemo=new ISqlElement() {
			@Override
			public String getSql() {
				return "delete  from dddabc";
			}
			@Override
			public Map<String, Object> getParamsMap() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public Object[] getParams() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//latestEvaluationRecordExt.addRecodeInCacheByProKey(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(),dto);
		/*ApracticesCacheDto dto1=latestEvaluationRecordExt.queryRecodeInCache(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(),ApracticesCacheDto.class);
		System.out.println("添加前！"+(dto1==null?null:dto1.getEdu_id()));
		latestEvaluationRecordExt.addRecodeInCacheByProKey(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(),dto,sqlDemo);*/
		//latestEvaluationRecordExt.updateRecodeInCacheByProKey(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(),dto, sqlDemo);
		//latestEvaluationRecordExt.delRecodeInCacheByProKey(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(), sqlDemo,ApracticesCacheDto.class);
		//ApracticesCacheDto dto2=latestEvaluationRecordExt.queryRecodeInCache(dto.getEdu_id(),dto.getSemesterid(),"12",dto.getAppraseridentify(),dto.getAppraiserrid(),dto.getPracticeid(),ApracticesCacheDto.class);
		//测试普通表添加接口
		//System.out.println("测试已通过！"+(dto2==null?null:dto2.getCmis30id()));
		//latestEvaluationRecordExt.addAttachFileInCache(dto.getEdu_id(),dto.getEdu_id(),dto,"aaa");
		//String foreignKey,String attachid,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz
		//latestEvaluationRecordExt.deleteAttachFileInCache(dto.getEdu_id(),dto.getEdu_id(),"aaa","12", sqlDemo, ApracticesCacheDto.class);
		//List<ApracticesCacheDto> dto1=latestEvaluationRecordExt.queryAttachFileInCache(dto.getEdu_id(), "aaa", ApracticesCacheDto.class);
		//System.out.println("测试已通过！"+(dto1==null?null:dto1.getCmis30id()));
		//(String foreignKey,String prokey,T t,String tablename,String two_part_id,ISqlElement sqlDemo);
		//latestEvaluationRecordExt.addChildrenObjectInCache(dto.getEdu_id(),dto.getEdu_id(),dto,"aaa","12",sqlDemo);
		//latestEvaluationRecordExt.updateChildrenObjectlInCache(dto.getEdu_id(),dto.getEdu_id(),dto,"aaa","12",sqlDemo);
		//(String foreignKey,String prokey,String tablename,Class clazz);
		//latestEvaluationRecordExt.deleteChildrenObjectInCache(dto.getEdu_id(),dto.getEdu_id(),"aaa","12",sqlDemo,ApracticesCacheDto.class);
		ApracticesCacheDto dto1=latestEvaluationRecordExt.queryChildrenObjectInCache(dto.getEdu_id(),dto.getEdu_id(),"aaa",ApracticesCacheDto.class);
		//System.out.println("测试已通过！"+(dto1==null?null:dto1.getCmis30id()));
	}
	public static void main(String[]args) throws Exception{
		//09071075_20152_54_家长_09071075p
		//08071830_20152_23_家长_08071830p
		//08031841_20152_23_家长_08031841p
		//08071830_20152_23_家长_08071830p
		//12277937_12_2040_5_12277937p
		//10223911_12_8040_5_10223911p
		/*ApracticesCacheDto dto =new ApracticesCacheDto();
		dto.setEdu_id("01084017");
		dto.setAppraisaltypeid("2222");
		dto.setAppraseridentify("5");
		dto.setSemesterid("20122");
		dto.setStudentid("qqqqqqqqqqq");
		dto.setCmis30id("bbbbbbbbbbb");
		dto.setAppraiserrid("2222222222222222222");
		dto.setPracticeid("6666666666");
		dto.setItem1("weiooweiroiueroierworwewero");
		dto.setItem11("ioptyuyopiuyuipkldfg");
		ISqlElement sqlDemo=new ISqlElement() {
			@Override
			public String getSql() {
				return "insert  from dddabc";
			}
			@Override
			public Map<String, Object> getParamsMap() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public Object[] getParams() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t,ISqlElement sqlDemo,String signdate);
		//latestEvaluationRecordExt.addRecodeInCacheByProKey("dddd","4444","34","5","aaa","12","dd",sqlDemo,"2012-01-12ddd");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		sf.setLenient(false);
		sf.parse("2013-02-22dd");
		String[] signdateStr="2016-03-26上学期".split("-");
		Integer.parseInt(signdateStr[2].trim());*/
		/*for(int i=0;i<20;i++){
			CacheThreadDemo thread=new CacheThreadDemo(redisServiceExt,latestEvaluationRecordExt);
			thread.start();
		}*/
		/*Map<String,String>objMap=redisServiceExt.readObjMap("discodeSuccuessInCache",String.class);
		redisServiceExt.delete("error_task");
		System.out.println(objMap);*/
		//redisServiceExt.delete("90704782_ouser");
		//redisServiceExt.save("isok","0");
		/*redisServiceExt.insertQueue("test","1");
		redisServiceExt.insertQueue("test","2");
		redisServiceExt.insertQueue("test","3");
		redisServiceExt.insertQueue("test","4");
		
		List<String> listStr=redisServiceExt.readObjList("test");*/
		//redisServiceExt.delete("sys_subject");
		//redisServiceExt.cleanAllAppraserCache(Constant.R_REDIS_CACHE_LOGINKEY,"1000");
		//redisServiceExt.delete("login_user09026789");
		/*redisServiceExt.save("isok","1");*/
		//Object obj=redisServiceExt.readSingle("12277937_12_2040_5_12277937p");
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		/*double d=12.26;
		System.out.println((float)(Math.round(d*10))/10);
		System.out.println("已完成1");*/
		//07030026_20152_34_家长_07030026--2922304566
		//07030026_20152_46_家长_07030026--2922304603
		//07030026_20152_54_家长_07030026--2922304604
		//07030026_20152_64_家长_07030026--2922304628
		//07030026_20152_74_家长_07030026--2922304632
		//07030026_20152_94_家长_07030026--2922304633
		//2922304561
		//redisServiceExt.deleteStr("07030026_20152_23_家长_07030026");
		/*SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		sf.setLenient(false);
		sf.parse("20616-05-07");
		String[] signdateStr="20616-05-07".split("-");
		int yyyylength=signdateStr[0].length();
		Integer.parseInt(signdateStr[2].trim());
		System.out.println("已删除"+yyyylength);*/
	}
}
