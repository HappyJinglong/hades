<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/mass.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<title>家长评价孩子</title>
<style type="text/css">
#pj_ziwo_main .biaoge .shanchu1{
	margin-right :29px;
}
#pj_ziwo_main .biaoge .shanchu{
		margin-right :29px;
}
#pj_ziwo_main{
   top:20px;
   padding-top:13px;
   padding-right:13px;
   padding-left:13px;
}
.dangqianwz{
   width:98.5%;
}
html,body{
   magin-bottom:6px;
}
.pb21{
  padding-bottom:21px;
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
var levelcode='${levelcode1}';
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	ShowDiv();
    window.location.href="${ctx}/appraisechild/AppraisalChildAction.a?termid="+zsTermId+"&&showdiv=1";
}
var isSave = 0;  //是否保存
var isDelete=0;   //是否删除
var isSaveDelete=0;   //是不是保存时删除
var isSaved_ok=0;    //是不是保存成功
var deleteId = "";
var isFalsed=0;
function save(currentObj,tableObj){
	if(currentObj.attr("id").indexOf("newAdd")==0){
		isSave=1;
		saveNewContent(currentObj,tableObj);
	}else{
		updateContent(currentObj,tableObj,0);
		var proKey = currentObj.attr("id").split("_")[0];
		if(isFalsed==1)
		{
			isFalsed=0;
			return;
		}
		currentObj.parent().css("background","#eee");
		clear_style(proKey+"_content");
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
		    		$("#"+appId+"_content").parent().css("background","");
		    		$("#"+appId+"_content").attr("idValue","");
		    		$("#"+appId+'_tpOne').attr("id",sectionCode+"_tpOne");
		    		$("#"+appId+'_tpTwo').attr("id",sectionCode+"_tpTwo");
		    		$("#"+appId+"_content").attr("id","newAdd_"+sectionCode);
		    		$("#"+appId+"_Date").attr("id","newDate_"+sectionCode);
		    		$("#"+appId+"_order").attr("id",sectionCode+"_order");
				    $("#"+sectionCode+"_tpThree").css("display","none");
				    input_notice_number("newAdd_"+sectionCode,sectionCode);
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
			appraisalid : appId,
			evaluatetypeid : sectionCode,
			termid : $("#termId option:selected").val()
		}
	});
}
function saveNewContent(currentObj,tableObj){
	var sectionCode = tableObj.attr("id");
    var content1 = currentObj.val();
    var textId = currentObj.attr("id");
    var order = $("#"+sectionCode+"_order").html();
    clear_style(textId);
    var content=content1.replace(/(^\s*)|(\s*$)/g, "");
	if(content!="")
	{
		if(check_notice_number(content1,sectionCode))
		{
			isSave=0;
			return;
		}
		$("#newDate_"+sectionCode).attr("disabled",true);
		currentObj.attr("disabled",true);
		Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
		Ext.Ajax.request({
			url:'${ctx}/appraisechild/AppraisalChildAction.a?doInsertAppraisalChild',
			method:'POST',
			success:function(response,options){
				var temp=response.responseText; 
				 if(temp!="false"){
					 isSaved_ok=0;
					 isSave=0;
					 $("#newDate_"+sectionCode).attr("disabled",false);
					 currentObj.attr("disabled",false);
					 currentObj.parent().css("background","#eee");
					 $("#"+sectionCode+"_tpOne").attr("id",temp+"_tpOne");
					 $("#"+sectionCode+"_order").attr("id",temp+"_order");
					 $("#"+sectionCode+"_tpTwo").attr("id",temp+"_tpTwo");
					 $("#"+sectionCode+"_delete").attr("id",temp+"_delete");
					 if(sectionCode!="2040"&&sectionCode!="1020"&&sectionCode!="23"){
						 $("#"+sectionCode+"_tpThree").css("display","");
					 }
					 currentObj.attr("idValue",currentObj.val());
					 currentObj.attr("id",temp+"_content");
					 $("#"+temp+"_content").bind("focus",function(){
						 changeBack($(this));
					 });
					 $("#newDate_"+sectionCode).attr("id",temp+"_Date");
					 if(isDelete==1){
					   var deleteid = deleteId.split("_")[0];
					   if(deleteid==sectionCode){  
							/*  deleteCountentPage($("#"+temp+"_delete"),$("#"+sectionCode)); */
							 deleteContent($("#"+temp+"_delete"),$("#"+sectionCode),'${ctx}/appraisechild/AppraisalChildAction.a?doDeleteAppraisalChild');
					 	 }else{
					 		 alert_save_success($("#"+sectionCode+"_SN").html().replace("*",""),order);
					 		$("#"+deleteid+"_delete"),$("#"+deleteid+"_delete").closest("table")
					 		deleteContent($("#"+deleteid+"_delete"),$("#"+deleteid+"_delete").closest("table"),'${ctx}/appraisechild/AppraisalChildAction.a?doDeleteAppraisalChild');
							 /* deleteCountentPage($("#"+deleteid+"_delete"),$("#"+deleteid+"_delete").closest("table"));  */
						 }  
					 }else{
						 alert_save_success($("#"+sectionCode+"_SN").html().replace("*",""),order);
					 }
				 }else{
					 alert_save_failure($("#"+sectionCode+"_SN").html().replace("*",""));
					 $("#newDate_"+sectionCode).attr("disabled",false);
					 currentObj.attr("disabled",false);
					 text_style(textId);
					 isSaved_ok=1;
					 isSave=0;
				 }
			},
			params : {
				apprasial : content1,
				signDate : $("#newDate_"+sectionCode).val(),
				evaluatetypeid : sectionCode,
				studentid:$("#sid").val(),
				termid:$("#termId option:selected").val(),
				appraserid:$("#fid").val(),
				parentname:$("#pname").val()
			}
		});
	}else{
		isSave=0;
		input_notice_number(textId,sectionCode);
	}
}
//当空白时在文本框内显示提示语
function input_notice_number(textId,sectionCode)
{
	if(levelcode=="2012002")
	{
		input_notice(textId,600);
	}else if(levelcode=="2012003"||levelcode=="2012004")
	{
		if(sectionCode=="2040"||sectionCode=="1020")
		{
			input_notice(textId,300);
		}else 
		{
			input_notice(textId,4000);
		}
	}
}
//当填写的内容超过一定字数后提示
function check_notice_number(content,sectionCode)
{
	if(levelcode=="2012002")
	{
		if(content.length>600)
		{
			apprasial_alert(600);
			return true;
		}
	}else if(levelcode=="2012003"||levelcode=="2012004")
	{
		if((sectionCode=="2040"||sectionCode=="1020")&&(content.length>300))
		{
			apprasial_alert(300);
			return true;
		}else if(content.length>4000)
		{
			apprasial_alert(4000);
			return true;
		}
	}
}
function updateContent(currentObj,tableObj,num){

		var proKey = currentObj.attr("id").split("_")[0];
		var newContent = currentObj.val();
		var oldContent = currentObj.attr("idValue");
		var sectionCode = tableObj.attr("id");
		var order = $("#"+proKey+"_order").html();
		if(num==0){
			if(newContent==oldContent){
				currentObj.parent().css("background","#eee");
				isSave=0;
				return;
			}
		}
		if(newContent.replace(/(^\s*)|(\s*$)/g, "")=="")
		{
			isFalsed=1;
			return;
		}
		if(check_notice_number(newContent,sectionCode))
		{
			isFalsed=1;
			return;
		}
		Ext.Ajax.request({
			url:'${ctx}/appraisechild/AppraisalChildAction.a?doUpadateAppraisalChild',
			method:'POST',
			defaults:{autoScroll: true},
			success:function(response,options){
			 	var temp=response.responseText;
			    if(temp!="false"){
			    	currentObj.attr("idValue",newContent);
			    	alert_update_success($("#"+sectionCode+"_SN").html().replace("*",""),order);
			    }else{
			    	alert_update_failure($("#"+sectionCode+"_SN").html().replace("*",""));
			    	text_style(currentObj.attr("id"));
			    }
			},
			params : {
				appraisalid : proKey,
				signDate : $("#"+proKey+"_Date").val(),
				apprasial : newContent,
				evaluatetypeid : sectionCode,
				termid:$("#termId option:selected").val()
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
	deleteContent(currentObj,tableObj,'${ctx}/appraisechild/AppraisalChildAction.a?doDeleteAppraisalChild');
	}
}
function changeBack(currentObj){
	currentObj.parent().css("background","");
	var textId=currentObj.attr("id");
	text_style1(textId);
	var sectionCode = textId.split("_")[1];
	levelcode_clear_notice(textId,sectionCode);
}
//清除文本框里的提示语
function levelcode_clear_notice(textId,sectionCode) 
{

	if(levelcode=="2012002")
	{
		clear_notice1(textId,600);
	}else if(levelcode=="2012003"||levelcode=="2012004")
	{
		if(sectionCode=="2040"||sectionCode=="1020")
		{
			clear_notice1(textId,300);
		}else
		{
			clear_notice1(textId,4000);
		}
	}
}
function addNewContent(currentObj){
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
   		+'<td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：家长 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${parentname } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
    	+'日期：<input id="newDate_'+sectionCode+'" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$(\'#'+sectionCode+'\'))"/>'
    	+'</td>'
  		+'</tr>'
  		+'<tr id="'+sectionCode+'_tpTwo">'
    	+'<td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong ">'
    	+'<textarea class="content"'
     	+' id="newAdd_'+sectionCode+'"'
      	+'idValue=""'
      	+'style="background: transparent; border:1px solid transparent; height: 120px; width: 100%;"'
      	+'onblur="save($(this),$(\'#'+sectionCode+'\'));" onfocus="changeBack($(this));"></textarea>'
    	+'</td>'
    	+'<td width="10%" class="bg_eee"><span class="fr btn">'
       	+'<input id="'+sectionCode+'_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$(\'#'+sectionCode+'\'));"/>'
    	+'</span></td>'
  		+'</tr>';
  		currentObj.parent().parent().parent().before(newContent);
  		input_notice_number("newAdd_"+sectionCode,sectionCode);
}
function saveTime(currentObj,tableObj){
	//获取当前textarea
	var timeId = currentObj.attr("id");
	if(timeId.indexOf("newDate")==0){
		if(isSaved_ok==1)
		{
			saveNewContent($("#newAdd_"+newTextid),tableObj);
		}else{
			return;
		}
	}else{
		var oldTextid = timeId.split("_")[0];
		updateContent($("#"+oldTextid+"_content"),tableObj,1);
	}
}
$(document).ready(function(){
	var studentid='${studentid}';
	if(studentid==""||studentid==null)
	{
		alert("该学生不存在");
		document.body.innerHTML="";
		return ;
	}
	var texts = $("body").find("textarea");
	for(var i=0;i<texts.size();i++){
		var textId = $(texts[i]).attr("id");
		if(textId.indexOf("newAdd")==0){
			var sectionCode = textId.split("_")[1];
			input_notice_number(textId,sectionCode);
		}
	}
});
</script>
</head>

<body>
<input type="hidden"  id="sid" name="studentid" value="${studentid }"/>
<input type="hidden" id="fid" name="familymemberid" value="${appraserid }"/>
<input type="hidden" id="pname" name="parentname" value="${parentname }"/>

<div class="dangqianwz">
 	<span class="fl">当前位置：评价孩子->${name}(${levelname }-${grade }-${classname })</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
     <select name="select" id="termId" class="  wenziliebiao100"  onchange="chooseTerm()">
            <app:highSchoolTermTag selectClassid="${classid}" selectNum="${termid}" levelCode="${levelcode1}"/>
    </select> </span>
 </div>

<div id="pj_ziwo_main">
	<c:forEach items="${sections}" var="section" varStatus="pVs">
	 <c:if test="${section.sectionId eq '7030' or section.sectionId eq '94'}">
	    <div class="down mt18 pb21">
	 </c:if>
	 <c:if test="${section.sectionId ne '7030' or section.sectionId ne '94'}">
	    <div class="down mt18">
	 </c:if>
	    <table id="${section.sectionId}" width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		    <tr class="title_bg">
		    <td colspan="3" id="${section.sectionId}_SN">${section.sectionName}
		    <c:if test="${section.sectionId eq '2040' or section.sectionId eq '1020' or section.sectionId eq '23'}"><span class="red">*</span></c:if></td>
	        </tr>
	        <c:if test="${fn:length(section.aInfos)!=0}">
		        <c:forEach items="${section.aInfos}" var="aInfo" varStatus="vs">
				    <tr id="${aInfo.apprasialid}_tpOne">
				      <td id="${aInfo.apprasialid}_order" width="10%" height="40" class="bg_eee">${vs.count}</td>
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：家长&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${aInfo.appraser}<c:if test="${empty aInfo.appraser }">${parentname }</c:if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" />-->
				      	<input id="${aInfo.apprasialid}_Date" type="text" size="10" value="${aInfo.signdate}" idvalue="${aInfo.signdate }"
								onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td> 
				      	
			        </tr>
				    <tr id="${aInfo.apprasialid}_tpTwo">
				      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong " style="background:#eee">
	                    <textarea class="content"
		                id="${aInfo.apprasialid}_content"
		                idValue="${aInfo.apprasial}"
		                style="background: transparent; border:1px solid transparent; height: 130px; width: 100%;"
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));">${aInfo.apprasial }</textarea>
			          </td>
				      <td width="10%" class="bg_eee"><span class="fr btn">
				      <%-- <c:if test="${section.sectionId ne '2030'}"> --%>
			         	<input id="${aInfo.apprasialid}_delete" type="button" class="fr shanchu1" onclick="deleteCountentPage($(this),$('#${section.sectionId}'));"/>
				      <%-- </c:if> --%>
				      </span></td>
			        </tr>
			        	<c:if test="${vs.count==fn:length(section.aInfos)}">
							<tr id="${section.sectionId}_tpThree" <c:if test="${section.sectionId eq '2040' or section.sectionId eq '1020' or section.sectionId eq '23'}"> style="display:none;" </c:if>>
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
				      <td colspan="2" class="youjuzhong pr20 bg_eee" >评价人：家长&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${parentname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	日期：<!-- <input name="text3" type="text" class="wenben80" value="2015-05-05" /> -->
				      	<input id="newDate_${section.sectionId}" type="text" size="10"  value="${date_content}" onClick="WdatePicker()" onchange="saveTime($(this),$('#${section.sectionId}'))"/>
				      </td>
			        </tr>
				    <tr id="${section.sectionId}_tpTwo">
				      <td colspan="2" class="pl20 pr20 pb20 pt10 zuojuzhong">
	                  <textarea class="content"
		                id="newAdd_${section.sectionId}"
		                idValue=""
		                style="background: transparent; border:1px solid transparent; height: 130px; width: 100%; margin:5px;"
		                onblur="save($(this),$('#${section.sectionId}'));" onfocus="changeBack($(this));"></textarea>
			          </td>
				      <td width="10%" class="bg_eee"><span class="fr btn">
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
	</div>
  </c:forEach>
</div>
<%@ include file="/common/div.jsp"%>
</body>
</html>
