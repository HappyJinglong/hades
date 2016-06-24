<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<un:bind var="TERM_TYPE" type="com.flyrish.hades.common.Constant"
	field="DIC_TERM_TYPE" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>CMIS数据同步登陆页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <script src="${ctx}/js/jquery.min.js" type="text/javascript" ></script>
  	<script src="${ctx}/js/func.js" type="text/javascript" ></script>
  	 <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" /> 
    <link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" />
    
     <link href="${ctx}/css/font-awesome.css" rel="stylesheet" /> 
    
    <link href="${ctx}/css/adminia.css" rel="stylesheet" /> 
    <link href="${ctx}/css/adminia-responsive.css" rel="stylesheet" />
     <script src="${ctx}/jquery/jquery-1.4.4.min.js" type="text/javascript"></script> 
    <script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
   <script type="text/javascript" src="${ctx}/js/generateuuid.js"></script>
   <link href="${ctx}/css/pages/login.css" rel="stylesheet" /> 
   <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
   <script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<script type="text/javascript">
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
  var interval;
  function formSub()
	{   
		/* var flag=checkForm();
		if(!flag)return; */
		var fr = document.getElementById('loginForm');
		 var uuid = generateUUID();
		 $('#uuid').val(uuid);
		 $.ajaxFileUpload({
				url : 'http://192.168.10.202:9988/upload?X-Progress-ID=' + uuid+'&uuid='+uuid, // 上传文件的服务器地址
				sercureuri : false,  
				fileElementId : 'upload_file', // 文件选择框的id属性  
				dataType : 'json'  // 服务器返回的格式
			});
		 interval = window.setInterval(function() {
				//var progressURL = '${nginxServer}/progress?X-Progress-ID=' + uuid+'&'+Math.random();
				$.ajax({ 
					type : 'get',
					async : false,
					url: '../upload/UploadAction.a?getUploadFileProgress&X-Progress-ID=' + uuid+'&'+new Date().getTime(), 
					dataType: 'text',
					success: function(data){
						var json = eval(data);
		                // 上传完毕后清除定时器
		             	if (json.state == 'done') {  
		                 	window.clearTimeout(interval);  
		                 	//$('#upload_file').attr('disabled',false);
		            	}
			    	}
				});
			}, 1500);
		//fr.submit();
	}
  $(document).ready(function(){
		 $.ajax({
			type: "POST",
			contentType: "application/json",
			url: "${ctx}/demo/DemoAction.a?queryData",
			data: "{}",
			dataType: "json",
			success: function (msg) {
				$("#demo_test").autocomplete(msg.val,{
					minChars: 0,
					width: 310,
					matchContains: true,
					//autoFill: true,
					max:2000,
					formatItem: function(row, i, max) {
						return  row.name ;
					},
					formatMatch: function(row, i, max) {
						return  row.name;
					},
					formatResult: function(row) {
						return row.name;
					}
				}).result(function(event, row) {
					//选中后触发事件
					alert(row.id);
				});
			}
		});
	});
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
			<a class="brand" href="#">CMIS数据同步系统</a>
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
	<form name="loginForm" method="post" id="loginForm" action="${ctx}/demo/DemoAction.a">
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
							<input type="password" class="" id="j_password" name="pwd" value="${pwd}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">学期</label>
						<select name="termid" id="termid">
               				<app:selectSchoolYear selectValue="${TERM_TYPE}"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">关系人选择</label>
						<select name="termid" id="termid">
               				<app:appraiserSelect selectValue="4"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">初中学期选择</label>
						<select name="termid" id="termid">
               				<app:highSchoolTermTag selectClassid="158227" selectNum="" levelCode="2012002"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">高中或者内高班学期选择</label>
						<select name="termid" id="termid">
               				<app:highSchoolTermTag selectClassid="150845" selectNum="" levelCode="2012003"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">上传文件</label>
						<input type="file" name="upload_file" id="upload_file" multiple="multiple"></input>
					</div>
					<!-- <div class="control-group">
						<label class="control-label" for="password">上传文件</label>
						<input type="file" name="upload_file"></input>
					</div> -->
					<div class="control-group">
						<label class="control-label" for="password">角色标签</label>
						<select name="termid" id="termid">
               				<app:userRoleSelect campuseId="11888" userId="386401" selectRoleId=""/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">科目标签</label>
						<select name="termid" id="termid">
               				<app:subjectSelectTag levelCode="2012003" subjectName="数学"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">校区标签</label>
						<select name="termid" id="termid">
               				<app:userSchoolTag userId="445101" selectValue=""/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">学段标签</label>
						<select name="termid" id="termid">
               				<app:edusysSelectTag campuseId="11306"/>
              			</select>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">动态匹配</label>
						<input style="width:129px;" type="text" id="demo_test" size="32"/>
					</div>
				</fieldset>
				<div id="remember-me" class="pull-left">
				</div>
				<div class="pull-right">
					<input type="button" class="btn btn-warning btn-large" onclick="formSub()" value="登录">
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
		<p style="margin:0 auto;width:400px;">版本号：2.0.3</p>
		<p style="margin:0 auto;width:400px;">©技术支持：©北京教育网络和信息中心 服务热线：4006515253</p>
	</div> 
	
</div>
  </body>
</html>
