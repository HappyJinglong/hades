package com.flyrish.hades.webapp.action.masterReport;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.AntZip;
import com.flyrish.hades.util.NoServiceUtil;

public class ReportExportAction extends ExportAction{
	@Spring
	public IMasterReportExt masterReportExt;
	/**
	 * 查询参数
	 */
	public Map<String,Object>params = new HashMap<String, Object>();
	List<String>eduId = new ArrayList<String>();
	/**
	 * 班级id
	 */
	public String classId;
	/**
	 * 选择学生教育id
	 */
	public String eduIds;
	/**
	 * 标记是否导出打印模板
	 */
	public String flag;
	/**
	 * 下载状态码
	 */
	public String dStatus;
	public Object toMasterPage(){
		return "masterReportList.jsp";
	}
	@Before
	public void initData(HttpServletRequest req){
		this.getLoginInfo(req);
		this.initParams();
	}
	
	@DefaultAction
	public Object exportZip(HttpServletRequest req,HttpServletResponse res){
		String zipName = "";
		try {
			redisServiceExt.save(dStatus, "0");
			this.putTempParam();
			//1.获取班主任老师班级信息
			List<CampusDto> teacherInfos = masterReportExt.queryTeacherInfos(params);
			if(null!=teacherInfos && teacherInfos.size()>0){
				//String templteUrl =  NoServiceUtil.replaceFileSeparator(req.getRealPath("")+constantBean.get("reportfilePath"));
				//获得html模板路径
				String templteUrl =  NoServiceUtil.replaceFileSeparator(req.getRealPath("")+constantBean.get("reportfilePathHtml"));
				//String url = templteUrl.substring(0, templteUrl.lastIndexOf(File.separator+File.separator));
				//将数据扔到ngix文件服务器
				String uuid = NoServiceUtil.getGuid();    //产生uuid
				BigInteger uuid1;
                BigInteger div;
                BigInteger result;
				int hashcode = uuid.hashCode();
				uuid1 = new BigInteger(hashcode+"");
				div = new BigInteger("10");
				result = uuid1.mod(div);
				String url = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path"))+
					File.separator+"zipfile"+File.separator+result+File.separator+uuid+File.separator;
				
				zipName = this.getZipName(teacherInfos,url,flag);
				List<StudentDto> studentBaseInfos = masterReportExt.queryStudentBaseInfos(params);
				//this.initExportExcelData(studentBaseInfos,templteUrl,url,zipName);
				this.initExportHtmlData(studentBaseInfos,templteUrl,url,zipName);
				redisServiceExt.save(dStatus, "1");
				return exportExcel(res, new File(zipName),true);
			}
		} catch (Exception e) {
			try {
				redisServiceExt.save(dStatus, "2");
				return exportExcel(res, new File(zipName),true);
			} catch (ForceException e1) {
				e1.printStackTrace();
			}
		}
		return null;
		
	}

