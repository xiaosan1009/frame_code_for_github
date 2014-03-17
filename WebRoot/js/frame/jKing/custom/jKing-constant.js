var serverURL = 'main';

var onChangeFlag = false;

var timer = null;

var timerID = 0;

var REQ_CMDCODE_INIT = '0';

var COLOR_DATA_MUKO = '-1';

// 标签控件
var TYPE_LABEL = '0';
// 文本框控件
var TYPE_TEXTBOX = '1';
// 下拉框控件
var TYPE_SELECTBOX = '2';
// 复选框控件
var TYPE_CHECKBOX = '3';
// 单选框控件
var TYPE_RADIO = '4';
// 指针控件
var TYPE_GUIDE = '5';
// 按钮控件
var TYPE_BUTTON = '6';
// 图片控件
var TYPE_IMAGE = '7';
// 选项控件
var TYPE_OPTION = '8';
// 隐藏域控件 
var TYPE_HIDDEN = '9';
// 层控件 
var TYPE_DIV = '11';
// 日期选择控件 
var TYPE_DATE_FIELD = '13';
// 日期选择控件 
var TYPE_TEXTAREA = '14';
// 超链接控件
var TYPE_A = '15';

var LOADING_CLASS = 'loadingSingleClass';

var DISP_STATE_NORMAL = '0';
var DISP_STATE_READONLY = '1';
var DISP_STATE_NONE = '2';
var DISP_STATE_NOCHANGE = '3';
var DISP_STATE_NOT_VISIBLED = '4';

var CHECKED_TRUE = '1';
var CHECKED_FALSE = '0';

var DISP_CODE_ID = 'dispcode';
var SCREEN_CODE_ID = 'screencode';
var CMD_CODE_ID = 'cmdcode';
var TARGET_ID = 'targetid';
var TARGET_ID_INDEX = 'targetid_idx';
var RESULT_SCREEN_ID = 'result';
var LIST_INDEX_ID = 'listindex';
var SET_OPENER_INDEX = 'setopenerindex';
var KEY = 'key';
var TYPE_ID = 'type';
var VALUE_ID = 'value';
var AUTO_VALUE_ID = 'autoValue';

var RES_DATA_INDEX_ID = 0;
var RES_DATA_INDEX_ID_INDEX = 1;
var RES_DATA_INDEX_VALUE_EFFECT_FLG = 2;
var RES_DATA_INDEX_VALUE = 3;
var RES_DATA_INDEX_OPTIONTEXT = 4;
var RES_DATA_INDEX_TYPE = 5;
var RES_DATA_INDEX_BGCOLOR = 6;
var RES_DATA_INDEX_TEXTCOLOR = 7;
var RES_DATA_INDEX_DISPSTATE = 8;
var RES_DATA_INDEX_MAXLENGTH = 9;
var RES_DATA_INDEX_DETAILCODE = 10;
var RES_DATA_INDEX_ITEMNAME = 11;

var SCREEN_MENU = "800201";
var SCREEN_LOGIN = "000001";
var TARGETID_SUFFIX = '-_-';
var COMMON_NORMAL_BG_SIGN = '{ffffff}';
var COMMON_ERR_BG_SIGN = '{errorBg}';
var KINDEDITOR_UPLOAD_URL = 'main?dispcode=000005&cmdcode=1&clientType=1';