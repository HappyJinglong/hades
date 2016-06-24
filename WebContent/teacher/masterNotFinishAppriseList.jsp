<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
 <%@ include file="/common/mass.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css_new/index.css">
<link type="text/css" rel="stylesheet" href="${ctx}/css_new/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css_new/jquery.autocomplete.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css_new/jquery.gritter.css" />
<style type="text/css">
 body,html{ width:100%; height:115%;
    overflow-x:hidden;
	overflow-y:hidden; 
}
#three{
 	margin-right:-32px;
 	margin-top: 7px;
 }
  #pj_jiaoshi_main .name_cxbox{
	width:99.85%;
}
  #pj_jiaoshi_main{
	width: 99.4%;
	top:54px;
	height:29%;
}
#pj_jiaoshi_main .layout{
	/* width: 104.5%; */
	margin-left:-35px;
}
#pj_jiaoshi_main .name_con .neirong{
 	height:120px;
 	width:88.6%; 
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
#pj_jiaoshi_main .name_cxleft{
	margin-top: 25px;
}
.wenziliebiao100{
	width:110px;
}
/* 新添加内容 */
.ml70{
	margin-left:5px;
}
.title_xie{
   height:40px;
   background-color:rgb(39,159,70);
   padding-left: 100px;
   padding-top: 16px;
   font-size:18px;
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
}
#pj_jiaoshi_main .name{
   padding-left: 300px;
}
.img-padding{
 margin-top:-20px;
}
/* 删除按钮样式 */
#pj_jiaoshi_main .name_con .btn{
	border-bottom-width:0px;
	border-right-width:0px;
	border-top-width:0px;
	height:132px;
	/* border-left-width:0px; */
}
</style>

</head>
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
//是否点击删除按钮
var isNull=0;
var isDelete=0;
//是否是失去焦点保存
var isSave = 0;
//需要删除的数据
var deleteId = "";
var teacherName='${teacherName}';
var cid = '${classId}';
function saveOrUpdate(currentObj){
	var curentId = currentObj.attr("id");
	if(curentId.indexOf("content")>0){
		update_content(currentObj,0);
		if(isNull==1){
			isNull=0;
			return;
		}
		currentObj.parent().css("background","#eee");
		clear_style(curentId);
		currentObj.css("border","1px solid transparent");
	}else{
		save_content(currentObj);
		if(isNull==1){
			isNull=0;
			return;
		}
		clear_style(curentId);
		currentObj.css("border","1px solid transparent");
	}
}

