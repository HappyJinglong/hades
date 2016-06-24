<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初中学科统计</title>
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
		  $("form:first").submit();
			  })
	})  
function graduateyearOnchange(g,greed){
	var ss= g.value;
	if(0==ss){
		var this_div =document.getElementById("greed_ban");
		this_div.innerHTML='';
		var str="<select id='greed' name='greed' class='wenziliebiao100'>"
		+"<option selected='selected' value='0'>全部&nbsp;&nbsp;&nbsp;&nbsp;</option>"
		+"<option value='1'>六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
		+"<option value='2'>七年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
		+"<option value='3'>八年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
		+"<option value='4'>九年级&nbsp;&nbsp;&nbsp;&nbsp;</option>" 
		+"</select>"; 
		this_div.innerHTML=str;
	}else {
		var ff=parseInt(ss) - parseInt(greed);
		if(0==ff){
			var this_div =document.getElementById("greed_ban");
			this_div.innerHTML='';
			var str="<select id='greed' name='greed' class='wenziliebiao100'>"
				+"<option selected='selected' value='0'>全部&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='1'>六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='2'>七年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='3'>八年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='4'>九年级&nbsp;&nbsp;&nbsp;&nbsp;</option>" 
				+"</select>"; 
			this_div.innerHTML=str;
		}else if(1==ff){
			var this_div =document.getElementById("greed_ban");
			this_div.innerHTML='';
			var str="<select id='greed' name='greed' class='wenziliebiao100'>"
				+"<option selected='selected' value='0'>全部&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='1'>六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='2'>七年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='3'>八年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"</select>"; 
			this_div.innerHTML=str;
		}else if(2==ff){
			var this_div =document.getElementById("greed_ban");
			this_div.innerHTML='';
			var str="<select id='greed' name='greed' class='wenziliebiao100'>"
				+"<option selected='selected' value='0'>全部&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='1'>六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='2'>七年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"</select>"; 
			this_div.innerHTML=str;
		}else if(3==ff){
			var this_div =document.getElementById("greed_ban");
			this_div.innerHTML='';
			var str="<select id='greed' name='greed' class='wenziliebiao100'>"
				+"<option value='1'>六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"</select>"; 
			
			this_div.innerHTML=str;
		}
	}
}


</script>

</head>
<body>

<div class="dangqianwz">
  <form action="${ctx}/areaStat/CzTopicStat.a" method="post" >
 	<span class="fl">当前位置：必填数据填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <span >学校：
	      <!-- <input id="schoolName" name="schoolName" type="text" class="wenben120" /> -->
	      <input  id="schoolName" name="schoolName" type="text"/>
	      </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <span >届别：
	     <select id="graduateyear" name="graduateyear" class=" wenziliebiao100" onchange="graduateyearOnchange(this,2015)">
	               <option selected="selected" value="0">全部&nbsp;&nbsp;</option>
	                <option>2015</option>
	                <option>2016</option>
	                <option>2017</option>
	                <option>2018</option>
	            </select> 
	     </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>科目:
	     <select id="topic" name="topic" class="wenziliebiao100">
	               <option selected="selected" value="0">全部</option>
	               <option value="1">思想品德</option>
	               <option value="2">语文</option>
	               <option value="3">数学</option>
	               <option value="4">英语</option>
	               <option value="5">历史</option>
	               <option value="6">地理</option>
	               <option value="7">物理</option>
	               <option value="8">化学</option>
	               <option value="9">生物</option>
	               <option value="10">体育与健康</option>
	               <option value="11">音乐</option>
	               <option value="12">美术</option>
	               <option value="13">劳动技术</option>
	               <option value="14">信息技术</option>
	               <option value="15">研究性学习</option>
	               <option value="16">社区服务与社会实践活动</option>
	               <option value="17">地方与校本课程</option>
	            </select> 
	     </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                   学年：<span  id="greed_ban">
	             <select id="greed" name="greed" class="wenziliebiao100">
	               <option selected="selected" value="0">全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="1">六年级&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="2">七年级&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="3">八年级&nbsp;&nbsp;&nbsp;&nbsp;</option>
	               <option value="4">九年级&nbsp;&nbsp;&nbsp;&nbsp;</option> 
	            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
              <span class="button ml10"><a href="#" id="commit"  style="margin:10px;" />查  询</a></span>&nbsp;&nbsp;&nbsp; 
             <!--  <span ><input type="submit" value="查 询" class="button ml10" id="commit"/></span>  -->   
            <span ><input type="button" value="导  出" class="button ml10" /></span>
     </form>
