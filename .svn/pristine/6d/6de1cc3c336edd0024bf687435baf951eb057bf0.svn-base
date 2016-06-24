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
<title>课程模块设置</title>
<style >
td{
	word-wrap:break-word;
	text-align:center;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
var $$=jQuery.noConflict();
	window.onload = function (){
		<c:forEach items="${lst}" var="ls">
        $$("[name='checkbox']").each(function(){
        	if('${ls}'==$$(this).val()){
        		$$(this).attr("checked",'true');
        	}
        });
        </c:forEach>
	}
	function back(){
		$$("#fm").submit();
	}
</script>
</head>
<body style="background: #EEE">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">查看课程模块</a>
		</div>
		
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div  style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								模块信息
							</div>
							<div class="widget-content nopadding">
								<c:forEach items="${pageObj.pageElements}" var="roll" varStatus="status" >
								<table class="table table-bordered table-striped " style="table-layout:fixed;">
									<tbody>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学科：</td>
											<td style="text-align: left;" width="70%">${roll.subject_name}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块编号：</td>
											<td style="text-align: left;" width="70%">${roll.course_code}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块名称：</td>
											<td style="text-align: left;" width="70%">${roll.course_name}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块简称：</td>
											<td style="text-align: left;" width="70%">${roll.course_short_name}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">模块类别：</td>
											<td style="text-align: left;" width="70%">${roll.course_kindname}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学时数：</td>
											<td style="text-align: left;" width="70%">${roll.period_count}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学分：</td>
											<td style="text-align: left;" width="70%">${roll.credit_hour}</td>
										</tr>
										<tr>
									        <td rowspan="3"><div id="inputname" style="text-align:right;font-weight:bold;height:111px;line-height:111px;overflow:hidden;  ">开设学段：</div></td>
									        <td>
									        <div id="inputvalue">
									          <input type="checkbox" id="checkbox1" name="checkbox" value="1230001^1230101"  disabled="disabled"/>
									          		高一第1学段&nbsp;
									           <input type="checkbox" id="checkbox2" name="checkbox" value="1230001^1230110" disabled="disabled"/>
									          		高一第2学段&nbsp;
									           <input type="checkbox" id="checkbox3" name="checkbox" value="1230001^1230120" disabled="disabled"/>
									          		高一第3学段&nbsp;
									           <input type="checkbox" id="checkbox4" name="checkbox" value="1230001^1230130" disabled="disabled"/>
									          		高一第4学段&nbsp;
									         </div>
									         </td>
									      </tr>
									      <tr>
									         <td>
									         <div id="inputvalue">
									           <input type="checkbox" id="checkbox5" name="checkbox" value="1230010^1230101" disabled="disabled"/>
									          		高二第1学段&nbsp;
									           <input type="checkbox" id="checkbox6" name="checkbox" value="1230010^1230110" disabled="disabled"/>
									          		高二第2学段&nbsp;
									           <input type="checkbox" id="checkbox7" name="checkbox" value="1230010^1230120" disabled="disabled"/>
									          		高二第3学段&nbsp;
									           <input type="checkbox" id="checkbox8" name="checkbox" value="1230010^1230130" disabled="disabled"/>
									          		高二第4学段&nbsp;
									         </div>
									         </td>
									      </tr>
									      <tr>
									        <td>
									        <div id="inputvalue">
									           <input type="checkbox" id="checkbox9" name="checkbox" value="1230020^1230101" disabled="disabled"/>
									         		 高三第1学段&nbsp;
									           <input type="checkbox" id="checkbox10" name="checkbox" value="1230020^1230110" disabled="disabled"/>
									         		 高三第2学段&nbsp;
									           <input type="checkbox" id="checkbox11" name="checkbox" value="1230020^1230120" disabled="disabled"/>
									          		高三第3学段&nbsp;
									           <input type="checkbox" id="checkbox12" name="checkbox" value="1230020^1230130" disabled="disabled"/>
									          		高三第4学段&nbsp;
									         </div>
									         </td>
									    </tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">学习方向：</td>
											<td style="text-align: left;" width="70%">${roll.student_aspectname}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">系列：</td>
											<td style="text-align: left;" width="70%">${roll.series_name}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">备注：</td>
											<td style="text-align: left;" width="70%">${roll.course_remark}</td>
										</tr>
									</tbody>
								</table>	
								</c:forEach>
								<div class="form-actions pagination alternate"  style="margin-top: 0px;margin-bottom: 0px;">
									<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
										<button  style="width: 70px;" class="btn btn-success" onclick="back()">返回</button>
								     </span>
								</div>						
							</div>
						</div>
		</div>
	</div>
	 <form action="${ctx}/innercourse/InnerCourse.a" method="post" id="fm">
		 	<input type="hidden" name="subjectid" id="subjectidback" value="${subjectid}"/>
			<input type="hidden" name="coursekind" id="coursekindback" value="${coursekind}"/>
			<input type="hidden" name="isdefault" id="isdefaultback" value="${isdefault}"/>
			<input type="hidden" name="coursename" id="coursenameback" value="${coursename}"/> 
			<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/> 
			<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/> 
		 </form>
</body>
</html>