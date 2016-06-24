<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script  src="${ctx}/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>成绩处理</title>
	<style>
		.li{
			margin-left: 5%;
		}
		li{
		 float: left;
		}
		ul{
			list-style:none;
		}
		select{
			height: 30px;
			width: 110px	
		}
		.bt{
			background: none repeat scroll 0% 0% #279F46;
			border-bottom: 3px solid #1C7132;
			color: #FFF;
		}
		td {
			word-wrap:break-word;
			text-align:center;
		}
	</style>
	<script >
	//获取学段
		function getSegment(){
			var schoolyear = $("#year").val();
			$.post("${ctx}/score/ScoreAction.a?querySegmentId&&schoolyear="+schoolyear,null,function(data){
				var html = ""; 
				for(var i=0;i<data.length;i++){
					if(data[i].segment_id=="${segmentId}"){
						html+="<option selected='selected' value='"+data[i].segment_id+"'>"+data[i].segment_name+"</option>";
					}else{
						html+="<option value='"+data[i].segment_id+"'>"+data[i].segment_name+"</option>";
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				$("#segmentId").html(html);
			})
		}
		function query(){
			 $("#MyDiv").css("margin-top","44px");
			 $("#MyDiv").css("height","100%");
			ShowDiv();
			var segmentId =$("#segmentId").val();
			var year =$("#year").val();
			var gradeName=$("#gradeName").val();
			var className=$("#className").val();
			var courseType=$("#courseType").val();
			
			var edu_id=$("#edu_id").val();
			var studentName=$("#studentName").val();
			var subjectName=$("#subjectName").val();
			
			$.post("${ctx}/delScore/DoWithScore.a?studentQueryScore1",{"segmentId":segmentId,"schoolyear":year,"gradeName":gradeName,"className":className,"edu_id":edu_id,"studentName":studentName,"courseType":courseType,"subjectName":subjectName},function(data){
				data = data.mss;
				var str = "";
				for(var i=0;i<data.length;i++){
					str+="<tr><td width='2%' style='font-size: 13px; text-align: center;vertical-align:middle;' valign='middle'><input type='checkbox' name='idStr' value='"+data[i].credit_id+"'/></td>";
					str+="<th width='3%' style='font-size: 13px;'  >"+data[i].source+"</th>";
					str+="<td width='4%' style='text-align: center;vertical-align:middle;'>" + data[i].subjectName + "</td>";
					str+="<td width='4.5%' style='text-align: center;vertical-align:middle;'>"+ data[i].courseName +"</td>";
					str+="<td width='5%' style='font-size: 13px; text-align: center;vertical-align:middle;' valign='middle'>"+data[i].studentName+"</td>";
					str+="<td width='6%' style='font-size: 13px; text-align: center;vertical-align:middle;' valign='middle'>"+data[i].edu_id+"</td>";
					str+="<td width='5%' style='text-align: center;vertical-align:middle;'>" + data[i].peacetime1;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime2;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime3;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime4;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime5;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].daily_behave;
					str+="</td><td width='5.5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime16;
					str+="</td><td width='4.5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].cql;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].qqxs;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].examine_result;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime18;
					str+="</td><td width='3.5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].credit_hour;
					str+="</td><td width='3.5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].credit_source;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].is_pass;
					str+="</td><td width='4.5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].level_name;
					str+="</td></tr>";
				}
				
				if(""==str){
					str+="<tr><td colspan='2'>无课程成绩</td></tr>";
				}
				$("#content1").html(str);
				
				title1();
				 $("#MyDiv").hide();
				
			})
		}
		
		function title1(){
			
			var schoolyearValue = $("#year option:selected").html();
			var segmentValue = $("#segmentId option:selected").html();
			
			$("#title").html(schoolyearValue+"学年"+segmentValue+"成绩表");
		}
		
		function exportScore(){
				 
				url = "${ctx}/score/ScoreAction.a?studentExport";
				
				var schoolyear = $("#year").val();
				var segment = $("#segmentId").val();
				var schoolyearValue = $("#year option:selected").html();
				var segmentValue = $("#segmentId option:selected").html();
				url +="&&schoolyear="+schoolyear+"&&segmentId="+segment+"&&schoolyearValue="+schoolyearValue+"&&segmentValue="+encodeURI(encodeURI(segmentValue));
				
				window.location.href=url;
				
			
		}
		//获取班级
		function changeGrades(){
			$.post("${ctx}/score/ScoreAction.a?queryClass",null,function(data){
				var html = ""; 
				var gradeId = $("#gradeName").val().split("_")[0];
				if(!(null == gradeId || "" == gradeId )){
					var cs = data;
					for(var i=0;i<data.length ;i++){
						if(gradeId == data[i].gradeId){
							if(data[i].classId=="${className1}"){
								html +="<option selected='selected' value='"+data[i].classId+"'>"+data[i].className+"</option>";
							}else{
								html +="<option value='"+data[i].classId+"'>"+data[i].className+"</option>";
							}
						}
					}
				} 
				$("#className").html(html);
			})
		}
		//获取学年
		function getYear(){
			var manyYears= $("#gradeName").val().split("_")[1];
			$.post("${ctx}/score/ScoreAction.a?queryXueYear&&manyYears="+manyYears,null,function(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					if(data[i].split("@")[0]=="${schoolyear}"){
						html+="<option selected='selected' value='"+data[i].split("@")[0]+"'>"+data[i].split("@")[1]+"</option>";
					}else{
						html+="<option value='"+data[i].split("@")[0]+"'>"+data[i].split("@")[1]+"</option>";
					}
				}
				if(data.length == 0){
					html+="<option value='0'>无</option>"; 
				}
				$("#year").html(html);
				getSegment();
			})
			
		}
		function checkAll(currentObj){
			$("[name='idStr']").each(function(){
				if(currentObj.attr("checked")){
					$(this).attr("checked",true);
				}else{
					$(this).attr("checked",false);
				}
			});
		}
		var isDelete=0;
		function deleteStudentInfo(){
			var str = "";
			var inputs = $("[name='idStr']");
			for(var i=0;i<inputs.size();i++){
				if($(inputs[i]).attr("checked")){
					str+=$(inputs[i]).val()+",";
				}
			}
		 	if(isDelete==1){
				alert_KG_self_tip("正在删除中...");
			}else{ 
				if(str==""){
					alert_KG_self_tip("请选择需要删除的信息！");
				}else{
					if(!confirm("删除后成绩无法恢复，您确定要删除？")){
						return;
					}
					isDelete=1;
					$.post("${ctx}/delScore/DoWithScore.a?deleteScoreById",{credit_id:str},function(data){
						var temp=data.flag;
						isDelete=0;
						if(temp=='1'){
							query();
							alert_delete_success('');
						}else{
							alert_KG_self_tip("删除失败！");	
						}
					}); 
				}
			 } 
		}
	</script>
		<%@ include file="/common/queryLoadDiv.jsp"%>
