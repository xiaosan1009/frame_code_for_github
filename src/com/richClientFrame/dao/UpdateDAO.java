package com.richClientFrame.dao;

import org.springframework.dao.DataAccessException;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： UpdateDAO
 * 类描述 ： 数据库添加删除类接口.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public interface UpdateDAO {

    /**
     * 把数据库修改结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库修改结果
     */
    int update(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * 把数据库修改结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param tab 信息保存容器
     * @return 数据库修改结果
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException;
    
    /**
     * 把数据库添加结果返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库添加结果
     */
    TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException;

}
