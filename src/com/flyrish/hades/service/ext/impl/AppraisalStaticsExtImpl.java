package com.flyrish.hades.service.ext.impl;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AttachmentCacheDto;
import com.flyrish.hades.service.ext.IAppraisalStaticsExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.util.DataSource;

public class AppraisalStaticsExtImpl extends JdbcRootManager implements IAppraisalStaticsExt{
	
	@Override
	public <T> Map<String,Map<String,List<Map<Object,Integer>>>> queryRecordStaticsInChache(Map<String,Map<String,List<String>>>schoolInfos,String termid,String two_part_id,Class clazz) {
		if(null!=schoolInfos && schoolInfos.size()>0){
			//学校信息
			Set<String> schoolIds = schoolInfos.keySet();
			//遍历schoolIds
			if(null==schoolIds || schoolIds.size()==0)return null;
			Map<String,Map<String,List<Map<Object,Integer>>>>schoolCountInfos = new HashMap<String, Map<String,List<Map<Object,Integer>>>>();
			for(String schoolId:schoolIds){ //学校
				//学校评价计数
				Map<String,List<Map<Object,Integer>>>gradeCountInfos = new HashMap<String, List<Map<Object,Integer>>>();
				//年级信息
				Map<String, List<String>> gradeNumInfos = schoolInfos.get(schoolId);
				Set<String> gradeNums = gradeNumInfos.keySet();
				if(null==gradeNums || gradeNums.size()==0)continue;
				for(String gradeNum:gradeNums){//年级
					List<Map<Object,Integer>> totalGradeCountInfos = new ArrayList<Map<Object,Integer>>();
					//年级评价计数
					Map<Object,Integer>gradeTotalCounts = new HashMap<Object, Integer>();
					//年级评价单个计数
					Map<Object,Integer>gradeSingleCounts = new HashMap<Object, Integer>();
					//学生信息
					List<String> eduIds = gradeNumInfos.get(gradeNum);
					if(null==eduIds || eduIds.size()==0)continue;
					for(String eduId : eduIds){//单个学生
						//统计处理（单个学生的所有记录）
						List<T> queryRecodes = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, two_part_id, clazz);
						//遍历评价记录   分别统计该学生的  自我评价  家长评价  老师评价  学生评价信息  班主任评价
						if(null==queryRecodes || queryRecodes.size()==0)continue;
						Integer bzrCount = 0;
						Integer lsCount = 0;
						Integer jzCount = 0;
						Integer txCount = 0;
						Integer brCount = 0;
						//public <T> List<T> queryAttachFileInCache(String foreignKey,String tablename,Class clazz);
						for(T record : queryRecodes){
							Object appaiserType = this.getAppaiserType(clazz, record);
							//高初中不同类型数据统计
							if(Constant.APPRASER_MASTER.equals(appaiserType)){//初中班主任
								//评价总计数
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								//评价单独计数
								bzrCount = retCount(gradeSingleCounts,bzrCount, appaiserType);
							}else if(Constant.TEACHER_DICT_CZ.equals(appaiserType)){//初中老师
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								lsCount = retCount(gradeSingleCounts, lsCount,appaiserType);
							}else if(Constant.APPRASER_PARENT.equals(appaiserType)){//初中家长
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								jzCount = retCount(gradeSingleCounts, jzCount,appaiserType);
							}else if(Constant.APPRASER_STUDENT.equals(appaiserType)){//初中同学
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								txCount = retCount(gradeSingleCounts, txCount,appaiserType);
								//统计上传附件数量
								this.attacheCounts(clazz, gradeTotalCounts,gradeSingleCounts, record, appaiserType,"CZ");
							}else if(Constant.me_apprasialidentify.equals(appaiserType)){//初中本人
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								brCount = retCount(gradeSingleCounts, brCount,appaiserType);
							}else if(Constant.MASTER_DICT.equals(appaiserType)){//高中班主任
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								bzrCount = retCount(gradeSingleCounts, bzrCount,appaiserType);
							}else if(Constant.TEACHER_DICT.equals(appaiserType)){//高中老师
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								lsCount = retCount(gradeSingleCounts, lsCount,appaiserType);
							}else if(Constant.HIGH_APPRASER_PARENT.equals(appaiserType)){//高中家长
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								jzCount = retCount(gradeSingleCounts, jzCount,appaiserType);
							}else if(Constant.HIGH_APPRASER_STUDENT.equals(appaiserType)){//高中同学
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								txCount = retCount(gradeSingleCounts, txCount,appaiserType);
							}else if("1".equals(appaiserType)){//高中本人
								this.gradeTotalCount(gradeTotalCounts, appaiserType);
								brCount = retCount(gradeSingleCounts, brCount,appaiserType);
								//统计上传附件数量
								this.attacheCounts(clazz, gradeTotalCounts,gradeSingleCounts, record, appaiserType,"GZ");
							}
						}
					}
					totalGradeCountInfos.add(gradeSingleCounts);//某个栏目评价人数
					totalGradeCountInfos.add(gradeTotalCounts);//某个栏目评价总数
					gradeCountInfos.put(gradeNum, totalGradeCountInfos);
				}
				schoolCountInfos.put(schoolId, gradeCountInfos);
			}
			return schoolCountInfos;
		}
		return null;
	}
	private <T> void attacheCounts(Class clazz,
			Map<Object, Integer> gradeTotalCounts,
			Map<Object, Integer> gradeSingleCounts, T record,
			Object appaiserType,String flag) {
		//获取传入dto主键
		String foreignKey = this.getForeignKey(clazz,record);
		List<?> attachFiles = null;
		if("CZ".equals(flag)){
			attachFiles = latestEvaluationRecordExt.queryAttachFileInCache(foreignKey, "Attachment",AttachmentCacheDto.class);
		}else if("GZ".equals(flag)){
			attachFiles = latestEvaluationRecordExt.queryAttachFileInCache(foreignKey, "a_attach",AattachCacheDto.class);
		}
		this.dealWithList(gradeTotalCounts, gradeSingleCounts, appaiserType,attachFiles);
	}
	private void dealWithList(Map<Object, Integer> gradeTotalCounts,
			Map<Object, Integer> gradeSingleCounts, Object appaiserType,
			List<?> attachFiles) {
		if(null!=attachFiles && attachFiles.size()>0){
			Integer attacheCount=0;
			attacheCount = attachFiles.size();
			if(attacheCount>0){
				Integer tempCount = gradeTotalCounts.get(appaiserType+"_Attache");
				if(null==tempCount){
					gradeTotalCounts.put(appaiserType, attacheCount);
				}else{
					gradeTotalCounts.put(appaiserType, attacheCount+tempCount);
				}
				Integer singleTempCount = gradeSingleCounts.get(appaiserType+"_Attache");
				if(null==singleTempCount){
					gradeSingleCounts.put(appaiserType, 1);
				}else{
					gradeSingleCounts.put(appaiserType, ++singleTempCount);
				}
			}
		}
	}
	private <T> String getForeignKey(Class clazz,T record){
		Method[] declaredMethods = clazz.getDeclaredMethods();
		if(null==declaredMethods || declaredMethods.length==0)return null;
		for(Method meth : declaredMethods){
			String methodName = meth.getName();
			if("getPart_id".equals(methodName)){
				return retKey(record, meth);
			}else if("getApprasialid".equals(methodName)){
				return retKey(record, meth);
			}
		}
		return null;
	}
	private <T> String retKey(T record, Method meth) {
		try {
			return (String) meth.invoke(record);
		} catch (Exception e) {
			logger.error("retKey(Class,T)",e);
		}
		return null;
	}
	private Integer retCount(Map<Object, Integer> gradeSingleCounts,
			Integer bzrCount, Object appaiserType) {
		if(bzrCount==0){
			this.gradeTotalCount(gradeSingleCounts, appaiserType);
		}
		bzrCount++;
		return bzrCount;
	}
	private void gradeTotalCount(Map<Object, Integer> gradeTotalCounts,
			Object appaiserType) {
		Integer count = gradeTotalCounts.get(appaiserType);
		if(null == count){
			gradeTotalCounts.put(appaiserType, 1);
		}else{
			gradeTotalCounts.put(appaiserType, ++count);
		}
	}
	public <T> Object getAppaiserType(Class clazz,T obj) {
		Method[] methods = clazz.getDeclaredMethods();
		for(Method meth : methods){
			String methodName = meth.getName();
			if("getAppraseridentify".equals(methodName)||"getWrite_man".equals(methodName)){
				try {
					return meth.invoke(obj);
				} catch (Exception e) {
					logger.error("getAppaiserType(Class,T)",e);
				}
			}
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.flyrish.hades.dto.AapprasialCacheDto");
			AapprasialCacheDto test = new AapprasialCacheDto();
			test.setAppraseridentify("班主任");
			AppraisalStaticsExtImpl testExt = new AppraisalStaticsExtImpl();
			Object val = testExt.getAppaiserType(clazz, test);
			System.out.println(val);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected  Logger logger=Logger.getLogger(LatestEvaluationRecordExtImpl.class);
	private ILatestEvaluationRecordExt latestEvaluationRecordExt;
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}
	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}
	@Override
	public <T> Map<Object, Integer> queryRecordStaticsInChache(
			List<String> eduIds, String termid, String two_part_id, Class clazz) {
		if(null==eduIds || eduIds.size()==0)return null;
		Map<Object,Integer>gradeSingleCounts = new HashMap<Object, Integer>();
		for(String eduId : eduIds){
			/*if("33".equals(two_part_id) && "07024092".equals(eduId)){
				System.out.println(44);
			}*/
			List<T> queryRecodes = latestEvaluationRecordExt.queryRecodeInCache(eduId, termid, two_part_id, clazz);
			if(null==queryRecodes || queryRecodes.size()==0)continue;
			Integer bzrCount = 0;
			Integer lsCount = 0;
			Integer jzCount = 0;
			Integer txCount = 0;
			Integer brCount = 0;
			for(T record : queryRecodes){
				Object appaiserType = this.getAppaiserType(clazz, record);
				if(Constant.APPRASER_MASTER.equals(appaiserType)){
					appaiserType = Constant.TEACHER_DICT_CZ;
				}else if(Constant.MASTER_DICT.equals(appaiserType)){
					appaiserType = Constant.TEACHER_DICT;
				}
				//高初中不同类型数据统计
				if(Constant.APPRASER_MASTER.equals(appaiserType)){//初中班主任
					//评价总计数
					//评价单独计数
					bzrCount = retCount(gradeSingleCounts,bzrCount, appaiserType);
				}else if(Constant.TEACHER_DICT_CZ.equals(appaiserType)){//初中老师
					lsCount = retCount(gradeSingleCounts, lsCount,appaiserType);
				}else if(Constant.APPRASER_PARENT.equals(appaiserType)){//初中家长
					jzCount = retCount(gradeSingleCounts, jzCount,appaiserType);
				}else if(Constant.APPRASER_STUDENT.equals(appaiserType)){//初中同学
					txCount = retCount(gradeSingleCounts, txCount,appaiserType);
				}else if(Constant.me_apprasialidentify.equals(appaiserType)){//初中本人
					brCount = retCount(gradeSingleCounts, brCount,appaiserType);
				}else if(Constant.MASTER_DICT.equals(appaiserType)){//高中班主任
					bzrCount = retCount(gradeSingleCounts, bzrCount,appaiserType);
				}else if(Constant.TEACHER_DICT.equals(appaiserType)){//高中老师
					lsCount = retCount(gradeSingleCounts, lsCount,appaiserType);
				}else if(Constant.HIGH_APPRASER_PARENT.equals(appaiserType)){//高中家长
					jzCount = retCount(gradeSingleCounts, jzCount,appaiserType);
				}else if(Constant.HIGH_APPRASER_STUDENT.equals(appaiserType)){//高中同学
					txCount = retCount(gradeSingleCounts, txCount,appaiserType);
				}else if("1".equals(appaiserType)){//高中本人
					brCount = retCount(gradeSingleCounts, brCount,appaiserType);
				}
			}
		}
		return gradeSingleCounts;
	}
	@DataSource("read")
	public Map<String,Integer> queryAssessStatics(List<String> cmis30Ids,final String userType,String levelFlag,String campusId) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("campusId", campusId);
			params.put("levelCode", levelFlag);
			String preSql = "";
			if(Constant.USER_TYPE_SCHOOLADMIN.equals(userType)
					||Constant.USER_TYPE_SPORTSEMASTER.equals(userType)
					||Constant.USER_TYPE_CLASSMASTER.equals(userType)){
				params.put("cmis30Id", cmis30Ids.get(0));
				preSql = "IAppraisalStaticsExt.queryAssessStatics.query";
			}else{
				params.put("cmis30Ids", cmis30Ids.get(0));
				preSql = "IAppraisalStaticsExt.queryAssessStatics_sq.query";
			}
			ISqlElement sqlDemo=this.processSql(params, preSql);
			List<String> bzrpys =  this.findList(preSql, params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StringBuffer sb = new StringBuffer();
					if(Constant.USER_TYPE_SCHOOLADMIN.equals(userType)
							||Constant.USER_TYPE_SPORTSEMASTER.equals(userType)
							||Constant.USER_TYPE_CLASSMASTER.equals(userType)){
						sb.append(rs.getString("gradenum")).append("-")
						.append(rs.getString("classnum")).append("-")
						.append(rs.getString("termid")).append("@")
						.append(rs.getString("count"));
					}else{
						sb.append(rs.getString("cmis30id")).append("-")
						.append(rs.getString("gradenum")).append("-")
						.append(rs.getString("termid")).append("-")
						.append(rs.getString("campusid")).append("@")
						.append(rs.getString("count"));
					}
					return sb.toString();
				}
			});
			if(null!=bzrpys&&bzrpys.size()>0){
				Map<String,Integer>counts = new HashMap<String, Integer>();
				for(String countInfo : bzrpys){
					String[] countInfos = countInfo.split("@");
					if(null!=countInfos && countInfos.length==2){
						counts.put(countInfos[0], Integer.parseInt(countInfos[1]));
					}
				}
				return counts;
			}
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryAssessStatics",e);
		}
		return null;
	}
	@DataSource("read")
	public Map<String, List<String>> querySchoolInfos_new(List<String> cmis30Ids,String userType,String levelFlag,String campusId) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelFlag);
			params.put("campusId", campusId);
			String preSql = "";
			if(Constant.USER_TYPE_SCHOOLADMIN.equals(userType)
					||Constant.USER_TYPE_SPORTSEMASTER.equals(userType)
					||Constant.USER_TYPE_CLASSMASTER.equals(userType)){//教务老师、班主任们
				preSql = "IAppraisalStaticsExt.querySchoolInfos_new.query";
			}else{//市区用户们
				params.put("cmis30Ids", cmis30Ids);
				preSql = "IAppraisalStaticsExt.querySchoolInfos_sq.query";
			} 
			ISqlElement sqlDemo=this.processSql(params, preSql);
			List<String> infos =  this.findList(preSql, params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("infos");
				}
			});
			Map<String,List<String>>temp = new HashMap<String, List<String>>();
			if(null!=infos && infos.size()>0){
				for(String info : infos){
					String[] schoolInfos = info.split("@");
					if(null!=schoolInfos&&schoolInfos.length==2){
						String key = schoolInfos[0];
						String value = schoolInfos[1];
						if(NestUtil.isNotEmpty(value)){
							String[] values = value.split(",");
							if(null!=values&&values.length>0){
								List<String> eduIds = Arrays.asList(values);
								temp.put(key, eduIds);
							}
						}
					}else{
						temp.put(schoolInfos[0], Arrays.asList(new String[]{"0000"}));
					}
				}
				return temp;
			}
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.querySchoolInfos",e);
		}
		return null;
	}
	@DataSource("read")
	public Map<String, List<String>> querySchoolInfos(List<String> cmis30Ids,String userType,String levelFlag,String campusId) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelFlag);
			params.put("campusId", campusId);
			String preSql = "";
			if(Constant.USER_TYPE_SCHOOLADMIN.equals(userType)
					||Constant.USER_TYPE_SPORTSEMASTER.equals(userType)
					||Constant.USER_TYPE_CLASSMASTER.equals(userType)){//教务老师、班主任们
				preSql = "IAppraisalStaticsExt.querySchoolInfos.query";
			}else{//市区用户们
				params.put("cmis30Ids", cmis30Ids);
				preSql = "IAppraisalStaticsExt.querySchoolInfos_sq.query";
			} 
			ISqlElement sqlDemo=this.processSql(params, preSql);
			List<String> infos =  this.findList(preSql, params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("infos");
				}
			});
			if(null!=infos && infos.size()>0){
				Map<String,List<String>>temp = new HashMap<String, List<String>>();
				for(String info : infos){
					String[] schoolInfos = info.split("@");
					if(null!=schoolInfos&&schoolInfos.length==2){
						String key = schoolInfos[0];
						String value = schoolInfos[1];
						if(NestUtil.isNotEmpty(value)){
							String[] values = value.split(",");
							if(null!=values&&values.length>0){
								List<String> eduIds = Arrays.asList(values);
								temp.put(key, eduIds);
							}
						}
					}
				}
				return temp;
			}
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.querySchoolInfos",e);
		}
		return null;
	}
	@DataSource("read")
	public List<String> queryJB(String levelCode, String campuseId,String teacherId,String flag) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("campuseId", campuseId);
			params.put("teacherId", teacherId);
			params.put("flag", flag);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryJB.query");
			List<String>jbs = this.findList("IAppraisalStaticsExt.queryJB.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("years");
				}
			});
			if(NestUtil.isNotEmpty(flag)){
				return jbs;
			}else{
				if(null!=jbs && jbs.size()>0){
					List<String>years = new ArrayList<String>();
					String currentYear = jbs.get(0);
					int j = 0;
					if("2012002".equals(levelCode)){
						j=4;
					}else{
						j=3;
					}
					for(int i=0;i<j;i++){
						years.add(String.valueOf(Integer.parseInt(currentYear)+i));
					}
					return years;
				}
			}
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryJB",e);
		}
		return null;
	}
	
	@DataSource("read")
	public List<String> queryJB(String levelid, String levelCode,
			String campuseId, String teacherId, String flag) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("campuseId", campuseId);
			params.put("teacherId", teacherId);
			params.put("levelid",levelid);
			params.put("flag", flag);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryJB.query");
			List<String>jbs = this.findList("IAppraisalStaticsExt.queryJB.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("years");
				}
			});
			if(NestUtil.isNotEmpty(flag)){
				return jbs;
			}else{
				if(null!=jbs && jbs.size()>0){
					List<String>years = new ArrayList<String>();
					String currentYear = jbs.get(0);
					int j = 0;
					if("2012002".equals(levelCode)){
						j=4;
					}else{
						j=3;
					}
					for(int i=0;i<j;i++){
						years.add(String.valueOf(Integer.parseInt(currentYear)+i));
					}
					return years;
				}
			}
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryJB",e);
		}
		return null;
	}
	@DataSource("read")
	public List<String> queryClass(String gradeid,String teacherId) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("gradeid", gradeid);
			params.put("teacherId", teacherId);
			ISqlElement sqlDemo=this.processSql(params, "IAppraisalStaticsExt.queryClass.query");
			return this.findList("IAppraisalStaticsExt.queryClass.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("classInfo");
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryClass",e);
		}
		return null;
	}
	@DataSource("read")
	public List<String> queryTermIds2(String levelCode, String levelid,
			String userType) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("levelCode", levelCode);
			params.put("levelId", levelid);
			params.put("userType", userType);
			String preSql = "";
			if(NestUtil.isEmpty(userType)){
				preSql = "IAppraisalStaticsExt.queryTermIds3.query";
			}else{
				preSql = "IAppraisalStaticsExt.queryTermIds2.query";
			}
			ISqlElement sqlDemo=this.processSql(params, preSql);
			return this.findList(preSql, params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("termInfos");
				}
			});
		} catch (Exception e) {
			logger.error("IAppraisalStaticsExt.queryTermIds2",e);
		}
		return null;
	}
	@Override
	public List<String> queryDiscode() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			return this.findList("IAppraisalStaticsExt.queryDiscode.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("discode")+"_"+rs.getString("discodeName");
				}
			});
		} catch (Exception e) {
			logger.error("queryDiscode()",e);
		}
		return null;
	}
	
}
