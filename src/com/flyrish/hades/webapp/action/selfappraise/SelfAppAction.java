package com.flyrish.hades.webapp.action.selfappraise;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.DateUtil;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.PersonalityDto;
import com.flyrish.hades.dto.PracticeappraisalDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.InValidInsertException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.IHighSchoolCacheExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILearnprocessWorksServiceExt;
import com.flyrish.hades.service.ext.IPersonalityServiceExt;
import com.flyrish.hades.service.ext.IPracticesServiceExt;
import com.flyrish.hades.service.ext.IRecordPackageManagerExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.webapp.action.NginxUploadAction;



public class SelfAppAction extends NginxUploadAction{
	/**
	 * 处理自我评价业务类
	 */
	@Spring
	private ISelfAppManagerExt selfAppManagerExt;
	/**
	 * 处理记录袋业务类
	 */
	@Spring
	IRecordPackageManagerExt recordPackageManagerExt;
	/**
	 * 处理记录袋业务类
	 */
	@Spring
	ILearnprocessWorksServiceExt learnprocessWorksServiceExt;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	/**
	 * 处理个性发展基本情况
	 */
	@Spring
	IPersonalityServiceExt personalityServiceExt;
	
	@Spring
	IBaseInforManagerExt baseInforManagerExt;
	/**
	 * 综合实践活动
	 */
	@Spring
	IPracticesServiceExt practicesServiceExt;
	/**
	 * 缓存
	 */
	@Spring
	IHighSchoolCacheExt highSchoolCacheExt;
	/**
	 * checkbox的值
	 */
	public Integer[] appraiseIds;

	/**
	 * 是否删除标志
	 */
	public String ifDelete;

	/**
	 * 提示信息语
	 */
	public String message;

	/**
	 * 自我评价列表
	 */
	public List<AppraisalDto> appraisalList1;
	public List<AapprasialCacheDto> appraisalCacheList1;//缓存
	public String updateType;
	/**
	 * 自我评价列表
	 */
	public List<AppraisalDto> appraisalList2;
	public List<AapprasialCacheDto> appraisalCacheList2;//缓存
	/**
	 * 自我评价列表
	 */
	public List<AppraisalDto> appraisalList3;
	public List<AapprasialCacheDto> appraisalCacheList3;//缓存
	/**
	 * 记录袋
	 */
	public List<RecordPackageDto> packageList;
	public List<ArecordpackageCacheDto> packageCacheList;
	/**
	 * 学业成就
	 */
	public List<LearnprocessWorksDto> learnprocessWorksList;
	public List<AlearnprocessWorksCacheDto> learnprocessWorksCacheList;
	/**
	 * 个性发展基本情况
	 */
	public List<PersonalityDto> personalityDtosList;
	public List<ApersonalityCacheDto> personalityDtosCacheList;
	/**
	 * 综合实践活动
	 */
	public List<PracticesDto> practicesDtosList;
	public List<ApracticesCacheDto> practicesDtosCacheList;
	/**
	 * 是否显示保存按钮.
	 */
	public int readonly = 0;

	/**
	 * 被评价人ID.
	 */
	public String evaluatedPersonID;
	/**
	 * 被评价人姓名.
	 */
	public String evaluatePersonName;
	/**
	 * 对象
	 */
	public StudentDto evaluated;


	public String rpID;

	// 年级学期
	public String termId;
	
	// 学期前台传的学期
	public String termId1;
	
	// 评语
	public String apprasial;

	// 评价人
	public String appraser;
	
	//评价类型
	public String evaluateType1;
	//评价类型
	public String evaluateType2;
	//记录袋评价类型
	public String evaluateType3;
	//记录袋评价类型
	public String evaluateType;
	// 评价时间
	public String signDate;
	//当前服务端
	public String date;
	
	public String deleteType;
	
	public Integer levelcode;
	
	public StudentDto studentDto = new StudentDto();
	
	public AppraisalDto appraisalDto;
	
	public int choicenum;//跳转页面编号
	
	public String classid;//学生id
	
	public String subjectName;//学科科目
	
	public String id;
	
	public String attachid;//附件id
	
	public String personid;//提供人
	
/*	public static String insertId;*/
	
	public String inid;
	
	public String newId;
	
	public String newId2;
	
	public String nowDate;
	
	public String apprasialid;
	
	public String recordid;
	
	public String workid;
	
	public String attType;
	
	public static Map<String,StudentDto> insertMap = new HashMap<String,StudentDto>();
	
