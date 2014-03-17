$(function() {
	initScreen('050100');
	$('#count').focus();
	$('#count').select();
	$('#submitBtn').click(function() {
		if ($('#id').val() != null && $('#id').val() != '') {
			$.sync(3, {execute:function() {
				$.closed();
			}});
		} else {
			$.sync(1, {execute:function() {
				$.sync(2, {execute:function() {
					$.closed();
				}});
			}});
		}
	});
})
function init() {
	$.sync(2);
}