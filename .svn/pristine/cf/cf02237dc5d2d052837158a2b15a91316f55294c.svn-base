<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%-- <%
    request.getSession(true).invalidate();
    Cookie[] cookies = request.getCookies();
    if( cookies != null && cookies.length > 0 ) {
        Cookie cookie = cookies[0];
        cookie.setMaxAge(0);
    }
%>  --%>
<html>
<head>
<title>北京市中小学生综合素质评价系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/login1.css" />
<script src="${ctx}/js/func.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script src="${ctx}/js/popup_layer.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/AdminRoleSelect.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<un:bind var="NGINX_SERVER" type="com.flyrish.hades.common.Constant"
	field="NGINX_SERVER"/>
<style type="text/css">

</style>
<script type="text/javascript">
if(self!=top){top.location=self.location;}
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
	 		if(Sys.ie<9.0){
				alert('为体验本系统最佳效果，建议使用IE9及以上版本！');
	 		}
	 	}
window.onkeydown = (function(event){
	 if(event.keyCode == 13){
		formSub();
	} 
});
	function ico_refresh(){
	 	document.getElementById("checkCodeId").src="${ctx}/captcha.jpg?loginPoint=loginPoint&random="+new Date().getTime();
	}
	function formSub(){
		var fm = document.forms[0];
		fm.j_username.value = fm.j_username.value.trim();
		//判断用户名是否为空
		if(fm.j_username.value.trim() ==""){
			fm.j_username.focus();
			alert("请填写用户名");
			return false;
		}
		
		fm.j_password.value = fm.j_password.value.trim();
		//判断密码是否为空
		if(fm.j_password.value.trim() ==""){
			fm.j_password.focus();
			alert("请填写密码");
			return false;
		} 
		
		var result = false;
	    dwr.engine.setAsync(false);
		AdminRoleSelect.checkUserSchoolCount(fm.j_username.value,fm.j_password.value,function(obj){
				result = obj;
		});
	    dwr.engine.setAsync(true);
	    if(result==null){
	    	alert('网络异常，请稍后再试！');
// 	    	return;			result = true
	    }
	    if(result){
	    	fm.submit();
	    }else{
	    	AdminRoleSelect.querySchoolInfoDtoByUserName(fm.j_username.value,fm.j_password.value,function(obj){
				if(null == obj){
					result = false;
				}
				DWRUtil.removeAllOptions('userSchoolId');
				DWRUtil.addOptions('userSchoolId', obj,'schoolId','schoolName');
			});
			document.getElementById("divshow").style.display="";
	    }
	}
	function information(){
		$("#showDiv").css("background-color","#000");
		$("#showDiv").css("z-index","99");
		$("#showDiv").css("opacity","0.35");
		$("#fbmodal").css("display","block");
	}
	function init(){
		clearKuang("qust_show");
		document.getElementById("j_username").focus();
		document.getElementById("codeid").click();
		//ico_refresh();
		if(errorFlag||logout=='true'){
			$(".popupLayer").css("display","none");
			$("#showDiv").css("background-color","");
			$("#showDiv").css("z-index","-1");
			$("#showDiv").css("opacity","0"); 
		}else{
			$(".popupLayer").css("display","block");
			$(".popupLayer").css("left","25%");
			$(".popupLayer").css("top","18%");
			$("#showDiv").css("background-color","#000");
			$("#showDiv").css("z-index","1");
			$("#showDiv").css("opacity","0.35");  
		}
	}
	function closeTip(){
		$("#showDiv").css("background-color","");
		$("#showDiv").css("z-index","-1");
		$("#showDiv").css("opacity","0");
		$("#fbmodal").css("display","none");
		$("#j_username").focus(); 
	}
	var errorFlag = "";
	var logout = "";
	<%
	//判断session中的错误信息
	String error = request.getParameter("error");
	String logout = request.getParameter("logout");
	%>
		logout="<%=logout%>";
	<%
	if (error != null) {
		if ("user_pwd_error".equals(error)) {
			error = "对不起，您输入的用户名或密码错误，请重新输入！";
		}else if ("check_code_error".equals(error)) {
			error = "验证码错误，请重新登录！";
		}else if ("is_not_userd_error".equals(error)){
			error = "用户已被禁用，不能登录系统！";
		}else if("user_notfoundschool_error".equals(error)){
			error = "用户信息无效，不能登录！";
		}else if ("student_info_error".equals(error)) {
			error = "用户信息无效，不能登录！";
		}else if ("teacher_info_error".equals(error)) {
			error = "初高中年级任课教师和班主任才能登录系统！";
		}else if ("db_error".equals(error)) {
			error = "数据库暂时无法连接，请稍后重试！";
		}else if ("parent_info_error".equals(error)) {
			error = "暂不支持家长用户，不能登录！";
		}else if("notfoundHigOrMid_error".equals(error)){
			error = "只支持含有初高中学校的用户登录！";
		}else if("remote_server_error".equals(error)){
			error = "认证服务器连接异常，请稍后再试！";
		}else {
			error = "系统暂时无法访问，请稍候重试！";
		}%>
		alert("<%=error%>");
		errorFlag="<%=error%>";
		<% 
	}	
	%> 
	function formSubAll(){
		var fr = document.getElementById('loginForm');
		document.getElementById("divshow").style.display="none";
		fr.submit();
	}
	function closeDiv(){
		document.getElementById("divshow").style.display="none";
	}
	
	
