<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
 <link type="text/css" rel="stylesheet" href="${ctx}/css_new/jquery.gritter.css" />
  <script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
  <script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>课程模块设置</title>
<style >
	.loading{
		line-height:0px;
	}
	.li{
		margin-left: 10%;
	}
	li{
	 float: left;
	}
	ul{
		list-style:none;
	}
	select{
		height: 25px;
		width: 60px	
	}
	
</style>
	
<script>
function queryDatas(){
	if('${flag}'==1){
		ShowDiv();
		$("#timeCount").html("数据加载中...");
		$("body").append('<form action="${ctx}/masterReport/ReportAction.a" method="post"></form>');
		$("form").submit();
	}
}
function toSchoolList(url,param){
	ShowDiv();
	$("#timeCount").html("数据加载中...");
	if($("form").length>0){
		$("form").remove();
	}
	var form='<form action="'+url+'" method="post"><input type="hidden" name="discode" value="'+param+'"/></form>';
	$("body").append(form);
	$("form").submit();
}
</script>
</head>
<body style="background: #EEE" onload="queryDatas();">
	<div style="width: 100%;">
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								区县列表
							</div>
							<div class="widget-content nopadding">
								<table  width="100%" class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th  style="font-size: 14px">区县</th>
											<th width="10%" style="font-size: 13px">学校总数</th>
											<th width="10%" style="font-size: 13px">已上报学校数</th>
											<th width="10%" style="font-size: 13px">未上报学校数</th>
											<th width="10%" style="font-size: 13px">已上报人数</th>
											<th  width="10%" style="font-size: 13px">校验正确人数</th>
											<th width="10%" style="font-size: 13px">校验错误人数</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${viewDatas}" var="countData">
										<tr >
											<td style="text-align: center;">${countData.name}</td>
											<td style="text-align: center;">${countData.schoolTotalCount}</td>
											<td style="text-align: center;">
											<c:choose>
												<c:when test="${countData.reportSchoolCount ne 0}">
													<a href="#" onclick="toSchoolList('${ctx}/bookreport/QuBookreportAction.a?getYSBschool','${countData.discode}');">${countData.reportSchoolCount}</a>
												</c:when>
												<c:otherwise>
													${countData.reportSchoolCount}
												</c:otherwise>
											</c:choose>
											</td>
											<td style="text-align: center;">
											<%-- <a href="baogaoce3.html">${countData.noReportSchoolCount}</a> --%>
											<c:choose>
												<c:when test="${countData.noReportSchoolCount ne 0}">
													<a href="#" onclick="toSchoolList('${ctx}/bookreport/QuBookreportAction.a?getNoschool','${countData.discode}');">${countData.noReportSchoolCount}</a>
												</c:when>
												<c:otherwise>
													${countData.noReportSchoolCount}
												</c:otherwise>
											</c:choose>
											</td>
											<td style="text-align: center;">${countData.reportStudentCount}</td>
											<td style="text-align: center;">${countData.checkStudentCount}</td>
											<td style="text-align: center;">${countData.errorCheckStudentCount}</td>
										</tr>
									</c:forEach>
										<tr>
									      <td style="text-align: center;">合计</td>
									      <td style="text-align: center;">${totalSchoolCount}</td>
									      <td style="text-align: center;">${totalReportSchoolCount }</td>
									      <td style="text-align: center;">${totalNoReportSchoolCount }</td>
										  <td style="text-align: center;">${totalReportStudentCount }</td>
										  <td style="text-align: center;">${totalCheckStudentCount }</td>
										  <td style="text-align: center;">${totalErrorCheckStudentCount }</td>
										 </tr>
									</tbody>
								</table>	
												
							</div>
						</div>
		</div>
	</div>
	<%@ include file="/common/exporting.jsp"%>
</body>
</html>