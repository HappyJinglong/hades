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
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
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
#pj_ziwo_main .top{
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
							"${ctx}/monitorstatictics/monitorStaticticsAction.a?searchStaticticsDataForGZ");
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
<form id="form" action="" method="post">
	<input id="gradeName" name="gradeName" type="hidden" value=""/>
	<input id="className" name="className" type="hidden" value=""/>
	<input id="termName" name="termName" type="hidden" value=""/>
	<div class="dangqianwz">
		<span>当前位置：监控统计（汇总）</span> 
		<span >届别：
     <select name="gradeid" id='gradeid' class=" wenziliebiao100"  onchange="changGrade(this.value)">
               <c:forEach var="gradYear" items="${queryJB}">
	               	<option <c:if test="${gradeid == fn:split(gradYear, '_')[0]}">selected</c:if> value="${fn:split(gradYear, '_')[0]}">${fn:split(gradYear,'_')[1]}</option>
	           </c:forEach>
            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span >班级：
     <select name="classid" id='classid' class="wenziliebiao100"  value=""onchange="changeClass(this.value)">
     		 <c:forEach var="class" items="${classs}">
	               		<option value="${fn:split(class, '_')[0]}" <c:if test="${fn:split(class, '_')[0] eq classid}">selected='selected'</c:if>>${fn:split(class,'_')[2]}</option>
	          </c:forEach>	
     </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
     <select name="termid" id="termid" class="wenziliebiao100" value="">
              <c:forEach var="term" items="${terms}">
	               	<option value="${term.termid}" <c:if test="${term.termid eq termid}">selected='selected'</c:if>>${term.termname}</option>
	          </c:forEach>
     </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >
		<span>栏目： <select name="select" onchange="window.location.href = '#' + this.value" class="wenziliebiao100">
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
		</span><span><input type="button" value="查 询"  id="queryBtn" class="button ml10" onclick="getData();"/></span>&nbsp;&nbsp;&nbsp;
		<span><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
	</div>
