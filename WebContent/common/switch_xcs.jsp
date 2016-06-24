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
			var pjTb = frame2.find("#bg_eee_1_1");
			if(pjTb.length>0){
				pjTb.attr("width","8.1%");
			}
			var pjTb1 = frame2.find("#evaluateName_name");
			if(pjTb1.length>0){
				pjTb1.css("width","91.2%");
			}
			
			var pjTb2 = frame2.find("[name='xiaomao']");
			if(pjTb2.length>0){
				pjTb2.css("width","90%");
			}
			
			var pjTb3 = frame2.find("[name='delectNull_m']");
			if(pjTb3.length>0){
				pjTb3.css("width","5.7%");
			}
			var pjDiv = frame2.find("#pj_jiaoshi_main");
			if(pjDiv.length>0){
				/* pjDiv.css("width","99.02%"); */
			};
			var pjFB = frame2.find("#btnxie_001");
		 	if(pjFB.length>0){
				/* pjFB.parent().parent().css("width","7.2%"); */
			};
			var textStyle = frame2.find("#text_style");
			if(textStyle.length>0){
				frame2.find("textarea").css("width","102%");
			}
			window.parent.document.getElementsByTagName("frameset")[0].cols="0px,11px,*"; 
			$("a").attr("title","打开左边菜单");
		}
		
		else
			{
				document.all.switch1.src=str.replace("left_on.gif","left_off.gif");
				var frame2 = $(self.parent.frames[2].document);
				var pjTb = frame2.find("#bg_eee_1_1");
				if(pjTb.length>0){
					pjTb.attr("width","10%");
				}
				var pjTb1 = frame2.find("#evaluateName_name");
				if(pjTb1.length>0){
					pjTb1.css("width","90.1%");
				}
				
				var pjTb2 = frame2.find("[name='xiaomao']");
				if(pjTb2.length>0){
					pjTb2.css("width","89.6%");
				}
				var pjTb3 = frame2.find("[name='delectNull_m']");
				if(pjTb3.length>0){
					pjTb3.css("width","7%");
				}
				
				var pjDiv = frame2.find("#pj_jiaoshi_main");
				if(pjDiv.length>0){
					/* pjDiv.css("width","98.79%"); */
				};
			 	var pjFB = frame2.find("#btnxie_001");
			 	if(pjFB.length>0){
					/* pjFB.parent().parent().css("width","9%"); */
				};
				var textStyle = frame2.find("#text_style");
				if(textStyle.length>0){
					frame2.find("textarea").css("width","99%");
				}
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