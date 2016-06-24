package com.flyrish.hades.webapp.action.recordpackage;

/**
 * @author DongJie.Liu
 */
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
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.RecordPackageDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IRecordPackageManagerExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.NginxUploadAction;

@SuppressWarnings("unchecked")
public class ReportPackageAction extends NginxUploadAction {

	@Spring
	IRecordPackageManagerExt recordPackageManagerExt;

	@Spring
	ISelfAppManagerExt selfAppManagerExt;

	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public List<RecordPackageDto> packageList;// 记录袋信息
	
	public List<ArecordpackageCacheDto> packageCacheList;

	public List<AttachDto> attachDtoList;// 附件信息

	public String id;// 更新id
	
	public String updateType;

	public String content;// 评价内容
	
	public String deleteType;

	public String apprasial;// 评价内容

	public String signdate;// 评价时间

	public Integer appraseridentify;// 提供人身份

	public RecordPackageDto recordPackageDto;// 记录袋

	public AttachDto attachDto;// 附件

	public String fname;

	public String fpath;
	
	public int choicenum;

	public String uuid;

	public String old_uuid;

	public String new_uuid;

	public String add_uuid;

	public StudentDto studentDto = new StudentDto();

	public String evaluateType;

	public String evaluateType2;

	public StudentDto evaluated;

	public String termId;// 年级学期
	
	public String termId1;

	public String attachId;// 附件ID

	public Integer reportPackageId;// 记录袋的ID

	public String rpID;// 有数据时回调package的id

	public String new_rpID;// 初始化时回调package的id

	public String add_rpID;// 新加回调package的id

	public int image;

	public Integer personStatus;

	public String evaluatePerson;

	public String personid;
	
	public String levelcode;

