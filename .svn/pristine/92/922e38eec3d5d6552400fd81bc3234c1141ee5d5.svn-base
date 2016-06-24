package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.AttachFileDto;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.dto.SubjectDto;
import com.flyrish.hades.service.ext.AppriseMasterAppriseExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.DataSource;

public class MasterAppriseExtImpl extends JdbcRootManager implements AppriseMasterAppriseExt {
	
	
	public IMasterAppriseExt masterAppriseExt;
	
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	
	public IRedisServiceExt redisServiceExt;
	
	public IRedisServiceExt getRedisServiceExt() {
		return redisServiceExt;
	}


	public void setRedisServiceExt(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}


	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}


	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}


	public IMasterAppriseExt getMasterAppriseExt() {
		return masterAppriseExt;
	}


	public void setMasterAppriseExt(IMasterAppriseExt masterAppriseExt) {
		this.masterAppriseExt = masterAppriseExt;
	}


	public ConstantBean getConstantBean() {
		return constantBean;
	}


	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public ConstantBean constantBean;
	
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String sectionCode,List<String> eduIds,
			String termid, String levelCode,String cmis30id,String discode) {
		
		if(NestUtil.isEmpty(levelCode)||null ==eduIds || eduIds.size()<0)return null;
		
		List<AppraiseBaseDto> appraiseBaseDtos=new ArrayList<AppraiseBaseDto>();
		
		if((Integer.parseInt(levelCode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			//查询子类型
		    List<String> appraisalTypeIds =  masterAppriseExt.getAppraisalTypeByUpAppraisalTypeId(sectionCode==null?null:Integer.parseInt(sectionCode));
			// 10新学期伊始的我  
		    if(Constant.BEGIN_OF_THE_NEW_TERM.equals(sectionCode)  && eduIds.size()>0){
		    	 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
		   
			//20学期结束时的我
		    }else if( Constant.AT_THE_END_OF_THE_TERM.equals(sectionCode) && eduIds.size()>0){
		    	 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//初高中班主任评语查询
				queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
			//30 思想道德
		    }else if(Constant.IDEOLOGICAL_MORALITY.equals(sectionCode)&& eduIds.size()>0){
		    	 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_SXJLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
			//40合作与交流
			}else if(Constant.COOPERATION_AND_EXCHANGE.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_HZ_JLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			//50运动与健康
			}else if(Constant.SPORTS_AND_HEALTH.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//体质健康查询
				queryHealthstandard((Integer.parseInt(levelCode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			//60审美与表现
			}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_SMYBX_JLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			//70个性发展
			}else if(Constant.PERSONALITY_DEVELOPMENT.equals(sectionCode)&& eduIds.size()>0){  
				 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//个性发展基本情况
				queryApersonality(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
		    // 80学业成就
			}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//学科学习过程记录
				queryAlearnprocessWorks(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//课程评语查询
				queryLearnprocessAppraisal(eduIds,termid,cmis30id,discode,appraiseBaseDtos);

		    //90综合实践活动
			}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(sectionCode)&& eduIds.size()>0){ 
				//研究性学习/社区服务/社会实践活动
				queryPractices(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//综合实践活动--自我评价查询
				queryPracticesSelfAppraise(appraiseBaseDtos);
			}
		 
			
			//查询附件
			queryattchfile(appraiseBaseDtos);
			
			
			return appraiseBaseDtos;
		}
		else if((Integer.parseInt(levelCode)) ==(Constant.DICT_TYPE_LEVELCODE_CZ)){
	
			//查询初中评价信息
			queryMiddleSchoolAppraisalInfo(sectionCode,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			if(Constant.JUNIOR_TERN_END.equals(sectionCode)&& eduIds.size()>0){
				//初高中班主任评语查询
				queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}else if(Constant.JUNIOR_SPROT.equals(sectionCode)&& eduIds.size()>0){
				//初高中体质健康查询
				queryHealthstandard((Integer.parseInt(levelCode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}
			
			//查询初中附件
			queryMiddleSchoolAttachment(appraiseBaseDtos);
			
			
			return appraiseBaseDtos;
		}
		return null;
	}
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String sectionCode, String secondItem, List<String> eduIds,
			String termid, String levelCode,String cmis30id,String discode) {
		
		if(NestUtil.isEmpty(levelCode)||null ==eduIds || eduIds.size()<0)return null;
		
		List<AppraiseBaseDto> appraiseBaseDtos=new ArrayList<AppraiseBaseDto>();
		
		if((Integer.parseInt(levelCode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			//查询子类型
//			List<String> appraisalTypeIds =  masterAppriseExt.getAppraisalTypeByUpAppraisalTypeId(sectionCode==null?null:Integer.parseInt(sectionCode));
			List<String> appraisalTypeIds =  new ArrayList<String>();
			if(secondItem.equals("1030")){
				secondItem = "1020";
			}else if(secondItem.equals("5030")){
				appraisalTypeIds.add("5040");
				appraisalTypeIds.add("5050");
			}
			appraisalTypeIds.add(secondItem);
			// 10新学期伊始的我  
			if(Constant.BEGIN_OF_THE_NEW_TERM.equals(sectionCode)  && eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//20学期结束时的我
			}else if( Constant.AT_THE_END_OF_THE_TERM.equals(sectionCode) && eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//初高中班主任评语查询
				queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//30 思想道德
			}else if(Constant.IDEOLOGICAL_MORALITY.equals(sectionCode)&& eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_SXJLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//40合作与交流
			}else if(Constant.COOPERATION_AND_EXCHANGE.equals(sectionCode)&& eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_HZ_JLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//50运动与健康
			}else if(Constant.SPORTS_AND_HEALTH.equals(sectionCode)&& eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//体质健康查询
				queryHealthstandard((Integer.parseInt(levelCode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//60审美与表现
			}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(sectionCode)&& eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(Constant.TYPE_SMYBX_JLD,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//70个性发展
			}else if(Constant.PERSONALITY_DEVELOPMENT.equals(sectionCode)&& eduIds.size()>0){  
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//个性发展基本情况
				queryApersonality(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				// 80学业成就
			}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(sectionCode)&& eduIds.size()>0){
				//单条记录评价类
				queryAppraise(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//学科学习过程记录
				queryAlearnprocessWorks(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				//课程评语查询
				queryLearnprocessAppraisal(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//90综合实践活动
			}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(sectionCode)&& eduIds.size()>0){ 
				//研究性学习/社区服务/社会实践活动
				queryPractices(appraisalTypeIds,eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
				//综合实践活动--自我评价查询
				queryPracticesSelfAppraise(appraiseBaseDtos);
			}
			
			
			//查询附件
			queryattchfile(appraiseBaseDtos);
			
			
			return appraiseBaseDtos;
		}
		else if((Integer.parseInt(levelCode)) ==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			//查询初中评价信息
			queryMiddleSchoolAppraisalInfo(sectionCode, secondItem, eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			if(Constant.JUNIOR_TERN_END.equals(sectionCode)&& eduIds.size()>0){
				//初高中班主任评语查询
				queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}else if(Constant.JUNIOR_SPROT.equals(sectionCode)&& eduIds.size()>0){
				//初高中体质健康查询
				queryHealthstandard((Integer.parseInt(levelCode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}
			
			//查询初中附件
			queryMiddleSchoolAttachment(appraiseBaseDtos);
			
			
			return appraiseBaseDtos;
		}
		return null;
	}
	
	@DataSource("read")
	private void queryattchfile(List<AppraiseBaseDto> appraiseBaseDtos){
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<String> particesids=new ArrayList<String>();
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(NestUtil.isEmpty(dto.getApprasialid()))continue;
			particesids.add(dto.getApprasialid());
		}
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("attachtypeids",particesids);
		try{
			ISqlElement processSql = this.processSql(params, "OperationAppraiseServiceExtImpl.queryattchfile.query");
			System.out.println(processSql.getSql());
			List<AttachFileDto> attachFileDtos=this.findList("OperationAppraiseServiceExtImpl.queryattchfile.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AttachFileDto dto = new AttachFileDto();
						dto.setFilename(rs.getString("attachname"));
						dto.setFilepath("/DownloadAttachAction.a?attachid="+rs.getString("attachid"));
						dto.setAttachid(rs.getString("attachid"));
						dto.setAttachpath(rs.getString("attachpath"));
						dto.setAttachtypeid(rs.getString("attachtypeid"));
						dto.setAttachtype(rs.getString("attachtype"));
						return dto;
					}
			});
		  if(attachFileDtos==null||attachFileDtos.isEmpty())return;
		  //附件路径分组
		  Map<String,List<AttachFileDto>> dataMaps=new HashMap<String,List<AttachFileDto>>();
		  for(AttachFileDto filedto:attachFileDtos){
			  List<AttachFileDto>dtos=dataMaps.get(filedto.getAttachtypeid()+"_"+filedto.getAttachtype());
			  if(dtos==null){
				  dtos=new ArrayList<AttachFileDto>();
				  dataMaps.put(filedto.getAttachtypeid()+"_"+filedto.getAttachtype(),dtos);
			  }
			  dtos.add(filedto);
		  }
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			  //1：记录袋: 思想道德事迹记录袋/合作与交流记录袋/审美与表现记录袋
			  //2：个人发展:个性发展过程
			  //3：综合实践: 研究性学习评价/社区服务评价/社会实践基本评价
			  //4.学科学习发展过程:学科学习发展过程
			  if(dto==null
					  &&!Constant.TYPE_SXJLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SMYBX_JLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_GXFZGC.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_YJXX.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SQFU.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SHSJHD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_XY_GCJL.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid()))
				  continue;
			  if(Constant.TYPE_SXJLD.equals(dto.getAppraisaltypeid())||Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid())
					  ||Constant.TYPE_SMYBX_JLD.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_1"));
			  }else if(Constant.TYPE_GXFZGC.equals(dto.getAppraisaltypeid())||Constant.TYPE_GXFZ_CGZS.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_2"));
			  }else if(Constant.TYPE_YJXX.equals(dto.getAppraisaltypeid())||
						  Constant.TYPE_SQFU.equals(dto.getAppraisaltypeid())||
						  Constant.TYPE_SHSJHD.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_3"));
			  }else if(Constant.TYPE_XY_GCJL.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_4"));
			  }
		  }
		}catch (Exception e) {
			logger.error("queryattchfile(List<AppraiseBaseDto>)",e);
		}
	}
	/**
	 * 新学期伊始的我:自我评价，我的发展目标
	 * 学期结束时的我：自我评价，我的发展目标
	 * 思想道德：自我评价、学业成就：自我评价
	 * 合作与交流：自我评价
	 * 运动与健康：自我评价
	 * 审美与表现：自我评价
	 * 个性与发展：自我评价
	 * 个性发展过程
	 * 特长与成果展示
	 * 他人评价
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryAppraise(List<String> appraisaltypeids,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("appraisaltypeids",appraisaltypeids);
			params.put("eduIds", eduIds);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			
			
			List<AppraiseBaseDto> selfappraiseList =  this.findList("MasterAppriseExtImpl.queryAppraise.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					
					dto.setApprasialid(rs.getString("apprasialid"));
					dto.setApprasial(rs.getString("apprasial"));
					
					if(Constant.TYPE_GXFZ_CGZS.equals(rs.getString("appraisaltypeid"))){
						dto.setAppraseridentify(("本人"));
						dto.setAppraseridentifynum("1");
					}else{
						dto.setAppraseridentify(rs.getString("appraseridentify"));
						dto.setAppraseridentifynum(rs.getString("appraseridentifynum"));
					}
					dto.setAppraser(rs.getString("appraser"));
					dto.setAppraserid(rs.getString("appraiserrid"));
					dto.setEdu_id(rs.getString("edu_id"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					dto.setColumNumberName(rs.getString("appraisaltype"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(selfappraiseList);
		} catch (Exception e) {
			logger.error("queryAppraise(Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	/**
	 * 思想道德事迹记录袋
	 * 审美与表现记录袋
	 * 合作与交流行为记录袋
	  * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryRecordpackage(String appraisaltypeid,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("appraisaltypeid",appraisaltypeid);
			params.put("eduIds", eduIds);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<AppraiseBaseDto> list =  this.findList("MasterAppriseExtImpl.queryRecordpackage.query",params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("recordid"));
					dto.setApprasial(rs.getString("content"));
					dto.setAppraseridentify(rs.getString("appraseridentify"));
					dto.setAppraseridentifynum(rs.getString("appraseridentifynum"));
					dto.setAppraser(rs.getString("signer"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraserid(rs.getString("edu_id"));
					dto.setColumNumberName(rs.getString("appraisaltype"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(list);
		} catch (Exception e) {
			logger.error("queryRecordpackage(Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	/*
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 */
	@DataSource("read")
	private  void queryAlearnprocessWorks(List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto){
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("eduIds", eduIds);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<AppraiseBaseDto> list =  this.findList("MasterAppriseExtImpl.queryAlearnprocessWorks.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("workid"));
					dto.setApprasial(rs.getString("processdesc"));
					dto.setAppraseridentify(Constant.me_apprasialidentify);
					dto.setAppraseridentifynum("1");
					dto.setColumNumberName("学科学习过程记录");
					dto.setAppraser(rs.getString("name"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraserid(rs.getString("edu_id"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(Constant.TYPE_XY_GCJL);
					dto.setItem1(rs.getString("subject"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(list);
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorks(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	/**
	 * 个性发展基本情况
	  * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private  void queryApersonality(List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto){
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("eduIds", eduIds);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<AppraiseBaseDto> list =  this.findList("MasterAppriseExtImpl.queryApersonality.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("baseid"));
					dto.setApprasial(rs.getString("developmentrd"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraserid(rs.getString("edu_id"));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(Constant.TYPE_GXFZ_JBQK);
					
					dto.setAppraser(rs.getString("name"));
					dto.setAppraseridentify(Constant.me_apprasialidentify);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setAppraseridentifynum(rs.getString("indexid"));
					if(NestUtil.isNotEmpty(rs.getString("indexid"))){
						if("1".equals(rs.getString("indexid"))){
							dto.setColumNumberName("个性发展基本情况特长");
						}else if("2".equals(rs.getString("indexid"))){
							dto.setColumNumberName("个性发展基本情况有新意的成果");
						}else if("3".equals(rs.getString("indexid"))){
							dto.setColumNumberName("个性发展基本情况其他 ");
						}
					}
					return dto;
				}
			});
			appraiseBaseDto.addAll(list);
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorks(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	

	
	/**
	 * 课程评语查询
	  * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryLearnprocessAppraisal(List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("eduIds", eduIds);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<AppraiseBaseDto> learnprocessAppraisalList =  this.findList("MasterAppriseExtImpl.queryLearnprocessAppraisal.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("appraisalid"));
					dto.setApprasial(rs.getString("appraisal"));
					dto.setAppraseridentify(Constant.APPRASER_TEACHER);
					dto.setAppraseridentifynum("3");
					dto.setColumNumberName("课程评语");
					dto.setAppraser(rs.getString("sign"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraserid(rs.getString("appraiserrid"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(Constant.TYPE_KE_CHENG_PINGYU);
					dto.setItem1(rs.getString("subject"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(learnprocessAppraisalList);
		} catch (Exception e) {
			logger.error("queryaLearnprocessAppraisal(List<String>,String,String,String,List<AppraiseBaseDto>)",e);
		}
	}
	
	
	/**
	 * 综合实践活动查询
	  * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryPractices(List<String> appraisaltypeids,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("appraisaltypeids",appraisaltypeids);
			params.put("eduIds", eduIds);
			params.put("semesterid",termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<AppraiseBaseDto> list =  this.findList("MasterAppriseExtImpl.queryPractices.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("practiceid"));
					dto.setApprasial(rs.getString("item1"));
					dto.setAppraseridentify(Constant.me_apprasialidentify);
					dto.setAppraseridentifynum("1");
				//	dto.setAppraser(rs.getString("signer"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setAppraserid(rs.getString("edu_id"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("item5")==null?null:sdf.format(rs.getDate("item5")));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					dto.setItem1(rs.getString("item6"));
					dto.setItem2(rs.getString("item7"));
					dto.setColumNumberName(rs.getString("appraisaltype"));
					if(Constant.TYPE_SQFU.equals(rs.getString("appraisaltypeid"))
							||Constant.TYPE_SHSJHD.equals(rs.getString("appraisaltypeid"))){
						dto.setItem3(rs.getString("item8"));
					}else if(Constant.TYPE_YJXX.equals(rs.getString("appraisaltypeid"))){
						dto.setItem3(rs.getString("item2"));
					}
					if(Constant.TYPE_SQFU.equals(rs.getString("appraisaltypeid"))
							||Constant.TYPE_SHSJHD.equals(rs.getString("appraisaltypeid"))){
						dto.setItem4(rs.getString("item2"));
					}else if(Constant.TYPE_YJXX.equals(rs.getString("appraisaltypeid"))){
						dto.setItem4(rs.getString("item9"));
					}
					dto.setItem5(rs.getString("item9"));
					dto.setAppraser(rs.getString("name"));
	//				dto.setMyselfappraserid(rs.getString("appraisalid"));
	//				dto.setMyselfapprasercontent(rs.getString("content"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(list);
			
		} catch (Exception e) {
			logger.error("queryPractices(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	
	@DataSource("read")
	private void queryPracticesSelfAppraise(List<AppraiseBaseDto> appraiseBaseDtos){
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<String> ids=new ArrayList<String>();
		
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(NestUtil.isEmpty(dto.getApprasialid()))continue;
			ids.add(dto.getApprasialid());
		}
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("appraisalids",ids);
		try{
			List<AppraiseBaseDto> appraiseList=this.findList("MasterAppriseExtImpl.queryPracticesSelfAppraise.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setAppraseridentify(Constant.me_apprasialidentify);
						dto.setApprasialid(rs.getString("practiceid"));
						dto.setMyselfappraserid(rs.getString("appraisalid"));
						dto.setAppraser(rs.getString("signer"));
						dto.setMyselfapprasercontent(rs.getString("content"));
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					
						return dto;
					}
			});
			
			
		  if(appraiseList==null||appraiseList.isEmpty())return;
		  Map<String,List<AppraiseBaseDto>> dataMaps=new HashMap<String,List<AppraiseBaseDto>>();
		  for(AppraiseBaseDto dto : appraiseList){
			  List<AppraiseBaseDto> dtos=dataMaps.get(dto.getApprasialid());
			  if(dtos==null){
				  dtos=new ArrayList<AppraiseBaseDto>();
				  dataMaps.put(dto.getApprasialid(),dtos);
			  }
			  dtos.add(dto);
		  }
			
	
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			
			  for(String key : dataMaps.keySet()){
				  if(dto.getApprasialid().equals(key)){
					  dto.setPracticesSelfAppraiseDtos(dataMaps.get(dto.getApprasialid())); 
				  }
			  }
		  }
		  
		}catch (Exception e) {
			logger.error("queryPracticesSelfAppraise(List<AppraiseBaseDto>)",e);
		}
	}
	
	
	
	/**
	 * 班主任评语查询
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	public void queryAssess(List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		
		List<String> studentIds=new ArrayList<String>();
		Integer classid=null;
		String termId=null;
		String classId=null;
		Integer levelCode=null;
		

		String num=null;
		String gradeNum=null;
		/**
		 * 根据教育ID查询studentid |classid 
		 */
		Map<String,Object> params1 = new HashMap<String,Object>();
		params1.put("eduIds", eduIds);
		params1.put("cmis30id",cmis30id);
		params1.put("discode",discode);
		
	try {
			
			List<StudentDto> list =  this.findList("MasterAppriseExtImpl.querystudentIdByeduId.query", params1, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto dto = new StudentDto();
					dto.setStudentid(rs.getString("studentid"));
					dto.setLevelcode(rs.getInt("levelcode"));
					dto.setClassid(rs.getInt("classid"));
					return dto;
				}
			});
			
			if(list!=null && list.size()>0){
				for(StudentDto st : list){
					studentIds.add(st.getStudentid());
				}
				classid=list.get(0).getClassid();
				levelCode=list.get(0).getLevelcode();
			}
		} catch (Exception e) {
			logger.error("querystudentIdByeduId(String)",e);
		}
	//如果学段为高中
		if(levelCode==(Constant.DICT_TYPE_LEVELCODE_GZ) || 
				   levelCode==(Constant.DICT_TYPE_LEVELCODE_GZYK)){
			
			if(termid != null){
				num=termid.substring(1);
				gradeNum=termid.substring(0,1);
			}
			
			if(classid != null){
				//转换classid为string类型
				classId=classid.toString();
			}else{
				classId=null;
			}
			
			/**
			 * 转换学期格式
			 */
			Map<String,Object>params2 = new HashMap<String, Object>();
			params2.put("classId", classId);
			params2.put("gradenum", gradeNum);
			params2.put("num", num);
			
			try {
				ISqlElement sqlDemo=this.processSql(params2, "MasterAppriseExt.getHSHistoryTermId.qurey");
				List<String> strs = this.findList("MasterAppriseExt.getHSHistoryTermId.qurey", params2, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						return rs.getString("termid");
					}
				});
				if(strs!=null && strs.size()>0){
					termId=strs.get(0);
				}
			} catch (Exception e) {
				logger.error("getCZSubjectInfos()", e);
			}
			
			
			
			//查询班主任评语
			Map<String,Object> params3 = new HashMap<String,Object>();
			params3.put("termid", termId);
			params3.put("cmis30id",cmis30id);
			params3.put("discode",discode);
			params3.put("studentIds", studentIds);
			
			try {
				
				List<AppraiseBaseDto> assessList =  this.findList("MasterAppriseExtImpl.queryAssess.query", params3, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setApprasialid(rs.getString("assessid"));
						dto.setApprasial(rs.getString("assesscontent"));
						dto.setAppraseridentify(Constant.APPRASER_MASTER);
						dto.setAppraisaltypeid(Constant.TYPE_END_BZRPY);
						dto.setAppraser(rs.getString("signname"));
						dto.setTermid(rs.getString("termId"));
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
						dto.setAppraseridentifynum("4");
						dto.setColumNumberName("班主任评语");
						dto.setEdu_id(rs.getString("edu_id"));
						
						return dto;
					}
				});
				appraiseBaseDto.addAll(assessList);
			} catch (Exception e) {
				logger.error("queryAssess(List<String>,String,String,String,List<AppraiseBaseDto>)",e);
			}
		}//查询初中班主任评语
		else if(levelCode==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			

			termId=termid;
			
			Map<String,Object> params4 = new HashMap<String,Object>();
			params4.put("termid", termId);
			params4.put("cmis30id",cmis30id);
			params4.put("discode",discode);
			params4.put("studentIds",studentIds);
			
			try {
				List<AppraiseBaseDto> middleAppraisalList =  this.findList("MasterAppriseExtImpl.queryAssess.query", params4, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setPartId(rs.getString("assessid"));
						dto.setSignerName(rs.getString("signname"));
						dto.setPartInfo(rs.getString("assesscontent"));
						dto.setWriteMan("班主任");
						dto.setTwoPartId(Constant.CHARGE_TEACHER_APPRAISAL);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						dto.setCreateDate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
						dto.setEdu_id(rs.getString("edu_id"));
						return dto;
					}
				});
				appraiseBaseDto.addAll(middleAppraisalList);
			} catch (Exception e) {
				logger.error("queryAssess(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
			}
		}
	}
	
	
	
	
	/**
	 * 体质健康查询
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryHealthstandard(Integer levelCode,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto) {
		
		List<String> studentIds=new ArrayList<String>();
		Integer classid=null;
		String termId=null;
		String classId=null;
		String num=null;
		String gradeNum=null;

		
		
		/**
		 * 根据教育ID查询studentid |classid 
		 */
		Map<String,Object> params1 = new HashMap<String,Object>();
		params1.put("eduIds", eduIds);
		params1.put("cmis30id",cmis30id);
		params1.put("discode",discode);
		
		try {
			
			List<StudentDto> list =  this.findList("MasterAppriseExtImpl.querystudentIdByeduId.query", params1, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto dto = new StudentDto();
					dto.setStudentid(rs.getString("studentid"));
					dto.setClassid(rs.getInt("classid"));
					return dto;
				}
			});
			
			if(list!=null && list.size()>0){
				for(StudentDto st : list){
					studentIds.add(st.getStudentid());
				}
				classid=list.get(0).getClassid();
			}
		} catch (Exception e) {
			logger.error("querystudentIdByeduId(String)",e);
		}
		//学段为高中
		if(levelCode==(Constant.DICT_TYPE_LEVELCODE_GZ) || 
		   levelCode==(Constant.DICT_TYPE_LEVELCODE_GZYK)){
			if(termid != null){
				num=termid.substring(1);
				gradeNum=termid.substring(0,1);
			}
			if(classid != null){
				//转换classid为string类型
				classId=classid.toString();
			}else{
				classId=null;
			}
			
			/**
			 * 转换学期格式
			 */
			Map<String,Object>params2 = new HashMap<String, Object>();
			params2.put("classId", classId);
			params2.put("gradenum", gradeNum);
			params2.put("num", num);
			
			try {
				ISqlElement sqlDemo=this.processSql(params2, "MasterAppriseExt.getHSHistoryTermId.qurey");
				List<String> strs = this.findList("MasterAppriseExt.getHSHistoryTermId.qurey", params2, new RowMapper(){
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						return rs.getString("termid");
					}
				});
				if(strs!=null && strs.size()>0){
					termId=strs.get(0);
				}
			} catch (Exception e) {
				logger.error("getCZSubjectInfos()", e);
			}
		}//学段为初中
		else if(levelCode==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			termId=termid;
		}
		
		
		
		//查询体质健康
		Map<String,Object> params3 = new HashMap<String,Object>();
		params3.put("cmis30id",cmis30id);
		params3.put("discode",discode);
		params3.put("studentIds", studentIds);
		params3.put("termId", termId);
		
		try {
			List<AppraiseBaseDto> healthstandardList =  this.findList("MasterAppriseExtImpl.queryHealthstandard.query", params3, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					String itemMarkStandardWeight=null;
					String itemMarkWeight=null;
					
					AppraiseBaseDto dto = new AppraiseBaseDto();
					
					dto.setSchoolyear(rs.getString("schoolyear"));
					dto.setHealthid(rs.getString("healthid"));
					dto.setHealthstdid(rs.getString("healthstdid"));
					dto.setYearScore(rs.getString("year_score"));
					dto.setYearGrade(rs.getString("year_grade"));
					dto.setRewardSscore(rs.getString("reward_score"));
					dto.setGradenum(rs.getString("gradenum"));
					dto.setGradename(rs.getString("gradename"));
					dto.setItemGrade(rs.getString("item_grade"));
					dto.setItemMark(rs.getString("item_mark"));
					dto.setItemName(rs.getString("item_name"));
					dto.setItemScore(rs.getString("item_score"));
					dto.setItemType(rs.getString("item_type"));
					dto.setAppraisaltypeid(Constant.TYPE_YDJKTZJK);////高中评价类型
					dto.setTwoPartId(Constant.PLAY_PHYSCIAL_HEALTH);//初中评价类型
					dto.setEdu_id(rs.getString("edu_id"));
					if(dto != null){
						if(dto.getItemType().equals("1")){
							
							String itemMark=dto.getItemMark();
							if(itemMark!=null&&"".equals(itemMark))
							{
								String[] mark =itemMark.split("/");
								if(mark!=null&&mark.length==2)
								{
									itemMarkStandardWeight = mark[0];
									itemMarkWeight = mark[1];
								}
							}
						}
					}
					
					dto.setItemMarkWeight(itemMarkWeight);
					dto.setItemMarkStandardWeight(itemMarkStandardWeight);
					
					return dto;
				}
			});
			appraiseBaseDto.addAll(healthstandardList);
		} catch (Exception e) {
			logger.error("queryHealthstandard(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>))",e);
		}
	}
	
	
	
	
	/**
	 * 查询初中评价信息
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	public void queryMiddleSchoolAppraisalInfo(String sectionCode,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto){
		
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			params.put("eduIds", eduIds);
			params.put("termid", termid);
			params.put("onePartId", sectionCode);
			List<AppraiseBaseDto> middleAppraisalList =  this.findList("MasterAppriseExtImpl.queryMiddleSchoolAppraisalInfo.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					
					dto.setUserid(rs.getString("userid"));
					dto.setOnePartId(rs.getString("one_part_id"));
					dto.setPartName(rs.getString("part_name"));
					dto.setTwoPartId(rs.getString("two_part_id"));
					dto.setTwoPartName(rs.getString("two_part_name"));
					dto.setTopic(rs.getString("topic"));
					dto.setPartId(rs.getString("part_id"));
					dto.setAddress(rs.getString("address"));
					dto.setKeyword(rs.getString("keyword"));
					dto.setSubjectId(rs.getString("subject_id"));
					dto.setSubjectName(rs.getString("subject_name"));
					dto.setCooperationMan(rs.getString("cooperation_man"));
					dto.setTermid(rs.getString("termid"));
					dto.setPartInfo(rs.getString("part_info"));
					dto.setWriteMan(rs.getString("write_man"));
					dto.setSignerName(rs.getString("signer_name"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setCreateDate(rs.getDate("createdate")==null?null:sdf.format(rs.getDate("createdate")));
					dto.setStartDate(rs.getDate("startdate")==null?null:sdf.format(rs.getDate("startdate")));
					dto.setEndDate(rs.getDate("enddate")==null?null:sdf.format(rs.getDate("enddate")));
					dto.setEdu_id(rs.getString("edu_id"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(middleAppraisalList);
		} catch (Exception e) {
			logger.error("queryMiddleSchoolAppraisalInfo(List<String>,List<String>,String,String,String,List<AppraiseBaseDto>)",e);
		}
	}
	
	
	/**
	 * 查询初中评价信息
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	public void queryMiddleSchoolAppraisalInfo(String sectionCode, String secondItem,List<String> eduIds,String termid,String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDto){
		
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			params.put("eduIds", eduIds);
			params.put("termid", termid);
			params.put("onePartId", sectionCode);
			params.put("secondPartId", secondItem);
			List<AppraiseBaseDto> middleAppraisalList =  this.findList("MasterAppriseExtImpl.queryMiddleSchoolAppraisalInfo.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					
					dto.setUserid(rs.getString("userid"));
					dto.setOnePartId(rs.getString("one_part_id"));
					dto.setPartName(rs.getString("part_name"));
					dto.setTwoPartId(rs.getString("two_part_id"));
					dto.setTwoPartName(rs.getString("two_part_name"));
					dto.setTopic(rs.getString("topic"));
					dto.setPartId(rs.getString("part_id"));
					dto.setAddress(rs.getString("address"));
					dto.setKeyword(rs.getString("keyword"));
					dto.setSubjectId(rs.getString("subject_id"));
					dto.setSubjectName(rs.getString("subject_name"));
					dto.setCooperationMan(rs.getString("cooperation_man"));
					dto.setTermid(rs.getString("termid"));
					dto.setPartInfo(rs.getString("part_info"));
					dto.setWriteMan(rs.getString("write_man"));
					dto.setSignerName(rs.getString("signer_name"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setCreateDate(rs.getDate("createdate")==null?null:sdf.format(rs.getDate("createdate")));
					dto.setStartDate(rs.getDate("startdate")==null?null:sdf.format(rs.getDate("startdate")));
					dto.setEndDate(rs.getDate("enddate")==null?null:sdf.format(rs.getDate("enddate")));
					dto.setEdu_id(rs.getString("edu_id"));
					return dto;
				}
			});
			appraiseBaseDto.addAll(middleAppraisalList);
		} catch (Exception e) {
			logger.error("queryMiddleSchoolAppraisalInfo(List<String>,List<String>,String,String,String,List<AppraiseBaseDto>)",e);
		}
	}
	
	/**
	 * 查询初中附件
	 */
	@DataSource("read")
	private void queryMiddleSchoolAttachment(List<AppraiseBaseDto> appraiseBaseDtos){
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<String> ids=new ArrayList<String>();
		
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(NestUtil.isEmpty(dto.getPartId()))continue;
			ids.add(dto.getPartId());
		}
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("partIds",ids);
		try{		
			List<AttachFileDto> attachmentList=this.findList("OperationAppraiseServiceExtImpl.queryMiddleSchoolAttachment.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						
						AttachFileDto dto = new AttachFileDto();
						dto.setPartId(rs.getString("part_id"));
						dto.setAttachid(rs.getString("attachment_id"));
						dto.setFilename(rs.getString("attachment_name"));
						dto.setAttachpath(rs.getString("attachment_path"));
						dto.setFilepath("/DownloadAttachmentAction.a?attachid="+rs.getString("attachment_id"));
						
						return dto;
					}
			});
			
		  if(attachmentList==null||attachmentList.isEmpty())return;
		  Map<String,List<AttachFileDto>> dataMaps=new HashMap<String,List<AttachFileDto>>();
		  for(AttachFileDto dto : attachmentList){
			  List<AttachFileDto> dtos=dataMaps.get(dto.getPartId());
			  if(dtos==null){
				  dtos=new ArrayList<AttachFileDto>();
				  dataMaps.put(dto.getPartId(),dtos);
			  }
			  dtos.add(dto);
		  }
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			
			  for(String key : dataMaps.keySet()){
				  if(NestUtil.isEmpty(dto.getPartId())) break;
				  if(dto.getPartId().equals(key)){
					  dto.setAttachFileDtos(dataMaps.get(dto.getPartId()));
				  }
			  }
		  }
		}catch (Exception e) {
			logger.error("queryMiddleSchoolAttachment(List<AppraiseBaseDto>)",e);
		}
	}


	@Override
	public void queryMiddleSchoolAppraisalInfoFromCache(String sectionCode,	List<String> eduIds, String termId,	List<AppraiseBaseDto> appraiseBaseDtos) {
		if(null!=eduIds && eduIds.size()>0){
			List<SubjectDto> czSubjectInfos = masterAppriseExt.getCZSubjectInfos();
			List<String> sectionInfos = redisServiceExt.readList(Constant.CZ_SECTION_INFO);
			String onePartId = "";
			String onePartName = "";
			String twoPartId = "";
			String twoPartName = "";
			if(null!=sectionInfos && sectionInfos.size()>0){
				for(String sectionId : sectionInfos){
					String[] sectionIds = sectionId.split("@");
					onePartId = sectionIds[0];
					onePartName = sectionIds[1];
					twoPartId = sectionIds[2];
					twoPartName = sectionIds[3];
					if(sectionCode.equals(onePartId)){
						for(String eduId : eduIds){
							this.cacheToViewList(termId, appraiseBaseDtos, czSubjectInfos, onePartId, onePartName, twoPartId, twoPartName, eduId);
						}
					}
				}
			}
		}
	}


	private void cacheToViewList(String termId,
			List<AppraiseBaseDto> appraiseBaseDtos,
			List<SubjectDto> czSubjectInfos, String onePartId,
			String onePartName, String twoPartId, String twoPartName,
			String eduId) {
		List<PartInfoCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termId, twoPartId,PartInfoCacheDto.class);
		if(null!=recodeInCache && recodeInCache.size()>0){
			for(PartInfoCacheDto picDto : recodeInCache){
				AppraiseBaseDto dto = new AppraiseBaseDto();
				dto.setUserid(picDto.getUserid());
				dto.setOnePartId(onePartId);
				dto.setPartName(onePartName);
				dto.setTwoPartId(twoPartId);
				dto.setTwoPartName(twoPartName);
				dto.setTopic(picDto.getTopic());
				dto.setPartId(picDto.getPart_id());
				dto.setAddress(picDto.getAddress());
				dto.setKeyword(picDto.getKeyword());
				dto.setSubjectId(picDto.getSubject_id());
				dto.setSubjectName(this.getSubjectName(picDto.getSubject_id(),czSubjectInfos));
				dto.setCooperationMan(picDto.getCooperation_man());
				dto.setTermid(picDto.getTermid());
				dto.setPartInfo(picDto.getPart_info());
				dto.setWriteMan(picDto.getWrite_man());
				dto.setSignerName(picDto.getSigner_name());
				dto.setCreateDate(picDto.getCreateDate());
				dto.setStartDate(picDto.getStartdate());
				dto.setEndDate(picDto.getEnddate());
				dto.setEdu_id(eduId);
				appraiseBaseDtos.add(dto);
			}
		}
		if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
			Collections.sort(appraiseBaseDtos, compareByAppraiseId);
		}
	}
	
	private String getSubjectName(String subject_id, List<SubjectDto> czSubjectInfos) {
		String subjectName = "";
		if(null!=subject_id && null!=czSubjectInfos && czSubjectInfos.size()>0){
			for(SubjectDto sDto : czSubjectInfos){
				if(subject_id.equals(sDto.getSubjectid())){
					subjectName = sDto.getSubjectName();
					break;
				}
			}
		}
		return subjectName;
	}


	@Override
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByConditionFromCache(List<String>studentNames,String sectionCode, List<String> eduIds, String termid,String levelcode,String cmis30id,String discode) {
		if(NestUtil.isEmpty(levelcode)||null ==eduIds || eduIds.size()<0)return null;
		
		List<AppraiseBaseDto> appraiseBaseDtos=new ArrayList<AppraiseBaseDto>();
		
		if((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			//查询子类型
			List<String> sectionIds = redisServiceExt.readList(Constant.GZ_SECTION_INFO);
		    List<String> appraisalTypeIds =  masterAppriseExt.getAppraisalTypeByUpAppraisalTypeId(sectionCode==null?null:Integer.parseInt(sectionCode));
			// 10新学期伊始的我  
		    if(Constant.BEGIN_OF_THE_NEW_TERM.equals(sectionCode)  && eduIds.size()>0){
		    	 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
		   
			//20学期结束时的我
		    }else if( Constant.AT_THE_END_OF_THE_TERM.equals(sectionCode) && eduIds.size()>0){
		    	 //单条记录评价类
		    	this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//初高中班主任评语查询
				queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
				
			//30 思想道德
		    }else if(Constant.IDEOLOGICAL_MORALITY.equals(sectionCode)&& eduIds.size()>0){
		    	 //单条记录评价类
		    	this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				this.queryRecordpackageFromCache(Constant.TYPE_SXJLD,eduIds,termid,appraiseBaseDtos,sectionIds);
				
			//40合作与交流
			}else if(Constant.COOPERATION_AND_EXCHANGE.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				this.queryRecordpackageFromCache(Constant.TYPE_HZ_JLD,eduIds,termid,appraiseBaseDtos,sectionIds);
			
			//50运动与健康
			}else if(Constant.SPORTS_AND_HEALTH.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//体质健康查询
				queryHealthstandard((Integer.parseInt(levelcode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			
			//60审美与表现
			}else if(Constant.AESTHETIC_AND_PERFORMANCE.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				this.queryRecordpackageFromCache(Constant.TYPE_SMYBX_JLD,eduIds,termid,appraiseBaseDtos,sectionIds);
			
			//70个性发展
			}else if(Constant.PERSONALITY_DEVELOPMENT.equals(sectionCode)&& eduIds.size()>0){  
				 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//个性发展基本情况
				this.queryApersonalityFromCache(studentNames,Constant.TYPE_GXFZ_JBQK,eduIds,termid,appraiseBaseDtos,sectionIds);
				
		    // 80学业成就
			}else if(Constant.ACADEMIC_ACHIEVEMENT.equals(sectionCode)&& eduIds.size()>0){
				 //单条记录评价类
				this.queryAppraiseFromCache(appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//学科学习过程记录
				this.queryAlearnprocessWorksFromCache(studentNames,Constant.TYPE_XY_GCJL,eduIds,termid,appraiseBaseDtos,sectionIds);
				//课程评语查询
				this.queryLearnprocessAppraisalFromCache(Constant.TYPE_KE_CHENG_PINGYU,eduIds,termid,appraiseBaseDtos,sectionIds);

		    //90综合实践活动
			}else if(Constant.COMPREHENSIVE_PRACTICAL_ACTIVITIES.equals(sectionCode)&& eduIds.size()>0){ 
				//研究性学习/社区服务/社会实践活动
				this.queryPracticesFromCache(studentNames,appraisalTypeIds,eduIds,termid,appraiseBaseDtos,sectionIds);
				//综合实践活动--自我评价查询
				this.queryPracticesSelfAppraiseFromCache(appraiseBaseDtos);
			}
		 
			
			//查询附件
			this.queryattchfileFromCache(appraiseBaseDtos);
			if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
				Collections.sort(appraiseBaseDtos, compareByAppraiseId);
			}
		}else if((Integer.parseInt(levelcode)) ==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			this.queryMiddleSchoolAppraisalInfoFromCache(sectionCode, eduIds, termid, appraiseBaseDtos);
			
			if(Constant.JUNIOR_TERN_END.equals(sectionCode)&& eduIds.size()>0){
				//初高中班主任评语查询
				this.queryAssess(eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}else if(Constant.JUNIOR_SPROT.equals(sectionCode)&& eduIds.size()>0){
				//初高中体质健康查询
				this.queryHealthstandard((Integer.parseInt(levelcode)),eduIds,termid,cmis30id,discode,appraiseBaseDtos);
			}
			
			//查询初中附件
			this.queryMiddleSchoolAttachmentFromCache(appraiseBaseDtos);
			if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
				Collections.sort(appraiseBaseDtos, compareByAppraiseId);
			}
		}
		return appraiseBaseDtos;
	}


	private void queryattchfileFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		try{
			if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
			List<AttachFileDto> attachFileDtos=new ArrayList<AttachFileDto>();
			for(AppraiseBaseDto abDto:appraiseBaseDtos){
				/*if(abDto.getEdu_id().equals("10019654")){
					System.out.println("test");
				}*/
				if(NestUtil.isEmpty(abDto.getApprasialid()))continue;
				List<AattachCacheDto> queryChildrenObjectListInCache = latestEvaluationRecordExt.queryAttachFileInCache(abDto.getApprasialid(), "a_attach",AattachCacheDto.class);
				if(null!=queryChildrenObjectListInCache && queryChildrenObjectListInCache.size()>0){
					for(AattachCacheDto acDto : queryChildrenObjectListInCache){
						AttachFileDto dto = new AttachFileDto();
						dto.setFilename(acDto.getAttachname());
						dto.setFilepath("/DownloadAttachAction.a?attachid="+acDto.getAttachid()+"&&foreignKey="+abDto.getApprasialid());
						dto.setAttachid(acDto.getAttachid());
						dto.setAttachpath(acDto.getAttachpath());
						dto.setAttachtypeid(acDto.getAttachtypeid());
						dto.setAttachtype(acDto.getAttachtype());
						attachFileDtos.add(dto);
					}
				}
			}
		  if(attachFileDtos==null||attachFileDtos.isEmpty())return;
		  //附件路径分组
		  Map<String,List<AttachFileDto>> dataMaps=new HashMap<String,List<AttachFileDto>>();
		  for(AttachFileDto filedto:attachFileDtos){
			  List<AttachFileDto>dtos=dataMaps.get(filedto.getAttachtypeid()+"_"+filedto.getAttachtype());
			  if(dtos==null){
				  dtos=new ArrayList<AttachFileDto>();
				  dataMaps.put(filedto.getAttachtypeid()+"_"+filedto.getAttachtype(),dtos);
			  }
			  dtos.add(filedto);
		  }
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			  //1：记录袋: 思想道德事迹记录袋/合作与交流记录袋/审美与表现记录袋
			  //2：个人发展:个性发展过程
			  //3：综合实践: 研究性学习评价/社区服务评价/社会实践基本评价
			  //4.学科学习发展过程:学科学习发展过程
			  if(dto==null
					  &&!Constant.TYPE_SXJLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SMYBX_JLD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_GXFZGC.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_YJXX.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SQFU.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_SHSJHD.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_XY_GCJL.equals(dto.getAppraisaltypeid())
					  &&!Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid()))
				  continue;
			  if(Constant.TYPE_SXJLD.equals(dto.getAppraisaltypeid())||Constant.TYPE_HZ_JLD.equals(dto.getAppraisaltypeid())
					  ||Constant.TYPE_SMYBX_JLD.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_1"));
			  }else if(Constant.TYPE_GXFZGC.equals(dto.getAppraisaltypeid())||Constant.TYPE_GXFZ_CGZS.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_2"));
			  }else if(Constant.TYPE_YJXX.equals(dto.getAppraisaltypeid())||
						  Constant.TYPE_SQFU.equals(dto.getAppraisaltypeid())||
						  Constant.TYPE_SHSJHD.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_3"));
			  }else if(Constant.TYPE_XY_GCJL.equals(dto.getAppraisaltypeid())){
				  dto.setAttachFileDtos(dataMaps.get(dto.getApprasialid()+"_4"));
			  }
		  }
		}catch (Exception e) {
			logger.error("queryattchfile(List<AppraiseBaseDto>)",e);
		}
	}


	private void queryPracticesSelfAppraiseFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		try{
			if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
			List<AppraiseBaseDto> appraiseList=new ArrayList<AppraiseBaseDto>();
			
			for(AppraiseBaseDto abDto:appraiseBaseDtos){
				if(NestUtil.isEmpty(abDto.getApprasialid()))continue;
				List<ApracticeappraisalCacheDto> queryChildrenObjectListInCache = latestEvaluationRecordExt.queryChildrenObjectListInCache(abDto.getApprasialid(), "a_practiceappraisal",ApracticeappraisalCacheDto.class);
				if(null!=queryChildrenObjectListInCache && queryChildrenObjectListInCache.size()>0){
					for(ApracticeappraisalCacheDto acDto : queryChildrenObjectListInCache){
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setAppraseridentify(Constant.me_apprasialidentify);
						dto.setApprasialid(acDto.getPracticeid());
						dto.setMyselfappraserid(acDto.getAppraisalid());
						dto.setAppraser(acDto.getSigner());
						dto.setMyselfapprasercontent(acDto.getContent());
						dto.setSigndate(acDto.getSigndate());	
						appraiseList.add(dto);
					}
				}
			}
		  if(appraiseList==null||appraiseList.isEmpty())return;
		  Map<String,List<AppraiseBaseDto>> dataMaps=new HashMap<String,List<AppraiseBaseDto>>();
		  for(AppraiseBaseDto dto : appraiseList){
			  List<AppraiseBaseDto> dtos=dataMaps.get(dto.getApprasialid());
			  if(dtos==null){
				  dtos=new ArrayList<AppraiseBaseDto>();
				  dataMaps.put(dto.getApprasialid(),dtos);
			  }
			  dtos.add(dto);
		  }
			
	
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			
			  for(String key : dataMaps.keySet()){
				  if(dto.getApprasialid().equals(key)){
					  dto.setPracticesSelfAppraiseDtos(dataMaps.get(dto.getApprasialid())); 
				  }
			  }
		  }
		  
		}catch (Exception e) {
			logger.error("queryPracticesSelfAppraiseFromCache(List<AppraiseBaseDto>)",e);
		}
	}


	private void queryPracticesFromCache(List<String>studentNames,List<String> appraisalTypeIds,	List<String> eduIds, String termid,	List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionIds) {
		try {
			if(null!=appraisalTypeIds && appraisalTypeIds.size()>0 && null!=eduIds && eduIds.size()>0){
				int count = 0;
				for(String eduId : eduIds){
					for(String typeId : appraisalTypeIds){
						List<ApracticesCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeId,ApracticesCacheDto.class);
						if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
							for(ApracticesCacheDto acDto : queryRecodeInCache){
								AppraiseBaseDto dto = new AppraiseBaseDto();
								dto.setApprasialid(acDto.getPracticeid());
								dto.setApprasial(acDto.getItem1());
								dto.setAppraseridentify(Constant.me_apprasialidentify);
								dto.setAppraseridentifynum("1");
								dto.setEdu_id(acDto.getEdu_id());
								dto.setAppraserid(acDto.getEdu_id());
								dto.setSigndate(acDto.getItem5());
								dto.setTermid(acDto.getSemesterid());
								dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
								dto.setItem1(acDto.getItem6());
								dto.setItem2(acDto.getItem7());
								dto.setColumNumberName(typIdToName(sectionIds, typeId));
								if(Constant.TYPE_SQFU.equals(acDto.getAppraisaltypeid())
										||Constant.TYPE_SHSJHD.equals(acDto.getAppraisaltypeid())){
									dto.setItem3(acDto.getItem8());
								}else if(Constant.TYPE_YJXX.equals(acDto.getAppraisaltypeid())){
									dto.setItem3(acDto.getItem2());
								}
								if(Constant.TYPE_SQFU.equals(acDto.getAppraisaltypeid())
										||Constant.TYPE_SHSJHD.equals(acDto.getAppraisaltypeid())){
									dto.setItem4(acDto.getItem2());
								}else if(Constant.TYPE_YJXX.equals(acDto.getAppraisaltypeid())){
									dto.setItem4(acDto.getItem9());
								}
								dto.setItem5(acDto.getItem9());
								dto.setAppraser(studentNames.get(count));
								appraiseBaseDtos.add(dto);
							}
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			logger.error("queryPracticesFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryLearnprocessAppraisalFromCache(String typeId, List<String> eduIds, String termid,	List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionIds) {
		try {
			if(null!=eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<AlearnprocessAppraisalCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeId,AlearnprocessAppraisalCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(AlearnprocessAppraisalCacheDto aacDto : queryRecodeInCache){
							AppraiseBaseDto dto = new AppraiseBaseDto();
							dto.setApprasialid(aacDto.getAppraisalid());
							dto.setApprasial(aacDto.getAppraisal());
							dto.setAppraseridentify(Constant.APPRASER_TEACHER);
							dto.setAppraseridentifynum("3");
							dto.setColumNumberName("课程评语");
							dto.setAppraser(aacDto.getSign());
							dto.setEdu_id(aacDto.getEdu_id());
							dto.setAppraserid(aacDto.getAppraiserrid());
							dto.setSigndate(aacDto.getSigndate());
							dto.setTermid(aacDto.getSemesterid());
							dto.setAppraisaltypeid(Constant.TYPE_KE_CHENG_PINGYU);
							dto.setItem1(aacDto.getSubject());
							appraiseBaseDtos.add(dto);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryLearnprocessAppraisalFromCache(List<String>,String,String,String,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryAlearnprocessWorksFromCache(List<String>studentNames,String typeId, List<String> eduIds, String termid,List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionIds) {
		try {
			if(null!=eduIds && eduIds.size()>0){
				int count = 0;
				for(String eduId : eduIds){
					List<AlearnprocessWorksCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeId, AlearnprocessWorksCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(AlearnprocessWorksCacheDto acDto : queryRecodeInCache){
							AppraiseBaseDto dto = new AppraiseBaseDto();
							dto.setApprasialid(acDto.getWorkid());
							dto.setApprasial(acDto.getProcessdesc());
							dto.setAppraseridentify(Constant.me_apprasialidentify);
							dto.setAppraseridentifynum("1");
							dto.setColumNumberName("学科学习过程记录");
							dto.setAppraser(studentNames.get(count));
							dto.setEdu_id(acDto.getEdu_id());
							dto.setAppraserid(acDto.getEdu_id());
							dto.setSigndate(acDto.getSigndate());
							dto.setTermid(acDto.getSemesterid());
							dto.setAppraisaltypeid(Constant.TYPE_XY_GCJL);
							dto.setItem1(acDto.getSubject());
							appraiseBaseDtos.add(dto);
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorksFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryApersonalityFromCache(List<String>studentNames,String typeId, List<String> eduIds,	String termid, List<AppraiseBaseDto> appraiseBaseDtos,List<String> sectionIds) {
		try {
			if(null!=eduIds && eduIds.size()>0){
				int count = 0;
				for(String eduId : eduIds){
					List<ApersonalityCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeId, ApersonalityCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(ApersonalityCacheDto acDto : queryRecodeInCache){
							AppraiseBaseDto dto = new AppraiseBaseDto();
							dto.setApprasialid(acDto.getBaseid());
							dto.setApprasial(acDto.getDevelopmentrd());
							dto.setEdu_id(acDto.getEdu_id());
							dto.setAppraserid(acDto.getEdu_id());
							dto.setTermid(acDto.getSemesterid());
							dto.setAppraisaltypeid(Constant.TYPE_GXFZ_JBQK);
							dto.setAppraser(studentNames.get(count));
							dto.setAppraseridentify(Constant.me_apprasialidentify);
							dto.setSigndate(acDto.getSigndate());
							dto.setAppraseridentifynum(acDto.getIndexid());
							if(NestUtil.isNotEmpty(acDto.getIndexid())){
								if("1".equals(acDto.getIndexid())){
									dto.setColumNumberName("个性发展基本情况特长");
								}else if("2".equals(acDto.getIndexid())){
									dto.setColumNumberName("个性发展基本情况有新意的成果");
								}else if("3".equals(acDto.getIndexid())){
									dto.setColumNumberName("个性发展基本情况其他 ");
								}
							}
							appraiseBaseDtos.add(dto);
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			logger.error("queryApersonalityFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryRecordpackageFromCache(String typeSxjld,List<String> eduIds, String termid,List<AppraiseBaseDto> appraiseBaseDtos,List<String>sectionIds) {
		try {
			if(null!=eduIds && eduIds.size()>0){
				for(String eduId : eduIds){
					List<ArecordpackageCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeSxjld,ArecordpackageCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(ArecordpackageCacheDto acDto : queryRecodeInCache){
							AppraiseBaseDto dto = new AppraiseBaseDto();
							dto.setApprasialid(acDto.getRecordid());
							dto.setApprasial(acDto.getContent());
							dto.setAppraseridentify(this.numToName(acDto.getAppraseridentify()));
							dto.setAppraseridentifynum(acDto.getAppraseridentify());
							dto.setAppraser(acDto.getSigner());
							dto.setEdu_id(acDto.getEdu_id());
							dto.setAppraserid(acDto.getEdu_id());
							dto.setColumNumberName(this.typIdToName(sectionIds, typeSxjld));
							dto.setSigndate(acDto.getSigndate());
							dto.setTermid(acDto.getSemesterid());
							dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
							appraiseBaseDtos.add(dto);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryRecordpackage(Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryAppraiseFromCache(List<String> appraisalTypeIds,List<String> eduIds, String termid,List<AppraiseBaseDto> appraiseBaseDtos,List<String>sectionIds) {
		try {
			if(null!=eduIds && eduIds.size()>0 && null!=appraisalTypeIds && appraisalTypeIds.size()>0){
				for(String eduId :eduIds){
					for(String typeId : appraisalTypeIds){
						List<AapprasialCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, typeId,AapprasialCacheDto.class);
						if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
							for(AapprasialCacheDto acDto : queryRecodeInCache){
								AppraiseBaseDto dto = new AppraiseBaseDto();
								dto.setApprasialid(acDto.getApprasialid());
								dto.setApprasial(acDto.getApprasial());
								dto.setAppraseridentify(this.numToName(acDto.getAppraseridentify()));
								dto.setAppraseridentifynum(acDto.getAppraseridentify());
								dto.setAppraser(acDto.getAppraser());
								dto.setAppraserid(acDto.getAppraiserrid());
								dto.setEdu_id(acDto.getEdu_id());
								dto.setSigndate(acDto.getSigndate());
								if(Constant.TYPE_END_BZRPY.equals(dto.getAppraisaltypeid()))
									continue;
								dto.setTermid(acDto.getSemesterid());
								dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
								dto.setColumNumberName(this.typIdToName(sectionIds,typeId));
								appraiseBaseDtos.add(dto);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryAppraiseFromCache(Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private String typIdToName(List<String> sectionIds,String typeId) {
		String columName = "";
		if(null!=sectionIds && sectionIds.size()>0){
			for(String sectionId : sectionIds){
				if(typeId.equals(sectionId.split("@")[0])){
					columName = sectionId.split("@")[2];
					break;
				}
			}
		}
		return columName;
	}


	private void queryMiddleSchoolAttachmentFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<AttachFileDto>attachmentList = new ArrayList<AttachFileDto>();
		try{
			for(AppraiseBaseDto dto:appraiseBaseDtos){
				if(NestUtil.isEmpty(dto.getPartId()))continue;
				List<AttachmentCacheDto> attachFileInCaches = latestEvaluationRecordExt.queryAttachFileInCache(dto.getPartId(), "Attachment",AttachmentCacheDto.class);
				if(null!=attachFileInCaches && attachFileInCaches.size()>0){
				for(AttachmentCacheDto acDto : attachFileInCaches){
					AttachFileDto afDto = new AttachFileDto();
					afDto.setPartId(acDto.getPart_id());
					afDto.setAttachid(acDto.getAttachment_id());
					afDto.setFilename(acDto.getAttachment_name());
					afDto.setAttachpath(acDto.getAttachment_path());
					afDto.setFilepath("/DownloadAttachmentAction.a?attachid="+acDto.getAttachment_id()+"&&foreignKey="+dto.getPartId());
					attachmentList.add(afDto);
				}
				}
			}
		  if(attachmentList==null||attachmentList.isEmpty())return;
		  Map<String,List<AttachFileDto>> dataMaps=new HashMap<String,List<AttachFileDto>>();
		  for(AttachFileDto dto : attachmentList){
			  List<AttachFileDto> dtos=dataMaps.get(dto.getPartId());
			  if(dtos==null){
				  dtos=new ArrayList<AttachFileDto>();
				  dataMaps.put(dto.getPartId(),dtos);
			  }
			  dtos.add(dto);
		  }
		  //填充附件到相应的dto里
		  for(AppraiseBaseDto dto:appraiseBaseDtos){
			
			  for(String key : dataMaps.keySet()){
				  if(NestUtil.isEmpty(dto.getPartId())) break;
				  if(dto.getPartId().equals(key)){
					  dto.setAttachFileDtos(dataMaps.get(dto.getPartId()));
				  }
			  }
		  }
		}catch (Exception e) {
			logger.error("queryMiddleSchoolAttachmentFromCache(List<AppraiseBaseDto>)",e);
		}
	}
	public  final Comparator compareByAppraiseId = new Comparator(){          
        public int compare(Object app1, Object app2) {  
            try{                                          
            	AppraiseBaseDto app11 = (AppraiseBaseDto) app1;
            	AppraiseBaseDto app22 = (AppraiseBaseDto) app2;
    			return app11.compareTo(app22);                         
            }catch(Exception e){
                e.printStackTrace();
            }         
            return 1;                        
        }  
    };
	private String numToName(String appraseridentify) {
		String numName = "";
		if(NestUtil.isNotEmpty(appraseridentify)){
			if("1".equals(appraseridentify)){
				numName="本人";
			}else if("2".equals(appraseridentify)){
				numName="同学";
			}else if("3".equals(appraseridentify)){
				numName="教师";
			}else if("4".equals(appraseridentify)){
				numName="班主任";
			}else if("5".equals(appraseridentify)){
				numName="学生家长";
			}
		}
		return numName;
	}
}
