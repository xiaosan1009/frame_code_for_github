<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<jsp:include page="inc_head.jsp" />
		<style>
			#errArea {
				text-align: center;
				vertical-align: middle;
				color: red;
				font-weight: bold;
				font-size: xx-large;
				line-height: 50px;
				position: absolute;
				top: 40%;
				left: 0;
			}
		</style>
		<script type="text/javascript">
			function parseResponseDom(arg) {
				return $(arg)[0].childNodes; 
			}
			$(function() {
				var resAllDataDom = parseResponseDom($('#content').html());
				var resResCode;
				var resHeaderMsg;
				var headerDom;
				for (var i = 0; i < resAllDataDom.length; i++) {
				    if (resAllDataDom[i].nodeName == "HEADER") {
				    	headerDom = resAllDataDom[i];
				    	resHeaderMsg = headerDom.getAttributeNode("headerMsg").nodeValue;
					    break;
				    }
				}
				if(headerDom){
			    	resResCode = headerDom.getAttributeNode("resCode").nodeValue;
			    	if (resResCode == JK.RES_RESCODE_SESSION_TIMEOUT) {
				    	var dispCode = headerDom.getAttributeNode("dispCode").nodeValue;
			    		sessionTimeOutAction('0', dispCode);
			    	} else {
			    		var pageSize = $.pageSize();
						if (resHeaderMsg) {
							$('#errArea').html(resHeaderMsg);
						} else {
							$('#errArea').html(SYSTEM_ERROR);
						}
			    		if ($('#resCode').val() != '') {
			    			$('#errArea').css('left', 0);
			    			$('#errArea').css('top', 10);
			    			$('#errArea').css('font-size', 30);
			    			$('#errArea').width(390);
			    		} else {
				    		$('#errArea').width(pageSize[0] - 20);
			    		}
			    		$.loadingOff();
			    	} 
				}
				
				if($('#errArea').html() == ''){
					returnLogin();
				}
			});
			
			function returnLogin(){
				$.locationChange(SCREEN_LOGIN);
			}
		</script>
	</head>

	<body class="contentBody" id="content">
		<form name="form">
			<jsp:include page="inc_body.jsp" />
			<input type="hidden" name="resCode" id="resCode" value="${param.resCode}">
			<div id="errArea"></div>
		</form>
	</body>
</html>
