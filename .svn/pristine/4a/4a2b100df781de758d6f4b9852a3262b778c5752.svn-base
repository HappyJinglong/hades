<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<title>首页</title>
</head>

<script type="text/javascript">
 	function forwardStuInfoPage(stuId,name){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		mF.src ='${ctx}/student/CzZtreeActio.a?evaluatedPersonID='+stuId;
	}
</script>
<frameset border="0px" cols="250px,11px,*">
		<frame name="left" frameborder="0" noresize="0" src="czleft.jsp">
		<frame name="swich"  id="swich"  frameborder="0" marginwidth="0" marginheight="0" noresize="0" src="${ctx }/common/switch_xcs.jsp">
		<frame name="main"  id="mainFrame" frameborder="0"  noresize="0" src="${ctx }/common/loading.jsp">
		<%-- <frame name="main"  id="mainFrame" frameborder="0"  noresize="0" src="${ctx}/student/CzTreeShow.a"> --%>
	</frameset>

</html>





