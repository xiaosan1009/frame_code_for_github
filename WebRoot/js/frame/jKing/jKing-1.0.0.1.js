(function (window, undefined) {

var JK = function(id) {
		return document.getElementById(id);
	},
	_consts = {
		id : {
			/**
			 * 提示信息浮动层id
			 */
			POPUP_DIV_ID : 'common_popup_div',
			LIST_LOADING_ID : 'listRefreshLoading',
			INITIALIZE_CMD : 'initializeCmd',
			INITIALIZE_TARGETID : 'initializeTargetId',
			DIALOG_FLG : 'dialogFlg'
		},
		key : {
			ERROR_MSG : 'errorMsg'
		},
		control : {
			BODY : 'body'
		},
		res : {
			code : {
				/**
				 * 画面跳转
				 */
				RESCODE_PAGE_CHANGED : 'UR100010000',
				
				/**
				 * 错误处理
				 */
				RES_RESCODE_INTERNAL_ERR : 'UR100010001', 
				
				/**
				 * 画面表示信息的清除
				 */
				RES_RESCODE_MESSAGE_BLANK : 'UR100010002', 
				
				/**
				 * 数据添加成功
				 */
				RES_RESCODE_REG_COMPLETE : 'UR100010003', 
				
				/**
				 * 输入错误
				 */
				RES_RESCODE_INPUT_ERROR : 'UR100010004', 
				
				/**
				 * 数据库添加错误
				 */
				RES_RESCODE_DB_INPUT_ERROR : 'UR100010005', 
				
				/**
				 * 数据库读取错误
				 */
				RES_RESCODE_DB_OUTPUT_ERROR : 'UR100010006', 
				
				/**
				 * 没有数据
				 */
				RES_RESCODE_NO_DATA : 'UR100010007', 
				
				/**
				 * 删除成功
				 */
				RES_RESCODE_DEL_COMPLETE : 'UR100010008', 
				
				/**
				 * 初期信息表示
				 */
				RES_RESCODE_INFO : 'UR100010009', 
				
				/**
				 * 上传成功
				 */
				RES_RESCODE_UPLOAD_COMPLETE : 'UR100010010', 
				
				/**
				 * 用户登录错误
				 */
				RES_RESCODE_LOGIN_ERROR : 'UR100010011', 
				
				/**
				 * session超时
				 */
				RES_RESCODE_SESSION_TIMEOUT : 'UR100010012', 
				
				/**
				 * 数据库错误
				 */
				RES_RESCODE_DB_ERROR : 'UR100010013', 
				
				/**
				 * 删除错误
				 */
				RES_RESCODE_DB_DELETE_ERROR : 'UR100010014', 
				
				/**
				 * 用户名重复
				 */
				RES_RESCODE_OPENAME_OVERLAP : 'UR100010015', 
				
				/**
				 * session链接超过最大数
				 */
				RES_RESCODE_LOGIN_ERR_SESSION_MAX : 'UR100010016', 
				
				/**
				 * 用户重复登录！
				 */
				RES_RESCODE_OTHER_USER_LOGIN : 'UR100010017'
			},
			kind : {
				/**
				 * 单项目标识值
				 */
				RES_DATA_KIND_TAB : "0",
				
				/**
				 * 多项目标识值
				 */
				RES_DATA_KIND_HTML : "1",
				
				/**
				 * 混合项目标识值
				 */
				RES_DATA_KIND_COMPLEX : "3"
			}
		},
		flag : {
			/**
			 * 控件操作标识：允许操作
			 */
			VALUE_EFFECT_FLG_VALID : '1',
			
			/**
			 * 控件操作标识：不允许操作
			 */
			VALUE_EFFECT_FLG_INVALID : '0'
		}
	},
	
	_apps = {
		loadingOn : function loadingOn() {
			_tools.debug("loadingOn : self.location = " + self.location 
		       + ", window.parent.location = " + window.parent.location, 0);
			if (self.location == window.parent.location) {
				var arrPageSizes = $.pageSize();
		       	if (self.$ && self.$.find('#glayoutFrame').length > 0) {
		          	var obj = $('#glayoutFrame');
		          	obj.width(arrPageSizes[0]);
		          	obj.height(arrPageSizes[3]);
		          	obj.show();
		       	} else if (window.parent.$ && window.parent.$.find('#glayoutFrame').length > 0) {
		          	var obj = window.parent.$('#glayoutFrame');
		          	obj.width(arrPageSizes[0]);
		          	obj.height(arrPageSizes[3]);
		          	obj.show();
		       	}
		   	} else {
		       	var frameObj = window.parent;
		       	while (frameObj) {
		          	if (frameObj.$ && frameObj.$.find('#glayoutFrame').length == 0) {
		              	if (frameObj == frameObj.parent) {
		                 	return;
		              	} else {
		                 	frameObj = frameObj.parent;
		              	}
		          	} else {
		              	break;
		          	}
		       	}
		       	if(frameObj.$ && frameObj.$.find('#glayoutFrame').length > 0){
		          	var arrPageSizes = frameObj.$.pageSize();
		          	var obj = frameObj.$('#glayoutFrame');
		          	obj.width(arrPageSizes[0]);
		          	obj.height(arrPageSizes[3]);
		          	obj.show();
		       	}
		   	}
		},
		loadingOff : function loadingOff() {
			if (self.location === window.parent.location) {
				if (self.$ && self.$.find('#glayoutFrame').length > 0) {
					$('#glayoutFrame').hide();
				} else if (window.parent.$
						&& window.parent.$.find('#glayoutFrame').length > 0) {
					window.parent.$('#glayoutFrame').hide();
				}
			} else {
				var frameObj = window.parent;
				while (frameObj) {
					if (frameObj.$
							&& frameObj.$.find('#glayoutFrame').length === 0) {
						if (frameObj == frameObj.parent) {
							return;
						} else {
							frameObj = frameObj.parent;
						}
					} else {
						break;
					}
				}
				if (frameObj.$ && frameObj.$.find('#glayoutFrame').length > 0) {
					frameObj.$('#glayoutFrame').hide();
				}
			}
		},
		listLoadingOn : listRefreshLoadingShow,
		listLoadingOff : listRefreshLoadingHide,
		httpUrl : httpUrl,
		locationChange : locationChange,
		pageChange : pageChange,
		pageSize : pageSize,
		setAjaxParam : setAjaxParam,
		initScreen : initScreen,
		initalize : initalize,
		closed : closed,
		btnClosed : btnClosed,
		displaySingleResult : displaySingleResult,
		showErrorMsg : showErrorMsg,
		hideErrorMsg : hideErrorMsg,
		removeErrStyle : removeErrStyle,
		getCoordinate : getCoordinate
	},
	
	_tools = {
		debug : function debug(msg, flag) {
			if (MAIN_DEBUG_FLG) {
				if (flag === 1 || flag === true) {
					alert(msg);
				} else if(flag === 0 || flag === false || flag === undefined) {
					if (DEBUG_FLG) {
						alert(msg);
					}
				}
			}
		},
		nvl : nvl,
		isEmpty : isEmpty,
		toJson : function(o) {
			var r = [];
			if (typeof o == "string" || o == null) {
				return '@' + o + '@';
			}
			if (typeof o == "object") {
				if (!o.sort) {
					r[0] = "{";
					for (var i in o) {
						r[r.length] = '@' + i + '@';
						r[r.length] = ":";
						r[r.length] = $.toJson(o[i]);
						r[r.length] = ",";
					}
					r[r.length - 1] = "}";
				} else {
					r[0] = "[";
					for (var i = 0; i < o.length; i++) {
						r[r.length] = $.toJson(o[i]);
						r[r.length] = ",";
					}
					r[r.length - 1] = "]";
				}
				return r.join("");
			}
			return o.toString();
		},
		log : function() {
			var log = '';
			for (var i = 0; i < arguments.length; i++) {
				log += '[' + arguments[i] + ']';
			}
			_tools.debug(log, 1);
		},
		cloneObj : function(obj, constructor) {
			if (!constructor) {
				constructor = {};
			}
		    var objClone;  
		    if (typeof obj == "number" || typeof obj == "string" || typeof obj == "boolean" || !obj) {
				return obj;  
		    } else if (typeof obj == "function") {
				objClone = eval('(' + Object.toString.call(obj) + ')');  
		    } else if (obj.constructor == Object) {
				objClone = new obj.constructor();    
		    } else {
				objClone = new obj.constructor('dragresize', constructor);   
		    } 
		    for (var key in obj) {  
			    if (objClone[key] != null && objClone[key] != undefined && objClone[key] != obj[key]) {  
					if (typeof obj[key] == "number" || typeof obj[key] == "string" || typeof obj[key] == "boolean" || !obj[key] ) {
						objClone[key] = obj[key];    
			      	} else {   
						objClone[key] = arguments.callee(obj[key]);  
					}  
				}  
		    }  
		    objClone.toString = obj.toString;  
		    objClone.valueOf = obj.valueOf;  
		    return objClone;   
		}
	},
	
	/**
	 * 执行事件对象的存储容器
	 */
	_callback = new Object(), 
	
	/**
	 * 错误信息对象存储容器
	 */
	_errorMsgObj = {},
	
	/**
	 * 分页控制对象
	 */
	_pagingObj,
	
	/**
	 * 分页请求的方法id
	 */
	_pagingCmd,
	
	/**
	 * 分页请求的动画时间
	 */
	_pagingTime,
	
	/**
	 * 分页请求的参数对象
	 */
	_pagingSettings,
	
	/**
	 * 是否为初始化执行阶段的标识
	 */
	_initFlg = false,
	
	/**
	 * 是否为单项目请求的标识
	 */
	_singleFlg = false,
	
	/**
	 * 多项目控件id的缓存
	 */
	_targetId,
	
	/**
	 * 自定义系统异常时执行的方法对象
	 */
	_sysError,

	/**
	 * 主debug标识<br>
	 * 如果为false，那么所有通过debug的alert都将失效
	 */
	MAIN_DEBUG_FLG = true, 
	
	/**
	 * 强制alert标识<br>
	 * 如果为true，那么所有通过debug的alert无论第二个参数是否为真，都将alert<br>
	 * 优先级低于MAIN_DEBUG_FLG
	 */
	DEBUG_FLG = false;
	
JK.extend = function () {
	var target = arguments[0] || {}, i = 1, length = arguments.length, source;
	if (length === i) {
		source = target;
		target = this;
	} else {
		source = arguments[i];
	}
	for (var property in source) {
	    target[property] = source[property];
	}
	return target;
}
JK.extend({
	RES_RESCODE_SESSION_TIMEOUT : _consts.res.code.RES_RESCODE_SESSION_TIMEOUT
});
// 工具方法
JK.extend(_tools);

// flash控件方法
JK.extend({
	makeWidget : function(param) {
		var widget = new Object();
		if (param.id != null && param.id != undefined) {
			widget.targetId = param.id;
		}
		if (param.text != null && param.text != undefined) {
			widget.text = param.text;
		}
		if (param.type != null && param.type != undefined) {
			widget.type = param.type;
		}
		if (param.method != null && param.method != undefined) {
			widget.method = param.method;
		}
		if (param.cmd != null && param.cmd != undefined) {
			widget.cmd = param.cmd;
		}
		if (param.after != null && param.after != undefined) {
			widget.after = param.after;
		}
		return widget;
	},
	
	makeContainer : function() {
		var widget = new Object();
		var container = [];
		for (var i = 0; i < arguments.length; i++) {
			container[i] = arguments[i];
		}
		widget.container = container;
		return widget;
	}
});

// 应用方法
JK.extend(_apps);

// 交互方法
JK.extend({
	sync : sync,
	synchro : synchro,
	synced : synced,
	async : async,
	single : single,
	tree : tree,
	list : list,
	paging : paging,
	pagingSync : pagingSync,
	pagingNext : pagingNext,
	pagingPrev : pagingPrev,
	pagingGoto : pagingGoto,
	pagePrev : function() {
		_pagingObj.pagingPrev(_pagingCmd, _pagingTime, _pagingSettings);
	},

	pageNext : function() {
		_pagingObj.pagingNext(_pagingCmd, _pagingTime, _pagingSettings);
	},

	pageGoto : function() {
		_pagingObj.pagingGoto(_pagingCmd, _pagingTime, _pagingSettings);
	},
	doUpload : doUpload,
	doFileUpload : doFileUpload,
	kindEditor : kindEditor,
	auto : auto,
	del : del,
	edit : edit,
	excel : excel,
	queryDatas : queryDatas,
	queryDatasSync : queryDatasSync
});

// ********************************************** 工具方法 **********************************************


function nvl(param){
	if (!param && param !== '0') {
		return '';
	}
	return param;
}

function isEmpty(param) {
	if (param !== undefined && param !== null && param !== '' && param !== 'undefined') {
		return false;
	}
	return true;
}

// ********************************************** 应用方法 **********************************************
function initScreen(screencode) {
	_tools.debug("initScreen" , 0);
	var c = _consts.id;
	if (screencode !== SCREEN_MENU 
		&& screencode !== SCREEN_LOGIN 
		&& $('#' + c.DIALOG_FLG).val() !== '1') {
		loadingOn();
	}
	$('#' + DISP_CODE_ID).val(screencode);
	$('#' + SCREEN_CODE_ID).val(screencode);
    _initFlg = true;
    $.synced(callBackInitScreen);
    var initializeTargetId = $('#' + c.INITIALIZE_TARGETID).val();
    if (initializeTargetId) {
	    _pagingObj = $('#' + initializeTargetId);
	    _pagingCmd = $('#' + c.INITIALIZE_CMD).val();
	    pagingDisplay();
    }
}

function listRefreshLoadingShow(targetId) {
	var c = _consts.id;
	if ($('#' + c.LIST_LOADING_ID).length !== 0) {
		var pageSize = $.pageSize();
		var scrollY = pageSize[5];
		var top = pageSize[3] / 2 + scrollY;
		$('#' + c.LIST_LOADING_ID).css('top', top);
		$('#' + c.LIST_LOADING_ID).show();
		$('#' + c.LIST_LOADING_ID).fadeIn();
	} else {
		listRefreshLoadingCreate();
	}
}

function listRefreshLoadingHide() {
	var c = _consts.id;
	if ($('#' + c.LIST_LOADING_ID).length !== 0) {
		var pageSize = $.pageSize();
		var scrollY = pageSize[5];
		var top = pageSize[3] / 2 + scrollY;
		$('#' + c.LIST_LOADING_ID).css('top', top);
		if ($('#' + c.LIST_LOADING_ID).css('display') !== 'none') {
			$('#' + c.LIST_LOADING_ID).fadeOut(function() {
                $('#' + c.LIST_LOADING_ID).hide();
            });
		}
	}
}

function httpUrl(type) {
	var httpHost = window.location.host;
	var httpPathname = window.location.pathname;
	var httpProtocol = window.location.protocol;
	if (type || type === '0') {
		return httpProtocol + '//' + httpHost + httpPathname.substring(0, httpPathname.lastIndexOf('/'));
	}
	var httpUrl = httpProtocol + '//' + httpHost + httpPathname;
	return httpUrl;
}

function locationChange(dispcode) {
	var url = location.href.substring(0, location.href.lastIndexOf('/') + 1) + serverURL 
			+ '?dispcode=' + dispcode + '&cmdcode=0&clientType=1';
	if (top != this || (self.frameElement && self.frameElement.tagName=="IFRAME")) {
		window.parent.window.location = url;
	} else {
		window.location = url;
	}
}

function pageChange(dispcode, cmdcode, param) {
	if (isEmpty(cmdcode)) {
		cmdcode = REQ_CMDCODE_INIT;
	}
	$('#' + CMD_CODE_ID).val(cmdcode);
	$('#' + DISP_CODE_ID).val(dispcode);
	var url = serverURL + '?dispcode=' + dispcode;
	url += '&cmdcode=' + cmdcode + '&clientType=1';
	if (!isEmpty(param)) {
		url += '&' + param;
	}
	document.forms[0].method="post";
	document.forms[0].action = url;
	document.forms[0].submit();
}

function setAjaxParam(cmdCode, targetId, targetIdIndex, formId){
	var formname = checkFormId(formId);
	_tools.debug("setAjaxParam:formname = " + formname, 0);
	document.forms[formname].elements[CMD_CODE_ID].value = cmdCode;
	
	if (isEmpty(targetId)) {
		targetId = '';
	}
	document.forms[formname].elements[TARGET_ID].value = targetId;
			
	if (isEmpty(targetIdIndex)) {
		targetIdIndex = '';
	}
	document.forms[formname].elements[TARGET_ID_INDEX].value = targetIdIndex;
}

function pageSize() {
	var xScroll, yScroll, scrollLeft, scrollTop;
	if (window.innerHeight && window.scrollMaxY) {	
		xScroll = window.innerWidth + window.scrollMaxX;
		yScroll = window.innerHeight + window.scrollMaxY;
	// all but Explorer Mac
	} else if (document.body.scrollHeight > document.body.offsetHeight){
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	// Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
	} else {
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	if(window.pageYOffset) {
		// all except IE    
		scrollTop = window.pageYOffset;    
		scrollLeft = window.pageXOffset; 
	} else if (document.documentElement && document.documentElement.scrollTop) {    
		// IE 6 Strict    
		scrollTop = document.documentElement.scrollTop;    
		scrollLeft = document.documentElement.scrollLeft; 
	} else if(document.body) {    
		// all other IE    
		scrollTop = document.body.scrollTop;    
		scrollLeft = document.body.scrollLeft;   
	} 

	var windowWidth, windowHeight;
	// all except Explorer
	if (self.innerHeight) {
		if(document.documentElement.clientWidth){
			windowWidth = document.documentElement.clientWidth; 
		} else {
			windowWidth = self.innerWidth;
		}
		windowHeight = self.innerHeight;
	// Explorer 6 Strict Mode
	} else if (document.documentElement && document.documentElement.clientHeight) { 
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	// other Explorers
	} else if (document.body) { 
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}	
	// for small pages with total height less then height of the viewport
	if(yScroll < windowHeight){
		pageHeight = windowHeight;
	} else { 
		pageHeight = yScroll;
	}
	// for small pages with total width less then width of the viewport
	if(xScroll < windowWidth){	
		pageWidth = windowWidth;		
	} else {
		pageWidth = xScroll;
	}
	arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight, scrollLeft, scrollTop);
	return arrayPageSize;
}

function initalize() {
	listRefreshLoadingCreate();
	createCustomDialog();
	window.onscroll = windowOnScroll;
	window.onresize = windowOnResize;
}

function closed(settings) {
	if ($.frameClose) {
		$.frameClose(settings);
	} else {
		$.closeDialog();
	}
}

function btnClosed(settings) {
	if ($.frameClose) {
		$.frameClose(settings, true);
	} else {
		$.closeDialog();
	}
}

function displaySingleResult(resObj) {
	_tools.debug("displaySingleResult : resObj.id = " + resObj.id, 0);
	_tools.debug("displaySingleResult : resObj.id_index = " + resObj.id_index, 0);
	_errorMsgObj = {};
	var k = _consts.key;
	var targetId = resObj.id;
	var innerhtml = resObj.detailMsg;
	if (resObj.itemName && resObj.itemName !== '') {
		innerhtml = resObj.itemName + ":" + resObj.detailMsg
	}
	if ($('#' + targetId)[0]) {
		if ($('#' + targetId + '_err')[0]) {
			$('#' + targetId + '_err').html(innerhtml);
			$('#' + targetId + '_err').show();
			$('#' + targetId + '_err').css('display', 'inline-block');
			$('#' + targetId + '_msg').hide();
			_errorMsgObj[targetId + '_err'] = $('#' + targetId + '_err');
		} else {
			$('#' + targetId).mouseover(showErrorMsg);
			$('#' + targetId).mouseout(hideErrorMsg);
			_errorMsgObj[targetId] = $('#' + targetId)[0];
			$.data($('#' + targetId)[0], k.ERROR_MSG, innerhtml);
		}
		$('#' + targetId).removeLoading();
	} else {
		var objs = document.getElementsByName(targetId);
		if (objs && objs.length) {
			if (resObj.id_index) {
				$(objs[resObj.id_index]).mouseover(showErrorMsg);
				$(objs[resObj.id_index]).mouseout(hideErrorMsg);
                _errorMsgObj[targetId + resObj.id_index] = objs[resObj.id_index];
				$.data(objs[resObj.id_index], k.ERROR_MSG, innerhtml);
			} else {
				for (k=0; k < objs.length; k++) {
					$(objs[k]).mouseover(showErrorMsg);
					$(objs[k]).mouseout(hideErrorMsg);
                    _errorMsgObj[targetId + k] = objs[k];
					$.data(objs[k], k.ERROR_MSG, innerhtml);
				}
			}
		}
	}
}

function showErrorMsg(targetId) {
	var obj = event.srcElement;
	var c = _consts.id;
	var k = _consts.key;
	var w = _consts.control;
	if ($.data(obj, k.ERROR_MSG)) {
		var coordinate = $.getCoordinate(obj);
		
		if ($(w.BODY).find('#' + c.POPUP_DIV_ID)[0]) {
            $('#' + c.POPUP_DIV_ID).remove();
		}
        var popup1 = '<div id="' + c.POPUP_DIV_ID + '" class="popupClass">';
        var popup2 = '<div id="' + c.POPUP_DIV_ID + 'L" class="popupClass_l"></div>';
        var popup3 = '<div id="' + c.POPUP_DIV_ID + 'B" class="popupClass_b"></div>';
        var popup4 = '<div id="' + c.POPUP_DIV_ID + 'R" class="popupClass_r"></div>';
        var popup5 = '</div>';
        var popup = $(popup1 + popup2 + popup3 + popup4 + popup5);
		popup.css('left', coordinate.x);
		popup.css('top', coordinate.y + obj.offsetHeight);
		$(popup).appendTo(w.BODY);
        $('#' + c.POPUP_DIV_ID).bgiframe();
		$('#' + c.POPUP_DIV_ID + 'B').html($.data(obj, k.ERROR_MSG));
        $('#' + c.POPUP_DIV_ID).width($('#' + c.POPUP_DIV_ID + 'L').width() + $('#' + c.POPUP_DIV_ID + 'B').width() + $('#' + c.POPUP_DIV_ID + 'R').width() + 20);
	}
}

function hideErrorMsg(targetId) {
	if (this.id) {
		targetId = this.id;
	}
	var c = _consts.id;
	var w = _consts.control;
	if ($(w.BODY).find('#' + c.POPUP_DIV_ID)[0]) {
		$('#' + c.POPUP_DIV_ID).remove();
	}
	if (targetId && typeof targetId === "string" && $('#' + targetId + '_err')[0]) {
		$('#' + targetId + '_err').html('');
		$('#' + targetId + '_err').hide();
		$('#' + targetId + '_msg').show();
	}
}

function getCoordinate(obj) {
	var x = 0, y = 0;
	do {
    	x += obj.offsetLeft;
    	y += obj.offsetTop;
	} while (obj = obj.offsetParent);
	return { 'x': x, 'y': y };
}

function removeErrStyle() {
	$('.errorStyle').removeClass('errorStyle');
	$('.errorStyleSelectBox').removeClass('errorStyleSelectBox');
	var k = _consts.key;
    for (var param in _errorMsgObj) {
    	var obj = _errorMsgObj[param];
    	if (obj) {
	    	if ($.data(obj, k.ERROR_MSG)) {
		        $.removeData(obj, k.ERROR_MSG);
		        _errorMsgObj[param] = null;
	    	} else {
	    		if ($.isPlainObject(obj)) {
		    		obj.hide();
	    		}
	    	}
    	}
    }
}
// ********************************************** 交互方法 **********************************************
function doCommonAjax(cmdCode, asyncflag, settings) {
	_tools.debug("doCommonAjax : cmdCode = " + cmdCode, 0);
	_tools.debug("doCommonAjax : targetId = " + settings.targetId, 0);
	_tools.debug("doCommonAjax : analyze = " + settings.analyze, 0);
	_tools.debug("doCommonAjax : dispcode = " + settings.dispcode, 0);
	_tools.debug("doCommonAjax : fordatas = " + settings.fordatas, 0);
	setAjaxParam(cmdCode, settings.targetId, settings.targetId_idx, settings.formId);
	var k = _consts.key;
	var c = _consts.id;
	if (settings.checks) {
		var checks = settings.checks;
		for (var check in checks) {
			check = checks[check];
			var error = check.judge;
			if (error) {
				var targetId = check.id;
				if ($('#' + targetId)[0]) {
					$('#' + targetId).mouseover(showErrorMsg);
					$('#' + targetId).mouseout(hideErrorMsg);
					$('#' + targetId)[0].style.backgroundColor = "#ff66ff";
					$.data($('#' + targetId)[0], k.ERROR_MSG, check.msg);
					$('#' + targetId).removeLoading();
				}
				loadingOff();
				return;
			}
		}
	}
	
	if (!settings.url) {
		settings.url = serverURL;
	}
    var callObj = new Object();
	if (settings.execute) {
        callObj.execute = settings.execute;
	}
	if (settings.fordatas) {
        callObj.fordatas = settings.fordatas;
	}
	if (settings.finish) {
        callObj.finish = settings.finish;
	}
	if (settings.paging) {
        callObj.paging = settings.paging;
	}
	if (settings.end) {
        callObj.end = settings.end;
	}
	if (settings.replace) {
        callObj.replace = settings.replace;
	}
	if (settings.error) {
        _sysError = settings.error;
	} else {
		_sysError = null;
	}
	if (settings.information) {
        callObj.information = settings.information;
	}
	if (settings.listId) {
        callObj.targetId = settings.listId;
	}
    if (settings.off == undefined || settings.off == null) {
        settings.off = true;
    }
    callObj.off = settings.off;
    _callback[cmdCode] = callObj;
	if (settings.data != null && settings.data != undefined) {
		setValue(settings.data);
	}
	if (settings.index != null && settings.index != undefined) {
		setIndex(settings.index);
	}
	var formData = $('form').serialize();
	if (settings.dispcode) {
		formData = 'dispcode=' + settings.dispcode + '&' + formData;
	}
	if (settings.data != null && settings.data != undefined) {
		formData += '&' + settings.data;
	}
	_tools.debug("doCommonAjax:cmdCode = " + cmdCode + "url = " + settings.url, 0);
	_tools.debug("doCommonAjax:formData = " + formData, 0);
	$.ajax({
		url: settings.url,
		type: 'post',
		async: asyncflag,
		context: document.body,
		data: formData,
		beforeSend: function() {
			if (!_singleFlg) {
				if ('listDisplay' !== settings.analyze 
					&& '99' !== cmdCode 
					&& ($('#' + c.DIALOG_FLG).val() !== '1' || !_initFlg)) {
					loadingOn();
				}
			}
		},
		success: eval(settings.analyze),
		error: displayErr
	});
};

function successFunc(httpObj) {
	_tools.debug("successFunc : httpObj = " + httpObj, 0);	
	var rc = _consts.res.code;
	if (httpObj !== undefined) {
		var result = parseData(httpObj, _targetId, 0, _callback);
		if (!result) {
			return;
		}
        var loadingOffFlg = true;
		_targetId = '';
		if (_callback[result.cmd]) {
			var resultDatas = new Object();
	        resultDatas.cmd = result.cmd;
	        resultDatas.error = result.error;
	        resultDatas.datas = result.resultDatas;
			if (_callback[result.cmd].fordatas) {
                _callback[result.cmd].fordatas(resultDatas);
            }
            if (_callback[result.cmd].execute) {
                _callback[result.cmd].execute(resultDatas);
                loadingOffFlg = false;
            }
			if (_callback[result.cmd].finish) {
				_callback[result.cmd].finish(resultDatas);
			}
			if (_callback[result.cmd].off != undefined && _callback[result.cmd].off != null) {
                loadingOffFlg = _callback[result.cmd].off;
			}
            if (_callback[result.cmd].end) {
                _callback[result.cmd].end(resultDatas);
                loadingOffFlg = false;
            }
		}
        if (result.resResCode !== rc.RES_RESCODE_SESSION_TIMEOUT && loadingOffFlg) {
            loadingOff();
        }
	} else {
		loadingOff();
	}
}

function listDisplay(httpObj) {
	_tools.debug("listDisplay : httpObj = " + httpObj, 0);
	_tools.debug("targetId = " + _targetId, 0);
	var rc = _consts.res.code;
    _singleFlg = false;
	if (httpObj != undefined) {
		var result = parseData(httpObj, _targetId, _pagingTime, _callback);
        var loadingOffFlg = true;
		_targetId = '';
        if (_callback[result.cmd]) {
        	var resultDatas = new Object();
	        resultDatas.cmd = result.cmd;
	        resultDatas.error = result.error;
	        resultDatas.datas = result.resultDatas;
	        resultDatas.totalRows = result.totalRows;
			if (_callback[result.cmd].paging) {
				_callback[result.cmd].paging(resultDatas);
			}
            if (_callback[result.cmd].execute) {
                _callback[result.cmd].execute(resultDatas);
                loadingOffFlg = false;
            }
			if (_callback[result.cmd].finish) {
				_callback[result.cmd].finish(resultDatas);
			}
            if (_callback[result.cmd].off != undefined && _callback[result.cmd].off != null) {
                loadingOffFlg = _callback[result.cmd].off;
            }
			if (_callback[result.cmd].end) {
				_callback[result.cmd].end(resultDatas);
                loadingOffFlg = false;
			}
        }
        if (result.resResCode != rc.RES_RESCODE_SESSION_TIMEOUT && loadingOffFlg) {
            loadingOff();
        }
	} else {
		loadingOff();
	}
	listRefreshLoadingHide(_targetId);
}

function queryDatasSuccess(httpObj) {
	_tools.debug("queryDatasSuccess : httpObj = " + httpObj, 0);
    _singleFlg = false;
    var rc = _consts.res.code;
	if (httpObj != undefined) {
		var result = $.parseJSON(httpObj);
		var header = result.header;
		var cmd = header.cmdCode;
		var error = header.error != '0';
		var resResCode = header.resCode;
		var totalRows = header.totalRows;
		var datas;
		if (result.datas && result.datas.length > 0) {
			datas = result.datas[0].datas;
		}
		if (!datas) {
			datas = result.datas;
		}
        var loadingOffFlg = true;
        var resultDatas = new Object();
        resultDatas.cmd = cmd;
        resultDatas.error = error;
        resultDatas.datas = datas;
        resultDatas.totalRows = totalRows;
		_targetId = '';
        if (_callback[cmd]) {
            if (_callback[cmd].execute) {
                _callback[cmd].execute(resultDatas);
                loadingOffFlg = false;
            }
			if (_callback[cmd].finish) {
				_callback[cmd].finish(resultDatas);
			}
			if (_callback[cmd].off != undefined && _callback[cmd].off != null) {
                loadingOffFlg = _callback[cmd].off;
            }
        }
        if (resResCode != rc.RES_RESCODE_SESSION_TIMEOUT && loadingOffFlg) {
            loadingOff();
        }
	} else {
		loadingOff();
	}
	listRefreshLoadingHide(_targetId);
}

function displayErr(httpObj) {
	_tools.debug("displayErr [" + httpObj.responseText + "]", 0);
    _singleFlg = false;
	loadingOff();
	if (_sysError) {
		_sysError();
	}
	new MsgObj(SYSTEM_ERROR, true).doDispRes();
}


function doSynced(callback) {
    callback();
}

function doListPaging(cmd, settings, sync) {
	settings = jQuery.extend({
		paging:  pagingDisplay,
        synced : sync
    }, settings);
	$('#' + _targetId).list(cmd, settings);
}

function showPagingList(cmdCode, time, settings, sync) {
	if (typeof time !== 'number' && time != null & time != undefined) {
		settings = time;
		time = settings.time;
	}
    _pagingCmd = cmdCode;
    _pagingSettings = settings;
	if (time && !sync) {
		_pagingTime = parseInt(time, 10);
		$('#' + _targetId).slideUp(_pagingTime, function() {
			doListPaging(cmdCode, settings, sync);
		});
	} else {
		doListPaging(cmdCode, settings, sync);
	}
}

function initSettings(settings, cmd) {
	_callback[cmd] = null;
	return jQuery.extend({
		targetId: 		 '',
		targetId_idx: 	 '',
		analyze:         'successFunc',
		formId:          '',
		url:             '',
		data:            null,
		index:           null,
		finish:          null,
		error:           null,
		fordatas:        null,
        off:             true,
        information:     false,
		closeFun:        null,
		replace:         null,
		listId:          null,
		item: function(row, i, max) {
            var tab = '';
            for (var param in row) {
                tab += row[param] + ' : ';
            }
            tab = tab.substring(0, tab.length - 1);
            return i + "/" + max + ": \"" + tab;
        },
		result: function(row) {
            var tab = '';
            for (var param in row) {
                tab = row[param];
                return tab;
            }
            return tab;
        },
		execute:         null
	}, settings);
}

function sync(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	_tools.debug("sync : cmdCode = " + cmdCode, 0);
	_tools.debug("sync : analyze = " + settings.analyze, 0);
	_tools.debug("sync : finish = " + settings.finish, 0);
	_tools.debug("sync : targetId = " + settings.targetId, 0);
	_tools.debug("sync : targetId_idx = " + settings.targetId_idx, 0);
	_tools.debug("sync : formId = " + settings.formId, 0);
	_tools.debug("sync : url = " + settings.url, 0);
	_tools.debug("sync : execute = " + settings.execute, 0);
	_tools.debug("sync : information = " + settings.information, 0);
    var c = _consts.id;
	if ('listDisplay' !== settings.analyze 
		&& '99' != cmdCode 
		&& ($('#' + c.DIALOG_FLG).val() != '1' || !_initFlg)) {
		$.loadingOn();
	}
	doCommonAjax(cmdCode, true, settings);
};

function synchro(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	_tools.debug("sync : cmdCode = " + cmdCode, 0);
	_tools.debug("sync : analyze = " + settings.analyze, 0);
	_tools.debug("sync : analyze = " + settings.finish, 0);
	_tools.debug("sync : targetId = " + settings.targetId, 0);
	_tools.debug("sync : targetId_idx = " + settings.targetId_idx, 0);
	_tools.debug("sync : formId = " + settings.formId, 0);
	_tools.debug("sync : url = " + settings.url, 0);
	_tools.debug("sync : execute = " + settings.execute, 0);
	doCommonAjax(cmdCode, true, settings);
};

function synced(callbackFun) {
	var c = _consts.id;
    if (callbackFun && $.isFunction(callbackFun)) {
        if ($('#' + c.DIALOG_FLG).val() != '1' || !_initFlg) {
            loadingOn();
        }
        window.setTimeout(doSynced, 0, callbackFun);
    }
}

function async(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	doCommonAjax(cmdCode, true, settings);
}

function single(cmdCode, settings, jobj) {
	var targetId = jobj.attr('id');
	_tools.debug('single : targetId = ' + targetId, 0);
	settings = initSettings(settings, cmdCode);
	jobj.createLoading();
	settings.targetId = targetId;
	_singleFlg = true;
	async(cmdCode, settings);
}

function auto(cmdCode, dispCode, settings, jobj) {
    settings = initSettings(settings, cmdCode);
	var url = serverURL + '?clientType=9';
	if (cmdCode) {
		url += '&cmdcode=' + cmdCode;
	}
	if (dispCode) {
		url += '&dispcode=' + dispCode;
	}
	jobj.autocomplete(url, {
		width: 300,
		multiple: false,
		autoFill: false,
		mustMatch: false,
		matchContains: "word",
		formatItem: settings.item,
		formatResult: settings.result
	});
}

function zTree(cmdCode, settings, jobj) {
	var otherParam = [];
	otherParam[0] = 'dispcode';
	otherParam[1] = $('#' + DISP_CODE_ID).val();
	otherParam[2] = 'cmdcode';
	otherParam[3] = cmdCode;
	otherParam[4] = 'clientType';
	otherParam[5] = '3';
	
	var setting = {
		async: {
			enable: true,
			url: "main",
			otherParam: ['dispcode', '000002', 'cmdcode', '4', 'clientType', '3'],
			dataFilter: filter
		},
		data: {
			key: {
				title:"name"
			},
			simpleData: {
				enable: true,
				rootPId : 0
			}
		},
		view: {
			expandSpeed: "slow"
		},
		callback: {
			onClick : zTreeOnClick,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
	$.fn.zTree.init($("#tree"), setting);
}

function tree(cmdCode, settings, jobj) {
	var otherParam = settings.async.otherParam;
	var i = otherParam.length;
	otherParam[i++] = 'dispcode';
	otherParam[i++] = $('#' + DISP_CODE_ID).val();
	otherParam[i++] = 'cmdcode';
	otherParam[i++] = cmdCode;
	otherParam[i++] = 'clientType';
	otherParam[i++] = '3';
	settings.async.otherParam = otherParam;
	$.fn.zTree.init(jobj, settings);
}

function list(cmdCode, settings, jobj) {
	var targetId = jobj.attr('id');
    listRefreshLoadingShow(targetId);
    settings = initSettings(settings, cmdCode);
    settings.analyze = 'listDisplay';
	if (targetId) {
		_targetId = targetId;
		settings.listId = targetId;
	}
	_tools.debug("list : synced = " + settings.synced, 0);
    if (settings.synced) {
        synchro(cmdCode, settings);
    } else {
		sync(cmdCode, settings);
    }
}

function pagingSync(cmdCode, time, settings, jobj) {
	$("#currentpage").val(1);
	_targetId = jobj.attr('id');
	_pagingObj = jobj;
	showPagingList(cmdCode, time, settings, true);
}

function paging(cmdCode, time, settings, jobj) {
	$("#currentpage").val(1);
	_targetId = jobj.attr('id');
	_pagingObj = jobj;
	showPagingList(cmdCode, time, settings, false);
}

function pagingNext(cmdCode, time, settings, jobj) {
	_targetId = jobj.attr('id');
	$("#currentpage").val(parseInt($("#currentpage").val(), 10) + 1);
	showPagingList(cmdCode, time, settings, false);
}

function pagingPrev(cmdCode, time, settings, jobj) {
	_targetId = jobj.attr('id');
	$("#currentpage").val(parseInt($("#currentpage").val(), 10) - 1);
	showPagingList(cmdCode, time, settings, false);
}

function pagingGoto(cmdCode, time, settings, jobj) {
	_targetId = jobj.attr('id');
	var newPar=/^\d+$/;
	var page_num1 = parseInt($("#page_num1").val(), 10);
	$('.pageNobox').each(function() {
		if ($(this).val()) {
			page_num1 = $(this).val();
		}
	});
	if (!newPar.test(page_num1)) {
		page_num1 = 1;
	}
	var totalPages = parseInt($("#totalPages").val(), 10);
	if (page_num1 >= totalPages) {
		page_num1 = totalPages;
	} else if (page_num1 <= 1) {
		page_num1 = 1;
	}
	$("#currentpage").val(page_num1);
    $("#page_num1").val('')
	showPagingList(cmdCode, time, settings, false);
}

function doUpload(id, cmdcode, fun, jobj) {
    jobj.click(function() {
        $.loadingOn();
        if (!cmdcode) {
            cmdcode = '500';
        }
        setAjaxParam(cmdcode);
        var uploadObj;
        if (id) {
        	uploadObj = $('#' + id);
        } else {
        	uploadObj = $('input[type=file]:eq(0)');
        }
        uploadObj.upload(serverURL, $('form').serialize(), function(res) {
            var result = parseData(res);
            loadingOff();
            if (fun && $.isFunction(fun)) {
                fun.call(this, result.error);
            }
        }, '');
    });
}

function doFileUpload(cmdcode, fun, jobj, targetId) {
    jobj.change(function() {
        loadingOn();
        if (!cmdcode) {
            cmdcode = '500';
        }
        setAjaxParam(cmdcode);
        jobj.upload(serverURL, $('form').serialize(), function(res) {
            var result = parseData(res, targetId);
            loadingOff();
            if (fun && $.isFunction(fun)) {
                fun.call(this, result.error);
            }
        }, '');
    });
}

function kindEditor(settings, jobj) {
	var id = jobj.attr('id');
	KindEditor.ready(function(K) {
		var editor = K.create('#' + id, {
			themeType : 'simple',
			items : ["source", "|", "undo", "redo", "|", "preview", "print",
				"template", "code", "cut", "copy", "paste", "plainpaste",
				"wordpaste", "|", "justifyleft", "justifycenter",
				"justifyright", "justifyfull", "insertorderedlist",
				"insertunorderedlist", "indent", "outdent", "subscript",
				"superscript", "clearhtml", "|", "fullscreen", "/", "quickformat", "selectall",
				"formatblock", "fontname",
				"fontsize", "|", "forecolor", "hilitecolor", "bold",
				"italic", "underline", "strikethrough", "lineheight",
				"removeformat", "insertfile", "table", "hr", "emoticons",
				"baidumap", "pagebreak", "anchor", "link", "unlink"],
			noDisableItems : ["source", "fullscreen"]
         });
         settings.callback(editor);
	});
}

function del(cmdCode, index, settings) {
	if(confirm(DELETE_CONFIRM_MESSAGE)){
		setIndex(index);
		sync(cmdCode, settings);
	}
}

function edit(cmdCode, index, settings) {
	setIndex(index);
	sync(cmdCode, settings);
}

function excel(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	if (settings.createExcel) {
		sync(cmdCode, settings);
	} else {
		pageChange($('#' + DISP_CODE_ID).val(), cmdCode);
	}
}

function queryDatas(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	settings.analyze = 'queryDatasSuccess';
	settings.url = serverURL + '?clientType=3';
	window.setTimeout(doCommonAjax, 0, cmdCode, true, settings);
}

function queryDatasSync(cmdCode, settings) {
	settings = initSettings(settings, cmdCode);
	settings.analyze = 'queryDatasSuccess';
	settings.url = serverURL + '?clientType=3';
	doCommonAjax(cmdCode, true, settings);
}

// ********************************************** 私有方法 **********************************************

function createCustomDialog() {
	var w = _consts.control;
	var closeBtnHtml = "<div id=\"dialog-close\"><span id=\"dialog-title\"></span><a href=\"#\" id=\"dialog-btnClose\"><img src=\"img/frame/close.gif\"></a></div>";
	var frameHtml = "<iframe width=\"100%\" height=\"100%\" name=\"dialogContainerFrame\" id=\"dialogContainerFrame\" frameborder=\"0\"></iframe>";
	var loadingHtml = "<div id=\"dialog-loading\"><a href=\"#\" id=\"dialog-loading-link\"><img src=\"img/frame/lightbox/lightbox-ico-loading.gif\"></a></div>";
	var containerHtml = "<div id=\"dialog-container\">" + frameHtml + loadingHtml + "</div>";
	var contentHtml = "<div id=\"dialog-content\" style=\"display: none;\">" + closeBtnHtml + containerHtml + "</div>";
	var overlayHtml = "<div id=\"dialog-overlay\" style=\"display: none;\"></div>";
	var dialogMain = overlayHtml + contentHtml;
	
	$(w.BODY).append(dialogMain);
}
function listRefreshLoadingCreate() {
	var w = _consts.control;
	var c = _consts.id;
	if ($('#' + c.LIST_LOADING_ID).length == 0) {
		var pageSize = $.pageSize();
		var scrollY = pageSize[5];
		var top = pageSize[3] / 2 + scrollY;
		var loadingHtml = '<div id="' + c.LIST_LOADING_ID 
			+ '" class="listRefreshLoading" style="top:'+top+'"><span><img src="img/frame/indicator.gif" align="absmiddle"></span><span id="loading-msg">加载中，请稍候...</span></div>';
		$(w.BODY).append(loadingHtml);
	}
}
function callBackInitScreen() {
	init();	
    postScreenInit();
    postScreenInitAfter();
	loadingOff();
    _initFlg = false;
}

function parseData(httpObj, targetId, time, rep){
	_tools.debug("parseData httpObj = *" + httpObj + "*", 0);
	_tools.debug("parseData targetId = " + targetId, 0);
	_tools.debug("parseData rep = " + rep, 0);
	
	var resAllData = httpObj;
	var rc = _consts.res.code;
	var rk = _consts.res.kind;
	_tools.debug("parseData : resAllData = " + resAllData, 0);
	
	var resAllDataDom = parseResponseDom(resAllData);
	var headerDom;
	for (var i = 0; i < resAllDataDom.length; i++) {
	    if (resAllDataDom[i].nodeName == "HEADER") {
	    	headerDom = resAllDataDom[i];
		    break;
	    }
	}
	var resDisp;
	var resCmd;
	var resDataKind;
	var resResCode;
	var resHeaderMsg;
	var resError;
	var resTotalRows;
	var resPageSize;
	var resItemData;
	var isUpload = false;
	_tools.debug("parseData : headerDom = " + headerDom, 0);
	if (headerDom) {
		resDisp = headerDom.getAttributeNode("dispCode").nodeValue;
		resCmd = headerDom.getAttributeNode("cmdCode").nodeValue;
		resDataKind = headerDom.getAttributeNode("dataKind").nodeValue;
		resResCode = headerDom.getAttributeNode("resCode").nodeValue;
		resHeaderMsg = headerDom.getAttributeNode("headerMsg").nodeValue;
		resError = headerDom.getAttributeNode("error").nodeValue;
		resTotalRows = headerDom.getAttributeNode("totalRows").nodeValue;
		resPageSize = headerDom.getAttributeNode("pageSize").nodeValue;
		_tools.debug("parseData : resTotalRows = " + resTotalRows, 0);
		resItemData = resAllData;
	} else {
		if (resAllData.indexOf('{"header"') == -1) {
			if (targetId) {
				$('#' + targetId).html(resAllData);
			}
			loadingOff();
			return;
		}
		var resDatasJson = eval('(' + resAllData + ')');
		var headerJson = resDatasJson.header;
		resDisp = headerJson.dispCode;
		resCmd = headerJson.cmdCode;
		resDataKind = headerJson.dataKind;
		resResCode = headerJson.resCode;
		resHeaderMsg = headerJson.headerMsg;
		resError = headerJson.error;
		resTotalRows = headerJson.totalRows;
		resPageSize = headerJson.pageSize;
		resItemData = resDatasJson.datas;
		isUpload = true;
	}
	if (resTotalRows == null || resTotalRows == undefined) {
		resTotalRows = '';
	}
	if (resPageSize == null || resPageSize == undefined) {
		resPageSize = '';
	}
	_tools.debug("parseData : resItemData = " + resItemData, 0);
	_tools.debug("parseData : resDataKind = " + resDataKind, 0);
	
	var resultDatas;
	
    var settings;
    if (rep && rep[resCmd]) {
        settings = rep[resCmd];
    }
    if (settings && settings.targetId) {
    	targetId = settings.targetId;
    }
	// resDataKind：【0：单项目；1：多项目】
	if (resDataKind == rk.RES_DATA_KIND_TAB) {
		if (resItemData) {
			resultDatas = parseDataOfTab(resItemData, isUpload);
		}
		if (resResCode == rc.RESCODE_PAGE_CHANGED) {
			var forwardId = headerDom.getAttributeNode("targetId").nodeValue;
			pageChange(forwardId);
			return;
		}
	} else if (resDataKind == rk.RES_DATA_KIND_HTML) {
		$('#pagingTotalRows').val(resTotalRows);
		$('#pagingPageSize').val(resPageSize);
		if (rep && rep[resCmd]) {
			if (rep[resCmd].replace) {
				var reps = rep[resCmd].replace.split("|");
				for (var i = 0; i < reps.length; i++) {
					var repItems = reps[i].split("^");
					resItemData = $.replaceAll(resItemData, repItems[0], repItems[1]);
				}
			}
		}
		parseDataOfHTML(headerDom, resItemData, targetId, time);
	} else if (resDataKind == rk.RES_DATA_KIND_COMPLEX) {
		resultDatas = parseDataOfTab(resItemData, isUpload);
		parseDataOfHTML(headerDom, resItemData, targetId, time);
	}
	var resultObj = displayResult(resHeaderMsg, resResCode, resCmd, resError, settings);
	resultObj.totalRows = resTotalRows;
	resultObj.pageSize = resPageSize;
	resultObj.resResCode = resResCode;
	resultObj.resultDatas = resultDatas;
	_tools.debug("parseData end ", 0);
	return resultObj;
}

function parseDataOfTab(resDataLine, isUpload){
	_tools.debug("parseDataOfTab : resDataLine = " + resDataLine, 0);

	var resultObject = new Object();
	var optionClearFlag = true;
	var clearID = "";
	var resObj = null;
	var targetId;
	var targetIndex;
	var effect;
	var value;
	var text;
	var dataType;
	var bgColor;
	var charColor;
	var errMsgColor;
	var dispStat;
	var maxLen;
	var detailCode;
	var detailMsg;
	var itemName;
	var selected;
    if (!_singleFlg) {
	    removeErrStyle();
    } else {
        _singleFlg = false;
    }
	if (isUpload) {
		for (var i = 0; i < resDataLine.length; i++) {
			var resDataJson = resDataLine[i];
			targetId = resDataJson.targetid + '';
			if (!targetId) {
					continue;
				}
			targetIndex = resDataJson.targetIndex + '';
			effect = resDataJson.effect + '';
			value = resDataJson.value + '';
			text = resDataJson.text + '';
			dataType = resDataJson.dataType + '';
			bgColor = resDataJson.bgColor + '';
			charColor = resDataJson.charColor + '';
			errMsgColor = resDataJson.errMsgColor + '';
			dispStat = resDataJson.dispStat + '';
			maxLen = resDataJson.maxLen + '';
			detailCode = resDataJson.detailCode + '';
			detailMsg = resDataJson.detailMsg + '';
			itemName = resDataJson.itemName + '';
			selected = resDataJson.selected + '';
			if (clearID != (targetId + "" + targetIndex)) {
				clearID = targetId + "" + targetIndex;
				optionClearFlag = true;
			}
			resultObject[targetId + targetIndex] = value;
			resObj = new ResData(targetId, targetIndex, effect, value, text, 
				dataType, bgColor, charColor, errMsgColor, dispStat, maxLen, detailCode, detailMsg, itemName, selected);
			if (dataType == TYPE_OPTION && optionClearFlag) {
				optionClear(resObj.elmObj);
				optionClearFlag = false;
			}
			makeResObjectLive(resObj);
		}
	} else {
		var resDataDom = null;
		var resDataLineDom = parseResponseDom(resDataLine);
		_tools.debug("parseDataOfTab : resDataLineDom.length = " + resDataLineDom.length, 0);
		for (var i = 0; i < resDataLineDom.length; i++) {
		    if (resDataLineDom[i].nodeName != "RESPONSETAB") {
			    continue;
		    }
	    	resDataDom = resDataLineDom[i];
			if (resDataDom != undefined && resDataDom != null) {
				targetId = resDataDom.getAttributeNode("targetId").nodeValue;
				if (!targetId) {
					continue;
				}
				targetIndex = resDataDom.getAttributeNode("targetIndex").nodeValue;
				effect = resDataDom.getAttributeNode("effect").nodeValue;
				value = resDataDom.getAttributeNode("value").nodeValue;
				text = resDataDom.getAttributeNode("text").nodeValue;
				dataType = resDataDom.getAttributeNode("dataType").nodeValue;
				bgColor = resDataDom.getAttributeNode("bgColor").nodeValue;
				charColor = resDataDom.getAttributeNode("charColor").nodeValue;
				errMsgColor = resDataDom.getAttributeNode("errMsgColor").nodeValue;
				dispStat = resDataDom.getAttributeNode("dispStat").nodeValue;
				maxLen = resDataDom.getAttributeNode("maxLen").nodeValue;
				detailCode = resDataDom.getAttributeNode("detailCode").nodeValue;
				detailMsg = resDataDom.getAttributeNode("detailMsg").nodeValue;
				itemName = resDataDom.getAttributeNode("itemName").nodeValue;
				selected = resDataDom.getAttributeNode("selected").nodeValue;
				if (clearID != (targetId + "" + targetIndex)) {
					clearID = targetId + "" + targetIndex;
					optionClearFlag = true;
				}
				resultObject[targetId + targetIndex] = value;
				resObj = new ResData(targetId, targetIndex, effect, value, text, 
					dataType, bgColor, charColor, errMsgColor, dispStat, maxLen, detailCode, detailMsg, itemName, selected);
				if (dataType == TYPE_OPTION && optionClearFlag) {
					optionClear(resObj.elmObj);
					optionClearFlag = false;
				}
				makeResObjectLive(resObj);
			}
		}
	}
	$.removeAllLoading();
	_tools.debug("parseDataOfTab end", 0);
	return resultObject;

}

function parseDataOfHTML(headerDom, resItemData, targetID, time){
	_tools.debug("parseDataOfHTML start", 0);
	var rc = _consts.res.code;
	if (!targetID) {
		targetID = headerDom.getAttributeNode("targetId").nodeValue;
	}
	var targetID_idx = headerDom.getAttributeNode("targetIndex").nodeValue;
	var resResCode = headerDom.getAttributeNode("resCode").nodeValue;
	if(targetID_idx != ''){
		targetID = targetID + TARGETID_SUFFIX + targetID_idx;
	}
	_tools.debug("resItemData = " + resItemData, 0);
	_tools.debug("parseDataOfHTML : targetID = " + targetID, 0);
	_tools.debug("length = " + $('#' + targetID).length, 0);
	if (targetID == 'pageList') {
		$('.pagingTag').each(function() {
			$(this).html(resItemData);
		});
	} else {
		if (resResCode != rc.RES_RESCODE_INPUT_ERROR) {
			$('#' + targetID).html(resItemData);
		}
	}
	if (time) {
		$('#' + targetID).slideDown(time);
//		$('#' + targetID).show(time);
	} else {
		$('#' + targetID).show();
	}

	_tools.debug("parseDataOfTab end");
}

function checkFormId(formId) {
	_tools.debug("checkFormId : formId = " + formId, 0)
	if (isEmpty(formId)) {
		_tools.debug("checkFormId : form name = " + ($(document.forms[0]).attr('name')), 0)
		return $(document.forms[0]).attr('name');
	}
	return formId;
}

function optionClear(elmObj){
	var opLength = elmObj.length;
	for (k=0; k < opLength; k++) {
		elmObj.options[0] = null;
	}
}

function parseResponseDom(arg) {
	_tools.debug("parseResponseDom : arg = " + arg, 0);
	return $('<div>'+arg+'</div>')[0].childNodes; 
}

function makeResObjectLive(resObj) {
	_tools.debug("makeResObjectLive: start", 0);
	var k = _consts.key;
	if (resObj != null && resObj != undefined) {
		resObj.setValue();
		resObj.setBackgroundColor();
		resObj.setTextColor();
		resObj.setState();
		resObj.setMaxLength();
	}
	if (resObj != null && parseInt(resObj.detailCode, 10) >= 0) {
		_tools.debug("makeResObjectLive: resObj.detailCode = " + resObj.detailCode, 0);
		resObj.setErrStyle();
	} else {
		if ($('#' + resObj.id)[0]) {
			$.data($('#' + resObj.id)[0], k.ERROR_MSG, '');
			$('#' + resObj.id).removeLoading();
		}
	}
}

var ResData = function(
	id,
	id_index,
	valueEffectFlg,
	value,
	optionText,
	type,
	backgroundColor,
	textColor,
	errMsgColor,
	dispState,
	maxlength,
	detailCode,
	detailMsg,
	itemName, 
	selected) {
	this.id = id;
	this.id_index = id_index;
	this.valueEffectFlg = valueEffectFlg;
	this.value = value;
	this.optionText = optionText;
	this.type = type;
	this.backgroundColor = backgroundColor;
	this.textColor = textColor;
	this.errMsgColor = errMsgColor;
	this.dispState = dispState;
	this.maxlength = maxlength;
	this.detailCode = detailCode;
	this.detailMsg = detailMsg;
	this.itemName = itemName;
	this.selected = selected;
	var elmObjType;
	try{			
		if (id_index != '') {
			this.elmObj = document.getElementsByName(id)[id_index];
			if (!this.elmObj) {
				this.elmObj = $('#' + id + TARGETID_SUFFIX + id_index)[0];
			}
		} else {
			this.elmObj = $('#' + id)[0];
			if (!this.elmObj) {
				this.elmObj = document.getElementsByName(id);
				if (!this.elmObj.length) {
					this.elmObj = undefined;
				}
			}
		}
		if (this.elmObj) {
			if (!this.elmObj.length || (this.elmObj.length && this.elmObj[0].tagName.toUpperCase() == 'OPTION')) {
				elmObjType = this.elmObj;
			} else {
				elmObjType = this.elmObj[0];
			}
			if (elmObjType.tagName.toUpperCase() == 'INPUT') {
				_tools.debug("ResData : elmObjType.type = " + elmObjType.type, 0);
				if (elmObjType.type == 'text') {
					this.type = TYPE_TEXTBOX;
				} else if (elmObjType.type.toUpperCase() == 'BUTTON') {
					this.type = TYPE_BUTTON;
				} else if (elmObjType.type.toUpperCase() == 'PASSWORD') {
					this.type = TYPE_TEXTBOX;
				} else if (elmObjType.type.toUpperCase() == 'HIDDEN') {
					this.type = TYPE_HIDDEN;
				} else if (elmObjType.type.toUpperCase() == 'CHECKBOX') {
					this.type = TYPE_CHECKBOX;
				} else if (elmObjType.type.toUpperCase() == 'RADIO') {
					this.type = TYPE_RADIO;
				}
			} else if (elmObjType.tagName.toUpperCase() == 'SELECT') {
				if (this.type != TYPE_OPTION) {
					this.type = TYPE_SELECTBOX;
				}
			} else if (elmObjType.tagName.toUpperCase() == 'IMG') {
				this.type = TYPE_IMAGE;
			} else if (elmObjType.tagName.toUpperCase() == 'SPAN') {
				this.type = TYPE_LABEL;
			} else if (elmObjType.tagName.toUpperCase() == 'TEXTAREA') {
				this.type = TYPE_TEXTAREA;
			} else if (elmObjType.tagName.toUpperCase() == 'A') {
				this.type = TYPE_A;
			}
		}
	} catch (e) {
		_tools.debug('create obj : ' + SET_ERROR_MESS + " id=" + id + " index=" + id_index + "\r\n " + e.message, MAIN_DEBUG_FLG);
		loadingOff();
		throw e;
	}
};

ResData.prototype = {
	setValue : function() {
		var f = _consts.flag;
		try{
			if (this.valueEffectFlg == f.VALUE_EFFECT_FLG_VALID && this.elmObj) {
				switch (this.type) {
				case TYPE_LABEL:
					var tempValue = "";
					if (this.value.length > 0) {
						tempValue = this.value;
					}
					this.elmObj.innerHTML = tempValue;
					break;
				case TYPE_TEXTAREA:
					var tempValue = "";
					if (this.value.length > 0) {
						tempValue = this.value;
					}
					$(this.elmObj).html(tempValue);
					break;
				case TYPE_TEXTBOX:
				case TYPE_BUTTON:
				case TYPE_HIDDEN:
					this.elmObj.value = this.value;
					break;
				case TYPE_SELECTBOX:
					if (this.elmObj.options) {
						for (j=0; j < this.elmObj.options.length; j++) {
							if (this.elmObj.options[j].value == this.value) {
								this.elmObj.options[j].selected = true;
								break;
							}
						}
					}
					break;
				case TYPE_CHECKBOX:
					if (!this.elmObj.length) {
						if (this.value == CHECKED_TRUE) {
							this.elmObj.checked = true;
						} else if (this.value == CHECKED_FALSE) {
							this.elmObj.checked = false;
						} else {
							this.elmObj.value = this.value;
						}
					} else {
						for (k=0; k < this.elmObj.length; k++) {
							if (this.value == '') {
								this.elmObj[k].checked = false;
							} else {
								if (this.elmObj[k].value == this.value) {
									this.elmObj[k].checked = true;
									break;
								}
							}
						}
					}
					break;
				case TYPE_RADIO:
					for (k=0; k < this.elmObj.length; k++) {
						if (this.elmObj[k].value == this.value) {
							this.elmObj[k].checked = true;
							break;
						}
					}
					break;
				case TYPE_GUIDE:
						this.elmObj.alt = this.value;
					break;
				case TYPE_IMAGE:
						this.elmObj.src = this.value;
					break;
				case TYPE_A:
						this.elmObj.href = this.value;
					break;
				case TYPE_OPTION:
					var optionstr = '<option ';
					optionstr += ' value="' + this.value + '"';
					if (this.selected && this.selected == 'true') {
						optionstr += ' selected="selected"';
					}
					optionstr += '>';
					optionstr += this.optionText + '</option>';
//					var optionObj = document.createElement("OPTION");
//					debug("this.optionText"+ this.optionText);
//					optionObj.text  = this.optionText;
//					debug("this.value"+ this.value);
//					optionObj.value = this.value;
//					this.elmObj.add(optionObj);
					$(this.elmObj).append(optionstr)
					break;
				}
			}
		} catch (e) {
			_tools.debug(SET_ERROR_VALUE + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	},
	setMaxLength : function() {
		try{
			if (this.type == TYPE_TEXTBOX && this.elmObj) {
				if (this.maxlength != 0) {
					this.elmObj.maxLength = this.maxlength;
					this.elmObj.size = eval(this.maxlength) + ( (eval(this.maxlength)) / 5 * eval(2) );
				}
			}
		} catch (e) {
			_tools.debug(SET_ERROR_MAXLEN + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	},		
	setErrStyle : function() {
		try{
			if (this.elmObj) {
				JK.displaySingleResult(this);
			}
		} catch (e) {
			_tools.debug(SET_ERROR_MESS + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	},		
	setBackgroundColor : function() {
		try {
			if (this.backgroundColor != COLOR_DATA_MUKO 
				&& this.elmObj && !this.elmObj.disabled
				&& (this.elmObj.style || this.elmObj.length)) {
				_tools.debug("ResData : this.elmObj.id = " + this.elmObj.id 
					+ ",backgroundColor = " + this.backgroundColor 
					+ ",type = " + this.type 
					+ ",length = " + this.elmObj.length, 0);
				switch (this.type) {
				case TYPE_LABEL:
				case TYPE_TEXTBOX:
				case TYPE_BUTTON:
				case TYPE_CHECKBOX:
                case TYPE_TEXTAREA:
                case TYPE_HIDDEN:
					if (!this.elmObj.length) {
						if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                            $(this.elmObj).removeClass('errorStyle');
                            hideErrorMsg(this.elmObj.id);
						} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
                            $(this.elmObj).addClass('errorStyle');
                        } else {
							this.elmObj.style.backgroundColor = this.backgroundColor;
						}
					} else {
						for (k=0; k < this.elmObj.length; k++) {
							if (this.value) {
								if (this.elmObj[k].value == this.value) {
									if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                                        $(this.elmObj[k]).removeClass('errorStyle');
                                        hideErrorMsg(this.elmObj.id);
									} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
										$(this.elmObj[k]).addClass('errorStyle');
									} else {
										this.elmObj[k].style.backgroundColor = this.backgroundColor;
									}
									break;
								}
							} else {
								if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                                    $(this.elmObj[k]).removeClass('errorStyle');
                                    hideErrorMsg(this.elmObj.id);
								} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
	                                $(this.elmObj[k]).addClass('errorStyle');
	                            } else {
									this.elmObj[k].style.backgroundColor = this.backgroundColor;
								}
							}
						}
					}
					break;
				case TYPE_RADIO:
					for (k=0; k < this.elmObj.length; k++) {
						if (this.elmObj[k].value == this.value) {
                            if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                                $(this.elmObj[k]).removeClass('errorStyle');
                                hideErrorMsg(this.elmObj.id);
                            } else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
                                   $(this.elmObj[k]).addClass('errorStyle');
                            } else {
                                   this.elmObj[k].style.backgroundColor = this.backgroundColor;
                            }

							break;
						}
					}
					break;
				case TYPE_SELECTBOX:
					if (!this.elmObj.length) {
						if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
							$(this.elmObj).removeClass('errorStyleSelectBox');
                            hideErrorMsg(this.elmObj.id);
						} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
							$(this.elmObj).addClass('errorStyleSelectBox');
                        } else {
							this.elmObj.style.backgroundColor = this.backgroundColor;
						}
					} else {
						for (k=0; k < this.elmObj.length; k++) {
							if (this.value) {
								if (this.elmObj[k].value == this.value) {
									if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                                        $(this.elmObj[k]).removeClass('errorStyleSelectBox');
                                        hideErrorMsg(this.elmObj.id);
									} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
										$(this.elmObj[k]).addClass('errorStyleSelectBox');
									} else {
										this.elmObj[k].style.backgroundColor = this.backgroundColor;
									}
									break;
								}
							} else {
								if (this.backgroundColor == COMMON_NORMAL_BG_SIGN) {
                                    $(this.elmObj[k]).removeClass('errorStyleSelectBox');
                                    hideErrorMsg(this.elmObj.id);
								} else if (this.backgroundColor == COMMON_ERR_BG_SIGN) {
	                                $(this.elmObj[k]).addClass('errorStyleSelectBox');
	                            } else {
									this.elmObj[k].style.backgroundColor = this.backgroundColor;
								}
							}
						}
					}
					break;
				case TYPE_GUIDE:
				case TYPE_IMAGE:
					break;
				}
			}
		} catch (e) {
			_tools.debug(SET_ERROR_BG_COLOR + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	},
	setTextColor : function() {
		try{
			if (this.textColor != COLOR_DATA_MUKO && this.elmObj) {
				switch (this.type) {
				case TYPE_LABEL:
				case TYPE_TEXTBOX:
				case TYPE_BUTTON:
				case TYPE_SELECTBOX:
				case TYPE_CHECKBOX:
					if (!this.elmObj.length) {
						this.elmObj.style.color = this.textColor;
					} else {
						for (k=0; k < this.elmObj.length; k++) {
							if (this.elmObj[k].value == this.value) {
								this.elmObj[k].style.color = this.textColor;
								break;
							}
						}
					}
					break;
				case TYPE_RADIO:
					for(k=0; k < this.elmObj.length; k++){
						if (this.elmObj[k].value == this.value) {
							this.elmObj[k].style.color = this.textColor;
							break;
						}
					}					
					break;
				case TYPE_GUIDE:
				case TYPE_IMAGE:
				case TYPE_HIDDEN:
					break;
				}
			}
		} catch (e) {
			_tools.debug(SET_ERROR_TEXT_COLOR + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	},
	setState : function() {
		try{
			if (this.dispState != DISP_STATE_NOCHANGE && this.elmObj) {
				switch (this.type) {
				case TYPE_HIDDEN:
					break;
				case TYPE_LABEL:
				case TYPE_GUIDE:
				case TYPE_TEXTBOX:
				case TYPE_BUTTON:
				case TYPE_SELECTBOX:
				case TYPE_CHECKBOX:
                case TYPE_TEXTAREA:
					if (!this.elmObj.length) {
						if (this.dispState == DISP_STATE_NORMAL) {
							if (this.elmObj.style.display == 'none') {
								this.elmObj.style.display = 'inline';
							}
							if (this.type == TYPE_TEXTBOX) {
								this.elmObj.readOnly = false;
							} else {
								this.elmObj.disabled = false;
							}
						} else if (this.dispState == DISP_STATE_READONLY) {
							
							if (this.elmObj.style.display == 'none') {
								this.elmObj.style.display = 'inline';
							}
							if (this.type == TYPE_TEXTBOX) {
								this.elmObj.readOnly = true;
							} else {
								this.elmObj.disabled = true;
							}
						} else if (this.dispState == DISP_STATE_NONE) {
							this.elmObj.style.display = 'none';
						} else if (this.dispState == DISP_STATE_NOT_VISIBLED) {
							this.elmObj.style.visibility = 'hidden';
						}
					} else {
						if (this.dispState == DISP_STATE_NORMAL) {
							if (this.type == TYPE_SELECTBOX) {
								this.elmObj.disabled = false;
							} else {
								for (l=0; l < this.elmObj.length; l++) {
									if (this.elmObj[l].style.display == 'none') {
										this.elmObj[l].style.display = 'inline';
									}
									this.elmObj[l].disabled = false;
								}
							}
						} else if (this.dispState == DISP_STATE_READONLY) {
							if (this.type == TYPE_SELECTBOX) {
								this.elmObj.disabled = true;
							} else {
								for (m=0; m < this.elmObj.length; m++) {
									if (this.elmObj[m].style.display == 'none') {
										this.elmObj[m].style.display = 'inline';
									}
									this.elmObj[m].disabled = true;
								}				
							}
						} else if (this.dispState == DISP_STATE_NONE) {
							if (this.type == TYPE_SELECTBOX) {
								this.elmObj.style.display = 'none';
							} else {
								for (n=0; n < this.elmObj.length; n++) {
									this.elmObj[n].style.display = 'none';
								}
							}
						} else if (this.dispState == DISP_STATE_NOT_VISIBLED) {
							if (this.type == TYPE_SELECTBOX) {
								this.elmObj.style.visibility = 'hidden';
							} else {
								for (n=0; n < this.elmObj.length; n++) {
									this.elmObj[n].style.visibility = 'hidden';
								}
							}
						}
					}
					break;
				case TYPE_IMAGE:
					if (this.dispState == DISP_STATE_NORMAL) {
						if (this.elmObj.style.display == 'none') {
							this.elmObj.style.display = 'inline';
						}
						if (this.type == TYPE_TEXTBOX) {
							this.elmObj.readOnly = false;
						} else {
							this.elmObj.disabled = false;
						}
					} else if (this.dispState == DISP_STATE_READONLY) {
						
						if (this.elmObj.style.display == 'none') {
							this.elmObj.style.display = 'inline';
						}
						if (this.type == TYPE_TEXTBOX) {
							this.elmObj.readOnly = true;
						} else {
							this.elmObj.disabled = true;
						}
					} else if (this.dispState == DISP_STATE_NONE) {
						this.elmObj.style.display = 'none';
					} else if (this.dispState == DISP_STATE_NOT_VISIBLED) {
                        this.elmObj.style.visibility = 'hidden';
                    }
					break;
				case TYPE_RADIO:
					if (this.dispState == DISP_STATE_NORMAL) {
						for (l=0; l < this.elmObj.length; l++) {
							if (this.elmObj[l].style.display == 'none') {
								this.elmObj[l].style.display = 'inline';
							}
							this.elmObj[l].disabled = false;
						}
					} else if (this.dispState == DISP_STATE_READONLY) {
						for (m=0; m < this.elmObj.length; m++) {
							if (this.elmObj[m].style.display == 'none') {
								this.elmObj[m].style.display = 'inline';
							}
							this.elmObj[m].disabled = true;
						}				
					} else if (this.dispState == DISP_STATE_NONE) {
						for (n=0; n < this.elmObj.length; n++) {
							this.elmObj[n].style.display = 'none';
						}
					} else if (this.dispState == DISP_STATE_NOT_VISIBLED) {
						for (n=0; n < this.elmObj.length; n++) {
							this.elmObj[n].style.visibility = 'hidden';
						}
					}
					break;
				}
			}
		} catch (e) {
			_tools.debug(SET_ERROR_DISPLAY_STATE + " id=[" + this.id + "]" + "\r\n " + e.message, MAIN_DEBUG_FLG);
			loadingOff();
			throw e;
		}
	}
};

/**
 * 结果信息处理
 *
 * @param resResCode 结果信息
 * @param flag true：详细错误信息表示
 */
function displayResult(resHeaderMsg, resResCode, resCmd, resError, settings) {
//	resResCode = eval(resResCode);
	_tools.debug("displayResult resHeaderMsg = " + resHeaderMsg, 0);
	_tools.debug("displayResult resResCode = " + resResCode, 0);
	_tools.debug("displayResult resError = " + resError, 0);
	_tools.debug("displayResult settings = " + settings, 0);
	var rc = _consts.res.code;
	var resultError = false;
	var errorFlag = false;
	if (resError != '0') {
		errorFlag = true;
	}
	if (resResCode == rc.RES_RESCODE_SESSION_TIMEOUT) {
		sessionTimeOutAction(resCmd);
		resultError = true;
	} else if (resResCode == rc.RES_RESCODE_OTHER_USER_LOGIN) {
		otherUserLoginAction();
		resultError = true;
	} else if (resResCode == rc.RES_RESCODE_INTERNAL_ERR) {
		if (_sysError) {
			_sysError();
		}
		resultError = new MsgObj(resHeaderMsg, true, resResCode, settings).doDispRes();
	} else {
		if (rescodeListener(resResCode)) {
			resultError = new MsgObj(resHeaderMsg, errorFlag, resResCode, settings).doDispRes();
		}
	}
	return {cmd:resCmd,error:resultError};
}

/**
 * 结果序号对象
 *
 */
var MsgObj = function(msg, errFlag, resCode, settings) {
	this.msg = msg;
	this.errFlag = errFlag;
	this.resCode = resCode;
	this.settings = settings;
};
var messageTimer;
MsgObj.prototype = {
	/**
	 * 画面表示结果序号
	 * @param errFlag 错误：true，正常：false
	 */
	doDispRes : function() {
		_tools.debug("MsgObj this.msg = " + this.msg, 0);
		if (this.msg && this.msg != 'null') {
			var customFlg = customMessageControl(this.msg);
			if (this.errFlag) {
				loadingOff();
			}
			if (!customFlg) {
				return this.errFlag;
			}
			var obj = $('#' + RESULT_SCREEN_ID);
			_tools.debug("MsgObj obj = " + obj[0], 0);
			if (this.settings && this.settings.information 
				&& this.resCode != undefined && this.resCode != null && !this.errFlag) {
				showInformationMessage(this.resCode);
				return this.errFlag;
			}
			if (obj[0] == null) {
				// 'result'对象没有在画面添加
				_tools.debug(SET_ERROR_MESS + "\r\n" + "'" + RESULT_SCREEN_ID + "'", true);
			}
			obj.html(this.msg);
			obj.show();
			if (this.errFlag) {
				obj.css('color', '#ff0000');
			} else {
				obj.css('color', '#000000');
			}
			obj.css('font-weight', 'bold');
			obj.css('top', 0);
			if (messageTimer) {
				clearTimeout(messageTimer);
			}
			messageTimer = setTimeout(disappearMessage, 4000);
		}
		return this.errFlag;
	}
};

function disappearMessage() {
	var obj = $('#' + RESULT_SCREEN_ID);
	obj.hide();
	clearTimeout(messageTimer);
}

if (window.attachEvent && !window.addEventListener) {
	window.attachEvent("onunload", function() {
		if(timer != null && timer != 0) {
			clearInterval(timer);
			timer = null;
		}
		if(timerID != 0) {
			clearInterval(timerID);
			timerID = 0;
		}
	});
}

window.JK = JK;
})(window);
$(function() {
	JK.initalize();
})