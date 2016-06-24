<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>必填项</title>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
// function chooseTerm(){
// 	var zsTermId = $("#termId option:selected").val();
// 	ShowDiv();
// 	window.location.href="${ctx}/middlemaster/StudentApprisedAction.a?showAppriseCount&ids="+'${ids}'+"&idName="+'${idName}'+"&zsTermId="+zsTermId+"&isHistory=1";
// }
function query(){
	 $.ajax({
			url:'${ctx}/needs/SeniorAction.a?seniorQuery',
			method:'POST',
			success:function(msg){
				var value=eval(msg);
				var flag = value.val;
				if(flag == "1"){
					window.location.href = '${ctx}/needs/SeniorAction.a?_r=' + new Date().getTime();
				}
			}
	 });
}
</script>
<style type="text/css">
	#tjb_main{
		top:37px;
		padding:13px;
		margin-bottom:23px;
		position: absolute;
		width:97.8%;
	}
body,html{
    overflow-x:hidden;
	overflow-y:hidden; 
}
</style>
</head>
<body id="tjb_main_flag">
<div class="dangqianwz">
 	<span class="fl">当前位置：必填数据填写情况 </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         
 	栏目：
 	<select name="select" id="termId" class="wenziliebiao100" onchange="window.location.href='#' + this.value;">
 	<!-- <select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()"> -->
 		<option value="all">全部</option>
 		<c:forEach items="${at}" var="map"  varStatus="count">
	  		<option value="${map.appraisaltypeid }">${map.appraisaltype }</option>
	  	</c:forEach>
    	  	<%-- <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/> --%>
    </select>
   	<span >
   	<input type="submit" value="查 询" class="button ml10" onclick="query()" />
   	</span>       
    <span ><input type="submit" value="导 出" class="button ml10" /></span>
</div>
<div id="tjb_main" >
<div class="top" >
<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="22">
      <div class="tishi_left fl">${termName }
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="100" rowspan="3" id="all">栏目</td>
     <td width="40" rowspan="3">二级栏目</td>
     <td width="40" rowspan="3">填写人</td>
     <td width="40" rowspan="3">完成情况</td>
     <td colspan="6">${dqjb1 }届</td>
     <td colspan="6">${dqjb2 }届</td>
     <td colspan="6">${dqjb3 }届</td>
     </tr>
   <tr class="biaotou_zi">
     <td colspan="2">高一年级</td>
     <td colspan="2">高二年级</td>
     <td colspan="2">高三年级</td>
     <td colspan="2">高一年级</td>
     <td colspan="2">高二年级</td>
     <td colspan="2">高三年级</td>
     <td colspan="2">高一年级</td>
     <td colspan="2">高二年级</td>
     <td colspan="2">高三年级</td>
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
    <td rowspan="6" id="10">新学期伊始的我</td>
    
    <td rowspan="3">刚开学时的我</td>
    <td rowspan="3">学生本人</td>
   <td>学生总人数</td>
   <c:forEach items="${ntStrList}" var="list">
   		<td>${list}</td>
   </c:forEach>
   </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${cpSum}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
  <tr>
    <td>完成百分比（%）</td>
<!--     leve -->
    <c:forEach items="${leve}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
  <tr>
    <td rowspan="3">我的发展目标</td>
    <td rowspan="3">学生本人</td>
    <td>学生总人数</td>
	<c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	</c:forEach>
    </tr>
  <tr>
    <td>已完成学生数</td>
    <c:forEach items="${cpSum2}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${leve2}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
 <tr>
    <td rowspan="9" id="20">学期结束时的我</td>
    <td rowspan="3">学期末的我</td>
    <td rowspan="3">学生本人</td>
    <td>学生总人数</td>
	<c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	</c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${cpSum3}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${leve3}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
    <tr>
    <td rowspan="3">班主任评语</td>
    <td rowspan="3">班主任</td>
    <td>学生总人数</td>
	<c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	</c:forEach>
    </tr>
    <tr>
    <td>已完成学生数</td>
    <c:forEach items="${cpSum4}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
    <tr>
    <td>完成百分比（%）</td>
    <c:forEach items="${leve4}" var="list">
   		<td>${list}</td>
    </c:forEach>
    </tr>
    <tr>
      <td rowspan="3">家长评语和期望</td>
      <td rowspan="3">家长</td>
      <td>学生总人数</td>
	<c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	</c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum5}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve5}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3" id="30">思想道德</td>
      <td rowspan="3">自我评价</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum6}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve6}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3" id="80">学业成就</td>
      <td rowspan="3">自我评价</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum7}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve7}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3" id="40">合作与交流</td>
      <td rowspan="3">他人评价</td>
      <td rowspan="3">同学</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum8}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve8}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3" id="50">运动与健康</td>
      <td rowspan="3">体质健康</td>
      <td rowspan="3">云平台提取</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td> </td>
    </tr>
    <tr>
      <td rowspan="3" id="60">审美与表现</td>
      <td rowspan="3">记录袋</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum10}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve10}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3" id="90">综合实践活动</td>
      <td rowspan="3">研究性学习基本情况</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum11}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve11}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="6" id="70">个性与发展</td>
      <td rowspan="3">自我评价</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum12}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve12}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td rowspan="3">特长与成果展示</td>
      <td rowspan="3">学生本人</td>
      <td>学生总人数</td>
	  <c:forEach items="${ntStrList}" var="list">
		<td>${list}</td>
	  </c:forEach>
    </tr>
    <tr>
      <td>已完成学生数</td>
      <c:forEach items="${cpSum13}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>
    <tr>
      <td>完成百分比（%）</td>
      <c:forEach items="${leve13}" var="list">
   		<td>${list}</td>
      </c:forEach>
    </tr>	

   
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>