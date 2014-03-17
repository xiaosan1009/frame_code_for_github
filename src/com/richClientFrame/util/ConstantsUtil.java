package com.richClientFrame.util;

import java.io.File;

/**
 * 共通常量类.
 * 
 * @author king
 * @since 2010.03.24
 */
public interface ConstantsUtil {
    
    /**
     * 框架级常量.
     */
    interface Frame {
        
        /**
         * 资源文件路径.
         */
        String DIR_RESOURCE = "WEB-INF" + File.separator 
                                                + "classes" + File.separator
                                                + "com" + File.separator
                                                + "richClientFrame" + File.separator
                                                + "resource" + File.separator;
        
        String ENCODING = "UTF-8";
        
        String ENGINE_VERSION = "1.2.1.2";
        
        String[] ENGINE_VERSION_DESCRIPT = new String[] {
            "加入每次请求结束以后的切面处理",
            "在<param>标签中加入<sessionAllowNull>属性，用来控制在session没有的时候是否抛出异常",
            "修改分页标签",
            "异常处理模式变更",
            "增加解析任意excel格式的功能",
            "session中的用户信息变为可以保存对象",
            "读取头信息及错误信息文件夹[errmsg]支持多级文件夹设置",
        };
    }

    /**
     * 字符串常量.
     */
    interface Str {
        
        /**
         * 空字符串.
         */
        String EMPTY = "";

        /**
         * constant '.'.
         */
        String DOT = ".";

        /**
         * constant '\\.'.
         */
        String REGEX_DOT = "\\.";
        
        /**
         * constant '\\'.
         */
        String TRANSFERRED = "\\";
        
        /**
         * constant '\\'.
         */
        String TRANSFERREDS = "\\\\";
        
        /**
         * constant '/'.
         */
        String SLASH = "/";

        /**
         * constant '\\='.
         */
        String REGEX_EQUAL = "\\=";

        /**
         * constant '_'.
         */
        String UNDERLINE = "_";
        
        /**
         * constant '-'.
         */
        String LINE = "-";
        
        /**
         * constant '*'.
         */
        String STAR = "*";
        
        /**
         * constant ' '.
         */
        String BLANK = " ";
        
        /**
         * constant '-_-'.
         */
        String TARGETID_SUFFIX = "-_-";

        /**
         * constant '#'.
         */
        String POUND = "#";

        /**
         * constant '********'.
         */
        String COMMON_LOG_TAG = "********";

        /**
         * constant ','.
         */
        String COMMA = ",";

        /**
         * constant 'xml'.
         */
        String EXTENSION_XML = "xml";
        
        /**
         * constant ':'.
         */
        String COLON = ":";
        
    }
    
    /**
     * 字符串常量.
     */
    interface Char {

        /**
         * constant char '.'.
         */
        char DOT = '.';

        /**
         * constant char '/'.
         */
        char SLASH = '/';

        /**
         * constant char ':'.
         */
        char COLON = ':';
        
        /**
         * constant char '-'.
         */
        char LINE = '-';
        
        /**
         * constant char ' '.
         */
        char BLANK = ' ';
        
    }
    
    /**
     * 标签常量.
     */
    interface Tag {
        
        /**
         * Prefix for system property place holders: '%{'.
         */
        String PLACE_HOLDER_PREFIX = "%{";

        /**
         * Suffix for system property place holders: '}'.
         */
        String PLACE_HOLDER_SUFFIX = "}";
        
        /**
         * value attribute.
         */
        String VALUE_ATTRIBUTE = "value";
        
        /**
         * index attribute.
         */
        String INDEX_ATTRIBUTE = "index";
        
        /**
         * alternateColor attribute.
         */
        String ALTERNATE_COLOR_ATTRIBUTE = "alternateColor";
        
        /**
         * id attribute.
         */
        String ID_ATTRIBUTE = "id";
        
        /**
         * text attribute.
         */
        String TEXT_ATTRIBUTE = "text";
        
