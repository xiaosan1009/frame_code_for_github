package com.richClientFrame.exception;

import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� RichClientWebException
 * ������ �� ��ͨ��������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2009.07.28
 * @author king
 */
public class RichClientWebException extends Exception {
    
    /**
     * serial Version
     */
    private static final long serialVersionUID = -5230911354527440220L;
    
    /**
     * ͷ��Ϣ.
     */
    private ResponseHeader errHead;
    
    private String infoCode;
    
    /**
     * �������
     */
    private EngineExceptionEnum mEngineExceptionEnum;
    
    /**
     * ������Ϣ����
     */
    private ResponseTab[] errTab;
    
    /**
     * �Ƿ�Ϊ��Ϣ�쳣��true�������ڴ����쳣��
     */
    private boolean information;

    /**
     * ���캯��.
     */
    public RichClientWebException() {
        super();

    }
    
    /**
     * ���캯��.
     * @param code ������Ϣ
     */
    public RichClientWebException(String code) {
        super(code);
        infoCode = code;
        mEngineExceptionEnum = EngineExceptionEnum.get(code);
    }
    
    /**
     * ���캯��.
     * @param exceptionEnum ������Ϣ
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
    }

    /**
     * ���캯��.
     * @param exceptionEnum ������Ϣ
     * @param arg1 Throwable�쳣����
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, Throwable arg1) {
        super(exceptionEnum.getCode(), arg1);
        mEngineExceptionEnum = exceptionEnum;
    }
    
    /**
     * ���캯��.
     * @param arg0 Throwable�쳣����
     */
    public RichClientWebException(Throwable arg0) {
        super(arg0);

    }
    
    /**
     * ���캯��.
     * @param errHead ͷ��Ϣ
     */
    public RichClientWebException(ResponseHeader errHead) {
        this.errHead = errHead;
    }
    
    /**
     * ���캯��.
     * @param exceptionEnum �������
     * @param isInfo �Ƿ�Ϊ��Ϣ��������
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, boolean isInfo) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
        this.information = isInfo;
    }
    
    /**
     * ���캯��.
     * @param exceptionEnum �������
     * @param tabs ������Ϣ����
     */
    public RichClientWebException(EngineExceptionEnum exceptionEnum, ResponseTab[] tabs) {
        super(exceptionEnum.getCode());
        mEngineExceptionEnum = exceptionEnum;
        this.errTab = tabs;
    }
    
    /**
     * ���캯��.
     * @param code ������Ϣ
     * @param tabs ������Ϣ����
     */
    public RichClientWebException(String code, ResponseTab[] tabs) {
        super(code);
        mEngineExceptionEnum = EngineExceptionEnum.get(code);
        this.errTab = tabs;
    }

    /**
     * ��ȡ������Ϣ����.
     * @return errTab ������Ϣ����
     */
    public ResponseTab[] getErrTab() {
        return errTab;
    }

    /**
     * ���ô�����Ϣ����.
     * @param errTab ������Ϣ����
     */
    public void setErrTab(ResponseTab[] errTab) {
        this.errTab = errTab;
    }

    /** 
    * @Description: ȡ�ô���ͷ��Ϣ����
    * @author king
    * @since Oct 6, 2012 11:10:01 AM 
    * 
    * @return ����ͷ��Ϣ����
    */
    public ResponseHeader getErrHead() {
        return errHead;
    }

    /** 
    * @Description: ���ô���ͷ��Ϣ����
    * @author king
    * @since Oct 6, 2012 11:10:15 AM 
    * 
    * @param errHead ����ͷ��Ϣ����
    */
    public void setErrHead(ResponseHeader errHead) {
        this.errHead = errHead;
    }

    /** 
    * @Description: �ж��Ƿ�Ϊ��Ϣ�쳣��true�������ڴ����쳣��
    * @author king
    * @since Oct 4, 2012 11:22:29 AM 
    * 
    * @return �Ƿ�Ϊ��Ϣ�쳣
    */
    public boolean isInformation() {
        return information;
    }

    /** 
    * @Description: �����Ƿ�Ϊ��Ϣ�쳣��true�������ڴ����쳣��
    * @author king
    * @since Oct 4, 2012 11:22:44 AM 
    * 
    * @param information �Ƿ�Ϊ��Ϣ�쳣
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