</script>
<script type="text/javascript">
	var t = n = 0, count;
	$(document).ready(function(){	
		count=$("#banner_list a").length;
		$("#banner_list a:not(:first-child)").hide();
		$("#banner li").click(function() {
			var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4
			n = i;
			if (i >= count) return;
			$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(500);
			
		});
		t = setInterval("showAuto()", 2000);
		
	})
	
	function showAuto()
	{
		n = n >=(count - 1) ? 0 : ++n;
		$("#banner li").eq(n).trigger('click');
	}
	/* 弹出框淡出  */
	function clearKuang(id){
		var t9 = new PopupLayer({trigger:"#"+id,popupBlk:"#fbmodal",closeBtn:"#cancel",
			useOverlay:false,useFx:true,offsets:{x:100,y:100}});
			t9.doEffects = function(way){
			    if(way == "open"){
			    	$(".popupLayer").css("display","block");
			    	$(".popupLayer").css("left","25%");
			    	$(".popupLayer").css("top","17%");
			    	$("#showDiv").css("background-color","#000");
			    	$("#showDiv").css("z-index","1");
			    	$("#showDiv").css("opacity","0.35"); 
			    }
			    else{
			        this.popupLayer.animate({
			            left:"30%",
			            top:"20%",
			            opacity:0.1
			        },{duration:500,complete:function(){
			            this.popupLayer.css("opacity",1);this.popupLayer.hide()}.binding(this)});
				    $("#showDiv").css("background-color","");
					$("#showDiv").css("z-index","-1");
					$("#showDiv").css("opacity","0"); 
					ico_refresh();
					$("#j_username").focus(); 
			    }
			    
			}
	}
</script>

</head>

<body onload="init();">

<!--div class="bigBwrapper"-->

