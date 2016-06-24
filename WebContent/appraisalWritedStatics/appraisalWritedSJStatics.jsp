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
<title>统计表</title>
<script type="text/javascript">
	function sub(){
		$("#form1").submit();
		ShowDiv();
	}
	function initTittle(){
		$("#tittle").html($("#termId").find("option:selected").html()+"学校填写情况 ");
		$("#termName").val($("#termId").find("option:selected").html());
		$("#zoneName").val($("#discode").find("option:selected").html());
	}
	function toQueryQU(discode,termId){
		var form2 = $("#form2");
		if(form2.length>0){
			form2.remove();
		}
		form2 = '<form id="form2" action="${ctx }/appraisalWritedStatics/AppraisalWritedQXAction.a?toQueryQXAppraiseStatics" method="post">'
					+'<input type="hidden" name="discode" value="'+discode+'"/>'
					+'<input type="hidden" name="termId" value="'+termId+'"/>'
					/* +'<input type="hidden" name="termName" value="'+termName+'"/>'*/
					+'<input type="hidden" name="toQuery" value="1"/>' 
				+'</form>';
		$("body").append(form2);
		$("#form2").submit();
		ShowDiv();
	}
	function exportSchoolCount()
	{
		var termName = $("#termId option:selected").text();
		var termId = $("#termId option:selected").val();
		var discode = $("#discode").val();
		/* var schoolName = $("#schoolName").val(); */
		dStatus = uuid(); 
	window.location.href = "${ctx}/export/shoolFillCount.a?termName="
				+ termName + "&termId=" + termId + "&discode=" + discode + "&dStatus=" + dStatus;
	 
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

<body onload="initTittle();">
<div class="dangqianwz" style="padding-top: 1.2%;z-index:1001;position: fixed;">
 <form id="form1" action="${ctx }/appraisalWritedStatics/AppraisalWritedQXAction.a" method="post">
 	<input type="hidden" name="isQuery" value="1"/>
 	<input value="" type="hidden" id="termName" name="termName"/>
 	<input name="zoneName" id="zoneName" type="hidden" value=""/>
 	<span class="fl">当前位置：学校填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >区县：
   <select id="discode" name="discode" class="  wenziliebiao100">
   			<c:if test="${empty discode}">
			<option selected="selected" value="">全部</option>
				<c:forEach items="${queryQXInfos}" var="info">
					<option value="${fn:split(info,'@')[0]}">${fn:split(info,'@')[1]}</option>
   				</c:forEach>
   			</c:if>
			<c:if test="${!empty discode}">
				<option value=""
					<c:if test="${fn:split(info,'@')[0] eq discode}">selected="selected"</c:if>
					>全部</option>
				<c:forEach items="${queryQXInfos}" var="info">
					<option value="${fn:split(info,'@')[0]}"
					<c:if test="${fn:split(info,'@')[0] eq discode}">selected="selected"</c:if>
					>${fn:split(info,'@')[1]}</option>
   				</c:forEach>
   			</c:if>
   </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
  <span >学期：
    	<select  name="termId" id="termId" class="  wenziliebiao100">
			<app:newTermId termId="${termId}"/>
		</select>
     <%-- <select name="termId" id="termId" class="  wenziliebiao100">
            <c:forEach items="${termInfos}" var="termInfo" varStatus="vs">
     		<c:if test="${empty termId}">
     			<option value="${fn:split(termInfo,'@')[0]}" <c:if test="${vs.index == 0}">selected="selected"</c:if>>${fn:split(termInfo,'@')[1]}</option>
     		</c:if>
     		<c:if test="${not empty termId}">
     			<option value="${fn:split(termInfo,'@')[0]}" <c:if test="${termId eq fn:split(termInfo,'@')[0]}">selected="selected"</c:if>>${fn:split(termInfo,'@')[1]}</option>
     		</c:if>
     		</c:forEach>
            </select> --%> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="查 询" class="button ml10" onclick="sub();"/></span>&nbsp;&nbsp;&nbsp;
             <span ><input type="button" value="导 出" class="button ml10" onclick="exportSchoolCount();"/></span>
  </form>
</div>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="10">
      <div class="tishi_left fl" id="tittle"></div></td>
    </tr>
   <tr class="biaotou_zi">
     <td width="60" rowspan="2">序号</td>
     <td width="300" rowspan="2">区县</td>
      <c:forEach items="${queryJB  }" var="jb">
     	<td colspan="2">${jb }届</td>
      </c:forEach>
     </tr>
   <tr class="biaotou_zi">
     <td width="60">学校数量</td>
     <td>已填写学校数量</td>
     <td>学校数量</td>
     <td>已填写学校数量</td>
     <td>学校数量</td>
     <td>已填写学校数量</td>
      <c:if test="${userDto.levelcode eq '2012002'}">
	     <td>学校数量</td>
    	 <td>已填写学校数量</td>
	  </c:if>
    </tr>
      <c:forEach items="${staticseDtos }" var="info" varStatus="vs">
	   <tr>
	     <td>${vs.count }</td>
	     <c:if test="${info.classInfo ne '合计'}">
	     	<td><a href="#" onclick="toQueryQU('${info.discode}','${termId}');"><span style="color: red;">${info.classInfo }</span></a></td>
	     </c:if>
	      <c:if test="${info.classInfo eq '合计'}">
	     	<td>${info.classInfo }</td>
	     </c:if>
	     <td>${info.oneCount }</td>
	     <td>${info.oneCountFinished }</td>
	     <td>${info.twoCount }</td>
	     <td>${info.twoCountFinished }</td>
	     <td>${info.threeCount }</td>
	     <td>${info.threeCountFinished }</td>
	     <c:if test="${userDto.levelcode eq '2012002'}">
		     <td>${info.fourCount }</td>
		     <td>${info.fourCountFinished }</td>
		 </c:if>
	   </tr>
   </c:forEach>
   <!-- <tr>
   
     <td>1</td>
     <td><a href="学校情况.html"><span class="black">东城区</span></a></td>
     <td>7</td>
     <td>5</td>
     <td>2</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr> -->
  <!-- <tr>
    <td>3</td>
    <td>合计</td>
    <td>7</td>
    <td>5</td>
    <td>2</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr> -->
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
