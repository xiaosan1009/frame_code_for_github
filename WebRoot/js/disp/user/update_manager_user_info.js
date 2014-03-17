$(function() {
	initScreen('010011');
	$('#submitBtn').click(function() {
		$.sync(2, {finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
})
function init() {
	$.sync(1);
}

