<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
	<%@ include file="/common/mass.jsp"%>
	<un:bind var="TYPE_YDJK_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_ZWPJ" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我评价</title>
</head>
<style>
#pj_ziwo_main{top:38px;padding:13px;margin-bottom:21px}
.file{ position:absolute; top:0; right:300px; /* filter:alpha(opacity:0);opacity: 0; */ height:27px; width:69px }
.fileadd{ position:absolute; top:0; right:253px; /*  filter:alpha(opacity:0);opacity: 0; */height:27px; width:69px }
.doupload{position:relative;left: 130px;}
.fileadd2{ position:absolute; top:0;left: 10px;  filter:alpha(opacity:0);opacity: 0; height:27px; width:69px }
</style>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script> 
<body>
<%@ include file="/common/div.jsp"%>
<input type="hidden" name="evaluateType1" id="evaluateType1" value="${TYPE_YDJK_ZWPJ}"> 
<input type="hidden" name="choicenum" id="choicenum" value="${choicenum}"> 
	<div class="dangqianwz">
	<span class="fl">当前位置：自我评价->运动与健康</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<span>学期：
    	 <select name="termId" id="termId" onchange="changeterm()" class="wenziliebiao100">
            <app:highSchoolTermTag selectClassid="${classid}" selectNum="${termId}" levelCode="${levelcode}"/>
        </select>
    </span>
 	</div>
		<div id="pj_ziwo_main">
		<div id="top" class="top">
		<%int n=1;%>
			<div id="new_top" <c:if test="${not empty appraisalList1}">style='display:none'</c:if>>
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table">
					<tr class="title_bg">
						<td colspan="3">自我评价&nbsp;<span class="red"></span></td>
					</tr>
					<tr id="new_tr">  
						<td width="100" height="40" id="new_num1" class="bg_eee">1</td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
						评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：<input id="new_startDate1" type="text" size="10" onClick="WdatePicker()" value="${nowDate}" onchange="checksave('new_startDate1','new_content1',this)"/>
						</td>
					</tr>
					<tr>
						<td id ="new_td" colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong">
							<textarea id="new_content1" style="background: transparent;  border: 1px solid transparent; overflow:auto; height: 130px; width: 100%;" onblur="checksave('new_startDate1','new_content1',this)" onfocus="changeback(this)"></textarea>
						</td>
						 <td width="100" class="bg_eee" id="bg_eee_2_1"><span class="fr btn" style="margin:0px auto;float:none">
	      				 	<input type="button" class="fr shanchu1" style="margin:0px auto;float:none" title="删除" id="new_delete" onclick="deleteselfapp(this)"/>
	      				</span></td>
					</tr>
					<tr id="new_addlocation" style="display:none">
					    <td colspan="3" class="bg_eee h80">
				            <div class="fr btn">
				               <input type="button" id ="add" style="margin-right :20px;" title="添加" class="fr shanchu" onclick="addnew('new_addlocation','new_top')"/>
				            </div>
				         </td>
					</tr>
				</table>
			</div>
			<div id="old_top" <c:if test="${empty appraisalList1}">style='display:none'</c:if>>
			<input type="hidden" name="newId" id="newId" value="${newId}"> 
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table1">
					<tr class="title_bg">
						<td colspan="3">自我评价&nbsp;<span class="red"></span></td>
					</tr>
					<c:forEach items="${appraisalList1}" var="list" varStatus="status">
					<tr id="${list.apprasialid}_tr">
					  	<td width="100" height="40" class="bg_eee" id="${list.apprasialid}_num"><%=n%></td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
						评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：<input id="${list.apprasialid}_startDate" type="text"  value="${list.signdate}" size="10" onchange="doupdate1('${list.apprasialid}')" onClick="WdatePicker()"/> 
						</td>
					</tr>
					<tr>
						<td colspan="2" id="${list.apprasialid}_td" class="pl20 pr20 pb20 pt10 zuojuzhong " style="background:#eee;">
				 			 <textarea class="content"
						                id="${list.apprasialid}_content"
						                style="background: transparent;  border: 1px solid transparent; overflow:auto; height: 130px; width: 100%; "
						                idvalue="${list.apprasial}"
						                onblur="doupdate('${list.apprasialid}')"
						                onfocus="changeback(this)">${list.apprasial}</textarea>
						</td>
						<td width="100" class="bg_eee" id="bg_eee_2_1">
							<span class="fr btn" style="margin:0px auto;float:none"> 
							<input type="button" style="margin:0px auto;float:none" class="fr shanchu1" id="${list.apprasialid}_delete" title="删除" onclick="deleteselfapp(this)"/>
	      					</span>
	     			 	</td>
					</tr>
					<%n++;%>
					</c:forEach>
					<tr id="old_addlocation">
					    <td colspan="3" class="bg_eee h80">
				            <div class="fr btn">
				               <input type="button"  style="margin-right :20px;" id ="add" title="添加" class="fr shanchu" onclick="addnew('old_addlocation','old_top')"/>
				            </div>
				         </td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//改成可写并更新数据
	var interdelete = 0;
	var isSave = 0;
	var isDelete = 0;
	var deletecid ="";
	
	function doupdate(id){
		var this_div = document.getElementById(id+"_content");
		var this_date = document.getElementById(id+"_startDate");
		if(this_div.value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
			if(this_div.value!=this_div.attributes["idvalue"].nodeValue){
				if(this_div.value.length<=600){
					if(this_div.value.replace(/(^\s*)|(\s*$)/g,'')!=""){
						Ext.Ajax.request({
							url:'${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								var temp=response.responseText;
								if(temp=="##"){
									alert_update_failure("自我评价");	
									text_style(id+"_content");
								}else{
									if(interdelete == 0){
										document.getElementById(id+"_content").style.border = "1px solid transparent";
										document.getElementById(id+"_td").style.backgroundColor ="#eee";
										this_div.setAttribute("idvalue",this_div.value);
										alert_update_success("自我评价",$("#"+id+"_num").html())
										isSave = 0;
									}
								}
							},
							params : {
								id : id,
								termId : document.getElementById("termId").value,
								apprasial : this_div.value,
								choicenum : document.getElementById("choicenum").value,
								updateType : document.getElementById("evaluateType1").value,
								signDate : document.getElementById(id+"_startDate").value
							}
						});
					}
				}else{
					isSave = 0;
					apprasial_alert(600);
				}
			}else{
				document.getElementById(id+"_content").style.border = "1px solid transparent";
				document.getElementById(id+"_td").style.backgroundColor ="#eee";
				isSave = 0;
			}
		}
	}
	
	function doupdate1(id){
	/* 	var idd = window.event.srcElement.getAttribute('id');
		var arr = new Array();
		arr = idd.split("_");
		id = arr[0]; */
		var this_div = document.getElementById(id+"_content");
		var this_date = document.getElementById(id+"_startDate");
		if(this_div.value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
			if(this_div.value.length<=600){
				if(this_div.value.replace(/(^\s*)|(\s*$)/g,'')!=""){
					Ext.Ajax.request({
						url:'${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							var temp=response.responseText;
							if(temp=="##"){
								alert_update_failure("自我评价");	
								text_style(id+"_content");
							}else{
								if(interdelete == 0){
									/* document.getElementById(id+"_content").style.border = "1px solid transparent";
									document.getElementById(id+"_td").style.backgroundColor ="#eee"; */
									this_div.setAttribute("idvalue",this_div.value);
									alert_update_success("自我评价",$("#"+id+"_num").html())
									isSave = 0;
								}
							}
						},
						params : {
							id : id,
							termId : document.getElementById("termId").value,
							apprasial : this_div.value,
							choicenum : document.getElementById("choicenum").value,
							updateType : document.getElementById("evaluateType1").value,
							signDate : document.getElementById(id+"_startDate").value
						}
					});
				}
			}else{
				isSave = 0;
				apprasial_alert(600);
			}
		}
	}
	
	function save(day,valuenum){
		var type1 = document.getElementById("evaluateType1").value;
		var type2 = "1";
		if(document.getElementById(valuenum).value=="最多输入600字"){
			document.getElementById(valuenum).value="";
		}
		if(document.getElementById(valuenum).value.replace(/(^\s*)|(\s*$)/g,'') != ""){
			document.getElementById(valuenum).disabled = true;//提交过程中设为不可编辑
			document.getElementById(day).disabled = true;//提交过程中设为不可编辑
			if(document.getElementById(valuenum).value.length<=600){
					Ext.Ajax.request({
						url:'${ctx}/selfappraise/SelfAppAction.a?doInsertSelfEvaluation',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							var temp=response.responseText;
							if(temp=="##"){
								document.getElementById(valuenum).disabled=false;
								document.getElementById(day).disabled=false;
								alert_save_failure("自我评价");
								text_style(valuenum);
							}else{
								document.getElementById(valuenum).disabled=false;
								document.getElementById(day).disabled=false;
								 var saveid = temp;
								if(valuenum=="new_content1"){
									$("#new_content1").removeAttr("onblur");
									$("#new_content1").unbind("blur");
									$("#new_content1").bind("blur",function(){
										checksave(saveid+"_startDate",saveid+"_content","#"+saveid+"_content");
									});
									$('#new_content1').attr("id",saveid+"_content");
									$('#new_startDate1').attr("id",saveid+"_startDate");
									$('#new_td').attr("id",saveid+"_td");
									$('#new_tr').attr("id",saveid+"_tr");
									$('#new_delete').attr("id",saveid+"_delete");
									$('#new_num1').attr("id",saveid+"_num");
									if(isDelete != 1){
										document.getElementById("new_addlocation").style.display= ""
									}
								}else if(valuenum=="new_content11"){
									$("#new_content11").removeAttr("onblur");
									$("#new_content11").unbind("blur");
									$("#new_content11").bind("blur",function(){
										checksave(saveid+"_startDate",saveid+"_content","#"+saveid+"_content");
									});
									$('#new_content11').attr("id",saveid+"_content");
									$('#new_startDate11').attr("id",saveid+"_startDate");
									$('#new_td1').attr("id",saveid+"_td");
									$('#new_tr1').attr("id",saveid+"_tr");
									$('#new_delete1').attr("id",saveid+"_delete");
									$('#new_num').attr("id",saveid+"_num");
									if(isDelete != 1){
										document.getElementById("old_addlocation").style.display= ""
									}
								}
								if(isDelete == 1){
									oldid=saveid;
									if(deletecid!="new"){
										alert_save_success("自我评价",$("#"+saveid+"_num").html());
										var oldid = deletecid;
									}
									deleteselfapp1(oldid);
									isDelete = 0;
									isSave = 0;
									document.getElementById(saveid+"_content").setAttribute("idvalue",document.getElementById(saveid+"_content").value);
									document.getElementById(saveid+"_content").style.border = "1px solid transparent"; 
									document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
								}else{
								/* 	if(interdelete == 0){ */
										alert_save_success("自我评价",$("#"+saveid+"_num").html());
										document.getElementById(saveid+"_content").setAttribute("idvalue",document.getElementById(saveid+"_content").value);
										isSave = 0;
										document.getElementById(saveid+"_content").style.border = "1px solid transparent"; 
										document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
									/* } */
								}
								isSave=0;
							}
						},
						params : {
							apprasial : document.getElementById(valuenum).value,
							evaluateType1 : type1,
							evaluateType2 : type2,
							choicenum : document.getElementById("choicenum").value,
							termId : document.getElementById("termId").value,
							signDate : document.getElementById(day).value
						}
					}); 
			}else{
				isSave = 0;
				document.getElementById(valuenum).disabled=false;
				document.getElementById(day).disabled=false;
				apprasial_alert(600);
			}
		}else{
			if($("#new_content1")!=null&&$("#new_content1").val()!=undefined){
				document.getElementById("new_content1").style.border = "1px solid transparent";
			}
			if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
				document.getElementById("new_content11").style.border = "1px solid transparent";
			}
			isSave = 0;
			document.getElementById(valuenum).disabled=false;
			document.getElementById(day).disabled=false;
			input_notice(valuenum,600);
		}
	}
		
		 function addnew(trid,div){
			 if(document.getElementById("new_tr1")==null){
				 var div = document.getElementById(div); 
					var tr = div.getElementsByTagName("tr").length;
					var tr_num=Math.floor(tr/2);
					var str="";
					str +="<tr id='new_tr1'><td width='100' height='40' id='new_num' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"日期：<input id='new_startDate11' type='text' size='10' onClick='WdatePicker()' value='${nowDate}' onchange='checksave(\""+"new_startDate11"+"\",\""+"new_content11"+"\",this)'/><input type='hidden' name='insertId' id='insertId' /> "
					+"</td></tr><tr><td colspan='2' id='new_td1' class='pl20 pr20 pb20 pt10 zuojuzhong'>"
					+"<textarea id='new_content11' style='background: transparent; overflow:auto;  border: 1px solid transparent; height: 130px; width: 100%;' onblur='checksave(\""+"new_startDate11"+"\",\""+"new_content11"+"\",this)'  onfocus='changeback(this)'></textarea>"
					+"</td><td width='100' class='bg_eee' id='bg_eee_2_1'><span class='fr btn'  style='margin:0px auto;float:none'><input type='button' class='fr shanchu1' style='margin:0px auto;float:none' title='删除' id='new_delete1' onclick='deleteselfapp(this)'/></span></td></tr>";
					$("#"+trid).before(str);
					input_notice("new_content11",600);
					tr_num = 0;
			 }
		} 
		function deleteselfapp(obj){
			 interdelete = 1;
			 var r=apprasial_delete();
				/* var id = window.event.srcElement.getAttribute('id'); */
				var id=$(obj).attr("id");
				var arr = new Array();
				arr = id.split("_");
				var idd = arr[0];
				if (r==true){
					$("#"+idd+"_content").attr("disabled",true);
					isDelete =1;
					 if(isSave==1){
						 save_alert()
						 deletecid = idd;
					 }else{
							if(idd=="new"){
								if(document.getElementById("new_top").style.display!="none"){
									 var tr = $("#new_table").find("tr");
								}else{
									var tr = $("#new_table1").find("tr");
								}
						    	if(tr.size()!=4){
						    		if(document.getElementById("new_tr1")!=null){
										$("#new_tr1").next().remove();    
										$("#new_tr1").remove();
									}else if(document.getElementById("new_tr")!=null){
										$("#new_tr").next().remove();    
										$("#new_tr").remove();
									}
						    		isDelete =0;
						    		interdelete = 0;
						    	}else{
						    		isDelete =0;
						    		interdelete = 0;
						    		apprasial_del_Finish();
						    	}
							}else{
								Ext.Ajax.request({
									url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfApp',
									method:'POST',
									defaults:{autoScroll: true},
									success:function(response,options){
										 interdelete = 0;
										if(response.responseText=="1"){
											text_style(idd+"_content");
											alert_delete_failure("自我评价");
											$("#"+idd+"_content").attr("disabled",false);
										}else{
											 if(document.getElementById("new_top").style.display!="none"){
												 var tr = $("#new_table").find("tr");
											}else{
												var tr = $("#new_table1").find("tr");
											}
									    	if(tr.size()==4){
									    		$("#"+idd+"_content").val("");
									    		$("#"+idd+"_content").attr('onblur','');
									    		document.getElementById(idd+"_td").style.backgroundColor ="";
									    		 if(document.getElementById("new_top").style.display!="none"){
									    			 document.getElementById("new_addlocation").style.display= "none"
									    				$("#"+idd+"_content").attr("id","new_content1");
											    		$("#"+idd+"_startDate").attr("id","new_startDate1");
											    		$('#new_startDate1').val('${nowDate}')
											    		$('#'+idd+"_td").attr("id","new_td");
														$('#'+idd+"_tr").attr("id","new_tr");
														$('#'+idd+"_delete").attr("id","new_delete");
														$('#'+idd+"_num").attr("id","new_num1");
														$("#new_content1").unbind("blur");
														$("#new_content1").bind("blur",function(){
															checksave("new_startDate1","new_content1","#new_content1");
														});
														input_notice("new_content1",600);
														$("#new_content1").attr("disabled",false);
												}else{
													document.getElementById("old_addlocation").style.display= "none"
													$("#"+idd+"_content").attr("id","new_content11");
										    		$("#"+idd+"_startDate").attr("id","new_startDate11");
										    		$('#new_startDate11').val('${nowDate}')
										    		$('#'+idd+"_td").attr("id","new_td1");
													$('#'+idd+"_tr").attr("id","new_tr1");
													$('#'+idd+"_delete").attr("id","new_delete1");
													$('#'+idd+"_num").attr("id","new_num");
													$("#new_content11").unbind("blur");
													$("#new_content11").bind("blur",function(){
														checksave("new_startDate11","new_content11","#new_content1");
													});
													input_notice("new_content11",600);
													$("#new_content11").attr("disabled",false);
												}
												interdelete = 0;
									    	}else{
									    		 $("#"+idd+"_tr").next().remove();    
													$("#"+idd+"_tr").remove(); 
													//重新对评价排序
													if(document.getElementById("new_top").style.display!="none"){
														var td = $("#new_table").find("td");
											    		var orderNum=0;
											    	 for(var i=0;i<td.size();i++){
											    		var tdId = $(td[i]).attr("id"); 
											    		if(tdId.indexOf("_num")>0){
											    			$(td[i]).html(++orderNum);
											    		}
											    	} 
													}else{
														var td = $("#new_table1").find("td");
											    		var orderNum=0;
											    	 for(var i=0;i<td.size();i++){
											    		var tdId = $(td[i]).attr("id"); 
											    		if(tdId.indexOf("_num")>0){
											    			$(td[i]).html(++orderNum);
											    		}
											    	} 
													}
									    	}
									    	isDelete = 0;
									    	if($("#new_content1")!=null&&$("#new_content1").val()!=undefined){
												document.getElementById("new_content1").style.border = "1px solid transparent";
											}
											if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
												document.getElementById("new_content11").style.border = "1px solid transparent";
											}
											alert_delete_success("自我评价");
										}
									},
									params : {
										id : idd,
										termId : document.getElementById("termId").value,
										deleteType : "5010"
									}
								}); 
							}
							isDelete = 0;
						}
			  }
			  else{
				  interdelete = 0;
				  return false;
			  }
	    		interdelete = 0;
		}
		
		function deleteselfapp1(checkid){
			 	interdelete = 1;
				var type1 = document.getElementById("evaluateType1").value;
				var type2 = "1";
				var idd = checkid;
					if(idd=="new"){
						if(document.getElementById("new_top").style.display!="none"){
							 var tr = $("#new_table").find("tr");
						}else{
							var tr = $("#new_table1").find("tr");
						}
				    	if(tr.size()!=4){
				    		if(document.getElementById("new_tr1")!=null){
								$("#new_tr1").next().remove();    
								$("#new_tr1").remove();
							}else if(document.getElementById("new_tr")!=null){
								$("#new_tr").next().remove();    
								$("#new_tr").remove();
							}
				    	}else{
				    		apprasial_del_Finish();
				    	}
						isDelete = 0;
					}else{
						Ext.Ajax.request({
							url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfApp',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								 interdelete = 0;
								if(response.responseText=="1"){
									alert_delete_failure("自我评价");
									text_style(checkid+"_content");
								}else{
									 if(document.getElementById("new_top").style.display!="none"){
										 var tr = $("#new_table").find("tr");
									}else{
										var tr = $("#new_table1").find("tr");
									}
							    	if(tr.size()==4){
							    		$("#"+idd+"_content").val("");
							    		$("#"+idd+"_content").attr('onblur','');
							    		document.getElementById(idd+"_td").style.backgroundColor ="";
							    		 if(document.getElementById("new_top").style.display!="none"){
							    			 document.getElementById("new_addlocation").style.display= "none"
							    				$("#"+idd+"_content").attr("id","new_content1");
									    		$("#"+idd+"_startDate").attr("id","new_startDate1");
									    		$('#new_startDate11').val('${nowDate}')
									    		$('#'+idd+"_td").attr("id","new_td");
												$('#'+idd+"_tr").attr("id","new_tr");
												$('#'+idd+"_delete").attr("id","new_delete");
												$('#'+idd+"_num").attr("id","new_num1");
												$("#new_content1").unbind("blur");
												$("#new_content1").bind("blur",function(){
													checksave("new_startDate1","new_content1","#new_content1");
												});
												input_notice("new_content1",600);	
										}else{
											document.getElementById("old_addlocation").style.display= "none"
											$("#"+idd+"_content").attr("id","new_content11");
								    		$("#"+idd+"_startDate").attr("id","new_startDate11");
								    		$('#new_startDate11').val('${nowDate}')
								    		$('#'+idd+"_td").attr("id","new_td1");
											$('#'+idd+"_tr").attr("id","new_tr1");
											$('#'+idd+"_delete").attr("id","new_delete1");
											$('#'+idd+"_num").attr("id","new_num");
											$("#new_content11").unbind("blur");
											$("#new_content11").bind("blur",function(){
												checksave("new_startDate11","new_content11","#new_content11");
											});
											input_notice("new_content11",600);
										}
										interdelete = 0;
							    	}else{
							    		 $("#"+idd+"_tr").next().remove();    
											$("#"+idd+"_tr").remove(); 
											//重新对评价排序
											if(document.getElementById("new_top").style.display!="none"){
												var td = $("#new_table").find("td");
									    		var orderNum=0;
									    	 for(var i=0;i<td.size();i++){
									    		var tdId = $(td[i]).attr("id"); 
									    		if(tdId.indexOf("_num")>0){
									    			$(td[i]).html(++orderNum);
									    		}
									    	} 
											}else{
												var td = $("#new_table1").find("td");
									    		var orderNum=0;
									    	 for(var i=0;i<td.size();i++){
									    		var tdId = $(td[i]).attr("id"); 
									    		if(tdId.indexOf("_num")>0){
									    			$(td[i]).html(++orderNum);
									    		}
									    	} 
											}
											interdelete = 0;
							    	}
							    	isDelete = 0;
							    	if($("#new_content1")!=null&&$("#new_content1").val()!=undefined){
										document.getElementById("new_content1").style.border = "1px solid transparent";
									}
									if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
										document.getElementById("new_content11").style.border = "1px solid transparent";
									}
									alert_delete_success("自我评价");
								}
							},
							params : {
								id : idd,
								termId : document.getElementById("termId").value,
								deleteType : "5010"
							}
						}); 
					}
		}
		///更改学期刷新页面
		function changeterm(){
			ShowDiv();
			var term = document.getElementById("termId").value;
			url="${ctx}/selfappraise/SelfAppAction.a?choicenum="+${choicenum}+"&termId1="+term;
			document.location.replace(url);
		}
			
		function checksave(day,valuenum,obj){
			/* var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.getAttribute('id'); */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idi = arr[0];
			if(idi=="new"){
				isSave = 1;
				save(day,valuenum);
			}else{
				if(id.indexOf("_startDate")>0){
					doupdate1(idi);
				}else{
					doupdate(idi);
				}
			}
		}
		function changeback(obj){
			/* var id = window.event.srcElement.getAttribute('id'); */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idi = arr[0];
			if(idi!="new"){
				if(document.getElementById(idi+"_td")!=null){
					document.getElementById(idi+"_td").style.backgroundColor ="";
					document.getElementById(idi+"_content").style.border = "1px solid black";
				}
			}else{
				if($("#new_content1")!=null&&$("#new_content1").val()!=undefined){
					document.getElementById("new_content1").style.border = "1px solid black";
					clear_notice1("new_content1",600);
				}
				if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
					document.getElementById("new_content11").style.border = "1px solid black";
					clear_notice1("new_content11",600);
				}
			}
		}
		$(document).ready(function(){
			if($("#new_content1")!=null&&$("#new_content1").val()!=undefined){
			   input_notice("new_content1",600);
			}
		
		});
		
		   window.onbeforeunload = onbeforeunload_handler;   
		  function onbeforeunload_handler(){
			 	var i = 0;
		    	$("textarea").each(function(){
					if($(this).css('borderLeftWidth')=="2px"){
						i=1;
					}
				});
		    	if(i==1){
		    		var warning=window_close();
			        return warning; 
		    	} 
		    }  
		//窗口关闭触发事件
	　　$(window).load(function() {
		 var thisMession=document.getElementById("mession");
		
		 thisMession.innerHTML="";
		 thisMession.innerHTML=mess;  
		  });

</script>
</html>