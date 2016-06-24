package com.flyrish.hades.webapp.action.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;
/**
 * 变更前页面  以废
 */
public class CzPlayAndHealthAction extends BaseAction{
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;
	public Integer discode;  
	public Integer cmis30id;
	public JSONArray arraylist;
	public String levelCode;
	public String apperEduId;
	/**
	 * 自我评价列表
	 */
	public List<PartInfoXieDto> partInfoDtoList;
	public List<PartInfoXieDto> appraisalList;
	public List<StudentxieDto> listApraisal;
	/**
	 * 被评价人ID.
	 */
	public String evaluatedPersonID;
	
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
	     return null;
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
	
	
	@DefaultAction
	public Object defaultAction(HttpSession session){
		try {
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			if(null!=student){
				signer_name=student.getName();
				classid=student.getClassid();
				termId=student.getTermid();
				session.setAttribute("termId",termId);
				partInfoDtoList=czplayAndHealthExt.selectSelfAppraiseXie(studentid,classid,termId,apperEduId,two_part_id,discode,cmis30id);
				listApraisal =this.initApprise(partInfoDtoList);
				session.setAttribute("studentInfo", listApraisal);
				// 如果用户没有修改权限，并且记录集为空，则添加一个模板样式
				return "czotherappraise_list11.jsp";
			}else{
				return "/login.jsp";
			}
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	public Object chaneTermId(HttpSession session){
		try {
			
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			classid=student.getClassid();
			session.setAttribute("termId",termId1);
			
			
			partInfoDtoList=czplayAndHealthExt.selectSelfAppraiseXie(studentid,classid,termId1,apperEduId,two_part_id,discode,cmis30id);
		
		
			
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
	/*@Json */
	public void doUpdataOtherProcess(HttpSession session ,HttpServletResponse response) {
		try {
				 PartInfoXieDto  appr1 =new  PartInfoXieDto();
						appr1.setPart_id(part_id);
						appr1.setPart_info(part_info);// 评价内容
						appr1.setCreateDate(StringToDate(time));// 时间--界面
						czplayAndHealthExt.czupdataAppraisal(appr1);
						String message="修改成功";
						//ListToJson(response,session,message);
						response.setContentType("text/html; charset=utf-8");
						 response.getWriter().write("{success:'true',info:'"+message+"'}");
						
						  speekDto=czplayAndHealthExt.czfindSpeek(part_id,discode,cmis30id);
						  if(speekDto!=null){
							    String username =speekDto.getUsername();
							    String stuEduid =speekDto.getStuEduid();
							    String  studentName=speekDto.getStudentName(); 
							    String evaluateType=speekDto.getAppraisaltypeid();
							   if(evaluateType!=null&& studentName!=null && stuEduid!=null && studentName!=null){
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
				        }
						 
						 
						 
		
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
			
			PartInfoXieDto  appr =new  PartInfoXieDto();
			appr.setStudentid(evaluatedPersonID);//被评价人id
			appr.setWrite_man("同学");
			Integer ita=Integer.valueOf(evaluatedPersonID);
			StudentxieDto student1=czplayAndHealthExt.findstudent(ita,discode,cmis30id);
			//String  ter=student1.getTermid().toString();
			//appr.setTermid(ter);
			termId= (Integer)session.getAttribute("termId");
			appr.setTermid(termId.toString());
			appr.setPart_info(part_info);
			StudentxieDto student2=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			appr.setSigner_name(student2.getName());
			appr.setTwo_part_id(two_part_id);
			appr.setCmis30id(cmis30idString);
			appr.setDiscode(discode.toString());
			Date da=StringToDate(createDate);
			/*Date dtime=StringToDate(time);*/
			appr.setUserid(apperEduId); 
			appr.setCreateDate(da);
			
			String partid=czplayAndHealthExt.czinsertSelfAppraisal(appr);
			String message="保存成功";
			
			response.setContentType("text/html; charset=utf-8");
			 response.getWriter().write("{success:'true',info:'"+message+"',partid:"+partid+"}");
			
			 
			 
			  speekDto=czplayAndHealthExt.czfindSpeek(partid,discode,cmis30id);
			  if(speekDto!=null){
				    String username =speekDto.getUsername();
				    String stuEduid =speekDto.getStuEduid();
				    String  studentName=speekDto.getStudentName(); 
				    String evaluateType=speekDto.getAppraisaltypeid();
				   if(evaluateType!=null&& studentName!=null && stuEduid!=null && studentName!=null){
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
					    String proKey=String.valueOf(partid);
					   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
				   }
	        }
			 
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

	
	
	
	
	public Object doUpadateTime(HttpSession session) {
		try {
			    Date da=StringToDate(createDate);
			    czplayAndHealthExt.czupdataTime(part_id,da,discode,cmis30id);
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
			  PrintWriter pw = response.getWriter();
			  czplayAndHealthExt.deleApprasial(part_id,discode,cmis30id);
			  String message="删除成功";
			     response.setContentType("text/html; charset=utf-8");
				 response.getWriter().write("{success:'true',info:'"+message+"'}");
				 speekDto=czplayAndHealthExt.czfindSpeek(part_id,discode,cmis30id);
				  if(speekDto!=null){
					    String stuEduid =speekDto.getStuEduid();
					    String evaluateType=speekDto.getAppraisaltypeid();
					   if( stuEduid!=null){
						    String proKey=String.valueOf(part_id);
						    latestEvaluationRecordExt.deleteclassMateRecordInCache(stuEduid, evaluateType, proKey);
					   }
		        }
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
						/*if(dto.getStudentid().equals(fDto.getStudentid())){*/
							fDto.getCzaInfos().add(dto);  
//							finishApp.add(fDto);
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
	
	
	public void ListToJson(HttpServletResponse response,HttpSession session,String information)
	{
		try {
		Integer inte=Integer.valueOf(evaluatedPersonID);
		StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
		name=student1.getName();
		student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
		signer_name=student.getName();
		 //Integer ter=student.getTermid();
		 //String termid=ter.toString();
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
	/*		response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(arraylist.toString());*/
		
			 response.setContentType("text/html; charset=utf-8");
			 response.getWriter().write("{success:'true',info:'"+information+"',content:"+arraylist+"}");
			
		
		
		} catch (IOException e) {
			throw new ManagerException(e);
		}
	}
	//处理添加无内容数据
	public void delectAddNull(HttpServletResponse response,HttpSession session)
	{
		try {
			Integer inte=Integer.valueOf(evaluatedPersonID);
			StudentxieDto student1=czplayAndHealthExt.findstudent(inte,discode,cmis30id);
			name=student1.getName();
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			//Integer ter=student.getTermid();
			//String termid=ter.toString();
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
	
	
	//添加时获取伪id
	public void  gainFakeId(HttpSession session,HttpServletResponse response) {
		try {
			fakeId=baseInforManagerExt.queryProKey("partinfo");
		    response.getWriter().write(fakeId);  
            response.getWriter().flush(); 
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	
}
