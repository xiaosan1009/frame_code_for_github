package com.richClientFrame.exception;

import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： RichClientWebException
 * 类描述 ： 共通错误处理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2009.07.28
 * @author king
 */
public class RichClientWebException extends Exception {
    
    /**
     * serial Version
     */
    private static final long serialVersionUID = -5230911354527440220L;
    
    /**
     * 头信息.
     */
    private ResponseHeader errHead;
    
    private String infoCode;
    
    /**
     * 错误序号
     */
    private EngineExceptionEnum mEngineExceptionEnum;
    
    /**
     * 错误信息数组
     */
    private ResponseTab[] errTab;
    
    /**
     * 是否为信息异常【true：不属于错误异常】
     */
    private boolean information;

    /**
     * 构造函数.
     */
    public RichClientWebException() {
        super();

    }
    
    /**
     * 构造函数.
     * @param code 错误信息
     */
    public RichClientWebException(String code) {
        super(code);
        infoCode = code;
        mEngineExceptionEnum = EngineExceptionEnum.get(code);
    }
    
    /**
     * 构造函数.
     * @param exceptionEnum 错误信息
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
    }

    /**
     * 构造函数.
     * @param exceptionEnum 错误信息
     * @param arg1 Throwable异常对象
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, Throwable arg1) {
        super(exceptionEnum.getCode(), arg1);
        mEngineExceptionEnum = exceptionEnum;
    }
    
    /**
     * 构造函数.
     * @param arg0 Throwable异常对象
     */
    public RichClientWebException(Throwable arg0) {
        super(arg0);

    }
    
    /**
     * 构造函数.
     * @param errHead 头信息
     */
    public RichClientWebException(ResponseHeader errHead) {
        this.errHead = errHead;
    }
    
    /**
     * 构造函数.
     * @param exceptionEnum 错误序号
     * @param isInfo 是否为信息错误类型
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, boolean isInfo) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
        this.information = isInfo;
    }
    
    /**
     * 构造函数.
     * @param exceptionEnum 错误序号
     * @param tabs 错误信息数组
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, ResponseTab[] tabs) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
        this.errTab = tabs;
    }
    
    /**
     * 构造函数.
     * @param code 错误信息
     * @param tabs 错误信息数组
     */
    public RichClientWebException(String code, ResponseTab[] tabs) {
        super(code);
        mEngineExceptionEnum = EngineExceptionEnum.get(code);
        this.errTab = tabs;
    }

    /**
     * 获取错误信息数组.
     * @return errTab 错误信息数组
     */
    public ResponseTab[] getErrTab() {
        return errTab;
    }

    /**
     * 设置错误信息数组.
     * @param errTab 错误信息数组
     */
    public void setErrTab(ResponseTab[] errTab) {
        this.errTab = errTab;
    }

    /** 
    * @Description: 取得错误头信息对象
    * @author king
    * @since Oct 6, 2012 11:10:01 AM 
    * 
    * @return 错误头信息对象
    */
    public ResponseHeader getErrHead() {
        return errHead;
    }

    /** 
    * @Description: 设置错误头信息对象
    * @author king
    * @since Oct 6, 2012 11:10:15 AM 
    * 
    * @param errHead 错误头信息对象
    */
    public void setErrHead(ResponseHeader errHead) {
        this.errHead = errHead;
    }

    /** 
    * @Description: 判断是否为信息异常【true：不属于错误异常】
    * @author king
    * @since Oct 4, 2012 11:22:29 AM 
    * 
    * @return 是否为信息异常
    */
    public boolean isInformation() {
        return information;
    }

    /** 
    * @Description: 设置是否为信息异常【true：不属于错误异常】
    * @author king
    * @since Oct 4, 2012 11:22:44 AM 
    * 
    * @param information 是否为信息异常
    */
    public void setInformation(boolean information) {
        this.information = information;
    }

    /**
     * @Description: 
     * @author king
     * @since Aug 14, 2013 4:35:17 PM 
     * @version V1.0
     * @return EngineExceptionEnum
     */
    public EngineExceptionEnum getEngineExceptionEnum() {
        return mEngineExceptionEnum;
    }

    /**
     * @Description: 
     * @author king
     * @since Aug 14, 2013 4:35:25 PM 
     * @version V1.0
     * @param engineExceptionEnum engineExceptionEnum
     */
    public void setEngineExceptionEnum(EngineExceptionEnum engineExceptionEnum) {
        mEngineExceptionEnum = engineExceptionEnum;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }
    
}
