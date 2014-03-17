function showInformationMessage(resCode) {
	$.dialog('000004', {data:'resCode=' + resCode + '&msgDispCode=' + $('#dispcode').val()});
}
function otherUserLoginAction() {
    $.dialog('000004', {data:'resCode=' + RES_RESCODE_OTHER_USER_LOGIN, 
    closeBtnFun:function() {
        $.loadingOn();
        $.locationChange(SCREEN_LOGIN);
    }});
}