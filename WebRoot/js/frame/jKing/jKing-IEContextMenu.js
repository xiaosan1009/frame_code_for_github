/*****************************************************************************
 * 
 *
 * <版本支持>
 * ・IE6.0、IE7.0 *
 *****************************************************************************/
 
/******************************************************************************
 * title	:IE鼠标右键菜单 JavaScript
 * filename	:IEContextMenu.js
 * date		:2010.04.01
 * update	:
 ******************************************************************************/
// 右键菜单关联
document.oncontextmenu = showContextMenu ;
var CONTEXT_MENU_WIDTH = 0;
var CONTEXT_MENU_ITEM_NAME = new Array() ;
var CONTEXT_MENU_ACTION = new Array() ;

/******************************************************************************
 * function name:右键菜单关联方法
 * input		:none
 * output		:none
 ******************************************************************************/
function showContextMenu()
{
	var menuHtml = "" ;
	var menuHeight = 7 ;
	
	menuHtml += "<div style=\"border-bottom:1px solid ThreeDDarkShadow; border-right:1px solid ThreeDDarkShadow; \"\n" ;
	menuHtml += "         oncontextmenu=\"return false\">\n" ;
	menuHtml += "<div style=\"border:1px solid ButtonShadow; padding:2px; background-color:Menu;\">\n" ;

	for (count = 0; count < CONTEXT_MENU_ITEM_NAME.length; count++) {
		if ((CONTEXT_MENU_ITEM_NAME[count] != "") && (CONTEXT_MENU_ACTION[count] != "")) {
			// 【有效菜单】
			menuHtml += "<div style=\"padding-top:1px; padding-left:15px; font-family:MS UI Gothic; color:MenuText; " +
			"background-color:Menu; font-size:9pt; height:16px; cursor:default;\"\n" ;
			menuHtml += "     onmouseover=\"this.style.background='Highlight'; this.style.color='HighlightText'; \"\n" ;
			menuHtml += "     onmouseout=\"this.style.background='Menu'; this.style.color='MenuText'; \"\n" ;
			menuHtml += "     onmousedown=\"parent." + CONTEXT_MENU_ACTION[count] + "\">\n" ;
			menuHtml += CONTEXT_MENU_ITEM_NAME[count] + "\n" ;
			menuHtml += "</div>\n" ;
			menuHeight += 16 ;
		} else if ((CONTEXT_MENU_ITEM_NAME[count] != "" ) && ( CONTEXT_MENU_ACTION[count] == "" ) ) {
			// 【无效菜单】			menuHtml += "<div style=\"padding-top:1px; padding-left:15px; font-family:MS UI Gothic; color:GrayText; " +
			"background-color:Menu; font-size:9pt; height:16px; cursor:default;\"\n" ;
			menuHtml += "     onmouseover=\"this.style.background='Highlight'; this.style.color='GrayText'; \"\n" ;
			menuHtml += "     onmouseout=\"this.style.background='Menu'; this.style.color='GrayText'; \">\n" ;
			menuHtml += CONTEXT_MENU_ITEM_NAME[count] + "\n" ;
			menuHtml += "</div>\n" ;
			menuHeight += 16 ;
		} else {
			menuHtml += "    <div style=\"margin-left:1px; margin-right:1px; border-bottom:1px solid ButtonShadow; background-color:Menu; " +
			"font-size:1pt; height:4px; cursor:default;\"></div>\n" ;
			menuHtml += "    <div style=\"margin-left:1px; margin-right:1px; color:ButtonShadow; background-color:Menu; " +
			"font-size:1pt; height:5px; cursor:default;\"></div>\n" ;
			menuHeight += 9 ;
		}
	}
	menuHtml += "</div>\n" ;

	var objPopup = window.createPopup() ;

	objPopup.document.body.innerHTML = menuHtml ;
	objPopup.show((event.clientX + 2), (event.clientY + 2), CONTEXT_MENU_WIDTH, menuHeight, document.body) ;

	return true ;
}

function showContextMenuPlus(name, width, dispcode, type, value, winWidth, winHeight, winLeft, winTop) {
	var str = "";
	if (window.opener == undefined || window.opener == null) {
		str = "window.top.";
	} else {
		str = "window.opener.top.";
	}

	var menuHtml = "" ;
	var menuHeight = 7 ;

	menuHtml += "<div style=\"border-bottom:1px solid ThreeDDarkShadow; border-right:1px solid ThreeDDarkShadow; \"\n" ;
	menuHtml += "         oncontextmenu=\"return false\">\n" ;
	menuHtml += "<div style=\"border:1px solid ButtonShadow; padding:2px; background-color:Menu;\">\n" ;			
	
	menuHtml += "<div style=\"padding-top:1px; padding-left:15px; font-family:MS UI Gothic; color:MenuText; background-color:Menu; font-size:9pt; height:16px; cursor:default;\"\n" ;
	menuHtml += "     onmouseover=\"this.style.background='Highlight'; this.style.color='HighlightText'; \"\n" ;
	menuHtml += "     onmouseout=\"this.style.background='Menu'; this.style.color='MenuText'; \"\n" ;
	menuHtml += "     onmousedown=\"parent." + str + "showChildWindow('" + dispcode + "','"+ type + "'," + value + "," + winLeft + "," + winTop + "," + winWidth + "," + winHeight + ")\">\n" ;
	menuHtml += name + "\n" ;
	menuHtml += "</div>\n" ;
	menuHeight += 16 ;
	
	menuHtml += "</div>\n" ;
	
    var objPopup = window.createPopup();
    
    objPopup.document.body.innerHTML = menuHtml;
    
    objPopup.show( (event.clientX + 2), (event.clientY + 2), width, menuHeight, document.body );
    
    return (false);
}
