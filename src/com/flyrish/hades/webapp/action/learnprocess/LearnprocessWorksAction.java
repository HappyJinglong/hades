package com.flyrish.hades.webapp.action.learnprocess;

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

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.LearnprocessWorksDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ILearnprocessWorksServiceExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;


public class LearnprocessWorksAction extends NginxUploadAction{
	@Spring
	ISelfAppManagerExt selfAppManagerExt;
	/**
	 * 处理记录袋业务类
	 */
	@Spring
	ILearnprocessWorksServiceExt learnprocessWorksServiceExt;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	/**
	 * 学业成就
	 */
	public List<LearnprocessWorksDto> learnprocessWorksList;
	
	public List<AlearnprocessWorksCacheDto> learnprocessWorksCacheList;

	public List<AttachDto> attachDtoList;//附件信息
	
	public String evaluatePersonName;//被评价人姓名
	
	public String id;//更新id

	public String content;//评价内容
	
	public String apprasial;//评价内容

	public Integer appraseridentify;//提供人身份

	public LearnprocessWorksDto learnprocessWorksDto;//学业成就
	
	public AttachDto attachDto;//附件
	
	public String fname;
	
	public String fpath;
	
	public String uuid;
	
	public String old_uuid;
	
	public String new_uuid;
	
	public String add_uuid;
	
	public StudentDto studentDto = new StudentDto();
	
	public String evaluateType;
	
	public String evaluateType2;
	
	public String type;
	
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

	public String signdate;
	
	public Integer levelcode;
	
