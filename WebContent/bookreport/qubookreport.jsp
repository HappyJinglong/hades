<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<un:bind var="KG_COURSE_KIND" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_KIND"/>
<un:bind var="KG_COURSE_NEIZHI" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_NEIZHI"/>
<un:bind var="USER_MASTERROLE_TYPESTR" type="com.flyrish.hades.common.Constant"
	field="USER_MASTERROLE_TYPESTR"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />

<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
 <link type="text/css" rel="stylesheet" href="${ctx}/css_new/jquery.gritter.css" />
  <script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
  <script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>


<title>高中报告册</title>
	<style >
	.loading{
		line-height:0px;
	}
	.li{
		margin-left: 10%;
	}
	li{
	 float: left;
	}
	ul{
		list-style:none;
	}
	select{
		height: 27px;
		width: 65px	
	}
	.dslcheckbox{
		width:auto;
		height:auto;
	}
</style>
<script>
$(document).ready(function(){
	 $("#timeCount").html("加载中...");
});
function dianji(){
	if($("#cmis30id").val()==''){
		alert("请选择您要查看的学校！");
		return;
	}
	ShowDiv(); 
	document.getElementById("biaodan").submit();
}

function fanhui(){
	 location.href = "${ctx}/masterReport/ReportAction.a?toDetailsReportPage";
}
//获取老师信息
function getTeacherInfo(discode){
	 $.ajax({
			type: "POST",
			contentType: "application/json",
			url: "${ctx }/masterReport/ReportAction.a?querySchoolInfos&&discode="+discode,
			data: "{}",
			dataType: "json",
			success: function (msg) {
				if(!msg){
					return;
				}
				$("#schoolNameOld").autocomplete(msg.val,{
					minChars: 0,
					width: 180,
					matchContains: true,
					max:2000,
					formatItem: function(row, i, max) {
						return  row.name;
					},
					formatMatch: function(row, i, max) {
						return  row.name;
					},
					formatResult: function(row) {
						return row.name;
					}
				}).result(function(event, row) {
					$("#cmis30id").val(row.id);
				});
			}
		});
}

function check(type){   
   var ids = document.getElementsByName("cmis30id");              
   var flag = false ;  
   var cmis30id='';
   for(var i=0;i<ids.length;i++){
       if(ids[i].checked){
           flag = true ;
           cmis30id=ids[i].value;
           break ;
       }
   }
   if(!flag){
       alert("请最少选择一项！");
       return false ;
   }else{
   	if(type==3){
   		//导出
    	$("#timeCount").html("正在导出数据...");
   		dStatus = uuid();
   		/* $("#shangbao").append("<input id='hid' type='hidden' name='dStatus' value='"+dStatus+"'/>");
   	 	document.getElementById("shangbao").setAttribute("action","${ctx}/bookreport/QuBookreportAction.a?exportZipByXuex");
   		document.getElementById("shangbao").submit();
   		document.getElementById("shangbao").removeChild(document.getElementById("hid"));  */ 
   		$.ajax({
   	     url: "${ctx }/bookreport/QuBookreportAction.a?exportZipByXuex",
   	     type: "POST",
   	     data: {
   	    	 dStatus:dStatus,
   	    	 cmis30id:cmis30id
   	     },
   	     success: function(data) {
   	    	
   	     }
   	 	});
   		ShowDiv(); 
   		timeId = setInterval(queryDownStatus,1000);
   	}
   	
   }
} 

