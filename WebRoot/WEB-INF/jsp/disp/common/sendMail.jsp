<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>邮件发送</title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/test/mail.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<ul>
				<li>
					<input type="text" id="content" name="content">
				</li>
				<li>
					<input type="text" id="toAddress" name="toAddress">
				</li>
				<li>
					<a id="sendMailBtn" class="btn">发送邮件</a><br>
				</li>
			</ul>
		</form>
	</body>
</html>
