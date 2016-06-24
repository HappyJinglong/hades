package com.flyrish.hades.webapp.action.score;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.flyrish.hades.webapp.action.BaseAction;

public class ScoreAction extends BaseAction{

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
	
	public String className = "";   //班级名称
	
	public String  eduId = "";   //教育id
	
	public Double xfCount=0.0;   //本学段总分
	
	public List<ModelScoreDto> bXScore = new ArrayList<ModelScoreDto>();  //必修成绩
	
	public List<ModelScoreDto> xXScore = new ArrayList<ModelScoreDto>();   //选修成绩
	
	public List<ModelScoreDto> bX1Score = new ArrayList<ModelScoreDto>();   //必选
	
	
	public List<ModelScoreDto> xBScore = new ArrayList<ModelScoreDto>();  //校本成绩
	
	
	public String tableTitle2="";
	
	public String isToBake = "";
	
	@DefaultAction
	public Object getScoreInput(HttpServletRequest req,HttpSession session){
		
		if("1".equals(isToBake)){
			try {
				tableTitle2= java.net.URLDecoder.decode(tableTitle2 , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		init(req);
		
		return "scoreInput.jsp";
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
			 if(null!=segments && segments.size()>0){
				 	if(null!=campus && campus.size()>0){
				 		if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype())  //班主任
								&&Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){
							 subjectDtos =  iScoreExt.getSubjectBySegmentId(cmis30id, segments.get(0).getSegment_id(),Constant.KG_COURSE_NEIZHI,"",campus.get(0).getClassId()); //学科
						}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //任课老师
								&&Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){
							 subjectDtos =  iScoreExt.getSubjectBySegmentId(cmis30id, segments.get(0).getSegment_id(),Constant.KG_COURSE_NEIZHI,user.getTeacherid(),campus.get(0).getClassId()); //学科
						}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
								&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
							 subjectDtos =  iScoreExt.getSubjectBySegmentId(cmis30id, segments.get(0).getSegment_id(),Constant.KG_COURSE_NEIZHI,"",campus.get(0).getClassId()); //学科
						}
				 	}
				 
				 if(null!= subjectDtos && subjectDtos.size()>0){
					 if(null!=campus && campus.size()>0){
						 if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype())  //班主任
									&&Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){
							 	courseDtos = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, Constant.KG_COURSE_NEIZHI, subjectDtos.get(0).getSubject_id(), segments.get(0).getSegment_id(),"",campus.get(0).getClassId());
							 	List<KcourseDto> kcd2=new ArrayList<KcourseDto>();
							 	kcd2=iScoreExt.getCourseBySubjectIdAndCourseTypeByStudents(cmis30id,user.getDiscode(),subjectDtos.get(0).getSubject_id(),segments.get(0).getSegment_id(),campus.get(0).getClassId());
							 	courseDtos=getLastCourse(courseDtos,kcd2);
							}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //任课老师
									&&Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){
								courseDtos = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, Constant.KG_COURSE_NEIZHI, subjectDtos.get(0).getSubject_id(), segments.get(0).getSegment_id(),user.getTeacherid(),campus.get(0).getClassId());
							}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
									&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
								 courseDtos = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, Constant.KG_COURSE_NEIZHI, subjectDtos.get(0).getSubject_id(), segments.get(0).getSegment_id(),"",campus.get(0).getClassId());
								 List<KcourseDto> kcd2=new ArrayList<KcourseDto>();
								 kcd2=iScoreExt.getCourseBySubjectIdAndCourseTypeByStudents(cmis30id,user.getDiscode(),subjectDtos.get(0).getSubject_id(),segments.get(0).getSegment_id(),campus.get(0).getClassId());
								 courseDtos=getLastCourse(courseDtos,kcd2);
							}
					 }
				 
				 }
			 }
		 }
		 
		 
		 userRealType = getLoginInfo(req).getUserRealType(); //获取角色
		 if(Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){//如果是班主任
			 courseTypes.add(Constant.KG_COURSE_NEIZHI +"@内置课程");
		 }else if(Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){//任课老师
			 Map<String,Object>params = new HashMap<String, Object>();
			 params.put("unitId", userDto.getUnitid());
			 params.put("userId", userDto.getUserid());
			 List<String> roleTypes = iScoreExt.queryUserRoleType(params);
			 if(null!=roleTypes && roleTypes.size()>0){
				 if(roleTypes.contains(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE)&&roleTypes.contains(Constant.USER_TYPE_COURSEMASTER)){
					 courseTypes.add(Constant.KG_COURSE_NEIZHI +"@内置课程");
					 courseTypes.add(Constant.KG_COURSE_KIND  +"@校本课程");
				 }else if(roleTypes.contains(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE)){
					 courseTypes.add(Constant.KG_COURSE_KIND  +"@校本课程");
				 }else if(roleTypes.contains(Constant.USER_TYPE_COURSEMASTER)){
					 courseTypes.add(Constant.KG_COURSE_NEIZHI +"@内置课程");
				 }
			 }
		 }else if(Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){//教务老师
			 courseTypes.add(Constant.KG_COURSE_NEIZHI +"@内置课程");
			 courseTypes.add(Constant.KG_COURSE_KIND  +"@校本课程");
		 }else if(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE.equals(user.getUserRealType())){//校本老师
			 courseTypes.add(Constant.KG_COURSE_KIND  +"@校本课程");
		 }

	}
	public List<String> courseTypes = new ArrayList<String>();
	
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
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype())  //班主任
				&&Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){
			campus=masterAppriseExt.getClassInfos(params);
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //任课老师
				&&Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){
			campus=masterAppriseExt.getTeacherClassInfos(params);
			
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
				&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
			campus = iScoreExt.getJWLSClass(userDto.getLevelcode(), userDto.getCmis30id(),userDto.getCampuseId());
		}
		return campus;
	}
	
	//获取班级
	@Json
	public Object queryClass(HttpServletRequest req){
		return queryCampusDtos(req);
	}
	
	
	//获取学年
	@Json
	public Object queryXueYear(){
		
		
		return iScoreExt.getYears(manyYears);
	}
	
	//获取学段
	@Json
	public Object querySegmentId(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		UserDto user = getLoginInfo(req);
		params.put("cmis30id", user.getCmis30id() );
		params.put("schoolyear", schoolyear);
		return schoolCourseExt.getSegmentInfos(params);
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
	 * 任课老师  教务老师  班主任查询学生成绩 
	 * @param req
	 * @return
	 */
	public List<ModelScoreDto> teacherQueryModelScore(HttpServletRequest req){
		
		UserDto user = getLoginInfo(req);
		if(Constant.KG_COURSE_KIND.equals(courseType)){  //校本课程
			List<ViewDto> vds = iScoreExt.getSchoolXFAndXSAndTeacherName(segmentId, user.getDiscode(), courseId, subjectId, user.getCmis30id(), courseType,user.getTeacherid(),user.getUsertype(),user.getUserRealType());
			if(null!=vds && vds.size()>0){
				vd = vds.get(0);
				mss = iScoreExt.getSchoolScore(vd.getSegment_course_id(), vd.getXss(),user.getDiscode(),user.getCmis30id(),subjectId);
				maps.put("class_model_id", vd.getSegment_course_id());
			}
		}else if(Constant.KG_COURSE_NEIZHI.equals(courseType)){  //内置课程
			//String preclassid= queryClassIdBySchoolyearAndClassid(schoolyear,classid);
			String historyclassid = queryClassIdBySchoolyearAndClassid(schoolyear,classid);
			String historyschoolyear=null;
			if(NestUtil.isEmpty(historyclassid)){
				historyschoolyear=schoolyear;
				historyclassid=classid;
			}
			if(null==classid){
				maps.put("modelScores", mss);
				return mss;
			}
			String flag;
			try{
				flag=courseId.split("_")[1];
			}catch(Exception e){
				flag="1";
			}
			if("1".equals(flag)){
				List<ViewDto> vds = iScoreExt.getNZXFAndXSAndTeacherName(segmentId, historyclassid, user.getDiscode(), courseId, subjectId, user.getCmis30id(),user.getTeacherid(),user.getUsertype(),user.getUserRealType(),historyschoolyear);
				if(null!=vds && vds.size()>0){
					vd = vds.get(0);
					mss = iScoreExt.getNZScore(classid,historyclassid,vd.getClass_model_id(),vd.getXss(),user.getDiscode(),user.getCmis30id(),subjectId,historyschoolyear);
					maps.put("class_model_id", vd.getClass_model_id());
				}
			}else{
				List<ViewDto> vds = iScoreExt.getNZXFAndXSAndTeacherNameByStudents(segmentId, classid, user.getDiscode(), courseId, subjectId, user.getCmis30id());
				List<String> updateScoreClassModels=new ArrayList<String>();
				if(null!=vds && vds.size()>0){
					for(ViewDto dto:vds){
						if("0".equals(dto.getSpecail())){
							vd=dto;
							break;
						}
					}
					if(vd==null)
						vd=vds.get(0);
					if(vds.size()>1){
						for(ViewDto dto:vds){
							if(NestUtil.isNotEmpty(vd.getClass_model_id())&&!vd.getClass_model_id().equals(dto.getClass_model_id())){
								updateScoreClassModels.add(dto.getClass_model_id());
							}
						}
					}
					iScoreExt.updateStudentScoreClassModel(updateScoreClassModels,vd.getClass_model_id());
					mss = iScoreExt.getNZScore2(classid,vds,user.getDiscode(),user.getCmis30id(),vd.getXss());
					maps.put("class_model_id", vd.getClass_model_id());
				}
			}
		}
		return mss;
	}
	/**
	 * 根据学段获取学科
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	@Json
	public Object querySubjectBySegmentId(HttpServletRequest req){
		UserDto user = getLoginInfo(req);
		cmis30id = user.getCmis30id();
		String historyclassid = queryClassIdBySchoolyearAndClassid(schoolyear,classid);
		String historyschoolyear=null;
		if(NestUtil.isEmpty(historyclassid)){
			historyschoolyear=schoolyear;
			historyclassid=classid;
		}
		List<KsysSubjectDto> ksd = null;
		List<KsysSubjectDto> ksd1=null;
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype())  //班主任
				&&Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){
		  ksd = iScoreExt.getSubjectBySegmentId(cmis30id, segmentId,courseType,"",historyclassid,historyschoolyear); //学科
		  //反向推理，更加学生查询出对应的课程
		  ksd1=iScoreExt.getSubjectBySegmentIdAndStudents(cmis30id,user.getDiscode(),segmentId,courseType,classid);
		  ksd=getLastSubject(ksd,ksd1);
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //任课老师
				&&Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){
			 ksd = iScoreExt.getSubjectBySegmentId(cmis30id, segmentId,courseType,user.getTeacherid(),historyclassid,historyschoolyear); //学科
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
				&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
			 ksd = iScoreExt.getSubjectBySegmentId(cmis30id, segmentId,courseType,"",historyclassid,historyschoolyear); //学科
			 ksd1=iScoreExt.getSubjectBySegmentIdAndStudents(cmis30id,user.getDiscode(),segmentId,courseType,classid);
			 ksd=getLastSubject(ksd,ksd1); 
		}else if(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE.equals(user.getUserRealType())){
			 ksd = iScoreExt.getSubjectBySegmentId(cmis30id, segmentId,courseType,user.getTeacherid(),historyclassid,historyschoolyear); //学科
		}
		if(ksd==null)
			return new ArrayList<KsysSubjectDto>();
		return  ksd;
	}
	
	private List<KsysSubjectDto> getLastSubject(List<KsysSubjectDto> ksd,List<KsysSubjectDto> ksd1){
		Map<String,KsysSubjectDto> sysSubjectMaps=new HashMap<String,KsysSubjectDto>();
		if(ksd1!=null&&ksd1.size()>0){
			for(KsysSubjectDto dto:ksd1){
				sysSubjectMaps.put(dto.getSubject_id(),dto);
			}
		}
		if(ksd!=null&&ksd.size()>0){
			for(KsysSubjectDto dto:ksd){
				sysSubjectMaps.put(dto.getSubject_id(),dto);
			}
		}
		if(sysSubjectMaps.values()!=null&&sysSubjectMaps.values().size()>0)
			return new ArrayList<KsysSubjectDto>(sysSubjectMaps.values());
		return null;
	}
	/**
	 * 根据学科和课程类型获取课程模块
	 * @param schoolyear
	 * @param classid
	 * @return
	 */
	@Json
	public Object queryCourseBySubjectIdAndCourseType(HttpServletRequest req){
		UserDto user = getLoginInfo(req);
		cmis30id = user.getCmis30id();
		List<KcourseDto>  kcd = new ArrayList<KcourseDto>();
		//classid = queryClassIdBySchoolyearAndClassid(schoolyear,classid);
		String historyclassid = queryClassIdBySchoolyearAndClassid(schoolyear,classid);
		String historyschoolyear=null;
		if(NestUtil.isEmpty(historyclassid)){
			historyschoolyear=schoolyear;
			historyclassid=classid;
		}
		List<KcourseDto>  kcd2 = new ArrayList<KcourseDto>();
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype())  //班主任
				&&Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){
			kcd = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, courseType, subjectId,segmentId,"",historyclassid,historyschoolyear); //模块 
			kcd2=iScoreExt.getCourseBySubjectIdAndCourseTypeByStudents(cmis30id,user.getDiscode(),subjectId,segmentId,classid);
			kcd=getLastCourse(kcd,kcd2);
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //任课老师
				&&Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){
			kcd = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, courseType, subjectId,segmentId,user.getTeacherid(),historyclassid,historyschoolyear); //模块 
		}else if(Constant.USER_KIND_SCHOOLTEACHER.equals(user.getUsertype()) //教务老师
				&&Constant.USER_TYPE_SCHOOLADMIN.equals(user.getUserRealType())){
			kcd = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, courseType, subjectId,segmentId,"",historyclassid,historyschoolyear); //模块 
			kcd2=iScoreExt.getCourseBySubjectIdAndCourseTypeByStudents(cmis30id,user.getDiscode(),subjectId,segmentId,classid);
			kcd=getLastCourse(kcd,kcd2);
		}else if(Constant.USER_TYPE_COURSEMASTER_SCHOOLCODE.equals(user.getUserRealType())){
			kcd = iScoreExt.getCourseBySubjectIdAndCourseType(cmis30id, courseType, subjectId,segmentId,user.getTeacherid(),historyclassid,historyschoolyear); //模块 
		}
		if(kcd==null){
			return new ArrayList<KcourseDto>();
		}
		return kcd; //模块 
	}
	private List<KcourseDto> getLastCourse(List<KcourseDto> kcd,List<KcourseDto> kcd2){
		Map<String,KcourseDto> courseMaps=new HashMap<String,KcourseDto>();
		if(kcd2!=null&&kcd2.size()>0){
			for(KcourseDto dto:kcd2){
				courseMaps.put(dto.getCourse_id(),dto);
			}
		}
		if(kcd!=null&&kcd.size()>0){
			for(KcourseDto dto:kcd){
				courseMaps.put(dto.getCourse_id(),dto);
			}
		}
		if(courseMaps.values()!=null&&courseMaps.values().size()>0)
			return new ArrayList<KcourseDto>(courseMaps.values());
		
		return null;
	}
	@Json
	public Object queryScore(HttpServletRequest req, HttpServletResponse resp){

		
		teacherQueryModelScore(req);
		

		
	
	/*	ModelScoreDto msd = new ModelScoreDto();
		msd.setEdu_id("112121");
		msd.setStudentName("李师师");
		mss.add(msd);
		mss.add(msd);*/
		
//		Integer maxsCoreRow =mss.size();
//		getView(mss);
		 maps.put("modelScores", mss);
		 maps.put("vds", vd);
//		 getMergeCells(maps,maxsCoreRow);
		return maps;
		
	} 
	//导出excel
	public void export(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{

		    teacherQueryModelScore(req);
		
			ExportScore e = new ExportScore();
			segmentValue = java.net.URLDecoder.decode(segmentValue , "UTF-8"); 
			subjectValue = java.net.URLDecoder.decode(subjectValue , "UTF-8"); 
			courseValue = java.net.URLDecoder.decode(courseValue , "UTF-8"); 
			gradeName1 = java.net.URLDecoder.decode(gradeName1 , "UTF-8"); 
			className1 = java.net.URLDecoder.decode(className1 , "UTF-8"); 
			
			e.init(req, resp,mss,vd,schoolyearValue,segmentValue,subjectValue,courseValue,gradeName1,className1,Constant.USER_KIND_SCHOOLTEACHER,"");
		
	}
	
	//合并单元格数据
//	public Map<String,Object> getMergeCells(Map<String,Object> map,Integer maxsCoreRow){
//		List<MergeCellsDto> mcds = new ArrayList<MergeCellsDto>();
//		MergeCellsDto mcd1 = new MergeCellsDto();
//		mcd1.setRow(maxsCoreRow);
//		mcd1.setCol(0);
//		mcd1.setRowspan(1);
//		mcd1.setColspan(2);
//		
//		MergeCellsDto mcd2 = new MergeCellsDto();
//		mcd2.setRow(maxsCoreRow+1);
//		mcd2.setCol(0);
//		mcd2.setRowspan(1);
//		mcd2.setColspan(2);
//		
//		MergeCellsDto mcd3 = new MergeCellsDto();
//		mcd3.setRow(maxsCoreRow+2);
//		mcd3.setCol(0);
//		mcd3.setRowspan(1);
//		mcd3.setColspan(2);
//		
//		MergeCellsDto mcd4 = new MergeCellsDto();
//		mcd4.setRow(maxsCoreRow+3);
//		mcd4.setCol(0);
//		mcd4.setRowspan(1);
//		mcd4.setColspan(2);
//		
//		mcds.add(mcd1);
//		mcds.add(mcd2);
//		mcds.add(mcd3);
//		mcds.add(mcd4);
//		map.put("getMergeCells", mcds);
//		return map;
//	}
//	
//	
//	public List<ModelScoreDto> getView(List<ModelScoreDto> mss){
//		ModelScoreDto msd1 = new ModelScoreDto();
//		ModelScoreDto msd2 = new ModelScoreDto();
//		ModelScoreDto msd3 = new ModelScoreDto();
//		ModelScoreDto msd4 = new ModelScoreDto();
//		msd1.setEdu_id("最高成绩");
//		msd2.setEdu_id("最低成绩");
//		msd3.setEdu_id("平均成绩");
//		msd4.setEdu_id("标准差");
//		mss.add(msd1);
//		mss.add(msd2);
//		mss.add(msd3);
//		mss.add(msd4);
//		return mss;
//	}
	
	/**
	 * 保存更新成绩
	 * @return
	 */
	@Json
	public Object saveScore(HttpServletRequest req){
		UserDto user = getLoginInfo(req);
		List<ModelScoreDto> modelScores = converAnswerFormString(scoreJson);
		try{
			iScoreExt.insertNZScore(modelScores, courseType,Constant.KG_CREDIT_SOURCE_STUDY,user.getCmis30id(),user.getDiscode(),class_model_id);
			maps.put("success", "success");
			return maps;
		}catch (Exception e) {
			maps.put("error", "error");
			return maps;
		}
		
		
	}
	
	public Object studentQueryScoreFirst(HttpServletRequest req,HttpSession session){
		//获取登录用户信息
		   UserDto user = getLoginInfo(req);
			xueYears = iScoreExt.getYears(user.getGradenum());  //获取学年
			 if(xueYears.size()>0){
				 Map<String, Object> params = new HashMap<String, Object>();
				 params.put("cmis30id",user.getCmis30id());
				 params.put("schoolyear", xueYears.get(0).split("@")[0]);
				 segments = schoolCourseExt.getSegmentInfos(params);  //学段
			 }
			 
		return "scoreQuery.jsp";
	}
	
	/** 
	 * 学生查询成绩
	 */
	public Object studentQueryScore(HttpServletRequest req,HttpSession session){
		//获取登录用户信息
	   UserDto user = getLoginInfo(req);
		xueYears = iScoreExt.getYears(user.getGradenum());  //获取学年
		 if(xueYears.size()>0){
			 Map<String, Object> params = new HashMap<String, Object>();
			 params.put("cmis30id",user.getCmis30id());
			 params.put("schoolyear", xueYears.get(0).split("@")[0]);
			 segments = schoolCourseExt.getSegmentInfos(params);  //学段
			 if(segments.size()>0){
				 mss = iScoreExt.studentGetNZScore(segments.get(0).getSegment_id(), user.getClassid(), user.getEduId(), user.getCmis30id(), user.getDiscode());
				 mss.addAll(iScoreExt.studentGetSchoolScore(segments.get(0).getSegment_id(),user.getClassid(), user.getEduId(), user.getCmis30id(), user.getDiscode()));
			 }
		 }
		 
		List<ModelScoreDto>  studentNames = iScoreExt.GetStudentNameByEduId( user.getCmis30id(), user.getDiscode(), user.getEduId());
		
		if(studentNames!=null && studentNames.size()>0){
			studentName =studentNames.get(0).getStudentName();  
		}
		

		eduId = user.getEduId();
		 if("1".equals(user.getGradenum())){
			 className +="高一";
		 }else  if("2".equals(user.getGradenum())){
			 className +="高二";
		 }else  if("3".equals(user.getGradenum())){
			 className +="高三";
		 }
		 
		 className +=user.getClassName();
		 if(null!=mss && mss.size()>0){
			 for(int i=0;i<mss.size();i++){
				 ModelScoreDto dto = mss.get(i);
				if(!("".equals(dto.getCredit_hour())|| null==dto.getCredit_hour())){
					xfCount+=Integer.parseInt(dto.getCredit_hour());
				} 
				
				if(Constant.KG_COURSE_BX.equals(dto.getCourse_kind())){
					bXScore.add(dto);
				}else if(Constant.KG_COURSE_BX1.equals(dto.getCourse_kind())){
					bX1Score.add(dto);
				}else if(Constant.KG_COURSE_XX.equals(dto.getCourse_kind())){
					xXScore.add(dto);
				}else  if(Constant.KG_COURSE_KIND.equals(dto.getCourse_kind())){
					xBScore.add(dto);
				}
			
			
			 }
		 }
		 
		return "scoreQuery.jsp";
	}
	
	/**
	 * 学生查询成绩
	 * @return
	 */
	@Json
	public Object studentQueryScore1(HttpServletRequest req){
		//获取登录用户信息
		 UserDto user = getLoginInfo(req);
		 classid = queryClassIdBySchoolyearAndClassid(schoolyear,user.getClassid());
		 mss = iScoreExt.studentGetNZScore(segmentId, classid, user.getEduId(), user.getCmis30id(), user.getDiscode());
		 if(mss==null) mss=new ArrayList<ModelScoreDto>();
		 mss.addAll(iScoreExt.studentGetSchoolScore(segmentId,classid, user.getEduId(), user.getCmis30id(), user.getDiscode()));
		
		 eduId = user.getEduId();
		 if("1".equals(user.getGradenum())){
			 className +="高一";
		 }else  if("2".equals(user.getGradenum())){
			 className +="高二";
		 }else  if("3".equals(user.getGradenum())){
			 className +="高三";
		 }
		 List<ModelScoreDto>  studentNames = iScoreExt.GetStudentNameByEduId( user.getCmis30id(), user.getDiscode(), user.getEduId());
			
			if(studentNames!=null && studentNames.size()>0){
				studentName =studentNames.get(0).getStudentName();  
			}
		 className +=user.getClassName();
		 if(null!=mss && mss.size()>0){
			 for(int i=0;i<mss.size();i++){
				 ModelScoreDto dto = mss.get(i);
				if(!("".equals(dto.getCredit_hour())|| null==dto.getCredit_hour())){
					xfCount+=Double.parseDouble(dto.getCredit_hour());
				} 
				
				if(Constant.KG_COURSE_BX.equals(dto.getCourse_kind())){
					bXScore.add(dto);
				}else if(Constant.KG_COURSE_BX1.equals(dto.getCourse_kind())){
					bX1Score.add(dto);
				}else if(Constant.KG_COURSE_XX.equals(dto.getCourse_kind())){
					xXScore.add(dto);
				}else  if(Constant.KG_COURSE_KIND.equals(dto.getCourse_kind())){
					xBScore.add(dto);
				}
			
			
			 }
		 }
		 
		 maps.put("mss", mss);
		 maps.put("eduId", eduId);
		 maps.put("studentName", studentName);
		 maps.put("className", className);
		 maps.put("xfCount", xfCount);
		 maps.put("bXScore", bXScore);
		 maps.put("bX1Score", bX1Score);
		 maps.put("xXScore", xXScore);
		 maps.put("xBScore", xBScore);
		 
		 return maps;
	}
	
	//学生导出成绩excel
		public void studentExport(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException{

			//获取登录用户信息
			 UserDto user = getLoginInfo(req);
			 classid = queryClassIdBySchoolyearAndClassid(schoolyear,user.getClassid());
			 mss = iScoreExt.studentGetNZScore(segmentId, classid, user.getEduId(), user.getCmis30id(), user.getDiscode());
			 mss.addAll(iScoreExt.studentGetSchoolScore(segmentId,classid, user.getEduId(), user.getCmis30id(), user.getDiscode()));
			
				ExportScore e = new ExportScore();
				segmentValue = java.net.URLDecoder.decode(segmentValue , "UTF-8"); 
				
				List<ModelScoreDto>  studentNames = iScoreExt.GetStudentNameByEduId( user.getCmis30id(), user.getDiscode(), user.getEduId());
				
				if(studentNames!=null && studentNames.size()>0){
					studentName =studentNames.get(0).getStudentName();  
				}
				e.init(req, resp,mss,vd,schoolyearValue,segmentValue,"","","","",Constant.USER_KIND_SCHOOLSTUDENT,studentName);
			
		}
	
	public  List<ModelScoreDto> converAnswerFormString(String datas){  
		if(NestUtil.isEmpty(datas))return null;
        //将字符串转换为json对象
		String values=datas.replaceAll("&quot;","\"");
		JSONObject jo = JSONObject.fromObject(values);
		JSONArray jsonArray = jo.getJSONArray("datas");
		if(jsonArray==null)return null;
		  List<ModelScoreDto> msd = (List<ModelScoreDto>)JSONArray.toCollection(jsonArray, ModelScoreDto.class);
	        return msd;  
	 }
	
	public Object scoreExport(){
		try {
			tableTitle2= java.net.URLDecoder.decode(tableTitle2 , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return "scoreExport.jsp";
	}
	
	public Object hScoreExport(){
		try {
			tableTitle2= java.net.URLDecoder.decode(tableTitle2 , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return "hScoreExport.jsp";
	}
}
