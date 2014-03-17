(function ($) {
	var dimensions = {
		'010011' : {width : 800, height: 350},
		'010012' : {width : 800, height: 400},
		'020501' : {width : 700, height: 200},
		'020502' : {width : 700, height: 200},
		'020601' : {width : 700, height: 200},
		'020701' : {width : 800, height: 360},
		'020702' : {width : 800, height: 360},
		'020602' : {width : 700, height: 200},
		'300001' : {width : 544, height: 410},
		'800101' : {width : 900, height: 630},
		'000004' : {width : 400, height: 200},
		'010013' : {width : 900, height: 900}
	};
	$.dispDimensions = function (dispcode) {
		return dimensions[dispcode];
	};
})(jQuery);