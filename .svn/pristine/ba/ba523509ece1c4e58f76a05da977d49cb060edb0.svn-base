<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.autocomplete.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/TableFreeze.js"></script>
<script type='text/javascript' src='${ctx}/js/jquery.autocomplete.js'></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SegmentSelectDwr.js'></script>
<script type='text/javascript' src='${ctx}/dwr/interface/SeriesSelectDwr.js'></script>
<title>模块授课</title>
<style>
	.white_content {  
      position: absolute;  
      background-color: black;  
      z-index: 1;  
      -moz-opacity: 0.4;  
      opacity: .40;  
      filter: alpha(opacity=40);  
      vertical-align: middle;  
      top:0px;  
      text-align:center;
      width:100%;
      height:100%;  
	}  
	.li{
		margin-left: 10%;
	}
	li{
	 float: left;
	}
	ul{
		list-style:none;
	}
	select{
		height: 30px;
			width: 90px	
	}
	th,td{
		height: 33px
	}
		 .spnContent { 
	        vertical-align: middle;   
            margin-left: 10px;  
            color: white;  
            font-size: 18px;  
            font-weight: bold;  
} 
</style>
<script type="text/javascript">
var $$=jQuery.noConflict();
var isDelete = 0;
 var isSave = 0;//是否正在保存
	function modularExport(){
		window.location.href="${ctx}/innercourse/InnerCourse.a?goModularExport&yearid="+$$("#yearid").val()+"&segment="+$$("#segment").val()+"&gradenum="+$$("#gradenum").val();
	}
	function showcover(num)
	{
		document.getElementById("main").disabled="true";
		document.getElementById("covercontainer").style.display='block';
		document.getElementById("covercontent"+num).style.display="";
		scrollTo(0,0);
	}
	function hidecover(num)
	{
		document.getElementById("main").disabled="";
		document.getElementById("covercontainer").style.display="none";
		document.getElementById("covercontent"+num).style.display="none";
		$$("#subjectid").val("");
	}
	function hidecover1(num)
	{
		$$("#coursetable").html($$("#coursetable_hidden").html());
		
		$$("input[name='CourseId']").each(function(){
				if(!$$(this).attr('disabled')){
					$$(this).attr("checked",'');
					$$("#CourseIds_"+$$(this).attr('id').split("_")[1]).attr("checked",'');
				}
		});
		document.getElementById("main").disabled="";
		document.getElementById("covercontainer").style.display="none";
		document.getElementById("covercontent"+num).style.display="none";
		$$("#subjectid").val("");
	}
	//修改年级改变学年
	function changGradeToYear(gradenum,flag){ //v3.0
			if(gradenum =='' || null==gradenum){
				gradenum=$$("#gradenum").val().split("--")[1];
			}else{
				gradenum = gradenum.split("--")[1]
			}
			dwr.engine.setAsync(false);
			if(null!=gradenum&&''!=gradenum)
			{
				SeriesSelectDwr.changeGradeToYear(gradenum,function(obj){
					if(obj!=null){
						DWRUtil.removeAllOptions("year");
						DWRUtil.addOptions("year",obj ,"schoolyear" ,"schoolyearname" );
						if('${yearid}'!=null&&'${yearid}'!=''&&flag=='0'){
							DWRUtil.setValue("year", '${yearid}');
						}
					}else{
						DWRUtil.removeAllOptions("year");
					}
				});
			}else
			{
				DWRUtil.removeAllOptions("year");
			}
			dwr.engine.setAsync(true);
			changYearToSegment('');
	}
	
	//修改学年改变学段
	function changYearToSegment(year){ //v3.0
		var cmis30id = $$("#cmis30id").val();
			if(year =='' || null==year){
				year=$$("#year").val();
			}
			dwr.engine.setAsync(false);
			if(null!=year&&''!=year)
			{
				SeriesSelectDwr.changeYearToSegment(year,cmis30id,function(obj){
					if(obj!=null){
						DWRUtil.removeAllOptions("segment");
						DWRUtil.addOptions("segment",obj ,"segmentid" ,"segmentName" );
					}else{
						DWRUtil.removeAllOptions("segment");
					}
				});
			}else
			{
				DWRUtil.removeAllOptions("segment");
			}
			dwr.engine.setAsync(true);
			$$("#yearid").val(year);
	}
	
	//修改学年改变学段
	function changYearToSegment1(year){ //v3.0
		var cmis30id = $$("#cmis30id").val();
			if(year =='' || null==year){
				year=$$("#year").val();
			}
			dwr.engine.setAsync(false);
			if(null!=year&&''!=year)
			{
				SeriesSelectDwr.changeYearToSegment(year,cmis30id,function(obj){
					if(obj!=null){
						DWRUtil.removeAllOptions("segment");
						DWRUtil.addOptions("segment",obj ,"segmentid" ,"segmentName" );
						if('${segment}'!=null&&'${segment}'!=''){
							DWRUtil.setValue("segment", '${segment}');
						}
					}else{
						DWRUtil.removeAllOptions("segment");
					}
				});
			}else
			{
				DWRUtil.removeAllOptions("segment");
			}
			dwr.engine.setAsync(true);
			$$("#yearid").val(year);
	}

	window.onload = function (){
		if($$("#table").width()!=null&&$$("#dddiv").width()!=null){
			if($$("#table").width()>$$("#dddiv").width()){
				$$("#tablediv").width($$("#table").width());
			}
		}
		if($$("#table tr").length>=2){
			$$("#query1").attr("disabled","");		
		}else{
			
			$$("#query1").attr("disabled","disabled");
		}
		if('${noticeflag}'=="1"){
			alert_upload_success("保存成功");
		}else if('${noticeflag}'=="2"){
			alert_KG_importData_success();
		}
		/* var greadnum = $$("#selectGradenum").val(); */
		var greadnum = $$("#gradenum").val();
		changGradeToYear(greadnum,'0');
		var year = $$("#yearid").val();
		var cmis30id = $$("#cmis30id").val();
		changYearToSegment1(year);
		getTeacherInfo('');
		var teacherId="";
		var reg=new RegExp("teacher_","g"); //创建正则RegExp对象
		var checkid = "";	
		var valueid = "";	
		if("${selectClassModelList}"!=""&&"${selectClassModelList}"!=null&&"${selectClassModelList}"!="[]"){
			getTeacherInfo2('');
			$$("input[name='teacher_show']").each(function(){
				teacherId=$$(this).attr('id').replace(reg,"");
				<c:forEach items="${selectClassModelList}" var="selectClassModelList">
				checkid="${selectClassModelList.classid}"+"_"+"${selectClassModelList.course_id}"
			 	if(teacherId==checkid){
			 		if("${selectClassModelList.teachercode}"!="_"){
			 			valueid = "${selectClassModelList.teachercode}"
			 		}else{
			 			valueid = "";
			 		}
			 		$$("#"+$$(this).attr('id')).val(valueid);
			 		$$("#"+$$(this).attr('id')+"_hidden").val(valueid);
			 		$$(this).attr('idvalue',"${selectClassModelList.class_model_id}");
			 		$$("#"+$$(this).attr('id')+"_hidden").attr('idvalue',"${selectClassModelList.class_model_id}");
			 	}
			 </c:forEach>
			});
		}
		var tr = $$("#coursetable").find("tr");
		<c:forEach items="${kcourseListAll}" var="kcourseListAll">
		for(var j=0;j<tr.size();j++){
	   		var trId = $$(tr[j]).attr("id"); 
	   		if(trId.indexOf("trcourse_")>-1){
				if(trId.replace("trcourse_","") == "${kcourseListAll.course_id}"){
					$$(tr[j]).css('display',''); 
				}
	   		}
		 } 
		</c:forEach>
		//隐藏
		var tr_hidden = $$("#coursetable_hidden").find("tr");
		<c:forEach items="${kcourseListAll}" var="kcourseListAll">
		for(var j=0;j<tr_hidden.size();j++){
	   		var trId = $$(tr[j]).attr("id"); 
	   		if(trId.indexOf("trcourse_")>-1){
				if(trId.replace("trcourse_","") == "${kcourseListAll.course_id}"){
					$$(tr_hidden[j]).css('display',''); 
				}
	   		}
		 } 
		</c:forEach>
		
		/*$$("#table").FrozenTable(0,0,2);
		$$("#coursetable").FrozenTable(1,0,0);
		
	 	$$("#table").FrozenTable(0,0,1);
		
	 	$$("#tab_Test3").FrozenTable(1,0,1);
		
		$$("#tab_Test4").FrozenTable(2,1,1);
		
		$$("#tab_Test5").FrozenTable(3,1,4);
		
		$$("#tab_Test6").FrozenTable(2,1,2);
		
		$$("#tab_Test7").FrozenTable(2,2,1); */
		
	}
	
	function checkShowCourse(obj){
		var CourseValue = obj.value.split("--")[0];
		var id = '#CourseIds_'+CourseValue;
		$$(id).attr('checked',obj.checked);
	}
	function doSearchCourse(){
		 var reg=new RegExp("CourseId_","g"); //创建正则RegExp对象
		var regtr=new RegExp("tr_","g"); //创建正则RegExp对象
		var regtrold=new RegExp("old_","g"); //创建正则RegExp对象
		var tr = $$("#table").find("tr");
		var courseid = "";
		var courseoldid = "";
	   	 for(var i=0;i<tr.size();i++){
	   		var trId = $$(tr[i]).attr("id"); 
	   		if(trId.indexOf("tr_")>-1){
	   			courseid += trId+"--";
	   		}
	   	 } 
	   	courseid = courseid.replace(regtr,"").replace(regtrold,"");
	   	var arrayObj = new Array();
	   	arrayObj=courseid.split("--");
	   	
	   	for(var i=0;i<tr.size();i++){
	   		var trId = $$(tr[i]).attr("id"); 
	   		if(trId.indexOf("tr_old_")>-1){
	   			courseoldid += trId+"--";
	   		}
	   	 } 
	   	courseoldid = courseoldid.replace(regtr,"").replace(regtrold,"");
	   	var arrayObjOld = new Array();
	   	arrayObjOld = courseoldid.split("--");
		$$("input[name='CourseId']").each(function(){
			for(var i=0;i<arrayObj.length;i++){
				var iid = $$(this).attr('id').replace(reg,"");
					if(iid==arrayObj[i]){
						$$(this).attr("checked",'true');
						$$(this).attr('disabled','true');
						$$("#CourseIds_"+iid).attr("checked",'true');
					}
				}
		});
		$$("input[name='CourseId']").each(function(){
			var iid = $$(this).attr('id').replace(reg,"");
			for(var j=0;j<arrayObjOld.length;j++){
				if(iid==arrayObjOld[j]){
					$$("#CourseIds_"+iid).attr("checked",'');
				} 
			}
		}); 
		showcover(1);
	}
	function changeCourse(){
		if($$("#subjectid").val()==""){
			var subjectid = "00";
		}else{
			var subjectid = $$("#subjectid").val();
		}
		var tr = $$("#coursetable").find("tr");
		 Ext.Ajax.request({
				url : '${ctx}/innercourse/InnerCourse.a?searchGoAward',
				method:'POST',
				defaults:{autoScroll: true},
				success:function(response,options){
					var temp=response.responseText;
				    var len=eval(temp);
				    for(var j=0;j<tr.size();j++){
				    	var trId = $$(tr[j]).attr("id");
				    	if(trId.indexOf("trcourse_")>-1){
							$$(tr[j]).css('display','none');
				    	}
		    		}
				    if(len[0].listnow.length>0){
				    	for(var i=0;i<len[0].listnow.length;++i)
						{
				    		 for(var j=0;j<tr.size();j++){
			    		   		var trId = $$(tr[j]).attr("id"); 
			    		   		if(trId.indexOf("trcourse_")>-1){
									if(trId.replace("trcourse_","") == len[0].listnow[i].course_id){
										$$(tr[j]).css('display','');
									}
			    		   		}
				    		 } 
						}
				    }
				     doSearchCourse();
				},
				params : {
					year : $$("#yearid").val(),
					cmis30id : $$("#cmis30id").val(),
					segment : $$("#segment").val(),
					gradenum : $$("#gradenum").val(),
					subjectid : subjectid
				}
			});
		
	}
	
	function beforeselectCourse(){
		
		var selectedCount = 0;
		$$("input[name='CourseId']:checkbox:checked").each(function(){ 
			if(!$$(this).attr('disabled')){
				selectedCount++; 
			}
		});
		if(selectedCount< 1){
			alert("请选择课程");
			return false;
		}
		hidecover(1);
		return true;
	}
	/* //获取老师信息
	function getTeacherInfo1(currentObj){
		var teacherName="";
		if(""!==currentObj){
			teacherName = encodeURI(encodeURI(currentObj.val()));
		}
		 $$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "${ctx }/schoolCourse/SchoolCourseAction.a?queryTeacherInfo&&guidTeacherName="+teacherName,
				data: "{}",
				dataType: "json",
				success: function (msg) {
					if(!msg){
						return;
					}
							currentObj.autocomplete(msg.val,{
							minChars: 0,
							width: 140,
							matchContains: true,
							max:2000,
							formatItem: function(row, i, max) {
								return  row.name ;
							},
							formatMatch: function(row, i, max) {
								return  row.name;
							},
							formatResult: function(row) {
								return row.name;
							}
						}).result(function(event, row) {
							var objid = currentObj.attr("id")+"_hidden";
							$$("#"+objid).val(row.id);
						}); 
				}
			});
	} */
	//获取老师信息
	function getTeacherInfo(currentObj){
		var teacherName="";
		if(""!=currentObj){
			teacherName = encodeURI(encodeURI(currentObj.val()));
		}
		 $$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "${ctx }/schoolCourse/SchoolCourseAction.a?queryTeacherInfo&&guidTeacherName="+teacherName,
				data: "{}",
				dataType: "json",
				success: function (msg) {
					if(!msg){
						return;
					}
				 <c:forEach items="${selectCourseList }" var="courselist">
					<c:forEach items="${classList }" var="classlist">
						 $$("#teacher_${classlist.classid}_${courselist.course_id}").autocomplete(msg.val,{
							minChars: 0,
							width: 140,
							matchContains: true,
							max:2000,
							formatItem: function(row, i, max) {
								return  row.name ;
							},
							formatMatch: function(row, i, max) {
								return  row.name;
							},
							formatResult: function(row) {
								return row.name;
							}
						}).result(function(event, row) {
							var objid = "teacher_${classlist.classid}_${courselist.course_id}_hidden";
							$$("#"+objid).val(row.id);
						}); 
					</c:forEach>
				</c:forEach>
				}
			});
	}
	//获取老师信息
	function getTeacherInfo2(currentObj){
		var teacherName="";
		if(""!=currentObj){
			teacherName = encodeURI(encodeURI(currentObj.val()));
		}
		 $$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "${ctx }/schoolCourse/SchoolCourseAction.a?queryTeacherInfo&&guidTeacherName="+teacherName,
				data: "{}",
				dataType: "json",
				success: function (msg) {
					if(!msg){
						return;
					}
					$$("[name='teacher_show']").autocomplete(msg.val,{
						minChars: 0,
						width: 140,
						matchContains: true,
						max:2000,
						formatItem: function(row, i, max) {
							return  row.name ;
						},
						formatMatch: function(row, i, max) {
							return  row.name;
						},
						formatResult: function(row) {
							return row.name;
						}
					}).result(function(event, row) {
						$$("#"+$$(this).attr("id")+"_hidden").val(row.id);
					}); 
				}
			});
	}
	function doadd(){
		if(isSave==0){
			$$("#timeCount").html("正在保存中，请稍后...");
			ShowDiv();
			var saveok = 0;
			var updateok = 0;
			isSave = 1;
			var teacherid="";
			var teacheroldid="";
			$$("input[name='teacher_hidden']").each(function(){
				if($$(this).val()==""||$$(this).val()==null){
					$$(this).val("@@");
				}
				if($$(this).attr('idvalue')==null||$$(this).attr('idvalue')==""||$$(this).attr('idvalue')=="undefinded"){
					teacherid += $$(this).val()+"--"+$$(this).attr('id')+"##";
				}
			});
			var classIds = $$("td[id*='class_']")
		/* 	for(var i=0;i<classIds.size();i++){
				alert($$(classIds[i]).attr("id").split("_")[1]);
			} */
		   	var trold = $$("#table").find("tr");
		   	 for(var i=0;i<trold.size();i++){
		   		var troldId = $$(trold[i]).attr("id"); 
		   		if(troldId.indexOf("tr_old_")>-1){
		   			var inputSite = 0;
		   			$$(trold[i]).find("input[name='teacher_hidden']").each(function(){
						if($$(this).val()==""||$$(this).val()==null){
							$$(this).val("@@");
						}
						if($$(this).attr('idvalue')!=null&&$$(this).attr('idvalue')!=""&&$$(this).attr('idvalue')!="undefinded"){
							teacheroldid += $$(this).val()+"--"+$$(this).attr('idvalue')+"##";
						}
						if(($$(this).attr('idvalue')==null||$$(this).attr('idvalue')==""||$$(this).attr('idvalue')=="undefinded")
								&&($$(this).val()!=""&&$$(this).val()!=null&&$$(this).val()!="@@")){
							//teacherId Ok $$(this).val()
							//courseId $$(this).parent().parent().attr("id");
							//classId OK $$(classIds[i]).attr("id").split("_")[1]
							//segmentId  OK $$("#segment").val()
							teacheroldid += $$(this).val()+"--"//teacherId
											+$$(classIds[inputSite]).attr("id").split("_")[1]+"--"//classId
											+$$(this).parent().parent().attr("id").split("_")[2]+"--"//courseId
											+$$("#segment").val().split("--")[0]//segmentId
											+"##";
							/* alert($$(this).parent().parent().attr("id")); */
						}
						inputSite++;
					});
		   		}
		   	 }
			var classid="";
			var courseid="";
			var courseoldid=0;
			var tr = $$("#table").find("tr");
			   	 for(var i=0;i<tr.size();i++){
			   		var trId = $$(tr[i]).attr("id"); 
			   		if(trId.indexOf("tr_")>-1&&trId.indexOf("tr_old_")<=-1){
			   			courseid += trId+"--";
			   		}
			   	 }
		   	/* var trold = $$("#table").find("tr"); */
		   	 for(var i=0;i<trold.size();i++){
		   		var troldId = $$(trold[i]).attr("id"); 
		   		if(troldId.indexOf("tr_old_")>-1){
		   			courseoldid = 1;
		   		}
		   	 }
				<c:forEach items="${classList}" var="ls">
		        	classid += "${ls.classid}"+"--";
		        </c:forEach>
		        
		    if(courseid!=""){
		    	Ext.Ajax.request({
					url : '${ctx}/innercourse/InnerCourse.a?doInsertSegmentModel',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						if(response.responseText=="##"){
							saveok = "##";
						}else{
							saveok = 1;
						}
						isSuccussDo(courseid,courseoldid,updateok,saveok);
					},
					params : {
						segment : $$("#segment").val(),
						selectClassid : classid,
						selectCourseid : courseid,
						selectteacherid : teacherid
					}
				});
		    }    
		    
		    if(courseoldid==1){
		    	 Ext.Ajax.request({
						url : '${ctx}/innercourse/InnerCourse.a?updateSegmentModel',
						method:'POST',
						defaults:{autoScroll: true},
						success:function(response,options){
							if(response.responseText=="##"){
								updateok = "##";
							}else{
								updateok = 1;
							}
							isSuccussDo(courseid,courseoldid,updateok,saveok);
						},
						params : {
							teacherCourseid : teacheroldid
						}
					});
		    }
		}
	}
	function isSuccussDo(courseid,courseoldid,updateok,saveok){
		 if(courseid!=""&&courseoldid==1){
		    	if(updateok==1&&saveok==1){
		    		$$("#noticeflag").val(1);
		    		$$("#fr").submit();
		    	}else if(updateok=="##"||saveok=="##"){
		    		$$("#MyDiv").css("display","none");
		    		isSave = 0;
		    		alert_upload_failure("保存失败");
		    	}
		    }else if(courseid!=""&&courseoldid!=1){
				if(saveok==1){
					$$("#noticeflag").val(1);
					$$("#fr").submit();
		    	}else if(saveok=="##"){
		    		$$("#MyDiv").css("display","none");
		    		isSave = 0;
		    		alert_upload_failure("保存失败");
		    	}
		    }else if(courseid==""&&courseoldid==1){
				if(updateok==1){
					$$("#noticeflag").val(1);
					$$("#fr").submit();
		    	}else if(updateok=="##"){
		    		$$("#MyDiv").css("display","none");
		    		isSave = 0;
		    		alert_upload_failure("保存失败");
		    	}
		    }
	}
	function deleteunsave(obj){		
		 var r=apprasial_delete();
		 if (r==true){
			 $$("#CourseId_"+obj).attr("disabled",'');
			 $$("#CourseId_"+obj).attr("checked",'');
			 $$("#CourseIds_"+obj).attr("disabled",'');
			 $$("#CourseIds_"+obj).attr("checked",'');
			 alert_upload_success("删除成功");
			$$("#tr_"+obj).remove();
			if($$("#table tr").length>=2){
				$$("#query1").attr("disabled","");		
			}else{
				
				$$("#query1").attr("disabled","disabled");
			}
		 }
	}
	
	function deletesave(obj){				
		if(isDelete==0){
			
			isDelete=1;
			 var r=apprasial_delete1();
			 if (r==true){
				 
				var did = obj+"--"+$$("#segment").val();
				Ext.Ajax.request({
					url : '${ctx}/innercourse/InnerCourse.a?deleteTeacherCourse',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						if(response.responseText=="##"){
							alert_upload_failure("删除失败！");
							isDelete=0;
						}else{
							if(response.responseText=="@@"){
								alert_KG_self_tip("该记录学生成绩已经录入，不能删除！");
							}else{
								 $$("#CourseId_"+obj).attr("disabled",'');
								 $$("#CourseId_"+obj).attr("checked",'');
								 $$("#CourseIds_"+obj).attr("disabled",'');
								 $$("#CourseIds_"+obj).attr("checked",'');
								alert_upload_success("删除成功");
								$$("#tr_old_"+obj).remove();
								if($$("#table tr").length>=2){
									$$("#query1").attr("disabled","");		
								}else{
									
									$$("#query1").attr("disabled","disabled");
								}
							}
							isDelete=0;
						}
					},
					params : {
						year : $$("#yearid").val(),
						cmis30id : $$("#cmis30id").val(),
						gradenum : $$("#gradenum").val(),
						teacherCourseid : did
					}
				});
			 }else{
				 isDelete=0;
			 }
		}else{
			alert_KG_self_tip("正在删除中...");
		}
		
	}
	function changesegment(){
		if($$("#table tr").length>=2){
			$$("#query1").attr("disabled","");		
		}else{
			
			$$("#query1").attr("disabled","disabled");
		}
		ShowDiv();
		$$("#fr").submit();
	} 
	function checkempty(obj){
		if(obj.val()==""){
			$$("#"+obj.attr('id')+"_hidden").val("@@");
		}
	}
	function loading(){
		if($$("#table tr").length>=2){
			$$("#query1").attr("disabled","");		
		}else{
			
			$$("#query1").attr("disabled","disabled");
		}
		ShowDiv();
		$$("#fr").submit();
	}
