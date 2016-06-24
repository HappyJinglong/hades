package com.flyrish.hades.webapp.action.masterReport;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.RstudentDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SelfDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IMasterReportExt;
import com.flyrish.hades.util.AntZip;
import com.flyrish.hades.util.NoServiceUtil;

public class ReportImportAction extends ExportAction{
	@Spring
	public IMasterReportExt masterReportExt;
	public FileItem fileItem;
	public String messageData;
	public String errorMessageData;
	public List<SchoolTreeDto> studentInfo;
	public String classId;
	public String gradeId;
	public String classIds;
	/**
	 * 导入
	 * @return
	 */
	public Object doImportData(HttpSession session,HttpServletRequest req){
		
		//String url= NoServiceUtil.getAbsolutePath2(session.getServletContext(), "importFile");
		
		//将数据扔到ngix文件服务器
		String uuid = NoServiceUtil.getGuid();    //产生uuid
		BigInteger uuid1,div,result;
		int hashcode = uuid.hashCode();
		uuid1 = new BigInteger(hashcode+"");
		div = new BigInteger("10");
		result = uuid1.mod(div);
		String url = NoServiceUtil.replaceFileSeparator(constantBean.get("root_path"))+
			File.separator+"zipfile"+File.separator+result+File.separator+uuid+File.separator;
		
		List<String> errorMessageList = new ArrayList<String>();
		List<File> fileList = null;
		if(fileItem!=null){
			try{
				fileList = AntZip.unzip(fileItem.getFile(), url);
				String contentErro = "";
				if(fileList!=null&&fileList.size()>0){
					this.initStudentInfo(req);
					List<SelfDto>selfDtos = new ArrayList<SelfDto>();
					List<SelfDto>selfExtraDtos = new ArrayList<SelfDto>();
					List<SelfDto>appraiseDtos = new ArrayList<SelfDto>();
					List<String>eduIds = new ArrayList<String>();
					for(int i=0;i<fileList.size();i++){
						File ff = fileList.get(i);
						try{
							RstudentDto student = masterReportExt.checkExcelFile(ff,studentInfo);
							if(student!=null){
								RstudentDto studentInfos = masterReportExt.getStudentInfos(ff,student);
								if(null!=studentInfos && NestUtil.isEmpty(studentInfos.getErroMeg())){
									selfDtos.add(studentInfos.getSelfDto());
									selfExtraDtos.addAll(studentInfos.getSelfDtoList());
									appraiseDtos.addAll(studentInfos.getSelfAppraisalDtoList());
									eduIds.add(studentInfos.getEduId());
								}else{
									contentErro="0";
									errorMessageList.add(ff.getName()+":"+studentInfos.getErroMeg());
								}
							}else{
								errorMessageList.add(ff.getName());
							}
						}catch(ManagerException mee){
							errorMessageList.add(ff.getName());
							logger.error("导入报告单错误："+mee.getMessage()+mee.fillInStackTrace());
							continue;
						}catch(Exception ee){
							errorMessageList.add(ff.getName());
							logger.error("导入报告单错误："+ee.getMessage()+ee.fillInStackTrace());
							continue;
						}
					}
					masterReportExt.saveImportData(eduIds,selfDtos,selfExtraDtos,appraiseDtos);
					if(fileList!=null&&fileList.size()>0){
						AntZip.deleteFileList(fileList);
					}
				}else{
					errorMessageData = "导入失败，该ZIP文件中无导入文件。";
				}
				if(StringUtil.isEmpty(errorMessageData)){
					if(errorMessageList!=null&&errorMessageList.size()>0){
						errorMessageData = "文件：";
						for(String errorStr : errorMessageList){
							errorMessageData += errorStr+",<br/>";
						}
						if(NestUtil.isEmpty(contentErro)){
							errorMessageData += "导入失败，请检查以上文件是否为导入模版文件和文件中内容是否正确。";
						}
					}else{
						messageData = "导入成功。";
					}
				}
			}catch(ManagerException me){
				errorMessageData = "导入失败。请核对zip包压缩是否符合导入说明条件！";
				if(fileList!=null&&fileList.size()>0){
					try{
						AntZip.deleteFileList(fileList);
					}catch(ManagerException me1){}
				}
				logger.error("导入报告单错误："+me.getMessage()+me.fillInStackTrace());
			}catch(Exception e){
				errorMessageData = "导入失败。请核对zip包压缩是否符合导入说明条件！";
				if(fileList!=null&&fileList.size()>0){
					try{
						AntZip.deleteFileList(fileList);
					}catch(ManagerException me1){}
				}
				logger.error("导入报告单错误："+e.getMessage()+e.fillInStackTrace());
			}
		}
		return toImportPage();
	}
	private void initStudentInfo(HttpServletRequest req) {
		this.getLoginInfo(req);
		Map<String,Object>params = new HashMap<String, Object>();
		params.put("cmis30id", userDto.getCmis30id());
		params.put("discode", userDto.getDiscode()); 
		List<String>lids = new ArrayList<String>();
		if(NestUtil.isNotEmpty(classIds)){
			String[] split = classIds.split(",");
			if(null!=split && split.length>0){
				for(String lid : split){
					lids.add(lid);
				}
			}else{
				lids.add("-1");
			}
		}else{
			lids.add("-1");
		}
		params.put("lids", lids);
		studentInfo = masterReportExt.getStudentInfo(params);
	}
	@DefaultAction
	public Object toImportPage(){
		return "/reportBook/materReportImport.jsp";
	}
}
