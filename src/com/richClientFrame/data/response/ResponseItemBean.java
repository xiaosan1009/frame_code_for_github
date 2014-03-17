package com.richClientFrame.data.response;

import com.richClientFrame.dao.TableRowMap;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseItemBean
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Sep 29, 2013 3:49:58 PM
 * @author king
 */
public class ResponseItemBean {
    
    private TableRowMap dataMap = new TableRowMap();
    
    public ResponseItemBean(TableRowMap dataMap) {
        this.dataMap = dataMap;
    }

    public TableRowMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(TableRowMap dataMap) {
        this.dataMap = dataMap;
    }
    
    public void put(String key, String value) {
        dataMap.put(key, value);
    }
    
    public String get(String key) {
        return dataMap.getString(key);
    }
    
    public boolean containsKey(String key) {
        return dataMap.containsKey(key);
    }

}
