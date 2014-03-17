<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.user.010011" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 修改管理用户信息【010011】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/user/update_manager_user_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<input type="hidden" name="userId" id="userId" value="32131">
			<div id="formMain">
				
				<div class="formTr">
					<span id="formHeader" class="formTh"><fmt:message key="FS_title" /></span>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0001" /></span>
						<input class="formTd" id="username" name="username" type="text" readonly="readonly" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
			
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<input class="formTd focusItem" id="password" name="password" type="password" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
			
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0003" /></span>
						<input class="formTd" id="repassword" name="repassword" type="password" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
			
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0004" /></span>
						<select id="userType" name="userType" class="formTd" style="font-size: 20">
						<jsp:include page="../../frame/inc_cmb.jsp">
							<jsp:param name="targetId" value="userType" />
						</jsp:include>
					</select>
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
			
				<div class="formTr">
					<button id="submitBtn" class="btn btn-submit" tabindex="6" type="button">
						<fmt:message key="FS_0007" />
					</button>
				</div>
			</div>
		</form>
	</body>
</html>
