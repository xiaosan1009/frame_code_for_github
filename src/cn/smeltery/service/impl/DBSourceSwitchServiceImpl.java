package cn.smeltery.service.impl;

import cn.smeltery.service.IDBSourceSwitchService;

import com.richClientFrame.dao.DbConnection;
import com.richClientFrame.dao.TableRowMap;

/**
 * 共通数据源切换服务实现类.
 * @author king
 * @since 2012.03.12
 */
public class DBSourceSwitchServiceImpl implements IDBSourceSwitchService {
    
    private DbConnection db;
    
    /**
     * 取得登录用户信息.
     * @param userLogin 登录用户ID
     * @return 登录用户信息
     */
    public TableRowMap queryUserInfoByUserLogin(String userLogin) {
        return db.query("Frame.queryUserInfoByUserLogin", userLogin);
    }

    /**
     * 取得所有省与数据源的映射信息.
     * @return 所有省与数据源的映射信息
     */
    public TableRowMap[] queryAllProvinceDBMapping() {
        return db.querys("Frame.queryAllProvinceDBMapping");
    }
    
    /**
     * 设置数据源信息.
     * @param db 数据源信息
     */
    public void setDb(DbConnection db) {
        this.db = db;
    }
}
