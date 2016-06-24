<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

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
<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
<script src="${ctx}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${ctx}/js/func.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<title>自我评价</title>
	<style>
		.table-head{
			position: fixed;
		    width: 100%;
		    top: 0;
		    z-index: 1;
		    background: #eee;
		    padding-top: 10px;
		}
		.table-content{
			position: absolute;
		    background: #eee;
		    width: 100%;
		    top: 76px;
		    margin-bottom: 10px;
		}
		.table-head table, .table-content table{
			margin: auto;
			border-collapse: collapse;
			font-family: '';
		}
		.table-head table caption{
			border: solid 1px #666;
		    border-bottom: none;
		    height: 30px;
		    line-height: 30px;
		    text-align: left;
		    padding-left: 20px;
		}
		.table-head table .th{
			text-align: center;
			height: 30px;
		}
		.table-content table .th{
			text-align: center;
		}
		.table-head table td, .table-content table td{
			border: solid 1px #666;
			height: auto;
			padding: 2px;
		}
		.table-content>table table{
			margin: 2px auto;
		}
		.table-content>table table td{
			background-color: #fff;
		}
		input.pager {
		    font-size: 14px;
		    width: 26px;
		    border: solid 1px #666;
		    font-family: '';
		}
	</style>
	<script type="text/javascript">
		function doPager(step){
			window.parent.submitdata(Number('${param.index}') + step);
		}
	</script>
</head>

