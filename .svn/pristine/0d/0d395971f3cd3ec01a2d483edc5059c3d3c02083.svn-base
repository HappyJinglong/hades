<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<title>校验错误人数</title>
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
/* $(window).load(function() {
	var clas="${errorjsp }";
	if(clas=="1"){
		alert("该学生没有班主任,请重试！");
	}
}); */
//导出
function baogaocdaochu(){
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
	    		//$("#shangbao").append("<input type='hidden' name='dStatus' value='"+dStatus+"'/>");
	    		//document.getElementById("shangbao").submit();
	    		ShowDiv(); 
	    		timeId = setInterval(queryDownStatus,1000);			 
		}
}

function stushangbao1(){
	alert("请选择毕业班级上报！");
}
//上报
function stushangbao(){ //jquery获取复选框值
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
javascript:history.go(-1)
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
 
</script>
</head>
<body style="background: #EEE">
	<div style="width: 100%;">
		
		<div style="width: 96%;margin-top: 13px;margin-left:2%">
			<div class="widget-box" style="margin-top:0px;margin-bottom:0px;">
				<div class="widget-title" style="background: #279F46;text-align: center;font-size: 12px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >校验错误学生列表
				</div>
				<div width="100%"   class="widget-content nopadding" >
					<div style="width:100%; overflow-x:scroll">
					<table class="table table-bordered table-striped with-check" >
						<thead>
							<tr>
								<th width="3%" rowspan="3"  style="font-size: 14px">选择</i>
							  
								<th width="10%" rowspan="3"  style="font-size: 14px">姓名</th>
								<th width="10%" rowspan="3" style="font-size: 13px">学籍号</th>
								<th colspan="21"  style="font-size: 14px">数据</i></th>
								<th colspan="4"  style="font-size: 14px">学分不够</th>
								<th colspan="4"  style="font-size: 14px">学分为空</th>
								<th colspan="2"  style="font-size: 14px">会考成绩</th>
								<th width="8%" rowspan="3"  style="font-size: 14px">其他</th>												
							</tr>
							<tr>
								<th colspan="6"  style="font-size: 14px">报告册封面</th>
							    <th colspan="4"  style="font-size: 14px">个性发展</th>
							    <th colspan="3"  style="font-size: 14px">评语表</th>
							    <th colspan="6"  style="font-size: 14px">研究性学习</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">体质健康</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">体检表</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">必修116</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">选修22</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">校本6</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">总学分144</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">必修116</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">选修22</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">校本6</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">总学分144</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">会考缺成绩</th>
							    <th  width="8%" rowspan="2"  style="font-size: 14px">会考成绩非ABCD</th>													  
						  	</tr>
							<tr>
								<th width="8%"  style="font-size: 14px">姓名</th>
								<th width="8%"  style="font-size: 14px">性别</th>
								<th width="8%"  style="font-size: 14px">年龄</th>
								<th width="8%"  style="font-size: 14px">班级</th>
								<th width="8%"  style="font-size: 14px">学籍号</th>
								<th width="8%"  style="font-size: 14px">毕业学校</th>
								<th width="8%"  style="font-size: 14px">自我评价</th>
								<th width="8%"  style="font-size: 14px">成果展示1</th>
								<th width="8%"  style="font-size: 14px">成果展示2</th>
								<th width="8%"  style="font-size: 14px">成果展示3</th>
								<th width="8%"  style="font-size: 14px">高一</th>
								<th width="8%"  style="font-size: 14px">高二</th>
								<th width="8%"  style="font-size: 14px">高三</th>
								<th width="8%"  style="font-size: 14px">标题1</th>
								<th width="8%"  style="font-size: 14px">内容摘要1</th>
								<th width="8%"  style="font-size: 14px">标题2</th>
								<th width="8%"  style="font-size: 14px">内容摘要2</th>
								<th width="8%"  style="font-size: 14px">标题3</th>
								<th width="8%"  style="font-size: 14px">内容摘要3</th>
						   </tr>
						</thead>
						<tbody>
						<!-- 开始 -->
						<c:forEach items="${studentlist }" var="stu">
							<tr >
								<td style="text-align: center;"><input type="checkbox" name="checkbox" value="${stu.eduid }" class="dslcheckbox"/></td>
								<td style="text-align: center;">${stu.studentname }</td>
								<td style="text-align: center;">${stu.studentno}</td>
								<td style="text-align: center;">${stu.name}</td>
								<td style="text-align: center;">${stu.sex}</td>
								<td style="text-align: center;">${stu.age}</td>
								<td style="text-align: center;">${stu.grade}</td>
								<td style="text-align: center;">${stu.studentCode}</td>
								<td style="text-align: center;">${stu.school}</td>
								<td style="text-align: center;">${stu.selfappraisal}</td>
								<td style="text-align: center;">${stu.show1}</td>
								<td style="text-align: center;">${stu.show2}</td>
								<td style="text-align: center;">${stu.show3}</td>
								<td style="text-align: center;">${stu.senior1}</td>
								<td style="text-align: center;">${stu.senior2}</td>
								<td style="text-align: center;">${stu.senior3}</td>
								<td style="text-align: center;">${stu.headline1}</td>
								<td style="text-align: center;">${stu.digest1}</td>
								<td style="text-align: center;">${stu.headline2}</td>
								<td style="text-align: center;">${stu.digest2}</td>
								<td style="text-align: center;">${stu.headline3}</td>
								<td style="text-align: center;">${stu.digest3}</td>
								<td style="text-align: center;">${stu.constitution}</td>
								<td style="text-align: center;">${stu.physical}</td>
								<!-- 学分不足 -->
									<td style="text-align: center;">${stu.required}</td>
									<td style="text-align: center;">${stu.elective}</td>
									<td style="text-align: center;">${stu.edition}</td>
									<td style="text-align: center;">${stu.credit}</td>
								<!-- 学分空 -->
								<td style="text-align: center;">${stu.requirednull}</td>
								<td style="text-align: center;">${stu.electivenull}</td>
								<td style="text-align: center;">${stu.editionnull}</td>
								<td style="text-align: center;">${stu.creditnull}</td>
								
								<td style="text-align: center;">${stu.huikaochngji}</td>
								<td style="text-align: center;">${stu.huikaoadcd}</td>
								<td style="text-align: center;"></td>
							</tr>
						</c:forEach>
						<!-- 结束 -->
						</tbody>
					</table>
					</div>
					<div class="form-actions pagination alternate""  style="margin-top: 0px;margin-bottom: 0px;">
						<span style="margin-right: 30px;float:right;margin-top:  line-height: 36;">
							<button onClick="onchenked();" style="width: 100px;"   class="btn btn-success">全选</button>
                                     <button onClick="onchenkedNull();" style="width: 100px;"   class="btn btn-success">取消全选</button>
                                     
                                  
									 <button onClick="baogaocdaochu();" style="width: 100px;"   class="btn btn-success">报告册导出</button>
									 <c:if test="${isSB==1 }">
                                     	<button onClick="stushangbao();" style="width: 100px;"   class="btn btn-success">上报报告册</button>
                                     </c:if>
                                     <c:if test="${isSB==0 }">
                                     	<button onClick="stushangbao1();" style="width: 100px;"   class="btn btn-success">上报报告册</button>
                                     </c:if>
                                     <button onClick="fanhui();"style="width: 100px;"   class="btn btn-success">返回</button>
					     </span>
					</div>						
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/exporting.jsp"%>
</body>
</html>