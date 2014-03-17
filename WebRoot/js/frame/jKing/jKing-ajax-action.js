(function ($) {
	
	var _settings = {
		ajax : function() {
			this.targetId = '';
			this.targetId_idx = '';
			this.analyze = 'successFunc';
			this.formId = '';
			this.url = '';
			this.data = null;
			this.index = null;
			// 方法有3个参数分别为：1.cmd 方法ID;2.error 错误标识;3.totalrows 数据总条数
			this.finish = null;
			this.error = null;
			// 方法有2个参数分别为：1.cmd 方法ID;2.error 错误标识
			this.execute = null;
			// 返回单项目数据的回调方法: 1.单项目数据;2.error 错误标识;3.方法ID
			this.fordatas = null;
			this.item = null;
			this.result = null;
			this.replace = null;
			this.listId = null;
	        this.off = true;
	        this.information = false;
		},
		dialogWin : function() {
			this.dispcode = '';
			this.cmdcode = '0';
			this.width = '550px';
			this.height = '550px';
			this.toolbar = 'yes';
			this.location = 'yes';
			this.directories = 'yes';
			this.status = 'yes';
			this.menubar = 'yes';
			this.resizable = 'yes';
			this.param = '';
			this.modeless = false;
			this.callback = null;
		},
		kindEditor : function() {
			this.callback = null;
		},
		dialog : function() {
	        this.l_width = 300;
	        this.l_height = 300;
	        this.overlayBgColor = '#000000';
	        this.overlayOpacity = 0.8;
	        this.imageLoading = 'img/frame/lightbox/lightbox-ico-loading.gif';
	        this.imageBtnClose = 'img/frame/close.gif';
	        this.keyToClose = 'c';
	        this.data = null;
	        this.closeFun = null;
	        this.title = '';
	    },
	    ztree : function() {
	    	this.async = {
				enable: true,
				contentType: "application/x-www-form-urlencoded",
				type: "post",
				dataType: "text",
				url: "main",
				autoParam: [],
				otherParam: [],
				dataFilter: null
			};
			this.data = {
				key: {
					children: "children",
					name: "name",
					title: "name",
					url: "url"
				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: null
				},
				keep: {
					parent: false,
					leaf: false
				}
			};
			this.view = {
				expandSpeed: "slow",
				addDiyDom: null,
				autoCancelSelected: true,
				dblClickExpand: true,
				fontCss: {},
				nameIsHTML: false,
				selectedMulti: true,
				showIcon: true,
				showLine: true,
				showTitle: true
			};
			this.callback = {
				beforeAsync:null,
				beforeClick:null,
				beforeDblClick:null,
				beforeRightClick:null,
				beforeMouseDown:null,
				beforeMouseUp:null,
				beforeExpand:null,
				beforeCollapse:null,
				beforeRemove:null,
	
				onAsyncError:null,
				onAsyncSuccess:null,
				onNodeCreated:null,
				onClick:null,
				onDblClick:null,
				onRightClick:null,
				onMouseDown:null,
				onMouseUp:null,
				onExpand:null,
				onCollapse:null,
				onRemove:null
			}
	    }
	    
	};
	
//	var _settings = {
//		ajaxSetting : {
//			targetId : '',
//			targetId_idx : '',
//			analyze : 'successFunc',
//			formId : '',
//			url : '',
//			data : null,
//			index : null,
//			// 方法有3个参数分别为：1.cmd 方法ID,2.error 错误标识,3.totalrows 数据总条数
//			finish : null,
//			error : null,
//			// 方法有2个参数分别为：1.cmd 方法ID,2.error 错误标识
//			execute : null,
//			// 返回单项目数据的回调方法: 1.单项目数据,2.error 错误标识,3.方法ID
//			fordatas : null,
//			item : null,
//			result : null,
//			replace : null,
//			listId : null,
//	        off : true,
//	        information : false
//		}
//	};
	
	$.excel = function(cmdCode, settings) {
		JK.excel(cmdCode, settings);
	}
	
	$.queryDatas = function(cmdCode, settings) {
		JK.queryDatas(cmdCode, settings);
	}
	
	$.queryDatasSync = function(cmdCode, settings) {
		JK.queryDatasSync(cmdCode, settings);
	}
	
	/*
	* 异步调用后台方法
	*/
	$.sync = function(cmdCode, settings) {
		JK.sync(cmdCode, settings);
	};
	
	/*
	* 同步调用后台方法
	*/
	$.synchro = function(cmdCode, settings) {
		JK.synchro(cmdCode, settings);
	};
    
    $.synced = function(callback) {
        JK.synced(callback);
    }
    
	/*
	* 异步调用后台方法
	*/
	$.async = function(cmdCode, settings) {
		JK.async(cmdCode, settings);
	}
	
	$.del = function(cmdCode, index, settings) {
		JK.del(cmdCode, index, settings);
	}
	
	$.edit = function(cmdCode, index, settings) {
		JK.edit(cmdCode, index, settings);
	}
	
	$.closed = function(settings) {
		JK.closed(settings);
	}
	
	$.btnClosed = function(settings) {
		JK.btnClosed(settings);
	}
	
	$.setAjaxParam = function(cmdCode, targetId, targetIdIndex, formId){
		JK.setAjaxParam(cmdCode, targetId, targetIdIndex, formId);
	}
	
	$.calendar = function(callback) {
		new CommonDialog().showCalendarDialog(callback);
	};
	
	$.dialogWin = function(settings) {
		var dispcode = settings.dispcode;
		var cmdcode = settings.cmdcode;
		var option = 'dialogWidth:' + settings.width 
				   + ';dialogHeight:' + settings.height 
				   + ';toolbar:' + settings.toolbar 
				   + ';location:' + settings.location 
				   + ';directories:' + settings.directories
				   + ';status:' + settings.status
				   + ';menubar:' + settings.menubar
				   + ';resizable:' + settings.resizable;
		var addparam = settings.param;
		var modeless = settings.modeless;
		var callback = settings.callback;
		new CommonDialog().openCustomDialog(dispcode, cmdcode, option, addparam, modeless, callback);
	};
	
	$.DialogWinSettings = _settings.dialogWin;
	
	$.Settings = _settings.ajax;
	
	$.KindEditorSettings = _settings.kindEditor;
	
	$.DialogSettings = _settings.dialog;
	
	$.ZtreeSettings = _settings.ztree;
})(jQuery);

