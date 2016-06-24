<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SegmentSelectDwr.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SeriesSelectDwr.js'></script>
<title>模块开设学段及班级任课教师导入</title>
 <style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
 </style>
 <script type="text/javascript">
 var $$=jQuery.noConflict();
 var isSave = 0;
	window.onload = function (){
		changGradeToYear();
		var year = $$("#yearid").val();
		changYearToSegment(year);
		if('${noticeflag}'=="1"){
			alert_KG_importData_failure();
		}
	}
	//修改年级改变学年
	function changGradeToYear(){ //v3.0
			dwr.engine.setAsync(false);
			SeriesSelectDwr.changeGradeToYear("3",function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("year");
					DWRUtil.addOptions("year",obj ,"schoolyear" ,"schoolyearname" );
				}else{
					DWRUtil.removeAllOptions("year");
				}
			});
			dwr.engine.setAsync(true);
	}
//修改学年改变学段
	function changYearToSegment(year){ //v3.0
		var cmis30id = $$("#cmis30id").val();
			if(year =='' || null==year){
				year=$$("#year").val();
			}
			dwr.engine.setAsync(false);
			if(null!=year&&''!=year)
			{
				SeriesSelectDwr.changeYearToSegment(year,cmis30id,function(obj){
					if(obj!=null){
						DWRUtil.removeAllOptions("segment");
						DWRUtil.addOptions("segment",obj ,"segmentid" ,"segmentName" );
					}else{
						DWRUtil.removeAllOptions("segment");
					}
				});
			}else
			{
				DWRUtil.removeAllOptions("segment");
			}
			dwr.engine.setAsync(true);
			$$("#yearid").val(year);
	}
	function dl(){
			var segment = $$("#segment").val();
			var year = $$("#year").val();
			window.location.href="${ctx }/innercourse/InnerCourse.a?download&segment="+segment+"&yearid="+year;
	}
	function goBack(){
		 $$("#fm1").submit();
	 }
	function upload(){
		$$("#timeCount").html("文件导入中...");
		ShowDiv();
		if(isSave==0){
			isSave=1;
			if($$("#importFile").val()!=""){
				 var re = /\.(xls|xlsx)$/;
				 var result= $$("#importFile").val().match(re);
				if(result){					
					$$("#parsing").css("display","");
					$$("#fm2").submit();
				}else{
					$$("#MyDiv").css("display","none");
					isSave=0;
					alert_KG_chooseFile_tip("请选择excel文件!");				
				}
			}else{
				$$("#MyDiv").css("display","none");
				isSave=0;
				alert_KG_chooseFile_tip("请选择上传文件！");
			}
		}else{
			isSave=0;
			alert_KG_self_tip("数据导入中...");
		}
	}
 </script>
</head>
<body style="background: #EEE">
	<div style="width: 100%;">
	<input type="hidden" id="cmis30id" name="cmis30id" value="${cmis30id}"/>
	<input type="hidden" id="yearid" name="yearid" value="${yearid}"/>
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">模块开设学段及班级任课教师导入</a>
		</div>
		<input type="hidden" id="selectGradenum" name="selectGradenum" value="${selectGradenum}"/>
		<form action="${ctx }/innercourse/ExcelAction.a" method="post" id="fm2" enctype="multipart/form-data">
			<input id="yearid" value="${yearid}" name="yearid" type="hidden"/>
			<input id="gradenum" value="${gradenum}" name="gradenum" type="hidden"/>
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right:13px">
							<div  style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								导入模块授课教师
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr >
											<td width="40%"  style="text-align: right;">学年：</td>
											<td width="60%"   style="text-align: left;">
												  <select id="year" name="year" style="width: 140px;" onchange="changYearToSegment(this.value);"></select>     
											</td>
											
										</tr>
										<tr>
											<td style="text-align: right;">学段：</td>
											<td style="text-align: left;">
												<select id="segment" name="segment"></select>     
												<span><a href="#" onclick="dl()">下载模块及班级任课教师导入模板</a></span>
											</td>
										</tr>
										<tr>
											<td style="text-align: right;">请选择导入文件：</td>
											<td style="text-align: left;"><input type="file" id="importFile" name="importFile" value="浏览"/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<!-- <span id="parsing" style="display:none;"><img src="${ctx}/images/progress.gif" style="height:20px"/>&nbsp;&nbsp;数据导入中，请耐心等待...</span> -->
											</td>
										</tr>
									</tbody>
								</table>							
							</div>
						</div>
						<div class="pagination alternate"  style="border: 1px solid #CDCDCD;margin: 0px 13px;height: 70px;line-height: 40px" >
							<span style="margin-right: 30px;float:right;margin-top: 15px; ">
								<input  type="button" class="btn btn-success" style="width: 70px;" id="query" onclick="upload()" value="确 定" />
								<input type="button" class="btn btn-success" style="width: 70px;" id="query" onclick="goBack()" value="返 回" />
						     </span>
						</div>
		</div>
		</form>
				<div class="widget-box" 
						style="margin-bottom:25px;margin-left:13px;margin-right:13px;
						<c:if test="${empty errorMessagesShow and empty errorMessagesOther and empty errorexl }">
							display:none;
						</c:if>
						">
							<div style="background: red;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >导入错误内容</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody>
										<tr>
											<td width="10%"  style="text-align: center;">序号</td>
											<td width="90%"   style="text-align: center;">
											错误信息
											</td>
										</tr>
										<c:forEach items="${errorMessagesShow }" var="msgs" varStatus="vs">
											<tr>
												<td style="text-align: center;">	${vs.count }		</td>
												<td style="text-align: left;">		第${fn:split(msgs,'#!#!')[0]}行：${fn:split(msgs,'#!#!')[1]}	</td>
											</tr>
										</c:forEach>
										 <c:forEach items="${errorMessagesOther }" var="msgs" varStatus="vs">
											<tr>
												<td style="text-align: center;">	${vs.count }		</td>
												<td style="text-align: left;">		第${fn:split(msgs,'#!#!')[0]}行：${fn:split(msgs,'#!#!')[1]}, ${fn:split(msgs,'#!#!')[2]}	</td>
											</tr>
										</c:forEach>
										<c:if test="${not empty errorexl}">
											<tr>
												<td style="text-align: center;">	1		</td>
												<td style="text-align: left;">${errorexl}</td>
											</tr>
										</c:if> 
									</tbody>
								</table>							
							</div>
						</div>
	</div>
	
	<form action="${ctx}/innercourse/InnerCourse.a?searchGoAward"  id="fm1" method="post">
			<input id="yearid" value="${yearid}" name="yearid" type="hidden"/>
			<input id="gradenum" value="${gradenum}" name="gradenum" type="hidden"/>
			<input id="segment" value="${segment}" name="segment" type="hidden"/>
			<input id="levelcode" value="${levelcode}" name="levelcode" type="hidden"/>
		</form>
		<%@ include file="/common/div.jsp"%>
</body>
</html>