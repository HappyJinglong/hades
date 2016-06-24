package com.flyrish.hades.service.ext.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.CLOB;

import org.nestframework.addons.spring.Spring;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.commons.utils.StringUtil;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.common.ConstantBean;
import com.flyrish.hades.dto.AapprasialCacheDto;
import com.flyrish.hades.dto.AattachCacheDto;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.AttachDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.exception.InValidInsertException;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.service.ext.ILatestEvaluationRecordExt;
import com.flyrish.hades.service.ext.ISelfAppManagerExt;
import com.flyrish.hades.util.DataSource;


public class SelfAppManagerExtImpl extends JdbcRootManager implements ISelfAppManagerExt{
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


	public ConstantBean getConstantBean() {
		return constantBean;
	}


	public void setConstantBean(ConstantBean constantBean) {
		this.constantBean = constantBean;
	}
	public ConstantBean constantBean;
	/**
	 * 获取自我评价列表信息
	 */
	public List selectSelfAppraise(final Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectSelfAppraise.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto appraisal=new AppraisalDto();
					appraisal.setApprasialid(rs.getString("apprasialid"));
					appraisal.setSigndate((Date) rs.getDate("signdate"));
					appraisal.setAppraisaltypeid(Integer.valueOf(rs.getString("appraisaltypeid")));
					appraisal.setApprasial((String) rs.getString("apprasial"));
					appraisal.setAppraser((String) rs.getString("appraser"));
					appraisal.setSemesterid(Integer.valueOf(rs.getString("semesterid")));
					appraisal.setStudentid(rs.getString("studentid"));
					appraisal.setAppraseridentify(Integer.valueOf(rs.getString("appraseridentify")));
					Map<String, Object> queryMap1 = new HashMap<String, Object>();
					queryMap1.put("recordid", Integer.valueOf(rs.getString("apprasialid")));
					queryMap1.put("cmis30id", queryMap.get("cmis30id"));
					queryMap1.put("discode", queryMap.get("discode"));
					queryMap1.put("attachtype", "2");
					List<AttachDto> lst= selectAttach(queryMap1);
					appraisal.setAttachListForFile(lst);	
					return appraisal;
				}
			});
		} catch (Exception e) {
			logger.error("selectSelfAppraise(Map)",e);
		}
		return null;
	}
	
	
	public List selectAttach(Map<String, Object> queryMap) {
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

	
	/**
	 * 增加自我评价
	 * @param appraisal
	 * @return
	 */
	public String insertSelfAppraisal(AppraisalDto appraisal) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("appraisaltypeid",appraisal.getAppraisaltypeid());
		params.put("appraiserrid",appraisal.getAppraiserrid());
		params.put("apprasial",appraisal.getApprasial());
		params.put("studentid",appraisal.getStudentid());
		params.put("appraseridentify",appraisal.getAppraseridentify());
		params.put("appraser",appraisal.getAppraser());
		params.put("signdate",appraisal.getSigndate());
		params.put("edu_id",appraisal.getEduid());
		params.put("semesterid",appraisal.getSemesterid());
		params.put("cmis30id",appraisal.getCmis30id());
		params.put("discode",appraisal.getDiscode());
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisal.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			List lst = new ArrayList();
			lst = getJdbcTemplate().queryForList("select s_A_APPRASIAL.CURRVAL from dual");
			String str = lst.get(0).toString().split("=")[1].split("}")[0];
			return str;
		}catch(Exception e){
			logger.error("insertSelfAppraisal(AppraisalDto)",e);
			throw new ManagerException(e);
		}
	}
	
	
	
	/**
	 * 增加自我评价
	 * @param appraisal
	 * @return
	 */
	public String insertSelfAppraisalwith(AppraisalDto appraisal) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisal.getApprasialid());
		params.put("appraisaltypeid",appraisal.getAppraisaltypeid());
		params.put("appraiserrid",appraisal.getAppraiserrid());
		params.put("apprasial",appraisal.getApprasial());
		params.put("studentid",appraisal.getStudentid());
		params.put("appraseridentify",appraisal.getAppraseridentify());
		params.put("appraser",appraisal.getAppraser());
		params.put("signdate",appraisal.getSigndate());
		params.put("edu_id",appraisal.getEduid());
		params.put("semesterid",appraisal.getSemesterid());
		params.put("cmis30id",appraisal.getCmis30id());
		params.put("discode",appraisal.getDiscode());
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisalwith.add");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
		}catch(Exception e){
			logger.error("insertSelfAppraisalwith(AppraisalDto)",e);
			if(e.getMessage().contains(Constant.MISSING_ONLY_MSG)){
				throw new InValidInsertException();
			}else{
				throw new ManagerException(e);
			}
		}
		return null;
	}
	/**
	 * 修改自我评价
	 * @param list
	 * @return
	 */
	public boolean updateSelfAppraisal(AppraisalDto appraisal) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisal.getApprasialid());
		params.put("apprasial",appraisal.getApprasial());
		params.put("signdate",appraisal.getSigndate());
		params.put("appraseridentify",appraisal.getAppraseridentify());
		try{
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.updateSelfAppraisal.update");
			this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
			return true;
		}catch(Exception e){
			logger.error("updateSelfAppraisal(AppraisalDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}
	/**
	 * 获得学生信息
	 */
	@DataSource("read")
	public StudentDto selectStudent(Map<String, Object> queryMap) {
		try {
			List<StudentDto> students=this.findList("SelfAppManager.selectStudent.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto stu=new StudentDto();
					String str="";
					if(rs.getString("levelname")!=null && rs.getString("levelname").length()>0){
						str+=rs.getString("levelname");
					}
					if(rs.getString("gradename")!=null && rs.getString("gradename").length()>0){
						str+="-";
						str+=rs.getString("gradename");
					}
					if(rs.getString("classsname")!=null && rs.getString("classsname").length()>0){
						str+="-";
						str+=rs.getString("classsname");
					}
					stu.setLevelGradeClass(str);
					stu.setStudentid(rs.getString("studentid"));
					stu.setSex(Integer.valueOf(rs.getString("sex")));
					if(Integer.valueOf(rs.getString("sex")).equals(9001001)){
						stu.setSexname("男");
					}
					else if(Integer.valueOf(rs.getString("sex")).equals(9001002)){
						stu.setSexname("女");
					}
					stu.setStudentno(rs.getString("studentno"));
					stu.setName(rs.getString("name"));
					stu.setTermid(rs.getString("termid"));
					stu.setTermtype(rs.getString("termtype"));
					stu.setEduid(rs.getString("edu_id"));
					stu.setCmis30id(rs.getString("cmis30id"));
					stu.setGradenum(rs.getString("gradenum"));
					stu.setDiscode(Integer.valueOf(rs.getString("discode")));
					stu.setClassid(Integer.valueOf(rs.getString("classid")));
					stu.setLevelid(Integer.valueOf(rs.getString("levelid")));
					stu.setGradeid(Integer.valueOf(rs.getString("gradeid")));
					stu.setLevelcode(Integer.valueOf(rs.getString("levelcode")));
					return stu;
				}
			});
			if(students!=null&&students.size()>0)
				return students.get(0);
		} catch (Exception e) {
			logger.error("selectSelfAppraise(Map)",e);
		}
		return null;
	}

	public boolean deleteSelfAppraisal(String id,String cmis30id,String discode) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("apprasialid",id);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deleteSelfAppraisal.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteSelfAppraisal(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
		
	} 
	public boolean deleteSelfAppraisalWithatt(String id,String cmis30id,String discode) {
		if(!StringUtil.isEmpty(id)){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("apprasialid",id);
			params.put("cmis30id",cmis30id);
			params.put("discode",discode);
			Map<String,Object> params1=new HashMap<String,Object>();
			params1.put("recordid",id);
			try{
				ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deleteSelfAppraisal.delete");
				this.getJdbcTemplate().update(sqlElement.getSql(),sqlElement.getParams());
				ISqlElement sqlElement1=this.processSql(params1,"SelfAppManager.deletetSelfPackageAttach.delete");
				this.getJdbcTemplate().update(sqlElement1.getSql(),sqlElement1.getParams());
				return true;
			}catch(Exception e){
				logger.error("deleteSelfAppraisal(String)",e);
				//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
				throw new ManagerException(e);
			}
		}else{
			return false;
		}
		
	} 
	
	public List selectPhoto(Map<String, Object> queryMap) {
		try {
			return this.findList("SelfAppManager.selectPhoto.query", queryMap, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					StudentDto student = new StudentDto();
					student.setPhotoUrl(NestUtil.isEmpty(rs.getString("photo"))?constantBean.get("defaultPhotosUrl"):constantBean.get("photo_nginx_server")+rs.getString("photo").replace("\\","/"));
					return student;
				}
			});
		} catch (Exception e) {
			logger.error("selectPhoto(Map<String, Object>)",e);
		}
		return null;
	}
	/**
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * clob转String
	 */
	public static String ClobToString(CLOB clob) throws SQLException, IOException { 
		String reString = ""; 
		Reader is = clob.getCharacterStream();// 得到流 
		BufferedReader br = new BufferedReader(is); 
		String s = br.readLine(); 
		StringBuffer sb = new StringBuffer(); 
		while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer由StringBuffer转成STRING 
		sb.append(s); 
		s = br.readLine(); 
		} 
		reString = sb.toString(); 
		return reString; 
		}
	
	public String insertSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto) {
		String insertid = baseInforManagerExt.queryProKey("A_APPRASIAL");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",insertid);
		params.put("appraisaltypeid",appraisalCacheDto.getAppraisaltypeid());
		params.put("appraiserrid",appraisalCacheDto.getAppraiserrid());
		params.put("apprasial",appraisalCacheDto.getApprasial());
		params.put("studentid",appraisalCacheDto.getStudentid());
		params.put("appraseridentify",appraisalCacheDto.getAppraseridentify());
		params.put("appraser",appraisalCacheDto.getAppraser());
		params.put("edu_id",appraisalCacheDto.getEdu_id());
		params.put("semesterid",appraisalCacheDto.getSemesterid());
		params.put("cmis30id",appraisalCacheDto.getCmis30id());
		params.put("discode",appraisalCacheDto.getDiscode());
		try {
			//java.util.Date valueOf = java.sql.Date.valueOf(appraisalCacheDto.getSigndate());
			params.put("signdate",appraisalCacheDto.getSigndate());
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisal.addCache1");
			appraisalCacheDto.setApprasialid(insertid);
			latestEvaluationRecordExt.addRecodeInCacheByProKey(appraisalCacheDto.getEdu_id(), appraisalCacheDto.getSemesterid(), appraisalCacheDto.getAppraisaltypeid(),
					appraisalCacheDto.getAppraseridentify(), appraisalCacheDto.getAppraiserrid(), insertid, appraisalCacheDto, sqlElement,appraisalCacheDto.getSigndate());
			return insertid;
		} catch (Exception e) {
			logger.error("insertSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto)", e);
		}
		return null;
	}


	@Override
	public String insertSelfAppraisalwithCache(AapprasialCacheDto appraisalCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisalCacheDto.getApprasialid());
		params.put("appraisaltypeid",appraisalCacheDto.getAppraisaltypeid());
		params.put("appraiserrid",appraisalCacheDto.getAppraiserrid());
		params.put("apprasial",appraisalCacheDto.getApprasial());
		params.put("studentid",appraisalCacheDto.getStudentid());
		params.put("appraseridentify",appraisalCacheDto.getAppraseridentify());
		params.put("appraser",appraisalCacheDto.getAppraser());
		params.put("signdate",appraisalCacheDto.getSigndate());
		params.put("edu_id",appraisalCacheDto.getEdu_id());
		params.put("semesterid",appraisalCacheDto.getSemesterid());
		params.put("cmis30id",appraisalCacheDto.getCmis30id());
		params.put("discode",appraisalCacheDto.getDiscode());
		try {
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.insertSelfAppraisal.addCache");
			latestEvaluationRecordExt.addRecodeInCacheByProKey(appraisalCacheDto.getEdu_id(), appraisalCacheDto.getSemesterid(), appraisalCacheDto.getAppraisaltypeid(),
					appraisalCacheDto.getAppraseridentify(), appraisalCacheDto.getAppraiserrid(), appraisalCacheDto.getApprasialid(), appraisalCacheDto, sqlElement,appraisalCacheDto.getSigndate());
		} catch (Exception e) {
			logger.error("insertSelfAppraisalwithCache(AapprasialCacheDto appraisalCacheDto)", e);
		}
		return null;
	}

	@Override
	public boolean updateSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisalCacheDto.getApprasialid());
		params.put("apprasial",appraisalCacheDto.getApprasial());
		params.put("appraseridentify",appraisalCacheDto.getAppraseridentify());
		try {
			String personid = "1";
			//java.util.Date valueOf = java.sql.Date.valueOf(appraisalCacheDto.getSigndate());
			params.put("signdate",appraisalCacheDto.getSigndate());
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.updateSelfAppraisal.updateCache1");
			latestEvaluationRecordExt.updateRecodeInCacheByProKey(appraisalCacheDto.getEdu_id(), appraisalCacheDto.getSemesterid(), 
					appraisalCacheDto.getAppraisaltypeid(), personid, appraisalCacheDto.getAppraiserrid(),
					appraisalCacheDto.getApprasialid(), appraisalCacheDto, sqlElement,appraisalCacheDto.getSigndate());
			return true;
		} catch (Exception e) {
			logger.error("updateSelfAppraisalCache(AapprasialCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}


	@Override
	public boolean deleteSelfAppraisalCache(AapprasialCacheDto appraisalCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisalCacheDto.getApprasialid());
		params.put("cmis30id",appraisalCacheDto.getCmis30id());
		params.put("discode",appraisalCacheDto.getDiscode());
		try {
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deleteSelfAppraisal.deleteCache");
			latestEvaluationRecordExt.delRecodeInCacheByProKey(appraisalCacheDto.getEdu_id(), appraisalCacheDto.getSemesterid(),
					appraisalCacheDto.getAppraisaltypeid(), appraisalCacheDto.getAppraseridentify(),
					appraisalCacheDto.getAppraiserrid(), appraisalCacheDto.getApprasialid(), sqlElement,AapprasialCacheDto.class);
			return true;
		} catch (Exception e) {
			logger.error("deleteSelfAppraisalCache(AapprasialCacheDto)",e);
			//当涉及到对数据进行更新和操作时，需要抛出异常，回滚事务，避免产生脏数据
			throw new ManagerException(e);
		}
	}


	@Override
	public boolean deleteSelfAppraisalWithattCache(AapprasialCacheDto appraisalCacheDto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid",appraisalCacheDto.getApprasialid());
		params.put("cmis30id",appraisalCacheDto.getCmis30id());
		params.put("discode",appraisalCacheDto.getDiscode());
		Map<String,Object> params1=new HashMap<String,Object>();
		params1.put("recordid",appraisalCacheDto.getApprasialid());
		try {
			ISqlElement sqlElement=this.processSql(params,"SelfAppManager.deleteSelfAppraisal.deleteCache");
			latestEvaluationRecordExt.delRecodeInCacheByProKey(appraisalCacheDto.getEdu_id(), appraisalCacheDto.getSemesterid(),
					appraisalCacheDto.getAppraisaltypeid(), appraisalCacheDto.getAppraseridentify(),
					appraisalCacheDto.getAppraiserrid(), appraisalCacheDto.getApprasialid(), sqlElement,AapprasialCacheDto.class);
			ISqlElement sqlElement1=this.processSql(params1,"SelfAppManager.deletetSelfPackageAttach.deleteCache");
			latestEvaluationRecordExt.deleteAttachFileAllByforeignKey(appraisalCacheDto.getApprasialid(), "a_attach", appraisalCacheDto.getAppraisaltypeid(), sqlElement1,AattachCacheDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
