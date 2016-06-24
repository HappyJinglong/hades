<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 <%@ include file="/common/mass.jsp"%> 
<head>

<title></title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />

<link href="${ctx}/css/public.css" rel="stylesheet" type="text/css" />
<%--  <link href="${ctx}/css/iframe.css" rel="stylesheet" type="text/css" />  --%>
 <link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" /> 
 <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
 <link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" /> 
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/func.js"></script>

<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<style type="text/css">
body,html{ width:100%; height:100%;
     overflow-x:hidden;
	 overflow-y:hidden; 

}
#pj_jiaoshi_main {
    bottom: 0;
    left: 0px;
    width:94%;
    /*min-width: 930px;*/
    overflow: auto;
    /*padding-left: 50px;*/
    padding-top: 30px;
    /* position: absolute;
    right: 0; */
   /*  top: 0px; */
}
.mt10{margin-top:0px;}
A{margin-top: 10px;}
#pj_jiaoshi_main .name_con .btnxie{
border-top-width: 0px;
border-bottom-width: 0px;
border-right-width: 0px;
height:131px
}
.title_xie{
   height:40px;
   background-color:rgb(39,159,70); 
   padding-left: 100px;
   padding-top: 20px;
   font-size:16px;
   color:#fff;
}
.xiechengsheng3{
   margin-lef:50px;
   margin-left:50px;
   padding-left: 50px;
    width: 10%;
   background-color:rgb(238,238,238);
   border:solid #999; border-width:0px 1px 0px 1px;
}
.xiechengsheng4{
 width: 100%;
 /*margin-left:50px;*/
   
}
.xiechengsheng{
   padding-right: 20px;
   text-align:right;
   background-color:rgb(238,238,238);
   border:solid #999; border-width:0px 1px 0px 0px;
   font-size:12pt;
}

#pj_jiaoshi_main .name{
   padding-left: 300px;
}
#pj_jiaoshi_main{
   padding-right: 10px;
   padding-left: 0px;
   width:98.79%;
   padding-top:0px;
   top:20px;
}
td ,tr{margin-right: -15px}
.img-padding{
 margin-top:-20px;
}

</style>
</head>
<script type="text/javaScript">


$(window).load(function() {
	 var thisMession=document.getElementById("mession");
	 
	 
	 thisMession.innerHTML="";
	 thisMession.innerHTML=mess;  
	  });

var dectestatic="notdelct";
//锚点选中数据
$(document).ready(function(){
	 $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "${ctx}/student/PlayAndHealthAction.a?queryData",
		data: "{}",
		dataType: "json",
		success: function (msg) {
			$("#lookForId").autocomplete(msg.val,{
				minChars: 0,
				width: 310,
				matchContains: true,
				//autoFill: true,
				max:2000,
				formatItem: function(row, i, max) {
					return  row.name ;
				},
				formatMatch: function(row, i, max) {
					return  row.name;
				},
				formatResult: function(row) {
					return row.name.split("_")[0];
				}
			}).result(function(event, row) {
				//选中后触发事件
				/* (row.name); */
				window.location.hash=row.name;
			});
		}
	});
});
//文字限制提示
$(document).ready(function(){
      
	var textareas=$("body").find("textarea");	
		for(var i=0;i<textareas.size();++i)
		{ 
			var id_content=$(textareas[i]).attr("id");
			 if(id_content.indexOf("new_apprise_content")>=0) 
			 {  
				 $("#"+id_content).css("color","#ACACAC"); 
				 $("#"+id_content).val("最多输入4000字");
			 } 
		}
	
});

//文字限制提示
function readying(){
	var textareas=$("body").find("textarea");
		for(var i=0;i<textareas.size();++i)
		{ 
			var id_content=$(textareas[i]).attr("id");
			 if(id_content.indexOf("new_apprise_content")>=0) 
			 {  
				 $("#"+id_content).css("color","#ACACAC"); 
				 $("#"+id_content).val("最多输入4000字");
			 } 
		}
}


