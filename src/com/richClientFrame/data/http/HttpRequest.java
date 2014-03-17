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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� HttpRequest
 * ������ �� ģ��HTTP����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.08.31
 * @author king
 */
public final class HttpRequest {
    
    private static final int TIMEOUT_TIME = 20 * 1000;
    
    private static LogUtil sLog = new LogUtil(HttpRequest.class, true);
    
    /**
     * ���캯��.
     */
    private HttpRequest() {
    }

    /**
     * ģ��get����.
     * 
     * @param url �����ַ
     * @param headers ͷ�ļ�
     * @param params �������
     * @param encoding ������ʽ
     * @param duan ���Ϊtrue��ϵ����get����
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, String> headers, 
            Map<String, Object> params, 
            String encoding, 
            boolean duan) throws RichClientWebException {
        
        sLog.info("doGet", "start", "url = " + url);
        
        // ��װ���صĲ���
        final Result result = new Result();
        try {
            // ʵ����һ��Httpclient��
            final DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_TIME);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_TIME);
            
            // ����в����ľ�ƴװ����
            if (null != params) {
                url += assemblyParameter(params);
            }
            
            // ����ʵ����һ��get����
            final HttpGet hp = new HttpGet(url);
            
            // �����Ҫͷ������װ����
            if (null != headers) {
                hp.setHeaders(assemblyHeader(headers));
            }
            
            // ִ������󷵻�һ��HttpResponse
            HttpResponse response;
            response = client.execute(hp);
            
            // ���Ϊtrue��ϵ����get����
            if (duan) {
                hp.abort();
            }
            
            // ����һ��HttpEntity
            final HttpEntity entity = response.getEntity();
            
            // ���÷��ص�cookie
            result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            // ���÷��ص�״̬
            result.setStatusCode(response.getStatusLine().getStatusCode());
            // ���÷��ص�ͷ������
            result.setHeaders(response.getAllHeaders());
            // ���÷��ص���Ϣ
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
     * ģ��get����.
     * 
     * @param url �����ַ
     * @param headers ͷ�ļ�
     * @param params �������
     * @param encoding ������ʽ
     * @return ������
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
     * ģ��post����.
     * 
     * @param url �����ַ
     * @param headers ͷ�ļ�
     * @param params �������
     * @param encoding ������ʽ
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params, 
            String encoding) throws RichClientWebException {
        sLog.info("doPost", "start");
        // ��װ���صĲ���
        final Result result = new Result();
        try {
            // ʵ����һ��Httpclient��
            final DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_TIME);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_TIME);
            // ʵ����һ��post����
            final HttpPost post = new HttpPost(url);
            
            // ������Ҫ�ύ�Ĳ���
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
            
            // ����ͷ��
            if (null != headers) {
                post.setHeaders(assemblyHeader(headers));
            }
            
            // ʵ�����󲢷���
            final HttpResponse response = client.execute(post);
            if (response == null) {
                sLog.error("doPost", "�ӿڷ��ؽ��Ϊ�գ���");
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity == null) {
                sLog.error("doPost", "�ӿڷ���entityΪ�գ���");
            }
            
            // ���÷���״̬����
            result.setStatusCode(response.getStatusLine().getStatusCode());
            // ���÷��ص�ͷ����Ϣ
            result.setHeaders(response.getAllHeaders());
            // ���÷��ص�cookie
            sLog.info("doPost", null, "CookieStore = " + client.getCookieStore());
            if (client.getCookieStore() != null) {
                result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            }
            // ���÷��ص���Ϣ
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
     * ����ͷ�ļ�.
     * 
     * @param headers ͷ�ļ�
     * @return ͷ�ļ�
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
     * ����cookie.
     * 
     * @param cookies cookie��Ϣ
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
     * ����������Ϣ.
     * 
     * @param parameters ������Ϣ
     * @return ������Ϣ
     */
    public static String assemblyParameter(Map<String, Object> parameters) {
        String para = "?";
        for (String str : parameters.keySet()) {
            para += str + "=" + parameters.get(str) + "&";
        }
        return para.substring(0, para.length() - 1);
    }
}
