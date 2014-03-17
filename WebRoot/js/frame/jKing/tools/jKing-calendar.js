/**
 * 生成日历js
 */

// 日期数据（初期值是当前日期）
var dd = new Date();

// 星期名称种类(1:长星期名称、1以外:短星期名称)
var weekType = 0;
// 星期名称（初期值：短星期名称）
var weekData = new Array("日", "一", "二", "三", "四", "五", "六");
// 星期背景色（日~六）
var weekColor = new Array("#ffcccc", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#acc0d4");

// 日期内数据保存数组（0~31,0未使用）
var dispData = new Array(32);
// 数组初期化（空字符设置）
for (var i = 0; i < dispData.length; i++) {
	dispData[i] = "&nbsp;";
}

/**
 * 生成日历表格
 * ・HTML格式的数据在变量dispStr中，调用document.write显示在页面上
 * ・行数会根据日数变化
 * @param width 表格的宽【<td>的width】
 * @param height 表格的高【<td>的height】
 * @param styles 表格的样式
 * @param dayLink 日期的文字是否有链接 1：有链接
 */
function createCarendarTable(width, height, styles, dayLinkFlg) {
	// 画面输出字符串
	var dispStr = "";
	// 第一天设置
	this.dd = new Date(this.dd.getYear(), this.dd.getMonth(), 1);
	// 月末日期设置
	var lastDay = new Date(this.dd.getYear(), this.dd.getMonth() + 1, 0).getDate();
	// 月份的开始星期设置
	var startWeek = this.dd.getDay();
	// 日期开始处理标识
	var startFlg = false;
	// 日期结束处理标识
	var endFlg = true;
	// 处理日期计数（初期值：1）
	var dayCnt = 1;

	var dispYear = this.dd.getYear();
	if (2000 > dispYear) {
		dispYear = dispYear + 1900;
	}
	var dispMonth = "0" + (this.dd.getMonth() + 1);
	dispMonth = dispMonth.substr(dispMonth.length - 2);
	dispStr = "<span class=\"font12\" style=\"margin-left: 10\">" + dispYear + "年" + dispMonth + "月</span><br>\n";

	var dayLinkS = "";
	var dayLinkE = "";

	dispStr = dispStr + "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"" + styles + "\">\n";
	dispStr = dispStr + "	<tr height=\"24\">\n";
	for (var i = 0; i < weekData.length; i++) {
			dispStr = dispStr + "		<td valign=\"middle\" align=\"center\" bgcolor=\"" + weekColor[i] + "\">" + weekData[i] + "</td>\n";
	}
	dispStr = dispStr + "	</tr>\n";

	while (endFlg) {
		dispStr = dispStr + "	<tr valign=\"top\">\n";
		// 一星期间的数据做成
		for (var i = 0; i < weekData.length; i++) {
			// 判断是否是开始日期			if (dayCnt == 1 && i == startWeek) {
				startFlg = true;
			}
			if (startFlg) {
				dispStr = dispStr + "		<td bgcolor=\"" + weekColor[i] + "\" width=\"" + width + "\" height=\"" + height + "\">\n";
				dispStr = dispStr + "			<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" height=\"100%\" class=\"tableNoBorder\">";
				if (dayLinkFlg == 1) {
					dayLinkS = "<a href=\"JavaScript:pageOpen('date', '" + dispYear + "/" + dispMonth + "/" + dayCnt + "');\">";
					dayLinkE = "</a>";
				}
				dispStr = dispStr + "				<tr><td align=\"left\" valign=\"top\">" + dayLinkS + dayCnt + dayLinkE + "</td></tr>\n";
				if (dispData != null && dispData.length != 0) {
					dispStr = dispStr + "				<tr><td align=\"center\" valign=\"middle\">" + dispData[dayCnt] + "</td></tr>\n";
				}
				dispStr = dispStr + "			</table>\n";
				dispStr = dispStr + "		</td>\n";
				dayCnt++;
			} else {
				dispStr = dispStr + "		<td bgcolor=\"" + weekColor[i] + "\" width=\"" + width + "\" height=\"" + height + "\">&nbsp;</td>\n";
			}
			if (lastDay < dayCnt) {
				endFlg = false;
				startFlg = false;
			}
		}
		dispStr = dispStr + "	</tr>\n";
	}

	dispStr = dispStr + "</table>\n";

	document.write(dispStr);
}

