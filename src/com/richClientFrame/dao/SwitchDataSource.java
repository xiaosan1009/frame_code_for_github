package com.richClientFrame.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.LogUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SwitchDataSource
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Jan 15, 20132:46:47 PM
 * @author king
 */
public class SwitchDataSource {
    
    private static LogUtil sLog = new LogUtil(SwitchDataSource.class, true);
    
    /**
     * 数据源类型保持对象
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    
    private Map<Object, SqlMapClient> targetDataSources;
    
    private SqlMapClient defaultDataSource;
    
    /**
     * @Description: 设置数据源数组
     * @author king
     * @since Jan 15, 2013 3:32:17 PM 
     * @version V1.0
     * @param targetDataSources 数据源数组
     */
    public void setTargetDataSources(Map<Object, SqlMapClient> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }
    
    /**
     * 数据源类型的设置.
     * 
     * @param dbType 数据源类型
     */
    public static void setType(String dbType) {
        sLog.debug("setType", null, "dbType = " + dbType);
        CONTEXT_HOLDER.set(dbType);
    }
    
    /**
     * 数据源类型的取得.
     * 
     * @return 数据源类型
     */
    public SqlMapClient getDb() {
        if (CommonUtil.isEmpty(CONTEXT_HOLDER.get())) {
            return defaultDataSource;
        }
        return targetDataSources.get(CONTEXT_HOLDER.get());
    }

    public void setDefaultDataSource(SqlMapClient defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

}
