package com.richClientFrame.info;

import com.richClientFrame.data.SessionData;

/**
 * ��¼�û���Ϣ������.
 * @author King
 * @since 2010.06.11
 */

public class LoginOperator {
    
    /**
     * ��¼��session��Ϣ
     */
    private SessionData seData;
    
    /**
     * ���캯��.
     * @param seData session��Ϣ
     */
    public LoginOperator(SessionData seData) {
        super();
        this.seData = seData;
    }

    /**
     * ��¼ʱsession��Ϣȡ��.
     * @return session��Ϣ
     */
    public SessionData getSeData() {
        return seData;
    }

    /**
     * ��¼ʱsession��Ϣ�趨.
     * @param seData ession��Ϣ
     */
    public void setSeData(SessionData seData) {
        this.seData = seData;
    }

}