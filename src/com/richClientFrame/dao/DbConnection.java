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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： DbConnection
 * 类描述 ： 数据库连接类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class DbConnection implements IDbExcute {
    
    private LogUtil log;
    
    private UpdateDAO updateDAO;
    
    private QueryDAO queryDAO;
    
    /**
     * 构造函数.
     */
    public DbConnection() {
        log = new LogUtil(DbConnection.class, true);
    }
    
    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    public PageBean queryPages(String sqlID, int pageSize, Param param) {
        return queryPages(sqlID, null, pageSize, param);
    }
    
    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    public PageBean queryPages(
            String sqlID, Object bindParams, int pageSize, Param param) {
        return queryPagesPriv(sqlID, bindParams, pageSize, param);
    }

    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    private PageBean queryPagesPriv(String sqlID, Object bindParams, int pageSize, Param param) {
        log.info("queryPagesPriv", "start", "sqlID = " + sqlID, "pageSize = " + pageSize);
        PageBean pageBean = null;
        TableRowMap[] tabs = null;
        int count = 0;
        if (ConstantsUtil.Client.FLEX_TYPE.equals(param.clientType)) {
            tabs = querys(sqlID, bindParams);
        } else {
            // 当前页数
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
                log.info("queryPagesPriv", "通过缓存获得查询列表数据条数为：" + count);
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
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    public TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException {
        final TableRowMap result = updateDAO.insert(sqlID, bindParams);
        return result;
    }
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams) throws DataAccessException {
        final int row = updateDAO.update(sqlID, bindParams);
        return row;
    }
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param tab 信息保存容器
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException {
        final int row = updateDAO.update(sqlID, bindParams, tab);
        return row;
    }
    
    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    public TableRowMap query(String sqlID) {
        final TableRowMap tab = query(sqlID, null);
        return tab;
    }
    
    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     */
    public TableRowMap query(String sqlID, Object bindParams) {
        final TableRowMap[] tabs = querys(sqlID, bindParams);
        if (tabs == null || tabs.length <= 0) {
            return null;
        }
        return tabs[0];
    }
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    public TableRowMap[] querys(String sqlID) {
        final TableRowMap[] tabs = querys(sqlID, null);
        return tabs;
    }
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     */
    public TableRowMap[] querys(String sqlID, Object bindParams) {
        final TableRowMap[] tabs = queryDAO.executeForArray(sqlID, bindParams);
        return tabs;
    }

    /**
     * 设置数据库修改对象.
     * @param updateDAO 数据库修改对象
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }

    /**
     * 设置数据库查询对象.
     * @param queryDAO 数据库查询对象
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
