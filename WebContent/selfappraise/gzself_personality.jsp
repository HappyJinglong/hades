<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/masses.jsp"%>
<un:bind var="TYPE_GXFZ_ZWPJ" type="com.flyrish.hades.common.Constant" field="TYPE_GXFZ_ZWPJ" />
<un:bind var="TYPE_GXFZGC" type="com.flyrish.hades.common.Constant" field="TYPE_GXFZGC" />
<un:bind var="TYPE_GXFZ_CGZS" type="com.flyrish.hades.common.Constant" field="TYPE_GXFZ_CGZS" />
<un:bind var="PAGE_GXFZ" type="com.flyrish.hades.common.Constant" field="PAGE_GXFZ" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我评价</title>
</head>
<style>
.wenbenyu000{ width:660px; height:100px; line-height:20px; border:1px solid #999; resize:none;}
#pj_ziwo_main{top:38px;padding:13px;margin-bottom:-8px;}
#pj_ziwo_main .fujian{ color:#ccc; text-align:right; padding-right:20px; width:48%;}
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
<input type="hidden" name="evaluateType1" id="evaluateType1" value="${TYPE_GXFZ_ZWPJ}"> 
<input type="hidden" name="evaluateType2" id="evaluateType2" value="${TYPE_GXFZGC}"> 
<input type="hidden" name="choicenum" id="choicenum" value="${choicenum}"> 
<input type="hidden" name="evaluateType2" id="evaluateType3" value="${TYPE_GXFZ_CGZS}"> 
<input type="hidden" name="filePath" id="filePath" value="${filePath}"/>
<input type="hidden" name="fileName" id="fileName" value="${fileName}"/>
<input type="hidden" name="evaluatePersonName" id="evaluatePersonName" value="${evaluatePersonName}"> 
	<div class="dangqianwz">
	<span class="fl">当前位置：自我评价->个性与发展</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<span>学期：
    	<select name="termId" id="termId" onchange="changeterm()"  class="wenziliebiao100">
            <app:highSchoolTermTag selectClassid="${classid}" selectNum="${termId}" levelCode="${levelcode}"/>
        </select>
    </span>
 	</div>
	<div id="pj_ziwo_main">
	<div id="top1"  class="top">
		<div id="new_top_jbqk" <c:if test="${not empty personalityDtosList}">style='display:none'</c:if>>
			<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
				class="biaoge">
				<tr class="title_bg">
					<td colspan="4">个性发展基本情况<span class="red">*</span></td>
				</tr>
				<tr>
					<td height="40" colspan="4" class="youjuzhong bg_eee">
					评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					日期：<span class="youjuzhong pr20 bg_eee">
					<input id="new_startDate_jbqk" type="text" size="10" value="${nowDate}" idvalue="${nowDate}" onClick="WdatePicker()"/> 
					</span></td>
				</tr>
				<tr>
					<td class="th" width="10%">二级指标</td>
					<td class="th" width="10%">三级指标</td>
					<td class="th">个性发展记录</td>
					<td class="th">操作</td>
				</tr>
				<tr>
					<td height="191" class="h50 th">特长</td>
					<td class="h50 th">学科特长<br /> 体育运动特长<br /> 艺术特长
					</td>
					<td class="h50" id="new_td11"><span class="downbox mt18"> 
							<textarea id="new_personalty1" class="fl wenbenyu000" style="  margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;" onfocus="turnon1('new_personalty1',this)" onblur="turnoff1('new_personalty1',this)">最多输入4000字</textarea>
					</span>
					</td>
					<td width="10%" rowspan="3" class="bg_eee" id="bg_eee_2_4">
					<input type="button" class="fr tianjia" style="margin-right:18px;" onclick="savejbqk()"/>
					</td>
				</tr>
				<tr>
					<td height="191" class="h50 th">有新意的成果</td>
					<td class="h50 th">活动成果<br /> 设计成果<br /> 制作成果
					</td>
					<td class="h50 " id="new_td22"><span class="downbox mt18"> 
							<textarea id="new_personalty2" class="fl wenbenyu000" style=" margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;" onfocus="turnon1('new_personalty2',this)" onblur="turnoff1('new_personalty2',this)">最多输入4000字</textarea>
					</span></td>
				</tr>
				<tr>
					<td height="191" class="h50 th">其他</td>
					<td class="h50 th"  id="new_td33">自主选择</td>
					<td class="h50 "><span class="downbox mt18"> 
							<textarea id="new_personalty3" class="fl wenbenyu000" style=" margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;" onfocus="turnon1('new_personalty3',this)" onblur="turnoff1('new_personalty3',this)">最多输入4000字</textarea>
					</span></td>
				</tr>
			</table>
			</div>
			
			
			<div id="old_top_jbqk" <c:if test="${empty personalityDtosList}">style='display:none'</c:if>>
			<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
				class="biaoge">
				<tr class="title_bg">
					<td colspan="4">个性发展基本情况<span class="red">*</span></td>
				</tr>
				<tr>
					<td height="40" colspan="4" class="youjuzhong bg_eee">
					评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					日期：<span class="youjuzhong pr20 bg_eee">
					<c:forEach items="${personalityDtosList}" var="list" varStatus="status">
						<c:if test="${list.indexid eq '1'}">
						<input id="old_startDate_jbqk" type="text" size="10" value="${list.signdate}" idvalue="${list.signdate}" onClick="WdatePicker()"/> 
						</c:if>
					</c:forEach>
					</span></td>
				</tr>
				<tr>
					<td class="th" width="10%">二级指标</td>
					<td class="th" width="10%">三级指标</td>
					<td class="th" width="70%">个性发展记录</td>
					<td class="th"width="10%">操作</td>
				</tr>
				<tr>
					<td height="191" class="h50 th">特长</td>
					<td class="h50 th">学科特长<br /> 体育运动特长<br /> 艺术特长
					</td>
					<td class="h50" id="old_td11" style="background:#eee;">
					<span class="downbox mt18"  style="padding-left:0px"> 
						<c:forEach items="${personalityDtosList}" var="list" varStatus="status">
							<c:if test="${list.indexid eq '1'}">
							<input id="old_jbqk1" type="hidden" value="${list.baseid}"/><textarea id="old_personalty1" class="fl wenbenyu000" style=" margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px;background: transparent; float:left; cursor:text; width:100%;" idvalue="${list.developmentrd}" <c:if test="${empty list.developmentrd}">style="float:left; color:#BCBCBC; cursor:text;"</c:if> onfocus="turnon1('old_personalty1',this)" onblur="turnoff1('old_personalty1',this)"><c:if test="${not empty list.developmentrd}">${list.developmentrd}</c:if><c:if test="${empty list.developmentrd}">最多输入4000字</c:if></textarea>
							</c:if>
						</c:forEach>
					</span>
					</td>
					<td width="10%" rowspan="3" class="bg_eee" id="bg_eee_2_4">
					<input type="button" class="fr tianjia" style="margin-right:18px;" onclick="updatejbqk()"/>
					</td>
				</tr>
				<tr>
					<td height="191" class="h50 th">有新意的成果</td>
					<td class="h50 th">活动成果<br /> 设计成果<br /> 制作成果
					</td>
					<td class="h50 " id="old_td22" style="background:#eee;">
					<span class="downbox mt18" style="padding-left:0px"> 
						<c:forEach items="${personalityDtosList}" var="list" varStatus="status">
						<c:if test="${list.indexid eq '2'}">
							<input id="old_jbqk2" type="hidden" value="${list.baseid}"/>
							<textarea id="old_personalty2" class="fl wenbenyu000" style=" margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px;background: transparent; float:left; cursor:text; width:100%	;" idvalue="${list.developmentrd}" <c:if test="${empty list.developmentrd}">style="float:left; color:#BCBCBC; cursor:text;"</c:if> onfocus="turnon1('old_personalty2',this)" onblur="turnoff1('old_personalty2',this)"><c:if test="${not empty list.developmentrd}">${list.developmentrd}</c:if><c:if test="${empty list.developmentrd}">最多输入4000字</c:if></textarea>
							</c:if>
						</c:forEach>
					</span></td>
				</tr>
				<tr>
					<td height="191" class="h50 th">其他</td>
					<td class="h50 th">自主选择</td>
					<td class="h50 " id="old_td33" style="background:#eee;">
					<span class="downbox mt18" style="padding-left:0px"> 
						<c:forEach items="${personalityDtosList}" var="list" varStatus="status">
							<c:if test="${list.indexid eq '3'}">
							<input id="old_jbqk3" type="hidden" value="${list.baseid}"/><textarea id="old_personalty3" class="fl wenbenyu000" style=" margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px;background: transparent; float:left; cursor:text; width:100%;" idvalue="${list.developmentrd}" <c:if test="${empty list.developmentrd}">style="float:left; color:#BCBCBC; cursor:text;"</c:if> onfocus="turnon1('old_personalty3',this)" onblur="turnoff1('old_personalty3',this)"><c:if test="${not empty list.developmentrd}">${list.developmentrd}</c:if><c:if test="${empty list.developmentrd}">最多输入4000字</c:if></textarea>
							</c:if>
						</c:forEach>
					</span></td>
				</tr>
			</table>
			</div>
		</div>
			<div id="top" class="top">
		<%int n=1;%>
			<div id="new_top" <c:if test="${not empty appraisalList1}">style='display:none'</c:if>>
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge" id="new_table" style="margin-top: 19px;">
					<tr class="title_bg">
						<td colspan="3">自我评价&nbsp;<span class="red"></span></td>
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
						 <td width="10%" class="bg_eee" id="bg_eee_2_1"><span class="fr btn" style="margin:0px auto;float:none">
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
			<input type="hidden" name="newId" id="newId" value="${newId}"> 
			<div id="old_top" <c:if test="${empty appraisalList1}">style='display:none'</c:if>>
				<table width="100%" border="0" cellspacing="1" bgcolor="#999999" style="margin-top: 19px;"
					class="biaoge" id="new_table1" >
					<tr class="title_bg">
						<td colspan="3">自我评价&nbsp;<span class="red"></span></td>
					</tr>
					<c:forEach items="${appraisalList1}" var="list" varStatus="status">
					<tr id="${list.apprasialid}_tr">
					  	<td width="10%" height="40" class="bg_eee" id="${list.apprasialid}_num"><%=n%></td>
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
						<td width="10%" class="bg_eee" id="bg_eee_2_1">
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
		<br></br>
		<div id="top_down" class="top"  style="margin-top: -14px;">
			<c:if test="${empty appraisalList2}">
			<div id="new_top_down" >
				<table width="100%" id="new_table3" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
					<td colspan="3">个性发展过程&nbsp;</td>
					</tr>
					<tr id="new_tr3">
						<td width="10%" height="40" id="new_num3" class="bg_eee">1</td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
							评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							日期：<input id="new_startDate3" type="text" value="${nowDate}" size="10" onClick="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="h50" id ="new_td3" style="width: 90%;">
								<input type="hidden" class="fl wenbenkuang670" id="new_topic3"  style="background: transparent;float:left;color:#BCBCBC;cursor:text;" />
							<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
								<textarea id="new_content3" style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;" onfocus="turnon('new_content3',this)" onblur="turnoff('new_content3',this)">最多输入300字</textarea>
							</div>
							<div id="new_attach3">
								<div class="downbox mt18" id="new_attachment3">
	            			</div>
            				</div>	
							<div class="downbox mt18" style="position: relative;">
							<form method="post" id="new_frm3">
									<input type="hidden" name="new_uuid3" id="new_uuid3"/>
									<input type="hidden" name="new_rpID3" id="new_rpID3" />
									<span class="fl wenben85left">附件：</span> 
									<!-- <input name='new_textfield3' id='new_textfield3' type="text"
										class="wenbenkuang670 fujian" style="position:absolute;background: transparent;left:80px;" style="margin-left:28px"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file"
										name="new_fileField3" class="fileadd2" id="new_fileField3" size="28"
										onchange="document.getElementById('new_textfield3').value=this.value" 
										style="margin-left:28px;"/><span id="new_tp3" style="margin-right:8px">0%</span>  
									<input type="button" value="添 加" class="shangchuan ml10" /> 
									<input type="button" value="上 传" style="margin-right:-100px" id ="new_upload3" onclick="checkupload1()"
										class="shangchuan ml10" /> </span> -->
									<input name='new_fileField3' id='new_fileField3' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="new_upload3" class="shangchuan ml10" type="button" value="上 传" 
									onclick="checkupload1()"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="new_tp3">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
							</form>
							</div>
						</td>
						<td width="10%" height="191" class="bg_eee" id="bg_eee_2_2" style="margin:0px auto;float:none">
						<input type="button" class="fr shanchu1" style="margin:0px auto;float:none" title="删除" id="new_delete3" onclick="deleteselfpackage1(this)"/>
						<input type="button" class="fr tianjia" style="margin:0px auto;float:none" title="保存" id="new_save3" onclick="check1('down','new_startDate3','new_content3','new_rpID3','new_topic3',this)"/></td>
					</tr>
					<tr id="addpackagelocation3">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" style="margin-right :18px;" onclick="addnew3()" class="fr shanchu" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
			<c:if test="${not empty appraisalList2}">
			<div id="new_top_down" >
				<table width="100%"  id="new_table3" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
					<td colspan="3">个性发展过程&nbsp;</td>
					</tr>
					<%int i=1;%>
					<c:forEach items="${appraisalList2}" var="list" varStatus="status">
						<tr id="${list.apprasialid}_tr">
							<td width="10%" height="40 " class="bg_eee" id="${list.apprasialid}_num"><%=i%></td>
							   <td colspan="2" class="youjuzhong pr20 bg_eee" >
							   		评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        			签名：${evaluatePersonName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        			日期：<input id="${list.apprasialid}_startDate" type="text" value="${list.signdate}" size="10" onClick="WdatePicker()"/> 
							   </td>
						</tr>
						<tr>
							<td colspan="2" id="${list.apprasialid}_td" style="background:#eee;width: 90%;">
								<input type="hidden" style="background: transparent;" class="fl wenbenkuang670" id="${list.apprasialid}_topic" onclick="changeBackgroundtd('${list.apprasialid}')"/>
								<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
				 					 <textarea class="content"
						                id="${list.apprasialid}_content"
						                style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999;"
						                idvalue="${list.apprasial}"  onfocus="turnon('${list.apprasialid}',this)" onblur="turnoff('${list.apprasialid}',this)"
						                onclick="changeBackgroundtd('${list.apprasialid}');"
						                >${list.apprasial}</textarea>
								</div>
								<div id="${list.apprasialid}_attach">
										<%int j=1;%>
									<c:forEach items="${list.attachListForFile}" var="attachFile">
										<div class="downbox mt18" id="${attachFile.attachid}_attachment">
		            					<span class="fl wenben85left">附件<%=j %>：&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" style="color:black;" onclick="dodown('${attachFile.attachid}','${list.apprasialid}')">${attachFile.filename}</a>
		            					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteselfattach1('${attachFile.attachid}','${list.apprasialid}')"><img src="${ctx}/images/upload_del.gif" style="height:10px; width:10px; "border="0"/></img></a>
		            					</span>
			            						<%-- ${attachFile.filename}
			            						<input type="button" value="下 载" class="shangchuan fl ml10"  onclick="dodown('${attachFile.attachid}')"/> 
												<input type="button" value="删 除" class="shangchuan fl ml10" onclick="deleteselfattach1('${attachFile.attachid}','${list.apprasialid}')"/> --%>
	            						<%j++;%>
	            						</div>
            						</c:forEach>
            					</div>	
								<div class="downbox mt18" style="position: relative;">
									<form method="post" id="${list.apprasialid}_frm">
									<input type="hidden" name="${list.apprasialid}_old_uuid" id="${list.apprasialid}_old_uuid" value="${uuid}"/>
									<input type="hidden" name="${list.apprasialid}_rpID" id="${list.apprasialid}_rpID" value="${list.apprasialid}"/>
									<span class="fl wenben85left">附件：</span> 
									<%-- <input name='${list.apprasialid}_textfield' id='${list.apprasialid}_textfield' type="text"
										class="wenbenkuang670 fujian"  style="position:absolute;background: transparent;left:80px;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file" class="fileadd2" size="28" name="${list.apprasialid}_fileField" id="${list.apprasialid}_fileField"  style="margin-left:130px"
									onchange="document.getElementById('${list.apprasialid}_textfield').value=this.value"/><span id="${list.apprasialid}_tp" style="margin-left:100px">0%</span>  
									<input type="button" value="添 加" class="shangchuan ml10" style="margin-left:20px" /> 
									<input type="button" value="上 传" id ="${list.apprasialid}_upload" onclick="return beforeSubmitForm1('old_fileField','${list.apprasialid}_frm','${list.apprasialid}_old_uuid','${list.apprasialid}_tp','${list.apprasialid}');"
										class="shangchuan ml10" /> 
									</span> --%>
									<input name='${list.apprasialid}_fileField' id='${list.apprasialid}_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="${list.apprasialid}_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="return beforeSubmitForm1('old_fileField','${list.apprasialid}_frm','${list.apprasialid}_old_uuid','${list.apprasialid}_tp','${list.apprasialid}');"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="${list.apprasialid}_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
									</form>
								</div>
							</td>
							<td width="10%" height="191" class="bg_eee" id="bg_eee_2_2" style="margin:0px auto;float:none">
							<input type="button" class="fr shanchu1" title="删除" style="margin:0px auto;float:none" id="${list.apprasialid}_delete2" onclick="deleteselfpackage1(this)"/> 
							<input type="button" class="fr tianjia" title="保存" style="margin:0px auto;float:none" id="${list.apprasialid}_save"  onclick="updateold1('${list.apprasialid}')"/>
							</td>
						</tr>
						<%i++;%>
					</c:forEach>
					<tr id="addpackagelocation3">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" onclick="addnew3()" style="margin-right :18px;" class="fr shanchu" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
		</div>	
		<!-- 特长与成果展示 -->
		<div id="down" class="down mt18 pb30" style="margin-top: 19px;">
			<c:if test="${empty appraisalList3}">
			<div id="new_down" >
				<table width="100%" id="new_table2" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
					<td colspan="3">特长与成果展示&nbsp;</td>
					</tr>
					<tr id="new_tr2">
						<td width="10%" height="40" id="new_num2" class="bg_eee">1</td>
						<td colspan="2" class="youjuzhong pr20 bg_eee">
							提供人：<select name="new_person" id="new_person">
               								<app:appraiserSelect selectValue=""/>
              						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							日期：<input id="new_startDate2" type="text" value="${nowDate}" size="10" onClick="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="h50" id ="new_td2" style="width: 90%;">
								<input type="hidden" class="fl wenbenkuang670" id="new_topic2"  style="background: transparent;float:left;color:#BCBCBC;cursor:text;" />
							<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
								<textarea id="new_content2" style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;" onfocus="turnon('new_content2',this)" onblur="turnoff('new_content2',this)">最多输入300字</textarea>
							</div>
							<div id="new_attach">
								<div class="downbox mt18" id="new_attachment2">
	            			</div>
            				</div>	
							<div class="downbox mt18" style="position: relative;">
							<form method="post" id="new_frm">
									<input type="hidden" name="new_uuid" id="new_uuid"/>
									<input type="hidden" name="new_rpID" id="new_rpID" />
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
									<input type="button" value="上 传"  style="margin-right:-100px" id ="new_upload" onclick="checkupload()"
										class="shangchuan ml10" /> </span> -->
									<input name='new_fileField' id='new_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="new_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="checkupload()"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="new_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
										
							</form>
							</div>
						</td>
						<td width="10%" height="191" class="bg_eee" id="bg_eee_2_3">
						<input type="button" class="fr shanchu1" title="删除" id="new_delete2" style="margin:0px auto;float:none" onclick="deleteselfpackage(this)"/>
						<input type="button" class="fr tianjia" title="保存" id="new_save" style="margin:0px auto;float:none" onclick="check('down','new_startDate2','new_content2','new_rpID','new_topic2',this)"/></td>
					</tr>
					<tr id="addpackagelocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" onclick="addnew1()" style="margin-right :18px;" class="fr shanchu" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			</c:if>
			<c:if test="${not empty appraisalList3}">
			<div id="new_down" >
				<table width="100%"  id="new_table2" border="0" cellspacing="1" bgcolor="#999999"
					class="biaoge">
					<tr class="title_bg">
					<td colspan="3">特长与成果展示&nbsp;</td>
					</tr>
					<%int i=1;%>
					<c:forEach items="${appraisalList3}" var="list" varStatus="status">
						<tr id="${list.apprasialid}_tr">
							<td width="10%" height="40 " class="bg_eee" id="${list.apprasialid}_num"><%=i%></td>
							   <td colspan="2" class="youjuzhong pr20 bg_eee" >
							   		提供人：<select name="${list.apprasialid}_person" id="${list.apprasialid}_person" >
               								<app:appraiserSelect selectValue="${list.appraseridentify}"/>
              							</select>
                        			签名：${evaluatePersonName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        			日期：<input id="${list.apprasialid}_startDate" type="text" value="${list.signdate}" size="10" onClick="WdatePicker()"/> 
							   </td>
						</tr>
						<tr>
							<td colspan="2" id="${list.apprasialid}_td" style="background:#eee;width: 90%;">
								<input type="hidden" style="background: transparent;" class="fl wenbenkuang670" id="${list.apprasialid}_topic" onclick="changeBackgroundtd('${list.apprasialid}')"/>
								<div class="downbox mt18">
								<span class="fl wenben85left">内容描述：</span>
				 					 <textarea class="content"
						                id="${list.apprasialid}_content"
						                style="background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999;"
						                idvalue="${list.apprasial}"  onfocus="turnon('${list.apprasialid}',this)" onblur="turnoff('${list.apprasialid}',this)"
						                onclick="changeBackgroundtd('${list.apprasialid}');"
						                >${list.apprasial}</textarea>
								</div>
								<div id="${list.apprasialid}_attach">
										<%int j=1;%>
									<c:forEach items="${list.attachListForFile}" var="attachFile">
										<div class="downbox mt18" id="${attachFile.attachid}_attachment">
		            					<span class="fl wenben85left">附件<%=j %>：&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" style="color:black;" onclick="dodown('${attachFile.attachid}','${list.apprasialid}')">${attachFile.filename}</a>
		            					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteselfattach('${attachFile.attachid}','${list.apprasialid}')"><img src="${ctx}/images/upload_del.gif" style="height:10px; width:10px; "border="0"/></img></a>
		            					</span>
			            						<%-- ${attachFile.filename}
			            						<input type="button" value="下 载" class="shangchuan fl ml10"  onclick="dodown('${attachFile.attachid}')"/> 
												<input type="button" value="删 除" class="shangchuan fl ml10" onclick="deleteselfattach('${attachFile.attachid}','${list.apprasialid}')"/> --%>
	            						<%j++;%>
	            						</div>
            						</c:forEach>
            					</div>	
								<div class="downbox mt18" style="position: relative;">
									<form method="post" id="${list.apprasialid}_frm">
									<input type="hidden" name="${list.apprasialid}_old_uuid" id="${list.apprasialid}_old_uuid" value="${uuid}"/>
									<input type="hidden" name="${list.apprasialid}_rpID" id="${list.apprasialid}_rpID" value="${list.apprasialid}"/>
									<span class="fl wenben85left">附件：</span> 
									<%-- <input name='${list.apprasialid}_textfield' id='${list.apprasialid}_textfield' type="text"
										class="wenbenkuang670 fujian"  style="position:absolute;background: transparent;left:80px;"
										value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" readonly/>
									<span class="doupload">
									<input type="file" class="fileadd2" size="28" name="${list.apprasialid}_fileField" id="${list.apprasialid}_fileField" style="margin-left:130px"
									onchange="document.getElementById('${list.apprasialid}_textfield').value=this.value"/><span id="${list.apprasialid}_tp" style="margin-left:100px">0%</span>  
									<input type="button" value="添 加" style="margin-left:20px" class="shangchuan ml10" /> 
									<input type="button" value="上 传" id ="${list.apprasialid}_upload" onclick="return beforeSubmitForm('old_fileField','${list.apprasialid}_frm','${list.apprasialid}_old_uuid','${list.apprasialid}_tp','${list.apprasialid}');"
										class="shangchuan ml10" /> 
									</span> --%>
									<input name='${list.apprasialid}_fileField' id='${list.apprasialid}_fileField' type="file"
										  style="position:absolute;background: transparent;left:80px;" />
									<input id="${list.apprasialid}_upload" class="shangchuan ml10" type="button" value="上 传" 
									onclick="return beforeSubmitForm('old_fileField','${list.apprasialid}_frm','${list.apprasialid}_old_uuid','${list.apprasialid}_tp','${list.apprasialid}');"
										 style="position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px" />  
									<div style="position:absolute;left:420px;"><span id="${list.apprasialid}_tp">0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span style="font-size:10px;">格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>
									</form>
								</div>
							</td>
							<td width="10%" height="191" class="bg_eee" id="bg_eee_2_3">
							<input type="button" class="fr shanchu1" title="删除" id="${list.apprasialid}_delete2" style="margin:0px auto;float:none" onclick="deleteselfpackage(this)"/> 
							<input type="button" class="fr tianjia" title="保存" id="${list.apprasialid}_save" style="margin:0px auto;float:none" onclick="updateold('${list.apprasialid}')"/>
							</td>
						</tr>
						<%i++;%>
					</c:forEach>
					<tr id="addpackagelocation">
						<td colspan="3" class="bg_eee h80">
							<div class="fr btn">
								<input type="button" id = "add1" title="增加" onclick="addnew1()" style="margin-right :18px;" class="fr shanchu" />
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
			var this_div = document.getElementById(div);
			var type1 = "1";
			var type2 = "1";
			var name="特长与成果展示";
					if(document.getElementById(valuenum).value.length<=300){
						if(document.getElementById(valuenum).value=="最多输入300字"){
							document.getElementById(valuenum).value = "";
						}
						saveNow = 1;
						Ext.Ajax.request({
							url:'${ctx}/personality/PersonalityAction.a?insertSelfApp',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								var temp=response.responseText;
								var saveid = temp;
								if(response.responseText=="##"){
									text_style("new_content");
									alert_save_failure(name);
									saveNow = 0;
								}else{
									saveNow = 0;
									$("#new_fileField").attr('onclick','');
									$("#new_fileField").attr('onchange','');
									$("#new_upload").attr('onclick','');
									$('#new_content2').attr("id",saveid+"_content");
									$('#new_startDate2').attr("id",saveid+"_startDate");
									$('#new_person').attr("id",saveid+"_person");
									$('#new_td2').attr("id",saveid+"_td");
									$('#new_tr2').attr("id",saveid+"_tr");
									$('#new_num2').attr("id",saveid+"_num");
									$('#new_delete2').attr("id",saveid+"_delete");
									$('#new_save').attr("id",saveid+"_save");
									$("#new_fileField").attr("id",saveid+"_fileField");
									$("#new_textfield").attr("id",saveid+"_textfield");
									$("#new_upload").attr("id",saveid+"_upload");
									$("#new_tp").attr("id",saveid+"_tp");
									$("#new_attach").attr("id",saveid+"_attach");
									$("#new_frm").attr("id",saveid+"_frm");
									$("#new_uuid").attr("id",saveid+"_uuid");
									$("#new_rpID").attr("id",saveid+"_rpID");
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
											$("#"+saveid+"_content").val("最多输入300字")
											$("#"+saveid+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"});
										}
										beforeSubmitForm("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
									}
									if(check!="1"){
										$("#"+saveid+"_content").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
										document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
										alert_save_success(name,$("#"+saveid+"_num").html());
									}
									 saveNow = 0;
								}
							},
							params : {
								apprasial : document.getElementById(valuenum).value,
								personid : document.getElementById("new_person").value,
								evaluateType1 : type1,
								choicenum : document.getElementById("choicenum").value,
								evaluateType3 : document.getElementById("evaluateType3").value,
								termId : document.getElementById("termId").value,
								signdate : document.getElementById(day).value
							}
						}); 
					}else{
						apprasial_alert(300);
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
		 										    					url:'${ctx}/personality/PersonalityAction.a?saveFile',
		 										    					method:'POST',
		 										    					defaults:{autoScroll: true},
		 										    					success:function(response,options){
		 										    						if(response.responseText=="##"){
		 										    							alert_upload_failure("上传失败");
		 										    							$("#"+id+"_upload").attr("disabled",false);
		 																	}else{
		 										    						document.getElementById(id+"_attach").innerHTML = "";
		 										    						var rrid = id+"_attach";
		 										    						var temp=response.responseText;
		 										    					    var len=eval(temp);
		 										    					    var str="";
		 										    						for(var i=0;i<len[0].list2.length;++i)
		 										    						{
		 										    							if(len[0].list2[i].apprasialid == id){
		 										    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
		 										    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;" 
		 										    									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
		 										    									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
		 										    									+"</span></div>"
		 										    									/* + len[0].list2[i].attachListForFile[j].filename
		 										    									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\")'/>"
		 										    									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'/></div>"; */
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
		 																		/* $("#"+id+"_upload").attr('onclick','');
		 																		$("#"+id+"_upload").bind("click",function(){
		 																			return beforeSubmitForm("old_fileField",id+"_frm",id+"_old_uuid",id+"_tp",id);
		 																		}); */
		 																		$("#"+id+"_fileField").bind("change",function(){
		 																			document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
		 																		});
		 																		$("#"+id+"_upload").attr("disabled",false);
		 																	
		 										    					},
		 										    					params : {
		 										    						uuid : uuid,
		 										    						rpID : id,
		 										    						evaluateType1 : type1,
		 										    						evaluateType3 : document.getElementById("evaluateType3").value,
		 										    						attType : document.getElementById("evaluateType3").value,
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
		 							alert("特长与成果展示 栏目"+$("#"+id+"_num").html()+": 附件名重复!");	
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
	
	//下载附件
	function dodown(attachid,foreignKey)
	{
		var url="${ctx}/DownloadAttachAction.a?attachid="+attachid+"&&foreignKey="+foreignKey;
		document.location.replace(url);
	    return false;
	}
	function giveid(id){
		beforeid = id;
	document.getElementById(id).setAttribute("name","old_fileField");
	document.getElementById(id).setAttribute("id","old_fileField");
	}
	 
	function deleteselfpackage(obj){
		var name="特长与成果展示";
		/* var id = window.event.srcElement.getAttribute('id'); */
		var id=$(obj).attr("id");
		var arr = new Array();
		arr = id.split("_");
		var idd = arr[0];
		  var r=apprasial_delete()
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
								url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfAppWithatt',
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
											 	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
												+"提供人："
												+"<select name='new_person' id='new_person' >"
												+"<option value='1'>本人&nbsp;&nbsp;&nbsp;&nbsp;</option>"
												+"<option value='2'>同学&nbsp;&nbsp;&nbsp;&nbsp;</option>"
												+"<option value='3'>教师&nbsp;&nbsp;&nbsp;&nbsp;</option>"
												+"<option value='4'>班主任&nbsp;&nbsp;&nbsp;&nbsp;</option>"
												+"<option value='5'>学生家长&nbsp;&nbsp;&nbsp;&nbsp;</option>"
												+"</select>"
												+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
												+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
												+"日期：<input id='new_startDate2' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
												+"</td></tr><tr><td colspan='2' id='new_td2' class='h50' style='width: 90%;'>"
											 	+"<input type='hidden' class='fl wenbenkuang670' id='new_topic2'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' />"
											 	+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content2' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content2"+"\",this)' onblur='turnoff(\""+"new_content2"+"\",this)'>最多输入300字</textarea></div>"
											 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
											 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
											 	+"<input type='hidden' name='new_uuid' id='new_uuid'/><input type='hidden' name='new_rpID' id='new_rpID' />"
											 	+"<span class='fl wenben85left'>附件：</span>"
											 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
											 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload()' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
											 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
											 	+"</form></div></td><td width='100' height='191' class='bg_eee'>"
											 	+"<input type='button' class='fr shanchu1' title='删除' style='margin:0px auto;float:none' id='new_delete2' onclick='deleteselfpackage(this)'/> "
											 	+"<input type='button' class='fr tianjia' title='保存' style='margin:0px auto;float:none' id='new_save' onclick='check(\""+"down"+"\",\""+"new_startDate2"+"\",\""+"new_content2"+"\",\""+"new_rpID"+"\",\""+"new_topic2"+"\",this)'/>"
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
									id : idd,
									termId : document.getElementById("termId").value,
									deleteType : "7050"
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
		  var r=apprasial_delete()
		  if (r==true){
			  var type1 = "1";
				var type2 = "1";
				
			  Ext.Ajax.request({
					url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfAttachApp',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						if(response.responseText=="1"){
							alert_delete_failure("附件")	
						}else{
							alert_delete_success("附件 ")
							document.getElementById(rid+"_attach").innerHTML = "";
  						var temp=response.responseText;
  					    var len=eval(temp);
  					    var str="";
  						for(var i=0;i<len[0].list2.length;++i)
  						{
  							if(len[0].list2[i].apprasialid == rid){
  								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
  									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+"：&nbsp;&nbsp;&nbsp;" 
  									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
  									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
									+"</span></div>"
  									/* + len[0].list2[i].attachListForFile[j].filename
  									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\")'/>"
  									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'/></div>"; */
	    							}
  							}
  						}
  						document.getElementById(rid+"_attach").innerHTML = str;
						}
					},
					params : {
						attachid : id,
						apprasialid : rid,
						evaluateType1 : type1,
						termId : document.getElementById("termId").value,
						evaluateType3 : document.getElementById("evaluateType3").value,
						attType : document.getElementById("evaluateType3").value
					}
				}); 
		  }
		  else{
			  return false;
		  }
	}
	
	
 	function addnew1(){
 		var width = $("#bg_eee_2_3").attr("width");
 		if(document.getElementById("new_tr2")==undefined||document.getElementById("new_tr2")==null){
 			var div = document.getElementById("new_down");  
			var tr = div.getElementsByTagName("tr").length;
			var tr_num=Math.floor(tr/2);
			 var str="";
			 str+="<tr id='new_tr2'><td width='10%' height='40' id='new_num2' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
			 	+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+"提供人："
				+"<select name='new_person' id='new_person' >"
				+"<option value='1'>本人&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='2'>同学&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='3'>教师&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='4'>班主任&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"<option value='5'>学生家长&nbsp;&nbsp;&nbsp;&nbsp;</option>"
				+"</select>"
				+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+"日期：<input id='new_startDate2' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
				+"</td></tr><tr><td colspan='2' id='new_td2' class='h50' style='width: 90%;'>"
			 	+"<input type='hidden' class='fl wenbenkuang670' id='new_topic2'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' />"
			 	+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content2' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content2"+"\",this)' onblur='turnoff(\""+"new_content2"+"\",this)'>最多输入300字</textarea></div>"
			 	+"<div id='new_attach'><div class='downbox mt18' id='new_attachment'></div></div>"
			 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
			 	+"<input type='hidden' name='new_uuid' id='new_uuid'/><input type='hidden' name='new_rpID' id='new_rpID' />"
			 	+"<span class='fl wenben85left'>附件：</span>"
			 	+"<input name='new_fileField' id='new_fileField' type='file' style='position:absolute;background: transparent;left:80px;' />"
			 	+"<input id='new_upload' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload()' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
			 	+"<div style='position:absolute;left:420px;'><span id='new_tp'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
			 	+"</form></div></td><td width='"+width+"' height='191' class='bg_eee' id='bg_eee_2_3'>"
			 	+"<input type='button' class='fr shanchu1' title='删除' style='margin:0px auto;float:none' id='new_delete2' onclick='deleteselfpackage(this)'/> "
			 	+"<input type='button' class='fr tianjia' title='保存' style='margin:0px auto;float:none' id='new_save' onclick='check(\""+"down"+"\",\""+"new_startDate2"+"\",\""+"new_content2"+"\",\""+"new_rpID"+"\",\""+"new_topic2"+"\",this)'/>"
			 	+"</td></tr>"
			 $("#addpackagelocation").before(str);
 		}
	} 
	function check(div,day,valuenum,rid,zhuti,obj){
	/* 	var id = window.event.srcElement.getAttribute('id'); */
		var id=$(obj).attr("id");
		var arr = new Array();
		arr = id.split("_");
		var idi = arr[0];
		var name="特长与成果展示";
		if(idi!="new"){
			var type1 = "1";
			var type2 = document.getElementById("evaluateType2").value;
					if(document.getElementById(idi+"_content").value.length<=300){
						if(document.getElementById(idi+"_content").value=="最多输入300字"){
							document.getElementById(idi+"_content").value = "";
						}
						var v_url='${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess'
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
									$("#"+idi+"_content").bind("focus",function(){
										changeBackgroundtd(idi);
									});
								/* 	$("#"+idi+"_content").bind("blur",function(){
										returnBackgroundtd(idi);
									}); */
									$("#"+idi+"_content").removeAttr("style"); 
									 $("#"+idi+"_content").css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"}); 
									alert_save_success(name,$("#"+idi+"_num").html());
								}
							},
							params : {
								id : idi,
								apprasial : document.getElementById(idi+"_content").value,
								personid : document.getElementById(idi+"_person").value,
								choicenum : document.getElementById("choicenum").value,
								updateType : "7050",
								evaluateType1 : type1,
								evaluateType3 : document.getElementById("evaluateType3").value,
								termId : document.getElementById("termId").value,
								signdate : document.getElementById(idi+"_startDate").value
							}
						});
					}else{
						apprasial_alert(300);
					}
		}else{
			savepackage(div,day,valuenum,rid,zhuti,"0");
		}
	}
	
	function updateold(id){
			var this_div = document.getElementById(id + "_content");
			var name="特长与成果展示";
			if(this_div.value.length<=300){
				if(this_div.value=="最多输入300字"){
				this_div.value = "";
				}
				var v_url='${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess'
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
								apprasial : this_div.value,
								choicenum : document.getElementById("choicenum").value,
								updateType : "7050",
								termId : document.getElementById("termId").value,
								evaluateType3 : document.getElementById("evaluateType3").value,
								personid : document.getElementById(id+"_person").value,
								signdate : document.getElementById(id+"_startDate").value
							}
						});
			}else{
				apprasial_alert(300);
			}
	}
	function changeBackgroundtd(id)
	{
		document.getElementById(id+"_td").style.backgroundColor ="";
		$("#"+id+"_content").css({background:"transparent",color:"black",float:"left",cursor:"text"});
		if($("#"+id+"_content").val() !="最多输入300字"){
			$("#"+id+"_content").css({background:"transparent",color:"black",float:"left",cursor:"text"});
		}
		if($("#"+id+"_content").is(":focus")){
			flag = false; 
			 } 
		if(flag&&$("#"+id+"_content").val() ==""){
			$("#"+id+"_content").val("最多输入300字");
		 	$("#"+id+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"}); 
		}
	}
	function returnBackgroundtd(id)
	{
		document.getElementById(id+"_td").style.backgroundColor ="#eee";
	}
	//更改学期刷新页面
	function changeterm(){
		ShowDiv();
		var term = document.getElementById("termId").value;
		url="${ctx}/selfappraise/SelfAppAction.a?choicenum="+${choicenum}+"&termId1="+term;
		document.location.replace(url);
	}
	function checkupload(){
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
	}
	

	function turnon(id,obj){
		/* var idd = window.event.srcElement.getAttribute('id'); */
			var idd=$(obj).attr("id");
		if($("#"+idd).val()=="最多输入300字"){
			$("#"+idd).removeAttr("style"); 
		 	$("#"+idd).css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"}); 
			$("#"+idd).val("");
		}
	}
	function turnoff(id,obj){
		/* var idd = window.event.srcElement.getAttribute('id'); */
			var idd=$(obj).attr("id");
		if ($("#"+idd).val() ==""){
			$("#"+idd).val("最多输入300字");
		$("#"+idd).css({color:"#BCBCBC",float:"left",cursor:"text"}); 
		}
	}
//-----------------------------------------------------------------------------------

var beforeid1="";
var interdelete1 = 0;
var saveNow1 = 0;
var isSave1 = 0;
var isDelete1 = 0;
var deletecid1 ="";


	//新增保存记录袋
	 function savepackage1(div,day,valuenum,rid,zhuti,check){
		if(saveNow1 == 0){
			var this_div = document.getElementById(div);
			var type1 = "1";
			var type2 = "1";
			var name="个性发展过程";
					if(document.getElementById(valuenum).value.length<=300){
						if(document.getElementById(valuenum).value=="最多输入300字"){
							document.getElementById(valuenum).value = "";
						}
						saveNow1 = 1;
						Ext.Ajax.request({
							url:'${ctx}/personality/PersonalityAction.a?insertSelfApp',
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								var temp=response.responseText;
								var saveid = temp;
								if(response.responseText=="##"){
									text_style("new_content3");
									alert_save_failure(name);
									saveNow1 = 0;
								}else{
									saveNow1 = 0;
									$("#new_fileField3").attr('onclick','');
									$("#new_fileField3").attr('onchange','');
									$("#new_upload3").attr('onclick','');
									$('#new_content3').attr("id",saveid+"_content");
									$('#new_startDate3').attr("id",saveid+"_startDate");
									$('#new_td3').attr("id",saveid+"_td");
									$('#new_tr3').attr("id",saveid+"_tr");
									$('#new_num3').attr("id",saveid+"_num");
									$('#new_delete3').attr("id",saveid+"_delete");
									$('#new_save3').attr("id",saveid+"_save");
									$("#new_fileField3").attr("id",saveid+"_fileField");
									$("#new_textfield3").attr("id",saveid+"_textfield");
									$("#new_upload3").attr("id",saveid+"_upload");
									$("#new_tp3").attr("id",saveid+"_tp");
									$("#new_attach3").attr("id",saveid+"_attach");
									$("#new_frm3").attr("id",saveid+"_frm");
									$("#new_uuid3").attr("id",saveid+"_uuid");
									$("#new_rpID3").attr("id",saveid+"_rpID");
									$("#"+saveid+"_fileField").attr('onclick','');
									$("#"+saveid+"_fileField").attr('onchange','');
									$("#"+saveid+"_upload").attr('onclick',''); 
									$("#"+saveid+"_upload").bind("click",function(){
										return beforeSubmitForm1("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
									});
									$("#"+saveid+"_fileField").bind("change",function(){
										document.getElementById(saveid+"_textfield").value=document.getElementById(saveid+"_fileField").value
									}); 
									if(check!="0"){
										if($("#"+saveid+"_content").val()==""){
											$("#"+saveid+"_content").val("最多输入300字")
											$("#"+saveid+"_content").css({color:"#BCBCBC",float:"left",cursor:"text"});
										}
										beforeSubmitForm1("old_fileField",saveid+"_frm",saveid+"_old_uuid",saveid+"_tp",saveid);
									}
									if(check!="1"){
										$("#"+saveid+"_content").bind("focus",function(){
											changeBackgroundtd(saveid);
										});
									/* 	$("#"+saveid+"_content").bind("blur",function(){
											returnBackgroundtd(saveid);
										}); */
										document.getElementById(saveid+"_td").style.backgroundColor ="#eee";
										alert_save_success(name,$("#"+saveid+"_num").html());
									}
									/* $("#"+saveid+"_content").removeAttr("style"); 
									 $("#"+saveid+"_content").css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"});  */
									 saveNow1 = 0;
								}
							},
							params : {
								apprasial : document.getElementById(valuenum).value,
								evaluateType1 : type1,
								choicenum : document.getElementById("choicenum").value,
								evaluateType2 : document.getElementById("evaluateType2").value,
								termId : document.getElementById("termId").value,
								signdate : document.getElementById(day).value
							}
						}); 
					}else{
						apprasial_alert(300);
					}
		}else{
			/* if(check!=1){
			alert("正在保存中...");
			} */
		}
	}
		var interval;  
		/**
		 * 附件的上传
		 */
		function beforeSubmitForm1(name,formid,uui,load,id){
			/*  var Sys = {};    
			 var ua = navigator.userAgent.toLowerCase();    
			 var s;    
			 (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1]:
			 (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
			 (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 0;
			 
			 if (Sys.ie){
			 		if(Sys.ie<7.0){
						alert('请使用IE9~IE11浏览器上传附件，否则无法上传');
						return;
			 		}else{ */
			 			beforeid1 = id+"_fileField";
						 $("#"+beforeid1).attr("name","old_fileField");
						 $("#"+beforeid1).attr("id","old_fileField");
							$("#"+id+"_upload").attr("disabled",true);
							var isrepeat = 0;
						var arr = new Array();
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
													    					url:'${ctx}/personality/PersonalityAction.a?saveFile',
													    					method:'POST',
													    					defaults:{autoScroll: true},
													    					success:function(response,options){
													    						if(response.responseText=="##"){
													    							alert_upload_failure("上传失败");
													    							$("#"+id+"_upload").attr("disabled",false);
																				}else{
													    						document.getElementById(id+"_attach").innerHTML = "";
													    						var rrid = id+"_attach";
													    						var temp=response.responseText;
													    					    var len=eval(temp);
													    					    var str="";
													    						for(var i=0;i<len[0].list2.length;++i)
													    						{
													    							if(len[0].list2[i].apprasialid == id){
													    								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
													    									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													    									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
													    									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach1(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
													    									+"</span></div>"
													    									/* + len[0].list2[i].attachListForFile[j].filename
													    									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\")'/>"
													    									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach1(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'/></div>"; */
														    							}
													    							}
													    							
													    						}
													    						document.getElementById(id+"_attach").innerHTML = str;
													    						alert_upload_success("上传已完成");	$("#"+id+"_upload").attr("disabled",false);
																				}
													    						bar.innerHTML = "0%"; 
													    						//document.getElementById(id+"_textfield").value = "格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx";
													    						 $("#"+id+"_fileField").attr('onclick','');
																					$("#"+id+"_fileField").attr('onchange','');
																					/* $("#"+id+"_upload").attr('onclick','');
																					$("#"+id+"_upload").bind("click",function(){
																						return beforeSubmitForm1("old_fileField",id+"_frm",id+"_old_uuid",id+"_tp",id);
																					}); */
																					$("#"+id+"_fileField").bind("change",function(){
																						document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
																					});
																					$("#"+id+"_upload").attr("disabled",false);
													    					},
													    					params : {
													    						uuid : uuid,
													    						rpID : id,
													    						evaluateType1 : type1,
													    						evaluateType2 : document.getElementById("evaluateType2").value,
													    						attType : document.getElementById("evaluateType2").value,
													    						termId : document.getElementById("termId").value
													    					}
													    				}); 
													                 	$('#old_fileField').attr("id",beforeid1);
													            	}
																}else{
																	if(json.state != "error"){
																		alert("附件大小不能超过1M");
										  		            		 	$("#"+id+"_fileField").attr('onclick','');
																		$("#"+id+"_fileField").attr('onchange','');
																		$("#"+id+"_fileField").bind("change",function(){
																			document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
																		});
										  		            			$('#old_fileField').attr("id",beforeid1);
										  		            			bar.innerHTML = "0%";
										  		            			window.clearTimeout(interval);  
										  		            			$("#"+id+"_fileField").attr('onclick','');
																		$("#"+id+"_fileField").attr('onchange','');
																		$("#"+id+"_fileField").bind("change",function(){
																			document.getElementById(id+"_textfield").value=document.getElementById(id+"_fileField").value
																		});
																		if($('#old_fileField')!=null&&$('#old_fileField')!=""&&$('#old_fileField')!=undefined){
																			$('#old_fileField').attr("id",beforeid1);
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
										alert("个性发展过程 栏目:"+$("#"+id+"_num").html()+" 附件名重复!");	
										$("#"+id+"_upload").attr("disabled",false);
										$('#old_fileField').attr("id",beforeid1);
										}
									}else{
										alert("上传文件格式不正确");
										$("#"+id+"_upload").attr("disabled",false);
										$('#old_fileField').attr("id",beforeid1);
									}
								}else{
									alert("上传文件名不能超过25个字!");
									$("#"+id+"_upload").attr("disabled",false);
									$('#old_fileField').attr("id",beforeid1);
								}
							}else{
								alert("请选择要上传的文件！");
								$("#"+id+"_upload").attr("disabled",false);
								$('#old_fileField').attr("id",beforeid1);
							}
			 		/* }
			 	}else{
					alert('请使用IE9~IE11浏览器上传附件，否则无法上传');
					return;
			 	}  */
		}
		function deleteselfpackage1(obj){
			var name="个性发展过程";
		/* 	var id = window.event.srcElement.getAttribute('id'); */
			var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idd = arr[0];
			  var r=apprasial_delete()
			  if (r==true){
				  if(saveNow1==1){
					 /*  alert("正在保存中..."); */
				  }else{
					  var type1 = "1";
					  var type2 = "1";
					  type2 = document.getElementById("evaluateType2").value;
					  if(idd=="new"){
							var tr = $("#new_table3").find("tr");
					    	if(tr.size()!=4){
					    		if(document.getElementById("new_tr3")!=null||document.getElementById("new_tr3")!=undefined){
									$("#new_tr3").next().remove();    
									$("#new_tr3").remove();
								}
					    	}else{
					    		apprasial_del_Finish();
					    	}
						}else{
							 Ext.Ajax.request({
									url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfAppWithatt',
									method:'POST',
									defaults:{autoScroll: true},
									success:function(response,options){
										if(response.responseText=="1"){
											text_style(idd+"_content");
											alert_delete_failure(name);
										}else{
											var tr = $("#new_table3").find("tr");
											if(tr.size()==4){
												$("#"+idd+"_tr").next().remove();    
												$("#"+idd+"_tr").remove();
												var str="";
												 str+="<tr id='new_tr3'><td width='10%' height='40' id='new_num3' class='bg_eee'>1</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
													+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
													+"日期：<input id='new_startDate3' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
													+"</td></tr><tr><td colspan='2' id='new_td3' class='h50' style='width: 90%;'>"
												 	+"<input type='hidden' class='fl wenbenkuang670' id='new_topic3'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' />"
												 	+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content3' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content3"+"\",this)' onblur='turnoff(\""+"new_content3"+"\",this)'>最多输入300字</textarea></div>"
												 	+"<div id='new_attach3'><div class='downbox mt18' id='new_attachment'></div></div>"
												 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
												 	+"<input type='hidden' name='new_uuid3' id='new_uuid3'/><input type='hidden' name='new_rpID3' id='new_rpID3' />"
												 	+"<span class='fl wenben85left'>附件：</span>"
												 	+"<input name='new_fileField3' id='new_fileField3' type='file' style='position:absolute;background: transparent;left:80px;' />"
												 	+"<input id='new_upload3' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload1()' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
												 	+"<div style='position:absolute;left:420px;'><span id='new_tp3'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
												 	+"</form></div></td><td width='100' height='191' class='bg_eee'>"
												 	+"<input type='button' class='fr shanchu1' title='删除' style='margin:0px auto;float:none' id='new_delete3' onclick='deleteselfpackage1(this)'/> "
												 	+"<input type='button' class='fr tianjia' title='保存' style='margin:0px auto;float:none' id='new_save3' onclick='check1(\""+"down"+"\",\""+"new_startDate3"+"\",\""+"new_content3"+"\",\""+"new_rpID3"+"\",\""+"new_topic3"+"\",this)'/>"
												 	+"</td></tr>"
												 $("#addpackagelocation3").before(str);
											}else{
												 $("#"+idd+"_tr").next().remove();    
													$("#"+idd+"_tr").remove(); 
													//重新对评价排序
													var td = $("#new_table3").find("td");
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
										id : idd,
										termId : document.getElementById("termId").value,
										deleteType : "7040"
									}
								}); 
						}
				  }
			  }
			  else{
				  return false;
			  }
		}
		
		
		function deleteselfattach1(id,rid){
			  var r=apprasial_delete()
			  if (r==true){
				  var type1 = "1";
					var type2 = "1";
				  Ext.Ajax.request({
						url:'${ctx}/selfappraise/SelfAppAction.a?deleteSelfAttachApp',
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
	  							if(len[0].list2[i].apprasialid == rid){
	  								for(var j=0;j<len[0].list2[i].attachListForFile.length;++j){
	  									str +="<div class='downbox mt18' id=\""+len[0].list2[i].attachListForFile[j].attachid+"_attachment\"><span class='fl wenben85left'>附件"+(j+1)+":&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
	  									+"<a href='javascript:void(0);' style='color:black;' onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'>"+len[0].list2[i].attachListForFile[j].filename+"</a>"
	  									+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' title='删除' onclick='deleteselfattach1(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'><img src='${ctx}/images/upload_del.gif' style='height:10px; width:10px;' border='0'/></img></a>"
    									+"</span></div>"
	  									/* + len[0].list2[i].attachListForFile[j].filename
	  									+"<input type='button' value='下 载' class='shangchuan fl ml10'  onclick='dodown(\""+len[0].list2[i].attachListForFile[j].attachid+"\")'/>"
	  									+"<input type='button' value='删 除' class='shangchuan fl ml10' onclick='deleteselfattach1(\""+len[0].list2[i].attachListForFile[j].attachid+"\",\""+len[0].list2[i].apprasialid+"\")'/></div>"; */
		    							}
	  							}
	  						}
	  						document.getElementById(rid+"_attach").innerHTML = str;
							}
						},
						params : {
							attachid : id,
							apprasialid : rid,
							evaluateType1 : type1,
							termId : document.getElementById("termId").value,
							evaluateType2 : document.getElementById("evaluateType2").value,
							attType : document.getElementById("evaluateType2").value
						}
					}); 
			  }
			  else{
				  return false;
			  }
		}
		
		
		function addnew3(){
			var width = $("#bg_eee_2_2").attr("width");
	 		if(document.getElementById("new_tr3")==undefined||document.getElementById("new_tr3")==null){
	 			var div = document.getElementById("new_top_down");  
				var tr = div.getElementsByTagName("tr").length;
				var tr_num=Math.floor(tr/2);
				 var str="";
				 str+="<tr id='new_tr3'><td width='10%' height='40' id='new_num3' class='bg_eee'>"+tr_num+"</td><td colspan='2' class='youjuzhong pr20 bg_eee'>"
					+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"日期：<input id='new_startDate3' type='text' size='10' value='${nowDate}' onClick='WdatePicker()'/>"
					+"</td></tr><tr><td colspan='2' id='new_td3' class='h50' style='width: 90%;'>"
				 	+"<input type='hidden' class='fl wenbenkuang670' id='new_topic3'  style='background: transparent; float:left;color:#BCBCBC;cursor:text;' />"
				 	+"<div class='downbox mt18'><span class='fl wenben85left'>内容描述：</span><textarea id='new_content3' style='background: transparent; overflow:auto; height: 130px; width: 99%; border:1px solid #999; float:left;color:#BCBCBC;cursor:text;' onfocus='turnon(\""+"new_content3"+"\",this)' onblur='turnoff(\""+"new_content3"+"\",this)'>最多输入300字</textarea></div>"
				 	+"<div id='new_attach3'><div class='downbox mt18' id='new_attachment'></div></div>"
				 	+"<div class='downbox mt18' style='position: relative;'><form  method='post' id='new_frm'>"
				 	+"<input type='hidden' name='new_uuid3' id='new_uuid3'/><input type='hidden' name='new_rpID3' id='new_rpID3' />"
				 	+"<span class='fl wenben85left'>附件：</span>"
				 	+"<input name='new_fileField3' id='new_fileField3' type='file' style='position:absolute;background: transparent;left:80px;' />"
				 	+"<input id='new_upload3' class='shangchuan ml10' type='button' value='上 传' onclick='checkupload1()' style='position:absolute;left:320px;border-bottom: 1px solid #000;border-right: 1px solid #000;border-top: 1px solid #D7D7D7;border-left: 1px solid #D7D7D7;color:#272222;background-color:#279F46;height:20px' />"
				 	+"<div style='position:absolute;left:420px;'><span id='new_tp3'>0%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style='font-size:10px;'>格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx</span></div>"
				 	+"</form></div></td><td height='191' width='"+width+"' class='bg_eee' id='bg_eee_2_2'>"
				 	+"<input type='button' class='fr shanchu1' title='删除' id='new_delete3' style='margin:0px auto;float:none' onclick='deleteselfpackage1(this)'/> "
				 	+"<input type='button' class='fr tianjia' title='保存' id='new_save3' style='margin:0px auto;float:none' onclick='check1(\""+"down"+"\",\""+"new_startDate3"+"\",\""+"new_content3"+"\",\""+"new_rpID3"+"\",\""+"new_topic3"+"\",this)'/>"
				 	+"</td></tr>"
				 $("#addpackagelocation3").before(str);
	 		}
		} 
		
		function check1(div,day,valuenum,rid,zhuti,obj){
			/* var id = window.event.srcElement.getAttribute('id'); */
				var id=$(obj).attr("id");
			var arr = new Array();
			arr = id.split("_");
			var idi = arr[0];
			var name="个性发展过程"
			if(idi!="new"){
				var type1 = "1";
				var type2 = document.getElementById("evaluateType2").value;
						if(document.getElementById(idi+"_content").value.length<=300){
							if(document.getElementById(idi+"_content").value=="最多输入300字"){
								document.getElementById(idi+"_content").value = "";
							}
							var v_url='${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess'
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
											 updateold1(idi)
											});
										$("#"+idi+"_content").bind("focus",function(){
											changeBackgroundtd(idi);
										});
										/* $("#"+idi+"_content").bind("blur",function(){
											returnBackgroundtd(idi);
										}); */
										$("#"+idi+"_content").removeAttr("style"); 
										 $("#"+idi+"_content").css({background:"transparent", height:"130px", width:"99%", border:"1px solid #999"}); 
										alert_save_success(name,$("#"+idi+"_num").html());
									}
								},
								params : {
									id : idi,
									apprasial : document.getElementById(idi+"_content").value,
									evaluateType1 : type1,
									choicenum : document.getElementById("choicenum").value,
									updateType : "7040",
									evaluateType2 : document.getElementById("evaluateType2").value,
									termId : document.getElementById("termId").value,
									signdate : document.getElementById(idi+"_startDate").value
								}
							});
						}else{
							apprasial_alert(300);
						}
			}else{
				savepackage1(div,day,valuenum,rid,zhuti,"0");
			}
		}
		function checkupload1(){
			if(document.getElementById("new_fileField3").value!=""){
				var paths = new Array();
				paths = $("#new_fileField3").val().split("\\");
				var path_name = new Array();
				path_name = paths[paths.length-1].split(".");
				var pname = path_name[0];
				if(pname.length<=25){
				savepackage1('down','new_startDate3','new_content3','new_rpID3','new_topic3','1');
				}else{
					alert("上传文件名不能超过25个字!");
				}
			}else{
				alert("请选择要上传的文件！");
			}
		}
		
		function updateold1(id){
			var this_div = document.getElementById(id + "_content");
			var name="个性发展过程"
			if(this_div.value.length<=300){
				if(this_div.value=="最多输入300字"){
				this_div.value = "";
				}
				var v_url='${ctx}/selfappraise/SelfAppAction.a?doUpdataSelfProcess'
					  Ext.Ajax.request({
							url : v_url,
							method:'POST',
							defaults:{autoScroll: true},
							success:function(response,options){
								if(response.responseText=="##"){
									text_style(idi+"_content");
									alert_update_failure(name);
								}else{
									document.getElementById(id+"_td").style.backgroundColor ="#eee";
									alert_update_success(name,$("#"+id+"_num").html())
								}
							},
							params : {
								id : id,
								apprasial : this_div.value,
								choicenum : document.getElementById("choicenum").value,
								updateType : "7040",
								termId : document.getElementById("termId").value,
								signdate : document.getElementById(id+"_startDate").value
							}
						});
			}else{
				apprasial_alert(300);
			}
	}
		//---------------------------------------------------------------------
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
								updateType : "7020",
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
							updateType : "7020",
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
									/* if(interdelete == 0){ */
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
			 var width = $("#bg_eee_2_1").attr("width");
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
					+"</td><td  class='bg_eee' width='"+width+"' id='bg_eee_2_1'><span class='fr btn'  style='margin:0px auto;float:none'><input type='button' class='fr shanchu1' style='margin:0px auto;float:none' title='删除' id='new_delete1' onclick='deleteselfapp(this)'/></span></td></tr>";
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
														checksave("new_startDate11","new_content11","#new_content11");
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
										deleteType : "7020"
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
								deleteType : "7020"
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
//-----------------------------------------------------------
 var isSavejbqk = 0;
