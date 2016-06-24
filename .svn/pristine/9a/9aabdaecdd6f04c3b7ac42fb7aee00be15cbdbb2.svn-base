<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/mass.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
 
		  <!--  public static final String TYPE_SX_TRPJ="3020";//他人评价思想道德
			   public static final String TYPE_HZ_TRPJ="4020";//他人评价
			   public static final String TYPE_YDJK_TRPJ="5020";//他人评价运动与健康
			   public static final String TYPE_SMYBX_TRPJ="6020";//他人评价审美与表现
			   public static final String TYPE_GXFZ_TRPJ="7030";//他人评价个性发展
			   public static final String TYPE_XY="8040";//学业
			   Constant.TYPE_SX_ZWPJ; -->
<%-- <un:bind var="TYPE_SX_TRPJ" type="com.flyrish.hades.common.Constant" field="TYPE_SX_TRPJ"/> --%>




<title></title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />

<link href="${ctx}/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/iframe.css" rel="stylesheet" type="text/css" /> 
 <link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css"/>
 <link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" /> 
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/func.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>


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
 $(window).load(function() {
	 var thisMession=document.getElementById("mession");
	
	 thisMession.innerHTML="";
	 thisMession.innerHTML=mess;  
	  });
	 
 var  addmove="notmove";
 
