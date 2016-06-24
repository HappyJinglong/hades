<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 <%@ include file="/common/mass.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

			<%--   public static final String TYPE_SX_TRPJ="3020";//他人评价思想道德
			   public static final String TYPE_HZ_TRPJ="4020";//他人评价
			   public static final String TYPE_YDJK_TRPJ="5020";//他人评价运动与健康
			   public static final String TYPE_SMYBX_TRPJ="6020";//他人评价审美与表现
			   public static final String TYPE_GXFZ_TRPJ="7030";//他人评价个性发展
			   public static final String TYPE_XY="8040";//学业
			   Constant.TYPE_SX_ZWPJ;
 <un:bind var="TYPE_SX_TRPJ" type="com.flyrish.hades.common.Constant" field="TYPE_SX_TRPJ"/>  --%>




<title></title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link href="${ctx}/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/iframe.css" rel="stylesheet" type="text/css" />
 <link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" /> 
 <link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/func.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
</head>

<style type="text/css">
 html,body{ margin-bottom:100px;

     overflow-x:hidden;
	 overflow-y:hidden; 

} 
#pj_ziwo_main .biaoge .shanchu1{
	margin-right :20px;
}
#pj_ziwo_main .biaoge .shanchu{
		margin-right :20px;
}
#pj_ziwo_main{ margin-bottom :20px; top:29px;padding:13px}

</style>

 <script type="text/javaScript">
 /* <img src='${ctx}/images/xiegx.png'> */
  $(window).load(function() {
	 var thisMession=document.getElementById("mession");
	 thisMession.innerHTML="";
	 thisMession.innerHTML=mess;  
	  });

 
 
 var  addmove="notmove"; 
 
 
