$(function() {
	initScreen('050400');
	$('#searchBtn').click(function() {
		showList1();
	});
	$('#parentType').change(function() {
		$.sync(2);
	});
})
function init() {
	showList1();
}

function showList1() {
	$('#list1').paging(1, 500, {execute:function() {
		$(':text').keydown(function(objEvent) {
			if (objEvent.keyCode == '13') {
				$(this).trigger("blur");
			}
		});
	}});
}

function unitEdit() {
	var obj = $(event.srcElement);
	obj.parent().children(":eq(0)").hide();
	obj.parent().children(":eq(1)").show();
	obj.parent().children(":eq(1)").focus();
	obj.parent().children(":eq(1)").select();
}

function unitChange(index) {
	var obj = $(event.srcElement);
	obj.single(3, {index:index, execute:function() {
		obj.parent().children(":eq(0)").html(obj.parent().children(":eq(1)").val());
		obj.parent().children(":eq(0)").show();
		obj.parent().children(":eq(1)").hide();
	}});
}