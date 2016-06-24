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
  <form action="${ctx }/areaStat/PopulationAction.a?serch" method="post">
		<div class="dangqianwz" style="position:fixed; top:0; left: 0;width:100%; height: 30px; ">
		 	<span class="fl">当前位置：数据填写总体情况</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		             <span >栏目：
		     <select name="selectNumber" class=" wenziliebiao100">
		               <option value="1" onclick="document.getElementById('xin').scrollIntoView();">新学期伊始的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="2" onclick="document.getElementById('xue').scrollIntoView();">学期结束时的我&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="3" onclick="document.getElementById('si').scrollIntoView();">思想道德&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="4" onclick="document.getElementById('xue1').scrollIntoView();">学业成就&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="5" onclick="document.getElementById('he').scrollIntoView();">合作与交流&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="6" onclick="document.getElementById('yun').scrollIntoView();">运动与健康&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="7" onclick="document.getElementById('shen').scrollIntoView();">审美与表现&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="8" onclick="document.getElementById('zong').scrollIntoView();">综合实践活动&nbsp;&nbsp;&nbsp;&nbsp;</option>
		               <option value="9" onclick="document.getElementById('ge').scrollIntoView();">个性发展&nbsp;&nbsp;&nbsp;&nbsp;</option>
		            </select> </span>
		             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span >学期：
		            <select  name="termId" id="termId" class="  wenziliebiao100">
	                 	<app:newTermId termId="${termId}"/> </span >
	               </select>
		            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <span ><input type="submit" value="查 询" class="button ml10" /></span>&nbsp;&nbsp;&nbsp;
		            <span ><input type="button" value="导 出" class="button ml10" /></span>
		</div>

</form>
<div id="tjb_main">
  <div class="top">
  <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"  >
   <tr class="tishi">
    <td colspan="22">
      <div class="tishi_left fl">数据填写总体情况
        </div>
    </td>
    </tr>
   <tr class="biaotou_zi">
     <td width="150" >栏目</td>
     <td width="80" >二级栏目</td>
     <td width="80" >填写人</td>
     <td width="80" >完成情况</td>
     <td >${str1}届</td>
     <td >${str2}届</td>
     <td >${str3}届</td>
     <c:if test='${ length eq "4"}'>
     <td >${str4}届</td>
     </c:if>
     </tr>
  
