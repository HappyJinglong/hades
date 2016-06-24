<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>北京市中小学数据上传页</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />    
    
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />  
    <link href="${ctx}/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="${ctx}/css/font-awesome.css" rel="stylesheet" /> 
    <link href="${ctx}/css/adminia.css" rel="stylesheet" />
    <link href="${ctx}/css/adminia-responsive.css" rel="stylesheet" />  
    <%-- <script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>  --%>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui.css" />
    
	
	<script src="${ctx}/jquery/jquery-1.4.4.min.js " type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script>
	
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
  <style>
	  body{TEXT-ALIGN: center;}
	  
	  #container{ MARGIN-RIGHT: auto;
	  
		  MARGIN-LEFT: auto;
		  height:200px;
		  background:#F00;
		  width:400px;
		  vertical-align:middle;
		  line-height:200px;
	  }
	  
	  .pull-left{
	  	float: left;
	  	font-size: 16px;
	  	font-weight: 600;
	  	 margin-bottom: 1.25em;
	    padding: 5px 10px;
	  }
	  input[type="file"]{
	  	height: 40px;
	  }
	  .widget-content {
		    background: #fff none repeat scroll 0 0;
		    border: 1px solid #d5d5d5;
		    border-radius: 0px;
		    padding: 4px;
	}
	#hidebg { position:absolute;left:0px;top:0px;

      background-color:#000;

      width:100%;  /*宽度设置为100%，这样才能使隐藏背景层覆盖原页面*/

      filter:alpha(opacity=60);  /*设置透明度为60%*/

      opacity:0.6;  /*非IE浏览器下设置透明度为60%*/

      display:none;

      z-Index:2;
      }
	#fullbg {
		background-color:gray;
		left:0;
		opacity:0.5;
		position:absolute;
		top:0;
		z-index:3;
		filter:alpha(opacity=50);
		-moz-opacity:0.5;
		-khtml-opacity:0.5;
		}
	#dialog {
		background-color:#fff;
		border:5px solid rgba(0,0,0, 0.4);
		height:400px;
		left:35%;
		margin:-200px 0 0 -200px;
		padding:1px;
		position:fixed !important; /* 浮动对话框 */
		position:absolute;
		top:50%;
		width:800px;
		z-index:5;
		border-radius:5px;
		display:none;
		}
	#dialog p {
		margin:0 0 12px;
		height:24px;
		line-height:24px;
		background:#CCCCCC;
		}
	#dialog p.close {
		text-align:right;
		padding-right:10px;
	}
	#dialog p.close a {
		color:#000000;
		text-decoration:none;
	} 
  </style>
  <!-- <script type="text/javascript">
	//显示灰色 jQuery 遮罩层
	function showBg() {
		var bh = $("body").height();
		var bw = $("body").width();
		var hideobj=document.getElementById("hidebg");
		hidebg.style.height=window.document.body.offsetWidth+"px";
		hidebg.style.display="block";  //显示隐藏层
		$("#fullbg").css({
		height:bh,
		width:bw,
		display:"block"
		});
	 $("#dialog").show();
	}
	//关闭灰色 jQuery 遮罩
	function closeBg() {
		document.getElementById("hidebg").style.display="none";
		$("#fullbg,#dialog").hide();
	}
	</script> -->
<body>
<div id="hidebg"></div>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			
			<a class="brand" href="#">北京市高中学校CMIS40库、课程管理系统数据库以及综合素质评价附件上传</a>
			
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->
<div id="content">
	<div class="container">
		<div class="row">
			<div class="span9" style="width:1100px;margin-left:-50px">
				<h1 class="page-title">
					CMIS40数据库、课程管理系统数据库以及综合素质评价附件各区县上传情况统计页
				</h1>
				<div class="widget">														
					<div class="widget-content">
						<table width="100%"  border="1" cellpadding="0" cellspacing="1" id="msglists">
						<tr height="45px">
							<td colspan="8">北京市各区县文件上传统计列表</td>
						</tr>
						<tr height="45px">
							<td rowspan="2">序号</td>
							<td rowspan="2">区级</td>
							<td colspan="3">已上传数量</td>
							<td colspan="3">未上传数量</td>
						</tr>
						<tr height="45px">
							<td>CMIS40库</td>
							<td>综素附件</td>
							<td>课程管理数据库</td>
							<td>CMIS40库</td>
							<td>综素附件</td>
							<td>课程管理数据库</td>
						</tr>
						<c:forEach items="${countryInfoDtos}" var="item" varStatus="status">
						<tr height="45px">
							<td>${status.count}</td>
							<td>${item.name}</td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=cmisdb&&discode=${item.discode}&&isupload=done&&mydiscode=${discode}&&discodename=${item.name}" style="color:#000000">${item.cmisdbover}</a></td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=attchfile&&discode=${item.discode}&&isupload=done&&mydiscode=${discode}&&discodename=${item.name}" style="color:#000000">${item.attchfileover}</a></td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=coursedb&&discode=${item.discode}&&isupload=done&&discodename=${item.name}&&mydiscode=${discode}" style="color:#000000">${item.coursedbover}</a></td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=cmisdb&&discode=${item.discode}&&mydiscode=${discode}&&discodename=${item.name}" style="color:#000000">${item.cmisdbunover}</a></td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=attchfile&&discode=${item.discode}&&mydiscode=${discode}&&discodename=${item.name}" style="color:#000000">${item.attchfileunover}</a></td>
							<td><a href="${ctx}/fileupload/FileUploadAction.a?queryStudentContent&&db=coursedb&&discode=${item.discode}&&mydiscode=${discode}&&discodename=${item.name}" style="color:#000000">${item.coursedbunover}</a></td>
						</tr>
						</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div> 
	</div>  
</div>
</body>
</html>
