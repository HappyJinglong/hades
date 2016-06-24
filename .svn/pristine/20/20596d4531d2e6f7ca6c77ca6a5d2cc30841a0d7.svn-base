package com.flyrish.hades.webapp.action.selfappraise;

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
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.PracticeappraisalDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILearnprocessWorksServiceExt;
import com.flyrish.hades.service.ext.IPersonalityServiceExt;
import com.flyrish.hades.service.ext.IPracticesServiceExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;

public class PracticesAction extends NginxUploadAction {
	/**
	 * 综合实践活动
	 */
	@Spring
	IPracticesServiceExt practicesServiceExt;

	/**
	 * 处理自我评价业务类
	 */
	@SuppressWarnings("unused")
	@Spring
	private ISelfAppManagerExt selfAppManagerExt;

	@Spring
	IPersonalityServiceExt personalityServiceExt;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;

	/**
	 * 处理记录袋业务类
	 */
	@Spring
	ILearnprocessWorksServiceExt learnprocessWorksServiceExt;

	public String id;// 更新删除id

	public String evaluatePerson;

	public String levelcode;

	public StudentDto studentDto = new StudentDto();

	public String evaluateType;// 类型

	public String termId;// 年级学期

	public AttachDto attachDto;// 附件
	
	public String deleteType;
	
	public String updateType;

	public Date signdate;

	public String appraisalid;

	public String content;

	public String practiceid;
	
	public String apptype;
	
	public String attType;

	/**
	 * 综合实践活动
	 */
	public List<PracticesDto> practicesDtosList;
	
	public List<ApracticesCacheDto> practicesDtosCacheList;

	public String rpID;

	public String item1 = "";

	public String item2 = "";

	public String item3 = "";

	public String item4 = "";

	public String item5 = "";

	public String item6 = "";

	public String item7 = "";

	public String item8 = "";

	public String item9 = "";

	public String item10 = "";

	public String item11 = "";

	public String item12 = "";

	public String item13 = "";

	public String item14 = "";

	public String item15 = "";

	public String item16 = "";

	public String item17 = "";

	public String item18 = "";

	public String item19 = "";

	public String item20 = "";

	public String trueAddress;// 真实路径

	public String attachtypeid;//附件表外键

	@Before
	public void doBefore(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		getLoginInfo(request);
		Integer levelCode = Integer.valueOf(userDto.getLevelcode());
		studentDto.setClassid(Integer.valueOf(userDto.getClassid()));
		studentDto.setName(userDto.getStudentName());
		studentDto.setGradenum(userDto.getGradenum());
		studentDto.setStudentid(userDto.getPersonid());
		studentDto.setCmis30id(userDto.getCmis30id());
		studentDto.setDiscode(Integer.valueOf(userDto.getDiscode()));
		studentDto.setEduid(userDto.getEduId());
		studentDto.setTermtype(userDto.getTermtype());
		studentDto.setLevelcode(Integer.valueOf(userDto.getLevelcode()));
		trueAddress = constantBean.get("JLD_upload") + "/"
				+ userDto.getTermId() + "/" + userDto.getDiscode() + "/"
				+ userDto.getCmis30id() + "/" + userDto.getGradeName() + "/"
				+ userDto.getClassName();
		isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
	}

