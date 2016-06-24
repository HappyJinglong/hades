<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<title>首页</title>
</head>
<script type="text/javascript">
	var tmp ='${levelCode}';
 	function forwardStuInfoPage(stuId,key,name,classid,eduid){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if(tmp=='2012003' || tmp=='2012004'){
			mF.src = '${ctx}/appraise/QueryAppraiseAction.a?edu_id='+eduid+'&&studentName='+name;
		}
		if(tmp=='2012002'){
			mF.src = '${ctx}/appraise/QueryAppraiseAction.a?edu_id='+eduid+'&&studentName='+name;
		}
	}
 	function forwardStuInfoList(ids,idName){
 		self.frames[2].ShowDiv && self.frames[2].ShowDiv();
		var mF = document.getElementById('mainFrame');
		if(tmp=='2012003' || tmp=='2012004'){
			mF.src = '${ctx }/master/StudentApprisedAction.a?DefaultEduId';
		}
		if(tmp=='2012002'){
			mF.src = '${ctx }/middlemaster/StudentApprisedAction.a?DefaultEduId';
		}
		if(ids){
			mF.src +=  '&ids='+ids;
		}
		if(idName){
			mF.src +=  '&idName='+idName;
		}
	} 
</script>
<frame>
	<frameset border="0px" cols="250px,11px,*">
	<!-- 高中 -->
	<c:if test="${levelCode eq '2012003' or levelCode eq 2012004}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/master/MasterAppriseAction.a?queryCommentTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>">
		<%-- <frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/master/StudentApprisedAction.a?DefaultEduId"> --%>
	</c:if>
	
 	<!-- 初中 -->
	<c:if test="${levelCode eq 2012002}">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/middlemaster/MasterAppriseAction.a?queryCommentTreePage&&commonFuncId=<%=request.getAttribute("commonFuncId")%>">
		<%-- <frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/middlemaster/StudentApprisedAction.a?DefaultEduId"> --%>
	</c:if> 
		<frame name="swich"  id="swich"  frameborder="0" marginwidth="0" marginheight="0" noresize="0" src="${ctx }/common/switch_ckpj.jsp"> 
				<frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/common/loading.jsp">
	</frameset><noframes></noframes>
</html>