        /**
         * stat attribute.
         */
        String STAT_ATTRIBUTE = "stat";
        
        /**
         * stat attribute.
         */
        String STYLE_ATTRIBUTE = "style";
        
        /**
         * char attribute.
         */
        String CHAR_ATTRIBUTE = "char";
        
        /**
         * widget attribute.
         */
        String WIDGET_ATTRIBUTE = "widget";
        
        /**
         * widget attribute.
         */
        String SELECTED_ATTRIBUTE = "selected";
        
        String DIMENSION = "dimension_values";
        
        String MULTIPLE = "multiple_values";
        
        String CMB_ITEMS = "cmb_values";
        
        String CMB_CONTEXT = "cmb_context";
        
    }
    
    /**
     * 客户端常量.
     */
    interface Client {
        
        /**
         * common type:'0'.
         */
        String COMMON_TYPE = "0";
        
        /**
         * ajax type:'1'.
         */
        String AJAX_TYPE = "1";
        
        /**
         * flex type:'2'.
         */
        String FLEX_TYPE = "2";
        
        /**
         * android type:'3'.
         */
        String ANDROID_TYPE = "3";
        
        /**
         * autocomplete type:'9'.
         */
        String AUTO_COMPLETE = "9";
        
    }
    
    /**
     * 处理类常量.
     */
    interface Resource {
        
        String MUST_ERROR_MESSAGE = "mustErrorMessage";
        
        String PLUGIN_INFO = "pluginInfo";
    }
    
    /**
     * 处理类常量.
     */
    interface Handler {
        
        /**
         * 常用列表ID.
         */
        String LIST1_ID = "list1";
        
        /**
         * 分页控件ID.
         */
        String PAGING_ID = "pageList";
        
        /**
         * 分页页数隐藏域控件ID.
         */
        String PAGE_SIZE_ID = "pageSize";
        
        /**
         * 分页数据总条数隐藏域控件ID.
         */
        String TOTAL_ROWS_ID = "totalRows";
        
        /**
         * 共通处理类.
         */
        String COMMON_HANDLER_NAME = "abstractHandlerItem";
        
        /**
         * 日期初期值.
         */
        String DATE_INIT_VALUE = "----/--/--";
        
        /**
         * 时间初期值.
         */
        String TIME_INIT_VALUE = "--:--";
    }
    
    /**
     * 样式常量.
     */
    interface Style {
        
        /**
         * 粗体.
         */
        String BOLD = "bold";
        
    }
    
    /**
     * 颜色常量.
     */
    interface Color {
        
        /**
         * 红色：#FF0000.
         */
        String RED = "#FF0000";
        
        /**
         * 蓝色：#0000FF.
         */
        String BLUE = "#0000FF";
        
        /**
         * 紫色：#ff00ff.
         */
        String PURPLE = "#ff00ff";
        
        /**
         * 黑色：#000000.
         */
        String BLACK = "#000000";
        
    }
    
    /**
     * 控件类型常量.
     */
    interface Widget {
        
        /**
         * 初期值:-1.
         */
        int DEFAULT = -1;
        
        /**
         * 标签控件:0.
         */
        int TEXT = 0;
        
        /**
         * 文本框控件:1.
         */
        int TEXTBOX = 1;
        
        /**
         * 下拉框控件:2.
         */
        int CMB = 2;
        
        /**
         * 复选框控件:3.
         */
        int CHK = 3;
        
        /**
         * 单选框控件:4.
         */
        int RADIO = 4;
        
        /**
         * 指针控件:5.
         */
        int GUIDE = 5;
        
        /**
         * 按钮控件:6.
         */
        int BTN = 6;
        
        /**
         * 图片控件:7.
         */
        int IMG = 7;
        
        /**
         * 选项控件:8.
         */
        int OPTION = 8;
        
        /**
         * 隐藏域控件 :9.
         */
        int HIDDEN = 9;
        
        /**
         * 列表:10.
         */
        int DATAGRID = 10;
        
