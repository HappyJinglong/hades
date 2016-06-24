package com.flyrish.hades.redistest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flyrish.hades.service.ext.IAppraisalWritedStaticsExt;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

public class LymServiceTest {
//	private static IRedisServiceExt redisServiceExt;
//	private static IUserLoginServiceExt userLoginServiceExt;
//	private static ILatestEvaluationRecordExt latestEvaluationRecordExt;
	//初始化接口
//	@Before
	public void initRedisTemplate() throws IOException{
//		userLoginServiceExt =(IUserLoginServiceExt)ctx.getBean("userLoginServiceExt");
//		redisServiceExt =(IRedisServiceExt)ctx.getBean("redisServiceExt");
//		latestEvaluationRecordExt = (ILatestEvaluationRecordExt)ctx.getBean("latestEvaluationRecordExt");
	}

	public static void main(String[] args) {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("com/flyrish/hades/redistest/applicationContext-extservice.xml");
		IAppraisalWritedStaticsExt appraisalWritedStaticsExt =(IAppraisalWritedStaticsExt)ctx.getBean("appraisalWritedStaticsExt");
		
		List<String>test = new ArrayList<String>();
		test.add(" name = '张三'");
		test.add(" name = 'lisi'");
		List<String> querytest = appraisalWritedStaticsExt.querytest("张", "2",test);
		System.out.println(querytest);
	
	
	}
	
//	@Test
	public void constantBeanTest(){
//		初中Key：CM_null_2444042_24_2012002
//		高中Key：CM_null_2444042_24_2012003
/*		try {
			List<OFunc> conmmonFuncsCZ = redisServiceExt.readList("CM_11093_480598_3622_370");
			redisServiceExt.delete("CM_11093_480598_3622_370");
			for(OFunc of : conmmonFuncsCZ){
				System.out.println("cz:::"+of.getFuncname());
			}
		} catch (ForceException e) {
			e.printStackTrace();
		}*/
//		latestEvaluationRecordExt.deleteparentRecordToCache("07003145", "33", "83431951");
	}
	/*@Test
	public void queryAppraiseFromRedisTest(){
		List<SchoolTreeDto>singleStudentInfo = new ArrayList<SchoolTreeDto>();
		for(SchoolTreeDto stDto : studentInfos){
			if("07003934".equals(stDto.getEdusyId())){
				singleStudentInfo.add(stDto);
			}
		}
		List<AppraisalDto> queryAppraiseFromRedis = masterAppriseExt.queryAppraiseFromRedis(singleStudentInfo, termId, sectionIds, appraseridentify, appraiserrid, flag, params);
		System.out.println(queryAppraiseFromRedis.size());
		for(AppraisalDto aDto : queryAppraiseFromRedis){
			System.out.println(aDto.getStudentName()+"::::"+aDto.getApprasial());
		}
		
	}*/
}
