package com.richClientFrame.service;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IExpandFace;

import org.springframework.dao.DataAccessException;

/**
 * 共通DB操作服务接口.
 * @author king
 * @since 2012.03.28
 */
public interface IDbService {

    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     * @throws RichClientWebException RichClientWebException
     */
    PageBean queryPages(String sqlID, int pageSize, Param param) 
        throws RichClientWebException;
    
    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     * @throws RichClientWebException RichClientWebException
     */
    PageBean queryPages(String sqlID, Object bindParams, int pageSize, Param param) 
        throws RichClientWebException;
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    TableRowMap insert(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException;
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    int update(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException;
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param tab 信息保存容器
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) 
        throws DataAccessException, RichClientWebException;
    
    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    TableRowMap query(String sqlID);
    
    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     */
    TableRowMap query(String sqlID, Object bindParams) throws RichClientWebException;
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    TableRowMap[] querys(String sqlID) throws RichClientWebException;
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     */
    TableRowMap[] querys(String sqlID, Object bindParams) throws RichClientWebException;
    
    /**
     * 设置扩展对象.
     * @param expand 扩展对象
     */
    void setExpand(IExpandFace expand);
    
    IDbService get(String key);
}
