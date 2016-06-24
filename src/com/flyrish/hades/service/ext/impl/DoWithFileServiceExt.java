package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.HuploadOldfile;
import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IDoWithFileServiceExt;
import com.flyrish.hades.util.DataSource;

public class DoWithFileServiceExt extends JdbcRootManager implements
		IDoWithFileServiceExt {

	public Boolean doWithStartUploadFile(String uuid, String filename,
			String schoolcode, String uploadfiletype) {
		if(NestUtil.isEmpty(schoolcode))return false;
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("uuid",uuid);
		params.put("Filename",filename);
		params.put("Schoolcode",schoolcode);
		params.put("Type",uploadfiletype);
		params.put("State","start");
		try{
			ISqlElement sqlParams=this.processSql(params,"DoWithFileServiceExt.doWithStartUploadFile.insert");
			this.getJdbcTemplate().update(sqlParams.getSql(),sqlParams.getParams());
			return true;
		}catch(Exception ex){
			logger.error("doWithStartUploadFile(String,String,String,String)",ex);
			return false;
		}
	}
    public Boolean doWithEndUploadFileAll(String schoolcode,String filetype,String uuid,String filepath){
    	//把之前上传的同类型文件置为无效
    	setValidFileInfo(schoolcode, filetype);
    	//更新信息
    	return doWithEndUploadFile(uuid,filepath);
    }
    //把上传的历史记录中，已成功上传的文件置为无效
	private void setValidFileInfo(String schoolcode, String filetype) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("schoolcode",schoolcode);
		params.put("filetype",filetype);
		try{
			ISqlElement sqlParams=this.processSql(params,"DoWithFileServiceExt.setValidFileInfo.update");
			this.getJdbcTemplate().update(sqlParams.getSql(),sqlParams.getParams());
		}catch(Exception e){
			logger.error("setValidFileInfo(String,String)", e);
			throw new ManagerException(e);
		}
	}
	private Boolean doWithEndUploadFile(String uuid,String filepath) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("uuid",uuid);
		params.put("Filepath",filepath);
		params.put("State","done");
		try{
			ISqlElement sqlParams=this.processSql(params,"DoWithFileServiceExt.doWithEndUploadFile.update");
			this.getJdbcTemplate().update(sqlParams.getSql(),sqlParams.getParams());
			return true;
		}catch(Exception ex){
			logger.error("doWithEndUploadFile(String,String,String)",ex);
			throw new ManagerException(ex);
		}
	}
	public List<HuploadOldfile> queryHuploadOldfileList(String schoolcode) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("schoolcode",schoolcode);
		try{
			String sql="select Filename,to_char(Uploadtime,'yyyy-MM-dd HH24:MI:SS') Uploadtime,"+
							"case State"+
							     " when 'start' then '上传失败或者正在上传中'"+
							      " when 'done' then '上传成功'"+
							      " when 'cast' then '该文件已经废止'"+
							" end state"+
							" from h_upload_oldfile"+
							" where Schoolcode='"+schoolcode+"'"+
							" order by Uploadtime desc";
			return this.getJdbcTemplate().query(sql, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					HuploadOldfile dto=new HuploadOldfile();
					dto.setFileName(rs.getString("Filename"));
					dto.setState(rs.getString("state"));
					dto.setUploadTime(rs.getString("Uploadtime"));
					return dto;
				}
			});
		}catch(Exception ex){
			logger.error("queryHuploadOldfileList(String)", ex);
		}
		return null;
	}
	
}
