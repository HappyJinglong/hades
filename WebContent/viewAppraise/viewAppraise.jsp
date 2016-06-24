<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx }/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/unicorn.main.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/jquery.gritter.css" />
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="${ctx}/js/unicorn.interface.kg.js"></script>
<script type='text/javascript'
	src='${ctx}/dwr/interface/ShowUserFuncDwr.js'></script>
<script type='text/javascript' src='${ctx }/dwr/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>

<style type="text/css">
.selectionCls {
	width: auto;
	margin: auto;
}

li {
	float: left;
	margin-right: 40px;
}

ul {
	list-style: none;
	overflow: hidden;
	padding: 0;
	margin: 0 auto;
	background: #eee;
	border-top: none;
	width: 1200px;
}
</style>
<title>统计表</title>
</head>
<script type="text/javascript">
	function submitdata(index) {
		index = index || 1;
		var cmis30id = $("#cmis30id").val();
		var discode = $("#discode").val() || '${discode}';
		var classid = $('#classid').val();
		var termid = $("#termid").val();
		var graduateyear = $("#gradeid").val();
		var firstItem = $("#firstItem").val();
		var secondItem = $("#secondItem").val();
		var levelcode = window.top.$("#levelId option:selected").val();
		if (discode == undefined || discode == '') {
			alert("请选择区县!");
			return false;
		}
		if (cmis30id == undefined || cmis30id == '') {
			alert("请选择学校!");
			return false;
		}
		if (termid == undefined || termid == '') {
			alert("请选择学期!");
			return false;
		}
		if (graduateyear == undefined || graduateyear == '') {
			alert("请选择届别!");
			return false;
		}

		ShowDiv();
		var params = '&levelCode=' + levelcode + '&index=' + index
				+ '&cmis30id=' + cmis30id + '&discode=' + discode + '&classid='
				+ classid + '&termid=' + termid + '&graduateyear='
				+ graduateyear
		if (firstItem == undefined || firstItem == '') {
			$("#iframe").prop(
					'src',
					'${ctx}/viewAppraise/ViewAppraiseAction.a?toPersonPage'
							+ params);
		} else {
			params += '&firstItem=' + firstItem + '&secondItem=' + secondItem;
			if (secondItem == undefined || secondItem == '') {
				$("#iframe").prop(
						'src',
						'${ctx}/viewAppraise/ViewAppraiseAction.a?toItemPage'
								+ params);
			} else {
				$("#iframe").prop(
						'src',
						'${ctx}/viewAppraise/ViewAppraiseAction.a?toSecondItemPage'
								+ params);
			}
		}
	}

	function rerender() {
		var searchBar = $("#searchBar");
		var bodyContent = $("#bodyContent");
		bodyContent.css('top', 40 + searchBar.height());
	}

	function changeDiscode(discode) {
		$.post('${ctx}/viewAppraise/viewAppraiseAction.a?querySchool', {
			'levelcode' : '${levelCode}',
			'discode' : discode
		}, function(data) {
			DWRUtil.removeAllOptions('cmis30id');
			if (null != data && data.length > 0) {
				data = eval('(' + data + ')')
				DWRUtil.addOptions('cmis30id', [ {
					'cmis30id' : '',
					'schoolname' : '--请选择--'
				} ], 'cmis30id', 'schoolname');
				DWRUtil.addOptions('cmis30id', data, 'cmis30id', 'schoolname');
				rerender();
			}
		});

	}
	function changeSchool(cmis30id) {
		$("#gradeid").val('');
		$("#termid").val('');
		$("#classid").val('');
		rerender();
	}
	function changeGrade(gradeyear) {
		var cmis30id = $("#cmis30id").val();
		var discode = $("#discode").val();
		$.post('${ctx}/viewAppraise/viewAppraiseAction.a?getClassList', {
			'graduateyear' : gradeyear,
			'cmis30id' : cmis30id,
			'discode' : discode
		}, function(data) {
			DWRUtil.removeAllOptions('classid');
			if (null != data && data.length > 0) {
				data = eval('(' + data + ')')
				DWRUtil.addOptions('classid', data, 'classid', 'classname');
				rerender();
			}
		});
		ShowUserFuncDwr.queryTermDtoByGradeYear(gradeyear, function(data) {
			DWRUtil.removeAllOptions('termid');
			if (null != data && data.length > 0) {
				DWRUtil.addOptions('termid', data, 'termid', 'termname');
				rerender();
			}
		});
	}
	<c:if test="${empty queryQXInfos}">
	changeDiscode('${discode}');
	</c:if>

	var secs = {
		"10" : [ {
			"1010" : "自我评价"
		}, {
			"1020" : "我的发展目标"
		}, {
			"1030" : "家长期望"
		} ],
		"20" : [ {
			"2010" : "自我评价"
		}, {
			"2020" : "我的发展目标"
		}, {
			"2030" : "班主任评语"
		}, {
			"2040" : "家长评语和期望"
		} ],
		"30" : [ {
			"3010" : "自我评价"
		}, {
			"3020" : "他人评价"
		}, {
			"3030" : "思想道德事迹记录袋"
		}, {
			"3040" : "思想道德工具评价"
		} ],
		"40" : [ {
			"4010" : "自我评价"
		}, {
			"4020" : "他人评价"
		}, {
			"4030" : "合作与交流记录袋"
		} ],
		"50" : [ {
			"5010" : "自我评价"
		}, {
			"5020" : "他人评价"
		}, {
			"5030" : "体质健康"
		}],
		"60" : [ {
			"6010" : "自我评价"
		}, {
			"6020" : "他人评价"
		}, {
			"6030" : "审美与表现记录袋"
		}, {
			"6040" : "审美与表现工具评价"
		} ],
		"70" : [ {
			"7010" : "基本情况"
		}, {
			"7020" : "自我评价"
		}, {
			"7030" : "他人评价"
		}, {
			"7040" : "个性发展过程"
		}, {
			"7050" : "个性发展特长与成果展示"
		} ],
		"80" : [ {
			"8010" : "学科作品展示"
		}, {
			"8020" : "课程评语"
		}, {
			"8030" : "自我评价"
		}, {
			"8040" : "他人评价"
		} ],
		"90" : [ {
			"9010" : "研究性学习评价"
		}, {
			"9020" : "社区服务评价"
		}, {
			"9030" : "社会实践基本评价"
		} ]
	};
	function changeFirstItem(value) {
		var html = [ '<option value="">--全部--</option>' ];
		if ('${levelcode ne 2012002}') {
			var list = secs[value];
			for ( var _index in list) {
				var obj = list[_index];
				for ( var key in obj)
					html.push('<option value="'+ key +'">' + obj[key]
							+ '</option>');
			}
		}
		$("#secondItem").html(html.join(''));
		rerender();
	}
