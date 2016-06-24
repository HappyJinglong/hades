package com.flyrish.hades.webapp.action.student;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;
/**
 * 变更前的文件  已废
 * @author dell
 *
 */
public class GzFistShowAction extends BaseAction{
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;
	public Integer discode;  
	public Integer cmis30id;
	public String json;
	public Integer levelCode;
	//评价列表
	public List<AppraisalxieDto> appraisalList;
	//被评价人ID
	public String evaluatedPersonID;
	public String name;
	public JSONArray arraylist;
	public String stdId;
	public String sex;
	public String studentNo;
	public String classname;
	public String rpID;
	public Integer purview;
	public Integer personStatus;
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
	public Integer apprasialid=0;
	//班级id
	public Integer classid;
	public Integer termId;
	// 评语
	public String apprasial;
	// 评价人
	public String appraser;
	// 评价的id
	public String appraiserrid;
	public List<StudentxieDto> listApraisal;
	// 评价时间  evaluateid
	public String signDate;
    //类型
	public String evaluateType="3020";
	@Spring
	public IPlayAndHealthExt playAndHealthExt;
	
	
	@Before
	public Object befo(HttpSession session){
		try {
			levelCode=(Integer)session.getAttribute("levelCode");
			studentid=(Integer)session.getAttribute("studentid");
			 discode=(Integer)session.getAttribute(Constant.DISCODE);
			 cmis30id=(Integer)session.getAttribute(Constant.CMIS30);
		  if(studentid==null||discode==null){
			   return "/longin.jsp";
		   }else{
			   session.setAttribute("levelCode",levelCode);
			   session.setAttribute("studentid",studentid );
			   session.setAttribute("discode",discode );
			   return null;
		   }
		
		} catch (Exception e) {
			throw new ManagerException(e);
			
		}
	}
	@DefaultAction
	public Object defaultAction(HttpSession session){
		return null;
		
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
						studentDto.getaInfos().add(dto);
						list.add(studentDto);
					}
				}
		}
		return list;
	}
	
	
	
	
	
	public void ListToJson(HttpServletResponse response)
	{
		try {
			Integer nID=new Integer(id);
			student=playAndHealthExt.findstudent(nID,discode,cmis30id);
			termIdString=playAndHealthExt.findStundentTermId(classid);
			appraisalList = playAndHealthExt.findGzReturnTree(evaluateType,termIdString,studentid,nID,cmis30id,discode);
		   if(appraisalList.size()==0){
			   AppraisalxieDto appraisalxieDto= new AppraisalxieDto();
			   appraisalxieDto.setStudentid(nID);
			   appraisalxieDto.setAppraisaltypeid(new Integer(evaluateType));
			   appraisalxieDto.setApprasial(" ");
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
	
	
	
	
	
	
}
