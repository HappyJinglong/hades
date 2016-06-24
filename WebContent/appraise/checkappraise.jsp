<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/mass.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<un:bind var="START_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_ZWPJ"/>
<un:bind var="START_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB"/>
<un:bind var="END_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_ZWPJ"/>
<un:bind var="END_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_WDFZMB"/>
	
<un:bind var="END_BZRPY" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_BZRPY"/>
<un:bind var="END_JZPYQW" type="com.flyrish.hades.common.Constant"
	field="TYPE_END_JZPYQW"/>
<un:bind var="SX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_ZWPJ"/>
<un:bind var="SX_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SX_TRPJ"/>
	
<un:bind var="SXJLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SXJLD"/>
<un:bind var="SXDDGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SXDDGJPJ"/>
<un:bind var="HZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_ZWPJ"/>
<un:bind var="HZ_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_TRPJ"/>
	
<un:bind var="HZ_JLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZ_JLD"/>
<un:bind var="HZJLGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_HZJLGJPJ"/>
<un:bind var="YDJK_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_ZWPJ"/>
<un:bind var="YDJK_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJK_TRPJ"/>
	
<un:bind var="YDJKGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJKGJPJ"/>
<un:bind var="SMYBX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_ZWPJ"/>
<un:bind var="SMYBX_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_TRPJ"/>
<un:bind var="SMYBX_JLD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBX_JLD"/>
<!-- 运动与健康--体质健康	 -->
<un:bind var="YDJKTZJK" type="com.flyrish.hades.common.Constant"
	field="TYPE_YDJKTZJK"/>	
	
	
<un:bind var="SMYBXGJPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SMYBXGJPJ"/>
<un:bind var="YJXX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX_ZWPJ"/>
<un:bind var="YJXX_XXNR" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX_XXNR"/>
<un:bind var="SQFU_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU_ZWPJ"/>
	
<un:bind var="SQFU_FWNR" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU_FWNR"/>
<un:bind var="SJHD_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_SJHD_ZWPJ"/>
<un:bind var="SJHD_JBQK" type="com.flyrish.hades.common.Constant"
	field="TYPE_SJHD_JBQK"/>
<un:bind var="GXFZ_JBQK" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_JBQK"/>
	
<un:bind var="GXFZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_ZWPJ"/>
<un:bind var="GXFZ_TRPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_TRPJ"/>
<un:bind var="GXFZGC" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZGC"/>
<un:bind var="GXFZ_CGZS" type="com.flyrish.hades.common.Constant"
	field="TYPE_GXFZ_CGZS"/>

<un:bind var="XY_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_ZWPJ"/>
<un:bind var="XY_GCJL" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_GCJL"/>
<un:bind var="XY_XFRD" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY_XFRD"/>
<un:bind var="XY" type="com.flyrish.hades.common.Constant"
	field="TYPE_XY"/>
	
<un:bind var="START_WDFZMB_MYSELF" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB_MYSELF"/>
<un:bind var="START_WDFZMB_PRATENT" type="com.flyrish.hades.common.Constant"
	field="TYPE_START_WDFZMB_PRATENT"/>
	
<un:bind var="me_apprasialidentify" type="com.flyrish.hades.common.Constant"
	field="me_apprasialidentify"/>
	
<un:bind var="PARENT" type="com.flyrish.hades.common.Constant"
	field="APPRASER_PARENT"/>
<un:bind var="STUDENT" type="com.flyrish.hades.common.Constant"
	field="APPRASER_STUDENT"/>
<un:bind var="TEACHER" type="com.flyrish.hades.common.Constant"
	field="APPRASER_TEACHER"/>
<un:bind var="MASTER" type="com.flyrish.hades.common.Constant"
	field="APPRASER_MASTER"/>
	
	
<un:bind var="YJXX" type="com.flyrish.hades.common.Constant"
	field="TYPE_YJXX"/>
<un:bind var="SQFU" type="com.flyrish.hades.common.Constant"
	field="TYPE_SQFU"/>
<un:bind var="SHSJHD" type="com.flyrish.hades.common.Constant"
	field="TYPE_SHSJHD"/>
<un:bind var="KE_CHENG_PINGYU" type="com.flyrish.hades.common.Constant"
	field="TYPE_KE_CHENG_PINGYU"/>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>自我评价</title>

<style>
.textarea{max-height:140px;
		  heihgt:200px;
		  overflow:auto;
		}
		
		
a {
    color: #000000;
    text-decoration: none;
}
.wenbenkuang6711 {
    border: 1px solid #999;
    height: 26px;
    line-height: 26px;
    width: 100%;
}
.export{
	width: 70px;
	height: 28px;
	background: none repeat scroll 0% 0% #279F46;
	border-bottom: 3px solid #1C7132;
	color: #FFF;
}
.Float_left{Float:left;}
.Float_right{Float:right;}
</style>
<script type="text/javascript">
function changeBackgroundWhite(obj){
	$(obj).parent().css("background-color","");
	if($(obj).attr('type')=='text'){
		$(obj).css("background-color","");
	}
}
function changeBackgroundGrey(obj){
	$(obj).parent().css("background-color","#eee");
	if($(obj).attr('type')=='text'){
		$(obj).css("background-color","#eee");
	}
}
function changeBackgroundW(obj){
	if($(obj).attr('readOnly'))return;
	$(obj).css("background-color","");
}
function changeBackgroundG(obj){
	
	$(obj).css("background-color","#eee");
	
	saveData(obj);
	
}