function save_content(tt){
	isSave=1;
	var subjdctId = null;
	if($("#subSelected").length>0){
		subjdctId = $("#subSelected option:selected").val();
	}
	var newId = tt.attr("id");
	//学生id
	var sid = newId.split("_")[0];
	var eduid = $("#"+sid+"_Eduid").val();
	//评价内容
	var content = tt.val();
	//栏目id
	var sectionCode = $("#sectionId").val();
	//新增时间
	var signDate = $("#"+newId+"_Date").val();
	if($.trim(content)=="" || $.trim(content)=="最多输入600字"){
		isSave=0;
		tt.parent().css("color","");
		input_notice(newId,600);
		return;
	}
	if(content.length>600){
		isNull=1;
		apprasial_alert(600);
		isSave=0;
		return;
	}
	$("#"+newId+"_Date").attr("disabled",true);
	tt.attr("disabled",true);
	Ext.Ajax.request({
		url:'${ctx}/teacher/MasterApriseDataAction.a?doInsertMasterAppraisal',
		method:'POST',
		defaults:{autoScroll: true},
		success:function(response,options){
		 	var temp=response.responseText;
		 	if(temp!="false"){
		 		$("#"+newId+"_Date").attr("disabled",false);
		 		tt.attr("disabled",false);
		 		$("#"+newId+"_Sid").attr("id",temp+"_Sid");
				tt.parent().css("background","#eee");
		 		tt.attr("idValue",content);
		 		tt.attr("id",temp+"_content");
		 		$("#"+newId+"_delete").attr("id",temp+"_delete");
		 		$("#"+newId+"_Date").attr("id",temp+"_Date");
		 		$("#"+newId+"_add").attr("id",temp+"_add");
		 		$("#"+sid+"_one").attr("id",temp+"_one");
		 		$("#"+sid+"_tab").attr("id",temp+"_tab");
		 		$("#"+sid+"_order").attr("id",temp+"_order");
		 		if(sectionCode!="22"){
		 			$("#"+sid+"_two").css("display","block");
		 		}
		 		$("#"+sid+"_two").attr("id",temp+"_two");
				if(isDelete==1){
					isSave=0;
					if(deleteId.indexOf("newAdd")>0){
						deleteContent($("#"+temp+"_delete"),$("#"+sid+"_div"));
					}else{
						var dId = deleteId.split("_")[0];
						alert_save_success($("#"+sid+"_name").html(),$("#"+temp+"_order").html());
						deleteContent($("#"+deleteId),$("#"+$("#"+dId+"_Sid").val()+"_div"));
					}
		 		}else{
		 			alert_save_success($("#"+sid+"_name").html(),$("#"+temp+"_order").html());
		 			var studentColor = $("#"+eduid+"_studentName");
		 			if(studentColor.attr("color")=="red"){
		 				studentColor.attr("color","green");
		 			}
		 		}
				isSave=0;
		 	}else{
		 		$("#"+newId+"_Date").attr("disabled",false);
		 		tt.attr("disabled",false);
		 		text_style(newId);
		 		alert_save_failure($("#"+sid+"_name").html());
		 		isSave=0;
		 	}
		},
		params : {
			signDate : signDate, 
			apprasial : content,
			evaluatetypeid:sectionCode,
			studentid:sid,
			classId:$("#cid").val(),
			eduid:eduid,
			zsTermId:$("#termId option:selected").val(),
			isHistory:1,
			subject:subjdctId,
			studentname:$("#"+sid+"_name").html()
		}
	});
}
//更新内容
function update_content(tt,num){
	var tId = tt.attr("id");//文本域id
	var subjdctId = null;
	if($("#subSelected").length>0){
		subjdctId = $("#subSelected option:selected").val();
	}
	var appraisalid = tId.split("_")[0];//评价id
	var val_ago = $("#"+appraisalid+"_content").attr("idValue");//文本域更新前值
	var val_now = $("#"+appraisalid+"_content").val();//输入值
	var signDate = $("#"+appraisalid+"_Date").val();//签名时间 
	var sectionCode = $("#sectionId").val();//栏目id
	var studentid=$("#"+appraisalid+"_Sid").val();//学生id
	if(num==0){
		if(val_now==val_ago){
			return;
		}
	}
	if($.trim(val_now)=="" || $.trim(val_now)=="最多输入600字"){
		isNull=1;
		return;
	}
	if(val_now.length>600){
		isNull=1;
		apprasial_alert(600);
		return;
	}
	Ext.Ajax.request({
		url:'${ctx}/teacher/MasterApriseDataAction.a?doUpadateMasterAppraisal',
		method:'POST',
		defaults:{autoScroll: true},
		success:function(response,options){
		 	var temp=response.responseText;
		    if(temp!="false"){
		    	$("#"+appraisalid+"_content").attr("idValue",val_now);
		    	alert_update_success($("#"+studentid+"_name").html(),$("#"+appraisalid+"_order").html());
		    }else{
		    	text_style(tId);
		    	alert_update_failure($("#"+studentid+"_name").html());
		    }
		},
		params : {
			proKey :appraisalid,
			signDate : signDate,
			apprasial : val_now,
			evaluatetypeid:sectionCode,
			studentid:studentid,
			classId:$("#cid").val(),
			zsTermId:$("#termId option:selected").val(),
			isHistory:1,
			subject:subjdctId,
			eduid:$("#"+studentid+"_Eduid").val(),
			studentname:$("#"+studentid+"_name").html()
		}
	});
}
function addContent(parentDiv,currentObj){
	var width = $("#shanchu_btn").css("width");
	var frame2 = $(self.parent.frames[1].document);
	var flag = frame2.find("a").attr("title");
	var textWidth = "88.6%";
	if(flag=="关闭左边菜单"){
		textWidth= "88.6%";
		width = "10%";
	}else{
		textWidth = "90.6%";
		width = "8.05%";
	}
	var orderNum = 0;
  	for(var i=0;i<parentDiv.find("textarea").size();i++){
  		orderNum++;
		if($(parentDiv.find("textarea")[i]).attr("id").indexOf("newAdd")>0){
			return;
		}
	}
	var appId = currentObj.attr("id").split("_")[0];
	var sectionCode = $("#sectionId").val();
 	var nowTime = $("#now_time").val();
	var sid = $("#"+appId+"_Sid").val();
	var blank=sid+"_newAdd";
	var tmp= '<table  class="xiechengsheng4" cellspacing="0" id="'+sid+'_tab">' 
	    +'<tr>' 
	    +'<td id="'+sid+'_order" width="10%" height="40" class="xiechengsheng3">'+(orderNum+1)+'</td>'
	    + '<td  class="xiechengsheng" >评价人：任课老师 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：'+teacherName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
		+ '&nbsp;日期：<input	id="'+blank+'_Date" type="text" size="10" value="'+nowTime+'" idvalue="" onchange="saveOrUpdateTime($(this));"'	                  
		+ 'onClick="WdatePicker()" /></td>'
		+ '</tr>'
		+ '</table>'
		+'<div class="name_con" id ="'+sid+'_one">'
		+'<input id="'+blank+'_Sid" value="'+sid+'" type="hidden">'
		+'<textarea id="'+blank+'" idvalue=""  style="width:'+textWidth+'; background: transparent; border: 1px solid transparent; " class="fl neirong ml70 mt10" onfocus="changeBack($(this));"  onblur="saveOrUpdate($(this))">'
		+'</textarea>'
		+'<div class="fr btn" style="width:'+width+';" id="shanchu_btn"><input id="'+blank+'_delete" class="fr shanchu" type="button" style="margin-top:30px;margin-right:20px;" onclick="delete_content($(this),$(\'#'+sid+'_singleStu\'))"/></div></div>'
		+'<div class="name_cxbox" id ="'+sid+'_two">'
		+'<div class="name_cxright fr"><div class="fr btn" id="three">'
		+'<input id="'+blank+'_add" class="fr zengjia mr15" type="button" onclick="addContent($(\'#'+sid+'_div\'),$(this));"/>'
		+'</div></div></div>';
		parentDiv.append(tmp);
		currentObj.parent().parent().parent().css("display","none");
		input_notice(blank,600);
}
//删除
function delete_content(currentObj,stuDivObj){
	deleteId = currentObj.attr("id");
	var flag = apprasial_delete();
	if(flag){
		isDelete=1;
		if(isSave==1){
			return;
		}
		deleteContent(currentObj,stuDivObj);
	}
}
function deleteContent(currentObj,stuDivObj){
	var textarea = stuDivObj.find("textarea");
	if(textarea.size()==1 && $(textarea[0]).attr("id").indexOf("newAdd")>0){
		apprasial_del_Finish();
		isDelete=0;
		return;
	}
	var deletId = currentObj.attr("id");
	if(deletId.indexOf("newAdd")>0 && textarea.size()>0){
		var sid = deletId.split("_")[0];
		var preDiv = $("#"+sid+"_tab").prev();
		$(preDiv).css("display","block");
		$("#"+sid+"_tab").remove();
		$("#"+sid+"_one").remove();
		$("#"+sid+"_two").remove();
		isDelete=0;
		return;
	}
	var appraisalid=deletId.split("_")[0];
	$("#"+appraisalid+"_content").attr("disabled",true);
	var sectionCode = $("#sectionId").val();//栏目id
	var studentid=$("#"+appraisalid+"_Sid").val();//学生id
	Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	Ext.Ajax.request({
		url:'${ctx}/teacher/MasterApriseDataAction.a?doDeleteMasterAppraisal',
		method:'POST',
		defaults:{autoScroll: true},
		success:function(response,options){
			var temp=response.responseText;
		    if(temp!="false"){
		    	var currentPoint = 0;
		    	var count = 0;
		    	var inputs = stuDivObj.find("input");
		    	for(var i=0;i<inputs.size();i++){
		    		if($(inputs[i]).attr("id").indexOf("delete")>0){
		    			count++;
		    			if($(inputs[i]).attr("id")==deletId){
		    				currentPoint=count;
		    			}
		    		}
		    	}
		    	if(count==1){
			 		$("#"+appraisalid+"_Sid").attr("id",studentid+"_newAdd_Sid");
			 		$("#"+appraisalid+"_content").attr("idValue","");
			 		$("#"+appraisalid+"_content").val("");
			 		$("#"+appraisalid+"_content").parent().css("background","#FFF");
			 		$("#"+appraisalid+"_content").attr("id",studentid+"_newAdd");
			 		$("#"+appraisalid+"_delete").attr("id",studentid+"_newAdd_delete");
			 		$("#"+appraisalid+"_Date").attr("id",studentid+"_newAdd_Date");
			 		$("#"+appraisalid+"_add").attr("id",studentid+"_newAdd_add");
			 		$("#"+appraisalid+"_one").attr("id",studentid+"_one");
			 		$("#"+appraisalid+"_two").attr("id",studentid+"_two");
			 		$("#"+appraisalid+"_tab").attr("id",studentid+"_tab");
			 		$("#"+appraisalid+"_order").attr("id",studentid+"_order");
			 		$($("#"+studentid+"_two")).css("display","none");
					input_notice(studentid+"_newAdd",600);
					$("#"+studentid+"_newAdd").attr("disabled",false);
		    	}else{
			    	if(currentPoint==1 || currentPoint<count){
			    		$("#"+appraisalid+"_one").remove();
						$("#"+appraisalid+"_two").remove();
						$("#"+appraisalid+"_tab").remove();
			    	}else if(currentPoint==count){
			    		var preDivOld = $("#"+appraisalid+"_tab").prev();
						$(preDivOld).css("display","block");
						$("#"+appraisalid+"_one").remove();
						$("#"+appraisalid+"_two").remove();	
						$("#"+appraisalid+"_tab").remove();	
			    	}
		    	}
		    	//重新排序
		    	var count=0;
		    	stuDivObj.find("td").each(function(){
		    		if($(this).attr("id").indexOf("_order")>-1){
		    			$(this).html(++count);
		    		}
		    	});
		    	alert_delete_success($("#"+studentid+"_name").html());
		    	isDelete=0;
		    	if(count==1){
		    		var textArea = $("#"+studentid+"_div").find("textarea");
		    		if(textArea.val()=="最多输入600字"){
		    			var studentColor = $("#"+$("#"+studentid+"_Eduid").val()+"_studentName").attr("color");
		    			if(studentColor == "green"){
		    				$("#"+$("#"+studentid+"_Eduid").val()+"_studentName").attr("color","red");
		    			}
		    		}
		    	}
		    }else{
		    	text_style(appraisalid+"_content");
		    	alert_delete_failure($("#"+studentid+"_name").html());
		    	isDelete=0;
		    }
		},
		params : {
			proKey : appraisalid,
			evaluatetypeid:sectionCode,
			studentid:studentid,
			classId:$("#cid").val(),
			zsTermId:$("#termId option:selected").val(),
			isHistory:1,
			eduid:$("#"+studentid+"_Eduid").val()
		}
	});
}
//选择班级
function chooseClass(){
	var flag = $("#flag").val();
	var evaluatetypeid =  $("#sectionId").val();
	var sectionName =  $("#sectionName").val();
	var classid =$("#selectedId option:selected").val();
	var subject = null;
	if($("#subSelected").length>0){
		subject = $("#subSelected option:selected").val();
	}
	ShowDiv();
	window.location.href="${ctx}/teacher/MasterApriseDataAction.a?showAppriseList&&flag="+flag+"&&evaluatetypeid="+evaluatetypeid+"&&sectionName="+sectionName+"&&classId="+classid+"&&subject="+subject;
}
//锚点选中数据
$(document).ready(function(){
	var texts = $("body").find("textarea");
	var sectionCode = $("#sectionId").val();
	for(var i=0;i<texts.size();i++){
		input_notice($(texts[i]).attr("id"),600)
	}
	$("#currentClass").html($("#selectedId option:selected").html());
	initStudentInfos();
	var frame2 = $(self.parent.frames[1].document);
	var flag = frame2.find("a").attr("title");
	if(flag == "关闭左边菜单"){
		$("[name='shanchu_btn']").css("width","10%");
		$("textarea").css("width","88.6%");
	}else{
		$("[name='shanchu_btn']").css("width","8.05%");
		$("textarea").css("width","90.5%");
	}
});
/**
 *根据id查出学生信息
 */
