$(function() {
	initScreen('010012');
	$('#submitBtn').click(function() {
		$.sync(2, {finish:function(data) {
			if (!data.error) {
				$.closed({"a":"test"});
			}
		}});
	});
})
function init() {
	var sett = new $.Settings();
	sett.fordatas = function(data) {
		if (!data.error) {
			alert(data.datas.CLIENT_SEX);
		}
	};
	$.sync(1, sett);
}

