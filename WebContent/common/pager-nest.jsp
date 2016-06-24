<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:set var="act" value="gotoPage" />
<c:if test="${not empty param.action}">
	<c:set var="act" value="${param.action}" />
</c:if>
<c:if test="${param.showCount == 1}">
	<fmt:message key="pager.pageCount">
		<fmt:param>${pageObj.totalCount}</fmt:param>
		<fmt:param>${pageObj.currPageNumber}</fmt:param>
		<fmt:param>${pageObj.lastPageNumber}</fmt:param>
	</fmt:message>
</c:if>
<c:choose>
	<c:when test="${pageObj.currPageNumber == pageObj.firstPageNumber}">
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="button"
			value='<fmt:message key="pager.firstPage"/>' disabled="disabled" />
	</c:when>
	<c:otherwise>
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="submit"
			name="${act}"
			onclick="this.form.pageNo.value=${pageObj.firstPageNumber};"
			value='<fmt:message key="pager.firstPage"/>' />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${pageObj.currPageNumber <= pageObj.firstPageNumber}">
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="button"
			value='<fmt:message key="pager.previousPage"/>' disabled="disabled" />
	</c:when>
	<c:otherwise>
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="submit"
			name="${act}"
			onclick="this.form.pageNo.value=${pageObj.previousPageNumber };"
			value='<fmt:message key="pager.previousPage"/>' />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${pageObj.currPageNumber >= pageObj.lastPageNumber}">
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="button"
			value='<fmt:message key="pager.nextPage"/>' disabled="disabled" />
	</c:when>
	<c:otherwise>
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="submit"
			name="${act}"
			onclick="this.form.pageNo.value=${pageObj.nextPageNumber };"
			value='<fmt:message key="pager.nextPage"/>' />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${pageObj.currPageNumber == pageObj.lastPageNumber}">
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="button"
			value='<fmt:message key="pager.lastPage"/>' disabled="disabled" />
	</c:when>
	<c:otherwise>
		<input class=btnpage_mouseout
			onmouseover="this.className='btnpage_mouseover'"
			onmouseout="this.className='btnpage_mouseout'" type="submit"
			name="${act}"
			onclick="this.form.pageNo.value=${pageObj.lastPageNumber };"
			value='<fmt:message key="pager.lastPage"/>' />
	</c:otherwise>
</c:choose>

<c:if test="${param.toPage == 1}">
	<input id="toPage" class=btnpage_mouseout
		onmouseover="this.className='btnpage_mouseover'"
		onmouseout="this.className='btnpage_mouseout'" type="submit"
		name="${act}"
		onclick="this.form.pageNo.value=this.form._toPageNo.value;"
		value='<fmt:message key="pager.pageNo"/>' />
	<input type="text" size="2" name="_toPageNo"
		style="width:25px;"
		value="${pageObj.currPageNumber }" 
		onblur="toPage.click();" />
每页显示<input type="text" name="pageSize" id="pageSize"
			style="width:25px;"
		<c:if test="${pageSize eq 0 }">value="1"</c:if>
		<c:if test="${pageSize ne 0 }">value="${pageSize }"</c:if>
		maxlength="6" size="3"
		onkeydown="if (event.keyCode==13) {toPage.click(); return false;}" />条记录．
</c:if>