        /**
         * DIV:11.
         */
        int DIV = 11;
        
    }
    
    /**
     * 状态常量.
     */
    interface Status {
        
        /**
         * 有效.
         */
        int EFFECT_YES = 1;
        
        /**
         * 无效.
         */
        int EFFECT_NO = 0;
        
        /**
         * 背景色:【没有设定】:'-1'.
         */
        String BG_COLOR_DEFAULT = "-1";
        
        /**
         * 文字色:【没有设定】:'-1'.
         */
        String CHAR_COLOR_DEFAULT = "-1";
        
        /**
         * 字体粗细:【没有设定】:'-1'.
         */
        String CHAR_WEIGHT_DEFAULT = "-1";
        
        /**
         * 表示状态:【通常】:0.
         */
        int WRITE = 0;
        
        /**
         * 表示状态:【只读（readonly）】:1.
         */
        int READ = 1;
        
        /**
         * 表示状态:【隐藏（hidden）】:2.
         */
        int HIDE = 2;
        
        /**
         * 表示状态:【不做控制】:3.
         */
        int NO_CHANGE = 3;
        
        /**
         * 表示状态:【不显示，占位】:4.
         */
        int NOT_VISIBLED = 4;
        
        /**
         * 复选框的表示状态:【没有选中】:'0'.
         */
        String CHECK_OFF = "0";
        
        /**
         * 复选框的表示状态:【选中】:'1'.
         */
        String CHECK_ON = "1"; 
    }
    
    /**
     * 验证常量.
     */
    interface Check {
        
        /**
         * 默认设定:-1.
         */
        String DEFAULT = "-1";
        
        /**
         * 没有输入的错误:1001.
         */
        String NOTHING_INPUT = "1001";
        
        /**
         * 输入错误:1002.
         */
        String INPUT_ERROR = "1002";
        
        /**
         * 输入范围错误:1003.
         */
        String RANGE_ERROR = "1003";
        
        /**
         * 文字超限错误:1004.
         */
        String OVER_LENGTH = "1004";
        
        /**
         * 不能输入的文字错误:1005.
         */
        String INPUT_INVALID = "1005";
        
        /**
         * 数字的项目输入文字的错误:1008.
         */
        String NUM_ONLY_ERROR = "1008";
        
        /**
         * 时间前后验证错误:1010.
         */
        String TIME_DISORDER = "1010";
        
        /**
         * 重复错误:1011.
         */
        String OVERLAP = "1011";
        
        /**
         * 数字前后验证错误:1012.
         */
        String NUM_DIS_ORDER = "1012";
        
        /**
         * 文件格式验证错误:1013.
         */
        String FILE_FORMAT_ERROR = "1013";
        
        /**
         * 邮箱地址格式不正确:1014.
         */
        String EMAIL_FORMAT_ERROR = "1014";
    }
    
    /**
     * 交互数据常量.
     */
    interface DataType {

        /**
         * 数据种别：初期值:-1.
         */
        String INITIAL = "-1";
        
        /**
         * 数据类型：单项目:0.
         */
        String SINGLE = "0";
        /**
         * 数据类型：多项目:1.
         */
        String LIST = "1";
        
        /**
         * 数据类型：初期化:2.
         */
        String PAGE = "2";
        
        /**
         * 数据类型：复合数据:3.
         */
        String COMPLEX = "3";
    }
    
    /**
     * 处理结果信息常量.
     */
    interface Result {
        
        /**
         * 结果类型：画面跳转:-4.
         */
        int PAGE_CHANGED = -4;
        
        /**
         * 结果类型：服务器异常:-2.
         */
        int SYSTEM_ERROR = -2;
        
        /**
         * 结果类型：信息空白:-1.
         */    
        int BLANK = -1;
        
        /**
         * 结果类型：登入成功:0.
         */
        int REGISTER_SUCCESS = 0;
        
        /**
         * 结果类型：输入错误:1.
         */
        int INPUT_ERROR = 1;
        
