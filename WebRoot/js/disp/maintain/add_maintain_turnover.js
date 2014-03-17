$(function() {
	initScreen('030400');
	$('#filiale').focus();
	$('#submitBtn').click(function() {
		$.sync(1, {finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
	$('#maintainDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#maintainDate').val(ymd);
		});
	});
})