function savejbqk(){

	if(isSavejbqk == 0){
		isSavejbqk = 1;			
		if((document.getElementById("new_personalty1").value.length<=4000)&&(document.getElementById("new_personalty2").value.length<=4000)&&(document.getElementById("new_personalty3").value.length<=4000)){
			if(document.getElementById("new_personalty1").value=="最多输入4000字"){
				document.getElementById("new_personalty1").value=""
			}
			if(document.getElementById("new_personalty2").value=="最多输入4000字"){
				document.getElementById("new_personalty2").value=""
			}
			if(document.getElementById("new_personalty3").value=="最多输入4000字"){
				document.getElementById("new_personalty3").value=""
			}
			$("#new_personalty1").attr("disabled",true);
			$("#new_personalty2").attr("disabled",true);
			$("#new_personalty3").attr("disabled",true);
			Ext.Ajax.request({
				url:'${ctx}/personality/PersonalityAction.a?doSave',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					isSavejbqk = 0;			
					var temp=response.responseText;	
					var len=eval(temp);	
					document.getElementById("top1").innerHTML = "";	
					 var str = "<div id='old_top_jbqk'>";
						str += "<table width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge'><tr class='title_bg'>"
							+"<td colspan='4'>个性发展基本情况<span class='red'>*</span></td>"
							+"</tr><tr><td height='40' colspan='4' class='youjuzhong bg_eee'>"
							+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "1"){
									str += "日期：<span class='youjuzhong pr20 bg_eee'><input id='old_startDate_jbqk'  idvalue = '' type='text' size='10' value=\""+len[0].list2[i].signdate+"\" onClick='WdatePicker()' />";
								}
							}
							str += "</span></td></tr><tr><td class='th' width='10%'>二级指标</td><td class='th' width='10%'>三级指标</td><td class='th' width='70%'>个性发展记录</td><td class='th' width='10%'>操作</td></tr>"
							+"<tr><td height='191' class='h50 th'>特长</td><td class='h50 th'>学科特长<br /> 体育运动特长<br /> 艺术特长</td><td class='h50' id='old_td11'  style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> ";
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "1"){
									str += "<input id='old_jbqk1' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty1' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%; ' onfocus='turnon1(\""+"old_personalty1"+"\",this)' onblur='turnoff1(\""+"old_personalty1"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;'  onfocus='turnon1(\""+"old_personalty1"+"\",this)' onblur='turnoff1(\""+"old_personalty1"+"\",this)'>"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
							}
							str += "</span></td><td rowspan='3' class='bg_eee' id='bg_eee_2_4'><input type='button' class='fr tianjia' style='margin-right:18px;' onclick='updatejbqk()'/></td>"
							    +"</tr><tr><td height='191' class='h50 th'>有新意的成果</td><td class='h50 th'>活动成果<br /> 设计成果<br /> 制作成果</td><td class='h50' id='old_td22' style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> "
						    for(var i=0;i<len[0].list2.length;++i){
							    if(len[0].list2[i].indexid == "2"){
								str += "<input id='old_jbqk2' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty2' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty2"+"\",this)' onblur='turnoff1(\""+"old_personalty2"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;'  onfocus='turnon1(\""+"old_personalty2"+"\",this)' onblur='turnoff1(\""+"old_personalty2"+"\",this)'>"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
						    }
							str += "</span></td></tr><tr><td height='191' class='h50 th'>其他</td><td class='h50 th'>自主选择</td><td class='h50' id='old_td33' style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> ";
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "3"){
									str += "<input id='old_jbqk3' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty3' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty3"+"\",this)' onblur='turnoff1(\""+"old_personalty3"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty3"+"\",this)' onblur='turnoff1(\""+"old_personalty3"+"\",this)' >"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
							}
							str +="</span></td></tr></table></div>"
						document.getElementById("top1").innerHTML = str;
						document.getElementById("old_startDate_jbqk").setAttribute("idvalue",document.getElementById("old_startDate_jbqk").value);
						alert_save_success("个性发展","")
						$("#old_td11").css("background-color","#eee");
						$("#old_td22").css("background-color","#eee");
						$("#old_td33").css("background-color","#eee");
						$("#old_personalty1").attr("disabled",false);
						$("#old_personalty2").attr("disabled",false);
						$("#old_personalty3").attr("disabled",false);
				},
				params : {
					developmentrd1 : document.getElementById("new_personalty1").value,
					signdate : document.getElementById("new_startDate_jbqk").value,
					developmentrd2 : document.getElementById("new_personalty2").value,
					developmentrd3 : document.getElementById("new_personalty3").value,
					termId : document.getElementById("termId").value
				}
			}); 
		}else{
			$("#old_personalty1").attr("disabled",false);
			$("#old_personalty2").attr("disabled",false);
			$("#old_personalty3").attr("disabled",false);
			isSavejbqk = 0;	
			apprasial_alert(4000);
		}
	}else{
		
	}
}
function updatejbqk(){
	
	var tdWidth = $("#bg_eee_2_4").attr("width");
	if((document.getElementById("old_personalty1").value.length<=4000)&&(document.getElementById("old_personalty2").value.length<=4000)&&(document.getElementById("old_personalty3").value.length<=4000)){
		if(document.getElementById("old_personalty1").value=="最多输入4000字"){
			document.getElementById("old_personalty1").value=""
		}
		if(document.getElementById("old_personalty2").value=="最多输入4000字"){
			document.getElementById("old_personalty2").value=""
		}
		if(document.getElementById("old_personalty3").value=="最多输入4000字"){
			document.getElementById("old_personalty3").value=""
		}
		$("#old_personalty1").attr("disabled",true);
		$("#old_personalty2").attr("disabled",true);
		$("#old_personalty3").attr("disabled",true);
		if((document.getElementById("old_personalty1").value!=$("#old_personalty1").attr("idvalue"))||(document.getElementById("old_personalty2").value!=$("#old_personalty2").attr("idvalue"))||(document.getElementById("old_personalty3").value!=$("#old_personalty3").attr("idvalue"))||(document.getElementById("old_startDate_jbqk").value!=$("#old_startDate_jbqk").attr("idvalue"))){
		Ext.Ajax.request({
			url:'${ctx}/personality/PersonalityAction.a?doUpdate',
			method:'POST',
			defaults:{autoScroll: true},
			success:function(response,options){
				var temp=response.responseText;
				if(response.responseText=="##"){
					alert_update_failure("个性发展");	
				}else{
					var len=eval(temp);	
					document.getElementById("top1").innerHTML = "";	
					 var str = "<div id='old_top_jbqk'>";
						str += "<table width='100%' border='0' cellspacing='1' bgcolor='#999999' class='biaoge'><tr class='title_bg'>"
							+"<td colspan='4'>个性发展基本情况<span class='red'>*</span></td>"
							+"</tr><tr><td height='40' colspan='4' class='youjuzhong bg_eee'>"
							+"评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "1"){
									str += "日期：<span class='youjuzhong pr20 bg_eee'><input id='old_startDate_jbqk' idvalue = '' type='text' size='10'  value=\""+len[0].list2[i].signdate+"\" onClick='WdatePicker()' /></span></td> ";
								}
							}
							str += "</tr><tr><td class='th' width='10%'>二级指标</td><td class='th' width='10%'>三级指标</td><td class='th'  width='70%'>个性发展记录</td><td class='th'  width='10%'>操作</td></tr>"
							+"<tr><td height='191' class='h50 th'>特长</td><td class='h50 th'>学科特长<br /> 体育运动特长<br /> 艺术特长</td><td class='h50' id='old_td11' style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> ";
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "1"){
									str += "<input id='old_jbqk1' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty1' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty1"+"\",this)' onblur='turnoff1(\""+"old_personalty1"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += "style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty1"+"\",this)' onblur='turnoff1(\""+"old_personalty1"+"\",this)' >"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
							}
							str += "</span></td><td width='"+tdWidth+"' rowspan='3' class='bg_eee' id='bg_eee_2_4'><input type='button' class='fr tianjia' style='margin-right:18px;' onclick='updatejbqk()'/></td>"
							    +"</tr><tr><td height='191' class='h50 th'>有新意的成果</td><td class='h50 th'>活动成果<br /> 设计成果<br /> 制作成果</td><td class='h50' id='old_td22' style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> "
						    for(var i=0;i<len[0].list2.length;++i){
							    if(len[0].list2[i].indexid == "2"){
								str += "<input id='old_jbqk2' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty2' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty2"+"\",this)' onblur='turnoff1(\""+"old_personalty2"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;'  onfocus='turnon1(\""+"old_personalty2"+"\",this)' onblur='turnoff1(\""+"old_personalty2"+"\",this)'>"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
						    }
							str += "</span></td></tr><tr><td height='191' class='h50 th'>其他</td><td class='h50 th'>自主选择</td><td class='h50'  id='old_td33' style='background:#eee;'><span class='downbox mt18' style='padding-left:0px'> ";
							for(var i=0;i<len[0].list2.length;++i){
								if(len[0].list2[i].indexid == "3"){
									str += "<input id='old_jbqk3' type='hidden' value=\""+len[0].list2[i].baseid+"\"/><textarea id='old_personalty3' class='fl wenbenyu000'";
									if(len[0].list2[i].developmentrd.length==0){
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; color:#BCBCBC; cursor:text; width:100%;' onfocus='turnon1(\""+"old_personalty3"+"\",this)' onblur='turnoff1(\""+"old_personalty3"+"\",this)'>最多输入4000字</textarea>"
									}else{
										str += " style=' margin-top:0px; margin-left:0px; margin-bottom:0px; margin-right:0px; background: transparent; float:left; cursor:text; width:100%;'  onfocus='turnon1(\""+"old_personalty3"+"\",this)' onblur='turnoff1(\""+"old_personalty3"+"\",this)'>"+len[0].list2[i].developmentrd+"</textarea>";
									}
								}
							}
							str +="</span></td></tr></table></div>"
						document.getElementById("top1").innerHTML = str;
							document.getElementById("old_startDate_jbqk").setAttribute("idvalue",document.getElementById("old_startDate_jbqk").value);
					alert_update_success("个性发展","");
					document.getElementById("old_personalty1").setAttribute("idvalue",document.getElementById("old_personalty1").value);
					document.getElementById("old_personalty2").setAttribute("idvalue",document.getElementById("old_personalty2").value);
					document.getElementById("old_personalty3").setAttribute("idvalue",document.getElementById("old_personalty3").value);
				}
				$("#old_personalty1").attr("disabled",false);
				$("#old_personalty2").attr("disabled",false);
				$("#old_personalty3").attr("disabled",false);
			},
			params : {
				id1 : document.getElementById("old_jbqk1").value,
				id2 : document.getElementById("old_jbqk2").value,
				id3 : document.getElementById("old_jbqk3").value,
				developmentrd1 : document.getElementById("old_personalty1").value,
				signdate : document.getElementById("old_startDate_jbqk").value,
				developmentrd2 : document.getElementById("old_personalty2").value,
				developmentrd3 : document.getElementById("old_personalty3").value,
				termId : document.getElementById("termId").value
			}
		}); 
	}else{
		if($("#old_personalty1").val()==""){
			$("#old_personalty1").removeAttr("style"); 
			$("#old_personalty1").css({background:"transparent",width:"100%",float:"left",cursor:"text"});
			$("#old_personalty1").css("margin-top","0px");
			$("#old_personalty1").css("margin-bottom","0px");
			$("#old_personalty1").css("margin-left","0px");
			$("#old_personalty1").css("margin-right","0px");
		}
		if($("#old_personalty2").val()==""){
			$("#old_personalty2").removeAttr("style"); 
			$("#old_personalty2").css({background:"transparent",width:"100%",float:"left",cursor:"text"});
			$("#old_personalty2").css("margin-top","0px");
			$("#old_personalty2").css("margin-bottom","0px");
			$("#old_personalty2").css("margin-left","0px");
			$("#old_personalty2").css("margin-right","0px");
		}
		if($("#old_personalty3").val()==""){
			$("#old_personalty3").removeAttr("style"); 
			$("#old_personalty3").css({background:"transparent",width:"100%",float:"left",cursor:"text"});
			$("#old_personalty3").css("margin-top","0px");
			$("#old_personalty3").css("margin-bottom","0px");
			$("#old_personalty3").css("margin-left","0px");
			$("#old_personalty3").css("margin-right","0px");
		}
		$("#old_personalty1").attr("disabled",false);
		$("#old_personalty2").attr("disabled",false);
		$("#old_personalty3").attr("disabled",false);
	}
		$("#old_td11").css("background-color","#eee");
		$("#old_td22").css("background-color","#eee");
		$("#old_td33").css("background-color","#eee");
	}else{
		$("#old_personalty1").attr("disabled",false);
		$("#old_personalty2").attr("disabled",false);
		$("#old_personalty3").attr("disabled",false);
		apprasial_alert(4000);
	}
}　　
function turnon1(id,obj){

	/* var idd = window.event.srcElement.getAttribute('id'); */
	var idd=$(obj).attr("id");
	if($("#"+idd).val()=="最多输入4000字"){
		$("#"+idd).removeAttr("style"); 
		$("#"+idd).css({float:"left",width:"100%",cursor:"text"});
		$("#"+idd).css("margin-top","0px");
		$("#"+idd).css("margin-bottom","0px");
		$("#"+idd).css("margin-left","0px");
		$("#"+idd).css("margin-right","0px");
		$("#"+idd).val("");
	}
	if(id=="old_personalty1"){
		$("#old_td11").css("background-color","");
	}else if(id=="old_personalty2"){
		$("#old_td22").css("background-color","");
	}else if(id=="old_personalty3"){
		$("#old_td33").css("background-color","");
	}
	
}
 	function turnoff1(id,obj){
 	
 		/* var idd = window.event.srcElement.getAttribute('id'); */
		var idd=$(obj).attr("id");
	 		if ($("#"+idd).val() ==""){
			$("#"+idd).val("最多输入4000字");
			$("#"+idd).css({float:"left",color:"#BCBCBC",cursor:"text"});
		}
	}
 	$(document).ready(function(){
		if($("#old_personalty1").val()=="最多输入4000字"){
			$("#old_personalty1").removeAttr("style"); 
			$("#old_personalty1").css({background:"transparent",float:"left",width:"100%",color:"#BCBCBC",cursor:"text"});
			$("#old_personalty1").css("margin-top","0px");
			$("#old_personalty1").css("margin-bottom","0px");
			$("#old_personalty1").css("margin-left","0px");
			$("#old_personalty1").css("margin-right","0px");
		}
		if($("#old_personalty2").val()=="最多输入4000字"){
			$("#old_personalty2").removeAttr("style"); 
			$("#old_personalty2").css({background:"transparent",float:"left",width:"100%",color:"#BCBCBC",cursor:"text"});
			$("#old_personalty2").css("margin-top","0px");
			$("#old_personalty2").css("margin-bottom","0px");
			$("#old_personalty2").css("margin-left","0px");
			$("#old_personalty2").css("margin-right","0px");
		}
		if($("#old_personalty3").val()=="最多输入4000字"){
			$("#old_personalty3").removeAttr("style"); 
			$("#old_personalty3").css({background:"transparent",float:"left",width:"100%",color:"#BCBCBC",cursor:"text"});
			$("#old_personalty3").css("margin-top","0px");
			$("#old_personalty3").css("margin-bottom","0px");
			$("#old_personalty3").css("margin-left","0px");
			$("#old_personalty3").css("margin-right","0px");
		}
		 var thisMession=document.getElementById("mession");
		
 		 thisMession.innerHTML="";
 		 thisMession.innerHTML=mess;  
	});

		</script>
		</html>