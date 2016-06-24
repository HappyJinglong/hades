<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 <%@ include file="/common/mass.jsp"%>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link href="${ctx}/css/iframe.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" /> 
 <link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
  <script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
  <script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>



<style type="text/css">
body,html{ width:100%; height:100%;
     overflow-x:hidden;
	 overflow-y:hidden; 
}
.mr100{
	margin-right:18px;
}
#pj_jiaoshi_main {
    bottom: 0;
    left: 0px;
    width:95%;
    overflow: auto;
    padding-top: 30px;
}

#pj_jiaoshi_main .name_con .btnxie{
border-top-width: 0px;
border-bottom-width: 0px;
border-right-width: 0px;
height:131px
}
.local{
 float:left; 
}
#pj_jiaoshi_main .name_con .btnxie .delect{
  width:65px; 
  height:66px; 
  background:url(../images/jingling_icon1.png) no-repeat -64px 0; margin-top:30px; cursor:pointer;
}
#pj_jiaoshi_main .name_con .xiezhong{
  height:120px;
  width:100px
 padding-left:28px; 
}
.mt10{margin-top:0px;}
inline style{width:99%;}
.ml70{margin-left: 0px;}
A{margin-top: 10px;}
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
  top:20px;
  padding-right: 10px;
   padding-left: 0px;
   width:98.79%;
  padding-top:0px;
}

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
$(document).ready(function(){
	 $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "${ctx}/student/CzPlayAndHealthAction.a?queryData",
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
					 $("#"+id_content).val("最多输入600字");
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
					 $("#"+id_content).val("最多输入600字");
				 } 
			}
	}

	
	function romvezi(){
		$("#new_apprise_content").css("color","");
		$("#new_apprise_content").val("");
		$("#new_apprise_content").unbind('click');
		$("#new_apprise_content").removeAttr("onclick");
		text_style1("new_apprise_content");
		
		//id="new_apprise_content"  style="height:120px; width:93%;overflow:auto;border:solid 1px;"
	
	}
	

	function addApprasial(studentid,num){ 
		           flag_full=true;
					var newDiv = Ext.get('new_apprise');
					newDiv.setDisplayed("");
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
					    	var xianshishu=parseInt(orderNum)+parseInt(1);
					    	$("#new_shu").html(xianshishu);
					 $("#new_studentid").val(studentid);
					 $("#new_numshu").val(num);
					 var ss=$('#'+studentid+'_'+num+'_addxie').parent();
					 var ssf=ss.parent();
					//var ssff=ssarent();
					$("#new_apprise").insertAfter(ssf); 
					$('#'+studentid+'_'+num+'_addxie').css("display","none");  
					readying();
	}
	
	 function save_process(){
		 var textWidth = $("textarea").css("width");
		 setTimeout(function () { 
		 var trimApprasial=$("#new_apprise_content").val();
		 if(trimApprasial=="最多输入600字"){
	        return;    	
		 }
		 document.getElementById("new_apprise_content").style.border ="0px solid";
		var leng=$("#new_apprise_content").val();
		var trimLeng=leng.trim().length;
		var ss=leng.length;
		var xianStudentid=$("#new_studentid").val();
		var name1=$("#"+xianStudentid+"_name").html();
		if(trimLeng>0){
		if(ss<=600){
				$("#new_apprise_content").bind('click',function(){
		 			romvezi();
		 		}); 
			Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	    	Ext.Ajax.request({
				url:'CzPlayAndHealthAction.a?doUpdataOtherProcessNull',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					  var kk=document.getElementById("new_studentid").value;
					  var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var info=len.info;
					    var pid=len.partid;
					    if(success="true")
					    {   
					    	
					    	var evaluatedPersonID=document.getElementById("new_studentid").value;
					    	  if(dectestatic=="delct"){
					    		        
						       			Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
							    		Ext.Ajax.request({
										url:'${ctx}/student/CzPlayAndHealthAction.a?deleApprasial',
										method:'POST',
										defaults:{autoScroll: true},
										success:function(response,options){
											
					    		            alert_delete_success(name1);
											   var pNum=0;
											  var len=Ext.util.JSON.decode(response.responseText);
											    var success=len.success.replace( /^\s+|\s+$/g,"");
											    var info=len.info;
											    if(success="true")
											    {   
											    	dectestatic="notdelct";
											    	 //$("#new_apprise").insertAfter(yin_div); 
											    	var divs=$("#"+evaluatedPersonID+"_xie").find("div");
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
														var  lengthpidAdd=lengthpid[2];
														$("#"+evaluatedPersonID+"_"+lengthpidAdd+"_addxie").css("display","block");
														$('#new_apprise').css("display","none");
														
														 readying();
											    }else if(success=="false")
											    {
											    	return;
											    }
											    
										},
										params : {
											part_id:pid
										}
									});
						       			
						       		}else{
										    	var part_info=document.getElementById("new_apprise_content").value;
										        
										        var two_part_id=document.getElementById("evaluateType").value;
										        var newnum1=document.getElementById("new_numshu").value;
										        var newnum=parseInt(newnum1)+parseInt(1);
										        var name=$("#new_name").html();
										        var createDate=$("#newxie_time").val();
										       
										        
										        var orderNum=$("#new_shu").html();
										        var newSuccessdiv= "<div  id='"+pid+"_dele_"+newnum+"'>"
										        +"<table  class='xiechengsheng4' cellspacing='0'>" 
										       		 +"<tr>" 
										        		+"<td id='"+evaluatedPersonID+"_"+newnum+"_shu' width='10%' height='40' class='xiechengsheng3'>"+orderNum+"</td>"
										        		+"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
										       				 +"  日期： <input id='"+evaluatedPersonID+"_"+pid+"_"+newnum+"_Date' type='text' size='10' value="+createDate+" idvalue="+createDate+" onblur='update_time("+evaluatedPersonID+","+pid+","+newnum+")' onClick='WdatePicker()'/>"
														+"</td>"
													 +" </tr>"
												+" </table>"
										        
										        +"<div class='name_con ' style='background:#eee;' id='"+evaluatedPersonID+"_ "+newnum+"_boder'>"
											        	    +"<div class='fl neirong ml70 mt10' id='wuyong1_"+newnum+"' >"                         
											        	         +"<div  id='"+evaluatedPersonID+"_"+newnum+"_content' style='height:120px; margin-top:0px;  width:100%;pxoverflow:auto;'    idvalue="+part_info+">" 
											                       +"<textarea id='apperedxiel_"+evaluatedPersonID+"_ "+newnum+"' style='background: transparent; border-style: none; height: 120px; width: "+textWidth+";' onclick='return change_content_is_available("+pid+","+evaluatedPersonID+","+newnum+",true);'>"+part_info+"</textarea>"
														        +"</div>" 
													       +"</div>"
											               +"<div class='fr btnxie' style='width:84px;' id='wuyong2_"+newnum+"'>"
											               +" <div class='xiezhong' id='wuyong3_"+newnum+"'>" 
											                    +"<a href='#' onclick='javascript:removeAppraise("+pid+","+evaluatedPersonID+","+newnum+");' class='local delect'></a>"
											        	      +"</div>"
											            	+"</div>"
											          +"</div>"
											           +"<div class='name_cxbox ' id='"+evaluatedPersonID+"_"+newnum+"_addxie' >"
											                   +"<div  id='wuyong4_"+newnum+"'>"
												                 +"<div class='btnxie' id='wuyong10_"+newnum+"'>"
													                +"<div class='xiezhong' id='wuyong11_"+newnum+"'>"
													       				 +"<a href='#' id='add_"+newnum+"' class='fr zengjia mr100' onclick='addApprasial("+evaluatedPersonID+","+newnum+");' ></a>" 
													                 +"</div>"
												                 +"</div>"
											                +"</div>"
											          +"</div>"  
											          +"</div>";
											          var yin_div = $("#cun");//隐藏储备div
											          var xianshi= document.getElementById("xian");
													 
											                xianshi.innerHTML=newSuccessdiv; 
															
											                var newDiv = Ext.get("new_apprise");
															//newDiv.setDisplayed("");
															
															$('#new_apprise').css("display","none"); 
															 $("#new_apprise").insertAfter(yin_div); 
															var ss=$('#'+evaluatedPersonID+'_'+newnum1+'_addxie').parent();
															//var ssf=ss.parent();
															$("#"+pid+"_dele_"+newnum).insertAfter(ss); 
															if(dectestatic!="delct"){
													    		alert_save_success(name1,orderNum);
													    	}
															readying();
						       		         
						       		          }
										    }else if(success=="false")
										    {
										    	alert_g(info);
										    	this_div.focus();
										    	return;
										    }
										    var content=len.content;
											//update_div(kk,content);
											readying();
				
				},
				params : {//newxie_time
					part_info:document.getElementById("new_apprise_content").value,
					 evaluatedPersonID:document.getElementById("new_studentid").value,
					 two_part_id : document.getElementById("evaluateType").value,
					 createDate:$("#newxie_time").val()
				}
			});
		
		}else{
			alert("评价内容已超过600字");
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
	 function removeNewAdd(){
		 if(confirm( "确定要删除?" )) { 
	     var apper=$("#new_apprise_content").val();
		var length=apper.trim().length;
	     if(length>0){
	    	 var trimLength=apper.length;
	 	    if(apper=="最多输入600字"||trimLength>600){
	 	    	$("#new_apprise_content").bind('click',function(){
		 			romvezi();
		 		}); 
	 	    	var xianStudentid=$("#new_studentid").val();
	 			var name1=$("#"+xianStudentid+"_name").html();
	 	    	alert_delete_success(name1);
	 	    	$('#new_apprise').css("display","none");
	 	    	var evaluatedPersonID=document.getElementById("new_studentid").value;
	 	    	var pNum=0;
	 	    	 var divs=$("#"+evaluatedPersonID+"_xie").find("div");
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
	 					var  lengthpidAdd=lengthpid[2];
	 					$("#"+evaluatedPersonID+"_"+lengthpidAdd+"_addxie").css("display","block");
	 	    }else{
	    	 dectestatic="delct";}
	     }else{
	    	 var xianStudentid=$("#new_studentid").val();
	 		var name1=$("#"+xianStudentid+"_name").html();
	    	 alert_delete_success(name1);
	    	 $('#new_apprise').css("display","none");
	    	 var evaluatedPersonID=document.getElementById("new_studentid").value;
	    	  var pNum=0;
	    	 var divs=$("#"+evaluatedPersonID+"_xie").find("div");
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
					var  lengthpidAdd=lengthpid[2];
					$("#"+evaluatedPersonID+"_"+lengthpidAdd+"_addxie").css("display","block");
	    	 
	    	 
	     }
	 }
	 }
	//更改评价
	var content_div_full="";
	function change_content_is_available(apprid,studentid,id,flag){
		var textWidth = $("textarea").css("width");
		if(id==null||id==""){
		}else{
			if(flag==true){ 
				var this_div =document.getElementById(studentid+"_"+id+"_content");
				this_div.focus();
				this_div.parentNode.onclick=null;  
				$("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#fff");
				this_div.innerHTML=''; 
				var str='<textarea id="apperedxiel_'+studentid+'_'+id+'"  style="height:120px; width:'+textWidth+'; overflow:auto;border:solid 1px;"  onblur="return change_content_is_available('+apprid+','+studentid+','+id+',false)"></textarea>';
				this_div.innerHTML=str;
				document.getElementById("apperedxiel_"+studentid+"_"+id).value=this_div.attributes["idvalue"].nodeValue;
				document.getElementById("apperedxiel_"+studentid+"_"+id).focus();
				$("apperedxiel_"+studentid+"_"+id).focus();
				content_div_full=$("#apperedxiel_"+studentid+"_"+id).val();
			}else{
				var this_div =document.getElementById(studentid+"_"+id+"_content");
				var app=$("#apperedxiel_"+studentid+"_"+id).val().trim().length;
				var apple=$("#apperedxiel_"+studentid+'_'+id).val();
				var leng=$("#apperedxiel_"+studentid+"_"+id).val().length;
				if(leng<=600){
					if(app!=0){
				if(apple!=content_div_full){
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'CzPlayAndHealthAction.a?doUpdataOtherProcess',
						method:'POST',
						success:function(response,options){
							  var len=Ext.util.JSON.decode(response.responseText);
							    var success=len.success.replace( /^\s+|\s+$/g,"");
							    var info=len.info;
							    if(success="true")
							    {    
							    	var name1=$("#"+studentid+"_name").html();
							    	alert_update_success(name1,"");
							    	//this_div.setAttribute("style","border:3px solid red;background:transparent;  height: 120px; width: 100%;");
							    	//alert_g(info);<---提示没有写
									$("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#eee");
							    	 var ppaer=$("#apperedxiel_"+studentid+"_"+id).val();
							    	 this_div.setAttribute("idvalue",ppaer);				
							    	 this_div.setAttribute("style","height:130px; width:100%; margin-top:0px;  pxoverflow:auto;");
									  this_div.innerHTML=''; 
									  this_div.innerHTML="<textarea id='apperedxiel_"+studentid+"_"+id+"' style='background: transparent; border-style: none; height: 120px; width:"+textWidth+";' onclick='return change_content_is_available("+apprid+","+studentid+","+id+",true);'>"+ppaer+"</textarea>"
							    }else if(success=="false")
							    {
							    	//alert_g(info);
							    	//this_div.focus();
							    	return;
							    }
							   // var content=len.content;
						},
						params : {
							evaluatedPersonID:studentid,
							part_id:apprid,
							part_info:$("#apperedxiel_"+studentid+"_"+id).val(),
							two_part_id:document.getElementById("evaluateType").value
						}
					});
					}else{  
					         var ppaer=$("#apperedxiel_"+studentid+"_"+id).val();
						     $("#"+studentid+"_"+id+"_content").parent().parent().css("background-color", "#eee");
							  this_div.innerHTML=''; 
							  this_div.innerHTML="<textarea id='apperedxiel_"+studentid+"_"+id+"' style='background: transparent; border-style: none; height: 120px; width:"+textWidth+";' onclick='return change_content_is_available("+apprid+","+studentid+","+id+",true);'>"+ppaer+"</textarea>"
					    
					}
				
				}
				}else{
						  alert("评价内容已超过600");
					  }
					}
				} 
			}
	
	//初次内容为空时的更改
			var content_div_full=""; 
			function change_content_is_availableNull(apprid,studentid,id,flag){
				var textWidth = $("textarea").css("width");
				var name1=$("#"+studentid+"_name").html();
				if(id==null||id==""){
				}else{
					var this_div = document.getElementById(studentid+"_"+id+"_content");
					this_div.focus();
					if(flag==true){ 
						this_div.parentNode.onclick=null;  
						this_div.innerHTML='';
						var str='<textarea id="apperedxiel_'+studentid+'_'+id+'"  style="height:120px; width:'+textWidth+'; overflow:auto;border:solid 1px;"  onblur="return change_content_is_availableNull('+apprid+','+studentid+','+id+',false)"></textarea>'
						this_div.innerHTML=str;
						document.getElementById("apperedxiel_"+studentid+"_"+id).focus();
						$("apperedxiel_"+studentid+"_"+id).focus();
						document.getElementById("apperedxiel_"+studentid+"_"+id).value=this_div.attributes["idvalue"].nodeValue;
						//content_div_full=document.getElementById("apperedxiel_"+studentid+"_"+id).innerText;
					}else{
						var app=$("#apperedxiel_"+studentid+"_"+id).val().trim();
						 if(app=="最多输入600字"){
					        return;    	
						 }
						var app=$("#apperedxiel_"+studentid+"_"+id).val().trim();
						var leng=app.length;
						var leng2=$("#apperedxiel_"+studentid+"_"+id).val().length;
						
						if(leng>0){
						if(leng2<=600){
							setTimeout(function () { 
							
							Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
							Ext.Ajax.request({
								url:'CzPlayAndHealthAction.a?doUpdataOtherProcessNull',
								method:'POST',
								success:function(response,options){
									  var len=Ext.util.JSON.decode(response.responseText);
									    var success=len.success.replace( /^\s+|\s+$/g,"");
									    var info=len.info;
									     var pid=len.partid;
									     if(success="true")
									    {    
									    	 if(dectestatic!="delct"){
									    		 alert_save_success(name1,1);
									    	 }
											if(dectestatic=="delct"){
								       			Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
									    		Ext.Ajax.request({
												url:'${ctx}/student/CzPlayAndHealthAction.a?deleApprasial',
												method:'POST',
												defaults:{autoScroll: true},
												success:function(response,options){
									    		 alert_delete_success(name1);
													   
													  var len=Ext.util.JSON.decode(response.responseText);
													    var success=len.success.replace( /^\s+|\s+$/g,"");
													    var info=len.info;
													    if(success="true")
													    {   
													    	dectestatic="notdelct"; 
													    	 var fistDiv="<div  id='0_dele_1'>"
														          
													    		   +"<table  class='xiechengsheng4' cellspacing='0'>" 
														       		 +"<tr>" 
														        		+"<td id='"+studentid+"_0_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
														        		+"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
														       				 +"  日期： <input id='"+studentid+"_0_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time("+studentid+",0,1)' onClick='WdatePicker()'/>" 
																		+"</td>"
																	 +" </tr>"
																+" </table>"
														             
													    	 
													    	 +"<div class='name_con ' id='wuyong4_1'>"
														        	    +"<div class='fl neirong ml70 mt10'  id='wuyong5_1}'>"
																		  +"<div  style='height:120px; margin-top:0px;  width:100%;pxoverflow:auto;'  id='"+studentid+"_1_content' idvalue=''>" 
																				+"<textarea id='new_apprise_content_xiel_"+studentid+"_1' style='background: transparent; border-style: none; height: 120px; width: "+textWidth+";' onclick='return change_content_is_availableNull(0,"+studentid+",1,true)'></textarea>"
																	      +"</div> "
																		+"</div>"
														            	+"<div class='fr btnxie' style='width:84px;' id='wuyong6_1'>"
														            	        +"<div class='xiezhong' id='wuyong7_1'>"
														            	  		+"<a id='null_"+studentid+"_delect' href='#' onclick='javascript:removeAppraiseNull(0,"+studentid+",1);' class='local delect'></a>"
																		       +"</div>"
														            	+"</div>"
														        +"</div>"
														          +"<div class='name_cxbox ' id='"+studentid+"_1_addxie' style='display:none' >"
														            +"<div id='wuyong8_1'>"
														            +"<div class='btnxie' id='wuyong13_1'>"
														                +"<div class='xiezhong'  id='wuyong14_1'>"
														                      +"<a href='#' id='add_1' class='fr zengjia mr100' onclick='addApprasial("+studentid+",1);' ></a>" 
														                +"</div>"
														            +"</div>"
														       +"</div>" 
														          +"</div>"
														         +"</div>"; 
														        
																 var this_divAddxie =document.getElementById(studentid+"_xie");
																	this_divAddxie.innerHTML='';
																	this_divAddxie.innerHTML=fistDiv;
																	readying();
													    }else if(success=="false")
													    {
													    	//alert_g(info);
													    	//this_div.focus();
													    	return;
													    }
													    
												},
												params : {
													part_id:pid
												}
											});
								       			
								       		}else{
								       			  /* $("#"+studentid+"_"+id+"_addxie").css("display","block");
										    	 parentDiv=document.getElementById(studentid+"_"+id+"_content"); 
										    	 parentDivf=parentDiv.parentNode;
										    	 parentDivff=parentDivf.parentNode;
										    	 parentDivff.setAttribute("style","background:#eee;");
										    	//alert_g(info);<---提示没有写 '+apprid+',
										    	this_div.setAttribute("idvalue",document.getElementById("apperedxiel_"+studentid+"_"+id).value);				
												  var part_info=document.getElementById("apperedxiel_"+studentid+'_'+id).value;
												 this_div.innerHTML=''; 
												 this_div.innerHTML="<textarea id='apperedxiel_"+studentid+"_"+id+"' style='background: transparent; border-style: none; height: 120px; width:99%;' onclick='return change_content_is_available("+apprid+","+studentid+","+id+",true);'>"+part_info+"</textarea>"
												var delecrNull=document.getElementById("null_"+studentid+"_delect");
												delecrNull.onclick=function(){removeAppraise(pid,studentid,id)};
												 readying(); */
								       		
								       		
								       		
												 var createDate=$("#"+studentid+"_"+apprid+"_"+id+"_Date" ).val();
												  var part_info=document.getElementById("apperedxiel_"+studentid+'_'+id).value;
												 var fistDiv="<div  id='"+pid+"_dele_1'>"
										    		   +"<table  class='xiechengsheng4' cellspacing='0'>" 
											       		 +"<tr>" 
											        		+"<td id='"+studentid+"_1_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
											        		+"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
											       				 +"  日期： <input id='"+studentid+"_"+pid+"_1_Date' type='text' size='10' value="+createDate+" idvalue='"+createDate+"' onblur='update_time("+studentid+","+pid+",1)' onClick='WdatePicker()'/>" 
															+"</td>"
														 +" </tr>"
													+" </table>"
										   	 +"<div class='name_con ' style='background:#eee;' id='wuyong4_1'>"
											        	  +"<div class='fl neirong ml70 mt10'  id='wuyong5_1}'>"
															  +"<div  style='height:130px; margin-top:0px;  width:100%;pxoverflow:auto;'  id='"+studentid+"_1_content' idvalue='"+part_info+"'>" 
																	+"<textarea id='apperedxiel_"+studentid+"_1' style='background: transparent; border-style: none; height: 120px; width: "+textWidth+";' onclick='return change_content_is_available("+pid+","+studentid+",1,true)'>"+part_info+"</textarea>"
														      +"</div> "     
															+"</div>" 
											            	+"<div class='fr btnxie' style='width:84px;' id='wuyong6_1'>"
											            	        +"<div class='xiezhong' id='wuyong7_1'>"
											            	  		+"<a id='null_"+studentid+"_delect' href='#' onclick='javascript:removeAppraise("+pid+","+studentid+",1);' class='local delect'></a>"
															       +"</div>"
											            	+"</div>"
											        +"</div>"
											        
											       +"<div class='name_cxbox ' id='"+studentid+"_1_addxie'>"
											            +"<div id='wuyong8_1'>"
											            +"<div class='btnxie' id='wuyong13_1'>"
											                +"<div class='xiezhong'  id='wuyong14_1'>"
											                      +"<a href='#' id='add_1' class='fr zengjia mr100' onclick='addApprasial("+studentid+",1);' ></a>" 
											                +"</div>"
											            +"</div>"
											       +"</div>" 
											          +"</div>"
											          +"</div>"; 
													 var this_divAddxie =document.getElementById(studentid+"_xie");
														this_divAddxie.innerHTML='';
														this_divAddxie.innerHTML=fistDiv; 
								       		}
				                             
									    }else if(success=="false")
									    {
									    	alert_g(info);
									    	this_div.focus();
									    	return;
									    }
									   // var content=len.content;
										//update_div(studentid,content);
								},
								params : {
									evaluatedPersonID:studentid,
									part_id:apprid, 
									part_info:document.getElementById("apperedxiel_"+studentid+'_'+id).value,
									two_part_id:document.getElementById("evaluateType").value,
									createDate:$("#"+studentid+"_"+apprid+"_"+id+"_Date" ).val()
								}
							}); 
							
					
					
						}, 50);
					
					   
					  }else{
								this_div.setAttribute("idvalue",document.getElementById("apperedxiel_"+studentid+"_"+id).value);
								  alert("评价内容已超过600");
							}
					      }else{
								this_div.innerText=document.getElementById("apperedxiel_"+studentid+'_'+id).value;
								this_div.parentNode.onclick=function(){change_content_is_availableNull(apprid,studentid,id,true);}
								this_div.setAttribute("style","color:#ACACAC");
								this_div.innerText="  最多输入600字"; 
					          }
					       }
						}
					} 
			
			
			
			
			
			
	 function saveApprasial(){
		
		var newDiv = Ext.get('new_apprise');
		save_process();
	   
	 }

	 function alertSave(){
		 alert_g("保存成功");
	   } 
	function failure(document_object)
{
	document_object.setAttribute("style","border:3px solid red");
} 

 
	 function removeAppraise(part,studentid,num) {
		 var textWidth = $("textarea").css("width");
		 if(confirm( "确定要删除?" )) {  
			 var name1=$("#"+studentid+"_name").html();   
			 Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	    		Ext.Ajax.request({
				url:'${ctx}/student/CzPlayAndHealthAction.a?deleApprasial',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					  var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var info=len.info;
					    if(success="true")
					    {     alert_delete_success(name1);
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
				    					$("#new_apprise").css("display","none");	
				    				}
						    		var fistDiv="<div  id='0_dele_1'>"
										   +"<table  class='xiechengsheng4' cellspacing='0'>" 
								       		 +"<tr>" 
								        		+"<td id='"+studentid+"_0_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
								        		+"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								       				 +"  日期： <input id='"+studentid+"_0_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time("+studentid+",0,1)' onClick='WdatePicker()'/>" 
												+"</td>"
											 +" </tr>"
										+" </table>"
									 +"<div class='name_con ' id='wuyong4_1'>"
							           +"<div class='fl neirong ml70 mt10'  id='wuyong5_1}'>"
											  +"<div  style='height:120px; margin-top:0px;  width:100%;pxoverflow:auto;'  id='"+studentid+"_1_content' idvalue=''>" 
													+"<textarea id='new_apprise_content_xiel_"+studentid+"_1' style='background: transparent; border-style: none; color:#ACACAC; height: 120px; width: "+textWidth+";' onclick='return change_content_is_availableNull(0,"+studentid+",1,true)'>最多输入600字</textarea>"
										      +"</div> "
											+"</div>"
							            	+"<div class='fr btnxie' id='wuyong6_1' style='width:84px;'>"
							            	        +"<div class='xiezhong' id='wuyong7_1'>"
							            	  		+"<a id='null_"+studentid+"_delect' href='#' onclick='javascript:removeAppraiseNull(0,"+studentid+",1);' class='local delect'></a>"
											       +"</div>"
							            	+"</div>"
							        +"</div>"
							       
							        +"<div class='name_cxbox' id='"+studentid+"_1_addxie' style='display:none' >"
							            +"<div id='wuyong8_1'>"
							            +"<div class='btnxie' id='wuyong13_1'>"
							                +"<div class='xiezhong'  id='wuyong14_1'>"
							                      +"<a href='#' id='add_1' class='fr zengjia mr100' onclick='addApprasial("+studentid+",1);' ></a>" 
							                +"</div>"
							            +"</div>"
							       +"</div>" 
							          +"</div>"
							          +"</div>"; 
							        
									 var this_divAddxie =document.getElementById(studentid+"_xie");
										this_divAddxie.innerHTML='';
										this_divAddxie.innerHTML=fistDiv;
										readying();	
					         }
					              else if(num>=orderNum){
					            	
						    		$("#"+part+"_dele_"+num).remove();
						    		var divs=$("#"+studentid+"_xie").find("div");
						    		for(var i=0;i<divs.size();++i)
									{ 
										var id_content=$(divs[i]).attr("id");
										 if(id_content.indexOf("_dele")>=0) 
										 {   ++pNum;
											 var arr = new Array();
											 arr[pNum]=id_content;
											 var  lengthpid1=id_content.split("_"); //字符分割 
											 var  lengthpidAdd2=lengthpid1[2];
											 $("#"+studentid+"_"+lengthpidAdd2+"_shu").html("");
											 $("#"+studentid+"_"+lengthpidAdd2+"_shu").html(pNum);
										 } 
									}
						    		   var lengthDiv=arr[pNum];
										var  lengthpid=lengthDiv.split("_"); //字符分割 
										var  lengthpidAdd=lengthpid[2];
										 //alert(lengthpidAdd);
										 $("#"+studentid+"_"+lengthpidAdd+"_addxie").css("display","block");
						    	}else{
						    		
						    		$("#"+part+"_dele_"+num).remove();
						    		
						    		var divs=$("#"+studentid+"_xie").find("div");
						    		
						    		for(var i=0;i<divs.size();++i)
									{ 
										var id_content=$(divs[i]).attr("id");
										 if(id_content.indexOf("_dele")>=0) 
										 {   ++pNum;
											 var arr = new Array();
											 var  lengthpid=id_content.split("_"); //字符分割 
											 var  lengthpidAdd=lengthpid[2];
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").html("");
											 $("#"+studentid+"_"+lengthpidAdd+"_shu").html(pNum);
										 } 
									}
						    		
						    		
						    	}
							
					    
					    }else if(success=="false")
					    {
					    	alert_g(info);
					    	this_div.focus();
					    	return;
					    }
					    var content=len.content;
						
				
				},
				params : {
							evaluatedPersonID:studentid,
							part_id:part,
							two_part_id:document.getElementById("evaluateType").value
				}
			}); 
		
		 
		 
		 
		 }  
		 } 
	
	  
		 
		 
	//删除初始为空 是数据
	 function removeAppraiseNull(part,studentid,num) {
		if(confirm( "确定要删除?" )) { 
			var appr=$("#apperedxiel_"+studentid+"_"+num).val();
			if(appr!=null&&appr!=" "&&appr!=""){
				var lent=$("#apperedxiel_"+studentid+"_"+num).val().length;
				if(lent>600){
					var this_div = document.getElementById(studentid+"_"+num+"_content");
					this_div.setAttribute("idvalue","");
					this_div.innerText="";
					this_div.parentNode.onclick=function(){change_content_is_availableNull(part,studentid,num,true);}
					this_div.setAttribute("style","color:#ACACAC");
					this_div.innerText="  最多输入600字";
				}else{
				dectestatic="delct";} 
		    }else{
		    	apprasial_del_Finish();
		    }   
		 } 
		} 
	
		//删除新添加的
	 function delectNewAdd(){
		  var appr=$("#new_apprise_content").val();
		if(confirm( "确定要删除?" ))
		  {    
			    if(appr!=null&&appr!=" "&&appr!=""){
			    	
			    	var length=appr.trim().length;
			    	if(length>0){
			    		
				  		Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
			    		Ext.Ajax.request({
						url:'${ctx}/student/CzPlayAndHealthAction.a?deleApprasial',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							   var studentid=document.getElementById("new_studentid").value;
							  var len=Ext.util.JSON.decode(response.responseText);
							    var success=len.success.replace( /^\s+|\s+$/g,"");
							    var info=len.info;
							    if(success="true")
							    {
							    	alert_g(info);
							    }else if(success=="false")
							    {
							    	alert_g(info);
							    	this_div.focus();
							    	return;
							    }
							    var content=len.content;
							   // update_div(studentid,content);
						},
						params : {
							evaluatedPersonID:document.getElementById("new_studentid").value,
							two_part_id: document.getElementById("evaluateType").value,
							part_id:document.getElementById("part_id").value
						}
					});
		     }else{
		    	 var evaluateType= document.getElementById("evaluateType").value;
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
				    Ext.Ajax.request({
					url:'${ctx}/student/CzPlayAndHealthAction.a?delectAddNull',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						     var studentid=document.getElementById("new_studentid").value;
							  var temp=response.responseText;
							 var len=eval(temp);
							 //update_div(studentid,len);
					},
					params : {
								evaluatedPersonID:document.getElementById("new_studentid").value,
								two_part_id: document.getElementById("evaluateType").value,
								part_id:document.getElementById("part_id").value
							}
						});
			    }
		    }else{  
		    	 $('#new_apprise').css("display","none");
					var evaluateType= document.getElementById("evaluateType").value;
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
				    Ext.Ajax.request({
					url:'${ctx}/student/CzPlayAndHealthAction.a?delectAddNull',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						     var studentid=document.getElementById("new_studentid").value;
						    var temp=response.responseText;
							 var len=eval(temp);
						},
					params : {
								evaluatedPersonID:document.getElementById("new_studentid").value,
								two_part_id: document.getElementById("evaluateType").value,
								part_id:document.getElementById("part_id").value
							}
						}); 
		   
		    }
		  }   
		 
	}
 function changeterm(){
	    ShowDiv();
		var term = document.getElementById("termId").value;
		var evaluateType= document.getElementById("evaluateType").value;
		url="${ctx}/student/CzPlayAndHealthAction.a?chaneTermId&two_part_id="+evaluateType+"&termId1="+term;
		document.location.replace(url);
	}
 
 
 function update_time(studentid,id,num)
	{
	if(id==null||id==""||id==0)
		{
		    
		}else{
			var time1=$("#"+studentid+"_"+id+"_"+num+"_Date").val();
			var time2=$("#"+studentid+"_"+id+"_"+num+"_Date").attr("idvalue");
			if(time1!=time2)
			{   id="${list.studentid}_${status.index+1}_shu"
				var name1=$("#"+studentid+"_name").html();
		    	var xianshu=$("#"+studentid+"_"+num+"_shu").html();
				alert_update_success(name1,xianshu);
		    	$("#"+studentid+"_"+id+"_"+num+"_Date").attr("idvalue",time1);
				Ext.Ajax.request({
					url:'${ctx}/student/CzPlayAndHealthAction.a?doUpadateTime',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
					},
					params : {
						part_id:id,
						createDate:time1,
					}
				});
				
			}	
		}
		
	}
