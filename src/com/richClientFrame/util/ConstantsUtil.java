package com.richClientFrame.util;

import java.io.File;

/**
 * ��ͨ������.
 * 
 * @author king
 * @since 2010.03.24
 */
public interface ConstantsUtil {
    
    /**
     * ��ܼ�����.
     */
    interface Frame {
        
        /**
         * ��Դ�ļ�·��.
         */
        String DIR_RESOURCE = "WEB-INF" + File.separator 
                                                + "classes" + File.separator
                                                + "com" + File.separator
                                                + "richClientFrame" + File.separator
                                                + "resource" + File.separator;
        
        String ENCODING = "UTF-8";
        
        String ENGINE_VERSION = "1.2.1.2";
        
        String[] ENGINE_VERSION_DESCRIPT = new String[] {
            "����ÿ����������Ժ�����洦��",
            "��<param>��ǩ�м���<sessionAllowNull>���ԣ�����������sessionû�е�ʱ���Ƿ��׳��쳣",
            "�޸ķ�ҳ��ǩ",
            "�쳣����ģʽ���",
            "���ӽ�������excel��ʽ�Ĺ���",
            "session�е��û���Ϣ��Ϊ���Ա������",
            "��ȡͷ��Ϣ��������Ϣ�ļ���[errmsg]֧�ֶ༶�ļ�������",
        };
    }

    /**
     * �ַ�������.
     */
    interface Str {
        
        /**
         * ���ַ���.
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
     * �ַ�������.
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
     * ��ǩ����.
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
     * �ͻ��˳���.
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
     * �����ೣ��.
     */
    interface Resource {
        
        String MUST_ERROR_MESSAGE = "mustErrorMessage";
        
        String PLUGIN_INFO = "pluginInfo";
    }
    
    /**
     * �����ೣ��.
     */
    interface Handler {
        
        /**
         * �����б�ID.
         */
        String LIST1_ID = "list1";
        
        /**
         * ��ҳ�ؼ�ID.
         */
        String PAGING_ID = "pageList";
        
        /**
         * ��ҳҳ��������ؼ�ID.
         */
        String PAGE_SIZE_ID = "pageSize";
        
        /**
         * ��ҳ����������������ؼ�ID.
         */
        String TOTAL_ROWS_ID = "totalRows";
        
        /**
         * ��ͨ������.
         */
        String COMMON_HANDLER_NAME = "abstractHandlerItem";
        
        /**
         * ���ڳ���ֵ.
         */
        String DATE_INIT_VALUE = "----/--/--";
        
        /**
         * ʱ�����ֵ.
         */
        String TIME_INIT_VALUE = "--:--";
    }
    
    /**
     * ��ʽ����.
     */
    interface Style {
        
        /**
         * ����.
         */
        String BOLD = "bold";
        
    }
    
    /**
     * ��ɫ����.
     */
    interface Color {
        
        /**
         * ��ɫ��#FF0000.
         */
        String RED = "#FF0000";
        
        /**
         * ��ɫ��#0000FF.
         */
        String BLUE = "#0000FF";
        
        /**
         * ��ɫ��#ff00ff.
         */
        String PURPLE = "#ff00ff";
        
        /**
         * ��ɫ��#000000.
         */
        String BLACK = "#000000";
        
    }
    
    /**
     * �ؼ����ͳ���.
     */
    interface Widget {
        
        /**
         * ����ֵ:-1.
         */
        int DEFAULT = -1;
        
        /**
         * ��ǩ�ؼ�:0.
         */
        int TEXT = 0;
        
        /**
         * �ı���ؼ�:1.
         */
        int TEXTBOX = 1;
        
        /**
         * ������ؼ�:2.
         */
        int CMB = 2;
        
        /**
         * ��ѡ��ؼ�:3.
         */
        int CHK = 3;
        
        /**
         * ��ѡ��ؼ�:4.
         */
        int RADIO = 4;
        
        /**
         * ָ��ؼ�:5.
         */
        int GUIDE = 5;
        
        /**
         * ��ť�ؼ�:6.
         */
        int BTN = 6;
        
        /**
         * ͼƬ�ؼ�:7.
         */
        int IMG = 7;
        
        /**
         * ѡ��ؼ�:8.
         */
        int OPTION = 8;
        
        /**
         * ������ؼ� :9.
         */
        int HIDDEN = 9;
        
        /**
         * �б�:10.
         */
        int DATAGRID = 10;
        
        /**
         * DIV:11.
         */
        int DIV = 11;
        
    }
    