    private void initExportHtmlData(List<StudentDto> studentBaseInfos,String fUrl,String url,String zipName) throws ManagerException{
        List<File> zipList = new ArrayList<File>();
        if(null!=studentBaseInfos && studentBaseInfos.size()>0){
            List<AppraisalDto> personalAppral = null;
            List<AppraisalDto> studyAppral = null;
            if("1".equals(flag)){
                personalAppral = masterReportExt.queryPersonalAppral(params);
                studyAppral = masterReportExt.queryStudyAppral(params);
            }
            //个性发展
            List<AppraisalDto> outPersonalAppralSelf = masterReportExt.queryOutPersonalAppral(params,"self");
            List<AppraisalDto> outPersonalAppralExtra = masterReportExt.queryOutPersonalAppral(params,"");
            //研究性学习
            List<AppraisalDto> outStudyAppral = masterReportExt.queryOutStudyAppral(params);
            //班主任评语
            List<AppraisalDto> masterAppraise = masterReportExt.queryMasterAppral(params);
            //会考成绩
            Map<String,String> hkScore = masterReportExt.queryHKScore(params);
            //成绩
            List<ModelScoreDto>allScore = masterReportExt.queryAllScore(params);
            //体质健康数据
            List<HealthDataDto>healthDatas = masterReportExt.queryHealthDdatas(params);
            //体检数据
            List<CheckItemInfoDto>checkItems = masterReportExt.queryCheckItems(params);
            List<File> fileList = new ArrayList<File>();
            for(StudentDto sDto : studentBaseInfos){
                //以学生为单位  生成zip文件名
                String zipFileName = "";
                try{
                    //文件名称
                    String fileName1 = "";
                    if("1".equals(flag)){
                        fileName1 = sDto.getName()+"（"+sDto.getStudentno()+"）评价数据.xls";
                    }
                    //String fileName2 = sDto.getName()+"（"+sDto.getStudentno()+"）.xls";
                    String fileName2 = sDto.getName()+"（"+sDto.getStudentno()+"）.html";
                    //1.封面 OK
                    List<AppraisalDto> personalAppralSelf = null;
                    List<AppraisalDto> personalAppralExtra = null;
                    List<AppraisalDto> singleStudyAppral = null;
                    if("1".equals(flag)){
                        personalAppralSelf = masterReportExt.initSinglePersonalSelf(sDto,personalAppral,"selef");
                        personalAppralExtra = masterReportExt.initSinglePersonalSelf(sDto,personalAppral,"");
                        singleStudyAppral = masterReportExt.initsingleStudyAppral(sDto,studyAppral);
                    }
                    //2.1个性发展 自我评价
                    AppraisalDto outSinglePersonalAppraiseSelf = masterReportExt.initOutSinglePersonalAppraiseSelf(outPersonalAppralSelf,sDto);
                    //2.2个性发展 自我评价
                    List<AppraisalDto> outSinglePersonalAppraiseExtra = masterReportExt.initOutSinglePersonalAppraiseExtra(outPersonalAppralExtra,sDto);
                    //3.评语
                    List<AppraisalDto> masterAppral = masterReportExt.initSingleMasterAppraise(sDto,masterAppraise);
                    //4.成绩学分
                    Map<String, Map<String,List<ModelScoreDto>>>singleScore = masterReportExt.initSingleScore(sDto,allScore);
                    //5.研究性学习
                    List<AppraisalDto> outSingleStudyAppral = masterReportExt.initOutSinglePersonalAppraiseExtra(outStudyAppral,sDto);
                    //6.体质健康
                    List<HealthDataDto>singleHealthData = masterReportExt.querySingleHealthData(sDto,healthDatas);
                    //7.健康体检
                    List<CheckItemInfoDto> sigleCheckItems = masterReportExt.querySigleCheckItems(sDto,checkItems);
                    //生成学生评价数据
                    if("1".equals(flag)){
                        masterReportExt.writeAppraiseData(url,fileName1,personalAppralSelf,personalAppralExtra,singleStudyAppral);
                    }
                    //生成报告单数据
                    AntZip.outPutStreamFileMethod(url, fileName2, new File(fUrl));
                    File fileBGD = new File(url+File.separator+fileName2);
                    //文件是否存在
                    if(fileBGD.exists()){
                        //masterReportExt.writeReportExcel(fileBGD, sDto,masterAppral,outSinglePersonalAppraiseSelf,outSinglePersonalAppraiseExtra,outSingleStudyAppral,singleHealthData,sigleCheckItems,singleScore,flag,hkScore);
                        masterReportExt.writeReportHtml(fileBGD, sDto,masterAppral,outSinglePersonalAppraiseSelf,outSinglePersonalAppraiseExtra,outSingleStudyAppral,singleHealthData,sigleCheckItems,singleScore,flag,hkScore);
                    }

                    File file = null;
                    if("1".equals(flag)){
                        fileList = new ArrayList<File>();
                        file = new File(url+File.separator+fileName1);
                        fileList.add(file);
                    }
                    file = new File(url+File.separator+fileName2);
                    fileList.add(file);
                    if("1".equals(flag)){
                        //压缩评价，报告单
                        zipFileName = url+File.separator+sDto.getName()+"（"+sDto.getStudentno()+"）.zip";
                        AntZip.createExcelZip(fileList, zipFileName);
                        zipList.add(new File(zipFileName));
                        AntZip.deleteFileList(fileList);
                    }
                }catch(ManagerException me){
                    //删除excel文件
                    if(StringUtil.isNotEmpty(zipFileName))
                        fileList.add(new File(zipFileName));
                    AntZip.deleteFileList(fileList);
                    continue;
                }catch(Exception e){
                    //删除excel文件
                    if(StringUtil.isNotEmpty(zipFileName))
                        fileList.add(new File(zipFileName));
                    AntZip.deleteFileList(fileList);
                    continue;
                }
            }
            if("1".equals(flag)){
                //压缩全部学生为完整文件
                AntZip.createExcelZip(zipList, zipName);
                //删除学生Zip文件
                AntZip.deleteFileList(zipList);
            }else{
                //压缩全部学生为完整文件
                AntZip.createExcelZip(fileList, zipName);
                //删除学生Zip文件
                AntZip.deleteFileList(fileList);
            }
        }
    }


