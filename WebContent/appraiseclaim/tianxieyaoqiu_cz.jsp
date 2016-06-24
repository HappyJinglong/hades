<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>



<title>自我评价</title>
<script type="text/javascript">
	 function select(){
		
		var typeId = document.getElementById("select").value;
//		alert(typeId);
		window.location.hash=typeId;
	} 
</script>
</head>

<body style="background-color: #EEE">
<div class="dangqianwz" style="width: 97.3%;padding-bottom: 0px;padding-right: 0px;">
 	<span class="fl">当前位置：评价填写要求</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
 <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge"  style="text-align: center;margin-top: 15px;text-shadow:0px 0px 0px #3E3E3E">
	           <tr class="title_bg">
	             <td colspan="5" style="text-shadow: 0px 0px 0px #279F46;color: #FFF;font-size: 16px;background:#279F46">评价填写要求</td>
	           </tr>
			    
			    <tr>
			      <td class="th" width="15%" >一级栏目</td>
			      <td  colspan="2" class="th"   width="30%">二级栏目</td>
			      <td  class="th"  width="15%">评价次数</td>
			      <td class="th" >完成日期</td>
	          </tr>
        </table>
 </div>
<div id="pj_ziwo_main" style="top: 76px;overflow-x:hidden;margin-bottom: 20px;width: 98.5%">
 <div class="top">
      <table width="100%" border="0" cellspacing="1" bgcolor="#999999" class="biaoge">
		    <tr>
		      <td height="191" rowspan="2" width="15%"  class="h50 th"></a>新学期伊始的我</td>
		      <td colspan="2" class="h50 th"  width="30%">自我评价<span class="red">*</span><a id="1001"></a></td>
		      <td rowspan="2" class="h50" width="15%" ><span class="fl"> </span>1/学期</td>
		      <td rowspan="2" class="h50"><span class=" fl">
		       
		      </span>学期开始后1个月内完成</td>
        </tr>
		    <tr>
		      <td colspan="2" class="h50 th"><a id="1002"></a>我的发展目标<span class="red">*</span></td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">学期结束时的我</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 ">1/学期</td>
		      <td rowspan="3" class="h50 ">学期结束后1周内完成</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">班主任评语<span class="red">*</span></td>
		      <td class="h50 ">1/学期</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1003"></a>家长期望和寄语<span class="red">*</span></td>
		      <td class="h50 ">1/学期</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">思想道德</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学期</td>
		      <td rowspan="5" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td rowspan="4" class="h50 ">不限</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1004"></a>教师</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">思想道德记录袋<span class="red"></span></td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">学业成就</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学期</td>
		      <td class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">课程评语<span class="red">*</span></td>
		      <td class="h50 ">1次/学生/学年</td>
		      <td class="h50 ">每年7月底前内完成</td>
        </tr>
		    <tr>
		      <td class="h50 th">同学</td>
		      <td rowspan="3" class="h50 ">不限</td>
		      <td rowspan="3" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1005"></a>家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">学科作品展示<span class="red"></span></td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">合作与交流</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td class="h50 ">不限</td>
		      <td rowspan="5" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学<span class="red">*</span></td>
		      <td class="h50 ">不少于3人/学期</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1006"></a>教师</td>
		      <td rowspan="3" class="h50 ">不限</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">合作与交流行为记录袋<span class="red"></span></td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">运动与健康</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td rowspan="4" class="h50 ">不限</td>
		      <td rowspan="4" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
        </tr>
		    <tr>
		      <td class="h50 th"><a id="1007"></a>教师</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">体质健康<span class="red"></span></td>
		      <td class="h50 ">1/学年</td>
		      <td class="h50 ">第1学期结束后1周内完成</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">审美与表现</td>
		      <td height="191" colspan="2" class="h50 th">自我评价</td>
		      <td rowspan="4" class="h50 ">不限</td>
		      <td rowspan="5" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th"><a id="1008"></a>审美与表现记录袋<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学期</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th">综合实践活动</td>
		      <td rowspan="3" class="h50 th">研究性学习</td>
		      <td height="191" class="h50 th">基本情况<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学年</td>
		      <td rowspan="5" class="h50 ">每学年7月底完成</td>
        </tr>
		    <tr>
		      <td class="h50 th">研究成果</td>
		      <td rowspan="4" class="h50 ">不限</td>
        </tr>
		    <tr>
		      <td class="h50 th">自我评价</td>
        </tr>
		    <tr>
		      <td height="191" rowspan="2" class="h50 th">社区服务与社会实践<span class="red"></span></td>
		      <td height="191" class="h50 th">基本情况</td>
        </tr>
		    <tr>
		      <td height="95" class="h50 th">自我评价</td>
        </tr>
		    <tr>
		      <td rowspan="5" class="h50 th"><a id="1009"></a>个性发展</td>
		      <td height="191" colspan="2" class="h50 th">自我评价<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学期</td>
		      <td rowspan="5" class="h50 ">第1学期在11月底前完成，第2学期在5月底前完成</td>
        </tr>
		    <tr>
		      <td rowspan="3" class="h50 th">他人评价</td>
		      <td height="191" class="h50 th">同学</td>
		      <td rowspan="3" class="h50 ">不限</td>
        </tr>
		    <tr>
		      <td class="h50 th">教师</td>
        </tr>
		    <tr>
		      <td class="h50 th">家长</td>
        </tr>
		    <tr>
		      <td height="191" colspan="2" class="h50 th">特长与成果展示<span class="red">*</span></td>
		      <td class="h50 ">&gt;=1/学期</td>
        </tr>
          
      </table>
    </div>
  

</div>


</body>

