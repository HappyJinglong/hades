<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<un:bind var="TERM_TYPE" type="com.flyrish.hades.common.Constant"
	field="DIC_TERM_TYPE" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>北京市高中学校文件上传登陆页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <script src="${ctx}/js/jquery.min.js" type="text/javascript" ></script>
  	<script src="${ctx}/js/func.js" type="text/javascript" ></script>
  	 <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" /> 
    <link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" />
    
     <link href="${ctx}/css/font-awesome.css" rel="stylesheet" /> 
    
   <link href="${ctx}/css/adminia.css" rel="stylesheet" /> 
   <link href="${ctx}/css/adminia-responsive.css" rel="stylesheet" />
   <script src="${ctx}/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>
   <meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
   <script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
   <script type="text/javascript" src="${ctx}/js/generateuuid.js"></script>
   <link href="${ctx}/css/pages/login.css" rel="stylesheet" /> 
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<script type="text/javascript">
window.onload=function(){
	  var msg='${error}';
	  if(msg!=''){
		  alert(msg);
	  }
		//判断IE类型
		 var Sys = {};    
		 var ua = navigator.userAgent.toLowerCase();    
		 var s;    
		 (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1]:
		 (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
		 (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		 (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		 (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		 (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	 	if (Sys.ie){
	 		if(Sys.ie<7.0){
	 			$('#logininbtn').attr('style','display:none');
				alert('请使用IE8~IE11浏览器登录本系统，否则无法登录');
				return;
	 		}
	 	}else{
	 		$('#logininbtn').attr('style','display:none');
			alert('请使用IE8~IE11浏览器登录本系统，否则无法登录');
			return;
	 	} 
}
  function checkForm()
	{
		var frm = document.forms[0];
		frm.j_username.value = frm.j_username.value.trim();
	    //判断登录名是否填写。
	    if (frm.j_username.value.trim() == "")
	    {
	        frm.j_username.focus();
	        alert("请填写用户名。");
	        return false;
	    }
	   
	    //判断密码是否填写。
	     frm.j_password.value = frm.j_password.value.trim();
	    if (frm.j_password.value.trim() == "")
	    {
	        frm.j_password.focus();
	        alert("请填写密码。");
	        return false;
	    }
		document.loginForm.submit();
	}
  </script>
<body>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			<a class="brand" href="#">北京市高中学校CMIS40库、课改数据库以及综素附件上传登陆页</a>
			<div class="nav-collapse">
				<ul class="nav pull-right">
					<li class="">
					</li>
				</ul>
			</div>			
		</div> 
	</div>
</div>
<div id="login-container">
	<div id="login-header">
		<h3>用户登录</h3>
	</div>
	<div id="login-content" class="clearfix">
	<form name="loginForm" method="post" id="loginForm" action="${ctx}/fileupload/LoginFileUploadAction.a?onLogin">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="username">用户名</label>
						<div class="controls">
							<input type="text" class="" id="j_username" name="username" value="${username}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">密码</label>
						<div class="controls">
							<input type="password" class="" id="j_password" name="password" value="${password}"/>
						</div>
					</div>
				</fieldset>
				<div id="remember-me" class="pull-left">
				</div>
				<div class="pull-right">
					<input type="button" class="btn btn-warning btn-large" id="logininbtn" onclick="checkForm()" value="登录">
					</input>
				</div>
			</form>
		</div>
		<div id="login-extra">
		</div>
</div>
<div id="footer">
	
	<div class="container">				
		<hr />
		<p style="margin:0 auto;width:150px;">版本号：1.0.0</p>
		<p style="margin:0 auto;width:400px;">©技术支持：北控软件有限公司 服务热线：4006515253</p>
	</div> 
	
</div>
  </body>
</html>
