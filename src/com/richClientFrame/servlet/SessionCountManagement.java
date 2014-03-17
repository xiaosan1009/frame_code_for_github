package com.richClientFrame.servlet;

import com.richClientFrame.util.LogUtil;

/**
 * session计数类.
 * @author King
 * @since 2010/10/15
 */
public final class SessionCountManagement {

    /**
     * 自对象
     */
    private static SessionCountManagement sIns;
    
    /**
     * 现在session数
     */
    private int sessionCounter;
    
    /**
     * log
     */
    private LogUtil logger = new LogUtil(this.getClass());
    
    /**
     * 构造函数
     */
    private SessionCountManagement() {
        super();
    }
    
    /**
     * 自对象生成<br>.
     * 
     * @return SessionCountManagement 自对象
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
     * session计算.
     * @param sessionSts session状态：true存在
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
     * 现在session数取得.
     * @return 现在session数
     */
    public int getSessionCounter() {
        return sessionCounter;
    }


}
