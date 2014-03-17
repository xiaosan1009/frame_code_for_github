package com.richClientFrame.data.db;

import com.richClientFrame.dao.TableRowMap;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： PageBean
 * 类描述 ： 分页数据信息存储类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Sep 14, 2012 11:07:26 PM 
 * @author king
 */
public class PageBean {
    
    private List<TableRowMap> datas;
    
    private int totalRows;
    
    private int pageSize;

    /** 
    * @Description: 取得列表信息数据
    * @author king
    * @since Sep 14, 2012 11:07:53 PM 
    * 
    * @return 列表信息数据
    */
    public List<TableRowMap> getDatas() {
        return datas;
    }

    /** 
    * @Description: 设置列表信息数据
    * @author king
    * @since Sep 14, 2012 11:08:08 PM 
    *  
    * @param datas 列表信息数据
    */
    public void setDatas(List<TableRowMap> datas) {
        this.datas = datas;
    }

    /** 
    * @Description: 取得数据总数
    * @author king
    * @since Sep 14, 2012 11:08:35 PM 
    * 
    * @return 数据总数
    */
    public int getTotalRows() {
        return totalRows;
    }

    /** 
    * @Description: 设置数据总数
    * @author king
    * @since Sep 14, 2012 11:08:49 PM 
    * 
    * @param totalRows 数据总数
    */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /** 
    * @Description: 取得每页数据条数
    * @author king
    * @since Sep 14, 2012 11:09:01 PM 
    * 
    * @return 每页数据条数
    */
    public int getPageSize() {
        return pageSize;
    }

    /** 
    * @Description: 设置每页数据条数
    * @author king
    * @since Sep 14, 2012 11:09:18 PM 
    * 
    * @param pageSize 每页数据条数
    */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
