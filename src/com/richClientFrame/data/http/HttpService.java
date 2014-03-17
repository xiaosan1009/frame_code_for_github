package com.richClientFrame.data.http;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： HttpService
 * 类描述 ： 发送HTTP请求类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.08.31
 * @author king
 */
public final class HttpService {
    
    /**
     * 构造函数
     */
    private HttpService() {
        
    }
    
    /**
     * 模拟get请求.
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, Object> params) throws RichClientWebException {
        
        return doGet(url, null, params);
    }
    
    /**
     * 模拟get请求.
     * 
     * @param url 请求地址
     * @param headers 头文件
     * @param params 请求参数
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params) throws RichClientWebException {
        
        return doGet(url, headers, params, ConstantsUtil.Frame.ENCODING);
    }
    
    /**
     * 模拟get请求.
     * 
     * @param url 请求地址
     * @param headers 头文件
     * @param params 请求参数
     * @param encoding 参数格式
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params, 
            String encoding) throws RichClientWebException {
        return HttpRequest.doGet(url, headers, params, encoding);
    }
    
    /**
     * 模拟post请求.
     * 
     * @param url 请求地址
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(String url) throws RichClientWebException {

        return doPost(url, null, null);
    }
    
    /**
     * 模拟post请求.
     * 
     * @param url 请求地址
     * @param params 请求参数
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, Object> params) throws RichClientWebException {
        
        return doPost(url, null, params);
    }
    
    /**
     * 模拟post请求.
     * 
     * @param url 请求地址
     * @param headers 头文件
     * @param params 请求参数
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params) throws RichClientWebException {
        
        return doPost(url, headers, params, ConstantsUtil.Frame.ENCODING);
    }
    
    /**
     * 模拟post请求.
     * 
     * @param url 请求地址
     * @param headers 头文件
     * @param params 请求参数
     * @param encoding 参数格式
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params, 
            String encoding) throws RichClientWebException {
        return HttpRequest.doPost(url, headers, params, encoding);
    }

}
