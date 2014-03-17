<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.maintain.030400" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 维修费用录入【030400】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/maintain/add_maintain_turnover.js"></script>
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
						<select id="filiale" name="filiale" class="formTd" style="font-size: 20">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="filiale" />
							</jsp:include>
						</select>
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0003" /></span>
						<input class="formTd" id="maintainCost" name="maintainCost" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<input class="formTd" id="maintainDate" name="maintainDate" type="text" readonly="readonly" />
					</div>
					<div class="formMesRight">
						<span class="formTh formMesTh">如果为空，默认当前系统日期</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh" style="vertical-align: top"><fmt:message key="FS_0004" /></span>
						<textarea rows="10" cols="25" id="maintainComment" name="maintainComment"></textarea>
					</div>
					<div class="formMesRight">
					</div>
				</div>
				
				<div class="formTr">
					<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
