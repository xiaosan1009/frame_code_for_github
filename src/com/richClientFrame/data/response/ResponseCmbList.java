/**
 * 
 */
package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseCmbList
 * 类描述 ： 下拉列表请求数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2009.07.28
 * @author king
 */
public class ResponseCmbList {
    
    /**
     * 项目控件LIST.
     */
    private List<ResponseTab[]> list = new ArrayList<ResponseTab[]>();
    
    /**
     * 构造函数.
     * 
     * @param list 项目控件LIST
     */
    public ResponseCmbList(List<ResponseTab[]> list) {
        this.list = list;
    }
    
    /**
     * 项目控件LIST的取得.
     * 
     * @return 项目控件LIST
     */
    public List<ResponseTab[]> getList() {
        return list;
    }
    
    /**
     * 项目控件LIST的设定.
     * 
     * @param list 项目控件LIST
     */
    public void setList(List<ResponseTab[]> list) {
        this.list = list;
    }
    
    /**
     * 行控件的取得.
     * 
     * @param index 序号
     * @return 行控件
     */
    public ResponseTab[] getLine(int index) {
        return (ResponseTab[])list.get(index);
    }
    
    /**
     * 行控件的添加.
     * 
     * @param resCmbs 控件内容
     */
    public void addLine(ResponseTab[] resCmbs) {
        list.add(resCmbs);
    }
    
    /**
     * 行控件的总数的取得.
     * 
     * @return 行控件的总数
     */
    public int size() {
        return list.size();
    }
}
