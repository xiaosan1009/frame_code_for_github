$(function() {
	initScreen('000002');
})

function jumpFunction(dispcode){
	$.loadingOn();
	$('#operateFrame').attr('src', 'main?dispcode=' + dispcode + '&cmdcode=0&clientType=1');
}
function startMenuDisplay() {
	if ($("#startmenudiv").css('display') == 'none') {
		$("#startmenudiv").show();
		$("#startmenudiv").animate({bottom : '+=500px', opacity:'1'}, 500);
	} else {
		$("#startmenudiv").animate({bottom : '-=500px', opacity:'0'}, 500, function() {
			$("#startmenudiv").hide();
		});
	}
}
function init() {
	$(".startmenu").click(function() {
		startMenuDisplay();
	}).parent("td").hover(function() {
		$(".startmenu").removeClass("startmenu").addClass("startmenuhover");
	}, function() {
		$(".startmenuhover").removeClass("startmenuhover").addClass("startmenu");
	});
	var ztsett = new $.ZtreeSettings();
	ztsett.callback.onClick = zTreeOnClick;
	ztsett.callback.onAsyncSuccess = zTreeOnAsyncSuccess;
	ztsett.async.dataFilter = filter;
	$('#tree').tree('4', ztsett);
	menuLayoutInit();
}
function menuLayoutInit() {
	var pageSize = $.pageSize();
	var header_height = $('#header').height() + 2;
	var operate_bottom_height = 0;
	var footer_height =  $('#footer').height();
	var content_height = pageSize[1] - header_height - footer_height;
	$('#main').height(pageSize[1]);
	$('#content').height(content_height);
	$('#container').height(content_height);
//	$('#list1').height(content_height);
	$('#operate').height(content_height - operate_bottom_height);
	$('#operateFrame').height($('#operate').height());
}

function userLogout() {
	$.sync(2);
}
function showQuickNewMenu() {
	var selectedEffect = "clip";
	var options = {};
	if($("#quicknew").css("display") == "none") {
		$("#defaultprogram").hide();
		$("#allmenus").hide();
		$("#quicknew").toggle(selectedEffect, options, 400);
		$("#quicknewmenulink").html("<div class=\"navicon\"><img src=\"img/disp/menu/icons/navbackicon.png\"></div> <div class=\"menuslinkbutton\">返回</div>");
	} else {
		$("#allmenus").hide();
		$("#quicknew").hide();
		$("#defaultprogram").toggle(selectedEffect, options, 400);
		$("#quicknewmenulink").html("<div class=\"navicon\"><img src=\"img/disp/menu/icons/navicon.png\"></div> <div class=\"menuslinkbutton\">快捷新建</div>");
	}
}

function showAllMenu() {
	var selectedEffect = "clip";
	var options = {};
	if($("#allmenus").css("display") == "none") {
		$("#quicknew").hide();
		$("#defaultprogram").hide();
		$("#allmenus").css("overflow","hidden");
		$("#allmenus").toggle(selectedEffect, options,400,showAllMenuCallBack);
		$("#allmenuslink").html("<div class=\"navicon\"><img src=\"img/disp/menu/icons/navbackicon.png\"></div> <div class=\"menuslinkbutton\">返回</div>");
	} else {
		$("#allmenus").hide();
		$("#defaultprogram").toggle(selectedEffect, options, 400);
		$("#allmenuslink").html("<div class=\"navicon\"><img src=\"img/disp/menu/icons/navicon.png\"></div> <div class=\"menuslinkbutton\">所有菜单</div>");
	}
}

function showAllMenuCallBack() {
	$("#allmenus").css("overflow-y","auto");
}

function zTreeOnClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (treeNode.isParent) {
		zTree.expandNode(treeNode);
	} else {
		startMenuDisplay();
		jumpFunction(treeNode.disp);
	}
}
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
//	alert(msg);
};

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	var result = new Array();
	var datas = childNodes.datas[0].datas;
//	for (var i = 0; i < datas.length; i++) {
//		var obj = new Object();
//		obj.id = datas[i].id;
//		obj.pId = datas[i].pId;
//		obj.name = datas[i].name;
//		obj.open = false;
//		result.push(obj);
//	}
	return datas;
}
