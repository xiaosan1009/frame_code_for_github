package com.richClientFrame.dao;

import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： UpdateDAOImpl
 * 类描述 ： 数据库添加删除类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class UpdateDAOImpl extends SqlMapClientDaoSupport implements UpdateDAO {

    private final LogUtil log = new LogUtil(this.getClass());
    
    private SwitchDataSource switchDataSource;

    /**
     * 把数据库修改结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库修改结果
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams) throws DataAccessException {
        return update(sqlID, bindParams, null);
    }
    
    /**
     * 把数据库修改结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param tab 信息保存容器
     * @return 数据库修改结果
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException {
        log.debug("update Start 【sqlID = " + sqlID + "】 【bindParams = " + bindParams + "】");
        
        executeInitialize();
        
        final int row = getSqlMapClientTemplate().update(sqlID, bindParams);
        if (tab != null) {
            getSql(sqlID, bindParams, tab);
        }
        
        log.debug("update End. success count:" + row);
        return row;
    }

    /**
     * 把数据库添加结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库添加结果
     * @throws DataAccessException DataAccessException
     */
    public TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException {
        log.debug("insert Start 【sqlID = " + sqlID + "】 【bindParams = " + bindParams + "】");
        
        TableRowMap rObj = null;
        
        try {
            executeInitialize();
            
            final Object obj = getSqlMapClientTemplate().insert(sqlID, bindParams);
            if (obj != null) {
                log.debug("Return type:" + obj.getClass().getName());
                if (obj instanceof Integer && obj != null) {
                    rObj = new TableRowMap();
                    rObj.put("id", Integer.parseInt(obj.toString()));
                } else {
                    rObj = TableRowMap.class.cast(obj);
                }
            }
            if (rObj == null) {
                rObj = new TableRowMap();
            }
            getSql(sqlID, bindParams, rObj);
            log.debug("insert End. success count:" + obj);
        } catch (ClassCastException e) {
            logger.error("ClassCastException :" + e.getMessage());
            e.printStackTrace();
        }

        return rObj;
    }
    

    /**
     * 取得sql信息
     * @param sqlID sql文
     * @param bindParams 参数
     * @param tab 信息保存容器
     */
    @SuppressWarnings("deprecation")
    private void getSql(String sqlID, Object bindParams, TableRowMap tab) {
        log.debug("UpdateDAOImpl : getSql", "start");
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
        tab.put(ConstantsUtil.Db.SQL, exeSql);
        tab.put(ConstantsUtil.Db.PARAMS, parameterArr);
        tab.put(ConstantsUtil.Db.SQL_ID, sqlID);
        log.debug("getSql Start 【sqlID = " + sqlID + "】 【sql = " + exeSql + "】");
        log.debug("UpdateDAOImpl : getSql", "end");
    }

    public void setSwitchDataSource(SwitchDataSource switchDataSource) {
        this.switchDataSource = switchDataSource;
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

}