//文字限制提示
	$(document).ready(function(){
	      
		var textareas=$("body").find("textarea");	
		var conut=0;
		 for(var i=0;i<textareas.size();++i)
			{   
			    
				var id_content=$(textareas[i]).attr("id");
				 if(id_content.indexOf("_tishixie")>=0) 
				 {    ++conut;
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
				 if(id_content.indexOf("_tishixie")>=0) 
				 {   
					 $("#"+id_content).css("color","#ACACAC"); 
					 $("#"+id_content).val("最多输入600字");
				 } 
			}
	}

	
	 function romvezi(type){//new_${penseeList[0].two_part_id}_content_tishixie
		$("#new_"+type+"_content_tishixie").css("color","");
		$("#new_"+type+"_content_tishixie").val("");
		$("#new_"+type+"_content_tishixie").removeAttr("onclick");
		$("#new_"+type+"_content_tishixie").unbind('click');
		text_style1("new_"+type+"_content_tishixie");
		
		
	 } 
 
 var flag_full = false;
	//nn类型
	 function addpart_info(nn){ 
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
	//使得内容变为可写studid
 var content_div_full="";
	function change_content_is_available(studid,apprid,applty,id,flag){
		if(id==null||id==""){
		
		}else{
			if(flag==true){ 
				var this_div = document.getElementById(applty+"_"+id);
				this_div.focus();
				this_div.parentNode.onclick=null; 
				this_div.onclick=null;
				this_div.setAttribute("style","color:#FFFFFF;height:130px;");
				this_div.innerHTML='<textarea   id="'+applty+'_apprasial_'+id+'"  style="height:130px; width:100%; border:solid 1px;"  onblur="return change_content_is_available('+studid+','+apprid+','+applty+','+id+',false)"></textarea>';
				document.getElementById(applty+"_apprasial_"+id).value=this_div.attributes["idvalue"].nodeValue;
				document.getElementById(applty+"_apprasial_"+id).focus();
				$("#"+applty+"_apprasial_"+id).focus();
				content_div_full=document.getElementById(applty+"_apprasial_"+id).value;
			}else{ 
				var this_div = document.getElementById(applty+"_"+id);
				var app=$("#"+applty+"_apprasial_"+id).val().trim().length;
				var leng=$("#"+applty+"_apprasial_"+id).val().length;
			   var ssper=$("#"+applty+"_apprasial_"+id).val();
				if(app>0){
			   if(leng<=600){
				if(ssper!=content_div_full){
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/CzZtreeActio.a?doUpdataOtherProcess',
						method:'POST',
						success:function(response,options){
						
							var len=Ext.util.JSON.decode(response.responseText);
						    var success=len.success.replace( /^\s+|\s+$/g,"");
						    var info=len.info;
						    if(success="true")
						    {
						    	var ppar=$("#"+applty+"_apprasial_"+id).val();
								this_div.setAttribute("style","height:130px;background:#eee;");
								this_div.setAttribute("idvalue",ppar);
								this_div.innerHTML="";
								this_div.innerHTML="<textarea id='save_"+applty+"_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_available("+studid+","+apprid+","+applty+","+id+",true);'>"+ppar+"</textarea>"; 
								
								//this_div.innerText=ppar;
								this_div.parentNode.onclick=function(){change_content_is_available(studid,apprid,applty,id,true);}
								
						    
								 if(applty==32){
										alert_update_success("思想道德",id);
									};if(applty==45){
										alert_update_success("学业成绩",id);
									}; if(applty==52){
										alert_update_success("合作与交流",id);
									}; if(applty==62){
										alert_update_success("运动与健康",id);
									};if(applty==72){
										alert_update_success("审美与表现",id);
									}; if(applty==92){
										alert_update_success("个性发展",id);
									};
						    
						    
						    
						    
						    }else if(success=="false")
						    {
						    	alert_g(info);
						    	this_div.focus();
						    	return;
						    }
						   
						
						},
						params : {
							evaluatedPersonID:studid,
							part_id:apprid,
							part_info : $("#"+applty+"_apprasial_"+id).val(),
							two_part_id : applty, 
							createDate : document.getElementById(apprid+"_"+id+"_Date").value  
						}
					});
					}else{
						this_div.setAttribute("style","height:130px;background:#eee;");
						    var apper= $("#"+applty+"_apprasial_"+id).val(); 
							this_div.setAttribute("idvalue",apper);				
							this_div.innerHTML="";
							this_div.innerHTML="<textarea id='save_"+applty+"_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_available("+studid+","+apprid+","+applty+","+id+",true);'>"+apper+"</textarea>"; 
					}
			}else{
				  alert("评价内容已超过600");
			  }
			}
			}
		}
	}	  

	//初始为空时的保存
	 var content_div_full="";
	function change_content_is_availableNull(studid,apprid,applty,id,flag){
		if(id==null||id==""){
		}else{
			var this_div = document.getElementById(applty+"_"+id);
			if(flag==true){  
				this_div.focus();
				this_div.parentNode.onclick=null;                                              
				this_div.innerHTML='<textarea name="idcontent"  style="height:130px; width:100%;  border:solid 1px;" id="idcontent" onblur="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',false)"></textarea>';
				document.getElementById("idcontent").focus();
				$("#idcontent").focus();
				
				//content_div_full=document.getElementById("idcontent").innerText;
			}else{
				var app=$("#idcontent").val().trim();
				if(app=="最多输入600字"){
					var tishi="最多输入600字";
					this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',true)">'+tishi+'</textarea>';
		         	}
				var app=$("#idcontent").val().trim().length;
				var leng=$("#idcontent").val().length;
				if(app>0){
				if(leng<=600){
					
					Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
					Ext.Ajax.request({
						url:'${ctx}/student/CzZtreeActio.a?doUpdataOtherProcessNull',
						method:'POST',
						success:function(response,options){
							var len=Ext.util.JSON.decode(response.responseText);
						    var success=len.success.replace( /^\s+|\s+$/g,"");
						    var info=len.info;
						     var pid=len.pid;
						    if(success="true")
						    {   
						    	 
						           
						    	if(addmove=="move"){
						    		
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
											    {   addmove="notmove";
											       this_div.setAttribute("idvalue","");				
												   //this_div.innerText="最多输入600字";
												   //this_div.parentNode.onclick=function(){change_content_is_availableNull(studid,apprid,applty,id,true);}
											       var tishi="最多输入600字";
													this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',true)">'+tishi+'</textarea>';
											    
											    
													 if(applty==32){
															alert_delete_success("思想道德");
														};if(applty==45){
															alert_delete_success("学业成绩");
														}; if(applty==52){
															alert_delete_success("合作与交流");
														}; if(applty==62){
															alert_delete_success("运动与健康");
														};if(applty==72){
															alert_delete_success("审美与表现");
														}; if(applty==92){
															alert_delete_success("个性发展");
														}; 
											    
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
							    	$("#"+applty+"_add").css("display","block");
							    	 var ppar=$("#idcontent").val();
									this_div.setAttribute("style","height:130px;background:#eee;");
									this_div.setAttribute("idvalue",ppar);
									this_div.innerHTML="";
									this_div.innerHTML="<textarea id='save_"+applty+"_"+id+"' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%;' onclick='return change_content_is_available("+studid+","+pid+","+applty+","+id+",true);'>"+ppar+"</textarea>"; 
									//this_div.innerText=ppar;
									//this_div.parentNode.onclick=function(){change_content_is_available(studid,pid,applty,id,true);}
									var newtime=document.getElementById(apprid+"_"+id+"_Date");
									newtime.onblur=function(){update_time(pid,id,applty,'1')};
									var addnewtimeid=pid+"_"+id+"_Date";
									newtime.setAttribute("id",addnewtimeid);
									var newdelect=document.getElementById("delect_"+applty+"_"+id);
									newdelect.onclick=function(){removeAppraise(pid,applty,studid,1)}; 
						    	
									if(addmove!="move")
						    		  { if(applty==32){
											alert_update_success("思想道德",id);
										};if(applty==45){
											alert_update_success("学业成绩",id);
										}; if(applty==52){
											alert_update_success("合作与交流",id);
										}; if(applty==62){
											alert_update_success("运动与健康",id);
										};if(applty==72){
											alert_update_success("审美与表现",id);
										}; if(applty==92){
											alert_update_success("个性发展",id);
										};
							    
						    		  }
						    	}
						    }else if(success=="false")
						    {
						    	if(applty==32){
									alert_delete_failure("思想道德");
								};if(applty==45){
									alert_delete_failure("学业成绩");
								}; if(applty==52){
									alert_delete_failure("合作与交流");
								}; if(applty==62){
									alert_delete_failure("运动与健康");
								};if(applty==72){
									alert_delete_failure("审美与表现");
								}; if(applty==92){
									alert_delete_failure("个性发展");
								};                      
					    	//var ss=document.getElementById("zi_"+two_part+"_"+unmji);
					    	this_div.style.border ="2px solid red";
					    	
					    	ss.focus();
					    	return;
						    }
						},
						params : {
							evaluatedPersonID:studid,
							part_id:apprid,
							part_info : $("#idcontent").val(),
							two_part_id : applty, 
							createDate : document.getElementById(apprid+"_"+id+"_Date").value  
					}
					});
					
			}else{
				
				  alert("评价内容已超过600");
			  }
			}else{
				 var tishi="最多输入600字";
				this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',true)">'+tishi+'</textarea>';
				//<textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border-style: none; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
			}
			}
		}
	}	 
	
	//新加的保存
 	 function saveApprasial(kk,studentid){
		var width = $("#bg_eee_1_1").attr("width");
 	  setTimeout(function () { 
 		 $("#button_"+kk).removeAttr("onclick"); 
		 $("#button_"+kk).unbind('click');
 		document.getElementById("new_"+kk+"_content_tishixie").style.border ="0px solid";
		var leng=$('#new_'+kk+'_content_tishixie').val().trim();
		if(leng.length==0){
			  $("#new_"+kk+"_content_tishixie").bind('click',function(){
       			romvezi(kk);
       		}); 
			 $("#new_"+kk+"_content_tishixie").css("color","#ACACAC"); 
			$("#new_"+kk+"_content_tishixie").val("最多输入600字");
			$("#button_"+kk).bind('click',function(){
				addpart_info(kk);
     		});
		}else{
			var trimApprasial=$('#new_'+kk+'_content_tishixie').val();
			
         	var ss=trimApprasial.length;
         	
         	if(trimApprasial=="最多输入600字"){
         		$("#button_"+kk).bind('click',function(){
         			addpart_info(kk);
         		});
         		return;
         	}
         	if(ss<=600){
         		//回复点击事件
         		 $("#new_"+kk+"_content_tishixie").bind('click',function(){
          			romvezi(kk);
          		}); 
				var newDiv = Ext.get(kk+"_coent");
				Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		    	Ext.Ajax.request({
					url:'${ctx}/student/CzZtreeActio.a?doUpdataOtherProcessNull',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					    var info=len.info;
					    part_id=len.pid;
					    if(success=="true")
					    {
					    	 var num4=$('#'+kk+'_addlength').html();//显示的数
							 var part_info =$('#new_'+kk+'_content_tishixie').val();
							 var createDate =document.getElementById(kk+"_NewDate").value;  
							 if(addmove=="move"){
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
										    {  
										    	   if(kk==32){
														alert_delete_success("思想道德");
													};if(kk==45){
														alert_delete_success("学业成绩");
													}; if(kk==52){
														alert_delete_success("合作与交流");
													}; if(kk==62){
														alert_delete_success("运动与健康");
													};if(kk==72){
														alert_delete_success("审美与表现");
													}; if(kk==92){
														alert_delete_success("个性发展");
													};
										    	addmove="notmove";
										    	$('#'+kk+'_coent').css("display","none");
										    	/* alert_update_success("思想道德","1"); */
										    	$("#button_"+kk).bind('click',function(){
										    		addpart_info(kk);
									     		});
										    }else if(success=="false")
										    {
										    	
										    	return;
										    }
									},
									params : {
												part_id:part_id
									}
								}); 
							 }else{
							 
									 var pNum=0;
									var divs=$("#"+kk+"_table_zong").find("table");
							    	 	for(var i=0;i<divs.size();++i)
										{ 
											var id_content=$(divs[i]).attr("id");
												 ++pNum;
												 var arr = new Array();
												 arr[pNum]=id_content;
										} 
										   
							    		    var lengthDiv=arr[pNum];//最后一个
											var  lengthpid=lengthDiv.split("_"); //字符分割 
											var  num9=lengthpid[2];
											var num=Number(num9)+Number(1); 
							 var ssdiv="<table width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge' id='zi_"+kk+"_"+num+"'>"
								+"<tr class='"+part_id+"_dele'>"
						      +"<td id='"+kk+"_jishu_"+num+"' width='10%' height='40' class='bg_eee'>"+num4+"</td>"
						      +"<td colspan='2' class='youjuzhongs pr20 bg_eee'  id='${list.two_part_id}_${status.index+1}_time'>评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+"日期： <input id='"+part_id+"_"+num+"_Date' type='text' size='10' value="+createDate+" idvalue="+createDate+" onblur='update_time("+part_id+","+num+","+kk+","+num4+")' onClick='WdatePicker()'/>"
							  +"</td>"
							   +"</tr>"  
							    +"<tr class='"+part_id+"_dele' id='"+part_id+"_wuyong2'>"  
						       	 +"<td colspan='2' class='pl20 pr20 pb20 pt10 zuojuzhong'  style='height:130px;background:#eee;' id='"+kk+"_"+num+"' idvalue='"+part_info+"' >"
									 	+"<textarea id='save_"+kk+"_"+num+"' style='background: transparent; border: 1px solid transparent; height: 130px; width:100%;' onclick='return change_content_is_available("+studentid+","+part_id+","+kk+","+num+",true);'>"+part_info+"</textarea>" 
					               +"</td>" 
							      +"<td width='"+width+"' class='bg_eee' id='bg_eee_1_1'>"
							      +"<span class='btn'>" 
								       		+"<input type='button' class='fr shanchu1' onclick='removeAppraise("+part_id+","+kk+","+studentid+","+num+");'/>"
							      +"</span>"
							      +"</td>"
					        +"</tr>"
					        +"<table>";
					        $('#new_'+kk+'_content_tishixie').val("");
					         $("#"+kk+"_coent").css("display","none");
					        
					        var cundiv=document.getElementById("cun");
							cundiv.innerHTML=ssdiv;
							var newAdddiv=$("#zi_"+kk+"_"+num);
							var addwei=$("#"+lengthDiv);
							newAdddiv.insertAfter(addwei);
					    
							if(addmove!="move"){
							 if(kk==32){
									alert_update_success("思想道德",num4);
								};if(kk==45){
									alert_update_success("学业成绩",num4);
								}; if(kk==52){
									alert_update_success("合作与交流",num4);
								}; if(kk==62){
									alert_update_success("运动与健康",num4);
								};if(kk==72){
									alert_update_success("审美与表现",num4);
								}; if(kk==92){
									alert_update_success("个性发展",num4);
								};
							};
					  
							$("#button_"+kk).bind('click',function(){
								addpart_info(kk);
				     		});
							 
							 }
					    }else if(success=="false")
					    {    
					    	
					    	$("#button_"+kk).bind('click',function(){
					    		addpart_info(kk);
				     		});
					    	 if(kk==32){
									alert_update_failure("思想道德");
								};if(kk==45){
									alert_update_failure("学业成绩");
								}; if(kk==52){
									alert_update_failure("合作与交流");
								}; if(kk==62){
									alert_update_failure("运动与健康");
								};if(kk==72){
									alert_update_failure("审美与表现");
								}; if(kk==92){
									alert_update_failure("个性发展");
								};
					    	var ss=document.getElementById('new_'+kk+'_content_tishixie');
					    	ss.style.border ="2px solid red";
					    	
					    	ss.focus();
					    	return;
					    
					    }
					   
						readying();
					},
					params : {
						evaluatedPersonID:studentid,
						part_info :$('#new_'+kk+'_content_tishixie').val(),
						two_part_id :kk,   
						createDate : document.getElementById(kk+"_NewDate").value  
					}
				});
		 }else{ 
			alert("评价内容已超过600字");
		 }
		
		}
 	 }, 50);
	
	}	  

	//有数据是的删除
 function removeAppraise(part,two_part,studentid,unmji){
	
		var width = $("#bg_eee_1_1").attr("width");
		if(confirm( "确定要删除?" )) {  
			   Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	    		Ext.Ajax.request({
				url:'${ctx}/student/CzZtreeActio.a?deleApprasial',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
						var len=Ext.util.JSON.decode(response.responseText);
					    var success=len.success.replace( /^\s+|\s+$/g,"");
					   
					    if(success="true") 
					    {    var unmcont=0;
					    	var textareas=$("#"+two_part+"_table_zong").find("table");
					    	var trsize=textareas.size();
				    		
					    	if(trsize>1){
					    		
				    			$("#zi_"+two_part+"_"+unmji).remove();
				    			var textareas1=$("#"+two_part+"_table_zong").find("table");
				    			 var trsize1=textareas1.size();
				    			 
				    			 for(var i=0;i<trsize1;i++){
				    				   ++unmcont;
				    				  var id_content=$(textareas1[i]).attr("id");
				    				  var  lengthpid=id_content.split("_"); //字符分割 
								      var  numty=lengthpid[2];
								      $("#"+two_part+"_jishu_"+numty).html(""); 
								      $("#"+two_part+"_jishu_"+numty).html(unmcont); 
				    			   }  
				    			}else{
				    				//${cooperateList[0].two_part_id}_coent
				    				var temp1=$("#"+two_part+"_coent").is(":visible");
				    				if(temp1){
				    					$("#"+two_part+"_coent").css("display","none");	
				    				}
				    				$("#zi_"+two_part+"_"+unmji).remove();
				    				var kong="<table width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge' id='zi_"+two_part+"_1'>" 
				   			     +"<tr>"
				   			      +"<td id='"+two_part+"_jishu_1' width='10%' height='40' class='bg_eee'>1</td>"
				   			     +" <td colspan='2' class='youjuzhongs pr20 bg_eee'  >评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				   						+"日期： <input id='0_1_Date' type='text' size='10' value='${time}' idvalue='${time}' onblur='update_time(0,1,"+two_part+",'1')' onClick='WdatePicker()'/>"
				   				+"</td>"
				   		        +"</tr>"       
				   			    +"<tr>"                                                                                           
				   			       +"<td colspan='2' class='pl20 pr20 pb20 pt10 zuojuzhong'  style='height:130px;' id='"+two_part+"_1' idvalue='' >"
				   						 +"<textarea  id='save_"+two_part+"_1_tishixie' style='background: transparent; border: 1px solid transparent; height: 130px; width: 100%; color:#ACACAC;' onclick='return change_content_is_availableNull("+studentid+",0,"+two_part+",1,true);'>最多输入600字</textarea>" 
				   		               +"</td>" 
				   				   +"<td width='"+width+"' class='bg_eee' id='bg_eee_1_1'>"
				   				    +"<span class='btn'>" 
				   					       +"<input id='delect_"+two_part+"_1' type='button' class='fr shanchu1' onclick='removeAppraiseChu(0,"+two_part+","+studentid+",1);'/>"
				   				      +"</span>"
				   				      +"</td>"
				   		        +"</tr>"
				   		    +"</table>";
					   		var dddiv=document.getElementById(two_part+"_table_zong");
					   		dddiv.innerHTML="";
					   		dddiv.innerHTML=kong;
					   		id="${penseeList[0].two_part_id}_add"
					   		$("#"+two_part+"_add").css("display","none");
				    	} 
					    	 if(two_part==32){
									alert_delete_success("思想道德");
								};if(two_part==45){
									alert_delete_success("学业成绩");
								}; if(two_part==52){
									alert_delete_success("合作与交流");
								}; if(two_part==62){
									alert_delete_success("运动与健康");
								};if(two_part==72){
									alert_delete_success("审美与表现");
								}; if(two_part==92){
									alert_delete_success("个性发展");
								};
					  }else if(success=="false")
					    {
						  if(two_part==32){
								alert_delete_failure("思想道德");
							};if(two_part==45){
								alert_delete_failure("学业成绩");
							}; if(two_part==52){
								alert_delete_failure("合作与交流");
							}; if(two_part==62){
								alert_delete_failure("运动与健康");
							};if(two_part==72){
								alert_delete_failure("审美与表现");
							}; if(two_part==92){
								alert_delete_failure("个性发展");
							};                      
				    	var ss=document.getElementById("zi_"+two_part+"_"+unmji);
				    	ss.style.border ="2px solid red";
				    	
				    	ss.focus();
				    	return;
					    }
				},
				params : {
							evaluatedPersonID:studentid,
							part_id:part,
							two_part_id:two_part
				}
			}); 
		 }  
		
	
	   }  
	   //空删除(${list.studentid},${list.two_part_id},${status.index+1}) 0,"+two_part+","+studentid+",1
	  function removeAppraiseChu(apprid,applty,studid,id) {
		 if(confirm( "确定要删除?" )) { 
			 
			 
			 if($('#idcontent').length>0)  {
			 var leng=document.getElementById('idcontent').value;
			  var lengtrim=leng.trim();
			  if(lengtrim.length==0){
				  apprasial_del_Finish();
				}else{
					var newapper=document.getElementById('idcontent').value;
					var longth=newapper.length;
					if(longth>600){
						 if(applty==32){
								alert_delete_success("思想道德");
							};if(applty==45){
								alert_delete_success("学业成绩");
							}; if(applty==52){
								alert_delete_success("合作与交流");
							}; if(applty==62){
								alert_delete_success("运动与健康");
							};if(applty==72){
								alert_delete_success("审美与表现");
							}; if(applty==92){
								alert_delete_success("个性发展");
							};
						var this_div = document.getElementById(applty+"_"+id);
						  this_div.setAttribute("idvalue","");				
					       var tishi="最多输入600字";
							this_div.innerHTML='<textarea name="idcontent"  style="background: transparent;color:#ACACAC; height:130px; width:100%;   id="idcontent" onclick="return change_content_is_availableNull('+studid+','+apprid+','+applty+','+id+',true)">'+tishi+'</textarea>';
					}else{
						
						addmove="move";
		      }
		  }  
			 }else{
				 apprasial_del_Finish();
			 }
		 
		 
		 
		 } 
	   }
	  //增加删除
  function delect(typeid,stuid) {//id="new_${penseeList[0].two_part_id}_content_tishixie"
         var apappraisal="new_"+typeid+"_content_tishixie";
		 var apprasialDelect=$('#'+apappraisal).val();
		 var length=apprasialDelect.length;
		 if(confirm( "确定要删除?" )) {
			 if(length>0){
				if(length>600){
							 //回复点击事件
				         		 $("#new_"+typeid+"_content_tishixie").bind('click',function(){
				          			romvezi(typeid);
				          		});
				         		$("#button_"+typeid).bind('click',function(){
				         			addpart_info(typeid);
				         		});
						   addmove="notmove";
						   $('#'+typeid+'_coent').css("display","none");
						   if(typeid==32){
								alert_delete_success("思想道德");
							};if(typeid==45){
								alert_delete_success("学业成绩");
							}; if(typeid==52){
								alert_delete_success("合作与交流");
							}; if(typeid==62){
								alert_delete_success("运动与健康");
							};if(typeid==72){
								alert_delete_success("审美与表现");
							}; if(typeid==92){
								alert_delete_success("个性发展");
							};		    
				 } 
				 else{var apperTrim=apprasialDelect.trim();
			  	if(apperTrim==null||apperTrim==""||apperTrim==undefined||apperTrim=="最多输入600字"){
			  		$('#'+typeid+'_coent').css("display","none");
			  		 if(typeid==32){
							alert_delete_success("思想道德");
						};if(typeid==45){
							alert_delete_success("学业成绩");
						}; if(typeid==52){
							alert_delete_success("合作与交流");
						}; if(typeid==62){
							alert_delete_success("运动与健康");
						};if(typeid==72){
							alert_delete_success("审美与表现");
						}; if(typeid==92){
							alert_delete_success("个性发展");
						};
			  	}else{
			  		    addmove="move"; 
					}
			 }
			 }
			 else{  
				  $('#'+typeid+'_coent').css("display","none");
					 if(typeid==32){
							alert_delete_success("思想道德");
						};if(typeid==45){
							alert_delete_success("学业成绩");
						}; if(typeid==52){
							alert_delete_success("合作与交流");
						}; if(typeid==62){
							alert_delete_success("运动与健康");
						};if(typeid==72){
							alert_delete_success("审美与表现");
						}; if(typeid==92){
							alert_delete_success("个性发展");
						};
			 } 
		 } 
	 }  
  
				
				
				function changeterm(){
					ShowDiv();
					var term = document.getElementById("termId").value;
					var appraiserrid= document.getElementById("appraiserrid_hiden").value;
					url="${ctx}/student/CzZtreeActio.a?chaneTermId&evaluatedPersonID="+appraiserrid+"&termId1="+term;
					document.location.replace(url);
					
				}
				 function update_time(id,num,two_part,num4)
					{

					if(id==null||id==""||id==0)
						{
						     
						}else{
							var time1=document.getElementById(id+"_"+num+"_Date").value;
							var time2=$("#"+id+"_"+num+"_Date").attr("idvalue");
							if(time1!=time2)
							{   
								var shu=$("#"+two_part+"_jishu_"+num).html();
								 if(two_part==32){
										alert_update_success("思想道德",shu);
									};if(two_part==45){
										alert_update_success("学业成绩",shu);
									}; if(two_part==52){
										alert_update_success("合作与交流",shu);
									}; if(two_part==62){
										alert_update_success("运动与健康",shu);
									};if(two_part==72){
										alert_update_success("审美与表现",shu);
									}; if(two_part==92){
										alert_update_success("个性发展",shu);
									};
									$("#"+id+"_"+num+"_Date").attr("idvalue",time1);
								Ext.Ajax.request({
									url:'${ctx}/student/CzZtreeActio.a?doUpadateTime',
									method:'POST',
									defaults:{autoScroll: true},
									success:function(response,options){
									},
									params : {
										part_id:id,
										createDate:time1,
										two_part_id:two_part
									   
									
									}
								});
								
							}	
						}
						
					}

				
	</script>  
 </head>