</head>
<body style="background: #EEE"  onload="title1();"> 
<div style="width: 100%;margin-bottom: 35px">
	 <div style="width: 100%;position: fixed;top: 0px;z-index: 100;background: #EEE">
  	 <div id="breadcrumb"  >
		<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>处理成绩</a>
	 </div>  
			<div style="width: 100%;border: 1px solid #CDCDCD;">
				 <ul style="overflow: hidden;margin: auto;margin-top: 10px;padding: 0 25px;">
				 	<li style="margin-left: 0%;">
				 	   年级：
				 		<select onchange="changeGrades();getYear();"  id="gradeName">
			 			<c:forEach items="${grades}" var="grade" varStatus="i">
			 				<c:if test="${i.index eq 0}">
			 					<c:set value="${grade.key }"  var="FirstgradeId"/>
			 				</c:if>
			 				<option value="${grade.key }">${grade.value }</option>
			 			</c:forEach>
			 			<c:if test="${fn:length(grades) eq 0}"><option value="0">无</option></c:if>
			 		</select>
				 	</li>
				 	<li style="margin-left: 1%;">
				 	   班级：
				 		<select id="className" style="width: 60px">
			 			<c:forEach items="${campus}" var="campu">
			 				  <c:if test="${fn:split(FirstgradeId, '_')[0] eq campu.gradeId}">
			 					<option value="${campu.classId }">${campu.className }</option>
			 				  </c:if>
			 			</c:forEach> 
			 			 <c:if test="${fn:length(campus) eq 0}"><option value="0">无</option></c:if>
			 			</select>
				 	</li>
				 	<li style="margin-left: 1.5%;">
				 	   学年：
				 		<select name="year" id="year" onchange="getSegment();" style="width: 100px">
               				<c:forEach var="xueYear" items="${xueYears }">
               					<option value="${fn:split(xueYear, '@')[0]}">${fn:split(xueYear, '@')[1]}</option>
               				</c:forEach>
               				<c:if test="${fn:length(xueYears) eq 0}"><option value="0">无</option></c:if>
              			</select>
				 	</li>
				 	<li style="margin-left: 2%;">
				 	   学段：
				 		<select id="segmentId" name="segmentId" style="width: 90px">
						<c:forEach var="segment" items="${segments }">
							<option value="${segment.segment_id}">${segment.segment_name}</option>
						</c:forEach>
						<c:if test="${fn:length(segments) eq 0}"><option value="0">无</option></c:if>
			 		</select>
				 	</li>
				 	<li style="margin-left: 2.5%;">
				 	   学科：
				 	   <input type="text" name='subjectName' id='subjectName' style="width: 80px"/>
				 	</li>
				 	<li style="margin-left: 2.5%;">
				 	   课程类型：
				 	   <select id='courseType' name='courseType' style="width: 90px">
				 	   	<option value="0">全部</option>
				 	   	<option value="1">内置课程</option>
				 	   	<option value="2">校本课程</option>
				 	   </select>
				 	</li>
				 	<li style="margin-left: 2.5%;">
				 	   教育ID：
				 		<input type="text" name='edu_id' id='edu_id' style="width: 80px"/>
				 	</li>
				 	<li style="margin-left: 2.5%;">
				 	   姓名：
				 		<input type="text" name='studentName' id='studentName' style="width: 80px"/>
				 	</li>
				 	 <li style="margin-left: 3%;float: right;margin-right: 10px;">
				 	 	<span>&nbsp;
				 	 	</span>
				  		<input type="button"  onclick="query();" class="btn btn-success" id="query"  value="查 询"  style="width: 70px;margin-bottom: 7px" />
					 </li>
				 </ul>
			</div>
			<div style="margin: 13px;margin-bottom: 0px">
				<div style="width: 100%;margin-top:0%;margin-bottom: 0px">
					<div style="background: #279F46;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								<span style="margin-left: 40%" id="title">2014-2015学年第1学段成绩表</span>
					</div>
					<div class="widget-content nopadding" style="margin-bottom: 0px">
								<table class="table table-bordered table-striped"  style="table-layout:fixed;">
									<thead>
										<tr>
											<th width="2%" style="font-size: 13px;"  >
											<input type="checkbox" onclick="checkAll($(this))"/>
											</th>
											<th width="3%" style="font-size: 13px;"  >来源</th>
											<th width="4%" style="font-size: 13px;"  >学科</th>
											<th width="4.5%" style="font-size: 13px;">模块</th>
											<th width="5%" style="font-size: 13px;"  >姓名</th>
											<th width="6%" style="font-size: 13px;"  >教育ID</th>
											<th width="5%" style="font-size: 13px;">平时成绩1</th>
											<th width="5%" style="font-size: 13px;">平时成绩2</th>
											<th width="5%" style="font-size: 13px;">平时成绩3</th>
											<th width="5%" style="font-size: 13px">平时成绩4</th>
											<th width="5%" style="font-size: 13px;">平时成绩5</th>
											<th  width="5%" style="font-size: 13px;">平时评定</th>
											<th width="5.5%" style="font-size: 13px">日常表现</th>
											<th  width="4.5%" style="font-size: 13px;">出勤率</th>
											<th  width="5%" style="font-size: 13px;">缺勤学时</th>
											<th  width="5%" style="font-size: 13px;">考试成绩</th>
											<th  width="5%" style="font-size: 13px;">补考成绩</th>
											<th  width="3.5%" style="font-size: 13px;">学分</th>
											<th  width="3.5%" style="font-size: 13px;">免修</th>
											<th  width="7%" style="font-size: 13px;">学分不通过</th>
											<th  width="4.5%" style="font-size: 13px;">会考成绩</th>
										</tr>
									</thead>
								</table>
						</div>
				</div>
				
			</div>
			</div>
			<div style="width: 100%;margin-top:221px;">
						<div class="widget-box" style="margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped"  style="table-layout:fixed;" >
									<tbody id="content1" >
									</tbody>
								</table>
								<!-- <span style="margin-top:15px;margin-right: 40px;float: right;">
									<input type="button" value="删除" class="btn btn-success" style="width: 70px;margin-bottom: 7px" onclick="deleteStudentInfo()"></input>
								</span>	 -->
								<div>
									<span style="margin-top:5px;margin-right: 40px;float: right;">
										<input type="button" value="删除" class="btn btn-success" style="width: 70px;margin-bottom: 7px" onclick="deleteStudentInfo()"></input>
									</span>	
								</div>						
							</div>
						</div>
		</div>
	</div>
</body>
</html>