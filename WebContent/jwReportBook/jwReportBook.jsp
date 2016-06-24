<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<un:bind var="KG_COURSE_KIND" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_KIND"/>
<un:bind var="KG_COURSE_NEIZHI" type="com.flyrish.hades.common.Constant"
	field="KG_COURSE_NEIZHI"/>
<un:bind var="USER_MASTERROLE_TYPESTR" type="com.flyrish.hades.common.Constant"
	field="USER_MASTERROLE_TYPESTR"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/handsontable.full.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/unicorn.main.css" />
<script data-jsfiddle="common" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<script data-jsfiddle="common" src="${ctx}/js/handsontable.full.js"></script>

<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>

<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>


<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript"
	src="${ctx}/ReportServer?op=emb&resource=finereport.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/ReportServer?op=emb&resource=finereport.css" />

<title>高中报告册</title>
<style >
.loading{
		line-height:0px;
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
		height: 27px;
		width: 65px	
	}
	.dslcheckbox{
		width:auto;
		height:auto;
	}
	td{
	text-overflow:ellipsis;
	white-space:nowrap;
	}
</style>

<script type="text/javascript">
$(window).load(function() {
	show();
});


//导出
function baogaocdaochu(){
		var dqxueduan="${qdxueduan}";
		var clas="${classid }";
		var chk_value =[];
		$('input[name="checkbox"]:checked').each(function(){
		chk_value.push($(this).val());
		});
		if(chk_value.length==0){
			alert("你还没有选择任何内容！");
		}else{
				dStatus = uuid();
				location.href = "${ctx }/bookreport/BookreportAction.a?exportZip&classId="+clas+"&eduIds="+chk_value+"&dStatus="+dStatus;
				ShowDiv(); 
	    		timeId = setInterval(queryDownStatus,1000);		
		}
}
function stushangbao1(){
	alert("请选择毕业班级上报！");
}

//上报
function stushangbao(){  
	var chk_value =[];
	$('input[name="checkbox"]:checked').each(function(){
	chk_value.push($(this).val());
	});
	if(chk_value.length==0){
		alert("你还没有选择任何内容！");
	}else{
		location.href ="${ctx }/bookreport/BookreportAction.a?StuBSB&xue="+chk_value;
		/*  $.get("${ctx }/bookreport/BookreportAction.a?StuBSB&xue="+chk_value,
				  function(data){
			 location.href = "${ctx }/bookreport/BookreportAction.a?GetBjList&sectorsId=51961&learningPeriodId=2012003";//location.href实现客户端页面的跳转  
	 	 }); */
	}
	
	
} 

function fanhui(){
	var xuenian="${sessionScope.xueduan}";
	var jiebie="${sessionScope.jb}";
/* 	alert("a");
	alert(xuenian);
	alert(jiebie); */
	location.href ="${ctx }/bookreport/BookreportAction.a?GetBjList&learningPeriodId="+xuenian+"&sectorsId="+jiebie;
//javascript:history.go(-1)
}
//全选
function onchenked(){
    var oBoxs=$("body").find("input");	
    for(var i=0;i<oBoxs.length;i++){
           oBoxs[i].checked=true;
        }
}
//全不选
function onchenkedNull(){
	 var oBoxs=$("body").find("input");	
	    for(var i=0;i<oBoxs.length;i++){
	           oBoxs[i].checked=false;
	        }
}

var dStatus = "";
var timeId = "";
var count = 0;
function queryDownStatus(){
	 $("#MyDiv").css("height","120%");
	 $("#timeCount").html("已耗时间"+(++count)+"秒");
	 $.ajax({
	     url: "${ctx }/bookreport/BookreportAction.a?queryDownLoadStatus",
	     type: "POST",
	     data: {
	    	 dStatus:dStatus
	     },
	     success: function(data) {
	    	if(data.val=="1"){
	    		$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		 $("#timeCount").html("正在导出数据...");
	    	}else if(data.val=="2"){//导出文件失败
	    		$("#MyDiv").css("display","none");
	    		clearInterval(timeId);
	    		count=0;
	    		 $("#timeCount").html("正在导出数据...");
	    		fail_notice_word("导出数据失败！");
	    	}
	     }
	 });
}

function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";
    var uuid = s.join("");
    return uuid;
}

