package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： TabRowMapDimensions
 * 类描述 ： 二维数据信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class TabRowMapDimensions {
    
    /**
     * 二维数据信息LIST.
     */
    private List<TabRowMapList> dimensions = new ArrayList<TabRowMapList>();
    
    /**
     * 二维数据信息的取得.
     * 
     * @param index 序号
     * @return 二维数据信息
     */
    public TabRowMapList getDimension(int index) {
        return dimensions.get(index);
    }
    
    /**
     * 二维数据信息的添加.
     * 
     * @param tabRowMapList 二维数据信息
     */
    public void addDimension(TabRowMapList tabRowMapList) {
        dimensions.add(tabRowMapList);
    }
    
    public void addDimension(TabRowMapList tabRowMapList, int index) {
        dimensions.add(index, tabRowMapList);
    }
    
    public int size() {
        return dimensions.size();
    }
}
