package com.richClientFrame.info;

import java.util.Hashtable;

import com.richClientFrame.exception.RichClientWebException;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlOperator
 * ������ �� �û���Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.10.07
 * @author king
 */
public class ControlOperator {
    
    /**
     * �������
     */
    private static ControlOperator sMe;
        
    /**
     * ͬ����
     */
    private static Object sLockObj = new Object();
    
    /**
     * ��¼�û��������
     */
    private Hashtable<String, LoginOperator> logOpeTable;
    
    /**
     * �Զ���ȡ��.
     * @return ControlOperator �Զ���
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlOperator getInstance() throws RichClientWebException {
        
        synchronized (ControlOperator.class) {
            if (sMe == null) {
                sMe = new ControlOperator();
            }
        }
        
        return sMe;
    }
    
    /**
     * ���캯��.
     * @throws RichClientWebException RichClientWebException
     */
    public ControlOperator() throws RichClientWebException {
        super();
        // ��¼�û���Ϣ�����������
        this.logOpeTable = new Hashtable<String, LoginOperator>();
    }
    
    //--------------------------------------------------------------
    /* 
     * ��¼�û���Ϣ�Ĳ���
     */
    //--------------------------------------------------------------
    
    /**
     * ��¼�û���Ϣ׷��.
     * @param clientIP �ͻ�IP
     * @param loginOperator ��¼�û���Ϣ
     */
    public void putLoginOperator(String clientIP, LoginOperator loginOperator) {
        synchronized (sLockObj) {
            logOpeTable.put(clientIP, loginOperator);
        }
    }
    
    /**
     * ָ��IP�ĵ�¼�û���Ϣ��ȡ��.
     * @param clientIP �û�IP
     * @return ��¼�û���Ϣ
     */
    public LoginOperator getLoginOperator(String clientIP) {
        synchronized (sLockObj) {
            return logOpeTable.get(clientIP);
        }
    }
    
    /**
     * ��¼�û���Ϣ��ɾ��.
     * @param clientIP �û�IP
     */
    public void removeLoginOperator(String clientIP) {
        synchronized (sLockObj) {
            logOpeTable.remove(clientIP);
        }
    }
}