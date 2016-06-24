<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<un:bind var="KG_COURSE_KIND" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_KIND" />
<un:bind var="KG_COURSE_NEIZHI" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_NEIZHI" />
<un:bind var="USER_MASTERROLE_TYPESTR"
	type="com.flyrish.hades.common.Constant"
	field="USER_MASTERROLE_TYPESTR" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/unicorn.main.css" />

<script type="text/javascript"
	src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css_new/jquery.gritter.css" />
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<script type="text/javascript"
	src="${ctx}/ReportServer?op=emb&resource=finereport.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/ReportServer?op=emb&resource=finereport.css" />


<title>高中报告册</title>
<style>
.loading {
	line-height: 0px;
}

.li {
	margin-left: 10%;
}

li {
	float: left;
}

ul {
	list-style: none;
}

select {
	height: 27px;
	width: 65px
}

.dslcheckbox {
	width: auto;
	height: auto;
}
</style>
<script>
	$(document).ready(function() {
		//$("#timeCount").html("加载中...");
		/* 	alert("a");
		 //学段
		 var select = $("#mySelect");
		 //界别
		 var jb = $("#jb"); */

		var select = document.getElementById("mySelect");
		//var select = $("#mySelect");
		//界别
		//var jb = document.getElementById("jb");
		var jb = document.getElementById("jb");

		var xueduan = "${dqXue}";
		var jiebie = "${dqJie}";

		/* 	$("#mySelect").children("option").each(function(){  
		 var temp_value = $(this).val();  
		 if(temp_value == xueduan){  
		 $(this).attr("selected","selected");  
		 }  
		 }); 
		
		 $("#jb").children("option").each(function(){  
		 var temp_value = $(this).val();  
		 if(temp_value == jiebie){  
		 $(this).attr("selected","selected");  
		 }  
		 });  */

		for (var i = 0; i < select.options.length; i++) {
			if (select.options[i].innerHTML == xueduan) {
				select.options[i].selected = true;
				break;
			}
		}

		for (var i = 0; i < jb.options.length; i++) {
			if (jb.options[i].innerHTML == jiebie) {
				jb.options[i].selected = true;
				break;
			}
		}

	});

	function tishi() {
		alert("请选择毕业年级上报");
	}

	function xuejie() {
		//学段
		var select = document.getElementById("mySelect");
		//界别
		var jb = document.getElementById("jb");

		$.get("${ctx }/bookreport/BookreportAction.a?getJBList&xueduan="
				+ select.value, function(data) {
			/*  for (var i = 0; i <= jb.options.length; i++) {       
			  	   jb.options.remove(i);        
			 }  */
			$("#jb").empty();
			for (var i = 0; i <= data.vals.length; i++) {
				var option1 = new Option(data.vals[i].jiebie,
						data.vals[i].jiebieid);
				jb.options.add(option1);
			}

		});
	}

	//查询按钮
	function dianji() {
		//document.getElementById("MyDiv").style.visibility="visible";//显示
		/*document.getElementById("MyDiv").style.display=="block";//显示 */
		ShowDiv();
		document.getElementById("biaodan").submit();
	}

	//报告册上报
	function is(type) {

		//校验
		check();

		//1 报告册上报
		if (type == 1) {
			document.getElementById("shangbao").submit();
		}
	}

	function check(type) {
		var ids = document.getElementsByName("classs");
		var flag = false;
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			alert("请最少选择一项！");
			return false;
		} else {
			//上报 "MyDiv"
			if (type == 1) {
				$("#timeCount").html("加载中...");
				/*document.getElementById("MyDiv").style.display="";//显示  */
				ShowDiv();
				document.getElementById("shangbao").setAttribute("action",
						"${ctx }/bookreport/BookreportAction.a?BSB")
				document.getElementById("shangbao").submit();
			} else if (type == 2) {
				$("#timeCount").html("加载中...");
				/*document.getElementById("MyDiv").style.visibility="visible";//显示*/
				ShowDiv();
				//校验
				document.getElementById("shangbao").setAttribute("action",
						"${ctx }/bookreport/BookreportAction.a?ClassBJY");
				document.getElementById("shangbao").submit();
			} else if (type == 3) {
				//导出
				dStatus = uuid();
				$("#shangbao")
						.append(
								"<input id='hid' type='hidden' name='dStatus' value='"+dStatus+"'/>");
				document
						.getElementById("shangbao")
						.setAttribute("action",
								"${ctx }/bookreport/BookreportAction.a?exportZipByClass");
				document.getElementById("shangbao").submit();
				document.getElementById("shangbao").removeChild(
						document.getElementById("hid"));
				ShowDiv();
				timeId = setInterval(queryDownStatus, 1000);
			}

		}
	}

	var dStatus = "";
	var timeId = "";
	var count = 0;
	function queryDownStatus() {
		$("#MyDiv").css("height", "120%");
		$("#timeCount").html("已耗时间" + (++count) + "秒");
		$.ajax({
			url : "${ctx }/bookreport/BookreportAction.a?queryDownLoadStatus",
			type : "POST",
			data : {
				dStatus : dStatus
			},
			success : function(data) {
				if (data.val == "1") {
					$("#MyDiv").css("display", "none");
					clearInterval(timeId);
					count = 0;
					$("#timeCount").html("正在导出数据...");
				} else if (data.val == "2") {//导出文件失败
					$("#MyDiv").css("display", "none");
					clearInterval(timeId);
					count = 0;
					$("#timeCount").html("正在导出数据...");
					fail_notice_word("导出数据失败！");
				}
			}
		});
	}

	function uuid() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";
		var uuid = s.join("");
		return uuid;
	}
	function dslclick() {
		ShowDiv();
	}

	function printReport() {
		if(true){
			alert("功能正在完善中，暂缓使用！");
			return false;
		}
		
		var checkedList = $(":checkbox:checked", "#reportTable").toArray();
		var ids = [];
		if (checkedList == 0) {
			alert("请最少选择一项！");
			return false;
		} else {
			$("#printModal").show();
			for (var i = 0; i < checkedList.length; i++) {
				var checkbox = checkedList[i];
				ids.push(checkbox.value);
			}
			//     	var form = document.createElement("form");
			//     	document.body.appendChild(form);
			//     	var input = document.createElement("input");
			// 		input.type = 'hidden';
			//     	form.appendChild(input);
			// 		input.value = ids.join(",");
			// 		input.name = 'classids';
			//     	var input1 = document.createElement("input");
			// 		input1.type = 'hidden';
			//     	form.appendChild(input1);
			// 		input1.value = $("#jb").val();
			// 		input1.name = 'jb';
			//     	var input2 = document.createElement("input");
			// 		input2.type = 'hidden';
			//     	form.appendChild(input2);
			// 		input2.value = $("#mySelect").val();
			// 		input2.name = 'xueduan';
			// 		form.method = "post";
			// 		form.action = '${ctx}/bookreport/BookreportAction.a?printReport';
			// 		form.submit();
			$.ajax({
				url : '${ctx}/bookreport/BookreportAction.a?printReport',
				type : 'post',
				dataType : 'text',
				data : {
					'classids' : ids.join(","),
					'xueduan' : $("#mySelect").val(),
					"jb" : $("#jb").val()
				},
				success : function(result) {

					if (result) {
						var reportlets = '[' +result + ']';
						var config = {
							url : '${ctx}/ReportServer',
							isPopUp : false,
							data : {
								reportlets : reportlets
							}
						};
						FR.doURLFlashPrint(config);
					}
				}
			});
		}
	}
