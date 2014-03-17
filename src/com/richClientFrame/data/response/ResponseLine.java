package com.richClientFrame.data.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseLine
 * 类描述 ： 列表每行数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class ResponseLine {

    private Map<String, ResponseTab> line = new HashMap<String, ResponseTab>();
    
    private int listLineIndex;
    
    private boolean changeLine;
    
    private String lineChar;
    
    private long sortItem;
    
    private ResponseList list;
    
    public int getListLineIndex() {
        return listLineIndex;
    }

    public void setListLineIndex(int listLineIndex) {
        this.listLineIndex = listLineIndex;
    }

    public Map<String, ResponseTab> getLine() {
        return line;
    }
    
    public void setLine(Map<String, ResponseTab> line) {
        this.line = line;
    }
    
    public ResponseTab get(String key) {
        return (ResponseTab)line.get(key);
    }
    
    public void put(String key, ResponseTab responseTab) {
        line.put(key, responseTab);
    }

    public boolean isChangeLine() {
        return changeLine;
    }

    public void setChangeLine(boolean changeLine) {
        this.changeLine = changeLine;
    }

    public String getLineChar() {
        return lineChar;
    }

    public void setLineChar(String lineChar) {
        this.lineChar = lineChar;
    }

    public long getSortItem() {
        return sortItem;
    }

    public void setSortItem(long sortItem) {
        this.sortItem = sortItem;
    }

    public ResponseList getList() {
        return list;
    }

    public void setList(ResponseList list) {
        this.list = list;
    }

}