	@Before
	public Object doBefore(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:levelCode;
		/*Integer studentId=(Integer)session.getAttribute("studentid");
		String studentid = studentId==null?null:String.valueOf(studentId);
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("studentid", studentid);
		studentDto = selfAppManagerExt.selectStudent(queryMap);*/
		studentDto.setClassid(Integer.valueOf(userDto.getClassid()));
		studentDto.setName(userDto.getStudentName());
		studentDto.setGradenum(userDto.getGradenum());
		studentDto.setStudentid(userDto.getPersonid());
		studentDto.setCmis30id(userDto.getCmis30id());
		studentDto.setDiscode(Integer.valueOf(userDto.getDiscode()));
		studentDto.setEduid(userDto.getEduId());
		studentDto.setTermtype(userDto.getTermtype());
		studentDto.setLevelcode(Integer.valueOf(userDto.getLevelcode()));
		classid = userDto.getClassid();
		evaluatePersonName = userDto.getStudentName();
		isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
		return null;
	}
	
	
	/**
	 * 显示默认页
	 * 
	 * @param request,response,session
	 * @return
	 * @throws ParseException 
	 */
	@DefaultAction
	@SuppressWarnings("unchecked")
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		nowDate = df.format(new Date());
		Map<String,Object> queryMap = new HashMap<String,Object>();
			evaluatePersonName = studentDto.getName();
			termId = studentDto.getGradenum()+studentDto.getTermtype();
			if(termId1!=termId&&termId1!=null){
				termId = termId1;
			}
			/*levelcode = studentDto.getLevelcode();*/
			evaluated = studentDto;
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			switch(choicenum){
			case 1001://新学期开始的我
				evaluateType1 = Constant.TYPE_START_ZWPJ;
				evaluateType2 = Constant.TYPE_START_WDFZMB;
				queryMap.put("appraseridentify","1");
				break;
			case 1002://学期结束时的我
				evaluateType1 = Constant.TYPE_END_ZWPJ;
				evaluateType2 = Constant.TYPE_END_WDFZMB;
				queryMap.put("appraseridentify","1");
				break;
			case 1003://思想道德
				evaluateType1 = Constant.TYPE_SX_ZWPJ;
				evaluateType2 = Constant.TYPE_SXJLD;
				break;
			case 1004://学业成就
				evaluateType1 = Constant.TYPE_XY_ZWPJ;
				String evaluateTypes = Constant.TYPE_XY_GCJL;
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksList = learnprocessWorksServiceExt.selectLearnprocessWorks(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					learnprocessWorksCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
							termId, evaluateTypes, "1", userDto.getUsername(),AlearnprocessWorksCacheDto.class);
					learnprocessWorksList = new ArrayList<LearnprocessWorksDto>();
					if (learnprocessWorksCacheList != null) {
						for (AlearnprocessWorksCacheDto dto :learnprocessWorksCacheList) {
							LearnprocessWorksDto learnprocessWorksDto = new LearnprocessWorksDto();
							learnprocessWorksDto.setWorkid(dto.getWorkid());
							learnprocessWorksDto.setSubject(dto.getSubject());
							learnprocessWorksDto.setProcessdesc(dto.getProcessdesc());
							learnprocessWorksDto.setSemesterid(dto.getSemesterid());
							learnprocessWorksDto.setStudentid(dto.getStudentid());
							learnprocessWorksDto.setEdu_id(dto.getEdu_id());
							learnprocessWorksDto.setDiscode(dto.getDiscode());
							learnprocessWorksDto.setCmis30id(dto.getCmis30id());
							learnprocessWorksDto.setSigndate(StringToDatexie(dto.getSigndate()));
							List<AttachDto> list = new ArrayList<AttachDto>();
							List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getWorkid(), "A_ATTACH",AattachCacheDto.class);
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
							learnprocessWorksDto.setAttachListForFile(list);
							learnprocessWorksList.add(learnprocessWorksDto);
						}
					}
				}
				Collections.sort(learnprocessWorksList,new Comparator(){
					public int compare(Object app1, Object app2) {
						try{
							LearnprocessWorksDto app11 = (LearnprocessWorksDto) app1;
							LearnprocessWorksDto app22 = (LearnprocessWorksDto) app2;
							return app11.compareTo(app22);                         
						}catch(Exception e){
							e.printStackTrace();
						}         
						return 1;                        
					}
				});
				break;
			case 1005://合作交流
				evaluateType1 = Constant.TYPE_HZ_ZWPJ;
				evaluateType2 = Constant.TYPE_HZ_JLD;
				break;
			case 1006://运动与健康
				evaluateType1 = Constant.TYPE_YDJK_ZWPJ;
				break;
			case 1007://审美与表现
				evaluateType1 = Constant.TYPE_SMYBX_ZWPJ;
				evaluateType2 = Constant.TYPE_SMYBX_JLD;
				break;
			case 1008://综合实践活动
				evaluateType1 = Constant.TYPE_YJXX;
				evaluateType2 = Constant.TYPE_SQFU;
				evaluateType3 = Constant.TYPE_SHSJHD;
				queryMap.put("evaluateType",evaluateType);
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
				Collections.sort( practicesDtosList,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
					    	PracticesDto app11 = (PracticesDto) app1;
					    	PracticesDto app22 = (PracticesDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
				break;
			case 1009://个性发展
				evaluateType1 = Constant.TYPE_GXFZ_ZWPJ;
				evaluateType2 = Constant.TYPE_GXFZGC;
				evaluateType3 = Constant.TYPE_GXFZ_CGZS;
				String evaluateType4 = Constant.TYPE_GXFZ_JBQK;
				if ("0".equals(isStartAppraiseCache)) {
						personalityDtosList = personalityServiceExt.selectPersonality(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					personalityDtosCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
							termId, evaluateType4, "1", userDto.getUsername(),ApersonalityCacheDto.class);
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
				Collections.sort( personalityDtosList,new Comparator(){
			        public int compare(Object app1, Object app2) {
			            try{
			            	PersonalityDto app11 = (PersonalityDto) app1;
			            	PersonalityDto app22 = (PersonalityDto) app2;
			    			return app11.compareTo(app22);                         
			            }catch(Exception e){
			                e.printStackTrace();
			            }         
			            return 1;                        
			        }
				});
				break;
			default:
		}
			if(StringUtil.isEmpty(evaluateType)){
				if(!StringUtil.isEmpty(evaluateType1)&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
					if(appraisalList1.size()==0){
						newId = baseInforManagerExt.queryProKey("A_APPRASIAL"); 
					}
				} else if ("1".equals(isStartAppraiseCache)){
						appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
								termId, evaluateType1, "1", userDto.getUsername(),AapprasialCacheDto.class);
						appraisalList1 = new ArrayList<AppraisalDto>();
						if (appraisalCacheList1 == null) {
							newId = baseInforManagerExt.queryProKey("A_APPRASIAL"); 
						} else if (appraisalCacheList1 != null) {
							for (AapprasialCacheDto dto : appraisalCacheList1) {
								AppraisalDto appraisalDto = new AppraisalDto();
								appraisalDto.setAppraisaltypeid(Integer.valueOf(dto.getAppraisaltypeid()));
								appraisalDto.setAppraiserrid(NestUtil.isEmpty(dto.getAppraiserrid())?0:Integer.valueOf(dto.getAppraiserrid()));
								appraisalDto.setAppraser(dto.getAppraser());
								appraisalDto.setAppraseridentify(Integer.valueOf(dto.getAppraseridentify()));
								appraisalDto.setApprasial(dto.getApprasial());
								appraisalDto.setApprasialid(dto.getApprasialid());
								appraisalDto.setCmis30id(NestUtil.isEmpty(dto.getCmis30id())?0:Integer.valueOf(dto.getCmis30id()));
								appraisalDto.setDiscode(NestUtil.isEmpty(dto.getDiscode())?0:Integer.valueOf(dto.getDiscode()));
								appraisalDto.setEduid(dto.getEdu_id());
								appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
								appraisalDto.setSemesterid(NestUtil.isEmpty(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
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
								appraisalList1.add(appraisalDto);
							}
						}
				}
				Collections.sort( appraisalList1,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
					    	AppraisalDto app11 = (AppraisalDto) app1;
					    	AppraisalDto app22 = (AppraisalDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
				if(!StringUtil.isEmpty(evaluateType2)&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					if(evaluateType2.equals("3030")||evaluateType2.equals("4030")||evaluateType2.equals("6030")){
						queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
						if ("0".equals(isStartAppraiseCache)) {
							packageList=recordPackageManagerExt.selectRecordpackage(queryMap);
						} else if ("1".equals(isStartAppraiseCache)) {
							packageCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, evaluateType2, "1", userDto.getUsername(),ArecordpackageCacheDto.class);
							packageList = new ArrayList<RecordPackageDto>();
							if (packageCacheList != null) {
								for (ArecordpackageCacheDto dto : packageCacheList) {
									RecordPackageDto recordPackageDto = new RecordPackageDto();
									recordPackageDto.setRecordid(NestUtil.isEmpty(dto.getRecordid())?0:Integer.valueOf(dto.getRecordid()));
									recordPackageDto.setStudentid(NestUtil.isEmpty(dto.getStudentid())?0:Integer.valueOf(dto.getStudentid()));
									recordPackageDto.setSemesterid(NestUtil.isEmpty(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
									recordPackageDto.setAppraisaltypeid(NestUtil.isEmpty(dto.getAppraisaltypeid())?0:Integer.valueOf(dto.getAppraisaltypeid()));
									recordPackageDto.setContent(dto.getContent());
									recordPackageDto.setAppraseridentify(NestUtil.isEmpty(dto.getAppraseridentify())?0:Integer.valueOf(dto.getAppraseridentify()));
									recordPackageDto.setSigner(dto.getSigner());
									recordPackageDto.setSigndate(StringToDatexie(dto.getSigndate()));
									recordPackageDto.setEdu_id(NestUtil.isEmpty(dto.getEdu_id())?0:Integer.valueOf(dto.getEdu_id()));
									recordPackageDto.setDiscode(dto.getDiscode());
									recordPackageDto.setCmis30id(NestUtil.isEmpty(dto.getCmis30id())?0:Integer.valueOf(dto.getCmis30id()));
									recordPackageDto.setPartid(dto.getPartid()==null?0:Integer.valueOf(dto.getPartid()));
									List<AttachDto> list = new ArrayList<AttachDto>();
									List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getRecordid(), "A_ATTACH",AattachCacheDto.class);
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
									recordPackageDto.setAttachListForFile(list);
									packageList.add(recordPackageDto);
								}
							}
						}
						Collections.sort( packageList,new Comparator(){
							public int compare(Object app1, Object app2) {
							    try{
							    	RecordPackageDto app11 = (RecordPackageDto) app1;
							    	RecordPackageDto app22 = (RecordPackageDto) app2;
									return app11.compareTo(app22);                         
							    }catch(Exception e){
								e.printStackTrace();
							    }         
							    return 1;                        
							}
						});
					} else {
						queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
						if ("0".equals(isStartAppraiseCache)) {
							appraisalList2 = personalityServiceExt.selectSelfAppraise(queryMap);
						} else if ("1".equals(isStartAppraiseCache)) {
							appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
									termId, evaluateType2, "1", userDto.getUsername(),AapprasialCacheDto.class);
							appraisalList2 = new ArrayList<AppraisalDto>();
							if (appraisalCacheList2 != null) {
								for (AapprasialCacheDto dto : appraisalCacheList2) {
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
									appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
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
											attachDto.setWorkid(dtos.getProcessid()==null?0:Integer.valueOf(dtos.getWorkid()));
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
						Collections.sort( appraisalList2,new Comparator(){
							public int compare(Object app1, Object app2) {
							    try{
							    	AppraisalDto app11 = (AppraisalDto) app1;
							    	AppraisalDto app22 = (AppraisalDto) app2;
									return app11.compareTo(app22);                         
							    }catch(Exception e){
								e.printStackTrace();
							    }         
							    return 1;                        
							}
						});
						if(appraisalList2.equals(null) || appraisalList2.size()==0 || "".equals(appraisalList2) || appraisalList2 == null){
							newId2 = baseInforManagerExt.queryProKey("A_APPRASIAL"); 
						}

					}
				}
				if(!StringUtil.isEmpty(evaluateType3)&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
					if ("0".equals(isStartAppraiseCache)) {
						appraisalList3 = selfAppManagerExt.selectSelfAppraise(queryMap);
					} else if ("1".equals(isStartAppraiseCache)) {
						appraisalCacheList3 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
								termId, evaluateType3, "1", userDto.getUsername(),AapprasialCacheDto.class);
						appraisalList3 = new ArrayList<AppraisalDto>();
						if (appraisalCacheList3 != null) {
							for (AapprasialCacheDto dto : appraisalCacheList3) {
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
								appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
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
								appraisalList3.add(appraisalDto);
							}
						}
					}
					Collections.sort( appraisalList3,new Comparator(){
						public int compare(Object app1, Object app2) {
						    try{
						    	AppraisalDto app11 = (AppraisalDto) app1;
						    	AppraisalDto app22 = (AppraisalDto) app2;
								return app11.compareTo(app22);                         
						    }catch(Exception e){
							e.printStackTrace();
						    }         
						    return 1;                        
						}
					});
				}
			}
			readonly = 1;
			switch(choicenum){
				case Constant.PAGE_XXQKSDW:
					return "/selfappraise/gzself_semesterstart.jsp";
				case Constant.PAGE_XQJSSDW:
					return "/selfappraise/gzself_semesterend.jsp";
				case Constant.PAGE_SXDD:
					return "/selfappraise/gzself_package.jsp";
				case Constant.PAGE_XYCJ:
					return "/selfappraise/gzself_course.jsp";
				case Constant.PAGE_HZJL:
					return "/selfappraise/gzself_package.jsp";
				case Constant.PAGE_YDYJK:
					return "/selfappraise/gzself_sports.jsp";
				case Constant.PAGE_SMYBX:
					return "/selfappraise/gzself_package.jsp";
				case Constant.PAGE_ZHSJHD:
					if(Constant.TYPE_YJXX.equals(evaluateType)){
						return "/selfappraise/gzself_yanjiu.jsp";
					}else if(Constant.TYPE_SQFU.equals(evaluateType)){
						return "/selfappraise/gzself_fuwu.jsp";
					}else if(Constant.TYPE_SHSJHD.equals(evaluateType)){
						return "/selfappraise/gzself_shijian.jsp";
					}
				case Constant.PAGE_GXFZ:
					return "/selfappraise/gzself_personality.jsp";
				default:
					return null;
			}
	}
	
	/**
	 * 增加保存自我评价页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public Object doInsertSelfEvaluation(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String insertid ="";
			AppraisalDto appraisal = new AppraisalDto();
			appraisal.setStudentid(studentDto.getStudentid());// 被评价人
			String rtype="";
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType1));// 评价类别
				rtype = evaluateType1;
				appraisal.setAppraseridentify(1);
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType2));// 评价类别
				rtype = evaluateType2;
				appraisal.setAppraseridentify(1);
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType3));// 评价类别
				rtype = evaluateType3;
				appraisal.setAppraseridentify(Integer.valueOf(personid));
			}else{
				appraisal.setAppraseridentify(1);
			}
			appraisal.setSemesterid(Integer.valueOf(termId));
			appraisal.setAppraser(studentDto.getName());
			appraisal.setApprasial(apprasial);// 评价内容--界面
			appraisal.setSigndate(StringToDate(signDate));// 时间--界面
			appraisal.setEduid(studentDto.getEduid());
			appraisal.setDiscode(studentDto.getDiscode());
			appraisal.setAppraiserrid(Integer.valueOf(studentDto.getEduid()));
			appraisal.setCmis30id(Integer.valueOf(studentDto.getCmis30id()));
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
			if(appraisal!=null){
				if ("0".equals(isStartAppraiseCache)) {
					insertid = selfAppManagerExt.insertSelfAppraisal(appraisal);
					latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), rtype, insertid, columnName, userDto.getStudentName(), new Date());
					studentDto.setInsertid(insertid);
				} else if ("1".equals(isStartAppraiseCache)) {
					AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
					appraisalCacheDto.setAppraisaltypeid(Integer.toString(appraisal.getAppraisaltypeid()));
					appraisalCacheDto.setAppraiserrid(studentDto.getEduid()==null?"":studentDto.getEduid());
					appraisalCacheDto.setAppraser(appraisal.getAppraser());
					appraisalCacheDto.setAppraseridentify(Integer.toString(appraisal.getAppraseridentify()));
					appraisalCacheDto.setApprasial(appraisal.getApprasial());
					appraisalCacheDto.setApprasialid(appraisal.getApprasialid());
					appraisalCacheDto.setCmis30id(appraisal.getCmis30id()==null?"":Integer.toString(appraisal.getCmis30id()));
					appraisalCacheDto.setDiscode(appraisal.getDiscode()==null?"":Integer.toString(appraisal.getDiscode()));
					appraisalCacheDto.setEdu_id(appraisal.getEduid());
					appraisalCacheDto.setPartid(appraisal.getPartId()==null?"":Integer.toString(appraisal.getPartId()));
					appraisalCacheDto.setSemesterid(appraisal.getSemesterid()==null?"":Integer.toString(appraisal.getSemesterid()));
					appraisalCacheDto.setSigndate(sdf.format(appraisal.getSigndate()));
					appraisalCacheDto.setStudentid(appraisal.getStudentid());
					insertid = selfAppManagerExt.insertSelfAppraisalCache(appraisalCacheDto);
					latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), rtype, insertid, columnName, userDto.getStudentName(), new Date());
					studentDto.setInsertid(insertid);
				}
			}
			Map<String,Object> queryMap = new HashMap<String,Object>();
			evaluatePersonName = studentDto.getName();
			termId = studentDto.getGradenum()+studentDto.getTermtype();
			if(termId1!=termId&&termId1!=null){
				termId = termId1;
			}
			levelcode = studentDto.getLevelcode();
			evaluated = studentDto;
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			response.setContentType("text/html;charset=UTF-8"); 
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);

			JSONArray arraylist1;
			JSONArray arraylist2;
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				queryMap.remove("evaluateType");
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			}
			/*appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
			arraylist1 = JSONArray.fromObject(appraisalList1,jsonConfig);*/
			
			response.getWriter().write(insertid);
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doInsertSelfEvaluation(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException e1) {
			}
			logger.error("doInsertSelfEvaluation(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}finally{
			 studentDto.setSaveDone(true);
			insertMap.put(studentDto.getStudentid(),studentDto);
		}
		return null;
	}
	
	
	/**
	 * 增加保存自我评价页面
	 * 
	 * @return
	 */
	public Object doInsertSelfEvaluationwith(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			AppraisalDto appraisal = new AppraisalDto();
			appraisal.setStudentid(studentDto.getStudentid());// 被评价人
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType1));// 评价类别
				appraisal.setAppraseridentify(1);
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType2));// 评价类别
				appraisal.setAppraseridentify(1);
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				appraisal.setAppraisaltypeid(Integer.parseInt(evaluateType3));// 评价类别
				appraisal.setAppraseridentify(Integer.valueOf(personid));
			}else{
				appraisal.setAppraseridentify(1);
			}
			appraisal.setApprasialid(inid);
			appraisal.setSemesterid(Integer.valueOf(termId));
			appraisal.setAppraser(studentDto.getName());
			appraisal.setApprasial(apprasial);// 评价内容--界面
			appraisal.setSigndate(StringToDate(signDate));// 时间--界面
			appraisal.setEduid(studentDto.getEduid());
			appraisal.setDiscode(studentDto.getDiscode());
			appraisal.setAppraiserrid(Integer.valueOf(studentDto.getEduid()));
			appraisal.setCmis30id(Integer.valueOf(studentDto.getCmis30id()));
			if(appraisal!=null){
				if ("0".equals(isStartAppraiseCache)) {
					selfAppManagerExt.insertSelfAppraisalwith(appraisal);
				} else if ("1".equals(isStartAppraiseCache)){
					AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
					appraisalCacheDto.setAppraisaltypeid(Integer.toString(appraisal.getAppraisaltypeid()));
					appraisalCacheDto.setAppraiserrid(studentDto.getEduid()==null?"":studentDto.getEduid());
					appraisalCacheDto.setAppraser(appraisal.getAppraser());
					appraisalCacheDto.setAppraseridentify(Integer.toString(appraisal.getAppraseridentify()));
					appraisalCacheDto.setApprasial(appraisal.getApprasial());
					appraisalCacheDto.setApprasialid(appraisal.getApprasialid());
					appraisalCacheDto.setCmis30id(appraisal.getCmis30id()==null?"":Integer.toString(appraisal.getCmis30id()));
					appraisalCacheDto.setDiscode(appraisal.getDiscode()==null?"":Integer.toString(appraisal.getDiscode()));
					appraisalCacheDto.setEdu_id(appraisal.getEduid());
					appraisalCacheDto.setPartid(appraisal.getPartId()==null?"":Integer.toString(appraisal.getPartId()));
					appraisalCacheDto.setSemesterid(appraisal.getSemesterid()==null?"":Integer.toString(appraisal.getSemesterid()));
					appraisalCacheDto.setSigndate(sdf.format(appraisal.getSigndate()));
					appraisalCacheDto.setStudentid(appraisal.getStudentid());
					selfAppManagerExt.insertSelfAppraisalwithCache(appraisalCacheDto);
				}
			}
			Map<String,Object> queryMap = new HashMap<String,Object>();
			evaluatePersonName = studentDto.getName();
			termId = studentDto.getGradenum()+studentDto.getTermtype();
			if(termId1!=termId&&termId1!=null){
				termId = termId1;
			}
			levelcode = studentDto.getLevelcode();
			evaluated = studentDto;
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			response.setContentType("text/html;charset=UTF-8"); 
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);

			JSONArray arraylist1;
			JSONArray arraylist2;
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				queryMap.remove("evaluateType");
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			}
			if ("0".equals(isStartAppraiseCache)){
				appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				String type = (String) queryMap.get("evaluateType");
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
						termId, type, "1", userDto.getUsername(),AapprasialCacheDto.class);
				appraisalList1 = new ArrayList<AppraisalDto>();
				for (AapprasialCacheDto dto : appraisalCacheList1) {
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
					appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
					appraisalDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
					appraisalDto.setSigndate(dto.getSigndate()==""?new Date():StringToDate(dto.getSigndate()));
					appraisalDto.setStudentid(dto.getStudentid());
					appraisalList1.add(appraisalDto);
				}
			}
			arraylist1 = JSONArray.fromObject(appraisalList1,jsonConfig);
			response.getWriter().write(arraylist1.toString());
		}catch(InValidInsertException ie){
			try {
				response.getWriter().write("@@");
			} catch (IOException e) {
			}
		}catch(ManagerException ex){
			logger.error("doInsertSelfEvaluationwith(HttpServletRequest,HttpServletResponse)",ex);
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException e1) {
			}
		}
		return null;
	}
	
	/**
	 * 修改自我评价
	 * 
	 * @param request,response,session
	 * @return
	 */
	public Object doUpdataSelfProcess(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<AppraisalDto> listAppraise = new ArrayList<AppraisalDto>();
		AppraisalDto appraisal = new AppraisalDto();
		appraisal.setApprasialid(id+"");
		appraisal.setApprasial(apprasial);// 评价内容--界面
		appraisal.setSigndate(StringToDate(signDate));// 时间--界面
		if(!"1".equals(evaluateType3)&&!StringUtil.isEmpty(evaluateType3)){
			appraisal.setAppraseridentify(Integer.valueOf(personid));
		}else{
			appraisal.setAppraseridentify(1);
		}
		if(appraisal!=null){
			if ("0".equals(isStartAppraiseCache)) {
				selfAppManagerExt.updateSelfAppraisal(appraisal);
			} else if ("1".equals(isStartAppraiseCache)) {
				AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
				appraisalCacheDto.setApprasialid(appraisal.getApprasialid());
				appraisalCacheDto.setApprasial(appraisal.getApprasial());
				appraisalCacheDto.setSigndate(sdf.format(appraisal.getSigndate()));
				appraisalCacheDto.setAppraseridentify(Integer.toString(appraisal.getAppraseridentify()));
				appraisalCacheDto.setEdu_id(studentDto.getEduid());
				appraisalCacheDto.setSemesterid(termId);
				appraisalCacheDto.setAppraisaltypeid(updateType);
				appraisalCacheDto.setAppraiserrid(studentDto.getEduid());
				selfAppManagerExt.updateSelfAppraisalCache(appraisalCacheDto);
			}
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
			latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), updateType, id+"", columnName, userDto.getStudentName(), new Date());
			Map<String,Object> queryMap = new HashMap<String,Object>();
			Map<String,Object> Map = new HashMap<String,Object>();
			
			evaluated = studentDto;
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termId",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			if(!"1".equals(evaluateType2)&&!StringUtil.isEmpty(evaluateType2)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			}else if(!"1".equals(evaluateType3)&&!StringUtil.isEmpty(evaluateType3)){
				queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
			}
			response.setContentType("text/html;charset=UTF-8"); 
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
		/*	appraisalList2 = selfAppManagerExt.selectSelfAppraise(queryMap);
			Map.put("list2", appraisalList2);*/
			JSONArray json = JSONArray.fromObject(Map,jsonConfig);
			response.getWriter().write(json.toString());
			
		}
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doUpdataSelfProcess(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			return "error.jsp";
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException e1) {
			}
			logger.error("doUpdataSelfProcess(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			return "error.jsp";
		}
		return null;
	}
	
	/**
	 * 删除自我评价
	 * 
	 * @param request,response,session
	 * @return
	 */
	public void deleteSelfApp(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(id)){
				if ("0".equals(isStartAppraiseCache)) {
					selfAppManagerExt.deleteSelfAppraisal(id+"",userDto.getCmis30id(),userDto.getDiscode());
				} else if ("1".equals(isStartAppraiseCache)) {
					AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
					appraisalCacheDto.setApprasialid(id);
					appraisalCacheDto.setAppraseridentify("1");
					appraisalCacheDto.setEdu_id(studentDto.getEduid());
					appraisalCacheDto.setSemesterid(termId);
					appraisalCacheDto.setAppraisaltypeid(deleteType);
					appraisalCacheDto.setAppraiserrid(studentDto.getEduid());
					appraisalCacheDto.setCmis30id(userDto.getCmis30id());
					appraisalCacheDto.setDiscode(userDto.getDiscode());
					selfAppManagerExt.deleteSelfAppraisalCache(appraisalCacheDto);
				}
				latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), deleteType, id);
				Map<String,Object> queryMap = new HashMap<String,Object>();
				evaluatePersonName = studentDto.getName();
				termId = studentDto.getGradenum()+studentDto.getTermtype();
				if(termId1!=termId&&termId1!=null){
					termId = termId1;
				}
				levelcode = studentDto.getLevelcode();
				evaluated = studentDto;
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				response.setContentType("text/html;charset=UTF-8"); 
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);

				JSONArray arraylist1;
				JSONArray arraylist2;
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				/*appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
				arraylist1 = JSONArray.fromObject(appraisalList1,jsonConfig);
				response.getWriter().write(arraylist1.toString());*/
				
			}
		}catch (Exception e) {
			logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
	}
	
	
	
	/**
	 * 删除自我评价
	 * 
	 * @param request,response,session
	 * @return
	 */
	public void deleteSelf(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		boolean flag1 = true;
		while(flag1){
			if(insertMap.get(studentDto.getStudentid()).isSaveDone()){
				try{
						id = insertMap.get(studentDto.getStudentid()).getInsertid();
						if ("0".equals(isStartAppraiseCache)) {
							selfAppManagerExt.deleteSelfAppraisal(id,userDto.getCmis30id(),userDto.getDiscode());
						} else if ("1".equals(isStartAppraiseCache)) {
							AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
							appraisalCacheDto.setApprasialid(id);
							appraisalCacheDto.setAppraseridentify("1");
							appraisalCacheDto.setEdu_id(studentDto.getEduid());
							appraisalCacheDto.setSemesterid(userDto.getLevelcode());
							appraisalCacheDto.setAppraisaltypeid(deleteType);
							appraisalCacheDto.setAppraiserrid(studentDto.getEduid());
							appraisalCacheDto.setCmis30id(userDto.getCmis30id());
							appraisalCacheDto.setDiscode(userDto.getDiscode());
							selfAppManagerExt.deleteSelfAppraisalCache(appraisalCacheDto);
						}
						Map<String,Object> queryMap = new HashMap<String,Object>();
						evaluatePersonName = studentDto.getName();
						termId = studentDto.getGradenum()+studentDto.getTermtype();
						if(termId1!=termId&&termId1!=null){
							termId = termId1;
						}
						levelcode = studentDto.getLevelcode();
						queryMap.put("evaluateid",studentDto.getStudentid());
						queryMap.put("termId",termId);
						queryMap.put("cmis30id",studentDto.getCmis30id());
						queryMap.put("discode",studentDto.getDiscode());
						response.setContentType("text/html;charset=UTF-8"); 
						JsonConfig jsonConfig=new JsonConfig();
						DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
						jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);

						JSONArray arraylist1;
						if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
							queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
						}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
							queryMap.remove("evaluateType");
							queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
						}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
							queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
						}
						if ("0".equals(isStartAppraiseCache)) {
							appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
						} else if ("1".equals(isStartAppraiseCache)) {
							String type = (String) queryMap.get("evaluateType");
							appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
									termId, type, "1", userDto.getUsername(),AapprasialCacheDto.class);
							appraisalList1 = new ArrayList<AppraisalDto>();
							for (AapprasialCacheDto dto : appraisalCacheList1) {
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
								appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
								appraisalDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
								appraisalDto.setSigndate(dto.getSigndate()==""?new Date():StringToDate(dto.getSigndate()));
								appraisalDto.setStudentid(dto.getStudentid());
								appraisalList1.add(appraisalDto);
							}
						}
						arraylist1 = JSONArray.fromObject(appraisalList1,jsonConfig);
						response.getWriter().write(arraylist1.toString());
						insertMap.remove(studentDto.getStudentid());
				}catch (Exception e) {
					logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
					try {
						response.getWriter().write("1");
					} catch (IOException e1) {
						logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
					}
				}finally{
					studentDto.setSaveDone(false);
					insertMap.put(studentDto.getStudentid(), studentDto);
					flag1 = false;
				}
			}
		}
	}
	
	/**
	 * 删除自我评价
	 * 
	 * @param request,response,session
	 * @return
	 */
	public void deleteSelfAppWithatt(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(id)){
				if ("0".equals(isStartAppraiseCache)) {
					selfAppManagerExt.deleteSelfAppraisalWithatt(id+"",userDto.getCmis30id(),userDto.getDiscode());
				} else if ("1".equals(isStartAppraiseCache)) {
					AapprasialCacheDto appraisalCacheDto = new AapprasialCacheDto();
					appraisalCacheDto.setApprasialid(id);
					appraisalCacheDto.setAppraseridentify("1");
					appraisalCacheDto.setEdu_id(studentDto.getEduid());
					appraisalCacheDto.setSemesterid(termId);
					appraisalCacheDto.setAppraisaltypeid(deleteType);
					appraisalCacheDto.setAppraiserrid(studentDto.getEduid());
					appraisalCacheDto.setCmis30id(userDto.getCmis30id());
					appraisalCacheDto.setDiscode(userDto.getDiscode());
					selfAppManagerExt.deleteSelfAppraisalWithattCache(appraisalCacheDto);
				}
				latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), deleteType, id);
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
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				/*appraisalList2 = selfAppManagerExt.selectSelfAppraise(queryMap);
				Map.put("list2", appraisalList2);*/
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			}
		}catch (Exception e) {
			logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteSelfApp(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
	}
	
	/**
	 * 删除附件
	 * 
	 * @param request,response,session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void deleteSelfAttach(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(attachid)){
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.deleteAttach(attachid);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachid(attachid);
					attachCacheDto.setAttachtypeid(recordid);
					learnprocessWorksServiceExt.deleteAttachCache(attachCacheDto,evaluateType2);
				}
				Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				response.setContentType("text/html;charset=UTF-8"); 
				evaluated = studentDto;
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				if ("0".equals(isStartAppraiseCache)) {
					packageList=recordPackageManagerExt.selectRecordpackage(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = queryMap.get("evaluateType")+"";
					packageCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId, 
							type, "1", userDto.getUsername(),ArecordpackageCacheDto.class);
					packageList = new ArrayList<RecordPackageDto>();
					if (packageCacheList != null) {
						for (ArecordpackageCacheDto dto : packageCacheList) {
							RecordPackageDto recordPackageDto = new RecordPackageDto();
							recordPackageDto.setRecordid("".equals(dto.getRecordid())?0:Integer.valueOf((dto.getRecordid())));
							recordPackageDto.setStudentid("".equals(dto.getStudentid())?0:Integer.valueOf(dto.getStudentid()));
							recordPackageDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
							recordPackageDto.setAppraisaltypeid("".equals(dto.getAppraisaltypeid())?0:Integer.valueOf(dto.getAppraisaltypeid()));
							recordPackageDto.setContent(dto.getContent());
							recordPackageDto.setAppraseridentify("".equals(dto.getAppraseridentify())?0:Integer.valueOf(dto.getAppraseridentify()));
							recordPackageDto.setSigner(dto.getSigner());
							recordPackageDto.setSigndate(dto.getSigndate()==""?new Date():StringToDate(dto.getSigndate()));
							recordPackageDto.setEdu_id("".equals(dto.getEdu_id())?0:Integer.valueOf(dto.getEdu_id()));
							recordPackageDto.setDiscode(dto.getDiscode());
							recordPackageDto.setCmis30id("".equals(dto.getCmis30id())?0:Integer.valueOf(dto.getCmis30id()));
							recordPackageDto.setPartid(dto.getPartid()==null?0:Integer.valueOf(dto.getPartid()));
							List<AttachDto> list = new ArrayList<AttachDto>();
							List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getRecordid(), "A_ATTACH",AattachCacheDto.class);
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
							recordPackageDto.setAttachListForFile(list);
							packageList.add(recordPackageDto);
						}
					}
				}
				Map.put("list2", packageList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			}
		}catch (Exception e) {
			logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
	}
	
	/**
	 * 删除附件
	 * 
	 * @param request,response,session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void deleteSelfAttachApp(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(attachid)){
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.deleteAttach(attachid);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachid(attachid);
					attachCacheDto.setAttachtypeid(apprasialid);
					learnprocessWorksServiceExt.deleteAttachCache(attachCacheDto,attType);
				}
				Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				response.setContentType("text/html;charset=UTF-8"); 
				evaluated = studentDto;
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList1 = selfAppManagerExt.selectSelfAppraise(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = queryMap.get("evaluateType")+"";
					
					appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, type, "1", userDto.getUsername(),AapprasialCacheDto.class);
					appraisalList1 = new ArrayList<AppraisalDto>();
					for (AapprasialCacheDto dto : appraisalCacheList1) {
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
						appraisalDto.setPartId(NestUtil.isEmpty(dto.getPartid())?0:Integer.valueOf(dto.getPartid()));
						appraisalDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
						appraisalDto.setSigndate(dto.getSigndate()==""?new Date():StringToDate(dto.getSigndate()));
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
						appraisalList1.add(appraisalDto);
					}
				}
				Map.put("list2", appraisalList1);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			}
		}catch (Exception e) {
			logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
	}
	
	
	/**
	 * 删除附件
	 * 
	 * @param request,response,session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void deleteSelfAttachLearn(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(attachid)){
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.deleteAttach(attachid);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachid(attachid);
					attachCacheDto.setAttachtypeid(workid);
					learnprocessWorksServiceExt.deleteAttachCache(attachCacheDto,evaluateType2);
				}
				Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				response.setContentType("text/html;charset=UTF-8"); 
				evaluated = studentDto;
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksList = learnprocessWorksServiceExt.selectLearnprocessWorks(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = "";
					if (queryMap.get("evaluateType")==null) {
						type = "8010";
					} else {
						type = queryMap.get("evaluateType")+"";
					}
					learnprocessWorksCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
							termId, type, "1", userDto.getUsername(),AlearnprocessWorksCacheDto.class);
					learnprocessWorksList = new ArrayList<LearnprocessWorksDto>();
					for (AlearnprocessWorksCacheDto dto :learnprocessWorksCacheList) {
						LearnprocessWorksDto learnprocessWorksDto = new LearnprocessWorksDto();
						learnprocessWorksDto.setWorkid(dto.getWorkid());
						learnprocessWorksDto.setSubject(dto.getSubject());
						learnprocessWorksDto.setProcessdesc(dto.getProcessdesc());
						learnprocessWorksDto.setSemesterid(dto.getSemesterid());
						learnprocessWorksDto.setStudentid(dto.getStudentid());
						learnprocessWorksDto.setEdu_id(dto.getEdu_id());
						learnprocessWorksDto.setDiscode(dto.getDiscode());
						learnprocessWorksDto.setCmis30id(dto.getCmis30id());
						learnprocessWorksDto.setSigndate(StringToDatexie(dto.getSigndate()));
						List<AttachDto> list = new ArrayList<AttachDto>();
						List<AattachCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(dto.getWorkid(), "A_ATTACH",AattachCacheDto.class);
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
						learnprocessWorksDto.setAttachListForFile(list);
						learnprocessWorksList.add(learnprocessWorksDto);
					}
				}
				Map.put("list2", learnprocessWorksList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());
			}
		}catch (Exception e) {
			logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteSelfAttach(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
	}
	/**
	 * 字符串转日期
	 * 
	 * @param d
	 * @return
	 */
	public static Date StringToDate(String d) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch (Exception e) {
			return new Date();
		}
	}
	
	public void doAdd(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try {
			Map<String,Object> Map = new HashMap<String,Object>();
			inid = baseInforManagerExt.queryProKey("A_APPRASIAL");
			Map.put("inid", inid);
			JSONArray json = JSONArray.fromObject(Map);
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			logger.error("doAdd(HttpServletRequest,HttpServletResponse,HttpSession)",e);
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

