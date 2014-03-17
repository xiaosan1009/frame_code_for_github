$(function() {
	initScreen('020601');
	$('#typeName').focus();
	$('#submitBtn').click(function() {
		actionSubmit();
	});
	$('#typeName').keyup(function(f) {
		if (f.keyCode == 13) {
			actionSubmit();
		}
	});
})
function actionSubmit() {
	$.sync(1, {finish:function(cmd, error) {
		if (!error) {
			$.closed();
		}
	}});
}