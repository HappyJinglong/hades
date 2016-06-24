<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css_new/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<style type="text/css">
.biaotou_zi td {
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function changeTerm(currentObj){
		 $.ajax({
		     url: "${ctx }/appraisalWritedStatics/AppraisalWritedJWAction.a?queryTermInfos",
		     type: "POST",
		     data: {
		    	 years:currentObj.val()
		     },
		     success: function(data) {
		    	 var json = eval(data.val);
		    	 var termInfo = "";
		    	 $("#termId").html("");
		    	 var options = '';
		    	 for(var i = 0;i<json.length;i++){
		    		 var terms = json[i].split("@");
		    		 if(!'${termId}'){//termId不为null
		    			 if(i==0){
		    				 termInfo = terms[1];
		    				 options += '<option value="'+terms[0]+'"selected="selected">'+terms[1]+'</option>';
		    			 }else{
		    				 options += '<option value="'+terms[0]+'">'+terms[1]+'</option>';
		    			 }
		    		 }else{
		    			 if(terms[0]=='${termId}'){
		    				 termInfo = terms[1];
		    				 options += '<option value="'+terms[0]+'"selected="selected">'+terms[1]+'</option>';
		    			 }else{
		    				 options += '<option value="'+terms[0]+'">'+terms[1]+'</option>';	
		    			 }
		    		 }
		    	 }
		    	 $("#termId").append(options);
				 var infos= $('#jb').val()+"届"+termInfo+"评语填写统计";
				 if($('#jb').val()){
					 $("#tittle").html(infos);
				 }else{
					 $("#tittle").html("***届***学期评语填写统计");
				 }
		     }
		 });
	}
	function sub(){
		if(!$('#jb').val()){
			alert("该学段下，无届别！");
			return;
		}
		if(!$('#termId').val()){
			alert("该届别下无学期信息！");
			return;
		}
		$("#form1").submit();
		ShowDiv();
	}
	function exportExcel(){
		dStatus = uuid(); 
		window.location.href = "${ctx}/export/WritedStaticsExportAction.a?years="
			+ $('#jb').val() + "&termId=" + $("#termId").val() + "&dStatus=" + dStatus
			+ "&termName=" + $("#termId option:selected").html();
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
<title>统计表</title>

</head>

<body onload="changeTerm($('#jb'));">
<div class="dangqianwz" style="padding-top: 1.2%;z-index:1001;position: fixed;">
  <form action="${ctx }/appraisalWritedStatics/AppraisalWritedJWAction.a" method="post" id="form1">
  		<input type="hidden" name="flag" value="1"/>
	  <span class="fl">当前位置：评语填写统计</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	               <span >届别：
	     <select id="jb" name="years" class="  wenziliebiao100" onchange="changeTerm($(this));">
	     	<c:forEach items="${JBs}" var="jb" varStatus="vs">
	     		<c:if test="${empty years}">
	     			<option value="${fn:split(jb,'_')[1]}" <c:if test="${vs.index eq 0 }">selected="selected"</c:if>>${fn:split(jb,'_')[1]}</option>
	     		</c:if>
	     		<c:if test="${!empty years}">
	     			<option value="${fn:split(jb,'_')[1]}" <c:if test="${fn:split(jb,'_')[1] eq years}">selected="selected"</c:if>>${fn:split(jb,'_')[1]}</option>
	     		</c:if>
	     	</c:forEach>
	            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <span >学期：
	     <select id="termId" name="termId" class="  wenziliebiao100"></select> </span>
   <%--   <select  name="termId2" id="termId2" class="  wenziliebiao100">
		<app:newTermId termId="${termId}"/>
	</select> --%>
  	<input type="button" value="查询" class="button ml10" onclick="sub();"/>
  	<input type="button" value="导出" class="button ml10" onclick="exportExcel();"/>
  </form>
</div>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="16">
      <div id="tittle" class="tishi_left fl">届学期评语填写统计</div></td>
    </tr>
   <tr class="biaotou_zi">
     <td width="300" rowspan="2">班级</td>
     <td width="60" rowspan="2">学生总人数</td>
     <td colspan="2">班主任评语</td>
     <td colspan="2">思想道德</td>
     <td colspan="2"><p>课程评语</p></td>
     <td colspan="2">合作与交流</td>
     <td colspan="2">运动与健康</td>
     <td colspan="2">审美与表现</td>
     <td colspan="2">个性发展</td>
     </tr>
  <tr class="biaotou_zi">
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	<td width="60">已完成人数</td>
  	<td width="60">未完成人数</td>
  	</tr>
  	<c:if test="${not empty appraiseStatics}">
  		<c:forEach items="${appraiseStatics }" var="as">
		  <tr>
		    <td>${as.classInfo }</span></td>
		    <td>${as.totalCount }</td>
		    <td>${as.bzrCount }</td>
		    <td>${as.totalCount - as.bzrCount}</td>
		    <td>${as.sxddCount }</td>
		    <td>${as.totalCount - as.sxddCount }</td>
		    <td>${as.kcpyCount }</td>
		    <td>${as.totalCount - as.kcpyCount }</td>
		    <td>${as.hzyjlCount }</td>
		    <td>${as.totalCount - as.hzyjlCount }</td>
		    <td>${as.ysyjkCount }</td>
		    <td>${as.totalCount - as.ysyjkCount }</td>
		    <td>${as.smybxCount }</td>
		    <td>${as.totalCount - as.smybxCount }</td>
		   <td>${as.gxfzCount }</td>
		    <td>${as.totalCount - as.gxfzCount }</td>
		   </tr>
  		</c:forEach>
  	</c:if>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>