<c:forEach items="${TwoColumnsDtoList}" var="stud"  varStatus="statused">
<c:if test='${stud.twoColumns_11 eq "11"}'>
  <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused1"> 
	<c:if test='${statused1.index eq "0" }'> 
	 <tr>
	    <td rowspan="8" id="xin">新学期伊始的我</td>
	    <td rowspan="4">刚开学时的我</td>
	    <td rowspan="4">学生本人</td>
	  <td>学生总人数</td>
	   <td>${cont9s}</td>
	   <td>${cont8s}</td>
	   <td>${cont7s}</td>
	   <c:if test='${ length eq "4"}'>
	   <td>${cont6s}</td>
	   </c:if>
	   </tr>
	  </c:if>
		 <c:if test='${stud1.key eq "contedsunm"}'>
		  
			  <tr>
		    	<td>已完成学生数</td>
			    <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td>
			    </c:if>
			     </tr>
		   	</c:if>
	    <c:if test='${stud1.key eq "contedpic"}'>
	     
			  <tr>
		    	<td>完成百分比（%）</td>
			    <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
			     </tr>
		 </c:if>
	   
	    <c:if test='${stud1.key eq "psunm"}'> 
			  <tr>
		    	<td>已填写条目数</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
			     </tr>
	     </c:if>
    </c:forEach> 
 </c:if> 
 
 <c:if test='${stud.twoColumns_11 eq "12"}'>
 <c:forEach items="${stud.sList}" var="stud1"  varStatus="statused1"> 
	<c:if test='${statused1.index eq "0" }'> 
  <tr>
    <td rowspan="4">我的发展目标</td>
    <td rowspan="4">学生本人</td>
    <td>学生总人数</td>
	 <td>${cont9s}</td>
	   <td>${cont8s}</td>
	   <td>${cont7s}</td>
	   <c:if test='${ length eq "4"}'>
	   <td>${cont6s}</td>
	   </c:if>
    </tr>
    </c:if>
    <c:if test='${stud1.key eq "contedsunm"}'>
			  <tr>
		    	<td>已完成学生数</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
			     </tr>
		   	</c:if>
	    <c:if test='${stud1.key eq "contedpic"}'>
			  <tr>
		    	<td>完成百分比（%）</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
			     </tr>
		 </c:if>
	   
	    <c:if test='${stud1.key eq "psunm"}'> 
			  <tr>
		    	<td>已填写条目数</td>
			     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
			     </tr>
	     </c:if>
    </c:forEach>
   </c:if>  
  
  
   <c:if test='${stud.twoColumns_11 eq "21" || stud.twoColumns_11 eq "22" || stud.twoColumns_11 eq "23"}'>
	   <c:forEach items="${stud.sList}" var="stud3"  varStatus="statused3"> 
		   <c:if test='${statused3.index eq "0" }'>  
		   		<c:if test='${stud.twoColumns_11 eq "21"}'>
		   			<td rowspan="12" id="xue">学期结束时的我</td>
		        </c:if>
		    </c:if>
		    <c:if test='${stud.twoColumns_11 eq "21"}'>
			   <c:if test='${statused3.index eq "0" }'> 
			    <td rowspan="4">学期末的我</td>
			    <td rowspan="4">学生本人</td>
		    </c:if>
		    </c:if>
		    
		    <c:if test='${stud.twoColumns_11 eq "22"}'>
			    <c:if test='${statused3.index eq "0" }'> 
				    <td rowspan="4">班主任评语</td>
				    <td rowspan="4">班主任</td>
			    </c:if>
		    </c:if>
		    <c:if test='${stud.twoColumns_11 eq "23"}'>
			    <c:if test='${statused3.index eq "0" }'> 
				    <td rowspan="4">家长评语和期望</td>
				    <td rowspan="4">家长</td>
			    </c:if>
		    </c:if>
		   
		   <c:if test='${statused3.index eq "0" }'> 
		    <td>学生总人数</td>
		    <td>${cont9s}</td>
		   <td>${cont8s}</td>
		   <td>${cont7s}</td>
		   <c:if test='${ length eq "4"}'>
		   <td>${cont6s}</td>
		   </c:if>
		    </tr>
		    </c:if> 
		   <c:if test='${stud3.key eq "contedsunm"}'>
					  <tr>
				    	<td>已完成学生数</td>
					     <td>${stud3.value[0]}</td>
			    <td>${stud3.value[1]}</td>
			    <td>${stud3.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud3.value[3]}</td></c:if>
					     </tr>
		</c:if>
				 
			
			  <c:if test='${stud3.key eq "contedpic"}'>
					  <tr>
				    	<td>完成百分比（%）</td>
					     <td>${stud3.value[0]}</td>
			    <td>${stud3.value[1]}</td>
			    <td>${stud3.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud3.value[3]}</td></c:if>
					     </tr>
				 </c:if> 
			   
			    <c:if test='${stud3.key eq "psunm"}'> 
					  <tr>
				    	<td>已填写条目数</td>
					     <td>${stud3.value[0]}</td>
			    <td>${stud3.value[1]}</td>
			    <td>${stud3.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud3.value[3]}</td></c:if>
					     </tr>
				</c:if>
  	</c:forEach>
  </c:if>
     
     
   <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) ne "8"}'> 
  <c:if test='${stud.twoColumns_11 ne "21" && stud.twoColumns_11 ne "22" && stud.twoColumns_11 ne "23"  && stud.twoColumns_11 ne "11" && stud.twoColumns_11 ne "12"  }'>
   	<c:forEach items="${stud.sList}" var="stud1"  varStatus="statused9"> 
	       <c:if test='${statused9.index eq "0" }'> 
		     <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "1"}'>
		     	<tr>   
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "3"}'>
		      		<td rowspan="21" id="si">思想道德</td>
	     	        </c:if>
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "4"}'>
		      		<td rowspan="21" id="xue1">学业成就</td>
	     	        </c:if>
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "5"}'>
		      		<td rowspan="21" id="he">合作与交流</td>
	     	        </c:if>
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "6"}'>
		      		<td rowspan="20" id="yun">运动与健康</td>
	     	        </c:if>
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "7"}'>
		      		<td rowspan="21" id="shen">审美与表现</td>
	     	        </c:if>
		      		<c:if test='${fn:substring(stud.twoColumns_11 ,0, 1)eq "9"}'>
		      		<td rowspan="20" id="ge">个性发展</td>
	     	        </c:if>
	     	 </c:if>
	      </c:if>
	      
	     <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "1"}'>
	       	<c:if test='${statused9.index eq "0" }'> 
	       		<td rowspan="4">自我评价</td>
	    	 </c:if>
	     </c:if>
	     <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "2"}'>
	       <c:if test='${statused9.index eq "0" }'> 
	       		 <td rowspan="12">他人评价</td>
	     	</c:if>
	     </c:if>
	      
	      <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "5"}'>
	         <c:if test='${stud.twoColumns_11 ne "65" && stud.twoColumns_11 ne "95"}'>
		         <c:if test='${statused9.index eq "0" }'> 
		         	 <td rowspan="5">记录袋</td>
		         </c:if>
	         </c:if>
	         <c:if test='${stud.twoColumns_11 eq "65"}'>
		         <c:if test='${statused9.index eq "0" }'> 
		         	 <td rowspan="4">体质与健康</td>
		         </c:if>
	         </c:if>
	         <c:if test='${stud.twoColumns_11 eq "95"}'>
		         <c:if test='${statused9.index eq "0" }'> 
		         	 <td rowspan="5">特长与成果展示</td>
		         </c:if>
	         </c:if>
	        
	      </c:if>
	      <c:if test='${statused9.index eq "0" }'> 
	      <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "1"}'>
	           <td rowspan="4">学生本人</td>
	      </c:if>
	      <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "2"}'>
	           <td rowspan="4">同学</td>
	      </c:if>
	      <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "3"}'>
	           <td rowspan="4">老师</td>
	      </c:if>
	      <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "4"}'>
	           <td rowspan="4">家长</td>
	      </c:if>
	    
	     <c:if test='${fn:substring(stud.twoColumns_11 ,1, 2)eq "5"}'>
	           <c:if test='${stud.twoColumns_11 ne "65"}'>
		         <c:if test='${statused9.index eq "0" }'> 
		         	 <td rowspan="5">学生本人</td>
		         </c:if>
	         </c:if>
	         <c:if test='${stud.twoColumns_11 eq "65"}'>
		         <c:if test='${statused9.index eq "0" }'> 
		         	 <td rowspan="4">学生本人</td>
		         </c:if>
	         </c:if>
	           
	           
	      </c:if>
	      </c:if>
	      <c:if test='${statused9.index eq "0" }'> 
	      <td>学生总人数</td>
	      <td>${cont9s}</td>
		   <td>${cont8s}</td>
		   <td>${cont7s}</td>
		   <c:if test='${ length eq "4"}'>
		   <td>${cont6s}</td>
		   </c:if>
	    </tr>
	  	</c:if>
	  <c:if test='${stud1.key eq "contedsunm"}'>
	           
				  <tr>
			    	<td>已完成学生数</td>
				   <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
				     </tr>
			   	</c:if>
			  
			 
		
		  <c:if test='${stud1.key eq "contedpic"}'>
				  <tr>
			    	<td>完成百分比（%）</td>
				     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
				     </tr>
			 </c:if> 
		   
		    <c:if test='${stud1.key eq "psunm"}'> 
				  <tr>
			    	<td>已填写条目数</td>
				     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
				     </tr>
		</c:if> 
		    <c:if test='${stud1.key eq "psunmdai"}'> 
				  <tr>
			    	<td>附件</td>
				     <td>${stud1.value[0]}</td>
			    <td>${stud1.value[1]}</td>
			    <td>${stud1.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud1.value[3]}</td></c:if>
				     </tr>
		</c:if> 

