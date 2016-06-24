<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/mass.jsp"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />

<link rel="stylesheet" type="text/css" href="${ctx}/css_new/index.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css_new/style.css" />
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


<head>
<style type="text/css">
 body,html{ 
 	 width:100%; height:100%;
     overflow-x:hidden;
	 overflow-y:hidden; 
 }
#three{
 	margin-right:-35px;
 	margin-top: 7px;
 }
 #pj_jiaoshi_main1 .name_cxbox{
	width:99.85%;
}
  #pj_jiaoshi_main1{
	padding: 0px;
	width:95%;
	margin-left: 2.5%;
}

#pj_jiaoshi_main1 .name_con .neirong{
 	height:120px;
 	/* width:88.3%; */
	line-height:1.5;
} 
.mt10{
	margin-top:5px;
}
/* #pj_jiaoshi_main .name_con .btn{
	width:10%;
} */
#shanchu_btn{
	width:10%;
}
#no_content{
	position: absolute;
	top: 150px;
	bottom: 0px;
	padding: 50px 30px 30px 300px;
	overflow: auto;
}
#pj_jiaoshi_main1 .name_cxleft{
	margin-top: 25px;
}
/* 新添加内容 */
.ml70{
	margin-left:5px;
}
.title_xie{
   height:40px;
   background-color:rgb(39,159,70);
   padding-left: 100px;
   padding-top: 15px;
   font-size:18px;
   color:#fff;
   width: 95%;
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
}
#pj_jiaoshi_main1 .name{
   padding-left: 300px;
}
.img-padding{
 margin-top:-20px;
}
/* 删除按钮样式 */
#pj_jiaoshi_main1 .name_con .btn{
	border-bottom-width:0px;
	border-right-width:0px;
	border-top-width:0px;
	height:132px;
}
element.style{
  width:100%
}

.qust_show span .server{
   background-position: -275px -380;
   
}
.qust_show{
  padding:10px 23px 20px 5px;
}
#pj_jiaoshi_main1 .name_con .btnxie{
 border:0px solid;
 border-left:1px solid #999999;
}

.imp-padding{
 margin-lift:0.8%;
}

a{
    margin-left:-12%; 
}
pj_jiaoshi_main1{
margin-bottom: 20px;
}
#pj_jiaoshi_main1 .layout{
margin-bottom: 40px;
}
</style>
</head>


<script type="text/javaScript">




$(window).load(function() {
	    
	    initStudentInfos();
		var thisMession=document.getElementById("mession");
		thisMession.innerHTML="";
		thisMession.innerHTML=mess;
		 var frame2 = $(self.parent.frames[1].document);
		var flag = frame2.find("a").attr("title");
		if(flag == "关闭左边菜单"){
		}else{
			var textareas=$("body");
			var pjTb2 = textareas.find("[name='xiaomao']");
			if(pjTb2.length>0){
				pjTb2.css("width","90%");
			}
			
			var pjTb3 = textareas.find("[name='delectNull_m']");
			if(pjTb3.length>0){
				pjTb3.css("width","5.7%");
			}
			
		} 
		 
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
		
		
		 var frame2 = $(self.parent.frames[1].document);
			var flag = frame2.find("a").attr("title");
			if(flag == "关闭左边菜单"){
			}else{
				var textareas=$("body");
				var pjTb2 = textareas.find("[name='xiaomao']");
				if(pjTb2.length>0){
					pjTb2.css("width","90%");
				}
				
				var pjTb3 = textareas.find("[name='delectNull_m']");
				if(pjTb3.length>0){
					pjTb3.css("width","5.7%");
				}
				
			} 
}

 var dectestatic="notdelct";

//初始为空时 ，使得内容为空时可写



//更改时间
function update_time(eduid,apprasialid,num)
{    eduid=eduid+"";
	if(7==eduid.length){
		eduid="0"+eduid;
	} 
    if(apprasialid==null||apprasialid==""||apprasialid==0)
	{
		return null;
	}else{
		var time1=$("#"+eduid+"_"+apprasialid+"_"+num+"_Date").val();
		var time2=$("#"+eduid+"_"+apprasialid+"_"+num+"_Date").attr("idvalue");
		if(time1!=time2)
		{    
		    var name=$("#evaluateName_name").html();
			var xianshu=$("#"+eduid+"_"+num+"_shu").html();
		       alert_update_success(name,xianshu);
    		$("#"+eduid+"_"+apprasialid+"_"+num+"_Date").attr("idvalue",time1);
			Ext.Ajax.request({
				url:'${ctx}/student/PlayAndHealthXinAction.a?doUpdataOtherProcess',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
				},
				params : {
					signDate:time1,
					eduId :eduid,
					apprasialid:apprasialid,
					evaluateType : $("#evaluateType").val(),
					termId:$("#termId").val(),
					pStudentid:$("#pStudentid").val(),
					evaluateName:$("#evaluateName_name")
				
				}
			});
			
		}	
	} 
	
}
	  



  //删除评价 { listat.apprasialid},${listat.eduid},${status.index+1}