function saveData(obj,writeManInfo){

	var writeMan = "";
	var eduId = $("#edu_id").val();  //被评价学生的教育id
	var studentName =encodeURI($("#studentName").val());// 被评价学生的姓名
	var columnName =encodeURI($(obj).attr('columnName'));
	var termId = "${termid}";
	
	if($(obj).attr('readOnly'))return;
	var itemval=$(obj).attr('id').split('_');
	
	var itemid=itemval[0];
	var wmInfos = writeManInfo.split("_");
	if(wmInfos.length==2){
		writeMan = wmInfos[0];
		itemid =itemid+"_"+wmInfos[1];
	}else{
		writeMan=writeManInfo;
	}
	
	var itemclass=itemval[1];
	var itemnewContent=$(obj).val();
	var itemoldContent=$('#'+$(obj).attr('id')+'s').val();
	var levelcode=$('#levelcode').val();
	var itemname=$(obj).attr('name');
	if(itemoldContent.trim()==itemnewContent.trim())return;
	
	if(itemnewContent.trim().length==0){
		return true;
	}
	if(itemclass == "1010" && itemnewContent.length>300 ||
	   itemclass == "1020" && itemnewContent.length>300 ||
	   itemclass == "2010" && itemnewContent.length>300 ||
	   itemclass == "2020" && itemnewContent.length>300 ||
	   itemclass == "2030" && itemnewContent.length>300 ||
	   itemclass == "2040" && itemnewContent.length>300 ||
	   itemclass == "7040" && itemnewContent.length>300 ||
	   itemclass == "7050" && itemnewContent.length>300 ){
			apprasial_alert(300);
			return true;
		}
	if( itemclass == "3010" && itemnewContent.length>600 ){  //思想道德自我评价
		apprasial_alert(600);
		return true;
	}
	
	 if(itemclass =="3020" && itemnewContent.length>4000 ||			//思想道德  他人评价
	   itemclass == "3030" && itemnewContent.length>4000 ||
	   itemclass == "9999" && itemnewContent.length>4000 ||
	   itemclass == "8040" && itemnewContent.length>4000 ||  		//学业成就  他人评价
	   itemclass == "4020" && itemnewContent.length>4000 ||			//合作与交流   他人评价
	   itemclass == "4030" && itemnewContent.length>4000 ||    
	   itemclass == "5020" && itemnewContent.length>4000 ||			//运动与健康   他人
	   itemclass == "6020" && itemnewContent.length>4000 ||			//审美与表现  他人
	   itemclass == "6030" && itemnewContent.length>4000 ||			//审美与表现  内容
	   itemclass == "9030apprasial" && itemnewContent.length>4000 ||
	   itemclass == "7010" && itemnewContent.length>4000 ||
	   itemclass == "7030" && itemnewContent.length>4000 ){				//个性   他人
			apprasial_alert(4000);
			return true;
		}
	 if(itemclass == "8020" && itemnewContent.length>600 ){
			apprasial_alert(600);
			return true;
		}
	 
	 if(itemclass == "8010" && itemnewContent.length>200 ){
			apprasial_alert(200);
			return true;
		}
	 
	 
	 if(itemclass == "4010" && itemnewContent.length>600 ||		//合作与交流  自我
	   itemclass == "5010" && itemnewContent.length>600 ||
	   itemclass == "6010" && itemnewContent.length>600 ||
	   itemclass == "9010" && itemnewContent.length>600 ||
	   itemclass == "9020" && itemnewContent.length>600 ||
	   itemclass == "9030" && itemnewContent.length>600 ||
	   itemclass == "7020" && itemnewContent.length>600 ){
			apprasial_alert(600);
			return true;
		}
	 
	 
	 if(itemclass == "9010item1" && itemnewContent.length>30 ){		//综合  题目
			apprasial_alert(30);
			return true;
		}
	
	
	if(itemclass == "9030item4" && itemnewContent.length>120 ){
		apprasial_alert(120);
		return true;
	}
	if(itemclass == "9030item5" && itemnewContent.length>120 ||
			   itemclass == "9020item2" && itemnewContent.length>120||
			   itemclass == "9020item3" && itemnewContent.length>120||
			   itemclass == "9010item2" && itemnewContent.length>120||
			   itemclass == "9010item3" && itemnewContent.length>120||
			   itemclass == "9010item4" && itemnewContent.length>120){
		apprasial_alert(120);
		return true;
	}
	 
	 
	 if(itemclass == "9010apprasial" && itemnewContent.length>700 ){
			apprasial_alert(700);
			return true;
		}
	 
	 
	 if(itemclass == "9020item4" && itemnewContent.length>25 ||		
	   itemclass == "9020item5" && itemnewContent.length>25 ){
			apprasial_alert(25);
			return true;
		}

	 
	 $.ajax({
		url:'${ctx}/appraise/SaveAppraiseAction.a?doupdateAppraisal',
		method:'POST',
		success:function(msg){
			var value=eval(msg);
			if(msg.val){
				alert_save_success(itemname,"");
				$('#'+$(obj).attr('id')+'s').val(itemnewContent);
				$("#gritter-notice-wrapper").css("z-index","100000");
			}
		},
		 data:{
			apprasialid: itemval[0],
			appraisaltypeid:itemval[1],
			apprasial:itemnewContent,
			levelcode:levelcode,
			stuEduid:eduId,
			studentName:studentName,
			columnName:columnName,
			columNum:itemclass.substr(0,4),
			proKey:itemid,
			termid:termId,
			writeMan:writeMan
		}
	});
}


	function select(){
		
		var typeId = document.getElementById("select").value;
		window.location.hash=typeId;
	} 
	
	function chooseTerm(){
	
		var eduId = $("#edu_id").val();
		var termId = $("#termId option:selected").val();
		ShowDiv();
		window.location.href="${ctx}/appraise/QueryAppraiseAction.a?termid="+termId+"&&edu_id="+eduId+"&&studentName=${studentName }&&checkFlag=1";
	}
	
	function export_appraise(){
		var eduId = $("#edu_id").val();
		var levelcode = $("#levelcode").val();
		var termId = $("#termId option:selected").val();
		var termidName = $("#termId option:selected").text();
		var cmis30id = $("#cmis30id").val();
		var discode = $("#discode").val();
		var studentName = $("#studentName").val();
		
		window.location.href="${ctx}/export/SeniorAppraisalExport.a?termId="+termId+"&levelCode="+levelcode+"&eduId="+eduId+"&cmis30id="+cmis30id+"&discode="+discode+"&studentName="+studentName+"&termidName="+termidName; 
	}
	
	 $(window).load(function() {
		
		 var thisMession=document.getElementById("mession");
	
		 thisMession.innerHTML="";
		 thisMession.innerHTML=messcha;  
		 
		 var commonFuncId = <%=request.getAttribute("commonFuncId")%>;
		 if (commonFuncId != 'null' || commonFuncId != '1050' || commonFuncId != '2020') {
			 window.location.hash=commonFuncId;
		 }
		  }
	 );
	 $().ready(function(){
		 if("${styleChange}"=="yes"){
			 $("#dq_table").attr("width","100.3%");
		 }
	 });
