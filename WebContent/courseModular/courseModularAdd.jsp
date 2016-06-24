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
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type='text/javascript' 
src='${ctx}/dwr/interface/SeriesSelectDwr.js'></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<title>模块添加</title>
<style >
	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	
</style>
<script type="text/javascript">
var $$=jQuery.noConflict();
var isSave = 0;
//修改年级改变班级列表
	function changeSeriesid(subjectid){ //v3.0
		if(subjectid =='' || null==subjectid){
			subjectid=$$("#subjectid").val();
		}
		dwr.engine.setAsync(false);
		if(null!=subjectid&&''!=subjectid)
		{
			SeriesSelectDwr.changeSubjectToSeries(subjectid,function(obj){
				if(obj!=null){
					DWRUtil.removeAllOptions("seriesid");
					DWRUtil.addOptions("seriesid", {"":"请选择"});
					DWRUtil.addOptions("seriesid",obj ,"series_id" ,"series_name" );
					/* DWRUtil.setValue("segmentId", '${segmentId}');  */
				}else{
					DWRUtil.removeAllOptions("seriesid");
					DWRUtil.addOptions("seriesid", {"":"请选择"});
				}
			});
		}else
		{
			DWRUtil.removeAllOptions("seriesid");
			DWRUtil.addOptions("seriesid", {"":"请选择"});
		}
		dwr.engine.setAsync(true);
	}
	//页面数据校验
	function dataCheck(){
		if(!checkInpu($$("#subjectid"),"学科")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#coursecode"),"模块编号")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#coursename"),"模块名称")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if($$('input:radio[name="radio"]:checked').val()==undefined){
			$$("#MyDiv").css("display","none");
			alert_common('保存失败,模块类别为必填项','gritter-item');
			return false;
		}
		if(!checkInpu($$("#periodcount"),"学时数")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		if(!checkInpu($$("#credithour"),"学分")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		var str = 0;
		$$("input[name='checkbox']:checkbox:checked").each(function(){ 
			str++;
		});
		if(str==0){
			$$("#MyDiv").css("display","none");
			course_alert_failure("开设学段为必选项");
			return false;
		}
		if(!checkOtherData($$("#remark"),2000,"备注")){
			$$("#MyDiv").css("display","none");
			return false;
		}
		return true;
	}
	function add(){
		$$("#timeCount").html("数据保存中...");
		ShowDiv();
		if(isSave==0){			
			isSave=1;
			var str=""; 
			$$("input[name='checkbox']:checkbox:checked").each(function(){ 
				str+=$$(this).val()+"-"; 
			});
			var radio="";
			$$("input[name='radio']").each(function(){ 
				  if ($$(this).attr("checked") == true){
					  radio=$$(this).val(); 
				  }
			});
			if(dataCheck()){
				Ext.Ajax.request({
					url:'${ctx}/innercourse/InnerCourse.a?doInsert',
					method:'POST',
					defaults:{autoScroll: true},
					success:function(response,options){
						if(response.responseText=="##"){
							$$("#MyDiv").css("display","none");
							alert_upload_failure("保存失败");
							isSave=0;
						}else{
							if(response.responseText=="1"){
								$$("#MyDiv").css("display","none");
								alert_KG_self_tip("模块编号已经存在！");
							}else if(response.responseText=="2"){
								$$("#MyDiv").css("display","none");
								alert_KG_self_tip("模块名称已经存在！");
							}else if(response.responseText=="3"){
								$$("#MyDiv").css("display","none");
								alert_KG_self_tip("模块名称和模块编号已经存在！");
							}else{
								$$("#MyDiv").css("display","none");
								$$("#noticeflag").val(1); 
								$$("#fm").submit();
							}
							isSave=0;
						}
					},
					params : {
						subjectid : $$("#subjectid").val(),
						seriesid : $$("#seriesid").val(),
						coursecode : $$("#coursecode").val(),
						coursename : $$("#coursename").val(),
						coursekind : radio,
						studentaspect : $$("#studentaspect").val(),
						periodcount : $$("#periodcount").val(),
						credithour : $$("#credithour").val(),
						courseremark : $$("#remark").val(),
						courseshortname : $$("#courseshortname").val(),
						checkbox : str
					}
				}); 
			}else{
				isSave=0;
			}
		}else{
			alert_KG_self_tip("正在保存中...");
		}
	}
	function back(){
		$$("#fm").submit();
	}
	
	//校验 模块名称
	/*function verify(){
		
		
		
		var value = $$("#coursename").val();		
		var reg = /[#]/;		
		if(reg.test(value)){
			$$("#coursename").html("不能输入特殊字符#");
		}						
	}*/
</script>
</head>
<body>
		<div style="width: 100%;">
					<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i> 模块设置</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">添加模块</a>
			</div>
			<div class="span12" style="width: 100%;margin-left: 0px;margin-top: 0px">
						<div class="widget-box" style="font-size: 14px;font-weight:bold;color: #666;margin-top: 0px"> 
							<div  style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								模块信息
							</div>
							<div class="widget-content nopadding">
								 <form method="post" class="form-horizontal" >
									<div class="control-group">
										<label class="control-label" style="width: 480px">学科：</label>
										<div class="controls" style="margin-left:460px">
										<select name="subjectid" id="subjectid" style="width: 480px"  onchange="changeSeriesid(this.value);">
					               			<option value="">请选择</option>
					               			<app:sysSubjectSelectTag subjectId="${subjectid}"/>
					              		</select><span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块编号：</label>
										<div class="controls">
											<input type="text" id="coursecode" style="width: 480px" maxlength="20" />
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块名称：</label>
										<div class="controls">
											<input type="text" id="coursename" style="width: 480px" maxlength="60" />
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块简称：</label>
										<div class="controls">
											<input type="text" id="courseshortname" style="width: 480px" maxlength="60" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">模块类别：</label>
										<div class="controls" style="font-size: 12px;font-weight:normal;">
											<input type="radio" id="radio1" name="radio" value="1230301"/>&nbsp;&nbsp;必修&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" id="radio2" name="radio" value="1230310"/>&nbsp;&nbsp;必选&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" id="radio3" name="radio" value="1230315"/>&nbsp;&nbsp;选修<span style="color: red">*</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">学时数：</label>
										<div class="controls">
											<input style="width: 480px" id="periodcount"  type="text" maxlength="6"  />
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">学分：</label>
										<div class="controls">
											<input type="text" id="credithour" style="width: 480px" maxlength="4" />
											<span style="color: red"> *</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">开设学段：</label>
										<div class="controls" style="font-size: 12px;font-weight:normal;margin-left: 480px" >
											<input type="checkbox" id="checkbox1" name="checkbox" value="1230001^1230101" />
									          		&nbsp;&nbsp;高一第1学段&nbsp;&nbsp;&nbsp;&nbsp;
									           <input type="checkbox" id="checkbox2" name="checkbox" value="1230001^1230110"/>
									          		&nbsp;&nbsp;高一第2学段&nbsp;&nbsp;&nbsp;&nbsp;
									           <input type="checkbox" id="checkbox3" name="checkbox" value="1230001^1230120"/>
									          		&nbsp;&nbsp;高一第3学段&nbsp;&nbsp;&nbsp;&nbsp;
									           <input type="checkbox" id="checkbox4" name="checkbox" value="1230001^1230130"/>
									          		&nbsp;&nbsp;高一第4学段&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<div class="controls" style="font-size: 12px;font-weight:normal;margin-left: 480px">
											<input type="checkbox" id="checkbox5" name="checkbox" value="1230010^1230101"/>
									          		 &nbsp;&nbsp;高二第1学段&nbsp;&nbsp;&nbsp;&nbsp;
									        <input type="checkbox" id="checkbox6" name="checkbox" value="1230010^1230110"/>
									          		 &nbsp;&nbsp;高二第2学段&nbsp;&nbsp;&nbsp;&nbsp;
									        <input type="checkbox" id="checkbox7" name="checkbox" value="1230010^1230120"/>
									          		 &nbsp;&nbsp;高二第3学段&nbsp;&nbsp;&nbsp;&nbsp;
									        <input type="checkbox" id="checkbox8" name="checkbox" value="1230010^1230130"/>
									          		 &nbsp;&nbsp;高二第4学段&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<div class="controls" style="font-size: 12px;font-weight:normal;margin-left: 480px">
											 <input type="checkbox" id="checkbox9" name="checkbox" value="1230020^1230101" />
									         		 &nbsp;&nbsp;高三第1学段&nbsp;&nbsp;&nbsp;&nbsp;
									         <input type="checkbox" id="checkbox10" name="checkbox" value="1230020^1230110" />
									         		 &nbsp;&nbsp;高三第2学段&nbsp;&nbsp;&nbsp;&nbsp;
									         <input type="checkbox" id="checkbox11" name="checkbox" value="1230020^1230120" />
									          		&nbsp;&nbsp;高三第3学段&nbsp;&nbsp;&nbsp;&nbsp;
									         <input type="checkbox" id="checkbox12" name="checkbox" value="1230020^1230130" />
									          		&nbsp;&nbsp;高三第4学段&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red">*</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">学习方向：</label>
										<div class="controls">
											<select  style="width: 480px" id="studentaspect">
											<option value="">请选择</option>
									 		<c:if test="${not empty sysdictList}">
							          			<c:forEach items="${sysdictList}" var="sd" varStatus="r">
							                	<option value="${sd.dict_id}">${sd.dict_name}</option>
							         			</c:forEach>
							         		</c:if>
									 		</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">系列：</label>
										<div class="controls">
											<select style="width: 480px" id="seriesid" name="seriesid">
									 		<option value="">请选择</option>
									 		</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="width: 480px">备注：</label>
										<div class="controls">
											<textarea rows="10" id="remark" style="width: 480px"></textarea>
										</div>
									</div>
									<div class="form-actions"  style="padding-right: 30px;text-align: right;">
												<input type="button" class="btn btn-success" style="width: 70px;" id="query"  onclick="add()" value="保 存" />
												<input type="button" class="btn btn-success" style="width: 70px;" id="query" onclick="back()" value="返 回" />
									</div>
								 </form>
							</div>
						</div>						
		        </div>
		 </div>
		 <form action="${ctx}/innercourse/InnerCourse.a" method="post" id="fm">
		  <input type="hidden" id="noticeflag" name="noticeflag" value="0"/>
		 	<input type="hidden" name="subjectid" id="subjectidback" value="${subjectid}"/>
			<input type="hidden" name="coursekind" id="coursekindback" value="${coursekind}"/>
			<input type="hidden" name="isdefault" id="isdefaultback" value="${isdefault}"/>
			<input type="hidden" name="coursename" id="coursenameback" value="${coursename}"/> 
			<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/> 
			<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/> 
		 </form>
		 	<%@ include file="/common/div.jsp"%>
</body>
</html>