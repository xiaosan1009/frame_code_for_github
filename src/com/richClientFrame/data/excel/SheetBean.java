package com.richClientFrame.data.excel;

import com.richClientFrame.dao.TableRowMap;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SheetBean
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Aug 2, 2013 10:00:44 AM
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
