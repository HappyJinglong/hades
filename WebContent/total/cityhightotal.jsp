<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<un:bind var="START_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_ZWPJ"/>
<un:bind var="START_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB"/>
<un:bind var="END_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_ZWPJ"/>
<un:bind var="END_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_WDFZMB"/>
	
<un:bind var="END_BZRPY" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_BZRPY"/>
<un:bind var="END_JZPYQW" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_JZPYQW"/>
<un:bind var="SX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_ZWPJ"/>
<un:bind var="SX_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_TRPJ"/>
	
<un:bind var="SX_TRPJ_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_TRPJ_C"/>
<un:bind var="SX_TRPJ_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_TRPJ_T"/>
<un:bind var="SX_TRPJ_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_TRPJ_P"/>
	
<un:bind var="SXJLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SXJLD"/>
<un:bind var="SXDDGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SXDDGJPJ"/>
<un:bind var="HZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_ZWPJ"/>
<un:bind var="HZ_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ"/>

<un:bind var="HZ_TRPJ_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ_C"/>
<un:bind var="HZ_TRPJ_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ_T"/>
<un:bind var="HZ_TRPJ_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ_P"/>
	
<un:bind var="HZ_JLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_JLD"/>
<un:bind var="HZJLGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZJLGJPJ"/>
<un:bind var="YDJK_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_ZWPJ"/>
<un:bind var="YDJK_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_TRPJ"/>

<un:bind var="YDJK_TRPJ_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_TRPJ_C"/>
<un:bind var="YDJK_TRPJ_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_TRPJ_T"/>
<un:bind var="YDJK_TRPJ_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_TRPJ_P"/>
<un:bind var="YDJKGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJKGJPJ"/>
<un:bind var="SMYBX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_ZWPJ"/>
<un:bind var="SMYBX_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_TRPJ"/>
<un:bind var="SMYBX_TRPJ_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_TRPJ_C"/>
<un:bind var="SMYBX_TRPJ_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_TRPJ_T"/>
<un:bind var="SMYBX_TRPJ_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_TRPJ_P"/>
	
<un:bind var="SMYBX_JLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_JLD"/>
<!-- 运动与健康--体质健康	 -->
<un:bind var="YDJKTZJK" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJKTZJK"/>	
	
	
<un:bind var="SMYBXGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBXGJPJ"/>
<un:bind var="YJXX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX_ZWPJ"/>
<un:bind var="YJXX_XXNR" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX_XXNR"/>
<un:bind var="SQFU_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU_ZWPJ"/>
	
<un:bind var="SQFU_FWNR" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU_FWNR"/>
<un:bind var="SJHD_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SJHD_ZWPJ"/>
<un:bind var="SJHD_JBQK" type="com.flyrish.hades.common.Constant"
	field="TYPE_SJHD_JBQK"/>
<un:bind var="GXFZ_JBQK" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_JBQK"/>
	
<un:bind var="GXFZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_ZWPJ"/>
<un:bind var="GXFZ_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_TRPJ"/>
	
<un:bind var="GXFZ_TRPJ_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_TRPJ_C"/>
<un:bind var="GXFZ_TRPJ_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_TRPJ_T"/>
<un:bind var="GXFZ_TRPJ_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_TRPJ_P"/>

<un:bind var="GXFZGC" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZGC"/>
<un:bind var="GXFZ_CGZS" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_CGZS"/>

<un:bind var="XY_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_ZWPJ"/>
<un:bind var="XY_GCJL" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_GCJL"/>
<un:bind var="XY_XFRD" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_XFRD"/>
<un:bind var="XY" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY"/>
	
<un:bind var="XY_C" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_C"/>
<un:bind var="XY_T" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_T"/>
<un:bind var="XY_P" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_P"/>
	
<un:bind var="START_WDFZMB_MYSELF" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB_MYSELF"/>
<un:bind var="START_WDFZMB_PRATENT" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB_PRATENT"/>
	
<un:bind var="me_apprasialidentify" type="com.flyrish.hades.common.Constant"
	field="me_apprasialidentify"/>
	
<un:bind var="PARENT" type="com.flyrish.hades.common.Constant"
	field="APPRASER_PARENT"/>
<un:bind var="STUDENT" type="com.flyrish.hades.common.Constant"
	field="APPRASER_STUDENT"/>
