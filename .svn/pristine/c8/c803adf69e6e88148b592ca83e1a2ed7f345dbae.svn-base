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
function closeleftmenu()

{
	str=document.all.switch1.src
	flag=str.search("left_off.gif")

	if (flag>0)
	
		{
			document.all.switch1.src=str.replace("left_off.gif","left_on.gif");
			var frame2 = $(self.parent.frames[2].document);
			var pjDiv = frame2.find("#pj_jiaoshi_main");
			if(pjDiv.length>0){
				pjDiv.css("width","99.4%");
			};
			var pjDiv1 = frame2.find("#tjb_main_flag");
			if(pjDiv1.length>0){
				frame2.find("#tjb_main").css("width","98.2%");
			};
			var pjTb = frame2.find("#bg_eee_1_1");
			if(pjTb.length>0){
				pjTb.attr("width","8%");
			}
			var divLayout = frame2.find(".layout");
			if(divLayout.length>0){
				divLayout.css("width","103.95%");
			}
			var divSC_btn = frame2.find("#shanchu_btn");
			if(divSC_btn.length>0){
				divSC_btn.css("width","8.05%");
			}
			var textStyle = frame2.find("#text_style");
			if(textStyle.length>0){
				frame2.find("textarea").css("width","90.6%");
			}
			var dengslanniu = frame2.find("#dengslanniu");
			if(dengslanniu.length>0){
				dengslanniu.css("width","90%");
			}
			window.parent.document.getElementsByTagName("frameset")[0].cols="0px,11px,*"; 
			$("a").attr("title","打开左边菜单");
		}
		
		else
			{
				document.all.switch1.src=str.replace("left_on.gif","left_off.gif");
 				var frame2 = $(self.parent.frames[2].document);
				var pjDiv = frame2.find("#pj_jiaoshi_main");
				if(pjDiv.length>0){
					pjDiv.css("width","99.4%");
				};
				var pjDiv1 = frame2.find("#tjb_main_flag");
				if(pjDiv1.length>0){
					frame2.find("#tjb_main").css("width","97.8%");
				};
				var pjTb = frame2.find("#bg_eee_1_1");
				if(pjTb.length>0){
					pjTb.attr("width","10%");
				}
				var divLayout = frame2.find(".layout");
				if(divLayout.length>0){
					divLayout.css("width","105%");
				}
				var divSC_btn = frame2.find("#shanchu_btn");
				if(divSC_btn.length>0){
					divSC_btn.css("width","10%");
				}
				var textStyle = frame2.find("#text_style");
				if(textStyle.length>0){
					frame2.find("textarea").css("width","88.5%");
				}
				var dengslanniu = frame2.find("#dengslanniu");
				if(dengslanniu.length>0){
					dengslanniu.css("width","89.5%");
				}
				window.parent.document.getElementsByTagName("frameset")[0].cols="255px,11px,*"; 
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
//onmouseout="changeColor($(this),0)" onmouseover="changeColor($(this),1);"
</SCRIPT>
<body>
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