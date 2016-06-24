<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8" ?>
    
<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
</head>
<SCRIPT type="text/javascript">
var orgin = "";
function closeleftmenu()

{
	str=document.all.switch1.src
	flag=str.search("left_off.gif")

	if (flag>0)
	
		{
			orgin = window.parent.document.getElementsByTagName("frameset")[0].cols;
			document.all.switch1.src=str.replace("left_off.gif","left_on.gif");
			var frame2 = $(self.parent.frames[2].document);
			var pjDiv = frame2.find("#pj_ziwo_main");
			if(pjDiv.length>0){
				pjDiv.css("width","98.3%");
			};
			var dgm = frame2.find("#dangqianwz_gz_master");
			if(dgm.length>0){
				dgm.css("width","97.05%");
			};
			window.parent.document.getElementsByTagName("frameset")[0].cols="0px,11px,*"; 
			$("a").attr("title","打开左边菜单");
		}
		
		else
			{
				document.all.switch1.src=str.replace("left_on.gif","left_off.gif");
				var frame2 = $(self.parent.frames[2].document);
				var pjDiv = frame2.find("#pj_ziwo_main");
				if(pjDiv.length>0){
					pjDiv.css("width","97.95%");
				};
				var dgm = frame2.find("#dangqianwz_gz_master");
				if(dgm.length>0){
					dgm.css("width","96.4%");
				};
				window.parent.document.getElementsByTagName("frameset")[0].cols = orgin; 
				$("a").attr("title","关闭左边菜单");
			}
		/*force ie10 redraw*/
		if(navigator.userAgent.indexOf('MSIE 10.0') != -1){
		var w = parent.document.body.clientWidth;
		parent.document.body.style.width = w + 1 + 'px';
		setTimeout(function(){
		parent.document.body.style.width = w - 1 + 'px';
		parent.document.body.style.width = 'auto';
		}, 0);
		}
}
function changeColor(currentObj,num){
	if(num==1){
		currentObj.css("background","#B4DDBD none repeat scroll 0% 0%");
	}else{
		currentObj.css("background","");
	}
}
</SCRIPT>
<body style="margin: 0;">
<table width="11" height="100%" border="0" cellpadding="0" cellspacing="0" onmouseout="changeColor($(this),0)" onmouseover="changeColor($(this),1);">
  <tr>
    <td><div width="11" height="75px" id="td1">
    <a href="#" title="关闭左边菜单">
    <img src="${ctx}/images/left_off.gif" width="11" height="23" border="0" name="switch1" onClick="javascript:closeleftmenu()"></a>
    </div>
    </td>
  </tr>
</table>
</body>
</html>