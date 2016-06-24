<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/masses.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/jquery.gritter.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<style type="text/css">
#pj_ziwo_main{top:23px;padding:13px;margin-bottom:-8px;}
#pj_ziwo_main .fujian{ color:#ccc; text-align:right; padding-right:20px; width:48%;}
body,html {
	overflow-x:hidden;
	overflow-y:hidden;
	width: 100%;
	height: 100%;
}
.del{ width:42px; height:42px;  background:url(../images/del.png);}
.xgg{ width:42px; height:42px;  background:url(../images/xgg.png);}
.file{ position:absolute; top:0; right:300px; /* filter:alpha(opacity:0);opacity: 0; */ height:27px; width:69px }
.fileadd{ position:absolute; top:0; right:253px; /*  filter:alpha(opacity:0);opacity: 0; */height:27px; width:69px }
.doupload{position:relative;left: 130px;}
.fileadd2{ position:absolute; top:0;left: 10px;  filter:alpha(opacity:0);opacity: 0; height:27px; width:69px }
.wenben86left {
	width: 116px;
	height: 26px;
	line-height: 26px;
	text-align: left;
}
.wenbenyu111{ width:90%; height:120px; line-height:20px; border:1px solid #999; resize:none; font-size:13px;}
.wenbenyu112{ width:90%; height:250px; line-height:20px; border:1px solid #999; resize:none; font-size:13px;}
</style>
</head>
<body>
<%@ include file="/common/div.jsp"%>
	<div class="dangqianwz">
		<span class="fl">当前位置：自我评价->综合实践活动->社会实践活动</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>学期： <select
			name="termId" id="termId" onchange="changeterm()"
			class="wenziliebiao100">
				<app:highSchoolTermTag selectClassid="${classid}"
					selectNum="${termId}" levelCode="${levelcode}" />
		</select>
		</span>
	</div>
	<div id="pj_ziwo_main">
		<div class="down mt18 pb30">
			<c:if test="${empty practicesDtosList}">
			<div id="top">
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table">
					<tr class="title_bg">
						<td colspan="3">社会实践活动内容</td>
					</tr>
					<tr id="new_tr">
						<td width="10%" height="40" id="new_num" class="bg_eee">1</td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
							评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							日期：<input id="new_startDate" type="text" value="${nowDate}" size="10" onClick="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" id="new_td" class="h50" style="width: 90%;">
							<div class="downbox mt18">
                    			<span class="fl wenben86left">选择活动种类：</span>
	                  			<select name="select" class=" fl  wenziliebiao100" id="new_tm" style="background: transparent;">
		             				<option value="社会考察活动">社会考察活动</option>
		           					<option value="社会调查活动">社会调查活动</option>
		           					<option value="社会实践活动">社会实践活动</option>
		           					<option value="其他">其他</option>
	            	  			</select>  
               				</div>
               				<div class="downbox mt18">
					            <span class="fl wenben86left">成果形成：</span>
						            <select name="select" class=" fl  wenziliebiao100" id="new_hhr" style="background: transparent;">
					              	    <option value="个人体会">个人体会</option>
					           		    <option value="考察报告">考察报告</option>
					           	        <option value="个人总结">个人总结</option>
					           		    <option value="调查报告">调查报告</option>
					           		    <option value="其他">其他</option>
	            	  				</select>  
               				</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">地点：</span> <input type="text"
									class="fl wenbenkuang670" id="new_zxss" style="background: transparent;float:left;color:#BCBCBC;cursor:text;" onfocus="turnon120(event)" onblur="turnoff120(event)"  maxlength="120" value="最多输入120字" />
							</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">完成学时：</span> <input type="text"
									class="fl wenbenkuang100" id="new_sslj" style="background: transparent;float:left;color:#BCBCBC;cursor:text;" onfocus="turnon120(event)" onblur="turnoff120(event)"  maxlength="120" value="最多输入120字" />
							</div>

							<div class="downbox mt18">
								<span class="fl wenben86left">主体：</span>
								<textarea id="new_content" class="wenbenyu112" style="background: transparent; float:left;color:#BCBCBC;cursor:text;" onfocus="turnon4000(event)" onblur="turnoff4000(event)">最多输入4000字</textarea>
							</div>
							<div id="new_attach">
								<div class="downbox mt18" id="new_attachment"></div>
            				</div>	
							<div class="downbox mt18" style="position: relative;">
							<form method="post" id="new_frm">
									<span class="fl wenben85left">附件：</span> 
									<!-- <input name='new_textfield' id='new_textfield' type="text"
										class="wenbenkuang670 fujian" style="position:absolute;background: transparent;left:112px;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file"
										name="new_fileField" class="fileadd2" id="new_fileField" size="28" style="margin-left:26px"
										onchange="document.getElementById('new_textfield').value=this.value" 
										/><span id="new_tp" style="margin-right:5px">0%</span>
									<input type="button" value="添 加" class="shangchuan ml10" /> 
									<input type="button" value="上 传" id ="new_upload" style="margin-right:-150px" onclick="checkupload(event)"
										class="shangchuan ml10" /> </span> -->
									<input name='new_fileField' id='new_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="new_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="checkupload(event)"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="new_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
							</form>
							</div>
							<div class="downbox mt18" id="new_textareatd">
								<span class="fl wenben86left" style="background: transparent;">自我评价：</span>
								<textarea class="wenbenyu111" id="new_contentapp" onblur="checksaveapp(event)" onfocus="turnon600(event)" style="background: transparent;float:left;color:#BCBCBC;cursor:text;">最多输入600字</textarea>
								<input type="button" title="删 除" id="new_deleteapp" class="del fl ml10" onclick="deletepracticeappraisal(event)" style="margin-top:45px;margin-left:1px;"/>
								<input type="button" title="添 加" id="new_addapp" style="margin-left:91.15%"  class="xgg fl ml10" onclick="addtextarea(event)"/>
							</div>
						</td>
						<td width="10%" height="191" class="bg_eee" id="bg_eee_4_1">
						<input type="button" class="fr shanchu1" title="删除" id="new_delete" onclick="deleteselfall(event)" style="margin:0px auto;float:none"/>
						<input type="button" class="fr tianjia" title="保存"  style="margin:0px auto;float:none" id="new_save" onclick="check(event)"/>
						</td>
					</tr>
					<tr id="addlocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" class="fr shanchu"  title="增加" style="margin-right :18px;" onclick="addnew()"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
			<c:if test="${not empty practicesDtosList}">
			<div id="top">
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table">
					<tr class="title_bg">
						<td colspan="3">社会实践活动内容</td>
					</tr>
					<%int i=1;%>
					<c:forEach items="${practicesDtosList}" var="list" varStatus="status">
					<tr id="${list.practiceid}_tr">
						<td width="10%" height="40 " id="${list.practiceid}_num" class="bg_eee"><%=i%></td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
							评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							日期：<input id="${list.practiceid}_startDate" type="text" value="${list.item5}" size="10" onClick="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" id="${list.practiceid}_td" class="h50" style="background:#eee;width: 90%;">
							<div class="downbox mt18">
								<span class="fl wenben86left">选择活动种类：</span>
								  <select name="select" class=" fl  wenziliebiao100" id="${list.practiceid}_tm" style="background: transparent;" onclick="changeBackgroundtd('${list.practiceid}')">
				             			<option value="社会考察活动" ${(list.item6 eq "社会考察活动")?"selected":"" }>社会考察活动</option>
				           				<option value="社会调查活动" ${(list.item6 eq "社会调查活动")?"selected":"" }>社会调查活动</option>
				           				<option value="社会实践活动" ${(list.item6 eq "社会实践活动")?"selected":"" }>社会实践活动</option>
				           				<option value="其他" ${(list.item6 eq "其他")?"selected":"" }>其他</option>
				            	  </select>  
							</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">成果形成：</span>
								 <select name="select" class=" fl  wenziliebiao100" id="${list.practiceid}_hhr" style="background: transparent;" onclick="changeBackgroundtd('${list.practiceid}')">
				              		<option value="个人体会" ${(list.item7 eq "个人体会")?"selected":"" }>个人体会</option>
				           			<option value="考察报告" ${(list.item7 eq "考察报告")?"selected":"" }>考察报告</option>
				           			<option value="个人总结" ${(list.item7 eq "个人总结")?"selected":"" }>个人总结</option>
				           			<option value="调查报告" ${(list.item7 eq "调查报告")?"selected":"" }>调查报告</option>
				           			<option value="其他" ${(list.item7 eq "其他")?"selected":"" }>其他</option>
				            	  </select>  
							</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">地点：</span> <input type="text"
									class="fl wenbenkuang670" id="${list.practiceid}_zxss" style="background: transparent;" maxlength="120"  onfocus="turnon120(event)" onblur="turnoff120(event)" 
								 	value="${list.item2}" onclick="changeBackgroundtd('${list.practiceid}')"/>
							</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">完成学时：</span> <input type="text"
									class="fl wenbenkuang100" id="${list.practiceid}_sslj" style="background: transparent;" maxlength="120"  onfocus="turnon120(event)" onblur="turnoff120(event)" 
								 	value="${list.item9}" onclick="changeBackgroundtd('${list.practiceid}')"/>
							</div>
							<div class="downbox mt18">
								<span class="fl wenben86left">主体：</span>
								<textarea id="${list.practiceid}_content" class="wenbenyu112" 
								idvalue="${list.item1}"  onfocus="turnon4000(event)" onblur="turnoff4000(event)"
								onclick="changeBackgroundtd('${list.practiceid}');"
								style="background: transparent;float:left;cursor:text;">${list.item1}</textarea>
							</div>
							<div id="${list.practiceid}_attach">
										<%int j=1;%>
									<c:forEach items="${list.attachListForFile}" var="attachFile">
										<div class="downbox mt18" id="${attachFile.attachid}_attachment">
		            					<span class="fl wenben85left">附件<%=j %>：&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" style="color:black;" onclick="dodown('${attachFile.attachid}','${list.practiceid}')">${attachFile.filename}</a>
		            					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteselfattach('${attachFile.attachid}','${list.practiceid}')"><img src="${ctx}/images/upload_del.gif" style="height:10px; width:10px; "border="0"/></img></a>
		            					</span>
	            						<%j++;%>
	            						</div>
            						</c:forEach>
            					</div>
							<div class="downbox mt18" style="position: relative;">
									<form method="post" id="${list.practiceid}_frm">
									<input type="hidden" name="${list.practiceid}_old_uuid" id="${list.practiceid}_old_uuid" value="${uuid}"/>
									<input type="hidden" name="${list.practiceid}_rpID" id="${list.practiceid}_rpID" value="${list.practiceid}"/>
									<span class="fl wenben85left">附件：</span> 
									<%-- <input name='${list.practiceid}_textfield' id='${list.practiceid}_textfield' type="text"
										class="wenbenkuang670 fujian"  style="position:absolute;background: transparent;left:112px;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file" class="fileadd2" size="28" name="${list.practiceid}_fileField" id="${list.practiceid}_fileField" style="margin-left:175px"
									onchange="document.getElementById('${list.practiceid}_textfield').value=this.value"/><span id="${list.practiceid}_tp" style="margin-left:150px">0%</span> 
									<input type="button" value="添 加" class="shangchuan ml10" style="margin-left:15px" /> 
									<input type="button" value="上 传" id ="${list.practiceid}_upload" onclick="return beforeSubmitForm('old_fileField','${list.practiceid}_frm','${list.practiceid}_old_uuid','${list.practiceid}_tp','${list.practiceid}');"
										class="shangchuan ml10" /> 
									</span> --%>
									<input name='${list.practiceid}_fileField' id='${list.practiceid}_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="${list.practiceid}_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="return beforeSubmitForm('old_fileField','${list.practiceid}_frm','${list.practiceid}_old_uuid','${list.practiceid}_tp','${list.practiceid}');"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="${list.practiceid}_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
									</form>
								</div>
							<div class="downbox mt18"  id="${list.practiceid}_textareatd">
								<span class="fl wenben86left">自我评价：</span>
								<c:if test="${not empty list.practiceappraisalList}">
								<c:forEach items="${list.practiceappraisalList}" var="practiceappraisalList">
									<textarea class="wenbenyu111" style="background: transparent;float:left;cursor:text;" onblur="checksaveapp(event)" onfocus="changeBackgroundtd('${list.practiceid}')"  id="${practiceappraisalList.appraisalid}_contentapp">${practiceappraisalList.content}</textarea>
									<input type="button" title="删 除" class="del fl ml10" id="${practiceappraisalList.appraisalid}_deleteapp" onclick="deletepracticeappraisal(event)" style="margin-top:45px;margin-left:1px;"/>
								</c:forEach>
								</c:if>
								<c:if test="${empty list.practiceappraisalList}">
									<textarea class="wenbenyu111" style="background: transparent;float:left;cursor:text;"  onfocus="changeBackgroundtd('${list.practiceid}')"  onblur="checksaveapp(event)" id="${list.practiceid}_contentapp"></textarea>
									<input type="button" title="删 除" class="del fl ml10" id="${list.practiceid}_deleteapp" onclick="deletepracticeappraisal(event)"/>
								</c:if>
								 <input type="button" title="添 加" id="${list.practiceid}_addapp" style="margin-left:91.15%" class="xgg fl ml10" onclick="addtextarea(event)" style="margin-top:45px;margin-left:1px;"/>
							</div>

						</td>
						<td width="10%" height="191" class="bg_eee" id="bg_eee_4_1" style="margin:0px auto;float:none">
							<input type="button" class="fr shanchu1" style="margin:0px auto;float:none" title="删除" id="${list.practiceid}_delete" onclick="deleteselfall(event)"/> 
							<input type="button" class="fr tianjia" style="margin:0px auto;float:none" title="保存" id="${list.practiceid}_save"  onclick="check(event)"/>
							</td>
					</tr>
					<%i++;%>
					</c:forEach>
					<tr id="addlocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add" title="增加" style="margin-right :18px;" onclick="addnew()" class="fr shanchu" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
		</div>
	</div>
</body>
<script type="text/javascript">
var beforeid="";
var interdelete = 0;
var saveNow = 0;
var isSave = 0;//是否正在保存
var isDelete = 0;//是否保存时点删除
var deletecid ="";
var issaveapp = 0;
var is600app = 0;//自我评价不超过600字
function changeBackground(id)
{
	$("#"+id+"_content").parent().css("background-color","");
}
//新增保存记录袋
function saveall(blur,check,event){//blur为1是点击保存按钮,0是失去焦点保存;check点击上传为1，不点击上传为0
	/* var idd = window.event.srcElement.getAttribute('id'); */
	event = event? event: window.event;
	var obj=event.srcElement ? event.srcElement : event.target;
	var idd=obj.id;
	var arr = new Array();
	arr = idd.split("_");
	id = arr[0];
	var type = "9030"
	var new_zxss = $("#new_zxss").val();
	var new_sslj = $("#new_sslj").val();
	var new_content = $("#new_content").val();
	if($("#new_zxss").val()=="最多输入120字"){new_zxss=""}
	if($("#new_sslj").val()=="最多输入120字"){new_sslj=""}
	if($("#new_content").val()=="最多输入4000字"){new_content=""}
		if(saveNow == 0){
			saveNow =1;
			if(blur=="1"&&check=="0"){
						if($("#new_content").val().length<=4000){
							if($("#new_zxss").val()=="最多输入120字"){$("#new_zxss").val("");}
							if($("#new_sslj").val()=="最多输入120字"){$("#new_sslj").val("");}
							if($("#new_content").val()=="最多输入4000字"){$("#new_content").val("");}
							if($("#new_contentapp").val()=="最多输入600字"){$("#new_contentapp").val("");}
							Ext.Ajax.request({
								url:'${ctx}/selfappraise/PracticesAction.a?insertSelfPractices',
								method:'POST',
								defaults:{autoScroll: true},
								success:function(response,options){
									var temp=response.responseText;
									var saveid = temp;
									if(response.responseText=="##"){
										text_style("new_td");
										alert_save_failure("社会实践活动");
										saveNow = 0;
									}else{
										saveNow = 0;
										if(blur=="0"){
											savePracticeappraisal(saveid)
										}

										$("#new_tm").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_hhr").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_zxss").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_sslj").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_fileField").attr('onclick','');
										$("#new_fileField").attr('onchange','');
										$("#new_upload").attr('onclick','');
										$('#new_content').attr("id",saveid+"_content");
										$('#new_startDate').attr("id",saveid+"_startDate");
										$('#new_td').attr("id",saveid+"_td");
										$('#new_tr').attr("id",saveid+"_tr");
										$('#new_delete').attr("id",saveid+"_delete");
										$('#new_save').attr("id",saveid+"_save");
										$("#new_fileField").attr("id",saveid+"_fileField");
										$("#new_textfield").attr("id",saveid+"_textfield");
										$("#new_upload").attr("id",saveid+"_upload");
										$("#new_tp").attr("id",saveid+"_tp");
										$("#new_attach").attr("id",saveid+"_attach");
										$("#new_frm").attr("id",saveid+"_frm");
										$("#new_uuid").attr("id",saveid+"_uuid");
										$("#new_rpID").attr("id",saveid+"_rpID");
										$("#new_tm").attr("id",saveid+"_tm");
										$("#new_hhr").attr("id",saveid+"_hhr");
										$("#new_zxss").attr("id",saveid+"_zxss");
										$("#new_sslj").attr("id",saveid+"_sslj");
										$("#new_textareatd").attr("id",saveid+"_textareatd");
										$("#new_addapp").attr("id",saveid+"_addapp");
										$("#new_num").attr("id",saveid+"_num");
										$("#new_contentapp").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										if($("#new_contentapp").val()!='undefinded'&&$("#new_contentapp").val()!=null){
											$("#new_contentapp").attr("id",saveid+"_contentapp");
										}
										if($("#new_deleteapp").val()!='undefinded'&&$("#new_deleteapp").val()!=null){
											$("#new_deleteapp").attr("id",saveid+"_deleteapp");
										} 
										 $("#"+saveid+"_upload").bind("click",function(){
											return beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
										});
										$("#"+saveid+"_fileField").bind("change",function(){
											document.getElementById(saveid+"_textfield").value=document.getElementById(saveid+"_fileField").value
										});
										if(blur=="1"){
											if(check!="0"){
												if($("#"+saveid+"_content").val()==""){
													$("#"+saveid+"_content").val("最多输入4000字")
													$("#"+saveid+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"});
												}
												if($("#"+saveid+"_zxss").val()==""){
													$("#"+saveid+"_zxss").val("最多输入120字")
													$("#"+saveid+"_zxss").css({color:"#BCBCBC",float:"left",cursor:"text"});
												}
												if($("#"+saveid+"_sslj").val()==""){
													$("#"+saveid+"_sslj").val("最多输入120字")
													$("#"+saveid+"_sslj").css({color:"#BCBCBC",float:"left",cursor:"text"});
												}
												beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);//执行上传
											}
											if(check!="1"){
												
												$("#"+saveid+"_content").bind("focus",function(){
													changeBackgroundtd(saveid);
												});
												$("#"+saveid+"_tm").bind("focus",function(){
													changeBackgroundtd(saveid);
												});
												$("#"+saveid+"_hhr").bind("focus",function(){
													changeBackgroundtd(saveid);
												});
												$("#"+saveid+"_zxss").bind("focus",function(){
													changeBackgroundtd(saveid);
												});
												$("#"+saveid+"_sslj").bind("focus",function(){
													changeBackgroundtd(saveid);
												});
												document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
												alert_save_success("社会实践活动",$("#"+saveid+"_num").html());
											}
										}else{
											
										}
										saveNow = 0;
									}
								},
								params : {
									item1 : new_content,
									item2 : new_zxss,
									item5 : $("#new_startDate").val(),
									item6 : $("#new_tm").val(),
									item7 : $("#new_hhr").val(),
									item9 : new_sslj,
									evaluateType : type,
									termId : document.getElementById("termId").value
								}
							}); 
						}else{
							apprasial_alert(4000);
							saveNow = 0;
						}
				 	
			}else{
				if((blur=="0"&&$("#new_contentapp").val()!=""&&$("#new_contentapp").val()!="最多输入600字")||blur!="0"){
					if($("#new_content").val().length<=4000){
						Ext.Ajax.request({
							url:'${ctx}/selfappraise/PracticesAction.a?insertSelfPractices',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								var temp=response.responseText;
								var saveid = temp;
								if(response.responseText=="##"){
									text_style("new_td");
									alert_save_failure("社会实践活动");
									saveNow = 0;
								}else{
									saveNow = 0;
									if(blur=="0"){
										savePracticeappraisal(saveid)
									}
									$("#new_tm").bind("focus",function(){
										changeBackgroundtd(saveid);
									});
									$("#new_hhr").bind("focus",function(){
										changeBackgroundtd(saveid);
									});
									$("#new_zxss").bind("focus",function(){
										changeBackgroundtd(saveid);
									});
									$("#new_sslj").bind("focus",function(){
										changeBackgroundtd(saveid);
									});
									$("#new_fileField").attr('onclick','');
									$("#new_fileField").attr('onchange','');
									$("#new_upload").attr('onclick','');
									$('#new_content').attr("id",saveid+"_content");
									$('#new_startDate').attr("id",saveid+"_startDate");
									$('#new_td').attr("id",saveid+"_td");
									$('#new_tr').attr("id",saveid+"_tr");
									$('#new_delete').attr("id",saveid+"_delete");
									$('#new_save').attr("id",saveid+"_save");
									$("#new_fileField").attr("id",saveid+"_fileField");
									$("#new_textfield").attr("id",saveid+"_textfield");
									$("#new_upload").attr("id",saveid+"_upload");
									$("#new_tp").attr("id",saveid+"_tp");
									$("#new_attach").attr("id",saveid+"_attach");
									$("#new_frm").attr("id",saveid+"_frm");
									$("#new_uuid").attr("id",saveid+"_uuid");
									$("#new_rpID").attr("id",saveid+"_rpID");
									$("#new_tm").attr("id",saveid+"_tm");
									$("#new_hhr").attr("id",saveid+"_hhr");
									$("#new_zxss").attr("id",saveid+"_zxss");
									$("#new_sslj").attr("id",saveid+"_sslj");
									$("#new_textareatd").attr("id",saveid+"_textareatd");
									$("#new_addapp").attr("id",saveid+"_addapp");
									$("#new_num").attr("id",saveid+"_num");
									$("#new_contentapp").bind("focus",function(){
										changeBackgroundtd(saveid);
									});
									if($("#new_contentapp").val()!='undefinded'&&$("#new_contentapp").val()!=null){
										$("#new_contentapp").attr("id",saveid+"_contentapp");
									}
									if($("#new_deleteapp").val()!='undefinded'&&$("#new_deleteapp").val()!=null){
										$("#new_deleteapp").attr("id",saveid+"_deleteapp");
									} 
									 $("#"+saveid+"_upload").bind("click",function(){
										return beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
									});
									$("#"+saveid+"_fileField").bind("change",function(){
										document.getElementById(saveid+"_textfield").value=document.getElementById(saveid+"_fileField").value
									});
									if(blur=="1"){
										if(check!="0"){
											
											if($("#"+saveid+"_content").val()==""){
												$("#"+saveid+"_content").val("最多输入4000字")
												$("#"+saveid+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"});
											}
											beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);//执行上传
										}
										if(check!="1"){
											$("#"+saveid+"_content").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_tm").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_hhr").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_zxss").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_sslj").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
											alert_save_success("社会实践活动",$("#"+saveid+"_num").html());
										}
									}else{
										
									}
									saveNow = 0;
								}
							},
							params : {
								item1 : new_content,
								item2 : new_zxss,
								item5 : $("#new_startDate").val(),
								item6 : $("#new_tm").val(),
								item7 : $("#new_hhr").val(),
								item9 : new_sslj,
								evaluateType : type,
								termId : document.getElementById("termId").value
							}
						}); 
					}else{
						saveNow = 0;
						apprasial_alert(4000);
					}
				}else{
					if($("#new_contentapp").val()==""){
						$("#new_contentapp").val("最多输入600字")
						$("#new_contentapp").css({color:"#BCBCBC",float:"left",cursor:"text"});
					}
					saveNow = 0;
				}
			}
			
		}else{
			/* if(check!=1){
				alert("正在保存中...");
			} */
		}
}

