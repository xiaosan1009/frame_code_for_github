package com.richClientFrame.data.excel;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： RowBean
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Aug 2, 2013 9:53:17 AM
 * @author king
 */
public class RowBean {
    
    private String site;
    
    List<CellBean> cellList;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<CellBean> getCellList() {
        return cellList;
    }

    public void setCellList(List<CellBean> cellList) {
        this.cellList = cellList;
    }

}