jQuery.fn.extend(
    {
    	single : function(cmdCode, settings) {
			JK.single(cmdCode, settings, this);
		},
		
		auto : function(cmdCode, dispCode, settings) {
            JK.auto(cmdCode, dispCode, settings, this);
		},
	
		tree : function(cmdCode, settings) {
            JK.tree(cmdCode, settings, this);
		},
	
		list : function(cmdCode, settings) {
            JK.list(cmdCode, settings, this);
		},
		
		pagingSync : function(cmdCode, time, settings) {
			JK.pagingSync(cmdCode, time, settings, this);
		},
		
		paging : function(cmdCode, time, settings) {
			JK.paging(cmdCode, time, settings, this);
		},
		
		pagingNext : function(cmdCode, time, settings) {
			JK.pagingNext(cmdCode, time, settings, this);
		},
		
		pagingPrev : function(cmdCode, time, settings) {
			JK.pagingPrev(cmdCode, time, settings, this);
		},
		
		pagingGoto : function(cmdCode, time, settings) {
			JK.pagingGoto(cmdCode, time, settings, this);
		},
        
        doUpload : function(id, cmdcode, fun) {
            JK.doUpload(id, cmdcode, fun, this);
        },
        
        doFileUpload : function(cmdcode, fun) {
            JK.doFileUpload(cmdcode, fun, this);
        },
        
        uploadExcelList : function(targetId, cmdcode, fun) {
            JK.doFileUpload(cmdcode, fun, this, targetId);
        },
        
        kindEditor : function(settings) {
        	JK.kindEditor(settings, this);
        }
    }
)

function bindClose(fun, doc) {
	$.frameClose = fun;
	$.doc = doc;
}

function postListInit() {
    $.loadingOff();
}
