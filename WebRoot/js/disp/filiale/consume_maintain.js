$(function() {
	initScreen('030500');
	$('#searchBtn').click(function() {
		$.sync('2', {finish:function(cmd, error) {
			if (!error) {
				showList();
			}
		}});
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
})
function init() {
	showList();
}

function showList() {
	$('#list1').paging(1);
}