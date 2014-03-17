$(function() {
	JK.initScreen('010003');
	$('#clientName').focus();
	$('#submitBtn').click(function() {
//		$.sync(1, {execute:function() {
//			$.closed();
//		}});
        $('#clientSex').single(2);
	});
    $('input[type="file"]').each(function(event) {
        $(this).fileinput({
            buttonText: 'Browse Files...',
            inputText: 'Please press the button'
        });
    });
//    new Upload().doUpload();
    $('#id_0001').doFileUpload('501');
})