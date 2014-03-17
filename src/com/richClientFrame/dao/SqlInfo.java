package com.richClientFrame.dao;

import com.ibatis.sqlmap.engine.mapping.result.ResultMapping;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� SqlInfo
 * ������ �� sql��Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Sep 11, 2012 3:01:16 PM 
 * @author king
 */
public class SqlInfo {
    
    private String sql;
    
    private ResultMapping[] resultMappings;

    /**
     * ȡ��sql����Ϣ.
     * @return sql����Ϣ
     */
    public String getSql() {
        return sql;
    }

    /**
     * ����sql����Ϣ.
     * @param sql sql����Ϣ.
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * ȡ��sql�Ĳ�����Ϣ.
     * @return sql�Ĳ�����Ϣ
     */
    public ResultMapping[] getResultMappings() {
        return resultMappings;
    }

    /**
     * ����sql�Ĳ�����Ϣ.
     * @param resultMappings sql�Ĳ�����Ϣ.
     */
    public void setResultMappings(ResultMapping[] resultMappings) {
        this.resultMappings = resultMappings;
    }

}
