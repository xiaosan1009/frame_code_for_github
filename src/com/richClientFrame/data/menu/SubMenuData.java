/**
 * 
 */
package com.richClientFrame.data.menu;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SubMenuData
 * 类描述 ： 下拉菜单信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.11.04
 * @author king
 */
public class SubMenuData {
    
    /**
     * 下拉菜单名称
     */
    private String subname;
    
    /**
     * 下拉菜单画面ID
     */
    private String dispcode;
    
    /**
     * 机能ID
     */
    private String functionId;

    /**
     * 构造函数.
     */
    public SubMenuData() {
        super();
    }

    /**
     * 子菜单名称取得.
     * 
     * @return 子菜单名称取得
     */
    public String getSubname() {
        return subname;
    }

    /**
     * 子菜单名称设定.
     * 
     * @param subname 子菜单名称
     */
    public void setSubname(String subname) {
        this.subname = subname;
    }

    /**
     * 下拉菜单名称文字数的取得.
     * 
     * @return 下拉菜单名称文字数
     */
    public int getNameLength() {

        return getSubname().length();
    }

    /**
     * 下拉菜单画面ID取得.
     * 
     * @return dispcode 下拉菜单画面ID
     * 
     */
    public String getDispcode() {
        return dispcode;
    }

    /**
     * 下拉菜单画面ID设定.
     * 
     * @param dispcode 下拉菜单画面ID
     */
    public void setDispcode(String dispcode) {
        this.dispcode = dispcode;
    }

    /**
     * 机能ID取得.
     * 
     * @return dispcode 机能ID
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * 机能ID设定.
     * 
     * @param functionId 机能ID
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

}