var eduIdAgo = "";
function findById(obj,id){
	id = $(obj).attr("id");
	$("li").css("background","");
	$(obj).parent().css("background","gray");
	//隐藏以前的
	$("[name='"+eduIdAgo+"_pic']").hide();
	$("[name='"+eduIdAgo+"_main']").hide();
	//显示当前的
	$("[name='"+id+"_pic']").show();
	$("[name='"+id+"_main']").show();
	eduIdAgo=id;
}
function initStudentInfos(){	
	var divdsl = $("#xingmingdsl");
	var length = "${fn:length(appriseInfos)}";
	var str = "";				
	str += "<ul class='name'>";	
	<c:forEach items="${appriseInfos}" var="studentInfo" varStatus="vs">
		var name = "${studentInfo.name}";
		var eduId = "${studentInfo.eduid}";
		var status = "${studentInfo.status}";
		var count = "${vs.index}";
		if(count==0){
			str += "<li style='background:gray;'><span id='"+eduId+"' title='"+name+"' style='cursor:pointer' onclick='findById(this,"+eduId+")'>";
			eduIdAgo = eduId;
		}else{
			str += "<li><span id='"+eduId+"' title='"+name+"' style='cursor:pointer' onclick='findById(this,"+eduId+")'>";
		}
		if(status == "y"){//判断状态
			if(name.length>3){//截取字符串
				var st = name.substring(0,2);							
				st +="..."
				str += "<font id='"+eduId+"_studentName' color=green>"+st+"</font></span></li>";
			}else{
				str += "<font  id='"+eduId+"_studentName' color=green>"+name+"</font></span></li>";
			}						
		}else{
			if(name.length>3){
				var st = name.substring(0,2);
				st +="..."
				str += "<font  id='"+eduId+"_studentName' color=red>"+st+"</font></span></li>";
			}else{
				str += "<font  id='"+eduId+"_studentName' color=red>"+name+"</font></span></li>";
			}	
		}		
	</c:forEach>
	str += "</ul>";									
	divdsl.append(str);
}
function saveOrUpdateTime(currentObj){
	//获取当前textarea
	var timeId = currentObj.attr("id");
	if(timeId.indexOf("newAdd")>0){
		save_content($("#"+timeId.split("_")[0]+"_newAdd"));
	}else{
		var oldTextid = timeId.split("_")[0];
		update_content($("#"+oldTextid+"_content"),1);
	}
}
function chooseTerm(){
	var zsTermId = $("#termId option:selected").val();
	var subject = null;
	if($("#subSelected").length>0){
		subject = $("#subSelected option:selected").val();
	}
	ShowDiv();
	window.location.href="${ctx}/teacher/MasterApriseDataAction.a?showAppriseList&&flag="+'${flag}'+"&&evaluatetypeid="+'${evaluatetypeid}'+"&&sectionName="+'${sectionName}'+"&&classId="+'${classId}'+"&&zsTermId="+zsTermId+"&&isHistory=1&&subject="+subject;
}
function changeBack(currentObj){
	if(currentObj.css('borderLeftWidth')=="2px"){
		return;
	}
	currentObj.parent().css("background","#FFF");
	var textId=currentObj.attr("id");
	text_style1(textId);
	clear_notice1(textId,600);
}
function chooseSub(){
	var zsTermId = $("#termId option:selected").val();
	var flag = $("#flag").val();
	var evaluatetypeid =  $("#sectionId").val();
	var sectionName =  $("#sectionName").val();
	var subject =$("#subSelected option:selected").val();
	var classid = $("#cid").val();
	ShowDiv();
	window.location.href="${ctx}/teacher/MasterApriseDataAction.a?showAppriseList&&flag="+flag+"&&evaluatetypeid="+evaluatetypeid+"&&sectionName="+sectionName+"&&subject="+subject+"&&classId="+classid+"&&zsTermId="+zsTermId+"&&isHistory=1";

}
</script>
<body id="text_style">
<input type="hidden" id="sectionId" value="${evaluatetypeid }"/>
<input type="hidden" id="now_time" value="${date_content }"/>
<input type="hidden" id="flag" value="${flag}"/>
<input type="hidden" id="sectionName" value="${sectionName}"/>
<input type="hidden" id="cid" value="${classId}"/>
<div class="dangqianwz">
 	<span class="fl">当前位置：未完成评价学生->${sectionName}&nbsp;&nbsp;&nbsp;</span> 
    <span >班级：
     <select id="selectedId" name="select" class="  wenziliebiao100" onchange="chooseClass();">
     			<c:if test="${not empty classInfos }">  
     				<c:forEach items="${classInfos}" var="cInfo"  varStatus="vs">
     					<option <c:if test="${fn:split(cInfo,'@')[1] eq classId}">selected="selected"</c:if> value="${fn:split(cInfo,'@')[1] }">${fn:split(cInfo,'@')[0] }</option>
     				</c:forEach>
     			</c:if>
            </select>  </span> 
            &nbsp;&nbsp;
            	<c:if test="${evaluatetypeid eq '44'}">
            		<c:if test="${not empty subjectDtos}">
                        <span >课程：
				    		<select id="subSelected" name="select" class="  wenziliebiao100" onchange="chooseSub();">
				    			<c:forEach items="${subjectDtos }" var="subjct" varStatus="vs">
				    				<%-- <option <c:if test="${vs.count==1}">selected="selected"</c:if> value="${subjct.subjectid }">${subjct.subjectName }</option> --%>
				    				<option    <c:if test="${subjct.subjectid eq  subject}">selected="selected"</c:if>   value="${subjct.subjectid}">${subjct.subjectName }</option>
				    			</c:forEach>
				            </select>  
            			</span> &nbsp;&nbsp;
            		</c:if>
            	</c:if>
               	  <span >学期：
     <select name="select" id="termId" class="  wenziliebiao100" onchange="chooseTerm()">
    	 <app:highSchoolTermTag selectClassid="${classId}" selectNum="${zsTermId}" levelCode="${levelCode}"/>
     </select> </span>
 </div>
 
   <!-- 学生列表展示 -->
	<div class="s_content">
		<div class="nj" id="currentClass">		
		</div>
		<div align="center">
			<div class="ns" id="dengslanniu">
				<div class="names" id="xingmingdsl">
				</div>
			</div>
		</div>
	</div>
 <!-- 学生列表展示 -->
 
