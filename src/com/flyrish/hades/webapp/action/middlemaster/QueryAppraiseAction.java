package com.flyrish.hades.webapp.action.middlemaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.data.Json;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.IAppraisalChildExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;
import com.flyrish.hades.util.Utility;
import com.flyrish.hades.webapp.action.BaseAction;

public class QueryAppraiseAction extends BaseAction{
	//教育标识号
	public String edu_id;
	//学期标识号
	public String termid;
	//学段代码
	public String levelcode;
	
	public String cmis30id;
	
	public String discode;
	
	public String classId;
	
	public String sectionCode;
	//班级名称
	public String className;
	
	@Spring
	public IMasterAppriseExt masterAppriseExt;
	
	@Spring
	public IOperationAppraiseServiceExt operationAppraiseServiceExt;
	
	@Spring
	public IAppraisalChildExt appraisalChildExt;
	
	@Spring
	public AppriseMasterAppriseExt appriseMasterAppriseExt;
	
	public Map<String, Map<String,List<AppraiseBaseDto>>> appraiseMaps1=new LinkedHashMap<String, Map<String,List<AppraiseBaseDto>>>(0);
	
	
	public Map<String,List<AppraiseBaseDto>> appraiseMaps=null;
	@DefaultAction
	public Object defaultQueryAction(HttpServletRequest req){
		//获取登录用户信息
		UserDto user = getLoginInfo(req);
		//判断角色类型，如果教育id为空或者家长角色登录，则教育标识号从session中获取
		initEdu_id(req);
		//获取学段代码
		levelcode=user.getLevelcode();
		 cmis30id=user.getCmis30id();
		 discode=user.getDiscode();
		
		
		List<SchoolTreeDto> stuIfos = this.getStudentInfos(classId,req);
		if(null==stuIfos){
			
		}else{
				List<String> eduIds = new ArrayList<String>();
				List<String> studentNames = new ArrayList<String>();
				for(SchoolTreeDto slt : stuIfos){
					eduIds.add(slt.getEdusyId());
					studentNames.add(slt.getText());
				}
				if(eduIds.size()>0){
					
					if(NestUtil.isNotEmpty(levelcode)&&NestUtil.isEmpty(termid)
							&&((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_CZ))){
						edu_id =eduIds.get(0);
						termid=operationAppraiseServiceExt.calcuMiddleTermidByEduId(edu_id,cmis30id,discode);
					}
					//获取数据集合
					String isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
					List<AppraiseBaseDto> appraiseBaseDtos = new ArrayList<AppraiseBaseDto>();
					if("1".equals(isStartAppraiseCache)){
						appraiseBaseDtos=appriseMasterAppriseExt.queryAppraiseBaseDtoByConditionFromCache(studentNames,sectionCode, eduIds, termid, levelcode,cmis30id,discode);
					}else{
						appraiseBaseDtos=appriseMasterAppriseExt.queryAppraiseBaseDtoByCondition(sectionCode,eduIds,termid,levelcode,cmis30id,discode);						
					}
					//组装数据
					installDataMaps(stuIfos,appraiseBaseDtos,req);
				}
				
		}
		return "queryCommentDetailByColumn.jsp";
	}
	
	/**
	 * 获取班主任管辖下所有学生id
	 * @param classIds
	 * @param gradeId
	 * @param req
	 * @return
	 */
	private List<SchoolTreeDto> getStudentInfos(String classId,HttpServletRequest req) {
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("lid",classId );
		params.put("cmis30Id",cmis30id );
		params.put("discode",discode );
		return masterAppriseExt.getStudentInfo(params);
	}
	/**
	 * 异步加载数据
	 * @param req
	 * @return
	 */
	@Json
	public Object queryData(HttpServletRequest req){
		List<SchoolTreeDto> stuIfos =  getStudentInfos(classId,req);
		if(null!=stuIfos && stuIfos.size()>0){
			List<String>listStr=new ArrayList<String>();
			for (SchoolTreeDto stI : stuIfos) {
				Map<String,Object>maps=new HashMap<String,Object>();
				maps.put("id",stI.getEdusyId());
				maps.put("name",stI.getText()+"_"+stI.getEdusyId());
				listStr.add(Utility.createJsonStr(maps));
			}
			return JSONObject.fromObject("{val:"+Utility.createJsonStr(listStr)+"}");
		}
		return null;
	}
	
	
	private void initEdu_id(HttpServletRequest req){
		userDto=this.getLoginInfo(req);
		if(NestUtil.isNotEmpty(edu_id)||userDto==null)return;
		Integer userType = Integer.parseInt(userDto.getUsertype());
		if(userType == Integer.parseInt(Constant.USER_KIND_SCHOOLSTUDENT)
				||userType == Integer.parseInt(Constant.USER_KIND_SCHOOLFAM)){
			edu_id=userDto.getEduId();
		}
	}
	private void installDataMaps(List<SchoolTreeDto> stuIfos,List<AppraiseBaseDto> appraiseBaseDtos,HttpServletRequest req){
		for(SchoolTreeDto st : stuIfos){
			appraiseMaps = new HashMap<String,List<AppraiseBaseDto>>(0);
			if(!(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)){
				for(AppraiseBaseDto dto:appraiseBaseDtos){
				  if(st.getEdusyId().equals(dto.getEdu_id())){
						if(dto==null||NestUtil.isEmpty(dto.getTwoPartId())) continue;
						//将当前用户可操作的信息只为可读
						Boolean isReadOnly=estimateIsReadOnlyByLoginUser(req,dto.getUserid(),dto.getTwoPartId(),dto.getSignerName());
						dto.setIsReadOnly(isReadOnly);
						//组装数据集合
						List<AppraiseBaseDto> dtos=null;
						dtos=appraiseMaps.get(dto.getTwoPartId());
						if(dtos==null){
							dtos=new ArrayList<AppraiseBaseDto>();
						    appraiseMaps.put(dto.getTwoPartId(),dtos);
						}
						dtos.add(dto);
				  }
				}
			}
			appraiseMaps1.put(st.getText()+"_"+st.getEdusyId(), appraiseMaps);
		}
		
	}
	/**
	 * 通过登录用户身份判断当前的评价信息是否只读
	 * @param req reqest对象
	 * @param appraserid 评价人标识号
	 * @param appraser 评价人姓名
	 * @return
	 */
	public Boolean estimateIsReadOnlyByLoginUser(HttpServletRequest req,String appraserid,String twoPartId,String appraser){
		if(userDto==null)return true;
		String username=userDto.getUsername();
		//如果角色为班主任，并且该评语为班主任评语，则将其置为非只读
		if(Constant.USER_KIND_SCHOOLTEACHER.equals(userDto.getUsertype())
				&&(Constant.USER_MASTERROLE_TYPESTR.equals(userDto.getUserRealType())||(NestUtil.isNotEmpty(userDto.getTeacherName())&&userDto.getTeacherName().equals(appraser)))
				&&Constant.CHARGE_TEACHER_APPRAISAL.equals(twoPartId)){
			return false;
		}
		if(NestUtil.isNotEmpty(username)&&username.equals(appraserid)){
			return false;
		}else{
			return true;
		}
	}
}
