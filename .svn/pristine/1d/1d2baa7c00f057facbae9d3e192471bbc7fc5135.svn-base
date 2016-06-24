<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="java.util.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<un:bind var="STUDENT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_STUDENT"/>    <%-- 学生 --%>
<un:bind var="PARENT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_PARENT"/>       <%--家长  --%>
<un:bind var="SPORT" type="com.flyrish.hades.common.Constant" field="USER_TYPE_SPORTSEMASTER"/> <%--德育老师  --%>
<un:bind var="COURSE" type="com.flyrish.hades.common.Constant" field="USER_TYPE_COURSEMASTER"/> <%--任课老师  --%>
<un:bind var="MASTER" type="com.flyrish.hades.common.Constant" field="USER_TYPE_CLASSMASTER"/>  <%-- 班主任 --%>
<un:bind var="SCHOOLADMIN" type="com.flyrish.hades.common.Constant" field="USER_TYPE_SCHOOLADMIN"/> 
<un:bind var="CONSTY" type="com.flyrish.hades.common.Constant" field="USER_KIND_COUNTY"/>  <%-- 区县 --%>
<un:bind var="ADMIN" type="com.flyrish.hades.common.Constant" field="USER_KIND_CITY"/>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-all-debug.js"></script>
<script type="text/javascript" src="${ctx}/ext-2.1/ext-lang-zh_CN.js"></script>
<%-- <script src="${ctx}/js/jquery.min.js" type="text/javascript"></script> --%>




<title>首页</title>	

 <script type="text/javascript">
  $(document).ready(function(){
        if('${userRealtype}'=='${MASTER}'||'${userRealtype}'=='${PARENT}'||'${userRealtype}'=='${STUDENT}'||'${userRealtype}'=='${COURSE}'){
        	$("#appraisal").append("数据正在加载中");
        	Ext.Ajax.request({
    			url:'${ctx}/homePage/HomePageAction.a?queryNewApprasial',
    			method:'POST',
    			defaults:{autoScroll: true},
    			success:function(response,options){
    			 	var content = Ext.decode(response.responseText);
    			 	var inhtml="";
    			    if(content!=null&&content.length>0)
    			 	{
    			    	var _TheArray = new Array("xqys","xqjs","sxdd","xycj","hzjl","ydjk","smbx","zhsj","gxfz");
   			 			for(var i=0;i<content.length;++i)
   			 			{
   			 				if (content[i]!=0) {
	   			 				var contentId=window.document.getElementById(_TheArray[i]);
	   			 				if(contentId!=null)
	   			 					contentId.innerHTML= content[i];
   			 				} else {
   			 					var divId = window.document.getElementById(_TheArray[i]+"Div");
   			 					divId.style.display="none";
   			 				}
   			 				/*  inhtml+="<li> <span class='con_wenziliebiao fl'>"+content[i].msg+"</span><span class='con_data fr'>"+content[i].signdate1+"</span></li>"; */
   			 			}
    			 	}
    			    /* document.getElementById("appraisal").innerHTML="";
    			    $("#appraisal").append(inhtml); */
    			}
    		});
        }
         $("#inform").append("数据正在加载中");
    	   Ext.Ajax.request({
			url:'${ctx}/homePage/HomePageAction.a?queryInform',
			method:'POST',
			defaults:{autoScroll: true},
			success:function(response,options){
			    var jsoncontent = Ext.decode(response.responseText);
			    var count = jsoncontent.count;
			    var informcontent = jsoncontent.content;
			 	var informhtml="";
			    if(informcontent!=null&&informcontent.length>0)
			 	{
			    	for(var i=0;i<informcontent.length;++i)
			    	{
			    		informhtml+="<li><span class='con_wenziliebiao fl'><a href='${ctx}/inform/InformAction.a?queryInformation&informIds="
			    				+informcontent[i].informid+"'>"+informcontent[i].theme+"</a>";
			    		if(informcontent[i].flag=="-1")
			    		{
			    			informhtml+="<img src='${ctx}/images/ico_news2.gif' width='22' height='9' />";
			    		}
			    		informhtml+="</span> <span class='con_data fr'>"+informcontent[i].startDate+"</span></li>";
			    	}
			 		
			 	}
			    document.getElementById("inform").innerHTML="";
			    $("#inform").append(informhtml);
			    if('${userRealtype}'=='${MASTER}'||'${userRealtype}'=='${PARENT}'||'${userRealtype}'=='${STUDENT}'||'${userRealtype}'=='${COURSE}'){
			    	if(count>5)
			    	{
			    		$("#inform").after("<span class='con_more'><a href='${ctx}/homePage/HomePageAction.a?queryInform&moreflag=1'>更多>></a></span>");
			    	}
			    }else{
			    	if(count>24)
			    	{
			    		$("#inform").after("<span class='con_more'><a href='${ctx}/homePage/HomePageAction.a?queryInform&moreflag=1'>更多>></a></span>");
			    	}
			    } 
			 } 
		 }); 
	});
			      
	function nogo(){
		alert("功能尚未开通！");
		return false;
	}			
	
	function clearContent(commonFuncId) {
		 $.ajax({
				url:'${ctx}/homePage/HomePageAction.a?ClearContent&time='+new Date().getTime(),
				method:'POST',
				data:{
					commonFuncId: commonFuncId
				}
			});
		return true;
	}
