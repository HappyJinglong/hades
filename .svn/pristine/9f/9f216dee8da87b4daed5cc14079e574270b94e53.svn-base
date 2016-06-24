package com.flyrish.hades.webapp.action.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;

import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.webapp.action.BaseAction;


public class CzZtreeActio extends BaseAction{
	//评价标识
	public String part_id;
	public Integer level;
	// 评语
	public String part_info;
	//评价类型
	public String two_part_id;
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
	public String classid;
	public String json;
	//父类id
	public String pid=null;
	//显示的名字
	public String name;
	//结的的id
	public String id;
	public String ty="1";
	public String fakeId;
	//评价人id
	public String apperEduId;
	//名字
	public String evaluateTypename;
	//教育id
	public String edu_id;
	public StudentxieDto student;
	//学生列表
	public List<StudentxieDto> list;
	public Integer studentid;
	public String signer_name;
	public Integer termidInteger;
	//学期
	public String  termId1;
	public Integer discode;  
	public Integer cmis30id;
	public JSONArray arraylist;
	public String levelCode;
	  //查缓存用的标明+CacheDtos
		public  List<PartInfoCacheDto> aapprasialCacheDtosList;
	
	public String evaluatedPersonID="474435";
	public String cmis30idString;
	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	public SpeekDto speekDto;
	@Before
	public Object befor(HttpServletRequest req){
		  this.getLoginInfo(req);
		  levelCode=userDto.getLevelcode();
		  String studentidString=userDto.getPersonid(); 
		  studentid=Integer.valueOf(studentidString);
		  String discodeString=userDto.getDiscode();
		  discode=Integer.valueOf(discodeString);
		   cmis30idString=userDto.getCmis30id();
		  cmis30id=Integer.valueOf(cmis30idString);
		  apperEduId=userDto.getEduId();
		  signer_name=userDto.getStudentName();//登录人的名字
		  isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
	     return null;
	}
	
