package com.richClientFrame.data.excel;

import com.richClientFrame.dao.TableRowMap;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SheetBean
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Aug 2, 2013 10:00:44 AM
 * @author king
 */
public class SheetBean {
    
    private String sheetName;
    
    private List<RowBean> rowList;
    
    private List<TableRowMap> rowTabs;

    public List<TableRowMap> getRowTabs() {
        return rowTabs;
    }

    public void setRowTabs(List<TableRowMap> rowTabs) {
        this.rowTabs = rowTabs;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<RowBean> getRowList() {
        return rowList;
    }

    public void setRowList(List<RowBean> rowList) {
        this.rowList = rowList;
    }

}