	public String trueAddress;//真实路径
	@Before
	public void doBefore(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:levelCode;
		studentDto.setClassid(Integer.valueOf(userDto.getClassid()));
		studentDto.setName(userDto.getStudentName());
		studentDto.setGradenum(userDto.getGradenum());
		studentDto.setStudentid(userDto.getPersonid());
		studentDto.setCmis30id(userDto.getCmis30id());
		studentDto.setDiscode(Integer.valueOf(userDto.getDiscode()));
		studentDto.setEduid(userDto.getEduId());
		studentDto.setTermtype(userDto.getTermtype());
		studentDto.setLevelcode(Integer.valueOf(userDto.getLevelcode()));
		evaluatePersonName = userDto.getStudentName();
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
		attachDto.setAttachtype(4);
		attachDto.setImage(1);
		attachDto.setCmis30id(userDto.getCmis30id());
		attachDto.setDiscode(userDto.getDiscode());
		String attType = "8010";
		if(StringUtil.isEmpty(rpID)&&!StringUtil.isEmpty(new_rpID)){
			rpID = new_rpID;
		}
		else if(!StringUtil.isEmpty(add_rpID)&&StringUtil.isEmpty(new_rpID)){
			rpID = add_rpID;
		}if(attachDto!=null&&!StringUtil.isEmpty(rpID)){
			try {
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.insertAttach(attachDto, rpID);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachtype(attachDto.getAttachtype().toString());
					attachCacheDto.setAttachname(attachDto.getFilename().toString());
					attachCacheDto.setAttachpath(attachDto.getFilepath().toString());
					attachCacheDto.setImage(attachDto.getImage().toString());
					attachCacheDto.setCmis30id(attachDto.getCmis30id().toString());
					attachCacheDto.setDiscode(attachDto.getDiscode().toString());
					attachCacheDto.setAttachtypeid(rpID);
					learnprocessWorksServiceExt.insertAttachCache(attachCacheDto,attType);
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
				queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksList = learnprocessWorksServiceExt.selectLearnprocessWorks(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = Constant.TYPE_XY_GCJL;
					learnprocessWorksCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(),
							termId, type, "1", userDto.getUsername(),AlearnprocessWorksCacheDto.class);
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
				Map.put("list2", learnprocessWorksList);
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
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	public void doSave(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		try{
			LearnprocessWorksDto learnprocessWorksDto = new LearnprocessWorksDto();
				learnprocessWorksDto.setCmis30id(studentDto.getCmis30id());
				learnprocessWorksDto.setDiscode(studentDto.getDiscode()+"");
				learnprocessWorksDto.setEdu_id(studentDto.getEduid());
				learnprocessWorksDto.setSemesterid(termId);
				learnprocessWorksDto.setStudentid(studentDto.getStudentid());
				learnprocessWorksDto.setProcessdesc(processdesc);
				learnprocessWorksDto.setSigndate(StringToDate(signdate));
				learnprocessWorksDto.setSubject(subject);
				learnprocessWorksDto.setEdu_id(studentDto.getEduid());
				AttachDto attachDto = new AttachDto();
				attachDto.setFilename("name");
				attachDto.setFilepath("path");
				attachDto.setAttachtype(4);
				attachDto.setImage(1);
				List<AttachDto> attachs = new ArrayList<AttachDto>();
				attachs.add(attachDto);
				if(learnprocessWorksDto!=null&&attachs!=null){
					if ("0".equals(isStartAppraiseCache)) {
						rpID = learnprocessWorksServiceExt.insertLearnprocessWorks(learnprocessWorksDto, attachs);
					} else if ("1".equals(isStartAppraiseCache)) {
						AlearnprocessWorksCacheDto learnprocessWorksCacheDto = new AlearnprocessWorksCacheDto();
						learnprocessWorksCacheDto.setCmis30id(studentDto.getCmis30id());
						learnprocessWorksCacheDto.setDiscode(studentDto.getDiscode()+"");
						learnprocessWorksCacheDto.setEdu_id(studentDto.getEduid());
						learnprocessWorksCacheDto.setSemesterid(termId);
						learnprocessWorksCacheDto.setStudentid(studentDto.getStudentid());
						learnprocessWorksCacheDto.setProcessdesc(processdesc);
						learnprocessWorksCacheDto.setSigndate(signdate);
						learnprocessWorksCacheDto.setSubject(subject);
						learnprocessWorksCacheDto.setAppraseridentify("1");
						AattachCacheDto attachCacheDto = new AattachCacheDto();
						attachCacheDto.setAttachname("name");
						attachCacheDto.setAttachpath("path");
						attachCacheDto.setAttachtype("4");
						attachCacheDto.setImage("1");
						rpID = learnprocessWorksServiceExt.insertLearnprocessWorksCache(learnprocessWorksCacheDto,attachCacheDto,type);
					}
					latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), "8010", rpID, "学业成就", userDto.getStudentName(), new Date());
					
					/*Map<String,Object> queryMap = new HashMap<String,Object>();
					Map<String,Object> Map = new HashMap<String,Object>();
					JsonConfig jsonConfig=new JsonConfig();
					DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
					jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
					Map.put("rpID", rpID);
					response.setContentType("text/html;charset=UTF-8"); 
					evaluated = studentDto;
					queryMap.put("evaluateid",studentDto.getStudentid());
					queryMap.put("termId",termId);
					queryMap.put("cmis30id",studentDto.getCmis30id());
					queryMap.put("discode",studentDto.getDiscode());
					queryMap.put("evaluateType", Integer.parseInt(evaluateType));
					learnprocessWorksList = learnprocessWorksServiceExt.selectLearnprocessWorks(queryMap);
					Map.put("list2", learnprocessWorksList);
					JSONArray json = JSONArray.fromObject(Map,jsonConfig);*/
					response.getWriter().write(rpID);
				}
				//PrintWriter writer = response.getWriter().write(buf);
				//writer.write(rpID);
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
	public Object doUpdate(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			List<LearnprocessWorksDto> listLearnprocessWorks = new ArrayList<LearnprocessWorksDto>();
			LearnprocessWorksDto learnprocessWorksDto = new LearnprocessWorksDto();
			learnprocessWorksDto.setWorkid(id);
			learnprocessWorksDto.setProcessdesc(processdesc);
			learnprocessWorksDto.setSubject(subject);
			learnprocessWorksDto.setSigndate(StringToDate(signdate));
			if(listLearnprocessWorks!=null){
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.updateLearnprocessWorks(learnprocessWorksDto);
				} else if ("1".equals(isStartAppraiseCache)) {
					String type = "8010";
					AlearnprocessWorksCacheDto learnprocessWorksCacheDto = new AlearnprocessWorksCacheDto();
					learnprocessWorksCacheDto.setWorkid(id);
					learnprocessWorksCacheDto.setProcessdesc(processdesc);
					learnprocessWorksCacheDto.setSubject(subject);
					learnprocessWorksCacheDto.setSigndate(signdate);
					learnprocessWorksCacheDto.setEdu_id(studentDto.getEduid());
					learnprocessWorksCacheDto.setSemesterid(termId);
					learnprocessWorksCacheDto.setAppraseridentify("1");
					learnprocessWorksServiceExt.updateLearnprocessWorksCache(learnprocessWorksCacheDto, type);
				}
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), "8010", id, "学业成就", userDto.getStudentName(), new Date());
				
				/*Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(D
			/*	Map<String,Object> queryMap = new HashMap<String,Object>();
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
				queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				learnprocessWorksList = learnprocessWorksServiceExt.selectLearnprocessWorks(queryMap);
				Map.put("list2", learnprocessWorksList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());*/
			}
			}catch(ManagerException ex){
				try {
					response.getWriter().write("##");
				} catch (IOException e) {
				}
				logger.error("doUpdate(HttpServletRequest,HttpServletResponse,HttpSession)",ex);
			} 
		return null;
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
	/**
	 * 删除记录袋和附件
	 * 
	 * @param request,response,session
	 * @return
	 */
	public void deleteLearnprocessWorks(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		try{
			if(NestUtil.isNotEmpty(id)){
				if ("0".equals(isStartAppraiseCache)) {
					learnprocessWorksServiceExt.deleteLearnprocessWorks(id);
				} else if ("1".equals(isStartAppraiseCache)) {
					String two_part_id = "8010";
					AlearnprocessWorksCacheDto learnprocessWorksCacheDto = new AlearnprocessWorksCacheDto();
					learnprocessWorksCacheDto.setWorkid(id);
					learnprocessWorksCacheDto.setAppraseridentify("1");
					learnprocessWorksCacheDto.setEdu_id(studentDto.getEduid());
					learnprocessWorksCacheDto.setSemesterid(termId);
					learnprocessWorksServiceExt.deleteLearnprocessWorksCache(learnprocessWorksCacheDto,two_part_id);
				}
				latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), "8010", id);
			}
		}catch (Exception e) {
			logger.error("deleteLearnprocessWorks(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error("deleteLearnprocessWorks(HttpServletRequest,HttpServletResponse,HttpSession)",e);
			}
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

}
