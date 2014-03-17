$(function() {
	initScreen('020602');
	$('#submitBtn').click(function() {
		actionSubmit();
	});
	$('#typeName').keyup(function(f) {
		if (f.keyCode == 13) {
			actionSubmit();
		}
	});
})
function init() {
	$.sync(1, {execute:function() {
		$('#typeName').focus();
		$('#typeName').select();
	}});
}
function actionSubmit() {
	$.sync(2, {finish:function(cmd, error) {
		if (!error) {
			$.closed();
		}
	}});
}