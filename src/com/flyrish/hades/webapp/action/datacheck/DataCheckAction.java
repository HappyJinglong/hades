package com.flyrish.hades.webapp.action.datacheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.utils.NestUtil;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.PartInfoDto;
import com.flyrish.hades.service.ext.IDataCheckExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class DataCheckAction extends BaseAction {

	@Spring
	IDataCheckExt dataCheckExt;
	
	public List<PartInfoDto> partinfoList;//初中自我评价列表
	
	public List<AppraisalDto> appraisalList;//初中自我评价列表
	
	public String gradelength;//年级是四年制还是三年制
	
	public String showdiv;
	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		isStartAppraiseCache= constantBean.get("isStartAppraiseCache");
		
		if (NestUtil.isNotEmpty(showdiv)) {
			getLoginInfo(request);
			if("2012003".equals(userDto.getLevelcode())||"2012004".equals(userDto.getLevelcode())){
				doShowGZ(request,response,session);
				return "datacheck_gz.jsp";
			}else{
				doShowCZ(request,response,session);
				return "datacheck_cz.jsp";
			}
		}else {
			return "showdiv.jsp";
		}
	}
	
	public void doShowCZ(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		getLoginInfo(request);
		
		Map<String, Object> queryMap1 = new HashMap<String, Object>();
		queryMap1.put("levelid", userDto.getLevelid());
		List<String> lst = new ArrayList<String>();
		lst = dataCheckExt.querygradelength(queryMap1);
		if(lst!=null&&lst.size()>0){
			gradelength =lst.get(0);
		}
		
		List<PartInfoDto> infoList = new ArrayList<PartInfoDto>();
		List<PartInfoDto> assessList = new ArrayList<PartInfoDto>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cmis30id", userDto.getCmis30id());
		queryMap.put("discode", userDto.getDiscode());
		queryMap.put("eduid", userDto.getEduId());
	
	 if("0".equals(isStartAppraiseCache)){
			infoList = dataCheckExt.queryPartInfo(queryMap);
		}else{
			String eduId=userDto.getEduId();
			List<String> listSemesterid=new ArrayList<String>();
			String gradenum=userDto.getGradenum();
			Integer Semes=Integer.valueOf((userDto.getTermId().substring(0,4)));
			String[] listSemesteridThree  = {"1","2","1","2","1","2"};//学期对应字段三年内
			String[] listSemesteridFour = {"1","2","1","2","1","2","1","2"};//学期对应字段
			String[] greedFour = {"6","6","7","7","8","8","9","9"};//学期对应字段
			String[] greedThree = {"7","7","8","8","9","9"};//学期对应字段
			
			String[] listLanMu = {"11" , "12", "21", "23", "31","32","33","34","35","41","42" ,"43" ,"44", "45","46" ,"51","52","53", "54","55" ,"61","62", "63", "64", "65", "71","72" , "73", "74","75", "81", "82", "83", "84", "85" , "91", "92"  , "93" ,"94", "95"};
			     
			
			if("4".equals(gradelength)){
				if("6".equals(gradenum)){
					String  t1=String.valueOf(Semes)+"1";
					String  t2=String.valueOf(Semes)+"2";
					String  t4=String.valueOf(Semes+1)+"1";
					String  t5=String.valueOf(Semes+1)+"2";
					String  t6=String.valueOf(Semes+2)+"1";
					String  t7=String.valueOf(Semes+2)+"2";
					String  t8=String.valueOf(Semes+3)+"1";
					String  t9=String.valueOf(Semes+3)+"2";
					listSemesterid.add(t1);
					listSemesterid.add(t2);
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}else if("7".equals(gradenum)){
					String  t1=String.valueOf(Semes-1)+"1";
					String  t2=String.valueOf(Semes-1)+"2";
					String  t4=String.valueOf(Semes)+"1";
					String  t5=String.valueOf(Semes)+"2";
					String  t6=String.valueOf(Semes+1)+"1";
					String  t7=String.valueOf(Semes+1)+"2";
					String  t8=String.valueOf(Semes+2)+"1";
					String  t9=String.valueOf(Semes+2)+"2";
					listSemesterid.add(t1);
					listSemesterid.add(t2);
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}else if("8".equals(gradenum)){
					String  t1=String.valueOf(Semes-2)+"1";
					String  t2=String.valueOf(Semes-2)+"2";
					String  t4=String.valueOf(Semes-1)+"1";
					String  t5=String.valueOf(Semes-1)+"2";
					String  t6=String.valueOf(Semes)+"1";
					String  t7=String.valueOf(Semes)+"2";
					String  t8=String.valueOf(Semes+1)+"1";
					String  t9=String.valueOf(Semes+1)+"2";
					listSemesterid.add(t1);
					listSemesterid.add(t2);
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}else if("9".equals(gradenum)){
					String  t1=String.valueOf(Semes-3)+"1";
					String  t2=String.valueOf(Semes-3)+"2";
					String  t4=String.valueOf(Semes-2)+"1";
					String  t5=String.valueOf(Semes-2)+"2";
					String  t6=String.valueOf(Semes-1)+"1";
					String  t7=String.valueOf(Semes-1)+"2";
					String  t8=String.valueOf(Semes)+"1";
					String  t9=String.valueOf(Semes)+"2";
					listSemesterid.add(t1);
					listSemesterid.add(t2);
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}
			
			
				//List<String> listSemesterid  //学期
				
				
				 
				 //   <td class="h50" id="12--1--7">0</td>
			      //<td class="h50" id="12--2--7">0</td>
			      //<td class="h50" id="12--1--8">0</td>
			     // <td class="h50" id="12--2--8">0</td>
			   	//	var id='${pl.two_part_id}'+"--"+'${pl.termType}'+"--"+'${pl.gradeNum}';
			   /* dto.setCheckCount(rs.getString("checkcount"));
				dto.setGradeNum(rs.getString("gradenum"));
				dto.setTwo_part_id(rs.getString("two_part_id"));
				dto.setTermType(rs.getString("termtype"));*/
			   for(int z=0;z<listSemesterid.size();z++){
			  // for(String sstrime:listSemesterid){
				   String sstrime=listSemesterid.get(z); 
				      for(int i=0;i<listLanMu.length;i++){
				    	  String lanMu=listLanMu[i];
				    	  List<PartInfoCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,sstrime,lanMu,PartInfoCacheDto.class);
				          if(null!=sss){
				        	  if(0<sss.size()){
				        	      PartInfoDto appraisalDto1=new PartInfoDto();
								  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
								  appraisalDto1.setGradeNum(greedFour[z]);
								  appraisalDto1.setTwo_part_id(lanMu);
								  appraisalDto1.setTermType(listSemesteridFour[z]);
								  infoList.add(appraisalDto1);
				        	  }
				          }
				      }
			   }
			    
			    
			
			
			
			
			}else{
			    if("7".equals(gradenum)){
					String  t4=String.valueOf(Semes)+"1";
					String  t5=String.valueOf(Semes)+"2";
					String  t6=String.valueOf(Semes+1)+"1";
					String  t7=String.valueOf(Semes+1)+"2";
					String  t8=String.valueOf(Semes+2)+"1";
					String  t9=String.valueOf(Semes+2)+"2";
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}else if("8".equals(gradenum)){
					String  t4=String.valueOf(Semes-1)+"1";
					String  t5=String.valueOf(Semes-1)+"2";
					String  t6=String.valueOf(Semes)+"1";
					String  t7=String.valueOf(Semes)+"2";
					String  t8=String.valueOf(Semes+1)+"1";
					String  t9=String.valueOf(Semes+1)+"2";
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}else if("9".equals(gradenum)){
					String  t4=String.valueOf(Semes-2)+"1";
					String  t5=String.valueOf(Semes-2)+"2";
					String  t6=String.valueOf(Semes-1)+"1";
					String  t7=String.valueOf(Semes-1)+"2";
					String  t8=String.valueOf(Semes)+"1";
					String  t9=String.valueOf(Semes)+"2";
					listSemesterid.add(t4);
					listSemesterid.add(t5);
					listSemesterid.add(t6);
					listSemesterid.add(t7);
					listSemesterid.add(t8);
					listSemesterid.add(t9);
				}
			
			    for(int z=0;z<listSemesterid.size();z++){
					  // for(String sstrime:listSemesterid){
						   String sstrime=listSemesterid.get(z); 
						      for(int i=0;i<listLanMu.length;i++){
						    	  String lanMu=listLanMu[i];
						    	  List<PartInfoCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,sstrime,lanMu,PartInfoCacheDto.class);
						          if(null!=sss){
						        	  if(0<sss.size()){
						        	      PartInfoDto appraisalDto1=new PartInfoDto();
										  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
										  appraisalDto1.setGradeNum(greedThree[z]);
										  appraisalDto1.setTwo_part_id(lanMu);
										  appraisalDto1.setTermType(listSemesteridThree[z]);
										  infoList.add(appraisalDto1);
						            }
						          }
						      }
					   }
			
			}
			
			
		
		
		}
	    assessList = dataCheckExt.queryClassTeacher(queryMap);
		partinfoList = new ArrayList<PartInfoDto>();
		if(infoList.size()>0&&infoList!=null){
			for(int i=0;i<infoList.size();i++){
				PartInfoDto dto = new PartInfoDto();
				dto = infoList.get(i);
				if(!"22".equals(dto.getTwo_part_id())){
					partinfoList.add(dto);
				}
			}
		}
		if(assessList.size()>0&&assessList!=null){
			for(int j=0;j<assessList.size();j++){
				PartInfoDto dto = new PartInfoDto();
				dto = assessList.get(j);
					partinfoList.add(dto);
			}
		}
		
	}
	
	public void doShowGZ(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		getLoginInfo(request);
		List<AppraisalDto> Apprasiallst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> recordpackagelst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> LearnprocessWorkslst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> Practiceslst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> LearnprocessAppraisallst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> Personalitylst = new ArrayList<AppraisalDto>();
		List<AppraisalDto> AssessGZlst = new ArrayList<AppraisalDto>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cmis30id", userDto.getCmis30id());
		queryMap.put("discode", userDto.getDiscode());
		queryMap.put("eduid", userDto.getEduId());
		
		
		if("0".equals(isStartAppraiseCache)){
				Apprasiallst = dataCheckExt.queryApprasial(queryMap);
				 
				 /* <td class="h50" id="3020--11--3">0</td>
			      <td class="h50" id="3020--12--3">0</td>
			      <td class="h50" id="3020--21--3">0</td>
			      <td class="h50" id="3020--22--3">0</td>
			      <td class="h50" id="3020--31--3">0</td>
			      <td class="h50" id="3020--32--3">0</td>*/
				
				
				
				recordpackagelst = dataCheckExt.queryRecordpackage(queryMap);
				LearnprocessAppraisallst = dataCheckExt.queryLearnprocessAppraisal(queryMap);
				
				LearnprocessWorkslst = dataCheckExt.queryLearnprocessWorks(queryMap);
				Personalitylst = dataCheckExt.queryPersonality(queryMap);
				Practiceslst = dataCheckExt.queryPractices(queryMap);
				
				AssessGZlst = dataCheckExt.queryClassTeacherGZ(queryMap);
			}else{
					
				 String eduId=userDto.getEduId();
				String[] listSemesterid = {"11","12","21","22","31","32"};
				String[] listLanMu1 = {"9010","9020","9030"};
				String[] listLanMu = {"1010","1020","2010","2020","2030", "2040","3010","3020", "3030", "3040" , "4010" , "4020", "5010", "5020" , "6010", "6020", "7010" , "7020", "7030"  ,"4030","4040", "5030", "5040" ,"5050", "6030", "6040" , "7040"   , "7050" , "8010" , "8020" ,"8030" , "8040" , "9010" , "9020" , "9030"};
				/*appraiserdic=学生本人#1,同学#2,教师#3,班主任#4,学生家长#5*/
				String[] listAppraseridentify ={"1","2","3","4","5"};//
				for(int i=0;i<listSemesterid.length;i++){
					String semesterid= listSemesterid[i];
					for(int y=0;y<listLanMu.length;y++){
						String lanMu=listLanMu[y];

						     List<AapprasialCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,lanMu,AapprasialCacheDto.class);
						     if(null!=sss){
									  Integer checkCount1=0;
									  Integer checkCount2=0;
									  Integer checkCount3=0;
									  Integer checkCount4=0;
									  Integer checkCount5=0;
									  for(AapprasialCacheDto aappras :sss ){
										 Integer fy=Integer.valueOf( aappras.getAppraseridentify());
										 switch (fy) {
											case 1:
												checkCount1+=1;
												break;
											case 2:
												checkCount2+=1;
												break;
											case 3:
												checkCount3+=1;
												break;
											case 4:
												checkCount3+=1;
												break;
											case 5:
												checkCount5+=1;
												break;
									
										}
									  }
									 if(0<checkCount1){
										  AppraisalDto appraisalDto1=new AppraisalDto();
										  appraisalDto1.setCheckCount(String.valueOf(checkCount1));
										  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
										  appraisalDto1.setAppraisaltypeid(Integer.valueOf(lanMu));
										  appraisalDto1.setAppraseridentify(1);
										  Apprasiallst.add(appraisalDto1);
									 }
									 if(0<checkCount2){
										  AppraisalDto appraisalDto2=new AppraisalDto();
										  appraisalDto2.setCheckCount(String.valueOf(checkCount2));
										  appraisalDto2.setSemesterid(Integer.valueOf(semesterid));
										  appraisalDto2.setAppraisaltypeid(Integer.valueOf(lanMu));
										  appraisalDto2.setAppraseridentify(2);
										  Apprasiallst.add(appraisalDto2);
									 }
									 if(0<checkCount3){
										  AppraisalDto appraisalDto3=new AppraisalDto();
										  appraisalDto3.setCheckCount(String.valueOf(checkCount3));
										  appraisalDto3.setSemesterid(Integer.valueOf(semesterid));
										  appraisalDto3.setAppraisaltypeid(Integer.valueOf(lanMu));
										  appraisalDto3.setAppraseridentify(3);
										  Apprasiallst.add(appraisalDto3);
									 }
									 if(0<checkCount4){
										  AppraisalDto appraisalDto4=new AppraisalDto();
										  appraisalDto4.setCheckCount(String.valueOf(checkCount4));
										  appraisalDto4.setSemesterid(Integer.valueOf(semesterid));
										  appraisalDto4.setAppraisaltypeid(Integer.valueOf(lanMu));
										  appraisalDto4.setAppraseridentify(4);
										  Apprasiallst.add(appraisalDto4);
									 }
									 if(0<checkCount5){
										  AppraisalDto appraisalDto5=new AppraisalDto();
										  appraisalDto5.setCheckCount(String.valueOf(checkCount5));
										  appraisalDto5.setSemesterid(Integer.valueOf(semesterid));
										  appraisalDto5.setAppraisaltypeid(Integer.valueOf(lanMu));
										  appraisalDto5.setAppraseridentify(5);
										  Apprasiallst.add(appraisalDto5);
									 }
						}
				
					}
					
				}
				
/////////////////////////////222222222222222222222222222222222222222////////////////////////////////////////////////////////////////////////////		
				for(int i=0;i<listSemesterid.length;i++){
					String semesterid= listSemesterid[i];
					for(int y=0;y<listLanMu.length;y++){
						String lanMu=listLanMu[y];
							  List<ArecordpackageCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,lanMu,ArecordpackageCacheDto.class);
							  if(null!=sss){
								  if(0<sss.size()){
									  AppraisalDto appraisalDto1=new AppraisalDto();
									  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
									  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
									  appraisalDto1.setAppraisaltypeid(Integer.valueOf(lanMu));
									  appraisalDto1.setAppraseridentify(1);
									  recordpackagelst.add(appraisalDto1);
								   }
								  }
					}
					
				}
				
