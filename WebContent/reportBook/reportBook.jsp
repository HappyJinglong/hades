<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<style type="text/css">
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
		margin-top: 6px;
	}
	select{
		height: 28px;
		width: 77px	
	}
	.dslcheckbox{
		width:auto;
		height:auto;
	}

</style>

<script type="text/javascript">
$(window).load(function() {
	classnum();
	$("#MyDiv").css("height","120%");
	if("1"=="${falg}"){
		//1、初始化年级选项
		$("#selectGrade").val("${gradeId}");
		//2、初始化班级选项
		classnum();
		$("#selectClass").val("${classId}");
		$("#selectGrade").val("${gradeId}");
		//3、查询出数据
		findClassStuder();
	}
});

function delectoption(){
	$("select option").each(function() {
		text = $(this).text();
		if($("select option:contains("+text+")").length > 1)
		$("select option:contains("+text+"):gt(0)").remove();
	});
}


function classnum(){
	var  gendnum=$("#selectGrade").val();
	var ss="";
	<c:forEach items="${campus}" var="ca" >
		var geend="${ca.gradeNum}";
		if(gendnum==geend){
			ss+="<option value='"+${ca.classId}+"'>"+${ca.classNum}+" 班</option>"
		}
    </c:forEach>
    $("#selectClass").html("");
    $("#selectClass").html(ss);
    delectoption();
}
//全选
function onchenked(){
    var oBoxs=$("body").find("input");	
    for(var i=0;i<oBoxs.length;i++){
           oBoxs[i].checked=true;
        }
}
//全部选
function onchenkedNull(){
	 var oBoxs=$("body").find("input");	
	    for(var i=0;i<oBoxs.length;i++){
	           oBoxs[i].checked=false;
	        }
}
//修改年级
 function onchangeGrade(){
	 学段:
	 	var  gendnum=$("#selectLevel").val();
		var ss="";
		<c:forEach items="${campus}" var="ca" >
			var geend="${ca.levelId}";
			if(gendnum==geend){
				ss+="<option value='"+${ca.levelId}+"'>"+${ca.levelName}+"</option>"
			}
	    </c:forEach>
	    $("#selectLevel").html("");
	    $("#selectLevel").html(ss);
	 classnum();
} 

 
 function  findClassStuder(){
	 ShowDiv();
	 Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		Ext.Ajax.request({
			url:'${ctx}/reportBook/ReportBookAction.a?chanClass',
			method:'POST',
			success:function(response,options){
				 var temp=response.responseText;
				    var list=eval(temp);
				    var tr="";				    
					var i=2;
					var leng=list.length;
					var mo=leng%3;
					var z=0;					
					 for(var q=0;q<list.length;++q){
					   var studentno = list[q].studentno;
						var text=list[q].text;
						var edu_id =list[q].edu_id;							
						   z++;
						  i+=1;
						  var s=i%3;
						  if(0==s){
							  tr+="<tr>"; 
						  }
						  if(2!=s){
							  tr+="<td style='text-align: center;width:5%;'><input class='dslcheckbox' value='"+edu_id+"' type='checkbox' name='checkbox' /></td>"
						        +"<td style='text-align: center;width:10%;'>"+text+"</td>"
						        +"<td style='text-align: center;width:10%;'>"+studentno+"</td>"
						        +"<td style='text-align: center;width:5%;'></td>";
						  }else{
							   tr+="<td style='text-align: center;width:5%;'><input class='dslcheckbox' value='"+edu_id+"' type='checkbox' name='checkbox' /></td>"
						        +"<td style='text-align: center;width:10%;'>"+text+"</td>"
						        +"<td style='text-align: center;width:10%;'>"+studentno+"</td>";
						  }						  
						 if(z!=leng){
							   if(2==s){
								  tr+="<tr>"; 
							    }
						 }else{
							 if(0==mo){
								  tr+="<tr>"; 
							    }else if(1==mo){
							    	tr+="<td style='text-align: center; '></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<td style='text-align: center; '></td>"
								        +"<td style='text-align: center; '></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<tr>";
							    }else{
							    	tr+="<td style='text-align: center; '></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<td style='text-align: center;'></td>"
								        +"<tr>";
							    }							 
						 }
					 }
					
				 	tr+="<tr>"
						+"<td colspan='11' style='padding:0px;'>"
								+"<div class='form-actions pagination alternate''  style='margin-top: 0px;margin-bottom: 0px;'>"									
								+"<span style='margin-right: 30px;float:right;margin-top:  line-height: 36;'>"
								+"<button onClick='onchenked()' style='width: 100px;'  class='btn btn-success'>全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
								+"<button onClick='onchenkedNull()' style='width: 100px;' class='btn btn-success'>取消全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
								+"<button onClick='exportReport(1);' style='width: 130px;' class='btn btn-success'>报告册模板下载</button>&nbsp;&nbsp;&nbsp;&nbsp;"
								+"<button onClick='toImportPage();' style='width: 110px;' class='btn btn-success'>报告册导入</button>&nbsp;&nbsp;&nbsp;&nbsp"
								+"<button onClick='exportReport(0);' style='width: 110px;' class='btn btn-success'>报告册导出</button>"
								/*+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册校验</button>校验结果下载"*/
								+"</span>"
							+"</div>"			
						+"</td>"
					+"</tr>"; 					
					var newpaerdiv=$("#minTable");										
				    newpaerdiv.html(""); 
				    newpaerdiv.html(tr);	
				    $("#class").val($("#selectClass").val());
				    $("#grade").val($("#selectGrade").val());
			  },
				params : {
					classid:$("#selectClass").val()
				}
			});
		 $("#MyDiv").hide();
  }
 
 function toImportPage(){
	 //获取classIds
	 var classIds = "";
	<c:forEach items="${campus}" var="ca" >
		classIds+="${ca.classId},";
    </c:forEach>
	 window.location.href="${ctx}/masterReport/ReportImportAction.a?classId="+$("#class").val()+"&&gradeId="+$("#grade").val()+"&&classIds="+classIds;
 }
 
 
 function exportReport(num){
	 var classId = $("#class").val();
	 var eduIds = "";
	 $("[type='checkbox']").each(function (){
		 if($(this).attr("checked")){
			 eduIds+=$(this).val()+",";
		 }
	 });
	 if(!$.trim(eduIds)){
		 alert("请选择需要导出数据！");
		 return;
	 }
	 dStatus = uuid();
	 window.location.href="${ctx}/masterReport/ReportExportAction.a?eduIds="+eduIds+"&&classId="+classId+"&&flag="+num+"&&dStatus="+dStatus;
	 ShowDiv(); 
	 timeId = setInterval(queryDownStatus,1000);
 }
 var dStatus = "";
 var timeId = "";
 var count = 0;
 function queryDownStatus(){
	 $("#MyDiv").css("height","120%");
	 $("#timeCount").html("已耗时间"+(++count)+"秒");
 	 $.ajax({
	     url: "${ctx}/masterReport/ReportExportAction.a?queryDownLoadStatus",
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
	    	}else if(data.val=="2"){//导出文件失败
	    		$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
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
</script>
</head>
<body style="background: #EEE">	
	<input type="hidden" value="" id="class">
	<input type="hidden" value="" id="grade">
	<input type="hidden" value="${campus}" id="campusHidden">
	<div style="width: 100%;">
		<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;">
		<div id="breadcrumb" style="margin-top: 10px; margin-bottom: 5px">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>高中报告册</a>
			</div>
			 <ul style="padding-top: 10px">
			 	<li style="margin-left: 5%">
			 	   学段：
			 		<select onchange="onchangeGrade()" id="selectLevel">
			 		 <c:forEach items="${campus}" var="ca" >
			 			<option value="${ca.levelId}">${ca.levelName}</option>
			 		 </c:forEach>
			 		</select>
			 	</li>
			 	<li class="li"> 
			 	    年级：
				 	  <select onchange="classnum()"  id="selectGrade">
				 		 <c:forEach items="${campus}" var="ca" >
				 			 <c:if test="${campus[0].levelId eq ca.levelId}"> 
				 				<option value="${ca.gradeNum}">${ca.gradeName}</option>
				 			</c:if>
				 		 </c:forEach>
				 		</select>     
			 	</li>
			 	<li class="li">
			 	     班级：
				 	  <select id="selectClass">
				 			
				 		</select>     
			 	</li>
			 
			 	 <li class="li">
			  		<input type="button" class="btn btn-success" style="width: 70px;margin-bottom: 7px" id="query" onclick="findClassStuder()"  value="查 询" /> 
				 </li>
			 </ul>
		</div>	
		<div style="width: 96%;margin-top: 13px;margin-left:2%">
			<div class="widget-box" style="margin-top:0px;margin-bottom:0px;">
				<div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
					学生列表
				</div>
				<div class="widget-content nopadding" style="position: relative;">
					<table class="table table-bordered table-striped with-check">
						<thead>
							<tr>
								<th width="5%"  style="font-size: 14px">选择</i>										  
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								<th width="5%" style="font-size: 13px">&nbsp;</th>
								<th width="5%"  style="font-size: 14px">选择</i></th>
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								<th width="5%" style="font-size: 13px">&nbsp;</th>
								<th width="5%"  style="font-size: 14px">选择</i></th>
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								
							</tr>
						</thead>
						<tbody id="minTable">																	  							
						</tbody>
					</table>											
			</div>					
		</div>
	</div>
	<%@ include file="/common/exporting.jsp"%>
</body>

</html>






									
									




