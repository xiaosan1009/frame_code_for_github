
package com.richClientFrame.data.http;

import org.apache.http.Header;

import java.util.HashMap;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： Result
 * 类描述 ： 封住请求返回的参数.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.08.31
 * @author king
 */
public class Result {
    
    public static final int HTTP_TIME_OUT_STATUS_CODE = 1001;

    private String cookie;

    private int statusCode;

    private HashMap<String, Header> headerAll;
    
    private String data;

    /**
     * 取得cookie信息.
     * @return cookie信息
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * 设置cookie信息.
     * @param cookie cookie信息
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * 取得请求状态信息.
     * @return 请求状态信息
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 设置请求状态信息.
     * @param statusCode 请求状态信息
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 取得请求头信息.
     * @return 请求头信息
     */
    public HashMap<String, Header> getHeaders() {
        return headerAll;
    }

    /**
     * 设置请求头信息.
     * @param headers 请求头信息
     */
    public void setHeaders(Header[] headers) {
        headerAll = new HashMap<String, Header>();
        for (Header header : headers) {
            headerAll.put(header.getName(), header);
        }
    }

    /**
     * 取得请求结果信息.
     * @return 请求结果信息
     */
    public String getData() {
        return data;
    }

    /**
     * 设置请求结果信息.
     * @param data 请求结果信息
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * 判断是否请求超时[true:超时;false:未超时].
     * @return 请求结果信息
     */
    public boolean isHttpTimeOut() {
        return HTTP_TIME_OUT_STATUS_CODE == statusCode;
    }
}