</script>
</head>



<body style="background-color: #EEE" >
<div class="dangqianwz" id="dangqianwz_gz_master" style="width: 96.4%;padding-bottom: 0px;padding-right: 0px">
 	<span class="fl">当前位置：查看评价->${studentName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
     <select name="termId" class="  wenziliebiao100" id="termId" onchange="chooseTerm()">
               <app:highSchoolTermTag selectClassid="${classId }" selectNum="${termid}" levelCode="${levelcode }"/>
      </select> 
            </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >选择栏目：
     <select name="select" class="  wenziliebiao100" id = "select" onchange="select();">
               <option selected="selected" value='1001'>新学期伊始的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1002'>学期结束时的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1003'>思想道德&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1004'>学业成就&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1005'>合作与交流&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1006'>运动与健康&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1007'>审美与表现&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1008'>综合实践活动&nbsp;&nbsp;&nbsp;&nbsp;</option>
               <option value='1009'>个性发展 &nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select> </span> 
             <span class="fr">
             	<input type="button" value="导 出" class="export" id="export" onclick="export_appraise();" style="background-color:#279F46;cursor:pointer;"/>
             </span>
              <table id="dq_table" width="100%" border="0" cellspacing="1" bgcolor="#999999"  style="text-align: center;margin-top: 15px;text-shadow:0px 0px 0px #3E3E3E">
           <tr class="title_bg" >
             <td colspan="5" height="48px" style="text-shadow: 0px 0px 0px #279F46;color: #FFF;font-size: 16px;background:#279F46">评价情况</td>
           </tr>
		    
		    <tr>
		      <td class="th" width="12%" height="30px" style="font-size: 13px;font-family: 微软雅黑;color: #3E3E3E;">一级栏目</td>
		      <td width="12%" colspan="2" height="30px"class="th"  style="font-size: 13px;font-family: 微软雅黑;color: #3E3E3E;">二级栏目</td>
		      <td  class="th" height="30px" style="font-size: 13px;font-family: 微软雅黑;color: #3E3E3E;">内容</td>
        </tr>
    </table>
 
 </div>
<div id="pj_ziwo_main" style="top: 110px;width: 97.95%;padding-right: 0px;overflow-x:hidden;margin-bottom: 39px">
<input type="hidden" id="levelcode" value="${levelcode}"/>
<input type="hidden" id="edu_id" value="${edu_id}"/>
<input type="hidden" id="cmis30id" value="${cmis30id}"/>
<input type="hidden" id="discode" value="${discode}"/>
<input type="hidden" id="studentName" value="${studentName}"/>
 <div class="top">
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
         <!--   <tr class="title_bg">
             <td colspan="4"><a id="1001"></a>我的评价情况</td>
           </tr>
		    
		    <tr>
		      <td class="th" width="15%" >一级栏目</td>
		      <td width="20%" colspan="2" class="th"  >二级栏目</td>
		      <td  class="th"  >内容</td>
        </tr> -->
		    <tr><a id="1001"></a>
		      <td height="191" width="12%" rowspan="3" class="h50 th">新学期伊始的我</td>
		      <td colspan="2" width="12%" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
				<c:forEach items="${appraiseMaps[START_ZWPJ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td  style="padding-right: 6px">
								<textarea columnName="新学期伊始的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
									<span style='float:right'>
										评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
									</span>
									<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
								</td>
							</tr>
			  		</table>
				</c:forEach>
			  </td>
			</tr>
		    <tr>
		      <td colspan="2" class="h50 th"><a id="1002"></a>我的发展目标<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[START_WDFZMB]}" var="item">
					 <c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
									<tr>
										<td  style="padding-right: 6px"> 
										<textarea columnName="新学期伊始的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
											<span style='float:right'>
												评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
											</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
										</td>
									</tr>
					  		</table>
				  		</c:if>
					</c:forEach>
			</td>
        </tr>
		    <tr>
		      <td colspan="2" class="h50 th">家长的期望</td>
		      <td class="h50" style="background-color:rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[START_WDFZMB]}" var="item">
					  <c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  			<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="新学期伊始的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="家长的期望" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
									<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  			</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="4" class="h50 th">学期结束时的我</td>
		      <td height="95" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" style="background-color:rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_ZWPJ]}" var="item">
						  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
						  </table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">我的发展目标</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_WDFZMB]}" var="item">
						  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
										<tr>
											<td  style="padding-right: 6px">
											<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
												<span style='float:right'>
													评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
												</span>
												<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
											</td>
										</tr>
						  </table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">班主任评语<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_BZRPY]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1003"></a>家长评语和期望<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_JZPYQW]}" var="item">
					  	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
									<tr>
										<td  style="padding-right: 6px">
										<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
											<span style='float:right'>
												评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
											</span>
											<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
										</td>
									</tr>
					  	</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">思想道德</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
				<c:forEach items="${appraiseMaps[SX_ZWPJ]}" var="item">
		      		<c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
									<span style='float:right'>
										评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
									</span>
									<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
					  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,TEACHER) or fn:contains(item.appraseridentify,MASTER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        	</tr>
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1004"></a>思想道德记录袋<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SXJLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">思想道德记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%"  style="padding-right: 6px">
						   <textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
						   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					  </td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx }${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							提供人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="6" class="h50 th">学业成就</td>
		      <td height="47" colspan="2" class="h50 th">学科作品展示</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[XY_GCJL]}" var="item" varStatus="pVs">
			      <c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					  
					  	<tr >
				  			<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">学科学习过程记录${pVs.count}</td>
				  		</tr>
					  
					  <c:if test="${item.item1 == '思想政治'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">思想品德</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '语文'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">语文</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '数学'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">数学</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '英语'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">英语</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '历史'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">历史</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '地理'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">地理</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '物理'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">物理</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '化学'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">化学</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '生物'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">生物</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '体育与健康'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">体育与健康</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '音乐'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">音乐</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '美术'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">美术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '劳动技术'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">劳动技术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '信息技术'}">
						  <tr>
							  <td width="10%" style="background:#eee;">科目</td>
							  <td width="90%" style="background:#eee;">
								  <span style="float:left">信息技术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <tr>
					     <td width="10%" style="background:#eee;">作品信息</td>
						  <td width="90%"  style="padding-right: 6px">
							   <textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
							   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
						  </td>
					  </tr>
					  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
						  <tr>
						      <td width="10%" style="background:#eee;">
							      附件${state.count}：
							  </td>
							  <td width="90%" style="background:#eee;">
							     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
							  </td>
						  </tr>
					  </c:forEach>
					  <tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
							评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
							</td>
					  </tr>
					  </table>
					  </c:if>
			 	</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="h50 th">课程评语<span class="red">*</span></td>
		       <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[KE_CHENG_PINGYU]}" var="item" varStatus="i">
					<c:if test="${fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						  	<tr> <td width="10%" style="background:#eee;text-align: left;" colspan="2" >课程评语${i.count }</td></tr>
						  	 <tr>
								  <td width="10%" style="background:#eee;">科目</td>
								  <td width="90%" style="background:#eee;">
									  <span style="float:left">${item.item1}</span>
								  </td>
							  </tr>
								<tr>
								   <td width="10%" style="background:#eee;">内容描述:</td>
									<td  style="padding-right: 6px" >
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
        
        
		    <!--  <tr>
		     <td height="47" colspan="2" class="h50 th">学分认定记录</td>
		      <td class="h50 ">&nbsp;</td> 
		      
        	</tr> -->
        
        
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">教师<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">同学</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1005"></a>家长</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">合作与交流</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[HZ_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red">*</span></td>
		       <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1006"></a>合作与交流行为记录袋<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[HZ_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">合作与交流行为记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%"  style="padding-right: 6px">
						   <textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
						   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					  </td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							提供人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">运动与健康</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>																																																																																																																																																																																																																																											
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
        
		    <tr>
		      <td height="47" class="h50 th"><a id="1007"></a>家长</td>
		     <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="运动与健康" class="textarea" style="border-style: none; width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
        
        
        	<tr>
		      <td height="191" colspan="2" class="h50 th">体质健康<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      
		      
			 	<c:if test="${not empty appraiseMaps[YDJKTZJK]}">
        		<table width="100%" cellspacing="1" cellpadding="0" border="0" bgcolor="#999999" >
        		
				<tbody>
				      
				        <tr style="background-color: rgb(238, 238, 238);">
				            <td>
				                <div align="center">
				                                                            指标（项目）
				                </div>
				            </td>
				            <td>
				                <div align="center">
				                    	成绩
				                </div>
				            </td>
				            <td>
				                <div align="center">
				                    	得分
				                </div>
				            </td>
				            <td>
				                <div align="center">
				                    	等级
				                </div>
				            </td>
				        </tr>
				        
				        
				    <c:forEach items="${appraiseMaps[YDJKTZJK]}" var="item" >    
				        
					<c:if test="${item.itemType eq 1 }">
				       	<tr style="background-color: rgb(238, 238, 238);">
				            <td rowspan="2">
				                <div align="center">
				                    ${item.itemName }
				                </div>
				                <div align="center">
				                    	体重
				                </div>
				            </td>
				            <td>
				                <div align="center">
				                    ${fn:split(item.itemMark,'/')[0] }
				                </div>
				            </td>
				            <td rowspan="2" style="background-color: rgb(238, 238, 238);">
				                <div align="center">
				                     ${item.itemScore }
				                </div>
				                <div align="center" ></div>
				            </td>
				            <td rowspan="2">
				                <div align="center">
				                   	 ${item.itemGrade }
				                </div>
				                <div align="center"></div>
				            </td>
				        </tr> 
				        
				        <tr style="background-color: rgb(238, 238, 238);">
				            <td>
				                <div align="center">
				                    ${fn:split(item.itemMark,'/')[1] }
				                </div>
				            </td>
				        </tr>
				</c:if>
				        
				        
					<c:choose>
						<c:when test="${item.itemType eq 2 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
				        
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 3 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 4 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 5 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 6 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 7 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 8 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 9 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 10 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 11 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 12 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 13 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 14 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       		
				       		<c:when test="${item.itemType eq 15 }">
				       			<tr style="background-color: rgb(238, 238, 238);">
						            <td>
						                 <div align="center">
						                </div>
						                <div align="center">
						                    ${item.itemName }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                     ${item.itemMark }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                    ${item.itemScore }
						                </div>
						            </td>
						            <td>
						                <div align="center">
						                   	${item.itemGrade }
						                </div>
						            </td>
						        </tr>
				       		</c:when>
				       </c:choose> 
					</c:forEach> 
				        
				        
				        
					<c:forEach items="${appraiseMaps[YDJKTZJK]}" var="item" begin="0" end="0" step="1">
				        
				        <tr style="background-color: rgb(238, 238, 238);">
				            <td>
				                <div align="center">
				                    	奖励得分
				                </div>
				            </td>
				            <td colspan="3">
				                <div align="center"></div>
				                <div align="center">
				                    ${item.rewardSscore }
				                </div>
				                <div align="center"></div>
				            </td>
				        </tr>
				        
				        <tr style="background-color: rgb(238, 238, 238);">
				            <td>
				                <div align="center">
				                   	 学年总分
				                </div>
				            </td>
				            <td colspan="3">
				                <div align="center"></div>
				                <div align="center">
				                    ${item.yearScore }
				                </div>
				                <div align="center"></div>
				                <div align="center"></div>
				            </td>
				        </tr>
				        
				        <tr style="background-color: rgb(238, 238, 238);">
				            <td>
				                <div align="center">
				                    	等级评定
				                </div>
				            </td>
				            <td colspan="3">
				                <div align="center">
				                    ${item.yearGrade }
				                </div>
				            </td>
				        </tr>
					</c:forEach>
				   </tbody>
				</table>
			</c:if>	
		  </td>
       </tr>
        
     
        
		    <tr>
		      <td rowspan="5" class="h50 th">审美与表现</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		       <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">审美与表现记录袋<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SMYBX_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  
				  <tr  >
				  	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">审美与表现记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%"  style="padding-right: 6px">
						   <textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
						   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					  </td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							提供人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
						</td>
				  </tr>
				  </table>
			  </c:forEach><a id="1008"></a>
			  </td>
        </tr>
        
		    <tr>
		      <td rowspan="3" class="h50 th">综合实践活动</td>
		      <td height="191" colspan="2" rowspan="1" class="h50 th">研究性学习<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[YJXX]}" var="item" varStatus="pVs">
			  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
			  
			  	<tr >
				  <td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">研究性学习${pVs.count}</td>
				</tr>
				
			  <tr>
				 <td width="10%" style="background:#eee;"> 题目：</td>
				 <td width="90%" >
				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item1}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item1s" value="${item.item1}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;"> 合伙人：</td>
				 <td width="90%">
					<input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item2}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')"/>
					<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item2s" value="${item.item2}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;"> 总学时数：</td>
				 <td width="90%">
				 <input columnName="综合实践活动" type="text" class="fl wenbenkuang6711" style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item3}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item3" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item3s" value="${item.item3}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;"> 实施路径：</td>
				 <td width="90%">
				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item4}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item4" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item4s" value="${item.item4}"/>
				 </td>
			  </tr>
			  <tr>
			      <td width="10%" style="background:#eee;">
				      内容摘要：
				  </td>
				 <td width="90%" style="padding-right: 6px">
					   <textarea columnName="综合实践活动" class="textarea" style="background: transparent; border-style: none;  width: 100%;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}apprasial" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial }</textarea>
					   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}apprasials" value="${item.apprasial}"/>
				  </td>
			  </tr>
			  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   	<tr>
				     <td width="10%" style="background:#eee;" >
					      自我评价：
					 </td>
					 <td width="100%" style="background:#eee;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2" varStatus="i">
					  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  <tr>
					 <td  style="padding-right: 6px">
					   <textarea columnName="综合实践活动" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item2.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item2.myselfappraserid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}_${item.apprasialid }')">${item2.myselfapprasercontent}</textarea>
					  
					
					   <input type="hidden" id="${item2.myselfappraserid}_${item.appraisaltypeid}s" value="${item2.myselfapprasercontent}"/>
					 </td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
				<tr>
				 <td width="100%" style="background:#eee;" colspan="2">
				 	<span style='float:right'>
							评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}&nbsp;&nbsp;
					</span>
				 </td>
				</tr>
			  
			  </table>
			  </c:forEach>
			  
			  </td>
        </tr>
		 
		 
		    <tr>
		      <td height="95" colspan="2" class="h50 th">社区服务<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SQFU]}" var="item" varStatus="pVs">
			  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
			  
			  	<tr >
				 <td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">社区服务${pVs.count}</td>
				</tr>
				
			  <tr>
				 <td width="15%" style="background:#eee;">选择次数：</td>
				 
			<c:if test="${item.isReadOnly}">	 
				 <td width="85%" style="background-color:rgb(238, 238, 238);">
