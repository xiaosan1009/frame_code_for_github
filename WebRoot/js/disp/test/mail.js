$(function() {
	initScreen('010009');
	$('#sendMailBtn').click(function() {
		$.sync('1');
	});
})
