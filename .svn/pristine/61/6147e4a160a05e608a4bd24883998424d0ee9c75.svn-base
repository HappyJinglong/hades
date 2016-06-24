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

import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class CzTreeShow extends BaseAction{
	public List<PartInfoXieDto> appraisalList;
	//思想
	public List<PartInfoXieDto> penseeList=new ArrayList<PartInfoXieDto>();
	//学业
	public List<PartInfoXieDto> successList=new ArrayList<PartInfoXieDto>();
	//合作
	public List<PartInfoXieDto> cooperateList=new ArrayList<PartInfoXieDto>();
	//运动
	public List<PartInfoXieDto> playList=new ArrayList<PartInfoXieDto>();
	//审美
	public List<PartInfoXieDto> tastelList=new ArrayList<PartInfoXieDto>();
	//个性
	public List<PartInfoXieDto> selfhoodList=new ArrayList<PartInfoXieDto>();
	public String json;
	//父类id
	public String pid=null;
	//显示的名字
	public String name;
	//结的的id
	public String id;
	public String ty="1";
	public String apperEduId;
	//班级
	public String classid;
	//被评价人ID.
	public String evaluatedPersonID;
	public String fakeId;
	public String edu_id;
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;
	public String signer_name;
	public Integer termidInteger;
	public Integer discode;  
	public Integer cmis30id;
	public Integer levelCode;
	 //查缓存用的标明+CacheDtos
	public  List<PartInfoCacheDto> aapprasialCacheDtosList;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
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
		  signer_name=userDto.getStudentName();//登录人的名字
		  isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
	     return null;
	}
	
	/**
	 * 排列
	 */
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	PartInfoXieDto app11 = (PartInfoXieDto) app1;
            	PartInfoXieDto app22 = (PartInfoXieDto) app2;
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
			String ss=student.getClassid().toString();
			classid=ss;
			List<SchoolTreeDto> studentInfo = playAndHealthExt.getStudentInfoAll(ss,studentid,cmis30id,discode);
			SchoolTreeDto dd=studentInfo.get(0);
			evaluatedPersonID=dd.getId();
			Integer inte=Integer.valueOf(evaluatedPersonID);
			StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
			name=student1.getName();
			edu_id=student1.getEduid();
			session.setAttribute("name",name);
			session.setAttribute("edu_id",edu_id);
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			termidInteger=student.getTermid();
			session.setAttribute("termidInteger",termidInteger);
			
		if("0".equals(isStartAppraiseCache)){	
			appraisalList =czplayAndHealthExt.czAllAppraise(apperEduId,termidInteger,evaluatedPersonID,cmis30id,discode);
			for (PartInfoXieDto PartInfoXieDto:appraisalList){
			String str=PartInfoXieDto.getTwo_part_id();
	         Integer it = new Integer(str);  
	         int i = it.intValue();  
	          switch(i){
			   case 32:
					{
						 penseeList.add(PartInfoXieDto);
					   break;
					}
					case 45:
					{
						 successList.add(PartInfoXieDto);
					   break;
					}
					case 52:
					{
						 cooperateList.add(PartInfoXieDto);
						   break;
					}	
					case 62:
						{
						playList.add(PartInfoXieDto);
							   break;
							}
					case 72:
					{
						tastelList.add(PartInfoXieDto);
						break;
					}
					case 92:
					{
						 selfhoodList.add(PartInfoXieDto);
						break;
					}
			 }
			} 
		}else{
			String termIdString=String.valueOf(termidInteger);
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "32", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					   PartInfoXieDto appraisalList=new PartInfoXieDto();
					   	appraisalList.setPart_id(appra.getPart_id());
						appraisalList.setTermid(appra.getTermid());
						appraisalList.setStudentid(appra.getStudentid());
						appraisalList.setPart_info(appra.getPart_info());
						appraisalList.setSigner_name(signer_name);
						appraisalList.setTwo_part_id("32");
						appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
						appraisalList.setWrite_man("同学");
					   penseeList.add(appraisalList);
				   }
				  Collections.sort(penseeList,compareByAppraiseId);
				
			    }
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "45", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					PartInfoXieDto appraisalList=new PartInfoXieDto();
					appraisalList.setStudentid(appra.getStudentid());
					appraisalList.setPart_id(appra.getPart_id());
					appraisalList.setTermid(appra.getTermid());
					appraisalList.setPart_info(appra.getPart_info());
					appraisalList.setSigner_name(signer_name);
					appraisalList.setTwo_part_id("45");
					appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
					appraisalList.setWrite_man("同学");
					successList.add(appraisalList);
				}
				  Collections.sort(successList,compareByAppraiseId);
			}
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "52", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					PartInfoXieDto appraisalList=new PartInfoXieDto();
					appraisalList.setStudentid(appra.getStudentid());
					appraisalList.setPart_id(appra.getPart_id());
					appraisalList.setTermid(appra.getTermid());
					appraisalList.setPart_info(appra.getPart_info());
					appraisalList.setSigner_name(signer_name);
					appraisalList.setTwo_part_id("52");
					appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
					appraisalList.setWrite_man("同学");
					cooperateList.add(appraisalList);
				}
				Collections.sort(cooperateList,compareByAppraiseId);
			}
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "62", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					PartInfoXieDto appraisalList=new PartInfoXieDto();
					appraisalList.setStudentid(appra.getStudentid());
					appraisalList.setPart_id(appra.getPart_id());
					appraisalList.setTermid(appra.getTermid());
					appraisalList.setPart_info(appra.getPart_info());
					appraisalList.setSigner_name(signer_name);
					appraisalList.setTwo_part_id("62");
					appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
					appraisalList.setWrite_man("同学");
					playList.add(appraisalList);
				}
				Collections.sort(playList,compareByAppraiseId);
			}
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "72", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					PartInfoXieDto appraisalList=new PartInfoXieDto();
					appraisalList.setStudentid(appra.getStudentid());
					appraisalList.setPart_id(appra.getPart_id());
					appraisalList.setTermid(appra.getTermid());
					appraisalList.setPart_info(appra.getPart_info());
					appraisalList.setSigner_name(signer_name);
					appraisalList.setTwo_part_id("72");
					appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
					appraisalList.setWrite_man("同学");
					tastelList.add(appraisalList);
				}
				Collections.sort(tastelList,compareByAppraiseId);
			}
			aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu_id, termIdString, "92", "同学", apperEduId,PartInfoCacheDto.class);
			if(aapprasialCacheDtosList!=null){
				for( PartInfoCacheDto  appra  : aapprasialCacheDtosList){
					PartInfoXieDto appraisalList=new PartInfoXieDto();
					appraisalList.setStudentid(appra.getStudentid());
					appraisalList.setPart_id(appra.getPart_id());
					appraisalList.setTermid(appra.getTermid());
					appraisalList.setPart_info(appra.getPart_info());
					appraisalList.setSigner_name(signer_name);
					appraisalList.setTwo_part_id("92");
					appraisalList.setCreateDate(StringToDatexie(appra.getCreateDate()));
					appraisalList.setWrite_man("同学");
					 selfhoodList.add(appraisalList);
				}
				Collections.sort(selfhoodList,compareByAppraiseId);
			}
		}
		   if(penseeList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("32");
			   appraisalList.setStudentid(evaluatedPersonID);
			   penseeList.add(appraisalList);
		   }
		   if(successList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("45");
			   appraisalList.setStudentid(evaluatedPersonID);
			   successList.add(appraisalList);
		   }
		    if(cooperateList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("52");
			   appraisalList.setStudentid(evaluatedPersonID);
			   cooperateList.add(appraisalList);
		   }
		    if(playList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("62");
			   appraisalList.setStudentid(evaluatedPersonID);
			   playList.add(appraisalList);
		   }
		    if(tastelList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("72");
			   appraisalList.setStudentid(evaluatedPersonID);
			   tastelList.add(appraisalList);
		   }
		   if(selfhoodList.size()==0){
			   PartInfoXieDto appraisalList=new PartInfoXieDto();
			   appraisalList.setPart_info(" ");
			   appraisalList.setPart_id("0");
			   appraisalList.setTwo_part_id("92");
			   appraisalList.setStudentid(evaluatedPersonID);
			   selfhoodList.add(appraisalList);
		   }
		    return "cztrpj.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}

	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	public String time=format.format(date); 
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
}
