<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/masses.jsp"%>
<un:bind var="ACTIVITY_BASEINFO_2" type="com.flyrish.hades.common.Constant" field="ACTIVITY_BASEINFO_2" />
<un:bind var="ACTIVITY_SELF_APPRAISAL_2" type="com.flyrish.hades.common.Constant" field="ACTIVITY_SELF_APPRAISAL_2" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我评价</title>
</head>
<style>
#pj_ziwo_main{top:38px;padding:13px;margin-bottom:-8px;}
#pj_ziwo_main .fujian{ color:#ccc; text-align:right; padding-right:20px; width:48%;}
body,html {
	overflow-x:hidden;
	overflow-y:hidden;
	width: 100%;
	height: 100%;
}
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
<input type="hidden" name="evaluateType1" id="evaluateType1" value="${ACTIVITY_SELF_APPRAISAL_2}"> 
<input type="hidden" name="evaluateType2" id="evaluateType2" value="${ACTIVITY_BASEINFO_2}"> 
<input type="hidden" name="levelcode" id="levelcode" value="${levelcode}"> 
	<div class="dangqianwz">
		<span class="fl">当前位置：自我评价->综合实践活动->社区服务与社会实践活动</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span>学期：
	    	<select name="termId" id="termId" onchange="changeterm()"  class="wenziliebiao100">
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
						<td colspan="3">自我评价&nbsp;<span class="red">*</span></td>
					</tr>
					<tr id="new_tr">  
						<td width="10%" height="40" id="new_num1" class="bg_eee">1</td>
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
						 <td width="100" class="bg_eee"><span class="fr btn" style="margin:0px auto;float:none">
	      				 	<input type="button" class="fr shanchu1" style="margin:0px auto;float:none" title="删除" id="new_delete" onclick="deleteselfapp(this)"/>
	      				</span></td>
					</tr>
					<tr id="new_addlocation" style="display:none">
					    <td colspan="3" class="bg_eee h80">
				            <div class="fr btn">
				               <input type="button"  style="margin-right :20px;" id ="add" title="添加" class="fr shanchu" onclick="addnew('new_addlocation','new_top')"/>
				            </div>
				         </td>
					</tr>
				</table>
			</div>
			<div id="old_top" <c:if test="${empty appraisalList1}">style='display:none'</c:if>>
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table1">
					<tr class="title_bg">
						<td colspan="3">自我评价&nbsp;<span class="red">*</span></td>
					</tr>
					<c:forEach items="${appraisalList1}" var="list" varStatus="status">
					<tr id="${list.part_id}_tr">
					  	<td width="10%" height="40" class="bg_eee" id="${list.part_id}_num"><%=n%></td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
						评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						日期：<input id="${list.part_id}_startDate" type="text"  value="${list.createDate}" size="10" onchange="doupdate1('${list.part_id}')" onClick="WdatePicker()"/> 
						</td>
					</tr>
					<tr>
						<td colspan="2" id="${list.part_id}_td" class="pl20 pr20 pb20 pt10 zuojuzhong " style="background:#eee;">
				 			 <textarea class="content"
						                id="${list.part_id}_content"
						                style="background: transparent; overflow:auto;  border: 1px solid transparent; height: 130px; width: 100%; "
						                idvalue="${list.part_info}"
						                onblur="doupdate('${list.part_id}')"
						                onfocus="changeback(this)">${list.part_info}</textarea>
						</td>
						<td width="100" class="bg_eee">
							<span class="fr btn" style="margin:0px auto;float:none"> 
							<input type="button" style="margin:0px auto;float:none" class="fr shanchu1" id="${list.part_id}_delete" title="删除" onclick="deleteselfapp(this)"/>
	      					</span>
	     			 	</td>
					</tr>
					<%n++;%>
					</c:forEach>
					<tr id="old_addlocation">
					    <td colspan="3" class="bg_eee h80">
				            <div class="fr btn">
				               <input type="button"  style="margin-right :20px;"  id ="add" title="添加" class="fr shanchu" onclick="addnew('old_addlocation','old_top')"/>
				            </div>
				         </td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 记录袋 -->
		<div id="down" class="down mt18 pb30">
			<c:if test="${empty appraisalList2}">
			<div id="new_down" >
				<table width="100%" id="new_table2" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
						<td colspan="3">基本情况</td>
					</tr>
					<tr id="new_tr2">
						<td width="10%" height="40" id="new_num2" class="bg_eee">1</td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
							评价人：本人 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							日期：<input id="new_startDate2" type="text" value="${nowDate}" size="10" onClick="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="h50" id ="new_td2" style="width: 90%;">
							<div class="downbox mt18">
								<span class="fl wenben85left">主题：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang670" maxlength="25" id="new_topic2" style="background: transparent;float:left;color:#BCBCBC;cursor:text;" onfocus="turnon1('new_topic2',this)" onblur="turnoff1('new_topic2',this)" value="最多输入25字" />
							</div>
							<div class="downbox mt18">
                    		<span class="fl wenben85left">地点：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang670" maxlength="30" id="new_address2" style="background: transparent;float:left;color:#BCBCBC;cursor:text;" onfocus="turnon2('new_address2',this)" onblur="turnoff2('new_address2',this)" value="最多输入30字" />
               				 </div>
               				 <div class="downbox mt18">
								<span class="fl wenben85left">起止日期：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang100" value="${nowDate}" id="new_startdate" style="background: transparent;" onClick="WdatePicker()"/> 
								<span class="fl" style="margin-top:4px">&nbsp;&nbsp;至&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang100" value="${nowDate}" id="new_enddate" style="background: transparent;" onClick="WdatePicker()"/>
							</div>
							<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
								<textarea id="new_content2" style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;" onfocus="turnon('new_content2',this)" onblur="turnoff('new_content2',this)">最多输入600字</textarea>
							</div>
							<div id="new_attach">
								<div class="downbox mt18" id="new_attachment2">
	            			</div>
            				</div>	
							<div class="downbox mt18" style="position: relative;">
							<form method="post" id="new_frm">
									<input type="hidden" name="new_uuid" id="new_uuid"/>
									<input type="hidden" name="new_rpID" id="new_rpID"/>
									<span class="fl wenben85left">附件：</span> 
									<!-- <input name='new_textfield' id='new_textfield' type="text"
										class="wenbenkuang670 fujian" style="position:absolute;background: transparent;left:80px;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file"
										name="new_fileField" class="fileadd2" id="new_fileField" size="28" style="margin-left:28px"
										onchange="document.getElementById('new_textfield').value=this.value"
										/><span id="new_tp" style="margin-right:8px">0%</span>  
									<input type="button" value="添 加" class="shangchuan ml10" /> 
									<input type="button" value="上 传" id ="new_upload" style="margin-right:-100px" onclick="checkupload(this)"
										class="shangchuan ml10" /> </span> -->
									<input name='new_fileField' id='new_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="new_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="checkupload(this)"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="new_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
							</form>
							</div>
						</td>
						<td width="10%" height="191" class="bg_eee" id="bg_eee_3_1" style="margin:0px auto;float:none">
						<input type="button" class="fr shanchu1"  style="margin:0px auto;float:none" title="删除" id="new_delete2" onclick="deleteselfpackage(this)"/>
						<input type="button" class="fr tianjia"  style="margin:0px auto;float:none" title="保存" id="new_save" onclick="check('down','new_startDate2','new_content2','new_rpID','new_topic2',this)"/></td>
					</tr>
					<tr id="addpackagelocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" style="margin-right :18px;" onclick="addnew1()" class="fr shanchu" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
			<c:if test="${not empty appraisalList2}">
			<div id="new_down" >
				<table width="100%"  id="new_table2" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
						<td colspan="3">基本情况</td>
					</tr>
					<%int i=1;%>
					<c:forEach items="${appraisalList2}" var="list" varStatus="status">
						<tr id="${list.part_id}_tr">
							<td width="10%" height="40 " class="bg_eee" id="${list.part_id}_num"><%=i%></td>
							   <td colspan="2" class="youjuzhong pr20 bg_eee" >
							   		评价人：本人 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        			签名：${evaluatePersonName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        			日期：<input id="${list.part_id}_startDate" type="text" value="${list.createDate}" size="10" onClick="WdatePicker()"/> 
							   </td>
						</tr>
						<tr>
							<td colspan="2" id="${list.part_id}_td" style="background:#eee;width: 90%;">
							<div class="downbox mt18">
								<span class="fl wenben85left">主题：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" style="background: transparent;" maxlength="25" class="fl wenbenkuang670" id="${list.part_id}_topic" value="${list.topic}"  onfocus="turnon1('${list.part_id}',this)" onblur="turnoff1('${list.part_id}',this)"/>
							</div>
							<div class="downbox mt18">
                    		<span class="fl wenben85left">地点：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" style="background: transparent;" maxlength="30" class="fl wenbenkuang670" id="${list.part_id}_address" value="${list.address}"  onfocus="turnon2('${list.part_id}',this)" onblur="turnoff2('${list.part_id}',this)"/>
               				</div>
               				<div class="downbox mt18">
								<span class="fl wenben85left">起止日期：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang100" value="${list.startdate}" id="${list.part_id}_startdate" style="background: transparent;" onClick="WdatePicker()" onfocus="changeBackgroundtd('${list.part_id}')"/> 
								<span class="fl" style="margin-top:4px">&nbsp;&nbsp;至&nbsp;&nbsp;</span> 
								<input type="text" class="fl wenbenkuang100" value="${list.enddate}" id="${list.part_id}_enddate" style="background: transparent;" onClick="WdatePicker()" onfocus="changeBackgroundtd('${list.part_id}')"/>
							</div>
								<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
				 					 <textarea class="content"
						                id="${list.part_id}_content"
						                style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999;"
						                idvalue="${list.part_info}"
						                onclick="changeBackgroundtd('${list.part_id}');"
						                 onfocus="turnon('${list.part_id}',this)" onblur="turnoff('${list.part_id}',this)"
						                >${list.part_info}</textarea>
								</div>
								<div id="${list.part_id}_attach">
										<%int j=1;%>
									<c:forEach items="${list.attachListForFile}" var="attachFile">
										<div class="downbox mt18" id="${attachFile.attachment_id}_attachment">
		            					<span class="fl wenben85left">附件<%=j %>：&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" style="color:black;" onclick="dodown('${attachFile.attachment_id}','${list.part_id}')">${attachFile.attachment_name}</a>
			            				&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" title="删除"  onclick="deleteselfattach('${attachFile.attachment_id}','${list.part_id}')"><img src="${ctx}/images/upload_del.gif"  style="height:11px; width:11px; /* padding-top:6px; vertical-align:top*/"  border="0"/></img></a></span>
			            					<%-- 	<input type="button" value="下 载" class="shangchuan fl ml10"  onclick="dodown('${attachFile.attachment_id}')"/> 
												<input type="button" value="删 除" class="shangchuan fl ml10" onclick="deleteselfattach('${attachFile.attachment_id}','${list.part_id}')"/> --%>
	            						<%j++;%>
	            						</div>
            						</c:forEach>
            					</div>	
								<div class="downbox mt18" style="position: relative;">
									<form method="post" id="${list.part_id}_frm">
									<input type="hidden" name="${list.part_id}_old_uuid" id="${list.part_id}_old_uuid" value="${uuid}"/>
									<input type="hidden" name="${list.part_id}_rpID" id="${list.part_id}_rpID" value="${list.part_id}"/>
									<span class="fl wenben85left">附件：</span> 
									<%-- <input name='${list.part_id}_textfield' id='${list.part_id}_textfield' type="text"
										class="wenbenkuang670 fujian"  style="position:absolute;left:80px;background: transparent;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file" class="fileadd2" size="28" name="${list.part_id}_fileField" id="${list.part_id}_fileField" style="margin-left:130px"
									onchange="document.getElementById('${list.part_id}_textfield').value=this.value" /><span id="${list.part_id}_tp" style="margin-left:100px">0%</span>  
									<input type="button" value="添 加"  style="margin-left:20px" class="shangchuan ml10" /> 
									<input type="button" value="上 传" id ="${list.part_id}_upload" onclick="return beforeSubmitForm('old_fileField','${list.part_id}_frm','${list.part_id}_old_uuid','${list.part_id}_tp','${list.part_id}');"
										class="shangchuan ml10" /> 
									</span> --%>
									<input name='${list.part_id}_fileField' id='${list.part_id}_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="${list.part_id}_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="return beforeSubmitForm('old_fileField','${list.part_id}_frm','${list.part_id}_old_uuid','${list.part_id}_tp','${list.part_id}');"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="${list.part_id}_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
									</form>
								</div>
							</td>
							<td width="10%" height="191" id="bg_eee_3_1" class="bg_eee" style="margin:0px auto;float:none">
							<input type="button" class="fr shanchu1" style="margin:0px auto;float:none" title="删除" id="${list.part_id}_delete2" onclick="deleteselfpackage(this)"/> 
							<input type="button" class="fr tianjia" style="margin:0px auto;float:none" title="保存" id="${list.part_id}_save"  onclick="updateold('${list.part_id}')"/>
							</td>
						</tr>
						<%i++;%>
					</c:forEach>
					<tr id="addpackagelocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" style="margin-right :18px;" onclick="addnew1()" class="fr shanchu" />
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
	//改成可写并更新数据
	var content_div_full = "";
	var beforeid="";
	var interdelete = 0;
	var saveNow = 0;
	var isSave = 0;
	var isDelete = 0;
	var deletecid ="";
	
	function changeBackground(id)
	{
		$("#"+id+"_content").parent().css("background-color","");
	}
		//新增保存记录袋
		 function savepackage(div,day,valuenum,rid,zhuti,check){
			if(saveNow == 0){
				saveNow = 1;
				var this_div = document.getElementById(div);
				var type1 = "1";
				var type2 = "1";
				type2 = document.getElementById("evaluateType2").value;
				var name = "基本情况"
				if(document.getElementById(zhuti).value.replace(/(^\s*)|(\s*$)/g,'') != ""&&document.getElementById(zhuti).value!="最多输入25字"){
					if($('#new_startdate').val()<=$('#new_enddate').val()){
						if(document.getElementById(valuenum).value.length<=600){
							if(document.getElementById(zhuti).value=="最多输入25字"){
								document.getElementById(zhuti).value = "";
							}
							if($("#new_address2").val()=="最多输入30字"){
								$("#new_address2").val("");
							}
							if(document.getElementById(valuenum).value=="最多输入600字"){
								document.getElementById(valuenum).value = "";
							}
							Ext.Ajax.request({
								url:'${ctx}/partinfo/PartinfoAction.a?doInsertSelfAppZh',
								method:'POST',
								defaults:{autoScroll: true},
								success:function(response,options){
									var temp=response.responseText;
									var saveid = temp;
									if(response.responseText=="##"){
										text_style("new_content2");
										alert_save_failure(name);
										saveNow = 0;
									}else{
										saveNow = 0;
										$("#new_startdate").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_enddate").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										$("#new_topic2").removeAttr("style"); 
										$("#new_topic2").css({background:"transparent",float:"left",cursor:"text"});
										$("#new_address2").removeAttr("style"); 
										$("#new_address2").css({background:"transparent",float:"left",cursor:"text"});
										
										$("#new_fileField").attr('onclick','');
										$("#new_fileField").attr('onchange','');
										$("#new_upload").attr('onclick','');
										$('#new_content2').attr("id",saveid+"_content");
										$('#new_startDate2').attr("id",saveid+"_startDate");
										$('#new_num2').attr("id",saveid+"_num");
										$('#new_td2').attr("id",saveid+"_td");
										$('#new_tr2').attr("id",saveid+"_tr");
										$('#new_delete2').attr("id",saveid+"_delete");
										$('#new_save').attr("id",saveid+"_save");
										$('#new_topic2').attr("id",saveid+"_topic");
										$("#new_fileField").attr("id",saveid+"_fileField");
										$("#new_textfield").attr("id",saveid+"_textfield");
										$("#new_address2").attr("id",saveid+"_address");
										$("#new_upload").attr("id",saveid+"_upload");
										$("#new_tp").attr("id",saveid+"_tp");
										$("#new_attach").attr("id",saveid+"_attach");
										$("#new_frm").attr("id",saveid+"_frm");
										$("#new_uuid").attr("id",saveid+"_uuid");
										$("#new_rpID").attr("id",saveid+"_rpID");
										$('#new_startdate').attr("id",saveid+"_startdate");
										$('#new_enddate').attr("id",saveid+"_enddate");
										$("#"+saveid+"_fileField").attr('onclick','');
										$("#"+saveid+"_fileField").attr('onchange','');
										$("#"+saveid+"_upload").attr('onclick','');
										 $("#"+saveid+"_upload").bind("click",function(){
											return beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
										});
										$("#"+saveid+"_fileField").bind("change",function(){
											document.getElementById(saveid+"_textfield").value=document.getElementById(saveid+"_fileField").value
										});
										if(check!="0"){
											if($("#"+saveid+"_content").val()==""){
											$("#"+saveid+"_content").val("最多输入600字");
											$("#"+saveid+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"});
											}
											beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
										}
										if(check!="1"){
											if($("#"+saveid+"_content").val()=="最多输入600字"){
												$("#"+saveid+"_content").val("");
											}
											$("#"+saveid+"_topic").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_content").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_address").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_startdate").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											$("#"+saveid+"_enddate").bind("focus",function(){
												changeBackgroundtd(saveid);
											});
											/* $("#"+saveid+"_topic").bind("blur",function(){
												returnBackgroundtd(saveid);
											});
											$("#"+saveid+"_content").bind("blur",function(){
												returnBackgroundtd(saveid);
											}); */
											document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
											alert_save_success(name,$("#"+saveid+"_num").html());
										}
										saveNow = 0;
									}
								},
								params : {
									part_info : document.getElementById(valuenum).value,
									evaluateType1 : type1,
									evaluateType2 : type2,
									address : $("#new_address2").val(),
									termId : document.getElementById("termId").value,
									startdate : $('#new_startdate').val(),
									enddate : $('#new_enddate').val(),
									CreateDate : document.getElementById(day).value,
									topic : document.getElementById(zhuti).value
								}
							}); 
						}else{
							saveNow = 0;
							apprasial_alert(600);
						}
					}else{
						saveNow = 0;
						alert("起始日期不能大于截止日期!");
					}
				}else{
					saveNow = 0;
					alert("主题不能为空");
				}
			}else{
				/* if(check!=1){
				alert("正在保存中...");
				} */
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
							var isrepeat = 0;
						var arr = new Array();
						var filetype = document.getElementById(name).value;
						arr = filetype.split(".");
						var ftype = arr[arr.length-1];
						if(document.getElementById(id+"_topic").value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
							if(document.getElementById(id+"_topic").value.length<=25){
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
											type2 = document.getElementById("evaluateType2").value;
											type1 = "1";
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
													    					url:'${ctx}/partinfo/PartinfoAction.a?saveFile',
													    					method:'POST',
													    					defaults:{autoScroll: true},
													    					success:function(response,options){
													    						if(response.responseText=="##"){
													    							alert_upload_failure("上传失败");
													    							$("#"+id+"_upload").attr("disabled",false);
																				}else{
																					document.getElementById(id+"_attach").innerHTML = "";
														    						var temp=response.responseText;
														    					    var len=eval(temp);
														    					    var str="";
														    						for(var i=0;i<len[0].list2.length;++i)
														    						{
														    							if(len[0].list2[i].part_id == id){
														    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
														    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachment_id+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;"
														    										+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'>"+len[0].list2[i].attachListForFile[j].attachment_name+"</a>&nbsp;"
														    										+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
														    										+"</span></div>"
														    									/* 	+ len[0].list2[i].attachListForFile[j].attachment_name
														    									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\")'/>"
														    									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'/></div>"; */
															    							}
														    							}
														    						}
														    						document.getElementById(id+"_attach").innerHTML = str;
														    						alert_upload_success("上传已完成");
														    						$("#"+id+"_upload").attr("disabled",false);
																				}
														    						bar.innerHTML = "0%"; 
														    						//document.getElementById(id+"_textfield").value = "格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx";
														    						 $("#"+id+"_fileField").attr('onclick','');
																						$("#"+id+"_fileField").attr('onchange','');
																						/* $("#"+id+"_upload").attr('onclick',''); */
																						$("#"+id+"_fileField").bind("change",function(){
																							document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
																						});
																						$("#"+id+"_upload").attr("disabled",false);
													    					},
													    					params : {
													    						uuid : uuid,
													    						rpID : id,
													    						evaluateType1 : type1,
													    						evaluateType2 : type2,
													    						attType : type2,
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
																		/* $("#"+id+"_upload").attr('onclick',''); */
																		$("#"+id+"_fileField").bind("change",function(){
																			document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
																		});
										  		            			$('#old_fileField').attr("id",beforeid);
										  		            			bar.innerHTML = "0%";
										  		            			window.clearTimeout(interval);  
										  		            			$("#"+id+"_fileField").attr('onclick','');
																		$("#"+id+"_fileField").attr('onchange','');
																		/* $("#"+id+"_upload").attr('onclick',''); */
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
										alert("基本情况 栏目"+$("#"+id+"_num").html()+": 附件名重复!");	
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
						}else{
							apprasial_alert(25);
							$("#"+id+"_upload").attr("disabled",false);
							$('#old_fileField').attr("id",beforeid);
						}
						}else{
							alert("主题不能为空");
							$("#"+id+"_upload").attr("disabled",false);
							$('#old_fileField').attr("id",beforeid);
						}
			 	 	}
			 	}else{
					alert('请使用IE9~IE11浏览器上传附件，否则无法上传');
					return;
			 	}
		}
		
		//下载附件
		function dodown(attachment_id,foreignKey)
		{
			var url="${ctx}/DownloadAttachmentAction.a?attachment_id="+attachment_id+"&&foreignKey="+foreignKey;
			document.location.replace(url);
		    return false;
		}
		function giveid(id){
			beforeid = id;
		/* document.getElementById(id).setAttribute("name","old_fileField");
		document.getElementById(id).setAttribute("id","old_fileField"); */
		}
		 
		function deleteselfpackage(obj){
			var width = $("#bg_eee_3_1").attr("width");
			var type = document.getElementById("evaluateType2").value;
			var name = "基本情况"
			/* var id = window.event.srcElement.getAttribute('id'); */
			/* event = event? event: window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.id; */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idd = arr[0];
			  var r=apprasial_delete();
			  if (r==true){
				  if(saveNow==1){
					/*   alert("正在保存中..."); */
				  }else{
					  var type1 = "1";
					  var type2 = "1";
					  type2 = document.getElementById("evaluateType2").value;
					  if(idd=="new"){
							var tr = $("#new_table2").find("tr");
					    	if(tr.size()!=4){
					    		if(document.getElementById("new_tr2")!=null||document.getElementById("new_tr2")!=undefined){
									$("#new_tr2").next().remove();    
									$("#new_tr2").remove();
								}
					    	}else{
					    		apprasial_del_Finish();
					    	}
						}else{
							 Ext.Ajax.request({
									url:'${ctx}/partinfo/PartinfoAction.a?deleteAttachwith',
									method:'POST',
									defaults:{autoScroll: true},
									success:function(response,options){
										if(response.responseText=="1"){
											text_style(idd+"_content");
											alert_delete_failure(name);
										}else{
											var tr = $("#new_table2").find("tr");
											if(tr.size()==4){
												$("#"+idd+"_tr").next().remove();    
												$("#"+idd+"_tr").remove();
												 var str="";
												 str+="<tr id='new_tr2'><td width='10%' height='40' id='new_num2' class='bg_eee'>1</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
												 	+"评价人：本人 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													+"日期：<input id='new_startDate2' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
													+"</td></tr><tr><td colspan='2' id='new_td2' class='h50' style='width: 90%;'><div class='downbox mt18'>"
												 	+"<span class='fl wenben85left'>主题：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang670' maxlength='25' id='new_topic2'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon1(\""+"new_topic2"+"\",this)' onblur='turnoff1(\""+"new_topic2"+"\",this)' value='最多输入25字' /></div>"
												 	+"<div class='downbox mt18'><span class='fl wenben85left'>地点：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang670' maxlength='30' id='new_address2'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon2(\""+"new_address2"+"\",this)' onblur='turnoff2(\""+"new_address2"+"\",this)' value='最多输入30字' /></div>"
												 	+"<div class='downbox mt18'><span class='fl wenben86left'>起止日期：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang100' value='${nowDate}' id='new_startdate' style='background: transparent;' onClick='WdatePicker()'/> "
													+"<span class='fl' style='margin-top:4px'>&nbsp;&nbsp;至&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang100' value='${nowDate}' id='new_enddate'  style='background: transparent;' onClick='WdatePicker()'/></div>"
												 	+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content2' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content2"+"\",this)' onblur='turnoff(\""+"new_content2"+"\",this)'>最多输入600字</textarea></div>"
												 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
												 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
												 	+"<input type='hidden' name='new_uuid' id='new_uuid'/><input type='hidden' name='new_rpID' id='new_rpID'/>"
												 	+"<span class='fl wenben85left'>附件：</span>"
												 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
												 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload(this)' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
												 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
												 	+"</form></div></td><td width='"+width+"' height='191' id='bg_eee_3_1' class='bg_eee' style='margin:0px auto;float:none' >"
												 	+"<input type='button' class='fr shanchu1' title='删除' id='new_delete2' onclick='deleteselfpackage(this)' style='margin:0px auto;float:none'/> "
												 	+"<input type='button' class='fr tianjia' title='保存' id='new_save' style='margin:0px auto;float:none' onclick='check(\""+"down"+"\",\""+"new_startDate2"+"\",\""+"new_content2"+"\",\""+"new_rpID"+"\",\""+"new_topic2"+"\",this)'/>"
												 	+"</td></tr>"
												 $("#addpackagelocation").before(str);
											}else{
												 $("#"+idd+"_tr").next().remove();    
													$("#"+idd+"_tr").remove(); 
													//重新对评价排序
													var td = $("#new_table2").find("td");
											    	var orderNum=0;
											    	 for(var i=0;i<td.size();i++){
											    		var tdId = $(td[i]).attr("id"); 
											    		if(tdId.indexOf("_num")>0){
											    			$(td[i]).html(++orderNum);
											    		}
											    	} 
											}
											alert_delete_success(name)
										}
									},
									params : {
										evaluateType : type,
										termId : document.getElementById("termId").value,
										id : idd
									}
								}); 
						}
				  }
			  }
			  else{
				  return false;
			  }
		}
		
		function deleteselfattach(id,rid){
			  var r=apprasial_delete();
			  if (r==true){
				  var type1 = "1";
					var type2 = "1";
						type2 = document.getElementById("evaluateType2").value;
				  Ext.Ajax.request({
						url:'${ctx}/partinfo/PartinfoAction.a?deleteAttachment',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							if(response.responseText=="1"){
								alert_delete_failure("附件");	
							}else{
								alert_delete_success("附件")
								document.getElementById(rid+"_attach").innerHTML = "";
	    						var temp=response.responseText;
	    					    var len=eval(temp);
	    					    var str="";
	    						for(var i=0;i<len[0].list2.length;++i)
	    						{
	    							if(len[0].list2[i].part_id == rid){
	    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
	    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachment_id+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;" 
	    									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'>"+len[0].list2[i].attachListForFile[j].attachment_name+"</a>&nbsp;"
	    									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px; padding-top:6px; vertical-align:top' border='0'/></img></a>"
    										+"</span></div>"
	    									/* + len[0].list2[i].attachListForFile[j].attachment_name
	    									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\")'/>"
	    									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'/></div>"; */
		    							}
	    							}
	    						}
	    						document.getElementById(rid+"_attach").innerHTML = str;
							}
						},
						params : {
							id : id,
							part_id : rid,
							evaluateType1 : type1,
							evaluateType2 : type2,
							attType : type2,
							termId : document.getElementById("termId").value
						}
					}); 
			  }
			  else{
				  return false;
			  }
		}
		
		function newdeleteselfattach(id,rid,rrid){
			  var r=apprasial_delete();
			  if (r==true){
				  var type1 = "1";
					var type2 = "1";
						type2 = document.getElementById("evaluateType2").value;
				  Ext.Ajax.request({
						url:'${ctx}/partinfo/PartinfoAction.a?deleteAttachment',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							if(response.responseText=="1"){
								alert_delete_failure("附件");	
							}else{
								alert_delete_success("附件")
								document.getElementById(rrid).innerHTML = "";
	    						var temp=response.responseText;
	    					    var len=eval(temp);
	    					    var str="";
	    						for(var i=0;i<len[0].list2.length;++i)
	    						{
	    							if(len[0].list2[i].part_id == rid){
	    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
	    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachment_id+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;"
	    									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'>"+len[0].list2[i].attachListForFile[j].attachment_name+"</a>&nbsp;"
	    									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px; padding-top:6px; vertical-align:top' border='0'/></img></a>"
    										+"</span></div>"
	    									/* + len[0].list2[i].attachListForFile[j].attachment_name
	    									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\")'/>"
	    									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachment_id+"\",\""+len[0].list2[i].part_id+"\")'/></div>"; */
		    							}
	    							}
	    							
	    						}
	    						document.getElementById(rrid).innerHTML = str;
							}
						},
						params : {
							id : id,
							evaluateType1 : type1,
							evaluateType2 : type2,
							attType : type2,
							termId : document.getElementById("termId").value
						}
					}); 
			  }
			  else{
				  return false;
			  }
		}
		
	 	function addnew1(){
	 		var width = $("#bg_eee_3_1").attr("width");
	 		if(document.getElementById("new_tr2")==undefined||document.getElementById("new_tr2")==null){
	 			var div = document.getElementById("new_down");  
				var tr = div.getElementsByTagName("tr").length;
				var tr_num=Math.floor(tr/2);
				 var str="";
				 str+="<tr id='new_tr2'><td width='10%' height='40' id='new_num2' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
				 	+"评价人：本人 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"日期：<input id='new_startDate2' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
					+"</td></tr><tr><td colspan='2' id='new_td2' class='h50' style='width: 90%;'><div class='downbox mt18'>"
				 	+"<span class='fl wenben85left'>主题：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang670' id='new_topic2' maxlength='25' style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon1(\""+"new_topic2"+"\",this)' onblur='turnoff1(\""+"new_topic2"+"\",this)' value='最多输入25字' /></div>"
					+"<div class='downbox mt18'><span class='fl wenben85left'>地点：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang670' maxlength='30' id='new_address2'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon2(\""+"new_address2"+"\",this)' onblur='turnoff2(\""+"new_address2"+"\",this)' value='最多输入30字' /></div>"
					+"<div class='downbox mt18'><span class='fl wenben86left'>起止日期：&nbsp;&nbsp;&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang100' value='${nowDate}' id='new_startdate' style='background: transparent;' onClick='WdatePicker()'/> "
					+"<span class='fl' style='margin-top:4px'>&nbsp;&nbsp;至&nbsp;&nbsp;</span><input type='text' class='fl wenbenkuang100' value='${nowDate}' id='new_enddate'  style='background: transparent;' onClick='WdatePicker()'/></div>"
					+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content2' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content2"+"\",this)' onblur='turnoff(\""+"new_content2"+"\",this)'>最多输入600字</textarea></div>"
				 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
				 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
				 	+"<input type='hidden' name='new_uuid' id='new_uuid'/><input type='hidden' name='new_rpID' id='new_rpID'/>"
				 	+"<span class='fl wenben85left'>附件：</span>"
				 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
				 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload(this)' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
				 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
				 	+"</form></div></td><td width='"+width+"' id='bg_eee_3_1' height='191' class='bg_eee' style='margin:0px auto;float:none'>"
				 	+"<input type='button' class='fr shanchu1' title='删除' id='new_delete2' onclick='deleteselfpackage(this)' style='margin:0px auto;float:none'/> "
				 	+"<input type='button' class='fr tianjia' title='保存' id='new_save' style='margin:0px auto;float:none' onclick='check(\""+"down"+"\",\""+"new_startDate2"+"\",\""+"new_content2"+"\",\""+"new_rpID"+"\",\""+"new_topic2"+"\",this)'/>"
				 	+"</td></tr>"
				 $("#addpackagelocation").before(str);
	 		}
		} 
		function check(div,day,valuenum,rid,zhuti,obj){
			/* var id = window.event.srcElement.getAttribute('id'); */
				/* event = event? event: window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.id; */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idi = arr[0];
			var name = "基本情况"
			if(idi!="new"){
				var type1 = "1";
				var type2 = document.getElementById("evaluateType2").value;
				var type = document.getElementById("evaluateType2").value;
					if(document.getElementById(idi+"_topic").value.replace(/(^\s*)|(\s*$)/g,'') != ""){
						if($('#'+idi+'_startdate').val()<=$('#'+idi+'_enddate').val()){
						if(document.getElementById(idi+"_content").value.length<=4000){
							if(document.getElementById(idi+"_content").value=="最多输入600字"){
								document.getElementById(idi+"_content").value = "";
							}if(document.getElementById(idi+"_topic").value=="最多输入25字"){
								document.getElementById(idi+"_topic").value = "";
							}if(document.getElementById(idi+"_address").value=="最多输入30字"){
								document.getElementById(idi+"_address").value = "";
							}
							var v_url='${ctx}/partinfo/PartinfoAction.a?doUpdataSelfPartInfoZh'
							Ext.Ajax.request({
								url : v_url,
								method:'POST',
								defaults:{autoScroll: true},
								success:function(response,options){
									if(response.responseText=="##"){
										text_style(idi+"_content");
										alert_save_failure(name);
									}else{
										document.getElementById(idi+"_td").style.backgroundColor ="#eee"; 
										$("#"+idi+"_save").attr('onclick','');
										 $("#"+idi+"_save").bind("click",function(){
											 updateold(idi)
											});
									 	$("#"+idi+"_topic").bind("focus",function(){
											changeBackgroundtd(idi);
										});
										$("#"+idi+"_content").bind("focus",function(){
											changeBackgroundtd(idi);
										});
										$("#"+idi+"_content").removeAttr("style"); 
										 $("#"+idi+"_content").css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"}); 
										/* $("#"+idi+"_topic").bind("blur",function(){
											returnBackgroundtd(idi);
										});
										$("#"+idi+"_content").bind("blur",function(){
											returnBackgroundtd(idi);
										}); */
										alert_save_success(name,$("#"+idi+"_num").html());
									}
								},
								params : {
									id : idi,
									part_info : document.getElementById(idi+"_content").value,
									topic : document.getElementById(idi+"_topic").value,
									address : $("#"+idi+"_address").val(),
									startdate : $('#'+idi+'_startdate').val(),
									enddate : $('#'+idi+'_enddate').val(),
									evaluateType1 : type1,
									evaluateType2 : type2,
									evaluateType : type,
									termId : document.getElementById("termId").value,
									CreateDate : document.getElementById(idi+"_startDate").value
								}
							});
						}else{
							apprasial_alert(4000);
						}
						}else{
							alert("起始日期不能大于截止日期!");
						}
					}else{
						alert("主题不能为空");
					}
			}else{
				savepackage(div,day,valuenum,rid,zhuti,"0");
			}
		}
		
		function updateold(id){
				var this_div = document.getElementById(id + "_content");
				var type = document.getElementById("evaluateType2").value;
				var name = "基本情况";
				if(document.getElementById(id + "_topic").value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
					if($('#'+id+'_startdate').val()<=$('#'+id+'_enddate').val()){
						if(this_div.value.length<=600){
							if(document.getElementById(id + "_topic").value=="最多输入25字"){
							document.getElementById(id + "_topic").value = "";
							}
							if(document.getElementById(id + "_address").value=="最多输入30字"){
								document.getElementById(id + "_address").value = "";
							}
							if(this_div.value=="最多输入600字"){
							this_div.value = "";
							}
							var v_url='${ctx}/partinfo/PartinfoAction.a?doUpdataSelfPartInfoZh'
								  Ext.Ajax.request({
										url : v_url,
										method:'POST',
										defaults:{autoScroll: true},
										success:function(response,options){
											if(response.responseText=="##"){
												text_style(id+"_content");
												alert_update_failure(name);
											}else{
												document.getElementById(id+"_td").style.backgroundColor ="#eee";
												alert_update_success(name,$("#"+id+"_num").html())
											}
										},
										params : {
											id : id,
											part_info : this_div.value,
											evaluateType : type,
											topic : document.getElementById(id+"_topic").value,
											termId : document.getElementById("termId").value,
											address : $("#"+id+"_address").val(),
											startdate : $('#'+id+'_startdate').val(),
											enddate : $('#'+id+'_enddate').val(),
											CreateDate : document.getElementById(id+"_startDate").value
										}
									});
						}else{
							apprasial_alert(600);
						}
					}else{
						alert("起始日期不能大于截止日期!");
					}
				}else{
					alert("主题不能为空");
				}
		}
		function changeBackgroundtd(id)
		{
			document.getElementById(id+"_td").style.backgroundColor ="";
			if($("#"+id+"_content").val() !="最多输入600字"){
				$("#"+id+"_content").css({background:"transparent",color:"black",float:"left",cursor:"text"});
			}
			if($("#"+id+"_content").is(":focus")){
				flag = false; 
				 } 
			if((!$("#"+id+"_content").is(":focus"))&&$("#"+id+"_content").val() ==""){
				$("#"+id+"_content").val("最多输入600字");
			 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if((!$("#"+id+"_topic").is(":focus"))&&$("#"+id+"_topic").val() ==""){
				$("#"+id+"_topic").val("最多输入25字");
			 	$("#"+id+"_topic").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
			if((!$("#"+id+"_address").is(":focus"))&&$("#"+id+"_address").val() ==""){
				$("#"+id+"_address").val("最多输入30字");
			 	$("#"+id+"_address").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
			}
		}
		function returnBackgroundtd(id)
		{
			document.getElementById(id+"_td").style.backgroundColor ="#eee";
		}
	/* 	//更改学期刷新页面
			function changeterm(){
				ShowDiv();
				var term = document.getElementById("termId").value;
				url="${ctx}/partinfo/PartinfoAction.a?choicenum="+${choicenum}+"&termId1="+term;
				document.location.replace(url);
			} */
		function checkupload(obj){
			/* var id = window.event.srcElement.getAttribute('id'); */
		/* 	event = event? event: window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.id; */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idi = arr[0];
			if(idi=="new"){
				var name="new_topic2"
			}else{
				var name=idi+"_topic"
			}
			if(document.getElementById(name).value.replace(/(^\s*)|(\s*$)/g,'').length!=0&&document.getElementById(name).value!="最多输入25字"){
				if(document.getElementById(name).value.length<=25){
					if(document.getElementById("new_fileField").value!=""){
						var paths = new Array();
						paths = $("#new_fileField").val().split("\\");
						var path_name = new Array();
						path_name = paths[paths.length-1].split(".");
						var pname = path_name[0];
						if(pname.length<=25){
						savepackage('down','new_startDate2','new_content2','new_rpID','new_topic2','1');
						}else{
							alert("上传文件名不能超过25个字!");
						}
					}else{
						alert("请选择要上传的文件！");
					}
				}else{
					apprasial_alert(25);
				}
			}else{
				alert("主题不能为空");
			}
		}
//---------------------------------------------------------------------
		function doupdate(id){
		var this_div = document.getElementById(id+"_content");
		var this_date = document.getElementById(id+"_startDate");
		var type = document.getElementById("evaluateType1").value;
		 if(this_div.value=="最多输入600字"){
			this_div.value=""
		}
		if(this_div.value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
			if(this_div.value!=this_div.attributes["idvalue"].nodeValue){
				if(this_div.value.length<=600){
					if(this_div.value.replace(/(^\s*)|(\s*$)/g,'')!=""){
						Ext.Ajax.request({
							url:'${ctx}/partinfo/PartinfoAction.a?doUpdataSelfPartInfo',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								var temp=response.responseText;
								if(temp=="##"){
									alert_update_failure("自我评价");	
									text_style(id+"_content");
								}else{
									if(interdelete == 0){
										document.getElementById(id+"_content").style.border ="1px solid transparent";
										document.getElementById(id+"_td").style.backgroundColor ="#eee";
										this_div.setAttribute("idvalue",this_div.value);
										alert_update_success("自我评价",$("#"+id+"_num").html())
										isSave = 0;
									}
								}
							},
							params : {
								id : id,
								part_info : this_div.value,
								evaluateType : type,
								termId : document.getElementById("termId").value,
								CreateDate : document.getElementById(id+"_startDate").value
							}
						});
					}
				}else{
					isSave = 0;
					apprasial_alert(600);
				}
			}else{
				document.getElementById(id+"_content").style.border ="1px solid transparent";
				document.getElementById(id+"_td").style.backgroundColor ="#eee";
				isSave = 0;
			}
		}
	}
	
	function doupdate1(id){
		var this_div = document.getElementById(id+"_content");
		var this_date = document.getElementById(id+"_startDate");
		var type = document.getElementById("evaluateType1").value;
		 if(this_div.value=="最多输入600字"){
				this_div.value=""
			} 
		if(this_div.value.replace(/(^\s*)|(\s*$)/g,'').length!=0){
			if(this_div.value.length<=600){
				if(this_div.value.replace(/(^\s*)|(\s*$)/g,'')!=""){
					Ext.Ajax.request({
						url:'${ctx}/partinfo/PartinfoAction.a?doUpdataSelfPartInfo',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							var temp=response.responseText;
							if(temp=="##"){
								alert_update_failure("自我评价");	
								text_style(id+"_content");
							}else{
								if(interdelete == 0){
									/* document.getElementById(id+"_content").style.border ="1px solid transparent";
									document.getElementById(id+"_td").style.backgroundColor ="#eee"; */
									this_div.setAttribute("idvalue",this_div.value);
									alert_update_success("自我评价",$("#"+id+"_num").html())
									isSave = 0;
								}
							}
						},
						params : {
							id : id,
							part_info : this_div.value,
							evaluateType : type,
							termId : document.getElementById("termId").value,
							CreateDate : document.getElementById(id+"_startDate").value
						}
					});
				}
			}else{
				isSave = 0;
				apprasial_alert(600);
			}
		}
	}
	
	function save(day,valuenum,obj){
		var type1 = document.getElementById("evaluateType1").value;
		var type2 = "1";
		 if(document.getElementById(valuenum).value=="最多输入600字"){
			 document.getElementById(valuenum).value=""
			} 
		if(document.getElementById(valuenum).value.replace(/(^\s*)|(\s*$)/g,'') != ""){
			document.getElementById(valuenum).disabled = true;//提交过程中设为不可编辑
			document.getElementById(day).disabled = true;//提交过程中设为不可编辑
			if(document.getElementById(valuenum).value.length<=600){
					Ext.Ajax.request({
						url:'${ctx}/partinfo/PartinfoAction.a?doInsertSelfAppWithoutatt',
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
									deleteselfapp1(oldid,obj);
									isDelete = 0;
									isSave = 0;
									document.getElementById(saveid+"_content").setAttribute("idvalue",document.getElementById(saveid+"_content").value);
									document.getElementById(saveid+"_content").style.border ="1px solid transparent"; 
									document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
								}else{
									/* if(interdelete == 0){ */
										alert_save_success("自我评价",$("#"+saveid+"_num").html());
										document.getElementById(saveid+"_content").setAttribute("idvalue",document.getElementById(saveid+"_content").value);
										isSave = 0;
										/* $("#"+saveid+"_startDate").bind("change",function(){
											doupdate1(saveid);
										}); */
										document.getElementById(saveid+"_content").style.border ="1px solid transparent"; 
										document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
									/* } */
								}
								isSave = 0;
							}
						},
						params : {
							part_info : document.getElementById(valuenum).value,
							evaluateType1 : type1,
							evaluateType2 : type2,
							termId : document.getElementById("termId").value,
							CreateDate : document.getElementById(day).value
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
				document.getElementById("new_content1").style.border ="1px solid transparent";
				clear_notice1("new_content1",600);
			}
			if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
				document.getElementById("new_content11").style.border ="1px solid transparent";
				clear_notice1("new_content11",600);
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
					str +="<tr id='new_tr1'><td width='10%' height='40' id='new_num' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"日期：<input id='new_startDate11' type='text' size='10' onClick='WdatePicker()' value='${nowDate}' onchange='checksave(\""+"new_startDate11"+"\",\""+"new_content11"+"\",this)'/><input type='hidden' name='insertId' id='insertId' /> "
					+"</td></tr><tr><td colspan='2' id='new_td1' class='pl20 pr20 pb20 pt10 zuojuzhong'>"
					+"<textarea id='new_content11' style='background: transparent; overflow:auto;  border: 1px solid transparent; height: 130px; width: 100%;' onblur='checksave(\""+"new_startDate11"+"\",\""+"new_content11"+"\",this)'  onfocus='changeback(this)'></textarea>"
					+"</td><td width='100' class='bg_eee'><span class='fr btn'  style='margin:0px auto;float:none'><input type='button' class='fr shanchu1' style='margin:0px auto;float:none' title='删除' id='new_delete1' onclick='deleteselfapp(this)'/></span></td></tr>";
					$("#"+trid).before(str);
					input_notice("new_content11",600);
					tr_num = 0;
			 }
		} 
		function deleteselfapp(obj){
			 interdelete = 1;
			 var type = document.getElementById("evaluateType1").value;
			 var r=apprasial_delete();
				/* var id = window.event.srcElement.getAttribute('id'); 
				event = event? event: window.event;
			var obj=event.srcElement ? event.srcElement : event.target;*/
			var id=$(obj).attr("id");
				var arr = new Array();
				arr = id.split("_");
				var idd = arr[0];
				if (r==true){
					$("#"+idd+"_content").attr("disabled",true);
					isDelete = 1;
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
						    		apprasial_del_Finish();
						    		isDelete =0;
						    		interdelete = 0;
						    	}
							}else{
								Ext.Ajax.request({
									url:'${ctx}/partinfo/PartinfoAction.a?deletePartInfo',
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
														$("#new_content1").removeAttr("onblur");
														$("#new_content1").bind("blur",function(){
															checksave("new_startDate1","new_content1","#new_content1");
														});
														$("#new_content1").attr("disabled",false);
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
													$("#new_content11").removeAttr("onblur");
													$("#new_content11").bind("blur",function(){
														checksave("new_startDate11","new_content11","#new_content11");
													});
													$("#new_content11").attr("disabled",false);
													input_notice("new_content11",600);
												}
												interdelete = 0;
									    	}else{
									    		 $("#"+idd+"_tr").next().remove();    
													$("#"+idd+"_tr").remove(); 
													interdelete = 0;
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
												document.getElementById("new_content1").style.border ="1px solid transparent";
											}
											if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
												document.getElementById("new_content11").style.border ="1px solid transparent";
											}
											alert_delete_success("自我评价");
										}
									},
									params : {
										evaluateType : type,
										termId : document.getElementById("termId").value,
										id : idd
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
		
		function deleteselfapp1(checkid,obj){
			 	interdelete = 1;
				var type1 = document.getElementById("evaluateType1").value;
				var type = document.getElementById("evaluateType1").value;
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
							url:'${ctx}/partinfo/PartinfoAction.a?deletePartInfo',
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
												$("#new_content1").removeAttr("onblur");
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
											$("#new_content11").removeAttr("onblur");
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
										document.getElementById("new_content1").style.border ="1px solid transparent";
									}
									if($("#new_content11")!=null&&$("#new_content11").val()!=undefined){
										document.getElementById("new_content11").style.border ="1px solid transparent";
									}
									alert_delete_success("自我评价");
								}
							},
							params : {
								evaluateType : type,
								termId : document.getElementById("termId").value,
								id : idd
							}
						}); 
					}
		}
		//更改学期刷新页面
		function changeterm(){
			ShowDiv();
			var term = document.getElementById("termId").value;
			var choicenum = 1008;
			var evaluateType = 85;
			url="${ctx}/partinfo/PartinfoAction.a?choicenum="+choicenum+"&termId1="+term+"&evaluateType="+evaluateType;
			document.location.replace(url);
		}
			
		function checksave(day,valuenum,obj){
			/* var id = window.event.srcElement.getAttribute('id'); 
			var event = event || window.event;
			var obj=event.srcElement ? event.srcElement : event.target;
			var id=obj.getAttribute('id');*/
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
		    	$("td").each(function(){
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
	　　
			function turnon(id,obj){
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
			if(id!="new"){
				document.getElementById(id+"_td").style.backgroundColor ="";
			}
				if($("#"+idd).val()=="最多输入600字"){
					$("#"+idd).removeAttr("style"); 
				 $("#"+idd).css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"}); 
					$("#"+idd).val("");
				}
				if((!$("#"+id+"_content").is(":focus"))&&$("#"+id+"_content").val() ==""){
					$("#"+id+"_content").val("最多输入600字");
				 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_topic").is(":focus"))&&$("#"+id+"_topic").val() ==""){
					$("#"+id+"_topic").val("最多输入25字");
				 	$("#"+id+"_topic").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_address").is(":focus"))&&$("#"+id+"_address").val() ==""){
					$("#"+id+"_address").val("最多输入30字");
				 	$("#"+id+"_address").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
			}
			function turnoff(id,obj){
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
				if ($("#"+idd).val() ==""){
					$("#"+idd).val("最多输入600字");
				 	$("#"+idd).css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
			}
			
			function turnon1(id,obj){
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
			if(id!="new"){
				document.getElementById(id+"_td").style.backgroundColor ="";
			}
				if($("#"+idd).val()=="最多输入25字"){
					$("#"+idd).removeAttr("style"); 
					$("#"+idd).css({background:"transparent"});
					$("#"+idd).val("");
				}
				if((!$("#"+id+"_content").is(":focus"))&&$("#"+id+"_content").val() ==""){
					$("#"+id+"_content").val("最多输入600字");
					alert();
				 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_topic").is(":focus"))&&$("#"+id+"_topic").val() ==""){
					$("#"+id+"_topic").val("最多输入25字");
				 	$("#"+id+"_topic").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_address").is(":focus"))&&$("#"+id+"_address").val() ==""){
					$("#"+id+"_address").val("最多输入30字");
				 	$("#"+id+"_address").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
			}
			function turnoff1(id,obj){
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
				if ($("#"+idd).val() ==""){
					$("#"+idd).val("最多输入25字");
					$("#"+idd).css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
				}
			}		
			
			function turnon2(id,obj){
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
			if(id!="new"){
				document.getElementById(id+"_td").style.backgroundColor ="";
			}
				if($("#"+idd).val()=="最多输入30字"){
					$("#"+idd).removeAttr("style"); 
					$("#"+idd).css({background:"transparent",float:"left",cursor:"text"});
					$("#"+idd).val("");
				}
				if((!$("#"+id+"_content").is(":focus"))&&$("#"+id+"_content").val() ==""){
					$("#"+id+"_content").val("最多输入600字");
				 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_topic").is(":focus"))&&$("#"+id+"_topic").val() ==""){
					$("#"+id+"_topic").val("最多输入25字");
				 	$("#"+id+"_topic").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
				if((!$("#"+id+"_address").is(":focus"))&&$("#"+id+"_address").val() ==""){
					$("#"+id+"_address").val("最多输入30字");
				 	$("#"+id+"_address").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
				}
			}
			function turnoff2(id,obj){
				
			var idd=$(obj).attr("id");
			var arr = new Array();
			arr = idd.split("_");
			var id = arr[0];
				if ($("#"+idd).val() ==""){
					$("#"+idd).val("最多输入30字");
					$("#"+idd).css({background:"transparent",color:"#BCBCBC",float:"left",cursor:"text"});
				}
			}
			$(window).load(function() {
				 var thisMession=document.getElementById("mession");
			
				 thisMession.innerHTML="";
				 thisMession.innerHTML=mess;  
				  });

</script>
</html>