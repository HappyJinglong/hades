package com.flyrish.hades.webapp.action.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.GeneralDto;
import com.flyrish.hades.dto.KcourseSeriesDto;
import com.flyrish.hades.service.ext.IInnerCourseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class SeriesSelectDwr extends BaseAction{
	
	private IInnerCourseExt innerCourseExt;
	
	public List<KcourseSeriesDto>changeSubjectToSeries(String subjectid){
		Map<String,Object>queryMap = new HashMap<String,Object>();
		queryMap.put("subjectid", subjectid);
		List<KcourseSeriesDto> ksd = innerCourseExt.selectSeries(queryMap);
		return ksd;
	}
	
	public List<GeneralDto>changeGradeToYear(String gradenum){
		Map<String,Object>queryMap = new HashMap<String,Object>();
		queryMap.put("gradenum", gradenum);
		List<GeneralDto> gd = innerCourseExt.selectYear(queryMap);
		return gd;
	}
	
	public List<GeneralDto>changeYearToSegment(String year,String cmis30id){
		Map<String,Object>queryMap = new HashMap<String,Object>();
		queryMap.put("schoolyear", year);
		queryMap.put("cmis30id", cmis30id);
		List<GeneralDto> gd = innerCourseExt.selectSegment(queryMap);
		return gd;
	}

	public IInnerCourseExt getInnerCourseExt() {
		return innerCourseExt;
	}

	public void setInnerCourseExt(IInnerCourseExt innerCourseExt) {
		this.innerCourseExt = innerCourseExt;
	}

}
