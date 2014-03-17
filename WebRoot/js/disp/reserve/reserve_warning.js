$(function() {
	initScreen('040300');
	$('#searchBtn').click(function() {
		$('#list1').paging(1, 500);
	});
	$('#type').change(function() {
		$.sync(2, {execute:function() {
			$.sync(3);
		}});
	});
	$('#parentType').change(function() {
		$.sync(3);
	});
	$('#turnoverDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#turnoverDate').val(ymd);
		});
	});
})
function init() {
	$('#list1').paging(1, 500);
}