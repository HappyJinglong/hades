<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自我评价</title>

</head>
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/generateuuid.js"></script> 
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script> 
<style type="text/css"> 
body,html{ width:100%; height:100%;}
.span1 {
  	float: left; 
    height: 44px;
    text-align: left;
    width: 400px;
}
.span2 {
  	float: right; 
    height: 44px;
    text-align: right;
    width: 800px;
}
.xue_zuti {
    /*background: #34495e;*/
    color: #fff;
 	float: left;
    height: 43px;
    padding: 0;
   	width:100%;
} 
.xue_zuti .span1 {
   color: #37840c;
   float: left;  
   font-size: 14px;
   font-weight: bold;
   height: 43px;
   line-height: 43px;
   margin: 0;
   padding: 0;
   text-align: center;
   width: 115px;
   background:url("${ctx}/images/xue_pic_02.gif") repeat scroll 0 0;
}
.xue_zuti .span2 {
    background:url("${ctx}/images/xue_pic_01.gif") repeat scroll 0 0;
    color: #0014ff;
    float: left;
    font-size: 14px;
    height: 43px;
    line-height: 43px;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 115px;
}
.xue_zuti .span3 {
    background:url("${ctx}/images/xue_pic_01.gif") repeat scroll 0 0;
    color: #0014ff;
    float: left;
    font-size: 14px;
    height: 43px;
    line-height: 43px;
    margin: 0;
    padding: 0;
    text-align: center;
    width: 115px;
}
.lanmu2 {
   color: #0a3896; 
    text-decoration: none;
}
.wenben86left{width:86px; height:26px; line-height:26px; text-align:left;}
</style>
<body>
<input type="hidden" name="filePath" id="filePath" value="${filePath}"/>
<input type="hidden" name="fileName" id="fileName" value="${fileName}"/>
<input type="hidden" name="choicenum" id="choicenum" value="${choicenum}"> 
<input type="hidden" name="evaluatePersonName" id="evaluatePersonName" value="${evaluatePersonName}">

 <div class="xue_zuti">
 	<span  id="span2"  class="span1"><a  id="student" class="lanmu2" onclick="changeType('9010')">研究性学习</a></span>
 	<span  id="span1" class="span2"><a  id="teacher"  class="lanmu2" onclick="changeType('9020')">社区服务</a></span>	
    <span  id="span3" class="span2"><a  id="student1" class="lanmu2" onclick="changeType('9030')">社会实践活动 </a></span>	 
 </div>
<div class="dangqianwz">
 	<%-- <span class="fl" id="yjxxspan" style="display:">当前位置：自我评价->综合实践活动->研究性学习</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<span class="fl" id="sqfuspan" style="display:none">当前位置：自我评价->综合实践活动->社区服务</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	<span class="fl" id="sjhdspan" style="display:none">当前位置：自我评价->综合实践活动->社会实践活动</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span >学期：
	     <select name="termId" id="termId" onchange="changeterm()" class="wenziliebiao100">
            <app:highSchoolTermTag selectClassid="${classid}" selectNum="${termId}" levelCode="${levelcode}"/>
       	 </select> 
	</span>  --%>
 </div>
