﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<un:bind var="KIND_SCHOOLTEACHER"
	type="com.flyrish.hades.common.Constant"
	field="USER_KIND_SCHOOLTEACHER" />
<un:bind var="KIND_SCHOOLGROUP" type="com.flyrish.hades.common.Constant"
	field="USER_KIND_SCHOOLGROUP" />
<un:bind var="KIND_COUNTY" type="com.flyrish.hades.common.Constant"
	field="USER_KIND_COUNTY" />
<un:bind var="KIND_CITY" type="com.flyrish.hades.common.Constant"
	field="USER_KIND_CITY" />

<un:bind var="TYPE_LEVELCODE_CZSTR"
	type="com.flyrish.hades.common.Constant"
	field="DICT_TYPE_LEVELCODE_CZSTR" />
<un:bind var="TYPE_LEVELCODE_GZSTR"
	type="com.flyrish.hades.common.Constant"
	field="DICT_TYPE_LEVELCODE_GZSTR" />
<un:bind var="TYPE_LEVELCODE_GZYKSTR"
	type="com.flyrish.hades.common.Constant"
	field="DICT_TYPE_LEVELCODE_GZYKSTR" />
<un:bind var="TYPE_LEVELCODE_GZKGTR"
	type="com.flyrish.hades.common.Constant"
	field="DICT_TYPE_LEVELCODE_GZKGTR" />
<un:bind var="SCHOOLADMIN" type="com.flyrish.hades.common.Constant"
	field="USER_TYPE_SCHOOLADMIN" />
<un:bind var="MASTER" type="com.flyrish.hades.common.Constant"
	field="USER_TYPE_CLASSMASTER" />
<%-- 班主任 --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type='text/javascript'
	src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script src="${ctx}/js/index.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<!-- <base target='mainframe_all' /> -->
<style>
td a {
	font-size: 13px;
}

#alls {
	width: 700px;
}

#alls td {
	width: 21%;
}

#alls img {
	height: 17px;
	width: 22px;
}

.nr {
	padding: 15px;
}
a.usedMore {
    margin: 0 7px;
}
</style>
<script type="text/javascript">
	/* if(self!=top){top.location=self.location;} */
	function toFirstPage() {
		var campuseId = document.getElementById('campuseId');
		if (campuseId == null) {
			var roleIdDom = document.getElementById('roleId');
			var roleId = null;
			if (roleIdDom != null)
				roleId = roleIdDom.value;
			toHrefByPost('frm1', "${ctx}/homePage/HomePageAction.a?roleId="
					+ roleId + "&campuseId=''", 'iframepage');
		} else {
			var roleIdDom = document.getElementById('roleId');
			var roleId = null;
			if (roleIdDom != null)
				roleId = roleIdDom.value;
			toHrefByPost('frm1', "${ctx}/homePage/HomePageAction.a?roleId="
					+ roleId + "&campuseId="
					+ document.getElementById('campuseId').value, 'iframepage');
		}
	}

	function funcChange(obj, userid, funcId, userLevel, funcMessg) {

		if (obj.indexOf(".a") <= 0) {
			alert("功能尚未开通！");
			return false;
		}
		var result = true;
		dwr.engine.setAsync(false);
		ShowUserFuncDwr.saveUserFunc(userid, funcId, funcMessg, function(re) {
			result = re;
		});
		dwr.engine.setAsync(true);
		if (result) {
			//通过dwr更新常用功能列表
			var roleIdVal;
			if (document.getElementById('roleId') != null)
				roleIdVal = document.getElementById('roleId').value;
			ShowUserFuncDwr
					.queryUseNumUserMenu(
							userid,
							roleIdVal,
							function(data) {
								var useHtml = "";
								if (null != data && data.length > 0) {
									for (var i = 0; i < data.length; i++) {
										if (null == data[i].execfilename
												|| data[i].execfilename == 'null') {
											useHtml += "<a href='#'  onclick='nogo();'>"
													+ data[i].funcname
													+ "</a>";
										} else if (data[i].execfilename
												.indexOf("?") == "-1") {
											useHtml += "<a class=\"usedMore\" href='${ctx}"
													+ data[i].execfilename
													+ "?commonFuncId="
													+ data[i].funcid
													+ "' target='mainframe_all'>"
													+ data[i].funcname
													+ "</a>";
										} else {
											useHtml += "<a class=\"usedMore\" href='${ctx}"+data[i].execfilename+"&&commonFuncId="+data[i].funcid+"' target='mainframe_all'>"
													+ data[i].funcname
													+ "</a>";
										}
									}
								}
								document.getElementById('offused').innerHTML = useHtml;
							});
		}
		toHrefByPost('frm1', obj, 'iframepage');
	}
	function toFunc(currentObj, url, herf) {
		if (url.indexOf(".a") <= 0) {
			alert("功能尚未开通！");
			return false;
		}
		toHrefByPost('frm1', herf, 'iframepage');
	}
