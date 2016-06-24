package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.service.ext.IAppraisalChildExt;
import com.flyrish.hades.service.ext.IBaseInforManagerExt;
import com.flyrish.hades.util.DataSource;

public class AppraisalChildExtImpl extends JdbcRootManager implements IAppraisalChildExt{
	
	private IBaseInforManagerExt baseInforManagerExt;
	public IBaseInforManagerExt getBaseInforManagerExt() {
		return baseInforManagerExt;
	}
	public void setBaseInforManagerExt(IBaseInforManagerExt baseInforManagerExt) {
		this.baseInforManagerExt = baseInforManagerExt;
	}
	@DataSource("read")
	public List<AppraisalDto> getAppraisalChildList(AppraisalDto appraisal,Integer levelcode) {
		Map<String,Object> params=new HashMap<String,Object>();
	
		params.put("semesterid", appraisal.getSemesterid());
	    params.put("edu_id",appraisal.getEduid());
        params.put("cmis30id", appraisal.getCmis30id());
        params.put("discode", appraisal.getDiscode());
		List<AppraisalDto> listappraisal=new ArrayList<AppraisalDto>();
		try {
			if(levelcode==Constant.DICT_TYPE_LEVELCODE_CZ)
			{
				params.put("appraseridentify", appraisal.getAppraseridentity());
				/*ISqlElement sqlElement=this.processSql(params,"AppraisalChildExtImpl.getAppraisalChildList.get2");
				System.out.println(sqlElement);*/
				listappraisal=this.findList("AppraisalChildExtImpl.getAppraisalChildList.get2", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						AppraisalDto appraisal=new AppraisalDto();
						appraisal.setApprasialid(rs.getString("part_id"));
						appraisal.setAppraisaltypeid(rs.getInt("two_part_id"));
						appraisal.setApprasial(rs.getString("part_info"));
						appraisal.setAppraser(rs.getString("signer_name"));
						appraisal.setSigndate(rs.getDate("createdate"));		
						return appraisal;
					}			
				});
				return listappraisal;
			}else if(levelcode==Constant.DICT_TYPE_LEVELCODE_GZ||Constant.DICT_TYPE_LEVELCODE_GZYK==levelcode){
				params.put("appraseridentify", appraisal.getAppraseridentify());
				listappraisal=this.findList("AppraisalChildExtImpl.getAppraisalChildList.get1", params, new RowMapper(){
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						AppraisalDto appraisal=new AppraisalDto();
						appraisal.setApprasialid(rs.getString("apprasialid"));
						appraisal.setAppraisaltypeid(rs.getInt("appraisaltypeid"));
						appraisal.setApprasial(rs.getString("apprasial"));
						appraisal.setAppraser(rs.getString("appraser"));
						appraisal.setSigndate(rs.getDate("signdate"));		
						return appraisal;
					}
					
				});
				return listappraisal;
			}
		} catch (Exception e) {
			logger.error("getAppraisalChildList(Integer,Interger)", e);
			
		}
		return null;
	}
    @DataSource("")
	public String InsertAppraisalChild(AppraisalDto appraisal,Integer levelcode) {
		if (logger.isDebugEnabled()) {
			logger.debug("InsertAppraisalChild(Appraisal) - start");
		}
		String insertId="";
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("studentid", appraisal.getStudentid());  
        params.put("appraisaltypeid", appraisal.getAppraisaltypeid());
        params.put("semesterid", appraisal.getSemesterid());
        params.put("appraser", appraisal.getAppraser());
        params.put("apprasial", appraisal.getApprasial());
        params.put("signdate", appraisal.getSigndate());
        params.put("edu_id",appraisal.getEduid());
        params.put("cmis30id", appraisal.getCmis30id());
        params.put("discode", appraisal.getDiscode());
        params.put("appraiserid", appraisal.getApprasialid1());
		try {
			String sqlString="";
			if(levelcode==Constant.DICT_TYPE_LEVELCODE_CZ)   //初中家长评价
			{
				params.put("appraseridentify", appraisal.getAppraseridentity());
				sqlString="AppraisalChildExtImpl.InsertAppraisalChild.add2";
				insertId=baseInforManagerExt.queryProKey("partinfo");
			}else if(levelcode==Constant.DICT_TYPE_LEVELCODE_GZ||Constant.DICT_TYPE_LEVELCODE_GZYK==levelcode) //高中家长评价
			{
				 params.put("appraseridentify", appraisal.getAppraseridentify());
				 insertId=baseInforManagerExt.queryProKey("a_apprasial");
			     sqlString="AppraisalChildExtImpl.InsertAppraisalChild.add1";	
			}
			params.put("insertId",insertId);
			ISqlElement sqlelement=this.processSql(params, sqlString);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {			
			logger.error("InsertAppraisalChild(Appraisal)", e);
		    throw new ManagerException(e);
		}
		return insertId;
	}
    @DataSource("")
	public int DeleteAppraisalChild(String appraisalid,Integer levelcode) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasialid", appraisalid);
		try {
			String sqlString="";
			if(levelcode==Constant.DICT_TYPE_LEVELCODE_CZ)   //删除初中家长评价
			{
				sqlString="AppraisalChildExtImpl.DeleteAppraisalChild.delete2";
			}else if(levelcode==Constant.DICT_TYPE_LEVELCODE_GZ||Constant.DICT_TYPE_LEVELCODE_GZYK==levelcode) //删除高中家长评价
			{
			     sqlString="AppraisalChildExtImpl.DeleteAppraisalChild.delete1";	
			}
			ISqlElement sqlelement=this.processSql(params, sqlString);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("DeleteAppraisalChild(Interger)", e);
			throw new ManagerException(e);
		}
		return 0;
	}
    @DataSource("")
	public int UpdateAppraisalChildList(AppraisalDto appraisal,Integer levelcode) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("apprasial", appraisal.getApprasial());
		params.put("signdate", appraisal.getSigndate());
		params.put("apprasialid", appraisal.getApprasialid());
		try {
			String sqlString="";
			if(levelcode==Constant.DICT_TYPE_LEVELCODE_CZ)   //初中家长评价
			{
				sqlString="AppraisalChildExtImpl.UpdateAppraisalChildList.update2";
			}else if(levelcode==Constant.DICT_TYPE_LEVELCODE_GZ||Constant.DICT_TYPE_LEVELCODE_GZYK==levelcode) //高中家长评价
			{
			     sqlString="AppraisalChildExtImpl.UpdateAppraisalChildList.update1";	
			}
			ISqlElement sqlelement=this.processSql(params, sqlString);
			getJdbcTemplate().update(sqlelement.getSql(), sqlelement.getParams());
		} catch (Exception e) {
			logger.error("UpdateAppraisalChildList(AppraisalDto)", e);
			throw new ManagerException(e);
		}
		return 0;
	}
    @DataSource("read")
	public String getParentInfo(String studentid,String cmis30id,String discode){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("studentid", studentid);
		params.put("cmis30id", cmis30id);
		params.put("discode", discode);
		List<String> students=new ArrayList<String>();
		try {
			ISqlElement sqlelement=this.processSql(params, "AppraisalChildExtImpl.getStudentInfo.get");
			students = this.findList(
					"AppraisalChildExtImpl.getStudentInfo.get", params,
					new RowMapper() {
						public Object mapRow(ResultSet rs, int arg1)
								throws SQLException {
							String parentName = rs.getString("name");
							return parentName;
						}
					});
			if(null!=students && students.size()>0){
				 return students.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			logger.error("getStudentInfo(Integer)", e);
		}
		return null;
	}
	@Override
	public ISqlElement getIsqlElement(Map<String, Object> params,
			String sqlString) {
		try {
			ISqlElement Isql = this.processSql(params, sqlString);
			return Isql;
		} catch (Exception e) {
			logger.error("getIsqlElement(Map,String)", e);
		}
		return null;
	}
}
	

