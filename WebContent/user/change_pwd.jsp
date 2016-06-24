<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>    
<html>
<head>
<title>北京市初高中综合素质评价云平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/css/cmis4_library.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/cmis4_master.css" rel="stylesheet" type="text/css"/>
<script language=JavaScript src="${ctx}/js/func.js" type=text/JavaScript></script>
<script type='text/javascript' src='${ctx}/js/validata.js'></script>
<style type="text/css">

 	body {
	background-color: #EEFAFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {color: #FF0000}
.style2 {font-size: 12px}
.style3 {
	font-size: 14px;
	font-weight: bold;
	color: #003399;
} 

</style>

<script type="text/javascript">
	
	/* var fields=[{id:'oldPwd',text:'原密码',maxLength:20,type:'/[^01]/g'},
				{id:'newPwd',text:'新密码',minLength:6,maxLength:20,type:'/[^01]/g'},
				{id:'rNewPwd',text:'确认密码',compareTo:'newPwd',compareType:'=='
				}]; */
	
	var fields=[{id:'oldPwd',text:'原密码',maxLength:20,type:''},
				{id:'newPwd',text:'新密码',minLength:6,maxLength:20,type:''},
				{id:'rNewPwd',text:'确认密码',compareTo:'newPwd',compareType:'=='
				}];
	
	<%--校验数据--%>
	function checkFrom(){
		var success = checkFields(fields);
		return success;
	}
	
	 function check(obj){
		
		 var str = document.getElementById("oldPwd").value;
		 /*  document.getElementById("oldPwd").value = str.replace(/[^01]/g, ""); */
	    alert(str);
		 
	    if(!/^[A-Za-z]+$/.test(obj.value)&&obj.value!='')){
	    	alert("aa");
	    }
	} 
	
</script>
</head>
<body style="width: 100%">
<form id="addUserForm" name="addUserForm" action="${ctx}/user/ChangePwdAction.a?doChangePwd" method="post">
	<div id="titlearea"> 
		<span class="title">当前操作：</span>
	      <h1>修改密码</h1>
		<c:if test="${not empty error}">
			<div id="errormsg" align="left" class="style3" style="width:100px;height:20px;background-color:yellow;">${error}</div>
		</c:if>
			<c:if test="${not empty message}">
			<div id="successmsg" style="width:100px;height:20px;">${message }</div>
		</c:if>
	</div>
<div id="resulttitlearea"><span>修改密码</span></div>
<div id="formarea">
<table style="width: 650px;" border="0" cellpadding="0" cellspacing="1">
	<tr>
		<td>
		<div id="inputname">请输入旧密码：</div>
		</td>
		<td>
		<div id="inputvalue">
			<input type="password" name="oldPwd" value="${oldPwd}" id="oldPwd" minLength="6" maxlength="20">
		</div>
		 <div><span class="style1">*</span></div></td>

	</tr>
	<tr>
		<td>
		<div id="inputname">请输入新密码：</div>
		</td>
		<td>
		<div id="inputvalue">
			<input type="password" name="newPwd" value="${newPwd}" id="newPwd" minLength="6" maxlength="20" onblur="check(this);">
		</div>
		 <div><span class="style1">*</span></div></td>

	</tr>
	<tr>
		<td>
		<div id="inputname">确认新密码：</div>
		</td>
		<td>
		<div id="inputvalue">
			<input type="password" name="rNewPwd" value="${rNewPwd}" id="rNewPwd" minLength="6" maxlength="20">
		</div>
		 <div><span class="style1">*</span></div></td>
	</tr>
</table>
</div>
<div id="operationarea">
	<div id="operationcontent">
		<input name="doChangePwd" type="submit" value=" 保存 " onclick="return checkFrom();" 
		<%-- <c:if test="${!isTrue }">disabled="disabled"</c:if> --%>
		class="btnop_mouseout" onmouseover="this.className='btnop_mouseover'" onmouseout="this.className='btnop_mouseout'" />
	</div>
</div>
</form>
</body>
</html>