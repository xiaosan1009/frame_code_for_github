$(function() {
	initScreen('010001');
	$('#username').focus();
	$('#submitBtn').click(function() {
		$.sync(1, {information:true, finish:function() {
//			$.closed();
		}});
	});
	$('#username').blur(function() {
		$('#username').single(2);
	});
})