</script>
</head>
<body>
<div id="content">
<%-- <%Map<String,Integer> maps=(Map<String,Integer>)request.getAttribute("countMap"); %> --%>
<c:if test="${usertype eq CONSTY or usertype eq ADMIN or userRealtype eq SPORT or userRealtype eq  SCHOOLADMIN}">
	<div class="content_left mt10 fl">
		<div class="zuixingpingjia">
			<h3 class="con_title">通知公告 </h3>
			<ul id="inform">
			</ul>
		</div>
	</div>
</c:if>
<!-- 学生和家长 -->
<c:if test="${userRealtype eq STUDENT or userRealtype eq PARENT}">
	<div class="left1 fl pd-r mar-t15">
		<div class="one mar-b14 wh">
			<div class="nr">
			<c:if test="${userRealtype eq STUDENT}">
					<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1001" class="text_t">新学期伊始的我</a>
					<ul>
						<li id="xqysDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1001" onclick="clearContent(1001)">您有<span id="xqys" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>学期开始后1个月内完成</li>
					</ul>
			</c:if>
			<c:if test="${userRealtype eq PARENT}">
				<c:if test="${levelcode eq '2012002' }">
					<a href="#" class="text_t">新学期伊始的我</a>
					<ul>
						<li id="xqysDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1001" onclick="clearContent(1001)">您有<span id="xqys" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>学期开始后1个月内完成</li>
					</ul>
				</c:if>
				<c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
					<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1001" class="text_t">新学期伊始的我</a>
					<ul>
						<li id="xqysDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1001" onclick="clearContent(1001)">您有<span id="xqys" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>学期开始后1个月内完成</li>
					</ul>
				</c:if>
			</c:if>
			</div>
		</div>
		<div class="two mar-b14 wh">
			<div class="nr">
				<c:if test="${userRealtype eq STUDENT}">
					<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1004" class="text_t">学业成就</a>
					<ul>
						<c:if test="${levelcode eq '2012002' }"> 
							<li id="xycjDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
			            	<li title="学业成就->自我评价：学期结束后1周内完成">学业成就->自我评价：学期结束后1周内完成</li>
              				<li title="学业成就->学科学习过程记录：第1学期在11月底前完成，第2学期在5月底前完成">学业成就->学科学习过程记录：第1学期在11月底前完成，第2学期在5月底前完成</li>
						</c:if> 
						<c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
							<li id="xycjDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
              				<li title="学业成就->自我评价：学期结束后1周内完成">学业成就->自我评价：学期结束后1周内完成</li>
              				<li title="学业成就->学科学习过程记录：第1学期在11月底前完成，第2学期在5月底前完成">学业成就->学科学习过程记录：第1学期在11月底前完成，第2学期在5月底前完成</li>
            			</c:if>
					</ul>
				</c:if>
				<c:if test="${userRealtype eq PARENT}">
					<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1004" class="text_t">学业成就</a>
					<ul>
						<li id="xycjDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="three wh">
			<div class="nr">
				<c:if test="${userRealtype eq STUDENT}">
					<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1008" class="text_t">综合实践活动</a>
					<ul>
						<li id="zhsjDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?queryCommentTreeMenu&&commonFuncId=1008" onclick="clearContent(1008)">您有<span id="zhsj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
						<li>新学期开学后1个月内完成</li>				 
					</ul>
				</c:if>
				<c:if test="${userRealtype eq PARENT}">
					<a href="#" class="text_t">综合实践活动</a>
					<ul>
						<li id="zhsjDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?queryCommentTreeMenu&&commonFuncId=1008" onclick="clearContent(1008)">您有<span id="zhsj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>新学期开学后1个月内完成</li>				 
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</c:if>
<!-- 老师 -->
<c:if test="${userRealtype eq COURSE or userRealtype eq MASTER}">
	<div class="left1 fl pd-r mar-t15">
		<div class="one mar-b14 wh">
			<div class="nr">
			<c:if test="${userRealtype eq COURSE}">
					<a href="#" class="text_t">新学期伊始的我</a>
					<ul>
						<li id="xqysDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1001" onclick="clearContent(1001)">您有<span id="xqys" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>学期开始后1个月内完成</li>
					</ul>
			</c:if>
			<c:if test="${userRealtype eq MASTER}">
				<a href="#" class="text_t">新学期伊始的我</a>
				<ul>
					<li id="xqysDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1001" onclick="clearContent(1001)">您有<span id="xqys" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
                	<li>学期开始后1个月内完成</li>
				</ul>
			</c:if>
			</div>
		</div>
		<div class="two mar-b14 wh">
			<div class="nr">
				<c:if test="${userRealtype eq COURSE}">
					<ul>
						<c:if test="${levelcode eq '2012002' }"> 
							<a href="#" class="text_t">学业成就</a>
							<li id="xycjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>
						</c:if> 
						<c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
							<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1004" class="text_t">学业成就</a>
							<li id="xycjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
              				<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>
            			</c:if>
					</ul>
				</c:if>
				<c:if test="${userRealtype eq MASTER}">
					<ul>
	                	<c:if test="${levelcode eq '2012002' }"> 
							<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=9999" class="text_t">课程评语</a>
							<li id="xycjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li>学期结束后1周内完成</li>
						</c:if> 
						<c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
							<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1004" class="text_t">学业成就</a>
							<li id="xycjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1004" onclick="clearContent(1004)">您有<span id="xycj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
              				<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>
            			</c:if>
					</ul>
				</c:if>
			</div>
		</div>
		<div class="three wh">
			<div class="nr">
				<c:if test="${userRealtype eq COURSE}">
					<a href="#" class="text_t">综合实践活动</a>
					<ul>
						<li id="zhsjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1008" onclick="clearContent(1008)">您有<span id="zhsj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
						<li>新学期开学后1个月内完成</li>				 
					</ul>
				</c:if>
				<c:if test="${userRealtype eq MASTER}">
					<a href="#" class="text_t">综合实践活动</a>
					<ul>
						<li id="zhsjDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1008" onclick="clearContent(1008)">您有<span id="zhsj" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                	<li>新学期开学后1个月内完成</li>				 
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${usertype eq CONSTY or usertype eq ADMIN or userRealtype eq SPORT or userRealtype eq  SCHOOLADMIN}">
  <div class="content_center mt10 ml10 fl">
    <div class="tongzhigonggao con_h180">
      <div class="plr20">
        <h3 class="con_title">业务提示 </h3>
        <ul>
        	<li>1、支持学生历史评价数据填写，可以选择历史学期，填写历史学期评价数据。</li>
		   	<li>2、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。</li>	
        </ul>
     </div>
    </div>
    <div class="yewutishi con_h380 mt10">
      <div class="plr20">
        <%@ include file="/common/business.jsp" %>
     </div>
    </div>
  </div>
