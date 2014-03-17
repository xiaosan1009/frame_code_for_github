<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.client.010004" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_0008" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/multiple/query_multiple_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div style="margin: 10px;clear: both;">
				<div id="list1">
				</div>
			</div>
		</form>
	</body>
</html>
