
package com.richClientFrame.data.http;

import org.apache.http.Header;

import java.util.HashMap;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� Result
 * ������ �� ��ס���󷵻صĲ���.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.08.31
 * @author king
 */
public class Result {
    
    public static final int HTTP_TIME_OUT_STATUS_CODE = 1001;

    private String cookie;

    private int statusCode;

    private HashMap<String, Header> headerAll;
    
    private String data;

    /**
     * ȡ��cookie��Ϣ.
     * @return cookie��Ϣ
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * ����cookie��Ϣ.
     * @param cookie cookie��Ϣ
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * ȡ������״̬��Ϣ.
     * @return ����״̬��Ϣ
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * ��������״̬��Ϣ.
     * @param statusCode ����״̬��Ϣ
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * ȡ������ͷ��Ϣ.
     * @return ����ͷ��Ϣ
     */
    public HashMap<String, Header> getHeaders() {
        return headerAll;
    }

    /**
     * ��������ͷ��Ϣ.
     * @param headers ����ͷ��Ϣ
     */
    public void setHeaders(Header[] headers) {
        headerAll = new HashMap<String, Header>();
        for (Header header : headers) {
            headerAll.put(header.getName(), header);
        }
    }

    /**
     * ȡ����������Ϣ.
     * @return ��������Ϣ
     */
    public String getData() {
        return data;
    }

    /**
     * ������������Ϣ.
     * @param data ��������Ϣ
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * �ж��Ƿ�����ʱ[true:��ʱ;false:δ��ʱ].
     * @return ��������Ϣ
     */
    public boolean isHttpTimeOut() {
        return HTTP_TIME_OUT_STATUS_CODE == statusCode;
    }
}
