<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 新学期伊始的我=》自我评价 -->
<un:bind var="START_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TERMS_BEGIN_ME"/>
<!--新学期伊始的我=》我的发展目标-->
<un:bind var="START_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="DEVELOP_TARGET_ME"/>	
<!--学期结束的我=》自我评价-->
<un:bind var="END_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TERMS_END_ME"/>
<!--学期结束的我=》班主任评语-->
<un:bind var="END_BZRPY" type="com.flyrish.hades.common.Constant"
	field="CHARGE_TEACHER_APPRAISAL"/>
<!--学期结束的我=》家长评语和期望-->
<un:bind var="END_JZPYQW" type="com.flyrish.hades.common.Constant"
	field="PRAENTS_APPRAISAL_EXPECT"/>
<!--思想道德=》自我评价-->
<un:bind var="SX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_SELF_APPRAISAL"/>
<!--思想道德=》教师-->
<un:bind var="SX_TEACHER_PJ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_TEACHER_APPRAISAL"/>
<!--思想道德=》同学-->
<un:bind var="SX_CLASSMATES_PJ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_CLASSMATES_APPRAISAL"/>
<!--思想道德=》家长-->
<un:bind var="SX_PARENT_PJ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_PARENT_APPRAISAL"/>
<!--学业成就=》自我评价-->
<un:bind var="XY_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="WORKS_SELF_APPRAISAL"/>
<!--学业成就=》课程评语-->
<un:bind var="XY_TRPJ_KECHENG_PINGYU" type="com.flyrish.hades.common.Constant"
	field="WORKS_SUBJECT_APPRAISAL"/>
<!--合作交流=》同学评价-->
<un:bind var="HZ_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_CLASSMATES_APPRAISAL"/>
<!--审美与表现=》审美与表现记录袋-->
<un:bind var="SMYBX_JLD" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_RECORD_BAG"/>
<!--综合实践活动-研究性学习-基本情况-->
<un:bind var="ZHSJ_YJXX_JBQK" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_BASEINFO_1"/>
<!--个性发展=>自我评价-->
<un:bind var="GXFZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_SELF_APPRAISAL"/>	
<!--个性发展=》特长与成果展示-->
<un:bind var="GXFZ_CGZS" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_RECORD_BAG"/>
<!--运动与健康=》体质健康-->
<un:bind var="TYPE_YDJKTZJK" type="com.flyrish.hades.common.Constant"
	field="PLAY_PHYSCIAL_HEALTH"/>
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
<div class="dangqianwz" style="padding-top: 12px;z-index:1001;width: 99%;position: fixed;">
<form id="form" action="${ctx}/totalAppraisalStatics/TotalAppraisalStaticsAction.a" method="post">
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
<div id="tjb_main" style="overflow: visible;">
   <!--  <div class="top" style="padding-bottom: 0px;height: 20%"> -->
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
  	<!-- </table>
	</div>
