<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
#pj_ziwo_main {
	margin: 0;
	padding: 0;
	border-radius: none;
	width: auto;
}
</style>
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type='text/javascript'
	src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<style type="text/css">
td {
	border: solid 1px #999;
}

td.th-hide {
	overflow: hidden;
}

#pj_ziwo_main .biaoge tr.hide {
	height: 0;
	line-height: 0;
}

.dangqianwz {
	width: auto;
	border-radius: none;
	position: static;
}

#pj_ziwo_main {
	position: fixed;
	/* height: 657px; */
	left: 0px;
	right: 0px;
	top: 38px;
	bottom: 20px;
	padding: 10px 10px 30px 10px;
	overflow: auto;
}

#pj_ziwo_main .top {
	position: absolute;
	bottom: 0;
	top: 0;
	left: 10px;
	right: 10px;
	width: auto;
}
</style>

<title>自我评价</title>

<script type="text/javascript">

	function exportExcel() {
		var grade = $("#gradeid option:selected").text();
		var clazz = $("#classid option:selected").text();
		var term = $("#termid option:selected").text();
		
		var gradeid = $("#gradeid option:selected").val();
		var classid = $("#classid option:selected").val();
		var termid = $("#termid option:selected").val();
		if(gradeid==''||!classid||classid==''||!termid||termid=='')
		{
			if (!gradeid||gradeid == '') {
				alert("请选择届别!");
				return;
			}
			if (!classid||classid == '') {
				alert("请选择班级!");
				return;
			}
			if (!termid||termid == '') {
				alert("请选择学期!");
				return;
			}
		}
		dStatus = uuid();
	    window.location.href = '${ctx}/monitorstatictics/monitorStaticticsAction.a?exportExcel&gradeName='
				+ grade
				+ "&dStatus=" 
				+ dStatus
				+ "&className="
				+ clazz
				+ "&termName="
				+ term
				+ "&gradeid="
				+ gradeid
				+ "&classid="
				+ classid
				+ "&termid="
				+ termid;
		ShowDiv(); 
		timeId = setInterval(queryDownStatus,1000);
	}
	
	function getData() {
		if (checkData()) {
			var clazz = $("#classid option:selected").text();
			$("#className").val(clazz);
			var grade = $("#gradeid option:selected").text();
			$("#gradeName").val(grade);
			var term = $("#termid option:selected").text();
			$("#termName").val(term);
			ShowDiv();
			$("#form")
					.attr("action",
							"${ctx}/monitorstatictics/monitorStaticticsAction.a?searchStaticticsDataForCZ");
			$("#form").submit();
		}
	}
	 var dStatus = "";
	 var timeId = "";
	 var count = 0;
	 function queryDownStatus(){
		 $("#MyDiv").css("height","120%");
		 $("#timeCount").html("已耗时间"+(++count)+"秒");
	 	 $.ajax({
		     url: "${ctx}/export/shoolFillCount.a?queryDownLoadStatus",
		     type: "POST",
		     data: {
		    	 dStatus:dStatus
		     },
		     success: function(data) {
		    	if(data.val=="1"){
		    		$("#MyDiv").css("display","none");
		    		clearInterval(timeId);
		    		count=0;
		    		 $("#timeCount").html("正在导出数据...");
		    	}else if(data.val=="2"){//导出文件失败
		    		$("#MyDiv").css("display","none");
		    		clearInterval(timeId);
		    		count=0;
		    		 $("#timeCount").html("正在导出数据...");
		    		fail_notice_word("导出数据失败！");
		    	}
		     }
		 });
	 }
	 function uuid() {
	     var s = [];
	     var hexDigits = "0123456789abcdef";
	     for (var i = 0; i < 36; i++) {
	         s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	     }
	     s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
	     s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
	     s[8] = s[13] = s[18] = s[23] = "-";
	     var uuid = s.join("");
	     return uuid;
	 }
</script>
</head>

