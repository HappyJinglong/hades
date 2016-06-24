package com.flyrish.hades.webapp.action.student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class CzFistShow extends BaseAction{
	// 自我评价列表
	public List<PartInfoXieDto> partInfoDtoList;
	public List<StudentxieDto> listApraisal;
	//被评价人ID.
	public String evaluatedPersonID;
	public String name;
	//学期
	public String termId;
	// 被评价人id
	public String evaluateid;
	//被评价人名字
	public String evaluateName;
	//评价标识
	public Integer part_id=0;
	//班级id
	public Integer classid;
	// 评语
	public String part_info;
	// 评价人姓名
	public String signer_name;
	// 评价时间  
	public String CreateDate;
    //评价人类型
	public String write_man;
    //评价类型
	public String two_part_id="32";
	public StudentxieDto student;
	public List<StudentxieDto> list;
	public Integer studentid;
	public Integer discode;  
	public Integer cmis30id;
	public Integer levelCode;
	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
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
		try {
			student=czplayAndHealthExt.findstudent(studentid,discode,cmis30id);
			signer_name=student.getName();
			classid=student.getClassid();
			session.setAttribute("signer_name",student.getName());
			session.setAttribute("user",student);
			//此处的appraiserrid 是登录人的id（学生id）
			partInfoDtoList=czplayAndHealthExt.selectSelfAppraiseXie(studentid,student.getClassid(),student.getTermid(),student.getName(),two_part_id,discode,cmis30id);
			
			for( PartInfoXieDto Pd:partInfoDtoList){
		    	if(Pd.getPart_info()==null){
		    		Pd.setPart_id("0");
		    	}
		    }
			listApraisal =this.initApprise(partInfoDtoList);
			// 如果用户没有修改权限，并且记录集为空，则添加一个模板样式
				return "czotherappraise_list11.jsp";
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
					studentDto.getCzaInfos().add(dto);
					studentDto.setPhotoUrl(dto.getPhotoUrl());
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
						studentDto.getCzaInfos().add(dto);
						list.add(studentDto);
					}
				}
		}
		return list;
	}
	
	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	public String time=format.format(date); 
}
