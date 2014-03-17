var CommonDialog = function() {
}
CommonDialog.prototype = {
	showErrTypeDialog : function(addparam) {
		this.openCustomDialog('000101', '0', 'dialogWidth: 640px; dialogHeight:500px;', addparam);
	},
	showStyleDialog : function(addparam) {
		if (!addparam) {
			addparam = '';
		}
		this.openCustomDialog('000102', '0', 'dialogWidth: 940px; dialogHeight:500px;', 'style=' + addparam);
	},
	showComboBoxDialog : function(addparam) {
		if (!addparam) {
			addparam = '';
		}
		this.openCustomDialog('000103', '0', 'dialogWidth: 540px; dialogHeight:500px;', 'comboBox=' + addparam);
	},
	showPluginDialog : function(dispcode, info) {
		if (!info) {
			info = '';
		}
		info += '&pluginCode=' + dispcode;
		this.openCustomDialog('001000', '9999', 'dialogWidth: 640px; dialogHeight:500px;', 'info=' + info);
	},
	showCalendarDialog : function(callback) {
		
		var dlgOption = "";
		var winVal = "";
		
		var selDate = window.showModalDialog("./html/calendar.html", this.window, "dialogWidth: 190px; dialogHeight: 300px;status:no;location:no;scroll:no;");
		if (selDate != null && selDate.length > 0 && selDate == "blank") {
			callback("", "", "", "");
			return true;
		} else if (selDate != null && selDate.length > 0 && selDate != "closed") {
		
			var dates = selDate.split("/");
			
			var year = parseInt(dates[0], 10);
			var month = parseInt(dates[1], 10);
			var date = parseInt(dates[2], 10);
			
			if (month < 10) {
				month = "0" + month
			}
			
			if (date < 10) {
				date = "0" + date
			}
			
			var ymd = year + "-" + month + "-" + date;				
			callback(year, parseInt(dates[1], 10), parseInt(dates[2], 10), ymd);
			return true;
		}
		return false;
	},
	openWindow : function(dispcode, index, listNo, option, name, ymdFormatType, type, value) {
	
		$('#' + SET_OPENER_INDEX).val(checkParam(index));
		var winOption = "";

		if (dispcode == 0) {
			return true;
		}
		var addparam = '';
		if (type != undefined && type != null && type.length > 0) {
			addparam = "&type=" + type + "&value=" + value;
		}

		var param = "dispcode=" + dispcode 
				+ "&cmdcode=" + REQ_CMDCODE_INIT 
				+ "&screencode=" + $('#' + SCREEN_CODE_ID).val() 
				+ "&clientType=1" 
				+ "&listindex=" + $('#' + SET_OPENER_INDEX).val()
				+ "&number=" + checkParam(listNo)
				+ "&ymdformattype=" + checkParam(ymdFormatType)
				+ addparam;

		winOption = "toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no";
		if (option != undefined) {
			winOption = option;
		}
		var win = window.open(serverURL + "?" + param, name, winOption);
		
		return win;
	},
	openCustomDialog : function(dispcode, cmdcode, option, addparam, modeless, callback) {

		var dlgOption = "";
		var winVal = "";
		
		if (dispcode == 0) {
			return true;
		}		

		var param = "dispcode=" + dispcode 
				+ "&cmdcode=" + cmdcode 
				+ "&screencode=" + $('#' + SCREEN_CODE_ID).val() + "&clientType=1" ;
		if (addparam != undefined) {
			param += "&" + addparam;
		}
					
		dlgOption = "dialogWidth: 550px; dialogHeight:550px;";
		if (option != undefined) {
			dlgOption = option;
		}
		
		var winVal;
		if (modeless == undefined || modeless == false) {
			winVal = window.showModalDialog(serverURL + "?" + param, window, dlgOption);
		} else {
			winVal = window.showModelessDialog(serverURL + "?" + param, window, dlgOption);				
		}
		if (winVal == null || winVal == undefined || $.toString(winVal) == 'cancel' || $.toString(winVal) == 'closed') {
			return false;
		} else {
			if(callback != null && callback != undefined){
				callback(winVal);
			}
			return true;
		}
	},
	setValueDialog : function(dispcode, cmdcode, option, targetId, modeless, addparam) {

		var dlgOption = "";
		var winVal = "";
		
		if (dispcode == 0) {
			return true;
		}		

		var param = "dispcode=" + dispcode 
				+ "&cmdcode=" + cmdcode 
				+ "&screencode=" + $('#' + SCREEN_CODE_ID).val();
		if (addparam != undefined) {
			param += "&" + addparam;
		}
					
		dlgOption = "dialogWidth: 550px; dialogHeight:550px;";
		if (option != undefined) {
			dlgOption = option;
		}
		var winVal;
		if (modeless == undefined || modeless == false) {
			winVal = window.showModalDialog(serverURL + "?" + param, window, dlgOption);
		} else {
			winVal = window.showModelessDialog(serverURL + "?" + param, window, dlgOption);				
		}

		if (winVal == 'cancel' || winVal == 'closed' || winVal == undefined) {
			return false;
		} else {
			jQuery('#' + targetId).attr('value', winVal);
			return true;
		}
	}
};
