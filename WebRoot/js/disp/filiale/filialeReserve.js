$(function() {
	initScreen('030100');
	$('#searchBtn').click(function() {
		$.sync('7', {finish:function(cmd, error) {
			if (!error) {
				$('#list1').paging('1', 500);
			}
		}});
	});
	$('#type').change(function() {
		// 原辅材料
		if (this.value == '0') {
			$.sync(2, {execute:function() {
				$.sync(3);
			}});
		} else if (this.value == '1') {
			$.sync(4, {execute:function() {
				$.sync(5);
			}});
		} else {
			$.sync(6, {execute:function() {
				$.sync(3);
			}});
		}
	});
	$('#parentType').change(function() {
		// 原辅材料
		if ($('#type').val() == '0') {
			$.sync(3);
		} else {
			$.sync(5);
		}
	});
	$('#turnoverDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#turnoverDate').val(ymd);
		});
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
   
	var settings = new $.Settings();
	
	settings.item = function (row, i, max){
        return row.supplier;
    };
	
    settings.result = function (row){
        return row.supplier;
    };
    
	$('#searchSupplier').auto('8','030100',settings);
})
function init() {
	$.sync('9');
//	$('#list1').paging(1, 500);
}