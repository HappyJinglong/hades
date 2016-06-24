package com.flyrish.hades.service.ext;

import java.util.List;
import java.util.Map;

public interface IAppraisalStaticsExt {
	/**
	 * 统计某学校、某年级、某学期、某栏目评价信息
	 * @param schoolInfos Map<学校Id,Map<年级号,List<教育Id>>>
	 * @param termid 学期
	 * @param two_part_id 栏目号
	 * @param clazz 指定数据类型
	 * @return Map<学校Id,Map<年级号,List<Map<评价类型,评价数>>>> List长度为2 分别装 全部统计和单个统计
	 */
	public <T> Map<String,Map<String,List<Map<Object,Integer>>>> queryRecordStaticsInChache(Map<String,Map<String,List<String>>>schoolInfos,String termid,String two_part_id,Class clazz);
	/**
	 * 统计对应班级某个栏目填写情况
	 * @param eduIds 学生教育ID
	 * @param termid 学期
	 * @param two_part_id 二级栏目号
	 * @param clazz 指定数据类型
	 * @return Map<评价类型,计数>
	 */
	public <T> Map<Object,Integer> queryRecordStaticsInChache(List<String> eduIds,String termid,String two_part_id,Class clazz);
	
	/**
	 * 获取学校年级、班级班主任评语统计信息
	 * @param cmis30Ids 学校Id
	 * @param userType 用户类型
	 * @param levelFlag 学段标识号(userDto.getLevelId())
	 * @param campusId 校区ID
	 * @return 教务老师返回数据--Map<年级号-班级号-学期,计数> 
	 */
	public Map<String,Integer> queryAssessStatics(List<String>cmis30Ids,String userType,String levelFlag,String campusId);
	/**
	 * 获取对应学校每个年级、班级学生eduId
	 * @param cmis30Id 学校Id
	 * @param userType 用户类型(userDto.getUerRrealType()教务老师、班主任)
	 * @param levelFlag 学段标识号(userDto.getLevelId()教务老师)（市区用户初中2012002、高中不填值）
	 * @param campusId 校区ID（市区用户可省略）
	 * @return 教务老师或者班主任 Map<学校-年级-班级,List<eduId>>   市区县用户Map<学校-年级-校区ID-班级,List<eduId>>
	 */
	public Map<String,List<String>> querySchoolInfos(List<String>cmis30Id,String userType,String levelFlag,String campusId);
	/**
	 * 获取对应学校每个年级、班级学生eduId
	 * @param cmis30Id 学校Id
	 * @param userType 用户类型(userDto.getUerRrealType()教务老师、班主任)
	 * @param levelFlag 学段标识号(userDto.getLevelId())（市区用户初中2012002、高中不填值）
	 * @param campusId 校区ID（市区用户可省略）
	 * @return 教务老师或者班主任 Map<学校-年级-班级,List<eduId>>   市区县用户Map<学校-年级-校区ID-班级,List<eduId>>
	 */
	public Map<String, List<String>> querySchoolInfos_new(List<String> cmis30Ids,String userType,String levelFlag,String campusId);
	/**
	 * 获取市区级用户、班主任、教务老师界别信息
	 * @param levelCode 学段代码（班主任、教务老师）
	 * @param campuseId  校区id(班主任、教务老师)
	 * @param teacherId 教师标识号（班主任）
	 * @param flag 区别市区、老师（班主任任教务老师取值 "1" 市区不用传值）
	 * @return List<gradeid_届别>
	 */
	public List<String>queryJB(String levelCode,String campuseId,String teacherId,String flag);
	/**
	 * 获取市区级用户、班主任、教务老师界别信息
	 * @param levelid
	 * @param levelCode 学段代码（班主任、教务老师）
	 * @param campuseId  校区id(班主任、教务老师)
	 * @param teacherId 教师标识号（班主任）
	 * @param flag 区别市区、老师（班主任任教务老师取值 "1" 市区不用传值）
	 * @return List<gradeid_届别>
	 */
	public List<String>queryJB(String levelid,String levelCode,String campuseId,String teacherId,String flag);
	/**
	 * 获取教务老师或者班主任管辖班级
	 * @param gradeid 年级标识号
	 * @param teacherId 教师id
	 * @return List<classId_班级号>
	 */
	public List<String>queryClass(String gradeid,String teacherId);
	/**
	 * 获取教务老师、市区级用户termIDs
	 * @param levelCode 学段代码
	 * @param levelid 学段id
	 * @param userType 用户类型（市区级用户不填写内容）
	 * @return
	 */
	public List<String>queryTermIds2(String levelCode,String levelid,String userType);
	/**
	 * 查询区县代码
	 * @return
	 */
	public List<String>queryDiscode();
}