</div>

<div id="tjb_main">
  <div class="top">
 
 
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <div class="biaoti">***届学生***年级课程评语填写情况 </div>
   <tr class="biaotou_zi">
     <td class="school_Class" width="100" rowspan="2" >学校</td>
     <td  class="graduateyea_Class" width="40" rowspan="2">届别</td>
     <td class="greed_Class"  width="80" rowspan="2">年级</td>
    <c:if test="${topicxuanze==0}">
     <td class="1_Class" colspan="4">思想品德</td>
     <td class="2_Class"  colspan="4">语文</td>
     <td class="3_Class" colspan="4">数学</td>
     <td class="4_Class" colspan="4">外语</td>
     <td class="5_Class" colspan="4">历史</td>
     <td class="6_Class" colspan="4">地理</td>
     <td class="7_Class" colspan="4">物理</td>
     <td class="8_Class" colspan="4">化学</td>
     <td class="9_Class" colspan="4">生物</td>
     <td class="10_Class" colspan="4">体育与健康</td>
     <td class="11_Class" colspan="4">音乐</td>
     <td class="12_Class" colspan="4">美术</td>
     <td class="13_Class" colspan="4">劳动技术</td>
     <td class="14_Class" colspan="4">信息技术</td>
     <td class="15_Class" colspan="4">研究性学习</td>
     <td class="16_Class" colspan="4">社区服务与社会实践</td>
     <td class="17_Class" colspan="4">地方与校本课程</td>
   </c:if> 
    <c:if test="${topicxuanze!=0}">
      <td class="1_Class" colspan="4">${topicmu}</td>
    </c:if> 
     </tr>
  <tr class="biaotou_zi">
    
    <c:if test="${topicxuanze==0}">
     <!--思想品德 -->
    <td class="1_Class"  width="40">学生总人数</td>
    <td class="1_Class" width="40">已填写人数</td>
    <td class="1_Class"  width="40">完成百分比（%）</td>
    <td class="1_Class"  width="40">已评价条目数</td>
    <!--语文-->
    <td class="2_Class"  width="40">学生总人数</td>
    <td class="2_Class" width="40">已填写人数</td>
    <td class="2_Class" width="40">完成百分比（%）</td>
    <td class="2_Class" width="40">已评价条目数</td>
    <!--数学 -->
    <td class="3_Class" width="40">学生总人数</td>
    <td class="3_Class" width="40">已填写人数</td>
    <td class="3_Class" width="40">完成百分比（%）</td>
    <td class="3_Class" width="40">已评价条目数</td>
    <!--外语 -->
    <td class="4_Class" width="40">学生总人数</td>
    <td class="4_Class" width="40">已填写人数</td>
    <td class="4_Class" width="40">完成百分比（%）</td>
    <td class="4_Class" width="40">已评价条目数</td>
    <!--历史 -->
    <td class="5_Class" width="40">学生总人数</td>
    <td class="5_Class" width="40">已填写人数</td>
    <td class="5_Class" width="40">完成百分比（%）</td>
    <td class="5_Class" width="40">已评价条目数</td>
    <!--地理 -->
    <td class="6_Class" width="40">学生总人数</td>
    <td class="6_Class" width="40">已填写人数</td>
    <td class="6_Class" width="40">完成百分比（%）</td>
    <td class="6_Class" width="40">已评价条目数</td>
    <!--物理 -->
    <td class="7_Class" width="40">学生总人数</td>
    <td class="7_Class" width="40">已填写人数</td>
    <td class="7_Class" width="40">完成百分比（%）</td>
    <td class="7_Class" width="40">已评价条目数</td>
    <!--化学 -->
    <td class="8_Class" width="40">学生总人数</td>
    <td class="8_Class" width="40">已填写人数</td>
    <td class="8_Class" width="40">完成百分比（%）</td>
    <td class="8_Class" width="40">已评价条目数</td>
    <!--生物 -->
    <td class="9_Class" width="40">学生总人数</td>
    <td class="9_Class"width="40">已填写人数</td>
    <td class="9_Class" width="40">完成百分比（%）</td>
    <td class="9_Class" width="40">已评价条目数</td>
    <!--体育与健康 -->    
    <td class="10_Class" width="40">学生总人数</td>
    <td class="10_Class" width="40">已填写人数</td>
    <td class="10_Class" width="40">完成百分比（%）</td>
    <td class="10_Class" width="40">已评价条目数</td>
     <!--音乐 -->
    <td class="11_Class" width="40">学生总人数</td>
    <td class="11_Class" width="40">已填写人数</td>
    <td class="11_Class" width="40">完成百分比（%）</td>
    <td class="11_Class" width="40">已评价条目数</td>
     <!--美术-->    
    <td class="12_Class" width="40">学生总人数</td>
    <td class="12_Class" width="40">已填写人数</td>
    <td class="12_Class" width="40">完成百分比（%）</td>
    <td class="12_Class" width="40">已评价条目数</td>
    <!--劳动技术 -->
    <td class="13_Class" width="40">学生总人数</td>
    <td class="13_Class" width="40">已填写人数</td>
    <td class="13_Class" width="40">完成百分比（%）</td>
    <td class="13_Class" width="40">已评价条目数</td>
    <!--信息技术 -->
    <td class="14_Class" width="40">学生总人数</td>
    <td class="14_Class" width="40">已填写人数</td>
    <td class="14_Class" width="40">完成百分比（%）</td>
    <td class="14_Class" width="40">已评价条目数</td>
    <!--研究性学习-->
    <td class="15_Class" width="40">学生总人数</td>
    <td class="15_Class" width="40">已填写人数</td>
    <td class="15_Class" width="40">完成百分比（%）</td>
    <td class="15_Class" width="40">已评价条目数</td>
    <!--社区服务与社会实践活动 -->
    <td class="16_Class" width="40">学生总人数</td>
    <td class="16_Class" width="40">已填写人数</td>
    <td class="16_Class" width="40">完成百分比（%）</td>
    <td class="16_Class" width="40">已评价条目数</td>
    <!--地方与校本课 -->
    <td class="17_Class" width="40">学生总人数</td>
    <td class="17_Class"  width="40">已填写人数</td>
    <td class="17_Class"  width="40">完成百分比（%）</td>
    <td class="17_Class" width="40">已评价条目数</td>
  </tr>
   </c:if>
  <c:if test="${topicxuanze!=0}">
          <!--地方与校本课 -->
    <td class="17_Class" width="40">学生总人数</td>
    <td class="17_Class"  width="40">已填写人数</td>
    <td class="17_Class"  width="40">完成百分比（%）</td>
    <td class="17_Class" width="40">已评价条目数</td>
  </c:if>
  
