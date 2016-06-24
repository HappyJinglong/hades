package com.flyrish.hades.webapp.action.student;

import java.io.IOException;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.DateUtil;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AapprasialCacheXieDto;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class ZtreeAction extends BaseAction {
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;//评价人的id
	public Integer appraiserrid;//评价人的id
	public Integer level;//
	public String appraser;//评价人的名字
	public String evaluateType;
	public Integer termId;// 年级学期
	public Integer termId2;// 放在session年级学期
	public Integer termId1;//更改后的学期
	public Integer classid;//班级
	public Integer cmis30id;
	public Integer discode;
	public String evaluateName; //被评价人的名字
	public String id; //被评价人id
	public Integer apprasialid=0;//评价标识
	public String signDate;
	public JSONArray arraylist;
	public String levelCode;
	public String apprasial;// 评语
	public String apperEduId;//评价人的id
	public String evaluateTypename;//评价人的id
	public List<AapprasialCacheXieDto> apprasialCacheDtoList;
	public List<AppraisalxieDto> appraisalList;
	//思想
	public List<AppraisalxieDto> penseeList=new ArrayList<AppraisalxieDto>();
	//学业
	public List<AppraisalxieDto> successList=new ArrayList<AppraisalxieDto>();
	//合作
	public List<AppraisalxieDto> cooperateList=new ArrayList<AppraisalxieDto>();
	//运动
	public List<AppraisalxieDto> playList=new ArrayList<AppraisalxieDto>();
	//审美
	public List<AppraisalxieDto> tastelList=new ArrayList<AppraisalxieDto>();
	//个性
	public List<AppraisalxieDto> selfhoodList=new ArrayList<AppraisalxieDto>();
	public String json;
	//父类id
	public String pid=null;
	public String termIdString;
	//显示的名字
	public String name;
	public String ty="1";
	public Integer fakeId;
	public String edu_id;
	/**
	 * 被评价人ID.
	 */
	public Integer evaluatedPersonID;
	public SpeekDto speekDto;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	//查缓存用的标明+CacheDtos
	public  List<AapprasialCacheDto> aapprasialCacheDtosList;
	@Before
	public Object befo(HttpServletRequest req){
		 this.getLoginInfo(req);
		  levelCode=userDto.getLevelcode();
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
			
			if("0".equals(isStartAppraiseCache)){
				appraser=userDto.getStudentName();
				student=playAndHealthExt.findstudent(evaluatedPersonID,discode,cmis30id);
				evaluateName=student.getName();//被评价人的名字
				session.setAttribute("evaluateName",evaluateName);
				classid=student.getClassid();
				termIdString=playAndHealthExt.findStundentTermId(classid);
				termId=Integer.valueOf(termIdString);
				session.setAttribute("termId",termId);
				appraisalList = playAndHealthExt.allAppraise(termIdString,apperEduId,evaluatedPersonID,cmis30id,discode);
				for (AppraisalxieDto appraisalxieDto:appraisalList){
					int vv=appraisalxieDto.getAppraisaltypeid().intValue();
				   switch(vv){
				   case 3020:
						{
							//思想
							 penseeList.add(appraisalxieDto);
						   break;
						}
						case 8040:
						{
							//学业
							 successList.add(appraisalxieDto);
						   break;
						}
						case 4020:
						{
							//合作
							 cooperateList.add(appraisalxieDto);
							   break;
						}	
						case 5020:
							{
								//运动
							playList.add(appraisalxieDto);
								   break;
								}
						case 6020:
						{
							//审美
							tastelList.add(appraisalxieDto);
							break;
						}
						case 7030:
						{
							//个性
							 selfhoodList.add(appraisalxieDto);
							break;
						}
					
				 }
				} 
			
			}else{
				appraser=userDto.getStudentName();
				apperEduId= userDto.getEduId();
				student=playAndHealthExt.findstudent(evaluatedPersonID,discode,cmis30id);
				evaluateName=student.getName();//被评价人的名字
				classid=student.getClassid();
				edu_id=student.getEduid();
				session.setAttribute("edu_id",edu_id);
				termIdString=playAndHealthExt.findStundentTermId(classid);
				termId=Integer.valueOf(termIdString);
				session.setAttribute("termId",termId);
				//思想
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
			    
				
				//学业
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
		
				//合作
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
			    //运动
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
				   Collections.sort( playList,compareByAppraiseId);
			    }
		         //审美
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
				   Collections.sort(  tastelList,compareByAppraiseId);
			    }
				//个性
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
				   Collections.sort(  selfhoodList,compareByAppraiseId);
			    }
				
			}
			
			
		   if(penseeList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setAppraisaltypeid(3020);
			   appraisalList.setApprasialid(0);
			   penseeList.add(appraisalList);
		   }
		   if(successList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(8040); 
			   successList.add(appraisalList);
		   }
		   if(cooperateList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(4020);
			   cooperateList.add(appraisalList);
		   }
		   if(playList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setAppraisaltypeid(5020);
			   appraisalList.setApprasialid(0);
			   playList.add(appraisalList);
		   }
		   if(tastelList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(6020);
			   tastelList.add(appraisalList);
		   }
		   if(selfhoodList.size()==0){
			   AppraisalxieDto appraisalList=new AppraisalxieDto();
			   appraisalList.setApprasial(" ");
			   appraisalList.setStudentid(evaluatedPersonID);
			   appraisalList.setApprasialid(0);
			   appraisalList.setAppraisaltypeid(7030);
			   appraisalList.setFakeId(fakeId);
			   selfhoodList.add(appraisalList);
		   }
		   return "trpj.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	
	//修改学期
	
	public Object chaneTermId(HttpSession session){
		try {
			if("0".equals(isStartAppraiseCache)){
			 			StudentxieDto studentAppraser=playAndHealthExt.findstudent(studentid,discode,cmis30id);
						appraser=studentAppraser.getName();
						student=playAndHealthExt.findstudent(evaluatedPersonID,discode,cmis30id);
						evaluateName=student.getName();//被评价人的名字
						session.setAttribute("evaluateName",evaluateName);
						classid=student.getClassid();
						session.setAttribute("termId",termId1);
						termIdString=termId1.toString();
						appraisalList = playAndHealthExt.allAppraise(termIdString,apperEduId,evaluatedPersonID,cmis30id,discode);
						for (AppraisalxieDto appraisalxieDto:appraisalList){
						   int vv=appraisalxieDto.getAppraisaltypeid().intValue();
						   switch(vv){
						   case 3020:
								{
									//思想
									 penseeList.add(appraisalxieDto);
								   break;
								}
								case 8040:
								{
									//学业
									 successList.add(appraisalxieDto);
								   break;
								}
								case 4020:
								{
									//合作
									 cooperateList.add(appraisalxieDto);
									   break;
								}	
								case 5020:
									{
										//运动
									playList.add(appraisalxieDto);
										   break;
										}
								case 6020:
								{
									//审美
									tastelList.add(appraisalxieDto);
									break;
								}
								case 7030:
								{
									//个性
									 selfhoodList.add(appraisalxieDto);
									break;
								}
							
						 }
						} 
					}else{
                       //更改学期查看数据
						appraser=userDto.getStudentName();
						apperEduId= userDto.getEduId();
						student=playAndHealthExt.findstudent(evaluatedPersonID,discode,cmis30id);
						evaluateName=student.getName();//被评价人的名字
						classid=student.getClassid();
						edu_id=student.getEduid();
						session.setAttribute("edu_id",edu_id);
						session.setAttribute("termId",termId1);
						session.setAttribute("evaluateName",evaluateName);
						termIdString=termId1.toString();
						//思想
						aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "3020", "2", apperEduId,AapprasialCacheDto.class);
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
							   appraisalList.setAppraisaltypeid(3020);
							   appraisalList.setAppraseridentify(2);
							   penseeList.add(appraisalList);
						   }
						   Collections.sort(   penseeList,compareByAppraiseId);
					    }
						
						//学业
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
						   Collections.sort(successList,compareByAppraiseId);
					    }
				
						//合作
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
						   Collections.sort(cooperateList,compareByAppraiseId);
					    }
					    //运动
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
						   Collections.sort(playList,compareByAppraiseId);
					    }
				         //审美
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
						//个性
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
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setAppraisaltypeid(3020);
						   appraisalList.setApprasialid(0);
						   appraisalList.setFakeId(fakeId);
						   penseeList.add(appraisalList);
					   }
					   if(successList.size()==0){
						   AppraisalxieDto appraisalList=new AppraisalxieDto();
						   appraisalList.setApprasial(" ");
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setApprasialid(0);
						   appraisalList.setAppraisaltypeid(8040);
						   appraisalList.setFakeId(fakeId);
						   successList.add(appraisalList);
					   }
					   if(cooperateList.size()==0){
						   AppraisalxieDto appraisalList=new AppraisalxieDto();
						   appraisalList.setApprasial(" ");
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setApprasialid(0);
						   appraisalList.setAppraisaltypeid(4020);
						   appraisalList.setFakeId(fakeId);
						   cooperateList.add(appraisalList);
					   }
					   if(playList.size()==0){
						   AppraisalxieDto appraisalList=new AppraisalxieDto();
						   appraisalList.setApprasial(" ");
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setAppraisaltypeid(5020);
						   appraisalList.setApprasialid(0);
						   appraisalList.setFakeId(fakeId);
						   playList.add(appraisalList);
					   }
					   if(tastelList.size()==0){
						   AppraisalxieDto appraisalList=new AppraisalxieDto();
						   appraisalList.setApprasial(" ");
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setApprasialid(0);
						   appraisalList.setAppraisaltypeid(6020);
						   appraisalList.setFakeId(fakeId);
						   tastelList.add(appraisalList);
					   }
					   if(selfhoodList.size()==0){
						   AppraisalxieDto appraisalList=new AppraisalxieDto();
						   appraisalList.setApprasial(" ");
						   appraisalList.setStudentid(evaluatedPersonID);
						   appraisalList.setApprasialid(0);
						   appraisalList.setAppraisaltypeid(7030);
						   appraisalList.setFakeId(fakeId);
						   selfhoodList.add(appraisalList);
					   }
		   
		    return "trpj.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	public String time=format.format(date); 
	/**
	 * 保存他人修改评价
	 * 
	 * @return
	 */
	
	public void doUpdataOtherProcess(HttpSession session,HttpServletResponse response) {
		try {
			if("0".equals(isStartAppraiseCache)){
			            AppraisalxieDto appraisal = new AppraisalxieDto();
						appraisal.setApprasialid(apprasialid);
						appraisal.setApprasial(apprasial);// 评价内容
					    Date da=StringToDate(signDate);
						appraisal.setSignDate(da);// 时间--界面
						playAndHealthExt.updataAppraisal(appraisal);
						response.setContentType("text/html; charset=utf-8");
						response.getWriter().write("{success:'true',}");
					 }else{
						 edu_id=(String)session.getAttribute("edu_id");
						  termId=(Integer) session.getAttribute("termId");
						  termIdString=termId.toString();
						  AapprasialCacheDto aapprasialCacheDto=new AapprasialCacheDto();
						  aapprasialCacheDto.setApprasial(apprasial);
						  aapprasialCacheDto.setSigndate(signDate);
						  Date da=StringToDate(signDate);
						  
						  playAndHealthExt.updateCacheApper(edu_id,termIdString, evaluateType,"2",apperEduId,apprasialid,aapprasialCacheDto,apprasial,da);
						  response.setContentType("text/html; charset=utf-8");
							response.getWriter().write("{success:'true',}");
					 }
							    
					            String username =userDto.getStudentName();
							    String stuEduid =(String)session.getAttribute("edu_id");
							    String studentName=(String)session.getAttribute("evaluateName"); 
							   
							    if(evaluateType!=null&& studentName!=null && stuEduid!=null && studentName!=null){
								  
								   if("8040".equals(evaluateType)){
							    		evaluateTypename="学业成就";
							    	}else if("3020".equals(evaluateType)){
							    		evaluateTypename="思想道德";
							    	}else if("4020".equals(evaluateType)){
							    		evaluateTypename="合作与交流";
							    	}else if("5020".equals(evaluateType)){
							    		evaluateTypename="运动与健康";
							    	}else if("6020".equals(evaluateType)){
							    		evaluateTypename="审美与表现";
							    	}else if("7030".equals(evaluateType)){
							    		evaluateTypename="个性发展";
							    	}
								   String columnName=evaluateTypename;
								    Date date=new Date();
								    String proKey=String.valueOf(apprasialid);
								   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
							   }
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	//保存新增数据
	public void doUpdataOtherProcessNull(HttpSession session,HttpServletResponse response) {
		try {
			Integer pid=Integer.valueOf(id);
			StudentxieDto students=playAndHealthExt.findstudent(pid,discode,cmis30id);
			edu_id=students.getEduid();
			String pname=students.getName();
			session.setAttribute("edu_id",edu_id);
			edu_id=(String)session.getAttribute("edu_id");
			if("0".equals(isStartAppraiseCache)){
				termId=(Integer) session.getAttribute("termId");
				termIdString=termId.toString();
				StudentxieDto student1=playAndHealthExt.findstudent(studentid,discode,cmis30id);
				appraiserrid=student1.getStudentid();     
				AppraisalxieDto appraisal = new AppraisalxieDto();
				appraisal.setStudentid(new Integer(id));//被评价人id
				appraisal.setAppraseridentify(2);//插入他人评价变换角色		
				appraisal.setAppraisaltypeid(Integer.valueOf(evaluateType));// 评价类别
				appraisal.setAppraser(student1.getName());//评价人
				appraisal.setAppraiserridSting(apperEduId);//评价人的标识
				appraisal.setApprasial(apprasial);// 评价内容
				Date da=StringToDate(signDate);
				appraisal.setSignDate(da);// 时间--界面
				apprasialid=playAndHealthExt.insertSelfAppraisal(appraisal,termIdString,discode,cmis30id);
				String message="保存成功";
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',apprasialid:"+apprasialid+"}");
		    	String username =userDto.getStudentName();
				String stuEduid =edu_id;
				String  studentName=pname; 
				 if(evaluateType!=null&& studentName!=null && stuEduid!=null && studentName!=null){
					if("8040".equals(evaluateType)){
				    		evaluateTypename="学业成就";
				    	}else if("3020".equals(evaluateType)){
				    		evaluateTypename="思想道德";
				    	}else if("4020".equals(evaluateType)){
				    		evaluateTypename="合作与交流";
				    	}else if("5020".equals(evaluateType)){
				    		evaluateTypename="运动与健康";
				    	}else if("6020".equals(evaluateType)){
				    		evaluateTypename="审美与表现";
				    	}else if("7030".equals(evaluateType)){
				    		evaluateTypename="个性发展";
				    	}
					   String columnName=evaluateTypename;
					    Date date=new Date();
					    String proKey=String.valueOf(apprasialid);
					   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
				   }
			}else{
				String newId = baseInforManagerExt.queryProKey("A_APPRASIAL");
				AapprasialCacheDto acd=new  AapprasialCacheDto();
				termId=(Integer) session.getAttribute("termId");
				termIdString=termId.toString();//获得学期
				acd.setSemesterid(termIdString);//缓存
				AppraisalxieDto appraisal = new AppraisalxieDto();
				acd.setStudentid(id);//缓存
				acd.setApprasialid(newId);
				acd.setAppraseridentify("2");//缓存
				acd.setAppraisaltypeid(evaluateType);//缓存
				acd.setAppraser(userDto.getStudentName());//缓存
				acd.setAppraiserrid(apperEduId);//缓存
				acd.setApprasial(apprasial);//缓存
			    acd.setEdu_id(edu_id);
				acd.setSigndate(signDate);
				acd.setDiscode(String.valueOf(discode));
				acd.setCmis30id(String.valueOf(cmis30id));
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("appraseridentify","2");
				params.put("appraisaltypeid",evaluateType);
				params.put("apprasial",apprasial);
				params.put("studentid",id);
				params.put("appraser",userDto.getStudentName());
				params.put("appraiserrid",apperEduId);
				params.put("signDate",signDate);
				params.put("semesterid",termIdString);
				params.put("discode",discode);
				params.put("cmis30id",cmis30id);
				params.put("apprasialid",newId);
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				playAndHealthExt.addCacheApper(params,edu_id, termIdString, evaluateType, "2", apperEduId, newId,acd);
				String message="保存成功";
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',apprasialid:"+newId+"}");
		    	String username =userDto.getStudentName();
				String stuEduid =edu_id;
				String  studentName=pname; 
				    if(evaluateType!=null&& studentName!=null && stuEduid!=null && studentName!=null){
					   if("8040".equals(evaluateType)){
				    		evaluateTypename="学业成就";
				    	}else if("3020".equals(evaluateType)){
				    		evaluateTypename="思想道德";
				    	}else if("4020".equals(evaluateType)){
				    		evaluateTypename="合作与交流";
				    	}else if("5020".equals(evaluateType)){
				    		evaluateTypename="运动与健康";
				    	}else if("6020".equals(evaluateType)){
				    		evaluateTypename="审美与表现";
				    	}else if("7030".equals(evaluateType)){
				    		evaluateTypename="个性发展";
				    	}
					    String columnName=evaluateTypename;
					    Date date=new Date();
					    String proKey=newId;
					   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
				   }
			}
			
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	
	public Object doUpadateTime(HttpSession session) {
		try {
			if("0".equals(isStartAppraiseCache)){
				    Date da=StringToDate(signDate);
					playAndHealthExt.updataTime(apprasialid,da); 
			  }else{
				  edu_id=(String)session.getAttribute("edu_id");
				  termId=(Integer) session.getAttribute("termId");
				  termIdString=termId.toString();
				  AapprasialCacheDto aapprasialCacheDto=new AapprasialCacheDto();
				  aapprasialCacheDto.setSigndate(signDate);
				  Date da=StringToDate(signDate);
				  playAndHealthExt.updataCachTime(edu_id,termIdString,evaluateType,"2",apperEduId,apprasialid,aapprasialCacheDto,da);
				  
			  }
			return null;
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	//删除误操作数据 SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public void deletOtherProcess(HttpSession session,HttpServletResponse response) {
		try {
			  edu_id=(String)session.getAttribute("edu_id");
			  if("0".equals(isStartAppraiseCache)){
				    playAndHealthExt.deleApprasial(apprasialid);
					String message="删除成功";
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().write("{success:'true'}"); 
			  }else{
				      //缓存中删除
					termId=(Integer) session.getAttribute("termId");
					termIdString=termId.toString();//获得学期
					playAndHealthExt.delectCacheGzApper(edu_id,termIdString,evaluateType,"2",apperEduId,apprasialid);
					String message="删除成功";
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().write("{success:'true'}"); 
			  }
				 if(speekDto!=null){
					   if(edu_id!=null){
						    String proKey=String.valueOf(apprasialid);
						    String evaluateType=speekDto.getAppraisaltypeid();
						   latestEvaluationRecordExt.deleteclassMateRecordInCache(edu_id, evaluateType, proKey);
					   }
		        }
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	
	/**
	 * 字符串转日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date StringToDate(String dt) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dt);
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}

	
	
	
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
	
	//将List转换为Json
			public void ListToJson(HttpServletResponse response,HttpSession session,String information)
			{    termId=(Integer) session.getAttribute("termId");
			     termIdString=termId.toString();
				try {
					evaluatedPersonID=new Integer(id);
					student=playAndHealthExt.findstudent(evaluatedPersonID,discode,cmis30id);
					evaluateName=student.getName();//被评价人的名字
					classid=student.getClassid();
					//termId=playAndHealthExt.findStundentTermId(classid);
					appraisalList = playAndHealthExt.findGzReturnTree(evaluateType,termIdString,studentid,evaluatedPersonID,cmis30id,discode);
					if(0==appraisalList.size()){
						String fakeApprasialid=baseInforManagerExt.queryProKey("a_apprasial");
					    fakeId=Integer.valueOf(fakeApprasialid);
						AppraisalxieDto apprList=new AppraisalxieDto();
						   apprList.setApprasial(" ");
						   apprList.setStudentid(evaluatedPersonID);
						   apprList.setApprasialid(0);
						   apprList.setAppraisaltypeid(Integer.valueOf(evaluateType));
						   apprList.setFakeId(fakeId);
						   appraisalList.add(apprList);
					}
					JsonConfig jsonConfig=new JsonConfig();
					DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
					jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
					arraylist=JSONArray.fromObject(appraisalList,jsonConfig);
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().write("{success:'true',info:'"+information+"',content:"+arraylist+"}");
				
				} catch (IOException e) {
					throw new ManagerException(e);
				}
			}
			
			class DateJsonValueProcessor implements JsonValueProcessor
			{

				public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
					return null;
				}

				public Object processObjectValue(String key, Object value,
						JsonConfig jsonconfig) {
					if (value == null)  
		                return "";  
		            // 注意：在判断几个父子级类型时要先判断子类型再判断父类型  
		            if (value instanceof java.sql.Date) {  
		                String str = DateUtil.dateToStr((java.util.Date) value,  
		                        "yyyy-MM-dd");//这里是我封装的工具,可以使用SimpleDateFormat代替，一样  
		                return str;  
		            } else if (value instanceof java.sql.Timestamp  
		                    || value instanceof java.util.Date) {  
		                String str = DateUtil.dateToStr((java.util.Date) value,  
		                        "yyyy-MM-dd");  
		                return str;  
		            }  
		            return value.toString();  
				}
			}
	
}