function updateapp(id,id1){
	if($("#"+id+"_contentapp").val()=="最多输入600字"){$("#new_contentapp").val("");}
	/* if($("#"+id+"_contentapp").val().trim().length!=0){ */
		if($("#"+id+"_contentapp").val().length<=600){
			Ext.Ajax.request({
				url:'${ctx}/selfappraise/PracticesAction.a?doUpdatePracticeappraisal',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					var temp=response.responseText;
					if(temp=="##"){
						alert_update_failure("社会实践活动");	
						text_style(id+"_contentapp");
					}else{
					}
					is600app = 0;
				},
				params : {
					appraisalid : id,
					practiceid : id1,
					updateType : "9030",
					content : $("#"+id+"_contentapp").val()
				}
			});
		}else{
			is600app = 1;
			apprasial_alert(600);
		}
	/* } */
	
}

function updatePractices(id){
	if(is600app==0){
		if($("#"+id+"_content").val().length<=4000){
			if($("#"+id+"_zxss").val()=="最多输入120字"){$("#"+id+"_zxss").val("");}
			if($("#"+id+"_sslj").val()=="最多输入120字"){$("#"+id+"_sslj").val("");}
			if($("#"+id+"_content").val()=="最多输入4000字"){$("#"+id+"_content").val("");}
			if($("#new_contentapp").val()=="最多输入600字"){$("#new_contentapp").val("");}
			if($("#"+id+"_contentapp").val()=="最多输入600字"){$("#"+id+"_contentapp").val("");}
			Ext.Ajax.request({
				url:'${ctx}/selfappraise/PracticesAction.a?doUpdatePractices',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					var temp=response.responseText;
					if(temp=="##"){
						alert_update_failure("社会实践活动");	
						text_style(id+"_td");
						alert($(id+"_td").val())
					}else{
						document.getElementById(id+"_td").style.backgroundColor ="#eee";
						alert_save_success("社会实践活动",$("#"+id+"_num").html());
					}
				},
				params : {
					id : id,
					item1 : $("#"+id+"_content").val(),
					item2 : $("#"+id+"_zxss").val(),
					item5 : $("#"+id+"_startDate").val(),
					item6 : $("#"+id+"_tm").val(),
					item7 : $("#"+id+"_hhr").val(),
					item9 : $("#"+id+"_sslj").val(),
					termId : document.getElementById("termId").value,
					updateType : "9030"
				}
			});
		}else{
			apprasial_alert(4000);
		}
	}else{
		alert("自我评价已超600字");
	}
}

