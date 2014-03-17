package com.richClientFrame.dao;

import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;

import org.springframework.dao.DataAccessException;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： IDbExcute
 * 类描述 ： 数据库连接接口.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.11.08
 * @author king
 */
public interface IDbExcute {
    
    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    PageBean queryPages(String sqlID, int pageSize, Param param);
    
    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    PageBean queryPages(String sqlID, Object bindParams, int pageSize, Param param);
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    int update(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param tab 信息保存容器
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException;
    
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
    TableRowMap query(String sqlID, Object bindParams);
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    TableRowMap[] querys(String sqlID);
    
    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     */
    TableRowMap[] querys(String sqlID, Object bindParams);

}