<un:bind var="TEACHER" type="com.flyrish.hades.common.Constant"
	field="APPRASER_TEACHER"/>
<un:bind var="MASTER" type="com.flyrish.hades.common.Constant"
	field="APPRASER_MASTER"/>
	
	
<un:bind var="YJXX" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX"/>
<un:bind var="SQFU" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU"/>
<un:bind var="SHSJHD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SHSJHD"/>
<un:bind var="KE_CHENG_PINGYU" type="com.flyrish.hades.common.Constant"
	field="TYPE_KE_CHENG_PINGYU"/>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: normal;
}
.biaotou_zi td {
	font-weight: bold;
}
#tjb_main .top .biaoge tr td {
	font-weight: bold;
}
.biaotou_zi {
}
.biaotou_zi {
	font-weight: bold;
}
.biaotou_zi {
	font-weight: normal;
}
.biaotou_zi .biaotou_zi {
	font-weight: bold;
}
</style>
<title>统计表</title>

</head>
<script type="text/javascript">
var $$= jQuery.noConflict();
function submitdata(){
	ShowDiv();
	var sel=document.getElementById('termId');//select元素的id 
	var index=sel.selectedIndex;//获取被选中的option的索引 
	var textsel= sel.options[index].text;//获取相应的option的内容 
	$$("#termName").val(textsel);
}
function changGrade(gradeyear){
	ShowUserFuncDwr.queryTermDtoByGradeYear(gradeyear,function(data){
			DWRUtil.removeAllOptions('termId');
		if(null != data && data.length > 0){
			DWRUtil.addOptions('termId',data,'termid','termname');
		}
	});
}
function exportExcel()
{
	var schoolName = $$("#schoolName").val();
	var termId = $$("#termId option:selected").val();
	var termName = $$("#termId option:selected").text();
	var discodeName = $$("#discode option:selected").text();
	var discode= $$("#discode option:selected").val();
	var gradeid = $$("#gradeid option:selected").val();
	var gradeName = $$("#gradeid option:selected").text();
	dStatus = uuid(); 
	window.location.href = "${ctx}/export/TotalCountExportAction.a?discodeName="
				+ discodeName
				+ "&dStatus=" 
				+ dStatus
				+ "&gradeName="
				+ gradeName
				+ "&gradeid="
				+ gradeid
				+ "&schoolName="
				+ schoolName
				+ "&termName="
				+ termName + "&termId=" + termId + "&discode=" + discode;
	ShowDiv(); 
	timeId = setInterval(queryDownStatus,1000);
	}
