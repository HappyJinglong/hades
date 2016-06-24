package com.flyrish.hades.service.ext;

public interface IMergedSchoolDataExt {
	/** 
	 * 对合并校的成绩进行合并
	 * @param cmis30id 合并校标识号
	 * @param mergedCmis30id 被合并校标识号
	 */
	public void doWithMergedSchoolData(String cmis30id,String mergedCmis30id);
	
}
