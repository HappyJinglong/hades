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
<title>课程模块设置</title>
<style >
	.li{
		margin-left: 10%;
	}
	li{
	 float: left;
	 height: 100%
	}
	ul{
		list-style:none;
	}
	select{
		height: 30px;
			width: 90px	
	}
		 .spnContent { 
	        vertical-align: middle;   
            margin-left: 10px;  
            color: white;  
            font-size: 18px;  
            font-weight: bold;  
} 
td{
	word-wrap:break-word;
	text-align:center;
}
</style>
<script type="text/javascript">
var $$=jQuery.noConflict();
var isDelete=0;
window.onload = function (){
	<c:if test="${not empty pageObj}">
		<c:if test="${not empty pageObj.pageElements}">
			$$("#button").css("margin-top","-35px");
		</c:if>
	</c:if>
	if('${noticeflag}'=="1"){
		alert_upload_success("保存成功");
	}
	if('${noticeflag}'=="2"){
		alert_upload_success("删除成功");
	}
}

	function add(){
		var cmis30id = $$("#cmis30id").val();
		var subjectid = $$("#subjectid").val();
		var coursekind = $$("#coursekind").val();
		var isdefault = $$("#isdefault").val();
		var coursename = $$("#coursename").val();
		window.location.href="${ctx }/innercourse/InnerCourse.a?doAdd&cmis30id="+cmis30id+"&subjectid="+subjectid+"&coursekind="+coursekind+"&isdefault="+isdefault+"&coursename="+coursename;
	}
	
	function updateCourse(){
		var radio="";
		$$("input[name='radio']").each(function(){ 
			  if ($$(this).attr("checked") == true){
				  radio=$$(this).val(); 
			  }
		});
		if(radio!=""){
			var ids = new Array();
			ids = radio.split("#");
			var cmis30id = $$("#cmis30id").val();
			var subjectid = $$("#subjectid").val();
			var coursekind = $$("#coursekind").val();
			var isdefault = $$("#isdefault").val();
			var coursename = $$("#coursename").val();
			window.location.href="${ctx }/innercourse/InnerCourse.a?courseid="+ids[0]+"&isUpdate="+"1"+"&cmis30id="+cmis30id+"&subjectid="+subjectid+"&coursekind="+coursekind+"&isdefault="+isdefault+"&coursename="+coursename;
		}else{
			alert_KG_self_tip("请选择需要修改的信息！");
		}
	}
	
	function deleteCourse(){
		var radio="";
		$$("input[name='radio']").each(function(){ 
			  if ($$(this).attr("checked") == true){
				  radio=$$(this).val(); 
			  }
		});
		if(isDelete==0){
			isDelete=1;
			var r=apprasial_delete();
			 if (r==true){
				 if(radio!=""){
						var ids = new Array();
						ids = radio.split("#");
					 	if(ids[1]=="0"){
							Ext.Ajax.request({
								url:'${ctx}/innercourse/InnerCourse.a?deleteCourse',
								method:'POST',
								defaults:{autoScroll: true},
								success:function(response,options){
									if(response.responseText=="##"){
										alert_delete_failure("");
										isDelete=0;
									}else{
										if(response.responseText=="@@"){
											alert_KG_self_tip("该记录学生成绩已经录入，不能删除！");
										}else{
											$$("#noticeflag").val(2); 
											$$("#form").submit();
										}
										isDelete=0;
									}
								},
								params : {
									courseid : ids[0],
									coursename : ids[2]
								}
							});
						 }else{
							 isDelete=0;
							 alert_upload_failure("来源为系统的课程模块不可删除!");
						} 
					}else{
						isDelete=0;
						alert_KG_self_tip("请选择需要删除的信息！");
					}
			 }else{
				 isDelete=0;
			 }
		}else{
			alert_KG_self_tip("正在删除中...");
		}
	}
	function toQueryList(val){
		var str = '<input id="courseid" name="courseid" type="hidden" value="'+val+'"/>';
		$$("form").append(str);
		$$("#form").submit();
	}
	function loading(){
		ShowDiv();
		$$("#form").submit();
	}
