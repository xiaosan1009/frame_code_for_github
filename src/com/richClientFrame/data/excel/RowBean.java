package com.richClientFrame.data.excel;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� RowBean
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Aug 2, 2013 9:53:17 AM
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
