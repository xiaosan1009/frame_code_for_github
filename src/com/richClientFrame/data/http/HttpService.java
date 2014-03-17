package com.richClientFrame.data.http;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� HttpService
 * ������ �� ����HTTP������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.08.31
 * @author king
 */
public final class HttpService {
    
    /**
     * ���캯��
     */
    private HttpService() {
        
    }
    
    /**
     * ģ��get����.
     * 
     * @param url �����ַ
     * @param params �������
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, Object> params) throws RichClientWebException {
        
        return doGet(url, null, params);
    }
    
    /**
     * ģ��get����.
     * 
     * @param url �����ַ
     * @param headers ͷ�ļ�
     * @param params �������
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doGet(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params) throws RichClientWebException {
        
        return doGet(url, headers, params, ConstantsUtil.Frame.ENCODING);
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
            Map<String, String> headers,
            Map<String, Object> params, 
            String encoding) throws RichClientWebException {
        return HttpRequest.doGet(url, headers, params, encoding);
    }
    
    /**
     * ģ��post����.
     * 
     * @param url �����ַ
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(String url) throws RichClientWebException {

        return doPost(url, null, null);
    }
    
    /**
     * ģ��post����.
     * 
     * @param url �����ַ
     * @param params �������
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, Object> params) throws RichClientWebException {
        
        return doPost(url, null, params);
    }
    
    /**
     * ģ��post����.
     * 
     * @param url �����ַ
     * @param headers ͷ�ļ�
     * @param params �������
     * @return ������
     * @throws RichClientWebException RichClientWebException
     */
    public static Result doPost(
            String url, 
            Map<String, String> headers,
            Map<String, Object> params) throws RichClientWebException {
        
        return doPost(url, headers, params, ConstantsUtil.Frame.ENCODING);
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
        return HttpRequest.doPost(url, headers, params, encoding);
    }

}