    /**
     * ״̬����.
     */
    interface Status {
        
        /**
         * ��Ч.
         */
        int EFFECT_YES = 1;
        
        /**
         * ��Ч.
         */
        int EFFECT_NO = 0;
        
        /**
         * ����ɫ:��û���趨��:'-1'.
         */
        String BG_COLOR_DEFAULT = "-1";
        
        /**
         * ����ɫ:��û���趨��:'-1'.
         */
        String CHAR_COLOR_DEFAULT = "-1";
        
        /**
         * �����ϸ:��û���趨��:'-1'.
         */
        String CHAR_WEIGHT_DEFAULT = "-1";
        
        /**
         * ��ʾ״̬:��ͨ����:0.
         */
        int WRITE = 0;
        
        /**
         * ��ʾ״̬:��ֻ����readonly����:1.
         */
        int READ = 1;
        
        /**
         * ��ʾ״̬:�����أ�hidden����:2.
         */
        int HIDE = 2;
        
        /**
         * ��ʾ״̬:���������ơ�:3.
         */
        int NO_CHANGE = 3;
        
        /**
         * ��ʾ״̬:������ʾ��ռλ��:4.
         */
        int NOT_VISIBLED = 4;
        
        /**
         * ��ѡ��ı�ʾ״̬:��û��ѡ�С�:'0'.
         */
        String CHECK_OFF = "0";
        
        /**
         * ��ѡ��ı�ʾ״̬:��ѡ�С�:'1'.
         */
        String CHECK_ON = "1"; 
    }
    
    /**
     * ��֤����.
     */
    interface Check {
        
        /**
         * Ĭ���趨:-1.
         */
        String DEFAULT = "-1";
        
        /**
         * û������Ĵ���:1001.
         */
        String NOTHING_INPUT = "1001";
        
        /**
         * �������:1002.
         */
        String INPUT_ERROR = "1002";
        
        /**
         * ���뷶Χ����:1003.
         */
        String RANGE_ERROR = "1003";
        
        /**
         * ���ֳ��޴���:1004.
         */
        String OVER_LENGTH = "1004";
        
        /**
         * ������������ִ���:1005.
         */
        String INPUT_INVALID = "1005";
        
        /**
         * ���ֵ���Ŀ�������ֵĴ���:1008.
         */
        String NUM_ONLY_ERROR = "1008";
        
        /**
         * ʱ��ǰ����֤����:1010.
         */
        String TIME_DISORDER = "1010";
        
        /**
         * �ظ�����:1011.
         */
        String OVERLAP = "1011";
        
        /**
         * ����ǰ����֤����:1012.
         */
        String NUM_DIS_ORDER = "1012";
        
        /**
         * �ļ���ʽ��֤����:1013.
         */
        String FILE_FORMAT_ERROR = "1013";
        
        /**
         * �����ַ��ʽ����ȷ:1014.
         */
        String EMAIL_FORMAT_ERROR = "1014";
    }
    
    /**
     * �������ݳ���.
     */
    interface DataType {

        /**
         * �����ֱ𣺳���ֵ:-1.
         */
        String INITIAL = "-1";
        
        /**
         * �������ͣ�����Ŀ:0.
         */
        String SINGLE = "0";
        /**
         * �������ͣ�����Ŀ:1.
         */
        String LIST = "1";
        
        /**
         * �������ͣ����ڻ�:2.
         */
        String PAGE = "2";
        
        /**
         * �������ͣ���������:3.
         */
        String COMPLEX = "3";
    }
    
    /**
     * ��������Ϣ����.
     */
    interface Result {
        
        /**
         * ������ͣ�������ת:-4.
         */
        int PAGE_CHANGED = -4;
        
        /**
         * ������ͣ��������쳣:-2.
         */
        int SYSTEM_ERROR = -2;
        
        /**
         * ������ͣ���Ϣ�հ�:-1.
         */    
        int BLANK = -1;
        
        /**
         * ������ͣ�����ɹ�:0.
         */
        int REGISTER_SUCCESS = 0;
        
        /**
         * ������ͣ��������:1.
         */
        int INPUT_ERROR = 1;
        
        /**
         * ������ͣ�����ʧ��:2.
         */
        int REGISTER_ERROR = 2;
        
        /**
         * ������ͣ���ȡʧ��:3.
         */
        int READ_ERROR = 3;
        
        /**
         * ������ͣ�û��ȡ������:4.
         */
        int NO_DATA = 4;
        
