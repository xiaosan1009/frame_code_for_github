$(function() {
	JK.initScreen('060001');
    $('input[type="file"]').each(function(event) {
        $(this).fileinput({
            buttonText: 'Browse Files...',
            inputText: 'Please press the button'
        });
    });
    $('#id_0001').uploadExcelList('list1', '500', function(error) {
    	if (!error) {
    		$('#list1').list(1);
    		$('#list2').list(2);
    	}
    });
    $('#list1').list(1);
    $('#list2').list(2);
})

function showDataList(sheetName) {
	var sett = new $.Settings();
	sett.data = 'sheetName=' + sheetName;
	$('#list2').list(2, sett);
}