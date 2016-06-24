package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

import org.nestframework.commons.hibernate.IPage;

import com.flyrish.hades.dto.DownloadDto;
import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.KclassModelDto;
import com.flyrish.hades.dto.KcourseArrangeDto;
import com.flyrish.hades.dto.KcourseDto;
import com.flyrish.hades.dto.KcourseSeriesDto;
import com.flyrish.hades.dto.SysdictDto;
import com.flyrish.hades.dto.UserDto;

public interface IInnerCourseExt {
	
	/**
	 * 查询内置课程数据
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public IPage selectInnertCourse(Map<String,Object> queryMap,Integer pageNo,Integer pageSize);
	
	/**
	 * 查询内置课程数据
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public IPage selectInnertCourse1(Map<String,Object> queryMap,Integer pageNo,Integer pageSize);

	/**
	 * 查询内置课程数据List
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KcourseDto> selectInnertCourseList(Map<String,Object> queryMap);
	
	/**
	 * 查询内置课程数据List
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KcourseDto> selectInnertCourseCheckList(Map<String,Object> queryMap);
	
	/**
	 * 查询内置课程数据
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KcourseArrangeDto> selectArrange(Map<String, Object> queryMap);
	
	/**
	 * 查询系列
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KcourseSeriesDto> selectSeries(Map<String, Object> queryMap);
	
	/**
	 * 查询学年
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<GeneralDto> selectYear(Map<String, Object> queryMap);
	
	/**
	 * 查询年级
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<GeneralDto> selectGread(Map<String, Object> queryMap);
	
	/**
	 * 查询班级
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<GeneralDto> selectClass(Map<String, Object> queryMap);
	
	/**
	 * 查询全部年级的全部班级
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<GeneralDto> selectNowClass(Map<String, Object> queryMap);
	
	/**
	 * 查询学段
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<GeneralDto> selectSegment(Map<String, Object> queryMap);
	
	/**
	 * 查询学习方向
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<SysdictDto> selectAspect(Map<String, Object> queryMap);
	
	/**
	 * 增加内置课程数据
	 * @param 附件dto
	 * 
	 */
	public void insertKcourse(KcourseDto courseDto,String[] str);
	
	/**
	 * 更新内置课程数据
	 * @param 附件dto
	 * 
	 */
	public void updateKcourse(KcourseDto courseDto,String[] str);
	
	/**
	 * 删除内置课程
	 * @param 附件模块id
	 * 
	 */
	public void deleteKcourse(String id);
	
	/**
	 * 判断是否可以删除内置课程
	 * @param Map查询参数
	 * 
	 */
	public Integer checkDeleteCourse(Map<String, Object> params);
	
	/**
	 * 判断是否可以删除授课教师
	 * @param Map查询参数
	 * 
	 */
	public Integer CheckDeleteClass(String courseid,String segmentid, String cmis30id, String year, String gradeid);
	
	/**
	 * 查询教师授课模块
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KcourseDto> selectTeacherCourse(Map<String, Object> queryMap);
	
	/**
	 * 增加学段开设的模块
	 * @param 附件dto
	 * 
	 */
	public void insertSegmentModel(final String segmentid,final List<String> courseList,List<String> classList,Map<String, String> queryMap);
	
	/**
	 * 查询教师授课模块
	 * @param Map查询参数
	 * @return list栏目内容数据
	 */
	public List<KclassModelDto> selectClassModel(Map<String, Object> queryMap);
	
	/**
	 * excel插入
	 * @param 附件模块id
	 * 
	 */
	public void insertExlSegmentModel(final String segmentid,List<String> lst,Map<String, Object> classMap,int yearcount);
	
	/**
	 * 删除教师授课
	 * @param 附件模块id
	 * 
	 */
	public void deleteTeacherCourse(String courseid,String segmentid, String cmis30id, String year, String gradeid);
	/**
	 * 删除教师授课
	 * @param 附件模块id
	 * 
	 */
	public void deleteTeacherCourse(String courseid,String segmentid, String cmis30id, String year, String gradeid,UserDto userDto);
	
	/**
	 * 更新授课教师
	 * @param 附件dto
	 * 
	 */
	public void updateClassModel(final List<String> idList,final List<String> valueList);
	/**
	 * 更新授课教师
	 * @param 附件dto
	 * 
	 */
	public void updateClassModel(final List<String> idList,final List<String> valueList,UserDto userDto);
	
	/**
	 * 查询导出数据
	 * @param 附件dto
	 * 
	 */
	public List<DownloadDto> selectDownload(Map<String, Object> queryMap);

	public List<KcourseDto> selectInnertCourseCheckList1(
			Map<String, Object> params);

	/**
	 * 插入缺少数据的数据
	 * @param insertData
	 * @param userDto
	 */
	public void insertQSDatas(List<String> insertData, UserDto userDto);

}
