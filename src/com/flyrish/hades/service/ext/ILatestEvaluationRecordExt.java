package com.flyrish.hades.service.ext;

import java.util.Date;
import java.util.List;

import org.nestframework.commons.hibernate.ISqlElement;
import org.nestframework.exporter.exception.ManagerException;

public interface ILatestEvaluationRecordExt {
	/**
	 * 把班主任评价记录信息放入放入缓存中
	 * @param campuseid 校区标识号
	 * @param levelcode 学段代码
	 * @param username 班主任（评价人）的登录用户名
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 * @param columnName 栏目名称 如：合作与交流
	 * @param studentName 被评价学生的姓名 如：张三
	 * @param stuEduid 被评价学生的教育id
	 * @param date 评价的时间
	 */
	public void setheadMasterRecordToCache(String campuseid,String levelcode,
										   String username,
										   String columNum,String proKey,
										   String columnName,String studentName,
										   String stuEduid,Date date);
	/**
	 * 通过栏目号及用户标识号删除相应的班主任评价记录
	 * @param campuseid 校区标识号
	 * @param levelcode 学段代码
	 * @param username 登录用户名称
	 * @param stuEduid 被评价人的教育id
	 * @param columNum 栏目号
	 * @param proKey 被删除记录的主键
	 */
	public void deleteheadMasterRecordInCache(String campuseid,String levelcode,
											   String username,String stuEduid,
											   String columNum,String proKey);
	/**
	 * 把任课老师评价记录信息放入放入缓存中
	 * @param campuseid 校区标识号
	 * @param levelcode 学段代码
	 * @param username 任课老师（评价人）的登录用户名
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 * @param columnName 栏目名称 如：合作与交流
	 * @param studentName 被评价学生的姓名 如：张三
	 * @param stuEduid 被评价学生的教育id
	 * @param date 评价的时间 
	 */
	public void setteacherRecordToCache(String campuseid,String levelcode,
										String username,
										String columNum,String proKey,
										String columnName,String studentName,
										String stuEduid,Date date);
	/**
	 * 通过栏目号及用户标识号删除相应任课老师评价记录
	 * @param campuseid 校区标识号
	 * @param levelcode 学段代码
	 * @param username 登录用户名称
	 * @param stuEduid 被评价人的教育id
	 * @param columNum 栏目号
	 * @param proKey 被删除记录的主键
	 */
	public void deleteteacherRecordInCache(
				String campuseid,String levelcode,
			    String username,String stuEduid,
			    String columNum,String proKey
			);
	/**
	 * 把学生本人的评价记录信息放入放入缓存中
	 * @param username 学生（评价人）的登录用户名
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 * @param columnName 栏目名称 如：合作与交流
	 * @param studentName 被评价学生的姓名 如：张三
	 * @param date 评价的时间
	 */
	public void setstudentRecordToCache(String username,
										String columNum,String proKey,
										String columnName,String studentName,Date date);
	/**
	 * 通过栏目号及用户标识号删除自己的评价记录
	 * @param username 学生教育id
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 */
	public void deletestudentRecordInCache(String username,
										   String columNum,String proKey);
	/**
	 * 通过redisKey删除指定栏目的评价记录
	 * @param redisKey 缓存key
	 * @param oneLevelNum 一级栏目号
	 */
	public void deleteStatisticsRecordDataInCache(String redisKey,String oneLevelNum);
	/**
	 * 通过redisKey统计指定栏目的评价记录数量
	 * @param redisKey 缓存key
	 * @param oneLevelNum 一级栏目号
	 * @return 统计的数量
	 */
	public Integer queryStatisticsRecordDataInCache(String redisKey,String oneLevelNum);
	/**
	 * 把同学的评价记录信息放入放入缓存中
	 * @param username 同学（评价人）的登录用户名
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 * @param columnName 栏目名称 如：合作与交流
	 * @param studentName 被评价学生的姓名 如：张三
	 * @param stuEduid 被评价学生的教育id
	 * @param date 评价的时间
	 */
	public void setclassMateRecordToCache(String username,
										  String columNum,String proKey,
										  String columnName,String studentName,String stuEduid,Date date);
	/**
	 * 通过栏目号及用户标识号删除同学对自己的评价记录
	 * @param stuEduid 学生教育id
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 */
	public void deleteclassMateRecordInCache(String stuEduid,
										   String columNum,String proKey);
	
	/**
	 * 把家长的评价记录信息放入放入缓存中
	 * @param columnName 栏目名称 如：合作与交流
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 * @param studentName 被评价孩子的姓名 如：张三
	 * @param stuEduid 被评价孩子的教育id
	 * @param date 评价的时间
	 */
	public void setparentRecordToCache(String columnName,
			 String columNum,String proKey,
			 String studentName,String stuEduid,Date date);
	/**
	 * 通过栏目号及用户标识号删除家长对自己的评价记录
	 * @param stuEduid 学生教育id
	 * @param columNum 栏目号
	 * @param proKey 评价记录的主键标识号
	 */
	public void deleteparentRecordToCache(String stuEduid,
										   String columNum,String proKey);
	
	
	
	
	
