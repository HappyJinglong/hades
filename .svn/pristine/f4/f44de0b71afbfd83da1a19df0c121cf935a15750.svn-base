<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 <%@ include file="/common/mass.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link href="${ctx}/ext-2.1/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>班主任评价学生</title>
<style type="text/css">
	body,html{
    overflow-x:hidden;
	overflow-y:hidden; 
}
#pj_ziwo_main .biaoge .shanchu1{
	margin-right :20px;
}
#pj_ziwo_main .biaoge .shanchu{
		margin-right :20px;
}
#pj_ziwo_main {
		top:37px;
		padding:0px 13px;
		margin-bottom:25px;
}
#pj_ziwo_main .biaoge .downbox{
	margin-bottom :1px;
	padding-left:5px;
	margin-top:5px;
}
.pb30{
	padding-bottom:0px;
}
.wenbenyu670{
	/* line-height:16px;*/
	padding-left:5px;
	padding-top:5px;
} 
</style>
<script type="text/javascript">
$(window).load(function() {
	 var thisMession=document.getElementById("mession");

     thisMession.innerHTML="";
	 thisMession.innerHTML=mess;  
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
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	ShowDiv();
	window.location.href="${ctx}/middlemaster/StudentApprisedAction.a?studentid="+'${studentid}'+"&key="+'${key}'+"&name="+'${name}'+"&classId="+'${classId}'+"&eduid="+'${eduid}'+"&isHistory=1"+"&zsTermId="+zsTermId;
}
var isNull = 0;
var isSave = 0;
var isDelete=0;
var deleteId = "";
function save(currentObj,tableObj){
	if(currentObj.attr("id").indexOf("newAdd")==0){
		isSave=1;
		saveNewContent(currentObj,tableObj);
	}else{
		updateContent(currentObj,tableObj,0);
		if(isNull==1){
			isNull=0;
			return;
		}
    	if(tableObj.attr("id")=="44"){
    		$("#"+currentObj.attr("id").split("_")[0]+"_subject").css("background","#eee");	
    	}
		currentObj.closest('td').css("background","#eee");
		/* clear_style(currentObj.attr("id")); */
	}
}
function deleteContent(currentObj,tableObj,url){
	var appId = currentObj.attr("id").split("_")[0];
	$("#"+appId+"_content").attr("disabled",true);
	var sectionCode = tableObj.attr("id");
	var textarea = tableObj.find("textarea");
	if(textarea.size()==1 && $(textarea[0]).attr("id").indexOf("newAdd")==0){
		apprasial_del_Finish();
		isDelete=0;
		return;
	}
	if(appId==sectionCode && textarea.size()>0){
		$("#"+sectionCode+"_tpOne").remove();
		$("#"+sectionCode+"_tpTwo").remove();
		isDelete=0;
		return;
	}
	Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	Ext.Ajax.request({
		url:url,
		method:'POST',
		defaults:{autoScroll: true},
		success:function(response,options){
			isDelete=0;
			var temp=response.responseText;
		    if(temp!="false"){
		    	//移除相应的tr或者div
		    	var tr = tableObj.find("tr");
		    	var lastTr = tr[tr.size()-1];
		    	if(tr.size()==4){
		    		$("#"+appId+"_delete").attr("id",sectionCode+"_delete");
		    		$("#"+appId+"_content").val("");
		    		$("#"+appId+"_content").closest('td').css("background","");
					if(sectionCode=="44")
					{
						$("#"+appId+"_subject").css("background","");	
						$("#"+appId+"_subject").val("1");
						$("#"+appId+"_subject").attr("id","newSubject_"+sectionCode);
					}
		    		$("#"+appId+"_content").attr("idValue","");
		    		$("#"+appId+'_tpOne').attr("id",sectionCode+"_tpOne");
		    		$("#"+appId+'_tpTwo').attr("id",sectionCode+"_tpTwo");
		    		$("#"+appId+"_content").attr("id","newAdd_"+sectionCode);
		    		$("#"+appId+"_Date").attr("id","newDate_"+sectionCode);		    		
				    $("#"+sectionCode+"_tpThree").css("display","none");
				    if(sectionCode == '22'){
		    			 input_noticemaster("newAdd_"+sectionCode,600);
		    		}else{
		    			input_notice("newAdd_"+sectionCode,600);
		    		}			    
				    $("#newAdd_"+sectionCode).attr("disabled",false);
		    	}else{
		    		for(var i=0;i<tr.size();i++){
			    		var trId = $(tr[i]).attr("id");
			    		if(trId.indexOf(appId)==0){
			    			$(tr[i]).remove();
			    		}
			    	}
			    	//重新对评价排序
			    	var td = tableObj.find("td");
			    	var orderNum=0;
			    	for(var i=0;i<td.size();i++){
			    		var tdId = $(td[i]).attr("id"); 
			    		if(tdId.indexOf("_order")>0){
			    			$(td[i]).html(++orderNum);
			    		}
			    	}
			    	tableObj.append(lastTr);
		    	}
		    	
		    	alert_delete_success($("#"+sectionCode+"_SN").html().replace("*",""));
		    }else{
		    	//提示删除失败 具体到某一个人
		    	alert_delete_failure($("#"+sectionCode+"_SN").html().replace("*",""));
		    	text_style(appId+"_content");
		    }
		},
		params : {
			proKey:appId,
			evaluatetypeid:sectionCode,
			studentid:$("#sid").val(),
			classId:$("#clad").val(),
			zsTermId:$("#termId option:selected").val(),
			eduid:$("#eduid").val(),
			isHistory:1
		}
	});
}
function saveNewContent(currentObj,tableObj){
	var sectionCode = tableObj.attr("id");
	/* alert(sectionCode); */
    var content = currentObj.val();
    var textId=currentObj.attr("id");
	var subject = null;
	var tempContent = content.replace(/\s/g,"");
	if(tempContent!="" && tempContent!="最多输入600字")
	{
		if(content.length>600)
		{
			apprasial_alert(600);
			isSave=0;
			return;
		}
		$("#newDate_"+sectionCode).attr("disabled",true);
		currentObj.attr("disabled",true);
		if(sectionCode=="44")
		{
			subject = $("#newSubject_"+sectionCode+" option:selected").val();
			$("#newSubject_"+sectionCode).attr("disabled",true);
		}
	    if(sectionCode=="44"){
	    	currentObj.css("border","1px solid #999");
	    }else{
	    	 clear_style(textId); 
	    	 currentObj.css("border","1px solid transparent");
	    }
		Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		Ext.Ajax.request({
			url:'${ctx}/middlemaster/MasterApriseDataAction.a?doInsertMasterAppraisal',
			method:'POST',
			success:function(response,options){
				isSave=0;
				var temp=response.responseText; 
				 if(temp!="false"){
					 $("#newDate_"+sectionCode).attr("disabled",false);
					 currentObj.attr("disabled",false);
					 currentObj.closest('td').css("background","#eee");
					 if(sectionCode=="44")
					 {
						 $("#newSubject_"+sectionCode).css("background","#eee");
						 $("#newSubject_"+sectionCode).attr("disabled",false);
						 $("#newSubject_"+sectionCode).attr("id",temp+"_subject");
					 }
					 $("#"+sectionCode+"_tpOne").attr("id",temp+"_tpOne");
					 $("#"+sectionCode+"_tpTwo").attr("id",temp+"_tpTwo");
					 $("#"+sectionCode+"_delete").attr("id",temp+"_delete");
					 if(sectionCode!="22"){
						 $("#"+sectionCode+"_tpThree").css("display","");
					 }
					 currentObj.attr("idValue",currentObj.val());
					 currentObj.attr("id",temp+"_content");
					 $("#"+temp+"_content").bind("focus",function(){
						 changeBack($(this));
					 });
					 $("#newDate_"+sectionCode).attr("id",temp+"_Date");
					 if(isDelete==1){
						//alert_save_success($("#"+sectionCode+"_SN").html().replace("*",""), $("#"+temp+"_tpOne").children(":first").html());
						var deleteid = deleteId.split("_")[0];
					   if(deleteid==sectionCode){  
							 //deleteCountentPage($("#"+temp+"_delete"),$("#"+sectionCode));
						   deleteContent($("#"+temp+"_delete"),$("#"+sectionCode),'${ctx}/middlemaster/MasterApriseDataAction.a?doDeleteMasterAppraisal');
					 	 }else{
							 //deleteCountentPage($("#"+deleteid+"_delete"),$("#"+deleteid+"_delete").closest("table")); 
							 alert_save_success($("#"+sectionCode+"_SN").html().replace("*",""), $("#"+temp+"_tpOne").children(":first").html());
					 		deleteContent($("#"+deleteid+"_delete"),$("#"+deleteid+"_delete").closest("table"),'${ctx}/middlemaster/MasterApriseDataAction.a?doDeleteMasterAppraisal');
						 }  
					 }else{
						 alert_save_success($("#"+sectionCode+"_SN").html().replace("*",""), $("#"+temp+"_tpOne").children(":first").html());
					 }
				 }else{
					 alert_save_failure($("#"+sectionCode+"_SN").html().replace("*",""));
					 $("#newDate_"+sectionCode).attr("disabled",false);
					 currentObj.attr("disabled",false);
					 if(sectionCode=="44")
					 {
					    $("#newSubject_"+sectionCode).attr("disabled",false);
					 }
					 text_style(textId);
				 }
			},
			params : {
				apprasial : currentObj.val(),
				signDate : $("#newDate_"+sectionCode).val(),
				evaluatetypeid : sectionCode,
				studentid:$("#sid").val(),
				classId:$("#clad").val(),
				eduid:$("#eduid").val(),
				zsTermId:$("#termId option:selected").val(),
				subject:subject,
				isHistory:1,
				studentname:'${name}'
			}
		}); 
	}else{
		isSave=0;
		if(sectionCode == '22'){
			input_noticemaster(textId,600);
		}else{
			input_notice(textId,600);
		}
		
	    if(sectionCode=="44"){
	    	currentObj.css("border","1px solid #999");
	    }else{
	    	 clear_style(textId); 
	    	 currentObj.css("border","1px solid transparent");
	    }
	}  
}


function updateContent(currentObj,tableObj,num){
		var sectionCode = tableObj.attr("id");
		var proKey = currentObj.attr("id").split("_")[0];
		var subject = null;
		if(sectionCode=="44"){
			subject = $("#"+proKey+"_subject option:selected").val();
		}
		var newContent = currentObj.val();
		var oldContent = currentObj.attr("idValue");
		if(sectionCode == '22'){			
			if($.trim(newContent)=="" || $.trim(newContent) == "最多输入600字，班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。"){
				isNull=1;
				return;
			}
		}else{
			if($.trim(newContent)=="" || $.trim(newContent)=="最多输入600字"){
				
				alert($.trim(newContent) == "最多输入600字");
				isNull=1;
				return;
			}
		} 
		
		if(newContent.length>600)
		{
			isNull=1;
			apprasial_alert(600);
			return;
		}
	    if(sectionCode=="44"){
	    	currentObj.css("border","1px solid #999");
	    }else{
	    	clear_style(proKey+"_content");
	    	currentObj.css("border","1px solid transparent");
	    }
		if(num==0){
			if(newContent==oldContent){
				return;
			}
		}
		Ext.Ajax.request({
			url:'${ctx}/middlemaster/MasterApriseDataAction.a?doUpadateMasterAppraisal',
			method:'POST',
			defaults:{autoScroll: true},
			success:function(response,options){
			 	var temp=response.responseText;
			    if(temp!="false"){
			    	currentObj.attr("idValue",newContent);

			    	if($("#"+proKey+"_sign")){
			    		$("#"+proKey+"_sign").html("${teacherName}");
			    	} 
			    	alert_update_success($("#"+sectionCode+"_SN").html().replace("*",""), $("#"+proKey+"_tpOne").children(":first").html());
			    }else{
			    	alert_update_failure($("#"+sectionCode+"_SN").html().replace("*",""));
			    	text_style(currentObj.attr("id"));
			    }
			},
			params : {
				proKey : proKey,
				signDate : $("#"+proKey+"_Date").val(),
				apprasial : newContent,
				evaluatetypeid:sectionCode,
				studentid:$("#sid").val(),
				classId:$("#clad").val(),
				zsTermId:$("#termId option:selected").val(),
				subject:subject,
				isHistory:1,
				eduid:$("#eduid").val(),
				studentname:'${name}'
			}
		});
}
function deleteCountentPage(currentObj,tableObj){
	deleteId = currentObj.attr("id");
	var flag = apprasial_delete();
	if(flag){
		isDelete=1;
		if(isSave==1){
			return;
		}
		deleteContent(currentObj,tableObj,'${ctx}/middlemaster/MasterApriseDataAction.a?doDeleteMasterAppraisal');
	}
	
}
function changeBack(currentObj){
	
	if(currentObj.css('borderLeftWidth')=="2px"){
		return;
	}
	currentObj.closest('td').css("background","");
	var sectionCode=currentObj.closest('table').attr("id")
	var textId=currentObj.attr("id");
	text_style1(textId);
	var split_content = textId.split("_")[1];
	var split_id=textId.split("_")[0];
	if(sectionCode == '22'){
		clear_noticemaster(textId,600);
	}else{
		clear_notice1(textId,600);
	}
	
	if(sectionCode=="44"&&split_content=="content")
	{
		$("#"+split_id+"_subject").css("background","");
	}
}
function addNewContent(currentObj){
	var width = $("#bg_eee_1_1").attr("width");
	var sectionCode = currentObj.attr("id").split("_")[0];
	var texts = $("#"+sectionCode).find("textarea");
	for(var i=0;i<texts.size();i++){
		var text = texts[i];
		if($(text).attr("id").indexOf("newAdd")==0){
			return;
		}
	}
	var tds = $("#"+sectionCode).find("td");
	var countTD =0;
	for(i=0;i<tds.size();i++){
		if($(tds[i]).attr("id").indexOf("_order")>0){
			countTD++;
		}
	}
	var newContent ='<tr id="'+sectionCode+'_tpOne">'
    	+'<td id="'+sectionCode+'_order" width="10%" height="40" class="bg_eee">'+(countTD+1)+'</td>'
   		+'<td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${teacherName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
    	+'&nbsp;日期：<input id="newDate_'+sectionCode+'" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$(\'#'+sectionCode+'\'))"/>'
    	+'</td>'
  		+'</tr>'
  		+'<tr id="'+sectionCode+'_tpTwo">'
    	+'<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong ">'
    	+'<textarea class="content"'
     	+' id="newAdd_'+sectionCode+'"'
      	+'idValue=""'
      	+'style="background: transparent;  border: 1px solid transparent;  height: 130px; width: 100.25%;"'
      	+'onblur="save($(this),$(\'#'+sectionCode+'\'));" onfocus="changeBack($(this));"></textarea>'
    	+'</td>'
    	+'<td width="'+width+'" class="bg_eee" id="bg_eee_1_1"><span class="fr btn">'
       	+'<input id="'+sectionCode+'_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$(\'#'+sectionCode+'\'));"/>'
    	+'</span></td>'
  		+'</tr>';
  		currentObj.parent().parent().parent().before(newContent);
  		input_notice("newAdd_"+sectionCode,600);
}
function addNewContent_subject(currentObj){
	var width = $("#bg_eee_1_1").attr("width");
	var sectionCode = currentObj.attr("id").split("_")[0];
	var texts = $("#"+sectionCode).find("textarea");
	for(var i=0;i<texts.size();i++){
		var text = texts[i];
		if($(text).attr("id").indexOf("newAdd")==0){
			return;
		}
	}
	var tds = $("#"+sectionCode).find("td");
	var countTD =0;
	for(i=0;i<tds.size();i++){
		if($(tds[i]).attr("id").indexOf("_order")>0){
			countTD++;
		}
	}
	var newContent ='<tr id="'+sectionCode+'_tpOne">'
    	+'<td id="'+sectionCode+'_order" width="10%" height="40" class="bg_eee">'+(countTD+1)+'</td>'
   		+'<td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${teacherName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
    	+'&nbsp;日期：<input id="newDate_'+sectionCode+'" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$(\'#'+sectionCode+'\'))"/>'
    	+'</td></tr><tr id="'+sectionCode+'_tpTwo">'
    	+'<td colspan="2" class="h50"><div class="downbox mt18"><span class="fl wenben85left">选择科目：</span>'
    	+'<span class="fl"> <select name="select" class=" wenziliebiao100" id="newSubject_'+sectionCode+'" style="width:150px;overflow:scroll;" onchange="saveSubject($(this),$(\'#'+sectionCode+'\'))">'
    	+'<c:forEach items="${subjectDtos }" var="list" varStatus="status"><c:if test="${status.index==0}">'
    	+'<option selected="selected" value="${list.subjectid }">${list.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option> </c:if><c:if test="${status.index!=0}">'
    	+'<option value="${list.subjectid }">${list.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option></c:if></c:forEach></select> </span></div>'
    	+'<div class="downbox mt18"><span class="fl wenben85left">课程评语：</span><textarea id="newAdd_'+sectionCode+'" idValue="" class="fl " style="background: transparent; height: 130px; width: 98.7%;margin-left:0px;border: 1px solid #999"'
      	+'onblur="save($(this),$(\'#'+sectionCode+'\'));" onfocus="changeBack($(this));"></textarea></div>'
    	+'</td>'
    	+'<td width="'+width+'" class="bg_eee" id="bg_eee_1_1"><span class="fr btn">'
       	+'<input id="'+sectionCode+'_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$(\'#'+sectionCode+'\'));"/>'
    	+'</span></td>'
  		+'</tr>';
  		currentObj.parent().parent().parent().before(newContent);
  		input_notice("newAdd_"+sectionCode,600);
}
function saveTime(currentObj,tableObj){
	//获取当前textarea
	
	var timeId = currentObj.attr("id");
	if(timeId.indexOf("newDate")==0){
		saveNewContent($("#newAdd_"+newTextid),tableObj);
	}else{
		var oldTextid = timeId.split("_")[0];
		updateContent($("#"+oldTextid+"_content"),tableObj,1);
	}
}
function saveSubject(currentObj,tableObj)
{
	//获取当前的textarea
	var subjectId=currentObj.attr("id");
	if(subjectId.indexOf("newSubject")==0)
	{
		saveNewContent($("#newAdd_"+newTextid),tableObj);
	}else{
		var oldTextid=subjectId.split("_")[0];
		updateContent($("#"+oldTextid+"_content"),tableObj,1);
	}
}
$(document).ready(function(){
	var texts = $("body").find("textarea");
	for(var i=0;i<texts.size();i++){
		var textId = $(texts[i]).attr("id");
		
		
		if(textId.indexOf("newAdd")==0){
			var sectionCode = textId.split("_")[1];
			if(sectionCode == '22'){
				input_noticemaster(textId,600)
			}else{
				input_notice(textId,600)
			}
			
		}
	}
});
</script>
</head>

<body>
<input type="hidden"  id="sid" name="studentid" value="${studentid }"/>
<input type="hidden"  id="clad" name="classid" value="${classId }"/>
<input type="hidden" id="eduid" name="eduid" value="${eduid}"/>
<input type="hidden" id="sName" name="sName" value="${name}"/>
<div class="dangqianwz">
 	<span class="fl">当前位置：评价学生->${name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
     <select name="select" id="termId" class="  wenziliebiao100"  onchange="chooseTerm()">
            <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
    </select> </span>
 </div>

<div id="pj_ziwo_main">
	<c:forEach items="${sections}" var="section" varStatus="pVs">
	  <div class="down mt18 pb30">
	  <c:if test="${section.sectionId ne '44'}">
	    <table id="${section.sectionId}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		    <tr class="title_bg">
		    <td colspan="3" id="${section.sectionId}_SN" name="masterName">${section.sectionName}<c:if test="${section.sectionId eq '22'}"><span class="red">*</span></c:if></td>
	        </tr>
	        <c:if test="${fn:length(section.aInfos)!=0}">
		        <c:forEach items="${section.aInfos}" var="aInfo" varStatus="vs">
				    <tr id="${aInfo.apprasialid}_tpOne">
				      <td id="${aInfo.apprasialid}_order" width="10%" height="40" class="bg_eee">${vs.count}</td>
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：<span id="${aInfo.apprasialid}_sign">${aInfo.appraser}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" />-->
				      	<input id="${aInfo.apprasialid}_Date" type="text" size="10" value="${empty aInfo.signdate ? date_content : aInfo.signdate}" idvalue="${aInfo.signdate }"
								onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td> 
				      	
			        </tr>
				    <tr id="${aInfo.apprasialid}_tpTwo">
				      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong " style="background:#eee">
	                    <textarea class="content"
		                id="${aInfo.apprasialid}_content"
		                idValue="${aInfo.apprasial }"
		                style="background: transparent;  border: 1px solid transparent;  height: 130px; width: 100.25%;"
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));">${aInfo.apprasial }</textarea>
			          </td>
				      <td width="10%" id="bg_eee_1_1" class="bg_eee"><span class="fr btn">
			         	<input id="${aInfo.apprasialid}_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$('#${section.sectionId}'));"/>
				      </span></td>
			        </tr>
			        	<c:if test="${vs.count==fn:length(section.aInfos)}">
							<tr id="${section.sectionId}_tpThree" <c:if test="${section.sectionId eq '22'}"> style="display:none;" </c:if>>
								<td colspan="3" class="bg_eee h80">
									<div class="fr btn">
										<input id="${section.sectionId}_add" type="button"  class="fr shanchu" onclick="addNewContent($(this))"/>
									</div>
								</td>
							</tr>
						</c:if>
				</c:forEach>
	        </c:if>
     	    <c:if test="${fn:length(section.aInfos)==0}">
				    <tr id="${section.sectionId}_tpOne">
				      <td id="${section.sectionId}_order" width="10%" height="40" class="bg_eee">1</td>
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${teacherName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" /> -->
				      	<input id="newDate_${section.sectionId}" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td>
			        </tr>
				    <tr id="${section.sectionId}_tpTwo">
				      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong">
	                  <textarea class="content"
		                id="newAdd_${section.sectionId}"
		                idValue=""
		                style="background: transparent;  border: 1px solid transparent;  height: 130px; width: 100.25%;"
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));"></textarea>
			          </td>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1"><span class="fr btn">
				         <input id="${section.sectionId}_delete" type="button" class="fr shanchu1" onclick = "deleteCountentPage($(this),$('#${section.sectionId}'))"/>
				      </span></td>
			        </tr>
			        
      					<tr id="${section.sectionId}_tpThree" style="display:none;">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input id="${section.sectionId}_add" type="button" class="fr shanchu" onclick="addNewContent($(this))"/>
							</div>
						</td>
					</tr>
	        </c:if>
		 </table>
		 </c:if>
		 <c:if test="${section.sectionId eq '44'}">
		      <table id="${section.sectionId}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		    <tr class="title_bg">
		    <td colspan="3" id="${section.sectionId}_SN">${section.sectionName}<span class="red">*</span></td>
	        </tr>
	        <c:if test="${fn:length(section.aInfos)!=0}">
		        <c:forEach items="${section.aInfos}" var="aInfo" varStatus="vs">
				    <tr id="${aInfo.apprasialid}_tpOne">
				      <td id="${aInfo.apprasialid}_order" width="10%" height="40" class="bg_eee">${vs.count}</td>
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：<span id="${aInfo.apprasialid}_sign">${aInfo.appraser}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" />-->
				      	<input id="${aInfo.apprasialid}_Date" type="text" size="10" value="${empty aInfo.signdate ? date_content : aInfo.signdate}" idvalue="${aInfo.signdate }"
								onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td>  	
			        </tr>
				    <tr id="${aInfo.apprasialid}_tpTwo">
				      <td colspan="2" class="h50" style="background:#eee">
				        <div class="downbox mt18" >
                         <span class="fl wenben85left">选择科目：</span>
                         <span class="fl"> <select name="select" class="wenziliebiao100" id="${aInfo.apprasialid}_subject" onchange="saveSubject($(this),$('#${section.sectionId}'))" style="width:150px;overflow:scroll;background:#eee;" >
                         <c:forEach items="${subjectDtos }" var="list1" varStatus="status">
                           <c:if test="${aInfo.subject eq list1.subjectid}">
                               <option value="${list1.subjectid}" selected="selected">${list1.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
                           </c:if>
                           <c:if test="${aInfo.subject!=list1.subjectid}">
                               <option value="${list1.subjectid }">${list1.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
                           </c:if>
                         </c:forEach>
                         </select> </span>
                        </div>
                        <div class="downbox mt18" >
                         <span class="fl wenben85left">课程评语：</span>
	                    <textarea 
	                    style="background: transparent; height: 130px; width: 98.7%;margin-left:0px;border: 1px solid #999"
		                id="${aInfo.apprasialid}_content"
		                idValue="${aInfo.apprasial }"
		                class="fl "
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));">${aInfo.apprasial }</textarea>
		                </div>
			          </td>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1"><span class="fr btn">
			         	<input id="${aInfo.apprasialid}_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$('#${section.sectionId}'));"/>
				      </span></td>
			        </tr>
			        	<c:if test="${vs.count==fn:length(section.aInfos)}">
							<tr id="${section.sectionId}_tpThree">
								<td colspan="3" class="bg_eee h80">
									<div class="fr btn">
										<input id="${section.sectionId}_add" type="button"  class="fr shanchu" onclick="addNewContent_subject($(this))"/>
									</div>
								</td>
							</tr>
						</c:if>
				</c:forEach>
	        </c:if>
     	    <c:if test="${fn:length(section.aInfos)==0}">
				    <tr id="${section.sectionId}_tpOne">
				      <td id="${section.sectionId}_order" width="10%" height="40" class="bg_eee">1</td>
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：班主任&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${teacherName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" /> -->
				      	<input id="newDate_${section.sectionId}" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td>
			        </tr>
				    <tr id="${section.sectionId}_tpTwo">
				      <td colspan="2" class="h50">
				        <div class="downbox mt18">
                         <span class="fl wenben85left">选择科目：</span>
                         <span class="fl"> <select name="select" class=" wenziliebiao100" id="newSubject_${section.sectionId}" onchange="saveSubject($(this),$('#${section.sectionId}'))" style="width:150px;overflow:scroll;" >
                         <c:forEach items="${subjectDtos }" var="list" varStatus="status">
                             <c:if test="${status.index==0}">
                              <option selected="selected" value="${list.subjectid }">${list.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
                             </c:if>
                             <c:if test="${status.index!=0}">
                              <option value="${list.subjectid }">${list.subjectName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
                             </c:if>
                        </c:forEach>
                        </select> </span>
                       </div>
                       <div class="downbox mt18">
                         <span class="fl wenben85left">课程评语：</span>
	                  <textarea 
						style="background: transparent;  height: 130px; width: 98.7%;margin-left:0px;border: 1px solid #999"
		                id="newAdd_${section.sectionId}"
		                idValue=""
		                class="fl"
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));"></textarea>
		                </div>
			          </td>
				      <td width="10%" class="bg_eee" id="bg_eee_1_1"><span class="fr btn">
				         <input id="${section.sectionId}_delete" type="button" class="fr shanchu1" onclick = "deleteCountentPage($(this),$('#${section.sectionId}'))"/>
				      </span></td>
			        </tr>
      					<tr id="${section.sectionId}_tpThree" style="display:none;">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input id="${section.sectionId}_add" type="button" class="fr shanchu" onclick="addNewContent_subject($(this))"/>
							</div>
						</td>
					</tr>
	        </c:if>
		 </table>
		 </c:if>
	</div>
  </c:forEach>
</div>
  <c:if test="${empty sections }">
  	<div style="padding:60px 0px 0px 0px;">
  		<img src="${ctx }/images/sorry.jpg" />
  	</div>
</c:if>
<%@ include file="/common/div.jsp"%>
</body>
</html>