        /**
         * 结果类型：登入失败:2.
         */
        int REGISTER_ERROR = 2;
        
        /**
         * 结果类型：读取失败:3.
         */
        int READ_ERROR = 3;
        
        /**
         * 结果类型：没有取得数据:4.
         */
        int NO_DATA = 4;
        
        /**
         * 结果类型：删除成功:5.
         */
        int DELETE_SUCCESS = 5;
        
        /**
         * 结果类型：详细画面初期表示:6.
         */
        int INIT_INFO = 6;
        
        /**
         * 结果类型：上传成功:7.
         */
        int UPLOAD_SUCCESS = 7;
        
        /**
         * 结果类型：用户登入失败:8.
         */
        int LOGIN_ERROR = 8;
        
        /**
         * 结果类型：session过期:9.
         */
        int SESSION_TIMEOUT = 9;

        /**
         * 结果类型：数据库错误:10.
         */
        int DB_ERROR = 10;
        
        /**
         * 结果类型：删除失败:12.
         */
        int DELETE_ERROR = 12;
        
        /**
         * 结果类型：履历操作失败:14.
         */
        int OPEHIST_ERROR = 14;
        
        /**
         * 结果类型：用户名已存在:17.
         */
        int OPENAME_OVERLAP = 17;
        /**
         * 结果类型：用户登录中，删除，编辑不能:18.
         */
        int NO_OPE_REGIST = 18;
        /**
         * 结果类型：用户权限不足，删除不能:19.
         */
        int NO_AUTHORITY = 19;
        /**
         * 结果类型：相同PC登录:24.
         */
        int LOGIN_ERR_SAME_PC = 24;
        /**
         * 结果类型：IP不正确:25.
         */
        int LOGIN_ERR_IP = 25;
        /**
         * 结果类型：处理失败:26.
         */    
        int GENERAL_ERR = 26;
        /**
         * 结果类型：登录用户超过session最大数:27.
         */
        int LOGIN_ERR_SESSION_MAX = 27;
        /**
         * 结果类型：登录用户超过session最大数:28.
         */
        int LOGIN_REPEAT = 28;
        /**
         * 结果类型：错误的请求地址:29.
         */
        int INVALID_URL_REQUEST = 29;
    }
    
    /**
     * 请求参数常量.
     */
    interface Param {
        
        /**
         * 请求画面ID.
         * ※必须参数
         */
        String DISP = "dispcode";
         
        /**
         * 请求方法ID.
         * ※必须参数
         */
        String CMD = "cmdcode";
        
        /**
         * 请求控件ID.
         */
        String TARGET = "targetid";
        
        /**
         * 请求控件的序列ID.
         */
        String TARGET_IDX = "targetid_idx";
        
        /**
         * 数据列表的选择行号.
         */
        String LIST_INDEX = "listindex";
        
        /**
         * 特殊参数KEY.
         */
        String KEY = "key";
        
        /**
         * 客户端类型.
         */
        String CLIENT_TYPE = "clientType";
        
        /**
         * 请求验证KEY.
         */
        String CHECKER = "requestChecker";
        
        /**
         * 自动补全种类.
         */
        String PATTERN = "pattern";
        
        /**
         * 自动补全项目ID.
         */
        String HTMLID = "htmlid";
        
        /**
         * 自动补全项目值.
         */
        String HTML_VALUE = "htmlid_value";
        
    }
    
    /**
     * 画面项目ID常量.
     */
    interface Target {
        
        /**
         * 登录按钮.
         */
        String SET = "set";
        
        /**
         * 时间类型.
         */
        String TIME = "time";
        
        /**
         * 页面总数.
         */
        String ALL_PAGE = "all_page_num1";
        
        /**
         * 当前页数.
         */
        String CURRENT_PAGE = "currentpage";
        
        /**
         * 当前页数.
         */
        String NOW_PAGE = "now_page_num1";
        
        /**
         * 翻页号.
         */
        String PAGE_NUM = "page_num1";
        
