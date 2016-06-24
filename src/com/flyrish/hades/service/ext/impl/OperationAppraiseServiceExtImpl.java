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
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IMasterAppriseExt;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;
import com.flyrish.hades.service.ext.IRedisServiceExt;
import com.flyrish.hades.util.DataSource;

public class OperationAppraiseServiceExtImpl extends JdbcRootManager implements
		IOperationAppraiseServiceExt {
	
		public ILatestEvaluationRecordExt latestEvaluationRecordExt;
		
		public IRedisServiceExt redisServiceExt;
		
		public IMasterAppriseExt masterAppriseExt;
		
		public IMasterAppriseExt getMasterAppriseExt() {
			return masterAppriseExt;
		}


		public void setMasterAppriseExt(IMasterAppriseExt masterAppriseExt) {
			this.masterAppriseExt = masterAppriseExt;
		}


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


		public ConstantBean getConstantBean() {
			return constantBean;
		}
	
	
		public void setConstantBean(ConstantBean constantBean) {
			this.constantBean = constantBean;
		}
		public ConstantBean constantBean;
	
	public List<AppraiseBaseDto> queryAppraiseBaseDtoByCondition(String studentName,String edu_id,
			String termid, String levelCode,String cmis30id,String discode) {
		String isStartAppraiseCache = constantBean.get("isStartAppraiseCache");
		if(NestUtil.isEmpty(levelCode)||NestUtil.isEmpty(edu_id))return null;
		List<AppraiseBaseDto> appraiseBaseDtos=new ArrayList<AppraiseBaseDto>();
		if((Integer.parseInt(levelCode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelCode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK)){
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("edu_id", edu_id);
			params.put("semesterid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			if("1".equals(isStartAppraiseCache)){
				//单条记录评价类(从缓存中获取评价数据)
				List<String> sectionInfos = redisServiceExt.readList(Constant.GZ_SECTION_INFO);
				this.queryAppraiseFromCache(edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryRecordpackageFromCache(edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryPracticesFromCache(studentName,edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryPracticesSelfAppraiseFromCache(appraiseBaseDtos);
				this.queryApersonalityFromCache(studentName,edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryAlearnprocessWorksFromCache(studentName,edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryLearnprocessAppraisalFromCache(edu_id,termid,appraiseBaseDtos,sectionInfos);
				this.queryattchfileFromCache(appraiseBaseDtos);
				if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
					Collections.sort(appraiseBaseDtos, compareByAppraiseId);
				}
			}else{
				//单条记录评价类
				queryAppraise(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//思想道德事迹记录袋/合作与交流行为记录袋/审美与表现记录袋
				queryRecordpackage(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//研究性学习/社区服务/社会实践活动
				queryPractices(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//综合实践活动--自我评价查询
				queryPracticesSelfAppraise(appraiseBaseDtos);
				//个性发展基本情况
				queryApersonality(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//学科学习过程记录
				queryAlearnprocessWorks(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//课程评语查询
				queryLearnprocessAppraisal(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//查询附件
				queryattchfile(appraiseBaseDtos);
			}
			//初高中班主任评语查询
			queryAssess(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
			//初高中体质健康查询
			queryHealthstandard(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
			
			return appraiseBaseDtos;
		}
		else if((Integer.parseInt(levelCode)) ==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("edu_id", edu_id);
			params.put("termid", termid);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			if("1".equals(isStartAppraiseCache)){
				//查询初中评价信息
				this.queryMiddleSchoolAppraisalInfoFromCache(edu_id,termid,appraiseBaseDtos);
				this.queryMiddleSchoolAttachmentFromCache(appraiseBaseDtos);
				if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
					Collections.sort(appraiseBaseDtos, compareByAppraiseId);
				}
				//查询初中附件
			}else{
				//查询初中评价信息
				queryMiddleSchoolAppraisalInfo(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
				//查询初中附件
				queryMiddleSchoolAttachment(cmis30id,discode,appraiseBaseDtos);
			}
			//初高中班主任评语查询
			queryAssess(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
			//初高中体质健康查询
			queryHealthstandard(edu_id,termid,cmis30id,discode,params,appraiseBaseDtos);
			
			return appraiseBaseDtos;
		}
		return null;
	}
	private void queryattchfileFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		try{
			if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
			List<AttachFileDto> attachFileDtos=new ArrayList<AttachFileDto>();
			for(AppraiseBaseDto aDto:appraiseBaseDtos){
				if(NestUtil.isEmpty(aDto.getApprasialid()))continue;
				/*if(aDto.getEdu_id().equals("10019654")){
					System.out.println("test");
				}*/
				List<AattachCacheDto> attachFileInCache = latestEvaluationRecordExt.queryAttachFileInCache(aDto.getApprasialid(), "a_attach",AattachCacheDto.class);
				if(null!=attachFileInCache && attachFileInCache.size()>0){
					for(AattachCacheDto acDto : attachFileInCache){
						AttachFileDto dto = new AttachFileDto();
						dto.setFilename(acDto.getAttachname());
						dto.setFilepath("/DownloadAttachAction.a?attachid="+acDto.getAttachid()+"&&foreignKey="+aDto.getApprasialid());
						dto.setAttachid(acDto.getAttachid());
						dto.setAttachtypeid(acDto.getAttachtypeid());
						dto.setAttachpath(acDto.getAttachpath());
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
			logger.error("queryattchfileFromCache(List<AppraiseBaseDto>)",e);
		}
	}


	private void queryLearnprocessAppraisalFromCache(String edu_id,	String termid, List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionInfos) {
		try {
			List<AlearnprocessAppraisalCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, Constant.TYPE_KE_CHENG_PINGYU,AlearnprocessAppraisalCacheDto.class);
			if(null!=recodeInCache && recodeInCache.size()>0){
				for(AlearnprocessAppraisalCacheDto acDto : recodeInCache){
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(acDto.getAppraisalid());
					dto.setPartId(acDto.getAppraisalid());
					dto.setApprasial(acDto.getAppraisal());
					dto.setAppraseridentify(Constant.APPRASER_TEACHER);
					dto.setAppraseridentifynum("3");
					dto.setColumNumberName("课程评语");
					dto.setAppraser(acDto.getSign());
					dto.setEdu_id(acDto.getEdu_id());
					dto.setAppraserid(acDto.getAppraiserrid());
					dto.setSigndate(acDto.getSigndate());
					dto.setTermid(acDto.getSemesterid());
					dto.setAppraisaltypeid(Constant.TYPE_KE_CHENG_PINGYU);
					dto.setItem1(acDto.getSubject());
					appraiseBaseDtos.add(dto);
				}
			}
		} catch (Exception e) {
			logger.error("queryLearnprocessAppraisalFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}

	private void queryAlearnprocessWorksFromCache(String name,String edu_id, String termid,	List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionInfos) {
		try {
			List<AlearnprocessWorksCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, Constant.TYPE_XY_GCJL,AlearnprocessWorksCacheDto.class);
			if(null!=recodeInCache && recodeInCache.size()>0){
				for(AlearnprocessWorksCacheDto acDto : recodeInCache){
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(acDto.getWorkid());
					dto.setPartId(acDto.getWorkid());
					dto.setApprasial(acDto.getProcessdesc());
					dto.setAppraseridentify(Constant.me_apprasialidentify);
					dto.setAppraseridentifynum("1");
					dto.setColumNumberName("学科作品展示");
					dto.setAppraser(name);
					dto.setEdu_id(acDto.getEdu_id());
					dto.setAppraserid(acDto.getEdu_id());
					dto.setSigndate(acDto.getSigndate());
					dto.setTermid(acDto.getSemesterid());
					dto.setAppraisaltypeid(Constant.TYPE_XY_GCJL);
					dto.setItem1(acDto.getSubject());
					appraiseBaseDtos.add(dto);
				}
			}
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorksFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryApersonalityFromCache(String name,String edu_id, String termid,List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionInfos) {
		try {
			List<ApersonalityCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, Constant.TYPE_GXFZ_JBQK,ApersonalityCacheDto.class);
			if(null!=recodeInCache && recodeInCache.size()>0){
				for(ApersonalityCacheDto acDto : recodeInCache){
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setPartId(acDto.getBaseid());
					dto.setApprasialid(acDto.getBaseid());
					dto.setApprasial(acDto.getDevelopmentrd());
					dto.setEdu_id(acDto.getEdu_id());
					dto.setAppraserid(acDto.getEdu_id());
					dto.setTermid(acDto.getSemesterid());
					dto.setAppraisaltypeid(Constant.TYPE_GXFZ_JBQK);
					dto.setAppraser(name);
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
		} catch (Exception e) {
			logger.error("queryApersonalityFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryPracticesSelfAppraiseFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		try{
			if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
			List<AppraiseBaseDto> appraiseList=new ArrayList<AppraiseBaseDto>();
			for(AppraiseBaseDto aDto:appraiseBaseDtos){
				if(NestUtil.isEmpty(aDto.getPracticeid()))continue;
				List<ApracticeappraisalCacheDto> childrenObjectListInCache = latestEvaluationRecordExt.queryChildrenObjectListInCache(aDto.getPracticeid(), "a_practiceappraisal",ApracticeappraisalCacheDto.class);
				if(null!=childrenObjectListInCache && childrenObjectListInCache.size()>0){
					for(ApracticeappraisalCacheDto acDto : childrenObjectListInCache){
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setApprasialid(acDto.getPracticeid());
						dto.setPartId(acDto.getPracticeid());
						dto.setMyselfappraserid(acDto.getAppraisalid());
						dto.setAppraseridentify(Constant.me_apprasialidentify);
						dto.setAppraser(acDto.getSigner());
						dto.setMyselfapprasercontent(acDto.getContent());
						dto.setColumNumberName("自我评价");
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
				  if(NestUtil.isEmpty(dto.getPracticeid())) break;
				  if(dto.getPracticeid().equals(key)){
					  dto.setPracticesSelfAppraiseDtos(dataMaps.get(dto.getApprasialid())); 
				  }
			  }
		  }
		}catch (Exception e) {
			logger.error("queryPracticesSelfAppraiseFromCache(List<AppraiseBaseDto>)",e);
		}
	}


	private void queryPracticesFromCache(String name,String edu_id, String termid, List<AppraiseBaseDto> appraiseBaseDtos, List<String> sectionInfos) {
		try {
			if(null!=sectionInfos && sectionInfos.size()>0){
				for(String sectionId : sectionInfos){
					String[] sectionIds = sectionId.split("@");
					String appraisaltypeid = sectionIds[0];
					if(NestUtil.isEmpty(appraisaltypeid)||!Constant.GZ_A_PRACITES.contains(appraisaltypeid))continue;
					String appraisaltype = sectionIds[2];
					try {
						List<ApracticesCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, appraisaltypeid,ApracticesCacheDto.class);
						if(null!=recodeInCache && recodeInCache.size()>0){
							for(ApracticesCacheDto acDto : recodeInCache){
								AppraiseBaseDto dto = new AppraiseBaseDto();
								dto.setApprasialid(acDto.getPracticeid());
								dto.setPartId(acDto.getPracticeid());
								dto.setPracticeid(acDto.getPracticeid());
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
								dto.setColumNumberName(appraisaltype);
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
								dto.setAppraser(name);
								appraiseBaseDtos.add(dto);
							}
						}
					}  catch (ClassCastException e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryPracticesFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryRecordpackageFromCache(String edu_id, String termid,	List<AppraiseBaseDto> appraiseBaseDtos,List<String> sectionInfos) {
		try {
			if(null!=sectionInfos && sectionInfos.size()>0){
				for(String sectionId : sectionInfos){
					String[] sectionIds = sectionId.split("@");
					String appraisaltypeid = sectionIds[0];
					if(NestUtil.isEmpty(appraisaltypeid)||!Constant.GZ_A_RECORDPACKAG.contains(appraisaltypeid))continue;
					String appraisaltype = sectionIds[2];
					try {
						List<ArecordpackageCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, appraisaltypeid,ArecordpackageCacheDto.class);
						if(null!=recodeInCache && recodeInCache.size()>0){
							for(ArecordpackageCacheDto acDto : recodeInCache){
								AppraiseBaseDto dto = new AppraiseBaseDto();
								dto.setApprasialid(acDto.getRecordid());
								dto.setPartId(acDto.getRecordid());
								dto.setApprasial(acDto.getContent());
								dto.setAppraseridentify(this.numToName(acDto.getAppraseridentify()));
								dto.setAppraseridentifynum(acDto.getAppraseridentify());
								dto.setAppraser(acDto.getSigner());
								dto.setEdu_id(acDto.getEdu_id());
								dto.setAppraserid(acDto.getEdu_id());
								dto.setColumNumberName(appraisaltype);
								dto.setSigndate(acDto.getSigndate());
								dto.setTermid(acDto.getSemesterid());
								dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
								appraiseBaseDtos.add(dto);
							}
						}
					} catch (ClassCastException e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryRecordpackageFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


	private void queryAppraiseFromCache(String edu_id, String termid,List<AppraiseBaseDto> appraiseBaseDtos,List<String> sectionInfos) {
		try {
			if(null!=sectionInfos && sectionInfos.size()>0){
				for(String sectionId : sectionInfos){
					String[] sectionIds = sectionId.split("@");
					String appraisaltypeid = sectionIds[0];
					if(NestUtil.isEmpty(appraisaltypeid)||!Constant.GZ_APPRAISE_TOW_NUM.contains(appraisaltypeid))continue;
					if(appraisaltypeid.length()!=4) continue;
					String appraisaltype = sectionIds[2];
					try {
						List<AapprasialCacheDto> recodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, appraisaltypeid,AapprasialCacheDto.class);
						if(null!=recodeInCache && recodeInCache.size()>0){
							for(AapprasialCacheDto acDto : recodeInCache){
								AppraiseBaseDto dto = new AppraiseBaseDto();
								dto.setApprasialid(acDto.getApprasialid());
								dto.setPartId(acDto.getApprasialid());
								dto.setApprasial(acDto.getApprasial());
								dto.setAppraseridentify(this.numToName(acDto.getAppraseridentify()));
								dto.setAppraseridentifynum(acDto.getAppraseridentify());
								dto.setAppraser(acDto.getAppraser());
								dto.setAppraserid(acDto.getAppraiserrid());
								dto.setEdu_id(acDto.getEdu_id());
								dto.setSigndate(acDto.getSigndate());
								dto.setTermid(acDto.getSemesterid());
								if(Constant.TYPE_END_BZRPY.equals(acDto.getAppraisaltypeid()))
									continue;
								dto.setAppraisaltypeid(acDto.getAppraisaltypeid());
								dto.setColumNumberName(appraisaltype);
								appraiseBaseDtos.add(dto);
							}
						}
					} catch (ClassCastException e) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryAppraiseFromCache(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}


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


	/*
	 * 从缓存中获取附件数据
	 */
	private void queryMiddleSchoolAttachmentFromCache(List<AppraiseBaseDto> appraiseBaseDtos) {
		List<AttachFileDto> afDtos = new ArrayList<AttachFileDto>();
		if(null!=appraiseBaseDtos && appraiseBaseDtos.size()>0){
			for(AppraiseBaseDto abDto : appraiseBaseDtos){
				List<AttachmentCacheDto> attachFileInCache = latestEvaluationRecordExt.queryAttachFileInCache(abDto.getPartId(), "Attachment",AttachmentCacheDto.class);
				if(null!=attachFileInCache && attachFileInCache.size()>0){
					for(AttachmentCacheDto acDto : attachFileInCache){
						AttachFileDto dto = new AttachFileDto();
						dto.setPartId(acDto.getPart_id());
						dto.setAttachid(acDto.getAttachment_id());
						dto.setFilename(acDto.getAttachment_name());
						dto.setAttachpath(acDto.getAttachment_path());
						dto.setFilepath("/DownloadAttachmentAction.a?attachment_id="+acDto.getAttachment_id()+"&&foreignKey="+abDto.getPartId());
						afDtos.add(dto);
					}
				}
			}
		}
		if(null!=afDtos && afDtos.size()>0){
			this.fillCZAttachmentToView(appraiseBaseDtos, afDtos);
		}
	}


	/*
	 * 从缓存中初中获取数据
	 */
	private void queryMiddleSchoolAppraisalInfoFromCache(String edu_id, String termid, List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			List<String> sectionInfos = redisServiceExt.readList(Constant.CZ_SECTION_INFO);
			List<SubjectDto> czSubjectInfos = masterAppriseExt.getCZSubjectInfos();
			if(null!=sectionInfos && sectionInfos.size()>0){
				for(String sectionId : sectionInfos){
					String[] sectionIds = sectionId.split("@");
					String onePartId = sectionIds[0];
					String onePartName = sectionIds[1];
					String twoPartId = sectionIds[2];
					String twoPartName = sectionIds[3];
					List<PartInfoCacheDto> queryRecodeInCache = latestEvaluationRecordExt.queryRecodeInCache(edu_id, termid, twoPartId,PartInfoCacheDto.class);
					if(null!=queryRecodeInCache && queryRecodeInCache.size()>0){
						for(PartInfoCacheDto picDto : queryRecodeInCache){
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
							appraiseBaseDto.add(dto);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryMiddleSchoolAppraisalInfoFromCache(String,String,List<AppraiseBaseDto>)",e);
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
			List<AttachFileDto> attachFileDtos=this.findList("OperationAppraiseServiceExtImpl.queryattchfile.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AttachFileDto dto = new AttachFileDto();
						dto.setFilename(rs.getString("attachname"));
						dto.setFilepath("/DownloadAttachAction.a?attachid="+rs.getString("attachid"));
						dto.setAttachid(rs.getString("attachid"));
						dto.setAttachtypeid(rs.getString("attachtypeid"));
						dto.setAttachpath(rs.getString("attachpath"));
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
	@DataSource("read")
	public String calcuHeighTermidByEduId(String edu_id,String cmis30id,String discode) {
		try {
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("edu_id",edu_id);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<String> heightermids =  this.findList("OperationAppraiseServiceExtImpl.calcuHeighTermidByEduId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("semesterid");
				}
			});
			if(heightermids!=null&&heightermids.size()>0){
				return heightermids.get(0);
			}
		} catch (Exception e) {
			logger.error("calcuHeighTermidByEduId(String)",e);
		}
		return null;
	}
	
	@DataSource("read")
	public String calcuMiddleTermidByEduId(String edu_id,String cmis30id,String discode) {
		try {
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("edu_id",edu_id);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			List<String> middletermids =  this.findList("OperationAppraiseServiceExtImpl.calcuMiddleTermidByEduId.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("termid");
				}
			});
			if(middletermids!=null&&middletermids.size()>0){
				return middletermids.get(0);
			}
		} catch (Exception e) {
			logger.error("calcuMiddleTermidByEduId(String)",e);
		}
		return null;
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
	private void queryAppraise(String edu_id,String termid,String cmis30id,String discode,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			List<AppraiseBaseDto> selfappraiseList =  this.findList("OperationAppraiseServiceExtImpl.queryAppraise.query", params, new RowMapper() {
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
			logger.error("queryAppraise(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
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
	private void queryRecordpackage(String edu_id,String termid,String discode,String cmis30id,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			List<AppraiseBaseDto> list =  this.findList("OperationAppraiseServiceExtImpl.queryRecordpackage.query",params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("recordid"));
					dto.setApprasial(rs.getString("content"));
					dto.setAppraseridentify(rs.getString("appraseridentify"));
//					dto.setAppraseridentify(Constant.me_apprasialidentify);
					dto.setAppraseridentifynum(rs.getString("appraseridentifynum"));
//					dto.setAppraseridentifynum("1");
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
			logger.error("queryRecordpackage(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
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
	private  void queryAlearnprocessWorks(String edu_id,String termid,String discode,String cmis30id,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto){
		try {
			List<AppraiseBaseDto> list =  this.findList("OperationAppraiseServiceExtImpl.queryAlearnprocessWorks.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("workid"));
					dto.setApprasial(rs.getString("processdesc"));
					dto.setAppraseridentify(Constant.me_apprasialidentify);
					dto.setAppraseridentifynum("1");
					dto.setColumNumberName("学科作品展示");
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
	private  void queryApersonality(String edu_id,String termid,String discode,String cmis30id,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto){
		try {
			List<AppraiseBaseDto> list =  this.findList("OperationAppraiseServiceExtImpl.queryApersonality.query", params, new RowMapper() {
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
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
					dto.setAppraseridentifynum(rs.getString("indexid"));
		//			dto.setAppraseridentify(rs.getString("indexid"));
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
	 * 个性发展过程
	 * 特长与成果展示
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param discode 区县代码
	 * @param cmis30id 学校标识号
	 * @param params 查询参数
	 * @return
	 *//*
	@DataSource("read")
	private  List<AppraiseBaseDto> queryApersonalitydevelop(String edu_id,String termid,String discode,String cmis30id,Map<String,Object> params){
		try {
			List<AppraiseBaseDto> recordpackageList =  this.findList("", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasial(rs.getString("baseid"));
					dto.setApprasial(rs.getString("developmentrd"));
					dto.setEdu_id(rs.getString("edu_id"));
					dto.setTermid(rs.getString("semesterid"));
					dto.setAppraseridentify(rs.getString("indexid"));
					dto.setAppraisaltypeid(Constant.TYPE_GXFZ_JBQK);
					return dto;
				}
			});
			return recordpackageList;
			
		} catch (Exception e) {
			logger.error("OperationAppraiseServiceExtImpl.queryAlearnprocessWorks(String,String,String,String,Map<String,Object>)",e);
		}
		return null;
	}*/
	
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
	private void queryLearnprocessAppraisal(String edu_id,String termid,String cmis30id,String discode,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto) {
		
		try {
			
			List<AppraiseBaseDto> learnprocessAppraisalList =  this.findList("OperationAppraiseServiceExtImpl.queryLearnprocessAppraisal.query1", params, new RowMapper() {
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
			logger.error("queryaLearnprocessAppraisal(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
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
	private void queryPractices(String edu_id,String termid,String cmis30id,String discode,Map<String,Object>params,List<AppraiseBaseDto> appraiseBaseDto) {
		try {
			
			List<AppraiseBaseDto> list =  this.findList("OperationAppraiseServiceExtImpl.queryPractices.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					
					AppraiseBaseDto dto = new AppraiseBaseDto();
					dto.setApprasialid(rs.getString("practiceid"));
					dto.setPracticeid(rs.getString("practiceid"));
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
	
	
	
	
	/**
	 * 初高中班主任评语查询
	 * @param edu_id 教育标识号
	 * @param termid 学期标识号
	 * @param cmis30id 学校标识号
	 * @param discode 区县标识号
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryAssess(String edu_id,String termid,String cmis30id,String discode,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto) {
		
		String studentId=null;
		Integer classid=null;
		String termId=null;
		String classId=null;
		Integer levelCode=null;
		
		String num=null;
		String gradeNum=null;
		
		
		/**
		 * 根据教育ID查询studentid |classid |levelcode
		 */
		Map<String,Object> params1 = new HashMap<String,Object>();
		params1.put("edu_id", edu_id);
		params1.put("cmis30id",cmis30id);
		params1.put("discode",discode);
		
		try {
			List<StudentDto> list =  this.findList("OperationAppraiseServiceExtImpl.querystudentIdByeduId.query", params1, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto dto = new StudentDto();
					dto.setClassid(rs.getInt("classid"));
					dto.setLevelcode(rs.getInt("levelcode"));
					dto.setStudentid(rs.getString("studentid"));
					return dto;
				}
			});
			
			if(list!=null && list.size()>0){
				classid=list.get(0).getClassid();
				levelCode=list.get(0).getLevelcode();
				studentId=list.get(0).getStudentid();
			}else
				return;
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
			
			//查询高中班主任评语
			Map<String,Object> params3 = new HashMap<String,Object>();
			params3.put("termid", termId);
			params3.put("cmis30id",cmis30id);
			params3.put("discode",discode);
			params3.put("studentId", studentId);
			
			try {
				List<AppraiseBaseDto> assessList =  this.findList("OperationAppraiseServiceExtImpl.queryAssess.query", params3, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setApprasialid(rs.getString("assessid"));
						dto.setApprasial(rs.getString("assesscontent"));
						dto.setAppraseridentify(Constant.APPRASER_MASTER);
						dto.setAppraisaltypeid(Constant.TYPE_END_BZRPY);
						dto.setAppraser(rs.getString("signname"));
				//		dto.setTermid(rs.getString("termId"));
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						dto.setSigndate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
						dto.setAppraseridentifynum("4");
						dto.setColumNumberName("班主任评语");
						
						return dto;
					}
				});
				appraiseBaseDto.addAll(assessList);
			} catch (Exception e) {
				logger.error("queryAssess(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
			}
			
		}
		//查询初中班主任评语
		else if(levelCode==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			termId=termid;
			
			Map<String,Object> params4 = new HashMap<String,Object>();
			params4.put("termid", termId);
			params4.put("cmis30id",cmis30id);
			params4.put("discode",discode);
			params4.put("studentId",studentId);
			
			try {
				List<AppraiseBaseDto> middleAppraisalList =  this.findList("OperationAppraiseServiceExtImpl.queryAssess.query", params4, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						dto.setPartId(rs.getString("assessid"));
						dto.setSignerName(rs.getString("signname"));
						dto.setPartInfo(rs.getString("assesscontent"));
						dto.setWriteMan("班主任");
						dto.setTwoPartId(Constant.CHARGE_TEACHER_APPRAISAL);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						dto.setCreateDate(rs.getDate("signdate")==null?null:sdf.format(rs.getDate("signdate")));
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
	private void queryHealthstandard(String edu_id,String termid,String cmis30id,String discode,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto) {
		
		String studentId=null;
		Integer classid=null;
		String termId=null;
		String classId=null;
		Integer levelCode=null;
		
		String num=null;
		String gradeNum=null;
		
		
		/**
		 * 根据教育ID查询studentid |classid |levelcode 
		 */
		Map<String,Object> params1 = new HashMap<String,Object>();
		params1.put("edu_id", edu_id);
		params1.put("cmis30id",cmis30id);
		params1.put("discode",discode);
		
		try {
			List<StudentDto> list =  this.findList("OperationAppraiseServiceExtImpl.querystudentIdByeduId.query", params1, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto dto = new StudentDto();
					dto.setClassid(rs.getInt("classid"));
					dto.setLevelcode(rs.getInt("levelcode"));
					dto.setStudentid(rs.getString("studentid"));
					return dto;
				}
			});
			
			if(list!=null && list.size()>0){
				classid=list.get(0).getClassid();
				levelCode=list.get(0).getLevelcode();
				studentId=list.get(0).getStudentid();
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
			params2.put("num", num);
			params2.put("classId", classId);
			params2.put("gradenum", gradeNum);
			
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
			
		}
		//学段为初中
		else if(levelCode==(Constant.DICT_TYPE_LEVELCODE_CZ)){
			
			termId=termid;
		}
		
		
		//查询体质健康
		Map<String,Object> params3 = new HashMap<String,Object>();
		params3.put("cmis30id",cmis30id);
		params3.put("discode",discode);
		params3.put("studentId", studentId);
		params3.put("termId", termId);
		
		try {
			List<AppraiseBaseDto> healthstandardList =  this.findList("OperationAppraiseServiceExtImpl.queryHealthstandard.query", params3, new RowMapper() {
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
					dto.setAppraisaltypeid(Constant.TYPE_YDJKTZJK);	//高中评价类型
					dto.setTwoPartId(Constant.PLAY_PHYSCIAL_HEALTH);//初中评价类型
					
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
	
	//综合实践活动--自我评价查询
	@DataSource("read")
	private void queryPracticesSelfAppraise(List<AppraiseBaseDto> appraiseBaseDtos){
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<String> ids=new ArrayList<String>();
		
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(NestUtil.isEmpty(dto.getPracticeid()))continue;
			ids.add(dto.getPracticeid());
		}
		
		Map<String,Object>params=new HashMap<String,Object>();
		if(ids.size()<1){
			ids.add("-1");
		}
		params.put("appraisalids",ids);
		try{
			ISqlElement sqlDemo=this.processSql(params, "OperationAppraiseServiceExtImpl.queryPracticesSelfAppraise.query");
			
			List<AppraiseBaseDto> appraiseList=this.findList("OperationAppraiseServiceExtImpl.queryPracticesSelfAppraise.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraiseBaseDto dto = new AppraiseBaseDto();
						
						dto.setApprasialid(rs.getString("practiceid"));
						dto.setMyselfappraserid(rs.getString("appraisalid"));
						dto.setAppraseridentify(Constant.me_apprasialidentify);
						dto.setAppraser(rs.getString("signer"));
						dto.setMyselfapprasercontent(rs.getString("content"));
						dto.setColumNumberName("自我评价");
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
				  if(NestUtil.isEmpty(dto.getPracticeid())) break;
				  if(dto.getPracticeid().equals(key)){
					  dto.setPracticesSelfAppraiseDtos(dataMaps.get(dto.getApprasialid())); 
				  }
			  }
		  }
		}catch (Exception e) {
			logger.error("queryPracticesSelfAppraise(List<AppraiseBaseDto>)",e);
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
	private void queryMiddleSchoolAppraisalInfo(String edu_id,String termid,String cmis30id,String discode,Map<String,Object> params,List<AppraiseBaseDto> appraiseBaseDto){
		
		try {
			
			List<AppraiseBaseDto> middleAppraisalList =  this.findList("OperationAppraiseServiceExtImpl.queryMiddleSchoolAppraisalInfo.query", params, new RowMapper() {
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
					
					return dto;
				}
			});
			appraiseBaseDto.addAll(middleAppraisalList);
		} catch (Exception e) {
			logger.error("queryMiddleSchoolAppraisalInfo(String,String,String,String,Map<String,Object>,List<AppraiseBaseDto>)",e);
		}
	}
	
	
	/**
	 * 查询初中附件
	 */
	@DataSource("read")
	private void queryMiddleSchoolAttachment(String cmis30id,String discode,List<AppraiseBaseDto> appraiseBaseDtos){
		if(appraiseBaseDtos==null||appraiseBaseDtos.isEmpty())return;
		List<String> ids=new ArrayList<String>();
		
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(NestUtil.isEmpty(dto.getPartId()))continue;
			ids.add(dto.getPartId());
		}
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("partIds",ids);
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		try{		
			List<AttachFileDto> attachmentList=this.findList("OperationAppraiseServiceExtImpl.queryMiddleSchoolAttachment.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						
						AttachFileDto dto = new AttachFileDto();
						dto.setPartId(rs.getString("part_id"));
						dto.setAttachid(rs.getString("attachment_id"));
						dto.setFilename(rs.getString("attachment_name"));
						dto.setAttachpath(rs.getString("attachment_path"));
						dto.setFilepath("/DownloadAttachmentAction.a?attachment_id="+rs.getString("attachment_id"));
						return dto;
					}
			});
			
		  if(attachmentList==null||attachmentList.isEmpty())return;
		  fillCZAttachmentToView(appraiseBaseDtos, attachmentList);
		}catch (Exception e) {
			logger.error("queryMiddleSchoolAttachment(List<AppraiseBaseDto>)",e);
		}
	}


	private void fillCZAttachmentToView(List<AppraiseBaseDto> appraiseBaseDtos,
			List<AttachFileDto> attachmentList) {
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
}



