<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SegmentSelectDwr.js'></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<title>校本课程设置</title>
<style >
	.li{
		margin-left: 3%;
	}
	li{
	 float: left;
	}
	ul{
		list-style:none;
	}
	select{
			height: 30px;
			width: 90px	
	}
	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	 .spnContent { 
	        vertical-align: middle;   
            margin-left: 10px;  
            color: white;  
            font-size: 18px;  
            font-weight: bold;  
} 
td{
	word-wrap:break-word;
	text-align:center;
}
</style>
<script>
var $$=jQuery.noConflict();
	function tip(){
		if('${successFlag}'=="OK"){
			alert_KG_importData_success();
		}else if('${successFlag}'=="delete"){
			alert_delete_success("");
		}else if('${successFlag}'=="save"){
			alert_KG_save_success();
		}
	}
	function queryCourse(){
		ShowDiv();
		$$("#isSearch").val("0");
		$$("[name='pageNo']").val("1");
		$$("form").submit();
	}

	function add(){
		
		$$("form").attr("action","${ctx }/schoolCourse/SchoolCourseAction.a?toAddPage");
		$$("form").submit();
	}
	//删除
 	var isDelete=0;
	function deleteCourse(){
		//是否有选中的
 		var val = "";
		$$("[name='radio']").each(function(){
			if($$(this).attr("checked")){
				val = $$(this).val();
			}
		});
 		if(isDelete==1){
			alert_KG_self_tip("正在删除中...");
		}else{ 
			if(val!=""){
				if(!confirm("您确定要删除该条记录？")){
					return;
				}
				isDelete=1;
 				Ext.Ajax.request({
					url:'${ctx }/schoolCourse/SchoolCourseAction.a?toDeleteCourse',
					method:'POST',
					success:function(response,options){
						var temp=response.responseText;
						isDelete=0;
						if(temp=="false"){
							alert_delete_failure("");
							return;
						}
						if(temp=="exist"){
							/* alert("该模块学生成绩已经录入，不能删除！"); */
							alert_KG_self_tip("该记录学生成绩已经录入，不能删除！");
							return;
						}
						var str = '<input id="successFlag" name="successFlag" type="hidden" value="delete"/>';
						$$("form").append(str);
						$$("form").submit();
					},
					params : {
						courseid:val.split("_")[0],
						segCourseId:val.split("_")[1],
						teacherId:val.split("_")[2],
						oldTeacherIdInfo:val.split("_")[3]
					}
				});  
			}else{
				/* alert("您没有选择需要删除的模块！"); */
				alert_KG_self_tip("请选择需要删除的信息！");
			}
		 }  
	} 
	//修改
	function upadate(){
		//是否有选中的
		var val = "";
		$$("[name='radio']").each(function(){
			if($$(this).attr("checked")){
				val = $$(this).val().split("_")[0];
			}
		});
		if(val!=""){
			$$("form").attr("action","${ctx }/schoolCourse/SchoolCourseAction.a?toCourseDetails");
			var str = '<input id="courseid" name="courseid" type="hidden" value="'+val+'"/><input id="update" name="isUpdate" type="hidden" value="is"/>';
			$$("form").append(str);
			$$("form").submit();
		}else{
			/* alert("您没有选择需要修改的模块！"); */
			alert_KG_self_tip("请选择需要修改的信息！");
		}
	}
	function courseExport(){
		
		$$("form").attr("action","${ctx }/schoolCourse/ImportExcelAction.a?toExportPage");
		$$("form").submit();
		//window.location.href="${ctx }/schoolCourse/courseExport.jsp";
	}
	function studentExport(){
		
		$$("form").attr("action","${ctx }/schoolCourse/CouresHireStudentAction.a?toExportStudentPage");
		$$("form").submit();
		//window.location.href="${ctx }/schoolCourse/studentExport.jsp";
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
					DWRUtil.addOptions("segmentId",{"":"全部"});
					DWRUtil.addOptions("segmentId",obj ,"segment_id" ,"segment_name" );
					/* DWRUtil.setValue("segmentId", '${segmentId}'); */
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("segmentId");
			DWRUtil.addOptions("segmentId", {"":"全部"});
		}
		changeSegmentToSubject('');
		dwr.engine.setAsync(true);
	}
	//修改学年改变学段
	function changYearToSegment1(year){ //v3.0
		if(year =='' || null!=year){
			year=$$("#year").val();
		}
		dwr.engine.setAsync(false);
		if(null!=year&&''!=year)
		{
			SegmentSelectDwr.changeYearToSegment(year,'${cmis30id}',function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("segmentId");
					DWRUtil.addOptions("segmentId",{"":"全部"});
					DWRUtil.addOptions("segmentId",obj ,"segment_id" ,"segment_name" );
					DWRUtil.setValue("segmentId", '${segmentId}');
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("segmentId");
			DWRUtil.addOptions("segmentId", {"":"全部"});
		}
		changeSegmentToSubject('');
		dwr.engine.setAsync(true);
	}
	function toDetails(val){
		$$("form").attr("action","${ctx}/schoolCourse/SchoolCourseAction.a?toCourseDetails");
		var str = '<input id="courseid" name="courseid" type="hidden" value="'+val+'"/>';
		$$("form").append(str);
		$$("form").submit();
	}
	function toStudentDetails(segCourseId,schoolYear,courseName){
		$$("[name='pageNo']").val("1");
		$$("form").attr("action","${ctx}/schoolCourse/CouresHireStudentAction.a?toStudentDetails");
		var str = '<input id="segCourseId" name="segCourseId" type="hidden" value="'+segCourseId+'"/>'
				 +'<input id="schoolYear" name="isLockButton" type="hidden" value="'+schoolYear+'"/>'
				 +'<input id="courseInfo" name="courseInfo" type="hidden" value="'+courseName+'"/>';
		$$("form").append(str);
		$$("form").submit();
	}
	//修改学段改变学科
	function changeSegmentToSubject(segmentId){ //v3.0
		if(segmentId =='' || null!=segmentId){
			segmentId=$$("#segmentId").val();
		}
		dwr.engine.setAsync(false);
		if(null!=segmentId&&''!=segmentId)
		{
			SegmentSelectDwr.changeSegmentToSubject(segmentId,'${cmis30id}',function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("subjectId");
					DWRUtil.addOptions("subjectId",{"":"全部"});
					DWRUtil.addOptions("subjectId",obj ,"subject_id" ,"subject_name" );
					/* DWRUtil.setValue("subjectId", '${subjectId}'); */
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("subjectId");
			DWRUtil.addOptions("subjectId", {"":"全部"});
		}
		dwr.engine.setAsync(true);
	}
	function changeSegmentToSubject1(segmentId){ //v3.0
		if(segmentId =='' || null!=segmentId){
			segmentId=$$("#segmentId").val();
		}
		dwr.engine.setAsync(false);
		if(null!=segmentId&&''!=segmentId)
		{
			SegmentSelectDwr.changeSegmentToSubject(segmentId,'${cmis30id}',function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("subjectId");
					DWRUtil.addOptions("subjectId",{"":"全部"});
					DWRUtil.addOptions("subjectId",obj ,"subject_id" ,"subject_name" );
					DWRUtil.setValue("subjectId", '${subjectId}');
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("subjectId");
			DWRUtil.addOptions("subjectId", {"":"全部"});
		}
		dwr.engine.setAsync(true);
	}
	$$(document).ready(function(){
		tip();
		$$("#isHired").val("${isHired}");
		<c:if test="${not empty pageObj}">
			<c:if test="${not empty pageObj.pageElements}">
				$$("#button").css("margin-top","-35px");
			</c:if>
		</c:if>
	});
</script>
</head>
<body style="background: #EEE" onload="changYearToSegment1('');changeSegmentToSubject1('');">
	<div style="width: 100%;margin-bottom: 30px">
		<form action="${ctx }/schoolCourse/SchoolCourseAction.a" method="post">
		<div style="width: 100%;position: fixed;top: 0px;z-index: 100;background: #EEE">
			<div id="breadcrumb" style="margin-top: 0px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 校本课程</a>
			</div>
			<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 50px;top: 0px">
							
				 <ul >
				 	<li style="margin-left: 5%">
				 	   学年：
							<select name="year" id="year" onchange="changYearToSegment(this.value)">
								<option value="">全部</option>
	               				<app:courseYear selectNum="${year}"/>
	              			</select>
				 	</li>
				 	<li class="li">
				 	   学段：
				 		<select id="segmentId" name="segmentId" onchange="changeSegmentToSubject(this.value);">
				 			 <option value="">全部</option>
				 		</select>
				 	</li>
				 	<li class="li">
				 	   学科：
				 		<select name="subjectId" id="subjectId" >
								<option value="">全部</option>
	              			</select>
				 	</li>
				 	<li class="li">
				 	  是否已录取学生：
				 		<select id="isHired" name="isHired" >
				 			<option value="">全部</option>
				 			<option value="1">是</option>
				 			<option value="0">否</option>
				 		</select>
				 	</li>
				 	<li class="li">
				 	     模块名称：
					 	  <input type="text"  style="height: 15px;width: 90px;border-left: 1px solid;border-top: 1px solid;" name="courseName" id="courseName" value="${courseName }"/>    
				 	</li>
				 	<li class="li">
				 	     指导教师：
					 	  <input type="text"  style="height: 15px;width: 90px;border-left: 1px solid;border-top: 1px solid;" name="guidTeacherName" id="guidTeacherName" value="${guidTeacherName }"/>    
				 	</li>
				 	 <li class="li"><span>&nbsp;
				 	 	</span>
				  		<input type="button" onclick="queryCourse();" class="btn btn-success" id="query"  value="查 询"  style="width: 70px;margin-bottom: 9px" />
					 </li>
				 </ul>
			</div>
			<div style="margin-top: 13px;margin-left: 13px;margin-right: 13px">
				<div  style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								模块列表
				</div>
				<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="4%" style="font-size: 14px" ><i class="icon-resize-vertical"></i></th>
											<th width="5%" style="font-size: 13px">序号</th>
											<th width="7%" style="font-size: 13px">学年</th>
											<th width="7%" style="font-size: 13px">学段</th>
											<th width="7%" style="font-size: 13px">学科</th>
											<th width="9%" style="font-size: 13px">模块编号</th>
											<th width="15%"  style="font-size: 13px">模块名称</th>
											<th  width="7%" style="font-size: 13px">学习方向</th>
											<th width="13%" style="font-size: 13px">系列</th>
											<th  width="6%" style="font-size: 13px">学时</th>
											<th  width="6%" style="font-size: 13px">学分</th>
											<th  width="7%" style="font-size: 13px">指导教师</th>
											<th  width="7%" style="font-size: 13px">录取学生</th>
										</tr>
									</thead>
								</table>
				</div>
			</div>
		</div>
		<div style="width: 100%;margin-top: 165px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody><!-- style="table-layout:fixed;" -->
										<c:if test="${not empty pageObj.pageElements}">
     										<c:forEach items="${pageObj.pageElements}" var="roll" varStatus="status" >
												<tr >
													<td    style="text-align: center;vertical-align: middle;" width="4%"><input type="radio" name="radio" id="radio" value="${roll.course_id}_${roll.segmentCourseId}_${roll.teacherId}_${roll.eduId}"/></td>
													<td    style="text-align: center;vertical-align: middle;" width="5%">${status.count + pageObj.pageSize * (pageObj.currPageNumber-1)}</td>
													<td    style="text-align: center;vertical-align: middle;"  width="7%">${roll.schoolyearname}</td>
													<td    style="text-align: center;vertical-align: middle;"  width="7%">${roll.segmentName}</td>
													<td    style="text-align: center;vertical-align: middle;"  width="7%">${roll.subject_name}</td>
													<td    style="text-align: center;vertical-align: middle;" width="9%">${fn:replace(roll.course_code," ","&nbsp;")}</td>
													<td    style="text-align: center;vertical-align: middle;" width="15%"><a href="#" onclick="toDetails('${roll.course_id}_${roll.segmentCourseId}')" title="${roll.course_name}">
													<!-- 超出10个字 省略  -->
														<c:choose>
																<c:when test="${fn:length(roll.course_name) > 10}">
																	<c:out
																		value="${fn:substring(roll.course_name, 0, 10)}..." />
																</c:when>
																<c:otherwise>
																	${fn:replace(roll.course_name," ","&nbsp;")}
																</c:otherwise>
														</c:choose>
													</a></td>
													<td   style="text-align: center;vertical-align: middle;" width="7%">${roll.student_aspectname}</td>
													<td    style="text-align: center;vertical-align: middle;" width="13%" >${roll.series_name}</td>
													<td    style="text-align: center;vertical-align: middle;"  width="6%">${roll.period_count}</td>
													<td    style="text-align: center;vertical-align: middle;"  width="6%">${roll.credit_hour}</td>
													<td    style="text-align: center;vertical-align: middle;" width="7%">${roll.teacherName}</td>
													<td    style="text-align: center;vertical-align: middle;" width="7%"><a href="#" onclick="toStudentDetails('${roll.segmentCourseId}','${roll.schoolyear}','${roll.course_name}')">查看</a></td>
												</tr>
										 	</c:forEach>
     								</c:if>	
									</tbody> 
								</table>	
								<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
								<div id="pagearea">
									<span><input name="pageNo" value="${pageNo }" type="hidden" />
									<c:if test="${not empty pageObj}">
											<c:if test="${not empty pageObj.pageElements}">
												<jsp:include page="../common/pager-nest.jsp">
													<jsp:param name="toPage" value="1" />
													<jsp:param name="showCount" value="1" />
													<jsp:param name="action" value="toCourseList" />
												</jsp:include>
											</c:if>
										</c:if></span>
								</div>
									<span style="margin-right: 5px;float:right;margin-top:5px;" id="button">
										<input onclick="courseExport();" type="button" class="btn btn-success" style="width: 110px;" id="query"  value="导入校本课程" />
										<input onclick="studentExport();" type="button" class="btn btn-success" style="width: 110px;" id="query"  value="导入录取学生" />
										<input onclick="add();" type="button" class="btn btn-success" style="width: 70px;" id="query"  value="增 加" />
										<input type="button" class="btn btn-success" style="width: 70px;" id="query"  value="修 改"  onclick="upadate();"/>
										<input type="button" class="btn btn-success" style="width: 70px;" id="query"  value="删 除"  onclick="deleteCourse();"/>
								     </span>
								</div>								
							</div>
						</div>
			</div>
			<input id="isSearch" name="isSearch" type="hidden" value="${isSearch}"/>
		</form>
	</div>
	<%@ include file="/common/div.jsp"%>
</body>
</html>