<body>
	
		<div class="dangqianwz">
		<form id="form" action=""
		method="post">
		<input id="gradeName" name="gradeName" type="hidden" value=""/>
		<input id="className" name="className" type="hidden" value=""/>
		<input id="termName" name="termName" type="hidden" value=""/>
			<span class="fl">当前位置：监控统计（汇总）</span> <span>届别： <select
				name="gradeid" id='gradeid' class=" wenziliebiao100"
				onchange="changGrade(this.value)">
					<c:forEach var="gradYear" items="${queryJB}">
						<option
							<c:if test="${gradeid == fn:split(gradYear, '_')[0]}">selected</c:if>
							value="${fn:split(gradYear, '_')[0]}">${fn:split(gradYear,'_')[1]}</option>
					</c:forEach>
			</select>
			</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>班级： <select
				name="classid" id='classid' class="wenziliebiao100"
				onchange="changeClass(this.value)">
					<c:forEach var="class" items="${classs}">
						<option
							<c:if test="${classid == fn:split(class, '_')[0]}">selected</c:if>
							value="${fn:split(class, '_')[0]}">${fn:split(class,'_')[2]}</option>
					</c:forEach>
			</select>
			</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>学期： <select
				name="termid" id="termid" class="wenziliebiao100">
					<c:forEach var="term" items="${terms}">
						<option <c:if test="${termid == term.termid}">selected</c:if>
							value="${term.termid}">${term.termname}</option>
					</c:forEach>
			</select>
			</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span> <span>栏目：
					<select name="select"
					onchange="window.location.href = '#' + this.value"
					class="wenziliebiao100">
						<option selected="selected" value='1001'>新学期伊始的我</option>
						<option value='1002'>学期结束时的我</option>
						<option value='1003'>思想道德</option>
						<option value='1004'>学业成就</option>
						<option value='1005'>合作与交流</option>
						<option value='1006'>运动与健康</option>
						<option value='1007'>审美与表现</option>
						<option value='1008'>综合实践活动</option>
						<option value='1009'>个性发展</option>
				</select>
			</span><span><input type="button" value="查 询" 	id="queryBtn"
					name="searchStaticticsDataForCZ" class="button ml10" onclick="getData();"/></span>&nbsp;&nbsp;&nbsp;
				<span><input type="button" value="导 出" class="button ml10"
					onclick="exportExcel()" /></span>
						</form>
		</div>

	<input type="hidden" id="xxx" name="xxx" value=""/>
	<div id="pj_ziwo_main">
		<div class="top">
			<div
				style="position: absolute; top: 10px; left: 0; right: 0; z-index: 1;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#999999" id="table" class="biaoge"
					style="table-layout: fixed; border-collapse: collapse;">
					<tr style="height:48px;line-height:48px;font-size:16px;">
					<td colspan="10" align="left">
					<c:if test="${not empty gradeName}">${gradeName}届</c:if>
						<c:if test="${not empty className}">${className}</c:if>
						<c:if test="${not empty termName }">${termName}</c:if>
						数据填写监控统计
					</td>
					</tr>
					<tr>
						<td class="th" width="16%">一级栏目<a id="1001"></a></td>
						<td width="18%" colspan="2" class="th">二级栏目</td>
						<td width="10%" class="th">评价主体</td>
						<td width="10%" class="th">评价任务</td>
						<td width="10%" class="th">评价次数要求</td>
						<td width="8%" class="th">总人数</td>
						<td width="8%" class="th">完成人数</td>
						<td width="8%" class="th">未完成人数</td>
						<td width="12%" class="th">完成百分比（%）</td>
					</tr>
					</table>
					<table  width="100%" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#999999" id="table" class="biaoge"
					style="table-layout: fixed; border-collapse: collapse;">
					<tr id="1001">
						<td rowspan="2" class="h50">新学期伊始的我</td>
						<td class="h50 " colspan="2">自我评价<span class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期一次</td>
						<td class="h50" id="11-1">0</td>
						<td class="h50" id="11-2">0</td>
						<td class="h50" id="11-3">0</td>
						<td class="h50" id="11-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">我的发展目标<a id="1002"></a><span
							class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期一次</td>
						<td class="h50" id="12-1">0</td>
						<td class="h50" id="12-2">0</td>
						<td class="h50" id="12-3">0</td>
						<td class="h50" id="12-4">0</td>
					</tr>
					<!-- <tr>
		      <td class="h50 th">家长的期望</td>
		      <td class="h50 th">家长</td>
		      <td class="h50 th">鼓励填写</td>
		      <td class="h50 th">每学期一次</td>
		      <td class="h50" id="1020--22--5-">0</td>
		      <td class="h50" id="1020--31--5-">0</td>
		      <td class="h50" id="1020--32--5-">0</td>
        </tr> -->
					<tr id="1002">
						<td rowspan="3" class="h50 ">学期结束时的我</td>
						<td class="h50 " colspan="2">自我评价<span class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期一次</td>
						<td class="h50" id="21-1">0</td>
						<td class="h50" id="21-2">0</td>
						<td class="h50" id="21-3">0</td>
						<td class="h50" id="21-4">0</td>
					</tr>
					<!-- <tr>
		      <td class="h50 th">我的发展目标</td>
		      <td class="h50 th">学生本人</td>
		      <td class="h50 th">必填</td>
		      <td class="h50 th">每学期至少一次</td>
		      <td class="h50" id="2020--22--1-">0</td>
		      <td class="h50" id="2020--22--1-">0</td>
		      <td class="h50" id="2020--31--1-">0</td>
		      <td class="h50" id="2020--32--1-">0</td>
        </tr> -->
					<tr>
						<td class="h50 " colspan="2">班主任评语<span
							class="red">*</span></td>
						<td class="h50 ">班主任</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期一次</td>
						<td class="h50" id="22-1">0</td>
						<td class="h50" id="22-2">0</td>
						<td class="h50" id="22-3">0</td>
						<td class="h50" id="22-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">家长期望和寄语<a id="1003"></a><span class="red">*</span></td>
						<td class="h50 ">家长</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期一次</td>
						<td class="h50" id="23-1">0</td>
						<td class="h50" id="23-2">0</td>
						<td class="h50" id="23-3">0</td>
						<td class="h50" id="23-4">0</td>
					</tr>
					<tr id="1003">
						<td rowspan="5" class="h50 ">思想道德</td>
						<td class="h50 " colspan="2">自我评价<span class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="31-1">0</td>
						<td class="h50" id="31-2">0</td>
						<td class="h50" id="31-3">0</td>
						<td class="h50" id="31-4">0</td>
					</tr>
					<tr>
						<td class="h50 " rowspan="3" colspan="2">他人评价</td>
						<td class="h50 ">同学</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="32-1">0</td>
						<td class="h50" id="32-2">0</td>
						<td class="h50" id="32-3">0</td>
						<td class="h50" id="32-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">教师</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="33-1">0</td>
						<td class="h50" id="33-2">0</td>
						<td class="h50" id="33-3">0</td>
						<td class="h50" id="33-4">0</td>
					</tr>
					<tr>
						<td class="h50 "></a>家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="34-1">0</td>
						<td class="h50" id="34-2">0</td>
						<td class="h50" id="34-3">0</td>
						<td class="h50" id="34-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">思想道德记录袋<a id="1004"><span class="red"></span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="35-1">0</td>
						<td class="h50" id="35-2">0</td>
						<td class="h50" id="35-3">0</td>
						<td class="h50" id="35-4">0</td>
					</tr>
					<tr id="1004">
						<td rowspan="5" class="h50 ">学业成就</td>
						<td height="47" class="h50 " colspan="2">自我评价<span
							class="red"></span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="41-1">0</td>
						<td class="h50" id="41-2">0</td>
						<td class="h50" id="41-3">0</td>
						<td class="h50" id="41-4">0</td>
					</tr>
					<!-- <tr>
		      <td height="47" class="h50 th">课程评语<span class="red">*</span></td>
		      <td class="h50 th">学生本人</td>
		      <td class="h50 th">必填</td>
		      <td class="h50 th">每生每年一次</td>
		      <td class="h50" id="890909--22--1-">0</td>
		      <td class="h50" id="890909--31--1-">0</td>
		      <td class="h50" id="890909--32--1-">0</td>
        </tr> -->
					<tr>
						<td rowspan="1" class="h50 " colspan="2">课程评语</td>
						<td class="h50 ">教师</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每生每年一次</td>
						<td class="h50" id="44-1">0</td>
						<td class="h50" id="44-2">0</td>
						<td class="h50" id="44-3">0</td>
						<td class="h50" id="44-4">0</td>
					</tr>
					<tr>
						<td class="h50 " rowspan="2" colspan="2">他人评价</td>
						<td class="h50 ">同学<span class="red"></span></td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="45-1">0</td>
						<td class="h50" id="45-2">0</td>
						<td class="h50" id="45-3">0</td>
						<td class="h50" id="45-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="46-1">0</td>
						<td class="h50" id="46-2">0</td>
						<td class="h50" id="46-3">0</td>
						<td class="h50" id="46-4">0</td>
					</tr>
					<tr>
						<td height="47" class="h50 " colspan="2"><a id="1005"></a>学科作品展示</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="43-1">0</td>
						<td class="h50" id="43-2">0</td>
						<td class="h50" id="43-3">0</td>
						<td class="h50" id="43-4">0</td>
					</tr>
					<tr id="1005">
						<td rowspan="5" class="h50 ">合作与交流</td>
						<td class="h50 " colspan="2">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="51-1">0</td>
						<td class="h50" id="51-2">0</td>
						<td class="h50" id="51-3">0</td>
						<td class="h50" id="51-4">0</td>
					</tr>
					<tr>
						<td rowspan="3" class="h50 " colspan="2">他人评价</td>
						<td class="h50 ">同学<span class="red">*</span></td>
						<td class="h50 ">必填</td>
						<td class="h50 ">不少于 3 人</td>
						<td class="h50" id="52-1">0</td>
						<td class="h50" id="52-2">0</td>
						<td class="h50" id="52-3">0</td>
						<td class="h50" id="52-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">教师</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="53-1">0</td>
						<td class="h50" id="53-2">0</td>
						<td class="h50" id="53-3">0</td>
						<td class="h50" id="53-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="54-1">0</td>
						<td class="h50" id="54-2">0</td>
						<td class="h50" id="54-3">0</td>
						<td class="h50" id="54-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">合作与交流行为记录袋<a id="1006"></a><span class="red"></span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="55-1">0</td>
						<td class="h50" id="55-2">0</td>
						<td class="h50" id="55-3">0</td>
						<td class="h50" id="55-4">0</td>
					</tr>
					<tr id="1006">
						<td rowspan="5" class="h50 ">运动与健康</td>
						<td class="h50 " colspan="2">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="61-1">0</td>
						<td class="h50" id="61-2">0</td>
						<td class="h50" id="61-3">0</td>
						<td class="h50" id="61-4">0</td>
					</tr>
					<tr>
						<td rowspan="3" class="h50 " colspan="2">他人评价</td>
						<td class="h50 ">同学</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="62-1">0</td>
						<td class="h50" id="62-2">0</td>
						<td class="h50" id="62-3">0</td>
						<td class="h50" id="62-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">教师</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="63-1">0</td>
						<td class="h50" id="63-2">0</td>
						<td class="h50" id="63-3">0</td>
						<td class="h50" id="63-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="64-1">0</td>
						<td class="h50" id="64-2">0</td>
						<td class="h50" id="64-3">0</td>
						<td class="h50" id="64-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">体质健康<a id="1007"></a><span class="red"></span></td>
						<td class="h50 ">云平台提取</td>
						<td class="h50 "></td>
						<td class="h50 ">每年一次</td>
						<td class="h50" id="65-1">0</td>
						<td class="h50" id="65-2">0</td>
						<td class="h50" id="65-3">0</td>
						<td class="h50" id="65-4">0</td>
					</tr>
					<tr id="1007">
						<td rowspan="5" class="h50 ">审美与表现</td>
						<td class="h50 " colspan="2">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="71-1">0</td>
						<td class="h50" id="71-2">0</td>
						<td class="h50" id="71-3">0</td>
						<td class="h50" id="71-4">0</td>
					</tr>
					<tr>
						<td rowspan="3" class="h50 " colspan="2">他人评价</td>
						<td class="h50 ">同学</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="72-1">0</td>
						<td class="h50" id="72-2">0</td>
						<td class="h50" id="72-3">0</td>
						<td class="h50" id="72-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">教师</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="73-1">0</td>
						<td class="h50" id="73-2">0</td>
						<td class="h50" id="73-3">0</td>
						<td class="h50" id="73-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="74-1">0</td>
						<td class="h50" id="74-2">0</td>
						<td class="h50" id="74-3">0</td>
						<td class="h50" id="74-4">0</td>
					</tr>
					<tr>
						<td class="h50 " colspan="2">审美与表现记录袋<a id="1008"></a><span class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="75-1">0</td>
						<td class="h50" id="75-2">0</td>
						<td class="h50" id="75-3">0</td>
						<td class="h50" id="75-4">0</td>
					</tr>
					<tr id="1008">
						<td rowspan="5" class="h50 ">综合实践活动</td>
						<td rowspan="3" class="h50 ">研究性学习<span class="red">*</span></td>
						<td class="h50 ">基本情况</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="81-1">0</td>
						<td class="h50" id="81-2">0</td>
						<td class="h50" id="81-3">0</td>
						<td class="h50" id="81-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">研究成果</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="82-1">0</td>
						<td class="h50" id="82-2">0</td>
						<td class="h50" id="82-3">0</td>
						<td class="h50" id="82-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="83-1">0</td>
						<td class="h50" id="83-2">0</td>
						<td class="h50" id="83-3">0</td>
						<td class="h50" id="83-4">0</td>
					</tr>
					<!-- <tr>
		      <td class="h50 th">社区服务<span class="red"></span></td>
		      <td class="h50 th">学生本人</td>
		      <td class="h50 th">必填</td>
		      <td class="h50 th">次数不限</td>
		      <td class="h50" id="9020--22--1-">0</td>
		      <td class="h50" id="9020--31--1-">0</td>
		      <td class="h50" id="9020--32--1-">0</td>
        </tr> -->
					<tr>
						<td rowspan="2" class="h50 ">社区服务社会实践活动</td>
						<td class="h50 ">基本情况</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="84-1">0</td>
						<td class="h50" id="84-2">0</td>
						<td class="h50" id="84-3">0</td>
						<td class="h50" id="84-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="85-1">0</td>
						<td class="h50" id="85-2">0</td>
						<td class="h50" id="85-3">0</td>
						<td class="h50" id="85-4">0</td>
					</tr>
					<tr id="1009">
						<td rowspan="5" class="h50 "><a id="1009"></a>个性发展</td>
						<td colspan="2" class="h50 ">自我评价</td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="91-1">0</td>
						<td class="h50" id="91-2">0</td>
						<td class="h50" id="91-3">0</td>
						<td class="h50" id="91-4">0</td>
					</tr>
					<tr>
						<td rowspan="3" colspan="2" class="h50 ">他人评价</td>
						<td class="h50 ">同学</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="92-1">0</td>
						<td class="h50" id="92-2">0</td>
						<td class="h50" id="92-3">0</td>
						<td class="h50" id="92-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">教师</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="93-1">0</td>
						<td class="h50" id="93-2">0</td>
						<td class="h50" id="93-3">0</td>
						<td class="h50" id="93-4">0</td>
					</tr>
					<tr>
						<td class="h50 ">家长</td>
						<td class="h50 ">鼓励填写</td>
						<td class="h50 ">次数不限</td>
						<td class="h50" id="94-1">0</td>
						<td class="h50" id="94-2">0</td>
						<td class="h50" id="94-3">0</td>
						<td class="h50" id="94-4">0</td>
					</tr>
					<!-- <tr>
		      <td class="h50 th">个性发展过程<span class="red"></span></td>
		      <td class="h50 th">学生本人</td>
		      <td class="h50 th">必填</td>
		      <td class="h50 th">每学期至少一次</td>
		      <td class="h50" id="7040--22--1-">0</td>
		      <td class="h50" id="7040--31--1-">0</td>
		      <td class="h50" id="7040--32--1-">0</td>
        </tr> -->
					<tr>
						<td colspan="2" class="h50 ">特长与成果展示<span class="red">*</span></td>
						<td class="h50 ">学生本人</td>
						<td class="h50 ">必填</td>
						<td class="h50 ">每学期至少一次</td>
						<td class="h50" id="95-1">0</td>
						<td class="h50" id="95-2">0</td>
						<td class="h50" id="95-3">0</td>
						<td class="h50" id="95-4">0</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/common/div.jsp"%>