        /**
         * ������ͣ�ɾ���ɹ�:5.
         */
        int DELETE_SUCCESS = 5;
        
        /**
         * ������ͣ���ϸ������ڱ�ʾ:6.
         */
        int INIT_INFO = 6;
        
        /**
         * ������ͣ��ϴ��ɹ�:7.
         */
        int UPLOAD_SUCCESS = 7;
        
        /**
         * ������ͣ��û�����ʧ��:8.
         */
        int LOGIN_ERROR = 8;
        
        /**
         * ������ͣ�session����:9.
         */
        int SESSION_TIMEOUT = 9;

        /**
         * ������ͣ����ݿ����:10.
         */
        int DB_ERROR = 10;
        
        /**
         * ������ͣ�ɾ��ʧ��:12.
         */
        int DELETE_ERROR = 12;
        
        /**
         * ������ͣ���������ʧ��:14.
         */
        int OPEHIST_ERROR = 14;
        
        /**
         * ������ͣ��û����Ѵ���:17.
         */
        int OPENAME_OVERLAP = 17;
        /**
         * ������ͣ��û���¼�У�ɾ�����༭����:18.
         */
        int NO_OPE_REGIST = 18;
        /**
         * ������ͣ��û�Ȩ�޲��㣬ɾ������:19.
         */
        int NO_AUTHORITY = 19;
        /**
         * ������ͣ���ͬPC��¼:24.
         */
        int LOGIN_ERR_SAME_PC = 24;
        /**
         * ������ͣ�IP����ȷ:25.
         */
        int LOGIN_ERR_IP = 25;
        /**
         * ������ͣ�����ʧ��:26.
         */    
        int GENERAL_ERR = 26;
        /**
         * ������ͣ���¼�û�����session�����:27.
         */
        int LOGIN_ERR_SESSION_MAX = 27;
        /**
         * ������ͣ���¼�û�����session�����:28.
         */
        int LOGIN_REPEAT = 28;
        /**
         * ������ͣ�����������ַ:29.
         */
        int INVALID_URL_REQUEST = 29;
    }
    
    /**
     * �����������.
     */
    interface Param {
        
        /**
         * ������ID.
         * ���������
         */
        String DISP = "dispcode";
         
        /**
         * ���󷽷�ID.
         * ���������
         */
        String CMD = "cmdcode";
        
        /**
         * ����ؼ�ID.
         */
        String TARGET = "targetid";
        
        /**
         * ����ؼ�������ID.
         */
        String TARGET_IDX = "targetid_idx";
        
        /**
         * �����б��ѡ���к�.
         */
        String LIST_INDEX = "listindex";
        
        /**
         * �������KEY.
         */
        String KEY = "key";
        
        /**
         * �ͻ�������.
         */
        String CLIENT_TYPE = "clientType";
        
        /**
         * ������֤KEY.
         */
        String CHECKER = "requestChecker";
        
        /**
         * �Զ���ȫ����.
         */
        String PATTERN = "pattern";
        
        /**
         * �Զ���ȫ��ĿID.
         */
        String HTMLID = "htmlid";
        
        /**
         * �Զ���ȫ��Ŀֵ.
         */
        String HTML_VALUE = "htmlid_value";
        
    }
    
    /**
     * ������ĿID����.
     */
    interface Target {
        
        /**
         * ��¼��ť.
         */
        String SET = "set";
        
        /**
         * ʱ������.
         */
        String TIME = "time";
        
        /**
         * ҳ������.
         */
        String ALL_PAGE = "all_page_num1";
        
        /**
         * ��ǰҳ��.
         */
        String CURRENT_PAGE = "currentpage";
        
        /**
         * ��ǰҳ��.
         */
        String NOW_PAGE = "now_page_num1";
        
        /**
         * ��ҳ��.
         */
        String PAGE_NUM = "page_num1";
        
        /**
         * ǰһҳ.
         */
        String PREV_PAGE = "page_prev1";
        
        /**
         * ǰһҳ.
         */
        String NEXT_PAGE = "page_next1";
        
        /**
         * ��ҳ��.
         */
        String TOTAL_PAGES = "totalPages";
    }
    
    /**
     * ��ʼ����.
     */
    interface Default {
        
        /**
         * û���趨��ֵ��.
         */
        String VALUE_NOTHING = "-----";
        
        /**
         * û���趨�����ƣ�.
         */
        String NAME_NOTHING = "----------------";
        
        /**
         * û���趨��ʱ�䣩.
         */
        String HHMM_NOTHING = "--:--";
        
