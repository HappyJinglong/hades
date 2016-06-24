package com.flyrish.hades.service.ext.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.nestframework.utils.NestUtil;
import org.springframework.jdbc.core.RowMapper;

import com.flyrish.hades.common.Constant;
import com.flyrish.hades.dto.AppraiseBaseDto;
import com.flyrish.hades.dto.StudentAppraiseInfoNumDto;
import com.flyrish.hades.service.ext.IOperationAppraiseServiceExt;
import com.flyrish.hades.service.ext.ITotalStaticsExt;
import com.flyrish.hades.util.DataSource;

public class TotalStaticsExtImpl extends JdbcRootManager implements ITotalStaticsExt{
	public IOperationAppraiseServiceExt operationAppraiseServiceExt;
	public IOperationAppraiseServiceExt getOperationAppraiseServiceExt() {
		return operationAppraiseServiceExt;
	}
	public void setOperationAppraiseServiceExt(
			IOperationAppraiseServiceExt operationAppraiseServiceExt) {
		this.operationAppraiseServiceExt = operationAppraiseServiceExt;
	}
	@Override
	public Map<String, Object> queryStudentAppraiseInfoNumDtoByInfo(
			String gradeId, String classid, String termid,String cmis30id,String discode) {
		Map<String,Object> datas=new HashMap<String,Object>();
		//通过班级标识号获取届别和班级名称
		String className=queryClassNameByClassid(classid);
		if(NestUtil.isNotEmpty(className)){
			String gradeName=className.split("_")[0];
			String pclassName=className.split("_")[1];
			datas.put("gradeName","20"+gradeName.subSequence(7, 9));
			datas.put("className",pclassName);
		}
		//先查询出指定班级所有的学生（学生姓名_教育id_学段代码_届别_班级）
		List<String> studentInfos=queryStudentInfoByCondition(classid,gradeId,cmis30id,discode);
		if(studentInfos!=null&&!studentInfos.isEmpty()){
			datas.put("totalPersonsNum",studentInfos.size()+"");
			//开始组装评价类信息数据
			List<StudentAppraiseInfoNumDto> dtos=new ArrayList<StudentAppraiseInfoNumDto>();
			for(String info:studentInfos){
				if(NestUtil.isEmpty(info)) continue;
				String[] infos=info.split("_");
				//查下出某个学生的所有评价信息
				List<AppraiseBaseDto> appraiseBaseDtos=operationAppraiseServiceExt.queryAppraiseBaseDtoByCondition(infos[0],infos[1],termid,infos[2],cmis30id,discode);
				Map<String,List<AppraiseBaseDto>> appraiseMaps;
				StudentAppraiseInfoNumDto dto;
				//高中
				if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(infos[2])
						||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(infos[2])){
					appraiseMaps=installDataMaps(appraiseBaseDtos);
					//开始统计
					dto=startGetInfoHighSchoolNum(appraiseMaps);
				}else{
					//初中
					appraiseMaps=installDataMaps2(appraiseBaseDtos);
					//开始统计
					dto=startGetInfoMidleSchoolNum(appraiseMaps);
				}
				if(dto==null)
					dto=new StudentAppraiseInfoNumDto();
				dto.setGradeYear("20"+infos[3].substring(7, 9));
				dto.setClassName(infos[4]);
				dto.setStudentName(infos[0]);
				dtos.add(dto);
			}
			datas.put("data",dtos);
		}else{
			datas.put("totalPersonsNum","0");
		}
		return datas;
	}
	private StudentAppraiseInfoNumDto startGetInfoHighSchoolNum(Map<String,List<AppraiseBaseDto>> appraiseMaps){
		if(appraiseMaps==null||appraiseMaps.isEmpty())return null;
		StudentAppraiseInfoNumDto dto=new StudentAppraiseInfoNumDto();
		//新学期伊始的我=》自我评价
		List<AppraiseBaseDto> dtos=appraiseMaps.get(Constant.TYPE_START_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setStartEcoleNum(dtos.size()+"");
		//新学期伊始的我=》我的发展目标以及家长期望
		dtos=appraiseMaps.get(Constant.TYPE_START_WDFZMB);
		if(dtos!=null&&dtos.size()>0){
			int mydelCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				//我的发展目标
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.me_apprasialidentify))
					mydelCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setStartMyDlpmentTargetNum(mydelCount+"");
			dto.setStartparentsExpectNum(parentCount+"");
		}
		//学期结束的我=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_END_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndMyEcoleNum(dtos.size()+"");
		//学期结束的我=》我的发展目标
		dtos=appraiseMaps.get(Constant.TYPE_END_WDFZMB);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndMyDlpmentTargetNum(dtos.size()+"");
		//学期结束的我=》班主任评语
		dtos=appraiseMaps.get(Constant.TYPE_END_BZRPY);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndHeadMasterAppraiseNum(dtos.size()+"");
		//学期结束的我=》家长评语和期望
		dtos=appraiseMaps.get(Constant.TYPE_END_JZPYQW);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndParentsExpectAndAppNum(dtos.size()+"");
		//思想道德=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_SX_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddMySelfAppraiseNum(dtos.size()+"");
		//思想道德=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_SX_TRPJ);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setSxddOtherAppraiseParentsNum(parentCount+"");
			dto.setSxddOtherAppraiseClassMateNum(classMateCount+"");
			dto.setSxddOtherAppraiseTeacherNum(teacherCount+"");
		}
		//思想道德=》思想道德记录袋
		dtos=appraiseMaps.get(Constant.TYPE_SXJLD);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddRecodeBagNum(dtos.size()+"");
		//学业成就=》学科作品展示
		dtos=appraiseMaps.get(Constant.TYPE_XY_GCJL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjSubJectWorkNum(dtos.size()+"");
		//学业成就=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_XY_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjMySelfAppraiseNum(dtos.size()+"");
		//学业成就=》课程评语
		dtos=appraiseMaps.get(Constant.TYPE_KE_CHENG_PINGYU);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjSubJectAppraiseNum(dtos.size()+"");
		//学业成就=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_XY);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				//我的发展目标
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setXycjOtherAppraiseParentsNum(parentCount+"");
			dto.setXycjOtherAppraiseClassMateNum(classMateCount+"");
			dto.setXycjOtherAppraiseTeacherNum(teacherCount+"");
		}
		//合作交流=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_HZ_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlMySelfAppraiseNum(dtos.size()+"");
		//合作交流=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_HZ_TRPJ);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setXzyjlOtherAppraiseParentsNum(parentCount+"");
			dto.setXzyjlOtherAppraiseClassMateNum(classMateCount+"");
			dto.setXzyjlOtherAppraiseTeacherNum(teacherCount+"");
		}
		//合作交流=》合作与交流行为记录袋
		dtos=appraiseMaps.get(Constant.TYPE_HZ_JLD);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlRecodeBagNum(dtos.size()+"");
		//运动与健康=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_YDJK_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkMySelfAppraiseNum(dtos.size()+"");
		//运动与健康=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_YDJK_TRPJ);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setYdhjkOtherAppraiseParentsNum(parentCount+"");
			dto.setYdhjkOtherAppraiseClassMateNum(classMateCount+"");
			dto.setYdhjkOtherAppraiseTeacherNum(teacherCount+"");
		}
		//运动与健康=》体质健康
		dtos=appraiseMaps.get(Constant.TYPE_YDJKTZJK);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkPhysicalHealthNum("1");
		//审美与表现=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_SMYBX_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxMySelfAppraiseNum(dtos.size()+"");
		//审美与表现=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_SMYBX_TRPJ);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setXmybxOtherAppraiseParentsNum(parentCount+"");
			dto.setXmybxOtherAppraiseClassMateNum(classMateCount+"");
			dto.setXmybxOtherAppraiseTeacherNum(teacherCount+"");
		}
		//审美与表现=》审美与表现记录袋
		dtos=appraiseMaps.get(Constant.TYPE_SMYBX_JLD);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxRecordBagNum(dtos.size()+"");
		//综合实践活动-研究性学习
		dtos=appraiseMaps.get(Constant.TYPE_YJXX);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdYjxxxMySelfNum(dtos.size()+"");
		//综合实践活动-社区服务
		dtos=appraiseMaps.get(Constant.TYPE_SQFU);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdSqfwMySelfNum(dtos.size()+"");
		//综合实践活动-社会实践活动
		dtos=appraiseMaps.get(Constant.TYPE_SHSJHD);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdShsjhdMySelfNum(dtos.size()+"");
		//个性发展=>基本情况
		dtos=appraiseMaps.get(Constant.TYPE_GXFZ_JBQK);
		if(dtos!=null&&dtos.size()>0){
			int count=0;
			for(AppraiseBaseDto dt:dtos)
				if(NestUtil.isNotEmpty(dt.getApprasial())&&NestUtil.isNotEmpty(dt.getApprasial().trim()))
					count++;
			dto.setGxfzMyBaseInfoNum(count+"");
		}
		
		//个性发展=》自我评价
		dtos=appraiseMaps.get(Constant.TYPE_GXFZ_ZWPJ);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzMySelfAppraiseNum(dtos.size()+"");
		//个性发展=》他人评价
		dtos=appraiseMaps.get(Constant.TYPE_GXFZ_TRPJ);
		if(dtos!=null&&dtos.size()>0){
			int classMateCount=0;
			int teacherCount=0;
			int parentCount=0;
			for(AppraiseBaseDto dt:dtos){
				if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_STUDENT))
					classMateCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&
						(dt.getAppraseridentify().contains(Constant.APPRASER_TEACHER)
								||dt.getAppraseridentify().contains(Constant.APPRASER_MASTER)))
					teacherCount++;
				else if(NestUtil.isNotEmpty(dt.getAppraseridentify())&&dt.getAppraseridentify().contains(Constant.APPRASER_PARENT))
					parentCount++;
			}
			dto.setGxfzOtherAppraiseParentsNum(parentCount+"");
			dto.setGxfzOtherAppraiseClassMateNum(classMateCount+"");
			dto.setGxfzOtherAppraiseTeacherNum(teacherCount+"");
		}
		//个性发展=》个性发展过程
		dtos=appraiseMaps.get(Constant.TYPE_GXFZGC);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzSpecialtyDelepNum(dtos.size()+"");
		//个性发展=》特长与成果展示
		dtos=appraiseMaps.get(Constant.TYPE_GXFZ_CGZS);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzSpecialtyAndAchievementsNum(dtos.size()+"");
		return dto;
	}
	private StudentAppraiseInfoNumDto startGetInfoMidleSchoolNum(Map<String,List<AppraiseBaseDto>> appraiseMaps){
		if(appraiseMaps==null||appraiseMaps.isEmpty())return null;
		StudentAppraiseInfoNumDto dto=new StudentAppraiseInfoNumDto();
		//新学期伊始的我=》自我评价
		List<AppraiseBaseDto> dtos=appraiseMaps.get(Constant.TERMS_BEGIN_ME);
		if(dtos!=null&&dtos.size()>0)
			dto.setStartEcoleNum(dtos.size()+"");
		//新学期伊始的我=》我的发展目标
		dtos=appraiseMaps.get(Constant.DEVELOP_TARGET_ME);
		if(dtos!=null&&dtos.size()>0)
		dto.setStartMyDlpmentTargetNum(dtos.size()+"");
		//学期结束的我=》自我评价
		dtos=appraiseMaps.get(Constant.TERMS_END_ME);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndMyEcoleNum(dtos.size()+"");
/*		//学期结束的我=》我的发展目标
		dtos=appraiseMaps.get(Constant.TERMS_END_ME);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndMyDlpmentTargetNum(dtos.size()+"");*/
		//学期结束的我=》班主任评语
		dtos=appraiseMaps.get(Constant.CHARGE_TEACHER_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndHeadMasterAppraiseNum(dtos.size()+"");
		//学期结束的我=》家长评语和期望
		dtos=appraiseMaps.get(Constant.PRAENTS_APPRAISAL_EXPECT);
		if(dtos!=null&&dtos.size()>0)
			dto.setEndParentsExpectAndAppNum(dtos.size()+"");
		//思想道德=》自我评价
		dtos=appraiseMaps.get(Constant.MORALITY_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddMySelfAppraiseNum(dtos.size()+"");
		//思想道德=》同学评价
		dtos=appraiseMaps.get(Constant.MORALITY_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddOtherAppraiseClassMateNum(dtos.size()+"");
		//思想道德=》老师评价
		dtos=appraiseMaps.get(Constant.MORALITY_TEACHER_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddOtherAppraiseTeacherNum(dtos.size()+"");
		//思想道德=》家长评价
		dtos=appraiseMaps.get(Constant.MORALITY_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddOtherAppraiseParentsNum(dtos.size()+"");
		//思想道德=》思想道德记录袋
		dtos=appraiseMaps.get(Constant.MORALITY_RECORD_BAG);
		if(dtos!=null&&dtos.size()>0)
			dto.setSxddRecodeBagNum(dtos.size()+"");
		//学业成就=》学科作品展示
		dtos=appraiseMaps.get(Constant.WORKS_SUBJECT_SHOW);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjSubJectWorkNum(dtos.size()+"");
		//学业成就=》自我评价
		dtos=appraiseMaps.get(Constant.WORKS_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjMySelfAppraiseNum(dtos.size()+"");
		//学业成就=》课程评语
		dtos=appraiseMaps.get(Constant.WORKS_SUBJECT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjSubJectAppraiseNum(dtos.size()+"");
		//学业成就=》同学评价
		dtos=appraiseMaps.get(Constant.WORKS_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjOtherAppraiseClassMateNum(dtos.size()+"");
		//学业成就=》家长评价
		dtos=appraiseMaps.get(Constant.WORKS_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXycjOtherAppraiseParentsNum(dtos.size()+"");
		//合作交流=》自我评价
		dtos=appraiseMaps.get(Constant.COOPERATION_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlMySelfAppraiseNum(dtos.size()+"");
		//合作交流=》同学评价
		dtos=appraiseMaps.get(Constant.COOPERATION_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlOtherAppraiseClassMateNum(dtos.size()+"");
		//合作交流=》教师评价
		dtos=appraiseMaps.get(Constant.COOPERATION_TEACHER_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlOtherAppraiseTeacherNum(dtos.size()+"");
		//合作交流=》家长评价
		dtos=appraiseMaps.get(Constant.COOPERATION_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlOtherAppraiseParentsNum(dtos.size()+"");
		//合作交流=》合作与交流行为记录袋
		dtos=appraiseMaps.get(Constant.COOPERATION_RECORD_BAG);
		if(dtos!=null&&dtos.size()>0)
			dto.setXzyjlRecodeBagNum(dtos.size()+"");
		//运动与健康=》自我评价
		dtos=appraiseMaps.get(Constant.PLAY_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkMySelfAppraiseNum(dtos.size()+"");
		//运动与健康=》同学评价
		dtos=appraiseMaps.get(Constant.PLAY_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkOtherAppraiseClassMateNum(dtos.size()+"");
		//运动与健康=》教师评价
		dtos=appraiseMaps.get(Constant.PLAY_TEACHER_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkOtherAppraiseTeacherNum(dtos.size()+"");
		//运动与健康=》家长评价
		dtos=appraiseMaps.get(Constant.PLAY_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkOtherAppraiseParentsNum(dtos.size()+"");
		//运动与健康=》体质健康
		dtos=appraiseMaps.get(Constant.PLAY_PHYSCIAL_HEALTH);
		if(dtos!=null&&dtos.size()>0)
			dto.setYdhjkPhysicalHealthNum("1");
		//审美与表现=》自我评价
		dtos=appraiseMaps.get(Constant.AESTHETIC_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxMySelfAppraiseNum(dtos.size()+"");
		//审美与表现=》同学评价
		dtos=appraiseMaps.get(Constant.AESTHETIC_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxOtherAppraiseClassMateNum(dtos.size()+"");
		//审美与表现=》教师评价
		dtos=appraiseMaps.get(Constant.AESTHETIC_TEACHER_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxOtherAppraiseTeacherNum(dtos.size()+"");
		//审美与表现=》家长评价
		dtos=appraiseMaps.get(Constant.AESTHETIC_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxOtherAppraiseParentsNum(dtos.size()+"");
		//审美与表现=》审美与表现记录袋
		dtos=appraiseMaps.get(Constant.AESTHETIC_RECORD_BAG);
		if(dtos!=null&&dtos.size()>0)
			dto.setXmybxRecordBagNum(dtos.size()+"");
		
		//综合实践活动-研究性学习-基本情况
		dtos=appraiseMaps.get(Constant.ACTIVITY_BASEINFO_1);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdYjxxxBaseInfoNum(dtos.size()+"");
		//综合实践活动-研究性学习-研究成果
		dtos=appraiseMaps.get(Constant.ACTIVITY_RESEARCH_RESULT);
		if(dtos!=null&&dtos.size()>0){
			int count=0;
			for(AppraiseBaseDto dt:dtos){
				if(dt.getAttachFileDtos()!=null&&dt.getAttachFileDtos().size()>0)
					count++;
			}
			dto.setZhsjhdYjxxxAchievementsNum(count+"");
		}
		//综合实践活动-研究性学习-自我评价
		dtos=appraiseMaps.get(Constant.ACTIVITY_SELF_APPRAISAL_1);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdYjxxxMySelfAppraiseNum(dtos.size()+"");
		//综合实践活动-社区服务与社会实践-基本情况
		dtos=appraiseMaps.get(Constant.ACTIVITY_BASEINFO_2);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdSqfuYshsjBaseInfoNum(dtos.size()+"");
		//综合实践活动-社区服务与社会实践-自我评价
		dtos=appraiseMaps.get(Constant.ACTIVITY_SELF_APPRAISAL_2);
		if(dtos!=null&&dtos.size()>0)
			dto.setZhsjhdSqfuYshsjMySelfAppraiseNum(dtos.size()+"");
		
		//个性发展=>自我评价
		dtos=appraiseMaps.get(Constant.INDIVIDUALITY_SELF_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzMySelfAppraiseNum(dtos.size()+"");
		//个性发展=>同学评价
		dtos=appraiseMaps.get(Constant.INDIVIDUALITY_CLASSMATES_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzOtherAppraiseClassMateNum(dtos.size()+"");
		//个性发展=>教师评价
		dtos=appraiseMaps.get(Constant.INDIVIDUALITY_TEACHER_APPRASIAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzOtherAppraiseTeacherNum(dtos.size()+"");
		//个性发展=>家长评价
		dtos=appraiseMaps.get(Constant.INDIVIDUALITY_PARENT_APPRAISAL);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzOtherAppraiseParentsNum(dtos.size()+"");
		//个性发展=》特长与成果展示
		dtos=appraiseMaps.get(Constant.INDIVIDUALITY_RECORD_BAG);
		if(dtos!=null&&dtos.size()>0)
			dto.setGxfzSpecialtyAndAchievementsNum(dtos.size()+"");
		return dto;
	}
	private Map<String,List<AppraiseBaseDto>> installDataMaps(List<AppraiseBaseDto> appraiseBaseDtos){
		Map<String,List<AppraiseBaseDto>> appraiseMaps=new LinkedHashMap<String,List<AppraiseBaseDto>>(0);
		if(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)return appraiseMaps;
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(dto==null||NestUtil.isEmpty(dto.getAppraisaltypeid())) continue;
			//组装数据集合
			List<AppraiseBaseDto> dtos=null;
			dtos=appraiseMaps.get(dto.getAppraisaltypeid());
			if(dtos==null){
				dtos=new ArrayList<AppraiseBaseDto>();
			    appraiseMaps.put(dto.getAppraisaltypeid(),dtos);
			}
			dtos.add(dto);
		}
		return appraiseMaps;
	}
	
	private Map<String,List<AppraiseBaseDto>> installDataMaps2(List<AppraiseBaseDto> appraiseBaseDtos){
		Map<String,List<AppraiseBaseDto>> appraiseMaps=new LinkedHashMap<String,List<AppraiseBaseDto>>(0);
		if(appraiseBaseDtos==null||appraiseBaseDtos.size()==0)return appraiseMaps;
		for(AppraiseBaseDto dto:appraiseBaseDtos){
			if(dto==null||NestUtil.isEmpty(dto.getTwoPartId())) continue;
			//组装数据集合
			List<AppraiseBaseDto> dtos=null;
			dtos=appraiseMaps.get(dto.getTwoPartId());
			if(dtos==null){
				dtos=new ArrayList<AppraiseBaseDto>();
			    appraiseMaps.put(dto.getTwoPartId(),dtos);
			}
			dtos.add(dto);
		}
		return appraiseMaps;
	}
	private String queryClassNameByClassid(String classid){
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("classid", classid);
		try{
			List<String>classNames=this.findList("StudentAppDetailExtImpl.queryClassNameByClassid.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("info");
				}
			});
			if(classNames!=null&&!classNames.isEmpty()){
				return classNames.get(0);
			}
		}catch(Exception e){
			logger.error("queryClassNameByClassid(String)",e);
		}
		return null;
	}
	private List<String> queryStudentInfoByCondition(String classid,String gradeid,String cmis30id,String discode){
		return queryStudentInfoByCondition(classid,gradeid,cmis30id,discode,null,null);
	}
	@Override
	public List<String> queryStudentInfoByCondition(String classid,
			String gradeid, String cmis30id, String discode, String edu_id,
			String studentName) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("classid", classid);
		params.put("gradeid", gradeid);
		params.put("cmis30id", cmis30id);
		params.put("discode",discode);
		params.put("edu_id",edu_id);
		params.put("studentName",studentName);
		try{
			return this.findList("StudentAppDetailExtImpl.queryStudentInfoByCondition.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("info");
				}
			});
		}catch(Exception e){
			logger.error("queryStudentInfoByCondition(String,String,String,String)",e);
		}
		return null;
	}
	@Override
	public List<String> queryStudentInfoByCondition1(String classid,
			String gradeid, String cmis30id, String discode, String edu_id,
			String studentName) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("classid", classid);
		params.put("gradeid", gradeid);
		params.put("cmis30id", cmis30id);
		params.put("discode",discode);
		params.put("edu_id",edu_id);
		params.put("studentName",studentName);
		try{
			return this.findList("StudentAppDetailExtImpl.queryStudentInfoByCondition1.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("info");
				}
			});
		}catch(Exception e){
			logger.error("queryStudentInfoByCondition1(String,String,String,String)",e);
		}
		return null;
	}
	@Override
	public List<String> queryGradeYearList(String levelid,String levelCode) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("levelid",levelid);
		List<String> gradeYearList=new ArrayList<String>();
		try{
			List<Map<String,Integer>> gradeYears=this.findList("StudentAppDetailExtImpl.queryGradeYearList.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,Integer> val=new HashMap<String,Integer>();
					val.put("minval", rs.getInt("minval"));
					val.put("maxval", rs.getInt("maxval"));
					return val;
				}
			});
			if(gradeYears!=null&&gradeYears.size()>0){
				Map<String,Integer> val=gradeYears.get(0);
				Integer minval=val.get("minval");
				Integer maxval=val.get("maxval");
				if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
						||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
					if((maxval-minval)==3)
						maxval--;
				}
				for(int i=minval;i<=maxval;i++){
					gradeYearList.add(i+"");
				}
			}
		}catch(Exception e){
			logger.error("queryGradeYearList(String)",e);
		}
		return gradeYearList;
	}
	@Override
	@DataSource("read")
	public Map<String, Map<String, Map<String, String>>> queryMapDataByCondition(
			String termid, String cmis30id, String discode, String levelCode,
			String levelid) {
		//初始化数据容器
		Map<String,Map<String,Map<String,String>>> datas=new HashMap<String,Map<String,Map<String,String>>>();
		//各年级学生总数集合容器
		Map<String,String> gradeStudentNums=new HashMap<String,String>();
		
		if(NestUtil.isEmpty(levelCode)) return datas;
		//查询各年级学生总数
		queryGradeStudetNums(gradeStudentNums,cmis30id,discode,levelid,termid);
		
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			//组装高中数据
			//查询新学期伊始的我{自我评价，我的发展目标}，学期结束时的我{自我评价，家长评语和期望}，思想道德{自我评价}，合作与交流{他人评价}，个性与发展{特长与成果展示}数据
			queryAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//学期结束时的我{班主任评语}
			queryBZRAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas,Constant.TYPE_END_BZRPY);
			//学业成就{课程评语}
			querySubjectAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//学科学习过程记录
			querySubjectRecordData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//审美与表现{审美与表现记录袋}
			querySMABXAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//综合实践活动{研究性学习}
			queryZHSZHDAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//个性与发展{个性发展基本情况}
			queryZXFZBaseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//运动与健康（体质健康）
			queryYDYJKBaseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas,"5050");
		}else{
			queryMiddAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas);
			//班主任评语
			queryBZRAppraiseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas,Constant.CHARGE_TEACHER_APPRAISAL);
			//运动与健康（体质健康）
			queryYDYJKBaseData(termid,cmis30id,discode,levelid,gradeStudentNums,datas,Constant.PLAY_PHYSCIAL_HEALTH);
		}
		return datas;
	}
	
	private void querySubjectRecordData(String termid, String cmis30id,
			String discode, String levelid,
			Map<String, String> gradeStudentNums,
			Map<String, Map<String, Map<String, String>>> dats) {

		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			List<Map<String,String>> datas=this.findList("ITotalStaticsExt.querySubjectRecordData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> data=new HashMap<String,String>();
					data.put("appraisaltypeid","8010");
					data.put("gradeyear",rs.getString("gradeyear"));
					data.put("couts",rs.getString("couts"));
					data.put("totalCounts",rs.getString("totalCounts"));
					data.put("attachCount",rs.getString("attachCount"));
					return data;
				}
			});
			Map<String,Map<String,String>> overdats=new HashMap<String,Map<String,String>>();
			if(datas!=null&&datas.size()>0){
				//对数据进行分组统计
				for(Map<String,String> dt:datas){
					//直接统计已完成人数
					Map<String,String> appdata=overdats.get(dt.get("appraisaltypeid"));
					if(appdata==null){
						appdata=new HashMap<String,String>();
						overdats.put(dt.get("appraisaltypeid"),appdata);
					}
					appdata.put(dt.get("gradeyear"),dt.get("couts")+"@"+dt.get("totalCounts")+"@"+dt.get("attachCount"));
				}
			}
		//开始组装数据
		if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
			for(String gradeYear:gradeStudentNums.keySet()){
				String totalStudentNum=gradeStudentNums.get(gradeYear);
				String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,"8010");
			}
		}
		}catch(Exception e){
			logger.error("querySubjectRecordData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	@Override
	@DataSource("read")
	public Map<String, Map<String, Map<String, String>>> queryMapDataByConditionInDiscode(
			String termid, String gradeyear, String discode, String levelCode,
			String schoolname, Map<String, Map<String, String>> tongjiDatas) {
		//初始化数据容器
		Map<String,Map<String,Map<String,String>>> datas=new TreeMap<String,Map<String,Map<String,String>>>();
		//各学校对应届别学生总数集合容器
		Map<String,String> schoolStudentNums=new HashMap<String,String>();
		//查询各学校学生总数
		querySchoolStudetNums(schoolStudentNums,discode,gradeyear,schoolname,levelCode,termid);
		if(NestUtil.isEmpty(levelCode)) return datas;
		
		if(Constant.DICT_TYPE_LEVELCODE_GZSTR.equals(levelCode)
				||Constant.DICT_TYPE_LEVELCODE_GZYKSTR.equals(levelCode)){
			//组装高中数据
			//查询对应的学期标识号
			String semterid=querySemterid(termid,gradeyear);
			//查询新学期伊始的我{自我评价，我的发展目标}，学期结束时的我{自我评价，家长评语和期望}，思想道德{自我评价}，合作与交流{他人评价}，个性与发展{特长与成果展示}数据
			queryDiscodeAppraiseData(semterid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//学期结束时的我{班主任评语}
			queryDiscodeBZRAppraiseData(termid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas,Constant.TYPE_END_BZRPY);
			//学业成就{课程评语}
			queryDiscodeSubjectAppraiseData(semterid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//审美与表现{审美与表现记录袋}
			queryDiscodeSMABXAppraiseData(semterid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//综合实践活动{研究性学习}
			queryDiscodeZHSZHDAppraiseData(semterid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//个性与发展{个性发展基本情况}
			queryDiscodeZXFZBaseData(semterid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//运动与健康（体质健康）
			queryDiscodeYDYJKBaseData(termid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas,Constant.TYPE_YDJKTZJK);
		}else{
			queryDiscodeMiddAppraiseData(termid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas);
			//班主任评语
			queryDiscodeBZRAppraiseData(termid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas,Constant.CHARGE_TEACHER_APPRAISAL);
			//运动与健康（体质健康）
			queryDiscodeYDYJKBaseData(termid,discode,gradeyear,levelCode,schoolname,schoolStudentNums,datas,tongjiDatas,Constant.PLAY_PHYSCIAL_HEALTH);
			
		}
		return datas;
	}
	private void queryDiscodeMiddAppraiseData(String termid,String discode,String gradeyear,String levelCode,String schoolname,
			Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("levelCode",levelCode);
		params.put("schoolname",schoolname);
		try{
			final Map<String,Map<String,String>> dats=new HashMap<String,Map<String,String>>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeMiddAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String schoolName=rs.getString("schoolname");
					Map<String,String> map=dats.get(schoolName);
					if(map==null){
						map=new HashMap<String,String>();
						dats.put(schoolName,map);
					}
					map.put(rs.getString("appraisaltypeid"),rs.getString("couts"));
					return null;
				}
			});
		//开始组装数据
		if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
			for(String schoolName:schoolStudentNums.keySet()){
				String totalStudentNum=schoolStudentNums.get(schoolName);
				Map<String,String> map=dats.get(schoolName);
				//组装已完成数，查询新学期伊始的我{自我评价}数据
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TERMS_BEGIN_ME),Constant.TERMS_BEGIN_ME,datas,tongjiDatas);
				//新学期伊始的我{我的发展目标}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.DEVELOP_TARGET_ME),Constant.DEVELOP_TARGET_ME,datas,tongjiDatas);
				//学期结束时的我{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TERMS_END_ME),Constant.TERMS_END_ME,datas,tongjiDatas);
				//学期结束时的我{家长评语和期望}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.PRAENTS_APPRAISAL_EXPECT),Constant.PRAENTS_APPRAISAL_EXPECT,datas,tongjiDatas);
				//思想道德{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.MORALITY_SELF_APPRAISAL),Constant.MORALITY_SELF_APPRAISAL,datas,tongjiDatas);
				//学业成就{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.WORKS_SELF_APPRAISAL),Constant.WORKS_SELF_APPRAISAL,datas,tongjiDatas);
				//学业成就{课程评语}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.WORKS_SUBJECT_APPRAISAL),Constant.WORKS_SUBJECT_APPRAISAL,datas,tongjiDatas);
				//合作交流{同学评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.COOPERATION_CLASSMATES_APPRAISAL),Constant.COOPERATION_CLASSMATES_APPRAISAL,datas,tongjiDatas);
				//审美与表现{审美与表现记录袋}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.AESTHETIC_RECORD_BAG),Constant.AESTHETIC_RECORD_BAG,datas,tongjiDatas);
				//综合实践活动-研究性学习{基本情况}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.ACTIVITY_BASEINFO_1),Constant.ACTIVITY_BASEINFO_1,datas,tongjiDatas);
				//个性发展{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.INDIVIDUALITY_SELF_APPRAISAL),Constant.INDIVIDUALITY_SELF_APPRAISAL,datas,tongjiDatas);
				//个性发展{特长与成果展示}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.INDIVIDUALITY_RECORD_BAG),Constant.INDIVIDUALITY_RECORD_BAG,datas,tongjiDatas);
			}
		}
		}catch(Exception e){
			logger.error("queryDiscodeMiddAppraiseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeYDYJKBaseData(String termid,String discode,String gradeyear,String levelCode,
			String schoolname,Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas
			,Map<String, Map<String, String>> tongjiDatas,String levelNum){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeYDYJKBaseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),levelNum,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeYDYJKBaseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeZXFZBaseData(String termid,String discode,String gradeyear,String levelCode,
			String schoolname,Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeZXFZBaseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),Constant.TYPE_GXFZ_JBQK,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeZXFZBaseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeZHSZHDAppraiseData(String termid,String discode,String gradeyear,String levelCode,
			String schoolname,Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeZHSZHDAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),Constant.TYPE_YJXX,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeZHSZHDAppraiseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeSMABXAppraiseData(String termid,String discode,String gradeyear,String levelCode,
			String schoolname,Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeSMABXAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),Constant.TYPE_SMYBX_JLD,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeSMABXAppraiseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeSubjectAppraiseData(String termid,String discode,String gradeyear,String levelCode,
			String schoolname,Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeSubjectAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),Constant.TYPE_KE_CHENG_PINGYU,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeSubjectAppraiseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private void queryDiscodeBZRAppraiseData(String termid,String discode,String gradeyear,
			String levelCode,String schoolname,Map<String,String> schoolStudentNums,
			Map<String,Map<String,Map<String,String>>> datas,Map<String, Map<String, String>> tongjiDatas,String levelNum){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelCode);
		try{
			final Map<String,String> dats=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeBZRAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					dats.put(rs.getString("schoolname"),rs.getString("couts"));
					return null;
				}
			});
			if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
				for(String schoolName:schoolStudentNums.keySet()){
					String totalStudentNum=schoolStudentNums.get(schoolName);
					StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,dats==null?null:dats.get(schoolName),levelNum,datas,tongjiDatas);
				}
			}
		}catch(Exception e){
			logger.error("queryDiscodeBZRAppraiseData(String,String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,Map<String, Map<String, String>>)",e);
		}
	}
	private String querySemterid(String termid,String gradeyear){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("gradeyear",gradeyear);
		try{
			List<String> termids=this.findList("StudentAppDetailExtImpl.querySemterid.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("semtemid");
				}
			});
			if(termids!=null&&termids.size()>0){
				return termids.get(0);
			}
		}catch(Exception e){
			logger.error("querySemterid(String,String)",e);
		}
		return null;
	}
	private void queryDiscodeAppraiseData(String termid,String discode,String gradeyear,String levelcode,String schoolname,
			Map<String,String> schoolStudentNums,Map<String,Map<String,Map<String,String>>> dts
			,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("termid",termid);
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelcode);
		try{
			final Map<String,Map<String,String>> datas=new HashMap<String,Map<String,String>>();
			this.findList("StudentAppDetailExtImpl.queryDiscodeAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String schoolName=rs.getString("schoolname");
					Map<String,String> map=datas.get(schoolName);
					if(map==null){
						map=new HashMap<String,String>();
						datas.put(schoolName,map);
					}
					//map.put("appraisaltypeid",rs.getString("appraisaltypeid"));
					map.put(rs.getString("appraisaltypeid"),rs.getString("couts"));
					return null;
				}
			});
		
		//开始组装数据
		if(schoolStudentNums!=null&&schoolStudentNums.size()>0){
			for(String schoolName:schoolStudentNums.keySet()){
				String totalStudentNum=schoolStudentNums.get(schoolName);
				Map<String,String> map=datas.get(schoolName);
				//组装已完成数，查询新学期伊始的我{自我评价}数据
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_START_ZWPJ),Constant.TYPE_START_ZWPJ,dts,tongjiDatas);
				//新学期伊始的我{我的发展目标}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_START_WDFZMB),Constant.TYPE_START_WDFZMB,dts,tongjiDatas);
				//学期结束时的我{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_END_ZWPJ),Constant.TYPE_END_ZWPJ,dts,tongjiDatas);
				//学期结束时的我{家长评语和期望}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_END_JZPYQW),Constant.TYPE_END_JZPYQW,dts,tongjiDatas);
				//思想道德{自我评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_SX_ZWPJ),Constant.TYPE_SX_ZWPJ,dts,tongjiDatas);
				//合作与交流{他人评价}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_HZ_TRPJ),Constant.TYPE_HZ_TRPJ,dts,tongjiDatas);
				//个性与发展{特长与成果展示}
				StatisticsDiscodeCommonMethod(schoolName, totalStudentNum,map==null?null:map.get(Constant.TYPE_GXFZ_CGZS),Constant.TYPE_GXFZ_CGZS,dts,tongjiDatas);
			}
		}
		}catch(Exception e){
			logger.error("queryDiscodeAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>)",e);
		}
	}
	public void StatisticsDiscodeCommonMethod(String schoolName,String totalStudentNum,String overStudentNum,String levelNum
			,Map<String,Map<String,Map<String,String>>> dts
			,Map<String, Map<String, String>> tongjiDatas){
		Map<String,Map<String,String>> dataMap=dts.get(schoolName);
		if(dataMap==null){
			dataMap=new HashMap<String,Map<String,String>>();
			dts.put(schoolName,dataMap);
		}
		//通过栏目号获取数据
		Map<String,String> detailDataMap=dataMap.get(levelNum);
		if(detailDataMap==null){
			detailDataMap=new HashMap<String,String>();
			dataMap.put(levelNum,detailDataMap);
		}
		//放入学生总人数到Map中
		detailDataMap.put("totalStudentNum",totalStudentNum);
		//统计总人数
		sumStudentNums(levelNum,totalStudentNum,tongjiDatas,"totalStudentNum");
		//已完成数
		overStudentNum=NestUtil.isEmpty(overStudentNum)?"0":overStudentNum;
		//统计总完成人数以及总百分比
		sumStudentNums(levelNum,overStudentNum,tongjiDatas,"overStudentNum");
		
		detailDataMap.put("overStudentNum",overStudentNum);
		//完成百分比
		detailDataMap.put("percentOverStudentNum",queryPercentVal(overStudentNum,totalStudentNum));
	}
	private void sumStudentNums(String levelNum,String num,Map<String, Map<String, String>> tongjiDatas,String categoryType){
		Map<String,String> data=tongjiDatas.get(levelNum);
		if(data==null){
			data=new HashMap<String,String>();
			tongjiDatas.put(levelNum,data);
		}
		String numStr=data.get(categoryType);
		if(NestUtil.isEmpty(numStr))
			numStr="0";
		try{
			numStr=Integer.parseInt(numStr)+Integer.parseInt(num)+"";
		}catch(Exception e){
		}
		data.put(categoryType,numStr);
		if("overStudentNum".equals(categoryType)){
			String sumStudents=data.get("totalStudentNum");
			data.put("percentOverStudentNum",queryPercentVal(numStr,sumStudents));
		}
	}
	private void querySchoolStudetNums(final Map<String,String> schoolStudentNums,String discode,String gradeyear,String schoolname,String levelcode,String termid){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("discode",discode);
		params.put("gradeyear",gradeyear);
		params.put("schoolname",schoolname);
		params.put("levelcode",levelcode);
		params.put("termid",termid);
		try{
			this.findList("StudentAppDetailExtImpl.querySchoolStudetNums.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String schoolname=rs.getString("schoolname");
					String couts=rs.getString("couts");
					schoolStudentNums.put(schoolname,couts);
					return null;
				}
			});
		}catch(Exception e){
			logger.error("querySchoolStudetNums(final Map<String,String>,String,String,String)",e);
		}
	}
	private void queryYDYJKBaseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats,String levelNum){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		try{
			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("ITotalStaticsExt.queryYDYJKBaseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),(null==rs.getString("couts")?0:rs.getString("couts"))+"@"+(null==rs.getString("totalCounts")?0:rs.getString("totalCounts")));
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(null==overStudentNum?overStudentNum:overStudentNum.split("@")[0],totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,levelNum);
				}
			}
		}catch(Exception e){
			logger.error("queryYDYJKBaseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>,String)",e);
		}
	}
	@DataSource("read")
	private void queryMiddAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		try{
			List<Map<String,String>> datas=this.findList("ITotalStaticsExt.queryQXInfos.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> data=new HashMap<String,String>();
					data.put("appraisaltypeid",rs.getString("appraisaltypeid"));
					data.put("gradeyear",rs.getString("gradeyear"));
					data.put("couts",rs.getString("couts"));
					data.put("totalCounts",rs.getString("totalCounts"));
					data.put("attachNum",rs.getString("attachNum"));
					return data;
				}
			});
			Map<String,Map<String,String>> overdats=new HashMap<String,Map<String,String>>();
			if(datas!=null&&datas.size()>0){
				//对数据进行分组统计
				for(Map<String,String> dt:datas){
					//直接统计已完成人数
					Map<String,String> appdata=overdats.get(dt.get("appraisaltypeid"));
					if(appdata==null){
						appdata=new HashMap<String,String>();
						overdats.put(dt.get("appraisaltypeid"),appdata);
					}
					appdata.put(dt.get("gradeyear"),(null==dt.get("couts")?0:dt.get("couts"))
							+"@"+(null==dt.get("totalCounts")?0:dt.get("totalCounts"))
							+"@"+(null==dt.get("attachNum")?0:dt.get("attachNum")));
				}
			}
		//开始组装数据
		if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
			for(String gradeYear:gradeStudentNums.keySet()){
				String totalStudentNum=gradeStudentNums.get(gradeYear);
				String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
/*				//组装已完成数，查询新学期伊始的我{自我评价}数据
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TERMS_BEGIN_ME);
				//新学期伊始的我{我的发展目标}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.DEVELOP_TARGET_ME);
				//学期结束时的我{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TERMS_END_ME);
				//学期结束时的我{家长评语和期望}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.PRAENTS_APPRAISAL_EXPECT);
				//思想道德{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.MORALITY_SELF_APPRAISAL);
				//学业成就{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.WORKS_SELF_APPRAISAL);
				//学业成就{课程评语}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.WORKS_SUBJECT_APPRAISAL);
				//合作交流{同学评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.COOPERATION_CLASSMATES_APPRAISAL);
				//审美与表现{审美与表现记录袋}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.AESTHETIC_RECORD_BAG);
				//综合实践活动-研究性学习{基本情况}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.ACTIVITY_BASEINFO_1);
				//个性发展{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.INDIVIDUALITY_SELF_APPRAISAL);
				//个性发展{特长与成果展示}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.INDIVIDUALITY_RECORD_BAG);*/
				for(String typeId : Constant.TOTAL_TWO_PART_ID_CZ){
					StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,typeId);
				}
			}
		}
		}catch(Exception e){
			logger.error("queryMiddAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void queryZXFZBaseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("ITotalStaticsExt.queryZXFZBaseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),(null==rs.getString("couts")?"0":rs.getString("couts"))
							+"@"+(null==rs.getString("totalCounts")?"0":rs.getString("totalCounts")));//totalCounts
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(NestUtil.isEmpty(overStudentNum)?overStudentNum:overStudentNum.split("@")[0],totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,Constant.TYPE_GXFZ_JBQK);
				}
			}
		}catch(Exception e){
			logger.error("queryZXFZBaseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void queryZHSZHDAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			List<Map<String,String>> datas=this.findList("ITotalStaticsExt.queryZHSZHDAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> data=new HashMap<String,String>();
					data.put("appraisaltypeid",rs.getString("appraisaltypeid"));
					data.put("gradeyear",rs.getString("gradeyear"));
					data.put("couts",rs.getString("couts"));
					data.put("totalCounts",rs.getString("totcalCounts"));
					data.put("attachCount",rs.getString("attachCount"));
					return data;
				}
			});
			Map<String,Map<String,String>> overdats=new HashMap<String,Map<String,String>>();
			if(datas!=null&&datas.size()>0){
				//对数据进行分组统计
				for(Map<String,String> dt:datas){
					//直接统计已完成人数
					Map<String,String> appdata=overdats.get(dt.get("appraisaltypeid"));
					if(appdata==null){
						appdata=new HashMap<String,String>();
						overdats.put(dt.get("appraisaltypeid"),appdata);
					}
					appdata.put(dt.get("gradeyear"),dt.get("couts")+"@"+dt.get("totalCounts")+"@"+dt.get("attachCount"));
				}
			}
		//开始组装数据
		if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
			for(String gradeYear:gradeStudentNums.keySet()){
				String totalStudentNum=gradeStudentNums.get(gradeYear);
				String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
				for(String typeId : Constant.ALL_STATICS_PRACTICE_TWO_PART_ID){
					StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,typeId);
				}
			}
		}
