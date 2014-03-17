$(function() {
	initScreen('020300');
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
	$('#searchSupplier').auto('8','020900', {item : function (row, i, max){
        return row.supplier;
    }, result : function (row){
        return row.supplier;
    }});
})
function init() {
	showList1();
}
function showList1() {
	$('#list1').list(3, {finish:function() {
		$('#checkAll').click(function() {
			$(':checkbox[name="indexs"]').attr('checked', this.checked);
		});
	}});
}
