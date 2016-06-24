<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<un:bind var="TERM_TYPE" type="com.flyrish.hades.common.Constant"
	field="DIC_TERM_TYPE" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script src="${ctx }/js/jquery-1.4.4.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我评价</title>
</head>
<style type="text/css">
body,html{ width:100%; height:100%;}
.menu li{

}
#pj_menu.menu_zw li a{
  margin-left: 30px;
}
#pj_menu.xxkbox_zw.imgs{
  margin-left:25px;
}

 img{vertical-align:middle}
</style>

<script type="text/javascript">
function forward(){
	var commonFuncId =<%=request.getAttribute("commonFuncId")%>;
	 var id;
	if(commonFuncId=='null'||commonFuncId==''||commonFuncId=='1010'){
		id="first";
		$("#"+id).attr("href","${ctx}/selfappraise/Jump.a");
	}else{
	 	id="first"+commonFuncId;
	}
	document.getElementById(id).click();
}
function addTest(){
	try{
		self.parent.frames[2].gotoPage();
		return true;
	}catch(e){
		return false;
	}
}
	window.onload = function() {
		var current = document.getElementsByTagName('li')[0];
		document.body.onclick = function(e) {
			/* self.parent.frames[2].ShowDiv(); */
			var a = e || window.event;
			var src = a.srcElement || a.target;
			src = src.parentNode;
			if (src.tagName.toLowerCase() == 'li') {
				if (current) {
					current.className = '';
				}
				current = src;
				src.className = 'cur';
			}
		}
	}
	
	function onChange(obj){
		 var a = $("#pj_menu").find("a");
		 for(var i=0;i<a.size();i++){
			 if($(a[i]).parent().attr("class")=="cur"){
				$(a[i]).parent().removeClass();
				break;
			 }
		 }
		 obj.parent().addClass("cur") ;
		 try{
			 self.parent.frames[2].ShowDiv && self.parent.frames[2].ShowDiv();
		 }catch (e){
			 self.parent.frames[2].frames[1].ShowDiv();
		 }
	}
	function onChange1(obj){
		 var a = $("#pj_menu").find("a");
		 for(var i=0;i<a.size();i++){
			 if($(a[i]).parent().attr("class")=="cur"){
				$(a[i]).parent().removeClass();
				break;
			 }
		 }
		 obj.parent().addClass("cur") ;
	}
	function clearTimeId(){
		if(addTest()){
			clearInterval(timeId);
			timeId = null;
		}
	}
	var timeId = setInterval("clearTimeId()",5);
</script>
<body>
<div id="pj_menu" style="overflow:auto;width: 250px;overflow-x:hidden; height: 100%;">
    <div class="xxkbox_zw">
		<div class="fl pic">
		<c:forEach items="${lstPhoto}" var="list" varStatus="status">
		<img class="imgs" src="${list.photoUrl}" />
		</c:forEach>
		</div>
		<span class="fl ml10" title="${evaluatePersonName}">
			<c:choose>
				<c:when test="${fn:length(evaluatePersonName) > 4}">
					<c:out
						value="${fn:substring(evaluatePersonName, 0, 4)}..." />
				</c:when>
				<c:otherwise>
					 <!--<c:out value="${book.bookAuthors} " />-->
					${evaluatePersonName}
				</c:otherwise>
			</c:choose>	
		</span>
    </div>
	<ul class="menu menu_zw">
    	<li class="cur"><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1001" <c:if test="${ empty commonFuncId or commonFuncId eq '1010'}">id="first"</c:if> <c:if test="${not empty commonFuncId and commonFuncId ne '1010'}">id="first1001"</c:if> target="mainFrame" onclick="onChange($(this))" class="yangshi"><img src="${ctx}/images/xieks.png">新学期伊始的我&nbsp;<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1002" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1002"</c:if> target="mainFrame"  onclick="onChange($(this))" class="nobdt"><img src="${ctx}/images/xiejs.png">学期结束时的我&nbsp;<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1003" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1003"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xiesx.png">思想道德&nbsp;<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1004" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1004"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xiecj.png">学业成就&nbsp;<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1005" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1005"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xiehz.png">合作与交流</a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1006" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1006"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xieyd.png">运动与健康</a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1007" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1007"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xiesm.png">审美与表现&nbsp;<span class="red">*</span></a></li>	
        <li><a href="${ctx}/selfappraise/frame_zongheshijiancz.html" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1008"</c:if> target="mainFrame" onclick="onChange1($(this))" ><img src="${ctx}/images/xiesh.png" >综合实践活动&nbsp;<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/partinfo/PartinfoAction.a?choicenum=1009" <c:if test="${ empty commonFuncId}">id="first"</c:if> <c:if test="${not empty commonFuncId}">id="first1009"</c:if> target="mainFrame"  onclick="onChange($(this))" ><img src="${ctx}/images/xiegx.png">个性发展&nbsp;<span class="red">*</span></a></li>	
    	<li class="bdtbox"><a href="#" class="bdt"></a></li>
    </ul>
</div>
</body>
</html>