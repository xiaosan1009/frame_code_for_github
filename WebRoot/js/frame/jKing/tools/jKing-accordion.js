(function ($) {
	$.fn.accordion = function(height) {
		var mh = 30;
		var step = 10;
		var ms = 10;
		$(this).click(function(){
			toggle(this.parentNode, height);
		});
		function toggle(o, n) {
			if (!o.id) {
				o.id = "_" + Math.random() * 100;
			}
			if (!window.toggler) {
				window.toggler = {};
			}
			if (!window.toggler[o.id]) {
				window.toggler[o.id] = {
					obj : o,
					maxHeight : n,
					minHeight : mh,
					timer : null,
					action : -1
				};
			} else {
				window.toggler[o.id].minHeight = mh;
			}
			closeAllToggle(o.id);
			o.style.height = o.offsetHeight + "px";
			if (window.toggler[o.id].timer) {
				clearTimeout(window.toggler[o.id].timer);
			}
			window.toggler[o.id].action *= -1;
			window.toggler[o.id].timer = setTimeout("anim('" + o.id + "')", ms);
		}
		function closeAllToggle(id) {
			var togglers = window.toggler;
			for (var param in togglers) {
				var data = togglers[param];
				if (data.action == 1 && param != id) {
					data.action *= -1;
					data.timer = setTimeout("anim('" + param + "')", ms);
				}
			}
		}
		function anim(id) {
			var t = window.toggler[id];
			var o = window.toggler[id].obj;
			if (t.action < 0) {
				if (o.offsetHeight <= t.minHeight) {
					clearTimeout(t.timer);
					return;
				}
			} else {
				if (o.offsetHeight >= t.maxHeight) {
					clearTimeout(t.timer);
					return;
				}
			}
			o.style.height = (parseInt(o.style.height, 10) + t.action * step) + "px";
			window.toggler[id].timer = setTimeout("anim('" + id + "')", ms);
		}
	};
})(jQuery);