<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<title>课程模块设置</title>
<style >
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
		height: 28px;
		width: 68px;
		padding:0px;
	}
	.table.with-check tr th:first-child, .table.with-check tr td:first-child {
    width: 50px;
    }
    input,select
    {
       vertical-align:baseline;
    }
  
</style>
<script type="text/javascript">
	function controlCheckBox(obj){
		$("input:checkbox").each(function(){
			if($(this).attr('disabled'))return;
			$(this).attr('checked',$(obj).attr('checked'));
		});
	}
	$(document).ready(function() {
		var value = '${publicState}';
		$("#publicState option").each(function() {
			if ($(this).val() == value) {
				$(this).attr("selected", true);
			}
		});
		if('${isBack}'!="back"){
			if('${noticeflag}'=="1")
			{
			    success_notice_word("保存成功");
			}
			if('${noticeflag1}'=="1")
			{
				success_notice_word("删除成功");
			}
			if('${noticeflag1}'=="2")
			{
				success_notice_word("发布成功");
			}
		}
	});
	function add_Inform() {
		$("#form").attr("action","${ctx}/inform/InformAction.a?AddInform&flag=2");
		$("#form").submit();
	}
	//判断复选框是否选中
	function checkSelect(checkName) {
		var selected = false;
		$("input[name=" + checkName + "]:checkbox").each(function() {
			if ($(this).attr("checked")) {
				selected = true;
			}
		});
		return selected;
	}
	var flag = 0; //是否发布标识号
	function deleteInform() {
		if (!checkSelect("radio")) {
			alert("请选择要删除的通告");
			return;
		}
		var box = $("[name='radio']:checked");
		var val = "";
		$.each(box, function(i, v) {
			val += i > 0 ? "," + v.value : v.value;
			var status = $("#" + v.value + "_state").val();
			if (status == "1") {
				flag = 1;
			}
		});
		if (flag == 1) {
			flag = 0;
			alert("只有未发布的才可以删除");
			return;
		}
		if(!confirm("确定要删除？"))
		{
			return;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/inform/InformAction.a?deleteInform&informIds=" + val,
			async : false,
			dataType : "json",
			success : function(data) {
				var content = data.fin;
				if (content == true) {
					$("#noticeflag1").attr("value","1");
					$("#query").click();
				} else {
					fail_notice_word("删除通告失败");
				}
			},
			error : function(err) {
				fail_notice_word("删除通告失败");
			}
		});
	}
	function publishInform() {
		if (!checkSelect("radio")) {
			alert("请选择要发布的通告");
			return;
		}
		var box = $("[name='radio']:checked");
		var val = "";
		$.each(box, function(i, v) {
			val += i > 0 ? "," + v.value : v.value;
			var status = $("#" + v.value + "_state").val();
			if (status == "1") {
				flag = 1;
			}
		});
		if (flag == 1) {
			alert("只有未发布的才可以发布");
			flag = 0;
			return;
		}
		$.ajax({
					type : "post",
					url : "${ctx}/inform/InformAction.a?publishInform&informIds="
							+ val,
					async : false,
					dataType : "json",
					success : function(data) {
						var content = data.fin;
						if (content == true) {
							$("#noticeflag1").attr("value","2");
							$("#query").click();
						} else {
							fail_notice_word("发布通告失败");
						}
					},
					error : function(err) {
						fail_notice_word("发布通告失败");
					}
				});
	}
	function updateInform()
	{
		if (!checkSelect("radio")) {
			alert("请选择要修改的通告");
			return false;
		}
		var box = $("[name='radio']:checked");
		var length = box.length;
		if(length>1)
	    {
			alert("最多选取一个修改");
			return;
	    }
		var val = "";
		$.each(box, function(i, v) {
			val = v.value;
			var status = $("#" + v.value + "_state").val();
			if (status == "1") {
				flag = 1;
				return;
			}
		});
		if (flag == 1) {
			flag = 0;
			alert("只有未发布的才可以修改");
			return;
		}
		$("#informIds").attr("value",val);
		$("#form").attr("action","${ctx}/inform/InformAction.a?updateInform");
		$("#form").submit();
	}