</form>
	<div id="pj_ziwo_main">
		<div class="top">
			<div style="position: absolute;top: 10px;left: 0;right: 0;z-index: 1;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				bgcolor="#999999" id="table" class="biaoge"
				style="table-layout: fixed; border-collapse: collapse;">
					<tr style="height:48px;line-height:48px;font-size:16px;">
					<td colspan="9" align="left">
					<c:if test="${not empty gradeName}">${gradeName}届</c:if>
						<c:if test="${not empty className}">${className}</c:if>
						<c:if test="${not empty termName }">${termName}</c:if>
						数据填写监控统计
					</td>
					</tr>
					<tr>
						<td class="th" width="14%">一级栏目<a id="1001"></a></td>
						<td width="20%" class="th">二级栏目</td>
						<td width="10%" class="th">评价主体</td>
						<td width="10%" class="th">评价任务</td>
						<td width="10%" class="th">评价次数要求</td>
						<td width="8%" class="th">总人数</td>
						<td width="8%" class="th">完成人数</td>
						<td width="8%" class="th">未完成人数</td>
						<td width="12%" class="th">完成百分比（%）</td>
					</tr>
		
				<tr id="1001">
					<td rowspan="3" class="h50">新学期伊始的我</td>
					<td class="h50">自我评价<span class="red">*</span></td>
					<td class="h50">学生本人</td>
					<td class="h50">必填</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="1010-1">0</td>
					<td class="h50" id="1010-2">0</td>
					<td class="h50" id="1010-3">0</td>
					<td class="h50" id="1010-4">0</td>
				</tr>
				<tr>
					<td class="h50">我的发展目标<span
						class="red">*</span></td>
					<td class="h50">学生本人</td>
					<td class="h50">必填</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="1020-1-1">0</td>
					<td class="h50" id="1020-1-2">0</td>
					<td class="h50" id="1020-1-3">0</td>
					<td class="h50" id="1020-1-4">0</td>
				</tr>
				<tr>
					<td class="h50"><a id="1002"></a>家长的期望</td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="1020-5-1">0</td>
					<td class="h50" id="1020-5-2">0</td>
					<td class="h50" id="1020-5-3">0</td>
					<td class="h50" id="1020-5-4">0</td>
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
					<td rowspan="4" class="h50">学期结束时的我</td>
					<td class="h50">自我评价<span class="red">*</span></td>
					<td class="h50">学生本人</td>
					<td class="h50">必填</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="2010-1">0</td>
					<td class="h50" id="2010-2">0</td>
					<td class="h50" id="2010-3">0</td>
					<td class="h50" id="2010-4">0</td>
				</tr>
				<tr>
				      <td class="h50">我的发展目标</td>
				      <td class="h50">学生本人</td>
				      <td class="h50">必填</td>
				      <td class="h50">每学期至少一次</td>
				      <td class="h50" id="2020-1">0</td>
				      <td class="h50" id="2020-2">0</td>
				      <td class="h50" id="2020-3">0</td>
				      <td class="h50" id="2020-4">0</td>
		        </tr>
				<tr>
					<td class="h50">班主任评语<span
						class="red">*</span></td>
					<td class="h50">班主任</td>
					<td class="h50">必填</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="2030-1">0</td>
					<td class="h50" id="2030-2">0</td>
					<td class="h50" id="2030-3">0</td>
					<td class="h50" id="2030-4">0</td>
				</tr>
				<tr>
					<td class="h50">家长期望和寄语<a id="1003"></a><span class="red">*</span></td>
					<td class="h50">家长</td>
					<td class="h50">必填</td>
					<td class="h50">每学期一次</td>
					<td class="h50" id="2040-1">0</td>
					<td class="h50" id="2040-2">0</td>
					<td class="h50" id="2040-3">0</td>
					<td class="h50" id="2040-4">0</td>
				</tr>
				<tr id="1003">
					<td rowspan="5" class="h50">思想道德</td>
					<td class="h50">自我评价<span class="red">*</span></td>
					<td class="h50">学生本人</td>
					<td class="h50">必填</td>
					<td class="h50">每学期至少一次</td>
					<td class="h50" id="3010-1">0</td>
					<td class="h50" id="3010-2">0</td>
					<td class="h50" id="3010-3">0</td>
					<td class="h50" id="3010-4">0</td>
				</tr>
				<tr>
					<td class="h50" rowspan="3">他人评价</td>
					<td class="h50">同学</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="3020-2-1">0</td>
					<td class="h50" id="3020-2-2">0</td>
					<td class="h50" id="3020-2-3">0</td>
					<td class="h50" id="3020-2-4">0</td>
				</tr>
				<tr>
					<td class="h50">教师</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="3020-3-1">0</td>
					<td class="h50" id="3020-3-2">0</td>
					<td class="h50" id="3020-3-3">0</td>
					<td class="h50" id="3020-3-4">0</td>
				</tr>
				<tr>
					<td class="h50">家长</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="3020-5-1">0</td>
					<td class="h50" id="3020-5-2">0</td>
					<td class="h50" id="3020-5-3">0</td>
					<td class="h50" id="3020-5-4">0</td>
				</tr>
				<tr>
					<td class="h50">思想道德记录袋<span class="red"></span><a id="1004"></a></td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="3030-1">0</td>
					<td class="h50" id="3030-2">0</td>
					<td class="h50" id="3030-3">0</td>
					<td class="h50" id="3030-4">0</td>
				</tr>
				<tr id="1004">
					<td rowspan="6" class="h50">学业成就</td>
					<td height="47" class="h50">自我评价<span
						class="red"></span></td>
					<td class="h50">学生本人</td>
					<td class="h50">必填</td>
					<td class="h50">每学期至少一次</td>
					<td class="h50" id="8020-1">0</td>
					<td class="h50" id="8020-2">0</td>
					<td class="h50" id="8020-3">0</td>
					<td class="h50" id="8020-4">0</td>
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
        <td height="47" class="h50">学科作品展示<span
						class="red"></span></td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="8010-1">0</td>
					<td class="h50" id="8010-2">0</td>
					<td class="h50" id="8010-3">0</td>
					<td class="h50" id="8010-4">0</td>
					</tr>
					 <tr>
        <td height="47" class="h50">课程评语<span
						class="red">*</span></td>
					<td class="h50">教师</td>
					<td class="h50">必填</td>
					<td class="h50">每生每年一次</td>
					<td class="h50" id="9999-1">0</td>
					<td class="h50" id="9999-2">0</td>
					<td class="h50" id="9999-3">0</td>
					<td class="h50" id="9999-4">0</td>
					</tr>
				<tr>
					<td rowspan="3" class="h50">他人评价</td>
					<td class="h50">同学<span class="red"></span></td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="8040-2-1">0</td>
					<td class="h50" id="8040-2-2">0</td>
					<td class="h50" id="8040-2-3">0</td>
					<td class="h50" id="8040-2-4">0</td>
				</tr>
				<tr>
					<td class="h50">教师<span class="red"></span></td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="8040-3-1">0</td>
					<td class="h50" id="8040-3-2">0</td>
					<td class="h50" id="8040-3-3">0</td>
					<td class="h50" id="8040-3-4">0</td>
				</tr>
				<tr>
					<td class="h50">家长<a id="1005"></a></td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="8040-5-1">0</td>
					<td class="h50" id="8040-5-2">0</td>
					<td class="h50" id="8040-5-3">0</td>
					<td class="h50" id="8040-5-4">0</td>
				</tr>
				<!-- <tr>
					<td height="47" class="h50 th">学科作品展示</td>
					<td class="h50 th">学生本人</td>
					<td class="h50 th">必填</td>
					<td class="h50 th">次数不限</td>
					<td class="h50" id="43-1">0</td>
					<td class="h50" id="43-2">0</td>
					<td class="h50" id="43-3">0</td>
					<td class="h50" id="43-4">0</td>
				</tr>-->
				<tr id="1005">
					<td rowspan="5" class="h50">合作与交流</td>
					<td class="h50">自我评价</td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="4010-1">0</td>
					<td class="h50" id="4010-2">0</td>
					<td class="h50" id="4010-3">0</td>
					<td class="h50" id="4010-4">0</td>
				</tr>
				<tr>
					<td rowspan="3" class="h50">他人评价</td>
					<td class="h50">同学<span class="red">*</span></td>
					<td class="h50">必填</td>
					<td class="h50">不少于 3 人</td>
					<td class="h50" id="4020-2-1">0</td>
					<td class="h50" id="4020-2-2">0</td>
					<td class="h50" id="4020-2-3">0</td>
					<td class="h50" id="4020-2-4">0</td>
				</tr>
				<tr>
					<td class="h50">教师</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="4020-3-1">0</td>
					<td class="h50" id="4020-3-2">0</td>
					<td class="h50" id="4020-3-3">0</td>
					<td class="h50" id="4020-3-4">0</td>
				</tr>
				<tr>
					<td class="h50">家长</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="4020-5-1">0</td>
					<td class="h50" id="4020-5-2">0</td>
					<td class="h50" id="4020-5-3">0</td>
					<td class="h50" id="4020-5-4">0</td>
				</tr>
				<tr>
					<td class="h50">合作与交流行为记录袋<a id="1006"></a><span class="red"></span></td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="4030-1">0</td>
					<td class="h50" id="4030-2">0</td>
					<td class="h50" id="4030-3">0</td>
					<td class="h50" id="4030-4">0</td>
				</tr>
				<tr id="1006">
					<td rowspan="5" class="h50">运动与健康</td>
					<td class="h50">自我评价</td>
					<td class="h50">学生本人</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="5010-1">0</td>
					<td class="h50" id="5010-2">0</td>
					<td class="h50" id="5010-3">0</td>
					<td class="h50" id="5010-4">0</td>
				</tr>
				<tr>
					<td rowspan="3" class="h50">他人评价</td>
					<td class="h50">同学</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="5020-2-1">0</td>
					<td class="h50" id="5020-2-2">0</td>
					<td class="h50" id="5020-2-3">0</td>
					<td class="h50" id="5020-2-4">0</td>
				</tr>
				<tr>
					<td class="h50">教师</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="5020-3-1">0</td>
					<td class="h50" id="5020-3-2">0</td>
					<td class="h50" id="5020-3-3">0</td>
					<td class="h50" id="5020-3-4">0</td>
				</tr>
				<tr>
					<td class="h50">家长</td>
					<td class="h50">鼓励填写</td>
					<td class="h50">次数不限</td>
					<td class="h50" id="5020-5-1">0</td>
					<td class="h50" id="5020-5-2">0</td>
					<td class="h50" id="5020-5-3">0</td>
					<td class="h50" id="5020-5-4">0</td>
				</tr>
				<tr>
					<td class="h50">体质健康<a id="1007"></a><span class="red"></span></td>
					<td class="h50">云平台提取</td>
					<td class="h50"></td>
					<td class="h50">每年一次</td>
					<td class="h50" id="5050-1">0</td>
					<td class="h50" id="5050-2">0</td>
					<td class="h50" id="5050-3">0</td>
					<td class="h50" id="5050-4">0</td>
				</tr>
				<tr id="1007">
					<td rowspan="5" class="h50">审美与表现</td>
					<td class="h50">自我评价</td>
					<td class="h50 ">学生本人</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="6010-1">0</td>
					<td class="h50" id="6010-2">0</td>
					<td class="h50" id="6010-3">0</td>
					<td class="h50" id="6010-4">0</td>
				</tr>
				<tr>
					<td rowspan="3" class="h50 ">他人评价</td>
					<td class="h50 ">同学</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="6020-2-1">0</td>
					<td class="h50" id="6020-2-2">0</td>
					<td class="h50" id="6020-2-3">0</td>
					<td class="h50" id="6020-2-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">教师</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="6020-3-1">0</td>
					<td class="h50" id="6020-3-2">0</td>
					<td class="h50" id="6020-3-3">0</td>
					<td class="h50" id="6020-3-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">家长</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="6020-5-1">0</td>
					<td class="h50" id="6020-5-2">0</td>
					<td class="h50" id="6020-5-3">0</td>
					<td class="h50" id="6020-5-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">审美与表现记录袋<a id="1008"></a><span class="red">*</span></td>
					<td class="h50 ">学生本人</td>
					<td class="h50 ">必填</td>
					<td class="h50 ">每学期至少一次</td>
					<td class="h50" id="6030-1">0</td>
					<td class="h50" id="6030-2">0</td>
					<td class="h50" id="6030-3">0</td>
					<td class="h50" id="6030-4">0</td>
				</tr>
				<tr id="1008">
					<td rowspan="3" class="h50 ">综合实践活动</td>
					<td class="h50 ">研究性学习<span class="red">*</span></td>
