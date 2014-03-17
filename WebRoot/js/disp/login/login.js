$(function() {
	initScreen('000001');
//	alert($('#testA').attr('href', 'www.baidu.com'));
	$('#username').focus();
//	$('#loginBtn').mouseover(function() {
//		$(this).removeClass();
//		$(this).addClass('btn btn-login-hover');
//	});
//	$('#loginBtn').mouseout(function() {
//		$(this).removeClass();
//		$(this).addClass('btn btn-login');
//	});
//	$('#loginBtn').mousedown(function() {
//		$(this).removeClass();
//		$(this).addClass('btn btn-login-active');
//	});
	$('#loginBtn').click(function() {
		$.sync(1);
	});
	$('#password').keyup(function(f) {
		if (f.keyCode == 13) {
			$.sync(1);
		}
	});
})
