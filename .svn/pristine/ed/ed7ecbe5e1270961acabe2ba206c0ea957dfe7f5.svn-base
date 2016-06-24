<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
           
<title>统计表</title>
<style type="text/css">
#tjb_main .top .biaoge {
	font-weight: bold;
}
</style>

<script type="text/javaScript">
  
</script>
</head>

<body>                      



  <form action="${ctx }/areaStat/GzPopulationAction.a?serch" method="post">
		<div class="dangqianwz">
		 	<span class="fl">当前位置：数据填写总体情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		             <span >栏目：
		     <select name="selectNumber" class=" wenziliebiao100">
		               <option value="1" onclick="document.getElementById('xin').scrollIntoView();">新学期伊始的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="2" onclick="document.getElementById('xue').scrollIntoView();">学期结束时的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="3" onclick="document.getElementById('si').scrollIntoView();">思想道德&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="4" onclick="document.getElementById('he').scrollIntoView();">合作与交流&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="6" onclick="document.getElementById('shen').scrollIntoView();">审美与表现&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="5"onclick="document.getElementById('yun').scrollIntoView();">运动与健康&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="9"onclick="document.getElementById('zong').scrollIntoView();">综合实践活动&nbsp;&nbsp;&nbsp;&nbsp;</option>
                       <option value="8" onclick="document.getElementById('xue1').scrollIntoView();">学业成就&nbsp;&nbsp;&nbsp;&nbsp;</option>
		             <!--   <option value="9">个性发展&nbsp;&nbsp;&nbsp;&nbsp;</option> -->
		            </select> </span>
		            
		             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span >学期：
		            <select  name="termId" id="termId" class="  wenziliebiao100">
	                 	<app:newTermId termId="${termId}"/> 
	               </select>
	               </span >
		            
		              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <span ><input type="submit" value="查 询" class="button ml10" /></span>&nbsp;&nbsp;&nbsp;
		            <span ><input type="button" value="导 出" class="button ml10" /></span>
		</div>

</form>


<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
   <tr class="tishi">
    <td colspan="22">
      <div class="tishi_left fl">数据填写总体情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="150" rowspan="1">栏目</td>
     <td width="80" rowspan="1">二级栏目</td>
     <td width="80" rowspan="1">填写人</td>
     <td width="80" rowspan="1">完成情况</td>
     <td rowspan="1">${str1s}届</td>
     <td rowspan="1">${str2s}届</td>
     <td rowspan="1">${str3s}届</td>
     </tr>
  

  
  <c:forEach items="${TwoColumnsDtoList}" var="stud"  varStatus="statused">
 
 
 <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "1"}'>
 <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused3"> 
  <c:if test='${ statused3.index eq "0"}'>
	 <c:if test='${stud.twoColumns_11 eq "1010"}'>
	  	<tr>
	    	<td rowspan="12" id="xin">新学期伊始的我</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "1010"}'>
		    <td rowspan="4">刚开学时的我</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "1070"}'>
		    <td rowspan="4">我的发展目标</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "1080"}'>
		    <td rowspan="4">家长的期望</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
   <td>学生总人数</td>
   <td>${cont9s}</td>
   <td>${cont8s}</td>
   <td>${cont7s}</td>
   </tr>
  </c:if>
     <c:if test='${stud1.key eq "contedsunm"}'>
			  <tr>
		    	<td>已完成学生数</td>
			    <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			   </tr>
     </c:if>
	    <c:if test='${stud1.key eq "contedpic"}'>
			  <tr>
		    	<td>完成百分比（%）</td>
			    <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
	 </c:if>
	    <c:if test='${stud1.key eq "psunmdai"}'> 
			  <tr>
		    	<td>已填写条目数</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
	     </c:if>
	     
	     <c:if test='${stud1.key eq "psunm"}'> 
			  <tr>
		    	<td>已填写条目数</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
	     </c:if>
    </c:forEach>
  </c:if>
 <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "2"}'>
  <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused3"> 
   <c:if test='${ statused3.index eq "0"}'>
	 <c:if test='${stud.twoColumns_11 eq "2010"}'>
	  	<tr>
	    	  <td rowspan="16" id="xue">学期结束的我</td>
	    	  <td rowspan="4">学期末的我</td>
		     <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
 
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "2020"}'>
		    <td rowspan="4">我的发展目标</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "2030"}'>
		    <td rowspan="4">班主任评语</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
	  <c:if test='${stud.twoColumns_11 eq "2040"}'>
		    <td rowspan="4">家长评语和期望</td>
		    <td rowspan="4">学生本人</td>
	   </c:if>
  </c:if>
  <c:if test='${ statused3.index eq "0"}'>
   <td>学生总人数</td>
   <td>${cont9s}</td>
   <td>${cont8s}</td>
   <td>${cont7s}</td>
   </tr>
  </c:if>
     <c:if test='${stud1.key eq "contedsunm"}'>
			  <tr>
		    	<td>已完成学生数</td>
			      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
	</c:if>
	    <c:if test='${stud1.key eq "contedpic"}'>
			  <tr>
		    	<td>完成百分比（%）</td>
			      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
		 </c:if>
	   
	    <c:if test='${stud1.key eq "psunm"}'> 
			  <tr>
		    	<td>已填写条目数</td>
			      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     </tr>
	     </c:if>
    </c:forEach>
  </c:if>
  
  
  
  
