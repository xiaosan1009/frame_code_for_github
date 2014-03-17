package com.richClientFrame.dao;

import com.richClientFrame.util.LogUtil;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： DynamicDataSource
 * 类描述 ： 动态切换数据源类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.07.12
 * @author king
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    private static LogUtil sLog = new LogUtil(DynamicDataSource.class, true);

    /**
     * 数据源类型保持对象
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    
    /**
     * 数据源保持对象
     */
    private static Map<String,String> sDbMap;
    
    /**
     * 构造函数.
     */
    public DynamicDataSource() {
        CONTEXT_HOLDER.set("ds_oracle1");
    }

    /**
     * 数据源保持对象的取得.
     * 
     * @return 数据源保持对象
     */
    public static Map<String, String> getDbMap() {
        return sDbMap;
    }
    
    /**
     * 数据源类型的设置.
     * 
     * @param dbType 数据源类型
     */
    public static void setDb(String dbType) {
        sLog.info("setDb", null, "dbType = " + dbType);
        CONTEXT_HOLDER.set(dbType);
    }
    
    /**
     * 数据源类型的取得.
     * 
     * @param request request
     * @return 数据源类型
     */
    public static String getDb(HttpServletRequest request) {
        if (null != request) {
            return  request.getSession().getAttribute("datasource").toString();
        }
        return CONTEXT_HOLDER.get();
    }

    /**
     * 数据源类型的消除.
     * 
     */
    public static void clearDb() {
        CONTEXT_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        final Object dataSource = DynamicDataSource.getDb(null);
        sLog.info("determineCurrentLookupKey", null, "dataSource = " + dataSource);
        return dataSource;
    }

}
