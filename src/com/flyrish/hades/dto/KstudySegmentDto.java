package com.flyrish.hades.dto;

import java.io.Serializable;

public class KstudySegmentDto implements Serializable{

	private String segment_id;//学段标号
	
	private String segment_name;//学段名称
	
	private String schoolyear;//学年
	
	private String cmis30id;//cmis30id
	
	private String segment_order;//学段代码
	
	private String segment_start;//开始时间
	
	private String segment_end;//结束时间

	
	
	public String getSegment_name() {
		return segment_name;
	}

	public void setSegment_name(String segment_name) {
		this.segment_name = segment_name;
	}

	public String getSegment_id() {
		return segment_id;
	}

	public void setSegment_id(String segment_id) {
		this.segment_id = segment_id;
	}

	public String getSchoolyear() {
		return schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public String getCmis30id() {
		return cmis30id;
	}

	public void setCmis30id(String cmis30id) {
		this.cmis30id = cmis30id;
	}

	public String getSegment_order() {
		return segment_order;
	}

	public void setSegment_order(String segment_order) {
		this.segment_order = segment_order;
	}

	public String getSegment_start() {
		return segment_start;
	}

	public void setSegment_start(String segment_start) {
		this.segment_start = segment_start;
	}

	public String getSegment_end() {
		return segment_end;
	}

	public void setSegment_end(String segment_end) {
		this.segment_end = segment_end;
	}

	@Override
	public String toString() {
		return "KstudySegment [segment_id=" + segment_id + ", schoolyear="
				+ schoolyear + ", cmis30id=" + cmis30id + ", segment_order="
				+ segment_order + ", segment_start=" + segment_start
				+ ", segment_end=" + segment_end + "]";
	}

}
