package com.flyrish.hades.service.ext;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.AppraisalDto;
import com.flyrish.hades.dto.CampusDto;
import com.flyrish.hades.dto.CheckItemInfoDto;
import com.flyrish.hades.dto.DataCountDto;
import com.flyrish.hades.dto.HealthDataDto;
import com.flyrish.hades.dto.ModelScoreDto;
import com.flyrish.hades.dto.RstudentDto;
import com.flyrish.hades.dto.SchoolTreeDto;
import com.flyrish.hades.dto.SelfDto;
import com.flyrish.hades.dto.StudentDto;

public interface IMasterReportExt {
	/**
	 * 获取报告册学生基本信息
	 * @param params : String cmis30id、String discode、List<String> eduids
	 * @return : List<StudentDto>
	 */
	public List<StudentDto> queryStudentBaseInfos(Map<String,Object>params);
	/**
	 * 获取老师相关信息
	 * @param params ： String cmis30id、String discode、String classid、String campausid、String levelcode
	 * @return : List<CampusDto>
	 */
	public List<CampusDto> queryTeacherInfos(Map<String, Object> params);
	/**
	 * 获取个性发展评价信息
	 * @param params : List<String> eduids
	 * @return : List<AppraisalDto>
	 */
	public List<AppraisalDto>queryPersonalAppral(Map<String, Object> params);
	/**
	 * 获取研究性学习评价信息
	 * @param params : List<String> eduids
	 * @return : List<AppraisalDto>
	 */
	public List<AppraisalDto> queryStudyAppral(Map<String, Object> params);
	/**
	 * 获取  个性发展
	 * @param sDto
	 * @param personalAppral
	 * @param string
	 * @return
	 */
	public List<AppraisalDto> initSinglePersonalSelf(StudentDto sDto,List<AppraisalDto> personalAppral, String string);
	/**
	 * 获取 研究性学习
	 * @param sDto
	 * @param studyAppral
	 * @return
	 */
	public List<AppraisalDto> initsingleStudyAppral(StudentDto sDto,List<AppraisalDto> studyAppral);
	/**
	 * 写数据到磁盘
	 * @param url
	 * @param fileName1
	 * @param personalAppralSelf
	 * @param personalAppralExtra
	 * @param singleStudyAppral
	 */
	public void writeAppraiseData(String url, String fileName1,List<AppraisalDto> personalAppralSelf,List<AppraisalDto> personalAppralExtra,List<AppraisalDto> singleStudyAppral);
	/**
	 * 获取班主任评语信息
	 * @param params
	 * @return
	 */
	public List<AppraisalDto> queryMasterAppral(Map<String, Object> params);
	/**
	 * 获取单个人班主任评语信息
	 * @param sDto
	 * @param masterAppraise
	 * @return
	 */
	public List<AppraisalDto> initSingleMasterAppraise(StudentDto sDto,List<AppraisalDto> masterAppraise);
	/**
	 * 获取输出个性发展信息
	 * @param params
	 * @param flag
	 * @return
	 */
	public List<AppraisalDto> queryOutPersonalAppral(Map<String, Object> params, String flag);
	/**
	 * 获取研究性学习输出信息
	 * @param params
	 * @return
	 */
	public List<AppraisalDto> queryOutStudyAppral(Map<String, Object> params);
	/**
	 * 获取单个学生 单条个性发展自我评价记录
	 * @param outPersonalAppralSelf
	 * @param sDto
	 * @return
	 */
	public AppraisalDto initOutSinglePersonalAppraiseSelf(List<AppraisalDto> outPersonalAppralSelf, StudentDto sDto);
	/**
	 * 获取单个学生 三条个性发展成果展示
	 * @param outPersonalAppralSelf
	 * @param sDto
	 * @return
	 */
	public List<AppraisalDto> initOutSinglePersonalAppraiseExtra(List<AppraisalDto> outPersonalAppralSelf, StudentDto sDto);
	/**
	 * 获取体质健康数据
	 * @param params
	 * @return
	 */
	public List<HealthDataDto> queryHealthDdatas(Map<String, Object> params);
	/**
	 * 获取单个学生体质体检数据
	 * @param sDto
	 * @param healthDatas
	 * @return
	 */
	public List<HealthDataDto> querySingleHealthData(StudentDto sDto,List<HealthDataDto> healthDatas);
	/**
	 * 获取健康体检表
	 * @param params
	 * @return
	 */
	public List<CheckItemInfoDto> queryCheckItems(Map<String, Object> params);
	/**
	 * 获取健康体检表(单个人)
	 * @param sDto
	 * @param checkItems
	 * @return
	 */
	public List<CheckItemInfoDto> querySigleCheckItems(StudentDto sDto,List<CheckItemInfoDto> checkItems);
	/**
	 * 获取学生信息
	 * @param ff
	 * @return
	 */
	public RstudentDto checkExcelFile(File ff,List<SchoolTreeDto> studentInfo);
	/**
	 * 导入信息
	 * @param ff
	 * @param student
	 */
	public RstudentDto getStudentInfos(File ff, RstudentDto student);
	/**
	 * 保存导入信息
	 * @param eduIds
	 * @param selfDtos
	 * @param selfExtraDtos
	 * @param appraiseDtos
	 */
	public void saveImportData(List<String> eduIds, List<SelfDto> selfDtos,	List<SelfDto> selfExtraDtos, List<SelfDto> appraiseDtos);
	/**
	 * 查询所有成绩
	 * @param params
	 * @return
	 */
	public List<ModelScoreDto> queryAllScore(Map<String, Object> params);
	/**
	 * 获取单个人各科成绩
	 * @param sDto
	 * @param allScore
	 * @return
	 */
	public Map<String, Map<String,List<ModelScoreDto>>> initSingleScore(StudentDto sDto,List<ModelScoreDto> allScore);
	/**
	 * 将报告册所需相关内容写入excel
	 * @param fileBGD
	 * @param sDto
	 * @param masterAppral
	 * @param outSinglePersonalAppraiseSelf
	 * @param outSinglePersonalAppraiseExtra
	 * @param outSingleStudyAppral
	 * @param singleHealthData
	 * @param sigleCheckItems
	 * @param singleScore
	 * @param flag
	 */
	public void writeReportExcel(File fileBGD, StudentDto sDto,
			List<AppraisalDto> masterAppral,
			AppraisalDto outSinglePersonalAppraiseSelf,
			List<AppraisalDto> outSinglePersonalAppraiseExtra,
			List<AppraisalDto> outSingleStudyAppral,
			List<HealthDataDto> singleHealthData,
			List<CheckItemInfoDto> sigleCheckItems,
			Map<String, Map<String,List<ModelScoreDto>>>singleScore, String flag,Map<String,String> hkScore);
	/**
	 * 获取学生基本信息
	 * @param params
	 * @return
	 */
	public List<SchoolTreeDto> getStudentInfo(Map<String, Object> params);
	/**
	 * 查询会考成绩
	 * @param params
	 * @return
	 */
	public Map<String, String> queryHKScore(Map<String, Object> params);
	/**
	 * 获取区县已上报
	 * @param discode
	 * @param graduYear
	 * @return 
	 */
	public List<DataCountDto> queryReportZoneDatas(String discode, String graduYear);
	/**
	 * 获取所有区县信息
	 * @param discode
	 * @param graduYear
	 * @return
	 */
	public List<DataCountDto> queryReportZoneInfos(String discode);
	/**
	 * 获取学校相关信息
	 * @param discode
	 * @param graduYear
	 * @return
	 */
	public List<DataCountDto> queryReportSchoolInfos(String discode,String graduYear);

	/**
	 * 将报告册所需相关内容写入html
	 * @param fileBGD
	 * @param sDto
	 * @param masterAppral
	 * @param outSinglePersonalAppraiseSelf
	 * @param outSinglePersonalAppraiseExtra
	 * @param outSingleStudyAppral
	 * @param singleHealthData
	 * @param sigleCheckItems
	 * @param singleScore
     * @param flag
     * @param hkScore
     */
	void writeReportHtml(File fileBGD, StudentDto sDto, List<AppraisalDto> masterAppral, AppraisalDto outSinglePersonalAppraiseSelf, List<AppraisalDto> outSinglePersonalAppraiseExtra, List<AppraisalDto> outSingleStudyAppral, List<HealthDataDto> singleHealthData, List<CheckItemInfoDto> sigleCheckItems, Map<String, Map<String,List<ModelScoreDto>>> singleScore, String flag, Map<String, String> hkScore);
}
