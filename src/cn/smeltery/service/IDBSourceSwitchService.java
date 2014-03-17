package cn.smeltery.service;

import com.richClientFrame.dao.TableRowMap;

/**
 * 共通数据源切换服务接口.
 * @author king
 * @since 2012.03.12
 */
public interface IDBSourceSwitchService {
    
    /**
     * 取得所有省与数据源的映射信息.
     * @return 所有省与数据源的映射信息
     */
    TableRowMap[] queryAllProvinceDBMapping();
    
    /**
     * 取得登录用户信息.
     * @param userLogin 登录用户ID
     * @return 登录用户信息
     */
    TableRowMap queryUserInfoByUserLogin(String userLogin);
}