<%-- 				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item1}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				 
				 		<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="background-color:rgb(238, 238, 238);border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}"  disabled="disabled" id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="第一次" <c:if test="${item.item1 eq '第一次'}">selected</c:if> >第一次</option>
					  			<option value="第二次" <c:if test="${item.item1 eq '第二次'}">selected</c:if> >第二次</option>
					  			<option value="第三次" <c:if test="${item.item1 eq '第三次'}">selected</c:if> >第三次</option>
					  	</select>
				 
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item1s" value="${item.apprasial}"/>
				 </td>
			</c:if>	 
			<c:if test="${!item.isReadOnly}">	 
				 <td width="85%">
<%-- 				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item1}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				 
				 		<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="第一次" <c:if test="${item.item1 eq '第一次'}">selected</c:if> >第一次</option>
					  			<option value="第二次" <c:if test="${item.item1 eq '第二次'}">selected</c:if> >第二次</option>
					  			<option value="第三次" <c:if test="${item.item1 eq '第三次'}">selected</c:if> >第三次</option>
					  	</select>
				 
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item1s" value="${item.apprasial}"/>
				 </td>
			</c:if>		 
			  </tr>
			  <tr>
				 <td width="15%" style="background:#eee;">服务社区名称：</td>
				 <td width="85%">
					<input columnName="综合实践活动" type="text" class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item2}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')"/>
					<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item2s" value="${item.apprasial}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="15%" style="background:#eee;">服务社区联系电话：</td>
				 <td width="85%">
				 <input columnName="综合实践活动" type="text" class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item3}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item3" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item3s" value="${item.apprasial}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="15%" style="background:#eee;">服务时数：</td>
				 <td width="85%">
				<input columnName="综合实践活动" type="text" class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item4}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item4" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item4s" value="${item.apprasial}"/>
				 </td>
			  </tr>
			   <tr>
				 <td width="15%" style="background:#eee;"> 服务日期：</td>
				 <td width="85%">
					<input columnName="综合实践活动" type="text" class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item5}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item5" onblur="saveData(this,'${item.appraseridentifynum}')"/>
					<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item5s" value="${item.apprasial}"/>		 
				 </td>
			  </tr>
			  <tr>
			      <td width="10%" style="background:#eee;">
				      内容摘要：
				  </td>
				 <td width="90%"  style="padding-right: 6px">
					   <textarea columnName="综合实践活动" class="textarea" style="background: transparent; border-style: none;  width: 100%;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}apprasial" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial }</textarea>
					   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}apprasials" value="${item.apprasial}"/>
				  </td>
			  </tr>
			  
			 
			  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   <tr>
				      <td width="10%" style="background:#eee;">
					      自我评价：
					  </td>
					  <td width="100%" style="background:#eee;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2">
					  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					<tr>
					 <td  style="padding-right: 6px">
						   <textarea columnName="综合实践活动" class="textarea" style="background: transparent; border-style: none;  width: 100%;" name="${item2.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item2.myselfappraserid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}_${item.apprasialid }')">${item2.myselfapprasercontent}</textarea>
						  
						   <input type="hidden" id="${item2.myselfappraserid}_${item.appraisaltypeid}s" value="${item2.myselfapprasercontent}"/>
					  </td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
			  <tr>
				 <td width="100%" style="background:#eee;" colspan="2">
				 	<span style='float:right'>
							评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}&nbsp;&nbsp;
					</span>
				 </td>
				</tr>
			  </table>
			  </c:forEach>
			  </td>
        </tr>
        
		    <tr>
		      <td height="95" colspan="2" class="h50 th">社会实践活动</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SHSJHD]}" var="item" varStatus="pVs">
			   <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
			   
			   	<tr >
				 <td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">社会实践活动${pVs.count}</td>
				</tr>
			   
			  <tr>
				 <td width="10%" style="background:#eee;">选择活动种类：</td>
				 <c:if test="${item.isReadOnly}">
				 <td width="90%" style="background-color:rgb(238, 238, 238);">
