$(function() {
	initScreen('020701');
	$('#submitBtn').click(function() {
		$.sync(1, {finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
})