function savePracticeappraisal(id){
	if($("#new_contentapp").val()!='undefind'&&$("#new_contentapp").val()!=null){
		if($("#new_contentapp").val().trim().length!=0){
			if($("#new_contentapp").val().length<=600){
				if(issaveapp==0){
					if($("#new_contentapp").val()=="最多输入600字"){$("#new_contentapp").val("");}
					issaveapp = 1;
					Ext.Ajax.request({
						url:'${ctx}/selfappraise/PracticesAction.a?insertSelfPracticeappraisal',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							var temp=response.responseText;
							var saveid = temp;
							if(response.responseText=="##"){
								text_style("new_contentapp");
								alert_save_failure("社会实践活动");
								issaveapp = 0;
							}else{
								$('#new_contentapp').attr("id",saveid+"_contentapp");
								$('#new_deleteapp').attr("id",saveid+"_deleteapp");
								$("#"+saveid+"_contentapp").bind("focus",function(){
									changeBackgroundtd(id);
								});
								issaveapp = 0;
							}
							is600app = 0;
							issaveapp = 0;
						},
						params : {
							practiceid : id,
							apptype : "9030001",
							attType : "9030",
							content : $("#new_contentapp").val()
						}
					});
				}
			}else{
				is600app = 1;
				issaveapp = 0;
				apprasial_alert(600);
			}
		}else{
			issaveapp = 0;
			$("#new_contentapp").val("最多输入600字");
			$("#new_contentapp").css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
		}
	}else if($("#"+id+"_contentapp").val()!='undefind'&&$("#"+id+"_contentapp").val()!=null){
		if($("#"+id+"_contentapp").val().trim().length!=0){
			if($("#"+id+"_contentapp").val().length<=600){
				if(issaveapp==0){
					if($("#"+id+"_contentapp").val()=="最多输入600字"){$("#"+id+"_contentapp").val("");}
					issaveapp = 1;
					Ext.Ajax.request({
						url:'${ctx}/selfappraise/PracticesAction.a?insertSelfPracticeappraisal',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							var temp=response.responseText;
							var saveid = temp;
							if(response.responseText=="##"){
								text_style("#"+id+"_contentapp");
								alert_save_failure("研究性学习");
								issaveapp = 0;
							}else{
								$("#"+id+"_contentapp").attr("id",saveid+"_contentapp");
								$("#"+id+"_deleteapp").attr("id",saveid+"_deleteapp");
								$("#"+saveid+"_contentapp").bind("focus",function(){
									changeBackgroundtd(id);
								});
								issaveapp = 0;
							}
							is600app = 0;
							issaveapp = 0;
						},
						params : {
							practiceid : id,
							apptype : "9030001",
							attType : "9030",
							content : $("#"+id+"_contentapp").val()
						}
					});
				}
			}else{
				is600app = 1;
				issaveapp = 0;
				apprasial_alert(600);
			}
		}else{
			issaveapp = 0;
			$("#"+id+"_contentapp").val("最多输入600字");
			$("#"+id+"_contentapp").css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
		}
	}
}
function checksaveapp(event){//保存自我评价
	ii=0;
	var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
	var arr = new Array();
	arr = idd.split("_");
	id = arr[0];
	var arr1 = new Array();
	var idd1 = $("#"+idd).parent().attr("id");
	arr1 = idd1.split("_");
	id1 = arr1[0];
	if(id1=="new"){
		if(id=="new"){
			saveall("0","0",event)
		}else{
			updateapp(id,id1);
		}
	}else{
		if(id=="new"||id==id1){
			savePracticeappraisal(id1);
		}else{
			updateapp(id,id1);
		}
	}
	
}
function check(event){
	var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.getAttribute('id');
	var arr = new Array();
	arr = id.split("_");
	var idi = arr[0];
	if(idi=="new"){
		saveall("1","0",event)
	}else{
		updatePractices(idi);
	}
}
function checkupload(event){
	if(document.getElementById("new_fileField").value!=""){
		var paths = new Array();
		paths = $("#new_fileField").val().split("\\");
		var path_name = new Array();
		path_name = paths[paths.length-1].split(".");
		var pname = path_name[0];
		if(pname.length<=25){	
			saveall("1","1",event);
		}else{
			alert("上传文件名不能超过25个字!");
		}
	}else{
		alert("请选择要上传的文件！");
	}
}
/*
*上传下载部分js
*
*/
//生成uuid方法
function generateUUID() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4";
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
	s[8] = s[13] = s[18] = s[23] = "-";
	return s.join("");
}
var interval;  
/**
 * 附件的上传
 */