</script>
</head>
<body style="background: #EEE" >
	<form action="${ctx}/innercourse/InnerCourse.a?searchGoAward" id="fr" method="post">
	<input type="hidden" id="noticeflag" name="noticeflag" value="0"/>
	<div id="covercontainer" style="display:none;" class="white_content"></div>
	
	<div id="breadcrumb" style="margin-top: 10px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>模块授课老师</a>
	</div>
	<div id="covercontent1" style="display:none;text-align:center;border-radius: 20px;width:400px;-moz-opacity: 0.9;opacity: .90;filter: alpha(opacity=90);background-color:white;position:absolute;left:50%;top:20%;margin-top:-100px;margin-left:-200px;z-index:2;">
	<div id="resulttitlearea" ><span style="font-family:华文中宋; font-size:16px;font-weight: bold;color:#279F46;">选择课程</span></div>
  <div id="formarea"> 
  <div style="background: #279F46;color:white;">
	  学科:&nbsp;&nbsp;&nbsp;<select name="subjectid" id="subjectid" onchange="changeCourse()" style="margin-top:7px;width:100px;">
			<option value="" ${(subjectid == "")?"selected":"" }>全部</option>
	        <app:sysSubjectSelectTag subjectId="${subjectid}"/>
	     </select>
  </div>
  <div id="yoverflow" style="overflow:auto;height:360px;overflow:auto;text-align:center;">
		<center> 
		<table style="table-layout:fixed;width:380px;text-align:center;border-color:#279F46;margin-top:10px;border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;" border="1" cellspacing="1"
			cellpadding="0" id="coursetable">
			<tr>
				<td style="width:35px;border:0.5px solid #279F46;">选项</td>
				<td style="width:75px;border:0.5px solid #279F46;">学科</td>
				<td style="width:90px;border:0.5px solid #279F46;">课程模块</td>
			</tr>
			<c:if test="${not empty kcourseListAll}">
				<c:forEach items="${kcourseListAll }" var="roll">
					<tr id="trcourse_${roll.course_id}" style="display: none;">
						<td style="border:0.5px solid #279F46;width:35px">
						<input type="checkbox" id="CourseId_${roll.course_id}" name="CourseId" value="${roll.course_id}--${roll.course_name}" onclick="checkShowCourse(this);"/>
						<div style="display: none;"><input type="checkbox" name="CourseIds" value="${roll.course_id}--${roll.course_name}" id="CourseIds_${roll.course_id}"/></div>
						</td>
						<td style="border:0.5px solid #279F46;width:75px">${roll.subject_name}</td>
						<td style="word-wrap:break-word;text-align: center;vertical-align: middle;border:0.5px solid #279F46;width:90px">${roll.course_code}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty kcourseListAll}">
					<tr>
						<td style="border:0.5px solid #279F46;" colspan="2" align="center">没有课程模块!</td>									
					</tr>
			</c:if>
		</table>
		<!-- 隐藏 -->
		<table style="display:none;table-layout:fixed;width:380px;text-align:center;border-color:#279F46;margin-top:10px;border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;" border="1" cellspacing="1"
			cellpadding="0" id="coursetable_hidden">
			<tr>
				<td style="width:35px;border:0.5px solid #279F46;">选项</td>
				<td style="width:75px;border:0.5px solid #279F46;">学科</td>
				<td style="width:90px;border:0.5px solid #279F46;">课程模块</td>
			</tr>
			<c:if test="${not empty kcourseListAll}">
				<c:forEach items="${kcourseListAll }" var="roll">
					<tr id="trcourse_${roll.course_id}" style="display: none;">
						<td style="border:0.5px solid #279F46;width:35px">
						<input type="checkbox" id="CourseId_${roll.course_id}" name="CourseId" value="${roll.course_id}--${roll.course_name}" onclick="checkShowCourse(this);"/>
						<div style="display: none;"><input type="checkbox" name="CourseIds" value="${roll.course_id}--${roll.course_name}" id="CourseIds_${roll.course_id}"/></div>
						</td>
						<td style="border:0.5px solid #279F46;width:75px">${roll.subject_name}</td>
						<td style="word-wrap:break-word;text-align: center;vertical-align: middle;border:0.5px solid #279F46;width:90px">${roll.course_code}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty kcourseListAll}">
					<tr>
						<td style="border:0.5px solid #279F46;" colspan="2" align="center">没有课程模块!</td>									
					</tr>
			</c:if>
		</table>
		
		</center>
  </div>
  </div>
  <div id="operationarea"> 
  <div id="operationcontent" style="text-align:right; height: 40px;">
 	<input name="selectCourse" type="submit" value="确定 "  style="margin-top: 8px;" onclick="return beforeselectCourse();" id="selectCourse" class="btn btn-success"/>
	<input name="Submit" type="button" value="关闭 " style="margin-right: 40px;margin-top: 8px;" onclick="hidecover1(1);" class="btn btn-success" />
  </div>
  </div>
  </div>
 
