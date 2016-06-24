<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>校本课程设置</title>
<style >
	.li{
		margin-left: 3%;
	}
	li{
	 float: left;
	}
	ul{
		list-style:none;
	}
	select{
		height: 25px;
		width: 60px	
	}
	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	
</style>
<script>
function backPage(){
	$("[name='pageNo']").val("1");
	$("form").attr("action","${ctx }/schoolCourse/SchoolCourseAction.a");
	$("form").submit();
}
var isDelete=0;
function deleteStudentInfo(){
	var str = "";
	var inputs = $("[name='checkbox']");
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
			if(!confirm("您确定要删除该条记录？")){
				return;
			}
				isDelete=1;
				Ext.Ajax.request({
				url:'${ctx }/schoolCourse/CouresHireStudentAction.a?toDeleteStudentInfo',
				method:'POST',
				success:function(response,options){
					var temp=response.responseText;
					isDelete=0;
					if(temp=="false"){
						alert_delete_failure("");
						return;
					}
					if(temp=="exist"){
						alert_KG_self_tip("选择记录学生成绩已经录入，不能删除！")
						return;
					}
					var str = '<input id="successFlag" name="successFlag" type="hidden" value="delete"/>'; 
					$("form").append(str);
					$("form").submit();
				},
				params : {
					matriculateId:str
				}
			}); 
		}
	 } 
}
function tip(){
	if('${successFlag}'=="delete"){
		alert_delete_success("");
	}
}
$(document).ready(function(){
	tip();
	<c:if test="${not empty pageObj}">
		<c:if test="${not empty pageObj.pageElements}">
			$("#button").css("margin-top","-35px");
		</c:if>
	</c:if>
});
function checkAll(currentObj){
	$("[name='checkbox']").each(function(){
		if(currentObj.attr("checked")){
			$(this).attr("checked",true);
		}else{
			$(this).attr("checked",false);
		}
	});
}
</script>
</head>
<body style="background: #EEE">
	<div style="width: 100%;margin-bottom: 30px">
		<div style="width: 100%;position: fixed;top: 0px;z-index: 100;background: #EEE">
			<div id="breadcrumb" >
					<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 录取学生</a>
			</div>
			<div style="margin-top: 13px;margin-left: 13px;margin-right: 13px">
				<div  style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								${courseInfo}录取学生列表 
				</div>
				<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="3%" style="font-size: 14px">
												<input type="checkbox" name="checkall" onclick="checkAll($(this));"/>
											</th>
											<th width="5%" style="font-size: 14px">序号</th>
											<th width="10%" style="font-size: 13px">姓名</th>
											<th width="5%" style="font-size: 13px">性别</th>
											<th width="15%" style="font-size: 13px">教育ID</th>
											<th width="15%" style="font-size: 13px">学科</th>
											<th width="47%"  style="font-size: 13px">所在行政班级</th>
										</tr>
									</thead>
								</table>
				</div>
			</div>
		</div>
		<form action="${ctx }/schoolCourse/CouresHireStudentAction.a" method="post">
			<input type="hidden" name="segCourseId" value="${segCourseId}"/>
			<input type="hidden" name="isLockButton" value="${isLockButton}"/>
			<!-- 查询条件 -->			
			<input id="year" value="${year }" name="year" type="hidden"/>
			<input id="segmentId" value="${segmentId }" name="segmentId" type="hidden"/>
			<input id="subjectId" value="${subjectId }" name="subjectId" type="hidden"/>
			<input id="isHired" value="${isHired }" name="isHired" type="hidden"/>
			<input id="courseName" value="${courseName }" name="courseName" type="hidden"/>
			<input id="guidTeacherName" value="${guidTeacherName }" name="guidTeacherName" type="hidden"/>
		<div style="width: 100%;margin-top: 119px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
										<tbody>
										<c:if test="${not empty pageObj.pageElements}">
     										<c:forEach items="${pageObj.pageElements}" var="roll" varStatus="status" >
											<tr>
												<td width="3%" style="text-align: center;"><input type="checkbox" name="checkbox" value="${roll.matriculate_id}"/></td>
												<td width="5%" style="text-align: center;">${status.count + pageObj.pageSize * (pageObj.currPageNumber-1)}</td>
												<td width="10%" style="text-align: center;">${roll.studentName}</td>
												<td width="5%" style="text-align: center;">${roll.sex}</td>
												<td  width="15%" style="text-align: center;">${roll.eduId}</td>
												<td  width="15%" style="text-align: center;">${roll.subjectName}</td>
												<td  width="47%" style="text-align: center;">${roll.className}</td>
											</tr>
										 	</c:forEach>
     									</c:if>		
										</tbody>
								</table>	
								<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
									<div id="pagearea">
										<span><input name="pageNo" value="${pageNo }" type="hidden" />
										<c:if test="${not empty pageObj}">
												<c:if test="${not empty pageObj.pageElements}">
													<jsp:include page="../common/pager-nest.jsp">
														<jsp:param name="toPage" value="1" />
														<jsp:param name="showCount" value="1" />
														<jsp:param name="action" value="toStudentDetails" />
													</jsp:include>
												</c:if>
											</c:if></span>
									</div>
									<span style="margin-right: 5px;float:right;margin-top:  line-height: 36;" id="button">
										<input onclick="deleteStudentInfo();" type="button" class="btn btn-success" style="width: 70px;" id="query"  value="删 除" />
								    	<input onclick="backPage();" type="button" class="btn btn-success" style="width:70px;" id="query"  value="返 回" />
								     </span>
								</div>								
							</div>
						</div>
				
		</div>
		</form>
	</div>
</body>
</html>