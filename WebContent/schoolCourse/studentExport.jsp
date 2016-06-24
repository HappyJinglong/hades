<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>导入录取学生</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
 </style>
 <script>
 function backPage(){
	 $("#form1").submit();
 }
 var importFlag = 0;
function checkFile(){
	$("#timeCount").html("文件保存中...");
	ShowDiv();
	if(importFlag==1){
		$("#MyDiv").css("display","none");
		alert_KG_chooseFile_tip("数据导入中...");
		return false;
	}
	var filename = $("#importFile").val();
	if(!filename){
		$("#MyDiv").css("display","none");
		alert_KG_chooseFile_tip("请选择上传文件！");
		return false;
	}else if(!/\.(xls|xlsx)$/.test(filename)){
		$("#MyDiv").css("display","none");
		alert_KG_chooseFile_tip("请选择excel文件!");
		return false;
	}
	importFlag = 1;
	$("#parsing").css("display","");
	return true;
}
function tip(){
	if('${errorMessages}'){
		alert_KG_importData_failure();
	}
}
 </script>
</head>
<body style="background: #EEE" onload="tip();">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">导入录取学生</a>
		</div>
		<form action="${ctx }/schoolCourse/CouresHireStudentAction.a" method="post"  id="form2" enctype="multipart/form-data">
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px">
							<div style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
                                  导入录取学生
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr>
											<td style="text-align: right;" width="40%">请选择导入文件：</td>
											<td style="text-align: left;" width="60%">
											<input type="file" name="importFile" id="importFile" value="浏览"/> 
											<span><a href="${ctx }/schoolCourse/CouresHireStudentAction.a?exportStudentTemplate">下载校本课程录取学生模板</a></span>
												&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="parsing" style="display:none;"><img src="${ctx}/images/progress.gif" style="height:20px"/>&nbsp;&nbsp;数据导入中，请耐心等待...</span>
											</td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
							<div class="pagination alternate"  style="border: 1px solid #CDCDCD;margin: 0px 13px;height: 70px;line-height: 40px" >
							<span style="margin-right: 30px;float:right; line-height: 5;">
								<input  type="submit" class="btn btn-success" style="width: 70px;" id="query" name="importExcelAndPaser" value="确 定" onclick="return checkFile();"/>
								<input type="button" class="btn btn-success" style="width: 70px;" id="query" onclick="backPage();" value="返 回" />
						     </span>
						</div>
		</div>
		</form>
								<div class="widget-box" 
						style="margin-top:-150px;margin-bottom:0px;margin-left:13px;margin-right:13px;
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
	<%@ include file="/common/div.jsp"%>
</body>
</html>