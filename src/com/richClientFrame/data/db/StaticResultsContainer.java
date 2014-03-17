package com.richClientFrame.data.db;

import com.richClientFrame.info.ControlConfig;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� StaticResultsContainer
 * ������ �� ���ݿ�������̬������
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Dec 26, 20123:07:38 PM
 * @author king
 */
public class StaticResultsContainer {
    
    private static StaticResultsContainer sMeContainer;
    
    private Map<String, Integer> countTable = new Hashtable<String, Integer>();

    /**
     * @Description: ��������ȡ��.
     * @author king
     * @since Dec 26, 2012 3:20:50 PM 
     * @version V1.0
     * @return �������
     */
    public static StaticResultsContainer getInstance() {
        synchronized (ControlConfig.class) {
            if (sMeContainer == null) {
                sMeContainer = new StaticResultsContainer();
            }
        }
        return sMeContainer;
    }
    
    /**
     * @Description: �ж��Ƿ������Ӧ��key
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @param params ��������
     * @return �Ƿ������Ӧ��key
     */
    public boolean contains(String sql, Object params) {
        final String key = createResultsContainerKey(sql, params);
        return countTable.containsKey(key);
    }
    
    /**
     * @Description: ȡ����������
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @return �Ƿ������Ӧ��key
     */
    public Integer getCount(String sql) {
        final String key = createResultsContainerKey(sql, null);
        return countTable.get(key);
    }
    
    /**
     * @Description: ������������
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @param count ��������
     */
    public void setCount(String sql, int count) {
        final String key = createResultsContainerKey(sql, null);
        countTable.put(key, count);
    }
    
    /**
     * @Description: �ж��Ƿ������Ӧ��key
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @return �Ƿ������Ӧ��key
     */
    public boolean contains(String sql) {
        final String key = createResultsContainerKey(sql, null);
        return contains(key);
    }
    
    /**
     * @Description: ȡ����������
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @param params ��������
     * @return �Ƿ������Ӧ��key
     */
    public Integer getCount(String sql, Object params) {
        final String key = createResultsContainerKey(sql, params);
        return getCount(key);
    }
    
    /**
     * @Description: ������������
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql��
     * @param params ��������
     * @param count ��������
     */
    public void setCount(String sql, Object params, int count) {
        final String key = createResultsContainerKey(sql, params);
        setCount(key, count);
    }
    
    /**
     * @Description: ��������������key
     * @author king
     * @since Dec 26, 2012 4:06:58 PM 
     * @version V1.0
     * @param sql sql��
     * @param params ��������
     * @return ������key
     */
    public String createResultsContainerKey(String sql, Object params) {
        final StringBuilder sBuilder = new StringBuilder(sql);
        if (params != null && params instanceof Map) {
            Map paramMap = (Map)params;
            final Object[] key = paramMap.keySet().toArray();
            Arrays.sort(key);
            for (int i = 0; i < key.length; i++) {
                sBuilder.append(paramMap.get(key[i]));
            }
        }
        return sBuilder.toString();
    }

}
