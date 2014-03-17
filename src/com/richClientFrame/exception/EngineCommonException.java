package com.richClientFrame.exception;

import com.richClientFrame.exception.data.EngineExceptionEnum;

/**
 * ������ �� EngineCommonException.
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Dec 3, 20125:02:59 PM
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
     * ���캯��.
     */
    public EngineCommonException() {
        super();
    }
    
    /**
     * ���캯��.
     * @param errorInfo ������Ϣ
     */
    public EngineCommonException(String errorInfo) {
        super(errorInfo);
    }
    
    /**
     * ���캯��.
     * @param ex Throwable�쳣����
     */
    public EngineCommonException(Throwable ex) {
        super(ex);
    }
    
    /**
     * ���캯��.
     * @param errorInfo ������Ϣ
     * @param ex Throwable�쳣����
     */
    public EngineCommonException(String errorInfo, Throwable ex) {
        super(errorInfo, ex);
    }
    
    /**
     * ���캯��.
     * @param exinfo �������
     */
    public EngineCommonException(EngineExceptionEnum exinfo) {
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = exinfo.getErrinfo();
    }
    
    /**
     * ���캯��.
     * @param exinfo �������
     * @param sysErrInfo �������˴�����Ϣ
     */
    public EngineCommonException(EngineExceptionEnum exinfo, String sysErrInfo) {
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = sysErrInfo;
    }
    
    /**
     * ���캯��.
     * @param ex Throwable�쳣����
     * @param exinfo �������
     */
    public EngineCommonException(Throwable ex, EngineExceptionEnum exinfo) {
        super(ex);
        mExinfo = exinfo;
        errorCode = exinfo.getCode();
        errorInfo = exinfo.getErrinfo();
    }

    /**
     * @Description: ȡ�ô�����
     * @author king
     * @since Dec 3, 2012 5:17:07 PM 
     * @version V1.0
     * @return ������
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * @Description: ȡ�ô�����Ϣ
     * @author king
     * @since Dec 3, 2012 6:06:10 PM 
     * @version V1.0
     * @return ������Ϣ
     */
    public String getErrorMsg() {
        return errorInfo;
    }

}