<div style="top:110px;overflow-x:hidden;margin-bottom: 39px;height:360px;margin-right:-16px;">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"> -->
  <tr>
    <td rowspan="8" width="10%">新学期伊始的我</td>
    <td rowspan="4" width="10%">自我评价</td>
    <td rowspan="4" width="10%">学生本人</td>
   <td width="15%">学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas[START_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
   </tr>
  <tr>
    <td width="15%">已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas[START_ZWPJ]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_ZWPJ]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
      <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_ZWPJ]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td rowspan="4">我的发展目标</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）<a id="1002"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    
    
    <!-- 新学期伊始的我 end -->
    <!-- 学期结束时的我 start -->
    <tr>
    <td rowspan="12">学期结束时的我</td>
    <td rowspan="4">自我评价</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_ZWPJ]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_ZWPJ]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_ZWPJ]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td rowspan="4">班主任评语</td>
    <td rowspan="4">班主任</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_BZRPY]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_BZRPY]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_BZRPY]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
             <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_BZRPY]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td rowspan="4">家长期望和寄语</td>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1003"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                 <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["finishedTotalCount"][gradeyear] }</td>
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
	      <td>${datas[SX_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SX_ZWPJ]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SX_ZWPJ]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
                     <tr>
    <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SX_ZWPJ]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 思想道德他人评价 -->
    <!-- 同学 -->
         <tr>
         <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
	   <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['32']["totalStudentNum"][gradeyear] }</td>
	   </c:forEach>
	    </tr>
	    <tr>
	      <td>已完成学生数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['32']["overStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    <tr>
	      <td>完成百分比（%）</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['32']["percentOverStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	                         <tr>
	    <td>已填写条目数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['32']["finishedTotalCount"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	     <!-- 同学 -->
	         <!-- 教师 -->
		     <tr>
		      <td rowspan="4">教师</td>
		      <td>学生总人数</td>
		   <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas['33']["totalStudentNum"][gradeyear] }</td>
		   </c:forEach>
		    </tr>
		    <tr>
		      <td>已完成学生数</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas['33']["overStudentNum"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		    <tr>
		      <td>完成百分比（%）</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas['33']["percentOverStudentNum"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		                         <tr>
		    <td>已填写条目数</td>
		    <c:forEach items="${gradeYears}" var="gradeyear">
			      <td>${datas['33']["finishedTotalCount"][gradeyear] }</td>
		   	</c:forEach>
		    </tr>
		    <!-- 教师 -->
	         <!-- 家长 -->
         <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
	   <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['34']["totalStudentNum"][gradeyear] }</td>
	   </c:forEach>
	    </tr>
	    <tr>
	      <td>已完成学生数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['34']["overStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    <tr>
	      <td>完成百分比（%）</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['34']["percentOverStudentNum"][gradeyear] }</td>
	   	</c:forEach>
	    </tr>
	    	                         <tr>
	    <td>已填写条目数</td>
	    <c:forEach items="${gradeYears}" var="gradeyear">
		      <td>${datas['34']["finishedTotalCount"][gradeyear] }</td>
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
	      <td>${datas['35']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['35']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['35']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1004"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['35']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['35']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 思想道德记录袋 -->
    <!-- 思想道德 end -->
    <!-- 学业成就 start -->
    <tr>
      <td rowspan="21">学业成就</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_ZWPJ]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_ZWPJ]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_ZWPJ]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 学业成就他人评价 -->
    
    <!-- 同学 -->
    <tr>
     <td rowspan="8">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['45']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['45']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['45']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['45']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 家长 -->
    <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['46']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['46']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['46']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['46']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师 -->
    <tr>
     <td rowspan="4">课程评语</td>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_TRPJ_KECHENG_PINGYU]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_TRPJ_KECHENG_PINGYU]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_TRPJ_KECHENG_PINGYU]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[XY_TRPJ_KECHENG_PINGYU]["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <!-- 学业成就他人评价 -->
     <!-- 学科作品展示 -->
          <tr>
       <td rowspan="5">学科作品展示</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['43']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['43']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['43']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1005"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['43']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['43']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
	 <!-- 学业成就 end -->
	  <!-- 合作与交流 start -->
    <tr>
      <td rowspan="21">合作与交流</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['51']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['51']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['51']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['51']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['52']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['52']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['52']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['52']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['53']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['53']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['53']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['53']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['54']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['54']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['54']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['54']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">合作与交流行为记录袋</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['55']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['55']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['55']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1006"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['55']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['55']["attacheCount"][gradeyear] }</td>
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
	      <td>${datas['61']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['61']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['61']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['61']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 运动与健康他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['62']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['62']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['62']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['62']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['63']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['63']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['63']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['63']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['64']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['64']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['64']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['64']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 体质健康-->
            <tr>
      <td rowspan="4">体质健康</td>
      <td rowspan="4">云平台提取</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1007"></td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["finishedTotalCount"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <!-- 运动与健康 end -->
    <!-- 审美与表现  start-->
     <tr>
      <td rowspan="21"></a>审美与表现</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['71']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['71']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['71']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['71']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 审美与表现他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['72']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['72']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['72']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['72']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['73']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['73']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['73']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['73']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['74']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['74']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['74']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['74']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">审美与表现记录袋</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['75']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['75']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['75']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数<a id="1008"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['75']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['75']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 审美与表现  end-->
    <!-- 综合实践活动 start -->
    <!-- 研究性学习基本情况 -->
        <tr>
      <td rowspan="22">综合实践活动</td>
      <td rowspan="4">研究性学习基本情况</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['81']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['81']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['81']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['81']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 研究性学习研究成果 -->
            <tr>
      <td rowspan="5">研究性学习研究成果</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['82']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['82']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['82']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['82']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
          <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['82']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 研究性学习自我评价 -->
                <tr>
      <td rowspan="4">研究性学习自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['83']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['83']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['83']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['83']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 社区服务与实践活动基本情况 -->
                <tr>
      <td rowspan="5">社区服务与实践活动基本情况</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['84']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['84']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['84']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['84']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
         <tr>
          <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['84']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 社区服务与实践活动自我评价 -->
                <tr>
      <td rowspan="4">社区服务与实践活动自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['85']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['85']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）<a id="1009"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['85']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
     <tr>
          <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['85']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 综合实践活动 end -->
	<!-- 个性发展 start -->
	 <tr>
     <td rowspan="21">个性与发展</td>
      <td rowspan="4">自我评价</td>
      <td rowspan="4">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['91']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['91']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['91']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['91']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流他人评价 -->
        <!-- 同学评价 -->
        <tr>
        <td rowspan="12">他人评价</td>
      <td rowspan="4">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['92']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['92']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['92']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['92']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <!-- 教师评价 -->
        <tr>
      <td rowspan="4">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['93']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['93']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['93']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['93']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 家长评价 -->
            <tr>
      <td rowspan="4">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['94']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['94']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['94']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['94']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <!-- 合作与交流记录袋 -->
    <tr>
      <td rowspan="5">特长与成果展示</td>
      <td rowspan="5">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['95']["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['95']["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['95']["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>已填写条目数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['95']["finishedTotalCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
        <tr>
      <td>附件数量</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas['95']["attacheCount"][gradeyear] }</td>
   	</c:forEach>
    </tr>
	
	<!-- 个性发展 end -->
    
</table>
<!-- </div> -->
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
