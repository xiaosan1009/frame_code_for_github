<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.filiale.020700" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<link href="css/disp/user/query_manager_user_info.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/disp/filiale/filiale_info_manager.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div style="padding: 5px;margin-top: 20px;text-align: center;">
				<input type="button" id="searchBtn" class="btn btn-submit" value="<fmt:message key="FS_0007" />"/>
			</div>
			<div class="phonemoneyshop" style="margin: 10px;clear: both;">
				<div id="list1">
				</div>
				<div style="margin-top: 20px;">
					<af:pagingDisp></af:pagingDisp>
				</div>
			</div>
		</form>
	</body>
</html>
