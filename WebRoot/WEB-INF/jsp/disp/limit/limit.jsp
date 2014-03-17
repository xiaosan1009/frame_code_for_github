<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.limit.010005" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/limit/limit.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div id="header" style="margin: 10">
					<span style="font-size: 20">设置录入员权限</span>
				</div>
				<div id="list1">
				</div>
				<div class="formTr" style="margin: 5;">
					<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