	/**
	 * 通过指定学期，指定栏目所有评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中时20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @return 指定栏目下所评价的信息集合
	 */
	//public <T> List<T> queryRecodeInCache(String edu_id,String termid,String two_part_id,Class clazz);
	
	/**
	 * 通过指定学期，指定栏目所有评价信息(查询指定类型的数据集合)
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中时20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param clazz 指定类型的数据集
	 * @return 指定栏目下所评价的信息集合
	 */
	public <T> List<T> queryRecodeInCache(String edu_id,String termid,String two_part_id,Class clazz);
	/**
	 * 通过指定学期，指定栏目，指定评价人所有评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中时20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @return 指定栏目下某评价人所评价的信息集合
	 */
	//public <T> List<T> queryRecodeInCache(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,Class clazz);
	/**
	 * 通过指定学期，指定栏目，指定评价人所有评价信息(查询指定类型的数据集合)
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中时20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param clazz 指定类型的数据集
	 * @return 指定栏目下某评价人所评价的信息集合
	 */
	public <T> List<T> queryRecodeInCache(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,Class clazz);
	/**
	 * 通过指定学期，指定栏目，指定评价人，指定记录评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param prokey 主键key
	 * @param clazz 指定类型的数据集
	 * @return 指定栏目下某评价人，某条评价记录
	 */
	public <T> T queryRecodeInCache(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,Class clazz);
	
