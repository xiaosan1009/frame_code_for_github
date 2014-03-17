<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title><fmt:message key="FS_0008" /></title>
		<jsp:include page="../../frame/inc_head.jsp" />
		<script type="text/javascript" src="js/disp/example/read_excel_make_list.js"></script>
	</head>

	<body class="contentBody">
		<form name="form1">
			<jsp:include page="../../frame/inc_body.jsp" />
			<div id="searchBar">
				<input id="id_0001" name="id_0001" type="file"/>
			</div>
			<div style="margin: 10px;clear: both;">
				<div id="list1" style="float: left;width: 170;height: 640;OVERFLOW: scroll; scrollbar-shadow-color: #FFFFFF; scrollbar-highlight-color: #FFFFFF; scrollbar-arrow-color: #FFFFFF; scrollbar-face-color: #59ADBB; scrollbar-track-color: #FFFFFF; scrollbar-3dlight-color: #59ADBB; scrollbar-darkshadow-color: #59ADBB">
				</div>
				<div id="list2" style="float: right;">
				</div>
			</div>
		</form>
	</body>
</html>
