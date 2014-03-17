$(function() {
	initScreen('020702');
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

