<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<un:bind var="KG_COURSE_KIND" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_KIND" />
<un:bind var="KG_COURSE_NEIZHI" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_NEIZHI" />
<un:bind var="USER_MASTERROLE_TYPESTR"
	type="com.flyrish.hades.common.Constant"
	field="USER_MASTERROLE_TYPESTR" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/unicorn.main.css" />

<script type="text/javascript"
	src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css_new/jquery.gritter.css" />
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>


<title>高中报告册</title>
<style>
.loading {
	line-height: 0px;
}

.li {
	margin-left: 10%;
}

li {
	float: left;
}

ul {
	list-style: none;
}

.dslcheckbox {
	width: auto;
	height: auto;
}
</style>
<script>
function dianji(){
	//document.getElementById("MyDiv").style.visibility="visible";//显示
	/*document.getElementById("MyDiv").style.display=="block";//显示 */
	//ShowDiv(); 
	document.getElementById("biaodan").submit();
}
function check(type) {
	var ids = document.getElementsByName("classs");
	var flag = false;
	for (var i = 0; i < ids.length; i++) {
		if (ids[i].checked) {
			flag = true;
			break;
		}
	}
	if (!flag) {
		alert("请最少选择一项！");
		return false;
	} else {
		$("#exportExcel").submit();
		}
	}
</script>

</head>
<body style="background: #EEE">
	<div style="width: 100%;">
		<div style="width: 100%; margin-top: 13px">
			<div class="widget-box"
				style="margin-top: 0px; margin-bottom: 0px; margin-left: 13px; margin-right: 13px">
				<div class="widget-title"
					style="background: #279F46; text-align: center; font-size: 12px; color: #FFF; text-shadow: 0px 0px 0px #FFF; line-height: 36px; margin: 0px;">学校列表</div>
				<div class="widget-content nopadding">
					<table class="table table-bordered table-striped with-check">
						<thead>
							<tr>
								<th width="4%" style="font-size: 14px">选择</th>
								<th width="6%" style="font-size: 14px">序号</th>
								<th style="font-size: 14px">学校代码</th>
								<th style="font-size: 14px">学校名称</th>
								<th style="font-size: 14px">届别</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${schoolList}" var="sch" varStatus="status">
								<tr>
									<td><input type="radio" name="radio" /></td>
									<td style="text-align: center;">${status.index + 1}</td>
									<td style="text-align: center;">${sch.schoolcode }</td>
									<td style="text-align: center;">${sch.schoolname }</td>
									<td style="text-align: center;">${sch.jiebie }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="form-actions pagination alternate"
						style="margin-top: 0px; margin-bottom: 0px;">

						<span
							style="margin-right: 30px; float: right; margin-top: line-height: 36;">

							<button onClick="check(3);" style="width: 100px;"
								class="btn btn-success">列表导出</button>
							<button
								onClick="window.location.href=${ctx}/masterReport/ReportAction.a?toDetailsReportPage"
								style="width: 100px;" class="btn btn-success">返回</button>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="exportExcel" action="${ctx}/bookreport/QuBookreportAction.a?exportExcelWSB" target="downloadFrame">
		<input type="hidden" name="discode" value="${discode}"/>
	</form>
	<iframe id="downloadFrame" name="downloadFrame" style="display: none;"></iframe>
	<%@ include file="/common/exporting.jsp"%>
</body>
</html>