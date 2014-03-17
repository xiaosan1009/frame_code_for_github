package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseList
 * 类描述 ： 列表全体数据管理类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class ResponseList {
    
    public final static int SORT_UP = 1;

    public final static int SORT_DOWM = -1;
    
    private List<ResponseLine> list = new ArrayList<ResponseLine>();
    
    private ResponseLine dimensionLine;
    
    private boolean mHasCmbs;
    
    public List<ResponseLine> getList() {
        return list;
    }
    
    public void setList(List<ResponseLine> list) {
        this.list = list;
    }
    
    public ResponseLine getLine(int index) {
        return (ResponseLine)list.get(index);
    }
    
    public void addLine(ResponseLine responseLine) {
        list.add(responseLine);
    }
    
    public void addLine(ResponseLine responseLine, int index) {
        list.add(index, responseLine);
    }
    
    public int size() {
        return list.size();
    }
    
    public List<ResponseLine> listSort(int sortType) {
        Collections.sort(list, new Sort(sortType));
        return list;
    }
    
    private class Sort implements Comparator<ResponseLine> {

        private final static int UP = 1;

        private final static int DOWM = -1;

        private int state;

        public Sort(int state) {
            this.state = state;
        }

        public int compare(ResponseLine o1, ResponseLine o2) {
            if (state == Sort.DOWM) {
                return sortDown(o1, o2);
            }
            return sortUp(o1, o2);
        }

        private int sortUp(ResponseLine o1, ResponseLine o2) {
            if (o1.getSortItem() < o2.getSortItem()) {
                return -1;
            } else if (o1.getSortItem() > o2.getSortItem()) {
                return 1;
            } else {
                return 0;
            }
        }

        private int sortDown(ResponseLine o1, ResponseLine o2) {
            if (o1.getSortItem() > o2.getSortItem()) {
                return -1;
            } else if (o1.getSortItem() < o2.getSortItem()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public ResponseLine getDimensionLine() {
        return dimensionLine;
    }

    public void setDimensionLine(ResponseLine line) {
        this.dimensionLine = line;
    }
    
    public boolean isHasCmbs() {
        return mHasCmbs;
    }

    public void setHasCmbs(boolean hasCmbs) {
        mHasCmbs = hasCmbs;
    }
}