        /**
         * 前一页.
         */
        String PREV_PAGE = "page_prev1";
        
        /**
         * 前一页.
         */
        String NEXT_PAGE = "page_next1";
        
        /**
         * 总页数.
         */
        String TOTAL_PAGES = "totalPages";
    }
    
    /**
     * 初始常量.
     */
    interface Default {
        
        /**
         * 没有设定（值）.
         */
        String VALUE_NOTHING = "-----";
        
        /**
         * 没有设定（名称）.
         */
        String NAME_NOTHING = "----------------";
        
        /**
         * 没有设定（时间）.
         */
        String HHMM_NOTHING = "--:--";
        
        /**
         * 没有设定（时间 ：HHMMSS）.
         */
        String HHMMSS_NOTHING = "--:--:--";
        
        /**
         * 没有设定日期（MMDD）.
         */
        String MMDD_NOTHING = "--/--";
        
        /**
         * 没有设定日期（YYYYMMDD）.
         */
        String YYYYMMDD_NOTHING = "----/--/--";
    }
    
    /**
     * JSON常量.
     */
    interface Json {
        
        /**
         * JSON头数据KEY值.
         */
        String HEADER = "header";
        
        /**
         * JSON数据KEY值.
         */
        String DATAS = "datas";
        
        /**
         * JSON数据KEY值.
         */
        String ITEM = "item";
        
        /**
         * JSON数据KEY值.
         */
        String DISP_HEADER = "dispCode";
        
        /**
         * JSON数据KEY值.
         */
        String CMD_HEADER = "cmdCode";
        
        /**
         * JSON数据KEY值.
         */
        String DATA_KIND_HEADER = "dataKind";
        
        /**
         * JSON数据KEY值.
         */
        String RES_CODE_HEADER = "resCode";
        
        /**
         * JSON数据KEY值.
         */
        String TARGET_ID_HEADER = "targetId";
        
        /**
         * JSON数据KEY值.
         */
        String TARGET_INDEX_HEADER = "targetIndex";
        
        /**
         * JSON数据KEY值.
         */
        String MSG_HEADER = "headerMsg";
        
        /**
         * JSON数据KEY值.
         */
        String TOTAL_ROWS_HEADER = "totalRows";
        
        /**
         * JSON数据KEY值.
         */
        String PAGE_SIZE_HEADER = "pageSize";
        
        /**
         * JSON数据KEY值.
         */
        String ERROR_HEADER = "error";
        
        /**
         * JSON数据KEY值.
         */
        String TARGET_ID = "targetid";
        
        /**
         * JSON数据KEY值.
         */
        String TARGET_INDEX = "targetIndex";
        
        /**
         * JSON数据KEY值.
         */
        String EFFECT = "effect";
        
        /**
         * JSON数据KEY值.
         */
        String VALUE = "value";
        
        /**
         * JSON数据KEY值.
         */
        String TEXT = "text";
        
        /**
         * JSON数据KEY值.
         */
        String DATA_TYPE = "dataType";
        
        /**
         * JSON数据KEY值.
         */
        String BG_COLOR = "bgColor";
        
        /**
         * JSON数据KEY值.
         */
        String CHAR_COLOR = "charColor";
        
        /**
         * JSON数据KEY值.
         */
        String ERR_MSG_COLOR = "errMsgColor";
        
        /**
         * JSON数据KEY值.
         */
        String DISP_STAT = "dispStat";
        
        /**
         * JSON数据KEY值.
         */
        String MAX_LEN = "maxLen";
        
        /**
         * JSON数据KEY值.
         */
        String DETAIL_CODE = "detailCode";
        
        /**
         * JSON数据KEY值.
         */
        String DETAIL_MSG = "detailMsg";
        
        /**
         * JSON数据KEY值.
         */
        String ITEM_NAME = "itemName";
        
        /**
         * JSON数据KEY值.
         */
        String LABEL = "label";
        
        /**
         * JSON数据KEY值.
         */
        String DATA = "data";
    }
    
