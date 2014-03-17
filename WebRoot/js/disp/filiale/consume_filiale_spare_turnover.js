$(function() {
	initScreen('030300');
	$('#filiale').focus();
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
		$.sync('4', {finish:function(cmd, error) {
			if (!error) {
				showList1();
			}
		}});
	});
	
	$('#parentType').change(function() {
		$.sync(2);
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
	
	$('#searchSupplier').auto('5','030300', {item : function (row, i, max){
        return row.supplier;
    }, result : function (row){
        return row.supplier;
    }});
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