        /**
         * û���趨��ʱ�� ��HHMMSS��.
         */
        String HHMMSS_NOTHING = "--:--:--";
        
        /**
         * û���趨���ڣ�MMDD��.
         */
        String MMDD_NOTHING = "--/--";
        
        /**
         * û���趨���ڣ�YYYYMMDD��.
         */
        String YYYYMMDD_NOTHING = "----/--/--";
    }
    
    /**
     * JSON����.
     */
    interface Json {
        
        /**
         * JSONͷ����KEYֵ.
         */
        String HEADER = "header";
        
        /**
         * JSON����KEYֵ.
         */
        String DATAS = "datas";
        
        /**
         * JSON����KEYֵ.
         */
        String ITEM = "item";
        
        /**
         * JSON����KEYֵ.
         */
        String DISP_HEADER = "dispCode";
        
        /**
         * JSON����KEYֵ.
         */
        String CMD_HEADER = "cmdCode";
        
        /**
         * JSON����KEYֵ.
         */
        String DATA_KIND_HEADER = "dataKind";
        
        /**
         * JSON����KEYֵ.
         */
        String RES_CODE_HEADER = "resCode";
        
        /**
         * JSON����KEYֵ.
         */
        String TARGET_ID_HEADER = "targetId";
        
        /**
         * JSON����KEYֵ.
         */
        String TARGET_INDEX_HEADER = "targetIndex";
        
        /**
         * JSON����KEYֵ.
         */
        String MSG_HEADER = "headerMsg";
        
        /**
         * JSON����KEYֵ.
         */
        String TOTAL_ROWS_HEADER = "totalRows";
        
        /**
         * JSON����KEYֵ.
         */
        String PAGE_SIZE_HEADER = "pageSize";
        
        /**
         * JSON����KEYֵ.
         */
        String ERROR_HEADER = "error";
        
        /**
         * JSON����KEYֵ.
         */
        String TARGET_ID = "targetid";
        
        /**
         * JSON����KEYֵ.
         */
        String TARGET_INDEX = "targetIndex";
        
        /**
         * JSON����KEYֵ.
         */
        String EFFECT = "effect";
        
        /**
         * JSON����KEYֵ.
         */
        String VALUE = "value";
        
        /**
         * JSON����KEYֵ.
         */
        String TEXT = "text";
        
        /**
         * JSON����KEYֵ.
         */
        String DATA_TYPE = "dataType";
        
        /**
         * JSON����KEYֵ.
         */
        String BG_COLOR = "bgColor";
        
        /**
         * JSON����KEYֵ.
         */
        String CHAR_COLOR = "charColor";
        
        /**
         * JSON����KEYֵ.
         */
        String ERR_MSG_COLOR = "errMsgColor";
        
        /**
         * JSON����KEYֵ.
         */
        String DISP_STAT = "dispStat";
        
        /**
         * JSON����KEYֵ.
         */
        String MAX_LEN = "maxLen";
        
        /**
         * JSON����KEYֵ.
         */
        String DETAIL_CODE = "detailCode";
        
        /**
         * JSON����KEYֵ.
         */
        String DETAIL_MSG = "detailMsg";
        
        /**
         * JSON����KEYֵ.
         */
        String ITEM_NAME = "itemName";
        
        /**
         * JSON����KEYֵ.
         */
        String LABEL = "label";
        
        /**
         * JSON����KEYֵ.
         */
        String DATA = "data";
    }
    
    /**
     * �����ֱ���.
     */
    interface Lang {

        /**
         * �������ࣨ���.
         */
        String JA = "ja";
        
        /**
         * �������ࣨ�й��.
         */
        String CN = "zh_CN";
        
        /**
         * �������ࣨӢ�.
         */
        String EN = "eng";
    }
    
    /**
     * ������Ϣ����.
     */
    interface ErrInfo {
        
        /**
         * ϵͳ����.
         */
        String SYSTEM_ERROR = "ϵͳ�쳣!!";
        
        /**
         * ϵͳ����.
         */
        String DB_ERROR = "���ݿ��쳣!!";
        
        /**
         * ��ʱ����.
         */
        String RUN_TIME = "����ʱ����";
    }
    
    /**
     * ��ʽ����.
     */
    interface Request {
        
        /**
         * ���Ϸ�����.
         */
        String COMPLEX_METHOD = "showComplex";
        
        /**
         * ����Ŀ������.
         */
        String SINGLE_METHOD = "showItem";
        
