package com.flyrish.hades.webapp.action.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyrish.hades.dto.KstudySegmentDto;
import com.flyrish.hades.dto.KsysSubjectDto;
import com.flyrish.hades.service.ext.ISchoolCourseExt;
import com.flyrish.hades.webapp.action.BaseAction;

public class SegmentSelectDwr extends BaseAction{
	private ISchoolCourseExt schoolCourseExt;
	/**
	 * 获取学段信息
	 * @param year
	 * @return
	 */
	public List<KstudySegmentDto>changeYearToSegment(String year,String cmis30id){
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("schoolyear", year);
		params.put("cmis30id", cmis30id);
		List<KstudySegmentDto> ksd = schoolCourseExt.getSegmentInfos(params);
		return ksd;
	}
	/**
	 * 获取学科信息
	 * @param year
	 * @return
	 */
	public List<KsysSubjectDto>changeSegmentToSubject(String segmentId,String cmis30id){
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("cmis30id", cmis30id);
		params.put("segmentId", segmentId);
		List<KsysSubjectDto> ksd = schoolCourseExt.getSubjectByCTS(params);
		return ksd;
	}
	
	public ISchoolCourseExt getSchoolCourseExt() {
		return schoolCourseExt;
	}
	public void setSchoolCourseExt(ISchoolCourseExt schoolCourseExt) {
		this.schoolCourseExt = schoolCourseExt;
	}
	
}
