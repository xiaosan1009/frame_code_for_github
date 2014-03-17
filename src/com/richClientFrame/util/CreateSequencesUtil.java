package com.richClientFrame.util;

import com.richClientFrame.data.SessionData;

/**
 * �������ɷ���.
 * @author King
 * @since 2011.07.02
 */
public final class CreateSequencesUtil {
    
    /**
     * ���캯��
     */
    private CreateSequencesUtil() {
    }
    
    private static long sSequenceNo = 1;
    
    public static final long MAX_SIZE = 10000;

    public static final Object LOCK = new Object();

    /**
     * ��������.
     * @return ����
     */
    public static String createID() {
        return createID(ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * ��������.
     * @param key �Զ�������KEY
     * @return ����
     */
    public static String createID(String key) {
        return createID(key, null);
    }
    
    /**
     * ��������.
     * @param session session
     * @return ����
     */
    public static String createID(SessionData session) {
        return createID(ConstantsUtil.Str.EMPTY, session);
    }
    
    /**
     * ��������.
     * @param key �Զ�������KEY
     * @param session session
     * @return ����
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
    * @Description: ������
    * @author king
    * @since Oct 5, 2012 11:01:56 AM 
    * 
    * @param args ����
    */
    public static void main(String[] args) {
        System.out.println(createID("UB"));
    }
}
