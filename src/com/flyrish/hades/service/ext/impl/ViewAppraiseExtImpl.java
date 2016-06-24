package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.dto.ClassDto;
import com.flyrish.hades.dto.SchoolreportDto;
import com.flyrish.hades.dto.StudentDto;
import com.flyrish.hades.service.ext.IViewAppraiseExt;
import com.flyrish.hades.util.DataSource;

 

public class ViewAppraiseExtImpl extends JdbcRootManager implements IViewAppraiseExt  {


	@Override
	public List<ClassDto> getClassList(String cmis30id, String graduateyear,
			String discode, String levelCode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("cmis30id", cmis30id);
			params.put("year", graduateyear);
			params.put("discode", discode);
			params.put("levelcode", levelCode);
			String levelCode1 = levelCode;
			if(levelCode.equals("2011003")){
				levelCode1 = "2011004";
			}else if(levelCode.equals("2011004")){
				levelCode1 = "2011003";
			}
			params.put("levelcode1", levelCode1);
			this.processSql(params, "IViewAppraiseExt.getClassList.query");
			return this.findList("IViewAppraiseExt.getClassList.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					Map<String, String> map = new HashMap<String, String>();
					map.put("classid", rs.getString("classid"));
					map.put("classname", rs.getString("classsname"));
					return map;
				}
			});
		} catch (Exception e) {
			logger.error("IViewAppraiseExt.getClassList()", e);
		}
		return null;
	}
	

	/**
	 * 查询学生
	 * */
	@DataSource("read")
	public  StudentDto getStudentByPager(String classid, String cmis30id, String discode, String year, String termid, String levelCode, long index) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("levelcode", levelCode);
			String levelCode1 = levelCode;
			if(levelCode.equals("2011003")){
				levelCode1 = "2011004";
			}else if(levelCode.equals("2011004")){
				levelCode1 = "2011003";
			}
			params.put("levelcode1", levelCode1);
			params.put("year", year);
			params.put("index", index);
			params.put("termid", termid);
			this.processSql(params, "IViewAppraiseExt.getStudentByPager.query");
			List<Object> list = this.findList("IViewAppraiseExt.getStudentByPager.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
				
					StudentDto stu  = new StudentDto();
					stu.setStudentid(rs.getString("studentid")); //学生id 
					stu.setLevelid(rs.getInt("levelid")); //学区
					stu.setLevelcode(rs.getInt("levelcode")); 
					stu.setName(rs.getString("name"));
					stu.setStudentno(rs.getString("studentno"));
					stu.setEduid(rs.getString("edu_id")); 
					return stu;
				}
			});
			if(list == null || list.size() == 0){
				return new StudentDto();
			}
			return (StudentDto) list.get(0);
		} catch (Exception e) {
			logger.error("IViewAppraiseExt.getStudentByPager()", e);
		}
		return null;
	}
	/**
	 * 查询学生总数
	 * */
	@DataSource("read")
	public  Long getStudentTotal(String classid, String cmis30id, String discode, String year, String termid, String levelCode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("classid", classid);
			params.put("cmis30id", cmis30id);
			params.put("discode", discode);
			params.put("levelcode", levelCode);
			String levelCode1 = levelCode;
			if(levelCode.equals("2011003")){
				levelCode1 = "2011004";
			}else if(levelCode.equals("2011004")){
				levelCode1 = "2011003";
			}
			params.put("levelcode1", levelCode1);
			params.put("year", year);
			params.put("termid", termid);
			this.processSql(params, "IViewAppraiseExt.getStudentTotal.query");
			return (Long) this.findList("IViewAppraiseExt.getStudentTotal.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					return rs.getLong("total");
				}
			}).get(0);
		} catch (Exception e) {
			logger.error("IViewAppraiseExt.getStudentTotal()", e);
		}
		return 0L;
	}


	@Override
	public List<SchoolreportDto> querySchool(String discode, String levelcode) {
		try {
			Map<String,Object>params = new HashMap<String, Object>();
			params.put("discode", discode);
			params.put("levelcode", levelcode);
			String levelCode1 = levelcode;
			if(levelcode.equals("2011003")){
				levelCode1 = "2011004";
			}else if(levelcode.equals("2011004")){
				levelCode1 = "2011003";
			}
			params.put("levelcode1", levelCode1);
			this.processSql(params, "IViewAppraiseExt.querySchool.query");
			return this.findList("IViewAppraiseExt.querySchool.query", params, new RowMapper(){
				public Object mapRow(ResultSet rs, int num)
						throws SQLException {
					Map<String, String> school = new HashMap<String, String>();
					school.put("cmis30id", String.valueOf(rs.getInt("cmis30id")));
					school.put("schoolname", rs.getString("schoolname"));
					return school;
				}
			});
		} catch (Exception e) {
			logger.error("IViewAppraiseExt.querySchool()", e);
		}
		return null;
	}

}
