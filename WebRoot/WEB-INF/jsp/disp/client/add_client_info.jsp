<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.client.010003" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<link rel="stylesheet" type="text/css" href="css/frame/smoothness/jquery-ui-1.8.9.custom.css" />	
		<link rel="stylesheet" type="text/css" href="css/frame/fileinput.css" />	
		<script type="text/javascript" src="js/frame/jquery-ui-1.8.9.custom.min.js"></script>
		<script type="text/javascript" src="js/frame/jquery.fileinput.js"></script>
		<script type="text/javascript" src="js/disp/client/add_client_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div class="formTr">
					<span id="formHeader" class="formTh"><fmt:message key="FS_title" /></span>
				</div>
				
				<af:formInput focus="true" id="clientName" label="aaa" message="※必须输入" title="bbb"></af:formInput>
<!--				<div class="formTr formMesTr">-->
<!--					<div class="formMesLeft">-->
<!--						<span class="formTh"><fmt:message key="FS_0001" /></span>-->
<!--						<input class="formTd focusItem" title="<fmt:message key="FS_0006" />" id="clientName" name="clientName" type="text" />-->
<!--					</div>-->
<!--					<div class="formMesRight">-->
<!--						<span class="formMesMst">※必须输入</span>-->
<!--					</div>-->
<!--				</div>-->
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<input class="formTd" id="clientTel" name="clientTel" type="text" />
					</div>
					<div class="formMesRight">
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0003" /></span>
						<input class="formTd" id="clientFax" name="clientFax" type="text" />
					</div>
					<div class="formMesRight">
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0004" /></span>
						<input class="formTd" id="clientMail" name="clientMail" type="text" />
					</div>
					<div class="formMesRight">
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0005" /></span>
						<select id="clientSex" name="clientSex" class="formTd" style="font-size: 20">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="clientSex" />
							</jsp:include>
						</select>
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr">
					<input id="id_0001" name="id_0001" type="file"/>
					<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