<body style="background-color: #fff;">
	<div class="table-head" id="dangqianwz_gz_master">
       <table cellpadding="0" cellspacing="0" style="width: 1216px;">
       		<caption>当前学生：${studentName}
       		<div style="display: inline-block;float: right;padding: 5px 0;margin-right: 5px;">
       		<c:if test="${total gt 0}"><c:if test="${index gt 1}"><input type="button" title="上一个学生" onclick="doPager(-1);" class="pager" value="◁"/></c:if><c:if test="${index lt total}"><c:if test="${index gt 0}"><input type="button" class="pager" title="下一个学生" onclick="doPager(1);" style="margin-left: -1px;" value="▷"/></c:if></c:if></c:if>
       		</div>
       		</caption>
		    <tr>
		      <td class="th" width="200px" style="font-size: 13px;color: #3E3E3E;">一级栏目</td>
		      <td width="300px" colspan="2" class="th"  style="font-size: 13px;color: #3E3E3E;">二级栏目</td>
		      <td  class="th" style="width: 700px;font-size: 13px;color: #3E3E3E;">内容</td>
        	</tr>
		</table>
	</div>
	<div class="table-content">
      <table cellpadding="0" cellspacing="0" style="width: 1216px;">
		    <tr><a id="1001"></a>
		      <td width="200px" rowspan="3" class="cell th">新学期伊始的我</td>
		      <td colspan="2" width="300px" class="cell th">自我评价</td>
		      <td class="cell" style="width: 700px;background-color: rgb(238, 238, 238);">
				<c:forEach items="${appraiseMaps[START_ZWPJ]}" var="item">
			  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
							<tr>
								<td  style="padding-right: 6px">
								<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
								</td>
							</tr>
			  		</table>
				</c:forEach>
			  </td>
			</tr>
		    <tr>
		      <td colspan="2" class="cell th"><a id="1002"></a>我的发展目标</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[START_WDFZMB]}" var="item">
					 <c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
									<tr>
										<td  style="padding-right: 6px"> 
										<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
										</td>
									</tr>
					  		</table>
				  		</c:if>
					</c:forEach>
			</td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th">家长的期望</td>
		      <td class="cell" style="background-color:rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[START_WDFZMB]}" var="item">
					  <c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  			<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  			</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="4" class="cell th">学期结束时的我</td>
		      <td height="95" colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color:rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_ZWPJ]}" var="item">
						  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
						  </table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="cell th">我的发展目标</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_WDFZMB]}" var="item">
						  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
										<tr>
											<td  style="padding-right: 6px">
											<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
											</td>
										</tr>
						  </table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th">班主任评语</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_BZRPY]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th"><a id="1003"></a>家长评语和期望</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[END_JZPYQW]}" var="item">
					  	<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
									<tr>
										<td  style="padding-right: 6px">
										<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
										</td>
									</tr>
					  	</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="cell th">思想道德</td>
		      <td colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
				<c:forEach items="${appraiseMaps[SX_ZWPJ]}" var="item">
		      		<c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
					  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">同学</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">教师</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,TEACHER) or fn:contains(item.appraseridentify,MASTER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        	</tr>
		    <tr>
		      <td class="cell th">家长</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[SX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th"><a id="1004"></a>思想道德记录袋<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SXJLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: #fff;">思想道德记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td style="background:#fff;">内容描述：</td>
					  <td  style="padding-right: 6px">${item.apprasial}</td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx }${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="6" class="cell th">学业成就</td>
		      <td height="47" colspan="2" class="cell th">学科作品展示</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[XY_GCJL]}" var="item" varStatus="pVs">
			      <c:if test="${fn:contains(item.appraseridentify,me_apprasialidentify)}">
					  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
					  
					  	<tr >
				  			<td colspan="2" align="left" style="background-color: #fff;">学科学习过程记录${pVs.count}</td>
				  		</tr>
					  
					  <c:if test="${item.item1 == '思想政治'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">思想品德</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '语文'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">语文</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '数学'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">数学</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '英语'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">英语</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '历史'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">历史</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '地理'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">地理</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '物理'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">物理</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '化学'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">化学</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '生物'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">生物</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '体育与健康'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">体育与健康</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '音乐'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">音乐</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '美术'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">美术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '劳动技术'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">劳动技术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <c:if test="${item.item1 == '信息技术'}">
						  <tr>
							  <td style="background:#fff;">科目</td>
							  <td style="background:#fff;">
								  <span style="float:left">信息技术</span>
							  </td>
						  </tr>
					  </c:if>
					  
					  <tr>
					     <td style="background:#fff;">作品信息</td>
						  <td  style="padding-right: 6px">${item.apprasial}</td>
					  </tr>
					  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
						  <tr>
						      <td style="background:#fff;">
							      附件${state.count}：
							  </td>
							  <td style="background:#fff;">
							     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
							  </td>
						  </tr>
					  </c:forEach>
					  <tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
							</td>
					  </tr>
					  </table>
					  </c:if>
			 	</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="cell th">课程评语</td>
		       <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[KE_CHENG_PINGYU]}" var="item" varStatus="i">
					<c:if test="${fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
						  	<tr> <td style="background:#fff;text-align: left;" colspan="2" >课程评语${i.count }</td></tr>
						  	 <tr>
								  <td style="background:#fff;">科目</td>
								  <td style="background:#fff;">
									  <span style="float:left">${item.item1}</span>
								  </td>
							  </tr>
								<tr>
								   <td style="background:#fff;">内容描述:</td>
									<td  style="padding-right: 6px" >
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="47" colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">教师<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">同学</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th"><a id="1005"></a>家长</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[XY]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="cell th">合作与交流</td>
		      <td colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
					<c:forEach items="${appraiseMaps[HZ_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
					</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">同学</td>
		       <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">教师</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">家长</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[HZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th"><a id="1006"></a>合作与交流行为记录袋<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[HZ_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: #fff;">合作与交流行为记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td style="background:#fff;">内容描述：</td>
					  <td  style="padding-right: 6px">${item.apprasial}</td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="5" class="cell th">运动与健康</td>
		      <td colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">同学</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>																																																																																																																																																																																																																																											
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">教师</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
        
		    <tr>
		      <td height="47" class="cell th"><a id="1007"></a>家长</td>
		     <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[YDJK_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
        
        
        	<tr>
		      <td colspan="2" class="cell th">体质健康<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      
		      
			 	<c:if test="${not empty appraiseMaps[YDJKTZJK]}">
        		<table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#fff" >
        		
				<tbody>
				      
				        <tr>
				            <td>指标（项目）</td>
				            <td>成绩</td>
				            <td>得分</td>
				            <td>等级</td>
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
				                    ${item.itemMarkStandardWeight }
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
				                    ${item.itemMarkWeight }
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
		      <td rowspan="5" class="cell th">审美与表现</td>
		      <td colspan="2" class="cell th">自我评价</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">同学</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">教师</td>
		       <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">家长</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[SMYBX_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td colspan="2" class="cell th">审美与表现记录袋</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SMYBX_JLD]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  
				  <tr  >
				  	<td colspan="2" align="left" style="background-color: #fff;">审美与表现记录袋${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td style="background:#fff;">内容描述：</td>
					  <td  style="padding-right: 6px">${item.apprasial}</td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
						</td>
				  </tr>
				  </table>
			  </c:forEach><a id="1008"></a>
			  </td>
        </tr>
        
		    <tr>
		      <td rowspan="3" class="cell th">综合实践活动</td>
		      <td colspan="2" rowspan="1" class="cell th">研究性学习</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[YJXX]}" var="item" varStatus="pVs">
			  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
			  
			  	<tr >
				  <td colspan="2" align="left" style="background-color: #fff;">研究性学习${pVs.count}</td>
				</tr>
				
			  <tr>
				 <td style="background:#fff;"> 题目：</td>
				 <td >${item.item1}</td>
			  </tr>
			  <tr>
				 <td style="background:#fff;"> 合伙人：</td>
				 <td>${item.item2}</td>
			  </tr>
			  <tr>
				 <td style="background:#fff;"> 总学时数：</td>
				 <td>${item.item3}</td>
			  </tr>
			  <tr>
				 <td style="background:#fff;"> 实施路径：</td>
				 <td>${item.item4}</td>
			  </tr>
			  <tr>
			      <td style="background:#fff;">
				      内容摘要：
				  </td>
				 <td style="padding-right: 6px">${item.apprasial }</td>
			  </tr>
			  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   	<tr>
				     <td style="background:#fff;" >
					      自我评价：
					 </td>
					 <td width="100%" style="background:#fff;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2" varStatus="i">
					  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  <tr>
					 <td  style="padding-right: 6px">${item2.myselfapprasercontent}</td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
				<tr>
				 <td width="100%" style="background:#fff;" colspan="2">
					<div style="text-align: right;">
						<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
						<span style="margin-right: 20px;">签名：${item.appraser}</span>
						<span style="margin-right: 20px;">日期：${item.signdate}</span>
					</div>
				 </td>
				</tr>
			  
			  </table>
			  </c:forEach>
			  
			  </td>
        </tr>
		 
		 
		    <tr>
		      <td height="95" colspan="2" class="cell th">社区服务<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SQFU]}" var="item" varStatus="pVs">
			  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
			  
			  	<tr >
				 <td colspan="2" align="left" style="background-color: #fff;">社区服务${pVs.count}</td>
				</tr>
				
			  <tr>
				 <td width="15%" style="background:#fff;">选择次数：</td>
				 
			<c:if test="${item.isReadOnly}">${item.item1}</td>
			</c:if>	 
			<c:if test="${!item.isReadOnly}">	 
				 <td width="85%">${item.item1}</td>
			</c:if>		 
			  </tr>
			  <tr>
				 <td width="15%" style="background:#fff;">服务社区名称：</td>
				 <td width="85%">${item.item2}</td>
			  </tr>
			  <tr>
				 <td width="15%" style="background:#fff;">服务社区联系电话：</td>
				 <td width="85%">${item.item3}</td>
			  </tr>
			  <tr>
				 <td width="15%" style="background:#fff;">服务时数：</td>
				 <td width="85%">${item.item4}</td>
			  </tr>
			   <tr>
				 <td width="15%" style="background:#fff;"> 服务日期：</td>
				 <td width="85%">${item.item5}</td>
			  </tr>
			  <tr>
			      <td style="background:#fff;">
				      内容摘要：
				  </td>
				 <td  style="padding-right: 6px">${item.apprasial }</td>
			  </tr>
			  
			 
			  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   <tr>
				      <td style="background:#fff;">
					      自我评价：
					  </td>
					  <td width="100%" style="background:#fff;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2">
					  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
					<tr>
					 <td  style="padding-right: 6px">${item2.myselfapprasercontent}</td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
			  <tr>
				 <td width="100%" style="background:#fff;" colspan="2">
					<div style="text-align: right;">
						<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
						<span style="margin-right: 20px;">签名：${item.appraser}</span>
						<span style="margin-right: 20px;">日期：${item.signdate}</span>
					</div>
				 </td>
				</tr>
			  </table>
			  </c:forEach>
			  </td>
        </tr>
        
		    <tr>
		      <td height="95" colspan="2" class="cell th">社会实践活动</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[SHSJHD]}" var="item" varStatus="pVs">
			   <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
			   
			   	<tr >
				 <td colspan="2" align="left" style="background-color: #fff;">社会实践活动${pVs.count}</td>
				</tr>
			   
			  <tr>
				 <td style="background:#fff;">选择活动种类：</td>
				 <c:if test="${item.isReadOnly}">
				 <td style="background-color:rgb(238, 238, 238);">${item.item1}</td>
				 </c:if>
				 <c:if test="${!item.isReadOnly}">
				 <td>${item.item1}</td>
				 </c:if>	
			  </tr>
			  <tr>
				 <td style="background:#fff;">成果形成：</td>
				<c:if test="${item.isReadOnly}">${item.item2}</td>
				</c:if>
				<c:if test="${!item.isReadOnly}">
				 <td>${item.item2}</td>
				</c:if>
			  </tr>
			  <tr>
				 <td style="background:#fff;">地点：</td>
				 <td>${item.item4}</td>
			  </tr>
			  <tr>
				 <td style="background:#fff;">完成学时：</td>
				 <td>${item.item5}</td>
			  </tr>
			  <tr>
			      <td style="background:#fff;">
				      主体：
				  </td>
				  <td style="padding-right: 6px">${item.apprasial }</td>
			  </tr>
			   <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  
			   <tr>
				      <td style="background:#fff;">
					      自我评价：
					  </td>
					  <td width="100%" style="background:#fff;">
					  <c:forEach items="${item.practicesSelfAppraiseDtos}" var="item2">
					  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge"><tr>
					 <td  style="padding-right: 6px">${item2.myselfapprasercontent}</td>
			     </tr>
			  </table>
			  </c:forEach>
					 </td>
				</tr>
			  <tr>
				 <td width="100%" style="background:#fff;" colspan="2">
					<div style="text-align: right;">
						<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
						<span style="margin-right: 20px;">签名：${item.appraser}</span>
						<span style="margin-right: 20px;">日期：${item.signdate}</span>
					</div>
				 </td>
				</tr>
			  </table>
			  </c:forEach><a id="1009"></a>
			  </td>
        </tr>
        
		    <tr>
		      <td rowspan="7" class="cell th">个性发展</td>
		      <td height="95" colspan="2" class="cell th">基本情况</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				<tr>
					<td  style="background:#fff;">二级指标</td>
					<td  style="background:#fff;">三级指标</td>
					<td style="background:#fff;">个性发展记录</td>
				</tr>
				<tr>
				<td class="cell" style="background:#fff;">特长</td>
				<td class="cell" style="background:#fff;">
					学科特长
					<br />
					体育运动特长
					<br />
					艺术特长
				</td>
				<td class="cell" style="background-color: #fff;padding-right: 6px " >
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '1'}">${item.apprasial}</c:if>
					</c:forEach>
				</td>
				</tr>
				<tr>
				 <td class="cell" style="background:#fff;">有新意的成果</td>
				 <td class="cell" style="background:#fff;">
					活动成果
					<br />
					设计成果
					<br />
					制作成果
				</td>
				<td class="cell" style="background-color: #fff; padding-right: 6px">
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '2'}">${item.apprasial}</c:if>
					</c:forEach>
				</td>
				</tr>
				<tr>
					<td class="cell" style="background:#fff;">其他 </td>
					<td class="cell" style="background:#fff;">自主选择</td>
					<td class="cell" style="background-color: #fff; padding-right: 6px">
					<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item">
					   <c:if test="${item.appraseridentifynum eq '3'}">${item.apprasial}</c:if>
					</c:forEach>
				</td>
				</tr>
				
				<c:forEach items="${appraiseMaps[GXFZ_JBQK]}" var="item" varStatus="pvs">
				
					<c:if test="${pvs.index == 0 }">
						<tr>
							<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
							</td>
					    </tr>
					</c:if>
				</c:forEach>
			  </table>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="cell th">自我评价</td>
		     <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_ZWPJ]}" var="item">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td rowspan="3" class="cell th">他人评价</td>
		      <td class="cell th">同学</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,STUDENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">教师</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,MASTER) or fn:contains(item.appraseridentify,TEACHER)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td class="cell th">家长</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
			  	<c:forEach items="${appraiseMaps[GXFZ_TRPJ]}" var="item">
						<c:if test="${fn:contains(item.appraseridentify,PARENT)}">
				  		<table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
								<tr>
									<td  style="padding-right: 6px">
									<div style="border-style: none;width: 100%; background: transparent none repeat scroll 0px 0px;">${item.apprasial}</div>
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
									</td>
								</tr>
				  		</table>
				  		</c:if>
				</c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="cell th">个性发展过程<span class="red"></span></td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[GXFZGC]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  
				  <tr >
				  	<td colspan="2" align="left" style="background-color: #fff;">个性发展过程${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td style="background:#fff;">内容描述：</td>
					  <td  style="padding-right: 6px">${item.apprasial}</td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
		    <tr>
		      <td height="95" colspan="2" class="cell th">特长与成果展示</td>
		      <td class="cell" style="background-color: rgb(238, 238, 238);">
		      <c:forEach items="${appraiseMaps[GXFZ_CGZS]}" var="item" varStatus="pVs">
				  <table width="100%" border="0" cellspacing="0" bgcolor="#fff" class="biaoge">
				  <tr >
				  	<td colspan="2" align="left" style="background-color: #fff;">特长与成果展示${pVs.count}</td>
				  </tr>
				  
				  <tr>
					  <td style="background:#fff;">内容描述：</td>
					  <td  style="padding-right: 6px">${item.apprasial}</td>
				  </tr>
				  <c:forEach items="${item.attachFileDtos}" var="fileitem" varStatus="state">
					  <tr>
					      <td style="background:#fff;">
						      附件${state.count}：
						  </td>
						  <td style="background:#fff;">
						     <span style="float:left"><a href="${ctx}${fileitem.filepath}">${fileitem.filename}</a></span>
						  </td>
					  </tr>
				  </c:forEach>
				  <tr>
						<td class="youjuzhong pr20 bg_eee" colspan="3">
							<div style="text-align: right;">
								<span style="margin-right: 20px;">评价人：${item.appraseridentify}</span>
								<span style="margin-right: 20px;">签名：${item.appraser}</span>
								<span style="margin-right: 20px;">日期：${item.signdate}</span>
							</div>
						</td>
				  </tr>
				  </table>
			  </c:forEach>
			  </td>
        </tr>
      	</table>
	</div>
</body>
<script type="text/javascript">
	window.parent.$("#MyDiv").hide();
</script>
</html>

