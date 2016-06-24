<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>

<title>Insert title here</title>
</head>
<script type="text/javascript">
function gotoPage(){
	self.parent.frames[0].forward();
}
</script>
<body onload="ShowDiv();" style="margin: 0;">
<div id="MyDiv" class="white_content" style="width: 100%;height: 100%"> <!--弹出层时背景层DIV-->    
    <div class="loading">  
        <span style='width: 75px; height: 75px;'>  
            <img src='${ctx}/images/progress.gif' alt="数据正在加载..." style='width: 75px; height: 75px;'/></span><span class='spnContent'>数据正在加载...</span>
    </div>  
</div>
</body>
</html>