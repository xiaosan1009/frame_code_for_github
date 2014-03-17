<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.client.010004" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_0008" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/client/query_client_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<input type="hidden" name="test" id="test" value="好的">
			<div id="searchBar">
				<div id="searchBarLeft">
					<span class="searchBarLabel"><fmt:message key="FS_0006" /></span>
					<select id="clientSex" name="clientSex" class="searchBarWidgetMagin">
						<jsp:include page="../../frame/inc_cmb.jsp">
							<jsp:param name="targetId" value="clientSex" />
						</jsp:include>
					</select>
					<span class="searchBarLabel"><fmt:message key="FS_0010" /></span>
					<input id="startDate" name="startDate" type="text" class="searchBarWidgetMagin"/>
					<span class="searchBarLabel"><fmt:message key="FS_0011" /></span>
					<input id="endDate" name="endDate" type="text"/>
				</div>
				<div id="searchBarRight">
					<input type="button" id="searchBtn" class="btn btn-submit" tabindex="6" value="<fmt:message key="FS_0007" />"/>
				</div>
			</div>
			<div class="phonemoneyshop" style="margin: 10px;clear: both;">
				<div id="list1">
				</div>
				<div style="margin-top: 20px;">
					<jsp:include page="../../frame/inc_page.jsp" />
				</div>
				<div id="list2">
				</div>
			</div>
		</form>
	</body>
</html>
