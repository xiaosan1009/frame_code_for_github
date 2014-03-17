package com.richClientFrame.exception;

import com.richClientFrame.exception.data.EngineExceptionEnum;

/**
 * 类名称 ： EngineCommonException.
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Dec 3, 20125:02:59 PM
 * @author king
 */
public class EngineCommonException extends Exception {
    
    private String errorCode;
    
    private String errorInfo;
    
    private EngineExceptionEnum mExinfo;

    public EngineExceptionEnum getExinfo() {
        return mExinfo;
    }

    public void setExinfo(EngineExceptionEnum mExinfo) {
        this.mExinfo = mExinfo;
    }

    /**
     * serial Version
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数.
     */
    public EngineCommonException() {
        super();
    }
    
    /**
     * 构造函数.
     * @param errorInfo 错误信息
     */
    public EngineCommonException(String errorInfo) {
        super(errorInfo);
    }
    
    /**
     * 构造函数.
     * @param ex Throwable异常对象
     */
    public EngineCommonException(Throwable ex) {
        super(ex);
    }
    
    /**
     * 构造函数.
     * @param errorInfo 错误信息
     * @param ex Throwable异常对象
     */
    public EngineCommonException(String errorInfo, Throwable ex) {
        super(errorInfo, ex);
    }
    
    /**
     * 构造函数.
     * @param exinfo 错误序号
     */
    public EngineCommonException(EngineExceptionEnum exinfo) {
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = exinfo.getErrinfo();
    }
    
    /**
     * 构造函数.
     * @param exinfo 错误序号
     * @param sysErrInfo 服务器端错误信息
     */
    public EngineCommonException(EngineExceptionEnum exinfo, String sysErrInfo) {
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = sysErrInfo;
    }
    
    /**
     * 构造函数.
     * @param ex Throwable异常对象
     * @param exinfo 错误序号
     */
    public EngineCommonException(Throwable ex, EngineExceptionEnum exinfo) {
        super(ex);
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = exinfo.getErrinfo();
    }

    /**
     * @Description: 取得错误编号
     * @author king
     * @since Dec 3, 2012 5:17:07 PM 
     * @version V1.0
     * @return 错误编号
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * @Description: 取得错误信息
     * @author king
     * @since Dec 3, 2012 6:06:10 PM 
     * @version V1.0
     * @return 错误信息
     */
    public String getErrorMsg() {
        return errorInfo;
    }

}