	/**
	 * 更新指定学期，指定栏目，指定评价人，指定记录评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param prokey 主键key
	 * @param sqlDemo 含有Sql语句及Sql参数值得对象
	 * @param t 需要更新的数据
	 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid,prokey,t,sqlDemo任何一个为Null都将抛出异常
	 * 		        或者操作失败时，也会此抛异常
	 */
	public <T> void updateRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t,ISqlElement sqlDemo,String signdate);
	
	/**
	 * 添加指定学期，指定栏目，指定评价人，指定记录评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param prokey 主键key
	 * @param sqlDemo 含有Sql语句及Sql参数值得对象
	 * @param t 需要添加的数据对象
	 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid,prokey,t,sqlDemo任何一个为Null都将抛出异常
	 *  或者操作失败时，也会此抛异常
	 */
	public <T> void addRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t,ISqlElement sqlDemo,String signdate);
	/**
	 * 添加指定学期，指定栏目，指定评价人，指定记录评价信息（不走数据库）
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param prokey 主键key
	 * @param t 需要添加的数据对象
	 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid,prokey,t任何一个为Null都将抛出异常
	 *  或者操作失败时，也会此抛异常
	 */
	public <T> void addRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,T t);
	

	
	/**
	 * 删除指定学期，指定栏目，指定评价人，指定记录评价信息
	 * @param edu_id 被评价学生教育id
	 * @param termid 学期标识号（高中11、12等，初中20141、20142等）
	 * @param two_part_id 二级栏目标识号
	 * @param appraseridentify 评价人身份
	 * @param appraiserrid 评价人标识号
	 * @param sqlDemo 含有Sql语句及Sql参数值得对象
	 * @param prokey 主键key
	 * @param clazz 删除指定数据集
	 * @throws ManagerException 如果edu_id、termid、two_part_id、appraseridentify、appraiserrid,prokey,t,sqlDemo任何一个为Null都将抛出异常
	 * 或者操作失败时，也会此抛异常
	 */
	public <T> void delRecodeInCacheByProKey(String edu_id,String termid,String two_part_id,String appraseridentify,String appraiserrid,String prokey,ISqlElement sqlDemo,Class clazz);
	
	/**
	 * 通过主键查询附件
	 * @param foreignKey 外键
	 * @param attachid 附件主键
	 * @param tablename 表名
	 * @param clazz
	 * @return 返回附件，不存在返回null
	 */
	public <T> T queryAttachFileInCache(String foreignKey,String attachid,String tablename,Class clazz);
	/**
	 * 通过外键查询附件相关附件集合
	 * @param foreignKey 外键
	 * @param tablename 表名
	 * @param clazz 获取指定数据集
	 * @return 返回附件集合，不存在返回null
	 */
	public <T> List<T> queryAttachFileInCache(String foreignKey,String tablename,Class clazz);
	/**
	 * 向缓存添加附件信息
	 * @param foreignKey 外键
	 * @param attachid 附件主键
	 * @param t 放入的对象
	 * @param tablename 表名
	 * @param two_part_id 该附件所属二级栏目号
	 * @param sqlDemo Sql对象
	 * @throws ManagerException 如果attachid、t、levelCode、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void addAttachFileInCache(String foreignKey,String attachid,T t,String tablename,String two_part_id,ISqlElement sqlDemo);
	/**
	 * 向缓存添加附件信息（不走数据库）
	 * @param foreignKey 外键
	 * @param attachid 附件主键
	 * @param t 放入的对象
	 * @param tablename 表名
	 * @throws ManagerException 如果attachid、t、levelCode、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void addAttachFileInCache(String foreignKey,String attachid,T t,String tablename);
	/**
	 * 删除缓存中的附件信息
	 * @param foreignKey 外键
	 * @param attachid 附件主键
	 * @param tablename 表名
	 * @param two_part_id 该附件所属二级栏目号
	 * @param sqlDemo Sql对象
	 * @param clazz 指定数据集
	 * @throws ManagerException 如果foreignKey、attachid、levelCode、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void deleteAttachFileInCache(String foreignKey,String attachid,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz);
	/**
	 * 删除缓存中的某一条评价记录的所有附件信息
	 * @param foreignKey 外键
	 * @param tablename 表名
	 * @param two_part_id 该附件所属二级栏目号
	 * @param sqlDemo Sql对象
	 * @param clazz 删除指定数据集
	 * @throws ManagerException 如果foreignKey、levelCode、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void deleteAttachFileAllByforeignKey(String foreignKey,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz);
	/**
	 * 通过主键查询缓存中的附表信息
	 * @param foreignKey 外键
	 * @param prokey 附表主键
	 * @param tablename 表名
	 * @param clazz 指定数据集
	 * @return 附表对象
	 */
	public <T> T queryChildrenObjectInCache(String foreignKey,String prokey,String tablename,Class clazz);
	/**
	 * 通过外键获取相关联子表的数据信息
	 * @param foreignKey 外键
	 * @param tablename 表名
	 * @param clazz 指定数据集
	 * @return 子表数据信息集合
	 */
	public <T> List<T> queryChildrenObjectListInCache(String foreignKey,String tablename,Class clazz);
	/**
	 * 通过主键更新缓存中的附表信息
	 * @param foreignKey 外键
	 * @param prokey 附表主键
	 * @param tablename 表名
	 * @param two_part_id 该附件所属二级栏目号
	 * @param t 附表主键
	 * @param sqlDemo Sql对象
	 * @param clazz 指定数据集
	 * @throws ManagerException 如果prokey、tablename、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void updateChildrenObjectlInCache(String foreignKey,String prokey,T t,String tablename,String two_part_id,ISqlElement sqlDemo,String signdate);
	/**
	 * 通过主键删除附表对象
	 * @param foreignKey 外键
	 * @param prokey 附表主键
	 * @param tablename 表名
	 * @param two_part_id 该附表所属二级栏目号
	 * @param sqlDemo Sql对象
	 * @param clazz 指定数据集
	* @throws ManagerException 如果prokey、tablename、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void deleteChildrenObjectInCache(String foreignKey,String prokey,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz);
	/**
	 * 通过外键删除相关附表集合
	 * @param foreignKey 外键
	 * @param tablename 表名
	 * @param two_part_id 该附表所属二级栏目号
	 * @param sqlDemo Sql对象
	 * @param clazz 指定数据集
	* @throws ManagerException 如果foreignKey、tablename、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void deleteChildrenObjectInCache(String foreignKey,String tablename,String two_part_id,ISqlElement sqlDemo,Class clazz);
	
	/**
	 * 向缓存中添加附表对象
	 * @param foreignKey 外键
	 * @param prokey 附表
	 * @param tablename 表名
	 * @param two_part_id 该附件所属二级栏目号
	 * @param t 附表对象
	 * @param sqlDemo Sql对象
	 * @throws ManagerException 如果prokey、t、tablename、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void addChildrenObjectInCache(String foreignKey,String prokey,T t,String tablename,String two_part_id,ISqlElement sqlDemo,String signdate);
	
	/**
	 * 向缓存中添加附表对象（不走数据库）
	 * @param foreignKey 外键
	 * @param prokey 附表
	 * @param tablename 表名
	 * @param t 附表对象
	 * @param sqlDemo Sql对象
	 * @throws ManagerException 如果prokey、t、tablename、sqlDemo任何一个为Null都将抛出异常
	 */
	public <T> void addChildrenObjectInCache(String foreignKey,String prokey,T t,String tablename);
	/**
	 * 放key到指定容器中(默认走的是十万个容器)
	 * @param redisKey
	 * @param proKeyName 容器名称
	 */
	public void recodeKeyInTotalKey(String redisKey,String proKeyName);
	/**
	 * 放key到指定容器中(指定容器)
	 * @param redisKey
	 * @param containerNums 容器数量
	 * @param proKeyName 容器名称
	 */
	public void recodeKeyInTotalKey(String redisKey,String proKeyName,String containerNums);
}
