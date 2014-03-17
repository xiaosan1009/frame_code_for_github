$(function() {
	initScreen('010004');
})

function init() {
	$('#list1').paging('1');
	$('#list2').list('5');
	$('#searchBtn').click(function() {
//		$.sync('2', {finish:function(cmd, error) {
//			if (!error) {
//				$('#list1').paging('1');
//			}
//		}});
        $.synced(doTestSynced);
	});
//	$('#startDate').click(function() {
//		$.calendar(function(year, month, date, ymd) {
//			$('#startDate').val(ymd);
//		});
//	});
//	$('#endDate').click(function() {
//		$.calendar(function(year, month, date, ymd) {
//			$('#endDate').val(ymd);
//		});
//	});
	var dateS =new Date();
	dateS.setDate(dateS.getDate());
	var dateE =new Date();
	dateE.setDate(dateE.getDate());
	$("#startDate").datepicker({maxDate:dateS});
	$("#endDate").datepicker({maxDate:dateE});
}

function updateUserInfo(clientId) {
	var param = 'clientId=' + clientId + '&test=' + encodeURI('好的');
	$.dialog('010012', {data:param, closeFun:function(sett) {
		$('#list1').paging('1');
	}});
}

function deleteUserInfo(clientId) {
	$.del('3', clientId, {execute:function() {
		$('#list1').paging('1');
	}});
}
function doTestSynced() {
    $.synchro('2');
    $('#list1').pagingSync('1');
}