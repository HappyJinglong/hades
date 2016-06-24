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
function fanhui(){
javascript:history.go(-1)
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