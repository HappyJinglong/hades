package com.flyrish.hades.webapp.action.monitorstatictics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.Before;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ExportMonitorStatictics;
import com.flyrish.hades.common.TemplateExport;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AlearnprocessAppraisalCacheDto;
import com.flyrish.hades.dto.AlearnprocessWorksCacheDto;
import com.flyrish.hades.dto.ApersonalityCacheDto;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.ArecordpackageCacheDto;
import com.flyrish.hades.dto.PartInfoCacheDto;
import com.flyrish.hades.dto.TermDto;
import com.flyrish.hades.dto.UserDto;
import com.flyrish.hades.exception.ForceException;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ITermServiceExt;
import com.flyrish.hades.service.ext.IMonitorStaticticsExt;
import com.flyrish.hades.service.ext.ITotalStaticsExt;

public class MonitorStaticticsAction extends TemplateExport {

	@Spring
	public IAppraisalStaticsExt appraisalStaticsExt;

	@Spring
	public IMonitorStaticticsExt monitorStaticticsExt;

	@Spring
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;

	public Integer levelcode;

	public List<String> queryJB;

	public List<String> classs;

	// 学期集合<学期标识号_学期名称>
	public List<TermDto> terms;

	@Spring
	public ITermServiceExt termServiceExt;

	public String gradeid;

	public String termid;

	public String classid;

	public String gradeName;
	
	public String termName;
	
	public String className;
	
	public String caches_string = "[]";

	public List<Map<String, ?>> caches;
	
	private String filepath;

	private UserDto userDto;

	private String filename;

	public int gradeyear;


	public Integer getLevelcode() {
		return levelcode;
	}

	public void setLevelcode(Integer levelcode) {
		this.levelcode = levelcode;
	}

	@Before
	public Object doBefore(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		userDto = getLoginInfo(request);
		return null;
	}

	@DefaultAction
	public Object doSkip(HttpServletRequest request, HttpSession session) {
		UserDto user = getLoginInfo(request);
		queryJB = appraisalStaticsExt.queryJB(user.getLevelid(), user.getLevelcode(),
				user.getCampuseId(),
				user.getUsertype().equals("1502006") ? user.getTeacherid() : null, "1");
		if (queryJB != null&&queryJB.size()>0) {
			gradeid = queryJB.get(0).split("_")[0];
			for (String jb : queryJB) {
				String[] split = jb.split("_");
				if (split[0].equals(gradeid)) {
					gradeyear = Integer.parseInt(split[1]);
					break;
				}
			}
		}
		classs = appraisalStaticsExt.queryClass(gradeid,
				user.getUserRealType().equals("1502006") ? user.getTeacherid() : null);
		if (classs != null && !classs.isEmpty()) {
			classid = classs.get(0).split("_")[0];
			terms = termServiceExt.queryHighSchoolTerms(classid,
					user.getLevelcode());
			if(null != terms){
			   Collections.reverse(terms);
			   termid = String.valueOf(terms.get(0).getTermid());
			}
		}
		if (user.getLevelcode().equals("2012002")){
			return "monitor_staticitcs_cz.jsp";
		}else{
			return "monitor_staticitcs_gz.jsp";
		}
	}

	/**
	 * 初中监控统计
	 * */
	public Object searchStaticticsDataForCZ(HttpServletRequest request,
			HttpServletResponse response) {

		queryJuniorData(request);
		return "monitor_staticitcs_cz.jsp";
	}

	/**
	 * 高中监控统计
	 * */
	public Object searchStaticticsDataForGZ(HttpServletRequest request,
			HttpServletResponse response) {

		querySeniorData(request);
		return "monitor_staticitcs_gz.jsp";
	}

