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
 	function forwardStuInfoPage(stuId,key,name,classid){
		var mF = document.getElementById('mainFrame');
		mF.src = '${ctx}/middlemaster/StudentApprisedAction.a?studentid='+stuId+"&key="+key+"&name="+name+"&classId="+classid;
	}
 	function forwardStuInfoList(ids,idName){
		var mF = document.getElementById('mainFrame');
		mF.src = '${ctx}/middlemaster/StudentApprisedAction.a?showAppriseCount';
		if(ids){
			mF.src +=  '&ids='+ids;
		}
		if(idName){
			mF.src +=  '&idName='+idName;
		}
	} 
</script>
<frame>
	<frameset cols="250px,*">
		<frame name="left" frameborder="0" noresize="0" src="${ctx }/middlemaster/MasterAppriseAction.a?toTreePage">
		<frame name="main"  id="mainFrame"  frameborder="0"  noresize="0" src="${ctx }/middlemaster/StudentApprisedAction.a?showAppriseCount">
	</frameset><noframes></noframes>
</html>