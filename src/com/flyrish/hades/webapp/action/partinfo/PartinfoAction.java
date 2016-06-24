package com.flyrish.hades.webapp.action.partinfo;

import java.io.File;
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
import java.util.UUID;

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
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.AttachmentDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPartInfoServiceExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;


public class PartinfoAction extends NginxUploadAction{
	/**
	 * 处理自我评价业务类
	 */
	@Spring
	ISelfAppManagerExt selfAppManagerExt;
	/**
	 * 处理初中数据
	 */
	@Spring
	IPartInfoServiceExt partInfoServiceExt;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	@Spring
	IBaseInforManagerExt baseInforManagerExt;
	
	public StudentDto studentDto = new StudentDto();
	
	public String termId;
	
	public String termId1;
	
	public String evaluatePersonName;//被评价人姓名

	public StudentDto evaluated;//对象
	
	public int choicenum;//跳转页面编号
	
	public String evaluateType;
	Map<String,Object>params = new HashMap<String,Object>();
	//评价类型
	public String evaluateType1;
	//评价类型
	public String evaluateType2;
	
	public String evaluateType22;
	//评价类型
	public String evaluateType3;
	
	public String evaluateType4;
	
	public String evaluateType5;
	
	public List<PartInfoDto> appraisalList1;//自我评价列表
	public List<PartInfoCacheDto> appraisalCacheList1;//自我评价列表缓存
	
	public List<PartInfoDto> appraisalList2;//自我评价列表
	public List<PartInfoCacheDto> appraisalCacheList2;//自我评价列表缓存

	public List<PartInfoDto> appraisalList3;//自我评价列表
	public List<PartInfoCacheDto> appraisalCacheList3;//自我评价列表缓存
	
	public List<PartInfoDto> appraisalList4;//自我评价列表
	public List<PartInfoCacheDto> appraisalCacheList4;//自我评价列表缓存
	
	public List<PartInfoDto> appraisalList5;//自我评价列表
	public List<PartInfoCacheDto> appraisalCacheList5;//自我评价列表缓存
	
	public String part_info;//内容
	
	public String topic;//主题
	
	public String CreateDate;
	
	public String id;//前台传的内容id
	
	public String old_uuid;

	public String new_uuid;

	public String add_uuid;
	
	public AttachmentDto attachment;//初中附件
	
	public String rpID;// 有数据时回调package的id

	public String new_rpID;// 初始化时回调package的id

	public String add_rpID;// 新加回调package的id
	
	public Integer levelcode;
	
	public String subject_id;
	
	public String checkrid;
	
	public String classid;
	
	/*public static String insertId;
	
	public static boolean isSaveDone = false;
	
	private boolean flag1 = true;*/
	
	public String insertId;
	
	public String newId;//无数据时初始化id
	
	public String newId2;//无数据时初始化id2
	
	public String nowDate;
	
	public String appid;
	
	public String keyword;
	
	public String cooperation_man;
	
	public String startdate;
	
	public String enddate;
	
	public String address;
	
	public String sectionName;
	
	public String trueAddress;//真实路径
	
	public String part_id;//附件外键
	
