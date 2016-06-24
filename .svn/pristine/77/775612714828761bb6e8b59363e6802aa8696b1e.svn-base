<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="${ctx }/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${ctx }/css/unicorn.main.css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.4.min.js"></script>
<title>查看通知公告详细信息</title>
<style >
 	.bt{
		background: none repeat scroll 0% 0% #279F46;
		border-bottom: 3px solid #1C7132;
		color: #FFF;
	}
	.table {
    margin: 0 auto;
    width: 60%;
    }
    .nopadding .table-bordered {
       border: 1px solid #dddddd;
    }
    .min-height { 
    min-height:100px; 
    height:auto !important; 
    height:100px; 
    width:70%; 
}
 </style>
 <script type="text/javascript">
    function dodown()
    {
    	var url = "${ctx}/inform/InformAction.a?downAttach&filename=${information.filename}&filepath=${information.filepath}";
	    document.location.replace(url);
	    return false;
    }
    function goBack() {
    	if('${isBack}'=='back'){
        	var url ="${ctx}/inform/InformAction.a?queryInformByCon&isBack=back";
        	document.location.replace(url);
    	}else{
    		window.history.back(-1)
    	}
	}
 </script>
</head>
    <body style="background: #EEE">
	<div style="width: 100%;">
		<div id="breadcrumb" style="margin-top: 9px;">
				<a href="#" class="tip-bottom" style="font-size: 12px;color: #333;font-weight: bold;"><i class="icon-home"></i>通知公告</a>
				<a href="#" class="current" style="font-size: 12px;color: #333">查看</a>
		</div>
		
		<div style="width: 100%;margin-top: 13px">
						<div class="widget-box" style="margin-top:0px;margin-bottom:0px;margin-left: 13px;margin-right: 13px">
							<div style="background: #279F46;text-align: center;font-size: 13px; color: #FFF;text-shadow: 0px 0px 0px #FFF;line-height: 36px;margin: 0px;" >
								通知公告信息
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped " width="60%">
									<tbody>
										<tr >
											<td style="text-align: right;font-weight:bold;" width="30%">主题</td>
											<td style="text-align: left;" width="70%">${information.theme}</td>
										</tr>
										<tr >
											<td style="text-align: right;font-weight:bold;vertical-align: middle;" width="30%">内容：</td>
											<td style="text-align: left;" width="70%">
											<div class="min-height">
											     ${information.text}
											</div>
											</td>
										</tr>
                                      <%-- <c:if test="${not empty information.filename }"> --%>
										<tr>
											<td style="text-align: right;font-weight:bold;" width="30%">附件文件：</td>
											<td style="text-align: left;" width="70%"><a name="down" href="javascript:void(0);" style="cursor:pointer;color:black;" onclick="dodown();">${information.filename}</a></td>
										</tr>
									  <%-- </c:if> --%>
									</tbody>
								</table>	
								<div class="form-actions pagination alternate"  style="margin-top: 0px;margin-bottom: 0px;">
									<span style="margin-right:19%;float:right;margin-top:  line-height: 36;">
										<button  style="width: 70px;" class="btn btn-success" onclick="goBack()">返回</button>
								     </span>
								</div>						
							</div>
						</div>
		</div>
	</div>
	
</body>
</html>