<!-- 					<td class="h50 th">基本情况</td> -->
					<td class="h50 ">学生本人</td>
					<td class="h50 ">必填</td>
					<td class="h50 ">每学期至少一次</td>
					<td class="h50" id="9010-1">0</td>
					<td class="h50" id="9010-2">0</td>
					<td class="h50" id="9010-3">0</td>
					<td class="h50" id="9010-4">0</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="h50 th">研究成果</td> -->
<!-- 					<td class="h50 th">学生本人</td> -->
<!-- 					<td class="h50 th">鼓励填写</td> -->
<!-- 					<td class="h50 th">次数不限</td> -->
<!-- 					<td class="h50" id="9010--1">0</td> -->
<!-- 					<td class="h50" id="9010--2">0</td> -->
<!-- 					<td class="h50" id="9010--3">0</td> -->
<!-- 					<td class="h50" id="9010--4">0</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td class="h50 th">自我评价</td> -->
<!-- 					<td class="h50 th">学生本人</td> -->
<!-- 					<td class="h50 th">鼓励填写</td> -->
<!-- 					<td class="h50 th">次数不限</td> -->
<!-- 					<td class="h50" id="9010--1">0</td> -->
<!-- 					<td class="h50" id="9010--2">0</td> -->
<!-- 					<td class="h50" id="9010--3">0</td> -->
<!-- 					<td class="h50" id="9010--4">0</td> -->
<!-- 				</tr> -->
				<tr>
				      <td class="h50 ">社区服务<span class="red"></span></td>
				      <td class="h50 ">学生本人</td>
				      <td class="h50 ">必填</td>
				      <td class="h50 ">次数不限</td>
				      <td class="h50" id="9020-1">0</td>
				      <td class="h50" id="9020-2">0</td>
				      <td class="h50" id="9020-3">0</td>
				      <td class="h50" id="9020-4">0</td>
		        </tr>
				<tr>
					<td class="h50 ">社区服务社会实践活动<a id="1009"></td>
