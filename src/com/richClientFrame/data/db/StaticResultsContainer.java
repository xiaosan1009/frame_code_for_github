package com.richClientFrame.data.db;

import com.richClientFrame.info.ControlConfig;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： StaticResultsContainer
 * 类描述 ： 数据库结果集静态容器类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Dec 26, 20123:07:38 PM
 * @author king
 */
public class StaticResultsContainer {
    
    private static StaticResultsContainer sMeContainer;
    
    private Map<String, Integer> countTable = new Hashtable<String, Integer>();

    /**
     * @Description: 自身对象的取得.
     * @author king
     * @since Dec 26, 2012 3:20:50 PM 
     * @version V1.0
     * @return 自身对象
     */
    public static StaticResultsContainer getInstance() {
        synchronized (ControlConfig.class) {
            if (sMeContainer == null) {
                sMeContainer = new StaticResultsContainer();
            }
        }
        return sMeContainer;
    }
    
    /**
     * @Description: 判断是否包含相应的key
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @param params 参数集合
     * @return 是否包含相应的key
     */
    public boolean contains(String sql, Object params) {
        final String key = createResultsContainerKey(sql, params);
        return countTable.containsKey(key);
    }
    
    /**
     * @Description: 取得数据总数
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @return 是否包含相应的key
     */
    public Integer getCount(String sql) {
        final String key = createResultsContainerKey(sql, null);
        return countTable.get(key);
    }
    
    /**
     * @Description: 设置数据总数
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @param count 数据总数
     */
    public void setCount(String sql, int count) {
        final String key = createResultsContainerKey(sql, null);
        countTable.put(key, count);
    }
    
    /**
     * @Description: 判断是否包含相应的key
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @return 是否包含相应的key
     */
    public boolean contains(String sql) {
        final String key = createResultsContainerKey(sql, null);
        return contains(key);
    }
    
    /**
     * @Description: 取得数据总数
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @param params 参数集合
     * @return 是否包含相应的key
     */
    public Integer getCount(String sql, Object params) {
        final String key = createResultsContainerKey(sql, params);
        return getCount(key);
    }
    
    /**
     * @Description: 设置数据总数
     * @author king
     * @since Dec 26, 2012 4:06:09 PM 
     * @version V1.0
     * @param sql sql文
     * @param params 参数集合
     * @param count 数据总数
     */
    public void setCount(String sql, Object params, int count) {
        final String key = createResultsContainerKey(sql, params);
        setCount(key, count);
    }
    
    /**
     * @Description: 创建数据容器的key
     * @author king
     * @since Dec 26, 2012 4:06:58 PM 
     * @version V1.0
     * @param sql sql文
     * @param params 参数集合
     * @return 容器的key
     */
    public String createResultsContainerKey(String sql, Object params) {
        final StringBuilder sBuilder = new StringBuilder(sql);
        if (params != null && params instanceof Map) {
            Map paramMap = (Map)params;
            final Object[] key = paramMap.keySet().toArray();
            Arrays.sort(key);
            for (int i = 0; i < key.length; i++) {
                sBuilder.append(paramMap.get(key[i]));
            }
        }
        return sBuilder.toString();
    }

}
