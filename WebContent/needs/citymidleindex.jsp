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
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
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
function exportExcel()
{
	var termId =$$("#termId option:selected").val();
	var gradeyear =$$("#gradeid option:selected").val();
	var schoolname = $$("#schoolName").val();
	var termName = $$("#termId option:selected").text();
	var discode = $$("#discode option:selected").val();
	dStatus = uuid(); 
	window.location.href="${ctx}/export/RequirdCountExport.a?termId="+termId+"&discode="+discode+ "&dStatus=" + dStatus+"&gradeyear="+gradeyear+"&schoolname="+schoolname+"&termName="+termName;
	ShowDiv(); 
	timeId = setInterval(queryDownStatus,1000);
}
</script>
</head>
<body>
<div class="dangqianwz" style="z-index: 1001;position: fixed;width: 99%">
<form action="${ctx}/needs/CityNeedAppraisesAction.a?queryData" method="post">
 	<span class="fl">当前位置：必填数据填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<input type="hidden" name='termName' id='termName' value="${termName}"/>
   	<span >区县：
      <select name="discode" id="discode" class="wenziliebiao100">
      	<app:discode discode="${discode}"/>
      </select>
      </span>&nbsp;&nbsp;&nbsp;
  	<span >学校：
      <input id="schoolName" name="schoolName" type="text" class="wenben120" value="${schoolName}"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span >届别：
     <select name="gradeid" id="gradeid" class="wenziliebiao100" onchange="changGrade(this.value);">
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
		  <select>
          </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <span ><input type="submit" value="查 询" class="button ml10" onclick="submitdata();"/></span>&nbsp;&nbsp;&nbsp;
          <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
