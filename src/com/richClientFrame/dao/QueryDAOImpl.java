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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： QueryDAOImpl
 * 类描述 ： 数据库查询类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class QueryDAOImpl extends SqlMapClientDaoSupport implements QueryDAO {

    private LogUtil log = new LogUtil(this.getClass());
    
    private SwitchDataSource switchDataSource;
    
    /**
     * 把数据库查询结果封装成对象返回.
     * 
     * @param <E> 返回结果类型
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param clazz 返回结果类型
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成TableRowMap返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成Map<String, Object>返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成对象返回.
     * 
     * @param <E> 返回结果类型
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param clazz 返回结果类型
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成TableRowMap[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成Map<String, Object>[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
     */
    public Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams) {
        log.debug("executeForMapArray", "start", "sqlID = " + sqlID, "bindParams = " + bindParams);
        
        executeInitialize();

        final Map<String, Object>[] map = executeForObjectArray(sqlID, bindParams, Map.class);

        log.debug("executeForMapArray", "end");

        return map;
    }

    /**
     * 把数据库查询结果封装成对象返回.
     * 
     * @param <E> 返回结果类型
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param clazz 返回结果类型
     * @param beginIndex 查询开始序号
     * @param maxCount 最大查询数量
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成Map<String, Object>[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param beginIndex 查询开始序号
     * @param maxCount 最大查询数量
     * @return 数据库查询结果
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
     * 把数据库查询结果封装成TableRowMap[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param beginIndex 查询开始序号
     * @param maxCount 最大查询数量
     * @return 数据库查询结果
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
     * 取得sql执行的条数.
     * @param sqlID sql文
     * @param bindParams 参数
     * @return sql执行的条数
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
     * @Description: 初始化执行参数
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
