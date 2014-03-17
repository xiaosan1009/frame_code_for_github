<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.turnover.020900" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 库存流水查询【020900】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/turnover/query_turnover_info.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div id="searchBar">
					<div id="searchBarLeft">
						<!-- 状态 -->
						<span class="searchBarLabel"><fmt:message key="FS_0016" /></span>
						<select id="status" name="status" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="status" />
							</jsp:include>
						</select>
						
						<!-- 类型 -->
						<span class="searchBarLabel"><fmt:message key="FS_0013" /></span>
						<select id="type" name="type" class="searchBarWidgetMagin">
							<jsp:include page="../../frame/inc_cmb.jsp">
								<jsp:param name="targetId" value="type" />
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
						<span class="searchBarLabel"><fmt:message key="FS_0018" /></span>
						<input id="startDate" name="startDate" type="text" class="searchBarWidgetMagin" style="width:80px" readonly="readonly"/>
						<span class="searchBarLabel"><fmt:message key="FS_0019" /></span>
						<input id="endDate" name="endDate" type="text" readonly="readonly" style="width:80px"/>
						
						<!-- 供货商 -->
						<span class="searchBarLabel" style="margin-left: 10px"><fmt:message key="FS_0020" /></span>
						<input id="searchSupplier" name="searchSupplier" style="width:100px" type="text"/>
					</div>
					<div id="searchBarRight">
						<input type="button" id="searchBtn" class="btn btn-submit" value="<fmt:message key="FS_0008" />"/>
					</div>
				</div>
				<div id="list1" class="formBottom" style="text-align: left;width:1030px"></div>
				<af:pagingDisp></af:pagingDisp>
			</div>
		</form>
	</body>
</html>
