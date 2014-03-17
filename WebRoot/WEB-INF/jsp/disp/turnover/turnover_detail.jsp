<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.filiale.010013" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_title" />
		</title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript"
			src="js/disp/turnover/turnover_detail.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<!-- 流水信息类型【入库】 -->
			<input type="hidden" name="turnoverType" id="turnoverType" value="0">
			<!-- 类型【原辅材料】 -->
			<input type="hidden" name="type" id="type" value="${param.type}">
			<input type="hidden" name="turnover_id" id="turnover_id" value="${param.turnover_id}">
			<div id="formMain">

				<div class="formTr">
					<span id="formHeader" class="formTh"><fmt:message key="FS_title" />
					</span>
				</div>

				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0001" />
						</span>
						<input type="text" class="formTd" name="orderno" readOnly id="orderno">
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" /></span>
						<select id="parentType" name="parentType" class="formTd" style="font-size: 20">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="parentType" />
							</jsp:include>
						</select>
					</div>
				</div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0002" />
						</span>
						<select id="childType" name="childType" class="formTd"
							style="font-size: 20">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="childType" />
							</jsp:include>
						</select>
					</div>
					<div class="formMesRight">
					</div>
				</div>

				<!-- 供货商 -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0003" />
						</span>
						<input class="formTd" id="supplier" name="supplier" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<!-- 数量 -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0004" />
						</span>
						<input class="formTd" id="turnoverNumber" name="turnoverNumber"
							type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<!-- 单 价（元） -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0005" />
						</span>
						<input class="formTd" id="price" name="price" type="text" />
					</div>
					<div class="formMesRight">
						<span class="formMesMst">※必须输入</span>
					</div>
				</div>
				
				<!-- 有效期 -->
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0006" />
						</span>
						<input class="formTd" id="effective_date" name="effective_date"
							type="text" readonly="readonly" />
					</div>
				</div>

				<div class="formTr">
					<input id="submitBtn" class="btn btn-submit" tabindex="6"
						type="button" value="<fmt:message key="FS_0007" />" />
				</div>
			</div>
		</form>
	</body>
</html>
