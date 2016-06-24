<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
 <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
 <link type="text/css" rel="stylesheet" href="${ctx}/css_new/jquery.gritter.css" />
  <script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
  <script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>会考成绩导入</title>
<title>会考成绩导入</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	element .style{
	  height:40px;
	}
	.loading{
		line-height:0px;
	}
 </style>
</head>
<script type="text/javascript">
function checkForm(){
	$("#timeCount").html("数据正在导入中...");
	if(!$("#fileItem").val()){
		alert("请选择导入文件!");
    	return false;
	}
	if ($("#fileItem").val().indexOf('.zip',$("#fileItem").val().length-4) < 0){
	   	alert("导入的数据文件必须是一个zip格式的文件!");
    	return false;
  	}
  	var con = confirm("确定导入吗?");  
  	if(!con){
  		return false;
  	}
  	ShowDiv();
  	$("#parsing").css("display","");
}
function toBack(){
	 window.location.href="${ctx}/reportBook/ReportBookAction.a?toTreeMenu&&falg=1&&gradeId=${gradeId}&&classId=${classId}";
	 return false;
}
</script>
<body style="background: #EEE">
<form name="formName" enctype="multipart/form-data" method="post" action="${ctx }/masterReport/ReportImportAction.a">
	<input type="hidden" name="classIds" value="${classIds}"/>
	<input type="hidden" name="classId" value="${classId}"/>
	<input type="hidden" name="gradeId" value="${gradeId}"/>
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>报告册导入</a>
		</div>
		<div style="width: 96%;margin-top: 13px;margin-left:2%">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;">
							<div class="widget-title" style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
                                 报告册导入
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
								  <tr>
								    <td style="text-align: right;width:20%">请选择导入文件：</td>
								     <td style="text-align: left;" ><input id="fileItem" name="fileItem" type="file"  value="浏览"/>
								    <%--  &nbsp;&nbsp;&nbsp;&nbsp;<span id="parsing" style="display:none;"><img src="${ctx}/images/progress.gif" style="height:20px"/>&nbsp;&nbsp;数据导入中，请耐心等待...</span> --%></td>
							      </tr>
									<tbody>
										<tr>
											<td width="20%" style="text-align: right;">导入说明：</td>
											<td width="80%" style="text-align: left;" >1、请使用指定的模板文件填写评价信息。 <br>
											 2、一次只能导入一个报告册文件，且必须是模板文件压缩为指定ZIP格式的文件，可多个模板文件压缩为指定ZIP格
											  
					式的文件。 <br>
										    3、班主任用户可以通过“报告册模板下载”获得报告册信息，将报告册信息复制到模板中相应位置即可。</td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
						<div class="alternate"  style="border: 1px solid #CDCDCD;height: 40px;line-height: 40px" >
							<span style="margin-right: 30px;float:right;margin-top:  line-height: 40;">
								<!-- <input id="doImportData"
									 name="doImportData" 
									 type="submit" 
									 class="input_btn" 
									 style="width: 70px;" 
									 onclick="return checkForm();"
									 value="导  入" /> -->
									 <button onClick="return checkForm();" id="import" name="doImportData"  type="submit"  style="width: 110px;"class="btn btn-success">导  入</button>
								     <input type="button" onclick="return toBack();" class="btn btn-success" style="width: 110px;" id="query"  value="返 回" /> 
						      <!-- <input onClick="toBack();" style="width: 90px;"  value="返 回"></input> -->
						     </span>
						</div>
		</div>
						<div align="center" id="status"><c:if test="${not empty messageData}">
	<span style="color: blue">${messageData}</span>
	</c:if><c:if test="${not empty errorMessageData}">
		<span style="color: red">${errorMessageData}</span>
	</c:if>
</div>
	</div>
	
</form>
<%@ include file="/common/exporting.jsp"%>
</body>
</html>