package com.richClientFrame.dao;

import com.ibatis.sqlmap.engine.mapping.result.ResultMapping;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： SqlInfo
 * 类描述 ： sql信息保持类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Sep 11, 2012 3:01:16 PM 
 * @author king
 */
public class SqlInfo {
    
    private String sql;
    
    private ResultMapping[] resultMappings;

    /**
     * 取得sql文信息.
     * @return sql文信息
     */
    public String getSql() {
        return sql;
    }

    /**
     * 设置sql文信息.
     * @param sql sql文信息.
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * 取得sql文参数信息.
     * @return sql文参数信息
     */
    public ResultMapping[] getResultMappings() {
        return resultMappings;
    }

    /**
     * 设置sql文参数信息.
     * @param resultMappings sql文参数信息.
     */
    public void setResultMappings(ResultMapping[] resultMappings) {
        this.resultMappings = resultMappings;
    }

}
