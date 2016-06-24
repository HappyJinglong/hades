package com.flyrish.hades.webapp.action.areaStat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.TemerDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.TwoColumnsDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.ICzPlayAndHealthExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class PopulationAction extends BaseAction{
	
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
	//单双判断
	public String dan;
	
	public long  cont6;
       /*  学士的历史学期*/
	public List<TermDto>  studeruid6=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>   uid6=new ArrayList<String>();
	
	
	public long  cont7;
	 /*  学士的历史学期*/
	public List<TermDto> studeruid7=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String> uid7=new ArrayList<String>();
	
	
	public long  cont8;
	 /*  学士的历史学期*/
	public List<TermDto>  studeruid8=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>  uid8=new ArrayList<String>();
	
	
	public long  cont9;
	 /*  学士的历史学期*/
	public List<TermDto>  studeruid9=new ArrayList<TermDto>();
	 /*一个年级学生的id*/
	public List<String>  uid9=new ArrayList<String>();
	
	public String  cont9s;
	public String  cont8s;
	public String  cont7s;
	public String  cont6s;
	String tm[] = new String [4];
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
	public Integer str1;
	public Integer str2;
	public Integer str3;
	public Integer str4;

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
		String ss2=userDto.getCampuseId();
		String ss1=userDto.getLevelid();
		length =czplayAndHealthExt.findGreedLenth(ss2,ss1);//学制长度
		List queryJB = appraisalStaticsExt.queryJB(userDto.getLevelcode(), "", "", "");
		  	Integer str=Integer.valueOf(termId.substring(0,4));//选了的
		  	Integer	 str9=Integer.valueOf(stringTermId.substring(0,4));//当前的
		  	
		       Integer xue=str9-str;
				
		listcmis30Id.add(cmis30Id);
		sturderlist =appraisalStaticsExt.querySchoolInfos(listcmis30Id, userDto.getUserRealType(), userDto.getLevelid(),campusId);//获取学生
		
		if(0<sturderlist.size()){
			  for (Map.Entry<String,List<String>> entry : sturderlist.entrySet()) {
				     String key=entry.getKey();
				     String[] aa = key.split("-");
				     String ss=aa[1];
				     List<String> ValueList =entry.getValue();
				     if("7".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont7+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid7.add(ValueList.get(z));
					    	 }
				    	 }
				    	 
				     }else if("8".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont8+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid8.add(ValueList.get(z));
					    	 }
				    	 }
				     }else if("9".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont9+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid9.add(ValueList.get(z));
					    	 }
				    	 }
				     }else if("6".equals(ss)){
				    	 if(0<ValueList.size()){
				    		 cont6+=ValueList.size();
					    	 for(int z=0;z<ValueList.size();z++){
					    		 uid6.add(ValueList.get(z));
					     }
				     }
				     }
			  }
			  
			  
			  if("3".equals(length)){
					str1=Integer.valueOf((String) queryJB.get(0));
					str2=Integer.valueOf((String) queryJB.get(1));
					str3=Integer.valueOf((String) queryJB.get(2));
					switch (xue){ 
					  case 0 :
							  {
								  tm[0]=termId;
								  tm[1]=termId;
								  tm[2]=termId;
								   cont9s=String.valueOf(cont9);
								   cont8s=String.valueOf(cont8);
								   cont7s=String.valueOf(cont7);
								  break;
							  }
					  case 1 :
					  { 
						  tm[0]=termId;
						  tm[1]=termId;
						  tm[2]="0";
						  cont9s=String.valueOf(cont9);
						   cont8s=String.valueOf(cont8);
						  break;
					  }
					  case 2 :
					  {
						  tm[0]=termId;
						  tm[1]="0";
						  tm[2]="0";
						  cont9s=String.valueOf(cont9);
						  break;
					  }
				 }	 
		
				}
				if("4".equals(length)){
					str1=Integer.valueOf((String) queryJB.get(0));
					str2=Integer.valueOf((String) queryJB.get(1));
					str3=Integer.valueOf((String) queryJB.get(2));
					str4=Integer.valueOf((String) queryJB.get(3));
					switch (xue){ 
					case 0 :
					{
						tm[0]=termId;
						tm[1]=termId;
						tm[2]=termId;
						tm[3]=termId;
						 cont9s=String.valueOf(cont9);
						 cont8s=String.valueOf(cont8);
						 cont7s=String.valueOf(cont6);
						 cont6s=String.valueOf(cont6);
						break;
					}
					case 1 :
					{ 
						tm[0]=termId;
						tm[1]=termId;
						tm[2]=termId;
						tm[3]="0";
						cont9s=String.valueOf(cont9);
						 cont8s=String.valueOf(cont8);
						 cont7s=String.valueOf(cont6);
					
						break;
					}
					case 2 :
					{
						tm[0]=termId;
						tm[1]=termId;
						tm[2]="0";
						tm[3]="0";
						cont9s=String.valueOf(cont9);
						 cont8s=String.valueOf(cont8);
						
						break;
					}
					case 3 :
					{
						tm[0]=termId;
						tm[1]="0";
						tm[2]="0";
						tm[3]="0";
						cont9s=String.valueOf(cont9);
					
						break;
					}
					}	  
					
				}
			  
			  
			  
						listNumber.add("11");
						listNumber.add("12");
						listNumber.add("21");
						listNumber.add("22");
						listNumber.add("23");
						listNumber.add("31");
						listNumber.add("32");
						listNumber.add("33");
						listNumber.add("34");
						listNumber.add("35");
					listNumber.add("41");
						listNumber.add("45");
						listNumber.add("42");
						listNumber.add("46");
						listNumber.add("43");
						
						for(int i=5;i<10;i++){
							listNumber.add(i+"1");
							listNumber.add(i+"2");
							listNumber.add(i+"3");
							listNumber.add(i+"4");
							listNumber.add(i+"5");
						}
			           //栏目号
				for(int i=0;i<listNumber.size();i++){
					//学生已完成人数
				  List <String> contedsunm = new ArrayList<String>();
					 //百分百
				  List <String> contedpic = new ArrayList<String>();
					 //总数
				  List <String> psunm = new ArrayList<String>();
				  
				  List <String> psunmdai = new ArrayList<String>();
				 
				  TwoColumnsDto tw=new TwoColumnsDto();
				  
				
				  
				  if("65".equals(listNumber.get(i))){
					  Map<String,String>  ssf= czplayAndHealthExt.findth(cmis30Id,discode,userDto.getLevelid(),termId);
					  if(null==ssf){
						 if( "4".equals(length)){
							 if(0==xue){
								 contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
							 }else if(1==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
							 }else if(2==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								
							 }else if(3==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
							 }
							  
						 }else if("3".equals(length)){
							 if(0==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
							 }else if(1==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
								
							 }else if(2==xue){
								  contedsunm.add("0");
								  contedpic.add("0");
								  psunm.add("0");
							 }
						 }
					
					  }else{   
						   String i1 = null;
						   String i2  = null;
						   String i3  = null;
						   String i4 = null;
						     double ii;
						     java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0");
						   for (Map.Entry<String,String>  entry : ssf.entrySet()) {
							      Integer hh= Integer.valueOf(entry.getKey());
							      if(str1==hh){
							    	  i1=entry.getValue();
							      }else if(str2==hh){
							    	  i2=entry.getValue();
							      }else if(str3==hh){
							    	  i3=entry.getValue();
							      }else if(str4==hh){
							    	  i4=entry.getValue();
							      }
								
						  }
						   
						   
						   if( "4".equals(length)){
								 if(0==xue){
									 if(null== i1){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									 if(null== i2){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont8));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									 
									 if(null== i3){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont7));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									 if(null== i4){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont6));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
								 }else if(1==xue){
									 if(null== i1){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										 if(null== i2){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont8));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										 
										 if(null== i3){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont7));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
									
								 }else if(2==xue){
									 if(null== i1){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										 if(null== i2){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont8));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
								 }else if(3==xue){
									 if(null== i1){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
								 }
								  
							 }else if("3".equals(length)){
								 if(0==xue){
									 if(null== i1){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									 if(null== i2){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont8));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									 
									 if(null== i3){
									  contedsunm.add("0");
									  contedpic.add("0");
									  psunm.add("0");
									 }else{
										 ii=Integer.valueOf(i1)/(Double.valueOf(cont7));
										 contedsunm.add(String.valueOf(i1));
										 contedpic.add(String.valueOf( df.format(ii*100))+"%");
									 }
									
								 }else if(1==xue){
									 if(null== i1){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										 if(null== i2){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont8));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										 
									
									
								 }else if(2==xue){
									 if(null== i1){
										  contedsunm.add("0");
										  contedpic.add("0");
										  psunm.add("0");
										 }else{
											 ii=Integer.valueOf(i1)/(Double.valueOf(cont9));
											 contedsunm.add(String.valueOf(i1));
											 contedpic.add(String.valueOf( df.format(ii*100))+"%");
										 }
										
								 }
							 }  
					  }
					  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
					  mapr.put("contedpic",contedpic);//%
					  mapr.put("psunm",psunm);//总数目
					  mapr.put("contedsunm",contedsunm);//学生已完成人数
					  tw.setsList(mapr); 
                      TwoColumnsDtoList.add(tw);
					  tw.setTwoColumns_11(listNumber.get(i));
				  }else{
					  
				   if("45".equals(listNumber.get(i)) ||"42".equals(listNumber.get(i)) ||"46".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i))){
					   if("45".equals(listNumber.get(i))){
						   tw.setTwoColumns_11("42");
					   }else if("42".equals(listNumber.get(i))){
						   tw.setTwoColumns_11("43");
					   }else if("46".equals(listNumber.get(i))){
						   tw.setTwoColumns_11("44");
					   }else if("43".equals(listNumber.get(i))){
						   tw.setTwoColumns_11("45");
					   }
				   }else{
					   tw.setTwoColumns_11(listNumber.get(i));
				   }
				    Integer integerlength=Integer.valueOf(length); 
				    for(int d=0;d<integerlength;d++){
										 String Termid=tm[d];
										 if(!"0".equals(Termid)){
										  Map<String,List<String>> schoolI = new HashMap<String,List<String>>();
										 if(0==d){
											 schoolI.put("9",uid9);
												if(0==uid9.size()){
													  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
													  contedsunm.add("0");
													  contedpic.add("0");
													  psunm.add("0");
													  mapr.put("contedpic",contedpic);//%
													  mapr.put("psunm",psunm);//总数目
													  mapr.put("contedsunm",contedsunm);//学生已完成人数
													  if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
														  psunmdai.add("0");
														  mapr.put("psunmdai",psunmdai);
													  	}
													  tw.setsList(mapr); 
													  continue;
												 }
										 	}else if(1==d){
										 		schoolI.put("8",uid8);
										 		if(0==uid8.size()){
													  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
													  contedsunm.add("0");
													  contedpic.add("0");
													  psunm.add("0");
													  mapr.put("contedpic",contedpic);//%
													  mapr.put("psunm",psunm);//总数目
													  mapr.put("contedsunm",contedsunm);//学生已完成人数
													  if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
														  psunmdai.add("0");
														  mapr.put("psunmdai",psunmdai);
													  	}
													  tw.setsList(mapr); 
													  continue;
												 }
										 	}else if(2==d){
										 		schoolI.put("7",uid7);
										 		if(0==uid7.size()){
													  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
													  contedsunm.add("0");
													  contedpic.add("0");
													  psunm.add("0");
													  mapr.put("contedpic",contedpic);//%
													  mapr.put("psunm",psunm);//总数目
													  mapr.put("contedsunm",contedsunm);//学生已完成人数
													  if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
														  psunmdai.add("0");
														  mapr.put("psunmdai",psunmdai);
													  	}
													  tw.setsList(mapr); 
													  continue;
												 }
										 	}else if(3==d){
										 		schoolI.put("6",uid6);
										 		if(0==uid6.size()){
													  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
													  contedsunm.add("0");
													  contedpic.add("0");
													  psunm.add("0");
													  mapr.put("contedpic",contedpic);//%
													  mapr.put("psunm",psunm);//总数目
													  mapr.put("contedsunm",contedsunm);//学生已完成人数
													  if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
														  psunmdai.add("0");
														  mapr.put("psunmdai",psunmdai);
													  	}
													  tw.setsList(mapr); 
													  continue;
												 }
										 	}
										 
										  Map<String, Map<String,List<String>>> schoolInfos =new  HashMap<String, Map<String,List<String>>>(); 
										  schoolInfos.put(cmis30Id, schoolI);
										  Map<String,Map<String,List<Map<Object,Integer>>>> maped = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos,Termid,listNumber.get(i),PartInfoCacheDto.class);
										  for (Map.Entry<String,Map<String,List<Map<Object,Integer>>>>  entry : maped.entrySet()) {
											  Map<String,List<Map<Object,Integer>>> mk=entry.getValue();
											  for (Map.Entry<String,List<Map<Object,Integer>>>  entry1 : mk.entrySet()) {
												  List<Map<Object,Integer>> dd= entry1.getValue();
												 
														
												    Map<Object,Integer> map1=dd.get(0);
												    if(null==map1||0==map1.size()){
												    	 psunm.add("0");
														 contedsunm.add("0");
														 contedpic.add("0");
														/* if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
															   psunmdai.add("0");
														  	}*/
												    }
												    for (Map.Entry<Object,Integer> entry2 : map1.entrySet()) {
												    	/*//学生已完成人数
														  List <TemerDto> contedsunm = new ArrayList<TemerDto>();*/
														 if(null!=entry2.getValue()){
															
															 Integer it= entry2.getValue();
															 Double ii = null;
															 if(0==d){
																  ii=it/(Double.valueOf(cont9));
															 	}else if(1==d){
															 		  ii=it/(Double.valueOf(cont8));
															 	}else if(2==d){
															 		  ii=it/(Double.valueOf(cont7));
															 	}else if(3==d){
															 		 ii=it/(Double.valueOf(cont6));
															 	}
															 java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.0"); 
															 contedsunm.add(String.valueOf(it));
															 contedpic.add(String.valueOf( df.format(ii*100))+"%");
														 }else{
															 
															 contedsunm.add("0");
															 contedpic.add("0");
														 }
															 //百分百
														  /*  List <TemerDto> contedpic = new ArrayList<TemerDto>();*/
												    }
												    Map<Object,Integer> map2=dd.get(1);
												   String ff="ii";
												    if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
												    	
												    	for (Map.Entry<Object,Integer> entry3 : map2.entrySet()) {
													    	  /* //总数目
															  List <TemerDto> psunm = new ArrayList<TemerDto>();*/
													        if(null!=entry3.getKey()){
													        	String ff1=(String) entry3.getKey();
													        	if(ff1.contains("Attache"))
																{
																	psunmdai.add(String.valueOf(entry3.getValue()));
																	ff="fji";
																}else{
																	psunm.add(String.valueOf(entry3.getValue())); 
																}
													        }
												    	}
												    	if("ii".equals(ff)){
												    		psunmdai.add("0");
												    	}else{
												    		ff="ii";
												    	}
												    }else{
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
										  Map<String,List<String>> mapr=new  HashMap<String, List<String>>();
										  mapr.put("contedpic",contedpic);//%
										  mapr.put("psunm",psunm);//总数目
										  mapr.put("contedsunm",contedsunm);//学生已完成人数
										  if("35".equals(listNumber.get(i)) ||"43".equals(listNumber.get(i)) ||"55".equals(listNumber.get(i)) || "75".equals(listNumber.get(i))||"82".equals(listNumber.get(i))||"84".equals(listNumber.get(i))){
											   mapr.put("psunmdai",psunmdai);
										  	}
										  System.out.println(psunmdai);
										  tw.setsList(mapr); 
										
									 
									 }
					            }
						
				  TwoColumnsDtoList.add(tw);
				
				}
				
				
				}
		 }
		  

		   return "population.jsp";
	   }
}
