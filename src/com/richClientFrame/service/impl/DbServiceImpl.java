package com.richClientFrame.service.impl;

import com.richClientFrame.dao.IDbExcute;
import com.richClientFrame.dao.SwitchDataSource;
import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IExpandFace;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.LogUtil;

import org.springframework.dao.DataAccessException;

import java.util.Map;

/**
 * 共通DB操作服务实现类.
 * @author king
 * @since 2012.03.28
 */
public class DbServiceImpl implements IDbService {
    
    private LogUtil log = new LogUtil(this.getClass(), true);
    
    private IDbExcute db;
    
    private IExpandFace expand;
    
    public IDbService get(String key) {
        SwitchDataSource.setType(key);
        return this;
    }

    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap insert(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException {
        TableRowMap tab = null;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            // 添加更新数据库
            tab = db.insert(sqlID, bindParams);
            // update操作之后的切面方法扩展方法
            setUpdatePost(tab);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return tab;
    }

    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    public TableRowMap query(String sqlID) {
        TableRowMap tab = null;
        try {
            // 查询单条数据
            tab = db.query(sqlID);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return tab;
    }

    /**
     * 查询单条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap query(String sqlID, Object bindParams) 
        throws RichClientWebException {
        TableRowMap tab = null;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            // 查询单条数据
            tab = db.query(sqlID, bindParams);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return tab;
    }

    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     */
    public PageBean queryPages(String sqlID, int pageSize, Param param) {
        PageBean pageBean = null;
        try {
            // 分页查询
            pageBean = db.queryPages(sqlID, pageSize, param);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return pageBean;
    }

    /**
     * 分页查询.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param pageSize 每页显示数据数
     * @param param request
     * @return 查询结果
     * @throws RichClientWebException RichClientWebException
     */
    public PageBean queryPages(String sqlID, Object bindParams, int pageSize,
            Param param) throws RichClientWebException {
        PageBean pageBean = null;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            // 分页查询
            pageBean = db.queryPages(sqlID, bindParams, pageSize, param);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return pageBean;
    }

    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @return 操作结果
     */
    public TableRowMap[] querys(String sqlID) {
        TableRowMap[] tabs = null;
        try {
            // 查询多条数据
            tabs = db.querys(sqlID);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return tabs;
    }

    /**
     * 查询多条数据.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap[] querys(String sqlID, Object bindParams) throws RichClientWebException {
        TableRowMap[] tabs = null;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            // 查询多条数据
            tabs = db.querys(sqlID, bindParams);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return tabs;
    }

    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public int update(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException {
        int result = 0;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            final TableRowMap tab = new TableRowMap();
            // 添加更新数据库
            result = db.update(sqlID, bindParams);
            // update操作之后的切面方法扩展方法
            setUpdatePost(tab);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return result;
    }
    
    
    /**
     * 添加更新数据库.
     * @param sqlID sql ID
     * @param bindParams 查询条件
     * @param tab 信息保存容器
     * @return 操作结果
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) 
        throws DataAccessException, RichClientWebException {
        int result = 0;
        try {
            // 根据扩展对象初始化参数
            setCondition(bindParams);
            // 添加更新数据库
            result = db.update(sqlID, bindParams, tab);
            // 数据库操作执行完成之后的处理
            setUpdatePost(tab);
        } finally {
            // 数据库操作执行完成之后的处理
            afterExecute();
        }
        return result;
    }
    
    /**
     * 设置数据源信息.
     * @param db 数据源信息
     */
    public void setDb(IDbExcute db) {
        this.db = db;
    }

    /**
     * 设置扩展对象.
     * @param expand 扩展对象
     */
    public void setExpand(IExpandFace expand) {
        this.expand = expand;
    }
    
    /**
     * 根据扩展对象初始化参数.
     * @param bindParams 参数容器
     * @throws RichClientWebException RichClientWebException
     */
    private void setCondition(Object bindParams) throws RichClientWebException {
        if (expand != null && bindParams instanceof Map) {
            expand.createCondition((Map<String, Object>) bindParams);
        }
    }
    
    /**
     * update操作之后的切面方法扩展方法.
     * @param tab 参数容器
     * @throws RichClientWebException RichClientWebException
     */
    private void setUpdatePost(TableRowMap tab) throws RichClientWebException {
        log.info("setUpdatePost", "start", "expand = " + expand);
        if (expand != null && tab != null) {
            expand.postUpdate(tab);
        }
        log.info("setUpdatePost", "end");
    }
    
    /**
     * @Description: 数据库操作执行完成之后的处理
     * @author king
     * @since Jan 17, 2013 1:07:26 PM 
     * @version V1.0
     */
    private void afterExecute() {
        SwitchDataSource.setType(null);
    }

}
