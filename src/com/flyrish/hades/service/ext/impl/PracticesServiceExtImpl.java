package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.ApracticeappraisalCacheDto;
import com.flyrish.hades.dto.ApracticesCacheDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.PracticeappraisalDto;
import com.flyrish.hades.dto.PracticesDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.IPracticesServiceExt;
import com.flyrish.hades.util.DataSource;

public class PracticesServiceExtImpl extends JdbcRootManager implements IPracticesServiceExt{
	public ILatestEvaluationRecordExt latestEvaluationRecordExt;
	public IBaseInforManagerExt baseInforManagerExt;
	
	
	public ILatestEvaluationRecordExt getLatestEvaluationRecordExt() {
		return latestEvaluationRecordExt;
	}



	public void setLatestEvaluationRecordExt(
			ILatestEvaluationRecordExt latestEvaluationRecordExt) {
		this.latestEvaluationRecordExt = latestEvaluationRecordExt;
	}



	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}



	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}



	public List selectSelfPractices(final Map<String, Object> queryMap) {
		try {
			return this.findList("SelfPractices.selectSelfPractices.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PracticesDto practices = new PracticesDto();
					practices.setAppraisaltypeid(rs.getString("appraisaltypeid"));
					practices.setPracticeid(rs.getString("practiceid"));
					practices.setItem1(rs.getString("item1"));
					practices.setItem2(rs.getString("item2"));
					practices.setItem3(rs.getString("item3"));
					practices.setItem4(rs.getString("item4"));
					practices.setItem5(rs.getString("item5"));
					practices.setItem6(rs.getString("item6"));
					practices.setItem7(rs.getString("item7"));
					practices.setItem8(rs.getString("item8"));
					practices.setItem9(rs.getString("item9"));
				/*	practices.setItem10(rs.getString("item10"));
					practices.setItem11(rs.getString("item11"));
					practices.setItem12(rs.getString("item12"));
					practices.setItem13(rs.getString("item13"));
					practices.setItem14(rs.getString("item14"));
					practices.setItem15(rs.getString("item15"));
					practices.setItem16(rs.getString("item16"));
					practices.setItem17(rs.getString("item17"));
					practices.setItem18(rs.getString("item18"));
					practices.setItem19(rs.getString("item19"));
					practices.setItem20(rs.getString("item20"));*/
					AttachDto attachDto = new AttachDto();
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("recordid", rs.getString("practiceid"));
					queryMap1.put("cmis30id", queryMap.get("cmis30id"));
					queryMap1.put("discode", queryMap.get("discode"));
					queryMap1.put("attachtype", "3");
					List<AttachDto> lst= selectAttach(queryMap1);
					practices.setAttachListForFile(lst);
					PracticeappraisalDto practiceappraisal = new PracticeappraisalDto();
					Map<String, Object> queryMap2 = new HashMap();
					queryMap2.put("practiceid", rs.getString("practiceid"));
					List<PracticeappraisalDto> lst1 = selectPracticeappraisal(queryMap2);
					practices.setPracticeappraisalList(lst1);
					return practices;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfPractices(Map)",e);
		}
		return null;
	}
	


	public String insertSelfPractices(PracticesDto practices) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("semesterid",practices.getSemesterid());
		params.put("discode",practices.getDiscode());
		params.put("cmis30id",practices.getCmis30id());
		params.put("studentid",practices.getStudentid());
		params.put("appraisaltypeid",practices.getAppraisaltypeid());
		params.put("edu_id",practices.getEdu_id());
		params.put("item1",practices.getItem1());
		params.put("item2",practices.getItem2());
		params.put("item3",practices.getItem3());
		params.put("item4",practices.getItem4());
		params.put("item5",practices.getItem5());
		params.put("item6",practices.getItem6());
		params.put("item7",practices.getItem7());
		params.put("item8",practices.getItem8());
		params.put("item9",practices.getItem9());
		params.put("item10",practices.getItem10());
		params.put("item11",practices.getItem11());
		params.put("item12",practices.getItem12());
		params.put("item13",practices.getItem13());
		params.put("item14",practices.getItem14());
		params.put("item15",practices.getItem15());
		params.put("item16",practices.getItem16());
		params.put("item17",practices.getItem17());
		params.put("item18",practices.getItem18());
		params.put("item19",practices.getItem19());
		params.put("item20",practices.getItem20());
		try{
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_A_PRACTICES.NEXTVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			params.put("practiceid",str);
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.insertPractices.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return str;
		}catch(Exception e){
			logger.error("insertSelfPractices(PracticesDto)",e);
				throw new ManagerException(e);
		}
	}
	
	public String insertSelfPracticeappraisal(PracticeappraisalDto practiceappraisal){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("content",practiceappraisal.getContent());
		params.put("practiceid",practiceappraisal.getPracticeid());
		/*params.put("signdate",practiceappraisal.getSigndate());*/
		params.put("signer",practiceappraisal.getSigner());
		try{
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_A_PRACTICEAPPRAISAL.NEXTVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			params.put("appraisalid",str);
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.insertPracticeappraisal.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return str;
		}catch(Exception e){
			logger.error("insertSelfPracticeappraisal(PracticeappraisalDto)",e);
				throw new ManagerException(e);
		}
	}
	
	/**
	 * 修改高中综合实践活动
	 * @param list
	 * @return
	 */
	public boolean updateSelfPractices(PracticesDto practices) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("practiceid",practices.getPracticeid());
		params.put("item1",practices.getItem1());
		params.put("item2",practices.getItem2());
		params.put("item3",practices.getItem3());
		params.put("item4",practices.getItem4());
		params.put("item5",practices.getItem5());
		params.put("item6",practices.getItem6());
		params.put("item7",practices.getItem7());
		params.put("item8",practices.getItem8());
		params.put("item9",practices.getItem9());
		/*params.put("item10",practices.getItem10());
		params.put("item11",practices.getItem11());
		params.put("item12",practices.getItem12());
		params.put("item13",practices.getItem13());
		params.put("item14",practices.getItem14());
		params.put("item15",practices.getItem15());
		params.put("item16",practices.getItem16());
		params.put("item17",practices.getItem17());
		params.put("item18",practices.getItem18());
		params.put("item19",practices.getItem19());
		params.put("item20",practices.getItem20());*/
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.updatePractices.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPractices(PracticesDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	
	/**
	 * 修改高中综合实践活动
	 * @param list
	 * @return
	 */
	public boolean updateSelfPracticeappraisal(PracticeappraisalDto practiceappraisal) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("content",practiceappraisal.getContent());
		/*params.put("signdate",practiceappraisal.getSigndate());*/
		params.put("appraisalid",practiceappraisal.getAppraisalid());
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.updatePracticeappraisal.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updateSelfPracticeappraisal(PracticesDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	
	
	public boolean deleteSelfPractices(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("practiceid",id);
			Map<String,Object> params1=new HashMap<String,Object>();
			params1.put("recordid",id);
			Map<String,Object> params2=new HashMap<String,Object>();
			params2.put("practiceid",id);
			try{
				ISqlElement sqlElement2=this.processSql(params2,"SelfPractices.deletePracticesPracticeappraisal.delete");
				this.getJdbcTemplate().update(sqlElement2.getSql(),sqlElement2.getParams());
				ISqlElement sqlElement1=this.processSql(params1,"SelfAppManager.deletetSelfPackageAttach.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				ISqlElement sqlElement=this.processSql(params,"SelfPractices.deletePractices.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPractices(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	} 
	
	public boolean deleteSelfPracticeappraisal(String id) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraisalid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfPractices.deletePracticeappraisal.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPracticeappraisal(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	} 
	
	/**
	 * 获取自我评价
	 * @param Map查询参数
	 * @return
	 */
	@DataSource("read")
	public List<PracticeappraisalDto> selectPracticeappraisal(Map<String, Object> queryMap) {
		try {
			return this.findList("SelfPractices.selectSelfPracticeappraisal.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					PracticeappraisalDto practiceappraisal = new PracticeappraisalDto();
					practiceappraisal.setAppraisalid(rs.getString("appraisalid"));
					practiceappraisal.setContent(rs.getString("content"));
					practiceappraisal.setPracticeid(rs.getString("practiceid"));
					practiceappraisal.setSigndate(rs.getDate("signdate"));
					practiceappraisal.setSigner(rs.getString("signer"));
					return practiceappraisal;
				}
			});
		} catch (Exception e) {
			logger.error("selectPracticeappraisal(Map)",e);
		}
		return null;
	}
	
	
	/**
	 * 获取附件数据
	 * @param Map查询参数
	 * @return
	 */
	public List<AttachDto> selectAttach(Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectattach.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AttachDto attachDto = new AttachDto();
					attachDto.setFilename(rs.getString("attachname"));
					attachDto.setFilepath(rs.getString("attachpath"));
					attachDto.setAttachid(Integer.valueOf(rs.getString("attachid")));
					return attachDto;
				}
			});
		} catch (Exception e) {
			logger.error("selectRecordpackage(Map)",e);
		}
		return null;
	}



	@Override
	public String insertSelfPracticesCache(ApracticesCacheDto practicesCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("semesterid",practicesCacheDto.getSemesterid());
		params.put("discode",practicesCacheDto.getDiscode());
		params.put("cmis30id",practicesCacheDto.getCmis30id());
		params.put("studentid",practicesCacheDto.getStudentid());
		params.put("appraisaltypeid",practicesCacheDto.getAppraisaltypeid());
		params.put("edu_id",practicesCacheDto.getEdu_id());
		params.put("item1",practicesCacheDto.getItem1());
		params.put("item2",practicesCacheDto.getItem2());
		params.put("item3",practicesCacheDto.getItem3());
		params.put("item4",practicesCacheDto.getItem4());
		params.put("item5",practicesCacheDto.getItem5());
		params.put("item6",practicesCacheDto.getItem6());
		params.put("item7",practicesCacheDto.getItem7());
		params.put("item8",practicesCacheDto.getItem8());
		params.put("item9",practicesCacheDto.getItem9());
		params.put("item10",practicesCacheDto.getItem10());
		params.put("item11",practicesCacheDto.getItem11());
		params.put("item12",practicesCacheDto.getItem12());
		params.put("item13",practicesCacheDto.getItem13());
		params.put("item14",practicesCacheDto.getItem14());
		params.put("item15",practicesCacheDto.getItem15());
		params.put("item16",practicesCacheDto.getItem16());
		params.put("item17",practicesCacheDto.getItem17());
		params.put("item18",practicesCacheDto.getItem18());
		params.put("item19",practicesCacheDto.getItem19());
		params.put("item20",practicesCacheDto.getItem20());
		try{
			String newId = baseInforManagerExt.queryProKey("A_PRACTICES");
			params.put("practiceid",newId);
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.insertPractices.addCache");
			//System.out.println(sqlElement.getSql());
			practicesCacheDto.setPracticeid(newId);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(practicesCacheDto.getEdu_id(), practicesCacheDto.getSemesterid(), 
					practicesCacheDto.getAppraisaltypeid(), practicesCacheDto.getAppraseridentify(), 
					practicesCacheDto.getAppraiserrid(), newId, practicesCacheDto, sqlElement,null);
			return newId;
		}catch(Exception e){
			logger.error("insertSelfPracticesCache(ApracticesCacheDto)",e);
				throw new ManagerException(e);
		}
	}



	@Override
	public String insertSelfPracticeappraisalCache(ApracticeappraisalCacheDto practiceappraisalCacheDto, String type) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("content",practiceappraisalCacheDto.getContent());
		params.put("practiceid",practiceappraisalCacheDto.getPracticeid());
		/*params.put("signdate",practiceappraisal.getSigndate());*/
		params.put("signer",practiceappraisalCacheDto.getSigner());
		try{
			String newId = baseInforManagerExt.queryProKey("A_PRACTICEAPPRAISAL");
			params.put("appraisalid",newId);
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.insertPracticeappraisal.addCache");
			//System.out.println(sqlElement.getSql());
			practiceappraisalCacheDto.setAppraisalid(newId);
			latestEvaluationRecordExt.addChildrenObjectInCache(practiceappraisalCacheDto.getPracticeid(), newId,
					practiceappraisalCacheDto, "a_practiceappraisal", type, sqlElement,null);
			return newId;
		}catch(Exception e){
			logger.error("insertSelfPracticeappraisalCache(ApracticeappraisalCacheDto)",e);
				throw new ManagerException(e);
		}
	}



	@Override
	public boolean updateSelfPracticesCache(ApracticesCacheDto practicesCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("practiceid",practicesCacheDto.getPracticeid());
		params.put("item1",practicesCacheDto.getItem1());
		params.put("item2",practicesCacheDto.getItem2());
		params.put("item3",practicesCacheDto.getItem3());
		params.put("item4",practicesCacheDto.getItem4());
		params.put("item5",practicesCacheDto.getItem5());
		params.put("item6",practicesCacheDto.getItem6());
		params.put("item7",practicesCacheDto.getItem7());
		params.put("item8",practicesCacheDto.getItem8());
		params.put("item9",practicesCacheDto.getItem9());
		/*params.put("item10",practicesCacheDto.getItem10());
		params.put("item11",practicesCacheDto.getItem11());
		params.put("item12",practicesCacheDto.getItem12());
		params.put("item13",practicesCacheDto.getItem13());
		params.put("item14",practicesCacheDto.getItem14());
		params.put("item15",practicesCacheDto.getItem15());
		params.put("item16",practicesCacheDto.getItem16());
		params.put("item17",practicesCacheDto.getItem17());
		params.put("item18",practicesCacheDto.getItem18());
		params.put("item19",practicesCacheDto.getItem19());
		params.put("item20",practicesCacheDto.getItem20());*/
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.updatePractices.updateCache");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(practicesCacheDto.getEdu_id(), practicesCacheDto.getSemesterid(),
					practicesCacheDto.getAppraisaltypeid(), practicesCacheDto.getAppraseridentify(),
					practicesCacheDto.getAppraiserrid(), practicesCacheDto.getPracticeid(), practicesCacheDto, sqlElement,null);
			return true;
		}catch(Exception e){
			logger.error("updateSelfPractices(PracticesDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}



	@Override
	public boolean updateSelfPracticeappraisalCache(ApracticeappraisalCacheDto practiceappraisalCacheDto,String type) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("content",practiceappraisalCacheDto.getContent());
		/*params.put("signdate",practiceappraisalCacheDto.getSigndate());*/
		params.put("appraisalid",practiceappraisalCacheDto.getAppraisalid());
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfPractices.updatePracticeappraisal.updateCache");
			latestEvaluationRecordExt.updateChildrenObjectlInCache(practiceappraisalCacheDto.getPracticeid(), practiceappraisalCacheDto.getAppraisalid(),
					practiceappraisalCacheDto, "a_practiceappraisal", type, sqlElement,null);
			return true;
		}catch(Exception e){
			logger.error("updateSelfPracticeappraisalCache(ApracticeappraisalCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}



	@Override
	public boolean deleteSelfPracticesCache(String username,String columNum,String proKey,ApracticesCacheDto practicesCacheDto) {
		if(!StringUtil.isEmpty(proKey)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("practiceid",proKey);
			Map<String,Object> params1=new HashMap<String,Object>();
			params1.put("recordid",proKey);
			Map<String,Object> params2=new HashMap<String,Object>();
			params2.put("practiceid",proKey);
			try{
				ISqlElement sqlElement2=this.processSql(params2,"SelfPractices.deletePracticesPracticeappraisal.deleteCache");
				latestEvaluationRecordExt.deleteChildrenObjectInCache(proKey, "A_PRACTICEAPPRAISAL", columNum, sqlElement2,ApracticeappraisalCacheDto.class);
				ISqlElement sqlElement1=this.processSql(params1,"SelfAppManager.deletetSelfPackageAttach.deleteCache");
				latestEvaluationRecordExt.deleteAttachFileAllByforeignKey(proKey, "a_attach", columNum, sqlElement1,AattachCacheDto.class);
				ISqlElement sqlElement=this.processSql(params,"SelfPractices.deletePractices.deleteCache");
				latestEvaluationRecordExt.delRecodeInCacheByProKey(practicesCacheDto.getEdu_id(), practicesCacheDto.getSemesterid(), 
						practicesCacheDto.getAppraisaltypeid(), practicesCacheDto.getAppraseridentify(), practicesCacheDto.getEdu_id(),
						proKey, sqlElement,ApracticesCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPracticesCache(String,String,String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}



	@Override
	public boolean deleteSelfPracticeappraisalCache(String id,String fid,String type) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("appraisalid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfPractices.deletePracticeappraisal.deleteCache");
				//System.out.println(sqlElement.getSql());
				//latestEvaluationRecordExt.deleteChildrenObjectInCache(fid, "a_practiceappraisal", type, sqlElement,ApracticeappraisalCacheDto.class);
				latestEvaluationRecordExt.deleteChildrenObjectInCache(fid,id,"a_practiceappraisal", type, sqlElement,ApracticeappraisalCacheDto.class);
				return true;
			}catch(Exception e){
				logger.error("deleteSelfPracticeappraisalCache(String,String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
	}

}