        /**
         * ��Ӧ���ݿ����ɾ�ķ�����.
         */
        String UPDATE_METHOD = "update";
        
        /**
         * �ļ��ϴ�������.
         */
        String UPLOAD_METHOD = "upload";
        
        /**
         * �����ʼ�������.
         */
        String SEND_MAIL_METHOD = "sendMail";
        
        /**
         * ����Ŀ������.
         */
        String LIST_METHOD = "showList";
        
        /**
         * ����.
         */
        String CLASS = "handlerclass";
        
        /**
         * ����.
         */
        String CLASS_CODE = "dispCode";
        
        /**
         * ������.
         */
        String METHOD = "methodName";
        
        /**
         * ������.
         */
        String METHOD_CODE = "methodCode";
        
        /**
         * jsp��.
         */
        String JSP = "jspName";
        
        /**
         * �Ƿ��Զ��ǳ�.
         */
        String LOGOUT = "logout";
        
        /**
         * �Ƿ���servlet����.
         */
        String REQUEST = "request";
        
        /**
         * ��תҳ��ID.
         */
        String FORWARD = "forward";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String CHECK = "check";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String JSP_LANG = "jspLang";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String CLIENT = "client";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String JSON = "jsonName";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String SQL = "sql";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String TARGET_ID = "targetId";
        
        /**
         * �Ƿ���й�ͨ��֤.
         */
        String PAGE_SIZE = "pageSize";
        
        /**
         * ����ܾ�KEY.
         */
        String REQUEST_REFUSE = "requestRefuse";
        
    }
    
    /**
     * ��������.
     */
    interface Cmb {
        
        /**
         * �����򴫵�ֵ.
         */
        String VALUE = "value";
        
        /**
         * ��������ʾֵ.
         */
        String LABEL = "label";
    }
    
    /**
     * ��request.xsd���ļ��еĳ���.
     */
    interface Xsd {
        
        /**
         * ȡ��ϵͳ���ڵ�KEY.
         */
        String SYSDATE = "%sysdate%";
        
        /**
         * ȡ�����ݿ�����е�KEY.
         */
        String SEQUENCE = "%sequence%";
        
        /**
         * ȡ��sessionId��KEY.
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
     * ����ID����.
     */
    interface Method {
        
        /**
         * ������ڴ�����ID.
         */
        String SHOW_PAGE_CODE = "0";
        
        /**
         * ��ҳ����.
         */
        String PAGING_CODE = "99";
        
        /**
         * �������ȡ�ó�Ա����ID.
         */
        String PLUGIN_GET_ITEMS_CODE = "9999";

        /**
         * ��ӡ�����ʾ.
         */
        String SHOW_PRINT_CODE = "100";
        
        /**
         * PDF��ӡ�����ʾ.
         */
        String PRINT_CODE = "101";
        
        /**
         * ���ش���.
         */
        String DOWNLOAD_CODE = "400";
        
        /**
         * �ϴ�����.
         */
        String UPLOAD_CODE = "500";
        
        /**
         * �Զ���ȫ����.
         */
        String AUTOCOMP_COM_CODE = "1001";
        
        /**
         * ������ڴ�����.
         */
        String SHOW_PAGE = "showPage";
        
        /**
         * ��ҳ����.
         */
        String PAGING = "showPageList";
        
        /**
         * ��ͨ��֤������.
         */
        String COMMON_CHECK = "commonChecker";
        
    }
    
    /**
     * ҳ��·�����.
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
         * ��ҳ·��.
         */
        String PAGING_PATH = "/WEB-INF/jsp/frame/inc_page.jsp";
        
        /**
         * ������·��.
         */
        String ERROR_PATH = "/WEB-INF/jsp/frame/err.jsp";
    }
    
    /**
     * ҳ��·�����.
     */
    interface Log {
        
        /**
         * �����־��ʶ.
         */
        String FRAME_SIGN = "[Frame]:";
        
        String LEFT_SIGN = "[";
        
        String RIGHT_SIGN = "] ";
        
        String FRAME_LEFT_SIGN = "[";
        
        String FRAME_RIGHT_SIGN = "] ";
    }
    
    /**
     * ���ݿ����.
     */
    interface Db {
        
        /**
         * sql��.
         */
        String SQL = "FRAME_SQL_CONTENT";
        
        /**
         * sql����.
         */
        String PARAMS = "FRAME_SQL_PARAMS";
        
        /**
         * sql��ID.
         */
        String SQL_ID = "FRAME_SQL_ID";
    }
    
    /**
     * Excel���.
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
