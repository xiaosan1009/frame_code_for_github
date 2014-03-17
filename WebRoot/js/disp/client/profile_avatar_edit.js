if (typeof Sina == "undefined") {
	Sina = {}
}
Sina.pkg = function(ns) {
	if (!ns || !ns.length) {
		return null
	}
	var levels = ns.split(".");
	var nsobj = Sina;
	for (var i = (levels[0] == "Sina") ? 1 : 0; i < levels.length; ++i) {
		nsobj[levels[i]] = nsobj[levels[i]] || {};
		nsobj = nsobj[levels[i]]
	}
	return nsobj
};
function $E(oID) {
	var node = typeof oID == "string" ? document.getElementById(oID) : oID;
	if (node != null) {
		return node
	} else {
	}
	return null
}
function $C(tagName) {
	return document.createElement(tagName)
}
function $N(name) {
	return document.getElementsByName(name)
}
function $G() {
}
function $G2() {
}
function v5SendLog() {
}
try {
	document.execCommand("BackgroundImageCache", false, true)
} catch (e) {
}
(function() {
	var funcName = "trace";
	var _traceList = [];
	var _startTime = new Date().valueOf();
	var _curTime = new Date().valueOf();
	var _runTime;
	var _trace = function(sText, oOption, sBgColor) {
		oOption = oOption || {};
		if (typeof oOption == "string") {
			oOption = {
				"color" : oOption
			};
			if (typeof sBgColor != "undefined" && typeof sBgColor == "string") {
				oOption.bgColor = sBgColor
			}
		}
		_traceList[_traceList.length] = [sText, oOption]
	};
	var _traceError = function(oError) {
		_trace(oError, {
					"color" : "#F00"
				})
	};
	_trace.error = _traceError;
	_trace.traceList = _traceList;
	_trace.toString = function() {
		return "Trace调试已关闭"
	};
	window[funcName] = _trace;
	window.traceError = _traceError
})();
Sina.pkg("Core");
if (typeof Core == "undefined") {
	Core = Sina.Core
}
Sina.pkg("Core.Array");
Core.Array.each = function(ar, insp) {
	var r = [];
	for (var i = 0; i < ar.length; i++) {
		var x = insp(ar[i], i);
		if (x !== null) {
			r.push(x)
		}
	}
	return r
};
function Jobs() {
	this._jobTable = []
}
Jobs.prototype = {
	_registedJobTable : {},
	initialize : function() {
	},
	_registJob : function(jobName, rel) {
		this._registedJobTable[jobName] = rel
	},
	add : function(jobName) {
		this._jobTable.push(jobName)
	},
	start : function() {
		var jobs = this._jobTable;
		var regJobs = this._registedJobTable;
		var i = 0;
		var joblen = this._jobTable.length;
		var getTime = function() {
			return new Date().valueOf()
		};
		var interNum = window.setInterval(function() {
					if (i >= joblen) {
						clearInterval(interNum);
						return
					}
					var jobName = jobs[i];
					var job = regJobs[jobName];
					i++;
					if (typeof job == "undefined") {
						trace("<b>Job[" + jobName + "] is undefiend!!!</b>", {
									"color" : "#900",
									"bgColor" : "#FFF;"
								});
						return
					}
					var _try = true;
					var _start = getTime();
					try {
						job.call()
					} catch (e) {
						trace("<b>Job[" + jobName + "] failed!!!</b>", {
									"color" : "#900",
									"bgColor" : "#666;"
								});
						traceError(e);
						_try = false
					} finally {
						if (_try) {
							var _end = getTime();
							trace(	"<b>Job[" + jobName + "] done in "
											+ (_end - _start) + "ms.</b>", {
										"color" : "#0F0",
										"bgColor" : "#666;"
									})
						}
					}
				}, 10)
	},
	call : function(jobName, args) {
		if (typeof this._registedJobTable[jobName] != "undefined") {
			this._registedJobTable[jobName].apply(this, args)
		} else {
			trace("<b>Job[" + jobName + "] is undefined!!!</b>", {
						"color" : "#900",
						"bgColor" : "#FFF;"
					})
		}
	}
};
$registJob = function(name, rel) {
	Jobs.prototype._registJob(name, rel)
};
$callJob = function(name) {
	var args = [];
	if (arguments.length > 1) {
		Core.Array.foreach(arguments, function(v, i) {
					args[i] = v
				});
		args.shift()
	}
	Jobs.prototype.call(name, args)
};
Sina.pkg("Utils");
if (typeof Utils == "undefined") {
	Utils = Sina.Utils
}
Sina.pkg("Utils.Cookie");
Utils.Cookie.getCookie = function(name) {
	name = name.replace(/([\.\[\]\$])/g, "\\$1");
	var rep = new RegExp(name + "=([^;]*)?;", "i");
	var co = document.cookie + ";";
	var res = co.match(rep);
	if (res) {
		return res[1] || ""
	} else {
		return ""
	}
};
Utils.Cookie.setCookie = function(name, value, expire, path, domain, secure) {
	var cstr = [];
	cstr.push(name + "=" + escape(value));
	if (expire) {
		var dd = new Date();
		var expires = dd.getTime() + expire * 3600000;
		dd.setTime(expires);
		cstr.push("expires=" + dd.toGMTString())
	}
	if (path) {
		cstr.push("path=" + path)
	}
	if (domain) {
		cstr.push("domain=" + domain)
	}
	if (secure) {
		cstr.push(secure)
	}
	document.cookie = cstr.join(";")
};
Utils.Cookie.deleteCookie = function(name) {
	document.cookie = name + "=;" + "expires=Fri, 31 Dec 1999 23:59:59 GMT;"
};
Sina.pkg("Sina.Member");
if (typeof console == "undefined") {
	var console = {
		log : function(obj) {
		}
	}
}
(function() {
	var userInfo = unescape(Utils.Cookie.getCookie("SUP"));
	if (userInfo != "") {
		userInfo = userInfo.split("uid=");
		if (userInfo.length == 2) {
			Sina.Member.uid = userInfo[1].split("&")[0]
		}
	}
})();
String.prototype.len = function() {
	return this.replace(/[^\x00-\xff]/g, "rr").length
};
String.prototype.sub = function(n) {
	var r = /[^\x00-\xff]/g;
	if (this.replace(r, "mm").length <= n) {
		return this
	}
	var m = Math.floor(n / 2);
	for (var i = m; i < this.length; i++) {
		if (this.substr(0, i).replace(r, "mm").length >= n) {
			return this.substr(0, i)
		}
	}
	return this
};
Sina.pkg("Core.Function");
Core.Function.bind2 = function(fFunc, object) {
	var __method = fFunc;
	return function() {
		return __method.apply(object, arguments)
	}
};
Function.prototype.bind2 = function(object) {
	var __method = this;
	return function() {
		return __method.apply(object, arguments)
	}
};
Sina.pkg("Core.Class");
Core.Class.extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property]
	}
	return destination
};
Sina.pkg("Core.Events");
Core.Events.addEvent = function(elm, func, evType, useCapture) {
	var _el = $E(elm);
	if (_el == null) {
		trace("addEvent 找不到对象：" + elm);
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
Sina.pkg("Core.Dom");
Sina.pkg("Core.Base");
(function() {
	var Detect = function() {
		var ua = navigator.userAgent.toLowerCase();
		this.$IE = /msie/.test(ua);
		this.$OPERA = /opera/.test(ua);
		this.$MOZ = /gecko/.test(ua);
		this.$IE5 = /msie 5 /.test(ua);
		this.$IE55 = /msie 5.5/.test(ua);
		this.$IE6 = /msie 6/.test(ua);
		this.$IE7 = /msie 7/.test(ua);
		this.$SAFARI = /safari/.test(ua);
		this.$winXP = /windows nt 5.1/.test(ua);
		this.$winVista = /windows nt 6.0/.test(ua);
		this.$FF2 = /Firefox\/2/i.test(ua)
	};
	Core.Base.detect = new Detect()
})();
Core.Dom.getStyle = function(el, property) {
	switch (property) {
		case "opacity" :
			var val = 100;
			try {
				val = el.filters["DXImageTransform.Microsoft.Alpha"].opacity
			} catch (e) {
				try {
					val = el.filters("alpha").opacity
				} catch (e) {
				}
			}
			return val / 100;
		case "float" :
			property = "styleFloat";
		default :
			var value = el.currentStyle ? el.currentStyle[property] : null;
			return (el.style[property] || value)
	}
};
if (!Core.Base.detect.$IE) {
	Core.Dom.getStyle = function(el, property) {
		if (property == "float") {
			property = "cssFloat"
		}
		try {
			var computed = document.defaultView.getComputedStyle(el, "")
		} catch (e) {
			traceError(e)
		}
		return el.style[property] || computed ? computed[property] : null
	}
}
Core.Events.removeEvent = function(oElement, fHandler, sName) {
	var _el = $E(oElement);
	if (_el == null) {
		trace("removeEvent 找不到对象：" + oElement);
		return
	}
	if (typeof fHandler != "function") {
		return
	}
	if (typeof sName == "undefined") {
		sName = "click"
	}
	if (_el.addEventListener) {
		_el.removeEventListener(sName, fHandler, false)
	} else {
		if (_el.attachEvent) {
			_el.detachEvent("on" + sName, fHandler)
		}
	}
	fHandler[sName] = null
};
Core.Class.AsPrototype = {};
Core.Class.create = function() {
	return function(t) {
		if (t != Core.Class.AsPrototype) {
			this.initialize.apply(this, arguments)
		}
	}
};
Sina.pkg("Utils.Io");
Utils.Io.iframeUpload = Core.Class.create();
Core.Class.extend(Utils.Io.iframeUpload.prototype, {
			initialize : function(frameId) {
				this.ifr = $E(frameId);
				if (!this.ifr) {
					return
				}
				this.ifr.actionCb = null
			},
			post : function(formId, url, cb, start, end, timeout, err_func) {
				this.f = $E(formId);
				if (!this.f) {
					return
				}
				this.f.action = url || this.f.action;
				this.f.method = "POST";
				this.f.target = this.ifr.id;
				this.ifr.actionCb = cb;
				this.ifr.ifrEvt = Core.Function.bind2(this.checkLoad, this.ifr);
				Core.Events.addEvent(this.ifr, this.ifr.ifrEvt, "load");
				this.ifr.onProgress = start || function() {
				};
				this.ifr.onProgressEnd = end || function() {
				};
				this.ifr.onError = err_func || function() {
				};
				this.f.submit();
				this.ifr.onProgress();
				if (timeout) {
					this.ifr.timeoutFlag = setTimeout(Core.Function.bind2(
									function() {
										this.abort();
										try {
											timeout.cb()
										} catch (e) {
										}
									}, this), timeout.interval || 10000)
				}
			},
			checkLoad : function() {
				if (this.onProgressEnd) {
					this.onProgressEnd()
				}
				if (this.timeoutFlag) {
					clearTimeout(this.timeoutFlag)
				}
				if (!this.actionCb) {
					return
				}
				try {
					var body = this.contentWindow.document.body;
					this.actionCb(body.childNodes[0].nodeValue);
					Core.Events.removeEvent(this, this.ifrEvt, "load")
				} catch (e) {
					this.onError(e.reason);
					trace('iframeUpload error:"' + e.reason + '"')
				}
			},
			abort : function() {
				this.ifr.actionCb = null;
				this.ifr.onProgress = function() {
				};
				this.ifr.onProgressEnd = function() {
				};
				this.ifr.location = "about:blank";
				Core.Events.removeEvent(this.ifr, this.ifr.ifrEvt, "load")
			}
		});
$G("iframeUpload", Utils.Io.iframeUpload, "xs", "通过Iframe 上传图片");
Core.Dom.addHTML = function(oParentNode, sHTML) {
	oParentNode.insertAdjacentHTML("BeforeEnd", sHTML)
};
if (!$IE) {
	Core.Dom.addHTML = function(oParentNode, sHTML) {
		var oRange = oParentNode.ownerDocument.createRange();
		oRange.setStartBefore(oParentNode);
		var oFrag = oRange.createContextualFragment(sHTML);
		oParentNode.appendChild(oFrag)
	}
}
Sina.pkg("Core.String");
Core.String.j2o = function(str) {
	if (!str || str == "") {
		return null
	}
	try {
		var o = window.eval("(" + str + ")");
		return o
	} catch (e) {
		trace("j2o : 数据分析出错");
		traceError(e);
		return null
	}
};
Core.String.trimHead = function(str) {
	return str.replace(/^(\u3000|\s|\t)*/gi, "")
};
Core.String.trimTail = function(str) {
	return str.replace(/(\u3000|\s|\t)*$/gi, "")
};
Core.String.trim = function(str) {
	return Core.String.trimHead(Core.String.trimTail(str))
};
$registJob("editAvatar", function() {
	var pos = {};
	var submit_btn = $E("submit_btn");
	var w = $E("w");
	var bgDiv = $E("bgDiv");
	var formNode = $E("pic_form");
	var frameNode = $E("frame");
	if (submit_btn == null) {
		trace("头像编辑" + "submit_btn" + "不存在");
		return
	}
	var isIE = (document.all) ? true : false;
	var isIE6 = isIE
			&& ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
	var BindAsEventListener = function(object, fun) {
		var args = Array.prototype.slice.call(arguments).slice(2);
		return function(event) {
			return fun.apply(object, [event || window.event].concat(args))
		}
	};
	var ImgCropper = Core.Class.create();
	ImgCropper.prototype = {
		initialize : function(container, handle, url, options) {
			this._Container = $E(container);
			this._layHandle = $E(handle);
			this._layBase = $E("_layBase");
			this._layBase.onload = Core.Function.bind2(resetImg, this);
			this.Url = this._layBase.src;
			this._layCropper = this._Container.appendChild(document
					.createElement("img"));
			this._layCropper.id = "cropper";
			this._layCropper.onload = Core.Function.bind2(this.SetPos, this);
			this._tempImg = document.createElement("img");
			this._tempImg.onload = Core.Function.bind2(this.SetSize, this);
			this.SetOptions(options);
			this.Opacity = Math.round(this.options.Opacity);
			this.Color = this.options.Color;
			this.Scale = !!this.options.Scale;
			this.Ratio = Math.max(this.options.Ratio, 1);
			this.Width = Math.round(this.options.Width);
			this.Height = Math.round(this.options.Height);
			this._drag = new Drag(this._layHandle, {
						Limit : true,
						mxContainer : $E("_layBase"),
						onMove : Core.Function.bind2(this.SetPos, this),
						Transparent : true
					});
			this.Resize = !!this.options.Resize;
			if (this.Resize) {
				var op = this.options, _resize = new Resize(this._layHandle, {
							Max : true,
							Scale : true,
							Ratio : 1,
							mxContainer : $E("_layBase"),
							onResize : Core.Function.bind2(this.SetPos, this)
						});
				op.RightDown && (_resize.Set(op.RightDown, "right-down"));
				op.LeftDown && (_resize.Set(op.LeftDown, "left-down"));
				op.RightUp && (_resize.Set(op.RightUp, "right-up"));
				op.LeftUp && (_resize.Set(op.LeftUp, "left-up"));
				op.Right && (_resize.Set(op.Right, "right"));
				op.Left && (_resize.Set(op.Left, "left"));
				op.Down && (_resize.Set(op.Down, "down"));
				op.Up && (_resize.Set(op.Up, "up"));
				this.Min = !!this.options.Min;
				this.minWidth = Math.round(this.options.minWidth);
				this.minHeight = Math.round(this.options.minHeight);
				this._resize = _resize
			}
			this._Container.style.position = "relative";
			this._Container.style.overflow = "hidden";
			this._layHandle.style.zIndex = 200;
			this._layCropper.style.zIndex = 100;
			this._layBase.style.position = this._layCropper.style.position = "absolute";
			this._layBase.style.top = this._layBase.style.left = this._layCropper.style.top = this._layCropper.style.left = 0;
			this.Init()
		},
		SetOptions : function(options) {
			this.options = {
				Opacity : 50,
				Color : "",
				Width : 0,
				Height : 0,
				Resize : false,
				Right : "",
				Left : "",
				Up : "",
				Down : "",
				RightDown : "",
				LeftDown : "",
				RightUp : "",
				LeftUp : "",
				Min : false,
				minWidth : 50,
				minHeight : 50,
				Scale : false,
				Ratio : 0,
				Preview : "",
				viewWidth : 0,
				viewHeight : 0
			};
			Core.Class.extend(this.options, options || {})
		},
		Init : function() {
			this.Color && (this._Container.style.backgroundColor = this.Color);
			this._tempImg.src = this._layCropper.src = this.Url;
			if (isIE) {
				this._layBase.style.filter = "alpha(opacity:" + this.Opacity
						+ ")"
			} else {
				this._layBase.style.opacity = this.Opacity / 100
			}
			this._view && (this._view.src = this.Url);
			if (this.Resize) {
				with (this._resize) {
					Scale = this.Scale;
					Ratio = this.Ratio;
					Min = this.Min;
					minWidth = this.minWidth;
					minHeight = this.minHeight
				}
			}
		},
		SetPos : function() {
			if (isIE6) {
				with (this._layHandle.style) {
					zoom = 0.9;
					zoom = 1
				}
			}
			var p = this.GetPos();
			pos = this.GetPos();
			this._layCropper.style.clip = "rect(" + p.Top + "px "
					+ (p.Left + p.Width) + "px " + (p.Top + p.Height) + "px "
					+ p.Left + "px)";
			this.SetPreview()
		},
		SetPreview : function() {
			if (this._view) {
				var p = this.GetPos(), s = this.GetSize(p.Width, p.Height,
						this.viewWidth, this.viewHeight), scale = s.Height
						/ p.Height;
				var pHeight = this._layBase.height * scale, pWidth = this._layBase.width
						* scale, pTop = p.Top * scale, pLeft = p.Left * scale;
				with (this._view.style) {
					width = pWidth + "px";
					height = pHeight + "px";
					top = -pTop + "px ";
					left = -pLeft + "px";
					clip = "rect(" + pTop + "px " + (pLeft + s.Width) + "px "
							+ (pTop + s.Height) + "px " + pLeft + "px)"
				}
			}
		},
		SetSize : function() {
			var s = this.GetSize(this._tempImg.width, this._tempImg.height,
					this.Width, this.Height);
			this._layBase.style.width = this._layCropper.style.width = s.Width
					+ "px";
			this._layBase.style.height = this._layCropper.style.height = s.Height
					+ "px";
			this._drag.mxRight = s.Width;
			this._drag.mxBottom = s.Height;
			if (this.Resize) {
				this._resize.mxRight = s.Width;
				this._resize.mxBottom = s.Height
			}
			resetImg()
		},
		GetPos : function() {
			with (this._layHandle) {
				return {
					Top : offsetTop,
					Left : offsetLeft,
					Width : offsetWidth,
					Height : offsetHeight
				}
			}
		},
		GetSize : function(nowWidth, nowHeight, fixWidth, fixHeight) {
			var iWidth = nowWidth, iHeight = nowHeight, scale = iWidth
					/ iHeight;
			if (fixHeight) {
				iWidth = (iHeight = fixHeight) * scale
			}
			if (fixWidth && (!fixHeight || iWidth > fixWidth)) {
				iHeight = (iWidth = fixWidth) / scale
			}
			return {
				Width : iWidth,
				Height : iHeight
			}
		}
	};
	var Drag = Core.Class.create();
	Drag.prototype = {
		initialize : function(drag, options) {
			this.Drag = $E(drag);
			this._x = this._y = 0;
			this._marginLeft = this._marginTop = 0;
			this._fM = BindAsEventListener(this, this.Move);
			this._fS = Core.Function.bind2(this.Stop, this);
			this.SetOptions(options);
			this.Limit = !!this.options.Limit;
			this.mxLeft = parseInt(this.options.mxLeft);
			this.mxRight = parseInt(this.options.mxRight);
			this.mxTop = parseInt(this.options.mxTop);
			this.mxBottom = parseInt(this.options.mxBottom);
			this.LockX = !!this.options.LockX;
			this.LockY = !!this.options.LockY;
			this.Lock = !!this.options.Lock;
			this.onStart = this.options.onStart;
			this.onMove = this.options.onMove;
			this.onStop = this.options.onStop;
			this._Handle = $E(this.options.Handle) || this.Drag;
			this._mxContainer = $E(this.options.mxContainer) || null;
			this.Drag.style.position = "absolute";
			if (isIE && !!this.options.Transparent) {
				with (this._Handle.appendChild(document.createElement("div")).style) {
					width = height = "100%";
					backgroundColor = "#fff";
					filter = "alpha(opacity:0)";
					fontSize = 0
				}
			}
			this.Repair();
			Core.Events.addEvent(this._Handle, BindAsEventListener(this,
							this.Start), "mousedown")
		},
		SetOptions : function(options) {
			this.options = {
				Handle : "",
				Limit : false,
				mxLeft : 0,
				mxRight : 9999,
				mxTop : 0,
				mxBottom : 9999,
				mxContainer : "",
				LockX : false,
				LockY : false,
				Lock : false,
				Transparent : false,
				onStart : function() {
				},
				onMove : function() {
				},
				onStop : function() {
				}
			};
			Core.Class.extend(this.options, options || {})
		},
		Start : function(oEvent) {
			if (this.Lock) {
				return
			}
			this.Repair();
			this._x = oEvent.clientX - this.Drag.offsetLeft;
			this._y = oEvent.clientY - this.Drag.offsetTop;
			this._marginLeft = parseInt(Core.Dom.getStyle(this.Drag,
					"marginLeft"))
					|| 0;
			this._marginTop = parseInt(Core.Dom
					.getStyle(this.Drag, "marginTop"))
					|| 0;
			Core.Events.addEvent(document, this._fM, "mousemove");
			Core.Events.addEvent(document, this._fS, "mouseup");
			if (isIE) {
				Core.Events.addEvent(this._Handle, this._fS, "losecapture");
				this._Handle.setCapture()
			} else {
				Core.Events.addEvent(window, this._fS, "blur");
				oEvent.preventDefault()
			}
			this.onStart()
		},
		Repair : function() {
			if (this.Limit) {
				this.mxRight = Math.max(this.mxRight, this.mxLeft
								+ this.Drag.offsetWidth);
				this.mxBottom = Math.max(this.mxBottom, this.mxTop
								+ this.Drag.offsetHeight);
				!this._mxContainer
						|| Core.Dom.getStyle(this._mxContainer, "position") == "relative"
						|| Core.Dom.getStyle(this._mxContainer, "position") == "absolute"
						|| (this._mxContainer.style.position = "relative")
			}
		},
		Move : function(oEvent) {
			if (this.Lock) {
				this.Stop();
				return
			}
			window.getSelection
					? window.getSelection().removeAllRanges()
					: document.selection.empty();
			var iLeft = oEvent.clientX - this._x, iTop = oEvent.clientY
					- this._y;
			if (this.Limit) {
				var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
				if (!!this._mxContainer) {
					mxLeft = Math.max(mxLeft, 0);
					mxTop = Math.max(mxTop, 0);
					mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
					mxBottom = Math.min(mxBottom,
							this._mxContainer.clientHeight)
				}
				iLeft = Math.max(Math.min(iLeft, mxRight
										- this.Drag.offsetWidth), mxLeft);
				iTop = Math.max(Math.min(iTop, mxBottom
										- this.Drag.offsetHeight), mxTop)
			}
			if (!this.LockX) {
				this.Drag.style.left = iLeft - this._marginLeft + "px"
			}
			if (!this.LockY) {
				this.Drag.style.top = iTop - this._marginTop + "px"
			}
			this.onMove()
		},
		Stop : function() {
			Core.Events.removeEvent(document, this._fM, "mousemove");
			Core.Events.removeEvent(document, this._fS, "mouseup");
			if (isIE) {
				Core.Events.removeEvent(this._Handle, this._fS, "losecapture");
				this._Handle.releaseCapture()
			} else {
				Core.Events.removeEvent(window, this._fS, "blur")
			}
			this.onStop()
		}
	};
	var Resize = Core.Class.create();
	Resize.prototype = {
		initialize : function(obj, options) {
			this._obj = $E(obj);
			this._styleWidth = this._styleHeight = this._styleLeft = this._styleTop = 0;
			this._sideRight = this._sideDown = this._sideLeft = this._sideUp = 0;
			this._fixLeft = this._fixTop = 0;
			this._scaleLeft = this._scaleTop = 0;
			this._mxSet = function() {
			};
			this._mxRightWidth = this._mxDownHeight = this._mxUpHeight = this._mxLeftWidth = 0;
			this._mxScaleWidth = this._mxScaleHeight = 0;
			this._fun = function() {
			};
			this._borderX = (parseInt(Core.Dom.getStyle(this._obj,
					"borderLeftWidth")) || 0)
					+ (parseInt(Core.Dom
							.getStyle(this._obj, "borderRightWidth")) || 0);
			this._borderY = (parseInt(Core.Dom.getStyle(this._obj,
					"borderTopWidth")) || 0)
					+ (parseInt(Core.Dom.getStyle(this._obj,
							"borderBottomWidth")) || 0);
			this._fR = BindAsEventListener(this, this.Resize);
			this._fS = Core.Function.bind2(this.Stop, this);
			this.SetOptions(options);
			this.Max = !!this.options.Max;
			this._mxContainer = $E(this.options.mxContainer) || null;
			this.mxLeft = Math.round(this.options.mxLeft);
			this.mxRight = Math.round(this.options.mxRight);
			this.mxTop = Math.round(this.options.mxTop);
			this.mxBottom = Math.round(this.options.mxBottom);
			this.Min = !!this.options.Min;
			this.minWidth = Math.round(this.options.minWidth);
			this.minHeight = Math.round(this.options.minHeight);
			this.Scale = !!this.options.Scale;
			this.Ratio = Math.max(this.options.Ratio, 0);
			this.onResize = this.options.onResize;
			this._obj.style.position = "absolute";
			!this._mxContainer
					|| Core.Dom.getStyle(this._mxContainer, "position") == "relative"
					|| (this._mxContainer.style.position = "relative")
		},
		SetOptions : function(options) {
			this.options = {
				Max : false,
				mxContainer : "",
				mxLeft : 0,
				mxRight : 9999,
				mxTop : 0,
				mxBottom : 9999,
				Min : false,
				minWidth : 50,
				minHeight : 50,
				Scale : false,
				Ratio : 0,
				onResize : function() {
				}
			};
			Core.Class.extend(this.options, options || {})
		},
		Set : function(resize, side) {
			var resize = $E(resize), fun;
			if (!resize) {
				return
			}
			switch (side.toLowerCase()) {
				case "up" :
					fun = this.Up;
					break;
				case "down" :
					fun = this.Down;
					break;
				case "left" :
					fun = this.Left;
					break;
				case "right" :
					fun = this.Right;
					break;
				case "left-up" :
					fun = this.LeftUp;
					break;
				case "right-up" :
					fun = this.RightUp;
					break;
				case "left-down" :
					fun = this.LeftDown;
					break;
				case "right-down" :
				default :
					fun = this.RightDown
			}
			Core.Events.addEvent(resize, BindAsEventListener(this, this.Start,
							fun), "mousedown")
		},
		Start : function(e, fun, touch) {
			e.stopPropagation ? e.stopPropagation() : (e.cancelBubble = true);
			this._fun = fun;
			this._styleWidth = this._obj.clientWidth;
			this._styleHeight = this._obj.clientHeight;
			this._styleLeft = this._obj.offsetLeft;
			this._styleTop = this._obj.offsetTop;
			this._sideLeft = e.clientX - this._styleWidth;
			this._sideRight = e.clientX + this._styleWidth;
			this._sideUp = e.clientY - this._styleHeight;
			this._sideDown = e.clientY + this._styleHeight;
			this._fixLeft = this._styleLeft + this._styleWidth;
			this._fixTop = this._styleTop + this._styleHeight;
			if (this.Scale) {
				this.Ratio = Math.max(this.Ratio, 0) || this._styleWidth
						/ this._styleHeight;
				this._scaleLeft = this._styleLeft + this._styleWidth / 2;
				this._scaleTop = this._styleTop + this._styleHeight / 2
			}
			if (this.Max) {
				var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
				if (!!this._mxContainer) {
					mxLeft = Math.max(mxLeft, 0);
					mxTop = Math.max(mxTop, 0);
					mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
					mxBottom = Math.min(mxBottom,
							this._mxContainer.clientHeight)
				}
				mxRight = Math.max(mxRight, mxLeft
								+ (this.Min ? this.minWidth : 0)
								+ this._borderX);
				mxBottom = Math.max(mxBottom, mxTop
								+ (this.Min ? this.minHeight : 0)
								+ this._borderY);
				this._mxSet = function() {
					this._mxRightWidth = mxRight - this._styleLeft
							- this._borderX;
					this._mxDownHeight = mxBottom - this._styleTop
							- this._borderY;
					this._mxUpHeight = Math.max(this._fixTop - mxTop, this.Min
									? this.minHeight
									: 0);
					this._mxLeftWidth = Math.max(this._fixLeft - mxLeft,
							this.Min ? this.minWidth : 0)
				};
				this._mxSet();
				if (this.Scale) {
					this._mxScaleWidth = Math.min(this._scaleLeft - mxLeft,
							mxRight - this._scaleLeft - this._borderX)
							* 2;
					this._mxScaleHeight = Math.min(this._scaleTop - mxTop,
							mxBottom - this._scaleTop - this._borderY)
							* 2
				}
			}
			Core.Events.addEvent(document, this._fR, "mousemove");
			Core.Events.addEvent(document, this._fS, "mouseup");
			if (isIE) {
				Core.Events.addEvent(this._obj, this._fS, "losecapture");
				this._obj.setCapture()
			} else {
				Core.Events.addEvent(window, this._fS, "blur");
				e.preventDefault()
			}
		},
		Resize : function(e) {
			window.getSelection
					? window.getSelection().removeAllRanges()
					: document.selection.empty();
			this._fun(e);
			with (this._obj.style) {
				width = this._styleWidth + "px";
				height = this._styleHeight + "px";
				top = this._styleTop + "px";
				left = this._styleLeft + "px"
			}
			this.onResize()
		},
		Up : function(e) {
			this.RepairY(this._sideDown - e.clientY, this._mxUpHeight);
			this.RepairTop();
			this.TurnDown(this.Down)
		},
		Down : function(e) {
			this.RepairY(e.clientY - this._sideUp, this._mxDownHeight);
			this.TurnUp(this.Up)
		},
		Right : function(e) {
			this.RepairX(e.clientX - this._sideLeft, this._mxRightWidth);
			this.TurnLeft(this.Left)
		},
		Left : function(e) {
			this.RepairX(this._sideRight - e.clientX, this._mxLeftWidth);
			this.RepairLeft();
			this.TurnRight(this.Right)
		},
		RightDown : function(e) {
			this.RepairAngle(e.clientX - this._sideLeft, this._mxRightWidth,
					e.clientY - this._sideUp, this._mxDownHeight);
			this.TurnLeft(this.LeftDown) || this.Scale
					|| this.TurnUp(this.RightUp)
		},
		RightUp : function(e) {
			this.RepairAngle(e.clientX - this._sideLeft, this._mxRightWidth,
					this._sideDown - e.clientY, this._mxUpHeight);
			this.RepairTop();
			this.TurnLeft(this.LeftUp) || this.Scale
					|| this.TurnDown(this.RightDown)
		},
		LeftDown : function(e) {
			this.RepairAngle(this._sideRight - e.clientX, this._mxLeftWidth,
					e.clientY - this._sideUp, this._mxDownHeight);
			this.RepairLeft();
			this.TurnRight(this.RightDown) || this.Scale
					|| this.TurnUp(this.LeftUp)
		},
		LeftUp : function(e) {
			this.RepairAngle(this._sideRight - e.clientX, this._mxLeftWidth,
					this._sideDown - e.clientY, this._mxUpHeight);
			this.RepairTop();
			this.RepairLeft();
			this.TurnRight(this.RightUp) || this.Scale
					|| this.TurnDown(this.LeftDown)
		},
		RepairX : function(iWidth, mxWidth) {
			iWidth = this.RepairWidth(iWidth, mxWidth);
			if (this.Scale) {
				var iHeight = this.RepairScaleHeight(iWidth);
				if (this.Max && iHeight > this._mxScaleHeight) {
					iHeight = this._mxScaleHeight;
					iWidth = this.RepairScaleWidth(iHeight)
				} else {
					if (this.Min && iHeight < this.minHeight) {
						var tWidth = this.RepairScaleWidth(this.minHeight);
						if (tWidth < mxWidth) {
							iHeight = this.minHeight;
							iWidth = tWidth
						}
					}
				}
				this._styleHeight = iHeight;
				this._styleTop = this._scaleTop - iHeight / 2
			}
			this._styleWidth = iWidth
		},
		RepairY : function(iHeight, mxHeight) {
			iHeight = this.RepairHeight(iHeight, mxHeight);
			if (this.Scale) {
				var iWidth = this.RepairScaleWidth(iHeight);
				if (this.Max && iWidth > this._mxScaleWidth) {
					iWidth = this._mxScaleWidth;
					iHeight = this.RepairScaleHeight(iWidth)
				} else {
					if (this.Min && iWidth < this.minWidth) {
						var tHeight = this.RepairScaleHeight(this.minWidth);
						if (tHeight < mxHeight) {
							iWidth = this.minWidth;
							iHeight = tHeight
						}
					}
				}
				this._styleWidth = iWidth;
				this._styleLeft = this._scaleLeft - iWidth / 2
			}
			this._styleHeight = iHeight
		},
		RepairAngle : function(iWidth, mxWidth, iHeight, mxHeight) {
			iWidth = this.RepairWidth(iWidth, mxWidth);
			if (this.Scale) {
				iHeight = this.RepairScaleHeight(iWidth);
				if (this.Max && iHeight > mxHeight) {
					iHeight = mxHeight;
					iWidth = this.RepairScaleWidth(iHeight)
				} else {
					if (this.Min && iHeight < this.minHeight) {
						var tWidth = this.RepairScaleWidth(this.minHeight);
						if (tWidth < mxWidth) {
							iHeight = this.minHeight;
							iWidth = tWidth
						}
					}
				}
			} else {
				iHeight = this.RepairHeight(iHeight, mxHeight)
			}
			this._styleWidth = iWidth;
			this._styleHeight = iHeight
		},
		RepairTop : function() {
			this._styleTop = this._fixTop - this._styleHeight
		},
		RepairLeft : function() {
			this._styleLeft = this._fixLeft - this._styleWidth
		},
		RepairHeight : function(iHeight, mxHeight) {
			iHeight = Math.min(this.Max ? mxHeight : iHeight, iHeight);
			iHeight = Math.max(this.Min ? this.minHeight : iHeight, iHeight, 0);
			return iHeight
		},
		RepairWidth : function(iWidth, mxWidth) {
			iWidth = Math.min(this.Max ? mxWidth : iWidth, iWidth);
			iWidth = Math.max(this.Min ? this.minWidth : iWidth, iWidth, 0);
			return iWidth
		},
		RepairScaleHeight : function(iWidth) {
			return Math.max(Math.round((iWidth + this._borderX) / this.Ratio
							- this._borderY), 0)
		},
		RepairScaleWidth : function(iHeight) {
			return Math.max(Math.round((iHeight + this._borderY) * this.Ratio
							- this._borderX), 0)
		},
		TurnRight : function(fun) {
			if (!(this.Min || this._styleWidth)) {
				this._fun = fun;
				this._sideLeft = this._sideRight;
				this.Max && this._mxSet();
				return true
			}
		},
		TurnLeft : function(fun) {
			if (!(this.Min || this._styleWidth)) {
				this._fun = fun;
				this._sideRight = this._sideLeft;
				this._fixLeft = this._styleLeft;
				this.Max && this._mxSet();
				return true
			}
		},
		TurnUp : function(fun) {
			if (!(this.Min || this._styleHeight)) {
				this._fun = fun;
				this._sideDown = this._sideUp;
				this._fixTop = this._styleTop;
				this.Max && this._mxSet();
				return true
			}
		},
		TurnDown : function(fun) {
			if (!(this.Min || this._styleHeight)) {
				this._fun = fun;
				this._sideUp = this._sideDown;
				this.Max && this._mxSet();
				return true
			}
		},
		Stop : function() {
			Core.Events.removeEvent(document, this._fR, "mousemove");
			Core.Events.removeEvent(document, this._fS, "mouseup");
			if (isIE) {
				Core.Events.removeEvent(this._obj, this._fS, "losecapture");
				this._obj.releaseCapture()
			} else {
				Core.Events.removeEvent(window, this._fS, "blur")
			}
		}
	};
	var ic = new ImgCropper(
			"bgDiv",
			"dragDiv",
			"http://images.cnblogs.com/cnblogs_com/cloudgamer/143727/r_xx2.jpg",
			{
				Color : "#000",
				Resize : true,
				Scale : true,
				Ratio : 1,
				Right : "rRight",
				Left : "rLeft",
				Up : "rUp",
				Down : "rDown",
				RightDown : "rRightDown",
				LeftDown : "rLeftDown",
				RightUp : "rRightUp",
				LeftUp : "rLeftUp"
			});
	function resetImg() {
		oldheight = $E("_layBase").offsetHeight;
		oldwidth = $E("_layBase").offsetWidth;
		if ($E("_layBase").offsetWidth > bgDiv.offsetWidth
				|| $E("_layBase").offsetHeight > bgDiv.offsetHeight) {
			if ($E("_layBase").offsetHeight / bgDiv.offsetHeight > $E("_layBase").offsetWidth
					/ bgDiv.offsetWidth) {
				oldheight = $E("_layBase").offsetHeight;
				$E("_layBase").style.height = bgDiv.offsetHeight + "px";
				$E("cropper").style.height = bgDiv.offsetHeight + "px";
				trace("new" + $E("cropper").style.height);
				$E("_layBase").style.width = ($E("_layBase").offsetWidth * (bgDiv.offsetHeight / oldheight))
						+ "px";
				$E("cropper").style.width = $E("_layBase").style.width
			} else {
				oldwidth = $E("_layBase").offsetWidth;
				$E("_layBase").style.width = bgDiv.offsetWidth + "px";
				$E("cropper").style.width = bgDiv.offsetWidth + "px";
				$E("_layBase").style.height = $E("_layBase").offsetHeight
						* (bgDiv.offsetWidth / oldwidth) + "px";
				$E("cropper").style.height = $E("_layBase").style.height
			}
		}
	}
	function resetDrag() {
		var oldheight = $E("_layBase").offsetHeight;
		var oldwidth = $E("_layBase").offsetWidth;
		$E("dragDiv").style.left = 0;
		$E("dragDiv").style.top = 0;
		if ($E("_layBase").offsetWidth < 180
				|| $E("_layBase").offsetHeight < 180) {
			$E("dragDiv").style.height = Math.min(oldheight, oldwidth) + "px";
			$E("dragDiv").style.width = $E("dragDiv").style.height
		}
	}
	cIFM("pic_fra");
	var s = false;
	Core.Events.addEvent(submit_btn, function() {
				submit_form();
				s = true
			}, "click");
	Core.Events.addEvent(document, function(event) {
				event = event ? event : window.event;
				if (event.keyCode == 13) {
					submit_form()
				}
			}, "keydown");
	var a = 0;
	function submit_form() {
		a = 0;
		var form_action = formNode.action;
		var img = document.createElement("img");
		img.src = $E("_layBase").src;
		img.width = $E("_layBase").offsetWidth <= oldwidth
				? oldwidth
				: $E("_layBase").offsetWidth;
		img.height = $E("_layBase").offsetHeight <= oldheight
				? oldheight
				: $E("_layBase").offsetHeight;
		var rawWidth = oldwidth;
		var rawHeight = oldheight;
		var x1 = dot2(pos.Left / $E("_layBase").offsetWidth);
		var x2 = dot2((pos.Left + pos.Width) / $E("_layBase").offsetWidth);
		var y1 = dot2(pos.Top / $E("_layBase").offsetHeight);
		var y2 = dot2((pos.Top + pos.Height) / $E("_layBase").offsetHeight);
		var x = x1 * rawWidth;
		var y = y1 * rawHeight;
		var w = (y2 - y1) * rawHeight;
		var mtime = $E("pic_form").mtime.value;
		var ut = $E("pic_form").ut.value;
		var json = $E("pic_form").json.value;
		var and = (/\?/.test(form_action) ? "&" : "?");
		form_action += and + "mtime=" + mtime + "&ut=" + ut + "&json=" + json;
		form_action += "&x=" + x + "&y=" + y + "&w=" + w;
		var upload_clss = new Utils.Io.iframeUpload("pic_fra").post("pic_form",
				form_action, function(txt) {
					var json = Core.String.j2o(Core.String.trim(txt));
					submitResult(json)
				}, null, null, 0, function(txt) {
					if (a == 0) {
						alert("提交失败，请重试！")
					}
					a++
				})
	}
	function l(u, c) {
		var s = document.createElement("script");
		s.type = "text/javascript";
		s[document.all ? "onreadystatechange" : "onload"] = function() {
			if (document.all && this.readyState != "loaded"
					&& this.readyState != "complete") {
				return
			}
			this[document.all ? "onreadystatechange" : "onload"] = null;
			this.parentNode.removeChild(this);
			if (c) {
				c()
			}
		};
		s.src = u;
		document.getElementsByTagName("head")[0].appendChild(s)
	}
	function dot2(num) {
		if (num < 0) {
			num = 0
		}
		return Math.round(num * 1000) / 1000
	}
	function cIFM(sFrameName) {
		sFrameName = sFrameName || "";
		if ($E(sFrameName)) {
			Core.Dom.removeNode($E(sFrameName))
		}
		Core.Dom.addHTML(document.body, "<iframe name='" + sFrameName
						+ "' id='" + sFrameName
						+ "' style='display: none;'></iframe>")
	}
	function submitResult(oResult) {
		if (oResult.ret == 1) {
			var returnUrl = formNode.r.value
					|| "/member/avatar_upload.php?tips=1";
			var url = "/member/aj_avatar_notice.php?uid=" + formNode.uid.value;
			if (url, function() {
				window.location = returnUrl
			}) {
			}
			setTimeout(function() {
						window.location = returnUrl
					}, 2000)
		} else {
			$E("avatarModifyError").innerHTML = "抱歉，由于系统问题您的头像保存失败，请稍后重试。"
		}
	}
});
$registJob("btnState", function() {
			var submit_btn = $E("submit_btn");
			var confirm_bind_btn = $E("confirm_bind_btn");
			Core.Events.addEvent(submit_btn, function() {
						submit_btn.className = "bind_btn bind_hover"
					}, "mouseover");
			Core.Events.addEvent(submit_btn, function() {
						submit_btn.className = "bind_btn"
					}, "mouseout");
			Core.Events.addEvent(confirm_bind_btn, function() {
						confirm_bind_btn.className = "bind_btn bind_hover"
					}, "mouseover");
			Core.Events.addEvent(confirm_bind_btn, function() {
						confirm_bind_btn.className = "bind_btn"
					}, "mouseout")
		});
function main() {
	var job = new Jobs();
	job.add("editAvatar");
	job.add("btnState");
	job.start()
};