<%-- 				<input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item1}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				
				
				<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="background-color:rgb(238, 238, 238);border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}"  disabled="disabled" id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="社会考察活动" <c:if test="${item.item1 eq '社会考察活动'}">selected</c:if> >社会考察活动</option>
					  			<option value="社会调查活动" <c:if test="${item.item1 eq '社会调查活动'}">selected</c:if> >社会调查活动</option>
					  			<option value="社会实践活动" <c:if test="${item.item1 eq '社会实践活动'}">selected</c:if> >社会实践活动</option>
					  			<option value="其他" <c:if test="${item.item1 eq '其他'}">selected</c:if> >其他</option>
				</select>
				<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item1s" value="${item.apprasial}"/>
				 </td>
				 </c:if>
				 <c:if test="${!item.isReadOnly}">
				 <td width="90%">
<%-- 				<input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item1}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				
				
				<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" id="${item.apprasialid}_${item.appraisaltypeid}item1" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="社会考察活动" <c:if test="${item.item1 eq '社会考察活动'}">selected</c:if> >社会考察活动</option>
					  			<option value="社会调查活动" <c:if test="${item.item1 eq '社会调查活动'}">selected</c:if> >社会调查活动</option>
					  			<option value="社会实践活动" <c:if test="${item.item1 eq '社会实践活动'}">selected</c:if> >社会实践活动</option>
					  			<option value="其他" <c:if test="${item.item1 eq '其他'}">selected</c:if> >其他</option>
				</select>
				<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item1s" value="${item.apprasial}"/>
				 </td>
				 </c:if>	
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;">成果形成：</td>
				<c:if test="${item.isReadOnly}">
				 <td width="90%" style="background-color:rgb(238, 238, 238);">