//新增
	function addApprasial(studentid,num){ 
		var orderNum=0;		
		var divs=$("#"+studentid+"_xie").find("div");
    	for(var i=0;i<divs.size();++i)
		{ 
			var id_content=$(divs[i]).attr("id");
			 if(id_content.indexOf("_dele")>=0) 
			 {  
				 ++orderNum;
			 } 
		}
    	       var newNumber=Number(orderNum)+Number(1);
    	         $('#'+studentid+'_'+num+'_add').css("display","none");
					var newDiv = Ext.get('new_apprise');
					newDiv.setDisplayed("");
					$("#new_studentid").val(studentid);
					$("#new_numshu").val(num);
					$("#new_shu").html(newNumber);
					var ss=$('#'+studentid+'_'+num+'_add').parent();
					var ss1=ss.parent();
					 $("#new_apprise").insertAfter(ss1);
					 readying();
		
	} 
	function save_process(){
		var width = $("#btnxie_001").parent().parent().css("width");
		var textWidth = $("textarea").css("width");
		setTimeout(function () { 
		 document.getElementById("new_apprise_content").style.border ="0px solid";
		var apper=$("#new_apprise_content").val().trim();
		if(apper=="最多输入4000字"){
          return; 	
		}
		var studentid=$("#new_studentid").val();
		var name=$("#"+studentid+"_name").html();
		if(apper!=""&&apper!=null){
			  var apperTrim=$("#new_apprise_content").val().length;
			 
		    if(apperTrim<=4000){
		    	 $("#new_apprise_content").bind('click',function(){
			 			romvezi();
			 		}); 
				Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		    	Ext.Ajax.request({
					url:'${ctx}/student/PlayAndHealthAction.a?doUpdataOtherProcessNll',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
					   var kk=document.getElementById("new_studentid").value; 
						var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var info=len.info;
					    var apprasialid=len.apprasialid; 
					    if(success="true")
					    {     
					    	   var newNum= $("#new_numshu").val();
					           var num=parseInt(newNum)+parseInt(1);
					           var studentid=$("#new_studentid").val();//document.getElementById("new_studentid").value;
					           if(dectestatic=="delct"){
						    		  Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
							    		Ext.Ajax.request({
										url:'${ctx}/student/PlayAndHealthAction.a?deleApprasial',
										method:'POST',
										defaults:{autoScroll: true},
										success:function(response,options){
											
											  var len=Ext.util.JSON.decode(response.responseText);
											    var success=len.success.replace( /^\s+|\s+$/g,"");
											    
											    if(success="true")
											    {    alert_delete_success(name);
											    	dectestatic="notdelct";
											    	$("#new_apprise_content").val("");
											    	$("#new_apprise").css("display","none");
                                                        var pNum=0;
											    	    var divs=$("#"+studentid+"_xie").find("div");
											    	    for(var i=0;i<divs.size();++i)
                                                        { 
                                                        	 var id_content=$(divs[i]).attr("id");
                                                        	 if(id_content.indexOf("_dele")>=0) 
                                                        	 {   ++pNum;
                                                        		 var arr = new Array();
                                                        		 arr[pNum]=id_content;
                                                        	 } 
                                                        }
                                                           var lengthDiv=arr[pNum];
                                                        	var  lengthpid=lengthDiv.split("_"); //字符分割 
                                                        	var  lengthpidAdd=lengthpid[1];
                                                        	
                                                        	 //(lengthpidAdd);id="${listat.studentid}_${status.index+1}_add"
                                                        	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
											    
											    }else if(success=="false")
											    {
											    	
											    	this_div.focus();
											    	return;
											    }
										},
										params : {
										     apprasialid :apprasialid
										}
									});  
						    	  
						    	  
						    	  }else{
					    	   var apprasial =$("#new_apprise_content").val();
					    	   var signDate =$("#newDate").val(); 
					    	   
					    	   var evaluateType = document.getElementById("evaluateType").value;
					    	   var xianshishu=$("#new_shu").html();
					    	   
					    	  var newdiv= "<div  id='"+studentid+"_"+num+"_dele'>"
					    	  +"<table  class='xiechengsheng4' cellspacing='0'>"
								+"<tr>" 
								  +"<td id='"+studentid+"_"+num+"_shu' width='10%' height='40' class='xiechengsheng3'>"+xianshishu+"</td>"
								     +"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
										+"日期：<input id='"+studentid+"_"+apprasialid+"_"+num+"_Date' type='text' size='10' value='"+signDate+"' idvalue='"+signDate+"' onblur='update_time("+studentid+","+apprasialid+","+num+")' onClick='WdatePicker()'/></td>"
								      +"</tr>"
							   +"</table>"
					    	  
					    	  +"<div class='name_con ' style='background:#eee;' id='wuyong1_"+num+"'>"
					        	 +"<div class='fl neirong ml70 mt10' id='wuyong2_"+num+"'>"
									 +"<div  style='height:120px; margin-top:0px; width:100%;pxoverflow:auto;' id='"+studentid+"_"+num+"_content' idvalue='"+apprasial+"'>"
										+"<textarea id= '"+studentid+"_"+num+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width:"+textWidth+";' onclick='return change_content_is_available("+apprasialid+","+studentid+","+num+",true);'>"+apprasial+"</textarea>"
									+"</div>"
									+"</div>"
					            	+"<div class='fr btnxie' style='width:"+width+";' id='wuyong6_"+num+"'>"
					            	  	 +"<div class='xiezhong' id='wuyong7_"+num+"'>" 	
					            	  		+"<a id='btnxie_001' href='#' onclick='javascript:removeAppraise("+apprasialid+","+studentid+","+num+");' class='local delect'></a>"
					            	   +"</div>"
					            	+"</div>"
					             +"</div>"
					             +"<div class='name_cxbox ' id='"+studentid+"_"+num+"_add' >"
					            	+"<div class='name_cxright fr' id='wuyong8_"+num+"'>"
								          +"<div class='fr btn' id='wuyong17_"+num+"'>"
								       				 +"<a href='#' id='add' class='fr zengjia mr100' onclick='addApprasial("+studentid+","+num+");' ></a>" 
								           +"</div>"
							   		+"</div>"
					        +"</div>"
					      +"</div>";   
					    	  var newpaerdiv=document.getElementById("tianjian");
						         newpaerdiv.innerHTML=newdiv;
						        var ss=$('#'+studentid+'_'+newNum+'_dele');
						         var ssf=ss.parent();
						         $("#"+studentid+"_"+num+"_dele").insertAfter(ss);
						         var newDiv = Ext.get("new_apprise");
									
						         $("#new_apprise").css("display","none");
					    
						         if(dectestatic!="delct"){
							    		alert_save_success(name,xianshishu);
							    	}  
						   }
					    }else if(success=="false")
					    {
					    
					    	this_div.focus();
					    	return;
					    }
						readying();
					},
					params : {
						 apprasial :$("#new_apprise_content").val(),
					    signDate :$("#newDate").val(), 
					    id:$("#new_studentid").val(),
					    evaluateType : $("#evaluateType").val()
					}
				});
		 }else{
			 alert("评价内容已超过4000");
		 }
		}else{
			
			$("#new_apprise_content").bind('click',function(){
	 			romvezi();
	 		});
			$("#new_apprise_content").val("最多输入4000字");
			readying();
	 }
	
	 }, 50);
	}
	//使得内容变为可写
	var content_div_full="";
	function change_content_is_available(apprid,studentid,id,flag){
		var textWidth = $("textarea").css("width");
		var name=$("#"+studentid+"_name").html();
		if(id==null||id==""){
		}else{
			
			if(flag==true){
				var this_div = document.getElementById(studentid+"_"+id+"_content");
				this_div.focus();
				this_div.parentNode.onclick=null;
				$("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#fff");
				this_div.innerHTML=''; 
				this_div.innerHTML='<textarea  id="apperedxiel_'+studentid+'_'+id+'"   style="height:120px; width:'+textWidth+'; overflow:auto; border:solid 1px;"  onblur="return change_content_is_available('+apprid+','+studentid+','+id+',false)"></textarea>';
				document.getElementById("apperedxiel_"+studentid+"_"+id).value=this_div.attributes["idvalue"].nodeValue;
				document.getElementById("apperedxiel_"+studentid+"_"+id).focus();
				$("#apperedxiel_"+studentid+"_"+id).focus();
				content_div_full=document.getElementById("apperedxiel_"+studentid+"_"+id).innerText;
			}else{
				var this_div = document.getElementById(studentid+"_"+id+"_content");
				var app=$("#apperedxiel_"+studentid+"_"+id).val().trim().length;
				var apple=$("#apperedxiel_"+studentid+"_"+id).val();
				var leng=$("#apperedxiel_"+studentid+"_"+id).val().length;
				content_div_full=$("#"+studentid+"_"+id+"_content").attr("idvalue");
				if(app>0){ 
				if(leng<=4000){
				if(apple!=content_div_full){
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/PlayAndHealthAction.a?doUpdataOtherProcess',
						method:'POST',
						success:function(response,options){
								var len=Ext.util.JSON.decode(response.responseText);
							    var success=len.success.replace( /^\s+|\s+$/g,"");
							    var info=len.info;
							    if(success="true")
							    {    
							    	var xianshu=$("#"+studentid+"_"+id+"_shu").html();
							       alert_update_success(name,xianshu);
							       $("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#eee");
							       var appernei=$("#apperedxiel_"+studentid+"_"+id).val();
									this_div.innerHTML="<textarea id= '"+studentid+"_"+id+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: "+textWidth+";'>"+appernei+"</textarea>";
									this_div.setAttribute("idvalue",appernei);				
									this_div.parentNode.onclick=function(){change_content_is_available(apprid,studentid,id,true);};
							       
							    }else if(success=="false")
							    {
							    	
							    	this_div.focus();
							    	return;
							    }
						},
						params : {
							id :studentid,
							apprasialid:apprid,
							apprasial : $("#apperedxiel_"+studentid+"_"+id).val(),
							evaluateType : document.getElementById("evaluateType").value,
							signDate : document.getElementById(studentid+"_"+apprid+"_"+id+"_Date").value 
						}
					});
				
				
				
				
				} else{
					 $("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#eee");
				   //更新数据
					this_div.setAttribute("idvalue",document.getElementById("apperedxiel_"+studentid+"_"+id).value);				
					this_div.parentNode.onclick=function(){change_content_is_available(apprid,studentid,id,true);}
					var appernei=$("#apperedxiel_"+studentid+"_"+id).val();
					this_div.innerHTML="<textarea id= '"+studentid+"_"+id+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: "+textWidth+";'>"+appernei+"</textarea>";
					
				} 
				}else{
					alert("评价内容已超过4000");
				  }
				}
				}
				}
		}	 
	
	
	//使得内容为空时可写
	var content_div_full="";
	function change_content_is_availableNll(apprid,studentid,id,flag){
		var width = $("#btnxie_001").parent().parent().css("width");
		var textWidth = $("textarea").css("width");
		var name=$("#"+studentid+"_name").html();
		if(id==null||id==""){
		}else{
			var this_div = document.getElementById(studentid+"_"+id+"_content");
			if(flag==true){  
				this_div.focus();
				this_div.parentNode.onclick=null;                                              
				this_div.innerHTML='<textarea name="idcontent"  style="height:120px; width:'+textWidth+'; overflow:auto;border:solid 1px;" id="idcontent" onblur="return change_content_is_availableNll('+apprid+','+studentid+','+id+',false)"></textarea>';
				var nullapper=this_div.attributes["idvalue"].nodeValue;
				if(nullapper!=""){
				  document.getElementById("idcontent").value=this_div.attributes["idvalue"].nodeValue;
				}
				document.getElementById("idcontent").focus();
				$("#idcontent").focus();
			}else{
				var app=$("#idcontent").val().trim();
				if(app=="最多输入4000字"){
			          return; 	
					}
				var leng=$("#idcontent").val().length;
				if(leng<=4000){
				if(app!=null&&app!=""&&app!="最多输入4000字"){
					setTimeout(function () { 
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/PlayAndHealthAction.a?doUpdataOtherProcessNll',
						method:'POST',
						success:function(response,options){
							  var len=Ext.util.JSON.decode(response.responseText);
							    var success=len.success.replace( /^\s+|\s+$/g,"");
							    var info=len.info;
							    var apprasialid=len.apprasialid; 
							    if(success="true")
							    {    
							    	if(dectestatic!="delct"){
							    		alert_save_success(name,"1");
							    	}
							    	  if(dectestatic=="delct"){
							    		  Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
								    		Ext.Ajax.request({
											url:'${ctx}/student/PlayAndHealthAction.a?deleApprasial',
											method:'POST',
											defaults:{autoScroll: true},
											success:function(response,options){
												alert_delete_success(name);
												  var len=Ext.util.JSON.decode(response.responseText);
												    var success=len.success.replace( /^\s+|\s+$/g,"");
												    
												    if(success="true")
												    {   dectestatic="notdelct";
												    	//this_div.setAttribute("idvalue",document.getElementById("idcontent").value);				
														//this_div.innerText=document.getElementById("idcontent").value;     
														this_div.parentNode.onclick=function(){change_content_is_availableNll(apprid,studentid,id,true);}
														this_div.setAttribute("style","color:#ACACAC");
														this_div.innerText="最多输入4000字";
												    }else if(success=="false")
												    {
												    	
												    	this_div.focus();
												    	return;
												    }
											},
											params : {
											     apprasialid :apprasialid
											}
										});  
							    	  }else{
							    	  var apprasial =$("#idcontent").val();
										var evaluateType =document.getElementById("evaluateType").value;
										var signDate = document.getElementById(studentid+"_"+apprid+"_"+id+"_Date").value;
										 
										 var newdiv= "<div  id='"+studentid+"_1_dele'>"
										 +"<table  class='xiechengsheng4' cellspacing='0'>"
											+"<tr>" 
											  +"<td id='"+studentid+"_1_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
											     +"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
											          +"日期：<input  id='"+studentid+"_"+apprasialid+"_1_Date'  type='text' size='10' value='"+signDate+"'  idvalue='"+signDate+"' onblur='update_time("+studentid+","+apprasialid+",1)' onClick='WdatePicker()' /></td>"
										      +"</tr>"
										   +"</table>"
										   +"<div id='wuyong1_1' class='name_con ' style='background:#eee;'>"
								        	 +"<div id='wuyong2_1' class='fl neirong ml70 mt10'>"
												 +"<div  style='height:120px; margin-top:0px; width:100%;pxoverflow:auto;' id='"+studentid+"_1_content' idvalue='"+apprasial+"'>"
													+"<textarea id= '"+studentid+"_1_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: "+textWidth+";' onclick='return change_content_is_available("+apprasialid+","+studentid+",1,true);'>"+apprasial+"</textarea>"
												+"</div>"
												+"</div>"
								            	+"<div class='fr btnxie' style='width:"+width+";' id='wuyong3_1'>"
								            	  	 +"<div class='xiezhong' id='wuyong4_1'>" 	
								            	  		+"<a id='btnxie_001' href='#' onclick='javascript:removeAppraise("+apprasialid+","+studentid+",1);' class='local delect'></a>"
								            	   +"</div>"
								            	+"</div>"
								             +"</div>"
								             +"<div class='name_cxbox '  id='"+studentid+"_1_add'>"
								            	+"<div class='name_cxright fr' id='wuyong5_1' >"
											          +"<div class='fr btn' id='wuyong13_1'>"
											       				 +"<a href='#' id='add' class='fr zengjia mr100' onclick='addApprasial("+studentid+",1);' ></a>" 
											           +"</div>"
										   		+"</div>"
								        +"</div>"
								      +"</div>";  
							         var newpaerdiv=document.getElementById(studentid+"_xie");
							         newpaerdiv.innerHTML=newdiv; 
							    }
							    }else if(success=="false")
							    {
							    	
							    	this_div.focus();
							    	return;
							    }
							
						
						},
						params : {
							id :studentid,
							apprasial :$("#idcontent").val(),
							evaluateType : document.getElementById("evaluateType").value,
							signDate : document.getElementById(studentid+"_"+apprid+"_"+id+"_Date").value
							
						}
					});
					 }, 50);
				
				} else{
				   //更新数据
					
				    this_div.setAttribute("idvalue",document.getElementById("idcontent").value);				
					this_div.innerText=document.getElementById("idcontent").value;     
					this_div.parentNode.onclick=function(){change_content_is_availableNll(apprid,studentid,id,true);}
					this_div.setAttribute("style","color:#ACACAC");
					this_div.innerText="  最多输入4000字";
				} 
				}else{
					this_div.setAttribute("idvalue",document.getElementById("idcontent").value);	
					alert("评价内容已超过4000");
				  }
				}
				}
		}	 
	function saveApprasial(){
		var newDiv = Ext.get('new_apprise');
		save_process();
	}

	//删除评价
	 function removeAppraise(apprasialid,studentid,num) {
		 var width = $("#btnxie_001").parent().parent().css("width");
		 var textWidth = $("textarea").css("width");
	 	if(confirm("确定要删除?"))                              
		  {     var name=$("#"+studentid+"_name").html();
	 		    var evaluateType= document.getElementById("evaluateType").value;
		  		Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	    		Ext.Ajax.request({
				url:'${ctx}/student/PlayAndHealthAction.a?deleApprasial',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					alert_delete_success(name);
					    var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var info=len.info;
					    if(success="true")
					    {
					    	   var orderNum=0;
				        	   var pNum=0;
					    	var divs=$("#"+studentid+"_xie").find("div");
						    	for(var i=0;i<divs.size();++i)
								{ 
									var id_content=$(divs[i]).attr("id");
									 if(id_content.indexOf("_dele")>=0) 
									 {  
										 ++orderNum;
									 } 
								}
						    	
						    	if(orderNum==1){
						    		var temp1=$("#new_apprise").is(":visible");
				    				if(temp1){
				    					//$("#new_apprise_content").val("  最多输入4000字")
				    					$("#new_apprise").css("display","none");	
				    				}
						    	
						    		$("#"+studentid+"_"+num+"_dele").remove();
                                    
						    		var fistDiv="<div  id='"+studentid+"_1_dele'>"
						    		 +"<table  class='xiechengsheng4' cellspacing='0'>"
										+"<tr>" 
										  +"<td id='"+studentid+"_1_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
										     +"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
										     +"日期：<input id='"+studentid+"_0_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time("+studentid+",0,1)' onClick='WdatePicker()'/>"
									      +"</tr>"
									   +"</table>"
						    		        +"<div class='name_con ' id='wuyong5_1'>"
								        	  +"<div class='fl neirong ml70 mt10' id='wuyong6_1'>"
												 +"<div  style='height:120px; margin-top:0px; width:100%;pxoverflow:auto;' id='"+studentid+"_1_content' idvalue='${listat.apprasial}' >"
														+"<textarea id='"+studentid+"_new_apprise_content_xie' style='background: transparent; overflow:auto; border-style: none; color:#ACACAC; height: 120px; width: "+textWidth+";' onclick='return change_content_is_availableNll(0,"+studentid+",1,true);'>最多输入4000字</textarea>"
												+"</div>"
												+"</div>"
								            	+"<div class='fr btnxie' style='width:"+width+";' id='wuyong7_1'>"
								            	     +"<div class='xiezhong' id='wuyong8_1'>"
								            	  		+"<a id='btnxie_001' href='#' onclick='javascript:delectNull(0,"+studentid+");'  class='local delect'></a>"
								            	     +"</div>"
								            	+"</div>"
								        +"</div>"
								      +"</div>";

									 var this_divAddxie =document.getElementById(studentid+"_xie");
										this_divAddxie.innerHTML='';
										this_divAddxie.innerHTML=fistDiv;
										
						    	}
						    	else if(num>=orderNum){
						    		
						    		$("#"+studentid+"_"+num+"_dele").remove();
						    		var divs=$("#"+studentid+"_xie").find("div");
						    		for(var i=0;i<divs.size();++i)
									{    
										 var id_content=$(divs[i]).attr("id");
										 if(id_content.indexOf("_dele")>=0) 
										 {  
											 ++pNum;
											 var arr = new Array();
											 arr[pNum]=id_content;
											 var  lengthpid=id_content.split("_"); //字符分割 
											 var  lengthpidAdd=lengthpid[1];
											
											
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").html("");
										    $("#"+studentid+"_"+lengthpidAdd+"_shu").html(pNum);
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").val("");
										    $("#"+studentid+"_"+lengthpidAdd+"_shu").val(pNum);
										 
										 } 
									}
						    		   var lengthDiv=arr[pNum];
										var  lengthpid=lengthDiv.split("_"); //字符分割 
										var  lengthpidAdd=lengthpid[1];
										
										 //(lengthpidAdd);id="${listat.studentid}_${status.index+1}_add"
										 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
										 
						    	}else{   
						    		$("#"+studentid+"_"+num+"_dele").remove();
						    		var xianNum=0;
						    		var divshuli=$("#"+studentid+"_xie").find("div");
							    	for(var i=0;i<divshuli.size();++i)
									{ 
										var id_content=$(divshuli[i]).attr("id");
										 if(id_content.indexOf("_dele")>=0) 
										 {   
											 ++xianNum;
											 var  lengthpid=id_content.split("_"); //字符分割 
											 var  lengthpidAdd=lengthpid[1];
											
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").html("");
										    $("#"+studentid+"_"+lengthpidAdd+"_shu").html(xianNum);
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").val("");
										    $("#"+studentid+"_"+lengthpidAdd+"_shu").val(xianNum);
										 } 
									}
						    	}
					    }else if(success=="false")
					    {
					    	this_div.focus();
					    	return;
					    }
				},
				params : {
				     apprasialid :apprasialid,
					 id:studentid,
					 evaluateType :evaluateType
				}
			});
		 }  
		 }
	//删除初始没有评价时的评价
	 function delectNull(apprasialid,studentid) {
		   
		if(confirm( "确定要删除?" ))
		  {     
			    var appr=$("#idcontent").html();
			    if(appr!=null&&appr!=" "&&appr!=""){
			    	var length=appr.trim().length;
			    	if(length>0){
			    	  if(length>4000){
			    		var this_div = document.getElementById(studentid+"_1_content");
						this_div.setAttribute("idvalue","");				
						this_div.innerText="";     
						this_div.parentNode.onclick=function(){change_content_is_availableNll(apprasialid,studentid,1,true);}
						 this_div.setAttribute("style","color:#ACACAC;font-size:12px;");
						this_div.innerText="  最多输入4000字"; 
						
			    	  }else{
			    		 
			    		  dectestatic="delct";}	
		       }
		     }else{
		    	 apprasial_del_Finish();
		     }
		  }  
		 
	}
	 
	 //删除新添加的
	 function delectNewAdd(){
		  if(confirm( "确定要删除?" ))
		  {   
			  var appr=$("#new_apprise_content").html();
			  if(appr=="最多输入4000字"){
			  var studentid=$("#new_studentid").val();
				var name=$("#"+studentid+"_name").html();
				 alert_delete_success(name);
			  $("#new_apprise").css("display","none");
			  var studentid=$("#new_studentid").val();
			  var pNum=0;
	    	    var divs=$("#"+studentid+"_xie").find("div");
	    	    for(var i=0;i<divs.size();++i)
              { 
              	 var id_content=$(divs[i]).attr("id");
              	 if(id_content.indexOf("_dele")>=0) 
              	 {   ++pNum;
              		 var arr = new Array();
              		 arr[pNum]=id_content;
              	 } 
              }
                 var lengthDiv=arr[pNum];
              	var  lengthpid=lengthDiv.split("_"); //字符分割 
              	var  lengthpidAdd=lengthpid[1];
              	
              	 //(lengthpidAdd);id="${listat.studentid}_${status.index+1}_add"
              	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
			  
			  
			  return;
		    }
			    if(appr!=null&&appr!=" "&&appr!=""){
			    	
			    	var length=appr.trim().length;
			    	if(length>0){
			    	   if(length>4000){
			    		   var studentid=$("#new_studentid").val();
							var name=$("#"+studentid+"_name").html();
							 alert_delete_success(name);
					    	$("#new_apprise").css("display","none");	
					    	 var studentid=$("#new_studentid").val(); 
					    	 var pNum=0;
					    	    var divs=$("#"+studentid+"_xie").find("div");
					    	    for(var i=0;i<divs.size();++i)
			                  { 
			                  	 var id_content=$(divs[i]).attr("id");
			                  	 if(id_content.indexOf("_dele")>=0) 
			                  	 {   ++pNum;
			                  		 var arr = new Array();
			                  		 arr[pNum]=id_content;
			                  	 } 
			                  }
			                     var lengthDiv=arr[pNum];
			                  	var  lengthpid=lengthDiv.split("_"); //字符分割 
			                  	var  lengthpidAdd=lengthpid[1];
			                  	
			                  	 //(lengthpidAdd);id="${listat.studentid}_${status.index+1}_add"
			                  	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
			    		   
			    	   }else{dectestatic="delct";}
			    	
			   }
		    }else{  
		    	var studentid=$("#new_studentid").val();
				var name=$("#"+studentid+"_name").html();
				 alert_delete_success(name);
		    	$("#new_apprise").css("display","none");	
		    	 var studentid=$("#new_studentid").val(); 
		    	 var pNum=0;
		    	    var divs=$("#"+studentid+"_xie").find("div");
		    	    for(var i=0;i<divs.size();++i)
                  { 
                  	 var id_content=$(divs[i]).attr("id");
                  	 if(id_content.indexOf("_dele")>=0) 
                  	 {   ++pNum;
                  		 var arr = new Array();
                  		 arr[pNum]=id_content;
                  	 } 
                  }
                     var lengthDiv=arr[pNum];
                  	var  lengthpid=lengthDiv.split("_"); //字符分割 
                  	var  lengthpidAdd=lengthpid[1];
                  	
                  	 //(lengthpidAdd);id="${listat.studentid}_${status.index+1}_add"
                  	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
		    }
		  
		  }   
		 
	}
	//更改学期刷新页面
		function changeterm(){
			 ShowDiv(); 
			var term = document.getElementById("termId").value;
			var evaluateType= document.getElementById("evaluateType").value;
			url="${ctx}/student/PlayAndHealthAction.a?chaneTermId&evaluateType="+evaluateType+"&termId1="+term;
			document.location.replace(url);
		}
	
		//更改时间
		function update_time(stuedid,id,num)
		{  
		   if(id==null||id==""||id==0)
			{
				
			}else{
				var time1=$("#"+stuedid+"_"+id+"_"+num+"_Date").val();
				var time2=$("#"+stuedid+"_"+id+"_"+num+"_Date").attr("idvalue");
				if(time1!=time2)
				{    
					var name=$("#"+stuedid+"_name").html();
					var xianshu=$("#"+stuedid+"_"+num+"_shu").html();
				       alert_update_success(name,xianshu);
		    		$("#"+stuedid+"_"+id+"_"+num+"_Date").attr("idvalue",time1);
					Ext.Ajax.request({
						url:'${ctx}/student/ZtreeAction.a?doUpadateTime',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
						},
						params : {
							apprasialid:id,
							signDate:time1
						}
					});
					
				}	
			}
			
		}
		function romvezi(){
			$("#new_apprise_content").css("color","");
			$("#new_apprise_content").val("");
			$("#new_apprise_content").unbind('click');
			$("#new_apprise_content").removeAttr("onclick");
			document.getElementById("new_apprise_content").style.border ="1px solid black";
		}
		
