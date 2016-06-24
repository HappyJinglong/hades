<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<%-- <link href="${ctx }/css/cmis4_global.css" rel="stylesheet" type="text/css" /> --%>

<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script src="${ctx }/js/jquery-1.4.4.min.js"></script>
<script src="${ctx }/js/jquery.ztree.core-3.5.min.js"></script>

<!-- 从云平台借鉴 -->


<script src="${ctx }/js/tablebg.js" type="text/javascript"></script> 
<link href="${ctx }/css/szhxy.css" rel="stylesheet" type="text/css">
<link rel="StyleSheet" href="${ctx }/css/dtree.css" type="text/css" />
<script type="text/javascript" src="${ctx }/js/dtree.js"></script>

  <!-- 树形显示 -->
<link href="${ctx }/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx }/ext-2.1/resources/css/xtheme-gray.css" />
<script type="text/javascript" src="${ctx }/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx }/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/js/MenuTree.js"></script>
<script type="text/javascript" src="${ctx }/ext-2.1/ext-lang-zh_CN.js"></script>
<link rel="StyleSheet" href="${ctx }/css/dtree.css" type="text/css" />
<!-- 从云平台借鉴 -->



<script type="text/javascript">
Ext.BLANK_IMAGE_URL = '${ctx }/ext-2.1/resources/images/default/s.gif';
</script>
<style type="text/css">
body,html{ background: none repeat scroll 0 0 #34495e; }
.span1 {
    float: left; 
    height: 44px;
    text-align: left;
    width: 300px;
}
.span2 {
    float: right; 
    height: 44px;
    text-align: right;
    width: 400px;
}
.xue_zuti {
    background: #34495e ;
    color: #fff;
  float: left;
    height: 43px;
    padding: 0;
    width:100%;
    padding: 0px 0px 0px 10px;
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
   width: 115px; 
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
    width: 115px;
}
 .lanmu2 {
    text-decoration: none;
} 
.x-tree-node a span, .x-dd-drag-ghost a span{
	color: #ffffff;
}
.x-tree-node{
	font: 15px arial,tahoma,helvetica,sans-serif;
}
.x-tree-node-collapsed .x-tree-node-icon{
	background-image:url(${ctx}/css/zTreeStyle/img/diy/1_close.png);
}
.x-tree-node-expanded .x-tree-node-icon{
	background-image:url(${ctx}/css/zTreeStyle/img/diy/1_open.png);
}
.x-tree-node-leaf .x-tree-node-icon{
	background-image:url(${ctx}/css/zTreeStyle/img/diy/10.png);
}
#pj_menu1 {
    position: absolute;
    left: 30px;
    height:91%;
    width:82.5%;
    top: 44px;
    bottom: 0px;
    background: #34495E none repeat scroll 0% 0%;
    overflow: auto; 
} 
a{
	color:#FFFFFF;
	text-decoration:none;
}
div{
	overflow:hidden;
	/*height: 98%;*/
} 
body,html{
    overflow-x:hidden;
	overflow-y:hidden; 
}
.menu li{
	margin-left:10px;
}
img{vertical-align:middle}
</style> 

