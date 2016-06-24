<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>统计表</title>
<style type="text/css">
	#tjb_main{
		top:20px;
		padding-top:30px;
		position: absolute;
	}
		body,html{
    overflow-x:hidden;
	overflow-y:hidden; 
}
</style>
</head>
<script type="text/javascript">
function checkData(aObj,num){
	ShowDiv();
	var pVs = aObj.attr("id").split("_")[0];
	var vs = aObj.attr("id").split("_")[1];
	var cid = $("#"+pVs+"_classId").val();
	var term = $("#"+pVs+"_termId").val();
	var flag = vs%2;
	window.location.href="${ctx}/middlemaster/MasterApriseDataAction.a?showAppriseList&&flag="+flag+"&&evaluatetypeid="+'${sectionCode}'+"&&sectionName="+'${sectionName}'+"&&classId="+cid+"&&zsTermId="+term+"&&isHistory=1";
}
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	var className = $("#termId option:selected").html();
	ShowDiv();
	window.location.href="${ctx }/middlemaster/StudentApprisedAction.a?showStudentCount&&sectionCode="+${sectionCode}+"&&zsTermId="+zsTermId+"&&isHistory=1&&className="+className;
}
</script>
<body>
<div class="dangqianwz">
 	<span class="fl">当前位置：评价学生->${sectionName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	  <span >学期：
     <select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()">
    	 <app:highSchoolTermTag selectClassid="${maxClassId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
     </select> </span>
</div>
<div id="tjb_main" style="width: 95%;">
  <div class="top" style="margin-left:-21px;width:103.5%;">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="4">
    	<div class="tishi_left fl">${sectionName }评价情况汇总<span class="renshu fl ml30">学生总数：<span class="zhi clgreen">6</span>&nbsp;人</span>
     </div>
    </td>
  </tr>
  <tr class="biaotou_zi">
  	<td width="300">班级</td>
  	<td width="200">学生数</td>
  	<td width="300">已完成学生数</td>
  	<td width="300">未完成学生数</td>
  	</tr>
  	<c:forEach items="${masterAppriseClassInfo }"  var="classinfo" varStatus="vsP">
  	  <tr>
		<c:forEach items="${classinfo.value }"  var="classinfos" varStatus="vs">
			 <td>
			 		<c:if test="${vs.count>2 }">
			 		<!-- 余1  已完成   余0 未完成 -->
			 			<c:if test="${classinfos eq '0'}">
			 				<%-- <a href="#"  onclick="checkData($(this),${vs.count%2});" id="${vsP.count}_${vs.count}_A"> --%>
			 				<span class="red" >${classinfos }</span><!-- </a> -->
			 			</c:if>
			 			<c:if test="${classinfos ne '0'}">
			 				<a href="#"  onclick="checkData($(this),${vs.count%2});" id="${vsP.count}_${vs.count}_A"><span class="red">${classinfos }</span></a>
			 			</c:if>
			 		</c:if>
			 		<c:if test="${vs.count==2 }">
			 			${classinfos }
			 		</c:if>
			 		<c:if test="${vs.count==1 }">
			 			${fn:split(classinfos,'@')[0] }<input type="hidden" id="${vsP.count}_classId" value="${fn:split(classinfos,'@')[1] }"/><input type="hidden" id="${vsP.count}_termId" value="${fn:split(classinfos,'@')[2] }"/>
			 		</c:if>
			 </td>
		</c:forEach>  	
	</tr>
  	</c:forEach>
<%--   	<c:forEach items="${masterAppriseClassInfo }" var="keyMap" varStatus="vs1">
  		<input type="hidden" id="${vs1.count}_cId"  value="${keyMap.key }"/>
  	</c:forEach> --%>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