<div id="main" style="width: 100%;position:relative;z-index:0;">
		<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 54.5px">
		<input type="hidden" id="yearid" name="yearid" value="${yearid}"/>
		<input type="hidden" id="cmis30id" name="cmis30id" value="${cmis30id}"/>
		<input type="hidden" id="selectGradenum" name="selectGradenum" value="${selectGradenum}"/>
			 <ul style="margin-top: 5px">
			 	<li style="margin-left: 5%">
			 	  年级：
			 		<select id="gradenum" name="gradenum" onchange="changGradeToYear(this.value,'1');">
			 			 <app:gradeSelectTag gradeId="${gradenum}" cmis30Id="${cmis30id}" levelCode="${levelcode}" campusId="${campusid}"/>
			 		</select>
			 	</li>
			 	<li class="li">
			 	    学年：
				 	  <select id="year" name="year" style="width: 140px;" onchange="changYearToSegment(this.value);"></select>     
			 	</li>
			 	<li class="li">
			 	   学段：
				 	  <select id="segment" name="segment" onchange="changesegment()"></select>     
			 	</li>
			 	
			 	 <li class="li"><span>&nbsp;
			 	 	</span>
			  		<input type="button" onclick="loading()" class="btn btn-success" style="width: 70px;margin-bottom: 9px" id="query"  value="查 询" />
				 </li>
			 </ul>
		</div>
		
		<div id="dddiv" style="width: 100%;margin-top: 13px">
			<div class="widget-box" id="searchShow" style="margin-top:0px;margin-bottom:0px;margin-left:13px;margin-right: 13px;overflow:auto;text-align: center;max-height: 400px;">
				<div class="nopadding" style="text-align: center;overflow:auto;">
				<div id="tablediv" style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;">班级模块任课教师设置</div>
				<c:if test="${not empty classList}">
				<center>
					<table class="table-bordered table-striped with-check" id="table" style="border: 1px solid #DDD;text-align:center;">
						<!-- <caption style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;">	班级模块任课教师设置</caption> -->
							<tr id="${classlist.classid}">
								<td style="font-size: 13px;border-top: 1px solid #DDD;width: 140px;" colspan="2"><div style="width:240px;"><input type="button" onclick="doSearchCourse()" class="theme-login" style="margin-top: 2px;margin-bottom: 2px" value="选择模块"/></div></td>
								<c:if test="${not empty classList}">
									<c:forEach items="${classList }" var="classlist">
										<td style="font-size: 13px;border-top: 1px solid #DDD;width: 120px" id="class_${classlist.classid}"><div style="width: 120px">${classlist.classname}</div></td>
									</c:forEach>
								</c:if>
							</tr>
							<c:if test="${not empty selectCourseList}">
								 <c:forEach items="${selectCourseList }" var="courselist">
									 <tr id="tr_${courselist.course_id}">
									 <td style="text-align: center;border-top: 1px solid #DDD;width: 20px"><a href="#" onclick="deleteunsave('${courselist.course_id}')"> <img src="${ctx}/img/ico_del.gif" title="删除"/></a></td>
										<td style="word-wrap:break-word;text-align: center;vertical-align: middle;text-align: left;border-top: 1px solid #DDD;width: 140px">&nbsp;${courselist.course_name}</td>
										<c:if test="${not empty classList}">
											<c:forEach items="${classList }" var="classlist">
												<td style="text-align: center;border-top: 1px solid #DDD;">
												<input type="text" style="width: 110px;margin-top: 2px;margin-bottom: 2px" id="teacher_${classlist.classid}_${courselist.course_id}"/><!-- onkeyup="getTeacherInfo1($$(this));" -->
												<input id="teacher_${classlist.classid}_${courselist.course_id}_hidden"  name="teacher_hidden" type="hidden"/>
												</td>
									 		</c:forEach>
										</c:if>
									 </tr>
								 </c:forEach>
							</c:if>
						<c:if test="${not empty selectClassModelList}">
							<c:if test="${not empty couresModelList}">
								 <c:forEach items="${couresModelListnum}" var="courselist">
									 <tr id="tr_old_${fn:split(courselist,'##')[0]}">
									 <td style="text-align: center;border-top: 1px solid #DDD;width: 20px"><a href="#" onclick="deletesave('${fn:split(courselist,'##')[0]}')"> <img src="${ctx}/img/ico_del.gif" title="删除"/></a></td>
										<td style="text-align: left;border-top: 1px solid #DDD;width: 140px">&nbsp;${fn:split(courselist,'##')[1]}(${fn:split(courselist,'##')[2]})</td>
										<c:if test="${not empty classList}">
											<c:forEach items="${classList}" var="classlist">
												<td style="text-align: center;border-top: 1px solid #DDD;">
												<input type="text" style="width: 110px;margin-top: 2px;margin-bottom: 2px" idvalue="" id="teacher_${classlist.classid}_${fn:split(courselist,'##')[0]}" name="teacher_show" onchange="checkempty($$(this))"/><!-- onkeyup="getTeacherInfo1($$(this));" -->
												<input id="teacher_${classlist.classid}_${fn:split(courselist,'##')[0]}_hidden" idvalue="" name="teacher_hidden" type="hidden"/>
												</td>
									 		</c:forEach>
										</c:if>
									 </tr>
								 </c:forEach>
							</c:if>
						</c:if>
					</table>
				</center>
				</c:if>
				</div>
			</div>
	</div>
	<center>
	<div class="form-actions"  style="padding-right:30px;border: 1px solid #DDD;text-align: right;margin-top: 0px;margin-left:13px;margin-right: 13px;margin-bottom: 0px;">
		<input type="button" class="btn btn-success" style="width: 70px;" id="query1" onclick="doadd()" value="保 存" />
		<input type="button"  onclick="modularExport();"  class="btn btn-success" style="width: 120px;" id="query"  value="导入模块授课教师" />
	</div>
	</center>
</div>
 </form>
<%@ include file="/common/div.jsp"%>
</body>
</html>