<%-- 				<input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item2}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				
					<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="border: 0px;margin-left: 4px;width: 99.7%;background-color:rgb(238, 238, 238);" name="${item.columNumberName}"  disabled="disabled" id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="个人体会" <c:if test="${item.item2 eq '个人体会'}">selected</c:if> >个人体会</option>
					  			<option value="考察报告" <c:if test="${item.item2 eq '考察报告'}">selected</c:if> >考察报告</option>
					  			<option value="个人总结" <c:if test="${item.item2 eq '个人总结'}">selected</c:if> >个人总结</option>
					  			<option value="调查报告" <c:if test="${item.item2 eq '调查报告'}">selected</c:if> >调查报告</option>
					  			<option value="其他" <c:if test="${item.item2 eq '其他'}">selected</c:if> >其他</option>
					</select>
				<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item2s" value="${item.apprasial}"/>
				 </td>
				</c:if>
				<c:if test="${!item.isReadOnly}">
				 <td width="90%">
<%-- 				<input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item2}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')"/>
 --%>				
					<select columnName="综合实践活动"   class="fl wenbenkuang6711" style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}"  id="${item.apprasialid}_${item.appraisaltypeid}item2" onblur="saveData(this,'${item.appraseridentifynum}')">
					  			<option value="个人体会" <c:if test="${item.item2 eq '个人体会'}">selected</c:if> >个人体会</option>
					  			<option value="考察报告" <c:if test="${item.item2 eq '考察报告'}">selected</c:if> >考察报告</option>
					  			<option value="个人总结" <c:if test="${item.item2 eq '个人总结'}">selected</c:if> >个人总结</option>
					  			<option value="调查报告" <c:if test="${item.item2 eq '调查报告'}">selected</c:if> >调查报告</option>
					  			<option value="其他" <c:if test="${item.item2 eq '其他'}">selected</c:if> >其他</option>
					</select>
				<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item2s" value="${item.apprasial}"/>
				 </td>
				</c:if>
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;">地点：</td>
				 <td width="90%">
				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item4}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item4" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item4s" value="${item.apprasial}"/>
				 </td>
			  </tr>
			  <tr>
				 <td width="10%" style="background:#eee;">完成学时：</td>
				 <td width="90%">
				 <input columnName="综合实践活动" type="text"  class="fl wenbenkuang6711"  style="border: 0px;margin-left: 4px;width: 99.7%;" name="${item.columNumberName}" value="${item.item5}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}item5" onblur="saveData(this,'${item.appraseridentifynum}')"/>
				 <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}item5s" value="${item.apprasial}"/>
				 </td>
			  </tr>
			  <tr>
			      <td width="10%" style="background:#eee;">
				      主体：
				  </td>
				  <td width="90%"  style="padding-right: 6px">
					   <textarea columnName="综合实践活动" class="textarea" style="background: transparent; border-style: none;  width: 100%;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}apprasial" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial }</textarea>
					   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}apprasials" value="${item.apprasial}"/>
				  </td>
			  </tr>
			   <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   <tr>
				      <td width="10%" style="background:#eee;">
					      自我评价：
					  </td>
					  <td width="100%" style="background:#eee;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2">
					  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"><tr>
					 <td  style="padding-right: 6px">
						   <textarea columnName="综合实践活动" class="textarea" style="background: transparent; border-style: none;  width: 100%;" name="${item2.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item2.myselfappraserid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}_${item.apprasialid }')">${item2.myselfapprasercontent}</textarea>
						   <input type="hidden" id="${item2.myselfappraserid}_${item.appraisaltypeid}s" value="${item2.myselfapprasercontent}"/>
					  </td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
			  <tr>
				 <td width="100%" style="background:#eee;" colspan="2">
				 	<span style='float:right'>
							评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}&nbsp;&nbsp;
					</span>
				 </td>
				</tr>
			  </table>
			  </c:forEach><a id="1009"></a>
			  </td>
        </tr>
        
		    <tr>
		      <td rowspan="7" class="h50 th">个性发展</td>
		      <td height="95" colspan="2" class="h50 th">基本情况<span class="red">*</span></td>
		      <td class="h50 ">
			  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				<tr>
					<td  width="10%" style="background:#eee;">二级指标</td>
					<td  width="10%" style="background:#eee;">三级指标</td>
					<td style="background:#eee;">个性发展记录</td>
				</tr>
				<tr>
				<td class="h50" height="191" style="background:#eee;">特长</td>
				<td class="h50" style="background:#eee;">
					学科特长
					<br />
					体育运动特长
					<br />
					艺术特长
				</td>
				<td class="h50" style="background-color: rgb(238, 238, 238);padding-right: 6px " >
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '1'}">
						
						<textarea columnName="个性发展" class="textarea" name="${item.columNumberName}" onblur="saveData(this,'${item.appraseridentifynum}')" style="background: transparent; border-style: none;  width: 100%;" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}">${item.apprasial}</textarea>
						<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					
						</c:if>
					</c:forEach>
				</td>
				</tr>
				<tr>
				 <td class="h50" height="191" style="background:#eee;">有新意的成果</td>
				 <td class="h50" style="background:#eee;">
					活动成果
					<br />
					设计成果
					<br />
					制作成果
				</td>
				<td class="h50" style="background-color: rgb(238, 238, 238); padding-right: 6px">
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '2'}">
					
						<textarea columnName="个性发展"  class="textarea"  name="${item.columNumberName}" onblur="saveData(this,'${item.appraseridentifynum}');" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" style="background: transparent; border-style: none;  width: 100%;">${item.apprasial}</textarea>
						<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
						
						</c:if>
					</c:forEach>
				</td>
				</tr>
				<tr>
					<td class="h50" height="191" style="background:#eee;">其他 </td>
					<td class="h50" style="background:#eee;">自主选择</td>
					<td class="h50" style="background-color: rgb(238, 238, 238); padding-right: 6px">
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '3'}">
						<textarea columnName="个性发展"  class="textarea"  name="${item.columNumberName}" onblur="saveData(this,'${item.appraseridentifynum}');" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" style="background: transparent; border-style: none;  width: 100%;">${item.apprasial}</textarea>
						<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
						</c:if>
					</c:forEach>
				</td>
				</tr>
				
				<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item" varStatus="pvs">
				
					<c:if test="${pvs.index == 0 }">
						<tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
								评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
							</td>
					    </tr>
					</c:if>
				</c:forEach>
			  </table>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">自我评价</td>
		     <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
										<span style='float:right'>
											评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
										</span>
										<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">个性发展过程<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[GXFZGC]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">个性发展过程${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%"  style="padding-right: 6px">
						   <textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
					  	   <input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					  </td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							评价人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="h50 th">特长与成果展示<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[GXFZ_CGZS]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  <tr >
				  	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">特长与成果展示${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%"  style="padding-right: 6px">
						   <textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="${item.columNumberName}" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.apprasialid}_${item.appraisaltypeid}" onblur="saveData(this,'${item.appraseridentifynum}')">${item.apprasial}</textarea>
					  		<input type="hidden" id="${item.apprasialid}_${item.appraisaltypeid}s" value="${item.apprasial}"/>
					  </td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">
						      附件${state.count}：
						  </td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							提供人：${item.appraseridentify}&nbsp&nbsp&nbsp&nbsp签名：${item.appraser}&nbsp&nbsp&nbsp&nbsp日期：${item.signdate}
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
          
      </table>
    </div>
  

