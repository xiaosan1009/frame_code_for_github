package com.richClientFrame.info;

import com.richClientFrame.data.SessionData;

/**
 * 登录用户信息管理类.
 * @author King
 * @since 2010.06.11
 */

public class LoginOperator {
    
    /**
     * 登录的session信息
     */
    private SessionData seData;
    
    /**
     * 构造函数.
     * @param seData session信息
     */
    public LoginOperator(SessionData seData) {
        super();
        this.seData = seData;
    }

    /**
     * 登录时session信息取得.
     * @return session信息
     */
    public SessionData getSeData() {
        return seData;
    }

    /**
     * 登录时session信息设定.
     * @param seData ession信息
     */
    public void setSeData(SessionData seData) {
        this.seData = seData;
    }

}