<form name="loginForm" id="loginForm" method="post" action="${ctx}/j_acegi_security_check"> 
	<div style="background:url(${ctx }/images/sb.png);height:100%;width:100%;position:absolute;z-index:99;display:none;" id="divshow">
		<div style="background:url(${ctx }/images/tck.jpg) no-repeat;height:500px;width:800px;margin:50px auto;text-align:center;">
	 
	        <div style="padding-top:160px;" >
	        	<select style="height:30px;min-width:200px;" id="userSchoolId" name="userSchoolId">
	            </select>
	        </div>
	        <div style="width: 330px; height: 60px; padding-top: 30px; padding-left: 20px; margin-top: 14px; margin-right: auto; margin-bottom: 14px; margin-left: auto;">
	        	 <a style="width:100%;height:38px;color:#FFF;display:block; background-image:url('${ctx}/images/btn.jpg'); line-height:30px;font-size:14px;float:left;" onclick="closeDiv()">取消</a> 
	                      
	            <a   style="width:100%;height:38px;color:#FFF;display:block;background-image:url('${ctx}/images/btn.jpg');line-height:30px;font-size:14px;float:right; margin-top:30px;"   onclick="formSubAll()">确定</a>
	        </div>
	    </div>
	</div>
	<div id="showDiv"></div>
	<div class="login-ct" id="dslmain">
		<div class="pad">
			<div style="text-align: center">
				<span style="color:white;font-size:18px">家长用户已开通：用户名为学生的教育Id号加p，初始密码为该学生出生日期8位数。</span>
			</div>
			<div class="bigB clear">
				<div class="mar">
					<div class="leftB">
	    				<div class="upB clear">
	        				<div id="top_left">
	                			<div class="blueB tm"></div>
	                			<img src="${ctx }/imgs/build.png" class="build">
	                			<p class="version">北京教育网络和信息中心</p>
	            			</div>
	            
	           				<div id="top_center">
	               				<div class="blueB1"></div>
	               				<div class="title">
				         			<div id="banner">	
	                            		<ul>
	                            	 		<li class="on">1</li>
	                                 		<li>2</li>
	                                 		<li>3</li>
	                                 		<li>4</li>
	                             		</ul>    
		                            	<div id="banner_list">
			                                 <a  target="_blank"><img src="${ctx }/imgs/p4.png" title="" alt="" /></a>
			                                 <a  target="_blank"><img src="${ctx }/imgs/p2.png" title="" alt="" /></a>
			                                 <a  target="_blank"><img src="${ctx }/imgs/p3.png" title="" alt="" /></a>
		                             	</div>
	                     			</div> 
				   				</div>
	               				<p class="name" ><img src="${ctx }/imgs/name1.png" ></p>
	               
	          				 </div>
	           
	           
	       				</div>
						<div class="clear">
				        	<div id="dw_left">
				               <div class="greenB"></div>
				               <div class="user">
				                	<div class="userB"><p class="userN" >用户名：</p><input type="text" name="j_username" id="j_username" style="width:120px;height:30px; line-height:30px;float:left;margin-right:10px;" >
					                    <p class="userN">密码：</p><input type="password" name="j_password" id="j_password" type="password" style="width:120px;height:30px;line-height:30px;float:left;margin-right:10px;">
					                    <p class="userN">验证码：</p><input type="text" name="j_captcha_response" id="j_captcha_response" style="width:80px;height:30px;line-height:30px;float:left;margin-right:10px;">
					                    
					                    <a href="#" onclick="ico_refresh();" id="codeid">
											<img id="checkCodeId" src="" alt="请点击刷新" style="float:left;padding-top:5.5px;"/>
										</a>
				                    </div>
				               </div>
							</div>
				           
				            <div id="loginB">
					            <div class="purple"> </div>
					            <div class="login"><a href="#" onclick="formSub()" class="login_dl">登录</a><a href="http://211.153.82.89:8080/am/portalManage/resetUserNameAction1.action" class="login_id">找回教育ID</a><span>|</span><a href="http://211.153.82.89:8080/am/portalManage/resetPwdAction1.action" class="psw_r">重置密码</a></div>
							   
				       		</div>
				       </div>
	    		</div>
	     		<div class="rightB">
	    
	    			<div id="top_right" name="keyidianji" style="display:block;">
	    				<div class="greenB1"></div>
		           			<a href="#" id="dslzhidao"><img src="${ctx }/imgs/book.png" class="book"></a>
		           			<a class="jueseshuoming">各角色操作手册</a>
		           			<span><a href="${NGINX_SERVER}/czsc/zswtzl.docx" style="position: absolute;top: 154px;left: 16px;color: #fff;font-weight: bold;letter-spacing: 2px;font-family: '微软雅黑';font-size: 16px;text-decoration: underline;"><font color="red">*</font>问题解答</a></span>		           			<span><a href="${NGINX_SERVER}/czsc/xs.pptx" class="student">学生</a></span>
		           			<span><a href="${NGINX_SERVER}/czsc/bzr.pptx" class="headmaster" >班主任</a></span>
		           			<span><a href="${NGINX_SERVER}/czsc/rkls.pptx" class="teachingteacher" >任课老师</a></span>
		           			<span><a href="${NGINX_SERVER}/czsc/jwls.pptx" class="educational">教务/德育</a></span>		           			<span><a href="${NGINX_SERVER}/czsc/gzbgc.pptx" class="heightreport">高中报告册</a></span>
			   			
	       			</div> 
	    			<div id="top_right"  name="bukeyidianji" style="display:none;">
	    				<div class="greenB1"></div>
		           			<img src="${ctx }/imgs/book.png" class="book">
		           			<span class="student">学生</span>
		           			<span class="headmaster">班主任</span>
		           			<span class="teachingteacher">任课老师</span>
		           			<span class="educational">教务</span>
			   			
	       			</div>
	       
			       <div id="dw_right">
				       <div class="blueB2 tm"></div>
				       <img src="${ctx }/imgs/tool.png" class="tool">
			       	   <p class="phone">使用浏览器：IE9及以上版本</p>
	        		   <p class="phone2">©北京教育网络和信息中心<br>服务热线：010-58964500<br><span style="margin-left: 60px;">400-9100915</span><br><span style="margin-left: 60px;">400-6515253</span><br>客服时间：9:00-17:00(一至五)</p>
			       </div>
	    		</div> 
				</div>  
			</div>
		</div>
	</div>
	<div class="qust_show" id="qust_show" style="margin-top:13%;  background-image:url('${ctx}/images/biankuang1.jpg');display:inline;float:left;background-size:100% 100%;"> 
		<a style="cursor:pointer;color: #00DD00;font-size: 15px;"><br/>
			<span>登</span><br/>
		    <span>录</span><br/>
		    <span>说</span><br/>
		    <span>明</span><br/>
		</a> 
	</div>

	<div id="fbmodal" style="display:none;">
		<div class="popup">
			<table>
				<tbody>
					<tr>
						<td class="tl"></td>
						<td class="b"></td>
						<td class="tr"></td>
					</tr>
					<tr>
						<td class="b"></td>
						<td class="body">
							<div class="title">学生、家长及教师登陆账号说明（家长用户已开通：用户名为学生的教育Id号加p，初始密码为该学生出生日期8位数。）</div>
							<div class="container">
								<div class="content" style="width: 771px;height:322px;">
									<p style="line-height: 2; text-indent: 1.2em;">
										1.本系统通过北京市教育认证中心实现实名身份认证，学生、教师和家长登录使用北京市级教育应用的通用账号 密码（与北京数字学校、初中开放学习管理服务平台等其他市级应用一致）。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;2.账号为学生教师的教育ID号。教育ID号为8位数字，是北京市学生教师的唯一身份标识，不会因升学转学等原因改变，请牢记。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;3.家长用户：用户名为学生的教育Id号加p，初始密码为该学生出生日期8位数。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;4.忘记教育ID号可通过以下方式找回：<br/>
										&nbsp;&nbsp;（1）在CMIS云平台(cmis.bjedu.cn)中，班主任或学籍管理老师可导出本校学生的教育ID号。<br/>
										&nbsp;&nbsp;（2）老师的教育ID号，可从本校的教职工管理平台(teacher.bjedu.cn)中导出本校教师的教育ID号。<br/>
										&nbsp;&nbsp;（3）在本页面登录框下方的“找回教育ID”链接中，可自行填写信息找回。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;5.忘记密码可通过以下方式进行密码重置：<br/>
										&nbsp;&nbsp;（1）在“找回教育ID”的操作页面中，完成教育ID号找回操作之后，直接可进行重置密码。<br/>
										&nbsp;&nbsp;（2）在本页面登录框下方的“重置密码”链接中，可自行填写信息找回。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;6.此账号密码在北京市级信息系统中通用，登录不同系统均采用同一套账号密码，建议采用自己适合的方式进行妥善保存。<br/>
										&nbsp;&nbsp;&nbsp;&nbsp;7.如果以上说明不能解决您在登录中的问题，请拨打服务电话010-58964500或400-9100915。<br/>
										<!-- <a id="close" onclick="closeTip();" href="javascript: vaoid()">关闭</a> -->
									</p>
								</div>
								<div class="footer">								
									<div class="right">
										<!-- <div class="button_outside_border_blue" id="ok">
											<div class="button_inside_border_blue" id="okay" onclick="closeTip();">确定</div>
										</div> -->										
										<div class="button_outside_border_grey" id="close">											
											<div class="button_inside_border_grey" id="cancel">确定</div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</td>
						<td class="b"></td>
					</tr>
					<tr>
						<td class="bl"></td>
						<td class="b"></td>
						<td class="br"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</form>
</body>
</html>