<c:if test="${not empty appriseInfos}">
<c:forEach items="${ appriseInfos}" var="student" varStatus="vst">
	<div id="pj_dengsl_main" name="${student.eduid}_pic" style="background:white; padding-top:18px;width:95%;margin-left:3%;<c:if test='${vst.index!=0 }'>display:none;</c:if>" class="dslxingmingdiv">
			<div class="name_pic">
				<img class="img-padding" src="${student.photoUrl}">
			</div>	
			<input type="hidden" id="${student.studentid}_Eduid" value="${student.eduid}"/>
			<div class="title_xie" id="${student.studentid}_name">${student.name}</div>
		</div>
	<div id="pj_jiaoshi_main" name="${student.eduid}_main" style="<c:if test='${vst.index!=0 }'>display:none;</c:if>"> 
	<div id="${student.studentid}_singleStu">
    	<div >
    	<div id="${student.studentid}_div" style="margin-left:3%;">
    		   			<table  class="xiechengsheng4" cellspacing="0"  id="${student.studentid}_tab"> 
		           <tr> 
		                <td id="${student.studentid}_order" width="10%" height="40" class="xiechengsheng3">1</td>
		                <td  class="xiechengsheng" >评价人：任课老师 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：${teacherName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						                  日期：<input	id="${student.studentid}_newAdd_Date" type="text" size="10" value="${date_content }" idvalue="" onchange="saveOrUpdateTime($(this));"
								onClick="WdatePicker()" /></td>
	                 </tr>
              </table>
		        <div class="name_con" id ="${student.studentid}_one">
	        		<input type="hidden" id="${student.studentid}_newAdd_Sid" value="${student.studentid}"/>
		        	<textarea id="${student.studentid}_newAdd"  style="background: transparent; border: 1px solid transparent; " idValue=""  class="fl neirong ml70 mt10"  onblur="saveOrUpdate($(this));" onfocus="changeBack($(this));"></textarea>
		       		<div class="fr btn" id="shanchu_btn" name="shanchu_name">
		            	<input id="${student.studentid}_newAdd_delete" type="button" class="fr shanchu" style="margin-top: 30px;margin-right: 20px;" onclick="delete_content($(this),$('#${student.studentid}_singleStu'))"/>
		            </div>
		        </div>
		          <div class="name_cxbox" id ="${student.studentid}_two"  style="display:none;">
			            <div class="name_cxright fr">
			                <div class="fr btn" id="three">
				                  <input id="${student.studentid}_newAdd_add" type="button"  class="fr zengjia mr15" onclick="addContent($('#${student.studentid}_div'),$(this))"/>
			                </div>
			            </div>
		        </div>
        </div>
         </div>
    </div>
    </div>
   </c:forEach>
  </c:if>

<c:if test="${empty appriseInfos}">
  	<div align="center"> 
  		<!-- <font color="red" size="6px">当前没有已完成评价学生记录</font> -->
  		<img src="${ctx }/images/sorry.jpg" />
  	</div>
</c:if>
<%@ include file="/common/div.jsp"%>
</body>
</html>
