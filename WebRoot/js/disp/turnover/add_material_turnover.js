$(function() {
	initScreen('020100');
	$('#parentType').focus();
	$('#submitBtn').click(function() {
		$.sync(1, {finish:function(cmd, error) {
			if (!error) {
				$.closed();
			}
		}});
	});
	$('#parentType').change(function() {
		$('#parentType').single('2');
	});
	$('#turnoverDate').click(function() {
		$.calendar(function(year, month, date, ymd) {
			$('#turnoverDate').val(ymd);
		});
	});
	$('#supplier').auto('8','020900', {item : function (row, i, max){
        return row.supplier;
    }, result : function (row){
        return row.supplier;
    }});
})
