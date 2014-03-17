/**
 * 画面初始化的执行方法1
 */
function init() {
}

/**
 * 画面初始化的执行方法2,对画面的控件权限控制等在此方法内执行控制
 */
function postScreenInit() {
}

/**
 * 画面初始化的执行方法3,画面的控件方法，如自动补全，日期控件等在此方法内初始化
 */
function postScreenInitAfter(){
}
/**
 * session超时的执行方法
 */
function sessionTimeOutAction() {
    $.locationChange(SCREEN_LOGIN);
}
/**
 * 请求执行完毕后，在设置了information：true的前提下，并且非错误结果，执行此方法
 * @param resCode 结果信息code
 */
function showInformationMessage(resCode) {
}

/**
 * 有其他用户登录的情况下，执行此方法
 */
function otherUserLoginAction() {
}

/**
 * 显示分页信息
 */
function pagingDisplay() {
    var totalRows = $('#pagingTotalRows').val();
    if (totalRows) {
        totalRows = parseInt(totalRows, 10);
    } else {
        totalRows = 0;
    }
    var pageSize = $('#pagingPageSize').val();
    if (pageSize) {
        pageSize = parseInt(pageSize, 10);
    } else {
        pageSize = 1;
    }
    var currentpage = $('#currentpage').val();
    if (currentpage) {
        currentpage = parseInt(currentpage, 10);
    } else {
        currentpage = 1;
    }
    var page_prev_btn = $('#page_prev1');
    var page_next_btn = $('#page_next1');
    var now_page_num_span = $('#now_page_num1');
    var all_page_num_span = $('#all_page_num1');
    var totalPages_hide = $('#totalPages');
    var allPageNum = parseInt(totalRows / pageSize, 10);
    if (totalRows % pageSize != 0) {
        allPageNum++;
    }
    if (allPageNum == 0) {
    	allPageNum = 1;
    }
    totalPages_hide.val(allPageNum);
    all_page_num_span.html(allPageNum);
    now_page_num_span.html(currentpage);
    if (currentpage <= 1) {
        page_prev_btn.attr('disabled', 'disabled');
    } else {
        page_prev_btn.attr('disabled', '');
    }
    if (currentpage >= allPageNum) {
        page_next_btn.attr('disabled', 'disabled');
    } else {
        page_next_btn.attr('disabled', '');
    }
    $('.pagingTag').show();
}
function onScorll() {
}
function onResize() {
}
/**
 * 显示引擎共通提示信息头的方法
 * 如果重载此方法，并返回false，共通提示信息头将不再显示
 * @param msg 提示信息的内容
 */
function customMessageControl(msg) {
	return true;
}
function rescodeListener(code) {
	return true;
}