////////////////////////a_learnprocess_appraisal//33333333333333333333333333333333333333333///////////////////////////////////////////////////////
				for(int i=0;i<listSemesterid.length;i++){
					 String semesterid= listSemesterid[i];
							  List<AlearnprocessAppraisalCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,"9999",AlearnprocessAppraisalCacheDto.class);
							  if(null!=sss){
								  if(0<sss.size()){
									  AppraisalDto appraisalDto1=new AppraisalDto();
									  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
									  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
									  appraisalDto1.setAppraisaltypeid(890909);
									  appraisalDto1.setAppraseridentify(1);
									  LearnprocessAppraisallst.add(appraisalDto1);
								  }
							  }
				}

/////////////////////////////////////////44444444444444444444444444444444444444444/////////////////////////////////////	
				for(int i=0;i<listSemesterid.length;i++){
					 String semesterid= listSemesterid[i];
							  List<AlearnprocessWorksCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,"8010",AlearnprocessWorksCacheDto.class);
							  if(null!=sss){
								  if(0<sss.size()){
									  AppraisalDto appraisalDto1=new AppraisalDto();
									  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
									  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
									  appraisalDto1.setAppraisaltypeid(8010);
									  appraisalDto1.setAppraseridentify(1);
									  LearnprocessWorkslst.add(appraisalDto1);
								  }
							  }
				}
