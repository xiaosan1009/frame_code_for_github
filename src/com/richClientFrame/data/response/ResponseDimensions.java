package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseDimensions
 * 类描述 ： 二维数据信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class ResponseDimensions {
    
    /**
     * 二维数据信息LIST.
     */
    private List<ResponseList> dimensions = new ArrayList<ResponseList>();
    
    /**
     * 二维数据信息的取得.
     * 
     * @param index 序号
     * @return 二维数据信息
     */
    public ResponseList getDimension(int index) {
        return dimensions.get(index);
    }
    
    /**
     * 二维数据信息的添加.
     * 
     * @param responseList 二维数据信息
     */
    public void addDimension(ResponseList responseList) {
        dimensions.add(responseList);
    }
    
    public void addDimension(ResponseList responseList, int index) {
        dimensions.add(index, responseList);
    }
    
    public int size() {
        return dimensions.size();
    }
}
