/**
 * 
 */
package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ExceptionData
 * 类描述 ： 表示设定（输入验证）异常管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.11.04
 * @author king
 */
public class ExceptionData {
    
    /**
     * 表示设定（输入验证）异常项目ID.
     */
    private String exceptTargetId = "";
    
    /**
     * <pre>
     * 表示设定（输入验证）异常数组序列.
     * </pre>
     */
    private List<String> checkTargetIdx = new ArrayList<String>();

    /**
     * 构造函数.
     */
    public ExceptionData() {
    }
    
    /**
     * 构造函数.
     * @param exceptTargetId 项目ID
     */
    public ExceptionData(String exceptTargetId) {
        super();
        this.exceptTargetId = exceptTargetId;
    }

    /**
     * 构造函数.
     * @param exceptTargetId 项目ID
     * @param checkTargetIdx 数组序列
     */
    public ExceptionData(String exceptTargetId, List<String> checkTargetIdx) {
        super();
        this.exceptTargetId = exceptTargetId;
        this.checkTargetIdx = checkTargetIdx;
    }

    /**
     * 项目ID取得.
     * @return 项目ID
     */
    public String getExceptTargetId() {
        return exceptTargetId;
    }

    /**
     * 项目ID设定.
     * @param exceptTargetId 项目ID
     */
    public void setExceptTargetId(String exceptTargetId) {
        this.exceptTargetId = exceptTargetId;
    }

    /**
     * 数组序列取得.
     * @return 数组序列
     */
    public List<String> getCheckTargetIdx() {
        return checkTargetIdx;
    }

    /**
     * 数组序列设定.
     * @param checkTargetIdx 数组序列
     */
    public void setCheckTargetIdx(List<String> checkTargetIdx) {
        this.checkTargetIdx = checkTargetIdx;
    }
}
