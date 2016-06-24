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
  </style>
  <script type="text/javascript">
  window.onload=function(){
	  var schoolcode=$('#schoolcode').val();
	  $.ajax({
	        type: "POST",
	        url: "${ctx}/fileupload/FileUploadAction.a?queryHistoryUploadFile",
	        data:{"schoolcode":schoolcode},
	        dataType: "json",
	        success: function(data){
	        	 var data=eval(data);
	        	 var length=data.val.length;
	        	 if(length>0){
	        		 dowithdata(data);
	        	 }else{
	        		 var node='<tr>'+
						'<td width="10%">'+1+'</td>'+
						'<td width="20%">暂无</td>'+
						'<td width="30%">暂无</td>'+
						'<td width="40%">暂无</td>'+
						'</tr>';
     	 		$('#msglists').append(node);
	        	 }
	        }
	    });
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
			$('#cmis40btn').attr('style','display:none');
			$('#coursebtn').attr('style','display:none');
			$('#attchfilebtn').attr('style','display:none');
			return;
	 	} 
	  
  }
  function dowithdata(data){
	  $('#msglists').empty();
	  var node1='<tr>'+
			'<td width="10%"></td>'+
			'<td width="20%"></td>'+
			'<td width="30%">文件上传历史记录</td>'+
			'<td width="40%"></td>'+
		'</tr>'+
		'<tr>'+
			'<td width="10%">序号</td>'+
			'<td width="20%">上传文件名</td>'+
			'<td width="30%">上传日期</td>'+
			'<td width="40%">当前状态</td>'+
		'</tr>';
		$('#msglists').append(node1);
	  for(var i=0;i<data.val.length;i++){
			 var node='<tr>'+
					'<td width="10%">'+(i+1)+'</td>'+
					'<td width="20%">'+data.val[i].fileName+'</td>'+
					'<td width="30%">'+data.val[i].uploadTime+'</td>'+
					'<td width="40%">'+data.val[i].state+'</td>'+
					'</tr>';
 	 	$('#msglists').append(node);
 	 }
  }
   function ajaxFileUpload(fileid,typeid,uuid,obj){
	   $.ajaxFileUpload({
			url : '${nginxServer}/upload?X-Progress-ID='+uuid+'&uuid='+uuid, // 上传文件的服务器地址
			sercureuri : false,
			fileElementId :fileid, // 文件选择框的id属性  
			dataType : 'json'  // 服务器返回的格式
		});
	  var interval;
	   interval = window.setInterval(function() {
			$.ajax({ 
				type : 'get',
				async : false,
				url: '${ctx}/upload/UploadAction.a?getUploadFileProgress&X-Progress-ID='+uuid+'&'+new Date().getTime(), 
				dataType: 'text',
				error:function(XMLHttpRequest, textStatus, errorThrown){
                   window.clearTimeout(interval);
                   $(obj).attr('disabled',false);
  				 	$(obj).val('点击上传');
  				 	$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
  					//异步请求降低上传次数
  					lessMoreUploadFileCounts();
              },
				success: function(data){
					var json = eval(data);
					var received=json.received;
					var size=json.size;
					var state=Math.floor((parseFloat(received)/parseFloat(size))*100)+"%";
					if(json.state=='starting'){
						$('#'+typeid).get(0).innerHTML='0%';
					}else if(json.state!='starting'&&isNaN(Math.floor((parseFloat(received)/parseFloat(size))*100))&&json.state!='done'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
						window.clearTimeout(interval);
						$(obj).val('点击上传');
						$(obj).attr('disabled',false);
						//异步请求降低上传次数
						lessMoreUploadFileCounts();
						return;
					}else if(json.state!='done'&&state!='100%'&&json.state!='starting'){
						$('#'+typeid).get(0).innerHTML=state;
						if(json.size>5368709120){
							$('#'+typeid).get(0).innerHTML='文件最大上传不得超过5G';
							$('#'+fileid).val('');
							window.clearTimeout(interval);
							$(obj).val('点击上传');
							$(obj).attr('disabled',false);
							//异步请求降低上传次数
							lessMoreUploadFileCounts();
							return;
						}
					}
	                // 上传完毕后清除定时器
	             	if (json.state == 'done'){  
	                 	window.clearTimeout(interval);
	                 	//后台更新数据状态
	             		finishCommonUploadInfo(typeid,uuid,obj);
	            	}  
		    	}
			});
		}, 1500);
   }
   function finishCommonUploadInfo(typeid,uuid,obj){
	   var schoolcode=$('#schoolcode').val();
	   $.ajax({ 
			type : 'post',
			async : false,
			url: '${ctx}/fileupload/FileUploadAction.a?endUploadFileInfo',
			data:{"uuid":uuid,"uploadfiletype":typeid,"schoolcode":schoolcode},
			dataType: 'text',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 $(obj).attr('disabled',false);
				 $(obj).val('点击上传');
				if(typeid=='cmisdb'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}else if(typeid=='attchfile'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}else if(typeid=='coursedb'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}
				//异步请求降低上传次数
				lessMoreUploadFileCounts();
				return;
        	},
			success: function(data){
				 var data=eval('('+data+')');
	        	 var length=data.val.length;
	        	 if(data.issucess){
	        		 $('#'+typeid).get(0).innerHTML='100%';
	        		 $(obj).attr('disabled',false);
	        		 $(obj).val('点击继续上传');
	        	 }else{
	        		$(obj).val('点击上传');
					$(obj).attr('disabled',false);
					if(typeid=='cmisdb'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}else if(typeid=='attchfile'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}else if(typeid=='coursedb'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}
				}
					dowithdata(data);
					return;
	    	}
		});
   }
   function lessMoreUploadFileCounts(){
	   $.ajax({ 
			type : 'post',
			async : false,
			url: '${ctx}/fileupload/FileUploadAction.a?lessOneUploadFileCounts',
			dataType: 'text',
			success: function(data){
	    	}
		});
   }
  function getFileName(o){
		var pos=o.lastIndexOf("\\");
	    return o.substring(pos+1);  
	   }
   //提交到后台共用方法
   function sumitCommonFileInfo(fileid,typeid,initstate,obj){
	   var uuid = generateUUID();
	   //校验是否文件名是否合法，获取文件名，校验其是否合法
	   var filename=getFileName($('#'+fileid).val());
	   //获取文件类型
	   var filetype=filename.substring(filename.lastIndexOf('.')+1);
	   if(filetype!='zip'){
		   $('#'+typeid).get(0).innerHTML='上传文件类型必须为zip格式';
		   return;
	   }
	   var fileOnlyName=filename.substring(0,filename.lastIndexOf('.'));
	   if(typeid=='cmisdb'&&fileOnlyName!='cmis40db'){
		   $('#'+typeid).get(0).innerHTML='上传文件名必须为cmis40db';
		   return;
	   }else if(typeid=='attchfile'&&fileOnlyName!='attachFiles'){
		   $('#'+typeid).get(0).innerHTML='上传文件名必须为attachFiles';
		   return;
	   }else if(typeid=='coursedb'&&fileOnlyName!='CourseDB'){
		   $('#'+typeid).get(0).innerHTML='上传文件名必须为CourseDB';
		   return;
	   }
	   var schoolcode=$('#schoolcode').val();
	   $(obj).attr('disabled','disabled');
	   $(obj).val('正在上传中');
	   $.ajax({ 
			type : 'post',
			async : false,
			url: '${ctx}/fileupload/FileUploadAction.a?startUploadFileInfo',
			data:{"uuid":uuid,"filename":filename,"uploadfiletype":typeid,"schoolcode":schoolcode},
			dataType: 'text',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 $(obj).attr('disabled',false);
				 $(obj).val('点击上传');
				if(typeid=='cmisdb'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}else if(typeid=='attchfile'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}else if(typeid=='coursedb'){
					$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
				}
				return;
         	},
			success: function(data){
				 var data=eval('('+data+')');
				if(data.val){
					//如果后台初始化数据成功，则执行
					$('#'+typeid).get(0).innerHTML=initstate;
					ajaxFileUpload(fileid,typeid,uuid,obj);
				}else{
					 $(obj).attr('disabled',false);
					 $(obj).val('点击上传');
					if(typeid=='cmisdb'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}else if(typeid=='attchfile'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}else if(typeid=='coursedb'){
						$('#'+typeid).get(0).innerHTML='网络异常，上传失败，稍后再试！';
					}
					//异步请求降低上传次数
					lessMoreUploadFileCounts();
					return;
				}
	    	}
		});
   }
   function sumitCmis40Db(obj){
	   sumitCommonFile('upload_cmis30','cmisdb',obj);
   }
   function sumitAttchfile(obj){
	   sumitCommonFile('upload_attchfile','attchfile',obj);
   }
   function sumitCourseDb(obj){
	   sumitCommonFile('upload_course','coursedb',obj);
   }
   function sumitCommonFile(fileid,typeid,obj){
	   //校验是否选择文件
	    $('#'+typeid).get(0).innerHTML='待上传中';
	   if($('#'+fileid).val()==''){
		   alert('请选择传文件');
		   return;
	   }
	   $('#'+typeid).get(0).innerHTML='正在校验文件信息，请稍后。。。';
	   $.ajax({ 
			type : 'post',
			async : false,
			url: '${ctx}/fileupload/FileUploadAction.a?queryUploadFileCounts',
			dataType: 'text',
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if(typeid=='cmisdb'){
					$('#'+typeid).get(0).innerHTML='服务器异常，稍后再试！';
				}else if(typeid=='attchfile'){
					$('#'+typeid).get(0).innerHTML='服务器异常，稍后再试！';
				}else if(typeid=='coursedb'){
					$('#'+typeid).get(0).innerHTML='服务器异常，稍后再试！';
				}
				return;
        	},
			success: function(data){
				 var data=eval('('+data+')');
				if(data.val){
					 sumitCommonFileInfo(fileid,typeid,'校验通过，开始上传中',obj);
				}else{
					if(typeid=='cmisdb'){
						$('#'+typeid).get(0).innerHTML='上传文件用户较多，稍后再试！';
					}else if(typeid=='attchfile'){
						$('#'+typeid).get(0).innerHTML='上传文件用户较多，稍后再试！';
					}else if(typeid=='coursedb'){
						$('#'+typeid).get(0).innerHTML='上传文件用户较多，稍后再试！';
					}
					return;
				}
	    	}
		});
   }
  </script>