</script>

</head>
<body style="background: #EEE;overflow: auto;">

	<div style="width: 100%;">
		<!--  -->
		<div id="breadcrumb" style="margin-top: 10px; margin-bottom: 5px">
			<a href="#" class="tip-bottom"
				style="font-size: 12px; color: #333; font-weight: bold;"><i
				class="icon-home"></i>高中报告册</a>
		</div>
		<a href="http://211.153.82.211/czsc/gzbgc.pptx" style="text-decoration: underline;float: right;margin-top: 15px;margin-right: 15px;">高中报告册使用手册</a>
		<div
			style="width: 99%; height: 30px; border: 1px solid #CDCDCD; line-height: 50px; padding-top: 15px; margin-left: 6px">

			<!-- 条件查询班级列表 -->
			<form action="${ctx }/bookreport/BookreportAction.a?GetBjList"
				id="biaodan" method="post">

				<ul style="padding-top: 10px; margin-top: -22px">
					<li class="li">学段： <select name="learningPeriodId"
						id="mySelect">
							<c:forEach items="${xueduanlist}" var="xueduan">
								<option value="${xueduan.xueduanid }" onclick="xuejie();">${xueduan.xueduan }</option>
							</c:forEach>
					</select>
					</li>
					<li style="margin-left: 5%">届别： <select name="sectorsId"
						id="jb">
							<c:forEach items="${jiebielist}" var="jiebie">
								<option value="${jiebie.jiebieid }">${jiebie.jiebie }</option>
							</c:forEach>
					</select>
					</li>
					<li class="li"><input type="button" class="btn btn-success"
						style="width: 70px; margin-bottom: 20px; margin-top: 7px"
						id="query" onclick="dianji()" value="查 询" /></li>
				</ul>
			</form>
			<!-- 查询表单结束 -->
		</div>

		<!-- 当没有查询的时候不显示该div -->
		<c:if test="${!empty(classList) }">

			<div style="width: 96%; margin-top: 13px; margin-left: 2%">
				<div class="widget-box" style="margin-top: 0px; margin-bottom: 0px;">
					<div class="widget-title"
						style="background: #279F46; text-align: center; font-size: 12px; color: #FFF; text-shadow: 0px 0px 0px #FFF; line-height: 36px; margin: 0px;">
						班级列表</div>
					<div class="widget-content nopadding">
						<table id="reportTable"
							class="table table-bordered table-striped with-check">
							<thead>
								<tr>
									<th width="8%" style="font-size: 14px">选择</th>
									<th style="font-size: 14px">届别</th>
									<th style="font-size: 14px">学段</th>
									<th style="font-size: 14px">班级</th>
									<th width="8%" style="font-size: 13px">班级人数</th>
									<th width="8%" style="font-size: 13px">已上报人数</th>
									<th width="8%" style="font-size: 13px">未上报人数</th>
									<th width="8%" style="font-size: 13px">校验正确人数</th>
									<th width="8%" style="font-size: 13px">校验错误人数</th>
								</tr>
							</thead>
							<tbody>
								<form action="${ctx }/bookreport/BookreportAction.a?BSB"
									method="post" id="shangbao">
									<c:forEach items="${classList }" var="class">
										<tr>
											<td><input type="checkbox" name="classs"
												class="dslcheckbox" value="${class.classId }" /></td>
											<td style="text-align: center;">${class.xueduan }</td>
											<td style="text-align: center;">${class.jiebie }</td>
											<td style="text-align: center;">${class.className }</td>
											<td style="text-align: center;"><a onclick="dslclick()"
												href="${ctx}/jwReportBook/JwReportBookAction.a?chanClass&classid=${class.classId }&marker=0&qdxueduan=${dqJie}">${class.totalCount }</a></td>

											<c:if test="${class.reportedCount!=0 }">
												<td style="text-align: center;"><a onclick="dslclick()"
													href="${ctx}/jwReportBook/JwReportBookAction.a?chanClass&classid=${class.classId }&marker=1&qdxueduan=${dqJie}">${class.reportedCount }</a></td>
											</c:if>
											<c:if test="${class.reportedCount==0 }">
												<td style="text-align: center;">${class.reportedCount }</td>
											</c:if>


											<c:if test="${class.noreportCount!=0 }">
												<td style="text-align: center;"><a onclick="dslclick()"
													href="${ctx}/jwReportBook/JwReportBookAction.a?chanClass&classid=${class.classId }&marker=2&qdxueduan=${dqJie}">${class.noreportCount }</a></td>
											</c:if>
											<c:if test="${class.noreportCount==0 }">
												<td style="text-align: center;">${class.noreportCount }</td>
											</c:if>
											<c:if test="${class.verficationSuccessCount!=0 }">
												<td style="text-align: center;"><a onclick="dslclick()"
													href="${ctx}/jwReportBook/JwReportBookAction.a?chanClass&classid=${class.classId }&marker=3&qdxueduan=${dqJie}">${class.verficationSuccessCount }</a></td>
											</c:if>

											<c:if test="${class.verficationSuccessCount==0 }">
												<td style="text-align: center;">${class.verficationSuccessCount }</td>
											</c:if>

											<c:if test="${class.verficationFailedCount!=0 }">
												<td style="text-align: center;"><a onclick="dslclick()"
													href="${ctx}/bookreport/BookreportAction.a?getErrorstudentlist&classid=${class.classId }&dqJie=${dqJie}">${class.verficationFailedCount }</a></td>
											</c:if>
											<c:if test="${class.verficationFailedCount==0 }">
												<td style="text-align: center;">${class.verficationFailedCount }</td>
											</c:if>
										</tr>
									</c:forEach>
							</tbody>
						</table>
						<div class="form-actions pagination alternate"
							"  style="margin-top: 0px; margin-bottom: 0px;">

							<span
								style="margin-right: 30px; float: right; margin-top: line-height: 36;">
								<input type="hidden" value="${dqxueid }" name="dqxueid" /> <input
								type="checkbox" name="feijingji" class="dslcheckbox" value="1"
								checked="checked" /> 非京籍一起上报
								</form>
								<button onClick="check(2);" style="width: 100px;"
									class="btn btn-success">报告册校验</button> <c:if test="${isSB==1 }">
									<button onClick="check(1);" style="width: 100px;"
										class="btn btn-success">报告册上报</button>
								</c:if> <c:if test="${isSB==0 }">
									<button onClick="tishi();" style="width: 100px;"
										class="btn btn-success">报告册上报</button>
								</c:if>
								<button onClick="check(3);" style="width: 100px;"
									class="btn btn-success">报告册导出</button>
								<button onClick="printReport();" style="width: 100px;"
									class="btn btn-success">打印报告册</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<!-- 没有值  不显示该giv  -->
		${error }
	</div>
	<%@ include file="/common/exporting.jsp"%>
	<div style="position: fixed;top: 0;bottom: 0;left: 0;right: 0;z-index: 100;display: none;" id="printModal">
		<div style="background-color: #FBFBFB;opacity: .4;filter:alpha(opacity=40);-moz-opacity: .4;-khtml-opacity: .4;position: absolute;width: 100%;height: 100%;"></div>
		<div style="position: absolute;border: solid 1px #999;width: 250px;height: 38px;top: 50%;left: 50%;margin-top: -29px;margin-left: -125px;background-color: #ccc;box-shadow: 2px 1px 20px 1px #666;border-radius: 10px;font-size: 12px;padding: 10px;color: #fff;"><span>由于打印需要的时间较长，请耐心等待，待打印完毕之后，请手动点击：<a href="javascript:void(0);" onclick="$(this).parent().parent().parent().hide()">【关闭】</a></span></div>
	</div>
</body>
</html>