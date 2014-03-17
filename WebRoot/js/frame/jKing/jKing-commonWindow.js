// ********************************************
// commonWindow.js
// InternetExplorer上的按键控制
// ********************************************
$(function() {
	$("body").keydown(function(event){ 
		if (event.which == 91) {
			//, window.parent.document
			var startMenuObj = $(".startmenu");
			while (startMenuObj.length <= 0) {
				startMenuObj = $(".startmenu", window.parent.document);
			}
			startMenuObj.trigger('click');
			return false;
		}
	});
})

	/** 
	 * event设置
	 * onkeydown: 键盘按下时的方法设定	 * onmousedown: 鼠标按下时的方法设定	 * oncontextmenu: 鼠标右键按下时的菜单	 */
//	window.document.onkeydown=key_Down;
//	window.document.onmousedown=mouse_Down;		
			
	//window.document.oncontextmenu=disableContextMenu;
	document.oncontextmenu = showContextMenuCommon;
		
	/**
	 * 鼠标右键菜单禁止	 */
	function disableContextMenu(ev) {
		return false; 
	}

	/**
	 * 右键菜单
	 * @param ev 	 */
	function showContextMenuCommon(ev) {
		try {
			// 初期画面加载过程中，右键屏蔽			if (document.getElementById('dispcode') == undefined || $('#' + DISP_CODE_ID).val() == SCREEN_MENU) {
				return false;
			}
		
			// 右键菜单已经设置的时候，不再设置			if (window.event.srcElement.oncontextmenu != undefined) {
				return false;
			}
			
			// 如果点击项目为输入控件，不弹出菜单			if (window.event.srcElement.tagName == 'INPUT' && window.event.srcElement.readOnly == false) {
				return false;
			} else if (window.event.srcElement.tagName == 'SELECT') {
				return false;
			} else if (window.event.srcElement.tagName == 'TEXTAREA') {
				return false;
			// 图片的时候不弹出菜单
			} else if ( window.event.srcElement.tagName == 'IMG' ) {
				return false;
			}
			
			var menuHtml = "" ;
			var menuHeight = 7;
			
			// 菜单做成
			// 【打印】菜单做成
			menuHtml += "<div style=\"border-bottom:1px solid ThreeDDarkShadow; border-right:1px solid ThreeDDarkShadow; \"\n" ;
			menuHtml += "         oncontextmenu=\"return false\">\n" ;
			menuHtml += "<div style=\"border:1px solid ButtonShadow; padding:2px; background-color:Menu;\">\n" ;			
			
			menuHtml += "<div style=\"padding-top:1px; padding-left:15px; font-family:MS UI Gothic; color:MenuText; background-color:Menu; font-size:9pt; height:16px; cursor:default;\"\n" ;
			menuHtml += "     onmouseover=\"this.style.background='Highlight'; this.style.color='HighlightText'; \"\n" ;
			menuHtml += "     onmouseout=\"this.style.background='Menu'; this.style.color='MenuText'; \"\n" ;
			menuHtml += "     onmousedown=\"parent.print();\">\n" ;
			menuHtml += CONTEXT_MENU_PRINT + "\n" ;
			
			menuHeight += 16;
			
			menuHtml += "</div>\n";
			
			var objPopup = window.createPopup();
			
			objPopup.document.body.innerHTML = menuHtml;
			
			objPopup.show( (event.clientX + 2), (event.clientY + 2), CONTEXT_MENU_COMMONWIDTH, menuHeight, document.body );
			
		} catch (e) {
			alert(e.message);
			return false;
		}
		
		return false;
	}
	
	/**
	 * 键盘按键/鼠标操作
	 * 按键信息保存数组，对应的按键组合会被屏蔽	 */
	var keyInfo = new Array();
	var btnInfo = new Array();

	//      KeyCode	            Shift  Alt    Ctrl
	keyInfo['8']	= new Array(false, false, false);	// BACK   :BackSpace(別関数にて特例対応あり)
	keyInfo['37']   = new Array(false, true,  false);	// BACK   :ALT+←
	keyInfo['78']	= new Array(false, false, true);	// OPEN   :Ctrl+N
	keyInfo['82']	= new Array(false, false, true);	// RELOAD :Ctrl+R
	keyInfo['114']	= new Array(false, false, false);	// SEARCH :F3
