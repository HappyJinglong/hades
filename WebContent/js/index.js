// JavaScript Document
window.onload=function(){
	var oMenus=document.getElementById('menus');
	var AMenu = document.getElementById('AMenu');
	var oAlls=document.getElementById('alls');
	
	oMenus.onmouseenter=function(){
//		clearInterval(oMenus.timer)
		oAlls.style.display="block";	
		AMenu.style.border= 'solid 1px';
		AMenu.style.borderBottomColor='#293A4A';
	}
	oMenus.onmouseleave=function(){
		oAlls.style.display="none";	
		AMenu.style.border= 'none';
		AMenu.style.borderBottom='solid 1px #293A4A';
//		oMenus.timer=setInterval(function(){
//			oAlls.style.display="none";			
//			
//		},500)
//		oAlls.onmouseover=function(){
//			clearInterval(oMenus.timer)	
//			
//		}	
//		oAlls.onmouseout=function(){
//			oMenus.timer=setInterval(function(){
//				oAlls.style.display="none";	
//			},500)
//		}
	}


//  rechange();
//	window.onresize=function(){
//		rechange();
//	}
	
}
function rechange(){
	/*var _winH = $(window).height();
	var _winW = $(window).width();*/
	var _winH = window.screen.availHeight;
	var _winW = window.screen.availWeight;
	var iframeHight = _winH - 216;
	/*alert(iframeHight);*/
	$('#iframepage').css({'height':iframeHight+'px','width':_winW});	
}