</c:if> 
<!-- 学生和家长 -->
<c:if test="${userRealtype eq STUDENT or userRealtype eq PARENT}">
	<div class="left2 fl pd-r mar-t15">
		<div class="one2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1002" class="text_t">新学期结束的我</a>
						<ul>
							<li id="xqjsDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1002" onclick="clearContent(1002)">您有<span id="xqjs" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li>学期结束后1周内完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1002" class="text_t">新学期结束的我</a>
						<ul>
							<li id="xqjsDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1002" onclick="clearContent(1002)">您有<span id="xqjs" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li>学期开始后1个月内完成</li>
						</ul>
					</c:if>
					
				</div>
		</div>
		<div class="two2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1005" class="text_t">合作与交流</a>
						<ul>
							<li id="hzjlDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1005" onclick="clearContent(1005)">您有<span id="hzjl" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1005" class="text_t">合作与交流</a>
						<ul>
							<li id="hzjlDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1005" onclick="clearContent(1005)">您有<span id="hzjl" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="three2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1006" class="text_t">运动与健康</a>
						<ul>
							<li id="ydjkDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1006" onclick="clearContent(1006)">您有<span id="ydjk" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1006" class="text_t">运动与健康</a>
						<ul>
							<li id="ydjkDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1006" onclick="clearContent(1006)">您有<span id="ydjk" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="four2 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1009" class="text_t">个性发展</a>
						<ul>
							<li id="gxfzDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1009" onclick="clearContent(1009)">您有<span id="gxfz" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1009" class="text_t">个性发展</a>
						<ul>
							<li id="gxfzDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1009" onclick="clearContent(1009)">您有<span id="gxfz" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
	</div>
