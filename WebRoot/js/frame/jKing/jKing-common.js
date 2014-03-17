// ********************************************
// common.js
// @author King
// ********************************************
function displaySingleResult(resObj) {
	JK.displaySingleResult(resObj);
}

(function ($) {
	var mySetInterval = setTimeout; 
	window.setTimeout = function(callback, interval) { 
		var args = Array.prototype.slice.call(arguments, 2); 
		function callFn(){callback.apply(null, args);} 
		return mySetInterval(callFn, interval); 
	} 
	/*
	* 判断是否为数值类型
	* @param num 要判断的数据
	*/
	$.isNumber = function(num){
	    if (num.match(/[^0-9]/g)) {
	        return false;
	    }
		return true;
	}
	
	/*
	* 全部替换
	* @param str 要替换的数据源
	* @param reg1 要被替换的字符
	* @param reg2 替换后的字符
	*/
	$.replaceAll = function(str, reg1, reg2) {
		while (str.indexOf(reg1) != -1) {
			str = str.replace(reg1, reg2);
		}
		return str;
	}
	
	$.getCoordinate = function(obj) {
    	return JK.getCoordinate(obj);
	}
	
	$.cloneObj = function(obj, constructor) {
	    return JK.cloneObj(obj, constructor);
	}
	
	$.toJson = function(o) {
		return JK.toJson(o);
	}
	
})(jQuery);
