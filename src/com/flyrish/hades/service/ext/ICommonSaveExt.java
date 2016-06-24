package com.flyrish.hades.service.ext;

public interface ICommonSaveExt {
	/**
	 * 保存更新评价信息
	 * @param apprasialid 评价标识号
	 * @param appraisaltypeid 评价类型
	 * @param apprasial 评价内容
	 * @param levelcode 学段代码
	 */
	public void saveCommonAppraise(String apprasialid,String appraisaltypeid,String apprasial,String levelcode);

	/**
	 * 保存更新评价信息(缓存)
	 * @param stuEduid 教育id
	 * @param termid 学期id
	 * @param columNum 栏目号
	 * @param appraseridentify 评价人身份标识号
	 * @param appraiserrid 评价人教育id
	 * @param proKey 主键
	 * @param levelcode 学段
	 */
	public void saveCommonAppraiseToCache(String apprasial,String stuEduid, String termid,String columNum, String appraseridentify, String appraiserrid,String proKey, String levelcode,String appraisaltypeid);
}