    /**
     * 语言种别常量.
     */
    interface Lang {

        /**
         * 语言种类（日语）.
         */
        String JA = "ja";
        
        /**
         * 语言种类（中国语）.
         */
        String CN = "zh_CN";
        
        /**
         * 语言种类（英语）.
         */
        String EN = "eng";
    }
    
    /**
     * 错误信息常量.
     */
    interface ErrInfo {
        
        /**
         * 系统错误.
         */
        String SYSTEM_ERROR = "系统异常!!";
        
        /**
         * 系统错误.
         */
        String DB_ERROR = "数据库异常!!";
        
        /**
         * 超时错误.
         */
        String RUN_TIME = "处理超时！！";
    }
    
    /**
     * 样式常量.
     */
    interface Request {
        
        /**
         * 复合方法名.
         */
        String COMPLEX_METHOD = "showComplex";
        
        /**
         * 单项目方法名.
         */
        String SINGLE_METHOD = "showItem";
        
        /**
         * 对应数据库的增删改方法名.
         */
        String UPDATE_METHOD = "update";
        
        /**
         * 文件上传方法名.
         */
        String UPLOAD_METHOD = "upload";
        
        /**
         * 发送邮件方法名.
         */
        String SEND_MAIL_METHOD = "sendMail";
        
        /**
         * 多项目方法名.
         */
        String LIST_METHOD = "showList";
        
        /**
         * 类名.
         */
        String CLASS = "handlerclass";
        
        /**
         * 类名.
         */
        String CLASS_CODE = "dispCode";
        
        /**
         * 方法名.
         */
        String METHOD = "methodName";
        
        /**
         * 方法名.
         */
        String METHOD_CODE = "methodCode";
        
        /**
         * jsp名.
         */
        String JSP = "jspName";
        
        /**
         * 是否自动登出.
         */
        String LOGOUT = "logout";
        
        /**
         * 是否是servlet请求.
         */
        String REQUEST = "request";
        
        /**
         * 跳转页面ID.
         */
        String FORWARD = "forward";
        
        /**
         * 是否进行共通验证.
         */
        String CHECK = "check";
        
        /**
         * 是否进行共通验证.
         */
        String JSP_LANG = "jspLang";
        
        /**
         * 是否进行共通验证.
         */
        String CLIENT = "client";
        
        /**
         * 是否进行共通验证.
         */
        String JSON = "jsonName";
        
        /**
         * 是否进行共通验证.
         */
        String SQL = "sql";
        
        /**
         * 是否进行共通验证.
         */
        String TARGET_ID = "targetId";
        
        /**
         * 是否进行共通验证.
         */
        String PAGE_SIZE = "pageSize";
        
        /**
         * 请求拒绝KEY.
         */
        String REQUEST_REFUSE = "requestRefuse";
        
    }
    
    /**
     * 下拉框常量.
     */
    interface Cmb {
        
        /**
         * 下拉框传递值.
         */
        String VALUE = "value";
        
        /**
         * 下拉框显示值.
         */
        String LABEL = "label";
    }
    
    /**
     * 【request.xsd】文件中的常量.
     */
    interface Xsd {
        
        /**
         * 取得系统日期的KEY.
         */
        String SYSDATE = "%sysdate%";
        
        /**
         * 取得数据库表序列的KEY.
         */
        String SEQUENCE = "%sequence%";
        
        /**
         * 取得sessionId的KEY.
         */
        String SESSION_ID = "%sessionId%";
        
        /**
         * constant 'fmt:'.
         */
        String FMT = "fmt:";
        
        /**
         * constant 'user:'.
         */
        String USER = "user:";
        
        /**
         * constant '#Result'.
         */
        String RESULT = "#Result";
        
        /**
         * constant '#'.
         */
        String DB_SIGN = "#";
        
        /**
         * constant '$'.
         */
        String REQUEST_SIGN = "$";
        
        /**
         * constant '%'.
         */
        String SESSION_SIGN = "%";
        
