package com.richClientFrame.exception;

import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： InputException
 * 类描述 ： 输入错误异常管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.05.18
 * @author king
 */
public class InputException extends Exception {

    /**
     * 头信息.
     */
    private ResponseHeader errHead;
    
    /**
     * 错误信息
     */
    private ResponseTab[] errTab;
    
    /**
     * 连续版本号
     */
    private static final long serialVersionUID = 3690727436075858982L;
    
    /**
     * 构造函数
     */
    public InputException() {
    }

    /**
     * 构造函数
     * 
     * @param arg0
     */
    public InputException(String arg0) {
        super(arg0);
    }

    /**
     * 构造函数
     * 
     * @param arg0
     */
    public InputException(Throwable arg0) {
        super(arg0);
    }

    /**
     * 构造函数
     * 
     * @param arg0
     * @param arg1
     */
    public InputException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    /**
     * 构造函数
     * 
     * @param errHead 头信息
     * @param errTab 错误信息
     */
    public InputException(ResponseHeader errHead, ResponseTab[] errTab) {
        super();
        this.errHead = errHead;
        this.errTab = errTab;
    }

    /**
     * 头信息取得
     * 
     * @return errHead 头信息
     */
    public ResponseHeader getErrHead() {
        return errHead;
    }

    /**
     * 头信息设定
     * 
     * @param errHead 头信息
     */
    public void setErrHead(ResponseHeader errHead) {
        this.errHead = errHead;
    }

    /**
     * 错误信息取得
     * 
     * @return errTab 错误信息
     */
    public ResponseTab[] getErrTab() {
        return errTab;
    }

    /**
     * 错误信息设定
     * 
     * @param errTab 错误信息
     */
    public void setErrTab(ResponseTab[] errTab) {
        this.errTab = errTab;
    }

}