</script>  
<body margin="0" id="text_style">
<%@ include file="/common/div.jsp"%>  
   <div class="dangqianwz">
 	<span>当前位置：评价同学->
        	<c:if test="${evaluateType eq 3020}">思想道德&nbsp;</c:if>
           	<c:if test="${evaluateType eq 8040}">学业成就&nbsp;</c:if>
		 	<c:if test="${evaluateType eq 4020}">合作与交流&nbsp;</c:if>
			<c:if test="${evaluateType eq 5020}">运动与健康&nbsp;</c:if>
			<c:if test="${evaluateType eq 6020}">审美与表现&nbsp;</c:if>
			<c:if test="${evaluateType eq 7030}">个性发展&nbsp;</c:if>
 	</span>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <span >姓名:
     	<input style=" width:129px;  border:1px #666 solid;" type="text" id="lookForId" size="32"/>
      </span>
 	
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	 <span >  学期: 
    	 <select style="border:1px #666 solid;"  name="termId" id="termId" onchange="changeterm()">
               <app:highSchoolTermTag  levelCode="${levelCode}" selectNum="${termId}" selectClassid="${classid}"/> 
        </select> 
    </span>
      
 </div>
  <div id="pj_jiaoshi_main">
 
<form name="otherstartForm" action="OtherAppraiseAction.a" method="post">
            <div id="tianjian"></div>
		<input id="evaluateType" type="hidden" name="evaluateType" value="${evaluateType}"/>
		<input id="evaluateid"  type="hidden" name="evaluateid" value="${evaluateid}"/> 
	   <div id="new_apprise" style='display:none ;margin-left:50px;'>
	    		<input id="new_studentid"  type="hidden" name="new_studentid" value=""/> 
	        	<input id="new_numshu"  type="hidden"  value=""/> 
	        	 
	        	  <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="new_shu" width="10%" height="40" class="xiechengsheng3"></td>
			                <td  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							                  日期：<input  id="newDate"  type="text" size="10" value="${time}" idvalue="${time}" onClick="WdatePicker()"/></td>
		                 </tr>
		              </table>
	        	<div class="name_con " id="new1_1">
		        	  <div class="fl neirong ml70 mt10" id="new2_1">
		        			 <textarea   id="new_apprise_content"  style="height:120px; width:99%; overflow:auto; overflow:auto; " onclick="romvezi()" onblur="return saveApprasial()"></textarea> 
						</div>          
	        	         <div class="fr btnxie" id="new3_1">
		            	  	 <div class="xiezhong" id="new4_1"> 	
		            	  		<a id="btnxie_001" href="#" onclick="javascript:delectNewAdd();" class="local delect"></a>
		            	   </div>
		            	</div>
	        	</div>
	           <div class="name_cxbox " id="new5_1">
	                 <div class="name_cxright fr" id="new10_1">
					       <div class="fr btn">
					       	<a href="#"  class="fr zengjia mr100"  ></a> 
					      </div>
				   	 </div>
	        </div> 
	</div> 
	
 <c:forEach items="${listApraisal}" var="stud">
 			
 			 <a id="${stud.name}_${stud.eduid}"></a>
			<%-- <c:set var="columnCount" value="${status.count}" /> --%>
				 <div class="layout" >
				    <br/>
				     <br/>
		    		 <div class="name_pic" >
		    		  <img class="img-padding" src="${stud.photoUrl}"/>
		    		 </div> 
		    		<div class="title_xie" id="${stud.studentid}_name">${stud.name} </div>
		        <div id="${stud.studentid}_xie" style="margin-left:50px;" >
		        <c:forEach items="${stud.aInfos}" var="listat"  varStatus="status">
		        	 <div  id="${listat.studentid}_${status.index+1}_dele" style="width:100%">
		        	 <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="${listat.studentid}_${status.index+1}_shu" width="10%" height="40" class="xiechengsheng3">${status.index+1}</td>
			                <td width="90%"  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <c:if test="${not empty listat.apprasial}">                                            
							                  日期：<input  id="${listat.studentid}_${listat.apprasialid}_${status.index+1}_Date"  type="text" size="10" value="${listat.signDate}" size="10" value="${listat.signDate}" idvalue="${listat.signDate}" onblur="update_time(${listat.studentid},${listat.apprasialid},${status.index+1})" onClick="WdatePicker()" />
		                       </c:if>
                               <c:if test="${empty listat.apprasial}">                                            
							                  日期：<input  id="${listat.studentid}_${listat.apprasialid}_${status.index+1}_Date"  type="text" size="10" value="${time}" idvalue="${time}" onClick="WdatePicker()"/>
		                       </c:if></td>
		                 </tr>
		              </table>
		        	<c:if test="${not empty listat.apprasial}">
		        	<div class="name_con " style="background:#eee;" id="wuyong1_${status.index+1}">
		        	  <div class="fl neirong ml70 mt10" id="wuyong" id="wuyong2_${status.index+1}">
						 <div  style="height:120px; margin-top:0px; width:100%;pxoverflow:auto;" id="${listat.studentid}_${status.index+1}_content" idvalue="${listat.apprasial}" >
							<textarea id= "${listat.studentid}_${status.index+1}_appered" style="background: transparent; overflow:auto; border-style: none; height: 120px; width: 99%;" onclick="return change_content_is_available(${listat.apprasialid},${listat.studentid},${status.index+1},true);">${listat.apprasial}</textarea>
						</div>
						</div>
		            	<div class="fr btnxie" id="wuyong3_${status.index+1}">
		            	  	 <div class="xiezhong" id="wuyong4_${status.index+1}"> 	
		            	  		<a id="btnxie_001" href="#" onclick="javascript:removeAppraise(${listat.apprasialid},${listat.studentid},${status.index+1});" class="local delect"></a>
		            	   </div>
		            	</div>
		             </div>
		        </c:if>
		        
		        <c:if test="${empty listat.apprasial}">
		          <div class="name_con " id="wuyong5_${status.index+1}">
		        	  <div class="fl neirong ml70 mt10" id="wuyong6_${status.index+1}">
		        	 <!--  background:red; -->
						 <div  style="height:120px; margin-top:0px; width:100%;pxoverflow:auto;" id="${listat.studentid}_${status.index+1}_content" idvalue="${listat.apprasial}" >
								<textarea id="${listat.studentid}_new_apprise_content_xie" style="background: transparent; border-style: none; height: 120px; width: 99%;" onclick="return change_content_is_availableNll(${listat.apprasialid},${listat.studentid},${status.index+1},true);">${listat.apprasial}</textarea>
						</div>
						</div>
		            	<div class="fr btnxie" id="wuyong7_${status.index+1}">
		            	     <div class="xiezhong" id="wuyong8_${status.index+1}">
		            	  		<a id="btnxie_001" href="#" onclick="javascript:delectNull(${listat.apprasialid},${listat.studentid});"  class="local delect"></a>
		            	     </div>
		            	</div>
		        </div>
		        </c:if>
		          
		          
		          
		       <c:if test="${status.count==fn:length(stud.aInfos)}"> 
				    <c:if test="${not empty listat.apprasial}"> 
				         <div class="name_cxbox "  id="${listat.studentid}_${status.index+1}_add" >
				            	<div  class="name_cxright fr" id="wuyong9_${status.index+1}">
							          <div id="wuyong24_${status.index+1}" class="fr btn">
							       				 <a href="#" id="add" class="fr zengjia mr100" onclick="addApprasial(${listat.studentid},${status.index+1});" ></a> 
							           </div>
						   		</div>
				        </div>  
			           </c:if> 
		         </c:if> 
		      <c:if test="${status.count!=fn:length(stud.aInfos)}">
			         <div class="name_cxbox"  id="${listat.studentid}_${status.index+1}_add"  style="display: none;">
			            	<div  class="name_cxright fr" id="wuyong9_${status.index+1}" >
						          <div id="wuyong24_${status.index+1}" class="fr btn">
						       				 <a href="#" id="add" class="fr zengjia mr100" onclick="addApprasial(${listat.studentid},${status.index+1});" ></a> 
						           </div>
					   		</div>
			        </div> 
		          </c:if>  
		      
		      </div>
		   </c:forEach>
			</div>			
			   
			
		 </div>
		 <!--  </div>  -->
	    </c:forEach>
     
 </form>
     </div> 
</body>



