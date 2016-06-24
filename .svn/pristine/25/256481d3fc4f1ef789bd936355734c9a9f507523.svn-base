<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<title>查看课程模块</title>
<style >
td{
	word-wrap:break-word;
	text-align:center;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
	function checkBox(){
		<c:if test="${not empty gns}">
			<c:forEach items="${gns}" var="gn">
				$("[name='gradeName']").each(function (){
					if($(this).val()=='${gn}'){
						$(this).attr("checked",'true');
					}
				});
			</c:forEach>
		</c:if>
		<c:if test="${not empty sns}">
		<c:forEach items="${sns}" var="sn">
			$("[name='segmentName']").each(function (){
				if($(this).val()=='${sn}'){
					$(this).attr("checked",'true');
				}
			});
		</c:forEach>
	</c:if>
	}
</script>
</head>
<body style="background: #EEE" onload="checkBox();">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">查看课程模块</a>
		</div>
		
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								模块信息
							</div>
							<div class="widget-content nopadding">
							<c:if test="${not empty singleCourseInfo }">
								<table class="table table-bordered table-striped " style="table-layout:fixed;">
									<tbody>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学年：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.schoolyearname }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学段：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.segmentName }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学科：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.subject_name }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块编号：</td>
											<td style="text-align: left;" width="70%">${fn:replace(singleCourseInfo.course_code," ","&nbsp;")}
											</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块名称：</td>
											<td style="text-align: left;" width="70%">${fn:replace(singleCourseInfo.course_name," ","&nbsp;")}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块简称：</td>
											<td style="text-align: left;" width="70%">${fn:replace(singleCourseInfo.course_short_name," ","&nbsp;")}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">指导教师：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.teacherName }_${singleCourseInfo.employyedid }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学时数：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.period_count }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学分：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.credit_hour }</td>
										</tr>
										<tr>
									         <td style="text-align: right;font-weight:bold;" width="30%">适用年级：</td>
											 <td style="text-align: left;" width="70%">
												<div class="controls" style="font-size: 12px;font-weight:normal;">
													<input type="checkbox"  name="gradeName" value="1230001" disabled="disabled"/>&nbsp;&nbsp; 高一年级&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"  name="gradeName" value="1230010" disabled="disabled"/> &nbsp;&nbsp;高二年级&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"  name="gradeName" value="1230020" disabled="disabled"/>&nbsp;&nbsp; 高三年级
												</div>
											 </td>
									      </tr>
									      <tr>
   									         <td style="text-align: right;font-weight:bold;" width="30%">适用学段：</td>
											<td style="text-align: left;" width="70%">
												<div class="controls" style="font-size: 12px;font-weight:normal;">
													<input type="checkbox"  name="segmentName" value="1230101" disabled="disabled"/>&nbsp;&nbsp; 第1学段&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"  name="segmentName" value="1230110" disabled="disabled"/> &nbsp;&nbsp;第2学段&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"  name="segmentName" value="1230120" disabled="disabled"/> &nbsp;&nbsp;第3学段&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"  name="segmentName" value="1230140" disabled="disabled"/>&nbsp;&nbsp; 第4学段
												</div>
											</td>
									    </tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学习方向：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.student_aspectname }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">系列：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.series_name }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">内容简介：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.content_introduction }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">参加要求：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.join_requirement }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">教学要求：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.teach_requirement }</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">备注：</td>
											<td style="text-align: left;" width="70%">${singleCourseInfo.course_remark }</td>
										</tr>
									</tbody>
								</table>	
								</c:if>
								<div class="form-actions pagination alternate"  style="margin-top: 0px;margin-bottom: 0px;">
									<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
									<form action="${ctx }/schoolCourse/SchoolCourseAction.a" method="post">
										<input id="year" value="${year }" name="year" type="hidden"/>
										<input id="segmentId" value="${segmentId }" name="segmentId" type="hidden"/>
										<input id="subjectId" value="${subjectId }" name="subjectId" type="hidden"/>
										<input id="isHired" value="${isHired }" name="isHired" type="hidden"/>
										<input id="courseName" value="${courseName }" name="courseName" type="hidden"/>
										<input id="guidTeacherName" value="${guidTeacherName }" name="guidTeacherName" type="hidden"/>
										<button  style="width: 70px;" class="btn btn-success" >返回</button>
									</form>
								     </span>
								</div>						
							</div>
						</div>
		</div>
	</div>
</body>
</html>