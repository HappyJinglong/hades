package com.flyrish.hades.webapp.action.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class CzPlayAndHealthXinAction extends BaseAction{
	//学生
	public StudentxieDto student;
	//学生列表
	public List<StudentxieDto> list;
	//学生id
	public Integer studentid;
	
	//自我评价列表
	public List<PartInfoXieDto> partInfoDtoList;
	public List<PartInfoXieDto> partInfoDtoListClassStuder;
	public List<PartInfoXieDto> appraisalList;
	public List<StudentxieDto> listApraisal=new ArrayList<StudentxieDto>();
	//被评价人ID.
	public String evaluatedPersonID;
	//名字
	public String name;
	//学期
	public Integer termId;
	public Integer termId1;
	// 被评价人id
	public String evaluateid;
	//被评价人名字
	public String evaluateName;
	//评价标识
	public String part_id;
	public Integer level;
	//班级id
	public Integer classid;
	// 评语
	public String part_info;
	// 评价人姓名
	public String signer_name;
	// 评价时间  
	public String createDate;
    //评价人类型
	public String write_man;
    //评价类型
	public String two_part_id="32";
	//转换后的评论id
	public String fakeId;
	public String time;
	public String cmis30idString;
	public String evaluateTypename;
	public String className;
	public Integer discode;  
	public Integer cmis30id;
	public JSONArray arraylist;
	public String levelCode;
	public String apperEduId;
	public String eduId;
	public  List<PartInfoCacheDto> aapprasialCacheDtosList;
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
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		  time=format.format(date); 
		  signer_name=userDto.getStudentName();
		  classid=Integer.valueOf(userDto.getClassid());
		  className="初中-"+userDto.getGradeName()+"-"+userDto.getClassName();
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
			    if(null==termId){
			       termId=Integer.valueOf(userDto.getTermId());
			    }
				if("0".equals(isStartAppraiseCache)){
					 partInfoDtoListClassStuder=czplayAndHealthExt.selectClassStudent(studentid,classid,termId,apperEduId,two_part_id,discode,cmis30id);
					 eduId=partInfoDtoListClassStuder.get(0).getEduid();
				     evaluateName= partInfoDtoListClassStuder.get(0).getPname();
					 partInfoDtoList=czplayAndHealthExt.selectSelfAppraiseXie(studentid,classid,termId,apperEduId,two_part_id,discode,cmis30id);
					 listApraisal =this.initApprise(partInfoDtoList);
				}else{
					   partInfoDtoListClassStuder=czplayAndHealthExt.selectClassStudentCade(studentid,classid,termId,apperEduId,two_part_id,discode,cmis30id);
					    eduId=partInfoDtoListClassStuder.get(0).getEduid();
						evaluateName= partInfoDtoListClassStuder.get(0).getPname();
					for( PartInfoXieDto   p:   partInfoDtoListClassStuder){
						   String edu=p.getEduid();
						   String term=String.valueOf(termId);
						   String Sname=p.getPname();
						   aapprasialCacheDtosList=latestEvaluationRecordExt.queryRecodeInCache(edu, term, two_part_id, "同学", apperEduId,PartInfoCacheDto.class);
						   List<PartInfoXieDto> lst1=new ArrayList<PartInfoXieDto>();
						  if(null==aapprasialCacheDtosList){
					    	   PartInfoXieDto dto= new PartInfoXieDto();
					    	   dto.setEduid(edu);
					    	   dto.setStudentid(p.getStudentid());
					    	   lst1.add(dto);
					    	   p.setContnumber("0");
					       }else{
					    	    if(0<aapprasialCacheDtosList.size()){
								 for( PartInfoCacheDto t: aapprasialCacheDtosList){
									 PartInfoXieDto dto= new PartInfoXieDto();
									    dto.setPart_id(t.getPart_id());
									   // dto.setStudentid(p.getStudentid());
									    dto.setStudentid(p.getStudentid());
										dto.setTermid(t.getTermid());
										dto.setPart_info(t.getPart_info());
										dto.setSigner_name(t.getSigner_name());
										dto.setTwo_part_id(t.getTwo_part_id());
							            dto.setCreateDate(StringToDatexie(t.getCreateDate()));
							            dto.setPname(Sname);
										dto.setWrite_man("同学");
										dto.setEduid(t.getEdu_id());
										lst1.add(dto);
								 }
								 p.setContnumber("1");
					        }else{
					        	   PartInfoXieDto dto= new PartInfoXieDto();
						    	   dto.setEduid(edu);
						    	   dto.setStudentid(p.getStudentid());
						    	   lst1.add(dto);
						    	   p.setContnumber("0");
					        }
								
					       }
						  Collections.sort(lst1,compareByAppraiseId);
						 for(PartInfoXieDto dto:lst1){
								//学生评价信息模型
								StudentxieDto studentDto = new StudentxieDto();
									//如果该list还没有数据 说明还没有填如数据  将查询到的数据放入该list中
									if(listApraisal.size()==0){
										Integer it = Integer.valueOf(dto.getStudentid());
										studentDto.setStudentid(it);
										studentDto.setName(p.getPname());
										studentDto.setPhotoUrl(p.getPhotoUrl());
										studentDto.getCzaInfos().add(dto);
										studentDto.setEduid(dto.getEduid());
										if(dto.getPart_info()==null){
											dto.setPart_id("0");
										}
										listApraisal.add(studentDto);
									}else{//如果 有数据了 遍历该list   将已有的数据和查询出来的数据进行比对  
												//如果已经存在了该条数据  直接添加评价信息
												//否则 将数据重新装入容器
										boolean isAdd = false;
										for(StudentxieDto fDto:listApraisal){
											if(NestUtil.isEmpty(dto.getStudentid()))
												continue;
											Integer it = Integer.valueOf(dto.getStudentid());
											if(it.equals(fDto.getStudentid())){
												fDto.getCzaInfos().add(dto);  
												isAdd=true;
												break;
											}
										}
										if(!isAdd){
											if(NestUtil.isEmpty(dto.getStudentid()))
												continue;
											Integer it = Integer.valueOf(dto.getStudentid());
											studentDto.setStudentid(it);
											studentDto.setName(p.getPname());
											studentDto.setPhotoUrl(p.getPhotoUrl());
											studentDto.setEduid(dto.getEduid());
											studentDto.getCzaInfos().add(dto);
											if(dto.getPart_info()==null){
												dto.setPart_id("0");  
											}
											listApraisal.add(studentDto);
										}
									}
							}
						  
						  
					  
					  }
					
				}
				return "czAppiser.jsp";
		
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	/**
	 * 变化学期
	 * @param session
	 * @return
	 */
	public Object chaneTermId(HttpSession session){
		try {
			
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			classid=student.getClassid();
			session.setAttribute("termId",termId1);
			partInfoDtoList=czplayAndHealthExt.selectSelfAppraiseXie(studentid,classid,termId1,apperEduId,two_part_id,discode,cmis30id);
			long endTime=System.currentTimeMillis();
			listApraisal =this.initApprise(partInfoDtoList);
			session.setAttribute("studentInfo", listApraisal);
			// 如果用户没有修改权限，并且记录集为空，则添加一个模板样式
			return "czotherappraise_list11.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	/**
	 * 保存他人修改评价
	 * 
	 * @return
	 */
	public void doUpdataOtherProcess(HttpSession session ,HttpServletResponse response) {
		try {

				      PartInfoXieDto  appr1 =new  PartInfoXieDto();
						appr1.setPart_id(part_id);
						appr1.setPart_info(part_info);// 评价内容
						appr1.setCreateDate(StringToDate(time));// 时间--界面
						
						if("0".equals(isStartAppraiseCache)){
						 czplayAndHealthExt.czupdataAppraisal(appr1);
						}else{
							  
							   String  termidString= String.valueOf(termId);
							   PartInfoCacheDto cacheDto =new PartInfoCacheDto();
							   cacheDto.setPart_info(part_info);
							   czplayAndHealthExt.czupdataAppraisalCade(eduId,termidString,two_part_id,"同学",apperEduId,part_id,cacheDto,appr1);
						}
						
						String message="修改成功";
						response.setContentType("text/html; charset=utf-8");
						 response.getWriter().write("{success:'true',info:'"+message+"'}");
		
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
								    String proKey=String.valueOf(part_id);
								   latestEvaluationRecordExt.setclassMateRecordToCache(signer_name, two_part_id, proKey, columnName, evaluateName, eduId, date);
		} catch (Exception e) {
			try {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'false',info:'保存失败'}");
			} catch (IOException e1) {
				logger.error("doUpdataOtherProcess(HttpSession,HttpServletResponse)", e);
				throw new ManagerException(e);
			}
			logger.error("doInsertAppraisalChild(HttpServletResponse,HttpServletResponse)", e);
			throw new ManagerException(e);
		}
	}
	
	
	public void doUpdataOtherProcessNull(HttpSession session ,HttpServletResponse response) {
		try {
			String partid;
			if("0".equals(isStartAppraiseCache)){
				PartInfoXieDto  appr =new  PartInfoXieDto();
				appr.setStudentid(evaluatedPersonID);//被评价人id
				appr.setWrite_man("同学");
				appr.setTermid(termId.toString());
				appr.setPart_info(part_info);
				appr.setSigner_name(signer_name);
				appr.setTwo_part_id(two_part_id);
				appr.setCmis30id(cmis30idString);
				appr.setDiscode(discode.toString());
				appr.setUserid(apperEduId); 
				appr.setCreateDate(StringToDatexie(createDate));
				 partid=czplayAndHealthExt.czinsertSelfAppraisal(appr);
				String message="保存成功";
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'true',info:'"+message+"',partid:"+partid+"}");
			}else{
				    partid = baseInforManagerExt.queryProKey("partinfo");
				    PartInfoCacheDto cacheDto =new PartInfoCacheDto();
					cacheDto.setStudentid(evaluatedPersonID);
					cacheDto.setWrite_man("同学");
					cacheDto.setPart_info(part_info);
					 String st=String.valueOf(termId);
					cacheDto.setTermid(st);
					cacheDto.setSigner_name(signer_name);
					cacheDto.setTwo_part_id(two_part_id);
					cacheDto.setCmis30id(cmis30idString);
					cacheDto.setUserid(apperEduId);
					cacheDto.setDiscode(discode.toString());
					Date da=StringToDate(createDate); // 时间--界面 数据库参数
					cacheDto.setCreateDate(createDate);
					cacheDto.setPart_id(partid);
					czplayAndHealthExt.addCachApper(eduId,st,two_part_id,"同学",apperEduId,partid,cacheDto,da);
					String message="保存成功";
					 response.setContentType("text/html; charset=utf-8");
					response.getWriter().write("{success:'true',info:'"+message+"',partid:'"+partid+"'}");
					
			}
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
					    String proKey=String.valueOf(partid);
					   latestEvaluationRecordExt.setclassMateRecordToCache(signer_name, two_part_id, proKey, columnName, evaluateName, eduId, date);
			
			 
		} catch (Exception e) {
			try {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("{success:'false',info:'保存失败'}");
			} catch (IOException e1) {
				logger.error("doUpdataOtherProcess(HttpSession,HttpServletResponse)", e);
				throw new ManagerException(e);
			}
			throw new ManagerException(e);
		}
	}
	/**
	 * 时间转换
	 * @param session
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
	/**
	 * 时间转换
	 * @param session
	 * @return
	 */
	public Object doUpadateTime(HttpSession session) {
		try {
			    Date da=StringToDatexie(createDate);
			    if("0".equals(isStartAppraiseCache)){
			    czplayAndHealthExt.czupdataTime(part_id,da,discode,cmis30id);
			    }else{
					   String  termidString= String.valueOf(termId);
					   PartInfoCacheDto cacheDto =new PartInfoCacheDto();
					   cacheDto.setCreateDate(createDate);
					   czplayAndHealthExt.czupdataTimeCade(eduId,termidString,two_part_id,"同学", apperEduId, part_id,cacheDto,da,discode,cmis30id);
					   
				  }
               return null;
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
				  czplayAndHealthExt.deleApprasial(part_id,discode,cmis30id);
			  }else{
				   String  termidString= String.valueOf(termId);
				   czplayAndHealthExt.deleApprasialCade(eduId,termidString,two_part_id,"同学",apperEduId,part_id,discode,cmis30id);
			  }
				 PrintWriter pw = response.getWriter();
			     String message="删除成功";
			     response.setContentType("text/html; charset=utf-8");
				 response.getWriter().write("{success:'true',info:'"+message+"'}");
		         latestEvaluationRecordExt.deleteclassMateRecordInCache(eduId, two_part_id, part_id);
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
	/**
	 * 列表转换
	 * @param appriseInfos
	 * @return
	 */
	private List<StudentxieDto> initApprise(List<PartInfoXieDto> appriseInfos) {
		List<StudentxieDto> list = new ArrayList<StudentxieDto>();
		for(PartInfoXieDto dto:appriseInfos){
			//学生评价信息模型
			StudentxieDto studentDto = new StudentxieDto();
				//如果该list还没有数据 说明还没有填如数据  将查询到的数据放入该list中
				if(list.size()==0){
					Integer it = Integer.valueOf(dto.getStudentid());
					studentDto.setStudentid(it);
					studentDto.setName(dto.getPname());
					studentDto.setPhotoUrl(dto.getPhotoUrl());
					studentDto.getCzaInfos().add(dto);
					studentDto.setEduid(dto.getEduid());
					if(dto.getPart_info()==null){
						dto.setPart_id("0");
					}
					list.add(studentDto);
				}else{//如果 有数据了 遍历该list   将已有的数据和查询出来的数据进行比对  
							//如果已经存在了该条数据  直接添加评价信息
							//否则 将数据重新装入容器
					boolean isAdd = false;
					for(StudentxieDto fDto:list){
						Integer it = Integer.valueOf(dto.getStudentid());
						if(it.equals(fDto.getStudentid())){
							fDto.getCzaInfos().add(dto);  
							isAdd=true;
							break;
						}
					}
					if(!isAdd){
						Integer it = Integer.valueOf(dto.getStudentid());
						studentDto.setStudentid(it);
						studentDto.setName(dto.getPname());
						studentDto.setPhotoUrl(dto.getPhotoUrl());
						studentDto.setEduid(dto.getEduid());
						studentDto.getCzaInfos().add(dto);
						if(dto.getPart_info()==null){
							dto.setPart_id("0");  
						}
						list.add(studentDto);
					}
				}
		}
		return list;
	}
	
	/**
	 * 转换
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
		 termId= (Integer)session.getAttribute("termId");
		 String stid=termId.toString();
		 partInfoDtoList =czplayAndHealthExt.czfindreturn(signer_name,stid,evaluatedPersonID,cmis30id,discode,two_part_id);
		 if(partInfoDtoList.size()==0){
			 PartInfoXieDto partInfoXieDto =new PartInfoXieDto();
			 partInfoXieDto.setTwo_part_id(two_part_id);
			 partInfoXieDto.setStudentid(evaluatedPersonID);
			 partInfoXieDto.setPart_info(" ");
			 partInfoXieDto.setPart_id("0");
			 fakeId=baseInforManagerExt.queryProKey("partinfo");
			   partInfoXieDto.setFakeId(fakeId);
			 partInfoDtoList.add(partInfoXieDto);
		 }
		 JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			arraylist=JSONArray.fromObject(partInfoDtoList,jsonConfig);
			 response.setContentType("text/html; charset=utf-8");
			 response.getWriter().write("{success:'true',info:'"+information+"',content:"+arraylist+"}");
		
		} catch (IOException e) {
			throw new ManagerException(e);
		}
	}
	/**
	 * 处理添加无内容数据
	 * @param response
	 * @param session
	 */
	public void delectAddNull(HttpServletResponse response,HttpSession session)
	{
		try {
			Integer inte=Integer.valueOf(evaluatedPersonID);
			StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
			name=student1.getName();
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			termId= (Integer)session.getAttribute("termId");
			String stid=termId.toString();
			partInfoDtoList =czplayAndHealthExt.czfindreturn(signer_name,stid,evaluatedPersonID,cmis30id,discode,two_part_id);
			if(partInfoDtoList.size()==0){
				PartInfoXieDto partInfoXieDto =new PartInfoXieDto();
				partInfoXieDto.setTwo_part_id(two_part_id);
				partInfoXieDto.setStudentid(evaluatedPersonID);
				partInfoXieDto.setPart_info(" ");
				partInfoXieDto.setPart_id("0");
				fakeId=baseInforManagerExt.queryProKey("partinfo");
				partInfoXieDto.setFakeId(fakeId);
				partInfoDtoList.add(partInfoXieDto);
			}
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			arraylist=JSONArray.fromObject(partInfoDtoList,jsonConfig);
					response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(arraylist.toString());
		} catch (IOException e) {
			throw new ManagerException(e);
		}
	}
	
	class DateJsonValueProcessor implements JsonValueProcessor
	{
		public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
			return null;
	 }
        /*
         * (non-Javadoc)
         * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
         */
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
	 * 添加时获取伪id
	 * @param session
	 * @param response
	 */
	public void  gainFakeId(HttpSession session,HttpServletResponse response) {
		try {
			fakeId=baseInforManagerExt.queryProKey("partinfo");
		    response.getWriter().write(fakeId);  
            response.getWriter().flush(); 
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	
	
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
		/*List<SchoolTreeDto>stuIfos = this.getStudentInfos(classId,"",req);*/
		//把北京地区放在集合最前面
		return null;
	}
	
}
