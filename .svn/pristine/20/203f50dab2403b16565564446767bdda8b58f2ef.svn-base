package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.nestframework.commons.hibernate.ISqlElement;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.CzGreedLengthDto;
import com.flyrish.hades.dto.FistGzTopicDto;
import com.flyrish.hades.dto.GreedDto;
import com.flyrish.hades.dto.ListBenDto;
import com.flyrish.hades.dto.ListTopicBenDto;
import com.flyrish.hades.dto.SchoolDto;
import com.flyrish.hades.dto.TopicStatDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IareaStatExt;
import com.flyrish.hades.util.DataSource;




public class AreaStatExtImpl extends JdbcRootManager implements IareaStatExt{
      
	
	@DataSource("read")  
	public List findAllSchoolid(String likeSchoolName, String levelCode,
			String discode) {
		
		 if(discode==null){
			 return  null;
		  }
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("schoolName",likeSchoolName);
		params.put("discode",discode);
		params.put("levelCode",levelCode);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"AreaStatExtImpl.findAllSchoolid.query");
			List classidlist =this.findList("AreaStatExtImpl.findAllSchoolid.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						Integer shoolid=rs.getInt("cmis30id");
					   return shoolid;
				}
			});
			
				return classidlist;
		}catch(Exception e){
			logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			throw new ManagerException(e);
		}
		
		
	}

	//高中校区评价班级数
	@DataSource("read")  
	public SchoolDto findClassApper(Integer cim, Integer intyear,
			String levelCode, String discode, String termid) {
		//查询本校本届所有的班级
		SchoolDto sch=new SchoolDto();
		for(int i=0;i<3;i++){
			List schoolClassidlist;
		    intyear =intyear+1;  	 
			Map<String,Object>params=new HashMap<String,Object>();
			params.put("graduateyear",intyear);
			params.put("discode",discode);
			params.put("levelCode",levelCode);
			params.put("cmis30id",cim);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params,"AreaStatExtImpl.findClassApper.query");
				   schoolClassidlist =this.findList("AreaStatExtImpl.findClassApper.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							Integer classid=rs.getInt("classid");
							return classid;
					}
				});
				
			}catch(Exception e){
				logger.error("findClassApper(Integer,Integer,String discode,Stringtermid)",e);
				throw new ManagerException(e);
			}
			//查班级是否被评价了
			if(schoolClassidlist.size()>0){
			    //评价数量  
				int appercont=0;
				  //查询班级是有评价
				for(int w=0;w<schoolClassidlist.size();w++){
					   List unmber;
						Map<String,Object>params1=new HashMap<String,Object>();
						 Integer classid=(Integer)schoolClassidlist.get(w);
						params1.put("classid",classid);
						params1.put("discode",discode);
						params1.put("cmis30id",cim);
						params1.put("termid",termid);
						try{
							//调试时候用的接口，没用的时候，可以关闭或者注释掉
							ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findClassAppercount.query");
							    unmber =this.findList("AreaStatExtImpl.findClassAppercount.query", params1, new RowMapper() {
							 	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
										Integer unm=rs.getInt("num");
										return unm;
								}
							});
							
						}catch(Exception e){
							logger.error("findClassAppercount(Integer,Integer,String discode,Stringtermid)",e);
							throw new ManagerException(e);
						}
						if(unmber.size()>0){
							Integer u=(Integer)unmber.get(0);
							if(u>0){
								++appercont;
							}
						}
						
				}
			
				if(0==i){
					sch.setFirstClassCount(schoolClassidlist.size());
					sch.setFirstClassCounted(appercont);
				  }else if(1==i){
						sch.setSecondClassCount(schoolClassidlist.size());
						sch.setSecondClassCounted(appercont);
					}else{
						sch.setThirdClassCount(schoolClassidlist.size());
						sch.setThirdClassCounted(appercont);
					}
			
			}
			
		
		}
		Map<String,Object>params2=new HashMap<String,Object>();
		params2.put("discode",discode);
		params2.put("cmis30id",cim);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findSchoolidName.query");
			List<String> schoolName =this.findList("AreaStatExtImpl.findSchoolidName.query", params2, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						String shoolname=(String)rs.getString("name");
					   return shoolname;
				}
			});
		
			sch.setSchoolname(schoolName.get(0));
		}catch(Exception e){
			logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			throw new ManagerException(e);
		}
		
		
		return sch;
	}
