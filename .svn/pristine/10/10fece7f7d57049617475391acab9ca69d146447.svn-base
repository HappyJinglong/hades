<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SegmentSelectDwr.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SeriesSelectDwr.js'></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<title>模块添加</title>
<style >
	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	
</style>
<script>
var $$=jQuery.noConflict();
	function pageBack(){
		$$("#form2").submit();
	}
	var isSubmit=0;
	function checkData(){
		$$("#timeCount").html("数据保存中...");
		ShowDiv();
		if(dataCheck()){
			var gradeName = "";
			var segmentName="";
			$$("[name='gradeName']").each(function (){
				if($$(this).attr("checked")){
					gradeName+="@"+$$(this).val();
				}
			});
			$$("[name='segmentName']").each(function (){
				if($$(this).attr("checked")){
					segmentName+="@"+$$(this).val();
				}
			});
			if(isSubmit==0){
				isSubmit=1;
				Ext.Ajax.request({
					url:'${ctx }/schoolCourse/SchoolCourseAction.a?addCourse',
					method:'POST',
					success:function(response,options){
						isSubmit = 0;
						var temp=response.responseText; 
						if(temp=="0"){
							$$("#MyDiv").css("display","none");
							course_alert_failure("保存失败！");
							return;
						}
						if(temp=="12"){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块名称和模块编号已经存在！");
							return;
						}else if(temp=="1"){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块名称已经存在！");
							return;
						}else if(temp=="2"){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块编号已经存在！");
							return;
						}else if(temp.indexOf("2")>=0 && temp.indexOf("1")>=0){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块名称和模块编号已经存在！");
							return;
						}else if(temp.indexOf("2")>=0){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块编号已经存在！");
							return;
						}else if(temp.indexOf("1")>=0){
							$$("#MyDiv").css("display","none");
							course_alert_failure("模块名称已经存在！");
							return;
						}
						var str = '<input id="successFlag" name="successFlag" type="hidden" value="save"/>';
						$$("#year1").val($$("#year").val());
						$$("#isSearch").val("0");
						$$("#segmentId1").val($$("#segmentId").val());
						$$("#subjectId1").val($$("#subjectId").val());
						$$("#form2").append(str);
						$$("#form2").submit();
					},
					params : {
						subjectId:$$("#subjectId").val(),
						seriesid:$$("#seriesid").val(),
						courseCode:$$.trim($$("#courseCode").val()),
						courseName:$$.trim($$("#courseName").val()),
						courseShortName:$$("#courseShortName").val(),
						studentAspect:$$("#studentAspect").val(),
						periodCount:$$("#periodCount").val(),
						creditHour:$$("#creditHour").val(),
						contentIntroduction:$$("#contentIntroduction").val(),
						joinRequirement:$$("#joinRequirement").val(),
						teachRequirement:$$("#teachRequirement").val(),
						courseRemark:$$("#courseRemark").val(),
						teacherId:$$("#teacherId").val(),
						segmentId:$$("#segmentId").val(),
						gName:gradeName,
						sName:segmentName,
						teacherIdInfo:$$("#teacherIdInfo").val()
					}
				}); 
			}else{
				alert_KG_self_tip("正在保存中...");
			}
		}
	}
	//页面数据校验
	function dataCheck(){
		if(!checkInpu($$("#year"),"学年")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#segmentId"),"学段")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#subjectId"),"学科")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#courseCode"),"模块编号")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#courseName"),"模块名称")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#teacherId"),"指导教师")){
			$$("#MyDiv").css("display","none");
			return false;			
		}
		if(!checkInpu($$("#periodCount"),"学时数")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#creditHour"),"学分")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkGrade()){
			$$("#MyDiv").css("display","none");
			course_alert_failure("适用年级为必选项");
			return false;
		}
		if(!checkOtherData($$("#contentIntroduction"),122,"内容简介")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkOtherData($$("#joinRequirement"),100,"参加要求")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkOtherData($$("#teachRequirement"),100,"教学要求")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkOtherData($$("#courseRemark"),1000,"备注")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		return true;
	}
	function checkGrade(){
		var flag = 0 ;
		$$("[name='gradeName']").each(function (){
			if($$(this).attr("checked")){
				flag = 1 ;
			}
		});
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	//修改学年改变学段
	function changYearToSegment(year){ //v3.0
		if(year =='' || null!=year){
			year=$$("#year").val();
		}
		dwr.engine.setAsync(false);
		if(null!=year&&''!=year)
		{
			SegmentSelectDwr.changeYearToSegment(year,'${cmis30id}',function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("segmentId");
					DWRUtil.addOptions("segmentId",{"":"请选择"});
					DWRUtil.addOptions("segmentId",obj ,"segment_id" ,"segment_name" );
					/* DWRUtil.setValue("segmentId", '${segmentId}'); */
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("segmentId");
			DWRUtil.addOptions("segmentId", {"":"请选择"});
		}
		dwr.engine.setAsync(true);
	}
	//修改学科联动系列
	function changeSeriesid(subjectid){ //v3.0
		if(subjectid =='' || null!=subjectid){
			subjectid=$$("#subjectId").val();
		}
		dwr.engine.setAsync(false);
		if(null!=subjectid&&''!=subjectid)
		{
			SeriesSelectDwr.changeSubjectToSeries(subjectid,function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("seriesid");
					DWRUtil.addOptions("seriesid", {"":"请选择"});
					DWRUtil.addOptions("seriesid",obj ,"series_id" ,"series_name" );
				}else{
					DWRUtil.removeAllOptions("seriesid");
					DWRUtil.addOptions("seriesid", {"":"请选择"});
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("seriesid");
			DWRUtil.addOptions("seriesid", {"":"请选择"});
		}
		dwr.engine.setAsync(true);
	}
	//获取老师信息
	function getTeacherInfo(currentObj){
		var teacherName="";
		if(""!==currentObj){
			teacherName = encodeURI(encodeURI(currentObj.val()));
		}
		 $$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "${ctx }/schoolCourse/SchoolCourseAction.a?queryTeacherInfo&&guidTeacherName="+teacherName,
				data: "{}",
				dataType: "json",
				success: function (msg) {
					if(!msg){
						return;
					}
					$$("#guidTeacherName").autocomplete(msg.val,{
						minChars: 0,
						width: 310,
						matchContains: true,
						max:2000,
						formatItem: function(row, i, max) {
							return  row.name ;
						},
						formatMatch: function(row, i, max) {
							return  row.name;
						},
						formatResult: function(row) {
							return row.name;
						}
					}).result(function(event, row) {
						$$("#teacherId").val(row.id);
						$$("#teacherIdInfo").val(row.name);
					});
				}
			});
	}
	function checkRepeat(currentObj,value){
 		if(!checkInpu(currentObj,value)){
			return;
		}
		var courseInfo = currentObj.val();
		var courseFlag="";
		if(currentObj.attr("id")=="courseCode"){
			courseFlag="courseCode";
		}else{
			courseFlag="courseName";
		}
		Ext.Ajax.request({
			url:'${ctx }/schoolCourse/SchoolCourseAction.a?checkRepeat',
			method:'POST',
			success:function(response,options){
				var temp=response.responseText; 
				if(temp=="0"){
					/* alert("保存失败！") */
					alert_save_failure("");
				}
				if(temp=="12"){
					/* alert("模块名称和模块编已经存在！"); */
					alert_KG_self_tip("模块名称和模块编已经存在！");
				}else if(temp=="1"){
					/* alert("模块名称已经存在！"); */
					alert_KG_self_tip("模块名称已经存在！");
				}else if(temp=="2"){
					/* alert("模块编号已经存在！"); */
					alert_KG_self_tip("模块编号已经存在！");
				}
			},
			params : {
				subjectId:$$("#subjectId").val(),
				seriesid:$$("#seriesid").val(),
				courseCode:$$("#courseCode").val(),
				courseName:$$("#courseName").val(),
				courseShortName:$$("#courseShortName").val(),
				studentAspect:$$("#studentAspect").val(),
				periodCount:$$("#periodCount").val(),
				creditHour:$$("#creditHour").val(),
				contentIntroduction:$$("#contentIntroduction").val(),
				joinRequirement:$$("#joinRequirement").val(),
				teachRequirement:$$("#teachRequirement").val(),
				courseRemark:$$("#courseRemark").val(),
				teacherId:$$("#teacherId").val(),
				segmentId:$$("#segmentId").val()
			}
		}); 
	}
</script>
</head>
<body onload="changYearToSegment('');changeSeriesid('');getTeacherInfo('');">
		<div style="width: 100%;">
			<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">添加模块</a>
			</div>
			<div class="span12" style="width: 100%;margin-left: 0px;margin-top: 0px">
						<div class="widget-box" style="font-size: 14px;font-weight:bold;color: #666;margin-top: 0px"> 
							<div style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								模块信息
							</div>
							<div class="widget-content nopadding">
								<form action="#" method="post" class="form-horizontal" />
									<div class="control-group">
											<label class="control-label" style="width: 480px">学年：</label>
											<div class="controls">
				 								<select style="width: 480px" name="year" id="year" onchange="changYearToSegment(this.value)">
													<!-- <option value="">请选择</option> -->
						               				<app:courseYear selectNum="${isLockButton}"/>
						              			</select><span style="color: red"> *</span>
											</div>
									</div>
									<div class="control-group">
											<label class="control-label" style="width: 480px">学段：</label>
											<div class="controls">
					 					 		<select id="segmentId" name="segmentId" style="width: 480px">
										 			 <option value="">请选择</option>
										 		</select><span style="color: red"> *</span>
											</div>
									</div>
									<div class="control-group" >
										<label class="control-label" style="width: 480px">学科：</label>
										<div class="controls">
					 					 		<select name="subjectId" id="subjectId" style="width: 480px" onchange="changeSeriesid(this.value);">
													<option value="">请选择</option>
						               				<app:sysSubjectSelectTag subjectId=""/>
						              			</select>
									 		<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group" >
										<label class="control-label" style="width: 480px">模块编号：</label>
										<div class="controls">
											<input type="text" maxlength="20" id="courseCode" name="courseCode" style="width: 480px" value=""/>
											<span style="color: red"> *</span><span id="courseCode_error" style="color: red;display:none;"> 模块编号已经存在</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块名称：</label>
										<div class="controls">
											<input id="courseName" maxlength="60" name="courseName" value="" type="text" style="width: 480px"/>
											<span style="color: red"> *</span><span id="courseName_error"  style="color: red;display:none;"> 模块名称已经存在</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块简称：</label>
										<div class="controls">
											<input id="courseShortName" maxlength="60" name="courseShortName" value="" type="text" style="width: 480px"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">指导教师：</label>
										<div class="controls">
											<input id="teacherId"  name="teacherId" type="hidden"/>
											<input id="teacherIdInfo"  name="teacherIdInfo" type="hidden"/>
											<input 
												id="guidTeacherName" 
												name="guidTeacherName" 
												value=""
												type="text" style="width: 480px"/>
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">学时数：</label>
										<div class="controls">
											<input id="periodCount" name="periodCount" maxlength="6" value="" type="text" style="width: 480px"/>
											<span style="color: red"> *</span>
										</div>
									</div>
										<div class="control-group">
										<label class="control-label" style="width: 480px">学分：</label>
										<div class="controls">
											<input id="creditHour" name="creditHour" maxlength="4" value="" type="text" style="width: 480px" />
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">适用年级：</label>
										<div class="controls" style="font-size: 12px;font-weight:normal;">
											<input type="checkbox"  name="gradeName" value="1230001"/>&nbsp;&nbsp; 高一年级&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox"  name="gradeName" value="1230010"/> &nbsp;&nbsp;高二年级&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox"  name="gradeName" value="1230020"/>&nbsp;&nbsp; 高三年级
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">适用学段：</label>
										<div class="controls" style="font-size: 12px;font-weight:normal;">
											<input type="checkbox"  name="segmentName" value="1230101"/>&nbsp;&nbsp; 第1学段&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox"  name="segmentName" value="1230110"/> &nbsp;&nbsp;第2学段&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox"  name="segmentName" value="1230120"/> &nbsp;&nbsp;第3学段&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox"  name="segmentName" value="1230140"/>&nbsp;&nbsp; 第4学段
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">学习方向：</label>
										<div class="controls">
											<select name="studentAspect"  id="studentAspect" style="width: 480px">
									 			<option value="">请选择</option>
									 			<app:aspect selectNum=""/>
									 		</select>
										</div>
									</div>
										<div class="control-group">
										<label class="control-label" style="width: 480px">系列：</label>
										<div class="controls">
											<select style="width: 480px" id="seriesid" name="seriesid">
									 		</select>
										</div>
									</div>
										<div class="control-group">
										<label class="control-label" style="width: 480px">内容简介：</label>
										<div class="controls">
											<input id="contentIntroduction" maxlength="122" name="contentIntroduction" type="text" style="width: 480px"  value="" />
										</div>
									</div>
										<div class="control-group">
										<label class="control-label" style="width: 480px">参加要求：</label>
										<div class="controls">
											<input id="joinRequirement" maxlength="100" name="joinRequirement" type="text" style="width: 480px" value=""/>
										</div>
									</div>
										<div class="control-group">
										<label class="control-label" style="width: 480px">教学要求：</label>
										<div class="controls">
											<input id="teachRequirement" maxlength="100" name="teachRequirement" type="text" style="width: 480px" value=""/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">备注：</label>
										<div class="controls">
												<textarea id="courseRemark" name="courseRemark" rows="10" style="width: 480px" value=""></textarea>
										</div>
									</div>
									<div class="form-actions"  style="padding-right: 30px;text-align: right;">
												<input type="button" class="btn btn-success" style="width: 70px;" id="query"  value="保 存"  onclick="checkData();"/>
												<input type="button" class="btn btn-success" style="width: 70px;" id="query"  value="返 回"  onclick="pageBack();"/>
									</div>
								</form>
								<form action="${ctx }/schoolCourse/SchoolCourseAction.a" method="post" id="form2">
										<input id="year1" value="${year }" name="year" type="hidden"/>
										<input id="segmentId1" value="${segmentId }" name="segmentId" type="hidden"/>
										<input id="subjectId1" value="${subjectId }" name="subjectId" type="hidden"/>
										<input id="isHired1" value="${isHired }" name="isHired" type="hidden"/>
										<input id="courseName1" value="${courseName }" name="courseName" type="hidden"/>
										<input id="guidTeacherName1" value="${guidTeacherName }" name="guidTeacherName" type="hidden"/>
										<input id="isSearch" name="isSearch" type="hidden" value="${isSearch}"/>
								</form>
							</div>
						</div>						
		        </div>
		 </div>
		 	<%@ include file="/common/div.jsp"%>
</body>
</html>