//文字限制提示
	$(document).ready(function(){
	      
		var textareas=$("body").find("textarea");	
			for(var i=0;i<textareas.size();++i)
			{ 
				var id_content=$(textareas[i]).attr("id");
				 if(id_content.indexOf("_tishixie")>=0) 
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
				 if(id_content.indexOf("_tishixie")>=0) 
				 {  
					 $("#"+id_content).css("color","#ACACAC"); 
					 $("#"+id_content).val("最多输入4000字");
				 } 
			}
	}

	
	function romvezi(type){
		$("#new_"+type+"_content_tishixie").css("color","");
		$("#new_"+type+"_content_tishixie").val("");
		$("#new_"+type+"_content_tishixie").unbind('click');
		$("#new_"+type+"_content_tishixie").removeAttr("onclick");
		text_style1("new_"+type+"_content_tishixie");
		//<textarea   id="new_${playList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%; overflow:auto;border:solid 1px;" onclick="romvezi(${playList[0].appraisaltypeid})" onblur="return saveApprasial(${playList[0].appraisaltypeid},${playList[0].studentid})"></textarea>
	}
 
 
 
 var flag_full = false;

	function addApprasial(nn){ 
		var textareas=$("#"+nn+"_table_zong").find("table");
		var trsize=textareas.size();
		var newlength=Number(trsize)+Number(1);
		$('#'+nn+'_addlength').html(newlength);
		var idv=nn+"_coent";
		flag_full=true;
		var newDiv = Ext.get(idv);
		newDiv.setDisplayed("");
		
		readying();
	}
	
	 //添加删除
	 function delect(typeid,stuid) {
		 if(confirm( "确定要删除?" )) {
			 var apappraisal="new_"+typeid+"_content_tishixie";
			 var apprasialDelect=$('#'+apappraisal+'').val();
			 var length=apprasialDelect.length;
			if(apprasialDelect!=null&&apprasialDelect!=""&&apprasialDelect!=undefined&&apprasialDelect!="最多输入4000字"){
			if(length>0){
				var apperTrim=apprasialDelect.trim();
			  	if(apperTrim==null||apperTrim==""||apperTrim==undefined){
			  		$('#'+typeid+'_coent').css("display","none");
			  	}else{
					   
				  	 if(length>4000){ 
						    $("#new_"+typeid+"_content_tishixie").bind('click',function(){
			         			romvezi(typeid);
			         		});
						    $("#button_"+typeid).bind('click',function(){
								addApprasial(typeid);
				     		});
							if(typeid==3020){
									alert_delete_success("思想道德");
							};if(typeid==8040){
								     alert_delete_success("学业成绩");
							}; if(typeid==4020){
										alert_delete_success("合作与交流");
							}; if(typeid==5020){
									alert_delete_success("运动与健康");
							};if(typeid==6020){
								alert_delete_success("审美与表现");
							}; if(typeid==7030){
								alert_delete_success("个性发展");
							};
								$('#new_'+typeid+'_content_tishixie').val("");
							   $('#'+typeid+'_coent').css("display","none"); 
							
					   }else{
						   addmove="move";
						   }  
			  		
					}
			 }
			 }else{
				  $('#'+typeid+'_coent').css("display","none");
				  if(typeid==3020){
						alert_delete_success("思想道德");
					};if(typeid==8040){
						alert_delete_success("学业成绩");
					}; if(typeid==4020){
						alert_delete_success("合作与交流");
					}; if(typeid==5020){
						alert_delete_success("运动与健康");
					};if(typeid==6020){
						alert_delete_success("审美与表现");
					}; if(typeid==7030){
						alert_delete_success("个性发展");
					};
				  readying();
			 }
		 }
	 }
	
	//新加的保存 <textarea   id="new_${penseeList[0].appraisaltypeid}_content_tishixie"
	function saveApprasial(kk,studentid){
		var width = $("#bg_eee_1_1").attr("width");
		setTimeout(function () { 
		var apprasial =$('#new_'+kk+'_content_tishixie').val();
		 $("#button_"+kk).removeAttr("onclick"); 
		 $("#button_"+kk).unbind('click');
		
		document.getElementById("new_"+kk+"_content_tishixie").style.border ="0px solid";
		var leng=$('#new_'+kk+'_content_tishixie').val().trim();
		
		if(leng=="最多输入4000字"){
			$("#button_"+kk).bind('click',function(){
				addApprasial(kk);
     		});
            return; 	
		}
		if(leng.length==0){
			$("#new_"+kk+"_content_tishixie").bind('click',function(){
     			romvezi(kk);
     		});
			$("#button_"+kk).bind('click',function(){
				addApprasial(kk);
     		});
			readying();
		}else{
			var trimApprasial=$('#new_'+kk+'_content_tishixie').val();
         	var ss=leng.length;
         	if(ss<=4000){
         		$("#new_"+kk+"_content_tishixie").bind('click',function(){
         			romvezi(kk);
         		});
         		
				var newDiv = Ext.get(kk+"_coent");
			 //if(document.getElementById("new_apprise_content").value.length<=4000){
				Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
				//setTimeout(
				Ext.Ajax.request({
					url:'${ctx}/student/ZtreeAction.a?doUpdataOtherProcessNull',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var apprasialid=len.apprasialid;
					    if(success=="true")
					    {    
					    	if(addmove=="move"){
					    		addmove="nemove";
				    			Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
						    	Ext.Ajax.request({  
									url:'${ctx}/student/ZtreeAction.a?deletOtherProcess',
									method:'POST',
									defaults:{autoScroll: true},
									success:function(response,options){
										  var len=Ext.util.JSON.decode(response.responseText);
										    var success=len.success.replace( /^\s+|\s+$/g,"");
										    if(success=="true")
										    {   
										    	
										    	if(kk==3020){
													alert_delete_success("思想道德");
												};if(kk==8040){
													alert_delete_success("学业成绩");
												}; if(kk==4020){
													alert_delete_success("合作与交流");
												}; if(kk==5020){
													alert_delete_success("运动与健康");
												};if(kk==6020){
													alert_delete_success("审美与表现");
												}; if(kk==7030){
													alert_delete_success("个性发展");
												};
										    	
										    	$('#new_'+kk+'_content_tishixie').val("");
										        $('#'+kk+'_coent').css("display","none");
										    
										    }else if(success=="false")
										    {
										    	return;
										    }
									},
									params : { 
										apprasialid:apprasialid
									
									}
								});
						    	$("#button_"+kk).bind('click',function(){
				    				addApprasial(kk);
				         		});
					    	}else{   
					    	
							var signDate=document.getElementById(kk+"_newDate").value;
							 var num2=$('#'+kk+'_addlength').html();//显示的数
							//查询最大的数3020_table_zong
							 var pNum=0;
							var divs=$("#"+kk+"_table_zong").find("table");
					    	 	for(var i=0;i<divs.size();++i)
								{ 
									var id_content=$(divs[i]).attr("id");
										 ++pNum;
										 var arr = new Array();
										 arr[pNum]=id_content;
								} 
					    		    var lengthDiv=arr[pNum];
									var  lengthpid=lengthDiv.split("_"); //字符分割 
									var  num9=lengthpid[2];
									var num=Number(num9)+Number(1);
						   var dddiv="<table id='zi_"+kk+"_"+num+"' width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge' >" 
						    +"<tr>"  
						      +"<td width='10%' height='40' class='bg_eee' id='"+kk+"_jishu_"+num+"'>"+num2+"</td>"
						          +"<td colspan='2' class='youjuzhongs pr20 bg_eee' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			                            +" 日期：<input id='"+apprasialid+"_"+kk+"_"+num+"_Date' type='text' size='10' value='"+signDate+"' idvalue='"+signDate+"'onblur='update_time("+apprasialid+","+kk+","+num+","+num2+")' onClick='WdatePicker()'/></td>"
					         +"</tr>"
					         +"<tr>"                                                                                                                                                        
							       +"<td colspan='2' class='pl20 pr20 pb20 pt10 zuojuzhong' style='height:130px;background:#eee;' id='"+kk+"_"+num+"' idvalue='"+apprasial+"'>"
						                     	+"<textarea id='"+kk+"_apprasial_"+num+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_available("+studentid+","+apprasialid+","+kk+","+num+",true);'>"+apprasial+"</textarea>"
						          +"</td>" 
							      +"<td width='"+width+"' class='bg_eee' id='bg_eee_1_1'>"
							         +"<span class='fr btn'>"                                                                                            
								       		+"<input type='button' class='fr shanchu1' onclick='removeAppraise("+apprasialid+","+studentid+","+kk+","+num+");'/>"
							         +"</span>"
							      +"</td>"
					           +"</tr>" 
						    +"</table>"; 
					    	
						    var cun=document.getElementById(kk+"_cun");
					    	cun.innerHTML="";
					    	cun.innerHTML=dddiv; 
					        
					    	$("#"+kk+"_coent").css("display","none");
							var newAdddiv=$("#zi_"+kk+"_"+num);
							
							var addwei=$("#"+lengthDiv);
							
							newAdddiv.insertAfter(addwei);
					      
	                       if(addmove!="move"){
					    		if(kk==3020){
									alert_save_success("思想道德",num2);
								};if(kk==8040){
									alert_save_success("学业成绩",num2);
								}; if(kk==4020){
									alert_save_success("合作与交流",num2);
								}; if(kk==5020){
									alert_save_success("运动与健康",num2);
								};if(kk==6020){
									alert_save_success("审美与表现",num2);
								}; if(kk==7030){
									alert_save_success("个性发展",num2);
								};
	                       }
	                       $("#button_"+kk).unbind('click');
	                       $("#button_"+kk).bind('click',function(){
	           				addApprasial(kk);
	                		});
	                        
				    		}
					    }else if(success=="false")
					    {
					    	 $("#button_"+kk).bind('click',function(){
									addApprasial(kk);
					     		});
					    	//$("#"+id).focus();
					    	return;
					    }
						readying();
					},
					params : {
						apprasial:$('#new_'+kk+'_content_tishixie').val(),
						id:studentid,
						evaluateType :kk,
						signDate :document.getElementById(kk+"_newDate").value,
					}
				});
				
		 }else{
			alert("评价内容已超过4000字");
		 }
		}
		
	 }, 50);
		}
	
	//使得内容变为可写studid
	 var content_div_full="";
	function change_content_is_available(studid,apprid,applty,id,flag){
		if(id==null||id==""||studid==null||studid==""){
		   
		}else{
			if(flag==true){ 
				var this_div = document.getElementById(applty+"_"+id);
				this_div.focus();
				 this_div.parentNode.onclick=null;
				 this_div.onclick=null;
				this_div.setAttribute("style","color:#FFFFFF;height:130px;");
				this_div.innerHTML='<textarea  id="'+applty+'_apprasial_'+id+'"   style="height:130px; width:100%; border:solid 1px;"  onblur="return change_content_is_available('+studid+','+apprid+','+applty+','+id+',false)"></textarea>';
				document.getElementById(applty+"_apprasial_"+id).value=this_div.attributes["idvalue"].nodeValue;
				document.getElementById(applty+"_apprasial_"+id).focus(); 
				$("#"+applty+"_apprasial_"+id).focus();
			    content_div_full=this_div.attributes["idvalue"].nodeValue;
			   //content_div_full=document.getElementById(apprid+"_apprasial_"+id).innerText;
			}else{
				var this_div = document.getElementById(applty+"_"+id);
				content_div_full=this_div.attributes["idvalue"].nodeValue;
				var app=$("#"+applty+"_apprasial_"+id).val().trim();
				var leng=app.length;
				if(leng>0){
					var appNotnull=$("#"+applty+"_apprasial_"+id).val().length;
				if(appNotnull<=4000){
					var appNot=$("#"+applty+"_apprasial_"+id).val();
				if(appNot!=content_div_full){
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/ZtreeAction.a?doUpdataOtherProcess',
						method:'POST',
						success:function(response,options){
							var len=Ext.util.JSON.decode(response.responseText);
							var success=len.success;
						    var info=len.info;
						    
						    if(success=="true")
						    {   
						    	if(applty==3020){
									alert_save_success("思想道德",id);
								};if(applty==8040){
									alert_save_success("学业成绩",id);
								}; if(applty==4020){
									alert_save_success("合作与交流",id);
								}; if(applty==5020){
									alert_save_success("运动与健康",id);
								};if(applty==6020){
									alert_save_success("审美与表现",id);
								}; if(applty==7030){
									alert_save_success("个性发展",id);
								};
								var ppar=$("#"+applty+"_apprasial_"+id).val();
								this_div.setAttribute("style","height:130px;background:#eee;");
								this_div.setAttribute("idvalue",ppar);
								this_div.innerHTML="";
								this_div.innerHTML="<textarea id='"+applty+"_apprasial_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return  change_content_is_available("+studid+","+apprid+","+applty+","+id+",true);'>"+ppar+"</textarea>"; 
								//this_div.parentNode.onclick=function(){change_content_is_available(studid,apprid,applty,id,true); onclick='return change_content_is_availableNull("+stud+",0,"+evalll+",1,true);'}
						    	
						    }else if(success=="false")
						    {
						    	alert_g(info);
						    	$("#"+id).focus();
						    	return;
						    }
						},
						params : {
							 id:studid,
							apprasialid:apprid,
							apprasial:$("#"+applty+"_apprasial_"+id).val(),
							evaluateType:applty, 
							signDate : document.getElementById(apprid+"_"+applty+"_"+id+"_Date").value  
						}
					});
				}else{  var ppar=$("#"+applty+"_apprasial_"+id).val();
				        this_div.setAttribute("style","height:130px;background:#eee;");
						//this_div.setAttribute("idvalue",document.getElementById("idcontent").value);				
						this_div.innerHTML="<textarea id='"+applty+"_apprasial_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return  change_content_is_available("+studid+","+apprid+","+applty+","+id+",true);'>"+ppar+"</textarea>";         
						//this_div.parentNode.onclick=function(){change_content_is_available(studid,apprid,applty,id,true); onclick='return change_content_is_availableNull("+stud+",0,"+evalll+",1,true);'}
				}
			}else{
				  alert("评价内容已超过4000字");
			  }
			}
			}
		}
	}	 
		
	
	//内容为空时的修改保存评价
	 var content_div_full="";
		function change_content_is_availableNull(studid,apprid,applty,id,flag){
			if(id==null||id==""||studid==null||studid==""){
			   
			}else{
				var this_div = document.getElementById(applty+"_"+id);
				this_div.focus();
				if(flag==true){
					this_div.parentNode.onclick=null;
					this_div.innerHTML='<textarea  id="idcontent" name="idcontent"   style="height:130px; width:100%; overflow:auto;border:solid 1px;"  onblur="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',false)"></textarea>';
					document.getElementById("idcontent").focus(); 
					$("#idcontent").focus();
				}else{
					var app=$("#idcontent").val().trim();
					
					if(app=="最多输入4000字"){
			            return; 	
					}
					
					var leng=app.length;
					if(leng>0){
					var apperl=$("#idcontent").val().length;
					if(apperl<=4000){
						
				/* 	if(app!=content_div_full&&app!=null&&app!=""){ */
						Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
						Ext.Ajax.request({
							url:'${ctx}/student/ZtreeAction.a?doUpdataOtherProcessNull',
							method:'POST',
							success:function(response,options){
								var len=Ext.util.JSON.decode(response.responseText);
							    var success=len.success.replace( /^\s+|\s+$/g,"");
							    var apprasialid=len.apprasialid;
							    if(success=="true")
							    {   
							    	  
							    	if(addmove!="move"){
							    	if(applty==3020){
										alert_save_success("思想道德",id);
									};if(applty==8040){
										alert_save_success("学业成绩",id);
									}; if(applty==4020){
										alert_save_success("合作与交流",id);
									}; if(applty==5020){
										alert_save_success("运动与健康",id);
									};if(applty==6020){
										alert_save_success("审美与表现",id);
									}; if(applty==7030){
										alert_save_success("个性发展",id);
									};
							          }
							    		if(addmove=="move"){
							    			Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
									    	Ext.Ajax.request({  
												url:'${ctx}/student/ZtreeAction.a?deletOtherProcess',
												method:'POST',
												defaults:{autoScroll: true},
												success:function(response,options){
													  var len=Ext.util.JSON.decode(response.responseText);
													    var success=len.success.replace( /^\s+|\s+$/g,"");
													    if(success=="true")
													    {     
														    if(applty==3020){
																alert_delete_success("思想道德");
															};if(applty==8040){
																alert_delete_success("学业成绩");
															}; if(applty==4020){
																alert_delete_success("合作与交流");
															}; if(applty==5020){
																alert_delete_success("运动与健康");
															};if(applty==6020){
																alert_delete_success("审美与表现");
															}; if(applty==7030){
																alert_delete_success("个性发展");
															};
													           addmove="notmove";
														       //this_div.setAttribute("idvalue","");				
															   //this_div.innerText="";
															  // this_div.parentNode.onclick=function(){change_content_is_availableNull(studid,apprid,applty,id,true);}
														     
															   
															   
																//var this_div = document.getElementById(applty+"_"+id);
																  this_div.setAttribute("idvalue","");				
															       var tishi="最多输入4000字";                                                                                                                                                                   
																	this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',true)">'+tishi+'</textarea>';
															   
															   
													    }else if(success=="false")
													    {
													    	return;
													    }
												},
												params : { 
													apprasialid:apprasialid
												
												}
											});
							    		}else{	
							    		$("#"+applty+"_add").css("display","block");
								    	var ppar=$("#idcontent").val();
								    	this_div.setAttribute("style","height:130px;background:#eee;");
										this_div.setAttribute("idvalue",ppar);
										this_div.innerHTML="";
										this_div.innerHTML="<textarea id='"+applty+"_apprasial_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;'>"+ppar+"</textarea>"; 
										this_div.parentNode.onclick=function(){change_content_is_available(studid,apprasialid,applty,id,true);}
										 var newtime=document.getElementById(apprid+"_"+applty+"_"+id+"_Date");
										 newtime.onblur=function(){update_time(apprasialid,applty,"1","1")};
										var addnewtimeid=apprasialid+"_"+applty+"_"+id+"_Date";
                                        $("#"+apprid+"_"+applty+"_"+id+"_Date").attr("id",addnewtimeid);
                                        var newdelect=document.getElementById("delect_"+applty+"_"+id);
										newdelect.onclick=function(){removeAppraise(apprasialid,studid,applty,id)}; 
										
							      }
							    }else if(success=="false")
							    {
							    	alert_g(info);
							    	$("#"+id).focus();
							    	return;
							    }
							},
							params : {
								 id:studid,
								apprasialid:apprid,
								apprasial:$("#idcontent").val(),
								evaluateType:applty,
								signDate : document.getElementById(apprid+"_"+applty+"_"+id+"_Date").value  
						  }
						});
					/* }else{  
						     var apprasialller=$("#idcontent").val().trim();
                             this_div.innerHTML="";
                             this_div.innerHTML="<textarea id='"+applty+"_apprasial_1_tishixie' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_availableNull("+studid+","+apprid+","+applty+","+id+",true);'>"+apprasialller+"</textarea>";
                             readying();
					} */
				 }else{
					  
					 alert("评价内容已超过4000字");
				  }
				 }else{
					 this_div.innerHTML="";
                     this_div.innerHTML="<textarea id='"+applty+"_apprasial_1_tishixie' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_availableNull("+studid+","+apprid+","+applty+","+id+",true);'></textarea>";
                     readying();
				 }
				}
			}
		}	 

	 function removeAppraise(appalid,stud,evalll,num) {
		 var width = $("#bg_eee_1_1").attr("width");
		 if(confirm( "确定要删除?" )) { 
				    Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		    		Ext.Ajax.request({
					url:'${ctx}/student/ZtreeAction.a?deletOtherProcess',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
							var len=Ext.util.JSON.decode(response.responseText);
							var success=len.success.replace( /^\s+|\s+$/g,"");
							
						    if(success=="true")
						    {
						    	   if(evalll==3020){
										alert_delete_success("思想道德");
									};if(evalll==8040){
										alert_delete_success("学业成绩");
									}; if(evalll==4020){
										alert_delete_success("合作与交流");
									}; if(evalll==5020){
										alert_delete_success("运动与健康");
									};if(evalll==6020){
										alert_delete_success("审美与表现");
									}; if(evalll==7030){
										alert_delete_success("个性发展");
									};
						    	    var unmcont=0;
							    	var textareas=$("#"+evalll+"_table_zong").find("table");
							    	var trsize=textareas.size();
							    	if(trsize>1){
						    			//<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
						    			$("#zi_"+evalll+"_"+num).remove();
						    			 var textareas1=$("#"+evalll+"_table_zong").find("table");
						    			 var trsize1=textareas1.size();
						    			 for(var i=0;i<trsize1;i++){
						    				 ++unmcont;
						    				  var id_content=$(textareas1[i]).attr("id");
						    				  var  lengthpid=id_content.split("_"); //字符分割 
										      var  numty=lengthpid[2];
										      $("#"+evalll+"_jishu_"+numty).html("");
										      $("#"+evalll+"_jishu_"+numty).html(unmcont); 
										      $("#"+evalll+"_jishu_"+numty).val(unmcont); 
						    			 }  
						    			}else{   
						    				var temp1=$("#"+evalll+"_coent").is(":visible");
						    				if(temp1){
						    					$("#"+evalll+"_coent").css("display","none");	
						    				}
						    				 $("#zi_"+evalll+"_"+num).remove();
						    	           var FistDiv="<table id='zi_"+evalll+"_1' width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge' >" 
						    				    +"<tr>" 
						    				      +"<td id='evalll_jishu_1' width='10%' height='40' class='bg_eee'>1</td>"
						    				          +"<td colspan='2' class='youjuzhongs pr20 bg_eee' >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						    	                             +"日期：<input id='0_"+evalll+"_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time(0,"+evalll+",1,1)' onClick='WdatePicker()'/></td>"
						    			        +"</tr>"
						    				    +"<tr>"                                                                                                                                                        
						    				        +"<td colspan='2' class='pl20 pr20 pb20 pt10 zuojuzhong' style='height:130px;' id='"+evalll+"_1' idvalue=''>"
						    			                   +"<textarea id='"+evalll+"_apprasial_1_tishixie' style='background: transparent; border: 1px solid transparent;color:#ACACAC; height: 130px; width: 100%;' onclick='return change_content_is_availableNull("+stud+",0,"+evalll+",1,true);'>最多输入4000字</textarea>"
						    			             +"</td>" 
						    					     +" <td width='"+width+"' class='bg_eee' id='bg_eee_1_1'>"
						    					      +"<span class='fr btn'>"                                                                                            
						    						      		+" <input id='delect_"+evalll+"_1' type='button' class='fr shanchu1' onclick='delectchu(0,"+stud+","+evalll+",1);'/>"
						    					      +"</span>"
						    					      +"</td>"
						    			           +"</tr>"
						    				      +"</table>";	
						   		var dddiv=document.getElementById(evalll+"_table_zong");
						   		dddiv.innerHTML="";
						   		dddiv.innerHTML=FistDiv;
						   	   $('#'+evalll+'_add').css("display","none");
						   		//id="${penseeList[0].appraisaltypeid}_add"			
						    }
						    	
						    	
						    }else if(success=="false")
						    {
						    	
						    	$("#"+id).focus();
						    	return;
						    }
						    
			  
			},
				params : {
				     apprasialid:appalid,
					 id:stud,
					 evaluateType:evalll
				}
			}); 
		  /*  } */
		   /* } */
	     }  
      }
	 
	 
	 
	 //删除初始为空的
 	 function delectchu(appalid,stud,evalll,num) {
 		
		 if(confirm( "确定要删除?" )) { 
			
			 if($('#idcontent').length>0)  {
				 var leng=document.getElementById('idcontent').value;
				 
				  var lengtrim=leng.trim();
				 
				  if( lengtrim==null||lengtrim==" "||lengtrim==""){
					  apprasial_del_Finish();
					}else{
						var lengthlong=lengtrim.length;
				   if(lengthlong>4000){
							 if(evalll==3020){
									alert_delete_success("思想道德");
							};if(evalll==8040){
								     alert_delete_success("学业成绩");
							}; if(evalll==4020){
										alert_delete_success("合作与交流");
							}; if(evalll==5020){
									alert_delete_success("运动与健康");
							};if(evalll==6020){
								alert_delete_success("审美与表现");
							}; if(evalll==7030){
								alert_delete_success("个性发展");
							};
							   var this_div = document.getElementById(evalll+"_1");
							   this_div.setAttribute("idvalue","");				
						        var tishi="最多输入4000字";                                                                                                                                                                   
								this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+stud+',0,'+evalll+',1,true)">'+tishi+'</textarea>';
						     
								 
					   }else{
						   addmove="move";
						   
					   } 
					}
			 }else{
				 apprasial_del_Finish();
			 }
			 
			 
			 
		 }
	 } 
	
	//更改学期刷新页面
		function changeterm(){
			 ShowDiv();
			var term = document.getElementById("termId").value;  
			var appraiserrid= document.getElementById("appraiserrid_hiden").value;
			url="${ctx}/student/ZtreeAction.a?chaneTermId&evaluatedPersonID="+appraiserrid+"&termId1="+term;
			document.location.replace(url);
		}
	
	
		function alertSave(){
			alert_g("保存成功");
		}
	
	//更改时间
		function update_time(id,ty,num,unmxian)
		{
			
		  //${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date
		if(id==null||id==""||id==0)
			{}else{
				var time1=document.getElementById(id+"_"+ty+"_"+num+"_Date").value;
				var time2=$("#"+id+"_"+ty+"_"+num+"_Date").attr("idvalue");
				if(time1!=time2)
				{      
					//id="${list.appraisaltypeid}_jishu_${status.index+1}"
					var shu=$("#"+ty+"_jishu_"+num).html();
				         if(ty==3020){
								alert_save_success("思想道德",shu);
							};if(ty==8040){
								alert_save_success("学业成绩",shu);
							}; if(ty==4020){
								alert_save_success("合作与交流",shu);
							}; if(ty==5020){
								alert_save_success("运动与健康",shu);
							};if(ty==6020){
								alert_save_success("审美与表现",shu);
							}; if(ty==7030){
								alert_save_success("个性发展",shu);
							}
							$("#"+id+"_"+ty+"_"+num+"_Date").attr("idvalue",time1);
					Ext.Ajax.request({
						url:'${ctx}/student/ZtreeAction.a?doUpadateTime',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
						},
						params : {
							apprasialid:id,
							signDate:time1,
							evaluateType:ty
						}
					});
					
				}	
			}
			
		}
		  