/*			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("StudentAppDetailExtImpl.queryZHSZHDAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),rs.getString("couts"));
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(overStudentNum,totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,Constant.TYPE_YJXX);
				}
			}*/
		}catch(Exception e){
			logger.error("queryZHSZHDAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void querySMABXAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			List<Map<String,String>> datas=this.findList("ITotalStaticsExt.querySMABXAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> data=new HashMap<String,String>();
					data.put("appraisaltypeid",rs.getString("appraisaltypeid"));
					data.put("gradeyear",rs.getString("gradeyear"));
					data.put("couts",rs.getString("couts"));
					data.put("totalCounts",rs.getString("totalCounts"));
					data.put("attachCount",rs.getString("attachCount"));
					return data;
				}
			});
			Map<String,Map<String,String>> overdats=new HashMap<String,Map<String,String>>();
			if(datas!=null&&datas.size()>0){
				//对数据进行分组统计
				for(Map<String,String> dt:datas){
					//直接统计已完成人数
					Map<String,String> appdata=overdats.get(dt.get("appraisaltypeid"));
					if(appdata==null){
						appdata=new HashMap<String,String>();
						overdats.put(dt.get("appraisaltypeid"),appdata);
					}
					appdata.put(dt.get("gradeyear"),dt.get("couts")+"@"+dt.get("totalCounts")+"@"+dt.get("attachCount"));
				}
			}
		//开始组装数据
		if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
			for(String gradeYear:gradeStudentNums.keySet()){
				String totalStudentNum=gradeStudentNums.get(gradeYear);
				String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
				for(String typeId : Constant.ALL_STATICS_RECORD_TWO_PART_ID){
					StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,typeId);
				}
			}
		}
