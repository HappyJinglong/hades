<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<un:bind var="STUDENT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_STUDENT"/>    <%-- 学生 --%>
<un:bind var="PARENT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_PARENT"/>                                       <%--家长  --%>
<un:bind var="SPORT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_SPORTSEMASTER"/>                                <%--德育老师  --%>
<un:bind var="COURSE" type="com.flyrish.hades.common.Constant" field="USER_TYPE_COURSEMASTER"/>                                <%--任课老师  --%>
<un:bind var="MASTER" type="com.flyrish.hades.common.Constant" field="USER_TYPE_CLASSMASTER"/>                                 <%-- 班主任 --%>
<un:bind var="SCHOOLADMIN" type="com.flyrish.hades.common.Constant" field="USER_TYPE_SCHOOLADMIN"/>                                 <%-- 教务老师 --%>
<un:bind var="CONSTY" type="com.flyrish.hades.common.Constant" field="USER_KIND_COUNTY"/>                                   <%-- 区县 --%>
<un:bind var="ADMIN" type="com.flyrish.hades.common.Constant" field="USER_KIND_CITY"/>                                     <%-- 市级 --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx }/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>通告录入</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
 </style>
 <script type="text/javascript">
         $(document).ready(function() {
        	<c:forEach items="${receiverObje}" var="ls">
            $("[name='radio']").each(function(){
            	if($(this).val().indexOf('${ls}')>=0){
            		$(this).attr("checked",'true');
            	}
            });
            </c:forEach>
            <c:forEach items="${levelObj}" var="lls">
            $("[name='radio2']").each(function(){
            	if($(this).val().indexOf('${lls}')>=0){
            		$(this).attr("checked",'true');
            	}
            });
            </c:forEach>
	    });
        function dodown()
         {
         	var url = "${ctx}/inform/InformAction.a?downAttach&filename=${information.filename}&filepath=${information.filepath}";
     	    document.location.replace(url);
     	    return false;
         }
        function setflag(obj){
        	$('#isDelAttachPath').val('1');
        	$(obj).parent().empty();
        }
	 	function checkNumber(tt, num) {
			var content = tt.val();
			if (content.length > num) {
				alert("内容不能超过" + num + "个字");
			}
		}
	 	function checkNumber1(tt, num) {
			var content = tt.val();
			if (content.length > num) {
				alert("主题不能超过" + num + "个字");
			}
		}
		function goBack() {
			/* window.history.back(-1) */
			$("#form").submit();
		}
		//生成uuid方法
		function generateUUID() {
			var s = [];
			var hexDigits = "0123456789abcdef";
			for ( var i = 0; i < 36; i++) {
				s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
			}
			s[14] = "4";
			s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
			s[8] = s[13] = s[18] = s[23] = "-";
			return s.join("");
		} 
		var uuid;
		 //上传附件并且验证后增加公告
		function submitForm(url,message) {
			var filepath = $("#upload_file").val();
			if (filepath.length > 0) {
				var interval;
				var paths = new Array();
				paths = filepath.split("\\");
				var filename = paths[paths.length - 1].split(".")[0];
				$("#flag").attr("value","1");
				if (filename.length <= 25) {
					uuid = generateUUID();
					$("#uuid").attr("value", uuid);
					$.ajaxFileUpload({
						url : '${nginxServer}/upload?X-Progress-ID=' + uuid
								+ '&uuid=' + uuid, // 上传文件的服务器地址
						sercureuri : false,
						fileElementId : 'upload_file', // 文件选择框的id属性  
						dataType : 'json' // 服务器返回的格式
					});
					interval = window.setInterval(function() {
					$.ajax({
								type : 'get',
								async : false,
								url : '${ctx}/upload/UploadAction.a?getUploadFileProgress&X-Progress-ID='
										+ uuid+'&'+new Date().getTime(),
								dataType : 'text',
								success : function(data) {
									var json = eval(data);
									if (json.state =='starting'||json.state == 'uploading'&&json.size<1024000||json.state == 'done') {
						              if(json.state == 'done'){
						            	  window.clearTimeout(interval);
										  if (validate()) {
											updateInform(url,message,uuid);
										  }else{
											  $("#add").attr("disabled",false);
										  }
						              }
									} else {
										$("#add").attr("disabled",false);
										window.clearTimeout(interval);
										if (json.state == 'error') {
											fail_notice_word("文件上传失败");
											return;
										} else {
											fail_notice_word("文件超过1M");
											return;
										}
									}
								}
							});
					},200);
				} else {
					alert("文件名太长,不能上传");
					$("#add").attr("disabled",false);
				}
			} else {
				if (validate()) {
					updateInform(url,message,uuid);
				}else{
					return;
				}
			}
		} 
		 function checkDate(checkName) {
			var date = $("#" + checkName).val();
			if (date.length == 0) {
				alert("请选择日期");
				return true;
			}
		}
        //当复选框没选中时提示
		function checkRadio(checkName, objName) {
			if (!checkSelect(checkName)) {
				alert("请选择" + objName);
				return true;
			} else {
				var box = $("[name=" + checkName + "]:checked");
				var val = "";
				$.each(box, function(i, v) {
					val += i > 0 ? "," + v.value : v.value;
				});
				$("#receiver_" + checkName).attr("value", val);
			}
		}
		//判断复选框是否选中
		function checkSelect(checkName) {
			var selected = false;
			$("input[name="+checkName+"]:checkbox").each(function() {
				if ($(this).attr("checked")) {
					selected = true;
				}
			});
			return selected;
		}
		function validate() {
			if (checkRadio("radio", "接收对象")) {
				$("#add").attr("disabled",false);
				return;
			}
			if (checkRadio("radio2", "接收学段")) {
				$("#add").attr("disabled",false);
				return;
			}
			var themeContent = $("#theme").val();
			if (themeContent == null || themeContent == "") {
				$("#add").attr("disabled",false);
				alert("主题不能为空");
				return;
			} else if (themeContent.length > 25) {
				$("#add").attr("disabled",false);
				alert("主题内容不能超过25个字");
				return;
			}
			var informContent = $("#informContent").val();
			if (informContent.length > 300) {
				$("#add").attr("disabled",false);
				alert("通知公告的内容不能超过300字");
				return;
			}
			if (checkDate("startDate")) {
				$("#add").attr("disabled",false);
				return;
			}
			if (checkDate("endDate")) {
				$("#add").attr("disabled",false);
				return;
			}
			return true;
		}
	    
		function updateInform(url, message,uuid) {
			$.ajax({
				type : "post",
				url : url,
				async : false,
				data : {
					"uuid" : uuid,
					"theme" : $("#theme").val(),
					"receiverObj" : $("#receiver_radio").val(),
					"objlevel" : $("#receiver_radio2").val(),
					"informContent" : $("#informContent").val(),
					"attachflag" : $("#flag").val(),
					"informid" : $("#informid").val(),
					"endDate" : $("#endDate").val(),
					"startDate" : $("#startDate").val(),
					"isDelAttachPath":$('#isDelAttachPath').val()
				},
				dataType : "json",
				success : function(data) {
					var content = data.fin;
					if (content == true) {
						$("#noticeflag").attr("value","1");
						$("#form").submit();
					} else {
						fail_notice_word(message);
					}
				},
				error : function(err) {
					alert(message);
				}
			});
		}
		//新增通告
		function updateInform2() {
			$("#add").attr("disabled","disabled");
			var url = "${ctx}/inform/AddInformAction.a?insertInform";
			submitForm(url, "增加通告失败");
		}
		//更新通告
		function updateInform1() {
			$("#add").attr("disabled","disabled");
			var url = "${ctx}/inform/AddInformAction.a?updateInformContent";
			submitForm(url, "更新通告失败");
		}
	</script>