</script>
</head>
<body style="background: #EEE">
<form action="${ctx}/innercourse/InnerCourse.a" id="form" method="post">
<input type="hidden" id="noticeflag" name="noticeflag" value="0"/>
<input type="hidden" name="cmis30id" id="cmis30id" value="${cmis30id}"/>
	<div style="width: 100%;margin-bottom: 35px">
		<div style="width: 100%;position: fixed;top: 0px;z-index: 100;background: #EEE">
		<div id="breadcrumb" style="margin-top: 0px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>课程模块</a>
		</div>
			<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 54.5px">
				 <ul >
				 	<li style="margin-left: 5%">
				 	   学科：
				 		<select name="subjectid" id="subjectid">
				 			<option value="" ${(subjectid == "")?"selected":"" }>全部</option>
	               			<app:sysSubjectSelectTag1 subjectId="${subjectid}"/>
	              		</select>
				 	</li>
				 	<li class="li">
				 	     模块类别：
					 	<select name="coursekind" id="coursekind">
					 		<option value="" ${(coursekind == "")?"selected":"" }>全部</option>
				 			<option value="1230301" ${(coursekind eq "1230301")?"selected":"" }>必修</option>
		            		<option value="1230315" ${(coursekind eq "1230315")?"selected":"" }>选修</option>
		            		<option value="1230310" ${(coursekind eq "1230310")?"selected":"" }>必选</option>
					 	</select>     
				 	</li>
				 	<li class="li">
				 	     来源：
					 	<select name="isdefault" id="isdefault">
				 			<option value="" ${(isdefault == "")?"selected":"" }>全部</option>
				 			<option value="1" ${(isdefault eq "1")?"selected":"" }>系统</option>
		            		<option value="0" ${(isdefault eq "0")?"selected":"" }>自编</option>
					 	</select>     
				 	</li>
				 	<li class="li">
				 	     模块名称：
					 	  <input type="text" id="coursename" name="coursename" value="${coursename}" style="height: 15px;width: 90px;border-left: 1px solid;border-top: 1px solid;"/>    
				 	</li>
				 	 <li class="li" style="height:50px;line-height: 50px;vertical-align:middle; ">
				 	 <span>&nbsp;
				 	 	</span>
				  		<input type="button" class="btn btn-success" onclick="loading();" style="width: 70px;margin-bottom: 7px" id="query"  value="查 询" /> 
					 </li>
				 </ul>
			</div>
			<div style="margin-top: 13px;margin-left: 13px;margin-right: 13px">
				<div style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								模块列表
				</div>
				<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="4%" ><i class="icon-resize-vertical"></i></th>
											<th width="4%" style="font-size: 14px">序号</th>
											<th width="8%" style="font-size: 13px">学科</th>
											<th width="8%" style="font-size: 13px">模块编号</th>
											<th width="20%"  style="font-size: 13px">模块名称</th>
											<th  width="8%" style="font-size: 13px">学习方向</th>
											<th width="16%" style="font-size: 13px">系列</th>
											<th  width="8%" style="font-size: 13px">学时</th>
											<th  width="8%" style="font-size: 13px">学分</th>
											<th  width="8%" style="font-size: 13px">模块类别</th>
											<th  width="8%" style="font-size: 13px">来源</th>
										</tr>
									</thead>
								</table>
				</div>
			
			</div>
		</div>
		<div style="width: 100%;margin-top: 165px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<tbody><!-- style="table-layout:fixed;" -->
									<c:if test="${not empty pageObj.pageElements}">
     									<c:forEach items="${pageObj.pageElements}" var="roll" varStatus="status" >
											<tr>
												<td width="4%"  style="text-align: center;vertical-align: middle;">
												<c:choose>  
												   <c:when test="${roll.subject_name == '研究性学习活动' || roll.subject_name == '社会实践'||roll.subject_name == '社区服务'}">     
							  					   	<input type="radio" name="radio" disabled id="radio" value="${roll.course_id}#${roll.is_default}#${roll.course_name}"/> 
												   </c:when>  
												     
												   <c:otherwise>   
												      <input type="radio" name="radio"  id="radio" value="${roll.course_id}#${roll.is_default}#${roll.course_name}"/>
												   </c:otherwise>  
												</c:choose>  
												</td>
												<td width="4%"  style="text-align: center;vertical-align: middle;">${status.count + pageObj.pageSize * (pageObj.currPageNumber-1)}</td>
												<td width="8%" style="text-align: center;vertical-align: middle;">${roll.subject_name}</td>
												<td width="8%"  style="text-align: center;vertical-align: middle;">
												
												<c:choose>
											<c:when test="${fn:length(roll.course_code) > 10}">
												<c:out
													value="${fn:substring(roll.course_code, 0, 10)}..." />
											</c:when>
											<c:otherwise>
												 <!--<c:out value="${book.bookAuthors} " />-->
												${roll.course_code}
											</c:otherwise>
										</c:choose>
												
												
												</td>
												<td width="20%" style="text-align: center;vertical-align: middle;"><a href="#" onclick="toQueryList('${roll.course_id}')" title="${roll.course_name}" >
												         	<c:choose>
											<c:when test="${fn:length(roll.course_name) > 10}">
												<c:out
													value="${fn:substring(roll.course_name, 0, 10)}..." />
											</c:when>
											<c:otherwise>
												 <!--<c:out value="${book.bookAuthors} " />-->
												${roll.course_name}
											</c:otherwise>
										</c:choose>
												
												</a></td>
												<td width="8%"  style="text-align: center;vertical-align: middle;">${roll.student_aspectname}</td>
												<td width="16%"style="text-align: center;vertical-align: middle;">${roll.series_name}</td>
												<td  width="8%" style="text-align: center;vertical-align: middle;">${roll.period_count}</td>
												<td  width="8%" style="text-align: center;vertical-align: middle;">${roll.credit_hour}</td>
												<td width="8%" style="text-align: center;vertical-align: middle;">${roll.course_kindname}</td>
												<c:if test="${roll.is_default eq 1}">
												<td  width="8%" style="text-align: center;vertical-align: middle;">系统</td>
												</c:if>
												<c:if test="${roll.is_default eq 0}">
												<td  width="8%" style="text-align: center;vertical-align: middle;">自编</td>
												</c:if>
											</tr>
									 	</c:forEach>
     								</c:if>		
									</tbody>
								</table>	
								
								<div class="form-actions pagination alternate" style="margin-top: 0px;margin-bottom: 0px;">
								<div id="pagearea">
									<span><input name="pageNo" value="${pageNo }"
										type="hidden" />
									<c:if test="${not empty pageObj}">
											<c:if test="${not empty pageObj.pageElements}">
												<jsp:include page="../common/pager-nest.jsp">
													<jsp:param name="toPage" value="1" />
													<jsp:param name="showCount" value="1" />
													<jsp:param name="action" value="doShow" />
												</jsp:include>
											</c:if>
										</c:if></span>
								</div>
								<span style="margin-right: 30px;float:right;margin-top:5px;" id="button">
										<button onclick="add()" type="button" style="width: 70px;" class="btn btn-success">增 加</button>
										<button  style="width: 70px;" type="button" class="btn btn-success" onclick="updateCourse()">修 改</button>
										<button style="width: 70px;" type="button" class="btn btn-success" onclick="deleteCourse()">删 除</button>
								</span>
								</div>						
							</div>
						</div>
		</div>
	</div>
	</form>
	<%@ include file="/common/div.jsp"%>
</body>
</html>