    @Spring
	public IRedisServiceExt redisServiceExt;

	private void initExportExcelData(List<StudentDto> studentBaseInfos,String fUrl,String url,String zipName) throws ManagerException {
        List<File> zipList = new ArrayList<File>();
        if (null != studentBaseInfos && studentBaseInfos.size() > 0) {
            List<AppraisalDto> personalAppral = null;
            List<AppraisalDto> studyAppral = null;
            if ("1".equals(flag)) {
                personalAppral = masterReportExt.queryPersonalAppral(params);
                studyAppral = masterReportExt.queryStudyAppral(params);
            }
            //个性发展
            List<AppraisalDto> outPersonalAppralSelf = masterReportExt.queryOutPersonalAppral(params, "self");
            List<AppraisalDto> outPersonalAppralExtra = masterReportExt.queryOutPersonalAppral(params, "");
            //研究性学习
            List<AppraisalDto> outStudyAppral = masterReportExt.queryOutStudyAppral(params);
            //班主任评语
            List<AppraisalDto> masterAppraise = masterReportExt.queryMasterAppral(params);
            //会考成绩
            Map<String, String> hkScore = masterReportExt.queryHKScore(params);
            //成绩
            List<ModelScoreDto> allScore = masterReportExt.queryAllScore(params);
            //体质健康数据
            List<HealthDataDto> healthDatas = masterReportExt.queryHealthDdatas(params);
            //体检数据
            List<CheckItemInfoDto> checkItems = masterReportExt.queryCheckItems(params);
            List<File> fileList = new ArrayList<File>();
            for (StudentDto sDto : studentBaseInfos) {
                //以学生为单位  生成zip文件名
                String zipFileName = "";
                try {
                    //文件名称
                    String fileName1 = "";
                    if ("1".equals(flag)) {
                        fileName1 = sDto.getName() + "（" + sDto.getStudentno() + "）评价数据.xls";
                    }
                    String fileName2 = sDto.getName() + "（" + sDto.getStudentno() + "）.xls";
                    //1.封面 OK
                    List<AppraisalDto> personalAppralSelf = null;
                    List<AppraisalDto> personalAppralExtra = null;
                    List<AppraisalDto> singleStudyAppral = null;
                    if ("1".equals(flag)) {
                        personalAppralSelf = masterReportExt.initSinglePersonalSelf(sDto, personalAppral, "selef");
                        personalAppralExtra = masterReportExt.initSinglePersonalSelf(sDto, personalAppral, "");
                        singleStudyAppral = masterReportExt.initsingleStudyAppral(sDto, studyAppral);
                    }
                    //2.1个性发展 自我评价
                    AppraisalDto outSinglePersonalAppraiseSelf = masterReportExt.initOutSinglePersonalAppraiseSelf(outPersonalAppralSelf, sDto);
                    //2.2个性发展 自我评价
                    List<AppraisalDto> outSinglePersonalAppraiseExtra = masterReportExt.initOutSinglePersonalAppraiseExtra(outPersonalAppralExtra, sDto);
                    //3.评语
                    List<AppraisalDto> masterAppral = masterReportExt.initSingleMasterAppraise(sDto, masterAppraise);
                    //4.成绩学分
                    Map<String, Map<String, List<ModelScoreDto>>> singleScore = masterReportExt.initSingleScore(sDto, allScore);
                    //5.研究性学习
                    List<AppraisalDto> outSingleStudyAppral = masterReportExt.initOutSinglePersonalAppraiseExtra(outStudyAppral, sDto);
                    //6.体质健康
                    List<HealthDataDto> singleHealthData = masterReportExt.querySingleHealthData(sDto, healthDatas);
                    //7.健康体检
                    List<CheckItemInfoDto> sigleCheckItems = masterReportExt.querySigleCheckItems(sDto, checkItems);
                    //生成学生评价数据
                    if ("1".equals(flag)) {
                        masterReportExt.writeAppraiseData(url, fileName1, personalAppralSelf, personalAppralExtra, singleStudyAppral);
                    }
                    //生成报告单数据
                    AntZip.outPutStreamFileMethod(url, fileName2, new File(fUrl));
                    File fileBGD = new File(url + File.separator + fileName2);
                    //文件是否存在
                    if (fileBGD.exists()) {
                        masterReportExt.writeReportExcel(fileBGD, sDto, masterAppral, outSinglePersonalAppraiseSelf, outSinglePersonalAppraiseExtra, outSingleStudyAppral, singleHealthData, sigleCheckItems, singleScore, flag, hkScore);
                    }

                    File file = null;
                    if ("1".equals(flag)) {
                        fileList = new ArrayList<File>();
                        file = new File(url + File.separator + fileName1);
                        fileList.add(file);
                    }
                    file = new File(url + File.separator + fileName2);
                    fileList.add(file);
                    if ("1".equals(flag)) {
                        //压缩评价，报告单
                        zipFileName = url + File.separator + sDto.getName() + "（" + sDto.getStudentno() + "）.zip";
                        AntZip.createExcelZip(fileList, zipFileName);
                        zipList.add(new File(zipFileName));
                        AntZip.deleteFileList(fileList);
                    }
                } catch (ManagerException me) {
                    //删除excel文件
                    if (StringUtil.isNotEmpty(zipFileName))
                        fileList.add(new File(zipFileName));
                    AntZip.deleteFileList(fileList);
                    continue;
                } catch (Exception e) {
                    //删除excel文件
                    if (StringUtil.isNotEmpty(zipFileName))
                        fileList.add(new File(zipFileName));
                    AntZip.deleteFileList(fileList);
                    continue;
                }
            }
            if ("1".equals(flag)) {
                //压缩全部学生为完整文件
                AntZip.createExcelZip(zipList, zipName);
                //删除学生Zip文件
                AntZip.deleteFileList(zipList);
            } else {
                //压缩全部学生为完整文件
                AntZip.createExcelZip(fileList, zipName);
                //删除学生Zip文件
                AntZip.deleteFileList(fileList);
            }
        }
    }