<c:if test="${topicxuanze==0}">  
<c:forEach items="${listTopic}" var="listopicschool" varStatus="vs"> 
   		
 		<c:forEach items="${listopicschool.listGreedDto}" var="listat" varStatus="vs2">
     	
         <c:forEach items="${listat.list}" var="lis" varStatus="vs3">
		<tr>
		 <c:if test="${vs3.index==0 and vs2.index==0}"><td class="school_Class"  rowspan="${listopicschool.schoolRow}">${listopicschool.schoolName}</td></c:if>
		  <c:if test="${vs3.index==0}"><td class="graduateyea_Class"  rowspan="${listat.greedRow}">${listat.greedName}</td></c:if> 
		    <td class="greed_Class" >${lis.greed}</td>
		  
		   <!--思想政治 -->
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.governmentcounted}</td>
		   <td class="2_Class">${lis.governmentper}</td>
		   <td class="2_Class">${lis.governmentgross}</td>
		   <!--语文 -->
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.chinesecounted}</td>
		   <td class="2_Class">${lis.chineseper}</td>
		   <td class="2_Class">${lis.chinesegross}</td>
		    <!--数学 -->
		   <td class="3_Class">${lis.chinesecount}</td> 
		   <td class="3_Class">${lis.arithcounted}</td>
		   <td class="3_Class">${lis.arithper}</td>
		   <td class="3_Class">${lis.arithgross}</td>
		  
		   <!--英语 -->
		   <td class="4_Class">${lis.chinesecount}</td> 
		   <td class="4_Class">${lis.englishcounted}</td>
		   <td class="4_Class">${lis.englishper}</td>
		   <td class="4_Class">${lis.englishgross}</td>
		  
		   <!--历史 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.historycounted}</td>
		   <td class="2_Class">${lis.historyper}</td>
		   <td class="2_Class">${lis.historygross}</td>
		   <!--地理 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.geographycounted}</td>
		   <td class="2_Class">${lis.geographyper}</td>
		   <td class="2_Class">${lis.geographyross}</td>
		   <!--物理 -->
		    <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.physicscounted}</td>
		   <td class="2_Class">${lis.physicsper}</td>
		   <td class="2_Class">${lis.physicsross}</td>
		   <!--化学 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.chemistrycounted}</td>
		   <td class="2_Class">${lis.chemistryper}</td>
		   <td class="2_Class">${lis.chemistryross}</td>
		   <!--生物-->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.biologycounted}</td>
		   <td class="2_Class">${lis.biologyper}</td>
		   <td class="2_Class">${lis.biologyross}</td>
		   <!--体育与健康-->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.currencycounted}</td>
		   <td class="2_Class">${lis.currencyper}</td>
		   <td class="2_Class">${lis.currencyross}</td>
		   <!--音乐 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.musiccounted}</td>
		   <td class="2_Class">${lis.musicper}</td>
		   <td class="2_Class">${lis.musicross}</td>
		   <!--美术 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.artcounted}</td>
		   <td class="2_Class">${lis.artcper}</td>
		   <td class="2_Class">${lis.artcross}</td>
		    <!--劳动技术 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.houseworkcounted}</td>
		   <td class="2_Class">${lis.houseworkcper}</td>
		   <td class="2_Class">${lis.houseworkcross}</td>
		    <!--信息技术-->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.infocounted}</td>
		   <td class="2_Class">${lis.infoper}</td>
		   <td class="2_Class">${lis.infoross}</td>
		    <!--研究性学习 -->
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.labcounted}</td>
		   <td class="2_Class">${lis.labcper}</td>
		   <td class="2_Class">${lis.labcross}</td>
		    <!--社区服务与社区实践活动 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.societycounted}</td>
		   <td class="2_Class">${lis.societycper}</td>
		   <td class="2_Class">${lis.societycross}</td>
		    <!--地方与校本课程 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis. climecounted}</td>
		   <td class="2_Class">${lis.climecper}</td>
		   <td class="2_Class">${lis.climecross}</td>
		   </tr>
		   </c:forEach>  
   </c:forEach> 
