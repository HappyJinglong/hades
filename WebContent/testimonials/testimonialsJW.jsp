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
 
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
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


<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>
<title>统计表</title>

</head>
</style>
<script>
</script>
	
</head>
<body>
<div class="dangqianwz">
 	<span class="fl">当前位置课程评语填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <form action="">
	            <span >科目：
	     		<select name="select" class="wenziliebiao100">
	     			<c:forEach items="${sublist }" var="kemu">
		              	 <option value="${kemu.subjectid }">${kemu.subjectName }</option>
		        	</c:forEach>       
	            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <span ><input type="button" value="查 询" class="button ml10" /></span>&nbsp;&nbsp;&nbsp;
	            <span ><input type="button" value="导 出" class="button ml10" /></span>
             </form>
</div>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="20">
      <div class="tishi_left fl">学期课程评语填写情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="100" rowspan="3">科目</td>
     <td width="40" rowspan="3">完成情况</td>
     <td colspan="6">2016届</td>
     <td colspan="6">2017届</td>
     <td colspan="6">2018届</td>
     </tr>
   <tr class="biaotou_zi">
     <td colspan="2">7年级</td>
     <td colspan="2">8年级</td>
     <td colspan="2">9年级</td>
     <td colspan="2">7年级</td>
     <td colspan="2">8年级</td>
     <td colspan="2">9年级</td>
     <td colspan="2">7年级</td>
     <td colspan="2">8年级</td>
     <td colspan="2">9年级</td>
     </tr>
  <tr class="biaotou_zi">
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	<td width="40">第1学期</td>
  	<td width="40">第2学期</td>
  	</tr>
  <tr>
    <td rowspan="4">语文</td>
    <td>学生总人数</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   <td>&nbsp;</td>
   </tr>
  <tr>
    <td>已完成学生数</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td>完成百分比（%）</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>已填写条目数</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
</div>
</div>
</body>
</html>