//	keyInfo['116']	= new Array(false, false, false);	// RELOAD :F5
	keyInfo['117']	= new Array(false, false, false);	// FOCUS  :F6
//	keyInfo['122']	= new Array(false, false, false);	// MAXWIN :F11
	//      button	            Shift  Alt    Ctrl
	btnInfo['1']	= new Array(true, false, false);	// OPEN   :Shift+左	

	/**
	 * 键盘按键控制
	 * @param e	 * @return boolean 【true：允许，false：禁止】
	 */
	function key_Down(e) {
	    if (!submitCheck()) {
	       return false;
	    }
	    if (keyInfo[event.keyCode]) {
	        if (keyInfo[event.keyCode][0] == event.shiftKey
	            && keyInfo[event.keyCode][1] == event.altKey
	            && keyInfo[event.keyCode][2] == event.ctrlKey) {
	            if (key_Down_BackSpace(e)) {
	                return true;
	            }
	            if (!event.altKey) {	                event.keyCode = 0;
	            }
	            return false;
	        }
	    }
		// Ctrl + F5的控制	    if (event.ctrlKey == true && event.keyCode == '116') {
			return false;
	    }
	    
	    return true;
	}

	/**
	 * 鼠标的控制
	 * @param e 	 * @return boolean 【true：允许，false：禁止】
	 */
	function mouse_Down(e){
	    if (btnInfo[event.button]) {
	        if (btnInfo[event.button][0] == event.shiftKey
	            && btnInfo[event.button][1] == event.altKey
	            && btnInfo[event.button][2] == event.ctrlKey) {
	             return false;
	        }
	    }
	    return true;
	}

	/**
	 * 文本编辑时删除键可用
	 * 文本编辑以外删除键不可用
	 * @param e	 * @return boolean 【true：允许，false：禁止】
	 */
	function key_Down_BackSpace(e) {
	    var obj
	    
	    // BackSpace按下
	    if (event.keyCode == 8) {
	        if (window.event.srcElement.type == "text" ||
	            window.event.srcElement.type == "password" ||
	            window.event.srcElement.type == "textarea"){ 
	            if (window.event.srcElement.readOnly == true) {
	            	return false;
	            } else {		
	            	return true;
	            }
	        }
	    }
	    return false;
	}

	//----------------------
	// 回车键的控制
	//----------------------
	function submitCheck() {
	    var flg = true;
	    if (event.keyCode==13) {
	        flg = false
	        if (window.event.srcElement.type == 'button' || 
	            window.event.srcElement.type == 'submit' || 
	            window.event.srcElement.type == 'reset' || 
	            window.event.srcElement.type == 'textarea' ||
	            window.event.srcElement.type == '') {
	            flg = true;
	        }
	    }
	    return flg;
	}
	
	//----------------------
	// マウスホイール制御
	// 2008.10.27
	//----------------------
	function handle(delta) {
		// 向下滚动滑轮
        if (delta < 0) {
		// 向上滚动滑轮        } else {
        }
	}
 
	function wheel(event){
    	var delta = 0;
    	// For IE.
       	if (!event) {
         	event = window.event;
        }          
       	if (event.wheelDelta) {
       		delta = event.wheelDelta / 120;
       	}        
        
        /** If delta is nonzero, handle it.         
        * Basically, delta is now positive if wheel was scrolled up,         
        * and negative, if wheel was scrolled down.        
        */
        if (delta) {             
         	handle(delta);
        }

        if (event.preventDefault) {                
        	event.preventDefault();        
        }

       	event.returnValue = true;
    }
 
    if (window.addEventListener) {
    	window.addEventListener('DOMMouseScroll', wheel, false);
    }
//    window.onmousewheel = document.onmousewheel = wheel;
	
	