<%--     <c:if test='${stud.twoColumns_11 ne "21" && stud.twoColumns_11 ne "22" && stud.twoColumns_11 ne "23"  && stud.twoColumns_11 ne "11" && stud.twoColumns_11 ne "12"  }'> --%>
  
  <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) ne "9" && fn:substring(stud.twoColumns_11 ,0, 1) ne "2" && fn:substring(stud.twoColumns_11 ,0, 1) ne "1" }'>
  <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused9"> 
    <c:if test='${statused9.index eq "0" }'> 
		      <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "1"}'> 
			        <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "3"}'>
			     	 <td rowspan="21" id="si">思想道德</td>
			      </c:if>
			        <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "8"}'>
			     	 <td rowspan="25" id="xue1">学业成绩</td>
			      </c:if>
			      <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "4"}'>
			      <td rowspan="21" id="he">合作与交流</td>
			      </c:if>
			      <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "6"}'>
			      <td rowspan="21" id="shen">审美与表现</td>
			      </c:if>
			      <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "5"}'>
			      <td rowspan="20" id="yun">运动与健康</td>
			      </c:if>
			      <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "7"}'>
			      <td rowspan="21" id="yun">个性发展</td>
			      </c:if>
			      <td rowspan="4">自我评价</td>
			      <td rowspan="4">学生本人</td>
		      </c:if> 
	 </c:if>
	 <c:if test='${statused9.index eq "0" }'> 
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "7"}'>
	      <td rowspan="12">他人评价</td>
	      <td rowspan="4">同学</td>
	     </c:if>
     </c:if>
     <c:if test='${statused9.index eq "0" }'> 
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "8"}'>
	      <td rowspan="4">教师</td>
	     </c:if>
     </c:if>
     <c:if test='${statused9.index eq "0" }'> 
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "9"}'>
	      <td rowspan="4">家长</td>
	     </c:if>
     </c:if>
     <c:if test='${statused9.index eq "0" }'> 
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "3"}'>
	       <td rowspan="5">记录袋</td>
           <td rowspan="5">学生本人</td>
	     </c:if>
     </c:if>
     <c:if test='${statused9.index eq "0" }'> 
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "4"}'>
	       <td rowspan="4">体质健康</td>
           <td rowspan="4">学生本人</td>
	     </c:if>
     </c:if>
     <c:if test='${statused9.index eq "0" }'>  
      
        <c:if test='${fn:substring(stud.twoColumns_11 ,2, 3)eq "5"}'>
	 
	      <td rowspan="4">课程评语</td>
            <td rowspan="4">学生本人</td>
	     </c:if>
     </c:if>
     
      <c:if test='${statused9.index eq "0" }'> 
	      <td>学生总人数</td>
	      <td>${cont9s}</td>
		   <td>${cont8s}</td>
		   <td>${cont7s}</td>
	    </tr>
	  	</c:if>
	  <c:if test='${stud1.key eq "contedsunm"}'>
				  <tr>
			    	<td>已完成学生数</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			   	</c:if>
		  <c:if test='${stud1.key eq "contedpic"}'>
		     
				  <tr>
			    	<td>完成百分比（%）</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			 </c:if> 

		    <c:if test='${stud1.key eq "psunm"}'> 
				  <tr>
			    	<td>已填写条目数</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			</c:if> 
		   <c:if test='${fn:substring(stud.twoColumns_11 ,2,3)eq "3"}'> 
		    <c:if test='${stud1.key eq "psunmdai"}'> 
				  <tr>
			    	<td>附件</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			</c:if> 
		</c:if> 
  </c:forEach>
  </c:if>
  
 
   <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "9" }'> 
      <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused11"> 
	     <c:if test='${statused11.index eq "0" }'> 
           <c:if test='${stud.twoColumns_11 eq "9010" }'>
		         <tr>
			      <td rowspan="15" id="zong">综合实践活动</td>
			      <td rowspan="5">研究性学习</td>
			      <td rowspan="5">学生本人</td>
	    	 </c:if> 
	    
	    <c:if test="${stud.twoColumns_11  eq '9030'}">
		    <tr>
		      <td rowspan="5">社会实践活动</td>
		      <td rowspan="5">学生本人</td>
      	</c:if>
	    
	    <c:if test="${stud.twoColumns_11 eq '9020'}">
		      <tr>
		      <td rowspan="5">社区服务</td>
		      <td rowspan="5">学生本人</td>
	   </c:if>
	      	 </c:if>
     <c:if test='${statused11.index eq "0" }'> 
	      <td>学生总人数</td>
	      <td>${cont9s}</td>
   <td>${cont8s}</td>
   <td>${cont7s}</td>
	    </tr>
	</c:if>  
	 <c:if test='${stud1.key eq "contedsunm"}'>
				  <tr>
			    	<td>已完成学生数</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
	  	</c:if> 
		  <c:if test='${stud1.key eq "contedpic"}'>
				  <tr>
			    	<td>完成百分比（%）</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			 </c:if> 

		    <c:if test='${stud1.key eq "psunm"}'> 
				  <tr>
			    	<td>已填写条目数</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
			</c:if>  
	    <c:if test='${stud1.key eq "psunmdai"}'> 
				  <tr>
			    	<td>附件</td>
				      <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
				     </tr>
	 		</c:if> 
     </c:forEach>
    </c:if>
</c:forEach>
</table>
</div>
</div>


</body>
</html>