</c:if>

<!-- 老师 -->
<c:if test="${userRealtype eq COURSE or userRealtype eq MASTER}">
	<div class="left2 fl pd-r mar-t15">
		<div class="one2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="#" class="text_t">新学期结束的我</a>
						<ul>
							<li id="xqjsDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1002" onclick="clearContent(1002)">您有<span id="xqjs" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li>学期结束后1周内完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="#" class="text_t">新学期结束的我</a>
						<ul>
							<li id="xqjsDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1002" onclick="clearContent(1002)">您有<span id="xqjs" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li>学期结束后1周内完成</li>				 
						</ul>
					</c:if>
					
				</div>
		</div>
		<div class="two2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1005" class="text_t">合作与交流</a>
						<ul>
							<li id="hzjlDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1005" onclick="clearContent(1005)">您有<span id="hzjl" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1005" class="text_t">合作与交流</a>
						<ul>
							<li id="hzjlDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1005" onclick="clearContent(1005)">您有<span id="hzjl" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="three2 mar-b10 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1006" class="text_t">运动与健康</a>
						<ul>
							<li id="ydjkDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1006" onclick="clearContent(1006)">您有<span id="ydjk" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1006" class="text_t">运动与健康</a>
						<ul>
							<li id="ydjkDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1006" onclick="clearContent(1006)">您有<span id="ydjk" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="four2 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1009" class="text_t">个性发展</a>
						<ul>
							<li id="gxfzDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1009" onclick="clearContent(1009)">您有<span id="gxfz" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1009" class="text_t">个性发展</a>
						<ul>
							<li id="gxfzDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1009" onclick="clearContent(1009)">您有<span id="gxfz" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
	                    	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
	</div>
</c:if>
  
