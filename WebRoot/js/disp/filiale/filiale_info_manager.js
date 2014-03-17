$(function() {
	initScreen('020700');
})

function init() {
	$('#list1').paging('1');
	$('#searchBtn').click(function() {
		$.dialog('020701', {closeFun:function() {
			$('#list1').paging('1');
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
}

function updateFilialeInfo(filialeId) {
	var param = 'filialeId=' + filialeId;
	$.dialog('020702', {data:param, closeFun:function() {
		$('#list1').paging('1');
	}});
}

function deleteFilialeInfo(filialeId) {
	$.del('3', filialeId, {execute:function() {
		$('#list1').paging('1');
	}});
}