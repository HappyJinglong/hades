<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css_new/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>

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
</style>
<title>统计表</title>
</head>
<script type="text/javascript">
var $$= jQuery.noConflict();
function submitdata(){
	ShowDiv();
	var sel=document.getElementById('termId');//select元素的id 
	var index=sel.selectedIndex;//获取被选中的option的索引 
	var textsel= sel.options[index].text;//获取相应的option的内容 
	$$("#termName").val(textsel);
}
function changGrade(gradeyear){
	ShowUserFuncDwr.queryTermDtoByGradeYear(gradeyear,function(data){
			DWRUtil.removeAllOptions('termId');
		if(null != data && data.length > 0){
			DWRUtil.addOptions('termId',data,'termid','termname');
		}
	});
}
function exportExcel()
{
    var termname = $$("#termId option:selected").text();
	var discodename = $$("#discode option:selected").text();
	var gradename = $$("#gradeid option:selected").text();
	var gradesyear =$$("#gradeid option:selected").val();
	var discode = $$("#discode option:selected").val();
	var termid = $$("#termId option:selected").val();
	var schoolName = $$("#schoolName").val();
    if(discode==undefined)
    {
    	discode = "";
    }
    dStatus = uuid(); 
	window.location.href = "${ctx}/export/CourseCountExport.a?termname="
				+ termname + "&dStatus=" + dStatus + "&discodename=" + discodename + "&gradename="
				+ gradename + "&gradesyear=" + gradesyear + "&discode="
				+ discode + "&termid=" + termid+"&schoolName="+schoolName; 
	ShowDiv(); 
	timeId = setInterval(queryDownStatus,1000);
	}
var dStatus = "";
var timeId = "";
var count = 0;
function queryDownStatus(){
	 $$("#MyDiv").css("height","120%");
	 $$("#timeCount").html("已耗时间"+(++count)+"秒");
	 $$.ajax({
	     url: "${ctx}/export/shoolFillCount.a?queryDownLoadStatus",
	     type: "POST",
	     data: {
	    	 dStatus:dStatus
	     },
	     success: function(data) {
	    	if(data.val=="1"){
	    		$$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		$$("#timeCount").html("正在导出数据...");
	    	}else if(data.val=="2"){//导出文件失败
	    		$$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		$$("#timeCount").html("正在导出数据...");
	    		fail_notice_word("导出数据失败！");
	    	}
	     }
	 });
}
function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";
    var uuid = s.join("");
    return uuid;
}

</script>
<body style="overflow-x:auto;">
<div class="dangqianwz" style="margin-top:10px;z-index:1001;position: fixed;">
	<form id="form" action="${ctx }/learnprocessStatics/LearnprocessStaticsAction.a" method="post">
		<input name="isQuery" value="1" type="hidden"/>
		<input type="hidden" name='termName' id='termName' value="${termName}"/>
	 	<span class="fl">当前位置：课程评语填写情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  <c:if test="${!empty queryQXInfos}">
	 	  <span >区县：
			   <select id="discode" name="discode" class="  wenziliebiao100">
			   			<c:if test="${empty discode}">
							<c:forEach items="${queryQXInfos}" var="info" varStatus="vs">
								<option value="${fn:split(info,'@')[0]}" 
									<c:if test="${vs.index==0}">selected="selected"</c:if>
								>${fn:split(info,'@')[1]}</option>
			   				</c:forEach>
			   			</c:if>
						<c:if test="${!empty discode}">
							<c:forEach items="${queryQXInfos}" var="info">
								<option value="${fn:split(info,'@')[0]}"
								<c:if test="${fn:split(info,'@')[0] eq discode}">selected="selected"</c:if>
								>${fn:split(info,'@')[1]}</option>
			   				</c:forEach>
			   			</c:if>
			   </select></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  </c:if>
	 	  <span >学校：
	      <input id="schoolName" name="schoolName" type="text" class="wenben120" value="${schoolName}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <span >届别：
	     		<select id="gradeid" name="gradeid" class="wenziliebiao100" onchange="changGrade(this.value);">
	               	<app:gradeYear gradeYear="${gradeid}"/>
	            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <span >学期：
	     		<select  name="termId" id="termId" class="wenziliebiao100">
					<app:newTermId termId="${termId}"/>
				</select>
	     		</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <span ><input type="submit" value="查 询" class="button ml10" onclick="submitdata();"/></span>&nbsp;&nbsp;&nbsp;
	            <span ><input type="button" value="导 出" class="button ml10" onclick="exportExcel();"/></span>
      </form>
</div>
<div id="tjb_main" style="overflow: visible;">
  <div class="top" style="width:250%;">
  <table  border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
   <c:choose>
   	<c:when test="${userDto.levelcode eq '2012002'}">
   		<td colspan="69">
   	</c:when>
   	<c:otherwise>
   		<td colspan="73">
   	</c:otherwise>
   </c:choose>
          <c:if test="${empty gradeid}">
      <div class="tishi_left fl">***届学生***课程评语填写情况
        </div>
     </c:if>
     <c:if test="${not empty gradeid}">
      <div style="height:60px;overflow:hidden;float:left;">${gradeid}届学生${termName}必填数据填写情况
        </div>
     </c:if>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="3%" rowspan="2">学校</td>
     <c:forEach items="${subjectDtos }" var="sub">
     	<td colspan="4" width="4%">${sub.subjectName }</td>
     </c:forEach>
    </tr>
  <tr class="biaotou_zi">
	  <c:forEach items="${subjectDtos }" var="sub">
	    <td width="1%">学生总人数</td>
	    <td width="1%">已填写人数</td>
	    <td width="1%">完成百分比（%）</td>
	    <td width="1%">已评价条目数</td>
	  </c:forEach>
  </tr>
 <!--  </table>
  </div>
  <div style="width:250%;height:400px; overflow-x: hidden">
  <table  border="0" cellspacing="1" bgcolor="#999999" class="biaoge"> -->
  <c:forEach items="${learnprocessStaticsInfo}" var="schoolInfo">
	  <tr>
		    <td class="biaoge" width="3%">${schoolInfo.key }</td>
		    <c:forEach items="${subjectDtos }" var="sub">
			   <c:choose>
				   	<c:when test="${!empty schoolInfo.value[sub.subjectid]}">
				   		<td width="1%">${fn:split(schoolInfo.value[sub.subjectid],'@')[0]}</td>
					    <td width="1%">${fn:split(schoolInfo.value[sub.subjectid],'@')[1]}</td>
					    <td width="1%">${fn:split(schoolInfo.value[sub.subjectid],'@')[2]}</td>
					    <td width="1%">${fn:split(schoolInfo.value[sub.subjectid],'@')[3]}</td>
				   	</c:when>
				    <c:otherwise>
				   		<td width="1%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
				    </c:otherwise>
		       </c:choose>
			</c:forEach>	  
	  </tr>
  </c:forEach>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
