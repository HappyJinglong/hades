<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>
<title>统计表</title>
<script type="text/javascript">
function go(){
	 var typeId = document.getElementById("levelid").value;
	window.location.hash=typeId;
	//alert('dddd');
}
function submitdata(){
	$("#termName").val($("#termId option:selected").html());
	ShowDiv();
	$("#form").submit();
}
function exportExcel()
{ 
	var termId = $("#termId option:selected").val();
	var termName =  $("#termId option:selected").text();
	dStatus = uuid(); 
	window.location.href="${ctx}/export/TotalCountExportAction.a?termId="+termId+ "&dStatus=" + dStatus+"&termName="+termName;
	ShowDiv(); 
	timeId = setInterval(queryDownStatus,1000);
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
<div class="dangqianwz" style="padding-top: 12px;z-index:1001;position: fixed;width: 99%">
<form id="form" action="${ctx}//totalAppraisalStatics/TotalAppraisalStaticsAction.a" method="post">
 	<input type="hidden" name="termName" value="${termName }" id="termName"/>
 	<span class="fl">当前位置：数据填写总体情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <span >栏目：
     <select name="select" id="levelid" class="wenziliebiao100" onchange="go();">
               	<option value='1001' selected="selected">新学期伊始的我</option>
                <option value='1002'>学期结束时的我</option>
                	<option value='1003'>思想道德</option>
                	<option value='1004'>学业成就</option>
                	<option value='1005'>合作与交流</option>
                	<option value='1006'>运动与健康</option>
                	<option value='1007'>审美与表现</option>
                	<option value='1008'>综合实践活动</option>
                	<option value='1009'>个性与发展</option>
            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >学期：
     		<select  name="termId" id="termId" class="wenziliebiao100">
				<app:newTermId termId="${termId}"/>
			<select>
            </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="查 询" class="button ml10" onclick="submitdata();"/></span>&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
</form>
</div>
<div id="tjb_main">
    <!-- <div class="top" style="padding-bottom: 0px;height: 20%"> -->
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="22"><a id="1001"></a>
      <div class="tishi_left fl">${termName }数据填写总体情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="10%" rowspan="1">栏目</td>
     <td width="10%" rowspan="1">二级栏目</td>
     <td width="10%" rowspan="1">填写人</td>
     <td width="15%" rowspan="1">完成情况</td>
     <c:forEach items="${gradeYears}" var="gradeyear">
	     <td width="15%" colspan="1">${gradeyear}届</td>
     </c:forEach>
     </tr>
  <!-- 	</table>
	</div>
<div style="top:110px;overflow-x:hidden;margin-bottom: 39px;height:360px;margin-right:-16px;">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"> -->
  <tr>
    <td rowspan="12" width="10%">新学期伊始的我</td>
    <td rowspan="4" width="10%">自我评价</td>
    <td rowspan="4" width="10%">学生本人</td>
   <td width="15%">学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas["1010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
   </tr>
  <tr>
    <td width="15%">已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas["1010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
      <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td rowspan="4">我的发展目标</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
      <tr>
    <td rowspan="4">家长的期望</td>
    <td rowspan="4">家长</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）<a id="1002"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["1020-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    
    <!-- 新学期伊始的我 end -->
    <!-- 学期结束时的我 start -->
    <tr>
    <td rowspan="16">学期结束时的我</td>
    <td rowspan="4">自我评价</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
      <tr>
    <td rowspan="4">我的发展目标</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2020-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2020-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2020-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2020-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
      <tr>
    <td rowspan="4">班主任评语</td>
    <td rowspan="4">班主任</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2030"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2030"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2030"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
             <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2030"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td rowspan="4">家长评语和期望</td>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2040-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2040-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1003"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2040-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                 <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["2040-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <!-- 学期结束时的我 end -->
      <!-- 思想道德 start -->
    <tr>
      <td rowspan="21">思想道德</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 思想道德他人评价 -->
    <!-- 同学 -->
         <tr>
         <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
	   <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-2"]["totalStudentNum"][gradeyear] }</td>
	   </c:forEach>
	    </tr>
	    <tr>
	      <td>已完成学生数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-2"]["overStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    <tr>
	      <td>完成百分比（%）</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-2"]["percentOverStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	                         <tr>
	    <td>已填写条目数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-2"]["finishedTotalCount"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	     <!-- 同学 -->
	         <!-- 教师 -->
		     <tr>
		      <td rowspan="4">教师</td>
		      <td>学生总人数</td>
		   <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas["3020-3"]["totalStudentNum"][gradeyear] }</td>
		   </c:forEach>
		    </tr>
		    <tr>
		      <td>已完成学生数</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas["3020-3"]["overStudentNum"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		    <tr>
		      <td>完成百分比（%）</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas["3020-3"]["percentOverStudentNum"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		                         <tr>
		    <td>已填写条目数</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas["3020-3"]["finishedTotalCount"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		    <!-- 教师 -->
	         <!-- 家长 -->
         <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
	   <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-5"]["totalStudentNum"][gradeyear] }</td>
	   </c:forEach>
	    </tr>
	    <tr>
	      <td>已完成学生数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-5"]["overStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    <tr>
	      <td>完成百分比（%）</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-5"]["percentOverStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    	                         <tr>
	    <td>已填写条目数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas["3020-5"]["finishedTotalCount"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	     <!--家长 -->
    <!-- 思想道德他人评价 -->
    <!-- 思想道德记录袋 -->
     <tr>
      <td rowspan="5">思想道德记录袋</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3030"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3030"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3030"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1004"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3030"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["3030"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 思想道德记录袋 -->
    <!-- 思想道德 end -->
    <!-- 学业成就 start -->
         <!-- 学科作品展示 -->
          <tr>
           <td rowspan="25">学业成就</td>
       <td rowspan="5">学科作品展示</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8010"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8010"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8010"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8010"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8010"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td rowspan="4">课程评语</td>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9999"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9999"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9999"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9999"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8020-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8020-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8020-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8020-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 学业成就他人评价 -->
    
    <!-- 同学 -->
    <tr>
     <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-2"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-2"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-2"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-2"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 教师 -->
    <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-3"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-3"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-3"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-3"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 家长 -->
    <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1005"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["8040-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <!-- 学业成就他人评价 -->

	 <!-- 学业成就 end -->
	  <!-- 合作与交流 start -->
    <tr>
      <td rowspan="21">合作与交流</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-2"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-2"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-2"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-2"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-3"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-3"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-3"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-3"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4020-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">合作与交流行为记录袋</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4030"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4030"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4030"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1006"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4030"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["4030"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流 end -->
    <!-- 运动与健康  start-->
        <tr>
      <td rowspan="20">运动与健康</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 运动与健康他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-2"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-2"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-2"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-2"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-3"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-3"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-3"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-3"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5020-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 体质健康-->
            <tr>
      <td rowspan="4">体质健康</td>
      <td rowspan="4">云平台提取</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5050"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5050"]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1007"></a></td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5050"]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["5050"]["finishedTotalCount"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <!-- 运动与健康 end -->
    <!-- 审美与表现  start-->
     <tr>
      <td rowspan="21">审美与表现</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6010-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6010-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6010-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6010-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 审美与表现他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-2"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-2"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-2"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-2"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-3"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-3"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-3"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-3"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6020-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">审美与表现记录袋</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6030"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6030"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6030"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1008"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6030"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["6030"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 审美与表现  end-->
    <!-- 综合实践活动 start -->
    <!-- 社区服务与实践活动基本情况 -->
                <tr>
      <td rowspan="15">综合实践活动</td>
      <td rowspan="5">研究性学习</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9010"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9010"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9010"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9010"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
          <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9010"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                <tr>
      <td rowspan="5">社区服务</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9020"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9020"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9020"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9020"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
          <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9020"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                    <tr>
      <td rowspan="5">社会实践活动</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9030"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9030"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9030"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数<a id="1009"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9030"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
          <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["9030"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 综合实践活动 end -->
	<!-- 个性发展 start -->
	 <tr>
     <td rowspan="30">个性与发展</td>
      <td rowspan="4">基本情况</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7010"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7010"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7010"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7010"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7020-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7020-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7020-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7020-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-2"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-2"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-2"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-2"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-3"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-3"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-3"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-3"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-5"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-5"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-5"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7030-5"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td rowspan="5">个性发展过程</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7040-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7040-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7040-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7040-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7040-1"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">特长与成果展示</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7050-1"]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7050-1"]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7050-1"]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7050-1"]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas["7050-1"]["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
	
	<!-- 个性发展 end -->
    
</table>
<!-- </div> -->
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