function beforeSubmitForm(name,formid,uui,load,id){
	 var Sys = {};    
	 var ua = navigator.userAgent.toLowerCase();    
	 var s;    
	 (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1]:
	 (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	 (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 0;
	 
	 if (Sys.ie){
	 		if(Sys.ie<7.0){
				alert('请使用IE9~IE11浏览器上传附件，否则无法上传');
				return;
	 		}else{
	 			 beforeid = id+"_fileField";
	 			 $("#"+beforeid).attr("name","old_fileField");
	 			 $("#"+beforeid).attr("id","old_fileField");
	 			 $("#"+id+"_upload").attr("disabled",true);
	 			var arr = new Array();
	 			var isrepeat = 0;
	 			var filetype = document.getElementById(name).value;
	 			arr = filetype.split(".");
	 			var ftype = arr[arr.length-1];
	 				if(document.getElementById(name).value!=""){
	 					var paths = new Array();
	 					paths = filetype.split("\\");
	 					var path_name = new Array();
	 					path_name = paths[paths.length-1].split(".");
	 					var pname = path_name[0];
	 					if(pname.length<=25){
	 						if((ftype!=null&&ftype!="")&&(ftype=="doc"||ftype=="docx"||ftype=="txt"||ftype=="jpg"||ftype=="pdf"||ftype=="pps"||ftype=="ppsx"||ftype=="ppt"||ftype=="pptx")){
	 							var a = $("#"+id+"_attach").find("a");
	 							for(var i=0;i<a.size();i++){
	 					    		var tdId = $(a[i]).text(); 
	 					    		if(paths[paths.length-1]==tdId){
	 					    			isrepeat =1;
	 					    		}
	 					    	} 
	 							if(isrepeat!=1){
	 								var uuid = generateUUID();
	 								var bar = document.getElementById(load);
	 								var type = "9030";
	 								var ajax;
	 									 $.ajaxFileUpload({
	 											url : '${nginxServer}/upload?X-Progress-ID=' + uuid+'&uuid='+uuid, // 上传文件的服务器地址
	 											sercureuri : false,  
	 											fileElementId : name, // 文件选择框的id属性  
	 											dataType : 'json'  // 服务器返回的格式
	 										});
	 									 interval = window.setInterval(function() {
	 											ajax = $.ajax({ 
	 												type : 'get',
	 												async : false,
	 												url: '${ctx}/upload/UploadAction.a?getUploadFileProgress&X-Progress-ID=' + uuid+'&'+new Date().getTime(), 
	 												dataType: 'text',
	 												success: function(data){
	 													var json = eval(data);
	 									                // 上传完毕后清除定时器
	 													// 更新进度信息
	 													if((json.state == "uploading"&&json.size<1024000)||json.state == "done"||json.state == "starting"){
	 								  		            	if(json.state == "uploading"){
	 								  		                	var w =  Math.floor(json.received * 100.0 / json.size);  
	 								  		                	bar.innerHTML = w + "%";
	 								  		            	} 
	 										             	if (json.state == 'done') {  
	 										             		bar.innerHTML = "100%";  
	 										                 	window.clearTimeout(interval);  
	 										                 	Ext.Ajax.request({
	 										    					url:'${ctx}/selfappraise/PracticesAction.a?saveFile',
	 										    					method:'POST',
	 										    					defaults:{autoScroll: true},
	 										    					success:function(response,options){
	 										    						var temp=response.responseText;
	 										    						if(temp=="##"){
	 										    							alert_save_failure("附件上传");
	 										    							$("#"+id+"_upload").attr("disabled",false);
	 										    						}else{
	 										    							var len=eval(temp);
	 											    						document.getElementById(id+"_attach").innerHTML = "";
	 											    						var temp=response.responseText;
	 											    					    var len=eval(temp);
	 											    					    var str="";
	 											    						for(var i=0;i<len[0].list2.length;++i)
	 											    						{
	 											    							if(len[0].list2[i].practiceid == id){
	 											    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
	 											    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;" 
	 											    									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].practiceid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
	 											    									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].practiceid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
	 											    									+"</span></div>"
	 												    							}
	 											    							}
	 											    						}
	 											    						document.getElementById(id+"_attach").innerHTML = str;
	 											    						alert_upload_success("上传已完成");
	 											    						bar.innerHTML = "0%"; 
	 											    						//document.getElementById(id+"_textfield").value = "格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx";
	 											    						 $("#"+id+"_fileField").attr('onclick','');
	 																			$("#"+id+"_fileField").attr('onchange','');
	 																			$("#"+id+"_fileField").bind("change",function(){
	 																				document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
	 																			});
	 																			$("#"+id+"_upload").attr("disabled",false);
	 										    						}
	 										    					},
	 										    					params : {
	 										    						uuid : uuid,
	 										    						rpID : id,
	 										    						evaluateType : type,
	 										    						termId : document.getElementById("termId").value
	 										    					}
	 										    				}); 
	 										                 	$('#old_fileField').attr("id",beforeid);
	 										            	}
	 													}else{
	 														if(json.state != "error"){
	 															alert("附件大小不能超过1M");
	 							  		            		 	$("#"+id+"_fileField").attr('onclick','');
	 															$("#"+id+"_fileField").attr('onchange','');
	 															$("#"+id+"_fileField").bind("change",function(){
	 																document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
	 															});
	 							  		            			$('#old_fileField').attr("id",beforeid);
	 							  		            			bar.innerHTML = "0%";
	 							  		            			window.clearTimeout(interval);  
	 							  		            			$("#"+id+"_fileField").attr('onclick','');
	 															$("#"+id+"_fileField").attr('onchange','');
	 															$("#"+id+"_fileField").bind("change",function(){
	 																document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
	 															});
	 															if($('#old_fileField')!=null&&$('#old_fileField')!=""&&$('#old_fileField')!=undefined){
	 																$('#old_fileField').attr("id",beforeid);
	 															}
	 														}else{
	 															window.clearTimeout(interval); 
	 															alert_upload_failure("上传失败");
	 														}
	 														$("#"+id+"_upload").attr("disabled",false);
	 						  		            		}
	 										    	}
	 											});
	 										}, 200);
	 							}else{
	 							alert("社会实践活动 栏目"+$("#"+id+"_num").html()+": 附件名重复!");	
	 							$("#"+id+"_upload").attr("disabled",false);
	 							$('#old_fileField').attr("id",beforeid);
	 							}
	 						}else{
	 							alert("上传文件格式不正确");
	 							$("#"+id+"_upload").attr("disabled",false);
	 							$('#old_fileField').attr("id",beforeid);
	 						}
	 					}else{
	 						alert("上传文件名不能超过25个字!");
	 						$("#"+id+"_upload").attr("disabled",false);
	 						$('#old_fileField').attr("id",beforeid);
	 					}
	 				}else{
	 					alert("请选择要上传的文件！");
	 					$("#"+id+"_upload").attr("disabled",false);
	 					$('#old_fileField').attr("id",beforeid);
	 				}
	 	 	}
	 	}else{
			alert('请使用IE9~IE11浏览器上传附件，否则无法上传');
			return;
	 	}
}

