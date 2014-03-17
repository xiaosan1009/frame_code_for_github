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
 * ��Ŀ���� �� Web2.0��������
 * ������ �� UpdateDAOImpl
 * ������ �� ���ݿ����ɾ����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class UpdateDAOImpl extends SqlMapClientDaoSupport implements UpdateDAO {

    private final LogUtil log = new LogUtil(this.getClass());
    
    private SwitchDataSource switchDataSource;

    /**
     * �����ݿ��޸Ľ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��޸Ľ��
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams) throws DataAccessException {
        return update(sqlID, bindParams, null);
    }
    
    /**
     * �����ݿ��޸Ľ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param tab ��Ϣ��������
     * @return ���ݿ��޸Ľ��
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException {
        log.debug("update Start ��sqlID = " + sqlID + "�� ��bindParams = " + bindParams + "��");
        
        executeInitialize();
        
        final int row = getSqlMapClientTemplate().update(sqlID, bindParams);
        if (tab != null) {
            getSql(sqlID, bindParams, tab);
        }
        
        log.debug("update End. success count:" + row);
        return row;
    }

    /**
     * �����ݿ���ӽ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ���ӽ��
     * @throws DataAccessException DataAccessException
     */
    public TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException {
        log.debug("insert Start ��sqlID = " + sqlID + "�� ��bindParams = " + bindParams + "��");
        
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
     * ȡ��sql��Ϣ
     * @param sqlID sql��
     * @param bindParams ����
     * @param tab ��Ϣ��������
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
        log.debug("getSql Start ��sqlID = " + sqlID + "�� ��sql = " + exeSql + "��");
        log.debug("UpdateDAOImpl : getSql", "end");
    }

    public void setSwitchDataSource(SwitchDataSource switchDataSource) {
        this.switchDataSource = switchDataSource;
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

}
