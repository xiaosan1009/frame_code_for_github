package com.richClientFrame.data.param;

import com.richClientFrame.dao.TableRowMap;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： UpdateParam
 * 类描述 ： 更新处理参数容器类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.09.07
 * @author king
 */
public class UpdateParam {
    
    private boolean dbResult;
    
    private TableRowMap result;

    public boolean isDbResult() {
        return dbResult;
    }

    public void setDbResult(boolean dbResult) {
        this.dbResult = dbResult;
    }

    public TableRowMap getResult() {
        return result;
    }

    public void setResult(TableRowMap result) {
        this.result = result;
    }

}