<c:if test="${usertype eq CONSTY or usertype eq ADMIN or userRealtype eq SPORT or userRealtype eq  SCHOOLADMIN}">
<div class="content_right mt10 ml10 fl" id="commonFunc" >
    <div class="ziwopingjia con_h150">
      <div class="plr20">
      <c:if test="${ fn:length(userCommonFunc) >=1 }">
	        <c:forEach items="${userCommonFunc }" var="commonFunc" begin="0" end="0">
		        <c:if test="${not empty commonFunc.url}">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="${ctx }${commonFunc.url }" target="mainframe_all"><img src="${ctx }/images/sy_zwpj_icon.png" width="100" height="80" /></a>
			        </div>
		        </c:if>
    		    <c:if test="${empty commonFunc.url}">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="#" onclick="nogo();"><img src="${ctx }/images/sy_zwpj_icon.png" width="100" height="80" /></a>
			        </div>
		        </c:if>
	        </c:forEach>
        </c:if>
      </div>
    </div>
    
    <div class="pingjiatongxue con_h150 mt10 ">
      <div class="plr20">
	  	<c:if test="${ fn:length(userCommonFunc) >=2 }">
			<c:forEach items="${userCommonFunc }" var="commonFunc" begin="1" end="1">
				<c:if test="${not empty commonFunc.url }">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="${ctx }${commonFunc.url }" target="mainframe_all"><img src="${ctx }/images/sy_pjtx_bg.png" width="100" height="80" /></a>
			       	</div>
		        </c:if>
		        <c:if test="${empty commonFunc.url }">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="#" onclick="nogo();"><img src="${ctx }/images/sy_pjtx_bg.png" width="100" height="80" /></a>
			       	</div>
		        </c:if>
			</c:forEach>
	  	</c:if>
      </div>
    </div>
    
    <div class="chakanpingjia con_h150 mt10">
      <div class="plr20">
        <c:if test="${ fn:length(userCommonFunc) >=3}">
			<c:forEach items="${userCommonFunc }" var="commonFunc" begin="2" end="2">
			<c:if test="${not empty commonFunc.url }">
		        <h3 class="con_title">
		        	${commonFunc.funcName }
		        </h3>
		        <div class="icon">
	        		<a href="${ctx }${commonFunc.url }" target="mainframe_all"><img src="${ctx}/images/sy_ckpj_bg.png" width="100" height="80" /></a>
		        </div>
	        </c:if>
	        <c:if test="${empty commonFunc.url }">
		        <h3 class="con_title">
		        	${commonFunc.funcName }
		        </h3>
		        <div class="icon">
	        		<a href="#" onclick="nogo();"><img src="${ctx}/images/sy_ckpj_bg.png" width="100" height="80" /></a>
		        </div>
	        </c:if>
			</c:forEach>
	  	</c:if>
      </div>
    </div>
    
    <div class="shujujiancha con_h160 mt10">
      <div class="plr20">
        <c:if test="${ fn:length(userCommonFunc) >=4}">
			<c:forEach items="${userCommonFunc }" var="commonFunc" begin="3" end="3">
				<c:if test="${not empty commonFunc.url }">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="${ctx }${commonFunc.url }" target="mainframe_all"><img src="${ctx}/images/sy_ckpj_bg.png" width="100" height="80" /></a>
			        </div>
		        </c:if>
		        <c:if test="${empty commonFunc.url }">
			        <h3 class="con_title">
			        	${commonFunc.funcName }
			        </h3>
			        <div class="icon">
		        		<a href="#" onclick="nogo();"><img src="${ctx}/images/sy_ckpj_bg.png" width="100" height="80" /></a>
			        </div>
		        </c:if>
			</c:forEach>
	  	</c:if>
      </div>
    </div>
  </div>
</c:if>

<!-- 学生和家长 -->
<c:if test="${userRealtype eq STUDENT or userRealtype eq PARENT}">
	<div class="left3 fl pd-r mar-t15">
		<div class="one3 mar-b14 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1003" class="text_t">思想道德</a>
						<ul>
							<li id="sxddDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1003" onclick="clearContent(1003)">您有<span id="sxdd" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1003" class="text_t">思想道德</a>
						<ul>
							<li id="sxddDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1003" onclick="clearContent(1003)">您有<span id="sxdd" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="two3 mar-b14 wh">
				<div class="nr">
					<c:if test="${userRealtype eq STUDENT}">
						<a href="${ctx}/selfappraise/ZWPJAtcion.a?commonFuncId=1007" class="text_t">审美与表现</a>
						<ul>
							<li id="smbxDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1007" onclick="clearContent(1007)">您有<span id="smbx" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
		                	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq PARENT}">
						<a href="${ctx}/appraisechild/AppraisalChildAction.a?commonFuncId=1007" class="text_t">审美与表现</a>
						<ul>
							<li id="smbxDiv"><a href="${ctx}/appraise/QueryAppraiseAction.a?commonFuncId=1007" onclick="clearContent(1007)">您有<span id="smbx" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
		                	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				 </div>
		</div>
		<div class="three3 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
					<a href="#" class="text_t">志愿服务</a>
					<ul>
	                	<li></li>				 
					</ul>
				</div>
		</div>
	</div>
