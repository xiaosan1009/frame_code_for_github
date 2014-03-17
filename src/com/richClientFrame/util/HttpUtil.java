package com.richClientFrame.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 关于HTTP请求共通操作类.
 * 
 * @author King
 * @since 2012.02.27
 */
public final class HttpUtil {
    
    /**
     * 构造函数.
     */
    private HttpUtil() {
    }
    
    /**
     * 功能描述 : 检测当前URL是否可连接或是否有效, 最多连接网络 5 次, 如果 5 次都不成功说明该地址不存在或视为无效地址.
     * 
     * @param url 指定URL网络地址
     * @return 检测结果
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
