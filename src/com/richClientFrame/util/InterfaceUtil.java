package com.richClientFrame.util;

import java.util.Arrays;
import java.util.Map;

/** 
* @ClassName: InterfaceUtil 
* @Description: 接口工具类
* @author king
* @since Nov 3, 2012 12:59:16 PM 
*  
*/
public final class InterfaceUtil {
    
    private static LogUtil sLog = new LogUtil(InterfaceUtil.class, true);
    
    /**
     * 构造函数
     */
    private InterfaceUtil() {
    }
    
    /** 
    * @Description: 创建接口请求安全key
    * @author king
    * @since Nov 3, 2012 1:04:58 PM 
    * 
    * @param map 参数容器
    * @param appInitKey 平台key
    * @return 接口请求安全key
    */
    public static String createSecurityKey(Map<String, String> map, String appInitKey) {
        return KeyedDigestMD5.getKeyedDigest(createParam(map), appInitKey);
    }
    
    /**
     * 组合请求参数(a=b&c=d的形式)
     * 
     * @param map 请求参数
     * @return 返回参数格式
     */
    private static String createParam(Map<String, String> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            // 对参数名按照ASCII升序排序
            final Object[] key = map.keySet().toArray();
            Arrays.sort(key);

            // 生成加密原串
            final StringBuffer res = new StringBuffer(128);
            for (int i = 0; i < key.length; i++) {
                res.append(key[i] + "=" + map.get(key[i]) + "&");
            }

            final String rStr = res.substring(0, res.length() - 1);
            sLog.info("createParam", "end", "请求接口的原始参数为：" + rStr);
            return rStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
