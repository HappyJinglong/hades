<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/mass.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 新学期伊始的我 -->
<un:bind var="START_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TERMS_BEGIN_ME"/>
<un:bind var="START_WDFZMB" type="com.flyrish.hades.common.Constant"
	field="DEVELOP_TARGET_ME"/>	
	
<!-- 学期结束时的我 -->
<un:bind var="END_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="TERMS_END_ME"/>	
<un:bind var="END_BZRPY" type="com.flyrish.hades.common.Constant"
	field="CHARGE_TEACHER_APPRAISAL"/>
<un:bind var="END_JZPYQW" type="com.flyrish.hades.common.Constant"
	field="PRAENTS_APPRAISAL_EXPECT"/>
	
<!-- 思想道德 -->
<un:bind var="SX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_SELF_APPRAISAL"/>
<un:bind var="SX_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="MORALITY_CLASSMATES_APPRAISAL"/>
<un:bind var="SX_TRPJ_JS" type="com.flyrish.hades.common.Constant"
	field="MORALITY_TEACHER_APPRAISAL"/>
<un:bind var="SX_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="MORALITY_PARENT_APPRAISAL"/>
<un:bind var="SXJLD" type="com.flyrish.hades.common.Constant"
	field="MORALITY_RECORD_BAG"/>
	
<!-- 学业成就 -->
<un:bind var="XY_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="WORKS_SELF_APPRAISAL"/>
<un:bind var="XY_TRPJ_KECHENG_PINGYU" type="com.flyrish.hades.common.Constant"
	field="WORKS_SUBJECT_APPRAISAL"/>
<un:bind var="XY_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="WORKS_CLASSMATES_APPRAISAL"/>
<un:bind var="XY_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="WORKS_PARENT_APPRAISAL"/>
<un:bind var="XY_XKZPZS" type="com.flyrish.hades.common.Constant"
	field="WORKS_SUBJECT_SHOW"/>
	
<!-- 合作与交流 -->
<un:bind var="HZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_SELF_APPRAISAL"/>
<un:bind var="HZ_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_CLASSMATES_APPRAISAL"/>
<un:bind var="HZ_TRPJ_JS" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_TEACHER_APPRAISAL"/>
<un:bind var="HZ_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_PARENT_APPRAISAL"/>		
<un:bind var="HZ_JLD" type="com.flyrish.hades.common.Constant"
	field="COOPERATION_RECORD_BAG"/>
	
<!-- 运动与健康 -->
<un:bind var="YDJK_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="PLAY_SELF_APPRAISAL"/>
<un:bind var="YDJK_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="PLAY_CLASSMATES_APPRAISAL"/>
<un:bind var="YDJK_TRPJ_JS" type="com.flyrish.hades.common.Constant"
	field="PLAY_TEACHER_APPRAISAL"/>
<un:bind var="YDJK_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="PLAY_PARENT_APPRAISAL"/>	
<!-- 体质与健康  -->
<un:bind var="YDJK_TZJK" type="com.flyrish.hades.common.Constant"
	field="PLAY_PHYSCIAL_HEALTH"/>	

<!-- 审美与表现 -->
<un:bind var="SMYBX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_SELF_APPRAISAL"/>
<un:bind var="SMYBX_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_CLASSMATES_APPRAISAL"/>
<un:bind var="SMYBX_TRPJ_JS" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_TEACHER_APPRAISAL"/>
<un:bind var="SMYBX_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_PARENT_APPRAISAL"/>		
<un:bind var="SMYBX_JLD" type="com.flyrish.hades.common.Constant"
	field="AESTHETIC_RECORD_BAG"/>
	
<!-- 综合实践活动 -->
<!-- 研究性学习 -->
<un:bind var="ZHSJ_YJXX_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_SELF_APPRAISAL_1"/>
<un:bind var="ZHSJ_YJXX_JBQK" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_BASEINFO_1"/>	
<un:bind var="ZHSJ_YJXX_YJCG" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_RESEARCH_RESULT"/>
<!-- 社区服务与社会实践 -->	
<un:bind var="ZHSJ_SQFWSHSJ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_SELF_APPRAISAL_2"/>
<un:bind var="ZHSJ_SQFWSHSJ_JBQK" type="com.flyrish.hades.common.Constant"
	field="ACTIVITY_BASEINFO_2"/>	

