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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SessionData
 * ������ �� session������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class SessionData implements Cloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * session�洢KEY.
     */
    public static final String KEY_SESSIONDATA = "seData";
    
    /**
     * ������Ϣ�洢KEY.
     */
    public static final String KEY_ERRMSG = "ERRMSG";

    
    /**
     * session ID.
     */
    private String sessionId;
    
    /**
     * �ͻ��˵�ַ
     */
    private String remoteAddr;
    
    /**
     * ���һ�ε�¼ʱ��
     */
    private long lastAccessedTime;
    
    /**
     * ��¼�û���Ϣ
     */
    private UserInfo userInfo;
    
    private int mPageSize;
    
    private int mTotalRows;
    
    /**
     * ��ϸ������Ϣ
     */
    private Map<String, Exception> detailErrMap = new HashMap<String, Exception>();
    
    /**
     * ��ϸ������Ϣ
     */
    private Map<String, Object> datas = new HashMap<String, Object>();    
    
    /**
     * ���캯��.
     */
    public SessionData() {
    }


    /**
     * session ID ȡ��.
     * @return session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * session ID �趨.
     * @param sessionId session ID
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * �ͻ��˵�ַȡ��.
     * @return �ͻ��˵�ַ
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }


    /**
     * �ͻ��˵�ַ�趨.
     * @param remoteAddr �ͻ��˵�ַ
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }


    /**
     * ���һ�ε�¼ʱ��ȡ��.
     * @return ���һ�ε�¼ʱ��
     */
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }


    /**
     * ���һ�ε�¼ʱ���趨.
     * @param lastAccessedTime ���һ�ε�¼ʱ��
     */
    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    /**
     * ��¼�û���Ϣȡ��.
     * @return ��¼�û���Ϣ
     */        
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * ��¼�û���Ϣ�趨.
     * @param userInfo ��¼�û���Ϣ
     */    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * ��ϸ������Ϣȡ��.
     * @return ��ϸ������Ϣ
     */
    public Map<String, Exception> getDetailErrMap() {
        return detailErrMap;
    }
    
    /**
     * ָ������Ĵ�����Ϣȡ��.
     * @param dispId ����ID
     * @return ָ������Ĵ�����Ϣ
     */
    public Exception getDetailErr(String dispId) {        
        if (detailErrMap != null) {
            return detailErrMap.get(dispId);
        } else {
            return null;
        }
    }
    
    /**
     * ָ������Ĵ�����Ϣ�趨.
     * @param dispId ����ID
     * @param ex ������Ϣ
     */
    public void setDetailErr(String dispId, Exception ex) {
        if (this.detailErrMap != null) {
            this.detailErrMap.put(dispId, ex);
        }
    }
    
    /**
     * session��Ϣȡ��.
     * @param key session key
     * @return session��Ϣ
     */
    public Object get(String key) {
        return datas.get(key);
    }
    
    /**
     * session��Ϣȡ��.
     * @param key session key
     * @return session��Ϣ
     */
    public String getString(String key) {
        return CommonUtil.toString(datas.get(key));
    }
    
    /**
     * session��Ϣ�趨.
     * @param key session key
     * @param value value
     */
    public void set(String key, Object value) {
        datas.put(key, value);
    }
    
    /**
     * session��Ϣ���Ƴ�.
     * @param key session key
     * @return �Ƴ��Ķ�����Ϣ
     */
    public Object remove(String key) {
        final Object value = datas.remove(key);
        return value;
    }
    
    /**
     * ָ������Ĵ�����Ϣ�趨.
     * @param dispId ����ID
     * @param resData ����������Ϣ�Ķ���
     */
    public void setDetailErr(String dispId, ResponseData resData) {
        if (this.detailErrMap != null) {
            this.detailErrMap.put(dispId, 
                    new RichClientWebException(resData.getResHeader().getResCode(), resData.getResTab()));
        }
    }
    
    /**
     * session����ĸ���.
     * @return session����
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
