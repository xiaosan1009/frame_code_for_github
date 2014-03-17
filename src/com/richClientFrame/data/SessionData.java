/**
 * 
 */
package com.richClientFrame.data;

import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.util.CommonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SessionData
 * 类描述 ： session管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class SessionData implements Cloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * session存储KEY.
     */
    public static final String KEY_SESSIONDATA = "seData";
    
    /**
     * 错误信息存储KEY.
     */
    public static final String KEY_ERRMSG = "ERRMSG";

    
    /**
     * session ID.
     */
    private String sessionId;
    
    /**
     * 客户端地址
     */
    private String remoteAddr;
    
    /**
     * 最后一次登录时间
     */
    private long lastAccessedTime;
    
    /**
     * 登录用户信息
     */
    private UserInfo userInfo;
    
    private int mPageSize;
    
    private int mTotalRows;
    
    /**
     * 详细错误信息
     */
    private Map<String, Exception> detailErrMap = new HashMap<String, Exception>();
    
    /**
     * 详细错误信息
     */
    private Map<String, Object> datas = new HashMap<String, Object>();    
    
    /**
     * 构造函数.
     */
    public SessionData() {
    }


    /**
     * session ID 取得.
     * @return session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * session ID 设定.
     * @param sessionId session ID
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 客户端地址取得.
     * @return 客户端地址
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }


    /**
     * 客户端地址设定.
     * @param remoteAddr 客户端地址
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }


    /**
     * 最后一次登录时间取得.
     * @return 最后一次登录时间
     */
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }


    /**
     * 最后一次登录时间设定.
     * @param lastAccessedTime 最后一次登录时间
     */
    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    /**
     * 登录用户信息取得.
     * @return 登录用户信息
     */        
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * 登录用户信息设定.
     * @param userInfo 登录用户信息
     */    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 详细错误信息取得.
     * @return 详细错误信息
     */
    public Map<String, Exception> getDetailErrMap() {
        return detailErrMap;
    }
    
    /**
     * 指定画面的错误信息取得.
     * @param dispId 画面ID
     * @return 指定画面的错误信息
     */
    public Exception getDetailErr(String dispId) {        
        if (detailErrMap != null) {
            return detailErrMap.get(dispId);
        } else {
            return null;
        }
    }
    
    /**
     * 指定画面的错误信息设定.
     * @param dispId 画面ID
     * @param ex 错误信息
     */
    public void setDetailErr(String dispId, Exception ex) {
        if (this.detailErrMap != null) {
            this.detailErrMap.put(dispId, ex);
        }
    }
    
    /**
     * session信息取得.
     * @param key session key
     * @return session信息
     */
    public Object get(String key) {
        return datas.get(key);
    }
    
    /**
     * session信息取得.
     * @param key session key
     * @return session信息
     */
    public String getString(String key) {
        return CommonUtil.toString(datas.get(key));
    }
    
    /**
     * session信息设定.
     * @param key session key
     * @param value value
     */
    public void set(String key, Object value) {
        datas.put(key, value);
    }
    
    /**
     * session信息的移除.
     * @param key session key
     * @return 移除的对象信息
     */
    public Object remove(String key) {
        final Object value = datas.remove(key);
        return value;
    }
    
    /**
     * 指定画面的错误信息设定.
     * @param dispId 画面ID
     * @param resData 包含错误信息的对象
     */
    public void setDetailErr(String dispId, ResponseData resData) {
        if (this.detailErrMap != null) {
            this.detailErrMap.put(dispId, 
                    new RichClientWebException(resData.getResHeader().getResCode(), resData.getResTab()));
        }
    }
    
    /**
     * session对象的复制.
     * @return session对象
     */
    public SessionData clone() {
        SessionData copyData = null;
        try {
            copyData = (SessionData)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
        
        return copyData;
    }


    public int getPageSize() {
        return mPageSize;
    }


    public void setPageSize(int pageSize) {
        this.mPageSize = pageSize;
    }


    public int getTotalRows() {
        return mTotalRows;
    }


    public void setTotalRows(int totalRows) {
        this.mTotalRows = totalRows;
    }
}
