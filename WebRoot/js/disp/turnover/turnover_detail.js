$(function() {
	initScreen('010013');
	$('#submitBtn').click(function() {
		$.sync(4, {finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
	
	$('#parentType').change(function() {
		if ($('#type').val() == '0') {
			$.sync(2);
		} else {
			$.sync(3);
		}
	});
	
	$('#effective_date').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#effective_date').val(ymd);
		});
	});
})

function init(){
	$.sync('1');
}

