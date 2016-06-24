package com.flyrish.hades.webapp.action.xspjtjmxb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.StudentAppraiseInfoNumDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class StudentAppDetailAction extends BaseAction{
	//届别集合<年级标识号_届别名称>
	public List<String> gradYears=new ArrayList<String>(0);
	//班级集合<班级标识号_班级名称>
	public List<String> classs=new ArrayList<String>(0);
	
	public List<StudentAppraiseInfoNumDto> data;
	//学期集合<学期标识号_学期名称>
	public List<TermDto> terms;
	@Spring
	public IAppraisalStaticsExt appraisalStaticsExt;
	@Spring
	public ITermServiceExt termServiceExt;
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	
	public String gradeid;
	
	public String gradeName;
	
	public String classid;
	
	public String className;
	
	public String studentsNum;
	
	public String termid;
	@DefaultAction
	public Object defaultAction(HttpServletRequest req){
		String levelCode = queryBaseData(req);
		return queryWhichPage(levelCode);
	}
	
	public Object queryStatistData(HttpServletRequest req){
		String levelCode = queryBaseData(req);
		//获取相应的数据集合
		Map<String,Object> datas=studentAppDetailExt.queryStudentAppraiseInfoNumDtoByInfo(gradeid,classid,termid,userDto.getCmis30id(),userDto.getDiscode());
		if(datas!=null&&!datas.isEmpty()){
			studentsNum=datas.get("totalPersonsNum")+"";
			data=(List<StudentAppraiseInfoNumDto>)datas.get("data");
		}
		return queryWhichPage(levelCode);
	}
	private Object queryWhichPage(String levelCode) {
		//高中
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			return "highschoolindex.jsp";
		}else{
			//初中
			return "juniorschoolindex.jsp";
		}
	}
	private String queryBaseData(HttpServletRequest req) {
		getLoginInfo(req);
		//学段代码
		String levelCode=userDto.getLevelcode();
		//用户真实的类型
		String userRealType=userDto.getUserRealType();
		
		String teacherid=null;
		
		if(Constant.USER_TYPE_SCHOOLADMIN.equals(userRealType)){
			//教务老师
			teacherid=null;
		}else if(Constant.USER_TYPE_CLASSMASTER.equals(userRealType)){
			//班主任
			teacherid=userDto.getTeacherid();
		}
		//查询学年
		gradYears=appraisalStaticsExt.queryJB(userDto.getLevelid(),levelCode, userDto.getCampuseId(),teacherid,"1");
		if(gradYears!=null&&!gradYears.isEmpty()){
			if(NestUtil.isEmpty(gradeid)){
				gradeid=gradYears.get(0).split("_")[0];
			}else{
				for(String gradYear:gradYears){
					String gradeidcopy=gradYear.split("_")[0];
					if(gradeid.equals(gradeidcopy)){
						gradeName=gradYear.split("_")[1];
						break;
					}
				}
			}
			//查询班级
			classs=appraisalStaticsExt.queryClass(gradeid, teacherid);
			if(classs!=null&&!classs.isEmpty()){
				if(NestUtil.isEmpty(classid)){
					classid=classs.get(0).split("_")[0];
				}else{
					for(String classsigle:classs){
						String classcopy=classsigle.split("_")[0];
						if(classid.equals(classcopy)){
							className=classsigle.split("_")[2];
							break;
						}
					}
				}
				terms = termServiceExt.queryHighSchoolTerms(classid,levelCode);
				if(terms!=null&&terms.size()>0)
					Collections.reverse(terms);
			}
		}
		return levelCode;
	}
}