<!-- 个性发展 -->
<un:bind var="GXFZ_ZWPJ" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_SELF_APPRAISAL"/>	
<un:bind var="GXFZ_TRPJ_TX" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_CLASSMATES_APPRAISAL"/>	
<un:bind var="GXFZ_TRPJ_JS" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_TEACHER_APPRASIAL"/>	
<un:bind var="GXFZ_TRPJ_JZ" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_PARENT_APPRAISAL"/>	
<un:bind var="GXFZ_CGZS" type="com.flyrish.hades.common.Constant"
	field="INDIVIDUALITY_RECORD_BAG"/>	
	
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>自我评价</title>
<style>

.textarea{max-height:140px;
		  heihgt:200px;overflow:auto;
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
.wenbenkuang6712 {
    border: 1px solid #999;
    height: 26px;
    line-height: 26px;
    width: 100%;
    background-color: rgb(238, 238, 238); 
}
.export{
	width: 70px;
	height: 28px;
	background: none repeat scroll 0% 0% #279F46;
	border-bottom: 3px solid #1C7132;
	color: #FFF;
}
</style>


<script type="text/javascript">
$(window).load(function() {
	 var commonFuncId = <%=request.getAttribute("commonFuncId")%>;
	 if (commonFuncId != 'null' || commonFuncId != '1050') {
		 window.location.hash=commonFuncId;
	 }
	 var thisMession=document.getElementById("mession");
	 thisMession.innerHTML="";
	 thisMession.innerHTML=messcha;  
	  });
	
	function changeBackgroundWhite(obj){
	/* 	$(obj).parent().css("background-color",""); */
		if($(obj).attr('type')=='text'){
		/* 	$(obj).css("background-color",""); */
		}
	}
	function changeBackgroundGrey(obj){
		$(obj).parent().css("background-color","#eee");
		if($(obj).attr('type')=='text'){
			$(obj).css("background-color","#eee");
		}
	}
/* 	function changeBackgroundW(obj){
		if($(obj).attr('readOnly'))return;
		$(obj).css("background-color","");
	} */
	function changeBackgroundG(obj){
		
		$(obj).css("background-color","#eee");
		
		saveData(obj);
		
	}
	 $(window).load(function() {
	    $("textarea").each(function(){
			$(this).css("height",$(this).attr("scrollHeight"));
			$(this).parent().css("background-color","#eee");
			$(this).unbind();
			if(!$(this).attr('readonly')){
				this.parentNode.style.backgroundColor='';
				/* $(this).bind('click',function(){
					changeBackgroundWhite(this);
				});
				$(this).bind('blur',function(){
					if(!(null==$(this).val() || ""==$(this).val())){
						changeBackgroundGrey(this);
					}
				});
				$(this).css("background",""); */
			}
		 });
	    
		$("input").each(function(){
			 if($(this).attr('type')=='hidden')return;
			 /*if($(this).val()!="导 出"){
					$(this).parent().css("background-color","#eee");
					$(this).css("background-color","#eee");
			 }*/
			$(this).unbind();
			$(this).bind('click',function(){
				changeBackgroundWhite(this);
			});
			/*$(this).bind('blur',function(){
				if(!(null==$(this).val() || ""==$(this).val())){
					changeBackgroundGrey(this);
				}
				/*  $(this).css("background-color",""); */
			/*});
			if(!$(this).attr('readonly')){
				/* $(this).css("background-color",""); 
			}*/
		 });
	  });
	function saveData(obj,writMan){
		var eduId = $("#edu_id").val();  //被评价学生的教育id
		var studentName =encodeURI($("#studentName").val());// 被评价学生的姓名
		var columnName =encodeURI($(obj).attr('columnName'));
		var termId = "${termid}";
		
		
		if($(obj).attr('readOnly'))return;
		var itemval=$(obj).attr('id').split('_');
		var itemid=itemval[0];
		var itemclass=itemval[1];
		var itemnewContent=$(obj).val();
		var itemoldContent=$('#'+$(obj).attr('id')+'s').val();
		var levelcode=$('#levelcode').val();
		var itemname=$(obj).attr('name');
		
		
		
		if(itemoldContent.trim()==itemnewContent.trim())return;
		
		if(itemnewContent.trim().length==0){
			return true;
		}
		
		
		if(itemnewContent.length>600){
			apprasial_alert(600);
			return true;
		}
		
		var num=25;
		
		if(itemclass == "35topic" && itemnewContent.length>25 ||
		   itemclass == "43topic" && itemnewContent.length>25 ||
		   itemclass == "55topic" && itemnewContent.length>25 ||
		   itemclass == "75topic" && itemnewContent.length>25 ||
		   itemclass == "81topic" && itemnewContent.length>25 ||
		   itemclass == "81keyword" && itemnewContent.length>25 ||
		   itemclass == "81cooperationMan" && itemnewContent.length>25 ||
		   itemclass == "84address" && itemnewContent.length>30 ||
		   itemclass == "81cooperationMan" && itemnewContent.length>25 ||
		   itemclass == "95topic" && itemnewContent.length>25 ||
		   itemclass == "84topic" && itemnewContent.length>25 ){
			if(itemclass == "84address" ||
					itemclass == "84address"){
				num = 30;
			}
			apprasial_alert(num);
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
				columNum:itemclass.substr(0,2),
				proKey:itemid,
				termid:termId,
				writeMan:writMan
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
		
		window.location.href="${ctx}/export/JuniorAppraisalExport.a?termId="+termId+"&levelCode="+levelcode+"&eduId="+eduId+"&cmis30id="+cmis30id+"&discode="+discode+"&studentName="+studentName+"&termidName="+termidName; 
	}
	
	 $(window).load(function() {
		
		 var thisMession=document.getElementById("mession");
		 var mess="<p>1、<span class='red'>*</span>&nbsp;&nbsp;标记为必填项</p><p>2、鼠标离开评价内容框，自动保存评价内容</p><p>3、选择学期，可以填写历史学期评价内容</p><p>  4、背景为白色的输入框可以编辑，否则不可编辑</p><p>5、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。</p>";
		 thisMession.sie
		 thisMession.innerHTML="";
		 thisMession.innerHTML=mess;  
		  });
	 
	 $().ready(function(){
		 if("${styleChange}"=="yes"){
			 $("#dq_table").attr("width","100.3%");
		 }
	 });
	 
</script>
</head>

<body style="background-color: #EEE">
<div class="dangqianwz" id="dangqianwz_gz_master" style="width: 96.4%;padding-bottom: 0px;padding-right: 0px">
 	<span class="fl">当前位置：查看评价->${studentName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <span >学期：
         <select name="select" class="  wenziliebiao100" id="termId" onchange="chooseTerm()">
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
            </select> 
          </span> 
          
          <span class="fr">
          	<input type="button" value="导 出" class="export" id="export" onclick="export_appraise();" style="background-color:#279F46"/>
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
<div id="pj_ziwo_main" style="top: 110px;width: 97.95%;padding-right: 0px;overflow-x:hidden;margin-bottom: 39px" >
<input type="hidden" id="levelcode" value="${levelcode}"/>
<input type="hidden" id="edu_id" value="${edu_id}"/>
<input type="hidden" id="cmis30id" value="${cmis30id}"/>
<input type="hidden" id="discode" value="${discode}"/>
<input type="hidden" id="studentName" value="${studentName}"/>
 <div class="top"><a id="1001"></a>
      <table width="100%" border="0px" cellspacing="1" bgcolor="#999999" class="biaoge">
		    <tr>
		      <td height="191" width="12%"  rowspan="2" class="h50 th">新学期伊始的我</td>
		      <td colspan="2"  width="12%" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
			      
			       <c:forEach items="${appraiseMaps[START_ZWPJ]}" var="item">
			  		<table width="100%" border="0px" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td style="padding-right: 6px">
								<textarea columnName="新学期伊始的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									<span style='float:right'>
										评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
									</span>
									<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
					  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
									<tr>
										<td style="padding-right: 6px"> 
										<textarea columnName="新学期伊始的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  我的发展目标" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
											<span style='float:right'>
												评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
											</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
										</td>
									</tr>
					  		</table>
					</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td rowspan="3" class="h50 th">学期结束时的我</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[END_ZWPJ]}" var="item">
						  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
						  </table>
					</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" colspan="2" class="h50 th">班主任评语<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[END_BZRPY]}" var="item" varStatus="i">
						  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  班主任评语" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
						  </table>
					</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1003"></a>家长期望和寄语<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[END_JZPYQW]}" var="item">
						  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学期结束时的我" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  家长期望和寄语" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
													评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
					  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
										<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
	      		<c:forEach items="${appraiseMaps[SX_TRPJ_TX]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td style="padding-right: 6px">
								<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									<span style='float:right'>
										评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
									</span>
									<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
								</td>
							</tr>
			  		</table>
				</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td class="h50 th">教师</td>
		       <td class="h50" style="background-color: rgb(238, 238, 238);">
		       		<c:forEach items="${appraiseMaps[SX_TRPJ_JS]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td style="padding-right: 6px">
								<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									<span style='float:right'>
										评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
									</span>
									<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
								</td>
							</tr>
			  		</table>
				</c:forEach>
		       </td>
        	</tr>
        
		    <tr>
		      <td class="h50 th">家长</td>
		       <td class="h50" style="background-color: rgb(238, 238, 238);">
		       		<c:forEach items="${appraiseMaps[SX_TRPJ_JZ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td style="padding-right: 6px">
								<textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									<span style='float:right'>
										评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
									</span>
									<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
								</td>
							</tr>
			  		</table>
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
							 <td width="10%" style="background:#eee;"> 主题：</td>
							 <td width="90%">
							<c:if test="${item.isReadOnly}">
							<div style="width:100%;height:100%;">
							 <input columnName="思想道德"  style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
							</div>
							</c:if> 
							<c:if test="${!item.isReadOnly}">
							 <input columnName="思想道德"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
							 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
							</c:if>
							 </td>
				  		 </tr>
					  
						 <tr>
							  <td width="10%" style="background:#eee;">内容描述：</td>
							  <td width="90%" style="padding-right: 6px">
								   <textarea columnName="思想道德" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							  </td>
						 </tr>
						 
					  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
						  <tr>
						      <td width="10%" style="background:#eee;"> 附件${state.count}：</td>
							  <td width="90%" style="background:#eee;">
							     <span style="float:left"><a href="${ctx }${fileitem.filepath}">${fileitem.filename}</a></span>
							  </td>
						  </tr>
					  </c:forEach>
					  <tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
								评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
							</td>
					  </tr>
					  </table>
				  </c:forEach>
		      </td>
        	</tr>
        	
		    <tr>
		      <td rowspan="5" class="h50 th">学业成就</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[XY_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
				  		</table>
					</c:forEach>
		      </td>
        	</tr>
        	
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">课程评语<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      
		      	<c:forEach items="${appraiseMaps[XY_TRPJ_KECHENG_PINGYU]}" var="item" varStatus="pVs">
				  	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  		
				  		<tr >
				  			<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">课程评语${pVs.count}</td>
				  		</tr>
				  		
						  	 <tr>
						  	 	<%-- <c:forEach items="${item.subjectDtos}" var="item2"> --%>
								  <td width="10%" style="background:#eee;">科目</td>
								  <td width="90%" style="background:#eee;">
									  <span style="float:left">${item.subjectName}</span>
								  </td>
								 <%-- </c:forEach> --%>
							  </tr>
							  <tr>
								  <td width="10%" style="background:#eee;">课程评语</td>
								  <td width="90%" style="padding-right: 6px">
									   <textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  课程评语" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
								  </td>
				  			 </tr>
				  			 <tr>
								<td class="youjuzhong pr20 bg_eee" colspan="3">
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</td>
					  		 </tr>
				  		</table>
					</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">同学</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      		<c:forEach items="${appraiseMaps[XY_TRPJ_TX]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      		<c:forEach items="${appraiseMaps[XY_TRPJ_JZ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1005"></a>学科作品展示<span class="red"></span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      
		      <c:forEach items="${appraiseMaps[XY_XKZPZS]}" var="item" varStatus="pVs">
					<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
					  
					  	<tr >
				  			<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">学科作品展示${pVs.count}</td>
				  		</tr>
					  
					<tr>
						 <td width="10%" style="background:#eee;"> 主题：</td>
						 <td width="90%">
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="学业成就" style="border: 0px" style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="学业成就" style="border: 0px" style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
						 </td>
			  		</tr>
			  		
					<tr>
					  	<%-- <c:forEach items="${item.subjectDtos}" var="item2"> --%>
						  <td width="10%" style="background:#eee;">科目</td>
						  <td width="90%" style="background:#eee;">
							  <span style="float:left">${item.subjectName}</span>
						  </td>
						 <%-- </c:forEach> --%>
					</tr>
					  
					 <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%" style="padding-right: 6px">
						   <textarea columnName="学业成就" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
						   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
								评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
							</td>
					  </tr>
					  </table>
			 	</c:forEach>
			  </td>
        </tr>
        
		    <tr>
		      <td rowspan="5" class="h50 th">合作与交流</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[HZ_ZWPJ]}" var="item" varStatus="pVs">
					<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[HZ_TRPJ_TX]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[HZ_TRPJ_JS]}" var="item">
					<%-- <c:if test="${item.writeMan eq '3' }"> --%>
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
							<tr>
								<td style="padding-right: 6px">
								<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
									<span style='float:right'>
										评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
									</span>
									<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
								</td>
							</tr>
			  		</table>
			  		<%-- </c:if> --%>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[HZ_TRPJ_JZ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1006"></a>合作与交流行为记录袋<span class="red"></span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[HZ_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				    
				  	<tr >
				  	 <td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">合作与交流记录袋${pVs.count}</td>
				  	</tr>
				  	
				  	<tr>
						 <td width="10%" style="background:#eee;"> 主题：</td>
						 <td width="90%" >
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="合作与交流" style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if> 
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="合作与交流" style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input  type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
						 </td>
			  		</tr>
				    <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%" style="padding-right: 6px">
						   <textarea columnName="合作与交流" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
						   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
					  </td>
				    </tr>
				    <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
						  <tr>
						      <td width="10%" style="background:#eee;">附件${state.count}：</td>
							  <td width="90%" style="background:#eee;">
							     <span style="float:left"><a href="${ctx }${fileitem.filepath}">${fileitem.filename}</a></span>
							  </td>
						  </tr>
					</c:forEach>
				    <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
						</td>
				    </tr>
				  </table>
			  </c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td rowspan="5" class="h50 th">运动与健康</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[YDJK_ZWPJ]}" var="item">
				  	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
				  	</table>	
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[YDJK_TRPJ_TX]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[YDJK_TRPJ_JS]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[YDJK_TRPJ_JZ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="运动与健康" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1007"></a>体质健康<span class="red"></span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      
		      	<c:if test="${not empty appraiseMaps[YDJK_TZJK]}">
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
				        
				        
				    <c:forEach items="${appraiseMaps[YDJK_TZJK]}" var="item" >    
				        
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
				        
				        
				        
					<c:forEach items="${appraiseMaps[YDJK_TZJK]}" var="item" begin="0" end="0" step="1">
				        
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
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[SMYBX_ZWPJ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="审美与表现" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
         </tr>
        
		 <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red"></span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[SMYBX_TRPJ_TX]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="审美与表现"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
         </tr>
        
		 <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[SMYBX_TRPJ_JS]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="审美与表现"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
         </tr>
         
		 <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[SMYBX_TRPJ_JZ]}" var="item">
				  				<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="审美与表现"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
				  		</table>
				</c:forEach>
		      </td>
         </tr>
         
		 <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1006"></a>审美与表现记录袋<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[SMYBX_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
				  
				   <tr >
				  	 <td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">审美与表现记录袋${pVs.count}</td>
				   </tr>
				  
				   <tr>
					 <td width="10%" style="background:#eee;"> 主题：</td>
					 <td width="90%" >
					 <c:if test="${item.isReadOnly}">
					 <div style="width:100%;height:100%;">
					 <input columnName="审美与表现"  style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
					 </div>
					 </c:if>
					 <c:if test="${!item.isReadOnly}">
					 <input columnName="审美与表现"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
					 </c:if>
					 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
					 </td>
			  	  </tr>
				  <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%" style="padding-right: 6px">
						   <textarea columnName="审美与表现"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
						   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
						评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
					</td>
				  </tr>
				  </table>
			  </c:forEach>
		      <a id="1008"></a>
		      </td>
        </tr>
        
		    <tr>
		      <td rowspan="5" class="h50 th">综合实践活动</td>
		      <td rowspan="3" class="h50 th">研究性学习</td>
		      <td height="191" class="h50 th">基本情况<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	
		      	<c:forEach items="${appraiseMaps[ZHSJ_YJXX_JBQK]}" var="item" varStatus="pVs">
		      	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		      	
		      		<tr >
				  	 	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">基本情况${pVs.count}</td>
				    </tr>
		      	
		      		<tr>
						 <td width="10%" style="background:#eee;"> 主题：</td>
						 <td width="90%" >
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="综合实践活动"  style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" readonly="readonly" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="综合实践活动"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
						 </td>
			  		</tr>
			  		<tr>
						 <td width="10%" style="background:#eee;"> 关键词：</td>
						 <td width="90%" style="background:#eee;">
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="综合实践活动"  type="text" style="border: 0px" class="fl wenbenkuang6712" name="  关键词" value="${item.keyword}" readonly="readonly" id="${item.partId}_${item.twoPartId}keyword" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="综合实践活动"  type="text" style="border: 0px" class="fl wenbenkuang6711" name="  关键词" value="${item.keyword}"  id="${item.partId}_${item.twoPartId}keyword" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}keywords" value="${item.keyword}"/>
						 </td>
			  		</tr>
			  		<tr>
						 <td width="10%" style="background:#eee;"> 合作人：</td>
						 <td width="90%" style="background:#eee;">
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="综合实践活动"  type="text"  style="border: 0px" class="fl wenbenkuang6712" name="  合作人" value="${item.cooperationMan}" readonly="readonly" id="${item.partId}_${item.twoPartId}cooperationMan" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="综合实践活动"  type="text"  style="border: 0px" class="fl wenbenkuang6711" name="  合作人" value="${item.cooperationMan}" id="${item.partId}_${item.twoPartId}cooperationMan" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}cooperationMans" value="${item.cooperationMan}"/>
						 </td>
			  		</tr>
			  		
			  		<tr>
				  		<td width="10%" style="background:#eee;"> 起止日期： </td>
						<td width="90%" style="background: #eee;">
						    <span  class="fl wenbenkuang100"   id="new_startdate"  >${item.startDate}</span> 
							<span class="fl">至</span> 
							<span  class="fl wenbenkuang100"   id="new_enddate"  >${item.endDate}</span>
						</td>
					</tr> 
				  	<tr>
						<td width="10%" style="background:#eee;">内容描述：</td>
						  <td width="90%" style="padding-right: 6px">
							 <textarea columnName="综合实践活动"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
							 <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
						</td>
				  	</tr>
				  
				  	<tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
						</td>
				  	</tr>
				  </table>
				</c:forEach>
		      
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">研究成果</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[ZHSJ_YJXX_YJCG]}" var="item">
		      	 <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
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
					<!-- 如果值为空 不显示下列信息 -->
					<c:if test="${!empty(item.attachFileDtos) }">
						 <tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
								评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
							</td>
				  		 </tr>
					</c:if>
				 </table>
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td class="h50 th">自我评价</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[ZHSJ_YJXX_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
								<tr>
									<td style="padding-right: 6px">
									<textarea columnName="综合实践活动"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
										<span style='float:right'>
											评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
										</span>
										<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
									</td>
								</tr>
				  		</table>	
				</c:forEach>
		      </td>
        </tr>
        
		    <tr>
		      <td height="191" rowspan="2" class="h50 th">社区服务与社会实践<span class="red"></span></td>
		      <td height="191" class="h50 th">基本情况</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[ZHSJ_SQFWSHSJ_JBQK]}" var="item" varStatus="pVs">
		      	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		      	
				    <tr >
				  	 	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">基本情况${pVs.count}</td>
				    </tr>
				    
		      		<tr>
						 <td width="10%" style="background:#eee;"> 主题：</td>
						 <td width="90%">
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="综合实践活动"  style="border: 0px" type="text" class="fl wenbenkuang6712" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="综合实践活动"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
						 </td>
			  		</tr>
			  		<tr>
						 <td width="10%" style="background:#eee;"> 地点：</td>
						 <td width="90%" style="background:#eee;">
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="综合实践活动" type="text"  style="border: 0px" class="fl wenbenkuang6712" name="  地点" value="${item.address}" readonly="readonly" id="${item.partId}_${item.twoPartId}address" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="综合实践活动" type="text"  style="border: 0px" class="fl wenbenkuang6711" name="  地点" value="${item.address}" id="${item.partId}_${item.twoPartId}address" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}addresss" value="${item.address}"/>
						 </td>
			  		</tr>
			  		
			  		
			  		
			  <tr>
				  		<td width="10%" style="background:#eee;"> 起止日期： </td>
						<td width="90%" style="background: #eee;">
						    <span  class="fl wenbenkuang100"   id="new_startdate"  >${item.startDate}</span> 
							<span class="fl">至</span> 
							<span  class="fl wenbenkuang100"   id="new_enddate"  >${item.endDate}</span>
						</td>
					</tr> 
			  		
				  	<tr>
					    <td width="10%" style="background:#eee;">内容描述：</td>
					    <td width="90%" style="padding-right: 6px">
						   <textarea columnName="综合实践活动" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
						   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
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
							评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
						</td>
				  	</tr>
				  </table>
				</c:forEach>
		      
		      
		      </td>
        </tr>
        
		    <tr>
		      <td height="95" class="h50 th">自我评价</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[ZHSJ_SQFWSHSJ_ZWPJ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="综合实践活动" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>	
				</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td rowspan="5" class="h50 th"><a id="1009"></a>个性发展</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[GXFZ_ZWPJ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="个性发展" class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  自我评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>	
				</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[GXFZ_TRPJ_TX]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td class="h50 th">教师</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[GXFZ_TRPJ_JS]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
        	</tr>
        
		    <tr>
		      <td class="h50 th">家长</td>
		      <td class="h50 " style="background-color: rgb(238, 238, 238);">
		      	<c:forEach items="${appraiseMaps[GXFZ_TRPJ_JZ]}" var="item">
			  		<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
						<tr>
							<td style="padding-right: 6px">
							<textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  他人评价" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
								<span style='float:right'>
									评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
								</span>
								<input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
							</td>
						</tr>
			  		</table>
				</c:forEach>
		      </td>
         </tr>
        
		 <tr>
		      <td height="191" colspan="2" class="h50 th">特长与成果展示<span class="red">*</span></td>
		      <td class="h50" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[GXFZ_CGZS]}" var="item" varStatus="pVs">
		      	<table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		      	
		      		<tr >
				  	 	<td colspan="2" align="left" style="background-color: rgb(238, 238, 238);">特长与成果展示${pVs.count}</td>
				    </tr>
		      	
		      		<tr>
						 <td width="10%" style="background:#eee;"> 主题：</td>
						 <td width="90%" >
						 <c:if test="${item.isReadOnly}">
						 <div style="width:100%;height:100%;">
						 <input columnName="个性发展"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" readonly="readonly" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </div>
						 </c:if>
						 <c:if test="${!item.isReadOnly}">
						 <input columnName="个性发展"  style="border: 0px" type="text" class="fl wenbenkuang6711" name="  主题" value="${item.topic}" id="${item.partId}_${item.twoPartId}topic" onblur="saveData(this,'${item.writeMan}')"/>
						 </c:if>
						 <input type="hidden" id="${item.partId}_${item.twoPartId}topics" value="${item.topic}"/>
						 </td>
			  		</tr>
				    <tr>
					  <td width="10%" style="background:#eee;">内容描述：</td>
					  <td width="90%" style="padding-right: 6px">
						   <textarea columnName="个性发展"  class="textarea" style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;" name="  内容描述" <c:if test="${item.isReadOnly}">readonly=true</c:if> id="${item.partId}_${item.twoPartId}" onblur="saveData(this,'${item.writeMan}')">${item.partInfo}</textarea>
						   <input type="hidden" id="${item.partId}_${item.twoPartId}s" value="${item.partInfo}"/>
					  </td>
				    </tr>
				    
				  	<c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td width="10%" style="background:#eee;">附件${state.count}：</td>
						  <td width="90%" style="background:#eee;">
						     <span style="float:left"><a href="${ctx }${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
					</c:forEach>
				  	<tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							评价人：${item.writeMan}&nbsp&nbsp&nbsp&nbsp签名：${item.signerName}&nbsp&nbsp&nbsp&nbsp日期：${item.createDate}
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
</body>