<div id="pj_ziwo_main">
<div id="new">
    <div class="down mt18 pb30">
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="new_table">
           <tr class="title_bg">
             <td colspan="3" id="new_yjxxtd">研究性学习内容</td>
             <td colspan="3" id="new_sqfutd" style="display:none">社区服务内容</td>
             <td colspan="3" id="new_sjhdtd" style="display:none">社会实践活动内容</td>
           </tr>
		    <tr>
	      <td width="10%" height="40 " class="bg_eee" >1</td>
	      <td colspan="2" class="youjuzhong pr20 bg_eee" >
	     	 评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 日期：<input id="new_startDate1" type="text" size="10" onClick="WdatePicker()" value="${nowDate}"/>
	      </td>
        </tr>
           <tr>
           <!-- 研究性学习 -->
             <td colspan="2" class="h50">
              <div id="new_yanjiuxuxi" style="display:">
             	<div class="downbox mt18">
                    <span class="fl wenben86left">题目：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_tm"  />
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben86left">合伙人：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_hhr"  />
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben86left">总学时数：</span>
                    <input type="text" class="fl wenbenkuang100" id="new_zxss"  />
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben86left">实施路径：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_sslj"  />
                </div>
             </div>
              
              <!-- 社区服务 -->
              <div id="new_shequfuwu" style="display:none">
              <div class="downbox mt18">
                    <span class="fl wenben220left">选择次数：</span>
                <select name="select" class=" fl  wenziliebiao100" id="new_xzcs">
          			<option value="第一次" >第一次</option>
       				<option value="第二次" >第二次</option>
       				<option value="第三次" >第三次</option>
            	</select>  
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务社区名称：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwsqmc"  />
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务社区联系电话：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_lxdh"  />
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务时数：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwss"  />
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben220left">服务日期：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwsj"  />
                </div>
             </div>
              <!-- 社会实践活动  -->
           	 <div id="new_zongheshijian" style="display:none">
              	<div class="downbox mt18">
                    <span class="fl wenben86left">选择活动种类：</span>
                  <select name="select" class=" fl  wenziliebiao100" id="new_hdzl">
             		<option value="社会考察活动">社会考察活动</option>
           			<option value="社会调查活动">社会调查活动</option>
           			<option value="社会实践活动">社会实践活动</option>
           			<option value="其他">其他</option>
            	  </select>  
               </div>
               <div class="downbox mt18">
                  <span class="fl wenben86left">成果形成：</span>
                  <select name="select" class=" fl  wenziliebiao100" id="new_cgxc">
              		<option value="个人体会">个人体会</option>
           			<option value="考察报告">考察报告</option>
           			<option value="个人总结">个人总结</option>
           			<option value="调查报告">调查报告</option>
           			<option value="其他">其他</option>
            	  </select>  
               </div>
               <div class="downbox mt18">
                    <span class="fl wenben86left">地点：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_didian"  />
               </div>
               <div class="downbox mt18">
                    <span class="fl wenben86left">完成学时：</span>
                    <input type="text" class="fl wenbenkuang100" id="new_wcxs" />
               </div>
              </div>
              
                
               <div class="downbox mt18">
               		<span class="fl wenben86left" id="new_zynrspan" style="display:none">内容摘要：</span>
                   	<span class="fl wenben86left" id="new_ztspan" style="display:none">主体：</span>  
                    <textarea class="fl wenbenyu750" id="new_content">
                    </textarea>
               </div>
                
                
                 <div class="downbox mt18">
                    <span class="fl wenben85left">附件1：</span>
					 <input name="text" type="text" class="fl wenben180" value="去公园的照片.jpg" />
                      <input type="button" value="下 载" class="shangchuan fl ml10" />
					 <input type="button" value="删 除" class="shangchuan fl ml10" />
               </div>    
			     <div class="downbox mt18">
                    <span class="fl wenben85left">附件：</span>
                    <input name="text" type="text" class="fl wenbenkuang670 fujian" value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" />
                    <input type="button" value="添 加" class="shangchuan fl ml10" />
					 <input type="button" value="上 传" class="shangchuan fl ml10" />
               </div>   
                <div class="downbox mt18">
                    <span class="fl wenben85left">自我评价：</span>
                    <textarea class="fl wenbenyu750" id="new_zwpj">
                    </textarea>
                    <input type="button" value="删 除" class="shangchuan fl ml10" />
					<input type="button" value="添 加" class="shangchuan fl ml10" />
               </div>
                  
		       </td>
			  <td width="100" height="191" class="bg_eee">
			   <input type="button" class="fr shanchu1"/>
                    <input type="button" class="fr tianjia"/>			  
              </td>
           </tr>
           <tr>
             <td colspan="3" class="bg_eee h80">
               <div class="fr btn">
                 <input type="button" class="fr shanchu"/>
                </div>             
             </td>
           </tr>
      </table>
    </div>
    </div>
    <c:if test="${not empty practicesDtosList}">
    <div id="old">
    <div class="down mt18 pb30">
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge" id="new_table">
           <tr class="title_bg">
             <td colspan="3" id="new_yjxxtd" style="display:">研究性学习内容<span class="red">*</span></td>
             <td colspan="3" id="new_sqfutd" style="display:none">社区服务内容</td>
             <td colspan="3" id="new_sjhdtd" style="display:none">社会实践活动内容</td>
           </tr>
		    <tr>
	      <td width="10%" height="40 " class="bg_eee" >1</td>
	      <td colspan="2" class="youjuzhong pr20 bg_eee" >
	     	 评价人：本人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 签名：${evaluatePersonName} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 日期：<input id="new_startDate1" type="text" size="10" onClick="WdatePicker()" value="${nowDate}"/>
	      </td>
        </tr>
        	<c:forEach items="${appraisalList1}" var="list" varStatus="status">
           <tr>
           <!-- 研究性学习 -->
             <td colspan="2" class="h50">
             <c:if test="${list.appraisaltypeid eq '9010'}">
              <div id="new_yanjiuxuxi" style="display:">
             	<div class="downbox mt18">
                    <span class="fl wenben86left">题目：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_tm" value="${list.item6}" maxlength="30"/>
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben86left">合伙人：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_hhr" value="${list.item7}" maxlength="120"/>
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben86left">总学时数：</span>
                    <input type="text" class="fl wenbenkuang100" id="new_zxss" value="${list.item2}" maxlength="120"/>
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben86left">实施路径：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_sslj" value="${list.item9}" maxlength="120"/>
                </div>
             </div>
              </c:if>
              <!-- 社区服务 -->
               <c:if test="${list.appraisaltypeid eq '9010'}">
              <div id="new_shequfuwu" style="display:none">
              <div class="downbox mt18">
                    <span class="fl wenben220left">选择次数：</span>
                <select name="select" class=" fl  wenziliebiao100" id="new_xzcs">
              			<option value="第一次" ${(list.item6 eq "第一次")?"selected":"" }>第一次</option>
           				<option value="第二次" ${(list.item6 eq "第二次")?"selected":"" }>第二次</option>
           				<option value="第三次" ${(list.item6 eq "第三次")?"selected":"" }>第三次</option>
            	</select>  
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务社区名称：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwsqmc" value="${list.item7}" maxlength="120"/>
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务社区联系电话：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_lxdh" value="${list.item8}" maxlength="120"/>
                </div>
                <div class="downbox mt18">
                    <span class="fl wenben220left">服务时数：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwss" value="${list.item2}" maxlength="25"/>
                </div>
                 <div class="downbox mt18">
                    <span class="fl wenben220left">服务日期：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_fwsj" value="${list.item9}" maxlength="25"/>
                </div>
             </div>
             </c:if>
              <!-- 社会实践活动  -->
               <c:if test="${list.appraisaltypeid eq '9010'}">
           	 <div id="new_zongheshijian" style="display:none">
              	<div class="downbox mt18">
                    <span class="fl wenben86left">选择活动种类：</span>
                  <select name="select" class=" fl  wenziliebiao100" id="new_hdzl">
             			<option value="社会考察活动" ${(list.item6 eq "社会考察活动")?"selected":"" }>社会考察活动</option>
           				<option value="社会调查活动" ${(list.item6 eq "社会调查活动")?"selected":"" }>社会调查活动</option>
           				<option value="社会实践活动" ${(list.item6 eq "社会实践活动")?"selected":"" }>社会实践活动</option>
           				<option value="其他" ${(list.item6 eq "其他")?"selected":"" }>其他</option>
            	  </select>  
               </div>
               <div class="downbox mt18">
                  <span class="fl wenben86left">成果形成：</span>
                  <select name="select" class=" fl  wenziliebiao100" id="new_cgxc">
              		<option value="个人体会" ${(list.item7 eq "个人体会")?"selected":"" }>个人体会</option>
           			<option value="考察报告" ${(list.item7 eq "考察报告")?"selected":"" }>考察报告</option>
           			<option value="个人总结" ${(list.item7 eq "个人总结")?"selected":"" }>个人总结</option>
           			<option value="调查报告" ${(list.item7 eq "调查报告")?"selected":"" }>调查报告</option>
           			<option value="其他" ${(list.item7 eq "其他")?"selected":"" }>其他</option>
            	  </select>  
               </div>
               <div class="downbox mt18">
                    <span class="fl wenben86left">地点：</span>
                    <input type="text" class="fl wenbenkuang670" id="new_didian" value="${list.item8}" maxlength="120"/>
               </div>
               <div class="downbox mt18">
                    <span class="fl wenben86left">完成学时：</span>
                    <input type="text" class="fl wenbenkuang100" id="new_wcxs"  value="${list.item2}" maxlength="120"/>
               </div>
              </div>
              </c:if>
               <div class="downbox mt18">
               		<span class="fl wenben86left" id="new_zynrspan">内容摘要：</span>
                   	<span class="fl wenben86left" id="new_ztspan" style="display:none">主体：</span>  
                    <c:if test="${list.appraisaltypeid eq '9010'}">
                    <div id="new_yjxxtextarea">
                    <textarea class="fl wenbenyu750" id="new_content1">
						${list.item1}
                    </textarea>
                    </div>
                    </c:if>
                    <c:if test="${list.appraisaltypeid eq '9020'}">
                     <div id="new_sqfutextarea" style="display:none">
                    <textarea class="fl wenbenyu750" id="new_content2">
						${list.item1}
                    </textarea>
                    </div>
                    </c:if>
                    <c:if test="${list.appraisaltypeid eq '9030'}">
                      <div id="new_sjhdtextarea" style="display:none">
                    <textarea class="fl wenbenyu750" id="new_content3">
						${list.item1}
                    </textarea>
                     </div>
                    </c:if>
               </div>
                <%--  <c:if test="${list.appraisaltypeid eq '9010'}">
                 <div class="downbox mt18">
                    <span class="fl wenben85left">附件1：</span>
					 <input name="text" type="text" class="fl wenben180" value="去公园的照片.jpg" />
                      <input type="button" value="下 载" class="shangchuan fl ml10" />
					 <input type="button" value="删 除" class="shangchuan fl ml10" />
               </div>    
			     <div class="downbox mt18">
                    <span class="fl wenben85left">附件：</span>
                    <input name="text" type="text" class="fl wenbenkuang670 fujian" value="格式仅限doc、docx、txt、jpg、pdf、pps、ppsx、ppt和pptx" />
                    <input type="button" value="添 加" class="shangchuan fl ml10" />
					 <input type="button" value="上 传" class="shangchuan fl ml10" />
               </div>
                 <c:forEach items="${list.attachListForFile}" var="attachFile">
                <div class="downbox mt18">
                    <span class="fl wenben85left">自我评价：</span>
                    <textarea class="fl wenbenyu750" id="new_zwpj">
                    ${list.}
                    </textarea>
                    <input type="button" value="删 除" class="shangchuan fl ml10" />
					<input type="button" value="添 加" class="shangchuan fl ml10" />
               </div>
               </c:forEach>
             </c:if>     --%> 
		       </td>
			  <td width="100" height="191" class="bg_eee">
			   <input type="button" class="fr shanchu1"/>
                    <input type="button" class="fr tianjia"/>			  
              </td>
           </tr>
            </c:forEach>
           <tr>
             <td colspan="3" class="bg_eee h80">
               <div class="fr btn">
                 <input type="button" class="fr shanchu"/>
                </div>             
             </td>
           </tr>
      </table>
    </div>
    </div>
    </c:if>