        String PARAM_SESSION_ALLOW_NULL = "sessionAllowNull";
    }
    
    /**
     * 方法ID常量.
     */
    interface Method {
        
        /**
         * 画面初期处理方法ID.
         */
        String SHOW_PAGE_CODE = "0";
        
        /**
         * 分页方法.
         */
        String PAGING_CODE = "99";
        
        /**
         * 插件机能取得成员方法ID.
         */
        String PLUGIN_GET_ITEMS_CODE = "9999";

        /**
         * 打印画面表示.
         */
        String SHOW_PRINT_CODE = "100";
        
        /**
         * PDF打印画面表示.
         */
        String PRINT_CODE = "101";
        
        /**
         * 下载处理.
         */
        String DOWNLOAD_CODE = "400";
        
        /**
         * 上传处理.
         */
        String UPLOAD_CODE = "500";
        
        /**
         * 自动补全处理.
         */
        String AUTOCOMP_COM_CODE = "1001";
        
        /**
         * 画面初期处理方法.
         */
        String SHOW_PAGE = "showPage";
        
        /**
         * 分页方法.
         */
        String PAGING = "showPageList";
        
        /**
         * 共通验证方法名.
         */
        String COMMON_CHECK = "commonChecker";
        
    }
    
    /**
     * 页面路径相关.
     */
    interface Page {
        
        /**
         * flex url path:'/WEB-INF/jsp/jsonList.jsp'.
         */
        String JSON_URL_PATH = "/WEB-INF/jsp/frame/jsonList.jsp";
        
        /**
         * single page url:'/WEB-INF/jsp/tab.jsp'.
         */
        String SINGLE_PAGE_URL_PATH = "/WEB-INF/jsp/frame/tab.jsp";
        
        /**
         * 分页路径.
         */
        String PAGING_PATH = "/WEB-INF/jsp/frame/inc_page.jsp";
        
        /**
         * 错误画面路径.
         */
        String ERROR_PATH = "/WEB-INF/jsp/frame/err.jsp";
    }
    
    /**
     * 页面路径相关.
     */
    interface Log {
        
        /**
         * 框架日志标识.
         */
        String FRAME_SIGN = "[Frame]:";
        
        String LEFT_SIGN = "[";
        
        String RIGHT_SIGN = "] ";
        
        String FRAME_LEFT_SIGN = "[";
        
        String FRAME_RIGHT_SIGN = "] ";
    }
    
    /**
     * 数据库相关.
     */
    interface Db {
        
        /**
         * sql文.
         */
        String SQL = "FRAME_SQL_CONTENT";
        
        /**
         * sql参数.
         */
        String PARAMS = "FRAME_SQL_PARAMS";
        
        /**
         * sql文ID.
         */
        String SQL_ID = "FRAME_SQL_ID";
    }
    
    /**
     * Excel相关.
     */
    interface Excel {
        
        String TYPE_STREAM = "stream";
        
        String TYPE_READ = "read";
        
        String TYPE_WRITE = "write";
        
        String FILE_TYPE_XLS = "xls";
        
        String FILE_TYPE_XLSX = "xlsx";
        
        String SHEET_NAME_KEY = "SHEET_NAME_KEY";
        
        String ROW_SIT_KEY = "ROW_SIT_KEY";
        
        String CELL_SIT_KEY = "CELL_SIT_KEY";
        
        String CELL_VALUE_KEY = "CELL_VALUE_KEY";
        
        String CELL_BACKGROUND_KEY = "CELL_BACKGROUND_KEY";
        
        String CELL_COLSPAN_KEY = "CELL_COLSPAN_KEY";
        
        String CELL_ROWSPAN_KEY = "CELL_ROWSPAN_KEY";
        
        String CELL_ALIGNMENT_KEY = "CELL_ALIGNMENT_KEY";
        
        String CELL_VERTICAL_ALIGNMENT_KEY = "CELL_VERTICAL_ALIGNMENT_KEY";
        
    }
}
