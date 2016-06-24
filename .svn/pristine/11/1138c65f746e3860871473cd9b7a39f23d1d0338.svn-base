package com.flyrish.hades.webapp.action.delScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KstudySegmentDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.ViewDto;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.service.ext.IScoreExt;
import com.flyrish.hades.service.ext.IStudentAppDetailExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class DoWithScore extends BaseAction {
	@Spring
	private IMasterAppriseExt masterAppriseExt;
	
	@Spring
	private IScoreExt iScoreExt;
	
	@Spring
	private ISchoolCourseExt schoolCourseExt;
	
	public List<CampusDto> campus=new ArrayList<CampusDto>();  //班级
	
	public Map<String,String> grades = new HashMap<String, String>(); //年级
	
	public List<String>  xueYears = new ArrayList<String>();  //学年
	
	public List<KstudySegmentDto> segments = new ArrayList<KstudySegmentDto>();//学段
	
	public List<KsysSubjectDto> subjectDtos = new ArrayList<KsysSubjectDto>();//学科
	
	public List<KcourseDto> courseDtos = new ArrayList<KcourseDto>();//课程模块
	
	public List<ModelScoreDto> mss = new ArrayList<ModelScoreDto>();
	
	Map<String,Object> maps = new HashMap<String,Object>();
	
	public ViewDto vd = new ViewDto();  //学时  学分   任课教师
	
	public String userRealType="";
	
	public String cmis30id="";
	
	public String manyYears="";   
	
	public String schoolyear ="";  //学年
	
	public String classid = ""; //班级id
	
	public String segmentId = "";  //学段id
	
	public String courseType="";  //课程模块类型
	
	public String subjectId = "";//学科id
	
	public String courseId = "";//模块id 
	
	public String scoreJson=""; //成绩json字符串
	
	public String class_model_id="";
	
	public String segment_course_id="";
	
	public String schoolyearValue = "";  //学年值
	
	public String gradeName1 = "";   //年级名称 
	 
	public String className1 ="";   //班级名称
	
	public String segmentValue = "" ;//学段名称
	
	public String subjectValue = "" ;//学科名称
			
	public String 	courseValue = "" ;//课程名称	
	
	public String studentName =""; //学生名称
	
	public String className = "";   //班级名称或者班级标识号
	
	public String  eduId = "";   //教育id
	
	public Double xfCount=0.0;   //本学段总分
	
	public String subjectName;//subjectName
	
	public String gradeName;//年级id
	
	public String year;//学年
	
	public String edu_id;//学生教育标识号
	
	public List<ModelScoreDto> bXScore = new ArrayList<ModelScoreDto>();  //必修成绩
	
	public List<ModelScoreDto> xXScore = new ArrayList<ModelScoreDto>();   //选修成绩
	
	public List<ModelScoreDto> bX1Score = new ArrayList<ModelScoreDto>();   //必选
	
	
	public List<ModelScoreDto> xBScore = new ArrayList<ModelScoreDto>();  //校本成绩
	
	@Spring
	public IStudentAppDetailExt studentAppDetailExt;
	
	
	public String tableTitle2="";
	
	public String isToBake = "";
	
	public String credit_id="";
	@DefaultAction
	public Object studentQueryScoreFirst(HttpServletRequest req,HttpSession session){
		//获取登录用户信息
		init(req);
		return "scoreQuery.jsp";
	}
	/**
	 * 根据学年获取当前班的历史班级id
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	public String queryClassIdBySchoolyearAndClassid(String schoolyear,String classid){
		List<String> lists = iScoreExt.getClassIdBySchoolyearAndClassid(schoolyear,classid);
		if(null!=lists && lists.size()>0){
			return lists.get(0);
		}
		return null;
	}
	/**
	 * 学生查询成绩
	 * @return
	 */
	@Json
	public Object studentQueryScore1(HttpServletRequest req){
		//获取登录用户信息
		 UserDto user = getLoginInfo(req);
		 classid = queryClassIdBySchoolyearAndClassid(schoolyear,className);
		 String gradeid=null;
		 if(NestUtil.isNotEmpty(gradeName))
			 gradeid=gradeName.split("_")[0];
		 //查询出该班级所有的学生的教育id
		 List<String> eduids=studentAppDetailExt.queryStudentInfoByCondition1(className, gradeid, user.getCmis30id(), user.getDiscode(), edu_id, studentName);
		 if(eduids==null||eduids.size()<1){
			 eduids=new ArrayList<String>();
			 eduids.add("-1");
		 }
		 if("0".equals(courseType)){
			 mss = iScoreExt.studentGetNZScore(subjectName, segmentId, classid,eduids, user.getCmis30id(), user.getDiscode());
			 if(mss==null) mss=new ArrayList<ModelScoreDto>();
			 mss.addAll(iScoreExt.studentGetSchoolScore(subjectName, segmentId,classid, eduids, user.getCmis30id(), user.getDiscode()));
		 }
		 else if("1".equals(courseType)){
			 mss = iScoreExt.studentGetNZScore(subjectName, segmentId, classid,eduids, user.getCmis30id(), user.getDiscode());
		 }else if("2".equals(courseType)){
			 mss=iScoreExt.studentGetSchoolScore(subjectName, segmentId,classid, eduids, user.getCmis30id(), user.getDiscode());
		 }
		 maps.put("mss", mss);
		 return maps;
	}
	@Json
	public Object deleteScoreById(HttpServletRequest req){
		if(NestUtil.isEmpty(credit_id))return null;
		String[]ids=credit_id.split(",");
		List<String>nZids=new ArrayList<String>();
		List<String>xBids=new ArrayList<String>();
		for(String id:ids){
			if(NestUtil.isEmpty(id))continue;
			if(id.contains("N"))
				nZids.add(id.split("_")[0]);
			else if(id.contains("X"))
				xBids.add(id.split("_")[0]);
		}
		String flag="1";
		//删除课程成绩
		try{
			iScoreExt.delCourseById(nZids,xBids);
		}catch(Exception e){
			logger.error("deleteScoreById(HttpServletRequest)",e);
			flag="0";
		}
		maps.put("flag",flag);
		return maps;
	}
	//初始化数据
	private void  init(HttpServletRequest req){
		 campus = queryCampusDtos(req);//返回教师所管辖的班级和年级信息
		 grades = queryGrade(campus);//返回年级
		 
		 if(grades.size()>0){
			 xueYears = iScoreExt.getYears(manyYears);  //获取学年
		 }
		 UserDto user = getLoginInfo(req);
		 cmis30id = user.getCmis30id();
		 if(xueYears.size()>0){
			 Map<String, Object> params = new HashMap<String, Object>();
			 params.put("cmis30id",cmis30id);
			 params.put("schoolyear", xueYears.get(0).split("@")[0]);
			 segments = schoolCourseExt.getSegmentInfos(params);  //学段
		 }
	}
	
	private Map<String,String> queryGrade(List<CampusDto> campus){
		Map<String,String> campus1 = new LinkedHashMap<String, String>();
		for(int i=0;i<campus.size();i++){
			CampusDto cmd = campus.get(i);
			if(i==0){
				manyYears = cmd.getGradeNum();
			}
			
			if("1".equals(cmd.getGradeNum())){
				cmd.setGradeName("高一年级");
			}else if("2".equals(cmd.getGradeNum())){
				cmd.setGradeName("高二年级");
			}else if("3".equals(cmd.getGradeNum())){
				cmd.setGradeName("高三年级");
			}
			campus1.put(cmd.getGradeId()+"_"+cmd.getGradeNum(), cmd.getGradeName());
		}
		
		return campus1;
	}
	//返回教师所管辖的班级信息
	private List<CampusDto> queryCampusDtos(HttpServletRequest req){
		//获取登录用户信息
		UserDto user = getLoginInfo(req);
		Map<String,Object>params = new HashMap<String, Object>();
		//查询参数
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("techerid", userDto.getTeacherid());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		List<CampusDto> campus = new ArrayList<CampusDto>();
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
				&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
			campus = iScoreExt.getJWLSClass(userDto.getLevelcode(), userDto.getCmis30id(),userDto.getCampuseId());
		}
		return campus;
	}
}