/* function addTabe(){
	var tr="";
	var i=2;
	var leng="${fn:length(studentInfo)}";
	var mo=leng%3;
	var z=0;
	<c:forEach items="${studentInfo}" var="st" varStatus="vs">
	   var studentno = "${st.studentno}";
		var text="${st.text}";
		var edu_id ="${st.edu_id}";
		   z++;
		  i+=1;
		  var s=i%3;
		  if(0==s){
			  tr+="<tr>"; 
		  }
		    tr+="<td style='text-align: center; width:2%'><input class='dslcheckbox' value='"+edu_id+"' type='checkbox' name='checkbox' /></td>"
		        +"<td style='text-align: center;width:10%'>"+text+"</td>"
		        +"<td style='text-align: center;width:15%'>"+${st.studentno}+"</td>";
		 if(z!=leng){
			   if(2==s){
				  tr+="<tr>"; 
			    }
		 }else{
			 if(0==mo){
				  tr+="<tr>"; 
			    }else if(1==mo){
			    	tr+="<td style='text-align: center; width:2%'></td>"
				        +"<td style='text-align: center;width:10%'></td>"
				        +"<td style='text-align: center;width:15%'></td>"
				        +"<td style='text-align: center; width:2%'></td>"
				        +"<td style='text-align: center;width:10%'></td>"
				        +"<td style='text-align: center;width:15%'></td>"
				        +"<tr>";
			    }else{
			    	tr+="<td style='text-align: center; width:2%'></td>"
				        +"<td style='text-align: center;width:10%'></td>"
				        +"<td style='text-align: center;width:15%'></td>"
				        +"<tr>";
			    }
			 
		 }
    </c:forEach>
 	tr+="<tr>"
		+"<td colspan='11' style='padding:0px;'>"
				+"<div class='form-actions pagination alternate''  style='margin-top: 0px;margin-bottom: 0px;'>"									
				+"<span style='margin-right: 30px;float:right;margin-top:  line-height: 36;'>"
				+"<button onClick='onchenked()' style='width: 100px;'  class='btn btn-inverse'>全选</button>"
				+"<button onClick='onchenkedNull()' style='width: 100px;' class='btn btn-inverse'>取消全选</button>"
				+"<button onClick='onchenked()' style='width: 130px;' class='btn btn-inverse'>报告册模板下载</button>"
				+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册导入</button>"
				+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册导出</button>"
				+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册校验</button>校验结果下载"
				+"</span>"
			+"</div>"			
		+"</td>"
	+"</tr>"; 
	 var newpaerdiv=document.getElementById("minTable");
     newpaerdiv.innerHTML=""; 
     newpaerdiv.innerHTML=tr;  
 }  */
function show(){
	   var tr="";				    
	   var is ="${isSB}";
		var i=2;
		var leng="${fn:length(studentInfo)}";
		if(0<leng){
			var mo=leng%3;
			var z=0;					
			<c:forEach items="${studentInfo}" var="st" varStatus="vs">
				var studentno = "${st.studentno}";
				var text="${st.text}";
				var edu_id ="${st.edu_id}";
				
				  z++;
				  i+=1;
				  var s=i%3;
				  if(0==s){
					  tr+="<tr>"; 
				  }
				  if(2!=s){
					  tr+="<td style='text-align: center;width:5%;'><input class='dslcheckbox' value='"+edu_id+"' type='checkbox' name='checkbox' /></td>"
				        +"<td style='text-align: center;width:10%;'>"+text+"</td>"
				        +"<td style='text-align: center;width:10%;'>"+studentno+"</td>"
				        +"<td style='text-align: center;width:5%;'></td>";
				  }else{
					   tr+="<td style='text-align: center;width:5%;'><input class='dslcheckbox' value='"+edu_id+"' type='checkbox' name='checkbox' /></td>"
				        +"<td style='text-align: center;width:10%;'>"+text+"</td>"
				        +"<td style='text-align: center;width:10%;'>"+studentno+"</td>";
				  }						  
				 if(z!=leng){
					   if(2==s){
						  tr+="<tr>"; 
					    }
				 }else{
					 if(0==mo){
						  tr+="<tr>"; 
					    }else if(1==mo){
					    	tr+="<td style='text-align: center; '></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<td style='text-align: center; '></td>"
						        +"<td style='text-align: center; '></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<tr>";
					    }else{
					    	tr+="<td style='text-align: center; '></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<td style='text-align: center;'></td>"
						        +"<tr>";
					    }							 
				 }
			 </c:forEach>
			if(is=="1"){
				tr+="<tr>"
					+"<td colspan='11' style='padding:0px;'>"
							+"<div class='form-actions pagination alternate''  style='margin-top: 0px;margin-bottom: 0px;'>"									
							+"<span style='margin-right: 30px;float:right;margin-top:  line-height: 36;'>"
							+"<button onClick='onchenked()' style='width: 100px;'  class='btn btn-success'>全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='onchenkedNull()' style='width: 100px;' class='btn btn-success'>取消全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='baogaocdaochu()' style='width: 130px;' class='btn btn-success'>报告册导出</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='stushangbao()' style='width: 110px;' class='btn btn-success'>上报报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='printReport()' style='width: 110px;' class='btn btn-success'>打印报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='fanhui()' style='width: 110px;' class='btn btn-success'>返回</button>"
							/*+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册校验</button>校验结果下载"*/
							+"</span>"
						+"</div>"			
					+"</td>"
				+"</tr>"; 				
			}else if(is=="0"){
				tr+="<tr>"
					+"<td colspan='11' style='padding:0px;'>"
							+"<div class='form-actions pagination alternate''  style='margin-top: 0px;margin-bottom: 0px;'>"									
							+"<span style='margin-right: 30px;float:right;margin-top:  line-height: 36;'>"
							+"<button onClick='onchenked()' style='width: 100px;'  class='btn btn-success'>全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='onchenkedNull()' style='width: 100px;' class='btn btn-success'>取消全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='baogaocdaochu()' style='width: 130px;' class='btn btn-success'>报告册导出</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='stushangbao1()' style='width: 110px;' class='btn btn-success'>上报报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='printReport()' style='width: 110px;' class='btn btn-success'>打印报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='fanhui()' style='width: 110px;' class='btn btn-success'>返回</button>"
							/*+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册校验</button>校验结果下载"*/
							+"</span>"
						+"</div>"			
					+"</td>"
				+"</tr>"; 		
			}else{
				tr+="<tr>"
					+"<td colspan='11' style='padding:0px;'>"
							+"<div class='form-actions pagination alternate''  style='margin-top: 0px;margin-bottom: 0px;'>"									
							+"<span style='margin-right: 30px;float:right;margin-top:  line-height: 36;'>"
							+"<button onClick='onchenked()' style='width: 100px;'  class='btn btn-success'>全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='onchenkedNull()' style='width: 100px;' class='btn btn-success'>取消全选</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='baogaocdaochu()' style='width: 130px;' class='btn btn-success'>报告册导出</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='stushangbao1()' style='width: 110px;' class='btn btn-success'>上报报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='printReport()' style='width: 110px;' class='btn btn-success'>打印报告册</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<button onClick='fanhui()' style='width: 110px;' class='btn btn-success'>返回</button>"
							/*+"<button onClick='onchenked()' style='width: 110px;' class='btn btn-inverse'>报告册校验</button>校验结果下载"*/
							+"</span>"
						+"</div>"			
					+"</td>"
				+"</tr>"; 	
			}
			var newpaerdiv=$("#minTable");										
		    newpaerdiv.html(""); 
		    newpaerdiv.html(tr);
		}
}

	function printReport() {
		if(true){
			alert("功能正在完善中，暂缓使用！");
			return false;
		}
		var checkedList = $(":checkbox:checked", "#reportTable").toArray();
		var ids = [];
		if (checkedList == 0) {
			alert("请最少选择一项！");
			return false;
		} else {
			$("#printModal").show();
			for (var i = 0; i < checkedList.length; i++) {
				var checkbox = checkedList[i];
				ids.push(checkbox.value);
			}
			$.ajax({
				url : '${ctx}/bookreport/BookreportAction.a?printReportByPerson',
				type : 'post',
				dataType : 'text',
				data : {
					'eduids' : ids.join(",")
				},
				success : function(result) {

					if (result) {
						var reportlets = '[' +result + ']';
						var config = {
							url : '${ctx}/ReportServer',
							isPopUp : false,
							data : {
								reportlets : reportlets
							}
						};
						FR.doURLFlashPrint(config);
					}
				}
			});
		}
	}
 