</script>

<title>北京市中小学生综合素质评价系统</title>
</head>
<body style="overflow: hidden;">
	<div id="header">
		<div class="header_left fl" style="width: 80%">
			<span> <img class="header_left fl"
				src="${ctx}/images/logo1.png"
				style="width: 102px; margin-top: 10px; height: 30px; margin-left: 10px">
			</span> <span class="logo fl  mt18 "> <img
				src="${ctx}/images/logo.png" width="340" height="20" /></span>
			<c:if test="${not empty schoolName }">
				<span class="title ml10 mt18 fl">${schoolName }</span>
			</c:if>
			<span class="name_icon ml10 mt18 fl"></span> <span
				class="name ml5 mt18 fl">${sessionScope.sessionUser.username }登录成功！</span>


		</div>
		<div class="header_right" style="position: absolute;top: 0;right: 0;margin-right: 10px;">
			<ul>
				<c:if
					test="${sessionScope.sessionUser.levelcode eq TYPE_LEVELCODE_GZKGTR }">
					<li style="display: none" id="liid">
				</c:if>
				<c:if
					test="${sessionScope.sessionUser.levelcode ne TYPE_LEVELCODE_GZKGTR }">
					<li id="liid">
				</c:if>
				<c:if
					test="${sessionScope.sessionUser.usertype ne KIND_SCHOOLGROUP}">
					<span class="set_icon sy fl"></span>
					<a href="#" class="fl ml8" onclick="toFirstPage()">首页</a>
				</c:if>
				</li>
				<!-- 	<li>
            	<span class="set_icon ywts fl"></span>
            	<a href="main.jsp" target="mainframe_all" class="fl ml8">业务提示</a>
            </li> -->

				<c:if
					test="${
            			sessionScope.sessionUser.usertype eq 1503001 and isShowPwd ||
            			sessionScope.sessionUser.usertype eq 1503002 and isShowPwd ||
            			
            			
            			
            			
            			sessionScope.sessionUser.usertype eq 1503005 and isShowPwd || 
            			
            			sessionScope.sessionUser.usertype eq 1503006 and isShowPwd ||
            			sessionScope.sessionUser.usertype eq 1503007 and isShowPwd 
            			}">
					<li><span class="set_icon xgmm fl"></span> <a
						href="http://211.153.82.89:8080/am/portalManage/initModifyPwdAction.action"
						class="fl ml8">修改密码</a> <%-- <a href="${ctx }/user/ChangePwdAction.a" target="mainframe_all" class="fl ml8">修改密码</a>  --%>
					</li>
				</c:if>


				<li><span class="set_icon tc fl"></span> <a
					href="${ctx}/j_acegi_logout" class="fl ml8" target="_self">退出</a></li>
			</ul>
		</div>
	</div>

	<div id="nav">
		<div class="nav_left fl" style="margin-left: 20px;">
			<c:if test="${sessionScope.sessionUser.usertype ne KIND_SCHOOLGROUP}">
				<div id="menus" class="menu_gongneng fl mt10" style="margin-right: 10px;">
					<a href="#" id="AMenu" style="left: 20px;display: block;z-index: 1000;position: absolute;width: 124px;border-bottom: solid 1px #293A4A;">
						<span class="set_icon gn fl ml18" style="margin-top: 7px;"></span> 
						<span class="fl ml18">功能菜单</span>
					</a>
					<div id="alls" style="border: solid 1px;border-top-left-radius: 0;top: 89px;left: 20px;">
						<table>
							<tbody>
								<c:if test="${not empty userFuncTreeDtoes }">
									<tr class="odd">
										<c:forEach items="${userFuncTreeDtoes}" var="treeDto"
											varStatus="vsta">
											<c:if test="${vsta.count ==1}">
												<c:set var="c" value="0"></c:set>
												<!-- 判断是否满5个 -->
												<c:set var="a" value="${treeDto.showType}"></c:set>
												<!-- 判断菜单类型 -->
											</c:if>

											<c:if test="${treeDto.showType eq a}">
												<c:if test="${c eq 5}">
													<c:set var="c" value="0"></c:set>
									</tr>
									<tr>
										<td colspan="5" class="tds"></td>
									</tr>
									<tr>
								</c:if>
								</c:if>

								<c:if test="${treeDto.showType ne a}">
									<c:forEach begin="1" end="${5-c}" step="1">
										<td></td>
									</c:forEach>
									<c:set var="c" value="0"></c:set>
									</tr>
									<tr>
										<td colspan="5" class="tds"></td>
									</tr>
									<tr>
										<c:set var="a" value="${treeDto.showType}"></c:set>
										<c:set var="c" value="0"></c:set>
								</c:if>
								<td><c:choose>
										<c:when test="${fn:indexOf(treeDto.url,'?') ne -1}">
											<a href="#"
												onclick="funcChange('${ctx}${treeDto.url }&&commonFuncId=${treeDto.funcId }','${sessionScope.sessionUser.userid}','${treeDto.funcId }','${sessionScope.sessionUserLevel }','${treeDto.url }_@_${treeDto.funcName }')"><img
												src="${ctx }/images/${treeDto.funcImage }" />
												${treeDto.funcName }</a>
										</c:when>
										<c:otherwise>
											<a href="#"
												onclick="funcChange('${ctx}${treeDto.url }?commonFuncId=${treeDto.funcId }','${sessionScope.sessionUser.userid}','${treeDto.funcId }','${sessionScope.sessionUserLevel }','${treeDto.url }_@_${treeDto.funcName }')"><img
												src="${ctx }/images/${treeDto.funcImage }" />
												${treeDto.funcName }</a>
										</c:otherwise>
									</c:choose></td>
								<c:if test="${c ne 5}">
									<c:set var="c" value="${c+1}"></c:set>
								</c:if>
								</c:forEach>

								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<span class="location">常用：</span>
				<div id="offused" style="display: inline">
					<c:if test="${not empty userCommonFunc}">
						<c:forEach items="${userCommonFunc}" var="useFunc"
							varStatus="uscf">
							<c:if test="${empty useFunc.url }">
								<a class="usedMore" href="#" onclick="nogo();">${useFunc.funcName }</a>
				</c:if>
							<c:if test="${not empty useFunc.url }">
								<c:choose>
									<c:when test="${fn:indexOf(useFunc.url,'?') ne -1}">
										<a class="usedMore" id="${uscf.count}_A" href="#"
											onclick="toFunc($$(this),'${useFunc.url }','${ctx }${useFunc.url }&&commonFuncId=${useFunc.funcId }')"
											target="mainframe_all">${useFunc.funcName }</a>
		           	</c:when>
									<c:otherwise>
										<a class="usedMore" id="${uscf.count}_A" href="#"
											onclick="toFunc($$(this),'${useFunc.url }','${ctx }${useFunc.url }?commonFuncId=${useFunc.funcId }')"
											target="mainframe_all">${useFunc.funcName }</a>
		           	</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:if>
					<%-- <c:if test="${sessionScope.sessionUser.userRealType eq SCHOOLADMIN and (sessionScope.sessionUser.levelcode eq '2012003' or sessionScope.sessionUser.levelcode eq '2012004')}">
  		<a href="${ctx}/bookreport/BookreportAction.a" target="mainframe_all" >高中报告册</a>
  		</c:if>
  		<c:if test="${sessionScope.sessionUser.userRealType eq MASTER and (sessionScope.sessionUser.levelcode eq '2012003' or sessionScope.sessionUser.levelcode eq '2012004') }">
  		<a href="${ctx}/reportBook/ReportBookAction.a" target="mainframe_all" >高中报告册</a>
  		</c:if> --%>
				</div>
			</c:if>
		</div>

		<div class="nav_right fr mr30">
			<ul>
				<li style='margin-left: 10px;'><span class="set_icon xq fl mt16"></span>
					<c:if test="${not empty schoolYearNameShow }">
						<span class="fl ml8">${schoolYearNameShow }</span>
					</c:if></li>

				<li style='margin-left: 10px;'><c:if
						test="${sessionScope.sessionUser.usertype eq KIND_SCHOOLTEACHER}">
						<span class="set_icon tj fl mt16"></span> &nbsp;
		  	
		  	<select name="campuseId" id="campuseId"
							onchange="selectRoleName(this.value)" autocomplete='off'>
							<app:userSchoolTag userId="${sessionScope.sessionUser.userid}"
								selectValue="${sessionScope.sessionUser.campuseId}" />
						</select>
					</c:if></li>


				<li style='margin-left: 10px;'><c:if
						test="${sessionScope.sessionUser.usertype eq KIND_SCHOOLTEACHER}">
						<span class="set_icon js fl mt16"></span>&nbsp;
			    	<select name="roleId" id="roleId"
							onchange="selectLevelName(this.value)" autocomplete='off'>
							<c:forEach items="${userRoleDtos}" var="dto">
								<option value="${dto.roleId}"
									<c:if test="${dto.roleId eq sessionScope.sessionUser.roleId}">selected="selected"</c:if>>${dto.roleName}</option>
							</c:forEach>
						</select>
					</c:if></li>


				<li
					style="height: 100%; line-height: 100%; margin-top: 15px; margin-left: 10px;">
					<c:if
						test="${sessionScope.sessionUser.usertype eq KIND_SCHOOLTEACHER}">
						<span> <img src="${ctx}/images/xie19.png"
							style="vertical-align: middle; width: 18; height: 19;" /></span>
						</span>&nbsp;
		  		<select name="levelId" id="levelId"
							onchange="selectLevelCode(this.value)" autocomplete='off'>
							<c:forEach items="${edusysDtos}" var="dto">
								<option value="${dto.edusysId}"
									<c:if test="${dto.edusysId eq sessionScope.sessionUser.levelid}">selected="selected"</c:if>>${dto.edusysName}</option>
							</c:forEach>
						</select>
					</c:if> <c:if
						test="${sessionScope.sessionUser.usertype eq KIND_COUNTY or sessionScope.sessionUser.usertype eq KIND_CITY}">
						<select name="levelId" id="levelId"
							onchange="refreshLevelcode(this.value)" autocomplete='off'>
							<option value="${TYPE_LEVELCODE_CZSTR}">初中</option>
							<option value="${TYPE_LEVELCODE_GZSTR}">高中</option>
						</select>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
	<div class="middle-frame" style="position: absolute;top: 96px;left: 0;right: 0;bottom: 30px;overflow: auto;">
	<c:if
		test="${sessionScope.sessionUser.usertype ne KIND_SCHOOLGROUP and sessionScope.sessionUser.levelcode ne TYPE_LEVELCODE_GZKGTR}">
		<iframe
			src="${ctx }/homePage/HomePageAction.a?roleId=${sessionScope.sessionUser.roleId }&campuseId=${sessionScope.sessionUser.campuseId }"
			name="mainframe_all" id="iframepage" frameborder="0"
			target="mainframe_all" style="width: 100%; height: 100%;"
			allowTransparency="true"> </iframe>
	</c:if>
	<c:if test="${sessionScope.sessionUser.usertype eq KIND_SCHOOLGROUP}">
		<iframe src="${ctx}/fileupload/import.jsp" name="mainframe_all"
			id="iframepage" frameborder="0" target="mainframe_all"
			style="width: 100%; height: 100%;" allowTransparency="true">
		</iframe>
	</c:if>
	<c:if
		test="${sessionScope.sessionUser.levelcode eq TYPE_LEVELCODE_GZKGTR}">
		<iframe src="${ctx}/score/ScoreAction.a" name="mainframe_all"
			id="iframepage" frameborder="0" target="mainframe_all"
			style="width: 100%; height: 100%;" allowTransparency="true">
		</iframe>
	</c:if>
	</div>
	<div
		style="background: none repeat scroll 0% 0% #34495E; bottom: 0px; height: 30px; position: absolute; width: 100%; line-height: 30px; font-weight: normal; color: #FFF;">
		<h5 class="left_h5"
			style="margin: 0px 15px; color: #FFF; font-weight: normal;">©北京教育网络和信息中心
		</h5>
		<h5 class="right_h5"
			style="margin: 0px 30px; color: #FFF; font-weight: normal;">技术支持：©北京教育网络和信息中心
			| 电话：4006515253</h5>
	</div>
	<form action="" id="frm1"></form>

	<script type="text/javascript">
		var $$;
		if (!$$) {
			$$ = dwr.util.byId;
		}
		function refreshLevelcode(levelcode) {
			ShowUserFuncDwr
					.refreshLevelCode(
							levelcode,
							function(data) {
								if (null != data && data.length > 0) {
									document.getElementById('alls').innerHTML = "";
									var functype = data[0].showType;
									var allHtml = "<table><tbody><tr class='odd'>";
									var num = 0;
									for (var i = 0; i < data.length; i++) {
										if (0 == i) {
											functype = data[i].showType;
										}
										if (functype == data[i].showType) {
											if (num == 5) {
												num = 0;
												allHtml += "</tr><tr><td colspan='5' class='tds'></td></tr><tr>"
											}
										}
										if (functype != data[i].showType) {
											for (var j = 1; j < 5 - num; j++) {
												allHtml += "<td></td>";
											}
											num = 0;
											allHtml += "</tr><tr><td colspan='5' class='tds'></td></tr><tr>";
											functype = data[i].showType;
										}
										if (null == data[i].url
												|| data[i].url == 'null') {
											allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
													+ data[i].url
													+ "?commonFuncId="
													+ data[i].funcId
													+ "',${sessionScope.sessionUser.userid},"
													+ data[i].funcId
													+ ",'${sessionScope.sessionUserLevel }','"
													+ data[i].url
													+ "_@_"
													+ data[i].funcName
													+ "')\">"
													+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
													+ data[i].funcName
													+ "</a></td>";
										} else {
											if (data[i].url.indexOf("?") != "-1") {
												allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
														+ data[i].url
														+ "&&commonFuncId="
														+ data[i].funcId
														+ "',${sessionScope.sessionUser.userid},"
														+ data[i].funcId
														+ ",'${sessionScope.sessionUserLevel }','"
														+ data[i].url
														+ "_@_"
														+ data[i].funcName
														+ "')\">"
														+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
														+ data[i].funcName
														+ "</a></td>";

											} else {
												allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
														+ data[i].url
														+ "?commonFuncId="
														+ data[i].funcId
														+ "',${sessionScope.sessionUser.userid},"
														+ data[i].funcId
														+ ",'${sessionScope.sessionUserLevel }','"
														+ data[i].url
														+ "_@_"
														+ data[i].funcName
														+ "')\">"
														+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
														+ data[i].funcName
														+ "</a></td>";
											}
										}

										if (num != 5) {
											num++;
										}
									}
									allHtml += "</tbody></table>";
									document.getElementById('alls').innerHTML = allHtml;
								}
								if (levelcode == '99999') {
									$$('iframepage').src = '${ctx}/score/ScoreAction.a?time='
											+ new Date().getTime();
								} else {
									$$('iframepage').src = '${ctx}/homePage/HomePageAction.a?time='
											+ new Date().getTime();
								}
							});
			ShowUserFuncDwr
					.queryUseCommonFunc(
							null,
							function(data) {
								var useHtml = "";
								if (null != data && data.length > 0) {
									for (var i = 0; i < data.length; i++) {
										if (null == data[i].url
												|| data[i].url == 'null') {
											useHtml += "<a href='#' onclick=\"funcChange('${ctx}"
													+ data[i].url
													+ "?commonFuncId="
													+ data[i].funcId
													+ "',${sessionScope.sessionUser.userid},"
													+ data[i].funcId
													+ ",'${sessionScope.sessionUserLevel }','"
													+ data[i].url
													+ "_@_"
													+ data[i].funcName
													+ "')\">"
													+ data[i].funcName
													+ "</a>";
										} else {
											if (data[i].url.indexOf("?") != "-1") {
												useHtml += "<a href='${ctx}"+data[i].url+"&&commonFuncId="+data[i].funcId+"' target='mainframe_all'>"
														+ data[i].funcName
														+ "</a>";
											} else {
												useHtml += "<a class=\"usedMore\" href='${ctx}"
														+ data[i].url
														+ "?commonFuncId="
														+ data[i].funcId
														+ "' target='mainframe_all'>"
														+ data[i].funcName
														+ "</a>";
											}
										}
									}
								}
								document.getElementById('offused').innerHTML = useHtml;
							});
		}
		function selectRoleName(campuseId) {
			funcOne(campuseId);
		}

		function funcOne(campuseId) {

			ShowUserFuncDwr.queryRoleNameAndLevelName(campuseId,
					function(data) {
						if (null != data && data.length > 0) {
							DWRUtil.removeAllOptions('roleId');
							DWRUtil.addOptions('roleId', data, 'roleId',
									'roleName');
						}
						var roleId = document.getElementById('roleId').value;
						funcTwo(campuseId, roleId);
					});

		}

		function selectLevelName(roleId) {
			var campuseId = document.getElementById('campuseId').value;
			funcTwo(campuseId, roleId);
		}

		function selectLevelCode(levelId) {
			var roleId = document.getElementById('roleId').value;
			var campuseId = document.getElementById('campuseId').value;
			var levelId = document.getElementById("levelId").value;
			funcThree(campuseId, levelId, roleId);
		}
		function funcTwo(campuseId, roleId) {

			ShowUserFuncDwr.queryEdusysDtoByCampuseIdAndRoleId(campuseId,
					roleId, function(data) {

						if (null != data && data.length > 0) {
							DWRUtil.removeAllOptions('levelId');
							DWRUtil.addOptions('levelId', data, 'edusysId',
									'edusysName');
						}
						var levelId = document.getElementById("levelId").value;
						funcThree(campuseId, levelId, roleId);
					});
		}

		function funcThree(campuseId, levelId, roleId) {

			//通过dwr获取功能菜单
			ShowUserFuncDwr
					.queryUserFuncTree(
							campuseId,
							levelId,
							roleId,
							function(data) {

								if (null != data && data.length > 0) {
									document.getElementById('alls').innerHTML = "";
									var functype = data[0].showType;
									var allHtml = "<table><tbody><tr class='odd'>";
									var num = 0;
									for (var i = 0; i < data.length; i++) {
										if (0 == i) {
											functype = data[i].showType;
										}
										if (functype == data[i].showType) {
											if (num == 5) {
												num = 0;
												allHtml += "</tr><tr><td colspan='5' class='tds'></td></tr><tr>"
											}
										}
										if (functype != data[i].showType) {
											for (var j = 1; j < 5 - num; j++) {
												allHtml += "<td></td>";
											}
											num = 0;
											allHtml += "</tr><tr><td colspan='5' class='tds'></td></tr><tr>";
											functype = data[i].showType;
										}
										if (null == data[i].url
												|| data[i].url == 'null') {
											allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
													+ data[i].url
													+ "?commonFuncId="
													+ data[i].funcId
													+ "',${sessionScope.sessionUser.userid},"
													+ data[i].funcId
													+ ",'${sessionScope.sessionUserLevel }','"
													+ data[i].url
													+ "_@_"
													+ data[i].funcName
													+ "')\">"
													+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
													+ data[i].funcName
													+ "</a></td>";
										} else {
											if (data[i].url.indexOf("?") != "-1") {
												allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
														+ data[i].url
														+ "&&commonFuncId="
														+ data[i].funcId
														+ "',${sessionScope.sessionUser.userid},"
														+ data[i].funcId
														+ ",'${sessionScope.sessionUserLevel }','"
														+ data[i].url
														+ "_@_"
														+ data[i].funcName
														+ "')\">"
														+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
														+ data[i].funcName
														+ "</a></td>";

											} else {
												allHtml += "<td><a href='#' onclick=\"funcChange('${ctx}"
														+ data[i].url
														+ "?commonFuncId="
														+ data[i].funcId
														+ "',${sessionScope.sessionUser.userid},"
														+ data[i].funcId
														+ ",'${sessionScope.sessionUserLevel }','"
														+ data[i].url
														+ "_@_"
														+ data[i].funcName
														+ "')\">"
														+ "<img src='${ctx }/images/"+data[i].funcImage+"'/>"
														+ data[i].funcName
														+ "</a></td>";
											}
										}

										if (num != 5) {
											num++;
										}
									}
									allHtml += "</tbody></table>";
									document.getElementById('alls').innerHTML = allHtml;
								}
								//通过dwr获取常用功能
								ShowUserFuncDwr
										.queryUseCommonFunc(
												roleId,
												function(data) {
													var useHtml = "";
													if (null != data
															&& data.length > 0) {
														for (var i = 0; i < data.length; i++) {
															if (null == data[i].url
																	|| data[i].url == 'null') {
																useHtml += "<a href='#' onclick=\"funcChange('${ctx}"
																		+ data[i].url
																		+ "?commonFuncId="
																		+ data[i].funcId
																		+ "',${sessionScope.sessionUser.userid},"
																		+ data[i].funcId
																		+ ",'${sessionScope.sessionUserLevel }','"
																		+ data[i].url
																		+ "_@_"
																		+ data[i].funcName
																		+ "')\">"
																		+ data[i].funcName
																		+ "</a>";
															} else {
																if (data[i].url
																		.indexOf("?") != "-1") {
																	useHtml += "<a class=\"usedMore\" href='${ctx}"+data[i].url+"&&commonFuncId="+data[i].funcId+"' target='mainframe_all'>"
																			+ data[i].funcName
																			+ "</a>";
																} else {
																	useHtml += "<a class=\"usedMore\" href='${ctx}"
																			+ data[i].url
																			+ "?commonFuncId="
																			+ data[i].funcId
																			+ "' target='mainframe_all'>"
																			+ data[i].funcName
																			+ "</a>";
																}
															}
														}
													}
													document
															.getElementById('offused').innerHTML = useHtml;
												});
								if (levelId == '99999') {
									$$('iframepage').src = '${ctx}/score/ScoreAction.a?time='
											+ new Date().getTime();
									document.getElementById('liid').style.display = "none";
								} else {
									$$('iframepage').src = '${ctx}/homePage/HomePageAction.a?roleId='
											+ roleId
											+ '&campuseId='
											+ campuseId
											+ '&levelId='
											+ levelId
											+ '&time=' + new Date().getTime();
									document.getElementById('liid').style.display = "";
								}
							});
		}
		function nogo() {
			alert("功能尚未开通！");
			return false;
		}
	</script>
	<script type="text/javascript">
		function toHrefByPost(formId, url, target) {
			var iframe = document.getElementById("iframepage");
			if (navigator.userAgent.indexOf("Firefox") < 0
					&& navigator.userAgent.indexOf("Chrome") < 0) {
				iframe.src = '';
			}
			setTimeout(function() {
				iframe.src = url;
			}, 10);
		}
	</script>
</body>
</html>