/*			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("ITotalStaticsExt.querySMABXAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),rs.getString("couts"));
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(overStudentNum,totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,Constant.TYPE_SMYBX_JLD);
				}
			}*/
		}catch(Exception e){
			logger.error("querySMABXAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void querySubjectAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("ITotalStaticsExt.querySubjectAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),(null==rs.getString("couts")?"0":rs.getString("couts"))
							+"@"+(null==rs.getString("totalCounts")?"0":rs.getString("totalCounts")));
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(NestUtil.isEmpty(overStudentNum)?overStudentNum:overStudentNum.split("@")[0],totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,Constant.TYPE_KE_CHENG_PINGYU);
				}
			}
		}catch(Exception e){
			logger.error("querySubjectAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void queryBZRAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats,String levelNum){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		try{
			final Map<String,String> datas=new HashMap<String,String>();
			this.findList("ITotalStaticsExt.queryBZRAppraiseData.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					datas.put(rs.getString("gradeyear"),rs.getString("couts"));
					return null;
				}
			});
			if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
				for(String gradeYear:gradeStudentNums.keySet()){
					String totalStudentNum=gradeStudentNums.get(gradeYear);
					String gradY=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					String overStudentNum=datas.get(gradY);
					String percentVal=queryPercentVal(overStudentNum,totalStudentNum);
					String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
					//相应的数据
					installBaseData(dats, gradYear, totalStudentNum,
							overStudentNum, percentVal,levelNum);
				}
			}
		}catch(Exception e){
			logger.error("queryBZRAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	private void installBaseData(
			Map<String, Map<String, Map<String, String>>> dats,
			String gradeYear, String totalStudentNum, String overStudentNum,
			String percentVal,String levelNum) {
		Map<String,Map<String,String>> bZRAppMapdat=dats.get(levelNum);
		if(bZRAppMapdat==null){
			bZRAppMapdat=new HashMap<String,Map<String,String>>();
			dats.put(levelNum,bZRAppMapdat);
		}
		Map<String,String> bZRAppMapTotalNums=bZRAppMapdat.get("totalStudentNum");
		if(bZRAppMapTotalNums==null){
			bZRAppMapTotalNums=new HashMap<String,String>();
			bZRAppMapdat.put("totalStudentNum", bZRAppMapTotalNums);
		}
		String finishedTotalNum = "0";
		if(!NestUtil.isEmpty(overStudentNum)){
			String[] counts = overStudentNum.split("@");
			if(2==counts.length){
				overStudentNum = counts[0];
				finishedTotalNum = counts[1];
			}else{
				finishedTotalNum = overStudentNum;
			}
		}
		bZRAppMapTotalNums.put(gradeYear,totalStudentNum);
		Map<String,String> startMyselfAppMapNums=bZRAppMapdat.get("overStudentNum");
		if(startMyselfAppMapNums==null){
			startMyselfAppMapNums=new HashMap<String,String>();
			bZRAppMapdat.put("overStudentNum",startMyselfAppMapNums);
		}
		//已完成数
		startMyselfAppMapNums.put(gradeYear,NestUtil.isEmpty(overStudentNum)?"0":overStudentNum);
		
		Map<String,String> startMyselfAppMapTotalNums=bZRAppMapdat.get("finishedTotalCount");
		if(startMyselfAppMapTotalNums==null){
			startMyselfAppMapTotalNums=new HashMap<String,String>();
			bZRAppMapdat.put("finishedTotalCount",startMyselfAppMapTotalNums);
		}
		//总条数
		startMyselfAppMapTotalNums.put(gradeYear,NestUtil.isEmpty(finishedTotalNum)?"0":finishedTotalNum);
		
		Map<String,String> percentAppMapNums=bZRAppMapdat.get("percentOverStudentNum");
		if(percentAppMapNums==null){
			percentAppMapNums=new HashMap<String,String>();
			bZRAppMapdat.put("percentOverStudentNum",percentAppMapNums);
		}
		//百分比
		percentAppMapNums.put(gradeYear,percentVal);
	}
	private String queryPercentVal(String childNum,String totalNum){
		//完成百分比
		Double percent=0.0;
		if(NestUtil.isEmpty(childNum)||"0".equals(childNum))return "0";
		try{
			percent=Double.parseDouble(childNum)/Double.parseDouble(totalNum)*100;
		}catch(Exception e){
			logger.error("queryPercentVal(String,String)"+childNum+"totalNum:"+totalNum,e);
			return null;
		}
		return new java.text.DecimalFormat("0.0").format(percent);
	}
	private void queryAppraiseData(String termid,String cmis30id,String discode,String levelid
			,Map<String,String> gradeStudentNums,Map<String,Map<String,Map<String,String>>> dats){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		params.put("gradelist",gradeStudentNums.keySet());
		try{
			List<Map<String,String>> datas=this.findList("ITotalStaticsExt.appraisaltypeid.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					Map<String,String> data=new HashMap<String,String>();
					data.put("appraisaltypeid",rs.getString("appraisaltypeid"));
					data.put("gradeyear",rs.getString("gradeyear"));
					data.put("couts",rs.getString("couts"));
					data.put("totalCounts",rs.getString("totalCounts"));
					data.put("attachCount",rs.getString("attachCount"));
					return data;
				}
			});
			Map<String,Map<String,String>> overdats=new HashMap<String,Map<String,String>>();
			if(datas!=null&&datas.size()>0){
				//对数据进行分组统计
				for(Map<String,String> dt:datas){
					//直接统计已完成人数
					Map<String,String> appdata=overdats.get(dt.get("appraisaltypeid"));
					if(appdata==null){
						appdata=new HashMap<String,String>();
						overdats.put(dt.get("appraisaltypeid"),appdata);
					}
					appdata.put(dt.get("gradeyear"),dt.get("couts")+"@"+dt.get("totalCounts")+"@"+dt.get("attachCount"));
				}
			}
		//开始组装数据
		if(gradeStudentNums!=null&&gradeStudentNums.size()>0){
			for(String gradeYear:gradeStudentNums.keySet()){
				String totalStudentNum=gradeStudentNums.get(gradeYear);
				String gradYear=NestUtil.isEmpty(gradeYear)?gradeYear:gradeYear.split("_")[0];
		/*		//组装已完成数，查询新学期伊始的我{自我评价}数据
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_START_ZWPJ);
				//新学期伊始的我{我的发展目标}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_START_WDFZMB);
				//学期结束时的我{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_END_ZWPJ);
				//学期结束时的我{家长评语和期望}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_END_JZPYQW);
				//思想道德{自我评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_SX_ZWPJ);
				//合作与交流{他人评价}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_HZ_TRPJ);
				//个性与发展{特长与成果展示}
				StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,Constant.TYPE_GXFZ_CGZS);*/
				for(String typeId : Constant.ALL_STATICS_APPRAISAL_TWO_PART_ID){
					for(String appraiserTpe : Constant.ALL_APPRAISER_TPYE){
						StatisticsCommonMethod(dats, overdats, gradYear,totalStudentNum,typeId+"-"+appraiserTpe);
					}
				}
			}
		}
		}catch(Exception e){
			logger.error("queryAppraiseData(String,String,String,String,Map<String,String>,Map<String,Map<String,Map<String,String>>>",e);
		}
	}
	/**
	 * 统计人数公用方法
	 * @param dats 总的数据容器
	 * @param overdats 已组装的数据容器
	 * @param gradeYear 学年
	 * @param totalStudentNum 指定学年下的学生总数
	 * @param levelNum 栏目号
	 */
	private void StatisticsCommonMethod(
			Map<String, Map<String, Map<String, String>>> dats,
			Map<String, Map<String, String>> overdats, String gradeYear,
			String totalStudentNum,String levelNum) {
		Map<String,Map<String,String>> startMyselfAppMapdat=dats.get(levelNum);
		if(startMyselfAppMapdat==null){
			startMyselfAppMapdat=new HashMap<String,Map<String,String>>();
			dats.put(levelNum,startMyselfAppMapdat);
		}
		//学生总数
		Map<String,String> startMyselfAppMapTotalNums=startMyselfAppMapdat.get("totalStudentNum");
		if(startMyselfAppMapTotalNums==null){
			startMyselfAppMapTotalNums=new HashMap<String,String>();
			startMyselfAppMapdat.put("totalStudentNum", startMyselfAppMapTotalNums);
		}
		startMyselfAppMapTotalNums.put(gradeYear,totalStudentNum);
		//已完成数
		Map<String,String> startMyselfAppMapNums=startMyselfAppMapdat.get("overStudentNum");
		if(startMyselfAppMapNums==null){
			startMyselfAppMapNums=new HashMap<String,String>();
			startMyselfAppMapdat.put("overStudentNum",startMyselfAppMapNums);
		}
		String overCount="0";
		Map<String,String> startMyselfAppMap=overdats.get(levelNum);
		if(startMyselfAppMap!=null&&startMyselfAppMap.size()>0){
			overCount=NestUtil.isEmpty(startMyselfAppMap.get(gradeYear))?"0":startMyselfAppMap.get(gradeYear).split("@")[0];
		}
		startMyselfAppMapNums.put(gradeYear,overCount);
		//完成百分比
		Map<String,String> startMyselfAppMapPercent=startMyselfAppMapdat.get("percentOverStudentNum");
		if(startMyselfAppMapPercent==null){
			startMyselfAppMapPercent=new HashMap<String,String>();
			startMyselfAppMapdat.put("percentOverStudentNum",startMyselfAppMapPercent);
		}
		startMyselfAppMapPercent.put(gradeYear,queryPercentVal(overCount,totalStudentNum));
		//评价总数量
		Map<String,String> startMyselfAppMapTotal=startMyselfAppMapdat.get("finishedTotalCount");
		if(startMyselfAppMapTotal==null){
			startMyselfAppMapTotal=new HashMap<String,String>();
			startMyselfAppMapdat.put("finishedTotalCount",startMyselfAppMapTotal);
		}
		String finishedTotalCount = "0";//finishedTotalCount
		Map<String,String> startMyselfAppMapTotalCounts=overdats.get(levelNum);
		if(startMyselfAppMapTotalCounts!=null&&startMyselfAppMapTotalCounts.size()>0){
			finishedTotalCount=NestUtil.isEmpty(startMyselfAppMapTotalCounts.get(gradeYear))?"0":startMyselfAppMapTotalCounts.get(gradeYear).split("@")[1];
		}
		startMyselfAppMapTotal.put(gradeYear,finishedTotalCount);
		//附件总数
		Map<String,String> attachMyselfAppMapTotal=startMyselfAppMapdat.get("attacheCount");
		if(attachMyselfAppMapTotal==null){
			attachMyselfAppMapTotal=new HashMap<String,String>();
			startMyselfAppMapdat.put("attacheCount",attachMyselfAppMapTotal);
		}
		String attacheCount = "0";//finishedTotalCount
		Map<String,String> attachetMyselfAppMapTotalCounts=overdats.get(levelNum);
		if(attachetMyselfAppMapTotalCounts!=null&&attachetMyselfAppMapTotalCounts.size()>0){
			attacheCount=NestUtil.isEmpty(attachetMyselfAppMapTotalCounts.get(gradeYear))?"0":attachetMyselfAppMapTotalCounts.get(gradeYear).split("@")[2];
		}
		attachMyselfAppMapTotal.put(gradeYear,attacheCount);
	}
	private void queryGradeStudetNums(final Map<String,String>gradeStudentNums,String cmis30id,String discode,String levelid,String termid){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("cmis30id",cmis30id);
		params.put("discode",discode);
		params.put("levelid",levelid);
		params.put("termid",termid);
		try{
			this.findList("StudentAppDetailExtImpl.queryGradeStudetNums.query", params,new RowMapper() {
				@Override
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
					String gradeyear=rs.getString("gradeyear");
					String semtemid=rs.getString("semtemid");
					gradeStudentNums.put(gradeyear+"_"+semtemid,rs.getString("counts"));
					return null;
				}
			});
		}catch(Exception e){
			logger.error("queryGradeStudetNums(final Map<String,String>,String,String,String)",e);
		}
	}
}