</script>
</head>
<body style="background: #EEE">

	<div style="width: 100%;">
	  <form id="form" action="${ctx}/inform/InformAction.a" method="post">
	  
	  <div id="breadcrumb" style="margin-top: 10px;">
	  	<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>通知公告</a>
	  </div>
	  <input type="hidden" id="informIds" name="informIds"/>
	  <input type="hidden" id="noticeflag1" name="noticeflag1"/>
	  <input type="hidden" id="flag" name="flag" value="1"/>
		<div style="width: 100%;height:50px;border: 1px solid #CDCDCD;line-height: 50px">
			 <ul style="margin-top: 5px">
			 	<li style="margin-left: 5%">
			 	   主题：
			 		 <input type="text" id="theme" name="theme" style="width: 120px;border-left: 1px solid;border-top: 1px solid;" value="${theme }"/>    
			 	</li>
			 	
			 	<li class="li">
			 	     状态：
				 	  <select id="publicState" name="publicState">
				 			<option value="">全部</option>
				 			<option value="1">已发布</option>
				 			<option value="0">未发布</option>
				 		</select>     
			 	</li>
			 	<li class="li">发布日期： <input id="startDate" name="startDate" type="text" value="${startDate }"
					style="width: 90px; border-left: 1px solid; border-top: 1px solid;"
					onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
					size="10" /> 至 <input  id="endDate" type="text" name="endDate" value="${endDate }" style="width: 90px;border-left: 1px solid;border-top: 1px solid;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" size="10"/>    
			 	</li>
			 	 <li class="li">
			  		<input type="submit" name="queryInformByCon" class="btn btn-success" style="width: 70px;margin-bottom: 7px;margin-top: 7px;" id="query"  value="查 询"/> 
				 </li>
			 </ul>
		</div>
		
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								通知公告列表
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped with-check" >
									<thead>
										<tr>
											<th width="4%" style="font-size: 14px;text-align: center;">
											<input type="checkbox" name="checkbox" id="checknoMe" value="" onclick="controlCheckBox(this)"/>
											</th>
											</th>
											<th width="4%" style="font-size: 14px">序号</th>
											<th style="font-size: 13px">主题</th>
											<th width="10%" style="font-size: 13px">状态</th>
											<th width="11%"  style="font-size: 13px">有效截止日期</th>
											<th  width="11%" style="font-size: 13px">发布日期</th>
											<th width="22%" style="font-size: 13px">接收对象</th>
										</tr>
									</thead>
									<tbody>
									<c:if test="${not empty pageObj.pageElements}">
									   <c:forEach items="${pageObj.pageElements }" var="inform" varStatus="pVs">
										<tr >
										   <input type="hidden" id="${inform.informid}_state" value="${inform.publishState}"></input>
											<td style="text-align: center;vertical-align: middle;"><input type="checkbox" name="radio" value="${inform.informid }" <c:if test="${inform.publishState eq '1' }">disabled="disabled"</c:if>></input></td>
											<td style="text-align: center;vertical-align: middle;">${pVs.count + pageObj.pageSize * (pageObj.currPageNumber-1)}</td>
											<td style="text-align: center;vertical-align: middle;"><a href='${ctx}/inform/InformAction.a?queryInformation&informIds=${inform.informid}&isInsert=no'><font color="#000000">${inform.theme}</font></a></td>
											<td style="text-align: center;vertical-align: middle;" id="${inform.informid}">${inform.statename }</td>
											<td style="text-align: center;vertical-align: middle;">${inform.endDate }</td>
											<td style="text-align: center;vertical-align: middle;">${inform.startDate }</td>
											<td style="text-align: center;vertical-align: middle;">${inform.receiverName }</td>
										</tr>
									  </c:forEach>
									</c:if>	
									</tbody>
								</table>	
								<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
									<div id="pagearea">
									<span><input id="pageNo" name="pageNo" value="${pageNo }"
										type="hidden" />
									<c:if test="${not empty pageObj}">
											<c:if test="${not empty pageObj.pageElements}">
												<jsp:include page="../common/pager-nest.jsp">
													<jsp:param name="toPage" value="1" />
													<jsp:param name="showCount" value="1" />
													<jsp:param name="action" value="queryInformByCon" />
												</jsp:include>
											</c:if>
										</c:if></span>
										<span style="margin-right: 30px;float:right;margin-top:5px;" id="button">
										<button type="button" onClick="add_Inform();"  style="width: 70px;" class="btn btn-success">增加</button>
										<button type="button" style="width: 70px;" class="btn btn-success" onClick="updateInform();">修改</button>
										<button type="button" style="width: 70px;" class="btn btn-success" onClick="deleteInform();">删除</button>
                                        <button type="button" onClick="publishInform();" style="width: 70px;" class="btn btn-success">发布</button>
							      </span>
								   </div>
								</div>						
							</div>
						</div>
		</div>
		</form>
	</div>
	
</body>
</html>