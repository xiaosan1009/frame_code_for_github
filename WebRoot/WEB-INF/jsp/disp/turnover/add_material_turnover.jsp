<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.turnover.020100" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 原辅材料入库【020100】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/turnover/add_material_turnover.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<!-- 流水信息类型【入库】 -->
			<input type="hidden" name="turnoverType" id="turnoverType" value="0">
			<!-- 类型【原辅材料】 -->
			<input type="hidden" name="type" id="type" value="0">
			<div id="formMain">
			
				<div class="formTr">
					<span id="formHeader" class="formTh"><fmt:message key="FS_title" /></span>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0001" /></span>
						<select id="parentType" name="parentType" class="formTd" style="font-size: 20">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="parentType" />
							</jsp:include>
						</select>
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0001" /></span>
						<select id="childType" name="childType" class="formTd" style="font-size: 20"> 
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="childType" />
							</jsp:include>
						</select>
					</div>
					<div class="formMesRight">
					</div>
				</div>
				
				<!-- 库单号 -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0008" /></span>
						<input class="formTd" id="orderNo" name="orderNo" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<!-- 供货商 -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0009" /></span>
						<input class="formTd" id="supplier" name="supplier" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<input class="formTd" id="turnoverNumber" name="turnoverNumber" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0005" /></span>
						<input class="formTd" id="price" name="price" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0004" /></span>
						<input class="formTd" id="turnoverDate" name="turnoverDate" type="text" readonly="readonly" />
					</div>
					<div class="formMesRight">
						<span class="formTh formMesTh">如果为空，默认当前系统日期</span>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh" style="vertical-align: top"><fmt:message key="FS_0003" /></span>
						<textarea rows="10" cols="25" id="turnoverComment" name="turnoverComment"></textarea>
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