////////////////////////555555555555555555555555555555/////////////////////////////
				
				for(int i=0;i<listSemesterid.length;i++){
					 String semesterid= listSemesterid[i];
							  List<ApersonalityCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,"7010",ApersonalityCacheDto.class);
							  if(null!=sss){
								  if(sss.size()>0){
									  AppraisalDto appraisalDto1=new AppraisalDto();
										  appraisalDto1.setCheckCount("1");
									  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
									  appraisalDto1.setAppraisaltypeid(7010);
									  appraisalDto1.setAppraseridentify(1);
									  Personalitylst.add(appraisalDto1);
								  }
							  }
				}
///////////////////		66666666666666666666666666666666		//////////////////////////
				
				for(int i=0;i<listSemesterid.length;i++){
					String semesterid= listSemesterid[i];
					for(int y=0;y<listLanMu1.length;y++){
						String lanMu=listLanMu1[y];
							  List<ApracticesCacheDto> sss=  latestEvaluationRecordExt.queryRecodeInCache(eduId,semesterid,lanMu,ApracticesCacheDto.class);
							  if(null!=sss){
								  if(sss.size()>0){ 
									  AppraisalDto appraisalDto1=new AppraisalDto();
									  appraisalDto1.setCheckCount(String.valueOf(sss.size()));
									  appraisalDto1.setSemesterid(Integer.valueOf(semesterid));
									  appraisalDto1.setAppraisaltypeid(Integer.valueOf(listLanMu1[y]));
									  appraisalDto1.setAppraseridentify(1);
									  Practiceslst.add(appraisalDto1);
								  }
							  }
					}
					
				}
