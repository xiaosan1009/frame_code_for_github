package com.richClientFrame.util;

import com.richClientFrame.data.SessionData;

/**
 * 序列生成方法.
 * @author King
 * @since 2011.07.02
 */
public final class CreateSequencesUtil {
    
    /**
     * 构造函数
     */
    private CreateSequencesUtil() {
    }
    
    private static long sSequenceNo = 1;
    
    public static final long MAX_SIZE = 10000;

    public static final Object LOCK = new Object();

    /**
     * 创建序列.
     * @return 序列
     */
    public static String createID() {
        return createID(ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * 创建序列.
     * @param key 自定义序列KEY
     * @return 序列
     */
    public static String createID(String key) {
        return createID(key, null);
    }
    
    /**
     * 创建序列.
     * @param session session
     * @return 序列
     */
    public static String createID(SessionData session) {
        return createID(ConstantsUtil.Str.EMPTY, session);
    }
    
    /**
     * 创建序列.
     * @param key 自定义序列KEY
     * @param session session
     * @return 序列
     */
    public static String createID(String key, SessionData session) {
        
        final String tranDate = DateUtils.getNowTime(DateUtils.FORMAT_YYYYMMDDHHSS3);
        int current = 10;
        synchronized (LOCK) {
            if (sSequenceNo > MAX_SIZE) {
                sSequenceNo = 10;
            }
            current += sSequenceNo++;
        }
        if (CommonUtil.isEmpty(key)) {
            key = "CM";
        }
        final StringBuffer sb = new StringBuffer(key);
        if (session != null && session.getUserInfo() != null) {
            if (CommonUtil.isNotEmpty(session.getUserInfo().getDataSource())) {
                sb.append(session.getUserInfo().getDataSource());
            } else {
                sb.append("00");
            }
            if (CommonUtil.isNotEmpty(session.getUserInfo().getProvinceId())) {
                sb.append(session.getUserInfo().getProvinceId());
            } else {
                sb.append("00");
            }
        } else {
            sb.append("0000");
        }
        sb.append(tranDate).append(current);
        
        return sb.toString();
    }
    
    /** 
    * @Description: 主函数
    * @author king
    * @since Oct 5, 2012 11:01:56 AM 
    * 
    * @param args 参数
    */
    public static void main(String[] args) {
        System.out.println(createID("UB"));
    }
}
