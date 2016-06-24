function alert_common(msg,item_class){
	$.gritter.add({
		text:msg,
		gritter_item_class:item_class,
		sticky: false
	});	
}
function alert_KG_self_tip(tt){
	alert_common(tt,'gritter-item');
}
function alert_KG_chooseFile_tip(tt){
	alert_common(tt,'gritter-item');
}

function alert_KG_importData_failure(){
	alert_common('导入数据失败','gritter-item');
}
 
function alert_KG_importData_success(){
	alert_common('导入数据成功','gritter-item1');
}

function alert_KG_save_success(){
	alert_common('保存数据成功','gritter-item1');
}


function course_alert_failure_emptyTip(currentObj,tt)
{	
	if("INPUT"==currentObj[0].tagName){
		alert_common('保存失败,'+tt+'为必填项','gritter-item');
	}else{
		alert_common('保存失败,'+tt+'为必选项','gritter-item');
	}
}
function course_alert_failure(tt)
{	
	alert_common('保存失败,'+tt,'gritter-item');
}
function course_alert_failure_overLength(currentObj,num,tt){
	if(currentObj.val().length>num){
		alert_common('保存失败,'+tt+':汉字、数字、字母长度不能超过'+num+'个','gritter-item');
		return false;
	}else{
		return true;
	}
}
function course_alert_failure_numCheck(currentTopic,num){
	alert_common('保存失败,'+currentTopic+':只能填写正整数且长度不能超过'+num+'个','gritter-item');
}
function checkNum(currentObj,currentTopic,num){
	if(/^\d+$/.test(currentObj.val())){
		if(currentObj.val().length>num){
			course_alert_failure_numCheck(currentTopic,num);
			return false;
		}else{
			return true;
		}
	}else{
		course_alert_failure_numCheck(currentTopic,num);
		return false;
	}
}
function checkInpu(currentObj,currentTopic){
	if(!$.trim(currentObj.val())){
		course_alert_failure_emptyTip(currentObj,currentTopic);
		return false;
	}else{
		if("INPUT"==currentObj[0].tagName){
			if("模块编号"==currentTopic){
				//提示“汉字、数字、字母长度均为20个”
				return course_alert_failure_overLength(currentObj,20,currentTopic);
			}else if("学时数"==currentTopic){
				//提示“汉字、数字、字母长度均为4个”
				return checkNum(currentObj,currentTopic,4);
			}else if("学分"==currentTopic){
				//提示“汉字、数字、字母长度均为6个”
				return checkNum(currentObj,currentTopic,6);
			}else{
				//提示“汉字、数字、字母长度均为60个”
				return course_alert_failure_overLength(currentObj,60,currentTopic);
			}
		}else{
			return true;
		}
	}
}
function fail_notice_word(tt)
{
	alert_common(tt,'gritter-item');
}
function success_notice_word(tt)
{
	alert_common(tt,'gritter-item1');
}
function alert_save_failure(tt)
{	
	alert_common(tt+'保存失败','gritter-item');
}
function alert_delete_failure(tt)
{	
	alert_common(tt+'删除失败','gritter-item');
}
function alert_update_failure(tt)
{	
	alert_common(tt+'保存失败','gritter-item');
}
function alert_save_success(tt,num)
{
	var sectionName=tt.replace(/(^\s*)|(\s*$)/g, "");
	if(num.length!=0)
	{
		alert_common(sectionName+'  栏目'+num+'  保存成功','gritter-item1');
	}else{
		alert_common(sectionName+'  保存成功','gritter-item1');
	}
}
function alert_delete_success(tt)
{
	alert_common(tt+'删除成功','gritter-item1');
}
function alert_update_success(tt,num)
{
	var sectionName=tt.replace(/(^\s*)|(\s*$)/g, "");
	if(num.length!=0)
	{
		alert_common(sectionName+'  栏目'+num+'  保存成功','gritter-item1');
	}else{
		alert_common(sectionName+'  保存成功','gritter-item1');
	}
}
function alert_upload_success(tt)
{
	alert_common(tt,'gritter-item1');
}
function alert_upload_failure(tt)
{	
	alert_common(tt,'gritter-item');
}
function apprasial_alert(number)
{
	alert("评价内容已超"+number+"字");
}
function apprasial_delete()
{
	return confirm("确认要删除？");
}
function apprasial_delete1()
{
	return confirm("删除此模块将删除该模块相关联的成绩且不可恢复，确认要删除？");
}
function apprasial_del_Finish()
{
	alert("已删除所有评价！！");
}
function save_alert()
{
}
function input_notice(id,number)
{	
	$("#"+id).css("color","#ACACAC");
	$("#"+id).val("最多输入"+number+"字");
}

function input_noticemaster(id,number)
{	
	$("#"+id).css("color","#ACACAC");
	$("#"+id).val("最多输入"+number+"字，班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。");
}

function clear_noticemaster(id,number)
{
	var string_number="最多输入"+number+"字，班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。";
	if($("#"+id).val()==string_number)
	{
		$("#"+id).css("color","");
		$("#"+id).val("");	
	}
}

function clear_notice1(id,number)
{
	var string_number="最多输入"+number+"字";
	if($("#"+id).val()==string_number)
	{
		$("#"+id).css("color","");
		$("#"+id).val("");	
	}
}

function text_style(id)
{
	document.getElementById(id).style.border ="2px solid red";
}
function text_style1(id)
{
	document.getElementById(id).style.border ="1px solid black";
}
function clear_style(id)
{
	document.getElementById(id).style.border ="1px solid transparent";
}
function window_close()
{
	return "当前页面有未执行成功的操作！";
}
//弹出隐藏层  
function ShowDiv() {  
    var showdiv = document.getElementById("MyDiv");  
    if (showdiv == undefined) {  
        return;  
    }  
    //alert($(window).height()+"px");
    showdiv.style.display = 'block'; 
    
}; 