function deleteselfattach(id,rid){
	  var r=apprasial_delete()
	  if (r==true){
		  var type="9030";
		  Ext.Ajax.request({
				url:'${ctx}/selfappraise/PracticesAction.a?deleteSelfAttach',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					if(response.responseText=="1"){
						alert_delete_failure("附件")
					}else{
						alert_delete_success("附件")
						document.getElementById(rid+"_attach").innerHTML = "";
						var temp=response.responseText;
					    var len=eval(temp);
					    var str="";
						for(var i=0;i<len[0].list2.length;++i)
						{
							if(len[0].list2[i].practiceid == rid){
								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;" 
									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].practiceid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].practiceid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
									+"</span></div>"
  								}
							}
						}
						document.getElementById(rid+"_attach").innerHTML = str;
					}
				},
				params : {
					id : id,
					attachtypeid : rid,
					termId : document.getElementById("termId").value,
					evaluateType : type
				}
			}); 
	  }
	  else{
		  return false;
	  }
}

function deleteselfall(event){
	/* var id = window.event.srcElement.getAttribute('id'); */
	var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.getAttribute('id');
	var arr = new Array();
	arr = id.split("_");
	var idd = arr[0];
	  var r=apprasial_delete()
	  if (r==true){
		  if(saveNow==1){
			/*   alert("正在保存中..."); */
		  }else{
			  if(idd=="new"){
					var tr = $("#new_table").find("tr");
			    	if(tr.size()!=4){
			    		if(document.getElementById("new_tr")!=null||document.getElementById("new_tr")!=undefined){
			    			$("#new_tr").next().remove();    
							$("#new_tr").remove();
						}
			    	}else{
			    		apprasial_del_Finish();
			    	}
				}else{
					 Ext.Ajax.request({
							url:'${ctx}/selfappraise/PracticesAction.a?deleteSelfPractices',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								if(response.responseText=="1"){
									text_style(idd+"_td");
									alert_delete_failure("社会实践活动");
								}else{
									var tr = $("#new_table").find("tr");
									if(tr.size()==4){
										$("#"+idd+"_tr").next().remove();    
										$("#"+idd+"_tr").remove();
										 var str="";
										 str+="<tr id='new_tr'><td width='10%' height='40' id='new_num' class='bg_eee'>"+"1"+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
										 	+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
										 	+"日期：<input id='new_startDate' type='text' value='${nowDate}' size='10' onClick='WdatePicker()'/>"
										 	+"</td></tr><tr><td colspan='2' id='new_td' class='h50' style='width: 90%;'>"
										 	+"<div class='downbox mt18'><span class='fl wenben86left'>选择活动种类：</span>"
			                    			+"<select name='select' class=' fl  wenziliebiao100' id='new_tm' style='background: transparent;'>"
			                    			+"<option value='社会考察活动'>社会考察活动</option><option value='社会调查活动'>社会调查活动</option><option value='社会实践活动'>社会实践活动</option><option value='其他'>其他</option>"
			                    			+"</select></div>"
			                    			+"<div class='downbox mt18'><span class='fl wenben86left'>成果形成：</span>"
			                    			+"<select name='select' class=' fl  wenziliebiao100' id='new_hhr' style='background: transparent;'>"
			                    			+"<option value='个人体会'>个人体会</option><option value='考察报告'>考察报告</option><option value='个人总结'>个人总结</option><option value='调查报告'>调查报告</option><option value='其他'>其他</option>"
				            	  			+"</select></div>"
										 	+"<div class='downbox mt18'><span class='fl wenben86left'>地点：</span> <input type='text' class='fl wenbenkuang670' id='new_zxss' style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onfocus='turnon120(event)' onblur='turnoff120(event)'  maxlength='120' value='最多输入120字'/></div>"
										 	+"<div class='downbox mt18'><span class='fl wenben86left'>完成学时：</span> <input type='text' class='fl wenbenkuang100' id='new_sslj' style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onfocus='turnon120(event)' onblur='turnoff120(event)'  maxlength='120' value='最多输入120字'/></div>"
										 	+"<div class='downbox mt18'><span class='fl wenben86left'>主体：</span><textarea id='new_content' class='wenbenyu112' style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon4000(event)' onblur='turnoff4000(event)'>最多输入4000字</textarea></div>"
										 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
										 	+"<div class='downbox mt18' style='position: relative;'><form method='post' id='new_frm'>"
										 	+"<span class='fl wenben85left'>附件：</span> "
										 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
										 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload(event)' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
										 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
										 	+"</form></div><div class='downbox mt18' id='new_textareatd'>"
										 	+"<span class='fl wenben86left' >自我评价：</span><textarea class='wenbenyu111' id='new_contentapp' onblur='checksaveapp(event)' style='background: transparent;float:left;color:#BCBCBC;cursor:text;'  onfocus='turnon600(event)'>最多输入600字</textarea>"
										 	+"<input type='button' title='删 除' id='new_deleteapp' class='del fl ml10' onclick='deletepracticeappraisal(event)' style='margin-top:45px;margin-left:1px;'/>"
										 	+"<input type='button' title='添 加' style='margin-left:91.15%' id='new_addapp' class='xgg fl ml10'  onclick='addtextarea(event)'/></div>"
										 	+"</td><td height='191' class='bg_eee'>"
										 	+"<input type='button' class='fr shanchu1' title='删除' id='new_delete' onclick='deleteselfall(event)' style='margin:0px auto;float:none'/>"
										 	+"<input type='button' class='fr tianjia' title='保存'  style='margin:0px auto;float:none' id='new_save' onclick='check(event)'/>"
										 	+"</td></tr>"
										 $("#addlocation").before(str);
									}else{
										 $("#"+idd+"_tr").next().remove();    
											$("#"+idd+"_tr").remove(); 
											//重新对评价排序
											var td = $("#new_table").find("td");
									    	var orderNum=0;
									    	 for(var i=0;i<td.size();i++){
									    		var tdId = $(td[i]).attr("id"); 
									    		if(tdId.indexOf("_num")>0){
									    			$(td[i]).html(++orderNum);
									    		}
									    	} 
									}
									alert_delete_success("社会实践活动")
								}
							},
							params : {
								id : idd,
								termId : document.getElementById("termId").value,
								deleteType : "9030"
							}
						}); 
				}
		  }
	  }
	  else{
		  return false;
	  }
}
function deletepracticeappraisal(event){
	ii=0;
	/* var idd = window.event.srcElement.getAttribute('id'); */
	var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
	var arr = new Array();
	arr = idd.split("_");
	id = arr[0];
	var textarea = $("#"+idd).parent().find("textarea");
	var farr = new Array();
	var fid;
	farr = $("#"+idd).parent().attr('id').split("_");
	fid = farr[0];
	 var r=apprasial_delete()
	  if (r==true){
		  if(id=="new"){	
			  if(textarea.size()!=1){
				  $("#new_contentapp").remove();
				  $("#new_deleteapp").remove();
			  }
		  }else{
			  Ext.Ajax.request({
					url:'${ctx}/selfappraise/PracticesAction.a?deleteSelfPracticeappraisal',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						if(response.responseText=="1"){
							alert_delete_failure("自我评价")
						}else{
							if(textarea.size()==1){
								$("#"+id+"_contentapp").attr("id",fid+"_contentapp");
								$("#"+id+"_deleteapp").attr("id",fid+"_deleteapp");
								$("#"+fid+"_contentapp").val("最多输入600字");
								$("#"+fid+"_contentapp").css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
								alert_delete_success("自我评价");
							}else{
								$("#"+id+"_contentapp").remove();
								$("#"+id+"_deleteapp").remove();
								alert_delete_success("自我评价");
							}
						}
					},
					params : {
						appraisalid : id,
						practiceid : fid,
						deleteType : "9030001"
					}
				}); 
		  }
	  }
	  else{
		  return false;
	  }
}

