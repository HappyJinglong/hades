function deleteContent(currentObj,tableObj,url){
	var appId = currentObj.attr("id").split("_")[0];
	var sectionCode = tableObj.attr("id");
	var textarea = tableObj.find("textarea");
	if(textarea.size()==1 && $.trim($(textarea[0]).val())==""){
		apprasial_del_Finish();
		return;
	}
	if(appId==sectionCode && textarea.size()>0){
		$("#"+sectionCode+"_tpOne").remove();
		$("#"+sectionCode+"_tpTwo").remove();
		return;
	}
	Ext.lib.Ajax.defaultPostHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
	Ext.Ajax.request({
		url:url,
		method:'POST',
		defaults:{autoScroll: true},
		success:function(response,options){
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
				    $("#"+sectionCode+"_tpThree").css("display","none");
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
		    	alert_delete_success();
		    }else{
		    	//提示删除失败 具体到某一个人
		    	alert_delete_failure($("#"+sectionCode+"SN").html());
		    }
		},
		params : {
			proKey:appId,
			evaluatetypeid:sectionCode,
			studentid:$("#sid").val(),
			classId:$("#clad").val(),
			zsTermId:$("#termId option:selected").val(),
			isHistory:1
		}
	});
}