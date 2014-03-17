package com.richClientFrame.dao;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.LogUtil;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� QueryDAOImpl
 * ������ �� ���ݿ��ѯ��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class QueryDAOImpl extends SqlMapClientDaoSupport implements QueryDAO {

    private LogUtil log = new LogUtil(this.getClass());
    
    private SwitchDataSource switchDataSource;
    
    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @return ���ݿ��ѯ���
     */
    @SuppressWarnings("unchecked")
    public <E> E executeForObject(String sqlID, Object bindParams, Class<?> clazz) {

        log.debug("executeForObject", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        E rObj = null;
        try {
            executeInitialize();
            
            final Object obj = getSqlMapClientTemplate().queryForObject(sqlID, bindParams);
            if (clazz != null && obj != null) {
                log.info("Return type:" + obj.getClass().getName());
                rObj = (E) clazz.cast(obj);
            }
        } catch (ClassCastException e) {
            log.error("ClassCastException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForObject", "end");

        return rObj;
    }
    
    /**
     * �����ݿ��ѯ�����װ��TableRowMap����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    public TableRowMap executeForObject(String sqlID, Object bindParams) {
        log.debug("executeForObject", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        executeInitialize();

        final Object obj = getSqlMapClientTemplate().queryForObject(sqlID, bindParams);

        TableRowMap rObj = null;
        try {
            if (obj != null) {
                log.info("Return type:" + obj.getClass().getName());
                rObj = TableRowMap.class.cast(obj);
            }
        } catch (ClassCastException e) {
            log.error("ClassCastException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForObject", "end");

        return rObj;
    }

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> executeForMap(String sqlID, Object bindParams) {
        log.debug("executeForMap", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        executeInitialize();

        final Map<String, Object> rObj = getSqlMapClientTemplate().queryForMap(sqlID, bindParams, null);

        log.debug("executeForMap", "end");

        return rObj;
    }

    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @return ���ݿ��ѯ���
     */
    @SuppressWarnings("unchecked")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<?> clazz) {
        log.debug("executeForObjectArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);

        if (clazz == null) {
            return null;
        }
        
        executeInitialize();

        final List list = getSqlMapClientTemplate().queryForList(sqlID, bindParams);

        final E[] retArray = (E[]) Array.newInstance(clazz, list.size());
        try {
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            log.error("ArrayStoreException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForObjectArray", "end");

        return retArray;
    }
    
    /**
     * �����ݿ��ѯ�����װ��TableRowMap[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    public TableRowMap[] executeForArray(String sqlID, Object bindParams) {
        log.debug("executeForArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        executeInitialize();
        
        final List<?> list = getSqlMapClientTemplate().queryForList(sqlID, bindParams);
        
        log.info("executeForArray", null, "size = " + list.size());

        final TableRowMap[] retArray = (TableRowMap[]) Array.newInstance(TableRowMap.class, list.size());
        try {
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            log.error("ArrayStoreException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForArray", "end");

        return retArray;
    }

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams) {
        log.debug("executeForMapArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        executeInitialize();

        final Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class);

        log.debug("executeForMapArray", "end");

        return map;
    }

    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    @SuppressWarnings("unchecked")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams,
            Class<?> clazz, int beginIndex, int maxCount) {
        log.debug("executeForObjectArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams, 
                "beginIndex = " + beginIndex, "maxCount = " + maxCount);

        if (clazz == null) {
            return null;
        }
        
        executeInitialize();

        final List<?> list = getSqlMapClientTemplate().queryForList(sqlID, bindParams, beginIndex, maxCount);

        final E[] retArray = (E[]) Array.newInstance(clazz, list.size());
        try {
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            log.error("ArrayStoreException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForObjectArray", "end");

        return retArray;
    }

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams, int beginIndex, int maxCount) {
        log.debug("executeForMapArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams, 
                "beginIndex = " + beginIndex, "maxCount = " + maxCount);
        
        executeInitialize();

        final Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class, beginIndex, maxCount);

        log.debug("executeForMapArray", "end");

        return map;
    }

    /**
     * �����ݿ��ѯ�����װ��TableRowMap[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    public TableRowMap[] executeForArray(String sqlID, Object bindParams,
            int beginIndex, int maxCount) {
        log.info("executeForArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams, 
                "beginIndex = " + beginIndex, "maxCount = " + maxCount);
        
        executeInitialize();

        final List<?> list = getSqlMapClientTemplate().queryForList(sqlID, bindParams, beginIndex, maxCount);

        final TableRowMap[] retArray = (TableRowMap[]) Array.newInstance(TableRowMap.class, list.size());
        try {
            list.toArray(retArray);
        } catch (ArrayStoreException e) {
            log.error("ArrayStoreException :" + e.getMessage(), e);
            e.printStackTrace();
        }

        log.debug("executeForArray", "end");

        return retArray;
    }
    
    /**
     * ȡ��sqlִ�е�����.
     * @param sqlID sql��
     * @param bindParams ����
     * @return sqlִ�е�����
     */
    public int executeForCount(String sqlID, Object bindParams) {
        log.debug("executeForCount", "start", "sqlID = " + sqlID);
        executeInitialize();
        final SqlMapExecutorDelegate delegate = ((SqlMapClientImpl)(
                getSqlMapClientTemplate().getSqlMapClient())).getDelegate();
        final MappedStatement ms = delegate.getMappedStatement(sqlID);
        final Sql sql = ms.getSql();
        final SessionScope sessionScope = new SessionScope();
        sessionScope.incrementRequestStackDepth();
        final StatementScope statementScope = new StatementScope(sessionScope);
        ms.initRequest(statementScope);
        final String exeSql = sql.getSql(statementScope, bindParams);
        final ParameterMap parameterMap = sql.getParameterMap(statementScope, bindParams);
        ms.setParameterMap(parameterMap);
        final Object[] parameterArr = parameterMap.getParameterObjectValues(statementScope, bindParams);
        final StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append("select count(*) from (");
        sqlBuff.append(exeSql);
        for (int i = 0; i < parameterArr.length; i++) {
            final int index = sqlBuff.indexOf("?");
            if (parameterArr[i] != null) {
                sqlBuff.replace(index, index + 1, "'" + parameterArr[i].toString() + "'");
            } else {
                sqlBuff.replace(index, index + 1, "null");
            }
        }
        sqlBuff.append(") frame_t");
        log.debug("executeForCount", null, "sql = " + sqlBuff.toString());
        final Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("sql", sqlBuff.toString());
        final int count = (Integer)getSqlMapClientTemplate().queryForObject("Frame.execute", conditions);
//        final int count = executeForObject("Frame.execute", conditions, Integer.class);
        return count;
    }
    
    /**
     * @Description: ��ʼ��ִ�в���
     * @author king
     * @since Jan 15, 2013 11:39:26 AM 
     * @version V1.0
     */
    private void executeInitialize() {
        if (switchDataSource != null && switchDataSource.getDb() != null) {
            setSqlMapClient(switchDataSource.getDb());
        }
    }

    public void setSwitchDataSource(SwitchDataSource switchDataSource) {
        this.switchDataSource = switchDataSource;
    }

}
