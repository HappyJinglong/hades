package com.flyrish.hades.webapp.action.student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AapprasialCacheXieDto;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class GzTreeShowAction extends BaseAction{
	
	public StudentxieDto student;//学生
	public StudentxieDto student1;//学生
	public List<StudentxieDto> list;//学生列表
	public Integer studentid;//评价人的id
	public Integer appraiserrid;//评价人的id
	public String appraser;//评价人的名字
	public String evaluateType;//评价类型
	public Integer termId;// 年级学期
	public Integer termId2;// 放在session年级学期k值
	public String termIdString;// 年级学期
	public Integer termId1;//更改后的学期
	public Integer classid;//班级
	public Integer cmis30id;
	public Integer discode;
	public Integer level;
	public String evaluateName; //被评价人的名字
	public String id; //被评价人id
	public Integer apprasialid=0;//评价标识
	public String signDate;//时间
	public Integer levelCode;
	public String apprasial;// 评语
	public List<AppraisalxieDto> appraisalList;
	public List<AppraisalxieDto> penseeList=new ArrayList<AppraisalxieDto>();	//思想
	public List<AppraisalxieDto> successList=new ArrayList<AppraisalxieDto>();//学业
	public List<AppraisalxieDto> cooperateList=new ArrayList<AppraisalxieDto>();//合作
	public List<AppraisalxieDto> playList=new ArrayList<AppraisalxieDto>();//运动
	public List<AppraisalxieDto> tastelList=new ArrayList<AppraisalxieDto>();//审美
	public List<AppraisalxieDto> selfhoodList=new ArrayList<AppraisalxieDto>();//个性
	public String json;
	public String pid=null;//父类id
	public String name;//显示的名字
	public String apperEduId;
	public String edu_id;
	public String ty="1";
	public Integer evaluatedPersonID;//被评价人ID.
	public Integer fakeId;
	public List<AapprasialCacheXieDto> apprasialCacheDtoList;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	//查缓存用的标明+CacheDtos
		public  List<AapprasialCacheDto> aapprasialCacheDtosList;
		@Spring
		public ILatestEvaluationRecordExt latestEvaluationRecordExt;
		
	@Before
	public Object befor(HttpServletRequest req){
		  this.getLoginInfo(req);
		  String levelCodeString=userDto.getLevelcode();
		  levelCode=Integer.valueOf(levelCodeString);
		  String studentidString=userDto.getPersonid(); 
		  studentid=Integer.valueOf(studentidString);
		  String discodeString=userDto.getDiscode();
		  discode=Integer.valueOf(discodeString);
		  String cmis30idString=userDto.getCmis30id();
		  cmis30id=Integer.valueOf(cmis30idString);
		  apperEduId=userDto.getEduId();
		  isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
		  return null;
	}
	/**
	 * 顺序排列
	 */
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AppraisalxieDto app11 = (AppraisalxieDto) app1;
            	AppraisalxieDto app22 = (AppraisalxieDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
	@DefaultAction 
	public Object defaultAction(HttpSession session){
		try {
				student=playAndHealthExt.findstudent(studentid,discode,cmis30id);
				appraser=student.getName();
				String ss=student.getClassid().toString();//banji
				List<SchoolTreeDto> studentInfo = playAndHealthExt.getStudentInfoAll(ss,studentid,cmis30id,discode);
				SchoolTreeDto dd=studentInfo.get(0);//第一个学生
				String str =dd.getId();
				Integer inte=Integer.valueOf(str);
				student1=playAndHealthExt.findstudent(inte,discode,cmis30id);
				evaluateName=student1.getName();//被评价人的名字
				edu_id=student1.getEduid();
				session.setAttribute("evaluateName",evaluateName);
				session.setAttribute("edu_id",edu_id);
				classid=student.getClassid();
				termIdString=playAndHealthExt.findStundentTermId(classid);
				termId=Integer.valueOf(termIdString);
				session.setAttribute("termId",termId);
				evaluatedPersonID=student1.getStudentid();
		if("0".equals(isStartAppraiseCache)){
				appraisalList = playAndHealthExt.allAppraise(termIdString,apperEduId,inte,cmis30id,discode);
				for (AppraisalxieDto appraisalxieDto:appraisalList){
					int vv=appraisalxieDto.getAppraisaltypeid().intValue();
				   switch(vv){
				   case 3020:
						{
							 penseeList.add(appraisalxieDto);
						   break;
						}
						case 8040:
						{
							 successList.add(appraisalxieDto);
						   break;
						}
						case 4020:
						{
							 cooperateList.add(appraisalxieDto);
							   break;
						}	
						case 5020:
							{
							playList.add(appraisalxieDto);
								   break;
								}
						case 6020:
						{
							tastelList.add(appraisalxieDto);
							break;
						}
						case 7030:
						{
							 selfhoodList.add(appraisalxieDto);
							break;
						}
					
				 }
				} 	
			
			
			}else{
				appraser=userDto.getStudentName();
				apperEduId= userDto.getEduId();
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "3020", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					    String datestring=appra.getSigndate();
					    appraisalList.setSignDate(StringToDatexie(datestring));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(3020);
					   appraisalList.setAppraseridentify(2);
					   penseeList.add(appraisalList);
				   }
				   Collections.sort( penseeList,compareByAppraiseId);
			    }
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "8040", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					   appraisalList.setSignDate(StringToDatexie(appra.getSigndate()));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(8040);
					   appraisalList.setAppraseridentify(2);
					   successList.add(appraisalList);
				   }
				   Collections.sort( successList,compareByAppraiseId);
			    }
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "4020", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					   appraisalList.setSignDate(StringToDatexie(appra.getSigndate()));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(4020);
					   appraisalList.setAppraseridentify(2);
					   cooperateList.add(appraisalList);
				   }
				   Collections.sort( cooperateList,compareByAppraiseId);
			    }
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "5020", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					   appraisalList.setSignDate(StringToDatexie(appra.getSigndate()));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(5020);
					   appraisalList.setAppraseridentify(2);
					   playList.add(appraisalList);
				   }
				   Collections.sort(  playList,compareByAppraiseId);
			    }
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "6020", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					   appraisalList.setSignDate(StringToDatexie(appra.getSigndate()));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(6020);
					   appraisalList.setAppraseridentify(2);
					   tastelList.add(appraisalList);
				   }
				   Collections.sort(tastelList,compareByAppraiseId);
			    }
				aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "7030", "2", apperEduId,AapprasialCacheDto.class);
			     //判断缓存是否有数据  没有查数据库
				if(aapprasialCacheDtosList!=null){
				   for( AapprasialCacheDto  appra  : aapprasialCacheDtosList){
					   AppraisalxieDto appraisalList=new AppraisalxieDto();
					   appraisalList.setStudentid(evaluatedPersonID);
					   appraisalList.setAppraser(appra.getAppraser());
					   appraisalList.setApprasial(appra.getApprasial());
					   appraisalList.setApprasialid(Integer.valueOf(appra.getApprasialid()));
					   appraisalList.setSignDate(StringToDatexie(appra.getSigndate()));
					   appraisalList.setEvaluateName(evaluateName);
					   appraisalList.setAppraisaltypeid(7030);
					   appraisalList.setAppraseridentify(2);
					   selfhoodList.add(appraisalList);
				   }
				   Collections.sort(selfhoodList,compareByAppraiseId);
			    }
				
			}
		if(penseeList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			   appraisalList.setAppraisaltypeid(3020);
			   appraisalList.setApprasialid(0);
			   penseeList.add(appraisalList);
		   }
		   if(successList.size()==0){
			
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			  appraisalList.setApprasialid(0);
			  appraisalList.setAppraisaltypeid(8040); 
			  successList.add(appraisalList);
		   
		   }
		   if(cooperateList.size()==0){
			   
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(4020);
			 
			   cooperateList.add(appraisalList);
		   }
		   if(playList.size()==0){
			   
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			   appraisalList.setAppraisaltypeid(5020);
			   appraisalList.setApprasialid(0);
			  
			   playList.add(appraisalList);
		   }
		   if(tastelList.size()==0){
			  
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(6020);
			
			   tastelList.add(appraisalList);
		   }
		   if(selfhoodList.size()==0){
			   
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(inte);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(7030);
			   
			   selfhoodList.add(appraisalList);
		   }
		    return "trpj.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}

	/**
	 * 时间转换
	 * @param d
	 * @return
	 */
private Date StringToDatexie(String d) {
	try {
		/*java.util.Date valueOf = java.sql.Date.valueOf(d);
		return valueOf;*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = this.dateToString(sdf.parse(d));
		return java.sql.Date.valueOf(date);
		} catch (Exception e) {
		String date = this.dateToString(new Date());
		java.util.Date valueOf = java.sql.Date.valueOf(date);
		return valueOf;
	}
}

private String dateToString(Date signDate){
	SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
	String date =simple.format(signDate);
	return date;
}

	
	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	public String time=format.format(date); 
}