/**
 * 日历表示年月日设定
 * @param year 年
 * @param month 月 */
function setDate(year, month) {
	if (year == undefined || year.length == 0) {
		year = this.dd.getYear();
	}
	if (month == undefined || month.length == 0) {
		month = this.dd.getMonth();
	} else {
		month = month - 1;
	}
	this.dd = new Date(year, month, 1);
}

/**
 * 星期的种类设定
 * @param type 1：长星期名称、2：英文名称、以外：短姓起名 */
function setWeekType(type) {
	this.weekType = type;
	if (this.weekType == 1) {
		// 长星期名称设定
		weekData = new Array("日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日");
	} else if (this.weekType == 2) {
		// 英文星期名称设定
		weekData = new Array("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
	}
}


/**
 * 下一年链接表示
 * @param linkStr 链接表示的文字
 */
function nextYearLink(linkStr) {
	var dispStr = "";		
	var nextYear = this.dd.getYear() + 1;
	var nextMonth = this.dd.getMonth() + 1;
	if (linkStr == null || linkStr.length == 0) {
		linkStr = "下一年";
	}

	// 日期在2000/01/01~2037/12/31之间
	if (nextYear > 2037) {
		return;
	}
	
	dispStr = dispStr + "<a href=\"?year=" + nextYear + "&month=" + nextMonth + "\">" + linkStr + "</a>\n";
	document.write(dispStr);
}


/**
 * 下月链接表示
 * @param linkStr 链接表示文字
 */
function nextMonthLink(linkStr) {
	var dispStr = "";
	var nextYear = this.dd.getYear();
	var nextMonth = this.dd.getMonth();
	if (nextMonth == 11) {
		nextYear = nextYear + 1;
		nextMonth = 1;
	} else {
		nextMonth = nextMonth + 1 + 1;
	}
	if (linkStr == null || linkStr.length == 0) {
		linkStr = "下一月";
	}
	
	// 日期在2000/01/01~2037/12/31之间
	if (nextYear > 2037) {
		return;
	}
	
	dispStr = dispStr + "<a href=\"?year=" + nextYear + "&month=" + nextMonth + "\">" + linkStr + "</a>\n";
	document.write(dispStr);
}


/**
 * 前年链接表示
 * @param linkStr 链接表示文字
 */
function prevYearLink(linkStr) {
	var dispStr = "";
	var prevYear = this.dd.getYear() - 1;
	var prevMonth = this.dd.getMonth() + 1;
	if (linkStr == null || linkStr.length == 0) {
		linkStr = "前一年";
	}
	
	// 日期在2000/01/01~2037/12/31之间
	if (prevYear < 2000) {
		return;
	}
	
	dispStr = dispStr + "<a href=\"?year=" + prevYear + "&month=" + prevMonth + "\">" + linkStr + "</a>\n";
	document.write(dispStr);
}


/**
 * 前月链接表示
 * @param linkStr 链接表示文字
 */
function prevMonthLink(linkStr) {
	var dispStr = "";
	var prevYear = this.dd.getYear();
	var prevMonth = this.dd.getMonth();
	if (prevMonth == 0) {
		prevMonth = 0;
	} else {
		prevMonth = prevMonth - 1 + 1;
	}
	if (linkStr == null || linkStr.length == 0) {
		linkStr = "前一月";
	}
	
	// 日期在2000/01/01~2037/12/31之间
	if(prevYear == 2000 && prevMonth == 0) {
		return;
	}
	
	dispStr = dispStr + "<a href=\"?year=" + prevYear + "&month=" + prevMonth + "\">" + linkStr + "</a>\n";
	document.write(dispStr);
}