</c:if>

<!-- 老师 -->
<c:if test="${userRealtype eq COURSE or userRealtype eq MASTER}">
	<div class="left3 fl pd-r mar-t15">
		<div class="one3 mar-b14 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1003" class="text_t">思想道德</a>
						<ul>
							<li id="sxddDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1003" onclick="clearContent(1003)">您有<span id="sxdd" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1003" class="text_t">思想道德</a>
						<ul>
							<li id="sxddDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1003" onclick="clearContent(1003)">您有<span id="sxdd" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
							<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
		<div class="two3 mar-b14 wh">
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=1007" class="text_t">审美与表现</a>
						<ul>
							<li id="smbxDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1007" onclick="clearContent(1007)">您有<span id="smbx" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
		                	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=1007" class="text_t">审美与表现</a>
						<ul>
							<li id="smbxDiv"><a href="${ctx}/master/MasterAppriseAction.a?queryCommentTreeMenu&&commonFuncId=1007" onclick="clearContent(1007)">您有<span id="smbx" class="num"></span>条最新评价记录</a><img src="${ctx }/images/ico_news2.gif" width="22" height="9" /></li>
		                	<li title="第1学期在11月底前完成，第2学期在5月底前完成">第1学期在11月底前完成，第2学期在5月底前完成</li>				 
						</ul>
					</c:if>
				 </div>
		</div>
		<div class="three3 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
					<c:if test="${userRealtype eq COURSE}">
						<a href="${ctx}/teacher/MasterAppriseAction.a?commonFuncId=9999" class="text_t">课程评语</a>
						<ul>
							<li>学期结束后1周内完成</li>
						</ul>
					</c:if>
					<c:if test="${userRealtype eq MASTER}">
						<a href="${ctx}/master/MasterAppriseAction.a?commonFuncId=9998" class="text_t">班主任评语</a>
						<ul>
							<li>学期结束后1周内完成</li>				 
						</ul>
					</c:if>
				</div>
		</div>
	</div>
</c:if>

<!-- 学生和家长 -->
<c:if test="${userRealtype eq STUDENT or userRealtype eq PARENT}">
	<div class="left4 fl mar-t15">
		<div class="one4 mar-b14 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
						<a href="#" class="text_t">通知公告 </a>
						<ul id="inform">
						</ul>
				</div>
		</div>
		<div class="two4 mar-b14 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
						<a href="#" class="text_t">业务提示</a>
						<ul>
		                	<li title="1、支持学生历史评价数据填写，可以选择历史学期，填写历史学期评价数据。">1、支持学生历史评价数据填写，可以选择历史学期，填写历史学期评价数据。</li>
		                	<li title="2、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。">2、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。</li>				 
						</ul>
				</div>
		</div>
	</div>
</c:if>

<!-- 老师 -->
<c:if test="${userRealtype eq COURSE or userRealtype eq MASTER}">
	<div class="left4 fl mar-t15">
		<div class="one4 mar-b14 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
					<a href="#" class="text_t">通知公告 </a>
						<ul id="inform">
						</ul>
				</div>
		</div>
		<div class="two4 mar-b14 wh">
			<!-- <a href="#" class="bg_n"><span class="num">5</span></a> -->
				<div class="nr">
					<a href="#" class="text_t">业务提示</a>
					<ul>
	                	<li title="1、支持学生历史评价数据填写，可以选择历史学期，填写历史学期评价数据。">1、支持学生历史评价数据填写，可以选择历史学期，填写历史学期评价数据。</li>
		                <li title="2、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。">2、班主任评语与学籍管理云平台的学生操行评语实时互通，只需在一个平台录入，两个平台皆可显示。</li>			 
					</ul>
				</div>
		</div>
	</div>
</c:if>
</div>
</body>
</html>
