<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.materialType.020502" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/materialType/update_material_type_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<input type="hidden" name="typeId" id="typeId" value="${param.typeId}">
			<div id="formMain">
				<div class="formTr">
					<span class="formTh" style="width: 120px;"><fmt:message key="FS_0001" /></span>
					<input class="formTd focusItem" id="typeName" name="typeName" type="text" />
				</div>
				<div class="formTr">
					<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