var dStatus = "";
var timeId = "";
var count = 0;
function queryDownStatus(){
	 $("#MyDiv").css("height","120%");
	 $("#timeCount").html("已耗时间"+(++count)+"秒");
	 $.ajax({
	     url: "${ctx }/bookreport/BookreportAction.a?queryDownLoadStatus",
	     type: "POST",
	     data: {
	    	 dStatus:dStatus
	     },
	     success: function(data) {
	    	if(data.val=="1"){
	    		$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		 $("#timeCount").html("正在导出数据...");
	    		 $.get("${ctx }/bookreport/QuBookreportAction.a?returnURL&dStatus="+dStatus,
  		 			  function(data){
	    			 dStatus = "";
  		 			  location.href = data.vals[0]; 
  		 			});  
	    	}else if(data.val=="2"){//导出文件失败
	    		$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		dStatus = "";
	    		count=0;
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
   s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
   s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
   s[8] = s[13] = s[18] = s[23] = "-";
   var uuid = s.join("");
   return uuid;
}
function dslclick(){
	 $("#timeCount").html("加载中...");
	ShowDiv(); 	
}
</script>
	
</head>
<body style="background: #EEE">
	<div style="width: 100%;">
		<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 50px">
	<form action="${ctx }/bookreport/QuBookreportAction.a?getSchoolByDiscode"  id="biaodan"  method="post">
			  <ul styl e="margin-top: 5px">
			 	<li style="margin-left: 5%"></li>
			 <li style="margin-left: 5%"> 
			 	  学校名称：
 			 	<select name="cmis30id" style="width: 200px">
			 		 	<option value="">------全部------</option>
			 		 <c:forEach items="${schoolSelectList }" var="var">
			 			<option value="${var.cmis30id }"<c:if test="${cmis30id eq  var.cmis30id}"> selected='selected'</c:if>>${var.schoolname }</option>
			 		 </c:forEach>
			 		
			 	</select>  
				<!-- <input 
					id="schoolNameOld" 
					name="schoolNameOld" 
					value=""
					type="text" style="width: 180px"/> -->
				<!-- <input type="hidden" id="cmis30id" name="cmis30id" value=""/> -->
			 	</li>
			 	 <li class="li">
			 	 	<input type="hidden"  name="discode" value="${discode }" />
			  		<input type="button" class="btn btn-success" style="margin-top:7px;width: 70px;margin-bottom: 7px" id="query" onclick="dianji()" value="查 询" /> 
				 </li>
			 </ul>  
	 </form>
		</div>
		${error }
		<form  id="shangbao" method="post">
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
						  <div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >学校列表</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="4%" style="font-size: 14px">选择</th>
											<th width="6%"  style="font-size: 14px">序号</th>
											<th style="font-size: 14px">学校代码</th>
											<th style="font-size: 14px">学校名称</th>
											<th style="font-size: 14px">届别</th>
											<th width="8%" style="font-size: 13px">上报人数</th>
											<th  width="8%" style="font-size: 13px">校验正确人数</th>
											<th width="8%" style="font-size: 13px">校验错误人数</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${schoolList}" var="sch" varStatus="xh">
											<tr >
												<td><input type="radio" id="radi" name="cmis30id" value="${sch.cmis30id }"/></td>
												<td style="text-align: center;">${xh.count}</td>
												<td style="text-align: center;">${sch.schoolcode }</td>
												<td style="text-align: center;">${sch.schoolname }</td>
												<td style="text-align: center;">${sch.jiebie }</td>
												<td style="text-align: center;">${sch.reportedCount }</td>
												<td style="text-align: center;">${sch.verficationSuccessCount }</td>
												<c:if test="${sch.verficationFailedCount!=0 }">
													<td style="text-align: center;"><a onclick="dslclick()" href="${ctx}/bookreport/QuBookreportAction.a?getErrorstudentlist&cmis30id=${sch.cmis30id}">${sch.verficationFailedCount }</a></td>
												</c:if>
												<c:if test="${sch.verficationFailedCount==0 }">
													<td style="text-align: center;">${sch.verficationFailedCount }</td>
												</c:if> 
											</tr>
										</c:forEach>
										<tr>
											    <td colspan="5">合计</td>
												<td style="text-align: center;">${sbzq }</td>
												<td style="text-align: center;">${jyzq }</td>
												<td style="text-align: center;">${jysb }</td>
												</tr>
									</tbody>
								</table>	
								<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
									
									<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
								<input type="button"  onClick="check(3);" style="width: 100px;"  class="btn btn-success" value="报告册导出"/>
									<input type="button"  onClick="fanhui();" style="width: 100px;"  class="btn btn-success" value="返回"/>
									<!--  <button onClick="check(3);" style="width: 100px;" class="btn btn-success">报告册导出</button>
									  <button onClick="fanhui();" style="width: 100px;" class="btn btn-success">返回</button> -->
							      </span>
							  </div>						
						  </div>
						</div>
		</div>
		</form>
	</div>
	<%@ include file="/common/exporting.jsp"%>
</body>
</html>