//下载附件
function dodown(attachid,foreignKey)
{
	var url="${ctx}/DownloadAttachAction.a?attachid="+attachid+"&&foreignKey="+foreignKey;
	document.location.replace(url);
    return false;
}

	function addnew(){
		var tdWidth = $("#bg_eee_4_1").attr("width");
		if(document.getElementById("new_tr")==undefined||document.getElementById("new_tr")==null){
			var div = document.getElementById("top");  
		var tr = div.getElementsByTagName("tr").length;
		var tr_num=Math.floor(tr/2);
		 var str="";
		 str+="<tr id='new_tr'><td width='10%' height='40' id='new_num' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
		 	+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		 	+"日期：<input id='new_startDate' type='text' value='${nowDate}' size='10' onClick='WdatePicker()'/>"
		 	+"</td></tr><tr><td colspan='2' id='new_td' class='h50' style='width: 90%;'>"
		 	+"<div class='downbox mt18'><span class='fl wenben86left'>选择活动种类：</span>"
			+"<select name='select' class=' fl  wenziliebiao100' id='new_tm' style='background: transparent;'>"
			+"<option value='社会考察活动'>社会考察活动</option><option value='社会调查活动'>社会调查活动</option><option value='社会实践活动'>社会实践活动</option><option value='其他'>其他</option>"
			+"</select></div>"
			+"<div class='downbox mt18'><span class='fl wenben86left'>成果形成：</span>"
			+"<select name='select' class=' fl  wenziliebiao100' id='new_hhr' style='background: transparent;'>"
			+"<option value='个人体会'>个人体会</option><option value='考察报告'>考察报告</option><option value='个人总结'>个人总结</option><option value='调查报告'>调查报告</option><option value='其他'>其他</option>"
  			+"</select></div>"
		 	+"<div class='downbox mt18'><span class='fl wenben86left'>地点：</span> <input type='text' class='fl wenbenkuang670' id='new_zxss' style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onfocus='turnon120(event)' onblur='turnoff120(event)' maxlength='120' value='最多输入120字'/></div>"
		 	+"<div class='downbox mt18'><span class='fl wenben86left'>完成学时：</span> <input type='text' class='fl wenbenkuang100' id='new_sslj' style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onfocus='turnon120(event)' onblur='turnoff120(event)' maxlength='120' value='最多输入120字'/></div>"
		 	+"<div class='downbox mt18'><span class='fl wenben86left'>主体：</span><textarea id='new_content' class='wenbenyu112' style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon4000(event)' onblur='turnoff4000(event)'>最多输入4000字</textarea></div>"
		 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
		 	+"<div class='downbox mt18' style='position: relative;'><form method='post' id='new_frm'>"
		 	+"<span class='fl wenben85left'>附件：</span> "
		 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
		 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload(event)' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
		 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
		 	+"</form></div><div class='downbox mt18' id='new_textareatd'>"
		 	+"<span class='fl wenben86left'>自我评价：</span><textarea class='wenbenyu111' id='new_contentapp' onblur='checksaveapp(event)' style='background: transparent;float:left;color:#BCBCBC;cursor:text;'  onfocus='turnon600(event)'>最多输入600字</textarea>"
		 	+"<input type='button' title='删 除'  id='new_deleteapp' class='del fl ml10' onclick='deletepracticeappraisal(event)'style='margin-top:45px;margin-left:1px;'/>"
		 	+"<input type='button' title='添 加' style='margin-left:91.15%' id='new_addapp' class='xgg fl ml10'  onclick='addtextarea(event)'/></div>"
		 	+"</td><td width='"+tdWidth+"' height='191' class='bg_eee'>"
		 	+"<input type='button' class='fr shanchu1' title='删除' id='new_delete' onclick='deleteselfall(event)' style='margin:0px auto;float:none'/>"
		 	+"<input type='button' class='fr tianjia' title='保存'  style='margin:0px auto;float:none' id='new_save' onclick='check(event)'/>"
		 	+"</td></tr>"
		 $("#addlocation").before(str);
		}
	} 
	