</c:forEach>
</c:if>
</c:if>  



 <c:if test='${fn:substring(stud.twoColumns_11 ,0, 1) eq "8"}'> 
     <c:forEach items="${stud.sList}" var="stud4"  varStatus="statused10"> 
      <c:if test='${statused10.index eq "0" }'> 
      <c:if test='${stud.twoColumns_11 eq "81"}'>
      <td rowspan="20" id="zong">综合实践活动</td>
     	 <td rowspan="4">研究性学习基本情况</td>
         <td rowspan="4">学生本人</td>
      </c:if>
      
      <c:if test='${stud.twoColumns_11 eq "82"}'>
     	 <td rowspan="4">研究性学习研究成果</td>
         <td rowspan="4">学生本人</td>
      </c:if>
      
      <c:if test='${stud.twoColumns_11 eq "83"}'>
     	 <td rowspan="4">研究性学习自我评价</td>
         <td rowspan="4">学生本人</td>
      </c:if>
      
      <c:if test='${stud.twoColumns_11 eq "84"}'>
     	 <td rowspan="4">社区服务与实践活动基本情况</td>
         <td rowspan="4">学生本人</td>
      </c:if>
      
      <c:if test='${stud.twoColumns_11 eq "85"}'>
     	 <td rowspan="4">社区服务与实践活动自我评价</td>
         <td rowspan="4">学生本人</td>
      </c:if>
     </c:if>
      
     
     <!--statused10可能有问题  -->
      <c:if test='${statused10.index eq "0" }'> 
	      <td>学生总人数</td>
	      <td>${cont9s}</td>
	   <td>${cont8s}</td>
	   <td>${cont7s}</td>
	   <c:if test='${ length eq "4"}'>
	   <td>${cont6s}</td>
	   </c:if>
	    </tr>
	  	</c:if>
	 
	  <c:if test='${stud4.key eq "contedsunm"}'>
				  <tr>
			    	<td>已完成学生数</td>
				   <td>${stud4.value[0]}</td>
			    <td>${stud4.value[1]}</td>
			    <td>${stud4.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud4.value[3]}</td></c:if>
				     </tr>
			     </c:if> 
		  <c:if test='${stud4.key eq "contedpic"}'>
				  <tr>
			    	<td>完成百分比（%）</td>
				    <td>${stud4.value[0]}</td>
			    <td>${stud4.value[1]}</td>
			    <td>${stud4.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud4.value[3]}</td></c:if>
				     </tr>
			 </c:if> 
		   
		    <c:if test='${stud4.key eq "psunm"}'> 
				  <tr>
			    	<td>已填写条目数</td>
				     <td>${stud4.value[0]}</td>
			    <td>${stud4.value[1]}</td>
			    <td>${stud4.value[2]}</td>
			     <c:if test='${ length eq "4"}'>
			    <td>${stud4.value[3]}</td></c:if>
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
