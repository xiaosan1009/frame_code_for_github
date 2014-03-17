<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.user.010001" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/user/add_manager_user_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<af:containerForm title="FS_title">
				<af:formInput focus="true" id="username" label="FS_0001" message="※必须输入" title="FS_0005"></af:formInput>
				<af:formPassword id="password" label="FS_0002" message="※必须输入" title="FS_0001"></af:formPassword>
				<af:formPassword id="repassword" label="FS_0003" message="※必须输入" title="FS_0006"></af:formPassword>
				<af:formSelect id="userType" label="FS_0004" message="※必须输入" title="FS_0006" style="font-size: 20;">
					<jsp:include page="../../frame/inc_cmb.jsp">
						<jsp:param name="targetId" value="userType" />
					</jsp:include>
				</af:formSelect>
				<af:formSubmit id="submitBtn" label="FS_0007" title="FS_0007"></af:formSubmit>
			</af:containerForm>
		</form>
	</body>
</html>
