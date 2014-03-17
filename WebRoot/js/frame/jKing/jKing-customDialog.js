(function ($) {
	var settings;
	var _iframe;
	var _dialog_title_height = 28;
	var dialog_default_width = 800;
	var dialog_default_height = 500;
	$.dialog = function (dispcode, externalSettings) {
		$.doc = this;
		$._dispCode = dispcode;
		settings = jQuery.extend({
			l_width:300, 
			l_height:300, 
			overlayBgColor:"#000000", 
			overlayOpacity:0.8, 
			imageLoading:"img/frame/lightbox/lightbox-ico-loading.gif", 
			imageBtnClose:"img/frame/close.gif", 
			closeFun:null,
			closeBtnFun:null,
			keyToClose:"c"
		}, externalSettings);
		_initialize();
	};
	function _initialize() {
		___getFrameObj("embed, object, select").css({"visibility":"hidden"});
		$("select").css({"visibility":"hidden"});
		_enable_keyboard_navigation();
		_set_interface();
		return false;
	}
	function _enable_keyboard_navigation() {
		___getFrameObj(document).keydown(function (objEvent) {
			_keyboard_action(objEvent);
		});
	}
	function _keyboard_action(objEvent) {
		var keycode;
		if (objEvent == null) {
			keycode = event.keyCode;
			escapeKey = 27;
		} else {
			keycode = objEvent.keyCode;
			escapeKey = objEvent.DOM_VK_ESCAPE;
		}
		key = String.fromCharCode(keycode).toLowerCase();
//		if ((key == settings.keyToClose) || (key == "x") || (keycode == escapeKey)) {
//			_finish();
//		}
	}
	function _set_interface() {
		_iframe = ___getFrameObj('#dialogContainerFrame')[0];
		_iframe.style.display = 'none';
		___getFrameObj('#dialog-loading').show();
		___getFrameObj('#dialog-title').html('加载中...');
		var url = serverURL + '?dispcode=' + $._dispCode + '&cmdcode=0&clientType=1&dialogFlg=1';
		if (settings.data != null && settings.data != undefined && settings.data != '') {
			url += '&' + settings.data;
		}
		_iframe.src = url;
		___getFrameObj('#dialogContainerFrame').bind('load', function () {
			var frameSize = ___getFrameSize(_iframe);
			var arrPageSizes = ___getPageSize();
			var arrPageScroll = ___getPageScroll();
			if (frameSize.width > arrPageSizes[0]) {
				frameSize.width = arrPageSizes[0];
			}
			if (frameSize.height > arrPageSizes[3] - _dialog_title_height) {
				frameSize.height = arrPageSizes[3] - _dialog_title_height;
			}
			//___getFrameObj('#dialog-content').css('left', (arrPageSizes[0] - frameSize.width) / 2);
			//___getFrameObj('#dialog-content').css('top', (arrPageSizes[3] - frameSize.height) / 2);
			___getFrameObj('#dialog-content').animate({ 
				width: parseInt(frameSize.width, 10), 
				height: parseInt(frameSize.height, 10),
				top:frameSize.top, 
				left:frameSize.left}, 1000,
				function() { 
					if (settings.title) {
						___getFrameObj('#dialog-title').html(settings.title);
						___getFrameObj('#dialog-title').css('margin-left', (frameSize.width - ___getFrameObj('#dialog-title').width()) / 2);
					} else {
						___getFrameObj('#dialog-title').html('');
					}
					___getFrameObj('#dialogContainerFrame').height(parseInt(frameSize.height, 10));
					___getFrameObj('#dialog-loading').hide();
					_iframe.style.display = '';
					_iframe.contentWindow.$('.focusItem').focus();
					_iframe.contentWindow.$('.focusItem').select();
					if (_iframe.contentWindow.bindClose) {
						_iframe.contentWindow.bindClose(iframeClose, $.doc);
					}
			});
		});
		
		var arrPageSizes = ___getPageSize();
		var arrPageScroll = ___getPageScroll();
		___getFrameObj("#dialog-content").css({
			width:settings.l_width, 
			height:settings.l_height, 
			top:(arrPageScroll[1] + arrPageSizes[3] - settings.l_height) / 2, 
			left:(arrPageScroll[0] + arrPageSizes[0] - settings.l_width) / 2}).show();
		___getFrameObj("#dialog-overlay").css({
			backgroundColor:settings.overlayBgColor, 
			opacity:settings.overlayOpacity, 
			width:arrPageSizes[0], 
			height:(arrPageSizes[1])}).show();
		___getFrameObj("#dialog-loading-link,#dialog-btnClose").bind('click', function () {
			_finish();
			return false;
		});
		
		$(window).resize(function () {
		});
	}
	function ___getFrameSize(iframe) {
		if (!iframe) {
			return {width:0, height:0, top:0, left:0};
		}
		var arrPageSizes = ___getPageSize();
		var arrPageScroll = ___getPageScroll();
		var dispcode;
		if (___getFrameObj(iframe.contentWindow.document.body).find('#dispcode')) {
			dispcode = ___getFrameObj(iframe.contentWindow.document.body).find('#dispcode').val();
		}
        if (!dispcode) {
            dispcode = $._dispCode;
        }
		var frameWidth = settings.d_width;
		var frameHeight = settings.d_height;
		var dimension = $.dispDimensions(dispcode);
		if (!frameWidth) {
			if (dimension) {
				frameWidth = dimension.width;
			} else {
				frameWidth = dialog_default_width;
			}
		}
		if (!frameHeight) {
			if (dimension) {
				frameHeight = dimension.height;
			} else {
				frameHeight = dialog_default_height;
			}
		}
		var frameTop = (arrPageScroll[1] + arrPageSizes[3] - frameHeight) / 2;
		if (frameTop < 0) {
			frameTop = 0;
		}
		var frameLeft = (arrPageScroll[0] + arrPageSizes[0] - frameWidth) / 2;
		if (frameLeft < 0) {
			frameLeft = 0;
		}
		return {width:frameWidth, height:frameHeight, top:frameTop, left:frameLeft};
	}
	function ___getPageSize() {
		var xScroll, yScroll;
		if (___getFrameWindow().innerHeight && ___getFrameWindow().scrollMaxY) {
			xScroll = ___getFrameWindow().innerWidth + ___getFrameWindow().scrollMaxX;
			yScroll = ___getFrameWindow().innerHeight + ___getFrameWindow().scrollMaxY;
		} else {
			if (___getFrameWindow().document.body.scrollHeight > ___getFrameWindow().document.body.offsetHeight) { // all but Explorer Mac
				xScroll = ___getFrameWindow().document.body.scrollWidth;
				yScroll = ___getFrameWindow().document.body.scrollHeight;
			} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
				xScroll = ___getFrameWindow().document.body.offsetWidth;
				yScroll = ___getFrameWindow().document.body.offsetHeight;
			}
		}
		var windowWidth, windowHeight;
		if (___getFrameWindow().self.innerHeight) {	// all except Explorer
			if (___getFrameWindow().document.documentElement.clientWidth) {
				windowWidth = ___getFrameWindow().document.documentElement.clientWidth;
			} else {
				windowWidth = ___getFrameWindow().self.innerWidth;
			}
			windowHeight = ___getFrameWindow().self.innerHeight;
		} else {
			if (___getFrameWindow().document.documentElement && ___getFrameWindow().document.documentElement.clientHeight) { // Explorer 6 Strict Mode
				windowWidth = ___getFrameWindow().document.documentElement.clientWidth;
				windowHeight = ___getFrameWindow().document.documentElement.clientHeight;
			} else {
				if (___getFrameWindow().document.body) { // other Explorers
					windowWidth = ___getFrameWindow().document.body.clientWidth;
					windowHeight = ___getFrameWindow().document.body.clientHeight;
				}
			}
		}	
		// for small pages with total height less then height of the viewport
		if (yScroll < windowHeight) {
			pageHeight = windowHeight;
		} else {
			pageHeight = yScroll;
		}
		// for small pages with total width less then width of the viewport
		if (xScroll < windowWidth) {
			pageWidth = xScroll;
		} else {
			pageWidth = windowWidth;
		}
		arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
		return arrayPageSize;
	}
	/**
	 / THIRD FUNCTION
	 * getPageScroll() by quirksmode.com
	 *
	 * @return Array Return an array with x,y page scroll values.
	 */
	function ___getPageScroll() {
		var xScroll, yScroll;
		if (___getFrameWindow().self.pageYOffset) {
			yScroll = ___getFrameWindow().self.pageYOffset;
			xScroll = ___getFrameWindow().self.pageXOffset;
		} else {
			if (___getFrameWindow().document.documentElement && ___getFrameWindow().document.documentElement.scrollTop) {	 // Explorer 6 Strict
				yScroll = ___getFrameWindow().document.documentElement.scrollTop;
				xScroll = ___getFrameWindow().document.documentElement.scrollLeft;
			} else {
				if (___getFrameWindow().document.body) {// all other Explorers
					yScroll = ___getFrameWindow().document.body.scrollTop;
					xScroll = ___getFrameWindow().document.body.scrollLeft;
				}
			}
		}
		arrayPageScroll = new Array(xScroll, yScroll);
		return arrayPageScroll;
	}
	function _finish(flg, closeSettings) {
        if (!$.doc) {
            return;
        }
		_disable_keyboard_navigation();
		___getFrameObj('#dialogContainerFrame').unbind('load');
		___getFrameObj("#dialog-loading-link,#dialog-btnClose").unbind('click');
		_iframe = null;
		
		___getFrameObj("embed, object, select").css({"visibility":"visible"});
		$($.doc.find('select')).css({"visibility":"visible"});
		___getFrameObj("#dialog-content").hide();
		___getFrameObj("#dialog-overlay").fadeOut(function () {
			if (flg) {
				if (settings && settings.closeFun) {
					settings.closeFun(closeSettings);
				}
			} else {
				if (settings && settings.closeBtnFun) {
					settings.closeBtnFun();
				}
			}
			___getFrameObj("#dialog-overlay").hide();
		});
	}
	function _disable_keyboard_navigation() {
		___getFrameObj(document).unbind();
	}
	function ___getFrameObj(targetId) {
	 	var frameObj = window.parent;
		while (frameObj != window) {
			if (frameObj == frameObj.parent ) {
				break;
			}
			frameObj = frameObj.parent;
		}
		return frameObj.$(targetId);
	 }
	 function ___getFrameWindow() {
	 	var frameObj = window.parent;
		while (frameObj != window) {
			if (frameObj == frameObj.parent ) {
				break;
			}
			frameObj = frameObj.parent;
		}
		return frameObj;
	 }
	function iframeClose(closeSettings, isNotCallBack) {
		if (!isNotCallBack) {
			_finish(true, closeSettings);
		} else {
			_finish();
		}
	}
	$.closeDialog = function () {
		_finish();
	};
	$.showDialog = function (dispcode, externalSettings) {
		if (dispcode && !settings) {
			$._dispCode = dispcode;
			settings = jQuery.extend({
				l_width:300, 
				l_height:300, 
				overlayBgColor:"#000", 
				overlayOpacity:0.8, 
				imageLoading:"img/frame/lightbox/lightbox-ico-loading.gif", 
				imageBtnClose:"img/frame/close.gif", 
				keyToClose:"c"
			}, externalSettings);
		}
		_initialize();
	};
})(jQuery);