</script>
<body>
	<div>
		<div id="breadcrumb" style="margin-top: 10px;">
			<a href="#" class="tip-bottom"
				style="font-size: 12px; color: #333; font-weight: bold;"><i
				class="icon-item"></i>查看评价内容</a>
		</div>
		<div
			style="width: 100%; border: 1px solid #CDCDCD; line-height: 40px; background: #eee;">
			<ul id="searchBar">
				<c:if test="${!empty queryQXInfos}">
					<li>区县： <select id="discode" name="discode"
						class="selectionCls" onchange="changeDiscode(this.value)">
							<option value="">--请选择--</option>
							<c:if test="${empty discode}">
								<c:forEach items="${queryQXInfos}" var="info" varStatus="vs">
									<option value="${fn:split(info,'@')[0]}">${fn:split(info,'@')[1]}</option>
								</c:forEach>
							</c:if>
							<c:if test="${!empty discode}">
								<c:forEach items="${queryQXInfos}" var="info">
									<option value="${fn:split(info,'@')[0]}">${fn:split(info,'@')[1]}</option>
								</c:forEach>
							</c:if>
					</select>
					</li>
				</c:if>
				<li>学校： <select id="cmis30id" name="cmis30id"
					class="selectionCls" onchange="changeSchool(this.value);">
						<option value="">--请选择--</option>
				</select>
				</li>
				<li>届别： <select id="gradeid" name="gradeid"
					class="selectionCls" onchange="changeGrade(this.value);">
						<option value="">--请选择--</option>
						<app:gradeYear gradeYear="${gradeid}" />
				</select>
				</li>
				<li>学期： <select name="termid" id="termid" class="selectionCls">
						<option value="">--请选择--</option>
						<app:newTermId termId="${termId}" />
				</select>
				</li>
				<li>班级 <select name="classid" id="classid" class="selectionCls">
						<option value="">--请选择--</option>
				</select>
				</li>
				<li>一级栏目 <select name="firstItem" id="firstItem"
					class="selectionCls" onchange="changeFirstItem(this.value)">
						<option value="">--全部--</option>
						<c:if test="${levelcode eq 2012002}">
						<option value="1">新学期伊始的我</option>
						<option value="2">学期结束时的我</option>
						<option value="3">思想道德</option>
						<option value="4">学业成就</option>
						<option value="5">合作与交流</option>
						<option value="6">运动与健康</option>
						<option value="7">审美与表现</option>
						<option value="8">综合实践活动</option>
						<option value="9">个性发展</option>
						</c:if>
						<c:if test="${levelcode ne 2012002}">
						<option value="10">新学期伊始的我</option>
						<option value="20">学期结束时的我</option>
						<option value="30">思想道德</option>
						<option value="80">学业成就</option>
						<option value="40">合作与交流</option>
						<option value="50">运动与健康</option>
						<option value="60">审美与表现</option>
						<option value="90">综合实践活动</option>
						<option value="70">个性发展</option>
						</c:if>
				</select>
				</li>
				<li>二级栏目 <select name="secondItem" id="secondItem"
					class="selectionCls">
						<option value="">--全部--</option>
				</select>
				</li>
				<li style="float: right; margin-right: 0;"><input type="submit"
					value="查 询" class="button ml10" onclick="submitdata();"
					style="margin: auto;" /></li>
			</ul>
		</div>
	</div>
	<div id="bodyContent"
		style="position: absolute; left: 0; right: 0; bottom: 0; top: 80px;">
		<iframe src="empty.jsp" id="iframe" frameborder="0"
			style="width: 100%; height: 100%;"></iframe>
	</div>
	<%@ include file="/common/div.jsp"%>
</body>
<script type="text/javascript">
	rerender();
</script>
</html>
