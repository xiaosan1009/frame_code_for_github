(function ($) {
	var _tip;
	$.fn.showTip = function(message) {
		if (!_tip) {
			$("body").append("<div class='tip' id='commonTip'><p></p></div>");
		}
		_tip = $("#commonTip");

		$(this).mouseover(function(){
			_tip.css({opacity:1, display:"none"}).fadeIn(400, function() {
				_tip.clearQueue();
			});
		}).mousemove(function(kmouse){
			_tip.children().html($(this).html());
			_tip.css({left:kmouse.pageX+15, top:kmouse.pageY+15});
		}).mouseout(function(){
			_tip.fadeOut(400);
		});
	};
})(jQuery);