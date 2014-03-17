<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.decimal.050100" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 小数点位数设定【050100】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/decimal/set_decimal_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div class="formTr">
					<span id="formHeader" class="formTh"><fmt:message key="FS_title" /></span>
				</div>
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0001" /></span>
						<input class="formTd" id="count" name="count" type="text" />
						<input id="id" name="id" type="hidden" />
					</div>
					<div class="formMesRight">
						<span class="formTh formMesTh">数量的小数点位数，默认为0</span>
					</div>
				</div>
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<input class="formTd" id="price" name="price" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formTh formMesTh">单价的小数点位数，默认为0</span>
					</div>
				</div>
				<div class="formTr">
					<input id="submitBtn" class="btn btn-submit" type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
