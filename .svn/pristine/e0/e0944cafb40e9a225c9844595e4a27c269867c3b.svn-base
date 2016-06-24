package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.exception.ManagerException;
import com.flyrish.hades.service.ext.IMergedSchoolDataExt;

public class MergedSchoolDataExtImpl extends JdbcRootManager implements IMergedSchoolDataExt {

	@Override
	public void doWithMergedSchoolData(String cmis30id,String mergedCmis30id) {
		if(NestUtil.isEmpty(mergedCmis30id)||NestUtil.isEmpty(cmis30id))return;
		//合并课程表k_course
		mergeCourse(cmis30id,mergedCmis30id);
		System.out.println("--------------课程表已合并完毕---------------------");
		//合并k_student_model表
		insertStudetnModel(mergedCmis30id);
		System.out.println("--------------内置课程成绩已合并完毕----------------");
		//合并k_student_matriculate表
		insertStudentMatriculate(mergedCmis30id);
		System.out.println("--------------校本课程成绩已合并完毕-----------------");
		//更新会考表
		updateHKScore(mergedCmis30id);
		System.out.println("----------会考成绩已合并完毕----------------");
		//合并k_segment_model表外键
		mergeSegmentModel(cmis30id,mergedCmis30id);
		System.out.println("---------------k_segment_model已合并完毕-------------");
		//合并k_study_segment
		mergeSegmentCourse(cmis30id,mergedCmis30id);
		System.out.println("--------------k_study_segment已合并完毕-------------------");
	}
	private void mergeSegmentCourse(String cmis30id,String mergedCmis30id){
		final List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		String querySql="select distinct kse.segment_course_id,mainkss.segment_id from k_segment_course kse "+
						"inner join k_study_segment kss on kse.segment_id=kss.segment_id and kss.cmis30id="+mergedCmis30id+
						" inner join k_study_segment mainkss on mainkss.schoolyear=kss.schoolyear and mainkss.segment_order=kss.segment_order and mainkss.cmis30id="+cmis30id;
		this.getJdbcTemplate().query(querySql,new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> dt=new HashMap<String,Object>();
				dt.put("segment_course_id",rs.getString("segment_course_id"));
				dt.put("segment_id",rs.getString("segment_id"));
				datas.add(dt);
				return null;
			}
			
		});
		String updateSql="update k_segment_course set segment_id=? where segment_course_id=?";
		String[] fieldNames={"segment_id","segment_course_id"};
		this.batchUpdateObjects(datas, fieldNames,updateSql);
	}
	private void mergeSegmentModel(String cmis30id,String mergedCmis30id){
		final List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		String querySql="select distinct kse.sement_model_id,mainkss.segment_id from k_segment_model kse "+
						"inner join k_study_segment kss on kse.segment_id=kss.segment_id and kss.cmis30id="+mergedCmis30id+
						" inner join k_study_segment mainkss on mainkss.schoolyear=kss.schoolyear and mainkss.segment_order=kss.segment_order and mainkss.cmis30id="+cmis30id;
		this.getJdbcTemplate().query(querySql,new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Map<String,Object> dt=new HashMap<String,Object>();
				dt.put("sement_model_id",rs.getString("sement_model_id"));
				dt.put("segment_id",rs.getString("segment_id"));
				datas.add(dt);
				return null;
			}
			
		});
		String updateSql="update k_segment_model set  segment_id=? where sement_model_id=?";
		String[] fieldNames={"segment_id","sement_model_id"};
		this.batchUpdateObjects(datas, fieldNames,updateSql);
	}
	private void mergeCourse(String cmis30id,String mergedCmis30id){
		try{
			String updateSql="update k_course set cmis30id="+cmis30id+",course_name=course_name||'-合' where cmis30id="+mergedCmis30id;
			this.getJdbcTemplate().update(updateSql);
		}catch(Exception e){
			logger.info("mergeCourse(String,String)",e);
			throw new ManagerException(e);
		}
	}
	private void updateHKScore(String cmis30id){
		try{
			String insertSql="insert into K_GENERAL_EXAMINATION_SCORE(general_examination_score_id,student_id,subject_id,level_name,modify_time,OLD_PROKEY,CMIS30ID,discode)"+
							"select s_K_GENERAL_EXAMINATION_SCORE.nextval,kge.student_id,subject_id,level_name,modify_time,OLD_PROKEY,mg.mergeschoolcmis30id,bb.discode from K_GENERAL_EXAMINATION_SCORE kge "+
							"inner join mergestudent mg on kge.student_id=mg.studentid "+
							"inner join b_baseinfo bb on bb.cmis30id=mg.mergeschoolcmis30id "+
							"where mergedschoolcmis30id="+cmis30id;
			this.getJdbcTemplate().update(insertSql);
		}catch(Exception e){
			logger.info("updateHKScore(String)",e);
			throw new ManagerException(e);
		}
	}
	private void insertStudetnModel(String cmis30id){
		try{
			String insertSql="insert into k_student_model(credit_id,studentid,classid,class_model_id,"+
					"daily_behave,examine_result,absence_ration,is_pass,nopass_reason,course_kind,course_name,credit_hour,"+
					"credit_source,Exemption_reason,Exemption_path,Exemption_name,cognizance_status,appeal_reason,appeal_restore,appeal_status,"+
					"service_content,service_days,import_desc,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,"+
					"peacetime6,peacetime7,peacetime8,peacetime9,peacetime10,peacetime11,peacetime12,peacetime13,"+
					"peacetime14,peacetime15,peacetime16,peacetime17,peacetime18,peacetime19,peacetime20,title1,"+
					"title2,title3,title4,title5,title6,title7,title8,title9,title10,title11,title12,credit_kind,history_class_name,"+
					"model_credit,old_credit_id,CMIS30ID,discode,old_class_model_id) "+
					"select S_K_STUDENT_MODEL.nextval,ksm.studentid,null,class_model_id,"+
					"daily_behave,examine_result,absence_ration,is_pass,nopass_reason,course_kind,course_name,credit_hour,"+
					"credit_source,Exemption_reason,Exemption_path,Exemption_name,cognizance_status,appeal_reason,appeal_restore,appeal_status,"+
					"service_content,service_days,import_desc,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,"+
					"peacetime6,peacetime7,peacetime8,peacetime9,peacetime10,peacetime11,peacetime12,peacetime13,"+
					"peacetime14,peacetime15,peacetime16,peacetime17,peacetime18,peacetime19,peacetime20,title1,"+
					"title2,title3,title4,title5,title6,title7,title8,title9,title10,title11,title12,credit_kind,history_class_name,"+
					"model_credit,old_credit_id,mg.mergeschoolcmis30id,bb.discode,old_class_model_id from mergestudent mg "+
					"inner join k_student_model ksm on ksm.studentid=mg.studentid "+
					"inner join b_baseinfo bb on bb.cmis30id=mg.mergeschoolcmis30id "+
					"where mergedschoolcmis30id="+cmis30id;
			this.getJdbcTemplate().update(insertSql);
		}catch(Exception e){
			logger.info("insertStudetnModel(String)",e);
			throw new ManagerException(e);
		}
	}
	private void insertStudentMatriculate(String cmis30id){
		try{
			String insertSql="insert into k_student_matriculate("+
                "matriculate_id,studentid,classid,segment_course_id,matriculate_time, matriculate_status,"+
                "sign_date,daily_behave,examine_result,absence_ration,is_pass,nopass_reason,course_kind,course_name,credit_hour,credit_source,"+
                "Exemption_reason,Exemption_path,Exemption_name,cognizance_status,appeal_reason,appeal_restore,appeal_status,wish_order,ante_score,import_desc,"+
               "peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,peacetime6,peacetime7,peacetime8,peacetime9,peacetime10,peacetime11,peacetime12,"+
                "peacetime13,peacetime14,peacetime15,peacetime16,peacetime17,peacetime18,peacetime19,peacetime20,title1,title2,title3,"+
               " title4,title5,title6,title7,title8,title9,title10,title11,title12,history_class_name,model_credit,old_matriculate_id,OLD_SEGMENT_COURSE_ID,cmis30id,discode"+
               " ) "+
               " select s_K_Student_Matriculate.nextval,ksm.studentid,null,"+
                "segment_course_id,matriculate_time,matriculate_status,sign_date,daily_behave,examine_result,absence_ration,"+
                "is_pass,nopass_reason,course_kind,course_name,credit_hour,credit_source,Exemption_reason,"+
               " Exemption_path,Exemption_name,cognizance_status,appeal_reason,appeal_restore,appeal_status,wish_order,"+
                "ante_score,import_desc,peacetime1,peacetime2,peacetime3,peacetime4,peacetime5,peacetime6,peacetime7,"+
                "peacetime8,peacetime9,peacetime10,peacetime11,peacetime12, peacetime13,peacetime14,peacetime15,"+
              " peacetime16,peacetime17,peacetime18,peacetime19,peacetime20,title1,title2,title3,title4,title5,title6,title7,title8,title9,title10,title11,title12,"+
               " history_class_name,model_credit,old_matriculate_id,OLD_SEGMENT_COURSE_ID,mg.mergeschoolcmis30id,bb.discode from mergestudent mg "+
				"inner join k_student_matriculate ksm on ksm.studentid=mg.studentid "+
				"inner join b_baseinfo bb on bb.cmis30id=mg.mergeschoolcmis30id "+
				"where mergedschoolcmis30id="+cmis30id;
			this.getJdbcTemplate().update(insertSql);
		}catch(Exception e){
			logger.info("insertStudentMatriculate(String)",e);
			throw new ManagerException(e);
		}
	}
}
