package com.flyrish.hades.webapp.action.needs;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.Flags.Flag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;

import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AppraisalTypeDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.NeedAppraisalTypeDto;
import com.flyrish.hades.dto.NeedDataView;
import com.flyrish.hades.dto.SchoolStudents;
import com.flyrish.hades.dto.TeacherComments;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.dto.needsMap;
import com.flyrish.hades.dto.needsTable;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.IScoreExt;
import com.flyrish.hades.service.ext.SeniorExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class SeniorAction extends BaseAction{
	@Spring
	private SeniorExt seniorExt;
	
	@Spring
	private IAppraisalStaticsExt appraisalStaticsExt;
	
	public String levelcode;
	public String commonFuncId;
	private List<String> xnList;//届别    学年
	private Map<String,List<String>> eduMap;//Map<学校-年级-班级,List<eduId>> 获取对应学校每个年级、班级学生eduId
	private Integer eduSize1 = 0;
	private Integer eduSize2 = 0;
	private Integer eduSize3 = 0;
	private Integer dqxn;//当前学年
	private List<needsTable> ntList = new ArrayList<needsTable>();//届别 - 学生总人数 - 年级 -上下学期
	
	List<String> ntStrList = new ArrayList<String>();
	
	private List<NeedDataView> ndvList = new ArrayList<NeedDataView>();//必填表格数据组装------------------------------------
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DefaultAction
	public Object doShow(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		getLoginInfo(request);
		Integer levelCode=Integer.valueOf(userDto.getLevelcode());
		levelcode = levelCode==null?null:String.valueOf(levelCode);//高中2012003   初中2012002
		request.setAttribute("commonFuncId", commonFuncId);//5010
		String termName = userDto.getTermName();
		termName = termName.substring(5, termName.length());
		////***届学生***学期必填数据填写情况
		termName = termName.substring(0, 4) + "届学生" + termName.substring(6, termName.length()) + "必填数据填写情况";
		session.setAttribute("termName", termName);//2015-2016学年秋季学期
//		System.out.println(termName);
		List<AppraisalTypeDto> at = new ArrayList<AppraisalTypeDto>();//栏目
		if(2012003 == levelCode){
			at = seniorExt.queryAppraisalTypeDto();
			if(at != null){
				//学生总人数
				String cmis30 = userDto.getCmis30id();
				List<String> cmis30Ids = new ArrayList<String>();
				cmis30Ids.add(cmis30);
				//获取对应学校每个年级、班级学生eduId		@return Map<学校-年级-班级,List<eduId>>
				eduMap = appraisalStaticsExt.querySchoolInfos(cmis30Ids,"1","",null);
				List<SchoolStudents> sssList = new ArrayList<SchoolStudents>();
				if(eduMap != null){
					Set entries = eduMap.entrySet();
					if(entries != null){
						Iterator iterator = entries.iterator();
						while(iterator.hasNext()){
							Map.Entry entry = (Entry) iterator.next();
							String key = (String)entry.getKey();
							List<String> value = (List<String>)entry.getValue();
							SchoolStudents sss = new SchoolStudents();
							sss.setCmis30(key);
							sss.setEduIds(value);
							sssList.add(sss);
						}
					}
				}
				if(sssList != null){
					//获取市区级用户、班主任、教务老师界别信息     @return 返回学年
					String campuseId = userDto.getCampuseId();
					String termId = userDto.getTermId();//20152  sub(2015+1)
//					System.out.println(termId + "----termId");//20151----termId
					String dqjb = termId.substring(0,4);
					Integer dqjbInt = Integer.parseInt(dqjb);//当前届别
					dqjbInt+=1;//当前届别 需要加1
					String sxxq = termId.substring(4);//区分上下学期  --1为上学期，2为下学期
//					System.out.println(sxxq+"---sxxq");//1
					session.setAttribute("dqjb1", dqjbInt);
					session.setAttribute("dqjb2", dqjbInt+1);
					session.setAttribute("dqjb3", dqjbInt+2);
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat();
					String dstr = sdf.format(d);
					String dyear = dstr.substring(0,2);
					if(dyear != null && !"".equals(dyear)){
						dyear = 20 + dyear;
						if(dqjbInt == Integer.parseInt(dyear)){
							//当前学年为 dqjbInt  2016
							dqxn = dqjbInt;//高三
						}else if(dqjbInt < Integer.parseInt(dyear)){
							if(Integer.parseInt(dyear) - dqjbInt == 1 ){
								//当前学年为 dqjbInt + 1   2017
								dqxn = dqjbInt + 1;//高二
							}else if(Integer.parseInt(dyear) - dqjbInt == 2 ){
								//当前学年为 dqjbInt + 2   2018
								dqxn = dqjbInt + 2;//高一
							}
						}
					}
					xnList = appraisalStaticsExt.queryJB(levelCode.toString(), campuseId, "","1");
					for(int i = 0;i<sssList.size();i++){
						String cmis30sss = sssList.get(i).getCmis30();
						List<String> eduIdList = sssList.get(i).getEduIds();//拿到每个班级的人数
						String[] strArr = cmis30sss.split("-");
						String grade = strArr[1];
						if("1".equals(grade)){//拿到每个年级的学生人数
							eduSize1 += eduIdList.size();
						}else if("2".equals(grade)){
							eduSize2 += eduIdList.size();
						}else if("3".equals(grade)){
							eduSize3 += eduIdList.size();
						}
//						if(eduIdList != null){
//							eduSize = eduIdList.size();
//						}
//						System.out.println(cmis30sss+"---"+eduSize);
					}
					ntStrList = new ArrayList<String>();
					
					if(xnList != null){//[56118_2016, 56121_2017]
						for(int i = 0;i<xnList.size();i++){
							String s = xnList.get(i);
							Integer jb = Integer.parseInt(s.substring(6, 10));//2016
							if(jb.equals(dqxn)){
								//dqxn == eduSize3  124总人数  == 2016届   == 高三    sxxq  上下学期
								// 届别  总人数  年级   上下学期
								needsTable nt = new needsTable();
								nt.setJb(dqxn);
								nt.setNum(eduSize3);
								nt.setGrade(3);
								nt.setSemester(Integer.parseInt(sxxq));
								int sxNum = 6;
								if("1".equals(sxxq)){
									sxNum --;
								}
								for(int k = 0;k<sxNum;k++){
									ntStrList.add(eduSize3.toString());
								}
								if("1".equals(sxxq)){
									ntStrList.add("");
								}
								ntList.add(nt);
							}if(jb -  dqxn == 1){
								//dqxn == eduSize2  111总人数  == 2017届   == 高三    sxxq  上下学期
								needsTable nt = new needsTable();
								nt.setJb(dqxn+1);
								nt.setNum(eduSize2);
								nt.setGrade(2);
								nt.setSemester(Integer.parseInt(sxxq));
								int sxNum = 4;
								if("1".equals(sxxq)){
									sxNum --;
								}
								for(int k = 0;k<sxNum;k++){
									ntStrList.add(eduSize2.toString());
								}
								if("1".equals(sxxq)){
									ntStrList.add("");
									ntStrList.add("");
									ntStrList.add("");
								}else{
									ntStrList.add("");
									ntStrList.add("");
								}
								ntList.add(nt);
							}else if(jb -  dqxn == 2){
								needsTable nt = new needsTable();
								nt.setJb(dqxn+2);
								nt.setNum(eduSize1);
								nt.setGrade(1);
								nt.setSemester(Integer.parseInt(sxxq));
								int sxNum = 2;
								if("1".equals(sxxq)){
									sxNum --;
								}
								for(int k = 0;k<sxNum;k++){
									ntStrList.add(eduSize1.toString());
								}
								if("1".equals(sxxq)){
									ntStrList.add("");
									ntStrList.add("");
									ntStrList.add("");
									ntStrList.add("");
								}else{
									ntStrList.add("");
									ntStrList.add("");
									ntStrList.add("");
								}
								ntList.add(nt);
							}
//							System.out.println(s);//56118_2016
						}
					}
					int size = ntStrList.size();
					if(size < 18){
						for(int g = 0;g<18 - size;g++){
							ntStrList.add("");
						}
					}
					request.setAttribute("ntStrList", ntStrList);//总人数
				}
				
				List<needsMap> strList = new ArrayList<needsMap>();
				for(int m = 0;m<sssList.size();m++){
					String cmiszz = sssList.get(m).getCmis30();
					String[] str = cmiszz.split("-");
					String njh = str[1];
					Integer strSize = strList.size();
					if(strSize == 0){
						needsMap nmap = new needsMap();
						nmap.setCmis30(njh);
						nmap.setEduIds(sssList.get(m).getEduIds());
						strList.add(nmap);
					}else{
						Integer pd = 0;
						List<String> addEdus = new ArrayList<String>();
						Integer size = 0;
						for(int n =0;n<strSize;n++){
							String c30 = strList.get(n).getCmis30();
							if(c30.equals(njh)){//如果相等就是在原本的基础上添加   --- 不相等就是增加一条
								pd = 1;
								addEdus = strList.get(n).getEduIds();//原本有的班级学生ID
								size = n ;
							}
						}
						if(pd == 1){
//							addEdus;//原本有的班级学生ID
							List<String> strEdu2 = sssList.get(m).getEduIds();//需要加上的学生ID
							List<String> strArr = new ArrayList<String>();
							for(int s = 0;s<addEdus.size();s++){
								strArr.add(addEdus.get(s));
							}
							for(int s = 0;s<strEdu2.size();s++){
								strArr.add(strEdu2.get(s));
							}
							strList.get(size).setEduIds(strArr);
						}else{//不相等就是增加一条
							needsMap nmap = new needsMap();
							nmap.setCmis30(njh);
							List<String> strEdus = sssList.get(m).getEduIds();
							nmap.setEduIds(strEdus);
							strList.add(nmap);
						}
					}
				}
				if(strList != null){
					List<String> cpSum = new ArrayList<String>();
					List<String> leve = new ArrayList<String>();
					List<String> cpSum2 = new ArrayList<String>();
					List<String> leve2 = new ArrayList<String>();
					List<String> cpSum3 = new ArrayList<String>();
					List<String> leve3 = new ArrayList<String>();
					List<String> cpSum4 = new ArrayList<String>();
					List<String> leve4 = new ArrayList<String>();
					List<String> cpSum5 = new ArrayList<String>();
					List<String> leve5 = new ArrayList<String>();
					List<String> cpSum6 = new ArrayList<String>();
					List<String> leve6 = new ArrayList<String>();
					List<String> cpSum7 = new ArrayList<String>();
					List<String> leve7 = new ArrayList<String>();
					List<String> cpSum8 = new ArrayList<String>();
					List<String> leve8 = new ArrayList<String>();
					List<String> cpSum9 = new ArrayList<String>();
					List<String> leve9 = new ArrayList<String>();
					List<String> cpSum10 = new ArrayList<String>();
					List<String> leve10 = new ArrayList<String>();
					List<String> cpSum11 = new ArrayList<String>();
					List<String> leve11 = new ArrayList<String>();
					List<String> cpSum12 = new ArrayList<String>();
					List<String> leve12 = new ArrayList<String>();
					List<String> cpSum13 = new ArrayList<String>();
					List<String> leve13 = new ArrayList<String>();
					for(int i = 0;i<strList.size();i++){
						Map<String,Map<String,List<String>>> schoolInfos = new HashMap<String,Map<String,List<String>>>();
						String cmis30Id = userDto.getCmis30id();
						Map<String,List<String>> map = new HashMap<String,List<String>>();
						String dxnj = strList.get(i).getCmis30();
						Integer dxSum = strList.get(i).getEduIds().size();
						map.put(dxnj, strList.get(i).getEduIds());
						schoolInfos.put(cmis30Id, map);
						//高一上学期 11  高二上学期21 高三上学期31 高三下学期32
						Class clazz = AapprasialCacheDto.class;
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "1010", clazz);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "1010", clazz);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "1010", clazz);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "1010", clazz);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "1010", clazz);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "1010", clazz);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum.add("");
									 leve.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "1020", clazz);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "1020", clazz);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "1020", clazz);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "1020", clazz);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "1020", clazz);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "1020", clazz);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum2.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve2.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum2.add("");
									 leve2.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "2010", clazz);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "2010", clazz);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "2010", clazz);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "2010", clazz);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "2010", clazz);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "2010", clazz);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum3.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve3.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum3.add("");
									 leve3.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "2040", clazz);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "2040", clazz);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "2040", clazz);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "2040", clazz);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "2040", clazz);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "2040", clazz);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("5");
								 if(sum != null){
									 cpSum5.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve5.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum5.add("");
									 leve5.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "3010", clazz);
									System.out.println("---"+cheList+"---"+3010);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum6.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve6.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum6.add("");
									 leve6.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "8020", clazz);
									System.out.println("---"+cheList+"---"+8020);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum7.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve7.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum7.add("");
									 leve7.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "4020", clazz);
									System.out.println("---"+cheList+"---"+4020);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("2");
								 if(sum != null){
									 cpSum8.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve8.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum8.add("");
									 leve8.add("");
								 }
							}
						}
						Class clazz2 = ArecordpackageCacheDto.class;
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "6030", clazz2);
									System.out.println("---"+cheList+"---"+6030);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum10.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve10.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum10.add("");
									 leve10.add("");
								 }
							}
						}
						Class clazz3 = ApracticesCacheDto.class;
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "9010", clazz3);
									System.out.println("---"+cheList+"---"+9010);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum11.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve11.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum11.add("");
									 leve11.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "7020", clazz);
									System.out.println("---"+cheList+"---"+7020);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum12.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve12.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum12.add("");
									 leve12.add("");
								 }
							}
						}
						for(int t = 0;t<6;t++){
							String strt = ntStrList.get(t);
							if(!"".equals(strt) || strt != null){
								Map<String,Map<String,List<Map<Object,Integer>>>> cheList = new HashMap<String,Map<String,List<Map<Object,Integer>>>>();
								if(t == 0){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "11", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}else if(t == 1){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "12", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}else if(t == 2){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "21", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}else if(t == 3){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "22", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}else if(t == 4){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "31", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}else if(t == 5){
									cheList = appraisalStaticsExt.queryRecordStaticsInChache(schoolInfos, "32", "7050", clazz);
									System.out.println("---"+cheList+"---"+7050);
								}
//								2016---21---{280177={2=[{}, {}]}}
								 Map<String, List<Map<Object, Integer>>> mapList = cheList.get(userDto.getCmis30id());
								 List<Map<Object, Integer>> listMap = new ArrayList<Map<Object, Integer>>();
								 listMap = mapList.get(dxnj);//年级
								 Map<Object, Integer> mapoi = listMap.get(1);
								 Integer sum = mapoi.get("1");
								 if(sum != null){
									 cpSum13.add(sum.toString());
									 NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
									 numberFormat.setMaximumFractionDigits(2);  
							         String result = numberFormat.format((float) sum / (float) dxSum * 100);
							         leve13.add(result);
//							         System.out.println("num1和num2的百分比为:" + result + "%");  
								 }else{
									 cpSum13.add("");
									 leve13.add("");
								 }
							}
						}
					}
					List<String> cmList = new ArrayList<String>();
					cmList.add(userDto.getCmis30id());
					Map<String, Integer> bzrs = appraisalStaticsExt.queryAssessStatics(cmList, userDto.getUserRealType(), "",null);//班主任评语
					List<TeacherComments> tcs = new ArrayList<TeacherComments>();
					
					if(bzrs != null){
						Set entries = bzrs.entrySet();
						if(entries != null){
							Iterator iterator = entries.iterator();
							while(iterator.hasNext()){
								Map.Entry entry = (Entry) iterator.next();
								String key = (String)entry.getKey();
								Integer value = (Integer) entry.getValue();
								TeacherComments tc = new TeacherComments();
								tc.setSums(key);
								tc.setJsSum(value.toString());
								tcs.add(tc);
							}
						}
					}
					
					if(tcs != null){
						String tid = userDto.getTermId();
						String tid2 = tid.substring(0, 4);//2015
						Integer tid2Int = Integer.parseInt(tid2);//当前年级 2015
						tid2Int = tid2Int - 2;
						String jb = "3";
						for(int y=0;y<18;y++){
							String xq = tid2Int+ "1";//xq = (tid2Int - 1) + "1";  20131
							
							if(y == 1){
								xq = tid2Int+ "2";//20132
							}
							if(y == 2){
								xq = (tid2Int + 1)+ "1";//20141
							}
							if(y == 3){
								xq = (tid2Int + 1)+ "2";//20142
							}
							if(y == 4){
								xq = (tid2Int + 2)+ "1";//20151
							}
							if(y == 5){
								xq = (tid2Int + 2)+ "2";//20152
							}
							if(y == 6){
								jb = "2";
								xq = tid2Int+ "1";
							}
							if(y > 6 ){
								if(y/6 == 0){
									xq = tid2Int+ "1";
								}
								if(y/6 == 1){
									xq = tid2Int+ "2";
								}
								if(y/6 == 2){
									xq = (tid2Int + 1)+ "1";
								}
								if(y/6 == 3){
									xq = (tid2Int + 1)+ "2";
								}
								if(y/6 == 4){
									xq = (tid2Int + 2)+ "1";
								}
								if(y/6 == 5){
									xq = (tid2Int + 2)+ "2";
								}
							}
							if(y == 12){
								jb = "1";
							}
							for(int q = 0; q<tcs.size();q++){
								//当前届别的  2016 高一 上学期 -- 1- -20151
								String sss = tcs.get(q).getSums();//3-1-20131 --年级号-班级号-学期
								String[] strArr = sss.split("-");
								String s = strArr[2];
								String s1 = strArr[0];//3
//								String s2 = s.substring(5);//1  -- 上下学期
//								String s3 = s.substring(0, 4);//2013 -- 学年	
								Integer sm = null;
								if(jb.equals(s1)){
									for(int j = 0;j<strList.size();j++){
										String cs = strList.get(j).getCmis30();
										if(cs.equals(jb)){
											sm = strList.get(j).getEduIds().size();
											break;
										}
									}
									if(xq.equals(s)){
										String js = tcs.get(q).getJsSum();
										int jsint = Integer.parseInt(js);
										int size = cpSum4.size();
										
										if(y >= size){
											if(y == size){
												cpSum4.add("");
												leve4.add("");
											}
										}
										String cp = cpSum4.get(y);
										if(cp == null || "".equals(cp)){
											cpSum4.set(y, jsint+"");
											
											NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
											numberFormat.setMaximumFractionDigits(2);  
									        String result = numberFormat.format((float) jsint / (float) sm * 100);
									        leve4.set(y, result);
//									        leve4.add(result);
										}else{
											String cp0 = cpSum4.get(y);
											int cpint0 = Integer.parseInt(cp0);
											cpint0 = cpint0 + jsint;
											cpSum4.set(y, cpint0+"");
											
											NumberFormat numberFormat = NumberFormat.getInstance(); //添加完成百分比
											numberFormat.setMaximumFractionDigits(2);  
									        String result = numberFormat.format((float) cpint0 / (float) sm * 100);
									        leve4.set(y, result);
										}
									}
								}
							}
							cpSum4.add("");
							leve4.add("");
						}
					}
					
