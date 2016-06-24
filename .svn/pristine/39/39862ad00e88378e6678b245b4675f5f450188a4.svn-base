<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script src="${ctx}/js/ajaxfileupload.js" type="text/javascript"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SegmentSelectDwr.js'></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<title>校本课程导入</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
 </style>
 <script type="text/javascript">
 var $$=jQuery.noConflict();
 function exportTemplate(year){
	 window.location.href="${ctx }/schoolCourse/ImportExcelAction.a?exportTemplate&segmentId="+$$("#segmentId option:selected").html()+"&isLockButton="+year;
 }
 function toBackPage(){
	 
	 $$("#form1").submit();
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
					DWRUtil.addOptions("segmentId",obj ,"segment_id" ,"segment_name" );
					if('${errorMessages}'){
						DWRUtil.setValue("segmentId", '${segmentId}');
					}
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("segmentId");
			DWRUtil.addOptions("segmentId", {"":"请选择"});
		}
		dwr.engine.setAsync(true);
	}
	function tip(){
		if('${errorMessages}'){
			alert_KG_importData_failure();
		}
	}
	var importFlag = 0;
	function checkFile(){
		$$("#timeCount").html("文件导入中...");
		 ShowDiv();
		if(importFlag==1){
			$$("#MyDiv").css("display","none");
			alert_KG_chooseFile_tip("数据导入中...");
			return false;
		}
		var filename = $$("#importFile").val();
		if(!filename){
			$$("#MyDiv").css("display","none");
			alert_KG_chooseFile_tip("请选择上传文件！");
			return false;
		}else if(!/\.(xls|xlsx)$/.test(filename)){
			$$("#MyDiv").css("display","none");
			alert_KG_chooseFile_tip("请选择excel文件!");
			return false;
		}
		importFlag = 1;
		$$("#parsing").css("display","");
		return true;
	}
 </script>
</head>

<body style="background: #EEE" onload="tip();changYearToSegment('');">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 13px;color: #333">校本课程导入</a>
		</div>
		<form action="${ctx }/schoolCourse/ImportExcelAction.a" method="post" id="form2" enctype="multipart/form-data">
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px">
							<div  style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
                                  校本课程导入
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr >
											<td width="40%"  style="text-align: right;">学年：</td>
											<td width="60%"   style="text-align: left;">
												<select name="year" id="year" onchange="changYearToSegment(this.value)">
													<app:courseYear selectNum="${isLockButton }"/>
												</select>
											</td>
											
										</tr>
										<tr>
											<td style="text-align: right;">学段：</td>
											<td style="text-align: left;">
												<select  id="segmentId" name="segmentId" >
												</select>
												<span><a href="#" onclick="exportTemplate('${isLockButton}')">下载校本课程导入模板</a></span>
											</td>
										</tr>
										<tr>
											<td style="text-align: right;">请选择导入文件：</td>
											<td style="text-align: left;">
												<input type="file"  id="importFile" name="importFile" value="浏览"/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<!--  <span id="parsing" style="display:none;"><img src="${ctx}/images/progress.gif" style="height:20px"/>&nbsp;&nbsp;数据导入中，请耐心等待...</span>-->
											</td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
						<div class="pagination alternate"  style="border: 1px solid #CDCDCD;margin: 0px 13px;height: 70px;line-height: 40px" >
							<span style="margin-right: 30px;float:right; line-height: 5;">
								<input  type="submit" class="btn btn-success" style="width: 70px;" id="query"  value="确 定"  name="importExcelAndPaser" onclick="return checkFile();"/>
								<input type="button" class="btn btn-success" style="width: 70px;" id="query"  value="返 回"  onclick="toBackPage();"/>
						     </span>
						</div>
		</div>
			<input id="year" value="${year }" name="year" type="hidden"/>
			<input id="segmentId" value="${segmentId }" name="segmentId" type="hidden"/>
			<input id="subjectId" value="${subjectId }" name="subjectId" type="hidden"/>
			<input id="isHired" value="${isHired }" name="isHired" type="hidden"/>
			<input id="courseName" value="${courseName }" name="courseName" type="hidden"/>
			<input id="guidTeacherName" value="${guidTeacherName }" name="guidTeacherName" type="hidden"/>
		</form>
			
						<div class="widget-box" 
						style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px;
						<c:if test="${empty errorMessages }">
							display:none
						</c:if>
						">
							<div style="background: red;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
                                 导入错误内容
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr >
											<td width="10%"  style="text-align: center;">序号</td>
											<td width="90%"   style="text-align: center;">
											错误信息
											</td>
										</tr>
										<c:forEach items="${errorMessages }" var="msgs" varStatus="vs">
											<tr>
												<td style="text-align: center;">	${vs.count }		</td>
												<td style="text-align: left;">		第${msgs.rownum }行：${msgs.errorInfo }	</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>							
							</div>
						</div>
		
		
		<form action="${ctx }/schoolCourse/SchoolCourseAction.a"  id="form1" method="post">
			<input id="year" value="${year }" name="year" type="hidden"/>
			<input id="segmentId" value="${segmentId }" name="segmentId" type="hidden"/>
			<input id="subjectId" value="${subjectId }" name="subjectId" type="hidden"/>
			<input id="isHired" value="${isHired }" name="isHired" type="hidden"/>
			<input id="courseName" value="${courseName }" name="courseName" type="hidden"/>
			<input id="guidTeacherName" value="${guidTeacherName }" name="guidTeacherName" type="hidden"/>
			<input id="isSearch" name="isSearch" type="hidden" value="${isSearch}"/>
		</form>
			
	</div>
<%@ include file="/common/div.jsp"%>

</body>
</html>