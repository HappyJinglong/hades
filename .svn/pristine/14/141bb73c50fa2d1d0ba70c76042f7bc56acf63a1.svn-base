package com.flyrish.hades.webapp.action.personality;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.commons.utils.DateUtil;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.exporter.exception.ManagerException;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPersonalityServiceExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class PersonalityAction extends NginxUploadAction {
	@Spring
	ISelfAppManagerExt selfAppManagerExt;
	/**
	 * 处理个性发展基本情况
	 */
	@Spring
	IPersonalityServiceExt personalityServiceExt;
	
	public List<AttachDto> attachDtoList;//附件信息
	
	public String id;//更新id
	
	public String id1;//更新id
	
	public String id2;//更新id
	
	public String id3;//更新id

	public String content;//评价内容
	
	public String apprasial;//评价内容

	public Integer appraseridentify;//提供人身份

	public LearnprocessWorksDto learnprocessWorksDto;//学业成就
	
	public AttachDto attachDto;//附件
	
	public String signdate;
	
	public String signDate;
	
	public String fname;
	
	public String fpath;
	
	public String uuid;
	
	public int choicenum;
	
	public String old_uuid;
	
	public String new_uuid;
	
	public String add_uuid;
	
	public StudentDto studentDto = new StudentDto();
	
	public String evaluateType1;
	
	public String evaluateType2;
	
	public String evaluateType3;
	
	public StudentDto evaluated;
	
	public String termId;// 年级学期

	public String attachId;//附件ID

	public String rpID;//有数据时回调package的id
	
	public String new_rpID;//初始化时回调package的id
	
	public String add_rpID;//新加回调package的id
	
	public int image;

	public Integer personStatus;

	public String evaluatePerson;
	
	public String processdesc;
	
	public String subject;
	
	public String developmentrd1;
	
	public String developmentrd2;
	
	public String developmentrd3;
	
	public List<AppraisalDto> appraisalList1;
	
	public List<AppraisalDto> appraisalList2;

	public List<AppraisalDto> appraisalList3;
	
	public String levelcode;
	
	public String personid;//提供人类型
	
	public List<PersonalityDto> personalityDtosList;
	
	public List<ApersonalityCacheDto> personalityDtosCacheList;
	
	public String trueAddress;//真实路径
	
	public String attType;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	@Before
	public void doBefore(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		studentDto.setClassid(Integer.valueOf(userDto.getClassid()));
		studentDto.setName(userDto.getStudentName());
		studentDto.setGradenum(userDto.getGradenum());
		studentDto.setStudentid(userDto.getPersonid());
		studentDto.setCmis30id(userDto.getCmis30id());
		studentDto.setDiscode(Integer.valueOf(userDto.getDiscode()));
		studentDto.setEduid(userDto.getEduId());
		studentDto.setTermtype(userDto.getTermtype());
		studentDto.setLevelcode(Integer.valueOf(userDto.getLevelcode()));
		isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
		trueAddress = constantBean.get("JLD_upload")+"/"+userDto.getTermId()+"/"+userDto.getDiscode()+"/"+userDto.getCmis30id()+"/"+userDto.getGradeName()+"/"+userDto.getClassName();
	}
	

	/**
	 * 返回文件名与文件路径
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Object saveFile(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		attachDto = uploadFile();
		attachDto.setAttachtype(2);
		attachDto.setImage(1);
		attachDto.setCmis30id(userDto.getCmis30id());
		attachDto.setDiscode(userDto.getDiscode());
		if(StringUtil.isEmpty(rpID)&&!StringUtil.isEmpty(new_rpID)){
			rpID = new_rpID;
		}
		else if(!StringUtil.isEmpty(add_rpID)&&StringUtil.isEmpty(new_rpID)){
			rpID = add_rpID;
		}if(attachDto!=null&&!StringUtil.isEmpty(rpID)){
			try {
				if ("0".equals(isStartAppraiseCache)) {
					personalityServiceExt.insertAttach(attachDto, rpID);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachtype("2");
					attachCacheDto.setImage("1");
					attachCacheDto.setCmis30id(userDto.getCmis30id());
					attachCacheDto.setDiscode(userDto.getDiscode());
					attachCacheDto.setAttachtypeid(rpID);
					attachCacheDto.setAttachname(attachDto.getFilename());
					attachCacheDto.setAttachpath(attachDto.getFilepath());
					personalityServiceExt.insertAttachCache(attachCacheDto,attType);
				}
				Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				response.setContentType("text/html;charset=UTF-8"); 
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				evaluated = studentDto;
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList2 = selfAppManagerExt.selectSelfAppraise(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String two_type = queryMap.get("evaluateType") + "";
					List<AapprasialCacheDto> appList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, two_type, "1", userDto.getUsername(),AapprasialCacheDto.class);
					appraisalList2 = new ArrayList<AppraisalDto>();
					if (appList != null) {
						for (AapprasialCacheDto dto : appList) {
							AppraisalDto appraisalDto = new AppraisalDto();
							appraisalDto.setAppraisaltypeid(Integer.valueOf(dto.getAppraisaltypeid()));
							appraisalDto.setAppraiserrid("".equals(dto.getAppraiserrid())?0:Integer.valueOf(dto.getAppraiserrid()));
							appraisalDto.setAppraser(dto.getAppraser());
							appraisalDto.setAppraseridentify(Integer.valueOf(dto.getAppraseridentify()));
							appraisalDto.setApprasial(dto.getApprasial());
							appraisalDto.setApprasialid(dto.getApprasialid());
							appraisalDto.setCmis30id("".equals(dto.getCmis30id())?0:Integer.valueOf(dto.getCmis30id()));
							appraisalDto.setDiscode("".equals(dto.getDiscode())?0:Integer.valueOf(dto.getDiscode()));
							appraisalDto.setEduid(dto.getEdu_id());
							appraisalDto.setPartId(dto.getPartid()==null?0:Integer.valueOf(dto.getPartid()));
							appraisalDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
							appraisalDto.setSigndate(StringToDatexie(dto.getSigndate()));
							appraisalDto.setStudentid(dto.getStudentid());
							List<AttachDto> list = new ArrayList<AttachDto>();
							List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getApprasialid(), "A_ATTACH",AattachCacheDto.class);
							if (cacheList != null) {
								for (AattachCacheDto dtos : cacheList) {
									AttachDto attachDto = new AttachDto();
									attachDto.setAttachid(Integer.valueOf(dtos.getAttachid()));
									attachDto.setAttachtype(Integer.valueOf(dtos.getAttachtype()));
									attachDto.setAttachtypeid(Integer.valueOf(dtos.getAttachtypeid()));
									attachDto.setCmis30id(dtos.getCmis30id());
									attachDto.setDiscode(dtos.getDiscode());
									attachDto.setFilename(dtos.getAttachname());
									attachDto.setFilepath(dtos.getAttachpath());
									attachDto.setImage(Integer.valueOf(dtos.getImage()));
									attachDto.setProcessid(dtos.getProcessid()==null?0:Integer.valueOf(dtos.getProcessid()));
									attachDto.setWorkid(dtos.getWorkid()==null?0:Integer.valueOf(dtos.getWorkid()));
									list.add(attachDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachDto app11 = (AttachDto) app1;
											AttachDto app22 = (AttachDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							appraisalDto.setAttachListForFile(list);
							appraisalList2.add(appraisalDto);
						}
					}
				}
				Map.put("list2", appraisalList2);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			}catch(ManagerException ex){
				try {
					response.getWriter().write("##");
				} catch (IOException e) {
				}
				logger.error("saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			}  catch (IOException e) {
				try {
					response.getWriter().write("##");
				} catch (IOException e1) {
				}
				logger.error("saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
		return null;
	}
	
	/**
	 * 保存个性发展过程与特长
	 * 
	 * @param request
	 * @return
	 */
	public void insertSelfApp(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			AppraisalDto appraisal = new AppraisalDto();
			String rtype = "";
			appraisal.setStudentid(studentDto.getStudentid());// 被评价人
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType1));// 评价类别
				appraisal.setAppraseridentify(1);
				rtype = evaluateType1;
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType2));// 评价类别
				rtype = evaluateType2;
				appraisal.setAppraseridentify(1);
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType3));// 评价类别
				appraisal.setAppraseridentify(Integer.valueOf(personid));
				rtype = evaluateType3;
			}
			appraisal.setSemesterid(Integer.valueOf(termId));
			appraisal.setAppraser(studentDto.getName());
			appraisal.setApprasial(apprasial);// 评价内容--界面
			appraisal.setSigndate(StringToDate(signDate));// 时间--界面
			appraisal.setEduid(studentDto.getEduid());
			appraisal.setDiscode(studentDto.getDiscode());
			appraisal.setAppraiserrid(Integer.valueOf(studentDto.getEduid()));
			appraisal.setCmis30id(Integer.valueOf(studentDto.getCmis30id()));
			AttachDto attachDto = new AttachDto();
			attachDto.setFilename("name");
			attachDto.setFilepath("path");
			attachDto.setAttachtype(2);
			attachDto.setImage(1);
			List<AttachDto> attachs = new ArrayList<AttachDto>();
			attachs.add(attachDto);
			String columnName = "";
			switch(choicenum){
			case Constant.PAGE_XXQKSDW:
				columnName="新学期伊始的我";
				break;
			case Constant.PAGE_XQJSSDW:
				columnName="学期结束时的我";
				break;
			case Constant.PAGE_SXDD:
				columnName="思想道德";
				break;
			case Constant.PAGE_XYCJ:
				columnName="学业成就";
				break;
			case Constant.PAGE_HZJL:
				columnName="合作与交流";
				break;
			case Constant.PAGE_YDYJK:
				columnName="运动与健康";
				break;
			case Constant.PAGE_SMYBX:
				columnName="审美与表现";
				break;
			case Constant.PAGE_ZHSJHD:
				columnName="综合实践活动";
				break;
			case Constant.PAGE_GXFZ:
				columnName="个性发展";
				break;
			}
			if ("0".equals(isStartAppraiseCache)) {
				rpID = personalityServiceExt.insertSelfApp(appraisal, attachs);
			} else if ("1".equals(isStartAppraiseCache)) {
				AapprasialCacheDto apprasialCacheDto = new AapprasialCacheDto();
				apprasialCacheDto.setSemesterid(termId);
				apprasialCacheDto.setAppraser(studentDto.getName());
				apprasialCacheDto.setApprasial(apprasial);// 评价内容--界面
				apprasialCacheDto.setSigndate(signDate==null?dateToString(new Date()):signDate);// 时间--界面
				apprasialCacheDto.setEdu_id(studentDto.getEduid());
				apprasialCacheDto.setDiscode(studentDto.getDiscode()+"");
				apprasialCacheDto.setAppraiserrid(studentDto.getEduid());
				apprasialCacheDto.setCmis30id(studentDto.getCmis30id());
				apprasialCacheDto.setAppraseridentify(appraisal.getAppraseridentify() + "");
				apprasialCacheDto.setAppraisaltypeid(rtype);
				rpID = personalityServiceExt.insertSelfAppCache(apprasialCacheDto);
			}
			latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), rtype, rpID, "个性发展", userDto.getStudentName(), new Date());
		/*	Map<String,Object> queryMap = new HashMap<String,Object>();
			Map<String,Object> Map = new HashMap<String,Object>();
			response.setContentType("text/html;charset=UTF-8"); 
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			Map.put("rpID", rpID);
			evaluated = studentDto;
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
			}
			appraisalList2 = selfAppManagerExt.selectSelfAppraise(queryMap);
			Map.put("list2", appraisalList2);
			JSONArray json = JSONArray.fromObject(Map,jsonConfig);*/
			response.getWriter().write(rpID);
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error("insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doSave(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			List list = new ArrayList();
			list.add(developmentrd1);
			list.add(developmentrd2);
			list.add(developmentrd3);
			if(list.size()!=0 ){
				for(int i=1;i<=3;i++){
					PersonalityDto personality = new PersonalityDto();
					personality.setCmis30id(studentDto.getCmis30id());
					personality.setDevelopmentrd(list.get(i-1).toString());
					personality.setDiscode(studentDto.getDiscode()+"");
					personality.setEdu_id(studentDto.getEduid());
					personality.setIndexid(i+"");
					personality.setSemesterid(termId);
					personality.setStudentid(studentDto.getStudentid());
					personality.setSigndate(StringToDate(signdate));
					if ("0".equals(isStartAppraiseCache)) {
						personalityServiceExt.insertPersonality(personality);
					} else if ("1".equals(isStartAppraiseCache)) {
						ApersonalityCacheDto personalityCacheDto = new ApersonalityCacheDto();
						personalityCacheDto.setCmis30id(studentDto.getCmis30id());
						personalityCacheDto.setDevelopmentrd(list.get(i-1).toString());
						personalityCacheDto.setDiscode(studentDto.getDiscode()+"");
						personalityCacheDto.setEdu_id(studentDto.getEduid());
						personalityCacheDto.setIndexid(i+"");
						personalityCacheDto.setSemesterid(termId);
						personalityCacheDto.setStudentid(studentDto.getStudentid());
						personalityCacheDto.setSigndate(signdate);
						personalityCacheDto.setAppraseridentify("1");
						personalityCacheDto.setAppraisaltypeid("7010");
						personalityServiceExt.insertPersonalityCache(personalityCacheDto);
					}
				}
			}
			Map<String,Object> queryMap = new HashMap<String,Object>();
			Map<String,Object> Map = new HashMap<String,Object>();
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			if ("0".equals(isStartAppraiseCache)) {
				personalityDtosList = personalityServiceExt.selectPersonality(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				personalityDtosCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
						termId, Constant.TYPE_GXFZ_JBQK, "1", userDto.getUsername(),ApersonalityCacheDto.class);
				personalityDtosList = new ArrayList<PersonalityDto>();
				if (personalityDtosCacheList != null) {
					for (ApersonalityCacheDto dto : personalityDtosCacheList) {
						PersonalityDto personalityDto = new PersonalityDto();
						personalityDto.setBaseid(dto.getBaseid());
						personalityDto.setAppraisaltypeid(dto.getAppraisaltypeid());
						personalityDto.setCmis30id(dto.getCmis30id());
						personalityDto.setDevelopmentrd(dto.getDevelopmentrd());
						personalityDto.setDiscode(dto.getDiscode());
						personalityDto.setEdu_id(dto.getEdu_id());
						personalityDto.setIndexid(dto.getIndexid());
						personalityDto.setSemesterid(dto.getSemesterid());
						personalityDto.setStudentid(dto.getStudentid());
						personalityDto.setSigndate(StringToDatexie(dto.getSigndate()));
						personalityDtosList.add(personalityDto);
					}
				}
				
			}
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			response.setContentType("text/html;charset=UTF-8");
			Map.put("list2", personalityDtosList);
			JSONArray json = JSONArray.fromObject(Map,jsonConfig);
			response.getWriter().write(json.toString());
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doSave(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error("doSave(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}
	}
	
	
	/**
	 * 更新信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	public void doUpdate(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			PersonalityDto personality = new PersonalityDto();
			List list = new ArrayList();
			list.add(developmentrd1);
			list.add(developmentrd2);
			list.add(developmentrd3);
			List list1 = new ArrayList();
			list1.add(id1);
			list1.add(id2);
			list1.add(id3);
			if(list1.size()!=0&&list.size()!=0){
				for(int i=1;i<=3;i++){
					if(list1.get(0)!=null&&list.get(0)!=null){
						personality.setBaseid(list1.get(i-1).toString());
						personality.setDevelopmentrd(list.get(i-1).toString());
						personality.setSigndate(StringToDate(signdate));
						if ("0".equals(isStartAppraiseCache)) {
							personalityServiceExt.updatePersonality(personality);
						} else if ("1".equals(isStartAppraiseCache)) {
							ApersonalityCacheDto personalityCacheDto = new ApersonalityCacheDto();
							personalityCacheDto.setBaseid(list1.get(i-1).toString());
							personalityCacheDto.setDevelopmentrd(list.get(i-1).toString());
							personalityCacheDto.setCmis30id(studentDto.getCmis30id());
							personalityCacheDto.setDiscode(studentDto.getDiscode()+"");
							personalityCacheDto.setEdu_id(studentDto.getEduid());
							personalityCacheDto.setIndexid(i+"");
							personalityCacheDto.setSemesterid(termId);
							personalityCacheDto.setStudentid(studentDto.getStudentid());
							personalityCacheDto.setSigndate(signdate);
							personalityCacheDto.setAppraseridentify("1");
							personalityCacheDto.setAppraisaltypeid("7010");
							personalityServiceExt.updatePersonalityCache(personalityCacheDto);
						}
					}
				}
			}
			Map<String,Object> queryMap = new HashMap<String,Object>();
			Map<String,Object> Map = new HashMap<String,Object>();
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			if ("0".equals(isStartAppraiseCache)) {
				personalityDtosList = personalityServiceExt.selectPersonality(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				personalityDtosCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
						termId, Constant.TYPE_GXFZ_JBQK, "1", userDto.getUsername(),ApersonalityCacheDto.class);
				personalityDtosList = new ArrayList<PersonalityDto>();
				if (personalityDtosCacheList != null) {
					for (ApersonalityCacheDto dto : personalityDtosCacheList) {
						PersonalityDto personalityDto = new PersonalityDto();
						personalityDto.setBaseid(dto.getBaseid());
						personalityDto.setAppraisaltypeid(dto.getAppraisaltypeid());
						personalityDto.setCmis30id(dto.getCmis30id());
						personalityDto.setDevelopmentrd(dto.getDevelopmentrd());
						personalityDto.setDiscode(dto.getDiscode());
						personalityDto.setEdu_id(dto.getEdu_id());
						personalityDto.setIndexid(dto.getIndexid());
						personalityDto.setSemesterid(dto.getSemesterid());
						personalityDto.setStudentid(dto.getStudentid());
						personalityDto.setSigndate(StringToDatexie(dto.getSigndate()));
						personalityDtosList.add(personalityDto);
					}
				}
			}
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			response.setContentType("text/html;charset=UTF-8");
			Map.put("list2", personalityDtosList);
			JSONArray json = JSONArray.fromObject(Map,jsonConfig);
			response.getWriter().write(json.toString());
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doSave(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error("doSave(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}
	}
	//上传附件
	private AttachDto uploadFile(){
		AttachDto attachDto=new AttachDto();
			//根盘符
		String rootPath = constantBean.get("mapping_root");
			//
		String realPath = constantBean.get("JLD_upload");
			//替换为当前系统支持的分隔符
		realPath = realPath.replaceAll("\\\\\\\\", File.separator);
		if(!StringUtil.isEmpty(new_uuid)&&StringUtil.isEmpty(old_uuid)&&StringUtil.isEmpty(add_uuid)){
			uuid = new_uuid;
		}else if(StringUtil.isEmpty(add_uuid)&&StringUtil.isEmpty(new_uuid)&&!StringUtil.isEmpty(old_uuid)){
			uuid = old_uuid;
		}else if(!StringUtil.isEmpty(add_uuid)&&StringUtil.isEmpty(new_uuid)){
			uuid = add_uuid;
		}
		//获取上传附件信息
		Map<String,Object> map = (Map<String, Object>) redisServiceExt.readMap(uuid);
			//上传的临时路径
		String filePath = (String) map.get("filePath");
			//临时文件的文件名
		String fileName = String.valueOf(map.get("fileName"));
			//目标文件
		String uuid=UUID.randomUUID().toString();
		String fileType=fileName.substring(fileName.lastIndexOf("."));
			try {
				//临时文件
				File srcFile = new File(rootPath+File.separator,filePath);
				//目标文件
				File desFile = new File(rootPath+trueAddress,uuid+fileType);
				if(!desFile.exists()){
					new File(desFile.getParent()).mkdirs();
					desFile.createNewFile();
				}
				NoServiceUtil.copyFile(srcFile,desFile);//copyFile();
				attachDto.setFilename((String) map.get("fileName"));
				attachDto.setFilepath(NoServiceUtil.replaceFileSeparator(trueAddress.replace("/", "\\\\")+File.separator+File.separator+uuid+fileType));
				 // 判断目录或文件是否存在  
				  if (!srcFile.exists()) { 
					  // 不存在返回 false  
				  }else {  
				        // 判断是否为文件  
				        if (srcFile.isFile()) { 
				        	deleteFile(srcFile.getPath());  
				        }
				  }
			} catch (Exception e) {
				logger.error("uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}finally{
				try {
					redisServiceExt.delete(uuid);
				} catch (ForceException ex) {
					logger.error("uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
				}
			}
			return attachDto;
		}
	/**
	 * 删除临时文件
	 * 
	 * @param request,response,session
	 * @return
	 */
	public void deleteFile(String sPath) {  
		    File file = new File(sPath);  
		    // 路径为文件且不为空则进行删除  
		    if (file.isFile() && file.exists()) {  
		        file.delete();  
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
	
	/**
	 * 字符串转日期
	 * 
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return new Date();
		}
	}
}



//为了解决list转换为Json时日期异常所需的内部类
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
