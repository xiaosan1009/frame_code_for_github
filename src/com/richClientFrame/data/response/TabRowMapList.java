package com.richClientFrame.data.response;

import com.richClientFrame.dao.TableRowMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： TabRowMapList
 * 类描述 ： 列表全体数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class TabRowMapList {
    
    private List<TableRowMap> list = new ArrayList<TableRowMap>();
    
    private TableRowMap dimensionLine;
    
    public List<TableRowMap> getList() {
        return list;
    }
    
    public void setList(List<TableRowMap> list) {
        this.list = list;
    }
    
    public TableRowMap getLine(int index) {
        return list.get(index);
    }
    
    public void addLine(TableRowMap responseLine) {
        list.add(responseLine);
    }
    
    public void addLine(TableRowMap responseLine, int index) {
        list.add(index, responseLine);
    }
    
    public int size() {
        return list.size();
    }

    public TableRowMap getDimensionLine() {
        return dimensionLine;
    }

    public void setDimensionLine(TableRowMap line) {
        this.dimensionLine = line;
    }
}