</script>  

<body id="text_style">
   <input id="evaluateType" type="hidden" name="evaluateType" value="${two_part_id}"/>
   <%@ include file="/common/div.jsp"%> 
  <div class="dangqianwz">
 	<span class="fl">当前位置：评价同学->
            <c:if test="${two_part_id eq 32}">思想道德&nbsp;</c:if>
           	<c:if test="${two_part_id eq 45}">学业成就&nbsp;</c:if>
		 	<c:if test="${two_part_id eq 52}">合作与交流&nbsp;</c:if>
			<c:if test="${two_part_id eq 62}">运动与健康&nbsp;</c:if>
			<c:if test="${two_part_id eq 72}">审美与表现&nbsp;</c:if>
			<c:if test="${two_part_id eq 92}">个性发展&nbsp;</c:if> 
 	</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <span >姓名：
     	<input style="width:129px; border:1px #666 solid;" type="text" id="lookForId" size="32"/>
      </span>
 	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	  <span>学期：
    	 <select style="border:1px #666 solid;"  name="termId" id="termId" onchange="changeterm()">
            <app:highSchoolTermTag  levelCode="${levelCode}" selectNum="${termId}" selectClassid="${classid}"/>
        </select> 
      </span>
 	   
 </div> 
  <div id="pj_jiaoshi_main">