	private String getZipName(List<CampusDto> teacherInfos,String url,String flag) {
		CampusDto teacher = teacherInfos.get(0);
		if("1".equals(flag)){
			return url+File.separator+teacher.getTeacherName()+"（高"+teacher.getGradeName()+"年级"+teacher.getClassNum()+"班）.zip";
		}else{
			return url+File.separator+"高"+teacher.getGradeName()+"年级"+teacher.getClassNum()+"班报告册.zip";
		}
	}
	/**
	 * 仅供测试使用
	 */
	private void putTempParam() {
		if(NestUtil.isNotEmpty(eduIds)){
			String[] tempEduIds = eduIds.split(",");
			if(null!=tempEduIds && tempEduIds.length>0){
				for(String eduid : tempEduIds){
					eduId.add(eduid);
				}
			}
		}else{
			eduId.add("-1");
		}
		params.put("eduIds", eduId);
		params.put("classid", classId);
	}
	private void initParams() {
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode());
		params.put("levelcode", userDto.getLevelcode());
		params.put("campusid", userDto.getCampuseId());
		params.put("techerid", userDto.getTeacherid());
	}
	@Json
	public Object queryDownLoadStatus(){
		try {
			String status = redisServiceExt.readSingle(dStatus);
			if("1".equals(status) || "2".equals(status)){
				redisServiceExt.delete(dStatus);
			}
			return JSONObject.fromObject("{val:"+status+"}");
		} catch (ForceException e) {
			return JSONObject.fromObject("{val:"+1+"}");
		}
	}
}