	/**
	 * 保存综合实践活动
	 * 
	 * @param request
	 * @return
	 */
	public void insertSelfPractices(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			PracticesDto practices = new PracticesDto();
			practices.setAppraisaltypeid(evaluateType);
			practices.setCmis30id(studentDto.getCmis30id());
			practices.setDiscode(studentDto.getDiscode() + "");
			practices.setEdu_id(studentDto.getEduid());
			practices.setSemesterid(termId);
			practices.setStudentid(studentDto.getStudentid());
			practices.setItem1(item1);
			practices.setItem2(item2);
			practices.setItem4(studentDto.getName());
			practices.setItem5(item5);
			practices.setItem6(item6);
			practices.setItem7(item7);
			practices.setItem8(item8);
			practices.setItem9(item9);
			String columnName = "";
			if ("0".equals(isStartAppraiseCache)) {
				rpID = practicesServiceExt.insertSelfPractices(practices);
			} else if ("1".equals(isStartAppraiseCache)) {
				ApracticesCacheDto practicesCacheDto = new ApracticesCacheDto();
				practicesCacheDto.setAppraisaltypeid(evaluateType);
				practicesCacheDto.setCmis30id(studentDto.getCmis30id());
				practicesCacheDto.setDiscode(studentDto.getDiscode() + "");
				practicesCacheDto.setEdu_id(studentDto.getEduid());
				practicesCacheDto.setSemesterid(termId);
				practicesCacheDto.setStudentid(studentDto.getStudentid());
				practicesCacheDto.setItem1(item1);
				practicesCacheDto.setItem2(item2);
				practicesCacheDto.setItem4(studentDto.getName());
				practicesCacheDto.setItem5(item5);
				practicesCacheDto.setItem6(item6);
				practicesCacheDto.setItem7(item7);
				practicesCacheDto.setItem8(item8);
				practicesCacheDto.setItem9(item9);
				practicesCacheDto.setAppraseridentify("1");
				practicesCacheDto.setAppraiserrid(studentDto.getEduid());
				rpID = practicesServiceExt.insertSelfPracticesCache(practicesCacheDto);
			}
			Integer  evaluateTypeInt=NestUtil.isEmpty(evaluateType)?0:Integer.parseInt(evaluateType);
			switch(evaluateTypeInt){
			case 9010:
				columnName="研究性学习";
				break;
			case 9020:
				columnName="社区服务";
				break;
			case 9030:
				columnName="社会实践活动";
				break;

			}
			latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), evaluateType, rpID, columnName, userDto.getStudentName(), new Date());
			response.getWriter().write(rpID);
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error(
					"insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
		}
	}

	/**
	 * 保存综合实践自我评价
	 * 
	 * @param request
	 * @return
	 */
	public void insertSelfPracticeappraisal(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			PracticeappraisalDto practiceappraisal = new PracticeappraisalDto();
			practiceappraisal.setContent(content);
			practiceappraisal.setPracticeid(practiceid);
			/* practiceappraisal.setSigndate(signdate); */
			practiceappraisal.setSigner(studentDto.getName());
			if ("0".equals(isStartAppraiseCache)) {
				rpID = practicesServiceExt.insertSelfPracticeappraisal(practiceappraisal);
			} else if ("1".equals(isStartAppraiseCache)) {
				ApracticeappraisalCacheDto practiceappraisalCacheDto = new ApracticeappraisalCacheDto();
				practiceappraisalCacheDto.setContent(content);
				practiceappraisalCacheDto.setPracticeid(practiceid);
				/* practiceappraisalCacheDto.setSigndate(signdate); */
				practiceappraisalCacheDto.setSigner(studentDto.getName());
				rpID = practicesServiceExt.insertSelfPracticeappraisalCache(practiceappraisalCacheDto,attType);
			}
			String columnName = "";
			Integer  apptypeInt=NestUtil.isEmpty(apptype)?0:Integer.parseInt(apptype);
			switch(apptypeInt){
			case 9010001:
				columnName="研究性学习";
				break;
			case 9020001:
				columnName="社区服务";
				break;
			case 9030001:
				columnName="社会实践活动";
				break;

			}
			latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), apptype, rpID, columnName, userDto.getStudentName(), new Date());
			response.getWriter().write(rpID);
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error(
					"insertSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
		}
	}

	/**
	 * 更新信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	public Object doUpdatePractices(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			PracticesDto practices = new PracticesDto();
			practices.setPracticeid(id);
			practices.setItem1(item1);
			practices.setItem2(item2);
			practices.setItem4(item4);
			practices.setItem5(item5);
			practices.setItem6(item6);
			practices.setItem7(item7);
			practices.setItem8(item8);
			practices.setItem9(item9);
			if (practices != null) {
				if ("0".equals(isStartAppraiseCache)) {
					practicesServiceExt.updateSelfPractices(practices);
				} else if ("1".equals(isStartAppraiseCache)) {
					ApracticesCacheDto practicesCacheDto = new ApracticesCacheDto();
					practicesCacheDto.setPracticeid(id);
					practicesCacheDto.setItem1(item1);
					practicesCacheDto.setItem2(item2);
					practicesCacheDto.setItem4(item4);
					practicesCacheDto.setItem5(item5);
					practicesCacheDto.setItem6(item6);
					practicesCacheDto.setItem7(item7);
					practicesCacheDto.setItem8(item8);
					practicesCacheDto.setItem9(item9);
					practicesCacheDto.setEdu_id(studentDto.getEduid());
					practicesCacheDto.setAppraiserrid(studentDto.getEduid());
					practicesCacheDto.setAppraseridentify("1");
					practicesCacheDto.setAppraisaltypeid(updateType);
					practicesCacheDto.setSemesterid(termId);
					practicesServiceExt.updateSelfPracticesCache(practicesCacheDto);
				}
				String columnName = "";
				Integer  updateTypeInt=NestUtil.isEmpty(updateType)?0:Integer.parseInt(updateType);
				switch(updateTypeInt){
				case 9010:
					columnName="研究性学习";
					break;
				case 9020:
					columnName="社区服务";
					break;
				case 9030:
					columnName="社会实践活动";
					break;

				}
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), updateType, id, columnName, userDto.getStudentName(), new Date());
				
			}
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"doUpdataSelfProcess(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		}
		return null;
	}

	/**
	 * 更新信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	public Object doUpdatePracticeappraisal(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			PracticeappraisalDto practiceappraisal = new PracticeappraisalDto();
			practiceappraisal.setAppraisalid(appraisalid);
			practiceappraisal.setPracticeid(practiceid);
			practiceappraisal.setContent(content);
			/* practiceappraisal.setSigndate(signdate); */
			if (practiceappraisal != null) {
				if ("0".equals(isStartAppraiseCache)) {
					practicesServiceExt.updateSelfPracticeappraisal(practiceappraisal);
				} else if ("1".equals(isStartAppraiseCache)) {
					ApracticeappraisalCacheDto practiceappraisalCacheDto = new ApracticeappraisalCacheDto();
					practiceappraisalCacheDto.setPracticeid(practiceid);
					practiceappraisalCacheDto.setAppraisalid(appraisalid);
					practiceappraisalCacheDto.setContent(content);
					/* practiceappraisalCacheDto.setSigndate(signdate); */
					practicesServiceExt.updateSelfPracticeappraisalCache(practiceappraisalCacheDto,updateType);
				}
				String columnName = "";
				String appType = "";
				Integer  updateTypeInt=NestUtil.isEmpty(updateType)?0:Integer.parseInt(updateType);
				switch(updateTypeInt){
				case 9010:
					columnName="研究性学习";
					appType = "9010001";
					break;
				case 9020:
					columnName="社区服务";
					appType = "9020001";
					break;
				case 9030:
					columnName="社会实践活动";
					appType = "9030001";
					break;

				}
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), appType, id, columnName, userDto.getStudentName(), new Date());
			}
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"doUpdataSelfProcess(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		}
		return null;
	}

	/**
	 * 返回文件名与文件路径
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Object saveFile(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			attachDto = uploadFile();
			attachDto.setAttachtype(3);
			attachDto.setImage(1);
			attachDto.setCmis30id(userDto.getCmis30id());
			attachDto.setDiscode(userDto.getDiscode());
			if (attachDto != null && !StringUtil.isEmpty(rpID)) {
				if ("0".equals(isStartAppraiseCache)) {
					personalityServiceExt.insertAttach(attachDto, rpID);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachtype("3");
					attachCacheDto.setImage("1");
					attachCacheDto.setCmis30id(userDto.getCmis30id());
					attachCacheDto.setDiscode(userDto.getDiscode());
					attachCacheDto.setAttachtypeid(rpID);
					attachCacheDto.setAttachname(attachDto.getFilename());
					attachCacheDto.setAttachpath(attachDto.getFilepath());
					personalityServiceExt.insertAttachCache(attachCacheDto,evaluateType);
				}
				Map<String, Object> queryMap = new HashMap<String, Object>();
				Map<String, Object> Map = new HashMap<String, Object>();
				response.setContentType("text/html;charset=UTF-8");
				JsonConfig jsonConfig = new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor = new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class,
						datejsonvalueprocessor);
				queryMap.put("evaluateid", studentDto.getStudentid());
				queryMap.put("termId", termId);
				queryMap.put("cmis30id", studentDto.getCmis30id());
				queryMap.put("discode", studentDto.getDiscode());
				if (!("1".equals(evaluateType))
						&& !StringUtil.isEmpty(evaluateType)) {
					queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				}
				if ("0".equals(isStartAppraiseCache)) {
					practicesDtosList = practicesServiceExt.selectSelfPractices(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					practicesDtosCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, evaluateType, "1", userDto.getUsername(),ApracticesCacheDto.class);
					practicesDtosList = new ArrayList<PracticesDto>();
					if (practicesDtosCacheList != null) {
						for (ApracticesCacheDto dto : practicesDtosCacheList) {
							PracticesDto practicesDto = new PracticesDto();
							practicesDto.setPracticeid(dto.getPracticeid());
							practicesDto.setSemesterid(dto.getSemesterid());
							practicesDto.setDiscode(dto.getDiscode());
							practicesDto.setCmis30id(dto.getCmis30id());
							practicesDto.setPartid(dto.getPartid());
							practicesDto.setStudentid(dto.getStudentid());
							practicesDto.setAppraisaltypeid(dto.getAppraisaltypeid());
							practicesDto.setItem1(dto.getItem1());
							practicesDto.setItem2(dto.getItem2());
							practicesDto.setItem3(dto.getItem3());
							practicesDto.setItem4(dto.getItem4());
							practicesDto.setItem5(dto.getItem5());
							practicesDto.setItem6(dto.getItem6());
							practicesDto.setItem7(dto.getItem7());
							practicesDto.setItem8(dto.getItem8());
							practicesDto.setItem9(dto.getItem9());
							practicesDto.setItem10(dto.getItem10());
							practicesDto.setItem11(dto.getItem11());
							practicesDto.setItem12(dto.getItem12());
							practicesDto.setItem13(dto.getItem13());
							practicesDto.setItem14(dto.getItem14());
							practicesDto.setItem15(dto.getItem15());
							practicesDto.setItem16(dto.getItem16());
							practicesDto.setItem17(dto.getItem17());
							practicesDto.setItem18(dto.getItem18());
							practicesDto.setItem19(dto.getItem19());
							practicesDto.setItem20(dto.getItem20());
							practicesDto.setEdu_id(dto.getEdu_id());
							List<AttachDto> list = new ArrayList<AttachDto>();
							List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getPracticeid(), "A_ATTACH",AattachCacheDto.class);
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
							practicesDto.setAttachListForFile(list);
							List<PracticeappraisalDto> praList = new ArrayList<PracticeappraisalDto>();
							List<ApracticeappraisalCacheDto> praCacheList = latestEvaluationRecordExt.queryChildrenObjectListInCache(dto.getPracticeid(), "a_practiceappraisal",ApracticeappraisalCacheDto.class);
							if (praCacheList != null) {
								for (ApracticeappraisalCacheDto praDtos : praCacheList) {
									PracticeappraisalDto praDto = new PracticeappraisalDto();
									praDto.setAppraisalid(praDtos.getAppraisalid());
									praDto.setContent(praDtos.getContent());
									praDto.setPracticeid(praDtos.getPracticeid());
									praDto.setSigndate(StringToDatexie(praDtos.getSigndate()));
									praDto.setSigner(praDtos.getSigner());
									praList.add(praDto);
								}
								Collections.sort(praList,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											PracticeappraisalDto app11 = (PracticeappraisalDto) app1;
											PracticeappraisalDto app22 = (PracticeappraisalDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							practicesDto.setPracticeappraisalList(praList);
							practicesDtosList.add(practicesDto);
					}
					}
				}
				Map.put("list2", practicesDtosList);
				JSONArray json = JSONArray.fromObject(Map, jsonConfig);
				response.getWriter().write(json.toString());
			}
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException e1) {
			}
			logger.error(
					"saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
		}

		return null;
	}

	/**
	 * 删除综合实践活动和附件
	 * 
	 * @param request
	 *            ,response,session
	 * @return
	 */
	public void deleteSelfPractices(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			if (NestUtil.isNotEmpty(id)) {
				if ("0".equals(isStartAppraiseCache)) {
					practicesServiceExt.deleteSelfPractices(id);
					latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), deleteType, id);
				} else if ("1".equals(isStartAppraiseCache)) {
					ApracticesCacheDto practicesCacheDto = new ApracticesCacheDto();
					practicesCacheDto.setAppraisaltypeid(deleteType);
					practicesCacheDto.setEdu_id(studentDto.getEduid());
					practicesCacheDto.setSemesterid(termId);
					practicesCacheDto.setAppraseridentify("1");
					practicesServiceExt.deleteSelfPracticesCache(userDto.getUsername(), deleteType, id,practicesCacheDto);
				}
			}
		} catch (Exception e) {
			logger.error(
					"deleteSelfPractices(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error(
						"deleteSelfPractices(HttpServletRequest,HttpServletResponse,HttpSession)",
						e);
			}
		}
	}

	/**
	 * 删除综合实践活动和附件
	 * 
	 * @param request
	 *            ,response,session
	 * @return
	 */
	public void deleteSelfPracticeappraisal(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			if (NestUtil.isNotEmpty(appraisalid)) {
				if ("0".equals(isStartAppraiseCache)) {
					practicesServiceExt.deleteSelfPracticeappraisal(appraisalid);
				} else if ("1".equals(isStartAppraiseCache)) {
					practicesServiceExt.deleteSelfPracticeappraisalCache(appraisalid,practiceid,deleteType);
				}
				latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), deleteType, id);
			}
		} catch (Exception e) {
			logger.error(
					"deleteSelfPracticeappraisal(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error(
						"deleteSelfPracticeappraisal(HttpServletRequest,HttpServletResponse,HttpSession)",
						e);
			}
		}
	}

	/**
	 * 删除附件
	 * 
	 * @param request
	 *            ,response,session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void deleteSelfAttach(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			if (NestUtil.isNotEmpty(id)) {
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.deleteAttach(id);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachid(id);
					attachCacheDto.setAttachtypeid(attachtypeid);
					learnprocessWorksServiceExt.deleteAttachCache(attachCacheDto,evaluateType);
				}
				Map<String, Object> queryMap = new HashMap<String, Object>();
				Map<String, Object> Map = new HashMap<String, Object>();
				response.setContentType("text/html;charset=UTF-8");
				JsonConfig jsonConfig = new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor = new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class,
						datejsonvalueprocessor);
				queryMap.put("evaluateid", studentDto.getStudentid());
				queryMap.put("termId", termId);
				queryMap.put("cmis30id", studentDto.getCmis30id());
				queryMap.put("discode", studentDto.getDiscode());
				if (!("1".equals(evaluateType))
						&& !StringUtil.isEmpty(evaluateType)) {
					queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				}
				if ("0".equals(isStartAppraiseCache)) {
					practicesDtosList = practicesServiceExt.selectSelfPractices(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					practicesDtosCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, evaluateType, "1", userDto.getUsername(),ApracticesCacheDto.class);
					practicesDtosList = new ArrayList<PracticesDto>();
					for (ApracticesCacheDto dto : practicesDtosCacheList) {
							PracticesDto practicesDto = new PracticesDto();
							practicesDto.setPracticeid(dto.getPracticeid());
							practicesDto.setSemesterid(dto.getSemesterid());
							practicesDto.setDiscode(dto.getDiscode());
							practicesDto.setCmis30id(dto.getCmis30id());
							practicesDto.setPartid(dto.getPartid());
							practicesDto.setStudentid(dto.getStudentid());
							practicesDto.setAppraisaltypeid(dto.getAppraisaltypeid());
							practicesDto.setItem1(dto.getItem1());
							practicesDto.setItem2(dto.getItem2());
							practicesDto.setItem3(dto.getItem3());
							practicesDto.setItem4(dto.getItem4());
							practicesDto.setItem5(dto.getItem5());
							practicesDto.setItem6(dto.getItem6());
							practicesDto.setItem7(dto.getItem7());
							practicesDto.setItem8(dto.getItem8());
							practicesDto.setItem9(dto.getItem9());
							practicesDto.setItem10(dto.getItem10());
							practicesDto.setItem11(dto.getItem11());
							practicesDto.setItem12(dto.getItem12());
							practicesDto.setItem13(dto.getItem13());
							practicesDto.setItem14(dto.getItem14());
							practicesDto.setItem15(dto.getItem15());
							practicesDto.setItem16(dto.getItem16());
							practicesDto.setItem17(dto.getItem17());
							practicesDto.setItem18(dto.getItem18());
							practicesDto.setItem19(dto.getItem19());
							practicesDto.setItem20(dto.getItem20());
							practicesDto.setEdu_id(dto.getEdu_id());
							List<AttachDto> list = new ArrayList<AttachDto>();
							List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getPracticeid(), "A_ATTACH",AattachCacheDto.class);
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
							practicesDto.setAttachListForFile(list);
							List<PracticeappraisalDto> praList = new ArrayList<PracticeappraisalDto>();
							List<ApracticeappraisalCacheDto> praCacheList = latestEvaluationRecordExt.queryChildrenObjectListInCache(dto.getPracticeid(), "a_practiceappraisal",ApracticeappraisalCacheDto.class);
							if (praCacheList != null) {
								for (ApracticeappraisalCacheDto praDtos : praCacheList) {
									PracticeappraisalDto praDto = new PracticeappraisalDto();
									praDto.setAppraisalid(praDtos.getAppraisalid());
									praDto.setContent(praDtos.getContent());
									praDto.setPracticeid(praDtos.getPracticeid());
									praDto.setSigndate(StringToDatexie(praDtos.getSigndate()));
									praDto.setSigner(praDtos.getSigner());
									praList.add(praDto);
								}
								Collections.sort(praList,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											PracticeappraisalDto app11 = (PracticeappraisalDto) app1;
											PracticeappraisalDto app22 = (PracticeappraisalDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							practicesDto.setPracticeappraisalList(praList);
							practicesDtosList.add(practicesDto);
					}
				}
				Map.put("list2", practicesDtosList);
				JSONArray json = JSONArray.fromObject(Map, jsonConfig);
				response.getWriter().write(json.toString());
			}
		} catch (Exception e) {
			logger.error(
					"deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error(
						"deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",
						e);
			}
		}
	}

	// 上传附件
	private AttachDto uploadFile() {
		AttachDto attachDto = new AttachDto();
		// 根盘符
		String rootPath = constantBean.get("mapping_root");
		//
		String realPath = constantBean.get("JLD_upload");
		// 替换为当前系统支持的分隔符
		realPath = realPath.replaceAll("\\\\\\\\", File.separator);
		// 获取上传附件信息
		Map<String, Object> map = (Map<String, Object>) redisServiceExt
				.readMap(uuid);
		// 上传的临时路径
		String filePath = (String) map.get("filePath");
		// 临时文件的文件名
		String fileName = String.valueOf(map.get("fileName"));
		// 目标文件
		String uuid = UUID.randomUUID().toString();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		try {
			// 临时文件
			File srcFile = new File(rootPath + File.separator, filePath);
			// 目标文件
			File desFile = new File(rootPath + trueAddress, uuid + fileType);
			if (!desFile.exists()) {
				new File(desFile.getParent()).mkdirs();
				desFile.createNewFile();
			}
			NoServiceUtil.copyFile(srcFile, desFile);// copyFile();
			attachDto.setFilename((String) map.get("fileName"));
			attachDto
					.setFilepath(NoServiceUtil.replaceFileSeparator(trueAddress
							.replace("/", "\\\\")
							+ File.separator
							+ File.separator + uuid + fileType));
			// 判断目录或文件是否存在
			if (!srcFile.exists()) {
				// 不存在返回 false
			} else {
				// 判断是否为文件
				if (srcFile.isFile()) {
					deleteFile(srcFile.getPath());
				}
			}
		} catch (Exception e) {
			logger.error(
					"uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
		} finally {
			try {
				redisServiceExt.delete(uuid);
			} catch (ForceException ex) {
				logger.error(
						"uploadFile(HttpServletRequest,HttpServletResponse,HttpSession)",
						ex);
			}
		}
		return attachDto;
	}

	/**
	 * 删除临时文件
	 * 
	 * @param request
	 *            ,response,session
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

	class DateJsonValueProcessor implements JsonValueProcessor {
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
						"yyyy-MM-dd");// 这里是我封装的工具,可以使用SimpleDateFormat代替，一样
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
