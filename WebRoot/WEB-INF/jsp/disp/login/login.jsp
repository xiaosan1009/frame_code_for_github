<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.login.000001" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 用户登录【000001】 -->
		<title><fmt:message key="FS_title" /></title>
		<link href="css/disp/login/login.css" rel="stylesheet" type="text/css" />
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/login/login.js"></script>
	</head>
	<body class="contentBody" style="overflow-y:hidden;">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="loginBlock" class="login">
				<div class="loginFunc">
					<fmt:message key="FS_0005" />
				</div>
				<div>
					<div id="lfVerSelect" class="loginFormIpt">
					</div>
					<div class="loginFormIpt loginFormIptFirst">
						<span class="loginFormTh"><fmt:message key="FS_0001" /></span>
						<input class="loginFormTdIpt formTd" id="username" name="username" type="text" maxlength="50" />
					</div>
					<div class="loginFormIpt">
						<span class="loginFormTh"><fmt:message key="FS_0002" /></span>
						<input class="loginFormTdIpt formTd" id="password" name="password" type="password" />
					</div>
					<div id="lfAutoLogin" class="loginFormIpt loginFormIptWiotTh" style="margin-top: 9px; z-index: 2;">
					</div>
					<div class="loginFormIpt loginFormIptWiotTh" id="loginBtnArea">
						<a id="loginBtn" href="#" class="btn"><fmt:message key="FS_0003" /></a>
<!--						<button id="loginBtn" class="btn btn-login" class="formSubmit" tabindex="6" type="button">-->
<!--							<fmt:message key="FS_0003" />-->
<!--						</button>-->
					</div>
					<div class="loginFormIpt loginFormIptWiotTh" style="margin-top: 4px;">
					</div>
					<div class="ext" id="loginExt">
						<a id="testA">
							<fmt:message key="FS_0004" />
						</a>
					</div>
				</div>
			</div>
			
			<iframe src="html/glayer.html" class="loadingOnClass" name="glayoutFrame" id="glayoutFrame" scrolling="no"></iframe>
		</form>
	</body>
</html>