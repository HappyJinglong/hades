package com.flyrish.hades.webapp.action.areaStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.SchoolNameDto;
import com.flyrish.hades.dto.TemerDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.TwoColumnsDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class GzquAction extends BaseAction{
	
	@Spring
	public IAppraisalStaticsExt appraisalStaticsExt;
	/*要查询的二级栏目*/
	public String selectNumber;
	public String cmis30Id;
	public String levelcode;
	public String discode;
	public String notNull="333";
	public String campusId;
	public String stringTermId;
	public String  termId;
	public String[] termIds=new String[3];
	//单双判断
	public String dan;
	public long  cont6=0;
       /*  学士的历史学期*/
	public List<TermDto>  studeruid6=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>   uid6=new ArrayList<String>();
	public long  cont7=0;
	 /*  学士的历史学期*/
	public List<TermDto> studeruid7=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String> uid7=new ArrayList<String>();
	public long  cont8=0;
	 /*  学士的历史学期*/
	public List<TermDto>  studeruid8=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>  uid8=new ArrayList<String>();
	public long  cont9=0;
	 /*  学士的历史学期*/
	public List<TermDto>  studeruid9=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>  uid9=new ArrayList<String>();
	
	
	String vec[] = new String [4];
	String classid[] = new String [4];
	/*学制长度*/
	public String length="3";
	//学生人数
	public String contStuerder="0";
	//学校列表
	public List<String> listcmis30Id=new ArrayList<String>();
	//查询出来的学生列表
	Map<String,List<String>> sturderlist = new HashMap<String,List<String>>(); 
	/*要查询的二级栏目号的list集合*/
	public List<String> listNumber=new ArrayList<String>();
	/*历史学期*/
	public List<String> terms=new ArrayList<String>();
	/*所有历史学期*/
	public List<List<TermDto>> termsAll=new ArrayList<List<TermDto>>();
	/*要查询的数据*/
	public List<TwoColumnsDto> TwoColumnsDtoList=new ArrayList<TwoColumnsDto>();
	@Spring
	public ICzPlayAndHealthExt czplayAndHealthExt;
	public String str2s;
	public String str3s;
	public String str1s;
	public String schoolName;

	@Spring
	public ITermServiceExt termServiceExt;
	@Before
	public Object befor(HttpServletRequest req){
		 try {
		       this.getLoginInfo(req);
		       cmis30Id=userDto.getCmis30id();
		       levelcode=userDto.getLevelcode();
		       discode= userDto.getDiscode();
		       campusId=userDto.getCampuseId();
		       stringTermId =userDto.getTermId();
		} catch (Exception e) {
			throw new ManagerException(e);
		}
		return null;
	}
	
	@DefaultAction
	public Object serch(HttpSession session){
		
	/*	if("0".equals( selectNumber)){
			
		}else{
			
		}
		*/
		
		
		
		//计算学期
		Integer str=Integer.valueOf(termId.substring(0,4));//选了的
		Integer str1=Integer.valueOf(stringTermId.substring(0,4));//当前的
		str2s=String.valueOf(str1+1);
		str3s=String.valueOf(str1+2);
		str1s=String.valueOf(str1);
		Integer xue=str1-str;
		String stre=termId.substring(4,5);
		
		 switch (xue){ 
		  case 0 :
				  {
					  termIds[0]="3"+stre;
					  termIds[1]="2"+stre;
					  termIds[2]="1"+stre;
					  break;
				  }
		  case 1 :
		  { 
			  termIds[0]="2"+stre;
			  termIds[1]="1"+stre;
			  termIds[2]="0";
			  break;
		  }
		  case 2 :
		  {
			  termIds[0]="1"+stre;
			  termIds[1]="0";
			  termIds[2]="0";
			  break;
		  }
				  
		 }	  
	
		 
		 List<SchoolNameDto> listSchool=czplayAndHealthExt.findShool(schoolName,discode,userDto.getLevelcode());
		 
		 
	/*	 * @param cmis30Id 学校Id
		 * @param userType 用户类型(userDto.getUerRrealType())
		 * @param levelFlag 学段标识号(userDto.getLevelId())
		 * @param campusId 校区ID*/
		 listcmis30Id.add(cmis30Id);
		sturderlist =appraisalStaticsExt.querySchoolInfos(listcmis30Id, userDto.getUserRealType(),userDto.getLevelid(),campusId);//获取学生
		if(0<sturderlist.size()){
			  for (Map.Entry<String,List<String>> entry : sturderlist.entrySet()) {
				     String key=entry.getKey();
				     String[] aa = key.split("-");
				     String ss=aa[1];
				     List<String> ValueList =entry.getValue();
				     if("1".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont7+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid7.add(ValueList.get(z));
					    	 }
				    	 }
				    	 
				     }else if("2".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont8+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid8.add(ValueList.get(z));
					    	 }
				    	 }
				     }else if("3".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont9+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid9.add(ValueList.get(z));
					    	 }
				    	 }
				     }
			  }
			  
				listNumber.add("1010");
				listNumber.add("1020");
				listNumber.add("2010");
				listNumber.add("2020");
				listNumber.add("2030");
				listNumber.add("2040");
				listNumber.add("3010");
				listNumber.add("3020");
				listNumber.add("3030");
				listNumber.add("4010");
				listNumber.add("4020");
				listNumber.add("4030");
				listNumber.add("6010");
				listNumber.add("6020");
				listNumber.add("6030");
				listNumber.add("5010");
				listNumber.add("5020");
				listNumber.add("5050");
				listNumber.add("9010");
				listNumber.add("9020");
				listNumber.add("9030");
				listNumber.add("8020");
				listNumber.add("8040");
				listNumber.add("8010");
				listNumber.add("8050");
			           //栏目号
				for(int i=0;i<listNumber.size();i++){
				  TwoColumnsDto tw=new TwoColumnsDto();
				//2 B标识
				  if("2".equals((listNumber.get(i).substring(0,1)))){
					  if("2030".equals(listNumber.get(i))){
						 // <学校Id-年级号-学期,计数>
							serchTcher (tw);
							tw.setTwoColumns_11(listNumber.get(i));
							TwoColumnsDtoList.add(tw);
					  }else{
						 String ss= listNumber.get(i);
						 serch1(tw,ss);
						 tw.setTwoColumns_11(listNumber.get(i));
						 TwoColumnsDtoList.add(tw);
					  }
				  }else if("3".equals((listNumber.get(i).substring(0,1)))||"4".equals((listNumber.get(i).substring(0,1)))||"6".equals((listNumber.get(i).substring(0,1)))||"5".equals((listNumber.get(i).substring(0,1)))){
					   if("1".equals((listNumber.get(i).substring(2,3)))){
						   String ss= listNumber.get(i);
						   serch1(tw,ss);
						   tw.setTwoColumns_11(listNumber.get(i));
						   TwoColumnsDtoList.add(tw);
					   }else if("2".equals((listNumber.get(i).substring(2,3)))){
						   String ss= listNumber.get(i);
						   String ssz= listNumber.get(i).substring(0,1);
						   List<Map<String,List<String>>>  list=serchfen(tw,ss);
						   for(int iz=0;iz<list.size();iz++){
							   System.out.println(list.get(iz));
							   TwoColumnsDto twz=new TwoColumnsDto();
							   if(0==iz){
								   twz.setTwoColumns_11(ssz+"070");
								   twz.setsList(list.get(0));
								   TwoColumnsDtoList.add(twz);
							   }else if(1==iz){
								   twz.setTwoColumns_11(ssz+"080");
								   twz.setsList(list.get(1));
								   TwoColumnsDtoList.add(twz);
							   }else if(2==iz){
								   twz.setTwoColumns_11(ssz+"090");
								   twz.setsList(list.get(2));
								   TwoColumnsDtoList.add(twz);
							   }
						   }
						   
					   }else if("3".equals((listNumber.get(i).substring(2,3)))){
						   String ss= listNumber.get(i);
						   tw.setTwoColumns_11(ss);
						   serch1dai(tw,ss);
						   TwoColumnsDtoList.add(tw);
					   }else if("5".equals((listNumber.get(i).substring(2,3)))){
						   String ss= "3030";
						   tw.setTwoColumns_11(ss);
						   serch1dai(tw,ss);
						   TwoColumnsDtoList.add(tw);
					   }
				  }else if("1".equals((listNumber.get(i).substring(0,1)))){
					   if("1".equals((listNumber.get(i).substring(2,3)))){
						   String ss= listNumber.get(i);
						   tw.setTwoColumns_11(ss);
						   serch1(tw,ss);
						   TwoColumnsDtoList.add(tw);
					   }else{
						   String ss= listNumber.get(i);
						   List<Map<String,List<String>>>  list=serchfen1(tw,ss);
						   for(int iz=0;iz<list.size();iz++){
								   System.out.println(list.get(iz));
								   TwoColumnsDto twz=new TwoColumnsDto();
								   if(0==iz){
									   twz.setTwoColumns_11("1070");
									   twz.setsList(list.get(0));
									   TwoColumnsDtoList.add(twz);
								   }else if(1==iz){
									   twz.setTwoColumns_11("1080");
									   twz.setsList(list.get(1));
									   TwoColumnsDtoList.add(twz);
								   }
						   }
						  
					  
					   }
				  }
				  else if("9".equals((listNumber.get(i).substring(0,1)))){
					    String ss= listNumber.get(i);
					    tw.setTwoColumns_11(ss);
					   serch1dai(tw,ss);
					   TwoColumnsDtoList.add(tw);
				  }
				  else if("8".equals((listNumber.get(i).substring(0,1)))){
							 if("2".equals((listNumber.get(i).substring(2,3)))){
								   tw.setTwoColumns_11("8010");
								   serch1(tw,"8020");
								   TwoColumnsDtoList.add(tw);
								   
							 }if("4".equals((listNumber.get(i).substring(2,3)))){
								   String ss= "8040";
								   String ssz= "8";
								   List<Map<String,List<String>>>  list=serchfen(tw,ss);
								  
								   for(int iz=0;iz<list.size();iz++){
									   System.out.println(list.get(iz));
									   TwoColumnsDto twz=new TwoColumnsDto();
									   if(0==iz){
										   twz.setTwoColumns_11(ssz+"070");
										   twz.setsList(list.get(0));
										   TwoColumnsDtoList.add(twz);
									   }else if(1==iz){
										   twz.setTwoColumns_11(ssz+"080");
										   twz.setsList(list.get(1));
										   TwoColumnsDtoList.add(twz);
									   }else if(2==iz){
										   twz.setTwoColumns_11(ssz+"090");
										   twz.setsList(list.get(2));
										   TwoColumnsDtoList.add(twz);
									   }
								   }
								   
						 }
						if("1".equals((listNumber.get(i).substring(2,3)))){
							   String ss= "8010";
							   serch1dai(tw,ss);
							   tw.setTwoColumns_11("8030");
							   TwoColumnsDtoList.add(tw);
						}
						if("5".equals((listNumber.get(i).substring(2,3)))){
							   String ss= "9999";
							   serch1(tw,ss);
							   tw.setTwoColumns_11("8050");
							   TwoColumnsDtoList.add(tw);
						}
					  
				  }
				  
				  System.out.println(TwoColumnsDtoList);
				}
				
				
				
		
		 }
		   return "gzpopulation.jsp";
	   }
	
	
	
	
	/**
	 * 获取学校年级、班级班主任评语统计信息
	 * @param cmis30Ids 学校Id
	 * @param userType 用户类型
	 * @param levelFlag 学段标识号(userDto.getLevelId())
	 * @param campusId 校区ID
	 * @return 教务老师返回数据--Map<年级号-班级号-学期,计数> 
	 */
	
	public TwoColumnsDto serchTcher (TwoColumnsDto tw){
		 java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0");  
		List<String> lici30= new ArrayList<String>();
		  lici30.add(cmis30Id);
		  //学生已完成人数
		  List <String> contedsunm = new ArrayList<String>();
			 //百分百
		  List <String> contedpic = new ArrayList<String>();
			 //总数
		  List <String> psunm = new ArrayList<String>();
		  TemerDto emerDto =new TemerDto();
		  Integer sum1=0;
		  Integer sum2=0;
		  Integer sum3=0;
		  Map<String,Integer>  mapTher=appraisalStaticsExt.queryAssessStatics(lici30,userDto.getUserRealType(),userDto.getLevelcode(),campusId);
		  if(mapTher==null){
			
			  
		  }else{
		//  Map<年级号-班级号-学期,计数> 
		  for (Map.Entry<String,Integer> entry2 : mapTher.entrySet()) {
			  String ke= entry2.getKey();
			  String[] ss = ke.split("-");
			  //学期
			    String rr=ss[2];
			 //年级
			  Integer yy=Integer.valueOf(ss[0]);
		
			  switch (yy){ 
			  case 1:
					  {
						  if(termIds[2].equals(rr)){
							  sum1+=entry2.getValue();
						  }
						  break;
					  }
			  case 2:
			  {
				  if(termIds[1].equals(rr)){
					  sum2+=entry2.getValue();
				  }
				  break;
			  }
			  case 3:
			  {
				  if(termIds[0].equals(rr)){
					  sum3+=entry2.getValue();
				  }
				  break;
			  }
		 }
		    }
		  
		     if(sum3==0){
		    	 contedsunm.add("0");
		    	 contedpic.add("0");
		    	 psunm.add("0");
		     }else{
		    	 contedsunm.add(sum3+"");
		    	 psunm.add(sum3+"");
		    	 Double ii=sum3/(Double.valueOf(cont9));
		    	 contedpic.add(String.valueOf( df.format(ii)));
		     }
		     if(!("0".equals(termIds[1]))){
		    	   if(sum2==0){
				    	 contedsunm.add("0");
				    	 contedpic.add("0");
				    	 psunm.add("0");
				     }else{
				    	 contedsunm.add(sum2+"");
				    	 psunm.add(sum2+"");
				    	 Double ii=sum2/(Double.valueOf(cont8));
				    	 contedpic.add(String.valueOf( df.format(ii)));
				     }
		     }
		     if(!("0".equals(termIds[2]))){
		    	 if(sum1==0){
			    	 contedsunm.add("0");
			    	 contedpic.add("0");
			    	 psunm.add("0");
			     }else{
			    	 contedsunm.add(sum1+"");
			    	 psunm.add(sum1+"");
			    	 Double ii=sum1/(Double.valueOf(cont7));
			    	 contedpic.add(String.valueOf( df.format(ii)));
			     }
		     }
		     
		  }
		  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
		  mapr.put("contedsunm",contedsunm);//学生已完成人数
		  mapr.put("contedpic",contedpic);//%
		  mapr.put("psunm",psunm);//总数目
		  tw.setsList(mapr);
		  return  tw;
	
		 
	}
	
	

	public TwoColumnsDto serch1 (TwoColumnsDto tw,String ss){
		 
		//学生已完成人数
		  List <String> contedsunm = new ArrayList<String>();
			 //百分百
		  List <String> contedpic = new ArrayList<String>();
			 //总数
		  List <String> psunm = new ArrayList<String>();
				   
		     for(int d=0;d<3;d++){
					         String Termid=termIds[d];
							 if(!"0".equals(Termid)){
								  Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
								 if(0==d){
									 schoolI.put("3",uid9);
								 	}else if(1==d){
								 		schoolI.put("2",uid8);
								 	}else if(2==d){
								 		schoolI.put("1",uid7);
								 	}
								
								  Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
								  schoolInfos.put(cmis30Id, schoolI);
								//  {210003={8=[08004146, 08004787, 08004923,} 20151 32
								  Map<String,Map<String,List<Map<Object,Integer>>>>  maped=new HashMap<String, Map<String,List<Map<Object,Integer>>>>();
								  if("9999".equals(ss)){
									  maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,AlearnprocessAppraisalCacheDto.class); 
									 }else{
								        maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,AapprasialCacheDto.class);
									 }
								  // 93;;;;;;;;;;;;;;20142;;;;;;;;;;;;;;{210003={8=[{老师=1}, {老师=1}]}}
								 // Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
								 // {210003={8=[{}, {}]}}
								  for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
									 /* {8=[{}, {}]}*/
									  Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
									  for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
										 /* [{}, {}] List<Map<评价类型,评价数>*/
										  List<Map<Object,Integer>> dd= entry1.getValue();
										 
												
										    Map<Object,Integer> map1=dd.get(0);
										    if(null==map1||0==map1.size()){
										    	 psunm.add("0");
												 contedsunm.add("0");
												 contedpic.add("0");
										    	
										    }
										    for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
										    	/*//学生已完成人数
												  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
												 if(null!=entry2.getValue()){
													 System.out.println(entry2.getValue()+"..............................");
													 Integer it= entry2.getValue();
													 Double ii = null;
													 if(0==d){
														  ii=it/(Double.valueOf(cont9));
													 	}else if(1==d){
													 		  ii=it/(Double.valueOf(cont8));
													 	}else if(2==d){
													 		  ii=it/(Double.valueOf(cont7));
													 	}
													 java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
													 contedsunm.add(String.valueOf(it));
													 contedpic.add(String.valueOf( df.format(ii)));
												 }else{
													 
													 contedsunm.add("0");
													 contedpic.add("0");
												 }
													 //百分百
												  /*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
										    }
										    Map<Object,Integer> map2=dd.get(1);
										    for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
										    	  /* //总数目
												  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
										        if(null!=entry3.getKey()){
										        	psunm.add(String.valueOf(entry3.getValue())); 
										        }else{
										        	psunm.add("0");
										        }
										
										    }
										    
									  }
								  }
							
							 }
			            }
					
		    
		   
		  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
		  mapr.put("contedsunm",contedsunm);//学生已完成人数
		  mapr.put("contedpic",contedpic);//%
		  mapr.put("psunm",psunm);//总数目
		  tw.setsList(mapr); 
		  System.out.println(contedsunm);
		  System.out.println(contedpic);
		  System.out.println(psunm);
	  return tw;
	
	}
	//2号查询
	public List<Map<String,List<String>>> serchfen (TwoColumnsDto tw,String ss){
			//学生已完成人数
			List <String> contedsunm = new ArrayList<String>();
			//百分百
			List <String> contedpic = new ArrayList<String>();
			//百分百
			List <String> psunm = new ArrayList<String>();
			//学生已完成人数
			List <String> contedsunm1 = new ArrayList<String>();
			//百分百
			List <String> contedpic1 = new ArrayList<String>();
			//百分百
			List <String> psunm1 = new ArrayList<String>();
			//学生已完成人数
			List <String> contedsunm2 = new ArrayList<String>();
			//百分百
			List <String> contedpic2 = new ArrayList<String>();
				//百分百
			List <String> psunm2 = new ArrayList<String>();
			Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
			Map<String,List<String>> mapr1=new  HashMap<String, List<String>>();
			Map<String,List<String>> mapr2=new  HashMap<String, List<String>>();
			
		
			
				for(int d=0;d<3;d++){
					String Termid=termIds[d];
					 if(!"0".equals(Termid)){
						Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
						if(0==d){
							schoolI.put("3",uid9);
						}else if(1==d){
							schoolI.put("2",uid8);
						}else if(2==d){
							schoolI.put("1",uid7);
						}
						
						Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
						schoolInfos.put(cmis30Id, schoolI);
						//  {210003={8=[08004146, 08004787, 08004923,} 20151 32
						Map<String,Map<String,List<Map<Object,Integer>>>> maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,AapprasialCacheDto.class);
						// 93;;;;;;;;;;;;;;20142;;;;;;;;;;;;;;{210003={8=[{老师=1}, {老师=1}]}}
						// Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
						// {210003={8=[{}, {}]}}   {210003={2=[{3=5, 2=1, 5=1, 4=1}, {3=6, 2=2, 5=3, 4=2}]}}
						for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
							/* {8=[{}, {}]}*/
							Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
							for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
								/* [{}, {}] List<Map<评价类型,评价数>*/
								List<Map<Object,Integer>> dd= entry1.getValue();
								
								
								Map<Object,Integer> map1=dd.get(0);
								if(null==map1||0==map1.size()){
									psunm.add("0");
									contedsunm.add("0");
									contedpic.add("0");
									
									psunm1.add("0");
									contedsunm1.add("0");
									contedpic1.add("0");
									
									psunm2.add("0");
									contedsunm2.add("0");
									contedpic2.add("0");
									
								}else{
								String jz="jz";
								String tx="tx";
								String ls="ls";
								Integer su=0;
								double ffe=0.0;
								java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
								for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
									/*//学生已完成人数
												  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
									
									if(null!=entry2.getValue()){
										System.out.println(entry2.getValue()+"..............................");
										Integer it= entry2.getValue();
										Double ii = null;
										if(0==d){
											ii=it/(Double.valueOf(cont9));
										}else if(1==d){
											ii=it/(Double.valueOf(cont8));
										}else if(2==d){
											ii=it/(Double.valueOf(cont7));
										}
										
										if("2".equals(entry2.getKey())){
											 tx="txk";
											contedsunm.add(String.valueOf(it));
											contedpic.add(String.valueOf( df.format(ii)));	
										}else if("3".equals(entry2.getKey())||"4".equals(entry2.getKey())){
											ls="kls";
											 su+=it;
											 ffe+=ii;
										}else if("5".equals(entry2.getKey())){
											 jz="kjz";
											contedsunm2.add(String.valueOf(it));
											contedpic2.add(String.valueOf( df.format(ii)));	
										}
										
									
									}else{
										
										contedsunm.add("0");
										contedpic.add("0");
									}
									//百分百
									/*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
								}
								
								if("jz".equals(jz)){
									contedsunm2.add("0");
									contedpic2.add("0");	
								}else{
									jz="jz";
								}
								if("tx".equals(tx)){
									contedsunm.add("0");
									contedpic.add("0");	
								}else{
									tx="tx";     
								}
								if("ls".equals(ls)){
									contedsunm1.add("0");
									contedpic1.add("0");	
								}else{
									ls="ls";
									contedsunm1.add(String.valueOf(su));
									contedpic1.add(String.valueOf( df.format(ffe)));	
								}
								Map<Object,Integer> map2=dd.get(1);
								Integer sunNuber=0;
								for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
									/* //总数目
												  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
									if(null!=entry3.getKey()){
										if("2".equals(entry3.getKey())){
											psunm.add(String.valueOf(entry3.getValue())); 
											 tx="txf";
										}else if("3".equals(entry3.getKey())||"4".equals(entry3.getKey())){
											sunNuber+=entry3.getValue();
											ls="lsw";
										}else if("5".equals(entry3.getKey())){
											psunm2.add(String.valueOf(entry3.getValue())); 
										    jz="jz1";
											
										}
									
									}else{
										psunm.add("0");
									}
									
								}
								if("jz".equals(jz)){
									psunm2.add("0");
								}else{
									jz="jz";
								}
								if("tx".equals(tx)){
									psunm.add("0");
								}else{
									tx="tx";
								}
								if("ls".equals(ls)){
									psunm1.add("0");	
								}else{
									ls="ls";
									psunm1.add(String.valueOf(sunNuber)); 
								}
								}
							}
						}
					
						//tw.setsList(mapr); 
						System.out.println(maped);
						
					
				}
			}
		mapr.put("contedsunm",contedsunm);//学生已完成人数
		mapr.put("contedpic",contedpic);//%
		mapr.put("psunm",psunm);//总数目
		
		mapr1.put("contedsunm",contedsunm1);//学生已完成人数
		mapr1.put("contedpic",contedpic1);//%
		mapr1.put("psunm",psunm1);//总数目
		
		mapr2.put("contedsunm",contedsunm2);//学生已完成人数
		mapr2.put("contedpic",contedpic2);//%
		mapr2.put("psunm",psunm2);//总数目
		List<Map<String,List<String>>> lsit=new ArrayList<Map<String,List<String>>>();
		lsit.add(mapr);
		lsit.add(mapr1);
		lsit.add(mapr2);
		return lsit;
		
	}
	
	
	//2号查询
		public List<Map<String,List<String>>> serchfen1 (TwoColumnsDto tw,String ss){
		
				//学生已完成人数
				List <String> contedsunm = new ArrayList<String>();
				//百分百
				List <String> contedpic = new ArrayList<String>();
				//百分百
				List <String> psunm = new ArrayList<String>();
		
	
					
				//学生已完成人数
				List <String> contedsunm2 = new ArrayList<String>();
				//百分百
				List <String> contedpic2 = new ArrayList<String>();
					//百分百
				List <String> psunm2 = new ArrayList<String>();
				Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
			
				Map<String,List<String>> mapr2=new  HashMap<String, List<String>>();
				
		
			
					for(int d=0;d<3;d++){
						String Termid=termIds[d];
						 if(!"0".equals(Termid)){
							Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
							if(0==d){
								schoolI.put("3",uid9);
							}else if(1==d){
								schoolI.put("2",uid8);
							}else if(2==d){
								schoolI.put("1",uid7);
							}
							
							Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
							schoolInfos.put(cmis30Id, schoolI);
							//  {210003={8=[08004146, 08004787, 08004923,} 20151 32
							Map<String,Map<String,List<Map<Object,Integer>>>> maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,AapprasialCacheDto.class);
							// 93;;;;;;;;;;;;;;20142;;;;;;;;;;;;;;{210003={8=[{老师=1}, {老师=1}]}}
							// Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
							// {210003={8=[{}, {}]}}   {210003={2=[{3=5, 2=1, 5=1, 4=1}, {3=6, 2=2, 5=3, 4=2}]}}
							for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
								/* {8=[{}, {}]}*/
								Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
								for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
									/* [{}, {}] List<Map<评价类型,评价数>*/
									List<Map<Object,Integer>> dd= entry1.getValue();
									
									
									Map<Object,Integer> map1=dd.get(0);
									if(null==map1||0==map1.size()){
										psunm.add("0");
										contedsunm.add("0");
										contedpic.add("0");
										
										
										
										psunm2.add("0");
										contedsunm2.add("0");
										contedpic2.add("0");
										
									}else{
									String jz="jz";
									String tx="tx";
									String ls="ls";
									Integer su=0;
									double ffe=0.0;
									java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
									for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
										/*//学生已完成人数
													  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
										
										if(null!=entry2.getValue()){
											System.out.println(entry2.getValue()+"..............................");
											Integer it= entry2.getValue();
											Double ii = null;
											if(0==d){
												ii=it/(Double.valueOf(cont9));
											}else if(1==d){
												ii=it/(Double.valueOf(cont8));
											}else if(2==d){
												ii=it/(Double.valueOf(cont7));
											}
											
											if("1".equals(entry2.getKey())){
												 tx="txk";
												contedsunm.add(String.valueOf(it));
												contedpic.add(String.valueOf( df.format(ii)));	
											}else if("10".equals(entry2.getKey())||"14".equals(entry2.getKey())){
												ls="kls";
												 su+=it;
												 ffe+=ii;
											}else if("5".equals(entry2.getKey())){
												 jz="kjz";
												contedsunm2.add(String.valueOf(it));
												contedpic2.add(String.valueOf( df.format(ii)));	
											}
											
										
										}else{
											
											contedsunm.add("0");
											contedpic.add("0");
										}
										//百分百
										/*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
									}
									
									if("jz".equals(jz)){
										contedsunm2.add("0");
										contedpic2.add("0");	
									}else{
										jz="jz";
									}
									if("tx".equals(tx)){
										contedsunm.add("0");
										contedpic.add("0");	
									}else{
										tx="tx";     
									}
									
									Map<Object,Integer> map2=dd.get(1);
									Integer sunNuber=0;
									for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
										/* //总数目
													  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
										if(null!=entry3.getKey()){
											//psunm.add(String.valueOf(entry3.getValue())); 
										
											if("2".equals(entry3.getKey())){
												psunm.add(String.valueOf(entry3.getValue())); 
												 tx="txf";
											}else if("13".equals(entry3.getKey())||"14".equals(entry3.getKey())){
												sunNuber+=entry3.getValue();
												ls="lsw";
											}else if("5".equals(entry3.getKey())){
												psunm2.add(String.valueOf(entry3.getValue())); 
											    jz="jz1";
												
											}
										
										}else{
											psunm.add("0");
										}
										
									}
									if("jz".equals(jz)){
										psunm2.add("0");
									}else{
										jz="jz";
									}
									if("tx".equals(tx)){
										psunm.add("0");
									}else{
										tx="tx";
									}
									
									}
								}
							}
						
							//tw.setsList(mapr); 
							System.out.println(maped);
							
						}
					}
			
				
		
			mapr.put("contedsunm",contedsunm);//学生已完成人数
			mapr.put("contedpic",contedpic);//%
			mapr.put("psunm",psunm);//总数目
			
			mapr2.put("contedsunm",contedsunm2);//学生已完成人数
			mapr2.put("contedpic",contedpic2);//%
			mapr2.put("psunm",psunm2);//总数目
			
	
			
			
			List<Map<String,List<String>>> lsit=new ArrayList<Map<String,List<String>>>();
			
			lsit.add(mapr);
		
			lsit.add(mapr2);
			
			return lsit;
			
		}
		
	
	
    /*
     * 
     * 
     * 
     * 记录袋
	*/
	public TwoColumnsDto serch1dai (TwoColumnsDto tw,String ss){
		 
		//学生已完成人数
		  List <String> contedsunm = new ArrayList<String>();
			 //百分百
		  List <String> contedpic = new ArrayList<String>();
			 //总数
		  List <String> psunm = new ArrayList<String>();
		  //记录袋
		  List <String> psunmdai = new ArrayList<String>();
				   for(int d=0;d<3;d++){
					   String Termid=termIds[d];
						 if(!"0".equals(Termid)){
								  Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
								 if(0==d){
									 schoolI.put("3",uid9);
								 	}else if(1==d){
								 		schoolI.put("2",uid8);
								 	}else if(2==d){
								 		schoolI.put("1",uid7);
								 	}
								 
								  Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
								  schoolInfos.put(cmis30Id, schoolI);
								//  {210003={8=[08004146, 08004787, 08004923,} 20151 32
								  Map<String,Map<String,List<Map<Object,Integer>>>> maped= new  HashMap<String,Map<String,List<Map<Object,Integer>>>>();
							if("9".equals((ss.substring(0,1)))){
								  maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,ApracticesCacheDto.class);
							
							}else if("8".equals((ss.substring(0,1)))){
								   maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,AlearnprocessWorksCacheDto.class);
							}else {
								  maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,ArecordpackageCacheDto.class);
									
							}
								 
                               // 93;;;;;;;;;;;;;;20142;;;;;;;;;;;;;;{210003={8=[{老师=1}, {老师=1}]}}
								 // Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
								 // {210003={8=[{}, {}]}}
								  for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
									 /* {8=[{}, {}]}*/
									  Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
									  for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
										 /* [{}, {}] List<Map<评价类型,评价数>*/
										  List<Map<Object,Integer>> dd= entry1.getValue();
										 
												
										    Map<Object,Integer> map1=dd.get(0);
										    if(null==map1||0==map1.size()){
										    	 psunm.add("0");
												 contedsunm.add("0");
												 contedpic.add("0");
												 psunmdai.add("0");
										    }
										    for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
										    	/*//学生已完成人数
												  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
												 if(null!=entry2.getValue()){
													 System.out.println(entry2.getValue()+"..............................");
													 Integer it= entry2.getValue();
													 Double ii = null;
													 if(0==d){
														  ii=it/(Double.valueOf(cont9));
													 	}else if(1==d){
													 		  ii=it/(Double.valueOf(cont8));
													 	}else if(2==d){
													 		  ii=it/(Double.valueOf(cont7));
													 	}
													 java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
													 contedsunm.add(String.valueOf(it));
													 
													 contedpic.add(String.valueOf( df.format(ii)));
												 }else{
													 
													 contedsunm.add("0");
													 contedpic.add("0");
												 }
													 //百分百
												  /*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
										    }
										    Map<Object,Integer> map2=dd.get(1);
										     String biao="ji";
										    for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
										    	  /* //总数目
												  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
										        if(null!=entry3.getKey()){
														String sk=(String) entry3.getKey();
										        	 if(sk.contains("Attache"))
														{
										        		 	psunmdai.add(String.valueOf(entry3.getValue()));
										        		 	biao="fji";
														}else{
															psunm.add(String.valueOf(entry3.getValue())); 
														}
										        }else{
										        	psunm.add("0");
										        }
										
										    }
										    if("ji".equals(biao)){
										    	psunmdai.add("0");
										    }else{
										    	 biao="ji";
										    }
										    
									  }
								  }
								  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
								  mapr.put("contedsunm",contedsunm);//学生已完成人数
								  mapr.put("contedpic",contedpic);//%
								  mapr.put("psunm",psunm);//总数目
								  mapr.put("psunmdai",psunmdai);//总数目
								  tw.setsList(mapr); 
								  System.out.println(maped);
							 }
			            }
	  return tw;
	
	}
	
	/*
	 * 
	 * 
	 * 
	 * 记录袋
	 */
	public TwoColumnsDto serch1dai5 (TwoColumnsDto tw,String ss){
		
		//学生已完成人数
		List <String> contedsunm = new ArrayList<String>();
		//百分百
		List <String> contedpic = new ArrayList<String>();
		//总数
		List <String> psunm = new ArrayList<String>();
		//记录袋
		List <String> psunmdai = new ArrayList<String>();
		
		for(int z=0;z<termsAll.size();z++){
			List<TermDto>  stid=termsAll.get(z);
			
			if(0<stid.size()){
				for(int d=0;d<stid.size();d++){
					String Termid=String.valueOf(stid.get(d).getTermid());
					if(null!=Termid){
						Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
						if(0==z){
							schoolI.put("3",uid9);
						}else if(1==z){
							schoolI.put("2",uid8);
						}else if(2==z){
							schoolI.put("1",uid7);
						}
						
						Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
						schoolInfos.put(cmis30Id, schoolI);
						//  {210003={8=[08004146, 08004787, 08004923,} 20151 32
						Map<String,Map<String,List<Map<Object,Integer>>>> maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,ss,ArecordpackageCacheDto.class);
						// 93;;;;;;;;;;;;;;20142;;;;;;;;;;;;;;{210003={8=[{老师=1}, {老师=1}]}}
						// Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
						// {210003={8=[{}, {}]}}
						for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
							/* {8=[{}, {}]}*/
							Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
							for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
								/* [{}, {}] List<Map<评价类型,评价数>*/
								List<Map<Object,Integer>> dd= entry1.getValue();
								
								
								Map<Object,Integer> map1=dd.get(0);
								if(null==map1||0==map1.size()){
									psunm.add("0");
									contedsunm.add("0");
									contedpic.add("0");
									psunmdai.add("0");
								}
								for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
									/*//学生已完成人数
												  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
									if(null!=entry2.getValue()){
										System.out.println(entry2.getValue()+"..............................");
										Integer it= entry2.getValue();
										Double ii = null;
										if(0==z){
											ii=it/(Double.valueOf(cont9));
										}else if(1==z){
											ii=it/(Double.valueOf(cont8));
										}else if(2==z){
											ii=it/(Double.valueOf(cont7));
										}
										java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
										contedsunm.add(String.valueOf(it));
										
										contedpic.add(String.valueOf( df.format(ii)));
									}else{
										
										contedsunm.add("0");
										contedpic.add("0");
									}
									//百分百
									/*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
								}
								Map<Object,Integer> map2=dd.get(1);
								String biao="ji";
								for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
									/* //总数目
												  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
									if(null!=entry3.getKey()){
										String sk=(String) entry3.getKey();
										if(sk.contains("Attache"))
										{
											psunmdai.add(String.valueOf(entry3.getValue()));
											biao="fji";
										}else{
											psunm.add(String.valueOf(entry3.getValue())); 
										}
									}else{
										psunm.add("0");
									}
									
								}
								if("ji".equals(biao)){
									psunmdai.add("0");
								}else{
									biao="ji";
								}
								
							}
						}
						Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
						mapr.put("contedsunm",contedsunm);//学生已完成人数
						mapr.put("contedpic",contedpic);//%
						mapr.put("psunm",psunm);//总数目
						mapr.put("psunmdai",psunmdai);//总数目
						tw.setsList(mapr); 
						System.out.println(maped);
					}
				}
				
			}
			
			
		}
		
		return tw;
		
	}
	
	public  List<TwoColumnsDto> findhua(HttpSession session,String s){
		selectNumber=s;
		serch( session);
		return TwoColumnsDtoList;
	}
	
	
}
