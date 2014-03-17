package com.richClientFrame.data.db;

import com.richClientFrame.dao.TableRowMap;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� PageBean
 * ������ �� ��ҳ������Ϣ�洢��
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Sep 14, 2012 11:07:26 PM 
 * @author king
 */
public class PageBean {
    
    private List<TableRowMap> datas;
    
    private int totalRows;
    
    private int pageSize;

    /** 
    * @Description: ȡ���б���Ϣ����
    * @author king
    * @since Sep 14, 2012 11:07:53 PM 
    * 
    * @return �б���Ϣ����
    */
    public List<TableRowMap> getDatas() {
        return datas;
    }

    /** 
    * @Description: �����б���Ϣ����
    * @author king
    * @since Sep 14, 2012 11:08:08 PM 
    *  
    * @param datas �б���Ϣ����
    */
    public void setDatas(List<TableRowMap> datas) {
        this.datas = datas;
    }

    /** 
    * @Description: ȡ����������
    * @author king
    * @since Sep 14, 2012 11:08:35 PM 
    * 
    * @return ��������
    */
    public int getTotalRows() {
        return totalRows;
    }

    /** 
    * @Description: ������������
    * @author king
    * @since Sep 14, 2012 11:08:49 PM 
    * 
    * @param totalRows ��������
    */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /** 
    * @Description: ȡ��ÿҳ��������
    * @author king
    * @since Sep 14, 2012 11:09:01 PM 
    * 
    * @return ÿҳ��������
    */
    public int getPageSize() {
        return pageSize;
    }

    /** 
    * @Description: ����ÿҳ��������
    * @author king
    * @since Sep 14, 2012 11:09:18 PM 
    * 
    * @param pageSize ÿҳ��������
    */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
