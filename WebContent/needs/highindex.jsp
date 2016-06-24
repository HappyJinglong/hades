<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--新学期伊始的我=》自我评价-->
<un:bind var="START_ZWPJ" type="com.flyrish.hades.common.Constant"
field="TYPE_START_ZWPJ"/>
<!--新学期伊始的我=》我的发展目标-->
<un:bind var="START_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB"/>
<!--学期结束的我=》自我评价-->
<un:bind var="END_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_ZWPJ"/>
<!--学期结束的我=》班主任评语-->
<un:bind var="END_BZRPY" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_BZRPY"/>
<!--学期结束的我=》家长评语和期望-->
<un:bind var="END_JZPYQW" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_JZPYQW"/>
<!--思想道德=》自我评价-->
<un:bind var="SX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_ZWPJ"/>
<!--学业成就=》课程评语-->
<un:bind var="KE_CHENG_PINGYU" type="com.flyrish.hades.common.Constant"
	field="TYPE_KE_CHENG_PINGYU"/>
<!--合作与交流=》他人评价（只要同学）-->
<un:bind var="HZ_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ"/>
<!--审美与表现=》审美与表现记录袋-->
<un:bind var="SMYBX_JLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_JLD"/>
<!--综合实践活动=》研究性学习-->
<un:bind var="YJXX" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX"/>
<!--个性与发展=》个性发展基本情况-->
<un:bind var="GXFZ_JBQK" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_JBQK"/>
<!--个性与发展=》特长与成果展示-->
<un:bind var="GXFZ_CGZS" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_CGZS"/>
<!--运动与健康=》体质健康-->
<un:bind var="TYPE_YDJKTZJK" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJKTZJK"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>统计表</title>
<script type="text/javascript">
function go(){
	 var typeId = document.getElementById("levelid").value;
	window.location.hash=typeId;
	//alert('dddd');
}
function submitdata(){
	ShowDiv();
}

function exportExcel(){
	var termId = $("#termId option:selected").val();
	var termName = $("#termId option:selected").text();
	dStatus = uuid(); 
	window.location.href = "${ctx}/export/RequirdCountExport.a?termId="+termId+ "&dStatus=" + dStatus+"&termName="+termName;
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
<div class="dangqianwz" style="z-index:1001;padding-top: 14px;position: fixed;width: 99%;">
<form id="form" action="${ctx}/needs/HighNeedAppraisesAction.a?queryData" method="post">
 	<span class="fl">当前位置：必填数据填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
             </select> 
             </span>&nbsp;&nbsp;&nbsp;
            <span >学期：
     		<select  name="termId" id="termId" class="wenziliebiao100">
				<app:newTermId termId="${termId}"/>
			<select>
             </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ><input type="submit" value="查 询" class="button ml10" onclick="submitdata();"/></span>&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
</form>
</div>
<div id="tjb_main" style="overflow: visible;">
  <!-- <div class="top" style="padding-bottom: 0px;height: 20%;"> -->
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="22">
    <a id="1001"></a>
      <div class="tishi_left fl">必填数据填写情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="10%" rowspan="1">栏目</td>
     <td width="10%" rowspan="1">二级栏目</td>
     <td width="10%" rowspan="1">填写人</td>
     <td width="15%" rowspan="1">完成情况</td>
     <c:forEach items="${gradeYears}" var="gradeyear">
	     <td colspan="1" width="15%">${gradeyear}届</td>
     </c:forEach>
   </tr>
	<!-- </table>
	</div>
<div  id="addH" style="height: 500px"> 
<div style="height:100%;overflow-y:auto;margin-right:-16px;">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">-->
  <tr>
    <td rowspan="6" width="10%">新学期伊始的我</td>
    <td rowspan="3" width="10%">自我评价</td>
    <td rowspan="3" width="10%">学生本人</td>
   <td width="15%">学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas[START_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
  <tr>
    <td width="15%">已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas[START_ZWPJ]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td width="15%">完成百分比（%）</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td width="15%">${datas[START_ZWPJ]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
  <tr>
    <td rowspan="3">我的发展目标</td>
    <td rowspan="3">学生本人</td>
    <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
  <tr>
    <td>已完成学生数<a id="1002"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
     <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[START_WDFZMB]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
    <td rowspan="9">学期结束时的我</td>
    <td rowspan="3">自我评价</td>
    <td rowspan="3">学生本人</td>
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
    <td rowspan="3">班主任评语</td>
    <td rowspan="3">班主任</td>
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
      <td rowspan="3">家长评语和期望</td>
      <td rowspan="3">家长</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数<a id="1003"></a></td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["overStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
     <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[END_JZPYQW]["percentOverStudentNum"][gradeyear] }</td>
   	</c:forEach>
    </tr>
    <tr>
      <td rowspan="3">思想道德</td>
      <td rowspan="3">自我评价</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SX_ZWPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数<a id="1004"></a></td>
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
      <td rowspan="3"> 学业成就</td>
      <td rowspan="3">课程评语</td>
      <td rowspan="3">教师</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[KE_CHENG_PINGYU]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数<a id="1005"></a></td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[KE_CHENG_PINGYU]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[KE_CHENG_PINGYU]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="3">合作与交流</td>
      <td rowspan="3">他人评价</td>
      <td rowspan="3">同学</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[HZ_TRPJ]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数<a id="1006"></a></td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[HZ_TRPJ]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[HZ_TRPJ]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="3"> 运动与健康</td>
      <td rowspan="3">体质健康</td>
      <td rowspan="3">云平台提取</td>
      <td>学生总人数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数<a id="1007"></a></td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[TYPE_YDJKTZJK]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="3">审美与表现</td>
      <td rowspan="3">审美与表现记录袋</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SMYBX_JLD]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SMYBX_JLD]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[SMYBX_JLD]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="3"><a id="1008"></a>综合实践活动</td>
      <td rowspan="3">研究性学习</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[YJXX]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[YJXX]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[YJXX]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="6"><a id="1009"></a>个性与发展</td>
      <td rowspan="3">个性发展基本情况</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_JBQK]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_JBQK]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_JBQK]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td rowspan="3">特长与成果展示</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
    <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_CGZS]["totalStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_CGZS]["overStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
   <c:forEach items="${gradeYears}" var="gradeyear">
	      <td>${datas[GXFZ_CGZS]["percentOverStudentNum"][gradeyear] }</td>
   </c:forEach>
    </tr>
</table>
<!-- </div> -->
</div>
<%@ include file="/common/div.jsp"%> 
</div>
</body>
</html>
