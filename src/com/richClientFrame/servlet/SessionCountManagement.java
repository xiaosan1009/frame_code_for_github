package com.richClientFrame.servlet;

import com.richClientFrame.util.LogUtil;

/**
 * session������.
 * @author King
 * @since 2010/10/15
 */
public final class SessionCountManagement {

    /**
     * �Զ���
     */
    private static SessionCountManagement sIns;
    
    /**
     * ����session��
     */
    private int sessionCounter;
    
    /**
     * log
     */
    private LogUtil logger = new LogUtil(this.getClass());
    
    /**
     * ���캯��
     */
    private SessionCountManagement() {
        super();
    }
    
    /**
     * �Զ�������<br>.
     * 
     * @return SessionCountManagement �Զ���
     */
    public static SessionCountManagement getInstance() {
        synchronized (SessionCountManagement.class) {
            if (sIns == null) {
                sIns = new SessionCountManagement();
            }
        }
        return sIns;
    }
    
    /**
     * session����.
     * @param sessionSts session״̬��true����
     */
    public synchronized void countSession(boolean sessionSts) {
        logger.info("SessionCountManagement : countSession", "start");
        if (sessionSts) {
            sessionCounter++;
        } else {
            sessionCounter--;
        }
        logger.info("SessionCountManagement : countSession", "end", "sessionCounter = " + sessionCounter);
    }

    /**
     * ����session��ȡ��.
     * @return ����session��
     */
    public int getSessionCounter() {
        return sessionCounter;
    }


}
