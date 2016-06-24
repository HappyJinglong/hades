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
		top:37px;
		padding:15px 13px;
		margin-bottom:23px;
		position: absolute;
		width:97.8%;
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
	var cid =$("#selectedId option:selected").val();
	var flag = vs%2;
	window.location.href="${ctx}/middlemaster/MasterApriseDataAction.a?showAppriseList&&flag="+flag+"&&evaluatetypeid="+'${sectionCode}'+"&&sectionName="+'${sectionName}'+"&&classId="+cid+"&&zsTermId="+'${zsTermId}'+"&&isHistory=1";
}
function chooseTerm(){
	var classid =$("#selectedId option:selected").val();
	var zsTermId = $("#termId option:selected").val();
	ShowDiv();
	window.location.href="${ctx }/middlemaster/StudentApprisedAction.a?showStudentCount&&sectionCode="+${sectionCode}+"&&zsTermId="+zsTermId+"&&isHistory=1&&isChooseClass=1&&classId="+classid;
}
//选择班级
function chooseClass(){
	var classid =$("#selectedId option:selected").val();
	ShowDiv();
	window.location.href="${ctx}/middlemaster/StudentApprisedAction.a?showStudentCount&sectionCode="+'${sectionCode}'+"&isChooseClass=1&sectionName="+'${sectionName }'+"&classId="+classid;
}
function goIn(aObj,num){
	ShowDiv();
	var pVs = aObj.attr("id").split("_")[0];
	var vs = aObj.attr("id").split("_")[1];
	var classid =$("#selectedId option:selected").val();
	var subId = $("#"+pVs+"_subId").val();
	var flag = vs%2;
	window.location.href="${ctx}/middlemaster/MasterApriseDataAction.a?showAppriseList&&flag="+flag+"&&evaluatetypeid="+'${sectionCode}'+"&&sectionName="+'${sectionName}'+"&&classId="+classid+"&&zsTermId="+'${zsTermId}'+"&&isHistory=1&&subject="+subId;
}
</script>
<body id="tjb_main_flag">
<div class="dangqianwz">
 	<span class="fl">当前位置：评价学生->${sectionName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	     <span >班级：
     <select id="selectedId" name="select" class="  wenziliebiao100" onchange="chooseClass();">
     			<c:if test="${not empty classInfos }">  
     				<c:forEach items="${classInfos}" var="cInfo"  varStatus="vs">
     					<option <c:if test="${fn:split(cInfo,'@')[1] eq classId}">selected="selected"</c:if> value="${fn:split(cInfo,'@')[1] }">${fn:split(cInfo,'@')[0] }</option>
     				</c:forEach>
     			</c:if>
            </select>  </span> 
 	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
 	  <span >学期：
     <select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()">
    	 <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
     </select> ${masterAppriseClassInfo.value[0] } </span>
</div>
<div id="tjb_main" >
  <div class="top" >
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="4" align="left">
    	<!-- <div class="tishi_left fl"> -->${sectionName }评价情况汇总--
		    <c:forEach items="${masterAppriseClassInfo }"  var="classinfo" varStatus="vsP">
				<c:forEach items="${classinfo.value }"  var="classinfos" varStatus="vs">
					 		<c:if test="${vs.count==3 }">
					 		<!-- 余1  已完成   余0 未完成 -->
					 				&nbsp;已经评价学生数：<!-- <span class="green" > -->${classinfos }<!-- </span> -->
					 		</c:if>
					 		<c:if test="${vs.count==4 }">
					 		<!-- 余1  已完成   余0 未完成 -->
					 			<c:if test="${classinfos eq '0'}">
					 				&nbsp;没有评价学生数：<span class="red" >${classinfos }</span>
					 			</c:if>
					 			<c:if test="${classinfos ne '0'}">
									&nbsp;没有评价学生数：<a href="#"  onclick="checkData($(this),${vs.count%2});" id="${vsP.count}_${vs.count}_A"><span class="red">${classinfos }</span></a>
								</c:if>
					 		</c:if>
					 		<c:if test="${vs.count==2}">
					 			&nbsp;&nbsp;&nbsp;学生总数：${classinfos }
					 		</c:if>
					 		<c:if test="${vs.count==1}">
					 			${classinfos}
					 		</c:if>
				</c:forEach>  	
		  	</c:forEach>
    	<!-- <span class="renshu fl ml30">学生总数：<span class="zhi clgreen">6</span>&nbsp;人</span> -->
    <!--  </div> -->
    </td>
  </tr>
  <tr class="biaotou_zi">
  	<td width="300">课程</td>
  	<td width="200">学生数</td>
  	<td width="300">已完成学生数</td>
  	<td width="300">未完成学生数</td>
  	</tr>
  	<c:forEach items="${classSubjectInfo}"  var="courseCount" varStatus="vsP">
  	  <tr>
		<c:forEach items="${courseCount }"  var="classinfos" varStatus="vs">
			 <td>
			 		<c:if test="${vs.count>2}">
			 		<!-- 余1  已完成   余0 未完成 -->
			 			<c:if test="${classinfos eq '0'}">
			 				<span class="red" >${classinfos }</span>
			 			</c:if>
			 			<c:if test="${classinfos ne '0'}">
			 				<a href="#"  onclick="goIn($(this),${vs.count%2});" id="${vsP.count}_${vs.count}_A"><span class="red">${classinfos }</span></a>
			 			</c:if>
			 		</c:if>
			 		<c:if test="${vs.count==2 }">
			 			${classinfos }
			 		</c:if>
			 		<c:if test="${vs.count==1 }">
			 			${fn:split(classinfos,'@')[0] }<input type = "hidden" id="${ vsP.count}_subId" value="${fn:split(classinfos,'@')[1]  }"/>
			 		</c:if>
			 </td>
		</c:forEach>  	
	</tr>
  	</c:forEach>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
