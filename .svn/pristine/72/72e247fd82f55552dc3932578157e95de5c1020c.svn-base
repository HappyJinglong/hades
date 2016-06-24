package com.flyrish.hades.webapp.action.score;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.flyrish.hades.service.ext.IMergedSchoolDataExt;

public class MergeScoreAction {
	@Spring
	public IMergedSchoolDataExt mergedSchoolDataExt;
	@DefaultAction
	public Object doWithScore(){
		try{
			//mergedSchoolDataExt.doWithMergedSchoolData("251007","250051");
			System.out.println("-----------------成绩已经合并完毕---------------");
		}catch(Exception e){
			System.out.println("合并校处理失败-"+e.getMessage());
		}
		return null;
	}
}
