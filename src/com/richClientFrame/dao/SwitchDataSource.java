package com.richClientFrame.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.LogUtil;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SwitchDataSource
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Jan 15, 20132:46:47 PM
 * @author king
 */
public class SwitchDataSource {
    
    private static LogUtil sLog = new LogUtil(SwitchDataSource.class, true);
    
    /**
     * ����Դ���ͱ��ֶ���
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    
    private Map<Object, SqlMapClient> targetDataSources;
    
    private SqlMapClient defaultDataSource;
    
    /**
     * @Description: ��������Դ����
     * @author king
     * @since Jan 15, 2013 3:32:17 PM 
     * @version V1.0
     * @param targetDataSources ����Դ����
     */
    public void setTargetDataSources(Map<Object, SqlMapClient> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }
    
    /**
     * ����Դ���͵�����.
     * 
     * @param dbType ����Դ����
     */
    public static void setType(String dbType) {
        sLog.debug("setType", null, "dbType = " + dbType);
        CONTEXT_HOLDER.set(dbType);
    }
    
    /**
     * ����Դ���͵�ȡ��.
     * 
     * @return ����Դ����
     */
    public SqlMapClient getDb() {
        if (CommonUtil.isEmpty(CONTEXT_HOLDER.get())) {
            return defaultDataSource;
        }
        return targetDataSources.get(CONTEXT_HOLDER.get());
    }

    public void setDefaultDataSource(SqlMapClient defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

}
