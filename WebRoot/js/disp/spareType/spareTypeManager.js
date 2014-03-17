var selectItemId;
$(function() {
	initScreen('020600');
	$('#submitBtn').click(function() {
		addType(true);
	});
})

function init() {
	$('#list1').list('1', {execute:function() {
		judegList1Height();
	}});
}

function judegList1Height() {
	if ($('#list1').height() != 0) {
		$('#list2').show();
		$('#list2').height($('#list1').height());
	} else {
		$('#list2').hide();
	}
}

function toggle(parentId, obj) {
	selectItemId = obj.id;
	clearStyle();
	$(obj).attr('class', 'selectedStyleItem');
	$('#parentId').val(parentId);
	$('#list2').list('2');
}

function addType(parent, obj, typeId) {
	if (!typeId) {
		typeId = '';
	} 
	if (!parent) {
		toggle(typeId, obj);
	}
	var param = 'typeId=' + typeId;
	$.dialog('020601', {data:param,closeFun:function() {
		if (parent) {
			$('#list1').list('1', {execute:function() {
				if (selectItemId) {
					$('#' + selectItemId).attr('class', 'selectedStyleItem');
				}
				judegList1Height();
			}});
		} else {
			$('#parentId').val(typeId);
			$('#list2').list('2');
		}
	}});
}

function clearStyle() {
	$('#list1').children().children().each(function() {
		$(this).attr('class','styleType');
	});
}

function editType(parent, obj, typeId) {
	if (parent) {
		toggle(typeId, obj);
	}
	var param = 'typeId=' + typeId;
	$.dialog('020602', {data:param,closeFun:function() {
		if (parent) {
			$('#list1').list('1', {execute:function() {
				$('#' + selectItemId).attr('class', 'selectedStyleItem');
				judegList1Height();
			}});
		} else {
			$('#list2').list('2');
		}
	}});
}

function deleteType(parent, obj, typeId) {
	if (parent) {
		toggle(typeId, obj);
	}
	$.del('3', typeId, {execute:function() {
		if (parent) {
			$('#list1').list('1', {execute:function() {
				$('#list2').height($('#list1').height());
				$('#parentId').val('');
				selectItemId = '';
				$('#list2').list('2', {execute:function() {
					judegList1Height();
				}});
			}});
		} else {
			$('#list2').list('2');
		}
	}});
}