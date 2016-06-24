<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>展示div</title>
</head>
<style>
.MyDiv{
   margin-left:-8px;
}

</style>
<script type="text/javascript">
function  showD(){
	var commonFuncId = <%=request.getAttribute("commonFuncId")%>;
	ShowDiv();
	window.location.href="${ctx }/appraise/QueryAppraiseAction.a?checkFlag=1&commonFuncId="+commonFuncId
}
</script>

<body onload="showD();">
     <div id="MyDiv" class="white_content" style="width: 100%;height: 100%;margin-left:-8px;"> <!--弹出层时背景层DIV-->    
    <div class="loading">  
        <span style='width: 75px; height: 75px;'>  
            <img src='${ctx}/images/progress.gif' alt="数据正在加载..." style='width: 75px; height: 75px;'/></span><span class='spnContent'>数据正在加载...</span>
    </div>  
</div>
</body>
</html>