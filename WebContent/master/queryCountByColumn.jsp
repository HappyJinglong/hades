<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<%@ include file="/common/div.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>


<title>统计表</title>

</head>
<script type="text/javascript">
function checkData(classId,className){
	ShowDiv();
	var zsTermId = $("#termId option:selected").val();	
	window.location.href="${ctx }/master/QueryAppraiseAction.a?sectionCode=${sectionCode}&&classId="+classId+"&&termid="+zsTermId+"&&className="+className;
}
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	ShowDiv();
	window.location.href="${ctx }/master/QueryCommentAction.a?sectionCode="+${sectionCode}+"&&zsTermId="+zsTermId+"&&isHistory=1";
}


</script>
<body style="overflow:hidden;">
<div class="dangqianwz">
 	<span class="fl">当前位置：查看评价->${sectionName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	  学期：
     <select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()">
    	 <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
     </select> </span>
</div>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="7">
    	<div class="tishi_left fl">${sectionName }评价情况
     </div>
    </td>
  </tr>
  <tr class="biaotou_zi">
  	<td width="300">班级</td>
  	<td width="200">学生数</td>
  	<td width="200">班主任评价</td>
  	<td width="200">任课教师评价</td>
  	<td width="200">学生自评</td>
  	<td width="200">同学互评</td>
  	<td width="200">家长评价</td>
  	</tr>
  	<c:forEach items="${masterAppriseClassInfo }"  var="classinfo" varStatus="vsP">
  	  <tr>
		<c:forEach items="${classinfo.value }"  var="classinfos" varStatus="vs">
			 <td>
			 	<c:choose>
			 		<c:when test="${vs.count eq 1 }">
			 		<a href="#" onclick="checkData(${fn:split(classinfo.key,'@')[2]},'${classinfos}')"> <span class="red" >${classinfos }</span></a>
			 		</c:when>
			 		<c:otherwise>
			 			${classinfos }
			 		</c:otherwise>
			 	</c:choose>
			 </td>
		</c:forEach>  	
	</tr>
  	</c:forEach>
  	<c:forEach items="${masterAppriseClassInfo }" var="keyMap" varStatus="vs1">
  		<input type="hidden" id="${vs1.count}_cId"  value="${keyMap.key }"/>
  	</c:forEach>
</table>
</div>
</div>
</body>
</html>
