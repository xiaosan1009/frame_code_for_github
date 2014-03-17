$(function () {
	initScreen("020901");
	
});

function init() {
	var jsonWidget = new Object();
	var widgets = [];
	var widgets_bottom = [];
	
	var containerWidget1 = JK.makeWidget({text : "类型", type : TYPE_LABEL});
	var containerWidget2 = JK.makeWidget({id : "type", type : TYPE_SELECTBOX, method : "change", cmd : "2|3"});
	var widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets[widgets.length] = widget;
	
	containerWidget1 = JK.makeWidget({text : "大分类", type : TYPE_LABEL});
	containerWidget2 = JK.makeWidget({id : "parentType", type: TYPE_SELECTBOX, method : "change", cmd : "3"});
	widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets[widgets.length] = widget;
	
	containerWidget1 = JK.makeWidget({text : "小分类", type : TYPE_LABEL});
	containerWidget2 = JK.makeWidget({id : "childType", type : TYPE_SELECTBOX});
	widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets[widgets.length] = widget;
	
	containerWidget1 = JK.makeWidget({text : "开始日期", type : TYPE_LABEL});
	containerWidget2 = JK.makeWidget({id : "startDate", type : TYPE_DATE_FIELD});
	widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets[widgets.length] = widget;
	
	containerWidget1 = JK.makeWidget({text : "结束日期", type : TYPE_LABEL});
	containerWidget2 = JK.makeWidget({id : "endDate", type : TYPE_DATE_FIELD});
	widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets[widgets.length] = widget;
	
	containerWidget1 = JK.makeWidget({text : "报表类型", type : TYPE_LABEL});
	containerWidget2 = JK.makeWidget({id : "dateFormat", type : TYPE_SELECTBOX, method : "change", cmd : "1", after : "createChart"});
	widget = JK.makeContainer(containerWidget1, containerWidget2);
	widgets_bottom[widgets_bottom.length] = widget;
	
	widget = JK.makeWidget({text : "查询", type : TYPE_BUTTON, method : "click", cmd : "1", after : "createChart"});
	widgets_bottom[widgets_bottom.length] = widget;
	
	jsonWidget.datas = widgets;
	jsonWidget.datas_bottom = widgets_bottom;
	$("#report").createFlash(
		"dispcode", "020901", 
		"url", $.httpUrl(),
		"listCmd", "1", 
		"typeCmd", "2", 
		"parentCmd", "3", 
		"flashName", "flexFrame", 
		"flashPath", "swf/", 
		"widget", $.toJson(jsonWidget), 
		"display", "1");
}