</script>  
<body margin="0">
<%@ include file="/common/div.jsp"%>  
<input id="appraiserrid_hiden"  type="hidden" name="appraiserrid_hiden" value="${evaluatedPersonID}"/> 
<div class="dangqianwz">
 	<span class="fl"> 评价同学：${evaluateName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span>学期：
    	 <select  style="border:1px #666 solid;"  name="termId" id="termId" onchange="changeterm()">
            <app:highSchoolTermTag  levelCode="${levelCode}" selectNum="${termId}" selectClassid="${classid}"/>
        </select> 
    </span> 
 </div>

<div id="pj_ziwo_main">

 
  <div class="top">
     
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr>  
			      <td class="title_bg" colspan="3">思想道德</td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="3020_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${penseeList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td> <%-- value='<fmt:formatDate value="${list.signDate}" type="date"/>' --%>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value='<fmt:formatDate value="${list.signDate}" type="date"/>' idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);"></textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${penseeList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${penseeList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${penseeList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${penseeList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%; border: 1px solid transparent; overflow:auto;" onclick="romvezi(${penseeList[0].appraisaltypeid})" onblur="return saveApprasial(${penseeList[0].appraisaltypeid},${penseeList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${penseeList[0].appraisaltypeid},${penseeList[0].studentid});"/>
				              
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${penseeList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${penseeList[0].apprasial!=' '}">
			   <input id="${penseeList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(penseeList)}"/>
				  <div  class="biaoge" id="${penseeList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${penseeList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${penseeList[0].appraisaltypeid});"/>
			              </div>
			          </div>
				  </div>
	            </c:if>
	             <c:if test="${penseeList[0].apprasial==' '}"><%-- id="${penseeList[0].appraisaltypeid}_add" --%>
				 	<div  class="biaoge" id="${penseeList[0].appraisaltypeid}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${penseeList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${penseeList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${penseeList[0].appraisaltypeid}_cun" style="display: none;" ></div>
	
	 </div>
		     <!--  ------------------------ 学业成就-------------------------- -->
		    
		     <div class="down mt18">
     
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr >  
			      <td  class="title_bg" colspan="3">学业成就</td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="8040_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${successList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${list.signDate}" idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${successList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${successList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${successList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${successList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%; overflow:auto;" onclick="romvezi(${successList[0].appraisaltypeid})" onblur="return saveApprasial(${successList[0].appraisaltypeid},${successList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${successList[0].appraisaltypeid},${successList[0].studentid});"/>
				              
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${successList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${successList[0].apprasial!=' '}">
			   <input id="${successList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(successList)}"/>
				   <div  class="biaoge" id="${successList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${successList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${successList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	             <c:if test="${successList[0].apprasial==' '}"><%-- id="${successList[0].appraisaltypeid}_add" --%>
				   <div  class="biaoge" id="${successList[0].appraisaltypeid}_add" style="display: none;"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${successList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${successList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${successList[0].appraisaltypeid}_cun" style="display:none;"></div>
	
	 </div>
		     <!-- -----------------合作与交流 ----------------------->
		    
		    <div class="down mt18">
     
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr >  
			      <td class="title_bg" colspan="3">合作与交流<span class="red">*</span> </td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="4020_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${cooperateList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${list.signDate}" idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${cooperateList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${cooperateList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${cooperateList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${cooperateList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%; border: 1px solid transparent; overflow:auto;" onclick="romvezi(${cooperateList[0].appraisaltypeid})" onblur="return saveApprasial(${cooperateList[0].appraisaltypeid},${cooperateList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${cooperateList[0].appraisaltypeid},${cooperateList[0].studentid});"/>
				              
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${cooperateList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${cooperateList[0].apprasial!=' '}">
			   <input id="${cooperateList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(cooperateList)}"/>
				    <div  class="biaoge" id="${cooperateList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${cooperateList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${cooperateList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	             <c:if test="${cooperateList[0].apprasial==' '}"><%-- id="${cooperateList[0].appraisaltypeid}_add" --%>
				    <div  class="biaoge" id="${cooperateList[0].appraisaltypeid}_add" style="display: none;"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${cooperateList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${cooperateList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${cooperateList[0].appraisaltypeid}_cun" style="display:none;"></div>
	
	 </div>
		    
		    
		    
		    
		   <div class="down mt18">
     
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr>  
			      <td class="title_bg" colspan="3">运动与健康</td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="5020_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${playList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${list.signDate}" idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${playList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${playList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${playList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${playList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%;border: 1px solid transparent; overflow:auto;" onclick="romvezi(${playList[0].appraisaltypeid})" onblur="return saveApprasial(${playList[0].appraisaltypeid},${playList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${playList[0].appraisaltypeid},${playList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${playList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${playList[0].apprasial!=' '}">
			   <input id="${playList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(playList)}"/>
				   <div  class="biaoge" id="${playList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${playList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${playList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	             <c:if test="${playList[0].apprasial==' '}"><%-- id="${playList[0].appraisaltypeid}_add" --%>
				  <div  class="biaoge" id="${playList[0].appraisaltypeid}_add" style="display: none;"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${playList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${playList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${playList[0].appraisaltypeid}_cun" style="display:none;"></div>
	
	 </div>   
		    
		    
		    <div class="down mt18">
     
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr >  
			      <td class="title_bg" colspan="3">审美与表现</td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="6020_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${tastelList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${list.signDate}" idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${tastelList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${tastelList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${tastelList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${tastelList[0].appraisaltypeid}_content_tishixie"  style="height:130px;border: 1px solid transparent; width:100%; overflow:auto;" onclick="romvezi(${tastelList[0].appraisaltypeid})" onblur="return saveApprasial(${tastelList[0].appraisaltypeid},${tastelList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${tastelList[0].appraisaltypeid},${tastelList[0].studentid});"/>
				              
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${tastelList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${tastelList[0].apprasial!=' '}">
			   <input id="${tastelList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(tastelList)}"/>
				    <div  class="biaoge" id="${tastelList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${tastelList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${tastelList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	             <c:if test="${tastelList[0].apprasial==' '}"><%-- id="${tastelList[0].appraisaltypeid}_add" --%>
				 <div  class="biaoge" id="${tastelList[0].appraisaltypeid}_add" style="display: none;"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${tastelList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${tastelList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${tastelList[0].appraisaltypeid}_cun" style="display:none;"></div>
	
	 </div>   
		    
		    	   <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="3020_xie" idvalue="思想道德">
			    
			    <tr >  
			      <td class="title_bg" colspan="3">个性发展</td>  <!-- 要改的地方 -->
		        </tr>   
		     </table>
		      <div id="7030_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${selfhoodList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table id="zi_${list.appraisaltypeid}_${status.index+1}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" > 
			    <tr> 
			      <td id="${list.appraisaltypeid}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <c:if test="${list.apprasial!=' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${list.signDate}" idvalue="${list.signDate}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
			      <c:if test="${list.apprasial==' '}">
			          <td colspan="2" class="youjuzhongs pr20 bg_eee" >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${appraser}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                          日期：<input id="${list.apprasialid}_${list.appraisaltypeid}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.apprasialid},${list.appraisaltypeid},${status.index+1},${status.index+1})" onClick="WdatePicker()"/></td>
		         </c:if>
		        </tr>
		        
			    <tr>                                                                                                                                                        
			      <c:if test="${list.apprasial!=' '}">                                                                                                                                                 
				       <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;background:#eee;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
			                     	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
			          </td> 
		          </c:if>
		          <c:if test="${list.apprasial==' '}">
			        <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;" id="${list.appraisaltypeid}_${status.index+1}" idvalue="${list.apprasial}">
		                   	<textarea id="${list.appraisaltypeid}_apprasial_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.apprasialid},${list.appraisaltypeid},${status.index+1},true);">${list.apprasial}</textarea>
		             </td> 
			        </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="fr btn">                                                                                            
					      	 <c:if test="${list.apprasial!=' '}"> 
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				          </c:if> 
				            <c:if test="${list.apprasial==' '}">
					      		 <input id="delect_${list.appraisaltypeid}_${status.index+1}" type="button" class="fr shanchu1" onclick="delectchu(${list.apprasialid},${list.studentid},${list.appraisaltypeid},${status.index+1});"/>
				                 
				            </c:if> 
				      </span>
				      </td>
		           </tr>
			      </table>
			     </c:forEach>
			    </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
		      
			   		<table id="${selfhoodList[0].appraisaltypeid}_coent"  style='display:none' width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					    <tr>
					      <td width="10%" height="40px" class="bg_eee" id="${selfhoodList[0].appraisaltypeid}_addlength"></td>
					      <td width="90%" colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${appraser} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					              日期：<input id="${selfhoodList[0].appraisaltypeid}_newDate" type="text" size="10" value="${time}" idvalue="${time}"  onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${selfhoodList[0].appraisaltypeid}_content_tishixie"  style="height:130px; width:100%;border: 1px solid transparent; border: 1px solid transparent; overflow:auto;" onclick="romvezi(${selfhoodList[0].appraisaltypeid})" onblur="return saveApprasial(${selfhoodList[0].appraisaltypeid},${selfhoodList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
					          <input type="button" class="fr shanchu1" onclick="delect(${selfhoodList[0].appraisaltypeid},${selfhoodList[0].studentid});"/>
				              
					       </span> 
					      </td>
				        </tr>
		            </table> 
				        
				                  <!-- 要改的地方 -->
				   <input id="${selfhoodList[0].appraisaltypeid}_apprasialid"  type="hidden"  value=""/>
			   <c:if test="${selfhoodList[0].apprasial!=' '}">
			   <input id="${selfhoodList[0].appraisaltypeid}_length"  type="hidden"  value=" ${fn:length(selfhoodList)}"/>
				   <div  class="biaoge" id="${selfhoodList[0].appraisaltypeid}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${selfhoodList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${selfhoodList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	             <c:if test="${selfhoodList[0].apprasial==' '}"><%-- id="${selfhoodList[0].appraisaltypeid}_add" --%>
				<div  class="biaoge" id="${selfhoodList[0].appraisaltypeid}_add" style="display: none;"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${selfhoodList[0].appraisaltypeid}" type="button" class="fr shanchu"   onclick="addApprasial(${selfhoodList[0].appraisaltypeid});"/>
			              </div>
			          </div>
					 </div>
	            </c:if>
	    <div id="${selfhoodList[0].appraisaltypeid}_cun" style="display:none;"></div>
	 </div>   
  </div>
</body>
</html>
	  
