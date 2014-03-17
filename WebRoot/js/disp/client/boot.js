var $_GLOBAL = {};
(function() {
	var __debug_mode = false;
	var __sina_ver = "0.0.1";
	var __js_domain = "http://login.sina.com.cn/";
	if (window.location.protocol != "http:") {
		__js_domain = __js_domain.replace("http:", window.location.protocol)
	}
	var __addDOMLoadEvent = function(func) {
		if (!window.__load_events) {
			var init = function() {
				window.st = 2;
				if (arguments.callee.done) {
					window.st = 21;
					return
				}
				arguments.callee.done = true;
				if (window.__load_timer) {
					window.st = 22;
					clearInterval(window.__load_timer);
					window.__load_timer = null
				}
				for (var i = 0; i < window.__load_events.length; i++) {
					window.st = 23;
					window.__load_events[i]()
				}
				window.__load_events = null
			};
			if (document.addEventListener) {
				document.addEventListener("DOMContentLoaded", init, false);
				/* @cc_on @ */
				/*
				 * @if (@_win32) var domlen =
				 * document.getElementsByTagName("*").length; var domnum = 0;
				 * (function () { if(domnum != domlen) {
				 * setTimeout(arguments.callee, 500); } else { setTimeout(init,
				 * 500); } domnum = domlen; })(); @end @
				 */
			}
			if (/WebKit/i.test(navigator.userAgent)) {
				window.__load_timer = setInterval(function() {
							if (/loaded|complete/.test(document.readyState)) {
								init()
							}
						}, 10)
			}
			if (window.ActiveXObject) {
				window.st = 0;
				window.__load_timer = setInterval(function() {
							try {
								document.body.doScroll("left");
								window.st = 1;
								init()
							} catch (ex) {
							}
						}, 10)
			}
			window.__load_events = []
		}
		window.__load_events.push(func)
	};
	var addEvent = function(elm, func, evType, useCapture) {
		var _el = elm;
		if (_el == null) {
			return
		}
		if (typeof useCapture == "undefined") {
			useCapture = false
		}
		if (typeof evType == "undefined") {
			evType = "click"
		}
		if (_el.addEventListener) {
			_el.addEventListener(evType, func, useCapture);
			return true
		} else {
			if (_el.attachEvent) {
				var r = _el.attachEvent("on" + evType, func);
				return true
			} else {
				_el["on" + evType] = func
			}
		}
	};
	var __addFocusEvent = function(fFocusFunction) {
		if (window.excute != null && typeof window.excute == "function") {
			excute();
			excute = null
		}
	};
	if (__debug_mode) {
	}
	window.__load_js = function() {
		var url = "";
		if (__debug_mode == true) {
			url = __js_domain + "js/bind2/index.php?product="
					+ $SCOPE.PRODUCT_NAME + "&pageid=" + $SCOPE.PAGE_ID
					+ "&rnd=" + new Date().getTime();
			document.write('<script type="text/javascript" src="' + url
					+ '" charset="utf-8" defer="true"><\/script>')
		} else {
			url = __js_domain + "js/" + $SCOPE.PRODUCT_NAME + "/"
					+ $SCOPE.PAGE_ID + ".js?" + $SCOPE.JS_VER + ".js";
			document.write('<script type="text/javascript" src="' + url
					+ '" charset="utf-8"><\/script>')
		}
	};
	window.__render_page = function() {
		window.st = 4;
		__addDOMLoadEvent(function() {
					function runMain() {
						if (typeof window.main == "function") {
							window.main();
							window.stok = 41
						} else {
							window.setTimeout(runMain, 10);
							window.st = 42
						}
					}
					runMain()
				});
		addEvent(document.body, __addFocusEvent, "focus");
		addEvent(window, __addFocusEvent, "scroll");
		addEvent(document.body, __addFocusEvent, "mousemove");
		addEvent(document.body, __addFocusEvent, "mouseover")
	};
	if (typeof $SCOPE.SET_DOMAIN == "undefined" || $SCOPE.SET_DOMAIN == true) {
		document.domain = "sina.com.cn"
	}
	(function() {
		if (window.scope == null) {
			window.scope = {}
		}
		scope._ua = navigator.userAgent.toLowerCase();
		window.$IE = scope.$IE = /msie/.test(scope._ua);
		window.$OPERA = scope.$OPERA = /opera/.test(scope._ua);
		window.$MOZ = scope.$MOZ = /gecko/.test(scope._ua);
		window.$IE5 = scope.$IE5 = /msie 5 /.test(scope._ua);
		window.$IE55 = scope.$IE55 = /msie 5.5/.test(scope._ua);
		window.$IE6 = scope.$IE6 = /msie 6/.test(scope._ua);
		window.$IE7 = scope.$IE7 = /msie 7/.test(scope._ua);
		window.$SAFARI = scope.$SAFARI = /safari/.test(scope._ua);
		window.$winXP = scope.$winXP = /windows nt 5.1/.test(scope._ua);
		window.$winVista = scope.$winVista = /windows nt 6.0/.test(scope._ua);
		if (window.$SYSMSG == null) {
			window.$SYSMSG = {}
		}
		$SYSMSG.extend = function(info, override) {
			for (var i in info) {
				$SYSMSG[i] = !!override == false ? info[i] : $SYSMSG[i]
			}
		}
	})();
	if (!/\((iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
		return
	}
	document.addEventListener("mouseover", function(e) {
				var ele = e.target;
				do {
					if (ele.tagName == "A") {
						ele.target = "_self";
						return
					}
					if (ele.tagName == "DIV") {
						return
					}
				} while (ele = ele.parentNode)
			}, false);
	window.Validator = {
		focus : function() {
		},
		blur : function() {
		}
	}
})();