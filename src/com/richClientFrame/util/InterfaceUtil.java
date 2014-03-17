package com.richClientFrame.util;

import java.util.Arrays;
import java.util.Map;

/** 
* @ClassName: InterfaceUtil 
* @Description: �ӿڹ�����
* @author king
* @since Nov 3, 2012 12:59:16 PM 
*  
*/
public final class InterfaceUtil {
    
    private static LogUtil sLog = new LogUtil(InterfaceUtil.class, true);
    
    /**
     * ���캯��
     */
    private InterfaceUtil() {
    }
    
    /** 
    * @Description: �����ӿ�����ȫkey
    * @author king
    * @since Nov 3, 2012 1:04:58 PM 
    * 
    * @param map ��������
    * @param appInitKey ƽ̨key
    * @return �ӿ�����ȫkey
    */
    public static String createSecurityKey(Map<String, String> map, String appInitKey) {
        return KeyedDigestMD5.getKeyedDigest(createParam(map), appInitKey);
    }
    
    /**
     * ����������(a=b&c=d����ʽ)
     * 
     * @param map �������
     * @return ���ز�����ʽ
     */
    private static String createParam(Map<String, String> map) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }

            // �Բ���������ASCII��������
            final Object[] key = map.keySet().toArray();
            Arrays.sort(key);

            // ���ɼ���ԭ��
            final StringBuffer res = new StringBuffer(128);
            for (int i = 0; i < key.length; i++) {
                res.append(key[i] + "=" + map.get(key[i]) + "&");
            }

            final String rStr = res.substring(0, res.length() - 1);
            sLog.info("createParam", "end", "����ӿڵ�ԭʼ����Ϊ��" + rStr);
            return rStr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