	public String attType;//附件所需二级栏目
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
		studentDto.setTermid(userDto.getTermId());
		classid = userDto.getClassid();
		evaluatePersonName = userDto.getStudentName();
		trueAddress = constantBean.get("JLD_upload")+"/"+userDto.getTermId()+"/"+userDto.getDiscode()+"/"+userDto.getCmis30id()+"/"+userDto.getGradeName()+"/"+userDto.getClassName();
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
	@SuppressWarnings("unchecked")
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		nowDate = df.format(new Date());
		Map<String,Object> queryMap = new HashMap<String,Object>();
		evaluatePersonName = studentDto.getName();
		evaluated = studentDto;
		termId = studentDto.getTermid();
		if(termId1!=termId&&termId1!=null){
			termId = termId1;
		}
		queryMap.put("evaluateid",studentDto.getStudentid());
		queryMap.put("termid",termId);
		queryMap.put("cmis30id",studentDto.getCmis30id());
		queryMap.put("discode",studentDto.getDiscode());
		switch(choicenum){
		case Constant.PAGE_XXQKSDW://新学期开始的我
			evaluateType1 = Constant.TERMS_BEGIN_ME;
			evaluateType2 = Constant.DEVELOP_TARGET_ME;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			Long startDatetime1=new Date().getTime();
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
				    	PartInfoDto app11 = (PartInfoDto) app1;
				    	PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			Long endDatetime1=new Date().getTime();
			logger.info((endDatetime1-startDatetime1)+"ms");
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			Long startDatetime2=new Date().getTime();
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList2.size()==0){
					newId2 = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 == null) {
					newId2 = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
				    	PartInfoDto app11 = (PartInfoDto) app1;
				    	PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			Long endDatetime2=new Date().getTime();
			logger.info((endDatetime2-startDatetime2)+"ms");
			
			break;
		case Constant.PAGE_XQJSSDW://学期结束时的我
			evaluateType1 = Constant.TERMS_END_ME;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			break;
		case Constant.PAGE_SXDD://思想道德
			evaluateType1 = Constant.MORALITY_SELF_APPRAISAL;
			evaluateType2 = Constant.MORALITY_RECORD_BAG;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
		        public int compare(Object app1, Object app2) {
		            try{
		            	PartInfoDto app11 = (PartInfoDto) app1;
		            	PartInfoDto app22 = (PartInfoDto) app2;
		    			return app11.compareTo(app22);                         
		            }catch(Exception e){
		                e.printStackTrace();
		            }         
		            return 1;                        
		        }
			});
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						List<AttachmentDto> list = new ArrayList<AttachmentDto>();
						List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
						if (cacheList != null) {
							for (AttachmentCacheDto dto : cacheList) {
								AttachmentDto attachmentDto = new AttachmentDto();
								attachmentDto.setAttachment_id(dto.getAttachment_id());
								attachmentDto.setAttachment_name(dto.getAttachment_name());
								attachmentDto.setAttachment_path(dto.getAttachment_path());
								attachmentDto.setCmis30id(dto.getCmis30id());
								attachmentDto.setDiscode(dto.getDiscode());
								attachmentDto.setPart_id(dto.getPart_id());
								list.add(attachmentDto);
							}
							Collections.sort(list,new Comparator(){
								public int compare(Object app1, Object app2) {
									try{
										AttachmentDto app11 = (AttachmentDto) app1;
										AttachmentDto app22 = (AttachmentDto) app2;
										return app11.compareTo(app22);                         
									}catch(Exception e){
										e.printStackTrace();
									}         
									return 1;                        
								}
							});
						}
						partInfo.setAttachListForFile(list);
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
		        public int compare(Object app1, Object app2) {
		            try{
		            	PartInfoDto app11 = (PartInfoDto) app1;
		            	PartInfoDto app22 = (PartInfoDto) app2;
		    			return app11.compareTo(app22);                         
		            }catch(Exception e){
		                e.printStackTrace();
		            }         
		            return 1;                        
		        }
			});
			break;
		case Constant.PAGE_XYCJ://学业成就
			evaluateType1 = Constant.WORKS_SELF_APPRAISAL;
			evaluateType2 = Constant.WORKS_SUBJECT_SHOW;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						List<AttachmentDto> list = new ArrayList<AttachmentDto>();
						List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
						if (cacheList != null) {
							for (AttachmentCacheDto dto : cacheList) {
								AttachmentDto attachmentDto = new AttachmentDto();
								attachmentDto.setAttachment_id(dto.getAttachment_id());
								attachmentDto.setAttachment_name(dto.getAttachment_name());
								attachmentDto.setAttachment_path(dto.getAttachment_path());
								attachmentDto.setCmis30id(dto.getCmis30id());
								attachmentDto.setDiscode(dto.getDiscode());
								attachmentDto.setPart_id(dto.getPart_id());
								list.add(attachmentDto);
							}
							Collections.sort(list,new Comparator(){
								public int compare(Object app1, Object app2) {
									try{
										AttachmentDto app11 = (AttachmentDto) app1;
										AttachmentDto app22 = (AttachmentDto) app2;
										return app11.compareTo(app22);                         
									}catch(Exception e){
										e.printStackTrace();
									}         
									return 1;                        
								}
							});
						}
						partInfo.setAttachListForFile(list);
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			break;
		case Constant.PAGE_HZJL://合作交流
			evaluateType1 = Constant.COOPERATION_SELF_APPRAISAL;
			evaluateType2 = Constant.COOPERATION_RECORD_BAG;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						List<AttachmentDto> list = new ArrayList<AttachmentDto>();
						List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
						if (cacheList != null) {
							for (AttachmentCacheDto dto : cacheList) {
								AttachmentDto attachmentDto = new AttachmentDto();
								attachmentDto.setAttachment_id(dto.getAttachment_id());
								attachmentDto.setAttachment_name(dto.getAttachment_name());
								attachmentDto.setAttachment_path(dto.getAttachment_path());
								attachmentDto.setCmis30id(dto.getCmis30id());
								attachmentDto.setDiscode(dto.getDiscode());
								attachmentDto.setPart_id(dto.getPart_id());
								list.add(attachmentDto);
							}
							Collections.sort(list,new Comparator(){
								public int compare(Object app1, Object app2) {
									try{
										AttachmentDto app11 = (AttachmentDto) app1;
										AttachmentDto app22 = (AttachmentDto) app2;
										return app11.compareTo(app22);                         
									}catch(Exception e){
										e.printStackTrace();
									}         
									return 1;                        
								}
							});
						}
						partInfo.setAttachListForFile(list);
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			break;
		case Constant.PAGE_YDYJK://运动与健康
			evaluateType1 = Constant.PLAY_SELF_APPRAISAL;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort(appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
					try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
					}catch(Exception e){
						e.printStackTrace();
					}         
					return 1;                        
				}
			});
			break;
		case Constant.PAGE_SMYBX://审美与表现
			evaluateType1 = Constant.AESTHETIC_SELF_APPRAISAL;
			evaluateType2 = Constant.AESTHETIC_RECORD_BAG;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						List<AttachmentDto> list = new ArrayList<AttachmentDto>();
						List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
						if (cacheList != null) {
							for (AttachmentCacheDto dto : cacheList) {
								AttachmentDto attachmentDto = new AttachmentDto();
								attachmentDto.setAttachment_id(dto.getAttachment_id());
								attachmentDto.setAttachment_name(dto.getAttachment_name());
								attachmentDto.setAttachment_path(dto.getAttachment_path());
								attachmentDto.setCmis30id(dto.getCmis30id());
								attachmentDto.setDiscode(dto.getDiscode());
								attachmentDto.setPart_id(dto.getPart_id());
								list.add(attachmentDto);
							}
							Collections.sort(list,new Comparator(){
								public int compare(Object app1, Object app2) {
									try{
										AttachmentDto app11 = (AttachmentDto) app1;
										AttachmentDto app22 = (AttachmentDto) app2;
										return app11.compareTo(app22);                         
									}catch(Exception e){
										e.printStackTrace();
									}         
									return 1;                        
								}
							});
						}
						partInfo.setAttachListForFile(list);
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			break;
		case Constant.PAGE_ZHSJHD://综合实践活动
			if("81".equals(evaluateType)){
				evaluateType1 = Constant.ACTIVITY_SELF_APPRAISAL_1;
				queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList1 = partInfoServiceExt.selectSelfPartInfoZonghe(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList1 = new ArrayList<PartInfoDto>();
					if (appraisalCacheList1 != null) {
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							partInfo.setStartdate(StringToDatexie(partInfoCacheDto.getStartdate()));
							partInfo.setEnddate(StringToDatexie(partInfoCacheDto.getEnddate()));
							partInfo.setKeyword(partInfoCacheDto.getKeyword()==null?"":partInfoCacheDto.getKeyword());
							partInfo.setCooperation_man(partInfoCacheDto.getCooperation_man()==null?"":partInfoCacheDto.getCooperation_man());
							partInfo.setAddress(partInfoCacheDto.getAddress()==null?"":partInfoCacheDto.getAddress());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList1.add(partInfo);
						}
					}
				}
				Collections.sort( appraisalList1,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
				evaluateType2 = Constant.ACTIVITY_BASEINFO_1;
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList2 = partInfoServiceExt.selectSelfPartInfoZonghe(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList2 = new ArrayList<PartInfoDto>();
					if (appraisalCacheList2 != null) {
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							partInfo.setStartdate(StringToDatexie(partInfoCacheDto.getStartdate()));
							partInfo.setEnddate(StringToDatexie(partInfoCacheDto.getEnddate()));
							partInfo.setKeyword(partInfoCacheDto.getKeyword()==null?"":partInfoCacheDto.getKeyword());
							partInfo.setCooperation_man(partInfoCacheDto.getCooperation_man()==null?"":partInfoCacheDto.getCooperation_man());
							partInfo.setAddress(partInfoCacheDto.getAddress()==null?"":partInfoCacheDto.getAddress());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList2.add(partInfo);
						}
					}
				}
				Collections.sort( appraisalList2,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
				evaluateType3 = Constant.ACTIVITY_RESEARCH_RESULT;
				queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList3 = partInfoServiceExt.selectSelfPartInfoZonghe(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					appraisalCacheList3 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							evaluateType3, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList3 = new ArrayList<PartInfoDto>();
					if (appraisalCacheList3 != null) {
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList3) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							partInfo.setStartdate(StringToDatexie(partInfoCacheDto.getStartdate()));
							partInfo.setEnddate(StringToDatexie(partInfoCacheDto.getEnddate()));
							partInfo.setKeyword(partInfoCacheDto.getKeyword()==null?"":partInfoCacheDto.getKeyword());
							partInfo.setCooperation_man(partInfoCacheDto.getCooperation_man()==null?"":partInfoCacheDto.getCooperation_man());
							partInfo.setAddress(partInfoCacheDto.getAddress()==null?"":partInfoCacheDto.getAddress());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList3.add(partInfo);
						}
					}
				}
				Collections.sort( appraisalList3,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
			}else{
				evaluateType1 = Constant.ACTIVITY_SELF_APPRAISAL_2;
				queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList1 = partInfoServiceExt.selectSelfPartInfoZonghe(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList1 = new ArrayList<PartInfoDto>();
					if (appraisalCacheList1 != null) {
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							partInfo.setStartdate(StringToDatexie(partInfoCacheDto.getStartdate()));
							partInfo.setEnddate(StringToDatexie(partInfoCacheDto.getEnddate()));
							partInfo.setKeyword(partInfoCacheDto.getKeyword()==null?"":partInfoCacheDto.getKeyword());
							partInfo.setCooperation_man(partInfoCacheDto.getCooperation_man()==null?"":partInfoCacheDto.getCooperation_man());
							partInfo.setAddress(partInfoCacheDto.getAddress()==null?"":partInfoCacheDto.getAddress());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList1.add(partInfo);
						}
					}
				}
				Collections.sort( appraisalList1,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
				evaluateType2 = Constant.ACTIVITY_BASEINFO_2;
				queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList2 = partInfoServiceExt.selectSelfPartInfoZonghe(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList2 = new ArrayList<PartInfoDto>();
					if (appraisalCacheList2 != null) {
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							partInfo.setStartdate(StringToDatexie(partInfoCacheDto.getStartdate()));
							partInfo.setEnddate(StringToDatexie(partInfoCacheDto.getEnddate()));
							partInfo.setKeyword(partInfoCacheDto.getKeyword()==null?"":partInfoCacheDto.getKeyword());
							partInfo.setCooperation_man(partInfoCacheDto.getCooperation_man()==null?"":partInfoCacheDto.getCooperation_man());
							partInfo.setAddress(partInfoCacheDto.getAddress()==null?"":partInfoCacheDto.getAddress());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList2.add(partInfo);
						}
					}
				}
				Collections.sort( appraisalList2,new Comparator(){
					public int compare(Object app1, Object app2) {
					    try{
						PartInfoDto app11 = (PartInfoDto) app1;
						PartInfoDto app22 = (PartInfoDto) app2;
							return app11.compareTo(app22);                         
					    }catch(Exception e){
						e.printStackTrace();
					    }         
					    return 1;                        
					}
				});
			}
			break;
		case Constant.PAGE_GXFZ://个性发展
			evaluateType1 = Constant.INDIVIDUALITY_SELF_APPRAISAL;
			evaluateType2 = Constant.INDIVIDUALITY_RECORD_BAG;
			queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				if(appraisalList1.size()==0){
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				}
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList1 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList1 == null) {
					newId = baseInforManagerExt.queryProKey("PARTINFO");
				} else if (appraisalCacheList1 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList1,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
						return app11.compareTo(app22);                         
				    }catch(Exception e){
					e.printStackTrace();
				    }         
				    return 1;                        
				}
			});
			queryMap.remove("evaluateType");
			queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
			if ("0".equals(isStartAppraiseCache)) {
				appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
			} else if ("1".equals(isStartAppraiseCache)) {
				appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
						evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
				appraisalList2 = new ArrayList<PartInfoDto>();
				if (appraisalCacheList2 != null) {
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						List<AttachmentDto> list = new ArrayList<AttachmentDto>();
						List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
						if (cacheList != null) {
							for (AttachmentCacheDto dto : cacheList) {
								AttachmentDto attachmentDto = new AttachmentDto();
								attachmentDto.setAttachment_id(dto.getAttachment_id());
								attachmentDto.setAttachment_name(dto.getAttachment_name());
								attachmentDto.setAttachment_path(dto.getAttachment_path());
								attachmentDto.setCmis30id(dto.getCmis30id());
								attachmentDto.setDiscode(dto.getDiscode());
								attachmentDto.setPart_id(dto.getPart_id());
								list.add(attachmentDto);
							}
							Collections.sort(list,new Comparator(){
								public int compare(Object app1, Object app2) {
									try{
										AttachmentDto app11 = (AttachmentDto) app1;
										AttachmentDto app22 = (AttachmentDto) app2;
										return app11.compareTo(app22);                         
									}catch(Exception e){
										e.printStackTrace();
									}         
									return 1;                        
								}
							});
						}
						partInfo.setAttachListForFile(list);
						appraisalList2.add(partInfo);
					}
				}
			}
			Collections.sort( appraisalList2,new Comparator(){
				public int compare(Object app1, Object app2) {
				    try{
					PartInfoDto app11 = (PartInfoDto) app1;
					PartInfoDto app22 = (PartInfoDto) app2;
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
		switch(choicenum){
		case Constant.PAGE_XXQKSDW:
			return "/selfappraise/czself_semesterstart.jsp";
		case Constant.PAGE_XQJSSDW:
			return "/selfappraise/czself_semesterend.jsp";
		case Constant.PAGE_SXDD:
			return "/selfappraise/czself_package.jsp";
		case Constant.PAGE_XYCJ:
			return "/selfappraise/czself_course.jsp";
		case Constant.PAGE_HZJL:
			return "/selfappraise/czself_package.jsp";
		case Constant.PAGE_YDYJK:
			return "/selfappraise/czself_sports.jsp";
		case Constant.PAGE_SMYBX:
			return "/selfappraise/czself_package.jsp";
		case Constant.PAGE_ZHSJHD:
			if("81".equals(evaluateType)){
				return "/selfappraise/czself_yanjiu.jsp";
			}else{
				return "/selfappraise/czself_shijian.jsp";
			}
			
		case Constant.PAGE_GXFZ:
			return "/selfappraise/czself_package.jsp";
		default:
			return null;
		}
	}
	
	
	/**
	 * 增加保存初中内容
	 * 
	 * @return
	 */
	public Object doInsertSelfAppWithoutatt(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			PartInfoDto partInfo=new PartInfoDto();
			partInfo.setCmis30id(studentDto.getCmis30id());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			if(!StringUtil.isEmpty(CreateDate)){
				partInfo.setCreateDate(StringToDate(CreateDate));
			}else{
				partInfo.setCreateDate(StringToDate(df.format(new Date())));
			}
			partInfo.setDiscode(studentDto.getDiscode()+"");
			partInfo.setIs_attachmen("0");
			partInfo.setSigner_name(studentDto.getName());
			partInfo.setStudentid(studentDto.getStudentid());
			partInfo.setEdu_id(studentDto.getEduid());
			if(!StringUtil.isEmpty(subject_id)){
				partInfo.setSubject_id(subject_id);
			}else{
				partInfo.setSubject_id(null);
			}
			partInfo.setTermid(termId);
			partInfo.setTopic(topic);
			partInfo.setUserid(studentDto.getEduid());
			partInfo.setWrite_man("本人");
			partInfo.setPart_info(part_info);
			String rtype = null;
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				partInfo.setTwo_part_id(evaluateType1);
				rtype = evaluateType1;
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				partInfo.setTwo_part_id(evaluateType2);
				rtype = evaluateType2;
			}
			if ("0".equals(isStartAppraiseCache)) {
				appid = partInfoServiceExt.insertSelfPartInfo(partInfo);
			} else if ("1".equals(isStartAppraiseCache)) {
				PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
				partInfoCacheDto.setCreateDate(df.format(partInfo.getCreateDate()));
				partInfoCacheDto.setIs_attachmen(partInfo.getIs_attachmen());
				partInfoCacheDto.setPart_info(partInfo.getPart_info());
				partInfoCacheDto.setSigner_name(partInfo.getSigner_name());
				partInfoCacheDto.setStudentid(partInfo.getStudentid());
				partInfoCacheDto.setSubject_id(partInfo.getSubject_id());
				partInfoCacheDto.setTermid(partInfo.getTermid());
				partInfoCacheDto.setTopic(partInfo.getTopic());
				partInfoCacheDto.setTwo_part_id(partInfo.getTwo_part_id());
				partInfoCacheDto.setUserid(partInfo.getUserid());
				partInfoCacheDto.setWrite_man(partInfo.getWrite_man());
				partInfoCacheDto.setCmis30id(partInfo.getCmis30id());
				partInfoCacheDto.setDiscode(partInfo.getDiscode());
				partInfoCacheDto.setEdu_id(partInfo.getEdu_id());
				appid = partInfoServiceExt.insertSelfPartInfoCache(partInfoCacheDto);
			}
			latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), rtype, appid, this.getSectionName(rtype), userDto.getStudentName(), new Date());
			response.getWriter().write(appid);
		}catch(ManagerException ex){
			logger.error("saveUserInfo(HttpServletRequest,HttpServletResponse)",ex);
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
		} catch (IOException e) {
			logger.error("saveUserInfo(HttpServletRequest,HttpServletResponse)",e);
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
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
	public Object doUpdataSelfPartInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
		PartInfoDto partInfo=new PartInfoDto();
		partInfo.setPart_id(id);
		partInfo.setPart_info(part_info);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		if(!StringUtil.isEmpty(CreateDate)){
			partInfo.setCreateDate(StringToDate(CreateDate));
		}else{
			partInfo.setCreateDate(StringToDate(df.format(new Date())));
		}
		if(!StringUtil.isEmpty(topic)){
			partInfo.setTopic(topic);
		}
		if(!StringUtil.isEmpty(subject_id)){
			partInfo.setSubject_id(subject_id);
		}
		this.initParams(id);
		String sectionCode = partInfoServiceExt.getSectionCode(params);
		if ("0".equals(isStartAppraiseCache)) {
			partInfoServiceExt.updateSelfPartInfo(partInfo);
		} else if ("1".equals(isStartAppraiseCache)) {
			PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
			partInfoCacheDto.setPart_info(partInfo.getPart_info());
			partInfoCacheDto.setPart_id(partInfo.getPart_id());
			partInfoCacheDto.setCreateDate(df.format(partInfo.getCreateDate()));
			partInfoCacheDto.setTopic(partInfo.getTopic());
			partInfoCacheDto.setSubject_id(partInfo.getSubject_id());
			partInfoCacheDto.setTwo_part_id(evaluateType);
			partInfoCacheDto.setEdu_id(studentDto.getEduid());
			partInfoCacheDto.setTermid(termId);
			partInfoCacheDto.setWrite_man("本人");
			partInfoServiceExt.updateSelfPartInfoCache(partInfoCacheDto);
		}
		latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), sectionCode, id, this.getSectionName(evaluateType), userDto.getStudentName(), new Date());
		}catch(ManagerException ex){
			logger.error("doUpdataSelfPartInfo(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
		}
		return null;
	}
	private void initParams(String proKey){
		params.put("userid", userDto.getUsername());
		params.put("partId", proKey);
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("partId", proKey);
	}
	
	/**
	 * 修改自我评价
	 * 
	 * @param request,response,session
	 * @return
	 */
	public Object doUpdataSelfPartInfoZh(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
		PartInfoDto partInfo=new PartInfoDto();
		partInfo.setPart_id(id);
		if(!StringUtil.isEmpty(part_info)){
		partInfo.setPart_info(part_info);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		if(!StringUtil.isEmpty(CreateDate)){
			partInfo.setCreateDate(StringToDate(CreateDate));
		}else{
			partInfo.setCreateDate(StringToDate(df.format(new Date())));
		}
		if(!StringUtil.isEmpty(topic)){
			partInfo.setTopic(topic);
		}
		if(!StringUtil.isEmpty(subject_id)){
			partInfo.setSubject_id(subject_id);
		}
		partInfo.setCooperation_man(cooperation_man);
		partInfo.setKeyword(keyword);
		partInfo.setAddress(address);
		partInfo.setStartdate(StringToDate(startdate));
		partInfo.setEnddate(StringToDate(enddate));
		this.initParams(id);
		String sectionCode = partInfoServiceExt.getSectionCode(params);
		if ("0".equals(isStartAppraiseCache)) {
			partInfoServiceExt.updateSelfPartInfoZh(partInfo);
		} else if ("1".equals(isStartAppraiseCache)) {
			PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
			partInfoCacheDto.setPart_info(partInfo.getPart_info());
			partInfoCacheDto.setPart_id(partInfo.getPart_id());
			partInfoCacheDto.setCreateDate(df.format(partInfo.getCreateDate()));
			partInfoCacheDto.setAddress(partInfo.getAddress());
			partInfoCacheDto.setCooperation_man(partInfo.getCooperation_man());
			partInfoCacheDto.setStartdate(df.format(partInfo.getStartdate()));
			partInfoCacheDto.setEnddate(df.format(partInfo.getEnddate()));
			partInfoCacheDto.setKeyword(partInfo.getKeyword());
			partInfoCacheDto.setTopic(partInfo.getTopic());
			partInfoCacheDto.setSubject_id(partInfo.getSubject_id());
			partInfoCacheDto.setTwo_part_id(evaluateType);
			partInfoCacheDto.setEdu_id(studentDto.getEduid());
			partInfoCacheDto.setTermid(termId);
			partInfoCacheDto.setWrite_man("本人");
			partInfoServiceExt.updateSelfPartInfoZhCache(partInfoCacheDto);
		}
		latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), sectionCode, id, this.getSectionName(evaluateType), userDto.getStudentName(), new Date());
		}catch(ManagerException ex){
			logger.error("doUpdataSelfPartInfo(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 增加保存初中内容
	 * 
	 * @return
	 */
	public Object doInsertSelfAppWithatt(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			PartInfoDto partInfo=new PartInfoDto();
			partInfo.setCmis30id(studentDto.getCmis30id());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			if(!StringUtil.isEmpty(CreateDate)){
				partInfo.setCreateDate(StringToDate(CreateDate));
			}else{
				partInfo.setCreateDate(StringToDate(df.format(new Date())));
			}
			partInfo.setDiscode(studentDto.getDiscode()+"");
			partInfo.setIs_attachmen("1");
			partInfo.setSigner_name(studentDto.getName());
			partInfo.setStudentid(studentDto.getStudentid());
			partInfo.setEdu_id(studentDto.getEduid());
			if(!StringUtil.isEmpty(subject_id)){
				partInfo.setSubject_id(subject_id);
			}else{
				partInfo.setSubject_id(null);
			}
			partInfo.setTermid(termId);
			partInfo.setTopic(topic);
			partInfo.setUserid(studentDto.getEduid());
			partInfo.setWrite_man("本人");
			partInfo.setPart_info(part_info);
			String sectionId = null;
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				partInfo.setTwo_part_id(evaluateType1);
				sectionId = evaluateType1;
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				partInfo.setTwo_part_id(evaluateType2);
				sectionId = evaluateType2;
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				partInfo.setTwo_part_id(evaluateType3);
				sectionId = evaluateType3;
			}
			if (partInfo != null) {
				if ("0".equals(isStartAppraiseCache)) {
					rpID = partInfoServiceExt.insertSelfPartInfo(partInfo);
				} else if ("1".equals(isStartAppraiseCache)) {
					PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
					partInfoCacheDto.setCreateDate(df.format(partInfo.getCreateDate()));
					partInfoCacheDto.setIs_attachmen(partInfo.getIs_attachmen());
					partInfoCacheDto.setPart_info(partInfo.getPart_info());
					partInfoCacheDto.setSigner_name(partInfo.getSigner_name());
					partInfoCacheDto.setStudentid(partInfo.getStudentid());
					partInfoCacheDto.setSubject_id(partInfo.getSubject_id());
					partInfoCacheDto.setTermid(partInfo.getTermid());
					partInfoCacheDto.setTopic(partInfo.getTopic());
					partInfoCacheDto.setTwo_part_id(partInfo.getTwo_part_id());
					partInfoCacheDto.setUserid(partInfo.getUserid());
					partInfoCacheDto.setWrite_man(partInfo.getWrite_man());
					partInfoCacheDto.setCmis30id(partInfo.getCmis30id());
					partInfoCacheDto.setDiscode(partInfo.getDiscode());
					partInfoCacheDto.setEdu_id(partInfo.getEdu_id());
					rpID = partInfoServiceExt.insertSelfPartInfoCache(partInfoCacheDto);
				}
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), sectionId, rpID, this.getSectionName(sectionId), userDto.getStudentName(), new Date());
			}
				response.getWriter().write(rpID);
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doInsertSelfAppWithatt(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error("doInsertSelfAppWithatt(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}
		return null;
	}
	
	
	/**
	 * 增加保存初中内容
	 * 
	 * @return
	 */
	public Object doInsertSelfAppZh(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			PartInfoDto partInfo=new PartInfoDto();
			partInfo.setCmis30id(studentDto.getCmis30id());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			if(!StringUtil.isEmpty(CreateDate)){
				partInfo.setCreateDate(StringToDate(CreateDate));
			}else{
				partInfo.setCreateDate(StringToDate(df.format(new Date())));
			}
			partInfo.setDiscode(studentDto.getDiscode()+"");
			partInfo.setIs_attachmen("1");
			partInfo.setSigner_name(studentDto.getName());
			partInfo.setStudentid(studentDto.getStudentid());
			partInfo.setEdu_id(studentDto.getEduid());
			if(!StringUtil.isEmpty(subject_id)){
				partInfo.setSubject_id(subject_id);
			}else{
				partInfo.setSubject_id(null);
			}
			if(!StringUtil.isEmpty(cooperation_man)){
				partInfo.setCooperation_man(cooperation_man);
			}else{
				partInfo.setCooperation_man(null);
			}
			if(!StringUtil.isEmpty(keyword)){
				partInfo.setKeyword(keyword);
			}else{
				partInfo.setKeyword(null);
			}
			if(!StringUtil.isEmpty(address)){
				partInfo.setAddress(address);
			}else{
				partInfo.setAddress(null);
			}
			if(!StringUtil.isEmpty(startdate)){
				partInfo.setStartdate(StringToDate(startdate));
			}else{
				partInfo.setStartdate(null);
			}
			if(!StringUtil.isEmpty(enddate)){
				partInfo.setEnddate(StringToDate(enddate));
			}else{
				partInfo.setEnddate(null);
			}
			if(!StringUtil.isEmpty(topic)){
				partInfo.setTopic(topic);
			}else{
				partInfo.setTopic(null);
			}
			partInfo.setTermid(termId);
			partInfo.setUserid(studentDto.getEduid());
			partInfo.setWrite_man("本人");
			partInfo.setPart_info(part_info);
			String sectionId = null;
			if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
				partInfo.setTwo_part_id(evaluateType1);
				sectionId = evaluateType1;
			}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
				partInfo.setTwo_part_id(evaluateType2);
				sectionId = evaluateType2;
			}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
				partInfo.setTwo_part_id(evaluateType3);
				sectionId = evaluateType3;
			}
			if (partInfo != null) {
				if ("0".equals(isStartAppraiseCache)) {
					rpID = partInfoServiceExt.insertSelfPartInfoWith(partInfo);
				} else if ("1".equals(isStartAppraiseCache)) {
					PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
					partInfoCacheDto.setCreateDate(df.format(partInfo.getCreateDate()));
					partInfoCacheDto.setIs_attachmen(partInfo.getIs_attachmen());
					partInfoCacheDto.setPart_info(partInfo.getPart_info());
					partInfoCacheDto.setSigner_name(partInfo.getSigner_name());
					partInfoCacheDto.setStudentid(partInfo.getStudentid());
					partInfoCacheDto.setSubject_id(partInfo.getSubject_id());
					partInfoCacheDto.setTermid(partInfo.getTermid());
					partInfoCacheDto.setTopic(partInfo.getTopic());
					partInfoCacheDto.setTwo_part_id(partInfo.getTwo_part_id());
					partInfoCacheDto.setUserid(partInfo.getUserid());
					partInfoCacheDto.setWrite_man(partInfo.getWrite_man());
					partInfoCacheDto.setCmis30id(partInfo.getCmis30id());
					partInfoCacheDto.setDiscode(partInfo.getDiscode());
					partInfoCacheDto.setEdu_id(partInfo.getEdu_id());
					partInfoCacheDto.setAddress(partInfo.getAddress());
					partInfoCacheDto.setCooperation_man(partInfo.getCooperation_man());
					partInfoCacheDto.setStartdate(partInfo.getStartdate()==null?"":df.format(partInfo.getStartdate()));
					partInfoCacheDto.setEnddate(partInfo.getStartdate()==null?"":df.format(partInfo.getEnddate()));
					partInfoCacheDto.setKeyword(partInfo.getKeyword());
					rpID = partInfoServiceExt.insertSelfPartInfoWithCache(partInfoCacheDto);
				}
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), sectionId, rpID, this.getSectionName(sectionId), userDto.getStudentName(), new Date());
				
			}
				response.getWriter().write(rpID);
		}catch(ManagerException ex){
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error("doInsertSelfAppWithatt(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error("doInsertSelfAppWithatt(HttpServletRequest,HttpServletResponse,HttpSession)",e);
		}
		return null;
	}
	/**
	 * 返回文件名与文件路径
	 * @throws ParseException 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Object saveFile(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws ParseException {
		attachment = uploadFile();
		if (StringUtil.isEmpty(rpID) && !StringUtil.isEmpty(new_rpID)) {
			rpID = new_rpID;
		} else if (!StringUtil.isEmpty(add_rpID)
				&& StringUtil.isEmpty(new_rpID)) {
			rpID = add_rpID;
		}
		if (attachment != null && !StringUtil.isEmpty(rpID)) {
			try {
				if ("0".equals(isStartAppraiseCache)) {
					partInfoServiceExt.insertAttachment(attachment, rpID);
				} else if ("1".equals(isStartAppraiseCache)) {
					AttachmentCacheDto attachmentCacheDto = new AttachmentCacheDto();
					attachmentCacheDto.setAttachment_name(attachment.getAttachment_name());
					attachmentCacheDto.setAttachment_path(attachment.getAttachment_path());
					attachmentCacheDto.setPart_id(rpID);
					attachmentCacheDto.setCmis30id(attachment.getCmis30id());
					attachmentCacheDto.setDiscode(attachment.getDiscode());
					partInfoServiceExt.insertAttachmentCache(attachmentCacheDto, attType);
				}
				if(NestUtil.isEmpty(evaluateType2)){
					latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), evaluateType22, rpID, this.getSectionName(evaluateType22), userDto.getStudentName(), new Date());
				}else{
					latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), evaluateType2, rpID, this.getSectionName(evaluateType2), userDto.getStudentName(), new Date());
				}
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				JSONArray arraylist1;
				JSONArray arraylist2;
				JSONObject jmap;
				Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				Map.put("rID", rpID);
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termid",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				response.setContentType("text/html;charset=UTF-8"); 
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
					if ("0".equals(isStartAppraiseCache)) {
						appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
					} else if ("1".equals(isStartAppraiseCache)) {
						appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
								evaluateType1, "本人", userDto.getUsername(),PartInfoCacheDto.class);
						appraisalList1 = new ArrayList<PartInfoDto>();
						if (appraisalCacheList1 != null) {
							for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
								PartInfoDto partInfo=new PartInfoDto();
								partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
								partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
								partInfo.setPart_id(partInfoCacheDto.getPart_id());
								partInfo.setPart_info(partInfoCacheDto.getPart_info());
								partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
								partInfo.setStudentid(partInfoCacheDto.getStudentid());
								partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
								partInfo.setTermid(partInfoCacheDto.getTermid());
								partInfo.setTopic(partInfoCacheDto.getTopic());
								partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
								partInfo.setUserid(partInfoCacheDto.getUserid());
								partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
								appraisalList1.add(partInfo);
							}
						}
					}
					Map.put("list2", appraisalList1);
					JSONArray json = JSONArray.fromObject(Map);
					response.getWriter().write(json.toString());
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
					if ("0".equals(isStartAppraiseCache)) {
						appraisalList2 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
					} else if ("1".equals(isStartAppraiseCache)) {
						appraisalCacheList2 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
								evaluateType2, "本人", userDto.getUsername(),PartInfoCacheDto.class);
						appraisalList2 = new ArrayList<PartInfoDto>();
						if (appraisalCacheList2 != null) {
							for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList2) {
								PartInfoDto partInfo=new PartInfoDto();
								partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
								partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
								partInfo.setPart_id(partInfoCacheDto.getPart_id());
								partInfo.setPart_info(partInfoCacheDto.getPart_info());
								partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
								partInfo.setStudentid(partInfoCacheDto.getStudentid());
								partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
								partInfo.setTermid(partInfoCacheDto.getTermid());
								partInfo.setTopic(partInfoCacheDto.getTopic());
								partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
								partInfo.setUserid(partInfoCacheDto.getUserid());
								partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
								List<AttachmentDto> list = new ArrayList<AttachmentDto>();
								List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
								if (cacheList != null) {
									for (AttachmentCacheDto dto : cacheList) {
										AttachmentDto attachmentDto = new AttachmentDto();
										attachmentDto.setAttachment_id(dto.getAttachment_id());
										attachmentDto.setAttachment_name(dto.getAttachment_name());
										attachmentDto.setAttachment_path(dto.getAttachment_path());
										attachmentDto.setCmis30id(dto.getCmis30id());
										attachmentDto.setDiscode(dto.getDiscode());
										attachmentDto.setPart_id(dto.getPart_id());
										list.add(attachmentDto);
									}
									Collections.sort(list,new Comparator(){
										public int compare(Object app1, Object app2) {
											try{
												AttachmentDto app11 = (AttachmentDto) app1;
												AttachmentDto app22 = (AttachmentDto) app2;
												return app11.compareTo(app22);                         
											}catch(Exception e){
												e.printStackTrace();
											}         
											return 1;                        
										}
									});
								}
								partInfo.setAttachListForFile(list);
								appraisalList2.add(partInfo);
							}
						}
					}
					Map.put("list2", appraisalList2);
					JSONArray json = JSONArray.fromObject(Map,jsonConfig);
					response.getWriter().write(json.toString());
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
					if ("0".equals(isStartAppraiseCache)) {
						appraisalList3 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
					} else if ("1".equals(isStartAppraiseCache)) {
						appraisalCacheList3 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
								evaluateType3, "本人", userDto.getUsername(),PartInfoCacheDto.class);
						appraisalList3 = new ArrayList<PartInfoDto>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if (appraisalCacheList3 != null) {
							for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList3) {
								PartInfoDto partInfo=new PartInfoDto();
								partInfo.setCreateDate(StringToDatexie(partInfoCacheDto.getCreateDate()));
								partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
								partInfo.setPart_id(partInfoCacheDto.getPart_id());
								partInfo.setPart_info(partInfoCacheDto.getPart_info());
								partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
								partInfo.setStudentid(partInfoCacheDto.getStudentid());
								partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
								partInfo.setTermid(partInfoCacheDto.getTermid());
								partInfo.setTopic(partInfoCacheDto.getTopic());
								partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
								partInfo.setUserid(partInfoCacheDto.getUserid());
								partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
								List<AttachmentDto> list = new ArrayList<AttachmentDto>();
								List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
								if (cacheList != null) {
									for (AttachmentCacheDto dto : cacheList) {
										AttachmentDto attachmentDto = new AttachmentDto();
										attachmentDto.setAttachment_id(dto.getAttachment_id());
										attachmentDto.setAttachment_name(dto.getAttachment_name());
										attachmentDto.setAttachment_path(dto.getAttachment_path());
										attachmentDto.setCmis30id(dto.getCmis30id());
										attachmentDto.setDiscode(dto.getDiscode());
										attachmentDto.setPart_id(dto.getPart_id());
										list.add(attachmentDto);
									}
									Collections.sort(list,new Comparator(){
										public int compare(Object app1, Object app2) {
											try{
												AttachmentDto app11 = (AttachmentDto) app1;
												AttachmentDto app22 = (AttachmentDto) app2;
												return app11.compareTo(app22);                         
											}catch(Exception e){
												e.printStackTrace();
											}         
											return 1;                        
										}
									});
								}
								partInfo.setAttachListForFile(list);
								appraisalList3.add(partInfo);
							}
						}
					}
					Map.put("list3", appraisalList3);
					JSONArray json = JSONArray.fromObject(Map,jsonConfig);
					response.getWriter().write(json.toString());
				}
			}catch(ManagerException ex){
				try {
					response.getWriter().write("##");
				} catch (IOException e) {
				}
				logger.error("saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			} catch (IOException e) {
				try {
					response.getWriter().write("##");
				} catch (IOException e1) {
				}
				logger.error("saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
		return null;
	}
	
	// 上传附件
		private AttachmentDto uploadFile() {
			AttachmentDto attachmentDto = new AttachmentDto();
			// 根盘符
			String rootPath = constantBean.get("mapping_root");
			//
			String realPath = constantBean.get("JLD_upload");
			// 替换为当前系统支持的分隔符
			realPath = realPath.replaceAll("\\\\\\\\", File.separator);
			if (!StringUtil.isEmpty(new_uuid) && StringUtil.isEmpty(old_uuid)
					&& StringUtil.isEmpty(add_uuid)) {
				uuid = new_uuid;
			} else if (StringUtil.isEmpty(add_uuid) && StringUtil.isEmpty(new_uuid)
					&& !StringUtil.isEmpty(old_uuid)) {
				uuid = old_uuid;
			} else if (!StringUtil.isEmpty(add_uuid)
					&& StringUtil.isEmpty(new_uuid)) {
				uuid = add_uuid;
			}
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
				attachmentDto.setAttachment_name((String) map.get("fileName"));
				attachmentDto.setAttachment_path(NoServiceUtil.replaceFileSeparator(trueAddress.replace("/", "\\\\")
						+ File.separator + File.separator + uuid + fileType));
				attachmentDto.setCmis30id(studentDto.getCmis30id());
				attachmentDto.setDiscode(userDto.getDiscode());
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
			return attachmentDto;
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

		/**
		 * 删除自我评价
		 * 
		 * @param request,response,session
		 * @return
		 */
		public void deletePartInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			try{
				if(NestUtil.isNotEmpty(id)){
					this.initParams(id);
					String sectionCode = partInfoServiceExt.getSectionCode(params);
					if ("0".equals(isStartAppraiseCache)) {
						partInfoServiceExt.deletePartinfo(id,userDto.getCmis30id(),userDto.getDiscode());
					} else if ("1".equals(isStartAppraiseCache)) {
						PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
						partInfoCacheDto.setPart_id(id);
						partInfoCacheDto.setCmis30id(userDto.getCmis30id());
						partInfoCacheDto.setDiscode(userDto.getDiscode());
						partInfoCacheDto.setTwo_part_id(evaluateType);
						partInfoCacheDto.setEdu_id(userDto.getEduId());
						partInfoCacheDto.setWrite_man("本人");
						partInfoCacheDto.setTermid(termId);
						partInfoServiceExt.deletePartinfoCache(partInfoCacheDto);
					}
					latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), sectionCode, id);
				}
			}catch (Exception e) {
				logger.error("deletePartInfo(HttpServletRequest,HttpServletResponse,HttpSession)",e);
				try {
					response.getWriter().write("1");
				} catch (IOException e1) {
					logger.error("deletePartInfo(HttpServletRequest,HttpServletResponse,HttpSession)",e);
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
		public void deleteAttachment(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			try{
				if(NestUtil.isNotEmpty(id)){
					if ("0".equals(isStartAppraiseCache)) {
						partInfoServiceExt.deleteAttachment(id);
					} else if ("1".equals(isStartAppraiseCache)) {
						partInfoServiceExt.deleteAttachmentCache(id, part_id, attType);
					}
					JsonConfig jsonConfig=new JsonConfig();
					DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
					jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
					JSONArray arraylist1;
					JSONArray arraylist2;
					JSONObject jmap;
					Map<String,Object> queryMap = new HashMap<String,Object>();
					Map<String,Object> Map = new HashMap<String,Object>();
					queryMap.put("evaluateid",studentDto.getStudentid());
					queryMap.put("termid",termId);
					queryMap.put("cmis30id",studentDto.getCmis30id());
					queryMap.put("discode",studentDto.getDiscode());
					response.setContentType("text/html;charset=UTF-8"); 
					if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
						queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
					}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
						queryMap.remove("evaluateType");
						queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
					}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
						queryMap.remove("evaluateType");
						queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
					}
					if ("0".equals(isStartAppraiseCache)) {
						appraisalList1 = partInfoServiceExt.selectSelfPartInfoWithatt(queryMap);
					} else if ("1".equals(isStartAppraiseCache)) {
						String type = queryMap.get("evaluateType") + "";
						appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
								type, "本人", userDto.getUsername(),PartInfoCacheDto.class);
						appraisalList1 = new ArrayList<PartInfoDto>();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
							PartInfoDto partInfo=new PartInfoDto();
							partInfo.setCreateDate(sdf.parse(partInfoCacheDto.getCreateDate()));
							partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
							partInfo.setPart_id(partInfoCacheDto.getPart_id());
							partInfo.setPart_info(partInfoCacheDto.getPart_info());
							partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
							partInfo.setStudentid(partInfoCacheDto.getStudentid());
							partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
							partInfo.setTermid(partInfoCacheDto.getTermid());
							partInfo.setTopic(partInfoCacheDto.getTopic());
							partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
							partInfo.setUserid(partInfoCacheDto.getUserid());
							partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
							List<AttachmentDto> list = new ArrayList<AttachmentDto>();
							List<AttachmentCacheDto> cacheList = latestEvaluationRecordExt.queryAttachFileInCache(partInfoCacheDto.getPart_id(), "ATTACHMENT",AttachmentCacheDto.class);
							if (cacheList != null) {
								for (AttachmentCacheDto dto : cacheList) {
									AttachmentDto attachmentDto = new AttachmentDto();
									attachmentDto.setAttachment_id(dto.getAttachment_id());
									attachmentDto.setAttachment_name(dto.getAttachment_name());
									attachmentDto.setAttachment_path(dto.getAttachment_path());
									attachmentDto.setCmis30id(dto.getCmis30id());
									attachmentDto.setDiscode(dto.getDiscode());
									attachmentDto.setPart_id(dto.getPart_id());
									list.add(attachmentDto);
								}
								Collections.sort(list,new Comparator(){
									public int compare(Object app1, Object app2) {
										try{
											AttachmentDto app11 = (AttachmentDto) app1;
											AttachmentDto app22 = (AttachmentDto) app2;
											return app11.compareTo(app22);                         
										}catch(Exception e){
											e.printStackTrace();
										}         
										return 1;                        
									}
								});
							}
							partInfo.setAttachListForFile(list);
							appraisalList1.add(partInfo);
						}
					}
					Map.put("list2", appraisalList1);
					JSONArray json = JSONArray.fromObject(Map,jsonConfig);
					response.getWriter().write(json.toString());
				}
			}catch (Exception e) {
				logger.error("deleteAttachment(HttpServletRequest,HttpServletResponse,HttpSession)",e);
				try {
					response.getWriter().write("1");
				} catch (IOException e1) {
					logger.error("deleteAttachment(HttpServletRequest,HttpServletResponse,HttpSession)",e);
				}
			}
		}
		
		public void doSelect(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ParseException{
			JSONArray arraylist1;
			JSONArray arraylist2;
			JsonConfig jsonConfig=new JsonConfig();
			DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
			jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("evaluateid",studentDto.getStudentid());
			queryMap.put("termid",termId);
			queryMap.put("cmis30id",studentDto.getCmis30id());
			queryMap.put("discode",studentDto.getDiscode());
			response.setContentType("text/html;charset=UTF-8"); 
			try {
				if(!("1".equals(evaluateType1))&&!StringUtil.isEmpty(evaluateType1)){
					queryMap.put("evaluateType", Integer.parseInt(evaluateType1));
				}else if(!("1".equals(evaluateType2))&&!StringUtil.isEmpty(evaluateType2)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType2));
				}else if(!("1".equals(evaluateType3))&&!StringUtil.isEmpty(evaluateType3)){
					queryMap.remove("evaluateType");
					queryMap.put("evaluateType", Integer.parseInt(evaluateType3));
				}
				if ("0".equals(isStartAppraiseCache)) {
					appraisalList1 = partInfoServiceExt.selectSelfPartInfo(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = (String)queryMap.get("evaluateType");
					appraisalCacheList1 = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), termId,
							type, "本人", userDto.getUsername(),PartInfoCacheDto.class);
					appraisalList1 = new ArrayList<PartInfoDto>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for (PartInfoCacheDto partInfoCacheDto : appraisalCacheList1) {
						PartInfoDto partInfo=new PartInfoDto();
						partInfo.setCreateDate(sdf.parse(partInfoCacheDto.getCreateDate()));
						partInfo.setIs_attachmen(partInfoCacheDto.getIs_attachmen());
						partInfo.setPart_id(partInfoCacheDto.getPart_id());
						partInfo.setPart_info(partInfoCacheDto.getPart_info());
						partInfo.setSigner_name(partInfoCacheDto.getSigner_name());
						partInfo.setStudentid(partInfoCacheDto.getStudentid());
						partInfo.setSubject_id(partInfoCacheDto.getSubject_id());
						partInfo.setTermid(partInfoCacheDto.getTermid());
						partInfo.setTopic(partInfoCacheDto.getTopic());
						partInfo.setTwo_part_id(partInfoCacheDto.getTwo_part_id());
						partInfo.setUserid(partInfoCacheDto.getUserid());
						partInfo.setWrite_man(partInfoCacheDto.getWrite_man());
						appraisalList1.add(partInfo);
					}
				}
				arraylist1 = JSONArray.fromObject(appraisalList1,jsonConfig);
				response.getWriter().write(arraylist1.toString());
			}catch (IOException e) {
				logger.error("doSelect(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
		}
		
		/**
		 * 删除记录袋
		 * 
		 * @param request,response,session
		 * @return
		 */
		public void deleteAttachwith(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			try{
				if(NestUtil.isNotEmpty(id)){
					this.initParams(id);
					String sectionCode = partInfoServiceExt.getSectionCode(params);
					if ("0".equals(isStartAppraiseCache)) {
						partInfoServiceExt.deleteAttach(id,userDto.getCmis30id(),userDto.getDiscode());
					} else if ("1".equals(isStartAppraiseCache)) {
						PartInfoCacheDto partInfoCacheDto = new PartInfoCacheDto();
						partInfoCacheDto.setPart_id(id);
						partInfoCacheDto.setCmis30id(userDto.getCmis30id());
						partInfoCacheDto.setDiscode(userDto.getDiscode());
						partInfoCacheDto.setEdu_id(userDto.getEduId());
						partInfoCacheDto.setTwo_part_id(evaluateType);
						partInfoCacheDto.setTermid(termId);
						partInfoCacheDto.setWrite_man("本人");
						partInfoServiceExt.deleteAttachCache(partInfoCacheDto);
					}
					latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), sectionCode, id);
				}
			}catch (Exception e) {
				logger.error("deleteAttachment(HttpServletRequest,HttpServletResponse,HttpSession)",e);
				try {
					response.getWriter().write("1");
				} catch (IOException e1) {
					logger.error("deleteAttachment(HttpServletRequest,HttpServletResponse,HttpSession)",e);
				}
			}
		}
		
		public void doAdd(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			try {
				Map<String,Object> Map = new HashMap<String,Object>();
				insertId = baseInforManagerExt.queryProKey("PARTINFO");
				Map.put("insertId", insertId);
				JSONArray json = JSONArray.fromObject(Map);
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				logger.error("doAdd(HttpServletRequest,HttpServletResponse,HttpSession)",e);
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
	private String getSectionName(String sectionId){
		if(Constant.TERMS_BEGIN_ME.equals(sectionId)){
			return "新学期伊始的我";
		}else if(Constant.DEVELOP_TARGET_ME.equals(sectionId)){
			return "新学期伊始的我";
		}else if(Constant.TERMS_END_ME.equals(sectionId)){
			return "学期结束的我";
		}else if(Constant.MORALITY_SELF_APPRAISAL.equals(sectionId)){
			return "思想道德";
		}else if(Constant.MORALITY_RECORD_BAG.equals(sectionId)){
			return "思想道德";
		}else if(Constant.WORKS_SELF_APPRAISAL.equals(sectionId)){
			return "学业成就";
		}else if(Constant.WORKS_SUBJECT_SHOW.equals(sectionId)){
			return "学业成就";
		}else if(Constant.COOPERATION_SELF_APPRAISAL.equals(sectionId)){
			return "合作与交流";
		}else if(Constant.COOPERATION_RECORD_BAG.equals(sectionId)){
			return "合作与交流";
		}else if(Constant.PLAY_SELF_APPRAISAL.equals(sectionId)){
			return "运动与健康";
		}else if(Constant.AESTHETIC_RECORD_BAG.equals(sectionId)){
			return "审美与表现";
		}else if(Constant.AESTHETIC_SELF_APPRAISAL.equals(sectionId)){
			return "审美与表现";
		}else if(Constant.ACTIVITY_RESEARCH_RESULT.equals(sectionId)){
			return "综合实践活动";
		}else if(Constant.ACTIVITY_BASEINFO_1.equals(sectionId)){
			return "综合实践活动";
		}else if(Constant.ACTIVITY_SELF_APPRAISAL_1.equals(sectionId)){
			return "综合实践活动";
		}else if(Constant.ACTIVITY_SELF_APPRAISAL_2.equals(sectionId)){
			return "综合实践活动";
		}else if(Constant.ACTIVITY_BASEINFO_2.equals(sectionId)){
			return "综合实践活动";
		}else if(Constant.INDIVIDUALITY_SELF_APPRAISAL.equals(sectionId)){
			return "个性发展";
		}else if(Constant.INDIVIDUALITY_RECORD_BAG.equals(sectionId)){
			return "个性发展";
		}
		return "";
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
	
	/**
	 * list集合时间展示
	 * @param d
	 * @return
	 */
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
