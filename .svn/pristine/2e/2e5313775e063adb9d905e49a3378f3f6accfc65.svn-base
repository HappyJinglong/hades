package com.flyrish.hades.webapp.action.homePage;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.FuncTreeDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.dto.OFunc;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IHomePageManagerExt;
import com.flyrish.hades.service.ext.IInformQueryExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILoginUserInfoServiceExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOUserServiceExt;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class HomePageAction extends NginxUploadAction{
	
//	public String schoolIdBase;
	
	public String level;		//用户等级
	public Integer userId;		//用户ID
	public Integer userType;	//用户类型
	public Integer roleId;		//角色ID
	public Integer roleType;		//角色类型
	public Integer campuseId;	//校区ID
	public Integer levelCode;	//学段类型
	public Integer funcLevelType;//功能等级类型
	public String counts;       //通知公告总条数
	public Integer levelId;		//等级ID
	public String commonFuncId;
	@Spring
	IOUserServiceExt userServiceExt;
	@Spring
	IHomePageManagerExt homePageManagerExt;
	@Spring
	ILoginUserInfoServiceExt loginUserInfoServiceExt;
	@Spring
	IBaseInforManagerExt baseInforManagerExt;
	@Spring
	public IMasterAppriseExt masterAppriseExt;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	//常用菜单的个数
	public boolean isPopUp = false;
	public List<FuncTreeDto> userFuncTreeDtoes;
	public List<FuncTreeDto> userCommonFunc;
	List<AppraisalDto> applist;
	List<Integer> countList;
	//常用功能的个数
	public Integer commonFuncNum;
	public String cmis30id;
	public String discode;
	//public String flag;
	//点击更多时传过来的参数
	public String moreflag;
	String personid; 
	String campusid;
	String userid;
	String teacherid;  //教师id
	String eduid;
	String username;
	String termid;
	String studentid;
	String gradenum;
	String parentname;
	String informcount;
	public String levelcode;
	String classid;
	UserDto userDto;
	//用户类别1503
	public String usertype;
	//登录用户真实的角色类型(校级)，其他级别为null
	public String userRealtype;
	@Spring
	public IInformQueryExt informQueryExt;
	@Before
	public Object doBefore(HttpServletRequest req)
	{
		userDto = this.getLoginInfo(req);
		usertype = userDto.getUsertype();
		userid = userDto.getUserid();
		discode = userDto.getDiscode();
		campusid = userDto.getCampuseId();
		cmis30id = userDto.getCmis30id();
		userRealtype = userDto.getUserRealType();
		levelcode = userDto.getLevelcode();
		classid = userDto.getClassid();
		teacherid = userDto.getTeacherid();
		eduid = userDto.getEduId();
		username = userDto.getTeachereduId();
		termid = userDto.getTermId();
		studentid = userDto.getPersonid();
		gradenum = userDto.getGradenum();
		parentname = userDto.getUsername();
		return null;
	}
	@DefaultAction
	public Object toDefaultPage(HttpServletResponse response){
	//	queryNewApprasial(response);
		commonFun();
	//	queryInform();
		return "/system/main.jsp";
	}
	
	
	
	
	
	/**
	 * 通知公告
	 */
	@SuppressWarnings("unchecked")
	public Object queryInform(HttpServletResponse response)
	{
		List<InformDto> informlist = new ArrayList<InformDto>();
		Map<String, Object> params = new HashMap<String, Object>();
		Date date_now = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String date = simple.format(date_now);
		if ("1".equals(constantBean.get("isCache"))) {     //走缓存
			Map<String, InformDto> map = (Map<String, InformDto>) redisServiceExt
					.readMap(Constant.R_READINFORM_KEY);
			if (Constant.USER_KIND_CITY.equals(usertype)&&map!=null) // 市级用户类型
			{
				params.put("isAll", "1");
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				params.put("userid", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					Collections.sort(pageObj.getPageElements(), ComparatorByYNandDate);
					return "/inform/informlist.jsp";
				} else {
					for (Map.Entry<String, InformDto> entry : map.entrySet()) {
						InformDto inform = entry.getValue();
						if (userid.equals(inform.getUserid())
								&& "1".equals(inform.getIsall())) {
							Map<String, String> readMap = inform.getReadMap();
							if (NestUtil.isEmpty(readMap.get(userid))) {
								inform.setFlag("-1");
							}else{
								inform.setFlag("0");
							}
							informlist.add(inform);
						}
					}
					if(informlist!=null&&informlist.size()>0)
					{
						Collections.sort(informlist, ComparatorByYNandDate);
					}
					if(informlist.size()>24)
					{
						informlist = informlist.subList(0, 24);
					}
				}
			} else if (Constant.USER_KIND_COUNTY.equals(usertype)&&map!=null) { // 区级用户类型
				params.put("isAll", "1");
				params.put("receiverObj", usertype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					for (Map.Entry<String, InformDto> entry : map.entrySet()) {
						InformDto inform = entry.getValue();
						if ("1".equals(inform.getIsall())
								&& inform.getReceiverObj().contains(usertype)) {
							Map<String, String> readMap = inform.getReadMap();
							if (NestUtil.isEmpty(readMap.get(userid))) {
								inform.setFlag("-1");
							}else{
								inform.setFlag("0");
							}
							informlist.add(inform);
						}
					}
					informcount = informlist.size()+"";
					if(informlist!=null&&informlist.size()>0)
					{
						Collections.sort(informlist, ComparatorByYNandDate);
					}
					if(informlist.size()>24)
					{
						informlist = informlist.subList(0, 24);
					}
				}
			} else if ((Constant.USER_TYPE_SCHOOLADMIN.equals(userRealtype)
					|| Constant.USER_TYPE_SPORTSEMASTER.equals(userRealtype))&&map!=null) { // 教务和德育老师
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + "00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					for (Map.Entry<String, InformDto> entry : map.entrySet()) {
						InformDto inform = entry.getValue();
						if (("1".equals(inform.getIsall()) || discode
								.equals(inform.getPublishDiscode()))
								&& inform.getReceiverObj().contains(
										userRealtype)
								&& inform.getObjlevel().contains(levelcode)) {
							Map<String, String> readMap = inform.getReadMap();
							if (NestUtil.isEmpty(readMap.get(userid))) {
								inform.setFlag("-1");
							}else{
								inform.setFlag("0");
							}
							informlist.add(inform);
						}
					}
					informcount = informlist.size()+"";
					if(informlist!=null&&informlist.size()>0)
					{
						Collections.sort(informlist, ComparatorByYNandDate);
					}
					if(informlist.size()>24)
					{
						informlist = informlist.subList(0, 24);
					}
				}
			} else if ((Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)
					|| Constant.USER_TYPE_COURSEMASTER.equals(userRealtype))&&map!=null) { // 班主任和任课老师
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("campusid", campusid);
				params.put("objlevel", levelcode);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					for (Map.Entry<String, InformDto> entry : map.entrySet()) {
						InformDto inform = entry.getValue();
						if (("1".equals(inform.getIsall())
								|| discode.equals(inform.getPublishDiscode()) || campusid
									.equals(inform.getCampusid()))
								&& inform.getReceiverObj().contains(
										userRealtype)
								&& inform.getObjlevel().contains(levelcode)) {
							Map<String, String> readMap = inform.getReadMap();
							if (NestUtil.isEmpty(readMap.get(userid))) {
								inform.setFlag("-1");
							}else{
								inform.setFlag("0");
							}
							informlist.add(inform);
						}
					}
					informcount = informlist.size()+"";
					if(informlist!=null&&informlist.size()>0)
					{
						Collections.sort(informlist, ComparatorByYNandDate);
					}
					if(informlist.size()>5)
					{
						informlist = informlist.subList(0, 5);
					}
				}
			} else if ((Constant.USER_TYPE_STUDENT.equals(userRealtype)
					|| Constant.USER_TYPE_PARENT.equals(userRealtype))&&map!=null) // 学生和家长
			{
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("classid", classid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				//String user = informQueryExt.queryUserid(param);
				String user=userDto.getMasterid();
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("campusid", campusid);
				params.put("userid", user);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					for (Map.Entry<String, InformDto> entry : map.entrySet()) {
						InformDto inform = entry.getValue();
						if (("1".equals(inform.getIsall())
								|| discode.equals(inform.getPublishDiscode())
								|| campusid.equals(inform.getCampusid()) || inform
								.getUserid().equals(user))
								&& inform.getReceiverObj().contains(
										userRealtype)
								&& inform.getObjlevel().contains(levelcode)) {
							Map<String, String> readMap = inform.getReadMap();
							if (NestUtil.isEmpty(readMap.get(userid))) {
								inform.setFlag("-1");
							}
							informlist.add(inform);
						}
					}
					informcount = informlist.size()+"";
					if(informlist!=null&&informlist.size()>0)
					{
						Collections.sort(informlist, ComparatorByYNandDate);
					}
					if(informlist.size()>5)
					{
						informlist = informlist.subList(0, 5);
					}
				}
			}
		} else {
			if (Constant.USER_KIND_CITY.equals(usertype)) // 市级用户类型
			{
				params.put("isAll", "1");
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				params.put("userid", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					pageObj = informQueryExt.informQuery(params, pageNo, 24);
				}
			} else if (Constant.USER_KIND_COUNTY.equals(usertype)) { // 区级用户类型
				params.put("isAll", "1");
				params.put("receiverObj", usertype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					pageObj = informQueryExt.informQuery(params, pageNo, 24);
				}
			} else if (Constant.USER_TYPE_SCHOOLADMIN.equals(userRealtype)
					|| Constant.USER_TYPE_SPORTSEMASTER.equals(userRealtype)) { // 教务和德育老师
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + "00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					pageObj = informQueryExt.informQuery(params, pageNo, 24);
				}
			} else if (Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)
					|| Constant.USER_TYPE_COURSEMASTER.equals(userRealtype)) { // 班主任和任课老师
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("campusid", campusid);
				params.put("objlevel", levelcode);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					pageObj = informQueryExt.informQuery(params, pageNo, 5);
				}
			} else if (Constant.USER_TYPE_STUDENT.equals(userRealtype)
					|| Constant.USER_TYPE_PARENT.equals(userRealtype)) // 学生和家长
			{
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("classid", classid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				String user = informQueryExt.queryUserid(param);
				params.put("receiverObj", userRealtype);
				params.put("publishState", "1");
				params.put("endtime", date + " 00-00-00");
				params.put("discode", discode);
				params.put("objlevel", levelcode);
				params.put("campusid", campusid);
				params.put("userid", user);
				params.put("oderbyC", "1");
				params.put("userid1", userid);
				if ("1".equals(moreflag)) {
					pageObj = informQueryExt.informQuery(params, pageNo,
							pageSize);
					return "/inform/informlist.jsp";
				} else {
					pageObj = informQueryExt.informQuery(params, pageNo, 5);
				}
			}
			informlist =(List<InformDto>)pageObj.getPageElements();
			informcount = pageObj.getTotalCount()+"";
		}
		JSONArray json = JSONArray.fromObject(informlist);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write("{'count':"+informcount+",'content':"+json.toString()+"}");
		} catch (IOException e) {
		}
		return null;
	}
	
	
	
	
	
	
	
	
	//首页显示最新的20条记录
	public void queryNewApprasial(HttpServletResponse response)
	{
		applist = new ArrayList<AppraisalDto>();
		countList = new ArrayList<Integer>();
		Map<String,Object> param = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询参数
		params.put("cmis30id", cmis30id);
		params.put("discode", discode);
		params.put("techerid", teacherid);
		params.put("levelcode", levelcode);
		params.put("campusid", campusid);
		if ("1".equals(constantBean.get("isCache"))) {   
			queryNewAppraiseInCache();
		}else{
			queryNewAppraiseInDb(response);
		}
//		JSONArray json = JSONArray.fromObject(applist);
		JSONArray json = JSONArray.fromObject(countList);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
		}
	}
	private void queryNewAppraiseInCache() {
		String redisKey="";
		Integer xqyscount = 0;
		Integer xqjscount = 0;
		Integer sxddcount = 0;
		Integer xycjcount = 0;
		Integer hzjlcount = 0;
		Integer ydjkcount = 0;
		Integer smbxcount = 0;
		Integer zhsjcount = 0;
		Integer gxfzcount = 0;
		if(Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)){// 班主任
			redisKey=MessageFormat.format(Constant.M_MASTER_FLAG,campusid+"_"+levelcode+"_"+username);
		}else if(Constant.USER_TYPE_COURSEMASTER.equals(userRealtype)){// 任课老师
			redisKey=MessageFormat.format(Constant.T_TEACHER_FLAG,campusid+"_"+levelcode+"_"+username);
		}else if(Constant.USER_TYPE_PARENT.equals(userRealtype)&&NestUtil.isNotEmpty(username)){//家长
			redisKey=MessageFormat.format(Constant.S_STUDENT_FLAG,username.split("_")[0]);
		}else if(Constant.USER_TYPE_STUDENT.equals(userRealtype)){//学生
			redisKey=MessageFormat.format(Constant.S_STUDENT_FLAG,username);
		}
		if(NestUtil.isNotEmpty(redisKey)){
			//初中
			if (Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_CZ).equals(Integer.valueOf(levelcode))) {
				xqyscount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_NEWTERM);
				countList.add(xqyscount);
				xqjscount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_TERN_END);
				countList.add(xqjscount);
				sxddcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_MORALITY);
				countList.add(sxddcount);
				xycjcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_STUDY);
				countList.add(xycjcount);
				hzjlcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_COOPERATION);
				countList.add(hzjlcount);
				ydjkcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_SPROT);
				countList.add(ydjkcount);
				smbxcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_AESTHETIC);
				countList.add(smbxcount);
				zhsjcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_PRACTICE);
				countList.add(zhsjcount);
				gxfzcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_DEVELOP);
				countList.add(gxfzcount);
			} else if (Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_GZ).equals(Integer.valueOf(levelcode)) || Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_GZYK).equals(Integer.valueOf(levelcode))) {
				xqyscount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.BEGIN_OF_THE_NEW_TERM);
				countList.add(xqyscount);
				xqjscount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.AT_THE_END_OF_THE_TERM);
				countList.add(xqjscount);
				sxddcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.IDEOLOGICAL_MORALITY);
				countList.add(sxddcount);
				xycjcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.ACADEMIC_ACHIEVEMENT);
				countList.add(xycjcount);
				hzjlcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.COOPERATION_AND_EXCHANGE);
				countList.add(hzjlcount);
				ydjkcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.SPORTS_AND_HEALTH);
				countList.add(ydjkcount);
				smbxcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.AESTHETIC_AND_PERFORMANCE);
				countList.add(smbxcount);
				zhsjcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES);
				countList.add(zhsjcount);
				gxfzcount = latestEvaluationRecordExt.queryStatisticsRecordDataInCache(redisKey, Constant.PERSONALITY_DEVELOPMENT);
				countList.add(gxfzcount);
			}
			