</div>
</body>
<script type="text/javascript">
///更改学期刷新页面
		function changeterm(){
			var term = document.getElementById("termId").value;
			url="${ctx}/selfappraise/SelfAppAction.a?choicenum="+${choicenum}+"&termId1="+term;
			document.location.replace(url);
		}	
		function changeType(id){
			if(id=="9010"){
				$("#new_yjxxtd").css('display','');
				$("#new_yanjiuxuxi").css('display','');
				$("#new_zynrspan").css('display','');
				$("#new_sqfutd").css('display','none'); 
				$("#new_sjhdtd").css('display','none'); 
				$("#new_shequfuwu").css('display','none'); 
				$("#new_zongheshijian").css('display','none'); 
				$("#new_ztspan").css('display','none'); 
				$("#span2").removeClass().addClass("span1");
				$("#span1").removeClass().addClass("span2") ;
				$("#span3").removeClass().addClass("span2");
			}else if(id=="9020"){
				$("#new_sqfutd").css('display','');
				$("#new_shequfuwu").css('display','');
				$("#new_zynrspan").css('display','');
				$("#new_yjxxtd").css('display','none'); 
				$("#new_sjhdtd").css('display','none'); 
				$("#new_yanjiuxuxi").css('display','none'); 
				$("#new_zongheshijian").css('display','none'); 
				$("#new_ztspan").css('display','none');
				$("#span2").removeClass().addClass("span2");
				$("#span1").removeClass().addClass("span1") ;
				$("#span3").removeClass().addClass("span2");
			}else if(id=="9030"){
				$("#new_sjhdtd").css('display','');
				$("#new_zongheshijian").css('display','');
				$("#new_ztspan").css('display','');
				$("#new_yjxxtd").css('display','none'); 
				$("#new_sqfutd").css('display','none'); 
				$("#new_yanjiuxuxi").css('display','none'); 
				$("#new_shequfuwu").css('display','none'); 
				$("#new_zynrspan").css('display','none');
				$("#span2").removeClass().addClass("span2");
				$("#span1").removeClass().addClass("span2");
				$("#span3").removeClass().addClass("span1");
			}
		}
		function save(){
			var id = window.event.srcElement.getAttribute('id');
			var arr = new Array();
			arr = id.split("_");
			var idd = arr[0];
		}
</script>
</html>