package com.richClientFrame.dao;

import com.richClientFrame.util.LogUtil;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� DynamicDataSource
 * ������ �� ��̬�л�����Դ��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.07.12
 * @author king
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    private static LogUtil sLog = new LogUtil(DynamicDataSource.class, true);

    /**
     * ����Դ���ͱ��ֶ���
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    
    /**
     * ����Դ���ֶ���
     */
    private static Map<String,String> sDbMap;
    
    /**
     * ���캯��.
     */
    public DynamicDataSource() {
        CONTEXT_HOLDER.set("ds_oracle1");
    }

    /**
     * ����Դ���ֶ����ȡ��.
     * 
     * @return ����Դ���ֶ���
     */
    public static Map<String, String> getDbMap() {
        return sDbMap;
    }
    
    /**
     * ����Դ���͵�����.
     * 
     * @param dbType ����Դ����
     */
    public static void setDb(String dbType) {
        sLog.info("setDb", null, "dbType = " + dbType);
        CONTEXT_HOLDER.set(dbType);
    }
    
    /**
     * ����Դ���͵�ȡ��.
     * 
     * @param request request
     * @return ����Դ����
     */
    public static String getDb(HttpServletRequest request) {
        if (null != request) {
            return  request.getSession().getAttribute("datasource").toString();
        }
        return CONTEXT_HOLDER.get();
    }

    /**
     * ����Դ���͵�����.
     * 
     */
    public static void clearDb() {
        CONTEXT_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        final Object dataSource = DynamicDataSource.getDb(null);
        sLog.info("determineCurrentLookupKey", null, "dataSource = " + dataSource);
        return dataSource;
    }

}