//////////////////////////7777777777777777777777777/////////////////////////////////////
	
				AssessGZlst = dataCheckExt.queryClassTeacherGZ(queryMap);
			}
		appraisalList = new ArrayList<AppraisalDto>();
		if(Apprasiallst.size()>0&&Apprasiallst!=null){
			for(int i=0;i<Apprasiallst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = Apprasiallst.get(i);
				appraisalList.add(dto);
			}
		}
		if(AssessGZlst.size()>0&&AssessGZlst!=null){
			for(int i=0;i<AssessGZlst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = AssessGZlst.get(i);
				appraisalList.add(dto);
			}
		}
		if(recordpackagelst.size()>0&&recordpackagelst!=null){
			for(int i=0;i<recordpackagelst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = recordpackagelst.get(i);
				appraisalList.add(dto);
			}
		}
		if(Personalitylst.size()>0&&Personalitylst!=null){
			for(int i=0;i<Personalitylst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = Personalitylst.get(i);
				appraisalList.add(dto);
			}
		}
		if(LearnprocessAppraisallst.size()>0&&LearnprocessAppraisallst!=null){
			for(int i=0;i<LearnprocessAppraisallst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = LearnprocessAppraisallst.get(i);
				appraisalList.add(dto);
			}
		}
		if(LearnprocessWorkslst.size()>0&&LearnprocessWorkslst!=null){
			for(int i=0;i<LearnprocessWorkslst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = LearnprocessWorkslst.get(i);
				appraisalList.add(dto);
			}
		}
		if(Practiceslst.size()>0&&Practiceslst!=null){
			for(int i=0;i<Practiceslst.size();i++){
				AppraisalDto dto = new AppraisalDto();
				dto = Practiceslst.get(i);
				appraisalList.add(dto);
			}
		}
	}
}
