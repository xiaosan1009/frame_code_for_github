package com.richClientFrame.exception;

import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� InputException
 * ������ �� ��������쳣������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.05.18
 * @author king
 */
public class InputException extends Exception {

    /**
     * ͷ��Ϣ.
     */
    private ResponseHeader errHead;
    
    /**
     * ������Ϣ
     */
    private ResponseTab[] errTab;
    
    /**
     * �����汾��
     */
    private static final long serialVersionUID = 3690727436075858982L;
    
    /**
     * ���캯��
     */
    public InputException() {
    }

    /**
     * ���캯��
     * 
     * @param arg0
     */
    public InputException(String arg0) {
        super(arg0);
    }

    /**
     * ���캯��
     * 
     * @param arg0
     */
    public InputException(Throwable arg0) {
        super(arg0);
    }

    /**
     * ���캯��
     * 
     * @param arg0
     * @param arg1
     */
    public InputException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    /**
     * ���캯��
     * 
     * @param errHead ͷ��Ϣ
     * @param errTab ������Ϣ
     */
    public InputException(ResponseHeader errHead, ResponseTab[] errTab) {
        super();
        this.errHead = errHead;
        this.errTab = errTab;
    }

    /**
     * ͷ��Ϣȡ��
     * 
     * @return errHead ͷ��Ϣ
     */
    public ResponseHeader getErrHead() {
        return errHead;
    }

    /**
     * ͷ��Ϣ�趨
     * 
     * @param errHead ͷ��Ϣ
     */
    public void setErrHead(ResponseHeader errHead) {
        this.errHead = errHead;
    }

    /**
     * ������Ϣȡ��
     * 
     * @return errTab ������Ϣ
     */
    public ResponseTab[] getErrTab() {
        return errTab;
    }

    /**
     * ������Ϣ�趨
     * 
     * @param errTab ������Ϣ
     */
    public void setErrTab(ResponseTab[] errTab) {
        this.errTab = errTab;
    }

}