/*------------------------//初中校区评价班级数-------------------------------------------------------------*/   
	//初中校区评价班级数
	@DataSource("read") 
	public SchoolDto czFindClassApper(Integer cim, Integer intyear,
			String levelCode, String discode, String termid) {
		    //查询本校本届所有的班级
				SchoolDto sch=new SchoolDto();
				for(int i=0;i<4;i++){
					List schoolClassidlist;
				    intyear =intyear+1;  	 
					Map<String,Object>params=new HashMap<String,Object>();
					params.put("graduateyear",intyear);
					params.put("discode",discode);
					params.put("levelCode",levelCode);
					params.put("cmis30id",cim);
					try{
						   ISqlElement sqlElement=this.processSql(params,"AreaStatExtImpl.findClassApper.query");
						   schoolClassidlist =this.findList("AreaStatExtImpl.findClassApper.query", params, new RowMapper() {
							public Object mapRow(ResultSet rs, int arg1) throws SQLException {
									Integer classid=rs.getInt("classid");
									return classid;
							}
						});
						
					}catch(Exception e){
						logger.error("findClassApper(Integer,Integer,String discode,Stringtermid)",e);
						throw new ManagerException(e);
					}
					//查班级是否被评价了
					if(schoolClassidlist.size()>0){
					    //评价数量  
						int appercont=0;
						  //查询班级是有评价
						for(int w=0;w<schoolClassidlist.size();w++){
							   List unmber;
								Map<String,Object>params1=new HashMap<String,Object>();
								Integer classid=(Integer)schoolClassidlist.get(w);
								params1.put("classid",classid);
								params1.put("discode",discode);
								params1.put("cmis30id",cim);
								params1.put("termid",termid);
								try{
									//调试时候用的接口，没用的时候，可以关闭或者注释掉
									ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.CzfindClassAppercount.query");
									    unmber =this.findList("AreaStatExtImpl.CzfindClassAppercount.query", params1, new RowMapper() {
									 	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
												Integer unm=rs.getInt("num");
												return unm;
										}
									});
									
								}catch(Exception e){
									logger.error("findClassAppercount(Integer,Integer,String discode,Stringtermid)",e);
									throw new ManagerException(e);
								}
								if(unmber.size()>0){
									Integer u=(Integer)unmber.get(0);
									if(u>0){
										++appercont;
									}
								}
								
						}
					
						if(0==i){
							sch.setFirstClassCount(schoolClassidlist.size());
							sch.setFirstClassCounted(appercont);
						  }else if(1==i){
								sch.setSecondClassCount(schoolClassidlist.size());
								sch.setSecondClassCounted(appercont);
							}else if(2==i){
								sch.setThirdClassCount(schoolClassidlist.size());
								sch.setThirdClassCounted(appercont);
							}else {
								 sch.setFourthClassCount(schoolClassidlist.size());
								 sch.setSecondClassCounted(appercont);
							}
					
					}
					
				
				}
				Map<String,Object>params2=new HashMap<String,Object>();
				params2.put("discode",discode);
				params2.put("cmis30id",cim);
				try{
					//调试时候用的接口，没用的时候，可以关闭或者注释掉
					ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findSchoolidName.query");
					List<String> schoolName =this.findList("AreaStatExtImpl.findSchoolidName.query", params2, new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1) throws SQLException {
								String shoolname=(String)rs.getString("name");
							   return shoolname;
						}
					});
				
					sch.setSchoolname(schoolName.get(0));
				}catch(Exception e){
					logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
					throw new ManagerException(e);
				}
				
				
				return sch;
	}
	 //高中课程评价
	@DataSource("read") 
	public FistGzTopicDto gzFindTopicApper(Integer cim, String levelCode,
			String discode, String topic, String greed, Integer graduateyear,
			Integer intyear) {
		FistGzTopicDto FGT=new FistGzTopicDto();
		Integer studertcount=0;
		//届为全部是  
		  if(0==0){
			    //科目为全部是
          	if("全部".equals(topic)){
          		 //学年为全部 
          		if("0".equals(greed)){
          			
          			/*----------------------------- 叁-------------------------------------*/
          			List<Integer>classidlist; 
          		  //查询最高年级  
          			Map<String,Object>params1=new HashMap<String,Object>();
          			params1.put("discode",discode);
          			params1.put("cmis30id",cim);
          			params1.put("graduateyear",intyear);
          			try{
          				 //查询当前班级学生数
          				ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findStudentCount.query");
          				 classidlist =this.findList("AreaStatExtImpl.findStudentCount.query", params1, new RowMapper() {
          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
          							Integer st=rs.getInt("num");
          						   return st;
          					}
          				});
          			}catch(Exception e){
          				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
          				throw new ManagerException(e);
          			}
          			 //查询最高年级  各科目评价  
          			GreedDto greedDto3=new GreedDto();
          			for(int w=0;w<3;w++){
          				List<ListTopicBenDto> listTopic;
	          				    Map<String,Object>params2=new HashMap<String,Object>();
			          			params2.put("discode",discode);
			          			params2.put("cmis30id",cim);
			          			params2.put("graduateyear",intyear);
			          			if(w==0){
				          			params2.put("semesterid1",11);
				          			params2.put("semesterid1",12);
			          			}else if(w==1){
			          				params2.put("semesterid1",21);
				          			params2.put("semesterid1",22);
			          			}else{
			          				params2.put("semesterid1",31);
				          			params2.put("semesterid1",32);
			          			}
			          			try{
			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
			          				ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findTopicApper.query");
			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params2, new RowMapper() {
			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
			          						listTopicBenDto.setSubject(rs.getString("subject"));
			          						return listTopicBenDto;
			          					}
			          				});
			          			}catch(Exception e){
			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			          				throw new ManagerException(e);
			          			}
			          			TopicStatDto top =new TopicStatDto(); 
			          			if(0==w){
	          						 top.setGreed("高一");
	          					 }else if(1==w){
	          						top.setGreed("高二");
	          					 }else{
	          						top.setGreed("高三");
	          					 }
	          			        for(int z=0;z<listTopic.size();z++)
	          			        {
					          	 String ss=listTopic.get(z).getSubject();	
					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
					          		 top.setChemistrycount(classidlist.get(0));//总人数
					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
					          			//private long    Chineseper;
					          		 }else{  
					          			top.setChemistrycounted(0);
					          		 }
					          		
					          		
			          			    }else if("英语".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    Englishper;
						          		 }else{  
						          			top.setEnglishcounted(0);
						          		 }
			          			    }else if("数学".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    arithper;
						          		 }else{  
						          			top.setArithcounted(0);
						          		 }
						          	
			          			    }/*else if("思想政治".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			  	//思想政治
						          	    private Integer governmentcount;
						          		private Integer governmentcounted;
						          		private Integer governmentgross;
						          		private long    governmentper;
						          	
			          			    }else if("历史".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			 	 //历史
						          		 private Integer historycount;
						          		 private Integer historycounted;
						          		 private Integer historygross;
						          		 private long    historyper;
						          	
			          			    }else if("地理".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          				  //地理
						          		 private Integer geographycount;
						          		 private Integer geographycounted;
						          		 private Integer geographyross;
						          		 private long    geographyper;	 
						          	  
			          			    }else if("物理".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			      //物理
						          		 private Integer physicscount;
						          		 private Integer physicscounted;
						          		 private Integer physicsross;
						          		 private long    physicsper;	 	 
						          		
			          			    }else if("化学".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			  	//化学    
						          		private Integer chemistrycount;
						          		private Integer chemistrycounted;
						          		private Integer chemistryross;
						          		private long    chemistryper;
						          	
			          			    }else if("生物".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			 	 //生物
						          		private Integer biologycount;
						          		private Integer biologycounted;
						          		private Integer biologyross;
						          		private long    biologyper;
						          	
			          			    }else if("信息技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			  	//信息技术
						          		private Integer infocount;
						          		private Integer infocounted;
						          		private Integer infoross;
						          		private long    infoper;
						          	  
			          			    }else if("通用技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			      //通用技术
						          		private Integer currencycount;
						          		private Integer currencycounted;
						          		private Integer currencyross;
						          		private long    currencyper;
						          	
			          			    }else if("音乐".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          			 	 //音乐
						          		private Integer musiccount;
						          		private Integer musiccounted;
						          		private Integer musicross;
						          		private long    musicper;
						          
			          			    }else if("美术".equals(ss)){
			          			    	top.setChemistrycount(classidlist.get(0));//总人数
			          					 //音乐
						          		private Integer artcount;
						          		private Integer artcounted;
						          		private Integer artcross;
						          		private long    artcper;
						          	
			          			    }*/
	          			        
					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
					        	//private  String  greedName;
					        	//private  String schoolName;
					        	
	          			        }
	          			      greedDto3.getList().add(top);
          			}
     			      greedDto3.setGreedName(intyear);
     			      greedDto3.setGreedRow(3);
     			     FGT.getListGreedDto().add(greedDto3);
                  /*====================================== 二 ====================================*/
     			    GreedDto greedDto2=new GreedDto();
     			     List<Integer>classidlist1; 
          			   intyear=intyear+1;
            			Map<String,Object>params3=new HashMap<String,Object>();
            			params3.put("discode",discode);
            			params3.put("cmis30id",cim);
            			params3.put("graduateyear",intyear);
            			try{
            				 //查询当前班级学生数
            				ISqlElement sqlElement=this.processSql(params3,"AreaStatExtImpl.findStudentCount.query");
            				 classidlist1 =this.findList("AreaStatExtImpl.findStudentCount.query", params3, new RowMapper() {
            					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            							Integer st=rs.getInt("num");
            						   return st;
            					}
            				});
            			}catch(Exception e){
            				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
            				throw new ManagerException(e);
            			}
          			
          			
          			for(int q=0;q<2;q++){
          				List<ListTopicBenDto> listTopic;
	          				    Map<String,Object>params4=new HashMap<String,Object>();
			          			params4.put("discode",discode);
			          			params4.put("cmis30id",cim);
			          			params4.put("graduateyear",intyear);
			          			/*params2.put("subject",subject);*/
			          			if(q==0){
				          			params4.put("semesterid1",11);
				          			params4.put("semesterid1",12);
			          			}else{
			          				params4.put("semesterid1",21);
				          			params4.put("semesterid1",22);
			          			}
			          			try{
			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
			          				ISqlElement sqlElement=this.processSql(params4,"AreaStatExtImpl.findTopicApper.query");
			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params4, new RowMapper() {
			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
			          						listTopicBenDto.setSubject(rs.getString("subject"));
			          						return listTopicBenDto;
			          					}
			          				});
			          			}catch(Exception e){
			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			          				throw new ManagerException(e);
			          			}
			          			 TopicStatDto top =new TopicStatDto(); 
			          			 if(0==q){
	          						 top.setGreed("高一");
	          					 }else{
	          						top.setGreed("高二");
	          					 }
	          			        for(int z=0;z<listTopic.size();z++)
	          			        {
	          			         
					          	 String ss=listTopic.get(z).getSubject();	
	          					
					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
					          		 top.setChemistrycount(classidlist1.get(0));//总人数
					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
					          			//private long    Chineseper;
					          		 }else{  
					          			top.setChemistrycounted(0);
					          		 }
					          		
					          		
			          			    }else if("英语".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    Englishper;
						          		 }else{  
						          			top.setEnglishcounted(0);
						          		 }
			          			    }else if("数学".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    arithper;
						          		 }else{  
						          			top.setArithcounted(0);
						          		 }
						          	
			          			    }/*else if("思想政治".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			  	//思想政治
						          	    private Integer governmentcount;
						          		private Integer governmentcounted;
						          		private Integer governmentgross;
						          		private long    governmentper;
						          	
			          			    }else if("历史".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			 	 //历史
						          		 private Integer historycount;
						          		 private Integer historycounted;
						          		 private Integer historygross;
						          		 private long    historyper;
						          	
			          			    }else if("地理".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          				  //地理
						          		 private Integer geographycount;
						          		 private Integer geographycounted;
						          		 private Integer geographyross;
						          		 private long    geographyper;	 
						          	  
			          			    }else if("物理".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			      //物理
						          		 private Integer physicscount;
						          		 private Integer physicscounted;
						          		 private Integer physicsross;
						          		 private long    physicsper;	 	 
						          		
			          			    }else if("化学".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			  	//化学    
						          		private Integer chemistrycount;
						          		private Integer chemistrycounted;
						          		private Integer chemistryross;
						          		private long    chemistryper;
						          	
			          			    }else if("生物".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			 	 //生物
						          		private Integer biologycount;
						          		private Integer biologycounted;
						          		private Integer biologyross;
						          		private long    biologyper;
						          	
			          			    }else if("信息技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			  	//信息技术
						          		private Integer infocount;
						          		private Integer infocounted;
						          		private Integer infoross;
						          		private long    infoper;
						          	  
			          			    }else if("通用技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			      //通用技术
						          		private Integer currencycount;
						          		private Integer currencycounted;
						          		private Integer currencyross;
						          		private long    currencyper;
						          	
			          			    }else if("音乐".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          			 	 //音乐
						          		private Integer musiccount;
						          		private Integer musiccounted;
						          		private Integer musicross;
						          		private long    musicper;
						          
			          			    }else if("美术".equals(ss)){
			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
			          					 //音乐
						          		private Integer artcount;
						          		private Integer artcounted;
						          		private Integer artcross;
						          		private long    artcper;
						          	
			          			    }*/
	          			        
					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
					        	//private  String  greedName;
					        	//private  String schoolName;
					        	
	          			        }
	          			      greedDto2.getList().add(top);
	          			      	
	          			/*}*/
          			}
          			  greedDto2.setGreedName(intyear);
          		
    			      greedDto2.setGreedRow(2);
    			     FGT.getListGreedDto().add(greedDto2);
          			
          		    /*====================================== 壹====================================*/
    			     GreedDto greedDto1=new GreedDto();
    			     List<Integer>classidlist2; 
            		  //查询最高年级  
          			    intyear=intyear+1;
            			Map<String,Object>params9=new HashMap<String,Object>();
            			params9.put("discode",discode);
            			params9.put("cmis30id",cim);
            			params9.put("graduateyear",intyear);
            			try{
            				 //查询当前班级学生数
            				ISqlElement sqlElement=this.processSql(params9,"AreaStatExtImpl.findStudentCount.query");
            				 classidlist2 =this.findList("AreaStatExtImpl.findStudentCount.query", params9, new RowMapper() {
            					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            							Integer st=rs.getInt("num");
            						   return st;
            					}
            				});
            			}catch(Exception e){
            				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
            				throw new ManagerException(e);
            			}
          			
          			
          			
          				List<ListTopicBenDto> listTopic;
	          			/*for(int i=0;i<topicList.size();i++){*/
			          			/*String subject=topicList.get(i);*/
	          				    Map<String,Object>params4=new HashMap<String,Object>();
			          			params4.put("discode",discode);
			          			params4.put("cmis30id",cim);
			          			params4.put("graduateyear",intyear);
				          		params4.put("semesterid1",11);
				          		params4.put("semesterid1",12);
			          			try{
			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
			          				ISqlElement sqlElement=this.processSql(params4,"AreaStatExtImpl.findTopicApper.query");
			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params4, new RowMapper() {
			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
			          						listTopicBenDto.setSubject(rs.getString("subject"));
			          						return listTopicBenDto;
			          					}
			          				});
			          			}catch(Exception e){
			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			          				throw new ManagerException(e);
			          			}
			          			 TopicStatDto top =new TopicStatDto(); 
	          				     top.setGreed("高一");
	          			        for(int z=0;z<listTopic.size();z++)
	          			        {
	          			         
					          	 String ss=listTopic.get(z).getSubject();	
	          					
					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
					          		 top.setChemistrycount(classidlist2.get(0));//总人数
					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
					          			//private long    Chineseper;
					          		 }else{  
					          			top.setChemistrycounted(0);
					          		 }
					          		
					          		
			          			    }else if("英语".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    Englishper;
						          		 }else{  
						          			top.setEnglishcounted(0);
						          		 }
			          			    }else if("数学".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
						          			//private long    arithper;
						          		 }else{  
						          			top.setArithcounted(0);
						          		 }
						          	
			          			    }/*else if("思想政治".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			  	//思想政治
						          	    private Integer governmentcount;
						          		private Integer governmentcounted;
						          		private Integer governmentgross;
						          		private long    governmentper;
						          	
			          			    }else if("历史".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			 	 //历史
						          		 private Integer historycount;
						          		 private Integer historycounted;
						          		 private Integer historygross;
						          		 private long    historyper;
						          	
			          			    }else if("地理".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          				  //地理
						          		 private Integer geographycount;
						          		 private Integer geographycounted;
						          		 private Integer geographyross;
						          		 private long    geographyper;	 
						          	  
			          			    }else if("物理".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			      //物理
						          		 private Integer physicscount;
						          		 private Integer physicscounted;
						          		 private Integer physicsross;
						          		 private long    physicsper;	 	 
						          		
			          			    }else if("化学".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			  	//化学    
						          		private Integer chemistrycount;
						          		private Integer chemistrycounted;
						          		private Integer chemistryross;
						          		private long    chemistryper;
						          	
			          			    }else if("生物".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			 	 //生物
						          		private Integer biologycount;
						          		private Integer biologycounted;
						          		private Integer biologyross;
						          		private long    biologyper;
						          	
			          			    }else if("信息技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			  	//信息技术
						          		private Integer infocount;
						          		private Integer infocounted;
						          		private Integer infoross;
						          		private long    infoper;
						          	  
			          			    }else if("通用技术".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			      //通用技术
						          		private Integer currencycount;
						          		private Integer currencycounted;
						          		private Integer currencyross;
						          		private long    currencyper;
						          	
			          			    }else if("音乐".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          			 	 //音乐
						          		private Integer musiccount;
						          		private Integer musiccounted;
						          		private Integer musicross;
						          		private long    musicper;
						          
			          			    }else if("美术".equals(ss)){
			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
			          					 //音乐
						          		private Integer artcount;
						          		private Integer artcounted;
						          		private Integer artcross;
						          		private long    artcper;
						          	
			          			    }*/
	          			        
					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
					        	//private  String  greedName;
					        	//private  String schoolName;
					        	
	          			       
	          			     	
	          			/*}*/
          			      }
	          			      greedDto1.getList().add(top);
	          			      greedDto1.setGreedName(intyear);
	         			      greedDto1.setGreedRow(1);
	         			      FGT.getListGreedDto().add(greedDto1);
	         			      FGT.setSchoolRow(6);
          			
          		}else{
          			//有具体学年
          			//如果选择的是高一 
          			if("1".equals(greed)){
          				List<Integer>classidlist; 
                		  //查询最高年级  
                			Map<String,Object>params1=new HashMap<String,Object>();
                			params1.put("discode",discode);
                			params1.put("cmis30id",cim);
                			params1.put("graduateyear",intyear);
                			try{
                				 //查询当前班级学生数
                				ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findStudentCount.query");
                				 classidlist =this.findList("AreaStatExtImpl.findStudentCount.query", params1, new RowMapper() {
                					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                							Integer st=rs.getInt("num");
                						   return st;
                					}
                				});
                			}catch(Exception e){
                				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
                				throw new ManagerException(e);
                			}
                			 //查询最高年级  各科目评价  
                			GreedDto greedDto3=new GreedDto();
                				List<ListTopicBenDto> listTopic;
      	          				    Map<String,Object>params2=new HashMap<String,Object>();
      			          			params2.put("discode",discode);
      			          			params2.put("cmis30id",cim);
      			          			params2.put("graduateyear",intyear);
      				          		params2.put("semesterid1",11);
      				          		params2.put("semesterid1",12);
      			          			try{
      			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
      			          				ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findTopicApper.query");
      			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params2, new RowMapper() {
      			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
      			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
      			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
      			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
      			          						listTopicBenDto.setSubject(rs.getString("subject"));
      			          						return listTopicBenDto;
      			          					}
      			          				});
      			          			}catch(Exception e){
      			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
      			          				throw new ManagerException(e);
      			          			}
      			          			TopicStatDto top =new TopicStatDto(); 
      			          			
      	          						 top.setGreed("高一");
      	          					
      	          			        for(int z=0;z<listTopic.size();z++)
      	          			        {
      					          	 String ss=listTopic.get(z).getSubject();	
      					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
      					          		 top.setChemistrycount(classidlist.get(0));//总人数
      					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
      					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
      					          			//private long    Chineseper;
      					          		 }else{  
      					          			top.setChemistrycounted(0);
      					          		 }
      					          		
      					          		
      			          			    }else if("英语".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
      			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
      						          			//private long    Englishper;
      						          		 }else{  
      						          			top.setEnglishcounted(0);
      						          		 }
      			          			    }else if("数学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
      			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
      						          			//private long    arithper;
      						          		 }else{  
      						          			top.setArithcounted(0);
      						          		 }
      						          	
      			          			    }/*else if("思想政治".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//思想政治
      						          	    private Integer governmentcount;
      						          		private Integer governmentcounted;
      						          		private Integer governmentgross;
      						          		private long    governmentper;
      						          	
      			          			    }else if("历史".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //历史
      						          		 private Integer historycount;
      						          		 private Integer historycounted;
      						          		 private Integer historygross;
      						          		 private long    historyper;
      						          	
      			          			    }else if("地理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          				  //地理
      						          		 private Integer geographycount;
      						          		 private Integer geographycounted;
      						          		 private Integer geographyross;
      						          		 private long    geographyper;	 
      						          	  
      			          			    }else if("物理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			      //物理
      						          		 private Integer physicscount;
      						          		 private Integer physicscounted;
      						          		 private Integer physicsross;
      						          		 private long    physicsper;	 	 
      						          		
      			          			    }else if("化学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//化学    
      						          		private Integer chemistrycount;
      						          		private Integer chemistrycounted;
      						          		private Integer chemistryross;
      						          		private long    chemistryper;
      						          	
      			          			    }else if("生物".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //生物
      						          		private Integer biologycount;
      						          		private Integer biologycounted;
      						          		private Integer biologyross;
      						          		private long    biologyper;
      						          	
      			          			    }else if("信息技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//信息技术
      						          		private Integer infocount;
      						          		private Integer infocounted;
      						          		private Integer infoross;
      						          		private long    infoper;
      						          	  
      			          			    }else if("通用技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			      //通用技术
      						          		private Integer currencycount;
      						          		private Integer currencycounted;
      						          		private Integer currencyross;
      						          		private long    currencyper;
      						          	
      			          			    }else if("音乐".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //音乐
      						          		private Integer musiccount;
      						          		private Integer musiccounted;
      						          		private Integer musicross;
      						          		private long    musicper;
      						          
      			          			    }else if("美术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          					 //音乐
      						          		private Integer artcount;
      						          		private Integer artcounted;
      						          		private Integer artcross;
      						          		private long    artcper;
      						          	
      			          			    }*/
      	          			        
      					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
      					        	//private  String  greedName;
      					        	//private  String schoolName;
      					        	
      	          			        }
      	          			      greedDto3.getList().add(top);
      	          			      greedDto3.setGreedName(intyear);
      	          			      greedDto3.setGreedRow(1);
      	          			      FGT.getListGreedDto().add(greedDto3);
                        /*====================================== 二 ====================================*/
           			    GreedDto greedDto2=new GreedDto();
           			     List<Integer>classidlist1; 
                			   intyear=intyear+1;
                  			Map<String,Object>params3=new HashMap<String,Object>();
                  			params3.put("discode",discode);
                  			params3.put("cmis30id",cim);
                  			params3.put("graduateyear",intyear);
                  			try{
                  				 //查询当前班级学生数
                  				ISqlElement sqlElement=this.processSql(params3,"AreaStatExtImpl.findStudentCount.query");
                  				 classidlist1 =this.findList("AreaStatExtImpl.findStudentCount.query", params3, new RowMapper() {
                  					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                  							Integer st=rs.getInt("num");
                  						   return st;
                  					}
                  				});
                  			}catch(Exception e){
                  				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
                  				throw new ManagerException(e);
                  			}
                			
                			
                		
                				List<ListTopicBenDto> listTopic_1;
      	          				    Map<String,Object>params4=new HashMap<String,Object>();
      			          			params4.put("discode",discode);
      			          			params4.put("cmis30id",cim);
      			          			params4.put("graduateyear",intyear);
      				          		params4.put("semesterid1",11);
      				          		params4.put("semesterid1",12);
      			          			try{
      			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
      			          				ISqlElement sqlElement=this.processSql(params4,"AreaStatExtImpl.findTopicApper.query");
      			          				listTopic_1 =this.findList("AreaStatExtImpl.findTopicApper.query", params4, new RowMapper() {
      			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
      			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
      			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
      			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
      			          						listTopicBenDto.setSubject(rs.getString("subject"));
      			          						return listTopicBenDto;
      			          					}
      			          				});
      			          			}catch(Exception e){
      			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
      			          				throw new ManagerException(e);
      			          			}
      			          			 TopicStatDto tops =new TopicStatDto(); 
      			          			 
      	          						 top.setGreed("高一");
      	          					
      	          			        for(int z=0;z<listTopic_1.size();z++)
      	          			        {
      	          			         
      					          	 String ss=listTopic_1.get(z).getSubject();	
      	          					
      					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
      					          		 tops.setChemistrycount(classidlist1.get(0));//总人数
      					          		 tops.setChinesegross(listTopic_1.get(z).getTopicApperSum());
      					          		 if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
      					          			 tops.setChemistrycounted(listTopic_1.get(z).getStudentApperedTopiccount());
      					          			//private long    Chineseper;
      					          		 }else{  
      					          			tops.setChemistrycounted(0);
      					          		 }
      					          		
      					          		
      			          			    }else if("英语".equals(ss)){
      			          			    	tops.setChemistrycount(classidlist1.get(0));//总人数
      			          			        tops.setEnglishgross(listTopic_1.get(z).getTopicApperSum());
      			          			    	 if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
      						          			 tops.setEnglishcounted(listTopic_1.get(z).getStudentApperedTopiccount());
      						          			//private long    Englishper;
      						          		 }else{  
      						          			tops.setEnglishcounted(0);
      						          		 }
      			          			    }else if("数学".equals(ss)){
      			          			    	tops.setChemistrycount(classidlist1.get(0));//总人数
      			          			    	tops.setArithgross(listTopic_1.get(z).getTopicApperSum());
      			          			    	if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
      						          			 tops.setArithcounted(listTopic_1.get(z).getStudentApperedTopiccount());
      						          			//private long    arithper;
      						          		 }else{  
      						          			tops.setArithcounted(0);
      						          		 }
      						          	
      			          			    }/*else if("思想政治".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			  	//思想政治
      						          	    private Integer governmentcount;
      						          		private Integer governmentcounted;
      						          		private Integer governmentgross;
      						          		private long    governmentper;
      						          	
      			          			    }else if("历史".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			 	 //历史
      						          		 private Integer historycount;
      						          		 private Integer historycounted;
      						          		 private Integer historygross;
      						          		 private long    historyper;
      						          	
      			          			    }else if("地理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          				  //地理
      						          		 private Integer geographycount;
      						          		 private Integer geographycounted;
      						          		 private Integer geographyross;
      						          		 private long    geographyper;	 
      						          	  
      			          			    }else if("物理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			      //物理
      						          		 private Integer physicscount;
      						          		 private Integer physicscounted;
      						          		 private Integer physicsross;
      						          		 private long    physicsper;	 	 
      						          		
      			          			    }else if("化学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			  	//化学    
      						          		private Integer chemistrycount;
      						          		private Integer chemistrycounted;
      						          		private Integer chemistryross;
      						          		private long    chemistryper;
      						          	
      			          			    }else if("生物".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			 	 //生物
      						          		private Integer biologycount;
      						          		private Integer biologycounted;
      						          		private Integer biologyross;
      						          		private long    biologyper;
      						          	
      			          			    }else if("信息技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			  	//信息技术
      						          		private Integer infocount;
      						          		private Integer infocounted;
      						          		private Integer infoross;
      						          		private long    infoper;
      						          	  
      			          			    }else if("通用技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			      //通用技术
      						          		private Integer currencycount;
      						          		private Integer currencycounted;
      						          		private Integer currencyross;
      						          		private long    currencyper;
      						          	
      			          			    }else if("音乐".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          			 	 //音乐
      						          		private Integer musiccount;
      						          		private Integer musiccounted;
      						          		private Integer musicross;
      						          		private long    musicper;
      						          
      			          			    }else if("美术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
      			          					 //音乐
      						          		private Integer artcount;
      						          		private Integer artcounted;
      						          		private Integer artcross;
      						          		private long    artcper;
      						          	
      			          			    }*/
      	          			        
      					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
      					        	//private  String  greedName;
      					        	//private  String schoolName;
      					        	
      	          			        }
      	          			      greedDto2.getList().add(tops);
      	          			      	
      	          			
                			  greedDto2.setGreedName(intyear);
                		
          			      greedDto2.setGreedRow(1);
          			     FGT.getListGreedDto().add(greedDto2);
                			
                		    /*====================================== 壹====================================*/
          			     GreedDto greedDto1=new GreedDto();
          			     List<Integer>classidlist2; 
                  		  //查询最高年级  
                			    intyear=intyear+1;
                  			Map<String,Object>params9=new HashMap<String,Object>();
                  			params9.put("discode",discode);
                  			params9.put("cmis30id",cim);
                  			params9.put("graduateyear",intyear);
                  			try{
                  				 //查询当前班级学生数
                  				ISqlElement sqlElement=this.processSql(params9,"AreaStatExtImpl.findStudentCount.query");
                  				 classidlist2 =this.findList("AreaStatExtImpl.findStudentCount.query", params9, new RowMapper() {
                  					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                  							Integer st=rs.getInt("num");
                  						   return st;
                  					}
                  				});
                  			}catch(Exception e){
                  				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
                  				throw new ManagerException(e);
                  			}
                			
                			
                			
                				List<ListTopicBenDto> listTopic_s;
      	          			/*for(int i=0;i<topicList.size();i++){*/
      			          			/*String subject=topicList.get(i);*/
      	          				    Map<String,Object>params_4=new HashMap<String,Object>();
      			          			params_4.put("discode",discode);
      			          			params_4.put("cmis30id",cim);
      			          			params_4.put("graduateyear",intyear);
      				          		params_4.put("semesterid1",11);
      				          		params_4.put("semesterid1",12);
      			          			try{
      			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
      			          				ISqlElement sqlElement=this.processSql(params_4,"AreaStatExtImpl.findTopicApper.query");
      			          				listTopic_s =this.findList("AreaStatExtImpl.findTopicApper.query", params_4, new RowMapper() {
      			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
      			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
      			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
      			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
      			          						listTopicBenDto.setSubject(rs.getString("subject"));
      			          						return listTopicBenDto;
      			          					}
      			          				});
      			          			}catch(Exception e){
      			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
      			          				throw new ManagerException(e);
      			          			}
      			          			 TopicStatDto top_s =new TopicStatDto(); 
      	          				     top.setGreed("高一");
      	          			        for(int z=0;z<listTopic_s.size();z++)
      	          			        {
      	          			         
      					          	 String ss=listTopic_s.get(z).getSubject();	
      	          					
      					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
      					          		 top_s.setChemistrycount(classidlist2.get(0));//总人数
      					          		 top_s.setChinesegross(listTopic_s.get(z).getTopicApperSum());
      					          		 if(0 !=listTopic_s.get(z).getStudentApperedTopiccount()){
      					          			 top_s.setChemistrycounted(listTopic_s.get(z).getStudentApperedTopiccount());
      					          			//private long    Chineseper;
      					          		 }else{  
      					          			top_s.setChemistrycounted(0);
      					          		 }
      					          		
      					          		
      			          			    }else if("英语".equals(ss)){
      			          			    	top_s.setChemistrycount(classidlist2.get(0));//总人数
      			          			        top_s.setEnglishgross(listTopic_s.get(z).getTopicApperSum());
      			          			    	 if(0 !=listTopic_s.get(z).getStudentApperedTopiccount()){
      						          			 top_s.setEnglishcounted(listTopic_s.get(z).getStudentApperedTopiccount());
      						          			//private long    Englishper;
      						          		 }else{  
      						          			top_s.setEnglishcounted(0);
      						          		 }
      			          			    }else if("数学".equals(ss)){
      			          			    	top_s.setChemistrycount(classidlist2.get(0));//总人数
      			          			    	top_s.setArithgross(listTopic_s.get(z).getTopicApperSum());
      			          			    	if(0 !=listTopic_s.get(z).getStudentApperedTopiccount()){
      						          			 top_s.setArithcounted(listTopic_s.get(z).getStudentApperedTopiccount());
      						          			//private long    arithper;
      						          		 }else{  
      						          			top_s.setArithcounted(0);
      						          		 }
      						          	
      			          			    }/*else if("思想政治".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			  	//思想政治
      						          	    private Integer governmentcount;
      						          		private Integer governmentcounted;
      						          		private Integer governmentgross;
      						          		private long    governmentper;
      						          	
      			          			    }else if("历史".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			 	 //历史
      						          		 private Integer historycount;
      						          		 private Integer historycounted;
      						          		 private Integer historygross;
      						          		 private long    historyper;
      						          	
      			          			    }else if("地理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          				  //地理
      						          		 private Integer geographycount;
      						          		 private Integer geographycounted;
      						          		 private Integer geographyross;
      						          		 private long    geographyper;	 
      						          	  
      			          			    }else if("物理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			      //物理
      						          		 private Integer physicscount;
      						          		 private Integer physicscounted;
      						          		 private Integer physicsross;
      						          		 private long    physicsper;	 	 
      						          		
      			          			    }else if("化学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			  	//化学    
      						          		private Integer chemistrycount;
      						          		private Integer chemistrycounted;
      						          		private Integer chemistryross;
      						          		private long    chemistryper;
      						          	
      			          			    }else if("生物".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			 	 //生物
      						          		private Integer biologycount;
      						          		private Integer biologycounted;
      						          		private Integer biologyross;
      						          		private long    biologyper;
      						          	
      			          			    }else if("信息技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			  	//信息技术
      						          		private Integer infocount;
      						          		private Integer infocounted;
      						          		private Integer infoross;
      						          		private long    infoper;
      						          	  
      			          			    }else if("通用技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			      //通用技术
      						          		private Integer currencycount;
      						          		private Integer currencycounted;
      						          		private Integer currencyross;
      						          		private long    currencyper;
      						          	
      			          			    }else if("音乐".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          			 	 //音乐
      						          		private Integer musiccount;
      						          		private Integer musiccounted;
      						          		private Integer musicross;
      						          		private long    musicper;
      						          
      			          			    }else if("美术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist2.get(0));//总人数
      			          					 //音乐
      						          		private Integer artcount;
      						          		private Integer artcounted;
      						          		private Integer artcross;
      						          		private long    artcper;
      						          	
      			          			    }*/
                			   }
      	          			      greedDto1.getList().add(top_s);
      	          			      greedDto1.setGreedName(intyear);
      	         			      greedDto3.setGreedRow(1);
      	         			      FGT.getListGreedDto().add(greedDto1);
      	         			      FGT.setSchoolRow(3);
                			
          				
          			}else if("2".equals(greed)){
          				List<Integer>classidlist; 
              		  //查询最高年级  
              			Map<String,Object>params1=new HashMap<String,Object>();
              			params1.put("discode",discode);
              			params1.put("cmis30id",cim);
              			params1.put("graduateyear",intyear);
              			try{
              				 //查询当前班级学生数
              				ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findStudentCount.query");
              				 classidlist =this.findList("AreaStatExtImpl.findStudentCount.query", params1, new RowMapper() {
              					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
              							Integer st=rs.getInt("num");
              						   return st;
              					}
              				});
              			}catch(Exception e){
              				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
              				throw new ManagerException(e);
              			}
              			 //查询最高年级  各科目评价  
              			GreedDto greedDto3=new GreedDto();
              				List<ListTopicBenDto> listTopic;
    	          				    Map<String,Object>params2=new HashMap<String,Object>();
    			          			params2.put("discode",discode);
    			          			params2.put("cmis30id",cim);
    			          			params2.put("graduateyear",intyear);
    				          		params2.put("semesterid1",21);
    				          		params2.put("semesterid1",22);
    			          			try{
    			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
    			          				ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findTopicApper.query");
    			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params2, new RowMapper() {
    			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
    			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
    			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
    			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
    			          						listTopicBenDto.setSubject(rs.getString("subject"));
    			          						return listTopicBenDto;
    			          					}
    			          				});
    			          			}catch(Exception e){
    			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
    			          				throw new ManagerException(e);
    			          			}
    			          			TopicStatDto top =new TopicStatDto(); 
    			          			
    	          						 top.setGreed("高二");
    	          					
    	          			        for(int z=0;z<listTopic.size();z++)
    	          			        {
    					          	 String ss=listTopic.get(z).getSubject();	
    					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
    					          		 top.setChemistrycount(classidlist.get(0));//总人数
    					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
    					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
    					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
    					          			//private long    Chineseper;
    					          		 }else{  
    					          			top.setChemistrycounted(0);
    					          		 }
    					          		
    					          		
    			          			    }else if("英语".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
    			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
    						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
    						          			//private long    Englishper;
    						          		 }else{  
    						          			top.setEnglishcounted(0);
    						          		 }
    			          			    }else if("数学".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
    			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
    						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
    						          			//private long    arithper;
    						          		 }else{  
    						          			top.setArithcounted(0);
    						          		 }
    						          	
    			          			    }/*else if("思想政治".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			  	//思想政治
    						          	    private Integer governmentcount;
    						          		private Integer governmentcounted;
    						          		private Integer governmentgross;
    						          		private long    governmentper;
    						          	
    			          			    }else if("历史".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			 	 //历史
    						          		 private Integer historycount;
    						          		 private Integer historycounted;
    						          		 private Integer historygross;
    						          		 private long    historyper;
    						          	
    			          			    }else if("地理".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          				  //地理
    						          		 private Integer geographycount;
    						          		 private Integer geographycounted;
    						          		 private Integer geographyross;
    						          		 private long    geographyper;	 
    						          	  
    			          			    }else if("物理".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			      //物理
    						          		 private Integer physicscount;
    						          		 private Integer physicscounted;
    						          		 private Integer physicsross;
    						          		 private long    physicsper;	 	 
    						          		
    			          			    }else if("化学".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			  	//化学    
    						          		private Integer chemistrycount;
    						          		private Integer chemistrycounted;
    						          		private Integer chemistryross;
    						          		private long    chemistryper;
    						          	
    			          			    }else if("生物".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			 	 //生物
    						          		private Integer biologycount;
    						          		private Integer biologycounted;
    						          		private Integer biologyross;
    						          		private long    biologyper;
    						          	
    			          			    }else if("信息技术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			  	//信息技术
    						          		private Integer infocount;
    						          		private Integer infocounted;
    						          		private Integer infoross;
    						          		private long    infoper;
    						          	  
    			          			    }else if("通用技术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			      //通用技术
    						          		private Integer currencycount;
    						          		private Integer currencycounted;
    						          		private Integer currencyross;
    						          		private long    currencyper;
    						          	
    			          			    }else if("音乐".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          			 	 //音乐
    						          		private Integer musiccount;
    						          		private Integer musiccounted;
    						          		private Integer musicross;
    						          		private long    musicper;
    						          
    			          			    }else if("美术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist.get(0));//总人数
    			          					 //音乐
    						          		private Integer artcount;
    						          		private Integer artcounted;
    						          		private Integer artcross;
    						          		private long    artcper;
    						          	
    			          			    }*/
    	          			        
    					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
    					        	//private  String  greedName;
    					        	//private  String schoolName;
    					        	
    	          			        }
    	          			      greedDto3.getList().add(top);
    	          			      greedDto3.setGreedName(intyear);
    	          			      greedDto3.setGreedRow(1);
    	          			      FGT.getListGreedDto().add(greedDto3);
                      /*====================================== 二 ====================================*/
         			    GreedDto greedDto2=new GreedDto();
         			     List<Integer>classidlist1; 
              			   intyear=intyear+1;
                			Map<String,Object>params3=new HashMap<String,Object>();
                			params3.put("discode",discode);
                			params3.put("cmis30id",cim);
                			params3.put("graduateyear",intyear);
                			try{
                				 //查询当前班级学生数
                				ISqlElement sqlElement=this.processSql(params3,"AreaStatExtImpl.findStudentCount.query");
                				 classidlist1 =this.findList("AreaStatExtImpl.findStudentCount.query", params3, new RowMapper() {
                					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                							Integer st=rs.getInt("num");
                						   return st;
                					}
                				});
                			}catch(Exception e){
                				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
                				throw new ManagerException(e);
                			}
              			
              			
              		
              				List<ListTopicBenDto> listTopic_1;
    	          				    Map<String,Object>params4=new HashMap<String,Object>();
    			          			params4.put("discode",discode);
    			          			params4.put("cmis30id",cim);
    			          			params4.put("graduateyear",intyear);
    				          		params4.put("semesterid1",21);
    				          		params4.put("semesterid1",22);
    			          			try{
    			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
    			          				ISqlElement sqlElement=this.processSql(params4,"AreaStatExtImpl.findTopicApper.query");
    			          				listTopic_1 =this.findList("AreaStatExtImpl.findTopicApper.query", params4, new RowMapper() {
    			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
    			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
    			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
    			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
    			          						listTopicBenDto.setSubject(rs.getString("subject"));
    			          						return listTopicBenDto;
    			          					}
    			          				});
    			          			}catch(Exception e){
    			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
    			          				throw new ManagerException(e);
    			          			}
    			          			 TopicStatDto tops =new TopicStatDto(); 
    			          			 
    	          						 top.setGreed("高二");
    	          					
    	          			        for(int z=0;z<listTopic_1.size();z++)
    	          			        {
    	          			         
    					          	 String ss=listTopic_1.get(z).getSubject();	
    	          					
    					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
    					          		 tops.setChemistrycount(classidlist1.get(0));//总人数
    					          		 tops.setChinesegross(listTopic_1.get(z).getTopicApperSum());
    					          		 if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
    					          			 tops.setChemistrycounted(listTopic_1.get(z).getStudentApperedTopiccount());
    					          			//private long    Chineseper;
    					          		 }else{  
    					          			tops.setChemistrycounted(0);
    					          		 }
    					          		
    					          		
    			          			    }else if("英语".equals(ss)){
    			          			    	tops.setChemistrycount(classidlist1.get(0));//总人数
    			          			        tops.setEnglishgross(listTopic_1.get(z).getTopicApperSum());
    			          			    	 if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
    						          			 tops.setEnglishcounted(listTopic_1.get(z).getStudentApperedTopiccount());
    						          			//private long    Englishper;
    						          		 }else{  
    						          			tops.setEnglishcounted(0);
    						          		 }
    			          			    }else if("数学".equals(ss)){
    			          			    	tops.setChemistrycount(classidlist1.get(0));//总人数
    			          			    	tops.setArithgross(listTopic_1.get(z).getTopicApperSum());
    			          			    	if(0 !=listTopic_1.get(z).getStudentApperedTopiccount()){
    						          			 tops.setArithcounted(listTopic_1.get(z).getStudentApperedTopiccount());
    						          			//private long    arithper;
    						          		 }else{  
    						          			tops.setArithcounted(0);
    						          		 }
    						          	
    			          			    }/*else if("思想政治".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			  	//思想政治
    						          	    private Integer governmentcount;
    						          		private Integer governmentcounted;
    						          		private Integer governmentgross;
    						          		private long    governmentper;
    						          	
    			          			    }else if("历史".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			 	 //历史
    						          		 private Integer historycount;
    						          		 private Integer historycounted;
    						          		 private Integer historygross;
    						          		 private long    historyper;
    						          	
    			          			    }else if("地理".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          				  //地理
    						          		 private Integer geographycount;
    						          		 private Integer geographycounted;
    						          		 private Integer geographyross;
    						          		 private long    geographyper;	 
    						          	  
    			          			    }else if("物理".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			      //物理
    						          		 private Integer physicscount;
    						          		 private Integer physicscounted;
    						          		 private Integer physicsross;
    						          		 private long    physicsper;	 	 
    						          		
    			          			    }else if("化学".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			  	//化学    
    						          		private Integer chemistrycount;
    						          		private Integer chemistrycounted;
    						          		private Integer chemistryross;
    						          		private long    chemistryper;
    						          	
    			          			    }else if("生物".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			 	 //生物
    						          		private Integer biologycount;
    						          		private Integer biologycounted;
    						          		private Integer biologyross;
    						          		private long    biologyper;
    						          	
    			          			    }else if("信息技术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			  	//信息技术
    						          		private Integer infocount;
    						          		private Integer infocounted;
    						          		private Integer infoross;
    						          		private long    infoper;
    						          	  
    			          			    }else if("通用技术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			      //通用技术
    						          		private Integer currencycount;
    						          		private Integer currencycounted;
    						          		private Integer currencyross;
    						          		private long    currencyper;
    						          	
    			          			    }else if("音乐".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          			 	 //音乐
    						          		private Integer musiccount;
    						          		private Integer musiccounted;
    						          		private Integer musicross;
    						          		private long    musicper;
    						          
    			          			    }else if("美术".equals(ss)){
    			          			    	top.setChemistrycount(classidlist1.get(0));//总人数
    			          					 //音乐
    						          		private Integer artcount;
    						          		private Integer artcounted;
    						          		private Integer artcross;
    						          		private long    artcper;
    						          	
    			          			    }*/
    	          			        
    					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
    					        	//private  String  greedName;
    					        	//private  String schoolName;
    					        	
    	          			        }
    	          			      greedDto2.getList().add(tops);
              			          greedDto2.setGreedName(intyear);
        			             greedDto2.setGreedRow(1);
        			             FGT.getListGreedDto().add(greedDto2);
        			             FGT.setSchoolRow(2);
          			}else{
          				List<Integer>classidlist; 
                		  //查询最高年级  
                			Map<String,Object>params1=new HashMap<String,Object>();
                			params1.put("discode",discode);
                			params1.put("cmis30id",cim);
                			params1.put("graduateyear",intyear);
                			try{
                				 //查询当前班级学生数
                				ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findStudentCount.query");
                				 classidlist =this.findList("AreaStatExtImpl.findStudentCount.query", params1, new RowMapper() {
                					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                							Integer st=rs.getInt("num");
                						   return st;
                					}
                				});
                			}catch(Exception e){
                				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
                				throw new ManagerException(e);
                			}
                			 //查询最高年级  各科目评价  
                			GreedDto greedDto3=new GreedDto();
                				List<ListTopicBenDto> listTopic;
      	          				    Map<String,Object>params2=new HashMap<String,Object>();
      			          			params2.put("discode",discode);
      			          			params2.put("cmis30id",cim);
      			          			params2.put("graduateyear",intyear);
      				          		params2.put("semesterid1",31);
      				          		params2.put("semesterid1",32);
      			          			try{
      			          				//调试时候用的接口，没用的时候，可以关闭或者注释掉
      			          				ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findTopicApper.query");
      			          				listTopic =this.findList("AreaStatExtImpl.findTopicApper.query", params2, new RowMapper() {
      			          					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
      			          						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
      			          						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
      			          						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
      			          						listTopicBenDto.setSubject(rs.getString("subject"));
      			          						return listTopicBenDto;
      			          					}
      			          				});
      			          			}catch(Exception e){
      			          				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
      			          				throw new ManagerException(e);
      			          			}
      			          			TopicStatDto top =new TopicStatDto(); 
      			          			
      	          						 top.setGreed("高三");
      	          					
      	          			        for(int z=0;z<listTopic.size();z++)
      	          			        {
      					          	 String ss=listTopic.get(z).getSubject();	
      					          	 if("语文".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
      					          		 top.setChemistrycount(classidlist.get(0));//总人数
      					          		 top.setChinesegross(listTopic.get(z).getTopicApperSum());
      					          		 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      					          			 top.setChemistrycounted(listTopic.get(z).getStudentApperedTopiccount());
      					          			//private long    Chineseper;
      					          		 }else{  
      					          			top.setChemistrycounted(0);
      					          		 }
      					          		
      					          		
      			          			    }else if("英语".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			        top.setEnglishgross(listTopic.get(z).getTopicApperSum());
      			          			    	 if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      						          			 top.setEnglishcounted(listTopic.get(z).getStudentApperedTopiccount());
      						          			//private long    Englishper;
      						          		 }else{  
      						          			top.setEnglishcounted(0);
      						          		 }
      			          			    }else if("数学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			    	top.setArithgross(listTopic.get(z).getTopicApperSum());
      			          			    	if(0 !=listTopic.get(z).getStudentApperedTopiccount()){
      						          			 top.setArithcounted(listTopic.get(z).getStudentApperedTopiccount());
      						          			//private long    arithper;
      						          		 }else{  
      						          			top.setArithcounted(0);
      						          		 }
      						          	
      			          			    }/*else if("思想政治".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//思想政治
      						          	    private Integer governmentcount;
      						          		private Integer governmentcounted;
      						          		private Integer governmentgross;
      						          		private long    governmentper;
      						          	
      			          			    }else if("历史".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //历史
      						          		 private Integer historycount;
      						          		 private Integer historycounted;
      						          		 private Integer historygross;
      						          		 private long    historyper;
      						          	
      			          			    }else if("地理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          				  //地理
      						          		 private Integer geographycount;
      						          		 private Integer geographycounted;
      						          		 private Integer geographyross;
      						          		 private long    geographyper;	 
      						          	  
      			          			    }else if("物理".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			      //物理
      						          		 private Integer physicscount;
      						          		 private Integer physicscounted;
      						          		 private Integer physicsross;
      						          		 private long    physicsper;	 	 
      						          		
      			          			    }else if("化学".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//化学    
      						          		private Integer chemistrycount;
      						          		private Integer chemistrycounted;
      						          		private Integer chemistryross;
      						          		private long    chemistryper;
      						          	
      			          			    }else if("生物".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //生物
      						          		private Integer biologycount;
      						          		private Integer biologycounted;
      						          		private Integer biologyross;
      						          		private long    biologyper;
      						          	
      			          			    }else if("信息技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			  	//信息技术
      						          		private Integer infocount;
      						          		private Integer infocounted;
      						          		private Integer infoross;
      						          		private long    infoper;
      						          	  
      			          			    }else if("通用技术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			      //通用技术
      						          		private Integer currencycount;
      						          		private Integer currencycounted;
      						          		private Integer currencyross;
      						          		private long    currencyper;
      						          	
      			          			    }else if("音乐".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          			 	 //音乐
      						          		private Integer musiccount;
      						          		private Integer musiccounted;
      						          		private Integer musicross;
      						          		private long    musicper;
      						          
      			          			    }else if("美术".equals(ss)){
      			          			    	top.setChemistrycount(classidlist.get(0));//总人数
      			          					 //音乐
      						          		private Integer artcount;
      						          		private Integer artcounted;
      						          		private Integer artcross;
      						          		private long    artcper;
      						          	
      			          			    }*/
      	          			        
      					          	//private List<TopicStatDto> list=new ArrayList<TopicStatDto>();
      					        	//private  String  greedName;
      					        	//private  String schoolName;
      					        	
      	          			        }
      	          			      greedDto3.getList().add(top);
      	          			      greedDto3.setGreedName(intyear);
      	          			      greedDto3.setGreedRow(1);
      	          			      FGT.getListGreedDto().add(greedDto3);
      	          			      FGT.setSchoolRow(1);
          			}
          			
          		}
          	}else{
          		//有具体科目
          		
          	}
          }else{
          	//有具体届
          }
       
		return FGT;
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String findSchoolName(Integer cim, String discode) {
		 
		  Map<String,Object>params20=new HashMap<String,Object>();
			params20.put("discode",discode);
			params20.put("cmis30id",cim);
			try{
				//调试时候用的接口，没用的时候，可以关闭或者注释掉
				ISqlElement sqlElement=this.processSql(params20,"AreaStatExtImpl.findSchoolidName.query");
				List<String> schoolName =this.findList("AreaStatExtImpl.findSchoolidName.query", params20, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
							String shoolname=(String)rs.getString("name");
						   return shoolname;
					}
				});
			
				return  schoolName.get(0);
			}catch(Exception e){
				logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
				throw new ManagerException(e);
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* <<<<<<<<<<<<<<<----------- //初中-----<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>
*/	
	
	public List<CzGreedLengthDto> findCzAllSchoolid(String schoolName,
			String levelCode, String discode) {
		 if(discode==null){
			 return  null;
		  }
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("schoolName",schoolName);
		params.put("discode",discode);
		params.put("levelCode",levelCode);
		try{
			//调试时候用的接口，没用的时候，可以关闭或者注释掉
			ISqlElement sqlElement=this.processSql(params,"AreaStatExtImpl.findCzAllSchoolid.query");
			List<CzGreedLengthDto> ListCzGreedLengthDt =this.findList("AreaStatExtImpl.findCzAllSchoolid.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					CzGreedLengthDto CzSchoolid=new CzGreedLengthDto();
					CzSchoolid.setSchoolId(rs.getInt("cmis30id"));
					CzSchoolid.setNumber(rs.getInt("numbe"));
					   return CzSchoolid;
				}
			});
			
				return  ListCzGreedLengthDt;
		}catch(Exception e){
			logger.error("findAllSchoolid(likeSchoolName,levelCode,discode)",e);
			throw new ManagerException(e);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*--------------------------------初中课程评价--------------------------------------------------------------------------*/	
	
	
	public FistGzTopicDto czFindTopicApper(Integer cim, String levelCode,
			String discode, String topic, String greed, Integer graduateyear,
			Integer intyear, Integer greedLength) {
		DecimalFormat df = new DecimalFormat("0.0");
	if(greedLength==3){	
		FistGzTopicDto FGT=new FistGzTopicDto();
		//Integer studertcount=0;
		//届为全部是  
		  if(0==graduateyear){
			    //科目为全部是
          	if("0".equals(topic)){
          		 //学年为全部 
          		if("0".equals(greed)){
          /*----------------------------- 叁-------------------------------------*/
          		  //查询最高年级  
          			Map<String,Object> params1 =new HashMap<String,Object>();
          			params1.put("discode",discode);
          			params1.put("cmis30id",cim);
          			params1.put("graduateyear",intyear);
          			List<Integer> classidlist=findSteudentCountSum(params1);
          			 //查询最高年级  各科目评价  
          			GreedDto greedDto3=new GreedDto();
          			for(int w=0;w<3;w++){
	          				    Map<String,Object>params2=new HashMap<String,Object>();
			          			params2.put("discode",discode);
			          			params2.put("cmis30id",cim);
			          			params2.put("graduateyear",intyear);
			          			if(w==0){
			          			    String 	semesterid1=String.valueOf(intyear-2)+"1"; 
			          			    String 	semesterid2=String.valueOf(intyear-2)+"2"; 
				          			params2.put("semesterid1",semesterid1);
				          			params2.put("semesterid2",semesterid2);
			          			}else if(w==1){
			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
			          				params2.put("semesterid1",semesterid1);
				          			params2.put("semesterid2",semesterid2);
			          			}else{
			          				String 	semesterid1=String.valueOf(intyear)+"1"; 
			          			    String 	semesterid2=String.valueOf(intyear)+"2";
			          				params2.put("semesterid1",semesterid1);
				          			params2.put("semesterid2",semesterid2);
			          			}
			          		List<ListTopicBenDto> listTopic=findCzApperSum(params2);
			          			
			          			TopicStatDto top =new TopicStatDto(); 
			          			if(0==w){
	          						 top.setGreed("七年级");
	          					 }else if(1==w){
	          						top.setGreed("八年级");
	          					 }else{
	          						top.setGreed("九年级");
	          					 }
			          			 int qq=listTopic.size();
	          			        for(int z=0;z<qq;z++)
	          			        {  Integer StudenSum= classidlist.get(0);
	          			        	if(null!=StudenSum){
	          			        	  top.setChinesecount(StudenSum);//总人数
	          			        	  String ss=listTopic.get(z).getSubject();
	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
	          			             top= apper(top,ss,apped,appedSum,StudenSum);
	          			            }
	          			        }
	          			      greedDto3.getList().add(top);
          			}
     			      greedDto3.setGreedName(intyear);
     			      greedDto3.setGreedRow(3);
     			     FGT.getListGreedDto().add(greedDto3);
                  /*====================================== 二 ====================================*/
     			    GreedDto greedDto2=new GreedDto();
     			     
          			   intyear=intyear+1;
            			Map<String,Object>params3=new HashMap<String,Object>();
            			params3.put("discode",discode);
            			params3.put("cmis30id",cim);
            			params3.put("graduateyear",intyear);
            			List<Integer> classidlist1=findSteudentCountSum(params3);	
          			for(int q=0;q<2;q++){
          				       
	          				    Map<String,Object>params4=new HashMap<String,Object>();
			          			params4.put("discode",discode);
			          			params4.put("cmis30id",cim);
			          			params4.put("graduateyear",intyear);
			          			/*params2.put("subject",subject);*/
			          			if(q==0){
			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
				          			params4.put("semesterid1",semesterid1);
				          			params4.put("semesterid2",semesterid2);
			          			}else{
			          			    String 	semesterid1=String.valueOf(intyear)+"1"; 
			          			    String 	semesterid2=String.valueOf(intyear)+"2";
			          				params4.put("semesterid1",semesterid1);
				          			params4.put("semesterid2",semesterid2);
			          			} 
			          			List<ListTopicBenDto> listTopic=findCzApperSum(params4);
			          			 TopicStatDto top =new TopicStatDto(); 
			          			 if(0==q){
	          						 top.setGreed("七年级");
	          					 }else{
	          						top.setGreed("八年级");
	          					 }
			          			  int ww=listTopic.size();
			          			 for(int z=0;z<ww;z++)
		          			        {  Integer StudenSum= classidlist1.get(0);
		          			        	if(null!=StudenSum){
		          			        	  top.setChinesecount(StudenSum);//总人数
		          			        	  String ss=listTopic.get(z).getSubject();
		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
		          			             top= apper(top,ss,apped,appedSum,StudenSum);
		          			            }
		          			        }
	          			      greedDto2.getList().add(top);
	          			      	
	          			/*}*/
          			}
          			  greedDto2.setGreedName(intyear);
          		
    			      greedDto2.setGreedRow(2);
    			     FGT.getListGreedDto().add(greedDto2);
          			
          		    /*====================================== 壹====================================*/
    			     GreedDto greedDto1=new GreedDto();
            		  //查询最高年级  
          			    intyear=intyear+1;
            			Map<String,Object>params9=new HashMap<String,Object>();
            			params9.put("discode",discode);
            			params9.put("cmis30id",cim);
            			params9.put("graduateyear",intyear);
            			List<Integer> classidlist2=findSteudentCountSum(params9);
	          				    Map<String,Object>params4=new HashMap<String,Object>();
			          			params4.put("discode",discode);
			          			params4.put("cmis30id",cim);
			          			params4.put("graduateyear",intyear);
			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
		          			    String 	semesterid2=String.valueOf(intyear)+"2";
			          			params4.put("semesterid1",semesterid1);
				          		params4.put("semesterid2",semesterid2);
				          		List<ListTopicBenDto> listTopic=findCzApperSum(params4);
			          			 TopicStatDto top =new TopicStatDto(); 
	          				     top.setGreed("七年级");
	          				   int er=listTopic.size();
	          				   for(int z=0;z<er;z++)
	          			        {  Integer StudenSum= classidlist2.get(0);
	          			        	if(null!=StudenSum){
	          			        	  top.setChinesecount(StudenSum);//总人数
	          			        	  String ss=listTopic.get(z).getSubject();
	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
	          			             top= apper(top,ss,apped,appedSum,StudenSum);
	          			            }
	          			        }
	          			      greedDto1.getList().add(top);
	          			      greedDto1.setGreedName(intyear);
	         			      greedDto1.setGreedRow(1);
	         			      FGT.getListGreedDto().add(greedDto1);
	         			      FGT.setSchoolRow(6);
          			
          		}else{
          			//有具体学年
          			
          			if("1".equals(greed)){
      					return null;
      				}
          		    //如果选择的是七年级 
          			 if("2".equals(greed)){
                		  //查询最高年级  
                			Map<String,Object>params1=new HashMap<String,Object>();
                			params1.put("discode",discode);
                			params1.put("cmis30id",cim);
                			params1.put("graduateyear",intyear);
                			List<Integer> classidlist=findSteudentCountSum(params1);
                			 //查询最高年级  各科目评价  
                			GreedDto greedDto3=new GreedDto();
      	          				    Map<String,Object>params2=new HashMap<String,Object>();
      			          			params2.put("discode",discode);
      			          			params2.put("cmis30id",cim);
      			          			params2.put("graduateyear",intyear);
      			          		    String semesterid1=String.valueOf(intyear-2)+"1"; 
			          			    String semesterid2=String.valueOf(intyear-2)+"2"; 
      			          			params2.put("semesterid1",semesterid1);
      				          		params2.put("semesterid2",semesterid2);
      				          	List<ListTopicBenDto> listTopic=findCzApperSum(params2);
      			          			TopicStatDto top =new TopicStatDto(); 
      	          						 top.setGreed("七年级");
      	          						 int qwe=listTopic.size();
      	          					  for(int z=0;z<qwe;z++)
  		          			        {  Integer StudenSum= classidlist.get(0);
  		          			        	if(null!=StudenSum){
  		          			        	  top.setChinesecount(StudenSum);//总人数
  		          			        	  String ss=listTopic.get(z).getSubject();
  		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
  		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
  		          			             top= apper(top,ss,apped,appedSum,StudenSum);
  		          			            }
  		          			        }
      	          			      greedDto3.getList().add(top);
      	          			      greedDto3.setGreedName(intyear);
      	          			      greedDto3.setGreedRow(1);
      	          			      FGT.getListGreedDto().add(greedDto3);
                        /*====================================== 二 ====================================*/
           			    GreedDto greedDto2=new GreedDto();
                			   intyear=intyear+1;
                  			Map<String,Object>params3=new HashMap<String,Object>();
                  			params3.put("discode",discode);
                  			params3.put("cmis30id",cim);
                  			params3.put("graduateyear",intyear);
                  			List<Integer> classidlist1=findSteudentCountSum(params3);
      	          				    Map<String,Object>params4=new HashMap<String,Object>();
      			          			params4.put("discode",discode);
      			          			params4.put("cmis30id",cim);
      			          			params4.put("graduateyear",intyear);
      			          		    String semesterid3=String.valueOf(intyear-1)+"1"; 
		          			        String semesterid4=String.valueOf(intyear-1)+"2"; 
      			          			params4.put("semesterid1",semesterid3);
      				          		params4.put("semesterid2",semesterid4);
      				          	List<ListTopicBenDto> listTopic_1=findCzApperSum(params4);
      			          			 TopicStatDto tops =new TopicStatDto(); 
      	          						 tops.setGreed("七年级");
      	          					      int wer=listTopic_1.size();
      	          					  for(int z=0;z<wer;z++)
    		          			        {  Integer StudenSum= classidlist1.get(0);
    		          			        	if(null!=StudenSum){
    		          			        	  tops.setChinesecount(StudenSum);//总人数
    		          			        	  String ss=listTopic_1.get(z).getSubject();
    		          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
    		          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
    		          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
    		          			            }
    		          			        }
      	          			      greedDto2.getList().add(tops);
                			      greedDto2.setGreedName(intyear);
          			              greedDto2.setGreedRow(1);
          			              FGT.getListGreedDto().add(greedDto2);
                			
                		    /*====================================== 壹====================================*/
          			     GreedDto greedDto1=new GreedDto();
                  		  //查询最高年级  
                			    intyear=intyear+1;
                  			Map<String,Object>params9=new HashMap<String,Object>();
                  			params9.put("discode",discode);
                  			params9.put("cmis30id",cim);
                  			params9.put("graduateyear",intyear);
                  			List<Integer> classidlist2=findSteudentCountSum(params9);
                				
      	          			/*for(int i=0;i<topicList.size();i++){*/
      			          			/*String subject=topicList.get(i);*/
      	          				    Map<String,Object>params_4=new HashMap<String,Object>();
      			          			params_4.put("discode",discode);
      			          			params_4.put("cmis30id",cim);
      			          			params_4.put("graduateyear",intyear);
      			          		    String semesterid5=String.valueOf(intyear)+"1"; 
	          			            String semesterid6=String.valueOf(intyear)+"2";
      				          		params_4.put("semesterid1",semesterid5);
      				          		params_4.put("semesterid2",semesterid6);
      				             	List<ListTopicBenDto> listTopic_s=findCzApperSum(params_4);
      			          			 TopicStatDto top_s =new TopicStatDto(); 
      	          				     top_s.setGreed("七年级");
      	          			          int asf=listTopic_s.size();
      	          				 for(int z=0;z<asf;z++)
		          			        {  Integer StudenSum= classidlist2.get(0);
		          			        	if(null!=StudenSum){
		          			        	  top_s.setChinesecount(StudenSum);//总人数
		          			        	  String ss=listTopic_s.get(z).getSubject();
		          			             Integer apped=listTopic_s.get(z).getStudentApperedTopiccount();//评价的人数
		          			             Integer appedSum= listTopic_s.get(z).getTopicApperSum();//总的评价书 
		          			             top_s= apper(top_s,ss,apped,appedSum,StudenSum);
		          			            }
		          			        }
      	          			      greedDto1.getList().add(top_s);
      	          			      greedDto1.setGreedName(intyear);
      	         			      greedDto3.setGreedRow(1);
      	         			      FGT.getListGreedDto().add(greedDto1);
      	         			      FGT.setSchoolRow(3);
                			
          				
          			}else if("3".equals(greed)){
                /*  -----------------------选择八年级----------------------------------*/
              		  //查询最高年级  
              			Map<String,Object>params1=new HashMap<String,Object>();
              			params1.put("discode",discode);
              			params1.put("cmis30id",cim);
              			params1.put("graduateyear",intyear);
              			List<Integer> classidlist=findSteudentCountSum(params1);
              			 //查询最高年级  各科目评价  
              			GreedDto greedDto3=new GreedDto();
    	          				    Map<String,Object>params2=new HashMap<String,Object>();
    			          			params2.put("discode",discode);
    			          			params2.put("cmis30id",cim);
    			          			params2.put("graduateyear",intyear);
    			          			String semesterid1=String.valueOf(intyear-1)+"1"; 
		          			        String semesterid2=String.valueOf(intyear-1)+"2"; 
    			          			params2.put("semesterid1",semesterid1);
    				          		params2.put("semesterid2",semesterid2);
    				         List<ListTopicBenDto> listTopic=findCzApperSum(params2);
    			          			TopicStatDto top =new TopicStatDto(); 
    	          						 top.setGreed("八年级");
    	          						 int zzc=listTopic.size();
    	          						 for(int z=0;z<zzc;z++)
 			          			        {  Integer StudenSum= classidlist.get(0);
 			          			        	if(null!=StudenSum){
 			          			        	  top.setChinesecount(StudenSum);//总人数
 			          			        	  String ss=listTopic.get(z).getSubject();
 			          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
 			          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
 			          			             top= apper(top,ss,apped,appedSum,StudenSum);
 			          			            }
 			          			        }
    	          			      greedDto3.getList().add(top);
    	          			      greedDto3.setGreedName(intyear);
    	          			      greedDto3.setGreedRow(1);
    	          			      FGT.getListGreedDto().add(greedDto3);
                      /*====================================== 二 ====================================*/
         			    GreedDto greedDto2=new GreedDto();
              			   intyear=intyear+1;
                			Map<String,Object>params3=new HashMap<String,Object>();
                			params3.put("discode",discode);
                			params3.put("cmis30id",cim);
                			params3.put("graduateyear",intyear);
                			List<Integer> classidlist1=findSteudentCountSum(params3);
                			
    	          				    Map<String,Object>params4=new HashMap<String,Object>();
    			          			params4.put("discode",discode);
    			          			params4.put("cmis30id",cim);
    			          			params4.put("graduateyear",intyear);
    			          			String semesterid3=String.valueOf(intyear)+"1"; 
		          			        String semesterid4=String.valueOf(intyear)+"2"; 
    				          		params4.put("semesterid1",semesterid3);
    				          		params4.put("semesterid2",semesterid4);
    				          		List<ListTopicBenDto> listTopic_1=findCzApperSum(params4);
    			          			 TopicStatDto tops =new TopicStatDto(); 
    			          			 
    	          						 tops.setGreed("八年级");
    	          						 int kk=listTopic_1.size();
    	          						for(int z=0;z<kk;z++)
			          			        {  Integer StudenSum= classidlist1.get(0);
			          			        	if(null!=StudenSum){
			          			        	  tops.setChinesecount(StudenSum);//总人数
			          			        	  String ss=listTopic_1.get(z).getSubject();
			          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
			          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
			          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
			          			            }
			          			        }	 
    	          			     greedDto2.getList().add(tops);
              			         greedDto2.setGreedName(intyear);
        			             greedDto2.setGreedRow(1);
        			             FGT.getListGreedDto().add(greedDto2);
        			             FGT.setSchoolRow(2);
          			}else{
          		           /*----------选择九年级---------------	*/	
          				 
                		  //查询最高年级  
                			Map<String,Object>params1=new HashMap<String,Object>();
                			params1.put("discode",discode);
                			params1.put("cmis30id",cim);
                			params1.put("graduateyear",intyear);
                			List<Integer> classidlist=findSteudentCountSum(params1);
                		
                			 //查询最高年级  各科目评价  
                			GreedDto greedDto3=new GreedDto();
      	          				    Map<String,Object>params2=new HashMap<String,Object>();
      			          			params2.put("discode",discode);
      			          			params2.put("cmis30id",cim);
      			          			params2.put("graduateyear",intyear);
      			          		    String semesterid3=String.valueOf(intyear)+"1"; 
	          			            String semesterid4=String.valueOf(intyear)+"2"; 
      			          			params2.put("semesterid1",semesterid3);
      				          		params2.put("semesterid2",semesterid4);
      				          	List<ListTopicBenDto> listTopic=findCzApperSum(params2);
      			          			TopicStatDto top =new TopicStatDto(); 
      	          						 top.setGreed("九年级");
      	          						int rr=listTopic.size();
      	          						for(int z=0;z<rr;z++)
    		          			        {  Integer StudenSum= classidlist.get(0);
    		          			        	if(null!=StudenSum){
    		          			        	  top.setChinesecount(StudenSum);//总人数
    		          			        	  String ss=listTopic.get(z).getSubject();
    		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
    		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
    		          			             top= apper(top,ss,apped,appedSum,StudenSum);
    		          			            }
    		          			        }
      	          			      greedDto3.getList().add(top);
      	          			      greedDto3.setGreedName(intyear);
      	          			      greedDto3.setGreedRow(1);
      	          			      FGT.getListGreedDto().add(greedDto3);
      	          			      FGT.setSchoolRow(1);
          			}
          			
          		}
          	}else{
/*------------------//有具体科目----------------------------------*/
          		if("0".equals(greed)){
                    /*----------------------------- 叁-------------------------------------*/
                    		  //查询最高年级  
                    			Map<String,Object> params1 =new HashMap<String,Object>();
                    			params1.put("discode",discode);
                    			params1.put("cmis30id",cim);
                    			params1.put("graduateyear",intyear);
                    			List<Integer> classidlist=findSteudentCountSum(params1);
                    			 //查询最高年级  各科目评价  
                    			GreedDto greedDto3=new GreedDto();
                    			for(int w=0;w<3;w++){
          	          				    Map<String,Object>params2=new HashMap<String,Object>();
          			          			params2.put("discode",discode);
          			          			params2.put("cmis30id",cim);
          			          			params2.put("graduateyear",intyear);
          			          			params2.put("graduateyear",intyear);
          			          			params2.put("subject_id",topic);
          			          			if(w==0){
          			          			    String 	semesterid1=String.valueOf(intyear-2)+"1"; 
          			          			    String 	semesterid2=String.valueOf(intyear-2)+"2"; 
          				          			params2.put("semesterid1",semesterid1);
          				          			params2.put("semesterid2",semesterid2);
          			          			}else if(w==1){
          			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
          			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
          			          				params2.put("semesterid1",semesterid1);
          				          			params2.put("semesterid2",semesterid2);
          			          			}else{
          			          				String 	semesterid1=String.valueOf(intyear)+"1"; 
          			          			    String 	semesterid2=String.valueOf(intyear)+"2";
          			          				params2.put("semesterid1",semesterid1);
          				          			params2.put("semesterid2",semesterid2);
          			          			}
          			          			
          			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
          			          			
          			          			TopicStatDto top =new TopicStatDto(); 
          			          			if(0==w){
          	          						 top.setGreed("七年级");
          	          					 }else if(1==w){
          	          						top.setGreed("八年级");
          	          					 }else{
          	          						top.setGreed("九年级");
          	          					 }
          			          			 int qq=listTopic.size();
          	          			        for(int z=0;z<qq;z++)
          	          			        {  Integer StudenSum= classidlist.get(0);
          	          			        	if(null!=StudenSum){
          	          			        	top.setChinesecount(StudenSum);//总人数
          	          			        	 String ss=topic;
          	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
          	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
          	          			             top= apper(top,ss,apped,appedSum,StudenSum);
          	          			            }
          	          			        }
          	          			      greedDto3.getList().add(top);
                    			}
               			      greedDto3.setGreedName(intyear);
               			      greedDto3.setGreedRow(3);
               			     FGT.getListGreedDto().add(greedDto3);
                            /*====================================== 二 ====================================*/
               			    GreedDto greedDto2=new GreedDto();
               			     
                    			   intyear=intyear+1;
                      			Map<String,Object>params3=new HashMap<String,Object>();
                      			params3.put("discode",discode);
                      			params3.put("cmis30id",cim);
                      			params3.put("graduateyear",intyear);
                      			List<Integer> classidlist1=findSteudentCountSum(params3);	
                    			for(int q=0;q<2;q++){
                    				       
          	          				    Map<String,Object>params4=new HashMap<String,Object>();
          			          			params4.put("discode",discode);
          			          			params4.put("cmis30id",cim);
          			          			params4.put("graduateyear",intyear);
          			          			/*params2.put("subject",subject);*/
          			          		   params4.put("subject_id",topic);
          			          			if(q==0){
          			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
          			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
          				          			params4.put("semesterid1",semesterid1);
          				          			params4.put("semesterid2",semesterid2);
          			          			}else{
          			          			    String 	semesterid1=String.valueOf(intyear)+"1"; 
          			          			    String 	semesterid2=String.valueOf(intyear)+"2";
          			          				params4.put("semesterid1",semesterid1);
          				          			params4.put("semesterid2",semesterid2);
          			          			} 
          			          		   List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
          			          			 TopicStatDto top =new TopicStatDto(); 
          			          			 if(0==q){
          	          						 top.setGreed("七年级");
          	          					 }else{
          	          						top.setGreed("八年级");
          	          					 }
          			          			  int ww=listTopic.size();
          			          			 for(int z=0;z<ww;z++)
          		          			        {  Integer StudenSum= classidlist1.get(0);
          		          			        	if(null!=StudenSum){
          		          			        	  top.setChinesecount(StudenSum);//总人数
          		          			        	  String ss=topic;
          		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
          		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
          		          			             top= apper(top,ss,apped,appedSum,StudenSum);
          		          			            }
          		          			        }
          	          			      greedDto2.getList().add(top);
          	          			      	
          	          			/*}*/
                    			}
                    			  greedDto2.setGreedName(intyear);
                    		
              			      greedDto2.setGreedRow(2);
              			     FGT.getListGreedDto().add(greedDto2);
                    			
                    		    /*====================================== 壹====================================*/
              			     GreedDto greedDto1=new GreedDto();
                      		  //查询最高年级  
                    			    intyear=intyear+1;
                      			Map<String,Object>params9=new HashMap<String,Object>();
                      			params9.put("discode",discode);
                      			params9.put("cmis30id",cim);
                      			params9.put("graduateyear",intyear);
                      			List<Integer> classidlist2=findSteudentCountSum(params9);
          	          				    Map<String,Object>params4=new HashMap<String,Object>();
          			          			params4.put("discode",discode);
          			          			params4.put("cmis30id",cim);
          			          			params4.put("graduateyear",intyear);
          			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
          		          			    String 	semesterid2=String.valueOf(intyear)+"2";
          			          			params4.put("semesterid1",semesterid1);
          				          		params4.put("semesterid2",semesterid2);
          				          	    params4.put("subject_id",topic);
            			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
          			          			 TopicStatDto top =new TopicStatDto(); 
          	          				     top.setGreed("七年级");
          	          				   int er=listTopic.size();
          	          				   for(int z=0;z<er;z++)
          	          			        {  Integer StudenSum= classidlist2.get(0);
          	          			        	if(null!=StudenSum){
          	          			        	  top.setChinesecount(StudenSum);//总人数
          	          			        	  String ss=topic;
          	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
          	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
          	          			             top= apper(top,ss,apped,appedSum,StudenSum);
          	          			            }
          	          			        }
          	          			      greedDto1.getList().add(top);
          	          			      greedDto1.setGreedName(intyear);
          	         			      greedDto1.setGreedRow(1);
          	         			      FGT.getListGreedDto().add(greedDto1);
          	         			      FGT.setSchoolRow(6);
                    			
                    		}else{
                    			//有具体学年
                    			if("1".equals(greed)){
                					return null;
                				}
                    		    //如果选择的是七年级 
                    			 if("2".equals(greed)){
                          		  //查询最高年级  
                          			Map<String,Object>params1=new HashMap<String,Object>();
                          			params1.put("discode",discode);
                          			params1.put("cmis30id",cim);
                          			params1.put("graduateyear",intyear);
                          			List<Integer> classidlist=findSteudentCountSum(params1);
                          			 //查询最高年级  各科目评价  
                          			GreedDto greedDto3=new GreedDto();
                	          				    Map<String,Object>params2=new HashMap<String,Object>();
                			          			params2.put("discode",discode);
                			          			params2.put("cmis30id",cim);
                			          			params2.put("graduateyear",intyear);
                			          			params2.put("subject_id",topic);
                			          			String semesterid1=String.valueOf(intyear-2)+"1"; 
          			          			        String semesterid2=String.valueOf(intyear-2)+"2"; 
                			          			params2.put("semesterid1",semesterid1);
                				          		params2.put("semesterid2",semesterid2); 
                				          	List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
                			          			TopicStatDto top_3 =new TopicStatDto(); 
                	          						 top_3.setGreed("七年级");
                	          						 int qwe=listTopic.size();
                	          					  for(int z=0;z<qwe;z++)
            		          			        {  Integer StudenSum= classidlist.get(0);
            		          			        	if(null!=StudenSum){
            		          			        	  top_3.setChinesecount(StudenSum);//总人数
            		          			        	  String ss=topic;
            		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
            		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
            		          			             top_3= apper(top_3,ss,apped,appedSum,StudenSum);
            		          			            }
            		          			        }
                	          			      greedDto3.getList().add(top_3);
                	          			      greedDto3.setGreedName(intyear);
                	          			      greedDto3.setGreedRow(1);
                	          			      FGT.getListGreedDto().add(greedDto3);
                                  /*====================================== 二 ====================================*/
                     			    GreedDto greedDto2=new GreedDto();
                          			   intyear=intyear+1;
                            			Map<String,Object>params3=new HashMap<String,Object>();
                            			params3.put("discode",discode);
                            			params3.put("cmis30id",cim);
                            			params3.put("graduateyear",intyear);
                            			List<Integer> classidlist1=findSteudentCountSum(params3);
                	          				    Map<String,Object>params4=new HashMap<String,Object>();
                			          			params4.put("discode",discode);
                			          			params4.put("cmis30id",cim);
                			          			params4.put("graduateyear",intyear);
                			          			params4.put("subject_id",topic);
                			          		    String semesterid3=String.valueOf(intyear-1)+"1"; 
          		          			            String semesterid4=String.valueOf(intyear-1)+"2"; 
                			          			params4.put("semesterid1",semesterid3);
                				          		params4.put("semesterid2",semesterid4);
                				          	List<ListTopicBenDto> listTopic_1=findCzApperSumTopic(params4);
                			          			 TopicStatDto tops =new TopicStatDto(); 
                	          						 tops.setGreed("七年级");
                	          					      int wer=listTopic_1.size();
                	          					  for(int z=0;z<wer;z++)
              		          			        {  Integer StudenSum= classidlist1.get(0);
              		          			        	if(null!=StudenSum){
              		          			        	  tops.setChinesecount(StudenSum);//总人数
              		          			        	  String ss=topic;
              		          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
              		          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
              		          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
              		          			            }
              		          			        }
                	          			      greedDto2.getList().add(tops);
                          			      greedDto2.setGreedName(intyear);
                    			              greedDto2.setGreedRow(1);
                    			              FGT.getListGreedDto().add(greedDto2);
                          			
                          		    /*====================================== 壹====================================*/
                    			     GreedDto greedDto1=new GreedDto();
                            		  //查询最高年级  
                          			    intyear=intyear+1;
                            			Map<String,Object>params9=new HashMap<String,Object>();
                            			params9.put("discode",discode);
                            			params9.put("cmis30id",cim);
                            			params9.put("graduateyear",intyear);
                            			List<Integer> classidlist2=findSteudentCountSum(params9);
                          				
                	          			/*for(int i=0;i<topicList.size();i++){*/
                			          			/*String subject=topicList.get(i);*/
                	          				    Map<String,Object>params_4=new HashMap<String,Object>();
                			          			params_4.put("discode",discode);
                			          			params_4.put("cmis30id",cim);
                			          			params_4.put("graduateyear",intyear);
                			          		    String semesterid5=String.valueOf(intyear)+"1"; 
          	          			            String semesterid6=String.valueOf(intyear)+"2";
                				          		params_4.put("semesterid1",semesterid5);
                				          		params_4.put("semesterid2",semesterid6);
                				          		params_4.put("subject_id",topic);
                				             	List<ListTopicBenDto> listTopic_s=findCzApperSumTopic(params_4);
                			          			 TopicStatDto top_s =new TopicStatDto(); 
                	          				     top_s.setGreed("七年级");
                	          			          int asf=listTopic_s.size();
                	          				 for(int z=0;z<asf;z++)
          		          			        {  Integer StudenSum= classidlist2.get(0);
          		          			        	if(null!=StudenSum){
          		          			        	  top_s.setChinesecount(StudenSum);//总人数
          		          			        	  String ss=topic;
          		          			             Integer apped=listTopic_s.get(z).getStudentApperedTopiccount();//评价的人数
          		          			             Integer appedSum= listTopic_s.get(z).getTopicApperSum();//总的评价书 
          		          			             top_s= apper(top_s,ss,apped,appedSum,StudenSum);
          		          			            }
          		          			        }
                	          			      greedDto1.getList().add(top_s);
                	          			      greedDto1.setGreedName(intyear);
                	         			      greedDto3.setGreedRow(1);
                	         			      FGT.getListGreedDto().add(greedDto1);
                	         			      FGT.setSchoolRow(3);
                          			
                    				
                    			}else if("3".equals(greed)){
                          /*  -----------------------选择八年级----------------------------------*/
                        		  //查询最高年级  
                        			Map<String,Object>params1=new HashMap<String,Object>();
                        			params1.put("discode",discode);
                        			params1.put("cmis30id",cim);
                        			params1.put("graduateyear",intyear);
                        			List<Integer> classidlist=findSteudentCountSum(params1);
                        			 //查询最高年级  各科目评价  
                        			GreedDto greedDto3=new GreedDto();
              	          				    Map<String,Object>params2=new HashMap<String,Object>();
              			          			params2.put("discode",discode);
              			          			params2.put("cmis30id",cim);
              			          			params2.put("graduateyear",intyear);
              			          		    params2.put("subject_id",topic);
              			          			String semesterid1=String.valueOf(intyear-1)+"1"; 
          		          			        String semesterid2=String.valueOf(intyear-1)+"2"; 
              			          			params2.put("semesterid1",semesterid1);
              				          		params2.put("semesterid2",semesterid2);
              				         List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
              			          			TopicStatDto top =new TopicStatDto(); 
              	          						 top.setGreed("八年级");
              	          						 int zzc=listTopic.size();
              	          						 for(int z=0;z<zzc;z++)
           			          			        {  Integer StudenSum= classidlist.get(0);
           			          			        	if(null!=StudenSum){
           			          			        	  top.setChinesecount(StudenSum);//总人数
           			          			        	  String ss=topic;
           			          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
           			          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
           			          			             top= apper(top,ss,apped,appedSum,StudenSum);
           			          			            }
           			          			        }
              	          			      greedDto3.getList().add(top);
              	          			      greedDto3.setGreedName(intyear);
              	          			      greedDto3.setGreedRow(1);
              	          			      FGT.getListGreedDto().add(greedDto3);
                                /*====================================== 二 ====================================*/
                   			    GreedDto greedDto2=new GreedDto();
                        			   intyear=intyear+1;
                          			Map<String,Object>params3=new HashMap<String,Object>();
                          			params3.put("discode",discode);
                          			params3.put("cmis30id",cim);
                          			params3.put("graduateyear",intyear);
                          			params3.put("subject_id",topic);
                          			List<Integer> classidlist1=findSteudentCountSum(params3);
                          			
              	          				    Map<String,Object>params4=new HashMap<String,Object>();
              			          			params4.put("discode",discode);
              			          			params4.put("cmis30id",cim);
              			          			params4.put("graduateyear",intyear);
              			          			String semesterid3=String.valueOf(intyear)+"1"; 
          		          			        String semesterid4=String.valueOf(intyear)+"2"; 
              				          		params4.put("semesterid1",semesterid3);
              				          		params4.put("semesterid2",semesterid4);
              				          		List<ListTopicBenDto> listTopic_1=findCzApperSumTopic(params4);
              			          			 TopicStatDto tops =new TopicStatDto(); 
              	          						 tops.setGreed("八年级");
              	          						 int kk=listTopic_1.size();
              	          						for(int z=0;z<kk;z++)
          			          			        {  Integer StudenSum= classidlist1.get(0);
          			          			        	if(null!=StudenSum){
          			          			        	  tops.setChinesecount(StudenSum);//总人数
          			          			        	  String ss=topic;
          			          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
          			          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
          			          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
          			          			            }
          			          			        }	 
              	          			     greedDto2.getList().add(tops);
                        			         greedDto2.setGreedName(intyear);
                  			             greedDto2.setGreedRow(1);
                  			             FGT.getListGreedDto().add(greedDto2);
                  			             FGT.setSchoolRow(2);
                    			}else{
                    		           /*----------选择九年级---------------	*/	
                    				 
                          		  //查询最高年级  
                          			Map<String,Object>params1=new HashMap<String,Object>();
                          			params1.put("discode",discode);
                          			params1.put("cmis30id",cim);
                          			params1.put("graduateyear",intyear);
                          			List<Integer> classidlist=findSteudentCountSum(params1);
                          		
                          			 //查询最高年级  各科目评价  
                          			GreedDto greedDto3=new GreedDto();
                	          				    Map<String,Object>params2=new HashMap<String,Object>();
                			          			params2.put("discode",discode);
                			          			params2.put("cmis30id",cim);
                			          			params2.put("graduateyear",intyear);
                			          			params2.put("subject_id",topic);
                			          		    String semesterid3=String.valueOf(intyear)+"1"; 
          	          			               String semesterid4=String.valueOf(intyear)+"2"; 
                			          			params2.put("semesterid1",semesterid3);
                				          		params2.put("semesterid2",semesterid4);
                				          	List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
                			          			TopicStatDto top =new TopicStatDto(); 
                	          						 top.setGreed("九年级");
                	          						int rr=listTopic.size();
                	          						for(int z=0;z<rr;z++)
              		          			        {  Integer StudenSum= classidlist.get(0);
              		          			        	if(null!=StudenSum){
              		          			        	  top.setChinesecount(StudenSum);//总人数
              		          			        	  String ss=topic;
              		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
              		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
              		          			             top= apper(top,ss,apped,appedSum,StudenSum);
              		          			            }
              		          			        }
                	          			      greedDto3.getList().add(top);
                	          			      greedDto3.setGreedName(intyear);
                	          			      greedDto3.setGreedRow(1);
                	          			      FGT.getListGreedDto().add(greedDto3);
                	          			      FGT.setSchoolRow(1);
                    			}
                    			
                    		}
          	}
          }else{
/*<<<<<<<<<<<<<<<<<//有具体届//科目为全部是//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,>>    */    	  
            	if("0".equals(topic)){
            		 //学年为全部 
            		if("0".equals(greed)){
            			/*Integer graduateyear,
            			Integer intyear, Integer greedLength) {	*/
            		int cha=graduateyear-(intyear);
            		
            		if(0==cha){
            			/*----------------------------- 叁-------------------------------------*/
              		  //查询最高年级  
              			Map<String,Object> params1 =new HashMap<String,Object>();
              			params1.put("discode",discode);
              			params1.put("cmis30id",cim);
              			params1.put("graduateyear",intyear);
              			List<Integer> classidlist=findSteudentCountSum(params1);
              			 //查询最高年级  各科目评价  
              			GreedDto greedDto3=new GreedDto();
              			for(int w=0;w<3;w++){
    	          				    Map<String,Object>params2=new HashMap<String,Object>();
    			          			params2.put("discode",discode);
    			          			params2.put("cmis30id",cim);
    			          			params2.put("graduateyear",intyear);
    			          			if(w==0){
    			          			    String 	semesterid1=String.valueOf(intyear-2)+"1"; 
    			          			    String 	semesterid2=String.valueOf(intyear-2)+"2"; 
    				          			params2.put("semesterid1",semesterid1);
    				          			params2.put("semesterid2",semesterid2);
    			          			}else if(w==1){
    			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
    			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
    			          				params2.put("semesterid1",semesterid1);
    				          			params2.put("semesterid2",semesterid2);
    			          			}else{
    			          				String 	semesterid1=String.valueOf(intyear)+"1"; 
    			          			    String 	semesterid2=String.valueOf(intyear)+"2";
    			          				params2.put("semesterid1",semesterid1);
    				          			params2.put("semesterid2",semesterid2);
    			          			}
    			          		List<ListTopicBenDto> listTopic=findCzApperSum(params2);
    			          			
    			          			TopicStatDto top =new TopicStatDto(); 
    			          			if(0==w){
    	          						 top.setGreed("七年级");
    	          					 }else if(1==w){
    	          						top.setGreed("八年级");
    	          					 }else{
    	          						top.setGreed("九年级");
    	          					 }
    			          			 int qq=listTopic.size();
    	          			        for(int z=0;z<qq;z++)
    	          			        {  Integer StudenSum= classidlist.get(0);
    	          			        	if(null!=StudenSum){
    	          			        	  top.setChinesecount(StudenSum);//总人数
    	          			        	  String ss=listTopic.get(z).getSubject();
    	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
    	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
    	          			             top= apper(top,ss,apped,appedSum,StudenSum);
    	          			            }
    	          			        }
    	          			      greedDto3.getList().add(top);
              			}
         			      greedDto3.setGreedName(intyear);
         			      greedDto3.setGreedRow(3);
         			     FGT.getListGreedDto().add(greedDto3);
         			     FGT.setSchoolRow(3);
            		}else if(1==cha){
                    /*====================================== 二 ====================================*/
            		GreedDto greedDto2=new GreedDto();
            			   intyear=intyear+1;
              			Map<String,Object>params3=new HashMap<String,Object>();
              			params3.put("discode",discode);
              			params3.put("cmis30id",cim);
              			params3.put("graduateyear",intyear);
              			List<Integer> classidlist1=findSteudentCountSum(params3);	
            			for(int q=0;q<2;q++){
            				       
  	          				    Map<String,Object>params4=new HashMap<String,Object>();
  			          			params4.put("discode",discode);
  			          			params4.put("cmis30id",cim);
  			          			params4.put("graduateyear",intyear);
  			          			/*params2.put("subject",subject);*/
  			          			if(q==0){
  			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
  			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
  				          			params4.put("semesterid1",semesterid1);
  				          			params4.put("semesterid2",semesterid2);
  			          			}else{
  			          			    String 	semesterid1=String.valueOf(intyear)+"1"; 
  			          			    String 	semesterid2=String.valueOf(intyear)+"2";
  			          				params4.put("semesterid1",semesterid1);
  				          			params4.put("semesterid2",semesterid2);
  			          			} 
  			          			List<ListTopicBenDto> listTopic=findCzApperSum(params4);
  			          			 TopicStatDto top =new TopicStatDto(); 
  			          			 if(0==q){
  	          						 top.setGreed("七年级");
  	          					 }else{
  	          						top.setGreed("八年级");
  	          					 }
  			          			  int ww=listTopic.size();
  			          			 for(int z=0;z<ww;z++)
  		          			        {  Integer StudenSum= classidlist1.get(0);
  		          			        	if(null!=StudenSum){
  		          			        	  top.setChinesecount(StudenSum);//总人数
  		          			        	  String ss=listTopic.get(z).getSubject();
  		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
  		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
  		          			             top= apper(top,ss,apped,appedSum,StudenSum);
  		          			            }
  		          			        }
  	          			      greedDto2.getList().add(top);
  	          			      	
  	          			/*}*/
            			}
            			  greedDto2.setGreedName(intyear);
            		
      			         greedDto2.setGreedRow(2);
      			         FGT.getListGreedDto().add(greedDto2);
      			         FGT.setSchoolRow(2);
            		   }else if(2==cha){
            		    /*====================================== 壹====================================*/
      			     GreedDto greedDto1=new GreedDto();
              		  //查询最高年级  
            			    intyear=intyear+2;
              			Map<String,Object>params9=new HashMap<String,Object>();
              			params9.put("discode",discode);
              			params9.put("cmis30id",cim);
              			params9.put("graduateyear",intyear);
              			List<Integer> classidlist2=findSteudentCountSum(params9);
  	          				    Map<String,Object>params4=new HashMap<String,Object>();
  			          			params4.put("discode",discode);
  			          			params4.put("cmis30id",cim);
  			          			params4.put("graduateyear",intyear);
  			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
  		          			    String 	semesterid2=String.valueOf(intyear)+"2";
  			          			params4.put("semesterid1",semesterid1);
  				          		params4.put("semesterid2",semesterid2);
  				          		List<ListTopicBenDto> listTopic=findCzApperSum(params4);
  			          			 TopicStatDto top =new TopicStatDto(); 
  	          				     top.setGreed("七年级");
  	          				   int er=listTopic.size();
  	          				   for(int z=0;z<er;z++)
  	          			        {  Integer StudenSum= classidlist2.get(0);
  	          			        	if(null!=StudenSum){
  	          			        	  top.setChinesecount(StudenSum);//总人数
  	          			        	  String ss=listTopic.get(z).getSubject();
  	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
  	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
  	          			             top= apper(top,ss,apped,appedSum,StudenSum);
  	          			            }
  	          			        }
  	          			      greedDto1.getList().add(top);
  	          			      greedDto1.setGreedName(intyear);
  	         			      greedDto1.setGreedRow(1);
  	         			      FGT.getListGreedDto().add(greedDto1);
  	         			      FGT.setSchoolRow(1);
            		   }
            		}else{
            			
/*<<<<<<<<<<<<<<<<<//有具体届//科目为全部//有具体学年<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,>>    */
            			if("1".equals(greed)){
        					return null;
        				}
            			int cha=graduateyear-intyear;
                		if(0==cha){
                		 if("2".equals(greed)){
               			    //2016届 全科目 七年级  
               				 //查询最高年级  
                     			Map<String,Object>params1=new HashMap<String,Object>();
                     			params1.put("discode",discode);
                     			params1.put("cmis30id",cim);
                     			params1.put("graduateyear",intyear);
                     			List<Integer> classidlist=findSteudentCountSum(params1);
                     			 //查询最高年级  各科目评价  
                     			GreedDto greedDto3=new GreedDto();
           	          				    Map<String,Object>params2=new HashMap<String,Object>();
           			          			params2.put("discode",discode);
           			          			params2.put("cmis30id",cim);
           			          			params2.put("graduateyear",intyear);
           			          		    String semesterid1=String.valueOf(intyear-2)+"1"; 
     			          			    String semesterid2=String.valueOf(intyear-2)+"2"; 
           			          			params2.put("semesterid1",semesterid1);
           				          		params2.put("semesterid2",semesterid2);
           				          	List<ListTopicBenDto> listTopic=findCzApperSum(params2);
           			          			TopicStatDto top =new TopicStatDto(); 
           	          						 top.setGreed("七年级");
           	          						 int qwe=listTopic.size();
           	          					  for(int z=0;z<qwe;z++)
       		          			        {  Integer StudenSum= classidlist.get(0);
       		          			        	if(null!=StudenSum){
       		          			        	  top.setChinesecount(StudenSum);//总人数
       		          			        	  String ss=listTopic.get(z).getSubject();
       		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
       		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
       		          			             top= apper(top,ss,apped,appedSum,StudenSum);
       		          			            }
       		          			        }
           	          			      greedDto3.getList().add(top);
           	          			      greedDto3.setGreedName(intyear);
           	          			      greedDto3.setGreedRow(1);
           	          			      FGT.getListGreedDto().add(greedDto3);
           	          			      FGT.setSchoolRow(1);
                             /*====================================== 二 ====================================*/
           	          	      //2016届 全科目 八年级
           	          	   if("3".equals(greed)){  
           	          		 GreedDto greedDto2=new GreedDto();
                       			Map<String,Object>params3=new HashMap<String,Object>();
                       			params3.put("discode",discode);
                       			params3.put("cmis30id",cim);
                       			params3.put("graduateyear",intyear);
                       			List<Integer> classidlist1=findSteudentCountSum(params3);
           	          				    Map<String,Object>params4=new HashMap<String,Object>();
           			          			params4.put("discode",discode);
           			          			params4.put("cmis30id",cim);
           			          			params4.put("graduateyear",intyear);
           			          		    String semesterid3=String.valueOf(intyear-1)+"1"; 
     		          			        String semesterid4=String.valueOf(intyear-1)+"2"; 
           			          			params4.put("semesterid1",semesterid3);
           				          		params4.put("semesterid2",semesterid4);
           				          	List<ListTopicBenDto> listTopic_1=findCzApperSum(params4);
           			          			 TopicStatDto tops =new TopicStatDto(); 
           	          						 tops.setGreed("八年级");
           	          					      int wer=listTopic_1.size();
           	          					  for(int z=0;z<wer;z++)
         		          			        {  Integer StudenSum= classidlist1.get(0);
         		          			        	if(null!=StudenSum){
         		          			        	  tops.setChinesecount(StudenSum);//总人数
         		          			        	  String ss=listTopic_1.get(z).getSubject();
         		          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
         		          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
         		          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
         		          			            }
         		          			        }
           	          			      greedDto2.getList().add(tops);
                     			      greedDto2.setGreedName(intyear);
               			              greedDto2.setGreedRow(1);
               			              FGT.getListGreedDto().add(greedDto2);
               			              FGT.setSchoolRow(1);
           	          	         }
                     		    /*====================================== 壹====================================*/
               			    
           	               //2016届 全科目 九年级
           	          	   if("4".equals(greed)){   
           	          	    GreedDto greedDto1=new GreedDto();
                       		  //查询最高年级  
                       			Map<String,Object>params9=new HashMap<String,Object>();
                       			params9.put("discode",discode);
                       			params9.put("cmis30id",cim);
                       			params9.put("graduateyear",intyear);
                       			List<Integer> classidlist2=findSteudentCountSum(params9);
                     				
           	          			/*for(int i=0;i<topicList.size();i++){*/
           			          			/*String subject=topicList.get(i);*/
           	          				    Map<String,Object>params_4=new HashMap<String,Object>();
           			          			params_4.put("discode",discode);
           			          			params_4.put("cmis30id",cim);
           			          			params_4.put("graduateyear",intyear);
           			          		    String semesterid5=String.valueOf(intyear)+"1"; 
     	          			            String semesterid6=String.valueOf(intyear)+"2";
           				          		params_4.put("semesterid1",semesterid5);
           				          		params_4.put("semesterid2",semesterid6);
           				             	List<ListTopicBenDto> listTopic_s=findCzApperSum(params_4);
           			          			 TopicStatDto top_s =new TopicStatDto(); 
           	          				     top_s.setGreed("九年级");
           	          			          int asf=listTopic_s.size();
           	          				 for(int z=0;z<asf;z++)
     		          			        {  Integer StudenSum= classidlist2.get(0);
     		          			        	if(null!=StudenSum){
     		          			        		top_s.setChinesecount(StudenSum);//总人数
     		          			        	  String ss=listTopic_s.get(z).getSubject();
     		          			             Integer apped=listTopic_s.get(z).getStudentApperedTopiccount();//评价的人数
     		          			             Integer appedSum= listTopic_s.get(z).getTopicApperSum();//总的评价书 
     		          			          top_s= apper(top_s,ss,apped,appedSum,StudenSum);
     		          			            }
     		          			        }
           	          			      greedDto1.getList().add(top_s);
           	          			      greedDto1.setGreedName(intyear);
           	         			      greedDto3.setGreedRow(1);
           	         			      FGT.getListGreedDto().add(greedDto1);
           	         			      FGT.setSchoolRow(1);
           	          	      }
               			 } //2017届 全科目 年级
                		}else if(1==cha){
                			      //2017届 全科目 七年级
                        			 if("2".equals(greed)){
                              		  //查询最高年级  
                              			Map<String,Object>params1=new HashMap<String,Object>();
                              			params1.put("discode",discode);
                              			params1.put("cmis30id",cim);
                              			params1.put("graduateyear",graduateyear);
                              			List<Integer> classidlist=findSteudentCountSum(params1);
                              			 //查询最高年级  各科目评价  
                              			GreedDto greedDto3=new GreedDto();
                    	          				    Map<String,Object>params2=new HashMap<String,Object>();
                    			          			params2.put("discode",discode);
                    			          			params2.put("cmis30id",cim);
                    			          			params2.put("graduateyear",graduateyear);
                    			          		    String semesterid1=String.valueOf(intyear-2)+"1"; 
              			          			    String semesterid2=String.valueOf(intyear-2)+"2"; 
                    			          			params2.put("semesterid1",semesterid1);
                    				          		params2.put("semesterid2",semesterid2);
                    				          	List<ListTopicBenDto> listTopic=findCzApperSum(params2);
                    			          			TopicStatDto top =new TopicStatDto(); 
                    	          						 top.setGreed("七年级");
                    	          						 int qwe=listTopic.size();
                    	          					  for(int z=0;z<qwe;z++)
                		          			        {  Integer StudenSum= classidlist.get(0);
                		          			        	if(null!=StudenSum){
                		          			        	  top.setChinesecount(StudenSum);//总人数
                		          			        	  String ss=listTopic.get(z).getSubject();
                		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
                		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
                		          			             top= apper(top,ss,apped,appedSum,StudenSum);
                		          			            }
                		          			        }
                    	          			      greedDto3.getList().add(top);
                    	          			      greedDto3.setGreedName(intyear+1);
                    	          			      greedDto3.setGreedRow(1);
                    	          			      FGT.getListGreedDto().add(greedDto3);
                    	          			      FGT.setSchoolRow(1);
                        			 }
                                      /*====================================== 二 ====================================*/
                        		  if("3".equals(greed)){
                        			     //2017届 全科目 8年级
                        			  Map<String,Object>params1=new HashMap<String,Object>();
                            			params1.put("discode",discode);
                            			params1.put("cmis30id",cim);
                            			params1.put("graduateyear",graduateyear);
                            			List<Integer> classidlist=findSteudentCountSum(params1);
                    	          		 GreedDto greedDto2=new GreedDto();
                              			   intyear=intyear+2;
                                			Map<String,Object>params3=new HashMap<String,Object>();
                                			params3.put("discode",discode);
                                			params3.put("cmis30id",cim);
                                			params3.put("graduateyear",intyear);
                                			List<Integer> classidlist1=findSteudentCountSum(params3);
                    	          				    Map<String,Object>params4=new HashMap<String,Object>();
                    			          			params4.put("discode",discode);
                    			          			params4.put("cmis30id",cim);
                    			          			params4.put("graduateyear",intyear);
                    			          		    String semesterid3=String.valueOf(intyear-2)+"1"; 
              		          			            String semesterid4=String.valueOf(intyear-2)+"2"; 
                    			          			params4.put("semesterid1",semesterid3);
                    				          		params4.put("semesterid2",semesterid4);
                    				          	List<ListTopicBenDto> listTopic_1=findCzApperSum(params4);
                    			          			 TopicStatDto tops =new TopicStatDto(); 
                    	          						 tops.setGreed("七年级");
                    	          					      int wer=listTopic_1.size();
                    	          					  for(int z=0;z<wer;z++)
                  		          			        {  Integer StudenSum= classidlist1.get(0);
                  		          			        	if(null!=StudenSum){
                  		          			        	  tops.setChinesecount(StudenSum);//总人数
                  		          			        	  String ss=listTopic_1.get(z).getSubject();
                  		          			             Integer apped=listTopic_1.get(z).getStudentApperedTopiccount();//评价的人数
                  		          			             Integer appedSum= listTopic_1.get(z).getTopicApperSum();//总的评价书 
                  		          			             tops= apper(tops,ss,apped,appedSum,StudenSum);
                  		          			            }
                  		          			        }
                    	          			      greedDto2.getList().add(tops);
                              			      greedDto2.setGreedName(intyear);
                        			              greedDto2.setGreedRow(1);
                        			              FGT.getListGreedDto().add(greedDto2);
                        			              FGT.setSchoolRow(1);
                      			
                        		  }
                			
                		}else if(2==cha){
                			
                				  //2018届 全科目 七年级
                				 /*====================================== 壹====================================*/
              			     GreedDto greedDto1=new GreedDto();
                      		  //查询最高年级  
                    			    intyear=intyear+2;
                      			Map<String,Object>params9=new HashMap<String,Object>();
                      			params9.put("discode",discode);
                      			params9.put("cmis30id",cim);
                      			params9.put("graduateyear",intyear);
                      			List<Integer> classidlist2=findSteudentCountSum(params9);
                    				
          	          			/*for(int i=0;i<topicList.size();i++){*/
          			          			/*String subject=topicList.get(i);*/
          	          				    Map<String,Object>params_4=new HashMap<String,Object>();
          			          			params_4.put("discode",discode);
          			          			params_4.put("cmis30id",cim);
          			          			params_4.put("graduateyear",intyear);
          			          		    String semesterid5=String.valueOf(intyear)+"1"; 
    	          			            String semesterid6=String.valueOf(intyear)+"2";
          				          		params_4.put("semesterid1",semesterid5);
          				          		params_4.put("semesterid2",semesterid6);
          				             	List<ListTopicBenDto> listTopic_s=findCzApperSum(params_4);
          			          			 TopicStatDto top_s =new TopicStatDto(); 
          	          				     top_s.setGreed("九年级");
          	          			          int asf=listTopic_s.size();
          	          				 for(int z=0;z<asf;z++)
    		          			        {  Integer StudenSum= classidlist2.get(0);
    		          			        	if(null!=StudenSum){
    		          			        	  top_s.setChinesecount(StudenSum);//总人数
    		          			        	  String ss=listTopic_s.get(z).getSubject();
    		          			             Integer apped=listTopic_s.get(z).getStudentApperedTopiccount();//评价的人数
    		          			             Integer appedSum= listTopic_s.get(z).getTopicApperSum();//总的评价书 
    		          			             top_s= apper(top_s,ss,apped,appedSum,StudenSum);
    		          			            }
    		          			        }
          	          			      greedDto1.getList().add(top_s);
          	          			      greedDto1.setGreedName(intyear);
          	         			      greedDto1.setGreedRow(1);
          	         			      FGT.getListGreedDto().add(greedDto1);
          	         			      FGT.setSchoolRow(1);
                		}
            			
            		
                      			
                  }
           }else{
/* *******************************有届有科目*********************************************************/
        	  
        	   int cha=graduateyear-(intyear);
        	   //2016届
        	   if(0==cha){
        		   //全部年
        		   if("0".equals(greed)){

                       /*----------------------------- 叁-------------------------------------*/
                       		  //查询最高年级  
                       			Map<String,Object> params1 =new HashMap<String,Object>();
                       			params1.put("discode",discode);
                       			params1.put("cmis30id",cim);
                       			params1.put("graduateyear",intyear);
                       			List<Integer> classidlist=findSteudentCountSum(params1);
                       			 //查询最高年级  各科目评价  
                       			GreedDto greedDto3=new GreedDto();
                       			for(int w=0;w<3;w++){
             	          				    Map<String,Object>params2=new HashMap<String,Object>();
             			          			params2.put("discode",discode);
             			          			params2.put("cmis30id",cim);
             			          			params2.put("graduateyear",intyear);
             			          			params2.put("graduateyear",intyear);
             			          			params2.put("subject_id",topic);
             			          			if(w==0){
             			          			    String 	semesterid1=String.valueOf(intyear-2)+"1"; 
             			          			    String 	semesterid2=String.valueOf(intyear-2)+"2"; 
             				          			params2.put("semesterid1",semesterid1);
             				          			params2.put("semesterid2",semesterid2);
             			          			}else if(w==1){
             			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
             			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
             			          				params2.put("semesterid1",semesterid1);
             				          			params2.put("semesterid2",semesterid2);
             			          			}else{
             			          				String 	semesterid1=String.valueOf(intyear)+"1"; 
             			          			    String 	semesterid2=String.valueOf(intyear)+"2";
             			          				params2.put("semesterid1",semesterid1);
             				          			params2.put("semesterid2",semesterid2);
             			          			}
             			          			
             			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
             			          			
             			          			TopicStatDto top =new TopicStatDto(); 
             			          			if(0==w){
             	          						 top.setGreed("七年级");
             	          					 }else if(1==w){
             	          						top.setGreed("八年级");
             	          					 }else{
             	          						top.setGreed("九年级");
             	          					 }
             			          			 int qq=listTopic.size();
             	          			        for(int z=0;z<qq;z++)
             	          			        {  Integer StudenSum= classidlist.get(0);
             	          			        	if(null!=StudenSum){
             	          			        	top.setChinesecount(StudenSum);//总人数
             	          			        	 String ss=topic;
             	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
             	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
             	          			             top= apper(top,ss,apped,appedSum,StudenSum);
             	          			            }
             	          			        }
             	          			      greedDto3.getList().add(top);
                       			}
                  			      greedDto3.setGreedName(intyear);
                  			      greedDto3.setGreedRow(3);
                  			      FGT.getListGreedDto().add(greedDto3);
                  			      FGT.setSchoolRow(3);
                  			  
        		   }else if("2".equals(greed)){
        			   
                       /*====================================== 二 ====================================*/
          			    GreedDto greedDto2=new GreedDto();
                 			Map<String,Object>params3=new HashMap<String,Object>();
                 			params3.put("discode",discode);
                 			params3.put("cmis30id",cim);
                 			params3.put("graduateyear",intyear);
                 			List<Integer> classidlist1=findSteudentCountSum(params3);	
     	          				    Map<String,Object>params4=new HashMap<String,Object>();
     			          			params4.put("discode",discode);
     			          			params4.put("cmis30id",cim);
     			          			params4.put("graduateyear",intyear);
     			          			/*params2.put("subject",subject);*/
     			          		   params4.put("subject_id",topic);
     			          				String 	semesterid1=String.valueOf(intyear-2)+"1"; 
     			          			    String 	semesterid2=String.valueOf(intyear-2)+"2";
     				          			params4.put("semesterid1",semesterid1);
     				          			params4.put("semesterid2",semesterid2);
     			          		   List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
     			          			TopicStatDto top =new TopicStatDto(); 
     	          					 top.setGreed("七年级");
     			          			  int ww=listTopic.size();
     			          			 for(int z=0;z<ww;z++)
     		          			        {  Integer StudenSum= classidlist1.get(0);
     		          			        	if(null!=StudenSum){
     		          			        	  top.setChinesecount(StudenSum);//总人数
     		          			        	  String ss=topic;
     		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
     		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
     		          			             top= apper(top,ss,apped,appedSum,StudenSum);
     		          			            }
     		          			        }
     	          			      greedDto2.getList().add(top);
               			          greedDto2.setGreedName(intyear);
         			              greedDto2.setGreedRow(1);
         			              FGT.getListGreedDto().add(greedDto2);
         			              FGT.setSchoolRow(1);
               		   
        		   }else if("3".equals(greed)){
        			   /*====================================== 壹====================================*/
       			     GreedDto greedDto1=new GreedDto();
               		  //查询最高年级  
               			Map<String,Object>params9=new HashMap<String,Object>();
               			params9.put("discode",discode);
               			params9.put("cmis30id",cim);
               			params9.put("graduateyear",intyear);
               			List<Integer> classidlist2=findSteudentCountSum(params9);
   	          				    Map<String,Object>params4=new HashMap<String,Object>();
   			          			params4.put("discode",discode);
   			          			params4.put("cmis30id",cim);
   			          			params4.put("graduateyear",intyear);
   			          			String 	semesterid1=String.valueOf(intyear-1)+"1"; 
   		          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
   			          			params4.put("semesterid1",semesterid1);
   				          		params4.put("semesterid2",semesterid2);
   				          	    params4.put("subject_id",topic);
     			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
   			          			 TopicStatDto top =new TopicStatDto(); 
   	          				     top.setGreed("八年级");
   	          				   int er=listTopic.size();
   	          				   for(int z=0;z<er;z++)
   	          			        {  Integer StudenSum= classidlist2.get(0);
   	          			        	if(null!=StudenSum){
   	          			        	  top.setChinesecount(StudenSum);//总人数
   	          			        	  String ss=topic;
   	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
   	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
   	          			             top= apper(top,ss,apped,appedSum,StudenSum);
   	          			            }
   	          			        }
   	          			      greedDto1.getList().add(top);
   	          			      greedDto1.setGreedName(intyear);
   	         			      greedDto1.setGreedRow(1);
   	         			      FGT.getListGreedDto().add(greedDto1);
   	         			      FGT.setSchoolRow(1);
        		   }else if("4".equals(greed)){
        			   GreedDto greedDto1=new GreedDto();
                		  //查询最高年级  
                			Map<String,Object>params9=new HashMap<String,Object>();
                			params9.put("discode",discode);
                			params9.put("cmis30id",cim);
                			params9.put("graduateyear",intyear);
                			List<Integer> classidlist2=findSteudentCountSum(params9);
    	          				    Map<String,Object>params4=new HashMap<String,Object>();
    			          			params4.put("discode",discode);
    			          			params4.put("cmis30id",cim);
    			          			params4.put("graduateyear",intyear);
    			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
    		          			    String 	semesterid2=String.valueOf(intyear)+"2";
    			          			params4.put("semesterid1",semesterid1);
    				          		params4.put("semesterid2",semesterid2);
    				          	    params4.put("subject_id",topic);
      			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
    			          			 TopicStatDto top =new TopicStatDto(); 
    	          				     top.setGreed("九年级");
    	          				   int er=listTopic.size();
    	          				   for(int z=0;z<er;z++)
    	          			        {  Integer StudenSum= classidlist2.get(0);
    	          			        	if(null!=StudenSum){
    	          			        	  top.setChinesecount(StudenSum);//总人数
    	          			        	  String ss=topic;
    	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
    	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
    	          			             top= apper(top,ss,apped,appedSum,StudenSum);
    	          			            }
    	          			        }
    	          			      greedDto1.getList().add(top);
    	          			      greedDto1.setGreedName(intyear);
    	         			      greedDto1.setGreedRow(1);
    	         			      FGT.getListGreedDto().add(greedDto1);
    	         			      FGT.setSchoolRow(1);
        		   }//2017
        	   }else if(1==cha){

        		   //全部年
        		   if("0".equals(greed)){

                       /*----------------------------- 叁-------------------------------------*/
                       		  //查询最高年级  
        			           intyear=intyear+1;
                       			Map<String,Object> params1 =new HashMap<String,Object>();
                       			params1.put("discode",discode);
                       			params1.put("cmis30id",cim);
                       			params1.put("graduateyear",intyear);
                       			List<Integer> classidlist=findSteudentCountSum(params1);
                       			 //查询最高年级  各科目评价  
                       			GreedDto greedDto3=new GreedDto();
                       			for(int w=0;w<2;w++){
             	          				    Map<String,Object>params2=new HashMap<String,Object>();
             			          			params2.put("discode",discode);
             			          			params2.put("cmis30id",cim);
             			          			params2.put("graduateyear",intyear);
             			          			params2.put("graduateyear",intyear);
             			          			params2.put("subject_id",topic);
             			          		   if(w==1){
             			          				String 	semesterid1=String.valueOf(intyear-1)+"1"; 
             			          			    String 	semesterid2=String.valueOf(intyear-1)+"2";
             			          				params2.put("semesterid1",semesterid1);
             				          			params2.put("semesterid2",semesterid2);
             			          			}else{
             			          				String 	semesterid1=String.valueOf(intyear)+"1"; 
             			          			    String 	semesterid2=String.valueOf(intyear)+"2";
             			          				params2.put("semesterid1",semesterid1);
             				          			params2.put("semesterid2",semesterid2);
             			          			}
             			          			
             			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params2);
             			          			
             			          			TopicStatDto top =new TopicStatDto(); 
             			          			if(0==w){
             	          						 top.setGreed("七年级");
             	          					 }else if(1==w){
             	          						top.setGreed("八年级");
             	          					 }
             			          			 int qq=listTopic.size();
             	          			        for(int z=0;z<qq;z++)
             	          			        {  Integer StudenSum= classidlist.get(0);
             	          			        	if(null!=StudenSum){
             	          			        	top.setChinesecount(StudenSum);//总人数
             	          			        	 String ss=topic;
             	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
             	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
             	          			             top= apper(top,ss,apped,appedSum,StudenSum);
             	          			            }
             	          			        }
             	          			      greedDto3.getList().add(top);
                       			}
                  			      greedDto3.setGreedName(intyear);
                  			      greedDto3.setGreedRow(2);
                  			      FGT.getListGreedDto().add(greedDto3);
                  			      FGT.setSchoolRow(2);
                  			  
        		   }else if("2".equals(greed)){
        			   
                       /*====================================== 二 ====================================*/
        			   intyear=intyear+1; 
        			   GreedDto greedDto2=new GreedDto();
                 			Map<String,Object>params3=new HashMap<String,Object>();
                 			params3.put("discode",discode);
                 			params3.put("cmis30id",cim);
                 			params3.put("graduateyear",intyear);
                 			List<Integer> classidlist1=findSteudentCountSum(params3);	
     	          				    Map<String,Object>params4=new HashMap<String,Object>();
     			          			params4.put("discode",discode);
     			          			params4.put("cmis30id",cim);
     			          			params4.put("graduateyear",intyear);
     			          			/*params2.put("subject",subject);*/
     			          		   params4.put("subject_id",topic);
     			          				String 	semesterid1=String.valueOf(intyear-2)+"1"; 
     			          			    String 	semesterid2=String.valueOf(intyear-2)+"2";
     				          			params4.put("semesterid1",semesterid1);
     				          			params4.put("semesterid2",semesterid2);
     			          		   List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
     			          			TopicStatDto top =new TopicStatDto(); 
     	          					 top.setGreed("七年级");
     			          			  int ww=listTopic.size();
     			          			 for(int z=0;z<ww;z++)
     		          			        {  Integer StudenSum= classidlist1.get(0);
     		          			        	if(null!=StudenSum){
     		          			        	  top.setChinesecount(StudenSum);//总人数
     		          			        	  String ss=topic;
     		          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
     		          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
     		          			             top= apper(top,ss,apped,appedSum,StudenSum);
     		          			            }
     		          			        }
     	          			      greedDto2.getList().add(top);
               			          greedDto2.setGreedName(intyear);
         			              greedDto2.setGreedRow(1);
         			              FGT.getListGreedDto().add(greedDto2);
         			              FGT.setSchoolRow(1);
               		   
        		   }else if("3".equals(greed)){
        			   /*====================================== 壹====================================*/
        			   intyear=intyear+1;
        			   GreedDto greedDto1=new GreedDto();
               		  //查询最高年级  
               			Map<String,Object>params9=new HashMap<String,Object>();
               			params9.put("discode",discode);
               			params9.put("cmis30id",cim);
               			params9.put("graduateyear",intyear);
               			List<Integer> classidlist2=findSteudentCountSum(params9);
   	          				    Map<String,Object>params4=new HashMap<String,Object>();
   			          			params4.put("discode",discode);
   			          			params4.put("cmis30id",cim);
   			          			params4.put("graduateyear",intyear);
   			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
   		          			    String 	semesterid2=String.valueOf(intyear)+"2";
   			          			params4.put("semesterid1",semesterid1);
   				          		params4.put("semesterid2",semesterid2);
   				          	    params4.put("subject_id",topic);
     			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
   			          			 TopicStatDto top =new TopicStatDto(); 
   	          				     top.setGreed("八年级");
   	          				   int er=listTopic.size();
   	          				   for(int z=0;z<er;z++)
   	          			        {  Integer StudenSum= classidlist2.get(0);
   	          			        	if(null!=StudenSum){
   	          			        	  top.setChinesecount(StudenSum);//总人数
   	          			        	  String ss=topic;
   	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
   	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
   	          			             top= apper(top,ss,apped,appedSum,StudenSum);
   	          			            }
   	          			        }
   	          			      greedDto1.getList().add(top);
   	          			      greedDto1.setGreedName(intyear);
   	         			      greedDto1.setGreedRow(1);
   	         			      FGT.getListGreedDto().add(greedDto1);
   	         			      FGT.setSchoolRow(1);
        		   }
        	   
        	   }else if(2==cha){

    			   /*====================================== 壹====================================*/
    			   intyear=intyear+2;
    			   GreedDto greedDto1=new GreedDto();
           		  //查询最高年级  
           			Map<String,Object>params9=new HashMap<String,Object>();
           			params9.put("discode",discode);
           			params9.put("cmis30id",cim);
           			params9.put("graduateyear",intyear);
           			List<Integer> classidlist2=findSteudentCountSum(params9);
	          				    Map<String,Object>params4=new HashMap<String,Object>();
			          			params4.put("discode",discode);
			          			params4.put("cmis30id",cim);
			          			params4.put("graduateyear",intyear);
			          			String 	semesterid1=String.valueOf(intyear)+"1"; 
		          			    String 	semesterid2=String.valueOf(intyear)+"2";
			          			params4.put("semesterid1",semesterid1);
				          		params4.put("semesterid2",semesterid2);
				          	    params4.put("subject_id",topic);
 			          		List<ListTopicBenDto> listTopic=findCzApperSumTopic(params4);
			          			 TopicStatDto top =new TopicStatDto(); 
	          				     top.setGreed("七年级");
	          				   int er=listTopic.size();
	          				   for(int z=0;z<er;z++)
	          			        {  Integer StudenSum= classidlist2.get(0);
	          			        	if(null!=StudenSum){
	          			        	  top.setChinesecount(StudenSum);//总人数
	          			        	  String ss=topic;
	          			             Integer apped=listTopic.get(z).getStudentApperedTopiccount();//评价的人数
	          			             Integer appedSum= listTopic.get(z).getTopicApperSum();//总的评价书 
	          			             top= apper(top,ss,apped,appedSum,StudenSum);
	          			            }
	          			        }
	          			      greedDto1.getList().add(top);
	          			      greedDto1.setGreedName(intyear);
	         			      greedDto1.setGreedRow(1);
	         			      FGT.getListGreedDto().add(greedDto1);
	         			      FGT.setSchoolRow(1);
        	   }
           }
        	
          }
       
		return FGT;
//	初中四年制处理	
	  }else{
	      FistGzTopicDto FGT=new FistGzTopicDto();
		return FGT;
	  }
	}
    	// top评价对象  ss 科目  apped 已评价人数的数  appedSum 总的评价书
      	public  TopicStatDto apper(TopicStatDto top, String ss,Integer apped,Integer appedSum ,Integer StudenSum){
      		DecimalFormat df = new DecimalFormat("0.0");
      		 
      		
      		 if("1".equals(ss)){
	 		     top.setGovernmentgross(appedSum);//评价总数
         		 if(apped>0){
         			 top.setGovernmentcounted(apped);//已评价人数
         			 float a=(float)(StudenSum);
         		     float b=apped/a;
         			 top.setGovernmentper(df.format(b));//比例
         		 }else{  
         			top.setGovernmentcounted(0);
         		 }
         		//语文
      		 }else if("2".equals(ss)){//count(ala.appraisalid)su, count(distinct(ala.edu_id)) stedentsu
         		 top.setChinesegross(appedSum);//总评价数
         		 if(apped>0){
         			 top.setChinesecounted(apped);
         			 float a=(float)(StudenSum);
         		     float b=apped/a;
         			 top.setChineseper(df.format(b));
         		 }else{  
         			top.setChinesecounted(0);
         		 }
         		
         		//英语英语  
 			    }else if("4".equals(ss)){
 			    
 			    	top.setEnglishgross(appedSum);//总评价数
 	         		 if(apped>0){
 	         			 top.setEnglishcounted(apped);
 	         			 float a=(float)(StudenSum);
 	         		     float b=apped/a;
 	         			 top.setEnglishper(df.format(b));
 	         		 }else{  
 	         			top.setEnglishcounted(0);
 	         		 }
 			      
 			    	 //数学
 			    }else if("3".equals(ss)){
 			    	 top.setArithgross(appedSum);//总评价数
 	         		 if(apped>0){
 	         			 top.setArithcounted(apped);
 	         			 float a=(float)(StudenSum);
 	         		     float b=apped/a;
 	         			 top.setArithper(df.format(b));
 			    	}else{  
	          			top.setArithcounted(0);
	          		 }
	          	
 			    }else if("5".equals(ss)){
	          	   top.setHistorygross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setHistorycounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setHistoryper(df.format(b));//比例
	         		 }else{  
	         			top.setHistorycounted(0);
	         		 }
	          		 
 			    }else if("6".equals(ss)){
	          	   top.setGeographyross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setGeographycounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setGeographyper(df.format(b));//比例
	         		 }else{  
	         			top.setGeographycounted(0);
	         		 }
	          		 
 			    }else if("7".equals(ss)){
 			      //物理
	          		  top.setPhysicsross(appedSum);//评价总数
		         		 if(apped>0){
		         			 top.setPhysicscounted(apped);//已评价人数
		         			 float a=(float)(StudenSum);
		         		     float b=apped/a;
		         			 top.setPhysicsper(df.format(b));//比例
		         		 }else{  
		         			top.setPhysicscounted(0);
		         		 }
		          		 
 			    }else if("8".equals(ss)){
	          	    //化学
	          		  top.setChemistryross(appedSum);//评价总数
		         		 if(apped>0){
		         			 top.setChemistrycounted(apped);//已评价人数
		         			 float a=(float)(StudenSum);
		         		     float b=apped/a;
		         			 top.setChemistryper(df.format(b));//比例
		         		 }else{  
		         			top.setChemistrycounted(0);
		         		 }
		          		 
 			    }else if("9".equals(ss)){
 			 	 //生物
	          	  top.setBiologyross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setBiologycounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setBiologyper(df.format(b));//比例
	         		 }else{  
	         			top.setBiologycounted(0);
	         		 }
 			    }else if("14".equals(ss)){
 			  	//信息技术
	          	  top.setInfoross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setInfocounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setInfoper(df.format(b));//比例
	         		 }else{  
	         			top.setInfocounted(0);
	         		 }
	          	  
 			    }else if("10".equals(ss)){
 			      // 初中体育与健康
	          		top.setCurrencyross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setCurrencycounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setCurrencyper(df.format(b));//比例
	         		 }else{  
	         			top.setCurrencycounted(0);
	         		 }
	          	
 			    }else if("11".equals(ss)){
 			 	   //音乐
	          		top.setMusicross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setMusiccounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setMusicper(df.format(b));//比例
	         		 }else{  
	         			top.setMusiccounted(0);
	         		 }
 			    }else if("12".equals(ss)){
 					 //音乐
	          		top.setArtcross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setArtcounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setArtcper(df.format(b));//比例
	         		 }else{  
	         			top.setArtcounted(0);
	         		 }
 			    }else if("13".equals(ss)){
 			   	//劳动技术  housework   
	          		top.setHouseworkcross(appedSum);//评价总数
	         		 if(apped>0){
	         			 top.setHouseworkcounted(apped);//已评价人数
	         			 float a=(float)(StudenSum);
	         		     float b=apped/a;
	         			 top.setHouseworkcper(df.format(b));//比例
	         		 }else{  
	         			top.setHouseworkcounted(0);}
	         		 }else if("15".equals(ss)){
	         			//研究性学习 lab
	 	          		top.setLabcross(appedSum);//评价总数
	 	         		 if(apped>0){
	 	         			 top.setLabcounted(apped);//已评价人数
	 	         			 float a=(float)(StudenSum);
	 	         		     float b=apped/a;
	 	         			 top.setLabcper(df.format(b));//比例
	 	         		 }else{  
	 	         			top.setLabcounted(0);}
	 	         		 }else if("16".equals(ss)){
	 	         			//社区服务与社会实践  society
	 		          		top.setSocietycross(appedSum);//评价总数
	 		         		 if(apped>0){
	 		         			 top.setSocietycounted(apped);//已评价人数
	 		         			 float a=(float)(StudenSum);
	 		         		     float b=apped/a;
	 		         			 top.setSocietycper(df.format(b));//比例
	 		         		 }else{  
	 		         			top.setSocietycounted(0);}
	 		         		 }else if("17".equals(ss)){
	 		         			 //地方与校本课程 clime  
	 			          		top.setClimecross(appedSum);//评价总数
	 			         		 if(apped>0){
	 			         			 top.setClimecounted(apped);//已评价人数
	 			         			 float a=(float)(StudenSum);
	 			         		     float b=apped/a;
	 			         			 top.setClimecper(df.format(b));//比例
	 			         		 }else{  
	 			         			top.setClimecounted(0);}
	 			         		 }
      		 
      		return top;
          }
    	 
     //初中每届学生总数
    	public List<Integer> findSteudentCountSum(Map<String,Object> params1){
				try{
 				 //查询当前班级学生数
 				ISqlElement sqlElement=this.processSql(params1,"AreaStatExtImpl.findStudentCount.query");
 				List<Integer> classidlist =this.findList("AreaStatExtImpl.findStudentCount.query", params1, new RowMapper() {
 					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
 							Integer st=rs.getInt("num");
 						   return st;
 					}
 				});
 				return classidlist;
 			}catch(Exception e){
 				logger.error("findStudentCount(likeSchoolName,levelCode,discode)",e);
 				throw new ManagerException(e);
 			}
			}
       //查询初中详情
    	public  List<ListTopicBenDto> findCzApperSum(Map<String,Object> params2){
				try{
  				//调试时候用的接口，没用的时候，可以关闭或者注释掉
  				ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findCzTopicApper.query");
  				List<ListTopicBenDto> listTopic =this.findList("AreaStatExtImpl.findCzTopicApper.query", params2, new RowMapper() {
  					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
  						ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
  						listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
  						listTopicBenDto.setTopicApperSum(rs.getInt("su"));
  						listTopicBenDto.setSubject(rs.getString("subject"));
  						return listTopicBenDto;
  					}
  				});
  				return listTopic;
  			}catch(Exception e){
  				logger.error("czfindAllSchoolid(likeSchoolName,levelCode,discode)",e);
  				throw new ManagerException(e);
  			}
			
			}
    	//查询初中详情有科目
    	public  List<ListTopicBenDto> findCzApperSumTopic(Map<String,Object> params2){
    		try{
    			//调试时候用的接口，没用的时候，可以关闭或者注释掉
    			ISqlElement sqlElement=this.processSql(params2,"AreaStatExtImpl.findCzTopicApperTopic.query");
    			List<ListTopicBenDto> listTopic =this.findList("AreaStatExtImpl.findCzTopicApperTopic.query", params2, new RowMapper() {
    				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
    					ListTopicBenDto listTopicBenDto=new ListTopicBenDto();
    					listTopicBenDto.setStudentApperedTopiccount(rs.getInt("stedentsu"));
    					listTopicBenDto.setTopicApperSum(rs.getInt("su"));
    					return listTopicBenDto;
    				}
    			});
    			return listTopic;
    		}catch(Exception e){
    			logger.error("AreaStatExtImpl.findCzTopicApperTopic.query",e);
    			throw new ManagerException(e);
    		}
    		
    	}

}
