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
var $$=jQuery.noConflict();
	 function select(){
		var typeId = document.getElementById("select").value;
		window.location.hash=typeId;
	} 
	 
	 window.onload = function (){
		 var td = $$("#table1").find("td");
			<c:forEach items="${partinfoList}" var="pl">
		   	 for(var i=0;i<td.size();i++){
		   		var tdId = $$(td[i]).attr("id"); 
		   		var id='${pl.two_part_id}'+"--"+'${pl.termType}'+"--"+'${pl.gradeNum}';
		   		if(id==tdId){
		   			$$(td[i]).text('${pl.checkCount}');
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
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="table1">
           <tr class="title_bg">
              <c:if test="${gradelength eq '4'}">
               <td colspan="11"><a id="1001"></a>我的评价情况</td>
              </c:if>
              <c:if test="${gradelength eq '3'}">
               <td colspan="9"><a id="1001"></a>我的评价情况</td>
              </c:if>
           </tr>
		    
		    <tr>
		      <td class="th" width="10%" >一级栏目</td>
		      <td width="20%" colspan="2" class="th"  >二级栏目</td>
		      <c:if test="${gradelength eq '4'}">
		      <td class="th">6年级第一学期</td>
		      <td class="th">6年级第二学期</td>
		      </c:if>
		      <td class="th">7年级第一学期</td>
		      <td class="th">7年级第二学期</td>
		      <td class="th">8年级第一学期</td>
		      <td class="th">8年级第二学期</td>
		      <td class="th">9年级第一学期</td>
		      <td class="th">9年级第二学期</td>
        </tr>
		    <tr>
		      <td height="191" rowspan="2" class="h50 th">新学期伊始的我</td>
		      <td colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="11--1--6">0</td>
		      <td class="h50" id="11--2--6">0</td>
		      </c:if>
		      <td class="h50" id="11--1--7">0</td>
		      <td class="h50" id="11--2--7">0</td>
		      <td class="h50" id="11--1--8">0</td>
		      <td class="h50" id="11--2--8">0</td>
		      <td class="h50" id="11--1--9">0</td>
		      <td class="h50" id="11--2--9">0</td>
        </tr>
		    <tr>    
		      <td colspan="2" class="h50 th"><a id="1002"></a>我的发展目标<span class="red">*</span></td>
		      <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="12--1--6">0</td>
		      <td class="h50" id="12--2--6">0</td>
		      </c:if>
		      <td class="h50" id="12--1--7">0</td>
		      <td class="h50" id="12--2--7">0</td>
		      <td class="h50" id="12--1--8">0</td>
		      <td class="h50" id="12--2--8">0</td>
		      <td class="h50" id="12--1--9">0</td>
		      <td class="h50" id="12--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">学期结束时的我</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="21--1--6">0</td>
		      <td class="h50" id="21--2--6">0</td>
		      </c:if>
		      <td class="h50" id="21--1--7">0</td>
		      <td class="h50" id="21--2--7">0</td>
		      <td class="h50" id="21--1--8">0</td>
		      <td class="h50" id="21--2--8">0</td>
		      <td class="h50" id="21--1--9">0</td>
		      <td class="h50" id="21--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">班主任评语<span class="red">*</span></td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="22--1--6">0</td>
		      <td class="h50" id="22--2--6">0</td>
		      </c:if>
		      <td class="h50" id="22--1--7">0</td>
		      <td class="h50" id="22--2--7">0</td>
		      <td class="h50" id="22--1--8">0</td>
		      <td class="h50" id="22--2--8">0</td>
		      <td class="h50" id="22--1--9">0</td>
		      <td class="h50" id="22--2--9">0</td>
        </tr>
		    <tr>               
		      <td height="191" colspan="2" class="h50 th">家长期望和寄语<span class="red">*</span></td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="23--1--6">0</td>
		      <td class="h50" id="23--2--6">0</td>
		      </c:if>
		      <td class="h50" id="23--1--7">0</td>
		      <td class="h50" id="23--2--7">0</td>
		      <td class="h50" id="23--1--8">0</td>
		      <td class="h50" id="23--2--8">0</td>
		      <td class="h50" id="23--1--9">0</td>
		      <td class="h50" id="23--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">思想道德</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<a id="1003"></a><span class="red">*</span></td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="31--1--6">0</td>
		      <td class="h50" id="31--2--6">0</td>
		      </c:if>
		      <td class="h50" id="31--1--7">0</td>
		      <td class="h50" id="31--2--7">0</td>
		      <td class="h50" id="31--1--8">0</td>
		      <td class="h50" id="31--2--8">0</td>
		      <td class="h50" id="31--1--9">0</td>
		      <td class="h50" id="31--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="32--1--6">0</td>
		      <td class="h50" id="32--2--6">0</td>
		      </c:if>
		      <td class="h50" id="32--1--7">0</td>
		      <td class="h50" id="32--2--7">0</td>
		      <td class="h50" id="32--1--8">0</td>
		      <td class="h50" id="32--2--8">0</td>
		      <td class="h50" id="32--1--9">0</td>
		      <td class="h50" id="32--2--9">0</td>
        </tr>                
		    <tr>
		      <td class="h50 th">教师</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="33--1--6">0</td>
		      <td class="h50" id="33--2--6">0</td>
		      </c:if>
		      <td class="h50" id="33--1--7">0</td>
		      <td class="h50" id="33--2--7">0</td>
		      <td class="h50" id="33--1--8">0</td>
		      <td class="h50" id="33--2--8">0</td>
		      <td class="h50" id="33--1--9">0</td>
		      <td class="h50" id="33--2--9">0</td>
        </tr>
		    <tr>
		    <td class="h50 th">家长</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="34--1--6">0</td>
		      <td class="h50" id="34--2--6">0</td>
		      </c:if>
		      <td class="h50" id="34--1--7">0</td>
		      <td class="h50" id="34--2--7">0</td>
		      <td class="h50" id="34--1--8">0</td>
		      <td class="h50" id="34--2--8">0</td>
		      <td class="h50" id="34--1--9">0</td>
		      <td class="h50" id="34--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">思想道德记录袋<span class="red"></span></td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="35--1--6">0</td>
		      <td class="h50" id="35--2--6">0</td>
		      </c:if>
		      <td class="h50" id="35--1--7">0</td>
		      <td class="h50" id="35--2--7">0</td>
		      <td class="h50" id="35--1--8">0</td>
		      <td class="h50" id="35--2--8">0</td>
		      <td class="h50" id="35--1--9">0</td>
		      <td class="h50" id="35--2--9">0</td>
        </tr>
		    <tr>                      
		      <td rowspan="5" class="h50 th">学业成就</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<a id="1004"></a><span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="41--1--6">0</td>
		      <td class="h50" id="41--2--6">0</td>
		      </c:if>
		      <td class="h50" id="41--1--7">0</td>
		      <td class="h50" id="41--2--7">0</td>
		      <td class="h50" id="41--1--8">0</td>
		      <td class="h50" id="41--2--8">0</td>
		      <td class="h50" id="41--1--9">0</td>
		      <td class="h50" id="41--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">课程评语<span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="44--1--6">0</td>
		      <td class="h50" id="44--2--6">0</td>
		      </c:if>
		      <td class="h50" id="44--1--7">0</td>
		      <td class="h50" id="44--2--7">0</td>
		      <td class="h50" id="44--1--8">0</td>
		      <td class="h50" id="44--2--8">0</td>
		      <td class="h50" id="44--1--9">0</td>
		      <td class="h50" id="44--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">同学</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="45--1--6">0</td>
		      <td class="h50" id="45--2--6">0</td>
		      </c:if>
		      <td class="h50" id="45--1--7">0</td>
		      <td class="h50" id="45--2--7">0</td>
		      <td class="h50" id="45--1--8">0</td>
		      <td class="h50" id="45--2--8">0</td>
		      <td class="h50" id="45--1--9">0</td>
		      <td class="h50" id="45--2--9">0</td>
        </tr>                                                 
		    <tr>
		      <td class="h50 th">家长</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="46--1--6">0</td>
		      <td class="h50" id="46--2--6">0</td>
		      </c:if>
		      <td class="h50" id="46--1--7">0</td>
		      <td class="h50" id="46--2--7">0</td>
		      <td class="h50" id="46--1--8">0</td>
		      <td class="h50" id="46--2--8">0</td>
		      <td class="h50" id="46--1--9">0</td>
		      <td class="h50" id="46--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">学科作品展示<span class="red"></span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="43--1--6">0</td>
		      <td class="h50" id="43--2--6">0</td>
		      </c:if>
		      <td class="h50" id="43--1--7">0</td>
		      <td class="h50" id="43--2--7">0</td>
		      <td class="h50" id="43--1--8">0</td>
		      <td class="h50" id="43--2--8">0</td>
		      <td class="h50" id="43--1--9">0</td>
		      <td class="h50" id="43--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">合作与交流</td>
		      <td height="191" colspan="2" class="h50 th"><a id="1005"></a>自我评价</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="51--1--6">0</td>
		      <td class="h50" id="51--2--6">0</td>
		      </c:if>
		      <td class="h50" id="51--1--7">0</td>
		      <td class="h50" id="51--2--7">0</td>
		      <td class="h50" id="51--1--8">0</td>
		      <td class="h50" id="51--2--8">0</td>
		      <td class="h50" id="51--1--9">0</td>
		      <td class="h50" id="51--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="52--1--6">0</td>
		      <td class="h50" id="52--2--6">0</td>
		      </c:if>
		      <td class="h50" id="52--1--7">0</td>
		      <td class="h50" id="52--2--7">0</td>
		      <td class="h50" id="52--1--8">0</td>
		      <td class="h50" id="52--2--8">0</td>
		      <td class="h50" id="52--1--9">0</td>
		      <td class="h50" id="52--2--9">0</td>
        </tr>
		    <tr>                                     
		      <td class="h50 th">教师</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="53--1--6">0</td>
		      <td class="h50" id="53--2--6">0</td>
		      </c:if>
		      <td class="h50" id="53--1--7">0</td>
		      <td class="h50" id="53--2--7">0</td>
		      <td class="h50" id="53--1--8">0</td>
		      <td class="h50" id="53--2--8">0</td>
		      <td class="h50" id="53--1--9">0</td>
		      <td class="h50" id="53--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		  <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="54--1--6">0</td>
		      <td class="h50" id="54--2--6">0</td>
		      </c:if>
		      <td class="h50" id="54--1--7">0</td>
		      <td class="h50" id="54--2--7">0</td>
		      <td class="h50" id="54--1--8">0</td>
		      <td class="h50" id="54--2--8">0</td>
		      <td class="h50" id="54--1--9">0</td>
		      <td class="h50" id="54--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">合作与交流行为记录袋<span class="red"></span></td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="55--1--6">0</td>
		      <td class="h50" id="55--2--6">0</td>
		      </c:if>
		      <td class="h50" id="55--1--7">0</td>
		      <td class="h50" id="55--2--7">0</td>
		      <td class="h50" id="55--1--8">0</td>
		      <td class="h50" id="55--2--8">0</td>
		      <td class="h50" id="55--1--9">0</td>
		      <td class="h50" id="55--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">运动与健康</td>
		      <td height="191" colspan="2" class="h50 th"><a id="1006"></a>自我评价</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="61--1--6">0</td>
		      <td class="h50" id="61--2--6">0</td>
		      </c:if>
		      <td class="h50" id="61--1--7">0</td>
		      <td class="h50" id="61--2--7">0</td>
		      <td class="h50" id="61--1--8">0</td>
		      <td class="h50" id="61--2--8">0</td>
		      <td class="h50" id="61--1--9">0</td>
		      <td class="h50" id="61--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="62--1--6">0</td>
		      <td class="h50" id="62--2--6">0</td>
		      </c:if>
		      <td class="h50" id="62--1--7">0</td>
		      <td class="h50" id="62--2--7">0</td>
		      <td class="h50" id="62--1--8">0</td>
		      <td class="h50" id="62--2--8">0</td>
		      <td class="h50" id="62--1--9">0</td>
		      <td class="h50" id="62--2--9">0</td>
        </tr>
		    <tr>                                                   
		      <td class="h50 th">教师</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="63--1--6">0</td>
		      <td class="h50" id="63--2--6">0</td>
		      </c:if>
		      <td class="h50" id="63--1--7">0</td>
		      <td class="h50" id="63--2--7">0</td>
		      <td class="h50" id="63--1--8">0</td>
		      <td class="h50" id="63--2--8">0</td>
		      <td class="h50" id="63--1--9">0</td>
		      <td class="h50" id="63--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="64--1--6">0</td>
		      <td class="h50" id="64--2--6">0</td>
		      </c:if>
		      <td class="h50" id="64--1--7">0</td>
		      <td class="h50" id="64--2--7">0</td>
		      <td class="h50" id="64--1--8">0</td>
		      <td class="h50" id="64--2--8">0</td>
		      <td class="h50" id="64--1--9">0</td>
		      <td class="h50" id="64--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">体质健康<span class="red"></span></td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="65--1--6">0</td>
		      <td class="h50" id="65--2--6">0</td>
		      </c:if>
		      <td class="h50" id="65--1--7">0</td>
		      <td class="h50" id="65--2--7">0</td>
		      <td class="h50" id="65--1--8">0</td>
		      <td class="h50" id="65--2--8">0</td>
		      <td class="h50" id="65--1--9">0</td>
		      <td class="h50" id="65--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">审美与表现</td>
		      <td height="191" colspan="2" class="h50 th"><a id="1007"></a>自我评价</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="71--1--6">0</td>
		      <td class="h50" id="71--2--6">0</td>
		      </c:if>
		      <td class="h50" id="71--1--7">0</td>
		      <td class="h50" id="71--2--7">0</td>
		      <td class="h50" id="71--1--8">0</td>
		      <td class="h50" id="71--2--8">0</td>
		      <td class="h50" id="71--1--9">0</td>
		      <td class="h50" id="71--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="72--1--6">0</td>
		      <td class="h50" id="72--2--6">0</td>
		      </c:if>
		      <td class="h50" id="72--1--7">0</td>
		      <td class="h50" id="72--2--7">0</td>
		      <td class="h50" id="72--1--8">0</td>
		      <td class="h50" id="72--2--8">0</td>
		      <td class="h50" id="72--1--9">0</td>
		      <td class="h50" id="72--2--9">0</td>
        </tr>                                                  
		    <tr>
		      <td class="h50 th">教师</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="73--1--6">0</td>
		      <td class="h50" id="73--2--6">0</td>
		      </c:if>
		      <td class="h50" id="73--1--7">0</td>
		      <td class="h50" id="73--2--7">0</td>
		      <td class="h50" id="73--1--8">0</td>
		      <td class="h50" id="73--2--8">0</td>
		      <td class="h50" id="73--1--9">0</td>
		      <td class="h50" id="73--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="74--1--6">0</td>
		      <td class="h50" id="74--2--6">0</td>
		      </c:if>
		      <td class="h50" id="74--1--7">0</td>
		      <td class="h50" id="74--2--7">0</td>
		      <td class="h50" id="74--1--8">0</td>
		      <td class="h50" id="74--2--8">0</td>
		      <td class="h50" id="74--1--9">0</td>
		      <td class="h50" id="74--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">审美与表现记录袋<span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="75--1--6">0</td>
		      <td class="h50" id="75--2--6">0</td>
		      </c:if>
		      <td class="h50" id="75--1--7">0</td>
		      <td class="h50" id="75--2--7">0</td>
		      <td class="h50" id="75--1--8">0</td>
		      <td class="h50" id="75--2--8">0</td>
		      <td class="h50" id="75--1--9">0</td>
		      <td class="h50" id="75--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">综合实践活动</td>
		      <td rowspan="3" class="h50 th">研究性学习</td>
		      <td height="191" class="h50 th">基本情况<a id="1008"></a><span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="81--1--6">0</td>
		      <td class="h50" id="81--2--6">0</td>
		      </c:if>
		      <td class="h50" id="81--1--7">0</td>
		      <td class="h50" id="81--2--7">0</td>
		      <td class="h50" id="81--1--8">0</td>
		      <td class="h50" id="81--2--8">0</td>
		      <td class="h50" id="81--1--9">0</td>
		      <td class="h50" id="81--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">研究成果</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="82--1--6">0</td>
		      <td class="h50" id="82--2--6">0</td>
		      </c:if>
		      <td class="h50" id="82--1--7">0</td>
		      <td class="h50" id="82--2--7">0</td>
		      <td class="h50" id="82--1--8">0</td>
		      <td class="h50" id="82--2--8">0</td>
		      <td class="h50" id="82--1--9">0</td>
		      <td class="h50" id="82--2--9">0</td>
        </tr>
		    <tr>                                                    
		      <td class="h50 th">自我评价</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="83--1--6">0</td>
		      <td class="h50" id="83--2--6">0</td>
		      </c:if>
		      <td class="h50" id="83--1--7">0</td>
		      <td class="h50" id="83--2--7">0</td>
		      <td class="h50" id="83--1--8">0</td>
		      <td class="h50" id="83--2--8">0</td>
		      <td class="h50" id="83--1--9">0</td>
		      <td class="h50" id="83--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" rowspan="2" class="h50 th">社区服务与社会实践<span class="red"></span></td>
		      <td height="191" class="h50 th">基本情况</td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="84--1--6">0</td>
		      <td class="h50" id="84--2--6">0</td>
		      </c:if>
		      <td class="h50" id="84--1--7">0</td>
		      <td class="h50" id="84--2--7">0</td>
		      <td class="h50" id="84--1--8">0</td>
		      <td class="h50" id="84--2--8">0</td>
		      <td class="h50" id="84--1--9">0</td>
		      <td class="h50" id="84--2--9">0</td>
        </tr>
		    <tr>
		      <td height="95" class="h50 th">自我评价</td>
		     <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="85--1--6">0</td>
		      <td class="h50" id="85--2--6">0</td>
		      </c:if>
		      <td class="h50" id="85--1--7">0</td>
		      <td class="h50" id="85--2--7">0</td>
		      <td class="h50" id="85--1--8">0</td>
		      <td class="h50" id="85--2--8">0</td>
		      <td class="h50" id="85--1--9">0</td>
		      <td class="h50" id="85--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">个性发展</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<a id="1009"></a><span class="red">*</span></td>
		  <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="91--1--6">0</td>
		      <td class="h50" id="91--2--6">0</td>
		      </c:if>
		      <td class="h50" id="91--1--7">0</td>
		      <td class="h50" id="91--2--7">0</td>
		      <td class="h50" id="91--1--8">0</td>
		      <td class="h50" id="91--2--8">0</td>
		      <td class="h50" id="91--1--9">0</td>
		      <td class="h50" id="91--2--9">0</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		 <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="92--1--6">0</td>
		      <td class="h50" id="92--2--6">0</td>
		      </c:if>
		      <td class="h50" id="92--1--7">0</td>
		      <td class="h50" id="92--2--7">0</td>
		      <td class="h50" id="92--1--8">0</td>
		      <td class="h50" id="92--2--8">0</td>
		      <td class="h50" id="92--1--9">0</td>
		      <td class="h50" id="92--2--9">0</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="93--1--6">0</td>
		      <td class="h50" id="93--2--6">0</td>
		      </c:if>
		      <td class="h50" id="93--1--7">0</td>
		      <td class="h50" id="93--2--7">0</td>
		      <td class="h50" id="93--1--8">0</td>
		      <td class="h50" id="93--2--8">0</td>
		      <td class="h50" id="93--1--9">0</td>
		      <td class="h50" id="93--2--9">0</td>
        </tr>
		    <tr>                                                     <%-- ${fn:length()} --%>
		      <td class="h50 th">家长</td>
		    <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="94--1--6">0</td>
		      <td class="h50" id="94--2--6">0</td>
		      </c:if>
		      <td class="h50" id="94--1--7">0</td>
		      <td class="h50" id="94--2--7">0</td>
		      <td class="h50" id="94--1--8">0</td>
		      <td class="h50" id="94--2--8">0</td>
		      <td class="h50" id="94--1--9">0</td>
		      <td class="h50" id="94--2--9">0</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">特长与成果展示<span class="red">*</span></td>
		   <c:if test="${gradelength eq '4'}">
		      <td class="h50" id="95--1--6">0</td>
		      <td class="h50" id="95--2--6">0</td>
		      </c:if>
		      <td class="h50" id="95--1--7">0</td>
		      <td class="h50" id="95--2--7">0</td>
		      <td class="h50" id="95--1--8">0</td>
		      <td class="h50" id="95--2--8">0</td>
		      <td class="h50" id="95--1--9">0</td>
		      <td class="h50" id="95--2--9">0</td>
        </tr>
      </table>
    </div>
  

</div>
</body>
</html>