<form name="otherstartForm" action="OtherAppraiseAction.a" method="post">
	 <div id="xian" ></div>
	  <div id="cun" ></div>
	  
	 <div id="new_apprise" style='display:none;margin-left:50px;'>
				<!-- <div class="layout"> -->
	    		<input id="new_studentid"  type="hidden" name="new_studentid" value=""/>
	    		 <input id="new_numshu"  type="hidden"  value=""/> 
	        	
	        	 <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="new_shu" width="10%" height="40" class="xiechengsheng3"></td>
			                <td  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							                  日期：<input id="newxie_time" type="text" size="10" value="${time}" onblur="update_time('0','0','0')"  onClick="WdatePicker()"/>
							   </td>
		                 </tr>
		              </table>
	        	<div class="name_con">
		        	 <div class="fl neirong ml70 mt10" > 
		        	       <div style="height:120px; margin-top:0px;  width:100%;pxoverflow:auto;">
		        			 <textarea   id="new_apprise_content"  style="height:120px; width:99%;overflow:auto;" onclick="romvezi()" onblur="return saveApprasial()"></textarea> 
						   </div> 
						 </div> 
	        	    <div class="fr btnxie" style="width:84px;">
		                <div class="xiezhong"> 
		                    <a href="#" onclick="removeNewAdd()" class="local delect"></a>
		        	      </div>
		            	</div>
	        	</div>
	           <div class="name_cxbox ">
	              <div class="btnxie">
				        <div class="xiezhong" >
				       		 <a href="#"  class="fr zengjia mr100" onclick="" ></a> 
				        </div>
			      </div>
	        
	        </div> 
	    <!-- </div>  -->
	</div> 

 <c:forEach items="${listApraisal}" var="stud" >
           <a id="${stud.name}_${stud.eduid}"></a>
				 <div class="layout" >
				   <br/>
				   <br/>
		    		<div class="name_pic" >
		    		 <img class="img-padding" src="${stud.photoUrl}"/>
		    		</div>
		    		<%-- <div class="name ml120 pt20" id="${stud.studentid}_name">${stud.name}</div> --%>
		        	<div class="title_xie" id="${stud.studentid}_name">${stud.name} </div>
		        	<div id="${stud.studentid}_xie" style="margin-left:50px;">
		        	<c:forEach items="${stud.czaInfos}" var="list"  varStatus="status">
		        	   <div  id="${list.part_id}_dele_${status.index+1}">
		        	  <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="${list.studentid}_${status.index+1}_shu" width="10%" height="40" class="xiechengsheng3">${status.index+1}</td>
			                <td  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <c:if test="${not empty list.part_info}">                                            
							                  日期： <input id="${list.studentid}_${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.studentid},${list.part_id},${status.index+1})" onClick="WdatePicker()"/>
							   
		                       </c:if>
                               <c:if test="${empty list.part_info}">                                            
							                  日期：<input id="${list.studentid}_${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.studentid},${list.part_id},${status.index+1})" onClick="WdatePicker()"/>
		                       </c:if>
		                       </td>
		                 </tr>
		              </table>
		        	  
		        	   
		        <c:if test="${not empty list.part_info}">
		        <div class="name_con" style="background:#eee;" id="${list.studentid}_${status.index+1}_boder">
		        	    <div class="fl neirong ml70 mt10" id="wuyong1_${status.index+1}">
		        	         <div  style="height:130px; width:100%; margin-top:0px; pxoverflow:auto;"  id="${list.studentid}_${status.index+1}_content" idvalue="${list.part_info}"> 
		                       <textarea id="apperedxiel_${list.studentid}_${status.index+1}" style="background: transparent; border-style: none; height: 120px; width:99%;" onclick="return change_content_is_available(${list.part_id},${list.studentid},${status.index+1},true);">${list.part_info}</textarea>
					        </div> 
				       </div>
		               <div class="fr btnxie" style="width:84px;" id="wuyong2_${status.index+1}">
		                <div class="xiezhong" id="wuyong3_${status.index+1}"> 
		                    <a href="#" onclick="javascript:removeAppraise(${list.part_id},${list.studentid},${status.index+1});" class="local delect"></a>
		        	      </div>
		            	</div>
		          </div>
		         </c:if>
		        <c:if test="${empty list.part_info}">
		           <div class="name_con " id="wuyong4_${status.index+1}">
		        	    <div class="fl neirong ml70 mt10"  id="wuyong5_${status.index+1}">
		        	 <!--  background:red; -->
						  <div  style="height:130px; margin-top:0px;  width:100%;pxoverflow:auto;"  id="${list.studentid}_${status.index+1}_content" idvalue="${list.part_info}"> 
								<textarea id="new_apprise_content_xiel_${list.studentid}_${status.index+1}" style="background: transparent; border-style: none; height: 120px; width: 99%;" onclick="return change_content_is_availableNull(${list.part_id},${list.studentid},${status.index+1},true)">${list.part_info}</textarea>
					      </div> 
						</div>
		            	<div class="fr btnxie" style="width:84px;" id="wuyong6_${status.index+1}">
		            	        <div class="xiezhong" id="wuyong7_${status.index+1}">
		            	  		<a id="null_${list.studentid}_delect" href="#" onclick="javascript:removeAppraiseNull(${list.part_id},${list.studentid},${status.index+1});" class="local delect"></a>
						       </div>
		            	</div>
		            	<!-- </div> -->
		        </div>
		         </c:if>
		         
		              <c:if test="${status.count==fn:length(stud.czaInfos)}"> 
		                  <c:if test="${not empty list.part_info}">
		                   <div class="name_cxbox "  id="${list.studentid}_${status.index+1}_addxie">
			                 <div class="btnxie" id="wuyong13_${status.index+1}">
				                 <div class="xiezhong"  id="wuyong14_${status.index+1}">
				       				 <a href="#" id="add_${status.index+1}" class="fr zengjia mr100" onclick="addApprasial(${list.studentid},${status.index+1});" ></a> 
				                 </div>
			                 </div>
		                  </div>
		              </c:if>
		               <c:if test="${empty list.part_info}">
		                  <div class="name_cxbox "  id="${list.studentid}_${status.index+1}_addxie" style="display: none;">
		                 
			                 <div class="btnxie" id="wuyong13_${status.index+1}">
				                 <div class="xiezhong"  id="wuyong14_${status.index+1}">
				       				 <a href="#" id="add_${status.index+1}" class="fr zengjia mr100" onclick="addApprasial(${list.studentid},${status.index+1});" ></a> 
				                 </div>
			                 </div>
		                </div>
		              </c:if>
		          </c:if> 
		             <c:if test="${status.count!=fn:length(stud.czaInfos)}"> 
		                 <div class="name_cxbox "  id="${list.studentid}_${status.index+1}_addxie" style="display: none;">
			                 <div class="btnxie" id="wuyong13_${status.index+1}">
				                 <div class="xiezhong"  id="wuyong14_${status.index+1}">
				       				 <a href="#" id="add_${status.index+1}" class="fr zengjia mr100" onclick="addApprasial(${list.studentid},${status.index+1});" ></a> 
				                 </div>
			                 </div>
		                </div>
		          </c:if>   
		          <!-- </div> -->
		          </div>
		        </c:forEach>
		      </div>
		    </div> 
	    </c:forEach>
 </form>
     </div> 
</body>


