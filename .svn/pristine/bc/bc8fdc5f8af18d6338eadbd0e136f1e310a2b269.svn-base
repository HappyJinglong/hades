<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<title>首页</title>
</head>
<style type="text/css">
/* .frame 
{ 
    border-width: 0px; 
    border-style: none; 
} */
</style>
<script type="text/javascript">
	var tmp ='${levelCode}';
 	function forwardStuInfoPage(stuId,key,name,classid,eduid){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if(tmp=='2012003' || tmp=='2012004'){
			mF.src = '${ctx}/master/StudentApprisedAction.a?studentid='+stuId+"&key="+key+"&name="+name+"&classId="+classid+"&eduid="+eduid;
		}
		if(tmp=='2012002'){
			mF.src = '${ctx}/middlemaster/StudentApprisedAction.a?studentid='+stuId+"&key="+key+"&name="+name+"&classId="+classid+"&eduid="+eduid;
		}
	}
 	function forwardStuInfoList(ids,idName){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if(tmp=='2012003' || tmp=='2012004'){
			mF.src = '${ctx}/master/StudentApprisedAction.a?showAppriseCount';
		}
		if(tmp=='2012002'){
			mF.src = '${ctx}/middlemaster/StudentApprisedAction.a?showAppriseCount';
		}
		if(ids){
			mF.src +=  '&ids='+ids;
		}
		if(idName){
			mF.src +=  '&idName='+idName;
		}
	} 
</script>
	<frameset border="0px" cols="255px,11px,*" >
	<c:if test="${levelCode eq '2012003' or levelCode eq 2012004}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/master/MasterAppriseAction.a?toTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>"/>
	</c:if>
	<c:if test="${levelCode eq 2012002}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/middlemaster/MasterAppriseAction.a?toTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>"/>
	</c:if>
		<frame name="swich"  id="swich"  frameborder="0" marginwidth="0" marginheight="0" noresize="0" src="${ctx }/common/switch_lym.jsp">
		<frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/common/loading.jsp">
	</frameset><noframes></noframes>
</html>