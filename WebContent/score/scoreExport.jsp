<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>成绩导入</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
 </style>
 <script>
 	function tip(){
 		if('${successFlag}'=="OK"){
			alert_KG_importData_success();
		}
 		
 		if('${errorMessages}'){
 			alert_KG_importData_failure();
 		}
 	}
 	
 	function checkFile(){
 		var filename = $("#importFile").val();
 		if(!filename){
 			alert_KG_chooseFile_tip("请选择上传文件！");
 			return false;
 		}else if(!/\.(xls|xlsx)$/.test(filename)){
 			alert_KG_chooseFile_tip("请选择excel文件!");
 			return false;
 		}
 		$("#parsing").css("display","");
 		return true;
 	}
 	function toBackPage(){
 		var courseType = "${courseType}";
 		var gradeName ="${gradeName1}";
 		var className = "${className1}";
 		var year = "${schoolyear}";
 		var segmentId = "${segmentId}";
 		var subjectId = "${subjectId}";
 		var courseId = "${courseId}";
 		var tableTitle2= "${tableTitle2}";
 		window.location.href="${ctx}/score/ScoreAction.a?courseType="+courseType+"&&gradeName1="+gradeName+"&&className1="
		+className+"&&schoolyear="+year+"&&segmentId="+segmentId+"&&subjectId="+subjectId+"&&courseId="+courseId+"&&isToBake="+1+"&&tableTitle2="+encodeURI(encodeURI(tableTitle2));
 	}
 
 </script>
</head>
<body style="background: #EEE" onload="tip();">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">成绩导入</a>
		</div>
		
		<form action="${ctx }/score/ImportScoreAction.a" method="post"  id="form2" enctype="multipart/form-data">
		<input type="hidden"  value="${courseType}"  id="courseType" name="courseType" />
		<input type="hidden"  value="${gradeName1}"  id="gradeName1" name="gradeName1" />
		<input type="hidden"  value="${className1}"  id="className1" name="className1" />
		<input type="hidden"  value="${schoolyear}"  id="schoolyear" name="schoolyear" />
		<input type="hidden"  value="${segmentId}"  id="segmentId" name="segmentId" />
		<input type="hidden"  value="${subjectId}"  id="subjectId" name="subjectId" />
		<input type="hidden"  value="${courseId}"  id="courseId" name="courseId" />
		<input type="hidden"  value="${tableTitle2}"  id="tableTitle2" name="tableTitle2" />
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px">
							<div  style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
                                  成绩导入
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr>
											<td style="text-align: right;" width="40%">请选择导入文件：</td>
											<td style="text-align: left;" width="60%">
											     <input type="file"  id="importFile"  name="importFile"  value="浏览"/> 
											     <span><a href="${ctx }/score/XZ.a?studentfilePath=mkScorefilePath">下载模块成绩模板</a></span>
											 		&nbsp;&nbsp;&nbsp;&nbsp;
												<span id="parsing" style="display:none;"><img src="${ctx}/images/progress.gif" style="height:20px"/>&nbsp;&nbsp;数据导入中，请耐心等待...</span>
											
											 </td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
						<div class="pagination alternate"  style="border: 1px solid #CDCDCD;margin: 0px 13px;height: 40px;line-height: 40px" >
							<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
								<input  type="submit" class="bt" style="width: 70px;" id="query"  value="确定"  name="importExcelAndPaser"   onclick="return checkFile();"/>
								<input type="button" class="bt" style="width: 70px;" id="query"  value="返回"  onclick="toBackPage();"/>
						     </span>
						</div>
		</div>
		</form>
		
		<div  class="widget-box" 
						style="margin-top:0px;margin-left:13px;margin-right:13px;
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
	

</body>
</html>