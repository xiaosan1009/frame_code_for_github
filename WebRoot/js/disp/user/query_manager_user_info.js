$(function() {
	initScreen('010002');
	$('#searchBtn').click(function() {
		$.sync('2', {finish:function(cmd, error) {
			if (!error) {
				$('#list1').paging('1');
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

//function init() {
//	$('#list1').paging('1');
//}

function updateUserInfo(userId) {
	var param = 'userId=' + userId;
	$.dialog('010011', {data:param,title:'test tile',closeFun:function() {
		$('#list1').paging('1');
	}});
}

function deleteUserInfo(userId) {
	$.del('3', userId, {execute:function() {
		$('#list1').paging('1');
	}});
}