</script>
</head>
<body style="background: #EEE;overflow: auto;">	
		<div style="width: 96%;margin-top: 13px;margin-left:2%">
			<div class="widget-box" style="margin-top:0px;margin-bottom:0px;">
				<div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
					学生列表
				</div>
				<div class="widget-content nopadding" style="position: relative;">
					<table class="table table-bordered table-striped with-check" id="reportTable">
						<thead>
							<tr>
								<th width="5%"  style="font-size: 14px">选择</i>										  
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								<th width="5%" style="font-size: 13px">&nbsp;</th>
								<th width="5%"  style="font-size: 14px">选择</i></th>
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								<th width="5%" style="font-size: 13px">&nbsp;</th>
								<th width="5%"  style="font-size: 14px">选择</i></th>
								<th width="10%" style="font-size: 14px">姓名</th>
								<th width="10%" style="font-size: 13px">学籍号</th>
								
							</tr>
						</thead>
						<tbody id="minTable">																	  							
						</tbody>
					</table>											
			</div>					
		</div>
	</div>
	<%@ include file="/common/exporting.jsp"%>
	<div style="position: fixed;top: 0;bottom: 0;left: 0;right: 0;z-index: 100;display: none;" id="printModal">
		<div style="background-color: #FBFBFB;opacity: .4;filter:alpha(opacity=40);-moz-opacity: .4;-khtml-opacity: .4;position: absolute;width: 100%;height: 100%;"></div>
		<div style="position: absolute;border: solid 1px #999;width: 250px;height: 38px;top: 50%;left: 50%;margin-top: -29px;margin-left: -125px;background-color: #ccc;box-shadow: 2px 1px 20px 1px #666;border-radius: 10px;font-size: 12px;padding: 10px;color: #fff;"><span>由于打印需要的时间较长，请耐心等待，待打印完毕之后，请手动点击：<a href="javascript:void(0);" onclick="$(this).parent().parent().parent().hide()">【关闭】</a></span></div>
	</div>
</body>

</html>






									
									