	public Integer queryHealthData(Integer levelCode, String termid,
			String classid, String cmis30id, String discode, String gradeid) {
		return monitorStaticticsExt.queryHeathData(levelCode, termid, classid,
				cmis30id, discode, gradeid);
	}
	
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response){
		try {
			redisServiceExt.save(dStatus, "0");
			filename = gradeName+"届"+className+termName+"数据监控统计.xls"; 
			if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(userDto.getLevelcode()))
			{
				filepath = "SeniorMonitorStatics";
				InputExcel(request,filepath);
				sheet.getRow(0).getCell(0).setCellValue(gradeName+"届"+className+termName+"数据监控统计");
				sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
				SeniorFillDataInExcel(request);
			}else if(Constant.DICT_TYPE_LEVELCODE_CZSTR.equals(userDto.getLevelcode())){
				filepath ="JuniorMonitorStatics";
				InputExcel(request,filepath);
				sheet.getRow(0).getCell(0).setCellValue(gradeName+"届"+className+termName+"数据监控统计");
				sheet.getRow(0).getCell(0).setCellStyle(titleCellStyle);
				JuniorFillDataInExcel(request);
			}
			OutputExcel(response, filename);
			redisServiceExt.save(dStatus, "1");
		} catch (ForceException e) {
			try {
				redisServiceExt.save(dStatus, "2");
			} catch (Exception e1) {
				logger.error("redisServiceExt.save(String,Object)", e1);
			}
			logger.error("exportExcel(HttpServletRequest,HttpServletResponse)", e);
		}
	}
	private void SeniorFillDataInExcel(HttpServletRequest request)
	{
		querySeniorData(request);
		HSSFRow Row;
		int rowStart = 2;
		int cellStart1 = 5;
		int cellEnd = 8;
		String[] detailData;
		for(Map<String,?> SeniorData : caches)
		{
			
			for(Map.Entry<String, ?> mapData : SeniorData.entrySet())
			{
				if(mapData.getValue() instanceof List)
				{
					List<Map<String, String[]>> newCache = (List<Map<String, String[]>>) mapData.getValue();
					for(Map<String,String[]> otherData : newCache)
					{
						for(Map.Entry<String, String[]> data : otherData.entrySet())
						{
							Row = createCell(rowStart++, cellStart1, cellEnd);
							detailData = (String[]) data.getValue();
							int cellStart = 5;
							for(String Detaildata : detailData)
							{
								Row.getCell(cellStart++).setCellValue(Detaildata);
							}
						}
					}
				}else{
					Row = createCell(rowStart++, cellStart1, cellEnd);
					detailData = (String[]) mapData.getValue();
					int cellStart = 5;
					for(String data : detailData)
					{
						Row.getCell(cellStart++).setCellValue(data);
					}
				}
			}
		}
	}
	private void JuniorFillDataInExcel(HttpServletRequest request)
	{
		queryJuniorData(request);
		HSSFRow Row;
		int rowStart = 2;
		int cellStart1 = 6;
		int cellEnd = 9;
		String[] detailData;
		if(null!=caches && caches.size()>0){
			for(Map<String,?> JuniorData : caches)
			{
				Row = createCell(rowStart++, cellStart1, cellEnd);
				for(Map.Entry<String, ?> mapData : JuniorData.entrySet())
				{
					detailData = (String[]) mapData.getValue();
					int cellStart = 6;
					for(String data : detailData)
					{
						Row.getCell(cellStart++).setCellValue(data);
					}
				}
			}
		}
	}
	
	private void querySeniorData(HttpServletRequest request)
	{
		UserDto user = getLoginInfo(request);
		
			List<String> cmis30ids = new ArrayList<String>();
			cmis30ids.add(user.getCmis30id());
			String usertype = user.getUsertype();
			Map<String, List<String>> schoolInfos = appraisalStaticsExt
					.querySchoolInfos_new(cmis30ids,
							user.getUserRealType(),user.getLevelid(),user.getCampuseId());
			if(null==schoolInfos||schoolInfos.isEmpty())
				return;
			queryJB = appraisalStaticsExt.queryJB(user.getLevelid(),user.getLevelcode(),
					user.getCampuseId(),
					usertype.equals("1502006") ? user.getTeacherid() : null,
					"1");
			int gradeyear = 0;
			if (queryJB != null&&queryJB.size()>0) {
				for (String jb : queryJB) {
					String[] split = jb.split("_");
					if (split[0].equals(gradeid)) {
						gradeyear = Integer.parseInt(split[1]);
						break;
					}
				}
			}
			classs = appraisalStaticsExt.queryClass(gradeid,
					user.getUserRealType().equals("1502006") ? user.getTeacherid() : null);
			int currentclass = 0;
			if (classs != null) {
				for (String clazz : classs) {
					String[] split = clazz.split("_");
					if (split[0].equals(classid)) {
						currentclass = Integer.parseInt(split[1]);
						break;
					}
				}
			}
			if (classs != null && !classs.isEmpty()) {
				String selectClassid = classs.get(0).split("_")[0];
				terms = termServiceExt.queryHighSchoolTerms(selectClassid,
						user.getLevelcode());
				Collections.reverse(terms);
			}
			String format = new SimpleDateFormat("yyyy").format(new Date());
			int currentyear = Integer.parseInt(format);
			List<String> list = schoolInfos.get(user.getCmis30id() + "-"
					+ (3 - (gradeyear - currentyear)) + "-"+currentclass+"-"+ className);
			if(list!=null&&list.size()==1&&list.get(0)=="0000")
				   return;
			String[] tpi = { "1010", "1020", "2010", "2020", "2030", "2040",
					"3010", "3020", "3030", "8020", "8010","9999", "8040", "4010",
					"4020", "4030", "5010", "5020", "5050", "6010", "6020",
					"6030", "9010", "9020", "9030", "7010", "7020", "7030",
					"7050", "7040" };
			caches = new ArrayList();
			if (list != null)
				for (int i = 0; i < tpi.length; i++) {
					if (tpi[i].equals("2030")) {
						Map<String, Integer> queryAssessStatics = appraisalStaticsExt
								.queryAssessStatics(cmis30ids,
										user.getUserRealType(), user.getLevelid(),user.getCampuseId());
						Integer integer = null;
						if (null != queryAssessStatics)
						{
							integer = queryAssessStatics
									.get((3 - (gradeyear - currentyear)) + "-"
											+ currentclass + "-" 
											+ String.valueOf(gradeyear-(4-Integer.parseInt(termid.substring(0,1))))+termid.substring(1));
						}
						
						Integer countNum = null==integer?0:integer;
						Map<String, String[]> statistics = new HashMap<String, String[]>();
						String[] st = { String.valueOf(list.size()), String.valueOf(countNum),
								String.valueOf(list.size() - countNum),
										String.valueOf(Math.round(countNum * 1000 / list.size())/10.0)};
						statistics.put(tpi[i], st);
						caches.add(statistics);
					} else {
						Class<?> clazz = AapprasialCacheDto.class;
						if (tpi[i].equals("3030") || tpi[i].equals("4030")
								|| tpi[i].equals("6030")) {
							clazz = ArecordpackageCacheDto.class;
						} else if (tpi[i].equals("9010")
								|| tpi[i].equals("9020")
								|| tpi[i].equals("9030")) {
							clazz = ApracticesCacheDto.class;
						} else if (tpi[i].equals("8010")) {
							clazz = AlearnprocessWorksCacheDto.class;
						} else if (tpi[i].equals("7010")) {
							clazz = ApersonalityCacheDto.class;
						}
						Map<Object, Integer> cache = appraisalStaticsExt
								.queryRecordStaticsInChache(list, termid,
										tpi[i], clazz);

						if (cache == null)
							continue;
						Map statistics = new HashMap();

						if (tpi[i].equals("3020") || tpi[i].equals("4020")
								|| tpi[i].equals("5020")
								|| tpi[i].equals("6020")
								|| tpi[i].equals("7030")
								|| tpi[i].equals("8040")) {

							Integer[] t = { 2, 3, 5 };
							Integer integer = 0;
							List other = new ArrayList();
							for (Integer key : t) {
								Map<String, String[]> newCache = new HashMap<String, String[]>();
								integer = cache.get(key.toString()) == null ? 0
										: cache.get(key.toString());
								if(key == 3)
								{
									Integer MasterNum = null==cache.get("4")?0:cache.get("4");
									integer +=MasterNum;
								}

								String[] st = { String.valueOf(list.size()), String.valueOf(integer),
										String.valueOf(list.size() - integer),
												String.valueOf(Math.round(integer * 1000 / list.size())/10.0)};
								newCache.put(key.toString(), st);
								other.add(newCache);
							}
							statistics.put(tpi[i], other);

						}else if(tpi[i].equals("1020")){
							Integer[] t = { 1, 5};
							Integer integer = 0;
							List other = new ArrayList();
							for(Integer key : t)
							{
								Map<String,String[]> newCache = new HashMap<String, String[]>();
								integer = cache.get(key.toString()) == null ? 0
										: cache.get(key.toString());
								String[] st = { String.valueOf(list.size()), String.valueOf(integer),
										String.valueOf(list.size() - integer),
												String.valueOf(Math.round(integer * 1000 / list.size())/10.0)};
								newCache.put(key.toString(), st);
								other.add(newCache);
							}
							statistics.put(tpi[i], other);
						} else if (tpi[i].equals("5050")) {
							Integer integer = queryHealthData(
									Integer.parseInt(user.getLevelcode()),
									termid, classid, user.getCmis30id(),
									user.getDiscode(), gradeid);
							Integer countNum = null==integer?0:integer;
							String[] st = { String.valueOf(list.size()), String.valueOf(countNum),
									String.valueOf(list.size() - countNum),
											String.valueOf(Math.round(countNum * 1000 / list.size())/10.0) };
							statistics.put(tpi[i], st);
						} else {

							Integer[] t = { 1, 2, 3, 4, 5 };
							Integer integer = 0;
							if(cache.size()==0)
							{
								String[] st = { String.valueOf(list.size()), String.valueOf(integer),
										String.valueOf(list.size() - integer),
												String.valueOf(Math.round(integer * 1000 / list.size())/10.0) };
								statistics.put(tpi[i], st);
								caches.add(statistics);
								continue;
							}
							for (Integer key : t) {
								for(Map.Entry<Object, Integer> cacheMap : cache.entrySet()){
									if(key.equals(Integer.parseInt((String)cacheMap.getKey())))
									{
										integer = cache.get(key.toString()) == null ? 0
												: cache.get(key.toString());
										String[] st = { String.valueOf(list.size()), String.valueOf(integer),
												String.valueOf(list.size() - integer),
														String.valueOf(Math.round(integer * 1000 / list.size())/10.0) };
										statistics.put(tpi[i], st);
									}
								}
							}
						}

						caches.add(statistics);
					}
				}
		caches_string = JSONArray.fromObject(caches).toString();
	}
	private void queryJuniorData(HttpServletRequest request)
	{
		UserDto user = getLoginInfo(request);
			List<String> cmis30ids = new ArrayList<String>();
			cmis30ids.add(user.getCmis30id());
			String usertype = user.getUsertype();
			Map<String, List<String>> schoolInfos = appraisalStaticsExt
					.querySchoolInfos_new(cmis30ids,
							user.getUserRealType(),
							user.getLevelid(),user.getCampuseId());
			if(null==schoolInfos||schoolInfos.isEmpty())
				return;
			queryJB = appraisalStaticsExt.queryJB(user.getLevelid(),user.getLevelcode(),
					user.getCampuseId(),
					usertype.equals("1502006") ? user.getTeacherid() : null,
					"1");
			int gradeyear = 0;
			if (queryJB != null) {
				for (String jb : queryJB) {
					String[] split = jb.split("_");
					if (split[0].equals(gradeid)) {
						gradeyear = Integer.parseInt(split[1]);
						break;
					}
				}
			}
			classs = appraisalStaticsExt.queryClass(gradeid,
					user.getUserRealType().equals("1502006") ? user.getTeacherid() : null);
			int currentclass = 0;
			if (classs != null) {
				for (String clazz : classs) {
					String[] split = clazz.split("_");
					if (split[0].equals(classid)) {
						currentclass = Integer.parseInt(split[1]);
						break;
					}
				}
			}
			if (classs != null && !classs.isEmpty()) {
				String selectClassid = classs.get(0).split("_")[0];
				terms = termServiceExt.queryHighSchoolTerms(selectClassid,
						user.getLevelcode());
				Collections.reverse(terms);
			}
			String format = new SimpleDateFormat("yyyy").format(new Date());
			int currentyear = Integer.parseInt(format);
			List<String> list = schoolInfos.get(user.getCmis30id() + "-"
					+ (9 - (gradeyear - currentyear)) + "-" +currentclass+"-"+ className);
			if(list!=null&&list.size()==1&&list.get(0)=="0000")
			   return;
			String[] tpi = { "11", "12", "21", "22", "23", "31", "32", "33",
					"34", "35", "41", "44", "45", "46", "43", "51", "52",
					"53", "54", "55", "61", "62", "63", "64", "65", "71", "72",
					"73", "74", "75", "81", "82", "83", "84", "85", "91", "92",
					"93", "94", "95" };
			caches = new ArrayList();
			if (list != null)
				for (int i = 0; i < tpi.length; i++) {
					if (tpi[i].equals("22")) {
						Map<String, Integer> queryAssessStatics = appraisalStaticsExt
								.queryAssessStatics(cmis30ids,
										user.getUserRealType(),
										user.getLevelid(),user.getCampuseId());
						Integer integer = null;
						if (null != queryAssessStatics)
						{
							integer = queryAssessStatics
									.get((9 - (gradeyear - currentyear)) + "-"
											+ currentclass + "-" + termid);
						}
						Map<String, String[]> statistics = new HashMap<String, String[]>();
						Integer countNum = null==integer?0:integer;
						String[] st = { String.valueOf(list.size()),  String.valueOf(countNum),
								 String.valueOf(list.size() - countNum),
										 String.valueOf(Math.round(countNum * 1000 / list.size())/10.0)};
						statistics.put(tpi[i], st);
						caches.add(statistics);
					} else if (tpi[i].equals("65")) {
						Map<String, String[]> statistics = new HashMap<String, String[]>();
						int integer = queryHealthData(
								Integer.parseInt(user.getLevelcode()),
								termid, classid, user.getCmis30id(),
								user.getDiscode(), gradeid);
						String[] st = {  String.valueOf(list.size()),  String.valueOf(integer),
								 String.valueOf(list.size() - integer),
										 String.valueOf(Math.round(integer * 1000 / list.size())/10.0) };
						statistics.put(tpi[i], st);
						caches.add(statistics);
					} else if (tpi[i].equals("33") || tpi[i].equals("53")
							|| tpi[i].equals("63") || tpi[i].equals("73")
							|| tpi[i].equals("93")) {
						Map<Object, Integer> cache = appraisalStaticsExt
								.queryRecordStaticsInChache(list, termid,
										tpi[i], PartInfoCacheDto.class);
						if (cache == null)
							continue;
						Integer teacherNum = cache.get("老师") != null ? cache.get("老师"): 0;
						Integer MasterNum = cache.get("班主任") != null ? cache.get("班主任"): 0;
						Integer integer = teacherNum + MasterNum;
						Map<String, String[]> statistics = new HashMap<String, String[]>();
						String[] st = {  String.valueOf(list.size()),  String.valueOf(integer),
								 String.valueOf(list.size() - integer),
										 String.valueOf(Math.round(integer * 1000 / list.size())/10.0) };
						statistics.put(tpi[i], st);
						caches.add(statistics);
					} else {
						Map<Object, Integer> cache = appraisalStaticsExt
								.queryRecordStaticsInChache(list, termid,
										tpi[i], PartInfoCacheDto.class);
						if (cache == null)
							continue;
						Integer integer = cache.get("本人") != null ? cache
								.get("本人") : cache.get("同学") != null ? cache
								.get("同学") : cache.get("家长") != null ? cache
								.get("家长") : cache.get("老师") != null ? cache
								.get("老师") : cache.get("班主任") != null ? cache
								.get("班主任") : 0;
						Map<String, String[]> statistics = new HashMap<String, String[]>();
						String[] st = {  String.valueOf(list.size()),  String.valueOf(integer),
								 String.valueOf(list.size() - integer),
										 String.valueOf(Math.round(integer * 1000 / list.size())/10.0) };
						statistics.put(tpi[i], st);
						caches.add(statistics);
					}
				}
		caches_string = JSONArray.fromObject(caches).toString();
	}
}