(function ($) {
	var flashId = '';
	var requiredMajorVersion = 9;
	var requiredMinorVersion = 0;
	var requiredRevision = 124;
	$.fn.createFlash = function () {
		var ret = getParams(arguments);
		var params = '';
		for (var i in ret.params) {
  			params += '&' + i + '=' + ret.params[i];
		}
		params = params.substring(1);
		var dispcode;
		var cmdcode;
		var display = ret.display;
		if (!display) {
			display = '0';
		}
		var hasProductInstall = DetectFlashVer(6, 0, 65);
	
		var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);
		if (hasProductInstall && !hasRequestedVersion) {
			var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
			var MMredirectURL = window.location;
		    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
		    var MMdoctitle = document.title;
		    $(this).flashId = this.attr('id') + '_flash';
			AC_FL_RunContent(
				"src", "playerProductInstall",
				"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
				"width", "0",
				"height", "0",
				"align", "middle",
				"id", this.attr('id') + '_flash',
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", ret.params['flashName'],
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer",
				"targetId", this.attr('id'),
				"FlashVars", params,
				"display", display
			);
		} else if (hasRequestedVersion) {
			flashId = this.attr('id') + '_flash';
			AC_FL_RunContent(
				"src", ret.params['flashPath'] + ret.params['flashName'],
				"width", "100%",
				"height", "100%",
				"align", "middle",
				"id", this.attr('id') + '_flash',
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", ret.params['flashName'],
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer",
				"targetId", this.attr('id'),
				"FlashVars", params,
				"display", display
			);
		} else {
			var alternateContent = 'Alternate HTML content should be placed here. '
			+ 'This content requires the Adobe Flash Player. '
			+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
			document.write(alternateContent);
		}
	};
	$.fn.sendData = function(params) {
		$('#' + flashId)[0].sendData(params);
	};
})(jQuery);

function getParams(args){
	var ret = new Object();
	ret.params = new Object();
	for (var i = 0; i < args.length; i = i + 2) {
		var currArg = args[i].toLowerCase();    
		
		switch (currArg){	
			case "display":
				ret.display = args[i+1];
			break;
			default:
				ret.params[args[i]] = args[i+1];
		}
	}
	return ret;
}