function removeAppraise(apprasialid,studentid,num) {
	studentid=studentid+"";
	 if(7==studentid.length){
		studentid="0"+studentid;
	}
	  if(confirm("确定要删除?"))                              
	  {      var name=$("#evaluateName_name").html();
		    var evaluateType= document.getElementById("evaluateType").value;
	  		Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
   		Ext.Ajax.request({
			url:'${ctx}/student/PlayAndHealthXinAction.a?deleApprasial',
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
				    	var divs=$("#111_xie").find("div");
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
					    	
					    		$("#"+studentid+"_"+num+"_dele").remove();
					    		$("#"+studentid+"_libiao").css("color","red");
					    		var fistDiv="<div  id='"+studentid+"_1_dele'>"
					    		 +"<table  class='xiechengsheng4' cellspacing='0'>"
									+"<tr>" 
									  +"<td id='"+studentid+"_1_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
									     +"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									     +"日期：<input id='"+studentid+"_0_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time("+studentid+",0,1)' onClick='WdatePicker()'/>"
								      +"</tr>"
								   +"</table>"
					    		        +"<div class='name_con ' id='wuyong5_1'>"
											 +"<div name='xiaomao' class='fl neirong'  style='height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;' id='"+studentid+"_1_content' idvalue='${listat.apprasial}' >"
													+"<textarea id='"+studentid+"_new_apprise_content_xie' style='background: transparent; overflow:auto; border-style: none; color:#ACACAC; height: 120px; width: 100%;' onclick='return change_content_is_availableNll(0,"+studentid+",1,true);'>最多输入4000字</textarea>"
											+"</div>"
							            	+"<div class='fr btnxie' name='delectNull_m' style='width:7%;' id='wuyong7_1'>"
							            	  		+"<a id='btnxie_001' href='#' onclick='javascript:delectNull(0,"+studentid+");'  class='local delect'></a>"
							            	+"</div>"
							        +"</div>"
							      +"</div>";

								 var this_divAddxie =document.getElementById("111_xie");
									this_divAddxie.innerHTML='';
									this_divAddxie.innerHTML=fistDiv;
									
					    	}
					    	else if(num>=orderNum){
					    		$("#"+studentid+"_"+num+"_dele").remove();
					    		var divs=$("#111_xie").find("div");
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
									  $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
									 
					    	}else{
					    		
					    		$("#"+studentid+"_"+num+"_dele").remove();
					    		var xianNum=0;
					    		var divshuli=$("#111_xie").find("div");
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
				 eduId :studentid,
				 evaluateType : $("#evaluateType").val(),
				 termId:$("#termId").val(),
				 pStudentid:$("#pStudentid").val(),
				 evaluateName:$("#evaluateName_name")
			}
		});
	 }  
	 }
