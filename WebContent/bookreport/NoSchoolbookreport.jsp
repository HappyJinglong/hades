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
function fanhui(){
	javascript:history.go(-1)
}
</script>
	
</head>
<body style="background: #EEE">
	<div style="width: 100%;">
		
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
						  <div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >上报情况统计</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="6%"  style="font-size: 14px">序号</th>
											<th style="font-size: 14px">学校代码</th>
											<th style="font-size: 14px">学校名称</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${schoolList }" var="sch" varStatus="xh" >
										<tr >
											<td style="text-align: center;">${xh.count}</td>
											<td style="text-align: center;">${sch.schoolcode }</td>
											<td style="text-align: center;">${sch.schoolname }</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>	
								<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
									<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
                                     <button onClick="fanhui();"style="width: 100px;"   class="btn btn-success">返回</button>
							      </span>
							  </div>						
						  </div>
						</div>
		</div>
	</div>
</body>
</html>