</body>
<script type="text/javascript">
	var gid = $("#gradeid");
	var cid = $("#classid");
	var tid = $("#termid");
	function changGrade(gradeid) {
		if (gradeid == '') {
			gid.val('');
			DWRUtil.removeAllOptions('classid');
			cid.val('');
			DWRUtil.removeAllOptions('termid');
			tid.val("");
			return;
		}
		var grade = $("#gradeid option:selected").text();
		$("#gradeName").val(grade);
		ShowUserFuncDwr.queryClassId(gradeid, function(data) {
			if (null != data && data.length > 0) {
				DWRUtil.removeAllOptions('classid');
				DWRUtil.addOptions('classid', data, 'classId', 'className');
			} else if (null != data && data.length == 0) {
				DWRUtil.removeAllOptions('classid');
				cid.val('');
				DWRUtil.removeAllOptions('termid');
				tid.val("");
			}
			var classid = document.getElementById("classid").value;
			changeClass(classid);
		});
	}
	function changeClass(classid) {
		if (classid == undefined || classid == '') {
			DWRUtil.removeAllOptions('termid');
			tid.val("");
			return;
		}
		var clazz = $("#classid option:selected").text();
		$("#className").val(clazz);
		ShowUserFuncDwr.queryTermDto(classid, function(data) {
			if (null != data && data.length > 0) {
				DWRUtil.removeAllOptions('termid');
				DWRUtil.addOptions('termid', data, 'termid', 'termname');
				var term = $("#termid option:selected").text();
				$("#termName").val(term);
			}
		});
		
	}
			
	function checkData() {
		if (gid.val() == '' || !cid.val() || cid.val() == '' || !tid.val()
				|| tid.val() == '') {
			if (gid.val() == '') {
				alert("请选择届别!");
				return false;
			}
			if (!cid.val() || cid.val() == '') {
				alert("请选择班级!");
				return false;
			}
			if (!tid.val() || tid.val() == '') {
				alert("请选择学期!");
				return false;
			}
		}
		return true;
	}
	var list = ${caches_string};
	var tpi = [ "11", "12", "21", "22", "23", "31", "32", "33", "34", "35",
			"41", "44", "45", "46", "43", "51", "52", "53", "54", "55", "61",
			"62", "63", "64", "65", "71", "72", "73", "74", "75", "81", "82",
			"83", "84", "85", "91", "92", "93", "94", "95" ];
	for ( var i = 0, len = tpi.length; i < len; i++) {
		var obj = list[i];
		var key = tpi[i];
		if (obj) {
			var array = obj[key] || [];
			for ( var index = 0; index < array.length; index++)
				$("#" + key + "-" + (index + 1)).html(array[index]);
		}
	}
</script>
</html>