/* 	function addtextarea(event){
		var event = event || window.event;
		var obj=event.srcElement ? event.srcElement : event.target;
		var idd=obj.getAttribute('id');
		var fid = $("#"+idd).parent().attr('id');
		var arr = new Array();
		arr = fid.split("_");
		id = arr[0];
		var text = $("#"+idd).parent().find("textarea");
		var num=0;
		for(var i=0;i<text.size();i++){
    		var tdId = $(text[i]).attr("id"); 
    		if(tdId.indexOf("w_contentapp")>0 || $("#"+tdId).val() == ""){
    			num=1; 
    		}
		}
		if(num==0){
			var str=""
				str+="<textarea class='wenbenyu111' id='new_contentapp' onblur='checksaveapp(event)' onfocus='changeBackgroundtd(\""+id+"\")'  style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onblur='turnoff600(event)'>最多输入600字</textarea>"
				   +"<input type='button' title='删 除' id='new_deleteapp' class='del fl ml10' onclick='deletepracticeappraisal(event)' style='margin-top:45px;margin-left:1px;'/>"
				$("#"+id+"_addapp").before(str);
		}else{
			if(issaveapp==1){
				 alert("正在保存中");
			}
		}
	}  */
	
	var ii=0;
	function addtextarea(event){
		
		 var event = event || window.event;
		var obj=event.srcElement ? event.srcElement : event.target;
		var idd=obj.getAttribute('id');
		var fid = $("#"+idd).parent().attr('id');
		var arr = new Array();
		arr = fid.split("_");
		id = arr[0];
		if("new"==id){
			return;
		}
		var text = $("#"+idd).parent().find("textarea"); 
		setTimeout(function () { 
			if($("#new_contentapp").length>0){
				alert("请先填写完未填写的自我评价");
				return;
			}else{ 
				if(0==ii){
					ii=1;
					var str=""
						str+="<textarea onfocus='changeBackgroundtd(\""+id+"\")' class='wenbenyu111' id='new_contentapp' onblur='checksaveapp(event)'   style='background: transparent;float:left;color:#BCBCBC;cursor:text;' onblur='turnoff600(event)'>最多输入600字</textarea>"
						   +"<input type='button' title='删除' id='new_deleteapp' class='del fl ml10' onclick='deletepracticeappraisal(event)' style='margin-top:45px;margin-left:1px;'/>"
						$("#"+id+"_addapp").before(str);
			  }else{
				  alert("请先填写完未填写的自我评价");
			   }
		}
	 }, 100);
} 
//更改学期刷新页面
		function changeterm(){
			ShowDiv();
			var term = document.getElementById("termId").value;
			var choicenum = 1008;
			var evaluateType = 9030;
			url="${ctx}/selfappraise/SelfAppAction.a?choicenum="+choicenum+"&termId1="+term+"&evaluateType="+evaluateType;
			document.location.replace(url);
		}
		
		function changeBackgroundtd(id)
		{
				document.getElementById(id+"_td").style.backgroundColor ="";
			if($("#"+id+"_contentapp").val()=="最多输入600字"){
				$("#"+id+"_contentapp").removeAttr("style"); 
				$("#"+id+"_contentapp").css({background:"transparent",color:"black",float:"left",cursor:"text"});
				$("#"+id+"_contentapp").val("");
			}
			if($("#new_contentapp").val()=="最多输入600字"){
				$("#new_contentapp").removeAttr("style"); 
				$("#new_contentapp").css({background:"transparent",color:"black",float:"left",cursor:"text"});
				$("#new_contentapp").val("");
			}
			if($("#"+id+"_content").val() !="最多输入4000字"){
				$("#"+id+"_content").css({background:"transparent",color:"black",float:"left",cursor:"text"});
			}
			
			if($("#new_contentapp").val()!=undefined&&$("#new_contentapp").val()!=null){
				if((!$("#new_contentapp").is(":focus"))&&$("#"+id+"_content").val() ==""){
					$("#new_contentapp").val("最多输入600字");
				 	$("#new_contentapp").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
			}
		
			if((!$("#"+id+"_zxss").is(":focus"))&&$("#"+id+"_zxss").val() ==""){
				$("#"+id+"_zxss").val("最多输入120字");
			 	$("#"+id+"_zxss").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if((!$("#"+id+"_sslj").is(":focus"))&&$("#"+id+"_sslj").val() ==""){
				$("#"+id+"_sslj").val("最多输入120字");
			 	$("#"+id+"_sslj").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if((!$("#"+id+"_content").is(":focus"))&&$("#"+id+"_content").val() ==""){
				$("#"+id+"_content").val("最多输入4000字");
			 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if((!$("#"+id+"_contentapp").is(":focus"))&&$("#"+id+"_contentapp").val() ==""){
				$("#"+id+"_contentapp").val("最多输入600字");
			 	$("#"+id+"_contentapp").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if($("#"+id+"_contentapp").is(":focus")){
				if($("#"+id+"_contentapp").val!='undefinded'&&$("#"+id+"_contentapp").val()!=null){
					if($("#"+id+"_contentapp").val()=="最多输入600字"){
						$("#"+id+"_contentapp").css({background:"transparent",color:"black",float:"left",cursor:"text"});
						$("#"+id+"_contentapp").val("");
					}
				}
			}
		}
		
		
		function turnon120(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			$("#"+idd).removeAttr("style"); 
			$("#"+idd).css({background:"transparent"});
			if($("#"+idd).val()=="最多输入120字"){
				$("#"+idd).val("");
			}
		}
		function turnoff120(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			if ($("#"+idd).val() ==""){
				$("#"+idd).val("最多输入120字");
				$("#"+idd).css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
			}
		}	
		function turnon4000(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			if($("#"+idd).val()=="最多输入4000字"){
				$("#"+idd).removeAttr("style"); 
				$("#"+idd).css({background:"transparent",float:"left",cursor:"text"});
				$("#"+idd).val("");
			}
		}
		function turnoff4000(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			if ($("#"+idd).val() ==""){
				$("#"+idd).val("最多输入4000字");
				$("#"+idd).css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
			}
		}	
		function turnon600(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			$("#"+idd).removeAttr("style"); 
			$("#"+idd).css({background:"transparent",float:"left",cursor:"text"});
			if($("#"+idd).val()=="最多输入600字"){
				$("#"+idd).val("");
			}
		}
		function turnoff600(event){
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var idd=obj.getAttribute('id');
			if ($("#"+idd).val() ==""){
				$("#"+idd).val("最多输入600字");
				$("#"+idd).css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
			}
		}		
		

		  window.onbeforeunload = onbeforeunload_handler;   
		  function onbeforeunload_handler(){
			 	var i = 0;
		    	$("textarea").each(function(){
					if($(this).css('borderLeftWidth')=="2px"){
						i=1;
					}
				});
		    	$("td ").each(function(){
					if($(this).css('borderLeftWidth')=="2px"){
						i=1;
					}
				});
		    	if(i==1){
		    		var warning=window_close();
			        return warning; 
		    	} 
		    }  
		  $(window).load(function() {
				 var thisMession=document.getElementById("mession");
			
				 thisMession.innerHTML="";
				 thisMession.innerHTML=mess;  
				  });

</script>
</html>