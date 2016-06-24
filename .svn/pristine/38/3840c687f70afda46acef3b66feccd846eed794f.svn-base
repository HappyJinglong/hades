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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;
import org.nestframework.data.Json;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.AppraisalxieStirngDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class PlayAndHealthXinAction extends BaseAction{
	// 学生
	public StudentxieDto student;
	// 学生列表
	public List<StudentxieDto> list;
	// 学生id
	public String studentid;
	public String discode;  
	public String cmis30id;
	public Integer level;
	public String json;
	public String levelCode;
	// 评价列表
	public List<AppraisalxieStirngDto> appraisalListString;
	public String evaluatedPersonID;
	// 名字
	public String name;
	public JSONArray arraylist;
	// 年级学期
	public String stdId;
	// 年级学期
	public String sex;
	// 年级学期
	public String studentNo;
	// 年级学期
	public String classname;
	public String rpID;
	public Integer purview;
	public Integer personStatus;
	// 年级学期
	public String termId;
	//更改后的学期
	public String termId1;
	public String termIdString;
	// 被评价人id
	public String evaluateid;
	//被评价人名字
	public String evaluateName;
	// 评价人身份
	public Integer personID=1;
	//评价被评价人ID
	public Integer id;
	//评价标识
	public String apprasialid;
	//班级id
	public String classid;
	// 评语
	public String apprasial;
	// 评价人
	public String appraser;
	//转换后的评论id
	public Integer fakeId;
	// 评价的id
	public String appraiserrid;
	public List<AppraisalxieStirngDto> listApraisal=new ArrayList<AppraisalxieStirngDto>();
	// 评价时间  evaluateid
	public String signDate;
    //类型
	public String evaluateType="3020";
	public String evaluateTypename;
	public String time;
	//评价人的id
	public String apperEduId;
	//被评价人的id
	public String eduId;
	//图片路径
	public String photoUrl;
	public String  pStudentid;//图片路径
	//班级名字
	public String  className;
	//查缓存用的标明+CacheDtos
	public  List<AapprasialCacheDto> aapprasialCacheDtosList;
	public SpeekDto speekDto;
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	@Spring
	public IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
 @Before
  public Object befo(HttpServletRequest req){
		 this.getLoginInfo(req);
		  levelCode=userDto.getLevelcode();
		  studentid=userDto.getPersonid();
		  discode=userDto.getDiscode();
		  cmis30id=userDto.getCmis30id();
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		  time=format.format(date); 
		  apperEduId=userDto.getEduId(); 
		  appraser=userDto.getStudentName();
		  name=userDto.getStudentName();
		  classid=userDto.getClassid();
		  isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
		  className="高中-"+userDto.getGradeName()+"-"+userDto.getClassName();
		  return null;
	}
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AppraisalxieStirngDto app11 = (AppraisalxieStirngDto) app1;
            	AppraisalxieStirngDto app22 = (AppraisalxieStirngDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
    @Json
	public Object queryData(HttpServletRequest req){
		List<StudentxieDto>stuIfos = (List<StudentxieDto>) req.getSession().getAttribute("studentInfo");
		if(null!=stuIfos && stuIfos.size()>0){
			List<String>listStr=new ArrayList<String>();
			for (StudentxieDto stI : stuIfos) {
				Map<String,Object>maps=new HashMap<String,Object>();
				maps.put("id",stI.getEduid());
				maps.put("name",stI.getName()+"_"+stI.getEduid());
				listStr.add(Utility.createJsonStr(maps));
			}
			return JSONObject.fromObject("{val:"+Utility.createJsonStr(listStr)+"}");
		}
		return null;
	}

	@DefaultAction //
	public Object defaultAction(HttpSession session){
		try {
		      if(null==termId){
				  termId =playAndHealthExt.findStundentTermId(Integer.valueOf(classid));
                  session.setAttribute("termId",termId);
		      }
			//全班同学的姓名 等信息
			appraisalListString = playAndHealthExt.selectSelfAppraiseXie(classid,evaluateType,termId,apperEduId,discode,cmis30id);
			if(null!=appraisalListString){
			   eduId=appraisalListString.get(0).getEduid();//第一个学的id
			   photoUrl=appraisalListString.get(0).getPhotoUrl();
			   evaluateName=appraisalListString.get(0).getEvaluateName();
			   pStudentid=appraisalListString.get(0).getStudentid();
			   
			    if("0".equals(isStartAppraiseCache)){ 
			    	//第一个学生的评价信息
			    	listApraisal=playAndHealthExt.findApprasial(evaluateType,termId,eduId,apperEduId,discode,cmis30id);
			    }else{
			    	   for(AppraisalxieStirngDto s: appraisalListString){
			    		   String ed= s.getEduid();
			    		   aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(ed, termId, evaluateType, "2", apperEduId,AapprasialCacheDto.class);
			    	      if(null==aapprasialCacheDtosList){
			    	    	  s.setSumnaber("0");
			    	      }else{
			    	    	  if(0<aapprasialCacheDtosList.size()){
			    	    		  s.setSumnaber("1");
			    	    	  }else{
			    	    		  s.setSumnaber("0");
			    	    	  }
			    	    	  
			    	      }
			    	   }
					aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, evaluateType, "2", apperEduId,AapprasialCacheDto.class);
				   if(null!=aapprasialCacheDtosList){
					if(0<aapprasialCacheDtosList.size()){
						for( AapprasialCacheDto ap: aapprasialCacheDtosList){
					    	 AppraisalxieStirngDto  dto =new AppraisalxieStirngDto();
					    	 dto.setApprasialid(ap.getApprasialid());
							 dto.setAppraisaltypeid(ap.getAppraisaltypeid());
							 dto.setAppraiserrid(ap.getAppraiserrid());
							 dto.setEduid(ap.getEdu_id());
							 dto.setSemesterid(ap.getSemesterid());
							 dto.setAppraseridentify(ap.getAppraseridentify());
							 dto.setStudentid(ap.getStudentid());
							 dto.setAppraser(ap.getAppraser());
							 dto.setApprasial(ap.getApprasial());
							 dto.setSignDate(StringToDatexie(ap.getSigndate()));
							 listApraisal.add(dto);
					    }
				   }
			     }
				   Collections.sort(listApraisal,compareByAppraiseId);
			    }
			   if(0==listApraisal.size()){
				   AppraisalxieStirngDto p=new AppraisalxieStirngDto();
				   p.setEduid(eduId);
				   p.setStudentid(pStudentid);
				   p.setAppraisaltypeid(evaluateType);
				   p.setApprasialid("0");
				   listApraisal.add(p);
			   }
			}
		    return "gzAppiser.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	/**
	 * 切换学生查询
	 */
	 public void chanStuder(HttpSession session,HttpServletResponse response){
		try {
			if("0".equals(isStartAppraiseCache)){ 
				 listApraisal=playAndHealthExt.findApprasial(evaluateType,termId,eduId,apperEduId,discode,cmis30id);	 
				 if(0==listApraisal.size()){
					   AppraisalxieStirngDto p=new AppraisalxieStirngDto();
					   p.setEduid(eduId);
					   p.setStudentid(pStudentid);
					   p.setAppraisaltypeid(evaluateType);
					   p.setApprasialid("0");
					   Date da=new Date();
					   String ss= dateToString(da);
					   Date tt=StringToDatexie(ss) ;
					   p.setSignDate(tt);
					   p.setAppraser(name);
					   listApraisal.add(p);
				   }
			}else{
				 aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, evaluateType, "2", apperEduId,AapprasialCacheDto.class);
				 if(null!=aapprasialCacheDtosList){
					 if(0<aapprasialCacheDtosList.size()){
					 for( AapprasialCacheDto ap: aapprasialCacheDtosList){
					    	 AppraisalxieStirngDto  dto =new AppraisalxieStirngDto();
					    	 dto.setApprasialid(ap.getApprasialid());
							 dto.setAppraisaltypeid(ap.getAppraisaltypeid());
							 dto.setAppraiserrid(ap.getAppraiserrid());
							 dto.setEduid(ap.getEdu_id());
							 dto.setSemesterid(ap.getSemesterid());
							 dto.setAppraseridentify(ap.getAppraseridentify());
							 dto.setStudentid(ap.getStudentid());
							 dto.setAppraser(ap.getAppraser());
							 dto.setApprasial(ap.getApprasial());
							 dto.setSignDate(StringToDatexie(ap.getSigndate()));
							 listApraisal.add(dto);
				       }
				    }else{
				    	  AppraisalxieStirngDto p=new AppraisalxieStirngDto();
						   p.setEduid(eduId);
						   p.setStudentid(pStudentid);
						   p.setAppraisaltypeid(evaluateType);
						   p.setApprasialid("0");
						   Date da=new Date();
						   String ss= dateToString(da);
						   Date tt=StringToDatexie(ss) ;
						   p.setSignDate(tt);
						   p.setAppraser(name);
						   //p.setApprasial("");
						   listApraisal.add(p);
				    }
				 }else{
					   AppraisalxieStirngDto p=new AppraisalxieStirngDto();
					   p.setEduid(eduId);
					   p.setStudentid(pStudentid);
					   p.setAppraisaltypeid(evaluateType);
					   p.setApprasialid("0");
					   Date da=new Date();
					   String ss= dateToString(da);
					   Date tt=StringToDatexie(ss) ;
					   p.setSignDate(tt);
					   p.setAppraser(name);
					   //p.setApprasial("");
					   listApraisal.add(p);
				 }
			 }
			   
			   ListToJson(response,listApraisal);
		} catch (Exception e) {
		}
	
	 }
	/**
	 * 将List转换为Json
	 * @param response
	 * @param list
	 */
			public void ListToJson(HttpServletResponse response,List list)
			{
				try {
					JsonConfig jsonConfig=new JsonConfig();
					DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
					jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
					arraylist=JSONArray.fromObject(list,jsonConfig);
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().write(arraylist.toString());
				} catch (IOException e) {
					logger.error("ListToJson(HttpServletResponse)", e);
				}
			}
	
	 /**
		 * 保存他人修改评价
		 * 
		 * @return
		 */
   public void doUpdataOtherProcess(HttpSession session,HttpServletResponse response) {
		 try {
				
			 if("0".equals(isStartAppraiseCache)){
			     playAndHealthExt.updataNewAppraisal(apprasialid,apprasial,signDate);
			 }else{
				  Integer apprasialidint=Integer.valueOf(apprasialid);
				  if(null!=signDate){
					  AapprasialCacheDto aapprasialCacheDto=new AapprasialCacheDto();
					  aapprasialCacheDto.setApprasial(apprasial);
					  aapprasialCacheDto.setSigndate(signDate);
					  Date da=StringToDate(signDate);
					  playAndHealthExt.updateCacheApper(eduId,termId, evaluateType,"2",apperEduId,apprasialidint,aapprasialCacheDto,apprasial,da); 
				  }else{
					  AapprasialCacheDto aapprasialCacheDto=new AapprasialCacheDto();
					  aapprasialCacheDto.setSigndate(signDate);
					  Date da=StringToDate(signDate);
					  playAndHealthExt.updataCachTime(eduId,termId,evaluateType,"2",apperEduId,apprasialidint,aapprasialCacheDto,da);
				  }
				}
				 response.setContentType("text/html; charset=utf-8");
			     response.getWriter().write("{success:'true'}");
		   if(evaluateType!=null){
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
			    }
			  
		      if(name!=null && evaluateType!=null){
				   String columnName=evaluateTypename;
				    Date date=new Date();
				   latestEvaluationRecordExt.setclassMateRecordToCache(name, evaluateType, apprasialid, columnName, evaluateName, eduId, date);
			   }
			   
			} catch (Exception e) {
				throw new ManagerException(e);
			}
		}
	/**
	 * 保存新的评价
	 * @param session
	 * @param response
	 */
	public void doUpdataOtherProcessNll(HttpSession session,HttpServletResponse response) {
		try {
			
			    AppraisalxieStirngDto appraisal = new AppraisalxieStirngDto();
				appraisal.setStudentid(pStudentid);//被评价人id
				appraisal.setAppraseridentify("2");//插入他人评价变换角色		
				appraisal.setAppraisaltypeid(evaluateType);// 评价类别
				appraisal.setAppraser(name);//评价人
				appraisal.setAppraiserrid(apperEduId);//评价人的标识
				appraisal.setApprasial(apprasial);// 评价内容
				Date da=StringToDatexie(signDate);
				appraisal.setSignDate(da);// 时间--界面
				if("0".equals(isStartAppraiseCache)){
					apprasialid=playAndHealthExt.insertSelfAppraisal(appraisal,termId,discode,cmis30id,eduId);
				}else{
				     apprasialid = baseInforManagerExt.queryProKey("A_APPRASIAL");
					AapprasialCacheDto acd=new  AapprasialCacheDto();
					acd.setSemesterid(termId);//缓存
					acd.setStudentid(pStudentid);//缓存
					acd.setApprasialid(apprasialid);
					acd.setAppraseridentify("2");//缓存
					acd.setAppraisaltypeid(evaluateType);//缓存
					acd.setAppraser(userDto.getStudentName());//缓存
					acd.setAppraiserrid(apperEduId);//缓存
					acd.setApprasial(apprasial);//缓存
				    acd.setEdu_id(eduId);
					acd.setSigndate(signDate);
					acd.setDiscode(String.valueOf(discode));
					acd.setCmis30id(String.valueOf(cmis30id));
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("appraseridentify","2");
					params.put("appraisaltypeid",evaluateType);
					params.put("apprasial",apprasial);
					params.put("studentid",pStudentid);
					params.put("appraser",userDto.getStudentName());
					params.put("appraiserrid",apperEduId);
					params.put("signDate",signDate);
					params.put("semesterid",termId);
					params.put("discode",discode);
					params.put("cmis30id",cmis30id);
					params.put("apprasialid",apprasialid);
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					playAndHealthExt.addCacheApper(params,eduId, termId, evaluateType, "2", apperEduId, apprasialid,acd);

				}
				
				response.setContentType("text/html; charset=utf-8");
			    response.getWriter().write("{success:'true',apprasialid:'"+apprasialid+"'}");
			 if(evaluateType!=null){
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
			    }
				   if(name!=null && evaluateType!=null){
					   String columnName=evaluateTypename;
					    Date date=new Date();
					   latestEvaluationRecordExt.setclassMateRecordToCache(name, evaluateType, apprasialid, columnName, evaluateName, eduId, date);
				   }
		} catch (Exception e) {
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
			  if("0".equals(isStartAppraiseCache)){ 
				  playAndHealthExt.deleApprasial(Integer.valueOf(apprasialid));
			  }else{
				  //缓存中删除
				    Integer st=Integer.valueOf(apprasialid);
				   playAndHealthExt.delectCacheGzApper(eduId,termId,evaluateType,"2",apperEduId,st);
			  }
			  response.setContentType("text/html; charset=utf-8");
			  response.getWriter().write("{success:'true'}");
			  String proKey=String.valueOf(apprasialid);
			  latestEvaluationRecordExt.deleteclassMateRecordInCache(termId, evaluateType, proKey);
		  } catch (Exception e) {
		   e.printStackTrace();
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
