package com.richClientFrame.data.response;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseLine
 * ������ �� �б�ÿ�����ݹ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
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
