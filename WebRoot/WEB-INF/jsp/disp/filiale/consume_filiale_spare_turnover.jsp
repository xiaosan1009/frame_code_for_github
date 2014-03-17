<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.filiale.030300" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 备品备件消耗录入【030300】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/filiale/consume_filiale_spare_turnover.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<!-- 类型【备品备件】 -->
			<input type="hidden" name="type" id="type" value="1">
			<div id="formMain">
				<div id="searchBar">
					<div id="searchBarLeft">
						<!-- 分厂 -->
						<span class="searchBarLabel"><fmt:message key="FS_0010" /></span>
						<select id="filiale" name="filiale" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="filiale" />
							</jsp:include>
						</select>
						
						<!-- 大分类 -->
						<span class="searchBarLabel"><fmt:message key="FS_0001" /></span>
						<select id="parentType" name="parentType" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="parentType" />
							</jsp:include>
						</select>
					
						<!-- 小分类 -->
						<span class="searchBarLabel"><fmt:message key="FS_0002" /></span>
						<select id="childType" name="childType" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="childType" />
							</jsp:include>
						</select>
						
						<!-- 日期 -->
						<span class="searchBarLabel"><fmt:message key="FS_0017" /></span>
						<input id="startDate" name="startDate" type="text" class="searchBarWidgetMagin" style="width:100" readonly="readonly"/>
						<span class="searchBarLabel"><fmt:message key="FS_0018" /></span>
						<input id="endDate" name="endDate" type="text" style="width:100" readonly="readonly"/>
						
						<!-- 供货商 -->
						<span class="searchBarLabel" style="margin-left: 10px"><fmt:message key="FS_0019" /></span>
						<input id="searchSupplier" name="searchSupplier" style="width:120" type="text"/>
					</div>
					<div id="searchBarRight">
						<input type="button" id="searchBtn" class="btn btn-submit" value="<fmt:message key="FS_0008" />"/>
					</div>
				</div>
				
				<div id="list1" class="formBottom" style="text-align: left;width:1030px"></div>
				
				<div class="formTr formMesTr">
					<div class="formMesLeft">
						<span class="formTh"><fmt:message key="FS_0012" /></span>
						<input class="formTd" id="turnoverDate" name="turnoverDate" type="text" readonly="readonly" />
					</div>
					<div class="formMesRight">
						<span class="formTh formMesTh">如果为空，默认当前系统日期</span>
					</div>
				</div>
				
				<div class="formTr">
					<input id="submitBtn" class="btn btn-submit" tabindex="6" type="button" value="<fmt:message key="FS_0015" />" />
				</div>
			</div>
		</form>
	</body>
</html>
