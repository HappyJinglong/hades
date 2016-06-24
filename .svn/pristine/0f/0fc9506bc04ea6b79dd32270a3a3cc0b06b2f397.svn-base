<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<title>统计表</title>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<style type="text/css">
 	#tjb_main{
		top:37px;
		padding:13px;
		margin-bottom:23px;
	}
	body,html{
    overflow-x:hidden;
	overflow-y:hidden; 
}
</style>
<script type="text/javascript">
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	ShowDiv();
	window.location.href="${ctx}/master/StudentApprisedAction.a?showAppriseCount&ids="+'${ids}'+"&idName="+'${idName}'+"&zsTermId="+zsTermId+"&isHistory=1";
}
</script>
</head>
<body>
<div class="dangqianwz">
 	<span class="fl">当前位置：评价学生</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<span>学期：<select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()">
    	 <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
    </select> </span>
</div>
<div id="tjb_main" >
<div class="top" >
<table width="100%" border="0" align="center" cellspacing="1" bgcolor="#9a9a9a" class="biaoge">
   <tr class="tishi">
    <td colspan="8">
    	<div class="tishi_left fl">${ classInfo} 评价填写情况汇总
        </div>
    </td>
    </tr>
  <tr class="biaotou_zi">
  	<td width="170">学生</td>
  	<td width="259">班主任评语<span class="red">*</span></td>
  	<td width="140">思想道德</td>
  	<td width="140">学业成就</td>
  	<td width="140">合作与交流</td>
  	<td width="140">运动与健康</td>
  	<td width="140">审美与表现</td>
  	<td width="140">个性与发展</td>
  	</tr>
  	<c:if test="${!empty masterAppriseStudentInfo }">
	  	<c:forEach items="${masterAppriseStudentInfo}" var="map"  varStatus="count">
	  		<tr>
	  			<c:forEach  items="${map.value}" var="list">
	  				<td>
	  					${list}
	  				</td>
	  			</c:forEach>
	  		</tr>
	  	</c:forEach>
  	</c:if>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>