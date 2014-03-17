package com.richClientFrame.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ����HTTP����ͨ������.
 * 
 * @author King
 * @since 2012.02.27
 */
public final class HttpUtil {
    
    /**
     * ���캯��.
     */
    private HttpUtil() {
    }
    
    /**
     * �������� : ��⵱ǰURL�Ƿ�����ӻ��Ƿ���Ч, ����������� 5 ��, ��� 5 �ζ����ɹ�˵���õ�ַ�����ڻ���Ϊ��Ч��ַ.
     * 
     * @param url ָ��URL�����ַ
     * @return �����
     */
    public static synchronized boolean isConnect(String url) {
        int counts = 0;
        while (counts < 5) {
            try {
                final URL urlStr = new URL(url);
                final HttpURLConnection connection = (HttpURLConnection)urlStr.openConnection();
                final int state = connection.getResponseCode();
                if (state == 200) {
                    return true;
                }
                break;
            } catch (Exception ex) {
                counts++;
                continue;
            }
        }
        return false;
    }

}
