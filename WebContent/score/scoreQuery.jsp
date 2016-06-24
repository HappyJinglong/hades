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
<title>成绩录入</title>
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
					html+="<option value='"+data[i].segment_id+"'>"+data[i].segment_name+"</option>"; 
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
			$.post("${ctx}/score/ScoreAction.a?studentQueryScore1",{"segmentId":segmentId,"schoolyear":year},function(data){
				var s = "";
				s+="<li class='li'>姓名："+data.studentName+"</li>";
				s+="<li class='li'>班级："+data.className+"</li>";
				s+="<li class='li'>教育ID："+data.eduId+"</li>";
				s+="<li class='li'>本学段总学分："+data.xfCount+"</li>";
				s+="<li class='li'>其中： 必修："+data.bXScore.length+"</li>";
				s+="<li class='li'>选修："+data.xXScore.length+"</li>";
				s+="<li class='li'>必选："+data.bX1Score.length+"</li>";
				s+="<li class='li'>校本课程："+data.xBScore.length+"</li>";
				$("#infor").html(s);				
				 data = data.mss;
				var str = "";
				for(var i=0;i<data.length;i++){
					str+="<tr><td width='4%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].subjectName ;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].courseName;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime1;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime2;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime3;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime4;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime5;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].daily_behave;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime16;
					str+="</td><td width='5%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].cql;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].qqxs;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].examine_result;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].peacetime18;
					str+="</td><td width='4%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].credit_hour;
					str+="</td><td width='4%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].credit_source;
					str+="</td><td width='7%' style='text-align: center;vertical-align:middle;'>";
					str+=data[i].is_pass;
					str+="</td><td width='6%' style='text-align: center;vertical-align:middle;'>";
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
	</script>
		<%@ include file="/common/queryLoadDiv.jsp"%>
</head>
<body style="background: #EEE"  onload="title1();"> 
<div style="width: 100%;margin-bottom: 30px">
	 <div style="width: 100%;position: fixed;top: 0px;z-index: 100;background: #EEE">
  	 <div id="breadcrumb"  >
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>成绩查询</a>
	 </div>  
			<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 53px">
				 <ul >
				 	<li style="margin-left: 80px">
				 	   学年：
				 		<select name="year" id="year" onchange="getSegment();">
	               				<c:forEach var="xueYear" items="${xueYears }">
	               					<option value="${fn:split(xueYear, '@')[0]}">${fn:split(xueYear, '@')[1]}</option>
	               				</c:forEach>
	               				<c:if test="${fn:length(xueYears) eq 0}"><option value="0">无</option></c:if>
	              		</select>
				 	</li>
				 	<li class="li">
				 	   学段：
				 		<select id="segmentId" name="segmentId"  >
							<c:forEach var="segment" items="${segments }">
								<option value="${segment.segment_id}">${segment.segment_name}</option>
							</c:forEach>
							<c:if test="${fn:length(segments) eq 0}"><option value="0">无</option></c:if>
				 		</select>
				 	</li>
				 
				 	 <li class="li">
				 	 	<span>&nbsp;
				 	 	</span>
				  		<input type="button"  onclick="query();" class="btn btn-success" id="query"  value="查 询"  style="width: 70px;margin-bottom: 7px" />
					 </li>
					 <li class="li"><span>&nbsp;
				 	 	</span>
							<input type="button" class="btn btn-success" style="width: 70px;margin-bottom: 7px" onclick="exportScore();" id="query"  value="导 出" />
					 </li>
				 </ul>
			</div>
			<div style="margin: 13px;margin-bottom: 0px">
				<div style="width: 100%;">
							<ul  id="infor">
								 <li class="li">姓名：${studentName }</li>	
								 <li class="li">班级：${className }</li>
								 <li class="li">教育ID：${eduId}</li>
								 <li class="li">本学段总学分：${xfCount }</li>	
								 <li class="li">其中： 必修： ${fn:length(bXScore) }</li>
								 <li class="li"> 选修：${fn:length(xXScore) }</li>
								 <li class="li"> 必选：${fn:length(bX1Score) }</li>
								 <li class="li">校本课程：${fn:length(xBScore) }</li>	
							</ul>
							<br />
				</div>
				<div style="width: 100%;margin-top: 13px;margin-bottom: 0px">
					<div style="background: #279F46;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								<span style="margin-left: 40%" id="title">2014-2015学年第1学段成绩表</span>
					</div>
					<div class="widget-content nopadding" style="margin-bottom: 0px">
								<table class="table table-bordered table-striped"  style="table-layout:fixed;">
									<thead>
										<tr>
											<th width="4%" style="font-size: 13px;"  >学科</th>
											<th width="5%" style="font-size: 13px;">模块</th>
											<th width="7%" style="font-size: 13px;">平时成绩1</th>
											<th width="7%" style="font-size: 13px;">平时成绩2</th>
											<th width="7%" style="font-size: 13px;">平时成绩3</th>
											<th width="7%" style="font-size: 13px">平时成绩4</th>
											<th width="7%" style="font-size: 13px;">平时成绩5</th>
											<th  width="6%" style="font-size: 13px;">平时评定</th>
											<th width="6%" style="font-size: 13px">日常表现</th>
											<th  width="5%" style="font-size: 13px;">出勤率</th>
											<th  width="6%" style="font-size: 13px;">缺勤学时</th>
											<th  width="6%" style="font-size: 13px;">考试成绩</th>
											<th  width="6%" style="font-size: 13px;">补考成绩</th>
											<th  width="4%" style="font-size: 13px;">学分</th>
											<th  width="4%" style="font-size: 13px;">免修</th>
											<th  width="7%" style="font-size: 13px;">学分不通过</th>
											<th  width="6%" style="font-size: 13px;">会考成绩</th>
										</tr>
									</thead>
								</table>
						</div>
				</div>
				
			</div>
		</div>
		
		<div style="width: 100%;margin-top: 200px">
						<div class="widget-box" style="margin-top:13px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped"  style="table-layout:fixed;" >
									<tbody id="content1" >
										<c:if test="${fn:length(mss) eq 0}"><tr><td colspan="2">无课程成绩</td></tr></c:if>
										<c:forEach var="m" items="${mss}">
											<tr >
												<td width="4%" style="font-size: 13px; text-align: center;vertical-align:middle;" valign="middle">${m.subjectName }</td>
												<td width="5%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.courseName }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime1 }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime2 }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime3 }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime4 }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime5 }</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.daily_behave }</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime16 }</td>
												<td width="5%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.cql}</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.qqxs }</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.examine_result}</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.peacetime18 }</td>
												<td width="4%"  style="font-size: 13px; text-align: center;vertical-align:middle;">${m.credit_hour }</td>
												<td width="4%" style="font-size: 13px; text-align: center;vertical-align:middle;" >${m.credit_source }</td>
												<td width="7%" style="font-size: 13px; text-align: center;vertical-align:middle;" >${m.is_pass }</td>
												<td width="6%" style="font-size: 13px; text-align: center;vertical-align:middle;">${m.level_name }</td>
											</tr>
										</c:forEach>
										<!-- 
												<tr >
											<td style="text-align: center;">数学</td>
											<td style="text-align: center;">数学1</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">100%</td>
											<td style="text-align: center;">2</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">99</td>
											<td style="text-align: center;">36</td>
											<td style="text-align: center;">否</td>
											<td style="text-align: center;">是</td>
											<td style="text-align: center;">99</td>
										</tr> -->
										
									</tbody>
								</table>							
							</div>
						</div>
			</div>
			
	</div>
</body>
</html>