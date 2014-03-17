package com.richClientFrame.dao;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： QueryDAO
 * 类描述 ： 数据库查询类接口.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public interface QueryDAO {

    /**
     * 把数据库查询结果封装成对象返回.
     * 
     * @param <E> 返回结果类型
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param clazz 返回结果类型
     * @return 数据库查询结果
     */
    <E> E executeForObject(String sqlID, Object bindParams, Class<?> clazz);
    
    /**
     * 把数据库查询结果封装成对象返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果条数
     */
    int executeForCount(String sqlID, Object bindParams);
    
    /**
     * 把数据库查询结果封装成TableRowMap返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
     */
    TableRowMap executeForObject(String sqlID, Object bindParams);

    /**
     * 把数据库查询结果封装成Map<String, Object>返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
     */
    Map<String, Object> executeForMap(String sqlID, Object bindParams);

    /**
     * 把数据库查询结果封装成对象返回.
     * 
     * @param <E> 返回结果类型
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param clazz 返回结果类型
     * @return 数据库查询结果
     */
    <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<?> clazz);
    
    /**
     * 把数据库查询结果封装成TableRowMap[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
     */
    TableRowMap[] executeForArray(String sqlID, Object bindParams);

    /**
     * 把数据库查询结果封装成Map<String, Object>[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @return 数据库查询结果
     */
    Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams);

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
    <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<?> clazz, int beginIndex, int maxCount);
    
    /**
     * 把数据库查询结果封装成TableRowMap[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param beginIndex 查询开始序号
     * @param maxCount 最大查询数量
     * @return 数据库查询结果
     */
    TableRowMap[] executeForArray(String sqlID, Object bindParams, int beginIndex, int maxCount);

    /**
     * 把数据库查询结果封装成Map<String, Object>[]返回.
     * 
     * @param sqlID SQL文ID
     * @param bindParams SQL文ID
     * @param beginIndex 查询开始序号
     * @param maxCount 最大查询数量
     * @return 数据库查询结果
     */
    Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams, int beginIndex, int maxCount);
}