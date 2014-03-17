// ********************************************
// html_common.js
// html for common JavaScript handle
// ********************************************
(function ($) {
	$.removeAllLoading = function() {
		$('.' + LOADING_CLASS).each(function() {
			$(this).removeLoading();
		});
	}
	$.debug = function(msg, flag) {
		JK.debug(msg, flag);
	}
	$.loadingOn = function() {
       JK.loadingOn();
    }
    
    $.loadingOff = function() {
       JK.loadingOff();
    }
	
	$.pageSize = function() {
		return JK.pageSize();
	}
	
	$.listRefreshLoadingShow = function(targetId) {
		JK.listLoadingOn(targetId);
	}
	
	$.listRefreshLoadingHide = function() {
		JK.listLoadingOff();
	}
	
	$.httpUrl = function(type) {
		return JK.httpUrl(type);
	}
	
	$.locationChange = function(dispcode) {
		JK.locationChange(dispcode);
	}
	
	$.pageChange = function(dispcode, cmdcode, param) {
		JK.pageChange(dispcode, cmdcode, param);
	}
})(jQuery);

jQuery.fn.extend(
    {
    	id : function() {
			return this.attr('id');
		},
		
		createLoading : function() {
			this.addClass(LOADING_CLASS);
		},
		
		removeLoading : function() {
			$.debug("removeLoading : targetId = " + this.id(), 0);
			this.removeClass(LOADING_CLASS);
		}
    }
)

function initScreen(screencode) {
	JK.initScreen(screencode);
}

function pageChange(dispcode, cmdcode) {
	JK.pageChange(dispcode, cmdcode);
}
	
function checkParam(param){
	return JK.nvl(param);
}

function setIndex(index) {
	$('#' + LIST_INDEX_ID).val(checkParam(index));
}

function setType(type) {
	$('#' + TYPE_ID).val(checkParam(type));
}

function setValue(value) {
	$('#' + VALUE_ID).val(checkParam(value));
}

function setAutoValue(value) {
	$('#' + AUTO_VALUE_ID).val(checkParam(value));
}

function setKey(key){
	$('#' + KEY).val(checkParam(key));
}

function windowOnScroll() {
	var pageSize = $.pageSize();
	var halfWin = pageSize[3] / 2
//	$('#' + RESULT_SCREEN_ID).css('top', pageSize[5] + halfWin);
	onScorll();
}

function windowOnResize() {
	var pageSize = $.pageSize();
	var halfWin = pageSize[3] / 2
//	$('#' + RESULT_SCREEN_ID).css('top', pageSize[5] + halfWin);
	onResize();
}
