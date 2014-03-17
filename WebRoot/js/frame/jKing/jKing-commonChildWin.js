// ********************************************
// commonChildWin.js

// 2010.10.02作成
// ********************************************

	/**
	 * 编辑过的画面关闭时，提示警告
	 */
	window.onbeforeunload = function(event) {				
		if (onChangeFlag == true) {
			event = event || window.event;
			event.returnValue = WINDOW_CLOSE_MESSAGE;
		}
	}
	
	/**
	 * 超时处理
	 */
	function sessionTimeOutAction() {
		document.forms[0].method="post";
		document.forms[0].action = "err.html";
		document.forms[0].submit();
	}