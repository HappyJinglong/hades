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
			try{
				var frameChild = $(self.parent.frames[2].frames[1].document);
				var bge41 = frameChild.find("#bg_eee_4_1");
				if(bge41.length>0){
					bge41.attr("width","7.8%")
				}
				var bge31 = frameChild.find("#bg_eee_3_1");
				if(bge31.length>0){
					bge31.attr("width","7.4%")
				}
				var bge42 = frameChild.find("#bg_eee_4_2");				
				if(bge42.length>0){
					bge42.attr("width","7.5%")
				}
				
			}catch(e){
				var frame2 = $(self.parent.frames[2].document);
				var bge21 = frame2.find("#bg_eee_2_1");
				if(bge21.length>0){
					bge21.attr("width","8.1%")
				}
				var bge22 = frame2.find("#bg_eee_2_2");
				if(bge22.length>0){
					bge22.attr("width","7.7%");
				}
				var bge23 = frame2.find("#bg_eee_2_3");
				if(bge23.length>0){
					bge23.attr("width","7.85%")
				}
				var bge24 = frame2.find("#bg_eee_2_4");
				if(bge24.length>0){
					bge24.attr("width","8.1%")
				}
				var bge25 = frame2.find("#bg_eee_2_5");
				if(bge25.length>0){
					bge25.attr("width","7.9%")
				}
			}
			window.parent.document.getElementsByTagName("frameset")[0].cols="0px,11px,*"; 
			/* document.all.switch1.alt="打开左边菜单"; */
			$("a").attr("title","打开左边菜单");
		}
		
		else
			{
				document.all.switch1.src=str.replace("left_on.gif","left_off.gif");
				try{
					var frameChild = $(self.parent.frames[2].frames[1].document);
					var bge41 = frameChild.find("#bg_eee_4_1");
					if(bge41.length>0){
						bge41.attr("width","10%")
					}
					var bge31 = frameChild.find("#bg_eee_3_1");
					if(bge31.length>0){
						bge31.attr("width","10%")
					}
					var bge42 = frameChild.find("#bg_eee_4_2");
					if(bge42.length>0){
						bge42.attr("width","10%")
					}
				}catch(e){
					var frame2 = $(self.parent.frames[2].document);
					var bge21 = frame2.find("#bg_eee_2_1");
					if(bge21.length>0){
						bge21.attr("width","10%")
					}
					var bge22 = frame2.find("#bg_eee_2_2");
					if(bge22.length>0){
						bge22.attr("width","10%")
					}
					var bge23 = frame2.find("#bg_eee_2_3");
					if(bge23.length>0){
						bge23.attr("width","10%")
					}
					var bge24 = frame2.find("#bg_eee_2_4");
					if(bge24.length>0){
						bge24.attr("width","10%")
					}
					var bge25 = frame2.find("#bg_eee_2_5");
					if(bge25.length>0){
						bge25.attr("width","10%")
					}
					
				}
				window.parent.document.getElementsByTagName("frameset")[0].cols = orgin; 
				/* document.all.switch1.alt="关闭左边菜单"; */
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