	public String trueAddress;//真实路径
	@Before
	public void doBefore(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
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
	public Object saveFile(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		attachDto = uploadFile();
		attachDto.setAttachtype(1);
		attachDto.setImage(1);
		attachDto.setCmis30id(userDto.getCmis30id());
		attachDto.setDiscode(userDto.getDiscode());
		if (StringUtil.isEmpty(rpID) && !StringUtil.isEmpty(new_rpID)) {
			rpID = new_rpID;
		} else if (!StringUtil.isEmpty(add_rpID)
				&& StringUtil.isEmpty(new_rpID)) {
			rpID = add_rpID;
		}
		if (attachDto != null && !StringUtil.isEmpty(rpID)) {
			try {
				if ("0".equals(isStartAppraiseCache)) {
					recordPackageManagerExt.insertAttach(attachDto, rpID);
				} else if ("1".equals(isStartAppraiseCache)) {
					AattachCacheDto attachCacheDto = new AattachCacheDto();
					attachCacheDto.setAttachname(attachDto.getFilename());
					attachCacheDto.setAttachpath(attachDto.getFilepath());
					attachCacheDto.setAttachtype("1");
					attachCacheDto.setImage("1");
					attachCacheDto.setCmis30id(userDto.getCmis30id());
					attachCacheDto.setDiscode(userDto.getDiscode());
					attachCacheDto.setAttachtypeid(rpID);
					recordPackageManagerExt.insertAttachCache(attachCacheDto,evaluateType);
				}
				Map<String,Object> queryMap = new HashMap<String,Object>();
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
				queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				if ("0".equals(isStartAppraiseCache)) {
					packageList=recordPackageManagerExt.selectRecordpackage(queryMap);
				} else if ("1".equals(isStartAppraiseCache)) {
					packageCacheList = latestEvaluationRecordExt.queryRecodeInCache(studentDto.getEduid(), 
							termId, evaluateType, "1", userDto.getUsername(),ArecordpackageCacheDto.class);
							packageList = new ArrayList<RecordPackageDto>();
							if (packageCacheList != null) {
								for (ArecordpackageCacheDto dto : packageCacheList) {
									RecordPackageDto recordPackageDto = new RecordPackageDto();
									recordPackageDto.setRecordid("".equals(dto.getRecordid())?0:Integer.valueOf(dto.getRecordid()));
									recordPackageDto.setStudentid("".equals(dto.getStudentid())?0:Integer.valueOf(dto.getStudentid()));
									recordPackageDto.setSemesterid("".equals(dto.getSemesterid())?0:Integer.valueOf(dto.getSemesterid()));
									recordPackageDto.setAppraisaltypeid("".equals(dto.getAppraisaltypeid())?0:Integer.valueOf(dto.getAppraisaltypeid()));
									recordPackageDto.setContent(dto.getContent());
									recordPackageDto.setAppraseridentify("".equals(dto.getAppraseridentify())?0:Integer.valueOf(dto.getAppraseridentify()));
									recordPackageDto.setSigner(dto.getSigner());
									recordPackageDto.setSigndate(StringToDatexie(dto.getSigndate()));
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
				logger.error(
						"saveFile(HttpServletRequest,HttpServletResponse,HttpSession)",
						e);
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
	public void doSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			RecordPackageDto recordPackageDto = new RecordPackageDto();
			recordPackageDto.setAppraisaltypeid(Integer.valueOf(evaluateType));
			recordPackageDto.setAppraseridentify(Integer.valueOf(personid));
			recordPackageDto.setCmis30id(Integer.valueOf(studentDto
					.getCmis30id()));
			recordPackageDto.setContent(content);
			recordPackageDto.setDiscode(studentDto.getDiscode() + "");
			recordPackageDto.setEdu_id(Integer.valueOf(studentDto.getEduid()));
			recordPackageDto.setSemesterid(Integer.valueOf(termId));
			recordPackageDto.setSigndate(StringToDate(signdate));
			recordPackageDto.setSigner(studentDto.getName());
			recordPackageDto.setStudentid(Integer.valueOf(studentDto
					.getStudentid()));
			AttachDto attachDto = new AttachDto();
			attachDto.setFilename("name");
			attachDto.setFilepath("path");
			attachDto.setAttachtype(1);
			attachDto.setImage(1);
			List<AttachDto> attachs = new ArrayList<AttachDto>();
			attachs.add(attachDto);
			if (recordPackageDto != null && attachs != null) {
				if ("0".equals(isStartAppraiseCache)) {
					rpID = recordPackageManagerExt.insertRecordpackage(recordPackageDto, attachs);
				} else if ("1".equals(isStartAppraiseCache)) {
					ArecordpackageCacheDto recordpackageCacheDto = new ArecordpackageCacheDto();
					recordpackageCacheDto.setAppraisaltypeid(evaluateType);
					recordpackageCacheDto.setAppraseridentify(personid);
					recordpackageCacheDto.setCmis30id(studentDto.getCmis30id());
					recordpackageCacheDto.setContent(content);
					recordpackageCacheDto.setDiscode(studentDto.getDiscode() + "");
					recordpackageCacheDto.setEdu_id(studentDto.getEduid());
					recordpackageCacheDto.setSemesterid(termId);
					recordpackageCacheDto.setSigndate(signdate);
					recordpackageCacheDto.setSigner(studentDto.getName());
					recordpackageCacheDto.setStudentid(studentDto.getStudentid());
					rpID = recordPackageManagerExt.insertRecordpackageCache(recordpackageCacheDto);
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
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), evaluateType, rpID, columnName, userDto.getStudentName(), new Date());
				response.getWriter().write(rpID);
			}
			// PrintWriter writer = response.getWriter().write(buf);
			// writer.write(rpID);
		} catch (ManagerException ex) {
			try {
				response.getWriter().write("##");
			} catch (IOException e) {
			}
			logger.error(
					"doSave(HttpServletRequest,HttpServletResponse,HttpSession)",
					ex);
		} catch (IOException e) {
			try {
				response.getWriter().write("##");
			} catch (IOException ex) {
			}
			logger.error(
					"doSave(HttpServletRequest,HttpServletResponse,HttpSession)",
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
	@SuppressWarnings("unused")
	public Object doUpdate(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			List<RecordPackageDto> listPackage = new ArrayList<RecordPackageDto>();
			RecordPackageDto recordpackage = new RecordPackageDto();
			recordpackage.setRecordid(Integer.valueOf(id));
			recordpackage.setContent(content);
			recordpackage.setSigndate(StringToDate(signdate));
			if(!StringUtil.isEmpty(personid)){
				
				recordpackage.setAppraseridentify(Integer.valueOf(personid));
				
			}else{
				recordpackage.setAppraseridentify(1);
			}
			if (recordpackage != null) {
				if ("0".equals(isStartAppraiseCache)) {
					recordPackageManagerExt.updateRecordpackage(recordpackage);
				} else if ("1".equals(isStartAppraiseCache)) {
					ArecordpackageCacheDto recordpackageCacheDto = new ArecordpackageCacheDto();
					recordpackageCacheDto.setRecordid(id);
					recordpackageCacheDto.setContent(content);
					recordpackageCacheDto.setSigndate(signdate);
					recordpackageCacheDto.setAppraseridentify(recordpackage.getAppraseridentify() + "");
					recordpackageCacheDto.setAppraisaltypeid(updateType);
					recordpackageCacheDto.setSemesterid(termId);
					recordpackageCacheDto.setEdu_id(studentDto.getEduid());
					recordPackageManagerExt.updateRecordpackageCache(recordpackageCacheDto);
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
				latestEvaluationRecordExt.setstudentRecordToCache(userDto.getUsername(), updateType, id, columnName, userDto.getStudentName(), new Date());
				
			/*	Map<String,Object> queryMap = new HashMap<String,Object>();
				Map<String,Object> Map = new HashMap<String,Object>();
				JsonConfig jsonConfig=new JsonConfig();
				DateJsonValueProcessor datejsonvalueprocessor=new DateJsonValueProcessor();
				jsonConfig.registerJsonValueProcessor(Date.class , datejsonvalueprocessor);
				response.setContentType("text/html;charset=UTF-8"); 
				queryMap.put("evaluateid",studentDto.getStudentid());
				queryMap.put("termId",termId);
				queryMap.put("cmis30id",studentDto.getCmis30id());
				queryMap.put("discode",studentDto.getDiscode());
				queryMap.put("evaluateType", Integer.parseInt(evaluateType));
				packageList=recordPackageManagerExt.selectRecordpackage(queryMap);
				Map.put("list2", packageList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());*/
				
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

	// 上传附件
	private AttachDto uploadFile() {
		AttachDto attachDto = new AttachDto();
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
			attachDto.setFilename((String) map.get("fileName"));
			attachDto.setFilepath(NoServiceUtil.replaceFileSeparator(trueAddress.replace("/", "\\\\")
					+ File.separator + File.separator + uuid + fileType));
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
	 * @param request
	 *            ,response,session
	 * @return
	 */
	public void deleteSelfPackage(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			if (NestUtil.isNotEmpty(id)) {
				if ("0".equals(isStartAppraiseCache)) {
					recordPackageManagerExt.deleteSelfPackage(id);
				} else if ("1".equals(isStartAppraiseCache)) {
					ArecordpackageCacheDto recordpackageCacheDto = new ArecordpackageCacheDto();
					recordpackageCacheDto.setRecordid(id);
					recordpackageCacheDto.setAppraisaltypeid(deleteType);
					recordpackageCacheDto.setEdu_id(studentDto.getEduid());
					recordpackageCacheDto.setAppraseridentify("1");
					recordpackageCacheDto.setSemesterid(termId);
					recordPackageManagerExt.deleteSelfPackageCache(recordpackageCacheDto);
				}
				latestEvaluationRecordExt.deletestudentRecordInCache(userDto.getUsername(), deleteType, id);
				/*Map<String,Object> queryMap = new HashMap<String,Object>();
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
				packageList=recordPackageManagerExt.selectRecordpackage(queryMap);
				Map.put("list2", packageList);
				JSONArray json = JSONArray.fromObject(Map,jsonConfig);
				response.getWriter().write(json.toString());*/
			}
		} catch (Exception e) {
			logger.error(
					"deleteSelfPackage(HttpServletRequest,HttpServletResponse,HttpSession)",
					e);
			try {
				response.getWriter().write("1");
			} catch (IOException e1) {
				logger.error(
						"deleteSelfPackage(HttpServletRequest,HttpServletResponse,HttpSession)",
						e);
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