	/**
	 * 列表转换
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
				Integer inte=Integer.valueOf(evaluatedPersonID);
				StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
				name=student1.getName();//被评价人名字
				edu_id=student1.getEduid();
				session.setAttribute("name",name);
				session.setAttribute("edu_id",edu_id);
				termidInteger=student1.getTermid();
				session.setAttribute("termidInteger",termidInteger);
				classid=student1.getClassid().toString();
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
	/**
	 * 切换学期
	 * @param session
	 * @return
	 */
	public Object chaneTermId(HttpSession session){
		try {   
			    Integer inte=Integer.valueOf(evaluatedPersonID);
				StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
				name=student1.getName();
				classid=student1.getClassid().toString();
				session.setAttribute("name", name);
				termidInteger=Integer.valueOf(termId1);
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
				edu_id=(String)session.getAttribute("edu_id");
				String termIdString=termId1;
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
				//合作
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
	
	
	
	/**
	 * 保存他人修改评价
	 * 
	 * @return
	 */
	public String createDate;
	public void doUpdataOtherProcess(HttpSession session ,HttpServletResponse response) {
		try {
			 edu_id=(String)session.getAttribute("edu_id");
			    PartInfoXieDto  appr1 =new  PartInfoXieDto();
				appr1.setPart_id(part_id);
				appr1.setPart_info(part_info);// 评价内容
				Date da=StringToDate(createDate);
				appr1.setCreateDate(da);// 时间--界面
			
			if("0".equals(isStartAppraiseCache)){
					czplayAndHealthExt.czupdataAppraisal(appr1);
			}else{
				   Integer termiInteger=(Integer)session.getAttribute("termidInteger");
				   String  termidString= String.valueOf(termiInteger);
				   PartInfoCacheDto cacheDto =new PartInfoCacheDto();
				   cacheDto.setCreateDate(createDate);
				   cacheDto.setPart_info(part_info);
				   czplayAndHealthExt.czupdataAppraisalCade(edu_id,termidString,two_part_id,"同学",apperEduId,part_id,cacheDto,appr1);
			}
			String message="修改成功";
			 response.setContentType("text/html; charset=utf-8");
			response.getWriter().write("{success:'true',info:'"+message+"'}");
					    String username =signer_name;
					    String stuEduid =edu_id;
					    String  studentName=(String)session.getAttribute("name"); 
					    String evaluateType=two_part_id;
					    if(evaluateType!=null&& username!=null && stuEduid!=null && studentName!=null){
						   if("45".equals(evaluateType)){
					    		evaluateTypename="学业成就";
					    	}else if("32".equals(evaluateType)){
					    		evaluateTypename="思想道德";
					    	}else if("52".equals(evaluateType)){
					    		evaluateTypename="合作与交流";
					    	}else if("62".equals(evaluateType)){
					    		evaluateTypename="运动与健康";
					    	}else if("72".equals(evaluateType)){
					    		evaluateTypename="审美与表现";
					    	}else if("92".equals(evaluateType)){
					    		evaluateTypename="个性发展";
					    	}
						   String columnName=evaluateTypename;
						    Date date=new Date();
						    String proKey=String.valueOf(part_id);
						   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
					    }
				
		} catch (Exception e) {
			try {
				response.getWriter().write("{success:'false'}");
			} catch (IOException e1) {
			}
			throw new ManagerException(e);
		}
	}
	/**
	 * 保存评价
	 * 
	 * @return
	 */
	
	public void doUpdataOtherProcessNull(HttpSession session ,HttpServletResponse response) {
		try {
			
			edu_id=(String)session.getAttribute("edu_id");
		   if("0".equals(isStartAppraiseCache)){	
				PartInfoXieDto  appr =new  PartInfoXieDto();
				appr.setStudentid(evaluatedPersonID);//被评价人id
				appr.setWrite_man("同学");
				appr.setPart_info(part_info);
				termidInteger=(Integer)session.getAttribute("termidInteger");
				String  sts=termidInteger.toString();
				appr.setTermid(sts);
				appr.setSigner_name(signer_name);
				appr.setTwo_part_id(two_part_id);
				appr.setCmis30id(cmis30idString);
				appr.setUserid(apperEduId);                   
				appr.setDiscode(discode.toString());
				Date da=StringToDate(createDate);
				appr.setCreateDate(da);// 时间--界面
				String pid= czplayAndHealthExt.czinsertSelfAppraisal(appr);
				String message="保存成功";
				 response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',info:'"+message+"',pid:'"+pid+"'}");
		   }else{
			   String newId = baseInforManagerExt.queryProKey("partinfo");
			    PartInfoCacheDto cacheDto =new PartInfoCacheDto();
				cacheDto.setStudentid(evaluatedPersonID);
				cacheDto.setWrite_man("同学");
				cacheDto.setPart_info(part_info);
				termidInteger=(Integer)session.getAttribute("termidInteger");
				String  termid=termidInteger.toString();
				cacheDto.setTermid(termid);
				cacheDto.setSigner_name(signer_name);
				cacheDto.setTwo_part_id(two_part_id);
				cacheDto.setCmis30id(cmis30idString);
				cacheDto.setUserid(apperEduId);
				cacheDto.setDiscode(discode.toString());
				Date da=StringToDate(createDate); // 时间--界面 数据库参数
				cacheDto.setCreateDate(createDate);
				cacheDto.setPart_id(newId);
				czplayAndHealthExt.addCachApper(edu_id,termid,two_part_id,"同学",apperEduId,newId,cacheDto,da);
				String message="保存成功";
				 response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',info:'"+message+"',pid:'"+newId+"'}");
		 }
				    String username =signer_name;
				    String stuEduid =edu_id;
				    String  studentName=(String)session.getAttribute("name"); 
				   if(two_part_id!=null&& username!=null && stuEduid!=null && studentName!=null){
					   if("45".equals(two_part_id)){
				    		evaluateTypename="学业成就";
				    	}else if("32".equals(two_part_id)){
				    		evaluateTypename="思想道德";
				    	}else if("52".equals(two_part_id)){
				    		evaluateTypename="合作与交流";
				    	}else if("62".equals(two_part_id)){
				    		evaluateTypename="运动与健康";
				    	}else if("72".equals(two_part_id)){
				    		evaluateTypename="审美与表现";
				    	}else if("92".equals(two_part_id)){
				    		evaluateTypename="个性发展";
				    	}
					   String columnName=evaluateTypename;
					    Date date=new Date();
					    String proKey=String.valueOf(pid);
					   latestEvaluationRecordExt.setclassMateRecordToCache(username, two_part_id, proKey, columnName, studentName, stuEduid, date);
				   }
			
		} catch (Exception e) {
			try {
				response.getWriter().write("{success:'false'}");
			} catch (IOException e1) {
			}
			throw new ManagerException(e);
		}
	}
	/**
	 * 删除评价
	 * 
	 * @return
	 */
	
	 public void deleApprasial(HttpSession session,HttpServletResponse response){
		  response.setCharacterEncoding("utf-8");
		  try {
			  edu_id=(String)session.getAttribute("edu_id");
			  if("0".equals(isStartAppraiseCache)){
				  PrintWriter pw = response.getWriter();
				   czplayAndHealthExt.deleApprasial(part_id,discode,cmis30id);
			   }else{
				   Integer termiInteger=(Integer)session.getAttribute("termidInteger");
				   String  termidString= String.valueOf(termiInteger);
				   czplayAndHealthExt.deleApprasialCade(edu_id,termidString,two_part_id,"同学",apperEduId,part_id,discode,cmis30id);
			   }  
			    String message="删除成功";
			    response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',info:'"+message+"'}");
				String proKey=String.valueOf(part_id);
				latestEvaluationRecordExt.deleteclassMateRecordInCache(edu_id, two_part_id, proKey);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }

	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	public String time=format.format(date); 
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
  //修改时间
	public Object doUpadateTime(HttpSession session) {
		try {
			Date da=StringToDate(createDate);
			  if("0".equals(isStartAppraiseCache)){
			    czplayAndHealthExt.czupdataTime(part_id,da,discode,cmis30id);
			  }else{
				   edu_id=(String)session.getAttribute("edu_id");
				   Integer termiInteger=(Integer)session.getAttribute("termidInteger");
				   String  termidString= String.valueOf(termiInteger);
				   PartInfoCacheDto cacheDto =new PartInfoCacheDto();
				   cacheDto.setCreateDate(createDate);
				   czplayAndHealthExt.czupdataTimeCade(edu_id,termidString,two_part_id,"同学", apperEduId, part_id,cacheDto,da,discode,cmis30id);
				   
			  }
			    
			return null;
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	/**
	 * 将List转换为Json
	 * @param response
	 * @param session
	 * @param information
	 */
		public void ListToJson(HttpServletResponse response,HttpSession session,String information)
		{
			try {
			Integer inte=Integer.valueOf(evaluatedPersonID);
			StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
			name=student1.getName();
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			  termidInteger= (Integer)session.getAttribute("termidInteger");
			 String stid=termidInteger.toString();
			 appraisalList =czplayAndHealthExt.czfindreturn(signer_name,stid,evaluatedPersonID,cmis30id,discode,two_part_id);
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
		/**
		 * 时间转换
		 * @param d
		 * @return
		 */
		private Date StringToDatexie(String d) {
			try {
				java.util.Date valueOf = java.sql.Date.valueOf(d);
				return valueOf;
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
