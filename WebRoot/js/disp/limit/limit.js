$(function() {
	initScreen('010005');
	$('#list1').list(1, {execute:function() {
		$(':checkbox').click(function() {
			if (this.name == 'first') {
				$(this).parent().parent().children().children('div').children().children(':checkbox').attr('checked', this.checked);
			} else {
				var checkedFlg = false;
				$(this).parent().parent().children().each(function() {
					if ($(this).children(':checkbox').attr('checked')) {
						checkedFlg = true;
					}
				});
				$(this).parent().parent().parent().parent().children().children(':checkbox').attr('checked', checkedFlg);
			}
		});
		$.sync(3);
	}});
	$('#submitBtn').click(function() {
		$.sync(2, {information:true, finish:function() {
//			$.closed();
		}});
	});
})