//删除初始没有评价时的评价
function delectNull(apprasialid,studentid) {
	 studentid=studentid+"";
	if(7==studentid.length){
		studentid="0"+studentid;
	} 
   if(confirm( "确定要删除?" ))
	  {     
		    var appr=$("#idcontent").val();
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
		  var appr=$("#new_apprise_content").val();
		  if(appr=="最多输入4000字"){
		    var studentid=$("#new_studentid").val();
		    var name=$("#evaluateName_name").html();
		    $("#new_apprise").css("display","none");
		    var pNum=0;
   	        var divs=$("#111_xie").find("div");
   	        var arr = new Array();
   	        for(var i=0;i<divs.size();++i)
             { 
         	    var id_content=$(divs[i]).attr("id");
         	   if(id_content.indexOf("_dele")>=0) 
         	   {   ++pNum;
         		 arr[pNum]=id_content;
         	   } 
             }
            var lengthDiv=arr[pNum];
         	var  lengthpid=lengthDiv.split("_"); //字符分割 
         	var  lengthpidAdd=lengthpid[1];
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
				    	    var divs=$("#111_xie").find("div");
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
		                  	
		                  	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
		    		   
		    	   }else{dectestatic="delct";}
		    	
		   }
	    }else{  
	    	var studentid=$("#new_studentid").val();
	    	 var name=$("#evaluateName_name").html();
			 alert_delete_success(name);
	    	$("#new_apprise").css("display","none");	
	    	 var studentid=$("#new_studentid").val(); 
	    	 var pNum=0;
	    	    var divs=$("#111_xie").find("div");
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
             	
             	 $("#"+studentid+"_"+lengthpidAdd+"_add").css("display","block");
	    }
	  
	  }   
	 
} 
//更改学期刷新页面
	function changeterm(){
		 ShowDiv(); 
		var term = document.getElementById("termId").value;
		var evaluateType= document.getElementById("evaluateType").value;
		url="${ctx}/student/PlayAndHealthXinAction.a?evaluateType="+evaluateType+"&termId="+term;
		document.location.replace(url);
	}


	var content_div_full="";
	function change_content_is_availableNll(apprid,eduid,num,flag){
		eduid=eduid+"";
		if(7==eduid.length){
			eduid="0"+eduid;
		}
		  var name=$("#evaluateName_name").html();
		if(eduid==null||eduid==""){
		}else{
			var this_div = document.getElementById(eduid+"_"+num+"_content");
			if(flag==true){ 
				this_div.focus();
				this_div.parentNode.onclick=null;  
				this_div.innerHTML="";
				this_div.innerHTML='<textarea name="idcontent"  style="height:120px; width:99%; overflow:auto;border:solid 1px;" id="idcontent" onblur="return change_content_is_availableNll('+apprid+','+eduid+','+num+',false)"></textarea>';
				var nullapper=this_div.attributes["idvalue"].nodeValue;//获取值
				if(nullapper!=""){
				  document.getElementById("idcontent").value=this_div.attributes["idvalue"].nodeValue;
				}
				document.getElementById("idcontent").focus();
				$("#idcontent").focus();
			}else{
				var app=$("#idcontent").val().trim();
				var leng=$("#idcontent").val().length;
				if(leng<=4000){
				if(app!=null&&app!=""&&app!="最多输入4000字"){
				setTimeout(function () { 
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/PlayAndHealthXinAction.a?doUpdataOtherProcessNll',
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
							    		$("#"+eduid+"_libiao").css("color","green");
							    	}
							    	  if(dectestatic=="delct"){
							    		  Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
								    		Ext.Ajax.request({
											url:'${ctx}/student/PlayAndHealthXinAction.a?deleApprasial',
											method:'POST',
											defaults:{autoScroll: true},
											success:function(response,options){
												alert_delete_success(name);
												  var len=Ext.util.JSON.decode(response.responseText);
												    var success=len.success.replace( /^\s+|\s+$/g,"");
												    
												    if(success="true")
												    {   dectestatic="notdelct";
														this_div.parentNode.onclick=function(){change_content_is_availableNll(apprid,eduid,num,true);}
														this_div.setAttribute("style","color:#ACACAC");
														this_div.innerText="最多输入4000字";
												    }else if(success=="false")
												    {
												    	
												    	this_div.focus();
												    	return;
												    }
											},
											params : {
											    apprasialid :apprasialid,
											 	eduId :eduid,
												evaluateType : $("#evaluateType").val(), 
												termId:$("#termId").val(),
												pStudentid:$("#pStudentid").val(),
												evaluateName:$("#evaluateName_name")
											}
										});  
							    	  }else{
									    	 var apprasial =$("#idcontent").val();
											 var evaluateType =$("#evaluateType").val();
											 var signDate =  document.getElementById(eduid+"_"+apprid+"_"+num+"_Date").value;
										     var newdiv= "<div  id='"+eduid+"_1_dele'>"
												 +"<table  class='xiechengsheng4' cellspacing='0'>"
													+"<tr>" 
													  +"<td id='"+eduid+"_1_shu' width='10%' height='40' class='xiechengsheng3'>1</td>"
													     +"<td  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													          +"日期：<input  id='"+eduid+"_"+apprasialid+"_1_Date'  type='text' size='10' value='"+signDate+"'  idvalue='"+signDate+"' onblur='update_time("+eduid+","+apprasialid+",1)' onClick='WdatePicker()' /></td>"
												      +"</tr>"
												   +"</table>"
												   +"<div id='wuyong1_1' class='name_con ' style='background:#eee;'>"
														 +"<div name='xiaomao' class='fl neirong ' style='height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;' id='"+eduid+"_1_content' idvalue='"+apprasial+"'>"
															+"<textarea id= '"+eduid+"_1_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: 99%;' onclick='return change_content_is_available("+apprasialid+","+eduid+",1,true);'>"+apprasial+"</textarea>"
														+"</div>"
														
										            	+"<div class='fr btnxie' name='delectNull_m' style='width:7%;' id='wuyong3_1'>"
										            	  		+"<a id='btnxie_001' href='#' onclick='javascript:removeAppraise("+apprasialid+","+eduid+",1);' class='local delect'></a>"
										            	+"</div>"
										             +"</div>"
										        +"<div class='name_cxbox '  id='"+eduid+"_1_add'>"
										            +" <div name='xiaomao' class='fl neirong' style=' margin-top:0px; width:89.6%;pxoverflow:auto;' > </div>"
											       
										            +"<div class='fr btnxie' id='wuyong55' name='delectNull_m'  style='width: 7%'>"
										           		 +"<a href='#' id='add' style='margin-top: 7px;' class='fl zengjia ' onclick='addApprasial("+eduid+",1);' ></a>" 
										            +" </div>"
										         +"</div>"
										        
										        
										        +"</div>";   
										    
									         var newpaerdiv=document.getElementById("111_xie");
									       
									         newpaerdiv.innerHTML=newdiv; 
									         readying();
									   }
							    }else if(success=="false")
							    {
							    	
							    	this_div.focus();
							    	return;
							    } 
							
						
						 },
						params : {
							eduId :eduid,
						 	apprasial :$("#idcontent").val(),
							evaluateType : $("#evaluateType").val(), 
							signDate : document.getElementById(eduid+"_"+apprid+"_"+num+"_Date").value, 
							termId:$("#termId").val(),
							pStudentid:$("#pStudentid").val(),
							evaluateName:$("#evaluateName_name")
						}
					});
			 	 }, 250);
				
				} else{
				   //更新数据
				   
				    this_div.setAttribute("idvalue",document.getElementById("idcontent").value);				
					this_div.innerText=document.getElementById("idcontent").value;     
					this_div.parentNode.onclick=function(){change_content_is_availableNll(apprid,eduid,num,true);}
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


	//有内容进行修改
	var content_div_full="";
	function change_content_is_available(apprid,eduid,num,flag){
	 	eduid=eduid+"";
		if(7==eduid.length){
			eduid="0"+eduid;
		} 
		
		var name=$("#evaluateName_name").html();
		if(apprid==null||apprid==""){
		}else{
			if(flag==true){
				var this_div = document.getElementById(eduid+"_"+num+"_content");
				this_div.focus();
				this_div.parentNode.onclick=null;
				$("#"+eduid+"_"+num+"_content").parent().css("background-color", "#fff");
				this_div.innerHTML=''; 
				this_div.innerHTML='<textarea  id="apperedxiel_'+eduid+'_'+num+'"   style="height:120px; width:99%; overflow:auto; border:solid 1px;"  onblur="return change_content_is_available('+apprid+','+eduid+','+num+',false)"></textarea>';
				document.getElementById("apperedxiel_"+eduid+"_"+num).value=this_div.attributes["idvalue"].nodeValue;
				document.getElementById("apperedxiel_"+eduid+"_"+num).focus();
				$("#apperedxiel_"+eduid+"_"+id).focus();
				content_div_full=document.getElementById("apperedxiel_"+eduid+"_"+num).innerText;
			}else{
				var this_div = document.getElementById(eduid+"_"+num+"_content");
				var app=$("#apperedxiel_"+eduid+"_"+num).val().trim().length;
				var apple=$("#apperedxiel_"+eduid+"_"+num).val();
				var leng=$("#apperedxiel_"+eduid+"_"+num).val().length;
				content_div_full=$("#"+eduid+"_"+num+"_content").attr("idvalue");
				if(app>0){ 
					if(leng<=4000){
						if(apple!=content_div_full){
							Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
							Ext.Ajax.request({
								url:'${ctx}/student/PlayAndHealthXinAction.a?doUpdataOtherProcess',
								method:'POST',
								success:function(response,options){
										var len=Ext.util.JSON.decode(response.responseText);
									    var success=len.success.replace( /^\s+|\s+$/g,"");
									    var info=len.info;
									    if(success="true")
									    {    
									    	var xianshu=$("#"+eduid+"_"+num+"_shu").html();
									       alert_update_success(name,xianshu);
									       $("#"+eduid+"_"+num+"_content").parent().css("background-color", "#eee");
											this_div.innerHTML="<textarea id= '"+eduid+"_"+num+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: 99%;'>"+apple+"</textarea>";
											this_div.setAttribute("idvalue",apple);				
											this_div.parentNode.onclick=function(){change_content_is_available(apprid,eduid,num,true);};
									       
									    }else if(success=="false")
									    {
									    	
									    	this_div.focus();
									    	return;
									    }
								},
								params : { 
									eduId :eduid,
									apprasialid:apprid,
									apprasial: $("#apperedxiel_"+eduid+"_"+num).val(),
									evaluateType : $("#evaluateType").val(),
									signDate : document.getElementById(eduid+"_"+apprid+"_"+num+"_Date").value, 
									termId:$("#termId").val(),
									pStudentid:$("#pStudentid").val(),
									evaluateName:$("#evaluateName_name")
								
								}
							});
						
						} else{
								 $("#"+eduid+"_"+num+"_content").parent().css("background-color", "#eee");
							
							   //更新数据
								this_div.setAttribute("idvalue",document.getElementById("apperedxiel_"+eduid+"_"+num).value);				
								this_div.onclick=function(){change_content_is_available(apprid,eduid,num,true);}
								
								this_div.innerHTML="<textarea id= '"+eduid+"_"+num+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: 100%;'>"+content_div_full+"</textarea>";
						    } 
						}else{
							 	alert("评价内容已超过4000");
						     }
					  }
			    }
			} 
		}	





//新增
function addApprasial(studentid,num){ 
	studentid=studentid+"";
	
	if(7==studentid.length){
		studentid="0"+studentid;
	} 
	var orderNum=0;		
	var divs=$("#111_xie").find("div");
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
function saveApprasial(){
	
   setTimeout(function () { 
			 document.getElementById("new_apprise_content").style.border ="0px solid";
			var apper=$("#new_apprise_content").val().trim();
			if(apper=="最多输入4000字"){
		      return; 	
			}
			var studentid=$("#new_studentid").val();
		  
			var name=$("#evaluateName_name").html();
			if(apper!=""&&apper!=null){
				  var apperTrim=$("#new_apprise_content").val().length;
				 
			    if(apperTrim<=4000){
			    	 $("#new_apprise_content").bind('click',function(){
				 			romvezi();
				 		}); 
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
			    	Ext.Ajax.request({
						url:'${ctx}/student/PlayAndHealthXinAction.a?doUpdataOtherProcessNll',
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
											url:'${ctx}/student/PlayAndHealthXinAction.a?deleApprasial',
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
												    	    var divs=$("#111_xie").find("div");
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
											     apprasialid :apprasialid,
											     eduId :studentid,
											     evaluateType : $("#evaluateType").val(),
											     termId:$("#termId").val(),
											     pStudentid:$("#pStudentid").val(),
											     evaluateName:$("#evaluateName_name")
											
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
						      
										 +"<div name='xiaomao' class='fl neirong' style='height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;' id='"+studentid+"_"+num+"_content' idvalue='"+apprasial+"'>"
											+"<textarea id= '"+studentid+"_"+num+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width:99%;' onclick='return change_content_is_available("+apprasialid+","+studentid+","+num+",true);'>"+apprasial+"</textarea>"
										
										+"</div>"
						            	+"<div class='fr btnxie' name='delectNull_m' style='width:7%;' id='wuyong6_"+num+"'>"
						            	  	 	
						            	  		+"<a id='btnxie_001' href='#' onclick='javascript:removeAppraise("+apprasialid+","+studentid+","+num+");' class='local delect'></a>"
						            	   
						            	+"</div>"
						             +"</div>"
						             
						        
						        +"<div class='name_cxbox ' id='"+studentid+"_"+num+"_add' >"
					            +" <div name='xiaomao' class='fl neirong' style=' margin-top:0px; width:89.6%;pxoverflow:auto;' > </div>"
						       
					            +"<div class='fr btnxie' id='wuyong55' name='delectNull_m'  style='width: 7%'>"
					           		 +"<a href='#' id='add'  style='margin-top: 7px;' class='fl zengjia ' onclick='addApprasial("+studentid+","+num+");' ></a>" 
					            +" </div>"
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
							         readying();
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
						     eduId :studentid,
						     evaluateType : $("#evaluateType").val(),
						     termId:$("#termId").val(),
						     pStudentid:$("#pStudentid").val(),
						     evaluateName:$("#evaluateName_name")
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

 }, 200); 
}




function romvezi(){
	$("#new_apprise_content").css("color","");
	$("#new_apprise_content").val("");
	$("#new_apprise_content").unbind('click');
	$("#new_apprise_content").removeAttr("onclick");
	document.getElementById("new_apprise_content").style.border ="1px solid black";
}


 function initStudentInfos(){
	
	  var divdsl = $("#xingmingdsl");
	var str = "";				
	str += "<ul class='name'>";	
	<c:forEach items="${appraisalListString}" var="studentInfo" varStatus="vs">
	    var name   = "${studentInfo.evaluateName}";
		var eduId  = "${studentInfo.eduid}";
		var status = "${studentInfo.sumnaber}";
		var studentid = "${studentInfo.studentid}";
		var photoUrl = "${studentInfo.photoUrl}";
		var count = "${vs.index}";
		if(count==0){
			str += "<li style='background:gray;' onclick='findById(this,\""+eduId+"\",\""+photoUrl+"\",\""+name+"\","+studentid+")'><span title='"+name+"' style='cursor:pointer'>";
		}else{
			str += "<li onclick='findById(this,\""+eduId+"\",\""+photoUrl+"\",\""+name+"\","+studentid+")'><span title='"+name+"' style='cursor:pointer'>";
		}
		if(0<status){//判断状态
			if(name.length>3){//截取字符串
				var st = name.substring(0,3);							
				st +="..."
				str += "<font id='"+eduId+"_libiao' color=green>"+st+"</font></span>";
			}else{
				str += "<font id='"+eduId+"_libiao' color=green>"+name+"</font></span>";
			}						
		}else{
			if(name.length>3){
				var st = name.substring(0,3);
				st +="..."
				str += "<font  id='"+eduId+"_libiao' color=red>"+st+"</font></span>";
			}else{
				str += "<font id='"+eduId+"_libiao'  color=red>"+name+"</font></span>";
			}	
		}		
	</c:forEach>   
	str += "</ul>";									
	divdsl.append(str); 
	$("#pj_jiaoshi_main1").css("display","block");
	
}  

/**
 *根据id查出学生信息
 */   
 function findById(obj,eduId,photoUrl,name,studentid){
	$("#pj_jiaoshi_main1").show();
	$("#pj_dengsl_main").show();
	$("li").css("background","");
	$(obj).css("background","gray");
	findByIdww(eduId,photoUrl,name,studentid)
} 

 function findByIdww(eduId,photoUrl,name,studentid){
	 eduId=eduId+"";
	 if(7==eduId.length){
		 eduId="0"+eduId;
		} 
	 var eduided=$("#eduid").val();
	 if(eduided==eduId){
		  return ;
	 }else{
		 $("#new_apprise").css("display","none");
		 $("#photoUrl").attr("src",photoUrl);
		 $("#evaluateName_name").html(name);
		 $("#pStudentid").val(studentid);
		 $("#eduid").val(eduId);
		  Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
			Ext.Ajax.request({
				url:'${ctx}/student/PlayAndHealthXinAction.a?chanStuder',
				method:'POST',
				success:function(response,options){
					    var temp=response.responseText;
					    var list=eval(temp);
					    	var ss="" ; 
					    	for(var i=1;i<=list.length;++i){
					    		
					    		var listat=list[i-1];
					    		
					    	 ss+="<div  id='"+listat.eduid+"_"+i+"_dele' style='width:100%'>"
					        	 +"<table  class='xiechengsheng4' cellspacing='0'> "
						          +" <tr>" 
						              +"<td id='"+listat.eduid+"_"+i+"_shu' width='10%' height='40' class='xiechengsheng3'>"+i+"</td>"
						                +"<td width='90%'  class='xiechengsheng' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名："+listat.appraser+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			                               if(""!=listat.apprasial){                                          
			                            	   ss+=" 日期：<input  id='"+listat.eduid+"_"+listat.apprasialid+"_"+i+"_Date'  type='text'  size='10' value='"+listat.signDate+"' idvalue='"+listat.signDate+"' onblur='update_time("+listat.eduid+","+listat.apprasialid+","+i+")' onClick='WdatePicker()' />";
			                               }
			                               if (""==listat.apprasial){                                             
			                            	   ss+="日期：<input  id='"+listat.eduid+"_"+listat.apprasialid+"_"+i+"_Date'  type='text' size='10'  value='"+listat.signDate+"' idvalue='"+listat.signDate+"' onClick='WdatePicker()'/>";
			                               }
			                              ss+="</td>"
					                 +"</tr>"
					              +"</table>"; 
					          if(null!=listat.apprasial&&""!=listat.apprasial){ 
					        	  ss+="<div class='name_con' style='background:#eee;' id='wuyong1_"+i+"'>"
										+"<div  name='xiaomao' class='fl neirong' style='height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;' id='"+listat.eduid+"_"+i+"_content' idvalue='"+listat.apprasial+"'>"
											+"<textarea id= '"+listat.eduid+"_"+i+"_appered' style='background: transparent; overflow:auto; border-style: none; height: 120px; width: 99%;' onclick='return change_content_is_available("+listat.apprasialid+","+listat.eduid+","+i+",true);'>"+listat.apprasial+"</textarea>"
									
										+"</div>" 
						            	+"<div class='fr btnxie' name='delectNull_m' id='wuyong3_"+i+"' style='width: 7%'>"
						           	  		
						          	  		+"<a id='btnxie_001' href='#' onclick='javascript:removeAppraise("+listat.apprasialid+","+listat.eduid+","+i+");' class='local delect'></a>"
						           	 
						           	+"</div>" 
						             +"</div>"; 
					           }   
					           if(null==listat.apprasial||""==listat.apprasial){ 
					        	 ss+="<div class='name_con ' id='wuyong5_"+i+"'>"
					        	  
					        	   	+" <div name='xiaomao' class='fl neirong ' style='height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;' id='"+listat.eduid+"_"+i+"_content' idvalue='' >"
										+"<textarea id='"+listat.eduid+"_new_apprise_content_xie' style='background: transparent; border-style: none; height: 120px; width: 99%;' onclick='return change_content_is_availableNll("+listat.apprasialid+","+listat.eduid+","+i+",true);'></textarea>"
								
									+"</div>" 
					            	 +"<div class='fr btnxie' name='delectNull_m' id='wuyong7_"+i+"' style='width: 7%'>"
					            	    
					            	  		+"<a id='btnxie_001' href='#' onclick='javascript:delectNull("+listat.apprasialid+","+listat.eduid+");'  class='local delect'></a>"
					            	   
					            	+"</div>" 
					        +"</div>";
					         }  
					      if(list.length==i){
							      if($.trim(listat.apprasial)){
							      
							       ss+="<div class='name_cxbox '  id='"+listat.eduid+"_"+i+"_add' >"
						            +" <div name='xiaomao' class='fl neirong' style=' margin-top:0px; width:89.6%;pxoverflow:auto;' > </div>"
							       
						            +"<div class='fr btnxie' id='wuyong55' name='delectNull_m'  style='width: 7%'>"
						        			+" <a href='#' id='add' style='margin-top: 7px;' class='fl zengjia ' onclick='addApprasial("+listat.eduid+","+i+");' ></a> "
						            +" </div>"
						         +"</div>";
							      
							      } 
					       }   
					         if(list.length>i){
						        ss+="<div class='name_cxbox'  id='"+listat.eduid+"_"+i+"_add'  style='display: none;'>"
						            +" <div name='xiaomao' class='fl neirong' style=' margin-top:0px; width:89.6%;pxoverflow:auto;' > </div>"
							       
						            +"<div class='fr btnxie' id='wuyong55' name='delectNull_m'  style='width: 7%'>"
						        			+" <a href='#' id='add' style='margin-top: 7px;' class='fl zengjia ' onclick='addApprasial("+listat.eduid+","+i+");' ></a> "
						            +" </div>"
						         +"</div>";
					         
					         
					         }
					         ss+="</div>"
					    }
					    	 var newpaerdiv=document.getElementById("111_xie");
					         newpaerdiv.innerHTML=""; 
					         newpaerdiv.innerHTML=ss; 
					         readying();	
					  
				},
				params : {
				     eduId :eduId,
				     evaluateType : $("#evaluateType").val(),
				     termId:$("#termId").val(),
				     pStudentid:studentid,
				}
			}); 
	 }
	 
	 
	 
}

</script>




<body>

<body>
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
  
 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	 <span >  学期: 
    	 <select style="border:1px #666 solid;"  name="termId" id="termId" onchange="changeterm()">
               <app:highSchoolTermTag  levelCode="${levelCode}" selectNum="${termId}" selectClassid="${classid}"/> 
        </select> 
    </span>
      
 </div>
	
 <div class="s_content" >
		<div class="nj">
			${className}
		</div>
		<div class="ns">
			<div class="names" id="xingmingdsl">
			</div>
		</div>
 </div>
 
	  <div class="s_content" style="background-color:rgb(39, 159, 70); margin-top: 19px">
	 	<div  style="position:absolute;z-index:100;" > <img class="img-padding" id="photoUrl" src="${photoUrl}"/></div> 
	  	<div class="title_xie" style="overflow:hidden; width:50%;"  id="evaluateName_name">${evaluateName} </div>
    </div>
	 
             <div id="tianjian" style="display: none;"></div>
	   <div id="new_apprise" style='display:none ;'>
	    		<input id="new_studentid"  type="hidden" name="new_studentid" value=""/> 
	        	<input id="new_numshu"  type="hidden"  value=""/> 
	        	 
	        	  <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="new_shu" width="10%" height="40" class="xiechengsheng3"></td>
			                <td  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							                  日期：<input  id="newDate"  type="text" size="10" value="${time}" idvalue="${time}" onClick="WdatePicker()"/></td>
		                 </tr>
		              </table>
	        	<div class="name_con " id="new1_1" >
		        	     <div name="xiaomao" style="height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;" class="fl neirong " id="new2_1">
		        			 <textarea   id="new_apprise_content"  style="height:120px; width:99%; overflow:auto;" onclick="romvezi()" onblur="return saveApprasial()"></textarea> 
						  </div>          
	        	          <div class="fr btnxie" id="new3_1" name="delectNull_m"  style="width: 7%">
		            	  		<a id="btnxie_001" href="#" onclick="javascript:delectNewAdd();" class="local delect"></a>
		            	 </div>
	        	</div>
	        
	           <div class="name_cxbox " id="new5_1">
					         <div name="xiaomao" class="fl neirong" style=" margin-top:0px; width:89.6%;pxoverflow:auto;" > </div>
					      <div class="fr btnxie" id="wuyong7_${status.index+1}" name="delectNull_m"  style="width: 7%">
			            	  	<a href="#" style="margin-top: 7px;"  class="fl zengjia "  ></a> 
			               </div>
			   </div>
	        
	</div> 
	        
      
				<input id="evaluateName" type="hidden" value="${evaluateName}"/>
				<input id="evaluateType" type="hidden" value="${evaluateType}"/>
				<input id="pStudentid" type="hidden" value="${pStudentid}"/>
				<input id="eduid" type="hidden" value="${eduid}"/>
				
				
				
		<div id="pj_jiaoshi_main1" style="display: none;">
				 <div class="layout" >
		        <div id="111_xie"  >
		           <c:forEach items="${listApraisal}"  var="listat"  varStatus="status">
		        	<div  id="${listat.eduid}_${status.index+1}_dele" style="width:100%">
		        	 <table  class="xiechengsheng4" cellspacing="0"> 
			           <tr> 
			              <td id="${listat.eduid}_${status.index+1}_shu" width="10%" height="40" class="xiechengsheng3">${status.index+1}</td>
			                <td width="90%"  class="xiechengsheng" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <c:if test="${not empty listat.apprasial}">                                            
							                  日期：<input  id="${listat.eduid}_${listat.apprasialid}_${status.index+1}_Date"  type="text"  size="10" value="${listat.signDate}" idvalue="${listat.signDate}" onblur="update_time(${listat.eduid},${listat.apprasialid},${status.index+1})" onClick="WdatePicker()" />
		                       </c:if>
                               <c:if test="${empty listat.apprasial}">                                            
							                  日期：<input  id="${listat.eduid}_${listat.apprasialid}_${status.index+1}_Date"  type="text" size="10" value="${time}" idvalue="${time}" onClick="WdatePicker()"/>
		                       </c:if></td>
		                 </tr>
		              </table>
		        	<c:if test="${not empty listat.apprasial}">
		        	<div class="name_con " style="background:#eee;" id="wuyong1_${status.index+1}">
						 <div class="fl neirong "  name="xiaomao"  style="height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;" id="${listat.eduid}_${status.index+1}_content" idvalue="${listat.apprasial}" >
							<textarea id= "${listat.eduid}_${status.index+1}_appered" style="background: transparent; overflow:auto; border-style: none; height: 120px; width: 99%;" onclick="return change_content_is_available(${listat.apprasialid},${listat.eduid},${status.index+1},true);">${listat.apprasial}</textarea>
						</div>
		            	<div class="fr btnxie" id="wuyong3_${status.index+1}" name="delectNull_m" style="width: 7%">
		            	  		<a  id="btnxie_001" href="#" onclick="javascript:removeAppraise(${listat.apprasialid},${listat.eduid},${status.index+1});" class="local delect"></a>
		            	</div>
		             </div>
		        </c:if>
		        
		        <c:if test="${empty listat.apprasial}">
		          <div class="name_con " id="wuyong5_${status.index+1}">
						 <div name="xiaomao" class="fl neirong" style="height:131px; margin-top:0px; width:89.6%;pxoverflow:auto;" id="${listat.eduid}_${status.index+1}_content" idvalue="" >
								<textarea id="${listat.eduid}_new_apprise_content_xie" style="background: transparent; border-style: none; height: 120px; width: 99%;" onclick="return change_content_is_availableNll(${listat.apprasialid},${listat.eduid},${status.index+1},true);">${listat.apprasial}</textarea>
						</div>
		            	<div class="fr btnxie" id="wuyong7_${status.index+1}" name="delectNull_m"  style="width: 7%">
		            	  		<a  id="btnxie_001" href="#" onclick="javascript:delectNull(${listat.apprasialid},${listat.eduid});"  class="local delect"></a>
		            	</div>
		        </div>
		        </c:if>
		          
		          
		          
		        <c:if test="${status.count==fn:length(listApraisal)}"> 
				    <c:if test="${not empty listat.apprasial}"> 
				     
				        <div class="name_cxbox "   id="${listat.eduid}_${status.index+1}_add" >
					         <div name="xiaomao" class="fl neirong" style=" margin-top:0px; width:89.6%;pxoverflow:auto;" > </div>
						       
					      <div class="fr btnxie" id="wuyong7_${status.index+1}" name="delectNull_m"  style="width: 7%">
			            	   <a  href="#" id="add" style="margin-top: 7px;" class="fl zengjia " onclick="addApprasial(${listat.eduid},${status.index+1});" ></a> 
			               </div>
				        </div>
			           </c:if> 
		         </c:if>  
		     <c:if test="${status.count!=fn:length(listApraisal)}">
			         <div class="name_cxbox "   id="${listat.eduid}_${status.index+1}_add" style="display: none;" >
					         <div name="xiaomao" class="fl neirong" style=" margin-top:0px; width:89.6%;pxoverflow:auto;" > </div>
						       
					      <div class="fr btnxie" id="wuyong7_${status.index+1}" name="delectNull_m"  style="width: 7%">
			            	   <a  href="#" id="add" style="margin-top: 7px;" class="fl zengjia" onclick="addApprasial(${listat.eduid},${status.index+1});" ></a> 
			               </div>
				        </div>
		          </c:if>  
		       </div>
		      </c:forEach>
			</div>			
			   
			
		 </div> 
	
	   
     

     </div> 

</body>
</html>