</div>
<%@ include file="/common/queryLoadDiv.jsp"%>
<script type="text/javascript">
$(window).load(function() {
    $("textarea").each(function(){
		$(this).css("height",$(this).attr("scrollHeight"));
		$(this).parent().css("background-color","#eee");
		$(this).unbind();
		if(!$(this).attr('readonly')){
			this.parentNode.style.backgroundColor='';
			//alert($(this).parent());
			//changeBackgroundWhite(this);
			/* $(this).bind('click',function(){
				changeBackgroundWhite(this);
			}); */
			/* $(this).bind('blur',function(){
				if(!(null==$(this).val() || ""==$(this).val())){
					changeBackgroundGrey(this);
				}
			}); */
			//$(this).css("background","");
		}
	 });
	$("input").each(function(){
		if($(this).attr('type')=='hidden')return;
		 if($(this).val()!="导 出"){
				$(this).parent().css("background-color","#eee");
				$(this).css("background-color","#eee");
		 }

		$(this).unbind();
		if(!$(this).attr('readonly')){
			$(this).bind('click',function(){
				changeBackgroundWhite(this);
			});
			$(this).bind('blur',function(){
				if(!(null==$(this).val() || ""==$(this).val())){
					changeBackgroundGrey(this);
				}
				 $(this).css("background-color","");
			});
			$(this).css("background-color","");
		}
	 });
  });
</script>
</body>

