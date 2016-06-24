<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/cmis4_library.css" rel="stylesheet" type="text/css" />
<link href="../css/cmis4_global.css" rel="stylesheet" type="text/css" />
<link href="../css/cmis4_layout.css" rel="stylesheet" type="text/css" />
<link href="../css/cmis4_master.css" rel="stylesheet" type="text/css">
<title>通知公告列表</title>
</head>
<body>
<form action="${ctx}/homePage/HomePageAction.a" method="post">
<input type="hidden" id="moreflag" name="moreflag" value="${moreflag}"/>
     <div id="contanier">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <div id="main">
      <tr>
        <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2" valign="top" background="../images/bg_main_l.gif">&nbsp;</td>
              <td height="420" valign="top" style="background-image:url(../images/bg_table.jpg); background-position:bottom; background-position:right; background-repeat:no-repeat; "><div id="table">
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td width="23" height="14"><div align="left"><img src="../images/bg_mainpic_l_t.jpg" width="23" height="14"></div></td>
                      <td valign="top" background="../images/bg_mainpic_c.jpg">&nbsp;</td>
                      <td width="23"><div align="right"><img src="../images/bg_mainpic_r_t.jpg" width="23" height="14"></div></td>
                    </tr>
                    <tr>
                      <td height="212" background="../images/bg_mainpic_l_m.jpg">&nbsp;</td>
                      <td valign="top" background="../images/bg_mainpic_c_m.jpg"><table width="70%" border="0" align="center" cellpadding="2" cellspacing="2">
                          <tr>
                            <td height="16">&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="25"><img src="../images/pic_notice.jpg" width="179" height="39"></td>
                          </tr>
                        </table>
                        <table width="70%" border="0" align="center" cellpadding="9" cellspacing="9">
                          <tr>
                            <td width="81%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td bgcolor="#ff8a00"><img src="../images/line_table.gif" width="371" height="3"></td>
                                </tr>
                              </table>
                              <div class="spl3"></div>
                              <div id="weblist">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/bg_line.gif">
                                <c:forEach items="${pageObj.pageElements }" var="inform" varStatus="pVs">
                                  <tr>
                                    <td width="3%"><div align="left"><img src="../images/ico_fedbk.gif" /></div></td> 
                                    <td><div align="left"><a href="${ctx}/inform/InformAction.a?queryInformation&informIds=${inform.informid}">${inform.theme}</a>&nbsp;<c:if test="${inform.flag eq '-1' }"><img src="../images/ico_news2.gif" width="22" height="9" /></c:if></div></td>
                                    <td width="15%"><div align="right" class="date">${inform.startDate }</div></td>
                                  </tr>
                                </c:forEach>
                                </table>
                              </div>
                              <div id="weblistpage">
									<span><input id="pageNo" name="pageNo" value="${pageNo }"
										type="hidden" />
									<c:if test="${not empty pageObj}">
											<c:if test="${not empty pageObj.pageElements}">
												<jsp:include page="../common/pager-nest.jsp">
													<jsp:param name="toPage" value="1" />
													<jsp:param name="showCount" value="1" />
													<jsp:param name="action" value="queryInform" />
												</jsp:include>
											</c:if>
										</c:if></span>
								   </div>
                          </tr>
                        </table>
                        <p>&nbsp;</p></td>
                      <td background="../images/bg_mainpic_r_m.jpg">&nbsp;</td>
                    </tr>
                  </table>
                </div></td>
              <td width="2" valign="top" background="../images/bg_main_r.gif">&nbsp;</td>
            </tr>
          </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="44" valign="top"><img src="../images/bg_mainbot_l.gif" width="44" height="23" /></td>
              <td background="../images/bg_mainbot_c.gif">&nbsp;</td>
              <td width="30" valign="top"><img src="../images/bg_mainbot_r.gif" width="30" height="20" /></td>
            </tr>
          </table>
    </div>
    </form>
</body>
</html>