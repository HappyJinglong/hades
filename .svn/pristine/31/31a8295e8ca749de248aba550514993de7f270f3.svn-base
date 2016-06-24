package com.flyrish.hades.webapp.action.bookreport;

import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.data.Json;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.ReportItme;
import com.flyrish.hades.dto.Reportstatus;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBookReportExt;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.AntZip;
import com.flyrish.hades.util.NoServiceUtil;
import com.flyrish.hades.webapp.action.masterReport.ExportAction;

public class QuBookreportAction extends ExportAction{
	@Spring
	public IRedisServiceExt redisServiceExt;
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
	}
	
	 
	/**
	 * service具体逻辑 -高中报告册
	 * */
	@Spring
	public IBookReportExt bookReportExt;
	
	
	//学校列表
	public List<SchoolreportDto> schoolList;
	
	//学校下拉框
	public List<SchoolreportDto> schoolSelectList;
	
	public String error;
	
	 /**
	 * 查询参数
	 */ 
	public Map<String,Object>params = new HashMap<String, Object>();
	/**
	 * 默认action
	 * */
	@DefaultAction
	public Object defaultQueryAction(HttpServletRequest request, HttpServletRequest req){
	schoolList=bookReportExt.getSchoolReported("110105", null);
		return "qubookreport.jsp";
	}
	
	
	//区县id
	public String discode;
	
	//学校名字
	public String schoolname;
	
	/**
	 * 合计
	 * */
	public Integer sbzq=0;  //上报正确人数总和
	
	public Integer jyzq=0;  //校验正确人数总和
	
	public Integer jysb=0;  //校验失败人数总和
	
	/**
	 * 接口 已上报学校
	 * */
	public Object getYSBschool(HttpServletRequest request, HttpServletRequest req){
		//学校列表
		schoolList=bookReportExt.getSchoolReported(discode, schoolname);
		if(null!=schoolList||schoolList.size()>1){
			for(SchoolreportDto s: schoolList){
				if(null!=s.getReportedCount()){
					sbzq=sbzq+s.getReportedCount();
				}
				if(null!=s.getVerficationSuccessCount()){
					jyzq=jyzq+s.getVerficationSuccessCount();
				}
				if(null!=s.getVerficationFailedCount()){
					jysb=jysb+s.getVerficationFailedCount();
				}
				
			
			}
		}
		//学校下拉框
		schoolSelectList=bookReportExt.getSchoolSelectList(discode);
		
		
		return "qubookreport.jsp";
	}
	/**
	 * 接口未上报学校列表
	 * */
	public Object getNoschool(HttpServletRequest request, HttpServletRequest req){
		schoolList=	bookReportExt.getNoSchollreportList(discode);
		return "NoSchoolbookreport.jsp";
	}
	
	/**
	 * 条件查询 查询单个学校
	 * */
	public Object getSchoolByDiscode(HttpServletRequest request, HttpServletRequest req){
		schoolList=bookReportExt.getSchoolOne(cmis30id);
		schoolSelectList=bookReportExt.getSchoolSelectList(discode);
		if(null!=schoolList||schoolList.size()>1){
			for(SchoolreportDto s: schoolList){
				if(null!=s.getReportedCount()){
					sbzq=sbzq+s.getReportedCount();
				}
				if(null!=s.getVerficationSuccessCount()){
					jyzq=jyzq+s.getVerficationSuccessCount();
				}
				if(null!=s.getVerficationFailedCount()){
					jysb=jysb+s.getVerficationFailedCount();
				}
				
			
			}
		}
		return "qubookreport.jsp";
	}
	/**
	 * 下载状态码
	 */ 
	public String dStatus;
	
	 /**
	 * 报告册导出-区县-学校
	 * *//* 
	*//**
	 * 班级为单位导出
	 * @throws ForceException 
	 * */ 
	@Json
	public Object exportZipByXuex(HttpServletRequest req,HttpServletResponse res) throws ForceException{
		params.put("cmis30id", cmis30id);
		String str="";
		String zipName = "";
		String bigzipName="";
		//Object [] obj=new Object[]{};
		//班级classid数组
		redisServiceExt.save(dStatus, "0");
		List<ClassDto> classids=bookReportExt.getClasssById(cmis30id);
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(new Date());
		
		String uuid = NoServiceUtil.getGuid();    //产生uuid
		BigInteger uuid1,div,result;
		int hashcode = uuid.hashCode();
		uuid1 = new BigInteger(hashcode+"");
		div = new BigInteger("10");
		result = uuid1.mod(div); 
		String url = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path"))+	
				File.separator+"tempZipfile"+File.separator+time+File.separator+result+File.separator+uuid+File.separator;
		  
		try {
			List<File>zipList=new ArrayList<File>();
			if(null!=classids){
				for(int i=0;i<classids.size();i++){
					
					//先查出来班主任id
					params.put("techerid", classids.get(i).getClassName());
					params.put("campusid", classids.get(i).getJiebie());
					params.put("discode", classids.get(i).getTotalCount());
					
					List<String> eduIdc = null;
					//查询学生eduid 根据classid
					 List<String>  edus=bookReportExt.getStudenteduidByclassidSB(String.valueOf(classids.get(i).getClassId()), classids.get(i).getJiebie());
					params.put("classid", classids.get(i).getClassId());
					if(null!=edus){
						 params.put("eduIds", edus);
					}else{
						params.put("eduIds", "-1");
					}
						/**
						 * tomcat
						 * */
					bigzipName =  classids.get(i).getXueduan()+".zip";
						
						
					String templteUrl =  NoServiceUtil.replaceFileSeparator(req.getRealPath("")+constantBean.get("reportfilePath"));
						//将数据扔到ngix文件服务器
						
					zipName = url+File.separator+classids.get(i).getClassnum()+"班"+".zip";
					bigzipName = url+File.separator+classids.get(i).getXueduan()+".zip";  
					List<StudentDto> studentBaseInfos = masterReportExt.queryStudentBaseInfos(params);
					List<File> fileList=this.initExportExcelData1(studentBaseInfos,templteUrl,url,zipName);
					AntZip.createExcelZip(fileList, zipName);
					AntZip.deleteFileList(fileList);
					zipList.add(new File(zipName));
					str =constantBean.get("nginx_server")+"//tempZipfile"+"//"+time+"//"+result+"//"+uuid+"//"+classids.get(i).getXueduan()+".zip";
				}  
		 	}
			AntZip.createExcelZip(zipList, bigzipName);
			redisServiceExt.save(dStatus, "1");
			String key =dStatus+"1";
			redisServiceExt.save(key, str);
			JSONArray json = new JSONArray();
			json.add(str);
			String ret = json.toString();
		 	return JSONObject.fromObject("{vals:"+ret+"}");
		} catch (Exception e) {
			e.printStackTrace();
				redisServiceExt.save(dStatus, "2");
		}
		return null;
	}
	@Json
	public Object returnURL(HttpServletRequest req,HttpServletResponse res) throws ForceException{
		String url=redisServiceExt.readSingle(dStatus+"1");
		redisServiceExt.delete(dStatus+"1");
		JSONArray json = new JSONArray();
		json.add(url);
		String ret = json.toString();
	 	return JSONObject.fromObject("{vals:"+ret+"}");
	}
	private List<File> initExportExcelData1(List<StudentDto> studentBaseInfos,String fUrl,String url,String zipName) throws ManagerException{
		List<File> zipList = new ArrayList<File>();
		//全部学生
		List<File> fileList = new ArrayList<File>();
		if(null!=studentBaseInfos && studentBaseInfos.size()>0){
			List<AppraisalDto> personalAppral = null;
			List<AppraisalDto> studyAppral = null;
			//个性发展
			List<AppraisalDto> outPersonalAppralSelf = masterReportExt.queryOutPersonalAppral(params,"self");
			List<AppraisalDto> outPersonalAppralExtra = masterReportExt.queryOutPersonalAppral(params,"");
			//研究性学习
			List<AppraisalDto> outStudyAppral = masterReportExt.queryOutStudyAppral(params);
			//班主任评语
			List<AppraisalDto> masterAppraise = masterReportExt.queryMasterAppral(params);
			//成绩
			List<ModelScoreDto>allScore = masterReportExt.queryAllScore(params);
			//会考成绩
			Map<String,String> hkScore = masterReportExt.queryHKScore(params);
			//体质健康数据
			List<HealthDataDto>healthDatas = masterReportExt.queryHealthDdatas(params);
			//体检数据
			List<CheckItemInfoDto>checkItems = masterReportExt.queryCheckItems(params);
			
			
			for(StudentDto sDto : studentBaseInfos){
				//以学生为单位  生成zip文件名
				String zipFileName = "";
				try{
					//文件名称
					String fileName1 = "";
					String fileName2 = sDto.getName()+"（"+sDto.getStudentno()+"）.xls";
					//1.封面 OK
					List<AppraisalDto> personalAppralSelf = null;
					List<AppraisalDto> personalAppralExtra = null;
					List<AppraisalDto> singleStudyAppral = null;
					//2.1个性发展 自我评价
					AppraisalDto outSinglePersonalAppraiseSelf = masterReportExt.initOutSinglePersonalAppraiseSelf(outPersonalAppralSelf,sDto);
					//2.2个性发展 自我评价
					List<AppraisalDto> outSinglePersonalAppraiseExtra = masterReportExt.initOutSinglePersonalAppraiseExtra(outPersonalAppralExtra,sDto);
					//3.评语
					List<AppraisalDto> masterAppral = masterReportExt.initSingleMasterAppraise(sDto,masterAppraise);
					//4.成绩学分 
					 Map<String, Map<String,List<ModelScoreDto>>> singleScore = masterReportExt.initSingleScore(sDto,allScore);
					//5.研究性学习
					List<AppraisalDto> outSingleStudyAppral = masterReportExt.initOutSinglePersonalAppraiseExtra(outStudyAppral,sDto);
					//6.体质健康
					List<HealthDataDto>singleHealthData = masterReportExt.querySingleHealthData(sDto,healthDatas);
					//7.健康体检
					List<CheckItemInfoDto> sigleCheckItems = masterReportExt.querySigleCheckItems(sDto,checkItems);
					//生成学生评价数据
					//生成报告单数据
					AntZip.outPutStreamFileMethod(url, fileName2, new File(fUrl));
				 	File fileBGD = new File(url+File.separator+fileName2);
					//文件是否存在
					if(fileBGD.exists()){
						masterReportExt.writeReportExcel(fileBGD, sDto,masterAppral,outSinglePersonalAppraiseSelf,outSinglePersonalAppraiseExtra,outSingleStudyAppral,singleHealthData,sigleCheckItems,singleScore,"2", hkScore);
					} 
					
					File file = null;
					file = new File(url+File.separator+fileName2);
					fileList.add(file);
					 
				}catch(ManagerException me){
					me.printStackTrace();
					//删除excel文件
					if(StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				}catch(Exception e){
					e.printStackTrace();
					//删除excel文件
					if(StringUtil.isNotEmpty(zipFileName))
						fileList.add(new File(zipFileName));
					AntZip.deleteFileList(fileList);
					continue;
				}
			}
				
		}
		return fileList;
	}
	@Spring
	public IMasterReportExt masterReportExt;
	
	
	//返回页面的错误人列表
	public List<ReportItme> studentlist;
	
	//需要参数 学校id
	public Integer cmis30id;
    /**
     * 校验错误人列表
     * */
	public Object getErrorstudentlist(HttpServletRequest request, HttpServletRequest req){
		UserDto  udto=this.getLoginInfo(req);
		studentlist = new ArrayList();
		//班级下错误人数集合
		List<StudentDto> stulist;
		
		stulist=bookReportExt.getQuErrorRepor(cmis30id);
		
		if(stulist.size()>0){
			for(StudentDto stu:stulist){
				ReportItme resitm = new ReportItme();
				resitm.setStudentname(stu.getName()); //名字
				resitm.setStudentno(stu.getStudentno()); //学籍号
				resitm.setEduid(stu.getEduid()); //eduid
				//取出每个学生”缺“
				 List<Reportstatus> rstat=bookReportExt.getStudenterrorItmeBystuId(stu.getStudentid());
				 
				 if(rstat.size()>0){
					 //取出每一项
					 for(Reportstatus r :rstat){
						 switch (Integer.valueOf(r.getVerfifykind())) {
						 case 1301398:  //姓名
								resitm.setName(r.getVerfifyresult());
								break;
						 case 1301397:  //性别
								resitm.setSex(r.getVerfifyresult());
								break;
						 case 1301396:  //年龄
								resitm.setAge(r.getVerfifyresult());
								break;
						 case 1301395:  //班级
								resitm.setGrade(r.getVerfifyresult());
								break;
						case 1301394:  //学籍号
							resitm.setStudentCode(r.getVerfifyresult());
							break;
						case 1301399:  //毕业学校
							resitm.setSchool(r.getVerfifyresult());
							break;
						case 1301307:  //自我评价
							resitm.setSelfappraisal(r.getVerfifyresult());
							break;
						case 1301308:  //成果展示1
							resitm.setShow1(r.getVerfifyresult());
							break;
						case 1301309:  //成果展示2
							resitm.setShow2(r.getVerfifyresult());
							break;
						case 1301310:  //成果展示3
							resitm.setShow3(r.getVerfifyresult());
							break;
						case 1301311:  //高一 senior1
							resitm.setSenior1(r.getVerfifyresult());
							break;
						case 1301312:  //高二
							resitm.setSenior2(r.getVerfifyresult());
							break;
						case 1301313:  //高三
							resitm.setSenior3(r.getVerfifyresult());
							break;
						case 1301315:  //内容摘要 
							resitm.setDigest1(r.getVerfifyresult());
							break;
						case 1301317:  //内容摘要2
							resitm.setDigest2(r.getVerfifyresult());
							break;
						case 1301319:  //内容摘要3     
							resitm.setDigest3(r.getVerfifyresult());
							break;
						case 1301314:  //标题1 
							resitm.setHeadline1(r.getVerfifyresult());
							break;
						case 1301316:  //标题2
							resitm.setHeadline2(r.getVerfifyresult());
							break;
						case 1301318:  //标题3
							resitm.setHeadline3(r.getVerfifyresult());
							break;
						case 1301205:  //学生体质健康数据明细
							resitm.setConstitution(r.getVerfifyresult());
							break;
						case 1301206:  //体检
							resitm.setPhysical(r.getVerfifyresult());
							break;
							
						case 1301207:  //必修116  
							resitm.setRequired(r.getVerfifyresult());
							break;
							
						case 1301208:  //选修22
							resitm.setElective(r.getVerfifyresult());
							break;
							
						case 1301209:  //校本
							resitm.setEdition(r.getVerfifyresult());
							break;
							
						case 1301210:  //总学分144
							resitm.setCredit(r.getVerfifyresult());
							break;
						case 10004:  //总学分144 为空
							resitm.setCreditnull(r.getVerfifyresult());
							break;
						case 10002:  //必修为空 requirednull
							resitm.setRequirednull(r.getVerfifyresult());
							break;
						case 10003:  //选修为空 electivenull
							resitm.setElectivenull(r.getVerfifyresult());
							break;
						case 10001:  //校本为空  editionnull
							resitm.setEditionnull(r.getVerfifyresult());
							break;
						case 1301221:  //会考缺成绩 
							resitm.setHuikaochngji(r.getVerfifyresult());
							break;
						case 1301222:  //会考缺成绩
							resitm.setHuikaoadcd(r.getVerfifyresult());
							break;
						default:
							break;
						}
					 }
				 }
				 studentlist.add(resitm);
			}
		}
		
		
		return "Querrorreportlist.jsp";
	}
}