var dStatus = "";
var timeId = "";
var count = 0;
function queryDownStatus(){
	 $$("#MyDiv").css("height","120%");
	 $$("#timeCount").html("已耗时间"+(++count)+"秒");
	 $$.ajax({
	     url: "${ctx}/export/shoolFillCount.a?queryDownLoadStatus",
	     type: "POST",
	     data: {
	    	 dStatus:dStatus
	     },
	     success: function(data) {
	    	if(data.val=="1"){
	    		$$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		 $$("#timeCount").html("正在导出数据...");
	    	}else if(data.val=="2"){//导出文件失败
	    		$$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		 $$("#timeCount").html("正在导出数据...");
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
<body>
<div class="dangqianwz" style="z-index: 1001;position: fixed;width: 99%">
  <form action="${ctx}/total/CityTotalDatasNumAction.a?queryData" method="post">
  <span class="fl" style="line-height: 28px;">当前位置：数据填写总体情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <span >区县：
		      <select id="discode" name="discode" class="wenziliebiao100">
		      	<app:discode discode="${discode}"/>
		     </select>
      </span>&nbsp;&nbsp;&nbsp;
  <span >学校：
      <input id="schoolName" name="schoolName" type="text" class="wenben120" value="${schoolName}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="hidden" name='termName' id='termName' value="${termName}"/>
             <span >届别：
     		<select id="gradeid" name="gradeid" class="wenziliebiao100" onchange="changGrade(this.value);">
               	<app:gradeYear gradeYear="${gradeid}"/>
            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span >学期：
     	<select  name="termId" id="termId" class="wenziliebiao100">
     			<c:if test="${empty gradeid}">
				    <app:newTermId termId="${termId}"/>
				</c:if>
				<c:if test="${not empty gradeid}">
					<c:forEach items="${termDtos}" var="item">
							<option value="${item.termid}" <c:if test="${item.termid eq termId}">selected="selected"</c:if>>${item.termname}</option>
					</c:forEach>
				</c:if>
			<select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ><input type="submit" value="查 询" class="button ml10" onclick="submitdata();"/></span>&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
    </form>
</div>
<div id="tjb_main" style="overflow:visible;">
  <div class="top">
  <table width="600%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="185">
       <c:if test="${empty gradeid}">
      <div class="tishi_left fl">***届学生***学期数据填写总体情况
        </div>
     </c:if>
     <c:if test="${not empty gradeid}">
      <div style="height:60px;overflow:hidden;float:left;">${gradeid}届学生${termName}数据填写总体情况
        </div>
     </c:if>
    </td>
	
    </tr>
   <tr class="biaotou_zi">
     <td width="100" rowspan="4" >学校</td>
     <td colspan="12">新学期伊始的我</td>
     <td colspan="16">学期结束的我</td>
     <td colspan="21">思想道德</td>
     <td colspan="25">学业成就</td>
     <td colspan="21">合作与交流</td>
     <td colspan="20">运动与健康</td>
     <td colspan="21">审美与表现</td>
     <td colspan="15">综合实践活动</td>
     <td colspan="30">个性发展</td>
     </tr>
   <tr class="biaotou_zi">
     <td colspan="4">自我评价</td>
     <td colspan="4">我的发展目标</td>
     <td colspan="4">家长的期望</td>
	 <td colspan="4">自我评价</td>
	 <td colspan="4">我的发展目标</td>
     <td colspan="4">班主任评语</td>
     <td colspan="4">家长评语和期望</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
     <td colspan="5">思想道德记录袋</td>
	 <td colspan="5">学科作品展示</td>
	 <td colspan="4">课程评语</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
     <td colspan="5">合作与交流行为记录袋</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
     <td colspan="4">体质健康</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
     <td colspan="5">审美与表现记录袋</td>
     <td colspan="5">研究性学习</td>
     <td colspan="5">社区服务</td>
	 <td colspan="5">社区实践活动</td>
	 <td colspan="4">基本情况</td>
     <td colspan="4">自我评价</td>
     <td colspan="12">他人评价</td>
	 <td colspan="5">个性发展过程</td>
     <td colspan="5">特长与成果展示</td>
     </tr>
  <tr class="biaotou_zi">
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">学生本人</td>
	<td colspan="4" rowspan="1">学生本人</td>
	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">班主任</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="5" rowspan="1">学生本人</td>
  	<td colspan="5" rowspan="1">学生本人</td>
	<td colspan="4" rowspan="1">教师</td>
	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="5" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="4" rowspan="1">云平台提取</td>
  	<td colspan="4" rowspan="1">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="5" rowspan="1">学生本人</td>
  	<td colspan="5">学生本人</td>
  	<td colspan="5">学生本人</td>
  	<td colspan="5">学生本人</td>
  	<td colspan="4">学生本人</td>
  	<td colspan="4">学生本人</td>
  	<td colspan="4" rowspan="1">同学</td>
  	<td colspan="4" rowspan="1">教师</td>
  	<td colspan="4" rowspan="1">家长</td>
  	<td colspan="5" rowspan="1">学生本人</td>
	<td colspan="5" rowspan="1">学生本人</td>
  	</tr>
  <tr class="biaotou_zi">
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
	<td width="40">附件数量</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <td width="40">附件数量</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
	<td width="40">附件数量</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
	<td width="40">附件数量</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <td width="40">附件数量</td>
     <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <td width="40">附件数量</td>
    <td  width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <td width="40">附件数量</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
	<td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">附件数量</td>
	<td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <td width="40">附件数量</td>
    </tr>
<c:forEach items="${datas}" var="titem">
  <tr>
    <td rowspan="1" class="biaoge">${titem.key}</td>
   <td>${titem.value[START_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[START_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[START_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[START_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[START_WDFZMB_MYSELF]["totalStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_MYSELF]["overStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_MYSELF]["percentOverStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_MYSELF]["overAppriseNum"]}</td>
   <td>${titem.value[START_WDFZMB_PRATENT]["totalStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_PRATENT]["overStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_PRATENT]["percentOverStudentNum"]}</td>
   <td>${titem.value[START_WDFZMB_PRATENT]["overAppriseNum"]}</td>
   <td>${titem.value[END_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[END_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[END_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[END_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[END_WDFZMB]["totalStudentNum"]}</td>
   <td>${titem.value[END_WDFZMB]["overStudentNum"]}</td>
   <td>${titem.value[END_WDFZMB]["percentOverStudentNum"]}</td>
   <td>${titem.value[END_WDFZMB]["overAppriseNum"]}</td>
   <td>${titem.value[END_BZRPY]["totalStudentNum"]}</td>
   <td>${titem.value[END_BZRPY]["overStudentNum"]}</td>
   <td>${titem.value[END_BZRPY]["percentOverStudentNum"]}</td>
   <td>${titem.value[END_BZRPY]["overAppriseNum"]}</td>
   <td>${titem.value[END_JZPYQW]["totalStudentNum"]}</td>
   <td>${titem.value[END_JZPYQW]["overStudentNum"]}</td>
   <td>${titem.value[END_JZPYQW]["percentOverStudentNum"]}</td>
   <td>${titem.value[END_JZPYQW]["overAppriseNum"]}</td>
   <td>${titem.value[SX_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[SX_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[SX_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[SX_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[SX_TRPJ_C]["totalStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_C]["overStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_C]["overAppriseNum"]}</td>
   <td>${titem.value[SX_TRPJ_T]["totalStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_T]["overStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_T]["overAppriseNum"]}</td>
   <td>${titem.value[SX_TRPJ_P]["totalStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_P]["overStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[SX_TRPJ_P]["overAppriseNum"]}</td>
   <td>${titem.value[SXJLD]["totalStudentNum"]}</td>
   <td>${titem.value[SXJLD]["overStudentNum"]}</td>
   <td>${titem.value[SXJLD]["percentOverStudentNum"]}</td>
   <td>${titem.value[SXJLD]["overAppriseNum"]}</td>
   <td>${titem.value[SXJLD]["attchFileNum"]}</td>
   <td>${titem.value[XY_GCJL]["totalStudentNum"]}</td>
   <td>${titem.value[XY_GCJL]["overStudentNum"]}</td>
   <td>${titem.value[XY_GCJL]["percentOverStudentNum"]}</td>
   <td>${titem.value[XY_GCJL]["overAppriseNum"]}</td>
   <td>${titem.value[XY_GCJL]["attchFileNum"]}</td>
   <td>${titem.value[KE_CHENG_PINGYU]["totalStudentNum"]}</td>
   <td>${titem.value[KE_CHENG_PINGYU]["overStudentNum"]}</td>
   <td>${titem.value[KE_CHENG_PINGYU]["percentOverStudentNum"]}</td>
   <td>${titem.value[KE_CHENG_PINGYU]["overAppriseNum"]}</td>
   <td>${titem.value[XY_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[XY_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[XY_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[XY_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[XY_C]["totalStudentNum"]}</td>
   <td>${titem.value[XY_C]["overStudentNum"]}</td>
   <td>${titem.value[XY_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[XY_C]["overAppriseNum"]}</td>
   <td>${titem.value[XY_T]["totalStudentNum"]}</td>
   <td>${titem.value[XY_T]["overStudentNum"]}</td>
   <td>${titem.value[XY_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[XY_T]["overAppriseNum"]}</td>
   <td>${titem.value[XY_P]["totalStudentNum"]}</td>
   <td>${titem.value[XY_P]["overStudentNum"]}</td>
   <td>${titem.value[XY_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[XY_P]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[HZ_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[HZ_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[HZ_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_TRPJ_C]["totalStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_C]["overStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_C]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_TRPJ_T]["totalStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_T]["overStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_T]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_TRPJ_P]["totalStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_P]["overStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[HZ_TRPJ_P]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_JLD]["totalStudentNum"]}</td>
   <td>${titem.value[HZ_JLD]["overStudentNum"]}</td>
   <td>${titem.value[HZ_JLD]["percentOverStudentNum"]}</td>
   <td>${titem.value[HZ_JLD]["overAppriseNum"]}</td>
   <td>${titem.value[HZ_JLD]["attchFileNum"]}</td>
   <td>${titem.value[YDJK_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[YDJK_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[YDJK_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[YDJK_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_C]["totalStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_C]["overStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_C]["overAppriseNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_T]["totalStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_T]["overStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_T]["overAppriseNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_P]["totalStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_P]["overStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[YDJK_TRPJ_P]["overAppriseNum"]}</td>
   <td>${titem.value[YDJKTZJK]["totalStudentNum"]}</td>
   <td>${titem.value[YDJKTZJK]["overStudentNum"]}</td>
   <td>${titem.value[YDJKTZJK]["percentOverStudentNum"]}</td>
   <td>${titem.value[YDJKTZJK]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[SMYBX_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[SMYBX_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[SMYBX_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_C]["totalStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_C]["overStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_C]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_T]["totalStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_T]["overStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_T]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_P]["totalStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_P]["overStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[SMYBX_TRPJ_P]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_JLD]["totalStudentNum"]}</td>
   <td>${titem.value[SMYBX_JLD]["overStudentNum"]}</td>
   <td>${titem.value[SMYBX_JLD]["percentOverStudentNum"]}</td>
   <td>${titem.value[SMYBX_JLD]["overAppriseNum"]}</td>
   <td>${titem.value[SMYBX_JLD]["attchFileNum"]}</td>
   <td>${titem.value[YJXX]["totalStudentNum"]}</td>
   <td>${titem.value[YJXX]["overStudentNum"]}</td>
   <td>${titem.value[YJXX]["percentOverStudentNum"]}</td>
   <td>${titem.value[YJXX]["overAppriseNum"]}</td>
   <td>${titem.value[YJXX]["attchFileNum"]}</td>
   <td>${titem.value[SQFU]["totalStudentNum"]}</td>
   <td>${titem.value[SQFU]["overStudentNum"]}</td>
   <td>${titem.value[SQFU]["percentOverStudentNum"]}</td>
   <td>${titem.value[SQFU]["overAppriseNum"]}</td>
   <td>${titem.value[SQFU]["attchFileNum"]}</td>
   <td>${titem.value[SHSJHD]["totalStudentNum"]}</td>
   <td>${titem.value[SHSJHD]["overStudentNum"]}</td>
   <td>${titem.value[SHSJHD]["percentOverStudentNum"]}</td>
   <td>${titem.value[SHSJHD]["overAppriseNum"]}</td>
   <td>${titem.value[SHSJHD]["attchFileNum"]}</td>
   <td>${titem.value[GXFZ_JBQK]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_JBQK]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_JBQK]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_JBQK]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZ_ZWPJ]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_ZWPJ]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_ZWPJ]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_C]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_C]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_C]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_T]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_T]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_T]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_P]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_P]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_TRPJ_P]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZGC]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZGC]["overStudentNum"]}</td>
   <td>${titem.value[GXFZGC]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZGC]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZGC]["attchFileNum"]}</td>
   <td>${titem.value[GXFZ_CGZS]["totalStudentNum"]}</td>
   <td>${titem.value[GXFZ_CGZS]["overStudentNum"]}</td>
   <td>${titem.value[GXFZ_CGZS]["percentOverStudentNum"]}</td>
   <td>${titem.value[GXFZ_CGZS]["overAppriseNum"]}</td>
   <td>${titem.value[GXFZ_CGZS]["attchFileNum"]}</td>
   </tr>
 </c:forEach>
   <tr>
    <td colspan="1" class="biaoge">合计</td>
   <td>${tongjiDatas[START_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[START_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[START_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[START_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_MYSELF]["totalStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_MYSELF]["overStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_MYSELF]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_MYSELF]["overAppriseNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_PRATENT]["totalStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_PRATENT]["overStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_PRATENT]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[START_WDFZMB_PRATENT]["overAppriseNum"]}</td>
   <td>${tongjiDatas[END_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[END_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[END_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[END_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[END_WDFZMB]["totalStudentNum"]}</td>
   <td>${tongjiDatas[END_WDFZMB]["overStudentNum"]}</td>
   <td>${tongjiDatas[END_WDFZMB]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[END_WDFZMB]["overAppriseNum"]}</td>
   <td>${tongjiDatas[END_BZRPY]["totalStudentNum"]}</td>
   <td>${tongjiDatas[END_BZRPY]["overStudentNum"]}</td>
   <td>${tongjiDatas[END_BZRPY]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[END_BZRPY]["overAppriseNum"]}</td>
   <td>${tongjiDatas[END_JZPYQW]["totalStudentNum"]}</td>
   <td>${tongjiDatas[END_JZPYQW]["overStudentNum"]}</td>
   <td>${tongjiDatas[END_JZPYQW]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[END_JZPYQW]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SX_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SX_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[SX_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SX_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SX_TRPJ_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SXJLD]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SXJLD]["overStudentNum"]}</td>
   <td>${tongjiDatas[SXJLD]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SXJLD]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SXJLD]["attchFileNum"]}</td>
   <td>${tongjiDatas[XY_GCJL]["totalStudentNum"]}</td>
   <td>${tongjiDatas[XY_GCJL]["overStudentNum"]}</td>
   <td>${tongjiDatas[XY_GCJL]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[XY_GCJL]["overAppriseNum"]}</td>
   <td>${tongjiDatas[XY_GCJL]["attchFileNum"]}</td>
   <td>${tongjiDatas[KE_CHENG_PINGYU]["totalStudentNum"]}</td>
   <td>${tongjiDatas[KE_CHENG_PINGYU]["overStudentNum"]}</td>
   <td>${tongjiDatas[KE_CHENG_PINGYU]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[KE_CHENG_PINGYU]["overAppriseNum"]}</td>
   <td>${tongjiDatas[XY_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[XY_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[XY_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[XY_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[XY_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[XY_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[XY_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[XY_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[XY_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[XY_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[XY_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[XY_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[XY_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[XY_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[XY_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[XY_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[HZ_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[HZ_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[HZ_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[HZ_TRPJ_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_JLD]["totalStudentNum"]}</td>
   <td>${tongjiDatas[HZ_JLD]["overStudentNum"]}</td>
   <td>${tongjiDatas[HZ_JLD]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[HZ_JLD]["overAppriseNum"]}</td>
   <td>${tongjiDatas[HZ_JLD]["attchFileNum"]}</td>
   <td>${tongjiDatas[YDJK_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YDJK_TRPJ_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[YDJKTZJK]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YDJKTZJK]["overStudentNum"]}</td>
   <td>${tongjiDatas[YDJKTZJK]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YDJKTZJK]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_TRPJ_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_JLD]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_JLD]["overStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_JLD]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SMYBX_JLD]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SMYBX_JLD]["attchFileNum"]}</td>
   <td>${tongjiDatas[YJXX]["totalStudentNum"]}</td>
   <td>${tongjiDatas[YJXX]["overStudentNum"]}</td>
   <td>${tongjiDatas[YJXX]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[YJXX]["overAppriseNum"]}</td>
   <td>${tongjiDatas[YJXX]["attchFileNum"]}</td>
   <td>${tongjiDatas[SQFU]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SQFU]["overStudentNum"]}</td>
   <td>${tongjiDatas[SQFU]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SQFU]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SQFU]["attchFileNum"]}</td>
   <td>${tongjiDatas[SHSJHD]["totalStudentNum"]}</td>
   <td>${tongjiDatas[SHSJHD]["overStudentNum"]}</td>
   <td>${tongjiDatas[SHSJHD]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[SHSJHD]["overAppriseNum"]}</td>
   <td>${tongjiDatas[SHSJHD]["attchFileNum"]}</td>
   <td>${tongjiDatas[GXFZ_JBQK]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_JBQK]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_JBQK]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_JBQK]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZ_ZWPJ]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_ZWPJ]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_ZWPJ]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_ZWPJ]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_C]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_C]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_C]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_C]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_T]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_T]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_T]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_T]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_P]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_P]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_P]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_TRPJ_P]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZGC]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZGC]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZGC]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZGC]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZGC]["attchFileNum"]}</td>
   <td>${tongjiDatas[GXFZ_CGZS]["totalStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_CGZS]["overStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_CGZS]["percentOverStudentNum"]}</td>
   <td>${tongjiDatas[GXFZ_CGZS]["overAppriseNum"]}</td>
   <td>${tongjiDatas[GXFZ_CGZS]["attchFileNum"]}</td>
    </tr>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
