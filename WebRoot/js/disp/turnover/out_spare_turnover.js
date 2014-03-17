$(function() {
	initScreen('020400');
	$('#parentType').focus();
	$('#submitBtn').click(function() {
		$.sync(1, {execute:function(cmd, error) {
			if (!error) {
				showList1();
			}
		},
		finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
	$('#searchBtn').click(function() {
		showList1();
	});
	$('#parentType').change(function() {
		$.sync(2);
	});
	$('#startDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#startDate').val(ymd);
		});
	});
	$('#endDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#endDate').val(ymd);
		});
	});
	$('#turnoverDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#turnoverDate').val(ymd);
		});
	});
})
function init() {
	showList1();
}
function showList1() {
	$('#list1').list(3, {execute:function() {
		$('#checkAll').click(function() {
			$(':checkbox[name="indexs"]').attr('checked', this.checked);
		});
	}});
}