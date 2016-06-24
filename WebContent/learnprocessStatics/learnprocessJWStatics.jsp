<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css_new/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>
<title>统计表</title>

</head>
<script type="text/javascript">
var $$= jQuery.noConflict();
 function sub(){
	 $$("#termName").val($$("#termId option:selected").text());
	 ShowDiv();
	 $$("#form1").submit();
 }
 function go(){
	 /* if(!'${isQuery}')return; */
	 var typeId = document.getElementById("subjectid").value;
	window.location.hash=typeId;
	//alert('dddd');
}
 function exportExcel()
 {
    var termname = $$("#termId option:selected").text();
 	var termid = $$("#termId option:selected").val();
 	dStatus = uuid(); 
 	window.location.href = "${ctx}/export/CourseCountExport.a?termname="
 				+ termname+ "&dStatus=" + dStatus +"&termid=" + termid; 
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
<div class="dangqianwz" style="padding-top: 18px;z-index:1001;position: fixed;">
	<form action="${ctx }/learnprocessStatics/LearnprocessStaticsAction.a" method="post" id="form1">
 	<input name="isQuery" value="1" type="hidden"/>
 	<input name="termName" value="" type="hidden" id="termName"/>
 	<span class="fl">当前位置:课程评语填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span >科目：
    			<select name="subject" class="  wenziliebiao100" id="subjectid" onchange="go();">
	              <c:forEach items="${subjectDtos }" var="sDto" varStatus="vs">
		             <c:choose>
		             	<c:when test="${empty subject}">
		             		<option value="${sDto.subjectid }" 
		             			<c:if test="${vs.index==0 }">selected="selected"</c:if>
		             		>${sDto.subjectName }</option>
		             	</c:when>
		             	<c:otherwise>
		             		<option value="${sDto.subjectid }" 
		             			<c:if test="${sDto.subjectid eq subject}">selected="selected"</c:if>
		             		>${sDto.subjectName }</option>
		             	</c:otherwise>
		             </c:choose> 
	              </c:forEach>
            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <span >学期：
		  	<select  name="termId" id="termId" class="  wenziliebiao100">
				<app:newTermId termId="${termId}"/>
			</select>
		   </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             
            <span ><input type="button" value="查 询" class="button ml10" onclick="sub();"/></span>&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
      </form>
</div>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="20">
      <div class="tishi_left fl">${termName }课程评语填写情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="100">科目</td>
     <td width="40">完成情况</td>
     <c:forEach items="${gradeYears}" var="year">
     	<td >${year }届</td>
     </c:forEach>
    </tr>
    <%-- <c:if test="${!empty isQuery}"> --%>
    	<c:forEach items="${subjectDtos}" var="sDto">
	   	  <c:forEach items="${learnprocessJWStaticsInfo[sDto.subjectid]}" var="finishedInfo" varStatus="vs">
		   	  <tr>
		   	  	<c:if test="${vs.index==0}">
			    	<td rowspan="4">${sDto.subjectName}</td>
			    </c:if>
			    	<td width="150px">${finishedInfo.key }
			    		<c:if test="${finishedInfo.key eq '学生总人数'}"><a id="${sDto.subjectid}"></a></c:if>
			    	</td>
			    	<c:forEach items="${gradeYears}" var="year">
			    		<td>${finishedInfo.value[year]}</td>
			    	</c:forEach>
		      </tr>
	      </c:forEach>
    	</c:forEach>
	<%-- </c:if> --%>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>