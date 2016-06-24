<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>

<title>统计表</title>
<style type="text/css">
.biaotou_zi td {
	font-weight: bold;
}
</style>

<script type="text/javaScript">
   $(function(){
	   $("#commit").click(function(){
		   $("form:first").submit();
		   
	   })
   }) 
</script>
</head>

<body>


<div class="dangqianwz">
 <form action="${ctx}/areaStat/SchoolStat.a" method="post" >
 	<span class="fl">当前位置：学校填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <span >学校名称：
		     <input class="wenziliebiao100" name="likeSchoolName" type="text" class="wenben120" value="" />
		  </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         
		  <span>学期：
		     <select name="termid" class="wenziliebiao100">
		               <option selected="selected" value="11">高一第一学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="12">高一第二学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="21">高二第一学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="22">高二第二学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="31">高三第一学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="32">高三第二学期&nbsp;&nbsp;&nbsp;&nbsp;</option>
		     </select> 
		    </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <span ><input type="button" value="查 询" class="button ml10" id="commit"/></span>&nbsp;&nbsp;&nbsp;<span ><input type="button" value="导 出" class="button ml10" /></span>
     </form>       
            
          
          
          
             
</div>


<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="10">
      <div class="tishi_left fl">
            <c:if test="${termid eq 11}">高一第一学期学校填写情况</c:if>
            <c:if test="${termid eq 12}">高一第二学期学校填写情况</c:if>
            <c:if test="${termid eq 21}">高二第一学期学校填写情况</c:if>
            <c:if test="${termid eq 22}">高二第二学期学校填写情况</c:if>
            <c:if test="${termid eq 31}">高三第一学期学校填写情况</c:if>
            <c:if test="${termid eq 32}">高三第二学期学校填写情况</c:if> 
            <%--初始化时注意 <c:if test="${termid eq }">高三第二学期学校填写情况</c:if>  --%>
            </div></td>
           
                                   
    </tr>
   <tr class="biaotou_zi">
     <td width="60" rowspan="2">序号</td>
     <td width="300" rowspan="2">学校名称</td>
     <td colspan="2">2015届</td>
     <td colspan="2">2016届</td>
     <td colspan="2">2017届</td>
     
     </tr>
   <tr class="biaotou_zi">
     <td>班级数量</td>
     <td>已填写班级数量</td>
     <td>班级数量</td>
     <td>已填写班级数量</td>
     <td>班级数量</td>
     <td>已填写班级数量</td>
    
     </tr>
		  <c:forEach items="${listSchoolDto}" var="listat"  varStatus="status">
		   <tr mame="single">
		     <td>${status.index+1}</td>
		     <td>${listat.schoolname}</td>
		     <td>${listat.firstClassCount}</td>
		     <td>${listat.firstClassCounted}</td>
		     <td>${listat.secondClassCount}</td>
		     <td>${listat.secondClassCounted}</td>
		     <td>${listat.thirdClassCount}</td>
		     <td>${listat.thirdClassCounted}</td>
		   </tr>
	   </c:forEach>
  <tr>
    <td mame="tongji">${fn:length(listSchoolDto)+1}</td>
    <td>合计</td>
    <td>${firstClassCountSum}</td>
    <td>${firstClassCountedSum}</td>
    <td>${secondClassCountSum}</td>
    <td>${secondClassCountedSum}</td>
    <td>${thirdClassCountSum}</td>
    <td>${thirdClassCountedSum}</td>
  </tr>
</table>
</div>
</div>
</body>
</html>