<body>
<input type="hidden" id="schoolcode" name="schoolcode" value="${sessionScope.sessionUser.username}"></input>
<%-- <div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 				
			</a>
			
			<a class="brand" href="#">北京市高中学校CMIS40库、课程管理系统数据库以及综合素质评价附件上传</a>
			
			<div class="nav-collapse">
			
				<ul class="nav pull-right">
					
					<!-- <li class="divider-vertical"></li> -->
					
					<li class="dropdown">
						
						<a class="brand " style="color:#ffffff" href="${ctx}/fileupload/LoginFileUploadAction.a?loginOut">
							退出							
						</a>
					</li>
				</ul>
				
			</div> <!-- /nav-collapse -->
			
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> --%> <!-- /navbar -->
<div id="content">
	<div class="container">
		<div class="row">
			<div class="span9" style="width:100%">
				<h1 class="page-title">
				<%-- <c:if test="${not empty sessionScope.sessionUser.schoolName}">
					${sessionScope.sessionUser.schoolName}CMIS40数据库、课程管理系统数据库以及综合素质评价附件上传
				</c:if>	 --%>
				CMIS40数据库、课程管理系统数据库以及综合素质评价附件上传	
				</h1>
				<div class="widget">														
					<div class="widget-content">
						<div class="faq-container">
						<input type="hidden" id="state"></input>
							<form action="#" id="saveAddKeyData"  method="post" >
								<div class="pull-left">
									<h2>CMIS40数据库：</h2>
								</div>
								<div class="pull-left">
								&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name="upload_cmis30" type="file" id="upload_cmis30" value=""/>
								</div>
								<div class="pull-left">
										<h3 id="cmisdb">待上传中</h3>
								</div>
								<div class="pull-right">
								<input type="button" class="btn btn-warning btn-large" id="cmis40btn" onclick="sumitCmis40Db(this);"  value="点击上传">
								</input>
								</div>
							</form>
						</div>
					</div>
					<br/>
					<div class="widget-content">
						<div class="faq-container">
						<input type="hidden" id="state"></input>
							<form action="#" id="saveAddKeyData"  method="post" >
								<div class="pull-left">
									<h2>综合素质评价附件：</h2>
								</div>
								<div class="pull-left">
								&nbsp&nbsp&nbsp&nbsp&nbsp<input type="file" name="upload_attchfile" id="upload_attchfile"></input>
								</div>
								<div class="pull-left">
										<h3 id="attchfile">待上传中</h3>
								</div>
								<div class="pull-right">
								<input type="button" class="btn btn-warning btn-large" id="attchfilebtn" onclick="sumitAttchfile(this)" value="点击上传">
								</input>
								</div>
							</form>
						</div>
					</div>
					<br/>
					<div class="widget-content">
						<div class="faq-container">
						<input type="hidden" id="state"></input>
							<form action="#" id="saveAddKeyData"  method="post" >
								<div class="pull-left">
									<h2>课程管理系统数据库：</h2>
								</div>
								<div class="pull-left">
								<input type="file" name="upload_course" id="upload_course"></input>
								</div>
								<div class="pull-left">
										<h3 id="coursedb">待上传中</h3>
								</div>
								<div class="pull-right">
								<input type="button" class="btn btn-warning btn-large" id="coursebtn" onclick="sumitCourseDb(this)" value="点击上传">
								</input>
								</div>
							</form>
						</div>
					</div>
					<br/>
					<div class="widget-content">
						<div class="faq-container">
							<div class="pull-left">
									<%-- <h2><a href="${ctx}/fileupload/FileUploadAction.a?downloadFile">使用说明文档</a></h2> --%>
									<h2><a href="${ctx}/filehelp/help.html" target="_blank">使用说明文档</a></h2>
								</div>
						</div>
					</div>
					<div class="widget-content">
						<div class="faq-container">
							<div class="pull-left">
									<h2>注意事项：</h2>
								</div>
								<div class="pull-left">
									<h3>1、建议单个文件上传并小于5G，尽量避免多个文件同时上传，否则可能会造成浏览器假死或者上传失败！</h3><br>
								</div>
						</div>
					</div>
					<br/>
					<div class="widget-content">
						<table width="100%"  border="0" cellpadding="0" cellspacing="1" id="msglists">
						<tr>
							<td width="10%"></td>
							<td width="20%"></td>
							<td width="30%">文件上传历史记录</td>
							<td width="40%"></td>
						</tr>
						<tr>
							<td width="10%">序号</td>
							<td width="20%">上传文件名</td>
							<td width="30%">上传日期</td>
							<td width="40%">当前状态</td>
						</tr>
						</table>
					</div>
				</div>
			</div>
			
			
		</div> 
		
	</div> 
	
</div>
<script>
window.onbeforeunload=function(){
	var coursebtnobj=document.getElementById('coursebtn');
	var attchfilebtnobj=document.getElementById('attchfilebtn');
	var cmis40btnobj=document.getElementById('cmis40btn');
	if(coursebtnobj.disabled
			||attchfilebtnobj.disabled
			||cmis40btnobj.disabled){
		return "当前有文件未上传完，确认离开？";
	}
}
</script>

  </body>
</html>