<body>
<%@ include file="/common/div.jsp"%> 
<div class="dangqianwz">
 	<span class="fl"> 评价同学：${name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <span>学期：
    	 <select  style="border:1px #666 solid;" name="termId" id="termId" onchange="changeterm()">
            <app:highSchoolTermTag  levelCode="${levelCode}" selectNum="${termidInteger}" selectClassid="${classid}"/>
        </select> 
      </span> 
 </div>
<div id="pj_ziwo_main">                                             
<input id="appraiserrid_hiden"  type="hidden" name="appraiserrid_hiden" value="${evaluatedPersonID}"/>
          <div class="top"  >
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" idvalue="思想道德">
			    <tr >  
			      <td class="title_bg" colspan="3">思想道德</td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="32_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		       <c:forEach items="${penseeList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				      </span>
				      </td>
		        </tr>
		         </table>
			  </c:forEach>
              </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
			   		<table id="${penseeList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${penseeList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${penseeList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${penseeList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"> <!-- 要改的地方 -->
				                <textarea   id="new_${penseeList[0].two_part_id}_content_tishixie"  style="height:130px; width:100%; overflow:auto; border: 1px solid transparent;" onclick='romvezi(${penseeList[0].two_part_id})' onblur="return saveApprasial(${penseeList[0].two_part_id},${penseeList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1" style="height:130px;">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${penseeList[0].two_part_id},${penseeList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
			    	 <input id="${penseeList[0].two_part_id}_length"  type="hidden"  value="${fn:length(penseeList)}"/>
			     <div id="cun"></div>
			   <input id="${penseeList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${penseeList[0].part_info!=' '}">
			     <div  class="biaoge" id="${penseeList[0].two_part_id}_add" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${penseeList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${penseeList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			   </c:if>
			 <c:if test="${penseeList[0].part_info==' '}"> 
			     <div  class="biaoge" id="${penseeList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${penseeList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${penseeList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			</c:if>
			 </div>  
 
          
		     <!--  ------------------------ 学业成就-------------------------- -->
		  <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" idvalue="思想道德">
			    <tr >  
			      <td class="title_bg" colspan="3">学业成就</td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="45_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		        
		       <c:forEach items="${successList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border-style: none; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				           
				      </span>
				      </td>
		           
		        </tr>
		         </table>
			  </c:forEach>
              </div>  
			      <!--  ------------------------隐藏的页面------------------------------- -->
			   		<table id="${successList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${successList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${successList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${successList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${successList[0].two_part_id}_content_tishixie"  style="height:130px; width:100%; border: 1px solid transparent; overflow:auto;" onclick='romvezi(${successList[0].two_part_id})' onblur="return saveApprasial(${successList[0].two_part_id},${successList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${successList[0].two_part_id},${successList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
			    	 <input id="${successList[0].two_part_id}_length"  type="hidden"  value="${fn:length(successList)}"/>
			     <div id="cun"></div>
			   <input id="${successList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${successList[0].part_info!=' '}">
			    <div  class="biaoge" id="${successList[0].two_part_id}_add" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${successList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${successList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			   </c:if>
			 <c:if test="${successList[0].part_info==' '}">
			     <div  class="biaoge" id="${successList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${successList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${successList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			</c:if>
			 </div>  


                 <!-- -----------------合作与交流 ----------------------->
               
		  <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" >
			    <tr >  
			      <td class="title_bg" colspan="3">合作与交流&nbsp;<span class="red">*</span></td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="52_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		        
		       <c:forEach items="${cooperateList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id}),${status.index+1}" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				           
				      </span>
				      </td>
		           
		        </tr>
		         </table>
			  </c:forEach>
              </div>  
			      <!--  ------------------------隐藏的页面------------------------------- -->
			   		<table id="${cooperateList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${cooperateList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${cooperateList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${cooperateList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_52_content_tishixie"  style="height:130px; width:100%;border: 1px solid transparent; overflow:auto;" onclick='romvezi(${cooperateList[0].two_part_id})' onblur="return saveApprasial(52,${cooperateList[0].studentid})"></textarea>
				                 <%-- <textarea   id="new_${playList[0].two_part_id}_content_tishixie"       style="height:110px; width:100%; overflow:auto;border:solid 1px;" onclick='romvezi(${playList[0].two_part_id})' onblur="return saveApprasial(${playList[0].two_part_id},${playList[0].studentid})"></textarea> --%>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${cooperateList[0].two_part_id},${cooperateList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
		            
		            
			    	 <input id="${cooperateList[0].two_part_id}_length"  type="hidden"  value="${fn:length(cooperateList)}"/>
			     <div id="cun"></div>
			   <input id="${cooperateList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${cooperateList[0].part_info!=' '}">
			     <div  class="biaoge" id="${cooperateList[0].two_part_id}_add"> 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${cooperateList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${cooperateList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div> 
			   </c:if>
			 <c:if test="${cooperateList[0].part_info==' '}">
			     <div  class="biaoge" id="${cooperateList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${cooperateList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${cooperateList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div> 
			</c:if>
			 </div>  
		
		  

<!-- -----------------------------------运动与健康 --------------------------->

	   
	      <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" idvalue="思想道德">
			    <tr >  
			      <td  class="title_bg" colspan="3">运动与健康</td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="62_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		        
		       <c:forEach items="${playList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent;  border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				           
				      </span>
				      </td>
		           
		        </tr>
		         </table>
			  </c:forEach>
              </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
			   		<table id="${playList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${playList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${playList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${playList[0].two_part_id}.'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${playList[0].two_part_id}_content_tishixie"  style="height:130px; width:100%; border: 1px solid transparent; overflow:auto;" onclick='romvezi(${playList[0].two_part_id})' onblur="return saveApprasial(${playList[0].two_part_id},${playList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${playList[0].two_part_id},${playList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
		            
		            
			    	 <input id="${playList[0].two_part_id}_length"  type="hidden"  value="${fn:length(playList)}"/>
			     <div id="cun"></div>
			   <input id="${playList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${playList[0].part_info!=' '}">
			      <div  class="biaoge" id="${playList[0].two_part_id}_add" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${playList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${playList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			   </c:if>
			 <c:if test="${playList[0].part_info==' '}">
			      <div  class="biaoge" id="${playList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${playList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${playList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div> 
			</c:if>
			 </div> 
	     
	     


      <!-- ----------------- 审美与表现-------------------------- -->
      
      
      
   
      <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" idvalue="思想道德">
			    <tr >  
			      <td class="title_bg" colspan="3">审美与表现</td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="72_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		        
		       <c:forEach items="${tastelList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				           
				      </span>
				      </td>
		           
		        </tr>
		         </table>
			  </c:forEach>
              </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
			    <%--   <table id="${tastelList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${tastelList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${tastelList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${tastelList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${tastelList[0].two_part_id}_content_tishixie"  style="height:130px; width:100%;border: 1px solid transparent; overflow:auto;" onclick='romvezi(${tastelList[0].two_part_id})' onblur="return saveApprasial(${tastelList[0].two_part_id},${tastelList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee">
					      <span class="fr btn">
				                <input id="button_${tastelList[0].two_part_id}" type="button" class="fr shanchu1" onclick="delect(${tastelList[0].two_part_id},${tastelList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table>   --%>
		            
		            
		            
		            
		         	<table id="${tastelList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${tastelList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${tastelList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${tastelList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${tastelList[0].two_part_id}_content_tishixie"  style="height:130px; border: 1px solid transparent; width:100%; overflow:auto;" onclick='romvezi(${tastelList[0].two_part_id})' onblur="return saveApprasial(${tastelList[0].two_part_id},${tastelList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${tastelList[0].two_part_id},${tastelList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
		          
		            
			    	 <input id="${tastelList[0].two_part_id}_length"  type="hidden"  value="${fn:length(tastelList)}"/>
			     <div id="cun"></div>
			   <input id="${tastelList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${tastelList[0].part_info!=' '}">
			    <div  class="biaoge" id="${tastelList[0].two_part_id}_add" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${tastelList[0].two_part_id}"  type="button" class="fr shanchu"   onclick="addpart_info(${tastelList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			   </c:if>
			 <c:if test="${tastelList[0].part_info==' '}">
			       <div  class="biaoge" id="${tastelList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input type="button" class="fr shanchu"   onclick="addpart_info(${tastelList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			</c:if>
			 </div>   
 
      
      
      <div class="down mt18">
		    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="32_xie" idvalue="思想道德">
			    <tr >  
			      <td class="title_bg" colspan="3">个性发展</td>  <!-- 要改的地方 -->
		        </tr> 
		     </table> 
		      <div id="92_table_zong" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">  
		        
		       <c:forEach items="${selfhoodList}" var="list" varStatus="status" > <!-- 要改的地方 -->
			    <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="zi_${list.two_part_id}_${status.index+1}"> 
			     <tr class="${list.part_id}_dele">
			      <td id="${list.two_part_id}_jishu_${status.index+1}" width="10%" height="40" class="bg_eee">${status.index+1}</td>
			      <td colspan="2" class="youjuzhongs pr20 bg_eee"  id="${list.two_part_id}_${status.index+1}_time">评价人：同学 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${signer_name}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${list.part_info!=' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${list.createDate}" idvalue="${list.createDate}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
					<c:if test="${list.part_info==' '}"> 
						日期： <input id="${list.part_id}_${status.index+1}_Date" type="text" size="10" value="${time}" idvalue="${time}" onblur="update_time(${list.part_id},${status.index+1},${list.two_part_id},${status.index+1})" onClick="WdatePicker()"/>
					</c:if>
				</td>
		        </tr>
			    <tr class="${list.part_id}_dele">                                                                                            
			       
			       <c:if test="${list.part_info!=' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;background:#eee;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 		<textarea id="save_${list.two_part_id}_${status.index+1}" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_available(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
			       <c:if test="${list.part_info==' '}"> 
			       			<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong"  style="height:130px;" id="${list.two_part_id}_${status.index+1}" idvalue="${list.part_info}" >
						 	 <textarea  id="save_${list.two_part_id}_${status.index+1}_tishixie" style="background: transparent; border: 1px solid transparent; height: 130px; width: 100%;" onclick="return change_content_is_availableNull(${list.studentid},${list.part_id},${list.two_part_id},${status.index+1},true);">${list.part_info}</textarea> 
		                  </td> 
		            </c:if>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1">
				      <span class="btn"> 
				            <c:if test="${list.part_info!=' '}">                                            
					       		<input type="button" class="fr shanchu1" onclick="removeAppraise(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				            </c:if>
				            <c:if test="${list.part_info==' '}">                                            
					       		<input id="delect_${list.two_part_id}_${status.index+1}" type="button" class="fr shanchu1" onclick="removeAppraiseChu(${list.part_id},${list.two_part_id},${list.studentid},${status.index+1});"/>
				              <!--   <input type="button" class="fr tianjia" onclick=""/>  -->
				            </c:if>
				           
				      </span>
				      </td>
		           
		        </tr>
		         </table>
			  </c:forEach>
              </div>
			      <!--  ------------------------隐藏的页面------------------------------- -->
			   		<table id="${selfhoodList[0].two_part_id}_coent" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" style='display:none'>
					    <tr>   
					      <td width="10%" height="40px" class="bg_eee" id="${selfhoodList[0].two_part_id}_addlength"></td>
					      <td  colspan="2" class="youjuzhongs pr20 bg_eee">评价人：同学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： ${signer_name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input id="${selfhoodList[0].two_part_id}_NewDate" value="${time}" type="text" size="10"  onblur="update_time('0','0',${selfhoodList[0].two_part_id},'1')" onClick="WdatePicker()"/></td>
				        </tr>
					    <tr>
					      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong" style="height:130px;"> <!-- 要改的地方 -->
				                <textarea   id="new_${selfhoodList[0].two_part_id}_content_tishixie"  style="height:130px; border: 1px solid transparent; width:100%; overflow:auto;" onclick='romvezi(${selfhoodList[0].two_part_id})' onblur="return saveApprasial(${selfhoodList[0].two_part_id},${selfhoodList[0].studentid})"></textarea>
				          </td>
					      <td width="10%" class="bg_eee" id="bg_eee_1_1">
					      <span class="fr btn">
				                <input type="button" class="fr shanchu1" onclick="delect(${selfhoodList[0].two_part_id},${selfhoodList[0].studentid});"/>
					       </span> 
					      </td>
				        </tr>
		            </table> 
		            
		            
			    	 <input id="${selfhoodList[0].two_part_id}_length"  type="hidden"  value="${fn:length(selfhoodList)}"/>
			     <div id="cun"></div>
			   <input id="${selfhoodList[0].two_part_id}_part_id"  type="hidden"  value=""/>
			  <c:if test="${selfhoodList[0].part_info!=' '}">
			    <div  class="biaoge" id="${selfhoodList[0].two_part_id}_add" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input id="button_${selfhoodList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${selfhoodList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			   </c:if>
			 <c:if test="${selfhoodList[0].part_info==' '}">
			    <div  class="biaoge" id="${selfhoodList[0].two_part_id}_add" style="display: none;" > 
				      <div class="bg_eee h80" style="border:1px #999 solid; background:#eee;">
			              <div class="fr btn" style="margin-top:7px;">
			                  <input  id="button_${selfhoodList[0].two_part_id}" type="button" class="fr shanchu"   onclick="addpart_info(${selfhoodList[0].two_part_id});"/>
			              </div>
			          </div>
				  </div>
			</c:if>
			 </div>   
 </div> 
</body>
 </html>

