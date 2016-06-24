<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高中学科统计</title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: normal;
}
.biaotou_zi td {
	font-weight: bold;
}
#tjb_main .top .biaoge tr td {
	font-weight: bold;
}
.biaotou_zi {
}
.biaotou_zi {
	font-weight: bold;
}
.biaotou_zi {
	font-weight: normal;
}
.biaotou_zi .biaotou_zi {
	font-weight: bold;
}
.wenziliebiao100{
  width: 115px;
}
.biaoti{
  
  font-size:20px;
  padding-left:20px;
  padding-top:8px;
  width: 100%;
  height: 50px;
}

</style>

<script type="text/javaScript">
var content_div_full="";
/* function  sbmit(){
	       alert("jjj");
           var greed=$("#greed").val(); 
           var topic=$("#topic").val(); 
           var graduateyear=$("#graduateyear").val(); 
           var schoolname=$("#schoolname").val(); 
				Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
				Ext.Ajax.request({
					url:'GzTopicStat.a',
					method:'POST',
					success:function(response,options){
						  var len=Ext.util.JSON.decode(response.responseText);
						    var success=len.success.replace( /^\s+|\s+$/g,"");
						    var info=len.info;
						    if(success="true")
						    {    

						    }else if(success=="false")
						    {
						    	return;
						    }
						   // var content=len.content;
					},
					params : {
						 greed:greed, 
			             topic:topic, 
			             graduateyear:graduateyear, 
			             schoolName:schoolname 
					}
				});
				
		} */
		
$(function(){
	$("#commit").click(function(){
		alert("jjj");
		$("form:first").submit();
			  })
	}) 
function graduateyearOnchange(g){
	
	
	alert(g.value);
}


</script>

</head>
<body>

<div class="dangqianwz">
  <form action="${ctx}/areaStat/GzTopicStat.a" method="post" >
 	<span class="fl">当前位置：必填数据填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <span >学校：
	      <input id="schoolname" name="schoolName" type="text" class="wenben120" value="" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <span >届别：
	     <select id="graduateyear" name="graduateyear" class=" wenziliebiao100" onchange="graduateyearOnchange(this)">
	               <option selected="selected" value="0">全部&nbsp;&nbsp;</option>
	                <option >2016</option>
	                <option>2017</option>
	                <option>2018</option>
	            </select> 
	     </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科目<span >：
	     <select id="topic" name="topic" class="wenziliebiao100">
	               <option selected="selected">全部</option>
	               <option>语文</option>
	               <option>英语</option>
	               <option>数学</option>
	               <option>思想政治</option>
	               <option>历史</option>
	               <option>地理</option>
	               <option>物理</option>
	               <option>化学</option>
	               <option>生物</option>
	               <option>信息技术</option>
	               <option>通用技术</option>
	               <option>音乐</option>
	               <option>美术</option>
	            </select> 
	     </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <span >学年：
	             <select id="greed" name="greed" class="wenziliebiao100">
	               <option selected="selected" value="0">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="1">高一学年&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="2">高二学年&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="3">高三学年&nbsp;&nbsp;&nbsp;&nbsp;</option>
	            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
            <!-- <span class="button ml10" width:100px;><a href="#"     onclick="sbmit()"/>查询</a></span> -->
              <span ><input type="button" value="查 询" class="button ml10" id="commit"/></span>&nbsp;&nbsp;&nbsp;    
            <span ><input type="button" value="导 出" class="button ml10" /></span>
     </form>
</div>

<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <div class="biaoti">***届学生***年级课程评语填写情况 </div>
   <tr class="biaotou_zi">
     <td width="150" rowspan="2" >学校</td>
     <td width="40" rowspan="2">届别</td>
     <td  width="80" rowspan="2">年级</td>
     <td colspan="4">语文</td>
     <td colspan="4">英语</td>
     <td colspan="4">数学</td>
     <td colspan="4">思想政治</td>
     <td colspan="4">历史</td>
     <td colspan="4">地理</td>
     <td colspan="4">物理</td>
     <td colspan="4">化学</td>
     <td colspan="4">生物</td>
     <td colspan="4">信息技术</td>
     <td colspan="4">通用技术</td>
     <td colspan="4">音乐</td>
     <td colspan="4">美术</td>
     </tr>
  <tr class="biaotou_zi">
     <!--语文 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--英语-->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--数学 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--思想政治 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--历史 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--地理 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--物理 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--化学 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--生物 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--信息技术 -->    
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <!--通用技术 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
     <!--音乐 -->    
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
    <!--美术 -->
    <td width="40">学生总人数</td>
    <td width="40">已填写人数</td>
    <td width="40">完成百分比（%）</td>
    <td width="40">已评价条目数</td>
  </tr>
 
<c:forEach items="${listTopic}" var="listopicschool" varStatus="vs"> 
   		
 		<c:forEach items="${listopicschool.listGreedDto}" var="listat" varStatus="vs2">
     	
         <c:forEach items="${listat.list}" var="lis" varStatus="vs3">
		<tr>
		 <c:if test="${vs3.index==0 and vs2.index==0}"><td rowspan="${listopicschool.schoolRow}" class="biaoge">${listopicschool.schoolName}</td></c:if>
		  <c:if test="${vs3.index==0}"><td rowspan="${listat.greedRow}">${listat.greedName}</td></c:if> 
		    <td>${lis.greed}</td>
		   <!--语文 -->
		   <td>${lis.chinesecount}</td>
		   <td>${lis.chinesecounted}</td>
		   <td>${lis.chineseper}</td>
		   <td>${lis.chinesegross}</td>
		   <!--英语 -->
		   <td>${lis.englishcount}</td>
		   <td>${lis.englishcounted}</td>
		   <td>${lis.englishper}</td>
		   <td>${lis.englishgross}</td>
		   <!--数学 -->
		   <td>${lis.arithcount}</td>
		   <td>${lis.arithcounted}</td>
		   <td>${lis.arithgross}</td>
		   <td>${lis.arithper}</td>
		   <!--思想政治 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--历史 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--地理 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--物理 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--化学 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--生物-->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--信息技术 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--通用技术 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--音乐 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <!--美术 -->
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td>
		   <td>&nbsp;</td></tr>
		   </c:forEach>  
   </c:forEach> 
</c:forEach> 
</table>
</div>
</div>

</body>
</html>