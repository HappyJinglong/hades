<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
#pj_ziwo_main{margin-top:21px;padding:13px;margin-bottom:21px}
</style>
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  --%>

<title>自我评价</title>

<script type="text/javascript">
	 function select(){
		
		var typeId = document.getElementById("select").value;
		window.location.hash=typeId;
	} 
	 
	 var $$=jQuery.noConflict();
	 function select(){
		var typeId = document.getElementById("select").value;
		window.location.hash=typeId;
	} 
	 
	 window.onload = function (){
		 var td = $$("#table1").find("td");
			<c:forEach items="${appraisalList}" var="pl">
		   	 for(var i=0;i<td.size();i++){
		   		var tdId = $$(td[i]).attr('id'); 
		   		var fy=${pl.appraseridentify};
		   	    var ry=${pl.appraisaltypeid};
		   	    if('7050'==ry){
		   	     var id='${pl.appraisaltypeid}'+"--"+'${pl.semesterid}'+"--"+'1';
		   		   if(id==tdId){
		   		    var dd=	$$(td[i]).text();
		   		     var zhi=parseInt(dd)+parseInt(${pl.checkCount}); 
		   			$$(td[i]).text(zhi);
		   		   }
		   	    }else{	
			   		if("4"==fy||"3"==fy){
							   		if("3"==fy){
								   		 var id='${pl.appraisaltypeid}'+"--"+'${pl.semesterid}'+"--"+'${pl.appraseridentify}';
								   		 if(id==tdId){
								   		    var dd=	$$(td[i]).text();
								   		     var zhi=parseInt(dd)+parseInt(${pl.checkCount}); 
								   			$$(td[i]).text(zhi);
								   		 }
							   		}else{
							   			  var id='${pl.appraisaltypeid}'+"--"+'${pl.semesterid}'+"--"+'3';
								   		   if(id==tdId){
								   		    var dd=	$$(td[i]).text();
								   		     var zhi=parseInt(dd)+parseInt(${pl.checkCount}); 
								   			$$(td[i]).text(zhi);
								   		   }
								   		 }
			   		 }else{	
				   		var id='${pl.appraisaltypeid}'+"--"+'${pl.semesterid}'+"--"+'${pl.appraseridentify}';
				   		if(id==tdId){
				   		    var dd=	$$(td[i]).text();
				   		   
				   			$$(td[i]).text('${pl.checkCount}');
				   		}
			   	   }
		   		
		   	    }
		   		
		   	
		   	 }
		   	 </c:forEach>
	
	 }
	 
</script>
</head>

<body>
<div class="dangqianwz">
 	<span class="fl">当前位置：数据检查</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >选择栏目：
     <select name="select" class="  wenziliebiao100" id="select" onchange="select();">
               <option selected="selected" value='1001'>新学期伊始的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1002'>学期结束时的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1003'>思想道德&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1004'>学业成就&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1005'>合作与交流&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1006'>运动与健康&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1007'>审美与表现&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1008'>综合实践活动&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1009'>个性发展 &nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select> </span> 
 
 </div>