</c:forEach>
</c:if> 

<!-- -----------------------------ffffffffffffff-------------------------------------------------------- -->
<c:if test="${topicxuanze!=0}">  
<c:forEach items="${listTopic}" var="listopicschool" varStatus="vs"> 
   		
 		<c:forEach items="${listopicschool.listGreedDto}" var="listat" varStatus="vs2">
     	
         <c:forEach items="${listat.list}" var="lis" varStatus="vs3">
		<tr>
		 <c:if test="${vs3.index==0 and vs2.index==0}"><td class="school_Class"  rowspan="${listopicschool.schoolRow}">${listopicschool.schoolName}</td></c:if>
		  <c:if test="${vs3.index==0}"><td class="graduateyea_Class"  rowspan="${listat.greedRow}">${listat.greedName}</td></c:if> 
		    <td class="greed_Class" >${lis.greed}</td>
		   <!--思想政治 -->
		    <c:if test="${topicxuanze==1}">
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.governmentcounted}</td>
		   <td class="2_Class">${lis.governmentper}</td>
		   <td class="2_Class">${lis.governmentgross}</td>
		   </c:if>
		   <!--语文 -->
		    <c:if test="${topicxuanze==2}">
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.chinesecounted}</td>
		   <td class="2_Class">${lis.chineseper}</td>
		   <td class="2_Class">${lis.chinesegross}</td>
		   </c:if>
		    <!--数学 -->
		     <c:if test="${topicxuanze==3}">
		   <td class="3_Class">${lis.chinesecount}</td> 
		   <td class="3_Class">${lis.arithcounted}</td>
		   <td class="3_Class">${lis.arithper}</td>
		   <td class="3_Class">${lis.arithgross}</td>
		   </c:if>
		   <!--英语 -->
		    <c:if test="${topicxuanze==4}">
		   <td class="4_Class">${lis.chinesecount}</td> 
		   <td class="4_Class">${lis.englishcounted}</td>
		   <td class="4_Class">${lis.englishper}</td>
		   <td class="4_Class">${lis.englishgross}</td>
		   </c:if>
		   <!--历史 -->
		    <c:if test="${topicxuanze==5}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.historycounted}</td>
		   <td class="2_Class">${lis.historyper}</td>
		   <td class="2_Class">${lis.historygross}</td>
		   </c:if>
		   <!--地理 -->
		    <c:if test="${topicxuanze==6}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.geographycounted}</td>
		   <td class="2_Class">${lis.geographyper}</td>
		   <td class="2_Class">${lis.geographyross}</td>
		   </c:if>
		   <!--物理 -->
		    <c:if test="${topicxuanze==7}">
		    <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.physicscounted}</td>
		   <td class="2_Class">${lis.physicsper}</td>
		   <td class="2_Class">${lis.physicsross}</td>
		   </c:if>
		   <!--化学 -->
		    <c:if test="${topicxuanze==8}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.chemistrycounted}</td>
		   <td class="2_Class">${lis.chemistryper}</td>
		   <td class="2_Class">${lis.chemistryross}</td>
		   </c:if>
		   <!--生物-->
		    <c:if test="${topicxuanze==9}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.biologycounted}</td>
		   <td class="2_Class">${lis.biologyper}</td>
		   <td class="2_Class">${lis.biologyross}</td>
		   </c:if>
		   <!--体育与健康-->
		    <c:if test="${topicxuanze==10}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.currencycounted}</td>
		   <td class="2_Class">${lis.currencyper}</td>
		   <td class="2_Class">${lis.currencyross}</td>
		   </c:if>
		   <!--音乐 -->
		    <c:if test="${topicxuanze==11}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.musiccounted}</td>
		   <td class="2_Class">${lis.musicper}</td>
		   <td class="2_Class">${lis.musicross}</td>
		   </c:if>
		   <!--美术 -->
		    <c:if test="${topicxuanze==12}">
		    <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.artcounted}</td>
		   <td class="2_Class">${lis.artcper}</td>
		   <td class="2_Class">${lis.artcross}</td>
		   </c:if>
		    <!--劳动技术 -->
		     <c:if test="${topicxuanze==13}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.houseworkcounted}</td>
		   <td class="2_Class">${lis.houseworkcper}</td>
		   <td class="2_Class">${lis.houseworkcross}</td>
		
		   </c:if>
		    <!--信息技术-->
		     <c:if test="${topicxuanze==14}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.infocounted}</td>
		   <td class="2_Class">${lis.infoper}</td>
		   <td class="2_Class">${lis.infoross}</td>
		   </c:if>
		    <!--研究性学习 -->
		     <c:if test="${topicxuanze==15}">
		   <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.labcounted}</td>
		   <td class="2_Class">${lis.labcper}</td>
		   <td class="2_Class">${lis.labcross}</td>
		   </c:if> 
		    <!--社区服务与社区实践活动 -->
		     <c:if test="${topicxuanze==16}">
		    <!--社区服务与社区实践活动 -->
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis.societycounted}</td>
		   <td class="2_Class">${lis.societycper}</td>
		   <td class="2_Class">${lis.societycross}</td>
		   </c:if> 
		    <!--地方与校本课程 -->
		     <c:if test="${topicxuanze==17}">
		     <td class="2_Class">${lis.chinesecount}</td> 
		   <td class="2_Class">${lis. climecounted}</td>
		   <td class="2_Class">${lis.climecper}</td>
		   <td class="2_Class">${lis.climecross}</td>
		   </c:if>
		   </tr>
		   </c:forEach>  
   </c:forEach> 
</c:forEach>
</c:if>

</table>



</div>
</div>

</body>
</html>






























 









