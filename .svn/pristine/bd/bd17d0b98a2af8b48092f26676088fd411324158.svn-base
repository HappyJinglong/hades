<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>Insert title here</title>

<style type="text/css"> 
body,html{ width:100%; height:100%;}
.span1 {
  	float: left; 
    height: 44px;
    text-align: left;
    width: 400px;
}
.span2 {
  	float: right; 
    height: 44px;
    text-align: right;
    width: 800px;
}
.xue_zuti {
    /*background: #34495e;*/
    color: #fff;
 	float: left;
    height: 43px;
    padding: 0;
   	width:100%;
} 
.xue_zuti .span1 {
   color: #37840c;
   float: left;  
   font-size: 14px;
   font-weight: bold;
   height: 43px;
   line-height: 43px;
   margin: 0;
   padding: 0;
   text-align: center;
   width: 180px;
   background:url("${ctx}/images/xue_pic_02.gif") repeat scroll 0 0;
}
.xue_zuti .span2 {
    background:url("${ctx}/images/xue_pic_01.gif") repeat scroll 0 0;
    color: #0014ff;
    float: left;
    font-size: 14px;
    height: 43px;
    line-height: 43px;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 180px;
}
.xue_zuti .span3 {
    background:url("${ctx}/images/xue_pic_01.gif") repeat scroll 0 0;
    color: #0014ff;
    float: left;
    font-size: 14px;
    height: 43px;
    line-height: 43px;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 180px;
}
.lanmu2 {
   color: #0a3896; 
    text-decoration: none;
}
</style>
<script type="text/javascript">
function forward(){
	 document.getElementById("student").click();
}
function addTest(){
	try{
		self.parent.frames[1].gotoPage();
		return true;
	}catch(e){
		return false;
	}
}
 	function showTeacherMenu(){
 		self.parent.frames["hit_a"].ShowDiv();
		$("#span2").removeClass().addClass("span2");
		$("#span1").removeClass().addClass("span1") ;
		/* $("#teacher").attr("href","zonghe.html"); */
	}
	
	function showStudentMenu(){
		self.parent.frames["hit_a"].ShowDiv();
		$("#span2").removeClass().addClass("span1");
		$("#span1").removeClass().addClass("span2") ;
		/* $("#student").attr("href","yanjiu.html"); */
	}
	function showStudent1Menu(){
		self.parent.frames["hit_a"].ShowDiv();
		$("#span2").removeClass().addClass("span2");
		$("#span1").removeClass().addClass("span2");
	/* 	$("#student1").attr("href","huodong.html"); */
	}
	function clearTimeId(){
		if(addTest()){
			clearInterval(timeId);
			timeId = null;
		}
	}
	var timeId = setInterval("clearTimeId()",5);
</script>
</head>
<body>
 <div class="xue_zuti">
 	<span  id="span2"  class="span1"><a id="student" id="first" href="${ctx}/partinfo/PartinfoAction.a?choicenum=1008&evaluateType=81" class="lanmu2" target="hit_a" onclick="showStudentMenu()">研究性学习</a></span>
 	<span  id="span1" class="span2"><a id="teacher" href="${ctx}/partinfo/PartinfoAction.a?choicenum=1008&evaluateType=85" class="lanmu2" target="hit_a" onclick="showTeacherMenu()" >社区服务与社会实践活动</a></span>	
 </div>
</body>
</html>