<div id="pj_ziwo_main">
 <div class="top">
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" id="table1" class="biaoge">
           <tr class="title_bg">
             <td colspan="9"><a id="1001"></a>我的评价情况</td>
           </tr>
		    
		    <tr>
		      <td class="th" width="15%" >一级栏目</td>
		      <td width="20" colspan="2" class="th"  >二级栏目</td>
		      <td  class="th"  >高一第一学期</td>
		      <td  class="th"  >高一第二学期</td>
		      <td  class="th"  >高二第一学期</td>
		      <td  class="th"  >高二第二学期</td>
		      <td  class="th"  >高三第一学期</td>
		      <td  class="th"  >高三第二学期</td>
        </tr>
		    <tr>
		      <td height="191" rowspan="3" class="h50 th">新学期伊始的我</td>
		      <td colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" id="1010--11--1">0</td>
		      <td class="h50" id="1010--12--1">0</td>
		      <td class="h50" id="1010--21--1">0</td>
		      <td class="h50" id="1010--22--1">0</td>
		      <td class="h50" id="1010--31--1">0</td>
		      <td class="h50" id="1010--32--1">0</td>
        </tr>
		    <tr>
		      <td colspan="2" class="h50 th"><a id="1002"></a>我的发展目标<span class="red">*</span></td>
		       <td class="h50" id="1020--11--1">0</td>
		      <td class="h50" id="1020--12--1">0</td>
		      <td class="h50" id="1020--21--1">0</td>
		      <td class="h50" id="1020--22--1">0</td>
		      <td class="h50" id="1020--31--1">0</td>
		      <td class="h50" id="1020--32--1">0</td>
        </tr>
		    <tr>
		      <td colspan="2" class="h50 th">家长的期望</td>
		       <td class="h50" id="1020--11--5">0</td>
		      <td class="h50" id="1020--12--5">0</td>
		      <td class="h50" id="1020--21--5">0</td>
		      <td class="h50" id="1020--22--5">0</td>
		      <td class="h50" id="1020--31--5">0</td>
		      <td class="h50" id="1020--32--5">0</td>
        </tr>
		    <tr>                                    
		      <td rowspan="4" class="h50 th">学期结束时的我</td>
		      <td height="95" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" id="2010--11--1">0</td>
		      <td class="h50" id="2010--12--1">0</td>
		      <td class="h50" id="2010--21--1">0</td>
		      <td class="h50" id="2010--22--1">0</td>
		      <td class="h50" id="2010--31--1">0</td>
		      <td class="h50" id="2010--32--1">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">我的发展目标</td>
		       <td class="h50" id="2020--11--1">0</td>
		      <td class="h50" id="2020--12--1">0</td>
		      <td class="h50" id="2020--21--1">0</td>
		      <td class="h50" id="2020--22--1">0</td>
		      <td class="h50" id="2020--31--1">0</td>
		      <td class="h50" id="2020--32--1">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1003"></a>班主任评语<span class="red">*</span></td>
		   	  <td class="h50" id="2030--11--3">0</td>
		      <td class="h50" id="2030--12--3">0</td>
		      <td class="h50" id="2030--21--3">0</td>
		      <td class="h50" id="2030--22--3">0</td>
		      <td class="h50" id="2030--31--3">0</td>
		      <td class="h50" id="2030--32--3">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">家长评语和期望<span class="red">*</span></td>
		      <td class="h50" id="2040--11--5">0</td>
		      <td class="h50" id="2040--12--5">0</td>
		      <td class="h50" id="2040--21--5">0</td>
		      <td class="h50" id="2040--22--5">0</td>
		      <td class="h50" id="2040--31--5">0</td>
		      <td class="h50" id="2040--32--5">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">思想道德</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		     <td class="h50" id="3010--11--1">0</td>
		      <td class="h50" id="3010--12--1">0</td>
		      <td class="h50" id="3010--21--1">0</td>
		      <td class="h50" id="3010--22--1">0</td>
		      <td class="h50" id="3010--31--1">0</td>
		      <td class="h50" id="3010--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		       <td class="h50" id="3020--11--2">0</td>
		      <td class="h50" id="3020--12--2">0</td>
		      <td class="h50" id="3020--21--2">0</td>
		      <td class="h50" id="3020--22--2">0</td>
		      <td class="h50" id="3020--31--2">0</td>
		      <td class="h50" id="3020--32--2">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" id="3020--11--3">0</td>
		      <td class="h50" id="3020--12--3">0</td>
		      <td class="h50" id="3020--21--3">0</td>
		      <td class="h50" id="3020--22--3">0</td>
		      <td class="h50" id="3020--31--3">0</td>
		      <td class="h50" id="3020--32--3">0</td>
        </tr>                                                           
		    <tr>
		      <td class="h50 th"><a id="1004"></a>家长</td>
		     <td class="h50" id="3020--11--5">0</td>
		      <td class="h50" id="3020--12--5">0</td>
		      <td class="h50" id="3020--21--5">0</td>
		      <td class="h50" id="3020--22--5">0</td>
		      <td class="h50" id="3020--31--5">0</td>
		      <td class="h50" id="3020--32--5">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">思想道德记录袋<span class="red"></span></td>
		     <td class="h50" id="3030--11--1">0</td>
		      <td class="h50" id="3030--12--1">0</td>
		      <td class="h50" id="3030--21--1">0</td>
		      <td class="h50" id="3030--22--1">0</td>
		      <td class="h50" id="3030--31--1">0</td>
		      <td class="h50" id="3030--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="6" class="h50 th">学业成就</td>
		      <td height="47" colspan="2" class="h50 th">学科作品展示</td>
		      <td class="h50" id="8010--11--1">0</td>
		      <td class="h50" id="8010--12--1">0</td>
		      <td class="h50" id="8010--21--1">0</td>
		      <td class="h50" id="8010--22--1">0</td>
		      <td class="h50" id="8010--31--1">0</td>
		      <td class="h50" id="8010--32--1">0</td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="h50 th">课程评语<span class="red">*</span></td>
		      <td class="h50" id="890909--11--1">0</td>
		      <td class="h50" id="890909--12--1">0</td>
		      <td class="h50" id="890909--21--1">0</td>
		      <td class="h50" id="890909--22--1">0</td>
		      <td class="h50" id="890909--31--1">0</td>
		      <td class="h50" id="890909--32--1">0</td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="h50 th">自我评价<span class="red"></span></td>
		       <td class="h50" id="8020--11--1">0</td>
		      <td class="h50" id="8020--12--1">0</td>
		      <td class="h50" id="8020--21--1">0</td>
		      <td class="h50" id="8020--22--1">0</td>
		      <td class="h50" id="8020--31--1">0</td>
		      <td class="h50" id="8020--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red"></span></td>
		     <td class="h50" id="8040--11--2">0</td>
		      <td class="h50" id="8040--12--2">0</td>
		      <td class="h50" id="8040--21--2">0</td>
		      <td class="h50" id="8040--22--2">0</td>
		      <td class="h50" id="8040--31--2">0</td>
		      <td class="h50" id="8040--32--2">0</td>
        </tr>
		    <tr>                               
		      <td class="h50 th"><a id="1005"></a>教师</td>
		     <td class="h50" id="8040--11--3">0</td>
		      <td class="h50" id="8040--12--3">0</td>
		      <td class="h50" id="8040--21--3">0</td>
		      <td class="h50" id="8040--22--3">0</td>
		      <td class="h50" id="8040--31--3">0</td>
		      <td class="h50" id="8040--32--3">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		   	  <td class="h50" id="8040--11--5">0</td>
		      <td class="h50" id="8040--12--5">0</td>
		      <td class="h50" id="8040--21--5">0</td>
		      <td class="h50" id="8040--22--5">0</td>
		      <td class="h50" id="8040--31--5">0</td>
		      <td class="h50" id="8040--32--5">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">合作与交流</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" id="4010--11--1">0</td>
		      <td class="h50" id="4010--12--1">0</td>
		      <td class="h50" id="4010--21--1">0</td>
		      <td class="h50" id="4010--22--1">0</td>
		      <td class="h50" id="4010--31--1">0</td>
		      <td class="h50" id="4010--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red">*</span></td>
		   	  <td class="h50" id="4020--11--2">0</td>
		      <td class="h50" id="4020--12--2">0</td>
		      <td class="h50" id="4020--21--2">0</td>
		      <td class="h50" id="4020--22--2">0</td>
		      <td class="h50" id="4020--31--2">0</td>
		      <td class="h50" id="4020--32--2">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
			  <td class="h50" id="4020--11--3">0</td>
		      <td class="h50" id="4020--12--3">0</td>
		      <td class="h50" id="4020--21--3">0</td>
		      <td class="h50" id="4020--22--3">0</td>
		      <td class="h50" id="4020--31--3">0</td>
		      <td class="h50" id="4020--32--3">0</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1006"></a>家长</td>
		      <td class="h50" id="4020--11--5">0</td>
		      <td class="h50" id="4020--12--5">0</td>
		      <td class="h50" id="4020--21--5">0</td>
		      <td class="h50" id="4020--22--5">0</td>
		      <td class="h50" id="4020--31--5">0</td>
		      <td class="h50" id="4020--32--5">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">合作与交流行为记录袋<span class="red"></span></td>
		      <td class="h50" id="4030--11--1">0</td>
		      <td class="h50" id="4030--12--1">0</td>
		      <td class="h50" id="4030--21--1">0</td>
		      <td class="h50" id="4030--22--1">0</td>
		      <td class="h50" id="4030--31--1">0</td>
		      <td class="h50" id="4030--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">运动与健康</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" id="5010--11--1">0</td>
		      <td class="h50" id="5010--12--1">0</td>
		      <td class="h50" id="5010--21--1">0</td>
		      <td class="h50" id="5010--22--1">0</td>
		      <td class="h50" id="5010--31--1">0</td>
		      <td class="h50" id="5010--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" id="5020--11--2">0</td>
		      <td class="h50" id="5020--12--2">0</td>
		      <td class="h50" id="5020--21--2">0</td>
		      <td class="h50" id="5020--22--2">0</td>
		      <td class="h50" id="5020--31--2">0</td>
		      <td class="h50" id="5020--32--2">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" id="5020--11--3">0</td>
		      <td class="h50" id="5020--12--3">0</td>
		      <td class="h50" id="5020--21--3">0</td>
		      <td class="h50" id="5020--22--3">0</td>
		      <td class="h50" id="5020--31--3">0</td>
		      <td class="h50" id="5020--32--3">0</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1007"></a>家长</td>
		      <td class="h50" id="5020--11--5">0</td>
		      <td class="h50" id="5020--12--5">0</td>
		      <td class="h50" id="5020--21--5">0</td>
		      <td class="h50" id="5020--22--5">0</td>
		      <td class="h50" id="5020--31--5">0</td>
		      <td class="h50" id="5020--32--5">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">体质健康<span class="red"></span></td>
		      <td class="h50" id="1010--11">0</td>
		      <td class="h50" id="1010--12">0</td>
		      <td class="h50" id="1010--21">0</td>
		      <td class="h50" id="1010--22">0</td>
		      <td class="h50" id="1010--31">0</td>
		      <td class="h50" id="1010--32">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">审美与表现</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" id="6010--11--1">0</td>
		      <td class="h50" id="6010--12--1">0</td>
		      <td class="h50" id="6010--21--1">0</td>
		      <td class="h50" id="6010--22--1">0</td>
		      <td class="h50" id="6010--31--1">0</td>
		      <td class="h50" id="6010--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		     <td class="h50" id="6020--11--2">0</td>
		      <td class="h50" id="6020--12--2">0</td>
		      <td class="h50" id="6020--21--2">0</td>
		      <td class="h50" id="6020--22--2">0</td>
		      <td class="h50" id="6020--31--2">0</td>
		      <td class="h50" id="6020--32--2">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" id="6020--11--3">0</td>
		      <td class="h50" id="6020--12--3">0</td>
		      <td class="h50" id="6020--21--3">0</td>
		      <td class="h50" id="6020--22--3">0</td>
		      <td class="h50" id="6020--31--3">0</td>
		      <td class="h50" id="6020--32--3">0</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1008"></a>家长</td>
		       <td class="h50" id="6020--11--5">0</td>
		      <td class="h50" id="6020--12--5">0</td>
		      <td class="h50" id="6020--21--5">0</td>
		      <td class="h50" id="6020--22--5">0</td>
		      <td class="h50" id="6020--31--5">0</td>
		      <td class="h50" id="6020--32--5">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">审美与表现记录袋<span class="red">*</span></td>
	 		  <td class="h50" id="6030--11--1">0</td>
		      <td class="h50" id="6030--12--1">0</td>
		      <td class="h50" id="6030--21--1">0</td>
		      <td class="h50" id="6030--22--1">0</td>
		      <td class="h50" id="6030--31--1">0</td>
		      <td class="h50" id="6030--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">综合实践活动</td>
		      <td height="95" colspan="2" class="h50 th">研究性学习<span class="red">*</span></td>
		       <td class="h50" id="9010--11--1">0</td>
		      <td class="h50" id="9010--12--1">0</td>
		      <td class="h50" id="9010--21--1">0</td>
		      <td class="h50" id="9010--22--1">0</td>
		      <td class="h50" id="9010--31--1">0</td>
		      <td class="h50" id="9010--32--1">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">社区服务<span class="red"></span></td>
		       <td class="h50" id="9020--11--1">0</td>
		      <td class="h50" id="9020--12--1">0</td>
		      <td class="h50" id="9020--21--1">0</td>
		      <td class="h50" id="9020--22--1">0</td>
		      <td class="h50" id="9020--31--1">0</td>
		      <td class="h50" id="9020--32--1">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">社会实践活动</td>
		      <td class="h50" id="9030--11--1">0</td>
		      <td class="h50" id="9030--12--1">0</td>
		      <td class="h50" id="9030--21--1">0</td>
		      <td class="h50" id="9030--22--1">0</td>
		      <td class="h50" id="9030--31--1">0</td>
		      <td class="h50" id="9030--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="7" class="h50 th"><a id="1009"></a>个性发展</td>
		      <td height="95" colspan="2" class="h50 th">基本情况<span class="red">*</span></td>
		      <td class="h50" id="7010--11--1">0</td>
		      <td class="h50" id="7010--12--1">0</td>
		      <td class="h50" id="7010--21--1">0</td>
		      <td class="h50" id="7010--22--1">0</td>
		      <td class="h50" id="7010--31--1">0</td>
		      <td class="h50" id="7010--32--1">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" id="7020--11--1">0</td>
		      <td class="h50" id="7020--12--1">0</td>
		      <td class="h50" id="7020--21--1">0</td>
		      <td class="h50" id="7020--22--1">0</td>
		      <td class="h50" id="7020--31--1">0</td>
		      <td class="h50" id="7020--32--1">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" id="7030--11--2">0</td>
		      <td class="h50" id="7030--12--2">0</td>
		      <td class="h50" id="7030--21--2">0</td>
		      <td class="h50" id="7030--22--2">0</td>
		      <td class="h50" id="7030--31--2">0</td>
		      <td class="h50" id="7030--32--2">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		       <td class="h50" id="7030--11--3">0</td>
		      <td class="h50" id="7030--12--3">0</td>
		      <td class="h50" id="7030--21--3">0</td>
		      <td class="h50" id="7030--22--3">0</td>
		      <td class="h50" id="7030--31--3">0</td>
		      <td class="h50" id="7030--32--3">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		       <td class="h50" id="7030--11--5">0</td>
		      <td class="h50" id="7030--12--5">0</td>
		      <td class="h50" id="7030--21--5">0</td>
		      <td class="h50" id="7030--22--5">0</td>
		      <td class="h50" id="7030--31--5">0</td>
		      <td class="h50" id="7030--32--5">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">个性发展过程<span class="red"></span></td>
		       <td class="h50" id="7040--11--1">0</td>
		      <td class="h50" id="7040--12--1">0</td>
		      <td class="h50" id="7040--21--1">0</td>
		      <td class="h50" id="7040--22--1">0</td>
		      <td class="h50" id="7040--31--1">0</td>
		      <td class="h50" id="7040--32--1">0</td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">特长与成果展示<span class="red">*</span></td>
		       <td class="h50" id="7050--11--1">0</td>
		      <td class="h50" id="7050--12--1">0</td>
		      <td class="h50" id="7050--21--1">0</td>
		      <td class="h50" id="7050--22--1">0</td>
		      <td class="h50" id="7050--31--1">0</td>
		      <td class="h50" id="7050--32--1">0</td>
        </tr>
          
      </table>
    </div>
</div>
</body>
</html>
