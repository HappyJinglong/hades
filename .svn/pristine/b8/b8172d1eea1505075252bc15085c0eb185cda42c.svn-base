<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<title>首页</title>
</head>
<script type="text/javascript">
 	function forwardStuInfoPage(stuId,key,name,classid,eduid){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if('${levelCode}'=='2012002'){
			mF.src = '${ctx}/teacher/StudentApprisedAction.a?studentid='+stuId+"&key="+key+"&name="+name+"&classId="+classid+"&eduid="+eduid;
		}else{
			mF.src = '${ctx}/highschoolteacher/StudentApprisedAction.a?studentid='+stuId+"&key="+key+"&name="+name+"&classId="+classid+"&eduid="+eduid;
		}
	}
 	function forwardStuInfoList(ids,idName){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if('${levelCode}'=='2012002'){
			mF.src = '${ctx}/teacher/StudentApprisedAction.a?&ids='+ids+'&idName='+idName;
		}else{
			mF.src = '${ctx}/highschoolteacher/StudentApprisedAction.a?&ids='+ids+'&idName='+idName;
		}
 	}
</script>
<frame>
	<frameset cols="250px,11px,*" border="0px">
	<c:if test="${levelCode eq '2012002'}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/teacher/MasterAppriseAction.a?toTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>">
		<%-- <frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/teacher/StudentApprisedAction.a"> --%>
	</c:if>
	<c:if test="${levelCode eq '2012003' or levelCode eq 2012004}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/highschoolteacher/MasterAppriseAction.a?toTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>">
		<%-- <frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/highschoolteacher/StudentApprisedAction.a"> --%>
	</c:if>
		<frame name="swich"  id="swich"  frameborder="0" marginwidth="0" marginheight="0" noresize="0" src="${ctx }/common/switch_lym.jsp">
		<frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/common/loading.jsp">
	</frameset><noframes></noframes>
</html>