package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.hibernate.IRowHandler;
import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.HOinfromreadDto;
import com.flyrish.hades.dto.InformDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IInformQueryExt;
import com.flyrish.hades.util.DataSource;

public class InformQueryExtImpl extends JdbcRootManager implements IInformQueryExt{
     
	@DataSource("read")
	public List<String> schoolQuerylevel(String cmis30id, String discode,String campusid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cmis30id", cmis30id);
		params.put("discode", discode);
		params.put("campusid", campusid);
		List<String> levellist = new ArrayList<String>();
		try {
			levellist = this.findList("InformQueryExtImpl.schoolQuerylevel.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String levelcode = rs.getString("levelcode");
					return levelcode;
				}
			});
		} catch (Exception e) {
			logger.error("schoolQuerylevel(String,String)",e);
		}
		return levellist;
	}
    @DataSource("")
	public void insertInform(Map<String,Object> params) {
		try {
			ISqlElement sqlelement = this.processSql(params,"InformQueryExtImpl.insertInform.add");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("insertInform(Map)", e);
			throw new ManagerException();
		}
	}
	@DataSource("read")
	public List<String> masterQuerylevel(String personid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personid", personid);
		List<String> levellist = new ArrayList<String>();
		try {
			levellist = this.findList("InformQueryExtImpl.masterQuerylevel.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String levelcode = rs.getString("levelcode");
					return levelcode;
				}
			});
		} catch (Exception e) {
			logger.error("masterQuerylevel(String)",e);
		}
		return levellist;
	}
    @DataSource("")
	public IPage<InformDto> informQuery(Map<String,Object> params,Integer pageNo,Integer pageSize) {
		try {
			return this.findPage("InformQueryExtImpl.informQuery.query","InformQueryExtImpl.informQuery.count",params,pageNo,pageSize,new IRowHandler<InformDto>(){
				public InformDto handleRow(ResultSet rs) {
					try {
						InformDto inform = new InformDto();
						inform.setInformid(rs.getString("inform_id"));
						inform.setTheme(rs.getString("motif"));
						inform.setText(rs.getString("text"));
						inform.setFilename(rs.getString("filename"));
						inform.setFilepath(rs.getString("filepath"));
						inform.setPublishState(rs.getString("issue"));
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						inform.setEndDate(rs.getDate("end_date")==null?null:sdf.format(rs.getDate("end_date")));
						inform.setStartDate(rs.getDate("issue_date")==null?null:sdf.format(rs.getDate("issue_date")));
						inform.setStatename(rs.getString("issuename"));
						inform.setReceiverObj(rs.getString("obj_role"));
						inform.setObjlevel(rs.getString("obj_levelcode"));
						inform.setFlag(rs.getString("flag"));
						return inform;
					} catch (Exception e) {
					}
					return null;
				}
				});
			
		} catch (Exception e) {
			logger.error("informQuery(Map,Integer,Integer)",e);
		}
		return null;
	}
    @DataSource("")
   	public List<InformDto> informlistQuery(Map<String,Object> params) {
   		List<InformDto> informlist = new ArrayList<InformDto>();
   		try {
   			informlist = this.findList("InformQueryExtImpl.informQuery.query", params, new RowMapper() {
   				
   				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
   					InformDto inform = new InformDto();
   					inform.setInformid(rs.getString("inform_id"));
   					inform.setTheme(rs.getString("motif"));
   					inform.setText(rs.getString("text"));
   					inform.setFilename(rs.getString("filename"));
   					inform.setFilepath(rs.getString("filepath"));
   					inform.setPublishState(rs.getString("issue"));
   					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
   					inform.setEndDate(rs.getDate("end_date")==null?null:sdf.format(rs.getDate("end_date")));
   					inform.setStartDate(rs.getDate("issue_date")==null?null:sdf.format(rs.getDate("issue_date")));
   					sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
   					inform.setStartMoreDate(rs.getDate("issue_date")==null?null:sdf.format(rs.getDate("issue_date")));
   					inform.setStatename(rs.getString("issuename"));
   					inform.setReceiverObj(rs.getString("obj_role"));
   					inform.setObjlevel(rs.getString("obj_levelcode"));
   					inform.setCampusid(rs.getString("campusid"));
   					inform.setUserid(rs.getString("issue_userid"));
   					inform.setPublishDiscode(rs.getString("issue_discode"));
   					inform.setIsall(rs.getString("is_all")); 
   					return inform;
   				}
   			});
   		} catch (Exception e) {
   			logger.error("informQuery(Map)", e);
   		}
   		return informlist;
   	}
    @DataSource("")
	public void deleteInform(Map<String, Object> params) {
		
		try {
			ISqlElement sqlelement = this.processSql(params,"InformQueryExtImpl.deleteInform.delete");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("insertInform(Map)", e);
			throw new ManagerException();
		}
	}
	public void publicInform(Map<String, Object> params) {
		try {
			ISqlElement sqlelement = this.processSql(params,"InformQueryExtImpl.publicInform.update");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("publicInform(Map)", e);
			throw new ManagerException();
		}
	}
	public void updateInform(Map<String, Object> params) {
		try {
			Map<String,Object> pa=new HashMap<String,Object>();
			pa.put("theme",params.get("theme"));
			pa.put("informContent", params.get("informContent"));
			pa.put("endtime",params.get("endtime"));
			pa.put("startDate",params.get("startDate"));
			pa.put("receiverObj",params.get("receiverObj"));
			pa.put("objlevel",params.get("objlevel"));
			pa.put("informid",params.get("informid"));
			String sqlKey="";
			if(params.containsKey("filepath")){
				pa.put("filepath",params.get("filepath"));
				pa.put("filename",params.get("filename"));
				sqlKey="InformQueryExtImpl.updateInform.update";
			}else{
				sqlKey="InformQueryExtImpl.updateInform.update2";
			}
			ISqlElement sqlelement = this.processSql(params,sqlKey);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("updateInform(Map)", e);
			throw new ManagerException();
		}
	}
	public String queryUserid(Map<String, Object> params) {
		List<String> idlist = new ArrayList<String>();
		String userid = "";
		try {
			idlist = this.findList("InformQueryExtImpl.queryUserid.get", params, new RowMapper() {
				
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("userid");
				}
			});
		} catch (Exception e) {}
		if(idlist!=null&&idlist.size()>0)
		{
			userid = idlist.get(0);
		}
		return userid;
	}
	public void insertRead(Map<String, Object> params) {
		try {
			ISqlElement sqlelement = this.processSql(params,"InformQueryExtImpl.insertRead.insert");
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("insertRead(Map)", e);
			throw new ManagerException();
		}
	}
	public int beforeInsertRead(Map<String, Object> params) {
		List<String> countlist = new ArrayList<String>();
		int count = 0;
		try {
			countlist = this.findList("InformQueryExtImpl.beforeInsertRead.select", params, new RowMapper() {
				
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("readcount");
				}
			});
		} catch (Exception e) {}
		if(countlist!=null&&countlist.size()>0)
		{
			count = Integer.parseInt(countlist.get(0));
		}
		return count;
	}
	public List<AppraisalDto> queryAppraisal(Map<String, Object> params,
			String levelcode,String usertype) {
		List<AppraisalDto> appraisal = new ArrayList<AppraisalDto>();
		if((Integer.parseInt(levelcode))==(Constant.DICT_TYPE_LEVELCODE_GZ) || (Integer.parseInt(levelcode)) == (Constant.DICT_TYPE_LEVELCODE_GZYK))
		{
			if(Constant.USER_TYPE_CLASSMASTER.equals(usertype))   //班主任
			{
				queryAppraise(params,appraisal);            //查询a_appraisal表
				queryAssess(params,appraisal);                  //班主任评语
			}else if(Constant.USER_TYPE_COURSEMASTER.equals(usertype))   //任课老师
			{
				queryAppraise(params,appraisal);            //查询a_appraisal表
				queryLearnprocessAppraisal(params,appraisal);   //课程评语
			}else if(Constant.USER_TYPE_STUDENT.equals(usertype))    //学生
			{
				queryAppraise(params,appraisal);            //查询a_appraisal表
				queryRecordpackage(params,appraisal);        //查询记录袋
				queryPractices(params,appraisal);             //综合实践活动
				queryApersonality(params,appraisal);        //个性发展基本过程
				queryAlearnprocessWorks(params,appraisal);    //学科学习过程记录
				queryLearnprocessAppraisal(params,appraisal);   //课程评语
				queryAssess(params,appraisal);                  //班主任评语
			}else if(Constant.USER_TYPE_PARENT.equals(usertype))     //家长
			{
				queryAppraise(params,appraisal);            //查询a_appraisal表
				queryRecordpackage(params,appraisal);        //查询记录袋
				queryPractices(params,appraisal);             //综合实践活动
				queryApersonality(params,appraisal);        //个性发展基本过程
				queryAlearnprocessWorks(params,appraisal);    //学科学习过程记录
				queryLearnprocessAppraisal(params,appraisal);   //课程评语
				queryAssess(params,appraisal);                  //班主任评语
			}
			
		}else if((Integer.parseInt(levelcode)) ==(Constant.DICT_TYPE_LEVELCODE_CZ))  
		{
			if(Constant.USER_TYPE_CLASSMASTER.equals(usertype))   //班主任
			{
				querymiddleAppraise(params,appraisal);
				queryAssess(params,appraisal);
			}else if(Constant.USER_TYPE_COURSEMASTER.equals(usertype))   //任课老师
			{
				querymiddleAppraise(params,appraisal);
			}else if(Constant.USER_TYPE_STUDENT.equals(usertype))    //学生
			{
				querymiddleAppraise(params,appraisal);
				queryAssess(params,appraisal);
			}else if(Constant.USER_TYPE_PARENT.equals(usertype))     //家长
			{
				querymiddleAppraise(params,appraisal);
				queryAssess(params,appraisal);
			}
		}
		return appraisal;
	}
	/**
	 * 查询a_appraisal表
	 * @param params
	 * @param appraisal
	 */
    private void queryAppraise(Map<String, Object> params,List<AppraisalDto> appraisal)
    {
    	try {
    		ISqlElement sqlString = this.processSql(params, "InformQueryExtImpl.queryAppraise.query");
			List<AppraisalDto> selfappraiseList =  this.findList("InformQueryExtImpl.queryAppraise.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(selfappraiseList);
		} catch (Exception e) {
			logger.error("queryAppraise(Map<String,Object>,List<AppraisalDto>)",e);
		}
    }
   /**
     * 初中评价信息
     * @param params
     * @param appraisal
     */
    private void querymiddleAppraise(Map<String, Object> params,List<AppraisalDto> appraisal)
    {
    	try {
			List<AppraisalDto> selfappraiseList =  this.findList("InformQueryExtImpl.querymiddleAppraise.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(selfappraiseList);
		} catch (Exception e) {
			logger.error("queryAppraise(Map<String,Object>,List<AppraisalDto>)",e);
		}
    }
    /**
     * 查询记录袋
     * @param params
     * @param appraisal
     */
    @DataSource("read")
	private void queryRecordpackage(Map<String, Object> params,List<AppraisalDto> appraisal) {
		try {
			List<AppraisalDto> list =  this.findList("InformQueryExtImpl.queryRecordpackage.query",params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(list);
		} catch (Exception e) {
			logger.error("queryRecordpackage(Map<String,Object>,List<AppraisalDto>)",e);
		}
	}
    /**
     * 综合实践活动查询
     * @param params
     * @param appraisal
     */
    @DataSource("read")
	private void queryPractices(Map<String, Object> params,List<AppraisalDto> appraisal) {
		try {
			
			List<AppraisalDto> list =  this.findList("InformQueryExtImpl.queryPractices.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			
			appraisal.addAll(list);
			
		} catch (Exception e) {
			logger.error("queryPractices(Map<String,Object>,List<AppraisalDto>)",e);
		}
	}
    /**
     * 个性发展基本情况
     * @param params
     * @param appraisal
     */
    private  void queryApersonality(Map<String, Object> params,List<AppraisalDto> appraisal){
		try {
			List<AppraisalDto> list =  this.findList("InformQueryExtImpl.queryApersonality.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(list);
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorks(Map<String,Object>,List<AppraisalDto>)",e);
		}
	}
    /**
     * 学科学习过程记录查询
     * @param params
     * @param appraisal
     */
    private  void queryAlearnprocessWorks(Map<String,Object> params,List<AppraisalDto> appraisal){
		try {
			List<AppraisalDto> list =  this.findList("InformQueryExtImpl.queryAlearnprocessWorks.query", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(list);
		} catch (Exception e) {
			logger.error("queryAlearnprocessWorks(Map<String,Object>,List<AppraisalDto>)",e);
		}
    }
    /**
     * 课程评语查询
     * @param params
     * @param appraisal
     */
    private void queryLearnprocessAppraisal(Map<String, Object> params,List<AppraisalDto> appraisal) {
		try {
			List<AppraisalDto> learnprocessAppraisalList =  this.findList("InformQueryExtImpl.queryLearnprocessAppraisal.query1", params, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					AppraisalDto app = new AppraisalDto();
					app.setStudentName(rs.getString("name"));
					app.setAppraisaltype(rs.getString("appraisaltype"));
					app.setAppraseridentity(rs.getString("identifyname"));
					app.setSigndate1(rs.getString("edittime"));
					return app;
				}
			});
			appraisal.addAll(learnprocessAppraisalList);
		} catch (Exception e) {
			logger.error("queryaLearnprocessAppraisal(Map<String,Object>,List<AppraisalDto>)",e);
		}
	}
    /**
	 * 初高中班主任评语查询
	 
	 * @param params 参数列表
	 * @param appraiseBaseDto 总集合
	 * @return
	 */
	@DataSource("read")
	private void queryAssess(Map<String, Object> params,List<AppraisalDto> appraisal) {
			try {
				ISqlElement sqlString = this.processSql(params, "InformQueryExtImpl.queryAssess.query");
				List<AppraisalDto> assessList =  this.findList("InformQueryExtImpl.queryAssess.query", params, new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						AppraisalDto app = new AppraisalDto();
						app.setStudentName(rs.getString("name"));
						app.setAppraisaltype(rs.getString("appraisaltype"));
						app.setAppraseridentity(rs.getString("identifyname"));
						app.setSigndate1(rs.getString("signdate"));
						return app;
					}
				});
				appraisal.addAll(assessList);
			} catch (Exception e) {
				logger.error("queryAssess(Map<String,Object>,List<AppraisalDto>)",e);
			}
			
		}
	@DataSource("read")
	public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params) {
		try {
			//学生信息
			ISqlElement sqlDemo=this.processSql(params, "InformQueryExtImpl.getStudentInfo.query");
			return this.findList("InformQueryExtImpl.getStudentInfo.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					SchoolTreeDto dto = new SchoolTreeDto();
					dto.setId(rs.getString("studentid"));
					dto.setText(rs.getString("name"));
					dto.setEdusyId(rs.getString("edu_id"));
					return dto;
				}
			});
		} catch (Exception e) {
			logger.error("getClasssInfo(Map<String,Object>)", e);
		}
		return null;
	}
	@DataSource("read")
	public void setHoiRead(List<InformDto> informDtos) {
		 try {
			List<HOinfromreadDto> informreadDtos = new ArrayList<HOinfromreadDto>(0);
			informreadDtos = this.findList("InformQueryExtImpl.setHoiRead.select", new RowMapper(){

				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					HOinfromreadDto informreadDtos = new HOinfromreadDto();
					informreadDtos.setInform_id(rs.getString("inform_id"));
					informreadDtos.setInformreadid(rs.getString("informreadid"));
					informreadDtos.setUserid(rs.getString("userid"));
					return informreadDtos;
				}
				 
			 });
			ConcurrentHashMap<String, String> readMap;
			if (informreadDtos != null && informreadDtos.size() > 0) {
				for (InformDto inform : informDtos) {
					readMap = new ConcurrentHashMap<String, String>();
					for(HOinfromreadDto informread : informreadDtos)
					{
						if(inform.getInformid().equals(informread.getInform_id()))
						{
							readMap.put(informread.getUserid(), informread.getUserid());
						}
					}
					inform.setReadMap(readMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
