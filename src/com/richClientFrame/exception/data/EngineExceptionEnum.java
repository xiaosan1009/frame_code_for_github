package com.richClientFrame.exception.data;

import com.richClientFrame.info.ControlResourceMap;


/**
 * 类名称 ： EngineExceptionEnum.
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Dec 3, 20125:55:42 PM
 * @author king
 */
public enum EngineExceptionEnum {
    
    // ***********************************************开发提示***********************************************
    // 系统异常类型
    REQUEST_TIMEOUT("WD90130003", "访问远程服务器超时"), 
    SERVICE_EXCEPTION("WD90130004", "服务器端异常，具体信息参照errorInfo属性"),
    JSON_EXCEPTION("WD90130005", "JSON数据格式错误！"),
    // 业务异常类型
    SESSION_TIMEOUT("WD90200001", "连接服务器超时"),
    LOGIN_REPEAT("WD90200002", "用户重复登录"),
    // 共通模块
    FM_COM_SYSTEM_ERROR("FM100010000", ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_SYSTEM_ERROR")),
    FM_COM_UNSUPPORTED_ENCODING_ERROR("FM100010001", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_UNSUPPORTED_ENCODING_ERROR")),
    FM_COM_IO_ERROR("FM100010002", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_IO_ERROR")),
    FM_COM_FILE_NOT_FOUND("FM100010003", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_FILE_NOT_FOUND")),
    FM_COM_NO_SUCH_METHOD("FM100010004", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_NO_SUCH_METHOD")),
    FM_COM_ILLEGAL_ACCESS("FM100010005", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_ILLEGAL_ACCESS")),
    FM_COM_NULL_POINTER("FM100010006", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_COM_NULL_POINTER")),
    // 验证模块
    FM_CHECKER_CHECKINDEX_VALUE_MUST_INDEXNUMBER("FM100110000", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_CHECK_CHECKINDEX_VALUE_MUST_INDEXNUMBER")),
    FM_CHECKER_CANT_GET_ERRMSG_INFO("FM100110001", 
                    ControlResourceMap.getInstance().getItemName("FM_SC_EX_CHECK_CANT_GET_ERRMSG_INFO")),
    // http请求模块
    FM_HTTP_CLIENT_PROTOCOL_ERROR("FM100210000", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_HTTP_CLIENT_PROTOCOL_ERROR")),
    FM_HTTP_IO_ERROR("FM100210001", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_HTTP_IO_ERROR")),
    FM_HTTP_UNSUPPORTED_ENCODING_ERROR("FM100210002", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_HTTP_UNSUPPORTED_ENCODING_ERROR")),
    // excel模块
    FM_EXCEL_XML_SET_READ_MUST_SET_IN("FM100310000", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_EXCEL_XML_SET_READ_MUST_SET_IN")),
    FM_EXCEL_XML_SET_WRITE_MUST_SET_OUT("FM100310001", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_EXCEL_XML_SET_WRITE_MUST_SET_OUT")),
    // condition模块
    FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER("FM100410000", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER")),
    FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER_OR_EQUAL("FM100410001", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER_OR_EQUAL")),
    FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS("FM100410002", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS")),
    FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS_OR_EQUAL("FM100410003", 
            ControlResourceMap.getInstance().getItemName("FM_SC_EX_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS_OR_EQUAL")),
    // ***********************************************用户提示***********************************************
    UR_COM_PAGE_CHANGED("UR100010000", 
            ControlResourceMap.getInstance().getItemName("UR_COM_PAGE_CHANGED")),
    UR_COM_SYSTEM_ERROR("UR100010001", 
            ControlResourceMap.getInstance().getItemName("UR_COM_SYSTEM_ERROR")),
    UR_COM_BLANK("UR100010002", 
            ControlResourceMap.getInstance().getItemName("UR_COM_BLANK")),
    UR_COM_REGISTER_SUCCESS("UR100010003", 
            ControlResourceMap.getInstance().getItemName("UR_COM_REGISTER_SUCCESS")),
    UR_COM_INPUT_ERROR("UR100010004", 
            ControlResourceMap.getInstance().getItemName("UR_COM_INPUT_ERROR")),
    UR_COM_REGISTER_ERROR("UR100010005", 
            ControlResourceMap.getInstance().getItemName("UR_COM_REGISTER_ERROR")),
    UR_COM_READ_ERROR("UR100010006", 
            ControlResourceMap.getInstance().getItemName("UR_COM_READ_ERROR")),
    UR_COM_NO_DATA("UR100010007", 
            ControlResourceMap.getInstance().getItemName("UR_COM_NO_DATA")),
    UR_COM_DELETE_SUCCESS("UR100010008", 
            ControlResourceMap.getInstance().getItemName("UR_COM_DELETE_SUCCESS")),
    UR_COM_INIT_INFO("UR100010009", 
            ControlResourceMap.getInstance().getItemName("UR_COM_INIT_INFO")),
    UR_COM_UPLOAD_SUCCESS("UR100010010", 
            ControlResourceMap.getInstance().getItemName("UR_COM_UPLOAD_SUCCESS")),
    UR_COM_LOGIN_ERROR("UR100010011", 
            ControlResourceMap.getInstance().getItemName("UR_COM_LOGIN_ERROR")),
    UR_COM_SESSION_TIMEOUT("UR100010012", 
            ControlResourceMap.getInstance().getItemName("UR_COM_SESSION_TIMEOUT")),
    UR_COM_DB_ERROR("UR100010013", 
            ControlResourceMap.getInstance().getItemName("UR_COM_DB_ERROR")),
    UR_COM_DELETE_ERROR("UR100010014", 
            ControlResourceMap.getInstance().getItemName("UR_COM_DELETE_ERROR")),
    UR_COM_OPENAME_OVERLAP("UR100010015", 
            ControlResourceMap.getInstance().getItemName("UR_COM_OPENAME_OVERLAP")),
    UR_COM_LOGIN_ERR_SESSION_MAX("UR100010016", 
            ControlResourceMap.getInstance().getItemName("UR_COM_LOGIN_ERR_SESSION_MAX")),
    UR_COM_LOGIN_REPEAT("UR100010017", 
            ControlResourceMap.getInstance().getItemName("UR_COM_LOGIN_REPEAT")),
    UR_COM_INVALID_URL_REQUEST("UR100010018", 
            ControlResourceMap.getInstance().getItemName("UR_COM_INVALID_URL_REQUEST")),
    UR_COM_MAIL_SUCCESS("UR100010019", 
            ControlResourceMap.getInstance().getItemName("UR_COM_MAIL_SUCCESS")),
            
    SYSTEM_ERROR("WD90200003", "服务器端系统异常");
    
    
    private final String code;
    
    private final String errinfo;
    
    /**
     * @Description: 
     * @author king
     * @since Aug 14, 2013 4:58:41 PM 
     * @version V1.0
     * @param key key
     * @return EngineExceptionEnum
     */
    public static EngineExceptionEnum get(String key) {
        final EngineExceptionEnum[] enums = EngineExceptionEnum.values();
        for (int i = 0; i < enums.length; i++) {
            if (key.equals(enums[i].code)) {
                return enums[i];
            }
        }
        return null;
    }
    
    /**
     * 构造函数
     * @param code 错误编码
     * @param errinfo 错误信息
     */
    private EngineExceptionEnum(String code, String errinfo) {
        this.code = code;
        this.errinfo = errinfo;
    }

    /**
     * @Description: 取得错误编码
     * @author king
     * @since Dec 3, 2012 6:01:05 PM 
     * @version V1.0
     * @return 错误编码
     */
    public String getCode() {
        return code;
    }

    /**
     * @Description: 取得错误信息
     * @author king
     * @since Dec 3, 2012 6:01:15 PM 
     * @version V1.0
     * @return 错误信息
     */
    public String getErrinfo() {
        return errinfo;
    }

}