</form>
</div>
<div id="tjb_main" style="overflow: visible;">
  <div class="top" style="width:1924px">
  <table border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="46">
    <c:if test="${empty gradeid}">
      <div class="tishi_left fl">***届学生***学期必填数据填写情况
        </div>
     </c:if>
     <c:if test="${not empty gradeid}">
      <div style="height:60px;overflow:hidden;float:left;">${gradeid}届学生${termName}必填数据填写情况
        </div>
     </c:if>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="100" rowspan="4" >学校</td>
     <td colspan="6">新学期伊始的我</td>
     <td colspan="9">学期结束的我</td>
     <td colspan="3">思想道德</td>
     <td colspan="6">学业成就</td>
     <td colspan="3">合作与交流</td>
     <td colspan="3">运动与健康</td>
     <td colspan="3">审美与表现</td>
     <td colspan="3">综合实践活动</td>
     <td colspan="6">个性发展</td>
     </tr>
   <tr class="biaotou_zi">
     <td colspan="3">自我评价</td>
     <td colspan="3">我的发展目标</td>
     <td colspan="3">自我评价</td>
     <td colspan="3">班主任评语</td>
     <td colspan="3">家长期望和寄语</td>
     <td colspan="3">自我评价</td>
     <td colspan="3">自我评价</td>
     <td colspan="3">课程评语</td>
	 <td colspan="3">他人评价</td>
     <td colspan="3">体质健康</td>
     <td colspan="3">审美与表现记录袋</td>
     <td colspan="3">研究性学习基本情况</td>
     <td colspan="3">自我评价</td>
     <td colspan="3">特长与成果展示</td>
     </tr>
  <tr class="biaotou_zi">
  	<td colspan="3">学生本人</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">班主任</td>
  	<td colspan="3">家长</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">教师</td>
  	<td colspan="3">同学</td>
  	<td colspan="3">云平台提取</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">学生本人</td>
  	<td colspan="3">学生本人</td>
	<td colspan="3">学生本人</td>
  	</tr>
  <tr class="biaotou_zi">
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
	<td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    </tr>
    <!-- </table>
    </div>
    <div style="height:295px;overflow-y:scroll;width:1924px">
    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"> -->
    <c:forEach items="${datas}" var="titem" varStatus="count">
		<tr>
			  <td rowspan="1" class="biaoge" width="100">${titem.key}</td>
			  <td width="40">${titem.value[START_ZWPJ]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[START_ZWPJ]["overStudentNum"]}</td>
			  <td width="40">${titem.value[START_ZWPJ]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[START_WDFZMB]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[START_WDFZMB]["overStudentNum"]}</td>
			  <td width="40">${titem.value[START_WDFZMB]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[END_ZWPJ]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[END_ZWPJ]["overStudentNum"]}</td>
			  <td width="40">${titem.value[END_ZWPJ]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[END_BZRPY]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[END_BZRPY]["overStudentNum"]}</td>
			  <td width="40">${titem.value[END_BZRPY]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[END_JZPYQW]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[END_JZPYQW]["overStudentNum"]}</td>
			  <td width="40">${titem.value[END_JZPYQW]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[SX_ZWPJ]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[SX_ZWPJ]["overStudentNum"]}</td>
			  <td width="40">${titem.value[SX_ZWPJ]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[XY_ZWPJ]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[XY_ZWPJ]["overStudentNum"]}</td>
			  <td width="40">${titem.value[XY_ZWPJ]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[XY_TRPJ_KECHENG_PINGYU]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[XY_TRPJ_KECHENG_PINGYU]["overStudentNum"]}</td>
			  <td width="40">${titem.value[XY_TRPJ_KECHENG_PINGYU]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[HZ_TRPJ_TX]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[HZ_TRPJ_TX]["overStudentNum"]}</td>
			  <td width="40">${titem.value[HZ_TRPJ_TX]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[TYPE_YDJKTZJK]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[TYPE_YDJKTZJK]["overStudentNum"]}</td>
			  <td width="40">${titem.value[TYPE_YDJKTZJK]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[SMYBX_JLD]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[SMYBX_JLD]["overStudentNum"]}</td>
			  <td width="40">${titem.value[SMYBX_JLD]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[ZHSJ_YJXX_JBQK]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[ZHSJ_YJXX_JBQK]["overStudentNum"]}</td>
			  <td width="40">${titem.value[ZHSJ_YJXX_JBQK]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_ZWPJ]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_ZWPJ]["overStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_ZWPJ]["percentOverStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_CGZS]["totalStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_CGZS]["overStudentNum"]}</td>
			  <td width="40">${titem.value[GXFZ_CGZS]["percentOverStudentNum"]}</td>
		   </tr>
</c:forEach>
<c:if test="${datas!= null&& fn:length(datas) gt 0 }">
  <tr>
    <td colspan="1" class="biaoge">合计</td>
    <td>${tongjiDatas[START_ZWPJ]["totalStudentNum"]}</td>
    <td>${tongjiDatas[START_ZWPJ]["overStudentNum"]}</td>
    <td>${tongjiDatas[START_ZWPJ]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[START_WDFZMB]["totalStudentNum"]}</td>
    <td>${tongjiDatas[START_WDFZMB]["overStudentNum"]}</td>
    <td>${tongjiDatas[START_WDFZMB]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[END_ZWPJ]["totalStudentNum"]}</td>
    <td>${tongjiDatas[END_ZWPJ]["overStudentNum"]}</td>
    <td>${tongjiDatas[END_ZWPJ]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[END_BZRPY]["totalStudentNum"]}</td>
    <td>${tongjiDatas[END_BZRPY]["overStudentNum"]}</td>
    <td>${tongjiDatas[END_BZRPY]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[END_JZPYQW]["totalStudentNum"]}</td>
    <td>${tongjiDatas[END_JZPYQW]["overStudentNum"]}</td>
    <td>${tongjiDatas[END_JZPYQW]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[SX_ZWPJ]["totalStudentNum"]}</td>
    <td>${tongjiDatas[SX_ZWPJ]["overStudentNum"]}</td>
    <td>${tongjiDatas[SX_ZWPJ]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[XY_ZWPJ]["totalStudentNum"]}</td>
    <td>${tongjiDatas[XY_ZWPJ]["overStudentNum"]}</td>
    <td>${tongjiDatas[XY_ZWPJ]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[XY_TRPJ_KECHENG_PINGYU]["totalStudentNum"]}</td>
    <td>${tongjiDatas[XY_TRPJ_KECHENG_PINGYU]["overStudentNum"]}</td>
    <td>${tongjiDatas[XY_TRPJ_KECHENG_PINGYU]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[HZ_TRPJ_TX]["totalStudentNum"]}</td>
    <td>${tongjiDatas[HZ_TRPJ_TX]["overStudentNum"]}</td>
    <td>${tongjiDatas[HZ_TRPJ_TX]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[TYPE_YDJKTZJK]["totalStudentNum"]}</td>
    <td>${tongjiDatas[TYPE_YDJKTZJK]["overStudentNum"]}</td>
    <td>${tongjiDatas[TYPE_YDJKTZJK]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[SMYBX_JLD]["totalStudentNum"]}</td>
    <td>${tongjiDatas[SMYBX_JLD]["overStudentNum"]}</td>
    <td>${tongjiDatas[SMYBX_JLD]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[ZHSJ_YJXX_JBQK]["totalStudentNum"]}</td>
    <td>${tongjiDatas[ZHSJ_YJXX_JBQK]["overStudentNum"]}</td>
    <td>${tongjiDatas[ZHSJ_YJXX_JBQK]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_ZWPJ]["totalStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_ZWPJ]["overStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_ZWPJ]["percentOverStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_CGZS]["totalStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_CGZS]["overStudentNum"]}</td>
    <td>${tongjiDatas[GXFZ_CGZS]["percentOverStudentNum"]}</td>
    </tr>
    </c:if>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
