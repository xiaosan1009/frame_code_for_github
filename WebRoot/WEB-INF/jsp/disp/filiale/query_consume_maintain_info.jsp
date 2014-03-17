<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.filiale.030500" scope="page" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 消耗及维修查询【030500】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/filiale/consume_maintain.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div id="searchBar">
					<div id="searchBarLeft">
						<!-- 类型 -->
						<span class="searchBarLabel"><fmt:message key="FS_0004" /></span>
						<select id="type" name="type" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="type" />
							</jsp:include>
						</select>
					
						<!-- 分厂 -->
						<span class="searchBarLabel"><fmt:message key="FS_0001" /></span>
						<select id="filiale" name="filiale" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="filiale" />
							</jsp:include>
						</select>
					
						<!-- 查询类型 -->
						<span class="searchBarLabel"><fmt:message key="FS_0005" /></span>
						<select id="searchType" name="searchType" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="searchType" />
							</jsp:include>
						</select>
						
						<!-- 日期 -->
						<span class="searchBarLabel"><fmt:message key="FS_0018" /></span>
						<input id="startDate" name="startDate" type="text" class="searchBarWidgetMagin" readonly="readonly"/>
						<span class="searchBarLabel"><fmt:message key="FS_0019" /></span>
						<input id="endDate" name="endDate" type="text" readonly="readonly"/>
					</div>
					<div id="searchBarRight">
						<input type="button" id="searchBtn" class="btn btn-submit" value="<fmt:message key="FS_0008" />"/>
					</div>
				</div>
				<div id="list1" class="formBottom"></div>
				<div style="margin-top: 20px;">
					<af:pagingDisp></af:pagingDisp>
				</div>
			</div>
		</form>
	</body>
</html>
