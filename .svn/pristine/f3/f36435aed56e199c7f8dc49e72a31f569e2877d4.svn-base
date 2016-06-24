package com.flyrish.hades.webapp.action.student;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.SpeekDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;
/**
 * 变更前页面   已废
 * @author dell
 *
 */
public class PlayAndHealthAction extends BaseAction{
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;
	public Integer discode;  
	public Integer cmis30id;
	public Integer level;
	public String json;
	public String levelCode;
	/**
	 * 自我评价列表
	 */
	public List<AppraisalxieDto> appraisalList;


	/**
	 * 被评价人ID.
	 */
	public String evaluatedPersonID;
	
	public String name;
	public JSONArray arraylist;
	/**
	 * request提交ID.
	 */
	/**
	 * 同班学生显示信息
	 */
	public String stdId;
	public String sex;
	public String studentNo;
	public String classname;
	public String rpID;
	public Integer purview;
	public Integer personStatus;
	// 年级学期
	public Integer termId;
	//更改后的学期
	public Integer termId1;
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
	public Integer apprasialid;
	//班级id
	public Integer classid;
	// 评语
	public String apprasial;
	// 评价人
	public String appraser;
	//转换后的评论id
	public Integer fakeId;
	// 评价的id
	public String appraiserrid;
	public List<StudentxieDto> listApraisal;
	// 评价时间  evaluateid
	public String signDate;
    //类型
	public String evaluateType="3020";
	public String evaluateTypename;
	public String time;
	public String apperEduId;//评价人的id
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
		  //levelCode=Integer.valueOf(levelCodeString);
		  String studentidString=userDto.getPersonid(); 
		  studentid=Integer.valueOf(studentidString);
		  String discodeString=userDto.getDiscode();
		  discode=Integer.valueOf(discodeString);
		  String cmis30idString=userDto.getCmis30id();
		  cmis30id=Integer.valueOf(cmis30idString);
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		  time=format.format(date); 
		  apperEduId=userDto.getEduId(); 
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
		return null;
	}

	@DefaultAction
	public Object defaultAction(HttpSession session){
		try {
			student=playAndHealthExt.findstudent(studentid,discode,cmis30id);
			appraser=student.getName();
			classid=student.getClassid();
			termIdString =playAndHealthExt.findStundentTermId(classid);
			session.setAttribute("appraser",student.getName());
			session.setAttribute("user",student);
			 termId=Integer.valueOf(termIdString);
			session.setAttribute("termId",termId);
		    appraisalList = playAndHealthExt.selectSelfAppraiseXie(classid,evaluateType,termIdString,apperEduId,discode,cmis30id);
		     listApraisal =this.initApprise(appraisalList);	
		     session.setAttribute("studentInfo", listApraisal);
		    return "raise_list11.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	public Object chaneTermId(HttpSession session){
		try {
			student=playAndHealthExt.findstudent(studentid,discode,cmis30id);
			appraser=student.getName();
			classid=student.getClassid();
			termIdString=termId1.toString();
			session.setAttribute("termId",termId1);
			//此处的appraiserrid 是登录人的id（学生id）
			appraisalList = playAndHealthExt.selectSelfAppraiseXie(classid,evaluateType,termIdString,apperEduId,discode,cmis30id);
			// 如果用户没有修改权限，并且记录集为空，则添加一个模板样式
			listApraisal =this.initApprise(appraisalList);	
		    return "raise_list11.jsp";
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		
	}
	
	
	
	/**
	 * 保存他人修改评价
	 * 
	 * @return
	 */
	
	public void doUpdataOtherProcess(HttpSession session,HttpServletResponse response) {
		try {
			 termId=(Integer) session.getAttribute("termId");
			 termIdString=termId.toString();
			 AppraisalxieDto appraisal = new AppraisalxieDto();
			 appraisal.setApprasialid(apprasialid);
			 appraisal.setApprasial(apprasial);// 评价内容
			 Date da=StringToDate(signDate);
		     appraisal.setSignDate(da);// 时间--界面
			 playAndHealthExt.updataAppraisal(appraisal);
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
	      speekDto=playAndHealthExt.findSpeek(apprasialid,discode,cmis30id);
	      if(speekDto!=null){
			    String username =speekDto.getUsername();
			    String stuEduid =speekDto.getStuEduid();
			    String  studentName=speekDto.getStudentName();        
			   if(studentName!=null && stuEduid!=null && studentName!=null){
				   String columnName=evaluateTypename;
				    Date date=new Date();
				    String proKey=String.valueOf(apprasialid);
				   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
			   }
        }
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	//保存新的评价
	public void doUpdataOtherProcessNll(HttpSession session,HttpServletResponse response) {
		try {
			    termId=(Integer) session.getAttribute("termId");
			    termIdString=termId.toString();
				AppraisalxieDto appraisal = new AppraisalxieDto();
				appraisal.setStudentid(new Integer(id));//被评价人id
				appraisal.setAppraseridentify(2);//插入他人评价变换角色		
				appraisal.setAppraisaltypeid(Integer.valueOf(evaluateType));// 评价类别
				appraser=(String) session.getAttribute("appraser");
				appraisal.setAppraser(appraser);//评价人
				
				//StudentxieDto student1=(StudentxieDto) session.getAttribute("user");
				appraisal.setAppraiserrid(Integer.valueOf(apperEduId));//评价人的标识
				
				appraisal.setApprasial(apprasial);// 评价内容
				 Date da=StringToDate(signDate);
				appraisal.setSignDate(da);// 时间--界面
				Integer apprasialid=playAndHealthExt.insertSelfAppraisal(appraisal,termIdString,discode,cmis30id);
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
		      speekDto=playAndHealthExt.findSpeek(apprasialid,discode,cmis30id);
		      if(speekDto!=null){
				    String username =speekDto.getUsername();
				    String stuEduid =speekDto.getStuEduid();
				    String  studentName=speekDto.getStudentName();        
				   if(studentName!=null && stuEduid!=null && studentName!=null){
					   String columnName=evaluateTypename;
					    Date date=new Date();
					    String proKey=String.valueOf(apprasialid);
					   latestEvaluationRecordExt.setclassMateRecordToCache(username, evaluateType, proKey, columnName, studentName, stuEduid, date);
				   }
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
			  PrintWriter pw = response.getWriter();
			  playAndHealthExt.deleApprasial(apprasialid);
			  response.setContentType("text/html; charset=utf-8");
				 response.getWriter().write("{success:'true'}");
					
				 /**
					 * 通过栏目号及用户标识号删除同学对自己的评价记录
					 * @param stuEduid 学生教育id
					 * @param columNum 栏目号
					 * @param proKey 评价记录的主键标识号
					 */
					
					 speekDto=playAndHealthExt.findSpeek(apprasialid,discode,cmis30id);
				      if(speekDto!=null){
						    String stuEduid =speekDto.getStuEduid();
						   if(stuEduid!=null){
							    String proKey=String.valueOf(apprasialid);
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
	
	private List<StudentxieDto> initApprise(List<AppraisalxieDto> appriseInfos) {
		
		List<StudentxieDto> list = new ArrayList<StudentxieDto>();
		for(AppraisalxieDto dto:appriseInfos){
			//学生评价信息模型
			StudentxieDto studentDto = new StudentxieDto();
				//如果该list还没有数据 说明还没有填如数据  将查询到的数据放入该list中
				if(list.size()==0){
					studentDto.setStudentid(dto.getStudentid());
					studentDto.setName(dto.getEvaluateName());
					studentDto.setPhotoUrl(dto.getPhotoUrl());
					studentDto.setEduid(dto.getEduid());
					studentDto.getaInfos().add(dto);
					list.add(studentDto);
				}else{//如果 有数据了 遍历该list   将已有的数据和查询出来的数据进行比对  
							//如果已经存在了该条数据  直接添加评价信息
							//否则 将数据重新装入容器
					boolean isAdd = false;
					for(StudentxieDto fDto:list){
						if(dto.getStudentid().equals(fDto.getStudentid())){
							fDto.getaInfos().add(dto);
//							finishApp.add(fDto);
							isAdd=true;
							break;
						}
					}
					if(!isAdd){
						studentDto.setStudentid(dto.getStudentid());
						studentDto.setName(dto.getEvaluateName());
						studentDto.setPhotoUrl(dto.getPhotoUrl());
						studentDto.setEduid(dto.getEduid());
						studentDto.getaInfos().add(dto);
						list.add(studentDto);
					}
				}
		}
		return list;
	}
	
	
	
	
	
	public void ListToJson(HttpServletResponse response,HttpSession session,String information)
	{   //termId=(String) session.getAttribute("termId");
		termId=(Integer) session.getAttribute("termId");
		 termIdString=termId.toString();
		try {
			Integer nID=new Integer(id);
			student=playAndHealthExt.findstudent(nID,discode,cmis30id);
			//termId=playAndHealthExt.findStundentTermId(classid);
			appraisalList = playAndHealthExt.findGzReturnTree(evaluateType,termIdString,studentid,nID,cmis30id,discode);
		   if(appraisalList.size()==0){
			   AppraisalxieDto appraisalxieDto= new AppraisalxieDto();
			   appraisalxieDto.setStudentid(nID);
			   appraisalxieDto.setAppraisaltypeid(new Integer(evaluateType));
			   appraisalxieDto.setApprasial(" ");
			   String fakeApprasialid=baseInforManagerExt.queryProKey("a_apprasial");
			   fakeId=Integer.valueOf(fakeApprasialid);
			   appraisalxieDto.setFakeId(fakeId);
			   appraisalList.add(appraisalxieDto);
		   }
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			arraylist=JSONArray.fromObject(appraisalList,jsonConfig);
			response.setContentType("text/html; charset=utf-8");
			//response.getWriter().write(arraylist.toString());
			 response.getWriter().write("{success:'true',info:'"+information+"',content:"+arraylist+"}");
				
		
		} catch (IOException e) {
			throw new ManagerException(e);
		}
	}
	public void delectAddNull(HttpServletResponse response,HttpSession session)
	{   //termId=(String) session.getAttribute("termId");
		termId=(Integer) session.getAttribute("termId");
		termIdString=termId.toString();
		try {
			Integer nID=new Integer(id);
			student=playAndHealthExt.findstudent(nID,discode,cmis30id);
			//termId=playAndHealthExt.findStundentTermId(classid);
			appraisalList = playAndHealthExt.findGzReturnTree(evaluateType,termIdString,studentid,nID,cmis30id,discode);
			if(appraisalList.size()==0){
				AppraisalxieDto appraisalxieDto= new AppraisalxieDto();
				appraisalxieDto.setStudentid(nID);
				appraisalxieDto.setAppraisaltypeid(new Integer(evaluateType));
				appraisalxieDto.setApprasial(" ");
				String fakeApprasialid=baseInforManagerExt.queryProKey("a_apprasial");
				fakeId=Integer.valueOf(fakeApprasialid);
				appraisalxieDto.setFakeId(fakeId);
				appraisalList.add(appraisalxieDto);
			}
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			arraylist=JSONArray.fromObject(appraisalList,jsonConfig);
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
			String fakeApprasialid=baseInforManagerExt.queryProKey("a_apprasial");
		    fakeId=Integer.valueOf(fakeApprasialid);
		    String fakeIdString =String.valueOf(fakeId);
		    response.getWriter().write(fakeIdString);  
            response.getWriter().flush(); 
		} catch (Exception e) {
			throw new ManagerException(e);
		}
	}
	
	
	
}