//			LinkedList<String> queue=redisServiceExt.readLinkList(redisKey,1000*60*60*24);
//			if(queue==null||queue.isEmpty())return;
//			for(int i=queue.size()-1;i>-1;i--){
//				AppraisalDto dto=new AppraisalDto();
//				String[] strMsgs=queue.get(i).split("_");
//				if(NestUtil.isEmpty(strMsgs[0])||NestUtil.isEmpty(strMsgs[1]))continue;
//				if(Constant.USER_TYPE_STUDENT.equals(userRealtype)){
//					String message=strMsgs[0];
//					if(message.contains(Constant.me_apprasialidentify)){
//						dto.setMsg(message.replace(userDto.getStudentName(),Constant.me_apprasial));
//					}else{
//						dto.setMsg(message.replace(userDto.getStudentName(),"我"));
//					}
//				}else if(Constant.USER_TYPE_PARENT.equals(userRealtype)){
//					String message=strMsgs[0];
//					if(message.contains(Constant.me_apprasialidentify)){
//						message=message.replace(userDto.getStudentName(),Constant.me_apprasial);
//						dto.setMsg(message.replace(Constant.me_apprasialidentify,userDto.getStudentName()));
//					}else{
//						dto.setMsg(message);
//					}
//				}else{
//					dto.setMsg(strMsgs[0]);
//				}
//				dto.setSigndate1(strMsgs[1]);
//				applist.add(dto);
//			}
		}
	}
	private void queryNewAppraiseInDb(HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cmis30id", cmis30id);
		params.put("discode", discode);
		params.put("techerid", teacherid);
		params.put("levelcode", levelcode);
		params.put("campusid", campusid);
		if ((Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZ)
				|| (Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK)) {
			String termid2 = gradenum+termid.substring(4, 5);
			if (Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)) // 班主任
			{
				List<CampusDto> campuslist = masterAppriseExt
						.getClassInfos(params);
				List<String> classidlist = new ArrayList<String>();
				if (null != campuslist && campuslist.size() > 0) {
					for (CampusDto campus : campuslist) {
						classidlist.add(campus.getClassId());
					}
				}else{
					try {
						response.getWriter().write("false");
						return;
					} catch (IOException e) {}
				}
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("cmis30id", cmis30id);
				params1.put("discode", discode);
				params1.put("idslist", classidlist);
				List<SchoolTreeDto> studentlist = new ArrayList<SchoolTreeDto>();
				studentlist = informQueryExt.getStudentInfo(params1);
				List<String> idlist = new ArrayList<String>(); // studentid的list
				List<String> eduidlist = new ArrayList<String>(); // eduid的list
				if (studentlist != null && studentlist.size() > 0) {
					for (SchoolTreeDto school : studentlist) {
						idlist.add(school.getId());
						eduidlist.add(school.getEdusyId());
					}
				}
				param.put("studentids", idlist);
				param.put("appraserrid", username);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("appraseridentify", "4");
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			} else if (Constant.USER_TYPE_COURSEMASTER.equals(userRealtype)) // 任课老师
			{
				param.put("appraserrid", username);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("appraseridentify", "3");
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			}else if(Constant.USER_TYPE_PARENT.equals(userRealtype)){       //家长
				param.put("appraserrid1", parentname);
				param.put("eduid", eduid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("studentid", studentid);
				param.put("termid", termid2);
				param.put("termid1", termid);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				for(AppraisalDto app : applist)
				{
					if(Constant.me_apprasialidentify.equals(app.getAppraseridentity()))
					{
						app.setAppraseridentity(app.getStudentName());
						app.setStudentName(Constant.me_apprasial);
					}
				}
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				} 
			}else if(Constant.USER_TYPE_STUDENT.equals(userRealtype))       //学生
			{
				param.put("eduid", eduid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("studentid", studentid);
				param.put("termid", termid2);
				param.put("termid1", termid);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				for(AppraisalDto app : applist)
				{
					if(Constant.me_apprasialidentify.equals(app.getAppraseridentity()))
					{
						app.setStudentName(Constant.me_apprasial);
					}else{
						app.setStudentName(Constant.me);
					}
				}
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			}
		} else if ((Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_CZ)) {    //初中的查看
			if (Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)) // 班主任
			{
				List<CampusDto> campuslist = masterAppriseExt
						.getClassInfos(params);
				List<String> classidlist = new ArrayList<String>();
				if (null != campuslist && campuslist.size() > 0) {
					for (CampusDto campus : campuslist) {
						classidlist.add(campus.getClassId());
					}
				}else{
					try {
						response.getWriter().write("false");
						return;
					} catch (IOException e) {}
				}
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("cmis30id", cmis30id);
				params1.put("discode", discode);
				params1.put("idslist", classidlist);
				List<SchoolTreeDto> studentlist = new ArrayList<SchoolTreeDto>();
				studentlist = informQueryExt.getStudentInfo(params1);
				List<String> idlist = new ArrayList<String>(); // studentid的list
				List<String> eduidlist = new ArrayList<String>(); // eduid的list
				if (studentlist != null && studentlist.size() > 0) {
					for (SchoolTreeDto school : studentlist) {
						idlist.add(school.getId());
						eduidlist.add(school.getEdusyId());
					}
				}
				param.put("studentids", idlist);
				param.put("appraserrid", username);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("writeman", Constant.MASTER_DICT_CZ);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			} else if (Constant.USER_TYPE_COURSEMASTER.equals(userRealtype)) // 任课老师
			{
				param.put("appraserrid", username);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
                param.put("writeman", Constant.TEACHER_DICT_CZ);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			}else if(Constant.USER_TYPE_PARENT.equals(userRealtype)){       //家长
				param.put("appraserrid", parentname);
				param.put("eduid", eduid);
				param.put("studentid", studentid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				for(AppraisalDto app : applist)
				{
					if(Constant.me_apprasialidentify.equals(app.getAppraseridentity()))
					{
						app.setAppraseridentity(app.getStudentName());
						app.setStudentName(Constant.me_apprasial);
					}
				}
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			}else if(Constant.USER_TYPE_STUDENT.equals(userRealtype))      //学生
			{
				param.put("eduid", eduid);
				param.put("cmis30id", cmis30id);
				param.put("discode", discode);
				param.put("studentid", studentid);
				applist = informQueryExt.queryAppraisal(
						param, levelcode, userRealtype);
				for(AppraisalDto app : applist)
				{
					if(Constant.me_apprasialidentify.equals(app.getAppraseridentity()))
					{
						app.setStudentName(Constant.me_apprasial);
					}else{
						app.setStudentName(Constant.me);
					}
				}
				if(null!=applist&&applist.size()>0)
				{
					Collections.sort(applist, GOODS_BY_INDATE);
				}
			}
		}
	}
	private void commonFun()
	{
		if(!Constant.USER_KIND_SCHOOLGROUP.equals(userDto.getUsertype())){
			//默认角色下的所有菜单
			if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
				funcLevelType = 1;
			}
			if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode())
					||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(userDto.getLevelcode())){
				funcLevelType = 3;
			}
			//用户常用功能查询
			List<OFunc> commonFuncs = homePageManagerExt.queryCommonFuncFromRedis(userDto, funcLevelType);
			if(null!=commonFuncs && commonFuncs.size()>0){
				userCommonFunc = new ArrayList<FuncTreeDto>();
				for(OFunc oFunc : commonFuncs){
					FuncTreeDto ftd = new FuncTreeDto();
					ftd.setFuncId(oFunc.getFuncid().toString());
					ftd.setFuncName(oFunc.getFuncname());
					ftd.setUrl(oFunc.getExecfilename());
					ftd.setUserId(oFunc.getUserId());
					ftd.setCommonFuncId(oFunc.getCommonFuncId());
					userCommonFunc.add(ftd);
				}
			}else{
				userCommonFunc = homePageManagerExt.queryCommonFunc(userDto.getUserid(),userDto.getRoleId(),funcLevelType);
				//没有放进缓存
				if(null!=userCommonFunc && userCommonFunc.size()>0){
					List<OFunc>newCommonFuncs = new ArrayList<OFunc>();
					for(FuncTreeDto ftd : userCommonFunc){
						OFunc of = new OFunc();
						of.setFuncid(new BigDecimal(ftd.getFuncId()));
						of.setFuncname(ftd.getFuncName());
						of.setExecfilename(ftd.getUrl());
						of.setUserId(ftd.getUserId());
						of.setCommonFuncId(ftd.getCommonFuncId());
						of.setClickCount(1);
						of.setUpdateOrInsertFlag(1);
						newCommonFuncs.add(of);
					}
					homePageManagerExt.saveCommonMenuToRedis(userDto, newCommonFuncs);
				}
			}
		}
	}
	public  final Comparator<AppraisalDto> GOODS_BY_INDATE = new Comparator<AppraisalDto>(){          
        public int compare(AppraisalDto o1, AppraisalDto o2) {  
            try{                                          
                Date d1 = StringToDate(o1.getSigndate1());                         
                Date d2 = StringToDate(o2.getSigndate1());                
                return  d2.compareTo(d1);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
	/**
	 * 字符串转日期
	 * 
	 * @param 
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(d);
		} catch (Exception e) {
			return new Date();
		}
	}
	public  final Comparator ComparatorByYNandDate = new Comparator(){          
        public int compare(Object inform1, Object inform2) {  
            try{                                          
            	InformDto inform11 = (InformDto) inform1;
    			InformDto inform22 = (InformDto) inform2;
    			return inform11.compareTo(inform22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
    
    
    /**
     * 清除右上角信息
     * @param response
     */
	public void ClearContent(HttpServletRequest request) {
    	String redisKey="";
		if(Constant.USER_TYPE_CLASSMASTER.equals(userRealtype)){// 班主任
			redisKey=MessageFormat.format(Constant.M_MASTER_FLAG,campusid+"_"+levelcode+"_"+username);
		}else if(Constant.USER_TYPE_COURSEMASTER.equals(userRealtype)){// 任课老师
			redisKey=MessageFormat.format(Constant.T_TEACHER_FLAG,campusid+"_"+levelcode+"_"+username);
		}else if(Constant.USER_TYPE_PARENT.equals(userRealtype)&&NestUtil.isNotEmpty(username)){//家长
			redisKey=MessageFormat.format(Constant.S_STUDENT_FLAG,username.split("_")[0]);
		}else if(Constant.USER_TYPE_STUDENT.equals(userRealtype)){//学生
			redisKey=MessageFormat.format(Constant.S_STUDENT_FLAG,username);
		}
		if(NestUtil.isNotEmpty(redisKey)){
			//初中
			if (Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_CZ).equals(Integer.valueOf(levelcode))) {
				if ("1001".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_NEWTERM);
				}
				if ("1002".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_TERN_END);				
				}
				if ("1003".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_MORALITY);
				}
				if ("1004".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_STUDY);
				}
				if ("1005".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_COOPERATION);
				}
				if ("1006".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_SPROT);
				}
				if ("1007".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_AESTHETIC);
				}
				if ("1008".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_PRACTICE);
				}
				if ("1009".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.JUNIOR_DEVELOP);
				}
			} else if (Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_GZ).equals(Integer.valueOf(levelcode)) || Integer.valueOf(Constant.DICT_TYPE_LEVELCODE_GZYK).equals(Integer.valueOf(levelcode))) {
				if ("1001".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.BEGIN_OF_THE_NEW_TERM);
				}
				if ("1002".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.AT_THE_END_OF_THE_TERM);				
				}
				if ("1003".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.IDEOLOGICAL_MORALITY);
				}
				if ("1004".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.ACADEMIC_ACHIEVEMENT);
				}
				if ("1005".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.COOPERATION_AND_EXCHANGE);
				}
				if ("1006".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.SPORTS_AND_HEALTH);
				}
				if ("1007".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.AESTHETIC_AND_PERFORMANCE);
				}
				if ("1008".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES);
				}
				if ("1009".equals(commonFuncId)) {
					latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, Constant.PERSONALITY_DEVELOPMENT);
				}
			}
		}
//    	latestEvaluationRecordExt.deleteStatisticsRecordDataInCache(redisKey, oneLevelNum);
    }
}