<script type="text/javascript">
var dataUrl = '${ctx}/master/QueryCommentTreeNodeAction.a?getTreeContent';	
var menutree;
Ext.onReady(function(){
	Ext.QuickTips.init();
	//加载对象
	var loader = new Ext.tree.TreeLoader({ 
		url: dataUrl 
	});
	
	menutree = new Ext.tree.MenuTree({
		border:false,	 //没有边框
		rootId:'root',
        rootText:'班级列表',
     //   bgcolor:'#D0F4FE',
        listeners:{  //监听节点的加载 传递参数
            "beforeload":function(node){  
                node.loader=new Ext.tree.TreeLoader({  
                    url:dataUrl,  
                    baseParams:{nodeId:node.id}  
                });  
            },
            "load":function(node){
				//alert(node.id );
				//if('${oneLevelId}'!=''&&'${oneLevelId}'==node.id){
					
				//}
			//	this.getNodeById(node.id).firstChild.select();
				//  if(menutree.getNodeById(node.id).hasChildNodes()){ 
		       	//	 menutree.getNodeById(node.id).select();
		          	/* 	  alert(menutree.getNodeById(node.id).firstChild);  */
	       		 //} 
             }
        },	
		treeTypeFlag:true,
		renderTo:'pj_menu1'
	});
	//展开根节点
	menutree.getNodeById('root').expand();
});
</script>
<script type="text/javascript">
function forward(){
	var commonFuncId =<%=request.getAttribute("commonFuncId")%>;
	var id;
	if(commonFuncId=='null'||commonFuncId==''||commonFuncId=='4020'||commonFuncId=='3020'){
		id="student";
		$("#"+id).attr("href","${ctx }/master/StudentApprisedAction.a?DefaultEduId");
	}else{
	 	id="student"+commonFuncId;
	 	document.getElementById('teacher').click();
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
function clearTimeId(){
	if(addTest()){
		clearInterval(timeId);
		timeId = null;
	}
}
var timeId = setInterval("clearTimeId()",5);
  function showStudentMenu(){
	  self.parent.frames[2].ShowDiv && self.parent.frames[2].ShowDiv();
	    $("#pj_menu1").css("display","block");
	    $("#pj_menu2").css("display","none");
	    $("#span2").removeClass().addClass("span1");
	    $("#span1").removeClass().addClass("span2");
	    $("#student").attr("href","${ctx }/master/StudentApprisedAction.a?DefaultEduId");
  }
  function showTeacherMenu(){
	  self.parent.frames[2].ShowDiv && self.parent.frames[2].ShowDiv();
	    $("#pj_menu1").css("display","none");
	    $("#pj_menu2").css("display","block");
	    $("#span2").removeClass().addClass("span2");
	    $("#span1").removeClass().addClass("span1") ;
	    $("#teacher").attr("href","${ctx }/master/QueryCommentAction.a?sectionCode=10");
  }
   window.onload = function() {
		var current = document.getElementsByTagName('li')[0];
		document.body.onclick = function(e) {
			var a = e || window.event;
			var src = a.srcElement || a.target;
			src = src.parentNode;
		}
	} 
   
  function changeAStyle(currentA){
	  self.parent.frames[2].ShowDiv && self.parent.frames[2].ShowDiv();
   		 var a = $("#pj_menu2").find("a");
   		 for(var i=0;i<a.size();i++){
   			 if($(a[i]).parent().attr("class")=="cur"){
   				$(a[i]).parent().removeClass();
   				break;
   			 }
   		 }
   		currentA.parent().addClass("cur") ;
  }
</script>
</head>
<body>
<div class="xue_zuti"> 
  <span  id="span2"  class="span1"><a  id="student"  href="#" class="lanmu2" target="main" onclick="showStudentMenu()">按学生</a></span>
  <span  id="span1" class="span2"><a  id="teacher"   href="#" class="lanmu2" target="main" onclick="showTeacherMenu()" >按栏目 </a></span>  
</div>
<div id="pj_menu2"  style="display:none;"> 
    <ul class="menu menu_zw" >
   <li class="cur"><a href="${ctx }/master/QueryCommentAction.a?sectionCode=10" <c:if test="${ empty commonFuncId or commonFuncId eq '4020' or commonFuncId eq '3020'}">id="student"</c:if> <c:if test="${not empty commonFuncId and commonFuncId ne '4020' and commonFuncId ne '3020'}">id="student1001"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xieks.png">新学期伊始的我&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=20" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1002"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiejs.png">学期结束时的我&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=30" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1003"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiesx.png">思想道德&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=80" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1004"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiecj.png">学业成就&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=40" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1005"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiehz.png">合作与交流&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=50" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1006"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xieyd.png">运动与健康&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=60" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1007"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiesm.png">审美与表现&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=90" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1008"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiesh.png">综合实践活动&nbsp;<span class="red">*</span></a></li> 
            <li><a href="${ctx }/master/QueryCommentAction.a?sectionCode=70" <c:if test="${ empty commonFuncId}">id="student"</c:if> <c:if test="${not empty commonFuncId}">id="student1009"</c:if> target="main"  onclick="changeAStyle($(this))"><img src="${ctx}/images/xiegx.png">个性发展&nbsp;<span class="red">*</span></a></li> 
      
      
      
      
      
        
      
    </ul>
</div>

<div id="pj_menu1"  >
<!--     <div class="zTreeDemoBackground left" style="color:#ffffff">
      <ul id="pj_menu1" class="ztree"></ul>
  </div> -->
</div>

</body>
</html>