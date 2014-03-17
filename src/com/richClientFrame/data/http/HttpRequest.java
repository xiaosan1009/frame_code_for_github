package com.richClientFrame.data.http;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： HttpRequest
 * 类描述 ： 模拟HTTP请求.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.08.31
 * @author king
 */
public final class HttpRequest {
    
    private static final int TIMEOUT_TIME = 20 * 1000;
    
    private static LogUtil sLog = new LogUtil(HttpRequest.class, true);
    
    /**
     * 构造函数.
     */
    private HttpRequest() {
    }

    /**
     * 模拟get请求.
     * 
     * @param url 请求地址
     * @param headers 头文件
     * @param params 请求参数
     * @param encoding 参数格式
     * @param duan 如果为true则断掉这个get请求
     * @return 请求结果
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, String> headers, 
            Map<String, Object> params, 
            String encoding, 
            boolean duan) throws RichClientWebException {
        
        sLog.info("doGet", "start", "url = " + url);
        
        // 封装返回的参数
        final Result result = new Result();
        try {
            // 实例化一个Httpclient的
            final DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_TIME);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_TIME);
            
            // 如果有参数的就拼装起来
            if (null != params) {
                url += assemblyParameter(params);
            }
            
            // 这是实例化一个get请求
            final HttpGet hp = new HttpGet(url);
            
            // 如果需要头部就组装起来
            if (null != headers) {
                hp.setHeaders(assemblyHeader(headers));
            }
            
            // 执行请求后返回一个HttpResponse
            HttpResponse response;
            response = client.execute(hp);
            
            // 如果为true则断掉这个get请求
            if (duan) {
                hp.abort();
            }
            
            // 返回一个HttpEntity
            final HttpEntity entity = response.getEntity();
            
            // 设置返回的cookie
            result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            // 设置返回的状态
            result.setStatusCode(response.getStatusLine().getStatusCode());
            // 设置返回的头部信心
            result.setHeaders(response.getAllHeaders());
            // 设置返回的信息
            result.setData(EntityUtils.toString(entity, encoding));
        } catch (SocketTimeoutException e) {
            sLog.error("doGet", "SocketTimeoutException", e);
            result.setStatusCode(Result.HTTP_TIME_OUT_STATUS_CODE);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            sLog.error("doGet", "ClientProtocolException", e);
            throw new RichClientWebException(EngineExceptionEnum.FM_HTTP_CLIENT_PROTOCOL_ERROR, e);
        } catch (IOException e) {
            e.printStackTrace();
            sLog.error("doGet", "IOException", e);
            throw new RichClientWebException(EngineExceptionEnum.FM_HTTP_IO_ERROR, e);
        }
        sLog.info("doGet", "end", "result = " + result);
        return result;
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
            Map<String, 
            String> headers, 
            Map<String, Object> params, 
            String encoding) throws RichClientWebException {
        return doGet(url, headers, params, encoding, false);
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
        sLog.info("doPost", "start");
        // 封装返回的参数
        final Result result = new Result();
        try {
            // 实例化一个Httpclient的
            final DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_TIME);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_TIME);
            // 实例化一个post请求
            final HttpPost post = new HttpPost(url);
            
            // 设置需要提交的参数
            if (params != null) {
                final List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (String key : params.keySet()) {
                    if (params.get(key) instanceof String[]) {
                        final String[] arrays = (String[]) params.get(key);
                        for (int i = 0; i < arrays.length; i++) {
                            list.add(new BasicNameValuePair(key, arrays[i]));
                        }
                    } else {
                        list.add(new BasicNameValuePair(key, CommonUtil.toString(params.get(key))));
                    }
                }
                post.setEntity(new UrlEncodedFormEntity(list, encoding));
            }
            
            // 设置头部
            if (null != headers) {
                post.setHeaders(assemblyHeader(headers));
            }
            
            // 实行请求并返回
            final HttpResponse response = client.execute(post);
            if (response == null) {
                sLog.error("doPost", "接口返回结果为空！！");
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity == null) {
                sLog.error("doPost", "接口返回entity为空！！");
            }
            
            // 设置返回状态代码
            result.setStatusCode(response.getStatusLine().getStatusCode());
            // 设置返回的头部信息
            result.setHeaders(response.getAllHeaders());
            // 设置返回的cookie
            sLog.info("doPost", null, "CookieStore = " + client.getCookieStore());
            if (client.getCookieStore() != null) {
                result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            }
            // 设置返回到信息
            result.setData(EntityUtils.toString(entity, encoding));
        } catch (SocketTimeoutException e) {
            sLog.error("doPost", "SocketTimeoutException");
            result.setStatusCode(Result.HTTP_TIME_OUT_STATUS_CODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_HTTP_UNSUPPORTED_ENCODING_ERROR, e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_HTTP_CLIENT_PROTOCOL_ERROR, e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_HTTP_IO_ERROR, e);
        }
        sLog.info("doPost", "end");
        return result;
    }

    /**
     * 构建头文件.
     * 
     * @param headers 头文件
     * @return 头文件
     */
    public static Header[] assemblyHeader(Map<String, String> headers) {
        final Header[] allHeader = new BasicHeader[headers.size()];
        int i = 0;
        for (String str : headers.keySet()) {
            allHeader[i] = new BasicHeader(str, headers.get(str));
            i++;
        }
        return allHeader;
    }

    /**
     * 构建cookie.
     * 
     * @param cookies cookie信息
     * @return cookie
     */
    public static String assemblyCookie(List<Cookie> cookies) {
        final StringBuffer sbu = new StringBuffer();
        for (Cookie cookie : cookies) {
            sbu.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        if (sbu.length() > 0) {
            sbu.deleteCharAt(sbu.length() - 1);
        }
        return sbu.toString();
    }

    /**
     * 构建参数信息.
     * 
     * @param parameters 参数信息
     * @return 参数信息
     */
    public static String assemblyParameter(Map<String, Object> parameters) {
        String para = "?";
        for (String str : parameters.keySet()) {
            para += str + "=" + parameters.get(str) + "&";
        }
        return para.substring(0, para.length() - 1);
    }
}
