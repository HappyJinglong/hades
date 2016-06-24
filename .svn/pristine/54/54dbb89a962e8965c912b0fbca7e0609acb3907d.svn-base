package com.flyrish.hades.webapp.action.appraise;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.data.Json;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.ICommonSaveExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class SaveAppraiseAction extends BaseAction{
	//评价标识号
	public String apprasialid;
	//评价类型
	public String appraisaltypeid;
	//评价内容
	public String apprasial;
	//学段代码
	public String levelcode;
	//栏目名称
	public String columnName;
	//被评价学生的教育id
	public String stuEduid;
	//被评价学生的姓名
	public String studentName;
	
	public String columNum; ///二级栏目号
	
	public String proKey;  //评价记录的主键标识号
	
	public Date date;
	//学期
	public String termid;
	//身份标识号
	public String appraseridentify;
	//评价人id
	public String appraiserrid;
	
	public String writeMan;
	@Spring
	public ICommonSaveExt commonSaveExt;
	
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	@Json
	public Object doupdateAppraisal(HttpServletRequest req){
		String isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		Boolean flag=true;
		try{
			//获取登录用户信息
			UserDto user = getLoginInfo(req); 
			columnName =  URLDecoder.decode(columnName , "UTF-8");
			studentName= URLDecoder.decode(studentName , "UTF-8");
			appraiserrid = user.getUsername();
			date = new Date();
			if(Constant.USER_MASTERROLE_TYPESTR.equals(user.getUserRealType())){  //班主任
			
				if("2012002".equals(levelcode)){
					if(Constant.MASTER_DICT_CZ.equals(writeMan)){
						appraseridentify=Constant.MASTER_DICT_CZ;
					}else{
						appraseridentify=Constant.TEACHER_DICT_CZ;
					}
				}else{
					if(Constant.MASTER_DICT.equals(writeMan)){
						appraseridentify=Constant.MASTER_DICT;
					}else{
						appraseridentify=Constant.TEACHER_DICT;
					}
					
				}
				latestEvaluationRecordExt.setheadMasterRecordToCache(user.getCampuseId(), user.getLevelcode(), user.getUsername(),columNum,proKey,columnName,studentName,stuEduid,date);
			
			}else if(Constant.USER_TYPE_COURSEMASTER.equals(user.getUserRealType())){  //任课老师
			
				if("2012002".equals(levelcode)){
					if(Constant.TEACHER_DICT_CZ.equals(writeMan)){
						appraseridentify=Constant.TEACHER_DICT_CZ;
					}else{
						appraseridentify=Constant.MASTER_DICT_CZ;
					}
				}else{
					if(Constant.TEACHER_DICT.equals(writeMan)){
						appraseridentify=Constant.TEACHER_DICT;
					}else{
						appraseridentify=Constant.MASTER_DICT;
					}
				}
				latestEvaluationRecordExt.setteacherRecordToCache(user.getCampuseId(), user.getLevelcode(), user.getUsername(),columNum,proKey,columnName ,studentName  , stuEduid,date);
		
			}else if(Constant.USER_TYPE_STUDENT.equals(user.getUserRealType()) && stuEduid.equals(user.getUsername())){  //学生本人 
				
				if("2012002".equals(levelcode)){
					appraseridentify=Constant.me_apprasialidentify;
				}else{
					appraseridentify="1";
				}
				latestEvaluationRecordExt.setstudentRecordToCache(user.getUsername(), columNum, proKey, columnName ,studentName ,date);
			
			}else if(Constant.USER_TYPE_STUDENT.equals(user.getUserRealType())&& !(stuEduid.equals(user.getUsername()))){  //学生同学 
			
				if("2012002".equals(levelcode)){
					appraseridentify=Constant.APPRASER_STUDENT;
				}else{
					appraseridentify="2";
				}
				latestEvaluationRecordExt.setclassMateRecordToCache(user.getUsername(), columNum, proKey,  columnName,studentName , stuEduid,date);
			
			}else if(Constant.USER_TYPE_PARENT.equals(user.getUserRealType())){  //家长
			
				if("2012002".equals(levelcode)){
					appraseridentify=Constant.APPRASER_PARENT;
				}else{
					appraseridentify="5";
				}
				latestEvaluationRecordExt.setparentRecordToCache(columnName, columNum, proKey, studentName, stuEduid,date);
			
			}
			if("1".equals(isStartAppraiseCache)){
				commonSaveExt.saveCommonAppraiseToCache(apprasial,stuEduid, termid, columNum,appraseridentify,appraiserrid,proKey,levelcode,appraisaltypeid);
			     
			}else{
				commonSaveExt.saveCommonAppraise(apprasialid, appraisaltypeid, apprasial,levelcode);
				
			}
		}catch(Exception e){
			logger.error("doupdateAppraisal()",e);
			flag=false;
		}
		return JSONObject.fromObject("{val:"+flag+"}");
	}
}
