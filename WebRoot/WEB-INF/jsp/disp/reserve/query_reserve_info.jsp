<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<fmt:setBundle basename="cn.smeltery.resource.reserve.020800" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!-- 库存查询【020800】 -->
		<title><fmt:message key="FS_title" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/reserve/reserve.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="formMain">
				<div id="searchBar">
					<div id="searchBarLeft">
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
						<span class="searchBarLabel"><fmt:message key="FS_0015" /></span>
						<input id="startDate" name="startDate" type="text" style="width:70px" class="searchBarWidgetMagin" readonly="readonly"/>
						<span class="searchBarLabel"><fmt:message key="FS_0016" /></span>
						<input id="endDate" name="endDate" style="width:70px" type="text" readonly="readonly"/>
						
						<!-- 供货商 -->
						<span class="searchBarLabel" style="margin-left: 10px"><fmt:message key="FS_0018" /></span>
						<input id="searchSupplier" name="searchSupplier" type="text"/>
					</div>
					<div id="searchBarRight">
						<input type="button" id="searchBtn" class="btn btn-submit" value="<fmt:message key="FS_0008" />"/>
						<input type="button" id="excelBtn" class="btn btn-submit" value="<fmt:message key="FS_0017" />"/>
					</div>
				</div>
				<div id="list1" class="formBottom" style="width:1113px;margin: 0 auto;">
				</div>
				<af:pagingDisp></af:pagingDisp>
			</div>
		</form>
	</body>
</html>
