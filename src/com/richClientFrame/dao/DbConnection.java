package com.richClientFrame.dao;

import com.ibatis.sqlmap.client.SqlMapException;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.db.StaticResultsContainer;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.springframework.dao.DataAccessException;

import java.util.Arrays;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� DbConnection
 * ������ �� ���ݿ�������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class DbConnection implements IDbExcute {
    
    private LogUtil log;
    
    private UpdateDAO updateDAO;
    
    private QueryDAO queryDAO;
    
    /**
     * ���캯��.
     */
    public DbConnection() {
        log = new LogUtil(DbConnection.class, true);
    }
    
    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    public PageBean queryPages(String sqlID, int pageSize, Param param) {
        return queryPages(sqlID, null, pageSize, param);
    }
    
    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    public PageBean queryPages(
            String sqlID, Object bindParams, int pageSize, Param param) {
        return queryPagesPriv(sqlID, bindParams, pageSize, param);
    }

    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    private PageBean queryPagesPriv(String sqlID, Object bindParams, int pageSize, Param param) {
        log.info("queryPagesPriv", "start", "sqlID = " + sqlID, "pageSize = " + pageSize);
        PageBean pageBean = null;
        TableRowMap[] tabs = null;
        int count = 0;
        if (ConstantsUtil.Client.FLEX_TYPE.equals(param.clientType)) {
            tabs = querys(sqlID, bindParams);
        } else {
            // ��ǰҳ��
            int page = 1;
            final String currentpage = param.get("currentpage");
            if (CommonUtil.isNotEmpty(currentpage)) {
                page = Integer.parseInt(currentpage);
            }
            final String pagingTotalRows = param.get("pagingTotalRows");
            if (page == 1 || CommonUtil.isEmpty(pagingTotalRows)) {
                try {
                    count = queryDAO.executeForCount(sqlID, bindParams);
                } catch (SqlMapException e) {
                    final TableRowMap[] tabCount = queryDAO.executeForArray(sqlID, bindParams);
                    if (tabCount != null) {
                        count = tabCount.length;
                    }
                }
            } else {
                count = Integer.parseInt(pagingTotalRows);
                log.info("queryPagesPriv", "ͨ�������ò�ѯ�б���������Ϊ��" + count);
            }
            final int beginIndex = (page - 1) * pageSize;
            if (beginIndex >= count) {
//                page = count / pageSize;
//                if (count % pageSize == 0) {
//                    page--;
//                }
//                beginIndex = page * pageSize;
                return null;
            }
            tabs = queryDAO.executeForArray(sqlID, bindParams, beginIndex, pageSize);
        }
        if (tabs != null) {
            pageBean = new PageBean();
            pageBean.setDatas(Arrays.asList(tabs));
            pageBean.setPageSize(pageSize);
            pageBean.setTotalRows(count);
        }
        log.info("queryPagesPriv", "end", "pageBean = " + pageBean);
        return pageBean;
    }
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    public TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException {
        final TableRowMap result = updateDAO.insert(sqlID, bindParams);
        return result;
    }
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams) throws DataAccessException {
        final int row = updateDAO.update(sqlID, bindParams);
        return row;
    }
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param tab ��Ϣ��������
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException {
        final int row = updateDAO.update(sqlID, bindParams, tab);
        return row;
    }
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    public TableRowMap query(String sqlID) {
        final TableRowMap tab = query(sqlID, null);
        return tab;
    }
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     */
    public TableRowMap query(String sqlID, Object bindParams) {
        final TableRowMap[] tabs = querys(sqlID, bindParams);
        if (tabs == null || tabs.length <= 0) {
            return null;
        }
        return tabs[0];
    }
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    public TableRowMap[] querys(String sqlID) {
        final TableRowMap[] tabs = querys(sqlID, null);
        return tabs;
    }
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     */
    public TableRowMap[] querys(String sqlID, Object bindParams) {
        final TableRowMap[] tabs = queryDAO.executeForArray(sqlID, bindParams);
        return tabs;
    }

    /**
     * �������ݿ��޸Ķ���.
     * @param updateDAO ���ݿ��޸Ķ���
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * �������ݿ��ѯ����.
     * @param queryDAO ���ݿ��ѯ����
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
