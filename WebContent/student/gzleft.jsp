<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link href="${ctx}/css/cmis4_global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/cmis4_tree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.js"></script>
<script src="${ctx}/js/dtree.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.core-3.5.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/css/zTreeStyle/zTreeStyle.css" />    
<style type="text/css">
body,html{ background: none repeat scroll 0 0 #34495e;  overflow-x:hidden;
	 overflow-y:hidden; }
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
</style>
 <script type="text/javascript">
 function forward(){
	 $("#student").attr("href","${ctx}/student/GzTreeShowAction.a");
	 document.getElementById("student").click();
}
function addTest(){
	self.parent.frames[1].gotoPage();
}
	function showStudentMenu(){
		self.parent.frames[1].ShowDiv();
		$("#pj_menu1").css("display","none");
		$("#pj_menu2").css("display","block");
		$("#span2").removeClass().addClass("span1");
		$("#span1").removeClass().addClass("span2") ;
		$("#student").attr("href","main.html");
	}
	function showTeacherMenu(){
		self.parent.frames[1].ShowDiv();
 		$("#pj_menu1").css("display","block");
 		$("#pj_menu2").css("display","none");
		$("#span2").removeClass().addClass("span2");
		$("#span1").removeClass().addClass("span1");
		$("#teacher").attr("href","ziwopingjia3.html");
	}
    var setting = {  
    		view: {
               //dblClickExpand: false,
               expandSpeed:0, //设置树展开的动画速度
         },
         //获取json数据
        async : {  
            enable : true, 
            url : "${ctx}/student/ZtreeActio.a?sObjecthow", // Ajax 获取数据的 URL 地址  
            autoParam : [ "id","name"] //ajax提交的时候，传的是id值
        },  
        data:{ // 必须使用data  
            simpleData : {  
                enable : true,  
                idKey : "id", // id编号命名   
                pIdKey : "pId", // 父id编号命名    
                rootPId : 0 
            }  
        },  
        // 回调函数  
        //?????treeId
         callback : {  
        	
        	
         		beforeClick: function ( treeId, treeNode, clickFlag) {
         			 var ssd=treeNode.id;
        		 	 var b=ssd.split("_")[1]; 
        		 	alert(b);
        		 	var targetObj = $("#tree_" +b+ "_a");
        		 	var tar = targetObj[0];
        		 	 targetObj[0].attr("target","main");
               	    targetObj[0].attr("src","ZtreeActio.a?showApprasial");
         		  
         	    }  ,
    

              onClick : function(event, treeId, treeNode, clickFlag) {  
            	     /* alert(treeId)
            	     alert(event)
            	 	 alert("ffff");
        		 	 var oA = null; 
        		 	 var ssd=treeNode.id;
        		 	 var b=ssd.split("_")[1];
        		 	alert(b);
        		 	 
        		 	var targetObj = $("#tree_" +b+ "_a");
        		 	var tar = targetObj[0];
        		 	alert(tar.target);
        		 	alert(tar.nodeType);
        		 	var names="";       
        		    for(var name in tar){       
        		       names+=name+": "+tar[name]+", ";  
        		    }  
        		    alert(names); 
                	  alert(" 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);  
                	  targetObj[0].attr("target","main");
                	  targetObj[0].attr("src","ZtreeActio.a?showApprasial"); */
            }    
          }
  } 
   
    // 过滤函数  
    function filter(treeId, parentNode, childNodes) {  
        if (!childNodes)  
            return null;  
        for ( var i = 0, l = childNodes.length; i < l; i++) {  
           childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');  
        }  
        return childNodes;  
    }  
   
    //   
    $(document).ready(function() { 
        $.fn.zTree.init($("#tree"),setting);  
    });
	
    
 
    

    
    
    
</script>
</head>
<body onload="addTest();">
<div class="xue_zuti"> 
 	<span  id="span2"  class="span1"><a  id="student" href="#" class="lanmu2" target="main" onclick="showStudentMenu()">按栏目</a></span>
 	<span  id="span1" class="span2"><a  id="teacher"   href="#" class="lanmu2" target="main" onclick="showTeacherMenu()" >按同学 </a></span>	 
</div>

<div id="pj_menu2"> 
    <ul class="menu" >
    	<!--<li class="cur"><a href="#" class="yangshi">班主任评语</a></li> -->	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=3020" target="main" class="nobdt" >思想道德</a></li>	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=8020" target="main">学业成就</a></li>	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=4020" target="main">合作与交流<span class="red">*</span></a></li>	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=5020" target="main">运动与健康</a></li>	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=6020" target="main">审美与表现</a></li>	
    	<li><a href="${ctx}/student/PlayAndHealthAction.a?evaluateType=7030" target="main">个性发展</a></li>	
    	<li><a   target="main" href="${ctx}/student/ZtreeActio.a">测试用</a></li>	
    	<li class="bdtbox"><a href="#" class="bdt"></a></li>	
    </ul>
</div>


<div id="pj_menu1"   style="display:none;">
        <ul id="tree" class="ztree"></ul>  
</div>

</body>
</html>