//{3-1-20131=9, 3-5-20151=1, 3-1-20132=9, 3-4-20132=9, 3-4-20131=9, 3-2-20121=1, 3-2-20132=6, 3-3-20131=9, 3-2-20131=6, 3-3-20132=9}
//					System.out.println(bzrs);
					if(cpSum.size() == 12){
						for(int i = 0;i<6;i++){
							cpSum.add("");
							leve.add("");
							cpSum2.add("");
							leve2.add("");
							cpSum3.add("");
							leve3.add("");
							cpSum5.add("");
							leve5.add("");
							cpSum6.add("");
							leve6.add("");
							cpSum7.add("");
							leve7.add("");
							cpSum8.add("");
							leve8.add("");
							cpSum10.add("");
							leve10.add("");
							cpSum11.add("");
							leve11.add("");
							cpSum12.add("");
							leve12.add("");
							cpSum13.add("");
							leve13.add("");
						}
					}
					request.setAttribute("cpSum", cpSum);//完成 总人数 1010
					request.setAttribute("leve", leve);//完成 总人数 百分比
					
					request.setAttribute("cpSum2", cpSum2);//完成 总人数 1020
					request.setAttribute("leve2", leve2);//完成 总人数 百分比
					
					request.setAttribute("cpSum3", cpSum3);//完成 总人数 2010
					request.setAttribute("leve3", leve3);//完成 总人数 百分比
					cpSum4.remove(18);
					leve4.remove(18);
					request.setAttribute("cpSum4", cpSum4);//完成 总人数 2030
					request.setAttribute("leve4", leve4);//完成 总人数 百分比
					
					request.setAttribute("cpSum5", cpSum5);//完成 总人数 2040
					request.setAttribute("leve5", leve5);//完成 总人数 百分比
					
					request.setAttribute("cpSum6", cpSum6);//完成 总人数 3010
					request.setAttribute("leve6", leve6);//完成 总人数 百分比
					
					request.setAttribute("cpSum7", cpSum7);//完成 总人数 8020
					request.setAttribute("leve7", leve7);//完成 总人数 百分比
					
					request.setAttribute("cpSum8", cpSum8);//完成 总人数 4020
					request.setAttribute("leve8", leve8);//完成 总人数 百分比
					
					request.setAttribute("cpSum10", cpSum10);//完成 总人数 6030
					request.setAttribute("leve10", leve10);//完成 总人数 百分比
					
					request.setAttribute("cpSum11", cpSum11);//完成 总人数 9010
					request.setAttribute("leve11", leve11);//完成 总人数 百分比
					
					request.setAttribute("cpSum12", cpSum12);//完成 总人数 7020
					request.setAttribute("leve12", leve12);//完成 总人数 百分比
					
					request.setAttribute("cpSum13", cpSum13);//完成 总人数 7050
					request.setAttribute("leve13", leve13);//完成 总人数 百分比
				}
			}
			session.setAttribute("at", at);//栏目信息
			return "senior.jsp";//高中
		}else{
			session.setAttribute("at", at);//栏目信息
			return "junior.jsp";//初中
		}
		
	}
}
