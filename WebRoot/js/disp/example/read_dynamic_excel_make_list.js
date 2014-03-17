$(function() {
	JK.initScreen('060002');
    $('input[type="file"]').each(function(event) {
        $(this).fileinput({
            buttonText: 'Browse Files...',
            inputText: 'Please press the button'
        });
    });
    $('#id_0001').doFileUpload('500', function(error) {
    	if (!error) {
    		$('#list1').list(1);
//    		$('#list2').list(2);
    	}
    });
    $('#list1').list(1);
//    $('#list2').list(2);
})

function showDataList(sheetName) {
	var sett = new $.Settings();
	sett.data = 'sheetName=' + sheetName;
	sett.finish = function(result) {
		createTable(result.datas);
	};
	$.queryDatas(3, sett);
//	$('#list2').list(2, sett);
}

function createTable(datas) {
	$("#list2").html('');
	var table = $('<table width="1013px" border="1" cellspacing="0" cellpadding="0" style="border-bottom-width:0;" class="font14 autoTableStyle">');
	table.appendTo($("#list2"));
	var tr;
	var rowIndex;
	for (var i = 0; i < datas.length; i++) {
		var rowColl = datas[i].row;
		var cellColl = datas[i].cell;
		if (rowIndex != rowColl) {
			tr = createTableRow(table);
			rowIndex = rowColl;
		}
		var td = createTableCell(tr, datas[i]);
	}
	$("#list2").append("</table>");
}
function createTableRow(table) {
	var tr = $("<tr></tr>");
	tr.appendTo(table);
	return tr;
}
function createTableCell(tr, data) {
	var value = data.value;
	var background = data.background;
	var rowspan = data.rowspan;
	var colspan = data.colspan;
	var align = data.align;
	var valign = data.valign;
	var tdhtml = '<td';
	if (rowspan) {
		tdhtml += ' rowspan=' + rowspan;
	}
	if (colspan) {
		tdhtml += ' colspan=' + colspan;
	}
	if (background) {
		tdhtml += ' bgcolor=' + background;
	}
	if (align) {
		tdhtml += ' align=' + align;
	}
	if (valign) {
		tdhtml += ' valign=' + valign;
	}
	if (!value) {
		value = '&nbsp;';
	}
	tdhtml += '>' + value+ '</td>';
	var td = $(tdhtml);
	td.appendTo(tr);
}