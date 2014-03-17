<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.materialType.020500" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<link href="css/disp/materialType/materialType.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/disp/materialType/materialType.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<input type="hidden" id="parentId" name="parentId">
			<div style="margin-top: 10px;">
				<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0007" />" />
			</div>
			<div style="margin-top: 10px;width: 900px;">
				<div id="list1" style="float: left;width: 200px">
				</div>
				<div id="list2" style="float:right;border: 1px solid #FF0000;width: 690px;height: 100%;white-space: nowrap;display: none;">
				</div>
			</div>
		</form>
	</body>
</html>