<!-- 					<td class="h50 th">基本情况</td> -->
					<td class="h50 ">学生本人</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="9030-1">0</td>
					<td class="h50" id="9030-2">0</td>
					<td class="h50" id="9030-3">0</td>
					<td class="h50" id="9030-4">0</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="h50 th">自我评价</td> -->
<!-- 					<td class="h50 th">学生本人</td> -->
<!-- 					<td class="h50 th">鼓励填写</td> -->
<!-- 					<td class="h50 th">次数不限</td> -->
<!-- 					<td class="h50" id="9030--1">0</td> -->
<!-- 					<td class="h50" id="9030--2">0</td> -->
<!-- 					<td class="h50" id="9030--3">0</td> -->
<!-- 					<td class="h50" id="9030--4">0</td> -->
<!-- 				</tr> -->
				<tr id="1009">
					<td rowspan="7" class="h50 "></a>个性发展</td>
					<td class="h50 ">基本情况</td>
					<td class="h50 ">学生本人</td>
					<td class="h50 ">必填</td>
					<td class="h50 ">每学期一次</td>
					<td class="h50" id="7010-1">0</td>
					<td class="h50" id="7010-2">0</td>
					<td class="h50" id="7010-3">0</td>
					<td class="h50" id="7010-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">自我评价</td>
					<td class="h50 ">学生本人</td>
					<td class="h50 ">必填</td>
					<td class="h50 ">每学期至少一次</td>
					<td class="h50" id="7020-1">0</td>
					<td class="h50" id="7020-2">0</td>
					<td class="h50" id="7020-3">0</td>
					<td class="h50" id="7020-4">0</td>
				</tr>
				<tr>
					<td rowspan="3" class="h50 ">他人评价</td>
					<td class="h50 ">同学</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="7030-2-1">0</td>
					<td class="h50" id="7030-2-2">0</td>
					<td class="h50" id="7030-2-3">0</td>
					<td class="h50" id="7030-2-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">教师</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="7030-3-1">0</td>
					<td class="h50" id="7030-3-2">0</td>
					<td class="h50" id="7030-3-3">0</td>
					<td class="h50" id="7030-3-4">0</td>
				</tr>
				<tr>
					<td class="h50 ">家长</td>
					<td class="h50 ">鼓励填写</td>
					<td class="h50 ">次数不限</td>
					<td class="h50" id="7030-5-1">0</td>
					<td class="h50" id="7030-5-2">0</td>
					<td class="h50" id="7030-5-3">0</td>
					<td class="h50" id="7030-5-4">0</td>
				</tr>
				
				<tr>
					<td class="h50 ">特长与成果展示<span class="red">*</span></td>
					<td class="h50 ">学生本人</td>
					<td class="h50 ">必填</td>
					<td class="h50 ">每学期至少一次</td>
					<td class="h50" id="7050-1">0</td>
					<td class="h50" id="7050-2">0</td>
					<td class="h50" id="7050-3">0</td>
					<td class="h50" id="7050-4">0</td>
				</tr>
				<tr>
		      <td class="h50 ">个性发展过程<span class="red"></span></td>
		      <td class="h50 ">学生本人</td>
		      <td class="h50 ">必填</td>
		      <td class="h50 ">每学期至少一次</td>
		      <td class="h50" id="7040-1">0</td>
		      <td class="h50" id="7040-2">0</td>
		      <td class="h50" id="7040-3">0</td>
		      <td class="h50" id="7040-4">0</td>
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
	function changGrade(gradeid){
		if(gradeid == ''){
			gid.val('');
			DWRUtil.removeAllOptions('classid');
			cid.val('');
			DWRUtil.removeAllOptions('termid');
			tid.val("");
			return ;
		}
		var grade = $("#gradeid option:selected").text();
		$("#gradeName").val(grade);
		ShowUserFuncDwr.queryClassId(gradeid,function(data){
			if(null != data && data.length > 0){
				DWRUtil.removeAllOptions('classid');
				DWRUtil.addOptions('classid',data,'classId','className');
			}else if(null != data && data.length == 0){
				DWRUtil.removeAllOptions('classid');
				cid.val('');
				DWRUtil.removeAllOptions('termid');
				tid.val("");
			}
			var classid = document.getElementById("classid").value;
			changeClass(classid);	
		});
	}
	function changeClass(classid){
		if(classid == undefined || classid == ''){
			DWRUtil.removeAllOptions('termid');
			tid.val("");
			return ;
		}
		var clazz = $("#classid option:selected").text();
		$("#className").val(clazz);
		ShowUserFuncDwr.queryTermDto(classid,function(data){
			if(null != data && data.length > 0){
				DWRUtil.removeAllOptions('termid');
				DWRUtil.addOptions('termid',data,'termid','termname');
				var term = $("#termid option:selected").text();
				$("#termName").val(term);
			}
		});
	}
	function checkData(){
		if(gid.val() == '' || !cid.val() || cid.val() == '' || !tid.val() || tid.val() == ''){
			if(!gid.val()||gid.val() == ''){
				alert("请选择届别!");
				return false;
			}
			if( !cid.val() || cid.val() == ''){
				alert("请选择班级!");
				return false;
			}
			if(!tid.val() || tid.val() == ''){
				alert("请选择学期!");
				return false;
			}
		}
		return true;
	};
	var list = ${caches_string};
		
	var tpi = [ "1010", "1020", "2010", "2020", "2030", "2040",
				"3010", "3020", "3030", "8020", "8010","9999", "8040", "4010",
				"4020", "4030", "5010", "5020", "5050", "6010", "6020",
				"6030", "9010", "9020", "9030", "7010", "7020", "7030",
				"7050", "7040" ];
	for ( var i = 0, len = tpi.length; i < len; i++) {
		var obj = list[i];
		var key = tpi[i];
		if (obj) {
			var array = obj[key];
			if (array) {
				for ( var index = 0; index < array.length; index++) {
					var _obj = array[index];
					if ($.type(_obj) == 'object') {
						for ( var _key in _obj) {
							var value = _obj[_key];
							for ( var _index = 0; _index < value.length; _index++)
								$("#" + key + "-" + _key + "-" + (_index + 1))
										.html(value[_index]);
						}
					} else
						$("#" + key + "-" + (index + 1)).html(_obj);
				}
			}
		}
	}
</script>
</html>
