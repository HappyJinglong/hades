<%@ include file="/common/taglibs.jsp"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type='text/javascript' src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/jquery/jquery-1.4.4.min.js'></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>
<title>学生评价统计明细表</title>
</head>
<script type="text/javascript">
function changGrade(gradeid){
	ShowUserFuncDwr.queryClassId(gradeid,function(data){
			DWRUtil.removeAllOptions('classid');
		if(null != data && data.length > 0){
			DWRUtil.addOptions('classid',data,'classId','className');
		}
		var classid = document.getElementById("classid").value;
		changeClass(classid);	
	});
}
function changeClass(classid){
	ShowUserFuncDwr.queryTermDto(classid,function(data){
			DWRUtil.removeAllOptions('termid');
		if(null != data && data.length > 0){
			DWRUtil.addOptions('termid',data,'termid','termname');
		}
	});
}

function exportStuDetail()
{
	var gradeId = $("#gradeid option:selected").val();
	var classid = $("#classid option:selected").val();
	var termid = $("#termid option:selected").val();
	var gradeName = $("#gradeid option:selected").text();
	var className = $("#classid option:selected").text();
	var termName = $("#termid option:selected").text();
	dStatus = uuid();
	window.location.href = "${ctx}/export/SeniorStuApprasialCount.a?gradeId="
				+ gradeId
				+ "&dStatus=" 
				+ dStatus
				+ "&classid="
				+ classid
				+ "&termid="
				+ termid
				+ "&gradeName="
				+ gradeName
				+ "&className="
				+ className
				+ "&termName="
				+ termName;
	 ShowDiv(); 
	 timeId = setInterval(queryDownStatus,1000);
	}
	function getData() {
		ShowDiv();
		$("#form").submit();
	}
	 var dStatus = "";
	 var timeId = "";
	 var count = 0;
	 function queryDownStatus(){
		 $("#MyDiv").css("height","120%");
		 $("#timeCount").html("已耗时间"+(++count)+"秒");
	 	 $.ajax({
		     url: "${ctx}/export/shoolFillCount.a?queryDownLoadStatus",
		     type: "POST",
		     data: {
		    	 dStatus:dStatus
		     },
		     success: function(data) {
		    	if(data.val=="1"){
		    		$("#MyDiv").css("display","none");
		    		clearInterval(timeId);
		    		count=0;
		    		 $("#timeCount").html("正在导出数据...");
		    	}else if(data.val=="2"){//导出文件失败
		    		$("#MyDiv").css("display","none");
		    		clearInterval(timeId);
		    		count=0;
		    		 $("#timeCount").html("正在导出数据...");
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
<body>
<div class="dangqianwz" style="z-index:1001;padding-top: 10px;position: fixed;">
<form id="form" action="${ctx}/xspjtjmxb/StudentAppDetailAction.a?queryStatistData" method="post">
 	<span class="fl">当前位置：学生评价统计明细表</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
             <span >届别：
     <select name="gradeid" id='gradeid' class=" wenziliebiao100" onchange="changGrade(this.value)">
               <c:forEach var="gradYear" items="${gradYears}">
	               	<option value="${fn:split(gradYear, '_')[0]}" <c:if test="${fn:split(gradYear, '_')[0] eq gradeid}">selected='selected'</c:if>>${fn:split(gradYear,'_')[1]}</option>
	           </c:forEach>
            </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <span >班级：
     <select name="classid" id='classid' class="wenziliebiao100" onchange="changeClass(this.value)">
     		 <c:forEach var="class" items="${classs}">
	               	<option value="${fn:split(class, '_')[0]}" <c:if test="${fn:split(class, '_')[0] eq classid}">selected='selected'</c:if>>${fn:split(class,'_')[2]}</option>
	          </c:forEach>	
     </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
     <select name="termid" id='termid' class="  wenziliebiao100">
              <c:forEach var="term" items="${terms}">
	               	<option value="${term.termid}" <c:if test="${term.termid eq termid}">selected='selected'</c:if>>${term.termname}</option>
	          </c:forEach>
     </select> </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >
            	<input type="button" value="查 询" class="button ml10" onclick="getData();"/>
            </span>&nbsp;&nbsp;&nbsp;
            <span ><input type="button" value="导 出" class="button ml10" onclick="exportStuDetail();"/></span>
</div>
</form>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="60">
      <c:if test="${not empty gradeName}">
    	<div class="tishi_left fl">${gradeName}届${className}学生数：${studentsNum}人
        </div>
    </c:if>
    <c:if test="${empty gradeName }">
   	 	<div class="tishi_left fl">****届****班学生数：****人
        </div>
    </c:if>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="60" rowspan="3">届别</td>
     <td width="60" rowspan="3">班级</td>
     <td width="80" rowspan="3">姓名</td>
     <td colspan="3">新学期伊始的我</td>
     <td colspan="4">学期结束的我</td>
     <td colspan="5">思想道德</td>
     <td colspan="6">学业成就</td>
     <td colspan="5">合作与交流</td>
     <td colspan="5">运动与健康</td>
     <td colspan="5">审美与表现</td>
     <td colspan="3">综合实践活动</td>
     <td colspan="7">个性发展</td>
     </tr>
   <tr class="biaotou_zi">
     <td width="40" rowspan="2">自我评价</td>
     <td width="40" rowspan="2">我的发展目标</td>
     <td width="40" rowspan="2">家长的期望</td>
     <td width="40" rowspan="2">自我评价</td>
     <td width="40" rowspan="2">我的发展目标</td>
     <td width="40" rowspan="2">班主任评语</td>
     <td width="40" rowspan="2">家长评语和期望</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">思想道德记录袋</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">学科作品展示</td>
     <td width="40" rowspan="2">课程评语</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">合作与交流行为记录袋</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">体质健康</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">审美与表现记录袋</td>
     <td>研究性学习</td>
     <td>社区服务</td>
     <td>社会实践活动</td>
     <td width="40" rowspan="2">基本情况</td>
     <td width="40" rowspan="2">自我评价</td>
     <td colspan="3">他人评价</td>
     <td width="40" rowspan="2">特长与成果展示</td>
      <td width="40" rowspan="2">个性发展过程</td>
     </tr>
  <tr class="biaotou_zi">
    <td width="40">同学</td>
    <td width="40">教师</td>
    <td width="40">家长</td>
    <td width="40">同学</td>
    <td width="40">教师</td>
    <td width="40">家长</td>
    <td width="40">同学</td>
    <td width="40">教师</td>
    <td width="40">家长</td>
    <td width="40">同学</td>
    <td  width="40">教师</td>
    <td width="40">家长</td>
    <td width="40">同学</td>
    <td width="40">教师</td>
    <td width="40">家长</td>
    <td width="40">学生本人</td>
    <td width="40">学生本人</td>
    <td width="40">学生本人</td>
    <td width="40">同学</td>
    <td width="40">教师</td>
    <td width="40">家长</td>
  	</tr>
<c:forEach items="${data}" var="obj">
  <tr>
    <td>${obj.gradeYear}</td>
    <td>${obj.className}</td>
    <td>${obj.studentName}</td>
   <td>${obj.startEcoleNum}</td>
   <td>${obj.startMyDlpmentTargetNum}</td>
   <td>${obj.startparentsExpectNum}</td>
   <td>${obj.endMyEcoleNum}</td>
   <td>${obj.endMyDlpmentTargetNum}</td>
   <td>${obj.endHeadMasterAppraiseNum}</td>
   <td>${obj.endParentsExpectAndAppNum}</td>
   <td>${obj.sxddMySelfAppraiseNum}</td>
   <td>${obj.sxddOtherAppraiseClassMateNum}</td>
   <td>${obj.sxddOtherAppraiseTeacherNum}</td>
   <td>${obj.sxddOtherAppraiseParentsNum}</td>
   <td>${obj.sxddRecodeBagNum}</td>
   <td>${obj.xycjMySelfAppraiseNum}</td>
   <td>${obj.xycjOtherAppraiseClassMateNum}</td>
   <td>${obj.xycjOtherAppraiseTeacherNum}</td>
   <td>${obj.xycjOtherAppraiseParentsNum}</td>
   <td>${obj.xycjSubJectWorkNum}</td>
   <td>${obj.xycjSubJectAppraiseNum}</td>
   <td>${obj.xzyjlMySelfAppraiseNum}</td>
   <td>${obj.xzyjlOtherAppraiseClassMateNum}</td>
   <td>${obj.xzyjlOtherAppraiseTeacherNum}</td>
   <td>${obj.xzyjlOtherAppraiseParentsNum}</td>
   <td>${obj.xzyjlRecodeBagNum}</td>
   <td>${obj.ydhjkMySelfAppraiseNum}</td>
   <td>${obj.ydhjkOtherAppraiseClassMateNum}</td>
   <td>${obj.ydhjkOtherAppraiseTeacherNum}</td>
   <td>${obj.ydhjkOtherAppraiseParentsNum}</td>
   <td>${obj.ydhjkPhysicalHealthNum}</td>
   <td>${obj.xmybxMySelfAppraiseNum}</td>
   <td>${obj.xmybxOtherAppraiseClassMateNum}</td>
   <td>${obj.xmybxOtherAppraiseTeacherNum}</td>
   <td>${obj.xmybxOtherAppraiseParentsNum}</td>
   <td>${obj.xmybxRecordBagNum}</td>
   <td>${obj.zhsjhdYjxxxMySelfNum}</td>
   <td>${obj.zhsjhdSqfwMySelfNum}</td>
   <td>${obj.zhsjhdShsjhdMySelfNum}</td>
   <td>${obj.gxfzMyBaseInfoNum}</td>
   <td>${obj.gxfzMySelfAppraiseNum}</td>
   <td>${obj.gxfzOtherAppraiseClassMateNum}</td>
   <td>${obj.gxfzOtherAppraiseTeacherNum}</td>
   <td>${obj.gxfzOtherAppraiseParentsNum}</td>
   <td>${obj.gxfzSpecialtyAndAchievementsNum}</td>
   <td>${obj.gxfzSpecialtyDelepNum}</td>
   </tr>
</c:forEach>
</table>
</div>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