</head>
<body style="background: #EEE">
<form id="form" name="form" action="${ctx}/inform/InformAction.a?queryInformByCon"  method="post">
    <input type="hidden" id="receiver_radio" name="receiverObj"/>
    <input type="hidden" id="receiver_radio2" name="objlevel"/>
    <input type="hidden" id="uuid" name="uuid"/>
    <input type="hidden" name="pageNu" id="pageNu" value="${pageNu1 }"/>
    <input type="hidden" name="pageNum" id="pageNum" value="${pageNum1 }"/>
    <input type="hidden" id="flag" name="flag"/>
    <input type="hidden" id="noticeflag" name="noticeflag"/>
    <input type="hidden" id="theme1" name="theme" value="${theme }"/>
    <input type="hidden" id="publicState1" name="publicState" value="${publicState }"/>
    <input type="hidden" id="startDate1" name="startDate" value="${startDate }"/>
    <input type="hidden" id="endDate1" name="endDate" value="${endDate }"/>
    <input type="hidden" id="informid" name="informid" value="${information.informid }"/>
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 通知公告</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">增加/修改</a>
		</div>
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px">
							<div  style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								通知公告信息
						  </div>
							<div class="widget-content nopadding">
							
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr >
											<td width="30%"  style="text-align: right;">接收对象：</td>
											<td width="70%"   style="text-align: left;">
											<c:if test="${usertype eq ADMIN }"><!-- 市级用户 -->
											   <input type="checkbox"  name="radio" value="${CONSTY}"/>&nbsp;&nbsp;区县&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${SPORT },${SCHOOLADMIN}"/>&nbsp;&nbsp;学校 &nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${MASTER },${COURSE }"/>&nbsp;&nbsp;教师&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${STUDENT }"/>&nbsp;&nbsp;学生&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${PARENT }"/>&nbsp;&nbsp;家长
											</c:if>
											<c:if test="${usertype eq CONSTY}"><!-- 区县用户 -->
											   <input type="checkbox" name="radio" value="${SPORT },${SCHOOLADMIN}"/>&nbsp;&nbsp;学校 &nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${MASTER },${COURSE }"/>&nbsp;&nbsp;教师&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${STUDENT }"/>&nbsp;&nbsp;学生&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${PARENT }"/>&nbsp;&nbsp;家长
											</c:if>
											<c:if test="${userRealtype eq SPORT or userRealtype eq SCHOOLADMIN }"><!-- 教务老师、德育老师 -->
											   <input type="checkbox" name="radio" value="${MASTER }"/>&nbsp;&nbsp;班主任&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${COURSE }"/>&nbsp;&nbsp;任课教师&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${STUDENT }"/>&nbsp;&nbsp;学生&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${PARENT }"/>&nbsp;&nbsp;家长
											</c:if>
											<c:if test="${userRealtype eq MASTER }"><!-- 班主任 -->
											   <input type="checkbox" name="radio" value="${STUDENT }"/>&nbsp;&nbsp;学生&nbsp;&nbsp;&nbsp;&nbsp;
											   <input type="checkbox" name="radio" value="${PARENT }"/>&nbsp;&nbsp;家长
											</c:if>
                                            <span style="color: red">*</span>
											</td>
											
										</tr>
										<tr>
										  <td  style="text-align: right;">接收学段：</td>
										  <td  style="text-align: left;">
										  <c:forEach items="${levelString}" var="levelcode">
										    <c:if test="${levelcode eq '2012002' }">
										       <input type="checkbox"  name="radio2" value="2012002"/>
										       &nbsp;&nbsp;初中&nbsp;&nbsp;&nbsp;&nbsp;
										    </c:if>
										    <c:if test="${levelcode eq '2012003' or levelcode eq '2012004'}">
										       <input type="checkbox" name="radio2" value="2012003,2012004"/>
										       &nbsp;&nbsp;高中&nbsp;&nbsp;&nbsp;&nbsp;
										    </c:if>
										    </c:forEach>
										    <span style="color: red">*</span></td>
									  </tr>
										<tr>
										  <td style="text-align: right;">主题：</td>
										  <td style="text-align: left;"><span class="controls">
										    <input id="theme" name="theme" style="width: 480px " type="text" height="28px" placeholder="最多25个汉字" onblur="checkNumber1($(this),25)" value="${information.theme }"/>
										    <span style="color: red">*</span> </span><span></span></td>
									  </tr>
										<tr>
										  <td style="text-align: right;">内容描述：</td>
										  <td style="text-align: left;"><span class="controls">
                                            <textarea id="informContent" name="informContent" style="height:120px;width:580px;" onblur="checkNumber($(this),300);">${information.text }</textarea>
										  </span></td>
									  </tr>
										<tr>
											<td style="text-align: right;">有效日期：</td>
											<td style="text-align: left;"><span class="controls"> <input
										id="startDate" style="width: 100px" type="text" height="28px"
										placeholder="开始日期" name="startDate"  value="${information.startDate }"
										onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" />
										至 <input id="endDate" name="endDate" style="width: 100px" type="text" height="28px" placeholder="结束日期" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" value="${information.endDate }"/> 
                                               <span style="color: red">*</span>
											</span><span></span>
											</td>
										</tr>
										<c:if test="${not empty information.filename}">
										<tr>
											<td style="text-align: right;">修改附件文件：</td>
											<td style="text-align: left;"><input type="file" value="浏览" id="upload_file" name="upload_file"/></td>
										</tr>
										</c:if>
										<c:if test="${empty information.filename}">
										<tr>
											<td style="text-align: right;">附件文件：</td>
											<td style="text-align: left;"><input type="file" value="浏览" id="upload_file" name="upload_file"/></td>
										</tr>
										</c:if>
										<c:if test="${not empty information.filename}">
										<tr>
											<td style="text-align: right;">附件文件：</td>
											<input type="hidden" name="isDelAttachPath" id="isDelAttachPath"></input>
											<td style="text-align: left;"><a name="down" href="javascript:void(0);" style="cursor:pointer;color:black;" onclick="dodown();">${information.filename}</a>
											<a onclick="setflag(this)" href="javascript:void(0);"><img style="width: 10px; height: 10px;" src="${ctx}/images/upload_del.gif" border="0"></img></a>
											</td>
										</tr>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
						<div class="pagination alternate"  style="border: 1px solid #CDCDCD;margin: 0px 13px 5px;height: 40px;line-height: 20px" >
							<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
							 <!-- 新增 -->
							   <c:if test="${flag eq '2' }">
								 <input id="add" type="button" name="queryInformByCon" class="btn btn-success" style="width: 70px;margin-top: 5px;" onclick="return updateInform2();" value="保存" />
							   </c:if>
							   <!-- 保存 -->
							   <c:if test="${flag eq '1' }">
							       <input id="add" type="button" name="queryInformByCon" class="btn btn-success" style="width: 70px;margin-top: 5px;"  onclick="return updateInform1();" value="保存" />
							   </c:if>
								<input type="button" class="btn btn-success" style="width: 70px;margin-top: 5px;" id="query" onclick="goBack();" value="返回" />
						     </span>
						</div>
		</div>
	</div>
	</form>
</body>
</html>