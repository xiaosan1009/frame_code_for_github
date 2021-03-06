$(function() {
    initScreen('020800');
    $('#searchBtn').click(function() {
        $.sync('7', {finish:function(cmd, error) {
            if (!error) {
                $('#list1').paging('1', 500);
            }
        }});
    });
    $('#excelBtn').click(function() {
        $.excel('8');
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
    
    $('#searchSupplier').auto('9','020800', {item : function (row, i, max){
        return row.supplier;
    }, result : function (row){
        return row.supplier;
    }});
})
function init() {
	$('#list1').paging(1, 500);
}

